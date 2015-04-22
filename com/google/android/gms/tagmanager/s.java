package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d.a;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class s
  extends aj
{
  private static final String ID = a.L.toString();
  private static final String anV = b.bl.toString();
  private static final String aoE = b.cV.toString();
  private final a aoF;
  
  public s(a parama)
  {
    super(str, arrayOfString);
    this.aoF = parama;
  }
  
  public d.a C(Map<String, d.a> paramMap)
  {
    String str = di.j((d.a)paramMap.get(aoE));
    HashMap localHashMap = new HashMap();
    d.a locala1 = (d.a)paramMap.get(anV);
    if (locala1 != null)
    {
      Object localObject = di.o(locala1);
      if (!(localObject instanceof Map))
      {
        bh.W("FunctionCallMacro: expected ADDITIONAL_PARAMS to be a map.");
        return di.pK();
      }
      Iterator localIterator = ((Map)localObject).entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        localHashMap.put(localEntry.getKey().toString(), localEntry.getValue());
      }
    }
    try
    {
      d.a locala2 = di.u(this.aoF.b(str, localHashMap));
      return locala2;
    }
    catch (Exception localException)
    {
      bh.W("Custom macro/tag " + str + " threw exception " + localException.getMessage());
    }
    return di.pK();
  }
  
  public boolean nN()
  {
    return false;
  }
  
  public static abstract interface a
  {
    public abstract Object b(String paramString, Map<String, Object> paramMap);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.s
 * JD-Core Version:    0.7.0.1
 */