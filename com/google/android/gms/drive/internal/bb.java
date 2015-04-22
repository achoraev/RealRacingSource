package com.google.android.gms.drive.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.BaseImplementation.b;
import com.google.android.gms.common.api.Status;

public class bb
  extends c
{
  private final BaseImplementation.b<Status> De;
  
  public bb(BaseImplementation.b<Status> paramb)
  {
    this.De = paramb;
  }
  
  public void o(Status paramStatus)
    throws RemoteException
  {
    this.De.b(paramStatus);
  }
  
  public void onSuccess()
    throws RemoteException
  {
    this.De.b(Status.Jv);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.internal.bb
 * JD-Core Version:    0.7.0.1
 */