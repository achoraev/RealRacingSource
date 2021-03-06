package com.crashlytics.android.internal;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Debug;
import android.os.StatFs;
import android.provider.Settings.Secure;
import java.io.Closeable;
import java.io.File;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public final class ab
{
  public static final Comparator<File> a = new ac();
  private static Boolean b = null;
  private static final char[] c = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102 };
  private static long d = -1L;
  private static Boolean e = null;
  
  public static int a(Context paramContext, String paramString1, String paramString2)
  {
    Resources localResources = paramContext.getResources();
    int i = paramContext.getApplicationContext().getApplicationInfo().icon;
    if (i > 0) {}
    for (String str = paramContext.getResources().getResourcePackageName(i);; str = paramContext.getPackageName()) {
      return localResources.getIdentifier(paramString1, paramString2, str);
    }
  }
  
  public static int a(boolean paramBoolean)
  {
    float f = b(v.a().getContext());
    if (!paramBoolean) {
      return 1;
    }
    if ((paramBoolean) && (f >= 99.0D)) {
      return 3;
    }
    if ((paramBoolean) && (f < 99.0D)) {
      return 2;
    }
    return 0;
  }
  
  public static long a(Context paramContext)
  {
    ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
    ((ActivityManager)paramContext.getSystemService("activity")).getMemoryInfo(localMemoryInfo);
    return localMemoryInfo.availMem;
  }
  
  private static long a(String paramString1, String paramString2, int paramInt)
  {
    return Long.parseLong(paramString1.split(paramString2)[0].trim()) * paramInt;
  }
  
  public static ActivityManager.RunningAppProcessInfo a(String paramString, Context paramContext)
  {
    List localList = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
    if (localList != null)
    {
      Iterator localIterator = localList.iterator();
      while (localIterator.hasNext())
      {
        ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)localIterator.next();
        if (localRunningAppProcessInfo.processName.equals(paramString)) {
          return localRunningAppProcessInfo;
        }
      }
    }
    return null;
  }
  
  public static SharedPreferences a()
  {
    return v.a().getContext().getSharedPreferences("com.crashlytics.prefs", 0);
  }
  
  public static String a(int paramInt)
  {
    if (paramInt < 0) {
      throw new IllegalArgumentException("value must be zero or greater");
    }
    Locale localLocale = Locale.US;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Integer.valueOf(paramInt);
    return String.format(localLocale, "%1$10s", arrayOfObject).replace(' ', '0');
  }
  
  public static String a(Context paramContext, String paramString)
  {
    int i = a(paramContext, paramString, "string");
    if (i > 0) {
      return paramContext.getString(i);
    }
    return "";
  }
  
  /* Error */
  private static String a(File paramFile, String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 218	java/io/File:exists	()Z
    //   4: istore_2
    //   5: aconst_null
    //   6: astore_3
    //   7: iload_2
    //   8: ifeq +80 -> 88
    //   11: new 220	java/io/BufferedReader
    //   14: dup
    //   15: new 222	java/io/FileReader
    //   18: dup
    //   19: aload_0
    //   20: invokespecial 225	java/io/FileReader:<init>	(Ljava/io/File;)V
    //   23: sipush 1024
    //   26: invokespecial 228	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   29: astore 4
    //   31: aload 4
    //   33: invokevirtual 231	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   36: astore 7
    //   38: aconst_null
    //   39: astore_3
    //   40: aload 7
    //   42: ifnull +39 -> 81
    //   45: ldc 233
    //   47: invokestatic 239	java/util/regex/Pattern:compile	(Ljava/lang/String;)Ljava/util/regex/Pattern;
    //   50: aload 7
    //   52: iconst_2
    //   53: invokevirtual 242	java/util/regex/Pattern:split	(Ljava/lang/CharSequence;I)[Ljava/lang/String;
    //   56: astore 8
    //   58: aload 8
    //   60: arraylength
    //   61: iconst_1
    //   62: if_icmple -31 -> 31
    //   65: aload 8
    //   67: iconst_0
    //   68: aaload
    //   69: aload_1
    //   70: invokevirtual 164	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   73: ifeq -42 -> 31
    //   76: aload 8
    //   78: iconst_1
    //   79: aaload
    //   80: astore_3
    //   81: aload 4
    //   83: ldc 244
    //   85: invokestatic 247	com/crashlytics/android/internal/ab:a	(Ljava/io/Closeable;Ljava/lang/String;)V
    //   88: aload_3
    //   89: areturn
    //   90: astore 5
    //   92: aconst_null
    //   93: astore 4
    //   95: invokestatic 90	com/crashlytics/android/internal/v:a	()Lcom/crashlytics/android/internal/v;
    //   98: invokevirtual 250	com/crashlytics/android/internal/v:b	()Lcom/crashlytics/android/internal/q;
    //   101: ldc 252
    //   103: new 254	java/lang/StringBuilder
    //   106: dup
    //   107: ldc_w 256
    //   110: invokespecial 257	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   113: aload_0
    //   114: invokevirtual 261	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   117: invokevirtual 264	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   120: aload 5
    //   122: invokeinterface 269 4 0
    //   127: aload 4
    //   129: ldc 244
    //   131: invokestatic 247	com/crashlytics/android/internal/ab:a	(Ljava/io/Closeable;Ljava/lang/String;)V
    //   134: aconst_null
    //   135: areturn
    //   136: astore 9
    //   138: aconst_null
    //   139: astore 4
    //   141: aload 9
    //   143: astore 6
    //   145: aload 4
    //   147: ldc 244
    //   149: invokestatic 247	com/crashlytics/android/internal/ab:a	(Ljava/io/Closeable;Ljava/lang/String;)V
    //   152: aload 6
    //   154: athrow
    //   155: astore 6
    //   157: goto -12 -> 145
    //   160: astore 5
    //   162: goto -67 -> 95
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	165	0	paramFile	File
    //   0	165	1	paramString	String
    //   4	4	2	bool	boolean
    //   6	83	3	str1	String
    //   29	117	4	localBufferedReader	java.io.BufferedReader
    //   90	31	5	localException1	Exception
    //   160	1	5	localException2	Exception
    //   143	10	6	localObject1	Object
    //   155	1	6	localObject2	Object
    //   36	15	7	str2	String
    //   56	21	8	arrayOfString	String[]
    //   136	6	9	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   11	31	90	java/lang/Exception
    //   11	31	136	finally
    //   31	38	155	finally
    //   45	81	155	finally
    //   95	127	155	finally
    //   31	38	160	java/lang/Exception
    //   45	81	160	java/lang/Exception
  }
  
  public static String a(InputStream paramInputStream)
    throws IOException
  {
    Scanner localScanner = new Scanner(paramInputStream).useDelimiter("\\A");
    if (localScanner.hasNext()) {
      return localScanner.next();
    }
    return "";
  }
  
  public static String a(String paramString)
  {
    return a(paramString.getBytes(), "SHA-1");
  }
  
  public static String a(byte[] paramArrayOfByte)
  {
    char[] arrayOfChar = new char[paramArrayOfByte.length << 1];
    for (int i = 0; i < paramArrayOfByte.length; i++)
    {
      int j = 0xFF & paramArrayOfByte[i];
      arrayOfChar[(i << 1)] = c[(j >>> 4)];
      arrayOfChar[(1 + (i << 1))] = c[(j & 0xF)];
    }
    return new String(arrayOfChar);
  }
  
  private static String a(byte[] paramArrayOfByte, String paramString)
  {
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance(paramString);
      localMessageDigest.update(paramArrayOfByte);
      return a(localMessageDigest.digest());
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      v.a().b().a("Crashlytics", "Could not create hashing algorithm: " + paramString + ", returning empty string.", localNoSuchAlgorithmException);
    }
    return "";
  }
  
  public static String a(String... paramVarArgs)
  {
    if ((paramVarArgs == null) || (paramVarArgs.length == 0)) {
      return null;
    }
    ArrayList localArrayList = new ArrayList();
    int i = paramVarArgs.length;
    for (int j = 0; j < i; j++)
    {
      String str2 = paramVarArgs[j];
      if (str2 != null) {
        localArrayList.add(str2.replace("-", "").toLowerCase(Locale.US));
      }
    }
    Collections.sort(localArrayList);
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = localArrayList.iterator();
    while (localIterator.hasNext()) {
      localStringBuilder.append((String)localIterator.next());
    }
    String str1 = localStringBuilder.toString();
    if (str1.length() > 0) {
      return a(str1);
    }
    return null;
  }
  
  public static void a(int paramInt, String paramString)
  {
    if (e(v.a().getContext())) {
      v.a().b().a(4, "Crashlytics", paramString);
    }
  }
  
  public static void a(Closeable paramCloseable, String paramString)
  {
    if (paramCloseable != null) {}
    try
    {
      paramCloseable.close();
      return;
    }
    catch (IOException localIOException)
    {
      v.a().b().a("Crashlytics", paramString, localIOException);
    }
  }
  
  public static void a(Flushable paramFlushable, String paramString)
  {
    if (paramFlushable != null) {}
    try
    {
      paramFlushable.flush();
      return;
    }
    catch (IOException localIOException)
    {
      v.a().b().a("Crashlytics", paramString, localIOException);
    }
  }
  
  public static void a(InputStream paramInputStream, OutputStream paramOutputStream, byte[] paramArrayOfByte)
    throws IOException
  {
    for (;;)
    {
      int i = paramInputStream.read(paramArrayOfByte);
      if (i == -1) {
        break;
      }
      paramOutputStream.write(paramArrayOfByte, 0, i);
    }
  }
  
  public static boolean a(Context paramContext, String paramString, boolean paramBoolean)
  {
    if (paramContext != null)
    {
      Resources localResources = paramContext.getResources();
      if (localResources != null)
      {
        int i = a(paramContext, paramString, "bool");
        if (i <= 0) {
          break label37;
        }
        paramBoolean = localResources.getBoolean(i);
      }
    }
    label37:
    int j;
    do
    {
      return paramBoolean;
      j = a(paramContext, paramString, "string");
    } while (j <= 0);
    return Boolean.parseBoolean(paramContext.getString(j));
  }
  
  public static float b(Context paramContext)
  {
    Intent localIntent = paramContext.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
    int i = localIntent.getIntExtra("level", -1);
    int j = localIntent.getIntExtra("scale", -1);
    return i / j;
  }
  
  public static int b()
  {
    return ad.a().ordinal();
  }
  
  public static long b(String paramString)
  {
    StatFs localStatFs = new StatFs(paramString);
    long l = localStatFs.getBlockSize();
    return l * localStatFs.getBlockCount() - l * localStatFs.getAvailableBlocks();
  }
  
  public static String b(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return "?";
    case 7: 
      return "A";
    case 3: 
      return "D";
    case 6: 
      return "E";
    case 4: 
      return "I";
    case 2: 
      return "V";
    }
    return "W";
  }
  
  private static String b(InputStream paramInputStream)
  {
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("SHA-1");
      byte[] arrayOfByte = new byte[1024];
      for (;;)
      {
        int i = paramInputStream.read(arrayOfByte);
        if (i == -1) {
          break;
        }
        localMessageDigest.update(arrayOfByte, 0, i);
      }
      str = a(localMessageDigest.digest());
    }
    catch (Exception localException)
    {
      v.a().b().a("Crashlytics", "Could not calculate hash for app icon.", localException);
      return "";
    }
    String str;
    return str;
  }
  
  public static Cipher b(int paramInt, String paramString)
    throws InvalidKeyException
  {
    if (paramString.length() < 32) {
      throw new InvalidKeyException("Key must be at least 32 bytes.");
    }
    SecretKeySpec localSecretKeySpec = new SecretKeySpec(paramString.getBytes(), 0, 32, "AES/ECB/PKCS7Padding");
    try
    {
      Cipher localCipher = Cipher.getInstance("AES/ECB/PKCS7Padding");
      localCipher.init(1, localSecretKeySpec);
      return localCipher;
    }
    catch (GeneralSecurityException localGeneralSecurityException)
    {
      v.a().b().a("Crashlytics", "Could not create Cipher for AES/ECB/PKCS7Padding - should never happen.", localGeneralSecurityException);
      throw new RuntimeException(localGeneralSecurityException);
    }
  }
  
  /* Error */
  public static long c()
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 40	com/crashlytics/android/internal/ab:d	J
    //   6: ldc2_w 37
    //   9: lcmp
    //   10: ifne +69 -> 79
    //   13: new 215	java/io/File
    //   16: dup
    //   17: ldc_w 489
    //   20: invokespecial 490	java/io/File:<init>	(Ljava/lang/String;)V
    //   23: ldc_w 492
    //   26: invokestatic 494	com/crashlytics/android/internal/ab:a	(Ljava/io/File;Ljava/lang/String;)Ljava/lang/String;
    //   29: astore_3
    //   30: aload_3
    //   31: invokestatic 500	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   34: ifne +180 -> 214
    //   37: aload_3
    //   38: getstatic 184	java/util/Locale:US	Ljava/util/Locale;
    //   41: invokevirtual 503	java/lang/String:toUpperCase	(Ljava/util/Locale;)Ljava/lang/String;
    //   44: astore 6
    //   46: aload 6
    //   48: ldc_w 505
    //   51: invokevirtual 508	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   54: ifeq +34 -> 88
    //   57: aload 6
    //   59: ldc_w 505
    //   62: sipush 1024
    //   65: invokestatic 510	com/crashlytics/android/internal/ab:a	(Ljava/lang/String;Ljava/lang/String;I)J
    //   68: lstore 8
    //   70: lload 8
    //   72: lstore 4
    //   74: lload 4
    //   76: putstatic 40	com/crashlytics/android/internal/ab:d	J
    //   79: getstatic 40	com/crashlytics/android/internal/ab:d	J
    //   82: lstore_1
    //   83: ldc 2
    //   85: monitorexit
    //   86: lload_1
    //   87: lreturn
    //   88: aload 6
    //   90: ldc_w 512
    //   93: invokevirtual 508	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   96: ifeq +19 -> 115
    //   99: aload 6
    //   101: ldc_w 512
    //   104: ldc_w 513
    //   107: invokestatic 510	com/crashlytics/android/internal/ab:a	(Ljava/lang/String;Ljava/lang/String;I)J
    //   110: lstore 4
    //   112: goto -38 -> 74
    //   115: aload 6
    //   117: ldc_w 515
    //   120: invokevirtual 508	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   123: ifeq +19 -> 142
    //   126: aload 6
    //   128: ldc_w 515
    //   131: ldc_w 516
    //   134: invokestatic 510	com/crashlytics/android/internal/ab:a	(Ljava/lang/String;Ljava/lang/String;I)J
    //   137: lstore 4
    //   139: goto -65 -> 74
    //   142: invokestatic 90	com/crashlytics/android/internal/v:a	()Lcom/crashlytics/android/internal/v;
    //   145: invokevirtual 250	com/crashlytics/android/internal/v:b	()Lcom/crashlytics/android/internal/q;
    //   148: ldc 252
    //   150: new 254	java/lang/StringBuilder
    //   153: dup
    //   154: ldc_w 518
    //   157: invokespecial 257	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   160: aload 6
    //   162: invokevirtual 322	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   165: invokevirtual 264	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   168: invokeinterface 521 3 0
    //   173: lconst_0
    //   174: lstore 4
    //   176: goto -102 -> 74
    //   179: astore 7
    //   181: invokestatic 90	com/crashlytics/android/internal/v:a	()Lcom/crashlytics/android/internal/v;
    //   184: invokevirtual 250	com/crashlytics/android/internal/v:b	()Lcom/crashlytics/android/internal/q;
    //   187: ldc 252
    //   189: new 254	java/lang/StringBuilder
    //   192: dup
    //   193: ldc_w 518
    //   196: invokespecial 257	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   199: aload 6
    //   201: invokevirtual 322	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   204: invokevirtual 264	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   207: aload 7
    //   209: invokeinterface 269 4 0
    //   214: lconst_0
    //   215: lstore 4
    //   217: goto -143 -> 74
    //   220: astore_0
    //   221: ldc 2
    //   223: monitorexit
    //   224: aload_0
    //   225: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   220	5	0	localObject	Object
    //   82	5	1	l1	long
    //   29	9	3	str1	String
    //   72	144	4	l2	long
    //   44	156	6	str2	String
    //   179	29	7	localNumberFormatException	java.lang.NumberFormatException
    //   68	3	8	l3	long
    // Exception table:
    //   from	to	target	type
    //   46	70	179	java/lang/NumberFormatException
    //   88	112	179	java/lang/NumberFormatException
    //   115	139	179	java/lang/NumberFormatException
    //   142	173	179	java/lang/NumberFormatException
    //   3	46	220	finally
    //   46	70	220	finally
    //   74	79	220	finally
    //   79	83	220	finally
    //   88	112	220	finally
    //   115	139	220	finally
    //   142	173	220	finally
    //   181	214	220	finally
  }
  
  public static void c(String paramString)
  {
    if (e(v.a().getContext())) {
      v.a().b().a("Crashlytics", paramString);
    }
  }
  
  public static boolean c(Context paramContext)
  {
    if (d()) {
      return false;
    }
    return ((SensorManager)paramContext.getSystemService("sensor")).getDefaultSensor(8) != null;
  }
  
  public static void d(String paramString)
  {
    if (e(v.a().getContext())) {
      v.a().b().d("Crashlytics", paramString);
    }
  }
  
  public static boolean d()
  {
    String str = Settings.Secure.getString(v.a().getContext().getContentResolver(), "android_id");
    return ("sdk".equals(Build.PRODUCT)) || ("google_sdk".equals(Build.PRODUCT)) || (str == null);
  }
  
  public static boolean d(Context paramContext)
  {
    if (e == null)
    {
      boolean bool1 = a(paramContext, "com.crashlytics.SilenceCrashlyticsLogCat", false);
      boolean bool2 = false;
      if (!bool1) {
        bool2 = true;
      }
      e = Boolean.valueOf(bool2);
    }
    return e.booleanValue();
  }
  
  public static boolean e()
  {
    boolean bool = d();
    String str = Build.TAGS;
    if ((!bool) && (str != null) && (str.contains("test-keys"))) {}
    File localFile;
    do
    {
      do
      {
        return true;
      } while (new File("/system/app/Superuser.apk").exists());
      localFile = new File("/system/xbin/su");
    } while ((!bool) && (localFile.exists()));
    return false;
  }
  
  public static boolean e(Context paramContext)
  {
    if (b == null) {
      b = Boolean.valueOf(a(paramContext, "com.crashlytics.Trace", false));
    }
    return b.booleanValue();
  }
  
  public static boolean e(String paramString)
  {
    return (paramString == null) || (paramString.length() == 0);
  }
  
  public static int f()
  {
    if (d()) {}
    for (int i = 1;; i = 0)
    {
      if (e()) {
        i |= 0x2;
      }
      int j;
      if (!Debug.isDebuggerConnected())
      {
        boolean bool = Debug.waitingForDebugger();
        j = 0;
        if (!bool) {}
      }
      else
      {
        j = 1;
      }
      if (j != 0) {
        i |= 0x4;
      }
      return i;
    }
  }
  
  public static boolean f(Context paramContext)
  {
    return (0x2 & paramContext.getApplicationInfo().flags) != 0;
  }
  
  /* Error */
  public static String g(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 56	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   4: aload_0
    //   5: invokestatic 597	com/crashlytics/android/internal/ab:h	(Landroid/content/Context;)I
    //   8: invokevirtual 601	android/content/res/Resources:openRawResource	(I)Ljava/io/InputStream;
    //   11: astore 5
    //   13: aload 5
    //   15: astore_2
    //   16: aload_2
    //   17: invokestatic 603	com/crashlytics/android/internal/ab:b	(Ljava/io/InputStream;)Ljava/lang/String;
    //   20: astore 6
    //   22: aload 6
    //   24: invokestatic 605	com/crashlytics/android/internal/ab:e	(Ljava/lang/String;)Z
    //   27: istore 7
    //   29: aconst_null
    //   30: astore 8
    //   32: iload 7
    //   34: ifeq +13 -> 47
    //   37: aload_2
    //   38: ldc_w 607
    //   41: invokestatic 247	com/crashlytics/android/internal/ab:a	(Ljava/io/Closeable;Ljava/lang/String;)V
    //   44: aload 8
    //   46: areturn
    //   47: aload 6
    //   49: astore 8
    //   51: goto -14 -> 37
    //   54: astore 4
    //   56: aconst_null
    //   57: astore_2
    //   58: invokestatic 90	com/crashlytics/android/internal/v:a	()Lcom/crashlytics/android/internal/v;
    //   61: invokevirtual 250	com/crashlytics/android/internal/v:b	()Lcom/crashlytics/android/internal/q;
    //   64: ldc 252
    //   66: ldc_w 453
    //   69: aload 4
    //   71: invokeinterface 269 4 0
    //   76: aload_2
    //   77: ldc_w 607
    //   80: invokestatic 247	com/crashlytics/android/internal/ab:a	(Ljava/io/Closeable;Ljava/lang/String;)V
    //   83: aconst_null
    //   84: areturn
    //   85: astore_1
    //   86: aconst_null
    //   87: astore_2
    //   88: aload_1
    //   89: astore_3
    //   90: aload_2
    //   91: ldc_w 607
    //   94: invokestatic 247	com/crashlytics/android/internal/ab:a	(Ljava/io/Closeable;Ljava/lang/String;)V
    //   97: aload_3
    //   98: athrow
    //   99: astore_3
    //   100: goto -10 -> 90
    //   103: astore 4
    //   105: goto -47 -> 58
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	108	0	paramContext	Context
    //   85	4	1	localObject1	Object
    //   15	76	2	localInputStream1	InputStream
    //   89	9	3	localObject2	Object
    //   99	1	3	localObject3	Object
    //   54	16	4	localException1	Exception
    //   103	1	4	localException2	Exception
    //   11	3	5	localInputStream2	InputStream
    //   20	28	6	str1	String
    //   27	6	7	bool	boolean
    //   30	20	8	str2	String
    // Exception table:
    //   from	to	target	type
    //   0	13	54	java/lang/Exception
    //   0	13	85	finally
    //   16	29	99	finally
    //   58	76	99	finally
    //   16	29	103	java/lang/Exception
  }
  
  public static int h(Context paramContext)
  {
    return paramContext.getApplicationContext().getApplicationInfo().icon;
  }
  
  public static String i(Context paramContext)
  {
    int i = a(paramContext, "com.crashlytics.android.build_id", "string");
    String str = null;
    if (i != 0)
    {
      str = paramContext.getResources().getString(i);
      v.a().b().a("Crashlytics", "Build ID is: " + str);
    }
    return str;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.crashlytics.android.internal.ab
 * JD-Core Version:    0.7.0.1
 */