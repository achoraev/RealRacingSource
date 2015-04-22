package com.firemonkeys.cloudcellapi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.util.Log;

public class NetworkStatusMonitor
  extends BroadcastReceiver
{
  public static final int CONNECTION_TYPE_CARRIER = 4;
  public static final int CONNECTION_TYPE_ETHERNET = 6;
  public static final int CONNECTION_TYPE_NONE = 1;
  public static final int CONNECTION_TYPE_TRANSIENT_CARRIER = 5;
  public static final int CONNECTION_TYPE_TRANSIENT_WIFI = 3;
  public static final int CONNECTION_TYPE_UNKNOWN = 0;
  public static final int CONNECTION_TYPE_WIFI = 2;
  public static final int NUM_CONNECTION_TYPE = 7;
  
  public static native void ReachabilityCallbackJNI(int paramInt);
  
  public static int getMobileNetworkGeneration(Context paramContext)
  {
    switch (((TelephonyManager)paramContext.getSystemService("phone")).getNetworkType())
    {
    default: 
      return 0;
    case 1: 
    case 2: 
    case 4: 
    case 7: 
    case 11: 
      return 2;
    case 3: 
    case 5: 
    case 6: 
    case 8: 
    case 9: 
    case 10: 
    case 12: 
    case 14: 
      return 3;
    }
    return 4;
  }
  
  public static int getNetworkConnectivity(Context paramContext)
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    if ((localNetworkInfo != null) && (localNetworkInfo.isConnected()))
    {
      int i = localNetworkInfo.getType();
      if (i == 1)
      {
        Log.d("Cloudcell", "getNetworkConnectivity() - CONNECTION_TYPE_WIFI");
        return 2;
      }
      if (i == 9)
      {
        Log.d("Cloudcell", "getNetowkrConnectivity() - CONNECTION_TYPE_ETHERNET");
        return 6;
      }
      Log.d("Cloudcell", "getNetworkConnectivity() - CONNECTION_TYPE_CARRIER");
      return 4;
    }
    Log.d("Cloudcell", "getNetworkConnectivity() - CONNECTION_TYPE_NONE");
    return 1;
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    try
    {
      ReachabilityCallbackJNI(getNetworkConnectivity(paramContext));
      return;
    }
    catch (UnsatisfiedLinkError localUnsatisfiedLinkError) {}
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.firemonkeys.cloudcellapi.NetworkStatusMonitor
 * JD-Core Version:    0.7.0.1
 */