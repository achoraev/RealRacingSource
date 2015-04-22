package com.ultra.sdk.volley.toolbox;

import com.ultra.sdk.volley.NetworkResponse;
import com.ultra.sdk.volley.ParseError;
import com.ultra.sdk.volley.Response;
import com.ultra.sdk.volley.Response.ErrorListener;
import com.ultra.sdk.volley.Response.Listener;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonObjectRequest
  extends JsonRequest<JSONObject>
{
  public JsonObjectRequest(int paramInt, String paramString, JSONObject paramJSONObject, Response.Listener<JSONObject> paramListener, Response.ErrorListener paramErrorListener) {}
  
  public JsonObjectRequest(String paramString, JSONObject paramJSONObject, Response.Listener<JSONObject> paramListener, Response.ErrorListener paramErrorListener) {}
  
  protected Response<JSONObject> parseNetworkResponse(NetworkResponse paramNetworkResponse)
  {
    try
    {
      Response localResponse = Response.success(new JSONObject(new String(paramNetworkResponse.data, HttpHeaderParser.parseCharset(paramNetworkResponse.headers))), HttpHeaderParser.parseCacheHeaders(paramNetworkResponse));
      return localResponse;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      return Response.error(new ParseError(localUnsupportedEncodingException));
    }
    catch (JSONException localJSONException)
    {
      return Response.error(new ParseError(localJSONException));
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ultra.sdk.volley.toolbox.JsonObjectRequest
 * JD-Core Version:    0.7.0.1
 */