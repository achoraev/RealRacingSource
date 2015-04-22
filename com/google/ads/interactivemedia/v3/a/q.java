package com.google.ads.interactivemedia.v3.a;

import com.google.ads.interactivemedia.v3.a.b.a;
import com.google.ads.interactivemedia.v3.a.b.f;
import java.math.BigInteger;

public final class q
  extends l
{
  private static final Class<?>[] a;
  private Object b;
  
  static
  {
    Class[] arrayOfClass = new Class[16];
    arrayOfClass[0] = Integer.TYPE;
    arrayOfClass[1] = Long.TYPE;
    arrayOfClass[2] = Short.TYPE;
    arrayOfClass[3] = Float.TYPE;
    arrayOfClass[4] = Double.TYPE;
    arrayOfClass[5] = Byte.TYPE;
    arrayOfClass[6] = Boolean.TYPE;
    arrayOfClass[7] = Character.TYPE;
    arrayOfClass[8] = Integer.class;
    arrayOfClass[9] = Long.class;
    arrayOfClass[10] = Short.class;
    arrayOfClass[11] = Float.class;
    arrayOfClass[12] = Double.class;
    arrayOfClass[13] = Byte.class;
    arrayOfClass[14] = Boolean.class;
    arrayOfClass[15] = Character.class;
    a = arrayOfClass;
  }
  
  public q(Boolean paramBoolean)
  {
    a(paramBoolean);
  }
  
  public q(Number paramNumber)
  {
    a(paramNumber);
  }
  
  public q(String paramString)
  {
    a(paramString);
  }
  
  private static boolean a(q paramq)
  {
    if ((paramq.b instanceof Number))
    {
      Number localNumber = (Number)paramq.b;
      return ((localNumber instanceof BigInteger)) || ((localNumber instanceof Long)) || ((localNumber instanceof Integer)) || ((localNumber instanceof Short)) || ((localNumber instanceof Byte));
    }
    return false;
  }
  
  private static boolean b(Object paramObject)
  {
    if ((paramObject instanceof String)) {
      return true;
    }
    Class localClass = paramObject.getClass();
    Class[] arrayOfClass = a;
    int i = arrayOfClass.length;
    for (int j = 0;; j++)
    {
      if (j >= i) {
        break label47;
      }
      if (arrayOfClass[j].isAssignableFrom(localClass)) {
        break;
      }
    }
    label47:
    return false;
  }
  
  public Number a()
  {
    if ((this.b instanceof String)) {
      return new f((String)this.b);
    }
    return (Number)this.b;
  }
  
  void a(Object paramObject)
  {
    if ((paramObject instanceof Character))
    {
      this.b = String.valueOf(((Character)paramObject).charValue());
      return;
    }
    if (((paramObject instanceof Number)) || (b(paramObject))) {}
    for (boolean bool = true;; bool = false)
    {
      a.a(bool);
      this.b = paramObject;
      return;
    }
  }
  
  public String b()
  {
    if (p()) {
      return a().toString();
    }
    if (o()) {
      return n().toString();
    }
    return (String)this.b;
  }
  
  public double c()
  {
    if (p()) {
      return a().doubleValue();
    }
    return Double.parseDouble(b());
  }
  
  public long d()
  {
    if (p()) {
      return a().longValue();
    }
    return Long.parseLong(b());
  }
  
  public int e()
  {
    if (p()) {
      return a().intValue();
    }
    return Integer.parseInt(b());
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    q localq;
    do
    {
      do
      {
        return true;
        if ((paramObject == null) || (getClass() != paramObject.getClass())) {
          return false;
        }
        localq = (q)paramObject;
        if (this.b != null) {
          break;
        }
      } while (localq.b == null);
      return false;
      if ((!a(this)) || (!a(localq))) {
        break;
      }
    } while (a().longValue() == localq.a().longValue());
    return false;
    if (((this.b instanceof Number)) && ((localq.b instanceof Number)))
    {
      double d1 = a().doubleValue();
      double d2 = localq.a().doubleValue();
      boolean bool1;
      if (d1 != d2)
      {
        boolean bool2 = Double.isNaN(d1);
        bool1 = false;
        if (bool2)
        {
          boolean bool3 = Double.isNaN(d2);
          bool1 = false;
          if (!bool3) {}
        }
      }
      else
      {
        bool1 = true;
      }
      return bool1;
    }
    return this.b.equals(localq.b);
  }
  
  public boolean f()
  {
    if (o()) {
      return n().booleanValue();
    }
    return Boolean.parseBoolean(b());
  }
  
  public int hashCode()
  {
    if (this.b == null) {
      return 31;
    }
    if (a(this))
    {
      long l2 = a().longValue();
      return (int)(l2 ^ l2 >>> 32);
    }
    if ((this.b instanceof Number))
    {
      long l1 = Double.doubleToLongBits(a().doubleValue());
      return (int)(l1 ^ l1 >>> 32);
    }
    return this.b.hashCode();
  }
  
  Boolean n()
  {
    return (Boolean)this.b;
  }
  
  public boolean o()
  {
    return this.b instanceof Boolean;
  }
  
  public boolean p()
  {
    return this.b instanceof Number;
  }
  
  public boolean q()
  {
    return this.b instanceof String;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.a.q
 * JD-Core Version:    0.7.0.1
 */