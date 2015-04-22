package com.millennialmedia.android;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout.LayoutParams;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Random;

public final class MMAdView
  extends MMLayout
  implements View.OnClickListener, Animation.AnimationListener
{
  static final int DEFAULT_RESIZE_PARAM_VALUES = -50;
  private static final String TAG = "MMAdView";
  public static final int TRANSITION_DOWN = 3;
  public static final int TRANSITION_FADE = 1;
  public static final int TRANSITION_NONE = 0;
  public static final int TRANSITION_RANDOM = 4;
  public static final int TRANSITION_UP = 2;
  int height = 0;
  int oldHeight = -50;
  int oldWidth = -50;
  ImageView refreshAnimationimageView;
  int transitionType = 4;
  ResizeView view;
  int width = 0;
  
  public MMAdView(Context paramContext)
  {
    super(paramContext);
    this.adImpl = new MMAdViewMMAdImpl(paramContext);
    init(paramContext);
  }
  
  @Deprecated
  public MMAdView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  @Deprecated
  public MMAdView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    if (!isInEditMode())
    {
      MMLog.d("MMAdView", "Creating MMAdView from XML layout.");
      this.adImpl = new MMAdViewMMAdImpl(paramContext);
      String str1;
      String str2;
      if (paramAttributeSet != null)
      {
        setApid(paramAttributeSet.getAttributeValue("http://millennialmedia.com/android/schema", "apid"));
        this.adImpl.ignoreDensityScaling = paramAttributeSet.getAttributeBooleanValue("http://millennialmedia.com/android/schema", "ignoreDensityScaling", false);
        str1 = paramAttributeSet.getAttributeValue("http://millennialmedia.com/android/schema", "height");
        str2 = paramAttributeSet.getAttributeValue("http://millennialmedia.com/android/schema", "width");
      }
      try
      {
        if (!TextUtils.isEmpty(str1)) {
          this.height = Integer.parseInt(str1);
        }
        if (!TextUtils.isEmpty(str2)) {
          this.width = Integer.parseInt(str2);
        }
      }
      catch (NumberFormatException localNumberFormatException)
      {
        for (;;)
        {
          MMLog.e("MMAdView", "Error reading attrs file from xml", localNumberFormatException);
        }
      }
      if (this.adImpl.mmRequest != null)
      {
        this.adImpl.mmRequest.age = paramAttributeSet.getAttributeValue("http://millennialmedia.com/android/schema", "age");
        this.adImpl.mmRequest.children = paramAttributeSet.getAttributeValue("http://millennialmedia.com/android/schema", "children");
        this.adImpl.mmRequest.education = paramAttributeSet.getAttributeValue("http://millennialmedia.com/android/schema", "education");
        this.adImpl.mmRequest.ethnicity = paramAttributeSet.getAttributeValue("http://millennialmedia.com/android/schema", "ethnicity");
        this.adImpl.mmRequest.gender = paramAttributeSet.getAttributeValue("http://millennialmedia.com/android/schema", "gender");
        this.adImpl.mmRequest.income = paramAttributeSet.getAttributeValue("http://millennialmedia.com/android/schema", "income");
        this.adImpl.mmRequest.keywords = paramAttributeSet.getAttributeValue("http://millennialmedia.com/android/schema", "keywords");
        this.adImpl.mmRequest.marital = paramAttributeSet.getAttributeValue("http://millennialmedia.com/android/schema", "marital");
        this.adImpl.mmRequest.politics = paramAttributeSet.getAttributeValue("http://millennialmedia.com/android/schema", "politics");
        this.adImpl.mmRequest.vendor = paramAttributeSet.getAttributeValue("http://millennialmedia.com/android/schema", "vendor");
        this.adImpl.mmRequest.zip = paramAttributeSet.getAttributeValue("http://millennialmedia.com/android/schema", "zip");
      }
      this.goalId = paramAttributeSet.getAttributeValue("http://millennialmedia.com/android/schema", "goalId");
      init(paramContext);
      return;
    }
    initEclipseAd(paramContext);
  }
  
  private void attachToWindow(View paramView)
  {
    try
    {
      detachFromParent(paramView);
      Context localContext = getContext();
      if ((localContext != null) && ((localContext instanceof Activity)))
      {
        Window localWindow = ((Activity)localContext).getWindow();
        if (localWindow != null)
        {
          View localView = localWindow.getDecorView();
          if ((localView != null) && ((localView instanceof ViewGroup))) {
            ((ViewGroup)localView).addView(paramView);
          }
        }
      }
      return;
    }
    finally {}
  }
  
  private void callSetTranslationX(int paramInt)
  {
    try
    {
      Class[] arrayOfClass = new Class[1];
      arrayOfClass[0] = Float.TYPE;
      Method localMethod = View.class.getMethod("setTranslationX", arrayOfClass);
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(paramInt);
      localMethod.invoke(this, arrayOfObject);
      return;
    }
    catch (Exception localException)
    {
      MMLog.e("MMAdView", "Unable to call setTranslationX", localException);
    }
  }
  
  private void callSetTranslationY(int paramInt)
  {
    try
    {
      Class[] arrayOfClass = new Class[1];
      arrayOfClass[0] = Float.TYPE;
      Method localMethod = View.class.getMethod("setTranslationY", arrayOfClass);
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(paramInt);
      localMethod.invoke(this, arrayOfObject);
      return;
    }
    catch (Exception localException)
    {
      MMLog.e("MMAdView", "Unable to call setTranslationY", localException);
    }
  }
  
  private void detachFromParent(View paramView)
  {
    if (paramView != null) {}
    try
    {
      ViewParent localViewParent = getParent();
      if ((localViewParent != null) && ((localViewParent instanceof ViewGroup)))
      {
        ViewGroup localViewGroup = (ViewGroup)localViewParent;
        if (paramView.getParent() != null) {
          localViewGroup.removeView(paramView);
        }
      }
      return;
    }
    finally {}
  }
  
  private void getAdInternal()
  {
    if (this.adImpl != null) {
      this.adImpl.requestAd();
    }
  }
  
  private boolean hasDefaultResizeParams()
  {
    return (this.oldWidth == -50) && (this.oldHeight == -50);
  }
  
  private void init(Context paramContext)
  {
    setBackgroundColor(0);
    this.adImpl.adType = "b";
    setOnClickListener(this);
    setFocusable(true);
    this.refreshAnimationimageView = new ImageView(paramContext);
    this.refreshAnimationimageView.setScaleType(ImageView.ScaleType.FIT_XY);
    this.refreshAnimationimageView.setVisibility(8);
    addView(this.refreshAnimationimageView, new RelativeLayout.LayoutParams(-2, -2));
    setLayoutParams(new RelativeLayout.LayoutParams(-2, -2));
  }
  
  /* Error */
  private void initEclipseAd(Context paramContext)
  {
    // Byte code:
    //   0: new 284	android/widget/ImageView
    //   3: dup
    //   4: aload_1
    //   5: invokespecial 285	android/widget/ImageView:<init>	(Landroid/content/Context;)V
    //   8: astore_2
    //   9: aconst_null
    //   10: astore_3
    //   11: aconst_null
    //   12: astore 4
    //   14: ldc_w 314
    //   17: invokestatic 320	java/lang/System:getProperty	(Ljava/lang/String;)Ljava/lang/String;
    //   20: astore 11
    //   22: aconst_null
    //   23: astore_3
    //   24: aconst_null
    //   25: astore 4
    //   27: aload 11
    //   29: ifnull +46 -> 75
    //   32: aload 11
    //   34: getstatic 325	java/io/File:separator	Ljava/lang/String;
    //   37: invokevirtual 331	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   40: istore 12
    //   42: aconst_null
    //   43: astore_3
    //   44: aconst_null
    //   45: astore 4
    //   47: iload 12
    //   49: ifne +26 -> 75
    //   52: new 333	java/lang/StringBuilder
    //   55: dup
    //   56: invokespecial 335	java/lang/StringBuilder:<init>	()V
    //   59: aload 11
    //   61: invokevirtual 339	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   64: getstatic 325	java/io/File:separator	Ljava/lang/String;
    //   67: invokevirtual 339	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   70: invokevirtual 343	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   73: astore 11
    //   75: new 322	java/io/File
    //   78: dup
    //   79: new 333	java/lang/StringBuilder
    //   82: dup
    //   83: invokespecial 335	java/lang/StringBuilder:<init>	()V
    //   86: aload 11
    //   88: invokevirtual 339	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   91: ldc_w 345
    //   94: invokevirtual 339	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   97: invokevirtual 343	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   100: invokespecial 347	java/io/File:<init>	(Ljava/lang/String;)V
    //   103: astore 13
    //   105: aload 13
    //   107: invokevirtual 350	java/io/File:exists	()Z
    //   110: istore 14
    //   112: aconst_null
    //   113: astore_3
    //   114: aconst_null
    //   115: astore 4
    //   117: iload 14
    //   119: ifne +134 -> 253
    //   122: new 352	java/net/URL
    //   125: dup
    //   126: ldc_w 354
    //   129: invokespecial 355	java/net/URL:<init>	(Ljava/lang/String;)V
    //   132: invokevirtual 359	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   135: checkcast 361	java/net/HttpURLConnection
    //   138: astore 15
    //   140: aload 15
    //   142: iconst_1
    //   143: invokevirtual 364	java/net/HttpURLConnection:setDoOutput	(Z)V
    //   146: aload 15
    //   148: sipush 10000
    //   151: invokevirtual 367	java/net/HttpURLConnection:setConnectTimeout	(I)V
    //   154: aload 15
    //   156: invokevirtual 370	java/net/HttpURLConnection:connect	()V
    //   159: aload 15
    //   161: invokevirtual 374	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   164: astore_3
    //   165: new 376	java/io/FileOutputStream
    //   168: dup
    //   169: aload 13
    //   171: invokespecial 379	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   174: astore 16
    //   176: sipush 1024
    //   179: newarray byte
    //   181: astore 17
    //   183: aload_3
    //   184: aload 17
    //   186: invokevirtual 385	java/io/InputStream:read	([B)I
    //   189: istore 18
    //   191: iload 18
    //   193: ifle +56 -> 249
    //   196: aload 16
    //   198: aload 17
    //   200: iconst_0
    //   201: iload 18
    //   203: invokevirtual 391	java/io/OutputStream:write	([BII)V
    //   206: goto -23 -> 183
    //   209: astore 5
    //   211: aload 16
    //   213: astore 4
    //   215: ldc 15
    //   217: ldc_w 393
    //   220: aload 5
    //   222: invokestatic 179	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   225: aload_3
    //   226: ifnull +7 -> 233
    //   229: aload_3
    //   230: invokevirtual 396	java/io/InputStream:close	()V
    //   233: aload 4
    //   235: ifnull +8 -> 243
    //   238: aload 4
    //   240: invokevirtual 397	java/io/OutputStream:close	()V
    //   243: aload_0
    //   244: aload_2
    //   245: invokevirtual 398	com/millennialmedia/android/MMAdView:addView	(Landroid/view/View;)V
    //   248: return
    //   249: aload 16
    //   251: astore 4
    //   253: aload 13
    //   255: invokevirtual 401	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   258: invokestatic 407	android/graphics/BitmapFactory:decodeFile	(Ljava/lang/String;)Landroid/graphics/Bitmap;
    //   261: astore 19
    //   263: aload_2
    //   264: ifnull +14 -> 278
    //   267: aload 19
    //   269: ifnull +9 -> 278
    //   272: aload_2
    //   273: aload 19
    //   275: invokevirtual 411	android/widget/ImageView:setImageBitmap	(Landroid/graphics/Bitmap;)V
    //   278: aload_3
    //   279: ifnull +7 -> 286
    //   282: aload_3
    //   283: invokevirtual 396	java/io/InputStream:close	()V
    //   286: aload 4
    //   288: ifnull -45 -> 243
    //   291: aload 4
    //   293: invokevirtual 397	java/io/OutputStream:close	()V
    //   296: goto -53 -> 243
    //   299: astore 8
    //   301: ldc 15
    //   303: astore 9
    //   305: ldc_w 413
    //   308: astore 10
    //   310: aload 9
    //   312: aload 10
    //   314: aload 8
    //   316: invokestatic 179	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   319: goto -76 -> 243
    //   322: astore 6
    //   324: aload_3
    //   325: ifnull +7 -> 332
    //   328: aload_3
    //   329: invokevirtual 396	java/io/InputStream:close	()V
    //   332: aload 4
    //   334: ifnull +8 -> 342
    //   337: aload 4
    //   339: invokevirtual 397	java/io/OutputStream:close	()V
    //   342: aload 6
    //   344: athrow
    //   345: astore 7
    //   347: ldc 15
    //   349: ldc_w 413
    //   352: aload 7
    //   354: invokestatic 179	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   357: goto -15 -> 342
    //   360: astore 8
    //   362: ldc 15
    //   364: astore 9
    //   366: ldc_w 413
    //   369: astore 10
    //   371: goto -61 -> 310
    //   374: astore 6
    //   376: aload 16
    //   378: astore 4
    //   380: goto -56 -> 324
    //   383: astore 5
    //   385: goto -170 -> 215
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	388	0	this	MMAdView
    //   0	388	1	paramContext	Context
    //   8	265	2	localImageView	ImageView
    //   10	319	3	localInputStream	java.io.InputStream
    //   12	367	4	localObject1	Object
    //   209	12	5	localException1	Exception
    //   383	1	5	localException2	Exception
    //   322	21	6	localObject2	Object
    //   374	1	6	localObject3	Object
    //   345	8	7	localException3	Exception
    //   299	16	8	localException4	Exception
    //   360	1	8	localException5	Exception
    //   303	62	9	str1	String
    //   308	62	10	str2	String
    //   20	67	11	str3	String
    //   40	8	12	bool1	boolean
    //   103	151	13	localFile	java.io.File
    //   110	8	14	bool2	boolean
    //   138	22	15	localHttpURLConnection	java.net.HttpURLConnection
    //   174	203	16	localFileOutputStream	java.io.FileOutputStream
    //   181	18	17	arrayOfByte	byte[]
    //   189	13	18	i	int
    //   261	13	19	localBitmap	Bitmap
    // Exception table:
    //   from	to	target	type
    //   176	183	209	java/lang/Exception
    //   183	191	209	java/lang/Exception
    //   196	206	209	java/lang/Exception
    //   282	286	299	java/lang/Exception
    //   291	296	299	java/lang/Exception
    //   14	22	322	finally
    //   32	42	322	finally
    //   52	75	322	finally
    //   75	112	322	finally
    //   122	176	322	finally
    //   215	225	322	finally
    //   253	263	322	finally
    //   272	278	322	finally
    //   328	332	345	java/lang/Exception
    //   337	342	345	java/lang/Exception
    //   229	233	360	java/lang/Exception
    //   238	243	360	java/lang/Exception
    //   176	183	374	finally
    //   183	191	374	finally
    //   196	206	374	finally
    //   14	22	383	java/lang/Exception
    //   32	42	383	java/lang/Exception
    //   52	75	383	java/lang/Exception
    //   75	112	383	java/lang/Exception
    //   122	176	383	java/lang/Exception
    //   253	263	383	java/lang/Exception
    //   272	278	383	java/lang/Exception
  }
  
  private void setUnresizeParameters()
  {
    if (hasDefaultResizeParams())
    {
      ViewGroup.LayoutParams localLayoutParams = getLayoutParams();
      this.oldWidth = localLayoutParams.width;
      this.oldHeight = localLayoutParams.height;
      if (this.oldWidth <= 0) {
        this.oldWidth = getWidth();
      }
      if (this.oldHeight <= 0) {
        this.oldHeight = getHeight();
      }
    }
  }
  
  void closeAreaTouched()
  {
    this.adImpl.unresizeToDefault();
  }
  
  public void getAd()
  {
    if ((this.adImpl != null) && (this.adImpl.requestListener != null))
    {
      getAd(this.adImpl.requestListener);
      return;
    }
    getAdInternal();
  }
  
  public void getAd(RequestListener paramRequestListener)
  {
    if (this.adImpl != null) {
      this.adImpl.requestListener = paramRequestListener;
    }
    getAdInternal();
  }
  
  void handleMraidResize(DTOResizeParameters paramDTOResizeParameters)
  {
    try
    {
      this.refreshAnimationimageView.setImageBitmap(null);
      if (MMSDK.hasSetTranslationMethod())
      {
        if (this.view == null)
        {
          this.view = new ResizeView(getContext());
          this.view.setId(304025022);
          this.view.setLayoutParams(new RelativeLayout.LayoutParams(1, 1));
          this.view.setBackgroundColor(0);
        }
        if (this.view.getParent() == null)
        {
          ViewParent localViewParent = getParent();
          if ((localViewParent != null) && ((localViewParent instanceof ViewGroup))) {
            ((ViewGroup)localViewParent).addView(this.view);
          }
        }
        BannerBounds localBannerBounds = new BannerBounds(paramDTOResizeParameters);
        if (!paramDTOResizeParameters.allowOffScreen) {
          localBannerBounds.calculateOnScreenBounds();
        }
        int[] arrayOfInt1 = new int[2];
        getLocationInWindow(arrayOfInt1);
        attachToWindow(this);
        int[] arrayOfInt2 = new int[2];
        getLocationInWindow(arrayOfInt2);
        setUnresizeParameters();
        int i = arrayOfInt1[0] - arrayOfInt2[0];
        int j = arrayOfInt1[1] - arrayOfInt2[1];
        localBannerBounds.modifyLayoutParams(getLayoutParams());
        callSetTranslationX(i + localBannerBounds.translationX);
        callSetTranslationY(j + localBannerBounds.translationY);
        setCloseArea(paramDTOResizeParameters.customClosePosition);
      }
      return;
    }
    finally {}
  }
  
  void handleUnresize()
  {
    try
    {
      if (MMSDK.hasSetTranslationMethod())
      {
        removeCloseTouchDelegate();
        if (!hasDefaultResizeParams())
        {
          ViewGroup.LayoutParams localLayoutParams = getLayoutParams();
          localLayoutParams.width = this.oldWidth;
          localLayoutParams.height = this.oldHeight;
          callSetTranslationX(0);
          callSetTranslationY(0);
          this.oldWidth = -50;
          this.oldHeight = -50;
        }
        if (this.view != null)
        {
          this.isResizing = true;
          this.view.attachToContext(this);
          ViewParent localViewParent = getParent();
          if ((localViewParent != null) && ((localViewParent instanceof ViewGroup)))
          {
            ViewGroup localViewGroup = (ViewGroup)localViewParent;
            if (this.view.getParent() != null) {
              localViewGroup.removeView(this.view);
            }
          }
          this.isResizing = false;
        }
      }
      return;
    }
    finally {}
  }
  
  @Deprecated
  public void onAnimationEnd(Animation paramAnimation)
  {
    this.refreshAnimationimageView.setVisibility(8);
  }
  
  @Deprecated
  public void onAnimationRepeat(Animation paramAnimation) {}
  
  @Deprecated
  public void onAnimationStart(Animation paramAnimation) {}
  
  @Deprecated
  public void onClick(View paramView)
  {
    MMLog.d("MMAdView", "On click for " + paramView.getId() + " view, " + paramView + " adimpl" + this.adImpl);
    onTouchEvent(MotionEvent.obtain(0L, System.currentTimeMillis(), 1, 0.0F, 0.0F, 0));
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    Utils.ThreadUtils.execute(new Runnable()
    {
      public void run()
      {
        float f = MMAdView.this.getContext().getResources().getDisplayMetrics().density;
        if (MMAdView.this.width <= 0) {
          MMAdView.this.width = ((int)(MMAdView.this.getWidth() / f));
        }
        if (MMAdView.this.height <= 0) {
          MMAdView.this.height = ((int)(MMAdView.this.getHeight() / f));
        }
      }
    });
  }
  
  public void onWindowFocusChanged(boolean paramBoolean)
  {
    super.onWindowFocusChanged(paramBoolean);
    if ((!paramBoolean) || (this.adImpl == null) || (this.adImpl.controller == null)) {}
    MMWebView localMMWebView;
    do
    {
      return;
      if (this.adImpl.controller.webView == null) {
        this.adImpl.controller.webView = MMAdImplController.getWebViewFromExistingAdImpl(this.adImpl);
      }
      localMMWebView = this.adImpl.controller.webView;
    } while ((localMMWebView == null) || (localMMWebView.isCurrentParent(this.adImpl.internalId)) || (localMMWebView.mraidState.equals("expanded")));
    localMMWebView.removeFromParent();
    addView(localMMWebView);
  }
  
  public void setBackgroundColor(int paramInt)
  {
    super.setBackgroundColor(paramInt);
    if ((this.adImpl != null) && (this.adImpl.controller != null) && (this.adImpl.controller.webView != null)) {
      this.adImpl.controller.webView.setBackgroundColor(paramInt);
    }
  }
  
  public void setHeight(int paramInt)
  {
    this.height = paramInt;
  }
  
  public void setTransitionType(int paramInt)
  {
    this.transitionType = paramInt;
  }
  
  public void setWidth(int paramInt)
  {
    this.width = paramInt;
  }
  
  class BannerBounds
  {
    DTOResizeParameters resizeParams;
    int translationX;
    int translationY;
    
    BannerBounds(DTOResizeParameters paramDTOResizeParameters)
    {
      this.resizeParams = paramDTOResizeParameters;
      this.translationX = paramDTOResizeParameters.offsetX;
      this.translationY = paramDTOResizeParameters.offsetY;
    }
    
    private BoundsResult calculateBounds(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      int i = paramInt1;
      int j = paramInt3;
      if (paramInt2 + (paramInt1 + paramInt3) > paramInt4)
      {
        i = paramInt2 + (paramInt4 - paramInt3);
        if (i < 0)
        {
          i = 0;
          j = paramInt4;
        }
      }
      for (;;)
      {
        BoundsResult localBoundsResult = new BoundsResult(null);
        localBoundsResult.translation = (i - paramInt1);
        localBoundsResult.size = j;
        return localBoundsResult;
        if (i + paramInt3 > paramInt4)
        {
          i = paramInt4 - paramInt3;
          continue;
          if (paramInt2 > 0) {
            i = paramInt2;
          }
        }
      }
    }
    
    private void calculateXBounds()
    {
      int[] arrayOfInt = new int[2];
      MMAdView.this.getLocationInWindow(arrayOfInt);
      BoundsResult localBoundsResult = calculateBounds(arrayOfInt[0], this.resizeParams.offsetX, this.resizeParams.width, this.resizeParams.xMax);
      this.resizeParams.width = localBoundsResult.size;
      this.translationX = localBoundsResult.translation;
    }
    
    private void calculateYBounds()
    {
      int[] arrayOfInt = new int[2];
      MMAdView.this.getLocationInWindow(arrayOfInt);
      BoundsResult localBoundsResult = calculateBounds(arrayOfInt[1], this.resizeParams.offsetY, this.resizeParams.height, this.resizeParams.yMax);
      this.resizeParams.height = localBoundsResult.size;
      this.translationY = localBoundsResult.translation;
    }
    
    void calculateOnScreenBounds()
    {
      calculateXBounds();
      calculateYBounds();
    }
    
    ViewGroup.LayoutParams modifyLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
    {
      paramLayoutParams.width = this.resizeParams.width;
      paramLayoutParams.height = this.resizeParams.height;
      return paramLayoutParams;
    }
    
    private class BoundsResult
    {
      int size;
      int translation;
      
      private BoundsResult() {}
    }
  }
  
  class MMAdViewMMAdImpl
    extends MMLayout.MMLayoutMMAdImpl
  {
    public MMAdViewMMAdImpl(Context paramContext)
    {
      super(paramContext);
      this.mmWebViewClientListener = new MMAdView.MMAdViewWebViewClientListener(this);
    }
    
    void animateTransition()
    {
      Object localObject;
      if (MMAdView.this.refreshAnimationimageView.getDrawable() != null)
      {
        int i = MMAdView.this.transitionType;
        if (i == 4) {
          i = new Random().nextInt(4);
        }
        switch (i)
        {
        default: 
          localObject = new AlphaAnimation(1.0F, 0.0F);
        }
      }
      for (;;)
      {
        ((Animation)localObject).setDuration(1000L);
        ((Animation)localObject).setAnimationListener(MMAdView.this);
        ((Animation)localObject).setFillEnabled(true);
        ((Animation)localObject).setFillBefore(true);
        ((Animation)localObject).setFillAfter(true);
        MMSDK.runOnUiThread(new Runnable()
        {
          public void run()
          {
            MMAdView.this.refreshAnimationimageView.startAnimation(this.val$animFinal);
          }
        });
        return;
        localObject = new TranslateAnimation(0.0F, 0.0F, 0.0F, -MMAdView.this.getHeight());
        continue;
        localObject = new TranslateAnimation(0.0F, 0.0F, 0.0F, MMAdView.this.getHeight());
      }
    }
    
    String getReqType()
    {
      return "getad";
    }
    
    String getRequestCompletedAction()
    {
      return "millennialmedia.action.ACTION_GETAD_SUCCEEDED";
    }
    
    String getRequestFailedAction()
    {
      return "millennialmedia.action.ACTION_GETAD_FAILED";
    }
    
    public boolean hasCachedVideoSupport()
    {
      return false;
    }
    
    void insertUrlAdMetaValues(Map<String, String> paramMap)
    {
      if (MMAdView.this.height > 0) {
        paramMap.put("hsht", String.valueOf(MMAdView.this.height));
      }
      if (MMAdView.this.width > 0) {
        paramMap.put("hswd", String.valueOf(MMAdView.this.width));
      }
      super.insertUrlAdMetaValues(paramMap);
    }
    
    public boolean isBanner()
    {
      return true;
    }
    
    boolean isLifecycleObservable()
    {
      return MMAdView.this.getWindowToken() != null;
    }
    
    boolean isTransitionAnimated()
    {
      return MMAdView.this.transitionType != 0;
    }
    
    void prepareTransition(Bitmap paramBitmap)
    {
      MMAdView.this.refreshAnimationimageView.setImageBitmap(paramBitmap);
      MMAdView.this.refreshAnimationimageView.setVisibility(0);
      MMAdView.this.refreshAnimationimageView.bringToFront();
    }
  }
  
  private static class MMAdViewWebViewClientListener
    extends MMAdImpl.BasicWebViewClientListener
  {
    MMAdViewWebViewClientListener(MMAdImpl paramMMAdImpl)
    {
      super();
    }
    
    public void onPageFinished(String paramString)
    {
      super.onPageFinished(paramString);
      MMAdImpl localMMAdImpl = (MMAdImpl)this.adImplRef.get();
      if ((localMMAdImpl != null) && (localMMAdImpl.isTransitionAnimated())) {
        localMMAdImpl.animateTransition();
      }
    }
  }
  
  class ResizeView
    extends View
  {
    public ResizeView(Context paramContext)
    {
      super();
    }
    
    void attachToContext(View paramView)
    {
      try
      {
        MMAdView.this.detachFromParent(paramView);
        if ((getParent() != null) && ((getParent() instanceof ViewGroup))) {
          ((ViewGroup)getParent()).addView(paramView);
        }
        return;
      }
      finally
      {
        localObject = finally;
        throw localObject;
      }
    }
    
    protected void onRestoreInstanceState(Parcelable paramParcelable)
    {
      MMLog.d("MMAdView", "onRestoreInstanceState");
      MMAdView.this.attachToWindow(MMAdView.this);
      super.onRestoreInstanceState(paramParcelable);
    }
    
    protected Parcelable onSaveInstanceState()
    {
      MMLog.d("MMAdView", "onSaveInstanceState");
      attachToContext(MMAdView.this);
      return super.onSaveInstanceState();
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.MMAdView
 * JD-Core Version:    0.7.0.1
 */