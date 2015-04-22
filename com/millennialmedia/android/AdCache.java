package com.millennialmedia.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Environment;
import android.text.TextUtils;
import java.io.File;
import java.io.FileFilter;
import java.io.ObjectInputStream;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

final class AdCache
{
  private static final String CACHED_AD_FILE = "ad.dat";
  private static final String CACHE_INCOMPLETE_PREFIX = "incompleteDownload_";
  private static final String CACHE_PREFIX = "nextCachedAd_";
  private static final String CACHE_PREFIX_APID = "nextCachedAd_apids";
  private static final String LOCK_FILE = "ad.lock";
  static final int PRIORITY_FETCH = 3;
  static final int PRIORITY_PRECACHE = 1;
  static final int PRIORITY_REFRESH = 2;
  private static final String PRIVATE_CACHE_DIR = ".mmsyscache";
  private static final String TAG = "AdCache";
  private static Set<String> apidListSet;
  private static String cachedVideoList;
  private static boolean cachedVideoListLoaded;
  private static Set<String> cachedVideoSet;
  private static Map<String, String> incompleteDownloadHashMap;
  private static boolean incompleteDownloadHashMapLoaded;
  static boolean isExternalEnabled = true;
  private static Map<String, String> nextCachedAdHashMap;
  private static boolean nextCachedAdHashMapLoaded;
  
  static void cachedVideoWasAdded(Context paramContext, String paramString)
  {
    if (paramString != null) {}
    try
    {
      if (!cachedVideoListLoaded) {
        getCachedVideoList(paramContext);
      }
      if (cachedVideoSet == null) {
        cachedVideoSet = new HashSet();
      }
      cachedVideoSet.add(paramString);
      cachedVideoList = null;
      return;
    }
    finally {}
  }
  
  static void cachedVideoWasRemoved(Context paramContext, String paramString)
  {
    if (paramString != null) {}
    try
    {
      if (!cachedVideoListLoaded) {
        getCachedVideoList(paramContext);
      }
      if (cachedVideoSet != null)
      {
        cachedVideoSet.remove(paramString);
        cachedVideoList = null;
      }
      return;
    }
    finally {}
  }
  
  static void cleanCache(Context paramContext)
  {
    Utils.ThreadUtils.execute(new Runnable()
    {
      public void run()
      {
        MMLog.d("AdCache", "AdCache");
        AdCache.cleanUpExpiredAds(this.val$context);
        AdCache.cleanupCache(this.val$context);
      }
    });
  }
  
  static void cleanUpExpiredAds(Context paramContext)
  {
    iterateCachedAds(paramContext, 1, new Iterator()
    {
      boolean callback(String paramAnonymousString1, int paramAnonymousInt, Date paramAnonymousDate, String paramAnonymousString2, long paramAnonymousLong, ObjectInputStream paramAnonymousObjectInputStream)
      {
        if ((paramAnonymousDate != null) && (paramAnonymousDate.getTime() <= System.currentTimeMillis())) {}
        try
        {
          CachedAd localCachedAd = (CachedAd)paramAnonymousObjectInputStream.readObject();
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = localCachedAd.getId();
          MMLog.e("AdCache", String.format("Deleting expired ad %s.", arrayOfObject));
          return true;
        }
        catch (Exception localException)
        {
          for (;;)
          {
            MMLog.e("AdCache", String.format("There was a problem reading the cached ad %s.", new Object[] { paramAnonymousString1 }), localException);
          }
        }
      }
    });
  }
  
  static void cleanupCache(Context paramContext)
  {
    cleanupInternalCache(paramContext);
    if (isExternalStorageAvailable(paramContext)) {
      cleanupExternalCache(paramContext);
    }
  }
  
  static void cleanupDirectory(File paramFile, long paramLong)
  {
    File[] arrayOfFile = paramFile.listFiles();
    int i = arrayOfFile.length;
    int j = 0;
    if (j < i)
    {
      File localFile = arrayOfFile[j];
      if (localFile.isFile()) {
        if (System.currentTimeMillis() - localFile.lastModified() > paramLong) {
          localFile.delete();
        }
      }
      for (;;)
      {
        j++;
        break;
        if (localFile.isDirectory()) {
          try
          {
            cleanupDirectory(localFile, paramLong);
            if ((localFile.list() != null) && (localFile.list().length == 0)) {
              localFile.delete();
            }
          }
          catch (SecurityException localSecurityException)
          {
            MMLog.e("AdCache", "Security Exception cleaning up directory", localSecurityException);
          }
        }
      }
    }
  }
  
  private static void cleanupExternalCache(Context paramContext)
  {
    File localFile = getExternalCacheDirectory(paramContext);
    if (localFile == null) {}
    while ((!localFile.exists()) || (!localFile.isDirectory())) {
      return;
    }
    cleanupDirectory(localFile, HandShake.sharedHandShake(paramContext).creativeCacheTimeout);
  }
  
  private static void cleanupInternalCache(Context paramContext)
  {
    File localFile = getInternalCacheDirectory(paramContext);
    if (localFile == null) {}
    while ((!localFile.exists()) || (!localFile.isDirectory())) {
      return;
    }
    cleanupDirectory(localFile, HandShake.sharedHandShake(paramContext).creativeCacheTimeout);
  }
  
  static boolean deleteFile(Context paramContext, String paramString)
  {
    File localFile = getCachedAdFile(paramContext, paramString);
    if (localFile != null) {
      return localFile.delete();
    }
    return false;
  }
  
