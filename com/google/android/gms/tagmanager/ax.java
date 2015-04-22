package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d.a;
import java.util.Map;

class ax
  extends aj
{
  private static final String ID = a.ad.toString();
  private static final String anT = b.bW.toString();
  private final Context lB;
  
  public ax(Context paramContext)
  {
    super(ID, new String[0]);
    this.lB = paramContext;
  }
  
  public d.a C(Map<String, d.a> paramMap)
  {
    if ((d.a)paramMap.get(anT) != null) {}
    for (String str1 = di.j((d.a)paramMap.get(anT));; str1 = null)
    {
      String str2 = ay.e(this.lB, str1);
      if (str2 == null) {
        break;
      }
      return di.u(str2);
    }
    return di.pK();
  }
  
  public boolean nN()
  {
    return true;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.ax
 * JD-Core Version:    0.7.0.1
 */