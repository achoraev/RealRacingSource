package com.google.android.gms.games.quest;

import android.os.Parcel;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class MilestoneEntity
  implements SafeParcelable, Milestone
{
  public static final MilestoneEntityCreator CREATOR = new MilestoneEntityCreator();
  private final int BR;
  private final String Wm;
  private final String Xu;
  private final long acO;
  private final long acP;
  private final byte[] acQ;
  private final int mState;
  
  MilestoneEntity(int paramInt1, String paramString1, long paramLong1, long paramLong2, byte[] paramArrayOfByte, int paramInt2, String paramString2)
  {
    this.BR = paramInt1;
    this.Xu = paramString1;
    this.acO = paramLong1;
    this.acP = paramLong2;
    this.acQ = paramArrayOfByte;
    this.mState = paramInt2;
    this.Wm = paramString2;
  }
  
  public MilestoneEntity(Milestone paramMilestone)
  {
    this.BR = 4;
    this.Xu = paramMilestone.getMilestoneId();
    this.acO = paramMilestone.getCurrentProgress();
    this.acP = paramMilestone.getTargetProgress();
    this.mState = paramMilestone.getState();
    this.Wm = paramMilestone.getEventId();
    byte[] arrayOfByte = paramMilestone.getCompletionRewardData();
    if (arrayOfByte == null)
    {
      this.acQ = null;
      return;
    }
    this.acQ = new byte[arrayOfByte.length];
    System.arraycopy(arrayOfByte, 0, this.acQ, 0, arrayOfByte.length);
  }
  
  static int a(Milestone paramMilestone)
  {
    Object[] arrayOfObject = new Object[5];
    arrayOfObject[0] = paramMilestone.getMilestoneId();
    arrayOfObject[1] = Long.valueOf(paramMilestone.getCurrentProgress());
    arrayOfObject[2] = Long.valueOf(paramMilestone.getTargetProgress());
    arrayOfObject[3] = Integer.valueOf(paramMilestone.getState());
    arrayOfObject[4] = paramMilestone.getEventId();
    return n.hashCode(arrayOfObject);
  }
  
  static boolean a(Milestone paramMilestone, Object paramObject)
  {
    boolean bool = true;
    if (!(paramObject instanceof Milestone)) {
      bool = false;
    }
    Milestone localMilestone;
    do
    {
      do
      {
        return bool;
      } while (paramMilestone == paramObject);
      localMilestone = (Milestone)paramObject;
    } while ((n.equal(localMilestone.getMilestoneId(), paramMilestone.getMilestoneId())) && (n.equal(Long.valueOf(localMilestone.getCurrentProgress()), Long.valueOf(paramMilestone.getCurrentProgress()))) && (n.equal(Long.valueOf(localMilestone.getTargetProgress()), Long.valueOf(paramMilestone.getTargetProgress()))) && (n.equal(Integer.valueOf(localMilestone.getState()), Integer.valueOf(paramMilestone.getState()))) && (n.equal(localMilestone.getEventId(), paramMilestone.getEventId())));
    return false;
  }
  
  static String b(Milestone paramMilestone)
  {
    return n.h(paramMilestone).a("MilestoneId", paramMilestone.getMilestoneId()).a("CurrentProgress", Long.valueOf(paramMilestone.getCurrentProgress())).a("TargetProgress", Long.valueOf(paramMilestone.getTargetProgress())).a("State", Integer.valueOf(paramMilestone.getState())).a("CompletionRewardData", paramMilestone.getCompletionRewardData()).a("EventId", paramMilestone.getEventId()).toString();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return a(this, paramObject);
  }
  
  public Milestone freeze()
  {
    return this;
  }
  
  public byte[] getCompletionRewardData()
  {
    return this.acQ;
  }
  
  public long getCurrentProgress()
  {
    return this.acO;
  }
  
  public String getEventId()
  {
    return this.Wm;
  }
  
  public String getMilestoneId()
  {
    return this.Xu;
  }
  
  public int getState()
  {
    return this.mState;
  }
  
  public long getTargetProgress()
  {
    return this.acP;
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
  
  public String toString()
  {
    return b(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    MilestoneEntityCreator.a(this, paramParcel, paramInt);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.quest.MilestoneEntity
 * JD-Core Version:    0.7.0.1
 */