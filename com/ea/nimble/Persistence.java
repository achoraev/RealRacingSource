package com.ea.nimble;

import android.app.backup.BackupManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Persistence
  implements LogSource
{
  private static int PERSISTENCE_VERSION = 101;
  static final Object s_dataLock = new Object();
  private boolean m_backUp;
  private boolean m_changed;
  private Map<String, byte[]> m_content;
  private boolean m_encryption;
  private Encryptor m_encryptor;
  private String m_identifier;
  private Storage m_storage;
  private Timer m_synchronizeTimer = new Timer(new Runnable()
  {
    public void run()
    {
      Persistence.this.synchronize();
    }
  });
  
  Persistence(Persistence paramPersistence, String paramString)
  {
    this.m_content = new HashMap(paramPersistence.m_content);
    this.m_identifier = paramString;
    this.m_storage = paramPersistence.m_storage;
    this.m_encryptor = paramPersistence.m_encryptor;
    this.m_encryption = paramPersistence.m_encryption;
    this.m_backUp = paramPersistence.m_backUp;
    flagChange();
  }
  
  Persistence(String paramString, Storage paramStorage, Encryptor paramEncryptor)
  {
    this.m_content = new HashMap();
    this.m_identifier = paramString;
    this.m_storage = paramStorage;
    this.m_encryptor = paramEncryptor;
    this.m_encryption = false;
    this.m_backUp = false;
    this.m_changed = false;
  }
  
  private void clearSynchronizeTimer()
  {
    synchronized (s_dataLock)
    {
      this.m_synchronizeTimer.cancel();
      return;
    }
  }
  
  private void flagChange()
  {
    this.m_changed = true;
    synchronized (s_dataLock)
    {
      clearSynchronizeTimer();
      this.m_synchronizeTimer.schedule(0.5D, false);
      return;
    }
  }
  
  static File getPersistenceDirectory(Storage paramStorage)
  {
    IApplicationEnvironment localIApplicationEnvironment = ApplicationEnvironment.getComponent();
    File localFile;
    String str;
    switch (2.$SwitchMap$com$ea$nimble$Persistence$Storage[paramStorage.ordinal()])
    {
    default: 
      Log.Helper.LOGES("Persistence", "Unknown storage type", new Object[0]);
      localFile = null;
      return localFile;
    case 1: 
      str = localIApplicationEnvironment.getDocumentPath();
    }
    for (;;)
    {
      localFile = new File(str + File.separator + "persistence");
      if ((localFile.isDirectory()) || (localFile.mkdirs())) {
        break;
      }
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = paramStorage;
      arrayOfObject[1] = localFile.toString();
      Log.Helper.LOGE("Persistence", "Cannot create persistence folder in storage(%s) %s", arrayOfObject);
      return null;
      str = localIApplicationEnvironment.getCachePath();
      continue;
      str = localIApplicationEnvironment.getTempPath();
    }
  }
  
  static File getPersistenceDirectory(Storage paramStorage, Context paramContext)
  {
    File localFile;
    switch (2.$SwitchMap$com$ea$nimble$Persistence$Storage[paramStorage.ordinal()])
    {
    default: 
      Log.e("Nimble", "Persistence : Unknown storage type");
      localFile = null;
      return localFile;
    }
    for (String str1 = paramContext.getFilesDir().getPath();; str1 = paramContext.getCacheDir().getPath())
    {
      PackageManager localPackageManager = paramContext.getPackageManager();
      try
      {
        ApplicationInfo localApplicationInfo2 = localPackageManager.getApplicationInfo(paramContext.getPackageName(), 128);
        localApplicationInfo1 = localApplicationInfo2;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        for (;;)
        {
          String str2;
          String str3;
          Object[] arrayOfObject;
          ApplicationInfo localApplicationInfo1 = null;
        }
      }
      str2 = localApplicationInfo1.metaData.getString("com.ea.nimble.configuration");
      str3 = str1 + File.separator + "Nimble" + File.separator + str2 + File.separator + "persistence";
      if (paramStorage == Storage.TEMP) {
        str3 = str3 + File.separator + "temp";
      }
      localFile = new File(str3);
      if ((localFile.isDirectory()) || (localFile.mkdirs())) {
        break;
      }
      arrayOfObject = new Object[2];
      arrayOfObject[0] = paramStorage;
      arrayOfObject[1] = str3.toString();
      Log.e("Nimble", String.format("Persistence : Cannot create persistence folder in storage(%s) %s", arrayOfObject));
      return null;
    }
  }
  
  static String getPersistencePath(String paramString, Storage paramStorage)
  {
    File localFile = getPersistenceDirectory(paramStorage);
    if (localFile == null) {
      return null;
    }
    return localFile + File.separator + paramString + ".dat";
  }
  
  static String getPersistencePath(String paramString, Storage paramStorage, Context paramContext)
  {
    File localFile = getPersistenceDirectory(paramStorage, paramContext);
    if (localFile == null) {
      return null;
    }
    return localFile + File.separator + paramString + ".dat";
  }
  
  /* Error */
  private void loadPersistenceData(boolean paramBoolean, Context paramContext)
  {
    // Byte code:
    //   0: aload_2
    //   1: ifnonnull +20 -> 21
    //   4: aload_0
    //   5: getfield 56	com/ea/nimble/Persistence:m_identifier	Ljava/lang/String;
    //   8: aload_0
    //   9: getfield 58	com/ea/nimble/Persistence:m_storage	Lcom/ea/nimble/Persistence$Storage;
    //   12: invokestatic 245	com/ea/nimble/Persistence:getPersistencePath	(Ljava/lang/String;Lcom/ea/nimble/Persistence$Storage;)Ljava/lang/String;
    //   15: astore_3
    //   16: aload_3
    //   17: ifnonnull +20 -> 37
    //   20: return
    //   21: aload_0
    //   22: getfield 56	com/ea/nimble/Persistence:m_identifier	Ljava/lang/String;
    //   25: aload_0
    //   26: getfield 58	com/ea/nimble/Persistence:m_storage	Lcom/ea/nimble/Persistence$Storage;
    //   29: aload_2
    //   30: invokestatic 247	com/ea/nimble/Persistence:getPersistencePath	(Ljava/lang/String;Lcom/ea/nimble/Persistence$Storage;Landroid/content/Context;)Ljava/lang/String;
    //   33: astore_3
    //   34: goto -18 -> 16
    //   37: new 121	java/io/File
    //   40: dup
    //   41: aload_3
    //   42: invokespecial 139	java/io/File:<init>	(Ljava/lang/String;)V
    //   45: astore 4
    //   47: aload 4
    //   49: invokevirtual 250	java/io/File:exists	()Z
    //   52: ifeq +13 -> 65
    //   55: aload 4
    //   57: invokevirtual 254	java/io/File:length	()J
    //   60: lconst_0
    //   61: lcmp
    //   62: ifne +38 -> 100
    //   65: iconst_2
    //   66: anewarray 4	java/lang/Object
    //   69: astore 5
    //   71: aload 5
    //   73: iconst_0
    //   74: aload_0
    //   75: getfield 56	com/ea/nimble/Persistence:m_identifier	Ljava/lang/String;
    //   78: aastore
    //   79: aload 5
    //   81: iconst_1
    //   82: aload_0
    //   83: getfield 58	com/ea/nimble/Persistence:m_storage	Lcom/ea/nimble/Persistence$Storage;
    //   86: invokevirtual 255	com/ea/nimble/Persistence$Storage:toString	()Ljava/lang/String;
    //   89: aastore
    //   90: aload_0
    //   91: ldc_w 257
    //   94: aload 5
    //   96: invokestatic 260	com/ea/nimble/Log$Helper:LOGD	(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;)V
    //   99: return
    //   100: iconst_1
    //   101: anewarray 4	java/lang/Object
    //   104: astore 6
    //   106: aload 6
    //   108: iconst_0
    //   109: aload 4
    //   111: invokevirtual 254	java/io/File:length	()J
    //   114: invokestatic 266	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   117: aastore
    //   118: aload_0
    //   119: ldc_w 268
    //   122: aload 6
    //   124: invokestatic 260	com/ea/nimble/Log$Helper:LOGD	(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;)V
    //   127: aconst_null
    //   128: astore 7
    //   130: new 270	java/io/FileInputStream
    //   133: dup
    //   134: aload 4
    //   136: invokespecial 273	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   139: astore 8
    //   141: new 275	java/io/ObjectInputStream
    //   144: dup
    //   145: aload 8
    //   147: invokespecial 278	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   150: astore 9
    //   152: aload 9
    //   154: invokevirtual 281	java/io/ObjectInputStream:readInt	()I
    //   157: getstatic 29	com/ea/nimble/Persistence:PERSISTENCE_VERSION	I
    //   160: if_icmpeq +79 -> 239
    //   163: new 283	java/io/InvalidClassException
    //   166: dup
    //   167: ldc_w 285
    //   170: ldc_w 287
    //   173: invokespecial 290	java/io/InvalidClassException:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   176: athrow
    //   177: astore 12
    //   179: aload 8
    //   181: astore 7
    //   183: iconst_3
    //   184: anewarray 4	java/lang/Object
    //   187: astore 13
    //   189: aload 13
    //   191: iconst_0
    //   192: aload_0
    //   193: getfield 56	com/ea/nimble/Persistence:m_identifier	Ljava/lang/String;
    //   196: aastore
    //   197: aload 13
    //   199: iconst_1
    //   200: aload_3
    //   201: aastore
    //   202: aload 13
    //   204: iconst_2
    //   205: aload 12
    //   207: invokevirtual 291	java/lang/Exception:toString	()Ljava/lang/String;
    //   210: aastore
    //   211: aload_0
    //   212: ldc_w 293
    //   215: aload 13
    //   217: invokestatic 153	com/ea/nimble/Log$Helper:LOGE	(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;)V
    //   220: aload 12
    //   222: invokevirtual 296	java/lang/Exception:printStackTrace	()V
    //   225: aload 7
    //   227: ifnull -207 -> 20
    //   230: aload 7
    //   232: invokevirtual 299	java/io/FileInputStream:close	()V
    //   235: return
    //   236: astore 14
    //   238: return
    //   239: aload_0
    //   240: aload 9
    //   242: invokevirtual 302	java/io/ObjectInputStream:readBoolean	()Z
    //   245: putfield 62	com/ea/nimble/Persistence:m_encryption	Z
    //   248: aload_0
    //   249: aload 9
    //   251: invokevirtual 302	java/io/ObjectInputStream:readBoolean	()Z
    //   254: putfield 64	com/ea/nimble/Persistence:m_backUp	Z
    //   257: iload_1
    //   258: ifne +83 -> 341
    //   261: new 304	java/io/BufferedInputStream
    //   264: dup
    //   265: aload 8
    //   267: invokespecial 305	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   270: astore 15
    //   272: aload_0
    //   273: getfield 62	com/ea/nimble/Persistence:m_encryption	Z
    //   276: ifeq +81 -> 357
    //   279: aload_0
    //   280: getfield 60	com/ea/nimble/Persistence:m_encryptor	Lcom/ea/nimble/Encryptor;
    //   283: aload 15
    //   285: invokevirtual 311	com/ea/nimble/Encryptor:encryptInputStream	(Ljava/io/InputStream;)Ljava/io/ObjectInputStream;
    //   288: astore 16
    //   290: aload_0
    //   291: aload 16
    //   293: invokevirtual 315	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   296: checkcast 317	java/util/Map
    //   299: putfield 51	com/ea/nimble/Persistence:m_content	Ljava/util/Map;
    //   302: iconst_2
    //   303: anewarray 4	java/lang/Object
    //   306: astore 17
    //   308: aload 17
    //   310: iconst_0
    //   311: aload_0
    //   312: getfield 56	com/ea/nimble/Persistence:m_identifier	Ljava/lang/String;
    //   315: aastore
    //   316: aload 17
    //   318: iconst_1
    //   319: aload_0
    //   320: getfield 58	com/ea/nimble/Persistence:m_storage	Lcom/ea/nimble/Persistence$Storage;
    //   323: invokevirtual 255	com/ea/nimble/Persistence$Storage:toString	()Ljava/lang/String;
    //   326: aastore
    //   327: aload_0
    //   328: ldc_w 319
    //   331: aload 17
    //   333: invokestatic 260	com/ea/nimble/Log$Helper:LOGD	(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;)V
    //   336: aload 16
    //   338: invokevirtual 320	java/io/ObjectInputStream:close	()V
    //   341: aload 9
    //   343: invokevirtual 320	java/io/ObjectInputStream:close	()V
    //   346: aload 8
    //   348: ifnull +8 -> 356
    //   351: aload 8
    //   353: invokevirtual 299	java/io/FileInputStream:close	()V
    //   356: return
    //   357: new 275	java/io/ObjectInputStream
    //   360: dup
    //   361: aload 15
    //   363: invokespecial 278	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   366: astore 16
    //   368: goto -78 -> 290
    //   371: astore 18
    //   373: return
    //   374: astore 10
    //   376: aload 7
    //   378: ifnull +8 -> 386
    //   381: aload 7
    //   383: invokevirtual 299	java/io/FileInputStream:close	()V
    //   386: aload 10
    //   388: athrow
    //   389: astore 11
    //   391: goto -5 -> 386
    //   394: astore 10
    //   396: aload 8
    //   398: astore 7
    //   400: goto -24 -> 376
    //   403: astore 12
    //   405: aconst_null
    //   406: astore 7
    //   408: goto -225 -> 183
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	411	0	this	Persistence
    //   0	411	1	paramBoolean	boolean
    //   0	411	2	paramContext	Context
    //   15	186	3	str	String
    //   45	90	4	localFile	File
    //   69	26	5	arrayOfObject1	Object[]
    //   104	19	6	arrayOfObject2	Object[]
    //   128	279	7	localObject1	Object
    //   139	258	8	localFileInputStream	java.io.FileInputStream
    //   150	192	9	localObjectInputStream1	java.io.ObjectInputStream
    //   374	13	10	localObject2	Object
    //   394	1	10	localObject3	Object
    //   389	1	11	localIOException1	IOException
    //   177	44	12	localException1	Exception
    //   403	1	12	localException2	Exception
    //   187	29	13	arrayOfObject3	Object[]
    //   236	1	14	localIOException2	IOException
    //   270	92	15	localBufferedInputStream	java.io.BufferedInputStream
    //   288	79	16	localObjectInputStream2	java.io.ObjectInputStream
    //   306	26	17	arrayOfObject4	Object[]
    //   371	1	18	localIOException3	IOException
    // Exception table:
    //   from	to	target	type
    //   141	177	177	java/lang/Exception
    //   239	257	177	java/lang/Exception
    //   261	290	177	java/lang/Exception
    //   290	341	177	java/lang/Exception
    //   341	346	177	java/lang/Exception
    //   357	368	177	java/lang/Exception
    //   230	235	236	java/io/IOException
    //   351	356	371	java/io/IOException
    //   130	141	374	finally
    //   183	225	374	finally
    //   381	386	389	java/io/IOException
    //   141	177	394	finally
    //   239	257	394	finally
    //   261	290	394	finally
    //   290	341	394	finally
    //   341	346	394	finally
    //   357	368	394	finally
    //   130	141	403	java/lang/Exception
  }
  
  private void putValue(String paramString, Serializable paramSerializable)
    throws IOException
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    ObjectOutputStream localObjectOutputStream = new ObjectOutputStream(localByteArrayOutputStream);
    localObjectOutputStream.writeObject(paramSerializable);
    localObjectOutputStream.close();
    byte[] arrayOfByte = localByteArrayOutputStream.toByteArray();
    if (!arrayOfByte.equals(this.m_content.get(paramString)))
    {
      this.m_content.put(paramString, arrayOfByte);
      flagChange();
    }
  }
  
  /* Error */
  private void savePersistenceData()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 56	com/ea/nimble/Persistence:m_identifier	Ljava/lang/String;
    //   4: aload_0
    //   5: getfield 58	com/ea/nimble/Persistence:m_storage	Lcom/ea/nimble/Persistence$Storage;
    //   8: invokestatic 245	com/ea/nimble/Persistence:getPersistencePath	(Ljava/lang/String;Lcom/ea/nimble/Persistence$Storage;)Ljava/lang/String;
    //   11: astore_1
    //   12: aload_1
    //   13: ifnonnull +4 -> 17
    //   16: return
    //   17: new 121	java/io/File
    //   20: dup
    //   21: aload_1
    //   22: invokespecial 139	java/io/File:<init>	(Ljava/lang/String;)V
    //   25: astore_2
    //   26: aconst_null
    //   27: astore_3
    //   28: new 354	java/io/FileOutputStream
    //   31: dup
    //   32: aload_2
    //   33: invokespecial 355	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   36: astore 4
    //   38: new 327	java/io/ObjectOutputStream
    //   41: dup
    //   42: aload 4
    //   44: invokespecial 330	java/io/ObjectOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   47: astore 5
    //   49: aload 5
    //   51: getstatic 29	com/ea/nimble/Persistence:PERSISTENCE_VERSION	I
    //   54: invokevirtual 359	java/io/ObjectOutputStream:writeInt	(I)V
    //   57: aload 5
    //   59: aload_0
    //   60: getfield 62	com/ea/nimble/Persistence:m_encryption	Z
    //   63: invokevirtual 363	java/io/ObjectOutputStream:writeBoolean	(Z)V
    //   66: aload 5
    //   68: aload_0
    //   69: getfield 64	com/ea/nimble/Persistence:m_backUp	Z
    //   72: invokevirtual 363	java/io/ObjectOutputStream:writeBoolean	(Z)V
    //   75: aload 5
    //   77: invokevirtual 366	java/io/ObjectOutputStream:flush	()V
    //   80: new 368	java/io/BufferedOutputStream
    //   83: dup
    //   84: aload 4
    //   86: invokespecial 369	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   89: astore 12
    //   91: aload_0
    //   92: getfield 62	com/ea/nimble/Persistence:m_encryption	Z
    //   95: ifeq +104 -> 199
    //   98: aload_0
    //   99: getfield 60	com/ea/nimble/Persistence:m_encryptor	Lcom/ea/nimble/Encryptor;
    //   102: aload 12
    //   104: invokevirtual 373	com/ea/nimble/Encryptor:encryptOutputStream	(Ljava/io/OutputStream;)Ljava/io/ObjectOutputStream;
    //   107: astore 13
    //   109: aload 13
    //   111: aload_0
    //   112: getfield 51	com/ea/nimble/Persistence:m_content	Ljava/util/Map;
    //   115: invokevirtual 334	java/io/ObjectOutputStream:writeObject	(Ljava/lang/Object;)V
    //   118: iconst_2
    //   119: anewarray 4	java/lang/Object
    //   122: astore 14
    //   124: aload 14
    //   126: iconst_0
    //   127: aload_0
    //   128: getfield 56	com/ea/nimble/Persistence:m_identifier	Ljava/lang/String;
    //   131: aastore
    //   132: aload 14
    //   134: iconst_1
    //   135: aload_0
    //   136: getfield 58	com/ea/nimble/Persistence:m_storage	Lcom/ea/nimble/Persistence$Storage;
    //   139: invokevirtual 255	com/ea/nimble/Persistence$Storage:toString	()Ljava/lang/String;
    //   142: aastore
    //   143: aload_0
    //   144: ldc_w 375
    //   147: aload 14
    //   149: invokestatic 260	com/ea/nimble/Log$Helper:LOGD	(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;)V
    //   152: aload 13
    //   154: invokevirtual 335	java/io/ObjectOutputStream:close	()V
    //   157: aload 5
    //   159: invokevirtual 335	java/io/ObjectOutputStream:close	()V
    //   162: aload 4
    //   164: ifnull +154 -> 318
    //   167: aload 4
    //   169: invokevirtual 376	java/io/FileOutputStream:close	()V
    //   172: iconst_1
    //   173: anewarray 4	java/lang/Object
    //   176: astore 11
    //   178: aload 11
    //   180: iconst_0
    //   181: aload_2
    //   182: invokevirtual 254	java/io/File:length	()J
    //   185: invokestatic 266	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   188: aastore
    //   189: aload_0
    //   190: ldc_w 378
    //   193: aload 11
    //   195: invokestatic 260	com/ea/nimble/Log$Helper:LOGD	(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;)V
    //   198: return
    //   199: new 327	java/io/ObjectOutputStream
    //   202: dup
    //   203: aload 12
    //   205: invokespecial 330	java/io/ObjectOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   208: astore 13
    //   210: goto -101 -> 109
    //   213: astore 15
    //   215: goto -43 -> 172
    //   218: astore 6
    //   220: iconst_3
    //   221: anewarray 4	java/lang/Object
    //   224: astore 9
    //   226: aload 9
    //   228: iconst_0
    //   229: aload_0
    //   230: getfield 56	com/ea/nimble/Persistence:m_identifier	Ljava/lang/String;
    //   233: aastore
    //   234: aload 9
    //   236: iconst_1
    //   237: aload_0
    //   238: getfield 58	com/ea/nimble/Persistence:m_storage	Lcom/ea/nimble/Persistence$Storage;
    //   241: invokevirtual 255	com/ea/nimble/Persistence$Storage:toString	()Ljava/lang/String;
    //   244: aastore
    //   245: aload 9
    //   247: iconst_2
    //   248: aload 6
    //   250: invokevirtual 291	java/lang/Exception:toString	()Ljava/lang/String;
    //   253: aastore
    //   254: aload_0
    //   255: ldc_w 380
    //   258: aload 9
    //   260: invokestatic 153	com/ea/nimble/Log$Helper:LOGE	(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;)V
    //   263: aload 6
    //   265: invokevirtual 296	java/lang/Exception:printStackTrace	()V
    //   268: aload_3
    //   269: ifnull -97 -> 172
    //   272: aload_3
    //   273: invokevirtual 376	java/io/FileOutputStream:close	()V
    //   276: goto -104 -> 172
    //   279: astore 10
    //   281: goto -109 -> 172
    //   284: astore 7
    //   286: aload_3
    //   287: ifnull +7 -> 294
    //   290: aload_3
    //   291: invokevirtual 376	java/io/FileOutputStream:close	()V
    //   294: aload 7
    //   296: athrow
    //   297: astore 8
    //   299: goto -5 -> 294
    //   302: astore 7
    //   304: aload 4
    //   306: astore_3
    //   307: goto -21 -> 286
    //   310: astore 6
    //   312: aload 4
    //   314: astore_3
    //   315: goto -95 -> 220
    //   318: goto -146 -> 172
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	321	0	this	Persistence
    //   11	11	1	str	String
    //   25	157	2	localFile	File
    //   27	288	3	localObject1	Object
    //   36	277	4	localFileOutputStream	java.io.FileOutputStream
    //   47	111	5	localObjectOutputStream1	ObjectOutputStream
    //   218	46	6	localException1	Exception
    //   310	1	6	localException2	Exception
    //   284	11	7	localObject2	Object
    //   302	1	7	localObject3	Object
    //   297	1	8	localIOException1	IOException
    //   224	35	9	arrayOfObject1	Object[]
    //   279	1	10	localIOException2	IOException
    //   176	18	11	arrayOfObject2	Object[]
    //   89	115	12	localBufferedOutputStream	java.io.BufferedOutputStream
    //   107	102	13	localObjectOutputStream2	ObjectOutputStream
    //   122	26	14	arrayOfObject3	Object[]
    //   213	1	15	localIOException3	IOException
    // Exception table:
    //   from	to	target	type
    //   167	172	213	java/io/IOException
    //   28	38	218	java/lang/Exception
    //   272	276	279	java/io/IOException
    //   28	38	284	finally
    //   220	268	284	finally
    //   290	294	297	java/io/IOException
    //   38	109	302	finally
    //   109	162	302	finally
    //   199	210	302	finally
    //   38	109	310	java/lang/Exception
    //   109	162	310	java/lang/Exception
    //   199	210	310	java/lang/Exception
  }
  
  public void addEntries(Object... paramVarArgs)
  {
    Object localObject1 = s_dataLock;
    Object localObject2 = null;
    int i = 0;
    try
    {
      if (i < paramVarArgs.length)
      {
        int j = i % 2;
        if (j == 0)
        {
          String str;
          try
          {
            str = (String)paramVarArgs[i];
            if (!Utility.validString(str)) {
              throw new RuntimeException("Invalid key");
            }
          }
          catch (Exception localException2)
          {
            Object[] arrayOfObject2 = new Object[1];
            arrayOfObject2[0] = Integer.valueOf(i);
            Log.Helper.LOGF(this, "Invalid key in NimblePersistence.addEntries at index %d, not a string", arrayOfObject2);
            return;
          }
          localObject2 = str;
        }
        for (;;)
        {
          i++;
          break;
          try
          {
            putValue(localObject2, (Serializable)paramVarArgs[i]);
          }
          catch (Exception localException1)
          {
            Object[] arrayOfObject1 = new Object[2];
            arrayOfObject1[0] = localObject2;
            arrayOfObject1[1] = Integer.valueOf(i);
            Log.Helper.LOGF(this, "Invalid value in NimblePersistence.addEntries for key %s at index %d", arrayOfObject1);
            return;
          }
        }
      }
    }
    finally {}
  }
  
  public void addEntriesFromMap(Map<String, Serializable> paramMap)
  {
    for (;;)
    {
      String str;
      synchronized (s_dataLock)
      {
        Iterator localIterator = paramMap.keySet().iterator();
        if (!localIterator.hasNext()) {
          break;
        }
        str = (String)localIterator.next();
        if (!Utility.validString(str)) {
          Log.Helper.LOGE(this, "Invalid key %s in NimblePersistence.addEntriesInDictionary, not a string, skip it", new Object[] { str });
        }
      }
      Serializable localSerializable = (Serializable)paramMap.get(str);
      if (localSerializable != null) {
        try
        {
          putValue(str, localSerializable);
        }
        catch (IOException localIOException) {}
      } else {
        Log.Helper.LOGE(this, "Invalid value in NimblePersistence.addEntries for key %s", new Object[] { str });
      }
    }
  }
  
  public void clean()
  {
    synchronized (s_dataLock)
    {
      File localFile = new File(getPersistencePath(this.m_identifier, this.m_storage));
      if ((localFile.exists()) && (!localFile.delete()))
      {
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = this.m_identifier;
        arrayOfObject[1] = this.m_storage.toString();
        Log.Helper.LOGE(this, "Fail to clean persistence file for id[%s] in storage %s", arrayOfObject);
      }
      return;
    }
  }
  
  public boolean getBackUp()
  {
    return this.m_backUp;
  }
  
  public boolean getEncryption()
  {
    return this.m_encryption;
  }
  
  public String getIdentifier()
  {
    return this.m_identifier;
  }
  
  public String getLogSourceTitle()
  {
    return "Persistence";
  }
  
  public Storage getStorage()
  {
    return this.m_storage;
  }
  
  public String getStringValue(String paramString)
  {
    Serializable localSerializable = getValue(paramString);
    try
    {
      String str = (String)localSerializable;
      return str;
    }
    catch (ClassCastException localClassCastException)
    {
      Log.Helper.LOGF(this, "Invalid value type for getStringValueCall, value is " + localSerializable.getClass().getName(), new Object[0]);
    }
    return null;
  }
  
  public Serializable getValue(String paramString)
  {
    byte[] arrayOfByte;
    Serializable localSerializable;
    synchronized (s_dataLock)
    {
      arrayOfByte = (byte[])this.m_content.get(paramString);
      if (arrayOfByte == null) {
        return null;
      }
    }
    return null;
  }
  
  void merge(Persistence paramPersistence, PersistenceService.PersistenceMergePolicy paramPersistenceMergePolicy)
  {
    switch (2.$SwitchMap$com$ea$nimble$PersistenceService$PersistenceMergePolicy[paramPersistenceMergePolicy.ordinal()])
    {
    }
    for (;;)
    {
      return;
      this.m_content = new HashMap(paramPersistence.m_content);
      return;
      this.m_content.putAll(paramPersistence.m_content);
      return;
      Iterator localIterator = paramPersistence.m_content.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        if (this.m_content.get(str) == null) {
          this.m_content.put(str, paramPersistence.m_content.get(str));
        }
      }
    }
  }
  
  void restore(boolean paramBoolean, Context paramContext)
  {
    synchronized (s_dataLock)
    {
      loadPersistenceData(paramBoolean, paramContext);
      return;
    }
  }
  
  public void setBackUp(boolean paramBoolean)
  {
    if (this.m_storage != Storage.DOCUMENT)
    {
      Log.Helper.LOGF(this, "Error: Backup flag not supported for storage: " + this.m_storage, new Object[0]);
      return;
    }
    this.m_backUp = paramBoolean;
  }
  
  public void setEncryption(boolean paramBoolean)
  {
    if (paramBoolean != this.m_encryption)
    {
      this.m_encryption = paramBoolean;
      flagChange();
    }
  }
  
  public void setValue(String paramString, Serializable paramSerializable)
  {
    synchronized (s_dataLock)
    {
      if (!Utility.validString(paramString))
      {
        Log.Helper.LOGF(this, "NimblePersistence cannot accept an invalid string " + paramString + " as key", new Object[0]);
        return;
      }
      if (paramSerializable == null)
      {
        if (this.m_content.get(paramString) != null)
        {
          this.m_content.remove(paramString);
          flagChange();
        }
        return;
      }
    }
    try
    {
      putValue(paramString, paramSerializable);
      return;
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        Log.Helper.LOGF(this, "NimblePersistence cannot archive value " + paramSerializable.toString(), new Object[0]);
      }
    }
  }
  
  public void synchronize()
  {
    synchronized (s_dataLock)
    {
      if (!this.m_changed)
      {
        Log.Helper.LOGD(this, "Not synchronizing to persistence since there is no change", new Object[0]);
        return;
      }
      clearSynchronizeTimer();
      savePersistenceData();
      if (this.m_backUp) {
        new BackupManager(ApplicationEnvironment.getComponent().getApplicationContext()).dataChanged();
      }
      return;
    }
  }
  
  public static enum Storage
  {
    static
    {
      CACHE = new Storage("CACHE", 1);
      TEMP = new Storage("TEMP", 2);
      Storage[] arrayOfStorage = new Storage[3];
      arrayOfStorage[0] = DOCUMENT;
      arrayOfStorage[1] = CACHE;
      arrayOfStorage[2] = TEMP;
      $VALUES = arrayOfStorage;
    }
    
    private Storage() {}
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ea.nimble.Persistence
 * JD-Core Version:    0.7.0.1
 */