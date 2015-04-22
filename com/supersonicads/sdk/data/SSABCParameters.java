package com.supersonicads.sdk.data;

public class SSABCParameters
  extends SSAObj
{
  private String CONNECTION_RETRIES = "connectionRetries";
  private String mConnectionRetries;
  
  public SSABCParameters() {}
  
  public SSABCParameters(String paramString)
  {
    super(paramString);
    if (containsKey(this.CONNECTION_RETRIES)) {
      setConnectionRetries(getString(this.CONNECTION_RETRIES));
    }
  }
  
  public String getConnectionRetries()
  {
    return this.mConnectionRetries;
  }
  
  public void setConnectionRetries(String paramString)
  {
    this.mConnectionRetries = paramString;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.supersonicads.sdk.data.SSABCParameters
 * JD-Core Version:    0.7.0.1
 */