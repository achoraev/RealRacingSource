package com.fiksu.asotracking;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

final class FiksuDeviceSettings
{
  private static final String APP_TRACKING_ENABLED_ATTRIBUTION_KEY = "app_tracking_enabled";
  private static final String CLIENT_ID_ATTRIBUTION_KEY = "clientId";
  private boolean mAppTrackingEnabled = true;
  private String mClientId = "";
  
  private static String makeNonNull(String paramString)
  {
    if (paramString == null) {
      paramString = "";
    }
    return paramString;
  }
  
  String getClientId()
  {
    return this.mClientId;
  }
  
  boolean isAppTrackingEnabled()
  {
    return this.mAppTrackingEnabled;
  }
  
  void readFromSharedPreferences(SharedPreferences paramSharedPreferences)
  {
    this.mClientId = paramSharedPreferences.getString("clientId", this.mClientId);
    this.mAppTrackingEnabled = paramSharedPreferences.getBoolean("app_tracking_enabled", this.mAppTrackingEnabled);
  }
  
  void setAppTrackingEnabled(boolean paramBoolean)
  {
    this.mAppTrackingEnabled = paramBoolean;
  }
  
  void setClientId(String paramString)
  {
    this.mClientId = makeNonNull(paramString);
  }
  
  void writeToSharedPreferences(SharedPreferences paramSharedPreferences)
  {
    SharedPreferences.Editor localEditor = paramSharedPreferences.edit();
    localEditor.putString("clientId", this.mClientId);
    localEditor.putBoolean("app_tracking_enabled", this.mAppTrackingEnabled);
    localEditor.commit();
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.fiksu.asotracking.FiksuDeviceSettings
 * JD-Core Version:    0.7.0.1
 */