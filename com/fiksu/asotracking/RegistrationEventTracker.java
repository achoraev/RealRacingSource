package com.fiksu.asotracking;

import android.content.Context;

final class RegistrationEventTracker
  extends EventTracker
{
  RegistrationEventTracker(Context paramContext, FiksuTrackingManager.RegistrationEvent paramRegistrationEvent, String paramString)
  {
    super(paramContext, FiksuEventType.REGISTRATION.getName() + paramRegistrationEvent.getNameSuffix());
    addParameter(FiksuEventParameter.USERNAME, paramString);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.fiksu.asotracking.RegistrationEventTracker
 * JD-Core Version:    0.7.0.1
 */