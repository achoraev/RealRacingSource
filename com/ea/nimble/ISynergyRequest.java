package com.ea.nimble;

import java.util.Map;

public abstract interface ISynergyRequest
{
  public abstract String getApi();
  
  public abstract String getBaseUrl();
  
  public abstract IHttpRequest getHttpRequest();
  
  public abstract Map<String, Object> getJsonData();
  
  public abstract Map<String, String> getUrlParameters();
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ea.nimble.ISynergyRequest
 * JD-Core Version:    0.7.0.1
 */