package com.millennialmedia.android;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

final class HttpRedirection
{
  private static final String HEADER_LOCATION = "Location";
  private static final String HTTPS = "https";
  private static final String LOG_URL_FORMAT = "Redirecting to: %s";
  private static final String METHOD_GET = "GET";
  private static final String TAG = "HttpRedirection";
  
  static final String navigateRedirects(String paramString)
  {
    if (paramString == null)
    {
      paramString = null;
      return paramString;
    }
    HttpURLConnection.setFollowRedirects(false);
    for (;;)
    {
      try
      {
        if (paramString.startsWith("https")) {
          break;
        }
        URL localURL = new URL(paramString);
        HttpURLConnection localHttpURLConnection = (HttpURLConnection)localURL.openConnection();
        localHttpURLConnection.setConnectTimeout(10000);
        localHttpURLConnection.setRequestMethod("GET");
        localHttpURLConnection.connect();
        int i = localHttpURLConnection.getResponseCode();
        str = localHttpURLConnection.getHeaderField("Location");
        if ((i < 300) || (i >= 400) || (TextUtils.isEmpty(str))) {
          break;
        }
        URI localURI = new URI(str);
        if (localURI.isAbsolute()) {
          continue;
        }
        paramString = localURL.toURI().resolve(localURI).toString();
      }
      catch (MalformedURLException localMalformedURLException)
      {
        String str;
        MMLog.e("HttpRedirection", "Bad url scheme", localMalformedURLException);
        return paramString;
        if (str == null) {
          continue;
        }
        paramString = str;
        continue;
      }
      catch (SocketTimeoutException localSocketTimeoutException)
      {
        MMLog.e("HttpRedirection", "Connection timeout.", localSocketTimeoutException);
        return paramString;
      }
      catch (IOException localIOException)
      {
        MMLog.e("HttpRedirection", "IOException following redirects: ", localIOException);
        return paramString;
      }
      catch (URISyntaxException localURISyntaxException)
      {
        MMLog.e("HttpRedirection", "URI Syntax incorrect.", localURISyntaxException);
      }
      MMLog.v("HttpRedirection", String.format("Redirecting to: %s", new Object[] { paramString }));
    }
    return paramString;
  }
  
  static void startActivityFromUri(RedirectionListenerImpl paramRedirectionListenerImpl)
  {
    if ((paramRedirectionListenerImpl == null) || (paramRedirectionListenerImpl.url == null) || (paramRedirectionListenerImpl.weakContext == null)) {
      return;
    }
    Utils.ThreadUtils.execute(new Runnable()
    {
      private void handleDestinationUri(HttpRedirection.RedirectionListenerImpl paramAnonymousRedirectionListenerImpl)
      {
        Context localContext = (Context)paramAnonymousRedirectionListenerImpl.weakContext.get();
        Intent localIntent;
        if (localContext != null)
        {
          String str1 = paramAnonymousRedirectionListenerImpl.destinationUri.getScheme();
          localIntent = null;
          if (str1 != null)
          {
            if (str1.equalsIgnoreCase("mmvideo")) {
              break label145;
            }
            localIntent = Utils.IntentUtils.getIntentForUri(paramAnonymousRedirectionListenerImpl);
          }
        }
        for (;;)
        {
          if (localIntent != null)
          {
            OverlaySettings localOverlaySettings = paramAnonymousRedirectionListenerImpl.getOverlaySettings();
            if ((localIntent != null) && (localOverlaySettings != null))
            {
              if (paramAnonymousRedirectionListenerImpl.orientation != null) {
                localOverlaySettings.orientation = paramAnonymousRedirectionListenerImpl.orientation;
              }
              localIntent.putExtra("settings", localOverlaySettings);
            }
            String str2 = localIntent.getStringExtra("class");
            if ((str2 != null) && (str2.equals(AdViewOverlayActivity.class.getCanonicalName()))) {}
          }
          try
          {
            if (paramAnonymousRedirectionListenerImpl.isActivityStartable(paramAnonymousRedirectionListenerImpl.destinationUri))
            {
              Utils.IntentUtils.startActivity(localContext, localIntent);
              paramAnonymousRedirectionListenerImpl.startingActivity(paramAnonymousRedirectionListenerImpl.destinationUri);
            }
            return;
          }
          catch (ActivityNotFoundException localActivityNotFoundException)
          {
            label145:
            boolean bool;
            Object[] arrayOfObject = new Object[1];
            arrayOfObject[0] = paramAnonymousRedirectionListenerImpl.destinationUri;
            MMLog.e("HttpRedirection", String.format("No activity found for %s", arrayOfObject), localActivityNotFoundException);
          }
          bool = paramAnonymousRedirectionListenerImpl.isHandlingMMVideo(paramAnonymousRedirectionListenerImpl.destinationUri);
          localIntent = null;
          if (!bool)
          {
            VideoAd.playAd(localContext, paramAnonymousRedirectionListenerImpl.destinationUri.getHost(), paramAnonymousRedirectionListenerImpl);
            localIntent = null;
          }
        }
      }
      
      public void run()
      {
        HttpRedirection.RedirectionListenerImpl localRedirectionListenerImpl = (HttpRedirection.RedirectionListenerImpl)this.val$listenerReference.get();
        String str;
        if (localRedirectionListenerImpl != null)
        {
          str = HttpRedirection.navigateRedirects(localRedirectionListenerImpl.url);
          if (str != null)
          {
            localRedirectionListenerImpl.destinationUri = Uri.parse(str);
            if (localRedirectionListenerImpl.destinationUri == null) {
              break label48;
            }
            handleDestinationUri(localRedirectionListenerImpl);
          }
        }
        return;
        label48:
        MMLog.e("HttpRedirection", String.format("Could not start activity for %s", new Object[] { str }));
      }
    });
  }
  
  private static abstract interface Listener
  {
    public abstract boolean canOpenOverlay();
    
    public abstract OverlaySettings getOverlaySettings();
    
    public abstract boolean isActivityStartable(Uri paramUri);
    
    public abstract boolean isExpandingToUrl();
    
    public abstract boolean isHandlingMMVideo(Uri paramUri);
    
    public abstract void startingActivity(Uri paramUri);
    
    public abstract void startingVideo();
    
    public abstract void updateLastVideoViewedTime();
  }
  
  static class RedirectionListenerImpl
    implements HttpRedirection.Listener
  {
    long creatorAdImplInternalId;
    Uri destinationUri;
    String orientation;
    String url;
    WeakReference<Context> weakContext;
    
    public boolean canOpenOverlay()
    {
      return false;
    }
    
    public OverlaySettings getOverlaySettings()
    {
      return null;
    }
    
    public boolean isActivityStartable(Uri paramUri)
    {
      return true;
    }
    
    public boolean isExpandingToUrl()
    {
      return false;
    }
    
    public boolean isHandlingMMVideo(Uri paramUri)
    {
      return false;
    }
    
    public void startingActivity(Uri paramUri)
    {
      MMLog.d("HttpRedirection", String.format("Starting activity for %s", new Object[] { paramUri }));
    }
    
    public void startingVideo() {}
    
    public void updateLastVideoViewedTime() {}
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.HttpRedirection
 * JD-Core Version:    0.7.0.1
 */