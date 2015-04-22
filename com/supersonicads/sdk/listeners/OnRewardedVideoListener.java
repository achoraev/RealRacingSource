package com.supersonicads.sdk.listeners;

import com.supersonicads.sdk.data.AdUnitsReady;

public abstract interface OnRewardedVideoListener
{
  public abstract void onRVAdClosed();
  
  public abstract void onRVAdCredited(int paramInt);
  
  public abstract void onRVGeneric(String paramString1, String paramString2);
  
  public abstract void onRVInitFail(String paramString);
  
  public abstract void onRVInitSuccess(AdUnitsReady paramAdUnitsReady);
  
  public abstract void onRVNoMoreOffers();
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.supersonicads.sdk.listeners.OnRewardedVideoListener
 * JD-Core Version:    0.7.0.1
 */