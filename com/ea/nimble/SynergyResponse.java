package com.ea.nimble;

import java.util.Map;
import org.json.JSONObject;

public class SynergyResponse
  implements ISynergyResponse
{
  public Error error = null;
  public IHttpResponse httpResponse = null;
  public Map<String, Object> jsonData = null;
  
  public Exception getError()
  {
    if ((this.error != null) || (this.httpResponse == null)) {
      return this.error;
    }
    return this.httpResponse.getError();
  }
  
  public IHttpResponse getHttpResponse()
  {
    return this.httpResponse;
  }
  
  public Map<String, Object> getJsonData()
  {
    return this.jsonData;
  }
  
  public boolean isCompleted()
  {
    if (this.httpResponse == null) {
      return false;
    }
    return this.httpResponse.isCompleted();
  }
  
  public void parseData()
  {
    if ((this.httpResponse != null) && (this.httpResponse.getError() == null))
    {
      String str = "<empty>";
      try
      {
        str = Utility.readStringFromStream(this.httpResponse.getDataStream());
        this.jsonData = Utility.convertJSONObjectToMap(new JSONObject(str));
        if (this.jsonData.containsKey("resultCode"))
        {
          int i = ((Integer)this.jsonData.get("resultCode")).intValue();
          if (i < 0) {
            this.error = new SynergyServerError(i, (String)this.jsonData.get("message"));
          }
        }
        return;
      }
      catch (Exception localException)
      {
        this.jsonData = null;
        this.error = new Error(Error.Code.NETWORK_INVALID_SERVER_RESPONSE, "Unparseable synergy json response " + str);
        return;
      }
    }
    this.jsonData = null;
    this.error = null;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ea.nimble.SynergyResponse
 * JD-Core Version:    0.7.0.1
 */