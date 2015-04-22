package com.ea.nimble;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Iterator;

class ApplicationLifecycleImpl
  extends Component
  implements IApplicationLifecycle, LogSource
{
  private ArrayList<IApplicationLifecycle.ActivityEventCallbacks> m_activityEventCallbacks;
  private ArrayList<IApplicationLifecycle.ActivityLifecycleCallbacks> m_activityLifecycleCallbacks;
  private ArrayList<IApplicationLifecycle.ApplicationLifecycleCallbacks> m_applicationLifecycleCallbacks;
  private BaseCore m_core;
  private int m_createdActivityCount;
  private int m_runningActivityCount;
  private State m_state;
  
  ApplicationLifecycleImpl(BaseCore paramBaseCore)
  {
    this.m_core = paramBaseCore;
    this.m_state = State.INIT;
    this.m_createdActivityCount = 0;
    this.m_runningActivityCount = 0;
    this.m_activityLifecycleCallbacks = new ArrayList();
    this.m_activityEventCallbacks = new ArrayList();
    this.m_applicationLifecycleCallbacks = new ArrayList();
  }
  
  private void notifyApplicationLaunch(Intent paramIntent)
  {
    Log.Helper.LOGD(this, "Application launch", new Object[0]);
    Iterator localIterator = this.m_applicationLifecycleCallbacks.iterator();
    while (localIterator.hasNext()) {
      ((IApplicationLifecycle.ApplicationLifecycleCallbacks)localIterator.next()).onApplicationLaunch(paramIntent);
    }
  }
  
  private void notifyApplicationQuit()
  {
    Iterator localIterator = this.m_applicationLifecycleCallbacks.iterator();
    while (localIterator.hasNext()) {
      ((IApplicationLifecycle.ApplicationLifecycleCallbacks)localIterator.next()).onApplicationQuit();
    }
    Log.Helper.LOGD(this, "Application quit", new Object[0]);
  }
  
  private void notifyApplicationResume()
  {
    Log.Helper.LOGD(this, "Application resume", new Object[0]);
    Iterator localIterator = this.m_applicationLifecycleCallbacks.iterator();
    while (localIterator.hasNext()) {
      ((IApplicationLifecycle.ApplicationLifecycleCallbacks)localIterator.next()).onApplicationResume();
    }
  }
  
  private void notifyApplicationSuspend()
  {
    Iterator localIterator = this.m_applicationLifecycleCallbacks.iterator();
    while (localIterator.hasNext()) {
      ((IApplicationLifecycle.ApplicationLifecycleCallbacks)localIterator.next()).onApplicationSuspend();
    }
    Log.Helper.LOGD(this, "Application suspend", new Object[0]);
  }
  
  public String getComponentId()
  {
    return "com.ea.nimble.applicationlifecycle";
  }
  
  public String getLogSourceTitle()
  {
    return "AppLifecycle";
  }
  
  public boolean handleBackPressed()
  {
    boolean bool = true;
    Iterator localIterator = this.m_activityEventCallbacks.iterator();
    while (localIterator.hasNext()) {
      if (!((IApplicationLifecycle.ActivityEventCallbacks)localIterator.next()).onBackPressed()) {
        bool = false;
      }
    }
    return bool;
  }
  
  public void notifyActivityCreate(Bundle paramBundle, Activity paramActivity)
  {
    Object[] arrayOfObject1 = new Object[1];
    arrayOfObject1[0] = paramActivity.getLocalClassName();
    Log.Helper.LOGV(this, "Activity %s CREATE", arrayOfObject1);
    if ((this.m_state == State.INIT) || (this.m_state == State.QUIT))
    {
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = this.m_state.toString();
      Log.Helper.LOGD(this, "Activity created clearly with state %s", arrayOfObject2);
      this.m_core.onApplicationLaunch(paramActivity.getIntent());
      Iterator localIterator1 = this.m_activityLifecycleCallbacks.iterator();
      while (localIterator1.hasNext()) {
        ((IApplicationLifecycle.ActivityLifecycleCallbacks)localIterator1.next()).onActivityCreated(paramActivity, paramBundle);
      }
      notifyApplicationLaunch(paramActivity.getIntent());
      this.m_createdActivityCount = 1;
      this.m_state = State.LAUNCH;
      if (this.m_runningActivityCount != 0)
      {
        Object[] arrayOfObject4 = new Object[1];
        arrayOfObject4[0] = Integer.valueOf(this.m_runningActivityCount);
        Log.Helper.LOGE(this, "Invalid running acitivity count %d", arrayOfObject4);
        this.m_runningActivityCount = 0;
      }
    }
    for (;;)
    {
      Object[] arrayOfObject3 = new Object[3];
      arrayOfObject3[0] = this.m_state.toString();
      arrayOfObject3[1] = Integer.valueOf(this.m_createdActivityCount);
      arrayOfObject3[2] = Integer.valueOf(this.m_runningActivityCount);
      Log.Helper.LOGV(this, "State after created %s (%d, %d)", arrayOfObject3);
      return;
      if (this.m_state == State.CONFIG_CHANGE)
      {
        if (ApplicationEnvironment.getCurrentActivity() != paramActivity)
        {
          Object[] arrayOfObject8 = new Object[2];
          arrayOfObject8[0] = ApplicationEnvironment.getCurrentActivity().getLocalClassName();
          arrayOfObject8[1] = paramActivity.getLocalClassName();
          Log.Helper.LOGE(this, "Activity created with state CONFIG_CHANGE but different activity %s and %s", arrayOfObject8);
        }
        for (;;)
        {
          this.m_core.onApplicationResume();
          Iterator localIterator4 = this.m_activityLifecycleCallbacks.iterator();
          while (localIterator4.hasNext()) {
            ((IApplicationLifecycle.ActivityLifecycleCallbacks)localIterator4.next()).onActivityCreated(paramActivity, paramBundle);
          }
          Log.Helper.LOGD(this, "Activity created from CONFIG_CHANGE, activity configuration changed", new Object[0]);
        }
        notifyApplicationResume();
        this.m_state = State.RESUME;
        if (this.m_runningActivityCount != 0)
        {
          Object[] arrayOfObject7 = new Object[1];
          arrayOfObject7[0] = Integer.valueOf(this.m_runningActivityCount);
          Log.Helper.LOGE(this, "Invalid running acitivity count %d", arrayOfObject7);
          this.m_runningActivityCount = 0;
        }
      }
      else if (this.m_state == State.PAUSE)
      {
        Log.Helper.LOGD(this, "Activity created from PAUSE, normal activity switch", new Object[0]);
        Iterator localIterator3 = this.m_activityLifecycleCallbacks.iterator();
        while (localIterator3.hasNext()) {
          ((IApplicationLifecycle.ActivityLifecycleCallbacks)localIterator3.next()).onActivityCreated(paramActivity, paramBundle);
        }
        this.m_createdActivityCount = (1 + this.m_createdActivityCount);
      }
      else if (this.m_state == State.SUSPEND)
      {
        Log.Helper.LOGD(this, "Activity created from SUSPEND, external activity switch", new Object[0]);
        this.m_core.onApplicationResume();
        this.m_state = State.RESUME;
        Iterator localIterator2 = this.m_activityLifecycleCallbacks.iterator();
        while (localIterator2.hasNext()) {
          ((IApplicationLifecycle.ActivityLifecycleCallbacks)localIterator2.next()).onActivityCreated(paramActivity, paramBundle);
        }
        this.m_createdActivityCount = (1 + this.m_createdActivityCount);
        if (this.m_runningActivityCount != 0)
        {
          Object[] arrayOfObject6 = new Object[1];
          arrayOfObject6[0] = Integer.valueOf(this.m_runningActivityCount);
          Log.Helper.LOGE(this, "Invalid running acitivity count %d", arrayOfObject6);
          this.m_runningActivityCount = 0;
        }
      }
      else
      {
        Object[] arrayOfObject5 = new Object[1];
        arrayOfObject5[0] = this.m_state.toString();
        Log.Helper.LOGE(this, "Activity created with %s state, shouldn't happen", arrayOfObject5);
      }
    }
  }
  
  @SuppressLint({"NewApi"})
  public void notifyActivityDestroy(Activity paramActivity)
  {
    Object[] arrayOfObject1 = new Object[1];
    arrayOfObject1[0] = paramActivity.getLocalClassName();
    Log.Helper.LOGV(this, "Activity %s DESTROY", arrayOfObject1);
    Iterator localIterator = this.m_activityLifecycleCallbacks.iterator();
    while (localIterator.hasNext()) {
      ((IApplicationLifecycle.ActivityLifecycleCallbacks)localIterator.next()).onActivityDestroyed(paramActivity);
    }
    if (this.m_state != State.CONFIG_CHANGE)
    {
      if ((this.m_state != State.SUSPEND) && (this.m_state != State.RUN))
      {
        Object[] arrayOfObject3 = new Object[1];
        arrayOfObject3[0] = this.m_state.toString();
        Log.Helper.LOGE(this, "Activity destroy on invalid state %s", arrayOfObject3);
      }
      if ((Build.VERSION.SDK_INT < 11) || (!paramActivity.isChangingConfigurations())) {
        break label179;
      }
      this.m_state = State.CONFIG_CHANGE;
    }
    for (;;)
    {
      Object[] arrayOfObject2 = new Object[3];
      arrayOfObject2[0] = this.m_state.toString();
      arrayOfObject2[1] = Integer.valueOf(this.m_createdActivityCount);
      arrayOfObject2[2] = Integer.valueOf(this.m_runningActivityCount);
      Log.Helper.LOGV(this, "State after destroy %s (%d, %d)", arrayOfObject2);
      return;
      label179:
      this.m_createdActivityCount = (-1 + this.m_createdActivityCount);
      if (this.m_createdActivityCount == 0)
      {
        this.m_state = State.QUIT;
        notifyApplicationQuit();
        this.m_core.onApplicationQuit();
      }
    }
  }
  
  public void notifyActivityPause(Activity paramActivity)
  {
    Object[] arrayOfObject1 = new Object[1];
    arrayOfObject1[0] = paramActivity.getLocalClassName();
    Log.Helper.LOGV(this, "Activity %s PAUSE", arrayOfObject1);
    Iterator localIterator = this.m_activityLifecycleCallbacks.iterator();
    while (localIterator.hasNext()) {
      ((IApplicationLifecycle.ActivityLifecycleCallbacks)localIterator.next()).onActivityPaused(paramActivity);
    }
    if (this.m_state != State.RUN)
    {
      Object[] arrayOfObject3 = new Object[1];
      arrayOfObject3[0] = paramActivity.getLocalClassName();
      Log.Helper.LOGE(this, "Activity pause on invalid state %s", arrayOfObject3);
    }
    this.m_state = State.PAUSE;
    Object[] arrayOfObject2 = new Object[3];
    arrayOfObject2[0] = this.m_state.toString();
    arrayOfObject2[1] = Integer.valueOf(this.m_createdActivityCount);
    arrayOfObject2[2] = Integer.valueOf(this.m_runningActivityCount);
    Log.Helper.LOGV(this, "State after pause %s (%d, %d)", arrayOfObject2);
  }
  
  public void notifyActivityRestart(Activity paramActivity)
  {
    Object[] arrayOfObject1 = new Object[1];
    arrayOfObject1[0] = paramActivity.getLocalClassName();
    Log.Helper.LOGV(this, "Activity %s RESTART", arrayOfObject1);
    ApplicationEnvironment.setCurrentActivity(paramActivity);
    if (this.m_state == State.PAUSE) {
      Log.Helper.LOGD(this, "Activity restart from PAUSE, normal activity switch", new Object[0]);
    }
    for (;;)
    {
      Object[] arrayOfObject3 = new Object[3];
      arrayOfObject3[0] = this.m_state.toString();
      arrayOfObject3[1] = Integer.valueOf(this.m_createdActivityCount);
      arrayOfObject3[2] = Integer.valueOf(this.m_runningActivityCount);
      Log.Helper.LOGV(this, "State after restart %s (%d, %d)", arrayOfObject3);
      return;
      if (this.m_state == State.SUSPEND)
      {
        this.m_core.onApplicationResume();
        this.m_state = State.RESUME;
        Log.Helper.LOGD(this, "Activity restart from SUSPEND, external activity switch", new Object[0]);
      }
      else
      {
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = this.m_state.toString();
        Log.Helper.LOGE(this, "Activity restart with invalid state %s", arrayOfObject2);
      }
    }
  }
  
  public void notifyActivityRestoreInstanceState(Bundle paramBundle, Activity paramActivity)
  {
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = paramActivity.getLocalClassName();
    Log.Helper.LOGV(this, "Activity %s RESTORE_STATE", arrayOfObject);
  }
  
  public void notifyActivityResult(int paramInt1, int paramInt2, Intent paramIntent, Activity paramActivity)
  {
    Iterator localIterator = this.m_activityEventCallbacks.iterator();
    while (localIterator.hasNext()) {
      ((IApplicationLifecycle.ActivityEventCallbacks)localIterator.next()).onActivityResult(paramActivity, paramInt1, paramInt2, paramIntent);
    }
  }
  
  public void notifyActivityResume(Activity paramActivity)
  {
    Object[] arrayOfObject1 = new Object[1];
    arrayOfObject1[0] = paramActivity.getLocalClassName();
    Log.Helper.LOGV(this, "Activity %s RESUME", arrayOfObject1);
    ApplicationEnvironment.setCurrentActivity(paramActivity);
    Iterator localIterator = this.m_activityLifecycleCallbacks.iterator();
    while (localIterator.hasNext()) {
      ((IApplicationLifecycle.ActivityLifecycleCallbacks)localIterator.next()).onActivityResumed(paramActivity);
    }
    if (this.m_state != State.PAUSE)
    {
      Object[] arrayOfObject3 = new Object[1];
      arrayOfObject3[0] = this.m_state.toString();
      Log.Helper.LOGE(this, "Activity resume on invalid state %s", arrayOfObject3);
      Log.Helper.LOGE(this, "<NOTE>Please double check if the game's activity hooks ApplicationLifecycle.onActivityRestart() correctly.", new Object[0]);
    }
    this.m_state = State.RUN;
    Object[] arrayOfObject2 = new Object[3];
    arrayOfObject2[0] = this.m_state.toString();
    arrayOfObject2[1] = Integer.valueOf(this.m_createdActivityCount);
    arrayOfObject2[2] = Integer.valueOf(this.m_runningActivityCount);
    Log.Helper.LOGV(this, "State after resume %s (%d, %d)", arrayOfObject2);
  }
  
  public void notifyActivityRetainNonConfigurationInstance()
  {
    if (this.m_state != State.SUSPEND)
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = this.m_state.toString();
      Log.Helper.LOGW(this, "configuration change should happen between onStop() and onDestroy(), but state is %s", arrayOfObject);
    }
    this.m_state = State.CONFIG_CHANGE;
  }
  
  public void notifyActivitySaveInstanceState(Bundle paramBundle, Activity paramActivity)
  {
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = paramActivity.getLocalClassName();
    Log.Helper.LOGV(this, "Activity %s SAVE_STATE", arrayOfObject);
    Iterator localIterator = this.m_activityLifecycleCallbacks.iterator();
    while (localIterator.hasNext()) {
      ((IApplicationLifecycle.ActivityLifecycleCallbacks)localIterator.next()).onActivitySaveInstanceState(paramActivity, paramBundle);
    }
  }
  
  public void notifyActivityStart(Activity paramActivity)
  {
    Object[] arrayOfObject1 = new Object[1];
    arrayOfObject1[0] = paramActivity.getLocalClassName();
    Log.Helper.LOGV(this, "Activity %s START", arrayOfObject1);
    ApplicationEnvironment.setCurrentActivity(paramActivity);
    if (this.m_state == State.LAUNCH)
    {
      this.m_state = State.PAUSE;
      Log.Helper.LOGD(this, "Activity start with LAUNCH state, normal app start", new Object[0]);
    }
    for (;;)
    {
      this.m_runningActivityCount = (1 + this.m_runningActivityCount);
      Object[] arrayOfObject3 = new Object[3];
      arrayOfObject3[0] = this.m_state.toString();
      arrayOfObject3[1] = Integer.valueOf(this.m_createdActivityCount);
      arrayOfObject3[2] = Integer.valueOf(this.m_runningActivityCount);
      Log.Helper.LOGV(this, "State after start %s (%d, %d)", arrayOfObject3);
      return;
      if (this.m_state == State.RESUME)
      {
        Iterator localIterator2 = this.m_activityLifecycleCallbacks.iterator();
        while (localIterator2.hasNext()) {
          ((IApplicationLifecycle.ActivityLifecycleCallbacks)localIterator2.next()).onActivityStarted(paramActivity);
        }
        notifyApplicationResume();
        this.m_state = State.PAUSE;
        Log.Helper.LOGD(this, "Activity start with CONFIG_CHANGE state, set to PAUSE", new Object[0]);
      }
      else if (this.m_state == State.PAUSE)
      {
        Log.Helper.LOGD(this, "Activity start with PAUSE state, normal activity switch", new Object[0]);
      }
      else
      {
        Iterator localIterator1 = this.m_activityLifecycleCallbacks.iterator();
        while (localIterator1.hasNext()) {
          ((IApplicationLifecycle.ActivityLifecycleCallbacks)localIterator1.next()).onActivityStarted(paramActivity);
        }
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = this.m_state.toString();
        Log.Helper.LOGE(this, "Activity start with invalid state %s", arrayOfObject2);
      }
    }
  }
  
  @SuppressLint({"NewApi"})
  public void notifyActivityStop(Activity paramActivity)
  {
    Object[] arrayOfObject1 = new Object[1];
    arrayOfObject1[0] = paramActivity.getLocalClassName();
    Log.Helper.LOGV(this, "Activity %s STOP", arrayOfObject1);
    Iterator localIterator = this.m_activityLifecycleCallbacks.iterator();
    while (localIterator.hasNext()) {
      ((IApplicationLifecycle.ActivityLifecycleCallbacks)localIterator.next()).onActivityStopped(paramActivity);
    }
    this.m_runningActivityCount = (-1 + this.m_runningActivityCount);
    if (this.m_runningActivityCount == 0)
    {
      if ((this.m_state != State.PAUSE) && (this.m_state != State.SUSPEND))
      {
        Object[] arrayOfObject4 = new Object[1];
        arrayOfObject4[0] = this.m_state;
        Log.Helper.LOGW(this, "Interesting case %s, HIGHLIGHT!!", arrayOfObject4);
      }
      this.m_state = State.SUSPEND;
      if (!paramActivity.isFinishing())
      {
        notifyApplicationSuspend();
        this.m_core.onApplicationSuspend();
      }
    }
    for (;;)
    {
      Object[] arrayOfObject3 = new Object[3];
      arrayOfObject3[0] = this.m_state.toString();
      arrayOfObject3[1] = Integer.valueOf(this.m_createdActivityCount);
      arrayOfObject3[2] = Integer.valueOf(this.m_runningActivityCount);
      Log.Helper.LOGV(this, "State after stop %s (%d, %d)", arrayOfObject3);
      if (ApplicationEnvironment.getCurrentActivity() == paramActivity) {
        ApplicationEnvironment.setCurrentActivity(null);
      }
      return;
      if (this.m_state == State.PAUSE)
      {
        this.m_state = State.SUSPEND;
        if (!paramActivity.isFinishing())
        {
          Log.Helper.LOGW(this, "running activity count may be messed", new Object[0]);
          notifyApplicationSuspend();
          this.m_core.onApplicationSuspend();
        }
      }
      else if (this.m_state != State.RUN)
      {
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = this.m_state.toString();
        Log.Helper.LOGE(this, "Activity stop on invalid state %s", arrayOfObject2);
      }
    }
  }
  
  public void notifyActivityWindowFocusChanged(boolean paramBoolean, Activity paramActivity)
  {
    Iterator localIterator = this.m_activityEventCallbacks.iterator();
    while (localIterator.hasNext()) {
      ((IApplicationLifecycle.ActivityEventCallbacks)localIterator.next()).onWindowFocusChanged(paramBoolean);
    }
  }
  
  public void registerActivityEventCallbacks(IApplicationLifecycle.ActivityEventCallbacks paramActivityEventCallbacks)
  {
    this.m_activityEventCallbacks.add(paramActivityEventCallbacks);
  }
  
  public void registerActivityLifecycleCallbacks(IApplicationLifecycle.ActivityLifecycleCallbacks paramActivityLifecycleCallbacks)
  {
    this.m_activityLifecycleCallbacks.add(paramActivityLifecycleCallbacks);
  }
  
  public void registerApplicationLifecycleCallbacks(IApplicationLifecycle.ApplicationLifecycleCallbacks paramApplicationLifecycleCallbacks)
  {
    this.m_applicationLifecycleCallbacks.add(paramApplicationLifecycleCallbacks);
  }
  
  protected void teardown()
  {
    this.m_activityLifecycleCallbacks.clear();
    this.m_activityEventCallbacks.clear();
    this.m_applicationLifecycleCallbacks.clear();
  }
  
  public void unregisterActivityEventCallbacks(IApplicationLifecycle.ActivityEventCallbacks paramActivityEventCallbacks)
  {
    this.m_activityEventCallbacks.remove(paramActivityEventCallbacks);
  }
  
  public void unregisterActivityLifecycleCallbacks(IApplicationLifecycle.ActivityLifecycleCallbacks paramActivityLifecycleCallbacks)
  {
    this.m_activityLifecycleCallbacks.remove(paramActivityLifecycleCallbacks);
  }
  
  public void unregisterApplicationLifecycleCallbacks(IApplicationLifecycle.ApplicationLifecycleCallbacks paramApplicationLifecycleCallbacks)
  {
    this.m_applicationLifecycleCallbacks.remove(paramApplicationLifecycleCallbacks);
  }
  
  private static enum State
  {
    static
    {
      PAUSE = new State("PAUSE", 4);
      SUSPEND = new State("SUSPEND", 5);
      QUIT = new State("QUIT", 6);
      CONFIG_CHANGE = new State("CONFIG_CHANGE", 7);
      State[] arrayOfState = new State[8];
      arrayOfState[0] = INIT;
      arrayOfState[1] = LAUNCH;
      arrayOfState[2] = RESUME;
      arrayOfState[3] = RUN;
      arrayOfState[4] = PAUSE;
      arrayOfState[5] = SUSPEND;
      arrayOfState[6] = QUIT;
      arrayOfState[7] = CONFIG_CHANGE;
      $VALUES = arrayOfState;
    }
    
    private State() {}
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ea.nimble.ApplicationLifecycleImpl
 * JD-Core Version:    0.7.0.1
 */