package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d.a;
import java.util.Map;

class cf
  extends aj
{
  private static final String ID = a.Q.toString();
  private static final String aql = b.dz.toString();
  private static final String aqm = b.dy.toString();
  
  public cf()
  {
    super(ID, new String[0]);
  }
  
  public d.a C(Map<String, d.a> paramMap)
  {
    d.a locala1 = (d.a)paramMap.get(aql);
    d.a locala2 = (d.a)paramMap.get(aqm);
    double d3;
    double d1;
    if ((locala1 != null) && (locala1 != di.pK()) && (locala2 != null) && (locala2 != di.pK()))
    {
      dh localdh1 = di.k(locala1);
      dh localdh2 = di.k(locala2);
      if ((localdh1 != di.pI()) && (localdh2 != di.pI()))
      {
        d3 = localdh1.doubleValue();
        d1 = localdh2.doubleValue();
        if (d3 > d1) {}
      }
    }
    for (double d2 = d3;; d2 = 0.0D)
    {
      return di.u(Long.valueOf(Math.round(d2 + Math.random() * (d1 - d2))));
      d1 = 2147483647.0D;
    }
  }
  
  public boolean nN()
  {
    return false;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.cf
 * JD-Core Version:    0.7.0.1
 */