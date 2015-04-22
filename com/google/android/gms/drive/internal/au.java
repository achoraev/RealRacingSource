package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.DriveId;

public class au
  implements Parcelable.Creator<OpenContentsRequest>
{
  static void a(OpenContentsRequest paramOpenContentsRequest, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.c(paramParcel, 1, paramOpenContentsRequest.BR);
    b.a(paramParcel, 2, paramOpenContentsRequest.Od, paramInt, false);
    b.c(paramParcel, 3, paramOpenContentsRequest.MV);
    b.c(paramParcel, 4, paramOpenContentsRequest.Px);
    b.H(paramParcel, i);
  }
  
  public OpenContentsRequest aw(Parcel paramParcel)
  {
    int i = 0;
    int j = a.C(paramParcel);
    Object localObject1 = null;
    int k = 0;
    int m = 0;
    if (paramParcel.dataPosition() < j)
    {
      int n = a.B(paramParcel);
      int i1;
      int i2;
      Object localObject2;
      int i3;
      switch (a.aD(n))
      {
      default: 
        a.b(paramParcel, n);
        i1 = i;
        i2 = k;
        localObject2 = localObject1;
        i3 = m;
      }
      for (;;)
      {
        m = i3;
        localObject1 = localObject2;
        k = i2;
        i = i1;
        break;
        int i7 = a.g(paramParcel, n);
        int i8 = i;
        i2 = k;
        localObject2 = localObject1;
        i3 = i7;
        i1 = i8;
        continue;
        DriveId localDriveId = (DriveId)a.a(paramParcel, n, DriveId.CREATOR);
        i3 = m;
        int i6 = k;
        localObject2 = localDriveId;
        i1 = i;
        i2 = i6;
        continue;
        int i4 = a.g(paramParcel, n);
        localObject2 = localObject1;
        i3 = m;
        int i5 = i;
        i2 = i4;
        i1 = i5;
        continue;
        i1 = a.g(paramParcel, n);
        i2 = k;
        localObject2 = localObject1;
        i3 = m;
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new OpenContentsRequest(m, localObject1, k, i);
  }
  
  public OpenContentsRequest[] bI(int paramInt)
  {
    return new OpenContentsRequest[paramInt];
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.internal.au
 * JD-Core Version:    0.7.0.1
 */