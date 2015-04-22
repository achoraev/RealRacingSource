package com.supersonicads.sdk.listeners;

public abstract interface OnInterstitialListener
{
  public abstract void onISAdClosed();
  
  public abstract void onISGeneric(String paramString1, String paramString2);
  
  public abstract void onISInitFail(String paramString);
  
  public abstract void onISInitSuccess();
  
  public abstract void onISLoaded();
  
  public abstract void onISLoadedFail(String paramString);
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.supersonicads.sdk.listeners.OnInterstitialListener
 * JD-Core Version:    0.7.0.1
 */