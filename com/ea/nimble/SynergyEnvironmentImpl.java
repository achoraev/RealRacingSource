package com.ea.nimble;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class SynergyEnvironmentImpl
  extends Component
  implements ISynergyEnvironment, LogSource
{
  private static final String PERSISTENCE_DATA_ID = "environmentData";
  public static final int SYNERGY_APP_VERSION_OK = 0;
  public static final int SYNERGY_APP_VERSION_UPDATE_RECOMMENDED = 1;
  public static final int SYNERGY_APP_VERSION_UPDATE_REQUIRED = 2;
  private static final String SYNERGY_INT_SERVER_URL = "http://synergy-int.eamobile.com:8081";
  private static final String SYNERGY_LIVE_SERVER_URL = "https://syn-dir.sn.eamobile.com";
  private static final String SYNERGY_STAGE_SERVER_URL = "https://synergy-stage.eamobile.com";
  public static final double SYNERGY_UPDATE_RATE_LIMIT_PERIOD_IN_SECONDS = 60.0D;
  public static final double SYNERGY_UPDATE_REFRESH_PERIOD_IN_SECONDS = 300.0D;
  private BaseCore m_core;
  private EnvironmentDataContainer m_environmentDataContainer;
  private BroadcastReceiver m_networkStatusChangeReceiver;
  private EnvironmentDataContainer m_previousValidEnvironmentDataContainer;
  private Long m_synergyEnvironmentUpdateRateLimitTriggerTimestamp;
  private SynergyEnvironmentUpdater m_synergyStartupObject;
  
  SynergyEnvironmentImpl(BaseCore paramBaseCore)
  {
    this.m_core = paramBaseCore;
    this.m_networkStatusChangeReceiver = null;
  }
  
  private void clearSynergyEnvironmentUpdateRateLimiting()
  {
    this.m_synergyEnvironmentUpdateRateLimitTriggerTimestamp = null;
  }
  
  private boolean isInSynergyEnvironmentUpdateRateLimitingPeriod()
  {
    return (this.m_synergyEnvironmentUpdateRateLimitTriggerTimestamp != null) && (System.currentTimeMillis() - this.m_synergyEnvironmentUpdateRateLimitTriggerTimestamp.longValue() <= 60000.0D);
  }
  
  private void restoreEnvironmentDataFromPersistent()
  {
    Persistence localPersistence = PersistenceService.getPersistenceForNimbleComponent("com.ea.nimble.synergyEnvironment", Persistence.Storage.CACHE);
    Serializable localSerializable;
    if (localPersistence != null)
    {
      localSerializable = localPersistence.getValue("environmentData");
      if (localSerializable == null) {
        Log.Helper.LOGD(this, "Environment persistence data value not found in persistence object. Probably first install.", new Object[0]);
      }
    }
    for (;;)
    {
      this.m_environmentDataContainer = null;
      return;
      try
      {
        this.m_environmentDataContainer = ((EnvironmentDataContainer)localSerializable);
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = this.m_environmentDataContainer.getMostRecentDirectorResponseTimestamp();
        Log.Helper.LOGD(this, "Restored environment data from persistent. Restored data timestamp, %s", arrayOfObject);
        Utility.sendBroadcast("nimble.environment.notification.restored_from_persistent", null);
        return;
      }
      catch (ClassCastException localClassCastException)
      {
        Log.Helper.LOGE(this, "Environment persistence data value is not the expected type.", new Object[0]);
      }
      continue;
      Log.Helper.LOGE(this, "Could not get environment persistence object to restore from", new Object[0]);
    }
  }
  
  private void saveEnvironmentDataToPersistent()
  {
    Persistence localPersistence = PersistenceService.getPersistenceForNimbleComponent("com.ea.nimble.synergyEnvironment", Persistence.Storage.CACHE);
    if (localPersistence != null)
    {
      Log.Helper.LOGD(this, "Saving environment data to persistent.", new Object[0]);
      localPersistence.setValue("environmentData", this.m_environmentDataContainer);
      localPersistence.synchronize();
      return;
    }
    Log.Helper.LOGE(this, "Could not get environment persistence object to save to.", new Object[0]);
  }
  
  private void startSynergyEnvironmentUpdate()
  {
    if (isUpdateInProgress()) {
      Log.Helper.LOGD(this, "Attempt made to start Synergy environment update while a previous one is active. Exiting.", new Object[0]);
    }
    do
    {
      return;
      if (Network.getComponent().getStatus() == Network.Status.OK) {
        break;
      }
    } while (this.m_networkStatusChangeReceiver != null);
    this.m_networkStatusChangeReceiver = new BroadcastReceiver()
    {
      public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
      {
        if ((paramAnonymousIntent.getAction().equals("nimble.notification.networkStatusChanged")) && (Network.getComponent().getStatus() == Network.Status.OK))
        {
          Log.Helper.LOGD(this, "Network restored. Starting Synergy environment update.", new Object[0]);
          Utility.unregisterReceiver(SynergyEnvironmentImpl.this.m_networkStatusChangeReceiver);
          SynergyEnvironmentImpl.access$002(SynergyEnvironmentImpl.this, null);
          SynergyEnvironmentImpl.this.startSynergyEnvironmentUpdate();
        }
      }
    };
    Log.Helper.LOGD(this, "Network not available to perform environment update. Setting receiver to listen for network status change.", new Object[0]);
    Utility.registerReceiver("nimble.notification.networkStatusChanged", this.m_networkStatusChangeReceiver);
    return;
    this.m_synergyStartupObject = new SynergyEnvironmentUpdater(this.m_core);
    this.m_previousValidEnvironmentDataContainer = this.m_environmentDataContainer;
    HashMap localHashMap = new HashMap();
    localHashMap.put("result", "1");
    Utility.sendBroadcast("nimble.environment.notification.startup_requests_started", localHashMap);
    this.m_synergyStartupObject.startSynergyStartupSequence(this.m_previousValidEnvironmentDataContainer, new SynergyEnvironmentUpdater.CompletionCallback()
    {
      public void callback(Exception paramAnonymousException)
      {
        if ((paramAnonymousException == null) && (SynergyEnvironmentImpl.this.m_synergyStartupObject != null) && (SynergyEnvironmentImpl.this.m_synergyStartupObject.getEnvironmentDataContainer() != null))
        {
          SynergyEnvironmentImpl.access$302(SynergyEnvironmentImpl.this, SynergyEnvironmentImpl.this.m_synergyStartupObject.getEnvironmentDataContainer());
          SynergyEnvironmentImpl.this.saveEnvironmentDataToPersistent();
          if (SynergyEnvironmentImpl.this.m_environmentDataContainer.getKeysOfDifferences(SynergyEnvironmentImpl.this.m_previousValidEnvironmentDataContainer) != null) {
            Utility.sendBroadcast("nimble.environment.notification.startup_environment_data_changed", null);
          }
          HashMap localHashMap2 = new HashMap();
          localHashMap2.put("result", "1");
          if ((ApplicationEnvironment.isMainApplicationRunning()) && (ApplicationEnvironment.getCurrentActivity() != null))
          {
            Log.Helper.LOGD(this, "App is running in forground, send the NOTIFICATION_STARTUP_REQUESTS_FINISHED notification", new Object[0]);
            Utility.sendBroadcast("nimble.environment.notification.startup_requests_finished", localHashMap2);
          }
        }
        for (;;)
        {
          SynergyEnvironmentImpl.access$202(SynergyEnvironmentImpl.this, null);
          return;
          Log.Helper.LOGI(this, "App is not running in forground, discard the NOTIFICATION_STARTUP_REQUESTS_FINISHED notification", new Object[0]);
          continue;
          Log.Helper.LOGE(this, "StartupError(%s)", new Object[] { paramAnonymousException });
          if ((paramAnonymousException instanceof Error))
          {
            Error localError = (Error)paramAnonymousException;
            if ((localError.isError(Error.Code.SYNERGY_GET_DIRECTION_TIMEOUT)) || (localError.isError(Error.Code.SYNERGY_SERVER_FULL)))
            {
              Log.Helper.LOGD(this, "GetDirection request timed out or ServerUnavailable signal received. Start rate limiting of /getDirection call.", new Object[0]);
              SynergyEnvironmentImpl.this.startSynergyEnvironmentUpdateRateLimiting();
            }
          }
          for (;;)
          {
            HashMap localHashMap1 = new HashMap();
            localHashMap1.put("result", "0");
            localHashMap1.put("error", paramAnonymousException.toString());
            if ((!ApplicationEnvironment.isMainApplicationRunning()) || (ApplicationEnvironment.getCurrentActivity() == null)) {
              break label316;
            }
            Log.Helper.LOGD(this, "App is running in forground, send the NOTIFICATION_STARTUP_REQUESTS_FINISHED notification", new Object[0]);
            Utility.sendBroadcast("nimble.environment.notification.startup_requests_finished", localHashMap1);
            break;
            if ((SynergyEnvironmentImpl.this.m_synergyStartupObject == null) || (SynergyEnvironmentImpl.this.m_synergyStartupObject.getEnvironmentDataContainer() == null)) {
              Log.Helper.LOGD(this, "Synergy Environment Update object or dataContainer null at callback. More than one update was being peroformed", new Object[0]);
            }
          }
          label316:
          Log.Helper.LOGI(this, "App is not running in forground, discard the NOTIFICATION_STARTUP_REQUESTS_FINISHED notification", new Object[0]);
        }
      }
    });
  }
  
  private void startSynergyEnvironmentUpdateRateLimiting()
  {
    this.m_synergyEnvironmentUpdateRateLimitTriggerTimestamp = Long.valueOf(System.currentTimeMillis());
  }
  
  public Error checkAndInitiateSynergyEnvironmentUpdate()
  {
    if (isUpdateInProgress()) {
      return new Error(Error.Code.SYNERGY_ENVIRONMENT_UPDATE_FAILURE, "Update in progress.");
    }
    if ((this.m_environmentDataContainer != null) && (this.m_environmentDataContainer.getMostRecentDirectorResponseTimestamp() != null)) {
      return new Error(Error.Code.SYNERGY_ENVIRONMENT_UPDATE_FAILURE, "Environment data already cached.");
    }
    if (isInSynergyEnvironmentUpdateRateLimitingPeriod())
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Double.valueOf(60.0D - (System.currentTimeMillis() - this.m_synergyEnvironmentUpdateRateLimitTriggerTimestamp.longValue()) / 1000.0D);
      Log.Helper.LOGD(this, "Attempt to re-initiate Synergy environment update blocked by rate limiting. %.2f seconds of rate limiting left", arrayOfObject);
      return new Error(Error.Code.SYNERGY_ENVIRONMENT_UPDATE_FAILURE, "Synergy environment update rate limit in effect.");
    }
    startSynergyEnvironmentUpdate();
    return null;
  }
  
  public void cleanup()
  {
    Log.Helper.LOGD(this, "cleanup", new Object[0]);
    if (this.m_synergyStartupObject != null)
    {
      this.m_synergyStartupObject.cancel();
      this.m_synergyStartupObject = null;
    }
    if (this.m_networkStatusChangeReceiver != null)
    {
      Utility.unregisterReceiver(this.m_networkStatusChangeReceiver);
      this.m_networkStatusChangeReceiver = null;
    }
    saveEnvironmentDataToPersistent();
    this.m_environmentDataContainer = null;
  }
  
  public String getComponentId()
  {
    return "com.ea.nimble.synergyEnvironment";
  }
  
  public String getEADeviceId()
  {
    checkAndInitiateSynergyEnvironmentUpdate();
    if (this.m_environmentDataContainer == null) {
      return null;
    }
    return this.m_environmentDataContainer.getEADeviceId();
  }
  
  public String getEAHardwareId()
  {
    checkAndInitiateSynergyEnvironmentUpdate();
    if (this.m_environmentDataContainer == null) {
      return null;
    }
    return this.m_environmentDataContainer.getEAHardwareId();
  }
  
  public String getGosMdmAppKey()
  {
    checkAndInitiateSynergyEnvironmentUpdate();
    if (this.m_environmentDataContainer == null) {
      return null;
    }
    return this.m_environmentDataContainer.getGosMdmAppKey();
  }
  
  public int getLatestAppVersionCheckResult()
  {
    if (this.m_environmentDataContainer == null) {
      return 0;
    }
    return this.m_environmentDataContainer.getLatestAppVersionCheckResult();
  }
  
  public String getLogSourceTitle()
  {
    return "SynergyEnv";
  }
  
  public String getNexusClientId()
  {
    checkAndInitiateSynergyEnvironmentUpdate();
    if (this.m_environmentDataContainer == null) {
      return null;
    }
    return this.m_environmentDataContainer.getNexusClientId();
  }
  
  public String getNexusClientSecret()
  {
    checkAndInitiateSynergyEnvironmentUpdate();
    if (this.m_environmentDataContainer == null) {
      return null;
    }
    return this.m_environmentDataContainer.getNexusClientSecret();
  }
  
  public String getProductId()
  {
    checkAndInitiateSynergyEnvironmentUpdate();
    if (this.m_environmentDataContainer == null) {
      return null;
    }
    return this.m_environmentDataContainer.getProductId();
  }
  
  public String getSellId()
  {
    checkAndInitiateSynergyEnvironmentUpdate();
    if (this.m_environmentDataContainer == null) {
      return null;
    }
    return this.m_environmentDataContainer.getSellId();
  }
  
  public String getServerUrlWithKey(String paramString)
  {
    checkAndInitiateSynergyEnvironmentUpdate();
    if (this.m_environmentDataContainer == null) {
      return null;
    }
    return this.m_environmentDataContainer.getServerUrlWithKey(paramString);
  }
  
  public String getSynergyDirectorServerUrl(NimbleConfiguration paramNimbleConfiguration)
  {
    switch (3.$SwitchMap$com$ea$nimble$NimbleConfiguration[paramNimbleConfiguration.ordinal()])
    {
    default: 
      Log.Helper.LOGF(this, "Request for Synergy Director server URL with unknown NimbleConfiguration, %d.", new Object[] { paramNimbleConfiguration });
      return "https://syn-dir.sn.eamobile.com";
    case 1: 
      return "http://synergy-int.eamobile.com:8081";
    case 2: 
      return "https://synergy-stage.eamobile.com";
    case 3: 
      return "https://syn-dir.sn.eamobile.com";
    }
    return PreferenceManager.getDefaultSharedPreferences(ApplicationEnvironment.getComponent().getApplicationContext()).getString("NimbleCustomizedSynergyServerEndpointUrl", "https://syn-dir.sn.eamobile.com");
  }
  
  public String getSynergyId()
  {
    checkAndInitiateSynergyEnvironmentUpdate();
    if (this.m_environmentDataContainer == null) {
      return null;
    }
    return this.m_environmentDataContainer.getSynergyId();
  }
  
  public boolean getSynergyServerFlags(String paramString)
  {
    checkAndInitiateSynergyEnvironmentUpdate();
    if (this.m_environmentDataContainer == null) {
      return false;
    }
    return this.m_environmentDataContainer.getSynergyServerFlags(paramString);
  }
  
  public int getTrackingPostInterval()
  {
    if (this.m_environmentDataContainer == null) {
      return -1;
    }
    return this.m_environmentDataContainer.getTrackingPostInterval();
  }
  
  public boolean isDataAvailable()
  {
    return this.m_environmentDataContainer != null;
  }
  
  public boolean isUpdateInProgress()
  {
    return this.m_synergyStartupObject != null;
  }
  
  public void restore()
  {
    Log.Helper.LOGD(this, "restore", new Object[0]);
    restoreEnvironmentDataFromPersistent();
    if ((this.m_environmentDataContainer == null) || ((System.currentTimeMillis() - this.m_environmentDataContainer.getMostRecentDirectorResponseTimestamp().longValue()) / 1000.0D > 300.0D))
    {
      startSynergyEnvironmentUpdate();
      return;
    }
    checkAndInitiateSynergyEnvironmentUpdate();
  }
  
  public void resume()
  {
    Log.Helper.LOGD(this, "resume", new Object[0]);
    clearSynergyEnvironmentUpdateRateLimiting();
    if ((this.m_environmentDataContainer == null) || ((System.currentTimeMillis() - this.m_environmentDataContainer.getMostRecentDirectorResponseTimestamp().longValue()) / 1000.0D > 300.0D)) {
      startSynergyEnvironmentUpdate();
    }
  }
  
  public void setup() {}
  
  public void suspend()
  {
    Log.Helper.LOGD(this, "suspend", new Object[0]);
    if (this.m_synergyStartupObject != null)
    {
      this.m_synergyStartupObject.cancel();
      this.m_synergyStartupObject = null;
    }
    if (this.m_networkStatusChangeReceiver != null)
    {
      Utility.unregisterReceiver(this.m_networkStatusChangeReceiver);
      this.m_networkStatusChangeReceiver = null;
    }
    saveEnvironmentDataToPersistent();
  }
  
  public void teardown()
  {
    this.m_environmentDataContainer = null;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ea.nimble.SynergyEnvironmentImpl
 * JD-Core Version:    0.7.0.1
 */