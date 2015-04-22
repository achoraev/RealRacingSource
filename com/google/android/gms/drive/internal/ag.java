package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.DriveId;

public class ag
  implements Parcelable.Creator<LoadRealtimeRequest>
{
  static void a(LoadRealtimeRequest paramLoadRealtimeRequest, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.c(paramParcel, 1, paramLoadRealtimeRequest.BR);
    b.a(paramParcel, 2, paramLoadRealtimeRequest.MW, paramInt, false);
    b.a(paramParcel, 3, paramLoadRealtimeRequest.Pk);
    b.H(paramParcel, i);
  }
  
  public LoadRealtimeRequest aj(Parcel paramParcel)
  {
    boolean bool1 = false;
    int i = a.C(paramParcel);
    Object localObject1 = null;
    int j = 0;
    if (paramParcel.dataPosition() < i)
    {
      int k = a.B(paramParcel);
      boolean bool2;
      Object localObject2;
      int m;
      switch (a.aD(k))
      {
      default: 
        a.b(paramParcel, k);
        bool2 = bool1;
        localObject2 = localObject1;
        m = j;
      }
      for (;;)
      {
        j = m;
        localObject1 = localObject2;
        bool1 = bool2;
        break;
        int n = a.g(paramParcel, k);
        boolean bool3 = bool1;
        localObject2 = localObject1;
        m = n;
        bool2 = bool3;
        continue;
        DriveId localDriveId = (DriveId)a.a(paramParcel, k, DriveId.CREATOR);
        m = j;
        bool2 = bool1;
        localObject2 = localDriveId;
        continue;
        bool2 = a.c(paramParcel, k);
        localObject2 = localObject1;
        m = j;
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new a.a("Overread allowed size end=" + i, paramParcel);
    }
    return new LoadRealtimeRequest(j, localObject1, bool1);
  }
  
  public LoadRealtimeRequest[] bv(int paramInt)
  {
    return new LoadRealtimeRequest[paramInt];
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.internal.ag
 * JD-Core Version:    0.7.0.1
 */