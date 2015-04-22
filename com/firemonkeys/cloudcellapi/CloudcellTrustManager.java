package com.firemonkeys.cloudcellapi;

import android.util.Log;
import java.security.cert.X509Certificate;
import java.util.Date;
import javax.net.ssl.X509TrustManager;

class CloudcellTrustManager
  implements X509TrustManager
{
  private CC_HttpRequest_Class m_httpRequest = null;
  
  public CloudcellTrustManager(CC_HttpRequest_Class paramCC_HttpRequest_Class)
  {
    this.m_httpRequest = paramCC_HttpRequest_Class;
  }
  
  public void checkClientTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString) {}
  
  public void checkServerTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString)
  {
    if (getSSLCheck()) {}
    for (;;)
    {
      int i;
      try
      {
        long l1 = Double.valueOf(getServerTime()).longValue();
        long l3;
        if (l1 > 0L)
        {
          l3 = 1000L * l1;
          Date localDate1 = new Date(l3);
          if (paramArrayOfX509Certificate != null)
          {
            i = 0;
            if (i < paramArrayOfX509Certificate.length)
            {
              Date localDate2 = paramArrayOfX509Certificate[i].getNotAfter();
              if (!localDate2.before(localDate1)) {
                break label146;
              }
              setClosedBySSLCheck(true);
              closeThread();
              Log.e("Cloudcell", "SSL Certificate expired! notAfter = " + localDate2.toString());
              break label146;
            }
          }
        }
        else
        {
          long l2 = System.currentTimeMillis();
          l3 = l2;
          continue;
        }
        this.m_httpRequest = null;
      }
      catch (Exception localException)
      {
        Log.w("Cloudcell", "the certificate chain can't be validated");
      }
      return;
      label146:
      i++;
    }
  }
  
  public void closeThread()
  {
    if (this.m_httpRequest != null) {
      this.m_httpRequest.shutdown();
    }
  }
  
  public X509Certificate[] getAcceptedIssuers()
  {
    return null;
  }
  
  public boolean getSSLCheck()
  {
    if (this.m_httpRequest != null) {
      return this.m_httpRequest.getSSLCheck();
    }
    return false;
  }
  
  public double getServerTime()
  {
    if (this.m_httpRequest != null) {
      return this.m_httpRequest.getServerTime();
    }
    return 0.0D;
  }
  
  public void setClosedBySSLCheck(boolean paramBoolean)
  {
    if (this.m_httpRequest != null) {
      this.m_httpRequest.setClosedBySSLCheck(paramBoolean);
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.firemonkeys.cloudcellapi.CloudcellTrustManager
 * JD-Core Version:    0.7.0.1
 */