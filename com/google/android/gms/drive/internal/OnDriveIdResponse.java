package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;

public class OnDriveIdResponse
  implements SafeParcelable
{
  public static final Parcelable.Creator<OnDriveIdResponse> CREATOR = new ak();
  final int BR;
  DriveId Od;
  
  OnDriveIdResponse(int paramInt, DriveId paramDriveId)
  {
    this.BR = paramInt;
    this.Od = paramDriveId;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public DriveId getDriveId()
  {
    return this.Od;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ak.a(this, paramParcel, paramInt);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.internal.OnDriveIdResponse
 * JD-Core Version:    0.7.0.1
 */