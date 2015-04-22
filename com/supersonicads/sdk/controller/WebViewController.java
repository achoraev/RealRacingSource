package com.supersonicads.sdk.controller;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.MutableContextWrapper;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.CountDownTimer;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.webkit.ConsoleMessage;
import android.webkit.DownloadListener;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebView.WebViewTransport;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;
import com.supersonicads.sdk.data.AdUnitsReady;
import com.supersonicads.sdk.data.SSABCParameters;
import com.supersonicads.sdk.data.SSAEnums.ControllerState;
import com.supersonicads.sdk.data.SSAEnums.DebugMode;
import com.supersonicads.sdk.data.SSAEnums.ProductType;
import com.supersonicads.sdk.data.SSAFile;
import com.supersonicads.sdk.data.SSAObj;
import com.supersonicads.sdk.listeners.OnGenericFunctionListener;
import com.supersonicads.sdk.listeners.OnInterstitialListener;
import com.supersonicads.sdk.listeners.OnOfferWallListener;
import com.supersonicads.sdk.listeners.OnRewardedVideoListener;
import com.supersonicads.sdk.precache.CacheManager;
import com.supersonicads.sdk.precache.CacheManager.OnAppsInstall;
import com.supersonicads.sdk.precache.DownloadManager;
import com.supersonicads.sdk.precache.DownloadManager.OnPreCacheCompletion;
import com.supersonicads.sdk.utils.DeviceProperties;
import com.supersonicads.sdk.utils.InAppPurchaseHelper;
import com.supersonicads.sdk.utils.InAppPurchaseHelper.OnPurchasedItemsListener;
import com.supersonicads.sdk.utils.LocationHelper;
import com.supersonicads.sdk.utils.LocationHelper.OnGetLocationListener;
import com.supersonicads.sdk.utils.Logger;
import com.supersonicads.sdk.utils.SDKUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WebViewController
  extends WebView
  implements DownloadManager.OnPreCacheCompletion, DownloadListener
{
  static final FrameLayout.LayoutParams COVER_SCREEN_PARAMS;
  public static int DISPLAY_WEB_VIEW_INTENT;
  public static String EXTERNAL_URL;
  public static String IS_STORE;
  public static String IS_STORE_CLOSE;
  private static String JSON_KEY_FAIL = "fail";
  private static String JSON_KEY_SUCCESS;
  public static int OPEN_URL_INTENT;
  public static String SECONDARY_WEB_VIEW;
  public static String WEBVIEW_TYPE;
  public static int mDebugMode;
  public static String mDownloadDomain;
  public static String mLoadDomain = "http://s.ssacdn.com/";
  private String TAG = WebViewController.class.getSimpleName();
  private CacheManager cacheManager;
  private DownloadManager downloadManager;
  private InAppPurchaseHelper inAppPurchaseHelper;
  private Boolean isKitkatAndAbove = null;
  private boolean isRemoveCloseEventHandler;
  private LocationHelper locationHelper;
  private Activity mActivity;
  private String mBCAppKey;
  private String mBCUserId;
  private FrameLayout mBrowserFrameLayout;
  private OnWebViewControllerChangeListener mChangeListener;
  private CountDownTimer mCloseEventTimer;
  private BroadcastReceiver mConnectionReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      ConnectivityManager localConnectivityManager = (ConnectivityManager)paramAnonymousContext.getSystemService("connectivity");
      NetworkInfo localNetworkInfo1 = localConnectivityManager.getNetworkInfo(1);
      boolean bool1 = false;
      if (localNetworkInfo1 != null) {
        bool1 = localNetworkInfo1.isConnected();
      }
      NetworkInfo localNetworkInfo2 = localConnectivityManager.getNetworkInfo(0);
      boolean bool2 = false;
      if (localNetworkInfo2 != null) {
        bool2 = localNetworkInfo2.isConnected();
      }
      if (WebViewController.this.mControllerState == SSAEnums.ControllerState.Ready) {
        WebViewController.this.deviceStatusChanged(bool1, bool2);
      }
    }
  };
  private FrameLayout mContentView;
  private String mControllerKeyPressed = "interrupt";
  private SSAEnums.ControllerState mControllerState = SSAEnums.ControllerState.None;
  private View mCustomView;
  private WebChromeClient.CustomViewCallback mCustomViewCallback;
  private FrameLayout mCustomViewContainer;
  private Map<String, String> mExtraParameters;
  private boolean mGlobalControllerTimeFinish;
  private CountDownTimer mGlobalControllerTimer;
  private int mHiddenForceCloseHeight = 50;
  private String mHiddenForceCloseLocation = "top-right";
  private int mHiddenForceCloseWidth = 50;
  private String mISAppKey;
  private String mISUserId;
  private boolean mISmiss;
  private FrameLayout mLayout;
  private CountDownTimer mLoadControllerTimer;
  private String mOWAppKey;
  private String mOWCreditsAppKey;
  private boolean mOWCreditsMiss;
  private String mOWCreditsUserId;
  private String mOWUserId;
  private boolean mOWmiss;
  private OnGenericFunctionListener mOnGenericFunctionListener;
  private OnInterstitialListener mOnInitInterstitialListener;
  private OnOfferWallListener mOnOfferWallListener;
  private OnRewardedVideoListener mOnRewardedVideoListener;
  private String mOrientationState;
  private boolean mRVmiss;
  private String mRequestParameters;
  private State mState;
  private Uri mUri;
  private ChromeClient mWebChromeClient;
  
  static
  {
    mDownloadDomain = "http://s.ssacdn.com/";
    mDebugMode = 0;
    IS_STORE = "is_store";
    IS_STORE_CLOSE = "is_store_close";
    WEBVIEW_TYPE = "webview_type";
    EXTERNAL_URL = "external_url";
    SECONDARY_WEB_VIEW = "secondary_web_view";
    DISPLAY_WEB_VIEW_INTENT = 0;
    OPEN_URL_INTENT = 1;
    COVER_SCREEN_PARAMS = new FrameLayout.LayoutParams(-1, -1);
    JSON_KEY_SUCCESS = "success";
  }
  
  public WebViewController(Context paramContext)
  {
    super(paramContext);
    Logger.i(this.TAG, "C'tor");
    init(paramContext);
    this.locationHelper = LocationHelper.getInstance(paramContext);
    this.inAppPurchaseHelper = InAppPurchaseHelper.getInstance();
    this.downloadManager = DownloadManager.getInstance(paramContext);
    this.downloadManager.setOnPreCacheCompletion(this);
    this.cacheManager = new CacheManager(paramContext);
    this.cacheManager.refreshRootDirectory(paramContext);
    this.mWebChromeClient = new ChromeClient(null);
    setWebViewClient(new ViewClient(null));
    setWebChromeClient(this.mWebChromeClient);
    WebSettings localWebSettings = getSettings();
    localWebSettings.setLoadWithOverviewMode(true);
    localWebSettings.setUseWideViewPort(true);
    setVerticalScrollBarEnabled(false);
    setHorizontalScrollBarEnabled(false);
    localWebSettings.setBuiltInZoomControls(false);
    localWebSettings.setJavaScriptEnabled(true);
    localWebSettings.setSupportMultipleWindows(true);
    localWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
    localWebSettings.setGeolocationEnabled(true);
    localWebSettings.setGeolocationDatabasePath("/data/data/org.itri.html5webview/databases/");
    localWebSettings.setDomStorageEnabled(true);
    if (Build.VERSION.SDK_INT > 11) {
      localWebSettings.setDisplayZoomControls(false);
    }
    if (Build.VERSION.SDK_INT >= 17) {
      getSettings().setMediaPlaybackRequiresUserGesture(false);
    }
    if (Build.VERSION.SDK_INT >= 19) {
      setWebContentsDebuggingEnabled(true);
    }
    addJavascriptInterface(new JSInterface(paramContext), "Android");
    setDownloadListener(this);
    setOnTouchListener(new View.OnTouchListener()
    {
      public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
      {
        float f1;
        float f2;
        int i;
        int j;
        int k;
        int m;
        int n;
        int i1;
        if (paramAnonymousMotionEvent.getAction() == 1)
        {
          f1 = paramAnonymousMotionEvent.getX();
          f2 = paramAnonymousMotionEvent.getY();
          Logger.i(WebViewController.this.TAG, "X:" + (int)f1 + " Y:" + (int)f2);
          i = SDKUtils.getDeviceWidth();
          j = SDKUtils.getDeviceHeight();
          Logger.i(WebViewController.this.TAG, "Width:" + i + " Height:" + j);
          k = SDKUtils.dpToPx(WebViewController.this.mHiddenForceCloseWidth);
          m = SDKUtils.dpToPx(WebViewController.this.mHiddenForceCloseHeight);
          if (!"top-right".equalsIgnoreCase(WebViewController.this.mHiddenForceCloseLocation)) {
            break label225;
          }
          n = i - (int)f1;
          i1 = (int)f2;
        }
        for (;;)
        {
          if ((n <= k) && (i1 <= m))
          {
            WebViewController.this.isRemoveCloseEventHandler = false;
            if (WebViewController.this.mCloseEventTimer != null) {
              WebViewController.this.mCloseEventTimer.cancel();
            }
            WebViewController.this.mCloseEventTimer = new CountDownTimer(2000L, 500L)
            {
              public void onFinish()
              {
                Logger.i(WebViewController.this.TAG, "Close Event Timer Finish");
                if (WebViewController.this.isRemoveCloseEventHandler)
                {
                  WebViewController.this.isRemoveCloseEventHandler = false;
                  return;
                }
                WebViewController.this.engageEnd("forceClose");
              }
              
              public void onTick(long paramAnonymous2Long)
              {
                Logger.i(WebViewController.this.TAG, "Close Event Timer Tick " + paramAnonymous2Long);
              }
            }.start();
          }
          return false;
          label225:
          if ("top-left".equalsIgnoreCase(WebViewController.this.mHiddenForceCloseLocation))
          {
            n = (int)f1;
            i1 = (int)f2;
          }
          else if ("bottom-right".equalsIgnoreCase(WebViewController.this.mHiddenForceCloseLocation))
          {
            n = i - (int)f1;
            i1 = j - (int)f2;
          }
          else
          {
            boolean bool = "bottom-left".equalsIgnoreCase(WebViewController.this.mHiddenForceCloseLocation);
            n = 0;
            i1 = 0;
            if (bool)
            {
              n = (int)f1;
              i1 = j - (int)f2;
            }
          }
        }
      }
    });
  }
  
  public WebViewController(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    Logger.i(this.TAG, "C'tor");
    init(paramContext);
  }
  
  public WebViewController(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    Logger.i(this.TAG, "C'tor");
    init(paramContext);
  }
  
  private void closeWebView()
  {
    if (this.mChangeListener != null) {
      this.mChangeListener.onHide();
    }
  }
  
  private void createInitProductJSMethod(SSAEnums.ProductType paramProductType)
  {
    if (paramProductType == SSAEnums.ProductType.BrandConnect)
    {
      StringBuilder localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append("SSA_CORE.SDKController.runFunction(").append("'").append("initBrandConnect").append("'").append(",").append("'").append("onInitBrandConnectSuccess").append("'").append(",").append("'").append("onInitBrandConnectFail").append("'").append(");");
      injectJavascript(localStringBuilder1.toString());
    }
    do
    {
      return;
      if (paramProductType == SSAEnums.ProductType.Interstitial)
      {
        StringBuilder localStringBuilder2 = new StringBuilder();
        localStringBuilder2.append("SSA_CORE.SDKController.runFunction(").append("'").append("initInterstitial").append("'").append(",").append("'").append("onInitInterstitialSuccess").append("'").append(",").append("'").append("onInitInterstitialFail").append("'").append(");");
        injectJavascript(localStringBuilder2.toString());
        return;
      }
      if (paramProductType == SSAEnums.ProductType.OfferWall)
      {
        StringBuilder localStringBuilder3 = new StringBuilder();
        localStringBuilder3.append("SSA_CORE.SDKController.runFunction(").append("'").append("showOfferWall").append("'").append(",").append("'").append("onShowOfferWallSuccess").append("'").append(",").append("'").append("onShowOfferWallFail").append("'").append(");");
        injectJavascript(localStringBuilder3.toString());
        return;
      }
    } while (paramProductType != SSAEnums.ProductType.OfferWallCredits);
    StringBuilder localStringBuilder4 = new StringBuilder();
    localStringBuilder4.append("SSA_CORE.SDKController.runFunction(").append("'").append("getUserCredits").append("?").append("parameters").append("=").append(parseToJson("productType", "OfferWall", "applicationKey", this.mOWCreditsAppKey, "applicationUserId", this.mOWCreditsUserId, null, null, null, false)).append("'").append(",").append("'").append("null").append("'").append(",").append("'").append("onGetUserCreditsFail").append("'").append(")").append(";");
    injectJavascript(localStringBuilder4.toString());
  }
  
  private String extractFailFunctionToCall(String paramString)
  {
    return new SSAObj(paramString).getString(JSON_KEY_FAIL);
  }
  
  private String extractSuccessFunctionToCall(String paramString)
  {
    return new SSAObj(paramString).getString(JSON_KEY_SUCCESS);
  }
  
  /* Error */
  private Object[] getApplicationParams(String paramString)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_2
    //   2: new 641	org/json/JSONObject
    //   5: dup
    //   6: invokespecial 642	org/json/JSONObject:<init>	()V
    //   9: astore_3
    //   10: ldc_w 644
    //   13: astore 4
    //   15: ldc_w 644
    //   18: astore 5
    //   20: aload_1
    //   21: invokestatic 650	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   24: ifne +223 -> 247
    //   27: aload_1
    //   28: getstatic 111	com/supersonicads/sdk/data/SSAEnums$ProductType:BrandConnect	Lcom/supersonicads/sdk/data/SSAEnums$ProductType;
    //   31: invokevirtual 651	com/supersonicads/sdk/data/SSAEnums$ProductType:toString	()Ljava/lang/String;
    //   34: invokevirtual 656	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   37: ifeq +142 -> 179
    //   40: aload_0
    //   41: getfield 382	com/supersonicads/sdk/controller/WebViewController:mBCAppKey	Ljava/lang/String;
    //   44: astore 4
    //   46: aload_0
    //   47: getfield 385	com/supersonicads/sdk/controller/WebViewController:mBCUserId	Ljava/lang/String;
    //   50: astore 5
    //   52: aload_3
    //   53: ldc_w 617
    //   56: aload_1
    //   57: invokevirtual 660	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   60: pop
    //   61: aload 5
    //   63: invokestatic 650	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   66: ifne +196 -> 262
    //   69: aload_3
    //   70: ldc_w 622
    //   73: invokestatic 665	com/supersonicads/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   76: aload 5
    //   78: invokestatic 665	com/supersonicads/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   81: invokevirtual 660	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   84: pop
    //   85: aload 4
    //   87: invokestatic 650	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   90: ifne +187 -> 277
    //   93: aload_3
    //   94: ldc_w 620
    //   97: invokestatic 665	com/supersonicads/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   100: aload 4
    //   102: invokestatic 665	com/supersonicads/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   105: invokevirtual 660	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   108: pop
    //   109: aload_0
    //   110: getfield 389	com/supersonicads/sdk/controller/WebViewController:mExtraParameters	Ljava/util/Map;
    //   113: ifnull +41 -> 154
    //   116: aload_0
    //   117: getfield 389	com/supersonicads/sdk/controller/WebViewController:mExtraParameters	Ljava/util/Map;
    //   120: invokeinterface 670 1 0
    //   125: ifne +29 -> 154
    //   128: aload_0
    //   129: getfield 389	com/supersonicads/sdk/controller/WebViewController:mExtraParameters	Ljava/util/Map;
    //   132: invokeinterface 674 1 0
    //   137: invokeinterface 680 1 0
    //   142: astore 7
    //   144: aload 7
    //   146: invokeinterface 685 1 0
    //   151: ifne +131 -> 282
    //   154: iconst_2
    //   155: anewarray 687	java/lang/Object
    //   158: astore 6
    //   160: aload 6
    //   162: iconst_0
    //   163: aload_3
    //   164: invokevirtual 688	org/json/JSONObject:toString	()Ljava/lang/String;
    //   167: aastore
    //   168: aload 6
    //   170: iconst_1
    //   171: iload_2
    //   172: invokestatic 694	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   175: aastore
    //   176: aload 6
    //   178: areturn
    //   179: aload_1
    //   180: getstatic 118	com/supersonicads/sdk/data/SSAEnums$ProductType:Interstitial	Lcom/supersonicads/sdk/data/SSAEnums$ProductType;
    //   183: invokevirtual 651	com/supersonicads/sdk/data/SSAEnums$ProductType:toString	()Ljava/lang/String;
    //   186: invokevirtual 656	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   189: ifeq +18 -> 207
    //   192: aload_0
    //   193: getfield 399	com/supersonicads/sdk/controller/WebViewController:mISAppKey	Ljava/lang/String;
    //   196: astore 4
    //   198: aload_0
    //   199: getfield 402	com/supersonicads/sdk/controller/WebViewController:mISUserId	Ljava/lang/String;
    //   202: astore 5
    //   204: goto -152 -> 52
    //   207: aload_1
    //   208: getstatic 121	com/supersonicads/sdk/data/SSAEnums$ProductType:OfferWall	Lcom/supersonicads/sdk/data/SSAEnums$ProductType;
    //   211: invokevirtual 651	com/supersonicads/sdk/data/SSAEnums$ProductType:toString	()Ljava/lang/String;
    //   214: invokevirtual 656	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   217: ifeq -165 -> 52
    //   220: aload_0
    //   221: getfield 416	com/supersonicads/sdk/controller/WebViewController:mOWAppKey	Ljava/lang/String;
    //   224: astore 4
    //   226: aload_0
    //   227: getfield 419	com/supersonicads/sdk/controller/WebViewController:mOWUserId	Ljava/lang/String;
    //   230: astore 5
    //   232: goto -180 -> 52
    //   235: astore 15
    //   237: aload 15
    //   239: invokevirtual 697	org/json/JSONException:printStackTrace	()V
    //   242: iconst_0
    //   243: istore_2
    //   244: goto -183 -> 61
    //   247: iconst_1
    //   248: istore_2
    //   249: goto -188 -> 61
    //   252: astore 13
    //   254: aload 13
    //   256: invokevirtual 697	org/json/JSONException:printStackTrace	()V
    //   259: goto -174 -> 85
    //   262: iconst_1
    //   263: istore_2
    //   264: goto -179 -> 85
    //   267: astore 11
    //   269: aload 11
    //   271: invokevirtual 697	org/json/JSONException:printStackTrace	()V
    //   274: goto -165 -> 109
    //   277: iconst_1
    //   278: istore_2
    //   279: goto -170 -> 109
    //   282: aload 7
    //   284: invokeinterface 701 1 0
    //   289: checkcast 703	java/util/Map$Entry
    //   292: astore 8
    //   294: aload 8
    //   296: invokeinterface 706 1 0
    //   301: checkcast 653	java/lang/String
    //   304: ldc_w 708
    //   307: invokevirtual 656	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   310: ifeq +17 -> 327
    //   313: aload_0
    //   314: aload 8
    //   316: invokeinterface 711 1 0
    //   321: checkcast 653	java/lang/String
    //   324: invokespecial 714	com/supersonicads/sdk/controller/WebViewController:setWebviewCache	(Ljava/lang/String;)V
    //   327: aload_3
    //   328: aload 8
    //   330: invokeinterface 706 1 0
    //   335: checkcast 653	java/lang/String
    //   338: invokestatic 665	com/supersonicads/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   341: aload 8
    //   343: invokeinterface 711 1 0
    //   348: checkcast 653	java/lang/String
    //   351: invokestatic 665	com/supersonicads/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   354: invokevirtual 660	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   357: pop
    //   358: goto -214 -> 144
    //   361: astore 9
    //   363: aload 9
    //   365: invokevirtual 697	org/json/JSONException:printStackTrace	()V
    //   368: goto -224 -> 144
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	371	0	this	WebViewController
    //   0	371	1	paramString	String
    //   1	278	2	bool	boolean
    //   9	319	3	localJSONObject	JSONObject
    //   13	212	4	str1	String
    //   18	213	5	str2	String
    //   158	19	6	arrayOfObject	Object[]
    //   142	141	7	localIterator	Iterator
    //   292	50	8	localEntry	java.util.Map.Entry
    //   361	3	9	localJSONException1	JSONException
    //   267	3	11	localJSONException2	JSONException
    //   252	3	13	localJSONException3	JSONException
    //   235	3	15	localJSONException4	JSONException
    // Exception table:
    //   from	to	target	type
    //   52	61	235	org/json/JSONException
    //   69	85	252	org/json/JSONException
    //   93	109	267	org/json/JSONException
    //   327	358	361	org/json/JSONException
  }
  
  private Context getBaseContext()
  {
    return ((MutableContextWrapper)getContext()).getBaseContext();
  }
  
  private Object[] getDeviceParams(Context paramContext)
  {
    boolean bool = false;
    DeviceProperties localDeviceProperties = DeviceProperties.getInstance(paramContext);
    JSONObject localJSONObject = new JSONObject();
    for (;;)
    {
      try
      {
        localJSONObject.put("appOrientation", "none");
        String str1 = localDeviceProperties.getDeviceOem();
        if (str1 != null) {
          localJSONObject.put(SDKUtils.encodeString("deviceOEM"), SDKUtils.encodeString(str1));
        }
        String str2 = localDeviceProperties.getDeviceModel();
        if (str2 == null) {
          continue;
        }
        localJSONObject.put(SDKUtils.encodeString("deviceModel"), SDKUtils.encodeString(str2));
        SDKUtils.loadGoogleAdvertiserInfo(paramContext);
        String str3 = SDKUtils.getAdvertiserId();
        Boolean localBoolean = Boolean.valueOf(SDKUtils.isLimitAdTrackingEnabled());
        if (!TextUtils.isEmpty(str3))
        {
          Logger.i(this.TAG, "add AID and LAT");
          localJSONObject.put("isLimitAdTrackingEnabled", localBoolean);
          localJSONObject.put("deviceIds" + "[" + "AID" + "]", SDKUtils.encodeString(str3));
        }
        String str4 = localDeviceProperties.getDeviceOsType();
        if (str4 == null) {
          continue;
        }
        localJSONObject.put(SDKUtils.encodeString("deviceOs"), SDKUtils.encodeString(str4));
        String str5 = Integer.toString(localDeviceProperties.getDeviceOsVersion());
        if (str5 == null) {
          continue;
        }
        localJSONObject.put(SDKUtils.encodeString("deviceOSVersion"), str5);
        String str6 = localDeviceProperties.getSupersonicSdkVersion();
        if (str6 != null) {
          localJSONObject.put(SDKUtils.encodeString("SDKVersion"), SDKUtils.encodeString(str6));
        }
        if ((localDeviceProperties.getDeviceCarrier() != null) && (localDeviceProperties.getDeviceCarrier().length() > 0)) {
          localJSONObject.put(SDKUtils.encodeString("mobileCarrier"), SDKUtils.encodeString(localDeviceProperties.getDeviceCarrier()));
        }
        String str7 = SDKUtils.getConnectionType(paramContext);
        if (TextUtils.isEmpty(str7)) {
          continue;
        }
        localJSONObject.put(SDKUtils.encodeString("connectionType"), SDKUtils.encodeString(str7));
        String str8 = paramContext.getResources().getConfiguration().locale.getLanguage();
        if (!TextUtils.isEmpty(str8)) {
          localJSONObject.put(SDKUtils.encodeString("deviceLanguage"), SDKUtils.encodeString(str8.toUpperCase()));
        }
        if (!CacheManager.isExternalStorageAvailable(paramContext)) {
          continue;
        }
        long l = SDKUtils.getAvailableSpaceInMB(paramContext);
        localJSONObject.put(SDKUtils.encodeString("diskFreeSize"), SDKUtils.encodeString(String.valueOf(l)));
        String str9 = String.valueOf(SDKUtils.getDeviceWidth());
        if (str9 == null) {
          continue;
        }
        StringBuilder localStringBuilder1 = new StringBuilder();
        localStringBuilder1.append(SDKUtils.encodeString("deviceScreenSize")).append("[").append(SDKUtils.encodeString("width")).append("]");
        localJSONObject.put(localStringBuilder1.toString(), SDKUtils.encodeString(str9));
        String str10 = String.valueOf(SDKUtils.getDeviceHeight());
        if (str10 == null) {
          continue;
        }
        StringBuilder localStringBuilder2 = new StringBuilder();
        localStringBuilder2.append(SDKUtils.encodeString("deviceScreenSize")).append("[").append(SDKUtils.encodeString("height")).append("]");
        localJSONObject.put(localStringBuilder2.toString(), SDKUtils.encodeString(str10));
        String str11 = SDKUtils.getPackageName(getBaseContext());
        if (!TextUtils.isEmpty(str11)) {
          localJSONObject.put(SDKUtils.encodeString("bundleId"), SDKUtils.encodeString(str11));
        }
        String str12 = String.valueOf(SDKUtils.getDeviceScale());
        if (!TextUtils.isEmpty(str12)) {
          localJSONObject.put(SDKUtils.encodeString("deviceScreenScale"), SDKUtils.encodeString(str12));
        }
      }
      catch (JSONException localJSONException)
      {
        Object[] arrayOfObject;
        localJSONException.printStackTrace();
        continue;
      }
      arrayOfObject = new Object[2];
      arrayOfObject[0] = localJSONObject.toString();
      arrayOfObject[1] = Boolean.valueOf(bool);
      return arrayOfObject;
      bool = true;
      continue;
      bool = true;
      continue;
      bool = true;
      continue;
      bool = true;
      continue;
      bool = true;
      continue;
      bool = true;
      continue;
      bool = true;
    }
  }
  
  private String getRequestParameters()
  {
    DeviceProperties localDeviceProperties = DeviceProperties.getInstance(getBaseContext());
    StringBuilder localStringBuilder = new StringBuilder();
    String str1 = localDeviceProperties.getSupersonicSdkVersion();
    if (!TextUtils.isEmpty(str1)) {
      localStringBuilder.append("SDKVersion").append("=").append(str1).append("&");
    }
    String str2 = localDeviceProperties.getDeviceOsType();
    if (!TextUtils.isEmpty(str2)) {
      localStringBuilder.append("deviceOs").append("=").append(str2);
    }
    int i = getDebugMode();
    int j = 0;
    if (i != 0)
    {
      j = 1;
      localStringBuilder.append("&").append("debug").append("=").append(getDebugMode());
    }
    if ((!"http://s.ssacdn.com/".equalsIgnoreCase(getLoadDomain())) && (getUri() != null))
    {
      String str3 = getUri().getScheme() + ":";
      String str4 = getUri().getHost();
      int k = getUri().getPort();
      if (k != -1) {
        str4 = str4 + ":" + k;
      }
      if (j == 0) {
        localStringBuilder.append("&").append("debug").append("=").append(getDebugMode());
      }
      localStringBuilder.append("&").append("protocol").append("=").append(str3).append("&").append("domain").append("=").append(str4);
    }
    return localStringBuilder.toString();
  }
  
  private Uri getUri()
  {
    return this.mUri;
  }
  
  private void init(Context paramContext)
  {
    this.mLayout = new FrameLayout(paramContext);
    this.mBrowserFrameLayout = new FrameLayout(paramContext);
    this.mCustomViewContainer = new FrameLayout(paramContext);
    FrameLayout.LayoutParams localLayoutParams1 = new FrameLayout.LayoutParams(-1, -1);
    this.mCustomViewContainer.setLayoutParams(localLayoutParams1);
    this.mCustomViewContainer.setVisibility(8);
    this.mBrowserFrameLayout.addView(this.mCustomViewContainer);
    LinearLayout localLinearLayout1 = new LinearLayout(paramContext);
    localLinearLayout1.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
    localLinearLayout1.setOrientation(1);
    this.mBrowserFrameLayout.addView(localLinearLayout1);
    LinearLayout localLinearLayout2 = new LinearLayout(paramContext);
    localLinearLayout2.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
    localLinearLayout1.addView(localLinearLayout2);
    this.mContentView = new FrameLayout(paramContext);
    FrameLayout.LayoutParams localLayoutParams2 = new FrameLayout.LayoutParams(-1, -1);
    this.mContentView.setLayoutParams(localLayoutParams2);
    localLinearLayout1.addView(this.mContentView);
    this.mLayout.addView(this.mBrowserFrameLayout, COVER_SCREEN_PARAMS);
    this.mContentView.addView(this);
  }
  
  private void initProduct(String paramString1, String paramString2, Map<String, String> paramMap, SSAEnums.ProductType paramProductType, String paramString3)
  {
    if ((TextUtils.isEmpty(paramString2)) || (TextUtils.isEmpty(paramString1))) {
      triggerOnControllerInitProductFail("User id or Application key are missing", paramProductType);
    }
    do
    {
      return;
      if (this.mControllerState == SSAEnums.ControllerState.Ready)
      {
        this.cacheManager.setApplicationKey(paramString1, paramProductType);
        this.cacheManager.setUserID(paramString2, paramProductType);
        createInitProductJSMethod(paramProductType);
        return;
      }
      setMissProduct(paramProductType);
      if (this.mControllerState == SSAEnums.ControllerState.Failed)
      {
        triggerOnControllerInitProductFail(SDKUtils.createErrorMessage(paramString3, "Initiating Controller"), paramProductType);
        return;
      }
    } while (!this.mGlobalControllerTimeFinish);
    downloadController();
  }
  
  private void injectJavascript(String paramString)
  {
    String str1 = "empty";
    if (getDebugMode() == SSAEnums.DebugMode.MODE_0.getValue()) {}
    for (str1 = "console.log(\"JS exeption: \" + JSON.stringify(e));";; str1 = "console.log(\"JS exeption: \" + JSON.stringify(e));") {
      do
      {
        final StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("try{").append(paramString).append("}catch(e){").append(str1).append("}");
        final String str2 = "javascript:" + localStringBuilder.toString();
        Context localContext = getBaseContext();
        if ((localContext instanceof Activity)) {
          ((Activity)localContext).runOnUiThread(new Runnable()
          {
            public void run()
            {
              Logger.i(WebViewController.this.TAG, str2);
              try
              {
                if (WebViewController.this.isKitkatAndAbove != null)
                {
                  if (WebViewController.this.isKitkatAndAbove.booleanValue())
                  {
                    WebViewController.this.evaluateJavascript(localStringBuilder.toString(), null);
                    return;
                  }
                  WebViewController.this.loadUrl(str2);
                  return;
                }
              }
              catch (Throwable localThrowable1)
              {
                Logger.e(WebViewController.this.TAG, "injectJavascript: " + localThrowable1.toString());
                return;
              }
              int i = Build.VERSION.SDK_INT;
              if (i >= 19) {
                try
                {
                  WebViewController.this.evaluateJavascript(localStringBuilder.toString(), null);
                  WebViewController.this.isKitkatAndAbove = Boolean.valueOf(true);
                  return;
                }
                catch (NoSuchMethodError localNoSuchMethodError)
                {
                  Logger.e(WebViewController.this.TAG, "evaluateJavascrip NoSuchMethodError: SDK version=" + Build.VERSION.SDK_INT + " " + localNoSuchMethodError);
                  WebViewController.this.loadUrl(str2);
                  WebViewController.this.isKitkatAndAbove = Boolean.valueOf(false);
                  return;
                }
                catch (Throwable localThrowable2)
                {
                  Logger.e(WebViewController.this.TAG, "evaluateJavascrip Exception: SDK version=" + Build.VERSION.SDK_INT + " " + localThrowable2);
                  WebViewController.this.loadUrl(str2);
                  WebViewController.this.isKitkatAndAbove = Boolean.valueOf(false);
                  return;
                }
              }
              WebViewController.this.loadUrl(str2);
              WebViewController.this.isKitkatAndAbove = Boolean.valueOf(false);
            }
          });
        }
        return;
      } while ((getDebugMode() < SSAEnums.DebugMode.MODE_1.getValue()) || (getDebugMode() > SSAEnums.DebugMode.MODE_3.getValue()));
    }
  }
  
  private String mapToJson(Map<String, String> paramMap)
  {
    JSONObject localJSONObject = new JSONObject();
    Iterator localIterator;
    if ((paramMap != null) && (!paramMap.isEmpty())) {
      localIterator = paramMap.keySet().iterator();
    }
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return localJSONObject.toString();
      }
      String str1 = (String)localIterator.next();
      String str2 = (String)paramMap.get(str1);
      try
      {
        localJSONObject.put(str1, SDKUtils.encodeString(str2));
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
      }
    }
  }
  
  private String parseToJson(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, boolean paramBoolean)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      if ((!TextUtils.isEmpty(paramString1)) && (!TextUtils.isEmpty(paramString2))) {
        localJSONObject.put(paramString1, SDKUtils.encodeString(paramString2));
      }
      if ((!TextUtils.isEmpty(paramString3)) && (!TextUtils.isEmpty(paramString4))) {
        localJSONObject.put(paramString3, SDKUtils.encodeString(paramString4));
      }
      if ((!TextUtils.isEmpty(paramString5)) && (!TextUtils.isEmpty(paramString6))) {
        localJSONObject.put(paramString5, SDKUtils.encodeString(paramString6));
      }
      if ((!TextUtils.isEmpty(paramString7)) && (!TextUtils.isEmpty(paramString8))) {
        localJSONObject.put(paramString7, SDKUtils.encodeString(paramString8));
      }
      if (!TextUtils.isEmpty(paramString9)) {
        localJSONObject.put(paramString9, paramBoolean);
      }
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        localJSONException.printStackTrace();
      }
    }
    return localJSONObject.toString();
  }
  
  private void resetMissProduct()
  {
    this.mRVmiss = false;
    this.mISmiss = false;
    this.mOWmiss = false;
    this.mOWCreditsMiss = false;
  }
  
  private void responseBack(String paramString1, boolean paramBoolean, String paramString2, String paramString3)
  {
    SSAObj localSSAObj = new SSAObj(paramString1);
    String str1 = localSSAObj.getString(JSON_KEY_SUCCESS);
    String str2 = localSSAObj.getString(JSON_KEY_FAIL);
    String str3;
    if (paramBoolean)
    {
      boolean bool2 = TextUtils.isEmpty(str1);
      str3 = null;
      if (!bool2) {
        str3 = str1;
      }
    }
    for (;;)
    {
      StringBuilder localStringBuilder;
      if (!TextUtils.isEmpty(str3))
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("SSA_CORE.SDKController.runFunction(").append("'").append(str3).append("?").append("parameters").append("=");
        if (TextUtils.isEmpty(paramString2)) {}
      }
      try
      {
        String str5 = new JSONObject(paramString1).put("errMsg", paramString2).toString();
        paramString1 = str5;
      }
      catch (JSONException localJSONException2)
      {
        label138:
        label170:
        break label138;
      }
      if (!TextUtils.isEmpty(paramString3)) {}
      try
      {
        String str4 = new JSONObject(paramString1).put("errCode", paramString3).toString();
        paramString1 = str4;
      }
      catch (JSONException localJSONException1)
      {
        boolean bool1;
        break label170;
      }
      localStringBuilder.append(paramString1).append("'").append(")").append(";");
      injectJavascript(localStringBuilder.toString());
      return;
      bool1 = TextUtils.isEmpty(str2);
      str3 = null;
      if (!bool1) {
        str3 = str2;
      }
    }
  }
  
  private void sendProductErrorMessage(SSAEnums.ProductType paramProductType)
  {
    String str = "";
    switch (paramProductType)
    {
    }
    for (;;)
    {
      triggerOnControllerInitProductFail(SDKUtils.createErrorMessage(str, "Initiating Controller"), paramProductType);
      return;
      str = "Init BC";
      continue;
      str = "Init IS";
      continue;
      str = "Show OW";
      continue;
      str = "Show OW Credits";
    }
  }
  
  private void setMissProduct(SSAEnums.ProductType paramProductType)
  {
    if (paramProductType == SSAEnums.ProductType.BrandConnect) {
      this.mRVmiss = true;
    }
    for (;;)
    {
      Logger.i(this.TAG, "setMissProduct(" + paramProductType + ")");
      return;
      if (paramProductType == SSAEnums.ProductType.Interstitial) {
        this.mISmiss = true;
      } else if (paramProductType == SSAEnums.ProductType.OfferWall) {
        this.mOWmiss = true;
      } else if (paramProductType == SSAEnums.ProductType.OfferWallCredits) {
        this.mOWCreditsMiss = true;
      }
    }
  }
  
  private void setUri(Uri paramUri)
  {
    this.mUri = paramUri;
  }
  
  private void setWebviewBackground(String paramString)
  {
    String str = new SSAObj(paramString).getString("color");
    boolean bool = "transparent".equalsIgnoreCase(str);
    int i = 0;
    if (!bool) {
      i = Color.parseColor(str);
    }
    setBackgroundColor(i);
  }
  
  private void setWebviewCache(String paramString)
  {
    if (paramString.equalsIgnoreCase("0"))
    {
      getSettings().setCacheMode(2);
      return;
    }
    getSettings().setCacheMode(-1);
  }
  
  private boolean shouldNotifyDeveloper(String paramString)
  {
    if (paramString == null)
    {
      Logger.d(this.TAG, "Trying to trigger a listener - no product was found");
      return false;
    }
    if (paramString.equalsIgnoreCase(SSAEnums.ProductType.Interstitial.toString())) {
      if (this.mOnInitInterstitialListener != null) {
        bool1 = true;
      }
    }
    boolean bool2;
    do
    {
      for (;;)
      {
        if (!bool1) {
          Logger.d(this.TAG, "Trying to trigger a listener - no listener was found for product " + paramString);
        }
        return bool1;
        bool1 = false;
      }
      if (paramString.equalsIgnoreCase(SSAEnums.ProductType.BrandConnect.toString()))
      {
        if (this.mOnRewardedVideoListener != null) {}
        for (bool1 = true;; bool1 = false) {
          break;
        }
      }
      if (paramString.equalsIgnoreCase(SSAEnums.ProductType.OfferWall.toString())) {
        break;
      }
      bool2 = paramString.equalsIgnoreCase(SSAEnums.ProductType.OfferWallCredits.toString());
      bool1 = false;
    } while (!bool2);
    if (this.mOnOfferWallListener != null) {}
    for (boolean bool1 = true;; bool1 = false) {
      break;
    }
  }
  
  private void toastingErrMsg(final String paramString1, String paramString2)
  {
    final String str = new SSAObj(paramString2).getString("errMsg");
    if (!TextUtils.isEmpty(str))
    {
      Context localContext = getBaseContext();
      if ((localContext instanceof Activity)) {
        ((Activity)localContext).runOnUiThread(new Runnable()
        {
          public void run()
          {
            if (WebViewController.this.getDebugMode() == SSAEnums.DebugMode.MODE_3.getValue()) {
              Toast.makeText(WebViewController.this.getBaseContext(), paramString1 + " : " + str, 1).show();
            }
          }
        });
      }
    }
  }
  
  private void triggerOnControllerInitProductFail(final String paramString, final SSAEnums.ProductType paramProductType)
  {
    if (shouldNotifyDeveloper(paramProductType.toString()))
    {
      Context localContext = getBaseContext();
      if ((localContext instanceof Activity)) {
        ((Activity)localContext).runOnUiThread(new Runnable()
        {
          public void run()
          {
            if (SSAEnums.ProductType.BrandConnect == paramProductType) {
              WebViewController.this.mOnRewardedVideoListener.onRVInitFail(paramString);
            }
            do
            {
              return;
              if (SSAEnums.ProductType.Interstitial == paramProductType)
              {
                WebViewController.this.mOnInitInterstitialListener.onISInitFail(paramString);
                return;
              }
              if (SSAEnums.ProductType.OfferWall == paramProductType)
              {
                WebViewController.this.mOnOfferWallListener.onOWShowFail(paramString);
                return;
              }
            } while (SSAEnums.ProductType.OfferWallCredits != paramProductType);
            WebViewController.this.mOnOfferWallListener.onGetOWCreditsFailed(paramString);
          }
        });
      }
    }
  }
  
  public void assetCached(String paramString1, String paramString2)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("SSA_CORE.SDKController.runFunction(").append("'").append("assetCached").append("?").append("parameters").append("=").append(parseToJson("file", paramString1, "path", paramString2, null, null, null, null, null, false)).append("'").append(");");
    injectJavascript(localStringBuilder.toString());
  }
  
  public void assetCachedFailed(String paramString1, String paramString2, String paramString3)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("SSA_CORE.SDKController.runFunction(").append("'").append("assetCachedFailed").append("?").append("parameters").append("=").append(parseToJson("file", paramString1, "path", paramString2, "errMsg", paramString3, null, null, null, false)).append("'").append(");");
    injectJavascript(localStringBuilder.toString());
  }
  
  public void destroy()
  {
    super.destroy();
    if (this.mActivity != null) {
      this.mActivity.finish();
    }
  }
  
  public void deviceStatusChanged(boolean paramBoolean1, boolean paramBoolean2)
  {
    String str = "none";
    if (paramBoolean1) {
      str = "wifi";
    }
    for (;;)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("SSA_CORE.SDKController.runFunction(").append("'").append("deviceStatusChanged").append("?").append("parameters").append("=").append(parseToJson("connectionType", str, null, null, null, null, null, null, null, false)).append("'").append(");");
      injectJavascript(localStringBuilder.toString());
      return;
      if (paramBoolean2) {
        str = "3g";
      }
    }
  }
  
  public void downloadController()
  {
    this.cacheManager.deleteFile("", "mobileController.html");
    setUri(Uri.parse(getLoadDomain()));
    String str = this.cacheManager.getSDKDownloadUrl();
    if (TextUtils.isEmpty(str)) {
      str = "http://s.ssacdn.com/mobileSDKController/mobileController.html";
    }
    SSAFile localSSAFile = new SSAFile(str, "");
    this.mGlobalControllerTimer = new CountDownTimer(40000L, 1000L)
    {
      public void onFinish()
      {
        Logger.i(WebViewController.this.TAG, "Global Controller Timer Finish");
        WebViewController.this.mGlobalControllerTimeFinish = true;
      }
      
      public void onTick(long paramAnonymousLong)
      {
        Logger.i(WebViewController.this.TAG, "Global Controller Timer Tick " + paramAnonymousLong);
      }
    }.start();
    if (!this.downloadManager.isMobileControllerThreadLive())
    {
      Logger.i(this.TAG, "Download Mobile Controller: " + str);
      this.downloadManager.downloadMobileControllerFile(localSSAFile);
      return;
    }
    Logger.i(this.TAG, "Download Mobile Controller: already alive");
  }
  
  public void engageEnd(String paramString)
  {
    if (paramString == "forceClose") {
      closeWebView();
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("SSA_CORE.SDKController.runFunction(").append("'").append("engageEnd").append("?").append("parameters").append("=").append(parseToJson("action", paramString, null, null, null, null, null, null, null, false)).append("'").append(");");
    injectJavascript(localStringBuilder.toString());
  }
  
  public void enterBackground()
  {
    if (this.mControllerState == SSAEnums.ControllerState.Ready)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("SSA_CORE.SDKController.runFunction(").append("'").append("enterBackground").append("'").append(");");
      injectJavascript(localStringBuilder.toString());
    }
  }
  
  public void enterForeground()
  {
    if (this.mControllerState == SSAEnums.ControllerState.Ready)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("SSA_CORE.SDKController.runFunction(").append("'").append("enterForeground").append("'").append(");");
      injectJavascript(localStringBuilder.toString());
    }
  }
  
  public String getControllerKeyPressed()
  {
    String str = this.mControllerKeyPressed;
    setControllerKeyPressed("interrupt");
    return str;
  }
  
  public int getDebugMode()
  {
    return mDebugMode;
  }
  
  public String getDownloadDomain()
  {
    return mDownloadDomain;
  }
  
  public FrameLayout getLayout()
  {
    return this.mLayout;
  }
  
  public String getLoadDomain()
  {
    return mLoadDomain;
  }
  
  public void getOfferWallCredits(String paramString1, String paramString2, OnOfferWallListener paramOnOfferWallListener)
  {
    this.mOWCreditsAppKey = paramString1;
    this.mOWCreditsUserId = paramString2;
    this.mOnOfferWallListener = paramOnOfferWallListener;
    initProduct(this.mOWCreditsAppKey, this.mOWCreditsUserId, null, SSAEnums.ProductType.OfferWallCredits, "Show OW Credits");
  }
  
  public String getOrientationState()
  {
    return this.mOrientationState;
  }
  
  public State getState()
  {
    return this.mState;
  }
  
  public boolean handleSearchKeysURLs(String paramString)
  {
    List localList = this.cacheManager.getSearchKeys();
    Iterator localIterator;
    if ((localList != null) && (!localList.isEmpty())) {
      localIterator = localList.iterator();
    }
    do
    {
      if (!localIterator.hasNext()) {
        return false;
      }
    } while (!paramString.contains((String)localIterator.next()));
    Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
    getBaseContext().startActivity(localIntent);
    return true;
  }
  
  public void hideCustomView()
  {
    this.mWebChromeClient.onHideCustomView();
  }
  
  public boolean inCustomView()
  {
    return this.mCustomView != null;
  }
  
  public void initBrandConnect(String paramString1, String paramString2, Map<String, String> paramMap, OnRewardedVideoListener paramOnRewardedVideoListener)
  {
    this.mBCAppKey = paramString1;
    this.mBCUserId = paramString2;
    this.mExtraParameters = paramMap;
    this.mOnRewardedVideoListener = paramOnRewardedVideoListener;
    initProduct(this.mBCAppKey, this.mBCUserId, this.mExtraParameters, SSAEnums.ProductType.BrandConnect, "Init BC");
  }
  
  public void initInterstitial(String paramString1, String paramString2, Map<String, String> paramMap, OnInterstitialListener paramOnInterstitialListener)
  {
    this.mISAppKey = paramString1;
    this.mISUserId = paramString2;
    this.mExtraParameters = paramMap;
    this.mOnInitInterstitialListener = paramOnInterstitialListener;
    initProduct(this.mISAppKey, this.mISUserId, this.mExtraParameters, SSAEnums.ProductType.Interstitial, "Init IS");
  }
  
  public void interceptedUrlToStore()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("SSA_CORE.SDKController.runFunction(").append("'").append("interceptedUrlToStore").append("'").append(");");
    injectJavascript(localStringBuilder.toString());
  }
  
  public void load(int paramInt)
  {
    try
    {
      loadUrl("about:blank");
      String str1 = this.cacheManager.getRootDirectoryPath();
      String str2 = "file://" + str1 + File.separator + "mobileController.html";
      if (new File(str1 + File.separator + "mobileController.html").exists())
      {
        this.mRequestParameters = getRequestParameters();
        str3 = str2 + "?" + this.mRequestParameters;
        this.mLoadControllerTimer = new CountDownTimer(10000L, 1000L)
        {
          public void onFinish()
          {
            Logger.i(WebViewController.this.TAG, "Loading Controller Timer Finish");
            if (this.val$loadAttemp == 2)
            {
              WebViewController.this.mGlobalControllerTimer.cancel();
              if (WebViewController.this.mRVmiss) {
                WebViewController.this.sendProductErrorMessage(SSAEnums.ProductType.BrandConnect);
              }
              if (WebViewController.this.mISmiss) {
                WebViewController.this.sendProductErrorMessage(SSAEnums.ProductType.Interstitial);
              }
              if (WebViewController.this.mOWmiss) {
                WebViewController.this.sendProductErrorMessage(SSAEnums.ProductType.OfferWall);
              }
              if (WebViewController.this.mOWCreditsMiss) {
                WebViewController.this.sendProductErrorMessage(SSAEnums.ProductType.OfferWallCredits);
              }
              return;
            }
            WebViewController.this.load(2);
          }
          
          public void onTick(long paramAnonymousLong)
          {
            Logger.i(WebViewController.this.TAG, "Loading Controller Timer Tick " + paramAnonymousLong);
          }
        }.start();
      }
    }
    catch (Throwable localThrowable1)
    {
      try
      {
        String str3;
        loadUrl(str3);
        Logger.i(this.TAG, "load(): " + str3);
        return;
        localThrowable1 = localThrowable1;
        Logger.e(this.TAG, "WebViewController:: load: " + localThrowable1.toString());
      }
      catch (Throwable localThrowable2)
      {
        for (;;)
        {
          Logger.e(this.TAG, "WebViewController:: load: " + localThrowable2.toString());
        }
      }
      Logger.i(this.TAG, "load(): Mobile Controller HTML Does not exist");
    }
  }
  
  public void loadInterstitial()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("SSA_CORE.SDKController.runFunction(").append("'").append("loadInterstitial").append("'").append(",").append("'").append("onLoadInterstitialSuccess").append("'").append(",").append("'").append("onLoadInterstitialFail").append("'").append(");");
    injectJavascript(localStringBuilder.toString());
  }
  
  public void nativeNavigationPressed(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    String str = parseToJson("action", paramString, null, null, null, null, null, null, null, false);
    localStringBuilder.append("SSA_CORE.SDKController.runFunction(").append("'").append("nativeNavigationPressed").append("?").append("parameters").append("=").append(str).append("'").append(");");
    injectJavascript(localStringBuilder.toString());
  }
  
  public void onDownloadStart(String paramString1, String paramString2, String paramString3, String paramString4, long paramLong)
  {
    Logger.i(this.TAG, paramString1 + " " + paramString4);
  }
  
  public void onFileDownloadFail(SSAFile paramSSAFile)
  {
    if (paramSSAFile.getFile().contains("mobileController.html"))
    {
      this.mGlobalControllerTimer.cancel();
      if (this.mRVmiss) {
        sendProductErrorMessage(SSAEnums.ProductType.BrandConnect);
      }
      if (this.mISmiss) {
        sendProductErrorMessage(SSAEnums.ProductType.Interstitial);
      }
      if (this.mOWmiss) {
        sendProductErrorMessage(SSAEnums.ProductType.OfferWall);
      }
      if (this.mOWCreditsMiss) {
        sendProductErrorMessage(SSAEnums.ProductType.OfferWallCredits);
      }
      return;
    }
    assetCachedFailed(paramSSAFile.getFile(), paramSSAFile.getPath(), paramSSAFile.getErrMsg());
  }
  
  public void onFileDownloadSuccess(SSAFile paramSSAFile)
  {
    if (paramSSAFile.getFile().contains("mobileController.html"))
    {
      load(1);
      return;
    }
    assetCached(paramSSAFile.getFile(), paramSSAFile.getPath());
  }
  
  public void pageFinished()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("SSA_CORE.SDKController.runFunction(").append("'").append("pageFinished").append("'").append(");");
    injectJavascript(localStringBuilder.toString());
  }
  
  public void pause()
  {
    if (Build.VERSION.SDK_INT > 10) {}
    try
    {
      onPause();
      requestAudioFocus();
      return;
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        Logger.i(this.TAG, "WebViewController: pause() - " + localThrowable);
      }
    }
  }
  
  public void registerConnectionReceiver(Context paramContext)
  {
    paramContext.registerReceiver(this.mConnectionReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
  }
  
  public void requestAudioFocus()
  {
    ((AudioManager)getBaseContext().getSystemService("audio")).requestAudioFocus(new AudioManager.OnAudioFocusChangeListener()
    {
      public void onAudioFocusChange(int paramAnonymousInt) {}
    }, 3, 2);
  }
  
  public void resume()
  {
    if (Build.VERSION.SDK_INT > 10) {}
    try
    {
      onResume();
      return;
    }
    catch (Throwable localThrowable)
    {
      Logger.i(this.TAG, "WebViewController: onResume() - " + localThrowable);
    }
  }
  
  public void runGenericFunction(String paramString, Map<String, String> paramMap, OnGenericFunctionListener paramOnGenericFunctionListener)
  {
    this.mOnGenericFunctionListener = paramOnGenericFunctionListener;
    if ("initBrandConnect".equalsIgnoreCase(paramString))
    {
      initBrandConnect((String)paramMap.get("applicationUserId"), (String)paramMap.get("applicationKey"), paramMap, this.mOnRewardedVideoListener);
      return;
    }
    if ("showBrandConnect".equalsIgnoreCase(paramString))
    {
      showBrandConnect();
      return;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("SSA_CORE.SDKController.runFunction(").append("'").append(paramString).append("?").append("parameters").append("=").append(mapToJson(paramMap)).append("'").append(",").append("'").append("onGenericFunctionSuccess").append("'").append(",").append("'").append("onGenericFunctionFail").append("'").append(")").append(";");
    injectJavascript(localStringBuilder.toString());
  }
  
  public void setActivity(Activity paramActivity)
  {
    this.mActivity = paramActivity;
  }
  
  public void setControllerKeyPressed(String paramString)
  {
    this.mControllerKeyPressed = paramString;
  }
  
  public void setDebugMode(int paramInt)
  {
    mDebugMode = paramInt;
  }
  
  public void setDownloadDomain(String paramString)
  {
    mDownloadDomain = paramString;
  }
  
  public void setLoadDomain(String paramString)
  {
    mLoadDomain = paramString;
  }
  
  public void setOnWebViewControllerChangeListener(OnWebViewControllerChangeListener paramOnWebViewControllerChangeListener)
  {
    this.mChangeListener = paramOnWebViewControllerChangeListener;
  }
  
  public void setOrientationState(String paramString)
  {
    this.mOrientationState = paramString;
  }
  
  public void setState(State paramState)
  {
    this.mState = paramState;
  }
  
  public void showBrandConnect()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("SSA_CORE.SDKController.runFunction(").append("'").append("showBrandConnect").append("'").append(",").append("'").append("onShowBrandConnectSuccess").append("'").append(",").append("'").append("onShowBrandConnectFail").append("'").append(");");
    injectJavascript(localStringBuilder.toString());
  }
  
  public void showInterstitial()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("SSA_CORE.SDKController.runFunction(").append("'").append("showInterstitial").append("'").append(",").append("'").append("onShowInterstitialSuccess").append("'").append(",").append("'").append("onShowInterstitialFail").append("'").append(");");
    injectJavascript(localStringBuilder.toString());
  }
  
  public void showOfferWall(String paramString1, String paramString2, Map<String, String> paramMap, OnOfferWallListener paramOnOfferWallListener)
  {
    this.mOWAppKey = paramString1;
    this.mOWUserId = paramString2;
    this.mExtraParameters = paramMap;
    this.mOnOfferWallListener = paramOnOfferWallListener;
    initProduct(this.mOWAppKey, this.mOWUserId, this.mExtraParameters, SSAEnums.ProductType.OfferWall, "Show OW");
  }
  
  public void unregisterConnectionReceiver(Context paramContext)
  {
    try
    {
      paramContext.unregisterReceiver(this.mConnectionReceiver);
      return;
    }
    catch (Exception localException)
    {
      Log.e(this.TAG, "unregisterConnectionReceiver - " + localException);
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException) {}
  }
  
  public void viewableChange(boolean paramBoolean, String paramString)
  {
    String str = parseToJson("webview", paramString, null, null, null, null, null, null, "isViewable", paramBoolean);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("SSA_CORE.SDKController.runFunction(").append("'").append("viewableChange").append("?").append("parameters").append("=").append(str).append("'").append(");");
    injectJavascript(localStringBuilder.toString());
  }
  
  private class ChromeClient
    extends WebChromeClient
  {
    private ChromeClient() {}
    
    public boolean onConsoleMessage(ConsoleMessage paramConsoleMessage)
    {
      Logger.i("MyApplication", paramConsoleMessage.message() + " -- From line " + paramConsoleMessage.lineNumber() + " of " + paramConsoleMessage.sourceId());
      return true;
    }
    
    public boolean onCreateWindow(WebView paramWebView, boolean paramBoolean1, boolean paramBoolean2, Message paramMessage)
    {
      WebView localWebView = new WebView(paramWebView.getContext());
      localWebView.setWebChromeClient(this);
      localWebView.setWebViewClient(new WebViewController.FrameBustWebViewClient(WebViewController.this, null));
      ((WebView.WebViewTransport)paramMessage.obj).setWebView(localWebView);
      paramMessage.sendToTarget();
      Logger.i("onCreateWindow", "onCreateWindow");
      return true;
    }
    
    public void onHideCustomView()
    {
      Logger.i("Test", "onHideCustomView");
      if (WebViewController.this.mCustomView == null) {
        return;
      }
      WebViewController.this.mCustomView.setVisibility(8);
      WebViewController.this.mCustomViewContainer.removeView(WebViewController.this.mCustomView);
      WebViewController.this.mCustomView = null;
      WebViewController.this.mCustomViewContainer.setVisibility(8);
      WebViewController.this.mCustomViewCallback.onCustomViewHidden();
      WebViewController.this.setVisibility(0);
    }
    
    public void onShowCustomView(View paramView, WebChromeClient.CustomViewCallback paramCustomViewCallback)
    {
      Logger.i("Test", "onShowCustomView");
      WebViewController.this.setVisibility(8);
      if (WebViewController.this.mCustomView != null)
      {
        Logger.i("Test", "mCustomView != null");
        paramCustomViewCallback.onCustomViewHidden();
        return;
      }
      Logger.i("Test", "mCustomView == null");
      WebViewController.this.mCustomViewContainer.addView(paramView);
      WebViewController.this.mCustomView = paramView;
      WebViewController.this.mCustomViewCallback = paramCustomViewCallback;
      WebViewController.this.mCustomViewContainer.setVisibility(0);
    }
  }
  
  private class FrameBustWebViewClient
    extends WebViewClient
  {
    private FrameBustWebViewClient() {}
    
    public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
    {
      if (WebViewController.this.mActivity != null)
      {
        Intent localIntent = new Intent(WebViewController.this.mActivity, OpenUrlActivity.class);
        localIntent.putExtra(WebViewController.EXTERNAL_URL, paramString);
        localIntent.putExtra(WebViewController.SECONDARY_WEB_VIEW, false);
        WebViewController.this.mActivity.startActivity(localIntent);
      }
      return true;
    }
  }
  
  public class JSInterface
  {
    volatile int udiaResults = 0;
    
    public JSInterface(Context paramContext) {}
    
    private void injectGetUDIA(String paramString, JSONArray paramJSONArray)
    {
      if (!TextUtils.isEmpty(paramString))
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("SSA_CORE.SDKController.runFunction(").append("'").append(paramString).append("?").append("parameters").append("=").append(paramJSONArray.toString()).append("'").append(",").append("'").append("onGetUDIASuccess").append("'").append(",").append("'").append("onGetUDIAFail").append("'").append(")").append(";");
        WebViewController.this.injectJavascript(localStringBuilder.toString());
      }
    }
    
    private void sendResults(String paramString, JSONArray paramJSONArray)
    {
      Logger.i(WebViewController.this.TAG, "sendResults: " + this.udiaResults);
      if (this.udiaResults <= 0) {
        injectGetUDIA(paramString, paramJSONArray);
      }
    }
    
    @JavascriptInterface
    public void adCredited(final String paramString)
    {
      Logger.i(WebViewController.this.TAG, "adCredited(" + paramString + ")");
      SSAObj localSSAObj = new SSAObj(paramString);
      String str1 = localSSAObj.getString("credits");
      final int i;
      String str2;
      final int j;
      label77:
      final String str3;
      final String str4;
      if (str1 != null)
      {
        i = Integer.parseInt(str1);
        str2 = localSSAObj.getString("total");
        if (str2 == null) {
          break label185;
        }
        j = Integer.parseInt(str2);
        str3 = localSSAObj.getString("productType");
        if (!localSSAObj.getBoolean("externalPoll")) {
          break label191;
        }
        str4 = WebViewController.this.mOWCreditsAppKey;
      }
      String str6;
      boolean bool3;
      for (final String str5 = WebViewController.this.mOWCreditsUserId;; str5 = WebViewController.this.mOWUserId)
      {
        boolean bool1 = str3.equalsIgnoreCase(SSAEnums.ProductType.OfferWall.toString());
        str6 = null;
        bool2 = false;
        bool3 = false;
        if (!bool1) {
          break label271;
        }
        if ((!localSSAObj.isNull("signature")) && (!localSSAObj.isNull("timestamp")) && (!localSSAObj.isNull("totalCreditsFlag"))) {
          break label212;
        }
        WebViewController.this.responseBack(paramString, false, "One of the keys are missung: signature/timestamp/totalCreditsFlag", null);
        return;
        i = 0;
        break;
        label185:
        j = 0;
        break label77;
        label191:
        str4 = WebViewController.this.mOWAppKey;
      }
      label212:
      if (localSSAObj.getString("signature").equalsIgnoreCase(SDKUtils.getMD5(str2 + str4 + str5))) {}
      for (boolean bool2 = true;; bool2 = false)
      {
        bool3 = localSSAObj.getBoolean("totalCreditsFlag");
        str6 = localSSAObj.getString("timestamp");
        label271:
        if (!WebViewController.this.shouldNotifyDeveloper(str3)) {
          break;
        }
        Context localContext = WebViewController.this.getBaseContext();
        if (!(localContext instanceof Activity)) {
          break;
        }
        final boolean bool4 = bool3;
        final String str7 = str6;
        final boolean bool5 = bool2;
        ((Activity)localContext).runOnUiThread(new Runnable()
        {
          public void run()
          {
            if (str3.equalsIgnoreCase(SSAEnums.ProductType.BrandConnect.toString())) {
              WebViewController.this.mOnRewardedVideoListener.onRVAdCredited(i);
            }
            while ((!str3.equalsIgnoreCase(SSAEnums.ProductType.OfferWall.toString())) || (!bool5) || (!WebViewController.this.mOnOfferWallListener.onOWAdCredited(i, j, bool4)) || (TextUtils.isEmpty(str7))) {
              return;
            }
            if (WebViewController.this.cacheManager.setLatestCompeltionsTime(str7, str4, str5))
            {
              WebViewController.this.responseBack(paramString, true, null, null);
              return;
            }
            WebViewController.this.responseBack(paramString, false, "Time Stamp could not be stored", null);
          }
        });
        return;
        WebViewController.this.responseBack(paramString, false, "Controller signature is not equal to SDK signature", null);
      }
    }
    
    @JavascriptInterface
    public void adUnitsReady(String paramString)
    {
      Logger.i(WebViewController.this.TAG, "adUnitsReady(" + paramString + ")");
      final AdUnitsReady localAdUnitsReady = new AdUnitsReady(paramString);
      if (!localAdUnitsReady.isNumOfAdUnitsExist()) {
        WebViewController.this.responseBack(paramString, false, "Num Of Ad Units Do Not Exist", null);
      }
      final String str;
      Context localContext;
      do
      {
        do
        {
          return;
          WebViewController.this.responseBack(paramString, true, null, null);
          str = localAdUnitsReady.getProductType();
        } while (!WebViewController.this.shouldNotifyDeveloper(str));
        localContext = WebViewController.this.getBaseContext();
      } while (!(localContext instanceof Activity));
      ((Activity)localContext).runOnUiThread(new Runnable()
      {
        public void run()
        {
          int i;
          if (Integer.parseInt(localAdUnitsReady.getNumOfAdUnits()) > 0)
          {
            i = 1;
            if (!str.equalsIgnoreCase(SSAEnums.ProductType.BrandConnect.toString())) {
              break label76;
            }
            if (i == 0) {
              break label60;
            }
            WebViewController.this.mOnRewardedVideoListener.onRVInitSuccess(localAdUnitsReady);
          }
          label60:
          label76:
          while (!str.equalsIgnoreCase(SSAEnums.ProductType.Interstitial.toString()))
          {
            return;
            i = 0;
            break;
            WebViewController.this.mOnRewardedVideoListener.onRVNoMoreOffers();
            return;
          }
          if (i != 0)
          {
            WebViewController.this.mOnInitInterstitialListener.onISInitSuccess();
            return;
          }
          WebViewController.this.mOnInitInterstitialListener.onISInitFail("");
        }
      });
    }
    
    @JavascriptInterface
    public void alert(String paramString) {}
    
    @JavascriptInterface
    public void createCalendarEvent(String paramString)
    {
      Logger.i(WebViewController.this.TAG, "createCalendarEvent(" + paramString + ")");
      try
      {
        JSONObject localJSONObject1 = new JSONObject();
        JSONObject localJSONObject2 = new JSONObject();
        localJSONObject2.put("frequency", "weekly");
        localJSONObject1.put("id", "testevent723GDf84");
        localJSONObject1.put("description", "Watch this crazy show on cannel 5!");
        localJSONObject1.put("start", "2014-02-01T20:00:00-8:00");
        localJSONObject1.put("end", "2014-06-30T20:00:00-8:00");
        localJSONObject1.put("status", "pending");
        localJSONObject1.put("recurrence", localJSONObject2.toString());
        localJSONObject1.put("reminder", "2014-02-01T19:50:00-8:00");
        return;
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
      }
    }
    
    @JavascriptInterface
    public void deleteFile(String paramString)
    {
      Logger.i(WebViewController.this.TAG, "deleteFile(" + paramString + ")");
      SSAFile localSSAFile = new SSAFile(paramString);
      if (!WebViewController.this.cacheManager.isPathExist(localSSAFile.getPath()))
      {
        WebViewController.this.responseBack(paramString, false, "File not exist", "1");
        return;
      }
      boolean bool = WebViewController.this.cacheManager.deleteFile(localSSAFile.getPath(), localSSAFile.getFile());
      WebViewController.this.responseBack(paramString, bool, null, null);
    }
    
    @JavascriptInterface
    public void deleteFolder(String paramString)
    {
      Logger.i(WebViewController.this.TAG, "deleteFolder(" + paramString + ")");
      SSAFile localSSAFile = new SSAFile(paramString);
      if (!WebViewController.this.cacheManager.isPathExist(localSSAFile.getPath()))
      {
        WebViewController.this.responseBack(paramString, false, "Folder not exist", "1");
        return;
      }
      boolean bool = WebViewController.this.cacheManager.deleteFolder(localSSAFile.getPath());
      WebViewController.this.responseBack(paramString, bool, null, null);
    }
    
    @JavascriptInterface
    public void displayWebView(String paramString)
    {
      Logger.i(WebViewController.this.TAG, "displayWebView(" + paramString + ")");
      WebViewController.this.responseBack(paramString, true, null, null);
      SSAObj localSSAObj = new SSAObj(paramString);
      boolean bool = ((Boolean)localSSAObj.get("display")).booleanValue();
      String str1 = localSSAObj.getString("productType");
      if (bool)
      {
        if (WebViewController.this.getState() != WebViewController.State.Display)
        {
          WebViewController.this.setState(WebViewController.State.Display);
          Logger.i(WebViewController.this.TAG, "State: " + WebViewController.this.mState);
          Context localContext = WebViewController.this.getBaseContext();
          String str2 = WebViewController.this.getOrientationState();
          int i = SDKUtils.getApplicationRotation(localContext);
          if (str1.equalsIgnoreCase(SSAEnums.ProductType.Interstitial.toString())) {}
          for (Intent localIntent = new Intent(localContext, InterstitialActivity.class);; localIntent = new Intent(localContext, ControllerActivity.class))
          {
            localIntent.putExtra("orientation_set_flag", str2);
            localIntent.putExtra("rotation_set_flag", i);
            localContext.startActivity(localIntent);
            return;
          }
        }
        Logger.i(WebViewController.this.TAG, "State: " + WebViewController.this.mState);
        return;
      }
      WebViewController.this.setState(WebViewController.State.Gone);
      WebViewController.this.closeWebView();
    }
    
    @JavascriptInterface
    public void getApplicationInfo(String paramString)
    {
      Logger.i(WebViewController.this.TAG, "getApplicationInfo(" + paramString + ")");
      String str1 = WebViewController.this.extractSuccessFunctionToCall(paramString);
      String str2 = WebViewController.this.extractFailFunctionToCall(paramString);
      String str3 = new SSAObj(paramString).getString("productType");
      new Object[2];
      Object[] arrayOfObject = WebViewController.this.getApplicationParams(str3);
      String str4 = (String)arrayOfObject[0];
      String str5;
      if (((Boolean)arrayOfObject[1]).booleanValue())
      {
        boolean bool2 = TextUtils.isEmpty(str2);
        str5 = null;
        if (!bool2) {
          str5 = str2;
        }
      }
      for (;;)
      {
        if (!TextUtils.isEmpty(str5))
        {
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("SSA_CORE.SDKController.runFunction(").append("'").append(str5).append("?").append("parameters").append("=").append(str4).append("'").append(",").append("'").append("onGetApplicationInfoSuccess").append("'").append(",").append("'").append("onGetApplicationInfoFail").append("'").append(")").append(";");
          WebViewController.this.injectJavascript(localStringBuilder.toString());
        }
        return;
        boolean bool1 = TextUtils.isEmpty(str1);
        str5 = null;
        if (!bool1) {
          str5 = str1;
        }
      }
    }
    
    @JavascriptInterface
    public void getCachedFilesMap(String paramString)
    {
      Logger.i(WebViewController.this.TAG, "getCachedFilesMap(" + paramString + ")");
      String str1 = WebViewController.this.extractSuccessFunctionToCall(paramString);
      SSAObj localSSAObj;
      if (!TextUtils.isEmpty(str1))
      {
        localSSAObj = new SSAObj(paramString);
        if (!localSSAObj.containsKey("path")) {
          WebViewController.this.responseBack(paramString, false, "path key does not exist", null);
        }
      }
      else
      {
        return;
      }
      String str2 = (String)localSSAObj.get("path");
      if (!WebViewController.this.cacheManager.isPathExist(str2))
      {
        WebViewController.this.responseBack(paramString, false, "path file does not exist on disk", null);
        return;
      }
      String str3 = WebViewController.this.cacheManager.getCachedFilesMap(str2);
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("SSA_CORE.SDKController.runFunction(").append("'").append(str1).append("?").append("parameters").append("=").append(str3).append("'").append(",").append("'").append("onGetCachedFilesMapSuccess").append("'").append(",").append("'").append("onGetCachedFilesMapFail").append("'").append(")").append(";");
      WebViewController.this.injectJavascript(localStringBuilder.toString());
    }
    
    @JavascriptInterface
    public void getDeviceStatus(String paramString)
    {
      Logger.i(WebViewController.this.TAG, "getDeviceStatus(" + paramString + ")");
      String str1 = WebViewController.this.extractSuccessFunctionToCall(paramString);
      String str2 = WebViewController.this.extractFailFunctionToCall(paramString);
      new Object[2];
      Object[] arrayOfObject = WebViewController.this.getDeviceParams(WebViewController.access$31(WebViewController.this));
      String str3 = (String)arrayOfObject[0];
      String str4;
      if (((Boolean)arrayOfObject[1]).booleanValue())
      {
        boolean bool2 = TextUtils.isEmpty(str2);
        str4 = null;
        if (!bool2) {
          str4 = str2;
        }
      }
      for (;;)
      {
        if (!TextUtils.isEmpty(str4))
        {
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("SSA_CORE.SDKController.runFunction(").append("'").append(str4).append("?").append("parameters").append("=").append(str3).append("'").append(",").append("'").append("onGetDeviceStatusSuccess").append("'").append(",").append("'").append("onGetDeviceStatusFail").append("'").append(")").append(";");
          WebViewController.this.injectJavascript(localStringBuilder.toString());
        }
        return;
        boolean bool1 = TextUtils.isEmpty(str1);
        str4 = null;
        if (!bool1) {
          str4 = str1;
        }
      }
    }
    
    @JavascriptInterface
    public void getOrientation(String paramString)
    {
      String str1 = WebViewController.this.extractSuccessFunctionToCall(paramString);
      WebViewController.this.extractFailFunctionToCall(paramString);
      String str2 = SDKUtils.getOrientation(WebViewController.this.getBaseContext()).toString();
      if (!TextUtils.isEmpty(str1))
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("SSA_CORE.SDKController.runFunction(").append("'").append(str1).append("?").append("parameters").append("=").append(str2).append("'").append(",").append("'").append("onGetOrientationSuccess").append("'").append(",").append("'").append("onGetOrientationFail").append("'").append(")").append(";");
        WebViewController.this.injectJavascript(localStringBuilder.toString());
      }
    }
    
    @JavascriptInterface
    public void getUDIA(String paramString)
    {
      this.udiaResults = 0;
      Logger.i(WebViewController.this.TAG, "getUDIA(" + paramString + ")");
      final String str1 = WebViewController.this.extractSuccessFunctionToCall(paramString);
      SSAObj localSSAObj = new SSAObj(paramString);
      if (!localSSAObj.containsKey("getByFlag")) {
        WebViewController.this.responseBack(paramString, false, "getByFlag key does not exist", null);
      }
      for (;;)
      {
        return;
        int i = Integer.parseInt(localSSAObj.getString("getByFlag"));
        if (i == 0) {
          continue;
        }
        String str2 = Integer.toBinaryString(i);
        if (TextUtils.isEmpty(str2))
        {
          WebViewController.this.responseBack(paramString, false, "fialed to convert getByFlag", null);
          return;
        }
        char[] arrayOfChar = new StringBuilder(str2).reverse().toString().toCharArray();
        final JSONArray localJSONArray = new JSONArray();
        JSONObject localJSONObject;
        if (arrayOfChar[3] == '0') {
          localJSONObject = new JSONObject();
        }
        try
        {
          localJSONObject.put("sessions", WebViewController.this.cacheManager.getSessions());
          WebViewController.this.cacheManager.deleteSessions();
          localJSONArray.put(localJSONObject);
          label209:
          if (arrayOfChar[1] == '1')
          {
            this.udiaResults = (1 + this.udiaResults);
            WebViewController.this.cacheManager.setAppsInstall();
            WebViewController.this.cacheManager.setOnAppsInstallListener(new CacheManager.OnAppsInstall()
            {
              public void onAppsInstallFinish(JSONArray paramAnonymousJSONArray)
              {
                WebViewController.JSInterface localJSInterface = WebViewController.JSInterface.this;
                localJSInterface.udiaResults = (-1 + localJSInterface.udiaResults);
                try
                {
                  JSONObject localJSONObject = new JSONObject();
                  localJSONObject.put("apps", paramAnonymousJSONArray);
                  localJSONArray.put(localJSONObject);
                  WebViewController.JSInterface.this.sendResults(str1, localJSONArray);
                  Logger.i(WebViewController.this.TAG, "done apps install");
                  return;
                }
                catch (JSONException localJSONException) {}
              }
            });
          }
          if (arrayOfChar[2] == '1')
          {
            this.udiaResults = (1 + this.udiaResults);
            WebViewController.this.locationHelper.getLocation(new LocationHelper.OnGetLocationListener()
            {
              public void onGetLocationFail(String paramAnonymousString)
              {
                WebViewController.JSInterface localJSInterface = WebViewController.JSInterface.this;
                localJSInterface.udiaResults = (-1 + localJSInterface.udiaResults);
              }
              
              public void onGetLocationSuccess(double paramAnonymousDouble1, double paramAnonymousDouble2)
              {
                WebViewController.JSInterface localJSInterface = WebViewController.JSInterface.this;
                localJSInterface.udiaResults = (-1 + localJSInterface.udiaResults);
                try
                {
                  JSONObject localJSONObject = new JSONObject();
                  localJSONObject.put("latitude", paramAnonymousDouble1);
                  localJSONObject.put("longitude", paramAnonymousDouble2);
                  localJSONArray.put(localJSONObject);
                  WebViewController.JSInterface.this.sendResults(str1, localJSONArray);
                  Logger.i(WebViewController.this.TAG, "done location");
                  return;
                }
                catch (JSONException localJSONException) {}
              }
            });
          }
          if ((arrayOfChar[0] != '1') || (!InAppPurchaseHelper.isInAppBillingServiceExist())) {
            continue;
          }
          this.udiaResults = (1 + this.udiaResults);
          WebViewController.this.inAppPurchaseHelper.bindToIInAppBillingService(WebViewController.this.getBaseContext(), new InAppPurchaseHelper.OnPurchasedItemsListener()
          {
            public void onPurchasedItemsFail()
            {
              WebViewController.JSInterface localJSInterface = WebViewController.JSInterface.this;
              localJSInterface.udiaResults = (-1 + localJSInterface.udiaResults);
            }
            
            public void onPurchasedItemsSuccess(String paramAnonymousString)
            {
              WebViewController.JSInterface localJSInterface = WebViewController.JSInterface.this;
              localJSInterface.udiaResults = (-1 + localJSInterface.udiaResults);
              try
              {
                JSONObject localJSONObject = new JSONObject();
                localJSONObject.put("inAppPurchasedItems", paramAnonymousString);
                localJSONArray.put(localJSONObject);
                WebViewController.JSInterface.this.sendResults(str1, localJSONArray);
                Logger.i(WebViewController.this.TAG, "done purchased items");
                return;
              }
              catch (JSONException localJSONException) {}
            }
          });
          return;
        }
        catch (JSONException localJSONException)
        {
          break label209;
        }
      }
    }
    
    @JavascriptInterface
    public void getUserData(String paramString)
    {
      Logger.i(WebViewController.this.TAG, "getUserData(" + paramString + ")");
      SSAObj localSSAObj = new SSAObj(paramString);
      if (!localSSAObj.containsKey("key"))
      {
        WebViewController.this.responseBack(paramString, false, "key does not exist", null);
        return;
      }
      String str1 = WebViewController.this.extractSuccessFunctionToCall(paramString);
      String str2 = localSSAObj.getString("key");
      String str3 = WebViewController.this.cacheManager.getUserData(str2);
      String str4 = WebViewController.this.parseToJson(str2, str3, null, null, null, null, null, null, null, false);
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("SSA_CORE.SDKController.runFunction(").append("'").append(str1).append("?").append("parameters").append("=").append(str4).append("'").append(");");
      WebViewController.this.injectJavascript(localStringBuilder.toString());
    }
    
    @JavascriptInterface
    public void getUserUniqueId(String paramString)
    {
      Logger.i(WebViewController.this.TAG, "getUserUniqueId(" + paramString + ")");
      SSAObj localSSAObj = new SSAObj(paramString);
      if (!localSSAObj.containsKey("productType")) {
        WebViewController.this.responseBack(paramString, false, "productType does not exist", null);
      }
      String str1;
      do
      {
        return;
        str1 = WebViewController.this.extractSuccessFunctionToCall(paramString);
      } while (TextUtils.isEmpty(str1));
      String str2 = localSSAObj.getString("productType");
      String str3 = WebViewController.this.cacheManager.getUniqueId(str2);
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("SSA_CORE.SDKController.runFunction(").append("'").append(str1).append("?").append("parameters").append("=").append(WebViewController.this.parseToJson("userUniqueId", str3, "productType", str2, null, null, null, null, null, false)).append("'").append(",").append("'").append("onGetUserUniqueIdSuccess").append("'").append(",").append("'").append("onGetUserUniqueIdFail").append("'").append(")").append(";");
      WebViewController.this.injectJavascript(localStringBuilder.toString());
    }
    
    @JavascriptInterface
    public String getVersion(String paramString)
    {
      Logger.i(WebViewController.this.TAG, "getVersion(" + paramString + ")");
      return "1.0";
    }
    
    @JavascriptInterface
    public void initController(String paramString)
    {
      Logger.i(WebViewController.this.TAG, "initController(" + paramString + ")");
      SSAObj localSSAObj = new SSAObj(paramString);
      String str;
      if (localSSAObj.containsKey("stage"))
      {
        str = localSSAObj.getString("stage");
        if (!"ready".equalsIgnoreCase(str)) {
          break label273;
        }
        WebViewController.this.mControllerState = SSAEnums.ControllerState.Ready;
        WebViewController.this.mGlobalControllerTimer.cancel();
        WebViewController.this.mLoadControllerTimer.cancel();
        if (WebViewController.this.mRVmiss) {
          WebViewController.this.initBrandConnect(WebViewController.this.mBCAppKey, WebViewController.this.mBCUserId, WebViewController.this.mExtraParameters, WebViewController.this.mOnRewardedVideoListener);
        }
        if (WebViewController.this.mISmiss) {
          WebViewController.this.initInterstitial(WebViewController.this.mISAppKey, WebViewController.this.mISUserId, WebViewController.this.mExtraParameters, WebViewController.this.mOnInitInterstitialListener);
        }
        if (WebViewController.this.mOWmiss) {
          WebViewController.this.showOfferWall(WebViewController.this.mOWAppKey, WebViewController.this.mOWUserId, WebViewController.this.mExtraParameters, WebViewController.this.mOnOfferWallListener);
        }
        if (WebViewController.this.mOWCreditsMiss) {
          WebViewController.this.getOfferWallCredits(WebViewController.this.mOWCreditsAppKey, WebViewController.this.mOWCreditsUserId, WebViewController.this.mOnOfferWallListener);
        }
      }
      label273:
      do
      {
        return;
        if ("loaded".equalsIgnoreCase(str))
        {
          WebViewController.this.mControllerState = SSAEnums.ControllerState.Loaded;
          return;
        }
        if (!"failed".equalsIgnoreCase(str)) {
          break;
        }
        WebViewController.this.mControllerState = SSAEnums.ControllerState.Failed;
        if (WebViewController.this.mRVmiss) {
          WebViewController.this.sendProductErrorMessage(SSAEnums.ProductType.BrandConnect);
        }
        if (WebViewController.this.mISmiss) {
          WebViewController.this.sendProductErrorMessage(SSAEnums.ProductType.Interstitial);
        }
        if (WebViewController.this.mOWmiss) {
          WebViewController.this.sendProductErrorMessage(SSAEnums.ProductType.OfferWall);
        }
      } while (!WebViewController.this.mOWCreditsMiss);
      WebViewController.this.sendProductErrorMessage(SSAEnums.ProductType.OfferWallCredits);
      return;
      Logger.i(WebViewController.this.TAG, "No STAGE mentioned! Should not get here!");
    }
    
    @JavascriptInterface
    public void onAdWindowsClosed(String paramString)
    {
      Logger.i(WebViewController.this.TAG, "onAdWindowsClosed(" + paramString + ")");
      final String str = new SSAObj(paramString).getString("productType");
      if ((WebViewController.this.shouldNotifyDeveloper(str)) && (str != null))
      {
        Context localContext = WebViewController.this.getBaseContext();
        if ((localContext instanceof Activity)) {
          ((Activity)localContext).runOnUiThread(new Runnable()
          {
            public void run()
            {
              if (str.equalsIgnoreCase(SSAEnums.ProductType.BrandConnect.toString())) {
                WebViewController.this.mOnRewardedVideoListener.onRVAdClosed();
              }
              do
              {
                return;
                if (str.equalsIgnoreCase(SSAEnums.ProductType.Interstitial.toString()))
                {
                  WebViewController.this.mOnInitInterstitialListener.onISAdClosed();
                  return;
                }
              } while (!str.equalsIgnoreCase(SSAEnums.ProductType.OfferWall.toString()));
              WebViewController.this.mOnOfferWallListener.onOWAdClosed();
            }
          });
        }
      }
    }
    
    @JavascriptInterface
    public void onGenericFunctionFail(String paramString)
    {
      Logger.i(WebViewController.this.TAG, "onGenericFunctionFail(" + paramString + ")");
      if (WebViewController.this.mOnGenericFunctionListener == null)
      {
        Logger.d(WebViewController.this.TAG, "genericFunctionListener was not found");
        return;
      }
      final String str = new SSAObj(paramString).getString("errMsg");
      Context localContext = WebViewController.this.getBaseContext();
      if ((localContext instanceof Activity)) {
        ((Activity)localContext).runOnUiThread(new Runnable()
        {
          public void run()
          {
            WebViewController.this.mOnGenericFunctionListener.onGFFail(str);
          }
        });
      }
      WebViewController.this.responseBack(paramString, true, null, null);
      WebViewController.this.toastingErrMsg("onGenericFunctionFail", paramString);
    }
    
    @JavascriptInterface
    public void onGenericFunctionSuccess(String paramString)
    {
      Logger.i(WebViewController.this.TAG, "onGenericFunctionSuccess(" + paramString + ")");
      if (WebViewController.this.mOnGenericFunctionListener == null)
      {
        Logger.d(WebViewController.this.TAG, "genericFunctionListener was not found");
        return;
      }
      Context localContext = WebViewController.this.getBaseContext();
      if ((localContext instanceof Activity)) {
        ((Activity)localContext).runOnUiThread(new Runnable()
        {
          public void run()
          {
            WebViewController.this.mOnGenericFunctionListener.onGFSuccess();
          }
        });
      }
      WebViewController.this.responseBack(paramString, true, null, null);
    }
    
    @JavascriptInterface
    public void onGetApplicationInfoFail(String paramString)
    {
      Logger.i(WebViewController.this.TAG, "onGetApplicationInfoFail(" + paramString + ")");
      WebViewController.this.responseBack(paramString, true, null, null);
      WebViewController.this.toastingErrMsg("onGetApplicationInfoFail", paramString);
    }
    
    @JavascriptInterface
    public void onGetApplicationInfoSuccess(String paramString)
    {
      Logger.i(WebViewController.this.TAG, "onGetApplicationInfoSuccess(" + paramString + ")");
      WebViewController.this.responseBack(paramString, true, null, null);
      WebViewController.this.toastingErrMsg("onGetApplicationInfoSuccess", paramString);
    }
    
    @JavascriptInterface
    public void onGetCachedFilesMapFail(String paramString)
    {
      Logger.i(WebViewController.this.TAG, "onGetCachedFilesMapFail(" + paramString + ")");
      WebViewController.this.responseBack(paramString, true, null, null);
      WebViewController.this.toastingErrMsg("onGetCachedFilesMapFail", paramString);
    }
    
    @JavascriptInterface
    public void onGetCachedFilesMapSuccess(String paramString)
    {
      Logger.i(WebViewController.this.TAG, "onGetCachedFilesMapSuccess(" + paramString + ")");
      WebViewController.this.responseBack(paramString, true, null, null);
      WebViewController.this.toastingErrMsg("onGetCachedFilesMapSuccess", paramString);
    }
    
    @JavascriptInterface
    public void onGetDeviceStatusFail(String paramString)
    {
      Logger.i(WebViewController.this.TAG, "onGetDeviceStatusFail(" + paramString + ")");
      WebViewController.this.responseBack(paramString, true, null, null);
      WebViewController.this.toastingErrMsg("onGetDeviceStatusFail", paramString);
    }
    
    @JavascriptInterface
    public void onGetDeviceStatusSuccess(String paramString)
    {
      Logger.i(WebViewController.this.TAG, "onGetDeviceStatusSuccess(" + paramString + ")");
      WebViewController.this.responseBack(paramString, true, null, null);
      WebViewController.this.toastingErrMsg("onGetDeviceStatusSuccess", paramString);
    }
    
    @JavascriptInterface
    public void onGetUDIAFail(String paramString)
    {
      Logger.i(WebViewController.this.TAG, "onGetUDIAFail(" + paramString + ")");
    }
    
    @JavascriptInterface
    public void onGetUDIASuccess(String paramString)
    {
      Logger.i(WebViewController.this.TAG, "onGetUDIASuccess(" + paramString + ")");
    }
    
    @JavascriptInterface
    public void onGetUserCreditsFail(String paramString)
    {
      Logger.i(WebViewController.this.TAG, "onGetUserCreditsFail(" + paramString + ")");
      final String str = new SSAObj(paramString).getString("errMsg");
      if (WebViewController.this.shouldNotifyDeveloper(SSAEnums.ProductType.OfferWall.toString()))
      {
        Context localContext = WebViewController.this.getBaseContext();
        if ((localContext instanceof Activity)) {
          ((Activity)localContext).runOnUiThread(new Runnable()
          {
            public void run()
            {
              WebViewController.this.mOnOfferWallListener.onGetOWCreditsFailed(str);
            }
          });
        }
      }
      WebViewController.this.responseBack(paramString, true, null, null);
      WebViewController.this.toastingErrMsg("onGetUserCreditsFail", paramString);
    }
    
    @JavascriptInterface
    public void onGetUserUniqueIdFail(String paramString)
    {
      Logger.i(WebViewController.this.TAG, "onGetUserUniqueIdFail(" + paramString + ")");
    }
    
    @JavascriptInterface
    public void onGetUserUniqueIdSuccess(String paramString)
    {
      Logger.i(WebViewController.this.TAG, "onGetUserUniqueIdSuccess(" + paramString + ")");
    }
    
    @JavascriptInterface
    public void onInitBrandConnectFail(String paramString)
    {
      Logger.i(WebViewController.this.TAG, "onInitBrandConnectFail(" + paramString + ")");
      final String str = new SSAObj(paramString).getString("errMsg");
      if (WebViewController.this.shouldNotifyDeveloper(SSAEnums.ProductType.BrandConnect.toString()))
      {
        Context localContext = WebViewController.this.getBaseContext();
        if ((localContext instanceof Activity)) {
          ((Activity)localContext).runOnUiThread(new Runnable()
          {
            public void run()
            {
              WebViewController.this.mOnRewardedVideoListener.onRVInitFail(str);
            }
          });
        }
      }
      WebViewController.this.responseBack(paramString, true, null, null);
      WebViewController.this.toastingErrMsg("onInitBrandConnectFail", paramString);
    }
    
    @JavascriptInterface
    public void onInitBrandConnectSuccess(String paramString)
    {
      Logger.i(WebViewController.this.TAG, "onInitBrandConnectSuccess(" + paramString + ")");
      SSABCParameters localSSABCParameters = new SSABCParameters(paramString);
      WebViewController.this.cacheManager.setSSABCParameters(localSSABCParameters);
      WebViewController.this.responseBack(paramString, true, null, null);
      WebViewController.this.toastingErrMsg("onInitBrandConnectSuccess", paramString);
    }
    
    @JavascriptInterface
    public void onInitInterstitialFail(String paramString)
    {
      Logger.i(WebViewController.this.TAG, "onInitInterstitialFail(" + paramString + ")");
      final String str = new SSAObj(paramString).getString("errMsg");
      if (WebViewController.this.shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString()))
      {
        Context localContext = WebViewController.this.getBaseContext();
        if ((localContext instanceof Activity)) {
          ((Activity)localContext).runOnUiThread(new Runnable()
          {
            public void run()
            {
              WebViewController.this.mOnInitInterstitialListener.onISInitFail(str);
            }
          });
        }
      }
      WebViewController.this.responseBack(paramString, true, null, null);
      WebViewController.this.toastingErrMsg("onInitInterstitialFail", paramString);
    }
    
    @JavascriptInterface
    public void onInitInterstitialSuccess(String paramString)
    {
      Logger.i(WebViewController.this.TAG, "onInitInterstitialSuccess(" + paramString + ")");
      SSABCParameters localSSABCParameters = new SSABCParameters(paramString);
      WebViewController.this.cacheManager.setSSABCParameters(localSSABCParameters);
      WebViewController.this.responseBack(paramString, true, null, null);
      WebViewController.this.toastingErrMsg("onInitInterstitialSuccess", paramString);
    }
    
    @JavascriptInterface
    public void onInterstitialGeneric(String paramString)
    {
      Logger.i(WebViewController.this.TAG, "onInterstitialGeneric(" + paramString + ")");
      if (WebViewController.this.shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString())) {
        WebViewController.this.mOnInitInterstitialListener.onISGeneric("", "");
      }
    }
    
    @JavascriptInterface
    public void onLoadInterstitialFail(String paramString)
    {
      Logger.i(WebViewController.this.TAG, "onLoadInterstitialFail(" + paramString + ")");
      final String str = new SSAObj(paramString).getString("errMsg");
      if (WebViewController.this.shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString()))
      {
        Context localContext = WebViewController.this.getBaseContext();
        if ((localContext instanceof Activity)) {
          ((Activity)localContext).runOnUiThread(new Runnable()
          {
            public void run()
            {
              WebViewController.this.mOnInitInterstitialListener.onISLoadedFail(str);
            }
          });
        }
      }
      WebViewController.this.responseBack(paramString, true, null, null);
      WebViewController.this.toastingErrMsg("onLoadInterstitialFail", paramString);
    }
    
    @JavascriptInterface
    public void onLoadInterstitialSuccess(String paramString)
    {
      Logger.i(WebViewController.this.TAG, "onLoadInterstitialSuccess(" + paramString + ")");
      if (WebViewController.this.shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString()))
      {
        Context localContext = WebViewController.this.getBaseContext();
        if ((localContext instanceof Activity)) {
          ((Activity)localContext).runOnUiThread(new Runnable()
          {
            public void run()
            {
              WebViewController.this.mOnInitInterstitialListener.onISLoaded();
            }
          });
        }
      }
      WebViewController.this.responseBack(paramString, true, null, null);
      WebViewController.this.toastingErrMsg("onLoadInterstitialSuccess", paramString);
    }
    
    @JavascriptInterface
    public void onOfferWallGeneric(String paramString)
    {
      Logger.i(WebViewController.this.TAG, "onOfferWallGeneric(" + paramString + ")");
      if (WebViewController.this.shouldNotifyDeveloper(SSAEnums.ProductType.OfferWall.toString())) {
        WebViewController.this.mOnOfferWallListener.onOWGeneric("", "");
      }
    }
    
    @JavascriptInterface
    public void onRewardedVideoGeneric(String paramString)
    {
      Logger.i(WebViewController.this.TAG, "onRewardedVideoGeneric(" + paramString + ")");
      if (WebViewController.this.shouldNotifyDeveloper(SSAEnums.ProductType.BrandConnect.toString())) {
        WebViewController.this.mOnRewardedVideoListener.onRVGeneric("", "");
      }
    }
    
    @JavascriptInterface
    public void onShowBrandConnectFail(String paramString)
    {
      Logger.i(WebViewController.this.TAG, "onShowBrandConnectFail(" + paramString + ")");
      WebViewController.this.responseBack(paramString, true, null, null);
      WebViewController.this.toastingErrMsg("onShowBrandConnectFail", paramString);
    }
    
    @JavascriptInterface
    public void onShowBrandConnectSuccess(String paramString)
    {
      Logger.i(WebViewController.this.TAG, "onShowBrandConnectSuccess(" + paramString + ")");
      WebViewController.this.responseBack(paramString, true, null, null);
      WebViewController.this.toastingErrMsg("onShowBrandConnectSuccess", paramString);
    }
    
    @JavascriptInterface
    public void onShowInterstitialFail(String paramString)
    {
      Logger.i(WebViewController.this.TAG, "onShowInterstitialFail(" + paramString + ")");
      WebViewController.this.responseBack(paramString, true, null, null);
      WebViewController.this.toastingErrMsg("onShowInterstitialFail", paramString);
    }
    
    @JavascriptInterface
    public void onShowInterstitialSuccess(String paramString)
    {
      Logger.i(WebViewController.this.TAG, "onShowInterstitialSuccess(" + paramString + ")");
      WebViewController.this.responseBack(paramString, true, null, null);
      WebViewController.this.toastingErrMsg("onShowInterstitialSuccess", paramString);
    }
    
    @JavascriptInterface
    public void onShowOfferWallFail(String paramString)
    {
      Logger.i(WebViewController.this.TAG, "onShowOfferWallFail(" + paramString + ")");
      final String str = new SSAObj(paramString).getString("errMsg");
      if (WebViewController.this.shouldNotifyDeveloper(SSAEnums.ProductType.OfferWall.toString()))
      {
        Context localContext = WebViewController.this.getBaseContext();
        if ((localContext instanceof Activity)) {
          ((Activity)localContext).runOnUiThread(new Runnable()
          {
            public void run()
            {
              WebViewController.this.mOnOfferWallListener.onOWShowFail(str);
            }
          });
        }
      }
      WebViewController.this.responseBack(paramString, true, null, null);
      WebViewController.this.toastingErrMsg("onShowOfferWallFail", paramString);
    }
    
    @JavascriptInterface
    public void onShowOfferWallSuccess(String paramString)
    {
      Logger.i(WebViewController.this.TAG, "onShowOfferWallSuccess(" + paramString + ")");
      if (WebViewController.this.shouldNotifyDeveloper(SSAEnums.ProductType.OfferWall.toString()))
      {
        Context localContext = WebViewController.this.getBaseContext();
        if ((localContext instanceof Activity)) {
          ((Activity)localContext).runOnUiThread(new Runnable()
          {
            public void run()
            {
              WebViewController.this.mOnOfferWallListener.onOWShowSuccess();
            }
          });
        }
      }
      WebViewController.this.responseBack(paramString, true, null, null);
      WebViewController.this.toastingErrMsg("onShowOfferWallSuccess", paramString);
    }
    
    @JavascriptInterface
    public void onUDIAFail(String paramString)
    {
      Logger.i(WebViewController.this.TAG, "onUDIAFail(" + paramString + ")");
    }
    
    @JavascriptInterface
    public void onUDIASuccess(String paramString)
    {
      Logger.i(WebViewController.this.TAG, "onUDIASuccess(" + paramString + ")");
    }
    
    @JavascriptInterface
    public void open(String paramString)
    {
      Logger.i(WebViewController.this.TAG, "open(" + paramString + ")");
      new SSAObj(paramString).getString("url");
    }
    
    @JavascriptInterface
    public void openUrl(String paramString)
    {
      Logger.i(WebViewController.this.TAG, "openUrl(" + paramString + ")");
      SSAObj localSSAObj = new SSAObj(paramString);
      String str1 = localSSAObj.getString("url");
      String str2 = localSSAObj.getString("method");
      Context localContext = WebViewController.this.getBaseContext();
      if (str2.equalsIgnoreCase("external_browser")) {
        localContext.startActivity(new Intent("android.intent.action.VIEW").setData(Uri.parse(str1)));
      }
      do
      {
        return;
        if (str2.equalsIgnoreCase("webview"))
        {
          Intent localIntent1 = new Intent(localContext, OpenUrlActivity.class);
          localIntent1.putExtra(WebViewController.EXTERNAL_URL, str1);
          localIntent1.putExtra(WebViewController.SECONDARY_WEB_VIEW, true);
          localContext.startActivity(localIntent1);
          return;
        }
      } while (!str2.equalsIgnoreCase("store"));
      Intent localIntent2 = new Intent(localContext, OpenUrlActivity.class);
      localIntent2.putExtra(WebViewController.EXTERNAL_URL, str1);
      localIntent2.putExtra(WebViewController.IS_STORE, true);
      localIntent2.putExtra(WebViewController.SECONDARY_WEB_VIEW, true);
      localContext.startActivity(localIntent2);
    }
    
    @JavascriptInterface
    public void removeCloseEventHandler(String paramString)
    {
      Logger.i(WebViewController.this.TAG, "removeCloseEventHandler(" + paramString + ")");
      if (WebViewController.this.mCloseEventTimer != null) {
        WebViewController.this.mCloseEventTimer.cancel();
      }
      WebViewController.this.isRemoveCloseEventHandler = true;
    }
    
    @JavascriptInterface
    public void saveFile(String paramString)
    {
      Logger.i(WebViewController.this.TAG, "saveFile(" + paramString + ")");
      SSAFile localSSAFile = new SSAFile(paramString);
      if (SDKUtils.getAvailableSpaceInMB(WebViewController.this.getBaseContext()) <= 0L)
      {
        WebViewController.this.responseBack(paramString, false, "no_disk_space", null);
        return;
      }
      if (!WebViewController.this.cacheManager.isExternalStorageAvailable())
      {
        WebViewController.this.responseBack(paramString, false, "sotrage_unavailable", null);
        return;
      }
      if (WebViewController.this.cacheManager.isFileCached(localSSAFile))
      {
        WebViewController.this.responseBack(paramString, false, "file_already_exist", null);
        return;
      }
      if (!SDKUtils.isOnline(WebViewController.this.getBaseContext()))
      {
        WebViewController.this.responseBack(paramString, false, "no_network_connection", null);
        return;
      }
      WebViewController.this.responseBack(paramString, true, null, null);
      String str1 = localSSAFile.getLastUpdateTime();
      String str2;
      String str3;
      String[] arrayOfString;
      if (str1 != null)
      {
        str2 = String.valueOf(str1);
        if (!TextUtils.isEmpty(str2))
        {
          str3 = localSSAFile.getPath();
          if (!str3.contains("/")) {
            break label251;
          }
          arrayOfString = localSSAFile.getPath().split("/");
        }
      }
      label251:
      for (String str4 = arrayOfString[(-1 + arrayOfString.length)];; str4 = str3)
      {
        WebViewController.this.cacheManager.setCampaignLastUpdate(str4, str2);
        WebViewController.this.downloadManager.downloadFile(localSSAFile);
        return;
      }
    }
    
    @JavascriptInterface
    public void setBackButtonState(String paramString)
    {
      Logger.i(WebViewController.this.TAG, "setBackButtonState(" + paramString + ")");
      String str = new SSAObj(paramString).getString("state");
      WebViewController.this.cacheManager.setBackButtonState(str);
    }
    
    @JavascriptInterface
    public void setForceClose(String paramString)
    {
      Logger.i(WebViewController.this.TAG, "setForceClose(" + paramString + ")");
      SSAObj localSSAObj = new SSAObj(paramString);
      String str1 = localSSAObj.getString("width");
      String str2 = localSSAObj.getString("height");
      WebViewController.this.mHiddenForceCloseWidth = Integer.parseInt(str1);
      WebViewController.this.mHiddenForceCloseHeight = Integer.parseInt(str2);
      WebViewController.this.mHiddenForceCloseLocation = localSSAObj.getString("position");
    }
    
    @JavascriptInterface
    public void setOrientation(String paramString)
    {
      Logger.i(WebViewController.this.TAG, "setOrientation(" + paramString + ")");
      String str = new SSAObj(paramString).getString("orientation");
      WebViewController.this.setOrientationState(str);
      int i = SDKUtils.getApplicationRotation(WebViewController.this.getBaseContext());
      if (WebViewController.this.mChangeListener != null) {
        WebViewController.this.mChangeListener.onSetOrientationCalled(str, i);
      }
    }
    
    @JavascriptInterface
    public void setStoreSearchKeys(String paramString)
    {
      Logger.i(WebViewController.this.TAG, "setStoreSearchKeys(" + paramString + ")");
      WebViewController.this.cacheManager.setSearchKeys(paramString);
    }
    
    @JavascriptInterface
    public void setUserData(String paramString)
    {
      Logger.i(WebViewController.this.TAG, "setUserData(" + paramString + ")");
      SSAObj localSSAObj = new SSAObj(paramString);
      if (!localSSAObj.containsKey("key"))
      {
        WebViewController.this.responseBack(paramString, false, "key does not exist", null);
        return;
      }
      if (!localSSAObj.containsKey("value"))
      {
        WebViewController.this.responseBack(paramString, false, "value does not exist", null);
        return;
      }
      String str1 = localSSAObj.getString("key");
      String str2 = localSSAObj.getString("value");
      if (WebViewController.this.cacheManager.setUserData(str1, str2))
      {
        String str3 = WebViewController.this.extractSuccessFunctionToCall(paramString);
        String str4 = WebViewController.this.parseToJson(str1, str2, null, null, null, null, null, null, null, false);
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("SSA_CORE.SDKController.runFunction(").append("'").append(str3).append("?").append("parameters").append("=").append(str4).append("'").append(");");
        WebViewController.this.injectJavascript(localStringBuilder.toString());
        return;
      }
      WebViewController.this.responseBack(paramString, false, "SetUserData failed writing to shared preferences", null);
    }
    
    @JavascriptInterface
    public void setUserUniqueId(String paramString)
    {
      Logger.i(WebViewController.this.TAG, "setUserUniqueId(" + paramString + ")");
      SSAObj localSSAObj = new SSAObj(paramString);
      if ((!localSSAObj.containsKey("userUniqueId")) || (!localSSAObj.containsKey("productType")))
      {
        WebViewController.this.responseBack(paramString, false, "uniqueId or productType does not exist", null);
        return;
      }
      String str1 = localSSAObj.getString("userUniqueId");
      String str2 = localSSAObj.getString("productType");
      if (WebViewController.this.cacheManager.setUniqueId(str1, str2))
      {
        WebViewController.this.responseBack(paramString, true, null, null);
        return;
      }
      WebViewController.this.responseBack(paramString, false, "setUserUniqueId failed", null);
    }
    
    @JavascriptInterface
    public void setWebviewBackgroundColor(String paramString)
    {
      Logger.i(WebViewController.this.TAG, "setWebviewBackgroundColor(" + paramString + ")");
      WebViewController.this.setWebviewBackground(paramString);
    }
    
    @JavascriptInterface
    public void toggleUDIA(String paramString)
    {
      Logger.i(WebViewController.this.TAG, "toggleUDIA(" + paramString + ")");
      SSAObj localSSAObj = new SSAObj(paramString);
      if (!localSSAObj.containsKey("toggle")) {
        WebViewController.this.responseBack(paramString, false, "toggle key does not exist", null);
      }
      int i;
      do
      {
        return;
        i = Integer.parseInt(localSSAObj.getString("toggle"));
      } while (i == 0);
      String str = Integer.toBinaryString(i);
      if (TextUtils.isEmpty(str))
      {
        WebViewController.this.responseBack(paramString, false, "fialed to convert toggle", null);
        return;
      }
      if (str.toCharArray()[3] == '0')
      {
        WebViewController.this.cacheManager.setShouldRegisterSessions(true);
        return;
      }
      WebViewController.this.cacheManager.setShouldRegisterSessions(false);
    }
  }
  
  public static abstract interface OnWebViewControllerChangeListener
  {
    public abstract void onHide();
    
    public abstract void onSetOrientationCalled(String paramString, int paramInt);
  }
  
  public static enum State
  {
    Gone,  Display;
  }
  
  private class ViewClient
    extends WebViewClient
  {
    private ViewClient() {}
    
    public void onPageFinished(WebView paramWebView, String paramString)
    {
      Logger.i("onPageFinished", paramString);
      if ((paramString.contains("adUnit")) || (paramString.contains("index.html"))) {
        WebViewController.this.pageFinished();
      }
      super.onPageFinished(paramWebView, paramString);
    }
    
    public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
    {
      Logger.i("onPageStarted", paramString);
      super.onPageStarted(paramWebView, paramString, paramBitmap);
    }
    
    public void onReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2)
    {
      Logger.i("onReceivedError", paramString2 + " " + paramString1);
      super.onReceivedError(paramWebView, paramInt, paramString1, paramString2);
    }
    
    public WebResourceResponse shouldInterceptRequest(WebView paramWebView, String paramString)
    {
      Logger.i("shouldInterceptRequest", paramString);
      try
      {
        boolean bool = new URL(paramString).getFile().contains("mraid.js");
        i = 0;
        if (bool) {
          i = 1;
        }
      }
      catch (MalformedURLException localMalformedURLException)
      {
        for (;;)
        {
          String str1;
          String str2;
          File localFile;
          int i = 0;
        }
      }
      if (i != 0)
      {
        str1 = WebViewController.this.cacheManager.getRootDirectoryPath();
        str2 = "file://" + str1 + File.separator + "mraid.js";
        localFile = new File(str2);
        try
        {
          new FileInputStream(localFile);
          WebResourceResponse localWebResourceResponse = new WebResourceResponse("text/javascript", "UTF-8", getClass().getResourceAsStream(str2));
          return localWebResourceResponse;
        }
        catch (FileNotFoundException localFileNotFoundException) {}
      }
      return super.shouldInterceptRequest(paramWebView, paramString);
    }
    
    public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
    {
      Logger.i("shouldOverrideUrlLoading", paramString);
      if (WebViewController.this.handleSearchKeysURLs(paramString))
      {
        WebViewController.this.interceptedUrlToStore();
        return true;
      }
      return super.shouldOverrideUrlLoading(paramWebView, paramString);
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.supersonicads.sdk.controller.WebViewController
 * JD-Core Version:    0.7.0.1
 */