package bolts;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

public class MeasurementEvent
{
  public static final String APP_LINK_NAVIGATE_IN_EVENT_NAME = "al_nav_in";
  public static final String APP_LINK_NAVIGATE_OUT_EVENT_NAME = "al_nav_out";
  public static final String MEASUREMENT_EVENT_ARGS_KEY = "event_args";
  public static final String MEASUREMENT_EVENT_NAME_KEY = "event_name";
  public static final String MEASUREMENT_EVENT_NOTIFICATION_NAME = "com.parse.bolts.measurement_event";
  private Context appContext;
  private Bundle args;
  private String name;
  
  private MeasurementEvent(Context paramContext, String paramString, Bundle paramBundle)
  {
    this.appContext = paramContext.getApplicationContext();
    this.name = paramString;
    this.args = paramBundle;
  }
  
  private static Bundle getApplinkLogData(Context paramContext, String paramString, Bundle paramBundle, Intent paramIntent)
  {
    Bundle localBundle = new Bundle();
    ComponentName localComponentName = paramIntent.resolveActivity(paramContext.getPackageManager());
    if (localComponentName != null) {
      localBundle.putString("class", localComponentName.getShortClassName());
    }
    Iterator localIterator1;
    if ("al_nav_out".equals(paramString))
    {
      if (localComponentName != null) {
        localBundle.putString("package", localComponentName.getPackageName());
      }
      if (paramIntent.getData() != null) {
        localBundle.putString("outputURL", paramIntent.getData().toString());
      }
      if (paramIntent.getScheme() != null) {
        localBundle.putString("outputURLScheme", paramIntent.getScheme());
      }
      localIterator1 = paramBundle.keySet().iterator();
    }
    for (;;)
    {
      if (!localIterator1.hasNext()) {
        break label427;
      }
      String str1 = (String)localIterator1.next();
      Object localObject = paramBundle.get(str1);
      if ((localObject instanceof Bundle))
      {
        Iterator localIterator2 = ((Bundle)localObject).keySet().iterator();
        for (;;)
        {
          if (!localIterator2.hasNext()) {
            break label362;
          }
          String str3 = (String)localIterator2.next();
          String str4 = objectToJSONString(((Bundle)localObject).get(str3));
          if (str1.equals("referer_app_link"))
          {
            if (str3.equalsIgnoreCase("url"))
            {
              localBundle.putString("refererURL", str4);
              continue;
              if (!"al_nav_in".equals(paramString)) {
                break;
              }
              if (paramIntent.getData() != null) {
                localBundle.putString("inputURL", paramIntent.getData().toString());
              }
              if (paramIntent.getScheme() == null) {
                break;
              }
              localBundle.putString("inputURLScheme", paramIntent.getScheme());
              break;
            }
            if (str3.equalsIgnoreCase("app_name"))
            {
              localBundle.putString("refererAppName", str4);
              continue;
            }
            if (str3.equalsIgnoreCase("package"))
            {
              localBundle.putString("sourceApplication", str4);
              continue;
            }
          }
          localBundle.putString(str1 + "/" + str3, str4);
        }
      }
      else
      {
        label362:
        String str2 = objectToJSONString(localObject);
        if (str1.equals("target_url"))
        {
          Uri localUri = Uri.parse(str2);
          localBundle.putString("targetURL", localUri.toString());
          localBundle.putString("targetURLHost", localUri.getHost());
        }
        else
        {
          localBundle.putString(str1, str2);
        }
      }
    }
    label427:
    return localBundle;
  }
  
  private static String objectToJSONString(Object paramObject)
  {
    if (paramObject == null) {
      return null;
    }
    if (((paramObject instanceof JSONArray)) || ((paramObject instanceof JSONObject))) {
      return paramObject.toString();
    }
    try
    {
      if ((paramObject instanceof Collection)) {
        return new JSONArray((Collection)paramObject).toString();
      }
      if ((paramObject instanceof Map)) {
        return new JSONObject((Map)paramObject).toString();
      }
      String str = paramObject.toString();
      return str;
    }
    catch (Exception localException) {}
    return null;
  }
  
  private void sendBroadcast()
  {
    if (this.name == null) {
      Log.d(getClass().getName(), "Event name is required");
    }
    try
    {
      Class localClass = Class.forName("android.support.v4.content.LocalBroadcastManager");
      Method localMethod1 = localClass.getMethod("getInstance", new Class[] { Context.class });
      Method localMethod2 = localClass.getMethod("sendBroadcast", new Class[] { Intent.class });
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = this.appContext;
      Object localObject = localMethod1.invoke(null, arrayOfObject);
      Intent localIntent = new Intent("com.parse.bolts.measurement_event");
      localIntent.putExtra("event_name", this.name);
      localIntent.putExtra("event_args", this.args);
      localMethod2.invoke(localObject, new Object[] { localIntent });
      return;
    }
    catch (Exception localException)
    {
      Log.d(getClass().getName(), "LocalBroadcastManager in android support library is required to raise bolts event.");
    }
  }
  
  static void sendBroadcastEvent(Context paramContext, String paramString, Intent paramIntent, Map<String, String> paramMap)
  {
    Bundle localBundle1 = new Bundle();
    if (paramIntent != null)
    {
      Bundle localBundle2 = AppLinks.getAppLinkData(paramIntent);
      if (localBundle2 == null) {
        break label94;
      }
      localBundle1 = getApplinkLogData(paramContext, paramString, localBundle2, paramIntent);
    }
    while (paramMap != null)
    {
      Iterator localIterator1 = paramMap.keySet().iterator();
      while (localIterator1.hasNext())
      {
        String str1 = (String)localIterator1.next();
        localBundle1.putString(str1, (String)paramMap.get(str1));
      }
      label94:
      Uri localUri = paramIntent.getData();
      if (localUri != null) {
        localBundle1.putString("intentData", localUri.toString());
      }
      Bundle localBundle3 = paramIntent.getExtras();
      if (localBundle3 != null)
      {
        Iterator localIterator2 = localBundle3.keySet().iterator();
        while (localIterator2.hasNext())
        {
          String str2 = (String)localIterator2.next();
          localBundle1.putString(str2, objectToJSONString(localBundle3.get(str2)));
        }
      }
    }
    new MeasurementEvent(paramContext, paramString, localBundle1).sendBroadcast();
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     bolts.MeasurementEvent
 * JD-Core Version:    0.7.0.1
 */