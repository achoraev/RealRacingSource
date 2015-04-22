package com.google.ads.interactivemedia.v3.a.b;

import java.math.BigDecimal;

public final class f
  extends Number
{
  private final String a;
  
  public f(String paramString)
  {
    this.a = paramString;
  }
  
  public double doubleValue()
  {
    return Double.parseDouble(this.a);
  }
  
  public float floatValue()
  {
    return Float.parseFloat(this.a);
  }
  
  public int intValue()
  {
    try
    {
      int i = Integer.parseInt(this.a);
      return i;
    }
    catch (NumberFormatException localNumberFormatException1)
    {
      try
      {
        long l = Long.parseLong(this.a);
        return (int)l;
      }
      catch (NumberFormatException localNumberFormatException2) {}
    }
    return new BigDecimal(this.a).intValue();
  }
  
  public long longValue()
  {
    try
    {
      long l = Long.parseLong(this.a);
      return l;
    }
    catch (NumberFormatException localNumberFormatException) {}
    return new BigDecimal(this.a).longValue();
  }
  
  public String toString()
  {
    return this.a;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.a.b.f
 * JD-Core Version:    0.7.0.1
 */