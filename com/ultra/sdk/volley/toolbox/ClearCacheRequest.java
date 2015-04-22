package com.ultra.sdk.volley.toolbox;

import android.os.Handler;
import android.os.Looper;
import com.ultra.sdk.volley.Cache;
import com.ultra.sdk.volley.NetworkResponse;
import com.ultra.sdk.volley.Request;
import com.ultra.sdk.volley.Request.Priority;
import com.ultra.sdk.volley.Response;

public class ClearCacheRequest
  extends Request<Object>
{
  private final Cache mCache;
  private final Runnable mCallback;
  
  public ClearCacheRequest(Cache paramCache, Runnable paramRunnable)
  {
    super(0, null, null);
    this.mCache = paramCache;
    this.mCallback = paramRunnable;
  }
  
  protected void deliverResponse(Object paramObject) {}
  
  public Request.Priority getPriority()
  {
    return Request.Priority.IMMEDIATE;
  }
  
  public boolean isCanceled()
  {
    this.mCache.clear();
    if (this.mCallback != null) {
      new Handler(Looper.getMainLooper()).postAtFrontOfQueue(this.mCallback);
    }
    return true;
  }
  
  protected Response<Object> parseNetworkResponse(NetworkResponse paramNetworkResponse)
  {
    return null;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ultra.sdk.volley.toolbox.ClearCacheRequest
 * JD-Core Version:    0.7.0.1
 */