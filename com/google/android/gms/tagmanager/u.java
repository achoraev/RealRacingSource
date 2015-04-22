package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d.a;
import java.util.Map;

class u
  extends aj
{
  private static final String ID = a.C.toString();
  private static final String NAME = b.dB.toString();
  private static final String aoP = b.cr.toString();
  private final DataLayer aod;
  
  public u(DataLayer paramDataLayer)
  {
    super(str, arrayOfString);
    this.aod = paramDataLayer;
  }
  
  public d.a C(Map<String, d.a> paramMap)
  {
    Object localObject = this.aod.get(di.j((d.a)paramMap.get(NAME)));
    if (localObject == null)
    {
      d.a locala = (d.a)paramMap.get(aoP);
      if (locala != null) {
        return locala;
      }
      return di.pK();
    }
    return di.u(localObject);
  }
  
  public boolean nN()
  {
    return false;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.u
 * JD-Core Version:    0.7.0.1
 */