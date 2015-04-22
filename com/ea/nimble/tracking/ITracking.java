package com.ea.nimble.tracking;

import java.util.Map;

public abstract interface ITracking
{
  public abstract void addCustomSessionData(String paramString1, String paramString2);
  
  public abstract void clearCustomSessionData();
  
  public abstract boolean getEnable();
  
  public abstract void logEvent(String paramString, Map<String, String> paramMap);
  
  public abstract void setEnable(boolean paramBoolean);
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ea.nimble.tracking.ITracking
 * JD-Core Version:    0.7.0.1
 */