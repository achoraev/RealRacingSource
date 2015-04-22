package com.ea.nimble;

public abstract interface ISynergyEnvironment
{
  public static final int NETWORK_CONNECTION_NONE = 1;
  public static final int NETWORK_CONNECTION_UNKNOWN = 0;
  public static final int NETWORK_CONNECTION_WIFI = 2;
  public static final int NETWORK_CONNECTION_WIRELESS = 3;
  public static final int SYNERGY_APP_VERSION_OK = 0;
  public static final int SYNERGY_APP_VERSION_UPDATE_RECOMMENDED = 1;
  public static final int SYNERGY_APP_VERSION_UPDATE_REQUIRED = 2;
  
  public abstract Error checkAndInitiateSynergyEnvironmentUpdate();
  
  public abstract String getEADeviceId();
  
  public abstract String getEAHardwareId();
  
  public abstract String getGosMdmAppKey();
  
  public abstract int getLatestAppVersionCheckResult();
  
  public abstract String getNexusClientId();
  
  public abstract String getNexusClientSecret();
  
  public abstract String getProductId();
  
  public abstract String getSellId();
  
  public abstract String getServerUrlWithKey(String paramString);
  
  public abstract String getSynergyDirectorServerUrl(NimbleConfiguration paramNimbleConfiguration);
  
  public abstract String getSynergyId();
  
  public abstract boolean getSynergyServerFlags(String paramString);
  
  public abstract int getTrackingPostInterval();
  
  public abstract boolean isDataAvailable();
  
  public abstract boolean isUpdateInProgress();
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ea.nimble.ISynergyEnvironment
 * JD-Core Version:    0.7.0.1
 */