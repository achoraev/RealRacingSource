package com.google.ads.interactivemedia.v3.a;

import com.google.ads.interactivemedia.v3.a.b.a.g;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class f
{
  final j a = new j() {};
  final r b = new r() {};
  private final ThreadLocal<Map<com.google.ads.interactivemedia.v3.a.c.a<?>, a<?>>> c = new ThreadLocal();
  private final Map<com.google.ads.interactivemedia.v3.a.c.a<?>, w<?>> d = Collections.synchronizedMap(new HashMap());
  private final List<x> e;
  private final com.google.ads.interactivemedia.v3.a.b.c f;
  private final boolean g;
  private final boolean h;
  private final boolean i;
  private final boolean j;
  
  public f()
  {
    this(com.google.ads.interactivemedia.v3.a.b.d.a, d.a, Collections.emptyMap(), false, false, false, true, false, false, u.a, Collections.emptyList());
  }
  
  f(com.google.ads.interactivemedia.v3.a.b.d paramd, e parame, Map<Type, h<?>> paramMap, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5, boolean paramBoolean6, u paramu, List<x> paramList)
  {
    this.f = new com.google.ads.interactivemedia.v3.a.b.c(paramMap);
    this.g = paramBoolean1;
    this.i = paramBoolean3;
    this.h = paramBoolean4;
    this.j = paramBoolean5;
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(com.google.ads.interactivemedia.v3.a.b.a.l.Q);
    localArrayList.add(g.a);
    localArrayList.add(paramd);
    localArrayList.addAll(paramList);
    localArrayList.add(com.google.ads.interactivemedia.v3.a.b.a.l.x);
    localArrayList.add(com.google.ads.interactivemedia.v3.a.b.a.l.m);
    localArrayList.add(com.google.ads.interactivemedia.v3.a.b.a.l.g);
    localArrayList.add(com.google.ads.interactivemedia.v3.a.b.a.l.i);
    localArrayList.add(com.google.ads.interactivemedia.v3.a.b.a.l.k);
    localArrayList.add(com.google.ads.interactivemedia.v3.a.b.a.l.a(Long.TYPE, Long.class, a(paramu)));
    localArrayList.add(com.google.ads.interactivemedia.v3.a.b.a.l.a(Double.TYPE, Double.class, a(paramBoolean6)));
    localArrayList.add(com.google.ads.interactivemedia.v3.a.b.a.l.a(Float.TYPE, Float.class, b(paramBoolean6)));
    localArrayList.add(com.google.ads.interactivemedia.v3.a.b.a.l.r);
    localArrayList.add(com.google.ads.interactivemedia.v3.a.b.a.l.t);
    localArrayList.add(com.google.ads.interactivemedia.v3.a.b.a.l.z);
    localArrayList.add(com.google.ads.interactivemedia.v3.a.b.a.l.B);
    localArrayList.add(com.google.ads.interactivemedia.v3.a.b.a.l.a(BigDecimal.class, com.google.ads.interactivemedia.v3.a.b.a.l.v));
    localArrayList.add(com.google.ads.interactivemedia.v3.a.b.a.l.a(BigInteger.class, com.google.ads.interactivemedia.v3.a.b.a.l.w));
    localArrayList.add(com.google.ads.interactivemedia.v3.a.b.a.l.D);
    localArrayList.add(com.google.ads.interactivemedia.v3.a.b.a.l.F);
    localArrayList.add(com.google.ads.interactivemedia.v3.a.b.a.l.J);
    localArrayList.add(com.google.ads.interactivemedia.v3.a.b.a.l.O);
    localArrayList.add(com.google.ads.interactivemedia.v3.a.b.a.l.H);
    localArrayList.add(com.google.ads.interactivemedia.v3.a.b.a.l.d);
    localArrayList.add(com.google.ads.interactivemedia.v3.a.b.a.c.a);
    localArrayList.add(com.google.ads.interactivemedia.v3.a.b.a.l.M);
    localArrayList.add(com.google.ads.interactivemedia.v3.a.b.a.j.a);
    localArrayList.add(com.google.ads.interactivemedia.v3.a.b.a.i.a);
    localArrayList.add(com.google.ads.interactivemedia.v3.a.b.a.l.K);
    localArrayList.add(com.google.ads.interactivemedia.v3.a.b.a.a.a);
    localArrayList.add(com.google.ads.interactivemedia.v3.a.b.a.l.R);
    localArrayList.add(com.google.ads.interactivemedia.v3.a.b.a.l.b);
    localArrayList.add(new com.google.ads.interactivemedia.v3.a.b.a.b(this.f));
    localArrayList.add(new com.google.ads.interactivemedia.v3.a.b.a.f(this.f, paramBoolean2));
    localArrayList.add(new com.google.ads.interactivemedia.v3.a.b.a.h(this.f, parame, paramd));
    this.e = Collections.unmodifiableList(localArrayList);
  }
  
  private com.google.ads.interactivemedia.v3.a.d.c a(Writer paramWriter)
    throws IOException
  {
    if (this.i) {
      paramWriter.write(")]}'\n");
    }
    com.google.ads.interactivemedia.v3.a.d.c localc = new com.google.ads.interactivemedia.v3.a.d.c(paramWriter);
    if (this.j) {
      localc.c("  ");
    }
    localc.d(this.g);
    return localc;
  }
  
  private w<Number> a(u paramu)
  {
    if (paramu == u.a) {
      return com.google.ads.interactivemedia.v3.a.b.a.l.n;
    }
    new w()
    {
      public Number a(com.google.ads.interactivemedia.v3.a.d.a paramAnonymousa)
        throws IOException
      {
        if (paramAnonymousa.f() == com.google.ads.interactivemedia.v3.a.d.b.i)
        {
          paramAnonymousa.j();
          return null;
        }
        return Long.valueOf(paramAnonymousa.l());
      }
      
      public void a(com.google.ads.interactivemedia.v3.a.d.c paramAnonymousc, Number paramAnonymousNumber)
        throws IOException
      {
        if (paramAnonymousNumber == null)
        {
          paramAnonymousc.f();
          return;
        }
        paramAnonymousc.b(paramAnonymousNumber.toString());
      }
    };
  }
  
  private w<Number> a(boolean paramBoolean)
  {
    if (paramBoolean) {
      return com.google.ads.interactivemedia.v3.a.b.a.l.p;
    }
    new w()
    {
      public Double a(com.google.ads.interactivemedia.v3.a.d.a paramAnonymousa)
        throws IOException
      {
        if (paramAnonymousa.f() == com.google.ads.interactivemedia.v3.a.d.b.i)
        {
          paramAnonymousa.j();
          return null;
        }
        return Double.valueOf(paramAnonymousa.k());
      }
      
      public void a(com.google.ads.interactivemedia.v3.a.d.c paramAnonymousc, Number paramAnonymousNumber)
        throws IOException
      {
        if (paramAnonymousNumber == null)
        {
          paramAnonymousc.f();
          return;
        }
        double d = paramAnonymousNumber.doubleValue();
        f.a(f.this, d);
        paramAnonymousc.a(paramAnonymousNumber);
      }
    };
  }
  
  private void a(double paramDouble)
  {
    if ((Double.isNaN(paramDouble)) || (Double.isInfinite(paramDouble))) {
      throw new IllegalArgumentException(paramDouble + " is not a valid double value as per JSON specification. To override this" + " behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
    }
  }
  
  private static void a(Object paramObject, com.google.ads.interactivemedia.v3.a.d.a parama)
  {
    if (paramObject != null) {
      try
      {
        if (parama.f() != com.google.ads.interactivemedia.v3.a.d.b.j) {
          throw new m("JSON document was not fully consumed.");
        }
      }
      catch (com.google.ads.interactivemedia.v3.a.d.d locald)
      {
        throw new t(locald);
      }
      catch (IOException localIOException)
      {
        throw new m(localIOException);
      }
    }
  }
  
  private w<Number> b(boolean paramBoolean)
  {
    if (paramBoolean) {
      return com.google.ads.interactivemedia.v3.a.b.a.l.o;
    }
    new w()
    {
      public Float a(com.google.ads.interactivemedia.v3.a.d.a paramAnonymousa)
        throws IOException
      {
        if (paramAnonymousa.f() == com.google.ads.interactivemedia.v3.a.d.b.i)
        {
          paramAnonymousa.j();
          return null;
        }
        return Float.valueOf((float)paramAnonymousa.k());
      }
      
      public void a(com.google.ads.interactivemedia.v3.a.d.c paramAnonymousc, Number paramAnonymousNumber)
        throws IOException
      {
        if (paramAnonymousNumber == null)
        {
          paramAnonymousc.f();
          return;
        }
        float f = paramAnonymousNumber.floatValue();
        f.a(f.this, f);
        paramAnonymousc.a(paramAnonymousNumber);
      }
    };
  }
  
  public <T> w<T> a(com.google.ads.interactivemedia.v3.a.c.a<T> parama)
  {
    Object localObject1 = (w)this.d.get(parama);
    if (localObject1 != null) {
      return localObject1;
    }
    Map localMap = (Map)this.c.get();
    Object localObject2;
    if (localMap == null)
    {
      HashMap localHashMap = new HashMap();
      this.c.set(localHashMap);
      localObject2 = localHashMap;
    }
    for (int k = 1;; k = 0)
    {
      for (;;)
      {
        localObject1 = (a)((Map)localObject2).get(parama);
        if (localObject1 != null) {
          break;
        }
        try
        {
          a locala = new a();
          ((Map)localObject2).put(parama, locala);
          Iterator localIterator = this.e.iterator();
          while (localIterator.hasNext())
          {
            localObject1 = ((x)localIterator.next()).a(this, parama);
            if (localObject1 != null)
            {
              locala.a((w)localObject1);
              this.d.put(parama, localObject1);
              return localObject1;
            }
          }
          throw new IllegalArgumentException("GSON cannot handle " + parama);
        }
        finally
        {
          ((Map)localObject2).remove(parama);
          if (k != 0) {
            this.c.remove();
          }
        }
      }
      localObject2 = localMap;
    }
  }
  
  public <T> w<T> a(x paramx, com.google.ads.interactivemedia.v3.a.c.a<T> parama)
  {
    Iterator localIterator = this.e.iterator();
    int k = 0;
    while (localIterator.hasNext())
    {
      x localx = (x)localIterator.next();
      if (k == 0)
      {
        if (localx == paramx) {
          k = 1;
        }
      }
      else
      {
        w localw = localx.a(this, parama);
        if (localw != null) {
          return localw;
        }
      }
    }
    throw new IllegalArgumentException("GSON cannot serialize " + parama);
  }
  
  public <T> w<T> a(Class<T> paramClass)
  {
    return a(com.google.ads.interactivemedia.v3.a.c.a.b(paramClass));
  }
  
  /* Error */
  public <T> T a(com.google.ads.interactivemedia.v3.a.d.a parama, Type paramType)
    throws m, t
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore_3
    //   2: aload_1
    //   3: invokevirtual 413	com/google/ads/interactivemedia/v3/a/d/a:p	()Z
    //   6: istore 4
    //   8: aload_1
    //   9: iload_3
    //   10: invokevirtual 415	com/google/ads/interactivemedia/v3/a/d/a:a	(Z)V
    //   13: aload_1
    //   14: invokevirtual 319	com/google/ads/interactivemedia/v3/a/d/a:f	()Lcom/google/ads/interactivemedia/v3/a/d/b;
    //   17: pop
    //   18: iconst_0
    //   19: istore_3
    //   20: aload_0
    //   21: aload_2
    //   22: invokestatic 418	com/google/ads/interactivemedia/v3/a/c/a:a	(Ljava/lang/reflect/Type;)Lcom/google/ads/interactivemedia/v3/a/c/a;
    //   25: invokevirtual 406	com/google/ads/interactivemedia/v3/a/f:a	(Lcom/google/ads/interactivemedia/v3/a/c/a;)Lcom/google/ads/interactivemedia/v3/a/w;
    //   28: aload_1
    //   29: invokevirtual 421	com/google/ads/interactivemedia/v3/a/w:b	(Lcom/google/ads/interactivemedia/v3/a/d/a;)Ljava/lang/Object;
    //   32: astore 10
    //   34: aload_1
    //   35: iload 4
    //   37: invokevirtual 415	com/google/ads/interactivemedia/v3/a/d/a:a	(Z)V
    //   40: aload 10
    //   42: areturn
    //   43: astore 8
    //   45: iload_3
    //   46: ifeq +11 -> 57
    //   49: aload_1
    //   50: iload 4
    //   52: invokevirtual 415	com/google/ads/interactivemedia/v3/a/d/a:a	(Z)V
    //   55: aconst_null
    //   56: areturn
    //   57: new 331	com/google/ads/interactivemedia/v3/a/t
    //   60: dup
    //   61: aload 8
    //   63: invokespecial 334	com/google/ads/interactivemedia/v3/a/t:<init>	(Ljava/lang/Throwable;)V
    //   66: athrow
    //   67: astore 6
    //   69: aload_1
    //   70: iload 4
    //   72: invokevirtual 415	com/google/ads/interactivemedia/v3/a/d/a:a	(Z)V
    //   75: aload 6
    //   77: athrow
    //   78: astore 7
    //   80: new 331	com/google/ads/interactivemedia/v3/a/t
    //   83: dup
    //   84: aload 7
    //   86: invokespecial 334	com/google/ads/interactivemedia/v3/a/t:<init>	(Ljava/lang/Throwable;)V
    //   89: athrow
    //   90: astore 5
    //   92: new 331	com/google/ads/interactivemedia/v3/a/t
    //   95: dup
    //   96: aload 5
    //   98: invokespecial 334	com/google/ads/interactivemedia/v3/a/t:<init>	(Ljava/lang/Throwable;)V
    //   101: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	102	0	this	f
    //   0	102	1	parama	com.google.ads.interactivemedia.v3.a.d.a
    //   0	102	2	paramType	Type
    //   1	45	3	bool1	boolean
    //   6	65	4	bool2	boolean
    //   90	7	5	localIOException	IOException
    //   67	9	6	localObject1	Object
    //   78	7	7	localIllegalStateException	IllegalStateException
    //   43	19	8	localEOFException	java.io.EOFException
    //   32	9	10	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   13	18	43	java/io/EOFException
    //   20	34	43	java/io/EOFException
    //   13	18	67	finally
    //   20	34	67	finally
    //   57	67	67	finally
    //   80	90	67	finally
    //   92	102	67	finally
    //   13	18	78	java/lang/IllegalStateException
    //   20	34	78	java/lang/IllegalStateException
    //   13	18	90	java/io/IOException
    //   20	34	90	java/io/IOException
  }
  
  public <T> T a(Reader paramReader, Type paramType)
    throws m, t
  {
    com.google.ads.interactivemedia.v3.a.d.a locala = new com.google.ads.interactivemedia.v3.a.d.a(paramReader);
    Object localObject = a(locala, paramType);
    a(localObject, locala);
    return localObject;
  }
  
  public <T> T a(String paramString, Class<T> paramClass)
    throws t
  {
    Object localObject = a(paramString, paramClass);
    return com.google.ads.interactivemedia.v3.a.b.i.a(paramClass).cast(localObject);
  }
  
  public <T> T a(String paramString, Type paramType)
    throws t
  {
    if (paramString == null) {
      return null;
    }
    return a(new StringReader(paramString), paramType);
  }
  
  public String a(l paraml)
  {
    StringWriter localStringWriter = new StringWriter();
    a(paraml, localStringWriter);
    return localStringWriter.toString();
  }
  
  public String a(Object paramObject)
  {
    if (paramObject == null) {
      return a(n.a);
    }
    return a(paramObject, paramObject.getClass());
  }
  
  public String a(Object paramObject, Type paramType)
  {
    StringWriter localStringWriter = new StringWriter();
    a(paramObject, paramType, localStringWriter);
    return localStringWriter.toString();
  }
  
  public void a(l paraml, com.google.ads.interactivemedia.v3.a.d.c paramc)
    throws m
  {
    boolean bool1 = paramc.g();
    paramc.b(true);
    boolean bool2 = paramc.h();
    paramc.c(this.h);
    boolean bool3 = paramc.i();
    paramc.d(this.g);
    try
    {
      com.google.ads.interactivemedia.v3.a.b.j.a(paraml, paramc);
      return;
    }
    catch (IOException localIOException)
    {
      throw new m(localIOException);
    }
    finally
    {
      paramc.b(bool1);
      paramc.c(bool2);
      paramc.d(bool3);
    }
  }
  
  public void a(l paraml, Appendable paramAppendable)
    throws m
  {
    try
    {
      a(paraml, a(com.google.ads.interactivemedia.v3.a.b.j.a(paramAppendable)));
      return;
    }
    catch (IOException localIOException)
    {
      throw new RuntimeException(localIOException);
    }
  }
  
  public void a(Object paramObject, Type paramType, com.google.ads.interactivemedia.v3.a.d.c paramc)
    throws m
  {
    w localw = a(com.google.ads.interactivemedia.v3.a.c.a.a(paramType));
    boolean bool1 = paramc.g();
    paramc.b(true);
    boolean bool2 = paramc.h();
    paramc.c(this.h);
    boolean bool3 = paramc.i();
    paramc.d(this.g);
    try
    {
      localw.a(paramc, paramObject);
      return;
    }
    catch (IOException localIOException)
    {
      throw new m(localIOException);
    }
    finally
    {
      paramc.b(bool1);
      paramc.c(bool2);
      paramc.d(bool3);
    }
  }
  
  public void a(Object paramObject, Type paramType, Appendable paramAppendable)
    throws m
  {
    try
    {
      a(paramObject, paramType, a(com.google.ads.interactivemedia.v3.a.b.j.a(paramAppendable)));
      return;
    }
    catch (IOException localIOException)
    {
      throw new m(localIOException);
    }
  }
  
  public String toString()
  {
    return "{serializeNulls:" + this.g + "factories:" + this.e + ",instanceCreators:" + this.f + "}";
  }
  
  static class a<T>
    extends w<T>
  {
    private w<T> a;
    
    public void a(com.google.ads.interactivemedia.v3.a.d.c paramc, T paramT)
      throws IOException
    {
      if (this.a == null) {
        throw new IllegalStateException();
      }
      this.a.a(paramc, paramT);
    }
    
    public void a(w<T> paramw)
    {
      if (this.a != null) {
        throw new AssertionError();
      }
      this.a = paramw;
    }
    
    public T b(com.google.ads.interactivemedia.v3.a.d.a parama)
      throws IOException
    {
      if (this.a == null) {
        throw new IllegalStateException();
      }
      return this.a.b(parama);
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.a.f
 * JD-Core Version:    0.7.0.1
 */