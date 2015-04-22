package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Locale;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;

class db
  implements ab
{
  private final String arH;
  private final HttpClient arI;
  private a arJ;
  private final Context arq;
  
  db(HttpClient paramHttpClient, Context paramContext, a parama)
  {
    this.arq = paramContext.getApplicationContext();
    this.arH = a("GoogleTagManager", "4.00", Build.VERSION.RELEASE, c(Locale.getDefault()), Build.MODEL, Build.ID);
    this.arI = paramHttpClient;
    this.arJ = parama;
  }
  
  private HttpEntityEnclosingRequest a(URL paramURL)
  {
    try
    {
      localBasicHttpEntityEnclosingRequest = new BasicHttpEntityEnclosingRequest("GET", paramURL.toURI().toString());
      bh.W("Exception sending hit: " + localURISyntaxException1.getClass().getSimpleName());
    }
    catch (URISyntaxException localURISyntaxException3)
    {
      try
      {
        localBasicHttpEntityEnclosingRequest.addHeader("User-Agent", this.arH);
        return localBasicHttpEntityEnclosingRequest;
      }
      catch (URISyntaxException localURISyntaxException2)
      {
        BasicHttpEntityEnclosingRequest localBasicHttpEntityEnclosingRequest;
        URISyntaxException localURISyntaxException1;
        break label38;
      }
      localURISyntaxException3 = localURISyntaxException3;
      localBasicHttpEntityEnclosingRequest = null;
      localURISyntaxException1 = localURISyntaxException3;
    }
    label38:
    bh.W(localURISyntaxException1.getMessage());
    return localBasicHttpEntityEnclosingRequest;
  }
  
  private void a(HttpEntityEnclosingRequest paramHttpEntityEnclosingRequest)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    Header[] arrayOfHeader = paramHttpEntityEnclosingRequest.getAllHeaders();
    int i = arrayOfHeader.length;
    for (int j = 0; j < i; j++) {
      localStringBuffer.append(arrayOfHeader[j].toString()).append("\n");
    }
    localStringBuffer.append(paramHttpEntityEnclosingRequest.getRequestLine().toString()).append("\n");
    if (paramHttpEntityEnclosingRequest.getEntity() != null) {}
    try
    {
      InputStream localInputStream = paramHttpEntityEnclosingRequest.getEntity().getContent();
      if (localInputStream != null)
      {
        int k = localInputStream.available();
        if (k > 0)
        {
          byte[] arrayOfByte = new byte[k];
          localInputStream.read(arrayOfByte);
          localStringBuffer.append("POST:\n");
          localStringBuffer.append(new String(arrayOfByte)).append("\n");
        }
      }
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        bh.V("Error Writing hit to log...");
      }
    }
    bh.V(localStringBuffer.toString());
  }
  
  static String c(Locale paramLocale)
  {
    if (paramLocale == null) {}
    while ((paramLocale.getLanguage() == null) || (paramLocale.getLanguage().length() == 0)) {
      return null;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramLocale.getLanguage().toLowerCase());
    if ((paramLocale.getCountry() != null) && (paramLocale.getCountry().length() != 0)) {
      localStringBuilder.append("-").append(paramLocale.getCountry().toLowerCase());
    }
    return localStringBuilder.toString();
  }
  
  String a(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6)
  {
    return String.format("%s/%s (Linux; U; Android %s; %s; %s Build/%s)", new Object[] { paramString1, paramString2, paramString3, paramString4, paramString5, paramString6 });
  }
  
  URL d(ap paramap)
  {
    String str = paramap.ou();
    try
    {
      URL localURL = new URL(str);
      return localURL;
    }
    catch (MalformedURLException localMalformedURLException)
    {
      bh.T("Error trying to parse the GTM url.");
    }
    return null;
  }
  
  public boolean dX()
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager)this.arq.getSystemService("connectivity")).getActiveNetworkInfo();
    if ((localNetworkInfo == null) || (!localNetworkInfo.isConnected()))
    {
      bh.V("...no network connectivity");
      return false;
    }
    return true;
  }
  
  public void j(List<ap> paramList)
  {
    int i = Math.min(paramList.size(), 40);
    int j = 1;
    int k = 0;
    ap localap;
    URL localURL;
    int m;
    if (k < i)
    {
      localap = (ap)paramList.get(k);
      localURL = d(localap);
      if (localURL == null)
      {
        bh.W("No destination: discarding hit.");
        this.arJ.b(localap);
        m = j;
      }
    }
    for (;;)
    {
      k++;
      j = m;
      break;
      HttpEntityEnclosingRequest localHttpEntityEnclosingRequest = a(localURL);
      if (localHttpEntityEnclosingRequest == null)
      {
        this.arJ.b(localap);
        m = j;
      }
      else
      {
        HttpHost localHttpHost = new HttpHost(localURL.getHost(), localURL.getPort(), localURL.getProtocol());
        localHttpEntityEnclosingRequest.addHeader("Host", localHttpHost.toHostString());
        a(localHttpEntityEnclosingRequest);
        if (j != 0) {}
        try
        {
          bo.A(this.arq);
          j = 0;
          HttpResponse localHttpResponse = this.arI.execute(localHttpHost, localHttpEntityEnclosingRequest);
          int n = localHttpResponse.getStatusLine().getStatusCode();
          HttpEntity localHttpEntity = localHttpResponse.getEntity();
          if (localHttpEntity != null) {
            localHttpEntity.consumeContent();
          }
          if (n != 200)
          {
            bh.W("Bad response: " + localHttpResponse.getStatusLine().getStatusCode());
            this.arJ.c(localap);
          }
          else
          {
            this.arJ.a(localap);
          }
        }
        catch (ClientProtocolException localClientProtocolException)
        {
          bh.W("ClientProtocolException sending hit; discarding hit...");
          this.arJ.b(localap);
          m = j;
          continue;
        }
        catch (IOException localIOException)
        {
          bh.W("Exception sending hit: " + localIOException.getClass().getSimpleName());
          bh.W(localIOException.getMessage());
          this.arJ.c(localap);
          m = j;
        }
        continue;
        return;
        m = j;
      }
    }
  }
  
  public static abstract interface a
  {
    public abstract void a(ap paramap);
    
    public abstract void b(ap paramap);
    
    public abstract void c(ap paramap);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.db
 * JD-Core Version:    0.7.0.1
 */