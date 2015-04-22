package com.google.android.gms.games.internal.multiplayer;

import android.os.Parcel;
import com.google.android.gms.common.internal.a;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.multiplayer.Invitation;
import com.google.android.gms.games.multiplayer.InvitationEntity;
import com.google.android.gms.games.multiplayer.Participant;
import java.util.ArrayList;

public final class ZInvitationCluster
  implements SafeParcelable, Invitation
{
  public static final InvitationClusterCreator CREATOR = new InvitationClusterCreator();
  private final int BR;
  private final ArrayList<InvitationEntity> aaL;
  
  ZInvitationCluster(int paramInt, ArrayList<InvitationEntity> paramArrayList)
  {
    this.BR = paramInt;
    this.aaL = paramArrayList;
    li();
  }
  
  private void li()
  {
    if (!this.aaL.isEmpty()) {}
    for (boolean bool = true;; bool = false)
    {
      a.I(bool);
      Invitation localInvitation1 = (Invitation)this.aaL.get(0);
      int i = this.aaL.size();
      for (int j = 1; j < i; j++)
      {
        Invitation localInvitation2 = (Invitation)this.aaL.get(j);
        a.a(localInvitation1.getInviter().equals(localInvitation2.getInviter()), "All the invitations must be from the same inviter");
      }
    }
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof ZInvitationCluster)) {
      return false;
    }
    if (this == paramObject) {
      return true;
    }
    ZInvitationCluster localZInvitationCluster = (ZInvitationCluster)paramObject;
    if (localZInvitationCluster.aaL.size() != this.aaL.size()) {
      return false;
    }
    int i = this.aaL.size();
    for (int j = 0; j < i; j++) {
      if (!((Invitation)this.aaL.get(j)).equals((Invitation)localZInvitationCluster.aaL.get(j))) {
        return false;
      }
    }
    return true;
  }
  
  public Invitation freeze()
  {
    return this;
  }
  
  public int getAvailableAutoMatchSlots()
  {
    throw new UnsupportedOperationException("Method not supported on a cluster");
  }
  
  public long getCreationTimestamp()
  {
    throw new UnsupportedOperationException("Method not supported on a cluster");
  }
  
  public Game getGame()
  {
    throw new UnsupportedOperationException("Method not supported on a cluster");
  }
  
  public String getInvitationId()
  {
    return ((InvitationEntity)this.aaL.get(0)).getInvitationId();
  }
  
  public int getInvitationType()
  {
    throw new UnsupportedOperationException("Method not supported on a cluster");
  }
  
  public Participant getInviter()
  {
    return ((InvitationEntity)this.aaL.get(0)).getInviter();
  }
  
  public ArrayList<Participant> getParticipants()
  {
    throw new UnsupportedOperationException("Method not supported on a cluster");
  }
  
  public int getVariant()
  {
    throw new UnsupportedOperationException("Method not supported on a cluster");
  }
  
  public int getVersionCode()
  {
    return this.BR;
  }
  
  public int hashCode()
  {
    return n.hashCode(this.aaL.toArray());
  }
  
  public boolean isDataValid()
  {
    return true;
  }
  
  public ArrayList<Invitation> lj()
  {
    return new ArrayList(this.aaL);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    InvitationClusterCreator.a(this, paramParcel, paramInt);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.internal.multiplayer.ZInvitationCluster
 * JD-Core Version:    0.7.0.1
 */