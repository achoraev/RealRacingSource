package com.google.ads.interactivemedia.v3.a;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

final class a
  implements k<java.util.Date>, s<java.util.Date>
{
  private final DateFormat a;
  private final DateFormat b;
  private final DateFormat c;
  
  a()
  {
    this(DateFormat.getDateTimeInstance(2, 2, Locale.US), DateFormat.getDateTimeInstance(2, 2));
  }
  
  public a(int paramInt1, int paramInt2)
  {
    this(DateFormat.getDateTimeInstance(paramInt1, paramInt2, Locale.US), DateFormat.getDateTimeInstance(paramInt1, paramInt2));
  }
  
  a(String paramString)
  {
    this(new SimpleDateFormat(paramString, Locale.US), new SimpleDateFormat(paramString));
  }
  
  a(DateFormat paramDateFormat1, DateFormat paramDateFormat2)
  {
    this.a = paramDateFormat1;
    this.b = paramDateFormat2;
    this.c = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
    this.c.setTimeZone(TimeZone.getTimeZone("UTC"));
  }
  
  private java.util.Date a(l paraml)
  {
    java.util.Date localDate2;
    synchronized (this.b)
    {
      try
      {
        java.util.Date localDate3 = this.b.parse(paraml.b());
        return localDate3;
      }
      catch (ParseException localParseException1) {}
    }
  }
  
  public l a(java.util.Date paramDate, Type paramType, r paramr)
  {
    synchronized (this.b)
    {
      q localq = new q(this.a.format(paramDate));
      return localq;
    }
  }
  
  public java.util.Date a(l paraml, Type paramType, j paramj)
    throws p
  {
    if (!(paraml instanceof q)) {
      throw new p("The date should be a string value");
    }
    java.util.Date localDate = a(paraml);
    if (paramType == java.util.Date.class) {
      return localDate;
    }
    if (paramType == Timestamp.class) {
      return new Timestamp(localDate.getTime());
    }
    if (paramType == java.sql.Date.class) {
      return new java.sql.Date(localDate.getTime());
    }
    throw new IllegalArgumentException(getClass() + " cannot deserialize to " + paramType);
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(a.class.getSimpleName());
    localStringBuilder.append('(').append(this.b.getClass().getSimpleName()).append(')');
    return localStringBuilder.toString();
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.a.a
 * JD-Core Version:    0.7.0.1
 */