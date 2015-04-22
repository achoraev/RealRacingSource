package com.popcap.ea2;

import android.app.Activity;
import android.os.Build;
import android.os.Build.VERSION;

public class EASquared
{
  public static boolean MeetsSystemRequirements()
  {
    return (!Build.MANUFACTURER.toLowerCase().contains("amazon")) || (Build.VERSION.SDK_INT >= 16);
  }
  
  public static void applicationCreate(Activity paramActivity)
  {
    if (MeetsSystemRequirements()) {
      BrandConnectAdProvider.applicationCreate(paramActivity);
    }
  }
  
  public static void applicationDestroy(Activity paramActivity)
  {
    if (MeetsSystemRequirements()) {
      BrandConnectAdProvider.applicationDestroy(paramActivity);
    }
  }
  
  public static void applicationPause(Activity paramActivity)
  {
    if (MeetsSystemRequirements())
    {
      BrandConnectAdProvider.applicationPause(paramActivity);
      AdColonyAdProvider.applicationPause();
    }
  }
  
  public static void applicationRestart(Activity paramActivity)
  {
    if (MeetsSystemRequirements()) {
      BrandConnectAdProvider.applicationRestart(paramActivity);
    }
  }
  
  public static void applicationResume(Activity paramActivity)
  {
    if (MeetsSystemRequirements())
    {
      BrandConnectAdProvider.applicationResume(paramActivity);
      AdColonyAdProvider.applicationResume(paramActivity);
    }
  }
  
  public static void applicationStop(Activity paramActivity)
  {
    if (MeetsSystemRequirements()) {
      BrandConnectAdProvider.applicationStop(paramActivity);
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.popcap.ea2.EASquared
 * JD-Core Version:    0.7.0.1
 */