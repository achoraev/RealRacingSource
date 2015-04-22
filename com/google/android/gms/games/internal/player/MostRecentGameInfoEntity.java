package com.google.android.gms.games.internal.player;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class MostRecentGameInfoEntity
  implements SafeParcelable, MostRecentGameInfo
{
  public static final MostRecentGameInfoEntityCreator CREATOR = new MostRecentGameInfoEntityCreator();
  private final int BR;
  private final String aaM;
  private final String aaN;
  private final long aaO;
  private final Uri aaP;
  private final Uri aaQ;
  private final Uri aaR;
  
  MostRecentGameInfoEntity(int paramInt, String paramString1, String paramString2, long paramLong, Uri paramUri1, Uri paramUri2, Uri paramUri3)
  {
    this.BR = paramInt;
    this.aaM = paramString1;
    this.aaN = paramString2;
    this.aaO = paramLong;
    this.aaP = paramUri1;
    this.aaQ = paramUri2;
    this.aaR = paramUri3;
  }
  
  public MostRecentGameInfoEntity(MostRecentGameInfo paramMostRecentGameInfo)
  {
    this.BR = 2;
    this.aaM = paramMostRecentGameInfo.lp();
    this.aaN = paramMostRecentGameInfo.lq();
    this.aaO = paramMostRecentGameInfo.lr();
    this.aaP = paramMostRecentGameInfo.ls();
    this.aaQ = paramMostRecentGameInfo.lt();
    this.aaR = paramMostRecentGameInfo.lu();
  }
  
  static int a(MostRecentGameInfo paramMostRecentGameInfo)
  {
    Object[] arrayOfObject = new Object[6];
    arrayOfObject[0] = paramMostRecentGameInfo.lp();
    arrayOfObject[1] = paramMostRecentGameInfo.lq();
    arrayOfObject[2] = Long.valueOf(paramMostRecentGameInfo.lr());
    arrayOfObject[3] = paramMostRecentGameInfo.ls();
    arrayOfObject[4] = paramMostRecentGameInfo.lt();
    arrayOfObject[5] = paramMostRecentGameInfo.lu();
    return n.hashCode(arrayOfObject);
  }
  
  static boolean a(MostRecentGameInfo paramMostRecentGameInfo, Object paramObject)
  {
    boolean bool = true;
    if (!(paramObject instanceof MostRecentGameInfo)) {
      bool = false;
    }
    MostRecentGameInfo localMostRecentGameInfo;
    do
    {
      do
      {
        return bool;
      } while (paramMostRecentGameInfo == paramObject);
      localMostRecentGameInfo = (MostRecentGameInfo)paramObject;
    } while ((n.equal(localMostRecentGameInfo.lp(), paramMostRecentGameInfo.lp())) && (n.equal(localMostRecentGameInfo.lq(), paramMostRecentGameInfo.lq())) && (n.equal(Long.valueOf(localMostRecentGameInfo.lr()), Long.valueOf(paramMostRecentGameInfo.lr()))) && (n.equal(localMostRecentGameInfo.ls(), paramMostRecentGameInfo.ls())) && (n.equal(localMostRecentGameInfo.lt(), paramMostRecentGameInfo.lt())) && (n.equal(localMostRecentGameInfo.lu(), paramMostRecentGameInfo.lu())));
    return false;
  }
  
  static String b(MostRecentGameInfo paramMostRecentGameInfo)
  {
    return n.h(paramMostRecentGameInfo).a("GameId", paramMostRecentGameInfo.lp()).a("GameName", paramMostRecentGameInfo.lq()).a("ActivityTimestampMillis", Long.valueOf(paramMostRecentGameInfo.lr())).a("GameIconUri", paramMostRecentGameInfo.ls()).a("GameHiResUri", paramMostRecentGameInfo.lt()).a("GameFeaturedUri", paramMostRecentGameInfo.lu()).toString();
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
  
  public String lp()
  {
    return this.aaM;
  }
  
  public String lq()
  {
    return this.aaN;
  }
  
  public long lr()
  {
    return this.aaO;
  }
  
  public Uri ls()
  {
    return this.aaP;
  }
  
  public Uri lt()
  {
    return this.aaQ;
  }
  
  public Uri lu()
  {
    return this.aaR;
  }
  
  public MostRecentGameInfo lv()
  {
    return this;
  }
  
  public String toString()
  {
    return b(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    MostRecentGameInfoEntityCreator.a(this, paramParcel, paramInt);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.internal.player.MostRecentGameInfoEntity
 * JD-Core Version:    0.7.0.1
 */