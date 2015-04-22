package com.millennialmedia.android;

import android.util.Log;

public class MMLog
{
  static
  {
    ComponentRegistry.addLoggingComponent(new LoggingComponent());
  }
  
  static void d(String paramString1, String paramString2)
  {
    ComponentRegistry.getLoggingComponent().d(paramString1, paramString2);
  }
  
  static void d(String paramString1, String paramString2, Throwable paramThrowable)
  {
    ComponentRegistry.getLoggingComponent().d(paramString1, paramString2, paramThrowable);
  }
  
  static void e(String paramString1, String paramString2)
  {
    ComponentRegistry.getLoggingComponent().e(paramString1, paramString2);
  }
  
  static void e(String paramString1, String paramString2, Throwable paramThrowable)
  {
    ComponentRegistry.getLoggingComponent().e(paramString1, paramString2, paramThrowable);
  }
  
  public static int getLogLevel()
  {
    return ComponentRegistry.getLoggingComponent().getLogLevel();
  }
  
  static void i(String paramString1, String paramString2)
  {
    ComponentRegistry.getLoggingComponent().i(paramString1, paramString2);
  }
  
  static void i(String paramString1, String paramString2, Throwable paramThrowable)
  {
    ComponentRegistry.getLoggingComponent().i(paramString1, paramString2, paramThrowable);
  }
  
  static void registerLogHandler(LogHandler paramLogHandler)
  {
    ComponentRegistry.getLoggingComponent().registerLogHandler(paramLogHandler);
  }
  
  public static void setLogLevel(int paramInt)
  {
    ComponentRegistry.getLoggingComponent().setLogLevel(paramInt);
  }
  
  static void v(String paramString1, String paramString2)
  {
    ComponentRegistry.getLoggingComponent().v(paramString1, paramString2);
  }
  
  static void v(String paramString1, String paramString2, Throwable paramThrowable)
  {
    ComponentRegistry.getLoggingComponent().v(paramString1, paramString2, paramThrowable);
  }
  
  static void w(String paramString1, String paramString2)
  {
    ComponentRegistry.getLoggingComponent().w(paramString1, paramString2);
  }
  
  static void w(String paramString1, String paramString2, Throwable paramThrowable)
  {
    ComponentRegistry.getLoggingComponent().w(paramString1, paramString2, paramThrowable);
  }
  
  static abstract interface LogHandler
  {
    public abstract void handleLog(String paramString);
  }
  
  static class LoggingComponent
  {
    private static final String TAG_STARTER = "MMSDK-";
    private static int logLevel = 4;
    private MMLog.LogHandler registeredLogHandler;
    
    private void callLogHandler(String paramString)
    {
      if (this.registeredLogHandler != null) {
        this.registeredLogHandler.handleLog(paramString);
      }
    }
    
    private void dInternal(String paramString1, String paramString2)
    {
      Log.d("MMSDK-" + paramString1, paramString2);
      callLogHandler(paramString2);
    }
    
    private void eInternal(String paramString1, String paramString2)
    {
      Log.e("MMSDK-" + paramString1, paramString2);
      callLogHandler(paramString2);
    }
    
    private void iInternal(String paramString1, String paramString2)
    {
      Log.i("MMSDK-" + paramString1, paramString2);
      callLogHandler(paramString2);
    }
    
    private void vInternal(String paramString1, String paramString2)
    {
      Log.v("MMSDK-" + paramString1, paramString2);
      callLogHandler(paramString2);
    }
    
    private void wInternal(String paramString1, String paramString2)
    {
      Log.w("MMSDK-" + paramString1, paramString2);
      callLogHandler(paramString2);
    }
    
    void d(String paramString1, String paramString2)
    {
      if (logLevel <= 3) {
        dInternal(paramString1, paramString2);
      }
    }
    
    void d(String paramString1, String paramString2, Throwable paramThrowable)
    {
      if (logLevel <= 3) {
        dInternal(paramString1, paramString2 + ": " + Log.getStackTraceString(paramThrowable));
      }
    }
    
    void e(String paramString1, String paramString2)
    {
      if (logLevel <= 6) {
        eInternal(paramString1, paramString2);
      }
    }
    
    void e(String paramString1, String paramString2, Throwable paramThrowable)
    {
      if (logLevel <= 6) {
        eInternal(paramString1, paramString2 + ": " + Log.getStackTraceString(paramThrowable));
      }
    }
    
    public int getLogLevel()
    {
      return logLevel;
    }
    
    void i(String paramString1, String paramString2)
    {
      if (logLevel <= 4) {
        iInternal(paramString1, paramString2);
      }
    }
    
    void i(String paramString1, String paramString2, Throwable paramThrowable)
    {
      if (logLevel <= 4) {
        iInternal(paramString1, paramString2 + ": " + Log.getStackTraceString(paramThrowable));
      }
    }
    
    void registerLogHandler(MMLog.LogHandler paramLogHandler)
    {
      this.registeredLogHandler = paramLogHandler;
    }
    
    public void setLogLevel(int paramInt)
    {
      logLevel = paramInt;
    }
    
    void v(String paramString1, String paramString2)
    {
      if (logLevel <= 2) {
        vInternal(paramString1, paramString2);
      }
    }
    
    void v(String paramString1, String paramString2, Throwable paramThrowable)
    {
      if (logLevel <= 2) {
        vInternal(paramString1, paramString2 + ": " + Log.getStackTraceString(paramThrowable));
      }
    }
    
    void w(String paramString1, String paramString2)
    {
      if (logLevel <= 5) {
        wInternal(paramString1, paramString2);
      }
    }
    
    void w(String paramString1, String paramString2, Throwable paramThrowable)
    {
      if (logLevel <= 5) {
        wInternal(paramString1, paramString2 + ": " + Log.getStackTraceString(paramThrowable));
      }
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.MMLog
 * JD-Core Version:    0.7.0.1
 */