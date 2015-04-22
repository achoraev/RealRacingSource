package com.millennialmedia.android;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import java.lang.ref.WeakReference;
import java.util.Map;

class BridgeMMInterstitial
  extends MMJSObject
{
  private static final String CLOSE = "close";
  private static final String EXPAND_TO_EXTERNAL_BROWSER = "expandToExternalBrowser";
  private static final String EXPAND_WITH_PROPERTIES = "expandWithProperties";
  private static final String OPEN = "open";
  private static final String SET_ORIENTATION = "setOrientation";
  private static final String TAG = BridgeMMInterstitial.class.getName();
  private static final String USE_CUSTOM_CLOSE = "useCustomClose";
  
  private Intent getExpandExtrasIntent(String paramString, OverlaySettings paramOverlaySettings)
  {
    Intent localIntent = new Intent();
    if (paramString != null) {
      localIntent.setData(Uri.parse(paramString));
    }
    localIntent.putExtra("settings", paramOverlaySettings);
    localIntent.putExtra("internalId", paramOverlaySettings.creatorAdImplId);
    return localIntent;
  }
  
  private boolean isForcingOrientation(MMJSResponse paramMMJSResponse)
  {
    int i = paramMMJSResponse.result;
    boolean bool1 = false;
    if (i == 1)
    {
      boolean bool2 = paramMMJSResponse.response instanceof String;
      bool1 = false;
      if (bool2)
      {
        String str = (String)paramMMJSResponse.response;
        if (!str.contains("portrait"))
        {
          boolean bool3 = str.contains("landscape");
          bool1 = false;
          if (!bool3) {}
        }
        else
        {
          bool1 = true;
        }
      }
    }
    return bool1;
  }
  
  private MMJSResponse setAllowOrientationChange(Map<String, String> paramMap)
  {
    String str = (String)paramMap.get("allowOrientationChange");
    if (str != null)
    {
      AdViewOverlayActivity localAdViewOverlayActivity = getBaseActivity();
      if (localAdViewOverlayActivity != null)
      {
        localAdViewOverlayActivity.setAllowOrientationChange(Boolean.parseBoolean(str));
        return MMJSResponse.responseWithSuccess();
      }
    }
    return null;
  }
  
  private MMJSResponse setForceOrientation(Map<String, String> paramMap)
  {
    String str = (String)paramMap.get("forceOrientation");
    AdViewOverlayActivity localAdViewOverlayActivity = getBaseActivity();
    if (localAdViewOverlayActivity != null) {
      if (!"none".equals(str))
      {
        if ("portrait".equals(str))
        {
          localAdViewOverlayActivity.setRequestedOrientationPortrait();
          return MMJSResponse.responseWithSuccess("portrait");
        }
        if ("landscape".equals(str))
        {
          localAdViewOverlayActivity.setRequestedOrientationLandscape();
          return MMJSResponse.responseWithSuccess("landscape");
        }
      }
      else if ("none".equals(str))
      {
        localAdViewOverlayActivity.setAllowOrientationChange(true);
        return MMJSResponse.responseWithSuccess("none");
      }
    }
    return null;
  }
  
  public MMJSResponse close(Map<String, String> paramMap)
  {
    MMWebView localMMWebView = (MMWebView)this.mmWebViewRef.get();
    if (localMMWebView != null)
    {
      localMMWebView.getMMLayout().closeAreaTouched();
      return MMJSResponse.responseWithSuccess();
    }
    return null;
  }
  
  MMJSResponse executeCommand(String paramString, Map<String, String> paramMap)
  {
    MMJSResponse localMMJSResponse;
    if ("close".equals(paramString)) {
      localMMJSResponse = close(paramMap);
    }
    boolean bool;
    do
    {
      return localMMJSResponse;
      if ("expandToExternalBrowser".equals(paramString)) {
        return expandToExternalBrowser(paramMap);
      }
      if ("expandWithProperties".equals(paramString)) {
        return expandWithProperties(paramMap);
      }
      if ("open".equals(paramString)) {
        return open(paramMap);
      }
      if ("setOrientation".equals(paramString)) {
        return setOrientation(paramMap);
      }
      bool = "useCustomClose".equals(paramString);
      localMMJSResponse = null;
    } while (!bool);
    return useCustomClose(paramMap);
  }
  
  public MMJSResponse expandToExternalBrowser(Map<String, String> paramMap)
  {
    return open(paramMap);
  }
  
  public MMJSResponse expandWithProperties(Map<String, String> paramMap)
  {
    String str1 = (String)paramMap.get("PROPERTY_BANNER_TYPE");
    if ((str1 != null) && (!Boolean.parseBoolean(str1))) {
      return MMJSResponse.responseWithError("Cannot expand a non banner ad");
    }
    String str2 = (String)paramMap.get("url");
    String str3 = (String)paramMap.get("transparent");
    String str4 = (String)paramMap.get("useCustomClose");
    String str5 = (String)paramMap.get("transition");
    String str6 = (String)paramMap.get("orientation");
    String str7 = (String)paramMap.get("transitionDuration");
    String str8 = (String)paramMap.get("height");
    String str9 = (String)paramMap.get("width");
    String str10 = (String)paramMap.get("modal");
    String str11 = (String)paramMap.get("PROPERTY_EXPANDING");
    String str12 = (String)paramMap.get("allowOrientationChange");
    Context localContext = (Context)this.contextRef.get();
    if (localContext != null)
    {
      OverlaySettings localOverlaySettings = new OverlaySettings();
      if (str2 != null) {
        localOverlaySettings.urlToLoad = str2;
      }
      if (str11 != null) {
        localOverlaySettings.creatorAdImplId = ((int)Float.parseFloat(str11));
      }
      if (str3 != null) {
        localOverlaySettings.setIsTransparent(Boolean.parseBoolean(str3));
      }
      if (str4 != null) {
        localOverlaySettings.setUseCustomClose(Boolean.parseBoolean(str4));
      }
      if (str5 != null) {
        localOverlaySettings.setTransition(str5);
      }
      if (str12 != null) {
        localOverlaySettings.allowOrientationChange = Boolean.parseBoolean(str12);
      }
      if (str6 == null) {
        str6 = (String)paramMap.get("forceOrientation");
      }
      if (str6 != null) {
        localOverlaySettings.orientation = str6;
      }
      if (str8 != null) {
        localOverlaySettings.height = ((int)Float.parseFloat(str8));
      }
      if (str9 != null) {
        localOverlaySettings.width = ((int)Float.parseFloat(str9));
      }
      if (str10 != null) {
        localOverlaySettings.modal = Boolean.parseBoolean(str10);
      }
      if (str7 != null) {}
      try
      {
        localOverlaySettings.setTransitionDurationInMillis(1000L * Long.parseLong(str7));
        Utils.IntentUtils.startAdViewOverlayActivity(localContext, getExpandExtrasIntent(str2, localOverlaySettings));
        MMSDK.Event.overlayOpenedBroadCast(localContext, getAdImplId((String)paramMap.get("PROPERTY_EXPANDING")));
        return MMJSResponse.responseWithSuccess();
      }
      catch (Exception localException)
      {
        for (;;)
        {
          MMLog.e(TAG, "Problem converting transitionDuration", localException);
        }
      }
    }
    return null;
  }
  
  public MMJSResponse open(Map<String, String> paramMap)
  {
    String str = (String)paramMap.get("url");
    Context localContext = (Context)this.contextRef.get();
    if ((str != null) && (localContext != null))
    {
      Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse(str));
      MMSDK.Event.intentStarted(localContext, "browser", getAdImplId((String)paramMap.get("PROPERTY_EXPANDING")));
      Utils.IntentUtils.startActivity(localContext, localIntent);
      return MMJSResponse.responseWithSuccess();
    }
    return null;
  }
  
  public MMJSResponse setOrientation(Map<String, String> paramMap)
  {
    MMJSResponse localMMJSResponse = setForceOrientation(paramMap);
    if ((localMMJSResponse == null) || (!isForcingOrientation(localMMJSResponse))) {
      localMMJSResponse = setAllowOrientationChange(paramMap);
    }
    return localMMJSResponse;
  }
  
  public MMJSResponse useCustomClose(Map<String, String> paramMap)
  {
    MMWebView localMMWebView = (MMWebView)this.mmWebViewRef.get();
    String str = (String)paramMap.get("useCustomClose");
    if ((str != null) && (localMMWebView != null))
    {
      AdViewOverlayView localAdViewOverlayView = localMMWebView.getAdViewOverlayView();
      if (localAdViewOverlayView != null)
      {
        localAdViewOverlayView.setUseCustomClose(Boolean.parseBoolean(str));
        return MMJSResponse.responseWithSuccess();
      }
    }
    return null;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.BridgeMMInterstitial
 * JD-Core Version:    0.7.0.1
 */