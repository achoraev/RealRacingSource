package com.firemint.realracing;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.SystemClock;
import java.util.ArrayList;

public class LocalNotificationsCenter
{
  public static final String EXTRA_ID = "id";
  public static final String EXTRA_LAUNCH_URL = "launchURL";
  public static final String EXTRA_LAUNCH_URL_MP_INVITE = "MultiplayerInvite";
  public static final String EXTRA_LAUNCH_URL_RT_ADMIN = "RaceTeamsAdmin";
  public static final String EXTRA_MESSAGE = "message";
  public static final String EXTRA_REMINDER = "reminder";
  private static final boolean LOG_ERROR_ENABLED;
  private static final boolean LOG_INFO_ENABLED;
  private static String LOG_TAG = "LocalNotificationsCenter";
  private static ArrayList<PendingIntent> mNotificationsIntent = new ArrayList();
  
  public static void CancelAllNotifications()
  {
    LogInfo("CancelAllNotifications: start");
    Context localContext = AppProxy.GetContext();
    NotificationManager localNotificationManager = (NotificationManager)localContext.getSystemService("notification");
    AlarmManager localAlarmManager = (AlarmManager)localContext.getSystemService("alarm");
    localNotificationManager.cancelAll();
    for (int i = 0; i < mNotificationsIntent.size(); i++)
    {
      PendingIntent localPendingIntent = (PendingIntent)mNotificationsIntent.get(i);
      localAlarmManager.cancel(localPendingIntent);
      localPendingIntent.cancel();
    }
    mNotificationsIntent.clear();
    LogInfo("CancelAllNotifications: end");
    SerialiseNotificationsHelper.ClearAll(localContext);
  }
  
  public static void CancelNotification(int paramInt, String paramString)
  {
    Context localContext = AppProxy.GetContext();
    AlarmManager localAlarmManager = (AlarmManager)localContext.getSystemService("alarm");
    Intent localIntent = new Intent(localContext, DelayedNotificationService.class);
    localIntent.setType("type" + paramString);
    PendingIntent localPendingIntent = PendingIntent.getService(localContext, 0, localIntent, 268435456);
    NotificationManager localNotificationManager = (NotificationManager)localContext.getSystemService("notification");
    try
    {
      localNotificationManager.cancel(paramInt);
      localAlarmManager.cancel(localPendingIntent);
      return;
    }
    catch (Exception localException)
    {
      LogError("CancelNotification: failed to cancel alarm: " + localException.toString());
    }
  }
  
  private static void LogError(String paramString) {}
  
  private static void LogInfo(String paramString) {}
  
  @TargetApi(19)
  public static void showNotification(int paramInt, String paramString1, long paramLong, String paramString2)
  {
    long l1 = SystemClock.elapsedRealtime() + 1000L * paramLong;
    LogInfo("showNotification id: " + paramInt + " delay: " + paramLong + " msg: " + paramString1);
    LogInfo("showNotification current time: " + SystemClock.elapsedRealtime());
    LogInfo("showNotification fire time:    " + l1);
    if (paramString2 != null) {
      LogInfo("showNotification url: " + paramString2);
    }
    Context localContext = AppProxy.GetContext();
    AlarmManager localAlarmManager = (AlarmManager)localContext.getSystemService("alarm");
    Intent localIntent = new Intent(localContext, DelayedNotificationService.class);
    localIntent.setType("type" + paramString1);
    localIntent.putExtra("message", paramString1);
    localIntent.putExtra("id", paramInt);
    if (paramInt == 1)
    {
      long l2 = Platform.getAppInstallTime();
      localIntent.putExtra("reminder", 2 - (int)Math.floor((System.currentTimeMillis() - l2) / 172800000L));
    }
    if ((paramString2 != null) && (paramString2.length() > 0)) {
      localIntent.putExtra("launchURL", paramString2);
    }
    PendingIntent localPendingIntent = PendingIntent.getService(localContext, 0, localIntent, 268435456);
    if (Build.VERSION.SDK_INT < 19) {
      localAlarmManager.set(3, l1, localPendingIntent);
    }
    for (;;)
    {
      mNotificationsIntent.add(localPendingIntent);
      return;
      localAlarmManager.setExact(3, l1, localPendingIntent);
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.firemint.realracing.LocalNotificationsCenter
 * JD-Core Version:    0.7.0.1
 */