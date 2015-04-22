package com.google.android.gms.fitness.request;

import android.os.RemoteException;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.fitness.data.BleDevice;
import java.util.HashMap;
import java.util.Map;

public class a
  extends l.a
{
  private final BleScanCallback Uf;
  
  private a(BleScanCallback paramBleScanCallback)
  {
    this.Uf = ((BleScanCallback)o.i(paramBleScanCallback));
  }
  
  public void onDeviceFound(BleDevice paramBleDevice)
    throws RemoteException
  {
    this.Uf.onDeviceFound(paramBleDevice);
  }
  
  public void onScanStopped()
    throws RemoteException
  {
    this.Uf.onScanStopped();
  }
  
  public static class a
  {
    private static final a Ug = new a();
    private final Map<BleScanCallback, a> Uh = new HashMap();
    
    public static a je()
    {
      return Ug;
    }
    
    public a a(BleScanCallback paramBleScanCallback)
    {
      synchronized (this.Uh)
      {
        a locala = (a)this.Uh.get(paramBleScanCallback);
        if (locala == null)
        {
          locala = new a(paramBleScanCallback, null);
          this.Uh.put(paramBleScanCallback, locala);
        }
        return locala;
      }
    }
    
    public a b(BleScanCallback paramBleScanCallback)
    {
      synchronized (this.Uh)
      {
        a locala1 = (a)this.Uh.get(paramBleScanCallback);
        if (locala1 != null) {
          return locala1;
        }
        a locala2 = new a(paramBleScanCallback, null);
        return locala2;
      }
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.fitness.request.a
 * JD-Core Version:    0.7.0.1
 */