package com.google.android.gms.games.multiplayer.turnbased;

import android.database.CharArrayBuffer;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.multiplayer.Participant;
import com.google.android.gms.games.multiplayer.ParticipantEntity;
import com.google.android.gms.internal.jv;
import java.util.ArrayList;

public final class TurnBasedMatchEntity
  implements SafeParcelable, TurnBasedMatch
{
  public static final TurnBasedMatchEntityCreator CREATOR = new TurnBasedMatchEntityCreator();
  private final int BR;
  private final int Di;
  private final String Tr;
  private final long Wk;
  private final String Xh;
  private final GameEntity aay;
  private final long abZ;
  private final String acE;
  private final String acF;
  private final int acG;
  private final byte[] acH;
  private final String acI;
  private final byte[] acJ;
  private final int acK;
  private final int acL;
  private final boolean acM;
  private final String acN;
  private final ArrayList<ParticipantEntity> acc;
  private final int acd;
  private final Bundle acs;
  private final String acw;
  
  TurnBasedMatchEntity(int paramInt1, GameEntity paramGameEntity, String paramString1, String paramString2, long paramLong1, String paramString3, long paramLong2, String paramString4, int paramInt2, int paramInt3, int paramInt4, byte[] paramArrayOfByte1, ArrayList<ParticipantEntity> paramArrayList, String paramString5, byte[] paramArrayOfByte2, int paramInt5, Bundle paramBundle, int paramInt6, boolean paramBoolean, String paramString6, String paramString7)
  {
    this.BR = paramInt1;
    this.aay = paramGameEntity;
    this.Xh = paramString1;
    this.acw = paramString2;
    this.abZ = paramLong1;
    this.acE = paramString3;
    this.Wk = paramLong2;
    this.acF = paramString4;
    this.acG = paramInt2;
    this.acL = paramInt6;
    this.acd = paramInt3;
    this.Di = paramInt4;
    this.acH = paramArrayOfByte1;
    this.acc = paramArrayList;
    this.acI = paramString5;
    this.acJ = paramArrayOfByte2;
    this.acK = paramInt5;
    this.acs = paramBundle;
    this.acM = paramBoolean;
    this.Tr = paramString6;
    this.acN = paramString7;
  }
  
  public TurnBasedMatchEntity(TurnBasedMatch paramTurnBasedMatch)
  {
    this.BR = 2;
    this.aay = new GameEntity(paramTurnBasedMatch.getGame());
    this.Xh = paramTurnBasedMatch.getMatchId();
    this.acw = paramTurnBasedMatch.getCreatorId();
    this.abZ = paramTurnBasedMatch.getCreationTimestamp();
    this.acE = paramTurnBasedMatch.getLastUpdaterId();
    this.Wk = paramTurnBasedMatch.getLastUpdatedTimestamp();
    this.acF = paramTurnBasedMatch.getPendingParticipantId();
    this.acG = paramTurnBasedMatch.getStatus();
    this.acL = paramTurnBasedMatch.getTurnStatus();
    this.acd = paramTurnBasedMatch.getVariant();
    this.Di = paramTurnBasedMatch.getVersion();
    this.acI = paramTurnBasedMatch.getRematchId();
    this.acK = paramTurnBasedMatch.getMatchNumber();
    this.acs = paramTurnBasedMatch.getAutoMatchCriteria();
    this.acM = paramTurnBasedMatch.isLocallyModified();
    this.Tr = paramTurnBasedMatch.getDescription();
    this.acN = paramTurnBasedMatch.getDescriptionParticipantId();
    byte[] arrayOfByte1 = paramTurnBasedMatch.getData();
    byte[] arrayOfByte2;
    if (arrayOfByte1 == null)
    {
      this.acH = null;
      arrayOfByte2 = paramTurnBasedMatch.getPreviousMatchData();
      if (arrayOfByte2 != null) {
        break label314;
      }
      this.acJ = null;
    }
    for (;;)
    {
      ArrayList localArrayList = paramTurnBasedMatch.getParticipants();
      int i = localArrayList.size();
      this.acc = new ArrayList(i);
      for (int j = 0; j < i; j++) {
        this.acc.add((ParticipantEntity)((Participant)localArrayList.get(j)).freeze());
      }
      this.acH = new byte[arrayOfByte1.length];
      System.arraycopy(arrayOfByte1, 0, this.acH, 0, arrayOfByte1.length);
      break;
      label314:
      this.acJ = new byte[arrayOfByte2.length];
      System.arraycopy(arrayOfByte2, 0, this.acJ, 0, arrayOfByte2.length);
    }
  }
  
  static int a(TurnBasedMatch paramTurnBasedMatch)
  {
    Object[] arrayOfObject = new Object[18];
    arrayOfObject[0] = paramTurnBasedMatch.getGame();
    arrayOfObject[1] = paramTurnBasedMatch.getMatchId();
    arrayOfObject[2] = paramTurnBasedMatch.getCreatorId();
    arrayOfObject[3] = Long.valueOf(paramTurnBasedMatch.getCreationTimestamp());
    arrayOfObject[4] = paramTurnBasedMatch.getLastUpdaterId();
    arrayOfObject[5] = Long.valueOf(paramTurnBasedMatch.getLastUpdatedTimestamp());
    arrayOfObject[6] = paramTurnBasedMatch.getPendingParticipantId();
    arrayOfObject[7] = Integer.valueOf(paramTurnBasedMatch.getStatus());
    arrayOfObject[8] = Integer.valueOf(paramTurnBasedMatch.getTurnStatus());
    arrayOfObject[9] = paramTurnBasedMatch.getDescription();
    arrayOfObject[10] = Integer.valueOf(paramTurnBasedMatch.getVariant());
    arrayOfObject[11] = Integer.valueOf(paramTurnBasedMatch.getVersion());
    arrayOfObject[12] = paramTurnBasedMatch.getParticipants();
    arrayOfObject[13] = paramTurnBasedMatch.getRematchId();
    arrayOfObject[14] = Integer.valueOf(paramTurnBasedMatch.getMatchNumber());
    arrayOfObject[15] = paramTurnBasedMatch.getAutoMatchCriteria();
    arrayOfObject[16] = Integer.valueOf(paramTurnBasedMatch.getAvailableAutoMatchSlots());
    arrayOfObject[17] = Boolean.valueOf(paramTurnBasedMatch.isLocallyModified());
    return n.hashCode(arrayOfObject);
  }
  
  static int a(TurnBasedMatch paramTurnBasedMatch, String paramString)
  {
    ArrayList localArrayList = paramTurnBasedMatch.getParticipants();
    int i = localArrayList.size();
    for (int j = 0; j < i; j++)
    {
      Participant localParticipant = (Participant)localArrayList.get(j);
      if (localParticipant.getParticipantId().equals(paramString)) {
        return localParticipant.getStatus();
      }
    }
    throw new IllegalStateException("Participant " + paramString + " is not in match " + paramTurnBasedMatch.getMatchId());
  }
  
  static boolean a(TurnBasedMatch paramTurnBasedMatch, Object paramObject)
  {
    boolean bool = true;
    if (!(paramObject instanceof TurnBasedMatch)) {
      bool = false;
    }
    TurnBasedMatch localTurnBasedMatch;
    do
    {
      do
      {
        return bool;
      } while (paramTurnBasedMatch == paramObject);
      localTurnBasedMatch = (TurnBasedMatch)paramObject;
    } while ((n.equal(localTurnBasedMatch.getGame(), paramTurnBasedMatch.getGame())) && (n.equal(localTurnBasedMatch.getMatchId(), paramTurnBasedMatch.getMatchId())) && (n.equal(localTurnBasedMatch.getCreatorId(), paramTurnBasedMatch.getCreatorId())) && (n.equal(Long.valueOf(localTurnBasedMatch.getCreationTimestamp()), Long.valueOf(paramTurnBasedMatch.getCreationTimestamp()))) && (n.equal(localTurnBasedMatch.getLastUpdaterId(), paramTurnBasedMatch.getLastUpdaterId())) && (n.equal(Long.valueOf(localTurnBasedMatch.getLastUpdatedTimestamp()), Long.valueOf(paramTurnBasedMatch.getLastUpdatedTimestamp()))) && (n.equal(localTurnBasedMatch.getPendingParticipantId(), paramTurnBasedMatch.getPendingParticipantId())) && (n.equal(Integer.valueOf(localTurnBasedMatch.getStatus()), Integer.valueOf(paramTurnBasedMatch.getStatus()))) && (n.equal(Integer.valueOf(localTurnBasedMatch.getTurnStatus()), Integer.valueOf(paramTurnBasedMatch.getTurnStatus()))) && (n.equal(localTurnBasedMatch.getDescription(), paramTurnBasedMatch.getDescription())) && (n.equal(Integer.valueOf(localTurnBasedMatch.getVariant()), Integer.valueOf(paramTurnBasedMatch.getVariant()))) && (n.equal(Integer.valueOf(localTurnBasedMatch.getVersion()), Integer.valueOf(paramTurnBasedMatch.getVersion()))) && (n.equal(localTurnBasedMatch.getParticipants(), paramTurnBasedMatch.getParticipants())) && (n.equal(localTurnBasedMatch.getRematchId(), paramTurnBasedMatch.getRematchId())) && (n.equal(Integer.valueOf(localTurnBasedMatch.getMatchNumber()), Integer.valueOf(paramTurnBasedMatch.getMatchNumber()))) && (n.equal(localTurnBasedMatch.getAutoMatchCriteria(), paramTurnBasedMatch.getAutoMatchCriteria())) && (n.equal(Integer.valueOf(localTurnBasedMatch.getAvailableAutoMatchSlots()), Integer.valueOf(paramTurnBasedMatch.getAvailableAutoMatchSlots()))) && (n.equal(Boolean.valueOf(localTurnBasedMatch.isLocallyModified()), Boolean.valueOf(paramTurnBasedMatch.isLocallyModified()))));
    return false;
  }
  
  static String b(TurnBasedMatch paramTurnBasedMatch)
  {
    return n.h(paramTurnBasedMatch).a("Game", paramTurnBasedMatch.getGame()).a("MatchId", paramTurnBasedMatch.getMatchId()).a("CreatorId", paramTurnBasedMatch.getCreatorId()).a("CreationTimestamp", Long.valueOf(paramTurnBasedMatch.getCreationTimestamp())).a("LastUpdaterId", paramTurnBasedMatch.getLastUpdaterId()).a("LastUpdatedTimestamp", Long.valueOf(paramTurnBasedMatch.getLastUpdatedTimestamp())).a("PendingParticipantId", paramTurnBasedMatch.getPendingParticipantId()).a("MatchStatus", Integer.valueOf(paramTurnBasedMatch.getStatus())).a("TurnStatus", Integer.valueOf(paramTurnBasedMatch.getTurnStatus())).a("Description", paramTurnBasedMatch.getDescription()).a("Variant", Integer.valueOf(paramTurnBasedMatch.getVariant())).a("Data", paramTurnBasedMatch.getData()).a("Version", Integer.valueOf(paramTurnBasedMatch.getVersion())).a("Participants", paramTurnBasedMatch.getParticipants()).a("RematchId", paramTurnBasedMatch.getRematchId()).a("PreviousData", paramTurnBasedMatch.getPreviousMatchData()).a("MatchNumber", Integer.valueOf(paramTurnBasedMatch.getMatchNumber())).a("AutoMatchCriteria", paramTurnBasedMatch.getAutoMatchCriteria()).a("AvailableAutoMatchSlots", Integer.valueOf(paramTurnBasedMatch.getAvailableAutoMatchSlots())).a("LocallyModified", Boolean.valueOf(paramTurnBasedMatch.isLocallyModified())).a("DescriptionParticipantId", paramTurnBasedMatch.getDescriptionParticipantId()).toString();
  }
  
  static String b(TurnBasedMatch paramTurnBasedMatch, String paramString)
  {
    ArrayList localArrayList = paramTurnBasedMatch.getParticipants();
    int i = localArrayList.size();
    for (int j = 0; j < i; j++)
    {
      Participant localParticipant = (Participant)localArrayList.get(j);
      Player localPlayer = localParticipant.getPlayer();
      if ((localPlayer != null) && (localPlayer.getPlayerId().equals(paramString))) {
        return localParticipant.getParticipantId();
      }
    }
    return null;
  }
  
  static Participant c(TurnBasedMatch paramTurnBasedMatch, String paramString)
  {
    ArrayList localArrayList = paramTurnBasedMatch.getParticipants();
    int i = localArrayList.size();
    for (int j = 0; j < i; j++)
    {
      Participant localParticipant = (Participant)localArrayList.get(j);
      if (localParticipant.getParticipantId().equals(paramString)) {
        return localParticipant;
      }
    }
    throw new IllegalStateException("Participant " + paramString + " is not in match " + paramTurnBasedMatch.getMatchId());
  }
  
  static ArrayList<String> c(TurnBasedMatch paramTurnBasedMatch)
  {
    ArrayList localArrayList1 = paramTurnBasedMatch.getParticipants();
    int i = localArrayList1.size();
    ArrayList localArrayList2 = new ArrayList(i);
    for (int j = 0; j < i; j++) {
      localArrayList2.add(((Participant)localArrayList1.get(j)).getParticipantId());
    }
    return localArrayList2;
  }
  
  public boolean canRematch()
  {
    return (this.acG == 2) && (this.acI == null);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return a(this, paramObject);
  }
  
  public TurnBasedMatch freeze()
  {
    return this;
  }
  
  public Bundle getAutoMatchCriteria()
  {
    return this.acs;
  }
  
  public int getAvailableAutoMatchSlots()
  {
    if (this.acs == null) {
      return 0;
    }
    return this.acs.getInt("max_automatch_players");
  }
  
  public long getCreationTimestamp()
  {
    return this.abZ;
  }
  
  public String getCreatorId()
  {
    return this.acw;
  }
  
  public byte[] getData()
  {
    return this.acH;
  }
  
  public String getDescription()
  {
    return this.Tr;
  }
  
  public void getDescription(CharArrayBuffer paramCharArrayBuffer)
  {
    jv.b(this.Tr, paramCharArrayBuffer);
  }
  
  public Participant getDescriptionParticipant()
  {
    String str = getDescriptionParticipantId();
    if (str == null) {
      return null;
    }
    return getParticipant(str);
  }
  
  public String getDescriptionParticipantId()
  {
    return this.acN;
  }
  
  public Game getGame()
  {
    return this.aay;
  }
  
  public long getLastUpdatedTimestamp()
  {
    return this.Wk;
  }
  
  public String getLastUpdaterId()
  {
    return this.acE;
  }
  
  public String getMatchId()
  {
    return this.Xh;
  }
  
  public int getMatchNumber()
  {
    return this.acK;
  }
  
  public Participant getParticipant(String paramString)
  {
    return c(this, paramString);
  }
  
  public String getParticipantId(String paramString)
  {
    return b(this, paramString);
  }
  
  public ArrayList<String> getParticipantIds()
  {
    return c(this);
  }
  
  public int getParticipantStatus(String paramString)
  {
    return a(this, paramString);
  }
  
  public ArrayList<Participant> getParticipants()
  {
    return new ArrayList(this.acc);
  }
  
  public String getPendingParticipantId()
  {
    return this.acF;
  }
  
  public byte[] getPreviousMatchData()
  {
    return this.acJ;
  }
  
  public String getRematchId()
  {
    return this.acI;
  }
  
  public int getStatus()
  {
    return this.acG;
  }
  
  public int getTurnStatus()
  {
    return this.acL;
  }
  
  public int getVariant()
  {
    return this.acd;
  }
  
  public int getVersion()
  {
    return this.Di;
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
  
  public boolean isLocallyModified()
  {
    return this.acM;
  }
  
  public String toString()
  {
    return b(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    TurnBasedMatchEntityCreator.a(this, paramParcel, paramInt);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchEntity
 * JD-Core Version:    0.7.0.1
 */