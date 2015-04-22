package com.google.ads.interactivemedia.v3.b;

import android.net.Uri;
import com.google.ads.interactivemedia.v3.a.f;
import com.google.ads.interactivemedia.v3.a.g;
import com.google.ads.interactivemedia.v3.a.l;
import com.google.ads.interactivemedia.v3.a.q;
import com.google.ads.interactivemedia.v3.a.s;
import com.google.ads.interactivemedia.v3.a.t;
import com.google.ads.interactivemedia.v3.api.CompanionAdSlot;
import com.google.ads.interactivemedia.v3.b.a.e;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

public class r
{
  private static final f a = new g().a(CompanionAdSlot.class, new s()
  {
    public l a(CompanionAdSlot paramAnonymousCompanionAdSlot, Type paramAnonymousType, com.google.ads.interactivemedia.v3.a.r paramAnonymousr)
    {
      return new q(paramAnonymousCompanionAdSlot.getWidth() + "x" + paramAnonymousCompanionAdSlot.getHeight());
    }
  }).a();
  private final b b;
  private final Object c;
  private final String d;
  private final c e;
  
  public r(b paramb, c paramc, String paramString)
  {
    this(paramb, paramc, paramString, null);
  }
  
  public r(b paramb, c paramc, String paramString, Object paramObject)
  {
    this.b = paramb;
    this.e = paramc;
    this.d = paramString;
    this.c = paramObject;
  }
  
  public static r a(String paramString)
    throws MalformedURLException, t
  {
    Uri localUri = Uri.parse(paramString);
    String str = localUri.getPath().substring(1);
    if (localUri.getQueryParameter("sid") == null) {
      throw new MalformedURLException("Session id must be provided in message.");
    }
    return new r(b.valueOf(str), c.valueOf(localUri.getQueryParameter("type")), localUri.getQueryParameter("sid"), a.a(localUri.getQueryParameter("data"), e.class));
  }
  
  public b a()
  {
    return this.b;
  }
  
  public c b()
  {
    return this.e;
  }
  
  public Object c()
  {
    return this.c;
  }
  
  public String d()
  {
    return this.d;
  }
  
  public String e()
  {
    HashMap localHashMap = new HashMap(3);
    localHashMap.put("type", this.e);
    localHashMap.put("sid", this.d);
    localHashMap.put("data", this.c);
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = "javascript:adsense.mobileads.afmanotify.receiveMessage";
    arrayOfObject[1] = this.b;
    arrayOfObject[2] = a.a(localHashMap);
    return String.format("%s('%s', %s);", arrayOfObject);
  }
  
  public String toString()
  {
    Object[] arrayOfObject = new Object[4];
    arrayOfObject[0] = this.b;
    arrayOfObject[1] = this.e;
    arrayOfObject[2] = this.d;
    arrayOfObject[3] = this.c;
    return String.format("JavaScriptMessage [command=%s, type=%s, sid=%s, data=%s]", arrayOfObject);
  }
  
  public static enum a
  {
    static
    {
      a[] arrayOfa = new a[3];
      arrayOfa[0] = a;
      arrayOfa[1] = b;
      arrayOfa[2] = c;
      d = arrayOfa;
    }
    
    private a() {}
  }
  
  public static enum b
  {
    static
    {
      adsLoader = new b("adsLoader", 1);
      contentTimeUpdate = new b("contentTimeUpdate", 2);
      displayContainer = new b("displayContainer", 3);
      i18n = new b("i18n", 4);
      log = new b("log", 5);
      videoDisplay = new b("videoDisplay", 6);
      webViewLoaded = new b("webViewLoaded", 7);
      b[] arrayOfb = new b[8];
      arrayOfb[0] = adsManager;
      arrayOfb[1] = adsLoader;
      arrayOfb[2] = contentTimeUpdate;
      arrayOfb[3] = displayContainer;
      arrayOfb[4] = i18n;
      arrayOfb[5] = log;
      arrayOfb[6] = videoDisplay;
      arrayOfb[7] = webViewLoaded;
      a = arrayOfb;
    }
    
    private b() {}
  }
  
  public static enum c
  {
    static
    {
      companionView = new c("companionView", 5);
      contentComplete = new c("contentComplete", 6);
      contentPauseRequested = new c("contentPauseRequested", 7);
      contentResumeRequested = new c("contentResumeRequested", 8);
      contentTimeUpdate = new c("contentTimeUpdate", 9);
      csi = new c("csi", 10);
      displayCompanions = new c("displayCompanions", 11);
      destroy = new c("destroy", 12);
      end = new c("end", 13);
      error = new c("error", 14);
      firstquartile = new c("firstquartile", 15);
      fullscreen = new c("fullscreen", 16);
      hide = new c("hide", 17);
      init = new c("init", 18);
      initialized = new c("initialized", 19);
      load = new c("load", 20);
      loaded = new c("loaded", 21);
      log = new c("log", 22);
      midpoint = new c("midpoint", 23);
      mute = new c("mute", 24);
      pause = new c("pause", 25);
      play = new c("play", 26);
      resume = new c("resume", 27);
      requestAds = new c("requestAds", 28);
      showVideo = new c("showVideo", 29);
      skip = new c("skip", 30);
      start = new c("start", 31);
      startTracking = new c("startTracking", 32);
      stop = new c("stop", 33);
      stopTracking = new c("stopTracking", 34);
      thirdquartile = new c("thirdquartile", 35);
      timeupdate = new c("timeupdate", 36);
      unmute = new c("unmute", 37);
      adRemainingTime = new c("adRemainingTime", 38);
      learnMore = new c("learnMore", 39);
      preSkipButton = new c("preSkipButton", 40);
      skipButton = new c("skipButton", 41);
      c[] arrayOfc = new c[42];
      arrayOfc[0] = adMetadata;
      arrayOfc[1] = adsLoaded;
      arrayOfc[2] = allAdsCompleted;
      arrayOfc[3] = click;
      arrayOfc[4] = complete;
      arrayOfc[5] = companionView;
      arrayOfc[6] = contentComplete;
      arrayOfc[7] = contentPauseRequested;
      arrayOfc[8] = contentResumeRequested;
      arrayOfc[9] = contentTimeUpdate;
      arrayOfc[10] = csi;
      arrayOfc[11] = displayCompanions;
      arrayOfc[12] = destroy;
      arrayOfc[13] = end;
      arrayOfc[14] = error;
      arrayOfc[15] = firstquartile;
      arrayOfc[16] = fullscreen;
      arrayOfc[17] = hide;
      arrayOfc[18] = init;
      arrayOfc[19] = initialized;
      arrayOfc[20] = load;
      arrayOfc[21] = loaded;
      arrayOfc[22] = log;
      arrayOfc[23] = midpoint;
      arrayOfc[24] = mute;
      arrayOfc[25] = pause;
      arrayOfc[26] = play;
      arrayOfc[27] = resume;
      arrayOfc[28] = requestAds;
      arrayOfc[29] = showVideo;
      arrayOfc[30] = skip;
      arrayOfc[31] = start;
      arrayOfc[32] = startTracking;
      arrayOfc[33] = stop;
      arrayOfc[34] = stopTracking;
      arrayOfc[35] = thirdquartile;
      arrayOfc[36] = timeupdate;
      arrayOfc[37] = unmute;
      arrayOfc[38] = adRemainingTime;
      arrayOfc[39] = learnMore;
      arrayOfc[40] = preSkipButton;
      arrayOfc[41] = skipButton;
      a = arrayOfc;
    }
    
    private c() {}
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.b.r
 * JD-Core Version:    0.7.0.1
 */