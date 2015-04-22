package com.google.android.gms.tagmanager;

import android.content.Context;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

class y
  implements aq
{
  private static y apb;
  private static final Object xz = new Object();
  private cg aos;
  private String apc;
  private String apd;
  private ar ape;
  
  private y(Context paramContext)
  {
    this(as.Z(paramContext), new cw());
  }
  
  y(ar paramar, cg paramcg)
  {
    this.ape = paramar;
    this.aos = paramcg;
  }
  
  public static aq X(Context paramContext)
  {
    synchronized (xz)
    {
      if (apb == null) {
        apb = new y(paramContext);
      }
      y localy = apb;
      return localy;
    }
  }
  
  public boolean cz(String paramString)
  {
    if (!this.aos.eJ())
    {
      bh.W("Too many urls sent too quickly with the TagManagerSender, rate limiting invoked.");
      return false;
    }
    if ((this.apc != null) && (this.apd != null)) {}
    try
    {
      paramString = this.apc + "?" + this.apd + "=" + URLEncoder.encode(paramString, "UTF-8");
      bh.V("Sending wrapped url hit: " + paramString);
      this.ape.cC(paramString);
      return true;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      bh.d("Error wrapping URL for testing.", localUnsupportedEncodingException);
    }
    return false;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.y
 * JD-Core Version:    0.7.0.1
 */