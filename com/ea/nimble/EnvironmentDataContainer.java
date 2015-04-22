package com.ea.nimble;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class EnvironmentDataContainer
  implements ISynergyEnvironment, Externalizable, LogSource
{
  private static final int SYNERGY_DIRECTOR_RESPONSE_APP_VERSION_OK = 0;
  private static final int SYNERGY_DIRECTOR_RESPONSE_APP_VERSION_UPGRADE_RECOMMENDED = 1;
  private static final int SYNERGY_DIRECTOR_RESPONSE_APP_VERSION_UPGRADE_REQUIRED = 2;
  private String m_applicationLanguageCode;
  private String m_eaDeviceId;
  private Map<String, Object> m_getDirectionResponseDictionary = new HashMap();
  private Long m_lastDirectorResponseTimestamp;
  private Map<String, String> m_serverUrls;
  private String m_synergyAnonymousId;
  private Map<String, Boolean> m_synergyFlags = new HashMap();
  
  public EnvironmentDataContainer()
  {
    this.m_synergyFlags.put("sendMacAddress", Boolean.valueOf(true));
    this.m_synergyFlags.put("sendUDID", Boolean.valueOf(false));
    this.m_applicationLanguageCode = "en";
  }
  
  public Error checkAndInitiateSynergyEnvironmentUpdate()
  {
    return null;
  }
  
  public String getEADeviceId()
  {
    return this.m_eaDeviceId;
  }
  
  public String getEAHardwareId()
  {
    return (String)this.m_getDirectionResponseDictionary.get("hwId");
  }
  
  Map<String, Object> getGetDirectionResponseDictionary()
  {
    return this.m_getDirectionResponseDictionary;
  }
  
  public String getGosMdmAppKey()
  {
    return (String)this.m_getDirectionResponseDictionary.get("mdmAppKey");
  }
  
  public Set<String> getKeysOfDifferences(ISynergyEnvironment paramISynergyEnvironment)
  {
    HashSet localHashSet = new HashSet();
    if (paramISynergyEnvironment != null) {}
    try
    {
      if (!Utility.stringsAreEquivalent(getEADeviceId(), paramISynergyEnvironment.getEADeviceId())) {
        localHashSet.add("ENVIRONMENT_KEY_EADEVICEID");
      }
      if ((paramISynergyEnvironment == null) || (!Utility.stringsAreEquivalent(getEAHardwareId(), paramISynergyEnvironment.getEAHardwareId()))) {
        localHashSet.add("ENVIRONMENT_KEY_EAHARDWAREID");
      }
      if ((paramISynergyEnvironment == null) || (!Utility.stringsAreEquivalent(getSynergyId(), paramISynergyEnvironment.getSynergyId()))) {
        localHashSet.add("ENVIRONMENT_KEY_SYNERGYID");
      }
      if ((paramISynergyEnvironment == null) || (!Utility.stringsAreEquivalent(getSellId(), paramISynergyEnvironment.getSellId()))) {
        localHashSet.add("ENVIRONMENT_KEY_SELLID");
      }
      if ((paramISynergyEnvironment == null) || (!Utility.stringsAreEquivalent(getProductId(), paramISynergyEnvironment.getProductId()))) {
        localHashSet.add("ENVIRONMENT_KEY_PRODUCTID");
      }
      if ((paramISynergyEnvironment == null) || (!Utility.stringsAreEquivalent(getServerUrlWithKey("synergy.drm"), paramISynergyEnvironment.getServerUrlWithKey("synergy.drm")))) {
        localHashSet.add("ENVIRONMENT_KEY_SERVER_URL_SYNERGY_DRM");
      }
      if ((paramISynergyEnvironment == null) || (!Utility.stringsAreEquivalent(getServerUrlWithKey("synergy.director"), paramISynergyEnvironment.getServerUrlWithKey("synergy.director")))) {
        localHashSet.add("ENVIRONMENT_KEY_SERVER_URL_SYNERGY_DIRECTOR");
      }
      if ((paramISynergyEnvironment == null) || (!Utility.stringsAreEquivalent(getServerUrlWithKey("synergy.m2u"), paramISynergyEnvironment.getServerUrlWithKey("synergy.m2u")))) {
        localHashSet.add("ENVIRONMENT_KEY_SERVER_URL_SYNERGY_MESSAGE_TO_USER");
      }
      if ((paramISynergyEnvironment == null) || (!Utility.stringsAreEquivalent(getServerUrlWithKey("synergy.product"), paramISynergyEnvironment.getServerUrlWithKey("synergy.product")))) {
        localHashSet.add("ENVIRONMENT_KEY_SERVER_URL_SYNERGY_PRODUCT");
      }
      if ((paramISynergyEnvironment == null) || (!Utility.stringsAreEquivalent(getServerUrlWithKey("synergy.tracking"), paramISynergyEnvironment.getServerUrlWithKey("synergy.tracking")))) {
        localHashSet.add("ENVIRONMENT_KEY_SERVER_URL_SYNERGY_TRACKING");
      }
      if ((paramISynergyEnvironment == null) || (!Utility.stringsAreEquivalent(getServerUrlWithKey("synergy.user"), paramISynergyEnvironment.getServerUrlWithKey("synergy.user")))) {
        localHashSet.add("ENVIRONMENT_KEY_SERVER_URL_SYNERGY_USER");
      }
      if ((paramISynergyEnvironment == null) || (!Utility.stringsAreEquivalent(getServerUrlWithKey("geoip.url"), paramISynergyEnvironment.getServerUrlWithKey("geoip.url")))) {
        localHashSet.add("ENVIRONMENT_KEY_SERVER_URL_SYNERGY_CENTRAL_IP_GEOLOCATION");
      }
      if ((paramISynergyEnvironment == null) || (!Utility.stringsAreEquivalent(getServerUrlWithKey("synergy.s2s"), paramISynergyEnvironment.getServerUrlWithKey("synergy.s2s")))) {
        localHashSet.add("ENVIRONMENT_KEY_SERVER_URL_SYNERGY_S2S");
      }
      if ((paramISynergyEnvironment == null) || (!Utility.stringsAreEquivalent(getServerUrlWithKey("friends.url"), paramISynergyEnvironment.getServerUrlWithKey("friends.url")))) {
        localHashSet.add("ENVIRONMENT_KEY_SERVER_URL_ORIGIN_FRIENDS");
      }
      if ((paramISynergyEnvironment == null) || (!Utility.stringsAreEquivalent(getServerUrlWithKey("eadp.friends.host"), paramISynergyEnvironment.getServerUrlWithKey("eadp.friends.host")))) {
        localHashSet.add("ENVIRONMENT_KEY_SERVER_URL_EADP_FRIENDS_HOST");
      }
      if ((paramISynergyEnvironment == null) || (!Utility.stringsAreEquivalent(getServerUrlWithKey("avatars.url"), paramISynergyEnvironment.getServerUrlWithKey("avatars.url")))) {
        localHashSet.add("ENVIRONMENT_KEY_SERVER_URL_ORIGIN_AVATAR");
      }
      if ((paramISynergyEnvironment == null) || (!Utility.stringsAreEquivalent(getServerUrlWithKey("origincasualapp.url"), paramISynergyEnvironment.getServerUrlWithKey("origincasualapp.url")))) {
        localHashSet.add("ENVIRONMENT_KEY_SERVER_URL_ORIGIN_CASUAL_APP");
      }
      if ((paramISynergyEnvironment == null) || (!Utility.stringsAreEquivalent(getServerUrlWithKey("origincasualserver.url"), paramISynergyEnvironment.getServerUrlWithKey("origincasualserver.url")))) {
        localHashSet.add("ENVIRONMENT_KEY_SERVER_URL_ORIGIN_CASUAL_SERVER");
      }
      if ((paramISynergyEnvironment == null) || (!Utility.stringsAreEquivalent(getServerUrlWithKey("akamai.url"), paramISynergyEnvironment.getServerUrlWithKey("akamai.url")))) {
        localHashSet.add("ENVIRONMENT_KEY_SERVER_URL_AKAMAI");
      }
      if ((paramISynergyEnvironment == null) || (!Utility.stringsAreEquivalent(getServerUrlWithKey("dmg.url"), paramISynergyEnvironment.getServerUrlWithKey("dmg.url")))) {
        localHashSet.add("ENVIRONMENT_KEY_SERVER_URL_DYNAMIC_MORE_GAMES");
      }
      if ((paramISynergyEnvironment == null) || (!Utility.stringsAreEquivalent(getServerUrlWithKey("mayhem.url"), paramISynergyEnvironment.getServerUrlWithKey("mayhem.url")))) {
        localHashSet.add("ENVIRONMENT_KEY_SERVER_URL_MAYHEM");
      }
      if ((paramISynergyEnvironment == null) || (getSynergyServerFlags("sendMacAddress") != paramISynergyEnvironment.getSynergyServerFlags("sendMacAddress"))) {
        localHashSet.add("ENVIRONMENT_KEY_SERVER_SERVER_FLAG_CAN_SEND_MAC_ADDRESS");
      }
      if ((paramISynergyEnvironment == null) || (getSynergyServerFlags("sendUDID") != paramISynergyEnvironment.getSynergyServerFlags("sendUDID"))) {
        localHashSet.add("ENVIRONMENT_KEY_SERVER_SERVER_FLAG_CAN_SEND_UDID");
      }
      if ((paramISynergyEnvironment == null) || (!Utility.stringsAreEquivalent(getServerUrlWithKey("nexus.connect"), paramISynergyEnvironment.getServerUrlWithKey("nexus.connect")))) {
        localHashSet.add("ENVIRONMENT_KEY_SERVER_URL_KEY_IDENTITY_CONNECT");
      }
      if ((paramISynergyEnvironment == null) || (!Utility.stringsAreEquivalent(getServerUrlWithKey("nexus.proxy"), paramISynergyEnvironment.getServerUrlWithKey("nexus.proxy")))) {
        localHashSet.add("ENVIRONMENT_KEY_SERVER_URL_KEY_IDENTITY_PROXY");
      }
      if ((paramISynergyEnvironment == null) || (!Utility.stringsAreEquivalent(getServerUrlWithKey("nexus.portal"), paramISynergyEnvironment.getServerUrlWithKey("nexus.portal")))) {
        localHashSet.add("ENVIRONMENT_KEY_SERVER_URL_KEY_IDENTITY_PORTAL");
      }
      if ((paramISynergyEnvironment == null) || (getLatestAppVersionCheckResult() != paramISynergyEnvironment.getLatestAppVersionCheckResult())) {
        localHashSet.add("ENVIRONMENT_KEY_APP_VERSION_CHECK_RESULT");
      }
    }
    catch (NullPointerException localNullPointerException)
    {
      for (;;)
      {
        Log.Helper.LOGE(this, "Null pointer exception comparing EnvironmentDataContainer.", new Object[0]);
        localNullPointerException.printStackTrace();
      }
    }
    if (localHashSet.size() > 0) {
      return localHashSet;
    }
    return null;
  }
  
  public int getLatestAppVersionCheckResult()
  {
    Object localObject = this.m_getDirectionResponseDictionary.get("appUpgrade");
    int i;
    if ((localObject instanceof Integer)) {
      i = ((Integer)localObject).intValue();
    }
    for (;;)
    {
      switch (i)
      {
      default: 
        return 0;
        boolean bool = localObject instanceof String;
        i = 0;
        if (bool) {
          i = Integer.parseInt((String)localObject);
        }
        break;
      }
    }
    return 0;
    return 1;
    return 2;
  }
  
  public String getLogSourceTitle()
  {
    return "SynergyEnv";
  }
  
  Long getMostRecentDirectorResponseTimestamp()
  {
    return this.m_lastDirectorResponseTimestamp;
  }
  
  public String getNexusClientId()
  {
    return (String)this.m_getDirectionResponseDictionary.get("clientId");
  }
  
  public String getNexusClientSecret()
  {
    return (String)this.m_getDirectionResponseDictionary.get("clientSecret");
  }
  
  public String getProductId()
  {
    return (String)this.m_getDirectionResponseDictionary.get("productId");
  }
  
  public String getSellId()
  {
    return (String)this.m_getDirectionResponseDictionary.get("sellId");
  }
  
  public String getServerUrlWithKey(String paramString)
  {
    if (this.m_serverUrls == null) {
      return null;
    }
    return (String)this.m_serverUrls.get(paramString);
  }
  
  Map<String, String> getServerUrls()
  {
    return this.m_serverUrls;
  }
  
  String getSynergyAnonymousId()
  {
    return this.m_synergyAnonymousId;
  }
  
  public String getSynergyDirectorServerUrl(NimbleConfiguration paramNimbleConfiguration)
  {
    return SynergyEnvironment.getComponent().getSynergyDirectorServerUrl(paramNimbleConfiguration);
  }
  
  Map<String, Boolean> getSynergyFlags()
  {
    return this.m_synergyFlags;
  }
  
  public String getSynergyId()
  {
    return this.m_synergyAnonymousId;
  }
  
  public boolean getSynergyServerFlags(String paramString)
  {
    if (this.m_synergyFlags == null) {
      return false;
    }
    return ((Boolean)this.m_synergyFlags.get(paramString)).booleanValue();
  }
  
  public int getTrackingPostInterval()
  {
    Integer localInteger = (Integer)this.m_getDirectionResponseDictionary.get("telemetryFreq");
    if (localInteger == null) {
      return -1;
    }
    return localInteger.intValue();
  }
  
  public boolean isDataAvailable()
  {
    return true;
  }
  
  public boolean isUpdateInProgress()
  {
    return false;
  }
  
  public void readExternal(ObjectInput paramObjectInput)
    throws IOException, ClassNotFoundException
  {
    this.m_getDirectionResponseDictionary = ((Map)paramObjectInput.readObject());
    this.m_serverUrls = ((Map)paramObjectInput.readObject());
    this.m_synergyFlags = ((Map)paramObjectInput.readObject());
    this.m_eaDeviceId = ((String)paramObjectInput.readObject());
    this.m_synergyAnonymousId = ((String)paramObjectInput.readObject());
    this.m_lastDirectorResponseTimestamp = Long.valueOf(paramObjectInput.readLong());
    this.m_applicationLanguageCode = ((String)paramObjectInput.readObject());
  }
  
  void setEADeviceId(String paramString)
  {
    this.m_eaDeviceId = paramString;
  }
  
  void setGetDirectionResponseDictionary(Map<String, Object> paramMap)
  {
    paramMap.put("sellId", ((Integer)paramMap.get("sellId")).toString());
    paramMap.put("productId", ((Integer)paramMap.get("productId")).toString());
    paramMap.put("hwId", ((Integer)paramMap.get("hwId")).toString());
    this.m_getDirectionResponseDictionary = paramMap;
  }
  
  void setMostRecentDirectorResponseTimestamp(Long paramLong)
  {
    this.m_lastDirectorResponseTimestamp = paramLong;
  }
  
  void setServerUrls(Map<String, String> paramMap)
  {
    this.m_serverUrls = paramMap;
  }
  
  void setSynergyAnonymousId(Integer paramInteger)
  {
    this.m_synergyAnonymousId = paramInteger.toString();
  }
  
  void setSynergyFlags(Map<String, Boolean> paramMap)
  {
    this.m_synergyFlags = paramMap;
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput)
    throws IOException
  {
    paramObjectOutput.writeObject(this.m_getDirectionResponseDictionary);
    paramObjectOutput.writeObject(this.m_serverUrls);
    paramObjectOutput.writeObject(this.m_synergyFlags);
    paramObjectOutput.writeObject(this.m_eaDeviceId);
    paramObjectOutput.writeObject(this.m_synergyAnonymousId);
    paramObjectOutput.writeLong(this.m_lastDirectorResponseTimestamp.longValue());
    paramObjectOutput.writeObject(this.m_applicationLanguageCode);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ea.nimble.EnvironmentDataContainer
 * JD-Core Version:    0.7.0.1
 */