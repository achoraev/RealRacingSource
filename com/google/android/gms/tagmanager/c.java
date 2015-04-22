package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.d.a;
import java.util.Map;

class c
  extends aj
{
  private static final String ID = com.google.android.gms.internal.a.v.toString();
  private final a anS;
  
  public c(Context paramContext)
  {
    this(a.W(paramContext));
  }
  
  c(a parama)
  {
    super(ID, new String[0]);
    this.anS = parama;
  }
  
  public d.a C(Map<String, d.a> paramMap)
  {
    if (!this.anS.isLimitAdTrackingEnabled()) {}
    for (boolean bool = true;; bool = false) {
      return di.u(Boolean.valueOf(bool));
    }
  }
  
  public boolean nN()
  {
    return false;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.c
 * JD-Core Version:    0.7.0.1
 */