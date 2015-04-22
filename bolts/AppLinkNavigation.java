package bolts;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.util.SparseArray;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AppLinkNavigation
{
  private static final String KEY_NAME_REFERER_APP_LINK = "referer_app_link";
  private static final String KEY_NAME_REFERER_APP_LINK_APP_NAME = "app_name";
  private static final String KEY_NAME_REFERER_APP_LINK_PACKAGE = "package";
  private static final String KEY_NAME_USER_AGENT = "user_agent";
  private static final String KEY_NAME_VERSION = "version";
  private static final String VERSION = "1.0";
  private static AppLinkResolver defaultResolver;
  private final AppLink appLink;
  private final Bundle appLinkData;
  private final Bundle extras;
  
  public AppLinkNavigation(AppLink paramAppLink, Bundle paramBundle1, Bundle paramBundle2)
  {
    if (paramAppLink == null) {
      throw new IllegalArgumentException("appLink must not be null.");
    }
    if (paramBundle1 == null) {
      paramBundle1 = new Bundle();
    }
    if (paramBundle2 == null) {
      paramBundle2 = new Bundle();
    }
    this.appLink = paramAppLink;
    this.extras = paramBundle1;
    this.appLinkData = paramBundle2;
  }
  
  private Bundle buildAppLinkDataForNavigation(Context paramContext)
  {
    Bundle localBundle1 = new Bundle();
    Bundle localBundle2 = new Bundle();
    if (paramContext != null)
    {
      String str1 = paramContext.getPackageName();
      if (str1 != null) {
        localBundle2.putString("package", str1);
      }
      ApplicationInfo localApplicationInfo = paramContext.getApplicationInfo();
      if (localApplicationInfo != null)
      {
        String str2 = paramContext.getString(localApplicationInfo.labelRes);
        if (str2 != null) {
          localBundle2.putString("app_name", str2);
        }
      }
    }
    localBundle1.putAll(getAppLinkData());
    localBundle1.putString("target_url", getAppLink().getSourceUrl().toString());
    localBundle1.putString("version", "1.0");
    localBundle1.putString("user_agent", "Bolts Android 1.1.2");
    localBundle1.putBundle("referer_app_link", localBundle2);
    localBundle1.putBundle("extras", getExtras());
    return localBundle1;
  }
  
  public static AppLinkResolver getDefaultResolver()
  {
    return defaultResolver;
  }
  
  private JSONObject getJSONForBundle(Bundle paramBundle)
    throws JSONException
  {
    JSONObject localJSONObject = new JSONObject();
    Iterator localIterator = paramBundle.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      localJSONObject.put(str, getJSONValue(paramBundle.get(str)));
    }
    return localJSONObject;
  }
  
  private Object getJSONValue(Object paramObject)
    throws JSONException
  {
    Object localObject;
    if ((paramObject instanceof Bundle)) {
      localObject = getJSONForBundle((Bundle)paramObject);
    }
    for (;;)
    {
      return localObject;
      if ((paramObject instanceof CharSequence)) {
        return paramObject.toString();
      }
      if ((paramObject instanceof List))
      {
        localObject = new JSONArray();
        Iterator localIterator = ((List)paramObject).iterator();
        while (localIterator.hasNext()) {
          ((JSONArray)localObject).put(getJSONValue(localIterator.next()));
        }
      }
      else if ((paramObject instanceof SparseArray))
      {
        localObject = new JSONArray();
        SparseArray localSparseArray = (SparseArray)paramObject;
        for (int i = 0; i < localSparseArray.size(); i++) {
          ((JSONArray)localObject).put(localSparseArray.keyAt(i), getJSONValue(localSparseArray.valueAt(i)));
        }
      }
      else
      {
        if ((paramObject instanceof Character)) {
          return paramObject.toString();
        }
        if ((paramObject instanceof Boolean)) {
          return paramObject;
        }
        if ((paramObject instanceof Number))
        {
          if (((paramObject instanceof Double)) || ((paramObject instanceof Float))) {
            return Double.valueOf(((Number)paramObject).doubleValue());
          }
          return Long.valueOf(((Number)paramObject).longValue());
        }
        if ((paramObject instanceof boolean[]))
        {
          localObject = new JSONArray();
          boolean[] arrayOfBoolean = (boolean[])paramObject;
          int j = arrayOfBoolean.length;
          for (int k = 0; k < j; k++) {
            ((JSONArray)localObject).put(getJSONValue(Boolean.valueOf(arrayOfBoolean[k])));
          }
        }
        else if ((paramObject instanceof char[]))
        {
          localObject = new JSONArray();
          char[] arrayOfChar = (char[])paramObject;
          int m = arrayOfChar.length;
          for (int n = 0; n < m; n++) {
            ((JSONArray)localObject).put(getJSONValue(Character.valueOf(arrayOfChar[n])));
          }
        }
        else if ((paramObject instanceof CharSequence[]))
        {
          localObject = new JSONArray();
          CharSequence[] arrayOfCharSequence = (CharSequence[])paramObject;
          int i1 = arrayOfCharSequence.length;
          for (int i2 = 0; i2 < i1; i2++) {
            ((JSONArray)localObject).put(getJSONValue(arrayOfCharSequence[i2]));
          }
        }
        else if ((paramObject instanceof double[]))
        {
          localObject = new JSONArray();
          double[] arrayOfDouble = (double[])paramObject;
          int i3 = arrayOfDouble.length;
          for (int i4 = 0; i4 < i3; i4++) {
            ((JSONArray)localObject).put(getJSONValue(Double.valueOf(arrayOfDouble[i4])));
          }
        }
        else if ((paramObject instanceof float[]))
        {
          localObject = new JSONArray();
          float[] arrayOfFloat = (float[])paramObject;
          int i5 = arrayOfFloat.length;
          for (int i6 = 0; i6 < i5; i6++) {
            ((JSONArray)localObject).put(getJSONValue(Float.valueOf(arrayOfFloat[i6])));
          }
        }
        else if ((paramObject instanceof int[]))
        {
          localObject = new JSONArray();
          int[] arrayOfInt = (int[])paramObject;
          int i7 = arrayOfInt.length;
          for (int i8 = 0; i8 < i7; i8++) {
            ((JSONArray)localObject).put(getJSONValue(Integer.valueOf(arrayOfInt[i8])));
          }
        }
        else if ((paramObject instanceof long[]))
        {
          localObject = new JSONArray();
          long[] arrayOfLong = (long[])paramObject;
          int i9 = arrayOfLong.length;
          for (int i10 = 0; i10 < i9; i10++) {
            ((JSONArray)localObject).put(getJSONValue(Long.valueOf(arrayOfLong[i10])));
          }
        }
        else if ((paramObject instanceof short[]))
        {
          localObject = new JSONArray();
          short[] arrayOfShort = (short[])paramObject;
          int i11 = arrayOfShort.length;
          for (int i12 = 0; i12 < i11; i12++) {
            ((JSONArray)localObject).put(getJSONValue(Short.valueOf(arrayOfShort[i12])));
          }
        }
        else
        {
          if (!(paramObject instanceof String[])) {
            break;
          }
          localObject = new JSONArray();
          String[] arrayOfString = (String[])paramObject;
          int i13 = arrayOfString.length;
          for (int i14 = 0; i14 < i13; i14++) {
            ((JSONArray)localObject).put(getJSONValue(arrayOfString[i14]));
          }
        }
      }
    }
    return null;
  }
  
  private static AppLinkResolver getResolver(Context paramContext)
  {
    if (getDefaultResolver() != null) {
      return getDefaultResolver();
    }
    return new WebViewAppLinkResolver(paramContext);
  }
  
  public static NavigationResult navigate(Context paramContext, AppLink paramAppLink)
  {
    return new AppLinkNavigation(paramAppLink, null, null).navigate(paramContext);
  }
  
  public static Task<NavigationResult> navigateInBackground(Context paramContext, Uri paramUri)
  {
    return navigateInBackground(paramContext, paramUri, getResolver(paramContext));
  }
  
  public static Task<NavigationResult> navigateInBackground(Context paramContext, Uri paramUri, AppLinkResolver paramAppLinkResolver)
  {
    paramAppLinkResolver.getAppLinkFromUrlInBackground(paramUri).onSuccess(new Continuation()
    {
      public AppLinkNavigation.NavigationResult then(Task<AppLink> paramAnonymousTask)
        throws Exception
      {
        return AppLinkNavigation.navigate(this.val$context, (AppLink)paramAnonymousTask.getResult());
      }
    }, Task.UI_THREAD_EXECUTOR);
  }
  
  public static Task<NavigationResult> navigateInBackground(Context paramContext, String paramString)
  {
    return navigateInBackground(paramContext, paramString, getResolver(paramContext));
  }
  
  public static Task<NavigationResult> navigateInBackground(Context paramContext, String paramString, AppLinkResolver paramAppLinkResolver)
  {
    return navigateInBackground(paramContext, Uri.parse(paramString), paramAppLinkResolver);
  }
  
  public static Task<NavigationResult> navigateInBackground(Context paramContext, URL paramURL)
  {
    return navigateInBackground(paramContext, paramURL, getResolver(paramContext));
  }
  
  public static Task<NavigationResult> navigateInBackground(Context paramContext, URL paramURL, AppLinkResolver paramAppLinkResolver)
  {
    return navigateInBackground(paramContext, Uri.parse(paramURL.toString()), paramAppLinkResolver);
  }
  
  private void sendAppLinkNavigateEventBroadcast(Context paramContext, Intent paramIntent, NavigationResult paramNavigationResult, JSONException paramJSONException)
  {
    HashMap localHashMap = new HashMap();
    if (paramJSONException != null) {
      localHashMap.put("error", paramJSONException.getLocalizedMessage());
    }
    if (paramNavigationResult.isSucceeded()) {}
    for (String str = "1";; str = "0")
    {
      localHashMap.put("success", str);
      localHashMap.put("type", paramNavigationResult.getCode());
      MeasurementEvent.sendBroadcastEvent(paramContext, "al_nav_out", paramIntent, localHashMap);
      return;
    }
  }
  
  public static void setDefaultResolver(AppLinkResolver paramAppLinkResolver)
  {
    defaultResolver = paramAppLinkResolver;
  }
  
  public AppLink getAppLink()
  {
    return this.appLink;
  }
  
  public Bundle getAppLinkData()
  {
    return this.appLinkData;
  }
  
  public Bundle getExtras()
  {
    return this.extras;
  }
  
  public NavigationResult navigate(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    Bundle localBundle = buildAppLinkDataForNavigation(paramContext);
    Iterator localIterator = getAppLink().getTargets().iterator();
    Intent localIntent;
    do
    {
      boolean bool = localIterator.hasNext();
      localObject1 = null;
      if (!bool) {
        break;
      }
      AppLink.Target localTarget = (AppLink.Target)localIterator.next();
      localIntent = new Intent("android.intent.action.VIEW");
      if (localTarget.getUrl() == null) {
        break label189;
      }
      localIntent.setData(localTarget.getUrl());
      localIntent.setPackage(localTarget.getPackageName());
      if (localTarget.getClassName() != null) {
        localIntent.setClassName(localTarget.getPackageName(), localTarget.getClassName());
      }
      localIntent.putExtra("al_applink_data", localBundle);
    } while (localPackageManager.resolveActivity(localIntent, 65536) == null);
    Object localObject1 = localIntent;
    NavigationResult localNavigationResult = NavigationResult.FAILED;
    Object localObject2;
    if (localObject1 != null)
    {
      localObject2 = localObject1;
      localNavigationResult = NavigationResult.APP;
    }
    for (;;)
    {
      sendAppLinkNavigateEventBroadcast(paramContext, (Intent)localObject2, localNavigationResult, null);
      if (localObject2 != null) {
        paramContext.startActivity((Intent)localObject2);
      }
      return localNavigationResult;
      label189:
      localIntent.setData(this.appLink.getSourceUrl());
      break;
      Uri localUri = getAppLink().getWebUrl();
      localObject2 = null;
      if (localUri == null) {
        continue;
      }
      try
      {
        JSONObject localJSONObject = getJSONForBundle(localBundle);
        localObject2 = new Intent("android.intent.action.VIEW", localUri.buildUpon().appendQueryParameter("al_applink_data", localJSONObject.toString()).build());
        localNavigationResult = NavigationResult.WEB;
      }
      catch (JSONException localJSONException)
      {
        sendAppLinkNavigateEventBroadcast(paramContext, localObject1, NavigationResult.FAILED, localJSONException);
        throw new RuntimeException(localJSONException);
      }
    }
  }
  
  public static enum NavigationResult
  {
    private String code;
    private boolean succeeded;
    
    static
    {
      APP = new NavigationResult("APP", 2, "app", true);
      NavigationResult[] arrayOfNavigationResult = new NavigationResult[3];
      arrayOfNavigationResult[0] = FAILED;
      arrayOfNavigationResult[1] = WEB;
      arrayOfNavigationResult[2] = APP;
      $VALUES = arrayOfNavigationResult;
    }
    
    private NavigationResult(String paramString, boolean paramBoolean)
    {
      this.code = paramString;
      this.succeeded = paramBoolean;
    }
    
    public String getCode()
    {
      return this.code;
    }
    
    public boolean isSucceeded()
    {
      return this.succeeded;
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     bolts.AppLinkNavigation
 * JD-Core Version:    0.7.0.1
 */