package com.google.android.gms.games.achievement;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;
import com.google.android.gms.common.internal.a;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerRef;

public final class AchievementRef
  extends d
  implements Achievement
{
  AchievementRef(DataHolder paramDataHolder, int paramInt)
  {
    super(paramDataHolder, paramInt);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public Achievement freeze()
  {
    return new AchievementEntity(this);
  }
  
  public String getAchievementId()
  {
    return getString("external_achievement_id");
  }
  
  public int getCurrentSteps()
  {
    int i = 1;
    if (getType() == i) {}
    for (;;)
    {
      a.I(i);
      return getInteger("current_steps");
      int j = 0;
    }
  }
  
  public String getDescription()
  {
    return getString("description");
  }
  
  public void getDescription(CharArrayBuffer paramCharArrayBuffer)
  {
    a("description", paramCharArrayBuffer);
  }
  
  public String getFormattedCurrentSteps()
  {
    int i = 1;
    if (getType() == i) {}
    for (;;)
    {
      a.I(i);
      return getString("formatted_current_steps");
      int j = 0;
    }
  }
  
  public void getFormattedCurrentSteps(CharArrayBuffer paramCharArrayBuffer)
  {
    int i = 1;
    if (getType() == i) {}
    for (;;)
    {
      a.I(i);
      a("formatted_current_steps", paramCharArrayBuffer);
      return;
      int j = 0;
    }
  }
  
  public String getFormattedTotalSteps()
  {
    int i = 1;
    if (getType() == i) {}
    for (;;)
    {
      a.I(i);
      return getString("formatted_total_steps");
      int j = 0;
    }
  }
  
  public void getFormattedTotalSteps(CharArrayBuffer paramCharArrayBuffer)
  {
    int i = 1;
    if (getType() == i) {}
    for (;;)
    {
      a.I(i);
      a("formatted_total_steps", paramCharArrayBuffer);
      return;
      int j = 0;
    }
  }
  
  public long getLastUpdatedTimestamp()
  {
    return getLong("last_updated_timestamp");
  }
  
  public String getName()
  {
    return getString("name");
  }
  
  public void getName(CharArrayBuffer paramCharArrayBuffer)
  {
    a("name", paramCharArrayBuffer);
  }
  
  public Player getPlayer()
  {
    return new PlayerRef(this.II, this.JX);
  }
  
  public Uri getRevealedImageUri()
  {
    return aR("revealed_icon_image_uri");
  }
  
  public String getRevealedImageUrl()
  {
    return getString("revealed_icon_image_url");
  }
  
  public int getState()
  {
    return getInteger("state");
  }
  
  public int getTotalSteps()
  {
    int i = 1;
    if (getType() == i) {}
    for (;;)
    {
      a.I(i);
      return getInteger("total_steps");
      int j = 0;
    }
  }
  
  public int getType()
  {
    return getInteger("type");
  }
  
  public Uri getUnlockedImageUri()
  {
    return aR("unlocked_icon_image_uri");
  }
  
  public String getUnlockedImageUrl()
  {
    return getString("unlocked_icon_image_url");
  }
  
  public long getXpValue()
  {
    if ((!aQ("instance_xp_value")) || (aS("instance_xp_value"))) {
      return getLong("definition_xp_value");
    }
    return getLong("instance_xp_value");
  }
  
  public String toString()
  {
    return AchievementEntity.b(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ((AchievementEntity)freeze()).writeToParcel(paramParcel, paramInt);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.achievement.AchievementRef
 * JD-Core Version:    0.7.0.1
 */