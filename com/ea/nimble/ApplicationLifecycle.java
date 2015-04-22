package com.ea.nimble;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class ApplicationLifecycle
{
  public static final String COMPONENT_ID = "com.ea.nimble.applicationlifecycle";
  
  public static IApplicationLifecycle getComponent()
  {
    return BaseCore.getInstance().getApplicationLifecycle();
  }
  
  public static void onActivityCreate(Bundle paramBundle, Activity paramActivity)
  {
    ApplicationEnvironment.setCurrentActivity(paramActivity);
    getComponent().notifyActivityCreate(paramBundle, paramActivity);
  }
  
  public static void onActivityDestroy(Activity paramActivity)
  {
    getComponent().notifyActivityDestroy(paramActivity);
  }
  
  public static void onActivityPause(Activity paramActivity)
  {
    getComponent().notifyActivityPause(paramActivity);
  }
  
  public static void onActivityRestart(Activity paramActivity)
  {
    getComponent().notifyActivityRestart(paramActivity);
  }
  
  public static void onActivityRestoreInstanceState(Bundle paramBundle, Activity paramActivity)
  {
    getComponent().notifyActivityRestoreInstanceState(paramBundle, paramActivity);
  }
  
  public static void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent, Activity paramActivity)
  {
    getComponent().notifyActivityResult(paramInt1, paramInt2, paramIntent, paramActivity);
  }
  
  public static void onActivityResume(Activity paramActivity)
  {
    getComponent().notifyActivityResume(paramActivity);
  }
  
  public static void onActivityRetainNonConfigurationInstance()
  {
    getComponent().notifyActivityRetainNonConfigurationInstance();
  }
  
  public static void onActivitySaveInstanceState(Bundle paramBundle, Activity paramActivity)
  {
    getComponent().notifyActivitySaveInstanceState(paramBundle, paramActivity);
  }
  
  public static void onActivityStart(Activity paramActivity)
  {
    getComponent().notifyActivityStart(paramActivity);
  }
  
  public static void onActivityStop(Activity paramActivity)
  {
    getComponent().notifyActivityStop(paramActivity);
  }
  
  public static void onActivityWindowFocusChanged(boolean paramBoolean, Activity paramActivity)
  {
    getComponent().notifyActivityWindowFocusChanged(paramBoolean, paramActivity);
  }
  
  public static boolean onBackPressed()
  {
    return getComponent().handleBackPressed();
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ea.nimble.ApplicationLifecycle
 * JD-Core Version:    0.7.0.1
 */