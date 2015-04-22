package com.ea.nimble;

import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class HttpResponse
  implements IHttpResponse
{
  public ByteBufferIOStream data = new ByteBufferIOStream();
  public long downloadedContentLength = 0L;
  public Exception error;
  public long expectedContentLength = 0L;
  public HashMap<String, String> headers = new HashMap();
  public boolean isCompleted = false;
  public long lastModified = -1L;
  public int statusCode = 0;
  public URL url = null;
  
  public InputStream getDataStream()
  {
    return this.data.getInputStream();
  }
  
  public long getDownloadedContentLength()
  {
    return this.downloadedContentLength;
  }
  
  public Exception getError()
  {
    return this.error;
  }
  
  public long getExpectedContentLength()
  {
    return this.expectedContentLength;
  }
  
  public Map<String, String> getHeaders()
  {
    return this.headers;
  }
  
  public Date getLastModified()
  {
    if (this.lastModified == 0L) {
      return new Date();
    }
    if (this.lastModified > 0L) {
      return new Date(this.lastModified);
    }
    return null;
  }
  
  public int getStatusCode()
  {
    return this.statusCode;
  }
  
  public URL getUrl()
  {
    return this.url;
  }
  
  public boolean isCompleted()
  {
    return this.isCompleted;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ea.nimble.HttpResponse
 * JD-Core Version:    0.7.0.1
 */