package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.a;
import com.google.android.gms.internal.d.a;
import java.util.Map;

class ah
  extends aj
{
  private static final String ID = a.K.toString();
  private final ct aoe;
  
  public ah(ct paramct)
  {
    super(ID, new String[0]);
    this.aoe = paramct;
  }
  
  public d.a C(Map<String, d.a> paramMap)
  {
    String str = this.aoe.pn();
    if (str == null) {
      return di.pK();
    }
    return di.u(str);
  }
  
  public boolean nN()
  {
    return false;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.ah
 * JD-Core Version:    0.7.0.1
 */