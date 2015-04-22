package com.google.android.gms.drive.realtime.internal.event;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class i
  implements Parcelable.Creator<ValuesRemovedDetails>
{
  static void a(ValuesRemovedDetails paramValuesRemovedDetails, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.c(paramParcel, 1, paramValuesRemovedDetails.BR);
    b.c(paramParcel, 2, paramValuesRemovedDetails.mIndex);
    b.c(paramParcel, 3, paramValuesRemovedDetails.Rr);
    b.c(paramParcel, 4, paramValuesRemovedDetails.Rs);
    b.a(paramParcel, 5, paramValuesRemovedDetails.RP, false);
    b.c(paramParcel, 6, paramValuesRemovedDetails.RQ);
    b.H(paramParcel, i);
  }
  
  public ValuesRemovedDetails bg(Parcel paramParcel)
  {
    int i = 0;
    int j = a.C(paramParcel);
    String str = null;
    int k = 0;
    int m = 0;
    int n = 0;
    int i1 = 0;
    while (paramParcel.dataPosition() < j)
    {
      int i2 = a.B(paramParcel);
      switch (a.aD(i2))
      {
      default: 
        a.b(paramParcel, i2);
        break;
      case 1: 
        i1 = a.g(paramParcel, i2);
        break;
      case 2: 
        n = a.g(paramParcel, i2);
        break;
      case 3: 
        m = a.g(paramParcel, i2);
        break;
      case 4: 
        k = a.g(paramParcel, i2);
        break;
      case 5: 
        str = a.o(paramParcel, i2);
        break;
      case 6: 
        i = a.g(paramParcel, i2);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new ValuesRemovedDetails(i1, n, m, k, str, i);
  }
  
  public ValuesRemovedDetails[] ct(int paramInt)
  {
    return new ValuesRemovedDetails[paramInt];
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.realtime.internal.event.i
 * JD-Core Version:    0.7.0.1
 */