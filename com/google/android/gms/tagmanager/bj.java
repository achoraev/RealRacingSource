package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d.a;
import java.util.Map;

class bj
  extends aj
{
  private static final String ID = a.ai.toString();
  private static final String apf = b.bw.toString();
  
  public bj()
  {
    super(str, arrayOfString);
  }
  
  public d.a C(Map<String, d.a> paramMap)
  {
    return di.u(di.j((d.a)paramMap.get(apf)).toLowerCase());
  }
  
  public boolean nN()
  {
    return true;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.bj
 * JD-Core Version:    0.7.0.1
 */