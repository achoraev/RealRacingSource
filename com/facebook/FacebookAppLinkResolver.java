package com.facebook;

import android.net.Uri;
import android.os.Bundle;
import bolts.AppLink;
import bolts.AppLink.Target;
import bolts.AppLinkResolver;
import bolts.Continuation;
import bolts.Task;
import bolts.Task.TaskCompletionSource;
import com.facebook.model.GraphObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FacebookAppLinkResolver
  implements AppLinkResolver
{
  private static final String APP_LINK_ANDROID_TARGET_KEY = "android";
  private static final String APP_LINK_KEY = "app_links";
  private static final String APP_LINK_TARGET_APP_NAME_KEY = "app_name";
  private static final String APP_LINK_TARGET_CLASS_KEY = "class";
  private static final String APP_LINK_TARGET_PACKAGE_KEY = "package";
  private static final String APP_LINK_TARGET_SHOULD_FALLBACK_KEY = "should_fallback";
  private static final String APP_LINK_TARGET_URL_KEY = "url";
  private static final String APP_LINK_WEB_TARGET_KEY = "web";
  private final HashMap<Uri, AppLink> cachedAppLinks = new HashMap();
  
  private static AppLink.Target getAndroidTargetFromJson(JSONObject paramJSONObject)
  {
    String str1 = tryGetStringFromJson(paramJSONObject, "package", null);
    if (str1 == null) {
      return null;
    }
    String str2 = tryGetStringFromJson(paramJSONObject, "class", null);
    String str3 = tryGetStringFromJson(paramJSONObject, "app_name", null);
    String str4 = tryGetStringFromJson(paramJSONObject, "url", null);
    Uri localUri = null;
    if (str4 != null) {
      localUri = Uri.parse(str4);
    }
    return new AppLink.Target(str1, str2, localUri, str3);
  }
  
  private static Uri getWebFallbackUriFromJson(Uri paramUri, JSONObject paramJSONObject)
  {
    Object localObject;
    try
    {
      JSONObject localJSONObject = paramJSONObject.getJSONObject("web");
      if (!tryGetBooleanFromJson(localJSONObject, "should_fallback", true)) {
        return null;
      }
      String str = tryGetStringFromJson(localJSONObject, "url", null);
      localObject = null;
      if (str != null)
      {
        Uri localUri = Uri.parse(str);
        localObject = localUri;
      }
      if (localObject == null) {
        return paramUri;
      }
    }
    catch (JSONException localJSONException)
    {
      localObject = paramUri;
    }
    return localObject;
  }
  
  private static boolean tryGetBooleanFromJson(JSONObject paramJSONObject, String paramString, boolean paramBoolean)
  {
    try
    {
      boolean bool = paramJSONObject.getBoolean(paramString);
      return bool;
    }
    catch (JSONException localJSONException) {}
    return paramBoolean;
  }
  
  private static String tryGetStringFromJson(JSONObject paramJSONObject, String paramString1, String paramString2)
  {
    try
    {
      String str = paramJSONObject.getString(paramString1);
      return str;
    }
    catch (JSONException localJSONException) {}
    return paramString2;
  }
  
  public Task<AppLink> getAppLinkFromUrlInBackground(final Uri paramUri)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(paramUri);
    getAppLinkFromUrlsInBackground(localArrayList).onSuccess(new Continuation()
    {
      public AppLink then(Task<Map<Uri, AppLink>> paramAnonymousTask)
        throws Exception
      {
        return (AppLink)((Map)paramAnonymousTask.getResult()).get(paramUri);
      }
    });
  }
  
  public Task<Map<Uri, AppLink>> getAppLinkFromUrlsInBackground(List<Uri> paramList)
  {
    final HashMap localHashMap1 = new HashMap();
    final HashSet localHashSet = new HashSet();
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      Uri localUri = (Uri)localIterator.next();
      synchronized (this.cachedAppLinks)
      {
        AppLink localAppLink = (AppLink)this.cachedAppLinks.get(localUri);
        if (localAppLink != null) {
          localHashMap1.put(localUri, localAppLink);
        }
      }
      if (!localHashSet.isEmpty()) {
        localStringBuilder.append(',');
      }
      localStringBuilder.append(localUri.toString());
      localHashSet.add(localUri);
    }
    if (localHashSet.isEmpty()) {
      return Task.forResult(localHashMap1);
    }
    final Task.TaskCompletionSource localTaskCompletionSource = Task.create();
    Bundle localBundle = new Bundle();
    localBundle.putString("ids", localStringBuilder.toString());
    localBundle.putString("fields", String.format("%s.fields(%s,%s)", new Object[] { "app_links", "android", "web" }));
    new Request(null, "", localBundle, null, new Request.Callback()
    {
      public void onCompleted(Response paramAnonymousResponse)
      {
        FacebookRequestError localFacebookRequestError = paramAnonymousResponse.getError();
        if (localFacebookRequestError != null)
        {
          localTaskCompletionSource.setError(localFacebookRequestError.getException());
          return;
        }
        GraphObject localGraphObject = paramAnonymousResponse.getGraphObject();
        if (localGraphObject != null) {}
        for (JSONObject localJSONObject1 = localGraphObject.getInnerJSONObject(); localJSONObject1 == null; localJSONObject1 = null)
        {
          localTaskCompletionSource.setResult(localHashMap1);
          return;
        }
        Iterator localIterator = localHashSet.iterator();
        Uri localUri;
        do
        {
          if (!localIterator.hasNext()) {
            break;
          }
          localUri = (Uri)localIterator.next();
        } while (!localJSONObject1.has(localUri.toString()));
        for (;;)
        {
          int j;
          try
          {
            for (;;)
            {
              JSONObject localJSONObject2 = localJSONObject1.getJSONObject(localUri.toString()).getJSONObject("app_links");
              JSONArray localJSONArray = localJSONObject2.getJSONArray("android");
              int i = localJSONArray.length();
              ArrayList localArrayList = new ArrayList(i);
              j = 0;
              if (j < i)
              {
                AppLink.Target localTarget = FacebookAppLinkResolver.getAndroidTargetFromJson(localJSONArray.getJSONObject(j));
                if (localTarget == null) {
                  break;
                }
                localArrayList.add(localTarget);
                break;
              }
              AppLink localAppLink = new AppLink(localUri, localArrayList, FacebookAppLinkResolver.getWebFallbackUriFromJson(localUri, localJSONObject2));
              localHashMap1.put(localUri, localAppLink);
              synchronized (FacebookAppLinkResolver.this.cachedAppLinks)
              {
                FacebookAppLinkResolver.this.cachedAppLinks.put(localUri, localAppLink);
              }
            }
          }
          catch (JSONException localJSONException) {}
          localTaskCompletionSource.setResult(localHashMap1);
          return;
          j++;
        }
      }
    }).executeAsync();
    return localTaskCompletionSource.getTask();
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.facebook.FacebookAppLinkResolver
 * JD-Core Version:    0.7.0.1
 */