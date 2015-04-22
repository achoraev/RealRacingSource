package com.millennialmedia.android;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.view.Display;
import android.view.WindowManager;
import java.lang.ref.WeakReference;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

class BridgeMMDevice
  extends MMJSObject
{
  private static final String CALL = "call";
  private static final String COMPOSE_EMAIL = "composeEmail";
  private static final String COMPOSE_SMS = "composeSms";
  private static final String ENABLE_HARDWARE_ACCEL = "enableHardwareAcceleration";
  private static final String GET_AVAIL_SCHEMES = "getAvailableSchemes";
  private static final String GET_INFO = "getInfo";
  private static final String GET_LOCATION = "getLocation";
  private static final String GET_ORIENTATION = "getOrientation";
  private static final String IS_SCHEME_AVAIL = "isSchemeAvailable";
  private static final String OPEN_APP_STORE = "openAppStore";
  private static final String OPEN_URL = "openUrl";
  private static final String SET_MMDID = "setMMDID";
  private static final String SHOW_MAP = "showMap";
  private static final String TAG = "BridgeMMDevice";
  private static final String TWEET = "tweet";
  
  /* Error */
  static org.json.JSONObject getDeviceInfo(Context paramContext)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: new 60	org/json/JSONObject
    //   5: dup
    //   6: invokespecial 61	org/json/JSONObject:<init>	()V
    //   9: astore_2
    //   10: aload_2
    //   11: ldc 63
    //   13: ldc 65
    //   15: invokevirtual 69	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   18: pop
    //   19: aload_2
    //   20: ldc 71
    //   22: aload_0
    //   23: invokestatic 77	com/millennialmedia/android/MMSDK:getConnectionType	(Landroid/content/Context;)Ljava/lang/String;
    //   26: invokevirtual 69	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   29: pop
    //   30: aload_2
    //   31: ldc 79
    //   33: ldc 81
    //   35: invokevirtual 69	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   38: pop
    //   39: getstatic 86	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
    //   42: ifnull +13 -> 55
    //   45: aload_2
    //   46: ldc 88
    //   48: getstatic 86	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
    //   51: invokevirtual 69	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   54: pop
    //   55: getstatic 93	android/os/Build:MODEL	Ljava/lang/String;
    //   58: ifnull +13 -> 71
    //   61: aload_2
    //   62: ldc 95
    //   64: getstatic 93	android/os/Build:MODEL	Ljava/lang/String;
    //   67: invokevirtual 69	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   70: pop
    //   71: aload_2
    //   72: ldc 97
    //   74: aload_0
    //   75: invokestatic 100	com/millennialmedia/android/MMSDK:getMMdid	(Landroid/content/Context;)Ljava/lang/String;
    //   78: invokevirtual 69	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   81: pop
    //   82: aload_0
    //   83: invokevirtual 106	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   86: invokevirtual 112	android/content/res/Resources:getDisplayMetrics	()Landroid/util/DisplayMetrics;
    //   89: astore 8
    //   91: aload_2
    //   92: ldc 114
    //   94: new 116	java/lang/Float
    //   97: dup
    //   98: aload 8
    //   100: getfield 121	android/util/DisplayMetrics:density	F
    //   103: invokespecial 124	java/lang/Float:<init>	(F)V
    //   106: invokevirtual 69	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   109: pop
    //   110: aload_2
    //   111: ldc 126
    //   113: new 128	java/lang/Integer
    //   116: dup
    //   117: aload 8
    //   119: getfield 132	android/util/DisplayMetrics:heightPixels	I
    //   122: invokespecial 135	java/lang/Integer:<init>	(I)V
    //   125: invokevirtual 69	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   128: pop
    //   129: aload_2
    //   130: ldc 137
    //   132: new 128	java/lang/Integer
    //   135: dup
    //   136: aload 8
    //   138: getfield 140	android/util/DisplayMetrics:widthPixels	I
    //   141: invokespecial 135	java/lang/Integer:<init>	(I)V
    //   144: invokevirtual 69	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   147: pop
    //   148: invokestatic 146	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   151: astore 12
    //   153: aload 12
    //   155: ifnull +27 -> 182
    //   158: aload_2
    //   159: ldc 148
    //   161: aload 12
    //   163: invokevirtual 152	java/util/Locale:getLanguage	()Ljava/lang/String;
    //   166: invokevirtual 69	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   169: pop
    //   170: aload_2
    //   171: ldc 154
    //   173: aload 12
    //   175: invokevirtual 157	java/util/Locale:getCountry	()Ljava/lang/String;
    //   178: invokevirtual 69	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   181: pop
    //   182: new 60	org/json/JSONObject
    //   185: dup
    //   186: invokespecial 61	org/json/JSONObject:<init>	()V
    //   189: astore 15
    //   191: aload 15
    //   193: ldc 159
    //   195: ldc 161
    //   197: invokevirtual 69	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   200: pop
    //   201: aload 15
    //   203: ldc 163
    //   205: ldc 165
    //   207: invokevirtual 69	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   210: pop
    //   211: aload 15
    //   213: ldc 167
    //   215: getstatic 170	com/millennialmedia/android/MMSDK:macId	Ljava/lang/String;
    //   218: invokevirtual 69	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   221: pop
    //   222: new 172	org/json/JSONArray
    //   225: dup
    //   226: invokespecial 173	org/json/JSONArray:<init>	()V
    //   229: astore 19
    //   231: aload 19
    //   233: aload 15
    //   235: invokevirtual 176	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   238: pop
    //   239: aload_2
    //   240: ldc 178
    //   242: aload 19
    //   244: invokevirtual 69	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   247: pop
    //   248: aload_2
    //   249: areturn
    //   250: astore_3
    //   251: ldc 47
    //   253: ldc 180
    //   255: aload_3
    //   256: invokestatic 186	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   259: aload_1
    //   260: areturn
    //   261: astore_3
    //   262: aload_2
    //   263: astore_1
    //   264: goto -13 -> 251
    //   267: astore_3
    //   268: aload_2
    //   269: astore_1
    //   270: goto -19 -> 251
    //   273: astore_3
    //   274: aload_2
    //   275: astore_1
    //   276: goto -25 -> 251
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	279	0	paramContext	Context
    //   1	275	1	localObject	Object
    //   9	266	2	localJSONObject1	org.json.JSONObject
    //   250	6	3	localJSONException1	org.json.JSONException
    //   261	1	3	localJSONException2	org.json.JSONException
    //   267	1	3	localJSONException3	org.json.JSONException
    //   273	1	3	localJSONException4	org.json.JSONException
    //   89	48	8	localDisplayMetrics	android.util.DisplayMetrics
    //   151	23	12	localLocale	java.util.Locale
    //   189	45	15	localJSONObject2	org.json.JSONObject
    //   229	14	19	localJSONArray	org.json.JSONArray
    // Exception table:
    //   from	to	target	type
    //   2	10	250	org/json/JSONException
    //   10	55	261	org/json/JSONException
    //   55	71	261	org/json/JSONException
    //   71	153	261	org/json/JSONException
    //   158	182	261	org/json/JSONException
    //   182	191	261	org/json/JSONException
    //   191	231	267	org/json/JSONException
    //   231	248	273	org/json/JSONException
  }
  
  public MMJSResponse call(Map<String, String> paramMap)
  {
    Context localContext = (Context)this.contextRef.get();
    String str = (String)paramMap.get("number");
    if ((localContext != null) && (str != null))
    {
      MMLog.d("BridgeMMDevice", String.format("Dialing Phone: %s", new Object[] { str }));
      if ((Boolean.parseBoolean((String)paramMap.get("dial"))) && (localContext.checkCallingOrSelfPermission("android.permission.CALL_PHONE") == 0)) {}
      for (Intent localIntent = new Intent("android.intent.action.CALL", Uri.parse("tel:" + str));; localIntent = new Intent("android.intent.action.VIEW", Uri.parse("tel:" + str)))
      {
        Utils.IntentUtils.startActivity(localContext, localIntent);
        MMSDK.Event.intentStarted(localContext, "tel", getAdImplId((String)paramMap.get("PROPERTY_EXPANDING")));
        return MMJSResponse.responseWithSuccess();
      }
    }
    return null;
  }
  
  public MMJSResponse composeEmail(Map<String, String> paramMap)
  {
    Context localContext = (Context)this.contextRef.get();
    String str1 = (String)paramMap.get("recipient");
    String str2 = (String)paramMap.get("subject");
    String str3 = (String)paramMap.get("message");
    if (localContext != null)
    {
      MMLog.d("BridgeMMDevice", "Creating email");
      Intent localIntent = new Intent("android.intent.action.SEND");
      localIntent.setType("plain/text");
      if (str1 != null) {
        localIntent.putExtra("android.intent.extra.EMAIL", str1.split(","));
      }
      if (str2 != null) {
        localIntent.putExtra("android.intent.extra.SUBJECT", str2);
      }
      if (str3 != null) {
        localIntent.putExtra("android.intent.extra.TEXT", str3);
      }
      Utils.IntentUtils.startActivity(localContext, localIntent);
      MMSDK.Event.intentStarted(localContext, "email", getAdImplId((String)paramMap.get("PROPERTY_EXPANDING")));
      return MMJSResponse.responseWithSuccess();
    }
    return null;
  }
  
  public MMJSResponse composeSms(Map<String, String> paramMap)
  {
    Context localContext = (Context)this.contextRef.get();
    String str1 = (String)paramMap.get("number");
    String str2 = (String)paramMap.get("message");
    if ((localContext != null) && (str1 != null))
    {
      MMLog.d("BridgeMMDevice", String.format("Creating sms: %s", new Object[] { str1 }));
      Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("sms:" + str1));
      if (str2 != null) {
        localIntent.putExtra("sms_body", str2);
      }
      Utils.IntentUtils.startActivity(localContext, localIntent);
      MMSDK.Event.intentStarted(localContext, "sms", getAdImplId((String)paramMap.get("PROPERTY_EXPANDING")));
      return MMJSResponse.responseWithSuccess("SMS Sent");
    }
    return null;
  }
  
  public MMJSResponse enableHardwareAcceleration(Map<String, String> paramMap)
  {
    MMLog.d("BridgeMMDevice", "hardware accel call" + paramMap);
    String str = (String)paramMap.get("enabled");
    MMWebView localMMWebView = (MMWebView)this.mmWebViewRef.get();
    if ((localMMWebView != null) && (localMMWebView != null))
    {
      if (Boolean.parseBoolean(str)) {
        localMMWebView.enableHardwareAcceleration();
      }
      for (;;)
      {
        return MMJSResponse.responseWithSuccess();
        localMMWebView.disableAllAcceleration();
      }
    }
    return null;
  }
  
  MMJSResponse executeCommand(String paramString, Map<String, String> paramMap)
  {
    MMJSResponse localMMJSResponse;
    if ("call".equals(paramString)) {
      localMMJSResponse = call(paramMap);
    }
    boolean bool;
    do
    {
      return localMMJSResponse;
      if ("composeEmail".equals(paramString)) {
        return composeEmail(paramMap);
      }
      if ("composeSms".equals(paramString)) {
        return composeSms(paramMap);
      }
      if ("enableHardwareAcceleration".equals(paramString)) {
        return enableHardwareAcceleration(paramMap);
      }
      if ("getAvailableSchemes".equals(paramString)) {
        return getAvailableSchemes(paramMap);
      }
      if ("getInfo".equals(paramString)) {
        return getInfo(paramMap);
      }
      if ("getLocation".equals(paramString)) {
        return getLocation(paramMap);
      }
      if ("getOrientation".equals(paramString)) {
        return getOrientation(paramMap);
      }
      if ("isSchemeAvailable".equals(paramString)) {
        return isSchemeAvailable(paramMap);
      }
      if ("openAppStore".equals(paramString)) {
        return openAppStore(paramMap);
      }
      if ("openUrl".equals(paramString)) {
        return openUrl(paramMap);
      }
      if ("setMMDID".equals(paramString)) {
        return setMMDID(paramMap);
      }
      if ("showMap".equals(paramString)) {
        return showMap(paramMap);
      }
      bool = "tweet".equals(paramString);
      localMMJSResponse = null;
    } while (!bool);
    return tweet(paramMap);
  }
  
  public MMJSResponse getAvailableSchemes(Map<String, String> paramMap)
  {
    Context localContext = (Context)this.contextRef.get();
    if (localContext != null)
    {
      HandShake localHandShake = HandShake.sharedHandShake(localContext);
      MMJSResponse localMMJSResponse = new MMJSResponse();
      localMMJSResponse.result = 1;
      localMMJSResponse.response = localHandShake.getSchemesJSONArray(localContext);
      return localMMJSResponse;
    }
    return null;
  }
  
  public MMJSResponse getInfo(Map<String, String> paramMap)
  {
    Context localContext = (Context)this.contextRef.get();
    if (localContext != null)
    {
      MMJSResponse localMMJSResponse = new MMJSResponse();
      localMMJSResponse.result = 1;
      localMMJSResponse.response = getDeviceInfo(localContext);
      return localMMJSResponse;
    }
    return null;
  }
  
  /* Error */
  public MMJSResponse getLocation(Map<String, String> paramMap)
  {
    // Byte code:
    //   0: getstatic 415	com/millennialmedia/android/MMRequest:location	Landroid/location/Location;
    //   3: ifnull +226 -> 229
    //   6: aconst_null
    //   7: astore_2
    //   8: new 60	org/json/JSONObject
    //   11: dup
    //   12: invokespecial 61	org/json/JSONObject:<init>	()V
    //   15: astore_3
    //   16: aload_3
    //   17: ldc_w 417
    //   20: getstatic 415	com/millennialmedia/android/MMRequest:location	Landroid/location/Location;
    //   23: invokevirtual 423	android/location/Location:getLatitude	()D
    //   26: invokestatic 428	java/lang/Double:toString	(D)Ljava/lang/String;
    //   29: invokevirtual 69	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   32: pop
    //   33: aload_3
    //   34: ldc_w 430
    //   37: getstatic 415	com/millennialmedia/android/MMRequest:location	Landroid/location/Location;
    //   40: invokevirtual 433	android/location/Location:getLongitude	()D
    //   43: invokestatic 428	java/lang/Double:toString	(D)Ljava/lang/String;
    //   46: invokevirtual 69	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   49: pop
    //   50: getstatic 415	com/millennialmedia/android/MMRequest:location	Landroid/location/Location;
    //   53: invokevirtual 437	android/location/Location:hasAccuracy	()Z
    //   56: ifeq +37 -> 93
    //   59: aload_3
    //   60: ldc_w 439
    //   63: getstatic 415	com/millennialmedia/android/MMRequest:location	Landroid/location/Location;
    //   66: invokevirtual 443	android/location/Location:getAccuracy	()F
    //   69: invokestatic 446	java/lang/Float:toString	(F)Ljava/lang/String;
    //   72: invokevirtual 69	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   75: pop
    //   76: aload_3
    //   77: ldc_w 448
    //   80: getstatic 415	com/millennialmedia/android/MMRequest:location	Landroid/location/Location;
    //   83: invokevirtual 443	android/location/Location:getAccuracy	()F
    //   86: invokestatic 446	java/lang/Float:toString	(F)Ljava/lang/String;
    //   89: invokevirtual 69	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   92: pop
    //   93: getstatic 415	com/millennialmedia/android/MMRequest:location	Landroid/location/Location;
    //   96: invokevirtual 451	android/location/Location:hasSpeed	()Z
    //   99: ifeq +20 -> 119
    //   102: aload_3
    //   103: ldc_w 453
    //   106: getstatic 415	com/millennialmedia/android/MMRequest:location	Landroid/location/Location;
    //   109: invokevirtual 456	android/location/Location:getSpeed	()F
    //   112: invokestatic 446	java/lang/Float:toString	(F)Ljava/lang/String;
    //   115: invokevirtual 69	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   118: pop
    //   119: getstatic 415	com/millennialmedia/android/MMRequest:location	Landroid/location/Location;
    //   122: invokevirtual 459	android/location/Location:hasBearing	()Z
    //   125: ifeq +20 -> 145
    //   128: aload_3
    //   129: ldc_w 461
    //   132: getstatic 415	com/millennialmedia/android/MMRequest:location	Landroid/location/Location;
    //   135: invokevirtual 464	android/location/Location:getBearing	()F
    //   138: invokestatic 446	java/lang/Float:toString	(F)Ljava/lang/String;
    //   141: invokevirtual 69	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   144: pop
    //   145: getstatic 415	com/millennialmedia/android/MMRequest:location	Landroid/location/Location;
    //   148: invokevirtual 467	android/location/Location:hasAltitude	()Z
    //   151: ifeq +20 -> 171
    //   154: aload_3
    //   155: ldc_w 469
    //   158: getstatic 415	com/millennialmedia/android/MMRequest:location	Landroid/location/Location;
    //   161: invokevirtual 472	android/location/Location:getAltitude	()D
    //   164: invokestatic 428	java/lang/Double:toString	(D)Ljava/lang/String;
    //   167: invokevirtual 69	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   170: pop
    //   171: aload_3
    //   172: ldc_w 474
    //   175: getstatic 415	com/millennialmedia/android/MMRequest:location	Landroid/location/Location;
    //   178: invokevirtual 478	android/location/Location:getTime	()J
    //   181: invokestatic 483	java/lang/Long:toString	(J)Ljava/lang/String;
    //   184: invokevirtual 69	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   187: pop
    //   188: aload_3
    //   189: astore_2
    //   190: new 279	com/millennialmedia/android/MMJSResponse
    //   193: dup
    //   194: invokespecial 396	com/millennialmedia/android/MMJSResponse:<init>	()V
    //   197: astore 5
    //   199: aload 5
    //   201: iconst_1
    //   202: putfield 399	com/millennialmedia/android/MMJSResponse:result	I
    //   205: aload 5
    //   207: aload_2
    //   208: putfield 407	com/millennialmedia/android/MMJSResponse:response	Ljava/lang/Object;
    //   211: aload 5
    //   213: areturn
    //   214: astore 4
    //   216: ldc 47
    //   218: ldc_w 485
    //   221: aload 4
    //   223: invokestatic 186	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   226: goto -36 -> 190
    //   229: ldc_w 487
    //   232: invokestatic 490	com/millennialmedia/android/MMJSResponse:responseWithError	(Ljava/lang/String;)Lcom/millennialmedia/android/MMJSResponse;
    //   235: areturn
    //   236: astore 4
    //   238: aload_3
    //   239: astore_2
    //   240: goto -24 -> 216
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	243	0	this	BridgeMMDevice
    //   0	243	1	paramMap	Map<String, String>
    //   7	233	2	localObject	Object
    //   15	224	3	localJSONObject	org.json.JSONObject
    //   214	8	4	localJSONException1	org.json.JSONException
    //   236	1	4	localJSONException2	org.json.JSONException
    //   197	15	5	localMMJSResponse	MMJSResponse
    // Exception table:
    //   from	to	target	type
    //   8	16	214	org/json/JSONException
    //   16	93	236	org/json/JSONException
    //   93	119	236	org/json/JSONException
    //   119	145	236	org/json/JSONException
    //   145	171	236	org/json/JSONException
    //   171	188	236	org/json/JSONException
  }
  
  public MMJSResponse getOrientation(Map<String, String> paramMap)
  {
    Context localContext = (Context)this.contextRef.get();
    if (localContext != null)
    {
      int i = localContext.getResources().getConfiguration().orientation;
      if (i == 0) {
        i = ((WindowManager)localContext.getSystemService("window")).getDefaultDisplay().getOrientation();
      }
      MMJSResponse localMMJSResponse = new MMJSResponse();
      localMMJSResponse.result = 1;
      switch (i)
      {
      default: 
        localMMJSResponse.response = "portrait";
        return localMMJSResponse;
      }
      localMMJSResponse.response = "landscape";
      return localMMJSResponse;
    }
    return null;
  }
  
  public MMJSResponse isSchemeAvailable(Map<String, String> paramMap)
  {
    String str = (String)paramMap.get("scheme");
    if (!str.contains(":")) {
      str = str + ":";
    }
    Context localContext = (Context)this.contextRef.get();
    if ((str != null) && (localContext != null))
    {
      Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse(str));
      if (localContext.getPackageManager().queryIntentActivities(localIntent, 65536).size() > 0) {
        return MMJSResponse.responseWithSuccess(str);
      }
    }
    return MMJSResponse.responseWithError(str);
  }
  
  public MMJSResponse openAppStore(Map<String, String> paramMap)
  {
    Context localContext = (Context)this.contextRef.get();
    String str1 = (String)paramMap.get("appId");
    String str2 = (String)paramMap.get("referrer");
    if ((localContext != null) && (str1 != null))
    {
      MMLog.d("BridgeMMDevice", String.format("Opening marketplace: %s", new Object[] { str1 }));
      Intent localIntent = new Intent("android.intent.action.VIEW");
      if (Build.MANUFACTURER.equals("Amazon")) {
        localIntent.setData(Uri.parse(String.format("amzn://apps/android?p=%s", new Object[] { str1 })));
      }
      for (;;)
      {
        MMSDK.Event.intentStarted(localContext, "market", getAdImplId((String)paramMap.get("PROPERTY_EXPANDING")));
        Utils.IntentUtils.startActivity(localContext, localIntent);
        return MMJSResponse.responseWithSuccess();
        if (str2 != null)
        {
          Object[] arrayOfObject = new Object[2];
          arrayOfObject[0] = str1;
          arrayOfObject[1] = URLEncoder.encode(str2);
          localIntent.setData(Uri.parse(String.format("market://details?id=%s&referrer=%s", arrayOfObject)));
        }
        else
        {
          localIntent.setData(Uri.parse("market://details?id=" + str1));
        }
      }
    }
    return null;
  }
  
  public MMJSResponse openUrl(Map<String, String> paramMap)
  {
    Context localContext = (Context)this.contextRef.get();
    String str = (String)paramMap.get("url");
    if ((localContext != null) && (str != null))
    {
      MMLog.d("BridgeMMDevice", String.format("Opening: %s", new Object[] { str }));
      Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse(str));
      if ((localIntent.getScheme().startsWith("http")) || (localIntent.getScheme().startsWith("https"))) {
        MMSDK.Event.intentStarted(localContext, "browser", getAdImplId((String)paramMap.get("PROPERTY_EXPANDING")));
      }
      Utils.IntentUtils.startActivity(localContext, localIntent);
      return MMJSResponse.responseWithSuccess("Overlay opened");
    }
    return MMJSResponse.responseWithError("URL could not be opened");
  }
  
  public MMJSResponse setMMDID(Map<String, String> paramMap)
  {
    String str = (String)paramMap.get("mmdid");
    Context localContext = (Context)this.contextRef.get();
    if (localContext != null)
    {
      HandShake.sharedHandShake(localContext).setMMdid(localContext, str);
      return MMJSResponse.responseWithSuccess("MMDID is set");
    }
    return null;
  }
  
  public MMJSResponse showMap(Map<String, String> paramMap)
  {
    Context localContext = (Context)this.contextRef.get();
    String str = (String)paramMap.get("location");
    if ((localContext != null) && (str != null))
    {
      MMLog.d("BridgeMMDevice", String.format("Launching Google Maps: %s", new Object[] { str }));
      Utils.IntentUtils.startActivity(localContext, new Intent("android.intent.action.VIEW", Uri.parse("geo:" + str)));
      MMSDK.Event.intentStarted(localContext, "geo", getAdImplId((String)paramMap.get("PROPERTY_EXPANDING")));
      return MMJSResponse.responseWithSuccess("Map successfully opened");
    }
    return null;
  }
  
  public MMJSResponse tweet(Map<String, String> paramMap)
  {
    return null;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.BridgeMMDevice
 * JD-Core Version:    0.7.0.1
 */