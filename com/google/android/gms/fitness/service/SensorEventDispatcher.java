package com.google.android.gms.fitness.service;

import android.os.RemoteException;
import com.google.android.gms.fitness.data.DataPoint;
import java.util.List;

public abstract interface SensorEventDispatcher
{
  public abstract void publish(DataPoint paramDataPoint)
    throws RemoteException;
  
  public abstract void publish(List<DataPoint> paramList)
    throws RemoteException;
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.fitness.service.SensorEventDispatcher
 * JD-Core Version:    0.7.0.1
 */