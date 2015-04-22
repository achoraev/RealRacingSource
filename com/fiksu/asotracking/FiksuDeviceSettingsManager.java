package com.fiksu.asotracking;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.Log;

final class FiksuDeviceSettingsManager
{
  private static final FiksuDeviceSettingsManager INSTANCE = new FiksuDeviceSettingsManager();
  private boolean disableGetDeviceId = false;
  private final FiksuDeviceSettings mFiksuDeviceSettings = new FiksuDeviceSettings();
  private SharedPreferences mSharedPreferences = null;
  
  static FiksuDeviceSettingsManager getInstance()
  {
    return INSTANCE;
  }
  
  private void setSharedPreferences(Context paramContext)
  {
    if (this.mSharedPreferences != null) {
      return;
    }
    if (paramContext == null)
    {
      Log.e("FiksuTracking", "Context is null so we cannot load configuration from SharedPreferences");
      return;
    }
    this.mSharedPreferences = paramContext.getSharedPreferences("FiksuDeviceSettingsSharedPreferences", 0);
  }
  
  String getAndroidId(Context paramContext)
  {
    String str = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
    if (str == null)
    {
      Log.e("FiksuTracking", "Could not retrieve android_id.  The android_id is not available on emulators running Android 2.1 or below.  Run the code on emulator 2.2 or better or an a device.");
      str = "";
    }
    return str;
  }
  
  String getClientId()
  {
    return this.mFiksuDeviceSettings.getClientId();
  }
  
  String getDeviceId(Context paramContext)
  {
    try
    {
      TelephonyManager localTelephonyManager = (TelephonyManager)paramContext.getSystemService("phone");
      String str;
      if (localTelephonyManager == null)
      {
        Log.e("FiksuTracking", "Could not access telephonyManager.");
        str = "";
      }
      do
      {
        return str;
        str = localTelephonyManager.getDeviceId();
      } while ((str != null) && (str.length() != 0));
      Log.e("FiksuTracking", "Could not retrieve deviceId. ");
      return "";
    }
    catch (SecurityException localSecurityException)
    {
      Log.e("FiksuTracking", "Could not retrieve deviceId: READ_PHONE_STATE permission not granted. " + localSecurityException.getMessage());
    }
    return "";
  }
  
  boolean getDisableGetDeviceId()
  {
    return this.disableGetDeviceId;
  }
  
  void initialize(Context paramContext)
  {
    setSharedPreferences(paramContext);
    if (this.mSharedPreferences != null) {
      this.mFiksuDeviceSettings.readFromSharedPreferences(this.mSharedPreferences);
    }
  }
  
  boolean isAppTrackingEnabled()
  {
    return this.mFiksuDeviceSettings.isAppTrackingEnabled();
  }
  
  void setAppTrackingEnabled(Context paramContext, boolean paramBoolean)
  {
    this.mFiksuDeviceSettings.setAppTrackingEnabled(paramBoolean);
    setSharedPreferences(paramContext);
    if (this.mSharedPreferences != null) {
      this.mFiksuDeviceSettings.writeToSharedPreferences(this.mSharedPreferences);
    }
  }
  
  void setClientId(Context paramContext, String paramString)
  {
    this.mFiksuDeviceSettings.setClientId(paramString);
    setSharedPreferences(paramContext);
    if (this.mSharedPreferences != null) {
      this.mFiksuDeviceSettings.writeToSharedPreferences(this.mSharedPreferences);
    }
  }
  
  void setDisableGetDeviceId()
  {
    this.disableGetDeviceId = true;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.fiksu.asotracking.FiksuDeviceSettingsManager
 * JD-Core Version:    0.7.0.1
 */