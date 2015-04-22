package com.google.ads.interactivemedia.v3.a.b.a;

import com.google.ads.interactivemedia.v3.a.b.e;
import com.google.ads.interactivemedia.v3.a.b.h;
import com.google.ads.interactivemedia.v3.a.b.j;
import com.google.ads.interactivemedia.v3.a.q;
import com.google.ads.interactivemedia.v3.a.t;
import com.google.ads.interactivemedia.v3.a.w;
import com.google.ads.interactivemedia.v3.a.x;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class f
  implements x
{
  private final com.google.ads.interactivemedia.v3.a.b.c a;
  private final boolean b;
  
  public f(com.google.ads.interactivemedia.v3.a.b.c paramc, boolean paramBoolean)
  {
    this.a = paramc;
    this.b = paramBoolean;
  }
  
  private w<?> a(com.google.ads.interactivemedia.v3.a.f paramf, Type paramType)
  {
    if ((paramType == Boolean.TYPE) || (paramType == Boolean.class)) {
      return l.f;
    }
    return paramf.a(com.google.ads.interactivemedia.v3.a.c.a.a(paramType));
  }
  
  public <T> w<T> a(com.google.ads.interactivemedia.v3.a.f paramf, com.google.ads.interactivemedia.v3.a.c.a<T> parama)
  {
    Type localType = parama.b();
    if (!Map.class.isAssignableFrom(parama.a())) {
      return null;
    }
    Type[] arrayOfType = com.google.ads.interactivemedia.v3.a.b.b.b(localType, com.google.ads.interactivemedia.v3.a.b.b.e(localType));
    w localw1 = a(paramf, arrayOfType[0]);
    w localw2 = paramf.a(com.google.ads.interactivemedia.v3.a.c.a.a(arrayOfType[1]));
    h localh = this.a.a(parama);
    return new a(paramf, arrayOfType[0], localw1, arrayOfType[1], localw2, localh);
  }
  
  private final class a<K, V>
    extends w<Map<K, V>>
  {
    private final w<K> b;
    private final w<V> c;
    private final h<? extends Map<K, V>> d;
    
    public a(Type paramType1, w<K> paramw, Type paramType2, w<V> paramw1, h<? extends Map<K, V>> paramh)
    {
      this.b = new k(paramType1, paramType2, paramw);
      this.c = new k(paramType1, paramh, paramw1);
      Object localObject;
      this.d = localObject;
    }
    
    private String a(com.google.ads.interactivemedia.v3.a.l paraml)
    {
      if (paraml.i())
      {
        q localq = paraml.m();
        if (localq.p()) {
          return String.valueOf(localq.a());
        }
        if (localq.o()) {
          return Boolean.toString(localq.f());
        }
        if (localq.q()) {
          return localq.b();
        }
        throw new AssertionError();
      }
      if (paraml.j()) {
        return "null";
      }
      throw new AssertionError();
    }
    
    public Map<K, V> a(com.google.ads.interactivemedia.v3.a.d.a parama)
      throws IOException
    {
      com.google.ads.interactivemedia.v3.a.d.b localb = parama.f();
      if (localb == com.google.ads.interactivemedia.v3.a.d.b.i)
      {
        parama.j();
        return null;
      }
      Map localMap = (Map)this.d.a();
      if (localb == com.google.ads.interactivemedia.v3.a.d.b.a)
      {
        parama.a();
        while (parama.e())
        {
          parama.a();
          Object localObject2 = this.b.b(parama);
          if (localMap.put(localObject2, this.c.b(parama)) != null) {
            throw new t("duplicate key: " + localObject2);
          }
          parama.b();
        }
        parama.b();
        return localMap;
      }
      parama.c();
      while (parama.e())
      {
        e.a.a(parama);
        Object localObject1 = this.b.b(parama);
        if (localMap.put(localObject1, this.c.b(parama)) != null) {
          throw new t("duplicate key: " + localObject1);
        }
      }
      parama.d();
      return localMap;
    }
    
    public void a(com.google.ads.interactivemedia.v3.a.d.c paramc, Map<K, V> paramMap)
      throws IOException
    {
      int i = 0;
      if (paramMap == null)
      {
        paramc.f();
        return;
      }
      if (!f.a(f.this))
      {
        paramc.d();
        Iterator localIterator2 = paramMap.entrySet().iterator();
        while (localIterator2.hasNext())
        {
          Map.Entry localEntry2 = (Map.Entry)localIterator2.next();
          paramc.a(String.valueOf(localEntry2.getKey()));
          this.c.a(paramc, localEntry2.getValue());
        }
        paramc.e();
        return;
      }
      ArrayList localArrayList1 = new ArrayList(paramMap.size());
      ArrayList localArrayList2 = new ArrayList(paramMap.size());
      Iterator localIterator1 = paramMap.entrySet().iterator();
      int j = 0;
      if (localIterator1.hasNext())
      {
        Map.Entry localEntry1 = (Map.Entry)localIterator1.next();
        com.google.ads.interactivemedia.v3.a.l locall = this.b.a(localEntry1.getKey());
        localArrayList1.add(locall);
        localArrayList2.add(localEntry1.getValue());
        if ((locall.g()) || (locall.h())) {}
        for (int k = 1;; k = 0)
        {
          j = k | j;
          break;
        }
      }
      if (j != 0)
      {
        paramc.b();
        while (i < localArrayList1.size())
        {
          paramc.b();
          j.a((com.google.ads.interactivemedia.v3.a.l)localArrayList1.get(i), paramc);
          this.c.a(paramc, localArrayList2.get(i));
          paramc.c();
          i++;
        }
        paramc.c();
        return;
      }
      paramc.d();
      while (i < localArrayList1.size())
      {
        paramc.a(a((com.google.ads.interactivemedia.v3.a.l)localArrayList1.get(i)));
        this.c.a(paramc, localArrayList2.get(i));
        i++;
      }
      paramc.e();
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.a.b.a.f
 * JD-Core Version:    0.7.0.1
 */