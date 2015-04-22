package com.google.ads.interactivemedia.v3.a.b.a;

import com.google.ads.interactivemedia.v3.a.d.b;
import com.google.ads.interactivemedia.v3.a.d.c;
import com.google.ads.interactivemedia.v3.a.f;
import com.google.ads.interactivemedia.v3.a.w;
import com.google.ads.interactivemedia.v3.a.x;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class g
  extends w<Object>
{
  public static final x a = new x()
  {
    public <T> w<T> a(f paramAnonymousf, com.google.ads.interactivemedia.v3.a.c.a<T> paramAnonymousa)
    {
      if (paramAnonymousa.a() == Object.class) {
        return new g(paramAnonymousf, null);
      }
      return null;
    }
  };
  private final f b;
  
  private g(f paramf)
  {
    this.b = paramf;
  }
  
  public void a(c paramc, Object paramObject)
    throws IOException
  {
    if (paramObject == null)
    {
      paramc.f();
      return;
    }
    w localw = this.b.a(paramObject.getClass());
    if ((localw instanceof g))
    {
      paramc.d();
      paramc.e();
      return;
    }
    localw.a(paramc, paramObject);
  }
  
  public Object b(com.google.ads.interactivemedia.v3.a.d.a parama)
    throws IOException
  {
    b localb = parama.f();
    switch (2.a[localb.ordinal()])
    {
    default: 
      throw new IllegalStateException();
    case 1: 
      ArrayList localArrayList = new ArrayList();
      parama.a();
      while (parama.e()) {
        localArrayList.add(b(parama));
      }
      parama.b();
      return localArrayList;
    case 2: 
      com.google.ads.interactivemedia.v3.a.b.g localg = new com.google.ads.interactivemedia.v3.a.b.g();
      parama.c();
      while (parama.e()) {
        localg.put(parama.g(), b(parama));
      }
      parama.d();
      return localg;
    case 3: 
      return parama.h();
    case 4: 
      return Double.valueOf(parama.k());
    case 5: 
      return Boolean.valueOf(parama.i());
    }
    parama.j();
    return null;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.a.b.a.g
 * JD-Core Version:    0.7.0.1
 */