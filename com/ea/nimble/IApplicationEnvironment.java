package com.ea.nimble;

import android.content.Context;

public abstract interface IApplicationEnvironment
{
  public abstract int getAgeCompliance();
  
  public abstract String getApplicationBundleId();
  
  public abstract Context getApplicationContext();
  
  public abstract String getApplicationLanguageCode();
  
  public abstract String getApplicationName();
  
  public abstract String getApplicationVersion();
  
  public abstract String getCachePath();
  
  public abstract String getCarrier();
  
  public abstract String getDeviceString();
  
  public abstract String getDocumentPath();
  
  public abstract String getGameSpecifiedPlayerId();
  
  public abstract String getGoogleAdvertisingId();
  
  public abstract String getGoogleEmail();
  
  public abstract String getMACAddress();
  
  public abstract String getOsVersion();
  
  public abstract String getTempPath();
  
  public abstract boolean isAppCracked();
  
  public abstract boolean isDeviceRooted();
  
  public abstract boolean isLimitAdTrackingEnabled();
  
  public abstract void refreshAgeCompliance();
  
  public abstract void setApplicationBundleId(String paramString);
  
  public abstract void setApplicationLanguageCode(String paramString);
  
  public abstract void setGameSpecifiedPlayerId(String paramString);
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ea.nimble.IApplicationEnvironment
 * JD-Core Version:    0.7.0.1
 */