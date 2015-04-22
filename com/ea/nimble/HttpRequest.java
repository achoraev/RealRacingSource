package com.ea.nimble;

import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.util.EnumSet;
import java.util.HashMap;

public class HttpRequest
  implements IHttpRequest
{
  private static int DEFAULT_NETWORK_TIMEOUT = 30;
  public ByteArrayOutputStream data = new ByteArrayOutputStream();
  public HashMap<String, String> headers = new HashMap();
  public IHttpRequest.Method method = IHttpRequest.Method.GET;
  public EnumSet<IHttpRequest.OverwritePolicy> overwritePolicy = IHttpRequest.OverwritePolicy.SMART;
  public boolean runInBackground;
  public String targetFilePath;
  public double timeout = DEFAULT_NETWORK_TIMEOUT;
  public URL url = null;
  
  public HttpRequest() {}
  
  public HttpRequest(URL paramURL)
  {
    this();
    this.url = paramURL;
  }
  
  public byte[] getData()
  {
    return this.data.toByteArray();
  }
  
  public HashMap<String, String> getHeaders()
  {
    return this.headers;
  }
  
  public IHttpRequest.Method getMethod()
  {
    return this.method;
  }
  
  public EnumSet<IHttpRequest.OverwritePolicy> getOverwritePolicy()
  {
    return this.overwritePolicy;
  }
  
  public String getTargetFilePath()
  {
    return this.targetFilePath;
  }
  
  public double getTimeout()
  {
    return this.timeout;
  }
  
  public URL getUrl()
  {
    return this.url;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ea.nimble.HttpRequest
 * JD-Core Version:    0.7.0.1
 */