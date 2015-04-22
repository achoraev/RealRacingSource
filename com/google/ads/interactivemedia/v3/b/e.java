package com.google.ads.interactivemedia.v3.b;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.util.Log;
import com.google.ads.interactivemedia.v3.api.AdDisplayContainer;
import com.google.ads.interactivemedia.v3.api.AdError;
import com.google.ads.interactivemedia.v3.api.AdError.AdErrorCode;
import com.google.ads.interactivemedia.v3.api.AdError.AdErrorType;
import com.google.ads.interactivemedia.v3.api.AdErrorEvent.AdErrorListener;
import com.google.ads.interactivemedia.v3.api.AdsLoader;
import com.google.ads.interactivemedia.v3.api.AdsLoader.AdsLoadedListener;
import com.google.ads.interactivemedia.v3.api.AdsManagerLoadedEvent;
import com.google.ads.interactivemedia.v3.api.AdsRequest;
import com.google.ads.interactivemedia.v3.api.ImaSdkSettings;
import com.google.ads.interactivemedia.v3.b.a.d;
import com.google.android.a.h;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.SortedSet;

public class e
  implements AdsLoader
{
  private final s a;
  private final Context b;
  private final o c = new o();
  private final List<AdsLoader.AdsLoadedListener> d = new ArrayList(1);
  private final Map<String, AdsRequest> e = new HashMap();
  private com.google.android.a.g f;
  private ImaSdkSettings g = new ImaSdkSettings();
  
  public e(Context paramContext, Uri paramUri, ImaSdkSettings paramImaSdkSettings)
  {
    this(new s(paramContext, paramUri, paramImaSdkSettings), paramContext);
    this.g = paramImaSdkSettings;
  }
  
  public e(s params, Context paramContext)
  {
    this.a = params;
    this.b = paramContext;
    this.f = new com.google.android.a.g(com.google.android.a.f.a("a.3.0b8", paramContext));
  }
  
  private String a()
  {
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = Build.VERSION.RELEASE;
    arrayOfObject[1] = "3.0b8";
    arrayOfObject[2] = this.b.getPackageName();
    return String.format("android%s:%s:%s", arrayOfObject);
  }
  
  private boolean a(AdsRequest paramAdsRequest)
  {
    if (paramAdsRequest == null)
    {
      this.c.a(new b(new AdError(AdError.AdErrorType.LOAD, AdError.AdErrorCode.INVALID_ARGUMENTS, "AdsRequest cannot be null.")));
      return false;
    }
    AdDisplayContainer localAdDisplayContainer = paramAdsRequest.getAdDisplayContainer();
    if (localAdDisplayContainer == null)
    {
      this.c.a(new b(new AdError(AdError.AdErrorType.LOAD, AdError.AdErrorCode.INVALID_ARGUMENTS, "Ad display container must be provided in the AdsRequest.")));
      return false;
    }
    if (localAdDisplayContainer.getAdContainer() == null)
    {
      this.c.a(new b(new AdError(AdError.AdErrorType.LOAD, AdError.AdErrorCode.INVALID_ARGUMENTS, "Ad display container must have a UI container.")));
      return false;
    }
    if ((paramAdsRequest.getAdTagUrl() == null) || (paramAdsRequest.getAdTagUrl().length() == 0))
    {
      this.c.a(new b(new AdError(AdError.AdErrorType.LOAD, AdError.AdErrorCode.INVALID_ARGUMENTS, "Ad tag url must non-null and non empty.")));
      return false;
    }
    return true;
  }
  
  private String b()
  {
    if (this.b.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") != 0)
    {
      Log.w("IMASDK", "Host application doesn't have ACCESS_NETWORK_STATE permission");
      return "android:0";
    }
    NetworkInfo localNetworkInfo = ((ConnectivityManager)this.b.getSystemService("connectivity")).getActiveNetworkInfo();
    if (localNetworkInfo == null) {
      return "android:0";
    }
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = Integer.valueOf(localNetworkInfo.getType());
    arrayOfObject[1] = Integer.valueOf(localNetworkInfo.getSubtype());
    return String.format("android:%d:%d", arrayOfObject);
  }
  
  void a(AdsManagerLoadedEvent paramAdsManagerLoadedEvent)
  {
    Iterator localIterator = this.d.iterator();
    while (localIterator.hasNext()) {
      ((AdsLoader.AdsLoadedListener)localIterator.next()).onAdsManagerLoaded(paramAdsManagerLoadedEvent);
    }
  }
  
  void a(AdsRequest paramAdsRequest, String paramString)
  {
    if (!a(paramAdsRequest)) {
      return;
    }
    this.e.put(paramString, paramAdsRequest);
    this.a.a(new s.a()
    {
      public void a(String paramAnonymousString1, AdError.AdErrorType paramAnonymousAdErrorType, int paramAnonymousInt, String paramAnonymousString2)
      {
        b localb = new b(new AdError(paramAnonymousAdErrorType, paramAnonymousInt, paramAnonymousString2), ((AdsRequest)e.g(e.this).get(paramAnonymousString1)).getUserRequestContext());
        e.h(e.this).a(localb);
      }
      
      public void a(String paramAnonymousString1, AdError.AdErrorType paramAnonymousAdErrorType, AdError.AdErrorCode paramAnonymousAdErrorCode, String paramAnonymousString2)
      {
        b localb = new b(new AdError(paramAnonymousAdErrorType, paramAnonymousAdErrorCode, paramAnonymousString2), ((AdsRequest)e.g(e.this).get(paramAnonymousString1)).getUserRequestContext());
        e.h(e.this).a(localb);
      }
      
      public void a(String paramAnonymousString, u paramAnonymousu, List<Float> paramAnonymousList, SortedSet<Float> paramAnonymousSortedSet)
      {
        AdsRequest localAdsRequest = (AdsRequest)e.g(e.this).get(paramAnonymousString);
        v localv = new v(localAdsRequest.getAdDisplayContainer().getPlayer(), paramAnonymousu.a());
        e.this.a(new g(new f(paramAnonymousString, e.f(e.this), paramAnonymousu, localAdsRequest.getAdDisplayContainer(), paramAnonymousList, paramAnonymousSortedSet, localv, e.b(e.this)), localAdsRequest.getUserRequestContext()));
      }
    }, paramString);
    this.a.a(paramAdsRequest.getAdDisplayContainer(), paramString);
    a locala = new a(paramAdsRequest, paramString);
    String[] arrayOfString = new String[1];
    arrayOfString[0] = paramAdsRequest.getAdTagUrl();
    locala.execute(arrayOfString);
  }
  
  public void addAdErrorListener(AdErrorEvent.AdErrorListener paramAdErrorListener)
  {
    this.c.a(paramAdErrorListener);
  }
  
  public void addAdsLoadedListener(AdsLoader.AdsLoadedListener paramAdsLoadedListener)
  {
    this.d.add(paramAdsLoadedListener);
  }
  
  public void contentComplete()
  {
    this.a.b(new r(r.b.adsLoader, r.c.contentComplete, "*"));
  }
  
  public ImaSdkSettings getSettings()
  {
    return this.g;
  }
  
  public void removeAdErrorListener(AdErrorEvent.AdErrorListener paramAdErrorListener)
  {
    this.c.b(paramAdErrorListener);
  }
  
  public void removeAdsLoadedListener(AdsLoader.AdsLoadedListener paramAdsLoadedListener)
  {
    this.d.remove(paramAdsLoadedListener);
  }
  
  public void requestAds(AdsRequest paramAdsRequest)
  {
    a(paramAdsRequest, "ima_sid_" + Integer.valueOf(new Random().nextInt(1000000000)).toString());
  }
  
  private class a
    extends AsyncTask<String, Void, String>
  {
    private AdsRequest b;
    private String c;
    
    public a(AdsRequest paramAdsRequest, String paramString)
    {
      this.b = paramAdsRequest;
      this.c = paramString;
    }
    
    protected String a(String... paramVarArgs)
    {
      Object localObject = paramVarArgs[0];
      try
      {
        if ((e.a(e.this) != null) && (localObject != null))
        {
          Uri localUri = Uri.parse((String)localObject);
          if (e.a(e.this).b(localUri))
          {
            String str = e.a(e.this).a(localUri, e.b(e.this)).toString();
            localObject = str;
          }
        }
        return localObject;
      }
      catch (h localh) {}
      return localObject;
    }
    
    protected void a(String paramString)
    {
      this.b.setAdTagUrl(paramString);
      d locald = new d(this.b, e.c(e.this), e.d(e.this), e.e(e.this));
      r localr = new r(r.b.adsLoader, r.c.requestAds, this.c, locald);
      e.f(e.this).b(localr);
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.b.e
 * JD-Core Version:    0.7.0.1
 */