package com.google.android.gms.drive.events;

import android.os.IBinder;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import java.util.ArrayList;

public class b
  implements Parcelable.Creator<CompletionEvent>
{
  static void a(CompletionEvent paramCompletionEvent, Parcel paramParcel, int paramInt)
  {
    int i = com.google.android.gms.common.internal.safeparcel.b.D(paramParcel);
    com.google.android.gms.common.internal.safeparcel.b.c(paramParcel, 1, paramCompletionEvent.BR);
    com.google.android.gms.common.internal.safeparcel.b.a(paramParcel, 2, paramCompletionEvent.MW, paramInt, false);
    com.google.android.gms.common.internal.safeparcel.b.a(paramParcel, 3, paramCompletionEvent.Dd, false);
    com.google.android.gms.common.internal.safeparcel.b.a(paramParcel, 4, paramCompletionEvent.NN, paramInt, false);
    com.google.android.gms.common.internal.safeparcel.b.a(paramParcel, 5, paramCompletionEvent.NO, paramInt, false);
    com.google.android.gms.common.internal.safeparcel.b.a(paramParcel, 6, paramCompletionEvent.NP, paramInt, false);
    com.google.android.gms.common.internal.safeparcel.b.b(paramParcel, 7, paramCompletionEvent.NQ, false);
    com.google.android.gms.common.internal.safeparcel.b.c(paramParcel, 8, paramCompletionEvent.Fa);
    com.google.android.gms.common.internal.safeparcel.b.a(paramParcel, 9, paramCompletionEvent.NR, false);
    com.google.android.gms.common.internal.safeparcel.b.H(paramParcel, i);
  }
  
  public CompletionEvent U(Parcel paramParcel)
  {
    int i = 0;
    IBinder localIBinder = null;
    int j = a.C(paramParcel);
    ArrayList localArrayList = null;
    MetadataBundle localMetadataBundle = null;
    ParcelFileDescriptor localParcelFileDescriptor1 = null;
    ParcelFileDescriptor localParcelFileDescriptor2 = null;
    String str = null;
    DriveId localDriveId = null;
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
        localDriveId = (DriveId)a.a(paramParcel, m, DriveId.CREATOR);
        break;
      case 3: 
        str = a.o(paramParcel, m);
        break;
      case 4: 
        localParcelFileDescriptor2 = (ParcelFileDescriptor)a.a(paramParcel, m, ParcelFileDescriptor.CREATOR);
        break;
      case 5: 
        localParcelFileDescriptor1 = (ParcelFileDescriptor)a.a(paramParcel, m, ParcelFileDescriptor.CREATOR);
        break;
      case 6: 
        localMetadataBundle = (MetadataBundle)a.a(paramParcel, m, MetadataBundle.CREATOR);
        break;
      case 7: 
        localArrayList = a.C(paramParcel, m);
        break;
      case 8: 
        i = a.g(paramParcel, m);
        break;
      case 9: 
        localIBinder = a.p(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new CompletionEvent(k, localDriveId, str, localParcelFileDescriptor2, localParcelFileDescriptor1, localMetadataBundle, localArrayList, i, localIBinder);
  }
  
  public CompletionEvent[] bb(int paramInt)
  {
    return new CompletionEvent[paramInt];
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.events.b
 * JD-Core Version:    0.7.0.1
 */