package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public class h
  implements Parcelable.Creator<InFilter>
{
  static void a(InFilter paramInFilter, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.c(paramParcel, 1000, paramInFilter.BR);
    b.a(paramParcel, 1, paramInFilter.QL, paramInt, false);
    b.H(paramParcel, i);
  }
  
  public InFilter aP(Parcel paramParcel)
  {
    int i = a.C(paramParcel);
    int j = 0;
    MetadataBundle localMetadataBundle = null;
    while (paramParcel.dataPosition() < i)
    {
      int k = a.B(paramParcel);
      switch (a.aD(k))
      {
      default: 
        a.b(paramParcel, k);
        break;
      case 1000: 
        j = a.g(paramParcel, k);
        break;
      case 1: 
        localMetadataBundle = (MetadataBundle)a.a(paramParcel, k, MetadataBundle.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new a.a("Overread allowed size end=" + i, paramParcel);
    }
    return new InFilter(j, localMetadataBundle);
  }
  
  public InFilter[] cb(int paramInt)
  {
    return new InFilter[paramInt];
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.query.internal.h
 * JD-Core Version:    0.7.0.1
 */