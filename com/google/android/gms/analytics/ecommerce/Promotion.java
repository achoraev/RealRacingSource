package com.google.android.gms.analytics.ecommerce;

import com.google.android.gms.common.internal.o;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Promotion
{
  public static final String ACTION_CLICK = "click";
  public static final String ACTION_VIEW = "view";
  Map<String, String> BK = new HashMap();
  
  public Map<String, String> aq(String paramString)
  {
    HashMap localHashMap = new HashMap();
    Iterator localIterator = this.BK.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      localHashMap.put(paramString + (String)localEntry.getKey(), localEntry.getValue());
    }
    return localHashMap;
  }
  
  void put(String paramString1, String paramString2)
  {
    o.b(paramString1, "Name should be non-null");
    this.BK.put(paramString1, paramString2);
  }
  
  public Promotion setCreative(String paramString)
  {
    put("cr", paramString);
    return this;
  }
  
  public Promotion setId(String paramString)
  {
    put("id", paramString);
    return this;
  }
  
  public Promotion setName(String paramString)
  {
    put("nm", paramString);
    return this;
  }
  
  public Promotion setPosition(String paramString)
  {
    put("ps", paramString);
    return this;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.analytics.ecommerce.Promotion
 * JD-Core Version:    0.7.0.1
 */