package com.fiksu.asotracking;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.Log;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Pattern;

final class EventUploader
  implements Runnable
{
  private static final String FIKSU_ENDPOINT_1 = "https://a.fiksu.com/";
  private static final String FIKSU_SAVED_URLS_PREFERENCE_NAME = "Fiksu.savedUrls";
  private static final String FIKSU_SEPARATOR = "<FIKSU>";
  private static final Pattern FIKSU_SEPARATOR_PATTERN = Pattern.compile("<FIKSU>");
  private static final int MAX_FAILED_URLS = 10;
  static final String TRACKING_REV = "50015";
  private final Context mContext;
  private final Map<String, String> mParameters;
  
  EventUploader(Context paramContext, Map<String, String> paramMap)
  {
    this.mParameters = paramMap;
    this.mContext = paramContext;
  }
  
  private void appendParameterToUrl(StringBuilder paramStringBuilder, FiksuEventParameter paramFiksuEventParameter)
    throws UnsupportedEncodingException
  {
    String str = (String)this.mParameters.get(paramFiksuEventParameter.getName());
    if (str != null) {
      paramStringBuilder.append("&" + paramFiksuEventParameter.getName() + "=" + encodeParameter(str));
    }
  }
  
  private String buildURL()
  {
    if (this.mContext == null)
    {
      Log.e("FiksuTracking", "Could not find context to use.  Please set it in your main Activity class using EventTracking.setContext().");
      return null;
    }
    String str1 = (String)this.mParameters.get(FiksuEventParameter.EVENT.getName());
    Log.d("FiksuTracking", "Event: " + str1);
    str2 = this.mContext.getPackageName();
    StringBuilder localStringBuilder1 = new StringBuilder(512);
    localStringBuilder1.append("https://a.fiksu.com/" + "50015" + "/android/" + str2 + "/event?");
    for (;;)
    {
      try
      {
        localStringBuilder1.append("appid=" + this.mContext.getPackageName());
        AndroidAdvertisingConfiguration localAndroidAdvertisingConfiguration = AndroidAdvertisingConfiguration.create(this.mContext);
        if ((localAndroidAdvertisingConfiguration.isGooglePlayLibraryPresent()) && (localAndroidAdvertisingConfiguration.isGooglePlayServicesAvailable()))
        {
          localStringBuilder1.append("&a_id=" + localAndroidAdvertisingConfiguration.getAdvertisingIdentifier());
          StringBuilder localStringBuilder3 = new StringBuilder("&a_enabled=");
          if (localAndroidAdvertisingConfiguration.limitAdTracking())
          {
            str7 = "0";
            localStringBuilder1.append(str7);
            localStringBuilder1.append("&device=" + encodeParameter(Build.MODEL));
            String str3 = FiksuDeviceSettingsManager.getInstance().getClientId();
            if (str3.length() > 0) {
              localStringBuilder1.append("&clientid=" + encodeParameter(str3));
            }
            if (FiksuConfigurationManager.getInstance().getFiksuConfiguration().isFacebookAttributionEnabled())
            {
              String str6 = FacebookAttribution.getAttributionId(this.mContext);
              if (str6 != null) {
                localStringBuilder1.append("&fb_id=" + str6);
              }
            }
          }
        }
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        String str7;
        PackageManager localPackageManager;
        String str5;
        Locale localLocale;
        StringBuilder localStringBuilder2;
        Log.e("FiksuTracking", "Problem creating URL", localUnsupportedEncodingException);
        return null;
      }
      try
      {
        localPackageManager = this.mContext.getPackageManager();
        localStringBuilder1.append("&app_version=" + encodeParameter(localPackageManager.getPackageInfo(str2, 0).versionName));
        str5 = localPackageManager.getApplicationInfo(str2, 0).loadLabel(localPackageManager).toString();
        if (str5 != null) {
          localStringBuilder1.append("&app_name=" + encodeParameter(str5));
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        Log.e("FiksuTracking", "Could not access package: " + str2);
        continue;
      }
      catch (Exception localException)
      {
        Log.e("FiksuTracking", "Unexpected exception", localException);
        continue;
        str4 = "0";
        continue;
      }
      localStringBuilder1.append("&system_version=" + Build.VERSION.RELEASE);
      localStringBuilder1.append("&system_name=" + encodeParameter(Build.PRODUCT));
      localLocale = this.mContext.getResources().getConfiguration().locale;
      localStringBuilder1.append("&country=" + encodeParameter(localLocale.getCountry()));
      localStringBuilder1.append("&lang=" + encodeParameter(localLocale.getLanguage()));
      localStringBuilder1.append("&timezone=" + encodeParameter(TimeZone.getDefault().getDisplayName()));
      localStringBuilder1.append("&gmtoffset=" + TimeZone.getDefault().getRawOffset() / 1000);
      if (str1 != null) {
        localStringBuilder1.append("&event=" + str1);
      }
      appendParameterToUrl(localStringBuilder1, FiksuEventParameter.USERNAME);
      appendParameterToUrl(localStringBuilder1, FiksuEventParameter.TVALUE);
      appendParameterToUrl(localStringBuilder1, FiksuEventParameter.FVALUE);
      appendParameterToUrl(localStringBuilder1, FiksuEventParameter.IVALUE);
      localStringBuilder2 = new StringBuilder("&app_tracking_enabled=");
      if (!FiksuDeviceSettingsManager.getInstance().isAppTrackingEnabled()) {
        break label910;
      }
      str4 = "1";
      localStringBuilder1.append(str4);
      return localStringBuilder1.toString();
      str7 = "1";
      continue;
      if (FiksuDeviceSettingsManager.getInstance().getDisableGetDeviceId()) {
        localStringBuilder1.append("&deviceiddisabled=1");
      }
    }
    for (;;)
    {
      localStringBuilder1.append("&udid=" + FiksuDeviceSettingsManager.getInstance().getAndroidId(this.mContext));
      break;
      localStringBuilder1.append("&deviceid=" + FiksuDeviceSettingsManager.getInstance().getDeviceId(this.mContext));
    }
  }
  
  private boolean doUpload(String paramString)
    throws MalformedURLException
  {
    try
    {
      int i = ((HttpURLConnection)new URL(paramString).openConnection()).getResponseCode();
      if (i == 200)
      {
        Log.d("FiksuTracking", "Successfully uploaded tracking information.");
        return true;
      }
      Log.e("FiksuTracking", "Failed to upload tracking information, bad response: " + i);
      if (i >= 500) {
        return false;
      }
    }
    catch (IOException localIOException)
    {
      Log.e("FiksuTracking", "Failed to upload tracking information: " + localIOException.getClass().getCanonicalName() + ":" + localIOException.getMessage());
      return false;
    }
    catch (NullPointerException localNullPointerException)
    {
      if (!Build.VERSION.RELEASE.startsWith("3.")) {
        throw new RuntimeException("Caught NullPointerException with Android OS version " + Build.VERSION.RELEASE + " (Tracking rev= " + "50015" + ")", localNullPointerException);
      }
    }
    return true;
  }
  
  private String encodeParameter(String paramString)
    throws UnsupportedEncodingException
  {
    if (paramString == null) {
      return "";
    }
    return URLEncoder.encode(paramString, "UTF-8");
  }
  
  private List<String> getSavedUrls()
  {
    ArrayList localArrayList = new ArrayList();
    SharedPreferences localSharedPreferences = EventTracker.getOurSharedPreferences(this.mContext);
    String[] arrayOfString;
    int i;
    if (localSharedPreferences != null)
    {
      String str = localSharedPreferences.getString("Fiksu.savedUrls", "");
      if ((str != null) && (!str.equals("")))
      {
        arrayOfString = FIKSU_SEPARATOR_PATTERN.split(str);
        i = arrayOfString.length;
      }
    }
    for (int j = 0;; j++)
    {
      if (j >= i) {
        return localArrayList;
      }
      localArrayList.add(arrayOfString[j]);
    }
  }
  
  private void saveFailedUrls(List<String> paramList)
  {
    if (paramList.size() > 10) {
      paramList = new ArrayList(paramList.subList(-10 + paramList.size(), paramList.size()));
    }
    StringBuilder localStringBuilder = new StringBuilder();
    if (paramList.size() > 0) {
      localStringBuilder.append((String)paramList.get(0));
    }
    for (int i = 1;; i++)
    {
      if (i >= paramList.size())
      {
        SharedPreferences.Editor localEditor = EventTracker.getOurSharedPreferences(this.mContext).edit();
        localEditor.putString("Fiksu.savedUrls", localStringBuilder.toString());
        localEditor.commit();
        return;
      }
      localStringBuilder.append("<FIKSU>" + (String)paramList.get(i));
    }
  }
  
  private void uploadToTracking()
  {
    if (this.mContext == null)
    {
      Log.e("FiksuTracking", "Could not find context to use.  Please set it in your main Activity class using EventTracking.setContext().");
      return;
    }
    String str1 = buildURL();
    for (;;)
    {
      ArrayList localArrayList;
      Iterator localIterator;
      synchronized (FiksuConstants.SHARED_PREFERENCES_LOCK)
      {
        List localList = getSavedUrls();
        if (str1 != null)
        {
          localList.add(str1);
          if (((String)this.mParameters.get(FiksuEventParameter.EVENT.getName())).equals(FiksuEventType.CONVERSION.getName())) {
            saveFailedUrls(localList);
          }
        }
        localArrayList = new ArrayList();
        localIterator = localList.iterator();
        if (!localIterator.hasNext())
        {
          saveFailedUrls(localArrayList);
          return;
        }
      }
      String str2 = (String)localIterator.next();
      try
      {
        if (!doUpload(str2))
        {
          Log.e("FiksuTracking", "Upload failed for url.  Saving it for retry later: " + str2);
          localArrayList.add(str2);
        }
      }
      catch (MalformedURLException localMalformedURLException)
      {
        Log.e("FiksuTracking", str2);
        Log.e("FiksuTracking", localMalformedURLException.toString());
      }
    }
  }
  
  /* Error */
  public void run()
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 499	com/fiksu/asotracking/EventUploader:uploadToTracking	()V
    //   4: aload_0
    //   5: monitorenter
    //   6: aload_0
    //   7: invokevirtual 502	java/lang/Object:notifyAll	()V
    //   10: aload_0
    //   11: monitorexit
    //   12: return
    //   13: astore_1
    //   14: aload_0
    //   15: monitorenter
    //   16: aload_0
    //   17: invokevirtual 502	java/lang/Object:notifyAll	()V
    //   20: aload_0
    //   21: monitorexit
    //   22: aload_1
    //   23: athrow
    //   24: astore_2
    //   25: aload_0
    //   26: monitorexit
    //   27: aload_2
    //   28: athrow
    //   29: astore_3
    //   30: aload_0
    //   31: monitorexit
    //   32: aload_3
    //   33: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	34	0	this	EventUploader
    //   13	10	1	localObject1	Object
    //   24	4	2	localObject2	Object
    //   29	4	3	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   0	4	13	finally
    //   16	22	24	finally
    //   25	27	24	finally
    //   6	12	29	finally
    //   30	32	29	finally
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.fiksu.asotracking.EventUploader
 * JD-Core Version:    0.7.0.1
 */