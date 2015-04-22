package com.supersonicads.sdk.agent;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.MutableContextWrapper;
import com.supersonicads.sdk.SSAPublisher;
import com.supersonicads.sdk.controller.WebViewController;
import com.supersonicads.sdk.listeners.OnGenericFunctionListener;
import com.supersonicads.sdk.listeners.OnInterstitialListener;
import com.supersonicads.sdk.listeners.OnOfferWallListener;
import com.supersonicads.sdk.listeners.OnRewardedVideoListener;
import com.supersonicads.sdk.precache.CacheManager;
import com.supersonicads.sdk.session.SSASession;
import com.supersonicads.sdk.session.SSASession.SessionType;
import com.supersonicads.sdk.utils.DeviceProperties;
import com.supersonicads.sdk.utils.Logger;
import java.util.Map;

@SuppressLint({"HandlerLeak"})
public final class SupersonicAdsPublisherAgent
  implements SSAPublisher
{
  private static final String TAG = "SupersonicAdsPublisherAgent";
  private static MutableContextWrapper mutableContextWrapper;
  private static SupersonicAdsPublisherAgent sInstance;
  private CacheManager cm;
  private SSASession session;
  private WebViewController wvc;
  
  private SupersonicAdsPublisherAgent(final Context paramContext, final String paramString, final int paramInt)
  {
    Logger.enableLogging(paramInt);
    Logger.i("SupersonicAdsPublisherAgent", "C'tor");
    mutableContextWrapper = new MutableContextWrapper(paramContext);
    if ((paramContext instanceof Activity)) {
      ((Activity)paramContext).runOnUiThread(new Runnable()
      {
        public void run()
        {
          SupersonicAdsPublisherAgent.this.wvc = new WebViewController(SupersonicAdsPublisherAgent.mutableContextWrapper);
          SupersonicAdsPublisherAgent.this.wvc.registerConnectionReceiver(paramContext);
          SupersonicAdsPublisherAgent.this.wvc.setDebugMode(paramInt);
          SupersonicAdsPublisherAgent.this.wvc.setLoadDomain(paramString);
          SupersonicAdsPublisherAgent.this.wvc.downloadController();
        }
      });
    }
    this.cm = new CacheManager(paramContext);
    startSession(paramContext);
  }
  
  private void endSession()
  {
    if (this.session != null)
    {
      this.session.endSession();
      this.cm.addSession(this.session);
      this.session = null;
    }
  }
  
  public static SupersonicAdsPublisherAgent getInstance(Context paramContext)
  {
    try
    {
      SupersonicAdsPublisherAgent localSupersonicAdsPublisherAgent = getInstance(paramContext, "http://s.ssacdn.com/", 0);
      return localSupersonicAdsPublisherAgent;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public static SupersonicAdsPublisherAgent getInstance(Context paramContext, int paramInt)
  {
    try
    {
      SupersonicAdsPublisherAgent localSupersonicAdsPublisherAgent = getInstance(paramContext, "http://s.ssacdn.com/", paramInt);
      return localSupersonicAdsPublisherAgent;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  /* Error */
  public static SupersonicAdsPublisherAgent getInstance(Context paramContext, String paramString, int paramInt)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: ldc 13
    //   5: ldc 95
    //   7: invokestatic 40	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   10: getstatic 97	com/supersonicads/sdk/agent/SupersonicAdsPublisherAgent:sInstance	Lcom/supersonicads/sdk/agent/SupersonicAdsPublisherAgent;
    //   13: ifnonnull +27 -> 40
    //   16: new 2	com/supersonicads/sdk/agent/SupersonicAdsPublisherAgent
    //   19: dup
    //   20: aload_0
    //   21: aload_1
    //   22: iload_2
    //   23: invokespecial 99	com/supersonicads/sdk/agent/SupersonicAdsPublisherAgent:<init>	(Landroid/content/Context;Ljava/lang/String;I)V
    //   26: putstatic 97	com/supersonicads/sdk/agent/SupersonicAdsPublisherAgent:sInstance	Lcom/supersonicads/sdk/agent/SupersonicAdsPublisherAgent;
    //   29: getstatic 97	com/supersonicads/sdk/agent/SupersonicAdsPublisherAgent:sInstance	Lcom/supersonicads/sdk/agent/SupersonicAdsPublisherAgent;
    //   32: astore 4
    //   34: ldc 2
    //   36: monitorexit
    //   37: aload 4
    //   39: areturn
    //   40: getstatic 47	com/supersonicads/sdk/agent/SupersonicAdsPublisherAgent:mutableContextWrapper	Landroid/content/MutableContextWrapper;
    //   43: aload_0
    //   44: invokevirtual 102	android/content/MutableContextWrapper:setBaseContext	(Landroid/content/Context;)V
    //   47: goto -18 -> 29
    //   50: astore_3
    //   51: ldc 2
    //   53: monitorexit
    //   54: aload_3
    //   55: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	56	0	paramContext	Context
    //   0	56	1	paramString	String
    //   0	56	2	paramInt	int
    //   50	5	3	localObject	Object
    //   32	6	4	localSupersonicAdsPublisherAgent	SupersonicAdsPublisherAgent
    // Exception table:
    //   from	to	target	type
    //   3	29	50	finally
    //   29	34	50	finally
    //   40	47	50	finally
  }
  
  private void startSession(Context paramContext)
  {
    this.session = new SSASession(paramContext, SSASession.SessionType.launched);
  }
  
  public void getOfferWallCredits(String paramString1, String paramString2, OnOfferWallListener paramOnOfferWallListener)
  {
    this.wvc.getOfferWallCredits(paramString1, paramString2, paramOnOfferWallListener);
  }
  
  public String getSDKVersion()
  {
    return "5.9";
  }
  
  public WebViewController getWebViewController()
  {
    return this.wvc;
  }
  
  public void initInterstitial(String paramString1, String paramString2, Map<String, String> paramMap, OnInterstitialListener paramOnInterstitialListener)
  {
    this.wvc.initInterstitial(paramString1, paramString2, paramMap, paramOnInterstitialListener);
  }
  
  public void initRewardedVideo(String paramString1, String paramString2, Map<String, String> paramMap, OnRewardedVideoListener paramOnRewardedVideoListener)
  {
    this.wvc.initBrandConnect(paramString1, paramString2, paramMap, paramOnRewardedVideoListener);
  }
  
  public void loadInterstitial()
  {
    this.wvc.loadInterstitial();
  }
  
  public void onPause(Context paramContext)
  {
    this.wvc.enterBackground();
    this.wvc.unregisterConnectionReceiver(paramContext);
    endSession();
  }
  
  public void onResume(Context paramContext)
  {
    mutableContextWrapper.setBaseContext(paramContext);
    this.wvc.enterForeground();
    this.wvc.registerConnectionReceiver(paramContext);
    if (this.session == null) {
      resumeSession(paramContext);
    }
  }
  
  public void release(Context paramContext)
  {
    Logger.i("SupersonicAdsPublisherAgent", "release()");
    DeviceProperties.release();
    this.wvc.destroy();
    sInstance = null;
    endSession();
  }
  
  public void resumeSession(Context paramContext)
  {
    this.session = new SSASession(paramContext, SSASession.SessionType.backFromBG);
  }
  
  public void runGenericFunction(String paramString, Map<String, String> paramMap, OnGenericFunctionListener paramOnGenericFunctionListener)
  {
    this.wvc.runGenericFunction(paramString, paramMap, paramOnGenericFunctionListener);
  }
  
  public void showInterstitial()
  {
    this.wvc.showInterstitial();
  }
  
  public void showOfferWall(String paramString1, String paramString2, Map<String, String> paramMap, OnOfferWallListener paramOnOfferWallListener)
  {
    this.wvc.showOfferWall(paramString1, paramString2, paramMap, paramOnOfferWallListener);
  }
  
  public void showRewardedVideo()
  {
    this.wvc.showBrandConnect();
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.supersonicads.sdk.agent.SupersonicAdsPublisherAgent
 * JD-Core Version:    0.7.0.1
 */