package com.ea.nimble.tracking;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import com.ea.nimble.ApplicationEnvironment;
import com.ea.nimble.Base;
import com.ea.nimble.IApplicationEnvironment;
import com.ea.nimble.IHttpRequest.Method;
import com.ea.nimble.ISynergyEnvironment;
import com.ea.nimble.ISynergyIdManager;
import com.ea.nimble.ISynergyResponse;
import com.ea.nimble.Log.Helper;
import com.ea.nimble.LogSource;
import com.ea.nimble.SynergyEnvironment;
import com.ea.nimble.SynergyIdManager;
import com.ea.nimble.SynergyNetworkConnectionHandle;
import com.ea.nimble.SynergyRequest;
import com.ea.nimble.Utility;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

public class TrackingS2S
  extends TrackingBase
  implements LogSource
{
  static final String COMPONENT_ID = "com.ea.nimble.trackingimpl.s2s";
  public static final int EVENT_APPRESUMED = 103;
  public static final int EVENT_APPSTARTED = 102;
  public static final int EVENT_APPSTARTED_AFTERINSTALL = 101;
  public static final String EVENT_CUSTOM = "SYNERGYS2S::CUSTOM";
  public static final int EVENT_MTXVIEW_ITEM_PURCHASED = 105;
  private static final String EVENT_PREFIX = "SYNERGYS2S::";
  public static final int EVENT_USER_REGISTERED = 104;
  private static final double MARS_DEFAULT_POST_INTERVAL = 60.0D;
  private static final double MARS_MAX_POST_RETRY_DELAY = 86400.0D;
  private static final String SYNERGY_API_POST_EVENTS = "/s2s/api/core/postEvents";
  
  private Map<String, Object> createEventRequestPostMap()
  {
    IApplicationEnvironment localIApplicationEnvironment = ApplicationEnvironment.getComponent();
    ISynergyEnvironment localISynergyEnvironment = SynergyEnvironment.getComponent();
    ISynergyIdManager localISynergyIdManager = SynergyIdManager.getComponent();
    Date localDate = new Date();
    localHashMap = new HashMap();
    localHashMap.put("bundleId", Utility.safeString(localIApplicationEnvironment.getApplicationBundleId()));
    localHashMap.put("sellId", Utility.safeString(localISynergyEnvironment.getSellId()));
    localHashMap.put("appName", Utility.safeString(localIApplicationEnvironment.getApplicationName()));
    localHashMap.put("appVersion", Utility.safeString(localIApplicationEnvironment.getApplicationVersion()));
    localHashMap.put("deviceId", Utility.safeString(localISynergyEnvironment.getEADeviceId()));
    if (localISynergyEnvironment.getSynergyServerFlags("sendUDID")) {
      localHashMap.put("deviceNativeId", Utility.safeString(Settings.Secure.getString(localIApplicationEnvironment.getApplicationContext().getContentResolver(), "android_id")));
    }
    localHashMap.put("systemName", "Android");
    localHashMap.put("systemVersion", Build.VERSION.RELEASE);
    localHashMap.put("deviceType", Build.MODEL);
    localHashMap.put("deviceBrand", Build.BRAND);
    PackageManager localPackageManager = localIApplicationEnvironment.getApplicationContext().getPackageManager();
    TelephonyManager localTelephonyManager = (TelephonyManager)localIApplicationEnvironment.getApplicationContext().getSystemService("phone");
    localHashMap.put("carrierName", Utility.safeString(localTelephonyManager.getNetworkOperatorName()));
    if (localPackageManager.checkPermission("android.permission.READ_PHONE_STATE", localIApplicationEnvironment.getApplicationContext().getPackageName()) == 0) {
      localHashMap.put("imei", Utility.safeString(localTelephonyManager.getDeviceId()));
    }
    localHashMap.put("androidId", Utility.safeString(Settings.Secure.getString(localIApplicationEnvironment.getApplicationContext().getContentResolver(), "android_id")));
    localHashMap.put("countryCode", Utility.safeString(Locale.getDefault().getCountry()));
    localHashMap.put("appLanguage", Utility.safeString(localIApplicationEnvironment.getApplicationLanguageCode()));
    Locale localLocale = Locale.US;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Calendar.getInstance();
    localHashMap.put("timezone", String.format(localLocale, "%tZ", arrayOfObject));
    localHashMap.put("gmtOffset", String.valueOf(TimeZone.getDefault().getOffset(localDate.getTime()) / 1000));
    localHashMap.put("synergyId", Utility.safeString(localISynergyIdManager.getSynergyId()));
    if (localIApplicationEnvironment.isDeviceRooted()) {}
    for (String str1 = "1";; str1 = "0")
    {
      localHashMap.put("jflag", str1);
      String str2 = "";
      boolean bool1 = true;
      try
      {
        str2 = ApplicationEnvironment.getComponent().getGoogleAdvertisingId();
        boolean bool2 = ApplicationEnvironment.getComponent().isLimitAdTrackingEnabled();
        bool1 = bool2;
      }
      catch (Exception localException1)
      {
        for (;;)
        {
          String str6;
          Log.Helper.LOGW(this, "Exception when getting advertising ID for Android", new Object[0]);
        }
      }
      localHashMap.put("advertiserID", str2);
      localHashMap.put("limitAdTracking", Boolean.valueOf(bool1));
      if (localISynergyEnvironment.getSynergyServerFlags("sendMacAddress")) {
        localHashMap.put("macAddress", Utility.safeString(localIApplicationEnvironment.getMACAddress()));
      }
      if (localIApplicationEnvironment != null)
      {
        str6 = localIApplicationEnvironment.getGameSpecifiedPlayerId();
        if ((str6 != null) && (str6.length() > 0)) {
          localHashMap.put("gamePlayerId", str6);
        }
      }
      try
      {
        Context localContext = ApplicationEnvironment.getComponent().getApplicationContext();
        ApplicationInfo localApplicationInfo = localContext.getPackageManager().getApplicationInfo(localContext.getPackageName(), 128);
        Bundle localBundle = localApplicationInfo.metaData;
        localObject = null;
        if (localBundle != null)
        {
          String str5 = localApplicationInfo.metaData.getString("com.facebook.sdk.ApplicationId");
          localObject = str5;
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        for (;;)
        {
          Object localObject = null;
        }
      }
      if (Utility.validString(localObject)) {
        localHashMap.put("fbAppId", localObject);
      }
      try
      {
        Cursor localCursor = ApplicationEnvironment.getComponent().getApplicationContext().getContentResolver().query(Uri.parse("content://com.facebook.katana.provider.AttributionIdProvider"), null, null, null, null);
        if (localCursor != null)
        {
          localCursor.moveToFirst();
          localHashMap.put("fbAttrId", localCursor.getString(0));
        }
      }
      catch (IllegalStateException localIllegalStateException)
      {
        for (;;)
        {
          int i;
          int j;
          localIllegalStateException.printStackTrace();
        }
      }
      catch (Exception localException2)
      {
        for (;;)
        {
          localException2.printStackTrace();
          continue;
          String str3 = "N";
        }
        if ((this.m_currentSessionObject == null) || (this.m_currentSessionObject.events == null)) {
          break label1019;
        }
        ArrayList localArrayList = new ArrayList(this.m_currentSessionObject.events);
        Iterator localIterator = localArrayList.iterator();
        while (localIterator.hasNext())
        {
          Map localMap = (Map)localIterator.next();
          if (localMap.containsKey("referrer") == true)
          {
            String str4 = (String)localMap.get("referrer");
            localMap.remove("referrer");
            localHashMap.put("referrer", str4);
          }
        }
        localHashMap.put("adEvents", localArrayList);
        return localHashMap;
      }
      if (!this.m_loggedInToOrigin) {
        break label880;
      }
      str3 = "Y";
      localHashMap.put("originUser", str3);
      i = this.m_customSessionData.size();
      if (i <= 0) {
        break label888;
      }
      for (j = 0; j < i; j++) {
        localHashMap.put(((TrackingBase.SessionData)this.m_customSessionData.get(j)).key, ((TrackingBase.SessionData)this.m_customSessionData.get(j)).value);
      }
    }
  }
  
  private static void initialize()
  {
    Base.registerComponent(new TrackingS2S(), "com.ea.nimble.trackingimpl.s2s");
  }
  
  private static boolean isS2SEvent(String paramString)
  {
    if (paramString == null) {
      return false;
    }
    return paramString.startsWith("SYNERGYS2S::");
  }
  
  List<Map<String, String>> convertEvent(Tracking.Event paramEvent)
  {
    if ((!Tracking.isNimbleStandardEvent(paramEvent.type)) && (!isS2SEvent(paramEvent.type))) {
      return null;
    }
    HashMap localHashMap = new HashMap(7);
    int i;
    String str1;
    String str2;
    String str3;
    String str4;
    String str5;
    label140:
    String str6;
    label193:
    String str7;
    label215:
    String str8;
    if (paramEvent.type.equals("NIMBLESTANDARD::APPSTART_AFTERINSTALL"))
    {
      i = 101;
      str1 = "Launch";
      String str10 = ReferrerReceiver.getReferrer(ApplicationEnvironment.getComponent().getApplicationContext());
      str2 = null;
      str3 = null;
      str4 = null;
      str5 = null;
      if (str10 != null)
      {
        int j = str10.length();
        str2 = null;
        str3 = null;
        str4 = null;
        str5 = null;
        if (j > 0)
        {
          localHashMap = new HashMap(8);
          localHashMap.put("referrer", str10);
          ReferrerReceiver.clearReferrer(ApplicationEnvironment.getComponent().getApplicationContext());
        }
      }
      localHashMap.put("eventType", String.valueOf(i));
      localHashMap.put("eventName", str1);
      localHashMap.put("timestamp", Utility.getUTCDateStringFormat(paramEvent.timestamp));
      if (str2 != null) {
        break label836;
      }
      str6 = "0";
      localHashMap.put("eventKeyType01", str6);
      if (str4 != null) {
        break label843;
      }
      str7 = "";
      localHashMap.put("eventValue01", str7);
      if (str3 != null) {
        break label850;
      }
      str8 = "0";
      label237:
      localHashMap.put("eventKeyType02", str8);
      if (str5 != null) {
        break label857;
      }
    }
    label836:
    label843:
    label850:
    label857:
    for (String str9 = "";; str9 = str5)
    {
      localHashMap.put("eventValue02", str9);
      localHashMap.put("transactionId", UUID.randomUUID().toString());
      if ((i == 101) || (i == 102) || (i == 103))
      {
        Log.Helper.LOGD(this, "Logging session start event. Posting event queue now.", new Object[0]);
        resetPostTimer(0.0D);
      }
      ArrayList localArrayList = new ArrayList();
      localArrayList.add(localHashMap);
      return localArrayList;
      if ((paramEvent.type.equals("NIMBLESTANDARD::APPSTART_NORMAL")) || (paramEvent.type.equals("NIMBLESTANDARD::APPSTART_AFTERUPGRADE")) || (paramEvent.type.equals("NIMBLESTANDARD::APPSTART_FROMURL")))
      {
        i = 102;
        str1 = "Launch";
        str2 = null;
        str3 = null;
        str4 = null;
        str5 = null;
        break label140;
      }
      if (paramEvent.type.equals("NIMBLESTANDARD::APPSTART_FROMPUSH"))
      {
        i = 102;
        str1 = "NotificationLaunch";
        str2 = null;
        str3 = null;
        str4 = null;
        str5 = null;
        break label140;
      }
      if ((paramEvent.type.equals("NIMBLESTANDARD::APPRESUME_NORMAL")) || (paramEvent.type.equals("NIMBLESTANDARD::SESSION_START")) || (paramEvent.type.equals("NIMBLESTANDARD::APPRESUME_FROMURL")) || (paramEvent.type.equals("NIMBLESTANDARD::APPRESUME_FROMEBISU")))
      {
        i = 103;
        str1 = "Resume";
        str2 = null;
        str3 = null;
        str4 = null;
        str5 = null;
        break label140;
      }
      if (paramEvent.type.equals("NIMBLESTANDARD::APPRESUME_FROMPUSH"))
      {
        i = 103;
        str1 = "NotificationResume";
        str2 = null;
        str3 = null;
        str4 = null;
        str5 = null;
        break label140;
      }
      if (paramEvent.type.equals("NIMBLESTANDARD::USER_REGISTERED"))
      {
        i = 104;
        str1 = "Registration";
        str2 = "username";
        str4 = (String)paramEvent.parameters.get("NIMBLESTANDARD::KEY_USERNAME");
        str3 = null;
        str5 = null;
        if (str4 != null) {
          break label140;
        }
        Log.Helper.LOGE("TRACK_S2S: Error: missing event parameter \"%s\"", "NIMBLESTANDARD::KEY_USERNAME", new Object[0]);
        str3 = null;
        str5 = null;
        break label140;
      }
      if (paramEvent.type.equals("NIMBLESTANDARD::MTX_ITEM_PURCHASED"))
      {
        i = 105;
        str1 = "Purchase";
        str2 = "tvalue";
        str4 = (String)paramEvent.parameters.get("NIMBLESTANDARD::KEY_MTX_CURRENCY");
        str3 = "fvalue";
        str5 = (String)paramEvent.parameters.get("NIMBLESTANDARD::KEY_MTX_PRICE");
        if (str4 == null) {
          Log.Helper.LOGE("TRACK_S2S: Error: missing event parameter \"%s\"", "NIMBLESTANDARD::KEY_MTX_CURRENCY", new Object[0]);
        }
        if (str5 != null) {
          break label140;
        }
        Log.Helper.LOGE("TRACK_S2S: Error: missing event parameter \"%s\"", "NIMBLESTANDARD::KEY_MTX_PRICE", new Object[0]);
        break label140;
      }
      if (!paramEvent.type.equals("SYNERGYS2S::CUSTOM")) {
        break;
      }
      i = Integer.parseInt((String)paramEvent.parameters.get("eventType"));
      str2 = (String)paramEvent.parameters.get("keyType01");
      str4 = (String)paramEvent.parameters.get("keyValue01");
      str3 = (String)paramEvent.parameters.get("keyType02");
      str5 = (String)paramEvent.parameters.get("keyValue02");
      str1 = null;
      break label140;
      str6 = str2;
      break label193;
      str7 = str4;
      break label215;
      str8 = str3;
      break label237;
    }
  }
  
  SynergyRequest createPostRequest(TrackingBaseSessionObject paramTrackingBaseSessionObject)
  {
    HashMap localHashMap1 = new HashMap();
    localHashMap1.put("apiVer", "1.0.0");
    String str = SynergyEnvironment.getComponent().getServerUrlWithKey("synergy.s2s");
    if (str == null)
    {
      Log.Helper.LOGI(this, "Tracking server URL from NimbleEnvironment is nil. Adding observer for environment update finish.", new Object[0]);
      super.addObserverForSynergyEnvironmentUpdateFinished();
      return null;
    }
    HashMap localHashMap2 = new HashMap(paramTrackingBaseSessionObject.sessionData);
    localHashMap2.put("now_timestamp", Utility.getUTCDateStringFormat(new Date()));
    SynergyRequest localSynergyRequest = new SynergyRequest("/s2s/api/core/postEvents", IHttpRequest.Method.POST, null);
    localSynergyRequest.baseUrl = str;
    localSynergyRequest.urlParameters = localHashMap1;
    localSynergyRequest.jsonData = localHashMap2;
    return localSynergyRequest;
  }
  
  public String getComponentId()
  {
    return "com.ea.nimble.trackingimpl.s2s";
  }
  
  public String getLogSourceTitle()
  {
    return "TrackingS2S";
  }
  
  String getPersistenceIdentifier()
  {
    return "S2S";
  }
  
  public double getRetryTime(SynergyNetworkConnectionHandle paramSynergyNetworkConnectionHandle)
  {
    d = 1.0D;
    int i = -1;
    if (paramSynergyNetworkConnectionHandle != null) {}
    try
    {
      if (paramSynergyNetworkConnectionHandle.getResponse() != null)
      {
        Map localMap = paramSynergyNetworkConnectionHandle.getResponse().getJsonData();
        if (localMap != null)
        {
          String str = new JSONObject(localMap).getString("resultCode");
          Log.Helper.LOGD(this, "getMessage result code " + str + "~", new Object[0]);
          i = Integer.parseInt(str);
        }
      }
      if ((i <= -21000) && (i >= -22000))
      {
        if (this.m_postRetryDelay < 60.0D) {
          this.m_postRetryDelay = 60.0D;
        }
        d = this.m_postRetryDelay;
        this.m_postRetryDelay = (10.0D * this.m_postRetryDelay);
        if (this.m_postRetryDelay > 86400.0D) {
          this.m_postRetryDelay = 86400.0D;
        }
      }
      for (;;)
      {
        Log.Helper.LOGD(this, "S2S retry delay result code is:" + i + ". Delay will be:" + d, new Object[0]);
        return d;
        d = this.m_postRetryDelay;
        this.m_postRetryDelay = (2.0D * this.m_postRetryDelay);
        if (this.m_postRetryDelay > 300.0D) {
          this.m_postRetryDelay = 300.0D;
        }
      }
      return d;
    }
    catch (JSONException localJSONException)
    {
      Log.Helper.LOGD(this, "Failed to parse result code in TrackingS2S retransmission check. Defaulting to retryTime of:" + d, new Object[0]);
    }
  }
  
  void packageCurrentSession()
  {
    if (this.m_currentSessionObject.countOfEvents() > 0)
    {
      this.m_currentSessionObject.sessionData = new HashMap(createEventRequestPostMap());
      if (!((ArrayList)this.m_currentSessionObject.sessionData.get("adEvents")).isEmpty()) {
        queueCurrentEventsForPost();
      }
    }
  }
  
  public void restore()
  {
    super.restore();
  }
  
  public boolean shouldAttemptReTrans(SynergyNetworkConnectionHandle paramSynergyNetworkConnectionHandle)
  {
    bool = true;
    if ((paramSynergyNetworkConnectionHandle == null) || (paramSynergyNetworkConnectionHandle.getResponse() == null))
    {
      Log.Helper.LOGF(this, "S2S retrans had no network handle response. Network probably failed to connect. \nWe should attempt retrans.", new Object[0]);
      return true;
    }
    int i = -1;
    try
    {
      Map localMap = paramSynergyNetworkConnectionHandle.getResponse().getJsonData();
      if (localMap != null)
      {
        String str = new JSONObject(localMap).getString("resultCode");
        Log.Helper.LOGV(this, "getMessage result code " + str + "~", new Object[0]);
        int j = Integer.parseInt(str);
        i = j;
      }
      if ((i > -20000) || ((i < -21000) && (i > -22000))) {
        bool = false;
      }
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        Log.Helper.LOGD(this, "Failed to parse result code in TrackingS2S retransmission check. Defaulting to NO.", new Object[0]);
        bool = false;
      }
    }
    Log.Helper.LOGV(this, "S2S retransmission is: " + bool, new Object[0]);
    return bool;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ea.nimble.tracking.TrackingS2S
 * JD-Core Version:    0.7.0.1
 */