package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.DriveId;

public class aw
  implements Parcelable.Creator<OpenFileIntentSenderRequest>
{
  static void a(OpenFileIntentSenderRequest paramOpenFileIntentSenderRequest, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.c(paramParcel, 1, paramOpenFileIntentSenderRequest.BR);
    b.a(paramParcel, 2, paramOpenFileIntentSenderRequest.Nw, false);
    b.a(paramParcel, 3, paramOpenFileIntentSenderRequest.Nx, false);
    b.a(paramParcel, 4, paramOpenFileIntentSenderRequest.Ny, paramInt, false);
    b.H(paramParcel, i);
  }
  
  public OpenFileIntentSenderRequest ax(Parcel paramParcel)
  {
    DriveId localDriveId = null;
    int i = a.C(paramParcel);
    int j = 0;
    String[] arrayOfString = null;
    String str = null;
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
        str = a.o(paramParcel, k);
        break;
      case 3: 
        arrayOfString = a.A(paramParcel, k);
        break;
      case 4: 
        localDriveId = (DriveId)a.a(paramParcel, k, DriveId.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new a.a("Overread allowed size end=" + i, paramParcel);
    }
    return new OpenFileIntentSenderRequest(j, str, arrayOfString, localDriveId);
  }
  
  public OpenFileIntentSenderRequest[] bJ(int paramInt)
  {
    return new OpenFileIntentSenderRequest[paramInt];
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.internal.aw
 * JD-Core Version:    0.7.0.1
 */