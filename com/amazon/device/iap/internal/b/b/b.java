package com.amazon.device.iap.internal.b.b;

import com.amazon.android.framework.exception.KiwiException;
import com.amazon.device.iap.internal.b.e;

public final class b
  extends a
{
  public b(e parame, String paramString)
  {
    super(parame, "1.0", paramString);
  }
  
  protected void preExecution()
    throws KiwiException
  {
    super.preExecution();
    com.amazon.device.iap.internal.c.b.a().b(c());
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.amazon.device.iap.internal.b.b.b
 * JD-Core Version:    0.7.0.1
 */