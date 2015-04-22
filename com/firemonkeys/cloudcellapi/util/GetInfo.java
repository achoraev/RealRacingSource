package com.firemonkeys.cloudcellapi.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.util.Log;
import com.firemonkeys.cloudcellapi.CC_Activity;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.Locale;
import org.apache.http.conn.util.InetAddressUtils;

public class GetInfo
{
  static final String TAG = "cloudcellapi.util.GetInfo";
  static boolean ms_bAdvertisingEnabled = false;
  static String ms_sAdvertisingIdentifier = "";
  
  public static boolean GetAdvertisingEnabled()
  {
    return ms_bAdvertisingEnabled;
  }
  
  public static String GetAdvertisingID()
  {
    return ms_sAdvertisingIdentifier;
  }
  
  public static int GetApiLevel()
  {
    return Build.VERSION.SDK_INT;
  }
  
  public static String GetBuildVersion()
  {
    try
    {
      Activity localActivity = CC_Activity.GetActivity();
      String str = localActivity.getPackageManager().getPackageInfo(localActivity.getPackageName(), 0).versionName;
      return str;
    }
    catch (Exception localException)
    {
      Log.e("cloudcellapi.util.GetInfo", "GetBuildVersion() error: " + localException.toString());
    }
    return "1.0.0";
  }
  
  public static String GetDeviceBrand()
  {
    return Build.BRAND;
  }
  
  public static String GetDeviceCountry()
  {
    try
    {
      String str = Locale.getDefault().getISO3Country();
      return str;
    }
    catch (Exception localException)
    {
      Log.e("cloudcellapi.util.GetInfo", "GetDeviceCountry() error: " + localException.toString());
    }
    return "";
  }
  
  public static String GetDeviceFirmwareVersion()
  {
    return Build.VERSION.RELEASE;
  }
  
  public static String GetDeviceLanguage()
  {
    return Locale.getDefault().getLanguage();
  }
  
  public static String GetDeviceMacAddress()
  {
    try
    {
      String str = ((WifiManager)CC_Activity.GetActivity().getSystemService("wifi")).getConnectionInfo().getMacAddress();
      return str;
    }
    catch (Exception localException)
    {
      Log.e("cloudcellapi.util.GetInfo", "GetDeviceMacAddress() error: " + localException.toString());
    }
    return "";
  }
  
  public static String GetDeviceManufacturer()
  {
    return Build.MANUFACTURER;
  }
  
  public static String GetDeviceModel()
  {
    return Build.MODEL;
  }
  
  public static String GetDeviceName()
  {
    return Build.MANUFACTURER + " " + Build.MODEL;
  }
  
  public static String GetDeviceUID()
  {
    return Settings.Secure.getString(CC_Activity.GetActivity().getApplicationContext().getContentResolver(), "android_id");
  }
  
  public static boolean GetIsAmazonDevice()
  {
    return GetDeviceManufacturer().toLowerCase().contains("amazon");
  }
  
  public static boolean GetIsTablet()
  {
    return (0xF & CC_Activity.GetActivity().getApplicationContext().getResources().getConfiguration().screenLayout) >= 3;
  }
  
  public static String GetLocalIp()
  {
    try
    {
      InetAddress localInetAddress;
      do
      {
        Enumeration localEnumeration1 = NetworkInterface.getNetworkInterfaces();
        Enumeration localEnumeration2;
        while (!localEnumeration2.hasMoreElements())
        {
          if (!localEnumeration1.hasMoreElements()) {
            break;
          }
          localEnumeration2 = ((NetworkInterface)localEnumeration1.nextElement()).getInetAddresses();
        }
        localInetAddress = (InetAddress)localEnumeration2.nextElement();
      } while ((localInetAddress.isLoopbackAddress()) || ((!InetAddressUtils.isIPv4Address(localInetAddress.getHostAddress().toString())) && (!InetAddressUtils.isIPv6Address(localInetAddress.getHostAddress().toString()))));
      String str = localInetAddress.getHostAddress().toString();
      return str;
    }
    catch (Exception localException)
    {
      Log.e("cloudcellapi.util.GetInfo", "GetLocalIp() error: " + localException.toString());
    }
    return "";
  }
  
  public static String GetPackageName()
  {
    try
    {
      String str = CC_Activity.GetActivity().getPackageName();
      return str;
    }
    catch (Exception localException)
    {
      Log.e("cloudcellapi.util.GetInfo", "GetPackageName() error: " + localException.toString());
    }
    return "";
  }
  
  public static void InitializeVolatile()
  {
    ms_bAdvertisingEnabled = true;
    new Thread(new Runnable()
    {
      public void run()
      {
        Log.d("cloudcellapi.util.GetInfo", "GetInfo.InitializeVolatile()");
        try
        {
          AdvertisingIdClient.Info localInfo = AdvertisingIdClient.getAdvertisingIdInfo(CC_Activity.GetActivity());
          GetInfo.ms_sAdvertisingIdentifier = localInfo.getId();
          GetInfo.ms_bAdvertisingEnabled = localInfo.isLimitAdTrackingEnabled();
          Log.d("cloudcellapi.util.GetInfo", "AdvertisingIdentifier: " + GetInfo.ms_sAdvertisingIdentifier);
          Log.d("cloudcellapi.util.GetInfo", "AdvertisingEnabled: " + GetInfo.ms_bAdvertisingEnabled);
          return;
        }
        catch (IOException localIOException)
        {
          for (;;)
          {
            Log.e("cloudcellapi.util.GetInfo", "IOException" + localIOException.getMessage());
          }
        }
        catch (GooglePlayServicesRepairableException localGooglePlayServicesRepairableException)
        {
          for (;;)
          {
            Log.e("cloudcellapi.util.GetInfo", "GooglePlayServicesRepairableException" + localGooglePlayServicesRepairableException.getMessage());
          }
        }
        catch (GooglePlayServicesNotAvailableException localGooglePlayServicesNotAvailableException)
        {
          for (;;)
          {
            Log.e("cloudcellapi.util.GetInfo", "GooglePlayServicesNotAvailableException" + localGooglePlayServicesNotAvailableException.getMessage());
          }
        }
        catch (IllegalStateException localIllegalStateException)
        {
          for (;;)
          {
            Log.e("cloudcellapi.util.GetInfo", "IllegalStateException" + localIllegalStateException.getMessage());
          }
        }
      }
    }).start();
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.firemonkeys.cloudcellapi.util.GetInfo
 * JD-Core Version:    0.7.0.1
 */