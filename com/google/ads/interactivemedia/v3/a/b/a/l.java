package com.google.ads.interactivemedia.v3.a.b.a;

import com.google.ads.interactivemedia.v3.a.d.c;
import com.google.ads.interactivemedia.v3.a.i;
import com.google.ads.interactivemedia.v3.a.m;
import com.google.ads.interactivemedia.v3.a.n;
import com.google.ads.interactivemedia.v3.a.o;
import com.google.ads.interactivemedia.v3.a.q;
import com.google.ads.interactivemedia.v3.a.t;
import com.google.ads.interactivemedia.v3.a.w;
import com.google.ads.interactivemedia.v3.a.x;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.UUID;

public final class l
{
  public static final w<StringBuffer> A = new w()
  {
    public StringBuffer a(com.google.ads.interactivemedia.v3.a.d.a paramAnonymousa)
      throws IOException
    {
      if (paramAnonymousa.f() == com.google.ads.interactivemedia.v3.a.d.b.i)
      {
        paramAnonymousa.j();
        return null;
      }
      return new StringBuffer(paramAnonymousa.h());
    }
    
    public void a(c paramAnonymousc, StringBuffer paramAnonymousStringBuffer)
      throws IOException
    {
      if (paramAnonymousStringBuffer == null) {}
      for (String str = null;; str = paramAnonymousStringBuffer.toString())
      {
        paramAnonymousc.b(str);
        return;
      }
    }
  };
  public static final x B = a(StringBuffer.class, A);
  public static final w<URL> C = new w()
  {
    public URL a(com.google.ads.interactivemedia.v3.a.d.a paramAnonymousa)
      throws IOException
    {
      if (paramAnonymousa.f() == com.google.ads.interactivemedia.v3.a.d.b.i) {
        paramAnonymousa.j();
      }
      String str;
      do
      {
        return null;
        str = paramAnonymousa.h();
      } while ("null".equals(str));
      return new URL(str);
    }
    
    public void a(c paramAnonymousc, URL paramAnonymousURL)
      throws IOException
    {
      if (paramAnonymousURL == null) {}
      for (String str = null;; str = paramAnonymousURL.toExternalForm())
      {
        paramAnonymousc.b(str);
        return;
      }
    }
  };
  public static final x D = a(URL.class, C);
  public static final w<URI> E = new w()
  {
    public URI a(com.google.ads.interactivemedia.v3.a.d.a paramAnonymousa)
      throws IOException
    {
      if (paramAnonymousa.f() == com.google.ads.interactivemedia.v3.a.d.b.i) {
        paramAnonymousa.j();
      }
      for (;;)
      {
        return null;
        try
        {
          String str = paramAnonymousa.h();
          if ("null".equals(str)) {
            continue;
          }
          URI localURI = new URI(str);
          return localURI;
        }
        catch (URISyntaxException localURISyntaxException)
        {
          throw new m(localURISyntaxException);
        }
      }
    }
    
    public void a(c paramAnonymousc, URI paramAnonymousURI)
      throws IOException
    {
      if (paramAnonymousURI == null) {}
      for (String str = null;; str = paramAnonymousURI.toASCIIString())
      {
        paramAnonymousc.b(str);
        return;
      }
    }
  };
  public static final x F = a(URI.class, E);
  public static final w<InetAddress> G = new w()
  {
    public InetAddress a(com.google.ads.interactivemedia.v3.a.d.a paramAnonymousa)
      throws IOException
    {
      if (paramAnonymousa.f() == com.google.ads.interactivemedia.v3.a.d.b.i)
      {
        paramAnonymousa.j();
        return null;
      }
      return InetAddress.getByName(paramAnonymousa.h());
    }
    
    public void a(c paramAnonymousc, InetAddress paramAnonymousInetAddress)
      throws IOException
    {
      if (paramAnonymousInetAddress == null) {}
      for (String str = null;; str = paramAnonymousInetAddress.getHostAddress())
      {
        paramAnonymousc.b(str);
        return;
      }
    }
  };
  public static final x H = b(InetAddress.class, G);
  public static final w<UUID> I = new w()
  {
    public UUID a(com.google.ads.interactivemedia.v3.a.d.a paramAnonymousa)
      throws IOException
    {
      if (paramAnonymousa.f() == com.google.ads.interactivemedia.v3.a.d.b.i)
      {
        paramAnonymousa.j();
        return null;
      }
      return UUID.fromString(paramAnonymousa.h());
    }
    
    public void a(c paramAnonymousc, UUID paramAnonymousUUID)
      throws IOException
    {
      if (paramAnonymousUUID == null) {}
      for (String str = null;; str = paramAnonymousUUID.toString())
      {
        paramAnonymousc.b(str);
        return;
      }
    }
  };
  public static final x J = a(UUID.class, I);
  public static final x K = new x()
  {
    public <T> w<T> a(com.google.ads.interactivemedia.v3.a.f paramAnonymousf, com.google.ads.interactivemedia.v3.a.c.a<T> paramAnonymousa)
    {
      if (paramAnonymousa.a() != Timestamp.class) {
        return null;
      }
      new w()
      {
        public Timestamp a(com.google.ads.interactivemedia.v3.a.d.a paramAnonymous2a)
          throws IOException
        {
          Date localDate = (Date)this.a.b(paramAnonymous2a);
          if (localDate != null) {
            return new Timestamp(localDate.getTime());
          }
          return null;
        }
        
        public void a(c paramAnonymous2c, Timestamp paramAnonymous2Timestamp)
          throws IOException
        {
          this.a.a(paramAnonymous2c, paramAnonymous2Timestamp);
        }
      };
    }
  };
  public static final w<Calendar> L = new w()
  {
    public Calendar a(com.google.ads.interactivemedia.v3.a.d.a paramAnonymousa)
      throws IOException
    {
      int i = 0;
      if (paramAnonymousa.f() == com.google.ads.interactivemedia.v3.a.d.b.i)
      {
        paramAnonymousa.j();
        return null;
      }
      paramAnonymousa.c();
      int j = 0;
      int k = 0;
      int m = 0;
      int n = 0;
      int i1 = 0;
      while (paramAnonymousa.f() != com.google.ads.interactivemedia.v3.a.d.b.d)
      {
        String str = paramAnonymousa.g();
        int i2 = paramAnonymousa.m();
        if ("year".equals(str)) {
          i1 = i2;
        } else if ("month".equals(str)) {
          n = i2;
        } else if ("dayOfMonth".equals(str)) {
          m = i2;
        } else if ("hourOfDay".equals(str)) {
          k = i2;
        } else if ("minute".equals(str)) {
          j = i2;
        } else if ("second".equals(str)) {
          i = i2;
        }
      }
      paramAnonymousa.d();
      return new GregorianCalendar(i1, n, m, k, j, i);
    }
    
    public void a(c paramAnonymousc, Calendar paramAnonymousCalendar)
      throws IOException
    {
      if (paramAnonymousCalendar == null)
      {
        paramAnonymousc.f();
        return;
      }
      paramAnonymousc.d();
      paramAnonymousc.a("year");
      paramAnonymousc.a(paramAnonymousCalendar.get(1));
      paramAnonymousc.a("month");
      paramAnonymousc.a(paramAnonymousCalendar.get(2));
      paramAnonymousc.a("dayOfMonth");
      paramAnonymousc.a(paramAnonymousCalendar.get(5));
      paramAnonymousc.a("hourOfDay");
      paramAnonymousc.a(paramAnonymousCalendar.get(11));
      paramAnonymousc.a("minute");
      paramAnonymousc.a(paramAnonymousCalendar.get(12));
      paramAnonymousc.a("second");
      paramAnonymousc.a(paramAnonymousCalendar.get(13));
      paramAnonymousc.e();
    }
  };
  public static final x M = b(Calendar.class, GregorianCalendar.class, L);
  public static final w<Locale> N = new w()
  {
    public Locale a(com.google.ads.interactivemedia.v3.a.d.a paramAnonymousa)
      throws IOException
    {
      if (paramAnonymousa.f() == com.google.ads.interactivemedia.v3.a.d.b.i)
      {
        paramAnonymousa.j();
        return null;
      }
      StringTokenizer localStringTokenizer = new StringTokenizer(paramAnonymousa.h(), "_");
      if (localStringTokenizer.hasMoreElements()) {}
      for (String str1 = localStringTokenizer.nextToken();; str1 = null)
      {
        if (localStringTokenizer.hasMoreElements()) {}
        for (String str2 = localStringTokenizer.nextToken();; str2 = null)
        {
          if (localStringTokenizer.hasMoreElements()) {}
          for (String str3 = localStringTokenizer.nextToken();; str3 = null)
          {
            if ((str2 == null) && (str3 == null)) {
              return new Locale(str1);
            }
            if (str3 == null) {
              return new Locale(str1, str2);
            }
            return new Locale(str1, str2, str3);
          }
        }
      }
    }
    
    public void a(c paramAnonymousc, Locale paramAnonymousLocale)
      throws IOException
    {
      if (paramAnonymousLocale == null) {}
      for (String str = null;; str = paramAnonymousLocale.toString())
      {
        paramAnonymousc.b(str);
        return;
      }
    }
  };
  public static final x O = a(Locale.class, N);
  public static final w<com.google.ads.interactivemedia.v3.a.l> P = new w()
  {
    public com.google.ads.interactivemedia.v3.a.l a(com.google.ads.interactivemedia.v3.a.d.a paramAnonymousa)
      throws IOException
    {
      switch (l.26.a[paramAnonymousa.f().ordinal()])
      {
      default: 
        throw new IllegalArgumentException();
      case 3: 
        return new q(paramAnonymousa.h());
      case 1: 
        return new q(new com.google.ads.interactivemedia.v3.a.b.f(paramAnonymousa.h()));
      case 2: 
        return new q(Boolean.valueOf(paramAnonymousa.i()));
      case 4: 
        paramAnonymousa.j();
        return n.a;
      case 5: 
        i locali = new i();
        paramAnonymousa.a();
        while (paramAnonymousa.e()) {
          locali.a(a(paramAnonymousa));
        }
        paramAnonymousa.b();
        return locali;
      }
      o localo = new o();
      paramAnonymousa.c();
      while (paramAnonymousa.e()) {
        localo.a(paramAnonymousa.g(), a(paramAnonymousa));
      }
      paramAnonymousa.d();
      return localo;
    }
    
    public void a(c paramAnonymousc, com.google.ads.interactivemedia.v3.a.l paramAnonymousl)
      throws IOException
    {
      if ((paramAnonymousl == null) || (paramAnonymousl.j()))
      {
        paramAnonymousc.f();
        return;
      }
      if (paramAnonymousl.i())
      {
        q localq = paramAnonymousl.m();
        if (localq.p())
        {
          paramAnonymousc.a(localq.a());
          return;
        }
        if (localq.o())
        {
          paramAnonymousc.a(localq.f());
          return;
        }
        paramAnonymousc.b(localq.b());
        return;
      }
      if (paramAnonymousl.g())
      {
        paramAnonymousc.b();
        Iterator localIterator2 = paramAnonymousl.l().iterator();
        while (localIterator2.hasNext()) {
          a(paramAnonymousc, (com.google.ads.interactivemedia.v3.a.l)localIterator2.next());
        }
        paramAnonymousc.c();
        return;
      }
      if (paramAnonymousl.h())
      {
        paramAnonymousc.d();
        Iterator localIterator1 = paramAnonymousl.k().o().iterator();
        while (localIterator1.hasNext())
        {
          Map.Entry localEntry = (Map.Entry)localIterator1.next();
          paramAnonymousc.a((String)localEntry.getKey());
          a(paramAnonymousc, (com.google.ads.interactivemedia.v3.a.l)localEntry.getValue());
        }
        paramAnonymousc.e();
        return;
      }
      throw new IllegalArgumentException("Couldn't write " + paramAnonymousl.getClass());
    }
  };
  public static final x Q = b(com.google.ads.interactivemedia.v3.a.l.class, P);
  public static final x R = a();
  public static final w<Class> a = new w()
  {
    public Class a(com.google.ads.interactivemedia.v3.a.d.a paramAnonymousa)
      throws IOException
    {
      if (paramAnonymousa.f() == com.google.ads.interactivemedia.v3.a.d.b.i)
      {
        paramAnonymousa.j();
        return null;
      }
      throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?");
    }
    
    public void a(c paramAnonymousc, Class paramAnonymousClass)
      throws IOException
    {
      if (paramAnonymousClass == null)
      {
        paramAnonymousc.f();
        return;
      }
      throw new UnsupportedOperationException("Attempted to serialize java.lang.Class: " + paramAnonymousClass.getName() + ". Forgot to register a type adapter?");
    }
  };
  public static final x b = a(Class.class, a);
  public static final w<BitSet> c = new w()
  {
    public BitSet a(com.google.ads.interactivemedia.v3.a.d.a paramAnonymousa)
      throws IOException
    {
      if (paramAnonymousa.f() == com.google.ads.interactivemedia.v3.a.d.b.i)
      {
        paramAnonymousa.j();
        return null;
      }
      BitSet localBitSet = new BitSet();
      paramAnonymousa.a();
      com.google.ads.interactivemedia.v3.a.d.b localb = paramAnonymousa.f();
      int i = 0;
      if (localb != com.google.ads.interactivemedia.v3.a.d.b.b)
      {
        boolean bool;
        switch (l.26.a[localb.ordinal()])
        {
        default: 
          throw new t("Invalid bitset value type: " + localb);
        case 1: 
          if (paramAnonymousa.m() != 0) {
            bool = true;
          }
          break;
        }
        for (;;)
        {
          if (bool) {
            localBitSet.set(i);
          }
          i++;
          localb = paramAnonymousa.f();
          break;
          bool = false;
          continue;
          bool = paramAnonymousa.i();
          continue;
          String str = paramAnonymousa.h();
          try
          {
            int j = Integer.parseInt(str);
            if (j != 0) {
              bool = true;
            } else {
              bool = false;
            }
          }
          catch (NumberFormatException localNumberFormatException)
          {
            throw new t("Error: Expecting: bitset number value (1, 0), Found: " + str);
          }
        }
      }
      paramAnonymousa.b();
      return localBitSet;
    }
    
    public void a(c paramAnonymousc, BitSet paramAnonymousBitSet)
      throws IOException
    {
      if (paramAnonymousBitSet == null)
      {
        paramAnonymousc.f();
        return;
      }
      paramAnonymousc.b();
      int i = 0;
      if (i < paramAnonymousBitSet.length())
      {
        if (paramAnonymousBitSet.get(i)) {}
        for (int j = 1;; j = 0)
        {
          paramAnonymousc.a(j);
          i++;
          break;
        }
      }
      paramAnonymousc.c();
    }
  };
  public static final x d = a(BitSet.class, c);
  public static final w<Boolean> e = new w()
  {
    public Boolean a(com.google.ads.interactivemedia.v3.a.d.a paramAnonymousa)
      throws IOException
    {
      if (paramAnonymousa.f() == com.google.ads.interactivemedia.v3.a.d.b.i)
      {
        paramAnonymousa.j();
        return null;
      }
      if (paramAnonymousa.f() == com.google.ads.interactivemedia.v3.a.d.b.f) {
        return Boolean.valueOf(Boolean.parseBoolean(paramAnonymousa.h()));
      }
      return Boolean.valueOf(paramAnonymousa.i());
    }
    
    public void a(c paramAnonymousc, Boolean paramAnonymousBoolean)
      throws IOException
    {
      if (paramAnonymousBoolean == null)
      {
        paramAnonymousc.f();
        return;
      }
      paramAnonymousc.a(paramAnonymousBoolean.booleanValue());
    }
  };
  public static final w<Boolean> f = new w()
  {
    public Boolean a(com.google.ads.interactivemedia.v3.a.d.a paramAnonymousa)
      throws IOException
    {
      if (paramAnonymousa.f() == com.google.ads.interactivemedia.v3.a.d.b.i)
      {
        paramAnonymousa.j();
        return null;
      }
      return Boolean.valueOf(paramAnonymousa.h());
    }
    
    public void a(c paramAnonymousc, Boolean paramAnonymousBoolean)
      throws IOException
    {
      if (paramAnonymousBoolean == null) {}
      for (String str = "null";; str = paramAnonymousBoolean.toString())
      {
        paramAnonymousc.b(str);
        return;
      }
    }
  };
  public static final x g = a(Boolean.TYPE, Boolean.class, e);
  public static final w<Number> h = new w()
  {
    public Number a(com.google.ads.interactivemedia.v3.a.d.a paramAnonymousa)
      throws IOException
    {
      if (paramAnonymousa.f() == com.google.ads.interactivemedia.v3.a.d.b.i)
      {
        paramAnonymousa.j();
        return null;
      }
      try
      {
        Byte localByte = Byte.valueOf((byte)paramAnonymousa.m());
        return localByte;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw new t(localNumberFormatException);
      }
    }
    
    public void a(c paramAnonymousc, Number paramAnonymousNumber)
      throws IOException
    {
      paramAnonymousc.a(paramAnonymousNumber);
    }
  };
  public static final x i = a(Byte.TYPE, Byte.class, h);
  public static final w<Number> j = new w()
  {
    public Number a(com.google.ads.interactivemedia.v3.a.d.a paramAnonymousa)
      throws IOException
    {
      if (paramAnonymousa.f() == com.google.ads.interactivemedia.v3.a.d.b.i)
      {
        paramAnonymousa.j();
        return null;
      }
      try
      {
        Short localShort = Short.valueOf((short)paramAnonymousa.m());
        return localShort;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw new t(localNumberFormatException);
      }
    }
    
    public void a(c paramAnonymousc, Number paramAnonymousNumber)
      throws IOException
    {
      paramAnonymousc.a(paramAnonymousNumber);
    }
  };
  public static final x k = a(Short.TYPE, Short.class, j);
  public static final w<Number> l = new w()
  {
    public Number a(com.google.ads.interactivemedia.v3.a.d.a paramAnonymousa)
      throws IOException
    {
      if (paramAnonymousa.f() == com.google.ads.interactivemedia.v3.a.d.b.i)
      {
        paramAnonymousa.j();
        return null;
      }
      try
      {
        Integer localInteger = Integer.valueOf(paramAnonymousa.m());
        return localInteger;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw new t(localNumberFormatException);
      }
    }
    
    public void a(c paramAnonymousc, Number paramAnonymousNumber)
      throws IOException
    {
      paramAnonymousc.a(paramAnonymousNumber);
    }
  };
  public static final x m = a(Integer.TYPE, Integer.class, l);
  public static final w<Number> n = new w()
  {
    public Number a(com.google.ads.interactivemedia.v3.a.d.a paramAnonymousa)
      throws IOException
    {
      if (paramAnonymousa.f() == com.google.ads.interactivemedia.v3.a.d.b.i)
      {
        paramAnonymousa.j();
        return null;
      }
      try
      {
        Long localLong = Long.valueOf(paramAnonymousa.l());
        return localLong;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw new t(localNumberFormatException);
      }
    }
    
    public void a(c paramAnonymousc, Number paramAnonymousNumber)
      throws IOException
    {
      paramAnonymousc.a(paramAnonymousNumber);
    }
  };
  public static final w<Number> o = new w()
  {
    public Number a(com.google.ads.interactivemedia.v3.a.d.a paramAnonymousa)
      throws IOException
    {
      if (paramAnonymousa.f() == com.google.ads.interactivemedia.v3.a.d.b.i)
      {
        paramAnonymousa.j();
        return null;
      }
      return Float.valueOf((float)paramAnonymousa.k());
    }
    
    public void a(c paramAnonymousc, Number paramAnonymousNumber)
      throws IOException
    {
      paramAnonymousc.a(paramAnonymousNumber);
    }
  };
  public static final w<Number> p = new w()
  {
    public Number a(com.google.ads.interactivemedia.v3.a.d.a paramAnonymousa)
      throws IOException
    {
      if (paramAnonymousa.f() == com.google.ads.interactivemedia.v3.a.d.b.i)
      {
        paramAnonymousa.j();
        return null;
      }
      return Double.valueOf(paramAnonymousa.k());
    }
    
    public void a(c paramAnonymousc, Number paramAnonymousNumber)
      throws IOException
    {
      paramAnonymousc.a(paramAnonymousNumber);
    }
  };
  public static final w<Number> q = new w()
  {
    public Number a(com.google.ads.interactivemedia.v3.a.d.a paramAnonymousa)
      throws IOException
    {
      com.google.ads.interactivemedia.v3.a.d.b localb = paramAnonymousa.f();
      switch (l.26.a[localb.ordinal()])
      {
      case 2: 
      case 3: 
      default: 
        throw new t("Expecting number, got: " + localb);
      case 4: 
        paramAnonymousa.j();
        return null;
      }
      return new com.google.ads.interactivemedia.v3.a.b.f(paramAnonymousa.h());
    }
    
    public void a(c paramAnonymousc, Number paramAnonymousNumber)
      throws IOException
    {
      paramAnonymousc.a(paramAnonymousNumber);
    }
  };
  public static final x r = a(Number.class, q);
  public static final w<Character> s = new w()
  {
    public Character a(com.google.ads.interactivemedia.v3.a.d.a paramAnonymousa)
      throws IOException
    {
      if (paramAnonymousa.f() == com.google.ads.interactivemedia.v3.a.d.b.i)
      {
        paramAnonymousa.j();
        return null;
      }
      String str = paramAnonymousa.h();
      if (str.length() != 1) {
        throw new t("Expecting character, got: " + str);
      }
      return Character.valueOf(str.charAt(0));
    }
    
    public void a(c paramAnonymousc, Character paramAnonymousCharacter)
      throws IOException
    {
      if (paramAnonymousCharacter == null) {}
      for (String str = null;; str = String.valueOf(paramAnonymousCharacter))
      {
        paramAnonymousc.b(str);
        return;
      }
    }
  };
  public static final x t = a(Character.TYPE, Character.class, s);
  public static final w<String> u = new w()
  {
    public String a(com.google.ads.interactivemedia.v3.a.d.a paramAnonymousa)
      throws IOException
    {
      com.google.ads.interactivemedia.v3.a.d.b localb = paramAnonymousa.f();
      if (localb == com.google.ads.interactivemedia.v3.a.d.b.i)
      {
        paramAnonymousa.j();
        return null;
      }
      if (localb == com.google.ads.interactivemedia.v3.a.d.b.h) {
        return Boolean.toString(paramAnonymousa.i());
      }
      return paramAnonymousa.h();
    }
    
    public void a(c paramAnonymousc, String paramAnonymousString)
      throws IOException
    {
      paramAnonymousc.b(paramAnonymousString);
    }
  };
  public static final w<BigDecimal> v = new w()
  {
    public BigDecimal a(com.google.ads.interactivemedia.v3.a.d.a paramAnonymousa)
      throws IOException
    {
      if (paramAnonymousa.f() == com.google.ads.interactivemedia.v3.a.d.b.i)
      {
        paramAnonymousa.j();
        return null;
      }
      try
      {
        BigDecimal localBigDecimal = new BigDecimal(paramAnonymousa.h());
        return localBigDecimal;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw new t(localNumberFormatException);
      }
    }
    
    public void a(c paramAnonymousc, BigDecimal paramAnonymousBigDecimal)
      throws IOException
    {
      paramAnonymousc.a(paramAnonymousBigDecimal);
    }
  };
  public static final w<BigInteger> w = new w()
  {
    public BigInteger a(com.google.ads.interactivemedia.v3.a.d.a paramAnonymousa)
      throws IOException
    {
      if (paramAnonymousa.f() == com.google.ads.interactivemedia.v3.a.d.b.i)
      {
        paramAnonymousa.j();
        return null;
      }
      try
      {
        BigInteger localBigInteger = new BigInteger(paramAnonymousa.h());
        return localBigInteger;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw new t(localNumberFormatException);
      }
    }
    
    public void a(c paramAnonymousc, BigInteger paramAnonymousBigInteger)
      throws IOException
    {
      paramAnonymousc.a(paramAnonymousBigInteger);
    }
  };
  public static final x x = a(String.class, u);
  public static final w<StringBuilder> y = new w()
  {
    public StringBuilder a(com.google.ads.interactivemedia.v3.a.d.a paramAnonymousa)
      throws IOException
    {
      if (paramAnonymousa.f() == com.google.ads.interactivemedia.v3.a.d.b.i)
      {
        paramAnonymousa.j();
        return null;
      }
      return new StringBuilder(paramAnonymousa.h());
    }
    
    public void a(c paramAnonymousc, StringBuilder paramAnonymousStringBuilder)
      throws IOException
    {
      if (paramAnonymousStringBuilder == null) {}
      for (String str = null;; str = paramAnonymousStringBuilder.toString())
      {
        paramAnonymousc.b(str);
        return;
      }
    }
  };
  public static final x z = a(StringBuilder.class, y);
  
