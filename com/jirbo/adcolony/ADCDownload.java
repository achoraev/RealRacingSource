package com.jirbo.adcolony;

import java.io.File;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

class ADCDownload
  extends ADCEvent
  implements Runnable
{
  ADCController controller;
  String data;
  File file;
  Object info;
  Listener listener;
  String post_content_type;
  String post_data;
  int size;
  SSLContext ssl_context;
  boolean success;
  boolean third_party_tracking;
  String url;
  boolean use_ssl;
  
  ADCDownload(ADCController paramADCController, String paramString, Listener paramListener)
  {
    this(paramADCController, paramString, paramListener, null);
  }
  
  ADCDownload(ADCController paramADCController, String paramString1, Listener paramListener, String paramString2)
  {
    super(paramADCController, false);
    this.url = paramString1;
    this.listener = paramListener;
    if (paramString2 != null) {
      this.file = new File(paramString2);
    }
  }
  
  void dispatch()
  {
    this.listener.on_download_finished(this);
  }
  
  /* Error */
  public void run()
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore_1
    //   2: iload_1
    //   3: iconst_3
    //   4: if_icmpgt +672 -> 676
    //   7: aload_0
    //   8: getfield 67	com/jirbo/adcolony/ADCDownload:post_content_type	Ljava/lang/String;
    //   11: ifnull +729 -> 740
    //   14: getstatic 73	com/jirbo/adcolony/ADCLog:dev	Lcom/jirbo/adcolony/ADCLog;
    //   17: ldc 75
    //   19: invokevirtual 79	com/jirbo/adcolony/ADCLog:println	(Ljava/lang/Object;)Lcom/jirbo/adcolony/ADCLog;
    //   22: pop
    //   23: iconst_3
    //   24: istore_1
    //   25: aload_0
    //   26: getfield 37	com/jirbo/adcolony/ADCDownload:url	Ljava/lang/String;
    //   29: astore 41
    //   31: aload_0
    //   32: getfield 37	com/jirbo/adcolony/ADCDownload:url	Ljava/lang/String;
    //   35: astore 42
    //   37: ldc 81
    //   39: astore 43
    //   41: aload_0
    //   42: getfield 37	com/jirbo/adcolony/ADCDownload:url	Ljava/lang/String;
    //   45: invokevirtual 87	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   48: ldc 89
    //   50: invokevirtual 93	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   53: ifeq +16 -> 69
    //   56: aload_0
    //   57: aload_0
    //   58: getfield 37	com/jirbo/adcolony/ADCDownload:url	Ljava/lang/String;
    //   61: bipush 7
    //   63: invokevirtual 97	java/lang/String:substring	(I)Ljava/lang/String;
    //   66: putfield 37	com/jirbo/adcolony/ADCDownload:url	Ljava/lang/String;
    //   69: aload_0
    //   70: getfield 37	com/jirbo/adcolony/ADCDownload:url	Ljava/lang/String;
    //   73: bipush 47
    //   75: invokevirtual 101	java/lang/String:indexOf	(I)I
    //   78: istore 44
    //   80: iload 44
    //   82: iconst_m1
    //   83: if_icmpeq +26 -> 109
    //   86: aload_0
    //   87: getfield 37	com/jirbo/adcolony/ADCDownload:url	Ljava/lang/String;
    //   90: iload 44
    //   92: invokevirtual 97	java/lang/String:substring	(I)Ljava/lang/String;
    //   95: astore 43
    //   97: aload_0
    //   98: getfield 37	com/jirbo/adcolony/ADCDownload:url	Ljava/lang/String;
    //   101: iconst_0
    //   102: iload 44
    //   104: invokevirtual 104	java/lang/String:substring	(II)Ljava/lang/String;
    //   107: astore 42
    //   109: new 106	java/net/Socket
    //   112: dup
    //   113: aload 42
    //   115: bipush 80
    //   117: invokespecial 109	java/net/Socket:<init>	(Ljava/lang/String;I)V
    //   120: astore 45
    //   122: aload 45
    //   124: sipush 30000
    //   127: invokevirtual 113	java/net/Socket:setSoTimeout	(I)V
    //   130: new 115	java/io/PrintStream
    //   133: dup
    //   134: aload 45
    //   136: invokevirtual 119	java/net/Socket:getOutputStream	()Ljava/io/OutputStream;
    //   139: invokespecial 122	java/io/PrintStream:<init>	(Ljava/io/OutputStream;)V
    //   142: astore 46
    //   144: aload 45
    //   146: invokevirtual 126	java/net/Socket:getInputStream	()Ljava/io/InputStream;
    //   149: astore 47
    //   151: new 128	java/lang/StringBuilder
    //   154: dup
    //   155: invokespecial 130	java/lang/StringBuilder:<init>	()V
    //   158: astore 48
    //   160: aload 48
    //   162: ldc 132
    //   164: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   167: pop
    //   168: aload 48
    //   170: aload 43
    //   172: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   175: pop
    //   176: aload 48
    //   178: ldc 138
    //   180: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   183: pop
    //   184: aload 48
    //   186: ldc 140
    //   188: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   191: pop
    //   192: aload 48
    //   194: aload 42
    //   196: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   199: pop
    //   200: aload 48
    //   202: ldc 142
    //   204: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   207: pop
    //   208: aload 48
    //   210: ldc 144
    //   212: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   215: pop
    //   216: aload 48
    //   218: aload_0
    //   219: getfield 67	com/jirbo/adcolony/ADCDownload:post_content_type	Ljava/lang/String;
    //   222: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   225: pop
    //   226: aload 48
    //   228: ldc 142
    //   230: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   233: pop
    //   234: aload 48
    //   236: ldc 146
    //   238: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   241: pop
    //   242: aload 48
    //   244: iconst_4
    //   245: aload_0
    //   246: getfield 148	com/jirbo/adcolony/ADCDownload:post_data	Ljava/lang/String;
    //   249: invokevirtual 152	java/lang/String:length	()I
    //   252: iadd
    //   253: invokevirtual 155	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   256: pop
    //   257: aload 48
    //   259: ldc 157
    //   261: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   264: pop
    //   265: aload 48
    //   267: aload_0
    //   268: getfield 148	com/jirbo/adcolony/ADCDownload:post_data	Ljava/lang/String;
    //   271: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   274: pop
    //   275: aload 48
    //   277: ldc 157
    //   279: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   282: pop
    //   283: aload 48
    //   285: invokevirtual 160	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   288: astore 63
    //   290: aload 46
    //   292: aload 63
    //   294: invokevirtual 163	java/io/PrintStream:print	(Ljava/lang/String;)V
    //   297: aload 46
    //   299: invokevirtual 166	java/io/PrintStream:flush	()V
    //   302: getstatic 73	com/jirbo/adcolony/ADCLog:dev	Lcom/jirbo/adcolony/ADCLog;
    //   305: ldc 168
    //   307: invokevirtual 79	com/jirbo/adcolony/ADCLog:println	(Ljava/lang/Object;)Lcom/jirbo/adcolony/ADCLog;
    //   310: aload 63
    //   312: invokevirtual 79	com/jirbo/adcolony/ADCLog:println	(Ljava/lang/Object;)Lcom/jirbo/adcolony/ADCLog;
    //   315: pop
    //   316: new 128	java/lang/StringBuilder
    //   319: dup
    //   320: invokespecial 130	java/lang/StringBuilder:<init>	()V
    //   323: astore 65
    //   325: aload 47
    //   327: invokevirtual 173	java/io/InputStream:read	()I
    //   330: istore 66
    //   332: iload 66
    //   334: iconst_m1
    //   335: if_icmpeq +39 -> 374
    //   338: aload 65
    //   340: iload 66
    //   342: i2c
    //   343: invokevirtual 176	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   346: pop
    //   347: aload 65
    //   349: invokevirtual 177	java/lang/StringBuilder:length	()I
    //   352: iconst_4
    //   353: if_icmplt +95 -> 448
    //   356: aload 65
    //   358: ldc 157
    //   360: bipush 252
    //   362: aload 65
    //   364: invokevirtual 177	java/lang/StringBuilder:length	()I
    //   367: iadd
    //   368: invokevirtual 180	java/lang/StringBuilder:indexOf	(Ljava/lang/String;I)I
    //   371: iflt +77 -> 448
    //   374: aload 65
    //   376: invokevirtual 160	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   379: astore 68
    //   381: iconst_m1
    //   382: istore 69
    //   384: aload 68
    //   386: ldc 182
    //   388: invokevirtual 185	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   391: istore 70
    //   393: iload 70
    //   395: iflt +205 -> 600
    //   398: iload 70
    //   400: ldc 182
    //   402: invokevirtual 152	java/lang/String:length	()I
    //   405: iadd
    //   406: istore 71
    //   408: aload 68
    //   410: iload 71
    //   412: aload 68
    //   414: ldc 142
    //   416: iload 71
    //   418: invokevirtual 186	java/lang/String:indexOf	(Ljava/lang/String;I)I
    //   421: invokevirtual 104	java/lang/String:substring	(II)Ljava/lang/String;
    //   424: astore 72
    //   426: aload 72
    //   428: iconst_0
    //   429: invokevirtual 190	java/lang/String:charAt	(I)C
    //   432: bipush 32
    //   434: if_icmpne +24 -> 458
    //   437: aload 72
    //   439: iconst_1
    //   440: invokevirtual 97	java/lang/String:substring	(I)Ljava/lang/String;
    //   443: astore 72
    //   445: goto -19 -> 426
    //   448: aload 47
    //   450: invokevirtual 173	java/io/InputStream:read	()I
    //   453: istore 66
    //   455: goto -123 -> 332
    //   458: iconst_m1
    //   459: aload 72
    //   461: invokevirtual 152	java/lang/String:length	()I
    //   464: iadd
    //   465: istore 73
    //   467: aload 72
    //   469: iload 73
    //   471: invokevirtual 190	java/lang/String:charAt	(I)C
    //   474: bipush 32
    //   476: if_icmpne +25 -> 501
    //   479: iconst_m1
    //   480: aload 72
    //   482: invokevirtual 152	java/lang/String:length	()I
    //   485: iadd
    //   486: istore 82
    //   488: aload 72
    //   490: iconst_0
    //   491: iload 82
    //   493: invokevirtual 104	java/lang/String:substring	(II)Ljava/lang/String;
    //   496: astore 72
    //   498: goto -40 -> 458
    //   501: aload 72
    //   503: invokestatic 195	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   506: istore 69
    //   508: new 128	java/lang/StringBuilder
    //   511: dup
    //   512: invokespecial 130	java/lang/StringBuilder:<init>	()V
    //   515: astore 74
    //   517: iload 69
    //   519: ifeq +16 -> 535
    //   522: aload 47
    //   524: invokevirtual 173	java/io/InputStream:read	()I
    //   527: istore 78
    //   529: iload 78
    //   531: iconst_m1
    //   532: if_icmpne +154 -> 686
    //   535: aload 45
    //   537: invokevirtual 198	java/net/Socket:close	()V
    //   540: aload_0
    //   541: aload 74
    //   543: invokevirtual 160	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   546: putfield 200	com/jirbo/adcolony/ADCDownload:data	Ljava/lang/String;
    //   549: aload_0
    //   550: aload_0
    //   551: getfield 200	com/jirbo/adcolony/ADCDownload:data	Ljava/lang/String;
    //   554: invokevirtual 152	java/lang/String:length	()I
    //   557: putfield 202	com/jirbo/adcolony/ADCDownload:size	I
    //   560: getstatic 73	com/jirbo/adcolony/ADCLog:dev	Lcom/jirbo/adcolony/ADCLog;
    //   563: ldc 204
    //   565: invokevirtual 207	com/jirbo/adcolony/ADCLog:print	(Ljava/lang/String;)Lcom/jirbo/adcolony/ADCLog;
    //   568: aload 41
    //   570: invokevirtual 207	com/jirbo/adcolony/ADCLog:print	(Ljava/lang/String;)Lcom/jirbo/adcolony/ADCLog;
    //   573: ldc 209
    //   575: invokevirtual 79	com/jirbo/adcolony/ADCLog:println	(Ljava/lang/Object;)Lcom/jirbo/adcolony/ADCLog;
    //   578: pop
    //   579: getstatic 73	com/jirbo/adcolony/ADCLog:dev	Lcom/jirbo/adcolony/ADCLog;
    //   582: aload_0
    //   583: getfield 200	com/jirbo/adcolony/ADCDownload:data	Ljava/lang/String;
    //   586: invokevirtual 79	com/jirbo/adcolony/ADCLog:println	(Ljava/lang/Object;)Lcom/jirbo/adcolony/ADCLog;
    //   589: pop
    //   590: aload_0
    //   591: iconst_1
    //   592: putfield 211	com/jirbo/adcolony/ADCDownload:success	Z
    //   595: aload_0
    //   596: invokestatic 217	com/jirbo/adcolony/ADC:queue_event	(Lcom/jirbo/adcolony/ADCEvent;)V
    //   599: return
    //   600: new 128	java/lang/StringBuilder
    //   603: dup
    //   604: invokespecial 130	java/lang/StringBuilder:<init>	()V
    //   607: ldc 219
    //   609: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   612: aload_0
    //   613: getfield 37	com/jirbo/adcolony/ADCDownload:url	Ljava/lang/String;
    //   616: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   619: ldc 221
    //   621: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   624: invokevirtual 160	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   627: invokestatic 224	com/jirbo/adcolony/ADC:log_error	(Ljava/lang/String;)V
    //   630: goto -122 -> 508
    //   633: astore_2
    //   634: new 128	java/lang/StringBuilder
    //   637: dup
    //   638: invokespecial 130	java/lang/StringBuilder:<init>	()V
    //   641: ldc 226
    //   643: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   646: aload_0
    //   647: getfield 37	com/jirbo/adcolony/ADCDownload:url	Ljava/lang/String;
    //   650: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   653: ldc 228
    //   655: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   658: aload_2
    //   659: invokevirtual 229	java/io/IOException:toString	()Ljava/lang/String;
    //   662: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   665: invokevirtual 160	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   668: invokestatic 232	com/jirbo/adcolony/ADC:log_debug	(Ljava/lang/String;)V
    //   671: iload_1
    //   672: iconst_3
    //   673: if_icmpne +756 -> 1429
    //   676: aload_0
    //   677: iconst_0
    //   678: putfield 211	com/jirbo/adcolony/ADCDownload:success	Z
    //   681: aload_0
    //   682: invokestatic 217	com/jirbo/adcolony/ADC:queue_event	(Lcom/jirbo/adcolony/ADCEvent;)V
    //   685: return
    //   686: iload 78
    //   688: i2c
    //   689: istore 79
    //   691: aload 74
    //   693: iload 79
    //   695: invokevirtual 176	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   698: pop
    //   699: iload 69
    //   701: ifle -184 -> 517
    //   704: iinc 69 255
    //   707: iload 69
    //   709: ifne -192 -> 517
    //   712: aload 47
    //   714: invokevirtual 235	java/io/InputStream:available	()I
    //   717: istore 81
    //   719: iload 81
    //   721: ifle -204 -> 517
    //   724: iconst_m1
    //   725: istore 69
    //   727: goto -210 -> 517
    //   730: astore 77
    //   732: iload 69
    //   734: ifle -199 -> 535
    //   737: aload 77
    //   739: athrow
    //   740: new 237	java/net/URL
    //   743: dup
    //   744: aload_0
    //   745: getfield 37	com/jirbo/adcolony/ADCDownload:url	Ljava/lang/String;
    //   748: invokespecial 238	java/net/URL:<init>	(Ljava/lang/String;)V
    //   751: invokevirtual 242	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   754: checkcast 244	java/net/HttpURLConnection
    //   757: checkcast 244	java/net/HttpURLConnection
    //   760: astore 7
    //   762: aload 7
    //   764: sipush 30000
    //   767: invokevirtual 247	java/net/HttpURLConnection:setReadTimeout	(I)V
    //   770: aload_0
    //   771: getfield 249	com/jirbo/adcolony/ADCDownload:third_party_tracking	Z
    //   774: ifeq +9 -> 783
    //   777: aload 7
    //   779: iconst_0
    //   780: invokevirtual 253	java/net/HttpURLConnection:setInstanceFollowRedirects	(Z)V
    //   783: aload_0
    //   784: getfield 46	com/jirbo/adcolony/ADCDownload:file	Ljava/io/File;
    //   787: ifnull +184 -> 971
    //   790: aload_0
    //   791: getfield 255	com/jirbo/adcolony/ADCDownload:controller	Lcom/jirbo/adcolony/ADCController;
    //   794: ifnull +23 -> 817
    //   797: aload_0
    //   798: getfield 255	com/jirbo/adcolony/ADCDownload:controller	Lcom/jirbo/adcolony/ADCController;
    //   801: getfield 261	com/jirbo/adcolony/ADCController:storage	Lcom/jirbo/adcolony/ADCStorage;
    //   804: ifnull +13 -> 817
    //   807: aload_0
    //   808: getfield 255	com/jirbo/adcolony/ADCDownload:controller	Lcom/jirbo/adcolony/ADCController;
    //   811: getfield 261	com/jirbo/adcolony/ADCController:storage	Lcom/jirbo/adcolony/ADCStorage;
    //   814: invokevirtual 266	com/jirbo/adcolony/ADCStorage:validate_storage_paths	()V
    //   817: aload_0
    //   818: getfield 46	com/jirbo/adcolony/ADCDownload:file	Ljava/io/File;
    //   821: invokevirtual 269	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   824: astore 33
    //   826: new 271	java/io/FileOutputStream
    //   829: dup
    //   830: aload 33
    //   832: invokespecial 272	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   835: astore 34
    //   837: aload 7
    //   839: invokevirtual 273	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   842: astore 35
    //   844: aload 7
    //   846: invokevirtual 276	java/net/HttpURLConnection:getContentLength	()I
    //   849: istore 36
    //   851: aload_0
    //   852: iconst_0
    //   853: putfield 202	com/jirbo/adcolony/ADCDownload:size	I
    //   856: sipush 1024
    //   859: newarray byte
    //   861: astore 37
    //   863: aload 35
    //   865: aload 37
    //   867: iconst_0
    //   868: sipush 1024
    //   871: invokevirtual 279	java/io/InputStream:read	([BII)I
    //   874: istore 38
    //   876: goto +607 -> 1483
    //   879: aload_0
    //   880: iload 38
    //   882: aload_0
    //   883: getfield 202	com/jirbo/adcolony/ADCDownload:size	I
    //   886: iadd
    //   887: putfield 202	com/jirbo/adcolony/ADCDownload:size	I
    //   890: aload 34
    //   892: aload 37
    //   894: iconst_0
    //   895: iload 38
    //   897: invokevirtual 285	java/io/OutputStream:write	([BII)V
    //   900: aload 35
    //   902: aload 37
    //   904: iconst_0
    //   905: sipush 1024
    //   908: invokevirtual 279	java/io/InputStream:read	([BII)I
    //   911: istore 38
    //   913: iload 36
    //   915: ifne +568 -> 1483
    //   918: aload 35
    //   920: invokevirtual 286	java/io/InputStream:close	()V
    //   923: aload 34
    //   925: invokevirtual 287	java/io/OutputStream:flush	()V
    //   928: aload 34
    //   930: invokevirtual 288	java/io/OutputStream:close	()V
    //   933: getstatic 291	com/jirbo/adcolony/ADCLog:debug	Lcom/jirbo/adcolony/ADCLog;
    //   936: ldc_w 293
    //   939: invokevirtual 207	com/jirbo/adcolony/ADCLog:print	(Ljava/lang/String;)Lcom/jirbo/adcolony/ADCLog;
    //   942: aload_0
    //   943: getfield 37	com/jirbo/adcolony/ADCDownload:url	Ljava/lang/String;
    //   946: invokevirtual 207	com/jirbo/adcolony/ADCLog:print	(Ljava/lang/String;)Lcom/jirbo/adcolony/ADCLog;
    //   949: ldc_w 295
    //   952: invokevirtual 207	com/jirbo/adcolony/ADCLog:print	(Ljava/lang/String;)Lcom/jirbo/adcolony/ADCLog;
    //   955: aload 33
    //   957: invokevirtual 79	com/jirbo/adcolony/ADCLog:println	(Ljava/lang/Object;)Lcom/jirbo/adcolony/ADCLog;
    //   960: pop
    //   961: aload_0
    //   962: iconst_1
    //   963: putfield 211	com/jirbo/adcolony/ADCDownload:success	Z
    //   966: aload_0
    //   967: invokestatic 217	com/jirbo/adcolony/ADC:queue_event	(Lcom/jirbo/adcolony/ADCEvent;)V
    //   970: return
    //   971: aload_0
    //   972: getfield 249	com/jirbo/adcolony/ADCDownload:third_party_tracking	Z
    //   975: ifeq +74 -> 1049
    //   978: aload 7
    //   980: invokevirtual 298	java/net/HttpURLConnection:getResponseCode	()I
    //   983: istore 30
    //   985: iload 30
    //   987: ifle +62 -> 1049
    //   990: getstatic 73	com/jirbo/adcolony/ADCLog:dev	Lcom/jirbo/adcolony/ADCLog;
    //   993: ldc_w 300
    //   996: invokevirtual 207	com/jirbo/adcolony/ADCLog:print	(Ljava/lang/String;)Lcom/jirbo/adcolony/ADCLog;
    //   999: iload 30
    //   1001: invokevirtual 303	com/jirbo/adcolony/ADCLog:print	(I)Lcom/jirbo/adcolony/ADCLog;
    //   1004: ldc_w 305
    //   1007: invokevirtual 79	com/jirbo/adcolony/ADCLog:println	(Ljava/lang/Object;)Lcom/jirbo/adcolony/ADCLog;
    //   1010: pop
    //   1011: getstatic 291	com/jirbo/adcolony/ADCLog:debug	Lcom/jirbo/adcolony/ADCLog;
    //   1014: ldc_w 293
    //   1017: invokevirtual 207	com/jirbo/adcolony/ADCLog:print	(Ljava/lang/String;)Lcom/jirbo/adcolony/ADCLog;
    //   1020: aload_0
    //   1021: getfield 37	com/jirbo/adcolony/ADCDownload:url	Ljava/lang/String;
    //   1024: invokevirtual 79	com/jirbo/adcolony/ADCLog:println	(Ljava/lang/Object;)Lcom/jirbo/adcolony/ADCLog;
    //   1027: pop
    //   1028: aload_0
    //   1029: ldc 81
    //   1031: putfield 200	com/jirbo/adcolony/ADCDownload:data	Ljava/lang/String;
    //   1034: aload_0
    //   1035: iconst_0
    //   1036: putfield 202	com/jirbo/adcolony/ADCDownload:size	I
    //   1039: aload_0
    //   1040: iconst_1
    //   1041: putfield 211	com/jirbo/adcolony/ADCDownload:success	Z
    //   1044: aload_0
    //   1045: invokestatic 217	com/jirbo/adcolony/ADC:queue_event	(Lcom/jirbo/adcolony/ADCEvent;)V
    //   1048: return
    //   1049: aload_0
    //   1050: getfield 37	com/jirbo/adcolony/ADCDownload:url	Ljava/lang/String;
    //   1053: ldc_w 307
    //   1056: invokevirtual 93	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   1059: ifeq +325 -> 1384
    //   1062: getstatic 312	android/os/Build$VERSION:SDK_INT	I
    //   1065: bipush 10
    //   1067: if_icmplt +317 -> 1384
    //   1070: new 237	java/net/URL
    //   1073: dup
    //   1074: aload_0
    //   1075: getfield 37	com/jirbo/adcolony/ADCDownload:url	Ljava/lang/String;
    //   1078: invokespecial 238	java/net/URL:<init>	(Ljava/lang/String;)V
    //   1081: invokevirtual 242	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   1084: checkcast 314	javax/net/ssl/HttpsURLConnection
    //   1087: checkcast 314	javax/net/ssl/HttpsURLConnection
    //   1090: astore 8
    //   1092: aload_0
    //   1093: iconst_1
    //   1094: putfield 316	com/jirbo/adcolony/ADCDownload:use_ssl	Z
    //   1097: getstatic 73	com/jirbo/adcolony/ADCLog:dev	Lcom/jirbo/adcolony/ADCLog;
    //   1100: ldc_w 318
    //   1103: invokevirtual 79	com/jirbo/adcolony/ADCLog:println	(Ljava/lang/Object;)Lcom/jirbo/adcolony/ADCLog;
    //   1106: pop
    //   1107: new 320	com/jirbo/adcolony/ADCDownload$1
    //   1110: dup
    //   1111: aload_0
    //   1112: invokespecial 322	com/jirbo/adcolony/ADCDownload$1:<init>	(Lcom/jirbo/adcolony/ADCDownload;)V
    //   1115: astore 25
    //   1117: aload 8
    //   1119: aload 25
    //   1121: invokevirtual 326	javax/net/ssl/HttpsURLConnection:setHostnameVerifier	(Ljavax/net/ssl/HostnameVerifier;)V
    //   1124: ldc_w 328
    //   1127: invokestatic 334	javax/net/ssl/SSLContext:getInstance	(Ljava/lang/String;)Ljavax/net/ssl/SSLContext;
    //   1130: astore 28
    //   1132: iconst_1
    //   1133: anewarray 336	javax/net/ssl/TrustManager
    //   1136: astore 29
    //   1138: aload 29
    //   1140: iconst_0
    //   1141: new 338	com/jirbo/adcolony/ADCDownload$DefaultTrustManager
    //   1144: dup
    //   1145: aconst_null
    //   1146: invokespecial 341	com/jirbo/adcolony/ADCDownload$DefaultTrustManager:<init>	(Lcom/jirbo/adcolony/ADCDownload$1;)V
    //   1149: aastore
    //   1150: aload 28
    //   1152: aconst_null
    //   1153: aload 29
    //   1155: new 343	java/security/SecureRandom
    //   1158: dup
    //   1159: invokespecial 344	java/security/SecureRandom:<init>	()V
    //   1162: invokevirtual 348	javax/net/ssl/SSLContext:init	([Ljavax/net/ssl/KeyManager;[Ljavax/net/ssl/TrustManager;Ljava/security/SecureRandom;)V
    //   1165: aload 28
    //   1167: invokestatic 352	javax/net/ssl/SSLContext:setDefault	(Ljavax/net/ssl/SSLContext;)V
    //   1170: aload 28
    //   1172: invokevirtual 356	javax/net/ssl/SSLContext:getSocketFactory	()Ljavax/net/ssl/SSLSocketFactory;
    //   1175: invokestatic 360	javax/net/ssl/HttpsURLConnection:setDefaultSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
    //   1178: getstatic 73	com/jirbo/adcolony/ADCLog:dev	Lcom/jirbo/adcolony/ADCLog;
    //   1181: ldc_w 362
    //   1184: invokevirtual 79	com/jirbo/adcolony/ADCLog:println	(Ljava/lang/Object;)Lcom/jirbo/adcolony/ADCLog;
    //   1187: pop
    //   1188: ldc2_w 363
    //   1191: invokestatic 370	java/lang/Thread:sleep	(J)V
    //   1194: getstatic 73	com/jirbo/adcolony/ADCLog:dev	Lcom/jirbo/adcolony/ADCLog;
    //   1197: ldc_w 372
    //   1200: invokevirtual 79	com/jirbo/adcolony/ADCLog:println	(Ljava/lang/Object;)Lcom/jirbo/adcolony/ADCLog;
    //   1203: pop
    //   1204: aload_0
    //   1205: getfield 316	com/jirbo/adcolony/ADCDownload:use_ssl	Z
    //   1208: ifeq +187 -> 1395
    //   1211: aload 8
    //   1213: invokevirtual 373	javax/net/ssl/HttpsURLConnection:getInputStream	()Ljava/io/InputStream;
    //   1216: astore 13
    //   1218: new 128	java/lang/StringBuilder
    //   1221: dup
    //   1222: invokespecial 130	java/lang/StringBuilder:<init>	()V
    //   1225: astore 14
    //   1227: sipush 1024
    //   1230: newarray byte
    //   1232: astore 15
    //   1234: aload 13
    //   1236: aload 15
    //   1238: iconst_0
    //   1239: sipush 1024
    //   1242: invokevirtual 279	java/io/InputStream:read	([BII)I
    //   1245: istore 16
    //   1247: iload 16
    //   1249: istore 17
    //   1251: iload 17
    //   1253: iconst_m1
    //   1254: if_icmpeq +46 -> 1300
    //   1257: iconst_m1
    //   1258: istore 19
    //   1260: iinc 19 1
    //   1263: iload 19
    //   1265: iload 17
    //   1267: if_icmpge +142 -> 1409
    //   1270: aload 14
    //   1272: aload 15
    //   1274: iload 19
    //   1276: baload
    //   1277: i2c
    //   1278: invokevirtual 176	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   1281: pop
    //   1282: goto -22 -> 1260
    //   1285: astore 21
    //   1287: getstatic 376	com/jirbo/adcolony/ADCLog:error	Lcom/jirbo/adcolony/ADCLog;
    //   1290: ldc_w 378
    //   1293: invokevirtual 79	com/jirbo/adcolony/ADCLog:println	(Ljava/lang/Object;)Lcom/jirbo/adcolony/ADCLog;
    //   1296: pop
    //   1297: invokestatic 383	com/jirbo/adcolony/AdColony:disable	()V
    //   1300: aload 13
    //   1302: invokevirtual 286	java/io/InputStream:close	()V
    //   1305: aload_0
    //   1306: aload 14
    //   1308: invokevirtual 160	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1311: putfield 200	com/jirbo/adcolony/ADCDownload:data	Ljava/lang/String;
    //   1314: aload_0
    //   1315: aload_0
    //   1316: getfield 200	com/jirbo/adcolony/ADCDownload:data	Ljava/lang/String;
    //   1319: invokevirtual 152	java/lang/String:length	()I
    //   1322: putfield 202	com/jirbo/adcolony/ADCDownload:size	I
    //   1325: aload_0
    //   1326: getfield 37	com/jirbo/adcolony/ADCDownload:url	Ljava/lang/String;
    //   1329: ldc_w 385
    //   1332: invokevirtual 389	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   1335: ifeq +9 -> 1344
    //   1338: invokestatic 395	java/lang/System:currentTimeMillis	()J
    //   1341: putstatic 399	com/jirbo/adcolony/ADC:last_config_ms	J
    //   1344: getstatic 291	com/jirbo/adcolony/ADCLog:debug	Lcom/jirbo/adcolony/ADCLog;
    //   1347: ldc_w 293
    //   1350: invokevirtual 207	com/jirbo/adcolony/ADCLog:print	(Ljava/lang/String;)Lcom/jirbo/adcolony/ADCLog;
    //   1353: aload_0
    //   1354: getfield 37	com/jirbo/adcolony/ADCDownload:url	Ljava/lang/String;
    //   1357: invokevirtual 79	com/jirbo/adcolony/ADCLog:println	(Ljava/lang/Object;)Lcom/jirbo/adcolony/ADCLog;
    //   1360: pop
    //   1361: goto -400 -> 961
    //   1364: astore 26
    //   1366: getstatic 73	com/jirbo/adcolony/ADCLog:dev	Lcom/jirbo/adcolony/ADCLog;
    //   1369: ldc_w 401
    //   1372: invokevirtual 79	com/jirbo/adcolony/ADCLog:println	(Ljava/lang/Object;)Lcom/jirbo/adcolony/ADCLog;
    //   1375: pop
    //   1376: aload 26
    //   1378: invokevirtual 404	java/lang/Exception:printStackTrace	()V
    //   1381: goto -203 -> 1178
    //   1384: aload_0
    //   1385: iconst_0
    //   1386: putfield 316	com/jirbo/adcolony/ADCDownload:use_ssl	Z
    //   1389: aconst_null
    //   1390: astore 8
    //   1392: goto -214 -> 1178
    //   1395: aload 7
    //   1397: invokevirtual 273	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   1400: astore 12
    //   1402: aload 12
    //   1404: astore 13
    //   1406: goto -188 -> 1218
    //   1409: aload 13
    //   1411: aload 15
    //   1413: iconst_0
    //   1414: sipush 1024
    //   1417: invokevirtual 279	java/io/InputStream:read	([BII)I
    //   1420: istore 20
    //   1422: iload 20
    //   1424: istore 17
    //   1426: goto -175 -> 1251
    //   1429: sipush 1000
    //   1432: bipush 10
    //   1434: iload_1
    //   1435: iconst_1
    //   1436: iadd
    //   1437: imul
    //   1438: imul
    //   1439: i2l
    //   1440: lstore_3
    //   1441: lload_3
    //   1442: invokestatic 370	java/lang/Thread:sleep	(J)V
    //   1445: getstatic 291	com/jirbo/adcolony/ADCLog:debug	Lcom/jirbo/adcolony/ADCLog;
    //   1448: ldc_w 406
    //   1451: invokevirtual 207	com/jirbo/adcolony/ADCLog:print	(Ljava/lang/String;)Lcom/jirbo/adcolony/ADCLog;
    //   1454: iload_1
    //   1455: iconst_1
    //   1456: iadd
    //   1457: invokevirtual 303	com/jirbo/adcolony/ADCLog:print	(I)Lcom/jirbo/adcolony/ADCLog;
    //   1460: ldc_w 408
    //   1463: invokevirtual 79	com/jirbo/adcolony/ADCLog:println	(Ljava/lang/Object;)Lcom/jirbo/adcolony/ADCLog;
    //   1466: pop
    //   1467: iinc 1 1
    //   1470: goto -1468 -> 2
    //   1473: astore 10
    //   1475: goto -281 -> 1194
    //   1478: astore 5
    //   1480: goto -35 -> 1445
    //   1483: iload 38
    //   1485: iconst_m1
    //   1486: if_icmpeq -568 -> 918
    //   1489: iload 36
    //   1491: ifle -612 -> 879
    //   1494: iload 38
    //   1496: iload 36
    //   1498: if_icmple +7 -> 1505
    //   1501: iload 36
    //   1503: istore 38
    //   1505: iload 36
    //   1507: iload 38
    //   1509: isub
    //   1510: istore 36
    //   1512: goto -633 -> 879
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1515	0	this	ADCDownload
    //   1	1467	1	i	int
    //   633	26	2	localIOException	java.io.IOException
    //   1440	2	3	l	long
    //   1478	1	5	localInterruptedException	java.lang.InterruptedException
    //   760	636	7	localHttpURLConnection	java.net.HttpURLConnection
    //   1090	301	8	localHttpsURLConnection	javax.net.ssl.HttpsURLConnection
    //   1473	1	10	localException1	java.lang.Exception
    //   1400	3	12	localInputStream1	java.io.InputStream
    //   1216	194	13	localObject	Object
    //   1225	82	14	localStringBuilder1	java.lang.StringBuilder
    //   1232	180	15	arrayOfByte1	byte[]
    //   1245	3	16	j	int
    //   1249	176	17	k	int
    //   1258	17	19	m	int
    //   1420	3	20	n	int
    //   1285	1	21	localOutOfMemoryError	java.lang.OutOfMemoryError
    //   1115	5	25	local1	1
    //   1364	13	26	localException2	java.lang.Exception
    //   1130	41	28	localSSLContext	SSLContext
    //   1136	18	29	arrayOfTrustManager	javax.net.ssl.TrustManager[]
    //   983	17	30	i1	int
    //   824	132	33	str1	String
    //   835	94	34	localFileOutputStream	java.io.FileOutputStream
    //   842	77	35	localInputStream2	java.io.InputStream
    //   849	662	36	i2	int
    //   861	42	37	arrayOfByte2	byte[]
    //   874	636	38	i3	int
    //   29	540	41	str2	String
    //   35	160	42	str3	String
    //   39	132	43	str4	String
    //   78	25	44	i4	int
    //   120	416	45	localSocket	java.net.Socket
    //   142	156	46	localPrintStream	java.io.PrintStream
    //   149	564	47	localInputStream3	java.io.InputStream
    //   158	126	48	localStringBuilder2	java.lang.StringBuilder
    //   288	23	63	str5	String
    //   323	52	65	localStringBuilder3	java.lang.StringBuilder
    //   330	124	66	i5	int
    //   379	34	68	str6	String
    //   382	351	69	i6	int
    //   391	15	70	i7	int
    //   406	11	71	i8	int
    //   424	78	72	str7	String
    //   465	5	73	i9	int
    //   515	177	74	localStringBuilder4	java.lang.StringBuilder
    //   730	8	77	localSocketTimeoutException	java.net.SocketTimeoutException
    //   527	160	78	i10	int
    //   689	5	79	c	char
    //   717	3	81	i11	int
    //   486	6	82	i12	int
    // Exception table:
    //   from	to	target	type
    //   7	23	633	java/io/IOException
    //   25	37	633	java/io/IOException
    //   41	69	633	java/io/IOException
    //   69	80	633	java/io/IOException
    //   86	109	633	java/io/IOException
    //   109	332	633	java/io/IOException
    //   338	374	633	java/io/IOException
    //   374	381	633	java/io/IOException
    //   384	393	633	java/io/IOException
    //   398	426	633	java/io/IOException
    //   426	445	633	java/io/IOException
    //   448	455	633	java/io/IOException
    //   458	498	633	java/io/IOException
    //   501	508	633	java/io/IOException
    //   508	517	633	java/io/IOException
    //   522	529	633	java/io/IOException
    //   535	599	633	java/io/IOException
    //   600	630	633	java/io/IOException
    //   691	699	633	java/io/IOException
    //   712	719	633	java/io/IOException
    //   737	740	633	java/io/IOException
    //   740	783	633	java/io/IOException
    //   783	817	633	java/io/IOException
    //   817	876	633	java/io/IOException
    //   879	913	633	java/io/IOException
    //   918	961	633	java/io/IOException
    //   961	970	633	java/io/IOException
    //   971	985	633	java/io/IOException
    //   990	1048	633	java/io/IOException
    //   1049	1124	633	java/io/IOException
    //   1124	1178	633	java/io/IOException
    //   1178	1188	633	java/io/IOException
    //   1188	1194	633	java/io/IOException
    //   1194	1218	633	java/io/IOException
    //   1218	1247	633	java/io/IOException
    //   1270	1282	633	java/io/IOException
    //   1287	1300	633	java/io/IOException
    //   1300	1344	633	java/io/IOException
    //   1344	1361	633	java/io/IOException
    //   1366	1381	633	java/io/IOException
    //   1384	1389	633	java/io/IOException
    //   1395	1402	633	java/io/IOException
    //   1409	1422	633	java/io/IOException
    //   522	529	730	java/net/SocketTimeoutException
    //   691	699	730	java/net/SocketTimeoutException
    //   712	719	730	java/net/SocketTimeoutException
    //   1270	1282	1285	java/lang/OutOfMemoryError
    //   1409	1422	1285	java/lang/OutOfMemoryError
    //   1124	1178	1364	java/lang/Exception
    //   1188	1194	1473	java/lang/Exception
    //   1441	1445	1478	java/lang/InterruptedException
  }
  
  public void start()
  {
    ADCThreadPool.run(this);
  }
  
  ADCDownload with_info(Object paramObject)
  {
    this.info = paramObject;
    return this;
  }
  
  ADCDownload with_post_data(String paramString1, String paramString2)
  {
    this.post_content_type = paramString1;
    this.post_data = paramString2;
    return this;
  }
  
  private static class DefaultTrustManager
    implements X509TrustManager
  {
    public void checkClientTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString)
      throws CertificateException
    {}
    
    public void checkServerTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString)
      throws CertificateException
    {}
    
    public X509Certificate[] getAcceptedIssuers()
    {
      return null;
    }
  }
  
  public static abstract interface Listener
  {
    public abstract void on_download_finished(ADCDownload paramADCDownload);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.jirbo.adcolony.ADCDownload
 * JD-Core Version:    0.7.0.1
 */