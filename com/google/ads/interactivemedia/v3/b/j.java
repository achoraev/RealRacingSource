package com.google.ads.interactivemedia.v3.b;

import com.google.ads.interactivemedia.v3.api.AdDisplayContainer;
import com.google.ads.interactivemedia.v3.api.AdsRequest;
import java.util.HashMap;
import java.util.Map;

public class j
  implements AdsRequest
{
  private String a;
  private AdDisplayContainer b;
  private Map<String, String> c;
  private String d;
  private transient Object e;
  
  public AdDisplayContainer getAdDisplayContainer()
  {
    return this.b;
  }
  
  public String getAdTagUrl()
  {
    return this.a;
  }
  
  public String getAdsResponse()
  {
    return this.d;
  }
  
  public String getExtraParameter(String paramString)
  {
    if (this.c == null) {
      return null;
    }
    return (String)this.c.get(paramString);
  }
  
  public Map<String, String> getExtraParameters()
  {
    return this.c;
  }
  
  public Object getUserRequestContext()
  {
    return this.e;
  }
  
  public void setAdDisplayContainer(AdDisplayContainer paramAdDisplayContainer)
  {
    this.b = paramAdDisplayContainer;
  }
  
  public void setAdTagUrl(String paramString)
  {
    this.a = paramString;
  }
  
  public void setAdsResponse(String paramString)
  {
    this.d = paramString;
  }
  
  public void setExtraParameter(String paramString1, String paramString2)
  {
    if (this.c == null) {
      this.c = new HashMap();
    }
    this.c.put(paramString1, paramString2);
  }
  
  public void setUserRequestContext(Object paramObject)
  {
    this.e = paramObject;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.b.j
 * JD-Core Version:    0.7.0.1
 */