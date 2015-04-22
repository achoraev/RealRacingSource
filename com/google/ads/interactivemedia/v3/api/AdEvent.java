package com.google.ads.interactivemedia.v3.api;

import java.util.Map;

public abstract interface AdEvent
{
  public abstract Ad getAd();
  
  public abstract Map<String, String> getAdData();
  
  public abstract AdEventType getType();
  
  public static abstract interface AdEventListener
  {
    public abstract void onAdEvent(AdEvent paramAdEvent);
  }
  
  public static enum AdEventType
  {
    static
    {
      LOADED = new AdEventType("LOADED", 13);
      AdEventType[] arrayOfAdEventType = new AdEventType[14];
      arrayOfAdEventType[0] = ALL_ADS_COMPLETED;
      arrayOfAdEventType[1] = CLICKED;
      arrayOfAdEventType[2] = COMPLETED;
      arrayOfAdEventType[3] = CONTENT_PAUSE_REQUESTED;
      arrayOfAdEventType[4] = CONTENT_RESUME_REQUESTED;
      arrayOfAdEventType[5] = FIRST_QUARTILE;
      arrayOfAdEventType[6] = LOG;
      arrayOfAdEventType[7] = MIDPOINT;
      arrayOfAdEventType[8] = PAUSED;
      arrayOfAdEventType[9] = RESUMED;
      arrayOfAdEventType[10] = SKIPPED;
      arrayOfAdEventType[11] = STARTED;
      arrayOfAdEventType[12] = THIRD_QUARTILE;
      arrayOfAdEventType[13] = LOADED;
      a = arrayOfAdEventType;
    }
    
    private AdEventType() {}
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.api.AdEvent
 * JD-Core Version:    0.7.0.1
 */