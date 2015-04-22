package com.google.android.gms.games.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class ConnectionInfoCreator
  implements Parcelable.Creator<ConnectionInfo>
{
  static void a(ConnectionInfo paramConnectionInfo, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.a(paramParcel, 1, paramConnectionInfo.jX(), false);
    b.c(paramParcel, 1000, paramConnectionInfo.getVersionCode());
    b.c(paramParcel, 2, paramConnectionInfo.jY());
    b.H(paramParcel, i);
  }
  
  public ConnectionInfo cf(Parcel paramParcel)
  {
    int i = 0;
    int j = a.C(paramParcel);
    String str = null;
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
        str = a.o(paramParcel, m);
        break;
      case 1000: 
        k = a.g(paramParcel, m);
        break;
      case 2: 
        i = a.g(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new ConnectionInfo(k, str, i);
  }
  
  public ConnectionInfo[] dA(int paramInt)
  {
    return new ConnectionInfo[paramInt];
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.internal.ConnectionInfoCreator
 * JD-Core Version:    0.7.0.1
 */