package com.ea.nimble;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.util.Patterns;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApplicationEnvironmentImpl
  extends Component
  implements IApplicationEnvironment, LogSource
{
  private static final int MILLIS_IN_AN_HOUR = 3600000;
  private static final String NIMBLE_APPLICATIONENVIRONMENT_PERSISTENCE_GAME_SPECIFIED_ID = "nimble_applicationenvironment_game_specified_id";
  private static final String PERSISTENCE_AGE_REQUIREMENTS = "ageRequirement";
  private static final String PERSISTENCE_TIME_RETRIEVED = "timeRetrieved";
  private static final String SYNERGY_API_GET_AGE_REQUIREMENTS = "/rest/agerequirements/ip";
  private static boolean isMainApplicationRunning = false;
  private static Activity s_currentActivity = null;
  private Context m_context;
  private BaseCore m_core;
  private String m_gameSpecifiedPlayerId;
  private String m_googleAdvertisingId = "";
  private boolean m_googleLimitAdTrackingEnabled = true;
  private String m_language;
  private String m_packageId;
  
  ApplicationEnvironmentImpl(BaseCore paramBaseCore)
  {
    if (s_currentActivity == null) {
      throw new AssertionError("Cannot create a ApplicationEnvironment without a valid current activity");
    }
    this.m_core = paramBaseCore;
    this.m_language = Locale.getDefault().getLanguage();
    this.m_context = s_currentActivity.getApplicationContext();
    File localFile1 = new File(getDocumentPath());
    File localFile2 = new File(getTempPath());
    if (((!localFile1.exists()) && (!localFile1.mkdirs())) || ((!localFile2.exists()) && (!localFile2.mkdirs()))) {
      throw new AssertionError("APP_ENV: Cannot create necessary folder");
    }
    for (File localFile3 : localFile2.listFiles())
    {
      localFile3.delete();
      Log.d("Nimble", "APP_ENV: Delete temp file " + localFile3.getName());
    }
  }
  
  private static boolean commandExists(String paramString)
  {
    String str = System.getenv("PATH");
    if (str == null) {}
    for (;;)
    {
      return false;
      String[] arrayOfString = str.split(Pattern.quote(File.pathSeparator));
      int i = arrayOfString.length;
      for (int j = 0; j < i; j++) {
        if (new File(arrayOfString[j], paramString).exists()) {
          return true;
        }
      }
    }
  }
  
  public static Activity getCurrentActivity()
  {
    return s_currentActivity;
  }
  
  public static boolean isMainApplicationRunning()
  {
    return isMainApplicationRunning;
  }
  
  /* Error */
  private void retrieveGoogleAdvertiserId()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new 172	java/lang/Thread
    //   5: dup
    //   6: new 174	com/ea/nimble/ApplicationEnvironmentImpl$3
    //   9: dup
    //   10: aload_0
    //   11: invokespecial 177	com/ea/nimble/ApplicationEnvironmentImpl$3:<init>	(Lcom/ea/nimble/ApplicationEnvironmentImpl;)V
    //   14: invokespecial 180	java/lang/Thread:<init>	(Ljava/lang/Runnable;)V
    //   17: astore_1
    //   18: aload_1
    //   19: invokevirtual 183	java/lang/Thread:start	()V
    //   22: aload_0
    //   23: monitorexit
    //   24: return
    //   25: astore 4
    //   27: aload_0
    //   28: ldc 185
    //   30: iconst_0
    //   31: anewarray 187	java/lang/Object
    //   34: invokestatic 193	com/ea/nimble/Log$Helper:LOGW	(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;)V
    //   37: goto -15 -> 22
    //   40: astore_3
    //   41: aload_0
    //   42: monitorexit
    //   43: aload_3
    //   44: athrow
    //   45: astore_2
    //   46: aload_0
    //   47: ldc 185
    //   49: iconst_0
    //   50: anewarray 187	java/lang/Object
    //   53: invokestatic 193	com/ea/nimble/Log$Helper:LOGW	(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;)V
    //   56: goto -34 -> 22
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	59	0	this	ApplicationEnvironmentImpl
    //   17	2	1	localThread	java.lang.Thread
    //   45	1	2	localThrowable	Throwable
    //   40	4	3	localObject	Object
    //   25	1	4	localVerifyError	VerifyError
    // Exception table:
    //   from	to	target	type
    //   18	22	25	java/lang/VerifyError
    //   2	18	40	finally
    //   18	22	40	finally
    //   27	37	40	finally
    //   46	56	40	finally
    //   18	22	45	java/lang/Throwable
  }
  
  public static void setCurrentActivity(Activity paramActivity)
  {
    isMainApplicationRunning = true;
    s_currentActivity = paramActivity;
  }
  
  public int getAgeCompliance()
  {
    Persistence localPersistence = PersistenceService.getPersistenceForNimbleComponent("com.ea.nimble.applicationEnvironment", Persistence.Storage.CACHE);
    Serializable localSerializable = localPersistence.getValue("timeRetrieved");
    if (localSerializable != null)
    {
      if ((int)(new Date().getTime() - ((Long)localSerializable).longValue()) / 3600000 > 24)
      {
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = ((Object)null);
        Log.Helper.LOGI(this, "getAgeCompliance- Stored value is older than 24 hours. Call refreshAgeCompliance to retrieve minAgeCompliance", arrayOfObject2);
        return -1;
      }
      return ((Integer)localPersistence.getValue("ageRequirement")).intValue();
    }
    Object[] arrayOfObject1 = new Object[1];
    arrayOfObject1[0] = ((Object)null);
    Log.Helper.LOGI(this, "getAgeCompliance- No stored value in persistance. Call refreshAgeCompliance to retrieve minAgeCompliance.", arrayOfObject1);
    return -1;
  }
  
  public String getApplicationBundleId()
  {
    if (this.m_packageId == null)
    {
      Context localContext = getApplicationContext();
      if (localContext != null) {
        this.m_packageId = localContext.getPackageName();
      }
    }
    return this.m_packageId;
  }
  
  public Context getApplicationContext()
  {
    return this.m_context;
  }
  
  public String getApplicationLanguageCode()
  {
    return this.m_language;
  }
  
  public String getApplicationName()
  {
    Context localContext = getApplicationContext();
    if (localContext == null) {
      return null;
    }
    return localContext.getPackageManager().getApplicationLabel(localContext.getApplicationInfo()).toString();
  }
  
  public String getApplicationVersion()
  {
    Context localContext = getApplicationContext();
    if (localContext == null) {
      return null;
    }
    try
    {
      String str = localContext.getPackageManager().getPackageInfo(localContext.getPackageName(), 0).versionName;
      return str;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = localContext.getPackageName();
      Log.Helper.LOGE(this, "Package name %s not found", arrayOfObject);
    }
    return null;
  }
  
  public String getCachePath()
  {
    Context localContext = getApplicationContext();
    if (localContext == null) {}
    for (String str = System.getProperty("user.dir") + File.separator + "cache";; str = localContext.getCacheDir().getPath()) {
      return str + File.separator + "Nimble" + File.separator + this.m_core.getConfiguration().toString();
    }
  }
  
  public String getCarrier()
  {
    Context localContext = getApplicationContext();
    if (localContext == null) {
      return null;
    }
    return ((TelephonyManager)localContext.getSystemService("phone")).getNetworkOperator();
  }
  
  public String getComponentId()
  {
    return "com.ea.nimble.applicationEnvironment";
  }
  
  public String getDeviceString()
  {
    return Build.MANUFACTURER + Build.MODEL;
  }
  
  public String getDocumentPath()
  {
    Context localContext = getApplicationContext();
    if (localContext == null) {}
    for (String str = System.getProperty("user.dir") + File.separator + "doc";; str = localContext.getFilesDir().getPath()) {
      return str + File.separator + "Nimble" + File.separator + this.m_core.getConfiguration().toString();
    }
  }
  
  public String getGameSpecifiedPlayerId()
  {
    return this.m_gameSpecifiedPlayerId;
  }
  
  public String getGoogleAdvertisingId()
  {
    return this.m_googleAdvertisingId;
  }
  
  public String getGoogleEmail()
  {
    AccountManager localAccountManager = AccountManager.get(getApplicationContext());
    Account[] arrayOfAccount1 = localAccountManager.getAccountsByType("com.google");
    if (arrayOfAccount1.length > 0) {
      return arrayOfAccount1[0].name;
    }
    Pattern localPattern = Patterns.EMAIL_ADDRESS;
    for (Account localAccount : localAccountManager.getAccounts()) {
      if (localPattern.matcher(localAccount.name).matches()) {
        return localAccount.name;
      }
    }
    return null;
  }
  
  public String getLogSourceTitle()
  {
    return "AppEnv";
  }
  
  public String getMACAddress()
  {
    Context localContext = getApplicationContext();
    if (localContext == null) {
      return null;
    }
    WifiInfo localWifiInfo = ((WifiManager)localContext.getSystemService("wifi")).getConnectionInfo();
    if (localWifiInfo == null) {
      return null;
    }
    return localWifiInfo.getMacAddress();
  }
  
  public String getOsVersion()
  {
    return String.valueOf(Build.VERSION.SDK_INT);
  }
  
  public String getTempPath()
  {
    return getCachePath() + File.separator + "temp";
  }
  
  public boolean isAppCracked()
  {
    Log.Helper.LOGDS("FraudDetection", "Returning false for isAppCracked() since it hasn't been implemented yet", new Object[0]);
    return false;
  }
  
  public boolean isDeviceRooted()
  {
    String str = Build.TAGS;
    if ((str != null) && (str.contains("test-keys"))) {}
    while ((new File("/system/app/Superuser.apk").exists()) || (commandExists("su"))) {
      return true;
    }
    return false;
  }
  
  public boolean isLimitAdTrackingEnabled()
  {
    return this.m_googleLimitAdTrackingEnabled;
  }
  
  public void refreshAgeCompliance()
  {
    if (Network.getComponent().getStatus() != Network.Status.OK)
    {
      Error localError = new Error(Error.Code.NETWORK_NO_CONNECTION, "No network connection, Min Age cannot update.");
      HashMap localHashMap = new HashMap();
      localHashMap.put("result", "0");
      localHashMap.put("error", localError);
      Utility.sendBroadcastSerializable("nimble.notification.age_compliance_refreshed", localHashMap);
      return;
    }
    SynergyRequest.SynergyRequestPreparingCallback local1 = new SynergyRequest.SynergyRequestPreparingCallback()
    {
      public void prepareRequest(SynergyRequest paramAnonymousSynergyRequest)
      {
        paramAnonymousSynergyRequest.baseUrl = SynergyEnvironment.getComponent().getServerUrlWithKey("geoip.url");
        paramAnonymousSynergyRequest.send();
      }
    };
    SynergyNetworkConnectionCallback local2 = new SynergyNetworkConnectionCallback()
    {
      public void callback(SynergyNetworkConnectionHandle paramAnonymousSynergyNetworkConnectionHandle)
      {
        HashMap localHashMap = new HashMap();
        Map localMap;
        if (paramAnonymousSynergyNetworkConnectionHandle.getResponse().getError() == null)
        {
          localMap = paramAnonymousSynergyNetworkConnectionHandle.getResponse().getJsonData();
          Integer localInteger = (Integer)localMap.get("code");
          if ((localInteger != null) && (localInteger.intValue() > 0))
          {
            String str = (String)localMap.get("message");
            Log.Helper.LOGD(this, "LOG_CALLBACK_ERROR : %s", new Object[] { str });
            localHashMap.put("result", "0");
            localHashMap.put("error", new Exception(str));
          }
        }
        for (;;)
        {
          Utility.sendBroadcastSerializable("nimble.notification.age_compliance_refreshed", localHashMap);
          return;
          int i = ((Integer)((Map)localMap.get("agerequirements")).get("minLegalRegAge")).intValue();
          Persistence localPersistence = PersistenceService.getPersistenceForNimbleComponent("com.ea.nimble.applicationEnvironment", Persistence.Storage.CACHE);
          localPersistence.setValue("timeRetrieved", Long.valueOf(new Date().getTime()));
          localPersistence.setValue("ageRequirement", Integer.valueOf(i));
          localHashMap.put("result", "1");
          continue;
          Log.Helper.LOGD(this, "LOG_CALLBACK_ERROR : %s", new Object[] { paramAnonymousSynergyNetworkConnectionHandle.getResponse().getError().getMessage() });
          localHashMap.put("result", "0");
          localHashMap.put("error", paramAnonymousSynergyNetworkConnectionHandle.getResponse().getError());
        }
      }
    };
    SynergyRequest localSynergyRequest = new SynergyRequest("/rest/agerequirements/ip", IHttpRequest.Method.GET, local1);
    SynergyNetwork.getComponent().sendRequest(localSynergyRequest, local2);
  }
  
  protected void restore()
  {
    Persistence localPersistence;
    if ((this.m_gameSpecifiedPlayerId == null) || (this.m_gameSpecifiedPlayerId.length() <= 0))
    {
      Log.Helper.LOGDS("ApplicationEnvironment", "Current game specified player ID is empty, reload from persistence", new Object[0]);
      localPersistence = PersistenceService.getPersistenceForNimbleComponent("com.ea.nimble.applicationEnvironment", Persistence.Storage.DOCUMENT);
      if (localPersistence == null) {
        Log.Helper.LOGWS("ApplicationEnvironment", "Persistence is null - Couldn't read Game Specified Player ID from Persistence", new Object[0]);
      }
    }
    else
    {
      return;
    }
    this.m_gameSpecifiedPlayerId = localPersistence.getStringValue("nimble_applicationenvironment_game_specified_id");
  }
  
  protected void resume()
  {
    try
    {
      retrieveGoogleAdvertiserId();
      return;
    }
    catch (VerifyError localVerifyError)
    {
      Log.Helper.LOGW(this, "APP_ENV: Cannot get Google Advertising ID because this device is not supported", new Object[0]);
      return;
    }
    catch (Throwable localThrowable)
    {
      Log.Helper.LOGW(this, "APP_ENV: Cannot get Google Advertising ID because this device is not supported", new Object[0]);
    }
  }
  
  public void setApplicationBundleId(String paramString)
  {
    this.m_packageId = paramString;
  }
  
  public void setApplicationLanguageCode(String paramString)
  {
    if (!Utility.validString(paramString))
    {
      Log.Helper.LOGI(this, "AppEnv: Set language to device default when parameter is nil or empty string.", new Object[0]);
      paramString = Locale.getDefault().getLanguage();
    }
    int i = paramString.indexOf('_');
    if (i < 0) {}
    for (String str = paramString;; str = paramString.substring(0, i))
    {
      int j = str.indexOf('-');
      if (j > 0) {
        str = str.substring(0, j);
      }
      if (!str.equals(this.m_language)) {
        break;
      }
      Log.Helper.LOGD(this, "Setting the same language code %s, skipping assignment", new Object[] { str });
      return;
    }
    String[] arrayOfString = Locale.getISOLanguages();
    int k = arrayOfString.length;
    for (int m = 0;; m++)
    {
      if (m >= k) {
        break label158;
      }
      if (arrayOfString[m].equals(str))
      {
        if (paramString.equals(this.m_language)) {
          break;
        }
        this.m_language = paramString;
        Utility.sendBroadcast("nimble.notification.LanguageChanged", null);
        return;
      }
    }
    label158:
    Log.Helper.LOGF(this, "Unknown language code " + paramString, new Object[0]);
  }
  
  public void setGameSpecifiedPlayerId(String paramString)
  {
    this.m_gameSpecifiedPlayerId = paramString;
    Persistence localPersistence = PersistenceService.getPersistenceForNimbleComponent("com.ea.nimble.applicationEnvironment", Persistence.Storage.DOCUMENT);
    if (localPersistence == null)
    {
      Log.Helper.LOGWS("ApplicationEnvironment", "Persistence is null - Couldn't save Game Specified Player ID to Persistence", new Object[0]);
      return;
    }
    localPersistence.setValue("nimble_applicationenvironment_game_specified_id", this.m_gameSpecifiedPlayerId);
  }
  
  protected void setup()
  {
    this.m_context = s_currentActivity.getApplicationContext();
    try
    {
      retrieveGoogleAdvertiserId();
      return;
    }
    catch (VerifyError localVerifyError)
    {
      Log.Helper.LOGW(this, "APP_ENV: Cannot get Google Advertising ID because this device is not supported", new Object[0]);
      return;
    }
    catch (Throwable localThrowable)
    {
      Log.Helper.LOGW(this, "APP_ENV: Cannot get Google Advertising ID because this device is not supported", new Object[0]);
    }
  }
  
  protected void teardown()
  {
    this.m_context = null;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ea.nimble.ApplicationEnvironmentImpl
 * JD-Core Version:    0.7.0.1
 */