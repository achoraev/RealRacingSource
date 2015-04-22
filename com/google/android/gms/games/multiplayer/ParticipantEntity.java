package com.google.android.gms.games.multiplayer;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import com.google.android.gms.internal.jv;

public final class ParticipantEntity
  extends GamesDowngradeableSafeParcel
  implements Participant
{
  public static final Parcelable.Creator<ParticipantEntity> CREATOR = new ParticipantEntityCreatorCompat();
  private final int BR;
  private final int EZ;
  private final int Fa;
  private final String NH;
  private final Uri Vh;
  private final Uri Vi;
  private final String Vs;
  private final String Vt;
  private final PlayerEntity Wh;
  private final String Wq;
  private final String Xr;
  private final boolean acg;
  private final ParticipantResult ach;
  
  ParticipantEntity(int paramInt1, String paramString1, String paramString2, Uri paramUri1, Uri paramUri2, int paramInt2, String paramString3, boolean paramBoolean, PlayerEntity paramPlayerEntity, int paramInt3, ParticipantResult paramParticipantResult, String paramString4, String paramString5)
  {
    this.BR = paramInt1;
    this.Xr = paramString1;
    this.NH = paramString2;
    this.Vh = paramUri1;
    this.Vi = paramUri2;
    this.Fa = paramInt2;
    this.Wq = paramString3;
    this.acg = paramBoolean;
    this.Wh = paramPlayerEntity;
    this.EZ = paramInt3;
    this.ach = paramParticipantResult;
    this.Vs = paramString4;
    this.Vt = paramString5;
  }
  
  public ParticipantEntity(Participant paramParticipant)
  {
    this.BR = 3;
    this.Xr = paramParticipant.getParticipantId();
    this.NH = paramParticipant.getDisplayName();
    this.Vh = paramParticipant.getIconImageUri();
    this.Vi = paramParticipant.getHiResImageUri();
    this.Fa = paramParticipant.getStatus();
    this.Wq = paramParticipant.jX();
    this.acg = paramParticipant.isConnectedToRoom();
    Player localPlayer = paramParticipant.getPlayer();
    if (localPlayer == null) {}
    for (PlayerEntity localPlayerEntity = null;; localPlayerEntity = new PlayerEntity(localPlayer))
    {
      this.Wh = localPlayerEntity;
      this.EZ = paramParticipant.getCapabilities();
      this.ach = paramParticipant.getResult();
      this.Vs = paramParticipant.getIconImageUrl();
      this.Vt = paramParticipant.getHiResImageUrl();
      return;
    }
  }
  
  static int a(Participant paramParticipant)
  {
    Object[] arrayOfObject = new Object[10];
    arrayOfObject[0] = paramParticipant.getPlayer();
    arrayOfObject[1] = Integer.valueOf(paramParticipant.getStatus());
    arrayOfObject[2] = paramParticipant.jX();
    arrayOfObject[3] = Boolean.valueOf(paramParticipant.isConnectedToRoom());
    arrayOfObject[4] = paramParticipant.getDisplayName();
    arrayOfObject[5] = paramParticipant.getIconImageUri();
    arrayOfObject[6] = paramParticipant.getHiResImageUri();
    arrayOfObject[7] = Integer.valueOf(paramParticipant.getCapabilities());
    arrayOfObject[8] = paramParticipant.getResult();
    arrayOfObject[9] = paramParticipant.getParticipantId();
    return n.hashCode(arrayOfObject);
  }
  
  static boolean a(Participant paramParticipant, Object paramObject)
  {
    boolean bool = true;
    if (!(paramObject instanceof Participant)) {
      bool = false;
    }
    Participant localParticipant;
    do
    {
      do
      {
        return bool;
      } while (paramParticipant == paramObject);
      localParticipant = (Participant)paramObject;
    } while ((n.equal(localParticipant.getPlayer(), paramParticipant.getPlayer())) && (n.equal(Integer.valueOf(localParticipant.getStatus()), Integer.valueOf(paramParticipant.getStatus()))) && (n.equal(localParticipant.jX(), paramParticipant.jX())) && (n.equal(Boolean.valueOf(localParticipant.isConnectedToRoom()), Boolean.valueOf(paramParticipant.isConnectedToRoom()))) && (n.equal(localParticipant.getDisplayName(), paramParticipant.getDisplayName())) && (n.equal(localParticipant.getIconImageUri(), paramParticipant.getIconImageUri())) && (n.equal(localParticipant.getHiResImageUri(), paramParticipant.getHiResImageUri())) && (n.equal(Integer.valueOf(localParticipant.getCapabilities()), Integer.valueOf(paramParticipant.getCapabilities()))) && (n.equal(localParticipant.getResult(), paramParticipant.getResult())) && (n.equal(localParticipant.getParticipantId(), paramParticipant.getParticipantId())));
    return false;
  }
  
  static String b(Participant paramParticipant)
  {
    return n.h(paramParticipant).a("ParticipantId", paramParticipant.getParticipantId()).a("Player", paramParticipant.getPlayer()).a("Status", Integer.valueOf(paramParticipant.getStatus())).a("ClientAddress", paramParticipant.jX()).a("ConnectedToRoom", Boolean.valueOf(paramParticipant.isConnectedToRoom())).a("DisplayName", paramParticipant.getDisplayName()).a("IconImage", paramParticipant.getIconImageUri()).a("IconImageUrl", paramParticipant.getIconImageUrl()).a("HiResImage", paramParticipant.getHiResImageUri()).a("HiResImageUrl", paramParticipant.getHiResImageUrl()).a("Capabilities", Integer.valueOf(paramParticipant.getCapabilities())).a("Result", paramParticipant.getResult()).toString();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return a(this, paramObject);
  }
  
  public Participant freeze()
  {
    return this;
  }
  
  public int getCapabilities()
  {
    return this.EZ;
  }
  
  public String getDisplayName()
  {
    if (this.Wh == null) {
      return this.NH;
    }
    return this.Wh.getDisplayName();
  }
  
  public void getDisplayName(CharArrayBuffer paramCharArrayBuffer)
  {
    if (this.Wh == null)
    {
      jv.b(this.NH, paramCharArrayBuffer);
      return;
    }
    this.Wh.getDisplayName(paramCharArrayBuffer);
  }
  
  public Uri getHiResImageUri()
  {
    if (this.Wh == null) {
      return this.Vi;
    }
    return this.Wh.getHiResImageUri();
  }
  
  public String getHiResImageUrl()
  {
    if (this.Wh == null) {
      return this.Vt;
    }
    return this.Wh.getHiResImageUrl();
  }
  
  public Uri getIconImageUri()
  {
    if (this.Wh == null) {
      return this.Vh;
    }
    return this.Wh.getIconImageUri();
  }
  
  public String getIconImageUrl()
  {
    if (this.Wh == null) {
      return this.Vs;
    }
    return this.Wh.getIconImageUrl();
  }
  
  public String getParticipantId()
  {
    return this.Xr;
  }
  
  public Player getPlayer()
  {
    return this.Wh;
  }
  
  public ParticipantResult getResult()
  {
    return this.ach;
  }
  
  public int getStatus()
  {
    return this.Fa;
  }
  
  public int getVersionCode()
  {
    return this.BR;
  }
  
  public int hashCode()
  {
    return a(this);
  }
  
  public boolean isConnectedToRoom()
  {
    return this.acg;
  }
  
  public boolean isDataValid()
  {
    return true;
  }
  
  public String jX()
  {
    return this.Wq;
  }
  
  public String toString()
  {
    return b(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    if (!gQ())
    {
      ParticipantEntityCreator.a(this, paramParcel, paramInt);
      return;
    }
    paramParcel.writeString(this.Xr);
    paramParcel.writeString(this.NH);
    String str1;
    label39:
    String str2;
    label58:
    int i;
    label90:
    int j;
    if (this.Vh == null)
    {
      str1 = null;
      paramParcel.writeString(str1);
      Uri localUri = this.Vi;
      str2 = null;
      if (localUri != null) {
        break label144;
      }
      paramParcel.writeString(str2);
      paramParcel.writeInt(this.Fa);
      paramParcel.writeString(this.Wq);
      if (!this.acg) {
        break label156;
      }
      i = 1;
      paramParcel.writeInt(i);
      PlayerEntity localPlayerEntity = this.Wh;
      j = 0;
      if (localPlayerEntity != null) {
        break label162;
      }
    }
    for (;;)
    {
      paramParcel.writeInt(j);
      if (this.Wh == null) {
        break;
      }
      this.Wh.writeToParcel(paramParcel, paramInt);
      return;
      str1 = this.Vh.toString();
      break label39;
      label144:
      str2 = this.Vi.toString();
      break label58;
      label156:
      i = 0;
      break label90;
      label162:
      j = 1;
    }
  }
  
  static final class ParticipantEntityCreatorCompat
    extends ParticipantEntityCreator
  {
    public ParticipantEntity cm(Parcel paramParcel)
    {
      boolean bool1 = true;
      if ((ParticipantEntity.b(ParticipantEntity.jT())) || (ParticipantEntity.bw(ParticipantEntity.class.getCanonicalName()))) {
        return super.cm(paramParcel);
      }
      String str1 = paramParcel.readString();
      String str2 = paramParcel.readString();
      String str3 = paramParcel.readString();
      Uri localUri1;
      String str4;
      Uri localUri2;
      label67:
      int i;
      String str5;
      boolean bool2;
      if (str3 == null)
      {
        localUri1 = null;
        str4 = paramParcel.readString();
        if (str4 != null) {
          break label153;
        }
        localUri2 = null;
        i = paramParcel.readInt();
        str5 = paramParcel.readString();
        if (paramParcel.readInt() <= 0) {
          break label163;
        }
        bool2 = bool1;
        label89:
        if (paramParcel.readInt() <= 0) {
          break label169;
        }
        label96:
        if (!bool1) {
          break label174;
        }
      }
      label153:
      label163:
      label169:
      label174:
      for (PlayerEntity localPlayerEntity = (PlayerEntity)PlayerEntity.CREATOR.createFromParcel(paramParcel);; localPlayerEntity = null)
      {
        return new ParticipantEntity(3, str1, str2, localUri1, localUri2, i, str5, bool2, localPlayerEntity, 7, null, null, null);
        localUri1 = Uri.parse(str3);
        break;
        localUri2 = Uri.parse(str4);
        break label67;
        bool2 = false;
        break label89;
        bool1 = false;
        break label96;
      }
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.multiplayer.ParticipantEntity
 * JD-Core Version:    0.7.0.1
 */