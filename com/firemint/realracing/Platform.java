package com.firemint.realracing;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.media.AudioManager;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.OnScanCompletedListener;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.opengl.GLUtils;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Debug;
import android.os.Environment;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import com.crashlytics.android.Crashlytics;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.zip.ZipFile;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.opengles.GL10;

public class Platform
{
  public static final int ASSET_DOWNLOAD_PROGRESS_NOTIFICATION_ID = 2;
  public static final int ASSET_DOWNLOAD_REMINDER_NOTIFICATION_ID = 1;
  public static final int DEFAULT_NOTIFICATION_ID = -2;
  static final int EXTERNAL_MOUNTED_READ_ONLY = 1;
  static final int EXTERNAL_MOUNTED_READ_WRITE = 2;
  static final int EXTERNAL_NOT_MOUNTED = 0;
  public static final int INTERNET_CONNECTION_DELAY = 3600;
  public static final int NETWORK_TYPE_EHRPD = 14;
  public static final int NETWORK_TYPE_EVDO_B = 12;
  public static final int NETWORK_TYPE_HSPAP = 15;
  public static final int NETWORK_TYPE_IDEN = 11;
  public static final int NETWORK_TYPE_LTE = 13;
  public static final int REPEAT_REMINDER_MAX = 2;
  public static final int TWO_DAY_DELAY_SEC = 172800;
  
  public static int GetAudioFramesPerBuffer()
  {
    if (getApiLevel() >= 17) {
      return Integer.parseInt(((AudioManager)MainActivity.instance.getSystemService("audio")).getProperty("android.media.property.OUTPUT_FRAMES_PER_BUFFER"));
    }
    return 0;
  }
  
  public static int GetAudioSampleRate()
  {
    if (getApiLevel() >= 17) {
      return Integer.parseInt(((AudioManager)MainActivity.instance.getSystemService("audio")).getProperty("android.media.property.OUTPUT_SAMPLE_RATE"));
    }
    return 0;
  }
  
  public static String GetDeviceHardware()
  {
    return Build.HARDWARE;
  }
  
  private static void closeStream(Closeable paramCloseable)
  {
    if (paramCloseable != null) {}
    try
    {
      paramCloseable.close();
      return;
    }
    catch (IOException localIOException) {}
  }
  
  public static boolean deleteDir(File paramFile, boolean paramBoolean)
  {
    if (paramFile.isDirectory())
    {
      String[] arrayOfString = paramFile.list();
      for (int i = 0; i < arrayOfString.length; i++) {
        if (!deleteDir(new File(paramFile, arrayOfString[i]), false)) {
          return false;
        }
      }
    }
    if (!paramBoolean) {
      return paramFile.delete();
    }
    return true;
  }
  
  public static boolean deleteDirectory(String paramString)
  {
    return deleteDir(new File(paramString), false);
  }
  
  public static void exitApp()
  {
    Activity localActivity = AppProxy.GetActivity();
    if (localActivity != null) {
      localActivity.runOnUiThread(new Runnable()
      {
        public void run()
        {
          this.val$activity.moveTaskToBack(true);
          this.val$activity.finish();
        }
      });
    }
  }
  
