package com.google.android.gms.analytics;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;

class q
  extends ae
{
  private static final Object yc = new Object();
  private static q yo;
  private Context mContext;
  private Handler mHandler;
  private d yd;
  private volatile f ye;
  private int yf = 1800;
  private boolean yg = true;
  private boolean yh;
  private String yi;
  private boolean yj = true;
  private boolean yk = true;
  private e yl = new e()
  {
    public void z(boolean paramAnonymousBoolean)
    {
      q.this.a(paramAnonymousBoolean, q.a(q.this));
    }
  };
  private p ym;
  private boolean yn = false;
  
  public static q dZ()
  {
    if (yo == null) {
      yo = new q();
    }
    return yo;
  }
  
  private void ea()
  {
    this.ym = new p(this);
    this.ym.z(this.mContext);
  }
  
  private void eb()
  {
    this.mHandler = new Handler(this.mContext.getMainLooper(), new Handler.Callback()
    {
      public boolean handleMessage(Message paramAnonymousMessage)
      {
        if ((1 == paramAnonymousMessage.what) && (q.ee().equals(paramAnonymousMessage.obj)))
        {
          t.ep().B(true);
          q.this.dispatchLocalHits();
          t.ep().B(false);
          if ((q.b(q.this) > 0) && (!q.c(q.this))) {
            q.d(q.this).sendMessageDelayed(q.d(q.this).obtainMessage(1, q.ee()), 1000 * q.b(q.this));
          }
        }
        return true;
      }
    });
    if (this.yf > 0) {
      this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(1, yc), 1000 * this.yf);
    }
  }
  
  void A(boolean paramBoolean)
  {
    try
    {
      a(this.yn, paramBoolean);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  /* Error */
  void a(Context paramContext, f paramf)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 81	com/google/android/gms/analytics/q:mContext	Landroid/content/Context;
    //   6: astore 4
    //   8: aload 4
    //   10: ifnull +6 -> 16
    //   13: aload_0
    //   14: monitorexit
    //   15: return
    //   16: aload_0
    //   17: aload_1
    //   18: invokevirtual 120	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   21: putfield 81	com/google/android/gms/analytics/q:mContext	Landroid/content/Context;
    //   24: aload_0
    //   25: getfield 122	com/google/android/gms/analytics/q:ye	Lcom/google/android/gms/analytics/f;
    //   28: ifnonnull -15 -> 13
    //   31: aload_0
    //   32: aload_2
    //   33: putfield 122	com/google/android/gms/analytics/q:ye	Lcom/google/android/gms/analytics/f;
    //   36: aload_0
    //   37: getfield 44	com/google/android/gms/analytics/q:yg	Z
    //   40: ifeq +12 -> 52
    //   43: aload_0
    //   44: invokevirtual 125	com/google/android/gms/analytics/q:dispatchLocalHits	()V
    //   47: aload_0
    //   48: iconst_0
    //   49: putfield 44	com/google/android/gms/analytics/q:yg	Z
    //   52: aload_0
    //   53: getfield 127	com/google/android/gms/analytics/q:yh	Z
    //   56: ifeq -43 -> 13
    //   59: aload_0
    //   60: invokevirtual 130	com/google/android/gms/analytics/q:dN	()V
    //   63: aload_0
    //   64: iconst_0
    //   65: putfield 127	com/google/android/gms/analytics/q:yh	Z
    //   68: goto -55 -> 13
    //   71: astore_3
    //   72: aload_0
    //   73: monitorexit
    //   74: aload_3
    //   75: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	76	0	this	q
    //   0	76	1	paramContext	Context
    //   0	76	2	paramf	f
    //   71	4	3	localObject	Object
    //   6	3	4	localContext	Context
    // Exception table:
    //   from	to	target	type
    //   2	8	71	finally
    //   16	52	71	finally
    //   52	68	71	finally
  }
  
  void a(boolean paramBoolean1, boolean paramBoolean2)
  {
    for (;;)
    {
      StringBuilder localStringBuilder;
      try
      {
        if (this.yn == paramBoolean1)
        {
          boolean bool = this.yj;
          if (bool == paramBoolean2) {
            return;
          }
        }
        if (((paramBoolean1) || (!paramBoolean2)) && (this.yf > 0)) {
          this.mHandler.removeMessages(1, yc);
        }
        if ((!paramBoolean1) && (paramBoolean2) && (this.yf > 0)) {
          this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(1, yc), 1000 * this.yf);
        }
        localStringBuilder = new StringBuilder().append("PowerSaveMode ");
        if (paramBoolean1) {
          break label157;
        }
        if (paramBoolean2) {
          break label150;
        }
      }
      finally {}
      z.V(str);
      this.yn = paramBoolean1;
      this.yj = paramBoolean2;
      continue;
      label150:
      String str = "terminated.";
      continue;
      label157:
      str = "initiated.";
    }
  }
  
  void dN()
  {
    if (this.ye == null)
    {
      z.V("setForceLocalDispatch() queued. It will be called once initialization is complete.");
      this.yh = true;
      return;
    }
    t.ep().a(t.a.Ak);
    this.ye.dN();
  }
  
  /* Error */
  void dispatchLocalHits()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 122	com/google/android/gms/analytics/q:ye	Lcom/google/android/gms/analytics/f;
    //   6: ifnonnull +16 -> 22
    //   9: ldc 179
    //   11: invokestatic 153	com/google/android/gms/analytics/z:V	(Ljava/lang/String;)V
    //   14: aload_0
    //   15: iconst_1
    //   16: putfield 44	com/google/android/gms/analytics/q:yg	Z
    //   19: aload_0
    //   20: monitorexit
    //   21: return
    //   22: invokestatic 165	com/google/android/gms/analytics/t:ep	()Lcom/google/android/gms/analytics/t;
    //   25: getstatic 182	com/google/android/gms/analytics/t$a:zX	Lcom/google/android/gms/analytics/t$a;
    //   28: invokevirtual 174	com/google/android/gms/analytics/t:a	(Lcom/google/android/gms/analytics/t$a;)V
    //   31: aload_0
    //   32: getfield 122	com/google/android/gms/analytics/q:ye	Lcom/google/android/gms/analytics/f;
    //   35: invokeinterface 185 1 0
    //   40: goto -21 -> 19
    //   43: astore_1
    //   44: aload_0
    //   45: monitorexit
    //   46: aload_1
    //   47: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	48	0	this	q
    //   43	4	1	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	19	43	finally
    //   22	40	43	finally
  }
  
  d ec()
  {
    try
    {
      if (this.yd != null) {
        break label80;
      }
      if (this.mContext == null) {
        throw new IllegalStateException("Cant get a store unless we have a context");
      }
    }
    finally {}
    this.yd = new ab(this.yl, this.mContext);
    if (this.yi != null)
    {
      this.yd.dM().af(this.yi);
      this.yi = null;
    }
    label80:
    if (this.mHandler == null) {
      eb();
    }
    if ((this.ym == null) && (this.yk)) {
      ea();
    }
    d locald = this.yd;
    return locald;
  }
  
  void ed()
  {
    try
    {
      if ((!this.yn) && (this.yj) && (this.yf > 0))
      {
        this.mHandler.removeMessages(1, yc);
        this.mHandler.sendMessage(this.mHandler.obtainMessage(1, yc));
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
  void setLocalDispatchPeriod(int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 66	com/google/android/gms/analytics/q:mHandler	Landroid/os/Handler;
    //   6: ifnonnull +16 -> 22
    //   9: ldc 226
    //   11: invokestatic 153	com/google/android/gms/analytics/z:V	(Ljava/lang/String;)V
    //   14: aload_0
    //   15: iload_1
    //   16: putfield 42	com/google/android/gms/analytics/q:yf	I
    //   19: aload_0
    //   20: monitorexit
    //   21: return
    //   22: invokestatic 165	com/google/android/gms/analytics/t:ep	()Lcom/google/android/gms/analytics/t;
    //   25: getstatic 229	com/google/android/gms/analytics/t$a:zY	Lcom/google/android/gms/analytics/t$a;
    //   28: invokevirtual 174	com/google/android/gms/analytics/t:a	(Lcom/google/android/gms/analytics/t$a;)V
    //   31: aload_0
    //   32: getfield 57	com/google/android/gms/analytics/q:yn	Z
    //   35: ifne +28 -> 63
    //   38: aload_0
    //   39: getfield 46	com/google/android/gms/analytics/q:yj	Z
    //   42: ifeq +21 -> 63
    //   45: aload_0
    //   46: getfield 42	com/google/android/gms/analytics/q:yf	I
    //   49: ifle +14 -> 63
    //   52: aload_0
    //   53: getfield 66	com/google/android/gms/analytics/q:mHandler	Landroid/os/Handler;
    //   56: iconst_1
    //   57: getstatic 39	com/google/android/gms/analytics/q:yc	Ljava/lang/Object;
    //   60: invokevirtual 134	android/os/Handler:removeMessages	(ILjava/lang/Object;)V
    //   63: aload_0
    //   64: iload_1
    //   65: putfield 42	com/google/android/gms/analytics/q:yf	I
    //   68: iload_1
    //   69: ifle -50 -> 19
    //   72: aload_0
    //   73: getfield 57	com/google/android/gms/analytics/q:yn	Z
    //   76: ifne -57 -> 19
    //   79: aload_0
    //   80: getfield 46	com/google/android/gms/analytics/q:yj	Z
    //   83: ifeq -64 -> 19
    //   86: aload_0
    //   87: getfield 66	com/google/android/gms/analytics/q:mHandler	Landroid/os/Handler;
    //   90: aload_0
    //   91: getfield 66	com/google/android/gms/analytics/q:mHandler	Landroid/os/Handler;
    //   94: iconst_1
    //   95: getstatic 39	com/google/android/gms/analytics/q:yc	Ljava/lang/Object;
    //   98: invokevirtual 104	android/os/Handler:obtainMessage	(ILjava/lang/Object;)Landroid/os/Message;
    //   101: iload_1
    //   102: sipush 1000
    //   105: imul
    //   106: i2l
    //   107: invokevirtual 108	android/os/Handler:sendMessageDelayed	(Landroid/os/Message;J)Z
    //   110: pop
    //   111: goto -92 -> 19
    //   114: astore_2
    //   115: aload_0
    //   116: monitorexit
    //   117: aload_2
    //   118: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	119	0	this	q
    //   0	119	1	paramInt	int
    //   114	4	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	19	114	finally
    //   22	63	114	finally
    //   63	68	114	finally
    //   72	111	114	finally
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.analytics.q
 * JD-Core Version:    0.7.0.1
 */