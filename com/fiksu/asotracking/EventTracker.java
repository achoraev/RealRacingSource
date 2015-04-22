package com.fiksu.asotracking;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.HashMap;

abstract class EventTracker
{
  private static Context mCachedContext = null;
  protected Context mContext = null;
  private final HashMap<String, String> mParameters;
  
  protected EventTracker(Context paramContext, String paramString)
  {
    FiksuConfigurationManager.getInstance().updateConfiguration(paramContext);
    this.mParameters = new HashMap();
    addParameter(FiksuEventParameter.EVENT, paramString);
    if (paramContext != null) {
      mCachedContext = paramContext.getApplicationContext();
    }
    this.mContext = mCachedContext;
  }
  
  private HashMap<String, String> copyOfParams()
  {
    return new HashMap(this.mParameters);
  }
  
  protected static SharedPreferences getOurSharedPreferences(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    return paramContext.getSharedPreferences("FiksuSharedPreferences", 0);
  }
  
  protected void addParameter(FiksuEventParameter paramFiksuEventParameter, String paramString)
  {
    this.mParameters.put(paramFiksuEventParameter.getName(), paramString);
  }
  
  protected void uploadEvent()
  {
    new Thread(new EventUploader(this.mContext, copyOfParams())).start();
  }
  
  protected void uploadEventSynchronously(long paramLong)
  {
    synchronized (new EventUploader(this.mContext, copyOfParams()))
    {
      new Thread(???).start();
    }
    try
    {
      ???.wait(paramLong);
      label34:
      return;
      localObject = finally;
      throw localObject;
    }
    catch (InterruptedException localInterruptedException)
    {
      break label34;
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.fiksu.asotracking.EventTracker
 * JD-Core Version:    0.7.0.1
 */