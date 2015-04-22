package com.google.android.gms.games;

import android.os.Parcel;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class PlayerLevel
  implements SafeParcelable
{
  public static final PlayerLevelCreator CREATOR = new PlayerLevelCreator();
  private final int BR;
  private final int VR;
  private final long VS;
  private final long VT;
  
  PlayerLevel(int paramInt1, int paramInt2, long paramLong1, long paramLong2)
  {
    boolean bool2;
    if (paramLong1 >= 0L)
    {
      bool2 = bool1;
      o.a(bool2, "Min XP must be positive!");
      if (paramLong2 <= paramLong1) {
        break label66;
      }
    }
    for (;;)
    {
      o.a(bool1, "Max XP must be more than min XP!");
      this.BR = paramInt1;
      this.VR = paramInt2;
      this.VS = paramLong1;
      this.VT = paramLong2;
      return;
      bool2 = false;
      break;
      label66:
      bool1 = false;
    }
  }
  
  public PlayerLevel(int paramInt, long paramLong1, long paramLong2)
  {
    this(1, paramInt, paramLong1, paramLong2);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (!(paramObject instanceof PlayerLevel)) {
      bool = false;
    }
    PlayerLevel localPlayerLevel;
    do
    {
      do
      {
        return bool;
      } while (this == paramObject);
      localPlayerLevel = (PlayerLevel)paramObject;
    } while ((n.equal(Integer.valueOf(localPlayerLevel.getLevelNumber()), Integer.valueOf(getLevelNumber()))) && (n.equal(Long.valueOf(localPlayerLevel.getMinXp()), Long.valueOf(getMinXp()))) && (n.equal(Long.valueOf(localPlayerLevel.getMaxXp()), Long.valueOf(getMaxXp()))));
    return false;
  }
  
  public int getLevelNumber()
  {
    return this.VR;
  }
  
  public long getMaxXp()
  {
    return this.VT;
  }
  
  public long getMinXp()
  {
    return this.VS;
  }
  
  public int getVersionCode()
  {
    return this.BR;
  }
  
  public int hashCode()
  {
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = Integer.valueOf(this.VR);
    arrayOfObject[1] = Long.valueOf(this.VS);
    arrayOfObject[2] = Long.valueOf(this.VT);
    return n.hashCode(arrayOfObject);
  }
  
  public String toString()
  {
    return n.h(this).a("LevelNumber", Integer.valueOf(getLevelNumber())).a("MinXp", Long.valueOf(getMinXp())).a("MaxXp", Long.valueOf(getMaxXp())).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    PlayerLevelCreator.a(this, paramParcel, paramInt);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.PlayerLevel
 * JD-Core Version:    0.7.0.1
 */