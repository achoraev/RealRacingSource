package com.ea.nimble;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public abstract interface IApplicationLifecycle
{
  public abstract boolean handleBackPressed();
  
  public abstract void notifyActivityCreate(Bundle paramBundle, Activity paramActivity);
  
  public abstract void notifyActivityDestroy(Activity paramActivity);
  
  public abstract void notifyActivityPause(Activity paramActivity);
  
  public abstract void notifyActivityRestart(Activity paramActivity);
  
  public abstract void notifyActivityRestoreInstanceState(Bundle paramBundle, Activity paramActivity);
  
  public abstract void notifyActivityResult(int paramInt1, int paramInt2, Intent paramIntent, Activity paramActivity);
  
  public abstract void notifyActivityResume(Activity paramActivity);
  
  public abstract void notifyActivityRetainNonConfigurationInstance();
  
  public abstract void notifyActivitySaveInstanceState(Bundle paramBundle, Activity paramActivity);
  
  public abstract void notifyActivityStart(Activity paramActivity);
  
  public abstract void notifyActivityStop(Activity paramActivity);
  
  public abstract void notifyActivityWindowFocusChanged(boolean paramBoolean, Activity paramActivity);
  
  public abstract void registerActivityEventCallbacks(ActivityEventCallbacks paramActivityEventCallbacks);
  
  public abstract void registerActivityLifecycleCallbacks(ActivityLifecycleCallbacks paramActivityLifecycleCallbacks);
  
  public abstract void registerApplicationLifecycleCallbacks(ApplicationLifecycleCallbacks paramApplicationLifecycleCallbacks);
  
  public abstract void unregisterActivityEventCallbacks(ActivityEventCallbacks paramActivityEventCallbacks);
  
  public abstract void unregisterActivityLifecycleCallbacks(ActivityLifecycleCallbacks paramActivityLifecycleCallbacks);
  
  public abstract void unregisterApplicationLifecycleCallbacks(ApplicationLifecycleCallbacks paramApplicationLifecycleCallbacks);
  
  public static abstract interface ActivityEventCallbacks
  {
    public abstract void onActivityResult(Activity paramActivity, int paramInt1, int paramInt2, Intent paramIntent);
    
    public abstract boolean onBackPressed();
    
    public abstract void onWindowFocusChanged(boolean paramBoolean);
  }
  
  public static class ActivityEventHandler
    implements IApplicationLifecycle.ActivityEventCallbacks
  {
    public void onActivityResult(Activity paramActivity, int paramInt1, int paramInt2, Intent paramIntent) {}
    
    public boolean onBackPressed()
    {
      return true;
    }
    
    public void onWindowFocusChanged(boolean paramBoolean) {}
  }
  
  public static abstract interface ActivityLifecycleCallbacks
  {
    public abstract void onActivityCreated(Activity paramActivity, Bundle paramBundle);
    
    public abstract void onActivityDestroyed(Activity paramActivity);
    
    public abstract void onActivityPaused(Activity paramActivity);
    
    public abstract void onActivityResumed(Activity paramActivity);
    
    public abstract void onActivitySaveInstanceState(Activity paramActivity, Bundle paramBundle);
    
    public abstract void onActivityStarted(Activity paramActivity);
    
    public abstract void onActivityStopped(Activity paramActivity);
  }
  
  public static class ActivityLifecycleHandler
    implements IApplicationLifecycle.ActivityLifecycleCallbacks
  {
    public void onActivityCreated(Activity paramActivity, Bundle paramBundle) {}
    
    public void onActivityDestroyed(Activity paramActivity) {}
    
    public void onActivityPaused(Activity paramActivity) {}
    
    public void onActivityResumed(Activity paramActivity) {}
    
    public void onActivitySaveInstanceState(Activity paramActivity, Bundle paramBundle) {}
    
    public void onActivityStarted(Activity paramActivity) {}
    
    public void onActivityStopped(Activity paramActivity) {}
  }
  
  public static abstract interface ApplicationLifecycleCallbacks
  {
    public abstract void onApplicationLaunch(Intent paramIntent);
    
    public abstract void onApplicationQuit();
    
    public abstract void onApplicationResume();
    
    public abstract void onApplicationSuspend();
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ea.nimble.IApplicationLifecycle
 * JD-Core Version:    0.7.0.1
 */