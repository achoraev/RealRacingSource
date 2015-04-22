package com.google.ads.interactivemedia.v3.b;

import com.google.ads.interactivemedia.v3.api.AdsManager;
import com.google.ads.interactivemedia.v3.api.AdsManagerLoadedEvent;

public class g
  implements AdsManagerLoadedEvent
{
  private final AdsManager a;
  private final Object b;
  
  g(AdsManager paramAdsManager, Object paramObject)
  {
    this.a = paramAdsManager;
    this.b = paramObject;
  }
  
  public AdsManager getAdsManager()
  {
    return this.a;
  }
  
  public Object getUserRequestContext()
  {
    return this.b;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.b.g
 * JD-Core Version:    0.7.0.1
 */