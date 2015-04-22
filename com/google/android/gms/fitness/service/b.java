package com.google.android.gms.fitness.service;

import android.os.RemoteException;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.k;
import java.util.Iterator;
import java.util.List;

class b
  implements SensorEventDispatcher
{
  private final k UA;
  
  b(k paramk)
  {
    this.UA = ((k)o.i(paramk));
  }
  
  public void publish(DataPoint paramDataPoint)
    throws RemoteException
  {
    this.UA.c(paramDataPoint);
  }
  
  public void publish(List<DataPoint> paramList)
    throws RemoteException
  {
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext()) {
      publish((DataPoint)localIterator.next());
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.fitness.service.b
 * JD-Core Version:    0.7.0.1
 */