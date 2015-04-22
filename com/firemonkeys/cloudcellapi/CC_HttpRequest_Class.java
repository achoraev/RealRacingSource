package com.firemonkeys.cloudcellapi;

import android.util.Log;
import java.security.SecureRandom;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import org.apache.http.conn.ssl.SSLSocketFactory;

public class CC_HttpRequest_Class
{
  private static boolean s_sslContextInit = false;
  public static String s_userAgent = null;
  private boolean m_bSSLCheck = false;
  private double m_serverTime = 0.0D;
  HttpThread m_thread = null;
  
  private void initSSLContext()
  {
    try
    {
      SSLContext localSSLContext = SSLContext.getInstance("TLS");
      TrustManager[] arrayOfTrustManager = new TrustManager[1];
      arrayOfTrustManager[0] = new CloudcellTrustManager(this);
      localSSLContext.init(null, arrayOfTrustManager, new SecureRandom());
      HttpsURLConnection.setDefaultSSLSocketFactory(localSSLContext.getSocketFactory());
      HttpsURLConnection.setDefaultHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
      return;
    }
    catch (Exception localException)
    {
      Log.e("Cloudcell", "Exception when trying to init SSLContext!");
    }
  }
  
  private static void initUserAgent(String paramString)
  {
    if (s_userAgent != null) {
      return;
    }
    s_userAgent = paramString + " " + System.getProperty("http.agent");
    Log.i("Clodcell", "User Agent: " + s_userAgent);
  }
  
  void addHeader(String paramString1, String paramString2)
  {
    this.m_thread.addHeader(paramString1, paramString2);
  }
  
  void close()
  {
    if (this.m_thread != null) {
      this.m_thread.interrupt();
    }
    this.m_thread = null;
  }
  
  public native void completeCallback(long paramLong, int paramInt);
  
  public native void dataCallback(long paramLong, byte[] paramArrayOfByte, int paramInt);
  
  public native void errorCallback(long paramLong, int paramInt);
  
  boolean getSSLCheck()
  {
    return this.m_bSSLCheck;
  }
  
  double getServerTime()
  {
    return this.m_serverTime;
  }
  
  public native void headerCallback(long paramLong, int paramInt, Map<String, List<String>> paramMap);
  
  void init(String paramString1, String paramString2, String paramString3, byte[] paramArrayOfByte, int paramInt, long paramLong, boolean paramBoolean1, double paramDouble, boolean paramBoolean2)
  {
    this.m_serverTime = paramDouble;
    this.m_bSSLCheck = paramBoolean2;
    initSSLContext();
    initUserAgent(paramString1);
    this.m_thread = new HttpThread(this, paramString2, paramString3, paramArrayOfByte, paramInt, paramLong, paramBoolean1);
  }
  
  boolean isClosed()
  {
    return this.m_thread == null;
  }
  
  void post()
  {
    if (this.m_thread == null)
    {
      Log.e("Cloudcell", "CC_HttpRequest_Class::post() called but thread is already closed");
      return;
    }
    if (this.m_thread.isAlive())
    {
      Log.e("Cloudcell", "CC_HttpRequest_Class::post() called but thread is already running");
      return;
    }
    this.m_thread.start();
  }
  
  void setClosedBySSLCheck(boolean paramBoolean)
  {
    this.m_thread.setClosedBySSLCheck(paramBoolean);
  }
  
  void shutdown()
  {
    if (this.m_thread != null) {
      this.m_thread.shutdown();
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.firemonkeys.cloudcellapi.CC_HttpRequest_Class
 * JD-Core Version:    0.7.0.1
 */