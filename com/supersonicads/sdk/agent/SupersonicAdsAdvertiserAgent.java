package com.supersonicads.sdk.agent;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.supersonicads.sdk.SSAAdvertiserTest;
import com.supersonicads.sdk.data.SSAObj;
import com.supersonicads.sdk.utils.Logger;
import com.supersonicads.sdk.utils.SDKUtils;
import java.net.MalformedURLException;
import java.net.URL;

public class SupersonicAdsAdvertiserAgent
  implements SSAAdvertiserTest
{
  private static final String BUNDLE_ID = "bundleId";
  private static final String DEVICE_IDS = "deviceIds";
  private static final String DOMAIN = "/campaigns/onLoad?";
  private static String PACKAGE_NAME = null;
  private static String SERVICE_HOST_NAME;
  private static int SERVICE_PORT = 0;
  private static String SERVICE_PROTOCOL = "http";
  private static final String SIGNATURE = "signature";
  private static final String TAG = "SupersonicAdsAdvertiserAgent";
  private static String TIME_API;
  public static SupersonicAdsAdvertiserAgent sInstance;
  private final String IS_REPORTED = "is_reported";
  private final String REPORT_APP_STARTED = "report_app_started";
  private SharedPreferences mSharedPreferences;
  
  static
  {
    SERVICE_HOST_NAME = "www.supersonicads.com";
    SERVICE_PORT = 80;
    TIME_API = "http://www.supersonicads.com/timestamp.php";
  }
  
  public static SupersonicAdsAdvertiserAgent getInstance()
  {
    try
    {
      Logger.i("SupersonicAdsAdvertiserAgent", "getInstance()");
      if (sInstance == null) {
        sInstance = new SupersonicAdsAdvertiserAgent();
      }
      SupersonicAdsAdvertiserAgent localSupersonicAdsAdvertiserAgent = sInstance;
      return localSupersonicAdsAdvertiserAgent;
    }
    finally {}
  }
  
  private String getRequestParameters(Context paramContext)
  {
    StringBuilder localStringBuilder1 = new StringBuilder();
    String str1;
    String str2;
    if (TextUtils.isEmpty(PACKAGE_NAME))
    {
      str1 = SDKUtils.getPackageName(paramContext);
      if (!TextUtils.isEmpty(str1)) {
        localStringBuilder1.append("&").append("bundleId").append("=").append(SDKUtils.encodeString(str1));
      }
      SDKUtils.loadGoogleAdvertiserInfo(paramContext);
      str2 = SDKUtils.getAdvertiserId();
      boolean bool = SDKUtils.isLimitAdTrackingEnabled();
      if (TextUtils.isEmpty(str2)) {
        break label234;
      }
      localStringBuilder1.append("&").append("deviceIds").append(SDKUtils.encodeString("[")).append(SDKUtils.encodeString("AID")).append(SDKUtils.encodeString("]")).append("=").append(SDKUtils.encodeString(str2));
      localStringBuilder1.append("&").append(SDKUtils.encodeString("isLimitAdTrackingEnabled")).append("=").append(SDKUtils.encodeString(Boolean.toString(bool)));
    }
    for (;;)
    {
      StringBuilder localStringBuilder2 = new StringBuilder();
      localStringBuilder2.append(str1);
      localStringBuilder2.append(str2);
      localStringBuilder2.append(getUTCTimeStamp(paramContext));
      String str3 = SDKUtils.getMD5(localStringBuilder2.toString());
      localStringBuilder1.append("&").append("signature").append("=").append(str3);
      return localStringBuilder1.toString();
      str1 = PACKAGE_NAME;
      break;
      label234:
      str2 = "";
    }
  }
  
  private int getUTCTimeStamp(Context paramContext)
  {
    try
    {
      Result localResult = performRequest(new URL(TIME_API), paramContext);
      if (localResult.getResponseCode() == 200)
      {
        SSAObj localSSAObj = new SSAObj(localResult.getResponseString());
        if (localSSAObj.containsKey("timestamp"))
        {
          int i = Integer.parseInt(localSSAObj.getString("timestamp"));
          int j = i % 60;
          return i - j;
        }
      }
    }
    catch (MalformedURLException localMalformedURLException) {}
    return 0;
  }
  
  public void clearReportApp(Context paramContext)
  {
    setReportAppStarted(false, paramContext);
  }
  
  public boolean getReportAppStarted(Context paramContext)
  {
    this.mSharedPreferences = paramContext.getSharedPreferences("report_app_started", 0);
    return this.mSharedPreferences.getBoolean("is_reported", false);
  }
  
  /* Error */
  public Result performRequest(URL paramURL, Context paramContext)
  {
    // Byte code:
    //   0: new 165	com/supersonicads/sdk/agent/SupersonicAdsAdvertiserAgent$Result
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 221	com/supersonicads/sdk/agent/SupersonicAdsAdvertiserAgent$Result:<init>	(Lcom/supersonicads/sdk/agent/SupersonicAdsAdvertiserAgent;)V
    //   8: astore_3
    //   9: aconst_null
    //   10: astore 4
    //   12: iconst_0
    //   13: istore 5
    //   15: aconst_null
    //   16: astore 6
    //   18: aconst_null
    //   19: astore 7
    //   21: aload_1
    //   22: invokevirtual 225	java/net/URL:toURI	()Ljava/net/URI;
    //   25: pop
    //   26: aload_1
    //   27: invokevirtual 229	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   30: checkcast 231	java/net/HttpURLConnection
    //   33: astore 4
    //   35: aload 4
    //   37: ldc 233
    //   39: invokevirtual 236	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
    //   42: aload 4
    //   44: sipush 5000
    //   47: invokevirtual 240	java/net/HttpURLConnection:setConnectTimeout	(I)V
    //   50: aload 4
    //   52: sipush 5000
    //   55: invokevirtual 243	java/net/HttpURLConnection:setReadTimeout	(I)V
    //   58: aload 4
    //   60: invokevirtual 246	java/net/HttpURLConnection:connect	()V
    //   63: aload 4
    //   65: invokevirtual 247	java/net/HttpURLConnection:getResponseCode	()I
    //   68: istore 5
    //   70: aload 4
    //   72: invokevirtual 251	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   75: astore 6
    //   77: ldc 252
    //   79: newarray byte
    //   81: pop
    //   82: new 90	java/lang/StringBuilder
    //   85: dup
    //   86: invokespecial 91	java/lang/StringBuilder:<init>	()V
    //   89: astore 23
    //   91: new 254	java/io/BufferedReader
    //   94: dup
    //   95: new 256	java/io/InputStreamReader
    //   98: dup
    //   99: aload 6
    //   101: invokespecial 259	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   104: invokespecial 262	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   107: astore 24
    //   109: aload 24
    //   111: invokevirtual 265	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   114: astore 30
    //   116: aload 30
    //   118: ifnonnull +84 -> 202
    //   121: aload 6
    //   123: ifnull +8 -> 131
    //   126: aload 6
    //   128: invokevirtual 270	java/io/InputStream:close	()V
    //   131: iload 5
    //   133: sipush 200
    //   136: if_icmpeq +36 -> 172
    //   139: ldc 28
    //   141: new 90	java/lang/StringBuilder
    //   144: dup
    //   145: ldc_w 272
    //   148: invokespecial 273	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   151: iload 5
    //   153: invokevirtual 145	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   156: ldc_w 275
    //   159: invokevirtual 108	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   162: aload_1
    //   163: invokevirtual 278	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   166: invokevirtual 147	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   169: invokestatic 85	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   172: aload 4
    //   174: ifnull +8 -> 182
    //   177: aload 4
    //   179: invokevirtual 281	java/net/HttpURLConnection:disconnect	()V
    //   182: aload_3
    //   183: iload 5
    //   185: invokevirtual 284	com/supersonicads/sdk/agent/SupersonicAdsAdvertiserAgent$Result:setResponseCode	(I)V
    //   188: aload 23
    //   190: ifnonnull +612 -> 802
    //   193: aload_3
    //   194: ldc_w 286
    //   197: invokevirtual 289	com/supersonicads/sdk/agent/SupersonicAdsAdvertiserAgent$Result:setResponseString	(Ljava/lang/String;)V
    //   200: aload_3
    //   201: areturn
    //   202: aload 23
    //   204: new 90	java/lang/StringBuilder
    //   207: dup
    //   208: aload 30
    //   210: invokestatic 295	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   213: invokespecial 273	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   216: ldc_w 297
    //   219: invokevirtual 108	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   222: invokevirtual 147	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   225: invokevirtual 108	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   228: pop
    //   229: goto -120 -> 109
    //   232: astore 29
    //   234: aload 23
    //   236: astore 9
    //   238: aload 6
    //   240: ifnull +8 -> 248
    //   243: aload 6
    //   245: invokevirtual 270	java/io/InputStream:close	()V
    //   248: iload 5
    //   250: sipush 200
    //   253: if_icmpeq +36 -> 289
    //   256: ldc 28
    //   258: new 90	java/lang/StringBuilder
    //   261: dup
    //   262: ldc_w 272
    //   265: invokespecial 273	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   268: iload 5
    //   270: invokevirtual 145	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   273: ldc_w 275
    //   276: invokevirtual 108	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   279: aload_1
    //   280: invokevirtual 278	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   283: invokevirtual 147	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   286: invokestatic 85	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   289: aload 4
    //   291: ifnull +8 -> 299
    //   294: aload 4
    //   296: invokevirtual 281	java/net/HttpURLConnection:disconnect	()V
    //   299: aload_3
    //   300: iload 5
    //   302: invokevirtual 284	com/supersonicads/sdk/agent/SupersonicAdsAdvertiserAgent$Result:setResponseCode	(I)V
    //   305: aload 9
    //   307: ifnonnull +12 -> 319
    //   310: aload_3
    //   311: ldc_w 286
    //   314: invokevirtual 289	com/supersonicads/sdk/agent/SupersonicAdsAdvertiserAgent$Result:setResponseString	(Ljava/lang/String;)V
    //   317: aload_3
    //   318: areturn
    //   319: aload_3
    //   320: aload 9
    //   322: invokevirtual 147	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   325: invokevirtual 289	com/supersonicads/sdk/agent/SupersonicAdsAdvertiserAgent$Result:setResponseString	(Ljava/lang/String;)V
    //   328: aload_3
    //   329: areturn
    //   330: astore 19
    //   332: aload 6
    //   334: ifnull +8 -> 342
    //   337: aload 6
    //   339: invokevirtual 270	java/io/InputStream:close	()V
    //   342: iload 5
    //   344: sipush 200
    //   347: if_icmpeq +36 -> 383
    //   350: ldc 28
    //   352: new 90	java/lang/StringBuilder
    //   355: dup
    //   356: ldc_w 272
    //   359: invokespecial 273	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   362: iload 5
    //   364: invokevirtual 145	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   367: ldc_w 275
    //   370: invokevirtual 108	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   373: aload_1
    //   374: invokevirtual 278	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   377: invokevirtual 147	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   380: invokestatic 85	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   383: aload 4
    //   385: ifnull +8 -> 393
    //   388: aload 4
    //   390: invokevirtual 281	java/net/HttpURLConnection:disconnect	()V
    //   393: aload_3
    //   394: iload 5
    //   396: invokevirtual 284	com/supersonicads/sdk/agent/SupersonicAdsAdvertiserAgent$Result:setResponseCode	(I)V
    //   399: aload 7
    //   401: ifnonnull +12 -> 413
    //   404: aload_3
    //   405: ldc_w 286
    //   408: invokevirtual 289	com/supersonicads/sdk/agent/SupersonicAdsAdvertiserAgent$Result:setResponseString	(Ljava/lang/String;)V
    //   411: aload_3
    //   412: areturn
    //   413: aload_3
    //   414: aload 7
    //   416: invokevirtual 147	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   419: invokevirtual 289	com/supersonicads/sdk/agent/SupersonicAdsAdvertiserAgent$Result:setResponseString	(Ljava/lang/String;)V
    //   422: aload_3
    //   423: areturn
    //   424: astore 17
    //   426: aload 6
    //   428: ifnull +8 -> 436
    //   431: aload 6
    //   433: invokevirtual 270	java/io/InputStream:close	()V
    //   436: iload 5
    //   438: sipush 200
    //   441: if_icmpeq +36 -> 477
    //   444: ldc 28
    //   446: new 90	java/lang/StringBuilder
    //   449: dup
    //   450: ldc_w 272
    //   453: invokespecial 273	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   456: iload 5
    //   458: invokevirtual 145	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   461: ldc_w 275
    //   464: invokevirtual 108	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   467: aload_1
    //   468: invokevirtual 278	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   471: invokevirtual 147	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   474: invokestatic 85	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   477: aload 4
    //   479: ifnull +8 -> 487
    //   482: aload 4
    //   484: invokevirtual 281	java/net/HttpURLConnection:disconnect	()V
    //   487: aload_3
    //   488: iload 5
    //   490: invokevirtual 284	com/supersonicads/sdk/agent/SupersonicAdsAdvertiserAgent$Result:setResponseCode	(I)V
    //   493: aload 7
    //   495: ifnonnull +12 -> 507
    //   498: aload_3
    //   499: ldc_w 286
    //   502: invokevirtual 289	com/supersonicads/sdk/agent/SupersonicAdsAdvertiserAgent$Result:setResponseString	(Ljava/lang/String;)V
    //   505: aload_3
    //   506: areturn
    //   507: aload_3
    //   508: aload 7
    //   510: invokevirtual 147	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   513: invokevirtual 289	com/supersonicads/sdk/agent/SupersonicAdsAdvertiserAgent$Result:setResponseString	(Ljava/lang/String;)V
    //   516: aload_3
    //   517: areturn
    //   518: astore 15
    //   520: aload 6
    //   522: ifnull +8 -> 530
    //   525: aload 6
    //   527: invokevirtual 270	java/io/InputStream:close	()V
    //   530: iload 5
    //   532: sipush 200
    //   535: if_icmpeq +36 -> 571
    //   538: ldc 28
    //   540: new 90	java/lang/StringBuilder
    //   543: dup
    //   544: ldc_w 272
    //   547: invokespecial 273	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   550: iload 5
    //   552: invokevirtual 145	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   555: ldc_w 275
    //   558: invokevirtual 108	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   561: aload_1
    //   562: invokevirtual 278	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   565: invokevirtual 147	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   568: invokestatic 85	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   571: aload 4
    //   573: ifnull +8 -> 581
    //   576: aload 4
    //   578: invokevirtual 281	java/net/HttpURLConnection:disconnect	()V
    //   581: aload_3
    //   582: iload 5
    //   584: invokevirtual 284	com/supersonicads/sdk/agent/SupersonicAdsAdvertiserAgent$Result:setResponseCode	(I)V
    //   587: aload 7
    //   589: ifnonnull +12 -> 601
    //   592: aload_3
    //   593: ldc_w 286
    //   596: invokevirtual 289	com/supersonicads/sdk/agent/SupersonicAdsAdvertiserAgent$Result:setResponseString	(Ljava/lang/String;)V
    //   599: aload_3
    //   600: areturn
    //   601: aload_3
    //   602: aload 7
    //   604: invokevirtual 147	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   607: invokevirtual 289	com/supersonicads/sdk/agent/SupersonicAdsAdvertiserAgent$Result:setResponseString	(Ljava/lang/String;)V
    //   610: aload_3
    //   611: areturn
    //   612: astore 13
    //   614: aload 6
    //   616: ifnull +8 -> 624
    //   619: aload 6
    //   621: invokevirtual 270	java/io/InputStream:close	()V
    //   624: iload 5
    //   626: sipush 200
    //   629: if_icmpeq +36 -> 665
    //   632: ldc 28
    //   634: new 90	java/lang/StringBuilder
    //   637: dup
    //   638: ldc_w 272
    //   641: invokespecial 273	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   644: iload 5
    //   646: invokevirtual 145	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   649: ldc_w 275
    //   652: invokevirtual 108	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   655: aload_1
    //   656: invokevirtual 278	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   659: invokevirtual 147	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   662: invokestatic 85	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   665: aload 4
    //   667: ifnull +8 -> 675
    //   670: aload 4
    //   672: invokevirtual 281	java/net/HttpURLConnection:disconnect	()V
    //   675: aload_3
    //   676: iload 5
    //   678: invokevirtual 284	com/supersonicads/sdk/agent/SupersonicAdsAdvertiserAgent$Result:setResponseCode	(I)V
    //   681: aload 7
    //   683: ifnonnull +12 -> 695
    //   686: aload_3
    //   687: ldc_w 286
    //   690: invokevirtual 289	com/supersonicads/sdk/agent/SupersonicAdsAdvertiserAgent$Result:setResponseString	(Ljava/lang/String;)V
    //   693: aload_3
    //   694: areturn
    //   695: aload_3
    //   696: aload 7
    //   698: invokevirtual 147	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   701: invokevirtual 289	com/supersonicads/sdk/agent/SupersonicAdsAdvertiserAgent$Result:setResponseString	(Ljava/lang/String;)V
    //   704: aload_3
    //   705: areturn
    //   706: astore 11
    //   708: aload 6
    //   710: ifnull +8 -> 718
    //   713: aload 6
    //   715: invokevirtual 270	java/io/InputStream:close	()V
    //   718: iload 5
    //   720: sipush 200
    //   723: if_icmpeq +36 -> 759
    //   726: ldc 28
    //   728: new 90	java/lang/StringBuilder
    //   731: dup
    //   732: ldc_w 272
    //   735: invokespecial 273	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   738: iload 5
    //   740: invokevirtual 145	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   743: ldc_w 275
    //   746: invokevirtual 108	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   749: aload_1
    //   750: invokevirtual 278	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   753: invokevirtual 147	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   756: invokestatic 85	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   759: aload 4
    //   761: ifnull +8 -> 769
    //   764: aload 4
    //   766: invokevirtual 281	java/net/HttpURLConnection:disconnect	()V
    //   769: aload_3
    //   770: iload 5
    //   772: invokevirtual 284	com/supersonicads/sdk/agent/SupersonicAdsAdvertiserAgent$Result:setResponseCode	(I)V
    //   775: aload 7
    //   777: ifnonnull +13 -> 790
    //   780: aload_3
    //   781: ldc_w 286
    //   784: invokevirtual 289	com/supersonicads/sdk/agent/SupersonicAdsAdvertiserAgent$Result:setResponseString	(Ljava/lang/String;)V
    //   787: aload 11
    //   789: athrow
    //   790: aload_3
    //   791: aload 7
    //   793: invokevirtual 147	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   796: invokevirtual 289	com/supersonicads/sdk/agent/SupersonicAdsAdvertiserAgent$Result:setResponseString	(Ljava/lang/String;)V
    //   799: goto -12 -> 787
    //   802: aload_3
    //   803: aload 23
    //   805: invokevirtual 147	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   808: invokevirtual 289	com/supersonicads/sdk/agent/SupersonicAdsAdvertiserAgent$Result:setResponseString	(Ljava/lang/String;)V
    //   811: aload_3
    //   812: areturn
    //   813: astore 10
    //   815: goto -567 -> 248
    //   818: astore 20
    //   820: goto -478 -> 342
    //   823: astore 18
    //   825: goto -389 -> 436
    //   828: astore 16
    //   830: goto -300 -> 530
    //   833: astore 14
    //   835: goto -211 -> 624
    //   838: astore 12
    //   840: goto -122 -> 718
    //   843: astore 32
    //   845: goto -714 -> 131
    //   848: astore 11
    //   850: aload 23
    //   852: astore 7
    //   854: goto -146 -> 708
    //   857: astore 28
    //   859: aload 23
    //   861: astore 7
    //   863: goto -249 -> 614
    //   866: astore 27
    //   868: aload 23
    //   870: astore 7
    //   872: goto -352 -> 520
    //   875: astore 26
    //   877: aload 23
    //   879: astore 7
    //   881: goto -455 -> 426
    //   884: astore 25
    //   886: aload 23
    //   888: astore 7
    //   890: goto -558 -> 332
    //   893: astore 8
    //   895: aconst_null
    //   896: astore 9
    //   898: goto -660 -> 238
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	901	0	this	SupersonicAdsAdvertiserAgent
    //   0	901	1	paramURL	URL
    //   0	901	2	paramContext	Context
    //   8	804	3	localResult	Result
    //   10	755	4	localHttpURLConnection	java.net.HttpURLConnection
    //   13	758	5	i	int
    //   16	698	6	localInputStream	java.io.InputStream
    //   19	870	7	localObject1	Object
    //   893	1	8	localMalformedURLException1	MalformedURLException
    //   236	661	9	localStringBuilder1	StringBuilder
    //   813	1	10	localIOException1	java.io.IOException
    //   706	82	11	localObject2	Object
    //   848	1	11	localObject3	Object
    //   838	1	12	localIOException2	java.io.IOException
    //   612	1	13	localIOException3	java.io.IOException
    //   833	1	14	localIOException4	java.io.IOException
    //   518	1	15	localFileNotFoundException1	java.io.FileNotFoundException
    //   828	1	16	localIOException5	java.io.IOException
    //   424	1	17	localSocketTimeoutException1	java.net.SocketTimeoutException
    //   823	1	18	localIOException6	java.io.IOException
    //   330	1	19	localURISyntaxException1	java.net.URISyntaxException
    //   818	1	20	localIOException7	java.io.IOException
    //   89	798	23	localStringBuilder2	StringBuilder
    //   107	3	24	localBufferedReader	java.io.BufferedReader
    //   884	1	25	localURISyntaxException2	java.net.URISyntaxException
    //   875	1	26	localSocketTimeoutException2	java.net.SocketTimeoutException
    //   866	1	27	localFileNotFoundException2	java.io.FileNotFoundException
    //   857	1	28	localIOException8	java.io.IOException
    //   232	1	29	localMalformedURLException2	MalformedURLException
    //   114	95	30	str	String
    //   843	1	32	localIOException9	java.io.IOException
    // Exception table:
    //   from	to	target	type
    //   91	109	232	java/net/MalformedURLException
    //   109	116	232	java/net/MalformedURLException
    //   202	229	232	java/net/MalformedURLException
    //   21	91	330	java/net/URISyntaxException
    //   21	91	424	java/net/SocketTimeoutException
    //   21	91	518	java/io/FileNotFoundException
    //   21	91	612	java/io/IOException
    //   21	91	706	finally
    //   243	248	813	java/io/IOException
    //   337	342	818	java/io/IOException
    //   431	436	823	java/io/IOException
    //   525	530	828	java/io/IOException
    //   619	624	833	java/io/IOException
    //   713	718	838	java/io/IOException
    //   126	131	843	java/io/IOException
    //   91	109	848	finally
    //   109	116	848	finally
    //   202	229	848	finally
    //   91	109	857	java/io/IOException
    //   109	116	857	java/io/IOException
    //   202	229	857	java/io/IOException
    //   91	109	866	java/io/FileNotFoundException
    //   109	116	866	java/io/FileNotFoundException
    //   202	229	866	java/io/FileNotFoundException
    //   91	109	875	java/net/SocketTimeoutException
    //   109	116	875	java/net/SocketTimeoutException
    //   202	229	875	java/net/SocketTimeoutException
    //   91	109	884	java/net/URISyntaxException
    //   109	116	884	java/net/URISyntaxException
    //   202	229	884	java/net/URISyntaxException
    //   21	91	893	java/net/MalformedURLException
  }
  
  public void reportAppStarted(final Context paramContext)
  {
    if (getReportAppStarted(paramContext)) {
      return;
    }
    new Thread(new Runnable()
    {
      public void run()
      {
        String str1 = SupersonicAdsAdvertiserAgent.this.getRequestParameters(paramContext);
        String str2 = "/campaigns/onLoad?" + str1;
        try
        {
          URL localURL = new URL(SupersonicAdsAdvertiserAgent.SERVICE_PROTOCOL, SupersonicAdsAdvertiserAgent.SERVICE_HOST_NAME, SupersonicAdsAdvertiserAgent.SERVICE_PORT, str2);
          if (SupersonicAdsAdvertiserAgent.this.performRequest(localURL, paramContext).getResponseCode() == 200) {
            SupersonicAdsAdvertiserAgent.this.setReportAppStarted(true, paramContext);
          }
          return;
        }
        catch (MalformedURLException localMalformedURLException) {}
      }
    }).start();
  }
  
  public void setDomain(String paramString1, String paramString2, int paramInt)
  {
    SERVICE_PROTOCOL = paramString1;
    SERVICE_HOST_NAME = paramString2;
    SERVICE_PORT = paramInt;
  }
  
  public void setPackageName(String paramString)
  {
    PACKAGE_NAME = paramString;
  }
  
  public void setReportAppStarted(boolean paramBoolean, Context paramContext)
  {
    this.mSharedPreferences = paramContext.getSharedPreferences("report_app_started", 0);
    SharedPreferences.Editor localEditor = this.mSharedPreferences.edit();
    localEditor.putBoolean("is_reported", paramBoolean);
    localEditor.apply();
  }
  
  public void setTimeAPI(String paramString)
  {
    TIME_API = paramString;
  }
  
  private class Result
  {
    private int mResponseCode;
    private String mResponseString;
    
    public Result() {}
    
    public Result(int paramInt, String paramString)
    {
      setResponseCode(paramInt);
      setResponseString(paramString);
    }
    
    public int getResponseCode()
    {
      return this.mResponseCode;
    }
    
    public String getResponseString()
    {
      return this.mResponseString;
    }
    
    public void setResponseCode(int paramInt)
    {
      this.mResponseCode = paramInt;
    }
    
    public void setResponseString(String paramString)
    {
      this.mResponseString = paramString;
    }
  }
  
  public static final class SuperSonicAdsAdvertiserException
    extends RuntimeException
  {
    private static final long serialVersionUID = 8169178234844720921L;
    
    public SuperSonicAdsAdvertiserException(Throwable paramThrowable)
    {
      super();
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.supersonicads.sdk.agent.SupersonicAdsAdvertiserAgent
 * JD-Core Version:    0.7.0.1
 */