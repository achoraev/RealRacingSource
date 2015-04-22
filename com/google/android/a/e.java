package com.google.android.a;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public abstract class e
  extends d
{
  static boolean d = false;
  private static Method e;
  private static Method f;
  private static Method g;
  private static Method h;
  private static Method i;
  private static Method j;
  private static Method k;
  private static Method l;
  private static String m;
  private static long n = 0L;
  private static k o;
  
  protected e(Context paramContext, i parami, j paramj)
  {
    super(paramContext, parami, paramj);
  }
  
  static String a()
    throws e.a
  {
    if (m == null) {
      throw new a();
    }
    return m;
  }
  
  static String a(Context paramContext, i parami)
    throws e.a
  {
    if (h == null) {
      throw new a();
    }
    try
    {
      localByteBuffer = (ByteBuffer)h.invoke(null, new Object[] { paramContext });
      if (localByteBuffer == null) {
        throw new a();
      }
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      ByteBuffer localByteBuffer;
      throw new a(localIllegalAccessException);
      String str = parami.a(localByteBuffer.array(), true);
      return str;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      throw new a(localInvocationTargetException);
    }
  }
  
  static ArrayList<Long> a(MotionEvent paramMotionEvent, DisplayMetrics paramDisplayMetrics)
    throws e.a
  {
    if ((i == null) || (paramMotionEvent == null)) {
      throw new a();
    }
    try
    {
      ArrayList localArrayList = (ArrayList)i.invoke(null, new Object[] { paramMotionEvent, paramDisplayMetrics });
      return localArrayList;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new a(localIllegalAccessException);
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      throw new a(localInvocationTargetException);
    }
  }
  
  /* Error */
  protected static void a(String paramString, Context paramContext, i parami)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 27	com/google/android/a/e:d	Z
    //   6: istore 4
    //   8: iload 4
    //   10: ifne +36 -> 46
    //   13: new 78	com/google/android/a/k
    //   16: dup
    //   17: aload_2
    //   18: aconst_null
    //   19: invokespecial 81	com/google/android/a/k:<init>	(Lcom/google/android/a/i;Ljava/security/SecureRandom;)V
    //   22: putstatic 83	com/google/android/a/e:o	Lcom/google/android/a/k;
    //   25: aload_0
    //   26: putstatic 37	com/google/android/a/e:m	Ljava/lang/String;
    //   29: aload_1
    //   30: invokestatic 86	com/google/android/a/e:f	(Landroid/content/Context;)V
    //   33: invokestatic 90	com/google/android/a/e:b	()Ljava/lang/Long;
    //   36: invokevirtual 96	java/lang/Long:longValue	()J
    //   39: putstatic 25	com/google/android/a/e:n	J
    //   42: iconst_1
    //   43: putstatic 27	com/google/android/a/e:d	Z
    //   46: ldc 2
    //   48: monitorexit
    //   49: return
    //   50: astore_3
    //   51: ldc 2
    //   53: monitorexit
    //   54: aload_3
    //   55: athrow
    //   56: astore 6
    //   58: goto -12 -> 46
    //   61: astore 5
    //   63: goto -17 -> 46
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	66	0	paramString	String
    //   0	66	1	paramContext	Context
    //   0	66	2	parami	i
    //   50	5	3	localObject	Object
    //   6	3	4	bool	boolean
    //   61	1	5	locala	a
    //   56	1	6	localUnsupportedOperationException	java.lang.UnsupportedOperationException
    // Exception table:
    //   from	to	target	type
    //   3	8	50	finally
    //   13	46	50	finally
    //   13	46	56	java/lang/UnsupportedOperationException
    //   13	46	61	com/google/android/a/e$a
  }
  
  static Long b()
    throws e.a
  {
    if (e == null) {
      throw new a();
    }
    try
    {
      Long localLong = (Long)e.invoke(null, new Object[0]);
      return localLong;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new a(localIllegalAccessException);
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      throw new a(localInvocationTargetException);
    }
  }
  
  static String b(Context paramContext, i parami)
    throws e.a
  {
    if (k == null) {
      throw new a();
    }
    try
    {
      localByteBuffer = (ByteBuffer)k.invoke(null, new Object[] { paramContext });
      if (localByteBuffer == null) {
        throw new a();
      }
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      ByteBuffer localByteBuffer;
      throw new a(localIllegalAccessException);
      String str = parami.a(localByteBuffer.array(), true);
      return str;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      throw new a(localInvocationTargetException);
    }
  }
  
  private static String b(byte[] paramArrayOfByte, String paramString)
    throws e.a
  {
    try
    {
      String str = new String(o.a(paramArrayOfByte, paramString), "UTF-8");
      return str;
    }
    catch (k.a locala)
    {
      throw new a(locala);
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      throw new a(localUnsupportedEncodingException);
    }
  }
  
  static String c()
    throws e.a
  {
    if (g == null) {
      throw new a();
    }
    try
    {
      String str = (String)g.invoke(null, new Object[0]);
      return str;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new a(localIllegalAccessException);
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      throw new a(localInvocationTargetException);
    }
  }
  
  static Long d()
    throws e.a
  {
    if (f == null) {
      throw new a();
    }
    try
    {
      Long localLong = (Long)f.invoke(null, new Object[0]);
      return localLong;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new a(localIllegalAccessException);
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      throw new a(localInvocationTargetException);
    }
  }
  
  static String d(Context paramContext)
    throws e.a
  {
    if (j == null) {
      throw new a();
    }
    String str;
    try
    {
      str = (String)j.invoke(null, new Object[] { paramContext });
      if (str == null) {
        throw new a();
      }
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new a(localIllegalAccessException);
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      throw new a(localInvocationTargetException);
    }
    return str;
  }
  
  static ArrayList<Long> e(Context paramContext)
    throws e.a
  {
    if (l == null) {
      throw new a();
    }
    ArrayList localArrayList;
    try
    {
      localArrayList = (ArrayList)l.invoke(null, new Object[] { paramContext });
      if ((localArrayList == null) || (localArrayList.size() != 2)) {
        throw new a();
      }
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new a(localIllegalAccessException);
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      throw new a(localInvocationTargetException);
    }
    return localArrayList;
  }
  
  private static void f(Context paramContext)
    throws e.a
  {
    try
    {
      arrayOfByte1 = o.a(m.a());
      arrayOfByte2 = o.a(arrayOfByte1, m.b());
      localFile1 = paramContext.getCacheDir();
      if (localFile1 == null)
      {
        localFile1 = paramContext.getDir("dex", 0);
        if (localFile1 == null) {
          throw new a();
        }
      }
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      byte[] arrayOfByte1;
      byte[] arrayOfByte2;
      File localFile1;
      throw new a(localFileNotFoundException);
      File localFile2 = localFile1;
      File localFile3 = File.createTempFile("ads", ".jar", localFile2);
      FileOutputStream localFileOutputStream = new FileOutputStream(localFile3);
      localFileOutputStream.write(arrayOfByte2, 0, arrayOfByte2.length);
      localFileOutputStream.close();
      try
      {
        DexClassLoader localDexClassLoader = new DexClassLoader(localFile3.getAbsolutePath(), localFile2.getAbsolutePath(), null, paramContext.getClassLoader());
        Class localClass1 = localDexClassLoader.loadClass(b(arrayOfByte1, m.c()));
        Class localClass2 = localDexClassLoader.loadClass(b(arrayOfByte1, m.o()));
        Class localClass3 = localDexClassLoader.loadClass(b(arrayOfByte1, m.i()));
        Class localClass4 = localDexClassLoader.loadClass(b(arrayOfByte1, m.g()));
        Class localClass5 = localDexClassLoader.loadClass(b(arrayOfByte1, m.q()));
        Class localClass6 = localDexClassLoader.loadClass(b(arrayOfByte1, m.e()));
        Class localClass7 = localDexClassLoader.loadClass(b(arrayOfByte1, m.m()));
        Class localClass8 = localDexClassLoader.loadClass(b(arrayOfByte1, m.k()));
        e = localClass1.getMethod(b(arrayOfByte1, m.d()), new Class[0]);
        f = localClass2.getMethod(b(arrayOfByte1, m.p()), new Class[0]);
        g = localClass3.getMethod(b(arrayOfByte1, m.j()), new Class[0]);
        h = localClass4.getMethod(b(arrayOfByte1, m.h()), new Class[] { Context.class });
        i = localClass5.getMethod(b(arrayOfByte1, m.r()), new Class[] { MotionEvent.class, DisplayMetrics.class });
        j = localClass6.getMethod(b(arrayOfByte1, m.f()), new Class[] { Context.class });
        k = localClass7.getMethod(b(arrayOfByte1, m.n()), new Class[] { Context.class });
        l = localClass8.getMethod(b(arrayOfByte1, m.l()), new Class[] { Context.class });
        String str2;
        return;
      }
      finally
      {
        String str1 = localFile3.getName();
        localFile3.delete();
        new File(localFile2, str1.replace(".jar", ".dex")).delete();
      }
    }
    catch (IOException localIOException)
    {
      throw new a(localIOException);
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      throw new a(localClassNotFoundException);
    }
    catch (k.a locala)
    {
      throw new a(locala);
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      throw new a(localNoSuchMethodException);
    }
    catch (NullPointerException localNullPointerException)
    {
      throw new a(localNullPointerException);
    }
  }
  
  /* Error */
  protected void b(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: iconst_1
    //   2: invokestatic 263	com/google/android/a/e:c	()Ljava/lang/String;
    //   5: invokevirtual 266	com/google/android/a/e:a	(ILjava/lang/String;)V
    //   8: aload_0
    //   9: iconst_2
    //   10: invokestatic 267	com/google/android/a/e:a	()Ljava/lang/String;
    //   13: invokevirtual 266	com/google/android/a/e:a	(ILjava/lang/String;)V
    //   16: aload_0
    //   17: bipush 25
    //   19: invokestatic 90	com/google/android/a/e:b	()Ljava/lang/Long;
    //   22: invokevirtual 96	java/lang/Long:longValue	()J
    //   25: invokevirtual 270	com/google/android/a/e:a	(IJ)V
    //   28: aload_0
    //   29: bipush 24
    //   31: aload_1
    //   32: invokestatic 272	com/google/android/a/e:d	(Landroid/content/Context;)Ljava/lang/String;
    //   35: invokevirtual 266	com/google/android/a/e:a	(ILjava/lang/String;)V
    //   38: aload_1
    //   39: invokestatic 274	com/google/android/a/e:e	(Landroid/content/Context;)Ljava/util/ArrayList;
    //   42: astore 9
    //   44: aload_0
    //   45: bipush 31
    //   47: aload 9
    //   49: iconst_0
    //   50: invokevirtual 278	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   53: checkcast 92	java/lang/Long
    //   56: invokevirtual 96	java/lang/Long:longValue	()J
    //   59: invokevirtual 270	com/google/android/a/e:a	(IJ)V
    //   62: aload_0
    //   63: bipush 32
    //   65: aload 9
    //   67: iconst_1
    //   68: invokevirtual 278	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   71: checkcast 92	java/lang/Long
    //   74: invokevirtual 96	java/lang/Long:longValue	()J
    //   77: invokevirtual 270	com/google/android/a/e:a	(IJ)V
    //   80: aload_0
    //   81: bipush 33
    //   83: invokestatic 280	com/google/android/a/e:d	()Ljava/lang/Long;
    //   86: invokevirtual 96	java/lang/Long:longValue	()J
    //   89: invokevirtual 270	com/google/android/a/e:a	(IJ)V
    //   92: return
    //   93: astore 8
    //   95: return
    //   96: astore 7
    //   98: return
    //   99: astore 6
    //   101: goto -21 -> 80
    //   104: astore 5
    //   106: goto -68 -> 38
    //   109: astore 4
    //   111: goto -83 -> 28
    //   114: astore_3
    //   115: goto -99 -> 16
    //   118: astore_2
    //   119: goto -111 -> 8
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	122	0	this	e
    //   0	122	1	paramContext	Context
    //   118	1	2	locala1	a
    //   114	1	3	locala2	a
    //   109	1	4	locala3	a
    //   104	1	5	locala4	a
    //   99	1	6	locala5	a
    //   96	1	7	locala6	a
    //   93	1	8	localIOException	IOException
    //   42	24	9	localArrayList	ArrayList
    // Exception table:
    //   from	to	target	type
    //   0	8	93	java/io/IOException
    //   8	16	93	java/io/IOException
    //   16	28	93	java/io/IOException
    //   28	38	93	java/io/IOException
    //   38	80	93	java/io/IOException
    //   80	92	93	java/io/IOException
    //   80	92	96	com/google/android/a/e$a
    //   38	80	99	com/google/android/a/e$a
    //   28	38	104	com/google/android/a/e$a
    //   16	28	109	com/google/android/a/e$a
    //   8	16	114	com/google/android/a/e$a
    //   0	8	118	com/google/android/a/e$a
  }
  
  /* Error */
  protected void c(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: iconst_2
    //   2: invokestatic 267	com/google/android/a/e:a	()Ljava/lang/String;
    //   5: invokevirtual 266	com/google/android/a/e:a	(ILjava/lang/String;)V
    //   8: aload_0
    //   9: iconst_1
    //   10: invokestatic 263	com/google/android/a/e:c	()Ljava/lang/String;
    //   13: invokevirtual 266	com/google/android/a/e:a	(ILjava/lang/String;)V
    //   16: invokestatic 90	com/google/android/a/e:b	()Ljava/lang/Long;
    //   19: invokevirtual 96	java/lang/Long:longValue	()J
    //   22: lstore 10
    //   24: aload_0
    //   25: bipush 25
    //   27: lload 10
    //   29: invokevirtual 270	com/google/android/a/e:a	(IJ)V
    //   32: getstatic 25	com/google/android/a/e:n	J
    //   35: lconst_0
    //   36: lcmp
    //   37: ifeq +24 -> 61
    //   40: aload_0
    //   41: bipush 17
    //   43: lload 10
    //   45: getstatic 25	com/google/android/a/e:n	J
    //   48: lsub
    //   49: invokevirtual 270	com/google/android/a/e:a	(IJ)V
    //   52: aload_0
    //   53: bipush 23
    //   55: getstatic 25	com/google/android/a/e:n	J
    //   58: invokevirtual 270	com/google/android/a/e:a	(IJ)V
    //   61: aload_0
    //   62: getfield 283	com/google/android/a/e:a	Landroid/view/MotionEvent;
    //   65: aload_0
    //   66: getfield 286	com/google/android/a/e:b	Landroid/util/DisplayMetrics;
    //   69: invokestatic 288	com/google/android/a/e:a	(Landroid/view/MotionEvent;Landroid/util/DisplayMetrics;)Ljava/util/ArrayList;
    //   72: astore 9
    //   74: aload_0
    //   75: bipush 14
    //   77: aload 9
    //   79: iconst_0
    //   80: invokevirtual 278	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   83: checkcast 92	java/lang/Long
    //   86: invokevirtual 96	java/lang/Long:longValue	()J
    //   89: invokevirtual 270	com/google/android/a/e:a	(IJ)V
    //   92: aload_0
    //   93: bipush 15
    //   95: aload 9
    //   97: iconst_1
    //   98: invokevirtual 278	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   101: checkcast 92	java/lang/Long
    //   104: invokevirtual 96	java/lang/Long:longValue	()J
    //   107: invokevirtual 270	com/google/android/a/e:a	(IJ)V
    //   110: aload 9
    //   112: invokevirtual 130	java/util/ArrayList:size	()I
    //   115: iconst_3
    //   116: if_icmplt +21 -> 137
    //   119: aload_0
    //   120: bipush 16
    //   122: aload 9
    //   124: iconst_2
    //   125: invokevirtual 278	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   128: checkcast 92	java/lang/Long
    //   131: invokevirtual 96	java/lang/Long:longValue	()J
    //   134: invokevirtual 270	com/google/android/a/e:a	(IJ)V
    //   137: aload_0
    //   138: bipush 27
    //   140: aload_1
    //   141: aload_0
    //   142: getfield 291	com/google/android/a/e:c	Lcom/google/android/a/i;
    //   145: invokestatic 293	com/google/android/a/e:a	(Landroid/content/Context;Lcom/google/android/a/i;)Ljava/lang/String;
    //   148: invokevirtual 266	com/google/android/a/e:a	(ILjava/lang/String;)V
    //   151: aload_0
    //   152: bipush 29
    //   154: aload_1
    //   155: aload_0
    //   156: getfield 291	com/google/android/a/e:c	Lcom/google/android/a/i;
    //   159: invokestatic 295	com/google/android/a/e:b	(Landroid/content/Context;Lcom/google/android/a/i;)Ljava/lang/String;
    //   162: invokevirtual 266	com/google/android/a/e:a	(ILjava/lang/String;)V
    //   165: return
    //   166: astore 8
    //   168: return
    //   169: astore 7
    //   171: return
    //   172: astore 6
    //   174: goto -23 -> 151
    //   177: astore 5
    //   179: goto -42 -> 137
    //   182: astore 4
    //   184: goto -123 -> 61
    //   187: astore_3
    //   188: goto -172 -> 16
    //   191: astore_2
    //   192: goto -184 -> 8
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	195	0	this	e
    //   0	195	1	paramContext	Context
    //   191	1	2	locala1	a
    //   187	1	3	locala2	a
    //   182	1	4	locala3	a
    //   177	1	5	locala4	a
    //   172	1	6	locala5	a
    //   169	1	7	locala6	a
    //   166	1	8	localIOException	IOException
    //   72	51	9	localArrayList	ArrayList
    //   22	22	10	l1	long
    // Exception table:
    //   from	to	target	type
    //   0	8	166	java/io/IOException
    //   8	16	166	java/io/IOException
    //   16	61	166	java/io/IOException
    //   61	137	166	java/io/IOException
    //   137	151	166	java/io/IOException
    //   151	165	166	java/io/IOException
    //   151	165	169	com/google/android/a/e$a
    //   137	151	172	com/google/android/a/e$a
    //   61	137	177	com/google/android/a/e$a
    //   16	61	182	com/google/android/a/e$a
    //   8	16	187	com/google/android/a/e$a
    //   0	8	191	com/google/android/a/e$a
  }
  
  static class a
    extends Exception
  {
    public a() {}
    
    public a(Throwable paramThrowable)
    {
      super();
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.a.e
 * JD-Core Version:    0.7.0.1
 */