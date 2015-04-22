package com.google.ads.interactivemedia.v3.a;

import com.google.ads.interactivemedia.v3.a.b.a.e;
import com.google.ads.interactivemedia.v3.a.d.a;
import com.google.ads.interactivemedia.v3.a.d.c;
import java.io.IOException;

public abstract class w<T>
{
  public final l a(T paramT)
  {
    try
    {
      e locale = new e();
      a(locale, paramT);
      l locall = locale.a();
      return locall;
    }
    catch (IOException localIOException)
    {
      throw new m(localIOException);
    }
  }
  
  public abstract void a(c paramc, T paramT)
    throws IOException;
  
  public abstract T b(a parama)
    throws IOException;
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.a.w
 * JD-Core Version:    0.7.0.1
 */