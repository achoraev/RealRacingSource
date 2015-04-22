package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d.a;
import java.util.Map;

class m
  extends aj
{
  private static final String ID = a.A.toString();
  private static final String VALUE = b.ff.toString();
  
  public m()
  {
    super(str, arrayOfString);
  }
  
  public static String nQ()
  {
    return ID;
  }
  
  public static String nR()
  {
    return VALUE;
  }
  
  public d.a C(Map<String, d.a> paramMap)
  {
    return (d.a)paramMap.get(VALUE);
  }
  
  public boolean nN()
  {
    return true;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.m
 * JD-Core Version:    0.7.0.1
 */