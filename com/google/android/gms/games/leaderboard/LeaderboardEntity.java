package com.google.android.gms.games.leaderboard;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.internal.jv;
import java.util.ArrayList;

public final class LeaderboardEntity
  implements Leaderboard
{
  private final String NH;
  private final Uri Vh;
  private final String Vs;
  private final String abu;
  private final int abv;
  private final ArrayList<LeaderboardVariantEntity> abw;
  private final Game abx;
  
  public LeaderboardEntity(Leaderboard paramLeaderboard)
  {
    this.abu = paramLeaderboard.getLeaderboardId();
    this.NH = paramLeaderboard.getDisplayName();
    this.Vh = paramLeaderboard.getIconImageUri();
    this.Vs = paramLeaderboard.getIconImageUrl();
    this.abv = paramLeaderboard.getScoreOrder();
    Game localGame = paramLeaderboard.getGame();
    if (localGame == null) {}
    for (GameEntity localGameEntity = null;; localGameEntity = new GameEntity(localGame))
    {
      this.abx = localGameEntity;
      ArrayList localArrayList = paramLeaderboard.getVariants();
      int i = localArrayList.size();
      this.abw = new ArrayList(i);
      for (int j = 0; j < i; j++) {
        this.abw.add((LeaderboardVariantEntity)((LeaderboardVariant)localArrayList.get(j)).freeze());
      }
    }
  }
  
  static int a(Leaderboard paramLeaderboard)
  {
    Object[] arrayOfObject = new Object[5];
    arrayOfObject[0] = paramLeaderboard.getLeaderboardId();
    arrayOfObject[1] = paramLeaderboard.getDisplayName();
    arrayOfObject[2] = paramLeaderboard.getIconImageUri();
    arrayOfObject[3] = Integer.valueOf(paramLeaderboard.getScoreOrder());
    arrayOfObject[4] = paramLeaderboard.getVariants();
    return n.hashCode(arrayOfObject);
  }
  
  static boolean a(Leaderboard paramLeaderboard, Object paramObject)
  {
    boolean bool = true;
    if (!(paramObject instanceof Leaderboard)) {
      bool = false;
    }
    Leaderboard localLeaderboard;
    do
    {
      do
      {
        return bool;
      } while (paramLeaderboard == paramObject);
      localLeaderboard = (Leaderboard)paramObject;
    } while ((n.equal(localLeaderboard.getLeaderboardId(), paramLeaderboard.getLeaderboardId())) && (n.equal(localLeaderboard.getDisplayName(), paramLeaderboard.getDisplayName())) && (n.equal(localLeaderboard.getIconImageUri(), paramLeaderboard.getIconImageUri())) && (n.equal(Integer.valueOf(localLeaderboard.getScoreOrder()), Integer.valueOf(paramLeaderboard.getScoreOrder()))) && (n.equal(localLeaderboard.getVariants(), paramLeaderboard.getVariants())));
    return false;
  }
  
  static String b(Leaderboard paramLeaderboard)
  {
    return n.h(paramLeaderboard).a("LeaderboardId", paramLeaderboard.getLeaderboardId()).a("DisplayName", paramLeaderboard.getDisplayName()).a("IconImageUri", paramLeaderboard.getIconImageUri()).a("IconImageUrl", paramLeaderboard.getIconImageUrl()).a("ScoreOrder", Integer.valueOf(paramLeaderboard.getScoreOrder())).a("Variants", paramLeaderboard.getVariants()).toString();
  }
  
  public boolean equals(Object paramObject)
  {
    return a(this, paramObject);
  }
  
  public String getDisplayName()
  {
    return this.NH;
  }
  
  public void getDisplayName(CharArrayBuffer paramCharArrayBuffer)
  {
    jv.b(this.NH, paramCharArrayBuffer);
  }
  
  public Game getGame()
  {
    return this.abx;
  }
  
  public Uri getIconImageUri()
  {
    return this.Vh;
  }
  
  public String getIconImageUrl()
  {
    return this.Vs;
  }
  
  public String getLeaderboardId()
  {
    return this.abu;
  }
  
  public int getScoreOrder()
  {
    return this.abv;
  }
  
  public ArrayList<LeaderboardVariant> getVariants()
  {
    return new ArrayList(this.abw);
  }
  
  public int hashCode()
  {
    return a(this);
  }
  
  public boolean isDataValid()
  {
    return true;
  }
  
  public Leaderboard lz()
  {
    return this;
  }
  
  public String toString()
  {
    return b(this);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.leaderboard.LeaderboardEntity
 * JD-Core Version:    0.7.0.1
 */