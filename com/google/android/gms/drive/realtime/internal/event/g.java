package com.google.android.gms.drive.realtime.internal.event;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class g
  implements Parcelable.Creator<ValueChangedDetails>
{
  static void a(ValueChangedDetails paramValueChangedDetails, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.c(paramParcel, 1, paramValueChangedDetails.BR);
    b.c(paramParcel, 2, paramValueChangedDetails.RM);
    b.H(paramParcel, i);
  }
  
  public ValueChangedDetails be(Parcel paramParcel)
  {
    int i = 0;
    int j = a.C(paramParcel);
    int k = 0;
    while (paramParcel.dataPosition() < j)
    {
      int m = a.B(paramParcel);
      switch (a.aD(m))
      {
      default: 
        a.b(paramParcel, m);
        break;
      case 1: 
        k = a.g(paramParcel, m);
        break;
      case 2: 
        i = a.g(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new ValueChangedDetails(k, i);
  }
  
  public ValueChangedDetails[] cr(int paramInt)
  {
    return new ValueChangedDetails[paramInt];
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.realtime.internal.event.g
 * JD-Core Version:    0.7.0.1
 */