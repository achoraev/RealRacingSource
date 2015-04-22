package com.ea.nimble;

import java.util.Map;

public abstract interface ISynergyResponse
{
  public abstract Exception getError();
  
  public abstract IHttpResponse getHttpResponse();
  
  public abstract Map<String, Object> getJsonData();
  
  public abstract boolean isCompleted();
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ea.nimble.ISynergyResponse
 * JD-Core Version:    0.7.0.1
 */