package com.firemonkeys.cloudcellapi;

import java.util.HashMap;
import java.util.Map;

class HttpThread
  extends Thread
{
  private boolean m_bClosedBySSLCheck = false;
  private Boolean m_bContinue;
  private long m_callbackPointer = 0L;
  private byte[] m_data = null;
  private boolean m_failOnErrorStatus = false;
  private Map<String, String> m_mHeaders = new HashMap();
  private String m_method = "";
  CC_HttpRequest_Class m_post = null;
  private int m_readCapacity = 0;
  private String m_url = "";
  
  public HttpThread(CC_HttpRequest_Class paramCC_HttpRequest_Class, String paramString1, String paramString2, byte[] paramArrayOfByte, int paramInt, long paramLong, boolean paramBoolean)
  {
    this.m_post = paramCC_HttpRequest_Class;
    this.m_data = paramArrayOfByte;
    this.m_method = paramString1;
    this.m_url = paramString2;
    this.m_data = paramArrayOfByte;
    this.m_readCapacity = paramInt;
    this.m_callbackPointer = paramLong;
    this.m_failOnErrorStatus = paramBoolean;
    this.m_bContinue = Boolean.valueOf(true);
  }
  
  public void addHeader(String paramString1, String paramString2)
  {
    this.m_mHeaders.put(paramString1, paramString2);
  }
  
  /* Error */
  public void run()
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore_1
    //   2: iconst_0
    //   3: istore_2
    //   4: iconst_0
    //   5: istore_3
    //   6: invokestatic 86	java/lang/System:nanoTime	()J
    //   9: lstore 4
    //   11: invokestatic 86	java/lang/System:nanoTime	()J
    //   14: lstore 6
    //   16: iconst_0
    //   17: istore 8
    //   19: new 88	java/net/URL
    //   22: dup
    //   23: aload_0
    //   24: getfield 36	com/firemonkeys/cloudcellapi/HttpThread:m_url	Ljava/lang/String;
    //   27: invokespecial 91	java/net/URL:<init>	(Ljava/lang/String;)V
    //   30: astore 9
    //   32: aload 9
    //   34: invokevirtual 95	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   37: checkcast 97	java/net/HttpURLConnection
    //   40: astore 25
    //   42: aload 25
    //   44: ldc 98
    //   46: invokevirtual 102	java/net/HttpURLConnection:setConnectTimeout	(I)V
    //   49: aload 25
    //   51: iconst_0
    //   52: invokevirtual 106	java/net/HttpURLConnection:setUseCaches	(Z)V
    //   55: aload 25
    //   57: iconst_1
    //   58: invokevirtual 109	java/net/HttpURLConnection:setDoInput	(Z)V
    //   61: aload_0
    //   62: getfield 49	com/firemonkeys/cloudcellapi/HttpThread:m_mHeaders	Ljava/util/Map;
    //   65: invokeinterface 113 1 0
    //   70: invokeinterface 119 1 0
    //   75: astore 26
    //   77: aload 26
    //   79: invokeinterface 125 1 0
    //   84: ifeq +120 -> 204
    //   87: aload 26
    //   89: invokeinterface 129 1 0
    //   94: checkcast 131	java/util/Map$Entry
    //   97: astore 35
    //   99: aload 25
    //   101: aload 35
    //   103: invokeinterface 134 1 0
    //   108: invokevirtual 140	java/lang/Object:toString	()Ljava/lang/String;
    //   111: aload 35
    //   113: invokeinterface 143 1 0
    //   118: invokevirtual 140	java/lang/Object:toString	()Ljava/lang/String;
    //   121: invokevirtual 146	java/net/HttpURLConnection:addRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   124: goto -47 -> 77
    //   127: astore 23
    //   129: ldc 148
    //   131: ldc 150
    //   133: invokestatic 156	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   136: pop
    //   137: iconst_1
    //   138: istore_2
    //   139: iload 8
    //   141: iconst_1
    //   142: if_icmpne +13 -> 155
    //   145: aload_0
    //   146: getfield 59	com/firemonkeys/cloudcellapi/HttpThread:m_bContinue	Ljava/lang/Boolean;
    //   149: invokevirtual 159	java/lang/Boolean:booleanValue	()Z
    //   152: ifne -141 -> 11
    //   155: aload_0
    //   156: getfield 51	com/firemonkeys/cloudcellapi/HttpThread:m_bClosedBySSLCheck	Z
    //   159: ifeq +5 -> 164
    //   162: iconst_0
    //   163: istore_1
    //   164: aload_0
    //   165: invokevirtual 162	com/firemonkeys/cloudcellapi/HttpThread:isInterrupted	()Z
    //   168: ifne +7 -> 175
    //   171: iload_2
    //   172: ifeq +10 -> 182
    //   175: aload_0
    //   176: getfield 51	com/firemonkeys/cloudcellapi/HttpThread:m_bClosedBySSLCheck	Z
    //   179: ifeq +19 -> 198
    //   182: iload_1
    //   183: ifeq +506 -> 689
    //   186: aload_0
    //   187: getfield 30	com/firemonkeys/cloudcellapi/HttpThread:m_post	Lcom/firemonkeys/cloudcellapi/CC_HttpRequest_Class;
    //   190: aload_0
    //   191: getfield 42	com/firemonkeys/cloudcellapi/HttpThread:m_callbackPointer	J
    //   194: iload_3
    //   195: invokevirtual 168	com/firemonkeys/cloudcellapi/CC_HttpRequest_Class:completeCallback	(JI)V
    //   198: aload_0
    //   199: aconst_null
    //   200: putfield 30	com/firemonkeys/cloudcellapi/HttpThread:m_post	Lcom/firemonkeys/cloudcellapi/CC_HttpRequest_Class;
    //   203: return
    //   204: aload 25
    //   206: ldc 170
    //   208: getstatic 173	com/firemonkeys/cloudcellapi/CC_HttpRequest_Class:s_userAgent	Ljava/lang/String;
    //   211: invokevirtual 146	java/net/HttpURLConnection:addRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   214: aload 25
    //   216: aload_0
    //   217: getfield 34	com/firemonkeys/cloudcellapi/HttpThread:m_method	Ljava/lang/String;
    //   220: invokevirtual 176	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
    //   223: aload_0
    //   224: getfield 38	com/firemonkeys/cloudcellapi/HttpThread:m_data	[B
    //   227: arraylength
    //   228: ifle +43 -> 271
    //   231: aload 25
    //   233: iconst_1
    //   234: invokevirtual 179	java/net/HttpURLConnection:setDoOutput	(Z)V
    //   237: aload 25
    //   239: aload_0
    //   240: getfield 38	com/firemonkeys/cloudcellapi/HttpThread:m_data	[B
    //   243: arraylength
    //   244: invokevirtual 182	java/net/HttpURLConnection:setFixedLengthStreamingMode	(I)V
    //   247: aload 25
    //   249: ldc 184
    //   251: aload_0
    //   252: getfield 38	com/firemonkeys/cloudcellapi/HttpThread:m_data	[B
    //   255: arraylength
    //   256: invokestatic 189	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   259: invokevirtual 192	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   262: aload 25
    //   264: ldc 194
    //   266: ldc 196
    //   268: invokevirtual 192	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   271: aload_0
    //   272: getfield 38	com/firemonkeys/cloudcellapi/HttpThread:m_data	[B
    //   275: arraylength
    //   276: ifle +29 -> 305
    //   279: aload 25
    //   281: invokevirtual 200	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
    //   284: astore 34
    //   286: aload 34
    //   288: aload_0
    //   289: getfield 38	com/firemonkeys/cloudcellapi/HttpThread:m_data	[B
    //   292: invokevirtual 206	java/io/OutputStream:write	([B)V
    //   295: aload 34
    //   297: invokevirtual 209	java/io/OutputStream:flush	()V
    //   300: aload 34
    //   302: invokevirtual 212	java/io/OutputStream:close	()V
    //   305: aload_0
    //   306: aconst_null
    //   307: putfield 38	com/firemonkeys/cloudcellapi/HttpThread:m_data	[B
    //   310: aload 25
    //   312: invokevirtual 216	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   315: astore 28
    //   317: aload 25
    //   319: invokevirtual 220	java/net/HttpURLConnection:getResponseCode	()I
    //   322: istore_3
    //   323: aload_0
    //   324: getfield 44	com/firemonkeys/cloudcellapi/HttpThread:m_failOnErrorStatus	Z
    //   327: ifeq +46 -> 373
    //   330: iload_3
    //   331: sipush 400
    //   334: if_icmplt +39 -> 373
    //   337: iconst_0
    //   338: istore_1
    //   339: aload 28
    //   341: invokevirtual 223	java/io/InputStream:close	()V
    //   344: aload 25
    //   346: invokevirtual 226	java/net/HttpURLConnection:disconnect	()V
    //   349: iconst_0
    //   350: istore 8
    //   352: goto -213 -> 139
    //   355: astore 21
    //   357: ldc 148
    //   359: ldc 228
    //   361: invokestatic 231	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   364: pop
    //   365: iconst_0
    //   366: istore 8
    //   368: iconst_0
    //   369: istore_1
    //   370: goto -231 -> 139
    //   373: aload_0
    //   374: getfield 51	com/firemonkeys/cloudcellapi/HttpThread:m_bClosedBySSLCheck	Z
    //   377: ifne -38 -> 339
    //   380: aload 25
    //   382: ldc 184
    //   384: invokevirtual 235	java/net/HttpURLConnection:getHeaderField	(Ljava/lang/String;)Ljava/lang/String;
    //   387: astore 29
    //   389: aload 29
    //   391: ifnull +313 -> 704
    //   394: aload 29
    //   396: invokestatic 239	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   399: istore 30
    //   401: aload_0
    //   402: getfield 30	com/firemonkeys/cloudcellapi/HttpThread:m_post	Lcom/firemonkeys/cloudcellapi/CC_HttpRequest_Class;
    //   405: aload_0
    //   406: getfield 42	com/firemonkeys/cloudcellapi/HttpThread:m_callbackPointer	J
    //   409: iload 30
    //   411: aload 25
    //   413: invokevirtual 243	java/net/HttpURLConnection:getHeaderFields	()Ljava/util/Map;
    //   416: invokevirtual 247	com/firemonkeys/cloudcellapi/CC_HttpRequest_Class:headerCallback	(JILjava/util/Map;)V
    //   419: aload_0
    //   420: getfield 40	com/firemonkeys/cloudcellapi/HttpThread:m_readCapacity	I
    //   423: newarray byte
    //   425: astore 31
    //   427: aload 28
    //   429: aload 31
    //   431: invokevirtual 251	java/io/InputStream:read	([B)I
    //   434: istore 32
    //   436: iload 32
    //   438: iconst_m1
    //   439: if_icmpeq -100 -> 339
    //   442: aload_0
    //   443: invokevirtual 162	com/firemonkeys/cloudcellapi/HttpThread:isInterrupted	()Z
    //   446: ifeq +16 -> 462
    //   449: ldc 148
    //   451: ldc 150
    //   453: invokestatic 156	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   456: pop
    //   457: iconst_1
    //   458: istore_2
    //   459: goto -120 -> 339
    //   462: aload_0
    //   463: getfield 30	com/firemonkeys/cloudcellapi/HttpThread:m_post	Lcom/firemonkeys/cloudcellapi/CC_HttpRequest_Class;
    //   466: aload_0
    //   467: getfield 42	com/firemonkeys/cloudcellapi/HttpThread:m_callbackPointer	J
    //   470: aload 31
    //   472: iload 32
    //   474: invokevirtual 255	com/firemonkeys/cloudcellapi/CC_HttpRequest_Class:dataCallback	(J[BI)V
    //   477: goto -50 -> 427
    //   480: astore 27
    //   482: aload 25
    //   484: invokevirtual 226	java/net/HttpURLConnection:disconnect	()V
    //   487: aload 27
    //   489: athrow
    //   490: astore 16
    //   492: ldc 148
    //   494: new 257	java/lang/StringBuilder
    //   497: dup
    //   498: invokespecial 258	java/lang/StringBuilder:<init>	()V
    //   501: ldc_w 260
    //   504: invokevirtual 264	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   507: aload 16
    //   509: invokevirtual 267	java/io/IOException:getMessage	()Ljava/lang/String;
    //   512: invokevirtual 264	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   515: ldc_w 269
    //   518: invokevirtual 264	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   521: aload 16
    //   523: invokevirtual 273	java/io/IOException:getCause	()Ljava/lang/Throwable;
    //   526: invokevirtual 276	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   529: invokevirtual 277	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   532: invokestatic 231	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   535: pop
    //   536: invokestatic 86	java/lang/System:nanoTime	()J
    //   539: lstore 18
    //   541: lload 18
    //   543: lload 6
    //   545: lsub
    //   546: ldc2_w 278
    //   549: lcmp
    //   550: ifge +30 -> 580
    //   553: lload 18
    //   555: lload 4
    //   557: lsub
    //   558: ldc2_w 280
    //   561: lcmp
    //   562: ifge +18 -> 580
    //   565: ldc 148
    //   567: ldc_w 283
    //   570: invokestatic 231	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   573: pop
    //   574: iconst_1
    //   575: istore 8
    //   577: goto -438 -> 139
    //   580: iconst_0
    //   581: istore 8
    //   583: iconst_0
    //   584: istore_1
    //   585: goto -446 -> 139
    //   588: astore 14
    //   590: ldc 148
    //   592: ldc_w 285
    //   595: invokestatic 231	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   598: pop
    //   599: iconst_0
    //   600: istore 8
    //   602: iconst_0
    //   603: istore_1
    //   604: goto -465 -> 139
    //   607: astore 12
    //   609: ldc 148
    //   611: new 257	java/lang/StringBuilder
    //   614: dup
    //   615: invokespecial 258	java/lang/StringBuilder:<init>	()V
    //   618: ldc_w 287
    //   621: invokevirtual 264	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   624: aload 12
    //   626: invokevirtual 288	java/lang/NullPointerException:getMessage	()Ljava/lang/String;
    //   629: invokevirtual 264	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   632: invokevirtual 277	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   635: invokestatic 231	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   638: pop
    //   639: iconst_0
    //   640: istore 8
    //   642: iconst_0
    //   643: istore_1
    //   644: goto -505 -> 139
    //   647: astore 10
    //   649: ldc 148
    //   651: new 257	java/lang/StringBuilder
    //   654: dup
    //   655: invokespecial 258	java/lang/StringBuilder:<init>	()V
    //   658: ldc_w 290
    //   661: invokevirtual 264	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   664: aload 10
    //   666: invokevirtual 291	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   669: invokevirtual 264	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   672: invokevirtual 277	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   675: aload 10
    //   677: invokestatic 294	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   680: pop
    //   681: iconst_0
    //   682: istore 8
    //   684: iconst_0
    //   685: istore_1
    //   686: goto -547 -> 139
    //   689: aload_0
    //   690: getfield 30	com/firemonkeys/cloudcellapi/HttpThread:m_post	Lcom/firemonkeys/cloudcellapi/CC_HttpRequest_Class;
    //   693: aload_0
    //   694: getfield 42	com/firemonkeys/cloudcellapi/HttpThread:m_callbackPointer	J
    //   697: iload_3
    //   698: invokevirtual 297	com/firemonkeys/cloudcellapi/CC_HttpRequest_Class:errorCallback	(JI)V
    //   701: goto -503 -> 198
    //   704: iconst_0
    //   705: istore 30
    //   707: goto -306 -> 401
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	710	0	this	HttpThread
    //   1	685	1	i	int
    //   3	456	2	j	int
    //   5	693	3	k	int
    //   9	547	4	l1	long
    //   14	530	6	l2	long
    //   17	666	8	m	int
    //   30	3	9	localURL	java.net.URL
    //   647	29	10	localThrowable	java.lang.Throwable
    //   607	18	12	localNullPointerException	java.lang.NullPointerException
    //   588	1	14	localIllegalStateException	java.lang.IllegalStateException
    //   490	32	16	localIOException	java.io.IOException
    //   539	15	18	l3	long
    //   355	1	21	localMalformedURLException	java.net.MalformedURLException
    //   127	1	23	localClosedByInterruptException	java.nio.channels.ClosedByInterruptException
    //   40	443	25	localHttpURLConnection	java.net.HttpURLConnection
    //   75	13	26	localIterator	java.util.Iterator
    //   480	8	27	localObject	java.lang.Object
    //   315	113	28	localInputStream	java.io.InputStream
    //   387	8	29	str	String
    //   399	307	30	n	int
    //   425	46	31	arrayOfByte	byte[]
    //   434	39	32	i1	int
    //   284	17	34	localOutputStream	java.io.OutputStream
    //   97	15	35	localEntry	java.util.Map.Entry
    // Exception table:
    //   from	to	target	type
    //   19	77	127	java/nio/channels/ClosedByInterruptException
    //   77	124	127	java/nio/channels/ClosedByInterruptException
    //   204	271	127	java/nio/channels/ClosedByInterruptException
    //   344	349	127	java/nio/channels/ClosedByInterruptException
    //   482	490	127	java/nio/channels/ClosedByInterruptException
    //   19	77	355	java/net/MalformedURLException
    //   77	124	355	java/net/MalformedURLException
    //   204	271	355	java/net/MalformedURLException
    //   344	349	355	java/net/MalformedURLException
    //   482	490	355	java/net/MalformedURLException
    //   271	305	480	finally
    //   305	330	480	finally
    //   339	344	480	finally
    //   373	389	480	finally
    //   394	401	480	finally
    //   401	427	480	finally
    //   427	436	480	finally
    //   442	457	480	finally
    //   462	477	480	finally
    //   19	77	490	java/io/IOException
    //   77	124	490	java/io/IOException
    //   204	271	490	java/io/IOException
    //   344	349	490	java/io/IOException
    //   482	490	490	java/io/IOException
    //   19	77	588	java/lang/IllegalStateException
    //   77	124	588	java/lang/IllegalStateException
    //   204	271	588	java/lang/IllegalStateException
    //   344	349	588	java/lang/IllegalStateException
    //   482	490	588	java/lang/IllegalStateException
    //   19	77	607	java/lang/NullPointerException
    //   77	124	607	java/lang/NullPointerException
    //   204	271	607	java/lang/NullPointerException
    //   344	349	607	java/lang/NullPointerException
    //   482	490	607	java/lang/NullPointerException
    //   19	77	647	java/lang/Throwable
    //   77	124	647	java/lang/Throwable
    //   204	271	647	java/lang/Throwable
    //   344	349	647	java/lang/Throwable
    //   482	490	647	java/lang/Throwable
  }
  
  public void setClosedBySSLCheck(boolean paramBoolean)
  {
    this.m_bClosedBySSLCheck = paramBoolean;
  }
  
  public void shutdown()
  {
    this.m_bContinue = Boolean.valueOf(false);
    interrupt();
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.firemonkeys.cloudcellapi.HttpThread
 * JD-Core Version:    0.7.0.1
 */