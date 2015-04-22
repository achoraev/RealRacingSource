package com.google.android.gms.games.event;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.internal.jv;

public final class EventEntity
  implements SafeParcelable, Event
{
  public static final EventEntityCreator CREATOR = new EventEntityCreator();
  private final int BR;
  private final String Tr;
  private final Uri Vh;
  private final String Vs;
  private final PlayerEntity Wh;
  private final String Wm;
  private final long Wn;
  private final String Wo;
  private final boolean Wp;
  private final String mName;
  
  EventEntity(int paramInt, String paramString1, String paramString2, String paramString3, Uri paramUri, String paramString4, Player paramPlayer, long paramLong, String paramString5, boolean paramBoolean)
  {
    this.BR = paramInt;
    this.Wm = paramString1;
    this.mName = paramString2;
    this.Tr = paramString3;
    this.Vh = paramUri;
    this.Vs = paramString4;
    this.Wh = new PlayerEntity(paramPlayer);
    this.Wn = paramLong;
    this.Wo = paramString5;
    this.Wp = paramBoolean;
  }
  
  public EventEntity(Event paramEvent)
  {
    this.BR = 1;
    this.Wm = paramEvent.getEventId();
    this.mName = paramEvent.getName();
    this.Tr = paramEvent.getDescription();
    this.Vh = paramEvent.getIconImageUri();
    this.Vs = paramEvent.getIconImageUrl();
    this.Wh = ((PlayerEntity)paramEvent.getPlayer().freeze());
    this.Wn = paramEvent.getValue();
    this.Wo = paramEvent.getFormattedValue();
    this.Wp = paramEvent.isVisible();
  }
  
  static int a(Event paramEvent)
  {
    Object[] arrayOfObject = new Object[9];
    arrayOfObject[0] = paramEvent.getEventId();
    arrayOfObject[1] = paramEvent.getName();
    arrayOfObject[2] = paramEvent.getDescription();
    arrayOfObject[3] = paramEvent.getIconImageUri();
    arrayOfObject[4] = paramEvent.getIconImageUrl();
    arrayOfObject[5] = paramEvent.getPlayer();
    arrayOfObject[6] = Long.valueOf(paramEvent.getValue());
    arrayOfObject[7] = paramEvent.getFormattedValue();
    arrayOfObject[8] = Boolean.valueOf(paramEvent.isVisible());
    return n.hashCode(arrayOfObject);
  }
  
  static boolean a(Event paramEvent, Object paramObject)
  {
    boolean bool = true;
    if (!(paramObject instanceof Event)) {
      bool = false;
    }
    Event localEvent;
    do
    {
      do
      {
        return bool;
      } while (paramEvent == paramObject);
      localEvent = (Event)paramObject;
    } while ((n.equal(localEvent.getEventId(), paramEvent.getEventId())) && (n.equal(localEvent.getName(), paramEvent.getName())) && (n.equal(localEvent.getDescription(), paramEvent.getDescription())) && (n.equal(localEvent.getIconImageUri(), paramEvent.getIconImageUri())) && (n.equal(localEvent.getIconImageUrl(), paramEvent.getIconImageUrl())) && (n.equal(localEvent.getPlayer(), paramEvent.getPlayer())) && (n.equal(Long.valueOf(localEvent.getValue()), Long.valueOf(paramEvent.getValue()))) && (n.equal(localEvent.getFormattedValue(), paramEvent.getFormattedValue())) && (n.equal(Boolean.valueOf(localEvent.isVisible()), Boolean.valueOf(paramEvent.isVisible()))));
    return false;
  }
  
  static String b(Event paramEvent)
  {
    return n.h(paramEvent).a("Id", paramEvent.getEventId()).a("Name", paramEvent.getName()).a("Description", paramEvent.getDescription()).a("IconImageUri", paramEvent.getIconImageUri()).a("IconImageUrl", paramEvent.getIconImageUrl()).a("Player", paramEvent.getPlayer()).a("Value", Long.valueOf(paramEvent.getValue())).a("FormattedValue", paramEvent.getFormattedValue()).a("isVisible", Boolean.valueOf(paramEvent.isVisible())).toString();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return a(this, paramObject);
  }
  
  public Event freeze()
  {
    return this;
  }
  
  public String getDescription()
  {
    return this.Tr;
  }
  
  public void getDescription(CharArrayBuffer paramCharArrayBuffer)
  {
    jv.b(this.Tr, paramCharArrayBuffer);
  }
  
  public String getEventId()
  {
    return this.Wm;
  }
  
  public String getFormattedValue()
  {
    return this.Wo;
  }
  
  public void getFormattedValue(CharArrayBuffer paramCharArrayBuffer)
  {
    jv.b(this.Wo, paramCharArrayBuffer);
  }
  
  public Uri getIconImageUri()
  {
    return this.Vh;
  }
  
  public String getIconImageUrl()
  {
    return this.Vs;
  }
  
  public String getName()
  {
    return this.mName;
  }
  
  public void getName(CharArrayBuffer paramCharArrayBuffer)
  {
    jv.b(this.mName, paramCharArrayBuffer);
  }
  
  public Player getPlayer()
  {
    return this.Wh;
  }
  
  public long getValue()
  {
    return this.Wn;
  }
  
  public int getVersionCode()
  {
    return this.BR;
  }
  
  public int hashCode()
  {
    return a(this);
  }
  
  public boolean isDataValid()
  {
    return true;
  }
  
  public boolean isVisible()
  {
    return this.Wp;
  }
  
  public String toString()
  {
    return b(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    EventEntityCreator.a(this, paramParcel, paramInt);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.event.EventEntity
 * JD-Core Version:    0.7.0.1
 */