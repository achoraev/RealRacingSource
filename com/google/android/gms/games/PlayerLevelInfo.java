package com.google.android.gms.games;

import android.os.Parcel;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class PlayerLevelInfo
  implements SafeParcelable
{
  public static final PlayerLevelInfoCreator CREATOR = new PlayerLevelInfoCreator();
  private final int BR;
  private final long VU;
  private final long VV;
  private final PlayerLevel VW;
  private final PlayerLevel VX;
  
  PlayerLevelInfo(int paramInt, long paramLong1, long paramLong2, PlayerLevel paramPlayerLevel1, PlayerLevel paramPlayerLevel2)
  {
    if (paramLong1 != -1L) {}
    for (boolean bool = true;; bool = false)
    {
      o.I(bool);
      o.i(paramPlayerLevel1);
      o.i(paramPlayerLevel2);
      this.BR = paramInt;
      this.VU = paramLong1;
      this.VV = paramLong2;
      this.VW = paramPlayerLevel1;
      this.VX = paramPlayerLevel2;
      return;
    }
  }
  
  public PlayerLevelInfo(long paramLong1, long paramLong2, PlayerLevel paramPlayerLevel1, PlayerLevel paramPlayerLevel2)
  {
    this(1, paramLong1, paramLong2, paramPlayerLevel1, paramPlayerLevel2);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (!(paramObject instanceof PlayerLevelInfo)) {
      bool = false;
    }
    PlayerLevelInfo localPlayerLevelInfo;
    do
    {
      do
      {
        return bool;
      } while (paramObject == this);
      localPlayerLevelInfo = (PlayerLevelInfo)paramObject;
    } while ((n.equal(Long.valueOf(this.VU), Long.valueOf(localPlayerLevelInfo.VU))) && (n.equal(Long.valueOf(this.VV), Long.valueOf(localPlayerLevelInfo.VV))) && (n.equal(this.VW, localPlayerLevelInfo.VW)) && (n.equal(this.VX, localPlayerLevelInfo.VX)));
    return false;
  }
  
  public PlayerLevel getCurrentLevel()
  {
    return this.VW;
  }
  
  public long getCurrentXpTotal()
  {
    return this.VU;
  }
  
  public long getLastLevelUpTimestamp()
  {
    return this.VV;
  }
  
  public PlayerLevel getNextLevel()
  {
    return this.VX;
  }
  
  public int getVersionCode()
  {
    return this.BR;
  }
  
  public int hashCode()
  {
    Object[] arrayOfObject = new Object[4];
    arrayOfObject[0] = Long.valueOf(this.VU);
    arrayOfObject[1] = Long.valueOf(this.VV);
    arrayOfObject[2] = this.VW;
    arrayOfObject[3] = this.VX;
    return n.hashCode(arrayOfObject);
  }
  
  public boolean isMaxLevel()
  {
    return this.VW.equals(this.VX);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    PlayerLevelInfoCreator.a(this, paramParcel, paramInt);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.PlayerLevelInfo
 * JD-Core Version:    0.7.0.1
 */