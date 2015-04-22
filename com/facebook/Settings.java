package com.facebook;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.facebook.internal.AttributionIdentifiers;
import com.facebook.internal.Utility;
import com.facebook.internal.Utility.FetchedAppSettings;
import com.facebook.internal.Validate;
import com.facebook.model.GraphObject;
import com.facebook.model.GraphObject.Factory;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONException;
import org.json.JSONObject;

public final class Settings
{
  private static final String ANALYTICS_EVENT = "event";
  public static final String APPLICATION_ID_PROPERTY = "com.facebook.sdk.ApplicationId";
  private static final String APP_EVENT_PREFERENCES = "com.facebook.sdk.appEventPreferences";
  private static final String ATTRIBUTION_ID_COLUMN_NAME = "aid";
  private static final Uri ATTRIBUTION_ID_CONTENT_URI;
  private static final String ATTRIBUTION_PREFERENCES = "com.facebook.sdk.attributionTracking";
  private static final String AUTO_PUBLISH = "auto_publish";
  public static final String CLIENT_TOKEN_PROPERTY = "com.facebook.sdk.ClientToken";
  private static final int DEFAULT_CORE_POOL_SIZE = 5;
  private static final int DEFAULT_KEEP_ALIVE = 1;
  private static final int DEFAULT_MAXIMUM_POOL_SIZE = 128;
  private static final ThreadFactory DEFAULT_THREAD_FACTORY = new ThreadFactory()
  {
    private final AtomicInteger counter = new AtomicInteger(0);
    
    public Thread newThread(Runnable paramAnonymousRunnable)
    {
      return new Thread(paramAnonymousRunnable, "FacebookSdk #" + this.counter.incrementAndGet());
    }
  };
  private static final BlockingQueue<Runnable> DEFAULT_WORK_QUEUE;
  private static final String FACEBOOK_COM = "facebook.com";
  private static final Object LOCK;
  private static final String MOBILE_INSTALL_EVENT = "MOBILE_APP_INSTALL";
  private static final String PUBLISH_ACTIVITY_PATH = "%s/activities";
  private static final String TAG = Settings.class.getCanonicalName();
  private static volatile String appClientToken;
  private static volatile String appVersion;
  private static volatile String applicationId;
  private static volatile boolean defaultsLoaded;
  private static volatile Executor executor;
  private static volatile String facebookDomain;
  private static volatile boolean isLoggingEnabled;
  private static final HashSet<LoggingBehavior> loggingBehaviors;
  private static AtomicLong onProgressThreshold;
  private static volatile boolean platformCompatibilityEnabled;
  private static Boolean sdkInitialized = Boolean.valueOf(false);
  private static volatile boolean shouldAutoPublishInstall;
  
  static
  {
    LoggingBehavior[] arrayOfLoggingBehavior = new LoggingBehavior[1];
    arrayOfLoggingBehavior[0] = LoggingBehavior.DEVELOPER_ERRORS;
    loggingBehaviors = new HashSet(Arrays.asList(arrayOfLoggingBehavior));
    defaultsLoaded = false;
    facebookDomain = "facebook.com";
    onProgressThreshold = new AtomicLong(65536L);
    isLoggingEnabled = false;
    LOCK = new Object();
    ATTRIBUTION_ID_CONTENT_URI = Uri.parse("content://com.facebook.katana.provider.AttributionIdProvider");
    DEFAULT_WORK_QUEUE = new LinkedBlockingQueue(10);
  }
  
  public static final void addLoggingBehavior(LoggingBehavior paramLoggingBehavior)
  {
    synchronized (loggingBehaviors)
    {
      loggingBehaviors.add(paramLoggingBehavior);
      return;
    }
  }
  
  public static final void clearLoggingBehaviors()
  {
    synchronized (loggingBehaviors)
    {
      loggingBehaviors.clear();
      return;
    }
  }
  
  public static String getAppVersion()
  {
    return appVersion;
  }
  
  public static String getApplicationId()
  {
    return applicationId;
  }
  
  private static Executor getAsyncTaskExecutor()
  {
    Object localObject;
    try
    {
      Field localField = AsyncTask.class.getField("THREAD_POOL_EXECUTOR");
      if ((localObject instanceof Executor)) {
        break label35;
      }
    }
    catch (NoSuchFieldException localNoSuchFieldException)
    {
      try
      {
        localObject = localField.get(null);
        if (localObject != null) {
          break label26;
        }
        return null;
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        return null;
      }
      localNoSuchFieldException = localNoSuchFieldException;
      return null;
    }
    label26:
    return null;
    label35:
    return (Executor)localObject;
  }
  
