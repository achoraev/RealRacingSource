package com.supersonicads.sdk;

import android.content.Context;
import com.supersonicads.sdk.listeners.OnGenericFunctionListener;
import com.supersonicads.sdk.listeners.OnInterstitialListener;
import com.supersonicads.sdk.listeners.OnOfferWallListener;
import com.supersonicads.sdk.listeners.OnRewardedVideoListener;
import java.util.Map;

public abstract interface SSAPublisher
{
  public abstract void getOfferWallCredits(String paramString1, String paramString2, OnOfferWallListener paramOnOfferWallListener);
  
  public abstract String getSDKVersion();
  
  public abstract void initInterstitial(String paramString1, String paramString2, Map<String, String> paramMap, OnInterstitialListener paramOnInterstitialListener);
  
  public abstract void initRewardedVideo(String paramString1, String paramString2, Map<String, String> paramMap, OnRewardedVideoListener paramOnRewardedVideoListener);
  
  public abstract void loadInterstitial();
  
  public abstract void onPause(Context paramContext);
  
  public abstract void onResume(Context paramContext);
  
  public abstract void release(Context paramContext);
  
  public abstract void runGenericFunction(String paramString, Map<String, String> paramMap, OnGenericFunctionListener paramOnGenericFunctionListener);
  
  public abstract void showInterstitial();
  
  public abstract void showOfferWall(String paramString1, String paramString2, Map<String, String> paramMap, OnOfferWallListener paramOnOfferWallListener);
  
  public abstract void showRewardedVideo();
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.supersonicads.sdk.SSAPublisher
 * JD-Core Version:    0.7.0.1
 */