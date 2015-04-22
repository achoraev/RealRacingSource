package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.StorageStats;

public class as
  implements Parcelable.Creator<OnStorageStatsResponse>
{
  static void a(OnStorageStatsResponse paramOnStorageStatsResponse, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.c(paramParcel, 1, paramOnStorageStatsResponse.BR);
    b.a(paramParcel, 2, paramOnStorageStatsResponse.Pw, paramInt, false);
    b.H(paramParcel, i);
  }
  
  public OnStorageStatsResponse au(Parcel paramParcel)
  {
    int i = a.C(paramParcel);
    int j = 0;
    StorageStats localStorageStats = null;
    while (paramParcel.dataPosition() < i)
    {
      int k = a.B(paramParcel);
      switch (a.aD(k))
      {
      default: 
        a.b(paramParcel, k);
        break;
      case 1: 
        j = a.g(paramParcel, k);
        break;
      case 2: 
        localStorageStats = (StorageStats)a.a(paramParcel, k, StorageStats.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new a.a("Overread allowed size end=" + i, paramParcel);
    }
    return new OnStorageStatsResponse(j, localStorageStats);
  }
  
  public OnStorageStatsResponse[] bG(int paramInt)
  {
    return new OnStorageStatsResponse[paramInt];
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.internal.as
 * JD-Core Version:    0.7.0.1
 */