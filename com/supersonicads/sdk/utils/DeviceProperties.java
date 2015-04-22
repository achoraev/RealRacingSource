package com.supersonicads.sdk.utils;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import java.util.Map;
import java.util.TreeMap;

public class DeviceProperties
{
  private static DeviceProperties mInstance = null;
  private String mDeviceCarrier;
  private Map<String, String> mDeviceIds;
  private String mDeviceModel;
  private String mDeviceOem;
  private String mDeviceOsType;
  private int mDeviceOsVersion;
  private final String mSupersonicSdkVersion = "5.9";
  
  private DeviceProperties(Context paramContext)
  {
    init(paramContext);
  }
  
  public static DeviceProperties getInstance(Context paramContext)
  {
    if (mInstance == null) {
      mInstance = new DeviceProperties(paramContext);
    }
    return mInstance;
  }
  
  private void init(Context paramContext)
  {
    this.mDeviceOem = Build.MANUFACTURER;
    this.mDeviceModel = Build.MODEL;
    this.mDeviceOsType = "android";
    this.mDeviceOsVersion = Build.VERSION.SDK_INT;
    this.mDeviceIds = new TreeMap();
    this.mDeviceCarrier = ((TelephonyManager)paramContext.getSystemService("phone")).getNetworkOperatorName();
  }
  
  public static void release()
  {
    mInstance = null;
  }
  
  public String getDeviceCarrier()
  {
    return this.mDeviceCarrier;
  }
  
  public Map<String, String> getDeviceIds()
  {
    return this.mDeviceIds;
  }
  
  public String getDeviceModel()
  {
    return this.mDeviceModel;
  }
  
  public String getDeviceOem()
  {
    return this.mDeviceOem;
  }
  
  public String getDeviceOsType()
  {
    return this.mDeviceOsType;
  }
  
  public int getDeviceOsVersion()
  {
    return this.mDeviceOsVersion;
  }
  
  public String getSupersonicSdkVersion()
  {
    return "5.9";
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.supersonicads.sdk.utils.DeviceProperties
 * JD-Core Version:    0.7.0.1
 */