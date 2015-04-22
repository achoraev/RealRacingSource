package com.fiksu.asotracking;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Application;
import android.os.Process;
import android.util.Log;
import java.util.Iterator;
import java.util.List;

class ForegroundTester
  implements Runnable
{
  private static boolean sStarted = false;
  private final Application mApplication;
  private final LaunchEventTracker mLaunchEventTracker;
  private boolean mPostedLaunch = false;
  private boolean mWasInForeground = false;
  
  ForegroundTester(Application paramApplication, LaunchEventTracker paramLaunchEventTracker)
  {
    this.mApplication = paramApplication;
    this.mLaunchEventTracker = paramLaunchEventTracker;
    try
    {
      if (sStarted)
      {
        Log.e("FiksuTracking", "Already initialized!. Only call FiksuTrackingManager.initialize() once.");
        return;
      }
      sStarted = true;
      new Thread(this).start();
      return;
    }
    finally {}
  }
  
  private boolean inForeground()
  {
    ActivityManager localActivityManager = (ActivityManager)this.mApplication.getSystemService("activity");
    try
    {
      List localList2 = localActivityManager.getRunningAppProcesses();
      localList1 = localList2;
    }
    catch (OutOfMemoryError localOutOfMemoryError)
    {
      List localList1;
      for (;;)
      {
        Log.e("FiksuTracking", "Unexpected exception", localOutOfMemoryError);
        localList1 = null;
      }
      Iterator localIterator = localList1.iterator();
      ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo;
      do
      {
        if (!localIterator.hasNext()) {
          break;
        }
        localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)localIterator.next();
      } while ((localRunningAppProcessInfo == null) || (localRunningAppProcessInfo.importance != 100) || (!this.mApplication.getPackageName().equals(localRunningAppProcessInfo.processName)));
    }
    return localList1 != null;
  }
  
  void postEvent()
  {
    if (!this.mPostedLaunch)
    {
      this.mPostedLaunch = true;
      this.mLaunchEventTracker.uploadEvent();
      return;
    }
    new ResumeEventTracker(this.mApplication).uploadEvent();
  }
  
  public void run()
  {
    try
    {
      Log.d("FiksuTracking", "ForegroundTester thread started, process: " + Process.myPid());
      Thread.sleep(6000L);
      for (;;)
      {
        Thread.sleep(5000L);
        if ((this.mWasInForeground) || (!inForeground())) {
          break;
        }
        postEvent();
        this.mWasInForeground = true;
      }
    }
    catch (InterruptedException localInterruptedException)
    {
      for (;;)
      {
        Log.i("FiksuTracking", "ForegroundTester thread was interrupted.");
        return;
        if ((this.mWasInForeground) && (!inForeground())) {
          this.mWasInForeground = false;
        }
      }
    }
    catch (SecurityException localSecurityException)
    {
      Log.i("FiksuTracking", "ForegroundTester thread was aborted.");
      return;
    }
    catch (Exception localException)
    {
      Log.e("FiksuTracking", "Unexpected exception", localException);
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.fiksu.asotracking.ForegroundTester
 * JD-Core Version:    0.7.0.1
 */