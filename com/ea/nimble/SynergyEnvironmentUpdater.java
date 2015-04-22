package com.ea.nimble;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

class SynergyEnvironmentUpdater
  implements LogSource
{
  private static final int GET_ANONUID_MAX_RETRY_ATTEMPTS = 3;
  private static final int GET_DIRECTION_MAX_RETRY_ATTEMPTS = 3;
  private static final int GET_EADEVICEID_MAX_RETRY_ATTEMPTS = 3;
  private static final int SYNERGY_DIRECTOR_RESPONSE_ERROR_CODE_SERVERS_FULL = -70002;
  private static final int SYNERGY_USER_VALIDATE_EADEVICEID_RESPONSE_ERROR_CODE_CLEAR_CLIENT_CACHED_EADEVICEID = -20094;
  private static final int SYNERGY_USER_VALIDATE_EADEVICEID_RESPONSE_ERROR_CODE_VALIDATION_FAILED = -20093;
  private static final int VALIDATE_EADEVICEID_MAX_RETRY_ATTEMPTS = 3;
  private CompletionCallback m_completionCallback;
  private BaseCore m_core;
  private EnvironmentDataContainer m_environmentForSynergyStartUp;
  private long m_getAnonUIDRetryCount;
  private long m_getDirectionRetryCount;
  private long m_getEADeviceIDRetryCount;
  private EnvironmentDataContainer m_previousValidEnvironmentData;
  private SynergyNetworkConnectionHandle m_synergyNetworkConnectionHandle;
  private long m_validateEADeviceIDRetryCount;
  
  SynergyEnvironmentUpdater(BaseCore paramBaseCore)
  {
    this.m_core = paramBaseCore;
    this.m_environmentForSynergyStartUp = new EnvironmentDataContainer();
    this.m_completionCallback = null;
    this.m_previousValidEnvironmentData = null;
    this.m_synergyNetworkConnectionHandle = null;
    this.m_validateEADeviceIDRetryCount = 0L;
    this.m_getEADeviceIDRetryCount = 0L;
  }
  
  private void callSynergyGetAnonUid()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("apiVer", "1.0.0");
    localHashMap.put("updatePriority", "false");
    localHashMap.put("hwId", this.m_environmentForSynergyStartUp.getEAHardwareId());
    if (Utility.validString(this.m_environmentForSynergyStartUp.getEADeviceId()))
    {
      localHashMap.put("eadeviceid", this.m_environmentForSynergyStartUp.getEADeviceId());
      this.m_synergyNetworkConnectionHandle = SynergyNetwork.getComponent().sendGetRequest(this.m_environmentForSynergyStartUp.getServerUrlWithKey("synergy.user"), "/user/api/android/getAnonUid", localHashMap, new SynergyNetworkConnectionCallback()
      {
        public void callback(SynergyNetworkConnectionHandle paramAnonymousSynergyNetworkConnectionHandle)
        {
          SynergyEnvironmentUpdater.access$002(SynergyEnvironmentUpdater.this, null);
          Exception localException = paramAnonymousSynergyNetworkConnectionHandle.getResponse().getError();
          if (localException == null)
          {
            Log.Helper.LOGD(this, "GETANON Success", new Object[0]);
            SynergyEnvironmentUpdater.this.m_environmentForSynergyStartUp.setSynergyAnonymousId((Integer)paramAnonymousSynergyNetworkConnectionHandle.getResponse().getJsonData().get("uid"));
            SynergyEnvironmentUpdater.this.onStartUpSequenceFinished(null);
            return;
          }
          if ((!SynergyEnvironmentUpdater.this.isTimeoutError(localException)) && (SynergyEnvironmentUpdater.this.m_getAnonUIDRetryCount < 3L))
          {
            SynergyEnvironmentUpdater.access$1308(SynergyEnvironmentUpdater.this);
            Object[] arrayOfObject2 = new Object[1];
            arrayOfObject2[0] = Long.valueOf(SynergyEnvironmentUpdater.this.m_getAnonUIDRetryCount);
            Log.Helper.LOGD(this, "GetAnonUid, call failed. Making retry attempt number %d.", arrayOfObject2);
            SynergyEnvironmentUpdater.this.callSynergyGetAnonUid();
            return;
          }
          SynergyEnvironmentUpdater.access$1302(SynergyEnvironmentUpdater.this, 0L);
          Object[] arrayOfObject1 = new Object[1];
          arrayOfObject1[0] = paramAnonymousSynergyNetworkConnectionHandle.getResponse().getError().toString();
          Log.Helper.LOGD(this, "GETANON Error, (%s)", arrayOfObject1);
          SynergyEnvironmentUpdater.this.onStartUpSequenceFinished(new Error(Error.Code.SYNERGY_GET_ANONYMOUS_ID_FAILURE, "Synergy \"get anonymous id\" call failed.", localException));
        }
      });
      return;
    }
    Log.Helper.LOGE(this, "getAnonUid got an invalid EA Device ID.", new Object[0]);
    onStartUpSequenceFinished(new Error(Error.Code.INVALID_ARGUMENT, "EA Device ID is invalid"));
  }
  
  private void callSynergyGetDirection()
  {
    String str1 = ApplicationEnvironment.getComponent().getApplicationBundleId();
    String str2 = ApplicationEnvironment.getComponent().getDeviceString();
    if (!Utility.validString(str1))
    {
      Log.Helper.LOGE(this, "GETDIRECTION bundleId is invalid", new Object[0]);
      onStartUpSequenceFinished(new Error(Error.Code.INVALID_ARGUMENT, "bundleId is invalid"));
      return;
    }
    if (!Utility.validString(str2))
    {
      Log.Helper.LOGE(this, "GETDIRECTION deviceString is invalid", new Object[0]);
      onStartUpSequenceFinished(new Error(Error.Code.INVALID_ARGUMENT, "deviceString is invalid"));
      return;
    }
    HashMap localHashMap = new HashMap();
    localHashMap.put("packageId", str1);
    localHashMap.put("deviceString", str2);
    localHashMap.put("serverEnvironment", getSynergyServerEnvironmentName());
    localHashMap.put("sdkVersion", "1.13.1.1009");
    localHashMap.put("apiVer", "1.0.0");
    this.m_synergyNetworkConnectionHandle = SynergyNetwork.getComponent().sendGetRequest(this.m_environmentForSynergyStartUp.getSynergyDirectorServerUrl(Base.getConfiguration()), "/director/api/android/getDirectionByPackage", localHashMap, new SynergyNetworkConnectionCallback()
    {
      public void callback(SynergyNetworkConnectionHandle paramAnonymousSynergyNetworkConnectionHandle)
      {
        Log.Helper.LOGD(this, "GETDIRECTION FINISHED", new Object[0]);
        SynergyEnvironmentUpdater.access$002(SynergyEnvironmentUpdater.this, null);
        Exception localException = paramAnonymousSynergyNetworkConnectionHandle.getResponse().getError();
        if (localException == null)
        {
          SynergyEnvironmentUpdater.this.m_environmentForSynergyStartUp.setMostRecentDirectorResponseTimestamp(Long.valueOf(System.currentTimeMillis()));
          SynergyEnvironmentUpdater.this.m_environmentForSynergyStartUp.setGetDirectionResponseDictionary(paramAnonymousSynergyNetworkConnectionHandle.getResponse().getJsonData());
          List localList = (List)SynergyEnvironmentUpdater.this.m_environmentForSynergyStartUp.getGetDirectionResponseDictionary().get("serverData");
          SynergyEnvironmentUpdater.this.m_environmentForSynergyStartUp.setServerUrls(new HashMap());
          Iterator localIterator = localList.iterator();
          while (localIterator.hasNext())
          {
            Map localMap = (Map)localIterator.next();
            SynergyEnvironmentUpdater.this.m_environmentForSynergyStartUp.getServerUrls().put(localMap.get("key"), localMap.get("value"));
          }
          SynergyEnvironmentUpdater.this.callSynergyGetSwitches();
        }
        do
        {
          return;
          if (!(localException instanceof SynergyServerError)) {
            break;
          }
        } while (!((SynergyServerError)localException).isError(-70002));
        SynergyEnvironmentUpdater.this.onStartUpSequenceFinished(new Error(Error.Code.SYNERGY_SERVER_FULL, "Synergy ServerUnavailable signal received.", localException));
        return;
        boolean bool = SynergyEnvironmentUpdater.this.isTimeoutError(localException);
        if ((!bool) && (SynergyEnvironmentUpdater.this.m_getDirectionRetryCount < 3L))
        {
          SynergyEnvironmentUpdater.access$508(SynergyEnvironmentUpdater.this);
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = Long.valueOf(SynergyEnvironmentUpdater.this.m_getDirectionRetryCount);
          Log.Helper.LOGD(this, "GetDirection, call failed. Making retry attempt number %d.", arrayOfObject);
          SynergyEnvironmentUpdater.this.callSynergyGetDirection();
          return;
        }
        SynergyEnvironmentUpdater.access$502(SynergyEnvironmentUpdater.this, 0L);
        if (bool)
        {
          SynergyEnvironmentUpdater.this.onStartUpSequenceFinished(new Error(Error.Code.SYNERGY_GET_DIRECTION_TIMEOUT, "Synergy /getDirectionByPackage request timed out.", localException));
          return;
        }
        SynergyEnvironmentUpdater.this.onStartUpSequenceFinished(localException);
      }
    });
  }
  
  private void callSynergyGetEADeviceId()
  {
    EnvironmentDataContainer localEnvironmentDataContainer = this.m_environmentForSynergyStartUp;
    HashMap localHashMap = new HashMap();
    localHashMap.put("apiVer", "1.0.0");
    localHashMap.put("hwId", localEnvironmentDataContainer.getEAHardwareId());
    if (localEnvironmentDataContainer.getSynergyServerFlags("sendMacAddress"))
    {
      String str = Utility.SHA256HashString(ApplicationEnvironment.getComponent().getMACAddress());
      if (str != null) {
        localHashMap.put("macHash", str);
      }
    }
    this.m_synergyNetworkConnectionHandle = SynergyNetwork.getComponent().sendGetRequest(this.m_environmentForSynergyStartUp.getServerUrlWithKey("synergy.user"), "/user/api/android/getDeviceID", localHashMap, new SynergyNetworkConnectionCallback()
    {
      public void callback(SynergyNetworkConnectionHandle paramAnonymousSynergyNetworkConnectionHandle)
      {
        SynergyEnvironmentUpdater.access$002(SynergyEnvironmentUpdater.this, null);
        Exception localException = paramAnonymousSynergyNetworkConnectionHandle.getResponse().getError();
        if (localException == null)
        {
          Log.Helper.LOGD(this, "GetEADeviceID Success", new Object[0]);
          SynergyEnvironmentUpdater.this.m_environmentForSynergyStartUp.setEADeviceId((String)paramAnonymousSynergyNetworkConnectionHandle.getResponse().getJsonData().get("deviceId"));
          SynergyEnvironmentUpdater.this.callSynergyGetAnonUid();
          return;
        }
        if ((!SynergyEnvironmentUpdater.this.isTimeoutError(localException)) && (SynergyEnvironmentUpdater.this.m_getEADeviceIDRetryCount < 3L))
        {
          SynergyEnvironmentUpdater.access$1108(SynergyEnvironmentUpdater.this);
          Object[] arrayOfObject2 = new Object[1];
          arrayOfObject2[0] = Long.valueOf(SynergyEnvironmentUpdater.this.m_getEADeviceIDRetryCount);
          Log.Helper.LOGD(this, "GetEADeviceID, call failed. Making retry attempt number %d.", arrayOfObject2);
          SynergyEnvironmentUpdater.this.callSynergyGetEADeviceId();
          return;
        }
        SynergyEnvironmentUpdater.access$1102(SynergyEnvironmentUpdater.this, 0L);
        Object[] arrayOfObject1 = new Object[1];
        arrayOfObject1[0] = paramAnonymousSynergyNetworkConnectionHandle.getResponse().getError();
        Log.Helper.LOGD(this, "GetEADeviceID Error (%s)", arrayOfObject1);
        SynergyEnvironmentUpdater.this.onStartUpSequenceFinished(new Error(Error.Code.SYNERGY_GET_EA_DEVICE_ID_FAILURE, "GetEADevideId call failed", paramAnonymousSynergyNetworkConnectionHandle.getResponse().getError()));
      }
    });
  }
  
  private void callSynergyGetSwitches()
  {
    HashMap localHashMap = new HashMap();
    String str = this.m_environmentForSynergyStartUp.getSellId();
    if (!Utility.validString(str))
    {
      Log.Helper.LOGE(this, "GETSWITCHES sellId is invalid", new Object[0]);
      onStartUpSequenceFinished(new Error(Error.Code.INVALID_ARGUMENT, "sellId is invalid"));
      return;
    }
    localHashMap.put("sellId", str);
    this.m_synergyNetworkConnectionHandle = SynergyNetwork.getComponent().sendGetRequest(this.m_environmentForSynergyStartUp.getServerUrlWithKey("synergy.director"), "/director/api/core/getSwitchesBasedOnSellId", localHashMap, new SynergyNetworkConnectionCallback()
    {
      public void callback(SynergyNetworkConnectionHandle paramAnonymousSynergyNetworkConnectionHandle)
      {
        SynergyEnvironmentUpdater.access$002(SynergyEnvironmentUpdater.this, null);
        if (paramAnonymousSynergyNetworkConnectionHandle.getResponse().getError() == null)
        {
          List localList = (List)paramAnonymousSynergyNetworkConnectionHandle.getResponse().getJsonData().get("switchPair");
          if (SynergyEnvironmentUpdater.this.m_environmentForSynergyStartUp.getSynergyFlags() == null)
          {
            Log.Helper.LOGE(this, "m_synergyFlags null in getSwitches response handling. Creating dictionary. Check initialization.", new Object[0]);
            SynergyEnvironmentUpdater.this.m_environmentForSynergyStartUp.setSynergyFlags(new HashMap(localList.size()));
          }
          Iterator localIterator = localList.iterator();
          while (localIterator.hasNext())
          {
            Map localMap = (Map)localIterator.next();
            String str = (String)localMap.get("switchName");
            Boolean localBoolean = (Boolean)localMap.get("switchOn");
            SynergyEnvironmentUpdater.this.m_environmentForSynergyStartUp.getSynergyFlags().put(str, localBoolean);
          }
        }
        if ((SynergyEnvironmentUpdater.this.m_previousValidEnvironmentData != null) && (Utility.validString(SynergyEnvironmentUpdater.this.m_previousValidEnvironmentData.getEADeviceId())))
        {
          SynergyEnvironmentUpdater.this.callSynergyValidateEADeviceId();
          return;
        }
        SynergyEnvironmentUpdater.this.callSynergyGetEADeviceId();
      }
    });
  }
  
  private void callSynergyValidateEADeviceId()
  {
    EnvironmentDataContainer localEnvironmentDataContainer = this.m_environmentForSynergyStartUp;
    HashMap localHashMap = new HashMap();
    localHashMap.put("apiVer", "1.0.0");
    localHashMap.put("hwId", localEnvironmentDataContainer.getEAHardwareId());
    if ((this.m_previousValidEnvironmentData != null) && (Utility.validString(this.m_previousValidEnvironmentData.getEADeviceId()))) {
      localHashMap.put("eadeviceid", this.m_previousValidEnvironmentData.getEADeviceId());
    }
    if (localEnvironmentDataContainer.getSynergyServerFlags("sendMacAddress"))
    {
      String str = Utility.SHA256HashString(ApplicationEnvironment.getComponent().getMACAddress());
      if (str != null) {
        localHashMap.put("macHash", str);
      }
    }
    this.m_synergyNetworkConnectionHandle = SynergyNetwork.getComponent().sendGetRequest(this.m_environmentForSynergyStartUp.getServerUrlWithKey("synergy.user"), "/user/api/android/validateDeviceID", localHashMap, new SynergyNetworkConnectionCallback()
    {
      public void callback(SynergyNetworkConnectionHandle paramAnonymousSynergyNetworkConnectionHandle)
      {
        SynergyEnvironmentUpdater.access$002(SynergyEnvironmentUpdater.this, null);
        Exception localException = paramAnonymousSynergyNetworkConnectionHandle.getResponse().getError();
        if (localException == null)
        {
          Log.Helper.LOGD(this, "ValidateEADeviceID Success", new Object[0]);
          SynergyEnvironmentUpdater.this.m_environmentForSynergyStartUp.setEADeviceId((String)paramAnonymousSynergyNetworkConnectionHandle.getResponse().getJsonData().get("deviceId"));
          SynergyEnvironmentUpdater.this.callSynergyGetAnonUid();
          return;
        }
        Log.Helper.LOGD(this, "ValidateEADeviceID Error (%s)", new Object[] { localException });
        if ((localException instanceof SynergyServerError))
        {
          SynergyServerError localSynergyServerError = (SynergyServerError)localException;
          if (localSynergyServerError.isError(-20094))
          {
            if (SynergyEnvironmentUpdater.this.m_previousValidEnvironmentData != null) {
              SynergyEnvironmentUpdater.this.m_previousValidEnvironmentData.setEADeviceId(null);
            }
            Log.Helper.LOGD(this, "ValidateEADeviceID, Server signal received to delete cached EA Device ID. Making request to get a new EA Device ID.", new Object[0]);
            SynergyEnvironmentUpdater.this.callSynergyGetEADeviceId();
            return;
          }
          if (localSynergyServerError.isError(-20093))
          {
            Log.Helper.LOGD(this, "ValidateEADeviceID, EADeviceID validation failed. Making request to get a new EA Device ID.", new Object[0]);
            SynergyEnvironmentUpdater.this.callSynergyGetEADeviceId();
            return;
          }
        }
        if ((!SynergyEnvironmentUpdater.this.isTimeoutError(localException)) && (SynergyEnvironmentUpdater.this.m_validateEADeviceIDRetryCount < 3L))
        {
          SynergyEnvironmentUpdater.access$1208(SynergyEnvironmentUpdater.this);
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = Long.valueOf(SynergyEnvironmentUpdater.this.m_validateEADeviceIDRetryCount);
          Log.Helper.LOGD(this, "ValidateEADeviceID, call failed. Making retry attempt number %d.", arrayOfObject);
          SynergyEnvironmentUpdater.this.callSynergyValidateEADeviceId();
          return;
        }
        SynergyEnvironmentUpdater.access$1202(SynergyEnvironmentUpdater.this, 0L);
        SynergyEnvironmentUpdater.this.onStartUpSequenceFinished(new Error(Error.Code.SYNERGY_GET_EA_DEVICE_ID_FAILURE, "ValidateEADeviceId call failed", localException));
      }
    });
  }
  
  private String getSynergyServerEnvironmentName()
  {
    switch (6.$SwitchMap$com$ea$nimble$NimbleConfiguration[this.m_core.getConfiguration().ordinal()])
    {
    default: 
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = this.m_core.getConfiguration().toString();
      Log.Helper.LOGF(this, "Request for Synergy server environment name with unknown NimbleConfiguration %s", arrayOfObject);
      return "live";
    case 1: 
    case 2: 
    case 3: 
      return this.m_core.getConfiguration().toString();
    }
    return PreferenceManager.getDefaultSharedPreferences(ApplicationEnvironment.getComponent().getApplicationContext()).getString("NimbleCustomizedSynergyServerEnvironmentName", "live");
  }
  
  private boolean isTimeoutError(Exception paramException)
  {
    return ((paramException instanceof Error)) && (((Error)paramException).isError(Error.Code.NETWORK_TIMEOUT));
  }
  
  private void onStartUpSequenceFinished(Exception paramException)
  {
    if (this.m_completionCallback != null)
    {
      this.m_completionCallback.callback(paramException);
      return;
    }
    Log.Helper.LOGW(this, "Startup sequence finished, but no completion callback set.", new Object[0]);
  }
  
  public void cancel()
  {
    if (this.m_synergyNetworkConnectionHandle != null)
    {
      Log.Helper.LOGD(this, "Canceling network connection.", new Object[0]);
      this.m_synergyNetworkConnectionHandle.cancel();
      this.m_synergyNetworkConnectionHandle = null;
    }
    onStartUpSequenceFinished(new Error(Error.Code.NETWORK_OPERATION_CANCELLED, "Synergy startup sequence canceled."));
  }
  
  EnvironmentDataContainer getEnvironmentDataContainer()
  {
    return this.m_environmentForSynergyStartUp;
  }
  
  public String getLogSourceTitle()
  {
    return "SynergyEnv";
  }
  
  public void startSynergyStartupSequence(EnvironmentDataContainer paramEnvironmentDataContainer, CompletionCallback paramCompletionCallback)
  {
    this.m_completionCallback = paramCompletionCallback;
    this.m_previousValidEnvironmentData = paramEnvironmentDataContainer;
    if (Network.getComponent().getStatus() != Network.Status.OK)
    {
      onStartUpSequenceFinished(new Error(Error.Code.NETWORK_NO_CONNECTION, "Device is not connected to Wifi or wireless."));
      return;
    }
    callSynergyGetDirection();
  }
  
  static abstract interface CompletionCallback
  {
    public abstract void callback(Exception paramException);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ea.nimble.SynergyEnvironmentUpdater
 * JD-Core Version:    0.7.0.1
 */