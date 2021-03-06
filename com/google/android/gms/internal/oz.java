package com.google.android.gms.internal;

import android.app.Activity;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.dynamic.c;
import com.google.android.gms.dynamic.e;
import com.google.android.gms.dynamic.g;
import com.google.android.gms.dynamic.g.a;
import com.google.android.gms.wallet.fragment.WalletFragmentOptions;

public class oz
  extends g<ou>
{
  private static oz aux;
  
  protected oz()
  {
    super("com.google.android.gms.wallet.dynamite.WalletDynamiteCreatorImpl");
  }
  
  public static or a(Activity paramActivity, c paramc, WalletFragmentOptions paramWalletFragmentOptions, os paramos)
    throws GooglePlayServicesNotAvailableException
  {
    int i = GooglePlayServicesUtil.isGooglePlayServicesAvailable(paramActivity);
    if (i != 0) {
      throw new GooglePlayServicesNotAvailableException(i);
    }
    try
    {
      or localor = ((ou)pP().L(paramActivity)).a(e.k(paramActivity), paramc, paramWalletFragmentOptions, paramos);
      return localor;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeException(localRemoteException);
    }
    catch (g.a locala)
    {
      throw new RuntimeException(locala);
    }
  }
  
  private static oz pP()
  {
    if (aux == null) {
      aux = new oz();
    }
    return aux;
  }
  
  protected ou bQ(IBinder paramIBinder)
  {
    return ou.a.bM(paramIBinder);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.oz
 * JD-Core Version:    0.7.0.1
 */