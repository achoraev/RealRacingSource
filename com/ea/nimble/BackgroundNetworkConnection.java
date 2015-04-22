package com.ea.nimble;

public class BackgroundNetworkConnection
  extends NetworkConnection
{
  public BackgroundNetworkConnection(NetworkImpl paramNetworkImpl, HttpRequest paramHttpRequest)
  {
    super(paramNetworkImpl, paramHttpRequest);
  }
  
  public void suspend() {}
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ea.nimble.BackgroundNetworkConnection
 * JD-Core Version:    0.7.0.1
 */