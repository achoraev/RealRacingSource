package com.google.android.gms.games.multiplayer;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.games.GameEntity;
import java.util.ArrayList;

public class InvitationEntityCreator
  implements Parcelable.Creator<InvitationEntity>
{
  static void a(InvitationEntity paramInvitationEntity, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.a(paramParcel, 1, paramInvitationEntity.getGame(), paramInt, false);
    b.c(paramParcel, 1000, paramInvitationEntity.getVersionCode());
    b.a(paramParcel, 2, paramInvitationEntity.getInvitationId(), false);
    b.a(paramParcel, 3, paramInvitationEntity.getCreationTimestamp());
    b.c(paramParcel, 4, paramInvitationEntity.getInvitationType());
    b.a(paramParcel, 5, paramInvitationEntity.getInviter(), paramInt, false);
    b.c(paramParcel, 6, paramInvitationEntity.getParticipants(), false);
    b.c(paramParcel, 7, paramInvitationEntity.getVariant());
    b.c(paramParcel, 8, paramInvitationEntity.getAvailableAutoMatchSlots());
    b.H(paramParcel, i);
  }
  
  public InvitationEntity cl(Parcel paramParcel)
  {
    ArrayList localArrayList = null;
    int i = 0;
    int j = a.C(paramParcel);
    long l = 0L;
    int k = 0;
    ParticipantEntity localParticipantEntity = null;
    int m = 0;
    String str = null;
    GameEntity localGameEntity = null;
    int n = 0;
    while (paramParcel.dataPosition() < j)
    {
      int i1 = a.B(paramParcel);
      switch (a.aD(i1))
      {
      default: 
        a.b(paramParcel, i1);
        break;
      case 1: 
        localGameEntity = (GameEntity)a.a(paramParcel, i1, GameEntity.CREATOR);
        break;
      case 1000: 
        n = a.g(paramParcel, i1);
        break;
      case 2: 
        str = a.o(paramParcel, i1);
        break;
      case 3: 
        l = a.i(paramParcel, i1);
        break;
      case 4: 
        m = a.g(paramParcel, i1);
        break;
      case 5: 
        localParticipantEntity = (ParticipantEntity)a.a(paramParcel, i1, ParticipantEntity.CREATOR);
        break;
      case 6: 
        localArrayList = a.c(paramParcel, i1, ParticipantEntity.CREATOR);
        break;
      case 7: 
        k = a.g(paramParcel, i1);
        break;
      case 8: 
        i = a.g(paramParcel, i1);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new InvitationEntity(n, localGameEntity, str, l, m, localParticipantEntity, localArrayList, k, i);
  }
  
  public InvitationEntity[] dS(int paramInt)
  {
    return new InvitationEntity[paramInt];
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.multiplayer.InvitationEntityCreator
 * JD-Core Version:    0.7.0.1
 */