package com.ultra.sdk.volley.toolbox;

import android.os.SystemClock;
import com.ultra.sdk.volley.Cache;
import com.ultra.sdk.volley.Cache.Entry;
import com.ultra.sdk.volley.VolleyLog;
import java.io.File;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class DiskBasedCache
  implements Cache
{
  private static final int CACHE_VERSION = 2;
  private static final int DEFAULT_DISK_USAGE_BYTES = 5242880;
  private static final float HYSTERESIS_FACTOR = 0.9F;
  private final Map<String, CacheHeader> mEntries = new LinkedHashMap(16, 0.75F, true);
  private final int mMaxCacheSizeInBytes;
  private final File mRootDirectory;
  private long mTotalSize = 0L;
  
  public DiskBasedCache(File paramFile)
  {
    this(paramFile, 5242880);
  }
  
  public DiskBasedCache(File paramFile, int paramInt)
  {
    this.mRootDirectory = paramFile;
    this.mMaxCacheSizeInBytes = paramInt;
  }
  
  private String getFilenameForKey(String paramString)
  {
    int i = paramString.length() / 2;
    return String.valueOf(paramString.substring(0, i).hashCode()) + String.valueOf(paramString.substring(i).hashCode());
  }
  
  private void pruneIfNeeded(int paramInt)
  {
    if (this.mTotalSize + paramInt < this.mMaxCacheSizeInBytes) {
      return;
    }
    int i;
    Iterator localIterator;
    while (!localIterator.hasNext())
    {
      i = 0;
      SystemClock.elapsedRealtime();
      localIterator = this.mEntries.entrySet().iterator();
    }
    CacheHeader localCacheHeader = (CacheHeader)((Map.Entry)localIterator.next()).getValue();
    if (getFileForKey(localCacheHeader.key).delete()) {
      this.mTotalSize -= localCacheHeader.size;
    }
    for (;;)
    {
      localIterator.remove();
      i++;
      if ((float)(this.mTotalSize + paramInt) >= 0.9F * this.mMaxCacheSizeInBytes) {
        break;
      }
      return;
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = localCacheHeader.key;
      arrayOfObject[1] = getFilenameForKey(localCacheHeader.key);
      VolleyLog.d("Could not delete cache entry for key=%s, filename=%s", arrayOfObject);
    }
  }
  
  private void putEntry(String paramString, CacheHeader paramCacheHeader)
  {
    if (!this.mEntries.containsKey(paramString)) {}
    CacheHeader localCacheHeader;
    for (this.mTotalSize += paramCacheHeader.size;; this.mTotalSize += paramCacheHeader.size - localCacheHeader.size)
    {
      this.mEntries.put(paramString, paramCacheHeader);
      return;
      localCacheHeader = (CacheHeader)this.mEntries.get(paramString);
    }
  }
  
  private void removeEntry(String paramString)
  {
    CacheHeader localCacheHeader = (CacheHeader)this.mEntries.get(paramString);
    if (localCacheHeader != null)
    {
      this.mTotalSize -= localCacheHeader.size;
      this.mEntries.remove(paramString);
    }
  }
  
  private static byte[] streamToBytes(InputStream paramInputStream, int paramInt)
    throws IOException
  {
    byte[] arrayOfByte = new byte[paramInt];
    int i = 0;
    for (;;)
    {
      int j;
      if (i < paramInt)
      {
        j = paramInputStream.read(arrayOfByte, i, paramInt - i);
        if (j != -1) {}
      }
      else
      {
        if (i == paramInt) {
          break;
        }
        throw new IOException("Expected " + paramInt + " bytes, read " + i + " bytes");
      }
      i += j;
    }
    return arrayOfByte;
  }
  
  public void clear()
  {
    for (;;)
    {
      File[] arrayOfFile;
      int i;
      int j;
      try
      {
        arrayOfFile = this.mRootDirectory.listFiles();
        if (arrayOfFile != null)
        {
          i = arrayOfFile.length;
          j = 0;
          break label68;
        }
        this.mEntries.clear();
        this.mTotalSize = 0L;
        VolleyLog.d("Cache cleared.", new Object[0]);
        return;
      }
      finally {}
      arrayOfFile[j].delete();
      j++;
      label68:
      if (j < i) {}
    }
  }
  
  /* Error */
  public Cache.Entry get(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 38	com/ultra/sdk/volley/toolbox/DiskBasedCache:mEntries	Ljava/util/Map;
    //   6: aload_1
    //   7: invokeinterface 161 2 0
    //   12: checkcast 118	com/ultra/sdk/volley/toolbox/DiskBasedCache$CacheHeader
    //   15: astore_3
    //   16: aconst_null
    //   17: astore 4
    //   19: aload_3
    //   20: ifnonnull +8 -> 28
    //   23: aload_0
    //   24: monitorexit
    //   25: aload 4
    //   27: areturn
    //   28: aload_0
    //   29: aload_1
    //   30: invokevirtual 126	com/ultra/sdk/volley/toolbox/DiskBasedCache:getFileForKey	(Ljava/lang/String;)Ljava/io/File;
    //   33: astore 5
    //   35: aconst_null
    //   36: astore 6
    //   38: new 196	com/ultra/sdk/volley/toolbox/DiskBasedCache$CountingInputStream
    //   41: dup
    //   42: new 198	java/io/FileInputStream
    //   45: dup
    //   46: aload 5
    //   48: invokespecial 200	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   51: aconst_null
    //   52: invokespecial 203	com/ultra/sdk/volley/toolbox/DiskBasedCache$CountingInputStream:<init>	(Ljava/io/InputStream;Lcom/ultra/sdk/volley/toolbox/DiskBasedCache$CountingInputStream;)V
    //   55: astore 7
    //   57: aload 7
    //   59: invokestatic 207	com/ultra/sdk/volley/toolbox/DiskBasedCache$CacheHeader:readHeader	(Ljava/io/InputStream;)Lcom/ultra/sdk/volley/toolbox/DiskBasedCache$CacheHeader;
    //   62: pop
    //   63: aload_3
    //   64: aload 7
    //   66: aload 5
    //   68: invokevirtual 209	java/io/File:length	()J
    //   71: aload 7
    //   73: invokestatic 213	com/ultra/sdk/volley/toolbox/DiskBasedCache$CountingInputStream:access$1	(Lcom/ultra/sdk/volley/toolbox/DiskBasedCache$CountingInputStream;)I
    //   76: i2l
    //   77: lsub
    //   78: l2i
    //   79: invokestatic 215	com/ultra/sdk/volley/toolbox/DiskBasedCache:streamToBytes	(Ljava/io/InputStream;I)[B
    //   82: invokevirtual 219	com/ultra/sdk/volley/toolbox/DiskBasedCache$CacheHeader:toCacheEntry	([B)Lcom/ultra/sdk/volley/Cache$Entry;
    //   85: astore 14
    //   87: aload 7
    //   89: ifnull +8 -> 97
    //   92: aload 7
    //   94: invokevirtual 222	com/ultra/sdk/volley/toolbox/DiskBasedCache$CountingInputStream:close	()V
    //   97: aload 14
    //   99: astore 4
    //   101: goto -78 -> 23
    //   104: astore 15
    //   106: aconst_null
    //   107: astore 4
    //   109: goto -86 -> 23
    //   112: astore 8
    //   114: iconst_2
    //   115: anewarray 4	java/lang/Object
    //   118: astore 11
    //   120: aload 11
    //   122: iconst_0
    //   123: aload 5
    //   125: invokevirtual 225	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   128: aastore
    //   129: aload 11
    //   131: iconst_1
    //   132: aload 8
    //   134: invokevirtual 226	java/io/IOException:toString	()Ljava/lang/String;
    //   137: aastore
    //   138: ldc 228
    //   140: aload 11
    //   142: invokestatic 147	com/ultra/sdk/volley/VolleyLog:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   145: aload_0
    //   146: aload_1
    //   147: invokevirtual 230	com/ultra/sdk/volley/toolbox/DiskBasedCache:remove	(Ljava/lang/String;)V
    //   150: aconst_null
    //   151: astore 4
    //   153: aload 6
    //   155: ifnull -132 -> 23
    //   158: aload 6
    //   160: invokevirtual 222	com/ultra/sdk/volley/toolbox/DiskBasedCache$CountingInputStream:close	()V
    //   163: aconst_null
    //   164: astore 4
    //   166: goto -143 -> 23
    //   169: astore 12
    //   171: aconst_null
    //   172: astore 4
    //   174: goto -151 -> 23
    //   177: astore 9
    //   179: aload 6
    //   181: ifnull +8 -> 189
    //   184: aload 6
    //   186: invokevirtual 222	com/ultra/sdk/volley/toolbox/DiskBasedCache$CountingInputStream:close	()V
    //   189: aload 9
    //   191: athrow
    //   192: astore_2
    //   193: aload_0
    //   194: monitorexit
    //   195: aload_2
    //   196: athrow
    //   197: astore 10
    //   199: aconst_null
    //   200: astore 4
    //   202: goto -179 -> 23
    //   205: astore 9
    //   207: aload 7
    //   209: astore 6
    //   211: goto -32 -> 179
    //   214: astore 8
    //   216: aload 7
    //   218: astore 6
    //   220: goto -106 -> 114
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	223	0	this	DiskBasedCache
    //   0	223	1	paramString	String
    //   192	4	2	localObject1	Object
    //   15	49	3	localCacheHeader	CacheHeader
    //   17	184	4	localObject2	Object
    //   33	91	5	localFile	File
    //   36	183	6	localObject3	Object
    //   55	162	7	localCountingInputStream	CountingInputStream
    //   112	21	8	localIOException1	IOException
    //   214	1	8	localIOException2	IOException
    //   177	13	9	localObject4	Object
    //   205	1	9	localObject5	Object
    //   197	1	10	localIOException3	IOException
    //   118	23	11	arrayOfObject	Object[]
    //   169	1	12	localIOException4	IOException
    //   85	13	14	localEntry	Cache.Entry
    //   104	1	15	localIOException5	IOException
    // Exception table:
    //   from	to	target	type
    //   92	97	104	java/io/IOException
    //   38	57	112	java/io/IOException
    //   158	163	169	java/io/IOException
    //   38	57	177	finally
    //   114	150	177	finally
    //   2	16	192	finally
    //   28	35	192	finally
    //   92	97	192	finally
    //   158	163	192	finally
    //   184	189	192	finally
    //   189	192	192	finally
    //   184	189	197	java/io/IOException
    //   57	87	205	finally
    //   57	87	214	java/io/IOException
  }
  
  public File getFileForKey(String paramString)
  {
    return new File(this.mRootDirectory, getFilenameForKey(paramString));
  }
  
  /* Error */
  public void initialize()
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_1
    //   2: aload_0
    //   3: monitorenter
    //   4: aload_0
    //   5: getfield 42	com/ultra/sdk/volley/toolbox/DiskBasedCache:mRootDirectory	Ljava/io/File;
    //   8: invokevirtual 237	java/io/File:exists	()Z
    //   11: ifne +40 -> 51
    //   14: aload_0
    //   15: getfield 42	com/ultra/sdk/volley/toolbox/DiskBasedCache:mRootDirectory	Ljava/io/File;
    //   18: invokevirtual 240	java/io/File:mkdirs	()Z
    //   21: ifne +27 -> 48
    //   24: iconst_1
    //   25: anewarray 4	java/lang/Object
    //   28: astore 16
    //   30: aload 16
    //   32: iconst_0
    //   33: aload_0
    //   34: getfield 42	com/ultra/sdk/volley/toolbox/DiskBasedCache:mRootDirectory	Ljava/io/File;
    //   37: invokevirtual 225	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   40: aastore
    //   41: ldc 242
    //   43: aload 16
    //   45: invokestatic 245	com/ultra/sdk/volley/VolleyLog:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   48: aload_0
    //   49: monitorexit
    //   50: return
    //   51: aload_0
    //   52: getfield 42	com/ultra/sdk/volley/toolbox/DiskBasedCache:mRootDirectory	Ljava/io/File;
    //   55: invokevirtual 189	java/io/File:listFiles	()[Ljava/io/File;
    //   58: astore_3
    //   59: aload_3
    //   60: ifnull -12 -> 48
    //   63: aload_3
    //   64: arraylength
    //   65: istore 4
    //   67: iload_1
    //   68: iload 4
    //   70: if_icmpge -22 -> 48
    //   73: aload_3
    //   74: iload_1
    //   75: aaload
    //   76: astore 5
    //   78: aconst_null
    //   79: astore 6
    //   81: new 198	java/io/FileInputStream
    //   84: dup
    //   85: aload 5
    //   87: invokespecial 200	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   90: astore 7
    //   92: aload 7
    //   94: invokestatic 207	com/ultra/sdk/volley/toolbox/DiskBasedCache$CacheHeader:readHeader	(Ljava/io/InputStream;)Lcom/ultra/sdk/volley/toolbox/DiskBasedCache$CacheHeader;
    //   97: astore 13
    //   99: aload 13
    //   101: aload 5
    //   103: invokevirtual 209	java/io/File:length	()J
    //   106: putfield 134	com/ultra/sdk/volley/toolbox/DiskBasedCache$CacheHeader:size	J
    //   109: aload_0
    //   110: aload 13
    //   112: getfield 122	com/ultra/sdk/volley/toolbox/DiskBasedCache$CacheHeader:key	Ljava/lang/String;
    //   115: aload 13
    //   117: invokespecial 247	com/ultra/sdk/volley/toolbox/DiskBasedCache:putEntry	(Ljava/lang/String;Lcom/ultra/sdk/volley/toolbox/DiskBasedCache$CacheHeader;)V
    //   120: aload 7
    //   122: ifnull +93 -> 215
    //   125: aload 7
    //   127: invokevirtual 248	java/io/FileInputStream:close	()V
    //   130: iinc 1 1
    //   133: goto -66 -> 67
    //   136: astore 15
    //   138: aload 5
    //   140: ifnull +9 -> 149
    //   143: aload 5
    //   145: invokevirtual 131	java/io/File:delete	()Z
    //   148: pop
    //   149: aload 6
    //   151: ifnull -21 -> 130
    //   154: aload 6
    //   156: invokevirtual 248	java/io/FileInputStream:close	()V
    //   159: goto -29 -> 130
    //   162: astore 9
    //   164: goto -34 -> 130
    //   167: astore 10
    //   169: aload 6
    //   171: ifnull +8 -> 179
    //   174: aload 6
    //   176: invokevirtual 248	java/io/FileInputStream:close	()V
    //   179: aload 10
    //   181: athrow
    //   182: astore_2
    //   183: aload_0
    //   184: monitorexit
    //   185: aload_2
    //   186: athrow
    //   187: astore 14
    //   189: goto -59 -> 130
    //   192: astore 11
    //   194: goto -15 -> 179
    //   197: astore 10
    //   199: aload 7
    //   201: astore 6
    //   203: goto -34 -> 169
    //   206: astore 8
    //   208: aload 7
    //   210: astore 6
    //   212: goto -74 -> 138
    //   215: goto -85 -> 130
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	218	0	this	DiskBasedCache
    //   1	130	1	i	int
    //   182	4	2	localObject1	Object
    //   58	16	3	arrayOfFile	File[]
    //   65	6	4	j	int
    //   76	68	5	localFile	File
    //   79	132	6	localObject2	Object
    //   90	119	7	localFileInputStream	java.io.FileInputStream
    //   206	1	8	localIOException1	IOException
    //   162	1	9	localIOException2	IOException
    //   167	13	10	localObject3	Object
    //   197	1	10	localObject4	Object
    //   192	1	11	localIOException3	IOException
    //   97	19	13	localCacheHeader	CacheHeader
    //   187	1	14	localIOException4	IOException
    //   136	1	15	localIOException5	IOException
    //   28	16	16	arrayOfObject	Object[]
    // Exception table:
    //   from	to	target	type
    //   81	92	136	java/io/IOException
    //   154	159	162	java/io/IOException
    //   81	92	167	finally
    //   143	149	167	finally
    //   4	48	182	finally
    //   51	59	182	finally
    //   63	67	182	finally
    //   73	78	182	finally
    //   125	130	182	finally
    //   154	159	182	finally
    //   174	179	182	finally
    //   179	182	182	finally
    //   125	130	187	java/io/IOException
    //   174	179	192	java/io/IOException
    //   92	120	197	finally
    //   92	120	206	java/io/IOException
  }
  
  public void invalidate(String paramString, boolean paramBoolean)
  {
    try
    {
      Cache.Entry localEntry = get(paramString);
      if (localEntry != null)
      {
        localEntry.softTtl = 0L;
        if (paramBoolean) {
          localEntry.ttl = 0L;
        }
        put(paramString, localEntry);
      }
      return;
    }
    finally {}
  }
  
  /* Error */
  public void put(String paramString, Cache.Entry paramEntry)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_2
    //   4: getfield 267	com/ultra/sdk/volley/Cache$Entry:data	[B
    //   7: arraylength
    //   8: invokespecial 269	com/ultra/sdk/volley/toolbox/DiskBasedCache:pruneIfNeeded	(I)V
    //   11: aload_0
    //   12: aload_1
    //   13: invokevirtual 126	com/ultra/sdk/volley/toolbox/DiskBasedCache:getFileForKey	(Ljava/lang/String;)Ljava/io/File;
    //   16: astore 4
    //   18: new 271	java/io/FileOutputStream
    //   21: dup
    //   22: aload 4
    //   24: invokespecial 272	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   27: astore 5
    //   29: new 118	com/ultra/sdk/volley/toolbox/DiskBasedCache$CacheHeader
    //   32: dup
    //   33: aload_1
    //   34: aload_2
    //   35: invokespecial 274	com/ultra/sdk/volley/toolbox/DiskBasedCache$CacheHeader:<init>	(Ljava/lang/String;Lcom/ultra/sdk/volley/Cache$Entry;)V
    //   38: astore 6
    //   40: aload 6
    //   42: aload 5
    //   44: invokevirtual 278	com/ultra/sdk/volley/toolbox/DiskBasedCache$CacheHeader:writeHeader	(Ljava/io/OutputStream;)Z
    //   47: pop
    //   48: aload 5
    //   50: aload_2
    //   51: getfield 267	com/ultra/sdk/volley/Cache$Entry:data	[B
    //   54: invokevirtual 282	java/io/FileOutputStream:write	([B)V
    //   57: aload 5
    //   59: invokevirtual 283	java/io/FileOutputStream:close	()V
    //   62: aload_0
    //   63: aload_1
    //   64: aload 6
    //   66: invokespecial 247	com/ultra/sdk/volley/toolbox/DiskBasedCache:putEntry	(Ljava/lang/String;Lcom/ultra/sdk/volley/toolbox/DiskBasedCache$CacheHeader;)V
    //   69: aload_0
    //   70: monitorexit
    //   71: return
    //   72: astore 7
    //   74: aload 4
    //   76: invokevirtual 131	java/io/File:delete	()Z
    //   79: ifne -10 -> 69
    //   82: iconst_1
    //   83: anewarray 4	java/lang/Object
    //   86: astore 8
    //   88: aload 8
    //   90: iconst_0
    //   91: aload 4
    //   93: invokevirtual 225	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   96: aastore
    //   97: ldc_w 285
    //   100: aload 8
    //   102: invokestatic 147	com/ultra/sdk/volley/VolleyLog:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   105: goto -36 -> 69
    //   108: astore_3
    //   109: aload_0
    //   110: monitorexit
    //   111: aload_3
    //   112: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	113	0	this	DiskBasedCache
    //   0	113	1	paramString	String
    //   0	113	2	paramEntry	Cache.Entry
    //   108	4	3	localObject	Object
    //   16	76	4	localFile	File
    //   27	31	5	localFileOutputStream	java.io.FileOutputStream
    //   38	27	6	localCacheHeader	CacheHeader
    //   72	1	7	localIOException	IOException
    //   86	15	8	arrayOfObject	Object[]
    // Exception table:
    //   from	to	target	type
    //   18	69	72	java/io/IOException
    //   2	18	108	finally
    //   18	69	108	finally
    //   74	105	108	finally
  }
  
  public void remove(String paramString)
  {
    try
    {
      boolean bool = getFileForKey(paramString).delete();
      removeEntry(paramString);
      if (!bool)
      {
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = paramString;
        arrayOfObject[1] = getFilenameForKey(paramString);
        VolleyLog.d("Could not delete cache entry for key=%s, filename=%s", arrayOfObject);
      }
      return;
    }
    finally {}
  }
  
  private static class CacheHeader
  {
    public String etag;
    public String key;
    public Map<String, String> responseHeaders;
    public long serverDate;
    public long size;
    public long softTtl;
    public long ttl;
    
    private CacheHeader() {}
    
    public CacheHeader(String paramString, Cache.Entry paramEntry)
    {
      this.key = paramString;
      this.size = paramEntry.data.length;
      this.etag = paramEntry.etag;
      this.serverDate = paramEntry.serverDate;
      this.ttl = paramEntry.ttl;
      this.softTtl = paramEntry.softTtl;
      this.responseHeaders = paramEntry.responseHeaders;
    }
    
    public static CacheHeader readHeader(InputStream paramInputStream)
      throws IOException
    {
      CacheHeader localCacheHeader = new CacheHeader();
      ObjectInputStream localObjectInputStream = new ObjectInputStream(paramInputStream);
      if (localObjectInputStream.readByte() != 2) {
        throw new IOException();
      }
      localCacheHeader.key = localObjectInputStream.readUTF();
      localCacheHeader.etag = localObjectInputStream.readUTF();
      if (localCacheHeader.etag.equals("")) {
        localCacheHeader.etag = null;
      }
      localCacheHeader.serverDate = localObjectInputStream.readLong();
      localCacheHeader.ttl = localObjectInputStream.readLong();
      localCacheHeader.softTtl = localObjectInputStream.readLong();
      localCacheHeader.responseHeaders = readStringStringMap(localObjectInputStream);
      return localCacheHeader;
    }
    
    private static Map<String, String> readStringStringMap(ObjectInputStream paramObjectInputStream)
      throws IOException
    {
      int i = paramObjectInputStream.readInt();
      Object localObject;
      if (i == 0) {
        localObject = Collections.emptyMap();
      }
      for (int j = 0;; j++)
      {
        if (j >= i)
        {
          return localObject;
          localObject = new HashMap(i);
          break;
        }
        ((Map)localObject).put(paramObjectInputStream.readUTF().intern(), paramObjectInputStream.readUTF().intern());
      }
    }
    
    private static void writeStringStringMap(Map<String, String> paramMap, ObjectOutputStream paramObjectOutputStream)
      throws IOException
    {
      if (paramMap != null)
      {
        paramObjectOutputStream.writeInt(paramMap.size());
        Iterator localIterator = paramMap.entrySet().iterator();
        for (;;)
        {
          if (!localIterator.hasNext()) {
            return;
          }
          Map.Entry localEntry = (Map.Entry)localIterator.next();
          paramObjectOutputStream.writeUTF((String)localEntry.getKey());
          paramObjectOutputStream.writeUTF((String)localEntry.getValue());
        }
      }
      paramObjectOutputStream.writeInt(0);
    }
    
    public Cache.Entry toCacheEntry(byte[] paramArrayOfByte)
    {
      Cache.Entry localEntry = new Cache.Entry();
      localEntry.data = paramArrayOfByte;
      localEntry.etag = this.etag;
      localEntry.serverDate = this.serverDate;
      localEntry.ttl = this.ttl;
      localEntry.softTtl = this.softTtl;
      localEntry.responseHeaders = this.responseHeaders;
      return localEntry;
    }
    
    public boolean writeHeader(OutputStream paramOutputStream)
    {
      try
      {
        ObjectOutputStream localObjectOutputStream = new ObjectOutputStream(paramOutputStream);
        localObjectOutputStream.writeByte(2);
        localObjectOutputStream.writeUTF(this.key);
        if (this.etag == null) {}
        for (String str = "";; str = this.etag)
        {
          localObjectOutputStream.writeUTF(str);
          localObjectOutputStream.writeLong(this.serverDate);
          localObjectOutputStream.writeLong(this.ttl);
          localObjectOutputStream.writeLong(this.softTtl);
          writeStringStringMap(this.responseHeaders, localObjectOutputStream);
          localObjectOutputStream.flush();
          return true;
        }
        Object[] arrayOfObject;
        return false;
      }
      catch (IOException localIOException)
      {
        arrayOfObject = new Object[1];
        arrayOfObject[0] = localIOException.toString();
        VolleyLog.d("%s", arrayOfObject);
      }
    }
  }
  
  private static class CountingInputStream
    extends FilterInputStream
  {
    private int bytesRead = 0;
    
    private CountingInputStream(InputStream paramInputStream)
    {
      super();
    }
    
    public int read()
      throws IOException
    {
      int i = super.read();
      if (i != -1) {
        this.bytesRead = (1 + this.bytesRead);
      }
      return i;
    }
    
    public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws IOException
    {
      int i = super.read(paramArrayOfByte, paramInt1, paramInt2);
      if (i != -1) {
        this.bytesRead = (i + this.bytesRead);
      }
      return i;
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ultra.sdk.volley.toolbox.DiskBasedCache
 * JD-Core Version:    0.7.0.1
 */