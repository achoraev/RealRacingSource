package com.google.android.gms.games.multiplayer;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class ParticipantResultCreator
  implements Parcelable.Creator<ParticipantResult>
{
  public static final int CONTENT_DESCRIPTION;
  
  static void a(ParticipantResult paramParticipantResult, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.a(paramParcel, 1, paramParticipantResult.getParticipantId(), false);
    b.c(paramParcel, 1000, paramParticipantResult.getVersionCode());
    b.c(paramParcel, 2, paramParticipantResult.getResult());
    b.c(paramParcel, 3, paramParticipantResult.getPlacing());
    b.H(paramParcel, i);
  }
  
  public ParticipantResult createFromParcel(Parcel paramParcel)
  {
    int i = 0;
    int j = a.C(paramParcel);
    String str = null;
    int k = 0;
    int m = 0;
    while (paramParcel.dataPosition() < j)
    {
      int n = a.B(paramParcel);
      switch (a.aD(n))
      {
      default: 
        a.b(paramParcel, n);
        break;
      case 1: 
        str = a.o(paramParcel, n);
        break;
      case 1000: 
        m = a.g(paramParcel, n);
        break;
      case 2: 
        k = a.g(paramParcel, n);
        break;
      case 3: 
        i = a.g(paramParcel, n);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new ParticipantResult(m, str, k, i);
  }
  
  public ParticipantResult[] newArray(int paramInt)
  {
    return new ParticipantResult[paramInt];
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.multiplayer.ParticipantResultCreator
 * JD-Core Version:    0.7.0.1
 */