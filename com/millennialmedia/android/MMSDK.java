package com.millennialmedia.android;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;
import org.apache.http.conn.util.InetAddressUtils;

public final class MMSDK
{
  private static final String BASE_URL_TRACK_EVENT = "http://ads.mp.mydas.mobi/pixel?id=";
  static final int CACHE_REQUEST_TIMEOUT = 30000;
  static final int CLOSE_ACTIVITY_DURATION = 400;
  static String COMMA;
  public static final String DEFAULT_APID = "28911";
  public static final String DEFAULT_BANNER_APID = "28913";
  public static final String DEFAULT_RECT_APID = "28914";
  static final String EMPTY = "";
  static final int HANDSHAKE_REQUEST_TIMEOUT = 3000;
  static final String JSON_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss ZZZZ";
  public static final int LOG_LEVEL_DEBUG = 1;
  public static final int LOG_LEVEL_ERROR = 0;
  public static final int LOG_LEVEL_INFO = 2;
  @Deprecated
  public static final int LOG_LEVEL_INTERNAL = 4;
  @Deprecated
  public static final int LOG_LEVEL_PRIVATE_VERBOSE = 5;
  public static final int LOG_LEVEL_VERBOSE = 3;
  static final int OPEN_ACTIVITY_DURATION = 600;
  static final String PREFS_NAME = "MillennialMediaSettings";
  static final int REQUEST_TIMEOUT = 10000;
  public static final String SDKLOG = "MMSDK";
  public static final String VERSION = "5.3.0-c3980670.a";
  @Deprecated
  static boolean disableAdMinRefresh = false;
  private static String getMMdidValue;
  private static boolean hasSpeechKit;
  private static boolean isBroadcastingEvents;
  static int logLevel;
  static String macId;
  static Handler mainHandler;
  private static int nextDefaultId = 1897808289;
  
  static
  {
    COMMA = ",";
    mainHandler = new Handler(Looper.getMainLooper());
    getMMdidValue = null;
    hasSpeechKit = false;
    try
    {
      System.loadLibrary("nmsp_speex");
      hasSpeechKit = true;
      return;
    }
    catch (UnsatisfiedLinkError localUnsatisfiedLinkError) {}
  }
  
