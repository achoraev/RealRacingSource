package com.google.android.gms.games.snapshot;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class SnapshotMetadataChangeCreator
  implements Parcelable.Creator<SnapshotMetadataChange>
{
  public static final int CONTENT_DESCRIPTION;
  
  static void a(SnapshotMetadataChange paramSnapshotMetadataChange, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.a(paramParcel, 1, paramSnapshotMetadataChange.getDescription(), false);
    b.c(paramParcel, 1000, paramSnapshotMetadataChange.getVersionCode());
    b.a(paramParcel, 2, paramSnapshotMetadataChange.getPlayedTimeMillis(), false);
    b.a(paramParcel, 4, paramSnapshotMetadataChange.getCoverImageUri(), paramInt, false);
    b.a(paramParcel, 5, paramSnapshotMetadataChange.lM(), paramInt, false);
    b.H(paramParcel, i);
  }
  
  public SnapshotMetadataChange createFromParcel(Parcel paramParcel)
  {
    Uri localUri = null;
    int i = com.google.android.gms.common.internal.safeparcel.a.C(paramParcel);
    int j = 0;
    com.google.android.gms.common.data.a locala = null;
    Long localLong = null;
    String str = null;
    while (paramParcel.dataPosition() < i)
    {
      int k = com.google.android.gms.common.internal.safeparcel.a.B(paramParcel);
      switch (com.google.android.gms.common.internal.safeparcel.a.aD(k))
      {
      default: 
        com.google.android.gms.common.internal.safeparcel.a.b(paramParcel, k);
        break;
      case 1: 
        str = com.google.android.gms.common.internal.safeparcel.a.o(paramParcel, k);
        break;
      case 1000: 
        j = com.google.android.gms.common.internal.safeparcel.a.g(paramParcel, k);
        break;
      case 2: 
        localLong = com.google.android.gms.common.internal.safeparcel.a.j(paramParcel, k);
        break;
      case 4: 
        localUri = (Uri)com.google.android.gms.common.internal.safeparcel.a.a(paramParcel, k, Uri.CREATOR);
        break;
      case 5: 
        locala = (com.google.android.gms.common.data.a)com.google.android.gms.common.internal.safeparcel.a.a(paramParcel, k, com.google.android.gms.common.data.a.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new a.a("Overread allowed size end=" + i, paramParcel);
    }
    return new SnapshotMetadataChange(j, str, localLong, locala, localUri);
  }
  
  public SnapshotMetadataChange[] newArray(int paramInt)
  {
    return new SnapshotMetadataChange[paramInt];
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.snapshot.SnapshotMetadataChangeCreator
 * JD-Core Version:    0.7.0.1
 */