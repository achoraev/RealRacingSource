package com.google.android.gms.wearable.internal;

import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataItem;

public class g
  implements DataEvent
{
  private int FD;
  private DataItem avs;
  
  public g(DataEvent paramDataEvent)
  {
    this.FD = paramDataEvent.getType();
    this.avs = ((DataItem)paramDataEvent.getDataItem().freeze());
  }
  
  public DataItem getDataItem()
  {
    return this.avs;
  }
  
  public int getType()
  {
    return this.FD;
  }
  
  public boolean isDataValid()
  {
    return true;
  }
  
  public DataEvent pW()
  {
    return this;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.wearable.internal.g
 * JD-Core Version:    0.7.0.1
 */