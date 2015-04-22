package com.ea.nimble.tracking;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import com.ea.nimble.ApplicationEnvironment;
import com.ea.nimble.Component;
import com.ea.nimble.EASPDataLoader;
import com.ea.nimble.EASPDataLoader.EASPDataBuffer;
import com.ea.nimble.INetwork;
import com.ea.nimble.ISynergyEnvironment;
import com.ea.nimble.ISynergyNetwork;
import com.ea.nimble.ISynergyResponse;
import com.ea.nimble.Log.Helper;
import com.ea.nimble.LogSource;
import com.ea.nimble.Network;
import com.ea.nimble.Network.Status;
import com.ea.nimble.Persistence;
import com.ea.nimble.Persistence.Storage;
import com.ea.nimble.PersistenceService;
import com.ea.nimble.SynergyEnvironment;
import com.ea.nimble.SynergyNetwork;
import com.ea.nimble.SynergyNetworkConnectionCallback;
import com.ea.nimble.SynergyNetworkConnectionHandle;
import com.ea.nimble.SynergyRequest;
import com.ea.nimble.Timer;
import com.ea.nimble.Utility;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

abstract class TrackingBase
  extends Component
  implements ITracking, LogSource
{
  private static final int DATA_VERSION_CURRENT = 3;
  private static final int DEFAULT_MAX_QUEUE_LENGTH = 3;
  protected static final double DEFAULT_POST_INTERVAL = 1.0D;
  protected static final double DEFAULT_REPOST_MULTIPLIER = 2.0D;
  protected static final double DEFAULT_RETRY_DELAY = 1.0D;
  protected static final double MAX_POST_RETRY_DELAY = 300.0D;
  protected static final double NOW_POST_INTERVAL = 0.0D;
  private static final String ORIGIN_LOGIN_STATUS_STRING_AUTO_LOGGING_IN = "autoLogin";
  private static final String ORIGIN_LOGIN_STATUS_STRING_LIVE_USER = "live";
  private static final String ORIGIN_NOTIFICATION_LOGIN_STATUS_UPDATE_KEY_STATUS = "STATUS";
  private static final String PERSISTENCE_ADDITIONAL_INFORMATION_ID = "eventHeaders";
  private static final String PERSISTENCE_CURRENT_SESSION_ID = "currentSessionObject";
  private static final String PERSISTENCE_ENABLE_FLAG = "trackingEnabledFlag";
  private static final String PERSISTENCE_EVENT_QUEUE_ID = "eventQueue";
  private static final String PERSISTENCE_FIRST_SESSION_ID_NUMBER = "firstSessionIDNumber";
  private static final String PERSISTENCE_LAST_SESSION_ID_NUMBER = "lastSessionIDNumber";
  private static final String PERSISTENCE_LOGGED_IN_TO_ORIGIN_ID = "loggedInToOrigin";
  private static final String PERSISTENCE_QUEUED_SESSIONS_ID = "queuedSessionObjects";
  private static final String PERSISTENCE_SAVED_SESSION_ID_NUMBER = "savedSession";
  private static final String PERSISTENCE_SESSION_DATA_ID = "sessionData";
  private static final String PERSISTENCE_VERSION_ID = "dataVersion";
  private static final String SESSION_FILE_FORMAT = "%sSession%d";
  protected TrackingBaseSessionObject m_currentSessionObject = new TrackingBaseSessionObject();
  protected ArrayList<SessionData> m_customSessionData = new ArrayList();
  private boolean m_enable = true;
  private long m_firstSessionIDNumber = 0L;
  private boolean m_isPostPending = false;
  private boolean m_isRequestInProgress = false;
  private long m_lastSessionIDNumber = 0L;
  protected boolean m_loggedInToOrigin = false;
  private int m_maxQueueLength = 3;
  private String m_name = getPersistenceIdentifier();
  private BroadcastReceiver m_networkStatusChangedReceiver = null;
  private OriginLoginStatusChangedReceiver m_originLoginStatusChangedReceiver = null;
  private ArrayList<Map<String, String>> m_pendingEvents = new ArrayList();
  private double m_postInterval = 1.0D;
  protected double m_postRetryDelay;
  private Timer m_postTimer = new Timer(new PostTask());
  private StartupRequestsFinishedReceiver m_receiver = new StartupRequestsFinishedReceiver(null);
  private ArrayList<TrackingBaseSessionObject> m_sessionsToPost = new ArrayList();
  
  private void addCurrentSessionObjectToBackOfQueue()
  {
    this.m_lastSessionIDNumber = (1L + this.m_lastSessionIDNumber);
    if (this.m_sessionsToPost.size() >= this.m_maxQueueLength) {
      saveSessionToFile(this.m_currentSessionObject, this.m_lastSessionIDNumber);
    }
    for (;;)
    {
      saveToPersistence();
      return;
      this.m_sessionsToPost.add(this.m_currentSessionObject);
    }
  }
  
  private void configureTrackingOnFirstInstall()
  {
    Log.Helper.LOGD(this, "First Install. Look for App Settings to enable/disable tracking", new Object[0]);
    try
    {
      String str = ApplicationEnvironment.getCurrentActivity().getPackageManager().getApplicationInfo(ApplicationEnvironment.getCurrentActivity().getPackageName(), 128).metaData.getString("com.ea.nimble.tracking.defaultEnable");
      if (Utility.validString(str))
      {
        if (str.equalsIgnoreCase("enable"))
        {
          Log.Helper.LOGD(this, "Default App Setting : Enable Tracking", new Object[0]);
          this.m_enable = true;
          return;
        }
        if (!str.equalsIgnoreCase("disable")) {
          return;
        }
        Log.Helper.LOGD(this, "Default App Setting : Disable Tracking", new Object[0]);
        this.m_enable = false;
      }
    }
    catch (Exception localException)
    {
      Log.e("Nimble", "WARNING! Cannot find valid TrackingEnable from AndroidManifest.xml");
    }
  }
  
  private void fillSessionsToPost()
  {
    for (int i = this.m_sessionsToPost.size();; i++)
    {
      long l;
      if (i < this.m_maxQueueLength)
      {
        l = this.m_firstSessionIDNumber + i;
        if (l <= this.m_lastSessionIDNumber) {}
      }
      else
      {
        return;
      }
      TrackingBaseSessionObject localTrackingBaseSessionObject = loadSessionFromFile(l);
      if (localTrackingBaseSessionObject != null) {
        this.m_sessionsToPost.add(localTrackingBaseSessionObject);
      }
    }
  }
  
  private String getFilenameForSessionID(long paramLong)
  {
    if (paramLong < 0L)
    {
      Log.Helper.LOGE(this, "Trying to find the filename for an invalid sessionID!", new Object[0]);
      return null;
    }
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = getComponentId();
    arrayOfObject[1] = Long.valueOf(paramLong);
    return String.format("%sSession%d", arrayOfObject);
  }
  
  private boolean isAbleToPostEvent()
  {
    if (!this.m_enable) {}
    do
    {
      return false;
      if ((!ApplicationEnvironment.isMainApplicationRunning()) || (ApplicationEnvironment.getCurrentActivity() == null))
      {
        Log.Helper.LOGD(this, "isAbleToPostEvent - return because the app is in background", new Object[0]);
        return false;
      }
      if (Network.getComponent().getStatus() == Network.Status.OK) {
        break;
      }
    } while (this.m_networkStatusChangedReceiver != null);
    Log.Helper.LOGD(this, "Network status not OK for event post. Adding receiver for network status change.", new Object[0]);
    killPostTimer();
    this.m_networkStatusChangedReceiver = new BroadcastReceiver()
    {
      public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
      {
        if ((paramAnonymousIntent.getAction().equals("nimble.notification.networkStatusChanged")) && (Network.getComponent().getStatus() == Network.Status.OK))
        {
          Log.Helper.LOGD(this, "Network status restored, kicking off event post.", new Object[0]);
          Utility.unregisterReceiver(TrackingBase.this.m_networkStatusChangedReceiver);
          TrackingBase.access$702(TrackingBase.this, null);
          TrackingBase.this.resetPostTimer(0.0D);
        }
      }
    };
    Utility.registerReceiver("nimble.notification.networkStatusChanged", this.m_networkStatusChangedReceiver);
    return false;
    if (!SynergyEnvironment.getComponent().isDataAvailable())
    {
      this.m_isPostPending = true;
      addObserverForSynergyEnvironmentUpdateFinished();
      return false;
    }
    return true;
  }
  
  private void killPostTimer()
  {
    if (this.m_postTimer != null)
    {
      this.m_postTimer.cancel();
      this.m_postTimer = null;
    }
  }
  
  private TrackingBaseSessionObject loadSessionFromFile(long paramLong)
  {
    Persistence localPersistence = PersistenceService.getPersistenceForNimbleComponent(getFilenameForSessionID(paramLong), Persistence.Storage.DOCUMENT);
    if (localPersistence != null)
    {
      Serializable localSerializable = localPersistence.getValue("savedSession");
      if ((localSerializable != null) && (localSerializable.getClass() == TrackingBaseSessionObject.class)) {
        return (TrackingBaseSessionObject)localSerializable;
      }
      return null;
    }
    return null;
  }
  
  private void postIntervalTimerExpired(boolean paramBoolean)
  {
    if (paramBoolean) {}
    try
    {
      packageCurrentSession();
      postPendingEvents(false);
      return;
    }
    finally {}
  }
  
  private void postPendingEvents(boolean paramBoolean)
  {
    if (!isAbleToPostEvent()) {}
    final TrackingBaseSessionObject localTrackingBaseSessionObject2;
    SynergyRequest localSynergyRequest;
    do
    {
      return;
      if ((this.m_sessionsToPost == null) || (this.m_sessionsToPost.size() <= 0))
      {
        Log.Helper.LOGD(this, "No tracking sessions to post.", new Object[0]);
        return;
      }
      for (TrackingBaseSessionObject localTrackingBaseSessionObject1 = (TrackingBaseSessionObject)this.m_sessionsToPost.get(0); localTrackingBaseSessionObject1 == null; localTrackingBaseSessionObject1 = (TrackingBaseSessionObject)this.m_sessionsToPost.get(0))
      {
        removeSessionAndFillQueue(null);
        if (this.m_sessionsToPost.size() <= 0)
        {
          Log.Helper.LOGD(this, "No valid tracking sessions to post.", new Object[0]);
          return;
        }
      }
      localTrackingBaseSessionObject2 = localTrackingBaseSessionObject1;
      localSynergyRequest = createPostRequest(localTrackingBaseSessionObject2);
    } while (localSynergyRequest == null);
    localSynergyRequest.httpRequest.runInBackground = paramBoolean;
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = Integer.valueOf(localTrackingBaseSessionObject2.repostCount);
    arrayOfObject[1] = Integer.valueOf(1 + localTrackingBaseSessionObject2.repostCount);
    Log.Helper.LOGD(this, "Event queue marshalled. Incrementing repost count from %d to %d", arrayOfObject);
    localTrackingBaseSessionObject2.repostCount = (1 + localTrackingBaseSessionObject2.repostCount);
    this.m_isRequestInProgress = true;
    SynergyNetwork.getComponent().sendRequest(localSynergyRequest, new SynergyNetworkConnectionCallback()
    {
      public void callback(SynergyNetworkConnectionHandle paramAnonymousSynergyNetworkConnectionHandle)
      {
        int i = 0;
        double d1 = 1.0D;
        for (;;)
        {
          try
          {
            if (paramAnonymousSynergyNetworkConnectionHandle.getResponse().getError() == null)
            {
              TrackingBase.this.removeSessionAndFillQueue(localTrackingBaseSessionObject2);
              TrackingBase.this.m_postRetryDelay = 1.0D;
              d1 = TrackingBase.this.m_postInterval;
              Log.Helper.LOGI(this, "Telemetry post request finished, resetting isRequestInProgress flag to false.", new Object[0]);
              TrackingBase.access$902(TrackingBase.this, false);
              if (i != 0)
              {
                double d2 = TrackingBase.this.m_postRetryDelay;
                TrackingBase localTrackingBase = TrackingBase.this;
                localTrackingBase.m_postRetryDelay = (2.0D * localTrackingBase.m_postRetryDelay);
                if (TrackingBase.this.m_postRetryDelay > 300.0D) {
                  TrackingBase.this.m_postRetryDelay = 300.0D;
                }
                Object[] arrayOfObject2 = new Object[2];
                arrayOfObject2[0] = Double.valueOf(d2);
                arrayOfObject2[1] = Integer.valueOf(TrackingBase.this.m_sessionsToPost.size());
                Log.Helper.LOGI(this, "Posting a retry with delay of %s due to failed send. Queue size: %d", arrayOfObject2);
                TrackingBase.this.resetPostTimer(d2, false);
              }
            }
            else
            {
              Object[] arrayOfObject1 = new Object[1];
              arrayOfObject1[0] = paramAnonymousSynergyNetworkConnectionHandle.getResponse().getError().getLocalizedMessage();
              Log.Helper.LOGE(this, "Failed to send tracking events. Error: %s", arrayOfObject1);
              i = 1;
              continue;
            }
            if ((TrackingBase.this.m_sessionsToPost != null) && (!TrackingBase.this.m_sessionsToPost.isEmpty()))
            {
              Object[] arrayOfObject4 = new Object[1];
              arrayOfObject4[0] = Integer.valueOf(TrackingBase.this.m_sessionsToPost.size());
              Log.Helper.LOGI(this, "More items found in the queue. Post the next one now. Queue size: %d", arrayOfObject4);
              TrackingBase.this.resetPostTimer(0.0D, false);
              continue;
            }
            arrayOfObject3 = new Object[1];
          }
          finally {}
          Object[] arrayOfObject3;
          arrayOfObject3[0] = Integer.valueOf(TrackingBase.this.m_sessionsToPost.size());
          Log.Helper.LOGI(this, "No more items found in the queue. Wait on the timer. Queue size: %d", arrayOfObject3);
          TrackingBase.this.resetPostTimer(d1, true);
        }
      }
    });
  }
  
  private void removeSessionAndFillQueue(TrackingBaseSessionObject paramTrackingBaseSessionObject)
  {
    this.m_sessionsToPost.remove(paramTrackingBaseSessionObject);
    PersistenceService.removePersistenceForNimbleComponent(getFilenameForSessionID(this.m_firstSessionIDNumber), Persistence.Storage.DOCUMENT);
    this.m_firstSessionIDNumber = (1L + this.m_firstSessionIDNumber);
    fillSessionsToPost();
    saveToPersistence();
  }
  
  private void saveSessionDataToPersistent()
  {
    Persistence localPersistence = PersistenceService.getPersistenceForNimbleComponent(getComponentId(), Persistence.Storage.CACHE);
    Log.Helper.LOGI(this, "Saving event queue to persistence.", new Object[0]);
    localPersistence.setValue("sessionData" + this.m_name, this.m_customSessionData);
    localPersistence.synchronize();
  }
  
  private void saveSessionToFile(TrackingBaseSessionObject paramTrackingBaseSessionObject, long paramLong)
  {
    String str = getFilenameForSessionID(paramLong);
    Persistence localPersistence = PersistenceService.getPersistenceForNimbleComponent(str, Persistence.Storage.DOCUMENT);
    if (localPersistence.getBackUp()) {
      localPersistence.setBackUp(false);
    }
    try
    {
      localPersistence.setValue("savedSession", paramTrackingBaseSessionObject);
      localPersistence.synchronize();
      PersistenceService.cleanReferenceToPersistence(str, Persistence.Storage.DOCUMENT);
      return;
    }
    catch (OutOfMemoryError localOutOfMemoryError)
    {
      for (;;)
      {
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = localOutOfMemoryError.getLocalizedMessage();
        Log.Helper.LOGE(this, "OutOfMemoryError occurred while saving a session object to file. Exception: %s", arrayOfObject);
      }
    }
  }
  
  private void saveToPersistence()
  {
    Persistence localPersistence1 = PersistenceService.getPersistenceForNimbleComponent(getComponentId(), Persistence.Storage.CACHE);
    Log.Helper.LOGD(this, "Saving event queue to persistence (cache storage).", new Object[0]);
    try
    {
      localPersistence1.setValue("dataVersion" + this.m_name, String.valueOf(3));
      localPersistence1.setValue("currentSessionObject", this.m_currentSessionObject);
      localPersistence1.setValue("eventQueue" + this.m_name, this.m_pendingEvents);
      localPersistence1.setValue("sessionData", this.m_customSessionData);
      localPersistence1.setValue("loggedInToOrigin", Boolean.valueOf(this.m_loggedInToOrigin));
      localPersistence1.setValue("firstSessionIDNumber", Long.valueOf(this.m_firstSessionIDNumber));
      localPersistence1.setValue("lastSessionIDNumber", Long.valueOf(this.m_lastSessionIDNumber));
      localPersistence1.setValue("queuedSessionObjects" + this.m_name, this.m_sessionsToPost);
      localPersistence1.synchronize();
      Persistence localPersistence2 = PersistenceService.getPersistenceForNimbleComponent(getComponentId(), Persistence.Storage.DOCUMENT);
      Log.Helper.LOGD(this, "Saving tracking enable/disable flag to persistence (document storage).", new Object[0]);
      if (!localPersistence2.getBackUp()) {
        localPersistence2.setBackUp(true);
      }
      localPersistence2.setValue("trackingEnabledFlag", Boolean.valueOf(this.m_enable));
      localPersistence2.synchronize();
      return;
    }
    catch (OutOfMemoryError localOutOfMemoryError)
    {
      for (;;)
      {
        Object[] arrayOfObject2 = new Object[2];
        arrayOfObject2[0] = Integer.valueOf(this.m_maxQueueLength);
        arrayOfObject2[1] = localOutOfMemoryError.getLocalizedMessage();
        Log.Helper.LOGE(this, "OutOfMemoryError in saving m_sessionsToPost to persistence! MaxQueueLength is %s. Exception: %s", arrayOfObject2);
      }
    }
    catch (Throwable localThrowable)
    {
      Object[] arrayOfObject1 = new Object[1];
      arrayOfObject1[0] = localThrowable.getLocalizedMessage();
      Log.Helper.LOGE(this, "Caught generic throwable while saving to persistence! Discarding attempt to save. Exception: %s", arrayOfObject1);
    }
  }
  
  public void addCustomSessionData(String paramString1, String paramString2)
  {
    if ((!Utility.validString(paramString1)) || (!Utility.validString(paramString2))) {
      return;
    }
    SessionData localSessionData = new SessionData();
    localSessionData.key = paramString1;
    localSessionData.value = paramString2;
    this.m_customSessionData.add(localSessionData);
    saveSessionDataToPersistent();
  }
  
  public void addObserverForSynergyEnvironmentUpdateFinished()
  {
    Utility.registerReceiver("nimble.environment.notification.startup_requests_finished", this.m_receiver);
  }
  
  public void cleanup()
  {
    killPostTimer();
    EASPDataLoader.deleteDatFile(EASPDataLoader.getTrackingDatFilePath());
  }
  
  public void clearCustomSessionData()
  {
    this.m_customSessionData.clear();
    saveSessionDataToPersistent();
  }
  
  abstract List<Map<String, String>> convertEvent(Tracking.Event paramEvent);
  
  abstract SynergyRequest createPostRequest(TrackingBaseSessionObject paramTrackingBaseSessionObject);
  
  public boolean getEnable()
  {
    try
    {
      boolean bool = this.m_enable;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public String getLogSourceTitle()
  {
    return "TrackingBase";
  }
  
  abstract String getPersistenceIdentifier();
  
  double getPostInterval()
  {
    return this.m_postInterval;
  }
  
  void logEvent(Tracking.Event paramEvent)
  {
    List localList = convertEvent(paramEvent);
    if ((localList != null) && (!localList.isEmpty()))
    {
      Iterator localIterator = localList.iterator();
      while (localIterator.hasNext())
      {
        Map localMap = (Map)localIterator.next();
        this.m_currentSessionObject.events.add(localMap);
        Log.Helper.LOGD(this, "Logged event, %s: \n", new Object[] { localMap });
      }
      if (((this.m_postTimer == null) || ((!this.m_postTimer.isRunning()) && (!this.m_isRequestInProgress))) && (isAbleToPostEvent())) {
        resetPostTimer();
      }
    }
    if (paramEvent.type.equals("NIMBLESTANDARD::SESSION_END"))
    {
      Log.Helper.LOGD(this, "Logging session end event, " + paramEvent.type + ". Posting event queue now.", new Object[0]);
      postPendingEvents(true);
    }
  }
  
  /* Error */
  public void logEvent(String paramString, Map<String, String> paramMap)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 118	com/ea/nimble/tracking/TrackingBase:m_enable	Z
    //   6: istore 4
    //   8: iload 4
    //   10: ifne +6 -> 16
    //   13: aload_0
    //   14: monitorexit
    //   15: return
    //   16: new 591	com/ea/nimble/tracking/Tracking$Event
    //   19: dup
    //   20: invokespecial 605	com/ea/nimble/tracking/Tracking$Event:<init>	()V
    //   23: astore 5
    //   25: aload 5
    //   27: aload_1
    //   28: putfield 594	com/ea/nimble/tracking/Tracking$Event:type	Ljava/lang/String;
    //   31: aload 5
    //   33: aload_2
    //   34: putfield 609	com/ea/nimble/tracking/Tracking$Event:parameters	Ljava/util/Map;
    //   37: aload 5
    //   39: new 611	java/util/Date
    //   42: dup
    //   43: invokespecial 612	java/util/Date:<init>	()V
    //   46: putfield 616	com/ea/nimble/tracking/Tracking$Event:timestamp	Ljava/util/Date;
    //   49: aload_0
    //   50: aload 5
    //   52: invokevirtual 618	com/ea/nimble/tracking/TrackingBase:logEvent	(Lcom/ea/nimble/tracking/Tracking$Event;)V
    //   55: goto -42 -> 13
    //   58: astore_3
    //   59: aload_0
    //   60: monitorexit
    //   61: aload_3
    //   62: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	63	0	this	TrackingBase
    //   0	63	1	paramString	String
    //   0	63	2	paramMap	Map<String, String>
    //   58	4	3	localObject	Object
    //   6	3	4	bool	boolean
    //   23	28	5	localEvent	Tracking.Event
    // Exception table:
    //   from	to	target	type
    //   2	8	58	finally
    //   16	55	58	finally
  }
  
  abstract void packageCurrentSession();
  
  protected void queueCurrentEventsForPost()
  {
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Integer.valueOf(this.m_sessionsToPost.size());
    Log.Helper.LOGI(this, "queueCurrentEventsForPost called. Starting queue size: %d", arrayOfObject);
    if (this.m_sessionsToPost == null) {
      this.m_sessionsToPost = new ArrayList();
    }
    if (this.m_currentSessionObject == null) {
      Log.Helper.LOGE(this, "Unexpected state, currentSessionObject is null.", new Object[0]);
    }
    for (;;)
    {
      this.m_currentSessionObject = new TrackingBaseSessionObject();
      return;
      if (this.m_currentSessionObject.countOfEvents() == 0) {
        Log.Helper.LOGE(this, "Unexpected state, currentSessionObject events list is null or empty.", new Object[0]);
      } else {
        addCurrentSessionObjectToBackOfQueue();
      }
    }
  }
  
  void resetPostTimer()
  {
    resetPostTimer(this.m_postInterval);
  }
  
  void resetPostTimer(double paramDouble)
  {
    resetPostTimer(paramDouble, true);
  }
  
  void resetPostTimer(double paramDouble, boolean paramBoolean)
  {
    double d = paramDouble;
    if (d < 0.0D)
    {
      Log.Helper.LOGE(this, "resetPostTimer called with an invalid period: period < 0.0. Timer reset with period 0.0 instead", new Object[0]);
      d = 0.0D;
    }
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Double.valueOf(paramDouble);
    Log.Helper.LOGI(this, "Resetting event post timer for %s seconds.", arrayOfObject);
    killPostTimer();
    this.m_postTimer = new Timer(new PostTask(paramBoolean));
    this.m_postTimer.schedule(d, false);
  }
  
  public void restore()
  {
    Persistence localPersistence1 = PersistenceService.getPersistenceForNimbleComponent(getComponentId(), Persistence.Storage.DOCUMENT);
    Persistence localPersistence2 = PersistenceService.getPersistenceForNimbleComponent(getComponentId(), Persistence.Storage.CACHE);
    Serializable localSerializable1 = localPersistence1.getValue("trackingEnabledFlag");
    if ((localSerializable1 != null) && (localSerializable1.getClass() == Boolean.class)) {}
    ArrayList localArrayList4;
    for (;;)
    {
      int k;
      int m;
      TrackingBaseSessionObject localTrackingBaseSessionObject;
      try
      {
        this.m_enable = ((Boolean)localSerializable1).booleanValue();
        if (!this.m_enable) {
          break label1285;
        }
        Serializable localSerializable5 = localPersistence2.getValue("currentSessionObject" + this.m_name);
        if ((localSerializable5 != null) && (localSerializable5.getClass() == TrackingBaseSessionObject.class))
        {
          if (this.m_currentSessionObject.countOfEvents() > 0) {
            Log.Helper.LOGE(this, "Events logged during startup before persistently cached events were restored. Overwriting logged events with cached events!", new Object[0]);
          }
          this.m_currentSessionObject = ((TrackingBaseSessionObject)localSerializable5);
        }
        Serializable localSerializable6 = localPersistence2.getValue("queuedSessionObjects" + this.m_name);
        if ((localSerializable6 == null) || (localSerializable6.getClass() != ArrayList.class)) {
          break label866;
        }
        ArrayList localArrayList3 = (ArrayList)localSerializable6;
        localArrayList4 = new ArrayList();
        k = localArrayList3.size();
        Serializable localSerializable8 = localPersistence2.getValue("firstSessionIDNumber");
        if ((localSerializable8 == null) || (localSerializable8.getClass() != Long.class)) {
          break label814;
        }
        this.m_firstSessionIDNumber = ((Long)localSerializable8).longValue();
        Serializable localSerializable9 = localPersistence2.getValue("lastSessionIDNumber");
        if ((localSerializable9 == null) || (localSerializable9.getClass() != Long.class)) {
          break label822;
        }
        this.m_lastSessionIDNumber = ((Long)localSerializable9).longValue();
        m = 0;
        if (m >= k) {
          break;
        }
        localTrackingBaseSessionObject = (TrackingBaseSessionObject)localArrayList3.get(m);
        if (m >= this.m_maxQueueLength) {
          break label839;
        }
        localArrayList4.add(localTrackingBaseSessionObject);
        m++;
        continue;
      }
      catch (ClassCastException localClassCastException3)
      {
        Object[] arrayOfObject4 = new Object[2];
        arrayOfObject4[0] = "trackingEnabledFlag";
        arrayOfObject4[1] = localSerializable1.getClass().getSimpleName();
        Log.Helper.LOGE(this, "Invalid persistence value for %s, expected Boolean, got %s", arrayOfObject4);
        continue;
      }
      Serializable localSerializable2 = localPersistence2.getValue("trackingEnabledFlag");
      if ((localSerializable2 != null) && (localSerializable2.getClass() == Boolean.class))
      {
        try
        {
          this.m_enable = ((Boolean)localSerializable2).booleanValue();
        }
        catch (ClassCastException localClassCastException2)
        {
          Object[] arrayOfObject3 = new Object[2];
          arrayOfObject3[0] = "trackingEnabledFlag";
          arrayOfObject3[1] = localSerializable2.getClass().getSimpleName();
          Log.Helper.LOGE(this, "Invalid persistence value for %s, expected Boolean, got %s", arrayOfObject3);
        }
      }
      else
      {
        try
        {
          EASPDataLoader.EASPDataBuffer localEASPDataBuffer2 = EASPDataLoader.loadDatFile(EASPDataLoader.getTrackingDatFilePath());
          localEASPDataBuffer1 = localEASPDataBuffer2;
        }
        catch (FileNotFoundException localFileNotFoundException)
        {
          for (;;)
          {
            try
            {
              boolean bool5;
              int n;
              EASPDataLoader.readLogEvent(localEASPDataBuffer1.m_decryptedByteBuffer);
              n++;
            }
            catch (Exception localException2)
            {
              EASPDataLoader.EASPDataBuffer localEASPDataBuffer1;
              int i;
              int i1;
              boolean bool2;
              Object[] arrayOfObject2;
              boolean bool3;
              Log.Helper.LOGD(this, "Exception reading EASP tracking data file: " + localException2, new Object[0]);
              localException2.printStackTrace();
              continue;
            }
            localFileNotFoundException = localFileNotFoundException;
            Log.Helper.LOGD(this, "No EASP tracking file.", new Object[0]);
            localEASPDataBuffer1 = null;
          }
        }
        catch (Exception localException1)
        {
          for (;;)
          {
            Log.Helper.LOGE(this, "Exception loading EASP tracking file.", new Object[0]);
            localEASPDataBuffer1 = null;
          }
          i1 = localEASPDataBuffer1.m_decryptedByteBuffer.getInt();
          bool2 = EASPDataLoader.readBooleanByte(localEASPDataBuffer1.m_decryptedByteBuffer);
          arrayOfObject2 = new Object[2];
          arrayOfObject2[0] = Integer.valueOf(i1);
          arrayOfObject2[1] = Boolean.valueOf(bool2);
          Log.Helper.LOGD(this, "EASP user level(%d), disabled(%b)", arrayOfObject2);
          if (bool2) {
            break label732;
          }
        }
        i = 0;
        if (localEASPDataBuffer1 != null)
        {
          Log.Helper.LOGD(this, "Nimble tracking persistence data does not exist, but EASP tracking data found. Loaded dat version" + localEASPDataBuffer1.m_version, new Object[0]);
          if (!localEASPDataBuffer1.m_version.equalsIgnoreCase("1.00.03"))
          {
            bool5 = localEASPDataBuffer1.m_version.equalsIgnoreCase("1.00.02");
            i = 0;
            if (!bool5) {}
          }
          else
          {
            n = 0;
            if (n >= 3) {}
          }
        }
        label732:
        for (bool3 = true;; bool3 = false)
        {
          this.m_enable = bool3;
          i = 1;
          boolean bool4 = localEASPDataBuffer1.m_version.equalsIgnoreCase("1.00.03");
          if (bool4) {}
          try
          {
            String str2 = EASPDataLoader.readString(localEASPDataBuffer1.m_decryptedByteBuffer);
            str1 = str2;
          }
          catch (Exception localException3)
          {
            for (;;)
            {
              Log.Helper.LOGE(this, "Exception reading ageGateDob string: " + localException3, new Object[0]);
              String str1 = null;
            }
          }
          if ((str1 != null) && (str1.length() > 0))
          {
            Log.Helper.LOGD(this, "Setting age gate DOB from EASP data,  %s.", new Object[] { str1 });
            addCustomSessionData("ageGateDob", str1);
          }
          if (i != 0) {
            break;
          }
          configureTrackingOnFirstInstall();
          break;
        }
        label814:
        this.m_firstSessionIDNumber = 0L;
        continue;
        label822:
        this.m_lastSessionIDNumber = (this.m_firstSessionIDNumber + (k - 1));
        continue;
        label839:
        saveSessionToFile(localTrackingBaseSessionObject, this.m_firstSessionIDNumber + m);
      }
    }
    this.m_sessionsToPost = localArrayList4;
    fillSessionsToPost();
    label866:
    Serializable localSerializable7 = localPersistence2.getValue("eventQueue" + this.m_name);
    Object localObject2 = null;
    if (localSerializable7 != null)
    {
      Class localClass = localSerializable7.getClass();
      localObject2 = null;
      if (localClass == ArrayList.class)
      {
        int j = ((ArrayList)localSerializable7).size();
        localObject2 = null;
        if (j > 0)
        {
          Object localObject3 = ((ArrayList)localSerializable7).get(0);
          localObject2 = null;
          if (localObject3 != null)
          {
            boolean bool1 = localObject3 instanceof Map;
            localObject2 = null;
            if (bool1)
            {
              ArrayList localArrayList2 = new ArrayList((ArrayList)localSerializable7);
              localObject2 = localArrayList2;
              localPersistence2.setValue("eventQueue" + this.m_name, null);
            }
          }
        }
      }
    }
    if ((localObject2 != null) && (localObject2.size() > 0))
    {
      if (this.m_currentSessionObject.countOfEvents() <= 0) {
        break label1262;
      }
      Log.Helper.LOGE(this, "Restored old format event queue in addition to new format session object. Ignoring event queue.", new Object[0]);
    }
    for (;;)
    {
      if (this.m_currentSessionObject.countOfEvents() > 0) {
        resetPostTimer();
      }
      Serializable localSerializable3 = localPersistence2.getValue("sessionData" + this.m_name);
      if ((localSerializable3 != null) && (localSerializable3.getClass() == ArrayList.class) && (((ArrayList)localSerializable3).size() > 0))
      {
        Object localObject1 = ((ArrayList)localSerializable3).get(0);
        if ((localObject1 != null) && ((localObject1 instanceof SessionData)))
        {
          ArrayList localArrayList1 = new ArrayList((ArrayList)localSerializable3);
          if (localArrayList1 != null) {
            this.m_customSessionData = localArrayList1;
          }
        }
      }
      Serializable localSerializable4 = localPersistence2.getValue("loggedInToOrigin");
      if ((localSerializable4 != null) && (localSerializable4.getClass() == Boolean.class)) {}
      try
      {
        this.m_loggedInToOrigin = ((Boolean)localSerializable4).booleanValue();
        if (this.m_originLoginStatusChangedReceiver == null)
        {
          OriginLoginStatusChangedReceiver localOriginLoginStatusChangedReceiver = new OriginLoginStatusChangedReceiver(null);
          this.m_originLoginStatusChangedReceiver = localOriginLoginStatusChangedReceiver;
          Utility.registerReceiver("nimble.notification.LoginStatusChanged", this.m_originLoginStatusChangedReceiver);
        }
        if (SynergyEnvironment.getComponent().isDataAvailable())
        {
          this.m_postInterval = SynergyEnvironment.getComponent().getTrackingPostInterval();
          return;
          label1262:
          Log.Helper.LOGD(this, "Restored old format event queue, storing in new format session object.", new Object[0]);
          this.m_currentSessionObject.events = localObject2;
          continue;
          label1285:
          this.m_currentSessionObject = new TrackingBaseSessionObject();
        }
      }
      catch (ClassCastException localClassCastException1)
      {
        for (;;)
        {
          Object[] arrayOfObject1 = new Object[2];
          arrayOfObject1[0] = "loggedInToOrigin";
          arrayOfObject1[1] = localSerializable4.getClass().getSimpleName();
          Log.Helper.LOGE(this, "Invalid persistence value for %s, expected Boolean, got %s", arrayOfObject1);
        }
        addObserverForSynergyEnvironmentUpdateFinished();
      }
    }
  }
  
  public void resume()
  {
    if (getEnable()) {
      resetPostTimer();
    }
    if (this.m_originLoginStatusChangedReceiver == null)
    {
      this.m_originLoginStatusChangedReceiver = new OriginLoginStatusChangedReceiver(null);
      Utility.registerReceiver("nimble.notification.LoginStatusChanged", this.m_originLoginStatusChangedReceiver);
    }
    this.m_postRetryDelay = 1.0D;
  }
  
  public void setEnable(boolean paramBoolean)
  {
    for (;;)
    {
      try
      {
        Object[] arrayOfObject1 = new Object[1];
        String str;
        if (paramBoolean)
        {
          str = "ENABLED";
          arrayOfObject1[0] = str;
          Log.Helper.LOGI(this, "setEnable called. enable = %s", arrayOfObject1);
          boolean bool = this.m_enable;
          if (bool != paramBoolean) {}
        }
        else
        {
          str = "DISABLED";
          continue;
        }
        if (!paramBoolean)
        {
          HashMap localHashMap = new HashMap();
          localHashMap.put("eventType", "NIMBLESTANDARD::USER_TRACKING_OPTOUT");
          logEvent("NIMBLESTANDARD::USER_TRACKING_OPTOUT", localHashMap);
          postPendingEvents(false);
          if (this.m_currentSessionObject.countOfEvents() > 0)
          {
            Object[] arrayOfObject2 = new Object[1];
            arrayOfObject2[0] = Integer.valueOf(this.m_currentSessionObject.countOfEvents());
            Log.Helper.LOGI(this, "Removing %d remaining events that couldn't be sent from queue.", arrayOfObject2);
          }
          this.m_currentSessionObject = new TrackingBaseSessionObject();
          if ((this.m_sessionsToPost != null) && (this.m_sessionsToPost.size() > 0))
          {
            Log.Helper.LOGI(this, "Removing unposted sessions.", new Object[0]);
            this.m_sessionsToPost.clear();
          }
          killPostTimer();
          this.m_enable = paramBoolean;
          saveToPersistence();
        }
        else
        {
          resetPostTimer();
        }
      }
      finally {}
    }
  }
  
  void setPostInterval(double paramDouble)
  {
    this.m_postInterval = paramDouble;
  }
  
  protected void setup()
  {
    this.m_postRetryDelay = 1.0D;
  }
  
  public void suspend()
  {
    if (this.m_networkStatusChangedReceiver != null)
    {
      Utility.unregisterReceiver(this.m_networkStatusChangedReceiver);
      this.m_networkStatusChangedReceiver = null;
    }
    if (this.m_originLoginStatusChangedReceiver != null)
    {
      Utility.unregisterReceiver(this.m_originLoginStatusChangedReceiver);
      this.m_originLoginStatusChangedReceiver = null;
    }
    killPostTimer();
    saveToPersistence();
    EASPDataLoader.deleteDatFile(EASPDataLoader.getTrackingDatFilePath());
  }
  
  private class OriginLoginStatusChangedReceiver
    extends BroadcastReceiver
  {
    private OriginLoginStatusChangedReceiver() {}
    
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if (paramIntent.getAction().equals("nimble.notification.LoginStatusChanged"))
      {
        if (paramIntent.getExtras() == null) {
          break label85;
        }
        String str = paramIntent.getExtras().getString("STATUS");
        if ((str.equals("live")) || (str.equals("autoLogin")))
        {
          Log.Helper.LOGI(this, "Login status update, TRUE", new Object[0]);
          TrackingBase.this.m_loggedInToOrigin = true;
        }
      }
      else
      {
        return;
      }
      Log.Helper.LOGI(this, "Login status update, FALSE", new Object[0]);
      TrackingBase.this.m_loggedInToOrigin = false;
      return;
      label85:
      Log.Helper.LOGI(this, "Login status updated event received without extras bundle. Marking NOT logged in to Origin.", new Object[0]);
      TrackingBase.this.m_loggedInToOrigin = false;
    }
  }
  
  private class PostTask
    implements Runnable
  {
    private boolean m_packageEventsOnExpiry = false;
    
    public PostTask()
    {
      this(true);
    }
    
    public PostTask(boolean paramBoolean)
    {
      this.m_packageEventsOnExpiry = paramBoolean;
    }
    
    public void run()
    {
      TrackingBase.this.postIntervalTimerExpired(this.m_packageEventsOnExpiry);
    }
  }
  
  public static class SessionData
    implements Serializable
  {
    private static final long serialVersionUID = 465486L;
    String key;
    String value;
  }
  
  private class StartupRequestsFinishedReceiver
    extends BroadcastReceiver
  {
    private StartupRequestsFinishedReceiver() {}
    
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if ((paramIntent.getAction().equals("nimble.environment.notification.startup_requests_finished")) && (paramIntent.getExtras() != null) && (paramIntent.getExtras().getString("result").equals("1")))
      {
        int i = SynergyEnvironment.getComponent().getTrackingPostInterval();
        if ((i < 0) || (i == -1)) {
          TrackingBase.access$302(TrackingBase.this, 1.0D);
        }
        while (TrackingBase.this.m_sessionsToPost != null)
        {
          Iterator localIterator = TrackingBase.this.m_sessionsToPost.iterator();
          while (localIterator.hasNext())
          {
            TrackingBaseSessionObject localTrackingBaseSessionObject = (TrackingBaseSessionObject)localIterator.next();
            if ((localTrackingBaseSessionObject != null) && (localTrackingBaseSessionObject.sessionData != null))
            {
              Object localObject1 = localTrackingBaseSessionObject.sessionData.get("sellId");
              if ((localObject1 != null) && ((localObject1 instanceof String)) && ((((String)localObject1).equals("")) || (((String)localObject1).equals("0"))))
              {
                String str3 = SynergyEnvironment.getComponent().getSellId();
                localTrackingBaseSessionObject.sessionData.put("sellId", Utility.safeString(str3));
                if ((str3 == null) || (str3.equals("")) || (str3.equals("0"))) {
                  Log.Helper.LOGE(this, "Sell Id was still null after synergy update", new Object[0]);
                }
              }
              Object localObject2 = localTrackingBaseSessionObject.sessionData.get("hwId");
              if ((localObject2 != null) && ((localObject2 instanceof String)) && (((String)localObject2).equals("")))
              {
                String str2 = SynergyEnvironment.getComponent().getEAHardwareId();
                localTrackingBaseSessionObject.sessionData.put("hwId", Utility.safeString(str2));
                if ((str2 == null) || (str2.equals(""))) {
                  Log.Helper.LOGE(this, "Hardware Id was still null after synergy update", new Object[0]);
                }
              }
              Object localObject3 = localTrackingBaseSessionObject.sessionData.get("deviceId");
              if ((localObject3 != null) && ((localObject3 instanceof String)) && ((((String)localObject3).equals("")) || (((String)localObject3).equals("0"))))
              {
                String str1 = SynergyEnvironment.getComponent().getEADeviceId();
                localTrackingBaseSessionObject.sessionData.put("deviceId", Utility.safeString(str1));
                if ((str1 == null) || (str1.equals("")) || (str1.equals("0"))) {
                  Log.Helper.LOGE(this, "Device Id was still null after synergy update", new Object[0]);
                }
              }
            }
          }
          TrackingBase.access$302(TrackingBase.this, i);
        }
        Log.Helper.LOGI(this, "Synergy environment update successful. Removing observer and re-attempting event post.", new Object[0]);
        Utility.unregisterReceiver(TrackingBase.this.m_receiver);
        if (TrackingBase.this.m_isPostPending)
        {
          TrackingBase.access$602(TrackingBase.this, false);
          TrackingBase.this.resetPostTimer(0.0D);
        }
      }
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ea.nimble.tracking.TrackingBase
 * JD-Core Version:    0.7.0.1
 */