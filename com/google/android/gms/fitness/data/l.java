package com.google.android.gms.fitness.data;

import android.os.RemoteException;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.fitness.request.OnDataPointListener;
import java.util.HashMap;
import java.util.Map;

public class l
  extends k.a
{
  private final OnDataPointListener Ti;
  
  private l(OnDataPointListener paramOnDataPointListener)
  {
    this.Ti = ((OnDataPointListener)o.i(paramOnDataPointListener));
  }
  
  public void c(DataPoint paramDataPoint)
    throws RemoteException
  {
    this.Ti.onDataPoint(paramDataPoint);
  }
  
  public static class a
  {
    private static final a Tj = new a();
    private final Map<OnDataPointListener, l> Tk = new HashMap();
    
    public static a iV()
    {
      return Tj;
    }
    
    public l a(OnDataPointListener paramOnDataPointListener)
    {
      synchronized (this.Tk)
      {
        l locall = (l)this.Tk.get(paramOnDataPointListener);
        if (locall == null)
        {
          locall = new l(paramOnDataPointListener, null);
          this.Tk.put(paramOnDataPointListener, locall);
        }
        return locall;
      }
    }
    
    public l b(OnDataPointListener paramOnDataPointListener)
    {
      synchronized (this.Tk)
      {
        l locall = (l)this.Tk.get(paramOnDataPointListener);
        return locall;
      }
    }
    
    public l c(OnDataPointListener paramOnDataPointListener)
    {
      synchronized (this.Tk)
      {
        l locall1 = (l)this.Tk.remove(paramOnDataPointListener);
        if (locall1 != null) {
          return locall1;
        }
        l locall2 = new l(paramOnDataPointListener, null);
        return locall2;
      }
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.fitness.data.l
 * JD-Core Version:    0.7.0.1
 */