package com.google.android.gms.games.leaderboard;

import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.games.internal.constants.LeaderboardCollection;
import com.google.android.gms.games.internal.constants.TimeSpan;

public final class LeaderboardVariantEntity
  implements LeaderboardVariant
{
  private final int abM;
  private final int abN;
  private final boolean abO;
  private final long abP;
  private final String abQ;
  private final long abR;
  private final String abS;
  private final String abT;
  private final long abU;
  private final String abV;
  private final String abW;
  private final String abX;
  
  public LeaderboardVariantEntity(LeaderboardVariant paramLeaderboardVariant)
  {
    this.abM = paramLeaderboardVariant.getTimeSpan();
    this.abN = paramLeaderboardVariant.getCollection();
    this.abO = paramLeaderboardVariant.hasPlayerInfo();
    this.abP = paramLeaderboardVariant.getRawPlayerScore();
    this.abQ = paramLeaderboardVariant.getDisplayPlayerScore();
    this.abR = paramLeaderboardVariant.getPlayerRank();
    this.abS = paramLeaderboardVariant.getDisplayPlayerRank();
    this.abT = paramLeaderboardVariant.getPlayerScoreTag();
    this.abU = paramLeaderboardVariant.getNumScores();
    this.abV = paramLeaderboardVariant.lD();
    this.abW = paramLeaderboardVariant.lE();
    this.abX = paramLeaderboardVariant.lF();
  }
  
  static int a(LeaderboardVariant paramLeaderboardVariant)
  {
    Object[] arrayOfObject = new Object[11];
    arrayOfObject[0] = Integer.valueOf(paramLeaderboardVariant.getTimeSpan());
    arrayOfObject[1] = Integer.valueOf(paramLeaderboardVariant.getCollection());
    arrayOfObject[2] = Boolean.valueOf(paramLeaderboardVariant.hasPlayerInfo());
    arrayOfObject[3] = Long.valueOf(paramLeaderboardVariant.getRawPlayerScore());
    arrayOfObject[4] = paramLeaderboardVariant.getDisplayPlayerScore();
    arrayOfObject[5] = Long.valueOf(paramLeaderboardVariant.getPlayerRank());
    arrayOfObject[6] = paramLeaderboardVariant.getDisplayPlayerRank();
    arrayOfObject[7] = Long.valueOf(paramLeaderboardVariant.getNumScores());
    arrayOfObject[8] = paramLeaderboardVariant.lD();
    arrayOfObject[9] = paramLeaderboardVariant.lF();
    arrayOfObject[10] = paramLeaderboardVariant.lE();
    return n.hashCode(arrayOfObject);
  }
  
  static boolean a(LeaderboardVariant paramLeaderboardVariant, Object paramObject)
  {
    boolean bool = true;
    if (!(paramObject instanceof LeaderboardVariant)) {
      bool = false;
    }
    LeaderboardVariant localLeaderboardVariant;
    do
    {
      do
      {
        return bool;
      } while (paramLeaderboardVariant == paramObject);
      localLeaderboardVariant = (LeaderboardVariant)paramObject;
    } while ((n.equal(Integer.valueOf(localLeaderboardVariant.getTimeSpan()), Integer.valueOf(paramLeaderboardVariant.getTimeSpan()))) && (n.equal(Integer.valueOf(localLeaderboardVariant.getCollection()), Integer.valueOf(paramLeaderboardVariant.getCollection()))) && (n.equal(Boolean.valueOf(localLeaderboardVariant.hasPlayerInfo()), Boolean.valueOf(paramLeaderboardVariant.hasPlayerInfo()))) && (n.equal(Long.valueOf(localLeaderboardVariant.getRawPlayerScore()), Long.valueOf(paramLeaderboardVariant.getRawPlayerScore()))) && (n.equal(localLeaderboardVariant.getDisplayPlayerScore(), paramLeaderboardVariant.getDisplayPlayerScore())) && (n.equal(Long.valueOf(localLeaderboardVariant.getPlayerRank()), Long.valueOf(paramLeaderboardVariant.getPlayerRank()))) && (n.equal(localLeaderboardVariant.getDisplayPlayerRank(), paramLeaderboardVariant.getDisplayPlayerRank())) && (n.equal(Long.valueOf(localLeaderboardVariant.getNumScores()), Long.valueOf(paramLeaderboardVariant.getNumScores()))) && (n.equal(localLeaderboardVariant.lD(), paramLeaderboardVariant.lD())) && (n.equal(localLeaderboardVariant.lF(), paramLeaderboardVariant.lF())) && (n.equal(localLeaderboardVariant.lE(), paramLeaderboardVariant.lE())));
    return false;
  }
  
  static String b(LeaderboardVariant paramLeaderboardVariant)
  {
    n.a locala1 = n.h(paramLeaderboardVariant).a("TimeSpan", TimeSpan.dH(paramLeaderboardVariant.getTimeSpan())).a("Collection", LeaderboardCollection.dH(paramLeaderboardVariant.getCollection()));
    Object localObject1;
    String str1;
    label77:
    Object localObject2;
    label107:
    n.a locala4;
    if (paramLeaderboardVariant.hasPlayerInfo())
    {
      localObject1 = Long.valueOf(paramLeaderboardVariant.getRawPlayerScore());
      n.a locala2 = locala1.a("RawPlayerScore", localObject1);
      if (!paramLeaderboardVariant.hasPlayerInfo()) {
        break label201;
      }
      str1 = paramLeaderboardVariant.getDisplayPlayerScore();
      n.a locala3 = locala2.a("DisplayPlayerScore", str1);
      if (!paramLeaderboardVariant.hasPlayerInfo()) {
        break label208;
      }
      localObject2 = Long.valueOf(paramLeaderboardVariant.getPlayerRank());
      locala4 = locala3.a("PlayerRank", localObject2);
      if (!paramLeaderboardVariant.hasPlayerInfo()) {
        break label215;
      }
    }
    label201:
    label208:
    label215:
    for (String str2 = paramLeaderboardVariant.getDisplayPlayerRank();; str2 = "none")
    {
      return locala4.a("DisplayPlayerRank", str2).a("NumScores", Long.valueOf(paramLeaderboardVariant.getNumScores())).a("TopPageNextToken", paramLeaderboardVariant.lD()).a("WindowPageNextToken", paramLeaderboardVariant.lF()).a("WindowPagePrevToken", paramLeaderboardVariant.lE()).toString();
      localObject1 = "none";
      break;
      str1 = "none";
      break label77;
      localObject2 = "none";
      break label107;
    }
  }
  
  public boolean equals(Object paramObject)
  {
    return a(this, paramObject);
  }
  
  public int getCollection()
  {
    return this.abN;
  }
  
  public String getDisplayPlayerRank()
  {
    return this.abS;
  }
  
  public String getDisplayPlayerScore()
  {
    return this.abQ;
  }
  
  public long getNumScores()
  {
    return this.abU;
  }
  
  public long getPlayerRank()
  {
    return this.abR;
  }
  
  public String getPlayerScoreTag()
  {
    return this.abT;
  }
  
  public long getRawPlayerScore()
  {
    return this.abP;
  }
  
  public int getTimeSpan()
  {
    return this.abM;
  }
  
  public boolean hasPlayerInfo()
  {
    return this.abO;
  }
  
  public int hashCode()
  {
    return a(this);
  }
  
  public boolean isDataValid()
  {
    return true;
  }
  
  public String lD()
  {
    return this.abV;
  }
  
  public String lE()
  {
    return this.abW;
  }
  
  public String lF()
  {
    return this.abX;
  }
  
  public LeaderboardVariant lG()
  {
    return this;
  }
  
  public String toString()
  {
    return b(this);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.leaderboard.LeaderboardVariantEntity
 * JD-Core Version:    0.7.0.1
 */