package com.google.android.gms.games.internal.game;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;

public final class GameBadgeEntity
  extends GamesDowngradeableSafeParcel
  implements GameBadge
{
  public static final GameBadgeEntityCreator CREATOR = new GameBadgeEntityCreatorCompat();
  private final int BR;
  private int FD;
  private String Nw;
  private String Tr;
  private Uri Vh;
  
  GameBadgeEntity(int paramInt1, int paramInt2, String paramString1, String paramString2, Uri paramUri)
  {
    this.BR = paramInt1;
    this.FD = paramInt2;
    this.Nw = paramString1;
    this.Tr = paramString2;
    this.Vh = paramUri;
  }
  
  public GameBadgeEntity(GameBadge paramGameBadge)
  {
    this.BR = 1;
    this.FD = paramGameBadge.getType();
    this.Nw = paramGameBadge.getTitle();
    this.Tr = paramGameBadge.getDescription();
    this.Vh = paramGameBadge.getIconImageUri();
  }
  
  static int a(GameBadge paramGameBadge)
  {
    Object[] arrayOfObject = new Object[4];
    arrayOfObject[0] = Integer.valueOf(paramGameBadge.getType());
    arrayOfObject[1] = paramGameBadge.getTitle();
    arrayOfObject[2] = paramGameBadge.getDescription();
    arrayOfObject[3] = paramGameBadge.getIconImageUri();
    return n.hashCode(arrayOfObject);
  }
  
  static boolean a(GameBadge paramGameBadge, Object paramObject)
  {
    boolean bool = true;
    if (!(paramObject instanceof GameBadge)) {
      bool = false;
    }
    GameBadge localGameBadge;
    do
    {
      do
      {
        return bool;
      } while (paramGameBadge == paramObject);
      localGameBadge = (GameBadge)paramObject;
    } while ((n.equal(Integer.valueOf(localGameBadge.getType()), paramGameBadge.getTitle())) && (n.equal(localGameBadge.getDescription(), paramGameBadge.getIconImageUri())));
    return false;
  }
  
  static String b(GameBadge paramGameBadge)
  {
    return n.h(paramGameBadge).a("Type", Integer.valueOf(paramGameBadge.getType())).a("Title", paramGameBadge.getTitle()).a("Description", paramGameBadge.getDescription()).a("IconImageUri", paramGameBadge.getIconImageUri()).toString();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return a(this, paramObject);
  }
  
  public String getDescription()
  {
    return this.Tr;
  }
  
  public Uri getIconImageUri()
  {
    return this.Vh;
  }
  
  public String getTitle()
  {
    return this.Nw;
  }
  
  public int getType()
  {
    return this.FD;
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
  
  public GameBadge ld()
  {
    return this;
  }
  
  public String toString()
  {
    return b(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    if (!gQ())
    {
      GameBadgeEntityCreator.a(this, paramParcel, paramInt);
      return;
    }
    paramParcel.writeInt(this.FD);
    paramParcel.writeString(this.Nw);
    paramParcel.writeString(this.Tr);
    if (this.Vh == null) {}
    for (String str = null;; str = this.Vh.toString())
    {
      paramParcel.writeString(str);
      return;
    }
  }
  
  static final class GameBadgeEntityCreatorCompat
    extends GameBadgeEntityCreator
  {
    public GameBadgeEntity ch(Parcel paramParcel)
    {
      if ((GameBadgeEntity.b(GameBadgeEntity.jT())) || (GameBadgeEntity.bw(GameBadgeEntity.class.getCanonicalName()))) {
        return super.ch(paramParcel);
      }
      int i = paramParcel.readInt();
      String str1 = paramParcel.readString();
      String str2 = paramParcel.readString();
      String str3 = paramParcel.readString();
      if (str3 == null) {}
      for (Uri localUri = null;; localUri = Uri.parse(str3)) {
        return new GameBadgeEntity(1, i, str1, str2, localUri);
      }
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.internal.game.GameBadgeEntity
 * JD-Core Version:    0.7.0.1
 */