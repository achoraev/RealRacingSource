package com.ultra.sdk.volley.toolbox;

import android.os.SystemClock;
import com.ultra.sdk.volley.AuthFailureError;
import com.ultra.sdk.volley.Cache.Entry;
import com.ultra.sdk.volley.Network;
import com.ultra.sdk.volley.NetworkError;
import com.ultra.sdk.volley.NetworkResponse;
import com.ultra.sdk.volley.NoConnectionError;
import com.ultra.sdk.volley.Request;
import com.ultra.sdk.volley.RetryPolicy;
import com.ultra.sdk.volley.ServerError;
import com.ultra.sdk.volley.TimeoutError;
import com.ultra.sdk.volley.VolleyError;
import com.ultra.sdk.volley.VolleyLog;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.cookie.DateUtils;

public class BasicNetwork
  implements Network
{
  protected static final boolean DEBUG;
  private static int DEFAULT_POOL_SIZE = 4096;
  private static int SLOW_REQUEST_THRESHOLD_MS = 3000;
  protected final HttpStack mHttpStack;
  protected final ByteArrayPool mPool;
  
  public BasicNetwork(HttpStack paramHttpStack)
  {
    this(paramHttpStack, new ByteArrayPool(DEFAULT_POOL_SIZE));
  }
  
  public BasicNetwork(HttpStack paramHttpStack, ByteArrayPool paramByteArrayPool)
  {
    this.mHttpStack = paramHttpStack;
    this.mPool = paramByteArrayPool;
  }
  
  private void addCacheHeaders(Map<String, String> paramMap, Cache.Entry paramEntry)
  {
    if (paramEntry == null) {}
    do
    {
      return;
      if (paramEntry.etag != null) {
        paramMap.put("If-None-Match", paramEntry.etag);
      }
    } while (paramEntry.serverDate <= 0L);
    paramMap.put("If-Modified-Since", DateUtils.formatDate(new Date(paramEntry.serverDate)));
  }
  
  private static void attemptRetryOnException(String paramString, Request<?> paramRequest, VolleyError paramVolleyError)
    throws VolleyError
  {
    RetryPolicy localRetryPolicy = paramRequest.getRetryPolicy();
    int i = paramRequest.getTimeoutMs();
    try
    {
      localRetryPolicy.retry(paramVolleyError);
      Object[] arrayOfObject2 = new Object[2];
      arrayOfObject2[0] = paramString;
      arrayOfObject2[1] = Integer.valueOf(i);
      paramRequest.addMarker(String.format("%s-retry [timeout=%s]", arrayOfObject2));
      return;
    }
    catch (VolleyError localVolleyError)
    {
      Object[] arrayOfObject1 = new Object[2];
      arrayOfObject1[0] = paramString;
      arrayOfObject1[1] = Integer.valueOf(i);
      paramRequest.addMarker(String.format("%s-timeout-giveup [timeout=%s]", arrayOfObject1));
      throw localVolleyError;
    }
  }
  
  private static Map<String, String> convertHeaders(Header[] paramArrayOfHeader)
  {
    HashMap localHashMap = new HashMap();
    for (int i = 0;; i++)
    {
      if (i >= paramArrayOfHeader.length) {
        return localHashMap;
      }
      localHashMap.put(paramArrayOfHeader[i].getName(), paramArrayOfHeader[i].getValue());
    }
  }
  
  private byte[] entityToBytes(HttpEntity paramHttpEntity)
    throws IOException, ServerError
  {
    PoolingByteArrayOutputStream localPoolingByteArrayOutputStream = new PoolingByteArrayOutputStream(this.mPool, (int)paramHttpEntity.getContentLength());
    byte[] arrayOfByte1 = null;
    InputStream localInputStream;
    try
    {
      localInputStream = paramHttpEntity.getContent();
      arrayOfByte1 = null;
      if (localInputStream == null) {
        throw new ServerError();
      }
    }
    finally {}
    try
    {
      paramHttpEntity.consumeContent();
      this.mPool.returnBuf(arrayOfByte1);
      localPoolingByteArrayOutputStream.close();
      throw localObject;
      arrayOfByte1 = this.mPool.getBuf(1024);
      for (;;)
      {
        int i = localInputStream.read(arrayOfByte1);
        byte[] arrayOfByte2;
        if (i == -1) {
          arrayOfByte2 = localPoolingByteArrayOutputStream.toByteArray();
        }
        try
        {
          paramHttpEntity.consumeContent();
          this.mPool.returnBuf(arrayOfByte1);
          localPoolingByteArrayOutputStream.close();
          return arrayOfByte2;
          localPoolingByteArrayOutputStream.write(arrayOfByte1, 0, i);
        }
        catch (IOException localIOException2)
        {
          for (;;)
          {
            VolleyLog.v("Error occured when calling consumingContent", new Object[0]);
          }
        }
      }
    }
    catch (IOException localIOException1)
    {
      for (;;)
      {
        VolleyLog.v("Error occured when calling consumingContent", new Object[0]);
      }
    }
  }
  
  private void logSlowRequests(long paramLong, Request<?> paramRequest, byte[] paramArrayOfByte, StatusLine paramStatusLine)
  {
    Object[] arrayOfObject;
    if (paramLong > SLOW_REQUEST_THRESHOLD_MS)
    {
      arrayOfObject = new Object[5];
      arrayOfObject[0] = paramRequest;
      arrayOfObject[1] = Long.valueOf(paramLong);
      if (paramArrayOfByte == null) {
        break label85;
      }
    }
    label85:
    for (Object localObject = Integer.valueOf(paramArrayOfByte.length);; localObject = "null")
    {
      arrayOfObject[2] = localObject;
      arrayOfObject[3] = Integer.valueOf(paramStatusLine.getStatusCode());
      arrayOfObject[4] = Integer.valueOf(paramRequest.getRetryPolicy().getCurrentRetryCount());
      VolleyLog.d("HTTP response for request=<%s> [lifetime=%d], [size=%s], [rc=%d], [retryCount=%s]", arrayOfObject);
      return;
    }
  }
  
  protected void logError(String paramString1, String paramString2, long paramLong)
  {
    long l = SystemClock.elapsedRealtime();
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = paramString1;
    arrayOfObject[1] = Long.valueOf(l - paramLong);
    arrayOfObject[2] = paramString2;
    VolleyLog.v("HTTP ERROR(%s) %d ms to fetch %s", arrayOfObject);
  }
  
  public NetworkResponse performRequest(Request<?> paramRequest)
    throws VolleyError
  {
    long l = SystemClock.elapsedRealtime();
    NetworkResponse localNetworkResponse1;
    for (;;)
    {
      HttpResponse localHttpResponse = null;
      byte[] arrayOfByte = null;
      Object localObject = new HashMap();
      try
      {
        HashMap localHashMap = new HashMap();
        addCacheHeaders(localHashMap, paramRequest.getCacheEntry());
        localHttpResponse = this.mHttpStack.performRequest(paramRequest, localHashMap);
        StatusLine localStatusLine = localHttpResponse.getStatusLine();
        j = localStatusLine.getStatusCode();
        localObject = convertHeaders(localHttpResponse.getAllHeaders());
        arrayOfByte = null;
        if (j == 304) {
          return new NetworkResponse(304, paramRequest.getCacheEntry().data, (Map)localObject, true);
        }
        arrayOfByte = entityToBytes(localHttpResponse.getEntity());
        logSlowRequests(SystemClock.elapsedRealtime() - l, paramRequest, arrayOfByte, localStatusLine);
        if ((j != 200) && (j != 204)) {
          throw new IOException();
        }
      }
      catch (SocketTimeoutException localSocketTimeoutException)
      {
        int j;
        attemptRetryOnException("socket", paramRequest, new TimeoutError());
        continue;
        NetworkResponse localNetworkResponse2 = new NetworkResponse(j, arrayOfByte, (Map)localObject, false);
        return localNetworkResponse2;
      }
      catch (ConnectTimeoutException localConnectTimeoutException)
      {
        attemptRetryOnException("connection", paramRequest, new TimeoutError());
      }
      catch (MalformedURLException localMalformedURLException)
      {
        throw new RuntimeException("Bad URL " + paramRequest.getUrl(), localMalformedURLException);
      }
      catch (IOException localIOException)
      {
        if (localHttpResponse != null)
        {
          int i = localHttpResponse.getStatusLine().getStatusCode();
          Object[] arrayOfObject = new Object[2];
          arrayOfObject[0] = Integer.valueOf(i);
          arrayOfObject[1] = paramRequest.getUrl();
          VolleyLog.e("Unexpected response code %d for %s", arrayOfObject);
          if (arrayOfByte == null) {
            break label382;
          }
          localNetworkResponse1 = new NetworkResponse(i, arrayOfByte, (Map)localObject, false);
          if ((i == 401) || (i == 403)) {
            attemptRetryOnException("auth", paramRequest, new AuthFailureError(localNetworkResponse1));
          }
        }
        else
        {
          throw new NoConnectionError(localIOException);
        }
      }
    }
    throw new ServerError(localNetworkResponse1);
    label382:
    throw new NetworkError(null);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ultra.sdk.volley.toolbox.BasicNetwork
 * JD-Core Version:    0.7.0.1
 */