  public static x a()
  {
    new x()
    {
      public <T> w<T> a(com.google.ads.interactivemedia.v3.a.f paramAnonymousf, com.google.ads.interactivemedia.v3.a.c.a<T> paramAnonymousa)
      {
        Class localClass = paramAnonymousa.a();
        if ((!Enum.class.isAssignableFrom(localClass)) || (localClass == Enum.class)) {
          return null;
        }
        if (!localClass.isEnum()) {
          localClass = localClass.getSuperclass();
        }
        return new l.a(localClass);
      }
    };
  }
  
  public static <TT> x a(com.google.ads.interactivemedia.v3.a.c.a<TT> parama, final w<TT> paramw)
  {
    new x()
    {
      public <T> w<T> a(com.google.ads.interactivemedia.v3.a.f paramAnonymousf, com.google.ads.interactivemedia.v3.a.c.a<T> paramAnonymousa)
      {
        if (paramAnonymousa.equals(this.a)) {
          return paramw;
        }
        return null;
      }
    };
  }
  
  public static <TT> x a(Class<TT> paramClass, final w<TT> paramw)
  {
    new x()
    {
      public <T> w<T> a(com.google.ads.interactivemedia.v3.a.f paramAnonymousf, com.google.ads.interactivemedia.v3.a.c.a<T> paramAnonymousa)
      {
        if (paramAnonymousa.a() == this.a) {
          return paramw;
        }
        return null;
      }
      
      public String toString()
      {
        return "Factory[type=" + this.a.getName() + ",adapter=" + paramw + "]";
      }
    };
  }
  
