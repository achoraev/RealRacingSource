package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.e;
import com.google.android.gms.common.internal.e.e;
import com.google.android.gms.common.internal.l;

@ez
public class fh
  extends e<fm>
{
  final int pP;
  
  public fh(Context paramContext, GooglePlayServicesClient.ConnectionCallbacks paramConnectionCallbacks, GooglePlayServicesClient.OnConnectionFailedListener paramOnConnectionFailedListener, int paramInt)
  {
    super(paramContext, paramConnectionCallbacks, paramOnConnectionFailedListener, new String[0]);
    this.pP = paramInt;
  }
  
  protected fm C(IBinder paramIBinder)
  {
    return fm.a.D(paramIBinder);
  }
  
  protected void a(l paraml, e.e parame)
    throws RemoteException
  {
    Bundle localBundle = new Bundle();
    paraml.g(parame, this.pP, getContext().getPackageName(), localBundle);
  }
  
  public fm cE()
  {
    return (fm)super.gS();
  }
  
  protected String getServiceDescriptor()
  {
    return "com.google.android.gms.ads.internal.request.IAdRequestService";
  }
  
  protected String getStartServiceAction()
  {
    return "com.google.android.gms.ads.service.START";
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.fh
 * JD-Core Version:    0.7.0.1
 */