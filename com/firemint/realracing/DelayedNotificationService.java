package com.firemint.realracing;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.IntentService;
import android.app.Notification;
import android.app.Notification.BigTextStyle;
import android.app.Notification.Builder;
import android.app.Notification.InboxStyle;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.SystemClock;

public class DelayedNotificationService
  extends IntentService
{
  static final String ACTION_NOTIFICATION_DISMISSED = "ACTION_NOTIFICATION_DISMISSED";
  static final String GROUP_KEY = "REAL_RACING_NOTIFICATION_GROUP";
  private static final boolean LOG_ERROR_ENABLED = false;
  private static final boolean LOG_INFO_ENABLED = false;
  private static final String LOG_TAG = "RR3_DelayedNotificationService";
  
  public DelayedNotificationService()
  {
    super("DelayedNotificationService");
    LogInfo("created");
  }
  
  private boolean IsConnectedToNetwork()
  {
    ConnectivityManager localConnectivityManager = (ConnectivityManager)getSystemService("connectivity");
    if (localConnectivityManager == null) {}
    NetworkInfo localNetworkInfo;
    do
    {
      return false;
      localNetworkInfo = localConnectivityManager.getActiveNetworkInfo();
    } while ((localNetworkInfo == null) || (!localNetworkInfo.isAvailable()) || (!localNetworkInfo.isConnected()));
    return true;
  }
  
  private static void LogError(String paramString) {}
  
  private static void LogInfo(String paramString) {}
  
  Notification CreateBigTextNotification(Notification.Builder paramBuilder, String paramString)
  {
    return new Notification.BigTextStyle(paramBuilder).bigText(paramString).build();
  }
  
  Notification CreateStackedNotification(SerialisedNotificationInfo paramSerialisedNotificationInfo, Notification.Builder paramBuilder, String paramString)
  {
    Notification.InboxStyle localInboxStyle = new Notification.InboxStyle(paramBuilder);
    localInboxStyle.addLine(paramString);
    int i = 0;
    int j = -1 + paramSerialisedNotificationInfo.GetNotificationCount();
    for (;;)
    {
      Object localObject;
      if (j >= 0) {
        localObject = "";
      }
      try
      {
        String str = "- " + paramSerialisedNotificationInfo.GetNotificationString(j);
        localObject = str;
      }
      catch (Exception localException)
      {
        for (;;)
        {
          LogError("Error getting notification string: " + localException.toString());
        }
        j--;
      }
      localInboxStyle.addLine((CharSequence)localObject);
      i++;
      if (i >= 4) {
        return localInboxStyle.build();
      }
    }
  }
  
  public Uri GetNotificationSoundUri()
  {
    return Uri.parse("android.resource://" + getPackageName() + "/" + 2131034113);
  }
  
  void NotificationDismissed()
  {
    SerialiseNotificationsHelper.ClearAll(getApplicationContext());
  }
  
  @TargetApi(21)
  protected void onHandleIntent(Intent paramIntent)
  {
    LogInfo("DelayedNotificationService: onHandleIntent");
    if ((paramIntent.getAction() != null) && (paramIntent.getAction().equals("ACTION_NOTIFICATION_DISMISSED")))
    {
      NotificationDismissed();
      return;
    }
    int i = paramIntent.getIntExtra("id", -2);
    String str1 = paramIntent.getStringExtra("message");
    String str2 = paramIntent.getStringExtra("launchURL");
    if ((str2 != null) && ((str2.contains("MultiplayerInvite")) || (str2.contains("RaceTeamsAdmin")))) {}
    for (int j = 1; (MainActivity.instance != null) && (MainActivity.instance.hasWindowFocus()) && (str1 != null) && (str2 != null); j = 0)
    {
      MainActivity.instance.setBackgroundLaunchURL(str1, str2);
      return;
    }
    if (i == 1)
    {
      if (!IsConnectedToNetwork())
      {
        showDelayedNotification(paramIntent, 3600);
        return;
      }
      if (paramIntent.getIntExtra("reminder", 0) >= 1) {
        showDelayedNotification(paramIntent, 172800);
      }
    }
    NotificationManager localNotificationManager = (NotificationManager)getSystemService("notification");
    long l = System.currentTimeMillis();
    Intent localIntent1 = new Intent(this, MainActivity.class);
    if (str2 != null) {
      localIntent1.putExtra("launchURL", str2);
    }
    PendingIntent localPendingIntent = PendingIntent.getActivity(this, 0, localIntent1, 268435456);
    Notification.Builder localBuilder2;
    Notification localNotification;
    if (Build.VERSION.SDK_INT >= 16)
    {
      Notification.Builder localBuilder1 = new Notification.Builder(getApplicationContext()).setContentTitle(getResources().getString(2131099648)).setContentText(str1).setSmallIcon(2130837578).setContentIntent(localPendingIntent).setAutoCancel(true);
      if (i == 2) {
        l = 0L;
      }
      localBuilder2 = localBuilder1.setWhen(l);
      if (j != 0) {
        localBuilder2.setTicker(str1);
      }
      if (MainActivity.IsAtLeastAPI(21))
      {
        localBuilder2.setColor(getResources().getColor(2131296286));
        localBuilder2.setGroup("REAL_RACING_NOTIFICATION_GROUP");
      }
      Intent localIntent2 = new Intent(this, DelayedNotificationService.class);
      localIntent2.setAction("ACTION_NOTIFICATION_DISMISSED");
      localBuilder2.setDeleteIntent(PendingIntent.getService(this, 0, localIntent2, 134217728));
      SerialisedNotificationInfo localSerialisedNotificationInfo = SerialiseNotificationsHelper.GetSavedInfo(getApplicationContext());
      int m = localSerialisedNotificationInfo.GetNotificationCount();
      LogInfo("There are (" + m + ") saved notifications");
      if (m > 0) {
        localNotification = CreateStackedNotification(localSerialisedNotificationInfo, localBuilder2, str1);
      }
    }
    for (;;)
    {
      if (i != 2)
      {
        Uri localUri = GetNotificationSoundUri();
        localNotification.sound = localUri;
        SerialiseNotificationsHelper.AddNotification(getApplicationContext(), str1);
      }
      LogInfo("DelayedNotificationService: showing notification id: " + i + " msg: " + str1);
      try
      {
        localNotificationManager.notify(0, localNotification);
        return;
      }
      catch (Exception localException)
      {
        LogInfo("DelayedNotificationService: Notification Exception: " + localException.toString());
        return;
      }
      localNotification = CreateBigTextNotification(localBuilder2, str1);
      continue;
      localNotification = new Notification(2130837578, str1, l);
      int k = 0x10 | localNotification.flags;
      localNotification.flags = k;
      if (i == 2) {
        l = 0L;
      }
      localNotification.when = l;
      Context localContext = getApplicationContext();
      String str3 = getResources().getString(2131099648);
      localNotification.setLatestEventInfo(localContext, str3, str1, localPendingIntent);
    }
  }
  
  public void showDelayedNotification(Intent paramIntent, int paramInt)
  {
    long l = SystemClock.elapsedRealtime() + paramInt * 1000;
    int i = paramIntent.getIntExtra("id", -2);
    String str = paramIntent.getStringExtra("message");
    LogInfo("Notifications : showDelayedNotification: " + str + " " + paramInt);
    if (i == 1)
    {
      int j = paramIntent.getIntExtra("reminder", 0);
      if ((j >= 1) && (paramInt == 172800)) {
        paramIntent.putExtra("reminder", j - 1);
      }
    }
    PendingIntent localPendingIntent = PendingIntent.getService(this, 0, paramIntent, 268435456);
    ((AlarmManager)getSystemService("alarm")).set(3, l, localPendingIntent);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.firemint.realracing.DelayedNotificationService
 * JD-Core Version:    0.7.0.1
 */