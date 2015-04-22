package com.millennialmedia.android;

import android.app.Activity;
import android.content.Context;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.Callable;
import org.json.JSONArray;

class BridgeMMCachedVideo
  extends MMJSObject
  implements AdCache.AdCacheTaskListener
{
  private static final String AVAILABLE_CACHED_VIDEOS = "availableCachedVideos";
  private static final String CACHE_VIDEO = "cacheVideo";
  private static final String END_VIDEO = "endVideo";
  private static final String PAUSE_VIDEO = "pauseVideo";
  private static final String PLAY_CACHED_VIDEO = "playCachedVideo";
  private static final String PLAY_VIDEO = "playVideo";
  private static final String RESTART_VIDEO = "restartVideo";
  private static final String TAG = "BridgeMMCachedVideo";
  private static final String VIDEO_ID_EXISTS = "videoIdExists";
  private boolean success;
  
  private VideoPlayerActivity getVPA()
  {
    WeakReference localWeakReference = this.mmWebViewRef;
    VideoPlayerActivity localVideoPlayerActivity = null;
    if (localWeakReference != null)
    {
      Object localObject = this.mmWebViewRef.get();
      localVideoPlayerActivity = null;
      if (localObject != null)
      {
        boolean bool1 = ((MMWebView)this.mmWebViewRef.get()).getActivity() instanceof MMActivity;
        localVideoPlayerActivity = null;
        if (bool1)
        {
          MMWebView localMMWebView = (MMWebView)this.mmWebViewRef.get();
          localVideoPlayerActivity = null;
          if (localMMWebView != null)
          {
            Activity localActivity = localMMWebView.getActivity();
            localVideoPlayerActivity = null;
            if (localActivity != null)
            {
              boolean bool2 = localActivity instanceof MMActivity;
              localVideoPlayerActivity = null;
              if (bool2)
              {
                MMActivity localMMActivity = (MMActivity)localActivity;
                MMBaseActivity localMMBaseActivity = localMMActivity.getWrappedActivity();
                localVideoPlayerActivity = null;
                if (localMMBaseActivity != null)
                {
                  boolean bool3 = localMMActivity.getWrappedActivity() instanceof VideoPlayerActivity;
                  localVideoPlayerActivity = null;
                  if (bool3) {
                    localVideoPlayerActivity = (VideoPlayerActivity)localMMActivity.getWrappedActivity();
                  }
                }
              }
            }
          }
        }
      }
    }
    return localVideoPlayerActivity;
  }
  
  public MMJSResponse availableCachedVideos(Map<String, String> paramMap)
  {
    final Context localContext = (Context)this.contextRef.get();
    if (localContext != null)
    {
      final JSONArray localJSONArray = new JSONArray();
      AdCache.iterateCachedAds(localContext, 2, new AdCache.Iterator()
      {
        boolean callback(CachedAd paramAnonymousCachedAd)
        {
          if (((paramAnonymousCachedAd instanceof VideoAd)) && (paramAnonymousCachedAd.isOnDisk(localContext)) && (!paramAnonymousCachedAd.isExpired())) {
            localJSONArray.put(paramAnonymousCachedAd.getId());
          }
          return true;
        }
      });
      MMJSResponse localMMJSResponse = new MMJSResponse();
      localMMJSResponse.result = 1;
      localMMJSResponse.response = localJSONArray;
      return localMMJSResponse;
    }
    return null;
  }
  
  /* Error */
  public MMJSResponse cacheVideo(Map<String, String> paramMap)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 70	com/millennialmedia/android/BridgeMMCachedVideo:contextRef	Ljava/lang/ref/WeakReference;
    //   6: invokevirtual 52	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   9: checkcast 72	android/content/Context
    //   12: astore_3
    //   13: aload_1
    //   14: ldc 107
    //   16: invokeinterface 112 2 0
    //   21: checkcast 114	java/lang/String
    //   24: astore 4
    //   26: aconst_null
    //   27: astore 5
    //   29: aload 4
    //   31: ifnull +36 -> 67
    //   34: aconst_null
    //   35: astore 5
    //   37: aload_3
    //   38: ifnull +29 -> 67
    //   41: new 116	com/millennialmedia/android/HttpGetRequest
    //   44: dup
    //   45: invokespecial 117	com/millennialmedia/android/HttpGetRequest:<init>	()V
    //   48: aload 4
    //   50: invokevirtual 120	com/millennialmedia/android/HttpGetRequest:get	(Ljava/lang/String;)Lorg/apache/http/HttpResponse;
    //   53: astore 7
    //   55: aload 7
    //   57: ifnonnull +15 -> 72
    //   60: ldc 31
    //   62: ldc 122
    //   64: invokestatic 128	com/millennialmedia/android/MMLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   67: aload_0
    //   68: monitorexit
    //   69: aload 5
    //   71: areturn
    //   72: aload 7
    //   74: invokeinterface 134 1 0
    //   79: astore 8
    //   81: aload 8
    //   83: ifnonnull +38 -> 121
    //   86: ldc 31
    //   88: ldc 136
    //   90: invokestatic 139	com/millennialmedia/android/MMLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   93: aconst_null
    //   94: astore 5
    //   96: goto -29 -> 67
    //   99: astore_2
    //   100: aload_0
    //   101: monitorexit
    //   102: aload_2
    //   103: athrow
    //   104: astore 6
    //   106: ldc 31
    //   108: ldc 141
    //   110: aload 6
    //   112: invokestatic 145	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   115: aconst_null
    //   116: astore 5
    //   118: goto -51 -> 67
    //   121: aload 8
    //   123: invokeinterface 151 1 0
    //   128: lconst_0
    //   129: lcmp
    //   130: ifne +16 -> 146
    //   133: ldc 31
    //   135: ldc 153
    //   137: invokestatic 139	com/millennialmedia/android/MMLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   140: aconst_null
    //   141: astore 5
    //   143: goto -76 -> 67
    //   146: aload 8
    //   148: invokeinterface 157 1 0
    //   153: astore 9
    //   155: aconst_null
    //   156: astore 5
    //   158: aload 9
    //   160: ifnull -93 -> 67
    //   163: aload 9
    //   165: invokeinterface 163 1 0
    //   170: astore 10
    //   172: aconst_null
    //   173: astore 5
    //   175: aload 10
    //   177: ifnull -110 -> 67
    //   180: aload 9
    //   182: invokeinterface 163 1 0
    //   187: ldc 165
    //   189: invokevirtual 169	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   192: istore 11
    //   194: aconst_null
    //   195: astore 5
    //   197: iload 11
    //   199: ifeq -132 -> 67
    //   202: new 171	com/millennialmedia/android/VideoAd
    //   205: dup
    //   206: aload 8
    //   208: invokeinterface 175 1 0
    //   213: invokestatic 179	com/millennialmedia/android/HttpGetRequest:convertStreamToString	(Ljava/io/InputStream;)Ljava/lang/String;
    //   216: invokespecial 182	com/millennialmedia/android/VideoAd:<init>	(Ljava/lang/String;)V
    //   219: astore 12
    //   221: aconst_null
    //   222: astore 5
    //   224: aload 12
    //   226: ifnull -159 -> 67
    //   229: aload 12
    //   231: invokevirtual 186	com/millennialmedia/android/VideoAd:isValid	()Z
    //   234: istore 13
    //   236: aconst_null
    //   237: astore 5
    //   239: iload 13
    //   241: ifeq -174 -> 67
    //   244: aload 12
    //   246: iconst_3
    //   247: putfield 189	com/millennialmedia/android/VideoAd:downloadPriority	I
    //   250: aload_3
    //   251: aconst_null
    //   252: aload 12
    //   254: aload_0
    //   255: invokestatic 193	com/millennialmedia/android/AdCache:startDownloadTask	(Landroid/content/Context;Ljava/lang/String;Lcom/millennialmedia/android/CachedAd;Lcom/millennialmedia/android/AdCache$AdCacheTaskListener;)Z
    //   258: istore 14
    //   260: iload 14
    //   262: ifeq +118 -> 380
    //   265: aload_0
    //   266: invokevirtual 198	java/lang/Object:wait	()V
    //   269: aload_0
    //   270: getfield 200	com/millennialmedia/android/BridgeMMCachedVideo:success	Z
    //   273: ifeq +67 -> 340
    //   276: ldc 202
    //   278: iconst_1
    //   279: anewarray 195	java/lang/Object
    //   282: dup
    //   283: iconst_0
    //   284: aload 4
    //   286: aastore
    //   287: invokestatic 206	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   290: invokestatic 210	com/millennialmedia/android/MMJSResponse:responseWithSuccess	(Ljava/lang/String;)Lcom/millennialmedia/android/MMJSResponse;
    //   293: astore 18
    //   295: aload 18
    //   297: astore 5
    //   299: aload_0
    //   300: invokevirtual 213	java/lang/Object:notify	()V
    //   303: goto -236 -> 67
    //   306: astore 20
    //   308: ldc 31
    //   310: ldc 215
    //   312: aload 20
    //   314: invokestatic 145	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   317: aconst_null
    //   318: astore 5
    //   320: goto -253 -> 67
    //   323: astore 19
    //   325: ldc 31
    //   327: ldc 215
    //   329: aload 19
    //   331: invokestatic 145	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   334: aconst_null
    //   335: astore 5
    //   337: goto -270 -> 67
    //   340: aload_0
    //   341: invokevirtual 213	java/lang/Object:notify	()V
    //   344: aconst_null
    //   345: astore 5
    //   347: goto -280 -> 67
    //   350: astore 17
    //   352: ldc 31
    //   354: ldc 217
    //   356: aload 17
    //   358: invokestatic 145	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   361: aload_0
    //   362: invokevirtual 213	java/lang/Object:notify	()V
    //   365: aconst_null
    //   366: astore 5
    //   368: goto -301 -> 67
    //   371: astore 16
    //   373: aload_0
    //   374: invokevirtual 213	java/lang/Object:notify	()V
    //   377: aload 16
    //   379: athrow
    //   380: ldc 219
    //   382: iconst_1
    //   383: anewarray 195	java/lang/Object
    //   386: dup
    //   387: iconst_0
    //   388: aload 4
    //   390: aastore
    //   391: invokestatic 206	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   394: invokestatic 222	com/millennialmedia/android/MMJSResponse:responseWithError	(Ljava/lang/String;)Lcom/millennialmedia/android/MMJSResponse;
    //   397: astore 15
    //   399: aload 15
    //   401: astore 5
    //   403: goto -336 -> 67
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	406	0	this	BridgeMMCachedVideo
    //   0	406	1	paramMap	Map<String, String>
    //   99	4	2	localObject1	Object
    //   12	239	3	localContext	Context
    //   24	365	4	str1	String
    //   27	375	5	localObject2	Object
    //   104	7	6	localException	java.lang.Exception
    //   53	20	7	localHttpResponse	org.apache.http.HttpResponse
    //   79	128	8	localHttpEntity	org.apache.http.HttpEntity
    //   153	28	9	localHeader	org.apache.http.Header
    //   170	6	10	str2	String
    //   192	6	11	bool1	boolean
    //   219	34	12	localVideoAd	VideoAd
    //   234	6	13	bool2	boolean
    //   258	3	14	bool3	boolean
    //   397	3	15	localMMJSResponse1	MMJSResponse
    //   371	7	16	localObject3	Object
    //   350	7	17	localInterruptedException	java.lang.InterruptedException
    //   293	3	18	localMMJSResponse2	MMJSResponse
    //   323	7	19	localIOException	java.io.IOException
    //   306	7	20	localIllegalStateException	java.lang.IllegalStateException
    // Exception table:
    //   from	to	target	type
    //   2	26	99	finally
    //   41	55	99	finally
    //   60	67	99	finally
    //   72	81	99	finally
    //   86	93	99	finally
    //   106	115	99	finally
    //   121	140	99	finally
    //   146	155	99	finally
    //   163	172	99	finally
    //   180	194	99	finally
    //   202	221	99	finally
    //   229	236	99	finally
    //   244	260	99	finally
    //   299	303	99	finally
    //   308	317	99	finally
    //   325	334	99	finally
    //   340	344	99	finally
    //   361	365	99	finally
    //   373	380	99	finally
    //   380	399	99	finally
    //   41	55	104	java/lang/Exception
    //   60	67	104	java/lang/Exception
    //   72	81	104	java/lang/Exception
    //   202	221	306	java/lang/IllegalStateException
    //   202	221	323	java/io/IOException
    //   265	295	350	java/lang/InterruptedException
    //   265	295	371	finally
    //   352	361	371	finally
  }
  
  public void downloadCompleted(CachedAd paramCachedAd, boolean paramBoolean)
  {
    try
    {
      Context localContext = (Context)this.contextRef.get();
      if ((paramBoolean) && (localContext != null)) {
        AdCache.save(localContext, paramCachedAd);
      }
      this.success = paramBoolean;
      notify();
      return;
    }
    finally {}
  }
  
  public void downloadStart(CachedAd paramCachedAd) {}
  
  public MMJSResponse endVideo(Map<String, String> paramMap)
  {
    final VideoPlayerActivity localVideoPlayerActivity = getVPA();
    if (localVideoPlayerActivity != null) {
      runOnUiThreadFuture(new Callable()
      {
        public MMJSResponse call()
        {
          localVideoPlayerActivity.endVideo();
          return MMJSResponse.responseWithSuccess();
        }
      });
    }
    return null;
  }
  
  MMJSResponse executeCommand(String paramString, Map<String, String> paramMap)
  {
    MMJSResponse localMMJSResponse;
    if ("availableCachedVideos".equals(paramString)) {
      localMMJSResponse = availableCachedVideos(paramMap);
    }
    boolean bool;
    do
    {
      return localMMJSResponse;
      if ("cacheVideo".equals(paramString)) {
        return cacheVideo(paramMap);
      }
      if ("endVideo".equals(paramString)) {
        return endVideo(paramMap);
      }
      if ("pauseVideo".equals(paramString)) {
        return pauseVideo(paramMap);
      }
      if ("playCachedVideo".equals(paramString)) {
        return playCachedVideo(paramMap);
      }
      if ("playVideo".equals(paramString)) {
        return playVideo(paramMap);
      }
      if ("restartVideo".equals(paramString)) {
        return restartVideo(paramMap);
      }
      bool = "videoIdExists".equals(paramString);
      localMMJSResponse = null;
    } while (!bool);
    return videoIdExists(paramMap);
  }
  
  public MMJSResponse pauseVideo(Map<String, String> paramMap)
  {
    final VideoPlayerActivity localVideoPlayerActivity = getVPA();
    if (localVideoPlayerActivity != null) {
      runOnUiThreadFuture(new Callable()
      {
        public MMJSResponse call()
        {
          localVideoPlayerActivity.pauseVideoByUser();
          return MMJSResponse.responseWithSuccess();
        }
      });
    }
    return null;
  }
  
  public MMJSResponse playCachedVideo(Map<String, String> paramMap)
  {
    Context localContext = (Context)this.contextRef.get();
    String str = (String)paramMap.get("videoId");
    MMJSResponse localMMJSResponse = null;
    if (str != null)
    {
      localMMJSResponse = null;
      if (localContext != null)
      {
        VideoAd localVideoAd = (VideoAd)AdCache.load(localContext, str);
        localMMJSResponse = null;
        if (localVideoAd != null)
        {
          boolean bool = localVideoAd.canShow(localContext, null, false);
          localMMJSResponse = null;
          if (bool)
          {
            localVideoAd.show(localContext, getAdImplId((String)paramMap.get("PROPERTY_EXPANDING")));
            localMMJSResponse = MMJSResponse.responseWithSuccess(String.format("Playing Video(%s)", new Object[] { str }));
          }
        }
      }
    }
    return localMMJSResponse;
  }
  
  public MMJSResponse playVideo(Map<String, String> paramMap)
  {
    final VideoPlayerActivity localVideoPlayerActivity = getVPA();
    if (localVideoPlayerActivity != null) {
      runOnUiThreadFuture(new Callable()
      {
        public MMJSResponse call()
        {
          localVideoPlayerActivity.resumeVideo();
          return MMJSResponse.responseWithSuccess();
        }
      });
    }
    return null;
  }
  
  public MMJSResponse restartVideo(Map<String, String> paramMap)
  {
    final VideoPlayerActivity localVideoPlayerActivity = getVPA();
    if (localVideoPlayerActivity != null) {
      runOnUiThreadFuture(new Callable()
      {
        public MMJSResponse call()
        {
          localVideoPlayerActivity.restartVideo();
          return MMJSResponse.responseWithSuccess();
        }
      });
    }
    return null;
  }
  
  @Deprecated
  public MMJSResponse videoIdExists(Map<String, String> paramMap)
  {
    Context localContext = (Context)this.contextRef.get();
    String str = (String)paramMap.get("videoId");
    if ((str != null) && (localContext != null))
    {
      VideoAd localVideoAd = (VideoAd)AdCache.load(localContext, str);
      if ((localVideoAd != null) && (localVideoAd.isOnDisk(localContext)) && (!localVideoAd.isExpired())) {
        return MMJSResponse.responseWithSuccess(str);
      }
    }
    return null;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.BridgeMMCachedVideo
 * JD-Core Version:    0.7.0.1
 */