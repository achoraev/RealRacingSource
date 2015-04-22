package com.popcap.ea2;

import android.app.Activity;
import android.util.Log;
import com.supersonicads.sdk.SSAFactory;
import com.supersonicads.sdk.SSAPublisher;
import com.supersonicads.sdk.data.AdUnitsReady;
import com.supersonicads.sdk.listeners.OnOfferWallListener;
import com.supersonicads.sdk.listeners.OnRewardedVideoListener;
import java.util.HashMap;
import java.util.Map;

public class BrandConnectAdProvider
{
  static final String TAG = "BrandConnectAdProvider";
  public static BrandConnectAdProvider mProvider = null;
  private static SSAPublisher sSupersonicAdsAgent;
  private String mAdminUserId;
  private String mApplicationId;
  private long mNativePtr = 0L;
  private boolean mOfferwallShowing;
  private boolean mUseDemoCampaigns;
  private String mUserId;
  
  public BrandConnectAdProvider(long paramLong, Activity paramActivity, String paramString1, String paramString2, boolean paramBoolean)
  {
    if (paramActivity == null) {
      throw new IllegalArgumentException("Context can not be null");
    }
    this.mNativePtr = paramLong;
    this.mApplicationId = paramString1;
    this.mAdminUserId = paramString2;
    this.mUseDemoCampaigns = paramBoolean;
    this.mOfferwallShowing = false;
    mProvider = this;
  }
  
