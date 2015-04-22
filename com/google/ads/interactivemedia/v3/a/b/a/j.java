package com.google.ads.interactivemedia.v3.a.b.a;

import com.google.ads.interactivemedia.v3.a.c.a;
import com.google.ads.interactivemedia.v3.a.d.c;
import com.google.ads.interactivemedia.v3.a.f;
import com.google.ads.interactivemedia.v3.a.w;
import com.google.ads.interactivemedia.v3.a.x;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public final class j
  extends w<Time>
{
  public static final x a = new x()
  {
    public <T> w<T> a(f paramAnonymousf, a<T> paramAnonymousa)
    {
      if (paramAnonymousa.a() == Time.class) {
        return new j();
      }
      return null;
    }
  };
  private final DateFormat b = new SimpleDateFormat("hh:mm:ss a");
  
  /* Error */
  public Time a(com.google.ads.interactivemedia.v3.a.d.a parama)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: invokevirtual 39	com/google/ads/interactivemedia/v3/a/d/a:f	()Lcom/google/ads/interactivemedia/v3/a/d/b;
    //   6: getstatic 45	com/google/ads/interactivemedia/v3/a/d/b:i	Lcom/google/ads/interactivemedia/v3/a/d/b;
    //   9: if_acmpne +13 -> 22
    //   12: aload_1
    //   13: invokevirtual 48	com/google/ads/interactivemedia/v3/a/d/a:j	()V
    //   16: aconst_null
    //   17: astore_3
    //   18: aload_0
    //   19: monitorexit
    //   20: aload_3
    //   21: areturn
    //   22: new 50	java/sql/Time
    //   25: dup
    //   26: aload_0
    //   27: getfield 28	com/google/ads/interactivemedia/v3/a/b/a/j:b	Ljava/text/DateFormat;
    //   30: aload_1
    //   31: invokevirtual 54	com/google/ads/interactivemedia/v3/a/d/a:h	()Ljava/lang/String;
    //   34: invokevirtual 60	java/text/DateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
    //   37: invokevirtual 66	java/util/Date:getTime	()J
    //   40: invokespecial 69	java/sql/Time:<init>	(J)V
    //   43: astore_3
    //   44: goto -26 -> 18
    //   47: astore 4
    //   49: new 71	com/google/ads/interactivemedia/v3/a/t
    //   52: dup
    //   53: aload 4
    //   55: invokespecial 74	com/google/ads/interactivemedia/v3/a/t:<init>	(Ljava/lang/Throwable;)V
    //   58: athrow
    //   59: astore_2
    //   60: aload_0
    //   61: monitorexit
    //   62: aload_2
    //   63: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	64	0	this	j
    //   0	64	1	parama	com.google.ads.interactivemedia.v3.a.d.a
    //   59	4	2	localObject	Object
    //   17	27	3	localTime	Time
    //   47	7	4	localParseException	java.text.ParseException
    // Exception table:
    //   from	to	target	type
    //   22	44	47	java/text/ParseException
    //   2	16	59	finally
    //   22	44	59	finally
    //   49	59	59	finally
  }
  
  public void a(c paramc, Time paramTime)
    throws IOException
  {
    if (paramTime == null) {}
    String str;
    for (Object localObject2 = null;; localObject2 = str)
    {
      try
      {
        paramc.b((String)localObject2);
        return;
      }
      finally {}
      str = this.b.format(paramTime);
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.a.b.a.j
 * JD-Core Version:    0.7.0.1
 */