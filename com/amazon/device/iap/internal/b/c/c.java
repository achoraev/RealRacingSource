package com.amazon.device.iap.internal.b.c;

import com.amazon.device.iap.internal.b.e;
import com.amazon.device.iap.internal.b.i;
import java.util.Set;

abstract class c
  extends i
{
  protected final Set<String> a;
  
  c(e parame, String paramString, Set<String> paramSet)
  {
    super(parame, "getItem_data", paramString);
    this.a = paramSet;
    a("skus", paramSet);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.amazon.device.iap.internal.b.c.c
 * JD-Core Version:    0.7.0.1
 */