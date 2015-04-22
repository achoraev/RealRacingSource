package com.google.android.gms.games.internal.game;

import android.os.Parcelable;
import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.snapshot.SnapshotMetadata;
import java.util.ArrayList;

public abstract interface ExtendedGame
  extends Parcelable, Freezable<ExtendedGame>
{
  public abstract Game getGame();
  
  public abstract ArrayList<GameBadge> kR();
  
  public abstract int kS();
  
  public abstract boolean kT();
  
  public abstract int kU();
  
  public abstract long kV();
  
  public abstract long kW();
  
  public abstract String kX();
  
  public abstract long kY();
  
  public abstract String kZ();
  
  public abstract SnapshotMetadata la();
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.internal.game.ExtendedGame
 * JD-Core Version:    0.7.0.1
 */