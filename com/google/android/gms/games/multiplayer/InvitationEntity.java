package com.google.android.gms.games.multiplayer;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import java.util.ArrayList;

public final class InvitationEntity
  extends GamesDowngradeableSafeParcel
  implements Invitation
{
  public static final Parcelable.Creator<InvitationEntity> CREATOR = new InvitationEntityCreatorCompat();
  private final int BR;
  private final String WO;
  private final GameEntity aay;
  private final long abZ;
  private final int aca;
  private final ParticipantEntity acb;
  private final ArrayList<ParticipantEntity> acc;
  private final int acd;
  private final int ace;
  
  InvitationEntity(int paramInt1, GameEntity paramGameEntity, String paramString, long paramLong, int paramInt2, ParticipantEntity paramParticipantEntity, ArrayList<ParticipantEntity> paramArrayList, int paramInt3, int paramInt4)
  {
    this.BR = paramInt1;
    this.aay = paramGameEntity;
    this.WO = paramString;
    this.abZ = paramLong;
    this.aca = paramInt2;
    this.acb = paramParticipantEntity;
    this.acc = paramArrayList;
    this.acd = paramInt3;
    this.ace = paramInt4;
  }
  
  InvitationEntity(Invitation paramInvitation)
  {
    this.BR = 2;
    this.aay = new GameEntity(paramInvitation.getGame());
    this.WO = paramInvitation.getInvitationId();
    this.abZ = paramInvitation.getCreationTimestamp();
    this.aca = paramInvitation.getInvitationType();
    this.acd = paramInvitation.getVariant();
    this.ace = paramInvitation.getAvailableAutoMatchSlots();
    String str = paramInvitation.getInviter().getParticipantId();
    Object localObject = null;
    ArrayList localArrayList = paramInvitation.getParticipants();
    int i = localArrayList.size();
    this.acc = new ArrayList(i);
    for (int j = 0; j < i; j++)
    {
      Participant localParticipant = (Participant)localArrayList.get(j);
      if (localParticipant.getParticipantId().equals(str)) {
        localObject = localParticipant;
      }
      this.acc.add((ParticipantEntity)localParticipant.freeze());
    }
    o.b(localObject, "Must have a valid inviter!");
    this.acb = ((ParticipantEntity)localObject.freeze());
  }
  
  static int a(Invitation paramInvitation)
  {
    Object[] arrayOfObject = new Object[8];
    arrayOfObject[0] = paramInvitation.getGame();
    arrayOfObject[1] = paramInvitation.getInvitationId();
    arrayOfObject[2] = Long.valueOf(paramInvitation.getCreationTimestamp());
    arrayOfObject[3] = Integer.valueOf(paramInvitation.getInvitationType());
    arrayOfObject[4] = paramInvitation.getInviter();
    arrayOfObject[5] = paramInvitation.getParticipants();
    arrayOfObject[6] = Integer.valueOf(paramInvitation.getVariant());
    arrayOfObject[7] = Integer.valueOf(paramInvitation.getAvailableAutoMatchSlots());
    return n.hashCode(arrayOfObject);
  }
  
  static boolean a(Invitation paramInvitation, Object paramObject)
  {
    boolean bool = true;
    if (!(paramObject instanceof Invitation)) {
      bool = false;
    }
    Invitation localInvitation;
    do
    {
      do
      {
        return bool;
      } while (paramInvitation == paramObject);
      localInvitation = (Invitation)paramObject;
    } while ((n.equal(localInvitation.getGame(), paramInvitation.getGame())) && (n.equal(localInvitation.getInvitationId(), paramInvitation.getInvitationId())) && (n.equal(Long.valueOf(localInvitation.getCreationTimestamp()), Long.valueOf(paramInvitation.getCreationTimestamp()))) && (n.equal(Integer.valueOf(localInvitation.getInvitationType()), Integer.valueOf(paramInvitation.getInvitationType()))) && (n.equal(localInvitation.getInviter(), paramInvitation.getInviter())) && (n.equal(localInvitation.getParticipants(), paramInvitation.getParticipants())) && (n.equal(Integer.valueOf(localInvitation.getVariant()), Integer.valueOf(paramInvitation.getVariant()))) && (n.equal(Integer.valueOf(localInvitation.getAvailableAutoMatchSlots()), Integer.valueOf(paramInvitation.getAvailableAutoMatchSlots()))));
    return false;
  }
  
  static String b(Invitation paramInvitation)
  {
    return n.h(paramInvitation).a("Game", paramInvitation.getGame()).a("InvitationId", paramInvitation.getInvitationId()).a("CreationTimestamp", Long.valueOf(paramInvitation.getCreationTimestamp())).a("InvitationType", Integer.valueOf(paramInvitation.getInvitationType())).a("Inviter", paramInvitation.getInviter()).a("Participants", paramInvitation.getParticipants()).a("Variant", Integer.valueOf(paramInvitation.getVariant())).a("AvailableAutoMatchSlots", Integer.valueOf(paramInvitation.getAvailableAutoMatchSlots())).toString();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return a(this, paramObject);
  }
  
  public Invitation freeze()
  {
    return this;
  }
  
  public int getAvailableAutoMatchSlots()
  {
    return this.ace;
  }
  
  public long getCreationTimestamp()
  {
    return this.abZ;
  }
  
  public Game getGame()
  {
    return this.aay;
  }
  
  public String getInvitationId()
  {
    return this.WO;
  }
  
  public int getInvitationType()
  {
    return this.aca;
  }
  
  public Participant getInviter()
  {
    return this.acb;
  }
  
  public ArrayList<Participant> getParticipants()
  {
    return new ArrayList(this.acc);
  }
  
  public int getVariant()
  {
    return this.acd;
  }
  
  public int getVersionCode()
  {
    return this.BR;
  }
  
  public int hashCode()
  {
    return a(this);
  }
  
  public boolean isDataValid()
  {
    return true;
  }
  
  public String toString()
  {
    return b(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    if (!gQ()) {
      InvitationEntityCreator.a(this, paramParcel, paramInt);
    }
    for (;;)
    {
      return;
      this.aay.writeToParcel(paramParcel, paramInt);
      paramParcel.writeString(this.WO);
      paramParcel.writeLong(this.abZ);
      paramParcel.writeInt(this.aca);
      this.acb.writeToParcel(paramParcel, paramInt);
      int i = this.acc.size();
      paramParcel.writeInt(i);
      for (int j = 0; j < i; j++) {
        ((ParticipantEntity)this.acc.get(j)).writeToParcel(paramParcel, paramInt);
      }
    }
  }
  
  static final class InvitationEntityCreatorCompat
    extends InvitationEntityCreator
  {
    public InvitationEntity cl(Parcel paramParcel)
    {
      if ((InvitationEntity.b(InvitationEntity.jT())) || (InvitationEntity.bw(InvitationEntity.class.getCanonicalName()))) {
        return super.cl(paramParcel);
      }
      GameEntity localGameEntity = (GameEntity)GameEntity.CREATOR.createFromParcel(paramParcel);
      String str = paramParcel.readString();
      long l = paramParcel.readLong();
      int i = paramParcel.readInt();
      ParticipantEntity localParticipantEntity = (ParticipantEntity)ParticipantEntity.CREATOR.createFromParcel(paramParcel);
      int j = paramParcel.readInt();
      ArrayList localArrayList = new ArrayList(j);
      for (int k = 0; k < j; k++) {
        localArrayList.add(ParticipantEntity.CREATOR.createFromParcel(paramParcel));
      }
      return new InvitationEntity(2, localGameEntity, str, l, i, localParticipantEntity, localArrayList, -1, 0);
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.multiplayer.InvitationEntity
 * JD-Core Version:    0.7.0.1
 */