package com.google.ads.interactivemedia.v3.b;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import com.google.ads.interactivemedia.v3.api.Ad;
import com.google.ads.interactivemedia.v3.api.AdDisplayContainer;
import com.google.ads.interactivemedia.v3.api.player.VideoProgressUpdate;
import com.google.ads.interactivemedia.v3.b.a.a;
import com.google.ads.interactivemedia.v3.b.a.c.a;
import com.google.ads.interactivemedia.v3.b.b.d;
import com.google.ads.interactivemedia.v3.b.b.e;
import com.google.ads.interactivemedia.v3.b.b.e.a;

public class h
  implements v.a
{
  private final String a;
  private s b;
  private u c;
  private AdDisplayContainer d;
  private e e;
  private Context f;
  private a g;
  
  public h(String paramString, u paramu, s params, AdDisplayContainer paramAdDisplayContainer, Context paramContext)
  {
    this.b = params;
    this.c = paramu;
    this.f = paramContext;
    this.a = paramString;
    this.d = paramAdDisplayContainer;
  }
  
  private void b(Ad paramAd)
  {
    switch (1.b[this.c.b().ordinal()])
    {
    default: 
      return;
    case 1: 
      d locald = d.a(paramAd);
      this.e = new e(this.f, this.d.getPlayer(), locald, this.b, this.a);
      this.b.a(this.e, this.a);
      this.e.a(new a(null));
      this.d.getAdContainer().addView(this.e.a());
      this.e.a(paramAd);
      return;
    }
    this.d.getAdContainer().addView(this.b.a());
  }
  
  public void a()
  {
    switch (1.b[this.c.b().ordinal()])
    {
    default: 
    case 1: 
      do
      {
        return;
      } while (this.e == null);
      this.e.b();
      this.d.getAdContainer().removeView(this.e.a());
      this.e = null;
      this.b.a(this.a);
      return;
    }
    this.d.getAdContainer().removeView(this.b.a());
  }
  
  public void a(Ad paramAd)
  {
    if ((this.g != null) && (!this.g.equals(paramAd)))
    {
      Log.e("IMASDK", "Cannot stop non current ad UI");
      return;
    }
    a();
    this.g = null;
  }
  
  public void a(VideoProgressUpdate paramVideoProgressUpdate)
  {
    if (this.e != null) {
      this.e.a(paramVideoProgressUpdate);
    }
  }
  
  public void a(a parama)
  {
    if (this.g != null) {
      a(this.g);
    }
    if (parama.isLinear())
    {
      this.g = parama;
      b(parama);
    }
  }
  
  private class a
    implements e.a
  {
    private a() {}
    
    public void a()
    {
      h.b(h.this).b(new r(r.b.videoDisplay, r.c.skip, h.a(h.this)));
    }
    
    public void b() {}
    
    public void c()
    {
      h.b(h.this).b(new r(r.b.videoDisplay, r.c.click, h.a(h.this)));
      h.b(h.this).b(h.c(h.this).getClickThruUrl());
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.b.h
 * JD-Core Version:    0.7.0.1
 */