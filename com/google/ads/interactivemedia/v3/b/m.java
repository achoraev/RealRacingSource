package com.google.ads.interactivemedia.v3.b;

import com.google.ads.interactivemedia.v3.api.player.VideoAdPlayer.VideoAdPlayerCallback;

class m
  implements VideoAdPlayer.VideoAdPlayerCallback
{
  private v a;
  
  public m(v paramv)
  {
    this.a = paramv;
  }
  
  public void onEnded()
  {
    this.a.c();
  }
  
  public void onError()
  {
    this.a.c();
  }
  
  public void onPause()
  {
    this.a.c();
  }
  
  public void onPlay()
  {
    this.a.a();
  }
  
  public void onResume()
  {
    this.a.a();
  }
  
  public void onVolumeChanged(int paramInt) {}
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.b.m
 * JD-Core Version:    0.7.0.1
 */