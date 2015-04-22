package com.google.ads.interactivemedia.v3.b;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.google.ads.interactivemedia.v3.api.AdDisplayContainer;
import com.google.ads.interactivemedia.v3.api.AdError.AdErrorCode;
import com.google.ads.interactivemedia.v3.api.AdError.AdErrorType;
import com.google.ads.interactivemedia.v3.api.AdEvent.AdEventType;
import com.google.ads.interactivemedia.v3.api.CompanionAdSlot;
import com.google.ads.interactivemedia.v3.api.ImaSdkSettings;
import com.google.ads.interactivemedia.v3.api.player.VideoAdPlayer;
import com.google.ads.interactivemedia.v3.b.a.c;
import com.google.ads.interactivemedia.v3.b.a.c.a;
import com.google.ads.interactivemedia.v3.b.a.e;
import com.google.ads.interactivemedia.v3.b.a.e.a;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;

public class s
  implements t.a
{
  private Map<String, b> a = new HashMap();
  private Map<String, a> b = new HashMap();
  private Map<String, c> c = new HashMap();
  private Map<String, VideoAdPlayer> d = new HashMap();
  private Map<String, AdDisplayContainer> e = new HashMap();
  private final Context f;
  private final t g;
  private u h;
  private boolean i = false;
  private Queue<r> j = new LinkedList();
  private long k = SystemClock.elapsedRealtime();
  
  public s(Context paramContext, Uri paramUri, ImaSdkSettings paramImaSdkSettings)
  {
    this.f = paramContext;
    this.g = new t(paramContext, this);
    this.g.a(a(paramUri, paramImaSdkSettings).toString());
  }
  
  static Uri a(Uri paramUri, ImaSdkSettings paramImaSdkSettings)
  {
    return paramUri.buildUpon().appendQueryParameter("sdk_version", "a.3.0b8").appendQueryParameter("hl", paramImaSdkSettings.getLanguage()).build();
  }
  
  private String a(String paramString1, String paramString2)
  {
    if ((paramString2 == null) || (paramString2.length() == 0)) {
      return paramString1;
    }
    return paramString1 + " Caused by: " + paramString2;
  }
  
  private Map<String, ViewGroup> a(a parama, Set<String> paramSet)
  {
    HashMap localHashMap = new HashMap(paramSet.size());
    Iterator localIterator = paramSet.iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      CompanionAdSlot localCompanionAdSlot = (CompanionAdSlot)parama.a().get(str);
      if (localCompanionAdSlot.getContainer() == null) {
        return null;
      }
      localHashMap.put(str, localCompanionAdSlot.getContainer());
    }
    return localHashMap;
  }
  
  private void a(long paramLong, String paramString)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("webViewLoadingTime", Long.valueOf(paramLong));
    b(new r(r.b.webViewLoaded, r.c.csi, paramString, localHashMap));
  }
  
  private void a(ViewGroup paramViewGroup, c paramc, String paramString)
  {
    paramViewGroup.removeAllViews();
    int m = 1.c[paramc.type.ordinal()];
    Object localObject = null;
    switch (m)
    {
    }
    for (;;)
    {
      paramViewGroup.addView((View)localObject);
      return;
      localObject = new l(paramViewGroup.getContext(), this, paramc);
      continue;
      localObject = new q(paramViewGroup.getContext(), this, paramc, paramString);
    }
  }
  
  private void a(r.c paramc, String paramString, e parame)
  {
    Object localObject;
    switch (1.b[paramc.ordinal()])
    {
    default: 
      a("other", paramc);
      return;
    case 1: 
      localObject = r.a.b;
    }
    try
    {
      if (parame.adUiStyle != null)
      {
        r.a locala = r.a.valueOf(parame.adUiStyle);
        localObject = locala;
      }
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      label65:
      String str;
      break label65;
    }
    this.h = new u(parame.adTimeUpdateMs, (r.a)localObject);
    this.i = true;
    a(SystemClock.elapsedRealtime() - this.k, paramString);
    b();
    return;
    if ((parame.ln == null) || (parame.n == null) || (parame.m == null))
    {
      Log.e("IMASDK", "Invalid logging message data: " + parame);
      return;
    }
    str = "SDK_LOG:" + parame.n;
    switch (parame.ln.charAt(0))
    {
    default: 
      Log.w("IMASDK", "Unrecognized log level: " + parame.ln);
      Log.w(str, parame.m);
      return;
    case 'D': 
      Log.d(str, parame.m);
      return;
    case 'E': 
    case 'S': 
      Log.e(str, parame.m);
      return;
    case 'I': 
      Log.i(str, parame.m);
      return;
    case 'V': 
      Log.v(str, parame.m);
      return;
    }
    Log.w(str, parame.m);
  }
  
  private void a(String paramString, r.c paramc)
  {
    Log.i("IMASDK", "Illegal message type " + paramc + " received for " + paramString + " channel");
  }
  
  private void b()
  {
    while ((this.i) && (!this.j.isEmpty())) {
      this.g.a((r)this.j.remove());
    }
  }
  
  private void b(r.c paramc, String paramString, e parame)
  {
    a locala = (a)this.e.get(paramString);
    b localb = (b)this.a.get(paramString);
    if ((locala == null) || (localb == null)) {
      Log.e("IMASDK", "Received displayContainer message: " + paramc + " for invalid session id: " + paramString);
    }
    for (;;)
    {
      return;
      switch (1.b[paramc.ordinal()])
      {
      case 4: 
      case 5: 
      default: 
        a(r.b.displayContainer.toString(), paramc);
        return;
      }
      if ((parame == null) || (parame.companions == null))
      {
        localb.a(AdError.AdErrorType.LOAD, AdError.AdErrorCode.INTERNAL_ERROR, "Display companions message requires companions in data.");
        return;
      }
      Map localMap = a(locala, parame.companions.keySet());
      if (localMap == null)
      {
        localb.a(AdError.AdErrorType.LOAD, AdError.AdErrorCode.INTERNAL_ERROR, "Display requested for invalid companion slot.");
        return;
      }
      Iterator localIterator = localMap.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        a((ViewGroup)localMap.get(str), (c)parame.companions.get(str), paramString);
      }
    }
  }
  
  private void c(r.c paramc, String paramString, e parame)
  {
    c localc = (c)this.c.get(paramString);
    if (localc != null) {
      localc.a(paramc, parame.translation);
    }
  }
  
  private void d(r.c paramc, String paramString, e parame)
  {
    a locala = (a)this.b.get(paramString);
    if (locala == null)
    {
      Log.e("IMASDK", "Received request message: " + paramc + " for invalid session id: " + paramString);
      return;
    }
    switch (1.b[paramc.ordinal()])
    {
    default: 
      a(r.b.adsLoader.toString(), paramc);
      return;
    case 6: 
      if (parame == null)
      {
        locala.a(paramString, AdError.AdErrorType.LOAD, AdError.AdErrorCode.INTERNAL_ERROR, "adsLoaded message did not contain cue points.");
        return;
      }
      locala.a(paramString, this.h, parame.adCuePoints, parame.internalCuePoints);
      return;
    }
    locala.a(paramString, AdError.AdErrorType.LOAD, parame.errorCode, a(parame.errorMessage, parame.innerError));
  }
  
  private void e(r.c paramc, String paramString, e parame)
  {
    VideoAdPlayer localVideoAdPlayer = (VideoAdPlayer)this.d.get(paramString);
    if (localVideoAdPlayer == null) {
      Log.e("IMASDK", "Received player message: " + paramc + " for invalid session id: " + paramString);
    }
    b localb;
    do
    {
      return;
      switch (1.b[paramc.ordinal()])
      {
      case 11: 
      case 12: 
      default: 
        a(r.b.videoDisplay.toString(), paramc);
        return;
      case 8: 
        if ((parame != null) && (parame.videoUrl != null)) {
          localVideoAdPlayer.loadAd(parame.videoUrl);
        }
        localVideoAdPlayer.playAd();
        return;
      case 9: 
        localVideoAdPlayer.pauseAd();
        return;
      }
      if ((parame != null) && (parame.videoUrl != null))
      {
        localVideoAdPlayer.loadAd(parame.videoUrl);
        return;
      }
      Log.e("IMASDK", "Load message must contain video url");
      localb = (b)this.a.get(paramString);
    } while (localb == null);
    localb.a(AdError.AdErrorType.LOAD, AdError.AdErrorCode.INTERNAL_ERROR, "Loading message did not contain a video url.");
  }
  
  private void f(r.c paramc, String paramString, e parame)
  {
    b localb = (b)this.a.get(paramString);
    if (localb == null)
    {
      Log.e("IMASDK", "Received manager message: " + paramc + " for invalid session id: " + paramString);
      return;
    }
    if ((parame != null) && (parame.adData != null)) {}
    for (com.google.ads.interactivemedia.v3.b.a.a locala = parame.adData;; locala = null)
    {
      switch (1.b[paramc.ordinal()])
      {
      case 13: 
      case 3: 
      case 4: 
      case 5: 
      case 6: 
      case 8: 
      case 10: 
      case 11: 
      case 12: 
      default: 
        a(r.b.adsManager.toString(), paramc);
        return;
      case 14: 
        if (locala != null)
        {
          localb.a(AdEvent.AdEventType.LOADED, locala);
          return;
        }
        Log.e("IMASDK", "Ad loaded message requires adData");
        localb.a(AdError.AdErrorType.LOAD, AdError.AdErrorCode.INTERNAL_ERROR, "Ad loaded message did not contain adData.");
        return;
      case 15: 
        localb.a(AdEvent.AdEventType.CONTENT_PAUSE_REQUESTED, null);
        return;
      case 16: 
        localb.a(AdEvent.AdEventType.CONTENT_RESUME_REQUESTED, null);
        return;
      case 17: 
        localb.a(AdEvent.AdEventType.COMPLETED, locala);
        return;
      case 18: 
        localb.a(AdEvent.AdEventType.ALL_ADS_COMPLETED, null);
        return;
      case 19: 
        ((VideoAdPlayer)this.d.get(paramString)).stopAd();
        localb.a(AdEvent.AdEventType.SKIPPED, locala);
        return;
      case 20: 
        localb.a(AdEvent.AdEventType.STARTED, locala);
        return;
      case 9: 
        localb.a(AdEvent.AdEventType.PAUSED, locala);
        return;
      case 21: 
        localb.a(AdEvent.AdEventType.RESUMED, locala);
        return;
      case 22: 
        localb.a(AdEvent.AdEventType.FIRST_QUARTILE, locala);
        return;
      case 23: 
        localb.a(AdEvent.AdEventType.MIDPOINT, locala);
        return;
      case 24: 
        localb.a(AdEvent.AdEventType.THIRD_QUARTILE, locala);
        return;
      case 25: 
        localb.a(AdEvent.AdEventType.CLICKED, locala);
        return;
      case 7: 
        localb.a(AdError.AdErrorType.PLAY, parame.errorCode, a(parame.errorMessage, parame.innerError));
        return;
      }
      localb.a(AdEvent.AdEventType.LOG, locala, parame.logData.constructMap());
      return;
    }
  }
  
  public WebView a()
  {
    return this.g.a();
  }
  
  public void a(AdDisplayContainer paramAdDisplayContainer, String paramString)
  {
    this.e.put(paramString, paramAdDisplayContainer);
  }
  
  public void a(VideoAdPlayer paramVideoAdPlayer, String paramString)
  {
    this.d.put(paramString, paramVideoAdPlayer);
  }
  
  public void a(r paramr)
  {
    e locale = (e)paramr.c();
    String str = paramr.d();
    r.c localc = paramr.b();
    switch (1.a[paramr.a().ordinal()])
    {
    default: 
      Log.e("IMASDK", "Unknown message channel: " + paramr.a());
      return;
    case 1: 
      f(localc, str, locale);
      return;
    case 2: 
      e(localc, str, locale);
      return;
    case 3: 
      d(localc, str, locale);
      return;
    case 4: 
      b(localc, str, locale);
      return;
    case 5: 
      c(localc, str, locale);
      return;
    }
    a(localc, str, locale);
  }
  
  public void a(a parama, String paramString)
  {
    this.b.put(paramString, parama);
  }
  
  public void a(b paramb, String paramString)
  {
    this.a.put(paramString, paramb);
  }
  
  public void a(c paramc, String paramString)
  {
    this.c.put(paramString, paramc);
  }
  
  public void a(String paramString)
  {
    this.c.remove(paramString);
  }
  
  public void b(r paramr)
  {
    this.j.add(paramr);
    b();
  }
  
  public void b(String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
    this.f.startActivity(localIntent);
  }
  
  public static abstract interface a
  {
    public abstract void a(String paramString1, AdError.AdErrorType paramAdErrorType, int paramInt, String paramString2);
    
    public abstract void a(String paramString1, AdError.AdErrorType paramAdErrorType, AdError.AdErrorCode paramAdErrorCode, String paramString2);
    
    public abstract void a(String paramString, u paramu, List<Float> paramList, SortedSet<Float> paramSortedSet);
  }
  
  public static abstract interface b
  {
    public abstract void a(AdError.AdErrorType paramAdErrorType, int paramInt, String paramString);
    
    public abstract void a(AdError.AdErrorType paramAdErrorType, AdError.AdErrorCode paramAdErrorCode, String paramString);
    
    public abstract void a(AdEvent.AdEventType paramAdEventType, com.google.ads.interactivemedia.v3.b.a.a parama);
    
    public abstract void a(AdEvent.AdEventType paramAdEventType, com.google.ads.interactivemedia.v3.b.a.a parama, Map<String, String> paramMap);
  }
  
  public static abstract interface c
  {
    public abstract void a(r.c paramc, String paramString);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.b.s
 * JD-Core Version:    0.7.0.1
 */