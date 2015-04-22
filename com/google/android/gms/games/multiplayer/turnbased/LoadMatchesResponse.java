package com.google.android.gms.games.multiplayer.turnbased;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.internal.constants.TurnBasedMatchTurnStatus;
import com.google.android.gms.games.multiplayer.InvitationBuffer;

public final class LoadMatchesResponse
{
  private final TurnBasedMatchBuffer acA;
  private final TurnBasedMatchBuffer acB;
  private final TurnBasedMatchBuffer acC;
  private final InvitationBuffer acz;
  
  public LoadMatchesResponse(Bundle paramBundle)
  {
    DataHolder localDataHolder1 = a(paramBundle, 0);
    label48:
    DataHolder localDataHolder3;
    if (localDataHolder1 != null)
    {
      this.acz = new InvitationBuffer(localDataHolder1);
      DataHolder localDataHolder2 = a(paramBundle, 1);
      if (localDataHolder2 == null) {
        break label107;
      }
      this.acA = new TurnBasedMatchBuffer(localDataHolder2);
      localDataHolder3 = a(paramBundle, 2);
      if (localDataHolder3 == null) {
        break label115;
      }
    }
    label107:
    label115:
    for (this.acB = new TurnBasedMatchBuffer(localDataHolder3);; this.acB = null)
    {
      DataHolder localDataHolder4 = a(paramBundle, 3);
      if (localDataHolder4 == null) {
        break label123;
      }
      this.acC = new TurnBasedMatchBuffer(localDataHolder4);
      return;
      this.acz = null;
      break;
      this.acA = null;
      break label48;
    }
    label123:
    this.acC = null;
  }
  
  private static DataHolder a(Bundle paramBundle, int paramInt)
  {
    String str = TurnBasedMatchTurnStatus.dH(paramInt);
    if (!paramBundle.containsKey(str)) {
      return null;
    }
    return (DataHolder)paramBundle.getParcelable(str);
  }
  
  public void close()
  {
    if (this.acz != null) {
      this.acz.close();
    }
    if (this.acA != null) {
      this.acA.close();
    }
    if (this.acB != null) {
      this.acB.close();
    }
    if (this.acC != null) {
      this.acC.close();
    }
  }
  
  public TurnBasedMatchBuffer getCompletedMatches()
  {
    return this.acC;
  }
  
  public InvitationBuffer getInvitations()
  {
    return this.acz;
  }
  
  public TurnBasedMatchBuffer getMyTurnMatches()
  {
    return this.acA;
  }
  
  public TurnBasedMatchBuffer getTheirTurnMatches()
  {
    return this.acB;
  }
  
  public boolean hasData()
  {
    if ((this.acz != null) && (this.acz.getCount() > 0)) {}
    while (((this.acA != null) && (this.acA.getCount() > 0)) || ((this.acB != null) && (this.acB.getCount() > 0)) || ((this.acC != null) && (this.acC.getCount() > 0))) {
      return true;
    }
    return false;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.multiplayer.turnbased.LoadMatchesResponse
 * JD-Core Version:    0.7.0.1
 */