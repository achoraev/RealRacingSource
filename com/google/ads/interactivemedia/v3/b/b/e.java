package com.google.ads.interactivemedia.v3.b.b;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.google.ads.interactivemedia.v3.api.Ad;
import com.google.ads.interactivemedia.v3.api.player.VideoAdPlayer;
import com.google.ads.interactivemedia.v3.api.player.VideoProgressUpdate;
import com.google.ads.interactivemedia.v3.b.r;
import com.google.ads.interactivemedia.v3.b.r.b;
import com.google.ads.interactivemedia.v3.b.r.c;
import com.google.ads.interactivemedia.v3.b.s;
import com.google.ads.interactivemedia.v3.b.s.c;
import com.google.ads.interactivemedia.v3.b.v.a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class e
  extends RelativeLayout
  implements View.OnClickListener, s.c, v.a
{
  private FrameLayout a;
  private List<a> b = new ArrayList();
  private final float c;
  private final String d;
  private s e;
  private boolean f = false;
  private float g;
  private String h;
  private b i;
  private b j;
  private d k;
  private a l;
  
  public e(Context paramContext, VideoAdPlayer paramVideoAdPlayer, d paramd, s params, String paramString)
  {
    super(paramContext);
    this.e = params;
    this.d = paramString;
    this.k = paramd;
    this.c = getResources().getDisplayMetrics().density;
    b(paramContext);
    if (paramd.a) {
      a(paramContext);
    }
    a(this.f);
  }
  
  private void a(Context paramContext)
  {
    this.a = new FrameLayout(paramContext);
    this.j = new b(paramContext);
    this.a.addView(this.j, new RelativeLayout.LayoutParams(-2, -2));
    int m = c.a(15, this.c);
    this.a.setPadding(m, m, 0, m);
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
    localLayoutParams.addRule(12);
    localLayoutParams.addRule(11);
    this.a.setLayoutParams(localLayoutParams);
    this.a.setOnClickListener(this);
    addView(this.a);
  }
  
  private void a(String paramString)
  {
    if (!this.f)
    {
      this.l.a(paramString);
      return;
    }
    if (!TextUtils.isEmpty(this.h))
    {
      this.l.a(paramString + ": " + this.h + "»");
      return;
    }
    this.l.a(paramString);
  }
  
  private void b(Context paramContext)
  {
    this.l = new a(paramContext, this.k);
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-1, -2);
    localLayoutParams.addRule(10);
    addView(this.l, localLayoutParams);
    this.l.a(new a.a()
    {
      public void c()
      {
        Iterator localIterator = e.a(e.this).iterator();
        while (localIterator.hasNext()) {
          ((e.a)localIterator.next()).c();
        }
      }
    });
  }
  
  private void b(String paramString)
  {
    this.l.b(paramString);
  }
  
  public View a()
  {
    return this;
  }
  
  public void a(Ad paramAd)
  {
    a("");
    b(this.k.m);
    this.e.b(new r(r.b.i18n, r.c.learnMore, this.d));
    if (paramAd.isSkippable())
    {
      this.i = b.b;
      this.a.setVisibility(0);
      HashMap localHashMap = new HashMap(1);
      localHashMap.put("seconds", Integer.valueOf(5));
      this.e.b(new r(r.b.i18n, r.c.preSkipButton, this.d, localHashMap));
    }
    for (;;)
    {
      setVisibility(0);
      return;
      this.i = b.a;
      if (this.a != null) {
        this.a.setVisibility(4);
      }
    }
  }
  
  public void a(VideoProgressUpdate paramVideoProgressUpdate)
  {
    if ((paramVideoProgressUpdate == null) || (paramVideoProgressUpdate.getDuration() < 0.0F)) {
      return;
    }
    float f1 = paramVideoProgressUpdate.getDuration() - paramVideoProgressUpdate.getCurrentTime();
    if (Math.floor(f1) != Math.floor(this.g)) {}
    float f2;
    for (int m = 1;; m = 0)
    {
      if (m != 0)
      {
        HashMap localHashMap1 = new HashMap(2);
        localHashMap1.put("minutes", Float.valueOf(f1 / 60.0F));
        localHashMap1.put("seconds", Float.valueOf(f1 % 60.0F));
        this.e.b(new r(r.b.i18n, r.c.adRemainingTime, this.d, localHashMap1));
      }
      if (this.i != b.b) {
        break;
      }
      f2 = 5.0F - paramVideoProgressUpdate.getCurrentTime();
      if (f2 > 0.0F) {
        break label222;
      }
      this.i = b.c;
      this.e.b(new r(r.b.i18n, r.c.skipButton, this.d));
      Iterator localIterator = this.b.iterator();
      while (localIterator.hasNext()) {
        ((a)localIterator.next()).b();
      }
    }
    label222:
    if (m != 0)
    {
      HashMap localHashMap2 = new HashMap(1);
      localHashMap2.put("seconds", Float.valueOf(f2));
      this.e.b(new r(r.b.i18n, r.c.preSkipButton, this.d, localHashMap2));
    }
    this.g = f1;
  }
  
  public void a(a parama)
  {
    this.b.add(parama);
  }
  
  public void a(r.c paramc, String paramString)
  {
    switch (2.a[paramc.ordinal()])
    {
    default: 
      return;
    case 1: 
      a(paramString);
      return;
    case 2: 
      b(paramString);
      return;
    }
    this.j.a(paramString);
  }
  
  public void a(boolean paramBoolean)
  {
    this.f = paramBoolean;
    if (this.k.a) {
      ((RelativeLayout.LayoutParams)this.a.getLayoutParams()).setMargins(0, 0, 0, c.a(25, this.c));
    }
  }
  
  public void b()
  {
    setVisibility(4);
  }
  
  public void onClick(View paramView)
  {
    if ((paramView == this.a) && (this.i == b.c))
    {
      Iterator localIterator = this.b.iterator();
      while (localIterator.hasNext()) {
        ((a)localIterator.next()).a();
      }
    }
  }
  
  public static abstract interface a
    extends a.a
  {
    public abstract void a();
    
    public abstract void b();
  }
  
  private static enum b
  {
    static
    {
      b[] arrayOfb = new b[3];
      arrayOfb[0] = a;
      arrayOfb[1] = b;
      arrayOfb[2] = c;
      d = arrayOfb;
    }
    
    private b() {}
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.b.b.e
 * JD-Core Version:    0.7.0.1
 */