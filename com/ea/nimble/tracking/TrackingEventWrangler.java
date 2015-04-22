package com.ea.nimble.tracking;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import com.ea.nimble.ApplicationEnvironment;
import com.ea.nimble.ApplicationLifecycle;
import com.ea.nimble.Base;
import com.ea.nimble.Component;
import com.ea.nimble.EASPDataLoader;
import com.ea.nimble.EASPDataLoader.EASPDataBuffer;
import com.ea.nimble.IApplicationEnvironment;
import com.ea.nimble.IApplicationLifecycle;
import com.ea.nimble.IApplicationLifecycle.ApplicationLifecycleCallbacks;
import com.ea.nimble.Log.Helper;
import com.ea.nimble.LogSource;
import com.ea.nimble.Persistence;
import com.ea.nimble.Persistence.Storage;
import com.ea.nimble.PersistenceService;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

class TrackingEventWrangler
  extends Component
  implements IApplicationLifecycle.ApplicationLifecycleCallbacks, LogSource
{
  private static final String APP_VERSION_PERSISTENCE_ID = "applicationBundleVersion";
  public static final String COMPONENT_ID = "com.ea.nimble.tracking.eventwrangler";
  private Long m_sessionStartTimestamp;
  
  private static void initialize()
  {
    Base.registerComponent(new TrackingEventWrangler(), "com.ea.nimble.tracking.eventwrangler");
  }
  
  private void logAndCheckEvent(String paramString)
  {
    logAndCheckEvent(paramString, null);
  }
  
  private void logAndCheckEvent(String paramString, Map<String, String> paramMap)
  {
    if (Tracking.isSessionStartEvent(paramString)) {
      if (this.m_sessionStartTimestamp != null)
      {
        Log.Helper.LOGE(this, "Pre-existing session start timestamp found while logging new session start! Overwriting previous session start timestamp.", new Object[0]);
        this.m_sessionStartTimestamp = Long.valueOf(System.currentTimeMillis());
      }
    }
    for (;;)
    {
      ITracking localITracking = (ITracking)Base.getComponent("com.ea.nimble.tracking");
      if (localITracking != null) {
        localITracking.logEvent(paramString, paramMap);
      }
      return;
      Log.Helper.LOGD(this, "Marking session start time.", new Object[0]);
      break;
      if (Tracking.isSessionEndEvent(paramString)) {
        if (this.m_sessionStartTimestamp == null)
        {
          Log.Helper.LOGE(this, "No session start timestamp found while logging new session end! Skip logging 'session time' event.", new Object[0]);
        }
        else
        {
          double d = (System.currentTimeMillis() - this.m_sessionStartTimestamp.longValue()) / 1000.0D;
          Locale localLocale = Locale.US;
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = Double.valueOf(d);
          String str = String.format(localLocale, "%.0f", arrayOfObject);
          Log.Helper.LOGD(this, "Logging session time, %s seconds.", new Object[] { str });
          HashMap localHashMap = new HashMap();
          localHashMap.put("NIMBLESTANDARD::KEY_DURATION", str);
          logAndCheckEvent("NIMBLESTANDARD::SESSION_TIME", localHashMap);
          this.m_sessionStartTimestamp = null;
        }
      }
    }
  }
  
  public void cleanup()
  {
    ApplicationLifecycle.getComponent().unregisterApplicationLifecycleCallbacks(this);
  }
  
  public String getComponentId()
  {
    return "com.ea.nimble.tracking.eventwrangler";
  }
  
  public String getLogSourceTitle()
  {
    return "Tracking";
  }
  
  public void onApplicationLaunch(Intent paramIntent)
  {
    if (paramIntent.getData() != null)
    {
      logAndCheckEvent("NIMBLESTANDARD::APPSTART_FROMURL");
      return;
    }
    if ((paramIntent.getStringExtra("PushNotification") != null) || (ApplicationEnvironment.getComponent().getApplicationContext().getSharedPreferences("PushNotification", 0).getString("PushNotification", null) != null))
    {
      Log.Helper.LOGI(this, "Awesome. PN launched me", new Object[0]);
      logAndCheckEvent("NIMBLESTANDARD::APPSTART_FROMPUSH");
      return;
    }
    Persistence localPersistence = PersistenceService.getPersistenceForNimbleComponent("com.ea.nimble.tracking.eventwrangler", Persistence.Storage.CACHE);
    String str1 = localPersistence.getStringValue("applicationBundleVersion");
    String str2 = ApplicationEnvironment.getComponent().getApplicationVersion();
    Log.Helper.LOGD(this, "Current app version, %s. Cached app version, %s", new Object[] { str2, str1 });
    if (str1 == null)
    {
      localPersistence.setValue("applicationBundleVersion", str2);
      try
      {
        EASPDataLoader.EASPDataBuffer localEASPDataBuffer2 = EASPDataLoader.loadDatFile(EASPDataLoader.getTrackingDatFilePath());
        localEASPDataBuffer1 = localEASPDataBuffer2;
      }
      catch (FileNotFoundException localFileNotFoundException)
      {
        for (;;)
        {
          Log.Helper.LOGD(this, "No EASP tracking file.", new Object[0]);
          localEASPDataBuffer1 = null;
        }
      }
      catch (Exception localException)
      {
        for (;;)
        {
          Log.Helper.LOGE(this, "Exception loading EASP tracking file.", new Object[0]);
          EASPDataLoader.EASPDataBuffer localEASPDataBuffer1 = null;
        }
        logAndCheckEvent("NIMBLESTANDARD::APPSTART_AFTERINSTALL");
        return;
      }
      if (localEASPDataBuffer1 != null)
      {
        Log.Helper.LOGD(this, "EASP tracking file found. Counting as app update.", new Object[0]);
        logAndCheckEvent("NIMBLESTANDARD::APPSTART_AFTERUPGRADE");
        return;
      }
    }
    if (!str1.equals(str2))
    {
      localPersistence.setValue("applicationBundleVersion", str2);
      logAndCheckEvent("NIMBLESTANDARD::APPSTART_AFTERUPGRADE");
      return;
    }
    logAndCheckEvent("NIMBLESTANDARD::APPSTART_NORMAL");
  }
  
  public void onApplicationQuit()
  {
    logAndCheckEvent("NIMBLESTANDARD::SESSION_END");
  }
  
  public void onApplicationResume()
  {
    if (ApplicationEnvironment.getCurrentActivity().getIntent().getData() != null)
    {
      logAndCheckEvent("NIMBLESTANDARD::APPRESUME_FROMURL");
      return;
    }
    if (ApplicationEnvironment.getComponent().getApplicationContext().getSharedPreferences("PushNotification", 0).getString("PushNotification", null) != null)
    {
      logAndCheckEvent("NIMBLESTANDARD::APPRESUME_FROMPUSH");
      return;
    }
    logAndCheckEvent("NIMBLESTANDARD::SESSION_START");
  }
  
  public void onApplicationSuspend()
  {
    logAndCheckEvent("NIMBLESTANDARD::SESSION_END");
  }
  
  public void restore()
  {
    ApplicationLifecycle.getComponent().registerApplicationLifecycleCallbacks(this);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ea.nimble.tracking.TrackingEventWrangler
 * JD-Core Version:    0.7.0.1
 */