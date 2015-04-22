package com.facebook.internal;

import android.content.Context;
import com.facebook.LoggingBehavior;
import java.io.IOException;

class UrlRedirectCache
{
  private static final String REDIRECT_CONTENT_TAG = TAG + "_Redirect";
  static final String TAG = UrlRedirectCache.class.getSimpleName();
  private static volatile FileLruCache urlRedirectCache;
  
  /* Error */
  static void cacheUriRedirect(Context paramContext, java.net.URI paramURI1, java.net.URI paramURI2)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnull +7 -> 8
    //   4: aload_2
    //   5: ifnonnull +4 -> 9
    //   8: return
    //   9: aconst_null
    //   10: astore_3
    //   11: aload_0
    //   12: invokestatic 44	com/facebook/internal/UrlRedirectCache:getCache	(Landroid/content/Context;)Lcom/facebook/internal/FileLruCache;
    //   15: aload_1
    //   16: invokevirtual 47	java/net/URI:toString	()Ljava/lang/String;
    //   19: getstatic 35	com/facebook/internal/UrlRedirectCache:REDIRECT_CONTENT_TAG	Ljava/lang/String;
    //   22: invokevirtual 53	com/facebook/internal/FileLruCache:openPutStream	(Ljava/lang/String;Ljava/lang/String;)Ljava/io/OutputStream;
    //   25: astore_3
    //   26: aload_3
    //   27: aload_2
    //   28: invokevirtual 47	java/net/URI:toString	()Ljava/lang/String;
    //   31: invokevirtual 59	java/lang/String:getBytes	()[B
    //   34: invokevirtual 65	java/io/OutputStream:write	([B)V
    //   37: aload_3
    //   38: invokestatic 71	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   41: return
    //   42: astore 5
    //   44: aload_3
    //   45: invokestatic 71	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   48: return
    //   49: astore 4
    //   51: aload_3
    //   52: invokestatic 71	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   55: aload 4
    //   57: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	58	0	paramContext	Context
    //   0	58	1	paramURI1	java.net.URI
    //   0	58	2	paramURI2	java.net.URI
    //   10	42	3	localOutputStream	java.io.OutputStream
    //   49	7	4	localObject	Object
    //   42	1	5	localIOException	IOException
    // Exception table:
    //   from	to	target	type
    //   11	37	42	java/io/IOException
    //   11	37	49	finally
  }
  
  static void clearCache(Context paramContext)
  {
    try
    {
      getCache(paramContext).clearCache();
      return;
    }
    catch (IOException localIOException)
    {
      Logger.log(LoggingBehavior.CACHE, 5, TAG, "clearCache failed " + localIOException.getMessage());
    }
  }
  
  static FileLruCache getCache(Context paramContext)
    throws IOException
  {
    try
    {
      if (urlRedirectCache == null) {
        urlRedirectCache = new FileLruCache(paramContext.getApplicationContext(), TAG, new FileLruCache.Limits());
      }
      FileLruCache localFileLruCache = urlRedirectCache;
      return localFileLruCache;
    }
    finally {}
  }
  
  /* Error */
  static java.net.URI getRedirectedUri(Context paramContext, java.net.URI paramURI)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnonnull +5 -> 6
    //   4: aconst_null
    //   5: areturn
    //   6: aload_1
    //   7: invokevirtual 47	java/net/URI:toString	()Ljava/lang/String;
    //   10: astore_2
    //   11: aconst_null
    //   12: astore_3
    //   13: aload_0
    //   14: invokestatic 44	com/facebook/internal/UrlRedirectCache:getCache	(Landroid/content/Context;)Lcom/facebook/internal/FileLruCache;
    //   17: astore 7
    //   19: iconst_0
    //   20: istore 8
    //   22: aconst_null
    //   23: astore 9
    //   25: aload 7
    //   27: aload_2
    //   28: getstatic 35	com/facebook/internal/UrlRedirectCache:REDIRECT_CONTENT_TAG	Ljava/lang/String;
    //   31: invokevirtual 114	com/facebook/internal/FileLruCache:get	(Ljava/lang/String;Ljava/lang/String;)Ljava/io/InputStream;
    //   34: astore 12
    //   36: aload 12
    //   38: ifnull +91 -> 129
    //   41: iconst_1
    //   42: istore 8
    //   44: new 116	java/io/InputStreamReader
    //   47: dup
    //   48: aload 12
    //   50: invokespecial 119	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   53: astore_3
    //   54: sipush 128
    //   57: newarray char
    //   59: astore 15
    //   61: new 21	java/lang/StringBuilder
    //   64: dup
    //   65: invokespecial 24	java/lang/StringBuilder:<init>	()V
    //   68: astore 16
    //   70: aload_3
    //   71: aload 15
    //   73: iconst_0
    //   74: aload 15
    //   76: arraylength
    //   77: invokevirtual 123	java/io/InputStreamReader:read	([CII)I
    //   80: istore 17
    //   82: iload 17
    //   84: ifle +25 -> 109
    //   87: aload 16
    //   89: aload 15
    //   91: iconst_0
    //   92: iload 17
    //   94: invokevirtual 126	java/lang/StringBuilder:append	([CII)Ljava/lang/StringBuilder;
    //   97: pop
    //   98: goto -28 -> 70
    //   101: astore 6
    //   103: aload_3
    //   104: invokestatic 71	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   107: aconst_null
    //   108: areturn
    //   109: aload_3
    //   110: invokestatic 71	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   113: aload 16
    //   115: invokevirtual 33	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   118: astore 19
    //   120: aload 19
    //   122: astore_2
    //   123: aload_3
    //   124: astore 9
    //   126: goto -101 -> 25
    //   129: iload 8
    //   131: ifeq +21 -> 152
    //   134: new 46	java/net/URI
    //   137: dup
    //   138: aload_2
    //   139: invokespecial 129	java/net/URI:<init>	(Ljava/lang/String;)V
    //   142: astore 14
    //   144: aload 9
    //   146: invokestatic 71	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   149: aload 14
    //   151: areturn
    //   152: aload 9
    //   154: invokestatic 71	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   157: aload 9
    //   159: pop
    //   160: aconst_null
    //   161: areturn
    //   162: astore 5
    //   164: aload_3
    //   165: invokestatic 71	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   168: aconst_null
    //   169: areturn
    //   170: astore 4
    //   172: aload_3
    //   173: invokestatic 71	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   176: aload 4
    //   178: athrow
    //   179: astore 4
    //   181: aload 9
    //   183: astore_3
    //   184: goto -12 -> 172
    //   187: astore 11
    //   189: aload 9
    //   191: astore_3
    //   192: goto -28 -> 164
    //   195: astore 10
    //   197: aload 9
    //   199: astore_3
    //   200: goto -97 -> 103
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	203	0	paramContext	Context
    //   0	203	1	paramURI	java.net.URI
    //   10	129	2	localObject1	Object
    //   12	188	3	localObject2	Object
    //   170	7	4	localObject3	Object
    //   179	1	4	localObject4	Object
    //   162	1	5	localIOException1	IOException
    //   101	1	6	localURISyntaxException1	java.net.URISyntaxException
    //   17	9	7	localFileLruCache	FileLruCache
    //   20	110	8	i	int
    //   23	175	9	localObject5	Object
    //   195	1	10	localURISyntaxException2	java.net.URISyntaxException
    //   187	1	11	localIOException2	IOException
    //   34	15	12	localInputStream	java.io.InputStream
    //   142	8	14	localURI	java.net.URI
    //   59	31	15	arrayOfChar	char[]
    //   68	46	16	localStringBuilder	java.lang.StringBuilder
    //   80	13	17	j	int
    //   118	3	19	str	String
    // Exception table:
    //   from	to	target	type
    //   13	19	101	java/net/URISyntaxException
    //   54	70	101	java/net/URISyntaxException
    //   70	82	101	java/net/URISyntaxException
    //   87	98	101	java/net/URISyntaxException
    //   109	120	101	java/net/URISyntaxException
    //   13	19	162	java/io/IOException
    //   54	70	162	java/io/IOException
    //   70	82	162	java/io/IOException
    //   87	98	162	java/io/IOException
    //   109	120	162	java/io/IOException
    //   13	19	170	finally
    //   54	70	170	finally
    //   70	82	170	finally
    //   87	98	170	finally
    //   109	120	170	finally
    //   25	36	179	finally
    //   44	54	179	finally
    //   134	144	179	finally
    //   25	36	187	java/io/IOException
    //   44	54	187	java/io/IOException
    //   134	144	187	java/io/IOException
    //   25	36	195	java/net/URISyntaxException
    //   44	54	195	java/net/URISyntaxException
    //   134	144	195	java/net/URISyntaxException
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.facebook.internal.UrlRedirectCache
 * JD-Core Version:    0.7.0.1
 */