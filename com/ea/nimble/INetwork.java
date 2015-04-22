package com.ea.nimble;

import java.net.URL;
import java.util.HashMap;

public abstract interface INetwork
{
  public abstract void forceRedetectNetworkStatus();
  
  public abstract Network.Status getStatus();
  
  public abstract boolean isNetworkWifi();
  
  public abstract NetworkConnectionHandle sendDeleteRequest(URL paramURL, HashMap<String, String> paramHashMap, NetworkConnectionCallback paramNetworkConnectionCallback);
  
  public abstract NetworkConnectionHandle sendGetRequest(URL paramURL, HashMap<String, String> paramHashMap, NetworkConnectionCallback paramNetworkConnectionCallback);
  
  public abstract NetworkConnectionHandle sendPostRequest(URL paramURL, HashMap<String, String> paramHashMap, byte[] paramArrayOfByte, NetworkConnectionCallback paramNetworkConnectionCallback);
  
  public abstract NetworkConnectionHandle sendRequest(HttpRequest paramHttpRequest, NetworkConnectionCallback paramNetworkConnectionCallback);
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ea.nimble.INetwork
 * JD-Core Version:    0.7.0.1
 */