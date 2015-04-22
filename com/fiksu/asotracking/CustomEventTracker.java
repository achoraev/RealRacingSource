package com.fiksu.asotracking;

import android.content.Context;

final class CustomEventTracker
  extends EventTracker
{
  CustomEventTracker(Context paramContext)
  {
    super(paramContext, FiksuEventType.CUSTOM_EVENT_01.getName());
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.fiksu.asotracking.CustomEventTracker
 * JD-Core Version:    0.7.0.1
 */