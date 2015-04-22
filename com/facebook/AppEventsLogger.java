package com.facebook;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import bolts.AppLinks;
import com.facebook.internal.AttributionIdentifiers;
import com.facebook.internal.Logger;
import com.facebook.internal.Utility;
import com.facebook.internal.Utility.FetchedAppSettings;
import com.facebook.internal.Validate;
import com.facebook.model.GraphObject;
import com.facebook.model.GraphObject.Factory;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Currency;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AppEventsLogger
{
  public static final String ACTION_APP_EVENTS_FLUSHED = "com.facebook.sdk.APP_EVENTS_FLUSHED";
  public static final String APP_EVENTS_EXTRA_FLUSH_RESULT = "com.facebook.sdk.APP_EVENTS_FLUSH_RESULT";
  public static final String APP_EVENTS_EXTRA_NUM_EVENTS_FLUSHED = "com.facebook.sdk.APP_EVENTS_NUM_EVENTS_FLUSHED";
  private static final int APP_SUPPORTS_ATTRIBUTION_ID_RECHECK_PERIOD_IN_SECONDS = 86400;
  private static final int FLUSH_APP_SESSION_INFO_IN_SECONDS = 30;
  private static final int FLUSH_PERIOD_IN_SECONDS = 60;
  private static final int NUM_LOG_EVENTS_TO_TRY_TO_FLUSH_AFTER = 100;
  private static final String SOURCE_APPLICATION_HAS_BEEN_SET_BY_THIS_INTENT = "_fbSourceApplicationHasBeenSet";
  private static final String TAG = AppEventsLogger.class.getCanonicalName();
  private static Context applicationContext;
  private static ScheduledThreadPoolExecutor backgroundExecutor;
  private static FlushBehavior flushBehavior = FlushBehavior.AUTO;
  private static String hashedDeviceAndAppId;
  private static boolean isOpenedByApplink;
  private static boolean requestInFlight;
  private static String sourceApplication;
  private static Map<AccessTokenAppIdPair, SessionEventsState> stateMap = new ConcurrentHashMap();
  private static Object staticLock = new Object();
  private final AccessTokenAppIdPair accessTokenAppId;
  private final Context context;
  
  private AppEventsLogger(Context paramContext, String paramString, Session paramSession)
  {
    Validate.notNull(paramContext, "context");
    this.context = paramContext;
    if (paramSession == null) {
      paramSession = Session.getActiveSession();
    }
    if ((paramSession != null) && ((paramString == null) || (paramString.equals(paramSession.getApplicationId())))) {
      this.accessTokenAppId = new AccessTokenAppIdPair(paramSession);
    }
    synchronized (staticLock)
    {
      if (hashedDeviceAndAppId == null) {
        hashedDeviceAndAppId = Utility.getHashedDeviceAndAppID(paramContext, paramString);
      }
      if (applicationContext == null) {
        applicationContext = paramContext.getApplicationContext();
      }
      initializeTimersIfNeeded();
      return;
      if (paramString == null) {
        paramString = Utility.getMetadataApplicationId(paramContext);
      }
      this.accessTokenAppId = new AccessTokenAppIdPair(null, paramString);
    }
  }
  
  private static int accumulatePersistedEvents()
  {
    PersistedEvents localPersistedEvents = PersistedEvents.readAndClearStore(applicationContext);
    int i = 0;
    Iterator localIterator = localPersistedEvents.keySet().iterator();
    while (localIterator.hasNext())
    {
      AccessTokenAppIdPair localAccessTokenAppIdPair = (AccessTokenAppIdPair)localIterator.next();
      SessionEventsState localSessionEventsState = getSessionEventsState(applicationContext, localAccessTokenAppIdPair);
      List localList = localPersistedEvents.getEvents(localAccessTokenAppIdPair);
      localSessionEventsState.accumulatePersistedEvents(localList);
      i += localList.size();
    }
    return i;
  }
  
  public static void activateApp(Context paramContext)
  {
    Settings.sdkInitialize(paramContext);
    activateApp(paramContext, Utility.getMetadataApplicationId(paramContext));
  }
  
  public static void activateApp(Context paramContext, String paramString)
  {
    if ((paramContext == null) || (paramString == null)) {
      throw new IllegalArgumentException("Both context and applicationId must be non-null");
    }
    if ((paramContext instanceof Activity)) {
      setSourceApplication((Activity)paramContext);
    }
    for (;;)
    {
      Settings.publishInstallAsync(paramContext, paramString, null);
      AppEventsLogger localAppEventsLogger = new AppEventsLogger(paramContext, paramString, null);
      final long l = System.currentTimeMillis();
      String str = getSourceApplication();
      backgroundExecutor.execute(new Runnable()
      {
        public void run()
        {
          this.val$logger.logAppSessionResumeEvent(l, this.val$sourceApplicationInfo);
        }
      });
      return;
      resetSourceApplication();
      Log.d(AppEventsLogger.class.getName(), "To set source application the context of activateApp must be an instance of Activity");
    }
  }
  
  private static FlushStatistics buildAndExecuteRequests(FlushReason paramFlushReason, Set<AccessTokenAppIdPair> paramSet)
  {
    FlushStatistics localFlushStatistics = new FlushStatistics(null);
    boolean bool = Settings.getLimitEventAndDataUsage(applicationContext);
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator1 = paramSet.iterator();
    while (localIterator1.hasNext())
    {
      AccessTokenAppIdPair localAccessTokenAppIdPair = (AccessTokenAppIdPair)localIterator1.next();
      SessionEventsState localSessionEventsState = getSessionEventsState(localAccessTokenAppIdPair);
      if (localSessionEventsState != null)
      {
        Request localRequest = buildRequestForSession(localAccessTokenAppIdPair, localSessionEventsState, bool, localFlushStatistics);
        if (localRequest != null) {
          localArrayList.add(localRequest);
        }
      }
    }
    if (localArrayList.size() > 0)
    {
      LoggingBehavior localLoggingBehavior = LoggingBehavior.APP_EVENTS;
      String str = TAG;
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = Integer.valueOf(localFlushStatistics.numEvents);
      arrayOfObject[1] = paramFlushReason.toString();
      Logger.log(localLoggingBehavior, str, "Flushing %d events due to %s.", arrayOfObject);
      Iterator localIterator2 = localArrayList.iterator();
      while (localIterator2.hasNext()) {
        ((Request)localIterator2.next()).executeAndWait();
      }
    }
    localFlushStatistics = null;
    return localFlushStatistics;
  }
  
  private static Request buildRequestForSession(AccessTokenAppIdPair paramAccessTokenAppIdPair, final SessionEventsState paramSessionEventsState, boolean paramBoolean, final FlushStatistics paramFlushStatistics)
  {
    String str = paramAccessTokenAppIdPair.getApplicationId();
    Utility.FetchedAppSettings localFetchedAppSettings = Utility.queryAppSettings(str, false);
    final Request localRequest = Request.newPostRequest(null, String.format("%s/activities", new Object[] { str }), null, null);
    Bundle localBundle = localRequest.getParameters();
    if (localBundle == null) {
      localBundle = new Bundle();
    }
    localBundle.putString("access_token", paramAccessTokenAppIdPair.getAccessToken());
    localRequest.setParameters(localBundle);
    int i = paramSessionEventsState.populateRequest(localRequest, localFetchedAppSettings.supportsImplicitLogging(), localFetchedAppSettings.supportsAttribution(), paramBoolean);
    if (i == 0) {
      return null;
    }
    paramFlushStatistics.numEvents = (i + paramFlushStatistics.numEvents);
    localRequest.setCallback(new Request.Callback()
    {
      public void onCompleted(Response paramAnonymousResponse)
      {
        AppEventsLogger.handleResponse(this.val$accessTokenAppId, localRequest, paramAnonymousResponse, paramSessionEventsState, paramFlushStatistics);
      }
    });
    return localRequest;
  }
  
  public static void deactivateApp(Context paramContext)
  {
    deactivateApp(paramContext, Utility.getMetadataApplicationId(paramContext));
  }
  
  public static void deactivateApp(Context paramContext, String paramString)
  {
    if ((paramContext == null) || (paramString == null)) {
      throw new IllegalArgumentException("Both context and applicationId must be non-null");
    }
    resetSourceApplication();
    AppEventsLogger localAppEventsLogger = new AppEventsLogger(paramContext, paramString, null);
    final long l = System.currentTimeMillis();
    backgroundExecutor.execute(new Runnable()
    {
      public void run()
      {
        this.val$logger.logAppSessionSuspendEvent(l);
      }
    });
  }
  
  static void eagerFlush()
  {
    if (getFlushBehavior() != FlushBehavior.EXPLICIT_ONLY) {
      flush(FlushReason.EAGER_FLUSHING_EVENT);
    }
  }
  
  private static void flush(FlushReason paramFlushReason)
  {
    Settings.getExecutor().execute(new Runnable()
    {
      public void run()
      {
        AppEventsLogger.flushAndWait(this.val$reason);
      }
    });
  }
  
  private static void flushAndWait(FlushReason paramFlushReason)
  {
    HashSet localHashSet;
    synchronized (staticLock)
    {
      if (requestInFlight) {
        return;
      }
      requestInFlight = true;
      localHashSet = new HashSet(stateMap.keySet());
      accumulatePersistedEvents();
    }
    try
    {
      FlushStatistics localFlushStatistics2 = buildAndExecuteRequests(paramFlushReason, localHashSet);
      FlushStatistics localFlushStatistics1 = localFlushStatistics2;
      Intent localIntent;
      return;
    }
    catch (Exception localException)
    {
      synchronized (staticLock)
      {
        requestInFlight = false;
        if (localFlushStatistics1 != null)
        {
          localIntent = new Intent("com.facebook.sdk.APP_EVENTS_FLUSHED");
          localIntent.putExtra("com.facebook.sdk.APP_EVENTS_NUM_EVENTS_FLUSHED", localFlushStatistics1.numEvents);
          localIntent.putExtra("com.facebook.sdk.APP_EVENTS_FLUSH_RESULT", localFlushStatistics1.result);
          LocalBroadcastManager.getInstance(applicationContext).sendBroadcast(localIntent);
          return;
          localObject2 = finally;
          throw localObject2;
          localException = localException;
          Log.d(TAG, "Caught unexpected exception while flushing: " + localException.toString());
          localFlushStatistics1 = null;
        }
      }
    }
  }
  
  private static void flushIfNecessary()
  {
    synchronized (staticLock)
    {
      if ((getFlushBehavior() != FlushBehavior.EXPLICIT_ONLY) && (getAccumulatedEventCount() > 100)) {
        flush(FlushReason.EVENT_THRESHOLD);
      }
      return;
    }
  }
  
  private static int getAccumulatedEventCount()
  {
    Object localObject1 = staticLock;
    int i = 0;
    try
    {
      Iterator localIterator = stateMap.values().iterator();
      while (localIterator.hasNext()) {
        i += ((SessionEventsState)localIterator.next()).getAccumulatedEventCount();
      }
      return i;
    }
    finally {}
  }
  
  public static FlushBehavior getFlushBehavior()
  {
    synchronized (staticLock)
    {
      FlushBehavior localFlushBehavior = flushBehavior;
      return localFlushBehavior;
    }
  }
  
  @Deprecated
  public static boolean getLimitEventUsage(Context paramContext)
  {
    return Settings.getLimitEventAndDataUsage(paramContext);
  }
  
  private static SessionEventsState getSessionEventsState(Context paramContext, AccessTokenAppIdPair paramAccessTokenAppIdPair)
  {
    SessionEventsState localSessionEventsState1 = (SessionEventsState)stateMap.get(paramAccessTokenAppIdPair);
    AttributionIdentifiers localAttributionIdentifiers = null;
    if (localSessionEventsState1 == null) {
      localAttributionIdentifiers = AttributionIdentifiers.getAttributionIdentifiers(paramContext);
    }
    synchronized (staticLock)
    {
      Object localObject5 = (SessionEventsState)stateMap.get(paramAccessTokenAppIdPair);
      SessionEventsState localSessionEventsState2;
      if (localObject5 == null) {
        localSessionEventsState2 = new SessionEventsState(localAttributionIdentifiers, paramContext.getPackageName(), hashedDeviceAndAppId);
      }
      Object localObject2;
      try
      {
        stateMap.put(paramAccessTokenAppIdPair, localSessionEventsState2);
        localObject5 = localSessionEventsState2;
        return localObject5;
      }
      finally {}
      throw localObject2;
    }
  }
  
  private static SessionEventsState getSessionEventsState(AccessTokenAppIdPair paramAccessTokenAppIdPair)
  {
    synchronized (staticLock)
    {
      SessionEventsState localSessionEventsState = (SessionEventsState)stateMap.get(paramAccessTokenAppIdPair);
      return localSessionEventsState;
    }
  }
  
  static String getSourceApplication()
  {
    String str = "Unclassified";
    if (isOpenedByApplink) {
      str = "Applink";
    }
    if (sourceApplication != null) {
      str = str + "(" + sourceApplication + ")";
    }
    return str;
  }
  
  private static void handleResponse(AccessTokenAppIdPair paramAccessTokenAppIdPair, Request paramRequest, Response paramResponse, SessionEventsState paramSessionEventsState, FlushStatistics paramFlushStatistics)
  {
    FacebookRequestError localFacebookRequestError = paramResponse.getError();
    String str1 = "Success";
    FlushResult localFlushResult = FlushResult.SUCCESS;
    if (localFacebookRequestError != null)
    {
      if (localFacebookRequestError.getErrorCode() != -1) {
        break label185;
      }
      str1 = "Failed: No Connectivity";
    }
    for (localFlushResult = FlushResult.NO_CONNECTIVITY;; localFlushResult = FlushResult.SERVER_ERROR)
    {
      String str2;
      if (Settings.isLoggingBehaviorEnabled(LoggingBehavior.APP_EVENTS)) {
        str2 = (String)paramRequest.getTag();
      }
      try
      {
        String str5 = new JSONArray(str2).toString(2);
        str3 = str5;
      }
      catch (JSONException localJSONException)
      {
        for (;;)
        {
          LoggingBehavior localLoggingBehavior;
          String str4;
          Object[] arrayOfObject1;
          Object[] arrayOfObject2;
          String str3 = "<Can't encode events for debug logging>";
          continue;
          boolean bool = false;
        }
      }
      localLoggingBehavior = LoggingBehavior.APP_EVENTS;
      str4 = TAG;
      arrayOfObject1 = new Object[3];
      arrayOfObject1[0] = paramRequest.getGraphObject().toString();
      arrayOfObject1[1] = str1;
      arrayOfObject1[2] = str3;
      Logger.log(localLoggingBehavior, str4, "Flush completed\nParams: %s\n  Result: %s\n  Events JSON: %s", arrayOfObject1);
      if (localFacebookRequestError == null) {
        break;
      }
      bool = true;
      paramSessionEventsState.clearInFlightAndStats(bool);
      if (localFlushResult == FlushResult.NO_CONNECTIVITY) {
        PersistedEvents.persistEvents(applicationContext, paramAccessTokenAppIdPair, paramSessionEventsState);
      }
      if ((localFlushResult != FlushResult.SUCCESS) && (paramFlushStatistics.result != FlushResult.NO_CONNECTIVITY)) {
        paramFlushStatistics.result = localFlushResult;
      }
      return;
      label185:
      arrayOfObject2 = new Object[2];
      arrayOfObject2[0] = paramResponse.toString();
      arrayOfObject2[1] = localFacebookRequestError.toString();
      str1 = String.format("Failed:\n  Response: %s\n  Error %s", arrayOfObject2);
    }
  }
  
  private static void initializeTimersIfNeeded()
  {
    synchronized (staticLock)
    {
      if (backgroundExecutor != null) {
        return;
      }
      backgroundExecutor = new ScheduledThreadPoolExecutor(1);
      Runnable local3 = new Runnable()
      {
        public void run()
        {
          if (AppEventsLogger.getFlushBehavior() != AppEventsLogger.FlushBehavior.EXPLICIT_ONLY) {
            AppEventsLogger.flushAndWait(AppEventsLogger.FlushReason.TIMER);
          }
        }
      };
      backgroundExecutor.scheduleAtFixedRate(local3, 0L, 60L, TimeUnit.SECONDS);
      Runnable local4 = new Runnable()
      {
        public void run()
        {
          HashSet localHashSet = new HashSet();
          synchronized (AppEventsLogger.staticLock)
          {
            Iterator localIterator1 = AppEventsLogger.stateMap.keySet().iterator();
            if (localIterator1.hasNext()) {
              localHashSet.add(((AppEventsLogger.AccessTokenAppIdPair)localIterator1.next()).getApplicationId());
            }
          }
          Iterator localIterator2 = localHashSet.iterator();
          while (localIterator2.hasNext()) {
            Utility.queryAppSettings((String)localIterator2.next(), true);
          }
        }
      };
      backgroundExecutor.scheduleAtFixedRate(local4, 0L, 86400L, TimeUnit.SECONDS);
      return;
    }
  }
  
  private void logAppSessionResumeEvent(long paramLong, String paramString)
  {
    PersistedAppSessionInfo.onResume(applicationContext, this.accessTokenAppId, this, paramLong, paramString);
  }
  
  private void logAppSessionSuspendEvent(long paramLong)
  {
    PersistedAppSessionInfo.onSuspend(applicationContext, this.accessTokenAppId, this, paramLong);
  }
  
  private static void logEvent(Context paramContext, final AppEvent paramAppEvent, final AccessTokenAppIdPair paramAccessTokenAppIdPair)
  {
    Settings.getExecutor().execute(new Runnable()
    {
      public void run()
      {
        AppEventsLogger.getSessionEventsState(this.val$context, paramAccessTokenAppIdPair).addEvent(paramAppEvent);
        AppEventsLogger.access$700();
      }
    });
  }
  
  private void logEvent(String paramString, Double paramDouble, Bundle paramBundle, boolean paramBoolean)
  {
    AppEvent localAppEvent = new AppEvent(this.context, paramString, paramDouble, paramBundle, paramBoolean);
    logEvent(this.context, localAppEvent, this.accessTokenAppId);
  }
  
  public static AppEventsLogger newLogger(Context paramContext)
  {
    return new AppEventsLogger(paramContext, null, null);
  }
  
  public static AppEventsLogger newLogger(Context paramContext, Session paramSession)
  {
    return new AppEventsLogger(paramContext, null, paramSession);
  }
  
  public static AppEventsLogger newLogger(Context paramContext, String paramString)
  {
    return new AppEventsLogger(paramContext, paramString, null);
  }
  
  public static AppEventsLogger newLogger(Context paramContext, String paramString, Session paramSession)
  {
    return new AppEventsLogger(paramContext, paramString, paramSession);
  }
  
  private static void notifyDeveloperError(String paramString)
  {
    Logger.log(LoggingBehavior.DEVELOPER_ERRORS, "AppEvents", paramString);
  }
  
  public static void onContextStop()
  {
    PersistedEvents.persistEvents(applicationContext, stateMap);
  }
  
  static void resetSourceApplication()
  {
    sourceApplication = null;
    isOpenedByApplink = false;
  }
  
  public static void setFlushBehavior(FlushBehavior paramFlushBehavior)
  {
    synchronized (staticLock)
    {
      flushBehavior = paramFlushBehavior;
      return;
    }
  }
  
  @Deprecated
  public static void setLimitEventUsage(Context paramContext, boolean paramBoolean)
  {
    Settings.setLimitEventAndDataUsage(paramContext, paramBoolean);
  }
  
  private static void setSourceApplication(Activity paramActivity)
  {
    ComponentName localComponentName = paramActivity.getCallingActivity();
    if (localComponentName != null)
    {
      String str = localComponentName.getPackageName();
      if (str.equals(paramActivity.getPackageName()))
      {
        resetSourceApplication();
        return;
      }
      sourceApplication = str;
    }
    Intent localIntent = paramActivity.getIntent();
    if ((localIntent == null) || (localIntent.getBooleanExtra("_fbSourceApplicationHasBeenSet", false)))
    {
      resetSourceApplication();
      return;
    }
    Bundle localBundle1 = AppLinks.getAppLinkData(localIntent);
    if (localBundle1 == null)
    {
      resetSourceApplication();
      return;
    }
    isOpenedByApplink = true;
    Bundle localBundle2 = localBundle1.getBundle("referer_app_link");
    if (localBundle2 == null)
    {
      sourceApplication = null;
      return;
    }
    sourceApplication = localBundle2.getString("package");
    localIntent.putExtra("_fbSourceApplicationHasBeenSet", true);
  }
  
  static void setSourceApplication(String paramString, boolean paramBoolean)
  {
    sourceApplication = paramString;
    isOpenedByApplink = paramBoolean;
  }
  
  public void flush()
  {
    flush(FlushReason.EXPLICIT);
  }
  
  public String getApplicationId()
  {
    return this.accessTokenAppId.getApplicationId();
  }
  
  boolean isValidForSession(Session paramSession)
  {
    AccessTokenAppIdPair localAccessTokenAppIdPair = new AccessTokenAppIdPair(paramSession);
    return this.accessTokenAppId.equals(localAccessTokenAppIdPair);
  }
  
  public void logEvent(String paramString)
  {
    logEvent(paramString, null);
  }
  
  public void logEvent(String paramString, double paramDouble)
  {
    logEvent(paramString, paramDouble, null);
  }
  
  public void logEvent(String paramString, double paramDouble, Bundle paramBundle)
  {
    logEvent(paramString, Double.valueOf(paramDouble), paramBundle, false);
  }
  
  public void logEvent(String paramString, Bundle paramBundle)
  {
    logEvent(paramString, null, paramBundle, false);
  }
  
  public void logPurchase(BigDecimal paramBigDecimal, Currency paramCurrency)
  {
    logPurchase(paramBigDecimal, paramCurrency, null);
  }
  
  public void logPurchase(BigDecimal paramBigDecimal, Currency paramCurrency, Bundle paramBundle)
  {
    if (paramBigDecimal == null)
    {
      notifyDeveloperError("purchaseAmount cannot be null");
      return;
    }
    if (paramCurrency == null)
    {
      notifyDeveloperError("currency cannot be null");
      return;
    }
    if (paramBundle == null) {
      paramBundle = new Bundle();
    }
    paramBundle.putString("fb_currency", paramCurrency.getCurrencyCode());
    logEvent("fb_mobile_purchase", paramBigDecimal.doubleValue(), paramBundle);
    eagerFlush();
  }
  
  public void logSdkEvent(String paramString, Double paramDouble, Bundle paramBundle)
  {
    logEvent(paramString, paramDouble, paramBundle, true);
  }
  
  private static class AccessTokenAppIdPair
    implements Serializable
  {
    private static final long serialVersionUID = 1L;
    private final String accessToken;
    private final String applicationId;
    
    AccessTokenAppIdPair(Session paramSession)
    {
      this(paramSession.getAccessToken(), paramSession.getApplicationId());
    }
    
    AccessTokenAppIdPair(String paramString1, String paramString2)
    {
      if (Utility.isNullOrEmpty(paramString1)) {
        paramString1 = null;
      }
      this.accessToken = paramString1;
      this.applicationId = paramString2;
    }
    
    private Object writeReplace()
    {
      return new SerializationProxyV1(this.accessToken, this.applicationId, null);
    }
    
    public boolean equals(Object paramObject)
    {
      if (!(paramObject instanceof AccessTokenAppIdPair)) {}
      AccessTokenAppIdPair localAccessTokenAppIdPair;
      do
      {
        return false;
        localAccessTokenAppIdPair = (AccessTokenAppIdPair)paramObject;
      } while ((!Utility.areObjectsEqual(localAccessTokenAppIdPair.accessToken, this.accessToken)) || (!Utility.areObjectsEqual(localAccessTokenAppIdPair.applicationId, this.applicationId)));
      return true;
    }
    
    String getAccessToken()
    {
      return this.accessToken;
    }
    
    String getApplicationId()
    {
      return this.applicationId;
    }
    
    public int hashCode()
    {
      int i;
      int j;
      if (this.accessToken == null)
      {
        i = 0;
        String str = this.applicationId;
        j = 0;
        if (str != null) {
          break label35;
        }
      }
      for (;;)
      {
        return i ^ j;
        i = this.accessToken.hashCode();
        break;
        label35:
        j = this.applicationId.hashCode();
      }
    }
    
    private static class SerializationProxyV1
      implements Serializable
    {
      private static final long serialVersionUID = -2488473066578201069L;
      private final String accessToken;
      private final String appId;
      
      private SerializationProxyV1(String paramString1, String paramString2)
      {
        this.accessToken = paramString1;
        this.appId = paramString2;
      }
      
      private Object readResolve()
      {
        return new AppEventsLogger.AccessTokenAppIdPair(this.accessToken, this.appId);
      }
    }
  }
  
  static class AppEvent
    implements Serializable
  {
    private static final long serialVersionUID = 1L;
    private static final HashSet<String> validatedIdentifiers = new HashSet();
    private boolean isImplicit;
    private JSONObject jsonObject;
    private String name;
    
    public AppEvent(Context paramContext, String paramString, Double paramDouble, Bundle paramBundle, boolean paramBoolean)
    {
      try
      {
        validateIdentifier(paramString);
        this.name = paramString;
        this.isImplicit = paramBoolean;
        this.jsonObject = new JSONObject();
        this.jsonObject.put("_eventName", paramString);
        this.jsonObject.put("_logTime", System.currentTimeMillis() / 1000L);
        this.jsonObject.put("_ui", Utility.getActivityName(paramContext));
        if (paramDouble != null) {
          this.jsonObject.put("_valueToSum", paramDouble.doubleValue());
        }
        if (this.isImplicit) {
          this.jsonObject.put("_implicitlyLogged", "1");
        }
        String str1 = Settings.getAppVersion();
        if (str1 != null) {
          this.jsonObject.put("_appVersion", str1);
        }
        if (paramBundle == null) {
          break label325;
        }
        localIterator = paramBundle.keySet().iterator();
      }
      catch (JSONException localJSONException)
      {
        for (;;)
        {
          Iterator localIterator;
          String str2;
          Object localObject;
          LoggingBehavior localLoggingBehavior2 = LoggingBehavior.APP_EVENTS;
          Object[] arrayOfObject2 = new Object[1];
          arrayOfObject2[0] = localJSONException.toString();
          Logger.log(localLoggingBehavior2, "AppEvents", "JSON encoding for app event failed: '%s'", arrayOfObject2);
          this.jsonObject = null;
          return;
          this.jsonObject.put(str2, localObject.toString());
        }
      }
      catch (FacebookException localFacebookException)
      {
        LoggingBehavior localLoggingBehavior1 = LoggingBehavior.APP_EVENTS;
        Object[] arrayOfObject1 = new Object[1];
        arrayOfObject1[0] = localFacebookException.toString();
        Logger.log(localLoggingBehavior1, "AppEvents", "Invalid app event name or parameter:", arrayOfObject1);
        this.jsonObject = null;
        return;
      }
      if (localIterator.hasNext())
      {
        str2 = (String)localIterator.next();
        validateIdentifier(str2);
        localObject = paramBundle.get(str2);
        if ((!(localObject instanceof String)) && (!(localObject instanceof Number))) {
          throw new FacebookException(String.format("Parameter value '%s' for key '%s' should be a string or a numeric type.", new Object[] { localObject, str2 }));
        }
      }
      label325:
      while (this.isImplicit) {}
      LoggingBehavior localLoggingBehavior3 = LoggingBehavior.APP_EVENTS;
      Object[] arrayOfObject3 = new Object[1];
      arrayOfObject3[0] = this.jsonObject.toString();
      Logger.log(localLoggingBehavior3, "AppEvents", "Created app event '%s'", arrayOfObject3);
    }
    
    private AppEvent(String paramString, boolean paramBoolean)
      throws JSONException
    {
      this.jsonObject = new JSONObject(paramString);
      this.isImplicit = paramBoolean;
    }
    
    private void validateIdentifier(String paramString)
      throws FacebookException
    {
      if ((paramString == null) || (paramString.length() == 0) || (paramString.length() > 40))
      {
        if (paramString == null) {
          paramString = "<None Provided>";
        }
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = paramString;
        arrayOfObject[1] = Integer.valueOf(40);
        throw new FacebookException(String.format("Identifier '%s' must be less than %d characters", arrayOfObject));
      }
      synchronized (validatedIdentifiers)
      {
        boolean bool = validatedIdentifiers.contains(paramString);
        if (!bool) {
          if (!paramString.matches("^[0-9a-zA-Z_]+[0-9a-zA-Z _-]*$")) {
            break label124;
          }
        }
      }
      synchronized (validatedIdentifiers)
      {
        validatedIdentifiers.add(paramString);
        return;
        localObject1 = finally;
        throw localObject1;
      }
      label124:
      throw new FacebookException(String.format("Skipping event named '%s' due to illegal name - must be under 40 chars and alphanumeric, _, - or space, and not start with a space or hyphen.", new Object[] { paramString }));
    }
    
    private Object writeReplace()
    {
      return new SerializationProxyV1(this.jsonObject.toString(), this.isImplicit, null);
    }
    
    public boolean getIsImplicit()
    {
      return this.isImplicit;
    }
    
    public JSONObject getJSONObject()
    {
      return this.jsonObject;
    }
    
    public String getName()
    {
      return this.name;
    }
    
    public String toString()
    {
      Object[] arrayOfObject = new Object[3];
      arrayOfObject[0] = this.jsonObject.optString("_eventName");
      arrayOfObject[1] = Boolean.valueOf(this.isImplicit);
      arrayOfObject[2] = this.jsonObject.toString();
      return String.format("\"%s\", implicit: %b, json: %s", arrayOfObject);
    }
    
    private static class SerializationProxyV1
      implements Serializable
    {
      private static final long serialVersionUID = -2488473066578201069L;
      private final boolean isImplicit;
      private final String jsonString;
      
      private SerializationProxyV1(String paramString, boolean paramBoolean)
      {
        this.jsonString = paramString;
        this.isImplicit = paramBoolean;
      }
      
      private Object readResolve()
        throws JSONException
      {
        return new AppEventsLogger.AppEvent(this.jsonString, this.isImplicit, null);
      }
    }
  }
  
  public static enum FlushBehavior
  {
    static
    {
      FlushBehavior[] arrayOfFlushBehavior = new FlushBehavior[2];
      arrayOfFlushBehavior[0] = AUTO;
      arrayOfFlushBehavior[1] = EXPLICIT_ONLY;
      $VALUES = arrayOfFlushBehavior;
    }
    
    private FlushBehavior() {}
  }
  
  private static enum FlushReason
  {
    static
    {
      SESSION_CHANGE = new FlushReason("SESSION_CHANGE", 2);
      PERSISTED_EVENTS = new FlushReason("PERSISTED_EVENTS", 3);
      EVENT_THRESHOLD = new FlushReason("EVENT_THRESHOLD", 4);
      EAGER_FLUSHING_EVENT = new FlushReason("EAGER_FLUSHING_EVENT", 5);
      FlushReason[] arrayOfFlushReason = new FlushReason[6];
      arrayOfFlushReason[0] = EXPLICIT;
      arrayOfFlushReason[1] = TIMER;
      arrayOfFlushReason[2] = SESSION_CHANGE;
      arrayOfFlushReason[3] = PERSISTED_EVENTS;
      arrayOfFlushReason[4] = EVENT_THRESHOLD;
      arrayOfFlushReason[5] = EAGER_FLUSHING_EVENT;
      $VALUES = arrayOfFlushReason;
    }
    
    private FlushReason() {}
  }
  
  private static enum FlushResult
  {
    static
    {
      SERVER_ERROR = new FlushResult("SERVER_ERROR", 1);
      NO_CONNECTIVITY = new FlushResult("NO_CONNECTIVITY", 2);
      UNKNOWN_ERROR = new FlushResult("UNKNOWN_ERROR", 3);
      FlushResult[] arrayOfFlushResult = new FlushResult[4];
      arrayOfFlushResult[0] = SUCCESS;
      arrayOfFlushResult[1] = SERVER_ERROR;
      arrayOfFlushResult[2] = NO_CONNECTIVITY;
      arrayOfFlushResult[3] = UNKNOWN_ERROR;
      $VALUES = arrayOfFlushResult;
    }
    
    private FlushResult() {}
  }
  
  private static class FlushStatistics
  {
    public int numEvents = 0;
    public AppEventsLogger.FlushResult result = AppEventsLogger.FlushResult.SUCCESS;
  }
  
  static class PersistedAppSessionInfo
  {
    private static final String PERSISTED_SESSION_INFO_FILENAME = "AppEventsLogger.persistedsessioninfo";
    private static final Runnable appSessionInfoFlushRunnable = new Runnable()
    {
      public void run()
      {
        AppEventsLogger.PersistedAppSessionInfo.saveAppSessionInformation(AppEventsLogger.applicationContext);
      }
    };
    private static Map<AppEventsLogger.AccessTokenAppIdPair, FacebookTimeSpentData> appSessionInfoMap;
    private static boolean hasChanges;
    private static boolean isLoaded;
    private static final Object staticLock = new Object();
    
    static
    {
      hasChanges = false;
      isLoaded = false;
    }
    
    private static FacebookTimeSpentData getTimeSpentData(Context paramContext, AppEventsLogger.AccessTokenAppIdPair paramAccessTokenAppIdPair)
    {
      restoreAppSessionInformation(paramContext);
      FacebookTimeSpentData localFacebookTimeSpentData = (FacebookTimeSpentData)appSessionInfoMap.get(paramAccessTokenAppIdPair);
      if (localFacebookTimeSpentData == null)
      {
        localFacebookTimeSpentData = new FacebookTimeSpentData();
        appSessionInfoMap.put(paramAccessTokenAppIdPair, localFacebookTimeSpentData);
      }
      return localFacebookTimeSpentData;
    }
    
    static void onResume(Context paramContext, AppEventsLogger.AccessTokenAppIdPair paramAccessTokenAppIdPair, AppEventsLogger paramAppEventsLogger, long paramLong, String paramString)
    {
      synchronized (staticLock)
      {
        getTimeSpentData(paramContext, paramAccessTokenAppIdPair).onResume(paramAppEventsLogger, paramLong, paramString);
        onTimeSpentDataUpdate();
        return;
      }
    }
    
    static void onSuspend(Context paramContext, AppEventsLogger.AccessTokenAppIdPair paramAccessTokenAppIdPair, AppEventsLogger paramAppEventsLogger, long paramLong)
    {
      synchronized (staticLock)
      {
        getTimeSpentData(paramContext, paramAccessTokenAppIdPair).onSuspend(paramAppEventsLogger, paramLong);
        onTimeSpentDataUpdate();
        return;
      }
    }
    
    private static void onTimeSpentDataUpdate()
    {
      if (!hasChanges)
      {
        hasChanges = true;
        AppEventsLogger.backgroundExecutor.schedule(appSessionInfoFlushRunnable, 30L, TimeUnit.SECONDS);
      }
    }
    
    /* Error */
    private static void restoreAppSessionInformation(Context paramContext)
    {
      // Byte code:
      //   0: aconst_null
      //   1: astore_1
      //   2: getstatic 25	com/facebook/AppEventsLogger$PersistedAppSessionInfo:staticLock	Ljava/lang/Object;
      //   5: astore_2
      //   6: aload_2
      //   7: monitorenter
      //   8: getstatic 29	com/facebook/AppEventsLogger$PersistedAppSessionInfo:isLoaded	Z
      //   11: istore 4
      //   13: iload 4
      //   15: ifne +75 -> 90
      //   18: new 96	java/io/ObjectInputStream
      //   21: dup
      //   22: aload_0
      //   23: ldc 8
      //   25: invokevirtual 102	android/content/Context:openFileInput	(Ljava/lang/String;)Ljava/io/FileInputStream;
      //   28: invokespecial 105	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
      //   31: astore 5
      //   33: aload 5
      //   35: invokevirtual 109	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
      //   38: checkcast 111	java/util/HashMap
      //   41: putstatic 42	com/facebook/AppEventsLogger$PersistedAppSessionInfo:appSessionInfoMap	Ljava/util/Map;
      //   44: getstatic 117	com/facebook/LoggingBehavior:APP_EVENTS	Lcom/facebook/LoggingBehavior;
      //   47: ldc 119
      //   49: ldc 121
      //   51: invokestatic 127	com/facebook/internal/Logger:log	(Lcom/facebook/LoggingBehavior;Ljava/lang/String;Ljava/lang/String;)V
      //   54: aload 5
      //   56: invokestatic 133	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
      //   59: aload_0
      //   60: ldc 8
      //   62: invokevirtual 137	android/content/Context:deleteFile	(Ljava/lang/String;)Z
      //   65: pop
      //   66: getstatic 42	com/facebook/AppEventsLogger$PersistedAppSessionInfo:appSessionInfoMap	Ljava/util/Map;
      //   69: ifnonnull +13 -> 82
      //   72: new 111	java/util/HashMap
      //   75: dup
      //   76: invokespecial 138	java/util/HashMap:<init>	()V
      //   79: putstatic 42	com/facebook/AppEventsLogger$PersistedAppSessionInfo:appSessionInfoMap	Ljava/util/Map;
      //   82: iconst_1
      //   83: putstatic 29	com/facebook/AppEventsLogger$PersistedAppSessionInfo:isLoaded	Z
      //   86: iconst_0
      //   87: putstatic 27	com/facebook/AppEventsLogger$PersistedAppSessionInfo:hasChanges	Z
      //   90: aload_2
      //   91: monitorexit
      //   92: return
      //   93: aload 7
      //   95: invokestatic 133	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
      //   98: aload_0
      //   99: ldc 8
      //   101: invokevirtual 137	android/content/Context:deleteFile	(Ljava/lang/String;)Z
      //   104: pop
      //   105: getstatic 42	com/facebook/AppEventsLogger$PersistedAppSessionInfo:appSessionInfoMap	Ljava/util/Map;
      //   108: ifnonnull +13 -> 121
      //   111: new 111	java/util/HashMap
      //   114: dup
      //   115: invokespecial 138	java/util/HashMap:<init>	()V
      //   118: putstatic 42	com/facebook/AppEventsLogger$PersistedAppSessionInfo:appSessionInfoMap	Ljava/util/Map;
      //   121: iconst_1
      //   122: putstatic 29	com/facebook/AppEventsLogger$PersistedAppSessionInfo:isLoaded	Z
      //   125: iconst_0
      //   126: putstatic 27	com/facebook/AppEventsLogger$PersistedAppSessionInfo:hasChanges	Z
      //   129: goto -39 -> 90
      //   132: aload_2
      //   133: monitorexit
      //   134: aload_3
      //   135: athrow
      //   136: astore 9
      //   138: invokestatic 142	com/facebook/AppEventsLogger:access$1300	()Ljava/lang/String;
      //   141: new 144	java/lang/StringBuilder
      //   144: dup
      //   145: invokespecial 145	java/lang/StringBuilder:<init>	()V
      //   148: ldc 147
      //   150: invokevirtual 151	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   153: aload 9
      //   155: invokevirtual 154	java/lang/Exception:toString	()Ljava/lang/String;
      //   158: invokevirtual 151	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   161: invokevirtual 155	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   164: invokestatic 161	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
      //   167: pop
      //   168: aload_1
      //   169: invokestatic 133	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
      //   172: aload_0
      //   173: ldc 8
      //   175: invokevirtual 137	android/content/Context:deleteFile	(Ljava/lang/String;)Z
      //   178: pop
      //   179: getstatic 42	com/facebook/AppEventsLogger$PersistedAppSessionInfo:appSessionInfoMap	Ljava/util/Map;
      //   182: ifnonnull +13 -> 195
      //   185: new 111	java/util/HashMap
      //   188: dup
      //   189: invokespecial 138	java/util/HashMap:<init>	()V
      //   192: putstatic 42	com/facebook/AppEventsLogger$PersistedAppSessionInfo:appSessionInfoMap	Ljava/util/Map;
      //   195: iconst_1
      //   196: putstatic 29	com/facebook/AppEventsLogger$PersistedAppSessionInfo:isLoaded	Z
      //   199: iconst_0
      //   200: putstatic 27	com/facebook/AppEventsLogger$PersistedAppSessionInfo:hasChanges	Z
      //   203: goto -113 -> 90
      //   206: aload_1
      //   207: invokestatic 133	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
      //   210: aload_0
      //   211: ldc 8
      //   213: invokevirtual 137	android/content/Context:deleteFile	(Ljava/lang/String;)Z
      //   216: pop
      //   217: getstatic 42	com/facebook/AppEventsLogger$PersistedAppSessionInfo:appSessionInfoMap	Ljava/util/Map;
      //   220: ifnonnull +13 -> 233
      //   223: new 111	java/util/HashMap
      //   226: dup
      //   227: invokespecial 138	java/util/HashMap:<init>	()V
      //   230: putstatic 42	com/facebook/AppEventsLogger$PersistedAppSessionInfo:appSessionInfoMap	Ljava/util/Map;
      //   233: iconst_1
      //   234: putstatic 29	com/facebook/AppEventsLogger$PersistedAppSessionInfo:isLoaded	Z
      //   237: iconst_0
      //   238: putstatic 27	com/facebook/AppEventsLogger$PersistedAppSessionInfo:hasChanges	Z
      //   241: aload 10
      //   243: athrow
      //   244: astore_3
      //   245: goto -113 -> 132
      //   248: astore 10
      //   250: aload 5
      //   252: astore_1
      //   253: goto -47 -> 206
      //   256: astore 9
      //   258: aload 5
      //   260: astore_1
      //   261: goto -123 -> 138
      //   264: astore 6
      //   266: aload 5
      //   268: astore 7
      //   270: goto -177 -> 93
      //   273: astore 15
      //   275: aconst_null
      //   276: astore 7
      //   278: goto -185 -> 93
      //   281: astore_3
      //   282: goto -150 -> 132
      //   285: astore 10
      //   287: goto -81 -> 206
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	290	0	paramContext	Context
      //   1	260	1	localObject1	Object
      //   5	128	2	localObject2	Object
      //   134	1	3	localObject3	Object
      //   244	1	3	localObject4	Object
      //   281	1	3	localObject5	Object
      //   11	3	4	bool	boolean
      //   31	236	5	localObjectInputStream	java.io.ObjectInputStream
      //   264	1	6	localFileNotFoundException1	java.io.FileNotFoundException
      //   93	184	7	localObject6	Object
      //   136	18	9	localException1	Exception
      //   256	1	9	localException2	Exception
      //   241	1	10	localObject7	Object
      //   248	1	10	localObject8	Object
      //   285	1	10	localObject9	Object
      //   273	1	15	localFileNotFoundException2	java.io.FileNotFoundException
      // Exception table:
      //   from	to	target	type
      //   18	33	136	java/lang/Exception
      //   54	82	244	finally
      //   82	90	244	finally
      //   33	54	248	finally
      //   33	54	256	java/lang/Exception
      //   33	54	264	java/io/FileNotFoundException
      //   18	33	273	java/io/FileNotFoundException
      //   8	13	281	finally
      //   90	92	281	finally
      //   93	121	281	finally
      //   121	129	281	finally
      //   132	134	281	finally
      //   168	195	281	finally
      //   195	203	281	finally
      //   206	233	281	finally
      //   233	244	281	finally
      //   18	33	285	finally
      //   138	168	285	finally
    }
    
    /* Error */
    static void saveAppSessionInformation(Context paramContext)
    {
      // Byte code:
      //   0: aconst_null
      //   1: astore_1
      //   2: getstatic 25	com/facebook/AppEventsLogger$PersistedAppSessionInfo:staticLock	Ljava/lang/Object;
      //   5: astore_2
      //   6: aload_2
      //   7: monitorenter
      //   8: getstatic 27	com/facebook/AppEventsLogger$PersistedAppSessionInfo:hasChanges	Z
      //   11: istore 4
      //   13: iload 4
      //   15: ifeq +53 -> 68
      //   18: new 164	java/io/ObjectOutputStream
      //   21: dup
      //   22: new 166	java/io/BufferedOutputStream
      //   25: dup
      //   26: aload_0
      //   27: ldc 8
      //   29: iconst_0
      //   30: invokevirtual 170	android/content/Context:openFileOutput	(Ljava/lang/String;I)Ljava/io/FileOutputStream;
      //   33: invokespecial 173	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;)V
      //   36: invokespecial 174	java/io/ObjectOutputStream:<init>	(Ljava/io/OutputStream;)V
      //   39: astore 5
      //   41: aload 5
      //   43: getstatic 42	com/facebook/AppEventsLogger$PersistedAppSessionInfo:appSessionInfoMap	Ljava/util/Map;
      //   46: invokevirtual 178	java/io/ObjectOutputStream:writeObject	(Ljava/lang/Object;)V
      //   49: iconst_0
      //   50: putstatic 27	com/facebook/AppEventsLogger$PersistedAppSessionInfo:hasChanges	Z
      //   53: getstatic 117	com/facebook/LoggingBehavior:APP_EVENTS	Lcom/facebook/LoggingBehavior;
      //   56: ldc 119
      //   58: ldc 180
      //   60: invokestatic 127	com/facebook/internal/Logger:log	(Lcom/facebook/LoggingBehavior;Ljava/lang/String;Ljava/lang/String;)V
      //   63: aload 5
      //   65: invokestatic 133	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
      //   68: aload_2
      //   69: monitorexit
      //   70: return
      //   71: astore 6
      //   73: invokestatic 142	com/facebook/AppEventsLogger:access$1300	()Ljava/lang/String;
      //   76: new 144	java/lang/StringBuilder
      //   79: dup
      //   80: invokespecial 145	java/lang/StringBuilder:<init>	()V
      //   83: ldc 147
      //   85: invokevirtual 151	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   88: aload 6
      //   90: invokevirtual 154	java/lang/Exception:toString	()Ljava/lang/String;
      //   93: invokevirtual 151	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   96: invokevirtual 155	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   99: invokestatic 161	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
      //   102: pop
      //   103: aload_1
      //   104: invokestatic 133	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
      //   107: goto -39 -> 68
      //   110: aload_2
      //   111: monitorexit
      //   112: aload_3
      //   113: athrow
      //   114: astore 7
      //   116: aload_1
      //   117: invokestatic 133	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
      //   120: aload 7
      //   122: athrow
      //   123: astore_3
      //   124: goto -14 -> 110
      //   127: astore 7
      //   129: aload 5
      //   131: astore_1
      //   132: goto -16 -> 116
      //   135: astore 6
      //   137: aload 5
      //   139: astore_1
      //   140: goto -67 -> 73
      //   143: astore_3
      //   144: goto -34 -> 110
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	147	0	paramContext	Context
      //   1	139	1	localObject1	Object
      //   5	106	2	localObject2	Object
      //   112	1	3	localObject3	Object
      //   123	1	3	localObject4	Object
      //   143	1	3	localObject5	Object
      //   11	3	4	bool	boolean
      //   39	99	5	localObjectOutputStream	java.io.ObjectOutputStream
      //   71	18	6	localException1	Exception
      //   135	1	6	localException2	Exception
      //   114	7	7	localObject6	Object
      //   127	1	7	localObject7	Object
      // Exception table:
      //   from	to	target	type
      //   18	41	71	java/lang/Exception
      //   18	41	114	finally
      //   73	103	114	finally
      //   63	68	123	finally
      //   41	63	127	finally
      //   41	63	135	java/lang/Exception
      //   8	13	143	finally
      //   68	70	143	finally
      //   103	107	143	finally
      //   110	112	143	finally
      //   116	123	143	finally
    }
  }
  
  static class PersistedEvents
  {
    static final String PERSISTED_EVENTS_FILENAME = "AppEventsLogger.persistedevents";
    private static Object staticLock = new Object();
    private Context context;
    private HashMap<AppEventsLogger.AccessTokenAppIdPair, List<AppEventsLogger.AppEvent>> persistedEvents = new HashMap();
    
    private PersistedEvents(Context paramContext)
    {
      this.context = paramContext;
    }
    
    public static void persistEvents(Context paramContext, AppEventsLogger.AccessTokenAppIdPair paramAccessTokenAppIdPair, AppEventsLogger.SessionEventsState paramSessionEventsState)
    {
      HashMap localHashMap = new HashMap();
      localHashMap.put(paramAccessTokenAppIdPair, paramSessionEventsState);
      persistEvents(paramContext, localHashMap);
    }
    
    public static void persistEvents(Context paramContext, Map<AppEventsLogger.AccessTokenAppIdPair, AppEventsLogger.SessionEventsState> paramMap)
    {
      PersistedEvents localPersistedEvents;
      synchronized (staticLock)
      {
        localPersistedEvents = readAndClearStore(paramContext);
        Iterator localIterator = paramMap.entrySet().iterator();
        while (localIterator.hasNext())
        {
          Map.Entry localEntry = (Map.Entry)localIterator.next();
          List localList = ((AppEventsLogger.SessionEventsState)localEntry.getValue()).getEventsToPersist();
          if (localList.size() != 0) {
            localPersistedEvents.addEvents((AppEventsLogger.AccessTokenAppIdPair)localEntry.getKey(), localList);
          }
        }
      }
      localPersistedEvents.write();
    }
    
    public static PersistedEvents readAndClearStore(Context paramContext)
    {
      synchronized (staticLock)
      {
        PersistedEvents localPersistedEvents = new PersistedEvents(paramContext);
        localPersistedEvents.readAndClearStore();
        return localPersistedEvents;
      }
    }
    
    /* Error */
    private void readAndClearStore()
    {
      // Byte code:
      //   0: aconst_null
      //   1: astore_1
      //   2: new 104	java/io/ObjectInputStream
      //   5: dup
      //   6: new 106	java/io/BufferedInputStream
      //   9: dup
      //   10: aload_0
      //   11: getfield 30	com/facebook/AppEventsLogger$PersistedEvents:context	Landroid/content/Context;
      //   14: ldc 8
      //   16: invokevirtual 112	android/content/Context:openFileInput	(Ljava/lang/String;)Ljava/io/FileInputStream;
      //   19: invokespecial 115	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
      //   22: invokespecial 116	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
      //   25: astore_2
      //   26: aload_2
      //   27: invokevirtual 119	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
      //   30: checkcast 25	java/util/HashMap
      //   33: astore 7
      //   35: aload_0
      //   36: getfield 30	com/facebook/AppEventsLogger$PersistedEvents:context	Landroid/content/Context;
      //   39: ldc 8
      //   41: invokevirtual 123	android/content/Context:getFileStreamPath	(Ljava/lang/String;)Ljava/io/File;
      //   44: invokevirtual 128	java/io/File:delete	()Z
      //   47: pop
      //   48: aload_0
      //   49: aload 7
      //   51: putfield 28	com/facebook/AppEventsLogger$PersistedEvents:persistedEvents	Ljava/util/HashMap;
      //   54: aload_2
      //   55: invokestatic 134	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
      //   58: return
      //   59: astore 9
      //   61: aload_1
      //   62: invokestatic 134	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
      //   65: return
      //   66: astore 4
      //   68: invokestatic 140	com/facebook/AppEventsLogger:access$1300	()Ljava/lang/String;
      //   71: new 142	java/lang/StringBuilder
      //   74: dup
      //   75: invokespecial 143	java/lang/StringBuilder:<init>	()V
      //   78: ldc 145
      //   80: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   83: aload 4
      //   85: invokevirtual 152	java/lang/Exception:toString	()Ljava/lang/String;
      //   88: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   91: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   94: invokestatic 159	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
      //   97: pop
      //   98: aload_1
      //   99: invokestatic 134	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
      //   102: return
      //   103: astore 5
      //   105: aload_1
      //   106: invokestatic 134	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
      //   109: aload 5
      //   111: athrow
      //   112: astore 5
      //   114: aload_2
      //   115: astore_1
      //   116: goto -11 -> 105
      //   119: astore 4
      //   121: aload_2
      //   122: astore_1
      //   123: goto -55 -> 68
      //   126: astore_3
      //   127: aload_2
      //   128: astore_1
      //   129: goto -68 -> 61
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	132	0	this	PersistedEvents
      //   1	128	1	localObject1	Object
      //   25	103	2	localObjectInputStream	java.io.ObjectInputStream
      //   126	1	3	localFileNotFoundException1	java.io.FileNotFoundException
      //   66	18	4	localException1	Exception
      //   119	1	4	localException2	Exception
      //   103	7	5	localObject2	Object
      //   112	1	5	localObject3	Object
      //   33	17	7	localHashMap	HashMap
      //   59	1	9	localFileNotFoundException2	java.io.FileNotFoundException
      // Exception table:
      //   from	to	target	type
      //   2	26	59	java/io/FileNotFoundException
      //   2	26	66	java/lang/Exception
      //   2	26	103	finally
      //   68	98	103	finally
      //   26	54	112	finally
      //   26	54	119	java/lang/Exception
      //   26	54	126	java/io/FileNotFoundException
    }
    
    /* Error */
    private void write()
    {
      // Byte code:
      //   0: aconst_null
      //   1: astore_1
      //   2: new 161	java/io/ObjectOutputStream
      //   5: dup
      //   6: new 163	java/io/BufferedOutputStream
      //   9: dup
      //   10: aload_0
      //   11: getfield 30	com/facebook/AppEventsLogger$PersistedEvents:context	Landroid/content/Context;
      //   14: ldc 8
      //   16: iconst_0
      //   17: invokevirtual 167	android/content/Context:openFileOutput	(Ljava/lang/String;I)Ljava/io/FileOutputStream;
      //   20: invokespecial 170	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;)V
      //   23: invokespecial 171	java/io/ObjectOutputStream:<init>	(Ljava/io/OutputStream;)V
      //   26: astore_2
      //   27: aload_2
      //   28: aload_0
      //   29: getfield 28	com/facebook/AppEventsLogger$PersistedEvents:persistedEvents	Ljava/util/HashMap;
      //   32: invokevirtual 175	java/io/ObjectOutputStream:writeObject	(Ljava/lang/Object;)V
      //   35: aload_2
      //   36: invokestatic 134	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
      //   39: return
      //   40: astore_3
      //   41: invokestatic 140	com/facebook/AppEventsLogger:access$1300	()Ljava/lang/String;
      //   44: new 142	java/lang/StringBuilder
      //   47: dup
      //   48: invokespecial 143	java/lang/StringBuilder:<init>	()V
      //   51: ldc 145
      //   53: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   56: aload_3
      //   57: invokevirtual 152	java/lang/Exception:toString	()Ljava/lang/String;
      //   60: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   63: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   66: invokestatic 159	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
      //   69: pop
      //   70: aload_1
      //   71: invokestatic 134	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
      //   74: return
      //   75: astore 4
      //   77: aload_1
      //   78: invokestatic 134	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
      //   81: aload 4
      //   83: athrow
      //   84: astore 4
      //   86: aload_2
      //   87: astore_1
      //   88: goto -11 -> 77
      //   91: astore_3
      //   92: aload_2
      //   93: astore_1
      //   94: goto -53 -> 41
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	97	0	this	PersistedEvents
      //   1	93	1	localObject1	Object
      //   26	67	2	localObjectOutputStream	java.io.ObjectOutputStream
      //   40	17	3	localException1	Exception
      //   91	1	3	localException2	Exception
      //   75	7	4	localObject2	Object
      //   84	1	4	localObject3	Object
      // Exception table:
      //   from	to	target	type
      //   2	27	40	java/lang/Exception
      //   2	27	75	finally
      //   41	70	75	finally
      //   27	35	84	finally
      //   27	35	91	java/lang/Exception
    }
    
    public void addEvents(AppEventsLogger.AccessTokenAppIdPair paramAccessTokenAppIdPair, List<AppEventsLogger.AppEvent> paramList)
    {
      if (!this.persistedEvents.containsKey(paramAccessTokenAppIdPair)) {
        this.persistedEvents.put(paramAccessTokenAppIdPair, new ArrayList());
      }
      ((List)this.persistedEvents.get(paramAccessTokenAppIdPair)).addAll(paramList);
    }
    
    public List<AppEventsLogger.AppEvent> getEvents(AppEventsLogger.AccessTokenAppIdPair paramAccessTokenAppIdPair)
    {
      return (List)this.persistedEvents.get(paramAccessTokenAppIdPair);
    }
    
    public Set<AppEventsLogger.AccessTokenAppIdPair> keySet()
    {
      return this.persistedEvents.keySet();
    }
  }
  
  static class SessionEventsState
  {
    public static final String ENCODED_EVENTS_KEY = "encoded_events";
    public static final String EVENT_COUNT_KEY = "event_count";
    public static final String NUM_SKIPPED_KEY = "num_skipped";
    private final int MAX_ACCUMULATED_LOG_EVENTS = 1000;
    private List<AppEventsLogger.AppEvent> accumulatedEvents = new ArrayList();
    private AttributionIdentifiers attributionIdentifiers;
    private String hashedDeviceAndAppId;
    private List<AppEventsLogger.AppEvent> inFlightEvents = new ArrayList();
    private int numSkippedEventsDueToFullBuffer;
    private String packageName;
    
    public SessionEventsState(AttributionIdentifiers paramAttributionIdentifiers, String paramString1, String paramString2)
    {
      this.attributionIdentifiers = paramAttributionIdentifiers;
      this.packageName = paramString1;
      this.hashedDeviceAndAppId = paramString2;
    }
    
    private byte[] getStringAsByteArray(String paramString)
    {
      try
      {
        byte[] arrayOfByte = paramString.getBytes("UTF-8");
        return arrayOfByte;
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        Utility.logd("Encoding exception: ", localUnsupportedEncodingException);
      }
      return null;
    }
    
    private void populateRequest(Request paramRequest, int paramInt, JSONArray paramJSONArray, boolean paramBoolean1, boolean paramBoolean2)
    {
      GraphObject localGraphObject = GraphObject.Factory.create();
      localGraphObject.setProperty("event", "CUSTOM_APP_EVENTS");
      if (this.numSkippedEventsDueToFullBuffer > 0) {
        localGraphObject.setProperty("num_skipped_events", Integer.valueOf(paramInt));
      }
      if (paramBoolean1) {
        Utility.setAppEventAttributionParameters(localGraphObject, this.attributionIdentifiers, this.hashedDeviceAndAppId, paramBoolean2);
      }
      try
      {
        Utility.setAppEventExtendedDeviceInfoParameters(localGraphObject, AppEventsLogger.applicationContext);
        label64:
        localGraphObject.setProperty("application_package_name", this.packageName);
        paramRequest.setGraphObject(localGraphObject);
        Bundle localBundle = paramRequest.getParameters();
        if (localBundle == null) {
          localBundle = new Bundle();
        }
        String str = paramJSONArray.toString();
        if (str != null)
        {
          localBundle.putByteArray("custom_events_file", getStringAsByteArray(str));
          paramRequest.setTag(str);
        }
        paramRequest.setParameters(localBundle);
        return;
      }
      catch (Exception localException)
      {
        break label64;
      }
    }
    
    public void accumulatePersistedEvents(List<AppEventsLogger.AppEvent> paramList)
    {
      try
      {
        this.accumulatedEvents.addAll(paramList);
        return;
      }
      finally
      {
        localObject = finally;
        throw localObject;
      }
    }
    
    /* Error */
    public void addEvent(AppEventsLogger.AppEvent paramAppEvent)
    {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield 35	com/facebook/AppEventsLogger$SessionEventsState:accumulatedEvents	Ljava/util/List;
      //   6: invokeinterface 159 1 0
      //   11: aload_0
      //   12: getfield 37	com/facebook/AppEventsLogger$SessionEventsState:inFlightEvents	Ljava/util/List;
      //   15: invokeinterface 159 1 0
      //   20: iadd
      //   21: sipush 1000
      //   24: if_icmplt +16 -> 40
      //   27: aload_0
      //   28: iconst_1
      //   29: aload_0
      //   30: getfield 86	com/facebook/AppEventsLogger$SessionEventsState:numSkippedEventsDueToFullBuffer	I
      //   33: iadd
      //   34: putfield 86	com/facebook/AppEventsLogger$SessionEventsState:numSkippedEventsDueToFullBuffer	I
      //   37: aload_0
      //   38: monitorexit
      //   39: return
      //   40: aload_0
      //   41: getfield 35	com/facebook/AppEventsLogger$SessionEventsState:accumulatedEvents	Ljava/util/List;
      //   44: aload_1
      //   45: invokeinterface 163 2 0
      //   50: pop
      //   51: goto -14 -> 37
      //   54: astore_2
      //   55: aload_0
      //   56: monitorexit
      //   57: aload_2
      //   58: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	59	0	this	SessionEventsState
      //   0	59	1	paramAppEvent	AppEventsLogger.AppEvent
      //   54	4	2	localObject	Object
      // Exception table:
      //   from	to	target	type
      //   2	37	54	finally
      //   40	51	54	finally
    }
    
    public void clearInFlightAndStats(boolean paramBoolean)
    {
      if (paramBoolean) {}
      try
      {
        this.accumulatedEvents.addAll(this.inFlightEvents);
        this.inFlightEvents.clear();
        this.numSkippedEventsDueToFullBuffer = 0;
        return;
      }
      finally {}
    }
    
    public int getAccumulatedEventCount()
    {
      try
      {
        int i = this.accumulatedEvents.size();
        return i;
      }
      finally
      {
        localObject = finally;
        throw localObject;
      }
    }
    
    public List<AppEventsLogger.AppEvent> getEventsToPersist()
    {
      try
      {
        List localList = this.accumulatedEvents;
        this.accumulatedEvents = new ArrayList();
        return localList;
      }
      finally
      {
        localObject = finally;
        throw localObject;
      }
    }
    
    public int populateRequest(Request paramRequest, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
    {
      int i;
      JSONArray localJSONArray;
      try
      {
        i = this.numSkippedEventsDueToFullBuffer;
        this.inFlightEvents.addAll(this.accumulatedEvents);
        this.accumulatedEvents.clear();
        localJSONArray = new JSONArray();
        Iterator localIterator = this.inFlightEvents.iterator();
        while (localIterator.hasNext())
        {
          AppEventsLogger.AppEvent localAppEvent = (AppEventsLogger.AppEvent)localIterator.next();
          if ((paramBoolean1) || (!localAppEvent.getIsImplicit())) {
            localJSONArray.put(localAppEvent.getJSONObject());
          }
        }
        if (localJSONArray.length() != 0) {
          break label118;
        }
      }
      finally {}
      return 0;
      label118:
      populateRequest(paramRequest, i, localJSONArray, paramBoolean2, paramBoolean3);
      return localJSONArray.length();
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.facebook.AppEventsLogger
 * JD-Core Version:    0.7.0.1
 */