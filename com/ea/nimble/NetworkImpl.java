package com.ea.nimble;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import java.io.ByteArrayOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class NetworkImpl
  extends Component
  implements INetwork, LogSource
{
  private static final String BACKUP_NETWORK_REACHABILITY_CHECK_URL = "http://www.ea.com";
  private static final int DETECTION_TIMEOUT = 30;
  private static final String MAIN_NETWORK_REACHABILITY_CHECK_URL = "http://cdn.skum.eamobile.com";
  private static final int[] PING_INTERVAL = { 5, 10, 30, 60 };
  private static final int QUICK_DETECTION_TIMEOUT = 5;
  private final int MAX_CONCURRENT_THREADS = 4;
  private ExecutorService m_asyncTaskManager;
  private ConnectivityReceiver m_connectivityReceiver;
  private NetworkConnection m_detectionConnection;
  private boolean m_isWifi;
  private DetectionState m_networkDetectionState;
  private int m_pingIndex;
  private List<NetworkConnection> m_queue;
  private Network.Status m_status;
  private Timer m_timer;
  private LinkedList<NetworkConnection> m_waitingToExecuteQueue;
  
  public NetworkImpl()
  {
    Log.Helper.LOGV(this, "constructor, start task manager and monitor the connectivity", new Object[0]);
    this.m_connectivityReceiver = null;
    this.m_status = Network.Status.UNKNOWN;
    this.m_detectionConnection = null;
    this.m_networkDetectionState = DetectionState.NONE;
    this.m_pingIndex = 0;
    this.m_queue = new ArrayList();
    startWork();
  }
  
  private void detect(boolean paramBoolean)
  {
    if (this.m_detectionConnection != null)
    {
      if (!paramBoolean) {
        return;
      }
      NetworkConnection localNetworkConnection = this.m_detectionConnection;
      this.m_detectionConnection = null;
      localNetworkConnection.cancel();
    }
    stopPing();
    if (reachabilityCheck()) {
      if (this.m_status != Network.Status.DEAD) {
        setStatus(Network.Status.OK);
      }
    }
    for (this.m_networkDetectionState = DetectionState.VERIFY_REACHABLE_MAIN;; this.m_networkDetectionState = DetectionState.VERIFY_UNREACHABLE_MAIN)
    {
      verifyReachability("http://cdn.skum.eamobile.com", 5.0D);
      return;
      if (this.m_status == Network.Status.UNKNOWN) {
        setStatus(Network.Status.NONE);
      }
    }
  }
  
  private void onReachabilityVerification(NetworkConnectionHandle paramNetworkConnectionHandle)
  {
    for (;;)
    {
      try
      {
        Exception localException = paramNetworkConnectionHandle.getResponse().getError();
        if (localException == null)
        {
          Log.Helper.LOGD(this, "network verified reachable.", new Object[0]);
          setStatus(Network.Status.OK);
          this.m_detectionConnection = null;
          return;
        }
        if (paramNetworkConnectionHandle != this.m_detectionConnection) {
          continue;
        }
        this.m_detectionConnection = null;
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = paramNetworkConnectionHandle.getResponse().getError();
        arrayOfObject[1] = this.m_networkDetectionState;
        Log.Helper.LOGD(this, "network verified unreachable, ERROR %s for detection state %s", arrayOfObject);
        if ((localException instanceof Error))
        {
          Error localError = (Error)localException;
          if ((localError.getDomain().equals("NimbleError")) && (localError.isError(Error.Code.NETWORK_OPERATION_CANCELLED))) {
            Log.Helper.LOGW(this, "Network detection verification connection get cancelled for unknown reason (maybe reasonable for Android)", new Object[0]);
          }
        }
        switch (2.$SwitchMap$com$ea$nimble$NetworkImpl$DetectionState[this.m_networkDetectionState.ordinal()])
        {
        case 1: 
          this.m_networkDetectionState = DetectionState.VERIFY_REACHABLE_BACKUP;
          verifyReachability("http://www.ea.com", 30.0D);
          break;
        case 2: 
          setStatus(Network.Status.NONE);
        }
      }
      finally {}
      continue;
      this.m_networkDetectionState = DetectionState.PING;
      if (this.m_status == Network.Status.DEAD)
      {
        startPing();
      }
      else
      {
        setStatus(Network.Status.DEAD);
        this.m_pingIndex = 0;
        startPing();
        continue;
        this.m_pingIndex = (1 + this.m_pingIndex);
        startPing();
      }
    }
  }
  
  private boolean reachabilityCheck()
  {
    this.m_isWifi = false;
    Context localContext = ApplicationEnvironment.getComponent().getApplicationContext();
    if (localContext == null) {}
    NetworkInfo localNetworkInfo;
    do
    {
      ConnectivityManager localConnectivityManager;
      do
      {
        return false;
        localConnectivityManager = (ConnectivityManager)localContext.getSystemService("connectivity");
      } while (localConnectivityManager == null);
      localNetworkInfo = localConnectivityManager.getActiveNetworkInfo();
    } while ((localNetworkInfo == null) || (!localNetworkInfo.isConnectedOrConnecting()));
    if ((localNetworkInfo.getType() == 1) || (localNetworkInfo.getType() == 9)) {
      this.m_isWifi = true;
    }
    return true;
  }
  
  private void registerNetworkListener()
  {
    if (this.m_connectivityReceiver == null)
    {
      Log.Helper.LOGD(this, "Register network reachability listener.", new Object[0]);
      this.m_connectivityReceiver = new ConnectivityReceiver(null);
      IntentFilter localIntentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
      ApplicationEnvironment.getComponent().getApplicationContext().registerReceiver(this.m_connectivityReceiver, localIntentFilter);
    }
  }
  
  private void setStatus(Network.Status paramStatus)
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = this.m_status;
    arrayOfObject[1] = paramStatus;
    Log.Helper.LOGI(this, "Status change %s -> %s", arrayOfObject);
    if (paramStatus != this.m_status)
    {
      this.m_status = paramStatus;
      Utility.sendBroadcast("nimble.notification.networkStatusChanged", null);
    }
  }
  
  private void startPing()
  {
    if (this.m_pingIndex >= PING_INTERVAL.length) {
      this.m_pingIndex = (-1 + PING_INTERVAL.length);
    }
    this.m_timer = new Timer(new TimerTask(null));
    this.m_timer.schedule(PING_INTERVAL[this.m_pingIndex], false);
  }
  
  /* Error */
  private void startWork()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 295	com/ea/nimble/NetworkImpl:m_asyncTaskManager	Ljava/util/concurrent/ExecutorService;
    //   6: astore_2
    //   7: aload_2
    //   8: ifnull +6 -> 14
    //   11: aload_0
    //   12: monitorexit
    //   13: return
    //   14: aload_0
    //   15: iconst_1
    //   16: invokespecial 97	com/ea/nimble/NetworkImpl:detect	(Z)V
    //   19: aload_0
    //   20: invokespecial 297	com/ea/nimble/NetworkImpl:registerNetworkListener	()V
    //   23: aload_0
    //   24: iconst_4
    //   25: invokestatic 303	java/util/concurrent/Executors:newFixedThreadPool	(I)Ljava/util/concurrent/ExecutorService;
    //   28: putfield 295	com/ea/nimble/NetworkImpl:m_asyncTaskManager	Ljava/util/concurrent/ExecutorService;
    //   31: aload_0
    //   32: getfield 305	com/ea/nimble/NetworkImpl:m_waitingToExecuteQueue	Ljava/util/LinkedList;
    //   35: ifnull -24 -> 11
    //   38: aload_0
    //   39: getfield 305	com/ea/nimble/NetworkImpl:m_waitingToExecuteQueue	Ljava/util/LinkedList;
    //   42: invokevirtual 310	java/util/LinkedList:isEmpty	()Z
    //   45: ifne -34 -> 11
    //   48: aload_0
    //   49: ldc_w 312
    //   52: iconst_0
    //   53: anewarray 57	java/lang/Object
    //   56: invokestatic 193	com/ea/nimble/Log$Helper:LOGW	(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;)V
    //   59: aload_0
    //   60: getfield 305	com/ea/nimble/NetworkImpl:m_waitingToExecuteQueue	Ljava/util/LinkedList;
    //   63: invokevirtual 310	java/util/LinkedList:isEmpty	()Z
    //   66: ifne -55 -> 11
    //   69: aload_0
    //   70: getfield 305	com/ea/nimble/NetworkImpl:m_waitingToExecuteQueue	Ljava/util/LinkedList;
    //   73: invokevirtual 316	java/util/LinkedList:poll	()Ljava/lang/Object;
    //   76: checkcast 115	com/ea/nimble/NetworkConnection
    //   79: astore_3
    //   80: aload_0
    //   81: new 318	java/lang/StringBuilder
    //   84: dup
    //   85: invokespecial 319	java/lang/StringBuilder:<init>	()V
    //   88: ldc_w 321
    //   91: invokevirtual 325	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   94: aload_3
    //   95: invokevirtual 329	com/ea/nimble/NetworkConnection:getRequest	()Lcom/ea/nimble/HttpRequest;
    //   98: getfield 335	com/ea/nimble/HttpRequest:url	Ljava/net/URL;
    //   101: invokevirtual 340	java/net/URL:toString	()Ljava/lang/String;
    //   104: invokevirtual 325	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   107: invokevirtual 341	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   110: iconst_0
    //   111: anewarray 57	java/lang/Object
    //   114: invokestatic 193	com/ea/nimble/Log$Helper:LOGW	(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;)V
    //   117: aload_0
    //   118: getfield 295	com/ea/nimble/NetworkImpl:m_asyncTaskManager	Ljava/util/concurrent/ExecutorService;
    //   121: aload_3
    //   122: invokeinterface 346 2 0
    //   127: goto -68 -> 59
    //   130: astore_1
    //   131: aload_0
    //   132: monitorexit
    //   133: aload_1
    //   134: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	135	0	this	NetworkImpl
    //   130	4	1	localObject	Object
    //   6	2	2	localExecutorService	ExecutorService
    //   79	43	3	localNetworkConnection	NetworkConnection
    // Exception table:
    //   from	to	target	type
    //   2	7	130	finally
    //   14	59	130	finally
    //   59	127	130	finally
  }
  
  private void stopPing()
  {
    if (this.m_timer != null)
    {
      this.m_timer.cancel();
      this.m_timer = null;
    }
  }
  
  private void stopWork()
  {
    label102:
    for (;;)
    {
      try
      {
        this.m_detectionConnection = null;
        stopPing();
        unregisterNetworkListener();
        ExecutorService localExecutorService = this.m_asyncTaskManager;
        if (localExecutorService == null) {
          return;
        }
        try
        {
          Iterator localIterator = this.m_asyncTaskManager.shutdownNow().iterator();
          if (!localIterator.hasNext()) {
            break label102;
          }
          ((NetworkConnection)localIterator.next()).suspend();
          continue;
          this.m_asyncTaskManager = null;
        }
        catch (InterruptedException localInterruptedException)
        {
          this.m_asyncTaskManager.shutdownNow();
          Thread.currentThread().interrupt();
        }
        continue;
        this.m_asyncTaskManager.awaitTermination(60L, TimeUnit.SECONDS);
      }
      finally {}
    }
  }
  
  private void unregisterNetworkListener()
  {
    if (this.m_connectivityReceiver != null) {}
    try
    {
      ApplicationEnvironment.getComponent().getApplicationContext().unregisterReceiver(this.m_connectivityReceiver);
      this.m_connectivityReceiver = null;
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      for (;;)
      {
        Log.Helper.LOGE(this, "Unable to unregister network reachability listener even it does exists", new Object[0]);
      }
    }
  }
  
  private void verifyReachability(String paramString, double paramDouble)
  {
    try
    {
      HttpRequest localHttpRequest = new HttpRequest(new URL(paramString));
      localHttpRequest.timeout = paramDouble;
      localHttpRequest.method = IHttpRequest.Method.GET;
      this.m_detectionConnection = new NetworkConnection(this, localHttpRequest);
      this.m_detectionConnection.setCompletionCallback(new NetworkConnectionCallback()
      {
        public void callback(NetworkConnectionHandle paramAnonymousNetworkConnectionHandle)
        {
          NetworkImpl.this.onReachabilityVerification(paramAnonymousNetworkConnectionHandle);
        }
      });
      if ((this.m_asyncTaskManager == null) || (this.m_asyncTaskManager.isShutdown()))
      {
        Log.Helper.LOGW(this, "AsyncTaskManager is not ready. Queueing networkconnection until AsyncTaskManager is started.", new Object[0]);
        if (this.m_waitingToExecuteQueue == null) {
          this.m_waitingToExecuteQueue = new LinkedList();
        }
        this.m_waitingToExecuteQueue.addFirst(this.m_detectionConnection);
        return;
      }
    }
    catch (MalformedURLException localMalformedURLException)
    {
      Log.Helper.LOGE(this, "Invalid url: " + paramString, new Object[0]);
      return;
    }
    this.m_asyncTaskManager.execute(this.m_detectionConnection);
  }
  
  public void cleanup()
  {
    stopWork();
    Log.Helper.LOGV(this, "cleanup", new Object[0]);
  }
  
  public void forceRedetectNetworkStatus()
  {
    try
    {
      detect(true);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public String getComponentId()
  {
    return "com.ea.nimble.network";
  }
  
  public String getLogSourceTitle()
  {
    return "Network";
  }
  
  public Network.Status getStatus()
  {
    return this.m_status;
  }
  
  public boolean isNetworkWifi()
  {
    return this.m_isWifi;
  }
  
  void removeConnection(NetworkConnection paramNetworkConnection)
  {
    try
    {
      this.m_queue.remove(paramNetworkConnection);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public void resume()
  {
    Log.Helper.LOGV(this, "resume", new Object[0]);
    try
    {
      detect(true);
      registerNetworkListener();
      return;
    }
    finally {}
  }
  
  public NetworkConnectionHandle sendDeleteRequest(URL paramURL, HashMap<String, String> paramHashMap, NetworkConnectionCallback paramNetworkConnectionCallback)
  {
    HttpRequest localHttpRequest = new HttpRequest(paramURL);
    localHttpRequest.method = IHttpRequest.Method.DELETE;
    localHttpRequest.headers = paramHashMap;
    return sendRequest(localHttpRequest, paramNetworkConnectionCallback);
  }
  
  public NetworkConnectionHandle sendGetRequest(URL paramURL, HashMap<String, String> paramHashMap, NetworkConnectionCallback paramNetworkConnectionCallback)
  {
    HttpRequest localHttpRequest = new HttpRequest(paramURL);
    localHttpRequest.method = IHttpRequest.Method.GET;
    localHttpRequest.headers = paramHashMap;
    return sendRequest(localHttpRequest, paramNetworkConnectionCallback);
  }
  
  public NetworkConnectionHandle sendPostRequest(URL paramURL, HashMap<String, String> paramHashMap, byte[] paramArrayOfByte, NetworkConnectionCallback paramNetworkConnectionCallback)
  {
    HttpRequest localHttpRequest = new HttpRequest(paramURL);
    localHttpRequest.method = IHttpRequest.Method.POST;
    localHttpRequest.headers = paramHashMap;
    try
    {
      localHttpRequest.data.write(paramArrayOfByte);
      return sendRequest(localHttpRequest, paramNetworkConnectionCallback);
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
    }
  }
  
  public NetworkConnectionHandle sendRequest(HttpRequest paramHttpRequest, NetworkConnectionCallback paramNetworkConnectionCallback)
  {
    if (paramHttpRequest.runInBackground) {}
    for (Object localObject1 = new BackgroundNetworkConnection(this, paramHttpRequest);; localObject1 = new NetworkConnection(this, paramHttpRequest))
    {
      ((NetworkConnection)localObject1).setCompletionCallback(paramNetworkConnectionCallback);
      if ((paramHttpRequest.url != null) && (Utility.validString(paramHttpRequest.url.toString()))) {
        break;
      }
      ((NetworkConnection)localObject1).finishWithError(new Error(Error.Code.INVALID_ARGUMENT, "Sending request without valid url"));
      return localObject1;
    }
    for (;;)
    {
      try
      {
        this.m_queue.add(localObject1);
        if ((this.m_asyncTaskManager != null) && (!this.m_asyncTaskManager.isShutdown())) {
          break;
        }
        if (this.m_asyncTaskManager != null)
        {
          Log.Helper.LOGW(this, "AsyncTaskManager shutdown. Queueing networkconnection until AsyncTaskManager is started.", new Object[0]);
          if (this.m_waitingToExecuteQueue == null) {
            this.m_waitingToExecuteQueue = new LinkedList();
          }
          this.m_waitingToExecuteQueue.add(localObject1);
          return localObject1;
        }
      }
      finally {}
      Log.Helper.LOGW(this, "AsyncTaskManager is not ready. Queueing networkconnection until AsyncTaskManager is started.", new Object[0]);
    }
    this.m_asyncTaskManager.execute((Runnable)localObject1);
    return localObject1;
  }
  
  public void setup()
  {
    Log.Helper.LOGV(this, "setup", new Object[0]);
    startWork();
  }
  
  public void suspend()
  {
    try
    {
      stopPing();
      unregisterNetworkListener();
      try
      {
        Iterator localIterator = new ArrayList(this.m_queue).iterator();
        while (localIterator.hasNext())
        {
          ((NetworkConnection)localIterator.next()).suspend();
          continue;
          localObject1 = finally;
        }
      }
      finally {}
    }
    finally {}
    Log.Helper.LOGV(this, "suspend", new Object[0]);
  }
  
  private class ConnectivityReceiver
    extends BroadcastReceiver
  {
    private ConnectivityReceiver() {}
    
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      Log.Helper.LOGD(this, "Network reachability changed!", new Object[0]);
      synchronized (NetworkImpl.this)
      {
        NetworkImpl.this.detect(true);
        return;
      }
    }
  }
  
  private static enum DetectionState
  {
    static
    {
      VERIFY_REACHABLE_BACKUP = new DetectionState("VERIFY_REACHABLE_BACKUP", 3);
      PING = new DetectionState("PING", 4);
      DetectionState[] arrayOfDetectionState = new DetectionState[5];
      arrayOfDetectionState[0] = NONE;
      arrayOfDetectionState[1] = VERIFY_REACHABLE_MAIN;
      arrayOfDetectionState[2] = VERIFY_UNREACHABLE_MAIN;
      arrayOfDetectionState[3] = VERIFY_REACHABLE_BACKUP;
      arrayOfDetectionState[4] = PING;
      $VALUES = arrayOfDetectionState;
    }
    
    private DetectionState() {}
  }
  
  private class TimerTask
    implements Runnable
  {
    private TimerTask() {}
    
    public void run()
    {
      synchronized (NetworkImpl.this)
      {
        NetworkImpl.access$302(NetworkImpl.this, null);
        NetworkImpl.this.verifyReachability("http://cdn.skum.eamobile.com", 30.0D);
        return;
      }
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ea.nimble.NetworkImpl
 * JD-Core Version:    0.7.0.1
 */