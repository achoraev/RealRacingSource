package com.popcap.ea2;

import android.app.Activity;
import android.util.Log;
import com.ultra.sdk.UltraManager;

public class Ultra
{
  private static final String TAG = "Ultra.java";
  
  public static void configure(Activity paramActivity, final String paramString1, final String paramString2)
  {
    paramActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        UltraManager.initUltra(this.val$activity, paramString1, paramString2);
        UltraManager.loadUltraCPVOrder(this.val$activity, paramString1, paramString2);
      }
    });
  }
  
  public static void reportDidShowCPV(boolean paramBoolean)
  {
    Log.d("Ultra.java", "reportDidShow: " + paramBoolean);
    UltraManager.reportProviderDidStartUltraCPV(paramBoolean);
  }
  
  public static void startCPV(Activity paramActivity, final UltraDelegate paramUltraDelegate)
  {
    Log.d("Ultra.java", "startCPV: " + paramUltraDelegate.toString());
    paramActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        Log.d("Ultra.java", "UltraManager.startUltraCPV: start");
        UltraManager.startUltraCPV(this.val$activity, paramUltraDelegate);
        Log.d("Ultra.java", "UltraManager.startUltraCPV: end");
      }
    });
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.popcap.ea2.Ultra
 * JD-Core Version:    0.7.0.1
 */