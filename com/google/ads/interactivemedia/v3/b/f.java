package com.google.ads.interactivemedia.v3.b;

import android.content.Context;
import com.google.ads.interactivemedia.v3.api.Ad;
import com.google.ads.interactivemedia.v3.api.AdDisplayContainer;
import com.google.ads.interactivemedia.v3.api.AdError;
import com.google.ads.interactivemedia.v3.api.AdError.AdErrorCode;
import com.google.ads.interactivemedia.v3.api.AdError.AdErrorType;
import com.google.ads.interactivemedia.v3.api.AdErrorEvent.AdErrorListener;
import com.google.ads.interactivemedia.v3.api.AdEvent.AdEventListener;
import com.google.ads.interactivemedia.v3.api.AdEvent.AdEventType;
import com.google.ads.interactivemedia.v3.api.AdsManager;
import com.google.ads.interactivemedia.v3.api.AdsRenderingSettings;
import com.google.ads.interactivemedia.v3.api.player.VideoAdPlayer;
import com.google.ads.interactivemedia.v3.api.player.VideoAdPlayer.VideoAdPlayerCallback;
import com.google.ads.interactivemedia.v3.b.a.a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;

public class f
  implements AdsManager
{
  v a;
  private final s b;
  private final String c;
  private final VideoAdPlayer d;
  private a e;
  private n f;
  private d g;
  private List<Float> h;
  private h i;
  private VideoAdPlayer.VideoAdPlayerCallback j;
  private final List<AdEvent.AdEventListener> k = new ArrayList(1);
  private final o l = new o();
  
  f(String paramString, s params, u paramu, AdDisplayContainer paramAdDisplayContainer, List<Float> paramList, SortedSet<Float> paramSortedSet, v paramv, Context paramContext)
  {
    this.i = new h(paramString, paramu, params, paramAdDisplayContainer, paramContext);
    this.c = paramString;
    this.b = params;
    this.d = paramAdDisplayContainer.getPlayer();
    this.h = paramList;
    this.a = paramv;
    paramv.a(this.i);
    if ((paramSortedSet != null) && (!paramSortedSet.isEmpty()))
    {
      this.f = new n(params, paramSortedSet, paramString);
      paramv.c(this.f);
      this.j = new m(paramv);
    }
  }
  
  private void a(r.c paramc)
  {
    this.b.b(new r(r.b.adsManager, paramc, this.c));
  }
  
  void a(AdEvent.AdEventType paramAdEventType)
  {
    a(paramAdEventType, null);
  }
  
  void a(AdEvent.AdEventType paramAdEventType, Map<String, String> paramMap)
  {
    c localc = new c(paramAdEventType, this.e, paramMap);
    Iterator localIterator = this.k.iterator();
    while (localIterator.hasNext()) {
      ((AdEvent.AdEventListener)localIterator.next()).onAdEvent(localc);
    }
  }
  
  public void addAdErrorListener(AdErrorEvent.AdErrorListener paramAdErrorListener)
  {
    this.l.a(paramAdErrorListener);
  }
  
  public void addAdEventListener(AdEvent.AdEventListener paramAdEventListener)
  {
    this.k.add(paramAdEventListener);
  }
  
  public void destroy()
  {
    this.a.c();
    this.a.b(this.i);
    this.i.a(this.e);
    this.d.removeCallback(this.g);
    if (this.j != null) {
      this.d.removeCallback(this.j);
    }
    a(r.c.destroy);
  }
  
  public List<Float> getAdCuePoints()
  {
    return this.h;
  }
  
  public Ad getCurrentAd()
  {
    return this.e;
  }
  
  public void init()
  {
    init(null);
  }
  
  public void init(AdsRenderingSettings paramAdsRenderingSettings)
  {
    this.b.a(new a(null), this.c);
    HashMap localHashMap = new HashMap();
    localHashMap.put("adsRenderingSettings", paramAdsRenderingSettings);
    this.b.b(new r(r.b.adsManager, r.c.init, this.c, localHashMap));
  }
  
  public void removeAdErrorListener(AdErrorEvent.AdErrorListener paramAdErrorListener)
  {
    this.l.b(paramAdErrorListener);
  }
  
  public void removeAdEventListener(AdEvent.AdEventListener paramAdEventListener)
  {
    this.k.remove(paramAdEventListener);
  }
  
  public void skip()
  {
    this.b.b(new r(r.b.adsManager, r.c.skip, this.c));
  }
  
  public void start()
  {
    if (this.d == null)
    {
      this.l.a(new b(new AdError(AdError.AdErrorType.PLAY, AdError.AdErrorCode.INVALID_ARGUMENTS, "Ad Display Container must contain a non-null video player.")));
      return;
    }
    this.b.a(this.d, this.c);
    a(r.c.start);
  }
  
  private class a
    implements s.b
  {
    private a() {}
    
    public void a(AdError.AdErrorType paramAdErrorType, int paramInt, String paramString)
    {
      b localb = new b(new AdError(paramAdErrorType, paramInt, paramString));
      f.h(f.this).a(localb);
      f.g(f.this).a(f.f(f.this));
    }
    
    public void a(AdError.AdErrorType paramAdErrorType, AdError.AdErrorCode paramAdErrorCode, String paramString)
    {
      b localb = new b(new AdError(paramAdErrorType, paramAdErrorCode, paramString));
      f.h(f.this).a(localb);
      f.g(f.this).a(f.f(f.this));
    }
    
    public void a(AdEvent.AdEventType paramAdEventType, a parama)
    {
      if (parama != null) {
        f.a(f.this, parama);
      }
      switch (f.1.a[paramAdEventType.ordinal()])
      {
      }
      for (;;)
      {
        f.this.a(paramAdEventType);
        if (paramAdEventType == AdEvent.AdEventType.COMPLETED) {
          f.a(f.this, null);
        }
        return;
        f.a(f.this, new d(f.a(f.this), f.b(f.this), f.this.a));
        f.d(f.this).addCallback(f.c(f.this));
        f.this.a.a(f.c(f.this));
        if (f.e(f.this) != null)
        {
          f.d(f.this).removeCallback(f.e(f.this));
          continue;
          f.d(f.this).removeCallback(f.c(f.this));
          f.this.a.b(f.c(f.this));
          if (f.e(f.this) != null)
          {
            f.d(f.this).addCallback(f.e(f.this));
            continue;
            f.g(f.this).a(f.f(f.this));
            continue;
            f.g(f.this).a(f.f(f.this));
            continue;
            f.this.destroy();
          }
        }
      }
    }
    
    public void a(AdEvent.AdEventType paramAdEventType, a parama, Map<String, String> paramMap)
    {
      if (parama != null) {
        f.a(f.this, parama);
      }
      f.this.a(paramAdEventType, paramMap);
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.b.f
 * JD-Core Version:    0.7.0.1
 */