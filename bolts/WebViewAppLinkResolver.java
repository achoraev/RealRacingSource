package bolts;

import android.content.Context;
import android.net.Uri;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WebViewAppLinkResolver
  implements AppLinkResolver
{
  private static final String KEY_AL_VALUE = "value";
  private static final String KEY_ANDROID = "android";
  private static final String KEY_APP_NAME = "app_name";
  private static final String KEY_CLASS = "class";
  private static final String KEY_PACKAGE = "package";
  private static final String KEY_SHOULD_FALLBACK = "should_fallback";
  private static final String KEY_URL = "url";
  private static final String KEY_WEB = "web";
  private static final String KEY_WEB_URL = "url";
  private static final String META_TAG_PREFIX = "al";
  private static final String PREFER_HEADER = "Prefer-Html-Meta-Tags";
  private static final String TAG_EXTRACTION_JAVASCRIPT = "javascript:boltsWebViewAppLinkResolverResult.setValue((function() {  var metaTags = document.getElementsByTagName('meta');  var results = [];  for (var i = 0; i < metaTags.length; i++) {    var property = metaTags[i].getAttribute('property');    if (property && property.substring(0, 'al:'.length) === 'al:') {      var tag = { \"property\": metaTags[i].getAttribute('property') };      if (metaTags[i].hasAttribute('content')) {        tag['content'] = metaTags[i].getAttribute('content');      }      results.push(tag);    }  }  return JSON.stringify(results);})())";
  private final Context context;
  
  public WebViewAppLinkResolver(Context paramContext)
  {
    this.context = paramContext;
  }
  
  private static List<Map<String, Object>> getAlList(Map<String, Object> paramMap, String paramString)
  {
    List localList = (List)paramMap.get(paramString);
    if (localList == null) {
      localList = Collections.emptyList();
    }
    return localList;
  }
  
  private static AppLink makeAppLinkFromAlData(Map<String, Object> paramMap, Uri paramUri)
  {
    ArrayList localArrayList = new ArrayList();
    List localList1 = (List)paramMap.get("android");
    if (localList1 == null) {
      localList1 = Collections.emptyList();
    }
    Iterator localIterator = localList1.iterator();
    if (localIterator.hasNext())
    {
      Map localMap2 = (Map)localIterator.next();
      List localList5 = getAlList(localMap2, "url");
      List localList6 = getAlList(localMap2, "package");
      List localList7 = getAlList(localMap2, "class");
      List localList8 = getAlList(localMap2, "app_name");
      int i = Math.max(localList5.size(), Math.max(localList6.size(), Math.max(localList7.size(), localList8.size())));
      int j = 0;
      label136:
      Object localObject1;
      label176:
      Uri localUri2;
      Object localObject2;
      label222:
      String str2;
      Object localObject3;
      label265:
      String str3;
      if (j < i)
      {
        if (localList5.size() <= j) {
          break label350;
        }
        localObject1 = ((Map)localList5.get(j)).get("value");
        localUri2 = tryCreateUrl((String)localObject1);
        if (localList6.size() <= j) {
          break label356;
        }
        localObject2 = ((Map)localList6.get(j)).get("value");
        str2 = (String)localObject2;
        if (localList7.size() <= j) {
          break label362;
        }
        localObject3 = ((Map)localList7.get(j)).get("value");
        str3 = (String)localObject3;
        if (localList8.size() <= j) {
          break label368;
        }
      }
      label350:
      label356:
      label362:
      label368:
      for (Object localObject4 = ((Map)localList8.get(j)).get("value");; localObject4 = null)
      {
        String str4 = (String)localObject4;
        AppLink.Target localTarget = new AppLink.Target(str2, str3, localUri2, str4);
        localArrayList.add(localTarget);
        j++;
        break label136;
        break;
        localObject1 = null;
        break label176;
        localObject2 = null;
        break label222;
        localObject3 = null;
        break label265;
      }
    }
    Uri localUri1 = paramUri;
    List localList2 = (List)paramMap.get("web");
    if ((localList2 != null) && (localList2.size() > 0))
    {
      Map localMap1 = (Map)localList2.get(0);
      List localList3 = (List)localMap1.get("url");
      List localList4 = (List)localMap1.get("should_fallback");
      if ((localList4 != null) && (localList4.size() > 0))
      {
        String str1 = (String)((Map)localList4.get(0)).get("value");
        if (Arrays.asList(new String[] { "no", "false", "0" }).contains(str1.toLowerCase())) {
          localUri1 = null;
        }
      }
      if ((localUri1 != null) && (localList3 != null) && (localList3.size() > 0)) {
        localUri1 = tryCreateUrl((String)((Map)localList3.get(0)).get("value"));
      }
    }
    AppLink localAppLink = new AppLink(paramUri, localArrayList, localUri1);
    return localAppLink;
  }
  
  private static Map<String, Object> parseAlData(JSONArray paramJSONArray)
    throws JSONException
  {
    HashMap localHashMap = new HashMap();
    int i = 0;
    if (i < paramJSONArray.length())
    {
      JSONObject localJSONObject = paramJSONArray.getJSONObject(i);
      String[] arrayOfString = localJSONObject.getString("property").split(":");
      if (!arrayOfString[0].equals("al")) {}
      for (;;)
      {
        i++;
        break;
        Object localObject1 = localHashMap;
        int j = 1;
        if (j < arrayOfString.length)
        {
          Object localObject2 = (List)((Map)localObject1).get(arrayOfString[j]);
          if (localObject2 == null)
          {
            localObject2 = new ArrayList();
            ((Map)localObject1).put(arrayOfString[j], localObject2);
          }
          if (((List)localObject2).size() > 0) {}
          for (Object localObject3 = (Map)((List)localObject2).get(-1 + ((List)localObject2).size());; localObject3 = null)
          {
            if ((localObject3 == null) || (j == -1 + arrayOfString.length))
            {
              localObject3 = new HashMap();
              ((List)localObject2).add(localObject3);
            }
            localObject1 = localObject3;
            j++;
            break;
          }
        }
        if (localJSONObject.has("content")) {
          if (localJSONObject.isNull("content")) {
            ((Map)localObject1).put("value", null);
          } else {
            ((Map)localObject1).put("value", localJSONObject.getString("content"));
          }
        }
      }
    }
    return localHashMap;
  }
  
  /* Error */
  private static String readFromConnection(URLConnection paramURLConnection)
    throws java.io.IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: instanceof 204
    //   4: ifeq +77 -> 81
    //   7: aload_0
    //   8: checkcast 204	java/net/HttpURLConnection
    //   11: astore 12
    //   13: aload_0
    //   14: invokevirtual 210	java/net/URLConnection:getInputStream	()Ljava/io/InputStream;
    //   17: astore 14
    //   19: aload 14
    //   21: astore_1
    //   22: new 212	java/io/ByteArrayOutputStream
    //   25: dup
    //   26: invokespecial 213	java/io/ByteArrayOutputStream:<init>	()V
    //   29: astore_2
    //   30: sipush 1024
    //   33: newarray byte
    //   35: astore 4
    //   37: aload_1
    //   38: aload 4
    //   40: invokevirtual 219	java/io/InputStream:read	([B)I
    //   43: istore 5
    //   45: iload 5
    //   47: iconst_m1
    //   48: if_icmpeq +41 -> 89
    //   51: aload_2
    //   52: aload 4
    //   54: iconst_0
    //   55: iload 5
    //   57: invokevirtual 223	java/io/ByteArrayOutputStream:write	([BII)V
    //   60: goto -23 -> 37
    //   63: astore_3
    //   64: aload_1
    //   65: invokevirtual 226	java/io/InputStream:close	()V
    //   68: aload_3
    //   69: athrow
    //   70: astore 13
    //   72: aload 12
    //   74: invokevirtual 229	java/net/HttpURLConnection:getErrorStream	()Ljava/io/InputStream;
    //   77: astore_1
    //   78: goto -56 -> 22
    //   81: aload_0
    //   82: invokevirtual 210	java/net/URLConnection:getInputStream	()Ljava/io/InputStream;
    //   85: astore_1
    //   86: goto -64 -> 22
    //   89: aload_0
    //   90: invokevirtual 232	java/net/URLConnection:getContentEncoding	()Ljava/lang/String;
    //   93: astore 6
    //   95: aload 6
    //   97: ifnonnull +64 -> 161
    //   100: aload_0
    //   101: invokevirtual 235	java/net/URLConnection:getContentType	()Ljava/lang/String;
    //   104: ldc 237
    //   106: invokevirtual 184	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   109: astore 7
    //   111: aload 7
    //   113: arraylength
    //   114: istore 8
    //   116: iconst_0
    //   117: istore 9
    //   119: iload 9
    //   121: iload 8
    //   123: if_icmpge +66 -> 189
    //   126: aload 7
    //   128: iload 9
    //   130: aaload
    //   131: invokevirtual 240	java/lang/String:trim	()Ljava/lang/String;
    //   134: astore 10
    //   136: aload 10
    //   138: ldc 242
    //   140: invokevirtual 245	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   143: ifeq +40 -> 183
    //   146: aload 10
    //   148: ldc 242
    //   150: invokevirtual 246	java/lang/String:length	()I
    //   153: invokevirtual 250	java/lang/String:substring	(I)Ljava/lang/String;
    //   156: astore 6
    //   158: goto +31 -> 189
    //   161: new 121	java/lang/String
    //   164: dup
    //   165: aload_2
    //   166: invokevirtual 254	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   169: aload 6
    //   171: invokespecial 257	java/lang/String:<init>	([BLjava/lang/String;)V
    //   174: astore 11
    //   176: aload_1
    //   177: invokevirtual 226	java/io/InputStream:close	()V
    //   180: aload 11
    //   182: areturn
    //   183: iinc 9 1
    //   186: goto -67 -> 119
    //   189: aload 6
    //   191: ifnonnull -30 -> 161
    //   194: ldc_w 259
    //   197: astore 6
    //   199: goto -38 -> 161
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	202	0	paramURLConnection	URLConnection
    //   21	156	1	localInputStream1	java.io.InputStream
    //   29	137	2	localByteArrayOutputStream	java.io.ByteArrayOutputStream
    //   63	6	3	localObject	Object
    //   35	18	4	arrayOfByte	byte[]
    //   43	13	5	i	int
    //   93	105	6	str1	String
    //   109	18	7	arrayOfString	String[]
    //   114	10	8	j	int
    //   117	67	9	k	int
    //   134	13	10	str2	String
    //   174	7	11	str3	String
    //   11	62	12	localHttpURLConnection	HttpURLConnection
    //   70	1	13	localException	Exception
    //   17	3	14	localInputStream2	java.io.InputStream
    // Exception table:
    //   from	to	target	type
    //   22	37	63	finally
    //   37	45	63	finally
    //   51	60	63	finally
    //   89	95	63	finally
    //   100	116	63	finally
    //   126	158	63	finally
    //   161	176	63	finally
    //   13	19	70	java/lang/Exception
  }
  
  private static Uri tryCreateUrl(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    return Uri.parse(paramString);
  }
  
  public Task<AppLink> getAppLinkFromUrlInBackground(final Uri paramUri)
  {
    final Capture localCapture1 = new Capture();
    final Capture localCapture2 = new Capture();
    Task.callInBackground(new Callable()
    {
      public Void call()
        throws Exception
      {
        URL localURL = new URL(paramUri.toString());
        URLConnection localURLConnection = null;
        while (localURL != null)
        {
          localURLConnection = localURL.openConnection();
          if ((localURLConnection instanceof HttpURLConnection)) {
            ((HttpURLConnection)localURLConnection).setInstanceFollowRedirects(true);
          }
          localURLConnection.setRequestProperty("Prefer-Html-Meta-Tags", "al");
          localURLConnection.connect();
          if ((localURLConnection instanceof HttpURLConnection))
          {
            HttpURLConnection localHttpURLConnection = (HttpURLConnection)localURLConnection;
            if ((localHttpURLConnection.getResponseCode() >= 300) && (localHttpURLConnection.getResponseCode() < 400))
            {
              localURL = new URL(localHttpURLConnection.getHeaderField("Location"));
              localHttpURLConnection.disconnect();
            }
            else
            {
              localURL = null;
            }
          }
          else
          {
            localURL = null;
          }
        }
        try
        {
          localCapture1.set(WebViewAppLinkResolver.readFromConnection(localURLConnection));
          localCapture2.set(localURLConnection.getContentType());
          return null;
        }
        finally
        {
          if ((localURLConnection instanceof HttpURLConnection)) {
            ((HttpURLConnection)localURLConnection).disconnect();
          }
        }
      }
    }).onSuccessTask(new Continuation()
    {
      public Task<JSONArray> then(Task<Void> paramAnonymousTask)
        throws Exception
      {
        final Task.TaskCompletionSource localTaskCompletionSource = Task.create();
        WebView localWebView = new WebView(WebViewAppLinkResolver.this.context);
        localWebView.getSettings().setJavaScriptEnabled(true);
        localWebView.setNetworkAvailable(false);
        localWebView.setWebViewClient(new WebViewClient()
        {
          private boolean loaded = false;
          
          private void runJavaScript(WebView paramAnonymous2WebView)
          {
            if (!this.loaded)
            {
              this.loaded = true;
              paramAnonymous2WebView.loadUrl("javascript:boltsWebViewAppLinkResolverResult.setValue((function() {  var metaTags = document.getElementsByTagName('meta');  var results = [];  for (var i = 0; i < metaTags.length; i++) {    var property = metaTags[i].getAttribute('property');    if (property && property.substring(0, 'al:'.length) === 'al:') {      var tag = { \"property\": metaTags[i].getAttribute('property') };      if (metaTags[i].hasAttribute('content')) {        tag['content'] = metaTags[i].getAttribute('content');      }      results.push(tag);    }  }  return JSON.stringify(results);})())");
            }
          }
          
          public void onLoadResource(WebView paramAnonymous2WebView, String paramAnonymous2String)
          {
            super.onLoadResource(paramAnonymous2WebView, paramAnonymous2String);
            runJavaScript(paramAnonymous2WebView);
          }
          
          public void onPageFinished(WebView paramAnonymous2WebView, String paramAnonymous2String)
          {
            super.onPageFinished(paramAnonymous2WebView, paramAnonymous2String);
            runJavaScript(paramAnonymous2WebView);
          }
        });
        localWebView.addJavascriptInterface(new Object()
        {
          @JavascriptInterface
          public void setValue(String paramAnonymous2String)
          {
            try
            {
              localTaskCompletionSource.trySetResult(new JSONArray(paramAnonymous2String));
              return;
            }
            catch (JSONException localJSONException)
            {
              localTaskCompletionSource.trySetError(localJSONException);
            }
          }
        }, "boltsWebViewAppLinkResolverResult");
        Object localObject = localCapture2.get();
        String str = null;
        if (localObject != null) {
          str = ((String)localCapture2.get()).split(";")[0];
        }
        localWebView.loadDataWithBaseURL(paramUri.toString(), (String)localCapture1.get(), str, null, null);
        return localTaskCompletionSource.getTask();
      }
    }, Task.UI_THREAD_EXECUTOR).onSuccess(new Continuation()
    {
      public AppLink then(Task<JSONArray> paramAnonymousTask)
        throws Exception
      {
        return WebViewAppLinkResolver.makeAppLinkFromAlData(WebViewAppLinkResolver.access$000((JSONArray)paramAnonymousTask.getResult()), paramUri);
      }
    });
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     bolts.WebViewAppLinkResolver
 * JD-Core Version:    0.7.0.1
 */