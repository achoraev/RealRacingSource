package com.google.android.gms.drive.realtime.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;

public class b
  implements Parcelable.Creator<EndCompoundOperationRequest>
{
  static void a(EndCompoundOperationRequest paramEndCompoundOperationRequest, Parcel paramParcel, int paramInt)
  {
    int i = com.google.android.gms.common.internal.safeparcel.b.D(paramParcel);
    com.google.android.gms.common.internal.safeparcel.b.c(paramParcel, 1, paramEndCompoundOperationRequest.BR);
    com.google.android.gms.common.internal.safeparcel.b.H(paramParcel, i);
  }
  
  public EndCompoundOperationRequest aV(Parcel paramParcel)
  {
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
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new a.a("Overread allowed size end=" + i, paramParcel);
    }
    return new EndCompoundOperationRequest(j);
  }
  
  public EndCompoundOperationRequest[] ch(int paramInt)
  {
    return new EndCompoundOperationRequest[paramInt];
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.realtime.internal.b
 * JD-Core Version:    0.7.0.1
 */