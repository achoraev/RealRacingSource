package com.google.ads.interactivemedia.v3.api;

public abstract interface Ad
{
  public abstract String getAdId();
  
  public abstract AdPodInfo getAdPodInfo();
  
  public abstract String getAdSystem();
  
  public abstract String[] getAdWrapperIds();
  
  public abstract String[] getAdWrapperSystems();
  
  public abstract double getDuration();
  
  public abstract int getHeight();
  
  public abstract String getTraffickingParameters();
  
  public abstract int getWidth();
  
  public abstract boolean isLinear();
  
  public abstract boolean isSkippable();
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.api.Ad
 * JD-Core Version:    0.7.0.1
 */