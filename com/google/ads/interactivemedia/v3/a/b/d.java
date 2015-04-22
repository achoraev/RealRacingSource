package com.google.ads.interactivemedia.v3.a.b;

import com.google.ads.interactivemedia.v3.a.b;
import com.google.ads.interactivemedia.v3.a.f;
import com.google.ads.interactivemedia.v3.a.w;
import com.google.ads.interactivemedia.v3.a.x;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class d
  implements x, Cloneable
{
  public static final d a = new d();
  private double b = -1.0D;
  private int c = 136;
  private boolean d = true;
  private boolean e;
  private List<b> f = Collections.emptyList();
  private List<b> g = Collections.emptyList();
  
  private boolean a(com.google.ads.interactivemedia.v3.a.a.c paramc)
  {
    return (paramc == null) || (paramc.a() <= this.b);
  }
  
  private boolean a(com.google.ads.interactivemedia.v3.a.a.c paramc, com.google.ads.interactivemedia.v3.a.a.d paramd)
  {
    return (a(paramc)) && (a(paramd));
  }
  
  private boolean a(com.google.ads.interactivemedia.v3.a.a.d paramd)
  {
    return (paramd == null) || (paramd.a() > this.b);
  }
  
  private boolean a(Class<?> paramClass)
  {
    return (!Enum.class.isAssignableFrom(paramClass)) && ((paramClass.isAnonymousClass()) || (paramClass.isLocalClass()));
  }
  
  private boolean b(Class<?> paramClass)
  {
    return (paramClass.isMemberClass()) && (!c(paramClass));
  }
  
  private boolean c(Class<?> paramClass)
  {
    return (0x8 & paramClass.getModifiers()) != 0;
  }
  
  protected d a()
  {
    try
    {
      d locald = (d)super.clone();
      return locald;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      throw new AssertionError();
    }
  }
  
  public <T> w<T> a(final f paramf, final com.google.ads.interactivemedia.v3.a.c.a<T> parama)
  {
    Class localClass = parama.a();
    final boolean bool1 = a(localClass, true);
    final boolean bool2 = a(localClass, false);
    if ((!bool1) && (!bool2)) {
      return null;
    }
    new w()
    {
      private w<T> f;
      
      private w<T> a()
      {
        w localw1 = this.f;
        if (localw1 != null) {
          return localw1;
        }
        w localw2 = paramf.a(d.this, parama);
        this.f = localw2;
        return localw2;
      }
      
      public void a(com.google.ads.interactivemedia.v3.a.d.c paramAnonymousc, T paramAnonymousT)
        throws IOException
      {
        if (bool1)
        {
          paramAnonymousc.f();
          return;
        }
        a().a(paramAnonymousc, paramAnonymousT);
      }
      
      public T b(com.google.ads.interactivemedia.v3.a.d.a paramAnonymousa)
        throws IOException
      {
        if (bool2)
        {
          paramAnonymousa.n();
          return null;
        }
        return a().b(paramAnonymousa);
      }
    };
  }
  
  public boolean a(Class<?> paramClass, boolean paramBoolean)
  {
    if ((this.b != -1.0D) && (!a((com.google.ads.interactivemedia.v3.a.a.c)paramClass.getAnnotation(com.google.ads.interactivemedia.v3.a.a.c.class), (com.google.ads.interactivemedia.v3.a.a.d)paramClass.getAnnotation(com.google.ads.interactivemedia.v3.a.a.d.class)))) {
      return true;
    }
    if ((!this.d) && (b(paramClass))) {
      return true;
    }
    if (a(paramClass)) {
      return true;
    }
    if (paramBoolean) {}
    for (List localList = this.f;; localList = this.g)
    {
      Iterator localIterator = localList.iterator();
      do
      {
        if (!localIterator.hasNext()) {
          break;
        }
      } while (!((b)localIterator.next()).a(paramClass));
      return true;
    }
    return false;
  }
  
  public boolean a(Field paramField, boolean paramBoolean)
  {
    if ((this.c & paramField.getModifiers()) != 0) {
      return true;
    }
    if ((this.b != -1.0D) && (!a((com.google.ads.interactivemedia.v3.a.a.c)paramField.getAnnotation(com.google.ads.interactivemedia.v3.a.a.c.class), (com.google.ads.interactivemedia.v3.a.a.d)paramField.getAnnotation(com.google.ads.interactivemedia.v3.a.a.d.class)))) {
      return true;
    }
    if (paramField.isSynthetic()) {
      return true;
    }
    if (this.e)
    {
      com.google.ads.interactivemedia.v3.a.a.a locala = (com.google.ads.interactivemedia.v3.a.a.a)paramField.getAnnotation(com.google.ads.interactivemedia.v3.a.a.a.class);
      if (locala != null)
      {
        if (!paramBoolean) {
          break label100;
        }
        if (locala.a()) {
          break label110;
        }
      }
      label100:
      while (!locala.b()) {
        return true;
      }
    }
    label110:
    if ((!this.d) && (b(paramField.getType()))) {
      return true;
    }
    if (a(paramField.getType())) {
      return true;
    }
    if (paramBoolean) {}
    for (List localList = this.f; !localList.isEmpty(); localList = this.g)
    {
      com.google.ads.interactivemedia.v3.a.c localc = new com.google.ads.interactivemedia.v3.a.c(paramField);
      Iterator localIterator = localList.iterator();
      do
      {
        if (!localIterator.hasNext()) {
          break;
        }
      } while (!((b)localIterator.next()).a(localc));
      return true;
    }
    return false;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.a.b.d
 * JD-Core Version:    0.7.0.1
 */