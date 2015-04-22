package com.google.android.gms.tagmanager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

class aw
  implements bm
{
  private HttpURLConnection apv;
  
  private InputStream a(HttpURLConnection paramHttpURLConnection)
    throws IOException
  {
    int i = paramHttpURLConnection.getResponseCode();
    if (i == 200) {
      return paramHttpURLConnection.getInputStream();
    }
    String str = "Bad response: " + i;
    if (i == 404) {
      throw new FileNotFoundException(str);
    }
    throw new IOException(str);
  }
  
  private void b(HttpURLConnection paramHttpURLConnection)
  {
    if (paramHttpURLConnection != null) {
      paramHttpURLConnection.disconnect();
    }
  }
  
  public InputStream cD(String paramString)
    throws IOException
  {
    this.apv = cE(paramString);
    return a(this.apv);
  }
  
  HttpURLConnection cE(String paramString)
    throws IOException
  {
    HttpURLConnection localHttpURLConnection = (HttpURLConnection)new URL(paramString).openConnection();
    localHttpURLConnection.setReadTimeout(20000);
    localHttpURLConnection.setConnectTimeout(20000);
    return localHttpURLConnection;
  }
  
  public void close()
  {
    b(this.apv);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.aw
 * JD-Core Version:    0.7.0.1
 */