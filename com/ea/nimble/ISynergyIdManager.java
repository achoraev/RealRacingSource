package com.ea.nimble;

public abstract interface ISynergyIdManager
{
  public abstract String getAnonymousSynergyId();
  
  public abstract String getSynergyId();
  
  public abstract SynergyIdManagerError login(String paramString1, String paramString2);
  
  public abstract SynergyIdManagerError logout(String paramString);
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ea.nimble.ISynergyIdManager
 * JD-Core Version:    0.7.0.1
 */