  /* Error */
  private static boolean downloadComponent(String paramString1, String paramString2, File paramFile, Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 191	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   4: ifeq +16 -> 20
    //   7: ldc 33
    //   9: ldc 193
    //   11: invokestatic 197	com/millennialmedia/android/MMLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   14: iconst_0
    //   15: istore 20
    //   17: iload 20
    //   19: ireturn
    //   20: new 116	java/io/File
    //   23: dup
    //   24: aload_2
    //   25: aload_1
    //   26: invokespecial 200	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   29: astore 4
    //   31: ldc 33
    //   33: ldc 202
    //   35: iconst_2
    //   36: anewarray 4	java/lang/Object
    //   39: dup
    //   40: iconst_0
    //   41: aload_1
    //   42: aastore
    //   43: dup
    //   44: iconst_1
    //   45: aload_0
    //   46: aastore
    //   47: invokestatic 208	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   50: invokestatic 211	com/millennialmedia/android/MMLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   53: aload 4
    //   55: invokevirtual 215	java/io/File:getParentFile	()Ljava/io/File;
    //   58: astore 5
    //   60: aload 5
    //   62: invokevirtual 160	java/io/File:exists	()Z
    //   65: ifne +38 -> 103
    //   68: aload 5
    //   70: invokevirtual 218	java/io/File:mkdirs	()Z
    //   73: ifne +30 -> 103
    //   76: ldc 33
    //   78: new 220	java/lang/StringBuilder
    //   81: dup
    //   82: invokespecial 221	java/lang/StringBuilder:<init>	()V
    //   85: aload 5
    //   87: invokevirtual 225	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   90: ldc 227
    //   92: invokevirtual 230	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   95: invokevirtual 234	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   98: invokestatic 211	com/millennialmedia/android/MMLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   101: iconst_0
    //   102: ireturn
    //   103: aload 4
    //   105: invokevirtual 160	java/io/File:exists	()Z
    //   108: ifeq +39 -> 147
    //   111: aload 4
    //   113: invokevirtual 237	java/io/File:length	()J
    //   116: lconst_0
    //   117: lcmp
    //   118: ifle +29 -> 147
    //   121: ldc 33
    //   123: new 220	java/lang/StringBuilder
    //   126: dup
    //   127: invokespecial 221	java/lang/StringBuilder:<init>	()V
    //   130: aload_1
    //   131: invokevirtual 230	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   134: ldc 239
    //   136: invokevirtual 230	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   139: invokevirtual 234	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   142: invokestatic 211	com/millennialmedia/android/MMLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   145: iconst_1
    //   146: ireturn
    //   147: aconst_null
    //   148: astore 6
    //   150: aconst_null
    //   151: astore 7
    //   153: ldc2_w 240
    //   156: lstore 8
    //   158: new 243	java/net/URL
    //   161: dup
    //   162: aload_0
    //   163: invokespecial 246	java/net/URL:<init>	(Ljava/lang/String;)V
    //   166: astore 10
    //   168: iconst_1
    //   169: invokestatic 252	java/net/HttpURLConnection:setFollowRedirects	(Z)V
    //   172: aload 10
    //   174: invokevirtual 256	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   177: checkcast 248	java/net/HttpURLConnection
    //   180: astore 18
    //   182: aload 18
    //   184: sipush 30000
    //   187: invokevirtual 260	java/net/HttpURLConnection:setConnectTimeout	(I)V
    //   190: aload 18
    //   192: ldc_w 262
    //   195: invokevirtual 265	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
    //   198: aload 18
    //   200: invokevirtual 268	java/net/HttpURLConnection:connect	()V
    //   203: aload 18
    //   205: invokevirtual 272	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   208: astore 6
    //   210: aload 18
    //   212: ldc_w 274
    //   215: invokevirtual 278	java/net/HttpURLConnection:getHeaderField	(Ljava/lang/String;)Ljava/lang/String;
    //   218: astore 19
    //   220: aconst_null
    //   221: astore 7
    //   223: aload 19
    //   225: ifnull +10 -> 235
    //   228: aload 19
    //   230: invokestatic 284	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   233: lstore 8
    //   235: aload 6
    //   237: ifnonnull +70 -> 307
    //   240: ldc 33
    //   242: ldc_w 286
    //   245: iconst_1
    //   246: anewarray 4	java/lang/Object
    //   249: dup
    //   250: iconst_0
    //   251: aload_1
    //   252: aastore
    //   253: invokestatic 208	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   256: invokestatic 288	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   259: aload 6
    //   261: ifnull +8 -> 269
    //   264: aload 6
    //   266: invokevirtual 293	java/io/InputStream:close	()V
    //   269: iconst_0
    //   270: istore 20
    //   272: iconst_0
    //   273: ifeq -256 -> 17
    //   276: aconst_null
    //   277: invokevirtual 296	java/io/FileOutputStream:close	()V
    //   280: iconst_0
    //   281: ireturn
    //   282: astore 21
    //   284: ldc 33
    //   286: ldc_w 298
    //   289: aload 21
    //   291: invokestatic 153	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   294: aload 4
    //   296: ifnull +9 -> 305
    //   299: aload 4
    //   301: invokevirtual 136	java/io/File:delete	()Z
    //   304: pop
    //   305: iconst_0
    //   306: ireturn
    //   307: new 295	java/io/FileOutputStream
    //   310: dup
    //   311: aload 4
    //   313: invokespecial 301	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   316: astore 23
    //   318: sipush 4096
    //   321: newarray byte
    //   323: astore 24
    //   325: aload 6
    //   327: aload 24
    //   329: invokevirtual 305	java/io/InputStream:read	([B)I
    //   332: istore 25
    //   334: iload 25
    //   336: ifle +79 -> 415
    //   339: aload 23
    //   341: aload 24
    //   343: iconst_0
    //   344: iload 25
    //   346: invokevirtual 309	java/io/FileOutputStream:write	([BII)V
    //   349: goto -24 -> 325
    //   352: astore 11
    //   354: aload 23
    //   356: astore 7
    //   358: ldc 33
    //   360: ldc_w 311
    //   363: iconst_2
    //   364: anewarray 4	java/lang/Object
    //   367: dup
    //   368: iconst_0
    //   369: aload_1
    //   370: aastore
    //   371: dup
    //   372: iconst_1
    //   373: aload 11
    //   375: aastore
    //   376: invokestatic 208	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   379: invokestatic 288	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   382: aload 6
    //   384: ifnull +8 -> 392
    //   387: aload 6
    //   389: invokevirtual 293	java/io/InputStream:close	()V
    //   392: aload 7
    //   394: ifnull +8 -> 402
    //   397: aload 7
    //   399: invokevirtual 296	java/io/FileOutputStream:close	()V
    //   402: aload 4
    //   404: ifnull +9 -> 413
    //   407: aload 4
    //   409: invokevirtual 136	java/io/File:delete	()Z
    //   412: pop
    //   413: iconst_0
    //   414: ireturn
    //   415: aload 4
    //   417: ifnull +86 -> 503
    //   420: aload 4
    //   422: invokevirtual 237	java/io/File:length	()J
    //   425: lstore 29
    //   427: lload 29
    //   429: lload 8
    //   431: lcmp
    //   432: ifeq +12 -> 444
    //   435: lload 8
    //   437: ldc2_w 240
    //   440: lcmp
    //   441: ifne +54 -> 495
    //   444: iconst_1
    //   445: istore 20
    //   447: aload 6
    //   449: ifnull +8 -> 457
    //   452: aload 6
    //   454: invokevirtual 293	java/io/InputStream:close	()V
    //   457: aload 23
    //   459: ifnull -442 -> 17
    //   462: aload 23
    //   464: invokevirtual 296	java/io/FileOutputStream:close	()V
    //   467: iload 20
    //   469: ireturn
    //   470: astore 31
    //   472: ldc 33
    //   474: ldc_w 298
    //   477: aload 31
    //   479: invokestatic 153	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   482: aload 4
    //   484: ifnull +9 -> 493
    //   487: aload 4
    //   489: invokevirtual 136	java/io/File:delete	()Z
    //   492: pop
    //   493: iconst_0
    //   494: ireturn
    //   495: ldc 33
    //   497: ldc_w 313
    //   500: invokestatic 288	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   503: aload 6
    //   505: ifnull +8 -> 513
    //   508: aload 6
    //   510: invokevirtual 293	java/io/InputStream:close	()V
    //   513: aload 23
    //   515: ifnull +8 -> 523
    //   518: aload 23
    //   520: invokevirtual 296	java/io/FileOutputStream:close	()V
    //   523: goto -121 -> 402
    //   526: astore 28
    //   528: ldc 33
    //   530: ldc_w 315
    //   533: iconst_1
    //   534: anewarray 4	java/lang/Object
    //   537: dup
    //   538: iconst_0
    //   539: aload_1
    //   540: aastore
    //   541: invokestatic 208	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   544: aload 28
    //   546: invokestatic 153	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   549: goto -46 -> 503
    //   552: astore 12
    //   554: aload 23
    //   556: astore 7
    //   558: aload 6
    //   560: ifnull +8 -> 568
    //   563: aload 6
    //   565: invokevirtual 293	java/io/InputStream:close	()V
    //   568: aload 7
    //   570: ifnull +8 -> 578
    //   573: aload 7
    //   575: invokevirtual 296	java/io/FileOutputStream:close	()V
    //   578: aload 12
    //   580: athrow
    //   581: astore 26
    //   583: ldc 33
    //   585: ldc_w 298
    //   588: aload 26
    //   590: invokestatic 153	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   593: aload 4
    //   595: ifnull +9 -> 604
    //   598: aload 4
    //   600: invokevirtual 136	java/io/File:delete	()Z
    //   603: pop
    //   604: iconst_0
    //   605: ireturn
    //   606: astore 15
    //   608: ldc 33
    //   610: ldc_w 298
    //   613: aload 15
    //   615: invokestatic 153	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   618: aload 4
    //   620: ifnull +9 -> 629
    //   623: aload 4
    //   625: invokevirtual 136	java/io/File:delete	()Z
    //   628: pop
    //   629: iconst_0
    //   630: ireturn
    //   631: astore 13
    //   633: ldc 33
    //   635: ldc_w 298
    //   638: aload 13
    //   640: invokestatic 153	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   643: aload 4
    //   645: ifnull +9 -> 654
    //   648: aload 4
    //   650: invokevirtual 136	java/io/File:delete	()Z
    //   653: pop
    //   654: iconst_0
    //   655: ireturn
    //   656: astore 12
    //   658: goto -100 -> 558
    //   661: astore 11
    //   663: aconst_null
    //   664: astore 7
    //   666: goto -308 -> 358
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	669	0	paramString1	String
    //   0	669	1	paramString2	String
    //   0	669	2	paramFile	File
    //   0	669	3	paramContext	Context
    //   29	620	4	localFile1	File
    //   58	28	5	localFile2	File
    //   148	416	6	localInputStream	java.io.InputStream
    //   151	514	7	localObject1	Object
    //   156	280	8	l1	long
    //   166	7	10	localURL	java.net.URL
    //   352	22	11	localException1	Exception
    //   661	1	11	localException2	Exception
    //   552	27	12	localObject2	Object
    //   656	1	12	localObject3	Object
    //   631	8	13	localIOException1	java.io.IOException
    //   606	8	15	localIOException2	java.io.IOException
    //   180	31	18	localHttpURLConnection	java.net.HttpURLConnection
    //   218	11	19	str	String
    //   15	453	20	bool	boolean
    //   282	8	21	localIOException3	java.io.IOException
    //   316	239	23	localFileOutputStream	java.io.FileOutputStream
    //   323	19	24	arrayOfByte	byte[]
    //   332	13	25	i	int
    //   581	8	26	localIOException4	java.io.IOException
    //   526	19	28	localSecurityException	SecurityException
    //   425	3	29	l2	long
    //   470	8	31	localIOException5	java.io.IOException
    // Exception table:
    //   from	to	target	type
    //   264	269	282	java/io/IOException
    //   276	280	282	java/io/IOException
    //   318	325	352	java/lang/Exception
    //   325	334	352	java/lang/Exception
    //   339	349	352	java/lang/Exception
    //   420	427	352	java/lang/Exception
    //   495	503	352	java/lang/Exception
    //   528	549	352	java/lang/Exception
    //   452	457	470	java/io/IOException
    //   462	467	470	java/io/IOException
    //   420	427	526	java/lang/SecurityException
    //   495	503	526	java/lang/SecurityException
    //   318	325	552	finally
    //   325	334	552	finally
    //   339	349	552	finally
    //   420	427	552	finally
    //   495	503	552	finally
    //   528	549	552	finally
    //   508	513	581	java/io/IOException
    //   518	523	581	java/io/IOException
    //   387	392	606	java/io/IOException
    //   397	402	606	java/io/IOException
    //   563	568	631	java/io/IOException
    //   573	578	631	java/io/IOException
    //   158	220	656	finally
    //   228	235	656	finally
    //   240	259	656	finally
    //   307	318	656	finally
    //   358	382	656	finally
    //   158	220	661	java/lang/Exception
    //   228	235	661	java/lang/Exception
    //   240	259	661	java/lang/Exception
    //   307	318	661	java/lang/Exception
  }
  
