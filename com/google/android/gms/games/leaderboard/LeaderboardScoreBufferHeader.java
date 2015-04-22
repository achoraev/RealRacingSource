package com.google.android.gms.games.leaderboard;

import android.os.Bundle;

public final class LeaderboardScoreBufferHeader
{
  private final Bundle Nh;
  
  public LeaderboardScoreBufferHeader(Bundle paramBundle)
  {
    if (paramBundle == null) {
      paramBundle = new Bundle();
    }
    this.Nh = paramBundle;
  }
  
  public Bundle lB()
  {
    return this.Nh;
  }
  
  public static final class Builder {}
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.leaderboard.LeaderboardScoreBufferHeader
 * JD-Core Version:    0.7.0.1
 */