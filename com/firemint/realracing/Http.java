package com.firemint.realracing;

import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.conn.ssl.SSLSocketFactory;

public class Http
{
  private static boolean s_sslContextInit;
  private static String s_userAgent;
  long m_callbackPointer = 0L;
  byte[] m_data = null;
  int m_readCapacity = 0;
  Thread m_thread = null;
  String m_url = "";
  
  static
  {
    if (!Http.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      s_userAgent = null;
      s_sslContextInit = false;
      return;
    }
  }
  
  Http()
  {
    initUserAgent();
    initSSLContext();
  }
  
  private native void completeCallback(long paramLong);
  
  private native void dataCallback(long paramLong, byte[] paramArrayOfByte, int paramInt);
  
  private native void errorCallback(long paramLong);
  
  private native void headerCallback(long paramLong, int paramInt);
  
  private static void initSSLContext()
  {
    if (s_sslContextInit) {
      return;
    }
    Log.i("RealRacing3", "HTTP SSL INIT");
    X509TrustManager local1 = new X509TrustManager()
    {
      public void checkClientTrusted(X509Certificate[] paramAnonymousArrayOfX509Certificate, String paramAnonymousString) {}
      
      public void checkServerTrusted(X509Certificate[] paramAnonymousArrayOfX509Certificate, String paramAnonymousString) {}
      
      public X509Certificate[] getAcceptedIssuers()
      {
        return null;
      }
    };
    try
    {
      TrustManager[] arrayOfTrustManager = { local1 };
      SSLContext localSSLContext = SSLContext.getInstance("TLS");
      localSSLContext.init(null, arrayOfTrustManager, new SecureRandom());
      HttpsURLConnection.setDefaultSSLSocketFactory(localSSLContext.getSocketFactory());
      HttpsURLConnection.setDefaultHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
      s_sslContextInit = true;
      Log.i("RealRacing3", "HTTP SSL DONE");
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Log.e("RealRacing3", "Exception when trying to init SSLContext!");
      }
    }
  }
  
  private static void initUserAgent()
  {
    if (s_userAgent != null) {
      return;
    }
    String str = Platform.getAppName() + "/" + Platform.getAppVersion();
    s_userAgent = str + " " + System.getProperty("http.agent");
  }
  
  void close()
  {
    this.m_callbackPointer = 0L;
    if (this.m_thread != null) {}
    this.m_thread = null;
  }
  
  void init(String paramString, byte[] paramArrayOfByte, int paramInt, long paramLong)
  {
    this.m_url = paramString;
    this.m_data = paramArrayOfByte;
    this.m_readCapacity = paramInt;
    this.m_callbackPointer = paramLong;
    assert (this.m_callbackPointer != 0L);
  }
  
  boolean isClosed()
  {
    return this.m_callbackPointer == 0L;
  }
  
  void post()
  {
    if (this.m_thread != null) {}
    this.m_thread = new HttpThread(null);
    this.m_thread.start();
  }
  
  private class HttpThread
    extends Thread
  {
    private HttpThread() {}
    
    public void run()
    {
      int i = 1;
      for (;;)
      {
        try
        {
          URL localURL = new URL(Http.this.m_url);
          Log.i("RealRacing3", "URL: " + Http.this.m_url);
          localHttpURLConnection = (HttpURLConnection)localURL.openConnection();
          localHttpURLConnection.setConnectTimeout(60000);
          localHttpURLConnection.setUseCaches(false);
          localHttpURLConnection.setDoInput(true);
          localHttpURLConnection.setRequestProperty("User-Agent", Http.s_userAgent);
          if (Http.this.m_data.length > 0)
          {
            localHttpURLConnection.setDoOutput(true);
            localHttpURLConnection.setFixedLengthStreamingMode(Http.this.m_data.length);
            localHttpURLConnection.setRequestMethod("POST");
            localHttpURLConnection.setRequestProperty("Content-Length", Integer.toString(Http.this.m_data.length));
            localHttpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
          }
        }
        catch (MalformedURLException localMalformedURLException)
        {
          HttpURLConnection localHttpURLConnection;
          String str;
          int j;
          InputStream localInputStream;
          byte[] arrayOfByte;
          int k;
          int m;
          Log.e("RealRacing3", "HTTP MalformedURLException: " + localMalformedURLException.getMessage());
          i = 0;
          continue;
        }
        catch (IOException localIOException)
        {
          Log.e("RealRacing3", "HTTP IOException: " + localIOException.getMessage());
          i = 0;
          continue;
        }
        catch (Throwable localThrowable)
        {
          Log.e("RealRacing3", "Other Exception: " + localThrowable.getMessage());
          i = 0;
          continue;
          Http.this.errorCallback(Http.this.m_callbackPointer);
        }
        try
        {
          Log.i("RealRacing3", "HTTP OUTPUT " + Integer.toString(Http.this.m_data.length));
          if (Http.this.m_data.length > 0)
          {
            OutputStream localOutputStream = localHttpURLConnection.getOutputStream();
            localOutputStream.write(Http.this.m_data);
            localOutputStream.flush();
            localOutputStream.close();
          }
          Http.this.m_data = null;
          Log.i("RealRacing3", "HTTP HEADER");
          str = localHttpURLConnection.getHeaderField("Content-Length");
          Log.i("RealRacing3", "HTTP Content-Length: " + str);
          j = 0;
          if (str != null) {
            j = Integer.parseInt(str);
          }
          Http.this.headerCallback(Http.this.m_callbackPointer, j);
          Log.i("RealRacing3", "HTTP INPUT");
          localInputStream = localHttpURLConnection.getInputStream();
          arrayOfByte = new byte[Http.this.m_readCapacity];
          k = 0;
          Log.i("RealRacing3", "HTTP START");
          m = localInputStream.read(arrayOfByte);
          if (m != -1)
          {
            Http.this.dataCallback(Http.this.m_callbackPointer, arrayOfByte, m);
            k += m;
          }
          else
          {
            Log.i("RealRacing3", "HTTP DONE " + Integer.toString(k));
            localInputStream.close();
            localHttpURLConnection.disconnect();
            if (i != 0)
            {
              Http.this.completeCallback(Http.this.m_callbackPointer);
              return;
            }
          }
        }
        finally
        {
          localHttpURLConnection.disconnect();
        }
      }
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.firemint.realracing.Http
 * JD-Core Version:    0.7.0.1
 */