  static boolean downloadComponentExternalStorage(String paramString1, String paramString2, Context paramContext)
  {
    File localFile = getExternalCacheDirectory(paramContext);
    if ((localFile == null) || (!localFile.isDirectory())) {
      return false;
    }
    return downloadComponent(paramString1, paramString2, localFile, paramContext);
  }
  
  static boolean downloadComponentExternalStorage(String paramString1, String paramString2, String paramString3, Context paramContext)
  {
    File localFile = getExternalCacheDirectory(paramContext);
    if ((localFile == null) || (!localFile.isDirectory())) {
      return false;
    }
    return downloadComponent(paramString1, paramString3, new File(localFile, paramString2), paramContext);
  }
  
  static boolean downloadComponentInternalCache(String paramString1, String paramString2, Context paramContext)
  {
    File localFile = getInternalCacheDirectory(paramContext);
    if ((localFile == null) || (!localFile.isDirectory())) {
      return false;
    }
    return downloadComponent(paramString1, paramString2, localFile, paramContext);
  }
  
  static File getCacheDirectory(Context paramContext)
  {
    if (isExternalStorageAvailable(paramContext)) {
      return getExternalCacheDirectory(paramContext);
    }
    return getInternalCacheDirectory(paramContext);
  }
  
  private static File getCachedAdFile(Context paramContext, String paramString)
  {
    String str = paramString + "ad.dat";
    boolean bool1 = isExternalStorageAvailable(paramContext);
    File localFile1 = getInternalCacheDirectory(paramContext);
    File localFile2 = null;
    if (localFile1 != null) {}
    try
    {
      boolean bool3 = localFile1.isDirectory();
      localFile2 = null;
      if (bool3) {
        localFile2 = new File(localFile1, str);
      }
      if (((localFile2 == null) || (!localFile2.exists())) && (!bool1))
      {
        File localFile3 = paramContext.getFilesDir();
        if (localFile3 != null)
        {
          File localFile4 = new File(localFile3, ".mmsyscache" + File.separator + str);
          if (localFile4.exists())
          {
            boolean bool2 = localFile4.isFile();
            if (bool2) {
              return localFile4;
            }
          }
        }
      }
    }
    catch (Exception localException)
    {
      MMLog.e("AdCache", "getCachedAdFile: ", localException);
      return null;
    }
    return localFile2;
  }
  
  static String getCachedVideoList(Context paramContext)
  {
    StringBuilder localStringBuilder;
    for (;;)
    {
      try
      {
        if (cachedVideoList != null) {
          break label172;
        }
        if (!cachedVideoListLoaded)
        {
          final HashSet localHashSet = new HashSet();
          iterateCachedAds(paramContext, 2, new Iterator()
          {
            boolean callback(CachedAd paramAnonymousCachedAd)
            {
              if ((paramAnonymousCachedAd.acid != null) && (paramAnonymousCachedAd.getType() == 1) && (paramAnonymousCachedAd.isOnDisk(this.val$context))) {
                localHashSet.add(paramAnonymousCachedAd.acid);
              }
              return true;
            }
          });
          cachedVideoSet = localHashSet;
          cachedVideoListLoaded = true;
        }
        if ((cachedVideoSet == null) || (cachedVideoSet.size() <= 0)) {
          break label172;
        }
        localStringBuilder = new StringBuilder();
        Iterator localIterator = cachedVideoSet.iterator();
        if (!localIterator.hasNext()) {
          break;
        }
        String str2 = (String)localIterator.next();
        if (localStringBuilder.length() > 0) {
          localStringBuilder.append("," + (String)str2);
        } else {
          localStringBuilder.append((String)str2);
        }
      }
      finally {}
    }
    cachedVideoList = localStringBuilder.toString();
    label172:
    String str1 = cachedVideoList;
    return str1;
  }
  
  static File getDownloadFile(Context paramContext, String paramString)
  {
    File localFile1 = getExternalCacheDirectory(paramContext);
    File localFile2 = null;
    if (localFile1 != null) {
      localFile2 = new File(localFile1, paramString);
    }
    return localFile2;
  }
  
