package com.millennialmedia.android;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.WindowManager.BadTokenException;
import android.webkit.URLUtil;
import android.widget.Toast;
import com.millennialmedia.google.gson.Gson;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class HandShake
{
  static final String BASE_URL = "http://androidsdk.ads.mp.mydas.mobi/";
  static final String BASE_URL_PATH = "getAd.php5?";
  private static final String HANDSHAKE_FALLBACK_URL = "http://ads.mp.mydas.mobi/appConfigServlet?apid=";
  private static final String HANDSHAKE_HTTPS_SCHEME = "https://";
  private static final String HANDSHAKE_HTTP_SCHEME = "http://";
  private static final String HANDSHAKE_URL_HOST = "ads.mp.mydas.mobi/";
  private static final String HANDSHAKE_URL_OVERRIDE_PARMS = "?apid=";
  private static final String HANDSHAKE_URL_PARMS = "appConfigServlet?apid=";
  private static final String KEY_CACHED_VIDEOS = "handshake_cachedvideos5.0";
  private static final String TAG = "HandShake";
  private static String adUrl = "http://androidsdk.ads.mp.mydas.mobi/getAd.php5?";
  static String apid = "28913";
  private static boolean forceRefresh;
  private static String handShakeURL = "https://ads.mp.mydas.mobi/appConfigServlet?apid=";
  private static HandShake sharedInstance;
  long adRefreshSecs;
  private final LinkedHashMap<String, AdTypeHandShake> adTypeHandShakes = new LinkedHashMap();
  private WeakReference<Context> appContextRef;
  DTOCachedVideo[] cachedVideos;
  private WeakReference<Context> contextRef;
  long creativeCacheTimeout = 259200000L;
  private long deferredViewTimeout = 3600000L;
  String endSessionURL;
  private long handShakeCallback = 86400000L;
  private final Handler handler = new Handler(Looper.getMainLooper());
  boolean hardwareAccelerationEnabled;
  boolean kill = false;
  private long lastHandShake;
  String mmdid;
  String mmjsUrl;
  private String noVideosToCacheURL;
  NuanceCredentials nuanceCredentials;
  private final ArrayList<Scheme> schemes = new ArrayList();
  private String schemesList;
  String startSessionURL;
  private final Runnable updateHandShakeRunnable = new Runnable()
  {
    public void run()
    {
      Context localContext = (Context)HandShake.this.contextRef.get();
      if (localContext == null) {
        localContext = (Context)HandShake.this.appContextRef.get();
      }
      if (localContext != null) {
        HandShake.sharedHandShake(localContext);
      }
    }
  };
  
  private HandShake() {}
  
  private HandShake(Context paramContext)
  {
    this.contextRef = new WeakReference(paramContext);
    this.appContextRef = new WeakReference(paramContext.getApplicationContext());
    if ((forceRefresh) || (!loadHandShake(paramContext)) || (System.currentTimeMillis() - this.lastHandShake > this.handShakeCallback))
    {
      forceRefresh = false;
      this.lastHandShake = System.currentTimeMillis();
      requestHandshake(false);
    }
  }
  
  private void deserializeFromObj(JSONObject paramJSONObject)
  {
    Context localContext1 = (Context)this.contextRef.get();
    if (localContext1 == null) {
      localContext1 = (Context)this.appContextRef.get();
    }
    if (localContext1 == null) {
      MMLog.e("HandShake", "No context for handshake");
    }
    while (paramJSONObject == null) {
      return;
    }
    int k;
    try
    {
      JSONArray localJSONArray1 = paramJSONObject.optJSONArray("errors");
      if (localJSONArray1 != null)
      {
        k = 0;
        if (k < localJSONArray1.length())
        {
          JSONObject localJSONObject4 = localJSONArray1.optJSONObject(k);
          if (localJSONObject4 == null) {
            break label634;
          }
          final String str2 = localJSONObject4.optString("message", null);
          String str3 = localJSONObject4.optString("type", null);
          if ((str2 == null) || (str3 == null)) {
            break label634;
          }
          if (str3.equalsIgnoreCase("log"))
          {
            MMLog.e("HandShake", str2);
          }
          else if (str3.equalsIgnoreCase("prompt"))
          {
            final Context localContext2 = localContext1;
            Handler localHandler = this.handler;
            Runnable local3 = new Runnable()
            {
              public void run()
              {
                try
                {
                  Toast.makeText(localContext2, "Error: " + str2, 1).show();
                  return;
                }
                catch (WindowManager.BadTokenException localBadTokenException)
                {
                  MMLog.e("HandShake", "Error with toast token", localBadTokenException);
                }
              }
            };
            localHandler.post(local3);
          }
        }
      }
    }
    catch (Exception localException)
    {
      MMLog.e("HandShake", "Error deserializing handshake", localException);
      return;
    }
    JSONObject localJSONObject1 = paramJSONObject.optJSONObject("adtypes");
    int i;
    if (localJSONObject1 != null)
    {
      String[] arrayOfString = MMAdImpl.getAdTypes();
      i = 0;
      label210:
      if (i < arrayOfString.length)
      {
        JSONObject localJSONObject3 = localJSONObject1.optJSONObject(arrayOfString[i]);
        if (localJSONObject3 == null) {
          break label640;
        }
        AdTypeHandShake localAdTypeHandShake = new AdTypeHandShake();
        localAdTypeHandShake.deserializeFromObj(localJSONObject3);
        localAdTypeHandShake.loadLastVideo(localContext1, arrayOfString[i]);
        this.adTypeHandShakes.put(arrayOfString[i], localAdTypeHandShake);
        break label640;
      }
    }
    for (;;)
    {
      try
      {
        JSONArray localJSONArray2 = paramJSONObject.optJSONArray("schemes");
        if (localJSONArray2 != null)
        {
          if ((this.schemes == null) || (this.schemes.size() <= 0)) {
            break label646;
          }
          this.schemes.removeAll(this.schemes);
          break label646;
          if (j < localJSONArray2.length())
          {
            JSONObject localJSONObject2 = localJSONArray2.optJSONObject(j);
            if (localJSONObject2 == null) {
              break label652;
            }
            Scheme localScheme = new Scheme();
            localScheme.deserializeFromObj(localJSONObject2);
            this.schemes.add(localScheme);
            break label652;
          }
        }
        this.adRefreshSecs = paramJSONObject.optLong("adrefresh", 0L);
        this.deferredViewTimeout = (1000L * paramJSONObject.optLong("deferredviewtimeout", 3600L));
        this.kill = paramJSONObject.optBoolean("kill");
        setAdUrl(paramJSONObject.optString("baseURL"));
        this.handShakeCallback = (1000L * paramJSONObject.optLong("handshakecallback", 86400L));
        this.creativeCacheTimeout = (1000L * paramJSONObject.optLong("creativeCacheTimeout", 259200L));
        this.hardwareAccelerationEnabled = paramJSONObject.optBoolean("hardwareAccelerationEnabled");
        this.startSessionURL = paramJSONObject.optString("startSessionURL");
        this.endSessionURL = paramJSONObject.optString("endSessionURL");
        String str1 = paramJSONObject.optString("nuanceCredentials");
        this.nuanceCredentials = ((NuanceCredentials)new Gson().fromJson(str1, NuanceCredentials.class));
        this.mmjsUrl = paramJSONObject.optString("mmjs");
        handleCachedVideos(paramJSONObject, localContext1);
        if ((!TextUtils.isEmpty(this.mmjsUrl)) && (!MRaid.isMRaidUpdated(localContext1, this.mmjsUrl)))
        {
          MRaid.downloadMraidJs((Context)this.appContextRef.get(), this.mmjsUrl);
          return;
        }
      }
      finally {}
      MMLog.w("HandShake", "Not downloading MMJS - (" + this.mmjsUrl + ")");
      return;
      label634:
      k++;
      break;
      label640:
      i++;
      break label210;
      label646:
      int j = 0;
      continue;
      label652:
      j++;
    }
  }
  
  static String getAdUrl()
  {
    if ((!TextUtils.isEmpty(adUrl)) && (URLUtil.isHttpUrl(adUrl.replace("getAd.php5?", "")))) {
      return adUrl;
    }
    return "http://androidsdk.ads.mp.mydas.mobi/getAd.php5?";
  }
  
  private void handleCachedVideos(JSONObject paramJSONObject, Context paramContext)
  {
    JSONArray localJSONArray = paramJSONObject.optJSONArray("cachedVideos");
    if (localJSONArray != null)
    {
      this.cachedVideos = ((DTOCachedVideo[])new Gson().fromJson(localJSONArray.toString(), [Lcom.millennialmedia.android.DTOCachedVideo.class));
      MMLog.d("HandShake", this.cachedVideos.toString());
    }
    this.noVideosToCacheURL = paramJSONObject.optString("noVideosToCacheURL");
    if (this.cachedVideos != null) {
      PreCacheWorker.preCacheVideos(this.cachedVideos, paramContext, this.noVideosToCacheURL);
    }
  }
  
  private boolean isFirstLaunch(Context paramContext)
  {
    if (paramContext == null) {
      return false;
    }
    return paramContext.getSharedPreferences("MillennialMediaSettings", 0).getBoolean("firstlaunchHandshake", true);
  }
  
  private boolean loadHandShake(Context paramContext)
  {
    SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("MillennialMediaSettings", 0);
    if (localSharedPreferences == null) {
      return false;
    }
    boolean bool1 = localSharedPreferences.contains("handshake_deferredviewtimeout");
    boolean bool2 = false;
    if (bool1)
    {
      this.deferredViewTimeout = localSharedPreferences.getLong("handshake_deferredviewtimeout", this.deferredViewTimeout);
      bool2 = true;
    }
    if (localSharedPreferences.contains("handshake_baseUrl"))
    {
      adUrl = localSharedPreferences.getString("handshake_baseUrl", adUrl);
      bool2 = true;
    }
    if (localSharedPreferences.contains("handshake_callback"))
    {
      this.handShakeCallback = localSharedPreferences.getLong("handshake_callback", this.handShakeCallback);
      bool2 = true;
    }
    if (localSharedPreferences.contains("handshake_hardwareAccelerationEnabled"))
    {
      this.hardwareAccelerationEnabled = localSharedPreferences.getBoolean("handshake_hardwareAccelerationEnabled", false);
      bool2 = true;
    }
    if (localSharedPreferences.contains("handshake_startSessionURL"))
    {
      this.startSessionURL = localSharedPreferences.getString("handshake_startSessionURL", "");
      bool2 = true;
    }
    if (localSharedPreferences.contains("handshake_endSessionURL"))
    {
      this.endSessionURL = localSharedPreferences.getString("handshake_endSessionURL", "");
      bool2 = true;
    }
    if (localSharedPreferences.contains("handshake_nuanceCredentials"))
    {
      String str3 = localSharedPreferences.getString("handshake_nuanceCredentials", "");
      this.nuanceCredentials = ((NuanceCredentials)new Gson().fromJson(str3, NuanceCredentials.class));
      bool2 = true;
    }
    if (localSharedPreferences.contains("handshake_mmdid"))
    {
      setMMdid(paramContext, localSharedPreferences.getString("handshake_mmdid", this.mmdid), false);
      bool2 = true;
    }
    if (localSharedPreferences.contains("handshake_creativecachetimeout"))
    {
      this.creativeCacheTimeout = localSharedPreferences.getLong("handshake_creativecachetimeout", this.creativeCacheTimeout);
      bool2 = true;
    }
    if (localSharedPreferences.contains("handshake_mmjs"))
    {
      this.mmjsUrl = localSharedPreferences.getString("handshake_mmjs", this.mmjsUrl);
      bool2 = true;
    }
    String[] arrayOfString1 = MMAdImpl.getAdTypes();
    for (int i = 0; i < arrayOfString1.length; i++)
    {
      AdTypeHandShake localAdTypeHandShake = new AdTypeHandShake();
      if (localAdTypeHandShake.load(localSharedPreferences, arrayOfString1[i]))
      {
        bool2 = true;
        this.adTypeHandShakes.put(arrayOfString1[i], localAdTypeHandShake);
      }
    }
    for (;;)
    {
      int k;
      try
      {
        if (localSharedPreferences.contains("handshake_schemes"))
        {
          String str2 = localSharedPreferences.getString("handshake_schemes", "");
          if (str2.length() > 0)
          {
            String[] arrayOfString2 = str2.split("\n");
            int j = arrayOfString2.length;
            k = 0;
            if (k >= j) {
              break label726;
            }
            String[] arrayOfString3 = arrayOfString2[k].split("\t");
            if (arrayOfString3.length < 2) {
              break label720;
            }
            Scheme localScheme = new Scheme(arrayOfString3[0], Integer.parseInt(arrayOfString3[1]));
            this.schemes.add(localScheme);
            break label720;
          }
        }
        if (localSharedPreferences.contains("handshake_cachedvideos5.0"))
        {
          String str1 = localSharedPreferences.getString("handshake_cachedvideos5.0", "");
          if (str1.length() > 0) {
            this.cachedVideos = ((DTOCachedVideo[])new Gson().fromJson(str1, [Lcom.millennialmedia.android.DTOCachedVideo.class));
          }
        }
        if (localSharedPreferences.contains("handshake_lasthandshake"))
        {
          this.lastHandShake = localSharedPreferences.getLong("handshake_lasthandshake", this.lastHandShake);
          bool2 = true;
        }
        if (bool2)
        {
          MMLog.d("HandShake", "Handshake successfully loaded from shared preferences.");
          if (System.currentTimeMillis() - this.lastHandShake < this.handShakeCallback) {
            this.handler.postDelayed(this.updateHandShakeRunnable, this.handShakeCallback - (System.currentTimeMillis() - this.lastHandShake));
          }
          this.noVideosToCacheURL = localSharedPreferences.getString("handshake_novideostocacheurl", "");
          if (this.cachedVideos != null) {
            PreCacheWorker.preCacheVideos(this.cachedVideos, paramContext, this.noVideosToCacheURL);
          }
        }
        return bool2;
      }
      finally {}
      label720:
      k++;
      continue;
      label726:
      bool2 = true;
    }
  }
  
  private JSONObject parseJson(String paramString)
  {
    MMLog.d("HandShake", String.format("JSON String: %s", new Object[] { paramString }));
    if (paramString != null) {
      try
      {
        JSONObject localJSONObject1 = new JSONObject(paramString);
        MMLog.v("HandShake", localJSONObject1.toString());
        if (localJSONObject1.has("mmishake"))
        {
          JSONObject localJSONObject2 = localJSONObject1.getJSONObject("mmishake");
          return localJSONObject2;
        }
      }
      catch (JSONException localJSONException)
      {
        MMLog.e("HandShake", "Error parsing json", localJSONException);
      }
    }
    return null;
  }
  
  private void requestHandshake(final boolean paramBoolean)
  {
    Context localContext = (Context)this.contextRef.get();
    if (localContext != null)
    {
      String str = localContext.getSharedPreferences("MillennialMediaSettings", 0).getString("handShakeUrl", null);
      if (str != null) {
        setHandShakeURL(str);
      }
    }
    Utils.ThreadUtils.execute(new Runnable()
    {
      /* Error */
      public void run()
      {
        // Byte code:
        //   0: aload_0
        //   1: getfield 19	com/millennialmedia/android/HandShake$1:this$0	Lcom/millennialmedia/android/HandShake;
        //   4: invokestatic 33	com/millennialmedia/android/HandShake:access$000	(Lcom/millennialmedia/android/HandShake;)Ljava/lang/ref/WeakReference;
        //   7: invokevirtual 39	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
        //   10: checkcast 41	android/content/Context
        //   13: astore_1
        //   14: aload_1
        //   15: ifnonnull +17 -> 32
        //   18: aload_0
        //   19: getfield 19	com/millennialmedia/android/HandShake$1:this$0	Lcom/millennialmedia/android/HandShake;
        //   22: invokestatic 44	com/millennialmedia/android/HandShake:access$100	(Lcom/millennialmedia/android/HandShake;)Ljava/lang/ref/WeakReference;
        //   25: invokevirtual 39	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
        //   28: checkcast 41	android/content/Context
        //   31: astore_1
        //   32: aload_1
        //   33: ifnonnull +4 -> 37
        //   36: return
        //   37: iconst_0
        //   38: istore_2
        //   39: new 46	java/lang/StringBuilder
        //   42: dup
        //   43: invokespecial 47	java/lang/StringBuilder:<init>	()V
        //   46: astore_3
        //   47: new 49	java/util/TreeMap
        //   50: dup
        //   51: invokespecial 50	java/util/TreeMap:<init>	()V
        //   54: astore 4
        //   56: aload 4
        //   58: ldc 52
        //   60: new 46	java/lang/StringBuilder
        //   63: dup
        //   64: invokespecial 47	java/lang/StringBuilder:<init>	()V
        //   67: ldc 54
        //   69: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   72: getstatic 64	android/os/Build:MODEL	Ljava/lang/String;
        //   75: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   78: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   81: invokeinterface 74 3 0
        //   86: pop
        //   87: aload_0
        //   88: getfield 19	com/millennialmedia/android/HandShake$1:this$0	Lcom/millennialmedia/android/HandShake;
        //   91: aload_1
        //   92: invokestatic 78	com/millennialmedia/android/HandShake:access$200	(Lcom/millennialmedia/android/HandShake;Landroid/content/Context;)Z
        //   95: istore_2
        //   96: iload_2
        //   97: ifeq +15 -> 112
        //   100: aload 4
        //   102: ldc 80
        //   104: ldc 82
        //   106: invokeinterface 74 3 0
        //   111: pop
        //   112: aload_0
        //   113: getfield 21	com/millennialmedia/android/HandShake$1:val$isInitialize	Z
        //   116: ifeq +15 -> 131
        //   119: aload 4
        //   121: ldc 84
        //   123: ldc 82
        //   125: invokeinterface 74 3 0
        //   130: pop
        //   131: aload_1
        //   132: aload 4
        //   134: invokestatic 90	com/millennialmedia/android/MMSDK:insertUrlCommonValues	(Landroid/content/Context;Ljava/util/Map;)V
        //   137: aload 4
        //   139: invokeinterface 94 1 0
        //   144: invokeinterface 100 1 0
        //   149: astore 10
        //   151: aload 10
        //   153: invokeinterface 106 1 0
        //   158: ifeq +90 -> 248
        //   161: aload 10
        //   163: invokeinterface 109 1 0
        //   168: checkcast 111	java/util/Map$Entry
        //   171: astore 25
        //   173: iconst_2
        //   174: anewarray 4	java/lang/Object
        //   177: astore 26
        //   179: aload 26
        //   181: iconst_0
        //   182: aload 25
        //   184: invokeinterface 114 1 0
        //   189: aastore
        //   190: aload 26
        //   192: iconst_1
        //   193: aload 25
        //   195: invokeinterface 117 1 0
        //   200: checkcast 119	java/lang/String
        //   203: ldc 121
        //   205: invokestatic 127	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   208: aastore
        //   209: aload_3
        //   210: ldc 129
        //   212: aload 26
        //   214: invokestatic 133	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   217: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   220: pop
        //   221: goto -70 -> 151
        //   224: astore 7
        //   226: ldc 135
        //   228: ldc 137
        //   230: aload 7
        //   232: invokestatic 143	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   235: iconst_0
        //   236: ifeq -200 -> 36
        //   239: aload_0
        //   240: getfield 19	com/millennialmedia/android/HandShake$1:this$0	Lcom/millennialmedia/android/HandShake;
        //   243: aload_1
        //   244: invokestatic 147	com/millennialmedia/android/HandShake:access$1000	(Lcom/millennialmedia/android/HandShake;Landroid/content/Context;)V
        //   247: return
        //   248: new 46	java/lang/StringBuilder
        //   251: dup
        //   252: invokespecial 47	java/lang/StringBuilder:<init>	()V
        //   255: invokestatic 150	com/millennialmedia/android/HandShake:access$300	()Ljava/lang/String;
        //   258: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   261: getstatic 153	com/millennialmedia/android/HandShake:apid	Ljava/lang/String;
        //   264: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   267: aload_3
        //   268: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   271: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   274: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   277: astore 11
        //   279: ldc 135
        //   281: ldc 155
        //   283: iconst_1
        //   284: anewarray 4	java/lang/Object
        //   287: dup
        //   288: iconst_0
        //   289: aload 11
        //   291: aastore
        //   292: invokestatic 133	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   295: invokestatic 159	com/millennialmedia/android/MMLog:v	(Ljava/lang/String;Ljava/lang/String;)V
        //   298: new 161	com/millennialmedia/android/HttpGetRequest
        //   301: dup
        //   302: sipush 3000
        //   305: invokespecial 164	com/millennialmedia/android/HttpGetRequest:<init>	(I)V
        //   308: aload 11
        //   310: invokevirtual 167	com/millennialmedia/android/HttpGetRequest:get	(Ljava/lang/String;)Lorg/apache/http/HttpResponse;
        //   313: astore 24
        //   315: aload 24
        //   317: astore 13
        //   319: aload 13
        //   321: ifnull +25 -> 346
        //   324: aload 13
        //   326: invokeinterface 173 1 0
        //   331: invokeinterface 179 1 0
        //   336: istore 23
        //   338: iload 23
        //   340: sipush 200
        //   343: if_icmpeq +85 -> 428
        //   346: invokestatic 150	com/millennialmedia/android/HandShake:access$300	()Ljava/lang/String;
        //   349: ldc 181
        //   351: ldc 183
        //   353: invokevirtual 186	java/lang/String:replaceFirst	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   356: invokestatic 190	com/millennialmedia/android/HandShake:access$302	(Ljava/lang/String;)Ljava/lang/String;
        //   359: pop
        //   360: new 46	java/lang/StringBuilder
        //   363: dup
        //   364: invokespecial 47	java/lang/StringBuilder:<init>	()V
        //   367: invokestatic 150	com/millennialmedia/android/HandShake:access$300	()Ljava/lang/String;
        //   370: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   373: getstatic 153	com/millennialmedia/android/HandShake:apid	Ljava/lang/String;
        //   376: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   379: aload_3
        //   380: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   383: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   386: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   389: astore 21
        //   391: ldc 135
        //   393: ldc 192
        //   395: iconst_1
        //   396: anewarray 4	java/lang/Object
        //   399: dup
        //   400: iconst_0
        //   401: aload 21
        //   403: aastore
        //   404: invokestatic 133	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   407: invokestatic 159	com/millennialmedia/android/MMLog:v	(Ljava/lang/String;Ljava/lang/String;)V
        //   410: new 161	com/millennialmedia/android/HttpGetRequest
        //   413: dup
        //   414: invokespecial 193	com/millennialmedia/android/HttpGetRequest:<init>	()V
        //   417: aload 21
        //   419: invokevirtual 167	com/millennialmedia/android/HttpGetRequest:get	(Ljava/lang/String;)Lorg/apache/http/HttpResponse;
        //   422: astore 22
        //   424: aload 22
        //   426: astore 13
        //   428: aload 13
        //   430: ifnull +25 -> 455
        //   433: aload 13
        //   435: invokeinterface 173 1 0
        //   440: invokeinterface 179 1 0
        //   445: istore 19
        //   447: iload 19
        //   449: sipush 200
        //   452: if_icmpeq +76 -> 528
        //   455: new 46	java/lang/StringBuilder
        //   458: dup
        //   459: invokespecial 47	java/lang/StringBuilder:<init>	()V
        //   462: ldc 195
        //   464: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   467: getstatic 153	com/millennialmedia/android/HandShake:apid	Ljava/lang/String;
        //   470: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   473: aload_3
        //   474: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   477: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   480: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   483: astore 17
        //   485: ldc 135
        //   487: new 46	java/lang/StringBuilder
        //   490: dup
        //   491: invokespecial 47	java/lang/StringBuilder:<init>	()V
        //   494: ldc 197
        //   496: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   499: aload 17
        //   501: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   504: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   507: invokestatic 159	com/millennialmedia/android/MMLog:v	(Ljava/lang/String;Ljava/lang/String;)V
        //   510: new 161	com/millennialmedia/android/HttpGetRequest
        //   513: dup
        //   514: invokespecial 193	com/millennialmedia/android/HttpGetRequest:<init>	()V
        //   517: aload 17
        //   519: invokevirtual 167	com/millennialmedia/android/HttpGetRequest:get	(Ljava/lang/String;)Lorg/apache/http/HttpResponse;
        //   522: astore 18
        //   524: aload 18
        //   526: astore 13
        //   528: aload 13
        //   530: ifnull +189 -> 719
        //   533: aload 13
        //   535: invokeinterface 173 1 0
        //   540: invokeinterface 179 1 0
        //   545: sipush 200
        //   548: if_icmpne +171 -> 719
        //   551: aload_0
        //   552: getfield 19	com/millennialmedia/android/HandShake$1:this$0	Lcom/millennialmedia/android/HandShake;
        //   555: aload_0
        //   556: getfield 19	com/millennialmedia/android/HandShake$1:this$0	Lcom/millennialmedia/android/HandShake;
        //   559: aload 13
        //   561: invokeinterface 201 1 0
        //   566: invokeinterface 207 1 0
        //   571: invokestatic 211	com/millennialmedia/android/HttpGetRequest:convertStreamToString	(Ljava/io/InputStream;)Ljava/lang/String;
        //   574: invokestatic 215	com/millennialmedia/android/HandShake:access$400	(Lcom/millennialmedia/android/HandShake;Ljava/lang/String;)Lorg/json/JSONObject;
        //   577: invokestatic 219	com/millennialmedia/android/HandShake:access$500	(Lcom/millennialmedia/android/HandShake;Lorg/json/JSONObject;)V
        //   580: aload_0
        //   581: getfield 19	com/millennialmedia/android/HandShake$1:this$0	Lcom/millennialmedia/android/HandShake;
        //   584: aload_1
        //   585: invokestatic 222	com/millennialmedia/android/HandShake:access$600	(Lcom/millennialmedia/android/HandShake;Landroid/content/Context;)V
        //   588: aload_0
        //   589: getfield 19	com/millennialmedia/android/HandShake$1:this$0	Lcom/millennialmedia/android/HandShake;
        //   592: invokestatic 226	com/millennialmedia/android/HandShake:access$900	(Lcom/millennialmedia/android/HandShake;)Landroid/os/Handler;
        //   595: aload_0
        //   596: getfield 19	com/millennialmedia/android/HandShake$1:this$0	Lcom/millennialmedia/android/HandShake;
        //   599: invokestatic 230	com/millennialmedia/android/HandShake:access$700	(Lcom/millennialmedia/android/HandShake;)Ljava/lang/Runnable;
        //   602: aload_0
        //   603: getfield 19	com/millennialmedia/android/HandShake$1:this$0	Lcom/millennialmedia/android/HandShake;
        //   606: invokestatic 234	com/millennialmedia/android/HandShake:access$800	(Lcom/millennialmedia/android/HandShake;)J
        //   609: invokevirtual 240	android/os/Handler:postDelayed	(Ljava/lang/Runnable;J)Z
        //   612: pop
        //   613: ldc 135
        //   615: ldc 242
        //   617: invokestatic 159	com/millennialmedia/android/MMLog:v	(Ljava/lang/String;Ljava/lang/String;)V
        //   620: iload_2
        //   621: ifeq -585 -> 36
        //   624: aload_0
        //   625: getfield 19	com/millennialmedia/android/HandShake$1:this$0	Lcom/millennialmedia/android/HandShake;
        //   628: aload_1
        //   629: invokestatic 147	com/millennialmedia/android/HandShake:access$1000	(Lcom/millennialmedia/android/HandShake;Landroid/content/Context;)V
        //   632: return
        //   633: astore 12
        //   635: ldc 135
        //   637: ldc 137
        //   639: aload 12
        //   641: invokestatic 143	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   644: aconst_null
        //   645: astore 13
        //   647: goto -328 -> 319
        //   650: astore 6
        //   652: ldc 135
        //   654: ldc 137
        //   656: aload 6
        //   658: invokestatic 143	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   661: iconst_0
        //   662: ifeq -626 -> 36
        //   665: aload_0
        //   666: getfield 19	com/millennialmedia/android/HandShake$1:this$0	Lcom/millennialmedia/android/HandShake;
        //   669: aload_1
        //   670: invokestatic 147	com/millennialmedia/android/HandShake:access$1000	(Lcom/millennialmedia/android/HandShake;Landroid/content/Context;)V
        //   673: return
        //   674: astore 14
        //   676: ldc 135
        //   678: ldc 137
        //   680: aload 14
        //   682: invokestatic 143	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   685: goto -257 -> 428
        //   688: astore 5
        //   690: iload_2
        //   691: ifeq +11 -> 702
        //   694: aload_0
        //   695: getfield 19	com/millennialmedia/android/HandShake$1:this$0	Lcom/millennialmedia/android/HandShake;
        //   698: aload_1
        //   699: invokestatic 147	com/millennialmedia/android/HandShake:access$1000	(Lcom/millennialmedia/android/HandShake;Landroid/content/Context;)V
        //   702: aload 5
        //   704: athrow
        //   705: astore 15
        //   707: ldc 135
        //   709: ldc 137
        //   711: aload 15
        //   713: invokestatic 143	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   716: goto -188 -> 528
        //   719: iconst_0
        //   720: istore_2
        //   721: goto -101 -> 620
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	724	0	this	1
        //   13	686	1	localContext	Context
        //   38	683	2	bool	boolean
        //   46	428	3	localStringBuilder	StringBuilder
        //   54	84	4	localTreeMap	java.util.TreeMap
        //   688	15	5	localObject1	Object
        //   650	7	6	localException	Exception
        //   224	7	7	localIOException1	java.io.IOException
        //   149	13	10	localIterator	Iterator
        //   277	32	11	str1	String
        //   633	7	12	localIOException2	java.io.IOException
        //   317	329	13	localObject2	Object
        //   674	7	14	localIOException3	java.io.IOException
        //   705	7	15	localIOException4	java.io.IOException
        //   483	35	17	str2	String
        //   522	3	18	localHttpResponse1	org.apache.http.HttpResponse
        //   445	8	19	i	int
        //   389	29	21	str3	String
        //   422	3	22	localHttpResponse2	org.apache.http.HttpResponse
        //   336	8	23	j	int
        //   313	3	24	localHttpResponse3	org.apache.http.HttpResponse
        //   171	23	25	localEntry	java.util.Map.Entry
        //   177	36	26	arrayOfObject	Object[]
        // Exception table:
        //   from	to	target	type
        //   39	96	224	java/io/IOException
        //   100	112	224	java/io/IOException
        //   112	131	224	java/io/IOException
        //   131	151	224	java/io/IOException
        //   151	221	224	java/io/IOException
        //   248	298	224	java/io/IOException
        //   324	338	224	java/io/IOException
        //   433	447	224	java/io/IOException
        //   533	620	224	java/io/IOException
        //   635	644	224	java/io/IOException
        //   676	685	224	java/io/IOException
        //   707	716	224	java/io/IOException
        //   298	315	633	java/io/IOException
        //   39	96	650	java/lang/Exception
        //   100	112	650	java/lang/Exception
        //   112	131	650	java/lang/Exception
        //   131	151	650	java/lang/Exception
        //   151	221	650	java/lang/Exception
        //   248	298	650	java/lang/Exception
        //   298	315	650	java/lang/Exception
        //   324	338	650	java/lang/Exception
        //   346	424	650	java/lang/Exception
        //   433	447	650	java/lang/Exception
        //   455	524	650	java/lang/Exception
        //   533	620	650	java/lang/Exception
        //   635	644	650	java/lang/Exception
        //   676	685	650	java/lang/Exception
        //   707	716	650	java/lang/Exception
        //   346	424	674	java/io/IOException
        //   39	96	688	finally
        //   100	112	688	finally
        //   112	131	688	finally
        //   131	151	688	finally
        //   151	221	688	finally
        //   226	235	688	finally
        //   248	298	688	finally
        //   298	315	688	finally
        //   324	338	688	finally
        //   346	424	688	finally
        //   433	447	688	finally
        //   455	524	688	finally
        //   533	620	688	finally
        //   635	644	688	finally
        //   652	661	688	finally
        //   676	685	688	finally
        //   707	716	688	finally
        //   455	524	705	java/io/IOException
      }
    });
  }
  
  private void saveHandShake(Context paramContext)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("MillennialMediaSettings", 0).edit();
    localEditor.putLong("handshake_deferredviewtimeout", this.deferredViewTimeout);
    localEditor.putBoolean("handshake_kill", this.kill);
    localEditor.putString("handshake_baseUrl", adUrl);
    localEditor.putLong("handshake_callback", this.handShakeCallback);
    localEditor.putBoolean("handshake_hardwareAccelerationEnabled", this.hardwareAccelerationEnabled);
    localEditor.putString("handshake_startSessionURL", this.startSessionURL);
    if (this.nuanceCredentials != null) {
      localEditor.putString("handshake_nuanceCredentials", new Gson().toJson(this.nuanceCredentials));
    }
    localEditor.putString("handshake_endSessionURL", this.endSessionURL);
    localEditor.putLong("handshake_creativecaetimeout", this.creativeCacheTimeout);
    localEditor.putString("handshake_mmjs", this.mmjsUrl);
    Iterator localIterator = this.adTypeHandShakes.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      ((AdTypeHandShake)this.adTypeHandShakes.get(str)).save(localEditor, (String)str);
    }
    try
    {
      StringBuilder localStringBuilder = new StringBuilder();
      for (int i = 0; i < this.schemes.size(); i++)
      {
        Scheme localScheme = (Scheme)this.schemes.get(i);
        if (i > 0) {
          localStringBuilder.append("\n");
        }
        localStringBuilder.append(localScheme.scheme + "\t" + localScheme.id);
      }
      localEditor.putString("handshake_schemes", localStringBuilder.toString());
      if (this.cachedVideos != null) {
        localEditor.putString("handshake_cachedvideos5.0", new Gson().toJson(this.cachedVideos));
      }
      localEditor.putString("handshake_novideostocacheurl", this.noVideosToCacheURL);
      localEditor.putLong("handshake_lasthandshake", this.lastHandShake);
      localEditor.commit();
      return;
    }
    finally {}
  }
  
  private void sentFirstLaunch(Context paramContext)
  {
    if (paramContext != null)
    {
      SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("MillennialMediaSettings", 0).edit();
      localEditor.putBoolean("firstlaunchHandshake", false);
      localEditor.commit();
    }
  }
  
  static void setAdUrl(String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
    {
      if (!paramString.endsWith("/")) {
        adUrl = paramString + "/" + "getAd.php5?";
      }
    }
    else {
      return;
    }
    adUrl = paramString + "getAd.php5?";
  }
  
  /* Error */
  static void setHandShakeURL(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: aload_1
    //   4: invokestatic 547	com/millennialmedia/android/HandShake:setHandShakeURL	(Ljava/lang/String;)Z
    //   7: istore_3
    //   8: iload_3
    //   9: ifne +7 -> 16
    //   12: ldc 2
    //   14: monitorexit
    //   15: return
    //   16: iconst_1
    //   17: putstatic 153	com/millennialmedia/android/HandShake:forceRefresh	Z
    //   20: new 2	com/millennialmedia/android/HandShake
    //   23: dup
    //   24: aload_0
    //   25: invokespecial 633	com/millennialmedia/android/HandShake:<init>	(Landroid/content/Context;)V
    //   28: putstatic 635	com/millennialmedia/android/HandShake:sharedInstance	Lcom/millennialmedia/android/HandShake;
    //   31: goto -19 -> 12
    //   34: astore_2
    //   35: ldc 2
    //   37: monitorexit
    //   38: aload_2
    //   39: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	40	0	paramContext	Context
    //   0	40	1	paramString	String
    //   34	5	2	localObject	Object
    //   7	2	3	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   3	8	34	finally
    //   16	31	34	finally
  }
  
  /* Error */
  static boolean setHandShakeURL(String paramString)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: aload_0
    //   4: invokestatic 379	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   7: istore_2
    //   8: iload_2
    //   9: ifeq +10 -> 19
    //   12: iconst_0
    //   13: istore_3
    //   14: ldc 2
    //   16: monitorexit
    //   17: iload_3
    //   18: ireturn
    //   19: aload_0
    //   20: ldc 20
    //   22: invokevirtual 638	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   25: ifeq +12 -> 37
    //   28: aload_0
    //   29: ldc 20
    //   31: ldc 17
    //   33: invokevirtual 641	java/lang/String:replaceFirst	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   36: astore_0
    //   37: new 390	java/lang/StringBuilder
    //   40: dup
    //   41: invokespecial 391	java/lang/StringBuilder:<init>	()V
    //   44: aload_0
    //   45: invokevirtual 397	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   48: ldc 26
    //   50: invokevirtual 397	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   53: invokevirtual 402	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   56: putstatic 84	com/millennialmedia/android/HandShake:handShakeURL	Ljava/lang/String;
    //   59: iconst_1
    //   60: istore_3
    //   61: goto -47 -> 14
    //   64: astore_1
    //   65: ldc 2
    //   67: monitorexit
    //   68: aload_1
    //   69: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	70	0	paramString	String
    //   64	5	1	localObject	Object
    //   7	2	2	bool1	boolean
    //   13	48	3	bool2	boolean
    // Exception table:
    //   from	to	target	type
    //   3	8	64	finally
    //   19	37	64	finally
    //   37	59	64	finally
  }
  
  /* Error */
  static HandShake sharedHandShake(Context paramContext)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 80	com/millennialmedia/android/HandShake:apid	Ljava/lang/String;
    //   6: ifnonnull +18 -> 24
    //   9: ldc 35
    //   11: ldc_w 645
    //   14: invokestatic 223	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   17: aconst_null
    //   18: astore_2
    //   19: ldc 2
    //   21: monitorexit
    //   22: aload_2
    //   23: areturn
    //   24: getstatic 635	com/millennialmedia/android/HandShake:sharedInstance	Lcom/millennialmedia/android/HandShake;
    //   27: ifnonnull +21 -> 48
    //   30: new 2	com/millennialmedia/android/HandShake
    //   33: dup
    //   34: aload_0
    //   35: invokespecial 633	com/millennialmedia/android/HandShake:<init>	(Landroid/content/Context;)V
    //   38: putstatic 635	com/millennialmedia/android/HandShake:sharedInstance	Lcom/millennialmedia/android/HandShake;
    //   41: getstatic 635	com/millennialmedia/android/HandShake:sharedInstance	Lcom/millennialmedia/android/HandShake;
    //   44: astore_2
    //   45: goto -26 -> 19
    //   48: invokestatic 163	java/lang/System:currentTimeMillis	()J
    //   51: getstatic 635	com/millennialmedia/android/HandShake:sharedInstance	Lcom/millennialmedia/android/HandShake;
    //   54: getfield 165	com/millennialmedia/android/HandShake:lastHandShake	J
    //   57: lsub
    //   58: getstatic 635	com/millennialmedia/android/HandShake:sharedInstance	Lcom/millennialmedia/android/HandShake;
    //   61: getfield 124	com/millennialmedia/android/HandShake:handShakeCallback	J
    //   64: lcmp
    //   65: ifle -24 -> 41
    //   68: ldc 35
    //   70: ldc_w 647
    //   73: invokestatic 426	com/millennialmedia/android/MMLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   76: new 2	com/millennialmedia/android/HandShake
    //   79: dup
    //   80: aload_0
    //   81: invokespecial 633	com/millennialmedia/android/HandShake:<init>	(Landroid/content/Context;)V
    //   84: putstatic 635	com/millennialmedia/android/HandShake:sharedInstance	Lcom/millennialmedia/android/HandShake;
    //   87: goto -46 -> 41
    //   90: astore_1
    //   91: ldc 2
    //   93: monitorexit
    //   94: aload_1
    //   95: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	96	0	paramContext	Context
    //   90	5	1	localObject	Object
    //   18	27	2	localHandShake	HandShake
    // Exception table:
    //   from	to	target	type
    //   3	17	90	finally
    //   24	41	90	finally
    //   41	45	90	finally
    //   48	87	90	finally
  }
  
  /* Error */
  boolean canDisplayCachedAd(String paramString, long paramLong)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 109	com/millennialmedia/android/HandShake:adTypeHandShakes	Ljava/util/LinkedHashMap;
    //   6: aload_1
    //   7: invokevirtual 606	java/util/LinkedHashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   10: checkcast 285	com/millennialmedia/android/HandShake$AdTypeHandShake
    //   13: astore 5
    //   15: aload 5
    //   17: ifnull +20 -> 37
    //   20: aload 5
    //   22: lload_2
    //   23: invokevirtual 652	com/millennialmedia/android/HandShake$AdTypeHandShake:canDisplayCachedAd	(J)Z
    //   26: istore 6
    //   28: iload 6
    //   30: istore 7
    //   32: aload_0
    //   33: monitorexit
    //   34: iload 7
    //   36: ireturn
    //   37: iconst_1
    //   38: istore 7
    //   40: goto -8 -> 32
    //   43: astore 4
    //   45: aload_0
    //   46: monitorexit
    //   47: aload 4
    //   49: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	50	0	this	HandShake
    //   0	50	1	paramString	String
    //   0	50	2	paramLong	long
    //   43	5	4	localObject	Object
    //   13	8	5	localAdTypeHandShake	AdTypeHandShake
    //   26	3	6	bool1	boolean
    //   30	9	7	bool2	boolean
    // Exception table:
    //   from	to	target	type
    //   2	15	43	finally
    //   20	28	43	finally
  }
  
  /* Error */
  boolean canRequestVideo(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 109	com/millennialmedia/android/HandShake:adTypeHandShakes	Ljava/util/LinkedHashMap;
    //   6: aload_2
    //   7: invokevirtual 606	java/util/LinkedHashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   10: checkcast 285	com/millennialmedia/android/HandShake$AdTypeHandShake
    //   13: astore 4
    //   15: aload 4
    //   17: ifnull +20 -> 37
    //   20: aload 4
    //   22: aload_1
    //   23: invokevirtual 655	com/millennialmedia/android/HandShake$AdTypeHandShake:canRequestVideo	(Landroid/content/Context;)Z
    //   26: istore 5
    //   28: iload 5
    //   30: istore 6
    //   32: aload_0
    //   33: monitorexit
    //   34: iload 6
    //   36: ireturn
    //   37: iconst_1
    //   38: istore 6
    //   40: goto -8 -> 32
    //   43: astore_3
    //   44: aload_0
    //   45: monitorexit
    //   46: aload_3
    //   47: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	48	0	this	HandShake
    //   0	48	1	paramContext	Context
    //   0	48	2	paramString	String
    //   43	4	3	localObject	Object
    //   13	8	4	localAdTypeHandShake	AdTypeHandShake
    //   26	3	5	bool1	boolean
    //   30	9	6	bool2	boolean
    // Exception table:
    //   from	to	target	type
    //   2	15	43	finally
    //   20	28	43	finally
  }
  
  void endSession()
  {
    if (!TextUtils.isEmpty(this.endSessionURL)) {
      Utils.HttpUtils.executeUrl(this.endSessionURL);
    }
  }
  
  JSONArray getSchemesJSONArray(Context paramContext)
  {
    JSONArray localJSONArray;
    try
    {
      localJSONArray = new JSONArray();
      if (this.schemes.size() > 0)
      {
        Iterator localIterator = this.schemes.iterator();
        while (localIterator.hasNext())
        {
          Scheme localScheme = (Scheme)localIterator.next();
          boolean bool = localScheme.checkAvailability(paramContext);
          if (bool) {
            try
            {
              JSONObject localJSONObject = new JSONObject();
              localJSONObject.put("scheme", localScheme.scheme);
              localJSONObject.put("schemeid", localScheme.id);
              localJSONArray.put(localJSONObject);
            }
            catch (JSONException localJSONException)
            {
              MMLog.e("HandShake", "Json error getting scheme", localJSONException);
            }
          }
        }
      }
    }
    finally {}
    return localJSONArray;
  }
  
  String getSchemesList(Context paramContext)
  {
    StringBuilder localStringBuilder;
    for (;;)
    {
      try
      {
        if ((this.schemesList != null) || (this.schemes.size() <= 0)) {
          break label148;
        }
        localStringBuilder = new StringBuilder();
        Iterator localIterator = this.schemes.iterator();
        if (!localIterator.hasNext()) {
          break;
        }
        Scheme localScheme = (Scheme)localIterator.next();
        if (localScheme.checkAvailability(paramContext)) {
          if (localStringBuilder.length() > 0) {
            localStringBuilder.append("," + localScheme.id);
          } else {
            localStringBuilder.append(Integer.toString(localScheme.id));
          }
        }
      }
      finally {}
    }
    if (localStringBuilder.length() > 0) {
      this.schemesList = localStringBuilder.toString();
    }
    label148:
    String str = this.schemesList;
    return str;
  }
  
  /* Error */
  boolean isAdTypeDownloading(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 109	com/millennialmedia/android/HandShake:adTypeHandShakes	Ljava/util/LinkedHashMap;
    //   6: aload_1
    //   7: invokevirtual 606	java/util/LinkedHashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   10: checkcast 285	com/millennialmedia/android/HandShake$AdTypeHandShake
    //   13: astore_3
    //   14: aload_3
    //   15: ifnull +14 -> 29
    //   18: aload_3
    //   19: getfield 697	com/millennialmedia/android/HandShake$AdTypeHandShake:downloading	Z
    //   22: istore 4
    //   24: aload_0
    //   25: monitorexit
    //   26: iload 4
    //   28: ireturn
    //   29: iconst_0
    //   30: istore 4
    //   32: goto -8 -> 24
    //   35: astore_2
    //   36: aload_0
    //   37: monitorexit
    //   38: aload_2
    //   39: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	40	0	this	HandShake
    //   0	40	1	paramString	String
    //   35	4	2	localObject	Object
    //   13	6	3	localAdTypeHandShake	AdTypeHandShake
    //   22	9	4	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   2	14	35	finally
    //   18	24	35	finally
  }
  
  void lockAdTypeDownload(String paramString)
  {
    try
    {
      AdTypeHandShake localAdTypeHandShake = (AdTypeHandShake)this.adTypeHandShakes.get(paramString);
      if (localAdTypeHandShake != null) {
        localAdTypeHandShake.downloading = true;
      }
      return;
    }
    finally {}
  }
  
  void sendInitRequest()
  {
    requestHandshake(true);
  }
  
  void setMMdid(Context paramContext, String paramString)
  {
    setMMdid(paramContext, paramString, true);
  }
  
  /* Error */
  void setMMdid(Context paramContext, String paramString, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_2
    //   3: ifnull +77 -> 80
    //   6: aload_2
    //   7: invokevirtual 491	java/lang/String:length	()I
    //   10: ifeq +13 -> 23
    //   13: aload_2
    //   14: ldc_w 701
    //   17: invokevirtual 704	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   20: ifeq +60 -> 80
    //   23: aload_0
    //   24: aconst_null
    //   25: putfield 476	com/millennialmedia/android/HandShake:mmdid	Ljava/lang/String;
    //   28: aload_0
    //   29: getfield 476	com/millennialmedia/android/HandShake:mmdid	Ljava/lang/String;
    //   32: invokestatic 708	com/millennialmedia/android/MMSDK:setMMdid	(Ljava/lang/String;)V
    //   35: iload_3
    //   36: ifeq +41 -> 77
    //   39: aload_1
    //   40: ldc_w 437
    //   43: iconst_0
    //   44: invokevirtual 441	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   47: invokeinterface 562 1 0
    //   52: astore 5
    //   54: aload 5
    //   56: ldc_w 474
    //   59: aload_0
    //   60: getfield 476	com/millennialmedia/android/HandShake:mmdid	Ljava/lang/String;
    //   63: invokeinterface 578 3 0
    //   68: pop
    //   69: aload 5
    //   71: invokeinterface 626 1 0
    //   76: pop
    //   77: aload_0
    //   78: monitorexit
    //   79: return
    //   80: aload_0
    //   81: aload_2
    //   82: putfield 476	com/millennialmedia/android/HandShake:mmdid	Ljava/lang/String;
    //   85: goto -57 -> 28
    //   88: astore 4
    //   90: aload_0
    //   91: monitorexit
    //   92: aload 4
    //   94: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	95	0	this	HandShake
    //   0	95	1	paramContext	Context
    //   0	95	2	paramString	String
    //   0	95	3	paramBoolean	boolean
    //   88	5	4	localObject	Object
    //   52	18	5	localEditor	SharedPreferences.Editor
    // Exception table:
    //   from	to	target	type
    //   6	23	88	finally
    //   23	28	88	finally
    //   28	35	88	finally
    //   39	77	88	finally
    //   80	85	88	finally
  }
  
  void startSession()
  {
    if (!TextUtils.isEmpty(this.startSessionURL)) {
      Utils.HttpUtils.executeUrl(this.startSessionURL);
    }
  }
  
  void unlockAdTypeDownload(String paramString)
  {
    try
    {
      AdTypeHandShake localAdTypeHandShake = (AdTypeHandShake)this.adTypeHandShakes.get(paramString);
      if (localAdTypeHandShake != null) {
        localAdTypeHandShake.downloading = false;
      }
      return;
    }
    finally {}
  }
  
  void updateLastVideoViewedTime(Context paramContext, String paramString)
  {
    try
    {
      AdTypeHandShake localAdTypeHandShake = (AdTypeHandShake)this.adTypeHandShakes.get(paramString);
      if (localAdTypeHandShake != null) {
        localAdTypeHandShake.updateLastVideoViewedTime(paramContext, paramString);
      }
      return;
    }
    finally {}
  }
  
  private class AdTypeHandShake
  {
    boolean downloading;
    long lastVideo = 0L;
    long videoInterval = 0L;
    
    AdTypeHandShake() {}
    
    boolean canDisplayCachedAd(long paramLong)
    {
      return System.currentTimeMillis() - paramLong < HandShake.this.deferredViewTimeout;
    }
    
    boolean canRequestVideo(Context paramContext)
    {
      long l = System.currentTimeMillis();
      MMLog.d("HandShake", "canRequestVideo() Current Time:" + l + " Last Video: " + this.lastVideo / 1000L + "  Diff: " + (l - this.lastVideo) / 1000L + "  Video interval: " + this.videoInterval / 1000L);
      return System.currentTimeMillis() - this.lastVideo > this.videoInterval;
    }
    
    void deserializeFromObj(JSONObject paramJSONObject)
    {
      if (paramJSONObject == null) {
        return;
      }
      this.videoInterval = (1000L * paramJSONObject.optLong("videointerval"));
    }
    
    boolean load(SharedPreferences paramSharedPreferences, String paramString)
    {
      boolean bool1 = paramSharedPreferences.contains("handshake_lastvideo_" + paramString);
      boolean bool2 = false;
      if (bool1)
      {
        this.lastVideo = paramSharedPreferences.getLong("handshake_lastvideo_" + paramString, this.lastVideo);
        bool2 = true;
      }
      if (paramSharedPreferences.contains("handshake_videointerval_" + paramString))
      {
        this.videoInterval = paramSharedPreferences.getLong("handshake_videointerval_" + paramString, this.videoInterval);
        bool2 = true;
      }
      return bool2;
    }
    
    void loadLastVideo(Context paramContext, String paramString)
    {
      SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("MillennialMediaSettings", 0);
      if ((localSharedPreferences != null) && (localSharedPreferences.contains("handshake_lastvideo_" + paramString))) {
        this.lastVideo = localSharedPreferences.getLong("handshake_lastvideo_" + paramString, this.lastVideo);
      }
    }
    
    void save(Context paramContext, String paramString)
    {
      SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("MillennialMediaSettings", 0).edit();
      save(localEditor, paramString);
      localEditor.commit();
    }
    
    void save(SharedPreferences.Editor paramEditor, String paramString)
    {
      paramEditor.putLong("handshake_lastvideo_" + paramString, this.lastVideo);
      paramEditor.putLong("handshake_videointerval_" + paramString, this.videoInterval);
    }
    
    void updateLastVideoViewedTime(Context paramContext, String paramString)
    {
      this.lastVideo = System.currentTimeMillis();
      save(paramContext, paramString);
    }
  }
  
  static class NuanceCredentials
  {
    String appID;
    String appKey;
    int port;
    String server;
    String sessionID;
    
    public String toString()
    {
      return "Credentials: appid=" + this.appID + " server=" + this.server + " port=" + this.port + " appKey=" + this.appKey + "sessionID=" + this.sessionID;
    }
  }
  
  private class Scheme
  {
    int id;
    String scheme;
    
    Scheme() {}
    
    Scheme(String paramString, int paramInt)
    {
      this.scheme = paramString;
      this.id = paramInt;
    }
    
    boolean checkAvailability(Context paramContext)
    {
      if (this.scheme.contains("://")) {}
      for (Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse(this.scheme)); paramContext.getPackageManager().queryIntentActivities(localIntent, 65536).size() > 0; localIntent = new Intent("android.intent.action.VIEW", Uri.parse(this.scheme + "://"))) {
        return true;
      }
      return false;
    }
    
    void deserializeFromObj(JSONObject paramJSONObject)
    {
      if (paramJSONObject == null) {
        return;
      }
      this.scheme = paramJSONObject.optString("scheme", null);
      this.id = paramJSONObject.optInt("schemeid");
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.HandShake
 * JD-Core Version:    0.7.0.1
 */