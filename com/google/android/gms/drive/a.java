package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class a
  implements Parcelable.Creator<Contents>
{
  static void a(Contents paramContents, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.c(paramParcel, 1, paramContents.BR);
    b.a(paramParcel, 2, paramContents.KE, paramInt, false);
    b.c(paramParcel, 3, paramContents.uQ);
    b.c(paramParcel, 4, paramContents.MV);
    b.a(paramParcel, 5, paramContents.MW, paramInt, false);
    b.a(paramParcel, 7, paramContents.MX);
    b.H(paramParcel, i);
  }
  
  public Contents N(Parcel paramParcel)
  {
    DriveId localDriveId = null;
    boolean bool = false;
    int i = com.google.android.gms.common.internal.safeparcel.a.C(paramParcel);
    int j = 0;
    int k = 0;
    ParcelFileDescriptor localParcelFileDescriptor = null;
    int m = 0;
    while (paramParcel.dataPosition() < i)
    {
      int n = com.google.android.gms.common.internal.safeparcel.a.B(paramParcel);
      switch (com.google.android.gms.common.internal.safeparcel.a.aD(n))
      {
      case 6: 
      default: 
        com.google.android.gms.common.internal.safeparcel.a.b(paramParcel, n);
        break;
      case 1: 
        m = com.google.android.gms.common.internal.safeparcel.a.g(paramParcel, n);
        break;
      case 2: 
        localParcelFileDescriptor = (ParcelFileDescriptor)com.google.android.gms.common.internal.safeparcel.a.a(paramParcel, n, ParcelFileDescriptor.CREATOR);
        break;
      case 3: 
        k = com.google.android.gms.common.internal.safeparcel.a.g(paramParcel, n);
        break;
      case 4: 
        j = com.google.android.gms.common.internal.safeparcel.a.g(paramParcel, n);
        break;
      case 5: 
        localDriveId = (DriveId)com.google.android.gms.common.internal.safeparcel.a.a(paramParcel, n, DriveId.CREATOR);
        break;
      case 7: 
        bool = com.google.android.gms.common.internal.safeparcel.a.c(paramParcel, n);
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new a.a("Overread allowed size end=" + i, paramParcel);
    }
    return new Contents(m, localParcelFileDescriptor, k, j, localDriveId, bool);
  }
  
  public Contents[] aS(int paramInt)
  {
    return new Contents[paramInt];
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.a
 * JD-Core Version:    0.7.0.1
 */