  static File getDownloadFile(Context paramContext, String paramString1, String paramString2)
  {
    File localFile1 = getExternalCacheDirectory(paramContext);
    File localFile2 = null;
    if (localFile1 != null) {
      localFile2 = new File(localFile1, paramString1 + File.separator + paramString2);
    }
    return localFile2;
  }
  
  static File getExternalCacheDirectory(Context paramContext)
  {
    File localFile1 = Environment.getExternalStorageDirectory();
    File localFile2 = null;
    if (localFile1 != null)
    {
      localFile2 = new File(localFile1, ".mmsyscache");
      if ((!localFile2.exists()) && (!localFile2.mkdirs())) {
        localFile2 = null;
      }
    }
    return localFile2;
  }
  
  /* Error */
  static String getIncompleteDownload(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 369	com/millennialmedia/android/AdCache:incompleteDownloadHashMapLoaded	Z
    //   6: ifne +7 -> 13
    //   9: aload_0
    //   10: invokestatic 372	com/millennialmedia/android/AdCache:loadIncompleteDownloadHashMap	(Landroid/content/Context;)V
    //   13: aload_1
    //   14: ifnonnull +10 -> 24
    //   17: aconst_null
    //   18: astore_3
    //   19: ldc 2
    //   21: monitorexit
    //   22: aload_3
    //   23: areturn
    //   24: getstatic 374	com/millennialmedia/android/AdCache:incompleteDownloadHashMap	Ljava/util/Map;
    //   27: aload_1
    //   28: invokeinterface 380 2 0
    //   33: checkcast 204	java/lang/String
    //   36: astore_3
    //   37: goto -18 -> 19
    //   40: astore_2
    //   41: ldc 2
    //   43: monitorexit
    //   44: aload_2
    //   45: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	46	0	paramContext	Context
    //   0	46	1	paramString	String
    //   40	5	2	localObject	Object
    //   18	19	3	str	String
    // Exception table:
    //   from	to	target	type
    //   3	13	40	finally
    //   24	37	40	finally
  }
  
  static File getInternalCacheDirectory(Context paramContext)
  {
    File localFile;
    if (paramContext == null) {
      localFile = null;
    }
    do
    {
      return localFile;
      localFile = new File(paramContext.getFilesDir(), ".mmsyscache");
    } while ((localFile == null) || (localFile.exists()) || (localFile.mkdirs()));
    return null;
  }
  
  /* Error */
  static String getNextCachedAd(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 383	com/millennialmedia/android/AdCache:nextCachedAdHashMapLoaded	Z
    //   6: ifne +7 -> 13
    //   9: aload_0
    //   10: invokestatic 386	com/millennialmedia/android/AdCache:loadNextCachedAdHashMap	(Landroid/content/Context;)V
    //   13: aload_1
    //   14: ifnonnull +10 -> 24
    //   17: aconst_null
    //   18: astore_3
    //   19: ldc 2
    //   21: monitorexit
    //   22: aload_3
    //   23: areturn
    //   24: getstatic 388	com/millennialmedia/android/AdCache:nextCachedAdHashMap	Ljava/util/Map;
    //   27: aload_1
    //   28: invokeinterface 380 2 0
    //   33: checkcast 204	java/lang/String
    //   36: astore_3
    //   37: goto -18 -> 19
    //   40: astore_2
    //   41: ldc 2
    //   43: monitorexit
    //   44: aload_2
    //   45: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	46	0	paramContext	Context
    //   0	46	1	paramString	String
    //   40	5	2	localObject	Object
    //   18	19	3	str	String
    // Exception table:
    //   from	to	target	type
    //   3	13	40	finally
    //   24	37	40	finally
  }
  
  static boolean hasDownloadFile(Context paramContext, String paramString)
  {
    File localFile = getDownloadFile(paramContext, paramString);
    return (localFile != null) && (localFile.exists());
  }
  
  static boolean isExternalMounted()
  {
    return Environment.getExternalStorageState().equals("mounted");
  }
  
