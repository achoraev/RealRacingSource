package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.net.Uri.Builder;
import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d.a;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class i
  extends dg
{
  private static final String ID = a.ay.toString();
  private static final String URL = b.eX.toString();
  private static final String anV = b.bl.toString();
  private static final String anW = b.eW.toString();
  static final String anX = "gtm_" + ID + "_unrepeatable";
  private static final Set<String> anY = new HashSet();
  private final a anZ;
  private final Context mContext;
  
  public i(Context paramContext)
  {
    this(paramContext, new a()
    {
      public aq nO()
      {
        return y.X(i.this);
      }
    });
  }
  
  i(Context paramContext, a parama)
  {
    super(str, arrayOfString);
    this.anZ = parama;
    this.mContext = paramContext;
  }
  
  private boolean cj(String paramString)
  {
    boolean bool1 = true;
    for (;;)
    {
      try
      {
        boolean bool2 = cl(paramString);
        if (bool2) {
          return bool1;
        }
        if (ck(paramString)) {
          anY.add(paramString);
        } else {
          bool1 = false;
        }
      }
      finally {}
    }
  }
  
  public void E(Map<String, d.a> paramMap)
  {
    String str1;
    if (paramMap.get(anW) != null)
    {
      str1 = di.j((d.a)paramMap.get(anW));
      if ((str1 == null) || (!cj(str1))) {
        break label46;
      }
    }
    label46:
    do
    {
      return;
      str1 = null;
      break;
      Uri.Builder localBuilder = Uri.parse(di.j((d.a)paramMap.get(URL))).buildUpon();
      d.a locala = (d.a)paramMap.get(anV);
      if (locala != null)
      {
        Object localObject2 = di.o(locala);
        if (!(localObject2 instanceof List))
        {
          bh.T("ArbitraryPixel: additional params not a list: not sending partial hit: " + localBuilder.build().toString());
          return;
        }
        Iterator localIterator1 = ((List)localObject2).iterator();
        while (localIterator1.hasNext())
        {
          Object localObject3 = localIterator1.next();
          if (!(localObject3 instanceof Map))
          {
            bh.T("ArbitraryPixel: additional params contains non-map: not sending partial hit: " + localBuilder.build().toString());
            return;
          }
          Iterator localIterator2 = ((Map)localObject3).entrySet().iterator();
          while (localIterator2.hasNext())
          {
            Map.Entry localEntry = (Map.Entry)localIterator2.next();
            localBuilder.appendQueryParameter(localEntry.getKey().toString(), localEntry.getValue().toString());
          }
        }
      }
      String str2 = localBuilder.build().toString();
      this.anZ.nO().cz(str2);
      bh.V("ArbitraryPixel: url = " + str2);
    } while (str1 == null);
    try
    {
      anY.add(str1);
      cz.a(this.mContext, anX, str1, "true");
      return;
    }
    finally {}
  }
  
  boolean ck(String paramString)
  {
    return this.mContext.getSharedPreferences(anX, 0).contains(paramString);
  }
  
  boolean cl(String paramString)
  {
    return anY.contains(paramString);
  }
  
  public static abstract interface a
  {
    public abstract aq nO();
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.i
 * JD-Core Version:    0.7.0.1
 */