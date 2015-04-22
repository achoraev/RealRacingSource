package com.facebook.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import java.io.InputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class ImageDownloader
{
  private static final int CACHE_READ_QUEUE_MAX_CONCURRENT = 2;
  private static final int DOWNLOAD_QUEUE_MAX_CONCURRENT = 8;
  private static WorkQueue cacheReadQueue = new WorkQueue(2);
  private static WorkQueue downloadQueue = new WorkQueue(8);
  private static Handler handler;
  private static final Map<RequestKey, DownloaderContext> pendingRequests = new HashMap();
  
  public static boolean cancelRequest(ImageRequest paramImageRequest)
  {
    RequestKey localRequestKey = new RequestKey(paramImageRequest.getImageUri(), paramImageRequest.getCallerTag());
    synchronized (pendingRequests)
    {
      DownloaderContext localDownloaderContext = (DownloaderContext)pendingRequests.get(localRequestKey);
      boolean bool = false;
      if (localDownloaderContext != null)
      {
        bool = true;
        if (localDownloaderContext.workItem.cancel()) {
          pendingRequests.remove(localRequestKey);
        }
      }
      else
      {
        return bool;
      }
      localDownloaderContext.isCancelled = true;
    }
  }
  
  public static void clearCache(Context paramContext)
  {
    ImageResponseCache.clearCache(paramContext);
    UrlRedirectCache.clearCache(paramContext);
  }
  
  /* Error */
  private static void download(RequestKey paramRequestKey, Context paramContext)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aconst_null
    //   3: astore_3
    //   4: aconst_null
    //   5: astore 4
    //   7: iconst_1
    //   8: istore 5
    //   10: new 103	java/net/URL
    //   13: dup
    //   14: aload_0
    //   15: getfield 107	com/facebook/internal/ImageDownloader$RequestKey:uri	Ljava/net/URI;
    //   18: invokevirtual 113	java/net/URI:toString	()Ljava/lang/String;
    //   21: invokespecial 116	java/net/URL:<init>	(Ljava/lang/String;)V
    //   24: invokevirtual 120	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   27: checkcast 122	java/net/HttpURLConnection
    //   30: astore_2
    //   31: aload_2
    //   32: iconst_0
    //   33: invokevirtual 126	java/net/HttpURLConnection:setInstanceFollowRedirects	(Z)V
    //   36: aload_2
    //   37: invokevirtual 130	java/net/HttpURLConnection:getResponseCode	()I
    //   40: lookupswitch	default:+36->76, 200:+269->309, 301:+128->168, 302:+128->168
    //   77: invokevirtual 134	java/net/HttpURLConnection:getErrorStream	()Ljava/io/InputStream;
    //   80: astore_3
    //   81: new 136	java/io/InputStreamReader
    //   84: dup
    //   85: aload_3
    //   86: invokespecial 139	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   89: astore 17
    //   91: sipush 128
    //   94: newarray char
    //   96: astore 18
    //   98: new 141	java/lang/StringBuilder
    //   101: dup
    //   102: invokespecial 142	java/lang/StringBuilder:<init>	()V
    //   105: astore 19
    //   107: aload 17
    //   109: aload 18
    //   111: iconst_0
    //   112: aload 18
    //   114: arraylength
    //   115: invokevirtual 146	java/io/InputStreamReader:read	([CII)I
    //   118: istore 20
    //   120: iload 20
    //   122: ifle +205 -> 327
    //   125: aload 19
    //   127: aload 18
    //   129: iconst_0
    //   130: iload 20
    //   132: invokevirtual 150	java/lang/StringBuilder:append	([CII)Ljava/lang/StringBuilder;
    //   135: pop
    //   136: goto -29 -> 107
    //   139: astore 9
    //   141: aload 9
    //   143: astore 8
    //   145: aload_3
    //   146: invokestatic 156	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   149: aload_2
    //   150: invokestatic 160	com/facebook/internal/Utility:disconnectQuietly	(Ljava/net/URLConnection;)V
    //   153: iload 5
    //   155: ifeq +12 -> 167
    //   158: aload_0
    //   159: aload 8
    //   161: aload 4
    //   163: iconst_0
    //   164: invokestatic 164	com/facebook/internal/ImageDownloader:issueResponse	(Lcom/facebook/internal/ImageDownloader$RequestKey;Ljava/lang/Exception;Landroid/graphics/Bitmap;Z)V
    //   167: return
    //   168: iconst_0
    //   169: istore 5
    //   171: aload_2
    //   172: ldc 166
    //   174: invokevirtual 170	java/net/HttpURLConnection:getHeaderField	(Ljava/lang/String;)Ljava/lang/String;
    //   177: astore 10
    //   179: aload 10
    //   181: invokestatic 174	com/facebook/internal/Utility:isNullOrEmpty	(Ljava/lang/String;)Z
    //   184: istore 11
    //   186: aconst_null
    //   187: astore 4
    //   189: aconst_null
    //   190: astore 8
    //   192: iconst_0
    //   193: istore 5
    //   195: aconst_null
    //   196: astore_3
    //   197: iload 11
    //   199: ifne +99 -> 298
    //   202: new 109	java/net/URI
    //   205: dup
    //   206: aload 10
    //   208: invokespecial 175	java/net/URI:<init>	(Ljava/lang/String;)V
    //   211: astore 12
    //   213: aload_1
    //   214: aload_0
    //   215: getfield 107	com/facebook/internal/ImageDownloader$RequestKey:uri	Ljava/net/URI;
    //   218: aload 12
    //   220: invokestatic 179	com/facebook/internal/UrlRedirectCache:cacheUriRedirect	(Landroid/content/Context;Ljava/net/URI;Ljava/net/URI;)V
    //   223: aload_0
    //   224: invokestatic 183	com/facebook/internal/ImageDownloader:removePendingRequest	(Lcom/facebook/internal/ImageDownloader$RequestKey;)Lcom/facebook/internal/ImageDownloader$DownloaderContext;
    //   227: astore 13
    //   229: aconst_null
    //   230: astore 4
    //   232: aconst_null
    //   233: astore 8
    //   235: iconst_0
    //   236: istore 5
    //   238: aconst_null
    //   239: astore_3
    //   240: aload 13
    //   242: ifnull +56 -> 298
    //   245: aload 13
    //   247: getfield 88	com/facebook/internal/ImageDownloader$DownloaderContext:isCancelled	Z
    //   250: istore 14
    //   252: aconst_null
    //   253: astore 4
    //   255: aconst_null
    //   256: astore 8
    //   258: iconst_0
    //   259: istore 5
    //   261: aconst_null
    //   262: astore_3
    //   263: iload 14
    //   265: ifne +33 -> 298
    //   268: aload 13
    //   270: getfield 187	com/facebook/internal/ImageDownloader$DownloaderContext:request	Lcom/facebook/internal/ImageRequest;
    //   273: astore 15
    //   275: new 50	com/facebook/internal/ImageDownloader$RequestKey
    //   278: dup
    //   279: aload 12
    //   281: aload_0
    //   282: getfield 191	com/facebook/internal/ImageDownloader$RequestKey:tag	Ljava/lang/Object;
    //   285: invokespecial 63	com/facebook/internal/ImageDownloader$RequestKey:<init>	(Ljava/net/URI;Ljava/lang/Object;)V
    //   288: astore 16
    //   290: aload 15
    //   292: aload 16
    //   294: iconst_0
    //   295: invokestatic 195	com/facebook/internal/ImageDownloader:enqueueCacheRead	(Lcom/facebook/internal/ImageRequest;Lcom/facebook/internal/ImageDownloader$RequestKey;Z)V
    //   298: aload_3
    //   299: invokestatic 156	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   302: aload_2
    //   303: invokestatic 160	com/facebook/internal/Utility:disconnectQuietly	(Ljava/net/URLConnection;)V
    //   306: goto -153 -> 153
    //   309: aload_1
    //   310: aload_2
    //   311: invokestatic 199	com/facebook/internal/ImageResponseCache:interceptAndCacheImageStream	(Landroid/content/Context;Ljava/net/HttpURLConnection;)Ljava/io/InputStream;
    //   314: astore_3
    //   315: aload_3
    //   316: invokestatic 205	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;)Landroid/graphics/Bitmap;
    //   319: astore 4
    //   321: aconst_null
    //   322: astore 8
    //   324: goto -26 -> 298
    //   327: aload 17
    //   329: invokestatic 156	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   332: new 207	com/facebook/FacebookException
    //   335: dup
    //   336: aload 19
    //   338: invokevirtual 208	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   341: invokespecial 209	com/facebook/FacebookException:<init>	(Ljava/lang/String;)V
    //   344: astore 8
    //   346: aconst_null
    //   347: astore 4
    //   349: goto -51 -> 298
    //   352: astore 7
    //   354: aload 7
    //   356: astore 8
    //   358: aload_3
    //   359: invokestatic 156	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   362: aload_2
    //   363: invokestatic 160	com/facebook/internal/Utility:disconnectQuietly	(Ljava/net/URLConnection;)V
    //   366: aconst_null
    //   367: astore 4
    //   369: goto -216 -> 153
    //   372: astore 6
    //   374: aload_3
    //   375: invokestatic 156	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   378: aload_2
    //   379: invokestatic 160	com/facebook/internal/Utility:disconnectQuietly	(Ljava/net/URLConnection;)V
    //   382: aload 6
    //   384: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	385	0	paramRequestKey	RequestKey
    //   0	385	1	paramContext	Context
    //   1	378	2	localHttpURLConnection	java.net.HttpURLConnection
    //   3	372	3	localInputStream	InputStream
    //   5	363	4	localBitmap	Bitmap
    //   8	252	5	i	int
    //   372	11	6	localObject1	Object
    //   352	3	7	localURISyntaxException	java.net.URISyntaxException
    //   143	214	8	localObject2	Object
    //   139	3	9	localIOException	java.io.IOException
    //   177	30	10	str	java.lang.String
    //   184	14	11	bool1	boolean
    //   211	69	12	localURI	URI
    //   227	42	13	localDownloaderContext	DownloaderContext
    //   250	14	14	bool2	boolean
    //   273	18	15	localImageRequest	ImageRequest
    //   288	5	16	localRequestKey	RequestKey
    //   89	239	17	localInputStreamReader	java.io.InputStreamReader
    //   96	32	18	arrayOfChar	char[]
    //   105	232	19	localStringBuilder	java.lang.StringBuilder
    //   118	13	20	j	int
    // Exception table:
    //   from	to	target	type
    //   10	76	139	java/io/IOException
    //   76	107	139	java/io/IOException
    //   107	120	139	java/io/IOException
    //   125	136	139	java/io/IOException
    //   171	186	139	java/io/IOException
    //   202	229	139	java/io/IOException
    //   245	252	139	java/io/IOException
    //   268	298	139	java/io/IOException
    //   309	321	139	java/io/IOException
    //   327	346	139	java/io/IOException
    //   10	76	352	java/net/URISyntaxException
    //   76	107	352	java/net/URISyntaxException
    //   107	120	352	java/net/URISyntaxException
    //   125	136	352	java/net/URISyntaxException
    //   171	186	352	java/net/URISyntaxException
    //   202	229	352	java/net/URISyntaxException
    //   245	252	352	java/net/URISyntaxException
    //   268	298	352	java/net/URISyntaxException
    //   309	321	352	java/net/URISyntaxException
    //   327	346	352	java/net/URISyntaxException
    //   10	76	372	finally
    //   76	107	372	finally
    //   107	120	372	finally
    //   125	136	372	finally
    //   171	186	372	finally
    //   202	229	372	finally
    //   245	252	372	finally
    //   268	298	372	finally
    //   309	321	372	finally
    //   327	346	372	finally
  }
  
  public static void downloadAsync(ImageRequest paramImageRequest)
  {
    if (paramImageRequest == null) {
      return;
    }
    RequestKey localRequestKey = new RequestKey(paramImageRequest.getImageUri(), paramImageRequest.getCallerTag());
    for (;;)
    {
      synchronized (pendingRequests)
      {
        DownloaderContext localDownloaderContext = (DownloaderContext)pendingRequests.get(localRequestKey);
        if (localDownloaderContext != null)
        {
          localDownloaderContext.request = paramImageRequest;
          localDownloaderContext.isCancelled = false;
          localDownloaderContext.workItem.moveToFront();
          return;
        }
      }
      enqueueCacheRead(paramImageRequest, localRequestKey, paramImageRequest.isCachedRedirectAllowed());
    }
  }
  
  private static void enqueueCacheRead(ImageRequest paramImageRequest, RequestKey paramRequestKey, boolean paramBoolean)
  {
    enqueueRequest(paramImageRequest, paramRequestKey, cacheReadQueue, new CacheReadWorkItem(paramImageRequest.getContext(), paramRequestKey, paramBoolean));
  }
  
  private static void enqueueDownload(ImageRequest paramImageRequest, RequestKey paramRequestKey)
  {
    enqueueRequest(paramImageRequest, paramRequestKey, downloadQueue, new DownloadImageWorkItem(paramImageRequest.getContext(), paramRequestKey));
  }
  
  private static void enqueueRequest(ImageRequest paramImageRequest, RequestKey paramRequestKey, WorkQueue paramWorkQueue, Runnable paramRunnable)
  {
    synchronized (pendingRequests)
    {
      DownloaderContext localDownloaderContext = new DownloaderContext(null);
      localDownloaderContext.request = paramImageRequest;
      pendingRequests.put(paramRequestKey, localDownloaderContext);
      localDownloaderContext.workItem = paramWorkQueue.addActiveWorkItem(paramRunnable);
      return;
    }
  }
  
  private static Handler getHandler()
  {
    try
    {
      if (handler == null) {
        handler = new Handler(Looper.getMainLooper());
      }
      Handler localHandler = handler;
      return localHandler;
    }
    finally {}
  }
  
  private static void issueResponse(RequestKey paramRequestKey, final Exception paramException, final Bitmap paramBitmap, final boolean paramBoolean)
  {
    DownloaderContext localDownloaderContext = removePendingRequest(paramRequestKey);
    if ((localDownloaderContext != null) && (!localDownloaderContext.isCancelled))
    {
      ImageRequest localImageRequest = localDownloaderContext.request;
      final ImageRequest.Callback localCallback = localImageRequest.getCallback();
      if (localCallback != null) {
        getHandler().post(new Runnable()
        {
          public void run()
          {
            ImageResponse localImageResponse = new ImageResponse(this.val$request, paramException, paramBoolean, paramBitmap);
            localCallback.onCompleted(localImageResponse);
          }
        });
      }
    }
  }
  
  public static void prioritizeRequest(ImageRequest paramImageRequest)
  {
    RequestKey localRequestKey = new RequestKey(paramImageRequest.getImageUri(), paramImageRequest.getCallerTag());
    synchronized (pendingRequests)
    {
      DownloaderContext localDownloaderContext = (DownloaderContext)pendingRequests.get(localRequestKey);
      if (localDownloaderContext != null) {
        localDownloaderContext.workItem.moveToFront();
      }
      return;
    }
  }
  
  private static void readFromCache(RequestKey paramRequestKey, Context paramContext, boolean paramBoolean)
  {
    InputStream localInputStream = null;
    boolean bool = false;
    if (paramBoolean)
    {
      URI localURI = UrlRedirectCache.getRedirectedUri(paramContext, paramRequestKey.uri);
      localInputStream = null;
      bool = false;
      if (localURI != null)
      {
        localInputStream = ImageResponseCache.getCachedImageStream(localURI, paramContext);
        if (localInputStream == null) {
          break label81;
        }
        bool = true;
      }
    }
    if (!bool) {
      localInputStream = ImageResponseCache.getCachedImageStream(paramRequestKey.uri, paramContext);
    }
    if (localInputStream != null)
    {
      Bitmap localBitmap = BitmapFactory.decodeStream(localInputStream);
      Utility.closeQuietly(localInputStream);
      issueResponse(paramRequestKey, null, localBitmap, bool);
    }
    label81:
    DownloaderContext localDownloaderContext;
    do
    {
      return;
      bool = false;
      break;
      localDownloaderContext = removePendingRequest(paramRequestKey);
    } while ((localDownloaderContext == null) || (localDownloaderContext.isCancelled));
    enqueueDownload(localDownloaderContext.request, paramRequestKey);
  }
  
  private static DownloaderContext removePendingRequest(RequestKey paramRequestKey)
  {
    synchronized (pendingRequests)
    {
      DownloaderContext localDownloaderContext = (DownloaderContext)pendingRequests.remove(paramRequestKey);
      return localDownloaderContext;
    }
  }
  
  private static class CacheReadWorkItem
    implements Runnable
  {
    private boolean allowCachedRedirects;
    private Context context;
    private ImageDownloader.RequestKey key;
    
    CacheReadWorkItem(Context paramContext, ImageDownloader.RequestKey paramRequestKey, boolean paramBoolean)
    {
      this.context = paramContext;
      this.key = paramRequestKey;
      this.allowCachedRedirects = paramBoolean;
    }
    
    public void run()
    {
      ImageDownloader.readFromCache(this.key, this.context, this.allowCachedRedirects);
    }
  }
  
  private static class DownloadImageWorkItem
    implements Runnable
  {
    private Context context;
    private ImageDownloader.RequestKey key;
    
    DownloadImageWorkItem(Context paramContext, ImageDownloader.RequestKey paramRequestKey)
    {
      this.context = paramContext;
      this.key = paramRequestKey;
    }
    
    public void run()
    {
      ImageDownloader.download(this.key, this.context);
    }
  }
  
  private static class DownloaderContext
  {
    boolean isCancelled;
    ImageRequest request;
    WorkQueue.WorkItem workItem;
  }
  
  private static class RequestKey
  {
    private static final int HASH_MULTIPLIER = 37;
    private static final int HASH_SEED = 29;
    Object tag;
    URI uri;
    
    RequestKey(URI paramURI, Object paramObject)
    {
      this.uri = paramURI;
      this.tag = paramObject;
    }
    
    public boolean equals(Object paramObject)
    {
      boolean bool1 = false;
      if (paramObject != null)
      {
        boolean bool2 = paramObject instanceof RequestKey;
        bool1 = false;
        if (bool2)
        {
          RequestKey localRequestKey = (RequestKey)paramObject;
          if ((localRequestKey.uri != this.uri) || (localRequestKey.tag != this.tag)) {
            break label51;
          }
          bool1 = true;
        }
      }
      return bool1;
      label51:
      return false;
    }
    
    public int hashCode()
    {
      return 37 * (1073 + this.uri.hashCode()) + this.tag.hashCode();
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.facebook.internal.ImageDownloader
 * JD-Core Version:    0.7.0.1
 */