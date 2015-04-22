package com.firemint.realracing;

import android.util.Log;
import com.fiksu.asotracking.FiksuTrackingManager;

public class Fiksu
{
  public static final String LOG_TAG = "RealRacing3_FiksuTrackingManager";
  
  public static void LOGI(String paramString)
  {
    Log.i("RealRacing3_FiksuTrackingManager", paramString);
  }
  
  public static void initialize()
  {
    if (MainActivity.instance != null)
    {
      LOGI("initializing");
      FiksuTrackingManager.initialize(MainActivity.instance.getApplication());
      return;
    }
    LOGI("initializing failed, MainActivity.instance == null");
  }
  
  public static void updateClientID(String paramString)
  {
    if (MainActivity.instance != null)
    {
      Log.i("RealRacing3_FiksuTrackingManager", "updateClientID: ID: " + paramString);
      FiksuTrackingManager.setClientId(MainActivity.instance, paramString);
      return;
    }
    LOGI("updateClientID failed, MainActivity.instance == null");
  }
  
  public static void uploadPurchaseEvent(String paramString1, double paramDouble, String paramString2)
  {
    if (MainActivity.instance != null)
    {
      Log.i("RealRacing3_FiksuTrackingManager", "uploadPurchaseEvent: username: " + paramString1 + " price: " + paramDouble + " currency: " + paramString2);
      FiksuTrackingManager.uploadPurchaseEvent(MainActivity.instance, paramString1, paramDouble, paramString2);
      return;
    }
    LOGI("uploadPurchaseEvent failed, MainActivity.instance == null");
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.firemint.realracing.Fiksu
 * JD-Core Version:    0.7.0.1
 */