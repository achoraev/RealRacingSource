package com.google.ads.interactivemedia.v3.api;

import java.util.List;

public abstract interface AdsManager
{
  public abstract void addAdErrorListener(AdErrorEvent.AdErrorListener paramAdErrorListener);
  
  public abstract void addAdEventListener(AdEvent.AdEventListener paramAdEventListener);
  
  public abstract void destroy();
  
  public abstract List<Float> getAdCuePoints();
  
  public abstract Ad getCurrentAd();
  
  public abstract void init();
  
  public abstract void init(AdsRenderingSettings paramAdsRenderingSettings);
  
  public abstract void removeAdErrorListener(AdErrorEvent.AdErrorListener paramAdErrorListener);
  
  public abstract void removeAdEventListener(AdEvent.AdEventListener paramAdEventListener);
  
  public abstract void skip();
  
  public abstract void start();
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.api.AdsManager
 * JD-Core Version:    0.7.0.1
 */