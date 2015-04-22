package com.supersonicads.sdk.session;

import android.content.Context;
import com.supersonicads.sdk.utils.SDKUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class SSASession
{
  public final String CONNECTIVITY = "connectivity";
  public final String SESSION_END_TIME = "sessionEndTime";
  public final String SESSION_START_TIME = "sessionStartTime";
  public final String SESSION_TYPE = "sessionType";
  private String connectivity;
  private long sessionEndTime;
  private long sessionStartTime;
  private SessionType sessionType;
  
  public SSASession(Context paramContext, SessionType paramSessionType)
  {
    setSessionStartTime(SDKUtils.getCurrentTimeMillis().longValue());
    setSessionType(paramSessionType);
    setConnectivity(SDKUtils.getConnectionType(paramContext));
  }
  
  public SSASession(JSONObject paramJSONObject)
  {
    try
    {
      paramJSONObject.get("sessionStartTime");
      paramJSONObject.get("sessionEndTime");
      paramJSONObject.get("sessionType");
      paramJSONObject.get("connectivity");
      return;
    }
    catch (JSONException localJSONException) {}
  }
  
  public void endSession()
  {
    setSessionEndTime(SDKUtils.getCurrentTimeMillis().longValue());
  }
  
  public String getConnectivity()
  {
    return this.connectivity;
  }
  
  public long getSessionEndTime()
  {
    return this.sessionEndTime;
  }
  
  public long getSessionStartTime()
  {
    return this.sessionStartTime;
  }
  
  public SessionType getSessionType()
  {
    return this.sessionType;
  }
  
  public void setConnectivity(String paramString)
  {
    this.connectivity = paramString;
  }
  
  public void setSessionEndTime(long paramLong)
  {
    this.sessionEndTime = paramLong;
  }
  
  public void setSessionStartTime(long paramLong)
  {
    this.sessionStartTime = paramLong;
  }
  
  public void setSessionType(SessionType paramSessionType)
  {
    this.sessionType = paramSessionType;
  }
  
  public static enum SessionType
  {
    backFromBG,  launched;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.supersonicads.sdk.session.SSASession
 * JD-Core Version:    0.7.0.1
 */