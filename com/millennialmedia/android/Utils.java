package com.millennialmedia.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

class Utils
{
  private static final String TAG = "Utils";
  
  static JSONObject getViewDimensions(View paramView)
  {
    JSONObject localJSONObject = new JSONObject();
    if (paramView == null) {
      MMLog.e("Utils", "Unable to calculate view dimensions for null view");
    }
    DisplayMetrics localDisplayMetrics;
    do
    {
      return localJSONObject;
      localDisplayMetrics = paramView.getContext().getResources().getDisplayMetrics();
    } while (localDisplayMetrics == null);
    int[] arrayOfInt = new int[2];
    paramView.getLocationInWindow(arrayOfInt);
    try
    {
      localJSONObject.put("x", (int)(arrayOfInt[0] / localDisplayMetrics.density));
      localJSONObject.put("y", (int)(arrayOfInt[1] / localDisplayMetrics.density));
      localJSONObject.put("width", (int)(paramView.getWidth() / localDisplayMetrics.density));
      localJSONObject.put("height", (int)(paramView.getHeight() / localDisplayMetrics.density));
      return localJSONObject;
    }
    catch (JSONException localJSONException)
    {
      MMLog.e("Utils", "Unable to build view dimensions", localJSONException);
    }
    return localJSONObject;
  }
  
  static class HttpUtils
  {
    static void executeUrl(String paramString)
    {
      Utils.ThreadUtils.execute(new Runnable()
      {
        public void run()
        {
          try
          {
            new DefaultHttpClient().execute(new HttpGet(this.val$url));
            MMLog.d("Utils", "Executed Url :\"" + this.val$url + "\"");
            return;
          }
          catch (IOException localIOException)
          {
            MMLog.e("Utils", "Exception with HttpUtils: ", localIOException);
          }
        }
      });
    }
  }
  
  static class IntentUtils
  {
    private static void fixDataAndTypeForVideo(Context paramContext, Intent paramIntent)
    {
      Uri localUri = paramIntent.getData();
      if (localUri != null)
      {
        String str = localUri.getLastPathSegment();
        if ((TextUtils.isEmpty(paramIntent.getStringExtra("class"))) && (!TextUtils.isEmpty(str)) && ((str.endsWith(".mp4")) || (str.endsWith(".3gp")) || (str.endsWith(".mkv")) || (str.endsWith("content.once")))) {
          paramIntent.setDataAndType(paramIntent.getData(), "video/*");
        }
      }
    }
    
    static Intent getIntentForUri(HttpRedirection.RedirectionListenerImpl paramRedirectionListenerImpl)
    {
      if (paramRedirectionListenerImpl == null) {}
      Uri localUri;
      Context localContext;
      do
      {
        return null;
        localUri = paramRedirectionListenerImpl.destinationUri;
        localContext = (Context)paramRedirectionListenerImpl.weakContext.get();
      } while (localContext == null);
      Intent localIntent1 = null;
      String str1;
      if (localUri != null)
      {
        str1 = localUri.getScheme();
        localIntent1 = null;
        if (str1 != null)
        {
          if (!str1.equalsIgnoreCase("market")) {
            break label111;
          }
          MMLog.v("Utils", "Creating Android Market intent.");
          localIntent1 = new Intent("android.intent.action.VIEW", localUri);
          MMSDK.Event.intentStarted(localContext, "market", paramRedirectionListenerImpl.creatorAdImplInternalId);
        }
      }
      while (localIntent1 != null)
      {
        MMLog.v("Utils", String.format("%s resolved to Intent: %s", new Object[] { localUri, localIntent1 }));
        return localIntent1;
        label111:
        if (str1.equalsIgnoreCase("rtsp"))
        {
          MMLog.v("Utils", "Creating streaming video player intent.");
          localIntent1 = new Intent(localContext, MMActivity.class);
          localIntent1.setData(localUri);
          localIntent1.putExtra("class", "com.millennialmedia.android.VideoPlayerActivity");
        }
        else if (str1.equalsIgnoreCase("tel"))
        {
          MMLog.v("Utils", "Creating telephone intent.");
          localIntent1 = new Intent("android.intent.action.DIAL", localUri);
          MMSDK.Event.intentStarted(localContext, "tel", paramRedirectionListenerImpl.creatorAdImplInternalId);
        }
        else if (str1.equalsIgnoreCase("sms"))
        {
          MMLog.v("Utils", "Creating txt message intent.");
          String str3 = localUri.getSchemeSpecificPart();
          String str4 = "";
          int i = str3.indexOf("?body=");
          if ((i != -1) && (str3.length() > i)) {
            str4 = str3.substring(0, i).replace(',', ';');
          }
          localIntent1 = new Intent("android.intent.action.VIEW", Uri.parse("sms:" + str4));
          if (i == -1) {
            localIntent1.putExtra("sms_body", str3.substring(i + 6));
          }
          MMSDK.Event.intentStarted(localContext, "sms", paramRedirectionListenerImpl.creatorAdImplInternalId);
        }
        else if (str1.equalsIgnoreCase("mailto"))
        {
          localIntent1 = new Intent("android.intent.action.VIEW", localUri);
          MMSDK.Event.intentStarted(localContext, "email", paramRedirectionListenerImpl.creatorAdImplInternalId);
        }
        else if (str1.equalsIgnoreCase("geo"))
        {
          MMLog.v("Utils", "Creating Google Maps intent.");
          localIntent1 = new Intent("android.intent.action.VIEW", localUri);
          MMSDK.Event.intentStarted(localContext, "geo", paramRedirectionListenerImpl.creatorAdImplInternalId);
        }
        else if (str1.equalsIgnoreCase("https"))
        {
          MMLog.v("Utils", "Creating launch browser intent.");
          localIntent1 = new Intent("android.intent.action.VIEW", localUri);
          MMSDK.Event.intentStarted(localContext, "browser", paramRedirectionListenerImpl.creatorAdImplInternalId);
        }
        else if (str1.equalsIgnoreCase("mmbrowser"))
        {
          String str2 = localUri.toString().substring(12);
          if ((str2 != null) && (!str2.contains("://"))) {
            str2 = str2.replaceFirst("//", "://");
          }
          MMLog.v("Utils", "MMBrowser - Creating launch browser intent.");
          localIntent1 = new Intent("android.intent.action.VIEW", Uri.parse(str2));
          MMSDK.Event.intentStarted(localContext, "browser", paramRedirectionListenerImpl.creatorAdImplInternalId);
        }
        else if (str1.equalsIgnoreCase("http"))
        {
          if ((localUri.getLastPathSegment() != null) && ((localUri.getLastPathSegment().endsWith(".mp4")) || (localUri.getLastPathSegment().endsWith(".3gp"))))
          {
            MMLog.v("Utils", "Creating video player intent.");
            localIntent1 = new Intent(localContext, MMActivity.class);
            localIntent1.setData(localUri);
            localIntent1.putExtra("class", "com.millennialmedia.android.VideoPlayerActivity");
            MMSDK.Event.intentStarted(localContext, "streamingVideo", paramRedirectionListenerImpl.creatorAdImplInternalId);
          }
          else
          {
            if (paramRedirectionListenerImpl.canOpenOverlay())
            {
              MMLog.v("Utils", "Creating launch overlay intent.");
              Intent localIntent2 = new Intent(localContext, MMActivity.class);
              localIntent2.putExtra("class", AdViewOverlayActivity.class.getCanonicalName());
              localIntent2.setData(localUri);
              return localIntent2;
            }
            MMLog.v("Utils", "Creating launch browser intent.");
            MMSDK.Event.intentStarted(localContext, "browser", paramRedirectionListenerImpl.creatorAdImplInternalId);
            localIntent1 = new Intent("android.intent.action.VIEW", localUri);
          }
        }
        else
        {
          MMLog.v("Utils", String.format("Creating intent for unrecognized URI. %s", new Object[] { localUri }));
          localIntent1 = new Intent("android.intent.action.VIEW", localUri);
        }
      }
      MMLog.v("Utils", String.format("%s", new Object[] { localUri }));
      return localIntent1;
    }
    
