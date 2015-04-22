package com.google.ads.interactivemedia.v3.a.b.a;

import com.google.ads.interactivemedia.v3.a.d.a;
import com.google.ads.interactivemedia.v3.a.d.b;
import com.google.ads.interactivemedia.v3.a.i;
import com.google.ads.interactivemedia.v3.a.n;
import com.google.ads.interactivemedia.v3.a.o;
import com.google.ads.interactivemedia.v3.a.q;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public final class d
  extends a
{
  private static final Reader a = new Reader()
  {
    public void close()
      throws IOException
    {
      throw new AssertionError();
    }
    
    public int read(char[] paramAnonymousArrayOfChar, int paramAnonymousInt1, int paramAnonymousInt2)
      throws IOException
    {
      throw new AssertionError();
    }
  };
  private static final Object b = new Object();
  private final List<Object> c;
  
  private void a(b paramb)
    throws IOException
  {
    if (f() != paramb) {
      throw new IllegalStateException("Expected " + paramb + " but was " + f());
    }
  }
  
  private Object q()
  {
    return this.c.get(-1 + this.c.size());
  }
  
  private Object r()
  {
    return this.c.remove(-1 + this.c.size());
  }
  
  public void a()
    throws IOException
  {
    a(b.a);
    i locali = (i)q();
    this.c.add(locali.iterator());
  }
  
  public void b()
    throws IOException
  {
    a(b.b);
    r();
    r();
  }
  
  public void c()
    throws IOException
  {
    a(b.c);
    o localo = (o)q();
    this.c.add(localo.o().iterator());
  }
  
  public void close()
    throws IOException
  {
    this.c.clear();
    this.c.add(b);
  }
  
  public void d()
    throws IOException
  {
    a(b.d);
    r();
    r();
  }
  
  public boolean e()
    throws IOException
  {
    b localb = f();
    return (localb != b.d) && (localb != b.b);
  }
  
  public b f()
    throws IOException
  {
    if (this.c.isEmpty()) {
      return b.j;
    }
    Object localObject = q();
    if ((localObject instanceof Iterator))
    {
      boolean bool = this.c.get(-2 + this.c.size()) instanceof o;
      Iterator localIterator = (Iterator)localObject;
      if (localIterator.hasNext())
      {
        if (bool) {
          return b.e;
        }
        this.c.add(localIterator.next());
        return f();
      }
      if (bool) {
        return b.d;
      }
      return b.b;
    }
    if ((localObject instanceof o)) {
      return b.c;
    }
    if ((localObject instanceof i)) {
      return b.a;
    }
    if ((localObject instanceof q))
    {
      q localq = (q)localObject;
      if (localq.q()) {
        return b.f;
      }
      if (localq.o()) {
        return b.h;
      }
      if (localq.p()) {
        return b.g;
      }
      throw new AssertionError();
    }
    if ((localObject instanceof n)) {
      return b.i;
    }
    if (localObject == b) {
      throw new IllegalStateException("JsonReader is closed");
    }
    throw new AssertionError();
  }
  
  public String g()
    throws IOException
  {
    a(b.e);
    Map.Entry localEntry = (Map.Entry)((Iterator)q()).next();
    this.c.add(localEntry.getValue());
    return (String)localEntry.getKey();
  }
  
  public String h()
    throws IOException
  {
    b localb = f();
    if ((localb != b.f) && (localb != b.g)) {
      throw new IllegalStateException("Expected " + b.f + " but was " + localb);
    }
    return ((q)r()).b();
  }
  
  public boolean i()
    throws IOException
  {
    a(b.h);
    return ((q)r()).f();
  }
  
  public void j()
    throws IOException
  {
    a(b.i);
    r();
  }
  
  public double k()
    throws IOException
  {
    b localb = f();
    if ((localb != b.g) && (localb != b.f)) {
      throw new IllegalStateException("Expected " + b.g + " but was " + localb);
    }
    double d = ((q)q()).c();
    if ((!p()) && ((Double.isNaN(d)) || (Double.isInfinite(d)))) {
      throw new NumberFormatException("JSON forbids NaN and infinities: " + d);
    }
    r();
    return d;
  }
  
  public long l()
    throws IOException
  {
    b localb = f();
    if ((localb != b.g) && (localb != b.f)) {
      throw new IllegalStateException("Expected " + b.g + " but was " + localb);
    }
    long l = ((q)q()).d();
    r();
    return l;
  }
  
  public int m()
    throws IOException
  {
    b localb = f();
    if ((localb != b.g) && (localb != b.f)) {
      throw new IllegalStateException("Expected " + b.g + " but was " + localb);
    }
    int i = ((q)q()).e();
    r();
    return i;
  }
  
  public void n()
    throws IOException
  {
    if (f() == b.e)
    {
      g();
      return;
    }
    r();
  }
  
  public void o()
    throws IOException
  {
    a(b.e);
    Map.Entry localEntry = (Map.Entry)((Iterator)q()).next();
    this.c.add(localEntry.getValue());
    this.c.add(new q((String)localEntry.getKey()));
  }
  
  public String toString()
  {
    return getClass().getSimpleName();
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.a.b.a.d
 * JD-Core Version:    0.7.0.1
 */