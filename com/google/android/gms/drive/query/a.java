package com.google.android.gms.drive.query;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.query.internal.LogicalFilter;
import java.util.ArrayList;

public class a
  implements Parcelable.Creator<Query>
{
  static void a(Query paramQuery, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.c(paramParcel, 1000, paramQuery.BR);
    b.a(paramParcel, 1, paramQuery.QB, paramInt, false);
    b.a(paramParcel, 3, paramQuery.QC, false);
    b.a(paramParcel, 4, paramQuery.QD, paramInt, false);
    b.b(paramParcel, 5, paramQuery.QE, false);
    b.H(paramParcel, i);
  }
  
  public Query aI(Parcel paramParcel)
  {
    ArrayList localArrayList = null;
    int i = com.google.android.gms.common.internal.safeparcel.a.C(paramParcel);
    int j = 0;
    SortOrder localSortOrder = null;
    String str = null;
    LogicalFilter localLogicalFilter = null;
    while (paramParcel.dataPosition() < i)
    {
      int k = com.google.android.gms.common.internal.safeparcel.a.B(paramParcel);
      switch (com.google.android.gms.common.internal.safeparcel.a.aD(k))
      {
      default: 
        com.google.android.gms.common.internal.safeparcel.a.b(paramParcel, k);
        break;
      case 1000: 
        j = com.google.android.gms.common.internal.safeparcel.a.g(paramParcel, k);
        break;
      case 1: 
        localLogicalFilter = (LogicalFilter)com.google.android.gms.common.internal.safeparcel.a.a(paramParcel, k, LogicalFilter.CREATOR);
        break;
      case 3: 
        str = com.google.android.gms.common.internal.safeparcel.a.o(paramParcel, k);
        break;
      case 4: 
        localSortOrder = (SortOrder)com.google.android.gms.common.internal.safeparcel.a.a(paramParcel, k, SortOrder.CREATOR);
        break;
      case 5: 
        localArrayList = com.google.android.gms.common.internal.safeparcel.a.C(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new a.a("Overread allowed size end=" + i, paramParcel);
    }
    return new Query(j, localLogicalFilter, str, localSortOrder, localArrayList);
  }
  
  public Query[] bU(int paramInt)
  {
    return new Query[paramInt];
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.query.a
 * JD-Core Version:    0.7.0.1
 */