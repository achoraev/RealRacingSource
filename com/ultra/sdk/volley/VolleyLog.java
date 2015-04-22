package com.ultra.sdk.volley;

import android.os.SystemClock;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class VolleyLog
{
  public static final boolean DEBUG;
  public static String TAG = "Volley";
  
  private static String buildMessage(String paramString, Object... paramVarArgs)
  {
    String str1;
    StackTraceElement[] arrayOfStackTraceElement;
    String str2;
    if (paramVarArgs == null)
    {
      str1 = paramString;
      arrayOfStackTraceElement = new Throwable().fillInStackTrace().getStackTrace();
      str2 = "<unknown>";
    }
    label178:
    for (int i = 2;; i++)
    {
      if (i >= arrayOfStackTraceElement.length) {}
      for (;;)
      {
        Locale localLocale = Locale.US;
        Object[] arrayOfObject = new Object[3];
        arrayOfObject[0] = Long.valueOf(Thread.currentThread().getId());
        arrayOfObject[1] = str2;
        arrayOfObject[2] = str1;
        return String.format(localLocale, "[%d] %s: %s", arrayOfObject);
        str1 = String.format(Locale.US, paramString, paramVarArgs);
        break;
        if (arrayOfStackTraceElement[i].getClass().equals(VolleyLog.class)) {
          break label178;
        }
        String str3 = arrayOfStackTraceElement[i].getClassName();
        String str4 = str3.substring(1 + str3.lastIndexOf('.'));
        str2 = str4.substring(1 + str4.lastIndexOf('$')) + "." + arrayOfStackTraceElement[i].getMethodName();
      }
    }
  }
  
  public static void d(String paramString, Object... paramVarArgs) {}
  
  public static void e(String paramString, Object... paramVarArgs) {}
  
  public static void e(Throwable paramThrowable, String paramString, Object... paramVarArgs) {}
  
  public static void v(String paramString, Object... paramVarArgs) {}
  
  public static void wtf(String paramString, Object... paramVarArgs) {}
  
  public static void wtf(Throwable paramThrowable, String paramString, Object... paramVarArgs) {}
  
  static class MarkerLog
  {
    public static final boolean ENABLED;
    private static final long MIN_DURATION_FOR_LOGGING_MS;
    private boolean mFinished = false;
    private final List<Marker> mMarkers = new ArrayList();
    
    private long getTotalDuration()
    {
      if (this.mMarkers.size() == 0) {
        return 0L;
      }
      long l = ((Marker)this.mMarkers.get(0)).time;
      return ((Marker)this.mMarkers.get(-1 + this.mMarkers.size())).time - l;
    }
    
    public void add(String paramString, long paramLong)
    {
      try
      {
        if (this.mFinished) {
          throw new IllegalStateException("Marker added to finished log");
        }
      }
      finally {}
      this.mMarkers.add(new Marker(paramString, paramLong, SystemClock.elapsedRealtime()));
    }
    
    protected void finalize()
      throws Throwable
    {
      if (!this.mFinished)
      {
        finish("Request on the loose");
        VolleyLog.e("Marker log finalized without finish() - uncaught exit point for request", new Object[0]);
      }
    }
    
    /* Error */
    public void finish(String paramString)
    {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: iconst_1
      //   4: putfield 23	com/ultra/sdk/volley/VolleyLog$MarkerLog:mFinished	Z
      //   7: aload_0
      //   8: invokespecial 78	com/ultra/sdk/volley/VolleyLog$MarkerLog:getTotalDuration	()J
      //   11: lstore_3
      //   12: lload_3
      //   13: lconst_0
      //   14: lcmp
      //   15: ifgt +6 -> 21
      //   18: aload_0
      //   19: monitorexit
      //   20: return
      //   21: aload_0
      //   22: getfield 21	com/ultra/sdk/volley/VolleyLog$MarkerLog:mMarkers	Ljava/util/List;
      //   25: iconst_0
      //   26: invokeinterface 35 2 0
      //   31: checkcast 37	com/ultra/sdk/volley/VolleyLog$MarkerLog$Marker
      //   34: getfield 40	com/ultra/sdk/volley/VolleyLog$MarkerLog$Marker:time	J
      //   37: lstore 5
      //   39: iconst_2
      //   40: anewarray 4	java/lang/Object
      //   43: astore 7
      //   45: aload 7
      //   47: iconst_0
      //   48: lload_3
      //   49: invokestatic 84	java/lang/Long:valueOf	(J)Ljava/lang/Long;
      //   52: aastore
      //   53: aload 7
      //   55: iconst_1
      //   56: aload_1
      //   57: aastore
      //   58: ldc 86
      //   60: aload 7
      //   62: invokestatic 89	com/ultra/sdk/volley/VolleyLog:d	(Ljava/lang/String;[Ljava/lang/Object;)V
      //   65: aload_0
      //   66: getfield 21	com/ultra/sdk/volley/VolleyLog$MarkerLog:mMarkers	Ljava/util/List;
      //   69: invokeinterface 93 1 0
      //   74: astore 8
      //   76: aload 8
      //   78: invokeinterface 99 1 0
      //   83: ifeq -65 -> 18
      //   86: aload 8
      //   88: invokeinterface 103 1 0
      //   93: checkcast 37	com/ultra/sdk/volley/VolleyLog$MarkerLog$Marker
      //   96: astore 9
      //   98: aload 9
      //   100: getfield 40	com/ultra/sdk/volley/VolleyLog$MarkerLog$Marker:time	J
      //   103: lstore 10
      //   105: iconst_3
      //   106: anewarray 4	java/lang/Object
      //   109: astore 12
      //   111: aload 12
      //   113: iconst_0
      //   114: lload 10
      //   116: lload 5
      //   118: lsub
      //   119: invokestatic 84	java/lang/Long:valueOf	(J)Ljava/lang/Long;
      //   122: aastore
      //   123: aload 12
      //   125: iconst_1
      //   126: aload 9
      //   128: getfield 106	com/ultra/sdk/volley/VolleyLog$MarkerLog$Marker:thread	J
      //   131: invokestatic 84	java/lang/Long:valueOf	(J)Ljava/lang/Long;
      //   134: aastore
      //   135: aload 12
      //   137: iconst_2
      //   138: aload 9
      //   140: getfield 110	com/ultra/sdk/volley/VolleyLog$MarkerLog$Marker:name	Ljava/lang/String;
      //   143: aastore
      //   144: ldc 112
      //   146: aload 12
      //   148: invokestatic 89	com/ultra/sdk/volley/VolleyLog:d	(Ljava/lang/String;[Ljava/lang/Object;)V
      //   151: lload 10
      //   153: lstore 5
      //   155: goto -79 -> 76
      //   158: astore_2
      //   159: aload_0
      //   160: monitorexit
      //   161: aload_2
      //   162: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	163	0	this	MarkerLog
      //   0	163	1	paramString	String
      //   158	4	2	localObject	Object
      //   11	38	3	l1	long
      //   37	117	5	l2	long
      //   43	18	7	arrayOfObject1	Object[]
      //   74	13	8	localIterator	java.util.Iterator
      //   96	43	9	localMarker	Marker
      //   103	49	10	l3	long
      //   109	38	12	arrayOfObject2	Object[]
      // Exception table:
      //   from	to	target	type
      //   2	12	158	finally
      //   21	76	158	finally
      //   76	151	158	finally
    }
    
    private static class Marker
    {
      public final String name;
      public final long thread;
      public final long time;
      
      public Marker(String paramString, long paramLong1, long paramLong2)
      {
        this.name = paramString;
        this.thread = paramLong1;
        this.time = paramLong2;
      }
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ultra.sdk.volley.VolleyLog
 * JD-Core Version:    0.7.0.1
 */