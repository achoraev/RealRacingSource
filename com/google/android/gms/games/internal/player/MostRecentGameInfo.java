package com.google.android.gms.games.internal.player;

import android.net.Uri;
import android.os.Parcelable;
import com.google.android.gms.common.data.Freezable;

public abstract interface MostRecentGameInfo
  extends Parcelable, Freezable<MostRecentGameInfo>
{
  public abstract String lp();
  
  public abstract String lq();
  
  public abstract long lr();
  
  public abstract Uri ls();
  
  public abstract Uri lt();
  
  public abstract Uri lu();
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.internal.player.MostRecentGameInfo
 * JD-Core Version:    0.7.0.1
 */