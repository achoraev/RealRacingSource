package com.google.android.gms.internal;

import android.content.Context;

@ez
public final class gq
  extends gg
{
  private final Context mContext;
  private final String mv;
  private final String uR;
  private String vW = null;
  
  public gq(Context paramContext, String paramString1, String paramString2)
  {
    this.mContext = paramContext;
    this.mv = paramString1;
    this.uR = paramString2;
  }
  
  public gq(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    this.mContext = paramContext;
    this.mv = paramString1;
    this.uR = paramString2;
    this.vW = paramString3;
  }
  
  /* Error */
  public void co()
  {
    // Byte code:
    //   0: new 32	java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial 33	java/lang/StringBuilder:<init>	()V
    //   7: ldc 35
    //   9: invokevirtual 39	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   12: aload_0
    //   13: getfield 24	com/google/android/gms/internal/gq:uR	Ljava/lang/String;
    //   16: invokevirtual 39	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   19: invokevirtual 43	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   22: invokestatic 49	com/google/android/gms/internal/gs:V	(Ljava/lang/String;)V
    //   25: new 51	java/net/URL
    //   28: dup
    //   29: aload_0
    //   30: getfield 24	com/google/android/gms/internal/gq:uR	Ljava/lang/String;
    //   33: invokespecial 53	java/net/URL:<init>	(Ljava/lang/String;)V
    //   36: invokevirtual 57	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   39: checkcast 59	java/net/HttpURLConnection
    //   42: astore_3
    //   43: aload_0
    //   44: getfield 18	com/google/android/gms/internal/gq:vW	Ljava/lang/String;
    //   47: ifnonnull +78 -> 125
    //   50: aload_0
    //   51: getfield 20	com/google/android/gms/internal/gq:mContext	Landroid/content/Context;
    //   54: aload_0
    //   55: getfield 22	com/google/android/gms/internal/gq:mv	Ljava/lang/String;
    //   58: iconst_1
    //   59: aload_3
    //   60: invokestatic 65	com/google/android/gms/internal/gj:a	(Landroid/content/Context;Ljava/lang/String;ZLjava/net/HttpURLConnection;)V
    //   63: aload_3
    //   64: invokevirtual 69	java/net/HttpURLConnection:getResponseCode	()I
    //   67: istore 5
    //   69: iload 5
    //   71: sipush 200
    //   74: if_icmplt +11 -> 85
    //   77: iload 5
    //   79: sipush 300
    //   82: if_icmplt +38 -> 120
    //   85: new 32	java/lang/StringBuilder
    //   88: dup
    //   89: invokespecial 33	java/lang/StringBuilder:<init>	()V
    //   92: ldc 71
    //   94: invokevirtual 39	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   97: iload 5
    //   99: invokevirtual 74	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   102: ldc 76
    //   104: invokevirtual 39	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   107: aload_0
    //   108: getfield 24	com/google/android/gms/internal/gq:uR	Ljava/lang/String;
    //   111: invokevirtual 39	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   114: invokevirtual 43	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   117: invokestatic 79	com/google/android/gms/internal/gs:W	(Ljava/lang/String;)V
    //   120: aload_3
    //   121: invokevirtual 82	java/net/HttpURLConnection:disconnect	()V
    //   124: return
    //   125: aload_0
    //   126: getfield 20	com/google/android/gms/internal/gq:mContext	Landroid/content/Context;
    //   129: aload_0
    //   130: getfield 22	com/google/android/gms/internal/gq:mv	Ljava/lang/String;
    //   133: iconst_1
    //   134: aload_3
    //   135: aload_0
    //   136: getfield 18	com/google/android/gms/internal/gq:vW	Ljava/lang/String;
    //   139: invokestatic 85	com/google/android/gms/internal/gj:a	(Landroid/content/Context;Ljava/lang/String;ZLjava/net/HttpURLConnection;Ljava/lang/String;)V
    //   142: goto -79 -> 63
    //   145: astore 4
    //   147: aload_3
    //   148: invokevirtual 82	java/net/HttpURLConnection:disconnect	()V
    //   151: aload 4
    //   153: athrow
    //   154: astore_2
    //   155: new 32	java/lang/StringBuilder
    //   158: dup
    //   159: invokespecial 33	java/lang/StringBuilder:<init>	()V
    //   162: ldc 87
    //   164: invokevirtual 39	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   167: aload_0
    //   168: getfield 24	com/google/android/gms/internal/gq:uR	Ljava/lang/String;
    //   171: invokevirtual 39	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   174: ldc 89
    //   176: invokevirtual 39	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   179: aload_2
    //   180: invokevirtual 92	java/lang/IndexOutOfBoundsException:getMessage	()Ljava/lang/String;
    //   183: invokevirtual 39	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   186: invokevirtual 43	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   189: invokestatic 79	com/google/android/gms/internal/gs:W	(Ljava/lang/String;)V
    //   192: return
    //   193: astore_1
    //   194: new 32	java/lang/StringBuilder
    //   197: dup
    //   198: invokespecial 33	java/lang/StringBuilder:<init>	()V
    //   201: ldc 94
    //   203: invokevirtual 39	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   206: aload_0
    //   207: getfield 24	com/google/android/gms/internal/gq:uR	Ljava/lang/String;
    //   210: invokevirtual 39	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   213: ldc 89
    //   215: invokevirtual 39	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   218: aload_1
    //   219: invokevirtual 95	java/io/IOException:getMessage	()Ljava/lang/String;
    //   222: invokevirtual 39	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   225: invokevirtual 43	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   228: invokestatic 79	com/google/android/gms/internal/gs:W	(Ljava/lang/String;)V
    //   231: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	232	0	this	gq
    //   193	26	1	localIOException	java.io.IOException
    //   154	26	2	localIndexOutOfBoundsException	java.lang.IndexOutOfBoundsException
    //   42	106	3	localHttpURLConnection	java.net.HttpURLConnection
    //   145	7	4	localObject	java.lang.Object
    //   67	31	5	i	int
    // Exception table:
    //   from	to	target	type
    //   43	63	145	finally
    //   63	69	145	finally
    //   85	120	145	finally
    //   125	142	145	finally
    //   0	43	154	java/lang/IndexOutOfBoundsException
    //   120	124	154	java/lang/IndexOutOfBoundsException
    //   147	154	154	java/lang/IndexOutOfBoundsException
    //   0	43	193	java/io/IOException
    //   120	124	193	java/io/IOException
    //   147	154	193	java/io/IOException
  }
  
  public void onStop() {}
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.gq
 * JD-Core Version:    0.7.0.1
 */