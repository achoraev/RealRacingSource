package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d.a;
import java.util.Map;

class e
  extends aj
{
  private static final String ID = a.Y.toString();
  private static final String anT = b.bW.toString();
  private static final String anU = b.bZ.toString();
  private final Context lB;
  
  public e(Context paramContext)
  {
    super(str, arrayOfString);
    this.lB = paramContext;
  }
  
  public d.a C(Map<String, d.a> paramMap)
  {
    d.a locala1 = (d.a)paramMap.get(anU);
    if (locala1 == null) {
      return di.pK();
    }
    String str1 = di.j(locala1);
    d.a locala2 = (d.a)paramMap.get(anT);
    if (locala2 != null) {}
    for (String str2 = di.j(locala2);; str2 = null)
    {
      String str3 = ay.f(this.lB, str1, str2);
      if (str3 == null) {
        break;
      }
      return di.u(str3);
    }
    return di.pK();
  }
  
  public boolean nN()
  {
    return true;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.e
 * JD-Core Version:    0.7.0.1
 */