package com.google.android.gms.games.quest;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.internal.jv;
import java.util.ArrayList;
import java.util.List;

public final class QuestEntity
  implements SafeParcelable, Quest
{
  public static final QuestEntityCreator CREATOR = new QuestEntityCreator();
  private final int BR;
  private final int FD;
  private final String Tr;
  private final long Wk;
  private final GameEntity aay;
  private final String acR;
  private final long acS;
  private final Uri acT;
  private final String acU;
  private final long acV;
  private final Uri acW;
  private final String acX;
  private final long acY;
  private final long acZ;
  private final ArrayList<MilestoneEntity> ada;
  private final String mName;
  private final int mState;
  
  QuestEntity(int paramInt1, GameEntity paramGameEntity, String paramString1, long paramLong1, Uri paramUri1, String paramString2, String paramString3, long paramLong2, long paramLong3, Uri paramUri2, String paramString4, String paramString5, long paramLong4, long paramLong5, int paramInt2, int paramInt3, ArrayList<MilestoneEntity> paramArrayList)
  {
    this.BR = paramInt1;
    this.aay = paramGameEntity;
    this.acR = paramString1;
    this.acS = paramLong1;
    this.acT = paramUri1;
    this.acU = paramString2;
    this.Tr = paramString3;
    this.acV = paramLong2;
    this.Wk = paramLong3;
    this.acW = paramUri2;
    this.acX = paramString4;
    this.mName = paramString5;
    this.acY = paramLong4;
    this.acZ = paramLong5;
    this.mState = paramInt2;
    this.FD = paramInt3;
    this.ada = paramArrayList;
  }
  
  public QuestEntity(Quest paramQuest)
  {
    this.BR = 2;
    this.aay = new GameEntity(paramQuest.getGame());
    this.acR = paramQuest.getQuestId();
    this.acS = paramQuest.getAcceptedTimestamp();
    this.Tr = paramQuest.getDescription();
    this.acT = paramQuest.getBannerImageUri();
    this.acU = paramQuest.getBannerImageUrl();
    this.acV = paramQuest.getEndTimestamp();
    this.acW = paramQuest.getIconImageUri();
    this.acX = paramQuest.getIconImageUrl();
    this.Wk = paramQuest.getLastUpdatedTimestamp();
    this.mName = paramQuest.getName();
    this.acY = paramQuest.lK();
    this.acZ = paramQuest.getStartTimestamp();
    this.mState = paramQuest.getState();
    this.FD = paramQuest.getType();
    List localList = paramQuest.lJ();
    int i = localList.size();
    this.ada = new ArrayList(i);
    for (int j = 0; j < i; j++) {
      this.ada.add((MilestoneEntity)((Milestone)localList.get(j)).freeze());
    }
  }
  
  static int a(Quest paramQuest)
  {
    Object[] arrayOfObject = new Object[13];
    arrayOfObject[0] = paramQuest.getGame();
    arrayOfObject[1] = paramQuest.getQuestId();
    arrayOfObject[2] = Long.valueOf(paramQuest.getAcceptedTimestamp());
    arrayOfObject[3] = paramQuest.getBannerImageUri();
    arrayOfObject[4] = paramQuest.getDescription();
    arrayOfObject[5] = Long.valueOf(paramQuest.getEndTimestamp());
    arrayOfObject[6] = paramQuest.getIconImageUri();
    arrayOfObject[7] = Long.valueOf(paramQuest.getLastUpdatedTimestamp());
    arrayOfObject[8] = paramQuest.lJ();
    arrayOfObject[9] = paramQuest.getName();
    arrayOfObject[10] = Long.valueOf(paramQuest.lK());
    arrayOfObject[11] = Long.valueOf(paramQuest.getStartTimestamp());
    arrayOfObject[12] = Integer.valueOf(paramQuest.getState());
    return n.hashCode(arrayOfObject);
  }
  
  static boolean a(Quest paramQuest, Object paramObject)
  {
    boolean bool = true;
    if (!(paramObject instanceof Quest)) {
      bool = false;
    }
    Quest localQuest;
    do
    {
      do
      {
        return bool;
      } while (paramQuest == paramObject);
      localQuest = (Quest)paramObject;
    } while ((n.equal(localQuest.getGame(), paramQuest.getGame())) && (n.equal(localQuest.getQuestId(), paramQuest.getQuestId())) && (n.equal(Long.valueOf(localQuest.getAcceptedTimestamp()), Long.valueOf(paramQuest.getAcceptedTimestamp()))) && (n.equal(localQuest.getBannerImageUri(), paramQuest.getBannerImageUri())) && (n.equal(localQuest.getDescription(), paramQuest.getDescription())) && (n.equal(Long.valueOf(localQuest.getEndTimestamp()), Long.valueOf(paramQuest.getEndTimestamp()))) && (n.equal(localQuest.getIconImageUri(), paramQuest.getIconImageUri())) && (n.equal(Long.valueOf(localQuest.getLastUpdatedTimestamp()), Long.valueOf(paramQuest.getLastUpdatedTimestamp()))) && (n.equal(localQuest.lJ(), paramQuest.lJ())) && (n.equal(localQuest.getName(), paramQuest.getName())) && (n.equal(Long.valueOf(localQuest.lK()), Long.valueOf(paramQuest.lK()))) && (n.equal(Long.valueOf(localQuest.getStartTimestamp()), Long.valueOf(paramQuest.getStartTimestamp()))) && (n.equal(Integer.valueOf(localQuest.getState()), Integer.valueOf(paramQuest.getState()))));
    return false;
  }
  
  static String b(Quest paramQuest)
  {
    return n.h(paramQuest).a("Game", paramQuest.getGame()).a("QuestId", paramQuest.getQuestId()).a("AcceptedTimestamp", Long.valueOf(paramQuest.getAcceptedTimestamp())).a("BannerImageUri", paramQuest.getBannerImageUri()).a("BannerImageUrl", paramQuest.getBannerImageUrl()).a("Description", paramQuest.getDescription()).a("EndTimestamp", Long.valueOf(paramQuest.getEndTimestamp())).a("IconImageUri", paramQuest.getIconImageUri()).a("IconImageUrl", paramQuest.getIconImageUrl()).a("LastUpdatedTimestamp", Long.valueOf(paramQuest.getLastUpdatedTimestamp())).a("Milestones", paramQuest.lJ()).a("Name", paramQuest.getName()).a("NotifyTimestamp", Long.valueOf(paramQuest.lK())).a("StartTimestamp", Long.valueOf(paramQuest.getStartTimestamp())).a("State", Integer.valueOf(paramQuest.getState())).toString();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return a(this, paramObject);
  }
  
  public Quest freeze()
  {
    return this;
  }
  
  public long getAcceptedTimestamp()
  {
    return this.acS;
  }
  
  public Uri getBannerImageUri()
  {
    return this.acT;
  }
  
  public String getBannerImageUrl()
  {
    return this.acU;
  }
  
  public Milestone getCurrentMilestone()
  {
    return (Milestone)lJ().get(0);
  }
  
  public String getDescription()
  {
    return this.Tr;
  }
  
  public void getDescription(CharArrayBuffer paramCharArrayBuffer)
  {
    jv.b(this.Tr, paramCharArrayBuffer);
  }
  
  public long getEndTimestamp()
  {
    return this.acV;
  }
  
  public Game getGame()
  {
    return this.aay;
  }
  
  public Uri getIconImageUri()
  {
    return this.acW;
  }
  
  public String getIconImageUrl()
  {
    return this.acX;
  }
  
  public long getLastUpdatedTimestamp()
  {
    return this.Wk;
  }
  
  public String getName()
  {
    return this.mName;
  }
  
  public void getName(CharArrayBuffer paramCharArrayBuffer)
  {
    jv.b(this.mName, paramCharArrayBuffer);
  }
  
  public String getQuestId()
  {
    return this.acR;
  }
  
  public long getStartTimestamp()
  {
    return this.acZ;
  }
  
  public int getState()
  {
    return this.mState;
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
  
  public boolean isEndingSoon()
  {
    return this.acY <= 1800000L + System.currentTimeMillis();
  }
  
  public List<Milestone> lJ()
  {
    return new ArrayList(this.ada);
  }
  
  public long lK()
  {
    return this.acY;
  }
  
  public String toString()
  {
    return b(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    QuestEntityCreator.a(this, paramParcel, paramInt);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.quest.QuestEntity
 * JD-Core Version:    0.7.0.1
 */