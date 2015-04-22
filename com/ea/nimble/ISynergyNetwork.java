package com.ea.nimble;

import java.util.Map;

public abstract interface ISynergyNetwork
{
  public abstract SynergyNetworkConnectionHandle sendGetRequest(String paramString1, String paramString2, Map<String, String> paramMap, SynergyNetworkConnectionCallback paramSynergyNetworkConnectionCallback);
  
  public abstract SynergyNetworkConnectionHandle sendPostRequest(String paramString1, String paramString2, Map<String, String> paramMap, Map<String, Object> paramMap1, SynergyNetworkConnectionCallback paramSynergyNetworkConnectionCallback);
  
  public abstract SynergyNetworkConnectionHandle sendPostRequest(String paramString1, String paramString2, Map<String, String> paramMap1, Map<String, Object> paramMap, SynergyNetworkConnectionCallback paramSynergyNetworkConnectionCallback, Map<String, String> paramMap2);
  
  public abstract void sendRequest(SynergyRequest paramSynergyRequest, SynergyNetworkConnectionCallback paramSynergyNetworkConnectionCallback);
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ea.nimble.ISynergyNetwork
 * JD-Core Version:    0.7.0.1
 */