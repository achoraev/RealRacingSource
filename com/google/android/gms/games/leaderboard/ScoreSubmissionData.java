package com.google.android.gms.games.leaderboard;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.games.internal.constants.TimeSpan;
import java.util.HashMap;

public final class ScoreSubmissionData
{
  private static final String[] abs = { "leaderboardId", "playerId", "timeSpan", "hasResult", "rawScore", "formattedScore", "newBest", "scoreTag" };
  private int HF;
  private String VK;
  private HashMap<Integer, Result> abY;
  private String abu;
  
  public ScoreSubmissionData(DataHolder paramDataHolder)
  {
    this.HF = paramDataHolder.getStatusCode();
    this.abY = new HashMap();
    int i = paramDataHolder.getCount();
    if (i == 3) {}
    for (boolean bool = true;; bool = false)
    {
      o.K(bool);
      for (int j = 0; j < i; j++)
      {
        int k = paramDataHolder.ar(j);
        if (j == 0)
        {
          this.abu = paramDataHolder.c("leaderboardId", j, k);
          this.VK = paramDataHolder.c("playerId", j, k);
        }
        if (paramDataHolder.d("hasResult", j, k)) {
          a(new Result(paramDataHolder.a("rawScore", j, k), paramDataHolder.c("formattedScore", j, k), paramDataHolder.c("scoreTag", j, k), paramDataHolder.d("newBest", j, k)), paramDataHolder.b("timeSpan", j, k));
        }
      }
    }
  }
  
  private void a(Result paramResult, int paramInt)
  {
    this.abY.put(Integer.valueOf(paramInt), paramResult);
  }
  
  public String getLeaderboardId()
  {
    return this.abu;
  }
  
  public String getPlayerId()
  {
    return this.VK;
  }
  
  public Result getScoreResult(int paramInt)
  {
    return (Result)this.abY.get(Integer.valueOf(paramInt));
  }
  
  public String toString()
  {
    n.a locala = n.h(this).a("PlayerId", this.VK).a("StatusCode", Integer.valueOf(this.HF));
    int i = 0;
    if (i < 3)
    {
      Result localResult = (Result)this.abY.get(Integer.valueOf(i));
      locala.a("TimesSpan", TimeSpan.dH(i));
      if (localResult == null) {}
      for (String str = "null";; str = localResult.toString())
      {
        locala.a("Result", str);
        i++;
        break;
      }
    }
    return locala.toString();
  }
  
  public static final class Result
  {
    public final String formattedScore;
    public final boolean newBest;
    public final long rawScore;
    public final String scoreTag;
    
    public Result(long paramLong, String paramString1, String paramString2, boolean paramBoolean)
    {
      this.rawScore = paramLong;
      this.formattedScore = paramString1;
      this.scoreTag = paramString2;
      this.newBest = paramBoolean;
    }
    
    public String toString()
    {
      return n.h(this).a("RawScore", Long.valueOf(this.rawScore)).a("FormattedScore", this.formattedScore).a("ScoreTag", this.scoreTag).a("NewBest", Boolean.valueOf(this.newBest)).toString();
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.leaderboard.ScoreSubmissionData
 * JD-Core Version:    0.7.0.1
 */