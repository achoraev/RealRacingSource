package com.google.android.gms.drive.realtime.internal.event;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class j
  implements Parcelable.Creator<ValuesSetDetails>
{
  static void a(ValuesSetDetails paramValuesSetDetails, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.c(paramParcel, 1, paramValuesSetDetails.BR);
    b.c(paramParcel, 2, paramValuesSetDetails.mIndex);
    b.c(paramParcel, 3, paramValuesSetDetails.Rr);
    b.c(paramParcel, 4, paramValuesSetDetails.Rs);
    b.H(paramParcel, i);
  }
  
  public ValuesSetDetails bh(Parcel paramParcel)
  {
    int i = 0;
    int j = a.C(paramParcel);
    int k = 0;
    int m = 0;
    int n = 0;
    while (paramParcel.dataPosition() < j)
    {
      int i1 = a.B(paramParcel);
      switch (a.aD(i1))
      {
      default: 
        a.b(paramParcel, i1);
        break;
      case 1: 
        n = a.g(paramParcel, i1);
        break;
      case 2: 
        m = a.g(paramParcel, i1);
        break;
      case 3: 
        k = a.g(paramParcel, i1);
        break;
      case 4: 
        i = a.g(paramParcel, i1);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new ValuesSetDetails(n, m, k, i);
  }
  
  public ValuesSetDetails[] cu(int paramInt)
  {
    return new ValuesSetDetails[paramInt];
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.realtime.internal.event.j
 * JD-Core Version:    0.7.0.1
 */