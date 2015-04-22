package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.common.GooglePlayServicesUtil;

@ez
public final class cf
{
  public static void a(Context paramContext, b paramb)
  {
    if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(paramContext) != 0)
    {
      paramb.a(bn.bs());
      return;
    }
    new a(paramContext, paramb);
  }
  
  public static final class a
    implements GooglePlayServicesClient.ConnectionCallbacks, GooglePlayServicesClient.OnConnectionFailedListener
  {
    private final Object mw = new Object();
    private final cf.b pN;
    private final cg pO;
    
    public a(Context paramContext, cf.b paramb)
    {
      this(paramContext, paramb, false);
    }
    
    a(Context paramContext, cf.b paramb, boolean paramBoolean)
    {
      this.pN = paramb;
      this.pO = new cg(paramContext, this, this, 6171000);
      if (!paramBoolean) {
        this.pO.connect();
      }
    }
    
    public void onConnected(Bundle paramBundle)
    {
      Bundle localBundle1 = bn.bs();
      Object localObject1 = this.mw;
      for (;;)
      {
        try
        {
          ch localch = this.pO.bC();
          if (localch == null) {
            break label208;
          }
          Bundle localBundle3 = localch.bD();
          localBundle2 = localBundle3;
          localBundle2 = localBundle1;
        }
        catch (IllegalStateException localIllegalStateException)
        {
          localIllegalStateException = localIllegalStateException;
          gs.d("Error when get Gservice values", localIllegalStateException);
          if ((this.pO.isConnected()) || (this.pO.isConnecting()))
          {
            this.pO.disconnect();
            localBundle2 = localBundle1;
            continue;
          }
        }
        catch (RemoteException localRemoteException)
        {
          gs.d("Error when get Gservice values", localRemoteException);
          if ((this.pO.isConnected()) || (this.pO.isConnecting()))
          {
            this.pO.disconnect();
            localBundle2 = localBundle1;
            continue;
          }
        }
        finally
        {
          if ((this.pO.isConnected()) || (this.pO.isConnecting())) {
            this.pO.disconnect();
          }
        }
        continue;
        label208:
        Bundle localBundle2 = localBundle1;
      }
    }
    
    public void onConnectionFailed(ConnectionResult paramConnectionResult)
    {
      this.pN.a(bn.bs());
    }
    
    public void onDisconnected()
    {
      gs.S("Disconnected from remote ad request service.");
    }
  }
  
  public static abstract interface b
  {
    public abstract void a(Bundle paramBundle);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.cf
 * JD-Core Version:    0.7.0.1
 */