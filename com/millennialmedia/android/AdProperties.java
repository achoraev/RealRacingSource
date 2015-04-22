package com.millennialmedia.android;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View;
import java.lang.ref.WeakReference;
import org.json.JSONException;
import org.json.JSONObject;

class AdProperties
{
  private static final String TAG = AdProperties.class.getName();
  WeakReference<Context> contextRef;
  
  AdProperties(Context paramContext)
  {
    this.contextRef = new WeakReference(paramContext);
  }
  
  private JSONObject getPermissions()
    throws JSONException
  {
    JSONObject localJSONObject = new JSONObject();
    if (getContext().checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0) {}
    for (boolean bool = true;; bool = false)
    {
      localJSONObject.put("android.permission.ACCESS_FINE_LOCATION", bool);
      return localJSONObject;
    }
  }
  
  private JSONObject getScreen()
    throws JSONException
  {
    JSONObject localJSONObject = new JSONObject();
    localJSONObject.put("height", getScreenDpiIndependentHeight());
    localJSONObject.put("width", getScreenDpiIndependentWidth());
    return localJSONObject;
  }
  
  private JSONObject getSupports()
    throws JSONException
  {
    JSONObject localJSONObject = new JSONObject();
    Context localContext = getContext();
    localJSONObject.put("sms", Boolean.parseBoolean(MMSDK.getSupportsSms(localContext)));
    localJSONObject.put("tel", Boolean.parseBoolean(MMSDK.getSupportsTel(localContext)));
    localJSONObject.put("calendar", MMSDK.getSupportsCalendar());
    localJSONObject.put("storePicture", false);
    localJSONObject.put("inlineVideo", true);
    return localJSONObject;
  }
  
  String getAdDpiIndependentHeight()
  {
    return getScreenDpiIndependentHeight();
  }
  
  String getAdDpiIndependentWidth()
  {
    return getScreenDpiIndependentWidth();
  }
  
  public JSONObject getAdProperties(View paramView)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("screen", getScreen());
      localJSONObject.put("ad", Utils.getViewDimensions(paramView));
      localJSONObject.put("do", MMSDK.getOrientation(getContext()));
      localJSONObject.put("supports", getSupports());
      localJSONObject.put("device", BridgeMMDevice.getDeviceInfo(getContext()));
      localJSONObject.put("permissions", getPermissions());
      localJSONObject.put("maxSize", getScreen());
      return localJSONObject;
    }
    catch (JSONException localJSONException)
    {
      MMLog.e(TAG, "Error when building ad properties", localJSONException);
    }
    return localJSONObject;
  }
  
  Context getContext()
  {
    return (Context)this.contextRef.get();
  }
  
  String getScreenDpiIndependentHeight()
  {
    DisplayMetrics localDisplayMetrics = getContext().getResources().getDisplayMetrics();
    return String.valueOf((int)(localDisplayMetrics.heightPixels / localDisplayMetrics.density));
  }
  
  String getScreenDpiIndependentWidth()
  {
    DisplayMetrics localDisplayMetrics = getContext().getResources().getDisplayMetrics();
    return String.valueOf((int)(localDisplayMetrics.widthPixels / localDisplayMetrics.density));
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.AdProperties
 * JD-Core Version:    0.7.0.1
 */