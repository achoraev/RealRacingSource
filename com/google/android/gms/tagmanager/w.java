package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d.a;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

class w
  extends dg
{
  private static final String ID = a.ah.toString();
  private static final String VALUE = b.ff.toString();
  private static final String apa = b.bS.toString();
  private final DataLayer aod;
  
  public w(DataLayer paramDataLayer)
  {
    super(str, arrayOfString);
    this.aod = paramDataLayer;
  }
  
  private void a(d.a parama)
  {
    if ((parama == null) || (parama == di.pE())) {}
    String str;
    do
    {
      return;
      str = di.j(parama);
    } while (str == di.pJ());
    this.aod.cv(str);
  }
  
  private void b(d.a parama)
  {
    if ((parama == null) || (parama == di.pE())) {}
    for (;;)
    {
      return;
      Object localObject1 = di.o(parama);
      if ((localObject1 instanceof List))
      {
        Iterator localIterator = ((List)localObject1).iterator();
        while (localIterator.hasNext())
        {
          Object localObject2 = localIterator.next();
          if ((localObject2 instanceof Map))
          {
            Map localMap = (Map)localObject2;
            this.aod.push(localMap);
          }
        }
      }
    }
  }
  
  public void E(Map<String, d.a> paramMap)
  {
    b((d.a)paramMap.get(VALUE));
    a((d.a)paramMap.get(apa));
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.w
 * JD-Core Version:    0.7.0.1
 */