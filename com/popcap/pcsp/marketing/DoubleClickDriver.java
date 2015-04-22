package com.popcap.pcsp.marketing;

import android.app.Activity;
import android.provider.Settings.Secure;
import android.util.Log;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest.Builder;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
import com.millennialmedia.android.MMSDK;
import com.popcap.pcsp.PCSPUtilities;

public class DoubleClickDriver
  extends AdListener
{
  private static final String TAG = "DoubleClickDriver";
  protected Activity mActivity = null;
  private boolean mAgeVerified = false;
  private Long mDriver;
  protected PublisherInterstitialAd mInterstitialAd = null;
  private boolean mIsTestDevice = false;
  private AdState mState = AdState.Idle;
  private String mTestDeviceId = "";
  
  public DoubleClickDriver(Activity paramActivity)
  {
    this(paramActivity, 0L);
  }
  
  public DoubleClickDriver(Activity paramActivity, long paramLong)
  {
    this.mActivity = paramActivity;
    this.mDriver = Long.valueOf(paramLong);
    MMSDK.initialize(paramActivity);
  }
  
  private native void notifyAdClicked(long paramLong);
  
  private native void notifyAdClosed(long paramLong);
  
  private native void notifyAdLoaded(long paramLong, boolean paramBoolean);
  
  public boolean AreTestAdsEnabled()
  {
    return this.mIsTestDevice;
  }
  
  public void LoadAd(String paramString)
  {
    if ((this.mState != AdState.Idle) && (this.mState != AdState.Loaded))
    {
      Log.d("DoubleClickDriver", "Ignoring LoadAd request due to being in state " + this.mState.name());
      return;
    }
    Log.i("DoubleClickDriver", "Queueing LoadAd.");
    this.mState = AdState.Loading;
    this.mActivity.runOnUiThread(new AdLoader(paramString));
  }
  
  public void SetAgeVerified(boolean paramBoolean)
  {
    this.mAgeVerified = paramBoolean;
  }
  
  public void SetTestAdsEnabled(boolean paramBoolean)
  {
    this.mIsTestDevice = paramBoolean;
    this.mTestDeviceId = "";
    if (this.mIsTestDevice)
    {
      String str = Settings.Secure.getString(this.mActivity.getContentResolver(), "android_id");
      if (str != null) {
        this.mTestDeviceId = PCSPUtilities.GetMD5HexString(str);
      }
    }
  }
  
  public boolean ShowAd()
  {
    if (this.mState != AdState.Loaded)
    {
      Log.d("DoubleClickDriver", "Ignoring ShowAd request due to being in state " + this.mState.name());
      return false;
    }
    if (this.mInterstitialAd == null)
    {
      Log.e("DoubleClickDriver", "Attempting to show ad before it has been initialized.");
      return false;
    }
    Log.i("DoubleClickDriver", "Queueing ShowAd.");
    this.mState = AdState.ShowQueued;
    this.mActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        if (DoubleClickDriver.this.mInterstitialAd.isLoaded())
        {
          Log.d("DoubleClickDriver", "Showing ad.");
          DoubleClickDriver.this.mInterstitialAd.show();
          return;
        }
        Log.w("DoubleClickDriver", "Ad can't be shown as it has not finished loading.");
      }
    });
    return true;
  }
  
  public void onAdClosed()
  {
    Log.d("DoubleClickDriver", "onAdClosed");
    this.mState = AdState.Loaded;
    if (this.mDriver != null) {
      notifyAdClosed(this.mDriver.longValue());
    }
  }
  
  public void onAdFailedToLoad(int paramInt)
  {
    Log.d("DoubleClickDriver", "onAdFailedToLoad");
    this.mState = AdState.Idle;
    if (this.mDriver != null) {
      notifyAdLoaded(this.mDriver.longValue(), false);
    }
  }
  
  public void onAdLeftApplication()
  {
    Log.d("DoubleClickDriver", "onAdLeftApplication");
    if (this.mDriver != null) {
      notifyAdClicked(this.mDriver.longValue());
    }
  }
  
  public void onAdLoaded()
  {
    Log.d("DoubleClickDriver", "onAdLoaded");
    this.mState = AdState.Loaded;
    if (this.mDriver != null) {
      notifyAdLoaded(this.mDriver.longValue(), true);
    }
  }
  
  public void onAdOpened()
  {
    Log.d("DoubleClickDriver", "onAdOpened");
    this.mState = AdState.Showing;
  }
  
  class AdLoader
    implements Runnable
  {
    private String mAdUnitId;
    
    public AdLoader(String paramString)
    {
      this.mAdUnitId = paramString;
    }
    
    public void run()
    {
      DoubleClickDriver.this.mInterstitialAd = new PublisherInterstitialAd(DoubleClickDriver.this.mActivity);
      DoubleClickDriver.this.mInterstitialAd.setAdUnitId(this.mAdUnitId);
      DoubleClickDriver.this.mInterstitialAd.setAdListener(DoubleClickDriver.this);
      Log.i("DoubleClickDriver", "Loading ad: '" + this.mAdUnitId + "'");
      PublisherAdRequest.Builder localBuilder1 = new PublisherAdRequest.Builder();
      PublisherAdRequest.Builder localBuilder2;
      if (DoubleClickDriver.this.mIsTestDevice)
      {
        localBuilder2 = localBuilder1.addTestDevice(PublisherAdRequest.DEVICE_ID_EMULATOR);
        if (!DoubleClickDriver.this.mTestDeviceId.isEmpty()) {
          localBuilder2 = localBuilder2.addTestDevice(DoubleClickDriver.this.mTestDeviceId);
        }
        if (!DoubleClickDriver.this.mAgeVerified) {
          break label164;
        }
      }
      label164:
      for (localBuilder1 = localBuilder2.tagForChildDirectedTreatment(false);; localBuilder1 = localBuilder2.tagForChildDirectedTreatment(true))
      {
        DoubleClickDriver.this.mInterstitialAd.loadAd(localBuilder1.build());
        return;
      }
    }
  }
  
  private static enum AdState
  {
    static
    {
      Loaded = new AdState("Loaded", 2);
      ShowQueued = new AdState("ShowQueued", 3);
      Showing = new AdState("Showing", 4);
      AdState[] arrayOfAdState = new AdState[5];
      arrayOfAdState[0] = Idle;
      arrayOfAdState[1] = Loading;
      arrayOfAdState[2] = Loaded;
      arrayOfAdState[3] = ShowQueued;
      arrayOfAdState[4] = Showing;
      $VALUES = arrayOfAdState;
    }
    
    private AdState() {}
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.popcap.pcsp.marketing.DoubleClickDriver
 * JD-Core Version:    0.7.0.1
 */