package com.facebook.internal;

import android.content.Context;
import com.facebook.LoggingBehavior;
import com.facebook.Settings;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidParameterException;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public final class FileLruCache
{
  private static final String HEADER_CACHEKEY_KEY = "key";
  private static final String HEADER_CACHE_CONTENT_TAG_KEY = "tag";
  static final String TAG = FileLruCache.class.getSimpleName();
  private static final AtomicLong bufferIndex = new AtomicLong();
  private final File directory;
  private boolean isTrimInProgress;
  private boolean isTrimPending;
  private AtomicLong lastClearCacheTime = new AtomicLong(0L);
  private final Limits limits;
  private final Object lock;
  private final String tag;
  
  public FileLruCache(Context paramContext, String paramString, Limits paramLimits)
  {
    this.tag = paramString;
    this.limits = paramLimits;
    this.directory = new File(paramContext.getCacheDir(), paramString);
    this.lock = new Object();
    if ((this.directory.mkdirs()) || (this.directory.isDirectory())) {
      BufferFile.deleteAll(this.directory);
    }
  }
  
  private void postTrim()
  {
    synchronized (this.lock)
    {
      if (!this.isTrimPending)
      {
        this.isTrimPending = true;
        Settings.getExecutor().execute(new Runnable()
        {
          public void run()
          {
            FileLruCache.this.trim();
          }
        });
      }
      return;
    }
  }
  
  private void renameToTargetAndTrim(String paramString, File paramFile)
  {
    if (!paramFile.renameTo(new File(this.directory, Utility.md5hash(paramString)))) {
      paramFile.delete();
    }
    postTrim();
  }
  
  /* Error */
  private void trim()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 67	com/facebook/internal/FileLruCache:lock	Ljava/lang/Object;
    //   4: astore_1
    //   5: aload_1
    //   6: monitorenter
    //   7: aload_0
    //   8: iconst_0
    //   9: putfield 98	com/facebook/internal/FileLruCache:isTrimPending	Z
    //   12: aload_0
    //   13: iconst_1
    //   14: putfield 131	com/facebook/internal/FileLruCache:isTrimInProgress	Z
    //   17: aload_1
    //   18: monitorexit
    //   19: getstatic 137	com/facebook/LoggingBehavior:CACHE	Lcom/facebook/LoggingBehavior;
    //   22: getstatic 34	com/facebook/internal/FileLruCache:TAG	Ljava/lang/String;
    //   25: ldc 139
    //   27: invokestatic 145	com/facebook/internal/Logger:log	(Lcom/facebook/LoggingBehavior;Ljava/lang/String;Ljava/lang/String;)V
    //   30: new 147	java/util/PriorityQueue
    //   33: dup
    //   34: invokespecial 148	java/util/PriorityQueue:<init>	()V
    //   37: astore 6
    //   39: lconst_0
    //   40: lstore 7
    //   42: lconst_0
    //   43: lstore 9
    //   45: aload_0
    //   46: getfield 65	com/facebook/internal/FileLruCache:directory	Ljava/io/File;
    //   49: invokestatic 152	com/facebook/internal/FileLruCache$BufferFile:excludeBufferFiles	()Ljava/io/FilenameFilter;
    //   52: invokevirtual 156	java/io/File:listFiles	(Ljava/io/FilenameFilter;)[Ljava/io/File;
    //   55: astore 11
    //   57: aload 11
    //   59: ifnull +126 -> 185
    //   62: aload 11
    //   64: arraylength
    //   65: istore 12
    //   67: iconst_0
    //   68: istore 13
    //   70: iload 13
    //   72: iload 12
    //   74: if_icmpge +111 -> 185
    //   77: aload 11
    //   79: iload 13
    //   81: aaload
    //   82: astore 14
    //   84: new 158	com/facebook/internal/FileLruCache$ModifiedFile
    //   87: dup
    //   88: aload 14
    //   90: invokespecial 160	com/facebook/internal/FileLruCache$ModifiedFile:<init>	(Ljava/io/File;)V
    //   93: astore 15
    //   95: aload 6
    //   97: aload 15
    //   99: invokevirtual 164	java/util/PriorityQueue:add	(Ljava/lang/Object;)Z
    //   102: pop
    //   103: getstatic 137	com/facebook/LoggingBehavior:CACHE	Lcom/facebook/LoggingBehavior;
    //   106: getstatic 34	com/facebook/internal/FileLruCache:TAG	Ljava/lang/String;
    //   109: new 166	java/lang/StringBuilder
    //   112: dup
    //   113: invokespecial 167	java/lang/StringBuilder:<init>	()V
    //   116: ldc 169
    //   118: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   121: aload 15
    //   123: invokevirtual 177	com/facebook/internal/FileLruCache$ModifiedFile:getModified	()J
    //   126: invokestatic 183	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   129: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   132: ldc 188
    //   134: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   137: aload 15
    //   139: invokevirtual 191	com/facebook/internal/FileLruCache$ModifiedFile:getFile	()Ljava/io/File;
    //   142: invokevirtual 194	java/io/File:getName	()Ljava/lang/String;
    //   145: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   148: invokevirtual 197	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   151: invokestatic 145	com/facebook/internal/Logger:log	(Lcom/facebook/LoggingBehavior;Ljava/lang/String;Ljava/lang/String;)V
    //   154: aload 14
    //   156: invokevirtual 200	java/io/File:length	()J
    //   159: lstore 17
    //   161: lload 7
    //   163: lload 17
    //   165: ladd
    //   166: lstore 7
    //   168: lload 9
    //   170: lconst_1
    //   171: ladd
    //   172: lstore 9
    //   174: iinc 13 1
    //   177: goto -107 -> 70
    //   180: astore_2
    //   181: aload_1
    //   182: monitorexit
    //   183: aload_2
    //   184: athrow
    //   185: lload 7
    //   187: aload_0
    //   188: getfield 52	com/facebook/internal/FileLruCache:limits	Lcom/facebook/internal/FileLruCache$Limits;
    //   191: invokevirtual 206	com/facebook/internal/FileLruCache$Limits:getByteCount	()I
    //   194: i2l
    //   195: lcmp
    //   196: ifgt +17 -> 213
    //   199: lload 9
    //   201: aload_0
    //   202: getfield 52	com/facebook/internal/FileLruCache:limits	Lcom/facebook/internal/FileLruCache$Limits;
    //   205: invokevirtual 209	com/facebook/internal/FileLruCache$Limits:getFileCount	()I
    //   208: i2l
    //   209: lcmp
    //   210: ifle +100 -> 310
    //   213: aload 6
    //   215: invokevirtual 213	java/util/PriorityQueue:remove	()Ljava/lang/Object;
    //   218: checkcast 158	com/facebook/internal/FileLruCache$ModifiedFile
    //   221: invokevirtual 191	com/facebook/internal/FileLruCache$ModifiedFile:getFile	()Ljava/io/File;
    //   224: astore 19
    //   226: getstatic 137	com/facebook/LoggingBehavior:CACHE	Lcom/facebook/LoggingBehavior;
    //   229: getstatic 34	com/facebook/internal/FileLruCache:TAG	Ljava/lang/String;
    //   232: new 166	java/lang/StringBuilder
    //   235: dup
    //   236: invokespecial 167	java/lang/StringBuilder:<init>	()V
    //   239: ldc 215
    //   241: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   244: aload 19
    //   246: invokevirtual 194	java/io/File:getName	()Ljava/lang/String;
    //   249: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   252: invokevirtual 197	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   255: invokestatic 145	com/facebook/internal/Logger:log	(Lcom/facebook/LoggingBehavior;Ljava/lang/String;Ljava/lang/String;)V
    //   258: lload 7
    //   260: aload 19
    //   262: invokevirtual 200	java/io/File:length	()J
    //   265: lsub
    //   266: lstore 7
    //   268: lload 9
    //   270: lconst_1
    //   271: lsub
    //   272: lstore 9
    //   274: aload 19
    //   276: invokevirtual 127	java/io/File:delete	()Z
    //   279: pop
    //   280: goto -95 -> 185
    //   283: astore_3
    //   284: aload_0
    //   285: getfield 67	com/facebook/internal/FileLruCache:lock	Ljava/lang/Object;
    //   288: astore 4
    //   290: aload 4
    //   292: monitorenter
    //   293: aload_0
    //   294: iconst_0
    //   295: putfield 131	com/facebook/internal/FileLruCache:isTrimInProgress	Z
    //   298: aload_0
    //   299: getfield 67	com/facebook/internal/FileLruCache:lock	Ljava/lang/Object;
    //   302: invokevirtual 218	java/lang/Object:notifyAll	()V
    //   305: aload 4
    //   307: monitorexit
    //   308: aload_3
    //   309: athrow
    //   310: aload_0
    //   311: getfield 67	com/facebook/internal/FileLruCache:lock	Ljava/lang/Object;
    //   314: astore 21
    //   316: aload 21
    //   318: monitorenter
    //   319: aload_0
    //   320: iconst_0
    //   321: putfield 131	com/facebook/internal/FileLruCache:isTrimInProgress	Z
    //   324: aload_0
    //   325: getfield 67	com/facebook/internal/FileLruCache:lock	Ljava/lang/Object;
    //   328: invokevirtual 218	java/lang/Object:notifyAll	()V
    //   331: aload 21
    //   333: monitorexit
    //   334: return
    //   335: astore 22
    //   337: aload 21
    //   339: monitorexit
    //   340: aload 22
    //   342: athrow
    //   343: astore 5
    //   345: aload 4
    //   347: monitorexit
    //   348: aload 5
    //   350: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	351	0	this	FileLruCache
    //   4	178	1	localObject1	Object
    //   180	4	2	localObject2	Object
    //   283	26	3	localObject3	Object
    //   288	58	4	localObject4	Object
    //   343	6	5	localObject5	Object
    //   37	177	6	localPriorityQueue	java.util.PriorityQueue
    //   40	227	7	l1	long
    //   43	230	9	l2	long
    //   55	23	11	arrayOfFile	File[]
    //   65	10	12	i	int
    //   68	107	13	j	int
    //   82	73	14	localFile1	File
    //   93	45	15	localModifiedFile	ModifiedFile
    //   159	5	17	l3	long
    //   224	51	19	localFile2	File
    //   335	6	22	localObject7	Object
    // Exception table:
    //   from	to	target	type
    //   7	19	180	finally
    //   181	183	180	finally
    //   19	39	283	finally
    //   45	57	283	finally
    //   62	67	283	finally
    //   77	161	283	finally
    //   185	213	283	finally
    //   213	268	283	finally
    //   274	280	283	finally
    //   319	334	335	finally
    //   337	340	335	finally
    //   293	308	343	finally
    //   345	348	343	finally
  }
  
  public void clearCache()
  {
    final File[] arrayOfFile = this.directory.listFiles(BufferFile.excludeBufferFiles());
    this.lastClearCacheTime.set(System.currentTimeMillis());
    if (arrayOfFile != null) {
      Settings.getExecutor().execute(new Runnable()
      {
        public void run()
        {
          File[] arrayOfFile = arrayOfFile;
          int i = arrayOfFile.length;
          for (int j = 0; j < i; j++) {
            arrayOfFile[j].delete();
          }
        }
      });
    }
  }
  
  public InputStream get(String paramString)
    throws IOException
  {
    return get(paramString, null);
  }
  
  /* Error */
  public InputStream get(String paramString1, String paramString2)
    throws IOException
  {
    // Byte code:
    //   0: new 54	java/io/File
    //   3: dup
    //   4: aload_0
    //   5: getfield 65	com/facebook/internal/FileLruCache:directory	Ljava/io/File;
    //   8: aload_1
    //   9: invokestatic 120	com/facebook/internal/Utility:md5hash	(Ljava/lang/String;)Ljava/lang/String;
    //   12: invokespecial 63	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   15: astore_3
    //   16: new 241	java/io/FileInputStream
    //   19: dup
    //   20: aload_3
    //   21: invokespecial 242	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   24: astore 4
    //   26: new 244	java/io/BufferedInputStream
    //   29: dup
    //   30: aload 4
    //   32: sipush 8192
    //   35: invokespecial 247	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;I)V
    //   38: astore 5
    //   40: aload 5
    //   42: invokestatic 253	com/facebook/internal/FileLruCache$StreamHeader:readHeader	(Ljava/io/InputStream;)Lorg/json/JSONObject;
    //   45: astore 7
    //   47: aload 7
    //   49: ifnonnull +18 -> 67
    //   52: iconst_0
    //   53: ifne +8 -> 61
    //   56: aload 5
    //   58: invokevirtual 256	java/io/BufferedInputStream:close	()V
    //   61: aconst_null
    //   62: areturn
    //   63: astore 15
    //   65: aconst_null
    //   66: areturn
    //   67: aload 7
    //   69: ldc 8
    //   71: invokevirtual 261	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   74: astore 8
    //   76: aload 8
    //   78: ifnull +16 -> 94
    //   81: aload 8
    //   83: aload_1
    //   84: invokevirtual 266	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   87: istore 9
    //   89: iload 9
    //   91: ifne +14 -> 105
    //   94: iconst_0
    //   95: ifne +8 -> 103
    //   98: aload 5
    //   100: invokevirtual 256	java/io/BufferedInputStream:close	()V
    //   103: aconst_null
    //   104: areturn
    //   105: aload 7
    //   107: ldc 11
    //   109: aconst_null
    //   110: invokevirtual 269	org/json/JSONObject:optString	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   113: astore 10
    //   115: aload_2
    //   116: ifnonnull +8 -> 124
    //   119: aload 10
    //   121: ifnonnull +20 -> 141
    //   124: aload_2
    //   125: ifnull +27 -> 152
    //   128: aload_2
    //   129: aload 10
    //   131: invokevirtual 266	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   134: istore 11
    //   136: iload 11
    //   138: ifne +14 -> 152
    //   141: iconst_0
    //   142: ifne +8 -> 150
    //   145: aload 5
    //   147: invokevirtual 256	java/io/BufferedInputStream:close	()V
    //   150: aconst_null
    //   151: areturn
    //   152: new 271	java/util/Date
    //   155: dup
    //   156: invokespecial 272	java/util/Date:<init>	()V
    //   159: invokevirtual 275	java/util/Date:getTime	()J
    //   162: lstore 12
    //   164: getstatic 137	com/facebook/LoggingBehavior:CACHE	Lcom/facebook/LoggingBehavior;
    //   167: getstatic 34	com/facebook/internal/FileLruCache:TAG	Ljava/lang/String;
    //   170: new 166	java/lang/StringBuilder
    //   173: dup
    //   174: invokespecial 167	java/lang/StringBuilder:<init>	()V
    //   177: ldc_w 277
    //   180: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   183: lload 12
    //   185: invokestatic 183	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   188: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   191: ldc_w 279
    //   194: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   197: aload_3
    //   198: invokevirtual 194	java/io/File:getName	()Ljava/lang/String;
    //   201: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   204: invokevirtual 197	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   207: invokestatic 145	com/facebook/internal/Logger:log	(Lcom/facebook/LoggingBehavior;Ljava/lang/String;Ljava/lang/String;)V
    //   210: aload_3
    //   211: lload 12
    //   213: invokevirtual 283	java/io/File:setLastModified	(J)Z
    //   216: pop
    //   217: iconst_1
    //   218: ifne +8 -> 226
    //   221: aload 5
    //   223: invokevirtual 256	java/io/BufferedInputStream:close	()V
    //   226: aload 5
    //   228: areturn
    //   229: astore 6
    //   231: iconst_0
    //   232: ifne +8 -> 240
    //   235: aload 5
    //   237: invokevirtual 256	java/io/BufferedInputStream:close	()V
    //   240: aload 6
    //   242: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	243	0	this	FileLruCache
    //   0	243	1	paramString1	String
    //   0	243	2	paramString2	String
    //   15	196	3	localFile	File
    //   24	7	4	localFileInputStream	java.io.FileInputStream
    //   38	198	5	localBufferedInputStream	java.io.BufferedInputStream
    //   229	12	6	localObject	Object
    //   45	61	7	localJSONObject	JSONObject
    //   74	8	8	str1	String
    //   87	3	9	bool1	boolean
    //   113	17	10	str2	String
    //   134	3	11	bool2	boolean
    //   162	50	12	l	long
    //   63	1	15	localIOException	IOException
    // Exception table:
    //   from	to	target	type
    //   16	26	63	java/io/IOException
    //   40	47	229	finally
    //   67	76	229	finally
    //   81	89	229	finally
    //   105	115	229	finally
    //   128	136	229	finally
    //   152	217	229	finally
  }
  
  public InputStream interceptAndPut(String paramString, InputStream paramInputStream)
    throws IOException
  {
    return new CopyingInputStream(paramInputStream, openPutStream(paramString));
  }
  
  OutputStream openPutStream(String paramString)
    throws IOException
  {
    return openPutStream(paramString, null);
  }
  
  /* Error */
  public OutputStream openPutStream(final String paramString1, String paramString2)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 65	com/facebook/internal/FileLruCache:directory	Ljava/io/File;
    //   4: invokestatic 305	com/facebook/internal/FileLruCache$BufferFile:newFile	(Ljava/io/File;)Ljava/io/File;
    //   7: astore_3
    //   8: aload_3
    //   9: invokevirtual 127	java/io/File:delete	()Z
    //   12: pop
    //   13: aload_3
    //   14: invokevirtual 308	java/io/File:createNewFile	()Z
    //   17: ifne +34 -> 51
    //   20: new 236	java/io/IOException
    //   23: dup
    //   24: new 166	java/lang/StringBuilder
    //   27: dup
    //   28: invokespecial 167	java/lang/StringBuilder:<init>	()V
    //   31: ldc_w 310
    //   34: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   37: aload_3
    //   38: invokevirtual 313	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   41: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   44: invokevirtual 197	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   47: invokespecial 316	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   50: athrow
    //   51: new 318	java/io/FileOutputStream
    //   54: dup
    //   55: aload_3
    //   56: invokespecial 319	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   59: astore 5
    //   61: new 321	java/io/BufferedOutputStream
    //   64: dup
    //   65: new 323	com/facebook/internal/FileLruCache$CloseCallbackOutputStream
    //   68: dup
    //   69: aload 5
    //   71: new 325	com/facebook/internal/FileLruCache$1
    //   74: dup
    //   75: aload_0
    //   76: invokestatic 224	java/lang/System:currentTimeMillis	()J
    //   79: aload_3
    //   80: aload_1
    //   81: invokespecial 328	com/facebook/internal/FileLruCache$1:<init>	(Lcom/facebook/internal/FileLruCache;JLjava/io/File;Ljava/lang/String;)V
    //   84: invokespecial 331	com/facebook/internal/FileLruCache$CloseCallbackOutputStream:<init>	(Ljava/io/OutputStream;Lcom/facebook/internal/FileLruCache$StreamCloseCallback;)V
    //   87: sipush 8192
    //   90: invokespecial 334	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;I)V
    //   93: astore 6
    //   95: new 258	org/json/JSONObject
    //   98: dup
    //   99: invokespecial 335	org/json/JSONObject:<init>	()V
    //   102: astore 7
    //   104: aload 7
    //   106: ldc 8
    //   108: aload_1
    //   109: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   112: pop
    //   113: aload_2
    //   114: invokestatic 343	com/facebook/internal/Utility:isNullOrEmpty	(Ljava/lang/String;)Z
    //   117: ifne +12 -> 129
    //   120: aload 7
    //   122: ldc 11
    //   124: aload_2
    //   125: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   128: pop
    //   129: aload 6
    //   131: aload 7
    //   133: invokestatic 347	com/facebook/internal/FileLruCache$StreamHeader:writeHeader	(Ljava/io/OutputStream;Lorg/json/JSONObject;)V
    //   136: iconst_1
    //   137: ifne +8 -> 145
    //   140: aload 6
    //   142: invokevirtual 348	java/io/BufferedOutputStream:close	()V
    //   145: aload 6
    //   147: areturn
    //   148: astore 12
    //   150: getstatic 137	com/facebook/LoggingBehavior:CACHE	Lcom/facebook/LoggingBehavior;
    //   153: iconst_5
    //   154: getstatic 34	com/facebook/internal/FileLruCache:TAG	Ljava/lang/String;
    //   157: new 166	java/lang/StringBuilder
    //   160: dup
    //   161: invokespecial 167	java/lang/StringBuilder:<init>	()V
    //   164: ldc_w 350
    //   167: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   170: aload 12
    //   172: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   175: invokevirtual 197	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   178: invokestatic 353	com/facebook/internal/Logger:log	(Lcom/facebook/LoggingBehavior;ILjava/lang/String;Ljava/lang/String;)V
    //   181: new 236	java/io/IOException
    //   184: dup
    //   185: aload 12
    //   187: invokevirtual 356	java/io/FileNotFoundException:getMessage	()Ljava/lang/String;
    //   190: invokespecial 316	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   193: athrow
    //   194: astore 9
    //   196: getstatic 137	com/facebook/LoggingBehavior:CACHE	Lcom/facebook/LoggingBehavior;
    //   199: iconst_5
    //   200: getstatic 34	com/facebook/internal/FileLruCache:TAG	Ljava/lang/String;
    //   203: new 166	java/lang/StringBuilder
    //   206: dup
    //   207: invokespecial 167	java/lang/StringBuilder:<init>	()V
    //   210: ldc_w 358
    //   213: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   216: aload 9
    //   218: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   221: invokevirtual 197	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   224: invokestatic 353	com/facebook/internal/Logger:log	(Lcom/facebook/LoggingBehavior;ILjava/lang/String;Ljava/lang/String;)V
    //   227: new 236	java/io/IOException
    //   230: dup
    //   231: aload 9
    //   233: invokevirtual 359	org/json/JSONException:getMessage	()Ljava/lang/String;
    //   236: invokespecial 316	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   239: athrow
    //   240: astore 8
    //   242: iconst_0
    //   243: ifne +8 -> 251
    //   246: aload 6
    //   248: invokevirtual 348	java/io/BufferedOutputStream:close	()V
    //   251: aload 8
    //   253: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	254	0	this	FileLruCache
    //   0	254	1	paramString1	String
    //   0	254	2	paramString2	String
    //   7	73	3	localFile	File
    //   59	11	5	localFileOutputStream	java.io.FileOutputStream
    //   93	154	6	localBufferedOutputStream	java.io.BufferedOutputStream
    //   102	30	7	localJSONObject	JSONObject
    //   240	12	8	localObject	Object
    //   194	38	9	localJSONException	JSONException
    //   148	38	12	localFileNotFoundException	java.io.FileNotFoundException
    // Exception table:
    //   from	to	target	type
    //   51	61	148	java/io/FileNotFoundException
    //   95	129	194	org/json/JSONException
    //   129	136	194	org/json/JSONException
    //   95	129	240	finally
    //   129	136	240	finally
    //   196	240	240	finally
  }
  
  long sizeInBytesForTest()
  {
    long l;
    synchronized (this.lock)
    {
      for (;;)
      {
        if (!this.isTrimPending)
        {
          boolean bool = this.isTrimInProgress;
          if (!bool) {
            break;
          }
        }
        try
        {
          this.lock.wait();
        }
        catch (InterruptedException localInterruptedException) {}
      }
      File[] arrayOfFile = this.directory.listFiles();
      l = 0L;
      if (arrayOfFile != null)
      {
        int i = arrayOfFile.length;
        int j = 0;
        if (j < i)
        {
          l += arrayOfFile[j].length();
          j++;
        }
      }
    }
    return l;
  }
  
  public String toString()
  {
    return "{FileLruCache: tag:" + this.tag + " file:" + this.directory.getName() + "}";
  }
  
  private static class BufferFile
  {
    private static final String FILE_NAME_PREFIX = "buffer";
    private static final FilenameFilter filterExcludeBufferFiles = new FilenameFilter()
    {
      public boolean accept(File paramAnonymousFile, String paramAnonymousString)
      {
        return !paramAnonymousString.startsWith("buffer");
      }
    };
    private static final FilenameFilter filterExcludeNonBufferFiles = new FilenameFilter()
    {
      public boolean accept(File paramAnonymousFile, String paramAnonymousString)
      {
        return paramAnonymousString.startsWith("buffer");
      }
    };
    
    static void deleteAll(File paramFile)
    {
      File[] arrayOfFile = paramFile.listFiles(excludeNonBufferFiles());
      if (arrayOfFile != null)
      {
        int i = arrayOfFile.length;
        for (int j = 0; j < i; j++) {
          arrayOfFile[j].delete();
        }
      }
    }
    
    static FilenameFilter excludeBufferFiles()
    {
      return filterExcludeBufferFiles;
    }
    
    static FilenameFilter excludeNonBufferFiles()
    {
      return filterExcludeNonBufferFiles;
    }
    
    static File newFile(File paramFile)
    {
      return new File(paramFile, "buffer" + Long.valueOf(FileLruCache.bufferIndex.incrementAndGet()).toString());
    }
  }
  
  private static class CloseCallbackOutputStream
    extends OutputStream
  {
    final FileLruCache.StreamCloseCallback callback;
    final OutputStream innerStream;
    
    CloseCallbackOutputStream(OutputStream paramOutputStream, FileLruCache.StreamCloseCallback paramStreamCloseCallback)
    {
      this.innerStream = paramOutputStream;
      this.callback = paramStreamCloseCallback;
    }
    
    public void close()
      throws IOException
    {
      try
      {
        this.innerStream.close();
        return;
      }
      finally
      {
        this.callback.onClose();
      }
    }
    
    public void flush()
      throws IOException
    {
      this.innerStream.flush();
    }
    
    public void write(int paramInt)
      throws IOException
    {
      this.innerStream.write(paramInt);
    }
    
    public void write(byte[] paramArrayOfByte)
      throws IOException
    {
      this.innerStream.write(paramArrayOfByte);
    }
    
    public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws IOException
    {
      this.innerStream.write(paramArrayOfByte, paramInt1, paramInt2);
    }
  }
  
  private static final class CopyingInputStream
    extends InputStream
  {
    final InputStream input;
    final OutputStream output;
    
    CopyingInputStream(InputStream paramInputStream, OutputStream paramOutputStream)
    {
      this.input = paramInputStream;
      this.output = paramOutputStream;
    }
    
    public int available()
      throws IOException
    {
      return this.input.available();
    }
    
    public void close()
      throws IOException
    {
      try
      {
        this.input.close();
        return;
      }
      finally
      {
        this.output.close();
      }
    }
    
    public void mark(int paramInt)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean markSupported()
    {
      return false;
    }
    
    public int read()
      throws IOException
    {
      int i = this.input.read();
      if (i >= 0) {
        this.output.write(i);
      }
      return i;
    }
    
    public int read(byte[] paramArrayOfByte)
      throws IOException
    {
      int i = this.input.read(paramArrayOfByte);
      if (i > 0) {
        this.output.write(paramArrayOfByte, 0, i);
      }
      return i;
    }
    
    public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws IOException
    {
      int i = this.input.read(paramArrayOfByte, paramInt1, paramInt2);
      if (i > 0) {
        this.output.write(paramArrayOfByte, paramInt1, i);
      }
      return i;
    }
    
    public void reset()
    {
      try
      {
        throw new UnsupportedOperationException();
      }
      finally {}
    }
    
    public long skip(long paramLong)
      throws IOException
    {
      byte[] arrayOfByte = new byte[1024];
      int i;
      for (long l = 0L;; l += i) {
        if (l < paramLong)
        {
          i = read(arrayOfByte, 0, (int)Math.min(paramLong - l, arrayOfByte.length));
          if (i >= 0) {}
        }
        else
        {
          return l;
        }
      }
    }
  }
  
  public static final class Limits
  {
    private int byteCount = 1048576;
    private int fileCount = 1024;
    
    int getByteCount()
    {
      return this.byteCount;
    }
    
    int getFileCount()
    {
      return this.fileCount;
    }
    
    void setByteCount(int paramInt)
    {
      if (paramInt < 0) {
        throw new InvalidParameterException("Cache byte-count limit must be >= 0");
      }
      this.byteCount = paramInt;
    }
    
    void setFileCount(int paramInt)
    {
      if (paramInt < 0) {
        throw new InvalidParameterException("Cache file count limit must be >= 0");
      }
      this.fileCount = paramInt;
    }
  }
  
  private static final class ModifiedFile
    implements Comparable<ModifiedFile>
  {
    private static final int HASH_MULTIPLIER = 37;
    private static final int HASH_SEED = 29;
    private final File file;
    private final long modified;
    
    ModifiedFile(File paramFile)
    {
      this.file = paramFile;
      this.modified = paramFile.lastModified();
    }
    
    public int compareTo(ModifiedFile paramModifiedFile)
    {
      if (getModified() < paramModifiedFile.getModified()) {
        return -1;
      }
      if (getModified() > paramModifiedFile.getModified()) {
        return 1;
      }
      return getFile().compareTo(paramModifiedFile.getFile());
    }
    
    public boolean equals(Object paramObject)
    {
      return ((paramObject instanceof ModifiedFile)) && (compareTo((ModifiedFile)paramObject) == 0);
    }
    
    File getFile()
    {
      return this.file;
    }
    
    long getModified()
    {
      return this.modified;
    }
    
    public int hashCode()
    {
      return 37 * (1073 + this.file.hashCode()) + (int)(this.modified % 2147483647L);
    }
  }
  
  private static abstract interface StreamCloseCallback
  {
    public abstract void onClose();
  }
  
  private static final class StreamHeader
  {
    private static final int HEADER_VERSION;
    
    static JSONObject readHeader(InputStream paramInputStream)
      throws IOException
    {
      if (paramInputStream.read() != 0) {
        return null;
      }
      int i = 0;
      for (int j = 0; j < 3; j++)
      {
        int n = paramInputStream.read();
        if (n == -1)
        {
          Logger.log(LoggingBehavior.CACHE, FileLruCache.TAG, "readHeader: stream.read returned -1 while reading header size");
          return null;
        }
        i = (i << 8) + (n & 0xFF);
      }
      byte[] arrayOfByte = new byte[i];
      int k = 0;
      while (k < arrayOfByte.length)
      {
        int m = paramInputStream.read(arrayOfByte, k, arrayOfByte.length - k);
        if (m < 1)
        {
          Logger.log(LoggingBehavior.CACHE, FileLruCache.TAG, "readHeader: stream.read stopped at " + Integer.valueOf(k) + " when expected " + arrayOfByte.length);
          return null;
        }
        k += m;
      }
      JSONTokener localJSONTokener = new JSONTokener(new String(arrayOfByte));
      try
      {
        Object localObject = localJSONTokener.nextValue();
        if (!(localObject instanceof JSONObject))
        {
          Logger.log(LoggingBehavior.CACHE, FileLruCache.TAG, "readHeader: expected JSONObject, got " + localObject.getClass().getCanonicalName());
          return null;
        }
        JSONObject localJSONObject = (JSONObject)localObject;
        return localJSONObject;
      }
      catch (JSONException localJSONException)
      {
        throw new IOException(localJSONException.getMessage());
      }
    }
    
    static void writeHeader(OutputStream paramOutputStream, JSONObject paramJSONObject)
      throws IOException
    {
      byte[] arrayOfByte = paramJSONObject.toString().getBytes();
      paramOutputStream.write(0);
      paramOutputStream.write(0xFF & arrayOfByte.length >> 16);
      paramOutputStream.write(0xFF & arrayOfByte.length >> 8);
      paramOutputStream.write(0xFF & arrayOfByte.length >> 0);
      paramOutputStream.write(arrayOfByte);
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.facebook.internal.FileLruCache
 * JD-Core Version:    0.7.0.1
 */