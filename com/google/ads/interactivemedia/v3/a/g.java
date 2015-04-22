package com.google.ads.interactivemedia.v3.a;

import com.google.ads.interactivemedia.v3.a.b.a.l;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class g
{
  private com.google.ads.interactivemedia.v3.a.b.d a = com.google.ads.interactivemedia.v3.a.b.d.a;
  private u b = u.a;
  private e c = d.a;
  private final Map<Type, h<?>> d = new HashMap();
  private final List<x> e = new ArrayList();
  private final List<x> f = new ArrayList();
  private boolean g;
  private String h;
  private int i = 2;
  private int j = 2;
  private boolean k;
  private boolean l;
  private boolean m = true;
  private boolean n;
  private boolean o;
  
  private void a(String paramString, int paramInt1, int paramInt2, List<x> paramList)
  {
    if ((paramString != null) && (!"".equals(paramString.trim()))) {}
    for (a locala = new a(paramString);; locala = new a(paramInt1, paramInt2))
    {
      paramList.add(v.a(com.google.ads.interactivemedia.v3.a.c.a.b(java.util.Date.class), locala));
      paramList.add(v.a(com.google.ads.interactivemedia.v3.a.c.a.b(Timestamp.class), locala));
      paramList.add(v.a(com.google.ads.interactivemedia.v3.a.c.a.b(java.sql.Date.class), locala));
      do
      {
        return;
      } while ((paramInt1 == 2) || (paramInt2 == 2));
    }
  }
  
  public f a()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.addAll(this.e);
    Collections.reverse(localArrayList);
    localArrayList.addAll(this.f);
    a(this.h, this.i, this.j, localArrayList);
    return new f(this.a, this.c, this.d, this.g, this.k, this.o, this.m, this.n, this.l, this.b, localArrayList);
  }
  
  public g a(Type paramType, Object paramObject)
  {
    if (((paramObject instanceof s)) || ((paramObject instanceof k)) || ((paramObject instanceof h)) || ((paramObject instanceof w))) {}
    for (boolean bool = true;; bool = false)
    {
      com.google.ads.interactivemedia.v3.a.b.a.a(bool);
      if ((paramObject instanceof h)) {
        this.d.put(paramType, (h)paramObject);
      }
      if (((paramObject instanceof s)) || ((paramObject instanceof k)))
      {
        com.google.ads.interactivemedia.v3.a.c.a locala = com.google.ads.interactivemedia.v3.a.c.a.a(paramType);
        this.e.add(v.b(locala, paramObject));
      }
      if ((paramObject instanceof w)) {
        this.e.add(l.a(com.google.ads.interactivemedia.v3.a.c.a.a(paramType), (w)paramObject));
      }
      return this;
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.a.g
 * JD-Core Version:    0.7.0.1
 */