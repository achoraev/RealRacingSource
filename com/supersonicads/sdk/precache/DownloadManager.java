package com.supersonicads.sdk.precache;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.supersonicads.sdk.data.SSAFile;
import com.supersonicads.sdk.utils.SDKUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class DownloadManager
{
  public static final String CAMPAIGNS = "campaigns";
  public static final String FILE_ALREADY_EXIST = "file_already_exist";
  public static final String GLOBAL_ASSETS = "globalAssets";
  public static final int MESSAGE_EMPTY_URL = 1007;
  public static final int MESSAGE_FILE_DOWNLOAD_FAIL = 1017;
  public static final int MESSAGE_FILE_DOWNLOAD_SUCCESS = 1016;
  public static final int MESSAGE_FILE_NOT_FOUND_EXCEPTION = 1018;
  public static final int MESSAGE_GENERAL_HTTP_ERROR_CODE = 1011;
  public static final int MESSAGE_HTTP_EMPTY_RESPONSE = 1006;
  public static final int MESSAGE_HTTP_NOT_FOUND = 1005;
  public static final int MESSAGE_INIT_BC_FAIL = 1014;
  public static final int MESSAGE_IO_EXCEPTION = 1009;
  public static final int MESSAGE_MALFORMED_URL_EXCEPTION = 1004;
  public static final int MESSAGE_NUM_OF_BANNERS_TO_CACHE = 1013;
  public static final int MESSAGE_NUM_OF_BANNERS_TO_INIT_SUCCESS = 1012;
  public static final int MESSAGE_SOCKET_TIMEOUT_EXCEPTION = 1008;
  public static final int MESSAGE_URI_SYNTAX_EXCEPTION = 1010;
  public static final int MESSAGE_ZERO_CAMPAIGNS_TO_INIT_SUCCESS = 1015;
  public static final String NO_DISK_SPACE = "no_disk_space";
  public static final String NO_NETWORK_CONNECTION = "no_network_connection";
  public static final int OPERATION_TIMEOUT = 5000;
  public static final String SETTINGS = "settings";
  public static final String SOTRAGE_UNAVAILABLE = "sotrage_unavailable";
  public static final String UNABLE_TO_CREATE_FOLDER = "unable_to_create_folder";
  public static final String UTF8_CHARSET = "UTF-8";
  private static DownloadManager mDownloadManager;
  private final String FILE_NOT_FOUND_EXCEPTION = "file not found exception";
  private final String HTTP_EMPTY_RESPONSE = "http empty response";
  private final String HTTP_ERROR_CODE = "http error code";
  private final String HTTP_NOT_FOUND = "http not found";
  private final String HTTP_OK = "http ok";
  private final String IO_EXCEPTION = "io exception";
  private final String MALFORMED_URL_EXCEPTION = "malformed url exception";
  private final String SOCKET_TIMEOUT_EXCEPTION = "socket timeout exception";
  private final String TAG = "DownloadManager";
  private final String URI_SYNTAX_EXCEPTION = "uri syntax exception";
  private Handler downloadHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      switch (paramAnonymousMessage.what)
      {
      }
      do
      {
        do
        {
          return;
        } while (DownloadManager.this.mListener == null);
        DownloadManager.this.mListener.onFileDownloadSuccess((SSAFile)paramAnonymousMessage.obj);
        return;
      } while (DownloadManager.this.mListener == null);
      DownloadManager.this.mListener.onFileDownloadFail((SSAFile)paramAnonymousMessage.obj);
    }
  };
  private CacheManager mCacheManager;
  private OnPreCacheCompletion mListener;
  private Thread mMobileControllerThread;
  
  private DownloadManager(Context paramContext)
  {
    this.mCacheManager = new CacheManager(paramContext);
  }
  
  public static DownloadManager getInstance(Context paramContext)
  {
    try
    {
      if (mDownloadManager == null) {
        mDownloadManager = new DownloadManager(paramContext);
      }
      DownloadManager localDownloadManager = mDownloadManager;
      return localDownloadManager;
    }
    finally {}
  }
  
  /* Error */
  public List<Object> downloadContent(String paramString1, String paramString2, String paramString3, String paramString4, int paramInt)
  {
    // Byte code:
    //   0: new 170	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 171	java/util/ArrayList:<init>	()V
    //   7: astore 6
    //   9: aconst_null
    //   10: astore 7
    //   12: aconst_null
    //   13: astore 8
    //   15: aconst_null
    //   16: astore 9
    //   18: iconst_0
    //   19: istore 10
    //   21: aload_1
    //   22: invokestatic 177	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   25: ifeq +29 -> 54
    //   28: aload 6
    //   30: aload_1
    //   31: invokeinterface 183 2 0
    //   36: pop
    //   37: aload 6
    //   39: sipush 1007
    //   42: invokestatic 189	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   45: invokeinterface 183 2 0
    //   50: pop
    //   51: aload 6
    //   53: areturn
    //   54: new 191	java/net/URL
    //   57: dup
    //   58: aload_1
    //   59: invokespecial 194	java/net/URL:<init>	(Ljava/lang/String;)V
    //   62: astore 11
    //   64: aload 11
    //   66: invokevirtual 198	java/net/URL:toURI	()Ljava/net/URI;
    //   69: pop
    //   70: aload 11
    //   72: invokevirtual 202	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   75: checkcast 204	java/net/HttpURLConnection
    //   78: astore 9
    //   80: aload 9
    //   82: ldc 206
    //   84: invokevirtual 209	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
    //   87: aload 9
    //   89: sipush 5000
    //   92: invokevirtual 213	java/net/HttpURLConnection:setConnectTimeout	(I)V
    //   95: aload 9
    //   97: sipush 5000
    //   100: invokevirtual 216	java/net/HttpURLConnection:setReadTimeout	(I)V
    //   103: aload 9
    //   105: invokevirtual 219	java/net/HttpURLConnection:connect	()V
    //   108: aload 9
    //   110: invokevirtual 223	java/net/HttpURLConnection:getResponseCode	()I
    //   113: istore 10
    //   115: aload_0
    //   116: getfield 143	com/supersonicads/sdk/precache/DownloadManager:mCacheManager	Lcom/supersonicads/sdk/precache/CacheManager;
    //   119: invokevirtual 226	com/supersonicads/sdk/precache/CacheManager:createRootDirectory	()V
    //   122: aload 4
    //   124: invokestatic 177	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   127: ifne +13 -> 140
    //   130: aload_0
    //   131: getfield 143	com/supersonicads/sdk/precache/DownloadManager:mCacheManager	Lcom/supersonicads/sdk/precache/CacheManager;
    //   134: aload 4
    //   136: invokevirtual 230	com/supersonicads/sdk/precache/CacheManager:makeDir	(Ljava/lang/String;)Ljava/lang/String;
    //   139: pop
    //   140: new 232	java/io/File
    //   143: dup
    //   144: new 234	java/lang/StringBuilder
    //   147: dup
    //   148: aload_2
    //   149: invokestatic 239	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   152: invokespecial 240	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   155: getstatic 243	java/io/File:separator	Ljava/lang/String;
    //   158: invokevirtual 247	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   161: aload_3
    //   162: invokevirtual 247	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   165: invokevirtual 251	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   168: invokespecial 252	java/io/File:<init>	(Ljava/lang/String;)V
    //   171: astore 42
    //   173: new 254	java/io/FileOutputStream
    //   176: dup
    //   177: aload 42
    //   179: invokespecial 257	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   182: astore 43
    //   184: aload 9
    //   186: invokevirtual 261	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   189: astore 8
    //   191: ldc_w 262
    //   194: newarray byte
    //   196: astore 48
    //   198: iconst_0
    //   199: istore 49
    //   201: aload 8
    //   203: aload 48
    //   205: invokevirtual 268	java/io/InputStream:read	([B)I
    //   208: istore 54
    //   210: iload 54
    //   212: iconst_m1
    //   213: if_icmpne +146 -> 359
    //   216: iload 49
    //   218: ifne +8 -> 226
    //   221: sipush 1006
    //   224: istore 10
    //   226: aload 43
    //   228: ifnull +8 -> 236
    //   231: aload 43
    //   233: invokevirtual 271	java/io/FileOutputStream:close	()V
    //   236: aload 8
    //   238: ifnull +8 -> 246
    //   241: aload 8
    //   243: invokevirtual 272	java/io/InputStream:close	()V
    //   246: iload 10
    //   248: ifne +24 -> 272
    //   251: iload 10
    //   253: sipush 200
    //   256: if_icmpge +16 -> 272
    //   259: iload 10
    //   261: sipush 399
    //   264: if_icmple +8 -> 272
    //   267: sipush 1011
    //   270: istore 10
    //   272: iload 10
    //   274: sipush 200
    //   277: if_icmpeq +47 -> 324
    //   280: ldc 92
    //   282: new 234	java/lang/StringBuilder
    //   285: dup
    //   286: ldc_w 274
    //   289: invokespecial 240	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   292: iload 10
    //   294: invokevirtual 277	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   297: ldc_w 279
    //   300: invokevirtual 247	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   303: aload_1
    //   304: invokevirtual 247	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   307: ldc_w 281
    //   310: invokevirtual 247	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   313: iload 5
    //   315: invokevirtual 277	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   318: invokevirtual 251	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   321: invokestatic 287	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   324: aload 9
    //   326: ifnull +8 -> 334
    //   329: aload 9
    //   331: invokevirtual 290	java/net/HttpURLConnection:disconnect	()V
    //   334: aload 6
    //   336: aload_1
    //   337: invokeinterface 183 2 0
    //   342: pop
    //   343: aload 6
    //   345: iload 10
    //   347: invokestatic 189	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   350: invokeinterface 183 2 0
    //   355: pop
    //   356: aload 6
    //   358: areturn
    //   359: aload 43
    //   361: aload 48
    //   363: iconst_0
    //   364: iload 54
    //   366: invokevirtual 294	java/io/FileOutputStream:write	([BII)V
    //   369: iload 49
    //   371: iload 54
    //   373: iadd
    //   374: istore 49
    //   376: goto -175 -> 201
    //   379: astore 50
    //   381: sipush 1009
    //   384: istore 10
    //   386: goto -170 -> 216
    //   389: astore 36
    //   391: sipush 1004
    //   394: istore 37
    //   396: aload 7
    //   398: ifnull +8 -> 406
    //   401: aload 7
    //   403: invokevirtual 271	java/io/FileOutputStream:close	()V
    //   406: aload 8
    //   408: ifnull +8 -> 416
    //   411: aload 8
    //   413: invokevirtual 272	java/io/InputStream:close	()V
    //   416: iload 37
    //   418: ifne +24 -> 442
    //   421: iload 37
    //   423: sipush 200
    //   426: if_icmpge +16 -> 442
    //   429: iload 37
    //   431: sipush 399
    //   434: if_icmple +8 -> 442
    //   437: sipush 1011
    //   440: istore 37
    //   442: iload 37
    //   444: sipush 200
    //   447: if_icmpeq +47 -> 494
    //   450: ldc 92
    //   452: new 234	java/lang/StringBuilder
    //   455: dup
    //   456: ldc_w 274
    //   459: invokespecial 240	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   462: iload 37
    //   464: invokevirtual 277	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   467: ldc_w 279
    //   470: invokevirtual 247	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   473: aload_1
    //   474: invokevirtual 247	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   477: ldc_w 281
    //   480: invokevirtual 247	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   483: iload 5
    //   485: invokevirtual 277	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   488: invokevirtual 251	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   491: invokestatic 287	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   494: aload 9
    //   496: ifnull +8 -> 504
    //   499: aload 9
    //   501: invokevirtual 290	java/net/HttpURLConnection:disconnect	()V
    //   504: aload 6
    //   506: aload_1
    //   507: invokeinterface 183 2 0
    //   512: pop
    //   513: aload 6
    //   515: iload 37
    //   517: invokestatic 189	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   520: invokeinterface 183 2 0
    //   525: pop
    //   526: aload 6
    //   528: areturn
    //   529: astore 31
    //   531: sipush 1010
    //   534: istore 32
    //   536: aload 7
    //   538: ifnull +8 -> 546
    //   541: aload 7
    //   543: invokevirtual 271	java/io/FileOutputStream:close	()V
    //   546: aload 8
    //   548: ifnull +8 -> 556
    //   551: aload 8
    //   553: invokevirtual 272	java/io/InputStream:close	()V
    //   556: iload 32
    //   558: ifne +24 -> 582
    //   561: iload 32
    //   563: sipush 200
    //   566: if_icmpge +16 -> 582
    //   569: iload 32
    //   571: sipush 399
    //   574: if_icmple +8 -> 582
    //   577: sipush 1011
    //   580: istore 32
    //   582: iload 32
    //   584: sipush 200
    //   587: if_icmpeq +47 -> 634
    //   590: ldc 92
    //   592: new 234	java/lang/StringBuilder
    //   595: dup
    //   596: ldc_w 274
    //   599: invokespecial 240	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   602: iload 32
    //   604: invokevirtual 277	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   607: ldc_w 279
    //   610: invokevirtual 247	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   613: aload_1
    //   614: invokevirtual 247	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   617: ldc_w 281
    //   620: invokevirtual 247	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   623: iload 5
    //   625: invokevirtual 277	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   628: invokevirtual 251	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   631: invokestatic 287	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   634: aload 9
    //   636: ifnull +8 -> 644
    //   639: aload 9
    //   641: invokevirtual 290	java/net/HttpURLConnection:disconnect	()V
    //   644: aload 6
    //   646: aload_1
    //   647: invokeinterface 183 2 0
    //   652: pop
    //   653: aload 6
    //   655: iload 32
    //   657: invokestatic 189	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   660: invokeinterface 183 2 0
    //   665: pop
    //   666: aload 6
    //   668: areturn
    //   669: astore 26
    //   671: sipush 1008
    //   674: istore 27
    //   676: aload 7
    //   678: ifnull +8 -> 686
    //   681: aload 7
    //   683: invokevirtual 271	java/io/FileOutputStream:close	()V
    //   686: aload 8
    //   688: ifnull +8 -> 696
    //   691: aload 8
    //   693: invokevirtual 272	java/io/InputStream:close	()V
    //   696: iload 27
    //   698: ifne +24 -> 722
    //   701: iload 27
    //   703: sipush 200
    //   706: if_icmpge +16 -> 722
    //   709: iload 27
    //   711: sipush 399
    //   714: if_icmple +8 -> 722
    //   717: sipush 1011
    //   720: istore 27
    //   722: iload 27
    //   724: sipush 200
    //   727: if_icmpeq +47 -> 774
    //   730: ldc 92
    //   732: new 234	java/lang/StringBuilder
    //   735: dup
    //   736: ldc_w 274
    //   739: invokespecial 240	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   742: iload 27
    //   744: invokevirtual 277	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   747: ldc_w 279
    //   750: invokevirtual 247	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   753: aload_1
    //   754: invokevirtual 247	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   757: ldc_w 281
    //   760: invokevirtual 247	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   763: iload 5
    //   765: invokevirtual 277	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   768: invokevirtual 251	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   771: invokestatic 287	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   774: aload 9
    //   776: ifnull +8 -> 784
    //   779: aload 9
    //   781: invokevirtual 290	java/net/HttpURLConnection:disconnect	()V
    //   784: aload 6
    //   786: aload_1
    //   787: invokeinterface 183 2 0
    //   792: pop
    //   793: aload 6
    //   795: iload 27
    //   797: invokestatic 189	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   800: invokeinterface 183 2 0
    //   805: pop
    //   806: aload 6
    //   808: areturn
    //   809: astore 21
    //   811: sipush 1018
    //   814: istore 22
    //   816: aload 7
    //   818: ifnull +8 -> 826
    //   821: aload 7
    //   823: invokevirtual 271	java/io/FileOutputStream:close	()V
    //   826: aload 8
    //   828: ifnull +8 -> 836
    //   831: aload 8
    //   833: invokevirtual 272	java/io/InputStream:close	()V
    //   836: iload 22
    //   838: ifne +24 -> 862
    //   841: iload 22
    //   843: sipush 200
    //   846: if_icmpge +16 -> 862
    //   849: iload 22
    //   851: sipush 399
    //   854: if_icmple +8 -> 862
    //   857: sipush 1011
    //   860: istore 22
    //   862: iload 22
    //   864: sipush 200
    //   867: if_icmpeq +47 -> 914
    //   870: ldc 92
    //   872: new 234	java/lang/StringBuilder
    //   875: dup
    //   876: ldc_w 274
    //   879: invokespecial 240	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   882: iload 22
    //   884: invokevirtual 277	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   887: ldc_w 279
    //   890: invokevirtual 247	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   893: aload_1
    //   894: invokevirtual 247	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   897: ldc_w 281
    //   900: invokevirtual 247	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   903: iload 5
    //   905: invokevirtual 277	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   908: invokevirtual 251	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   911: invokestatic 287	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   914: aload 9
    //   916: ifnull +8 -> 924
    //   919: aload 9
    //   921: invokevirtual 290	java/net/HttpURLConnection:disconnect	()V
    //   924: aload 6
    //   926: aload_1
    //   927: invokeinterface 183 2 0
    //   932: pop
    //   933: aload 6
    //   935: iload 22
    //   937: invokestatic 189	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   940: invokeinterface 183 2 0
    //   945: pop
    //   946: aload 6
    //   948: areturn
    //   949: astore 16
    //   951: aload 16
    //   953: ifnull +24 -> 977
    //   956: aload 16
    //   958: invokevirtual 297	java/io/IOException:getMessage	()Ljava/lang/String;
    //   961: invokestatic 177	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   964: ifeq +13 -> 977
    //   967: ldc 92
    //   969: aload 16
    //   971: invokevirtual 297	java/io/IOException:getMessage	()Ljava/lang/String;
    //   974: invokestatic 287	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   977: sipush 1009
    //   980: istore 17
    //   982: aload 7
    //   984: ifnull +8 -> 992
    //   987: aload 7
    //   989: invokevirtual 271	java/io/FileOutputStream:close	()V
    //   992: aload 8
    //   994: ifnull +8 -> 1002
    //   997: aload 8
    //   999: invokevirtual 272	java/io/InputStream:close	()V
    //   1002: iload 17
    //   1004: ifne +24 -> 1028
    //   1007: iload 17
    //   1009: sipush 200
    //   1012: if_icmpge +16 -> 1028
    //   1015: iload 17
    //   1017: sipush 399
    //   1020: if_icmple +8 -> 1028
    //   1023: sipush 1011
    //   1026: istore 17
    //   1028: iload 17
    //   1030: sipush 200
    //   1033: if_icmpeq +47 -> 1080
    //   1036: ldc 92
    //   1038: new 234	java/lang/StringBuilder
    //   1041: dup
    //   1042: ldc_w 274
    //   1045: invokespecial 240	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1048: iload 17
    //   1050: invokevirtual 277	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1053: ldc_w 279
    //   1056: invokevirtual 247	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1059: aload_1
    //   1060: invokevirtual 247	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1063: ldc_w 281
    //   1066: invokevirtual 247	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1069: iload 5
    //   1071: invokevirtual 277	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1074: invokevirtual 251	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1077: invokestatic 287	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1080: aload 9
    //   1082: ifnull +8 -> 1090
    //   1085: aload 9
    //   1087: invokevirtual 290	java/net/HttpURLConnection:disconnect	()V
    //   1090: aload 6
    //   1092: aload_1
    //   1093: invokeinterface 183 2 0
    //   1098: pop
    //   1099: aload 6
    //   1101: iload 17
    //   1103: invokestatic 189	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1106: invokeinterface 183 2 0
    //   1111: pop
    //   1112: aload 6
    //   1114: areturn
    //   1115: astore 12
    //   1117: aload 7
    //   1119: ifnull +8 -> 1127
    //   1122: aload 7
    //   1124: invokevirtual 271	java/io/FileOutputStream:close	()V
    //   1127: aload 8
    //   1129: ifnull +8 -> 1137
    //   1132: aload 8
    //   1134: invokevirtual 272	java/io/InputStream:close	()V
    //   1137: iload 10
    //   1139: ifne +24 -> 1163
    //   1142: iload 10
    //   1144: sipush 200
    //   1147: if_icmpge +16 -> 1163
    //   1150: iload 10
    //   1152: sipush 399
    //   1155: if_icmple +8 -> 1163
    //   1158: sipush 1011
    //   1161: istore 10
    //   1163: iload 10
    //   1165: sipush 200
    //   1168: if_icmpeq +47 -> 1215
    //   1171: ldc 92
    //   1173: new 234	java/lang/StringBuilder
    //   1176: dup
    //   1177: ldc_w 274
    //   1180: invokespecial 240	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1183: iload 10
    //   1185: invokevirtual 277	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1188: ldc_w 279
    //   1191: invokevirtual 247	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1194: aload_1
    //   1195: invokevirtual 247	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1198: ldc_w 281
    //   1201: invokevirtual 247	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1204: iload 5
    //   1206: invokevirtual 277	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1209: invokevirtual 251	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1212: invokestatic 287	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1215: aload 9
    //   1217: ifnull +8 -> 1225
    //   1220: aload 9
    //   1222: invokevirtual 290	java/net/HttpURLConnection:disconnect	()V
    //   1225: aload 6
    //   1227: aload_1
    //   1228: invokeinterface 183 2 0
    //   1233: pop
    //   1234: aload 6
    //   1236: iload 10
    //   1238: invokestatic 189	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1241: invokeinterface 183 2 0
    //   1246: pop
    //   1247: aload 12
    //   1249: athrow
    //   1250: astore 51
    //   1252: goto -1006 -> 246
    //   1255: astore 13
    //   1257: goto -120 -> 1137
    //   1260: astore 12
    //   1262: aconst_null
    //   1263: astore 7
    //   1265: aconst_null
    //   1266: astore 8
    //   1268: goto -151 -> 1117
    //   1271: astore 12
    //   1273: aload 43
    //   1275: astore 7
    //   1277: goto -160 -> 1117
    //   1280: astore 18
    //   1282: goto -280 -> 1002
    //   1285: astore 16
    //   1287: aconst_null
    //   1288: astore 7
    //   1290: aconst_null
    //   1291: astore 8
    //   1293: goto -342 -> 951
    //   1296: astore 16
    //   1298: aload 43
    //   1300: astore 7
    //   1302: goto -351 -> 951
    //   1305: astore 23
    //   1307: goto -471 -> 836
    //   1310: astore 58
    //   1312: aconst_null
    //   1313: astore 7
    //   1315: aconst_null
    //   1316: astore 8
    //   1318: goto -507 -> 811
    //   1321: astore 47
    //   1323: aload 43
    //   1325: astore 7
    //   1327: goto -516 -> 811
    //   1330: astore 28
    //   1332: goto -636 -> 696
    //   1335: astore 57
    //   1337: aconst_null
    //   1338: astore 7
    //   1340: aconst_null
    //   1341: astore 8
    //   1343: goto -672 -> 671
    //   1346: astore 46
    //   1348: aload 43
    //   1350: astore 7
    //   1352: goto -681 -> 671
    //   1355: astore 33
    //   1357: goto -801 -> 556
    //   1360: astore 56
    //   1362: aconst_null
    //   1363: astore 7
    //   1365: aconst_null
    //   1366: astore 8
    //   1368: goto -837 -> 531
    //   1371: astore 45
    //   1373: aload 43
    //   1375: astore 7
    //   1377: goto -846 -> 531
    //   1380: astore 38
    //   1382: goto -966 -> 416
    //   1385: astore 55
    //   1387: aconst_null
    //   1388: astore 7
    //   1390: aconst_null
    //   1391: astore 8
    //   1393: goto -1002 -> 391
    //   1396: astore 44
    //   1398: aload 43
    //   1400: astore 7
    //   1402: goto -1011 -> 391
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1405	0	this	DownloadManager
    //   0	1405	1	paramString1	String
    //   0	1405	2	paramString2	String
    //   0	1405	3	paramString3	String
    //   0	1405	4	paramString4	String
    //   0	1405	5	paramInt	int
    //   7	1228	6	localArrayList	ArrayList
    //   10	1391	7	localObject1	Object
    //   13	1379	8	localInputStream	java.io.InputStream
    //   16	1205	9	localHttpURLConnection	java.net.HttpURLConnection
    //   19	1218	10	i	int
    //   62	9	11	localURL	java.net.URL
    //   1115	133	12	localObject2	Object
    //   1260	1	12	localObject3	Object
    //   1271	1	12	localObject4	Object
    //   1255	1	13	localIOException1	java.io.IOException
    //   949	21	16	localIOException2	java.io.IOException
    //   1285	1	16	localIOException3	java.io.IOException
    //   1296	1	16	localIOException4	java.io.IOException
    //   980	122	17	j	int
    //   1280	1	18	localIOException5	java.io.IOException
    //   809	1	21	localFileNotFoundException1	java.io.FileNotFoundException
    //   814	122	22	k	int
    //   1305	1	23	localIOException6	java.io.IOException
    //   669	1	26	localSocketTimeoutException1	java.net.SocketTimeoutException
    //   674	122	27	m	int
    //   1330	1	28	localIOException7	java.io.IOException
    //   529	1	31	localURISyntaxException1	java.net.URISyntaxException
    //   534	122	32	n	int
    //   1355	1	33	localIOException8	java.io.IOException
    //   389	1	36	localMalformedURLException1	java.net.MalformedURLException
    //   394	122	37	i1	int
    //   1380	1	38	localIOException9	java.io.IOException
    //   171	7	42	localFile	java.io.File
    //   182	1217	43	localFileOutputStream	java.io.FileOutputStream
    //   1396	1	44	localMalformedURLException2	java.net.MalformedURLException
    //   1371	1	45	localURISyntaxException2	java.net.URISyntaxException
    //   1346	1	46	localSocketTimeoutException2	java.net.SocketTimeoutException
    //   1321	1	47	localFileNotFoundException2	java.io.FileNotFoundException
    //   196	166	48	arrayOfByte	byte[]
    //   199	176	49	i2	int
    //   379	1	50	localIOException10	java.io.IOException
    //   1250	1	51	localIOException11	java.io.IOException
    //   208	166	54	i3	int
    //   1385	1	55	localMalformedURLException3	java.net.MalformedURLException
    //   1360	1	56	localURISyntaxException3	java.net.URISyntaxException
    //   1335	1	57	localSocketTimeoutException3	java.net.SocketTimeoutException
    //   1310	1	58	localFileNotFoundException3	java.io.FileNotFoundException
    // Exception table:
    //   from	to	target	type
    //   201	210	379	java/io/IOException
    //   359	369	379	java/io/IOException
    //   54	140	389	java/net/MalformedURLException
    //   140	173	389	java/net/MalformedURLException
    //   54	140	529	java/net/URISyntaxException
    //   140	173	529	java/net/URISyntaxException
    //   54	140	669	java/net/SocketTimeoutException
    //   140	173	669	java/net/SocketTimeoutException
    //   54	140	809	java/io/FileNotFoundException
    //   140	173	809	java/io/FileNotFoundException
    //   54	140	949	java/io/IOException
    //   140	173	949	java/io/IOException
    //   54	140	1115	finally
    //   140	173	1115	finally
    //   956	977	1115	finally
    //   231	236	1250	java/io/IOException
    //   241	246	1250	java/io/IOException
    //   1122	1127	1255	java/io/IOException
    //   1132	1137	1255	java/io/IOException
    //   173	184	1260	finally
    //   184	198	1271	finally
    //   201	210	1271	finally
    //   359	369	1271	finally
    //   987	992	1280	java/io/IOException
    //   997	1002	1280	java/io/IOException
    //   173	184	1285	java/io/IOException
    //   184	198	1296	java/io/IOException
    //   821	826	1305	java/io/IOException
    //   831	836	1305	java/io/IOException
    //   173	184	1310	java/io/FileNotFoundException
    //   184	198	1321	java/io/FileNotFoundException
    //   201	210	1321	java/io/FileNotFoundException
    //   359	369	1321	java/io/FileNotFoundException
    //   681	686	1330	java/io/IOException
    //   691	696	1330	java/io/IOException
    //   173	184	1335	java/net/SocketTimeoutException
    //   184	198	1346	java/net/SocketTimeoutException
    //   201	210	1346	java/net/SocketTimeoutException
    //   359	369	1346	java/net/SocketTimeoutException
    //   541	546	1355	java/io/IOException
    //   551	556	1355	java/io/IOException
    //   173	184	1360	java/net/URISyntaxException
    //   184	198	1371	java/net/URISyntaxException
    //   201	210	1371	java/net/URISyntaxException
    //   359	369	1371	java/net/URISyntaxException
    //   401	406	1380	java/io/IOException
    //   411	416	1380	java/io/IOException
    //   173	184	1385	java/net/MalformedURLException
    //   184	198	1396	java/net/MalformedURLException
    //   201	210	1396	java/net/MalformedURLException
    //   359	369	1396	java/net/MalformedURLException
  }
  
  public void downloadFile(SSAFile paramSSAFile)
  {
    new Thread(new SingleFileWorkerThread(paramSSAFile)).start();
  }
  
  public void downloadMobileControllerFile(SSAFile paramSSAFile)
  {
    this.mMobileControllerThread = new Thread(new SingleFileWorkerThread(paramSSAFile));
    this.mMobileControllerThread.start();
  }
  
  public boolean isMobileControllerThreadLive()
  {
    if (this.mMobileControllerThread != null) {
      return this.mMobileControllerThread.isAlive();
    }
    return false;
  }
  
  public void release()
  {
    mDownloadManager = null;
  }
  
  public void setOnPreCacheCompletion(OnPreCacheCompletion paramOnPreCacheCompletion)
  {
    this.mListener = paramOnPreCacheCompletion;
  }
  
  public class FileWorkerThread
    implements Callable<List<Object>>
  {
    private long mConnectionRetries;
    private String mDirectory;
    private String mFile;
    private String mFolderName;
    private String mLink;
    
    public FileWorkerThread(String paramString1, String paramString2, String paramString3, long paramLong, String paramString4)
    {
      this.mLink = paramString1;
      this.mDirectory = paramString2;
      this.mFile = paramString3;
      this.mConnectionRetries = paramLong;
      this.mFolderName = paramString4;
    }
    
    public List<Object> call()
    {
      Object localObject = null;
      if (this.mConnectionRetries == 0L) {
        this.mConnectionRetries = 1L;
      }
      for (int i = 0;; i++)
      {
        if (i >= this.mConnectionRetries) {
          return localObject;
        }
        new ArrayList();
        localObject = DownloadManager.this.downloadContent(this.mLink, this.mDirectory, this.mFile, this.mFolderName, i);
        int j = ((Integer)((List)localObject).get(1)).intValue();
        if ((j != 1008) && (j != 1009)) {
          return localObject;
        }
      }
    }
  }
  
  public static abstract interface OnPreCacheCompletion
  {
    public abstract void onFileDownloadFail(SSAFile paramSSAFile);
    
    public abstract void onFileDownloadSuccess(SSAFile paramSSAFile);
  }
  
  private class SingleFileWorkerThread
    implements Runnable
  {
    private long mConnectionRetries;
    private String mFile;
    private String mFileName;
    private String mFolderName;
    private String mPath;
    
    public SingleFileWorkerThread(SSAFile paramSSAFile)
    {
      this.mFile = paramSSAFile.getFile();
      this.mPath = paramSSAFile.getPath();
      this.mFileName = SDKUtils.getFileName(this.mFile);
      this.mFolderName = DownloadManager.this.mCacheManager.makeDir(this.mPath);
      this.mConnectionRetries = Long.parseLong(DownloadManager.this.mCacheManager.getConnectionRetries());
    }
    
    /* Error */
    public void run()
    {
      // Byte code:
      //   0: new 24	com/supersonicads/sdk/data/SSAFile
      //   3: dup
      //   4: aload_0
      //   5: getfield 43	com/supersonicads/sdk/precache/DownloadManager$SingleFileWorkerThread:mFileName	Ljava/lang/String;
      //   8: aload_0
      //   9: getfield 35	com/supersonicads/sdk/precache/DownloadManager$SingleFileWorkerThread:mPath	Ljava/lang/String;
      //   12: invokespecial 75	com/supersonicads/sdk/data/SSAFile:<init>	(Ljava/lang/String;Ljava/lang/String;)V
      //   15: astore_1
      //   16: new 77	android/os/Message
      //   19: dup
      //   20: invokespecial 78	android/os/Message:<init>	()V
      //   23: astore_2
      //   24: aload_2
      //   25: aload_1
      //   26: putfield 82	android/os/Message:obj	Ljava/lang/Object;
      //   29: aload_0
      //   30: getfield 56	com/supersonicads/sdk/precache/DownloadManager$SingleFileWorkerThread:mFolderName	Ljava/lang/String;
      //   33: ifnonnull +29 -> 62
      //   36: aload_2
      //   37: sipush 1017
      //   40: putfield 86	android/os/Message:what	I
      //   43: aload_1
      //   44: ldc 88
      //   46: invokevirtual 92	com/supersonicads/sdk/data/SSAFile:setErrMsg	(Ljava/lang/String;)V
      //   49: aload_0
      //   50: getfield 19	com/supersonicads/sdk/precache/DownloadManager$SingleFileWorkerThread:this$0	Lcom/supersonicads/sdk/precache/DownloadManager;
      //   53: invokestatic 96	com/supersonicads/sdk/precache/DownloadManager:access$2	(Lcom/supersonicads/sdk/precache/DownloadManager;)Landroid/os/Handler;
      //   56: aload_2
      //   57: invokevirtual 102	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
      //   60: pop
      //   61: return
      //   62: iconst_1
      //   63: invokestatic 108	java/util/concurrent/Executors:newFixedThreadPool	(I)Ljava/util/concurrent/ExecutorService;
      //   66: astore_3
      //   67: new 110	java/util/concurrent/ExecutorCompletionService
      //   70: dup
      //   71: aload_3
      //   72: invokespecial 113	java/util/concurrent/ExecutorCompletionService:<init>	(Ljava/util/concurrent/Executor;)V
      //   75: astore 4
      //   77: aload 4
      //   79: new 115	com/supersonicads/sdk/precache/DownloadManager$FileWorkerThread
      //   82: dup
      //   83: aload_0
      //   84: getfield 19	com/supersonicads/sdk/precache/DownloadManager$SingleFileWorkerThread:this$0	Lcom/supersonicads/sdk/precache/DownloadManager;
      //   87: aload_0
      //   88: getfield 30	com/supersonicads/sdk/precache/DownloadManager$SingleFileWorkerThread:mFile	Ljava/lang/String;
      //   91: aload_0
      //   92: getfield 56	com/supersonicads/sdk/precache/DownloadManager$SingleFileWorkerThread:mFolderName	Ljava/lang/String;
      //   95: aload_0
      //   96: getfield 43	com/supersonicads/sdk/precache/DownloadManager$SingleFileWorkerThread:mFileName	Ljava/lang/String;
      //   99: aload_0
      //   100: getfield 67	com/supersonicads/sdk/precache/DownloadManager$SingleFileWorkerThread:mConnectionRetries	J
      //   103: aload_0
      //   104: getfield 35	com/supersonicads/sdk/precache/DownloadManager$SingleFileWorkerThread:mPath	Ljava/lang/String;
      //   107: invokespecial 118	com/supersonicads/sdk/precache/DownloadManager$FileWorkerThread:<init>	(Lcom/supersonicads/sdk/precache/DownloadManager;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;)V
      //   110: invokeinterface 124 2 0
      //   115: pop
      //   116: aload 4
      //   118: invokeinterface 128 1 0
      //   123: invokeinterface 134 1 0
      //   128: checkcast 136	java/util/List
      //   131: astore 16
      //   133: aload 16
      //   135: iconst_0
      //   136: invokeinterface 139 2 0
      //   141: checkcast 141	java/lang/String
      //   144: pop
      //   145: aload 16
      //   147: iconst_1
      //   148: invokeinterface 139 2 0
      //   153: checkcast 143	java/lang/Integer
      //   156: invokevirtual 147	java/lang/Integer:intValue	()I
      //   159: istore 18
      //   161: iload 18
      //   163: istore 7
      //   165: aload_3
      //   166: invokeinterface 152 1 0
      //   171: aload_3
      //   172: ldc2_w 153
      //   175: getstatic 160	java/util/concurrent/TimeUnit:NANOSECONDS	Ljava/util/concurrent/TimeUnit;
      //   178: invokeinterface 164 4 0
      //   183: pop
      //   184: iload 7
      //   186: lookupswitch	default:+90->276, 200:+91->277, 404:+139->325, 1004:+139->325, 1005:+139->325, 1006:+139->325, 1008:+172->358, 1009:+172->358, 1010:+139->325, 1011:+139->325, 1018:+139->325
      //   277: aload_2
      //   278: sipush 1016
      //   281: putfield 86	android/os/Message:what	I
      //   284: aload_0
      //   285: getfield 19	com/supersonicads/sdk/precache/DownloadManager$SingleFileWorkerThread:this$0	Lcom/supersonicads/sdk/precache/DownloadManager;
      //   288: invokestatic 96	com/supersonicads/sdk/precache/DownloadManager:access$2	(Lcom/supersonicads/sdk/precache/DownloadManager;)Landroid/os/Handler;
      //   291: aload_2
      //   292: invokevirtual 102	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
      //   295: pop
      //   296: return
      //   297: astore 15
      //   299: invokestatic 170	java/lang/Thread:currentThread	()Ljava/lang/Thread;
      //   302: invokevirtual 173	java/lang/Thread:interrupt	()V
      //   305: iconst_0
      //   306: istore 7
      //   308: goto -143 -> 165
      //   311: astore 6
      //   313: invokestatic 170	java/lang/Thread:currentThread	()Ljava/lang/Thread;
      //   316: invokevirtual 173	java/lang/Thread:interrupt	()V
      //   319: iconst_0
      //   320: istore 7
      //   322: goto -157 -> 165
      //   325: aload_2
      //   326: sipush 1017
      //   329: putfield 86	android/os/Message:what	I
      //   332: aload_1
      //   333: ldc 175
      //   335: invokevirtual 92	com/supersonicads/sdk/data/SSAFile:setErrMsg	(Ljava/lang/String;)V
      //   338: aload_0
      //   339: getfield 19	com/supersonicads/sdk/precache/DownloadManager$SingleFileWorkerThread:this$0	Lcom/supersonicads/sdk/precache/DownloadManager;
      //   342: invokestatic 96	com/supersonicads/sdk/precache/DownloadManager:access$2	(Lcom/supersonicads/sdk/precache/DownloadManager;)Landroid/os/Handler;
      //   345: aload_2
      //   346: invokevirtual 102	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
      //   349: pop
      //   350: aload_3
      //   351: invokeinterface 179 1 0
      //   356: pop
      //   357: return
      //   358: aload_2
      //   359: sipush 1017
      //   362: putfield 86	android/os/Message:what	I
      //   365: aload_1
      //   366: ldc 181
      //   368: invokevirtual 92	com/supersonicads/sdk/data/SSAFile:setErrMsg	(Ljava/lang/String;)V
      //   371: aload_0
      //   372: getfield 19	com/supersonicads/sdk/precache/DownloadManager$SingleFileWorkerThread:this$0	Lcom/supersonicads/sdk/precache/DownloadManager;
      //   375: invokestatic 96	com/supersonicads/sdk/precache/DownloadManager:access$2	(Lcom/supersonicads/sdk/precache/DownloadManager;)Landroid/os/Handler;
      //   378: aload_2
      //   379: invokevirtual 102	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
      //   382: pop
      //   383: aload_3
      //   384: invokeinterface 179 1 0
      //   389: pop
      //   390: return
      //   391: astore 8
      //   393: goto -209 -> 184
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	396	0	this	SingleFileWorkerThread
      //   15	351	1	localSSAFile	SSAFile
      //   23	356	2	localMessage	Message
      //   66	318	3	localExecutorService	java.util.concurrent.ExecutorService
      //   75	42	4	localExecutorCompletionService	java.util.concurrent.ExecutorCompletionService
      //   311	1	6	localExecutionException	java.util.concurrent.ExecutionException
      //   163	158	7	i	int
      //   391	1	8	localInterruptedException1	java.lang.InterruptedException
      //   297	1	15	localInterruptedException2	java.lang.InterruptedException
      //   131	15	16	localList	List
      //   159	3	18	j	int
      // Exception table:
      //   from	to	target	type
      //   116	161	297	java/lang/InterruptedException
      //   116	161	311	java/util/concurrent/ExecutionException
      //   171	184	391	java/lang/InterruptedException
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.supersonicads.sdk.precache.DownloadManager
 * JD-Core Version:    0.7.0.1
 */