  public static String getAttributionId(ContentResolver paramContentResolver)
  {
    try
    {
      String[] arrayOfString = { "aid" };
      Cursor localCursor = paramContentResolver.query(ATTRIBUTION_ID_CONTENT_URI, arrayOfString, null, null, null);
      if ((localCursor != null) && (localCursor.moveToFirst()))
      {
        String str = localCursor.getString(localCursor.getColumnIndex("aid"));
        localCursor.close();
        return str;
      }
    }
    catch (Exception localException)
    {
      Log.d(TAG, "Caught unexpected exception in getAttributionId(): " + localException.toString());
      return null;
    }
    return null;
  }
  
  public static String getClientToken()
  {
    return appClientToken;
  }
  
  public static Executor getExecutor()
  {
    synchronized (LOCK)
    {
      if (executor == null)
      {
        Object localObject3 = getAsyncTaskExecutor();
        if (localObject3 == null) {
          localObject3 = new ThreadPoolExecutor(5, 128, 1L, TimeUnit.SECONDS, DEFAULT_WORK_QUEUE, DEFAULT_THREAD_FACTORY);
        }
        executor = (Executor)localObject3;
      }
      return executor;
    }
  }
  
  public static String getFacebookDomain()
  {
    return facebookDomain;
  }
  
  public static boolean getLimitEventAndDataUsage(Context paramContext)
  {
    return paramContext.getSharedPreferences("com.facebook.sdk.appEventPreferences", 0).getBoolean("limitEventUsage", false);
  }
  
  public static final Set<LoggingBehavior> getLoggingBehaviors()
  {
    synchronized (loggingBehaviors)
    {
      Set localSet = Collections.unmodifiableSet(new HashSet(loggingBehaviors));
      return localSet;
    }
  }
  
  public static long getOnProgressThreshold()
  {
    return onProgressThreshold.get();
  }
  
  public static boolean getPlatformCompatibilityEnabled()
  {
    return platformCompatibilityEnabled;
  }
  
  public static String getSdkVersion()
  {
    return "3.18.1";
  }
  
  @Deprecated
  public static boolean getShouldAutoPublishInstall()
  {
    return shouldAutoPublishInstall;
  }
  
  public static final boolean isLoggingBehaviorEnabled(LoggingBehavior paramLoggingBehavior)
  {
    for (;;)
    {
      synchronized (loggingBehaviors)
      {
        if ((isLoggingEnabled()) && (loggingBehaviors.contains(paramLoggingBehavior)))
        {
          bool = true;
          return bool;
        }
      }
      boolean bool = false;
    }
  }
  
  public static final boolean isLoggingEnabled()
  {
    return isLoggingEnabled;
  }
  
  public static void loadDefaultsFromMetadata(Context paramContext)
  {
    defaultsLoaded = true;
    if (paramContext == null) {}
    for (;;)
    {
      return;
      try
      {
        ApplicationInfo localApplicationInfo = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128);
        if ((localApplicationInfo != null) && (localApplicationInfo.metaData != null))
        {
          if (applicationId == null) {
            applicationId = localApplicationInfo.metaData.getString("com.facebook.sdk.ApplicationId");
          }
          if (appClientToken == null)
          {
            appClientToken = localApplicationInfo.metaData.getString("com.facebook.sdk.ClientToken");
            return;
          }
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
    }
  }
  
  static void loadDefaultsFromMetadataIfNeeded(Context paramContext)
  {
    if (!defaultsLoaded) {
      loadDefaultsFromMetadata(paramContext);
    }
  }
  
  static Response publishInstallAndWaitForResponse(Context paramContext, String paramString, boolean paramBoolean)
  {
    if ((paramContext == null) || (paramString == null)) {
      try
      {
        throw new IllegalArgumentException("Both context and applicationId must be non-null");
      }
      catch (Exception localException)
      {
        Utility.logd("Facebook-publish", localException);
        return new Response(null, null, new FacebookRequestError(null, localException));
      }
    }
    AttributionIdentifiers localAttributionIdentifiers = AttributionIdentifiers.getAttributionIdentifiers(paramContext);
    SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("com.facebook.sdk.attributionTracking", 0);
    String str1 = paramString + "ping";
    String str2 = paramString + "json";
    long l = localSharedPreferences.getLong(str1, 0L);
    String str3 = localSharedPreferences.getString(str2, null);
    if (!paramBoolean) {
      setShouldAutoPublishInstall(false);
    }
    GraphObject localGraphObject1 = GraphObject.Factory.create();
    localGraphObject1.setProperty("event", "MOBILE_APP_INSTALL");
    Utility.setAppEventAttributionParameters(localGraphObject1, localAttributionIdentifiers, Utility.getHashedDeviceAndAppID(paramContext, paramString), getLimitEventAndDataUsage(paramContext));
    localGraphObject1.setProperty("auto_publish", Boolean.valueOf(paramBoolean));
    localGraphObject1.setProperty("application_package_name", paramContext.getPackageName());
    Request localRequest = Request.newPostRequest(null, String.format("%s/activities", new Object[] { paramString }), localGraphObject1, null);
    if (l != 0L)
    {
      localObject = null;
      if (str3 == null) {}
    }
    try
    {
      GraphObject localGraphObject2 = GraphObject.Factory.create(new JSONObject(str3));
      localObject = localGraphObject2;
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        Response localResponse;
        SharedPreferences.Editor localEditor;
        localObject = null;
      }
    }
    if (localObject == null) {
      return (Response)Response.createResponsesFromString("true", null, new RequestBatch(new Request[] { localRequest }), true).get(0);
    }
    return new Response(null, null, null, localObject, true);
    if ((localAttributionIdentifiers == null) || ((localAttributionIdentifiers.getAndroidAdvertiserId() == null) && (localAttributionIdentifiers.getAttributionId() == null))) {
      throw new FacebookException("No attribution id available to send to server.");
    }
    if (!Utility.queryAppSettings(paramString, false).supportsAttribution()) {
      throw new FacebookException("Install attribution has been disabled on the server.");
    }
    localResponse = localRequest.executeAndWait();
    localEditor = localSharedPreferences.edit();
    localEditor.putLong(str1, System.currentTimeMillis());
    if ((localResponse.getGraphObject() != null) && (localResponse.getGraphObject().getInnerJSONObject() != null)) {
      localEditor.putString(str2, localResponse.getGraphObject().getInnerJSONObject().toString());
    }
    localEditor.commit();
    return localResponse;
  }
  
