package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.ArrayList;

public class f
  implements Parcelable.Creator<RealtimeDocumentSyncRequest>
{
  static void a(RealtimeDocumentSyncRequest paramRealtimeDocumentSyncRequest, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.c(paramParcel, 1, paramRealtimeDocumentSyncRequest.BR);
    b.b(paramParcel, 2, paramRealtimeDocumentSyncRequest.Nz, false);
    b.b(paramParcel, 3, paramRealtimeDocumentSyncRequest.NA, false);
    b.H(paramParcel, i);
  }
  
  public RealtimeDocumentSyncRequest Q(Parcel paramParcel)
  {
    ArrayList localArrayList1 = null;
    int i = a.C(paramParcel);
    int j = 0;
    ArrayList localArrayList2 = null;
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
        localArrayList2 = a.C(paramParcel, k);
        break;
      case 3: 
        localArrayList1 = a.C(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new a.a("Overread allowed size end=" + i, paramParcel);
    }
    return new RealtimeDocumentSyncRequest(j, localArrayList2, localArrayList1);
  }
  
  public RealtimeDocumentSyncRequest[] aX(int paramInt)
  {
    return new RealtimeDocumentSyncRequest[paramInt];
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.f
 * JD-Core Version:    0.7.0.1
 */