package com.google.ads.interactivemedia.v3.a.b;

import com.google.ads.interactivemedia.v3.a.d.a;
import com.google.ads.interactivemedia.v3.a.d.c;
import com.google.ads.interactivemedia.v3.a.d.d;
import com.google.ads.interactivemedia.v3.a.m;
import com.google.ads.interactivemedia.v3.a.n;
import com.google.ads.interactivemedia.v3.a.p;
import com.google.ads.interactivemedia.v3.a.t;
import com.google.ads.interactivemedia.v3.a.w;
import java.io.EOFException;
import java.io.IOException;
import java.io.Writer;

public final class j
{
  public static com.google.ads.interactivemedia.v3.a.l a(a parama)
    throws p
  {
    int i = 1;
    try
    {
      parama.f();
      i = 0;
      com.google.ads.interactivemedia.v3.a.l locall = (com.google.ads.interactivemedia.v3.a.l)com.google.ads.interactivemedia.v3.a.b.a.l.P.b(parama);
      return locall;
    }
    catch (EOFException localEOFException)
    {
      if (i != 0) {
        return n.a;
      }
      throw new t(localEOFException);
    }
    catch (d locald)
    {
      throw new t(locald);
    }
    catch (IOException localIOException)
    {
      throw new m(localIOException);
    }
    catch (NumberFormatException localNumberFormatException)
    {
      throw new t(localNumberFormatException);
    }
  }
  
  public static Writer a(Appendable paramAppendable)
  {
    if ((paramAppendable instanceof Writer)) {
      return (Writer)paramAppendable;
    }
    return new a(paramAppendable, null);
  }
  
  public static void a(com.google.ads.interactivemedia.v3.a.l paraml, c paramc)
    throws IOException
  {
    com.google.ads.interactivemedia.v3.a.b.a.l.P.a(paramc, paraml);
  }
  
  private static final class a
    extends Writer
  {
    private final Appendable a;
    private final a b = new a();
    
    private a(Appendable paramAppendable)
    {
      this.a = paramAppendable;
    }
    
    public void close() {}
    
    public void flush() {}
    
    public void write(int paramInt)
      throws IOException
    {
      this.a.append((char)paramInt);
    }
    
    public void write(char[] paramArrayOfChar, int paramInt1, int paramInt2)
      throws IOException
    {
      this.b.a = paramArrayOfChar;
      this.a.append(this.b, paramInt1, paramInt1 + paramInt2);
    }
    
    static class a
      implements CharSequence
    {
      char[] a;
      
      public char charAt(int paramInt)
      {
        return this.a[paramInt];
      }
      
      public int length()
      {
        return this.a.length;
      }
      
      public CharSequence subSequence(int paramInt1, int paramInt2)
      {
        return new String(this.a, paramInt1, paramInt2 - paramInt1);
      }
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.a.b.j
 * JD-Core Version:    0.7.0.1
 */