package com.popcap.pcsp.marketing;

import android.app.Activity;
import android.util.Log;
import com.popcap.pcsp.marketing.ima.GoogleImaAgent;
import com.popcap.pcsp.marketing.ima.GoogleImaAgent.LoadAdCallback;
import com.popcap.pcsp.marketing.ima.GoogleImaAgent.ShowAdCallback;

public class GoogleImaDriver
{
  private static final String TAG = "GoogleImaDriver";
  private Activity mActivity;
  private Long mDriver;
  
  public GoogleImaDriver(Activity paramActivity)
  {
    this.mActivity = paramActivity;
    this.mDriver = null;
  }
  
  public GoogleImaDriver(Activity paramActivity, long paramLong)
  {
    this.mActivity = paramActivity;
    this.mDriver = Long.valueOf(paramLong);
    setLoadAdCallback(new GoogleImaAgent.LoadAdCallback()
    {
      public void onError(Throwable paramAnonymousThrowable)
      {
        GoogleImaDriver.this.notifyAdLoaded(GoogleImaDriver.this.mDriver.longValue(), false);
      }
      
      public void onLoaded()
      {
        GoogleImaDriver.this.notifyAdLoaded(GoogleImaDriver.this.mDriver.longValue(), true);
      }
    });
    setShowAdCallback(new GoogleImaAgent.ShowAdCallback()
    {
      public void onError(Throwable paramAnonymousThrowable)
      {
        GoogleImaDriver.this.notifyAdClosed(GoogleImaDriver.this.mDriver.longValue());
      }
      
      public void onFinished(boolean paramAnonymousBoolean)
      {
        if (paramAnonymousBoolean) {
          GoogleImaDriver.this.notifyAdClicked(GoogleImaDriver.this.mDriver.longValue());
        }
        GoogleImaDriver.this.notifyAdClosed(GoogleImaDriver.this.mDriver.longValue());
      }
    });
  }
  
  private native void notifyAdClicked(long paramLong);
  
  private native void notifyAdClosed(long paramLong);
  
  private native void notifyAdLoaded(long paramLong, boolean paramBoolean);
  
  public void LoadAd(final String paramString)
  {
    Log.i("GoogleImaDriver", "Loading interactive ad");
    this.mActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        GoogleImaAgent.getInstance().loadAd(GoogleImaDriver.this.mActivity, paramString);
      }
    });
  }
  
  public boolean ShowAd()
  {
    Log.d("GoogleImaDriver", "Starting interactive ad");
    this.mActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        GoogleImaAgent.getInstance().showAd(GoogleImaDriver.this.mActivity);
      }
    });
    return true;
  }
  
  public void setLoadAdCallback(GoogleImaAgent.LoadAdCallback paramLoadAdCallback)
  {
    GoogleImaAgent.getInstance().setLoadAdCallback(paramLoadAdCallback);
  }
  
  public void setShowAdCallback(GoogleImaAgent.ShowAdCallback paramShowAdCallback)
  {
    GoogleImaAgent.getInstance().setShowAdCallback(paramShowAdCallback);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.popcap.pcsp.marketing.GoogleImaDriver
 * JD-Core Version:    0.7.0.1
 */