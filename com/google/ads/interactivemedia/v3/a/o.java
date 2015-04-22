package com.google.ads.interactivemedia.v3.a;

import com.google.ads.interactivemedia.v3.a.b.g;
import java.util.Map.Entry;
import java.util.Set;

public final class o
  extends l
{
  private final g<String, l> a = new g();
  
  public void a(String paramString, l paraml)
  {
    if (paraml == null) {
      paraml = n.a;
    }
    this.a.put(paramString, paraml);
  }
  
  public boolean equals(Object paramObject)
  {
    return (paramObject == this) || (((paramObject instanceof o)) && (((o)paramObject).a.equals(this.a)));
  }
  
  public int hashCode()
  {
    return this.a.hashCode();
  }
  
  public Set<Map.Entry<String, l>> o()
  {
    return this.a.entrySet();
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.a.o
 * JD-Core Version:    0.7.0.1
 */