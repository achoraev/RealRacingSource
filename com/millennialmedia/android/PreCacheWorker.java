package com.millennialmedia.android;

import android.content.Context;
import android.text.TextUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;

class PreCacheWorker
  extends Thread
{
  private static final String TAG = "PreCacheWorker";
  private static boolean busy;
  private Context appContext;
  private DTOCachedVideo[] cachedVideos;
  private volatile boolean downloadedNewVideos = false;
  private String noVideosToCacheURL;
  
  private PreCacheWorker(DTOCachedVideo[] paramArrayOfDTOCachedVideo, Context paramContext, String paramString)
  {
    this.cachedVideos = paramArrayOfDTOCachedVideo;
    this.noVideosToCacheURL = paramString;
    this.appContext = paramContext.getApplicationContext();
  }
  
  private void handleContent(DTOCachedVideo paramDTOCachedVideo, HttpEntity paramHttpEntity)
  {
    Header localHeader = paramHttpEntity.getContentType();
    String str;
    if (localHeader != null)
    {
      str = localHeader.getValue();
      if ((str == null) || (!str.equalsIgnoreCase("application/json"))) {
        break label41;
      }
      handleJson(paramDTOCachedVideo, paramHttpEntity);
    }
    label41:
    while ((str == null) || (!str.startsWith("video/"))) {
      return;
    }
    handleVideoFile(paramDTOCachedVideo, paramHttpEntity);
  }
  
  /* Error */
  private void handleJson(final DTOCachedVideo paramDTOCachedVideo, HttpEntity paramHttpEntity)
  {
    // Byte code:
    //   0: aload_2
    //   1: invokeinterface 82 1 0
    //   6: invokestatic 88	com/millennialmedia/android/HttpGetRequest:convertStreamToString	(Ljava/io/InputStream;)Ljava/lang/String;
    //   9: astore 5
    //   11: aload 5
    //   13: invokestatic 94	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   16: istore 6
    //   18: aconst_null
    //   19: astore 7
    //   21: iload 6
    //   23: ifne +18 -> 41
    //   26: new 96	com/millennialmedia/android/VideoAd
    //   29: dup
    //   30: aload 5
    //   32: invokespecial 99	com/millennialmedia/android/VideoAd:<init>	(Ljava/lang/String;)V
    //   35: astore 8
    //   37: aload 8
    //   39: astore 7
    //   41: aload 7
    //   43: ifnull +43 -> 86
    //   46: aload 7
    //   48: invokevirtual 103	com/millennialmedia/android/VideoAd:isValid	()Z
    //   51: ifeq +35 -> 86
    //   54: aload 7
    //   56: iconst_1
    //   57: putfield 107	com/millennialmedia/android/VideoAd:downloadPriority	I
    //   60: aload_0
    //   61: getfield 35	com/millennialmedia/android/PreCacheWorker:appContext	Landroid/content/Context;
    //   64: aconst_null
    //   65: aload 7
    //   67: new 109	com/millennialmedia/android/PreCacheWorker$1
    //   70: dup
    //   71: aload_0
    //   72: aload_1
    //   73: invokespecial 112	com/millennialmedia/android/PreCacheWorker$1:<init>	(Lcom/millennialmedia/android/PreCacheWorker;Lcom/millennialmedia/android/DTOCachedVideo;)V
    //   76: invokestatic 118	com/millennialmedia/android/AdCache:startDownloadTask	(Landroid/content/Context;Ljava/lang/String;Lcom/millennialmedia/android/CachedAd;Lcom/millennialmedia/android/AdCache$AdCacheTaskListener;)Z
    //   79: ifeq +36 -> 115
    //   82: aload_0
    //   83: invokevirtual 123	java/lang/Object:wait	()V
    //   86: return
    //   87: astore 4
    //   89: aload 4
    //   91: invokevirtual 126	java/lang/IllegalStateException:printStackTrace	()V
    //   94: ldc 8
    //   96: ldc 128
    //   98: invokestatic 134	com/millennialmedia/android/MMLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   101: return
    //   102: astore_3
    //   103: aload_3
    //   104: invokevirtual 135	java/io/IOException:printStackTrace	()V
    //   107: ldc 8
    //   109: ldc 128
    //   111: invokestatic 134	com/millennialmedia/android/MMLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   114: return
    //   115: aload_1
    //   116: getfield 140	com/millennialmedia/android/DTOCachedVideo:preCacheStartURL	Ljava/lang/String;
    //   119: invokestatic 145	com/millennialmedia/android/MMSDK$Event:logEvent	(Ljava/lang/String;)V
    //   122: aload_1
    //   123: getfield 148	com/millennialmedia/android/DTOCachedVideo:preCacheFailedURL	Ljava/lang/String;
    //   126: invokestatic 145	com/millennialmedia/android/MMSDK$Event:logEvent	(Ljava/lang/String;)V
    //   129: return
    //   130: astore 9
    //   132: aload 9
    //   134: invokevirtual 149	java/lang/InterruptedException:printStackTrace	()V
    //   137: ldc 8
    //   139: ldc 151
    //   141: aload 9
    //   143: invokestatic 155	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   146: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	147	0	this	PreCacheWorker
    //   0	147	1	paramDTOCachedVideo	DTOCachedVideo
    //   0	147	2	paramHttpEntity	HttpEntity
    //   102	2	3	localIOException	java.io.IOException
    //   87	3	4	localIllegalStateException	java.lang.IllegalStateException
    //   9	22	5	str	String
    //   16	6	6	bool	boolean
    //   19	47	7	localObject	Object
    //   35	3	8	localVideoAd	VideoAd
    //   130	12	9	localInterruptedException	java.lang.InterruptedException
    // Exception table:
    //   from	to	target	type
    //   0	18	87	java/lang/IllegalStateException
    //   26	37	87	java/lang/IllegalStateException
    //   0	18	102	java/io/IOException
    //   26	37	102	java/io/IOException
    //   54	86	130	java/lang/InterruptedException
    //   115	129	130	java/lang/InterruptedException
  }
  
  private void handleVideoFile(DTOCachedVideo paramDTOCachedVideo, HttpEntity paramHttpEntity)
  {
    if (!TextUtils.isEmpty(paramDTOCachedVideo.videoFileId))
    {
      MMSDK.Event.logEvent(paramDTOCachedVideo.preCacheStartURL);
      if (AdCache.downloadComponentInternalCache(paramDTOCachedVideo.url, paramDTOCachedVideo.videoFileId + "video.dat", this.appContext)) {
        MMSDK.Event.logEvent(paramDTOCachedVideo.preCacheCompleteURL);
      }
    }
    else
    {
      return;
    }
    MMSDK.Event.logEvent(paramDTOCachedVideo.preCacheFailedURL);
  }
  
  static void preCacheVideos(DTOCachedVideo[] paramArrayOfDTOCachedVideo, Context paramContext, String paramString)
  {
    try
    {
      if (!busy)
      {
        busy = true;
        new PreCacheWorker(paramArrayOfDTOCachedVideo, paramContext, paramString).start();
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  /* Error */
  public void run()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 25	com/millennialmedia/android/PreCacheWorker:cachedVideos	[Lcom/millennialmedia/android/DTOCachedVideo;
    //   6: ifnull +193 -> 199
    //   9: aload_0
    //   10: getfield 25	com/millennialmedia/android/PreCacheWorker:cachedVideos	[Lcom/millennialmedia/android/DTOCachedVideo;
    //   13: astore 5
    //   15: aload 5
    //   17: arraylength
    //   18: istore 6
    //   20: iconst_0
    //   21: istore 7
    //   23: iload 7
    //   25: iload 6
    //   27: if_icmpge +172 -> 199
    //   30: aload 5
    //   32: iload 7
    //   34: aaload
    //   35: astore 8
    //   37: new 84	com/millennialmedia/android/HttpGetRequest
    //   40: dup
    //   41: invokespecial 192	com/millennialmedia/android/HttpGetRequest:<init>	()V
    //   44: aload 8
    //   46: getfield 161	com/millennialmedia/android/DTOCachedVideo:url	Ljava/lang/String;
    //   49: invokevirtual 196	com/millennialmedia/android/HttpGetRequest:get	(Ljava/lang/String;)Lorg/apache/http/HttpResponse;
    //   52: astore 10
    //   54: aload 10
    //   56: ifnonnull +13 -> 69
    //   59: ldc 8
    //   61: ldc 198
    //   63: invokestatic 134	com/millennialmedia/android/MMLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   66: goto +191 -> 257
    //   69: aload 10
    //   71: invokeinterface 204 1 0
    //   76: astore 11
    //   78: aload 11
    //   80: ifnonnull +86 -> 166
    //   83: ldc 8
    //   85: ldc 206
    //   87: invokestatic 134	com/millennialmedia/android/MMLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   90: goto +167 -> 257
    //   93: astore_1
    //   94: ldc 2
    //   96: monitorenter
    //   97: iconst_0
    //   98: putstatic 183	com/millennialmedia/android/PreCacheWorker:busy	Z
    //   101: aload_0
    //   102: getfield 23	com/millennialmedia/android/PreCacheWorker:downloadedNewVideos	Z
    //   105: ifne +27 -> 132
    //   108: aload_0
    //   109: getfield 27	com/millennialmedia/android/PreCacheWorker:noVideosToCacheURL	Ljava/lang/String;
    //   112: invokestatic 94	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   115: ifne +17 -> 132
    //   118: aload_0
    //   119: getfield 25	com/millennialmedia/android/PreCacheWorker:cachedVideos	[Lcom/millennialmedia/android/DTOCachedVideo;
    //   122: ifnonnull +10 -> 132
    //   125: aload_0
    //   126: getfield 27	com/millennialmedia/android/PreCacheWorker:noVideosToCacheURL	Ljava/lang/String;
    //   129: invokestatic 145	com/millennialmedia/android/MMSDK$Event:logEvent	(Ljava/lang/String;)V
    //   132: ldc 2
    //   134: monitorexit
    //   135: aload_1
    //   136: athrow
    //   137: astore_2
    //   138: aload_0
    //   139: monitorexit
    //   140: aload_2
    //   141: athrow
    //   142: astore 9
    //   144: ldc 8
    //   146: ldc 208
    //   148: iconst_1
    //   149: anewarray 120	java/lang/Object
    //   152: dup
    //   153: iconst_0
    //   154: aload 9
    //   156: aastore
    //   157: invokestatic 212	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   160: invokestatic 134	com/millennialmedia/android/MMLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   163: goto +94 -> 257
    //   166: aload 11
    //   168: invokeinterface 216 1 0
    //   173: lconst_0
    //   174: lcmp
    //   175: ifne +13 -> 188
    //   178: ldc 8
    //   180: ldc 218
    //   182: invokestatic 134	com/millennialmedia/android/MMLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   185: goto +72 -> 257
    //   188: aload_0
    //   189: aload 8
    //   191: aload 11
    //   193: invokespecial 220	com/millennialmedia/android/PreCacheWorker:handleContent	(Lcom/millennialmedia/android/DTOCachedVideo;Lorg/apache/http/HttpEntity;)V
    //   196: goto +61 -> 257
    //   199: ldc 2
    //   201: monitorenter
    //   202: iconst_0
    //   203: putstatic 183	com/millennialmedia/android/PreCacheWorker:busy	Z
    //   206: aload_0
    //   207: getfield 23	com/millennialmedia/android/PreCacheWorker:downloadedNewVideos	Z
    //   210: ifne +27 -> 237
    //   213: aload_0
    //   214: getfield 27	com/millennialmedia/android/PreCacheWorker:noVideosToCacheURL	Ljava/lang/String;
    //   217: invokestatic 94	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   220: ifne +17 -> 237
    //   223: aload_0
    //   224: getfield 25	com/millennialmedia/android/PreCacheWorker:cachedVideos	[Lcom/millennialmedia/android/DTOCachedVideo;
    //   227: ifnonnull +10 -> 237
    //   230: aload_0
    //   231: getfield 27	com/millennialmedia/android/PreCacheWorker:noVideosToCacheURL	Ljava/lang/String;
    //   234: invokestatic 145	com/millennialmedia/android/MMSDK$Event:logEvent	(Ljava/lang/String;)V
    //   237: ldc 2
    //   239: monitorexit
    //   240: aload_0
    //   241: monitorexit
    //   242: return
    //   243: astore_3
    //   244: ldc 2
    //   246: monitorexit
    //   247: aload_3
    //   248: athrow
    //   249: astore 4
    //   251: ldc 2
    //   253: monitorexit
    //   254: aload 4
    //   256: athrow
    //   257: iinc 7 1
    //   260: goto -237 -> 23
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	263	0	this	PreCacheWorker
    //   93	43	1	localObject1	Object
    //   137	4	2	localObject2	Object
    //   243	5	3	localObject3	Object
    //   249	6	4	localObject4	Object
    //   13	18	5	arrayOfDTOCachedVideo	DTOCachedVideo[]
    //   18	10	6	i	int
    //   21	237	7	j	int
    //   35	155	8	localDTOCachedVideo	DTOCachedVideo
    //   142	13	9	localException	java.lang.Exception
    //   52	18	10	localHttpResponse	org.apache.http.HttpResponse
    //   76	116	11	localHttpEntity	HttpEntity
    // Exception table:
    //   from	to	target	type
    //   2	20	93	finally
    //   30	37	93	finally
    //   37	54	93	finally
    //   59	66	93	finally
    //   69	78	93	finally
    //   83	90	93	finally
    //   144	163	93	finally
    //   166	185	93	finally
    //   188	196	93	finally
    //   94	97	137	finally
    //   135	137	137	finally
    //   199	202	137	finally
    //   247	249	137	finally
    //   254	257	137	finally
    //   37	54	142	java/lang/Exception
    //   59	66	142	java/lang/Exception
    //   69	78	142	java/lang/Exception
    //   97	132	243	finally
    //   132	135	243	finally
    //   244	247	243	finally
    //   202	237	249	finally
    //   237	240	249	finally
    //   251	254	249	finally
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.PreCacheWorker
 * JD-Core Version:    0.7.0.1
 */