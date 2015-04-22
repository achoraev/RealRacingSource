package com.google.ads.interactivemedia.v3.a.b.a;

import com.google.ads.interactivemedia.v3.a.d.b;
import com.google.ads.interactivemedia.v3.a.f;
import com.google.ads.interactivemedia.v3.a.t;
import com.google.ads.interactivemedia.v3.a.w;
import com.google.ads.interactivemedia.v3.a.x;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public final class c
  extends w<Date>
{
  public static final x a = new x()
  {
    public <T> w<T> a(f paramAnonymousf, com.google.ads.interactivemedia.v3.a.c.a<T> paramAnonymousa)
    {
      if (paramAnonymousa.a() == Date.class) {
        return new c();
      }
      return null;
    }
  };
  private final DateFormat b = DateFormat.getDateTimeInstance(2, 2, Locale.US);
  private final DateFormat c = DateFormat.getDateTimeInstance(2, 2);
  private final DateFormat d = a();
  
  private static DateFormat a()
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
    localSimpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    return localSimpleDateFormat;
  }
  
  private Date a(String paramString)
  {
    try
    {
      Date localDate3 = this.c.parse(paramString);
      localObject2 = localDate3;
    }
    catch (ParseException localParseException1)
    {
      try
      {
        Date localDate2 = this.b.parse(paramString);
        localObject2 = localDate2;
      }
      catch (ParseException localParseException2)
      {
        try
        {
          Date localDate1 = this.d.parse(paramString);
          Object localObject2 = localDate1;
        }
        catch (ParseException localParseException3)
        {
          throw new t(paramString, localParseException3);
        }
      }
    }
    finally {}
    return localObject2;
  }
  
  public Date a(com.google.ads.interactivemedia.v3.a.d.a parama)
    throws IOException
  {
    if (parama.f() == b.i)
    {
      parama.j();
      return null;
    }
    return a(parama.h());
  }
  
  public void a(com.google.ads.interactivemedia.v3.a.d.c paramc, Date paramDate)
    throws IOException
  {
    if (paramDate == null) {}
    for (;;)
    {
      try
      {
        paramc.f();
        return;
      }
      finally {}
      paramc.b(this.b.format(paramDate));
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.a.b.a.c
 * JD-Core Version:    0.7.0.1
 */