package com.google.ads.interactivemedia.v3.a;

import com.google.ads.interactivemedia.v3.a.b.j;
import com.google.ads.interactivemedia.v3.a.d.c;
import java.io.IOException;

final class v<T>
  extends w<T>
{
  private final s<T> a;
  private final k<T> b;
  private final f c;
  private final com.google.ads.interactivemedia.v3.a.c.a<T> d;
  private final x e;
  private w<T> f;
  
  private v(s<T> params, k<T> paramk, f paramf, com.google.ads.interactivemedia.v3.a.c.a<T> parama, x paramx)
  {
    this.a = params;
    this.b = paramk;
    this.c = paramf;
    this.d = parama;
    this.e = paramx;
  }
  
  private w<T> a()
  {
    w localw1 = this.f;
    if (localw1 != null) {
      return localw1;
    }
    w localw2 = this.c.a(this.e, this.d);
    this.f = localw2;
    return localw2;
  }
  
  public static x a(com.google.ads.interactivemedia.v3.a.c.a<?> parama, Object paramObject)
  {
    return new a(paramObject, parama, false, null, null);
  }
  
  public static x b(com.google.ads.interactivemedia.v3.a.c.a<?> parama, Object paramObject)
  {
    if (parama.b() == parama.a()) {}
    for (boolean bool = true;; bool = false) {
      return new a(paramObject, parama, bool, null, null);
    }
  }
  
  public void a(c paramc, T paramT)
    throws IOException
  {
    if (this.a == null)
    {
      a().a(paramc, paramT);
      return;
    }
    if (paramT == null)
    {
      paramc.f();
      return;
    }
    j.a(this.a.a(paramT, this.d.b(), this.c.b), paramc);
  }
  
  public T b(com.google.ads.interactivemedia.v3.a.d.a parama)
    throws IOException
  {
    if (this.b == null) {
      return a().b(parama);
    }
    l locall = j.a(parama);
    if (locall.j()) {
      return null;
    }
    try
    {
      Object localObject = this.b.b(locall, this.d.b(), this.c.a);
      return localObject;
    }
    catch (p localp)
    {
      throw localp;
    }
    catch (Exception localException)
    {
      throw new p(localException);
    }
  }
  
  private static class a
    implements x
  {
    private final com.google.ads.interactivemedia.v3.a.c.a<?> a;
    private final boolean b;
    private final Class<?> c;
    private final s<?> d;
    private final k<?> e;
    
    private a(Object paramObject, com.google.ads.interactivemedia.v3.a.c.a<?> parama, boolean paramBoolean, Class<?> paramClass)
    {
      s locals;
      k localk;
      if ((paramObject instanceof s))
      {
        locals = (s)paramObject;
        this.d = locals;
        if (!(paramObject instanceof k)) {
          break label87;
        }
        localk = (k)paramObject;
        label36:
        this.e = localk;
        if ((this.d == null) && (this.e == null)) {
          break label93;
        }
      }
      label87:
      label93:
      for (boolean bool = true;; bool = false)
      {
        com.google.ads.interactivemedia.v3.a.b.a.a(bool);
        this.a = parama;
        this.b = paramBoolean;
        this.c = paramClass;
        return;
        locals = null;
        break;
        localk = null;
        break label36;
      }
    }
    
    public <T> w<T> a(f paramf, com.google.ads.interactivemedia.v3.a.c.a<T> parama)
    {
      boolean bool;
      if (this.a != null) {
        if ((this.a.equals(parama)) || ((this.b) && (this.a.b() == parama.a()))) {
          bool = true;
        }
      }
      while (bool)
      {
        return new v(this.d, this.e, paramf, parama, this, null);
        bool = false;
        continue;
        bool = this.c.isAssignableFrom(parama.a());
      }
      return null;
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.a.v
 * JD-Core Version:    0.7.0.1
 */