  static boolean isExternalStorageAvailable(Context paramContext)
  {
    if (paramContext == null) {}
    String str;
    do
    {
      return false;
      str = Environment.getExternalStorageState();
    } while ((paramContext.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") != 0) || (TextUtils.isEmpty(str)) || (!str.equals("mounted")) || (!isExternalEnabled));
    return true;
  }
  
  /* Error */
  static void iterateCachedAds(Context paramContext, int paramInt, Iterator paramIterator)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 173	com/millennialmedia/android/AdCache:getInternalCacheDirectory	(Landroid/content/Context;)Ljava/io/File;
    //   4: astore_3
    //   5: aload_3
    //   6: ifnull +135 -> 141
    //   9: aload_3
    //   10: new 408	com/millennialmedia/android/AdCache$2
    //   13: dup
    //   14: invokespecial 409	com/millennialmedia/android/AdCache$2:<init>	()V
    //   17: invokevirtual 412	java/io/File:listFiles	(Ljava/io/FileFilter;)[Ljava/io/File;
    //   20: astore 4
    //   22: aload 4
    //   24: ifnull +117 -> 141
    //   27: aload 4
    //   29: arraylength
    //   30: istore 5
    //   32: iconst_0
    //   33: istore 6
    //   35: aconst_null
    //   36: astore 7
    //   38: iload 6
    //   40: iload 5
    //   42: if_icmpge +462 -> 504
    //   45: aload 4
    //   47: iload 6
    //   49: aaload
    //   50: astore 9
    //   52: aload 9
    //   54: ifnull +15 -> 69
    //   57: aload 9
    //   59: invokevirtual 160	java/io/File:exists	()Z
    //   62: istore 17
    //   64: iload 17
    //   66: ifne +45 -> 111
    //   69: aload 7
    //   71: ifnull +426 -> 497
    //   74: aload 7
    //   76: invokevirtual 415	java/io/ObjectInputStream:close	()V
    //   79: aconst_null
    //   80: astore 10
    //   82: iinc 6 1
    //   85: aload 10
    //   87: astore 7
    //   89: goto -51 -> 38
    //   92: astore 11
    //   94: ldc 33
    //   96: ldc_w 417
    //   99: aload 11
    //   101: invokestatic 153	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   104: aload 7
    //   106: astore 10
    //   108: goto -26 -> 82
    //   111: iload_1
    //   112: ifne +87 -> 199
    //   115: aload_2
    //   116: aload 9
    //   118: invokevirtual 420	java/io/File:getName	()Ljava/lang/String;
    //   121: invokevirtual 426	com/millennialmedia/android/AdCache$Iterator:callback	(Ljava/lang/String;)Z
    //   124: istore 26
    //   126: iload 26
    //   128: ifne +36 -> 164
    //   131: aload 7
    //   133: ifnull +371 -> 504
    //   136: aload 7
    //   138: invokevirtual 415	java/io/ObjectInputStream:close	()V
    //   141: aload_2
    //   142: invokevirtual 429	com/millennialmedia/android/AdCache$Iterator:done	()V
    //   145: return
    //   146: astore 28
    //   148: ldc 33
    //   150: ldc_w 417
    //   153: aload 28
    //   155: invokestatic 153	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   158: aload 7
    //   160: pop
    //   161: goto -20 -> 141
    //   164: aload 7
    //   166: ifnull +331 -> 497
    //   169: aload 7
    //   171: invokevirtual 415	java/io/ObjectInputStream:close	()V
    //   174: aconst_null
    //   175: astore 10
    //   177: goto -95 -> 82
    //   180: astore 27
    //   182: ldc 33
    //   184: ldc_w 417
    //   187: aload 27
    //   189: invokestatic 153	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   192: aload 7
    //   194: astore 10
    //   196: goto -114 -> 82
    //   199: new 414	java/io/ObjectInputStream
    //   202: dup
    //   203: new 431	java/io/FileInputStream
    //   206: dup
    //   207: aload 9
    //   209: invokespecial 432	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   212: invokespecial 435	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   215: astore 10
    //   217: aload 10
    //   219: invokevirtual 438	java/io/ObjectInputStream:readInt	()I
    //   222: istore 18
    //   224: aload 10
    //   226: invokevirtual 441	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   229: checkcast 443	java/util/Date
    //   232: astore 19
    //   234: aload 10
    //   236: invokevirtual 441	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   239: checkcast 204	java/lang/String
    //   242: astore 20
    //   244: aload 10
    //   246: invokevirtual 446	java/io/ObjectInputStream:readLong	()J
    //   249: lstore 21
    //   251: iload_1
    //   252: iconst_1
    //   253: if_icmpne +56 -> 309
    //   256: aload_2
    //   257: aload 9
    //   259: invokevirtual 420	java/io/File:getName	()Ljava/lang/String;
    //   262: iload 18
    //   264: aload 19
    //   266: aload 20
    //   268: lload 21
    //   270: aload 10
    //   272: invokevirtual 449	com/millennialmedia/android/AdCache$Iterator:callback	(Ljava/lang/String;ILjava/util/Date;Ljava/lang/String;JLjava/io/ObjectInputStream;)Z
    //   275: ifne +80 -> 355
    //   278: aload 10
    //   280: invokevirtual 415	java/io/ObjectInputStream:close	()V
    //   283: iconst_0
    //   284: ifeq -143 -> 141
    //   287: aconst_null
    //   288: invokevirtual 415	java/io/ObjectInputStream:close	()V
    //   291: goto -150 -> 141
    //   294: astore 24
    //   296: ldc 33
    //   298: ldc_w 417
    //   301: aload 24
    //   303: invokestatic 153	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   306: goto -165 -> 141
    //   309: aload_2
    //   310: aload 10
    //   312: invokevirtual 441	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   315: checkcast 451	com/millennialmedia/android/CachedAd
    //   318: invokevirtual 454	com/millennialmedia/android/AdCache$Iterator:callback	(Lcom/millennialmedia/android/CachedAd;)Z
    //   321: ifne +34 -> 355
    //   324: aload 10
    //   326: invokevirtual 415	java/io/ObjectInputStream:close	()V
    //   329: iconst_0
    //   330: ifeq -189 -> 141
    //   333: aconst_null
    //   334: invokevirtual 415	java/io/ObjectInputStream:close	()V
    //   337: goto -196 -> 141
    //   340: astore 25
    //   342: ldc 33
    //   344: ldc_w 417
    //   347: aload 25
    //   349: invokestatic 153	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   352: goto -211 -> 141
    //   355: aload 10
    //   357: ifnull -275 -> 82
    //   360: aload 10
    //   362: invokevirtual 415	java/io/ObjectInputStream:close	()V
    //   365: aconst_null
    //   366: astore 10
    //   368: goto -286 -> 82
    //   371: astore 23
    //   373: ldc 33
    //   375: ldc_w 417
    //   378: aload 23
    //   380: invokestatic 153	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   383: goto -301 -> 82
    //   386: astore 14
    //   388: aload 7
    //   390: astore 10
    //   392: iconst_1
    //   393: anewarray 4	java/lang/Object
    //   396: astore 15
    //   398: aload 15
    //   400: iconst_0
    //   401: aload 9
    //   403: invokevirtual 420	java/io/File:getName	()Ljava/lang/String;
    //   406: aastore
    //   407: ldc 33
    //   409: ldc_w 456
    //   412: aload 15
    //   414: invokestatic 208	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   417: aload 14
    //   419: invokestatic 153	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   422: aload 10
    //   424: ifnull -342 -> 82
    //   427: aload 10
    //   429: invokevirtual 415	java/io/ObjectInputStream:close	()V
    //   432: aconst_null
    //   433: astore 10
    //   435: goto -353 -> 82
    //   438: astore 16
    //   440: ldc 33
    //   442: ldc_w 417
    //   445: aload 16
    //   447: invokestatic 153	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   450: goto -368 -> 82
    //   453: astore 12
    //   455: aload 7
    //   457: astore 10
    //   459: aload 10
    //   461: ifnull +8 -> 469
    //   464: aload 10
    //   466: invokevirtual 415	java/io/ObjectInputStream:close	()V
    //   469: aload 12
    //   471: athrow
    //   472: astore 13
    //   474: ldc 33
    //   476: ldc_w 417
    //   479: aload 13
    //   481: invokestatic 153	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   484: goto -15 -> 469
    //   487: astore 12
    //   489: goto -30 -> 459
    //   492: astore 14
    //   494: goto -102 -> 392
    //   497: aload 7
    //   499: astore 10
    //   501: goto -419 -> 82
    //   504: aload 7
    //   506: pop
    //   507: goto -366 -> 141
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	510	0	paramContext	Context
    //   0	510	1	paramInt	int
    //   0	510	2	paramIterator	Iterator
    //   4	6	3	localFile1	File
    //   20	26	4	arrayOfFile	File[]
    //   30	13	5	i	int
    //   33	50	6	j	int
    //   36	469	7	localObject1	Object
    //   50	352	9	localFile2	File
    //   80	420	10	localObject2	Object
    //   92	8	11	localIOException1	java.io.IOException
    //   453	17	12	localObject3	Object
    //   487	1	12	localObject4	Object
    //   472	8	13	localIOException2	java.io.IOException
    //   386	32	14	localException1	Exception
    //   492	1	14	localException2	Exception
    //   396	17	15	arrayOfObject	Object[]
    //   438	8	16	localIOException3	java.io.IOException
    //   62	3	17	bool1	boolean
    //   222	41	18	k	int
    //   232	33	19	localDate	Date
    //   242	25	20	str	String
    //   249	20	21	l	long
    //   371	8	23	localIOException4	java.io.IOException
    //   294	8	24	localIOException5	java.io.IOException
    //   340	8	25	localIOException6	java.io.IOException
    //   124	3	26	bool2	boolean
    //   180	8	27	localIOException7	java.io.IOException
    //   146	8	28	localIOException8	java.io.IOException
    // Exception table:
    //   from	to	target	type
    //   74	79	92	java/io/IOException
    //   136	141	146	java/io/IOException
    //   169	174	180	java/io/IOException
    //   287	291	294	java/io/IOException
    //   333	337	340	java/io/IOException
    //   360	365	371	java/io/IOException
    //   57	64	386	java/lang/Exception
    //   115	126	386	java/lang/Exception
    //   199	217	386	java/lang/Exception
    //   427	432	438	java/io/IOException
    //   57	64	453	finally
    //   115	126	453	finally
    //   199	217	453	finally
    //   464	469	472	java/io/IOException
    //   217	251	487	finally
    //   256	283	487	finally
    //   309	329	487	finally
    //   392	422	487	finally
    //   217	251	492	java/lang/Exception
    //   256	283	492	java/lang/Exception
    //   309	329	492	java/lang/Exception
  }
  
  /* Error */
  static CachedAd load(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnull +13 -> 14
    //   4: aload_1
    //   5: ldc_w 462
    //   8: invokevirtual 400	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   11: ifeq +5 -> 16
    //   14: aconst_null
    //   15: areturn
    //   16: aconst_null
    //   17: astore_2
    //   18: aload_0
    //   19: aload_1
    //   20: invokestatic 179	com/millennialmedia/android/AdCache:getCachedAdFile	(Landroid/content/Context;Ljava/lang/String;)Ljava/io/File;
    //   23: astore_3
    //   24: aload_3
    //   25: ifnonnull +5 -> 30
    //   28: aconst_null
    //   29: areturn
    //   30: new 414	java/io/ObjectInputStream
    //   33: dup
    //   34: new 431	java/io/FileInputStream
    //   37: dup
    //   38: aload_3
    //   39: invokespecial 432	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   42: invokespecial 435	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   45: astore 4
    //   47: aload 4
    //   49: invokevirtual 438	java/io/ObjectInputStream:readInt	()I
    //   52: pop
    //   53: aload 4
    //   55: invokevirtual 441	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   58: checkcast 443	java/util/Date
    //   61: pop
    //   62: aload 4
    //   64: invokevirtual 441	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   67: pop
    //   68: aload 4
    //   70: invokevirtual 446	java/io/ObjectInputStream:readLong	()J
    //   73: pop2
    //   74: aload 4
    //   76: invokevirtual 441	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   79: checkcast 451	com/millennialmedia/android/CachedAd
    //   82: astore 16
    //   84: aload 4
    //   86: ifnull +8 -> 94
    //   89: aload 4
    //   91: invokevirtual 415	java/io/ObjectInputStream:close	()V
    //   94: aload 16
    //   96: areturn
    //   97: astore 17
    //   99: ldc 33
    //   101: ldc_w 417
    //   104: aload 17
    //   106: invokestatic 153	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   109: aload 16
    //   111: areturn
    //   112: astore 5
    //   114: ldc 33
    //   116: new 220	java/lang/StringBuilder
    //   119: dup
    //   120: invokespecial 221	java/lang/StringBuilder:<init>	()V
    //   123: ldc_w 464
    //   126: invokevirtual 230	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   129: aload_1
    //   130: invokevirtual 230	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   133: ldc_w 466
    //   136: invokevirtual 230	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   139: invokevirtual 234	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   142: aload 5
    //   144: invokestatic 153	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   147: aload_2
    //   148: ifnull -134 -> 14
    //   151: aload_2
    //   152: invokevirtual 415	java/io/ObjectInputStream:close	()V
    //   155: aconst_null
    //   156: areturn
    //   157: astore 8
    //   159: ldc 33
    //   161: ldc_w 417
    //   164: aload 8
    //   166: invokestatic 153	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   169: aconst_null
    //   170: areturn
    //   171: astore 9
    //   173: ldc 33
    //   175: ldc_w 468
    //   178: iconst_1
    //   179: anewarray 4	java/lang/Object
    //   182: dup
    //   183: iconst_0
    //   184: aload_1
    //   185: aastore
    //   186: invokestatic 208	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   189: aload 9
    //   191: invokestatic 153	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   194: aload_2
    //   195: ifnull -181 -> 14
    //   198: aload_2
    //   199: invokevirtual 415	java/io/ObjectInputStream:close	()V
    //   202: aconst_null
    //   203: areturn
    //   204: astore 10
    //   206: ldc 33
    //   208: ldc_w 417
    //   211: aload 10
    //   213: invokestatic 153	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   216: aconst_null
    //   217: areturn
    //   218: astore 6
    //   220: aload_2
    //   221: ifnull +7 -> 228
    //   224: aload_2
    //   225: invokevirtual 415	java/io/ObjectInputStream:close	()V
    //   228: aload 6
    //   230: athrow
    //   231: astore 7
    //   233: ldc 33
    //   235: ldc_w 417
    //   238: aload 7
    //   240: invokestatic 153	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   243: goto -15 -> 228
    //   246: astore 6
    //   248: aload 4
    //   250: astore_2
    //   251: goto -31 -> 220
    //   254: astore 9
    //   256: aload 4
    //   258: astore_2
    //   259: goto -86 -> 173
    //   262: astore 5
    //   264: aload 4
    //   266: astore_2
    //   267: goto -153 -> 114
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	270	0	paramContext	Context
    //   0	270	1	paramString	String
    //   17	250	2	localObject1	Object
    //   23	16	3	localFile	File
    //   45	220	4	localObjectInputStream	ObjectInputStream
    //   112	31	5	localFileNotFoundException1	java.io.FileNotFoundException
    //   262	1	5	localFileNotFoundException2	java.io.FileNotFoundException
    //   218	11	6	localObject2	Object
    //   246	1	6	localObject3	Object
    //   231	8	7	localIOException1	java.io.IOException
    //   157	8	8	localIOException2	java.io.IOException
    //   171	19	9	localException1	Exception
    //   254	1	9	localException2	Exception
    //   204	8	10	localIOException3	java.io.IOException
    //   82	28	16	localCachedAd	CachedAd
    //   97	8	17	localIOException4	java.io.IOException
    // Exception table:
    //   from	to	target	type
    //   89	94	97	java/io/IOException
    //   30	47	112	java/io/FileNotFoundException
    //   151	155	157	java/io/IOException
    //   30	47	171	java/lang/Exception
    //   198	202	204	java/io/IOException
    //   30	47	218	finally
    //   114	147	218	finally
    //   173	194	218	finally
    //   224	228	231	java/io/IOException
    //   47	84	246	finally
    //   47	84	254	java/lang/Exception
    //   47	84	262	java/io/FileNotFoundException
  }
  
  private static void loadApidListSet(SharedPreferences paramSharedPreferences)
  {
    apidListSet = new HashSet();
    String str1 = paramSharedPreferences.getString("nextCachedAd_apids", null);
    if (str1 != null)
    {
      String[] arrayOfString = str1.split(MMSDK.COMMA);
      if ((arrayOfString != null) && (arrayOfString.length > 0))
      {
        int i = arrayOfString.length;
        for (int j = 0; j < i; j++)
        {
          String str2 = arrayOfString[j];
          apidListSet.add(str2);
        }
      }
    }
  }
  
  static CachedAd loadIncompleteDownload(Context paramContext, String paramString)
  {
    String str = getIncompleteDownload(paramContext, paramString);
    if (str == null) {
      return null;
    }
    return load(paramContext, str);
  }
  
  private static void loadIncompleteDownloadHashMap(Context paramContext)
  {
    SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("MillennialMediaSettings", 0);
    incompleteDownloadHashMap = new ConcurrentHashMap();
    if (apidListSet == null) {
      loadApidListSet(localSharedPreferences);
    }
    Iterator localIterator = apidListSet.iterator();
    while (localIterator.hasNext())
    {
      String str1 = (String)localIterator.next();
      for (String str2 : MMAdImpl.getAdTypes())
      {
        String str3 = localSharedPreferences.getString("incompleteDownload_" + str2 + '_' + str1, null);
        if (str3 != null) {
          incompleteDownloadHashMap.put(str2 + '_' + str1, str3);
        }
      }
    }
    incompleteDownloadHashMapLoaded = true;
  }
  
  static CachedAd loadNextCachedAd(Context paramContext, String paramString)
  {
    String str = getNextCachedAd(paramContext, paramString);
    if ((str == null) || (str.equals(""))) {
      return null;
    }
    return load(paramContext, str);
  }
  
  private static void loadNextCachedAdHashMap(Context paramContext)
  {
    SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("MillennialMediaSettings", 0);
    nextCachedAdHashMap = new ConcurrentHashMap();
    if (apidListSet == null) {
      loadApidListSet(localSharedPreferences);
    }
    Iterator localIterator = apidListSet.iterator();
    while (localIterator.hasNext())
    {
      String str1 = (String)localIterator.next();
      for (String str2 : MMAdImpl.getAdTypes())
      {
        String str3 = localSharedPreferences.getString("nextCachedAd_" + str2 + '_' + str1, null);
        if (str3 != null) {
          nextCachedAdHashMap.put(str2 + '_' + str1, str3);
        }
      }
    }
    nextCachedAdHashMapLoaded = true;
  }
  
  static void resetCache(Context paramContext)
  {
    iterateCachedAds(paramContext, 2, new Iterator()
    {
      boolean callback(CachedAd paramAnonymousCachedAd)
      {
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = paramAnonymousCachedAd.getId();
        MMLog.d("AdCache", String.format("Deleting ad %s.", arrayOfObject));
        paramAnonymousCachedAd.delete(this.val$context);
        return true;
      }
    });
    cachedVideoSet = null;
    cachedVideoList = null;
    cachedVideoListLoaded = false;
    if (nextCachedAdHashMap != null)
    {
      Iterator localIterator2 = nextCachedAdHashMap.keySet().iterator();
      while (localIterator2.hasNext()) {
        setNextCachedAd(paramContext, (String)localIterator2.next(), null);
      }
    }
    if (incompleteDownloadHashMap != null)
    {
      Iterator localIterator1 = incompleteDownloadHashMap.keySet().iterator();
      while (localIterator1.hasNext()) {
        setIncompleteDownload(paramContext, (String)localIterator1.next(), null);
      }
    }
  }
  
  /* Error */
  static boolean save(Context paramContext, CachedAd paramCachedAd)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aconst_null
    //   3: astore_3
    //   4: aload_1
    //   5: ifnonnull +5 -> 10
    //   8: iconst_0
    //   9: ireturn
    //   10: aload_0
    //   11: aload_1
    //   12: invokevirtual 538	com/millennialmedia/android/CachedAd:getId	()Ljava/lang/String;
    //   15: invokestatic 179	com/millennialmedia/android/AdCache:getCachedAdFile	(Landroid/content/Context;Ljava/lang/String;)Ljava/io/File;
    //   18: astore 4
    //   20: aload 4
    //   22: ifnull -14 -> 8
    //   25: iconst_2
    //   26: anewarray 4	java/lang/Object
    //   29: astore 5
    //   31: aload 5
    //   33: iconst_0
    //   34: aload_1
    //   35: invokevirtual 538	com/millennialmedia/android/CachedAd:getId	()Ljava/lang/String;
    //   38: aastore
    //   39: aload 5
    //   41: iconst_1
    //   42: aload 4
    //   44: aastore
    //   45: ldc 33
    //   47: ldc_w 540
    //   50: aload 5
    //   52: invokestatic 208	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   55: invokestatic 211	com/millennialmedia/android/MMLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   58: new 116	java/io/File
    //   61: dup
    //   62: aload 4
    //   64: invokevirtual 543	java/io/File:getParent	()Ljava/lang/String;
    //   67: new 220	java/lang/StringBuilder
    //   70: dup
    //   71: invokespecial 221	java/lang/StringBuilder:<init>	()V
    //   74: aload_1
    //   75: invokevirtual 538	com/millennialmedia/android/CachedAd:getId	()Ljava/lang/String;
    //   78: invokevirtual 230	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   81: ldc 20
    //   83: invokevirtual 230	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   86: invokevirtual 234	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   89: invokespecial 545	java/io/File:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   92: astore 6
    //   94: aload 6
    //   96: invokevirtual 548	java/io/File:createNewFile	()Z
    //   99: ifne +61 -> 160
    //   102: iconst_1
    //   103: anewarray 4	java/lang/Object
    //   106: astore 17
    //   108: aload 17
    //   110: iconst_0
    //   111: aload_1
    //   112: invokevirtual 538	com/millennialmedia/android/CachedAd:getId	()Ljava/lang/String;
    //   115: aastore
    //   116: ldc 33
    //   118: ldc_w 550
    //   121: aload 17
    //   123: invokestatic 208	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   126: invokestatic 211	com/millennialmedia/android/MMLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   129: aload 6
    //   131: invokevirtual 136	java/io/File:delete	()Z
    //   134: pop
    //   135: iconst_0
    //   136: ifeq +7 -> 143
    //   139: aconst_null
    //   140: invokevirtual 553	java/io/ObjectOutputStream:close	()V
    //   143: iconst_0
    //   144: ireturn
    //   145: astore 18
    //   147: ldc 33
    //   149: ldc_w 417
    //   152: aload 18
    //   154: invokestatic 153	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   157: goto -14 -> 143
    //   160: new 552	java/io/ObjectOutputStream
    //   163: dup
    //   164: new 295	java/io/FileOutputStream
    //   167: dup
    //   168: aload 4
    //   170: invokespecial 301	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   173: invokespecial 556	java/io/ObjectOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   176: astore 14
    //   178: aload 14
    //   180: aload_1
    //   181: invokevirtual 559	com/millennialmedia/android/CachedAd:getType	()I
    //   184: invokevirtual 562	java/io/ObjectOutputStream:writeInt	(I)V
    //   187: aload 14
    //   189: aload_1
    //   190: getfield 566	com/millennialmedia/android/CachedAd:expiration	Ljava/util/Date;
    //   193: invokevirtual 570	java/io/ObjectOutputStream:writeObject	(Ljava/lang/Object;)V
    //   196: aload 14
    //   198: aload_1
    //   199: getfield 573	com/millennialmedia/android/CachedAd:acid	Ljava/lang/String;
    //   202: invokevirtual 570	java/io/ObjectOutputStream:writeObject	(Ljava/lang/Object;)V
    //   205: aload 14
    //   207: aload_1
    //   208: getfield 576	com/millennialmedia/android/CachedAd:deferredViewStart	J
    //   211: invokevirtual 580	java/io/ObjectOutputStream:writeLong	(J)V
    //   214: aload 14
    //   216: aload_1
    //   217: invokevirtual 570	java/io/ObjectOutputStream:writeObject	(Ljava/lang/Object;)V
    //   220: aload 6
    //   222: invokevirtual 136	java/io/File:delete	()Z
    //   225: pop
    //   226: aload 14
    //   228: ifnull +8 -> 236
    //   231: aload 14
    //   233: invokevirtual 553	java/io/ObjectOutputStream:close	()V
    //   236: aload_1
    //   237: aload_0
    //   238: invokevirtual 583	com/millennialmedia/android/CachedAd:saveAssets	(Landroid/content/Context;)Z
    //   241: ifne +118 -> 359
    //   244: aload_1
    //   245: aload_0
    //   246: invokevirtual 585	com/millennialmedia/android/CachedAd:delete	(Landroid/content/Context;)V
    //   249: iconst_0
    //   250: ireturn
    //   251: astore 15
    //   253: ldc 33
    //   255: ldc_w 417
    //   258: aload 15
    //   260: invokestatic 153	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   263: goto -27 -> 236
    //   266: astore 7
    //   268: iconst_1
    //   269: anewarray 4	java/lang/Object
    //   272: astore 11
    //   274: aload 11
    //   276: iconst_0
    //   277: aload_1
    //   278: invokevirtual 538	com/millennialmedia/android/CachedAd:getId	()Ljava/lang/String;
    //   281: aastore
    //   282: ldc 33
    //   284: ldc_w 587
    //   287: aload 11
    //   289: invokestatic 208	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   292: aload 7
    //   294: invokestatic 153	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   297: aload_2
    //   298: invokevirtual 136	java/io/File:delete	()Z
    //   301: pop
    //   302: aload_3
    //   303: ifnull -295 -> 8
    //   306: aload_3
    //   307: invokevirtual 553	java/io/ObjectOutputStream:close	()V
    //   310: iconst_0
    //   311: ireturn
    //   312: astore 12
    //   314: ldc 33
    //   316: ldc_w 417
    //   319: aload 12
    //   321: invokestatic 153	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   324: iconst_0
    //   325: ireturn
    //   326: astore 8
    //   328: aload_2
    //   329: invokevirtual 136	java/io/File:delete	()Z
    //   332: pop
    //   333: aload_3
    //   334: ifnull +7 -> 341
    //   337: aload_3
    //   338: invokevirtual 553	java/io/ObjectOutputStream:close	()V
    //   341: aload 8
    //   343: athrow
    //   344: astore 9
    //   346: ldc 33
    //   348: ldc_w 417
    //   351: aload 9
    //   353: invokestatic 153	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   356: goto -15 -> 341
    //   359: iconst_1
    //   360: ireturn
    //   361: astore 8
    //   363: aload 6
    //   365: astore_2
    //   366: aconst_null
    //   367: astore_3
    //   368: goto -40 -> 328
    //   371: astore 8
    //   373: aload 14
    //   375: astore_3
    //   376: aload 6
    //   378: astore_2
    //   379: goto -51 -> 328
    //   382: astore 7
    //   384: aload 6
    //   386: astore_2
    //   387: aconst_null
    //   388: astore_3
    //   389: goto -121 -> 268
    //   392: astore 7
    //   394: aload 14
    //   396: astore_3
    //   397: aload 6
    //   399: astore_2
    //   400: goto -132 -> 268
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	403	0	paramContext	Context
    //   0	403	1	paramCachedAd	CachedAd
    //   1	399	2	localObject1	Object
    //   3	394	3	localObject2	Object
    //   18	151	4	localFile1	File
    //   29	22	5	arrayOfObject1	Object[]
    //   92	306	6	localFile2	File
    //   266	27	7	localException1	Exception
    //   382	1	7	localException2	Exception
    //   392	1	7	localException3	Exception
    //   326	16	8	localObject3	Object
    //   361	1	8	localObject4	Object
    //   371	1	8	localObject5	Object
    //   344	8	9	localIOException1	java.io.IOException
    //   272	16	11	arrayOfObject2	Object[]
    //   312	8	12	localIOException2	java.io.IOException
    //   176	219	14	localObjectOutputStream	java.io.ObjectOutputStream
    //   251	8	15	localIOException3	java.io.IOException
    //   106	16	17	arrayOfObject3	Object[]
    //   145	8	18	localIOException4	java.io.IOException
    // Exception table:
    //   from	to	target	type
    //   129	135	145	java/io/IOException
    //   139	143	145	java/io/IOException
    //   220	226	251	java/io/IOException
    //   231	236	251	java/io/IOException
    //   58	94	266	java/lang/Exception
    //   297	302	312	java/io/IOException
    //   306	310	312	java/io/IOException
    //   58	94	326	finally
    //   268	297	326	finally
    //   328	333	344	java/io/IOException
    //   337	341	344	java/io/IOException
    //   94	129	361	finally
    //   160	178	361	finally
    //   178	220	371	finally
    //   94	129	382	java/lang/Exception
    //   160	178	382	java/lang/Exception
    //   178	220	392	java/lang/Exception
  }
  
