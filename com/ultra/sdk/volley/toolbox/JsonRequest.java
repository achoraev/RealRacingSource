package com.ultra.sdk.volley.toolbox;

import com.ultra.sdk.volley.NetworkResponse;
import com.ultra.sdk.volley.Request;
import com.ultra.sdk.volley.Response;
import com.ultra.sdk.volley.Response.ErrorListener;
import com.ultra.sdk.volley.Response.Listener;
import com.ultra.sdk.volley.VolleyLog;
import java.io.UnsupportedEncodingException;

public abstract class JsonRequest<T>
  extends Request<T>
{
  private static final String PROTOCOL_CHARSET = "utf-8";
  private static final String PROTOCOL_CONTENT_TYPE = String.format("application/json; charset=%s", new Object[] { "utf-8" });
  private final Response.Listener<T> mListener;
  private final String mRequestBody;
  
  public JsonRequest(int paramInt, String paramString1, String paramString2, Response.Listener<T> paramListener, Response.ErrorListener paramErrorListener)
  {
    super(paramInt, paramString1, paramErrorListener);
    this.mListener = paramListener;
    this.mRequestBody = paramString2;
  }
  
  public JsonRequest(String paramString1, String paramString2, Response.Listener<T> paramListener, Response.ErrorListener paramErrorListener)
  {
    this(-1, paramString1, paramString2, paramListener, paramErrorListener);
  }
  
  protected void deliverResponse(T paramT)
  {
    this.mListener.onResponse(paramT);
  }
  
  public byte[] getBody()
  {
    try
    {
      if (this.mRequestBody == null) {
        return null;
      }
      byte[] arrayOfByte = this.mRequestBody.getBytes("utf-8");
      return arrayOfByte;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = this.mRequestBody;
      arrayOfObject[1] = "utf-8";
      VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", arrayOfObject);
    }
    return null;
  }
  
  public String getBodyContentType()
  {
    return PROTOCOL_CONTENT_TYPE;
  }
  
  public byte[] getPostBody()
  {
    return getBody();
  }
  
  public String getPostBodyContentType()
  {
    return getBodyContentType();
  }
  
  protected abstract Response<T> parseNetworkResponse(NetworkResponse paramNetworkResponse);
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ultra.sdk.volley.toolbox.JsonRequest
 * JD-Core Version:    0.7.0.1
 */