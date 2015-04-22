package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.d.a;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

abstract class aj
{
  private final Set<String> apj;
  private final String apk;
  
  public aj(String paramString, String... paramVarArgs)
  {
    this.apk = paramString;
    this.apj = new HashSet(paramVarArgs.length);
    int i = paramVarArgs.length;
    for (int j = 0; j < i; j++)
    {
      String str = paramVarArgs[j];
      this.apj.add(str);
    }
  }
  
  public abstract d.a C(Map<String, d.a> paramMap);
  
  boolean a(Set<String> paramSet)
  {
    return paramSet.containsAll(this.apj);
  }
  
  public abstract boolean nN();
  
  public String or()
  {
    return this.apk;
  }
  
  public Set<String> os()
  {
    return this.apj;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.aj
 * JD-Core Version:    0.7.0.1
 */