  private static void saveApidListSet(SharedPreferences.Editor paramEditor, String paramString)
  {
    int i = paramString.indexOf('_');
    String str1;
    StringBuilder localStringBuilder1;
    StringBuilder localStringBuilder2;
    if ((i >= 0) && (i < paramString.length()))
    {
      str1 = paramString.substring(i + 1);
      if ((str1 != null) && (!apidListSet.contains(str1)))
      {
        boolean bool = apidListSet.isEmpty();
        localStringBuilder1 = null;
        if (!bool)
        {
          Iterator localIterator = apidListSet.iterator();
          localStringBuilder1 = new StringBuilder();
          while (localIterator.hasNext()) {
            localStringBuilder1.append((String)localIterator.next() + MMSDK.COMMA);
          }
        }
        localStringBuilder2 = new StringBuilder();
        if (localStringBuilder1 != null) {
          break label181;
        }
      }
    }
    label181:
    for (String str2 = "";; str2 = localStringBuilder1.toString())
    {
      paramEditor.putString("nextCachedAd_apids", str2 + str1);
      apidListSet.add(str1);
      return;
    }
  }
  
  private static void saveIncompleteDownloadHashMap(Context paramContext, String paramString)
  {
    if (paramString != null)
    {
      SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("MillennialMediaSettings", 0).edit();
      saveApidListSet(localEditor, paramString);
      localEditor.putString("incompleteDownload_" + paramString, (String)incompleteDownloadHashMap.get(paramString));
      localEditor.commit();
    }
  }
  
