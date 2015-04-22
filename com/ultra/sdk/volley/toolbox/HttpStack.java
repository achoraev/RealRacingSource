package com.ultra.sdk.volley.toolbox;

import com.ultra.sdk.volley.AuthFailureError;
import com.ultra.sdk.volley.Request;
import java.io.IOException;
import java.util.Map;
import org.apache.http.HttpResponse;

public abstract interface HttpStack
{
  public abstract HttpResponse performRequest(Request<?> paramRequest, Map<String, String> paramMap)
    throws IOException, AuthFailureError;
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ultra.sdk.volley.toolbox.HttpStack
 * JD-Core Version:    0.7.0.1
 */