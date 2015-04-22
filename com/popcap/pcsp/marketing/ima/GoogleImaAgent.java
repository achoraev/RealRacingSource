package com.popcap.pcsp.marketing.ima;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.google.ads.interactivemedia.v3.api.AdDisplayContainer;
import com.google.ads.interactivemedia.v3.api.AdError;
import com.google.ads.interactivemedia.v3.api.AdErrorEvent;
import com.google.ads.interactivemedia.v3.api.AdErrorEvent.AdErrorListener;
import com.google.ads.interactivemedia.v3.api.AdEvent;
import com.google.ads.interactivemedia.v3.api.AdEvent.AdEventListener;
import com.google.ads.interactivemedia.v3.api.AdEvent.AdEventType;
import com.google.ads.interactivemedia.v3.api.AdsLoader;
import com.google.ads.interactivemedia.v3.api.AdsLoader.AdsLoadedListener;
import com.google.ads.interactivemedia.v3.api.AdsManager;
import com.google.ads.interactivemedia.v3.api.AdsManagerLoadedEvent;
import com.google.ads.interactivemedia.v3.api.AdsRenderingSettings;
import com.google.ads.interactivemedia.v3.api.AdsRequest;
import com.google.ads.interactivemedia.v3.api.ImaSdkFactory;
import java.util.Arrays;

