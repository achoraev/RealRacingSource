package com.google.ads.interactivemedia.v3.a.b.a;

import com.google.ads.interactivemedia.v3.a.b.h;
import com.google.ads.interactivemedia.v3.a.f;
import com.google.ads.interactivemedia.v3.a.w;
import com.google.ads.interactivemedia.v3.a.x;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;

public final class b
  implements x
{
  private final com.google.ads.interactivemedia.v3.a.b.c a;
  
  public b(com.google.ads.interactivemedia.v3.a.b.c paramc)
  {
    this.a = paramc;
  }
  
  public <T> w<T> a(f paramf, com.google.ads.interactivemedia.v3.a.c.a<T> parama)
  {
    Type localType1 = parama.b();
    Class localClass = parama.a();
    if (!Collection.class.isAssignableFrom(localClass)) {
      return null;
    }
    Type localType2 = com.google.ads.interactivemedia.v3.a.b.b.a(localType1, localClass);
    return new a(paramf, localType2, paramf.a(com.google.ads.interactivemedia.v3.a.c.a.a(localType2)), this.a.a(parama));
  }
  
  private static final class a<E>
    extends w<Collection<E>>
  {
    private final w<E> a;
    private final h<? extends Collection<E>> b;
    
    public a(f paramf, Type paramType, w<E> paramw, h<? extends Collection<E>> paramh)
    {
      this.a = new k(paramf, paramw, paramType);
      this.b = paramh;
    }
    
    public Collection<E> a(com.google.ads.interactivemedia.v3.a.d.a parama)
      throws IOException
    {
      if (parama.f() == com.google.ads.interactivemedia.v3.a.d.b.i)
      {
        parama.j();
        return null;
      }
      Collection localCollection = (Collection)this.b.a();
      parama.a();
      while (parama.e()) {
        localCollection.add(this.a.b(parama));
      }
      parama.b();
      return localCollection;
    }
    
    public void a(com.google.ads.interactivemedia.v3.a.d.c paramc, Collection<E> paramCollection)
      throws IOException
    {
      if (paramCollection == null)
      {
        paramc.f();
        return;
      }
      paramc.b();
      Iterator localIterator = paramCollection.iterator();
      while (localIterator.hasNext())
      {
        Object localObject = localIterator.next();
        this.a.a(paramc, localObject);
      }
      paramc.c();
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.a.b.a.b
 * JD-Core Version:    0.7.0.1
 */