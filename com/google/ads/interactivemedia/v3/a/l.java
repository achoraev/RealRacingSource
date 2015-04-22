package com.google.ads.interactivemedia.v3.a;

import com.google.ads.interactivemedia.v3.a.b.j;
import com.google.ads.interactivemedia.v3.a.d.c;
import java.io.IOException;
import java.io.StringWriter;

public abstract class l
{
  public Number a()
  {
    throw new UnsupportedOperationException(getClass().getSimpleName());
  }
  
  public String b()
  {
    throw new UnsupportedOperationException(getClass().getSimpleName());
  }
  
  public double c()
  {
    throw new UnsupportedOperationException(getClass().getSimpleName());
  }
  
  public long d()
  {
    throw new UnsupportedOperationException(getClass().getSimpleName());
  }
  
  public int e()
  {
    throw new UnsupportedOperationException(getClass().getSimpleName());
  }
  
  public boolean f()
  {
    throw new UnsupportedOperationException(getClass().getSimpleName());
  }
  
  public boolean g()
  {
    return this instanceof i;
  }
  
  public boolean h()
  {
    return this instanceof o;
  }
  
  public boolean i()
  {
    return this instanceof q;
  }
  
  public boolean j()
  {
    return this instanceof n;
  }
  
  public o k()
  {
    if (h()) {
      return (o)this;
    }
    throw new IllegalStateException("Not a JSON Object: " + this);
  }
  
  public i l()
  {
    if (g()) {
      return (i)this;
    }
    throw new IllegalStateException("This is not a JSON Array.");
  }
  
  public q m()
  {
    if (i()) {
      return (q)this;
    }
    throw new IllegalStateException("This is not a JSON Primitive.");
  }
  
  Boolean n()
  {
    throw new UnsupportedOperationException(getClass().getSimpleName());
  }
  
  public String toString()
  {
    try
    {
      StringWriter localStringWriter = new StringWriter();
      c localc = new c(localStringWriter);
      localc.b(true);
      j.a(this, localc);
      String str = localStringWriter.toString();
      return str;
    }
    catch (IOException localIOException)
    {
      throw new AssertionError(localIOException);
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.a.l
 * JD-Core Version:    0.7.0.1
 */