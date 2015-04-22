package com.firemonkeys.cloudcellapi;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsoluteLayout;
import android.widget.AbsoluteLayout.LayoutParams;
import android.widget.LinearLayout;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest.Builder;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
import com.google.android.gms.ads.mediation.admob.AdMobExtras;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class CC_GoogleAdManager_Class
{
  private static CC_GoogleAdManager_Class ms_pGoogleAdManager = null;
  Context mContext = null;
  HashMap<String, PublisherAdView> m_adViews;
  PublisherInterstitialAd m_interstitial = null;
  AdSize[] m_supportedAdSizes;
  
  public CC_GoogleAdManager_Class()
  {
    AdSize[] arrayOfAdSize = new AdSize[3];
    arrayOfAdSize[0] = AdSize.BANNER;
    arrayOfAdSize[1] = AdSize.FULL_BANNER;
    arrayOfAdSize[2] = AdSize.LEADERBOARD;
    this.m_supportedAdSizes = arrayOfAdSize;
    this.m_adViews = new HashMap();
    ms_pGoogleAdManager = this;
  }
  
  public static CC_GoogleAdManager_Class GetInstance()
  {
    return ms_pGoogleAdManager;
  }
  
  private native void OnBannerClicked(long paramLong, String paramString);
  
  private native void OnBannerClosedOverlay(long paramLong, String paramString);
  
  private native void OnBannerDisplayed(long paramLong, String paramString);
  
  private native void OnBannerFailed(long paramLong, String paramString);
  
  private native void OnBannerOpenedOverlay(long paramLong, String paramString);
  
  private native void OnInterstitialClicked(long paramLong);
  
  private native void OnInterstitialDismissed(long paramLong);
  
  private native void OnInterstitialDisplayed(long paramLong);
  
  private native void OnInterstitialFailed(long paramLong);
  
  public boolean AreAdsSupported()
  {
    return Build.VERSION.SDK_INT >= 16;
  }
  
  public void Constructor()
  {
    this.mContext = CC_Activity.GetActivity().getApplicationContext();
    ArrayList localArrayList = new ArrayList();
    for (AdSize localAdSize : this.m_supportedAdSizes) {
      if (IsSizeSupportedOnDevice(localAdSize)) {
        localArrayList.add(localAdSize);
      }
    }
    this.m_supportedAdSizes = ((AdSize[])localArrayList.toArray(new AdSize[0]));
  }
  
  public void DisplayBanner(final String paramString, final int paramInt1, final int paramInt2, final int paramInt3, final int paramInt4)
  {
    CC_Activity.GetActivity().runOnUiThread(new Runnable()
    {
      public void run()
      {
        PublisherAdView localPublisherAdView = new PublisherAdView(CC_Activity.GetActivity());
        AdSize[] arrayOfAdSize = new AdSize[1];
        arrayOfAdSize[0] = new AdSize(CC_GoogleAdManager_Class.this.PixToDip(paramInt3), CC_GoogleAdManager_Class.this.PixToDip(paramInt4));
        localPublisherAdView.setAdSizes(arrayOfAdSize);
        CC_GoogleAdManager_Class.this.LoadBanner(localPublisherAdView, null, paramString);
        AbsoluteLayout localAbsoluteLayout = new AbsoluteLayout(CC_GoogleAdManager_Class.this.mContext);
        localPublisherAdView.setLayoutParams(new AbsoluteLayout.LayoutParams(paramInt3, paramInt4, paramInt1, paramInt2));
        localAbsoluteLayout.addView(localPublisherAdView);
        CC_Activity.GetViewGroup().addView(localAbsoluteLayout);
      }
    });
  }
  
  public void DisplayBanner(final String paramString1, final String paramString2, final Bundle paramBundle, final long paramLong, boolean paramBoolean)
  {
    CC_Activity.GetActivity().runOnUiThread(new Runnable()
    {
      public void run()
      {
        CC_GoogleAdManager_Class.this.log("DisplayBanner Start: " + paramString1);
        if (CC_GoogleAdManager_Class.this.m_adViews.containsKey(paramString1))
        {
          CC_GoogleAdManager_Class.this.log("Banner already being displayed, returning");
          CC_GoogleAdManager_Class.this.OnBannerFailed(paramLong, paramString1);
          return;
        }
        if (!CC_GoogleAdManager_Class.this.AreAdsSupported())
        {
          CC_GoogleAdManager_Class.this.log("Banners are not supported on this device");
          CC_GoogleAdManager_Class.this.OnBannerFailed(paramLong, paramString1);
          return;
        }
        final PublisherAdView localPublisherAdView = new PublisherAdView(CC_Activity.GetActivity());
        localPublisherAdView.setAdListener(new AdListener()
        {
          public void onAdClosed()
          {
            CC_GoogleAdManager_Class.this.log("onAdClosed()");
            CC_GoogleAdManager_Class.this.OnBannerClosedOverlay(CC_GoogleAdManager_Class.2.this.val$nListener, CC_GoogleAdManager_Class.2.this.val$sBannerId);
          }
          
          public void onAdFailedToLoad(int paramAnonymous2Int)
          {
            CC_GoogleAdManager_Class.this.log("onAdFailedToLoad(" + CC_GoogleAdManager_Class.this.ResolveAdErrorCode(paramAnonymous2Int) + ")");
            CC_GoogleAdManager_Class.this.OnBannerFailed(CC_GoogleAdManager_Class.2.this.val$nListener, CC_GoogleAdManager_Class.2.this.val$sBannerId);
          }
          
          public void onAdLeftApplication()
          {
            CC_GoogleAdManager_Class.this.log("onAdLeftApplication()");
            CC_GoogleAdManager_Class.this.OnBannerClicked(CC_GoogleAdManager_Class.2.this.val$nListener, CC_GoogleAdManager_Class.2.this.val$sBannerId);
          }
          
          public void onAdLoaded()
          {
            CC_GoogleAdManager_Class.this.log("onAdLoaded()");
            LinearLayout localLinearLayout;
            if (localPublisherAdView.getParent() == null)
            {
              localLinearLayout = new LinearLayout(CC_GoogleAdManager_Class.this.mContext);
              localLinearLayout.addView(localPublisherAdView);
              if (!CC_GoogleAdManager_Class.2.this.val$bAnchorBottom) {
                break label100;
              }
            }
            label100:
            for (int i = 80;; i = 48)
            {
              localLinearLayout.setGravity(i | 0x1);
              CC_Activity.GetViewGroup().addView(localLinearLayout);
              CC_GoogleAdManager_Class.this.OnBannerDisplayed(CC_GoogleAdManager_Class.2.this.val$nListener, CC_GoogleAdManager_Class.2.this.val$sBannerId);
              return;
            }
          }
          
          public void onAdOpened()
          {
            CC_GoogleAdManager_Class.this.log("onAdOpened()");
            CC_GoogleAdManager_Class.this.OnBannerClicked(CC_GoogleAdManager_Class.2.this.val$nListener, CC_GoogleAdManager_Class.2.this.val$sBannerId);
            CC_GoogleAdManager_Class.this.OnBannerOpenedOverlay(CC_GoogleAdManager_Class.2.this.val$nListener, CC_GoogleAdManager_Class.2.this.val$sBannerId);
          }
        });
        CC_GoogleAdManager_Class.this.m_adViews.put(paramString1, localPublisherAdView);
        localPublisherAdView.setAdSizes(CC_GoogleAdManager_Class.this.m_supportedAdSizes);
        try
        {
          CC_GoogleAdManager_Class.this.LoadBanner(localPublisherAdView, paramString2, this.val$sUnitId);
          CC_GoogleAdManager_Class.this.log("DisplayBanner End: " + paramString1);
          return;
        }
        catch (Exception localException)
        {
          for (;;)
          {
            CC_GoogleAdManager_Class.this.log("Banner load exception: " + localException.toString());
            CC_GoogleAdManager_Class.this.OnInterstitialFailed(paramLong);
          }
        }
        catch (Throwable localThrowable)
        {
          for (;;)
          {
            CC_GoogleAdManager_Class.this.log("Banner load throwable: " + localThrowable.toString());
            CC_GoogleAdManager_Class.this.OnInterstitialFailed(paramLong);
          }
        }
      }
    });
  }
  
  public void DisplayInterstitial(final String paramString, Bundle paramBundle, final long paramLong)
  {
    CC_Activity.GetActivity().runOnUiThread(new Runnable()
    {
      public void run()
      {
        CC_GoogleAdManager_Class.this.log("Displaying Interstitial Start");
        if (!CC_GoogleAdManager_Class.this.AreAdsSupported())
        {
          CC_GoogleAdManager_Class.this.log("Interstitials are not supported on this device");
          CC_GoogleAdManager_Class.this.OnInterstitialFailed(paramLong);
        }
        while (CC_GoogleAdManager_Class.this.m_interstitial != null) {
          return;
        }
        Activity localActivity = CC_Activity.GetActivity();
        CC_GoogleAdManager_Class.this.m_interstitial = new PublisherInterstitialAd(localActivity);
        PublisherAdRequest.Builder localBuilder = new PublisherAdRequest.Builder();
        if (paramString != null) {
          localBuilder.addNetworkExtras(new AdMobExtras(paramString));
        }
        CC_GoogleAdManager_Class.this.m_interstitial.setAdUnitId(this.val$sUnitId);
        CC_GoogleAdManager_Class.this.m_interstitial.setAdListener(new AdListener()
        {
          public void onAdClosed()
          {
            CC_GoogleAdManager_Class.this.log("onAdClosed()");
            CC_GoogleAdManager_Class.this.m_interstitial = null;
            CC_GoogleAdManager_Class.this.OnInterstitialDismissed(CC_GoogleAdManager_Class.1.this.val$nListener);
          }
          
          public void onAdFailedToLoad(int paramAnonymous2Int)
          {
            CC_GoogleAdManager_Class.this.log("onAdFailedToLoad(" + CC_GoogleAdManager_Class.this.ResolveAdErrorCode(paramAnonymous2Int) + ")");
            CC_GoogleAdManager_Class.this.m_interstitial = null;
            CC_GoogleAdManager_Class.this.OnInterstitialFailed(CC_GoogleAdManager_Class.1.this.val$nListener);
          }
          
          public void onAdLeftApplication()
          {
            CC_GoogleAdManager_Class.this.log("onAdLeftApplication()");
            CC_GoogleAdManager_Class.this.OnInterstitialClicked(CC_GoogleAdManager_Class.1.this.val$nListener);
          }
          
          public void onAdLoaded()
          {
            CC_GoogleAdManager_Class.this.log("onAdLoaded()");
            CC_GoogleAdManager_Class.this.m_interstitial.show();
          }
          
          public void onAdOpened()
          {
            CC_GoogleAdManager_Class.this.log("onAdOpened()");
            CC_GoogleAdManager_Class.this.OnInterstitialDisplayed(CC_GoogleAdManager_Class.1.this.val$nListener);
          }
        });
        try
        {
          CC_GoogleAdManager_Class.this.m_interstitial.loadAd(localBuilder.build());
          CC_GoogleAdManager_Class.this.log("Displaying Interstitial End");
          return;
        }
        catch (Exception localException)
        {
          for (;;)
          {
            CC_GoogleAdManager_Class.this.log("Interstitial load exception: " + localException.toString());
            CC_GoogleAdManager_Class.this.OnInterstitialFailed(paramLong);
          }
        }
        catch (Throwable localThrowable)
        {
          for (;;)
          {
            CC_GoogleAdManager_Class.this.log("Interstitial load throwable: " + localThrowable.toString());
            CC_GoogleAdManager_Class.this.OnInterstitialFailed(paramLong);
          }
        }
      }
    });
  }
  
  public boolean IsSizeSupportedOnDevice(AdSize paramAdSize)
  {
    Display localDisplay = CC_Activity.GetActivity().getWindowManager().getDefaultDisplay();
    Point localPoint = new Point();
    localDisplay.getSize(localPoint);
    log("Checking ad size: " + paramAdSize.toString());
    log("Checking ad size against display: " + localPoint.toString());
    if ((localPoint.x >= paramAdSize.getWidthInPixels(this.mContext)) && (localPoint.y >= paramAdSize.getHeightInPixels(this.mContext))) {}
    for (boolean bool = true;; bool = false)
    {
      log("AdSupported: " + bool);
      return bool;
    }
  }
  
  protected void LoadBanner(PublisherAdView paramPublisherAdView, Bundle paramBundle, String paramString)
  {
    paramPublisherAdView.setAdUnitId(paramString);
    PublisherAdRequest.Builder localBuilder = new PublisherAdRequest.Builder();
    if (paramBundle != null) {
      localBuilder.addNetworkExtras(new AdMobExtras(paramBundle));
    }
    paramPublisherAdView.loadAd(localBuilder.build());
  }
  
  public int PixToDip(int paramInt)
  {
    float f = this.mContext.getResources().getDisplayMetrics().density;
    return (int)((paramInt - 0.5F) / f);
  }
  
  public void RemoveBanner(final String paramString)
  {
    CC_Activity.GetActivity().runOnUiThread(new Runnable()
    {
      public void run()
      {
        PublisherAdView localPublisherAdView = (PublisherAdView)CC_GoogleAdManager_Class.this.m_adViews.get(paramString);
        if (localPublisherAdView == null) {
          return;
        }
        CC_Activity.GetViewGroup().removeView(localPublisherAdView);
        CC_GoogleAdManager_Class.this.m_adViews.remove(paramString);
        localPublisherAdView.destroy();
      }
    });
  }
  
  public String ResolveAdErrorCode(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return "UNKNOWN";
    case 0: 
      return "ERROR_CODE_INTERNAL_ERROR";
    case 1: 
      return "ERROR_CODE_INVALID_REQUEST";
    case 2: 
      return "ERROR_CODE_NETWORK_ERROR";
    }
    return "ERROR_CODE_NO_FILL";
  }
  
  protected void log(String paramString)
  {
    Log.i("CC_ADS", paramString);
  }
  
  public void onDestroy()
  {
    log("onDestroy() start");
    Iterator localIterator = this.m_adViews.entrySet().iterator();
    while (localIterator.hasNext()) {
      ((PublisherAdView)((Map.Entry)localIterator.next()).getValue()).destroy();
    }
    log("onDestory() end");
  }
  
  public void onPause()
  {
    log("onPause() start");
    Iterator localIterator = this.m_adViews.entrySet().iterator();
    while (localIterator.hasNext()) {
      ((PublisherAdView)((Map.Entry)localIterator.next()).getValue()).pause();
    }
    log("onPause() end");
  }
  
  public void onResume()
  {
    log("onResume() start");
    Iterator localIterator = this.m_adViews.entrySet().iterator();
    while (localIterator.hasNext()) {
      ((PublisherAdView)((Map.Entry)localIterator.next()).getValue()).resume();
    }
    log("onResume() end");
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.firemonkeys.cloudcellapi.CC_GoogleAdManager_Class
 * JD-Core Version:    0.7.0.1
 */