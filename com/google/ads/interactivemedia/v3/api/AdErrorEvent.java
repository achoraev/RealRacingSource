package com.google.ads.interactivemedia.v3.api;

public abstract interface AdErrorEvent
{
  public abstract AdError getError();
  
  public abstract Object getUserRequestContext();
  
  public static abstract interface AdErrorListener
  {
    public abstract void onAdError(AdErrorEvent paramAdErrorEvent);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.api.AdErrorEvent
 * JD-Core Version:    0.7.0.1
 */