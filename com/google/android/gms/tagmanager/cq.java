package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.c.f;
import com.google.android.gms.internal.ol.a;
import com.google.android.gms.internal.pm;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;

class cq
  implements o.f
{
  private final String aoc;
  private final ExecutorService aqA;
  private bg<ol.a> aqt;
  private final Context mContext;
  
  cq(Context paramContext, String paramString)
  {
    this.mContext = paramContext;
    this.aoc = paramString;
    this.aqA = Executors.newSingleThreadExecutor();
  }
  
  private cr.c a(ByteArrayOutputStream paramByteArrayOutputStream)
  {
    try
    {
      cr.c localc = ba.cG(paramByteArrayOutputStream.toString("UTF-8"));
      return localc;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      bh.S("Failed to convert binary resource to string for JSON parsing; the file format is not UTF-8 format.");
      return null;
    }
    catch (JSONException localJSONException)
    {
      bh.W("Failed to extract the container from the resource file. Resource is a UTF-8 encoded string but doesn't contain a JSON container");
    }
    return null;
  }
  
  private void d(ol.a parama)
    throws IllegalArgumentException
  {
    if ((parama.gs == null) && (parama.ass == null)) {
      throw new IllegalArgumentException("Resource and SupplementedResource are NULL.");
    }
  }
  
  private cr.c k(byte[] paramArrayOfByte)
  {
    try
    {
      cr.c localc = cr.b(c.f.a(paramArrayOfByte));
      if (localc != null) {
        bh.V("The container was successfully loaded from the resource (using binary file)");
      }
      return localc;
    }
    catch (pm localpm)
    {
      bh.T("The resource file is corrupted. The container cannot be extracted from the binary file");
      return null;
    }
    catch (cr.g localg)
    {
      bh.W("The resource file is invalid. The container from the binary file is invalid");
    }
    return null;
  }
  
  public void a(bg<ol.a> parambg)
  {
    this.aqt = parambg;
  }
  
  public void b(final ol.a parama)
  {
    this.aqA.execute(new Runnable()
    {
      public void run()
      {
        cq.this.c(parama);
      }
    });
  }
  
  /* Error */
  boolean c(ol.a parama)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 136	com/google/android/gms/tagmanager/cq:oS	()Ljava/io/File;
    //   4: astore_2
    //   5: new 138	java/io/FileOutputStream
    //   8: dup
    //   9: aload_2
    //   10: invokespecial 141	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   13: astore_3
    //   14: aload_3
    //   15: aload_1
    //   16: invokestatic 147	com/google/android/gms/internal/pn:f	(Lcom/google/android/gms/internal/pn;)[B
    //   19: invokevirtual 151	java/io/FileOutputStream:write	([B)V
    //   22: aload_3
    //   23: invokevirtual 154	java/io/FileOutputStream:close	()V
    //   26: iconst_1
    //   27: ireturn
    //   28: astore 10
    //   30: ldc 156
    //   32: invokestatic 110	com/google/android/gms/tagmanager/bh:T	(Ljava/lang/String;)V
    //   35: iconst_0
    //   36: ireturn
    //   37: astore 9
    //   39: ldc 158
    //   41: invokestatic 65	com/google/android/gms/tagmanager/bh:W	(Ljava/lang/String;)V
    //   44: iconst_1
    //   45: ireturn
    //   46: astore 6
    //   48: ldc 160
    //   50: invokestatic 65	com/google/android/gms/tagmanager/bh:W	(Ljava/lang/String;)V
    //   53: aload_2
    //   54: invokevirtual 166	java/io/File:delete	()Z
    //   57: pop
    //   58: aload_3
    //   59: invokevirtual 154	java/io/FileOutputStream:close	()V
    //   62: iconst_0
    //   63: ireturn
    //   64: astore 8
    //   66: ldc 158
    //   68: invokestatic 65	com/google/android/gms/tagmanager/bh:W	(Ljava/lang/String;)V
    //   71: iconst_0
    //   72: ireturn
    //   73: astore 4
    //   75: aload_3
    //   76: invokevirtual 154	java/io/FileOutputStream:close	()V
    //   79: aload 4
    //   81: athrow
    //   82: astore 5
    //   84: ldc 158
    //   86: invokestatic 65	com/google/android/gms/tagmanager/bh:W	(Ljava/lang/String;)V
    //   89: goto -10 -> 79
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	92	0	this	cq
    //   0	92	1	parama	ol.a
    //   4	50	2	localFile	File
    //   13	63	3	localFileOutputStream	java.io.FileOutputStream
    //   73	7	4	localObject	Object
    //   82	1	5	localIOException1	java.io.IOException
    //   46	1	6	localIOException2	java.io.IOException
    //   64	1	8	localIOException3	java.io.IOException
    //   37	1	9	localIOException4	java.io.IOException
    //   28	1	10	localFileNotFoundException	java.io.FileNotFoundException
    // Exception table:
    //   from	to	target	type
    //   5	14	28	java/io/FileNotFoundException
    //   22	26	37	java/io/IOException
    //   14	22	46	java/io/IOException
    //   58	62	64	java/io/IOException
    //   14	22	73	finally
    //   48	58	73	finally
    //   75	79	82	java/io/IOException
  }
  
  /* Error */
  public cr.c ff(int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 22	com/google/android/gms/tagmanager/cq:mContext	Landroid/content/Context;
    //   4: invokevirtual 176	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   7: iload_1
    //   8: invokevirtual 182	android/content/res/Resources:openRawResource	(I)Ljava/io/InputStream;
    //   11: astore_3
    //   12: new 184	java/lang/StringBuilder
    //   15: dup
    //   16: invokespecial 185	java/lang/StringBuilder:<init>	()V
    //   19: ldc 187
    //   21: invokevirtual 191	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   24: iload_1
    //   25: invokevirtual 194	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   28: ldc 196
    //   30: invokevirtual 191	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   33: aload_0
    //   34: getfield 22	com/google/android/gms/tagmanager/cq:mContext	Landroid/content/Context;
    //   37: invokevirtual 176	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   40: iload_1
    //   41: invokevirtual 200	android/content/res/Resources:getResourceName	(I)Ljava/lang/String;
    //   44: invokevirtual 191	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   47: ldc 202
    //   49: invokevirtual 191	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   52: invokevirtual 205	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   55: invokestatic 105	com/google/android/gms/tagmanager/bh:V	(Ljava/lang/String;)V
    //   58: new 42	java/io/ByteArrayOutputStream
    //   61: dup
    //   62: invokespecial 206	java/io/ByteArrayOutputStream:<init>	()V
    //   65: astore 4
    //   67: aload_3
    //   68: aload 4
    //   70: invokestatic 209	com/google/android/gms/tagmanager/cr:b	(Ljava/io/InputStream;Ljava/io/OutputStream;)V
    //   73: aload_0
    //   74: aload 4
    //   76: invokespecial 211	com/google/android/gms/tagmanager/cq:a	(Ljava/io/ByteArrayOutputStream;)Lcom/google/android/gms/tagmanager/cr$c;
    //   79: astore 6
    //   81: aload 6
    //   83: ifnull +36 -> 119
    //   86: ldc 213
    //   88: invokestatic 105	com/google/android/gms/tagmanager/bh:V	(Ljava/lang/String;)V
    //   91: aload 6
    //   93: areturn
    //   94: astore_2
    //   95: new 184	java/lang/StringBuilder
    //   98: dup
    //   99: invokespecial 185	java/lang/StringBuilder:<init>	()V
    //   102: ldc 215
    //   104: invokevirtual 191	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   107: iload_1
    //   108: invokevirtual 194	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   111: invokevirtual 205	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   114: invokestatic 65	com/google/android/gms/tagmanager/bh:W	(Ljava/lang/String;)V
    //   117: aconst_null
    //   118: areturn
    //   119: aload_0
    //   120: aload 4
    //   122: invokevirtual 219	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   125: invokespecial 221	com/google/android/gms/tagmanager/cq:k	([B)Lcom/google/android/gms/tagmanager/cr$c;
    //   128: astore 7
    //   130: aload 7
    //   132: areturn
    //   133: astore 5
    //   135: new 184	java/lang/StringBuilder
    //   138: dup
    //   139: invokespecial 185	java/lang/StringBuilder:<init>	()V
    //   142: ldc 223
    //   144: invokevirtual 191	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   147: iload_1
    //   148: invokevirtual 194	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   151: ldc 196
    //   153: invokevirtual 191	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   156: aload_0
    //   157: getfield 22	com/google/android/gms/tagmanager/cq:mContext	Landroid/content/Context;
    //   160: invokevirtual 176	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   163: iload_1
    //   164: invokevirtual 200	android/content/res/Resources:getResourceName	(I)Ljava/lang/String;
    //   167: invokevirtual 191	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   170: ldc 202
    //   172: invokevirtual 191	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   175: invokevirtual 205	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   178: invokestatic 65	com/google/android/gms/tagmanager/bh:W	(Ljava/lang/String;)V
    //   181: aconst_null
    //   182: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	183	0	this	cq
    //   0	183	1	paramInt	int
    //   94	1	2	localNotFoundException	android.content.res.Resources.NotFoundException
    //   11	57	3	localInputStream	java.io.InputStream
    //   65	56	4	localByteArrayOutputStream	ByteArrayOutputStream
    //   133	1	5	localIOException	java.io.IOException
    //   79	13	6	localc1	cr.c
    //   128	3	7	localc2	cr.c
    // Exception table:
    //   from	to	target	type
    //   0	12	94	android/content/res/Resources$NotFoundException
    //   58	81	133	java/io/IOException
    //   86	91	133	java/io/IOException
    //   119	130	133	java/io/IOException
  }
  
  /* Error */
  void oR()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 115	com/google/android/gms/tagmanager/cq:aqt	Lcom/google/android/gms/tagmanager/bg;
    //   4: ifnonnull +13 -> 17
    //   7: new 226	java/lang/IllegalStateException
    //   10: dup
    //   11: ldc 228
    //   13: invokespecial 229	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
    //   16: athrow
    //   17: aload_0
    //   18: getfield 115	com/google/android/gms/tagmanager/cq:aqt	Lcom/google/android/gms/tagmanager/bg;
    //   21: invokeinterface 234 1 0
    //   26: ldc 236
    //   28: invokestatic 105	com/google/android/gms/tagmanager/bh:V	(Ljava/lang/String;)V
    //   31: invokestatic 242	com/google/android/gms/tagmanager/ce:oJ	()Lcom/google/android/gms/tagmanager/ce;
    //   34: invokevirtual 246	com/google/android/gms/tagmanager/ce:oK	()Lcom/google/android/gms/tagmanager/ce$a;
    //   37: getstatic 252	com/google/android/gms/tagmanager/ce$a:aqi	Lcom/google/android/gms/tagmanager/ce$a;
    //   40: if_acmpeq +15 -> 55
    //   43: invokestatic 242	com/google/android/gms/tagmanager/ce:oJ	()Lcom/google/android/gms/tagmanager/ce;
    //   46: invokevirtual 246	com/google/android/gms/tagmanager/ce:oK	()Lcom/google/android/gms/tagmanager/ce$a;
    //   49: getstatic 255	com/google/android/gms/tagmanager/ce$a:aqj	Lcom/google/android/gms/tagmanager/ce$a;
    //   52: if_acmpne +32 -> 84
    //   55: aload_0
    //   56: getfield 24	com/google/android/gms/tagmanager/cq:aoc	Ljava/lang/String;
    //   59: invokestatic 242	com/google/android/gms/tagmanager/ce:oJ	()Lcom/google/android/gms/tagmanager/ce;
    //   62: invokevirtual 258	com/google/android/gms/tagmanager/ce:getContainerId	()Ljava/lang/String;
    //   65: invokevirtual 264	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   68: ifeq +16 -> 84
    //   71: aload_0
    //   72: getfield 115	com/google/android/gms/tagmanager/cq:aqt	Lcom/google/android/gms/tagmanager/bg;
    //   75: getstatic 270	com/google/android/gms/tagmanager/bg$a:apM	Lcom/google/android/gms/tagmanager/bg$a;
    //   78: invokeinterface 273 2 0
    //   83: return
    //   84: new 275	java/io/FileInputStream
    //   87: dup
    //   88: aload_0
    //   89: invokevirtual 136	com/google/android/gms/tagmanager/cq:oS	()Ljava/io/File;
    //   92: invokespecial 276	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   95: astore_1
    //   96: new 42	java/io/ByteArrayOutputStream
    //   99: dup
    //   100: invokespecial 206	java/io/ByteArrayOutputStream:<init>	()V
    //   103: astore_2
    //   104: aload_1
    //   105: aload_2
    //   106: invokestatic 209	com/google/android/gms/tagmanager/cr:b	(Ljava/io/InputStream;Ljava/io/OutputStream;)V
    //   109: aload_2
    //   110: invokevirtual 219	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   113: invokestatic 280	com/google/android/gms/internal/ol$a:l	([B)Lcom/google/android/gms/internal/ol$a;
    //   116: astore 9
    //   118: aload_0
    //   119: aload 9
    //   121: invokespecial 282	com/google/android/gms/tagmanager/cq:d	(Lcom/google/android/gms/internal/ol$a;)V
    //   124: aload_0
    //   125: getfield 115	com/google/android/gms/tagmanager/cq:aqt	Lcom/google/android/gms/tagmanager/bg;
    //   128: aload 9
    //   130: invokeinterface 285 2 0
    //   135: aload_1
    //   136: invokevirtual 286	java/io/FileInputStream:close	()V
    //   139: ldc_w 288
    //   142: invokestatic 105	com/google/android/gms/tagmanager/bh:V	(Ljava/lang/String;)V
    //   145: return
    //   146: astore 11
    //   148: ldc_w 290
    //   151: invokestatic 60	com/google/android/gms/tagmanager/bh:S	(Ljava/lang/String;)V
    //   154: aload_0
    //   155: getfield 115	com/google/android/gms/tagmanager/cq:aqt	Lcom/google/android/gms/tagmanager/bg;
    //   158: getstatic 270	com/google/android/gms/tagmanager/bg$a:apM	Lcom/google/android/gms/tagmanager/bg$a;
    //   161: invokeinterface 273 2 0
    //   166: return
    //   167: astore 10
    //   169: ldc_w 292
    //   172: invokestatic 65	com/google/android/gms/tagmanager/bh:W	(Ljava/lang/String;)V
    //   175: goto -36 -> 139
    //   178: astore 7
    //   180: aload_0
    //   181: getfield 115	com/google/android/gms/tagmanager/cq:aqt	Lcom/google/android/gms/tagmanager/bg;
    //   184: getstatic 295	com/google/android/gms/tagmanager/bg$a:apN	Lcom/google/android/gms/tagmanager/bg$a;
    //   187: invokeinterface 273 2 0
    //   192: ldc_w 297
    //   195: invokestatic 65	com/google/android/gms/tagmanager/bh:W	(Ljava/lang/String;)V
    //   198: aload_1
    //   199: invokevirtual 286	java/io/FileInputStream:close	()V
    //   202: goto -63 -> 139
    //   205: astore 8
    //   207: ldc_w 292
    //   210: invokestatic 65	com/google/android/gms/tagmanager/bh:W	(Ljava/lang/String;)V
    //   213: goto -74 -> 139
    //   216: astore 5
    //   218: aload_0
    //   219: getfield 115	com/google/android/gms/tagmanager/cq:aqt	Lcom/google/android/gms/tagmanager/bg;
    //   222: getstatic 295	com/google/android/gms/tagmanager/bg$a:apN	Lcom/google/android/gms/tagmanager/bg$a;
    //   225: invokeinterface 273 2 0
    //   230: ldc_w 299
    //   233: invokestatic 65	com/google/android/gms/tagmanager/bh:W	(Ljava/lang/String;)V
    //   236: aload_1
    //   237: invokevirtual 286	java/io/FileInputStream:close	()V
    //   240: goto -101 -> 139
    //   243: astore 6
    //   245: ldc_w 292
    //   248: invokestatic 65	com/google/android/gms/tagmanager/bh:W	(Ljava/lang/String;)V
    //   251: goto -112 -> 139
    //   254: astore_3
    //   255: aload_1
    //   256: invokevirtual 286	java/io/FileInputStream:close	()V
    //   259: aload_3
    //   260: athrow
    //   261: astore 4
    //   263: ldc_w 292
    //   266: invokestatic 65	com/google/android/gms/tagmanager/bh:W	(Ljava/lang/String;)V
    //   269: goto -10 -> 259
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	272	0	this	cq
    //   95	161	1	localFileInputStream	java.io.FileInputStream
    //   103	7	2	localByteArrayOutputStream	ByteArrayOutputStream
    //   254	6	3	localObject	Object
    //   261	1	4	localIOException1	java.io.IOException
    //   216	1	5	localIllegalArgumentException	IllegalArgumentException
    //   243	1	6	localIOException2	java.io.IOException
    //   178	1	7	localIOException3	java.io.IOException
    //   205	1	8	localIOException4	java.io.IOException
    //   116	13	9	locala	ol.a
    //   167	1	10	localIOException5	java.io.IOException
    //   146	1	11	localFileNotFoundException	java.io.FileNotFoundException
    // Exception table:
    //   from	to	target	type
    //   84	96	146	java/io/FileNotFoundException
    //   135	139	167	java/io/IOException
    //   96	135	178	java/io/IOException
    //   198	202	205	java/io/IOException
    //   96	135	216	java/lang/IllegalArgumentException
    //   236	240	243	java/io/IOException
    //   96	135	254	finally
    //   180	198	254	finally
    //   218	236	254	finally
    //   255	259	261	java/io/IOException
  }
  
  File oS()
  {
    String str = "resource_" + this.aoc;
    return new File(this.mContext.getDir("google_tagmanager", 0), str);
  }
  
  public void oc()
  {
    this.aqA.execute(new Runnable()
    {
      public void run()
      {
        cq.this.oR();
      }
    });
  }
  
  public void release()
  {
    try
    {
      this.aqA.shutdown();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.cq
 * JD-Core Version:    0.7.0.1
 */