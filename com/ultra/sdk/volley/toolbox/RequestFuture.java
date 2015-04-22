package com.ultra.sdk.volley.toolbox;

import com.ultra.sdk.volley.Request;
import com.ultra.sdk.volley.Response.ErrorListener;
import com.ultra.sdk.volley.Response.Listener;
import com.ultra.sdk.volley.VolleyError;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class RequestFuture<T>
  implements Future<T>, Response.Listener<T>, Response.ErrorListener
{
  private VolleyError mException;
  private Request<?> mRequest;
  private T mResult;
  private boolean mResultReceived = false;
  
  private T doGet(Long paramLong)
    throws InterruptedException, ExecutionException, TimeoutException
  {
    try
    {
      if (this.mException != null) {
        throw new ExecutionException(this.mException);
      }
    }
    finally {}
    if (this.mResultReceived) {}
    for (Object localObject2 = this.mResult;; localObject2 = this.mResult)
    {
      return localObject2;
      if (paramLong == null) {
        wait(0L);
      }
      while (this.mException != null)
      {
        throw new ExecutionException(this.mException);
        if (paramLong.longValue() > 0L) {
          wait(paramLong.longValue());
        }
      }
      if (!this.mResultReceived) {
        throw new TimeoutException();
      }
    }
  }
  
  public static <E> RequestFuture<E> newFuture()
  {
    return new RequestFuture();
  }
  
  /* Error */
  public boolean cancel(boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 60	com/ultra/sdk/volley/toolbox/RequestFuture:mRequest	Lcom/ultra/sdk/volley/Request;
    //   6: astore_3
    //   7: iconst_0
    //   8: istore 4
    //   10: aload_3
    //   11: ifnonnull +8 -> 19
    //   14: aload_0
    //   15: monitorexit
    //   16: iload 4
    //   18: ireturn
    //   19: aload_0
    //   20: invokevirtual 64	com/ultra/sdk/volley/toolbox/RequestFuture:isDone	()Z
    //   23: istore 5
    //   25: iconst_0
    //   26: istore 4
    //   28: iload 5
    //   30: ifne -16 -> 14
    //   33: aload_0
    //   34: getfield 60	com/ultra/sdk/volley/toolbox/RequestFuture:mRequest	Lcom/ultra/sdk/volley/Request;
    //   37: invokevirtual 68	com/ultra/sdk/volley/Request:cancel	()V
    //   40: iconst_1
    //   41: istore 4
    //   43: goto -29 -> 14
    //   46: astore_2
    //   47: aload_0
    //   48: monitorexit
    //   49: aload_2
    //   50: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	51	0	this	RequestFuture
    //   0	51	1	paramBoolean	boolean
    //   46	4	2	localObject	Object
    //   6	5	3	localRequest	Request
    //   8	34	4	bool1	boolean
    //   23	6	5	bool2	boolean
    // Exception table:
    //   from	to	target	type
    //   2	7	46	finally
    //   19	25	46	finally
    //   33	40	46	finally
  }
  
  public T get()
    throws InterruptedException, ExecutionException
  {
    try
    {
      Object localObject = doGet(null);
      return localObject;
    }
    catch (TimeoutException localTimeoutException)
    {
      throw new AssertionError(localTimeoutException);
    }
  }
  
  public T get(long paramLong, TimeUnit paramTimeUnit)
    throws InterruptedException, ExecutionException, TimeoutException
  {
    return doGet(Long.valueOf(TimeUnit.MILLISECONDS.convert(paramLong, paramTimeUnit)));
  }
  
  public boolean isCancelled()
  {
    if (this.mRequest == null) {
      return false;
    }
    return this.mRequest.isCanceled();
  }
  
  /* Error */
  public boolean isDone()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 27	com/ultra/sdk/volley/toolbox/RequestFuture:mResultReceived	Z
    //   6: ifne +25 -> 31
    //   9: aload_0
    //   10: getfield 37	com/ultra/sdk/volley/toolbox/RequestFuture:mException	Lcom/ultra/sdk/volley/VolleyError;
    //   13: ifnonnull +18 -> 31
    //   16: aload_0
    //   17: invokevirtual 98	com/ultra/sdk/volley/toolbox/RequestFuture:isCancelled	()Z
    //   20: istore_3
    //   21: iload_3
    //   22: ifne +9 -> 31
    //   25: iconst_0
    //   26: istore_2
    //   27: aload_0
    //   28: monitorexit
    //   29: iload_2
    //   30: ireturn
    //   31: iconst_1
    //   32: istore_2
    //   33: goto -6 -> 27
    //   36: astore_1
    //   37: aload_0
    //   38: monitorexit
    //   39: aload_1
    //   40: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	41	0	this	RequestFuture
    //   36	4	1	localObject	Object
    //   26	7	2	bool1	boolean
    //   20	2	3	bool2	boolean
    // Exception table:
    //   from	to	target	type
    //   2	21	36	finally
  }
  
  public void onErrorResponse(VolleyError paramVolleyError)
  {
    try
    {
      this.mException = paramVolleyError;
      notifyAll();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public void onResponse(T paramT)
  {
    try
    {
      this.mResultReceived = true;
      this.mResult = paramT;
      notifyAll();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public void setRequest(Request<?> paramRequest)
  {
    this.mRequest = paramRequest;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ultra.sdk.volley.toolbox.RequestFuture
 * JD-Core Version:    0.7.0.1
 */