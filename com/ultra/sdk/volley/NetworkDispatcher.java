package com.ultra.sdk.volley;

import java.util.concurrent.BlockingQueue;

public class NetworkDispatcher
  extends Thread
{
  private final Cache mCache;
  private final ResponseDelivery mDelivery;
  private final Network mNetwork;
  private final BlockingQueue<Request> mQueue;
  private volatile boolean mQuit = false;
  
  public NetworkDispatcher(BlockingQueue<Request> paramBlockingQueue, Network paramNetwork, Cache paramCache, ResponseDelivery paramResponseDelivery)
  {
    this.mQueue = paramBlockingQueue;
    this.mNetwork = paramNetwork;
    this.mCache = paramCache;
    this.mDelivery = paramResponseDelivery;
  }
  
  private void parseAndDeliverNetworkError(Request<?> paramRequest, VolleyError paramVolleyError)
  {
    VolleyError localVolleyError = paramRequest.parseNetworkError(paramVolleyError);
    this.mDelivery.postError(paramRequest, localVolleyError);
  }
  
  public void quit()
  {
    this.mQuit = true;
    interrupt();
  }
  
  /* Error */
  public void run()
  {
    // Byte code:
    //   0: bipush 10
    //   2: invokestatic 60	android/os/Process:setThreadPriority	(I)V
    //   5: aload_0
    //   6: getfield 24	com/ultra/sdk/volley/NetworkDispatcher:mQueue	Ljava/util/concurrent/BlockingQueue;
    //   9: invokeinterface 66 1 0
    //   14: checkcast 34	com/ultra/sdk/volley/Request
    //   17: astore_2
    //   18: aload_2
    //   19: ldc 68
    //   21: invokevirtual 72	com/ultra/sdk/volley/Request:addMarker	(Ljava/lang/String;)V
    //   24: aload_2
    //   25: invokevirtual 76	com/ultra/sdk/volley/Request:isCanceled	()Z
    //   28: ifeq +33 -> 61
    //   31: aload_2
    //   32: ldc 78
    //   34: invokevirtual 81	com/ultra/sdk/volley/Request:finish	(Ljava/lang/String;)V
    //   37: goto -32 -> 5
    //   40: astore 5
    //   42: aload_0
    //   43: aload_2
    //   44: aload 5
    //   46: invokespecial 83	com/ultra/sdk/volley/NetworkDispatcher:parseAndDeliverNetworkError	(Lcom/ultra/sdk/volley/Request;Lcom/ultra/sdk/volley/VolleyError;)V
    //   49: goto -44 -> 5
    //   52: astore_1
    //   53: aload_0
    //   54: getfield 22	com/ultra/sdk/volley/NetworkDispatcher:mQuit	Z
    //   57: ifeq -52 -> 5
    //   60: return
    //   61: getstatic 89	android/os/Build$VERSION:SDK_INT	I
    //   64: bipush 14
    //   66: if_icmplt +10 -> 76
    //   69: aload_2
    //   70: invokevirtual 93	com/ultra/sdk/volley/Request:getTrafficStatsTag	()I
    //   73: invokestatic 98	android/net/TrafficStats:setThreadStatsTag	(I)V
    //   76: aload_0
    //   77: getfield 26	com/ultra/sdk/volley/NetworkDispatcher:mNetwork	Lcom/ultra/sdk/volley/Network;
    //   80: aload_2
    //   81: invokeinterface 104 2 0
    //   86: astore 6
    //   88: aload_2
    //   89: ldc 106
    //   91: invokevirtual 72	com/ultra/sdk/volley/Request:addMarker	(Ljava/lang/String;)V
    //   94: aload 6
    //   96: getfield 111	com/ultra/sdk/volley/NetworkResponse:notModified	Z
    //   99: ifeq +63 -> 162
    //   102: aload_2
    //   103: invokevirtual 114	com/ultra/sdk/volley/Request:hasHadResponseDelivered	()Z
    //   106: ifeq +56 -> 162
    //   109: aload_2
    //   110: ldc 116
    //   112: invokevirtual 81	com/ultra/sdk/volley/Request:finish	(Ljava/lang/String;)V
    //   115: goto -110 -> 5
    //   118: astore_3
    //   119: iconst_1
    //   120: anewarray 118	java/lang/Object
    //   123: astore 4
    //   125: aload 4
    //   127: iconst_0
    //   128: aload_3
    //   129: invokevirtual 122	java/lang/Exception:toString	()Ljava/lang/String;
    //   132: aastore
    //   133: aload_3
    //   134: ldc 124
    //   136: aload 4
    //   138: invokestatic 130	com/ultra/sdk/volley/VolleyLog:e	(Ljava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)V
    //   141: aload_0
    //   142: getfield 30	com/ultra/sdk/volley/NetworkDispatcher:mDelivery	Lcom/ultra/sdk/volley/ResponseDelivery;
    //   145: aload_2
    //   146: new 52	com/ultra/sdk/volley/VolleyError
    //   149: dup
    //   150: aload_3
    //   151: invokespecial 133	com/ultra/sdk/volley/VolleyError:<init>	(Ljava/lang/Throwable;)V
    //   154: invokeinterface 43 3 0
    //   159: goto -154 -> 5
    //   162: aload_2
    //   163: aload 6
    //   165: invokevirtual 137	com/ultra/sdk/volley/Request:parseNetworkResponse	(Lcom/ultra/sdk/volley/NetworkResponse;)Lcom/ultra/sdk/volley/Response;
    //   168: astore 7
    //   170: aload_2
    //   171: ldc 139
    //   173: invokevirtual 72	com/ultra/sdk/volley/Request:addMarker	(Ljava/lang/String;)V
    //   176: aload_2
    //   177: invokevirtual 142	com/ultra/sdk/volley/Request:shouldCache	()Z
    //   180: ifeq +35 -> 215
    //   183: aload 7
    //   185: getfield 148	com/ultra/sdk/volley/Response:cacheEntry	Lcom/ultra/sdk/volley/Cache$Entry;
    //   188: ifnull +27 -> 215
    //   191: aload_0
    //   192: getfield 28	com/ultra/sdk/volley/NetworkDispatcher:mCache	Lcom/ultra/sdk/volley/Cache;
    //   195: aload_2
    //   196: invokevirtual 151	com/ultra/sdk/volley/Request:getCacheKey	()Ljava/lang/String;
    //   199: aload 7
    //   201: getfield 148	com/ultra/sdk/volley/Response:cacheEntry	Lcom/ultra/sdk/volley/Cache$Entry;
    //   204: invokeinterface 157 3 0
    //   209: aload_2
    //   210: ldc 159
    //   212: invokevirtual 72	com/ultra/sdk/volley/Request:addMarker	(Ljava/lang/String;)V
    //   215: aload_2
    //   216: invokevirtual 162	com/ultra/sdk/volley/Request:markDelivered	()V
    //   219: aload_0
    //   220: getfield 30	com/ultra/sdk/volley/NetworkDispatcher:mDelivery	Lcom/ultra/sdk/volley/ResponseDelivery;
    //   223: aload_2
    //   224: aload 7
    //   226: invokeinterface 166 3 0
    //   231: goto -226 -> 5
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	234	0	this	NetworkDispatcher
    //   52	1	1	localInterruptedException	java.lang.InterruptedException
    //   17	207	2	localRequest	Request
    //   118	33	3	localException	java.lang.Exception
    //   123	14	4	arrayOfObject	java.lang.Object[]
    //   40	5	5	localVolleyError	VolleyError
    //   86	78	6	localNetworkResponse	NetworkResponse
    //   168	57	7	localResponse	Response
    // Exception table:
    //   from	to	target	type
    //   18	37	40	com/ultra/sdk/volley/VolleyError
    //   61	76	40	com/ultra/sdk/volley/VolleyError
    //   76	115	40	com/ultra/sdk/volley/VolleyError
    //   162	215	40	com/ultra/sdk/volley/VolleyError
    //   215	231	40	com/ultra/sdk/volley/VolleyError
    //   5	18	52	java/lang/InterruptedException
    //   18	37	118	java/lang/Exception
    //   61	76	118	java/lang/Exception
    //   76	115	118	java/lang/Exception
    //   162	215	118	java/lang/Exception
    //   215	231	118	java/lang/Exception
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ultra.sdk.volley.NetworkDispatcher
 * JD-Core Version:    0.7.0.1
 */