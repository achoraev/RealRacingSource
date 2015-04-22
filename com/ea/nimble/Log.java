package com.ea.nimble;

public class Log
{
  public static final String COMPONENT_ID = "com.ea.nimble.NimbleLog";
  public static final int LEVEL_DEBUG = 200;
  public static final int LEVEL_ERROR = 500;
  public static final int LEVEL_FATAL = 600;
  public static final int LEVEL_INFO = 300;
  public static final int LEVEL_VERBOSE = 100;
  public static final int LEVEL_WARN = 400;
  private static ILog s_instance;
  
  public static ILog getComponent()
  {
    try
    {
      if (s_instance == null) {
        s_instance = new LogImpl();
      }
      ILog localILog = s_instance;
      return localILog;
    }
    finally {}
  }
  
  public static class Helper
  {
    public static void LOG(int paramInt, String paramString, Object... paramVarArgs)
    {
      Log.getComponent().writeWithTitle(paramInt, null, paramString, paramVarArgs);
    }
    
    public static void LOGD(Object paramObject, String paramString, Object... paramVarArgs)
    {
      Log.getComponent().writeWithSource(200, paramObject, paramString, paramVarArgs);
    }
    
    public static void LOGDS(String paramString1, String paramString2, Object... paramVarArgs)
    {
      Log.getComponent().writeWithTitle(200, paramString1, paramString2, paramVarArgs);
    }
    
    public static void LOGE(Object paramObject, String paramString, Object... paramVarArgs)
    {
      Log.getComponent().writeWithSource(500, paramObject, paramString, paramVarArgs);
    }
    
    public static void LOGES(String paramString1, String paramString2, Object... paramVarArgs)
    {
      Log.getComponent().writeWithTitle(500, paramString1, paramString2, paramVarArgs);
    }
    
    public static void LOGF(Object paramObject, String paramString, Object... paramVarArgs)
    {
      Log.getComponent().writeWithSource(600, paramObject, paramString, paramVarArgs);
    }
    
    public static void LOGFS(String paramString1, String paramString2, Object... paramVarArgs)
    {
      Log.getComponent().writeWithTitle(600, paramString1, paramString2, paramVarArgs);
    }
    
    public static void LOGI(Object paramObject, String paramString, Object... paramVarArgs)
    {
      Log.getComponent().writeWithSource(300, paramObject, paramString, paramVarArgs);
    }
    
    public static void LOGIS(String paramString1, String paramString2, Object... paramVarArgs)
    {
      Log.getComponent().writeWithTitle(300, paramString1, paramString2, paramVarArgs);
    }
    
    public static void LOGV(Object paramObject, String paramString, Object... paramVarArgs)
    {
      Log.getComponent().writeWithSource(100, paramObject, paramString, paramVarArgs);
    }
    
    public static void LOGVS(String paramString1, String paramString2, Object... paramVarArgs)
    {
      Log.getComponent().writeWithTitle(100, paramString1, paramString2, paramVarArgs);
    }
    
    public static void LOGW(Object paramObject, String paramString, Object... paramVarArgs)
    {
      Log.getComponent().writeWithSource(400, paramObject, paramString, paramVarArgs);
    }
    
    public static void LOGWS(String paramString1, String paramString2, Object... paramVarArgs)
    {
      Log.getComponent().writeWithTitle(400, paramString1, paramString2, paramVarArgs);
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ea.nimble.Log
 * JD-Core Version:    0.7.0.1
 */