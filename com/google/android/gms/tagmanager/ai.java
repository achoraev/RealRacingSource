package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.c.c;
import com.google.android.gms.internal.c.d;
import com.google.android.gms.internal.c.i;
import com.google.android.gms.internal.d.a;
import java.util.Map;

class ai
{
  private static void a(DataLayer paramDataLayer, c.d paramd)
  {
    d.a[] arrayOfa = paramd.fB;
    int i = arrayOfa.length;
    for (int j = 0; j < i; j++) {
      paramDataLayer.cv(di.j(arrayOfa[j]));
    }
  }
  
  public static void a(DataLayer paramDataLayer, c.i parami)
  {
    if (parami.gq == null)
    {
      bh.W("supplemental missing experimentSupplemental");
      return;
    }
    a(paramDataLayer, parami.gq);
    b(paramDataLayer, parami.gq);
    c(paramDataLayer, parami.gq);
  }
  
  private static void b(DataLayer paramDataLayer, c.d paramd)
  {
    d.a[] arrayOfa = paramd.fA;
    int i = arrayOfa.length;
    for (int j = 0; j < i; j++)
    {
      Map localMap = c(arrayOfa[j]);
      if (localMap != null) {
        paramDataLayer.push(localMap);
      }
    }
  }
  
  private static Map<String, Object> c(d.a parama)
  {
    Object localObject = di.o(parama);
    if (!(localObject instanceof Map))
    {
      bh.W("value: " + localObject + " is not a map value, ignored.");
      return null;
    }
    return (Map)localObject;
  }
  
  private static void c(DataLayer paramDataLayer, c.d paramd)
  {
    c.c[] arrayOfc = paramd.fC;
    int i = arrayOfc.length;
    int j = 0;
    while (j < i)
    {
      c.c localc = arrayOfc[j];
      if (localc.fv == null)
      {
        bh.W("GaExperimentRandom: No key");
        j++;
      }
      else
      {
        Object localObject1 = paramDataLayer.get(localc.fv);
        Long localLong;
        label64:
        Map localMap;
        if (!(localObject1 instanceof Number))
        {
          localLong = null;
          long l1 = localc.fw;
          long l2 = localc.fx;
          if ((!localc.fy) || (localLong == null) || (localLong.longValue() < l1) || (localLong.longValue() > l2))
          {
            if (l1 > l2) {
              break label251;
            }
            localObject1 = Long.valueOf(Math.round(Math.random() * (l2 - l1) + l1));
          }
          paramDataLayer.cv(localc.fv);
          localMap = paramDataLayer.c(localc.fv, localObject1);
          if (localc.fz > 0L)
          {
            if (localMap.containsKey("gtm")) {
              break label259;
            }
            Object[] arrayOfObject = new Object[2];
            arrayOfObject[0] = "lifetime";
            arrayOfObject[1] = Long.valueOf(localc.fz);
            localMap.put("gtm", DataLayer.mapOf(arrayOfObject));
          }
        }
        for (;;)
        {
          paramDataLayer.push(localMap);
          break;
          localLong = Long.valueOf(((Number)localObject1).longValue());
          break label64;
          label251:
          bh.W("GaExperimentRandom: random range invalid");
          break;
          label259:
          Object localObject2 = localMap.get("gtm");
          if ((localObject2 instanceof Map)) {
            ((Map)localObject2).put("lifetime", Long.valueOf(localc.fz));
          } else {
            bh.W("GaExperimentRandom: gtm not a map");
          }
        }
      }
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.ai
 * JD-Core Version:    0.7.0.1
 */