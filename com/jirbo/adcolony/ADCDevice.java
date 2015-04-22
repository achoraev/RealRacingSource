package com.jirbo.adcolony;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.res.Resources;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import java.util.Locale;

class ADCDevice
{
  static String advertising_id;
  static boolean limit_ad_tracking;
  
  static String android_id()
  {
    return Settings.Secure.getString(AdColony.activity().getContentResolver(), "android_id");
  }
  
  static String carrier_name()
  {
    String str = ((TelephonyManager)AdColony.activity().getSystemService("phone")).getNetworkOperatorName();
    if (str.length() == 0) {
      str = "unknown";
    }
    return str;
  }
  
  static String device_id()
  {
    return Installation.id(ADC.activity());
  }
  
  static int display_height()
  {
    return ADC.activity().getWindowManager().getDefaultDisplay().getHeight();
  }
  
  static int display_width()
  {
    return ADC.activity().getWindowManager().getDefaultDisplay().getWidth();
  }
  
  static String imei()
  {
    return "";
  }
  
  static boolean is_tablet()
  {
    DisplayMetrics localDisplayMetrics = AdColony.activity().getResources().getDisplayMetrics();
    float f1 = localDisplayMetrics.widthPixels / localDisplayMetrics.xdpi;
    float f2 = localDisplayMetrics.heightPixels / localDisplayMetrics.ydpi;
    return Math.sqrt(f1 * f1 + f2 * f2) >= 6.0D;
  }
  
  static String language()
  {
    return Locale.getDefault().getLanguage();
  }
  
  static String mac_address()
  {
    try
    {
      String str = ((WifiManager)AdColony.activity().getSystemService("wifi")).getConnectionInfo().getMacAddress();
      return str;
    }
    catch (RuntimeException localRuntimeException) {}
    return null;
  }
  
  static String manufacturer()
  {
    return Build.MANUFACTURER;
  }
  
  static int memory_class()
  {
    Context localContext = ADC.activity().getApplicationContext();
    ADC.activity();
    return ((ActivityManager)localContext.getSystemService("activity")).getMemoryClass();
  }
  
  static long memory_used()
  {
    Runtime localRuntime = Runtime.getRuntime();
    return (localRuntime.totalMemory() - localRuntime.freeMemory()) / 1048576;
  }
  
  static String model()
  {
    return Build.MODEL;
  }
  
  static String open_udid()
  {
    return "";
  }
  
  static String os_version()
  {
    return Build.VERSION.RELEASE;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.jirbo.adcolony.ADCDevice
 * JD-Core Version:    0.7.0.1
 */