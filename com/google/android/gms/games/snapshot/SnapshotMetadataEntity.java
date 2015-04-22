package com.google.android.gms.games.snapshot;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.internal.jv;

public final class SnapshotMetadataEntity
  implements SafeParcelable, SnapshotMetadata
{
  public static final SnapshotMetadataEntityCreator CREATOR = new SnapshotMetadataEntityCreator();
  private final int BR;
  private final String Nw;
  private final String Tr;
  private final String WI;
  private final GameEntity aay;
  private final Uri adk;
  private final PlayerEntity ado;
  private final String adp;
  private final long adq;
  private final long adr;
  private final float ads;
  private final String adt;
  
  SnapshotMetadataEntity(int paramInt, GameEntity paramGameEntity, PlayerEntity paramPlayerEntity, String paramString1, Uri paramUri, String paramString2, String paramString3, String paramString4, long paramLong1, long paramLong2, float paramFloat, String paramString5)
  {
    this.BR = paramInt;
    this.aay = paramGameEntity;
    this.ado = paramPlayerEntity;
    this.WI = paramString1;
    this.adk = paramUri;
    this.adp = paramString2;
    this.ads = paramFloat;
    this.Nw = paramString3;
    this.Tr = paramString4;
    this.adq = paramLong1;
    this.adr = paramLong2;
    this.adt = paramString5;
  }
  
  public SnapshotMetadataEntity(SnapshotMetadata paramSnapshotMetadata)
  {
    this.BR = 3;
    this.aay = new GameEntity(paramSnapshotMetadata.getGame());
    this.ado = new PlayerEntity(paramSnapshotMetadata.getOwner());
    this.WI = paramSnapshotMetadata.getSnapshotId();
    this.adk = paramSnapshotMetadata.getCoverImageUri();
    this.adp = paramSnapshotMetadata.getCoverImageUrl();
    this.ads = paramSnapshotMetadata.getCoverImageAspectRatio();
    this.Nw = paramSnapshotMetadata.getTitle();
    this.Tr = paramSnapshotMetadata.getDescription();
    this.adq = paramSnapshotMetadata.getLastModifiedTimestamp();
    this.adr = paramSnapshotMetadata.getPlayedTime();
    this.adt = paramSnapshotMetadata.getUniqueName();
  }
  
  static int a(SnapshotMetadata paramSnapshotMetadata)
  {
    Object[] arrayOfObject = new Object[10];
    arrayOfObject[0] = paramSnapshotMetadata.getGame();
    arrayOfObject[1] = paramSnapshotMetadata.getOwner();
    arrayOfObject[2] = paramSnapshotMetadata.getSnapshotId();
    arrayOfObject[3] = paramSnapshotMetadata.getCoverImageUri();
    arrayOfObject[4] = Float.valueOf(paramSnapshotMetadata.getCoverImageAspectRatio());
    arrayOfObject[5] = paramSnapshotMetadata.getTitle();
    arrayOfObject[6] = paramSnapshotMetadata.getDescription();
    arrayOfObject[7] = Long.valueOf(paramSnapshotMetadata.getLastModifiedTimestamp());
    arrayOfObject[8] = Long.valueOf(paramSnapshotMetadata.getPlayedTime());
    arrayOfObject[9] = paramSnapshotMetadata.getUniqueName();
    return n.hashCode(arrayOfObject);
  }
  
  static boolean a(SnapshotMetadata paramSnapshotMetadata, Object paramObject)
  {
    boolean bool = true;
    if (!(paramObject instanceof SnapshotMetadata)) {
      bool = false;
    }
    SnapshotMetadata localSnapshotMetadata;
    do
    {
      do
      {
        return bool;
      } while (paramSnapshotMetadata == paramObject);
      localSnapshotMetadata = (SnapshotMetadata)paramObject;
    } while ((n.equal(localSnapshotMetadata.getGame(), paramSnapshotMetadata.getGame())) && (n.equal(localSnapshotMetadata.getOwner(), paramSnapshotMetadata.getOwner())) && (n.equal(localSnapshotMetadata.getSnapshotId(), paramSnapshotMetadata.getSnapshotId())) && (n.equal(localSnapshotMetadata.getCoverImageUri(), paramSnapshotMetadata.getCoverImageUri())) && (n.equal(Float.valueOf(localSnapshotMetadata.getCoverImageAspectRatio()), Float.valueOf(paramSnapshotMetadata.getCoverImageAspectRatio()))) && (n.equal(localSnapshotMetadata.getTitle(), paramSnapshotMetadata.getTitle())) && (n.equal(localSnapshotMetadata.getDescription(), paramSnapshotMetadata.getDescription())) && (n.equal(Long.valueOf(localSnapshotMetadata.getLastModifiedTimestamp()), Long.valueOf(paramSnapshotMetadata.getLastModifiedTimestamp()))) && (n.equal(Long.valueOf(localSnapshotMetadata.getPlayedTime()), Long.valueOf(paramSnapshotMetadata.getPlayedTime()))) && (n.equal(localSnapshotMetadata.getUniqueName(), paramSnapshotMetadata.getUniqueName())));
    return false;
  }
  
  static String b(SnapshotMetadata paramSnapshotMetadata)
  {
    return n.h(paramSnapshotMetadata).a("Game", paramSnapshotMetadata.getGame()).a("Owner", paramSnapshotMetadata.getOwner()).a("SnapshotId", paramSnapshotMetadata.getSnapshotId()).a("CoverImageUri", paramSnapshotMetadata.getCoverImageUri()).a("CoverImageUrl", paramSnapshotMetadata.getCoverImageUrl()).a("CoverImageAspectRatio", Float.valueOf(paramSnapshotMetadata.getCoverImageAspectRatio())).a("Description", paramSnapshotMetadata.getDescription()).a("LastModifiedTimestamp", Long.valueOf(paramSnapshotMetadata.getLastModifiedTimestamp())).a("PlayedTime", Long.valueOf(paramSnapshotMetadata.getPlayedTime())).a("UniqueName", paramSnapshotMetadata.getUniqueName()).toString();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return a(this, paramObject);
  }
  
  public SnapshotMetadata freeze()
  {
    return this;
  }
  
  public float getCoverImageAspectRatio()
  {
    return this.ads;
  }
  
  public Uri getCoverImageUri()
  {
    return this.adk;
  }
  
  public String getCoverImageUrl()
  {
    return this.adp;
  }
  
  public String getDescription()
  {
    return this.Tr;
  }
  
  public void getDescription(CharArrayBuffer paramCharArrayBuffer)
  {
    jv.b(this.Tr, paramCharArrayBuffer);
  }
  
  public Game getGame()
  {
    return this.aay;
  }
  
  public long getLastModifiedTimestamp()
  {
    return this.adq;
  }
  
  public Player getOwner()
  {
    return this.ado;
  }
  
  public long getPlayedTime()
  {
    return this.adr;
  }
  
  public String getSnapshotId()
  {
    return this.WI;
  }
  
  public String getTitle()
  {
    return this.Nw;
  }
  
  public String getUniqueName()
  {
    return this.adt;
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
    SnapshotMetadataEntityCreator.a(this, paramParcel, paramInt);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.snapshot.SnapshotMetadataEntity
 * JD-Core Version:    0.7.0.1
 */