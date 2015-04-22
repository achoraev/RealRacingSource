package com.fiksu.asotracking;

import android.content.Context;

class RatingEventTracker
  extends EventTracker
{
  RatingEventTracker(Context paramContext, String paramString, int paramInt)
  {
    super(paramContext, FiksuEventType.RATING.getName());
    addParameter(FiksuEventParameter.TVALUE, paramString);
    addParameter(FiksuEventParameter.IVALUE, Integer.toString(paramInt));
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.fiksu.asotracking.RatingEventTracker
 * JD-Core Version:    0.7.0.1
 */