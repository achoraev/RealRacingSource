package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class d
  implements Parcelable.Creator<DrivePreferences>
{
  static void a(DrivePreferences paramDrivePreferences, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.c(paramParcel, 1, paramDrivePreferences.BR);
    b.a(paramParcel, 2, paramDrivePreferences.Nm);
    b.H(paramParcel, i);
  }
  
  public DrivePreferences P(Parcel paramParcel)
  {
    boolean bool = false;
    int i = a.C(paramParcel);
    int j = 0;
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
        bool = a.c(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new a.a("Overread allowed size end=" + i, paramParcel);
    }
    return new DrivePreferences(j, bool);
  }
  
  public DrivePreferences[] aU(int paramInt)
  {
    return new DrivePreferences[paramInt];
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.d
 * JD-Core Version:    0.7.0.1
 */