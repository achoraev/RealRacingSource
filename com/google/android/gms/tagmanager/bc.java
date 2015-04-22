package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.a;
import com.google.android.gms.internal.d.a;
import java.util.Locale;
import java.util.Map;

class bc
  extends aj
{
  private static final String ID = a.N.toString();
  
  public bc()
  {
    super(ID, new String[0]);
  }
  
  public d.a C(Map<String, d.a> paramMap)
  {
    Locale localLocale = Locale.getDefault();
    if (localLocale == null) {
      return di.pK();
    }
    String str = localLocale.getLanguage();
    if (str == null) {
      return di.pK();
    }
    return di.u(str.toLowerCase());
  }
  
  public boolean nN()
  {
    return false;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.bc
 * JD-Core Version:    0.7.0.1
 */