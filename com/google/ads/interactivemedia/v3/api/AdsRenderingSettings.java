package com.google.ads.interactivemedia.v3.api;

import java.util.List;

public abstract interface AdsRenderingSettings
{
  public abstract int getBitrateKbps();
  
  public abstract List<String> getMimeTypes();
  
  public abstract void setBitrateKbps(int paramInt);
  
  public abstract void setMimeTypes(List<String> paramList);
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.api.AdsRenderingSettings
 * JD-Core Version:    0.7.0.1
 */