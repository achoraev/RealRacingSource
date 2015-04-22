package com.supersonicads.sdk.listeners;

public abstract interface OnOfferWallListener
{
  public abstract void onGetOWCreditsFailed(String paramString);
  
  public abstract void onOWAdClosed();
  
  public abstract boolean onOWAdCredited(int paramInt1, int paramInt2, boolean paramBoolean);
  
  public abstract void onOWGeneric(String paramString1, String paramString2);
  
  public abstract void onOWShowFail(String paramString);
  
  public abstract void onOWShowSuccess();
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.supersonicads.sdk.listeners.OnOfferWallListener
 * JD-Core Version:    0.7.0.1
 */