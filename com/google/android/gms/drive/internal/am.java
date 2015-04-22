package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.events.ChangeEvent;
import com.google.android.gms.drive.events.CompletionEvent;

public class am
  implements Parcelable.Creator<OnEventResponse>
{
  static void a(OnEventResponse paramOnEventResponse, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.c(paramParcel, 1, paramOnEventResponse.BR);
    b.c(paramParcel, 2, paramOnEventResponse.Oa);
    b.a(paramParcel, 3, paramOnEventResponse.Ps, paramInt, false);
    b.a(paramParcel, 5, paramOnEventResponse.Pt, paramInt, false);
    b.H(paramParcel, i);
  }
  
  public OnEventResponse ao(Parcel paramParcel)
  {
    Object localObject1 = null;
    int i = 0;
    int j = a.C(paramParcel);
    Object localObject2 = null;
    int k = 0;
    if (paramParcel.dataPosition() < j)
    {
      int m = a.B(paramParcel);
      Object localObject3;
      Object localObject4;
      int n;
      int i1;
      switch (a.aD(m))
      {
      case 4: 
      default: 
        a.b(paramParcel, m);
        localObject3 = localObject1;
        localObject4 = localObject2;
        n = i;
        i1 = k;
      }
      for (;;)
      {
        k = i1;
        i = n;
        localObject2 = localObject4;
        localObject1 = localObject3;
        break;
        int i3 = a.g(paramParcel, m);
        Object localObject7 = localObject1;
        localObject4 = localObject2;
        n = i;
        i1 = i3;
        localObject3 = localObject7;
        continue;
        int i2 = a.g(paramParcel, m);
        i1 = k;
        Object localObject6 = localObject2;
        n = i2;
        localObject3 = localObject1;
        localObject4 = localObject6;
        continue;
        ChangeEvent localChangeEvent = (ChangeEvent)a.a(paramParcel, m, ChangeEvent.CREATOR);
        n = i;
        i1 = k;
        Object localObject5 = localObject1;
        localObject4 = localChangeEvent;
        localObject3 = localObject5;
        continue;
        localObject3 = (CompletionEvent)a.a(paramParcel, m, CompletionEvent.CREATOR);
        localObject4 = localObject2;
        n = i;
        i1 = k;
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new OnEventResponse(k, i, localObject2, localObject1);
  }
  
  public OnEventResponse[] bA(int paramInt)
  {
    return new OnEventResponse[paramInt];
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.internal.am
 * JD-Core Version:    0.7.0.1
 */