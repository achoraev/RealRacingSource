package com.google.android.gms.drive.realtime.internal.event;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class e
  implements Parcelable.Creator<TextDeletedDetails>
{
  static void a(TextDeletedDetails paramTextDeletedDetails, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.c(paramParcel, 1, paramTextDeletedDetails.BR);
    b.c(paramParcel, 2, paramTextDeletedDetails.mIndex);
    b.c(paramParcel, 3, paramTextDeletedDetails.RL);
    b.H(paramParcel, i);
  }
  
  public TextDeletedDetails bc(Parcel paramParcel)
  {
    int i = 0;
    int j = a.C(paramParcel);
    int k = 0;
    int m = 0;
    while (paramParcel.dataPosition() < j)
    {
      int n = a.B(paramParcel);
      switch (a.aD(n))
      {
      default: 
        a.b(paramParcel, n);
        break;
      case 1: 
        m = a.g(paramParcel, n);
        break;
      case 2: 
        k = a.g(paramParcel, n);
        break;
      case 3: 
        i = a.g(paramParcel, n);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new TextDeletedDetails(m, k, i);
  }
  
  public TextDeletedDetails[] cp(int paramInt)
  {
    return new TextDeletedDetails[paramInt];
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.realtime.internal.event.e
 * JD-Core Version:    0.7.0.1
 */