  private static void saveNextCachedAdHashMapValue(Context paramContext, String paramString)
  {
    if (paramString != null)
    {
      SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("MillennialMediaSettings", 0).edit();
      saveApidListSet(localEditor, paramString);
      localEditor.putString("nextCachedAd_" + paramString, (String)nextCachedAdHashMap.get(paramString));
      localEditor.commit();
    }
  }
  
  static void setEnableExternalStorage(boolean paramBoolean)
  {
    isExternalEnabled = paramBoolean;
  }
  
  static void setIncompleteDownload(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      if (!incompleteDownloadHashMapLoaded) {
        loadIncompleteDownloadHashMap(paramContext);
      }
      if (paramString1 != null)
      {
        Map localMap = incompleteDownloadHashMap;
        if (paramString2 == null) {
          paramString2 = "";
        }
        localMap.put(paramString1, paramString2);
        saveIncompleteDownloadHashMap(paramContext, paramString1);
      }
      return;
    }
    finally {}
  }
  
  static void setNextCachedAd(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      if (!nextCachedAdHashMapLoaded) {
        loadNextCachedAdHashMap(paramContext);
      }
      if (paramString1 != null)
      {
        Map localMap = nextCachedAdHashMap;
        if (paramString2 == null) {
          paramString2 = "";
        }
        localMap.put(paramString1, paramString2);
        saveNextCachedAdHashMapValue(paramContext, paramString1);
      }
      return;
    }
    finally {}
  }
  
  static boolean startDownloadTask(Context paramContext, String paramString, CachedAd paramCachedAd, AdCacheTaskListener paramAdCacheTaskListener)
  {
    return AdCacheThreadPool.sharedThreadPool().startDownloadTask(paramContext, paramString, paramCachedAd, paramAdCacheTaskListener);
  }
  
  static abstract interface AdCacheTaskListener
  {
    public abstract void downloadCompleted(CachedAd paramCachedAd, boolean paramBoolean);
    
    public abstract void downloadStart(CachedAd paramCachedAd);
  }
  
  static class Iterator
  {
    static final int ITERATE_ID = 0;
    static final int ITERATE_INFO = 1;
    static final int ITERATE_OBJECT = 2;
    
    boolean callback(CachedAd paramCachedAd)
    {
      return false;
    }
    
    boolean callback(String paramString)
    {
      return false;
    }
    
    boolean callback(String paramString1, int paramInt, Date paramDate, String paramString2, long paramLong, ObjectInputStream paramObjectInputStream)
    {
      return false;
    }
    
    void done() {}
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.AdCache
 * JD-Core Version:    0.7.0.1
 */