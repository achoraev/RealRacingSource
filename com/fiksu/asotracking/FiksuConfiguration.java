package com.fiksu.asotracking;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import org.json.JSONException;
import org.json.JSONObject;

final class FiksuConfiguration
{
  private static final String DEBUG_MODE_ENABLED_KEY = "debug_mode_enabled";
  private static final boolean DEFAULT_DEBUG_MODE_ENABLED_SETTING = false;
  private static final boolean DEFAULT_FACEBOOK_ATTRIBUTION_SETTING = true;
  private static final String FACEBOOK_ATTRIBUTION_KEY = "facebook_attribution";
  private static final String LAST_UPDATE_TIMESTAMP = "last_update_timestamp";
  private boolean mDebugModeEnabled = false;
  private boolean mFacebookAttributionEnabled = true;
  private long mLastUpdateTimestamp = -1L;
  
  private static boolean getBooleanFromJSONObject(JSONObject paramJSONObject, String paramString, boolean paramBoolean)
  {
    try
    {
      boolean bool = paramJSONObject.getBoolean(paramString);
      return bool;
    }
    catch (JSONException localJSONException) {}
    return paramBoolean;
  }
  
  boolean hasEverSynchedWithServer()
  {
    return this.mLastUpdateTimestamp > 0L;
  }
  
  boolean isDebugModeEnabled()
  {
    return this.mDebugModeEnabled;
  }
  
  boolean isFacebookAttributionEnabled()
  {
    return this.mFacebookAttributionEnabled;
  }
  
  boolean isUpToDate()
  {
    if (this.mDebugModeEnabled) {}
    for (long l = 1800000L; System.currentTimeMillis() - this.mLastUpdateTimestamp < l; l = 86400000L) {
      return true;
    }
    return false;
  }
  
  boolean readFromJSONObject(JSONObject paramJSONObject)
  {
    if (paramJSONObject == null) {
      return false;
    }
    this.mFacebookAttributionEnabled = getBooleanFromJSONObject(paramJSONObject, "facebook_attribution", this.mFacebookAttributionEnabled);
    this.mDebugModeEnabled = getBooleanFromJSONObject(paramJSONObject, "debug_mode_enabled", this.mDebugModeEnabled);
    this.mLastUpdateTimestamp = System.currentTimeMillis();
    return true;
  }
  
  boolean readFromSharedPreferences(SharedPreferences paramSharedPreferences)
  {
    if (paramSharedPreferences == null) {
      return false;
    }
    this.mFacebookAttributionEnabled = paramSharedPreferences.getBoolean("facebook_attribution", this.mFacebookAttributionEnabled);
    this.mDebugModeEnabled = paramSharedPreferences.getBoolean("debug_mode_enabled", this.mDebugModeEnabled);
    this.mLastUpdateTimestamp = paramSharedPreferences.getLong("last_update_timestamp", this.mLastUpdateTimestamp);
    return true;
  }
  
  void setDebugModeEnabled(boolean paramBoolean)
  {
    this.mDebugModeEnabled = paramBoolean;
  }
  
  void updateLastUpdateTimestamp()
  {
    this.mLastUpdateTimestamp = System.currentTimeMillis();
  }
  
  boolean writeToSharedPreferences(SharedPreferences paramSharedPreferences)
  {
    if (paramSharedPreferences == null) {
      return false;
    }
    SharedPreferences.Editor localEditor = paramSharedPreferences.edit();
    localEditor.putBoolean("facebook_attribution", this.mFacebookAttributionEnabled);
    localEditor.putBoolean("debug_mode_enabled", this.mDebugModeEnabled);
    localEditor.putLong("last_update_timestamp", this.mLastUpdateTimestamp);
    return localEditor.commit();
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.fiksu.asotracking.FiksuConfiguration
 * JD-Core Version:    0.7.0.1
 */