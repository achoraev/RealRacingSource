package com.supersonicads.sdk.utils;

import android.util.Log;
import com.supersonicads.sdk.data.SSAEnums.DebugMode;

public class Logger
{
  private static boolean enableLogging;
  
  public static void d(String paramString1, String paramString2)
  {
    if (enableLogging) {
      Log.d(paramString1, paramString2);
    }
  }
  
  public static void d(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (enableLogging) {
      Log.d(paramString1, paramString2, paramThrowable);
    }
  }
  
  public static void e(String paramString1, String paramString2)
  {
    if (enableLogging) {
      Log.e(paramString1, paramString2);
    }
  }
  
  public static void e(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (enableLogging) {
      Log.e(paramString1, paramString2, paramThrowable);
    }
  }
  
  public static void enableLogging(int paramInt)
  {
    if (SSAEnums.DebugMode.MODE_0.getValue() == paramInt)
    {
      enableLogging = false;
      return;
    }
    enableLogging = true;
  }
  
  public static void i(String paramString1, String paramString2)
  {
    if (enableLogging) {
      Log.i(paramString1, paramString2);
    }
  }
  
  public static void i(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (enableLogging) {
      Log.i(paramString1, paramString2, paramThrowable);
    }
  }
  
  public static void v(String paramString1, String paramString2)
  {
    if (enableLogging) {
      Log.v(paramString1, paramString2);
    }
  }
  
  public static void v(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (enableLogging) {
      Log.v(paramString1, paramString2, paramThrowable);
    }
  }
  
  public static void w(String paramString1, String paramString2)
  {
    if (enableLogging) {
      Log.w(paramString1, paramString2);
    }
  }
  
  public static void w(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (enableLogging) {
      Log.w(paramString1, paramString2, paramThrowable);
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.supersonicads.sdk.utils.Logger
 * JD-Core Version:    0.7.0.1
 */