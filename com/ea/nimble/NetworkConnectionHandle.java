package com.ea.nimble;

public abstract interface NetworkConnectionHandle
{
  public abstract void cancel();
  
  public abstract NetworkConnectionCallback getCompletionCallback();
  
  public abstract NetworkConnectionCallback getHeaderCallback();
  
  public abstract NetworkConnectionCallback getProgressCallback();
  
  public abstract IHttpRequest getRequest();
  
  public abstract IHttpResponse getResponse();
  
  public abstract void setCompletionCallback(NetworkConnectionCallback paramNetworkConnectionCallback);
  
  public abstract void setHeaderCallback(NetworkConnectionCallback paramNetworkConnectionCallback);
  
  public abstract void setProgressCallback(NetworkConnectionCallback paramNetworkConnectionCallback);
  
  public abstract void waitOn();
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ea.nimble.NetworkConnectionHandle
 * JD-Core Version:    0.7.0.1
 */