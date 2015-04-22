package com.ea.nimble;

public abstract interface SynergyNetworkConnectionHandle
{
  public abstract void cancel();
  
  public abstract SynergyNetworkConnectionCallback getCompletionCallback();
  
  public abstract SynergyNetworkConnectionCallback getHeaderCallback();
  
  public abstract SynergyNetworkConnectionCallback getProgressCallback();
  
  public abstract ISynergyRequest getRequest();
  
  public abstract ISynergyResponse getResponse();
  
  public abstract void setCompletionCallback(SynergyNetworkConnectionCallback paramSynergyNetworkConnectionCallback);
  
  public abstract void setHeaderCallback(SynergyNetworkConnectionCallback paramSynergyNetworkConnectionCallback);
  
  public abstract void setProgressCallback(SynergyNetworkConnectionCallback paramSynergyNetworkConnectionCallback);
  
  public abstract void waitOn();
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ea.nimble.SynergyNetworkConnectionHandle
 * JD-Core Version:    0.7.0.1
 */