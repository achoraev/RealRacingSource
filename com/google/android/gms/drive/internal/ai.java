package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.Contents;

public class ai
  implements Parcelable.Creator<OnContentsResponse>
{
  static void a(OnContentsResponse paramOnContentsResponse, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.c(paramParcel, 1, paramOnContentsResponse.BR);
    b.a(paramParcel, 2, paramOnContentsResponse.Ox, paramInt, false);
    b.a(paramParcel, 3, paramOnContentsResponse.Po);
    b.H(paramParcel, i);
  }
  
  public OnContentsResponse ak(Parcel paramParcel)
  {
    boolean bool1 = false;
    int i = a.C(paramParcel);
    Object localObject1 = null;
    int j = 0;
    if (paramParcel.dataPosition() < i)
    {
      int k = a.B(paramParcel);
      boolean bool2;
      Object localObject2;
      int m;
      switch (a.aD(k))
      {
      default: 
        a.b(paramParcel, k);
        bool2 = bool1;
        localObject2 = localObject1;
        m = j;
      }
      for (;;)
      {
        j = m;
        localObject1 = localObject2;
        bool1 = bool2;
        break;
        int n = a.g(paramParcel, k);
        boolean bool3 = bool1;
        localObject2 = localObject1;
        m = n;
        bool2 = bool3;
        continue;
        Contents localContents = (Contents)a.a(paramParcel, k, Contents.CREATOR);
        m = j;
        bool2 = bool1;
        localObject2 = localContents;
        continue;
        bool2 = a.c(paramParcel, k);
        localObject2 = localObject1;
        m = j;
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new a.a("Overread allowed size end=" + i, paramParcel);
    }
    return new OnContentsResponse(j, localObject1, bool1);
  }
  
  public OnContentsResponse[] bw(int paramInt)
  {
    return new OnContentsResponse[paramInt];
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.internal.ai
 * JD-Core Version:    0.7.0.1
 */