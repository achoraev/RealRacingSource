package com.google.android.gms.games.internal.game;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.snapshot.SnapshotMetadataEntity;
import java.util.ArrayList;

public class ExtendedGameEntityCreator
  implements Parcelable.Creator<ExtendedGameEntity>
{
  static void a(ExtendedGameEntity paramExtendedGameEntity, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.a(paramParcel, 1, paramExtendedGameEntity.lb(), paramInt, false);
    b.c(paramParcel, 1000, paramExtendedGameEntity.getVersionCode());
    b.c(paramParcel, 2, paramExtendedGameEntity.kS());
    b.a(paramParcel, 3, paramExtendedGameEntity.kT());
    b.c(paramParcel, 4, paramExtendedGameEntity.kU());
    b.a(paramParcel, 5, paramExtendedGameEntity.kV());
    b.a(paramParcel, 6, paramExtendedGameEntity.kW());
    b.a(paramParcel, 7, paramExtendedGameEntity.kX(), false);
    b.a(paramParcel, 8, paramExtendedGameEntity.kY());
    b.a(paramParcel, 9, paramExtendedGameEntity.kZ(), false);
    b.c(paramParcel, 10, paramExtendedGameEntity.kR(), false);
    b.a(paramParcel, 11, paramExtendedGameEntity.la(), paramInt, false);
    b.H(paramParcel, i);
  }
  
  public ExtendedGameEntity cg(Parcel paramParcel)
  {
    int i = a.C(paramParcel);
    int j = 0;
    GameEntity localGameEntity = null;
    int k = 0;
    boolean bool = false;
    int m = 0;
    long l1 = 0L;
    long l2 = 0L;
    String str1 = null;
    long l3 = 0L;
    String str2 = null;
    ArrayList localArrayList = null;
    SnapshotMetadataEntity localSnapshotMetadataEntity = null;
    while (paramParcel.dataPosition() < i)
    {
      int n = a.B(paramParcel);
      switch (a.aD(n))
      {
      default: 
        a.b(paramParcel, n);
        break;
      case 1: 
        localGameEntity = (GameEntity)a.a(paramParcel, n, GameEntity.CREATOR);
        break;
      case 1000: 
        j = a.g(paramParcel, n);
        break;
      case 2: 
        k = a.g(paramParcel, n);
        break;
      case 3: 
        bool = a.c(paramParcel, n);
        break;
      case 4: 
        m = a.g(paramParcel, n);
        break;
      case 5: 
        l1 = a.i(paramParcel, n);
        break;
      case 6: 
        l2 = a.i(paramParcel, n);
        break;
      case 7: 
        str1 = a.o(paramParcel, n);
        break;
      case 8: 
        l3 = a.i(paramParcel, n);
        break;
      case 9: 
        str2 = a.o(paramParcel, n);
        break;
      case 10: 
        localArrayList = a.c(paramParcel, n, GameBadgeEntity.CREATOR);
        break;
      case 11: 
        localSnapshotMetadataEntity = (SnapshotMetadataEntity)a.a(paramParcel, n, SnapshotMetadataEntity.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new a.a("Overread allowed size end=" + i, paramParcel);
    }
    return new ExtendedGameEntity(j, localGameEntity, k, bool, m, l1, l2, str1, l3, str2, localArrayList, localSnapshotMetadataEntity);
  }
  
  public ExtendedGameEntity[] dJ(int paramInt)
  {
    return new ExtendedGameEntity[paramInt];
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.internal.game.ExtendedGameEntityCreator
 * JD-Core Version:    0.7.0.1
 */