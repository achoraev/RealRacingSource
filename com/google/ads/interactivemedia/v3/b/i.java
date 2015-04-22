package com.google.ads.interactivemedia.v3.b;

import com.google.ads.interactivemedia.v3.a.a.b;
import com.google.ads.interactivemedia.v3.api.AdsRenderingSettings;
import java.util.List;

public class i
  implements AdsRenderingSettings
{
  @b(a="bitrate")
  private int a = -1;
  @b(a="mimeTypes")
  private List<String> b = null;
  
  public int getBitrateKbps()
  {
    return this.a;
  }
  
  public List<String> getMimeTypes()
  {
    return this.b;
  }
  
  public void setBitrateKbps(int paramInt)
  {
    this.a = paramInt;
  }
  
  public void setMimeTypes(List<String> paramList)
  {
    this.b = paramList;
  }
  
  public String toString()
  {
    return "AdsRenderingSettings [bitrate=" + this.a + ", mimeTypes=" + this.b + "]";
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.b.i
 * JD-Core Version:    0.7.0.1
 */