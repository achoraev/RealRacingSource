package com.ea.nimble;

public abstract interface ILog
{
  public abstract String getLogFilePath();
  
  public abstract int getThresholdLevel();
  
  public abstract void setThresholdLevel(int paramInt);
  
  public abstract void writeWithSource(int paramInt, Object paramObject, String paramString, Object... paramVarArgs);
  
  public abstract void writeWithTitle(int paramInt, String paramString1, String paramString2, Object... paramVarArgs);
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ea.nimble.ILog
 * JD-Core Version:    0.7.0.1
 */