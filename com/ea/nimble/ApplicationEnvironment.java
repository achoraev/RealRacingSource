package com.ea.nimble;

import android.app.Activity;

public class ApplicationEnvironment
{
  public static final String COMPONENT_ID = "com.ea.nimble.applicationEnvironment";
  public static final String NOTIFICATION_AGE_COMPLIANCE_REFRESHED = "nimble.notification.age_compliance_refreshed";
  
  public static IApplicationEnvironment getComponent()
  {
    return BaseCore.getInstance().getApplicationEnvironment();
  }
  
  public static Activity getCurrentActivity()
  {
    return ApplicationEnvironmentImpl.getCurrentActivity();
  }
  
  public static boolean isMainApplicationRunning()
  {
    return ApplicationEnvironmentImpl.isMainApplicationRunning();
  }
  
  public static void setCurrentActivity(Activity paramActivity)
  {
    ApplicationEnvironmentImpl.setCurrentActivity(paramActivity);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ea.nimble.ApplicationEnvironment
 * JD-Core Version:    0.7.0.1
 */