package com.google.ads.interactivemedia.v3.a.b.a;

import com.google.ads.interactivemedia.v3.a.d.c;
import com.google.ads.interactivemedia.v3.a.f;
import com.google.ads.interactivemedia.v3.a.w;
import java.io.IOException;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

final class k<T>
  extends w<T>
{
  private final f a;
  private final w<T> b;
  private final Type c;
  
  k(f paramf, w<T> paramw, Type paramType)
  {
    this.a = paramf;
    this.b = paramw;
    this.c = paramType;
  }
  
  private Type a(Type paramType, Object paramObject)
  {
    if ((paramObject != null) && ((paramType == Object.class) || ((paramType instanceof TypeVariable)) || ((paramType instanceof Class)))) {
      paramType = paramObject.getClass();
    }
    return paramType;
  }
  
  public void a(c paramc, T paramT)
    throws IOException
  {
    w localw = this.b;
    Type localType = a(this.c, paramT);
    if (localType != this.c)
    {
      localw = this.a.a(com.google.ads.interactivemedia.v3.a.c.a.a(localType));
      if ((localw instanceof h.a)) {
        break label52;
      }
    }
    for (;;)
    {
      localw.a(paramc, paramT);
      return;
      label52:
      if (!(this.b instanceof h.a)) {
        localw = this.b;
      }
    }
  }
  
  public T b(com.google.ads.interactivemedia.v3.a.d.a parama)
    throws IOException
  {
    return this.b.b(parama);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.a.b.a.k
 * JD-Core Version:    0.7.0.1
 */