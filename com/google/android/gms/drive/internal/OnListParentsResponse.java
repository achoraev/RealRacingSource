package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.i;

public class OnListParentsResponse
  extends i
  implements SafeParcelable
{
  public static final Parcelable.Creator<OnListParentsResponse> CREATOR = new ao();
  final int BR;
  final DataHolder Pv;
  
  OnListParentsResponse(int paramInt, DataHolder paramDataHolder)
  {
    this.BR = paramInt;
    this.Pv = paramDataHolder;
  }
  
  protected void I(Parcel paramParcel, int paramInt)
  {
    ao.a(this, paramParcel, paramInt);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public DataHolder ik()
  {
    return this.Pv;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.internal.OnListParentsResponse
 * JD-Core Version:    0.7.0.1
 */