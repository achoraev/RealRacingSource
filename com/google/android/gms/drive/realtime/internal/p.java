package com.google.android.gms.drive.realtime.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class p
  implements Parcelable.Creator<ParcelableCollaborator>
{
  static void a(ParcelableCollaborator paramParcelableCollaborator, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.c(paramParcel, 1, paramParcelableCollaborator.BR);
    b.a(paramParcel, 2, paramParcelableCollaborator.Rk);
    b.a(paramParcel, 3, paramParcelableCollaborator.Rl);
    b.a(paramParcel, 4, paramParcelableCollaborator.vL, false);
    b.a(paramParcel, 5, paramParcelableCollaborator.Rm, false);
    b.a(paramParcel, 6, paramParcelableCollaborator.NH, false);
    b.a(paramParcel, 7, paramParcelableCollaborator.Rn, false);
    b.a(paramParcel, 8, paramParcelableCollaborator.Ro, false);
    b.H(paramParcel, i);
  }
  
  public ParcelableCollaborator aW(Parcel paramParcel)
  {
    boolean bool1 = false;
    String str1 = null;
    int i = a.C(paramParcel);
    String str2 = null;
    String str3 = null;
    String str4 = null;
    String str5 = null;
    boolean bool2 = false;
    int j = 0;
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
        bool2 = a.c(paramParcel, k);
        break;
      case 3: 
        bool1 = a.c(paramParcel, k);
        break;
      case 4: 
        str5 = a.o(paramParcel, k);
        break;
      case 5: 
        str4 = a.o(paramParcel, k);
        break;
      case 6: 
        str3 = a.o(paramParcel, k);
        break;
      case 7: 
        str2 = a.o(paramParcel, k);
        break;
      case 8: 
        str1 = a.o(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new a.a("Overread allowed size end=" + i, paramParcel);
    }
    return new ParcelableCollaborator(j, bool2, bool1, str5, str4, str3, str2, str1);
  }
  
  public ParcelableCollaborator[] cj(int paramInt)
  {
    return new ParcelableCollaborator[paramInt];
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.realtime.internal.p
 * JD-Core Version:    0.7.0.1
 */