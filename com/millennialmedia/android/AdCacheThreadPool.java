package com.millennialmedia.android;

import android.content.Context;
import java.lang.ref.WeakReference;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

final class AdCacheThreadPool
{
  private static AdCacheThreadPool sharedThreadPool;
  private ThreadPoolExecutor executor;
  private PriorityBlockingQueue queue;
  
  private AdCacheThreadPool()
  {
    TimeUnit localTimeUnit = TimeUnit.SECONDS;
    PriorityBlockingQueue localPriorityBlockingQueue = new PriorityBlockingQueue(32);
    this.queue = localPriorityBlockingQueue;
    this.executor = new ThreadPoolExecutor(1, 2, 30L, localTimeUnit, localPriorityBlockingQueue);
  }
  
  static AdCacheThreadPool sharedThreadPool()
  {
    try
    {
      if (sharedThreadPool == null) {
        sharedThreadPool = new AdCacheThreadPool();
      }
      AdCacheThreadPool localAdCacheThreadPool = sharedThreadPool;
      return localAdCacheThreadPool;
    }
    finally {}
  }
  
  /* Error */
  boolean startDownloadTask(Context paramContext, String paramString, CachedAd paramCachedAd, AdCache.AdCacheTaskListener paramAdCacheTaskListener)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: ifnull +59 -> 62
    //   6: aload_3
    //   7: ifnull +55 -> 62
    //   10: new 44	com/millennialmedia/android/AdCacheThreadPool$AdCacheTask
    //   13: dup
    //   14: aload_0
    //   15: aload_1
    //   16: aload_2
    //   17: aload_3
    //   18: aload 4
    //   20: invokespecial 47	com/millennialmedia/android/AdCacheThreadPool$AdCacheTask:<init>	(Lcom/millennialmedia/android/AdCacheThreadPool;Landroid/content/Context;Ljava/lang/String;Lcom/millennialmedia/android/CachedAd;Lcom/millennialmedia/android/AdCache$AdCacheTaskListener;)V
    //   23: astore 6
    //   25: aload_0
    //   26: getfield 27	com/millennialmedia/android/AdCacheThreadPool:queue	Ljava/util/concurrent/PriorityBlockingQueue;
    //   29: aload 6
    //   31: invokevirtual 51	java/util/concurrent/PriorityBlockingQueue:contains	(Ljava/lang/Object;)Z
    //   34: ifne +28 -> 62
    //   37: aload_3
    //   38: aload_1
    //   39: invokevirtual 57	com/millennialmedia/android/CachedAd:isOnDisk	(Landroid/content/Context;)Z
    //   42: ifne +20 -> 62
    //   45: aload_0
    //   46: getfield 36	com/millennialmedia/android/AdCacheThreadPool:executor	Ljava/util/concurrent/ThreadPoolExecutor;
    //   49: aload 6
    //   51: invokevirtual 61	java/util/concurrent/ThreadPoolExecutor:execute	(Ljava/lang/Runnable;)V
    //   54: iconst_1
    //   55: istore 5
    //   57: aload_0
    //   58: monitorexit
    //   59: iload 5
    //   61: ireturn
    //   62: iconst_0
    //   63: istore 5
    //   65: goto -8 -> 57
    //   68: astore 7
    //   70: aload_0
    //   71: monitorexit
    //   72: aload 7
    //   74: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	75	0	this	AdCacheThreadPool
    //   0	75	1	paramContext	Context
    //   0	75	2	paramString	String
    //   0	75	3	paramCachedAd	CachedAd
    //   0	75	4	paramAdCacheTaskListener	AdCache.AdCacheTaskListener
    //   55	9	5	bool	boolean
    //   23	27	6	localAdCacheTask	AdCacheTask
    //   68	5	7	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   10	54	68	finally
  }
  
  private class AdCacheTask
    implements Runnable, Comparable<AdCacheTask>
  {
    private CachedAd ad;
    private String adName;
    private WeakReference<Context> contextRef;
    private WeakReference<AdCache.AdCacheTaskListener> listenerRef;
    
    AdCacheTask(Context paramContext, String paramString, CachedAd paramCachedAd, AdCache.AdCacheTaskListener paramAdCacheTaskListener)
    {
      this.contextRef = new WeakReference(paramContext.getApplicationContext());
      this.adName = paramString;
      this.ad = paramCachedAd;
      if (paramAdCacheTaskListener != null) {
        this.listenerRef = new WeakReference(paramAdCacheTaskListener);
      }
    }
    
    public int compareTo(AdCacheTask paramAdCacheTask)
    {
      return this.ad.downloadPriority - paramAdCacheTask.ad.downloadPriority;
    }
    
    public boolean equals(Object paramObject)
    {
      if (this == paramObject) {
        return true;
      }
      if (!(paramObject instanceof AdCacheTask)) {
        return false;
      }
      AdCacheTask localAdCacheTask = (AdCacheTask)paramObject;
      return this.ad.equals(localAdCacheTask.ad);
    }
    
    public void run()
    {
      WeakReference localWeakReference = this.listenerRef;
      AdCache.AdCacheTaskListener localAdCacheTaskListener = null;
      if (localWeakReference != null) {
        localAdCacheTaskListener = (AdCache.AdCacheTaskListener)this.listenerRef.get();
      }
      if (localAdCacheTaskListener != null) {
        localAdCacheTaskListener.downloadStart(this.ad);
      }
      HandShake.sharedHandShake((Context)this.contextRef.get()).lockAdTypeDownload(this.adName);
      boolean bool1 = this.ad.download((Context)this.contextRef.get());
      HandShake.sharedHandShake((Context)this.contextRef.get()).unlockAdTypeDownload(this.adName);
      if (!bool1)
      {
        String str1 = AdCache.getIncompleteDownload((Context)this.contextRef.get(), this.adName);
        if ((str1 != null) && (this.ad.getId().equals(str1)))
        {
          this.ad.delete((Context)this.contextRef.get());
          AdCache.setIncompleteDownload((Context)this.contextRef.get(), this.adName, null);
        }
      }
      for (;;)
      {
        if (localAdCacheTaskListener != null) {
          localAdCacheTaskListener.downloadCompleted(this.ad, bool1);
        }
        return;
        Context localContext = (Context)this.contextRef.get();
        String str2 = this.adName;
        boolean bool2 = this.ad.downloadAllOrNothing;
        String str3 = null;
        if (!bool2) {
          str3 = this.ad.getId();
        }
        AdCache.setIncompleteDownload(localContext, str2, str3);
        continue;
        AdCache.setIncompleteDownload((Context)this.contextRef.get(), this.adName, null);
      }
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.AdCacheThreadPool
 * JD-Core Version:    0.7.0.1
 */