  public static <TT> x a(Class<TT> paramClass1, final Class<TT> paramClass2, final w<? super TT> paramw)
  {
    new x()
    {
      public <T> w<T> a(com.google.ads.interactivemedia.v3.a.f paramAnonymousf, com.google.ads.interactivemedia.v3.a.c.a<T> paramAnonymousa)
      {
        Class localClass = paramAnonymousa.a();
        if ((localClass == this.a) || (localClass == paramClass2)) {
          return paramw;
        }
        return null;
      }
      
      public String toString()
      {
        return "Factory[type=" + paramClass2.getName() + "+" + this.a.getName() + ",adapter=" + paramw + "]";
      }
    };
  }
  
  public static <TT> x b(Class<TT> paramClass, final w<TT> paramw)
  {
    new x()
    {
      public <T> w<T> a(com.google.ads.interactivemedia.v3.a.f paramAnonymousf, com.google.ads.interactivemedia.v3.a.c.a<T> paramAnonymousa)
      {
        if (this.a.isAssignableFrom(paramAnonymousa.a())) {
          return paramw;
        }
        return null;
      }
      
      public String toString()
      {
        return "Factory[typeHierarchy=" + this.a.getName() + ",adapter=" + paramw + "]";
      }
    };
  }
  
  public static <TT> x b(Class<TT> paramClass, final Class<? extends TT> paramClass1, final w<? super TT> paramw)
  {
    new x()
    {
      public <T> w<T> a(com.google.ads.interactivemedia.v3.a.f paramAnonymousf, com.google.ads.interactivemedia.v3.a.c.a<T> paramAnonymousa)
      {
        Class localClass = paramAnonymousa.a();
        if ((localClass == this.a) || (localClass == paramClass1)) {
          return paramw;
        }
        return null;
      }
      
      public String toString()
      {
        return "Factory[type=" + this.a.getName() + "+" + paramClass1.getName() + ",adapter=" + paramw + "]";
      }
    };
  }
  
