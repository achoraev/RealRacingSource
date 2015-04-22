package com.millennialmedia.android;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

class MMAdImplController
  implements AdCache.AdCacheTaskListener
{
  static final long NO_ID_RETURNED = -4L;
  private static final String TAG = "MMAdImplController";
  private static final Map<Long, MMAdImplController> saveableControllers = new ConcurrentHashMap();
  private static final Map<Long, WeakReference<MMAdImplController>> weakUnsaveableAdRef = new ConcurrentHashMap();
  volatile WeakReference<MMAdImpl> adImplRef;
  volatile long linkedAdImplId;
  RequestAdRunnable requestAdRunnable;
  volatile MMWebView webView;
  
  private MMAdImplController(MMAdImpl paramMMAdImpl)
  {
    MMLog.d("MMAdImplController", "**************** creating new controller.");
    this.adImplRef = new WeakReference(paramMMAdImpl);
    if (paramMMAdImpl.linkForExpansionId != 0L)
    {
      linkForExpansion(paramMMAdImpl);
      this.webView = getWebViewFromExistingAdImpl(paramMMAdImpl);
    }
    while ((paramMMAdImpl instanceof MMInterstitial.MMInterstitialAdImpl)) {
      return;
    }
    if (paramMMAdImpl.isBanner())
    {
      this.webView = new MMWebView(paramMMAdImpl.getContext().getApplicationContext(), paramMMAdImpl.internalId);
      this.webView.requiresPreAdSizeFix = true;
      return;
    }
    this.webView = new MMWebView(paramMMAdImpl.getContext(), paramMMAdImpl.internalId);
  }
  
  static void assignAdViewController(MMAdImpl paramMMAdImpl)
  {
    try
    {
      if (paramMMAdImpl.controller != null)
      {
        if (!saveableControllers.containsValue(paramMMAdImpl.controller))
        {
          if (!paramMMAdImpl.isLifecycleObservable()) {
            break label114;
          }
          saveableControllers.put(Long.valueOf(paramMMAdImpl.internalId), paramMMAdImpl.controller);
          if (weakUnsaveableAdRef.containsKey(Long.valueOf(paramMMAdImpl.internalId))) {
            weakUnsaveableAdRef.remove(Long.valueOf(paramMMAdImpl.internalId));
          }
        }
        for (;;)
        {
          MMLog.d("MMAdImplController", paramMMAdImpl + " - Has a controller");
          return;
          label114:
          if (!weakUnsaveableAdRef.containsKey(Long.valueOf(paramMMAdImpl.internalId))) {
            weakUnsaveableAdRef.put(Long.valueOf(paramMMAdImpl.internalId), new WeakReference(paramMMAdImpl.controller));
          }
        }
      }
      MMLog.d("MMAdImplController", "*****************************************assignAdViewController for " + paramMMAdImpl);
    }
    finally {}
    MMAdImplController localMMAdImplController = (MMAdImplController)saveableControllers.get(Long.valueOf(paramMMAdImpl.internalId));
    if (localMMAdImplController == null)
    {
      WeakReference localWeakReference = (WeakReference)weakUnsaveableAdRef.get(Long.valueOf(paramMMAdImpl.internalId));
      if (localWeakReference != null) {
        localMMAdImplController = (MMAdImplController)localWeakReference.get();
      }
      if (localMMAdImplController == null)
      {
        localMMAdImplController = new MMAdImplController(paramMMAdImpl);
        if (!paramMMAdImpl.isLifecycleObservable()) {
          break label321;
        }
        saveableControllers.put(Long.valueOf(paramMMAdImpl.internalId), localMMAdImplController);
      }
    }
    for (;;)
    {
      paramMMAdImpl.controller = localMMAdImplController;
      localMMAdImplController.adImplRef = new WeakReference(paramMMAdImpl);
      if ((localMMAdImplController.webView == null) || ((paramMMAdImpl instanceof MMInterstitial.MMInterstitialAdImpl))) {
        break;
      }
      setupWebView(paramMMAdImpl);
      break;
      label321:
      weakUnsaveableAdRef.put(Long.valueOf(paramMMAdImpl.internalId), new WeakReference(localMMAdImplController));
    }
  }
  
  static boolean attachWebViewFromOverlay(MMAdImpl paramMMAdImpl)
  {
    boolean bool = false;
    if (paramMMAdImpl == null) {}
    for (;;)
    {
      return bool;
      try
      {
        MMLog.d("MMAdImplController", "attachWebViewFromOverlay with " + paramMMAdImpl);
        if ((paramMMAdImpl.controller != null) && (paramMMAdImpl.controller.webView != null)) {
          paramMMAdImpl.controller.webView.resetSpeechKit();
        }
        MMAdImpl localMMAdImpl = getAdImplWithId(paramMMAdImpl.linkForExpansionId);
        bool = false;
        if (localMMAdImpl == null) {
          continue;
        }
        MMAdImplController localMMAdImplController1 = localMMAdImpl.controller;
        bool = false;
        if (localMMAdImplController1 == null) {
          continue;
        }
        if (localMMAdImpl.controller.webView == null)
        {
          MMAdImplController localMMAdImplController2 = paramMMAdImpl.controller;
          bool = false;
          if (localMMAdImplController2 == null) {
            continue;
          }
          MMWebView localMMWebView = paramMMAdImpl.controller.webView;
          bool = false;
          if (localMMWebView == null) {
            continue;
          }
          localMMAdImpl.controller.webView = paramMMAdImpl.controller.webView;
          paramMMAdImpl.removeView(paramMMAdImpl.controller.webView);
          paramMMAdImpl.controller.webView = null;
        }
        localMMAdImpl.controller.webView.setMraidDefault();
        localMMAdImpl.controller.webView.setWebViewClient(localMMAdImpl.getMMWebViewClient());
        bool = true;
      }
      finally {}
    }
  }
  
  static String controllersToString()
  {
    return weakUnsaveableAdRef.toString() + " SAVED:" + saveableControllers.toString();
  }
  
  static void destroyOtherInlineVideo(Context paramContext)
  {
    Iterator localIterator = saveableControllers.entrySet().iterator();
    while (localIterator.hasNext())
    {
      MMAdImplController localMMAdImplController = (MMAdImplController)((Map.Entry)localIterator.next()).getValue();
      if (localMMAdImplController != null)
      {
        MMAdImpl localMMAdImpl = (MMAdImpl)localMMAdImplController.adImplRef.get();
        if (localMMAdImpl != null)
        {
          MMAd localMMAd = localMMAdImpl.getCallingAd();
          if ((localMMAd != null) && ((localMMAd instanceof MMLayout))) {
            ((MMLayout)localMMAd).removeVideo();
          }
        }
      }
    }
  }
  
  static MMAdImpl getAdImplWithId(long paramLong)
  {
    boolean bool = paramLong < -4L;
    MMAdImpl localMMAdImpl = null;
    if (!bool) {}
    for (;;)
    {
      return localMMAdImpl;
      try
      {
        MMAdImplController localMMAdImplController = (MMAdImplController)saveableControllers.get(Long.valueOf(paramLong));
        if (localMMAdImplController == null)
        {
          WeakReference localWeakReference = (WeakReference)weakUnsaveableAdRef.get(Long.valueOf(paramLong));
          if (localWeakReference != null) {
            localMMAdImplController = (MMAdImplController)localWeakReference.get();
          }
        }
        localMMAdImpl = null;
        if (localMMAdImplController == null) {
          continue;
        }
        localMMAdImpl = (MMAdImpl)localMMAdImplController.adImplRef.get();
      }
      finally {}
    }
  }
  
  static MMWebView getWebViewFromExistingAdImpl(MMAdImpl paramMMAdImpl)
  {
    try
    {
      MMLog.i("MMAdImplController", "getWebViewFromExistingLayout(" + paramMMAdImpl.internalId + " taking from " + paramMMAdImpl.linkForExpansionId + ")");
      MMAdImpl localMMAdImpl = getAdImplWithId(paramMMAdImpl.linkForExpansionId);
      MMWebView localMMWebView = null;
      if (localMMAdImpl != null)
      {
        MMAdImplController localMMAdImplController = localMMAdImpl.controller;
        localMMWebView = null;
        if (localMMAdImplController != null)
        {
          localMMWebView = localMMAdImpl.controller.webView;
          localMMAdImpl.controller.webView = null;
        }
      }
      return localMMWebView;
    }
    finally {}
  }
  
  private boolean isDownloadingCachedAd(MMAdImpl paramMMAdImpl)
  {
    boolean bool = true;
    for (;;)
    {
      try
      {
        Context localContext = paramMMAdImpl.getContext();
        if (HandShake.sharedHandShake(localContext).isAdTypeDownloading(paramMMAdImpl.adType))
        {
          MMLog.i("MMAdImplController", "There is a download in progress. Defering call for new ad");
          MMSDK.Event.requestFailed(paramMMAdImpl, new MMException(12));
          return bool;
        }
        MMLog.d("MMAdImplController", "No download in progress.");
        CachedAd localCachedAd = AdCache.loadIncompleteDownload(localContext, paramMMAdImpl.getCachedName());
        if (localCachedAd != null)
        {
          MMLog.i("MMAdImplController", "Last ad wasn't fully downloaded. Download again.");
          MMSDK.Event.fetchStartedCaching(paramMMAdImpl);
          AdCache.startDownloadTask(localContext, paramMMAdImpl.getCachedName(), localCachedAd, this);
          continue;
        }
        MMLog.i("MMAdImplController", "No incomplete downloads.");
      }
      finally {}
      bool = false;
    }
  }
  
  static void removeAdViewController(MMAdImpl paramMMAdImpl)
  {
    for (;;)
    {
      try
      {
        MMAdImplController localMMAdImplController1 = paramMMAdImpl.controller;
        if (localMMAdImplController1 == null) {
          return;
        }
        if (paramMMAdImpl.isLifecycleObservable())
        {
          saveableControllers.put(Long.valueOf(paramMMAdImpl.internalId), paramMMAdImpl.controller);
          if (weakUnsaveableAdRef.get(Long.valueOf(paramMMAdImpl.internalId)) != null) {
            weakUnsaveableAdRef.remove(Long.valueOf(paramMMAdImpl.internalId));
          }
          MMLog.d("MMAdImplController", "****************RemoveAdviewcontroller - " + paramMMAdImpl);
          if (paramMMAdImpl.isFinishing)
          {
            saveableControllers.remove(Long.valueOf(paramMMAdImpl.internalId));
            weakUnsaveableAdRef.remove(Long.valueOf(paramMMAdImpl.internalId));
          }
          MMAdImplController localMMAdImplController2 = paramMMAdImpl.controller;
          paramMMAdImpl.controller = null;
          MMLog.d("MMAdImplController", "****************RemoveAdviewcontroller - controllers " + controllersToString());
          if (localMMAdImplController2.webView != null)
          {
            MMLog.d("MMAdImplController", "****************RemoveAdviewcontroller - controller!=null, expanding=" + localMMAdImplController2.webView.isExpanding);
            paramMMAdImpl.removeView(localMMAdImplController2.webView);
            localMMAdImplController2.webView.isExpanding = false;
            if ((paramMMAdImpl.isFinishing) && (paramMMAdImpl.linkForExpansionId == 0L))
            {
              localMMAdImplController2.webView.loadData("<html></html>", "text/html", "UTF-8");
              localMMAdImplController2.webView.resetSpeechKit();
              localMMAdImplController2.webView = null;
            }
          }
        }
        else
        {
          weakUnsaveableAdRef.put(Long.valueOf(paramMMAdImpl.internalId), new WeakReference(paramMMAdImpl.controller));
        }
      }
      finally {}
    }
  }
  
  private void requestAdInternal(MMAdImpl paramMMAdImpl)
  {
    if (paramMMAdImpl.apid == null)
    {
      localMMException = new MMException("MMAdView found with a null apid. New ad requests on this MMAdView are disabled until an apid has been assigned.", 1);
      MMLog.e("MMAdImplController", localMMException.getMessage());
      MMSDK.Event.requestFailed(paramMMAdImpl, localMMException);
    }
    while ((!paramMMAdImpl.isBanner()) && (isDownloadingCachedAd(paramMMAdImpl)))
    {
      MMException localMMException;
      return;
    }
    try
    {
      if (this.requestAdRunnable != null)
      {
        MMLog.i("MMAdImplController", MMException.getErrorCodeMessage(12));
        MMSDK.Event.requestFailed(paramMMAdImpl, new MMException(12));
        return;
      }
    }
    finally {}
    this.requestAdRunnable = new RequestAdRunnable(null);
    Utils.ThreadUtils.execute(this.requestAdRunnable);
  }
  
  /* Error */
  private static void setupWebView(MMAdImpl paramMMAdImpl)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: aload_0
    //   4: getfield 101	com/millennialmedia/android/MMAdImpl:controller	Lcom/millennialmedia/android/MMAdImplController;
    //   7: astore_2
    //   8: aload_2
    //   9: getfield 69	com/millennialmedia/android/MMAdImplController:webView	Lcom/millennialmedia/android/MMWebView;
    //   12: ifnull +81 -> 93
    //   15: aload_2
    //   16: getfield 69	com/millennialmedia/android/MMAdImplController:webView	Lcom/millennialmedia/android/MMWebView;
    //   19: aload_0
    //   20: invokevirtual 178	com/millennialmedia/android/MMAdImpl:getMMWebViewClient	()Lcom/millennialmedia/android/MMWebViewClient;
    //   23: invokevirtual 182	com/millennialmedia/android/MMWebView:setWebViewClient	(Landroid/webkit/WebViewClient;)V
    //   26: aload_2
    //   27: getfield 69	com/millennialmedia/android/MMAdImplController:webView	Lcom/millennialmedia/android/MMWebView;
    //   30: aload_0
    //   31: getfield 89	com/millennialmedia/android/MMAdImpl:internalId	J
    //   34: invokevirtual 347	com/millennialmedia/android/MMWebView:isCurrentParent	(J)Z
    //   37: ifne +56 -> 93
    //   40: aload_0
    //   41: invokevirtual 75	com/millennialmedia/android/MMAdImpl:isBanner	()Z
    //   44: ifeq +53 -> 97
    //   47: new 349	android/widget/RelativeLayout$LayoutParams
    //   50: dup
    //   51: bipush 254
    //   53: bipush 254
    //   55: invokespecial 352	android/widget/RelativeLayout$LayoutParams:<init>	(II)V
    //   58: astore_3
    //   59: aload_2
    //   60: getfield 69	com/millennialmedia/android/MMAdImplController:webView	Lcom/millennialmedia/android/MMWebView;
    //   63: invokevirtual 355	com/millennialmedia/android/MMWebView:isMraidResized	()Z
    //   66: ifeq +11 -> 77
    //   69: aload_2
    //   70: getfield 69	com/millennialmedia/android/MMAdImplController:webView	Lcom/millennialmedia/android/MMWebView;
    //   73: aload_0
    //   74: invokevirtual 358	com/millennialmedia/android/MMWebView:unresizeToDefault	(Lcom/millennialmedia/android/MMAdImpl;)V
    //   77: aload_2
    //   78: getfield 69	com/millennialmedia/android/MMAdImplController:webView	Lcom/millennialmedia/android/MMWebView;
    //   81: invokevirtual 361	com/millennialmedia/android/MMWebView:removeFromParent	()V
    //   84: aload_0
    //   85: aload_2
    //   86: getfield 69	com/millennialmedia/android/MMAdImplController:webView	Lcom/millennialmedia/android/MMWebView;
    //   89: aload_3
    //   90: invokevirtual 365	com/millennialmedia/android/MMAdImpl:addView	(Lcom/millennialmedia/android/MMWebView;Landroid/widget/RelativeLayout$LayoutParams;)V
    //   93: ldc 2
    //   95: monitorexit
    //   96: return
    //   97: new 349	android/widget/RelativeLayout$LayoutParams
    //   100: dup
    //   101: bipush 254
    //   103: iconst_m1
    //   104: invokespecial 352	android/widget/RelativeLayout$LayoutParams:<init>	(II)V
    //   107: astore_3
    //   108: goto -31 -> 77
    //   111: astore_1
    //   112: ldc 2
    //   114: monitorexit
    //   115: aload_1
    //   116: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	117	0	paramMMAdImpl	MMAdImpl
    //   111	5	1	localObject	Object
    //   7	79	2	localMMAdImplController	MMAdImplController
    //   58	50	3	localLayoutParams	android.widget.RelativeLayout.LayoutParams
    // Exception table:
    //   from	to	target	type
    //   3	77	111	finally
    //   77	93	111	finally
    //   97	108	111	finally
  }
  
  int checkReason(MMAdImpl paramMMAdImpl, CachedAd paramCachedAd)
  {
    if (paramCachedAd.isExpired())
    {
      Object[] arrayOfObject3 = new Object[1];
      arrayOfObject3[0] = paramCachedAd.getId();
      MMLog.d("MMAdImplController", String.format("%s is expired.", arrayOfObject3));
      return 21;
    }
    if (!paramCachedAd.isOnDisk(paramMMAdImpl.getContext()))
    {
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = paramCachedAd.getId();
      MMLog.d("MMAdImplController", String.format("%s is not on disk.", arrayOfObject2));
      return 22;
    }
    if (!HandShake.sharedHandShake(paramMMAdImpl.getContext()).canDisplayCachedAd(paramMMAdImpl.adType, paramCachedAd.deferredViewStart))
    {
      Object[] arrayOfObject1 = new Object[1];
      arrayOfObject1[0] = paramCachedAd.getId();
      MMLog.d("MMAdImplController", String.format("%s cannot be shown at this time.", arrayOfObject1));
      return 24;
    }
    return 100;
  }
  
  int display(MMAdImpl paramMMAdImpl)
  {
    CachedAd localCachedAd = AdCache.loadNextCachedAd(paramMMAdImpl.getContext(), paramMMAdImpl.getCachedName());
    if (localCachedAd != null)
    {
      if (localCachedAd.canShow(paramMMAdImpl.getContext(), paramMMAdImpl, true))
      {
        MMSDK.Event.displayStarted(paramMMAdImpl);
        AdCache.setNextCachedAd(paramMMAdImpl.getContext(), paramMMAdImpl.getCachedName(), null);
        localCachedAd.show(paramMMAdImpl.getContext(), paramMMAdImpl.internalId);
        HandShake.sharedHandShake(paramMMAdImpl.getContext()).updateLastVideoViewedTime(paramMMAdImpl.getContext(), paramMMAdImpl.adType);
        return 0;
      }
      return checkReason(paramMMAdImpl, localCachedAd);
    }
    return 20;
  }
  
  public void downloadCompleted(CachedAd paramCachedAd, boolean paramBoolean)
  {
    MMAdImpl localMMAdImpl = (MMAdImpl)this.adImplRef.get();
    if (localMMAdImpl == null)
    {
      MMLog.e("MMAdImplController", MMException.getErrorCodeMessage(25));
      return;
    }
    if (paramBoolean) {
      AdCache.setNextCachedAd(localMMAdImpl.getContext(), localMMAdImpl.getCachedName(), paramCachedAd.getId());
    }
    if (paramBoolean)
    {
      MMSDK.Event.requestCompleted(localMMAdImpl);
      return;
    }
    MMSDK.Event.requestFailed(localMMAdImpl, new MMException(15));
  }
  
  public void downloadStart(CachedAd paramCachedAd) {}
  
  public String getDefaultUserAgentString(Context paramContext)
  {
    return System.getProperty("http.agent");
  }
  
  HttpMMHeaders getLastHeaders()
  {
    if (this.webView == null) {
      return null;
    }
    return this.webView.getLastHeaders();
  }
  
  String getUserAgent()
  {
    MMAdImpl localMMAdImpl = (MMAdImpl)this.adImplRef.get();
    String str = null;
    if (localMMAdImpl != null)
    {
      Context localContext = localMMAdImpl.getContext();
      str = null;
      if (localContext != null) {
        str = getDefaultUserAgentString(localContext);
      }
    }
    if (TextUtils.isEmpty(str)) {
      str = Build.MODEL;
    }
    return str;
  }
  
  int isAdAvailable(MMAdImpl paramMMAdImpl)
  {
    CachedAd localCachedAd = AdCache.loadNextCachedAd(paramMMAdImpl.getContext(), paramMMAdImpl.getCachedName());
    if (localCachedAd != null)
    {
      if (localCachedAd.canShow(paramMMAdImpl.getContext(), paramMMAdImpl, true)) {
        return 0;
      }
      return checkReason(paramMMAdImpl, localCachedAd);
    }
    MMLog.i("MMAdImplController", "No next ad.");
    return 20;
  }
  
  void linkForExpansion(MMAdImpl paramMMAdImpl)
  {
    MMAdImpl localMMAdImpl = getAdImplWithId(paramMMAdImpl.linkForExpansionId);
    if (localMMAdImpl != null)
    {
      this.linkedAdImplId = paramMMAdImpl.linkForExpansionId;
      localMMAdImpl.controller.linkedAdImplId = paramMMAdImpl.internalId;
      localMMAdImpl.linkForExpansionId = paramMMAdImpl.internalId;
    }
  }
  
  void loadUrl(String paramString)
  {
    if ((!TextUtils.isEmpty(paramString)) && (this.webView != null)) {
      this.webView.loadUrl(paramString);
    }
  }
  
  void loadWebContent(String paramString1, String paramString2)
  {
    MMAdImpl localMMAdImpl = (MMAdImpl)this.adImplRef.get();
    if ((localMMAdImpl != null) && (this.webView != null)) {
      this.webView.setWebViewContent(paramString1, paramString2, localMMAdImpl);
    }
  }
  
  void requestAd()
  {
    MMAdImpl localMMAdImpl = (MMAdImpl)this.adImplRef.get();
    if (localMMAdImpl == null)
    {
      MMLog.e("MMAdImplController", MMException.getErrorCodeMessage(25));
      MMSDK.Event.requestFailed(localMMAdImpl, new MMException(25));
      return;
    }
    if (!localMMAdImpl.isRefreshable())
    {
      MMSDK.Event.requestFailed(localMMAdImpl, new MMException(16));
      return;
    }
    if (!MMSDK.isUiThread())
    {
      MMLog.e("MMAdImplController", MMException.getErrorCodeMessage(3));
      MMSDK.Event.requestFailed(localMMAdImpl, new MMException(3));
      return;
    }
    if (HandShake.sharedHandShake(localMMAdImpl.getContext()).kill)
    {
      MMLog.i("MMAdImplController", "The server is no longer allowing ads.");
      MMSDK.Event.requestFailed(localMMAdImpl, new MMException(16));
      return;
    }
    try
    {
      MMLog.d("MMAdImplController", "adLayout - requestAd");
      requestAdInternal(localMMAdImpl);
      return;
    }
    catch (Exception localException)
    {
      MMLog.e("MMAdImplController", "There was an exception with the ad request. ", localException);
      localException.printStackTrace();
    }
  }
  
  void setLastHeaders(HttpMMHeaders paramHttpMMHeaders)
  {
    if (this.webView != null) {
      this.webView.setLastHeaders(paramHttpMMHeaders);
    }
  }
  
  void setWebViewContent(String paramString1, String paramString2)
  {
    if (this.webView != null) {
      this.webView.setWebViewContent(paramString1, paramString2, (MMAdImpl)this.adImplRef.get());
    }
  }
  
  public String toString()
  {
    MMAdImpl localMMAdImpl = (MMAdImpl)this.adImplRef.get();
    StringBuilder localStringBuilder = new StringBuilder();
    if (localMMAdImpl != null) {
      localStringBuilder.append(localMMAdImpl + "-LinkInC=" + this.linkedAdImplId);
    }
    return localStringBuilder.toString() + " w/" + this.webView;
  }
  
  void unresizeToDefault()
  {
    if (this.webView != null) {
      this.webView.unresizeToDefault((MMAdImpl)this.adImplRef.get());
    }
  }
  
  private class RequestAdRunnable
    implements Runnable
  {
    String adUrl;
    HttpMMHeaders mmHeaders;
    
    private RequestAdRunnable() {}
    
    private boolean isAdUrlBuildable()
    {
      this.adUrl = null;
      WeakReference localWeakReference = MMAdImplController.this.adImplRef;
      MMAdImpl localMMAdImpl = null;
      if (localWeakReference != null) {
        localMMAdImpl = (MMAdImpl)MMAdImplController.this.adImplRef.get();
      }
      if (localMMAdImpl != null)
      {
        StringBuilder localStringBuilder;
        try
        {
          TreeMap localTreeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
          localMMAdImpl.insertUrlAdMetaValues(localTreeMap);
          MMSDK.insertUrlCommonValues(localMMAdImpl.getContext(), localTreeMap);
          localTreeMap.put("ua", localMMAdImpl.controller.getUserAgent());
          localStringBuilder = new StringBuilder(HandShake.getAdUrl());
          MMLog.d("MMAdImplController", localTreeMap.entrySet().toString());
          Iterator localIterator = localTreeMap.entrySet().iterator();
          while (localIterator.hasNext())
          {
            Map.Entry localEntry = (Map.Entry)localIterator.next();
            Object[] arrayOfObject2 = new Object[2];
            arrayOfObject2[0] = localEntry.getKey();
            arrayOfObject2[1] = URLEncoder.encode((String)localEntry.getValue(), "UTF-8");
            localStringBuilder.append(String.format("%s=%s&", arrayOfObject2));
          }
          localStringBuilder.delete(-1 + localStringBuilder.length(), localStringBuilder.length());
        }
        catch (UnsupportedEncodingException localUnsupportedEncodingException)
        {
          return failWithErrorMessage(new MMException(localUnsupportedEncodingException));
        }
        this.adUrl = localStringBuilder.toString();
        Object[] arrayOfObject1 = new Object[1];
        arrayOfObject1[0] = this.adUrl;
        MMLog.d("MMAdImplController", String.format("Calling for an advertisement: %s", arrayOfObject1));
      }
      else
      {
        failWithInfoMessage(new MMException(25));
      }
      return true;
    }
    
    private boolean isHandledHtmlResponse(HttpEntity paramHttpEntity)
    {
      try
      {
        WeakReference localWeakReference = MMAdImplController.this.adImplRef;
        MMAdImpl localMMAdImpl = null;
        if (localWeakReference != null) {
          localMMAdImpl = (MMAdImpl)MMAdImplController.this.adImplRef.get();
        }
        if (localMMAdImpl != null) {
          if (!localMMAdImpl.isBanner())
          {
            InterstitialAd localInterstitialAd = new InterstitialAd();
            localInterstitialAd.content = HttpGetRequest.convertStreamToString(paramHttpEntity.getContent());
            localInterstitialAd.setId(localMMAdImpl.adType);
            localInterstitialAd.adUrl = this.adUrl;
            localInterstitialAd.mmHeaders = this.mmHeaders;
            if (MMSDK.logLevel >= 5)
            {
              Object[] arrayOfObject = new Object[1];
              arrayOfObject[0] = localInterstitialAd.adUrl;
              MMLog.v("MMAdImplController", String.format("Received interstitial ad with url %s.", arrayOfObject));
              MMLog.v("MMAdImplController", localInterstitialAd.content);
            }
            AdCache.save(localMMAdImpl.getContext(), localInterstitialAd);
            AdCache.setNextCachedAd(localMMAdImpl.getContext(), localMMAdImpl.getCachedName(), localInterstitialAd.getId());
            MMSDK.Event.fetchStartedCaching(localMMAdImpl);
            MMSDK.Event.requestCompleted(localMMAdImpl);
          }
          else
          {
            if (localMMAdImpl.controller != null)
            {
              localMMAdImpl.controller.setLastHeaders(this.mmHeaders);
              localMMAdImpl.controller.setWebViewContent(HttpGetRequest.convertStreamToString(paramHttpEntity.getContent()), this.adUrl);
            }
            MMSDK.Event.requestCompleted(localMMAdImpl);
          }
        }
      }
      catch (IOException localIOException)
      {
        return failWithErrorMessage(new MMException("Exception raised in HTTP stream: " + localIOException, localIOException));
      }
      return true;
    }
    
    private boolean isHandledJsonResponse(HttpEntity paramHttpEntity)
    {
      WeakReference localWeakReference = MMAdImplController.this.adImplRef;
      MMAdImpl localMMAdImpl = null;
      if (localWeakReference != null) {
        localMMAdImpl = (MMAdImpl)MMAdImplController.this.adImplRef.get();
      }
      if (localMMAdImpl != null) {
        if (localMMAdImpl.isBanner()) {
          return failWithErrorMessage(new MMException("Millennial ad return unsupported format.", 15));
        }
      }
      for (;;)
      {
        VideoAd localVideoAd;
        try
        {
          localVideoAd = (VideoAd)CachedAd.parseJSON(HttpGetRequest.convertStreamToString(paramHttpEntity.getContent()));
          if ((localVideoAd != null) && (localVideoAd.isValid()))
          {
            MMLog.i("MMAdImplController", "Cached video ad JSON received: " + localVideoAd.getId());
            if (localVideoAd.isExpired())
            {
              MMLog.i("MMAdImplController", "New ad has expiration date in the past. Not downloading ad content.");
              localVideoAd.delete(localMMAdImpl.getContext());
              MMSDK.Event.requestFailed(localMMAdImpl, new MMException(15));
            }
          }
          else
          {
            return true;
          }
        }
        catch (IllegalStateException localIllegalStateException)
        {
          localIllegalStateException.printStackTrace();
          return failWithInfoMessage(new MMException("Millennial ad return failed. Invalid response data.", localIllegalStateException));
        }
        catch (IOException localIOException)
        {
          localIOException.printStackTrace();
          return failWithInfoMessage(new MMException("Millennial ad return failed. " + localIOException, localIOException));
        }
        if (AdCache.loadNextCachedAd(localMMAdImpl.getContext(), localMMAdImpl.getCachedName()) != null)
        {
          MMLog.i("MMAdImplController", "Previously fetched ad exists in the playback queue. Not downloading ad content.");
          localVideoAd.delete(localMMAdImpl.getContext());
          MMSDK.Event.requestFailed(localMMAdImpl, new MMException(17));
        }
        else
        {
          AdCache.save(localMMAdImpl.getContext(), localVideoAd);
          if (!localVideoAd.isOnDisk(localMMAdImpl.getContext()))
          {
            MMSDK.Event.logEvent(localVideoAd.cacheMissURL);
            MMLog.d("MMAdImplController", "Downloading ad...");
            MMSDK.Event.fetchStartedCaching(localMMAdImpl);
            localVideoAd.downloadPriority = 3;
            AdCache.startDownloadTask(localMMAdImpl.getContext(), localMMAdImpl.getCachedName(), localVideoAd, localMMAdImpl.controller);
          }
          else
          {
            MMLog.d("MMAdImplController", "Cached ad is valid. Moving it to the front of the queue.");
            AdCache.setNextCachedAd(localMMAdImpl.getContext(), localMMAdImpl.getCachedName(), localVideoAd.getId());
            MMSDK.Event.fetchStartedCaching(localMMAdImpl);
            MMSDK.Event.requestCompleted(localMMAdImpl);
          }
        }
      }
    }
    
    private boolean isHandledResponse(HttpResponse paramHttpResponse)
    {
      HttpEntity localHttpEntity = paramHttpResponse.getEntity();
      if (localHttpEntity == null)
      {
        failWithInfoMessage(new MMException("Null HTTP entity", 14));
        return false;
      }
      if (localHttpEntity.getContentLength() == 0L)
      {
        failWithInfoMessage(new MMException("Millennial ad return failed. Zero content length returned.", 14));
        return false;
      }
      saveMacId(paramHttpResponse);
      Header localHeader1 = localHttpEntity.getContentType();
      if ((localHeader1 != null) && (localHeader1.getValue() != null))
      {
        if (localHeader1.getValue().toLowerCase().startsWith("application/json")) {
          isHandledJsonResponse(localHttpEntity);
        }
        for (;;)
        {
          return true;
          if (!localHeader1.getValue().toLowerCase().startsWith("text/html")) {
            break;
          }
          Header localHeader2 = paramHttpResponse.getFirstHeader("X-MM-Video");
          this.mmHeaders = new HttpMMHeaders(paramHttpResponse.getAllHeaders());
          if ((localHeader2 != null) && (localHeader2.getValue().equalsIgnoreCase("true")))
          {
            WeakReference localWeakReference = MMAdImplController.this.adImplRef;
            MMAdImpl localMMAdImpl = null;
            if (localWeakReference != null) {
              localMMAdImpl = (MMAdImpl)MMAdImplController.this.adImplRef.get();
            }
            if (localMMAdImpl != null)
            {
              Context localContext = localMMAdImpl.getContext();
              HandShake.sharedHandShake(localContext).updateLastVideoViewedTime(localContext, localMMAdImpl.adType);
            }
          }
          isHandledHtmlResponse(localHttpEntity);
        }
        failWithInfoMessage(new MMException("Millennial ad return failed. Invalid (JSON/HTML expected) mime type returned.", 15));
        return false;
      }
      failWithInfoMessage(new MMException("Millennial ad return failed. HTTP Header value null.", 15));
      return false;
    }
    
    private void saveMacId(HttpResponse paramHttpResponse)
    {
      Header[] arrayOfHeader = paramHttpResponse.getHeaders("Set-Cookie");
      int i = arrayOfHeader.length;
      for (int j = 0; j < i; j++)
      {
        String str = arrayOfHeader[j].getValue();
        int k = str.indexOf("MAC-ID=");
        if (k >= 0)
        {
          int m = str.indexOf(';', k);
          if (m > k) {
            MMSDK.macId = str.substring(k + 7, m);
          }
        }
      }
    }
    
    boolean fail(MMException paramMMException)
    {
      WeakReference localWeakReference = MMAdImplController.this.adImplRef;
      MMAdImpl localMMAdImpl = null;
      if (localWeakReference != null) {
        localMMAdImpl = (MMAdImpl)MMAdImplController.this.adImplRef.get();
      }
      MMSDK.Event.requestFailed(localMMAdImpl, paramMMException);
      return false;
    }
    
    boolean failWithErrorMessage(MMException paramMMException)
    {
      MMLog.e("MMAdImplController", paramMMException.getMessage());
      return fail(paramMMException);
    }
    
    boolean failWithInfoMessage(MMException paramMMException)
    {
      MMLog.i("MMAdImplController", paramMMException.getMessage());
      return fail(paramMMException);
    }
    
    public void run()
    {
      try
      {
        if (MMAdImplController.this.adImplRef != null)
        {
          MMAdImpl localMMAdImpl = (MMAdImpl)MMAdImplController.this.adImplRef.get();
          if ((localMMAdImpl != null) && (MMSDK.isConnected(localMMAdImpl.getContext())))
          {
            boolean bool1 = isAdUrlBuildable();
            if (!bool1) {
              return;
            }
            HttpResponse localHttpResponse;
            try
            {
              localHttpResponse = new HttpGetRequest().get(this.adUrl);
              if (localHttpResponse == null)
              {
                failWithErrorMessage(new MMException("HTTP response is null.", 14));
                return;
              }
            }
            catch (Exception localException2)
            {
              failWithErrorMessage(new MMException("Ad request HTTP error. " + localException2.getMessage(), 14));
              return;
            }
            boolean bool2 = isHandledResponse(localHttpResponse);
            if (bool2) {}
          }
          else
          {
            failWithInfoMessage(new MMException("No network available, can't call for ads.", 11));
            return;
          }
        }
        return;
      }
      catch (Exception localException1)
      {
        failWithInfoMessage(new MMException("Request not filled, can't call for ads.", 14));
        return;
      }
      finally
      {
        MMAdImplController.this.requestAdRunnable = null;
      }
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.MMAdImplController
 * JD-Core Version:    0.7.0.1
 */