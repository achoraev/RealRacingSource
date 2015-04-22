package com.google.android.gms.tagmanager;

import android.net.Uri;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

class ce
{
  private static ce aqd;
  private volatile String aoc;
  private volatile a aqe;
  private volatile String aqf;
  private volatile String aqg;
  
  ce()
  {
    clear();
  }
  
  private String cI(String paramString)
  {
    return paramString.split("&")[0].split("=")[1];
  }
  
  private String j(Uri paramUri)
  {
    return paramUri.getQuery().replace("&gtm_debug=x", "");
  }
  
  static ce oJ()
  {
    try
    {
      if (aqd == null) {
        aqd = new ce();
      }
      ce localce = aqd;
      return localce;
    }
    finally {}
  }
  
  void clear()
  {
    this.aqe = a.aqh;
    this.aqf = null;
    this.aoc = null;
    this.aqg = null;
  }
  
  String getContainerId()
  {
    return this.aoc;
  }
  
  boolean i(Uri paramUri)
  {
    boolean bool = true;
    String str;
    try
    {
      str = URLDecoder.decode(paramUri.toString(), "UTF-8");
      if (!str.matches("^tagmanager.c.\\S+:\\/\\/preview\\/p\\?id=\\S+&gtm_auth=\\S+&gtm_preview=\\d+(&gtm_debug=x)?$")) {
        break label158;
      }
      bh.V("Container preview url: " + str);
      if (!str.matches(".*?&gtm_debug=x$")) {
        break label143;
      }
      this.aqe = a.aqj;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      for (;;)
      {
        bool = false;
        continue;
        this.aqe = a.aqi;
      }
    }
    finally {}
    this.aqg = j(paramUri);
    if ((this.aqe == a.aqi) || (this.aqe == a.aqj)) {
      this.aqf = ("/r?" + this.aqg);
    }
    this.aoc = cI(this.aqg);
    for (;;)
    {
      return bool;
      label143:
      label158:
      if (str.matches("^tagmanager.c.\\S+:\\/\\/preview\\/p\\?id=\\S+&gtm_preview=$"))
      {
        if (cI(paramUri.getQuery()).equals(this.aoc))
        {
          bh.V("Exit preview mode for container: " + this.aoc);
          this.aqe = a.aqh;
          this.aqf = null;
        }
      }
      else
      {
        bh.W("Invalid preview uri: " + str);
        bool = false;
        continue;
      }
      bool = false;
    }
  }
  
  a oK()
  {
    return this.aqe;
  }
  
  String oL()
  {
    return this.aqf;
  }
  
  static enum a
  {
    static
    {
      a[] arrayOfa = new a[3];
      arrayOfa[0] = aqh;
      arrayOfa[1] = aqi;
      arrayOfa[2] = aqj;
      aqk = arrayOfa;
    }
    
    private a() {}
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.ce
 * JD-Core Version:    0.7.0.1
 */