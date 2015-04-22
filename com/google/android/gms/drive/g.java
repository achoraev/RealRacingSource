package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class g
  implements Parcelable.Creator<StorageStats>
{
  static void a(StorageStats paramStorageStats, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.c(paramParcel, 1, paramStorageStats.BR);
    b.a(paramParcel, 2, paramStorageStats.NB);
    b.a(paramParcel, 3, paramStorageStats.NC);
    b.a(paramParcel, 4, paramStorageStats.ND);
    b.a(paramParcel, 5, paramStorageStats.NE);
    b.c(paramParcel, 6, paramStorageStats.NF);
    b.H(paramParcel, i);
  }
  
  public StorageStats R(Parcel paramParcel)
  {
    int i = 0;
    long l1 = 0L;
    int j = a.C(paramParcel);
    long l2 = l1;
    long l3 = l1;
    long l4 = l1;
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
        l4 = a.i(paramParcel, m);
        break;
      case 3: 
        l3 = a.i(paramParcel, m);
        break;
      case 4: 
        l2 = a.i(paramParcel, m);
        break;
      case 5: 
        l1 = a.i(paramParcel, m);
        break;
      case 6: 
        i = a.g(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new StorageStats(k, l4, l3, l2, l1, i);
  }
  
  public StorageStats[] aY(int paramInt)
  {
    return new StorageStats[paramInt];
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.g
 * JD-Core Version:    0.7.0.1
 */