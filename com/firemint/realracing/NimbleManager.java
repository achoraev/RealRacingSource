package com.firemint.realracing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.ea.nimble.ApplicationLifecycle;
import com.ea.nimble.ISynergyEnvironment;
import com.ea.nimble.SynergyEnvironment;
import com.ea.nimble.tracking.ITracking;
import com.ea.nimble.tracking.Tracking;
import java.util.HashMap;

public class NimbleManager
{
  Activity m_activity = null;
  boolean m_initialised = false;
  
  public static String getEADeviceID()
  {
    if ((SynergyEnvironment.getComponent() != null) && (SynergyEnvironment.getComponent().isDataAvailable())) {
      return SynergyEnvironment.getComponent().getEADeviceId();
    }
    Log.e("RealRacing3", "getEADeviceID: Failed to retrieve ID");
    return "";
  }
  
  public static String getSynergyID()
  {
    if ((SynergyEnvironment.getComponent() != null) && (SynergyEnvironment.getComponent().isDataAvailable())) {
      return SynergyEnvironment.getComponent().getSynergyId();
    }
    Log.e("RealRacing3", "getSynergyID: Failed to retrieve ID");
    return "";
  }
  
  public static void setEnableTracking(boolean paramBoolean)
  {
    try
    {
      if (Tracking.getComponent() != null) {
        Tracking.getComponent().setEnable(paramBoolean);
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      Log.e("RealRacing3", "Nimble Tracking Error: " + localThrowable.getMessage());
    }
  }
  
  public static void uploadMtxEvent(String paramString1, String paramString2)
  {
    try
    {
      if (Tracking.getComponent() != null)
      {
        HashMap localHashMap = new HashMap();
        localHashMap.put("NIMBLESTANDARD::KEY_MTX_CURRENCY", paramString1);
        localHashMap.put("NIMBLESTANDARD::KEY_MTX_PRICE", paramString2);
        Tracking.getComponent().logEvent("NIMBLESTANDARD::MTX_ITEM_PURCHASED", localHashMap);
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      Log.e("RealRacing3", "Nimble MTX Error: " + localThrowable.getMessage());
    }
  }
  
  public void initialise(Activity paramActivity)
  {
    this.m_activity = paramActivity;
    this.m_initialised = true;
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (this.m_initialised) {
      ApplicationLifecycle.onActivityResult(paramInt1, paramInt2, paramIntent, this.m_activity);
    }
  }
  
  public boolean onBackPressed()
  {
    boolean bool = true;
    if (this.m_initialised) {
      bool = ApplicationLifecycle.onBackPressed();
    }
    return bool;
  }
  
  public void onCreate(Bundle paramBundle)
  {
    if (this.m_initialised)
    {
      ApplicationLifecycle.onActivityCreate(paramBundle, this.m_activity);
      Tracking.getComponent().setEnable(true);
    }
  }
  
  protected void onDestroy()
  {
    if (this.m_initialised) {
      ApplicationLifecycle.onActivityDestroy(this.m_activity);
    }
  }
  
  protected void onPause()
  {
    if (this.m_initialised) {
      ApplicationLifecycle.onActivityPause(this.m_activity);
    }
  }
  
  protected void onRestart()
  {
    if (this.m_initialised) {
      ApplicationLifecycle.onActivityRestart(this.m_activity);
    }
  }
  
  public void onRestoreInstanceState(Bundle paramBundle)
  {
    if (this.m_initialised) {
      ApplicationLifecycle.onActivityRestoreInstanceState(paramBundle, this.m_activity);
    }
  }
  
  protected void onResume()
  {
    if (this.m_initialised) {
      ApplicationLifecycle.onActivityResume(this.m_activity);
    }
  }
  
  public Object onRetainNonConfigurationInstance()
  {
    ApplicationLifecycle.onActivityRetainNonConfigurationInstance();
    return null;
  }
  
  protected void onSaveInstanceState(Bundle paramBundle)
  {
    if (this.m_initialised) {
      ApplicationLifecycle.onActivitySaveInstanceState(paramBundle, this.m_activity);
    }
  }
  
  protected void onStart()
  {
    if (this.m_initialised) {
      ApplicationLifecycle.onActivityStart(this.m_activity);
    }
  }
  
  protected void onStop()
  {
    if (this.m_initialised) {
      ApplicationLifecycle.onActivityStop(this.m_activity);
    }
  }
  
  public void onWindowFocusChanged(boolean paramBoolean)
  {
    if (this.m_initialised) {
      ApplicationLifecycle.onActivityWindowFocusChanged(paramBoolean, this.m_activity);
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.firemint.realracing.NimbleManager
 * JD-Core Version:    0.7.0.1
 */