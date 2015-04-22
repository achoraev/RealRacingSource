package com.google.android.gms.games.internal.game;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import com.google.android.gms.games.snapshot.SnapshotMetadata;
import com.google.android.gms.games.snapshot.SnapshotMetadataEntity;
import java.util.ArrayList;

public final class ExtendedGameEntity
  extends GamesDowngradeableSafeParcel
  implements ExtendedGame
{
  public static final ExtendedGameEntityCreator CREATOR = new ExtendedGameEntityCreatorCompat();
  private final int BR;
  private final boolean aaA;
  private final int aaB;
  private final long aaC;
  private final long aaD;
  private final String aaE;
  private final long aaF;
  private final String aaG;
  private final ArrayList<GameBadgeEntity> aaH;
  private final SnapshotMetadataEntity aaI;
  private final GameEntity aay;
  private final int aaz;
  
  ExtendedGameEntity(int paramInt1, GameEntity paramGameEntity, int paramInt2, boolean paramBoolean, int paramInt3, long paramLong1, long paramLong2, String paramString1, long paramLong3, String paramString2, ArrayList<GameBadgeEntity> paramArrayList, SnapshotMetadataEntity paramSnapshotMetadataEntity)
  {
    this.BR = paramInt1;
    this.aay = paramGameEntity;
    this.aaz = paramInt2;
    this.aaA = paramBoolean;
    this.aaB = paramInt3;
    this.aaC = paramLong1;
    this.aaD = paramLong2;
    this.aaE = paramString1;
    this.aaF = paramLong3;
    this.aaG = paramString2;
    this.aaH = paramArrayList;
    this.aaI = paramSnapshotMetadataEntity;
  }
  
  public ExtendedGameEntity(ExtendedGame paramExtendedGame)
  {
    this.BR = 2;
    Game localGame = paramExtendedGame.getGame();
    GameEntity localGameEntity;
    SnapshotMetadata localSnapshotMetadata;
    SnapshotMetadataEntity localSnapshotMetadataEntity;
    if (localGame == null)
    {
      localGameEntity = null;
      this.aay = localGameEntity;
      this.aaz = paramExtendedGame.kS();
      this.aaA = paramExtendedGame.kT();
      this.aaB = paramExtendedGame.kU();
      this.aaC = paramExtendedGame.kV();
      this.aaD = paramExtendedGame.kW();
      this.aaE = paramExtendedGame.kX();
      this.aaF = paramExtendedGame.kY();
      this.aaG = paramExtendedGame.kZ();
      localSnapshotMetadata = paramExtendedGame.la();
      localSnapshotMetadataEntity = null;
      if (localSnapshotMetadata != null) {
        break label211;
      }
    }
    for (;;)
    {
      this.aaI = localSnapshotMetadataEntity;
      ArrayList localArrayList = paramExtendedGame.kR();
      int i = localArrayList.size();
      this.aaH = new ArrayList(i);
      for (int j = 0; j < i; j++) {
        this.aaH.add((GameBadgeEntity)((GameBadge)localArrayList.get(j)).freeze());
      }
      localGameEntity = new GameEntity(localGame);
      break;
      label211:
      localSnapshotMetadataEntity = new SnapshotMetadataEntity(localSnapshotMetadata);
    }
  }
  
  static int a(ExtendedGame paramExtendedGame)
  {
    Object[] arrayOfObject = new Object[9];
    arrayOfObject[0] = paramExtendedGame.getGame();
    arrayOfObject[1] = Integer.valueOf(paramExtendedGame.kS());
    arrayOfObject[2] = Boolean.valueOf(paramExtendedGame.kT());
    arrayOfObject[3] = Integer.valueOf(paramExtendedGame.kU());
    arrayOfObject[4] = Long.valueOf(paramExtendedGame.kV());
    arrayOfObject[5] = Long.valueOf(paramExtendedGame.kW());
    arrayOfObject[6] = paramExtendedGame.kX();
    arrayOfObject[7] = Long.valueOf(paramExtendedGame.kY());
    arrayOfObject[8] = paramExtendedGame.kZ();
    return n.hashCode(arrayOfObject);
  }
  
  static boolean a(ExtendedGame paramExtendedGame, Object paramObject)
  {
    boolean bool = true;
    if (!(paramObject instanceof ExtendedGame)) {
      bool = false;
    }
    ExtendedGame localExtendedGame;
    do
    {
      do
      {
        return bool;
      } while (paramExtendedGame == paramObject);
      localExtendedGame = (ExtendedGame)paramObject;
    } while ((n.equal(localExtendedGame.getGame(), paramExtendedGame.getGame())) && (n.equal(Integer.valueOf(localExtendedGame.kS()), Integer.valueOf(paramExtendedGame.kS()))) && (n.equal(Boolean.valueOf(localExtendedGame.kT()), Boolean.valueOf(paramExtendedGame.kT()))) && (n.equal(Integer.valueOf(localExtendedGame.kU()), Integer.valueOf(paramExtendedGame.kU()))) && (n.equal(Long.valueOf(localExtendedGame.kV()), Long.valueOf(paramExtendedGame.kV()))) && (n.equal(Long.valueOf(localExtendedGame.kW()), Long.valueOf(paramExtendedGame.kW()))) && (n.equal(localExtendedGame.kX(), paramExtendedGame.kX())) && (n.equal(Long.valueOf(localExtendedGame.kY()), Long.valueOf(paramExtendedGame.kY()))) && (n.equal(localExtendedGame.kZ(), paramExtendedGame.kZ())));
    return false;
  }
  
  static String b(ExtendedGame paramExtendedGame)
  {
    return n.h(paramExtendedGame).a("Game", paramExtendedGame.getGame()).a("Availability", Integer.valueOf(paramExtendedGame.kS())).a("Owned", Boolean.valueOf(paramExtendedGame.kT())).a("AchievementUnlockedCount", Integer.valueOf(paramExtendedGame.kU())).a("LastPlayedServerTimestamp", Long.valueOf(paramExtendedGame.kV())).a("PriceMicros", Long.valueOf(paramExtendedGame.kW())).a("FormattedPrice", paramExtendedGame.kX()).a("FullPriceMicros", Long.valueOf(paramExtendedGame.kY())).a("FormattedFullPrice", paramExtendedGame.kZ()).a("Snapshot", paramExtendedGame.la()).toString();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return a(this, paramObject);
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
  
  public ArrayList<GameBadge> kR()
  {
    return new ArrayList(this.aaH);
  }
  
  public int kS()
  {
    return this.aaz;
  }
  
  public boolean kT()
  {
    return this.aaA;
  }
  
  public int kU()
  {
    return this.aaB;
  }
  
  public long kV()
  {
    return this.aaC;
  }
  
  public long kW()
  {
    return this.aaD;
  }
  
  public String kX()
  {
    return this.aaE;
  }
  
  public long kY()
  {
    return this.aaF;
  }
  
  public String kZ()
  {
    return this.aaG;
  }
  
  public SnapshotMetadata la()
  {
    return this.aaI;
  }
  
  public GameEntity lb()
  {
    return this.aay;
  }
  
  public ExtendedGame lc()
  {
    return this;
  }
  
  public String toString()
  {
    return b(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = 0;
    if (!gQ())
    {
      ExtendedGameEntityCreator.a(this, paramParcel, paramInt);
      return;
    }
    this.aay.writeToParcel(paramParcel, paramInt);
    paramParcel.writeInt(this.aaz);
    if (this.aaA) {}
    for (int j = 1;; j = 0)
    {
      paramParcel.writeInt(j);
      paramParcel.writeInt(this.aaB);
      paramParcel.writeLong(this.aaC);
      paramParcel.writeLong(this.aaD);
      paramParcel.writeString(this.aaE);
      paramParcel.writeLong(this.aaF);
      paramParcel.writeString(this.aaG);
      int k = this.aaH.size();
      paramParcel.writeInt(k);
      while (i < k)
      {
        ((GameBadgeEntity)this.aaH.get(i)).writeToParcel(paramParcel, paramInt);
        i++;
      }
      break;
    }
  }
  
  static final class ExtendedGameEntityCreatorCompat
    extends ExtendedGameEntityCreator
  {
    public ExtendedGameEntity cg(Parcel paramParcel)
    {
      if ((ExtendedGameEntity.b(ExtendedGameEntity.jT())) || (ExtendedGameEntity.bw(ExtendedGameEntity.class.getCanonicalName()))) {
        return super.cg(paramParcel);
      }
      GameEntity localGameEntity = (GameEntity)GameEntity.CREATOR.createFromParcel(paramParcel);
      int i = paramParcel.readInt();
      if (paramParcel.readInt() == 1) {}
      int j;
      long l1;
      long l2;
      String str1;
      long l3;
      String str2;
      ArrayList localArrayList;
      for (boolean bool = true;; bool = false)
      {
        j = paramParcel.readInt();
        l1 = paramParcel.readLong();
        l2 = paramParcel.readLong();
        str1 = paramParcel.readString();
        l3 = paramParcel.readLong();
        str2 = paramParcel.readString();
        int k = paramParcel.readInt();
        localArrayList = new ArrayList(k);
        for (int m = 0; m < k; m++) {
          localArrayList.add(GameBadgeEntity.CREATOR.ch(paramParcel));
        }
      }
      return new ExtendedGameEntity(2, localGameEntity, i, bool, j, l1, l2, str1, l3, str2, localArrayList, null);
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.internal.game.ExtendedGameEntity
 * JD-Core Version:    0.7.0.1
 */