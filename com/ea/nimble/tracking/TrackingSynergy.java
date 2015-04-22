package com.ea.nimble.tracking;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import com.ea.nimble.ApplicationEnvironment;
import com.ea.nimble.Base;
import com.ea.nimble.IApplicationEnvironment;
import com.ea.nimble.IHttpRequest.Method;
import com.ea.nimble.INetwork;
import com.ea.nimble.ISynergyEnvironment;
import com.ea.nimble.ISynergyIdManager;
import com.ea.nimble.Log.Helper;
import com.ea.nimble.LogSource;
import com.ea.nimble.Network;
import com.ea.nimble.Network.Status;
import com.ea.nimble.SynergyEnvironment;
import com.ea.nimble.SynergyIdManager;
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
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public class TrackingSynergy
  extends TrackingBase
  implements LogSource
{
  static final String COMPONENT_ID = "com.ea.nimble.trackingimpl.synergy";
  public static final String EVENT_CUSTOM = "SYNERGYTRACKING::CUSTOM";
  private static final String EVENT_PREFIX = "SYNERGYTRACKING::";
  private static final int MAX_CUSTOM_EVENT_PARAMETERS = 20;
  private int m_eventNumber;
  private Map<String, String> m_mainAuthenticator;
  private final BroadcastReceiver m_mainAuthenticatorUpdateReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      HashMap localHashMap = (HashMap)paramAnonymousIntent.getSerializableExtra("NIMBLESTANDARD::KEY_IDENTITY_SOURCE");
      if ((localHashMap != null) && (localHashMap.size() > 0)) {
        TrackingSynergy.access$202(TrackingSynergy.this, localHashMap);
      }
    }
  };
  private List<Map<String, String>> m_pendingEvents = new ArrayList();
  private final BroadcastReceiver m_pidInfoUpdateReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      HashMap localHashMap = (HashMap)paramAnonymousIntent.getSerializableExtra("pidMapId");
      if ((localHashMap != null) && (localHashMap.size() > 0)) {
        TrackingSynergy.access$102(TrackingSynergy.this, localHashMap);
      }
    }
  };
  private Map<String, String> m_pidMap;
  private String m_sessionId;
  private SynergyIdChangedReceiver m_synergyIdChangedReceiver = new SynergyIdChangedReceiver(null);
  
  private Map<String, Object> generateSessionInfoDictionary(String paramString)
  {
    ISynergyEnvironment localISynergyEnvironment = SynergyEnvironment.getComponent();
    ISynergyIdManager localISynergyIdManager = SynergyIdManager.getComponent();
    IApplicationEnvironment localIApplicationEnvironment = ApplicationEnvironment.getComponent();
    HashMap localHashMap = new HashMap();
    String str1 = localISynergyEnvironment.getSellId();
    String str2 = localISynergyEnvironment.getEAHardwareId();
    String str3 = localISynergyEnvironment.getEADeviceId();
    String str4 = Build.VERSION.RELEASE;
    String str5 = ApplicationEnvironment.getComponent().getCarrier();
    String str6 = ApplicationEnvironment.getComponent().getApplicationVersion();
    Locale localLocale = Locale.US;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Calendar.getInstance();
    String str7 = String.format(localLocale, "%tZ", arrayOfObject);
    localHashMap.put("carrier", str5);
    localHashMap.put("timezone", str7);
    String str8;
    String str9;
    label164:
    String str10;
    label332:
    String str11;
    if (localIApplicationEnvironment.isAppCracked())
    {
      str8 = "1";
      localHashMap.put("pflag", str8);
      if (!localIApplicationEnvironment.isDeviceRooted()) {
        break label677;
      }
      str9 = "1";
      localHashMap.put("jflag", str9);
      localHashMap.put("firmwareVer", str4);
      localHashMap.put("sellId", Utility.safeString(str1));
      localHashMap.put("buildId", Utility.safeString(str6));
      localHashMap.put("sdkVer", "1.13.1.1009");
      localHashMap.put("sdkCfg", "DL");
      localHashMap.put("deviceId", Utility.safeString(str3));
      localHashMap.put("hwId", Utility.safeString(str2));
      localHashMap.put("schemaVer", "2");
      localHashMap.put("platform", "android");
      str10 = "N";
      INetwork localINetwork = Network.getComponent();
      if (localINetwork.getStatus() == Network.Status.OK)
      {
        if (!localINetwork.isNetworkWifi()) {
          break label685;
        }
        str10 = "W";
      }
      localHashMap.put("networkAccess", str10);
      if (!this.m_loggedInToOrigin) {
        break label693;
      }
      str11 = "Y";
      label355:
      localHashMap.put("originUser", str11);
      if (!Utility.validString(paramString)) {
        break label700;
      }
    }
    label677:
    label685:
    label693:
    label700:
    for (String str12 = paramString;; str12 = localISynergyIdManager.getSynergyId())
    {
      if (str12 != null) {
        localHashMap.put("uid", Utility.safeString(str12));
      }
      localHashMap.put("androidId", Utility.safeString(Settings.Secure.getString(localIApplicationEnvironment.getApplicationContext().getContentResolver(), "android_id")));
      String str13 = "";
      boolean bool1 = true;
      try
      {
        str13 = ApplicationEnvironment.getComponent().getGoogleAdvertisingId();
        boolean bool2 = ApplicationEnvironment.getComponent().isLimitAdTrackingEnabled();
        bool1 = bool2;
      }
      catch (Exception localException)
      {
        for (;;)
        {
          String str14;
          int i;
          int j;
          Log.Helper.LOGW(this, "Exception when getting advertising ID for Android", new Object[0]);
        }
      }
      localHashMap.put("advertiserID", str13);
      localHashMap.put("limitAdTracking", Boolean.valueOf(bool1));
      localHashMap.put("macHash", Utility.safeString(Utility.SHA256HashString(localIApplicationEnvironment.getMACAddress())));
      localHashMap.put("aut", Utility.safeString(""));
      if ((this.m_pidMap != null) && (this.m_pidMap.size() > 0)) {
        localHashMap.put("pidMap", this.m_pidMap);
      }
      if (localIApplicationEnvironment != null)
      {
        str14 = localIApplicationEnvironment.getGameSpecifiedPlayerId();
        if ((str14 != null) && (str14.length() > 0)) {
          localHashMap.put("gamePlayerId", str14);
        }
      }
      i = this.m_customSessionData.size();
      if (i <= 0) {
        break label727;
      }
      for (j = 0; j < i; j++) {
        localHashMap.put(((TrackingBase.SessionData)this.m_customSessionData.get(j)).key, ((TrackingBase.SessionData)this.m_customSessionData.get(j)).value);
      }
      str8 = "0";
      break;
      str9 = "0";
      break label164;
      str10 = "G";
      break label332;
      str11 = "N";
      break label355;
    }
    label727:
    return localHashMap;
  }
  
  private String generateSynergySessionId()
  {
    String str = Utility.getUTCDateStringFormat(new Date()).replace("_", "");
    StringBuilder localStringBuilder = new StringBuilder(24);
    localStringBuilder.append(str);
    int i = 24 - localStringBuilder.length();
    Random localRandom = new Random();
    for (int j = 0; j < i; j++) {
      localStringBuilder.append(localRandom.nextInt(10));
    }
    return localStringBuilder.toString();
  }
  
  private static void initialize()
  {
    Base.registerComponent(new TrackingSynergy(), "com.ea.nimble.trackingimpl.synergy");
  }
  
  private static boolean isSynergyEvent(String paramString)
  {
    if (paramString == null) {
      return false;
    }
    return paramString.startsWith("SYNERGYTRACKING::");
  }
  
  private void onSynergyIdChanged(Intent paramIntent)
  {
    String str1 = paramIntent.getStringExtra("previousSynergyId");
    String str2 = paramIntent.getStringExtra("currentSynergyId");
    HashMap localHashMap = new HashMap();
    localHashMap.put("eventType", String.valueOf(SynergyConstants.EVT_SESSION_END_SYNERGYID_CHANGE.value));
    localHashMap.put("keyType01", String.valueOf(SynergyConstants.EVT_KEYTYPE_SYNERGYID.value));
    localHashMap.put("keyValue01", Utility.safeString(str1));
    localHashMap.put("keyType02", String.valueOf(SynergyConstants.EVT_KEYTYPE_SYNERGYID.value));
    localHashMap.put("keyValue02", Utility.safeString(str2));
    logEvent("SYNERGYTRACKING::CUSTOM", localHashMap);
    this.m_currentSessionObject.sessionData = new HashMap(generateSessionInfoDictionary(str1));
    queueCurrentEventsForPost();
    localHashMap.put("eventType", String.valueOf(SynergyConstants.EVT_NEW_SESSION_START_SYNERGYID_CHANGE.value));
    logEvent("SYNERGYTRACKING::CUSTOM", localHashMap);
  }
  
  private void parseCustomParameters(Map<String, String> paramMap1, Map<String, String> paramMap2)
  {
    ArrayList localArrayList1 = new ArrayList(20);
    ArrayList localArrayList2 = new ArrayList(20);
    Iterator localIterator = paramMap1.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      String str3 = (String)localEntry.getKey();
      if (str3.startsWith("keyType"))
      {
        int n;
        try
        {
          n = Integer.parseInt(str3.substring("keyType".length()));
          if ((n <= 0) || (n > 20)) {
            break label213;
          }
          if (n - 1 >= localArrayList1.size()) {
            break label171;
          }
          localArrayList1.set(n - 1, localEntry.getValue());
        }
        catch (NumberFormatException localNumberFormatException2)
        {
          Log.Helper.LOGE(this, "Error: Invalid format for keyType parameter. Expected keyType##, got " + str3, new Object[0]);
        }
        continue;
        label171:
        for (int i1 = localArrayList1.size(); i1 < n - 1; i1++) {
          localArrayList1.add(null);
        }
        localArrayList1.add(localEntry.getValue());
        continue;
        label213:
        Object[] arrayOfObject6 = new Object[2];
        arrayOfObject6[0] = Integer.valueOf(n);
        arrayOfObject6[1] = Integer.valueOf(20);
        Log.Helper.LOGE(this, "Error: Custom parameter number %d is out of range of 1-%d", arrayOfObject6);
      }
      else if (str3.startsWith("keyValue"))
      {
        int k;
        try
        {
          k = Integer.parseInt(str3.substring("keyValue".length()));
          if ((k <= 0) || (k > 20)) {
            break label399;
          }
          if (k - 1 >= localArrayList2.size()) {
            break label354;
          }
          localArrayList2.set(k - 1, localEntry.getValue());
        }
        catch (NumberFormatException localNumberFormatException1)
        {
          Log.Helper.LOGE(this, "Error: Invalid format for keyValue parameter. Expected keyValue##, got " + str3, new Object[0]);
        }
        continue;
        label354:
        for (int m = localArrayList2.size(); m < k - 1; m++) {
          localArrayList2.add(null);
        }
        localArrayList2.add(localEntry.getValue());
        continue;
        label399:
        Object[] arrayOfObject5 = new Object[2];
        arrayOfObject5[0] = Integer.valueOf(k);
        arrayOfObject5[1] = Integer.valueOf(20);
        Log.Helper.LOGE(this, "Error: Custom parameter number %d is out of range of 1-%d", arrayOfObject5);
      }
    }
    int i = Math.max(localArrayList1.size(), localArrayList2.size());
    int j = 0;
    if (j < i)
    {
      String str1;
      if (j < localArrayList1.size())
      {
        str1 = (String)localArrayList1.get(j);
        label479:
        if (str1 == null)
        {
          str1 = "0";
          Object[] arrayOfObject4 = new Object[1];
          arrayOfObject4[0] = Integer.valueOf(j + 1);
          Log.Helper.LOGE(this, "Error: No corresponding keyType entry for parameter number %d", arrayOfObject4);
        }
        if (j >= localArrayList2.size()) {
          break label667;
        }
      }
      label667:
      for (String str2 = (String)localArrayList2.get(j);; str2 = null)
      {
        if (str2 == null)
        {
          str2 = "";
          Object[] arrayOfObject3 = new Object[1];
          arrayOfObject3[0] = Integer.valueOf(j + 1);
          Log.Helper.LOGE(this, "Error: No corresponding keyValue entry for parameter number %d", arrayOfObject3);
        }
        Locale localLocale1 = Locale.US;
        Object[] arrayOfObject1 = new Object[1];
        arrayOfObject1[0] = Integer.valueOf(j + 1);
        paramMap2.put(String.format(localLocale1, "eventKeyType%02d", arrayOfObject1), str1);
        Locale localLocale2 = Locale.US;
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = Integer.valueOf(j + 1);
        paramMap2.put(String.format(localLocale2, "eventValue%02d", arrayOfObject2), str2);
        j++;
        break;
        str1 = null;
        break label479;
      }
    }
  }
  
  private void resetSession()
  {
    this.m_sessionId = generateSynergySessionId();
    this.m_eventNumber = 1;
  }
  
  private void sleep()
  {
    Utility.unregisterReceiver(this.m_synergyIdChangedReceiver);
  }
  
  private void wakeup()
  {
    Utility.registerReceiver("nimble.synergyidmanager.notification.synergy_id_changed", this.m_synergyIdChangedReceiver);
  }
  
  public void cleanup()
  {
    sleep();
    super.cleanup();
  }
  
  List<Map<String, String>> convertEvent(Tracking.Event paramEvent)
  {
    Integer localInteger = Integer.valueOf(-1);
    HashMap localHashMap = new HashMap();
    if ((!Tracking.isNimbleStandardEvent(paramEvent.type)) && (!isSynergyEvent(paramEvent.type))) {
      return null;
    }
    SynergyConstants localSynergyConstants;
    String str1;
    ArrayList localArrayList;
    if (paramEvent.type.equals("NIMBLESTANDARD::APPSTART_NORMAL"))
    {
      localSynergyConstants = SynergyConstants.EVT_APPSTART_NORMALLY;
      str1 = Utility.getUTCDateStringFormat(paramEvent.timestamp);
      localArrayList = new ArrayList();
      if (localInteger.intValue() == -1) {
        break label1844;
      }
      localHashMap.put("eventType", String.valueOf(localInteger));
    }
    for (;;)
    {
      for (;;)
      {
        for (;;)
        {
          for (;;)
          {
            localHashMap.put("timestamp", str1);
            if (!localSynergyConstants.isSessionStartEventType()) {
              break label1973;
            }
            resetSession();
            localIterator = this.m_pendingEvents.iterator();
            while (localIterator.hasNext())
            {
              localMap = (Map)localIterator.next();
              localMap.put("session", this.m_sessionId);
              localMap.put("step", String.valueOf(this.m_eventNumber));
              this.m_eventNumber = (1 + this.m_eventNumber);
              localArrayList.add(localMap);
            }
            if (paramEvent.type.equals("NIMBLESTANDARD::APPSTART_AFTERINSTALL"))
            {
              localSynergyConstants = SynergyConstants.EVT_APPSTART_AFTERINSTALL;
              break;
            }
            if (paramEvent.type.equals("NIMBLESTANDARD::APPSTART_AFTERUPGRADE"))
            {
              localSynergyConstants = SynergyConstants.EVT_APPSTART_AFTERUPGRADE;
              break;
            }
            if (paramEvent.type.equals("NIMBLESTANDARD::APPSTART_FROMURL"))
            {
              localSynergyConstants = SynergyConstants.EVT_APPSTART_FROM_URL;
              break;
            }
            if (!paramEvent.type.equals("NIMBLESTANDARD::APPSTART_FROMPUSH")) {
              break label434;
            }
            localSynergyConstants = SynergyConstants.EVT_APPSTART_FROMPUSH;
            try
            {
              str3 = ApplicationEnvironment.getComponent().getApplicationContext().getSharedPreferences("PushNotification", 0).getString("messageId", null);
              localHashMap.put("eventKeyType01", String.valueOf(SynergyConstants.EVT_KEYTYPE_MESSAGEID.value));
              localHashMap.put("eventValue01", str3);
              ApplicationEnvironment.getComponent().getApplicationContext().getSharedPreferences("PushNotification", 0).edit().remove("messageId").commit();
              ApplicationEnvironment.getComponent().getApplicationContext().getSharedPreferences("PushNotification", 0).edit().remove("PushNotification").commit();
            }
            catch (Exception localException2)
            {
              localException2.printStackTrace();
            }
          }
          break;
          label434:
          if (paramEvent.type.equals("NIMBLESTANDARD::APPRESUME_FROMURL"))
          {
            localSynergyConstants = SynergyConstants.EVT_APP_ENTER_FOREGROUND_FROM_URL;
            break;
          }
          if (paramEvent.type.equals("NIMBLESTANDARD::APPRESUME_FROMEBISU"))
          {
            localSynergyConstants = SynergyConstants.EVT_APP_ENTER_FOREGROUND_FROM_EBISU;
            break;
          }
          if (!paramEvent.type.equals("NIMBLESTANDARD::APPRESUME_FROMPUSH")) {
            break label634;
          }
          localSynergyConstants = SynergyConstants.EVT_APP_RESUME_FROM_PUSH;
          try
          {
            str2 = ApplicationEnvironment.getComponent().getApplicationContext().getSharedPreferences("PushNotification", 0).getString("messageId", null);
            localHashMap.put("eventKeyType01", String.valueOf(SynergyConstants.EVT_KEYTYPE_MESSAGEID.value));
            localHashMap.put("eventValue01", str2);
            ApplicationEnvironment.getComponent().getApplicationContext().getSharedPreferences("PushNotification", 0).edit().remove("messageId").commit();
            ApplicationEnvironment.getComponent().getApplicationContext().getSharedPreferences("PushNotification", 0).edit().remove("PushNotification").commit();
          }
          catch (Exception localException1)
          {
            localException1.printStackTrace();
          }
        }
        break;
        label634:
        if (paramEvent.type.equals("NIMBLESTANDARD::SESSION_START"))
        {
          localSynergyConstants = SynergyConstants.EVT_APP_SESSION_START;
          break;
        }
        if (paramEvent.type.equals("NIMBLESTANDARD::SESSION_END"))
        {
          localSynergyConstants = SynergyConstants.EVT_APP_SESSION_END;
          break;
        }
        if (paramEvent.type.equals("NIMBLESTANDARD::SESSION_TIME"))
        {
          localSynergyConstants = SynergyConstants.EVT_APP_SESSION_TIME;
          localHashMap.put("eventKeyType01", String.valueOf(SynergyConstants.EVT_KEYTYPE_DURATION.value));
          localHashMap.put("eventValue01", paramEvent.parameters.get("NIMBLESTANDARD::KEY_DURATION"));
          break;
        }
        if (paramEvent.type.equals("NIMBLESTANDARD::MTX_ITEM_BEGIN_PURCHASE"))
        {
          localSynergyConstants = SynergyConstants.EVT_MTXVIEW_ITEMPURCHASE;
          localHashMap.put("eventKeyType01", String.valueOf(SynergyConstants.EVT_KEYTYPE_MTX_SELLID.value));
          localHashMap.put("eventValue01", paramEvent.parameters.get("NIMBLESTANDARD::KEY_MTX_SELLID"));
          break;
        }
        if (paramEvent.type.equals("NIMBLESTANDARD::MTX_ITEM_PURCHASED"))
        {
          localSynergyConstants = SynergyConstants.EVT_MTXVIEW_ITEM_PURCHASED;
          localHashMap.put("eventKeyType01", String.valueOf(SynergyConstants.EVT_KEYTYPE_MTX_SELLID.value));
          localHashMap.put("eventValue01", paramEvent.parameters.get("NIMBLESTANDARD::KEY_MTX_SELLID"));
          break;
        }
        if (paramEvent.type.equals("NIMBLESTANDARD::MTX_FREEITEM_DOWNLOADED"))
        {
          localSynergyConstants = SynergyConstants.EVT_MTXVIEW_FREEITEM_DOWNLOADED;
          localHashMap.put("eventKeyType01", String.valueOf(SynergyConstants.EVT_KEYTYPE_MTX_SELLID.value));
          localHashMap.put("eventValue01", paramEvent.parameters.get("NIMBLESTANDARD::KEY_MTX_SELLID"));
          break;
        }
        if (paramEvent.type.equals("NIMBLESTANDARD::USER_TRACKING_OPTOUT"))
        {
          localSynergyConstants = SynergyConstants.EVT_USER_TRACKING_OPTOUT;
          break;
        }
        if (paramEvent.type.equals("NIMBLESTANDARD::PN_DISPLAY_OPT_IN"))
        {
          localSynergyConstants = SynergyConstants.EVT_USER_SHOWN_PN_OPTIN_PROMPT;
          break;
        }
        if (paramEvent.type.equals("NIMBLESTANDARD::PN_USER_OPT_IN"))
        {
          localSynergyConstants = SynergyConstants.EVT_USER_SHOWN_PN_OPTIN_PROMPT;
          localHashMap.put("eventKeyType02", paramEvent.parameters.get(Integer.valueOf(SynergyConstants.EVT_KEYTYPE_ENUMERATION.value)));
          localHashMap.put("eventValue02", "Yes");
          break;
        }
        if (paramEvent.type.equals("NIMBLESTANDARD::PN_SHOWN_TO_USER"))
        {
          localSynergyConstants = SynergyConstants.EVT_PN_SHOWN_TO_USER;
          localHashMap.put("eventKeyType01", String.valueOf(SynergyConstants.EVT_KEYTYPE_MESSAGEID.value));
          localHashMap.put("eventValue01", paramEvent.parameters.get("NIMBLESTANDARD::KEY_PN_MESSAGE_ID"));
          break;
        }
        if (paramEvent.type.equals("NIMBLESTANDARD::PN_USER_CLICKED_OK"))
        {
          localSynergyConstants = SynergyConstants.EVT_PN_SHOWN_TO_USER;
          localHashMap.put("eventKeyType01", String.valueOf(SynergyConstants.EVT_KEYTYPE_MESSAGEID.value));
          localHashMap.put("eventValue01", paramEvent.parameters.get("NIMBLESTANDARD::KEY_PN_MESSAGE_ID"));
          localHashMap.put("eventKeyType02", paramEvent.parameters.get(Integer.valueOf(SynergyConstants.EVT_KEYTYPE_ENUMERATION.value)));
          localHashMap.put("eventValue02", "Ok");
          break;
        }
        if (paramEvent.type.equals("NIMBLESTANDARD::IDENTITY_MIGRATION"))
        {
          localSynergyConstants = SynergyConstants.EVT_IDENTITY_MIGRATION;
          localHashMap.put("eventKeyType01", String.valueOf(SynergyConstants.EVT_KEYTYPE_ENUMERATION.value));
          localHashMap.put("eventValue01", paramEvent.parameters.get("NIMBLESTANDARD::KEY_MIGRATION_GAME_TRIGGERED"));
          localHashMap.put("eventKeyType02", String.valueOf(SynergyConstants.EVT_KEYTYPE_JSON_MAP.value));
          localHashMap.put("eventValue02", paramEvent.parameters.get("NIMBLESTANDARD::KEY_IDENTITY_SOURCE"));
          localHashMap.put("eventKeyType02", String.valueOf(SynergyConstants.EVT_KEYTYPE_JSON_MAP.value));
          localHashMap.put("eventValue02", paramEvent.parameters.get("NIMBLESTANDARD::KEY_IDENTITY_TARGET"));
          break;
        }
        if (!paramEvent.type.equals("NIMBLESTANDARD::IDENTITY_LOGIN")) {
          break label1507;
        }
        localSynergyConstants = SynergyConstants.EVT_IDENTITY_LOGIN;
        localHashMap.put("eventKeyType01", String.valueOf(SynergyConstants.EVT_KEYTYPE_JSON_MAP.value));
        localHashMap.put("eventValue01", paramEvent.parameters.get("NIMBLESTANDARD::KEY_IDENTITY_PIDMAP_LOGIN"));
        localHashMap.put("eventKeyType02", String.valueOf(SynergyConstants.EVT_KEYTYPE_JSON_MAP.value));
        localHashMap.put("eventValue02", paramEvent.parameters.get("NIMBLESTANDARD::KEY_IDENTITY_TARGET"));
        new HashMap();
        try
        {
          new JSONObject((String)paramEvent.parameters.get("NIMBLESTANDARD::KEY_IDENTITY_PIDMAP_LOGIN"));
        }
        catch (JSONException localJSONException)
        {
          localJSONException.printStackTrace();
        }
      }
      break;
      label1507:
      if (paramEvent.type.equals("NIMBLESTANDARD::IDENTITY_LOGOUT"))
      {
        localSynergyConstants = SynergyConstants.EVT_IDENTITY_LOGOUT;
        localHashMap.put("eventKeyType01", String.valueOf(SynergyConstants.EVT_KEYTYPE_JSON_MAP.value));
        localHashMap.put("eventValue01", paramEvent.parameters.get("NIMBLESTANDARD::KEY_IDENTITY_SOURCE"));
        localHashMap.put("eventKeyType02", String.valueOf(SynergyConstants.EVT_KEYTYPE_JSON_MAP.value));
        localHashMap.put("eventValue02", paramEvent.parameters.get("NIMBLESTANDARD::KEY_IDENTITY_PIDMAP_LOGOUT"));
        break;
      }
      if (paramEvent.type.equals("NIMBLESTANDARD::IDENTITY_MIGRATION_STARTED"))
      {
        localSynergyConstants = SynergyConstants.EVT_IDENTITY_MIGRATION_STARTED;
        localHashMap.put("eventKeyType01", String.valueOf(SynergyConstants.EVT_KEYTYPE_ENUMERATION.value));
        localHashMap.put("eventValue01", paramEvent.parameters.get("NIMBLESTANDARD::KEY_MIGRATION_GAME_TRIGGERED"));
        localHashMap.put("eventKeyType02", String.valueOf(SynergyConstants.EVT_KEYTYPE_JSON_MAP.value));
        localHashMap.put("eventValue02", paramEvent.parameters.get("NIMBLESTANDARD::KEY_IDENTITY_SOURCE"));
        localHashMap.put("eventKeyType02", String.valueOf(SynergyConstants.EVT_KEYTYPE_JSON_MAP.value));
        localHashMap.put("eventValue02", paramEvent.parameters.get("NIMBLESTANDARD::KEY_IDENTITY_TARGET"));
        break;
      }
      if (paramEvent.type.equals("SYNERGYTRACKING::CUSTOM"))
      {
        localSynergyConstants = SynergyConstants.fromInt(Integer.parseInt((String)paramEvent.parameters.get("eventType")));
        if (localSynergyConstants == SynergyConstants.EVT_UNDEFINED) {
          localInteger = Integer.valueOf(Integer.parseInt((String)paramEvent.parameters.get("eventType")));
        }
        parseCustomParameters(paramEvent.parameters, localHashMap);
        break;
      }
      return null;
      label1844:
      localHashMap.put("eventType", String.valueOf(localSynergyConstants.value));
    }
    this.m_pendingEvents.clear();
    label1973:
    while (this.m_sessionId != null)
    {
      Iterator localIterator;
      Map localMap;
      String str3;
      String str2;
      localHashMap.put("session", this.m_sessionId);
      localHashMap.put("step", String.valueOf(this.m_eventNumber));
      this.m_eventNumber = (1 + this.m_eventNumber);
      if (localSynergyConstants.isSessionStartEventType())
      {
        Log.Helper.LOGD(this, "Logging session start event, %s. Posting event queue now.", new Object[] { localSynergyConstants });
        resetPostTimer(0.0D);
      }
      if (localSynergyConstants == SynergyConstants.EVT_APP_SESSION_END) {
        this.m_sessionId = null;
      }
      localArrayList.add(localHashMap);
      return localArrayList;
    }
    this.m_pendingEvents.add(localHashMap);
    return null;
  }
  
  SynergyRequest createPostRequest(TrackingBaseSessionObject paramTrackingBaseSessionObject)
  {
    String str1 = SynergyEnvironment.getComponent().getServerUrlWithKey("synergy.tracking");
    if (str1 == null)
    {
      Log.Helper.LOGI(this, "Tracking server URL from NimbleEnvironment is nil. Adding observer for environment update finish.", new Object[0]);
      addObserverForSynergyEnvironmentUpdateFinished();
      return null;
    }
    Map localMap = paramTrackingBaseSessionObject.sessionData;
    HashMap localHashMap = new HashMap();
    localHashMap.putAll(localMap);
    localHashMap.put("now_timestamp", Utility.getUTCDateStringFormat(new Date()));
    ArrayList localArrayList = new ArrayList(paramTrackingBaseSessionObject.events);
    for (int i = 0; i < localArrayList.size(); i++) {
      ((Map)localArrayList.get(i)).put("repostCount", String.valueOf(paramTrackingBaseSessionObject.repostCount));
    }
    localHashMap.put("events", localArrayList);
    if (localHashMap.get("uid") == null)
    {
      String str2 = SynergyIdManager.getComponent().getSynergyId();
      if (!Utility.validString(str2)) {
        break label241;
      }
      Log.Helper.LOGV(this, "Creating post request. No uid in session info dictionary, inserting uid value %s now.", new Object[] { str2 });
      localHashMap.put("uid", str2);
    }
    for (;;)
    {
      SynergyRequest localSynergyRequest = new SynergyRequest("/tracking/api/core/logEvent", IHttpRequest.Method.POST, null);
      localSynergyRequest.baseUrl = str1;
      localSynergyRequest.jsonData = localHashMap;
      return localSynergyRequest;
      label241:
      Log.Helper.LOGV(this, "Creating post request. No uid in session info dictionary, still no uid available now.", new Object[0]);
    }
  }
  
  public String getComponentId()
  {
    return "com.ea.nimble.trackingimpl.synergy";
  }
  
  public String getLogSourceTitle()
  {
    return "TrackingSynergy";
  }
  
  String getPersistenceIdentifier()
  {
    return "Synergy";
  }
  
  void packageCurrentSession()
  {
    if (this.m_currentSessionObject.countOfEvents() > 0)
    {
      Log.Helper.LOGV(this, "Preparing for post, generating session info dictionary.", new Object[0]);
      this.m_currentSessionObject.sessionData = new HashMap(generateSessionInfoDictionary(null));
      queueCurrentEventsForPost();
    }
  }
  
  public void restore()
  {
    super.restore();
    Utility.registerReceiver("nimble.notification.identity.authenticator.pid.info.update", this.m_pidInfoUpdateReceiver);
    Utility.registerReceiver("nimble.notification.identity.main.authenticator.change", this.m_mainAuthenticatorUpdateReceiver);
    wakeup();
  }
  
  public void resume()
  {
    super.resume();
  }
  
  private class SynergyIdChangedReceiver
    extends BroadcastReceiver
  {
    private SynergyIdChangedReceiver() {}
    
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      TrackingSynergy.this.onSynergyIdChanged(paramIntent);
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ea.nimble.tracking.TrackingSynergy
 * JD-Core Version:    0.7.0.1
 */