    static void startActionView(Context paramContext, String paramString)
    {
      startActivity(paramContext, new Intent("android.intent.action.VIEW", Uri.parse(paramString)));
    }
    
    static void startActivity(Context paramContext, Intent paramIntent)
    {
      if (!(paramContext instanceof Activity)) {
        paramIntent.addFlags(268435456);
      }
      fixDataAndTypeForVideo(paramContext, paramIntent);
      paramContext.startActivity(paramIntent);
    }
    
    static void startAdViewOverlayActivity(Context paramContext)
    {
      Intent localIntent = new Intent(paramContext, MMActivity.class);
      localIntent.putExtra("class", "com.millennialmedia.android.AdViewOverlayActivity");
      startActivity(paramContext, localIntent);
    }
    
    static void startAdViewOverlayActivity(Context paramContext, Intent paramIntent)
    {
      paramIntent.setClass(paramContext, MMActivity.class);
      paramIntent.putExtra("class", "com.millennialmedia.android.AdViewOverlayActivity");
      startActivity(paramContext, paramIntent);
    }
    
    static void startAdViewOverlayActivityWithData(Context paramContext, String paramString)
    {
      Intent localIntent = new Intent(paramContext, MMActivity.class);
      localIntent.putExtra("class", "com.millennialmedia.android.AdViewOverlayActivity");
      localIntent.setData(Uri.parse(paramString));
      startActivity(paramContext, localIntent);
    }
    
    static void startCachedVideoPlayerActivity(Context paramContext, Intent paramIntent)
    {
      paramIntent.setClass(paramContext, MMActivity.class);
      paramIntent.putExtra("class", "com.millennialmedia.android.CachedVideoPlayerActivity");
      startActivity(paramContext, paramIntent);
    }
    
    static void startPickerActivity(Context paramContext, File paramFile, String paramString)
    {
      Intent localIntent = new Intent(paramContext, MMActivity.class);
      localIntent.setData(Uri.fromFile(paramFile));
      localIntent.putExtra("type", paramString);
      localIntent.putExtra("class", "com.millennialmedia.android.BridgeMMMedia$PickerActivity");
      startActivity(paramContext, localIntent);
    }
    
    static void startVideoPlayerActivityWithData(Context paramContext, Uri paramUri)
    {
      Intent localIntent = new Intent(paramContext, MMActivity.class);
      localIntent.setData(paramUri);
      localIntent.putExtra("class", "com.millennialmedia.android.VideoPlayerActivity");
      startActivity(paramContext, localIntent);
    }
    
    static void startVideoPlayerActivityWithData(Context paramContext, File paramFile)
    {
      startVideoPlayerActivityWithData(paramContext, Uri.fromFile(paramFile));
    }
    
    static void startVideoPlayerActivityWithData(Context paramContext, String paramString)
    {
      startVideoPlayerActivityWithData(paramContext, Uri.parse(paramString));
    }
  }
  
  static class ThreadUtils
  {
    private static final ExecutorService cachedThreadExecutor = ;
    
    static void execute(Runnable paramRunnable)
    {
      cachedThreadExecutor.execute(paramRunnable);
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.Utils
 * JD-Core Version:    0.7.0.1
 */