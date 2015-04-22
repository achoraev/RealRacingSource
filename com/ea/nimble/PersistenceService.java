package com.ea.nimble;

import android.app.backup.BackupAgent;
import android.app.backup.BackupDataInput;
import android.app.backup.BackupDataOutput;
import android.content.Context;
import android.os.ParcelFileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class PersistenceService
{
  private static final String APPLICATION_PERSISTENCE_ID = "[APPLICATION]";
  public static final String COMPONENT_ID = "com.ea.nimble.persistence";
  private static final String NIMBLE_COMPONENT_PERSISTENCE_ID_TEMPLATE = "[COMPONENT]%s";
  
  public static void cleanReferenceToPersistence(String paramString, Persistence.Storage paramStorage)
  {
    if (!Utility.validString(paramString))
    {
      Log.Helper.LOGF("Persistence", "Invalid componentId " + paramString + " for component persistence", new Object[0]);
      return;
    }
    String str = String.format("[COMPONENT]%s", new Object[] { paramString });
    getComponent().cleanPersistenceReference(str, paramStorage);
  }
  
  public static Persistence getAppPersistence(Persistence.Storage paramStorage)
  {
    return getComponent().getPersistence("[APPLICATION]", paramStorage);
  }
  
  public static IPersistenceService getComponent()
  {
    return BaseCore.getInstance().getPersistenceService();
  }
  
  public static Persistence getPersistenceForNimbleComponent(String paramString, Persistence.Storage paramStorage)
  {
    if (!Utility.validString(paramString))
    {
      Log.Helper.LOGF("Persistence", "Invalid componentId " + paramString + " for component persistence", new Object[0]);
      return null;
    }
    String str = String.format("[COMPONENT]%s", new Object[] { paramString });
    return getComponent().getPersistence(str, paramStorage);
  }
  
  static void readBackup(BackupDataInput paramBackupDataInput, int paramInt, ParcelFileDescriptor paramParcelFileDescriptor, Context paramContext)
    throws IOException
  {
    label0:
    byte[] arrayOfByte;
    String str2;
    if (paramBackupDataInput.readNextHeader())
    {
      String str1 = paramBackupDataInput.getKey();
      int i = paramBackupDataInput.getDataSize();
      arrayOfByte = new byte[i];
      paramBackupDataInput.readEntityData(arrayOfByte, 0, i);
      str2 = Persistence.getPersistencePath(str1, Persistence.Storage.DOCUMENT, paramContext);
      localObject1 = null;
    }
    try
    {
      localFileOutputStream = new FileOutputStream(str2);
    }
    finally
    {
      try
      {
        localFileOutputStream.write(arrayOfByte);
        if (localFileOutputStream == null) {
          break label0;
        }
        localFileOutputStream.close();
        break label0;
      }
      finally
      {
        for (;;)
        {
          FileOutputStream localFileOutputStream;
          Iterator localIterator;
          localObject1 = localFileOutputStream;
        }
      }
      localObject2 = finally;
      if (localObject1 != null) {
        localObject1.close();
      }
      throw localObject2;
      if (!ApplicationEnvironment.isMainApplicationRunning()) {
        break label162;
      }
      localIterator = ((PersistenceServiceImpl)getComponent()).m_persistences.values().iterator();
    }
    while (localIterator.hasNext())
    {
      Persistence localPersistence = (Persistence)localIterator.next();
      if (localPersistence.getBackUp()) {
        localPersistence.restore(false, null);
      }
    }
    label162:
  }
  
  public static void removePersistenceForNimbleComponent(String paramString, Persistence.Storage paramStorage)
  {
    if (!Utility.validString(paramString))
    {
      Log.Helper.LOGF("Persistence", "Invalid componentId " + paramString + " for component persistence", new Object[0]);
      return;
    }
    String str = String.format("[COMPONENT]%s", new Object[] { paramString });
    getComponent().removePersistence(str, paramStorage);
  }
  
  /* Error */
  static void writeBackup(ParcelFileDescriptor paramParcelFileDescriptor1, BackupDataOutput paramBackupDataOutput, ParcelFileDescriptor paramParcelFileDescriptor2, Context paramContext)
    throws IOException
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: lconst_0
    //   4: lstore 5
    //   6: iconst_0
    //   7: istore 7
    //   9: aload_0
    //   10: ifnull +55 -> 65
    //   13: new 172	java/io/FileInputStream
    //   16: dup
    //   17: aload_0
    //   18: invokevirtual 178	android/os/ParcelFileDescriptor:getFileDescriptor	()Ljava/io/FileDescriptor;
    //   21: invokespecial 181	java/io/FileInputStream:<init>	(Ljava/io/FileDescriptor;)V
    //   24: astore 38
    //   26: new 183	java/io/DataInputStream
    //   29: dup
    //   30: aload 38
    //   32: invokespecial 186	java/io/DataInputStream:<init>	(Ljava/io/InputStream;)V
    //   35: astore 39
    //   37: aload 39
    //   39: invokevirtual 189	java/io/DataInputStream:readInt	()I
    //   42: istore 7
    //   44: aload 39
    //   46: invokevirtual 193	java/io/DataInputStream:readLong	()J
    //   49: lstore 42
    //   51: lload 42
    //   53: lstore 5
    //   55: aload 39
    //   57: ifnull +499 -> 556
    //   60: aload 39
    //   62: invokevirtual 194	java/io/DataInputStream:close	()V
    //   65: getstatic 107	com/ea/nimble/Persistence$Storage:DOCUMENT	Lcom/ea/nimble/Persistence$Storage;
    //   68: aload_3
    //   69: invokestatic 198	com/ea/nimble/Persistence:getPersistenceDirectory	(Lcom/ea/nimble/Persistence$Storage;Landroid/content/Context;)Ljava/io/File;
    //   72: astore 8
    //   74: new 200	java/util/ArrayList
    //   77: dup
    //   78: aload 8
    //   80: invokevirtual 206	java/io/File:listFiles	()[Ljava/io/File;
    //   83: invokestatic 212	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
    //   86: invokespecial 215	java/util/ArrayList:<init>	(Ljava/util/Collection;)V
    //   89: astore 9
    //   91: new 200	java/util/ArrayList
    //   94: dup
    //   95: invokespecial 216	java/util/ArrayList:<init>	()V
    //   98: astore 10
    //   100: iconst_0
    //   101: istore 11
    //   103: lconst_0
    //   104: lstore 12
    //   106: aload 9
    //   108: invokeinterface 219 1 0
    //   113: astore 14
    //   115: aload 14
    //   117: invokeinterface 153 1 0
    //   122: ifeq +167 -> 289
    //   125: aload 14
    //   127: invokeinterface 157 1 0
    //   132: checkcast 202	java/io/File
    //   135: astore 30
    //   137: aload 30
    //   139: invokevirtual 222	java/io/File:getName	()Ljava/lang/String;
    //   142: astore 31
    //   144: aload 31
    //   146: ldc 224
    //   148: invokevirtual 228	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
    //   151: istore 32
    //   153: iload 32
    //   155: ifge +111 -> 266
    //   158: aload 31
    //   160: astore 33
    //   162: new 109	com/ea/nimble/Persistence
    //   165: dup
    //   166: aload 33
    //   168: getstatic 107	com/ea/nimble/Persistence$Storage:DOCUMENT	Lcom/ea/nimble/Persistence$Storage;
    //   171: aconst_null
    //   172: invokespecial 231	com/ea/nimble/Persistence:<init>	(Ljava/lang/String;Lcom/ea/nimble/Persistence$Storage;Lcom/ea/nimble/Encryptor;)V
    //   175: astore 34
    //   177: aload 34
    //   179: iconst_1
    //   180: aload_3
    //   181: invokevirtual 164	com/ea/nimble/Persistence:restore	(ZLandroid/content/Context;)V
    //   184: aload 34
    //   186: invokevirtual 160	com/ea/nimble/Persistence:getBackUp	()Z
    //   189: ifeq +90 -> 279
    //   192: iinc 11 1
    //   195: aload 30
    //   197: invokevirtual 234	java/io/File:lastModified	()J
    //   200: lstore 35
    //   202: lload 35
    //   204: lload 12
    //   206: lcmp
    //   207: ifle +7 -> 214
    //   210: lload 35
    //   212: lstore 12
    //   214: aload 10
    //   216: aload 34
    //   218: invokeinterface 238 2 0
    //   223: pop
    //   224: goto -109 -> 115
    //   227: astore 45
    //   229: lconst_0
    //   230: lstore 5
    //   232: iconst_0
    //   233: istore 7
    //   235: aload 4
    //   237: ifnull -172 -> 65
    //   240: aload 4
    //   242: invokevirtual 194	java/io/DataInputStream:close	()V
    //   245: iconst_0
    //   246: istore 7
    //   248: goto -183 -> 65
    //   251: astore 41
    //   253: aload 4
    //   255: ifnull +8 -> 263
    //   258: aload 4
    //   260: invokevirtual 194	java/io/DataInputStream:close	()V
    //   263: aload 41
    //   265: athrow
    //   266: aload 31
    //   268: iconst_0
    //   269: iload 32
    //   271: invokevirtual 242	java/lang/String:substring	(II)Ljava/lang/String;
    //   274: astore 33
    //   276: goto -114 -> 162
    //   279: aload 14
    //   281: invokeinterface 245 1 0
    //   286: goto -171 -> 115
    //   289: iload 11
    //   291: iload 7
    //   293: if_icmpne +11 -> 304
    //   296: lload 5
    //   298: lload 12
    //   300: lcmp
    //   301: ifeq +136 -> 437
    //   304: iconst_0
    //   305: istore 15
    //   307: aload 9
    //   309: invokeinterface 248 1 0
    //   314: istore 16
    //   316: iload 15
    //   318: iload 16
    //   320: if_icmpge +117 -> 437
    //   323: aload 10
    //   325: iload 15
    //   327: invokeinterface 252 2 0
    //   332: checkcast 109	com/ea/nimble/Persistence
    //   335: astore 21
    //   337: aload 9
    //   339: iload 15
    //   341: invokeinterface 252 2 0
    //   346: checkcast 202	java/io/File
    //   349: astore 22
    //   351: aload 22
    //   353: invokevirtual 255	java/io/File:length	()J
    //   356: l2i
    //   357: newarray byte
    //   359: astore 23
    //   361: aconst_null
    //   362: astore 24
    //   364: new 172	java/io/FileInputStream
    //   367: dup
    //   368: aload 22
    //   370: invokespecial 258	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   373: astore 25
    //   375: aload 25
    //   377: aload 23
    //   379: invokevirtual 262	java/io/FileInputStream:read	([B)I
    //   382: pop
    //   383: aload 25
    //   385: ifnull +8 -> 393
    //   388: aload 25
    //   390: invokevirtual 263	java/io/FileInputStream:close	()V
    //   393: aload_1
    //   394: aload 21
    //   396: invokevirtual 266	com/ea/nimble/Persistence:getIdentifier	()Ljava/lang/String;
    //   399: aload 23
    //   401: arraylength
    //   402: invokevirtual 272	android/app/backup/BackupDataOutput:writeEntityHeader	(Ljava/lang/String;I)I
    //   405: pop
    //   406: aload_1
    //   407: aload 23
    //   409: aload 23
    //   411: arraylength
    //   412: invokevirtual 276	android/app/backup/BackupDataOutput:writeEntityData	([BI)I
    //   415: pop
    //   416: iinc 15 1
    //   419: goto -112 -> 307
    //   422: astore 26
    //   424: aload 24
    //   426: ifnull +8 -> 434
    //   429: aload 24
    //   431: invokevirtual 263	java/io/FileInputStream:close	()V
    //   434: aload 26
    //   436: athrow
    //   437: new 115	java/io/FileOutputStream
    //   440: dup
    //   441: aload_2
    //   442: invokevirtual 178	android/os/ParcelFileDescriptor:getFileDescriptor	()Ljava/io/FileDescriptor;
    //   445: invokespecial 277	java/io/FileOutputStream:<init>	(Ljava/io/FileDescriptor;)V
    //   448: astore 17
    //   450: aconst_null
    //   451: astore 18
    //   453: new 279	java/io/DataOutputStream
    //   456: dup
    //   457: aload 17
    //   459: invokespecial 282	java/io/DataOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   462: astore 19
    //   464: aload 19
    //   466: iload 11
    //   468: invokevirtual 286	java/io/DataOutputStream:writeInt	(I)V
    //   471: aload 19
    //   473: lload 12
    //   475: invokevirtual 290	java/io/DataOutputStream:writeLong	(J)V
    //   478: aload 19
    //   480: ifnull +8 -> 488
    //   483: aload 19
    //   485: invokevirtual 291	java/io/DataOutputStream:close	()V
    //   488: return
    //   489: astore 20
    //   491: aload 18
    //   493: ifnull +8 -> 501
    //   496: aload 18
    //   498: invokevirtual 291	java/io/DataOutputStream:close	()V
    //   501: aload 20
    //   503: athrow
    //   504: astore 20
    //   506: aload 19
    //   508: astore 18
    //   510: goto -19 -> 491
    //   513: astore 26
    //   515: aload 25
    //   517: astore 24
    //   519: goto -95 -> 424
    //   522: astore 41
    //   524: aconst_null
    //   525: astore 4
    //   527: goto -274 -> 253
    //   530: astore 41
    //   532: aload 39
    //   534: astore 4
    //   536: goto -283 -> 253
    //   539: astore 44
    //   541: aconst_null
    //   542: astore 4
    //   544: goto -315 -> 229
    //   547: astore 40
    //   549: aload 39
    //   551: astore 4
    //   553: goto -324 -> 229
    //   556: goto -491 -> 65
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	559	0	paramParcelFileDescriptor1	ParcelFileDescriptor
    //   0	559	1	paramBackupDataOutput	BackupDataOutput
    //   0	559	2	paramParcelFileDescriptor2	ParcelFileDescriptor
    //   0	559	3	paramContext	Context
    //   1	551	4	localObject1	Object
    //   4	293	5	l1	long
    //   7	287	7	i	int
    //   72	7	8	localFile1	java.io.File
    //   89	249	9	localArrayList1	java.util.ArrayList
    //   98	226	10	localArrayList2	java.util.ArrayList
    //   101	366	11	j	int
    //   104	370	12	l2	long
    //   113	167	14	localIterator	Iterator
    //   305	112	15	k	int
    //   314	7	16	m	int
    //   448	10	17	localFileOutputStream	FileOutputStream
    //   451	58	18	localObject2	Object
    //   462	45	19	localDataOutputStream	java.io.DataOutputStream
    //   489	13	20	localObject3	Object
    //   504	1	20	localObject4	Object
    //   335	60	21	localPersistence1	Persistence
    //   349	20	22	localFile2	java.io.File
    //   359	51	23	arrayOfByte	byte[]
    //   362	156	24	localObject5	Object
    //   373	143	25	localFileInputStream1	java.io.FileInputStream
    //   422	13	26	localObject6	Object
    //   513	1	26	localObject7	Object
    //   135	61	30	localFile3	java.io.File
    //   142	125	31	str1	String
    //   151	119	32	n	int
    //   160	115	33	str2	String
    //   175	42	34	localPersistence2	Persistence
    //   200	11	35	l3	long
    //   24	7	38	localFileInputStream2	java.io.FileInputStream
    //   35	515	39	localDataInputStream	java.io.DataInputStream
    //   547	1	40	localIOException1	IOException
    //   251	13	41	localObject8	Object
    //   522	1	41	localObject9	Object
    //   530	1	41	localObject10	Object
    //   49	3	42	l4	long
    //   539	1	44	localIOException2	IOException
    //   227	1	45	localIOException3	IOException
    // Exception table:
    //   from	to	target	type
    //   13	26	227	java/io/IOException
    //   13	26	251	finally
    //   364	375	422	finally
    //   453	464	489	finally
    //   464	478	504	finally
    //   375	383	513	finally
    //   26	37	522	finally
    //   37	51	530	finally
    //   26	37	539	java/io/IOException
    //   37	51	547	java/io/IOException
  }
  
  public static class PersistenceBackupAgent
    extends BackupAgent
  {
    public void onBackup(ParcelFileDescriptor paramParcelFileDescriptor1, BackupDataOutput paramBackupDataOutput, ParcelFileDescriptor paramParcelFileDescriptor2)
      throws IOException
    {
      synchronized (Persistence.s_dataLock)
      {
        PersistenceService.writeBackup(paramParcelFileDescriptor1, paramBackupDataOutput, paramParcelFileDescriptor2, this);
        return;
      }
    }
    
    public void onRestore(BackupDataInput paramBackupDataInput, int paramInt, ParcelFileDescriptor paramParcelFileDescriptor)
      throws IOException
    {
      synchronized (Persistence.s_dataLock)
      {
        PersistenceService.readBackup(paramBackupDataInput, paramInt, paramParcelFileDescriptor, this);
        return;
      }
    }
  }
  
  public static enum PersistenceMergePolicy
  {
    static
    {
      PersistenceMergePolicy[] arrayOfPersistenceMergePolicy = new PersistenceMergePolicy[3];
      arrayOfPersistenceMergePolicy[0] = OVERWRITE;
      arrayOfPersistenceMergePolicy[1] = SOURCE_FIRST;
      arrayOfPersistenceMergePolicy[2] = TARGET_FIRST;
      $VALUES = arrayOfPersistenceMergePolicy;
    }
    
    private PersistenceMergePolicy() {}
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ea.nimble.PersistenceService
 * JD-Core Version:    0.7.0.1
 */