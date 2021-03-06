package com.google.android.gms.location;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ActivityRecognitionResult
  implements SafeParcelable
{
  public static final ActivityRecognitionResultCreator CREATOR = new ActivityRecognitionResultCreator();
  public static final String EXTRA_ACTIVITY_RESULT = "com.google.android.location.internal.EXTRA_ACTIVITY_RESULT";
  private final int BR;
  List<DetectedActivity> aeb;
  long aec;
  long aed;
  
  public ActivityRecognitionResult(int paramInt, List<DetectedActivity> paramList, long paramLong1, long paramLong2)
  {
    this.BR = 1;
    this.aeb = paramList;
    this.aec = paramLong1;
    this.aed = paramLong2;
  }
  
  public ActivityRecognitionResult(DetectedActivity paramDetectedActivity, long paramLong1, long paramLong2)
  {
    this(Collections.singletonList(paramDetectedActivity), paramLong1, paramLong2);
  }
  
  public ActivityRecognitionResult(List<DetectedActivity> paramList, long paramLong1, long paramLong2)
  {
    if ((paramList != null) && (paramList.size() > 0)) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      o.b(bool1, "Must have at least 1 detected activity");
      boolean bool2 = paramLong1 < 0L;
      boolean bool3 = false;
      if (bool2)
      {
        boolean bool4 = paramLong2 < 0L;
        bool3 = false;
        if (bool4) {
          bool3 = true;
        }
      }
      o.b(bool3, "Must set times");
      this.BR = 1;
      this.aeb = paramList;
      this.aec = paramLong1;
      this.aed = paramLong2;
      return;
    }
  }
  
  public static ActivityRecognitionResult extractResult(Intent paramIntent)
  {
    if (!hasResult(paramIntent)) {
      return null;
    }
    Object localObject = paramIntent.getExtras().get("com.google.android.location.internal.EXTRA_ACTIVITY_RESULT");
    if ((localObject instanceof byte[]))
    {
      Parcel localParcel = Parcel.obtain();
      localParcel.unmarshall((byte[])localObject, 0, ((byte[])localObject).length);
      localParcel.setDataPosition(0);
      return CREATOR.createFromParcel(localParcel);
    }
    if ((localObject instanceof ActivityRecognitionResult)) {
      return (ActivityRecognitionResult)localObject;
    }
    return null;
  }
  
  public static boolean hasResult(Intent paramIntent)
  {
    if (paramIntent == null) {
      return false;
    }
    return paramIntent.hasExtra("com.google.android.location.internal.EXTRA_ACTIVITY_RESULT");
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public int getActivityConfidence(int paramInt)
  {
    Iterator localIterator = this.aeb.iterator();
    while (localIterator.hasNext())
    {
      DetectedActivity localDetectedActivity = (DetectedActivity)localIterator.next();
      if (localDetectedActivity.getType() == paramInt) {
        return localDetectedActivity.getConfidence();
      }
    }
    return 0;
  }
  
  public long getElapsedRealtimeMillis()
  {
    return this.aed;
  }
  
  public DetectedActivity getMostProbableActivity()
  {
    return (DetectedActivity)this.aeb.get(0);
  }
  
  public List<DetectedActivity> getProbableActivities()
  {
    return this.aeb;
  }
  
  public long getTime()
  {
    return this.aec;
  }
  
  public int getVersionCode()
  {
    return this.BR;
  }
  
  public String toString()
  {
    return "ActivityRecognitionResult [probableActivities=" + this.aeb + ", timeMillis=" + this.aec + ", elapsedRealtimeMillis=" + this.aed + "]";
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ActivityRecognitionResultCreator.a(this, paramParcel, paramInt);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.location.ActivityRecognitionResult
 * JD-Core Version:    0.7.0.1
 */