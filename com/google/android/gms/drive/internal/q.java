package com.google.android.gms.drive.internal;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.api.BaseImplementation.a;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.e;
import com.google.android.gms.common.internal.e.e;
import com.google.android.gms.common.internal.l;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.events.c;
import com.google.android.gms.drive.events.d;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class q
  extends e<ab>
{
  private final String Dd;
  private final String IM;
  private final Bundle OA;
  private final boolean OB;
  private DriveId OC;
  private DriveId OD;
  final GoogleApiClient.ConnectionCallbacks OE;
  final Map<DriveId, Map<c, y>> OF = new HashMap();
  
  public q(Context paramContext, Looper paramLooper, ClientSettings paramClientSettings, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener, String[] paramArrayOfString, Bundle paramBundle)
  {
    super(paramContext, paramLooper, paramConnectionCallbacks, paramOnConnectionFailedListener, paramArrayOfString);
    this.Dd = ((String)o.b(paramClientSettings.getAccountNameOrDefault(), "Must call Api.ClientBuilder.setAccountName()"));
    this.IM = paramClientSettings.getRealClientPackageName();
    this.OE = paramConnectionCallbacks;
    this.OA = paramBundle;
    Intent localIntent = new Intent("com.google.android.gms.drive.events.HANDLE_EVENT");
    localIntent.setPackage(paramContext.getPackageName());
    List localList = paramContext.getPackageManager().queryIntentServices(localIntent, 0);
    switch (localList.size())
    {
    default: 
      throw new IllegalStateException("AndroidManifest.xml can only define one service that handles the " + localIntent.getAction() + " action");
    case 0: 
      this.OB = false;
      return;
    }
    ServiceInfo localServiceInfo = ((ResolveInfo)localList.get(0)).serviceInfo;
    if (!localServiceInfo.exported) {
      throw new IllegalStateException("Drive event service " + localServiceInfo.name + " must be exported in AndroidManifest.xml");
    }
    this.OB = true;
  }
  
  protected ab T(IBinder paramIBinder)
  {
    return ab.a.U(paramIBinder);
  }
  
  PendingResult<Status> a(GoogleApiClient paramGoogleApiClient, final DriveId paramDriveId, final int paramInt)
  {
    o.b(d.a(paramInt, paramDriveId), "id");
    o.i("eventService");
    o.a(isConnected(), "Client must be connected");
    if (!this.OB) {
      throw new IllegalStateException("Application must define an exported DriveEventService subclass in AndroidManifest.xml to add event subscriptions");
    }
    paramGoogleApiClient.b(new p.a()
    {
      protected void a(q paramAnonymousq)
        throws RemoteException
      {
        paramAnonymousq.hY().a(new AddEventListenerRequest(paramDriveId, paramInt), null, null, new bb(this));
      }
    });
  }
  
  PendingResult<Status> a(GoogleApiClient paramGoogleApiClient, final DriveId paramDriveId, final int paramInt, c paramc)
  {
    o.b(d.a(paramInt, paramDriveId), "id");
    o.b(paramc, "listener");
    o.a(isConnected(), "Client must be connected");
    for (;;)
    {
      Map localMap2;
      synchronized (this.OF)
      {
        localMap2 = (Map)this.OF.get(paramDriveId);
        if (localMap2 == null)
        {
          HashMap localHashMap = new HashMap();
          this.OF.put(paramDriveId, localHashMap);
          localObject2 = localHashMap;
          final y localy = (y)((Map)localObject2).get(paramc);
          if (localy == null)
          {
            localy = new y(getLooper(), getContext(), paramInt, paramc);
            ((Map)localObject2).put(paramc, localy);
            localy.bq(paramInt);
            BaseImplementation.a locala = paramGoogleApiClient.b(new p.a()
            {
              protected void a(q paramAnonymousq)
                throws RemoteException
              {
                paramAnonymousq.hY().a(new AddEventListenerRequest(paramDriveId, paramInt), localy, null, new bb(this));
              }
            });
            return locala;
          }
          if (!localy.br(paramInt)) {
            continue;
          }
          o.m localm = new o.m(paramGoogleApiClient, Status.Jv);
          return localm;
        }
      }
      Object localObject2 = localMap2;
    }
  }
  
  protected void a(int paramInt, IBinder paramIBinder, Bundle paramBundle)
  {
    if (paramBundle != null)
    {
      paramBundle.setClassLoader(getClass().getClassLoader());
      this.OC = ((DriveId)paramBundle.getParcelable("com.google.android.gms.drive.root_id"));
      this.OD = ((DriveId)paramBundle.getParcelable("com.google.android.gms.drive.appdata_id"));
    }
    super.a(paramInt, paramIBinder, paramBundle);
  }
  
  protected void a(l paraml, e.e parame)
    throws RemoteException
  {
    String str = getContext().getPackageName();
    o.i(parame);
    o.i(str);
    o.i(gR());
    Bundle localBundle = new Bundle();
    if (!str.equals(this.IM)) {
      localBundle.putString("proxy_package_name", this.IM);
    }
    localBundle.putAll(this.OA);
    paraml.a(parame, 6171000, str, gR(), this.Dd, localBundle);
  }
  
  PendingResult<Status> b(GoogleApiClient paramGoogleApiClient, final DriveId paramDriveId, final int paramInt)
  {
    o.b(d.a(paramInt, paramDriveId), "id");
    o.i("eventService");
    o.a(isConnected(), "Client must be connected");
    paramGoogleApiClient.b(new p.a()
    {
      protected void a(q paramAnonymousq)
        throws RemoteException
      {
        paramAnonymousq.hY().a(new RemoveEventListenerRequest(paramDriveId, paramInt), null, null, new bb(this));
      }
    });
  }
  
  PendingResult<Status> b(GoogleApiClient paramGoogleApiClient, final DriveId paramDriveId, final int paramInt, c paramc)
  {
    o.b(d.a(paramInt, paramDriveId), "id");
    o.a(isConnected(), "Client must be connected");
    o.b(paramc, "listener");
    Map localMap2;
    final y localy;
    synchronized (this.OF)
    {
      localMap2 = (Map)this.OF.get(paramDriveId);
      if (localMap2 == null)
      {
        o.m localm1 = new o.m(paramGoogleApiClient, Status.Jv);
        return localm1;
      }
      localy = (y)localMap2.remove(paramc);
      if (localy == null)
      {
        o.m localm2 = new o.m(paramGoogleApiClient, Status.Jv);
        return localm2;
      }
    }
    if (localMap2.isEmpty()) {
      this.OF.remove(paramDriveId);
    }
    BaseImplementation.a locala = paramGoogleApiClient.b(new p.a()
    {
      protected void a(q paramAnonymousq)
        throws RemoteException
      {
        paramAnonymousq.hY().a(new RemoveEventListenerRequest(paramDriveId, paramInt), localy, null, new bb(this));
      }
    });
    return locala;
  }
  
  public void disconnect()
  {
    ab localab = (ab)gS();
    if (localab != null) {}
    try
    {
      localab.a(new DisconnectRequest());
      label25:
      super.disconnect();
      this.OF.clear();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      break label25;
    }
  }
  
  protected String getServiceDescriptor()
  {
    return "com.google.android.gms.drive.internal.IDriveService";
  }
  
  protected String getStartServiceAction()
  {
    return "com.google.android.gms.drive.ApiService.START";
  }
  
  public ab hY()
  {
    return (ab)gS();
  }
  
  public DriveId hZ()
  {
    return this.OC;
  }
  
  public DriveId ia()
  {
    return this.OD;
  }
  
  public boolean ib()
  {
    return this.OB;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.internal.q
 * JD-Core Version:    0.7.0.1
 */