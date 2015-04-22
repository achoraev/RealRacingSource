package com.google.ads.interactivemedia.v3.api;

import java.util.Map;

public abstract interface AdsRequest
{
  public abstract AdDisplayContainer getAdDisplayContainer();
  
  public abstract String getAdTagUrl();
  
  public abstract String getAdsResponse();
  
  public abstract String getExtraParameter(String paramString);
  
  public abstract Map<String, String> getExtraParameters();
  
  public abstract Object getUserRequestContext();
  
  public abstract void setAdDisplayContainer(AdDisplayContainer paramAdDisplayContainer);
  
  public abstract void setAdTagUrl(String paramString);
  
  public abstract void setAdsResponse(String paramString);
  
  public abstract void setExtraParameter(String paramString1, String paramString2);
  
  public abstract void setUserRequestContext(Object paramObject);
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.api.AdsRequest
 * JD-Core Version:    0.7.0.1
 */