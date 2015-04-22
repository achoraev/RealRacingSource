package com.google.android.gms.drive.internal;

import com.google.android.gms.common.api.BaseImplementation.a;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.Drive;

abstract class p<R extends Result>
  extends BaseImplementation.a<R, q>
{
  public p()
  {
    super(Drive.CU);
  }
  
  static abstract class a
    extends p<Status>
  {
    protected Status d(Status paramStatus)
    {
      return paramStatus;
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.internal.p
 * JD-Core Version:    0.7.0.1
 */