package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class b
  implements SafeParcelable
{
  public static final Parcelable.Creator<b> CREATOR = new c();
  final int BR;
  public final ae avk;
  public final IntentFilter[] avl;
  
  b(int paramInt, IBinder paramIBinder, IntentFilter[] paramArrayOfIntentFilter)
  {
    this.BR = paramInt;
    if (paramIBinder != null) {}
    for (this.avk = ae.a.bS(paramIBinder);; this.avk = null)
    {
      this.avl = paramArrayOfIntentFilter;
      return;
    }
  }
  
  public b(ax paramax)
  {
    this.BR = 1;
    this.avk = paramax;
    this.avl = paramax.qb();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  IBinder pV()
  {
    if (this.avk == null) {
      return null;
    }
    return this.avk.asBinder();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    c.a(this, paramParcel, paramInt);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.wearable.internal.b
 * JD-Core Version:    0.7.0.1
 */