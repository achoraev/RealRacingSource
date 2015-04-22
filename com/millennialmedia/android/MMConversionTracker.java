package com.millennialmedia.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import java.lang.reflect.Field;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.TreeMap;

final class MMConversionTracker
{
  private static final String KEY_REFERRER = "installReferrer";
  private static final String TAG = "MMConversionTracker";
  
  protected static void trackConversion(Context paramContext, String paramString, MMRequest paramMMRequest)
  {
    long l1 = 0L;
    if ((paramContext != null) && (paramString != null)) {}
    for (;;)
    {
      final boolean bool;
      TreeMap localTreeMap;
      int k;
      PackageInfo localPackageInfo;
      long l3;
      try
      {
        int i = paramString.length();
        if (i == 0) {
          return;
        }
        SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("MillennialMediaSettings", 0);
        bool = localSharedPreferences.getBoolean("firstLaunch_" + paramString, true);
        String str = localSharedPreferences.getString("installReferrer", null);
        localTreeMap = new TreeMap();
        if (paramMMRequest != null)
        {
          paramMMRequest.getUrlParams(localTreeMap);
          MMRequest.insertLocation(localTreeMap);
        }
        if (str != null)
        {
          String[] arrayOfString1 = str.split("&");
          int j = arrayOfString1.length;
          k = 0;
          if (k < j)
          {
            String[] arrayOfString2 = arrayOfString1[k].split("=");
            if (arrayOfString2.length < 2) {
              break label410;
            }
            localTreeMap.put(arrayOfString2[0], arrayOfString2[1]);
            break label410;
          }
        }
        if (bool)
        {
          SharedPreferences.Editor localEditor = localSharedPreferences.edit();
          localEditor.putBoolean("firstLaunch_" + paramString, false);
          localEditor.commit();
        }
      }
      finally {}
      for (;;)
      {
        try
        {
          localPackageInfo = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0);
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          MMLog.e("MMConversionTracker", "Can't find packagename: ", localNameNotFoundException);
          break;
          MMLog.w("MMConversionTracker", "No network available for conversion tracking.");
        }
        try
        {
          l3 = localPackageInfo.getClass().getField("firstInstallTime").getLong(localPackageInfo);
          l1 = l3;
        }
        catch (Exception localException)
        {
          MMLog.e("MMConversionTracker", "Error with firstInstallTime", localException);
          break;
        }
      }
      if (l1 > 0L)
      {
        GregorianCalendar localGregorianCalendar = new GregorianCalendar();
        localGregorianCalendar.setTimeInMillis(l1);
        localGregorianCalendar.setTimeZone(TimeZone.getTimeZone("GMT"));
        l1 = localGregorianCalendar.getTimeInMillis();
      }
      final long l2 = l1;
      if (MMSDK.isConnected(paramContext))
      {
        localTreeMap.put("ua", "Android:" + Build.MODEL);
        localTreeMap.put("apid", HandShake.apid);
        MMSDK.insertUrlCommonValues(paramContext, localTreeMap);
        Utils.ThreadUtils.execute(new Runnable()
        {
          public void run()
          {
            HttpGetRequest localHttpGetRequest = new HttpGetRequest();
            try
            {
              localHttpGetRequest.trackConversion(this.val$goalId, bool, l2, this.val$extraParams);
              return;
            }
            catch (Exception localException)
            {
              MMLog.e("MMConversionTracker", "Problem doing conversion tracking.", localException);
            }
          }
        });
      }
      else
      {
        continue;
        label410:
        k++;
      }
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.MMConversionTracker
 * JD-Core Version:    0.7.0.1
 */