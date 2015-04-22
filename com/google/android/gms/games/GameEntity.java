package com.google.android.gms.games;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import com.google.android.gms.internal.jv;

public final class GameEntity
  extends GamesDowngradeableSafeParcel
  implements Game
{
  public static final Parcelable.Creator<GameEntity> CREATOR = new GameEntityCreatorCompat();
  private final int BR;
  private final String Ez;
  private final String NH;
  private final String Tr;
  private final String Ve;
  private final String Vf;
  private final String Vg;
  private final Uri Vh;
  private final Uri Vi;
  private final Uri Vj;
  private final boolean Vk;
  private final boolean Vl;
  private final String Vm;
  private final int Vn;
  private final int Vo;
  private final int Vp;
  private final boolean Vq;
  private final boolean Vr;
  private final String Vs;
  private final String Vt;
  private final String Vu;
  private final boolean Vv;
  private final boolean Vw;
  private final boolean Vx;
  private final String Vy;
  
  GameEntity(int paramInt1, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, Uri paramUri1, Uri paramUri2, Uri paramUri3, boolean paramBoolean1, boolean paramBoolean2, String paramString7, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean3, boolean paramBoolean4, String paramString8, String paramString9, String paramString10, boolean paramBoolean5, boolean paramBoolean6, boolean paramBoolean7, String paramString11)
  {
    this.BR = paramInt1;
    this.Ez = paramString1;
    this.NH = paramString2;
    this.Ve = paramString3;
    this.Vf = paramString4;
    this.Tr = paramString5;
    this.Vg = paramString6;
    this.Vh = paramUri1;
    this.Vs = paramString8;
    this.Vi = paramUri2;
    this.Vt = paramString9;
    this.Vj = paramUri3;
    this.Vu = paramString10;
    this.Vk = paramBoolean1;
    this.Vl = paramBoolean2;
    this.Vm = paramString7;
    this.Vn = paramInt2;
    this.Vo = paramInt3;
    this.Vp = paramInt4;
    this.Vq = paramBoolean3;
    this.Vr = paramBoolean4;
    this.Vv = paramBoolean5;
    this.Vw = paramBoolean6;
    this.Vx = paramBoolean7;
    this.Vy = paramString11;
  }
  
  public GameEntity(Game paramGame)
  {
    this.BR = 5;
    this.Ez = paramGame.getApplicationId();
    this.Ve = paramGame.getPrimaryCategory();
    this.Vf = paramGame.getSecondaryCategory();
    this.Tr = paramGame.getDescription();
    this.Vg = paramGame.getDeveloperName();
    this.NH = paramGame.getDisplayName();
    this.Vh = paramGame.getIconImageUri();
    this.Vs = paramGame.getIconImageUrl();
    this.Vi = paramGame.getHiResImageUri();
    this.Vt = paramGame.getHiResImageUrl();
    this.Vj = paramGame.getFeaturedImageUri();
    this.Vu = paramGame.getFeaturedImageUrl();
    this.Vk = paramGame.jO();
    this.Vl = paramGame.jQ();
    this.Vm = paramGame.jR();
    this.Vn = paramGame.jS();
    this.Vo = paramGame.getAchievementTotalCount();
    this.Vp = paramGame.getLeaderboardCount();
    this.Vq = paramGame.isRealTimeMultiplayerEnabled();
    this.Vr = paramGame.isTurnBasedMultiplayerEnabled();
    this.Vv = paramGame.isMuted();
    this.Vw = paramGame.jP();
    this.Vx = paramGame.areSnapshotsEnabled();
    this.Vy = paramGame.getThemeColor();
  }
  
  static int a(Game paramGame)
  {
    Object[] arrayOfObject = new Object[21];
    arrayOfObject[0] = paramGame.getApplicationId();
    arrayOfObject[1] = paramGame.getDisplayName();
    arrayOfObject[2] = paramGame.getPrimaryCategory();
    arrayOfObject[3] = paramGame.getSecondaryCategory();
    arrayOfObject[4] = paramGame.getDescription();
    arrayOfObject[5] = paramGame.getDeveloperName();
    arrayOfObject[6] = paramGame.getIconImageUri();
    arrayOfObject[7] = paramGame.getHiResImageUri();
    arrayOfObject[8] = paramGame.getFeaturedImageUri();
    arrayOfObject[9] = Boolean.valueOf(paramGame.jO());
    arrayOfObject[10] = Boolean.valueOf(paramGame.jQ());
    arrayOfObject[11] = paramGame.jR();
    arrayOfObject[12] = Integer.valueOf(paramGame.jS());
    arrayOfObject[13] = Integer.valueOf(paramGame.getAchievementTotalCount());
    arrayOfObject[14] = Integer.valueOf(paramGame.getLeaderboardCount());
    arrayOfObject[15] = Boolean.valueOf(paramGame.isRealTimeMultiplayerEnabled());
    arrayOfObject[16] = Boolean.valueOf(paramGame.isTurnBasedMultiplayerEnabled());
    arrayOfObject[17] = Boolean.valueOf(paramGame.isMuted());
    arrayOfObject[18] = Boolean.valueOf(paramGame.jP());
    arrayOfObject[19] = Boolean.valueOf(paramGame.areSnapshotsEnabled());
    arrayOfObject[20] = paramGame.getThemeColor();
    return n.hashCode(arrayOfObject);
  }
  
  static boolean a(Game paramGame, Object paramObject)
  {
    boolean bool1 = true;
    if (!(paramObject instanceof Game)) {
      bool1 = false;
    }
    for (;;)
    {
      return bool1;
      if (paramGame != paramObject)
      {
        Game localGame = (Game)paramObject;
        Boolean localBoolean;
        if ((n.equal(localGame.getApplicationId(), paramGame.getApplicationId())) && (n.equal(localGame.getDisplayName(), paramGame.getDisplayName())) && (n.equal(localGame.getPrimaryCategory(), paramGame.getPrimaryCategory())) && (n.equal(localGame.getSecondaryCategory(), paramGame.getSecondaryCategory())) && (n.equal(localGame.getDescription(), paramGame.getDescription())) && (n.equal(localGame.getDeveloperName(), paramGame.getDeveloperName())) && (n.equal(localGame.getIconImageUri(), paramGame.getIconImageUri())) && (n.equal(localGame.getHiResImageUri(), paramGame.getHiResImageUri())) && (n.equal(localGame.getFeaturedImageUri(), paramGame.getFeaturedImageUri())) && (n.equal(Boolean.valueOf(localGame.jO()), Boolean.valueOf(paramGame.jO()))) && (n.equal(Boolean.valueOf(localGame.jQ()), Boolean.valueOf(paramGame.jQ()))) && (n.equal(localGame.jR(), paramGame.jR())) && (n.equal(Integer.valueOf(localGame.jS()), Integer.valueOf(paramGame.jS()))) && (n.equal(Integer.valueOf(localGame.getAchievementTotalCount()), Integer.valueOf(paramGame.getAchievementTotalCount()))) && (n.equal(Integer.valueOf(localGame.getLeaderboardCount()), Integer.valueOf(paramGame.getLeaderboardCount()))) && (n.equal(Boolean.valueOf(localGame.isRealTimeMultiplayerEnabled()), Boolean.valueOf(paramGame.isRealTimeMultiplayerEnabled()))))
        {
          localBoolean = Boolean.valueOf(localGame.isTurnBasedMultiplayerEnabled());
          if ((!paramGame.isTurnBasedMultiplayerEnabled()) || (!n.equal(Boolean.valueOf(localGame.isMuted()), Boolean.valueOf(paramGame.isMuted()))) || (!n.equal(Boolean.valueOf(localGame.jP()), Boolean.valueOf(paramGame.jP())))) {
            break label475;
          }
        }
        label475:
        for (boolean bool2 = bool1; (!n.equal(localBoolean, Boolean.valueOf(bool2))) || (!n.equal(Boolean.valueOf(localGame.areSnapshotsEnabled()), Boolean.valueOf(paramGame.areSnapshotsEnabled()))) || (!n.equal(localGame.getThemeColor(), paramGame.getThemeColor())); bool2 = false) {
          return false;
        }
      }
    }
  }
  
  static String b(Game paramGame)
  {
    return n.h(paramGame).a("ApplicationId", paramGame.getApplicationId()).a("DisplayName", paramGame.getDisplayName()).a("PrimaryCategory", paramGame.getPrimaryCategory()).a("SecondaryCategory", paramGame.getSecondaryCategory()).a("Description", paramGame.getDescription()).a("DeveloperName", paramGame.getDeveloperName()).a("IconImageUri", paramGame.getIconImageUri()).a("IconImageUrl", paramGame.getIconImageUrl()).a("HiResImageUri", paramGame.getHiResImageUri()).a("HiResImageUrl", paramGame.getHiResImageUrl()).a("FeaturedImageUri", paramGame.getFeaturedImageUri()).a("FeaturedImageUrl", paramGame.getFeaturedImageUrl()).a("PlayEnabledGame", Boolean.valueOf(paramGame.jO())).a("InstanceInstalled", Boolean.valueOf(paramGame.jQ())).a("InstancePackageName", paramGame.jR()).a("AchievementTotalCount", Integer.valueOf(paramGame.getAchievementTotalCount())).a("LeaderboardCount", Integer.valueOf(paramGame.getLeaderboardCount())).a("RealTimeMultiplayerEnabled", Boolean.valueOf(paramGame.isRealTimeMultiplayerEnabled())).a("TurnBasedMultiplayerEnabled", Boolean.valueOf(paramGame.isTurnBasedMultiplayerEnabled())).a("AreSnapshotsEnabled", Boolean.valueOf(paramGame.areSnapshotsEnabled())).a("ThemeColor", paramGame.getThemeColor()).toString();
  }
  
  public boolean areSnapshotsEnabled()
  {
    return this.Vx;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return a(this, paramObject);
  }
  
  public Game freeze()
  {
    return this;
  }
  
  public int getAchievementTotalCount()
  {
    return this.Vo;
  }
  
  public String getApplicationId()
  {
    return this.Ez;
  }
  
  public String getDescription()
  {
    return this.Tr;
  }
  
  public void getDescription(CharArrayBuffer paramCharArrayBuffer)
  {
    jv.b(this.Tr, paramCharArrayBuffer);
  }
  
  public String getDeveloperName()
  {
    return this.Vg;
  }
  
  public void getDeveloperName(CharArrayBuffer paramCharArrayBuffer)
  {
    jv.b(this.Vg, paramCharArrayBuffer);
  }
  
  public String getDisplayName()
  {
    return this.NH;
  }
  
  public void getDisplayName(CharArrayBuffer paramCharArrayBuffer)
  {
    jv.b(this.NH, paramCharArrayBuffer);
  }
  
  public Uri getFeaturedImageUri()
  {
    return this.Vj;
  }
  
  public String getFeaturedImageUrl()
  {
    return this.Vu;
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
  
  public int getLeaderboardCount()
  {
    return this.Vp;
  }
  
  public String getPrimaryCategory()
  {
    return this.Ve;
  }
  
  public String getSecondaryCategory()
  {
    return this.Vf;
  }
  
  public String getThemeColor()
  {
    return this.Vy;
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
  
  public boolean isMuted()
  {
    return this.Vv;
  }
  
  public boolean isRealTimeMultiplayerEnabled()
  {
    return this.Vq;
  }
  
  public boolean isTurnBasedMultiplayerEnabled()
  {
    return this.Vr;
  }
  
  public boolean jO()
  {
    return this.Vk;
  }
  
  public boolean jP()
  {
    return this.Vw;
  }
  
  public boolean jQ()
  {
    return this.Vl;
  }
  
  public String jR()
  {
    return this.Vm;
  }
  
  public int jS()
  {
    return this.Vn;
  }
  
  public String toString()
  {
    return b(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = 1;
    if (!gQ())
    {
      GameEntityCreator.a(this, paramParcel, paramInt);
      return;
    }
    paramParcel.writeString(this.Ez);
    paramParcel.writeString(this.NH);
    paramParcel.writeString(this.Ve);
    paramParcel.writeString(this.Vf);
    paramParcel.writeString(this.Tr);
    paramParcel.writeString(this.Vg);
    String str1;
    String str2;
    label90:
    String str3;
    label110:
    int j;
    if (this.Vh == null)
    {
      str1 = null;
      paramParcel.writeString(str1);
      if (this.Vi != null) {
        break label189;
      }
      str2 = null;
      paramParcel.writeString(str2);
      Uri localUri = this.Vj;
      str3 = null;
      if (localUri != null) {
        break label201;
      }
      paramParcel.writeString(str3);
      if (!this.Vk) {
        break label213;
      }
      j = i;
      label126:
      paramParcel.writeInt(j);
      if (!this.Vl) {
        break label219;
      }
    }
    for (;;)
    {
      paramParcel.writeInt(i);
      paramParcel.writeString(this.Vm);
      paramParcel.writeInt(this.Vn);
      paramParcel.writeInt(this.Vo);
      paramParcel.writeInt(this.Vp);
      return;
      str1 = this.Vh.toString();
      break;
      label189:
      str2 = this.Vi.toString();
      break label90;
      label201:
      str3 = this.Vj.toString();
      break label110;
      label213:
      j = 0;
      break label126;
      label219:
      i = 0;
    }
  }
  
  static final class GameEntityCreatorCompat
    extends GameEntityCreator
  {
    public GameEntity cd(Parcel paramParcel)
    {
      if ((GameEntity.b(GameEntity.jT())) || (GameEntity.bw(GameEntity.class.getCanonicalName()))) {
        return super.cd(paramParcel);
      }
      String str1 = paramParcel.readString();
      String str2 = paramParcel.readString();
      String str3 = paramParcel.readString();
      String str4 = paramParcel.readString();
      String str5 = paramParcel.readString();
      String str6 = paramParcel.readString();
      String str7 = paramParcel.readString();
      Uri localUri1;
      String str8;
      Uri localUri2;
      label88:
      String str9;
      Uri localUri3;
      label102:
      boolean bool1;
      if (str7 == null)
      {
        localUri1 = null;
        str8 = paramParcel.readString();
        if (str8 != null) {
          break label186;
        }
        localUri2 = null;
        str9 = paramParcel.readString();
        if (str9 != null) {
          break label196;
        }
        localUri3 = null;
        if (paramParcel.readInt() <= 0) {
          break label206;
        }
        bool1 = true;
        label112:
        if (paramParcel.readInt() <= 0) {
          break label212;
        }
      }
      label186:
      label196:
      label206:
      label212:
      for (boolean bool2 = true;; bool2 = false)
      {
        return new GameEntity(5, str1, str2, str3, str4, str5, str6, localUri1, localUri2, localUri3, bool1, bool2, paramParcel.readString(), paramParcel.readInt(), paramParcel.readInt(), paramParcel.readInt(), false, false, null, null, null, false, false, false, null);
        localUri1 = Uri.parse(str7);
        break;
        localUri2 = Uri.parse(str8);
        break label88;
        localUri3 = Uri.parse(str9);
        break label102;
        bool1 = false;
        break label112;
      }
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.GameEntity
 * JD-Core Version:    0.7.0.1
 */