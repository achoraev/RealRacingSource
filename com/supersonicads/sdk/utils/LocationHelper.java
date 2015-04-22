package com.supersonicads.sdk.utils;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class LocationHelper
{
  public static LocationHelper mInstance;
  private final String TAG = LocationHelper.class.getSimpleName();
  private Context mContext;
  private OnGetLocationListener mListener;
  private LocationListener mLocationListener;
  private LocationManager mLocationManager;
  
  private LocationHelper(Context paramContext)
  {
    this.mContext = paramContext.getApplicationContext();
    this.mLocationListener = new Listener(null);
  }
  
  /* Error */
  public static LocationHelper getInstance(Context paramContext)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 59	com/supersonicads/sdk/utils/LocationHelper:mInstance	Lcom/supersonicads/sdk/utils/LocationHelper;
    //   6: ifnonnull +17 -> 23
    //   9: new 2	com/supersonicads/sdk/utils/LocationHelper
    //   12: dup
    //   13: aload_0
    //   14: invokespecial 61	com/supersonicads/sdk/utils/LocationHelper:<init>	(Landroid/content/Context;)V
    //   17: astore_2
    //   18: ldc 2
    //   20: monitorexit
    //   21: aload_2
    //   22: areturn
    //   23: getstatic 59	com/supersonicads/sdk/utils/LocationHelper:mInstance	Lcom/supersonicads/sdk/utils/LocationHelper;
    //   26: astore_2
    //   27: goto -9 -> 18
    //   30: astore_1
    //   31: ldc 2
    //   33: monitorexit
    //   34: aload_1
    //   35: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	36	0	paramContext	Context
    //   30	5	1	localObject	Object
    //   17	10	2	localLocationHelper	LocationHelper
    // Exception table:
    //   from	to	target	type
    //   3	18	30	finally
    //   23	27	30	finally
  }
  
  private void removeUpdates()
  {
    this.mLocationManager.removeUpdates(this.mLocationListener);
  }
  
  public Location getLastKnownLocation()
  {
    return this.mLocationManager.getLastKnownLocation("network");
  }
  
  public void getLocation(OnGetLocationListener paramOnGetLocationListener)
  {
    this.mListener = paramOnGetLocationListener;
    this.mLocationManager = ((LocationManager)this.mContext.getSystemService("location"));
    try
    {
      this.mLocationManager.requestLocationUpdates("network", 0L, 0.0F, this.mLocationListener);
      return;
    }
    catch (Throwable localThrowable)
    {
      String str = "";
      if ((localThrowable != null) && (localThrowable.getMessage() != null)) {
        str = localThrowable.getMessage();
      }
      this.mListener.onGetLocationFail(str);
    }
  }
  
  private class Listener
    implements LocationListener
  {
    private Listener() {}
    
    public void onLocationChanged(Location paramLocation)
    {
      LocationHelper.this.mListener.onGetLocationSuccess(paramLocation.getLatitude(), paramLocation.getLongitude());
      Logger.i(LocationHelper.this.TAG, "onLocationChanged: " + paramLocation.getLatitude() + " " + paramLocation.getLongitude());
      LocationHelper.this.removeUpdates();
    }
    
    public void onProviderDisabled(String paramString)
    {
      Logger.i(LocationHelper.this.TAG, "onProviderDisabled");
      LocationHelper.this.mListener.onGetLocationFail("On Provider Disabled");
    }
    
    public void onProviderEnabled(String paramString)
    {
      Logger.i(LocationHelper.this.TAG, "onProviderEnabled");
    }
    
    public void onStatusChanged(String paramString, int paramInt, Bundle paramBundle)
    {
      Logger.i(LocationHelper.this.TAG, "onStatusChanged");
    }
  }
  
  public static abstract interface OnGetLocationListener
  {
    public abstract void onGetLocationFail(String paramString);
    
    public abstract void onGetLocationSuccess(double paramDouble1, double paramDouble2);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.supersonicads.sdk.utils.LocationHelper
 * JD-Core Version:    0.7.0.1
 */