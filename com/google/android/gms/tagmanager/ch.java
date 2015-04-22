package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d.a;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

class ch
  extends aj
{
  private static final String ID = a.ag.toString();
  private static final String aqn = b.bw.toString();
  private static final String aqo = b.bx.toString();
  private static final String aqp = b.dc.toString();
  private static final String aqq = b.cW.toString();
  
  public ch()
  {
    super(str, arrayOfString);
  }
  
  public d.a C(Map<String, d.a> paramMap)
  {
    d.a locala1 = (d.a)paramMap.get(aqn);
    d.a locala2 = (d.a)paramMap.get(aqo);
    if ((locala1 == null) || (locala1 == di.pK()) || (locala2 == null) || (locala2 == di.pK())) {
      return di.pK();
    }
    int i = 64;
    if (di.n((d.a)paramMap.get(aqp)).booleanValue()) {
      i = 66;
    }
    d.a locala3 = (d.a)paramMap.get(aqq);
    int j;
    if (locala3 != null)
    {
      Long localLong = di.l(locala3);
      if (localLong == di.pF()) {
        return di.pK();
      }
      j = localLong.intValue();
      if (j < 0) {
        return di.pK();
      }
    }
    else
    {
      j = 1;
    }
    try
    {
      String str1 = di.j(locala1);
      Matcher localMatcher = Pattern.compile(di.j(locala2), i).matcher(str1);
      boolean bool = localMatcher.find();
      String str2 = null;
      if (bool)
      {
        int k = localMatcher.groupCount();
        str2 = null;
        if (k >= j) {
          str2 = localMatcher.group(j);
        }
      }
      if (str2 == null) {
        return di.pK();
      }
      d.a locala4 = di.u(str2);
      return locala4;
    }
    catch (PatternSyntaxException localPatternSyntaxException) {}
    return di.pK();
  }
  
  public boolean nN()
  {
    return true;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.ch
 * JD-Core Version:    0.7.0.1
 */