package com.popcap.ea2;

import android.app.Activity;
import android.util.Log;
import com.jirbo.adcolony.AdColony;
import com.jirbo.adcolony.AdColonyAd;
import com.jirbo.adcolony.AdColonyAdAvailabilityListener;
import com.jirbo.adcolony.AdColonyAdListener;
import com.jirbo.adcolony.AdColonyV4VCAd;
import com.jirbo.adcolony.AdColonyV4VCListener;
import com.jirbo.adcolony.AdColonyV4VCReward;

public class AdColonyAdProvider
{
  private static final String TAG = "AdColonyAdProvider.java";
  private static String sApplicationId;
  private static long sNativePtr;
  private static String sZoneId;
  
  public static void applicationPause() {}
  
  public static void applicationResume(Activity paramActivity)
  {
    AdColony.resume(paramActivity);
  }
  
  public static void configure(long paramLong, String paramString1, String paramString2)
  {
    sNativePtr = paramLong;
    sApplicationId = paramString1;
    sZoneId = paramString2;
  }
  
  public static void initialize(final Activity paramActivity, String paramString)
  {
    paramActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        AdColony.setCustomID(this.val$userId);
        Activity localActivity = paramActivity;
        String str = AdColonyAdProvider.sApplicationId;
        String[] arrayOfString = new String[1];
        arrayOfString[0] = AdColonyAdProvider.sZoneId;
        AdColony.configure(localActivity, "version:1.0, store:google", str, arrayOfString);
        AdColony.addV4VCListener(new AdColonyV4VCListener()
        {
          public void onAdColonyV4VCReward(AdColonyV4VCReward paramAnonymous2AdColonyV4VCReward)
          {
            if (paramAnonymous2AdColonyV4VCReward.success()) {
              AdColonyAdProvider.onAwardCoins(paramAnonymous2AdColonyV4VCReward.amount(), 1);
            }
          }
        });
        AdColony.addAdAvailabilityListener(new AdColonyAdAvailabilityListener()
        {
          public void onAdColonyAdAvailabilityChange(boolean paramAnonymous2Boolean, String paramAnonymous2String)
          {
            StringBuilder localStringBuilder = new StringBuilder().append("Zone availability changed: ").append(paramAnonymous2String).append(" is now ");
            if (paramAnonymous2Boolean) {}
            for (String str = "available";; str = "not available")
            {
              Log.d("AdColonyAdProvider.java", str);
              AdColonyAdProvider.onAdAvailabilityChanged(paramAnonymous2Boolean, paramAnonymous2String);
              return;
            }
          }
        });
      }
    });
  }
  
  private static native void nativeAdAvailabilityChange(long paramLong, boolean paramBoolean, String paramString);
  
  private static native void nativeAdClosed(long paramLong);
  
  private static native void nativeAwardCoins(long paramLong, int paramInt1, int paramInt2);
  
  protected static void onAdAvailabilityChanged(boolean paramBoolean, String paramString)
  {
    nativeAdAvailabilityChange(sNativePtr, paramBoolean, paramString);
  }
  
  protected static void onAdClosed()
  {
    nativeAdClosed(sNativePtr);
  }
  
  protected static void onAwardCoins(int paramInt1, int paramInt2)
  {
    nativeAwardCoins(sNativePtr, paramInt1, paramInt2);
  }
  
  public static void showAdvertisements(Activity paramActivity)
  {
    Log.d("AdColonyAdProvider.java", "showAdvertisements");
    paramActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        new AdColonyV4VCAd(AdColonyAdProvider.sZoneId).withListener(new AdColonyAdListener()
        {
          public void onAdColonyAdAttemptFinished(AdColonyAd paramAnonymous2AdColonyAd) {}
          
          public void onAdColonyAdStarted(AdColonyAd paramAnonymous2AdColonyAd) {}
        }).show();
      }
    });
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.popcap.ea2.AdColonyAdProvider
 * JD-Core Version:    0.7.0.1
 */