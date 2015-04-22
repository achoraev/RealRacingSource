package com.google.ads.interactivemedia.v3.b;

import com.google.ads.interactivemedia.v3.api.Ad;
import com.google.ads.interactivemedia.v3.api.AdEvent;
import com.google.ads.interactivemedia.v3.api.AdEvent.AdEventType;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class c
  implements AdEvent
{
  private AdEvent.AdEventType a;
  private Ad b;
  private Map<String, String> c;
  
  c(AdEvent.AdEventType paramAdEventType, Ad paramAd, Map<String, String> paramMap)
  {
    this.a = paramAdEventType;
    this.b = paramAd;
    this.c = paramMap;
  }
  
  private String a()
  {
    if (this.c == null) {
      return "";
    }
    StringBuilder localStringBuilder = new StringBuilder("{");
    Iterator localIterator = this.c.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      localStringBuilder.append((String)localEntry.getKey());
      localStringBuilder.append(": ");
      localStringBuilder.append((String)localEntry.getValue());
      if (localIterator.hasNext()) {
        localStringBuilder.append(", ");
      }
    }
    return localStringBuilder.toString();
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    c localc;
    do
    {
      return true;
      if (!(paramObject instanceof c)) {
        return false;
      }
      localc = (c)paramObject;
      if (this.b == null)
      {
        if (localc.b != null) {
          return false;
        }
      }
      else if (!this.b.equals(localc.b)) {
        return false;
      }
    } while (this.a == localc.a);
    return false;
  }
  
  public Ad getAd()
  {
    return this.b;
  }
  
  public Map<String, String> getAdData()
  {
    return this.c;
  }
  
  public AdEvent.AdEventType getType()
  {
    return this.a;
  }
  
  public String toString()
  {
    Object[] arrayOfObject1 = new Object[2];
    arrayOfObject1[0] = this.a;
    arrayOfObject1[1] = this.b;
    String str1 = String.format("AdEvent[type=%s, ad=%s", arrayOfObject1);
    StringBuilder localStringBuilder = new StringBuilder().append(str1);
    if (this.c == null) {}
    Object[] arrayOfObject2;
    for (String str2 = "]";; str2 = String.format(", adData=%s]", arrayOfObject2))
    {
      return str2;
      arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = a();
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.b.c
 * JD-Core Version:    0.7.0.1
 */