package com.ultra.sdk.volley.toolbox;

import com.ultra.sdk.volley.Cache;
import com.ultra.sdk.volley.Cache.Entry;

public class NoCache
  implements Cache
{
  public void clear() {}
  
  public Cache.Entry get(String paramString)
  {
    return null;
  }
  
  public void initialize() {}
  
  public void invalidate(String paramString, boolean paramBoolean) {}
  
  public void put(String paramString, Cache.Entry paramEntry) {}
  
  public void remove(String paramString) {}
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ultra.sdk.volley.toolbox.NoCache
 * JD-Core Version:    0.7.0.1
 */