package com.google.ads.interactivemedia.v3.a.b.a;

import com.google.ads.interactivemedia.v3.a.d.c;
import com.google.ads.interactivemedia.v3.a.i;
import com.google.ads.interactivemedia.v3.a.l;
import com.google.ads.interactivemedia.v3.a.n;
import com.google.ads.interactivemedia.v3.a.o;
import com.google.ads.interactivemedia.v3.a.q;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public final class e
  extends c
{
  private static final Writer a = new Writer()
  {
    public void close()
      throws IOException
    {
      throw new AssertionError();
    }
    
    public void flush()
      throws IOException
    {
      throw new AssertionError();
    }
    
    public void write(char[] paramAnonymousArrayOfChar, int paramAnonymousInt1, int paramAnonymousInt2)
    {
      throw new AssertionError();
    }
  };
  private static final q b = new q("closed");
  private final List<l> c = new ArrayList();
  private String d;
  private l e = n.a;
  
  public e()
  {
    super(a);
  }
  
  private void a(l paraml)
  {
    if (this.d != null)
    {
      if ((!paraml.j()) || (i())) {
        ((o)j()).a(this.d, paraml);
      }
      this.d = null;
      return;
    }
    if (this.c.isEmpty())
    {
      this.e = paraml;
      return;
    }
    l locall = j();
    if ((locall instanceof i))
    {
      ((i)locall).a(paraml);
      return;
    }
    throw new IllegalStateException();
  }
  
  private l j()
  {
    return (l)this.c.get(-1 + this.c.size());
  }
  
  public c a(long paramLong)
    throws IOException
  {
    a(new q(Long.valueOf(paramLong)));
    return this;
  }
  
  public c a(Number paramNumber)
    throws IOException
  {
    if (paramNumber == null) {
      return f();
    }
    if (!g())
    {
      double d1 = paramNumber.doubleValue();
      if ((Double.isNaN(d1)) || (Double.isInfinite(d1))) {
        throw new IllegalArgumentException("JSON forbids NaN and infinities: " + paramNumber);
      }
    }
    a(new q(paramNumber));
    return this;
  }
  
  public c a(String paramString)
    throws IOException
  {
    if ((this.c.isEmpty()) || (this.d != null)) {
      throw new IllegalStateException();
    }
    if ((j() instanceof o))
    {
      this.d = paramString;
      return this;
    }
    throw new IllegalStateException();
  }
  
  public c a(boolean paramBoolean)
    throws IOException
  {
    a(new q(Boolean.valueOf(paramBoolean)));
    return this;
  }
  
  public l a()
  {
    if (!this.c.isEmpty()) {
      throw new IllegalStateException("Expected one JSON element but was " + this.c);
    }
    return this.e;
  }
  
  public c b()
    throws IOException
  {
    i locali = new i();
    a(locali);
    this.c.add(locali);
    return this;
  }
  
  public c b(String paramString)
    throws IOException
  {
    if (paramString == null) {
      return f();
    }
    a(new q(paramString));
    return this;
  }
  
  public c c()
    throws IOException
  {
    if ((this.c.isEmpty()) || (this.d != null)) {
      throw new IllegalStateException();
    }
    if ((j() instanceof i))
    {
      this.c.remove(-1 + this.c.size());
      return this;
    }
    throw new IllegalStateException();
  }
  
  public void close()
    throws IOException
  {
    if (!this.c.isEmpty()) {
      throw new IOException("Incomplete document");
    }
    this.c.add(b);
  }
  
  public c d()
    throws IOException
  {
    o localo = new o();
    a(localo);
    this.c.add(localo);
    return this;
  }
  
  public c e()
    throws IOException
  {
    if ((this.c.isEmpty()) || (this.d != null)) {
      throw new IllegalStateException();
    }
    if ((j() instanceof o))
    {
      this.c.remove(-1 + this.c.size());
      return this;
    }
    throw new IllegalStateException();
  }
  
  public c f()
    throws IOException
  {
    a(n.a);
    return this;
  }
  
  public void flush()
    throws IOException
  {}
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.a.b.a.e
 * JD-Core Version:    0.7.0.1
 */