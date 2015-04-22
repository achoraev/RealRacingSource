package com.google.ads.interactivemedia.v3.a.b.a;

import com.google.ads.interactivemedia.v3.a.b.d;
import com.google.ads.interactivemedia.v3.a.b.i;
import com.google.ads.interactivemedia.v3.a.e;
import com.google.ads.interactivemedia.v3.a.f;
import com.google.ads.interactivemedia.v3.a.t;
import com.google.ads.interactivemedia.v3.a.w;
import com.google.ads.interactivemedia.v3.a.x;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public final class h
  implements x
{
  private final com.google.ads.interactivemedia.v3.a.b.c a;
  private final e b;
  private final d c;
  
  public h(com.google.ads.interactivemedia.v3.a.b.c paramc, e parame, d paramd)
  {
    this.a = paramc;
    this.b = parame;
    this.c = paramd;
  }
  
  private b a(final f paramf, final Field paramField, String paramString, final com.google.ads.interactivemedia.v3.a.c.a<?> parama, boolean paramBoolean1, boolean paramBoolean2)
  {
    new b(paramString, paramBoolean1, paramBoolean2)
    {
      final w<?> a = paramf.a(parama);
      
      void a(com.google.ads.interactivemedia.v3.a.d.a paramAnonymousa, Object paramAnonymousObject)
        throws IOException, IllegalAccessException
      {
        Object localObject = this.a.b(paramAnonymousa);
        if ((localObject != null) || (!this.e)) {
          paramField.set(paramAnonymousObject, localObject);
        }
      }
      
      void a(com.google.ads.interactivemedia.v3.a.d.c paramAnonymousc, Object paramAnonymousObject)
        throws IOException, IllegalAccessException
      {
        Object localObject = paramField.get(paramAnonymousObject);
        new k(paramf, this.a, parama.b()).a(paramAnonymousc, localObject);
      }
    };
  }
  
  private String a(Field paramField)
  {
    com.google.ads.interactivemedia.v3.a.a.b localb = (com.google.ads.interactivemedia.v3.a.a.b)paramField.getAnnotation(com.google.ads.interactivemedia.v3.a.a.b.class);
    if (localb == null) {
      return this.b.a(paramField);
    }
    return localb.a();
  }
  
  private Map<String, b> a(f paramf, com.google.ads.interactivemedia.v3.a.c.a<?> parama, Class<?> paramClass)
  {
    LinkedHashMap localLinkedHashMap = new LinkedHashMap();
    if (paramClass.isInterface()) {
      return localLinkedHashMap;
    }
    Type localType1 = parama.b();
    while (paramClass != Object.class)
    {
      Field[] arrayOfField = paramClass.getDeclaredFields();
      int i = arrayOfField.length;
      int j = 0;
      if (j < i)
      {
        Field localField = arrayOfField[j];
        boolean bool1 = a(localField, true);
        boolean bool2 = a(localField, false);
        if ((!bool1) && (!bool2)) {}
        b localb2;
        do
        {
          j++;
          break;
          localField.setAccessible(true);
          Type localType2 = com.google.ads.interactivemedia.v3.a.b.b.a(parama.b(), paramClass, localField.getGenericType());
          b localb1 = a(paramf, localField, a(localField), com.google.ads.interactivemedia.v3.a.c.a.a(localType2), bool1, bool2);
          localb2 = (b)localLinkedHashMap.put(localb1.g, localb1);
        } while (localb2 == null);
        throw new IllegalArgumentException(localType1 + " declares multiple JSON fields named " + localb2.g);
      }
      parama = com.google.ads.interactivemedia.v3.a.c.a.a(com.google.ads.interactivemedia.v3.a.b.b.a(parama.b(), paramClass, paramClass.getGenericSuperclass()));
      paramClass = parama.a();
    }
    return localLinkedHashMap;
  }
  
  public <T> w<T> a(f paramf, com.google.ads.interactivemedia.v3.a.c.a<T> parama)
  {
    Class localClass = parama.a();
    if (!Object.class.isAssignableFrom(localClass)) {
      return null;
    }
    return new a(this.a.a(parama), a(paramf, parama, localClass), null);
  }
  
  public boolean a(Field paramField, boolean paramBoolean)
  {
    return (!this.c.a(paramField.getType(), paramBoolean)) && (!this.c.a(paramField, paramBoolean));
  }
  
  public static final class a<T>
    extends w<T>
  {
    private final com.google.ads.interactivemedia.v3.a.b.h<T> a;
    private final Map<String, h.b> b;
    
    private a(com.google.ads.interactivemedia.v3.a.b.h<T> paramh, Map<String, h.b> paramMap)
    {
      this.a = paramh;
      this.b = paramMap;
    }
    
    public void a(com.google.ads.interactivemedia.v3.a.d.c paramc, T paramT)
      throws IOException
    {
      if (paramT == null)
      {
        paramc.f();
        return;
      }
      paramc.d();
      try
      {
        Iterator localIterator = this.b.values().iterator();
        while (localIterator.hasNext())
        {
          h.b localb = (h.b)localIterator.next();
          if (localb.h)
          {
            paramc.a(localb.g);
            localb.a(paramc, paramT);
          }
        }
        paramc.e();
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        throw new AssertionError();
      }
    }
    
    public T b(com.google.ads.interactivemedia.v3.a.d.a parama)
      throws IOException
    {
      if (parama.f() == com.google.ads.interactivemedia.v3.a.d.b.i)
      {
        parama.j();
        return null;
      }
      Object localObject = this.a.a();
      try
      {
        parama.c();
        for (;;)
        {
          if (!parama.e()) {
            break label111;
          }
          String str = parama.g();
          localb = (h.b)this.b.get(str);
          if ((localb != null) && (localb.i)) {
            break;
          }
          parama.n();
        }
      }
      catch (IllegalStateException localIllegalStateException)
      {
        for (;;)
        {
          h.b localb;
          throw new t(localIllegalStateException);
          localb.a(parama, localObject);
        }
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        throw new AssertionError(localIllegalAccessException);
      }
      label111:
      parama.d();
      return localObject;
    }
  }
  
  static abstract class b
  {
    final String g;
    final boolean h;
    final boolean i;
    
    protected b(String paramString, boolean paramBoolean1, boolean paramBoolean2)
    {
      this.g = paramString;
      this.h = paramBoolean1;
      this.i = paramBoolean2;
    }
    
    abstract void a(com.google.ads.interactivemedia.v3.a.d.a parama, Object paramObject)
      throws IOException, IllegalAccessException;
    
    abstract void a(com.google.ads.interactivemedia.v3.a.d.c paramc, Object paramObject)
      throws IOException, IllegalAccessException;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.a.b.a.h
 * JD-Core Version:    0.7.0.1
 */