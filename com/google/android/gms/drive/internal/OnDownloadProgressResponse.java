package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class OnDownloadProgressResponse
  implements SafeParcelable
{
  public static final Parcelable.Creator<OnDownloadProgressResponse> CREATOR = new aj();
  final int BR;
  final long Pp;
  final long Pq;
  
  OnDownloadProgressResponse(int paramInt, long paramLong1, long paramLong2)
  {
    this.BR = paramInt;
    this.Pp = paramLong1;
    this.Pq = paramLong2;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public long jdMethod_if()
  {
    return this.Pp;
  }
  
  public long ig()
  {
    return this.Pq;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    aj.a(this, paramParcel, paramInt);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.internal.OnDownloadProgressResponse
 * JD-Core Version:    0.7.0.1
 */