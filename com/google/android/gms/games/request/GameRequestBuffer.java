package com.google.android.gms.games.request;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.g;

public final class GameRequestBuffer
  extends g<GameRequest>
{
  public GameRequestBuffer(DataHolder paramDataHolder)
  {
    super(paramDataHolder);
  }
  
  protected String gD()
  {
    return "external_request_id";
  }
  
  protected GameRequest n(int paramInt1, int paramInt2)
  {
    return new GameRequestRef(this.II, paramInt1, paramInt2);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.request.GameRequestBuffer
 * JD-Core Version:    0.7.0.1
 */