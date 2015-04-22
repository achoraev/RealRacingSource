package com.facebook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import java.util.Iterator;
import java.util.concurrent.Executor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AppLinkData
{
  private static final String APPLINK_BRIDGE_ARGS_KEY = "bridge_args";
  private static final String APPLINK_METHOD_ARGS_KEY = "method_args";
  private static final String APPLINK_VERSION_KEY = "version";
  public static final String ARGUMENTS_NATIVE_CLASS_KEY = "com.facebook.platform.APPLINK_NATIVE_CLASS";
  public static final String ARGUMENTS_NATIVE_URL = "com.facebook.platform.APPLINK_NATIVE_URL";
  public static final String ARGUMENTS_REFERER_DATA_KEY = "referer_data";
  public static final String ARGUMENTS_TAPTIME_KEY = "com.facebook.platform.APPLINK_TAP_TIME_UTC";
  private static final String BRIDGE_ARGS_METHOD_KEY = "method";
  private static final String BUNDLE_AL_APPLINK_DATA_KEY = "al_applink_data";
  static final String BUNDLE_APPLINK_ARGS_KEY = "com.facebook.platform.APPLINK_ARGS";
  private static final String DEFERRED_APP_LINK_ARGS_FIELD = "applink_args";
  private static final String DEFERRED_APP_LINK_CLASS_FIELD = "applink_class";
  private static final String DEFERRED_APP_LINK_CLICK_TIME_FIELD = "click_time";
  private static final String DEFERRED_APP_LINK_EVENT = "DEFERRED_APP_LINK";
  private static final String DEFERRED_APP_LINK_PATH = "%s/activities";
  private static final String DEFERRED_APP_LINK_URL_FIELD = "applink_url";
  private static final String METHOD_ARGS_REF_KEY = "ref";
  private static final String METHOD_ARGS_TARGET_URL_KEY = "target_url";
  private static final String REFERER_DATA_REF_KEY = "fb_ref";
  private static final String TAG = AppLinkData.class.getCanonicalName();
  private Bundle argumentBundle;
  private JSONObject arguments;
  private String ref;
  private Uri targetUri;
  
  public static AppLinkData createFromActivity(Activity paramActivity)
  {
    Validate.notNull(paramActivity, "activity");
    Intent localIntent = paramActivity.getIntent();
    AppLinkData localAppLinkData;
    if (localIntent == null) {
      localAppLinkData = null;
    }
    do
    {
      return localAppLinkData;
      localAppLinkData = createFromAlApplinkData(localIntent);
      if (localAppLinkData == null) {
        localAppLinkData = createFromJson(localIntent.getStringExtra("com.facebook.platform.APPLINK_ARGS"));
      }
    } while (localAppLinkData != null);
    return createFromUri(localIntent.getData());
  }
  
  private static AppLinkData createFromAlApplinkData(Intent paramIntent)
  {
    Bundle localBundle1 = paramIntent.getBundleExtra("al_applink_data");
    AppLinkData localAppLinkData;
    if (localBundle1 == null) {
      localAppLinkData = null;
    }
    Bundle localBundle2;
    do
    {
      return localAppLinkData;
      localAppLinkData = new AppLinkData();
      localAppLinkData.targetUri = paramIntent.getData();
      if (localAppLinkData.targetUri == null)
      {
        String str = localBundle1.getString("target_url");
        if (str != null) {
          localAppLinkData.targetUri = Uri.parse(str);
        }
      }
      localAppLinkData.argumentBundle = localBundle1;
      localAppLinkData.arguments = null;
      localBundle2 = localBundle1.getBundle("referer_data");
    } while (localBundle2 == null);
    localAppLinkData.ref = localBundle2.getString("fb_ref");
    return localAppLinkData;
  }
  
  private static AppLinkData createFromJson(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    try
    {
      JSONObject localJSONObject1 = new JSONObject(paramString);
      String str = localJSONObject1.getString("version");
      if ((localJSONObject1.getJSONObject("bridge_args").getString("method").equals("applink")) && (str.equals("2")))
      {
        localAppLinkData = new AppLinkData();
        localAppLinkData.arguments = localJSONObject1.getJSONObject("method_args");
        if (!localAppLinkData.arguments.has("ref")) {
          break label162;
        }
        localAppLinkData.ref = localAppLinkData.arguments.getString("ref");
        if (localAppLinkData.arguments.has("target_url")) {
          localAppLinkData.targetUri = Uri.parse(localAppLinkData.arguments.getString("target_url"));
        }
        localAppLinkData.argumentBundle = toBundle(localAppLinkData.arguments);
        return localAppLinkData;
      }
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        AppLinkData localAppLinkData;
        Log.d(TAG, "Unable to parse AppLink JSON", localJSONException);
        return null;
        if (localAppLinkData.arguments.has("referer_data"))
        {
          JSONObject localJSONObject2 = localAppLinkData.arguments.getJSONObject("referer_data");
          if (localJSONObject2.has("fb_ref")) {
            localAppLinkData.ref = localJSONObject2.getString("fb_ref");
          }
        }
      }
    }
    catch (FacebookException localFacebookException)
    {
      for (;;)
      {
        label162:
        Log.d(TAG, "Unable to parse AppLink JSON", localFacebookException);
      }
    }
  }
  
  private static AppLinkData createFromUri(Uri paramUri)
  {
    if (paramUri == null) {
      return null;
    }
    AppLinkData localAppLinkData = new AppLinkData();
    localAppLinkData.targetUri = paramUri;
    return localAppLinkData;
  }
  
  public static void fetchDeferredAppLinkData(Context paramContext, CompletionHandler paramCompletionHandler)
  {
    fetchDeferredAppLinkData(paramContext, null, paramCompletionHandler);
  }
  
  public static void fetchDeferredAppLinkData(Context paramContext, String paramString, final CompletionHandler paramCompletionHandler)
  {
    Validate.notNull(paramContext, "context");
    Validate.notNull(paramCompletionHandler, "completionHandler");
    if (paramString == null) {
      paramString = Utility.getMetadataApplicationId(paramContext);
    }
    Validate.notNull(paramString, "applicationId");
    Context localContext = paramContext.getApplicationContext();
    final String str = paramString;
    Settings.getExecutor().execute(new Runnable()
    {
      public void run()
      {
        AppLinkData.fetchDeferredAppLinkFromServer(this.val$applicationContext, str, paramCompletionHandler);
      }
    });
  }
  
  /* Error */
  private static void fetchDeferredAppLinkFromServer(Context paramContext, String paramString, CompletionHandler paramCompletionHandler)
  {
    // Byte code:
    //   0: invokestatic 238	com/facebook/model/GraphObject$Factory:create	()Lcom/facebook/model/GraphObject;
    //   3: astore_3
    //   4: aload_3
    //   5: ldc 240
    //   7: ldc 47
    //   9: invokeinterface 246 3 0
    //   14: aload_3
    //   15: aload_0
    //   16: invokestatic 252	com/facebook/internal/AttributionIdentifiers:getAttributionIdentifiers	(Landroid/content/Context;)Lcom/facebook/internal/AttributionIdentifiers;
    //   19: aload_0
    //   20: aload_1
    //   21: invokestatic 256	com/facebook/internal/Utility:getHashedDeviceAndAppID	(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/String;
    //   24: aload_0
    //   25: invokestatic 260	com/facebook/Settings:getLimitEventAndDataUsage	(Landroid/content/Context;)Z
    //   28: invokestatic 264	com/facebook/internal/Utility:setAppEventAttributionParameters	(Lcom/facebook/model/GraphObject;Lcom/facebook/internal/AttributionIdentifiers;Ljava/lang/String;Z)V
    //   31: aload_3
    //   32: ldc_w 266
    //   35: aload_0
    //   36: invokevirtual 269	android/content/Context:getPackageName	()Ljava/lang/String;
    //   39: invokeinterface 246 3 0
    //   44: ldc 50
    //   46: iconst_1
    //   47: anewarray 4	java/lang/Object
    //   50: dup
    //   51: iconst_0
    //   52: aload_1
    //   53: aastore
    //   54: invokestatic 273	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   57: astore 4
    //   59: aconst_null
    //   60: astore 5
    //   62: aconst_null
    //   63: aload 4
    //   65: aload_3
    //   66: aconst_null
    //   67: invokestatic 279	com/facebook/Request:newPostRequest	(Lcom/facebook/Session;Ljava/lang/String;Lcom/facebook/model/GraphObject;Lcom/facebook/Request$Callback;)Lcom/facebook/Request;
    //   70: invokevirtual 283	com/facebook/Request:executeAndWait	()Lcom/facebook/Response;
    //   73: invokevirtual 288	com/facebook/Response:getGraphObject	()Lcom/facebook/model/GraphObject;
    //   76: astore 7
    //   78: aconst_null
    //   79: astore 5
    //   81: aload 7
    //   83: ifnull +239 -> 322
    //   86: aload 7
    //   88: invokeinterface 292 1 0
    //   93: astore 8
    //   95: aconst_null
    //   96: astore 5
    //   98: aload 8
    //   100: ifnull +213 -> 313
    //   103: aload 8
    //   105: ldc 38
    //   107: invokevirtual 295	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   110: astore 9
    //   112: aload 8
    //   114: ldc 44
    //   116: ldc2_w 296
    //   119: invokevirtual 301	org/json/JSONObject:optLong	(Ljava/lang/String;J)J
    //   122: lstore 10
    //   124: aload 8
    //   126: ldc 41
    //   128: invokevirtual 295	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   131: astore 12
    //   133: aload 8
    //   135: ldc 53
    //   137: invokevirtual 295	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   140: astore 13
    //   142: aload 9
    //   144: invokestatic 307	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   147: istore 14
    //   149: aconst_null
    //   150: astore 5
    //   152: iload 14
    //   154: ifne +159 -> 313
    //   157: aload 9
    //   159: invokestatic 117	com/facebook/AppLinkData:createFromJson	(Ljava/lang/String;)Lcom/facebook/AppLinkData;
    //   162: astore 15
    //   164: aload 15
    //   166: astore 5
    //   168: lload 10
    //   170: ldc2_w 296
    //   173: lcmp
    //   174: ifeq +47 -> 221
    //   177: aload 5
    //   179: getfield 147	com/facebook/AppLinkData:arguments	Lorg/json/JSONObject;
    //   182: ifnull +16 -> 198
    //   185: aload 5
    //   187: getfield 147	com/facebook/AppLinkData:arguments	Lorg/json/JSONObject;
    //   190: ldc 26
    //   192: lload 10
    //   194: invokevirtual 311	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   197: pop
    //   198: aload 5
    //   200: getfield 145	com/facebook/AppLinkData:argumentBundle	Landroid/os/Bundle;
    //   203: ifnull +18 -> 221
    //   206: aload 5
    //   208: getfield 145	com/facebook/AppLinkData:argumentBundle	Landroid/os/Bundle;
    //   211: ldc 26
    //   213: lload 10
    //   215: invokestatic 317	java/lang/Long:toString	(J)Ljava/lang/String;
    //   218: invokevirtual 321	android/os/Bundle:putString	(Ljava/lang/String;Ljava/lang/String;)V
    //   221: aload 12
    //   223: ifnull +44 -> 267
    //   226: aload 5
    //   228: getfield 147	com/facebook/AppLinkData:arguments	Lorg/json/JSONObject;
    //   231: ifnull +16 -> 247
    //   234: aload 5
    //   236: getfield 147	com/facebook/AppLinkData:arguments	Lorg/json/JSONObject;
    //   239: ldc 17
    //   241: aload 12
    //   243: invokevirtual 324	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   246: pop
    //   247: aload 5
    //   249: getfield 145	com/facebook/AppLinkData:argumentBundle	Landroid/os/Bundle;
    //   252: ifnull +15 -> 267
    //   255: aload 5
    //   257: getfield 145	com/facebook/AppLinkData:argumentBundle	Landroid/os/Bundle;
    //   260: ldc 17
    //   262: aload 12
    //   264: invokevirtual 321	android/os/Bundle:putString	(Ljava/lang/String;Ljava/lang/String;)V
    //   267: aload 13
    //   269: ifnull +44 -> 313
    //   272: aload 5
    //   274: getfield 147	com/facebook/AppLinkData:arguments	Lorg/json/JSONObject;
    //   277: ifnull +16 -> 293
    //   280: aload 5
    //   282: getfield 147	com/facebook/AppLinkData:arguments	Lorg/json/JSONObject;
    //   285: ldc 20
    //   287: aload 13
    //   289: invokevirtual 324	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   292: pop
    //   293: aload 5
    //   295: getfield 145	com/facebook/AppLinkData:argumentBundle	Landroid/os/Bundle;
    //   298: ifnull +15 -> 313
    //   301: aload 5
    //   303: getfield 145	com/facebook/AppLinkData:argumentBundle	Landroid/os/Bundle;
    //   306: ldc 20
    //   308: aload 13
    //   310: invokevirtual 321	android/os/Bundle:putString	(Ljava/lang/String;Ljava/lang/String;)V
    //   313: aload_2
    //   314: aload 5
    //   316: invokeinterface 330 2 0
    //   321: return
    //   322: aconst_null
    //   323: astore 8
    //   325: goto -230 -> 95
    //   328: astore 22
    //   330: getstatic 79	com/facebook/AppLinkData:TAG	Ljava/lang/String;
    //   333: ldc_w 332
    //   336: invokestatic 335	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   339: pop
    //   340: goto -119 -> 221
    //   343: astore 6
    //   345: getstatic 79	com/facebook/AppLinkData:TAG	Ljava/lang/String;
    //   348: ldc_w 337
    //   351: invokestatic 340	com/facebook/internal/Utility:logd	(Ljava/lang/String;Ljava/lang/String;)V
    //   354: goto -41 -> 313
    //   357: astore 19
    //   359: getstatic 79	com/facebook/AppLinkData:TAG	Ljava/lang/String;
    //   362: ldc_w 332
    //   365: invokestatic 335	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   368: pop
    //   369: goto -102 -> 267
    //   372: astore 16
    //   374: getstatic 79	com/facebook/AppLinkData:TAG	Ljava/lang/String;
    //   377: ldc_w 332
    //   380: invokestatic 335	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   383: pop
    //   384: goto -71 -> 313
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	387	0	paramContext	Context
    //   0	387	1	paramString	String
    //   0	387	2	paramCompletionHandler	CompletionHandler
    //   3	63	3	localGraphObject1	com.facebook.model.GraphObject
    //   57	7	4	str1	String
    //   60	255	5	localObject	Object
    //   343	1	6	localException	java.lang.Exception
    //   76	11	7	localGraphObject2	com.facebook.model.GraphObject
    //   93	231	8	localJSONObject	JSONObject
    //   110	48	9	str2	String
    //   122	92	10	l	long
    //   131	132	12	str3	String
    //   140	169	13	str4	String
    //   147	6	14	bool	boolean
    //   162	3	15	localAppLinkData	AppLinkData
    //   372	1	16	localJSONException1	JSONException
    //   357	1	19	localJSONException2	JSONException
    //   328	1	22	localJSONException3	JSONException
    // Exception table:
    //   from	to	target	type
    //   177	198	328	org/json/JSONException
    //   198	221	328	org/json/JSONException
    //   62	78	343	java/lang/Exception
    //   86	95	343	java/lang/Exception
    //   103	149	343	java/lang/Exception
    //   157	164	343	java/lang/Exception
    //   177	198	343	java/lang/Exception
    //   198	221	343	java/lang/Exception
    //   226	247	343	java/lang/Exception
    //   247	267	343	java/lang/Exception
    //   272	293	343	java/lang/Exception
    //   293	313	343	java/lang/Exception
    //   330	340	343	java/lang/Exception
    //   359	369	343	java/lang/Exception
    //   374	384	343	java/lang/Exception
    //   226	247	357	org/json/JSONException
    //   247	267	357	org/json/JSONException
    //   272	293	372	org/json/JSONException
    //   293	313	372	org/json/JSONException
  }
  
  private static Bundle toBundle(JSONObject paramJSONObject)
    throws JSONException
  {
    Bundle localBundle = new Bundle();
    Iterator localIterator = paramJSONObject.keys();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      Object localObject1 = paramJSONObject.get(str);
      if ((localObject1 instanceof JSONObject))
      {
        localBundle.putBundle(str, toBundle((JSONObject)localObject1));
      }
      else if ((localObject1 instanceof JSONArray))
      {
        JSONArray localJSONArray = (JSONArray)localObject1;
        if (localJSONArray.length() == 0)
        {
          localBundle.putStringArray(str, new String[0]);
        }
        else
        {
          Object localObject2 = localJSONArray.get(0);
          if ((localObject2 instanceof JSONObject))
          {
            Bundle[] arrayOfBundle = new Bundle[localJSONArray.length()];
            for (int j = 0; j < localJSONArray.length(); j++) {
              arrayOfBundle[j] = toBundle(localJSONArray.getJSONObject(j));
            }
            localBundle.putParcelableArray(str, arrayOfBundle);
          }
          else
          {
            if ((localObject2 instanceof JSONArray)) {
              throw new FacebookException("Nested arrays are not supported.");
            }
            String[] arrayOfString = new String[localJSONArray.length()];
            for (int i = 0; i < localJSONArray.length(); i++) {
              arrayOfString[i] = localJSONArray.get(i).toString();
            }
            localBundle.putStringArray(str, arrayOfString);
          }
        }
      }
      else
      {
        localBundle.putString(str, localObject1.toString());
      }
    }
    return localBundle;
  }
  
  public Bundle getArgumentBundle()
  {
    return this.argumentBundle;
  }
  
  @Deprecated
  public JSONObject getArguments()
  {
    return this.arguments;
  }
  
  public String getRef()
  {
    return this.ref;
  }
  
  public Bundle getRefererData()
  {
    if (this.argumentBundle != null) {
      return this.argumentBundle.getBundle("referer_data");
    }
    return null;
  }
  
  public Uri getTargetUri()
  {
    return this.targetUri;
  }
  
  public static abstract interface CompletionHandler
  {
    public abstract void onDeferredAppLinkDataFetched(AppLinkData paramAppLinkData);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.facebook.AppLinkData
 * JD-Core Version:    0.7.0.1
 */