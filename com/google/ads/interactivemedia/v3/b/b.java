package com.google.ads.interactivemedia.v3.b;

import com.google.ads.interactivemedia.v3.api.AdError;
import com.google.ads.interactivemedia.v3.api.AdErrorEvent;

public class b
  implements AdErrorEvent
{
  private final AdError a;
  private final Object b;
  
  b(AdError paramAdError)
  {
    this.a = paramAdError;
    this.b = null;
  }
  
  b(AdError paramAdError, Object paramObject)
  {
    this.a = paramAdError;
    this.b = paramObject;
  }
  
  public AdError getError()
  {
    return this.a;
  }
  
  public Object getUserRequestContext()
  {
    return this.b;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.b.b
 * JD-Core Version:    0.7.0.1
 */