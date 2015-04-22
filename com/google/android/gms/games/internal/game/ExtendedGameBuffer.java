package com.google.android.gms.games.internal.game;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.g;

public final class ExtendedGameBuffer
  extends g<ExtendedGame>
{
  public ExtendedGameBuffer(DataHolder paramDataHolder)
  {
    super(paramDataHolder);
  }
  
  protected String gD()
  {
    return "external_game_id";
  }
  
  protected ExtendedGame h(int paramInt1, int paramInt2)
  {
    return new ExtendedGameRef(this.II, paramInt1, paramInt2);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.internal.game.ExtendedGameBuffer
 * JD-Core Version:    0.7.0.1
 */