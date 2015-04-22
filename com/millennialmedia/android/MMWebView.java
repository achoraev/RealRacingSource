package com.millennialmedia.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.webkit.GeolocationPermissions.Callback;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import org.json.JSONObject;

class MMWebView
  extends WebView
{
  static final String JS_INTERFACE_NAME = "interface";
  static final String PROPERTY_BANNER_TYPE = "PROPERTY_BANNER_TYPE";
  static final String PROPERTY_EXPANDING = "PROPERTY_EXPANDING";
  static final String PROPERTY_STATE = "PROPERTY_STATE";
  private static final String TAG = "MMWebView";
  private HttpMMHeaders _lastHeaders;
  long creatorAdImplId;
  int currentColor;
  String currentUrl;
  boolean hadFirstRecordingCreation = false;
  boolean hadFirstSpeechKitCreation = false;
  volatile boolean isExpanding;
  boolean isSendingSize = true;
  volatile boolean isUserClosedResize;
  volatile boolean isVisible = false;
  volatile String mraidState;
  int oldHeight = -50;
  int oldWidth = -50;
  volatile boolean requiresPreAdSizeFix;
  final GestureDetector tapDetector;
  final String userAgent;
  
  public MMWebView(Context paramContext, long paramLong)
  {
    super(paramContext);
    setWillNotDraw(false);
    setHorizontalScrollBarEnabled(false);
    setVerticalScrollBarEnabled(false);
    setOnTouchListener(new WebTouchListener(this));
    this.mraidState = "loading";
    this.creatorAdImplId = paramLong;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Long.valueOf(this.creatorAdImplId);
    MMLog.v("MMWebView", String.format("Assigning WebView internal id: %d", arrayOfObject));
    setId((int)(15063L + this.creatorAdImplId));
    if (HandShake.sharedHandShake(paramContext).hardwareAccelerationEnabled) {
      enableHardwareAcceleration();
    }
    for (;;)
    {
      setWebChromeClient(new MyWebChromeClient(this));
      WebSettings localWebSettings = getSettings();
      this.userAgent = localWebSettings.getUserAgentString();
      localWebSettings.setJavaScriptEnabled(true);
      localWebSettings.setCacheMode(-1);
      localWebSettings.setDefaultTextEncodingName("UTF-8");
      localWebSettings.setLoadWithOverviewMode(true);
      localWebSettings.setGeolocationEnabled(true);
      if (Build.VERSION.SDK_INT >= 17)
      {
        MMLog.i("MMWebView", "Disabling user gesture requirement for media playback");
        localWebSettings.setMediaPlaybackRequiresUserGesture(false);
      }
      localWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
      this.tapDetector = new GestureDetector(paramContext.getApplicationContext(), new BannerGestureListener(this));
      return;
      disableAllAcceleration();
    }
  }
  
  private boolean hasDefaultResizeParams()
  {
    return (this.oldWidth == -50) && (this.oldHeight == -50);
  }
  
  private boolean isInterstitial()
  {
    return getBanner() == null;
  }
  
  private boolean needsSamsungJBOpenGlFixNoAcceleration()
  {
    int i = Integer.parseInt(Build.VERSION.SDK);
    return ("Nexus S".equals(Build.MODEL)) && ("samsung".equals(Build.MANUFACTURER)) && ((i == 16) || (i == 17));
  }
  
  boolean allowMicrophoneCreationCommands()
  {
    boolean bool = true;
    if (this.hadFirstRecordingCreation) {
      bool = allowRecordingCommands();
    }
    do
    {
      return bool;
      this.hadFirstRecordingCreation = bool;
    } while ((isInterstitial()) && (this.isVisible));
    return false;
  }
  
  boolean allowRecordingCommands()
  {
    return (hasWindowFocus()) && (isInterstitial());
  }
  
  boolean allowSpeechCreationCommands()
  {
    boolean bool = true;
    if (this.hadFirstSpeechKitCreation) {
      bool = allowRecordingCommands();
    }
    do
    {
      return bool;
      this.hadFirstSpeechKitCreation = bool;
    } while ((isInterstitial()) && (this.isVisible));
    return false;
  }
  
  void animateTransition(final MMAdImpl paramMMAdImpl)
  {
    FutureTask localFutureTask = new FutureTask(new Callable()
    {
      public Void call()
      {
        try
        {
          MMWebView.this.buildDrawingCache();
          Bitmap localBitmap1 = MMWebView.this.getDrawingCache();
          if (localBitmap1 != null)
          {
            Bitmap localBitmap2 = Bitmap.createBitmap(localBitmap1);
            paramMMAdImpl.prepareTransition(localBitmap2);
          }
          MMWebView.this.destroyDrawingCache();
        }
        catch (Exception localException)
        {
          for (;;)
          {
            MMLog.e("MMWebView", "Animation exception: ", localException);
          }
        }
        return null;
      }
    });
    MMSDK.runOnUiThread(localFutureTask);
    try
    {
      localFutureTask.get();
      return;
    }
    catch (InterruptedException localInterruptedException) {}catch (ExecutionException localExecutionException) {}
  }
  
  boolean canScroll()
  {
    return getParent() instanceof MMAdView;
  }
  
  void disableAllAcceleration()
  {
    if (Build.VERSION.SDK_INT >= 11)
    {
      MMLog.i("MMWebView", "Disabling acceleration");
      setLayerType(0, null);
    }
  }
  
  void enableHardwareAcceleration()
  {
    if (!needsSamsungJBOpenGlFixNoAcceleration()) {}
    try
    {
      Class[] arrayOfClass = new Class[2];
      arrayOfClass[0] = Integer.TYPE;
      arrayOfClass[1] = Paint.class;
      Method localMethod = WebView.class.getMethod("setLayerType", arrayOfClass);
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = Integer.valueOf(2);
      arrayOfObject[1] = null;
      localMethod.invoke(this, arrayOfObject);
      MMLog.d("MMWebView", "Enabled hardwareAcceleration");
      return;
    }
    catch (Exception localException) {}
  }
  
  void enableSendingSize()
  {
    this.isSendingSize = true;
  }
  
  void enableSoftwareAcceleration()
  {
    if (!needsSamsungJBOpenGlFixNoAcceleration()) {}
    try
    {
      Class[] arrayOfClass = new Class[2];
      arrayOfClass[0] = Integer.TYPE;
      arrayOfClass[1] = Paint.class;
      Method localMethod = WebView.class.getMethod("setLayerType", arrayOfClass);
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = Integer.valueOf(1);
      arrayOfObject[1] = null;
      localMethod.invoke(this, arrayOfObject);
      MMLog.d("MMWebView", "Enable softwareAcceleration");
      return;
    }
    catch (Exception localException) {}
  }
  
  /* Error */
  Activity getActivity()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokevirtual 271	com/millennialmedia/android/MMWebView:getParent	()Landroid/view/ViewParent;
    //   6: astore_2
    //   7: aload_2
    //   8: ifnull +42 -> 50
    //   11: aload_2
    //   12: instanceof 318
    //   15: ifeq +35 -> 50
    //   18: aload_2
    //   19: checkcast 318	android/view/ViewGroup
    //   22: invokevirtual 321	android/view/ViewGroup:getContext	()Landroid/content/Context;
    //   25: astore 4
    //   27: aload 4
    //   29: ifnull +21 -> 50
    //   32: aload 4
    //   34: instanceof 323
    //   37: ifeq +13 -> 50
    //   40: aload 4
    //   42: checkcast 323	com/millennialmedia/android/MMActivity
    //   45: astore_3
    //   46: aload_0
    //   47: monitorexit
    //   48: aload_3
    //   49: areturn
    //   50: aconst_null
    //   51: astore_3
    //   52: goto -6 -> 46
    //   55: astore_1
    //   56: aload_0
    //   57: monitorexit
    //   58: aload_1
    //   59: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	60	0	this	MMWebView
    //   55	4	1	localObject	Object
    //   6	13	2	localViewParent	ViewParent
    //   45	7	3	localMMActivity	MMActivity
    //   25	16	4	localContext	Context
    // Exception table:
    //   from	to	target	type
    //   2	7	55	finally
    //   11	27	55	finally
    //   32	46	55	finally
  }
  
  String getAdId()
  {
    if ((this._lastHeaders != null) && (!TextUtils.isEmpty(this._lastHeaders.acid))) {
      return this._lastHeaders.acid;
    }
    return "DEFAULT_AD_ID";
  }
  
  /* Error */
  AdViewOverlayView getAdViewOverlayView()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokevirtual 271	com/millennialmedia/android/MMWebView:getParent	()Landroid/view/ViewParent;
    //   6: astore_2
    //   7: aload_2
    //   8: ifnull +19 -> 27
    //   11: aload_2
    //   12: instanceof 343
    //   15: ifeq +12 -> 27
    //   18: aload_2
    //   19: checkcast 343	com/millennialmedia/android/AdViewOverlayView
    //   22: astore_3
    //   23: aload_0
    //   24: monitorexit
    //   25: aload_3
    //   26: areturn
    //   27: aconst_null
    //   28: astore_3
    //   29: goto -6 -> 23
    //   32: astore_1
    //   33: aload_0
    //   34: monitorexit
    //   35: aload_1
    //   36: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	37	0	this	MMWebView
    //   32	4	1	localObject	Object
    //   6	13	2	localViewParent	ViewParent
    //   22	7	3	localAdViewOverlayView	AdViewOverlayView
    // Exception table:
    //   from	to	target	type
    //   2	7	32	finally
    //   11	23	32	finally
  }
  
  /* Error */
  MMAdView getBanner()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokevirtual 271	com/millennialmedia/android/MMWebView:getParent	()Landroid/view/ViewParent;
    //   6: astore_2
    //   7: aload_2
    //   8: ifnull +19 -> 27
    //   11: aload_2
    //   12: instanceof 273
    //   15: ifeq +12 -> 27
    //   18: aload_2
    //   19: checkcast 273	com/millennialmedia/android/MMAdView
    //   22: astore_3
    //   23: aload_0
    //   24: monitorexit
    //   25: aload_3
    //   26: areturn
    //   27: aconst_null
    //   28: astore_3
    //   29: goto -6 -> 23
    //   32: astore_1
    //   33: aload_0
    //   34: monitorexit
    //   35: aload_1
    //   36: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	37	0	this	MMWebView
    //   32	4	1	localObject	Object
    //   6	13	2	localViewParent	ViewParent
    //   22	7	3	localMMAdView	MMAdView
    // Exception table:
    //   from	to	target	type
    //   2	7	32	finally
    //   11	23	32	finally
  }
  
  HttpMMHeaders getLastHeaders()
  {
    return this._lastHeaders;
  }
  
  MMAdView getMMAdView()
  {
    if ((getParent() instanceof MMAdView)) {
      return (MMAdView)getParent();
    }
    return null;
  }
  
  MMLayout getMMLayout()
  {
    if ((getParent() instanceof MMLayout)) {
      return (MMLayout)getParent();
    }
    return null;
  }
  
  String getUserAgent()
  {
    return this.userAgent;
  }
  
  boolean isCurrentParent(long paramLong)
  {
    ViewParent localViewParent = getParent();
    if (localViewParent == null) {
      return false;
    }
    MMLog.w("MMWebView", "Id check for parent: " + paramLong + " versus " + ((MMLayout)localViewParent).adImpl.internalId);
    if (paramLong == ((MMLayout)localViewParent).adImpl.internalId) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  boolean isMraidResized()
  {
    return "resized".equals(this.mraidState);
  }
  
  boolean isOriginalUrl(String paramString)
  {
    return ((!TextUtils.isEmpty(this.currentUrl)) && (paramString.equals(this.currentUrl + "?"))) || (paramString.equals(this.currentUrl + "#"));
  }
  
  boolean isParentBannerAd()
  {
    if (getParent() != null) {
      return (ViewGroup)getParent() instanceof MMAdView;
    }
    return false;
  }
  
  public void loadDataWithBaseURL(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    this.currentUrl = paramString1;
    try
    {
      super.loadDataWithBaseURL(paramString1, paramString2, paramString3, paramString4, paramString5);
      return;
    }
    catch (Exception localException)
    {
      MMLog.e("MMWebView", "Error hit when calling through to loadDataWithBaseUrl", localException);
    }
  }
  
  public void loadUrl(final String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return;
    }
    if (paramString.startsWith("http")) {
      this.currentUrl = paramString;
    }
    MMLog.v("MMWebView", "loadUrl @@" + paramString);
    if (MMSDK.isUiThread()) {
      try
      {
        super.loadUrl(paramString);
        return;
      }
      catch (Exception localException)
      {
        return;
      }
    }
    MMSDK.runOnUiThread(new Runnable()
    {
      public void run()
      {
        MMWebView.this.loadUrl(paramString);
      }
    });
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    int i = View.MeasureSpec.getSize(paramInt2);
    int j = View.MeasureSpec.getSize(paramInt1);
    int k = getMeasuredHeight();
    if (k == 0) {
      k = i;
    }
    if (this.requiresPreAdSizeFix)
    {
      setMeasuredDimension(1, 1);
      return;
    }
    setMeasuredDimension(j, k);
  }
  
  public void onPauseWebView()
  {
    if (Build.VERSION.SDK_INT >= 11) {}
    try
    {
      WebView.class.getMethod("onPause", new Class[0]).invoke(this, new Object[0]);
      return;
    }
    catch (Exception localException)
    {
      MMLog.w("MMWebView", "No onPause()");
    }
  }
  
  public void onResumeWebView()
  {
    if ((!isParentBannerAd()) && (Build.VERSION.SDK_INT >= 19))
    {
      Activity localActivity = getActivity();
      if (localActivity != null) {
        localActivity.setRequestedOrientation(14);
      }
    }
    if (Build.VERSION.SDK_INT >= 11) {}
    try
    {
      WebView.class.getMethod("onResume", new Class[0]).invoke(this, new Object[0]);
      return;
    }
    catch (Exception localException)
    {
      MMLog.w("MMWebView", "No onResume()");
    }
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if ((getContext().getResources().getDisplayMetrics() != null) && (this.isSendingSize))
    {
      setAdSize();
      if ((getHeight() != 1) || (getWidth() != 1)) {
        MMSDK.runOnUiThreadDelayed(new Runnable()
        {
          public void run()
          {
            MMWebView.this.isSendingSize = false;
          }
        }, 800L);
      }
    }
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (paramMotionEvent.getAction() == 0) {
      requestFocus();
    }
    if (this.tapDetector != null) {
      this.tapDetector.onTouchEvent(paramMotionEvent);
    }
    if (paramMotionEvent.getAction() == 1)
    {
      Object[] arrayOfObject = new Object[3];
      arrayOfObject[0] = Integer.valueOf(paramMotionEvent.getAction());
      arrayOfObject[1] = Float.valueOf(paramMotionEvent.getX());
      arrayOfObject[2] = Float.valueOf(paramMotionEvent.getY());
      MMLog.v("MMWebView", String.format("Ad clicked: action=%d x=%f y=%f", arrayOfObject));
    }
    return super.onTouchEvent(paramMotionEvent);
  }
  
  void removeFromParent()
  {
    ViewParent localViewParent = getParent();
    if ((localViewParent != null) && ((localViewParent instanceof ViewGroup))) {
      ((ViewGroup)localViewParent).removeView(this);
    }
  }
  
  void resetSpeechKit()
  {
    BridgeMMSpeechkit.releaseSpeechKit();
    this.hadFirstSpeechKitCreation = false;
    this.hadFirstRecordingCreation = false;
  }
  
  void setAdProperties()
  {
    JSONObject localJSONObject = new AdProperties(getContext()).getAdProperties(this);
    loadUrl("javascript:MMJS.sdk.setAdProperties(" + localJSONObject + ");");
  }
  
  void setAdSize()
  {
    JSONObject localJSONObject = Utils.getViewDimensions(this);
    loadUrl("javascript:MMJS.sdk.setAdSize(" + localJSONObject + ");");
  }
  
  public void setBackgroundColor(int paramInt)
  {
    this.currentColor = paramInt;
    if (paramInt == 0) {
      enableSoftwareAcceleration();
    }
    super.setBackgroundColor(paramInt);
  }
  
  void setLastHeaders(HttpMMHeaders paramHttpMMHeaders)
  {
    this._lastHeaders = paramHttpMMHeaders;
  }
  
  void setMraidDefault()
  {
    loadUrl("javascript:MMJS.sdk.setState('default')");
    this.mraidState = "default";
    this.isSendingSize = true;
  }
  
  void setMraidExpanded()
  {
    loadUrl("javascript:MMJS.sdk.setState('expanded');");
    this.mraidState = "expanded";
    this.hadFirstSpeechKitCreation = false;
    this.hadFirstRecordingCreation = false;
    this.isSendingSize = true;
  }
  
  void setMraidHidden()
  {
    loadUrl("javascript:MMJS.sdk.setState('hidden')");
    this.mraidState = "hidden";
  }
  
  void setMraidPlacementTypeInline()
  {
    loadUrl("javascript:MMJS.sdk.setPlacementType('inline');");
  }
  
  void setMraidPlacementTypeInterstitial()
  {
    loadUrl("javascript:MMJS.sdk.setPlacementType('interstitial');");
  }
  
  void setMraidReady()
  {
    loadUrl("javascript:MMJS.sdk.ready();");
  }
  
  void setMraidResize(final DTOResizeParameters paramDTOResizeParameters)
  {
    try
    {
      if (MMSDK.hasSetTranslationMethod())
      {
        final MMAdView localMMAdView = getMMAdView();
        this.isUserClosedResize = false;
        MMLog.d("MMWebView", "New DTOResizeParameters = " + paramDTOResizeParameters);
        if (localMMAdView != null) {
          MMSDK.runOnUiThread(new Runnable()
          {
            private void handleMraidResize(DTOResizeParameters paramAnonymousDTOResizeParameters)
            {
              MMAdView localMMAdView = localMMAdView;
              localMMAdView.getClass();
              MMAdView.BannerBounds localBannerBounds = new MMAdView.BannerBounds(localMMAdView, paramAnonymousDTOResizeParameters);
              setUnresizeParameters();
              localBannerBounds.modifyLayoutParams(MMWebView.this.getLayoutParams());
            }
            
            private void setUnresizeParameters()
            {
              if (MMWebView.this.hasDefaultResizeParams())
              {
                ViewGroup.LayoutParams localLayoutParams = MMWebView.this.getLayoutParams();
                MMWebView.this.oldWidth = localLayoutParams.width;
                MMWebView.this.oldHeight = localLayoutParams.height;
                if (MMWebView.this.oldWidth <= 0) {
                  MMWebView.this.oldWidth = MMWebView.this.getWidth();
                }
                if (MMWebView.this.oldHeight <= 0) {
                  MMWebView.this.oldHeight = MMWebView.this.getHeight();
                }
              }
            }
            
            public void run()
            {
              synchronized (MMWebView.this)
              {
                MMWebView.this.isSendingSize = true;
                localMMAdView.handleMraidResize(paramDTOResizeParameters);
                handleMraidResize(paramDTOResizeParameters);
                MMWebView.this.loadUrl("javascript:MMJS.sdk.setState('resized');");
                MMWebView.this.mraidState = "resized";
                return;
              }
            }
          });
        }
      }
      return;
    }
    finally {}
  }
  
  void setMraidViewableHidden()
  {
    loadUrl("javascript:MMJS.sdk.setViewable(false)");
    this.isVisible = false;
  }
  
  void setMraidViewableVisible()
  {
    loadUrl("javascript:MMJS.sdk.setViewable(true)");
    this.isVisible = true;
  }
  
  void setWebViewContent(String paramString1, String paramString2, Context paramContext)
  {
    if ((paramString1 == null) || (paramString2 == null)) {
      return;
    }
    final String str1 = paramString2.substring(0, 1 + paramString2.lastIndexOf("/"));
    resetSpeechKit();
    String str2 = paramString1;
    if (MRaid.hasMraidLocally(paramContext)) {
      str2 = MRaid.injectMraidJs(paramContext, str2);
    }
    for (;;)
    {
      final String str3 = str2;
      if (MMSDK.logLevel >= 5)
      {
        MMLog.v("MMWebView", String.format("Received ad with base url %s.", new Object[] { paramString2 }));
        MMLog.v("MMWebView", paramString1);
      }
      MMSDK.runOnUiThread(new Runnable()
      {
        public void run()
        {
          if (HandShake.sharedHandShake(MMWebView.this.getContext()).hardwareAccelerationEnabled) {
            MMWebView.this.enableHardwareAcceleration();
          }
          for (;;)
          {
            MMWebView.this.isSendingSize = true;
            MMWebView.this.loadDataWithBaseURL(str1, str3, "text/html", "UTF-8", null);
            return;
            if (MMWebView.this.currentColor == 0) {
              MMWebView.this.enableSoftwareAcceleration();
            } else {
              MMWebView.this.disableAllAcceleration();
            }
          }
        }
      });
      return;
      MMLog.e("MMWebView", "MMJS is not downloaded");
    }
  }
  
  void setWebViewContent(String paramString1, String paramString2, final MMAdImpl paramMMAdImpl)
  {
    if ((paramString1 == null) || (paramString2 == null) || (paramMMAdImpl == null)) {
      return;
    }
    unresizeToDefault(paramMMAdImpl);
    resetSpeechKit();
    final String str1 = paramString2.substring(0, 1 + paramString2.lastIndexOf("/"));
    if (MMSDK.logLevel >= 5)
    {
      MMLog.v("MMWebView", String.format("Received ad with base url %s.", new Object[] { str1 }));
      MMLog.v("MMWebView", paramString1);
    }
    if (paramMMAdImpl.isTransitionAnimated()) {
      animateTransition(paramMMAdImpl);
    }
    final String str2;
    if (paramMMAdImpl.ignoreDensityScaling)
    {
      str2 = "<head><meta name=\"viewport\" content=\"target-densitydpi=device-dpi\" /></head>" + paramString1;
      if (!MRaid.hasMraidLocally(paramMMAdImpl.getContext())) {
        break label156;
      }
      str2 = MRaid.injectMraidJs(paramMMAdImpl.getContext(), str2);
    }
    for (;;)
    {
      MMSDK.runOnUiThread(new Runnable()
      {
        public void run()
        {
          if (HandShake.sharedHandShake(MMWebView.this.getContext()).hardwareAccelerationEnabled) {
            MMWebView.this.enableHardwareAcceleration();
          }
          for (;;)
          {
            MMAd localMMAd = paramMMAdImpl.getCallingAd();
            if ((localMMAd != null) && ((localMMAd instanceof MMLayout))) {
              ((MMLayout)localMMAd).removeVideo();
            }
            MMWebView.this.isSendingSize = true;
            MMWebView.this.loadDataWithBaseURL(str1, str2, "text/html", "UTF-8", null);
            return;
            if (MMWebView.this.currentColor == 0) {
              MMWebView.this.enableSoftwareAcceleration();
            } else {
              MMWebView.this.disableAllAcceleration();
            }
          }
        }
      });
      return;
      str2 = paramString1;
      break;
      label156:
      MMLog.e("MMWebView", "MMJS is not downloaded");
    }
  }
  
  void setmicrophoneAudioLevelChange(double paramDouble)
  {
    loadUrl("javascript:MMJS.sdk.microphoneAudioLevelChange(" + paramDouble + ")");
  }
  
  void setmicrophoneStateChange(String paramString)
  {
    loadUrl("javascript:MMJS.sdk.microphoneStateChange('" + paramString + "')");
  }
  
  public String toString()
  {
    return "MMWebView originally from(" + this.creatorAdImplId + ") MRaidState(" + this.mraidState + ")." + super.toString();
  }
  
  void unresizeToDefault(MMAdImpl paramMMAdImpl)
  {
    try
    {
      if ((MMSDK.hasSetTranslationMethod()) && (isMraidResized()) && (paramMMAdImpl != null))
      {
        MMAd localMMAd = paramMMAdImpl.getCallingAd();
        if ((localMMAd instanceof MMAdView))
        {
          final MMAdView localMMAdView = (MMAdView)localMMAd;
          this.isUserClosedResize = true;
          MMSDK.runOnUiThread(new Runnable()
          {
            void handleUnresize()
            {
              if ((MMSDK.hasSetTranslationMethod()) && (!MMWebView.this.hasDefaultResizeParams()))
              {
                ViewGroup.LayoutParams localLayoutParams = MMWebView.this.getLayoutParams();
                localLayoutParams.width = MMWebView.this.oldWidth;
                localLayoutParams.height = MMWebView.this.oldHeight;
                MMWebView.this.oldWidth = -50;
                MMWebView.this.oldHeight = -50;
                MMWebView.this.requestLayout();
              }
            }
            
            public void run()
            {
              synchronized (MMWebView.this)
              {
                localMMAdView.handleUnresize();
                handleUnresize();
                MMWebView.this.setMraidDefault();
                MMWebView.this.isSendingSize = true;
                MMWebView.this.invalidate();
                return;
              }
            }
          });
        }
      }
      return;
    }
    finally {}
  }
  
  void updateArgumentsWithSettings(Map<String, String> paramMap)
  {
    if (isParentBannerAd()) {}
    for (String str = "true";; str = "false")
    {
      paramMap.put("PROPERTY_BANNER_TYPE", str);
      paramMap.put("PROPERTY_STATE", this.mraidState);
      paramMap.put("PROPERTY_EXPANDING", String.valueOf(this.creatorAdImplId));
      return;
    }
  }
  
  private static class BannerGestureListener
    extends GestureDetector.SimpleOnGestureListener
  {
    WeakReference<MMWebView> webRef;
    
    public BannerGestureListener(MMWebView paramMMWebView)
    {
      this.webRef = new WeakReference(paramMMWebView);
    }
    
    public boolean onSingleTapConfirmed(MotionEvent paramMotionEvent)
    {
      MMWebView localMMWebView = (MMWebView)this.webRef.get();
      if (localMMWebView != null)
      {
        MMAdView localMMAdView = localMMWebView.getMMAdView();
        if (localMMAdView != null) {
          MMSDK.Event.adSingleTap(localMMAdView.adImpl);
        }
      }
      return false;
    }
  }
  
  private static class MyWebChromeClient
    extends WebChromeClient
  {
    private static final String KEY_USE_GEO = "mm_use_geo_location";
    WeakReference<MMWebView> webRef;
    
    MyWebChromeClient(MMWebView paramMMWebView)
    {
      this.webRef = new WeakReference(paramMMWebView);
    }
    
    private String getApplicationName(Context paramContext)
    {
      PackageManager localPackageManager = paramContext.getApplicationContext().getPackageManager();
      try
      {
        ApplicationInfo localApplicationInfo2 = localPackageManager.getApplicationInfo(paramContext.getPackageName(), 0);
        localApplicationInfo1 = localApplicationInfo2;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        for (;;)
        {
          ApplicationInfo localApplicationInfo1 = null;
          continue;
          Object localObject = "This app";
        }
      }
      if (localApplicationInfo1 != null)
      {
        localObject = localPackageManager.getApplicationLabel(localApplicationInfo1);
        return (String)localObject;
      }
    }
    
    private boolean isFirstGeoRequest()
    {
      MMWebView localMMWebView = (MMWebView)this.webRef.get();
      boolean bool1 = false;
      if (localMMWebView != null)
      {
        boolean bool2 = localMMWebView.getContext().getSharedPreferences("MillennialMediaSettings", 0).contains("mm_use_geo_location");
        bool1 = false;
        if (!bool2) {
          bool1 = true;
        }
      }
      return bool1;
    }
    
    private boolean retrieveUseGeo()
    {
      MMWebView localMMWebView = (MMWebView)this.webRef.get();
      boolean bool = false;
      if (localMMWebView != null) {
        bool = localMMWebView.getContext().getSharedPreferences("MillennialMediaSettings", 0).getBoolean("mm_use_geo_location", false);
      }
      return bool;
    }
    
    private void saveUseGeo(boolean paramBoolean)
    {
      MMWebView localMMWebView = (MMWebView)this.webRef.get();
      if (localMMWebView != null)
      {
        SharedPreferences.Editor localEditor = localMMWebView.getContext().getSharedPreferences("MillennialMediaSettings", 0).edit();
        localEditor.putBoolean("mm_use_geo_location", paramBoolean);
        localEditor.commit();
      }
    }
    
    public void onConsoleMessage(String paramString1, int paramInt, String paramString2)
    {
      super.onConsoleMessage(paramString1, paramInt, paramString2);
    }
    
    public void onGeolocationPermissionsShowPrompt(final String paramString, final GeolocationPermissions.Callback paramCallback)
    {
      if (isFirstGeoRequest())
      {
        if (retrieveUseGeo()) {
          paramCallback.invoke(paramString, true, true);
        }
        Activity localActivity;
        do
        {
          MMWebView localMMWebView;
          do
          {
            return;
            localMMWebView = (MMWebView)this.webRef.get();
          } while (localMMWebView == null);
          localActivity = localMMWebView.getActivity();
        } while (localActivity == null);
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(localActivity);
        localBuilder.setTitle(getApplicationName(localActivity));
        localBuilder.setMessage("Would like to use your Current Location.").setPositiveButton("Allow", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            MMWebView.MyWebChromeClient.this.saveUseGeo(true);
            paramCallback.invoke(paramString, true, true);
          }
        }).setNegativeButton("Don't Allow", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            MMWebView.MyWebChromeClient.this.saveUseGeo(false);
            paramCallback.invoke(paramString, false, false);
          }
        });
        localBuilder.create().show();
        return;
      }
      paramCallback.invoke(paramString, false, false);
    }
    
    public boolean onJsAlert(WebView paramWebView, String paramString1, String paramString2, JsResult paramJsResult)
    {
      MMWebView localMMWebView = (MMWebView)this.webRef.get();
      if (localMMWebView != null)
      {
        if (localMMWebView.getContext() != localMMWebView.getContext().getApplicationContext()) {
          return super.onJsAlert(paramWebView, paramString1, paramString2, paramJsResult);
        }
        Toast.makeText(localMMWebView.getContext(), paramString2, 0).show();
      }
      return true;
    }
    
    public boolean onJsBeforeUnload(WebView paramWebView, String paramString1, String paramString2, JsResult paramJsResult)
    {
      MMWebView localMMWebView = (MMWebView)this.webRef.get();
      if (localMMWebView != null)
      {
        if (localMMWebView.getContext() != localMMWebView.getContext().getApplicationContext()) {
          return super.onJsBeforeUnload(paramWebView, paramString1, paramString2, paramJsResult);
        }
        Toast.makeText(localMMWebView.getContext(), paramString2, 0).show();
      }
      return true;
    }
    
    public boolean onJsConfirm(WebView paramWebView, String paramString1, String paramString2, JsResult paramJsResult)
    {
      MMWebView localMMWebView = (MMWebView)this.webRef.get();
      if (localMMWebView != null)
      {
        if (localMMWebView.getContext() != localMMWebView.getContext().getApplicationContext()) {
          return super.onJsConfirm(paramWebView, paramString1, paramString2, paramJsResult);
        }
        Toast.makeText(localMMWebView.getContext(), paramString2, 0).show();
      }
      return true;
    }
    
    public boolean onJsPrompt(WebView paramWebView, String paramString1, String paramString2, String paramString3, JsPromptResult paramJsPromptResult)
    {
      MMWebView localMMWebView = (MMWebView)this.webRef.get();
      if (localMMWebView != null)
      {
        if (localMMWebView.getContext() != localMMWebView.getContext().getApplicationContext()) {
          return super.onJsPrompt(paramWebView, paramString1, paramString2, paramString3, paramJsPromptResult);
        }
        Toast.makeText(localMMWebView.getContext(), paramString2, 0).show();
      }
      return true;
    }
  }
  
  private static class WebTouchListener
    implements View.OnTouchListener
  {
    WeakReference<MMWebView> webRef;
    
    WebTouchListener(MMWebView paramMMWebView)
    {
      this.webRef = new WeakReference(paramMMWebView);
    }
    
    public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
    {
      MMWebView localMMWebView = (MMWebView)this.webRef.get();
      if (paramMotionEvent.getAction() == 2) {}
      for (boolean bool = true;; bool = false)
      {
        if (localMMWebView != null)
        {
          if ((!bool) || (!localMMWebView.canScroll())) {
            break;
          }
          bool = true;
        }
        return bool;
      }
      return false;
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.MMWebView
 * JD-Core Version:    0.7.0.1
 */