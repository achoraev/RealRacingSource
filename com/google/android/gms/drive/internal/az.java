package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.DrivePreferences;

public class az
  implements Parcelable.Creator<SetDrivePreferencesRequest>
{
  static void a(SetDrivePreferencesRequest paramSetDrivePreferencesRequest, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.c(paramParcel, 1, paramSetDrivePreferencesRequest.BR);
    b.a(paramParcel, 2, paramSetDrivePreferencesRequest.Pr, paramInt, false);
    b.H(paramParcel, i);
  }
  
  public SetDrivePreferencesRequest aA(Parcel paramParcel)
  {
    int i = a.C(paramParcel);
    int j = 0;
    DrivePreferences localDrivePreferences = null;
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
        localDrivePreferences = (DrivePreferences)a.a(paramParcel, k, DrivePreferences.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new a.a("Overread allowed size end=" + i, paramParcel);
    }
    return new SetDrivePreferencesRequest(j, localDrivePreferences);
  }
  
  public SetDrivePreferencesRequest[] bM(int paramInt)
  {
    return new SetDrivePreferencesRequest[paramInt];
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.internal.az
 * JD-Core Version:    0.7.0.1
 */