package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;

@ez
public abstract class fg
  extends gg
{
  private final fi pQ;
  private final ff.a tu;
  
  public fg(fi paramfi, ff.a parama)
  {
    this.pQ = paramfi;
    this.tu = parama;
  }
  
  private static fk a(fm paramfm, fi paramfi)
  {
    try
    {
      fk localfk = paramfm.b(paramfi);
      return localfk;
    }
    catch (RemoteException localRemoteException)
    {
      gs.d("Could not fetch ad response from ad request service.", localRemoteException);
      return null;
    }
    catch (NullPointerException localNullPointerException)
    {
      gs.d("Could not fetch ad response from ad request service due to an Exception.", localNullPointerException);
      return null;
    }
    catch (SecurityException localSecurityException)
    {
      gs.d("Could not fetch ad response from ad request service due to an Exception.", localSecurityException);
      return null;
    }
    catch (Throwable localThrowable)
    {
      gb.e(localThrowable);
    }
    return null;
  }
  
  public abstract void cC();
  
  public abstract fm cD();
  
  /* Error */
  public final void co()
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 56	com/google/android/gms/internal/fg:cD	()Lcom/google/android/gms/internal/fm;
    //   4: astore_2
    //   5: aload_2
    //   6: ifnonnull +27 -> 33
    //   9: new 58	com/google/android/gms/internal/fk
    //   12: dup
    //   13: iconst_0
    //   14: invokespecial 61	com/google/android/gms/internal/fk:<init>	(I)V
    //   17: astore_3
    //   18: aload_0
    //   19: invokevirtual 63	com/google/android/gms/internal/fg:cC	()V
    //   22: aload_0
    //   23: getfield 18	com/google/android/gms/internal/fg:tu	Lcom/google/android/gms/internal/ff$a;
    //   26: aload_3
    //   27: invokeinterface 68 2 0
    //   32: return
    //   33: aload_2
    //   34: aload_0
    //   35: getfield 16	com/google/android/gms/internal/fg:pQ	Lcom/google/android/gms/internal/fi;
    //   38: invokestatic 70	com/google/android/gms/internal/fg:a	(Lcom/google/android/gms/internal/fm;Lcom/google/android/gms/internal/fi;)Lcom/google/android/gms/internal/fk;
    //   41: astore_3
    //   42: aload_3
    //   43: ifnonnull -25 -> 18
    //   46: new 58	com/google/android/gms/internal/fk
    //   49: dup
    //   50: iconst_0
    //   51: invokespecial 61	com/google/android/gms/internal/fk:<init>	(I)V
    //   54: astore_3
    //   55: goto -37 -> 18
    //   58: astore_1
    //   59: aload_0
    //   60: invokevirtual 63	com/google/android/gms/internal/fg:cC	()V
    //   63: aload_1
    //   64: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	65	0	this	fg
    //   58	6	1	localObject	Object
    //   4	30	2	localfm	fm
    //   17	38	3	localfk	fk
    // Exception table:
    //   from	to	target	type
    //   0	5	58	finally
    //   9	18	58	finally
    //   33	42	58	finally
    //   46	55	58	finally
  }
  
  public final void onStop()
  {
    cC();
  }
  
  @ez
  public static final class a
    extends fg
  {
    private final Context mContext;
    
    public a(Context paramContext, fi paramfi, ff.a parama)
    {
      super(parama);
      this.mContext = paramContext;
    }
    
    public void cC() {}
    
    public fm cD()
    {
      Bundle localBundle = gb.bD();
      bm localbm = new bm(localBundle.getString("gads:sdk_core_location"), localBundle.getString("gads:sdk_core_experiment_id"), localBundle.getString("gads:block_autoclicks_experiment_id"));
      return fr.a(this.mContext, localbm, new cj(), new fy());
    }
  }
  
  @ez
  public static final class b
    extends fg
    implements GooglePlayServicesClient.ConnectionCallbacks, GooglePlayServicesClient.OnConnectionFailedListener
  {
    private final Object mw = new Object();
    private final ff.a tu;
    private final fh tv;
    
    public b(Context paramContext, fi paramfi, ff.a parama)
    {
      super(parama);
      this.tu = parama;
      this.tv = new fh(paramContext, this, this, paramfi.lD.wF);
      this.tv.connect();
    }
    
    public void cC()
    {
      synchronized (this.mw)
      {
        if ((this.tv.isConnected()) || (this.tv.isConnecting())) {
          this.tv.disconnect();
        }
        return;
      }
    }
    
    public fm cD()
    {
      synchronized (this.mw)
      {
        try
        {
          fm localfm = this.tv.cE();
          return localfm;
        }
        catch (IllegalStateException localIllegalStateException)
        {
          return null;
        }
      }
    }
    
    public void onConnected(Bundle paramBundle)
    {
      start();
    }
    
    public void onConnectionFailed(ConnectionResult paramConnectionResult)
    {
      this.tu.a(new fk(0));
    }
    
    public void onDisconnected()
    {
      gs.S("Disconnected from remote ad request service.");
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.fg
 * JD-Core Version:    0.7.0.1
 */