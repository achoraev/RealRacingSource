package com.millennialmedia.android;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.RelativeLayout.LayoutParams;
import java.lang.ref.WeakReference;
import java.util.Map;
import org.json.JSONObject;

abstract class MMAdImpl
  implements MMAd
{
  static final String BANNER = "b";
  static final String INTERSTITIAL = "i";
  private static final String TAG = "MMAdImpl";
  private static long nextAdViewId = 1L;
  String adType;
  String apid = "28911";
  WeakReference<Context> contextRef;
  MMAdImplController controller;
  boolean ignoreDensityScaling = false;
  long internalId;
  boolean isFinishing;
  long lastAdRequest;
  long linkForExpansionId;
  protected MMRequest mmRequest;
  MMWebViewClient mmWebViewClient;
  MMWebViewClient.MMWebViewClientListener mmWebViewClientListener;
  JSONObject obj;
  RequestListener requestListener;
  String userData;
  
  public MMAdImpl(Context paramContext)
  {
    this.contextRef = new WeakReference(paramContext);
    this.mmWebViewClientListener = new BasicWebViewClientListener(this);
    try
    {
      this.internalId = nextAdViewId;
      nextAdViewId = 1L + nextAdViewId;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Long.valueOf(this.internalId);
      MMLog.v("MMAdImpl", String.format("Assigning MMAdImpl internal id: %d", arrayOfObject));
      return;
    }
    finally {}
  }
  
  static String[] getAdTypes()
  {
    return new String[] { "b", "i" };
  }
  
  void addView(MMWebView paramMMWebView, RelativeLayout.LayoutParams paramLayoutParams) {}
  
  void animateTransition() {}
  
  public String getApid()
  {
    return this.apid;
  }
  
  String getCachedName()
  {
    if ((this.adType != null) && (this.apid != null)) {
      return this.adType + "_" + this.apid;
    }
    return null;
  }
  
  abstract MMAd getCallingAd();
  
  Context getContext()
  {
    if (this.contextRef != null) {
      return (Context)this.contextRef.get();
    }
    return null;
  }
  
  int getId()
  {
    return -1;
  }
  
  public boolean getIgnoresDensityScaling()
  {
    return this.ignoreDensityScaling;
  }
  
  public RequestListener getListener()
  {
    return this.requestListener;
  }
  
  public MMRequest getMMRequest()
  {
    return this.mmRequest;
  }
  
  MMWebViewClient getMMWebViewClient()
  {
    MMLog.d("MMAdImpl", "Returning a client for user: DefaultWebViewClient, adimpl=" + this);
    return new BannerWebViewClient(this.mmWebViewClientListener, new MMAdImplRedirectionListenerImpl(this));
  }
  
  String getReqType()
  {
    return "fetch";
  }
  
  String getRequestCompletedAction()
  {
    return "millennialmedia.action.ACTION_FETCH_SUCCEEDED";
  }
  
  String getRequestFailedAction()
  {
    return "millennialmedia.action.ACTION_FETCH_FAILED";
  }
  
  public boolean hasCachedVideoSupport()
  {
    return true;
  }
  
  void insertUrlAdMetaValues(Map<String, String> paramMap)
  {
    Context localContext = getContext();
    paramMap.put("apid", this.apid);
    paramMap.put("do", MMSDK.getOrientation(localContext));
    paramMap.put("olock", MMSDK.getOrientationLocked(localContext));
    if (!hasCachedVideoSupport()) {
      paramMap.put("cachedvideo", "false");
    }
    paramMap.put("reqtype", getReqType());
    if (this.mmRequest != null) {
      this.mmRequest.getUrlParams(paramMap);
    }
    if (HandShake.sharedHandShake(localContext).canRequestVideo(localContext, this.adType)) {
      paramMap.put("video", "true");
    }
    while (this.adType != null) {
      if ((this.adType.equals("b")) || (this.adType.equals("i")))
      {
        paramMap.put("at", this.adType);
        return;
        paramMap.put("video", "false");
      }
      else
      {
        MMLog.e("MMAdImpl", "******* ERROR: INCORRECT AD TYPE IN MMADVIEW OBJECT PARAMETERS (" + this.adType + ") **********");
        return;
      }
    }
    MMLog.e("MMAdImpl", "******* SDK DEFAULTED TO MMBannerAdBottom. THIS MAY AFFECT THE ADS YOU RECIEVE!!! **********");
    paramMap.put("at", "b");
  }
  
  public boolean isBanner()
  {
    return false;
  }
  
  boolean isExpandingToUrl()
  {
    return false;
  }
  
  boolean isLifecycleObservable()
  {
    return false;
  }
  
  boolean isRefreshable()
  {
    if (MMSDK.disableAdMinRefresh)
    {
      MMLog.d("MMAdImpl", "Minimum adrefresh time ignored");
      return true;
    }
    long l1 = System.currentTimeMillis();
    int i = (int)((l1 - this.lastAdRequest) / 1000L);
    long l2 = HandShake.sharedHandShake(getContext()).adRefreshSecs;
    if (i >= l2)
    {
      this.lastAdRequest = l1;
      return true;
    }
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = Integer.valueOf(i);
    arrayOfObject[1] = Long.valueOf(l2 - i);
    MMLog.d("MMAdImpl", String.format("Cannot request ad. Last ad request was %d seconds ago. Next ad can be requested in %d seconds.", arrayOfObject));
    return false;
  }
  
  boolean isTransitionAnimated()
  {
    return false;
  }
  
  boolean isUpdatingMraid()
  {
    return (this.controller != null) && (this.controller.webView != null) && (!this.controller.webView.isExpanding);
  }
  
  void prepareTransition(Bitmap paramBitmap) {}
  
  void removeProgressBar() {}
  
  void removeView(MMWebView paramMMWebView) {}
  
  void requestAd()
  {
    MMAdImplController.assignAdViewController(this);
    if (this.controller != null) {
      this.controller.requestAd();
    }
  }
  
  public void setApid(String paramString)
  {
    if ((paramString != null) && (!paramString.isEmpty())) {
      HandShake.apid = paramString;
    }
    this.apid = paramString;
  }
  
  void setClickable(boolean paramBoolean) {}
  
  public void setIgnoresDensityScaling(boolean paramBoolean)
  {
    this.ignoreDensityScaling = paramBoolean;
  }
  
  public void setListener(RequestListener paramRequestListener)
  {
    this.requestListener = paramRequestListener;
  }
  
  public void setMMRequest(MMRequest paramMMRequest)
  {
    this.mmRequest = paramMMRequest;
  }
  
  public String toString()
  {
    return "AdType[(" + this.adType + ") InternalId(" + this.internalId + ") LinkedId(" + this.linkForExpansionId + ") isFinishing(" + this.isFinishing + ")]";
  }
  
  void unresizeToDefault()
  {
    if (this.controller != null) {
      this.controller.unresizeToDefault();
    }
  }
  
  static class BasicWebViewClientListener
    extends MMWebViewClient.MMWebViewClientListener
  {
    WeakReference<MMAdImpl> adImplRef;
    
    BasicWebViewClientListener(MMAdImpl paramMMAdImpl)
    {
      this.adImplRef = new WeakReference(paramMMAdImpl);
    }
    
    public void onPageFinished(String paramString)
    {
      MMAdImpl localMMAdImpl = (MMAdImpl)this.adImplRef.get();
      if (localMMAdImpl != null)
      {
        localMMAdImpl.setClickable(true);
        if ((localMMAdImpl.controller != null) && (localMMAdImpl.controller.webView != null)) {
          synchronized (localMMAdImpl.controller.webView)
          {
            if (localMMAdImpl.controller.webView.hasWindowFocus())
            {
              localMMAdImpl.controller.webView.setMraidViewableVisible();
              return;
            }
            localMMAdImpl.controller.webView.setMraidViewableHidden();
          }
        }
      }
    }
    
    void onPageStarted(String paramString)
    {
      MMAdImpl localMMAdImpl = (MMAdImpl)this.adImplRef.get();
      if (localMMAdImpl != null) {
        localMMAdImpl.setClickable(false);
      }
    }
  }
  
  static class MMAdImplRedirectionListenerImpl
    extends HttpRedirection.RedirectionListenerImpl
  {
    WeakReference<MMAdImpl> adImplRef;
    
    public MMAdImplRedirectionListenerImpl(MMAdImpl paramMMAdImpl)
    {
      if (paramMMAdImpl != null)
      {
        this.adImplRef = new WeakReference(paramMMAdImpl);
        this.creatorAdImplInternalId = paramMMAdImpl.internalId;
      }
    }
    
    public boolean isActivityStartable(Uri paramUri)
    {
      MMAdImpl localMMAdImpl = (MMAdImpl)this.adImplRef.get();
      if (localMMAdImpl != null)
      {
        Context localContext = localMMAdImpl.getContext();
        if ((localContext != null) && ((localContext instanceof Activity)) && (((Activity)localContext).isFinishing())) {
          return false;
        }
      }
      return true;
    }
    
    public void startingActivity(Uri paramUri)
    {
      super.startingActivity(paramUri);
      if ((paramUri.getScheme().equalsIgnoreCase("http")) || (paramUri.getScheme().equalsIgnoreCase("https")))
      {
        MMAdImpl localMMAdImpl = (MMAdImpl)this.adImplRef.get();
        if (localMMAdImpl != null) {
          MMSDK.Event.overlayOpened(localMMAdImpl);
        }
      }
    }
    
    public void updateLastVideoViewedTime()
    {
      MMAdImpl localMMAdImpl = (MMAdImpl)this.adImplRef.get();
      if ((localMMAdImpl != null) && (localMMAdImpl.adType != null)) {
        HandShake.sharedHandShake(localMMAdImpl.getContext()).updateLastVideoViewedTime(localMMAdImpl.getContext(), localMMAdImpl.adType);
      }
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.MMAdImpl
 * JD-Core Version:    0.7.0.1
 */