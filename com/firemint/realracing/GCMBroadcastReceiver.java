package com.firemint.realracing;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.firemonkeys.cloudcellapi.CC_GCM_Helper_Class;

public class GCMBroadcastReceiver
  extends BroadcastReceiver
{
  private static final boolean LOG_ERROR_ENABLED = false;
  private static final boolean LOG_INFO_ENABLED = false;
  private static final String LOG_TAG = "GCMBroadcastReceiver";
  
  private static void LogError(String paramString) {}
  
  private static void LogInfo(String paramString) {}
  
  private void handleMessage(Context paramContext, Intent paramIntent)
  {
    String str1 = paramIntent.getStringExtra("message");
    LogInfo("GCM message: '" + str1 + "'");
    String str2 = paramIntent.getStringExtra("launchURL");
    LogInfo("GCM launchURL: '" + str2 + "'");
    Intent localIntent = new Intent(paramContext, DelayedNotificationService.class);
    localIntent.putExtra("message", str1);
    localIntent.putExtra("launchURL", str2);
    localIntent.putExtra("id", -1);
    paramContext.startService(localIntent);
  }
  
  private void handleRegistration(Context paramContext, Intent paramIntent)
  {
    String str = paramIntent.getStringExtra("registration_id");
    if (paramIntent.getStringExtra("error") != null) {
      LogError("GCM registration error");
    }
    do
    {
      return;
      if (paramIntent.getStringExtra("unregistered") != null)
      {
        LogInfo("GCM unregistered");
        return;
      }
    } while (str == null);
    LogInfo("GCM registered with id: " + str);
    try
    {
      CC_GCM_Helper_Class.RegisterCallback(str);
      return;
    }
    catch (UnsatisfiedLinkError localUnsatisfiedLinkError) {}
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    LogInfo("GCM intent " + paramIntent.getAction());
    if (paramIntent.getAction().equals("com.google.android.c2dm.intent.REGISTRATION")) {
      handleRegistration(paramContext, paramIntent);
    }
    while (!paramIntent.getAction().equals("com.google.android.c2dm.intent.RECEIVE")) {
      return;
    }
    handleMessage(paramContext, paramIntent);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.firemint.realracing.GCMBroadcastReceiver
 * JD-Core Version:    0.7.0.1
 */