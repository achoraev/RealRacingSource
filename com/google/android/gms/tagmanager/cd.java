package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d.a;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

abstract class cd
  extends aj
{
  private static final String apf = b.bw.toString();
  private static final String aqb = b.bx.toString();
  
  public cd(String paramString)
  {
    super(paramString, arrayOfString);
  }
  
  public d.a C(Map<String, d.a> paramMap)
  {
    Iterator localIterator = paramMap.values().iterator();
    while (localIterator.hasNext()) {
      if ((d.a)localIterator.next() == di.pK()) {
        return di.u(Boolean.valueOf(false));
      }
    }
    d.a locala1 = (d.a)paramMap.get(apf);
    d.a locala2 = (d.a)paramMap.get(aqb);
    if ((locala1 == null) || (locala2 == null)) {}
    for (boolean bool = false;; bool = a(locala1, locala2, paramMap)) {
      return di.u(Boolean.valueOf(bool));
    }
  }
  
  protected abstract boolean a(d.a parama1, d.a parama2, Map<String, d.a> paramMap);
  
  public boolean nN()
  {
    return true;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.cd
 * JD-Core Version:    0.7.0.1
 */