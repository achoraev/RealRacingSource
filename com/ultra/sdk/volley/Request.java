package com.ultra.sdk.volley;

import android.net.Uri;
import android.os.SystemClock;
import android.text.TextUtils;
import java.util.Collections;
import java.util.Map;

public abstract class Request<T>
  implements Comparable<Request<T>>
{
  private static final String DEFAULT_PARAMS_ENCODING = "UTF-8";
  private static final long SLOW_REQUEST_THRESHOLD_MS = 3000L;
  private Cache.Entry mCacheEntry = null;
  private boolean mCanceled = false;
  private final int mDefaultTrafficStatsTag;
  private final Response.ErrorListener mErrorListener;
  private final VolleyLog.MarkerLog mEventLog = null;
  private final int mMethod;
  private long mRequestBirthTime = 0L;
  private RequestQueue mRequestQueue;
  private boolean mResponseDelivered = false;
  private RetryPolicy mRetryPolicy;
  private Integer mSequence;
  private boolean mShouldCache = true;
  private Object mTag;
  private final String mUrl;
  
  public Request(int paramInt, String paramString, Response.ErrorListener paramErrorListener)
  {
    this.mMethod = paramInt;
    this.mUrl = paramString;
    this.mErrorListener = paramErrorListener;
    setRetryPolicy(new DefaultRetryPolicy());
    boolean bool = TextUtils.isEmpty(paramString);
    int i = 0;
    if (bool) {}
    for (;;)
    {
      this.mDefaultTrafficStatsTag = i;
      return;
      i = Uri.parse(paramString).getHost().hashCode();
    }
  }
  
  public Request(String paramString, Response.ErrorListener paramErrorListener)
  {
    this(-1, paramString, paramErrorListener);
  }
  
  /* Error */
  private byte[] encodeParameters(Map<String, String> paramMap, String paramString)
  {
    // Byte code:
    //   0: new 101	java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial 102	java/lang/StringBuilder:<init>	()V
    //   7: astore_3
    //   8: aload_1
    //   9: invokeinterface 108 1 0
    //   14: invokeinterface 114 1 0
    //   19: astore 5
    //   21: aload 5
    //   23: invokeinterface 120 1 0
    //   28: ifne +12 -> 40
    //   31: aload_3
    //   32: invokevirtual 123	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   35: aload_2
    //   36: invokevirtual 127	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   39: areturn
    //   40: aload 5
    //   42: invokeinterface 131 1 0
    //   47: checkcast 133	java/util/Map$Entry
    //   50: astore 6
    //   52: aload_3
    //   53: aload 6
    //   55: invokeinterface 136 1 0
    //   60: checkcast 88	java/lang/String
    //   63: aload_2
    //   64: invokestatic 142	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   67: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   70: pop
    //   71: aload_3
    //   72: bipush 61
    //   74: invokevirtual 149	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   77: pop
    //   78: aload_3
    //   79: aload 6
    //   81: invokeinterface 152 1 0
    //   86: checkcast 88	java/lang/String
    //   89: aload_2
    //   90: invokestatic 142	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   93: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   96: pop
    //   97: aload_3
    //   98: bipush 38
    //   100: invokevirtual 149	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   103: pop
    //   104: goto -83 -> 21
    //   107: astore 4
    //   109: new 154	java/lang/RuntimeException
    //   112: dup
    //   113: new 101	java/lang/StringBuilder
    //   116: dup
    //   117: ldc 156
    //   119: invokespecial 159	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   122: aload_2
    //   123: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   126: invokevirtual 123	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   129: aload 4
    //   131: invokespecial 162	java/lang/RuntimeException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   134: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	135	0	this	Request
    //   0	135	1	paramMap	Map<String, String>
    //   0	135	2	paramString	String
    //   7	91	3	localStringBuilder	java.lang.StringBuilder
    //   107	23	4	localUnsupportedEncodingException	java.io.UnsupportedEncodingException
    //   19	22	5	localIterator	java.util.Iterator
    //   50	30	6	localEntry	java.util.Map.Entry
    // Exception table:
    //   from	to	target	type
    //   8	21	107	java/io/UnsupportedEncodingException
    //   21	40	107	java/io/UnsupportedEncodingException
    //   40	104	107	java/io/UnsupportedEncodingException
  }
  
  public void addMarker(String paramString)
  {
    if (this.mRequestBirthTime == 0L) {
      this.mRequestBirthTime = SystemClock.elapsedRealtime();
    }
  }
  
  public void cancel()
  {
    this.mCanceled = true;
  }
  
  public int compareTo(Request<T> paramRequest)
  {
    Priority localPriority1 = getPriority();
    Priority localPriority2 = paramRequest.getPriority();
    if (localPriority1 == localPriority2) {
      return this.mSequence.intValue() - paramRequest.mSequence.intValue();
    }
    return localPriority2.ordinal() - localPriority1.ordinal();
  }
  
  public void deliverError(VolleyError paramVolleyError)
  {
    if (this.mErrorListener != null) {
      this.mErrorListener.onErrorResponse(paramVolleyError);
    }
  }
  
  protected abstract void deliverResponse(T paramT);
  
  void finish(String paramString)
  {
    if (this.mRequestQueue != null) {
      this.mRequestQueue.finish(this);
    }
    long l = SystemClock.elapsedRealtime() - this.mRequestBirthTime;
    if (l >= 3000L)
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = Long.valueOf(l);
      arrayOfObject[1] = toString();
      VolleyLog.d("%d ms: %s", arrayOfObject);
    }
  }
  
  public byte[] getBody()
    throws AuthFailureError
  {
    Map localMap = getParams();
    if ((localMap != null) && (localMap.size() > 0)) {
      return encodeParameters(localMap, getParamsEncoding());
    }
    return null;
  }
  
  public String getBodyContentType()
  {
    return "application/x-www-form-urlencoded; charset=" + getParamsEncoding();
  }
  
  public Cache.Entry getCacheEntry()
  {
    return this.mCacheEntry;
  }
  
  public String getCacheKey()
  {
    return getUrl();
  }
  
  public Map<String, String> getHeaders()
    throws AuthFailureError
  {
    return Collections.emptyMap();
  }
  
  public int getMethod()
  {
    return this.mMethod;
  }
  
  protected Map<String, String> getParams()
    throws AuthFailureError
  {
    return null;
  }
  
  protected String getParamsEncoding()
  {
    return "UTF-8";
  }
  
  public byte[] getPostBody()
    throws AuthFailureError
  {
    Map localMap = getPostParams();
    if ((localMap != null) && (localMap.size() > 0)) {
      return encodeParameters(localMap, getPostParamsEncoding());
    }
    return null;
  }
  
  public String getPostBodyContentType()
  {
    return getBodyContentType();
  }
  
  protected Map<String, String> getPostParams()
    throws AuthFailureError
  {
    return getParams();
  }
  
  protected String getPostParamsEncoding()
  {
    return getParamsEncoding();
  }
  
  public Priority getPriority()
  {
    return Priority.NORMAL;
  }
  
  public RetryPolicy getRetryPolicy()
  {
    return this.mRetryPolicy;
  }
  
  public final int getSequence()
  {
    if (this.mSequence == null) {
      throw new IllegalStateException("getSequence called before setSequence");
    }
    return this.mSequence.intValue();
  }
  
  public Object getTag()
  {
    return this.mTag;
  }
  
  public final int getTimeoutMs()
  {
    return this.mRetryPolicy.getCurrentTimeout();
  }
  
  public int getTrafficStatsTag()
  {
    return this.mDefaultTrafficStatsTag;
  }
  
  public String getUrl()
  {
    return this.mUrl;
  }
  
  public boolean hasHadResponseDelivered()
  {
    return this.mResponseDelivered;
  }
  
  public boolean isCanceled()
  {
    return this.mCanceled;
  }
  
  public void markDelivered()
  {
    this.mResponseDelivered = true;
  }
  
  protected VolleyError parseNetworkError(VolleyError paramVolleyError)
  {
    return paramVolleyError;
  }
  
  protected abstract Response<T> parseNetworkResponse(NetworkResponse paramNetworkResponse);
  
  public void setCacheEntry(Cache.Entry paramEntry)
  {
    this.mCacheEntry = paramEntry;
  }
  
  public void setRequestQueue(RequestQueue paramRequestQueue)
  {
    this.mRequestQueue = paramRequestQueue;
  }
  
  public void setRetryPolicy(RetryPolicy paramRetryPolicy)
  {
    this.mRetryPolicy = paramRetryPolicy;
  }
  
  public final void setSequence(int paramInt)
  {
    this.mSequence = Integer.valueOf(paramInt);
  }
  
  public final void setShouldCache(boolean paramBoolean)
  {
    this.mShouldCache = paramBoolean;
  }
  
  public void setTag(Object paramObject)
  {
    this.mTag = paramObject;
  }
  
  public final boolean shouldCache()
  {
    return this.mShouldCache;
  }
  
  public String toString()
  {
    String str1 = "0x" + Integer.toHexString(getTrafficStatsTag());
    if (this.mCanceled) {}
    for (String str2 = "[X] ";; str2 = "[ ] ") {
      return str2 + getUrl() + " " + str1 + " " + getPriority() + " " + this.mSequence;
    }
  }
  
  public static abstract interface Method
  {
    public static final int DELETE = 3;
    public static final int DEPRECATED_GET_OR_POST = -1;
    public static final int GET = 0;
    public static final int POST = 1;
    public static final int PUT = 2;
  }
  
  public static enum Priority
  {
    IMMEDIATE,  HIGH,  NORMAL,  LOW;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ultra.sdk.volley.Request
 * JD-Core Version:    0.7.0.1
 */