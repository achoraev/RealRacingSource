package com.fiksu.asotracking;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;

public class FiksuTrackingManager
{
  public static final String FIKSU_LOG_TAG = "FiksuTracking";
  
  @Deprecated
  public static void c2dMessageReceived(Context paramContext) {}
  
  public static String getClientId()
  {
    return FiksuDeviceSettingsManager.getInstance().getClientId();
  }
  
  public static void initialize(Application paramApplication)
  {
    if (paramApplication == null) {
      throw new IllegalArgumentException("application was null");
    }
    FiksuConfigurationManager.getInstance().initialize(paramApplication);
    FiksuDeviceSettingsManager.getInstance().initialize(paramApplication);
    new ForegroundTester(paramApplication, new LaunchEventTracker(paramApplication));
    boolean bool1 = false;
    for (int i = 1;; i = 1)
    {
      try
      {
        Bundle localBundle = paramApplication.getPackageManager().getApplicationInfo(paramApplication.getPackageName(), 128).metaData;
        bool1 = false;
        if (localBundle != null)
        {
          bool1 = localBundle.getBoolean("FiksuDisableGetDeviceId");
          boolean bool2 = localBundle.getBoolean("FiksuDisableReceiverCheck");
          if (!bool2) {
            continue;
          }
          i = 0;
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        for (;;)
        {
          Log.e("FiksuTracking", "Unexpected NameNotFoundException", localNameNotFoundException);
        }
      }
      catch (Exception localException)
      {
        for (;;)
        {
          Log.e("FiksuTracking", "Unexpected exception", localException);
        }
      }
      if (bool1) {
        FiksuDeviceSettingsManager.getInstance().setDisableGetDeviceId();
      }
      if (i != 0) {
        InstallTracking.checkForFiksuReceiver(paramApplication);
      }
      return;
    }
  }
  
  public static boolean isAppTrackingEnabled()
  {
    return FiksuDeviceSettingsManager.getInstance().isAppTrackingEnabled();
  }
  
  @Deprecated
  public static void promptForRating(Activity paramActivity) {}
  
  public static void setAppTrackingEnabled(Context paramContext, boolean paramBoolean)
  {
    if (paramContext == null) {
      throw new IllegalArgumentException("context was null");
    }
    FiksuDeviceSettingsManager.getInstance().setAppTrackingEnabled(paramContext, paramBoolean);
  }
  
  public static void setClientId(Context paramContext, String paramString)
  {
    if (paramContext == null) {
      throw new IllegalArgumentException("context was null");
    }
    FiksuDeviceSettingsManager.getInstance().setClientId(paramContext, paramString);
  }
  
  public static void setDebugModeEnabled(boolean paramBoolean)
  {
    FiksuConfigurationManager.getInstance().setDebugModeEnabled(paramBoolean);
  }
  
  public static void uploadCustomEvent(Context paramContext)
  {
    if (paramContext == null) {
      throw new IllegalArgumentException("context was null");
    }
    new CustomEventTracker(paramContext).uploadEvent();
  }
  
  public static void uploadPurchase(Context paramContext, PurchaseEvent paramPurchaseEvent, double paramDouble, String paramString)
  {
    if (paramContext == null) {
      throw new IllegalArgumentException("context was null");
    }
    if (paramPurchaseEvent == null) {
      throw new IllegalArgumentException("event was null");
    }
    new PurchaseEventTracker(paramContext, paramPurchaseEvent, null, Double.valueOf(paramDouble), paramString).uploadEvent();
  }
  
  public static void uploadPurchaseEvent(Context paramContext, String paramString1, double paramDouble, String paramString2)
  {
    if (paramContext == null) {
      throw new IllegalArgumentException("context was null");
    }
    new PurchaseEventTracker(paramContext, PurchaseEvent.EVENT1, paramString1, Double.valueOf(paramDouble), paramString2).uploadEvent();
  }
  
  public static void uploadRegistration(Context paramContext, RegistrationEvent paramRegistrationEvent)
  {
    if (paramContext == null) {
      throw new IllegalArgumentException("context was null");
    }
    if (paramRegistrationEvent == null) {
      throw new IllegalArgumentException("event was null");
    }
    new RegistrationEventTracker(paramContext, paramRegistrationEvent, null).uploadEvent();
  }
  
  public static void uploadRegistrationEvent(Context paramContext, String paramString)
  {
    if (paramContext == null) {
      throw new IllegalArgumentException("context was null");
    }
    new RegistrationEventTracker(paramContext, RegistrationEvent.EVENT1, paramString).uploadEvent();
  }
  
  public static enum PurchaseEvent
  {
    EVENT5("05"),  EVENT4("04"),  EVENT3("03"),  EVENT2("02"),  EVENT1("");
    
    private final String nameSuffix;
    
    private PurchaseEvent(String paramString)
    {
      this.nameSuffix = paramString;
    }
    
    final String getNameSuffix()
    {
      return this.nameSuffix;
    }
  }
  
  public static enum RegistrationEvent
  {
    EVENT3("03"),  EVENT2("02"),  EVENT1("");
    
    private final String nameSuffix;
    
    private RegistrationEvent(String paramString)
    {
      this.nameSuffix = paramString;
    }
    
    final String getNameSuffix()
    {
      return this.nameSuffix;
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.fiksu.asotracking.FiksuTrackingManager
 * JD-Core Version:    0.7.0.1
 */