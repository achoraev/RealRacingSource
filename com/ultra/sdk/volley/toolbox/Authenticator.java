package com.ultra.sdk.volley.toolbox;

import com.ultra.sdk.volley.AuthFailureError;

public abstract interface Authenticator
{
  public abstract String getAuthToken()
    throws AuthFailureError;
  
  public abstract void invalidateAuthToken(String paramString);
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ultra.sdk.volley.toolbox.Authenticator
 * JD-Core Version:    0.7.0.1
 */