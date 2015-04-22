package com.jirbo.adcolony;

public class AdColonyVideoAd
  extends AdColonyInterstitialAd
{
  public AdColonyVideoAd() {}
  
  public AdColonyVideoAd(String paramString)
  {
    super(paramString);
  }
  
  public AdColonyVideoAd withListener(AdColonyAdListener paramAdColonyAdListener)
  {
    this.listener = paramAdColonyAdListener;
    return this;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.jirbo.adcolony.AdColonyVideoAd
 * JD-Core Version:    0.7.0.1
 */