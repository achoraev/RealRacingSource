package com.google.android.gms.games.internal.request;

import android.os.Parcel;
import com.google.android.gms.common.internal.a;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.request.GameRequest;
import com.google.android.gms.games.request.GameRequestEntity;
import java.util.ArrayList;

public final class GameRequestCluster
  implements SafeParcelable, GameRequest
{
  public static final GameRequestClusterCreator CREATOR = new GameRequestClusterCreator();
  private final int BR;
  private final ArrayList<GameRequestEntity> abr;
  
  GameRequestCluster(int paramInt, ArrayList<GameRequestEntity> paramArrayList)
  {
    this.BR = paramInt;
    this.abr = paramArrayList;
    li();
  }
  
  private void li()
  {
    boolean bool1;
    GameRequest localGameRequest1;
    int j;
    label39:
    GameRequest localGameRequest2;
    if (!this.abr.isEmpty())
    {
      bool1 = true;
      a.I(bool1);
      localGameRequest1 = (GameRequest)this.abr.get(0);
      int i = this.abr.size();
      j = 1;
      if (j >= i) {
        return;
      }
      localGameRequest2 = (GameRequest)this.abr.get(j);
      if (localGameRequest1.getType() != localGameRequest2.getType()) {
        break label117;
      }
    }
    label117:
    for (boolean bool2 = true;; bool2 = false)
    {
      a.a(bool2, "All the requests must be of the same type");
      a.a(localGameRequest1.getSender().equals(localGameRequest2.getSender()), "All the requests must be from the same sender");
      j++;
      break label39;
      bool1 = false;
      break;
    }
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof GameRequestCluster)) {
      return false;
    }
    if (this == paramObject) {
      return true;
    }
    GameRequestCluster localGameRequestCluster = (GameRequestCluster)paramObject;
    if (localGameRequestCluster.abr.size() != this.abr.size()) {
      return false;
    }
    int i = this.abr.size();
    for (int j = 0; j < i; j++) {
      if (!((GameRequest)this.abr.get(j)).equals((GameRequest)localGameRequestCluster.abr.get(j))) {
        return false;
      }
    }
    return true;
  }
  
  public GameRequest freeze()
  {
    return this;
  }
  
  public long getCreationTimestamp()
  {
    throw new UnsupportedOperationException("Method not supported on a cluster");
  }
  
  public byte[] getData()
  {
    throw new UnsupportedOperationException("Method not supported on a cluster");
  }
  
  public long getExpirationTimestamp()
  {
    throw new UnsupportedOperationException("Method not supported on a cluster");
  }
  
  public Game getGame()
  {
    throw new UnsupportedOperationException("Method not supported on a cluster");
  }
  
  public int getRecipientStatus(String paramString)
  {
    throw new UnsupportedOperationException("Method not supported on a cluster");
  }
  
  public String getRequestId()
  {
    return ((GameRequestEntity)this.abr.get(0)).getRequestId();
  }
  
  public Player getSender()
  {
    return ((GameRequestEntity)this.abr.get(0)).getSender();
  }
  
  public int getStatus()
  {
    throw new UnsupportedOperationException("Method not supported on a cluster");
  }
  
  public int getType()
  {
    return ((GameRequestEntity)this.abr.get(0)).getType();
  }
  
  public int getVersionCode()
  {
    return this.BR;
  }
  
  public int hashCode()
  {
    return n.hashCode(this.abr.toArray());
  }
  
  public boolean isConsumed(String paramString)
  {
    throw new UnsupportedOperationException("Method not supported on a cluster");
  }
  
  public boolean isDataValid()
  {
    return true;
  }
  
  public ArrayList<GameRequest> lw()
  {
    return new ArrayList(this.abr);
  }
  
  public ArrayList<Player> lx()
  {
    throw new UnsupportedOperationException("Method not supported on a cluster");
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    GameRequestClusterCreator.a(this, paramParcel, paramInt);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.internal.request.GameRequestCluster
 * JD-Core Version:    0.7.0.1
 */