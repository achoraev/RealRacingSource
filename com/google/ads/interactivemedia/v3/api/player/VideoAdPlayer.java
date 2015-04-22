package com.google.ads.interactivemedia.v3.api.player;

public abstract interface VideoAdPlayer
{
  public abstract void addCallback(VideoAdPlayerCallback paramVideoAdPlayerCallback);
  
  public abstract VideoProgressUpdate getProgress();
  
  public abstract void loadAd(String paramString);
  
  public abstract void pauseAd();
  
  public abstract void playAd();
  
  public abstract void removeCallback(VideoAdPlayerCallback paramVideoAdPlayerCallback);
  
  public abstract void resumeAd();
  
  public abstract void stopAd();
  
  public static abstract interface VideoAdPlayerCallback
  {
    public abstract void onEnded();
    
    public abstract void onError();
    
    public abstract void onPause();
    
    public abstract void onPlay();
    
    public abstract void onResume();
    
    public abstract void onVolumeChanged(int paramInt);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.api.player.VideoAdPlayer
 * JD-Core Version:    0.7.0.1
 */