  static String byteArrayToString(byte[] paramArrayOfByte)
  {
    StringBuilder localStringBuilder = new StringBuilder(2 * paramArrayOfByte.length);
    for (int i = 0; i < paramArrayOfByte.length; i++)
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Byte.valueOf(paramArrayOfByte[i]);
      localStringBuilder.append(String.format("%02X", arrayOfObject));
    }
    return localStringBuilder.toString();
  }
  
  static void checkActivity(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      localPackageManager.getActivityInfo(new ComponentName(paramContext, "com.millennialmedia.android.MMActivity"), 128);
      return;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      MMLog.e("MMSDK", "Activity MMActivity not declared in AndroidManifest.xml", localNameNotFoundException);
      localNameNotFoundException.printStackTrace();
      createMissingPermissionDialog(paramContext, "MMActivity class").show();
    }
  }
  
  static void checkPermissions(Context paramContext)
  {
    if (paramContext.checkCallingOrSelfPermission("android.permission.INTERNET") == -1) {
      createMissingPermissionDialog(paramContext, "INTERNET permission").show();
    }
    if (paramContext.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == -1) {
      createMissingPermissionDialog(paramContext, "ACCESS_NETWORK_STATE permission").show();
    }
  }
  
  private static AlertDialog createMissingPermissionDialog(Context paramContext, String paramString)
  {
    AlertDialog localAlertDialog = new AlertDialog.Builder(paramContext).create();
    localAlertDialog.setTitle("Whoops!");
    localAlertDialog.setMessage(String.format("The developer has forgot to declare the %s in the manifest file. Please reach out to the developer to remove this error.", new Object[] { paramString }));
    localAlertDialog.setButton(-3, "OK", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.cancel();
      }
    });
    localAlertDialog.show();
    return localAlertDialog;
  }
  
  static String getAaid(AdvertisingIdClient.Info paramInfo)
  {
    if (paramInfo == null) {
      return null;
    }
    return paramInfo.getId();
  }
  
  static AdvertisingIdClient.Info getAdvertisingInfo(Context paramContext)
  {
    try
    {
      AdvertisingIdClient.Info localInfo = AdvertisingIdClient.getAdvertisingIdInfo(paramContext);
      return localInfo;
    }
    catch (IOException localIOException)
    {
      MMLog.e("MMSDK", "Unrecoverable error connecting to Google Play services (e.g.,the old version of the service doesnt support getting AdvertisingId", localIOException);
      return null;
    }
    catch (GooglePlayServicesNotAvailableException localGooglePlayServicesNotAvailableException)
    {
      MMLog.e("MMSDK", "Google Play services is not available entirely.", localGooglePlayServicesNotAvailableException);
      return null;
    }
    catch (IllegalStateException localIllegalStateException)
    {
      MMLog.e("MMSDK", "IllegalStateException: ", localIllegalStateException);
      return null;
    }
    catch (GooglePlayServicesRepairableException localGooglePlayServicesRepairableException)
    {
      MMLog.e("MMSDK", "Google Play Services is not installed, up-to-date, or enabled", localGooglePlayServicesRepairableException);
    }
    return null;
  }
  
  public static boolean getBroadcastEvents()
  {
    return isBroadcastingEvents;
  }
  
  static String getCn(Context paramContext)
  {
    return ((TelephonyManager)paramContext.getSystemService("phone")).getNetworkOperatorName();
  }
  
  static Configuration getConfiguration(Context paramContext)
  {
    return paramContext.getResources().getConfiguration();
  }
  
  static String getConnectionType(Context paramContext)
  {
    ConnectivityManager localConnectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
    if (localConnectivityManager == null) {
      return "unknown";
    }
    if ((localConnectivityManager.getActiveNetworkInfo() != null) && (localConnectivityManager.getActiveNetworkInfo().isConnected() == true))
    {
      int i = localConnectivityManager.getActiveNetworkInfo().getType();
      int j = localConnectivityManager.getActiveNetworkInfo().getSubtype();
      if (i == 1) {
        return "wifi";
      }
      if (i == 0)
      {
        switch (j)
        {
        default: 
          return "unknown";
        case 7: 
          return "1xrtt";
        case 4: 
          return "cdma";
        case 2: 
          return "edge";
        case 14: 
          return "ehrpd";
        case 5: 
          return "evdo_0";
        case 6: 
          return "evdo_a";
        case 12: 
          return "evdo_b";
        case 1: 
          return "gprs";
        case 8: 
          return "hsdpa";
        case 10: 
          return "hspa";
        case 15: 
          return "hspap";
        case 9: 
          return "hsupa";
        case 11: 
          return "iden";
        case 13: 
          return "lte";
        }
        return "umts";
      }
      return "unknown";
    }
    return "offline";
  }
  
  public static int getDefaultAdId()
  {
    try
    {
      int i = 1 + nextDefaultId;
      nextDefaultId = i;
      return i;
    }
    finally {}
  }
  
  static float getDensity(Context paramContext)
  {
    return paramContext.getResources().getDisplayMetrics().density;
  }
  
  private static String getDensityString(Context paramContext)
  {
    return Float.toString(getDensity(paramContext));
  }
  
  static String getDpiHeight(Context paramContext)
  {
    return Integer.toString(paramContext.getResources().getDisplayMetrics().heightPixels);
  }
  
  static String getDpiWidth(Context paramContext)
  {
    return Integer.toString(paramContext.getResources().getDisplayMetrics().widthPixels);
  }
  
  static String getIpAddress(Context paramContext)
  {
    StringBuilder localStringBuilder;
    String str2;
    try
    {
      localStringBuilder = new StringBuilder();
      Enumeration localEnumeration1 = NetworkInterface.getNetworkInterfaces();
      for (;;)
      {
        if (!localEnumeration1.hasMoreElements()) {
          break label161;
        }
        Enumeration localEnumeration2 = ((NetworkInterface)localEnumeration1.nextElement()).getInetAddresses();
        while (localEnumeration2.hasMoreElements())
        {
          InetAddress localInetAddress = (InetAddress)localEnumeration2.nextElement();
          if (!localInetAddress.isLoopbackAddress())
          {
            if (localStringBuilder.length() > 0) {
              localStringBuilder.append(',');
            }
            str2 = localInetAddress.getHostAddress().toUpperCase();
            if (!InetAddressUtils.isIPv4Address(str2)) {
              break label120;
            }
            localStringBuilder.append(str2);
          }
        }
      }
      i = str2.indexOf('%');
    }
    catch (Exception localException)
    {
      MMLog.e("MMSDK", "Exception getting ip information: ", localException);
      return "";
    }
    label120:
    int i;
    if (i < 0) {}
    for (String str3 = str2;; str3 = str2.substring(0, i))
    {
      localStringBuilder.append(str3);
      break;
    }
    label161:
    String str1 = localStringBuilder.toString();
    return str1;
  }
  
  @Deprecated
  public static int getLogLevel()
  {
    return MMLog.getLogLevel();
  }
  
  static String getMMdid(Context paramContext)
  {
    for (;;)
    {
      try
      {
        if (getMMdidValue != null)
        {
          str2 = getMMdidValue;
          return str2;
        }
        str1 = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
        str2 = null;
        if (str1 != null) {
          localStringBuilder = new StringBuilder("mmh_");
        }
      }
      finally
      {
        try
        {
          String str1;
          StringBuilder localStringBuilder;
          localStringBuilder.append(byteArrayToString(MessageDigest.getInstance("MD5").digest(str1.getBytes())));
          localStringBuilder.append("_");
          localStringBuilder.append(byteArrayToString(MessageDigest.getInstance("SHA1").digest(str1.getBytes())));
          str2 = localStringBuilder.toString();
          getMMdidValue = str2;
        }
        catch (Exception localException)
        {
          MMLog.e("MMSDK", "Exception calculating hash: ", localException);
          String str2 = null;
        }
        localObject = finally;
      }
    }
  }
  
  static String getMcc(Context paramContext)
  {
    Configuration localConfiguration = getConfiguration(paramContext);
    if (localConfiguration.mcc == 0)
    {
      String str = getNetworkOperator(paramContext);
      if ((str != null) && (str.length() >= 6)) {
        return str.substring(0, 3);
      }
    }
    return String.valueOf(localConfiguration.mcc);
  }
  
  static int getMediaVolume(Context paramContext)
  {
    return ((AudioManager)paramContext.getApplicationContext().getSystemService("audio")).getStreamVolume(3);
  }
  
  static String getMnc(Context paramContext)
  {
    Configuration localConfiguration = getConfiguration(paramContext);
    if (localConfiguration.mnc == 0)
    {
      String str = getNetworkOperator(paramContext);
      if ((str != null) && (str.length() >= 6)) {
        return str.substring(3);
      }
    }
    return String.valueOf(localConfiguration.mnc);
  }
  
  static String getNetworkOperator(Context paramContext)
  {
    return ((TelephonyManager)paramContext.getSystemService("phone")).getNetworkOperator();
  }
  
  static String getOrientation(Context paramContext)
  {
    switch (paramContext.getResources().getConfiguration().orientation)
    {
    default: 
      return "default";
    case 1: 
      return "portrait";
    case 2: 
      return "landscape";
    }
    return "square";
  }
  
  static final String getOrientationLocked(Context paramContext)
  {
    if (Settings.System.getString(paramContext.getContentResolver(), "accelerometer_rotation").equals("1")) {
      return "false";
    }
    return "true";
  }
  
  static boolean getSupportsCalendar()
  {
    return Build.VERSION.SDK_INT >= 14;
  }
  
  static String getSupportsSms(Context paramContext)
  {
    return String.valueOf(paramContext.getPackageManager().hasSystemFeature("android.hardware.telephony"));
  }
  
  static String getSupportsTel(Context paramContext)
  {
    return String.valueOf(paramContext.getPackageManager().hasSystemFeature("android.hardware.telephony"));
  }
  
  static boolean hasMicrophone(Context paramContext)
  {
    return paramContext.getPackageManager().hasSystemFeature("android.hardware.microphone");
  }
  
  static boolean hasRecordAudioPermission(Context paramContext)
  {
    return paramContext.checkCallingOrSelfPermission("android.permission.RECORD_AUDIO") == 0;
  }
  
  static boolean hasSetTranslationMethod()
  {
    return Integer.parseInt(Build.VERSION.SDK) >= 11;
  }
  
  private static String hasSpeechKit(Context paramContext)
  {
    if ((hasSpeechKit) && (hasRecordAudioPermission(paramContext))) {
      return "true";
    }
    return "false";
  }
  
  public static void initialize(Context paramContext)
  {
    HandShake localHandShake = HandShake.sharedHandShake(paramContext);
    localHandShake.sendInitRequest();
    localHandShake.startSession();
  }
  
  static void insertUrlCommonValues(Context paramContext, Map<String, String> paramMap)
  {
    MMLog.d("MMSDK", "executing getIDThread");
    paramMap.put("density", getDensityString(paramContext));
    paramMap.put("hpx", getDpiHeight(paramContext));
    paramMap.put("wpx", getDpiWidth(paramContext));
    paramMap.put("sk", hasSpeechKit(paramContext));
    paramMap.put("mic", Boolean.toString(hasMicrophone(paramContext)));
    String str1 = "true";
    int i = GooglePlayServicesUtil.isGooglePlayServicesAvailable(paramContext);
    String str2 = null;
    if (i == 0)
    {
      AdvertisingIdClient.Info localInfo = getAdvertisingInfo(paramContext);
      str2 = null;
      if (localInfo != null)
      {
        str2 = getAaid(localInfo);
        if ((str2 != null) && (localInfo.isLimitAdTrackingEnabled())) {
          str1 = "false";
        }
      }
    }
    if (str2 != null)
    {
      paramMap.put("aaid", str2);
      paramMap.put("ate", str1);
    }
    for (;;)
    {
      if (isCachedVideoSupportedOnDevice(paramContext))
      {
        paramMap.put("cachedvideo", "true");
        if (Build.MODEL != null) {
          paramMap.put("dm", Build.MODEL);
        }
        if (Build.VERSION.RELEASE != null) {
          paramMap.put("dv", "Android" + Build.VERSION.RELEASE);
        }
        paramMap.put("sdkversion", "5.3.0-c3980670.a");
        paramMap.put("mcc", getMcc(paramContext));
        paramMap.put("mnc", getMnc(paramContext));
        String str4 = getCn(paramContext);
        if (!TextUtils.isEmpty(str4)) {
          paramMap.put("cn", str4);
        }
        Locale localLocale = Locale.getDefault();
        if (localLocale != null)
        {
          paramMap.put("language", localLocale.getLanguage());
          paramMap.put("country", localLocale.getCountry());
        }
      }
      try
      {
        String str14 = paramContext.getPackageName();
        paramMap.put("pkid", str14);
        PackageManager localPackageManager = paramContext.getPackageManager();
        paramMap.put("pknm", localPackageManager.getApplicationLabel(localPackageManager.getApplicationInfo(str14, 0)).toString());
        String str5 = HandShake.sharedHandShake(paramContext).getSchemesList(paramContext);
        if (str5 != null) {
          paramMap.put("appsids", str5);
        }
        String str6 = AdCache.getCachedVideoList(paramContext);
        if (str6 != null) {
          paramMap.put("vid", str6);
        }
        try
        {
          String str7 = getConnectionType(paramContext);
          if (!AdCache.isExternalStorageAvailable(paramContext)) {
            break label760;
          }
          String str13 = AdCache.getCacheDirectory(paramContext).getAbsolutePath();
          localStatFs = new StatFs(str13);
          String str9 = Long.toString(localStatFs.getAvailableBlocks() * localStatFs.getBlockSize());
          Intent localIntent = paramContext.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
          String str10 = null;
          str11 = null;
          if (localIntent != null)
          {
            if (localIntent.getIntExtra("plugged", 0) != 0) {
              break label783;
            }
            str11 = "false";
            str10 = Integer.toString((int)(100.0F / localIntent.getIntExtra("scale", 100) * localIntent.getIntExtra("level", 0)));
          }
          if ((str10 != null) && (str10.length() > 0)) {
            paramMap.put("bl", str10);
          }
          if ((str11 != null) && (str11.length() > 0)) {
            paramMap.put("plugged", str11);
          }
          if (str9.length() > 0) {
            paramMap.put("space", str9);
          }
          if (str7 != null) {
            paramMap.put("conn", str7);
          }
          String str12 = URLEncoder.encode(getIpAddress(paramContext), "UTF-8");
          if (!TextUtils.isEmpty(str12)) {
            paramMap.put("pip", str12);
          }
        }
        catch (Exception localException2)
        {
          for (;;)
          {
            StatFs localStatFs;
            String str11;
            String str3;
            String str8;
            MMLog.e("MMSDK", "Exception inserting common parameters: ", localException2);
          }
        }
        MMRequest.insertLocation(paramMap);
        return;
        str3 = getMMdid(paramContext);
        if (str3 == null) {
          continue;
        }
        paramMap.put("mmdid", str3);
        continue;
        paramMap.put("cachedvideo", "false");
      }
      catch (Exception localException1)
      {
        for (;;)
        {
          MMLog.e("MMSDK", "Can't insert package information", localException1);
          continue;
          label760:
          str8 = paramContext.getFilesDir().getPath();
          localStatFs = new StatFs(str8);
          continue;
          label783:
          str11 = "true";
        }
      }
    }
  }
  
  static boolean isCachedVideoSupportedOnDevice(Context paramContext)
  {
    return (paramContext.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") != -1) && ((!Build.VERSION.SDK.equalsIgnoreCase("8")) || ((Environment.getExternalStorageState().equals("mounted")) && (AdCache.isExternalEnabled)));
  }
  
  static boolean isConnected(Context paramContext)
  {
    boolean bool = true;
    ConnectivityManager localConnectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
    if (localConnectivityManager == null) {
      return false;
    }
    if ((localConnectivityManager.getActiveNetworkInfo() != null) && (localConnectivityManager.getActiveNetworkInfo().isConnected() == bool)) {}
    for (;;)
    {
      return bool;
      bool = false;
    }
  }
  
  static boolean isUiThread()
  {
    return mainHandler.getLooper() == Looper.myLooper();
  }
  
  static void printDiagnostics(MMAdImpl paramMMAdImpl)
  {
    if (paramMMAdImpl == null) {}
    Context localContext;
    String str1;
    do
    {
      return;
      localContext = paramMMAdImpl.getContext();
      Object[] arrayOfObject1 = new Object[1];
      arrayOfObject1[0] = Integer.valueOf(paramMMAdImpl.getId());
      MMLog.i("MMSDK", String.format("MMAd External ID: %d", arrayOfObject1));
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = Long.valueOf(paramMMAdImpl.internalId);
      MMLog.i("MMSDK", String.format("MMAd Internal ID: %d", arrayOfObject2));
      Object[] arrayOfObject3 = new Object[1];
      arrayOfObject3[0] = paramMMAdImpl.apid;
      MMLog.i("MMSDK", String.format("APID: %s", arrayOfObject3));
      Object[] arrayOfObject4 = new Object[1];
      if (!AdCache.isExternalStorageAvailable(localContext)) {
        break;
      }
      str1 = "";
      arrayOfObject4[0] = str1;
      MMLog.i("MMSDK", String.format("SD card is %savailable.", arrayOfObject4));
    } while (localContext == null);
    Object[] arrayOfObject5 = new Object[1];
    arrayOfObject5[0] = localContext.getPackageName();
    MMLog.i("MMSDK", String.format("Package: %s", arrayOfObject5));
    Object[] arrayOfObject6 = new Object[1];
    arrayOfObject6[0] = getMMdid(localContext);
    MMLog.i("MMSDK", String.format("MMDID: %s", arrayOfObject6));
    MMLog.i("MMSDK", "Permissions:");
    Object[] arrayOfObject7 = new Object[1];
    String str2;
    label214:
    String str3;
    label254:
    String str4;
    label295:
    String str5;
    label336:
    String str6;
    label377:
    Object[] arrayOfObject12;
    if (localContext.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == -1)
    {
      str2 = "not ";
      arrayOfObject7[0] = str2;
      MMLog.i("MMSDK", String.format("android.permission.ACCESS_NETWORK_STATE is %spresent", arrayOfObject7));
      Object[] arrayOfObject8 = new Object[1];
      if (localContext.checkCallingOrSelfPermission("android.permission.INTERNET") != -1) {
        break label474;
      }
      str3 = "not ";
      arrayOfObject8[0] = str3;
      MMLog.i("MMSDK", String.format("android.permission.INTERNET is %spresent", arrayOfObject8));
      Object[] arrayOfObject9 = new Object[1];
      if (localContext.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") != -1) {
        break label481;
      }
      str4 = "not ";
      arrayOfObject9[0] = str4;
      MMLog.i("MMSDK", String.format("android.permission.WRITE_EXTERNAL_STORAGE is %spresent", arrayOfObject9));
      Object[] arrayOfObject10 = new Object[1];
      if (localContext.checkCallingOrSelfPermission("android.permission.VIBRATE") != -1) {
        break label488;
      }
      str5 = "not ";
      arrayOfObject10[0] = str5;
      MMLog.i("MMSDK", String.format("android.permission.VIBRATE is %spresent", arrayOfObject10));
      Object[] arrayOfObject11 = new Object[1];
      if (localContext.checkCallingOrSelfPermission("android.permission.ACCESS_COARSE_LOCATION") != -1) {
        break label495;
      }
      str6 = "not ";
      arrayOfObject11[0] = str6;
      MMLog.i("MMSDK", String.format("android.permission.ACCESS_COARSE_LOCATION is %spresent", arrayOfObject11));
      arrayOfObject12 = new Object[1];
      if (localContext.checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION") != -1) {
        break label502;
      }
    }
    label474:
    label481:
    label488:
    label495:
    label502:
    for (String str7 = "not ";; str7 = "")
    {
      arrayOfObject12[0] = str7;
      MMLog.i("MMSDK", String.format("android.permission.ACCESS_FINE_LOCATION is %spresent", arrayOfObject12));
      MMLog.i("MMSDK", "Cached Ads:");
      AdCache.iterateCachedAds(localContext, 2, new AdCache.Iterator()
      {
        boolean callback(CachedAd paramAnonymousCachedAd)
        {
          Object[] arrayOfObject = new Object[4];
          arrayOfObject[0] = paramAnonymousCachedAd.getTypeString();
          arrayOfObject[1] = paramAnonymousCachedAd.getId();
          String str1;
          if (paramAnonymousCachedAd.isOnDisk(this.val$context))
          {
            str1 = "";
            arrayOfObject[2] = str1;
            if (!paramAnonymousCachedAd.isExpired()) {
              break label72;
            }
          }
          label72:
          for (String str2 = "";; str2 = "not ")
          {
            arrayOfObject[3] = str2;
            MMLog.i("MMSDK", String.format("%s %s is %son disk. Is %sexpired.", arrayOfObject));
            return true;
            str1 = "not ";
            break;
          }
        }
      });
      return;
      str1 = "not ";
      break;
      str2 = "";
      break label214;
      str3 = "";
      break label254;
      str4 = "";
      break label295;
      str5 = "";
      break label336;
      str6 = "";
      break label377;
    }
  }
  
  static boolean removeAccelForJira1164()
  {
    return Integer.parseInt(Build.VERSION.SDK) >= 14;
  }
  
  public static void resetCache(Context paramContext)
  {
    AdCache.resetCache(paramContext);
  }
  
  static void runOnUiThread(Runnable paramRunnable)
  {
    if (isUiThread())
    {
      paramRunnable.run();
      return;
    }
    mainHandler.post(paramRunnable);
  }
  
  static void runOnUiThreadDelayed(Runnable paramRunnable, long paramLong)
  {
    mainHandler.postDelayed(paramRunnable, paramLong);
  }
  
  public static void setBroadcastEvents(boolean paramBoolean)
  {
    isBroadcastingEvents = paramBoolean;
  }
  
  @Deprecated
  public static void setLogLevel(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      MMLog.setLogLevel(4);
      return;
    case 1: 
      MMLog.setLogLevel(3);
      return;
    case 2: 
      MMLog.setLogLevel(4);
      return;
    case 3: 
      MMLog.setLogLevel(2);
      return;
    }
    MMLog.setLogLevel(6);
  }
  
  static void setMMdid(String paramString)
  {
    try
    {
      getMMdidValue = paramString;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  static boolean supportsFullScreenInline()
  {
    return Integer.parseInt(Build.VERSION.SDK) >= 11;
  }
  
  public static void trackConversion(Context paramContext, String paramString)
  {
    MMConversionTracker.trackConversion(paramContext, paramString, null);
  }
  
  public static void trackConversion(Context paramContext, String paramString, MMRequest paramMMRequest)
  {
    MMConversionTracker.trackConversion(paramContext, paramString, paramMMRequest);
  }
  
  public static void trackEvent(Context paramContext, String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
    {
      String str = getMMdid(paramContext);
      if (!TextUtils.isEmpty(str)) {
        Utils.HttpUtils.executeUrl("http://ads.mp.mydas.mobi/pixel?id=" + paramString + "&mmdid=" + str);
      }
    }
  }
  
  static class Event
  {
    public static final String INTENT_CALENDAR_EVENT = "calendar";
    public static final String INTENT_EMAIL = "email";
    public static final String INTENT_EXTERNAL_BROWSER = "browser";
    public static final String INTENT_MAPS = "geo";
    public static final String INTENT_MARKET = "market";
    public static final String INTENT_PHONE_CALL = "tel";
    public static final String INTENT_STREAMING_VIDEO = "streamingVideo";
    public static final String INTENT_TXT_MESSAGE = "sms";
    private static final String KEY_ERROR = "error";
    static final String KEY_INTENT_TYPE = "intentType";
    static final String KEY_INTERNAL_ID = "internalId";
    static final String KEY_PACKAGE_NAME = "packageName";
    protected static final String TAG = Event.class.getName();
    
    static void adSingleTap(MMAdImpl paramMMAdImpl)
    {
      if (paramMMAdImpl == null) {}
      do
      {
        return;
        MMSDK.runOnUiThread(new Runnable()
        {
          public void run()
          {
            if ((this.val$adImpl != null) && (this.val$adImpl.requestListener != null)) {}
            try
            {
              this.val$adImpl.requestListener.onSingleTap(this.val$adImpl.getCallingAd());
              return;
            }
            catch (Exception localException)
            {
              MMLog.e("MMSDK", "Exception raised in your RequestListener: ", localException);
            }
          }
        });
      } while (!MMSDK.isBroadcastingEvents);
      sendIntent(paramMMAdImpl.getContext(), new Intent("millennialmedia.action.ACTION_OVERLAY_TAP"), paramMMAdImpl.internalId);
      sendIntent(paramMMAdImpl.getContext(), new Intent("millennialmedia.action.ACTION_AD_SINGLE_TAP"), paramMMAdImpl.internalId);
    }
    
    static void displayStarted(MMAdImpl paramMMAdImpl)
    {
      if (paramMMAdImpl == null)
      {
        MMLog.w("MMSDK", "No Context in the listener: ");
        return;
      }
      if (MMSDK.isBroadcastingEvents) {
        sendIntent(paramMMAdImpl.getContext(), new Intent("millennialmedia.action.ACTION_DISPLAY_STARTED"), paramMMAdImpl.internalId);
      }
      overlayOpened(paramMMAdImpl);
    }
    
    static void fetchStartedCaching(MMAdImpl paramMMAdImpl)
    {
      if (paramMMAdImpl == null) {
        MMLog.w("MMSDK", "No Context in the listener: ");
      }
      do
      {
        return;
        MMSDK.runOnUiThread(new Runnable()
        {
          public void run()
          {
            if ((this.val$adImpl != null) && (this.val$adImpl.requestListener != null)) {}
            try
            {
              this.val$adImpl.requestListener.MMAdRequestIsCaching(this.val$adImpl.getCallingAd());
              return;
            }
            catch (Exception localException)
            {
              MMLog.e("MMSDK", "Exception raised in your RequestListener: ", localException);
            }
          }
        });
      } while (!MMSDK.isBroadcastingEvents);
      sendIntent(paramMMAdImpl.getContext(), new Intent("millennialmedia.action.ACTION_FETCH_STARTED_CACHING"), paramMMAdImpl.internalId);
    }
    
    static void intentStarted(Context paramContext, String paramString, long paramLong)
    {
      if ((MMSDK.isBroadcastingEvents) && (paramString != null)) {
        sendIntent(paramContext, new Intent("millennialmedia.action.ACTION_INTENT_STARTED").putExtra("intentType", paramString), paramLong);
      }
    }
    
    protected static void logEvent(String paramString)
    {
      MMLog.d("Logging event to: %s", paramString);
      Utils.ThreadUtils.execute(new Runnable()
      {
        public void run()
        {
          HttpGetRequest localHttpGetRequest = new HttpGetRequest();
          try
          {
            localHttpGetRequest.get(this.val$logString);
            return;
          }
          catch (Exception localException)
          {
            MMLog.e(MMSDK.Event.TAG, "Error logging event: ", localException);
          }
        }
      });
    }
    
    static void overlayClosed(MMAdImpl paramMMAdImpl)
    {
      if (paramMMAdImpl == null) {
        MMLog.w("MMSDK", "No Context in the listener: ");
      }
      do
      {
        return;
        MMSDK.runOnUiThread(new Runnable()
        {
          public void run()
          {
            if ((this.val$adImpl != null) && (this.val$adImpl.requestListener != null)) {}
            try
            {
              this.val$adImpl.requestListener.MMAdOverlayClosed(this.val$adImpl.getCallingAd());
              return;
            }
            catch (Exception localException)
            {
              MMLog.e("MMSDK", "Exception raised in your RequestListener: ", localException);
            }
          }
        });
      } while ((!MMSDK.isBroadcastingEvents) || (paramMMAdImpl.getContext() == null));
      sendIntent(paramMMAdImpl.getContext(), new Intent("millennialmedia.action.ACTION_OVERLAY_CLOSED"), paramMMAdImpl.internalId);
    }
    
    static void overlayOpened(MMAdImpl paramMMAdImpl)
    {
      if (paramMMAdImpl == null)
      {
        MMLog.w("MMSDK", "No Context in the listener: ");
        return;
      }
      MMSDK.runOnUiThread(new Runnable()
      {
        public void run()
        {
          if ((this.val$adImpl != null) && (this.val$adImpl.requestListener != null)) {}
          try
          {
            this.val$adImpl.requestListener.MMAdOverlayLaunched(this.val$adImpl.getCallingAd());
            return;
          }
          catch (Exception localException)
          {
            MMLog.e("MMSDK", "Exception raised in your RequestListener: ", localException);
          }
        }
      });
      overlayOpenedBroadCast(paramMMAdImpl.getContext(), paramMMAdImpl.internalId);
    }
    
    static void overlayOpenedBroadCast(Context paramContext, long paramLong)
    {
      if (MMSDK.isBroadcastingEvents) {
        sendIntent(paramContext, new Intent("millennialmedia.action.ACTION_OVERLAY_OPENED"), paramLong);
      }
    }
    
    static void requestCompleted(MMAdImpl paramMMAdImpl)
    {
      if (paramMMAdImpl == null) {
        MMLog.w("MMSDK", "No Context in the listener: ");
      }
      do
      {
        return;
        MMSDK.runOnUiThread(new Runnable()
        {
          public void run()
          {
            if ((this.val$adImpl != null) && (this.val$adImpl.requestListener != null)) {}
            try
            {
              this.val$adImpl.requestListener.requestCompleted(this.val$adImpl.getCallingAd());
              return;
            }
            catch (Exception localException)
            {
              MMLog.e("MMSDK", "Exception raised in your RequestListener: ", localException);
            }
          }
        });
      } while (!MMSDK.isBroadcastingEvents);
      String str = paramMMAdImpl.getRequestCompletedAction();
      sendIntent(paramMMAdImpl.getContext(), new Intent(str), paramMMAdImpl.internalId);
    }
    
    static void requestFailed(MMAdImpl paramMMAdImpl, final MMException paramMMException)
    {
      if (paramMMAdImpl == null) {
        MMLog.w("MMSDK", "No Context in the listener: ");
      }
      do
      {
        return;
        MMSDK.runOnUiThread(new Runnable()
        {
          public void run()
          {
            if ((this.val$adImpl != null) && (this.val$adImpl.requestListener != null)) {}
            try
            {
              this.val$adImpl.requestListener.requestFailed(this.val$adImpl.getCallingAd(), paramMMException);
              return;
            }
            catch (Exception localException)
            {
              MMLog.e("MMSDK", "Exception raised in your RequestListener: ", localException);
            }
          }
        });
      } while (!MMSDK.isBroadcastingEvents);
      String str = paramMMAdImpl.getRequestFailedAction();
      sendIntent(paramMMAdImpl.getContext(), new Intent(str).putExtra("error", paramMMException), paramMMAdImpl.internalId);
    }
    
    private static final void sendIntent(Context paramContext, Intent paramIntent, long paramLong)
    {
      String str1;
      if (paramContext != null)
      {
        paramIntent.addCategory("millennialmedia.category.CATEGORY_SDK");
        if (paramLong != -4L) {
          paramIntent.putExtra("internalId", paramLong);
        }
        paramIntent.putExtra("packageName", paramContext.getPackageName());
        str1 = paramIntent.getStringExtra("intentType");
        if (TextUtils.isEmpty(str1)) {
          break label122;
        }
      }
      label122:
      for (String str2 = String.format(" Type[%s]", new Object[] { str1 });; str2 = "")
      {
        MMLog.v("MMSDK", " @@ Intent: " + paramIntent.getAction() + " " + str2 + " for " + paramLong);
        paramContext.sendBroadcast(paramIntent);
        return;
      }
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.MMSDK
 * JD-Core Version:    0.7.0.1
 */