  static void publishInstallAsync(Context paramContext, final String paramString, final Request.Callback paramCallback)
  {
    Context localContext = paramContext.getApplicationContext();
    getExecutor().execute(new Runnable()
    {
      public void run()
      {
        final Response localResponse = Settings.publishInstallAndWaitForResponse(this.val$applicationContext, paramString, false);
        if (paramCallback != null) {
          new Handler(Looper.getMainLooper()).post(new Runnable()
          {
            public void run()
            {
              Settings.2.this.val$callback.onCompleted(localResponse);
            }
          });
        }
      }
    });
  }
  
  public static final void removeLoggingBehavior(LoggingBehavior paramLoggingBehavior)
  {
    synchronized (loggingBehaviors)
    {
      loggingBehaviors.remove(paramLoggingBehavior);
      return;
    }
  }
  
  /* Error */
  public static void sdkInitialize(Context paramContext)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 149	com/facebook/Settings:sdkInitialized	Ljava/lang/Boolean;
    //   6: invokevirtual 519	java/lang/Boolean:booleanValue	()Z
    //   9: istore_2
    //   10: iload_2
    //   11: iconst_1
    //   12: if_icmpne +7 -> 19
    //   15: ldc 2
    //   17: monitorexit
    //   18: return
    //   19: aload_0
    //   20: invokevirtual 501	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   23: invokestatic 525	com/facebook/BoltsMeasurementEventListener:getInstance	(Landroid/content/Context;)Lcom/facebook/BoltsMeasurementEventListener;
    //   26: pop
    //   27: iconst_1
    //   28: invokestatic 147	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   31: putstatic 149	com/facebook/Settings:sdkInitialized	Ljava/lang/Boolean;
    //   34: goto -19 -> 15
    //   37: astore_1
    //   38: ldc 2
    //   40: monitorexit
    //   41: aload_1
    //   42: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	43	0	paramContext	Context
    //   37	5	1	localObject	Object
    //   9	4	2	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   3	10	37	finally
    //   19	34	37	finally
  }
  
  public static void setAppVersion(String paramString)
  {
    appVersion = paramString;
  }
  
  public static void setApplicationId(String paramString)
  {
    applicationId = paramString;
  }
  
  public static void setClientToken(String paramString)
  {
    appClientToken = paramString;
  }
  
  public static void setExecutor(Executor paramExecutor)
  {
    Validate.notNull(paramExecutor, "executor");
    synchronized (LOCK)
    {
      executor = paramExecutor;
      return;
    }
  }
  
  public static void setFacebookDomain(String paramString)
  {
    Log.w(TAG, "WARNING: Calling setFacebookDomain from non-DEBUG code.");
    facebookDomain = paramString;
  }
  
  public static final void setIsLoggingEnabled(boolean paramBoolean)
  {
    isLoggingEnabled = paramBoolean;
  }
  
  public static void setLimitEventAndDataUsage(Context paramContext, boolean paramBoolean)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("com.facebook.sdk.appEventPreferences", 0).edit();
    localEditor.putBoolean("limitEventUsage", paramBoolean);
    localEditor.commit();
  }
  
  public static void setOnProgressThreshold(long paramLong)
  {
    onProgressThreshold.set(paramLong);
  }
  
  public static void setPlatformCompatibilityEnabled(boolean paramBoolean)
  {
    platformCompatibilityEnabled = paramBoolean;
  }
  
  @Deprecated
  public static void setShouldAutoPublishInstall(boolean paramBoolean)
  {
    shouldAutoPublishInstall = paramBoolean;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.facebook.Settings
 * JD-Core Version:    0.7.0.1
 */