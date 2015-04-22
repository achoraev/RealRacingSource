package com.ultra.sdk.volley.toolbox;

import com.ultra.sdk.volley.AuthFailureError;
import com.ultra.sdk.volley.Request;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class HttpClientStack
  implements HttpStack
{
  private static final String HEADER_CONTENT_TYPE = "Content-Type";
  protected final HttpClient mClient;
  
  public HttpClientStack(HttpClient paramHttpClient)
  {
    this.mClient = paramHttpClient;
  }
  
  private static void addHeaders(HttpUriRequest paramHttpUriRequest, Map<String, String> paramMap)
  {
    Iterator localIterator = paramMap.keySet().iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return;
      }
      String str = (String)localIterator.next();
      paramHttpUriRequest.setHeader(str, (String)paramMap.get(str));
    }
  }
  
  static HttpUriRequest createHttpRequest(Request<?> paramRequest, Map<String, String> paramMap)
    throws AuthFailureError
  {
    switch (paramRequest.getMethod())
    {
    default: 
      throw new IllegalStateException("Unknown request method.");
    case -1: 
      byte[] arrayOfByte = paramRequest.getPostBody();
      if (arrayOfByte != null)
      {
        HttpPost localHttpPost2 = new HttpPost(paramRequest.getUrl());
        localHttpPost2.addHeader("Content-Type", paramRequest.getPostBodyContentType());
        localHttpPost2.setEntity(new ByteArrayEntity(arrayOfByte));
        return localHttpPost2;
      }
      return new HttpGet(paramRequest.getUrl());
    case 0: 
      return new HttpGet(paramRequest.getUrl());
    case 3: 
      return new HttpDelete(paramRequest.getUrl());
    case 1: 
      HttpPost localHttpPost1 = new HttpPost(paramRequest.getUrl());
      localHttpPost1.addHeader("Content-Type", paramRequest.getBodyContentType());
      setEntityIfNonEmptyBody(localHttpPost1, paramRequest);
      return localHttpPost1;
    }
    HttpPut localHttpPut = new HttpPut(paramRequest.getUrl());
    localHttpPut.addHeader("Content-Type", paramRequest.getBodyContentType());
    setEntityIfNonEmptyBody(localHttpPut, paramRequest);
    return localHttpPut;
  }
  
  private static List<NameValuePair> getPostParameterPairs(Map<String, String> paramMap)
  {
    ArrayList localArrayList = new ArrayList(paramMap.size());
    Iterator localIterator = paramMap.keySet().iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return localArrayList;
      }
      String str = (String)localIterator.next();
      localArrayList.add(new BasicNameValuePair(str, (String)paramMap.get(str)));
    }
  }
  
  private static void setEntityIfNonEmptyBody(HttpEntityEnclosingRequestBase paramHttpEntityEnclosingRequestBase, Request<?> paramRequest)
    throws AuthFailureError
  {
    byte[] arrayOfByte = paramRequest.getBody();
    if (arrayOfByte != null) {
      paramHttpEntityEnclosingRequestBase.setEntity(new ByteArrayEntity(arrayOfByte));
    }
  }
  
  protected void onPrepareRequest(HttpUriRequest paramHttpUriRequest)
    throws IOException
  {}
  
  public HttpResponse performRequest(Request<?> paramRequest, Map<String, String> paramMap)
    throws IOException, AuthFailureError
  {
    HttpUriRequest localHttpUriRequest = createHttpRequest(paramRequest, paramMap);
    addHeaders(localHttpUriRequest, paramMap);
    addHeaders(localHttpUriRequest, paramRequest.getHeaders());
    onPrepareRequest(localHttpUriRequest);
    HttpParams localHttpParams = localHttpUriRequest.getParams();
    int i = paramRequest.getTimeoutMs();
    HttpConnectionParams.setConnectionTimeout(localHttpParams, 5000);
    HttpConnectionParams.setSoTimeout(localHttpParams, i);
    return this.mClient.execute(localHttpUriRequest);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ultra.sdk.volley.toolbox.HttpClientStack
 * JD-Core Version:    0.7.0.1
 */