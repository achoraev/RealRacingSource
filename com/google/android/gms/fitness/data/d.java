package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.ArrayList;

public class d
  implements Parcelable.Creator<Bucket>
{
  static void a(Bucket paramBucket, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.a(paramParcel, 1, paramBucket.iD());
    b.c(paramParcel, 1000, paramBucket.getVersionCode());
    b.a(paramParcel, 2, paramBucket.iE());
    b.a(paramParcel, 3, paramBucket.getSession(), paramInt, false);
    b.c(paramParcel, 4, paramBucket.iB());
    b.c(paramParcel, 5, paramBucket.getDataSets(), false);
    b.c(paramParcel, 6, paramBucket.getBucketType());
    b.a(paramParcel, 7, paramBucket.iC());
    b.H(paramParcel, i);
  }
  
  public Bucket bk(Parcel paramParcel)
  {
    long l1 = 0L;
    ArrayList localArrayList = null;
    boolean bool = false;
    int i = a.C(paramParcel);
    int j = 0;
    int k = 0;
    Session localSession = null;
    long l2 = l1;
    int m = 0;
    while (paramParcel.dataPosition() < i)
    {
      int n = a.B(paramParcel);
      switch (a.aD(n))
      {
      default: 
        a.b(paramParcel, n);
        break;
      case 1: 
        l2 = a.i(paramParcel, n);
        break;
      case 1000: 
        m = a.g(paramParcel, n);
        break;
      case 2: 
        l1 = a.i(paramParcel, n);
        break;
      case 3: 
        localSession = (Session)a.a(paramParcel, n, Session.CREATOR);
        break;
      case 4: 
        k = a.g(paramParcel, n);
        break;
      case 5: 
        localArrayList = a.c(paramParcel, n, DataSet.CREATOR);
        break;
      case 6: 
        j = a.g(paramParcel, n);
        break;
      case 7: 
        bool = a.c(paramParcel, n);
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new a.a("Overread allowed size end=" + i, paramParcel);
    }
    return new Bucket(m, l2, l1, localSession, k, localArrayList, j, bool);
  }
  
  public Bucket[] cz(int paramInt)
  {
    return new Bucket[paramInt];
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.fitness.data.d
 * JD-Core Version:    0.7.0.1
 */