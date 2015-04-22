package com.ea.nimble;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SynergyRequest
  implements ISynergyRequest
{
  public String api;
  public String baseUrl;
  public HttpRequest httpRequest;
  public Map<String, Object> jsonData;
  private SynergyNetworkConnection m_connection;
  public SynergyRequestPreparingCallback prepareRequestCallback;
  public Map<String, String> urlParameters;
  
  public SynergyRequest(String paramString, IHttpRequest.Method paramMethod, SynergyRequestPreparingCallback paramSynergyRequestPreparingCallback)
  {
    this.api = paramString;
    this.httpRequest = new HttpRequest();
    this.prepareRequestCallback = paramSynergyRequestPreparingCallback;
    this.urlParameters = null;
    this.jsonData = null;
    this.httpRequest.method = paramMethod;
    this.httpRequest.headers.put("Content-Type", "application/json");
    this.httpRequest.headers.put("SDK-VERSION", "1.13.1.1009");
    this.httpRequest.headers.put("SDK-TYPE", "Nimble");
    this.httpRequest.headers.put("EAM-USER-ID", SynergyIdManager.getComponent().getSynergyId());
    this.httpRequest.headers.put("EA-SELL-ID", SynergyEnvironment.getComponent().getSellId());
    this.httpRequest.headers.put("EAM-SESSION", ((SynergyNetworkImpl)SynergyNetwork.getComponent()).getSessionId());
  }
  
  void build()
    throws Error
  {
    if ((!Utility.validString(this.baseUrl)) || (!Utility.validString(this.api)))
    {
      Error.Code localCode = Error.Code.INVALID_ARGUMENT;
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = this.baseUrl;
      arrayOfObject[1] = this.api;
      throw new Error(localCode, String.format("Invalid synergy request parameter (%s, %s) to build http request url", arrayOfObject));
    }
    IApplicationEnvironment localIApplicationEnvironment = ApplicationEnvironment.getComponent();
    HashMap localHashMap = new HashMap();
    localHashMap.put("appVer", localIApplicationEnvironment.getApplicationVersion());
    localHashMap.put("appLang", localIApplicationEnvironment.getApplicationLanguageCode());
    String str1 = SynergyEnvironment.getComponent().getEAHardwareId();
    if (Utility.validString(str1)) {
      localHashMap.put("hwId", str1);
    }
    if (this.urlParameters != null) {
      localHashMap.putAll(this.urlParameters);
    }
    this.httpRequest.url = Network.generateURL(this.baseUrl + this.api, localHashMap);
    String str2;
    if (((this.httpRequest.method == IHttpRequest.Method.POST) || (this.httpRequest.method == IHttpRequest.Method.PUT)) && (this.jsonData != null) && (this.jsonData.size() > 0))
    {
      str2 = Utility.convertObjectToJSONString(this.jsonData);
      this.httpRequest.data = new ByteArrayOutputStream();
    }
    try
    {
      this.httpRequest.data.write(str2.getBytes());
      return;
    }
    catch (IOException localIOException)
    {
      throw new Error(Error.Code.INVALID_ARGUMENT, "Error converting jsonData in SynergyRequest to a data stream", localIOException);
    }
  }
  
  public String getApi()
  {
    return this.api;
  }
  
  public String getBaseUrl()
  {
    return this.baseUrl;
  }
  
  public HttpRequest getHttpRequest()
  {
    return this.httpRequest;
  }
  
  public Map<String, Object> getJsonData()
  {
    return this.jsonData;
  }
  
  public IHttpRequest.Method getMethod()
  {
    return this.httpRequest.getMethod();
  }
  
  public Map<String, String> getUrlParameters()
  {
    return this.urlParameters;
  }
  
  void prepare(SynergyNetworkConnection paramSynergyNetworkConnection)
  {
    this.m_connection = paramSynergyNetworkConnection;
    if (this.prepareRequestCallback != null)
    {
      this.prepareRequestCallback.prepareRequest(this);
      return;
    }
    send();
  }
  
  public void send()
  {
    this.m_connection.send();
  }
  
  public void setMethod(IHttpRequest.Method paramMethod)
  {
    this.httpRequest.method = paramMethod;
  }
  
  public static abstract interface SynergyRequestPreparingCallback
  {
    public abstract void prepareRequest(SynergyRequest paramSynergyRequest);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ea.nimble.SynergyRequest
 * JD-Core Version:    0.7.0.1
 */