  private static final class a<T extends Enum<T>>
    extends w<T>
  {
    private final Map<String, T> a = new HashMap();
    private final Map<T, String> b = new HashMap();
    
    public a(Class<T> paramClass)
    {
      for (;;)
      {
        String str1;
        try
        {
          Enum[] arrayOfEnum = (Enum[])paramClass.getEnumConstants();
          int i = arrayOfEnum.length;
          int j = 0;
          if (j < i)
          {
            Enum localEnum = arrayOfEnum[j];
            str1 = localEnum.name();
            com.google.ads.interactivemedia.v3.a.a.b localb = (com.google.ads.interactivemedia.v3.a.a.b)paramClass.getField(str1).getAnnotation(com.google.ads.interactivemedia.v3.a.a.b.class);
            if (localb != null)
            {
              str2 = localb.a();
              this.a.put(str2, localEnum);
              this.b.put(localEnum, str2);
              j++;
            }
          }
          else
          {
            return;
          }
        }
        catch (NoSuchFieldException localNoSuchFieldException)
        {
          throw new AssertionError();
        }
        String str2 = str1;
      }
    }
    
    public T a(com.google.ads.interactivemedia.v3.a.d.a parama)
      throws IOException
    {
      if (parama.f() == com.google.ads.interactivemedia.v3.a.d.b.i)
      {
        parama.j();
        return null;
      }
      return (Enum)this.a.get(parama.h());
    }
    
    public void a(c paramc, T paramT)
      throws IOException
    {
      if (paramT == null) {}
      for (String str = null;; str = (String)this.b.get(paramT))
      {
        paramc.b(str);
        return;
      }
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.a.b.a.l
 * JD-Core Version:    0.7.0.1
 */