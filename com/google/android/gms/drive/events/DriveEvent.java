package com.google.android.gms.drive.events;

import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public abstract interface DriveEvent
  extends SafeParcelable
{
  public abstract int getType();
  
  public static abstract interface Listener<E extends DriveEvent>
    extends c
  {
    public abstract void onEvent(E paramE);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.events.DriveEvent
 * JD-Core Version:    0.7.0.1
 */