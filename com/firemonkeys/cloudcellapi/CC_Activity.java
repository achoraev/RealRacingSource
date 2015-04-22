package com.firemonkeys.cloudcellapi;

import android.app.Activity;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.ViewGroup;
import com.firemonkeys.cloudcellapi.util.GetInfo;

public class CC_Activity
  extends Activity
{
  private static boolean s_bDownloadServiceStarted;
  private static Boolean s_canCallSuspendResume;
  private static GLSurfaceView s_glSurfaceView;
  private static Activity s_instance;
  private static ViewGroup s_viewGroup;
  
  static
  {
    if (!CC_Activity.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      s_instance = null;
      s_glSurfaceView = null;
      s_viewGroup = null;
      s_canCallSuspendResume = Boolean.valueOf(false);
      s_bDownloadServiceStarted = false;
      return;
    }
  }
  
  private static native void CloudcellResume();
  
  private static native void CloudcellSuspend();
  
  public static Activity GetActivity()
  {
    return s_instance;
  }
  
  public static GLSurfaceView GetGLSurfaceView()
  {
    return s_glSurfaceView;
  }
  
  public static ViewGroup GetViewGroup()
  {
    return s_viewGroup;
  }
  
  public static void canCallSuspendResume()
  {
    s_canCallSuspendResume = Boolean.valueOf(true);
  }
  
  public static int getMobileNetworkGeneration()
  {
    return NetworkStatusMonitor.getMobileNetworkGeneration(GetActivity().getApplicationContext());
  }
  
  public static int getNetworkConnectivity()
  {
    return NetworkStatusMonitor.getNetworkConnectivity(GetActivity().getApplicationContext());
  }
  
  public static Object getStaticSystemService(String paramString)
  {
    Activity localActivity1 = GetActivity();
    assert (localActivity1 != null);
    Activity localActivity2 = GetActivity();
    Object localObject = null;
    if (localActivity2 != null) {
      localObject = localActivity1.getSystemService(paramString);
    }
    return localObject;
  }
  
  public static void runOnGLThreadonCloudell(Runnable paramRunnable)
  {
    if ((paramRunnable == null) || (s_glSurfaceView == null)) {
      return;
    }
    GetGLSurfaceView().queueEvent(paramRunnable);
  }
  
  public static void setServiceStarted(boolean paramBoolean)
  {
    s_bDownloadServiceStarted = paramBoolean;
  }
  
  public static void startDownloadService()
  {
    if (s_bDownloadServiceStarted) {}
    Activity localActivity;
    do
    {
      return;
      localActivity = GetActivity();
      assert (localActivity != null);
    } while ((localActivity == null) || (s_bDownloadServiceStarted));
    Log.i("Cloudcell", "CC_Activity::startDownloadService()");
    localActivity.startService(new Intent(localActivity, AndroidAssetManagerService.class));
    s_bDownloadServiceStarted = true;
  }
  
  public static void staticStartActivity(Intent paramIntent)
  {
    Activity localActivity = GetActivity();
    assert (localActivity != null);
    if (GetActivity() != null) {
      localActivity.startActivity(paramIntent);
    }
  }
  
  public static void stopDownloadService()
  {
    Activity localActivity = GetActivity();
    assert (localActivity != null);
    if (localActivity != null)
    {
      Log.i("Cloudcell", "CC_Activity::stopDownloadService()");
      localActivity.stopService(new Intent(localActivity, AndroidAssetManagerService.class));
      s_bDownloadServiceStarted = false;
    }
  }
  
  protected void clearInstances()
  {
    s_instance = null;
    s_viewGroup = null;
    s_glSurfaceView = null;
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    CC_FacebookWorker_Class.handleActivityResult(paramInt1, paramInt2, paramIntent);
    if (CC_GooglePlusWorker_Class.m_pGoogleApiClient != null) {
      CC_GooglePlusWorker_Class.handleActivityResult(paramInt1, paramInt2, paramIntent);
    }
    CC_GoogleStoreServiceV3_Class.handleActivityResult(paramInt1, paramInt2, paramIntent);
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    CC_GoogleAdManager_Class localCC_GoogleAdManager_Class = CC_GoogleAdManager_Class.GetInstance();
    if (localCC_GoogleAdManager_Class != null) {
      localCC_GoogleAdManager_Class.onDestroy();
    }
  }
  
  protected void onPause()
  {
    super.onPause();
    CC_GoogleAdManager_Class localCC_GoogleAdManager_Class = CC_GoogleAdManager_Class.GetInstance();
    if (localCC_GoogleAdManager_Class != null) {
      localCC_GoogleAdManager_Class.onPause();
    }
    if (s_canCallSuspendResume.booleanValue()) {
      CloudcellSuspend();
    }
  }
  
  protected void onResume()
  {
    super.onResume();
    CC_GoogleAdManager_Class localCC_GoogleAdManager_Class = CC_GoogleAdManager_Class.GetInstance();
    if (localCC_GoogleAdManager_Class != null) {
      localCC_GoogleAdManager_Class.onResume();
    }
    AndroidAccountManager localAndroidAccountManager = AndroidAccountManager.getInstance();
    if (localAndroidAccountManager != null) {
      localAndroidAccountManager.onResume();
    }
    if (s_canCallSuspendResume.booleanValue()) {
      CloudcellResume();
    }
  }
  
  protected void onStart()
  {
    super.onStart();
    CC_GooglePlusWorker_Class.onStart();
  }
  
  protected void onStop()
  {
    super.onStop();
    CC_GooglePlusWorker_Class.onStop();
  }
  
  protected void setInstances(GLSurfaceView paramGLSurfaceView, ViewGroup paramViewGroup)
  {
    s_glSurfaceView = paramGLSurfaceView;
    s_viewGroup = paramViewGroup;
    s_instance = this;
    GetInfo.InitializeVolatile();
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.firemonkeys.cloudcellapi.CC_Activity
 * JD-Core Version:    0.7.0.1
 */