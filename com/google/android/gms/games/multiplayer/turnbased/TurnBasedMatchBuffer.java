package com.google.android.gms.games.multiplayer.turnbased;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.g;

public final class TurnBasedMatchBuffer
  extends g<TurnBasedMatch>
{
  public TurnBasedMatchBuffer(DataHolder paramDataHolder)
  {
    super(paramDataHolder);
  }
  
  protected String gD()
  {
    return "external_match_id";
  }
  
  protected TurnBasedMatch l(int paramInt1, int paramInt2)
  {
    return new TurnBasedMatchRef(this.II, paramInt1, paramInt2);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchBuffer
 * JD-Core Version:    0.7.0.1
 */