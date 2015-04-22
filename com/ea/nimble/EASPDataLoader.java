package com.ea.nimble;

import android.content.Context;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

public class EASPDataLoader
{
  public static boolean deleteDatFile(String paramString)
  {
    File localFile = new File(paramString);
    if (!localFile.exists()) {
      return true;
    }
    return localFile.delete();
  }
  
  public static String getTrackingDatFilePath()
  {
    Context localContext = ApplicationEnvironment.getComponent().getApplicationContext();
    if (localContext == null) {}
    for (String str = System.getProperty("user.dir") + File.separator + "doc";; str = localContext.getFilesDir().getPath()) {
      return str + File.separator + "EASP" + File.separator + "Tracking" + File.separator + "tracking.dat";
    }
  }
  
  /* Error */
  public static EASPDataBuffer loadDatFile(String paramString)
    throws java.lang.Exception
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnull +10 -> 11
    //   4: aload_0
    //   5: invokevirtual 87	java/lang/String:length	()I
    //   8: ifne +22 -> 30
    //   11: ldc 89
    //   13: ldc 91
    //   15: iconst_0
    //   16: anewarray 4	java/lang/Object
    //   19: invokestatic 97	com/ea/nimble/Log$Helper:LOGDS	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
    //   22: new 99	java/lang/NullPointerException
    //   25: dup
    //   26: invokespecial 100	java/lang/NullPointerException:<init>	()V
    //   29: athrow
    //   30: new 12	java/io/File
    //   33: dup
    //   34: aload_0
    //   35: invokespecial 15	java/io/File:<init>	(Ljava/lang/String;)V
    //   38: astore_1
    //   39: aload_1
    //   40: invokevirtual 19	java/io/File:exists	()Z
    //   43: ifeq +12 -> 55
    //   46: aload_1
    //   47: invokevirtual 103	java/io/File:length	()J
    //   50: lconst_0
    //   51: lcmp
    //   52: ifne +70 -> 122
    //   55: ldc 89
    //   57: new 38	java/lang/StringBuilder
    //   60: dup
    //   61: invokespecial 39	java/lang/StringBuilder:<init>	()V
    //   64: ldc 105
    //   66: invokevirtual 51	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   69: aload_0
    //   70: invokevirtual 51	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   73: invokevirtual 60	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   76: iconst_0
    //   77: anewarray 4	java/lang/Object
    //   80: invokestatic 109	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   83: iconst_0
    //   84: anewarray 4	java/lang/Object
    //   87: invokestatic 97	com/ea/nimble/Log$Helper:LOGDS	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
    //   90: new 111	java/io/FileNotFoundException
    //   93: dup
    //   94: new 38	java/lang/StringBuilder
    //   97: dup
    //   98: invokespecial 39	java/lang/StringBuilder:<init>	()V
    //   101: ldc 113
    //   103: invokevirtual 51	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   106: aload_0
    //   107: invokevirtual 51	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   110: ldc 115
    //   112: invokevirtual 51	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   115: invokevirtual 60	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   118: invokespecial 116	java/io/FileNotFoundException:<init>	(Ljava/lang/String;)V
    //   121: athrow
    //   122: iconst_2
    //   123: anewarray 4	java/lang/Object
    //   126: astore_2
    //   127: aload_2
    //   128: iconst_0
    //   129: aload_0
    //   130: aastore
    //   131: aload_2
    //   132: iconst_1
    //   133: aload_1
    //   134: invokevirtual 103	java/io/File:length	()J
    //   137: invokestatic 122	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   140: aastore
    //   141: ldc 89
    //   143: ldc 124
    //   145: aload_2
    //   146: invokestatic 97	com/ea/nimble/Log$Helper:LOGDS	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
    //   149: aconst_null
    //   150: astore_3
    //   151: aconst_null
    //   152: astore 4
    //   154: new 126	java/io/FileInputStream
    //   157: dup
    //   158: aload_1
    //   159: invokespecial 129	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   162: astore 5
    //   164: new 131	java/io/BufferedInputStream
    //   167: dup
    //   168: aload 5
    //   170: invokespecial 134	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   173: astore 6
    //   175: aload_1
    //   176: invokevirtual 103	java/io/File:length	()J
    //   179: l2i
    //   180: newarray byte
    //   182: astore 12
    //   184: aload 6
    //   186: aload 12
    //   188: invokevirtual 140	java/io/InputStream:read	([B)I
    //   191: istore 13
    //   193: ldc 142
    //   195: invokestatic 148	javax/crypto/Cipher:getInstance	(Ljava/lang/String;)Ljavax/crypto/Cipher;
    //   198: astore 14
    //   200: new 150	javax/crypto/spec/SecretKeySpec
    //   203: dup
    //   204: bipush 32
    //   206: newarray byte
    //   208: dup
    //   209: iconst_0
    //   210: ldc 151
    //   212: bastore
    //   213: dup
    //   214: iconst_1
    //   215: ldc 152
    //   217: bastore
    //   218: dup
    //   219: iconst_2
    //   220: ldc 153
    //   222: bastore
    //   223: dup
    //   224: iconst_3
    //   225: ldc 154
    //   227: bastore
    //   228: dup
    //   229: iconst_4
    //   230: ldc 155
    //   232: bastore
    //   233: dup
    //   234: iconst_5
    //   235: ldc 156
    //   237: bastore
    //   238: dup
    //   239: bipush 6
    //   241: ldc 157
    //   243: bastore
    //   244: dup
    //   245: bipush 7
    //   247: ldc 158
    //   249: bastore
    //   250: dup
    //   251: bipush 8
    //   253: ldc 159
    //   255: bastore
    //   256: dup
    //   257: bipush 9
    //   259: ldc 160
    //   261: bastore
    //   262: dup
    //   263: bipush 10
    //   265: ldc 161
    //   267: bastore
    //   268: dup
    //   269: bipush 11
    //   271: ldc 162
    //   273: bastore
    //   274: dup
    //   275: bipush 12
    //   277: ldc 163
    //   279: bastore
    //   280: dup
    //   281: bipush 13
    //   283: ldc 164
    //   285: bastore
    //   286: dup
    //   287: bipush 14
    //   289: ldc 165
    //   291: bastore
    //   292: dup
    //   293: bipush 15
    //   295: ldc 151
    //   297: bastore
    //   298: dup
    //   299: bipush 16
    //   301: ldc 166
    //   303: bastore
    //   304: dup
    //   305: bipush 17
    //   307: ldc 167
    //   309: bastore
    //   310: dup
    //   311: bipush 18
    //   313: ldc 164
    //   315: bastore
    //   316: dup
    //   317: bipush 19
    //   319: ldc 168
    //   321: bastore
    //   322: dup
    //   323: bipush 20
    //   325: ldc 169
    //   327: bastore
    //   328: dup
    //   329: bipush 21
    //   331: ldc 170
    //   333: bastore
    //   334: dup
    //   335: bipush 22
    //   337: ldc 171
    //   339: bastore
    //   340: dup
    //   341: bipush 23
    //   343: ldc 172
    //   345: bastore
    //   346: dup
    //   347: bipush 24
    //   349: ldc 173
    //   351: bastore
    //   352: dup
    //   353: bipush 25
    //   355: ldc 174
    //   357: bastore
    //   358: dup
    //   359: bipush 26
    //   361: ldc 175
    //   363: bastore
    //   364: dup
    //   365: bipush 27
    //   367: ldc 176
    //   369: bastore
    //   370: dup
    //   371: bipush 28
    //   373: ldc 177
    //   375: bastore
    //   376: dup
    //   377: bipush 29
    //   379: ldc 178
    //   381: bastore
    //   382: dup
    //   383: bipush 30
    //   385: ldc 179
    //   387: bastore
    //   388: dup
    //   389: bipush 31
    //   391: ldc 180
    //   393: bastore
    //   394: ldc 182
    //   396: invokespecial 185	javax/crypto/spec/SecretKeySpec:<init>	([BLjava/lang/String;)V
    //   399: astore 15
    //   401: new 187	javax/crypto/spec/IvParameterSpec
    //   404: dup
    //   405: aload 12
    //   407: bipush 8
    //   409: bipush 16
    //   411: invokespecial 190	javax/crypto/spec/IvParameterSpec:<init>	([BII)V
    //   414: astore 16
    //   416: aload 14
    //   418: iconst_2
    //   419: aload 15
    //   421: aload 16
    //   423: invokevirtual 194	javax/crypto/Cipher:init	(ILjava/security/Key;Ljava/security/spec/AlgorithmParameterSpec;)V
    //   426: aload 14
    //   428: aload 12
    //   430: bipush 24
    //   432: iload 13
    //   434: bipush 24
    //   436: isub
    //   437: invokevirtual 198	javax/crypto/Cipher:doFinal	([BII)[B
    //   440: invokestatic 204	java/nio/ByteBuffer:wrap	([B)Ljava/nio/ByteBuffer;
    //   443: astore 17
    //   445: aload 17
    //   447: getstatic 210	java/nio/ByteOrder:LITTLE_ENDIAN	Ljava/nio/ByteOrder;
    //   450: invokevirtual 214	java/nio/ByteBuffer:order	(Ljava/nio/ByteOrder;)Ljava/nio/ByteBuffer;
    //   453: pop
    //   454: new 216	com/ea/nimble/EASPDataLoader$EASPDataBuffer
    //   457: dup
    //   458: aload 17
    //   460: invokestatic 220	com/ea/nimble/EASPDataLoader:readString	(Ljava/nio/ByteBuffer;)Ljava/lang/String;
    //   463: aload 17
    //   465: invokevirtual 224	java/nio/ByteBuffer:slice	()Ljava/nio/ByteBuffer;
    //   468: getstatic 210	java/nio/ByteOrder:LITTLE_ENDIAN	Ljava/nio/ByteOrder;
    //   471: invokevirtual 214	java/nio/ByteBuffer:order	(Ljava/nio/ByteOrder;)Ljava/nio/ByteBuffer;
    //   474: invokespecial 227	com/ea/nimble/EASPDataLoader$EASPDataBuffer:<init>	(Ljava/lang/String;Ljava/nio/ByteBuffer;)V
    //   477: astore 19
    //   479: aload 5
    //   481: ifnull +8 -> 489
    //   484: aload 5
    //   486: invokevirtual 230	java/io/FileInputStream:close	()V
    //   489: aload 6
    //   491: ifnull +8 -> 499
    //   494: aload 6
    //   496: invokevirtual 231	java/io/InputStream:close	()V
    //   499: aload 19
    //   501: areturn
    //   502: astore 21
    //   504: ldc 89
    //   506: ldc 233
    //   508: iconst_1
    //   509: anewarray 4	java/lang/Object
    //   512: dup
    //   513: iconst_0
    //   514: aload_0
    //   515: aastore
    //   516: invokestatic 236	com/ea/nimble/Log$Helper:LOGES	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
    //   519: goto -30 -> 489
    //   522: astore 20
    //   524: ldc 89
    //   526: ldc 238
    //   528: iconst_1
    //   529: anewarray 4	java/lang/Object
    //   532: dup
    //   533: iconst_0
    //   534: aload_0
    //   535: aastore
    //   536: invokestatic 236	com/ea/nimble/Log$Helper:LOGES	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
    //   539: aload 19
    //   541: areturn
    //   542: astore 7
    //   544: iconst_2
    //   545: anewarray 4	java/lang/Object
    //   548: astore 11
    //   550: aload 11
    //   552: iconst_0
    //   553: aload_0
    //   554: aastore
    //   555: aload 11
    //   557: iconst_1
    //   558: aload 7
    //   560: invokevirtual 239	java/lang/Exception:toString	()Ljava/lang/String;
    //   563: aastore
    //   564: ldc 89
    //   566: ldc 241
    //   568: aload 11
    //   570: invokestatic 236	com/ea/nimble/Log$Helper:LOGES	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
    //   573: aload 7
    //   575: invokevirtual 244	java/lang/Exception:printStackTrace	()V
    //   578: aload 7
    //   580: athrow
    //   581: astore 8
    //   583: aload_3
    //   584: ifnull +7 -> 591
    //   587: aload_3
    //   588: invokevirtual 230	java/io/FileInputStream:close	()V
    //   591: aload 4
    //   593: ifnull +8 -> 601
    //   596: aload 4
    //   598: invokevirtual 231	java/io/InputStream:close	()V
    //   601: aload 8
    //   603: athrow
    //   604: astore 10
    //   606: ldc 89
    //   608: ldc 233
    //   610: iconst_1
    //   611: anewarray 4	java/lang/Object
    //   614: dup
    //   615: iconst_0
    //   616: aload_0
    //   617: aastore
    //   618: invokestatic 236	com/ea/nimble/Log$Helper:LOGES	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
    //   621: goto -30 -> 591
    //   624: astore 9
    //   626: ldc 89
    //   628: ldc 238
    //   630: iconst_1
    //   631: anewarray 4	java/lang/Object
    //   634: dup
    //   635: iconst_0
    //   636: aload_0
    //   637: aastore
    //   638: invokestatic 236	com/ea/nimble/Log$Helper:LOGES	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
    //   641: goto -40 -> 601
    //   644: astore 8
    //   646: aload 5
    //   648: astore_3
    //   649: aconst_null
    //   650: astore 4
    //   652: goto -69 -> 583
    //   655: astore 8
    //   657: aload 6
    //   659: astore 4
    //   661: aload 5
    //   663: astore_3
    //   664: goto -81 -> 583
    //   667: astore 7
    //   669: aload 5
    //   671: astore_3
    //   672: aconst_null
    //   673: astore 4
    //   675: goto -131 -> 544
    //   678: astore 7
    //   680: aload 6
    //   682: astore 4
    //   684: aload 5
    //   686: astore_3
    //   687: goto -143 -> 544
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	690	0	paramString	String
    //   38	138	1	localFile	File
    //   126	20	2	arrayOfObject1	Object[]
    //   150	537	3	localObject1	Object
    //   152	531	4	localObject2	Object
    //   162	523	5	localFileInputStream	java.io.FileInputStream
    //   173	508	6	localBufferedInputStream	java.io.BufferedInputStream
    //   542	37	7	localException1	java.lang.Exception
    //   667	1	7	localException2	java.lang.Exception
    //   678	1	7	localException3	java.lang.Exception
    //   581	21	8	localObject3	Object
    //   644	1	8	localObject4	Object
    //   655	1	8	localObject5	Object
    //   624	1	9	localException4	java.lang.Exception
    //   604	1	10	localIOException1	IOException
    //   548	21	11	arrayOfObject2	Object[]
    //   182	247	12	arrayOfByte	byte[]
    //   191	246	13	i	int
    //   198	229	14	localCipher	javax.crypto.Cipher
    //   399	21	15	localSecretKeySpec	javax.crypto.spec.SecretKeySpec
    //   414	8	16	localIvParameterSpec	javax.crypto.spec.IvParameterSpec
    //   443	21	17	localByteBuffer	ByteBuffer
    //   477	63	19	localEASPDataBuffer	EASPDataBuffer
    //   522	1	20	localException5	java.lang.Exception
    //   502	1	21	localIOException2	IOException
    // Exception table:
    //   from	to	target	type
    //   484	489	502	java/io/IOException
    //   494	499	522	java/lang/Exception
    //   154	164	542	java/lang/Exception
    //   154	164	581	finally
    //   544	581	581	finally
    //   587	591	604	java/io/IOException
    //   596	601	624	java/lang/Exception
    //   164	175	644	finally
    //   175	479	655	finally
    //   164	175	667	java/lang/Exception
    //   175	479	678	java/lang/Exception
  }
  
  public static boolean readBooleanByte(ByteBuffer paramByteBuffer)
  {
    return paramByteBuffer.get() != 0;
  }
  
  public static LogEvent readLogEvent(ByteBuffer paramByteBuffer)
    throws IOException
  {
    LogEvent localLogEvent = new LogEvent();
    try
    {
      if (!readBooleanByte(paramByteBuffer)) {
        return null;
      }
      localLogEvent.m_type = paramByteBuffer.getInt();
      localLogEvent.m_indexInsideSession = paramByteBuffer.getInt();
      localLogEvent.m_dateTimeInNanoseconds = paramByteBuffer.getLong();
      localLogEvent.m_EAUID = paramByteBuffer.getInt();
      localLogEvent.m_randomPart = readString(paramByteBuffer);
      localLogEvent.m_keyType01 = paramByteBuffer.getInt();
      localLogEvent.m_value01 = readString(paramByteBuffer);
      localLogEvent.m_keyType02 = paramByteBuffer.getInt();
      localLogEvent.m_value02 = readString(paramByteBuffer);
      localLogEvent.m_timestamp = paramByteBuffer.getLong();
      localLogEvent.m_keyType03 = paramByteBuffer.getInt();
      localLogEvent.m_value03 = readString(paramByteBuffer);
      localLogEvent.m_userLevel = paramByteBuffer.getInt();
      return localLogEvent;
    }
    catch (IOException localIOException)
    {
      Log.Helper.LOGES("Legacy", "Exception reading LogEvent: " + localIOException, new Object[0]);
      throw localIOException;
    }
  }
  
  /* Error */
  public static String readString(ByteBuffer paramByteBuffer)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 260	java/nio/ByteBuffer:getInt	()I
    //   4: istore_1
    //   5: iload_1
    //   6: ifgt +5 -> 11
    //   9: aconst_null
    //   10: areturn
    //   11: iload_1
    //   12: aload_0
    //   13: invokevirtual 312	java/nio/ByteBuffer:remaining	()I
    //   16: if_icmple +26 -> 42
    //   19: ldc 89
    //   21: ldc_w 314
    //   24: iconst_0
    //   25: anewarray 4	java/lang/Object
    //   28: invokestatic 236	com/ea/nimble/Log$Helper:LOGES	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
    //   31: new 81	java/io/IOException
    //   34: dup
    //   35: ldc_w 316
    //   38: invokespecial 317	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   41: athrow
    //   42: iload_1
    //   43: newarray byte
    //   45: astore_2
    //   46: aload_0
    //   47: aload_2
    //   48: iconst_0
    //   49: iload_1
    //   50: invokevirtual 320	java/nio/ByteBuffer:get	([BII)Ljava/nio/ByteBuffer;
    //   53: pop
    //   54: aconst_null
    //   55: astore 4
    //   57: new 83	java/lang/String
    //   60: dup
    //   61: aload_2
    //   62: ldc_w 322
    //   65: invokespecial 323	java/lang/String:<init>	([BLjava/lang/String;)V
    //   68: astore 5
    //   70: ldc 89
    //   72: ldc_w 325
    //   75: iconst_1
    //   76: anewarray 4	java/lang/Object
    //   79: dup
    //   80: iconst_0
    //   81: aload 5
    //   83: aastore
    //   84: invokestatic 97	com/ea/nimble/Log$Helper:LOGDS	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
    //   87: aload 5
    //   89: areturn
    //   90: astore 6
    //   92: ldc 89
    //   94: new 38	java/lang/StringBuilder
    //   97: dup
    //   98: invokespecial 39	java/lang/StringBuilder:<init>	()V
    //   101: ldc_w 327
    //   104: invokevirtual 51	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   107: aload 6
    //   109: invokevirtual 309	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   112: invokevirtual 60	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   115: iconst_0
    //   116: anewarray 4	java/lang/Object
    //   119: invokestatic 236	com/ea/nimble/Log$Helper:LOGES	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
    //   122: aload 4
    //   124: areturn
    //   125: astore 6
    //   127: aload 5
    //   129: astore 4
    //   131: goto -39 -> 92
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	134	0	paramByteBuffer	ByteBuffer
    //   4	46	1	i	int
    //   45	17	2	arrayOfByte	byte[]
    //   55	75	4	localObject	Object
    //   68	60	5	str	String
    //   90	18	6	localException1	java.lang.Exception
    //   125	1	6	localException2	java.lang.Exception
    // Exception table:
    //   from	to	target	type
    //   57	70	90	java/lang/Exception
    //   70	87	125	java/lang/Exception
  }
  
  public static class EASPDataBuffer
  {
    public ByteBuffer m_decryptedByteBuffer;
    public String m_version;
    
    public EASPDataBuffer(String paramString, ByteBuffer paramByteBuffer)
    {
      this.m_version = paramString;
      this.m_decryptedByteBuffer = paramByteBuffer;
    }
  }
  
  public static class LogEvent
  {
    public int m_EAUID;
    public long m_dateTimeInNanoseconds;
    public int m_indexInsideSession;
    public int m_keyType01;
    public int m_keyType02;
    public int m_keyType03;
    public String m_randomPart;
    public long m_timestamp;
    public int m_type;
    public int m_userLevel;
    public String m_value01;
    public String m_value02;
    public String m_value03;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ea.nimble.EASPDataLoader
 * JD-Core Version:    0.7.0.1
 */