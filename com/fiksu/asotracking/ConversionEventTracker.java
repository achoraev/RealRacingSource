package com.fiksu.asotracking;

import android.content.Context;

final class ConversionEventTracker
  extends EventTracker
{
  ConversionEventTracker(Context paramContext, String paramString)
  {
    super(paramContext, FiksuEventType.CONVERSION.getName());
    addParameter(FiksuEventParameter.TVALUE, paramString);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.fiksu.asotracking.ConversionEventTracker
 * JD-Core Version:    0.7.0.1
 */