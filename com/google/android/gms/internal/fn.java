package com.google.android.gms.internal;

import android.content.Context;
import java.util.concurrent.Future;

@ez
public class fn
  extends gg
{
  private final Object mw = new Object();
  private final fk sZ;
  private final fo tU;
  private Future<fz> tV;
  private final fd.a tm;
  private final fz.a tn;
  
  public fn(Context paramContext, u paramu, ai paramai, fz.a parama, fd.a parama1)
  {
    this(parama, parama1, new fo(paramContext, paramu, paramai, new go(), parama));
  }
  
  fn(fz.a parama, fd.a parama1, fo paramfo)
  {
    this.tn = parama;
    this.sZ = parama.vw;
    this.tm = parama1;
    this.tU = paramfo;
  }
  
  private fz r(int paramInt)
  {
    return new fz(this.tn.vv.tx, null, null, paramInt, null, null, this.sZ.orientation, this.sZ.qj, this.tn.vv.tA, false, null, null, null, null, null, this.sZ.tJ, this.tn.lH, this.sZ.tH, this.tn.vs, this.sZ.tM, this.sZ.tN, this.tn.vp, null);
  }
  
  /* Error */
  public void co()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 39	com/google/android/gms/internal/fn:mw	Ljava/lang/Object;
    //   4: astore 8
    //   6: aload 8
    //   8: monitorenter
    //   9: aload_0
    //   10: aload_0
    //   11: getfield 52	com/google/android/gms/internal/fn:tU	Lcom/google/android/gms/internal/fo;
    //   14: invokestatic 123	com/google/android/gms/internal/gi:submit	(Ljava/util/concurrent/Callable;)Ljava/util/concurrent/Future;
    //   17: putfield 125	com/google/android/gms/internal/fn:tV	Ljava/util/concurrent/Future;
    //   20: aload 8
    //   22: monitorexit
    //   23: aload_0
    //   24: getfield 125	com/google/android/gms/internal/fn:tV	Ljava/util/concurrent/Future;
    //   27: ldc2_w 126
    //   30: getstatic 133	java/util/concurrent/TimeUnit:MILLISECONDS	Ljava/util/concurrent/TimeUnit;
    //   33: invokeinterface 139 4 0
    //   38: checkcast 58	com/google/android/gms/internal/fz
    //   41: astore_3
    //   42: bipush 254
    //   44: istore_2
    //   45: aload_3
    //   46: ifnull +68 -> 114
    //   49: getstatic 145	com/google/android/gms/internal/gr:wC	Landroid/os/Handler;
    //   52: new 147	com/google/android/gms/internal/fn$1
    //   55: dup
    //   56: aload_0
    //   57: aload_3
    //   58: invokespecial 150	com/google/android/gms/internal/fn$1:<init>	(Lcom/google/android/gms/internal/fn;Lcom/google/android/gms/internal/fz;)V
    //   61: invokevirtual 156	android/os/Handler:post	(Ljava/lang/Runnable;)Z
    //   64: pop
    //   65: return
    //   66: astore 9
    //   68: aload 8
    //   70: monitorexit
    //   71: aload 9
    //   73: athrow
    //   74: astore 7
    //   76: ldc 158
    //   78: invokestatic 164	com/google/android/gms/internal/gs:W	(Ljava/lang/String;)V
    //   81: iconst_2
    //   82: istore_2
    //   83: aconst_null
    //   84: astore_3
    //   85: goto -40 -> 45
    //   88: astore 6
    //   90: aconst_null
    //   91: astore_3
    //   92: iconst_0
    //   93: istore_2
    //   94: goto -49 -> 45
    //   97: astore 5
    //   99: iconst_m1
    //   100: istore_2
    //   101: aconst_null
    //   102: astore_3
    //   103: goto -58 -> 45
    //   106: astore_1
    //   107: iconst_m1
    //   108: istore_2
    //   109: aconst_null
    //   110: astore_3
    //   111: goto -66 -> 45
    //   114: aload_0
    //   115: iload_2
    //   116: invokespecial 166	com/google/android/gms/internal/fn:r	(I)Lcom/google/android/gms/internal/fz;
    //   119: astore_3
    //   120: goto -71 -> 49
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	123	0	this	fn
    //   106	1	1	localCancellationException	java.util.concurrent.CancellationException
    //   44	72	2	i	int
    //   41	79	3	localfz	fz
    //   97	1	5	localInterruptedException	java.lang.InterruptedException
    //   88	1	6	localExecutionException	java.util.concurrent.ExecutionException
    //   74	1	7	localTimeoutException	java.util.concurrent.TimeoutException
    //   66	6	9	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   9	23	66	finally
    //   68	71	66	finally
    //   0	9	74	java/util/concurrent/TimeoutException
    //   23	42	74	java/util/concurrent/TimeoutException
    //   71	74	74	java/util/concurrent/TimeoutException
    //   0	9	88	java/util/concurrent/ExecutionException
    //   23	42	88	java/util/concurrent/ExecutionException
    //   71	74	88	java/util/concurrent/ExecutionException
    //   0	9	97	java/lang/InterruptedException
    //   23	42	97	java/lang/InterruptedException
    //   71	74	97	java/lang/InterruptedException
    //   0	9	106	java/util/concurrent/CancellationException
    //   23	42	106	java/util/concurrent/CancellationException
    //   71	74	106	java/util/concurrent/CancellationException
  }
  
  public void onStop()
  {
    synchronized (this.mw)
    {
      if (this.tV != null) {
        this.tV.cancel(true);
      }
      return;
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.fn
 * JD-Core Version:    0.7.0.1
 */