  public static void applicationCreate(Activity paramActivity)
  {
    paramActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        BrandConnectAdProvider.access$302(SSAFactory.getPublisherInstance(this.val$activity));
      }
    });
  }
  
  public static void applicationDestroy(Activity paramActivity)
  {
    if (sSupersonicAdsAgent != null) {
      sSupersonicAdsAgent.release(paramActivity);
    }
  }
  
  public static void applicationPause(Activity paramActivity)
  {
    if (sSupersonicAdsAgent != null) {
      sSupersonicAdsAgent.onPause(paramActivity);
    }
  }
  
  public static void applicationRestart(Activity paramActivity)
  {
    if (sSupersonicAdsAgent != null) {}
  }
  
  public static void applicationResume(Activity paramActivity)
  {
    if (sSupersonicAdsAgent != null) {
      sSupersonicAdsAgent.onResume(paramActivity);
    }
  }
  
  public static void applicationStop(Activity paramActivity)
  {
    if (sSupersonicAdsAgent != null) {}
  }
  
  private native void nativeAdClosed(long paramLong, boolean paramBoolean);
  
  private native void nativeAwardCoins(long paramLong, int paramInt1, int paramInt2);
  
  private native void nativeOWAwardCoins(long paramLong, int paramInt1, int paramInt2);
  
  private native void nativeOWClosed(long paramLong, boolean paramBoolean);
  
  private native void nativeSetAdsAvailable(long paramLong, boolean paramBoolean);
  
  public void initialize(Activity paramActivity, final String paramString)
  {
    if (paramActivity == null) {
      throw new IllegalArgumentException("Context can not be null");
    }
    if (sSupersonicAdsAgent == null) {
      throw new IllegalArgumentException("agent cannot be null");
    }
    this.mUserId = paramString;
    paramActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        Log.d("BrandConnectAdProvider", "initialize - start");
        String str = paramString;
        HashMap localHashMap = new HashMap();
        localHashMap.put("maxVideoLength", "180");
        if (BrandConnectAdProvider.this.mUseDemoCampaigns)
        {
          str = BrandConnectAdProvider.this.mAdminUserId;
          localHashMap.put("demoCampaigns", "1");
        }
        BrandConnectAdProvider.sSupersonicAdsAgent.initRewardedVideo(BrandConnectAdProvider.this.mApplicationId, str, localHashMap, new OnRewardedVideoListener()
        {
          public void onRVAdClosed()
          {
            Log.v("BrandConnectAdProvider", "Ads closed");
          }
          
          public void onRVAdCredited(int paramAnonymous2Int)
          {
            Log.d("BrandConnectAdProvider", "Ad finished; " + paramAnonymous2Int + " credits received");
            BrandConnectAdProvider.this.onAwardCoins(paramAnonymous2Int, 1);
            BrandConnectAdProvider.this.onAdClosed(false);
          }
          
          public void onRVGeneric(String paramAnonymous2String1, String paramAnonymous2String2) {}
          
          public void onRVInitFail(String paramAnonymous2String)
          {
            Log.w("BrandConnectAdProvider", "Unable to initialize brand connect: " + paramAnonymous2String);
            BrandConnectAdProvider.this.setAdsAvailable(false);
            BrandConnectAdProvider.this.onAdClosed(true);
          }
          
          public void onRVInitSuccess(AdUnitsReady paramAnonymous2AdUnitsReady)
          {
            Log.d("BrandConnectAdProvider", "BrandConnect initialization complete: " + paramAnonymous2AdUnitsReady.getNumOfAdUnits() + " ads available");
            BrandConnectAdProvider localBrandConnectAdProvider = BrandConnectAdProvider.this;
            if (paramAnonymous2AdUnitsReady.getNumOfAdUnits() != null) {}
            for (boolean bool = true;; bool = false)
            {
              localBrandConnectAdProvider.setAdsAvailable(bool);
              return;
            }
          }
          
          public void onRVNoMoreOffers()
          {
            Log.v("BrandConnectAdProvider", "No more ads available");
            BrandConnectAdProvider.this.setAdsAvailable(false);
          }
        });
        Log.d("BrandConnectAdProvider", "initialize - end");
      }
    });
  }
  
  protected void onAdClosed(boolean paramBoolean)
  {
    nativeAdClosed(this.mNativePtr, paramBoolean);
  }
  
  protected void onAwardCoins(int paramInt1, int paramInt2)
  {
    nativeAwardCoins(this.mNativePtr, paramInt1, paramInt2);
  }
  
  protected void onOWAwardCoins(int paramInt1, int paramInt2)
  {
    nativeOWAwardCoins(this.mNativePtr, paramInt1, paramInt2);
  }
  
  protected void onOWClosed(boolean paramBoolean)
  {
    Log.d("BrandConnectAdProvider", "OW Closed");
    this.mOfferwallShowing = false;
    nativeOWClosed(this.mNativePtr, paramBoolean);
  }
  
  protected void onOWOpened()
  {
    this.mOfferwallShowing = true;
  }
  
  protected void setAdsAvailable(boolean paramBoolean)
  {
    nativeSetAdsAvailable(this.mNativePtr, paramBoolean);
  }
  
  public void showAdvertisements(Activity paramActivity)
  {
    Log.d("BrandConnectAdProvider", "showAdvertisements");
    if (paramActivity == null) {
      throw new IllegalArgumentException("Context can not be null");
    }
    paramActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        BrandConnectAdProvider.sSupersonicAdsAgent.showRewardedVideo();
      }
    });
  }
  
  public void showOfferwall(Activity paramActivity, final String paramString)
  {
    Log.d("BrandConnectAdProvider", "showOfferwall");
    if (paramActivity == null) {
      throw new IllegalArgumentException("Context can not be null");
    }
    paramActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        HashMap localHashMap = new HashMap();
        localHashMap.put("useClientSideCallbacks", "true");
        BrandConnectAdProvider.sSupersonicAdsAgent.showOfferWall(BrandConnectAdProvider.this.mApplicationId, paramString, localHashMap, new OnOfferWallListener()
        {
          public void onGetOWCreditsFailed(String paramAnonymous2String)
          {
            Log.e("BrandConnectAdProvider", "OW Credit Failure: " + paramAnonymous2String);
          }
          
          public void onOWAdClosed()
          {
            Log.d("BrandConnectAdProvider", "OW Closed");
            BrandConnectAdProvider.this.onOWClosed(false);
          }
          
          public boolean onOWAdCredited(int paramAnonymous2Int1, int paramAnonymous2Int2, boolean paramAnonymous2Boolean)
          {
            Log.d("BrandConnectAdProvider", "OW finished; " + paramAnonymous2Int1 + " credits received. total: " + paramAnonymous2Boolean + " " + paramAnonymous2Int2);
            BrandConnectAdProvider.this.onOWAwardCoins(paramAnonymous2Int1, paramAnonymous2Int2);
            return true;
          }
          
          public void onOWGeneric(String paramAnonymous2String1, String paramAnonymous2String2) {}
          
          public void onOWShowFail(String paramAnonymous2String)
          {
            Log.d("BrandConnectAdProvider", "OW Failed: " + paramAnonymous2String);
            BrandConnectAdProvider.this.onOWClosed(true);
          }
          
          public void onOWShowSuccess()
          {
            Log.d("BrandConnectAdProvider", "Showing OW");
            BrandConnectAdProvider.this.onOWOpened();
          }
        });
      }
    });
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.popcap.ea2.BrandConnectAdProvider
 * JD-Core Version:    0.7.0.1
 */