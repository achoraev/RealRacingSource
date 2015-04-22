package com.firemonkeys.cloudcellapi;

import android.app.Activity;
import android.util.Log;
import com.google.android.gcm.GCMRegistrar;

public class CC_GCM_Helper_Class
{
  private static String s_senderID;
  
  public static native void NotificationCallback();
  
  public static native void RegisterCallback(String paramString);
  
  public static String getSenderID()
  {
    return s_senderID;
  }
  
  public void RegisterApplicationForPushNotifications()
  {
    Log.v("GCM", "GCM RegisterApplicationForPushNotifications from java...");
    try
    {
      Activity localActivity = CC_Activity.GetActivity();
      GCMRegistrar.checkDevice(localActivity);
      GCMRegistrar.checkManifest(localActivity);
      String[] arrayOfString = new String[1];
      arrayOfString[0] = s_senderID;
      GCMRegistrar.register(localActivity, arrayOfString);
      Log.v("GCM", "GCM has registered application for PN. SenderID=" + s_senderID + " Package=" + localActivity.getPackageName());
      return;
    }
    catch (Exception localException)
    {
      Log.v("GCM", "GCM Couldn't register application for PN");
    }
  }
  
  public void setSenderID(String paramString)
  {
    s_senderID = paramString;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.firemonkeys.cloudcellapi.CC_GCM_Helper_Class
 * JD-Core Version:    0.7.0.1
 */