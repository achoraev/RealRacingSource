package com.amazon.device.iap.internal.b.f;

import com.amazon.device.iap.internal.b.e;
import com.amazon.device.iap.internal.b.i;
import com.amazon.venezia.command.SuccessResult;

abstract class a
  extends i
{
  a(e parame, String paramString)
  {
    super(parame, "response_received", paramString);
    b(false);
  }
  
  protected boolean a(SuccessResult paramSuccessResult)
    throws Exception
  {
    return true;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.amazon.device.iap.internal.b.f.a
 * JD-Core Version:    0.7.0.1
 */