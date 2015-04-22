package com.google.android.gms.games;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.a;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import com.google.android.gms.games.internal.player.MostRecentGameInfo;
import com.google.android.gms.games.internal.player.MostRecentGameInfoEntity;
import com.google.android.gms.internal.jv;

public final class PlayerEntity
  extends GamesDowngradeableSafeParcel
  implements Player
{
  public static final Parcelable.Creator<PlayerEntity> CREATOR = new PlayerEntityCreatorCompat();
  private final int BR;
  private final String NH;
  private final String Nw;
  private final String VK;
  private final long VL;
  private final int VM;
  private final long VN;
  private final MostRecentGameInfoEntity VO;
  private final PlayerLevelInfo VP;
  private final boolean VQ;
  private final Uri Vh;
  private final Uri Vi;
  private final String Vs;
  private final String Vt;
  
  PlayerEntity(int paramInt1, String paramString1, String paramString2, Uri paramUri1, Uri paramUri2, long paramLong1, int paramInt2, long paramLong2, String paramString3, String paramString4, String paramString5, MostRecentGameInfoEntity paramMostRecentGameInfoEntity, PlayerLevelInfo paramPlayerLevelInfo, boolean paramBoolean)
  {
    this.BR = paramInt1;
    this.VK = paramString1;
    this.NH = paramString2;
    this.Vh = paramUri1;
    this.Vs = paramString3;
    this.Vi = paramUri2;
    this.Vt = paramString4;
    this.VL = paramLong1;
    this.VM = paramInt2;
    this.VN = paramLong2;
    this.Nw = paramString5;
    this.VQ = paramBoolean;
    this.VO = paramMostRecentGameInfoEntity;
    this.VP = paramPlayerLevelInfo;
  }
  
  public PlayerEntity(Player paramPlayer)
  {
    this.BR = 11;
    this.VK = paramPlayer.getPlayerId();
    this.NH = paramPlayer.getDisplayName();
    this.Vh = paramPlayer.getIconImageUri();
    this.Vs = paramPlayer.getIconImageUrl();
    this.Vi = paramPlayer.getHiResImageUri();
    this.Vt = paramPlayer.getHiResImageUrl();
    this.VL = paramPlayer.getRetrievedTimestamp();
    this.VM = paramPlayer.jU();
    this.VN = paramPlayer.getLastPlayedWithTimestamp();
    this.Nw = paramPlayer.getTitle();
    this.VQ = paramPlayer.isProfileVisible();
    MostRecentGameInfo localMostRecentGameInfo = paramPlayer.jV();
    MostRecentGameInfoEntity localMostRecentGameInfoEntity;
    if (localMostRecentGameInfo == null)
    {
      localMostRecentGameInfoEntity = null;
      this.VO = localMostRecentGameInfoEntity;
      this.VP = paramPlayer.getLevelInfo();
      a.f(this.VK);
      a.f(this.NH);
      if (this.VL <= 0L) {
        break label192;
      }
    }
    label192:
    for (boolean bool = true;; bool = false)
    {
      a.I(bool);
      return;
      localMostRecentGameInfoEntity = new MostRecentGameInfoEntity(localMostRecentGameInfo);
      break;
    }
  }
  
  static boolean a(Player paramPlayer, Object paramObject)
  {
    boolean bool = true;
    if (!(paramObject instanceof Player)) {
      bool = false;
    }
    Player localPlayer;
    do
    {
      do
      {
        return bool;
      } while (paramPlayer == paramObject);
      localPlayer = (Player)paramObject;
    } while ((n.equal(localPlayer.getPlayerId(), paramPlayer.getPlayerId())) && (n.equal(localPlayer.getDisplayName(), paramPlayer.getDisplayName())) && (n.equal(localPlayer.getIconImageUri(), paramPlayer.getIconImageUri())) && (n.equal(localPlayer.getHiResImageUri(), paramPlayer.getHiResImageUri())) && (n.equal(Long.valueOf(localPlayer.getRetrievedTimestamp()), Long.valueOf(paramPlayer.getRetrievedTimestamp()))) && (n.equal(localPlayer.getTitle(), paramPlayer.getTitle())) && (n.equal(localPlayer.getLevelInfo(), paramPlayer.getLevelInfo())));
    return false;
  }
  
  static int b(Player paramPlayer)
  {
    Object[] arrayOfObject = new Object[7];
    arrayOfObject[0] = paramPlayer.getPlayerId();
    arrayOfObject[1] = paramPlayer.getDisplayName();
    arrayOfObject[2] = paramPlayer.getIconImageUri();
    arrayOfObject[3] = paramPlayer.getHiResImageUri();
    arrayOfObject[4] = Long.valueOf(paramPlayer.getRetrievedTimestamp());
    arrayOfObject[5] = paramPlayer.getTitle();
    arrayOfObject[6] = paramPlayer.getLevelInfo();
    return n.hashCode(arrayOfObject);
  }
  
  static String c(Player paramPlayer)
  {
    return n.h(paramPlayer).a("PlayerId", paramPlayer.getPlayerId()).a("DisplayName", paramPlayer.getDisplayName()).a("IconImageUri", paramPlayer.getIconImageUri()).a("IconImageUrl", paramPlayer.getIconImageUrl()).a("HiResImageUri", paramPlayer.getHiResImageUri()).a("HiResImageUrl", paramPlayer.getHiResImageUrl()).a("RetrievedTimestamp", Long.valueOf(paramPlayer.getRetrievedTimestamp())).a("Title", paramPlayer.getTitle()).a("LevelInfo", paramPlayer.getLevelInfo()).toString();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return a(this, paramObject);
  }
  
  public Player freeze()
  {
    return this;
  }
  
  public String getDisplayName()
  {
    return this.NH;
  }
  
  public void getDisplayName(CharArrayBuffer paramCharArrayBuffer)
  {
    jv.b(this.NH, paramCharArrayBuffer);
  }
  
  public Uri getHiResImageUri()
  {
    return this.Vi;
  }
  
  public String getHiResImageUrl()
  {
    return this.Vt;
  }
  
  public Uri getIconImageUri()
  {
    return this.Vh;
  }
  
  public String getIconImageUrl()
  {
    return this.Vs;
  }
  
  public long getLastPlayedWithTimestamp()
  {
    return this.VN;
  }
  
  public PlayerLevelInfo getLevelInfo()
  {
    return this.VP;
  }
  
  public String getPlayerId()
  {
    return this.VK;
  }
  
  public long getRetrievedTimestamp()
  {
    return this.VL;
  }
  
  public String getTitle()
  {
    return this.Nw;
  }
  
  public void getTitle(CharArrayBuffer paramCharArrayBuffer)
  {
    jv.b(this.Nw, paramCharArrayBuffer);
  }
  
  public int getVersionCode()
  {
    return this.BR;
  }
  
  public boolean hasHiResImage()
  {
    return getHiResImageUri() != null;
  }
  
  public boolean hasIconImage()
  {
    return getIconImageUri() != null;
  }
  
  public int hashCode()
  {
    return b(this);
  }
  
  public boolean isDataValid()
  {
    return true;
  }
  
  public boolean isProfileVisible()
  {
    return this.VQ;
  }
  
  public int jU()
  {
    return this.VM;
  }
  
  public MostRecentGameInfo jV()
  {
    return this.VO;
  }
  
  public String toString()
  {
    return c(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    if (!gQ())
    {
      PlayerEntityCreator.a(this, paramParcel, paramInt);
      return;
    }
    paramParcel.writeString(this.VK);
    paramParcel.writeString(this.NH);
    String str1;
    String str2;
    if (this.Vh == null)
    {
      str1 = null;
      paramParcel.writeString(str1);
      Uri localUri = this.Vi;
      str2 = null;
      if (localUri != null) {
        break label84;
      }
    }
    for (;;)
    {
      paramParcel.writeString(str2);
      paramParcel.writeLong(this.VL);
      return;
      str1 = this.Vh.toString();
      break;
      label84:
      str2 = this.Vi.toString();
    }
  }
  
  static final class PlayerEntityCreatorCompat
    extends PlayerEntityCreator
  {
    public PlayerEntity ce(Parcel paramParcel)
    {
      if ((PlayerEntity.b(PlayerEntity.jT())) || (PlayerEntity.bw(PlayerEntity.class.getCanonicalName()))) {
        return super.ce(paramParcel);
      }
      String str1 = paramParcel.readString();
      String str2 = paramParcel.readString();
      String str3 = paramParcel.readString();
      String str4 = paramParcel.readString();
      Uri localUri1;
      if (str3 == null)
      {
        localUri1 = null;
        if (str4 != null) {
          break label104;
        }
      }
      label104:
      for (Uri localUri2 = null;; localUri2 = Uri.parse(str4))
      {
        return new PlayerEntity(11, str1, str2, localUri1, localUri2, paramParcel.readLong(), -1, -1L, null, null, null, null, null, true);
        localUri1 = Uri.parse(str3);
        break;
      }
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.PlayerEntity
 * JD-Core Version:    0.7.0.1
 */