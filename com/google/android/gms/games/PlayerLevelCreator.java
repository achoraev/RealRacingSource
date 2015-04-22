package com.google.android.gms.games;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class PlayerLevelCreator
  implements Parcelable.Creator<PlayerLevel>
{
  public static final int CONTENT_DESCRIPTION;
  
  static void a(PlayerLevel paramPlayerLevel, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.c(paramParcel, 1, paramPlayerLevel.getLevelNumber());
    b.c(paramParcel, 1000, paramPlayerLevel.getVersionCode());
    b.a(paramParcel, 2, paramPlayerLevel.getMinXp());
    b.a(paramParcel, 3, paramPlayerLevel.getMaxXp());
    b.H(paramParcel, i);
  }
  
  public PlayerLevel createFromParcel(Parcel paramParcel)
  {
    long l1 = 0L;
    int i = 0;
    int j = a.C(paramParcel);
    long l2 = l1;
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
        i = a.g(paramParcel, m);
        break;
      case 1000: 
        k = a.g(paramParcel, m);
        break;
      case 2: 
        l2 = a.i(paramParcel, m);
        break;
      case 3: 
        l1 = a.i(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new PlayerLevel(k, i, l2, l1);
  }
  
  public PlayerLevel[] newArray(int paramInt)
  {
    return new PlayerLevel[paramInt];
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.PlayerLevelCreator
 * JD-Core Version:    0.7.0.1
 */