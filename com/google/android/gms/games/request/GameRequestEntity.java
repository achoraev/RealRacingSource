package com.google.android.gms.games.request;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class GameRequestEntity
  implements SafeParcelable, GameRequest
{
  public static final GameRequestEntityCreator CREATOR = new GameRequestEntityCreator();
  private final int BR;
  private final int FD;
  private final int Fa;
  private final String XC;
  private final GameEntity aay;
  private final long abZ;
  private final byte[] acH;
  private final PlayerEntity adc;
  private final ArrayList<PlayerEntity> add;
  private final long ade;
  private final Bundle adf;
  
  GameRequestEntity(int paramInt1, GameEntity paramGameEntity, PlayerEntity paramPlayerEntity, byte[] paramArrayOfByte, String paramString, ArrayList<PlayerEntity> paramArrayList, int paramInt2, long paramLong1, long paramLong2, Bundle paramBundle, int paramInt3)
  {
    this.BR = paramInt1;
    this.aay = paramGameEntity;
    this.adc = paramPlayerEntity;
    this.acH = paramArrayOfByte;
    this.XC = paramString;
    this.add = paramArrayList;
    this.FD = paramInt2;
    this.abZ = paramLong1;
    this.ade = paramLong2;
    this.adf = paramBundle;
    this.Fa = paramInt3;
  }
  
  public GameRequestEntity(GameRequest paramGameRequest)
  {
    this.BR = 2;
    this.aay = new GameEntity(paramGameRequest.getGame());
    this.adc = new PlayerEntity(paramGameRequest.getSender());
    this.XC = paramGameRequest.getRequestId();
    this.FD = paramGameRequest.getType();
    this.abZ = paramGameRequest.getCreationTimestamp();
    this.ade = paramGameRequest.getExpirationTimestamp();
    this.Fa = paramGameRequest.getStatus();
    byte[] arrayOfByte = paramGameRequest.getData();
    if (arrayOfByte == null) {
      this.acH = null;
    }
    for (;;)
    {
      List localList = paramGameRequest.getRecipients();
      int i = localList.size();
      this.add = new ArrayList(i);
      this.adf = new Bundle();
      for (int j = 0; j < i; j++)
      {
        Player localPlayer = (Player)((Player)localList.get(j)).freeze();
        String str = localPlayer.getPlayerId();
        this.add.add((PlayerEntity)localPlayer);
        this.adf.putInt(str, paramGameRequest.getRecipientStatus(str));
      }
      this.acH = new byte[arrayOfByte.length];
      System.arraycopy(arrayOfByte, 0, this.acH, 0, arrayOfByte.length);
    }
  }
  
  static int a(GameRequest paramGameRequest)
  {
    Object[] arrayOfObject = new Object[8];
    arrayOfObject[0] = paramGameRequest.getGame();
    arrayOfObject[1] = paramGameRequest.getRecipients();
    arrayOfObject[2] = paramGameRequest.getRequestId();
    arrayOfObject[3] = paramGameRequest.getSender();
    arrayOfObject[4] = b(paramGameRequest);
    arrayOfObject[5] = Integer.valueOf(paramGameRequest.getType());
    arrayOfObject[6] = Long.valueOf(paramGameRequest.getCreationTimestamp());
    arrayOfObject[7] = Long.valueOf(paramGameRequest.getExpirationTimestamp());
    return n.hashCode(arrayOfObject);
  }
  
  static boolean a(GameRequest paramGameRequest, Object paramObject)
  {
    boolean bool = true;
    if (!(paramObject instanceof GameRequest)) {
      bool = false;
    }
    GameRequest localGameRequest;
    do
    {
      do
      {
        return bool;
      } while (paramGameRequest == paramObject);
      localGameRequest = (GameRequest)paramObject;
    } while ((n.equal(localGameRequest.getGame(), paramGameRequest.getGame())) && (n.equal(localGameRequest.getRecipients(), paramGameRequest.getRecipients())) && (n.equal(localGameRequest.getRequestId(), paramGameRequest.getRequestId())) && (n.equal(localGameRequest.getSender(), paramGameRequest.getSender())) && (Arrays.equals(b(localGameRequest), b(paramGameRequest))) && (n.equal(Integer.valueOf(localGameRequest.getType()), Integer.valueOf(paramGameRequest.getType()))) && (n.equal(Long.valueOf(localGameRequest.getCreationTimestamp()), Long.valueOf(paramGameRequest.getCreationTimestamp()))) && (n.equal(Long.valueOf(localGameRequest.getExpirationTimestamp()), Long.valueOf(paramGameRequest.getExpirationTimestamp()))));
    return false;
  }
  
  private static int[] b(GameRequest paramGameRequest)
  {
    List localList = paramGameRequest.getRecipients();
    int i = localList.size();
    int[] arrayOfInt = new int[i];
    for (int j = 0; j < i; j++) {
      arrayOfInt[j] = paramGameRequest.getRecipientStatus(((Player)localList.get(j)).getPlayerId());
    }
    return arrayOfInt;
  }
  
  static String c(GameRequest paramGameRequest)
  {
    return n.h(paramGameRequest).a("Game", paramGameRequest.getGame()).a("Sender", paramGameRequest.getSender()).a("Recipients", paramGameRequest.getRecipients()).a("Data", paramGameRequest.getData()).a("RequestId", paramGameRequest.getRequestId()).a("Type", Integer.valueOf(paramGameRequest.getType())).a("CreationTimestamp", Long.valueOf(paramGameRequest.getCreationTimestamp())).a("ExpirationTimestamp", Long.valueOf(paramGameRequest.getExpirationTimestamp())).toString();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return a(this, paramObject);
  }
  
  public GameRequest freeze()
  {
    return this;
  }
  
  public long getCreationTimestamp()
  {
    return this.abZ;
  }
  
  public byte[] getData()
  {
    return this.acH;
  }
  
  public long getExpirationTimestamp()
  {
    return this.ade;
  }
  
  public Game getGame()
  {
    return this.aay;
  }
  
  public int getRecipientStatus(String paramString)
  {
    return this.adf.getInt(paramString, 0);
  }
  
  public List<Player> getRecipients()
  {
    return new ArrayList(this.add);
  }
  
  public String getRequestId()
  {
    return this.XC;
  }
  
  public Player getSender()
  {
    return this.adc;
  }
  
  public int getStatus()
  {
    return this.Fa;
  }
  
  public int getType()
  {
    return this.FD;
  }
  
  public int getVersionCode()
  {
    return this.BR;
  }
  
  public int hashCode()
  {
    return a(this);
  }
  
  public boolean isConsumed(String paramString)
  {
    return getRecipientStatus(paramString) == 1;
  }
  
  public boolean isDataValid()
  {
    return true;
  }
  
  public Bundle lL()
  {
    return this.adf;
  }
  
  public String toString()
  {
    return c(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    GameRequestEntityCreator.a(this, paramParcel, paramInt);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.request.GameRequestEntity
 * JD-Core Version:    0.7.0.1
 */