public class GoogleImaAgent
  implements AdErrorEvent.AdErrorListener, AdsLoader.AdsLoadedListener, AdEvent.AdEventListener, TrackingVideoView.CompleteCallback
{
  private static final String TAG = "GoogleImaAgent";
  private static GoogleImaAgent sInstance;
  private Activity mAdActivity;
  private AdsLoader mAdLoader;
  private AdsManager mAdsManager;
  private boolean mClicked = false;
  private AdDisplayContainer mContainer;
  private boolean mIsLoaded = false;
  private boolean mIsPaused = false;
  private boolean mIsShowing = false;
  private LoadAdCallback mLoadAdCallback;
  private ShowAdCallback mShowAdCallback;
  private StandaloneVideoAdPlayer mVideoPlayer;
  
  public static GoogleImaAgent getInstance()
  {
    if (sInstance == null) {
      sInstance = new GoogleImaAgent();
    }
    return sInstance;
  }
  
  public void attachActivity(Activity paramActivity)
  {
    this.mAdActivity = paramActivity;
  }
  
  protected void closeAd()
  {
    if (this.mAdLoader != null)
    {
      this.mAdLoader.contentComplete();
      this.mAdLoader = null;
    }
    if (this.mVideoPlayer != null) {
      this.mVideoPlayer = null;
    }
    if (this.mContainer != null) {
      this.mContainer = null;
    }
    if (this.mAdsManager != null)
    {
      this.mAdsManager.destroy();
      this.mAdsManager = null;
    }
    if (this.mAdActivity != null)
    {
      this.mAdActivity.finish();
      this.mAdActivity = null;
    }
    this.mIsShowing = false;
    this.mIsLoaded = false;
    this.mIsPaused = false;
  }
  
  public void detachActivity()
  {
    this.mAdActivity = null;
  }
  
  public StandaloneVideoAdPlayer getPlayer()
  {
    return this.mVideoPlayer;
  }
  
  public void loadAd(Context paramContext, String paramString)
  {
    Log.d("GoogleImaAgent", "Loading interactive ad");
    this.mAdLoader = ImaSdkFactory.getInstance().createAdsLoader(paramContext);
    this.mAdLoader.addAdErrorListener(this);
    this.mAdLoader.addAdsLoadedListener(this);
    this.mVideoPlayer = new StandaloneVideoAdPlayer(paramContext);
    this.mVideoPlayer.setCompletionCallback(this);
    this.mVideoPlayer.setBackgroundColor(-16777216);
    this.mContainer = ImaSdkFactory.getInstance().createAdDisplayContainer();
    this.mContainer.setPlayer(this.mVideoPlayer);
    this.mContainer.setAdContainer(this.mVideoPlayer.getUiContainer());
    AdsRequest localAdsRequest = ImaSdkFactory.getInstance().createAdsRequest();
    localAdsRequest.setAdTagUrl(paramString);
    localAdsRequest.setAdDisplayContainer(this.mContainer);
    this.mAdLoader.requestAds(localAdsRequest);
  }
  
  public void onAdError(AdErrorEvent paramAdErrorEvent)
  {
    Log.e("GoogleImaAgent", "Error loading interactive ad: " + paramAdErrorEvent.getError().getMessage());
    closeAd();
    if (this.mLoadAdCallback != null) {
      this.mLoadAdCallback.onError(paramAdErrorEvent.getError());
    }
  }
  
  public void onAdEvent(AdEvent paramAdEvent)
  {
    Log.d("GoogleImaAgent", "EVENT: " + paramAdEvent.getType().toString());
    switch (1.$SwitchMap$com$google$ads$interactivemedia$v3$api$AdEvent$AdEventType[paramAdEvent.getType().ordinal()])
    {
    default: 
      Log.w("GoogleImaAgent", "Unknown ad event: " + paramAdEvent.getType().toString());
    case 2: 
    case 3: 
    case 4: 
    case 9: 
    case 1: 
    case 5: 
      do
      {
        do
        {
          return;
          Log.d("GoogleImaAgent", "Ad event: loaded");
          this.mIsLoaded = true;
        } while (this.mLoadAdCallback == null);
        this.mLoadAdCallback.onLoaded();
        return;
        closeAd();
      } while (this.mShowAdCallback == null);
      this.mShowAdCallback.onFinished(this.mClicked);
      return;
    case 6: 
      Log.d("GoogleImaAgent", "Ad event: complete");
      return;
    case 7: 
      this.mIsPaused = true;
      return;
    case 8: 
      this.mIsPaused = false;
      return;
    }
    this.mClicked = true;
  }
  
  public void onAdsManagerLoaded(AdsManagerLoadedEvent paramAdsManagerLoadedEvent)
  {
    Log.d("GoogleImaAgent", "Finished loading interactive ad");
    this.mAdsManager = paramAdsManagerLoadedEvent.getAdsManager();
    this.mAdsManager.addAdErrorListener(this);
    this.mAdsManager.addAdEventListener(this);
    AdsRenderingSettings localAdsRenderingSettings = ImaSdkFactory.getInstance().createAdsRenderingSettings();
    localAdsRenderingSettings.setMimeTypes(Arrays.asList(new String[] { "video/mp4" }));
    localAdsRenderingSettings.setBitrateKbps(1024);
    this.mAdsManager.init(localAdsRenderingSettings);
  }
  
  public void onComplete()
  {
    this.mAdLoader.contentComplete();
  }
  
  public void pause()
  {
    if (this.mVideoPlayer != null)
    {
      this.mVideoPlayer.savePosition();
      if (!this.mIsPaused) {
        this.mVideoPlayer.pauseAd();
      }
    }
  }
  
  public void resume()
  {
    if (this.mVideoPlayer != null)
    {
      this.mVideoPlayer.restorePosition();
      if (this.mIsPaused) {
        this.mVideoPlayer.play();
      }
    }
  }
  
  public void setLoadAdCallback(LoadAdCallback paramLoadAdCallback)
  {
    this.mLoadAdCallback = paramLoadAdCallback;
  }
  
  public void setShowAdCallback(ShowAdCallback paramShowAdCallback)
  {
    this.mShowAdCallback = paramShowAdCallback;
  }
  
  public void showAd(Context paramContext)
  {
    if (!this.mIsLoaded)
    {
      Log.e("GoogleImaAgent", "Unable to show ad: no ad has been loaded");
      return;
    }
    if (this.mIsShowing)
    {
      Log.e("GoogleImaAgent", "Unable to show ad: already showing an ad");
      return;
    }
    Intent localIntent = new Intent(paramContext, GoogleImaActivity.class);
    localIntent.addFlags(524288);
    paramContext.startActivity(localIntent);
  }
  
  public boolean start()
  {
    if (this.mAdsManager == null)
    {
      Log.e("GoogleImaAgent", "Unable to start ad, ads manager not initialized");
      return false;
    }
    this.mAdsManager.start();
    return true;
  }
  
  public static abstract interface LoadAdCallback
  {
    public abstract void onError(Throwable paramThrowable);
    
    public abstract void onLoaded();
  }
  
  public static abstract interface ShowAdCallback
  {
    public abstract void onError(Throwable paramThrowable);
    
    public abstract void onFinished(boolean paramBoolean);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.popcap.pcsp.marketing.ima.GoogleImaAgent
 * JD-Core Version:    0.7.0.1
 */