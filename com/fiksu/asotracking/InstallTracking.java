package com.fiksu.asotracking;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class InstallTracking
  extends BroadcastReceiver
{
  private static final String FIKSU_RECEIVER = "com.fiksu.asotracking.InstallTracking";
  private static final String INTENT_NAME = "com.android.vending.INSTALL_REFERRER";
  private static final long MAX_BLOCK_MS = 3000L;
  
  static void checkForFiksuReceiver(Context paramContext)
  {
    List localList = readReceiversFromManifest(paramContext);
    if (localList != null)
    {
      if ((localList.size() == 0) || (!((String)localList.get(0)).equals("com.fiksu.asotracking.InstallTracking")))
      {
        if (localList.size() > 0) {}
        for (String str = (String)localList.get(0);; str = "NONE")
        {
          Log.e("FiksuTracking", "THE FIKSU INSTALL TRACKING CODE ISN'T INSTALLED CORRECTLY!");
          Log.e("FiksuTracking", "Unexpected receiver: " + str);
          throw new FiksuIntegrationError("The Fiksu BroadcastReceiver must be installed as the only receiver for the INSTALL_REFERRER Intent in AndroidManifest.xml.");
        }
      }
      if (localList.size() > 1)
      {
        Log.e("FiksuTracking", "THE FIKSU INSTALL TRACKING CODE ISN'T INSTALLED CORRECTLY!");
        Log.e("FiksuTracking", "Multiple receivers declared for: com.android.vending.INSTALL_REFERRER");
        throw new FiksuIntegrationError("Multiple receivers declared for: com.android.vending.INSTALL_REFERRER");
      }
    }
  }
  
  private void forwardToOtherReceivers(Context paramContext, Intent paramIntent)
  {
    List localList = readTargetsFromMetaData(paramContext);
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator1 = localList.iterator();
    Iterator localIterator2;
    label40:
    Iterator localIterator3;
    if (!localIterator1.hasNext())
    {
      localIterator2 = localList.iterator();
      if (localIterator2.hasNext()) {
        break label129;
      }
      localIterator3 = localArrayList.iterator();
    }
    for (;;)
    {
      if (!localIterator3.hasNext())
      {
        return;
        String str = (String)localIterator1.next();
        if (str.equals("com.fiksu.asotracking.InstallTracking")) {
          localIterator1.remove();
        }
        if (!str.startsWith("getjar.")) {
          break;
        }
        localArrayList.add(0, str);
        localIterator1.remove();
        break;
        label129:
        forwardToReceiver(paramContext, paramIntent, (String)localIterator2.next());
        break label40;
      }
      forwardToReceiver(paramContext, paramIntent, (String)localIterator3.next());
    }
  }
  
  private void forwardToReceiver(Context paramContext, Intent paramIntent, String paramString)
  {
    try
    {
      ((BroadcastReceiver)Class.forName(paramString).newInstance()).onReceive(paramContext, paramIntent);
      Log.d("FiksuTracking", "Forwarded to: " + paramString);
      return;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      Log.e("FiksuTracking", "Forward failed, couldn't load class: " + paramString);
      return;
    }
    catch (Exception localException)
    {
      Log.e("FiksuTracking", "Forwarding to " + paramString + " failed:", localException);
    }
  }
  
  private static List<String> readReceiversFromManifest(Context paramContext)
  {
    try
    {
      PackageManager localPackageManager = paramContext.getPackageManager();
      Intent localIntent = new Intent("com.android.vending.INSTALL_REFERRER");
      localIntent.setPackage(paramContext.getPackageName());
      List localList = localPackageManager.queryBroadcastReceivers(localIntent, 0);
      ArrayList localArrayList = new ArrayList();
      Iterator localIterator = localList.iterator();
      for (;;)
      {
        if (!localIterator.hasNext()) {
          return localArrayList;
        }
        ResolveInfo localResolveInfo = (ResolveInfo)localIterator.next();
        if ((localResolveInfo != null) && (localResolveInfo.activityInfo != null) && (localResolveInfo.activityInfo.name != null)) {
          localArrayList.add(localResolveInfo.activityInfo.name);
        }
      }
      return null;
    }
    catch (Exception localException) {}
  }
  
  private static List<String> readTargetsFromMetaData(Context paramContext)
  {
    ArrayList localArrayList1 = new ArrayList();
    PackageManager localPackageManager = paramContext.getPackageManager();
    if (localPackageManager == null) {
      Log.e("FiksuTracking", "Couldn't get PackageManager.");
    }
    for (;;)
    {
      return localArrayList1;
      ActivityInfo localActivityInfo;
      try
      {
        localActivityInfo = localPackageManager.getReceiverInfo(new ComponentName(paramContext, InstallTracking.class), 128);
        if ((localActivityInfo == null) || (localActivityInfo.metaData == null) || (localActivityInfo.metaData.keySet() == null))
        {
          Log.d("FiksuTracking", "No forwarding metadata.");
          return localArrayList1;
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        Log.e("FiksuTracking", "Couldn't get info for receivers.");
        return localArrayList1;
      }
      Bundle localBundle = localActivityInfo.metaData;
      ArrayList localArrayList2 = new ArrayList(localBundle.keySet());
      Collections.sort(localArrayList2);
      Iterator localIterator = localArrayList2.iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        if (str.startsWith("forward.")) {
          if ((localBundle.getString(str) == null) || (localBundle.getString(str).trim().equals(""))) {
            Log.e("FiksuTracking", "Couldn't parse receiver from metadata.");
          } else {
            localArrayList1.add(localBundle.getString(str).trim());
          }
        }
      }
    }
  }
  
  private void uploadConversionEvent(Context paramContext, Intent paramIntent)
  {
    try
    {
      String str1 = paramIntent.getStringExtra("referrer");
      if (str1 != null) {}
      for (String str2 = URLDecoder.decode(str1, "UTF-8");; str2 = "")
      {
        new ConversionEventTracker(paramContext, str2).uploadEventSynchronously(3000L);
        return;
      }
      return;
    }
    catch (Exception localException)
    {
      Log.e("FiksuTracking", "Unhandled exception processing intent.", localException);
    }
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    uploadConversionEvent(paramContext, paramIntent);
    forwardToOtherReceivers(paramContext, paramIntent);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.fiksu.asotracking.InstallTracking
 * JD-Core Version:    0.7.0.1
 */