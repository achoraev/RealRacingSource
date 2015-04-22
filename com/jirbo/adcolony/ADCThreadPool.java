package com.jirbo.adcolony;

import java.util.ArrayList;
import java.util.Iterator;

class ADCThreadPool
{
  static ArrayList<ReusableThread> all_threads = new ArrayList();
  static ArrayList<ReusableThread> idle_threads;
  static String mutex = new String("mutex");
  static ArrayList<Runnable> pending_runnables = new ArrayList();
  static volatile boolean shutting_down;
  static ArrayList<Runnable> working_runnables = new ArrayList();
  
  static
  {
    idle_threads = new ArrayList();
  }
  
  static void reset()
  {
    
    synchronized (mutex)
    {
      pending_runnables.clear();
      start();
      return;
    }
  }
  
  static void run(Runnable paramRunnable)
  {
    ReusableThread localReusableThread1;
    ReusableThread localReusableThread2;
    synchronized (mutex)
    {
      if (shutting_down)
      {
        pending_runnables.add(paramRunnable);
        return;
      }
      int i = idle_threads.size();
      localReusableThread1 = null;
      if (i > 0) {
        localReusableThread1 = (ReusableThread)idle_threads.remove(i - 1);
      }
      if (localReusableThread1 != null) {
        break label112;
      }
      localReusableThread2 = new ReusableThread();
    }
    synchronized (mutex)
    {
      all_threads.add(localReusableThread2);
      localReusableThread2.target = paramRunnable;
      localReusableThread2.start();
      return;
      localObject1 = finally;
      throw localObject1;
    }
    try
    {
      label112:
      localReusableThread1.target = paramRunnable;
      localReusableThread1.notify();
      return;
    }
    finally {}
  }
  
  static void start()
  {
    synchronized (mutex)
    {
      shutting_down = false;
      working_runnables.clear();
      working_runnables.addAll(pending_runnables);
      pending_runnables.clear();
      all_threads.clear();
      Iterator localIterator = working_runnables.iterator();
      if (localIterator.hasNext()) {
        run((Runnable)localIterator.next());
      }
    }
  }
  
  static void stop()
  {
    synchronized (mutex)
    {
      shutting_down = true;
      Iterator localIterator = idle_threads.iterator();
      while (localIterator.hasNext()) {
        synchronized ((ReusableThread)localIterator.next())
        {
          ???.notify();
        }
      }
    }
    synchronized (mutex)
    {
      idle_threads.clear();
      return;
    }
  }
  
  static class ReusableThread
    extends Thread
  {
    Runnable target;
    
    /* Error */
    public void run()
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 17	com/jirbo/adcolony/ADCThreadPool$ReusableThread:target	Ljava/lang/Runnable;
      //   4: ifnull +17 -> 21
      //   7: aload_0
      //   8: getfield 17	com/jirbo/adcolony/ADCThreadPool$ReusableThread:target	Ljava/lang/Runnable;
      //   11: invokeinterface 21 1 0
      //   16: aload_0
      //   17: aconst_null
      //   18: putfield 17	com/jirbo/adcolony/ADCThreadPool$ReusableThread:target	Ljava/lang/Runnable;
      //   21: getstatic 27	com/jirbo/adcolony/ADCThreadPool:shutting_down	Z
      //   24: ifeq +42 -> 66
      //   27: return
      //   28: astore 6
      //   30: ldc 29
      //   32: invokestatic 35	com/jirbo/adcolony/ADC:log_error	(Ljava/lang/String;)V
      //   35: new 37	java/lang/StringBuilder
      //   38: dup
      //   39: invokespecial 38	java/lang/StringBuilder:<init>	()V
      //   42: aload 6
      //   44: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   47: ldc 44
      //   49: invokevirtual 47	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   52: invokevirtual 51	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   55: invokestatic 35	com/jirbo/adcolony/ADC:log_error	(Ljava/lang/String;)V
      //   58: aload 6
      //   60: invokevirtual 54	java/lang/RuntimeException:printStackTrace	()V
      //   63: goto -47 -> 16
      //   66: aload_0
      //   67: monitorenter
      //   68: getstatic 58	com/jirbo/adcolony/ADCThreadPool:mutex	Ljava/lang/String;
      //   71: astore_2
      //   72: aload_2
      //   73: monitorenter
      //   74: getstatic 62	com/jirbo/adcolony/ADCThreadPool:idle_threads	Ljava/util/ArrayList;
      //   77: aload_0
      //   78: invokevirtual 68	java/util/ArrayList:add	(Ljava/lang/Object;)Z
      //   81: pop
      //   82: aload_2
      //   83: monitorexit
      //   84: aload_0
      //   85: invokevirtual 73	java/lang/Object:wait	()V
      //   88: aload_0
      //   89: monitorexit
      //   90: goto -90 -> 0
      //   93: astore_1
      //   94: aload_0
      //   95: monitorexit
      //   96: aload_1
      //   97: athrow
      //   98: astore_3
      //   99: aload_2
      //   100: monitorexit
      //   101: aload_3
      //   102: athrow
      //   103: astore 5
      //   105: goto -17 -> 88
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	108	0	this	ReusableThread
      //   93	4	1	localObject1	Object
      //   98	4	3	localObject2	Object
      //   103	1	5	localInterruptedException	java.lang.InterruptedException
      //   28	31	6	localRuntimeException	java.lang.RuntimeException
      // Exception table:
      //   from	to	target	type
      //   7	16	28	java/lang/RuntimeException
      //   68	74	93	finally
      //   84	88	93	finally
      //   88	90	93	finally
      //   94	96	93	finally
      //   101	103	93	finally
      //   74	84	98	finally
      //   99	101	98	finally
      //   84	88	103	java/lang/InterruptedException
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.jirbo.adcolony.ADCThreadPool
 * JD-Core Version:    0.7.0.1
 */