package com.google.android.gms.games.leaderboard;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.internal.jv;

public final class LeaderboardScoreEntity
  implements LeaderboardScore
{
  private final String abA;
  private final String abB;
  private final long abC;
  private final long abD;
  private final String abE;
  private final Uri abF;
  private final Uri abG;
  private final PlayerEntity abH;
  private final String abI;
  private final String abJ;
  private final String abK;
  private final long abz;
  
  public LeaderboardScoreEntity(LeaderboardScore paramLeaderboardScore)
  {
    this.abz = paramLeaderboardScore.getRank();
    this.abA = ((String)o.i(paramLeaderboardScore.getDisplayRank()));
    this.abB = ((String)o.i(paramLeaderboardScore.getDisplayScore()));
    this.abC = paramLeaderboardScore.getRawScore();
    this.abD = paramLeaderboardScore.getTimestampMillis();
    this.abE = paramLeaderboardScore.getScoreHolderDisplayName();
    this.abF = paramLeaderboardScore.getScoreHolderIconImageUri();
    this.abG = paramLeaderboardScore.getScoreHolderHiResImageUri();
    Player localPlayer = paramLeaderboardScore.getScoreHolder();
    if (localPlayer == null) {}
    for (PlayerEntity localPlayerEntity = null;; localPlayerEntity = (PlayerEntity)localPlayer.freeze())
    {
      this.abH = localPlayerEntity;
      this.abI = paramLeaderboardScore.getScoreTag();
      this.abJ = paramLeaderboardScore.getScoreHolderIconImageUrl();
      this.abK = paramLeaderboardScore.getScoreHolderHiResImageUrl();
      return;
    }
  }
  
  static int a(LeaderboardScore paramLeaderboardScore)
  {
    Object[] arrayOfObject = new Object[9];
    arrayOfObject[0] = Long.valueOf(paramLeaderboardScore.getRank());
    arrayOfObject[1] = paramLeaderboardScore.getDisplayRank();
    arrayOfObject[2] = Long.valueOf(paramLeaderboardScore.getRawScore());
    arrayOfObject[3] = paramLeaderboardScore.getDisplayScore();
    arrayOfObject[4] = Long.valueOf(paramLeaderboardScore.getTimestampMillis());
    arrayOfObject[5] = paramLeaderboardScore.getScoreHolderDisplayName();
    arrayOfObject[6] = paramLeaderboardScore.getScoreHolderIconImageUri();
    arrayOfObject[7] = paramLeaderboardScore.getScoreHolderHiResImageUri();
    arrayOfObject[8] = paramLeaderboardScore.getScoreHolder();
    return n.hashCode(arrayOfObject);
  }
  
  static boolean a(LeaderboardScore paramLeaderboardScore, Object paramObject)
  {
    boolean bool = true;
    if (!(paramObject instanceof LeaderboardScore)) {
      bool = false;
    }
    LeaderboardScore localLeaderboardScore;
    do
    {
      do
      {
        return bool;
      } while (paramLeaderboardScore == paramObject);
      localLeaderboardScore = (LeaderboardScore)paramObject;
    } while ((n.equal(Long.valueOf(localLeaderboardScore.getRank()), Long.valueOf(paramLeaderboardScore.getRank()))) && (n.equal(localLeaderboardScore.getDisplayRank(), paramLeaderboardScore.getDisplayRank())) && (n.equal(Long.valueOf(localLeaderboardScore.getRawScore()), Long.valueOf(paramLeaderboardScore.getRawScore()))) && (n.equal(localLeaderboardScore.getDisplayScore(), paramLeaderboardScore.getDisplayScore())) && (n.equal(Long.valueOf(localLeaderboardScore.getTimestampMillis()), Long.valueOf(paramLeaderboardScore.getTimestampMillis()))) && (n.equal(localLeaderboardScore.getScoreHolderDisplayName(), paramLeaderboardScore.getScoreHolderDisplayName())) && (n.equal(localLeaderboardScore.getScoreHolderIconImageUri(), paramLeaderboardScore.getScoreHolderIconImageUri())) && (n.equal(localLeaderboardScore.getScoreHolderHiResImageUri(), paramLeaderboardScore.getScoreHolderHiResImageUri())) && (n.equal(localLeaderboardScore.getScoreHolder(), paramLeaderboardScore.getScoreHolder())) && (n.equal(localLeaderboardScore.getScoreTag(), paramLeaderboardScore.getScoreTag())));
    return false;
  }
  
  static String b(LeaderboardScore paramLeaderboardScore)
  {
    n.a locala = n.h(paramLeaderboardScore).a("Rank", Long.valueOf(paramLeaderboardScore.getRank())).a("DisplayRank", paramLeaderboardScore.getDisplayRank()).a("Score", Long.valueOf(paramLeaderboardScore.getRawScore())).a("DisplayScore", paramLeaderboardScore.getDisplayScore()).a("Timestamp", Long.valueOf(paramLeaderboardScore.getTimestampMillis())).a("DisplayName", paramLeaderboardScore.getScoreHolderDisplayName()).a("IconImageUri", paramLeaderboardScore.getScoreHolderIconImageUri()).a("IconImageUrl", paramLeaderboardScore.getScoreHolderIconImageUrl()).a("HiResImageUri", paramLeaderboardScore.getScoreHolderHiResImageUri()).a("HiResImageUrl", paramLeaderboardScore.getScoreHolderHiResImageUrl());
    if (paramLeaderboardScore.getScoreHolder() == null) {}
    for (Object localObject = null;; localObject = paramLeaderboardScore.getScoreHolder()) {
      return locala.a("Player", localObject).a("ScoreTag", paramLeaderboardScore.getScoreTag()).toString();
    }
  }
  
  public boolean equals(Object paramObject)
  {
    return a(this, paramObject);
  }
  
  public String getDisplayRank()
  {
    return this.abA;
  }
  
  public void getDisplayRank(CharArrayBuffer paramCharArrayBuffer)
  {
    jv.b(this.abA, paramCharArrayBuffer);
  }
  
  public String getDisplayScore()
  {
    return this.abB;
  }
  
  public void getDisplayScore(CharArrayBuffer paramCharArrayBuffer)
  {
    jv.b(this.abB, paramCharArrayBuffer);
  }
  
  public long getRank()
  {
    return this.abz;
  }
  
  public long getRawScore()
  {
    return this.abC;
  }
  
  public Player getScoreHolder()
  {
    return this.abH;
  }
  
  public String getScoreHolderDisplayName()
  {
    if (this.abH == null) {
      return this.abE;
    }
    return this.abH.getDisplayName();
  }
  
  public void getScoreHolderDisplayName(CharArrayBuffer paramCharArrayBuffer)
  {
    if (this.abH == null)
    {
      jv.b(this.abE, paramCharArrayBuffer);
      return;
    }
    this.abH.getDisplayName(paramCharArrayBuffer);
  }
  
  public Uri getScoreHolderHiResImageUri()
  {
    if (this.abH == null) {
      return this.abG;
    }
    return this.abH.getHiResImageUri();
  }
  
  public String getScoreHolderHiResImageUrl()
  {
    if (this.abH == null) {
      return this.abK;
    }
    return this.abH.getHiResImageUrl();
  }
  
  public Uri getScoreHolderIconImageUri()
  {
    if (this.abH == null) {
      return this.abF;
    }
    return this.abH.getIconImageUri();
  }
  
  public String getScoreHolderIconImageUrl()
  {
    if (this.abH == null) {
      return this.abJ;
    }
    return this.abH.getIconImageUrl();
  }
  
  public String getScoreTag()
  {
    return this.abI;
  }
  
  public long getTimestampMillis()
  {
    return this.abD;
  }
  
  public int hashCode()
  {
    return a(this);
  }
  
  public boolean isDataValid()
  {
    return true;
  }
  
  public LeaderboardScore lC()
  {
    return this;
  }
  
  public String toString()
  {
    return b(this);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.leaderboard.LeaderboardScoreEntity
 * JD-Core Version:    0.7.0.1
 */