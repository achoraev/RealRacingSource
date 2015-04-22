package com.ea.nimble;

import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.Map;

public abstract interface IHttpResponse
{
  public abstract InputStream getDataStream();
  
  public abstract long getDownloadedContentLength();
  
  public abstract Exception getError();
  
  public abstract long getExpectedContentLength();
  
  public abstract Map<String, String> getHeaders();
  
  public abstract Date getLastModified();
  
  public abstract int getStatusCode();
  
  public abstract URL getUrl();
  
  public abstract boolean isCompleted();
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ea.nimble.IHttpResponse
 * JD-Core Version:    0.7.0.1
 */