  /* Error */
  private static void extractRes(String paramString)
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore_1
    //   2: aconst_null
    //   3: astore_2
    //   4: aconst_null
    //   5: astore_3
    //   6: aconst_null
    //   7: astore 4
    //   9: aconst_null
    //   10: astore 5
    //   12: new 89	java/io/File
    //   15: dup
    //   16: new 133	java/lang/StringBuilder
    //   19: dup
    //   20: invokespecial 134	java/lang/StringBuilder:<init>	()V
    //   23: aload_0
    //   24: invokevirtual 138	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   27: ldc 140
    //   29: invokevirtual 138	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   32: invokevirtual 143	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   35: invokespecial 110	java/io/File:<init>	(Ljava/lang/String;)V
    //   38: astore 6
    //   40: aload 6
    //   42: invokevirtual 146	java/io/File:exists	()Z
    //   45: ifeq +84 -> 129
    //   48: new 148	java/io/BufferedReader
    //   51: dup
    //   52: new 150	java/io/InputStreamReader
    //   55: dup
    //   56: getstatic 45	com/firemint/realracing/MainActivity:instance	Lcom/firemint/realracing/MainActivity;
    //   59: invokevirtual 154	com/firemint/realracing/MainActivity:getResources	()Landroid/content/res/Resources;
    //   62: ldc 155
    //   64: invokevirtual 161	android/content/res/Resources:openRawResource	(I)Ljava/io/InputStream;
    //   67: invokespecial 164	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   70: invokespecial 167	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   73: astore 25
    //   75: new 169	java/io/FileReader
    //   78: dup
    //   79: aload 6
    //   81: invokespecial 172	java/io/FileReader:<init>	(Ljava/io/File;)V
    //   84: astore 26
    //   86: new 148	java/io/BufferedReader
    //   89: dup
    //   90: aload 26
    //   92: invokespecial 167	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   95: astore 27
    //   97: aload 25
    //   99: invokevirtual 175	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   102: aload 27
    //   104: invokevirtual 175	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   107: invokevirtual 181	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   110: istore 31
    //   112: iload 31
    //   114: ifeq +5 -> 119
    //   117: iconst_0
    //   118: istore_1
    //   119: aload 25
    //   121: invokestatic 183	com/firemint/realracing/Platform:closeStream	(Ljava/io/Closeable;)V
    //   124: aload 27
    //   126: invokestatic 183	com/firemint/realracing/Platform:closeStream	(Ljava/io/Closeable;)V
    //   129: iload_1
    //   130: ifeq +364 -> 494
    //   133: ldc 185
    //   135: ldc 187
    //   137: invokestatic 193	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   140: pop
    //   141: new 89	java/io/File
    //   144: dup
    //   145: aload_0
    //   146: invokespecial 110	java/io/File:<init>	(Ljava/lang/String;)V
    //   149: astore 19
    //   151: aload 19
    //   153: iconst_1
    //   154: invokestatic 102	com/firemint/realracing/Platform:deleteDir	(Ljava/io/File;Z)Z
    //   157: pop
    //   158: invokestatic 197	com/firemint/realracing/AppProxy:GetContext	()Landroid/content/Context;
    //   161: invokevirtual 200	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   164: ldc 155
    //   166: invokevirtual 161	android/content/res/Resources:openRawResource	(I)Ljava/io/InputStream;
    //   169: astore 4
    //   171: new 202	java/io/FileOutputStream
    //   174: dup
    //   175: aload 6
    //   177: invokespecial 203	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   180: astore 24
    //   182: aload 4
    //   184: aload 24
    //   186: invokestatic 207	com/firemint/realracing/Platform:saveStream	(Ljava/io/InputStream;Ljava/io/FileOutputStream;)V
    //   189: aload 4
    //   191: invokestatic 183	com/firemint/realracing/Platform:closeStream	(Ljava/io/Closeable;)V
    //   194: aload 24
    //   196: invokestatic 183	com/firemint/realracing/Platform:closeStream	(Ljava/io/Closeable;)V
    //   199: aload 24
    //   201: astore 5
    //   203: new 89	java/io/File
    //   206: dup
    //   207: new 133	java/lang/StringBuilder
    //   210: dup
    //   211: invokespecial 134	java/lang/StringBuilder:<init>	()V
    //   214: aload_0
    //   215: invokevirtual 138	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   218: ldc 209
    //   220: invokevirtual 138	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   223: invokevirtual 143	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   226: invokespecial 110	java/io/File:<init>	(Ljava/lang/String;)V
    //   229: astore 8
    //   231: invokestatic 197	com/firemint/realracing/AppProxy:GetContext	()Landroid/content/Context;
    //   234: invokevirtual 200	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   237: ldc 210
    //   239: invokevirtual 161	android/content/res/Resources:openRawResource	(I)Ljava/io/InputStream;
    //   242: astore 4
    //   244: iload_1
    //   245: ifne +26 -> 271
    //   248: aload 8
    //   250: invokevirtual 146	java/io/File:exists	()Z
    //   253: ifeq +18 -> 271
    //   256: aload 8
    //   258: invokevirtual 214	java/io/File:length	()J
    //   261: aload 4
    //   263: invokevirtual 219	java/io/InputStream:available	()I
    //   266: i2l
    //   267: lcmp
    //   268: ifeq +33 -> 301
    //   271: ldc 185
    //   273: ldc 221
    //   275: invokestatic 193	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   278: pop
    //   279: new 202	java/io/FileOutputStream
    //   282: dup
    //   283: aload 8
    //   285: invokespecial 203	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   288: astore 17
    //   290: aload 4
    //   292: aload 17
    //   294: invokestatic 207	com/firemint/realracing/Platform:saveStream	(Ljava/io/InputStream;Ljava/io/FileOutputStream;)V
    //   297: aload 17
    //   299: astore 5
    //   301: aload 4
    //   303: invokestatic 183	com/firemint/realracing/Platform:closeStream	(Ljava/io/Closeable;)V
    //   306: aload 5
    //   308: invokestatic 183	com/firemint/realracing/Platform:closeStream	(Ljava/io/Closeable;)V
    //   311: new 223	java/util/zip/ZipFile
    //   314: dup
    //   315: aload 8
    //   317: invokevirtual 226	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   320: invokespecial 227	java/util/zip/ZipFile:<init>	(Ljava/lang/String;)V
    //   323: astore 12
    //   325: iload_1
    //   326: ifeq +8 -> 334
    //   329: aload 12
    //   331: invokestatic 231	com/firemint/realracing/Platform:updateResources	(Ljava/util/zip/ZipFile;)V
    //   334: aload 12
    //   336: aload_0
    //   337: iload_1
    //   338: ldc 233
    //   340: ldc 235
    //   342: invokestatic 239	com/firemint/realracing/Platform:extractResZipFile	(Ljava/util/zip/ZipFile;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;)V
    //   345: ldc 185
    //   347: ldc 241
    //   349: invokestatic 193	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   352: pop
    //   353: return
    //   354: astore 28
    //   356: ldc 185
    //   358: new 133	java/lang/StringBuilder
    //   361: dup
    //   362: invokespecial 134	java/lang/StringBuilder:<init>	()V
    //   365: ldc 243
    //   367: invokevirtual 138	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   370: aload 6
    //   372: invokevirtual 226	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   375: invokevirtual 138	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   378: ldc 245
    //   380: invokevirtual 138	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   383: aload 28
    //   385: invokevirtual 248	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   388: invokevirtual 138	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   391: invokevirtual 143	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   394: invokestatic 251	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   397: pop
    //   398: aload_2
    //   399: invokestatic 183	com/firemint/realracing/Platform:closeStream	(Ljava/io/Closeable;)V
    //   402: aload_3
    //   403: invokestatic 183	com/firemint/realracing/Platform:closeStream	(Ljava/io/Closeable;)V
    //   406: goto -277 -> 129
    //   409: astore 29
    //   411: aload_2
    //   412: invokestatic 183	com/firemint/realracing/Platform:closeStream	(Ljava/io/Closeable;)V
    //   415: aload_3
    //   416: invokestatic 183	com/firemint/realracing/Platform:closeStream	(Ljava/io/Closeable;)V
    //   419: aload 29
    //   421: athrow
    //   422: astore 22
    //   424: ldc 185
    //   426: new 133	java/lang/StringBuilder
    //   429: dup
    //   430: invokespecial 134	java/lang/StringBuilder:<init>	()V
    //   433: ldc 253
    //   435: invokevirtual 138	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   438: aload 6
    //   440: invokevirtual 226	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   443: invokevirtual 138	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   446: ldc 245
    //   448: invokevirtual 138	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   451: aload 22
    //   453: invokevirtual 248	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   456: invokevirtual 138	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   459: invokevirtual 143	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   462: invokestatic 251	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   465: pop
    //   466: aload 4
    //   468: invokestatic 183	com/firemint/realracing/Platform:closeStream	(Ljava/io/Closeable;)V
    //   471: aload 5
    //   473: invokestatic 183	com/firemint/realracing/Platform:closeStream	(Ljava/io/Closeable;)V
    //   476: goto -273 -> 203
    //   479: astore 21
    //   481: aload 4
    //   483: invokestatic 183	com/firemint/realracing/Platform:closeStream	(Ljava/io/Closeable;)V
    //   486: aload 5
    //   488: invokestatic 183	com/firemint/realracing/Platform:closeStream	(Ljava/io/Closeable;)V
    //   491: aload 21
    //   493: athrow
    //   494: ldc 185
    //   496: ldc 255
    //   498: invokestatic 193	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   501: pop
    //   502: aconst_null
    //   503: astore 4
    //   505: aconst_null
    //   506: astore 5
    //   508: goto -305 -> 203
    //   511: astore 10
    //   513: ldc 185
    //   515: new 133	java/lang/StringBuilder
    //   518: dup
    //   519: invokespecial 134	java/lang/StringBuilder:<init>	()V
    //   522: ldc_w 257
    //   525: invokevirtual 138	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   528: aload 8
    //   530: invokevirtual 226	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   533: invokevirtual 138	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   536: ldc 245
    //   538: invokevirtual 138	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   541: aload 10
    //   543: invokevirtual 248	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   546: invokevirtual 138	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   549: invokevirtual 143	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   552: invokestatic 251	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   555: pop
    //   556: aload 4
    //   558: invokestatic 183	com/firemint/realracing/Platform:closeStream	(Ljava/io/Closeable;)V
    //   561: aload 5
    //   563: invokestatic 183	com/firemint/realracing/Platform:closeStream	(Ljava/io/Closeable;)V
    //   566: goto -255 -> 311
    //   569: astore 9
    //   571: aload 4
    //   573: invokestatic 183	com/firemint/realracing/Platform:closeStream	(Ljava/io/Closeable;)V
    //   576: aload 5
    //   578: invokestatic 183	com/firemint/realracing/Platform:closeStream	(Ljava/io/Closeable;)V
    //   581: aload 9
    //   583: athrow
    //   584: astore 14
    //   586: ldc 185
    //   588: new 133	java/lang/StringBuilder
    //   591: dup
    //   592: invokespecial 134	java/lang/StringBuilder:<init>	()V
    //   595: ldc_w 259
    //   598: invokevirtual 138	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   601: aload 8
    //   603: invokevirtual 226	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   606: invokevirtual 138	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   609: ldc 245
    //   611: invokevirtual 138	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   614: aload 14
    //   616: invokevirtual 260	java/io/IOException:getMessage	()Ljava/lang/String;
    //   619: invokevirtual 138	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   622: invokevirtual 143	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   625: invokestatic 251	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   628: pop
    //   629: return
    //   630: astore 9
    //   632: aload 17
    //   634: astore 5
    //   636: goto -65 -> 571
    //   639: astore 10
    //   641: aload 17
    //   643: astore 5
    //   645: goto -132 -> 513
    //   648: astore 21
    //   650: aload 24
    //   652: astore 5
    //   654: goto -173 -> 481
    //   657: astore 22
    //   659: aload 24
    //   661: astore 5
    //   663: goto -239 -> 424
    //   666: astore 29
    //   668: aload 25
    //   670: astore_2
    //   671: aconst_null
    //   672: astore_3
    //   673: goto -262 -> 411
    //   676: astore 29
    //   678: aload 27
    //   680: astore_3
    //   681: aload 25
    //   683: astore_2
    //   684: goto -273 -> 411
    //   687: astore 28
    //   689: aload 25
    //   691: astore_2
    //   692: aconst_null
    //   693: astore_3
    //   694: goto -338 -> 356
    //   697: astore 28
    //   699: aload 27
    //   701: astore_3
    //   702: aload 25
    //   704: astore_2
    //   705: goto -349 -> 356
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	708	0	paramString	String
    //   1	337	1	bool1	boolean
    //   3	702	2	localObject1	Object
    //   5	697	3	localObject2	Object
    //   7	565	4	localInputStream	InputStream
    //   10	652	5	localObject3	Object
    //   38	401	6	localFile1	File
    //   229	373	8	localFile2	File
    //   569	13	9	localObject4	Object
    //   630	1	9	localObject5	Object
    //   511	31	10	localException1	Exception
    //   639	1	10	localException2	Exception
    //   323	12	12	localZipFile	ZipFile
    //   584	31	14	localIOException	IOException
    //   288	354	17	localFileOutputStream1	FileOutputStream
    //   149	3	19	localFile3	File
    //   479	13	21	localObject6	Object
    //   648	1	21	localObject7	Object
    //   422	30	22	localException3	Exception
    //   657	1	22	localException4	Exception
    //   180	480	24	localFileOutputStream2	FileOutputStream
    //   73	630	25	localBufferedReader1	BufferedReader
    //   84	7	26	localFileReader	FileReader
    //   95	605	27	localBufferedReader2	BufferedReader
    //   354	30	28	localException5	Exception
    //   687	1	28	localException6	Exception
    //   697	1	28	localException7	Exception
    //   409	11	29	localObject8	Object
    //   666	1	29	localObject9	Object
    //   676	1	29	localObject10	Object
    //   110	3	31	bool2	boolean
    // Exception table:
    //   from	to	target	type
    //   48	75	354	java/lang/Exception
    //   48	75	409	finally
    //   356	398	409	finally
    //   158	182	422	java/lang/Exception
    //   158	182	479	finally
    //   424	466	479	finally
    //   231	244	511	java/lang/Exception
    //   248	271	511	java/lang/Exception
    //   271	290	511	java/lang/Exception
    //   231	244	569	finally
    //   248	271	569	finally
    //   271	290	569	finally
    //   513	556	569	finally
    //   311	325	584	java/io/IOException
    //   290	297	630	finally
    //   290	297	639	java/lang/Exception
    //   182	189	648	finally
    //   182	189	657	java/lang/Exception
    //   75	97	666	finally
    //   97	112	676	finally
    //   75	97	687	java/lang/Exception
    //   97	112	697	java/lang/Exception
  }
  
  /* Error */
  private static void extractResZipFile(ZipFile paramZipFile, String paramString1, boolean paramBoolean, String paramString2, String paramString3)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 264	java/util/zip/ZipFile:entries	()Ljava/util/Enumeration;
    //   4: astore 5
    //   6: aload 5
    //   8: invokeinterface 269 1 0
    //   13: ifeq +406 -> 419
    //   16: aload 5
    //   18: invokeinterface 273 1 0
    //   23: checkcast 275	java/util/zip/ZipEntry
    //   26: astore 6
    //   28: aload 6
    //   30: invokevirtual 278	java/util/zip/ZipEntry:getName	()Ljava/lang/String;
    //   33: astore 7
    //   35: aload_3
    //   36: invokevirtual 281	java/lang/String:isEmpty	()Z
    //   39: ifne +23 -> 62
    //   42: aload 7
    //   44: aload_3
    //   45: invokevirtual 284	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   48: ifeq +14 -> 62
    //   51: aload 7
    //   53: aload_3
    //   54: invokevirtual 286	java/lang/String:length	()I
    //   57: invokevirtual 290	java/lang/String:substring	(I)Ljava/lang/String;
    //   60: astore 7
    //   62: aload 7
    //   64: invokevirtual 281	java/lang/String:isEmpty	()Z
    //   67: ifne -61 -> 6
    //   70: new 89	java/io/File
    //   73: dup
    //   74: new 133	java/lang/StringBuilder
    //   77: dup
    //   78: invokespecial 134	java/lang/StringBuilder:<init>	()V
    //   81: aload_1
    //   82: invokevirtual 138	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   85: ldc_w 292
    //   88: invokevirtual 138	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   91: aload 7
    //   93: invokevirtual 138	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   96: invokevirtual 143	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   99: invokespecial 110	java/io/File:<init>	(Ljava/lang/String;)V
    //   102: astore 8
    //   104: aload 6
    //   106: invokevirtual 293	java/util/zip/ZipEntry:isDirectory	()Z
    //   109: ifeq +58 -> 167
    //   112: aload 8
    //   114: invokevirtual 146	java/io/File:exists	()Z
    //   117: ifne -111 -> 6
    //   120: ldc 185
    //   122: new 133	java/lang/StringBuilder
    //   125: dup
    //   126: invokespecial 134	java/lang/StringBuilder:<init>	()V
    //   129: aload 4
    //   131: invokevirtual 138	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   134: ldc_w 295
    //   137: invokevirtual 138	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   140: aload 7
    //   142: invokevirtual 138	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   145: ldc_w 297
    //   148: invokevirtual 138	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   151: invokevirtual 143	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   154: invokestatic 193	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   157: pop
    //   158: aload 8
    //   160: invokevirtual 300	java/io/File:mkdirs	()Z
    //   163: pop
    //   164: goto -158 -> 6
    //   167: iload_2
    //   168: ifne +25 -> 193
    //   171: aload 8
    //   173: invokevirtual 146	java/io/File:exists	()Z
    //   176: ifeq +17 -> 193
    //   179: aload 8
    //   181: invokevirtual 214	java/io/File:length	()J
    //   184: aload 6
    //   186: invokevirtual 303	java/util/zip/ZipEntry:getSize	()J
    //   189: lcmp
    //   190: ifeq -184 -> 6
    //   193: aload 8
    //   195: invokevirtual 307	java/io/File:getParentFile	()Ljava/io/File;
    //   198: astore 9
    //   200: aload 9
    //   202: invokevirtual 146	java/io/File:exists	()Z
    //   205: ifne +50 -> 255
    //   208: ldc 185
    //   210: new 133	java/lang/StringBuilder
    //   213: dup
    //   214: invokespecial 134	java/lang/StringBuilder:<init>	()V
    //   217: aload 4
    //   219: invokevirtual 138	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   222: ldc_w 295
    //   225: invokevirtual 138	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   228: aload 9
    //   230: invokevirtual 310	java/io/File:getPath	()Ljava/lang/String;
    //   233: invokevirtual 138	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   236: ldc_w 297
    //   239: invokevirtual 138	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   242: invokevirtual 143	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   245: invokestatic 193	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   248: pop
    //   249: aload 9
    //   251: invokevirtual 300	java/io/File:mkdirs	()Z
    //   254: pop
    //   255: ldc 185
    //   257: new 133	java/lang/StringBuilder
    //   260: dup
    //   261: invokespecial 134	java/lang/StringBuilder:<init>	()V
    //   264: aload 4
    //   266: invokevirtual 138	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   269: ldc_w 312
    //   272: invokevirtual 138	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   275: aload 7
    //   277: invokevirtual 138	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   280: ldc_w 297
    //   283: invokevirtual 138	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   286: invokevirtual 143	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   289: invokestatic 193	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   292: pop
    //   293: aconst_null
    //   294: astore 11
    //   296: aconst_null
    //   297: astore 12
    //   299: aload_0
    //   300: aload 6
    //   302: invokevirtual 316	java/util/zip/ZipFile:getInputStream	(Ljava/util/zip/ZipEntry;)Ljava/io/InputStream;
    //   305: astore 11
    //   307: new 202	java/io/FileOutputStream
    //   310: dup
    //   311: aload 8
    //   313: invokespecial 203	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   316: astore 16
    //   318: aload 11
    //   320: aload 16
    //   322: invokestatic 207	com/firemint/realracing/Platform:saveStream	(Ljava/io/InputStream;Ljava/io/FileOutputStream;)V
    //   325: aload 11
    //   327: invokestatic 183	com/firemint/realracing/Platform:closeStream	(Ljava/io/Closeable;)V
    //   330: aload 16
    //   332: invokestatic 183	com/firemint/realracing/Platform:closeStream	(Ljava/io/Closeable;)V
    //   335: goto -329 -> 6
    //   338: astore 14
    //   340: ldc 185
    //   342: new 133	java/lang/StringBuilder
    //   345: dup
    //   346: invokespecial 134	java/lang/StringBuilder:<init>	()V
    //   349: aload 4
    //   351: invokevirtual 138	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   354: ldc_w 318
    //   357: invokevirtual 138	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   360: aload 7
    //   362: invokevirtual 138	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   365: ldc_w 297
    //   368: invokevirtual 138	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   371: ldc 245
    //   373: invokevirtual 138	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   376: aload 14
    //   378: invokevirtual 248	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   381: invokevirtual 138	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   384: invokevirtual 143	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   387: invokestatic 251	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   390: pop
    //   391: aload 11
    //   393: invokestatic 183	com/firemint/realracing/Platform:closeStream	(Ljava/io/Closeable;)V
    //   396: aload 12
    //   398: invokestatic 183	com/firemint/realracing/Platform:closeStream	(Ljava/io/Closeable;)V
    //   401: goto -395 -> 6
    //   404: astore 13
    //   406: aload 11
    //   408: invokestatic 183	com/firemint/realracing/Platform:closeStream	(Ljava/io/Closeable;)V
    //   411: aload 12
    //   413: invokestatic 183	com/firemint/realracing/Platform:closeStream	(Ljava/io/Closeable;)V
    //   416: aload 13
    //   418: athrow
    //   419: return
    //   420: astore 13
    //   422: aload 16
    //   424: astore 12
    //   426: goto -20 -> 406
    //   429: astore 14
    //   431: aload 16
    //   433: astore 12
    //   435: goto -95 -> 340
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	438	0	paramZipFile	ZipFile
    //   0	438	1	paramString1	String
    //   0	438	2	paramBoolean	boolean
    //   0	438	3	paramString2	String
    //   0	438	4	paramString3	String
    //   4	13	5	localEnumeration	java.util.Enumeration
    //   26	275	6	localZipEntry	java.util.zip.ZipEntry
    //   33	328	7	str	String
    //   102	210	8	localFile1	File
    //   198	52	9	localFile2	File
    //   294	113	11	localInputStream	InputStream
    //   297	137	12	localObject1	Object
    //   404	13	13	localObject2	Object
    //   420	1	13	localObject3	Object
    //   338	39	14	localException1	Exception
    //   429	1	14	localException2	Exception
    //   316	116	16	localFileOutputStream	FileOutputStream
    // Exception table:
    //   from	to	target	type
    //   299	318	338	java/lang/Exception
    //   299	318	404	finally
    //   340	391	404	finally
    //   318	325	420	finally
    //   318	325	429	java/lang/Exception
  }
  
  public static int getApiLevel()
  {
    return Build.VERSION.SDK_INT;
  }
  
  public static long getAppInstallTime()
  {
    try
    {
      Context localContext = AppProxy.GetContext();
      long l = localContext.getPackageManager().getPackageInfo(localContext.getPackageName(), 0).lastUpdateTime;
      return l;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
    return 0L;
  }
  
  public static long getAppMemoryFree()
  {
    return Runtime.getRuntime().freeMemory() + Debug.getNativeHeapFreeSize();
  }
  
  public static long getAppMemoryUsage()
  {
    return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() + Debug.getNativeHeapAllocatedSize();
  }
  
  public static String getAppName()
  {
    try
    {
      Context localContext = AppProxy.GetContext();
      PackageManager localPackageManager = localContext.getPackageManager();
      String str = localPackageManager.getApplicationLabel(localPackageManager.getApplicationInfo(localContext.getPackageName(), 0)).toString();
      return str;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
    return "";
  }
  
  public static String getAppPath()
  {
    try
    {
      Context localContext = AppProxy.GetContext();
      String str = localContext.getPackageManager().getApplicationInfo(localContext.getPackageName(), 0).sourceDir;
      return str;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
    return "";
  }
  
  public static String getAppVersion()
  {
    try
    {
      Context localContext = AppProxy.GetContext();
      String str = localContext.getPackageManager().getPackageInfo(localContext.getPackageName(), 0).versionName;
      return str;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
    return "";
  }
  
  public static Object getAssetManager()
  {
    return MainActivity.instance.getAssets();
  }
  
  public static String getCarrier()
  {
    return ((TelephonyManager)AppProxy.GetContext().getSystemService("phone")).getNetworkOperatorName();
  }
  
  public static String getDeviceUID()
  {
    return Settings.Secure.getString(AppProxy.GetContext().getContentResolver(), "android_id");
  }
  
  public static String getExternalStorageDir()
  {
    File localFile = Environment.getExternalStorageDirectory();
    if (localFile != null)
    {
      String str = AppProxy.GetContext().getPackageName();
      return localFile.getAbsolutePath() + "/Android/data/" + str;
    }
    return "";
  }
  
  public static int getExternalStorageState()
  {
    String str = Environment.getExternalStorageState();
    if (str.equals("mounted")) {
      return 2;
    }
    if (str.equals("mounted_ro")) {
      return 1;
    }
    return 0;
  }
  
  public static String getInternalStorageDir()
  {
    return AppProxy.GetContext().getFilesDir().getAbsolutePath();
  }
  
  public static String getLocale()
  {
    String str = Locale.getDefault().toString();
    Log.i("Locale", "locale = " + str);
    return str;
  }
  
  public static DisplayMetrics getMetrics()
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    if (Build.VERSION.SDK_INT >= 19)
    {
      AppProxy.GetActivity().getWindowManager().getDefaultDisplay().getRealMetrics(localDisplayMetrics);
      return localDisplayMetrics;
    }
    AppProxy.GetActivity().getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics;
  }
  
  public static String getModelID()
  {
    return Build.MODEL;
  }
  
  public static String getModelName()
  {
    return Build.PRODUCT;
  }
  
  private static int getNextPOT(int paramInt)
  {
    int i = 1;
    while (i < paramInt) {
      i *= 2;
    }
    return i;
  }
  
  public static String getOsVersion()
  {
    return Build.VERSION.RELEASE;
  }
  
  public static int getScreenDPI()
  {
    return getMetrics().densityDpi;
  }
  
  public static int getScreenHeight()
  {
    return getMetrics().heightPixels;
  }
  
  public static int getScreenRotation()
  {
    return MainActivity.instance.getScreenRotation();
  }
  
  public static int getScreenWidth()
  {
    return getMetrics().widthPixels;
  }
  
  public static int getTotalMemory()
  {
    localFile = new File("/proc/meminfo");
    boolean bool = localFile.exists();
    int i = 0;
    if (bool) {}
    for (;;)
    {
      try
      {
        localBufferedReader = new BufferedReader(new FileReader(localFile));
        String str = localBufferedReader.readLine();
        i = 0;
        if (str != null)
        {
          Log.d("mem", str);
          String[] arrayOfString1 = str.split(":");
          Log.d("mem", arrayOfString1[0]);
          Log.d("mem", arrayOfString1[1]);
          if (!arrayOfString1[0].trim().toLowerCase().equals("memtotal")) {
            continue;
          }
          arrayOfString2 = arrayOfString1[1].trim().split(" ");
          Log.d("mem", arrayOfString2[0]);
        }
      }
      catch (Exception localException)
      {
        BufferedReader localBufferedReader;
        String[] arrayOfString2;
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = localFile.getAbsolutePath();
        arrayOfObject[1] = localException.getMessage();
        Log.e("RealRacing3", String.format("Error reading system file: %s (%s)", arrayOfObject));
        continue;
      }
      try
      {
        i = Integer.parseInt(arrayOfString2[0].trim()) / 1024;
        localBufferedReader.close();
        Log.d("mem", "totalmemory=" + i);
        return i;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        Log.d("mem", localNumberFormatException.toString());
        i = 0;
      }
    }
  }
  
  public static String humanByteSize(long paramLong)
  {
    if (1 != 0) {}
    for (int i = 1000; paramLong < i; i = 1024) {
      return paramLong + " B";
    }
    int j = (int)(Math.log(paramLong) / Math.log(i));
    StringBuilder localStringBuilder1 = new StringBuilder();
    String str1;
    StringBuilder localStringBuilder2;
    if (1 != 0)
    {
      str1 = "kMGTPE";
      localStringBuilder2 = localStringBuilder1.append(str1.charAt(j - 1));
      if (1 == 0) {
        break label155;
      }
    }
    label155:
    for (String str2 = "";; str2 = "i")
    {
      String str3 = str2;
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = Double.valueOf(paramLong / Math.pow(i, j));
      arrayOfObject[1] = str3;
      return String.format("%.1f %sB", arrayOfObject);
      str1 = "KMGTPE";
      break;
    }
  }
  
  public static boolean isMobileDataAvailable()
  {
    Context localContext = AppProxy.GetContext();
    boolean bool = false;
    NetworkInfo localNetworkInfo;
    if (localContext != null)
    {
      ConnectivityManager localConnectivityManager = (ConnectivityManager)AppProxy.GetContext().getSystemService("connectivity");
      localConnectivityManager.getNetworkInfo(0);
      localNetworkInfo = localConnectivityManager.getActiveNetworkInfo();
      if ((localNetworkInfo == null) || (!localNetworkInfo.isConnectedOrConnecting())) {
        break label127;
      }
      bool = true;
      Log.i("RealRacing3", "isMobileDataAvailable hasConnection=" + bool);
      if (bool)
      {
        if (localNetworkInfo.getType() != 6) {
          break label132;
        }
        Log.i("RealRacing3", "isMobileDataAvailable ConnectivityManager.TYPE_WIMAX");
      }
    }
    for (;;)
    {
      Log.i("RealRacing3", "isMobileDataAvailable hasConnection=" + bool);
      return bool;
      label127:
      bool = false;
      break;
      label132:
      Log.i("RealRacing3", "isMobileDataAvailable subtype=" + localNetworkInfo.getSubtype());
      Log.i("RealRacing3", "isMobileDataAvailable subtypeName=" + localNetworkInfo.getSubtypeName());
      switch (localNetworkInfo.getSubtype())
      {
      }
      bool = false;
    }
  }
  
  public static boolean isNetworkAvailable(int paramInt)
  {
    int i = 1;
    Context localContext = AppProxy.GetContext();
    int j = 0;
    NetworkInfo localNetworkInfo;
    if (localContext != null)
    {
      localNetworkInfo = ((ConnectivityManager)AppProxy.GetContext().getSystemService("connectivity")).getActiveNetworkInfo();
      j = 0;
      if (localNetworkInfo != null)
      {
        j = 1;
        if ((paramInt & NetworkType.Any.value) != NetworkType.Any.value) {
          break label82;
        }
        if ((!localNetworkInfo.isConnected()) || (!localNetworkInfo.isAvailable())) {
          break label77;
        }
        j &= i;
      }
    }
    label77:
    label82:
    do
    {
      return j;
      i = 0;
      break;
      if ((paramInt & NetworkType.Unmetered.value) != 0)
      {
        int k;
        if (localNetworkInfo.getType() != i)
        {
          int m = localNetworkInfo.getType();
          k = 0;
          if (m != 9) {}
        }
        else
        {
          k = i;
        }
        return j & k;
      }
      if ((paramInt & NetworkType.Wifi.value) != 0)
      {
        if (localNetworkInfo.getType() == i) {}
        for (;;)
        {
          return j & i;
          i = 0;
        }
      }
    } while ((paramInt & NetworkType.Ethernet.value) == 0);
    if (localNetworkInfo.getType() == 9) {}
    for (;;)
    {
      return j & i;
      i = 0;
    }
  }
  
  public static boolean isNetworkSettingsShown()
  {
    return MainActivity.m_WIFISettingsShown;
  }
  
  public static TextureInfo loadTextureFromMemory(byte[] paramArrayOfByte, int paramInt)
  {
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inPreferredConfig = Bitmap.Config.RGB_565;
    Bitmap localBitmap1 = BitmapFactory.decodeByteArray(paramArrayOfByte, 0, paramInt, localOptions);
    TextureInfo localTextureInfo = null;
    if (localBitmap1 != null)
    {
      localTextureInfo = new TextureInfo();
      int i = localBitmap1.getWidth();
      localTextureInfo.width = i;
      int j = localBitmap1.getHeight();
      localTextureInfo.height = j;
      int k = getNextPOT(localTextureInfo.width);
      localTextureInfo.texWidth = k;
      int m = getNextPOT(localTextureInfo.height);
      localTextureInfo.texHeight = m;
      Matrix localMatrix = new Matrix();
      localMatrix.setScale(1.0F, -1.0F);
      localMatrix.postTranslate(0.0F, localTextureInfo.height);
      Bitmap localBitmap2 = Bitmap.createBitmap(localBitmap1, 0, 0, localTextureInfo.width, localTextureInfo.height, localMatrix, false);
      int[] arrayOfInt = new int[1];
      GL10 localGL10 = (GL10)MainActivity.instance.getGLView().getGLContext().getGL();
      localGL10.glGenTextures(1, arrayOfInt, 0);
      int n = arrayOfInt[0];
      localTextureInfo.texId = n;
      localGL10.glBindTexture(3553, localTextureInfo.texId);
      localGL10.glTexParameterf(3553, 10241, 9729.0F);
      localGL10.glTexParameterf(3553, 10240, 9729.0F);
      localGL10.glTexParameterf(3553, 10242, 10497.0F);
      localGL10.glTexParameterf(3553, 10243, 10497.0F);
      localGL10.glTexImage2D(3553, 0, GLUtils.getInternalFormat(localBitmap2), localTextureInfo.texWidth, localTextureInfo.texHeight, 0, GLUtils.getInternalFormat(localBitmap2), GLUtils.getType(localBitmap2), null);
      GLUtils.texSubImage2D(3553, 0, 0, localTextureInfo.texHeight - localTextureInfo.height, localBitmap2);
      Log.i("Platform", "loadTextureFromMemory() U=" + localTextureInfo.width + " V=" + localTextureInfo.height + " W=" + localTextureInfo.texWidth + " H=" + localTextureInfo.texHeight);
    }
    return localTextureInfo;
  }
  
  public static void memoryProbe()
  {
    Log.e("RealRacing3", "Memory probing now...");
    System.gc();
    System.gc();
    Runtime localRuntime = Runtime.getRuntime();
    long l1 = Debug.getNativeHeapSize();
    long l2 = Debug.getNativeHeapAllocatedSize();
    long l3 = Debug.getNativeHeapFreeSize();
    long l4 = localRuntime.maxMemory();
    long l5 = localRuntime.totalMemory();
    long l6 = localRuntime.freeMemory();
    Object[] arrayOfObject1 = new Object[1];
    arrayOfObject1[0] = humanByteSize(l1);
    Log.e("RealRacing3", String.format("Native max memory: %s", arrayOfObject1));
    Object[] arrayOfObject2 = new Object[1];
    arrayOfObject2[0] = humanByteSize(l2);
    Log.e("RealRacing3", String.format("Native alloc memory: %s", arrayOfObject2));
    Object[] arrayOfObject3 = new Object[1];
    arrayOfObject3[0] = humanByteSize(l3);
    Log.e("RealRacing3", String.format("Native free memory: %s", arrayOfObject3));
    Object[] arrayOfObject4 = new Object[1];
    arrayOfObject4[0] = humanByteSize(l4);
    Log.e("RealRacing3", String.format("Max memory: %s", arrayOfObject4));
    Object[] arrayOfObject5 = new Object[1];
    arrayOfObject5[0] = humanByteSize(l5);
    Log.e("RealRacing3", String.format("Total memory: %s", arrayOfObject5));
    Object[] arrayOfObject6 = new Object[1];
    arrayOfObject6[0] = humanByteSize(l6);
    Log.e("RealRacing3", String.format("Free memory: %s", arrayOfObject6));
    ActivityManager localActivityManager = (ActivityManager)MainActivity.instance.getSystemService("activity");
    ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
    localActivityManager.getMemoryInfo(localMemoryInfo);
    Object[] arrayOfObject7 = new Object[1];
    arrayOfObject7[0] = humanByteSize(localMemoryInfo.availMem);
    Log.e("RealRacing3", String.format("TOTAL Available memory: %s", arrayOfObject7));
    Object[] arrayOfObject8 = new Object[1];
    arrayOfObject8[0] = humanByteSize(localMemoryInfo.threshold);
    Log.e("RealRacing3", String.format("TOTAL Low memory threshold: %s", arrayOfObject8));
    Object[] arrayOfObject9 = new Object[1];
    if (localMemoryInfo.lowMemory) {}
    for (String str = "TRUE";; str = "false")
    {
      arrayOfObject9[0] = str;
      Log.e("RealRacing3", String.format("TOTAL Low memory flag: %s", arrayOfObject9));
      Log.e("RealRacing3", "Memory probing DONE.");
      return;
    }
  }
  
  public static void openNetworkSettings()
  {
    if (MainActivity.instance != null)
    {
      MainActivity.m_WIFISettingsShown = true;
      MainActivity localMainActivity = MainActivity.instance;
      Intent localIntent = new Intent("android.settings.WIFI_SETTINGS");
      localMainActivity.startActivityForResult(localIntent, 329418467);
    }
  }
  
  public static void openURL(String paramString)
  {
    Log.e("RealRacing3", "Opening URL '" + paramString + "'");
    Intent localIntent = new Intent("android.intent.action.VIEW").setData(Uri.parse(paramString));
    AppProxy.GetContext().startActivity(localIntent);
  }
  
  public static boolean saveImage(int[] paramArrayOfInt, int paramInt1, int paramInt2, int paramInt3, String paramString)
  {
    File localFile1 = new File(paramString);
    File localFile2 = localFile1.getParentFile();
    if (!localFile2.exists()) {
      localFile2.mkdirs();
    }
    return saveImageToFile(paramArrayOfInt, paramInt1, paramInt2, paramInt3, localFile1);
  }
  
  private static boolean saveImageToFile(int[] paramArrayOfInt, int paramInt1, int paramInt2, int paramInt3, File paramFile)
  {
    Log.i("RealRacing3", "Saving to gallery: " + paramFile.toString());
    int i = 0;
    for (;;)
    {
      if (i < paramInt1) {}
      try
      {
        paramArrayOfInt[i] = (0xFF00FF00 & paramArrayOfInt[i] | (0xFF & paramArrayOfInt[i]) << 16 | (0xFF0000 & paramArrayOfInt[i]) >> 16);
        i++;
      }
      catch (Exception localException)
      {
        Bitmap localBitmap;
        Log.i("RealRacing3", "Error: " + localException.toString());
        return false;
      }
    }
    localBitmap = Bitmap.createBitmap(paramArrayOfInt, paramInt1 - paramInt2, -paramInt2, paramInt2, paramInt3, Bitmap.Config.ARGB_8888);
    localBitmap.setHasAlpha(false);
    FileOutputStream localFileOutputStream = new FileOutputStream(paramFile);
    if ((paramFile.getName().endsWith(".png")) || (paramFile.getName().endsWith(".PNG"))) {
      localBitmap.compress(Bitmap.CompressFormat.PNG, 100, localFileOutputStream);
    }
    for (;;)
    {
      localFileOutputStream.flush();
      localFileOutputStream.close();
      Context localContext = AppProxy.GetContext();
      String[] arrayOfString = new String[1];
      arrayOfString[0] = paramFile.toString();
      MediaScannerConnection.scanFile(localContext, arrayOfString, null, new MediaScannerConnection.OnScanCompletedListener()
      {
        public void onScanCompleted(String paramAnonymousString, Uri paramAnonymousUri)
        {
          Log.i("ExternalStorage", "Scanned " + paramAnonymousString + ":");
          Log.i("ExternalStorage", "-> uri=" + paramAnonymousUri);
        }
      });
      Log.i("RealRacing3", "Image saved!");
      return true;
      if ((!paramFile.getName().endsWith(".jpg")) && (!paramFile.getName().endsWith(".JPG"))) {
        break;
      }
      localBitmap.compress(Bitmap.CompressFormat.JPEG, 100, localFileOutputStream);
    }
    Log.i("MainActivity.LOG_TAG", "Error: Unrecognised image file extension on filename: " + paramFile.getName());
    localFileOutputStream.flush();
    localFileOutputStream.close();
    return false;
  }
  
  private static void saveStream(InputStream paramInputStream, FileOutputStream paramFileOutputStream)
    throws IOException
  {
    byte[] arrayOfByte = new byte[8192];
    for (;;)
    {
      int i = paramInputStream.read(arrayOfByte);
      if (i == -1) {
        break;
      }
      paramFileOutputStream.write(arrayOfByte, 0, i);
    }
  }
  
  public static boolean saveToImageGallery(int[] paramArrayOfInt, int paramInt1, int paramInt2, int paramInt3, String paramString)
  {
    File localFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "RealRacing3");
    if (!localFile.exists()) {
      localFile.mkdirs();
    }
    return saveImageToFile(paramArrayOfInt, paramInt1, paramInt2, paramInt3, new File(localFile, paramString));
  }
  
  public static void setCrashlyticsInt(int paramInt, String paramString)
  {
    Crashlytics.setInt(paramString, paramInt);
  }
  
  public static void setCrashlyticsString(String paramString1, String paramString2)
  {
    Crashlytics.setString(paramString2, paramString1);
  }
  
  public static void showMessage(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, int paramInt1, int paramInt2, int paramInt3)
  {
    if (MainActivity.instance != null) {
      MainActivity.instance.showMessage(paramString1, paramString2, paramString3, paramString4, paramString5, paramInt1, paramInt2, paramInt3);
    }
  }
  
  public static int testFunc(int paramInt)
  {
    return paramInt + 1;
  }
  
  public static void toggleIdleMode(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      AppProxy.GetActivity().runOnUiThread(new Runnable()
      {
        public void run()
        {
          MainActivity.instance.getWindow().clearFlags(128);
          Log.i("RealRacing3", "IDLE MODE: ALLOW");
        }
      });
      return;
    }
    AppProxy.GetActivity().runOnUiThread(new Runnable()
    {
      public void run()
      {
        MainActivity.instance.getWindow().addFlags(128);
        Log.i("RealRacing3", "IDLE MODE: BLOCK");
      }
    });
  }
  
  private static void updateResources(ZipFile paramZipFile)
  {
    extractResZipFile(paramZipFile, getExternalStorageDir() + "/.depot", true, "res/", "updateResources");
  }
  
  static enum NetworkType
  {
    int value;
    
    static
    {
      Ethernet = new NetworkType("Ethernet", 2, 4);
      Unmetered = new NetworkType("Unmetered", 3, 8);
      Any = new NetworkType("Any", 4, -1);
      NetworkType[] arrayOfNetworkType = new NetworkType[5];
      arrayOfNetworkType[0] = Carrier;
      arrayOfNetworkType[1] = Wifi;
      arrayOfNetworkType[2] = Ethernet;
      arrayOfNetworkType[3] = Unmetered;
      arrayOfNetworkType[4] = Any;
      $VALUES = arrayOfNetworkType;
    }
    
    private NetworkType(int paramInt)
    {
      this.value = paramInt;
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.firemint.realracing.Platform
 * JD-Core Version:    0.7.0.1
 */