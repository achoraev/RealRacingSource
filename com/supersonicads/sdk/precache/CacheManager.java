package com.supersonicads.sdk.precache;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Environment;
import android.text.TextUtils;
import com.supersonicads.sdk.data.SSABCParameters;
import com.supersonicads.sdk.data.SSAEnums.BackButtonState;
import com.supersonicads.sdk.data.SSAEnums.ProductType;
import com.supersonicads.sdk.data.SSAFile;
import com.supersonicads.sdk.data.SSAObj;
import com.supersonicads.sdk.session.SSASession;
import com.supersonicads.sdk.utils.DeviceProperties;
import com.supersonicads.sdk.utils.SDKUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CacheManager
{
  private static final String SSA_DIRECTORY_NAME = "supersonicads";
  public static final String SSA_SDK_DOWNLOAD_URL = "ssa_sdk_download_url";
  private static final String TAG = "CacheManager";
  private final String APPLICATION_KEY_BC = "application_key_bc";
  private final String APPLICATION_KEY_IS = "application_key_is";
  private final String APPLICATION_KEY_OW = "application_key_ow";
  private final String APPS_INSTALL = "apps_install";
  private final String BACK_BUTTON_STATE = "back_button_state";
  private final String BINARY_TOGGLE = "binary_toggle";
  private final String CONTROLLER_USER_DATA = "controller_user_data";
  private final String CURRENT_SDK_VERSION = "current_sdk_version";
  private final String CURRENT_SESSION = "current_session";
  private final String DEBUG_MODE = "debug_mode";
  private final String DOMAIN = "domain";
  private final String REGISTER_SESSIONS = "register_sessions";
  private final String SEARCH_KEYS = "search_keys";
  private final String SESSIONS = "sessions";
  private final String SHARED_PREF_LAST_UPDATE_CAMPAIGN = "shared_pref_last_update_campaign";
  private final String SHARED_PREF_SSA_BC_PARAMETERS = "shared_pref_ssa_bc_parameters";
  private final String SHARED_PREF_SSA_GENERAL = "shared_pref_ssa_general";
  private final String SHARED_PREF_SSA_INIT_PARAMETERS = "shared_pref_ssa_init_parameters";
  private final String SSA_BC_PARAMETER_CONNECTION_RETRIES = "ssa_bc_parameter_connection_retries";
  private final String UNIQUE_ID_BC = "unique_id_bc";
  private final String UNIQUE_ID_IS = "unique_id_is";
  private final String UNIQUE_ID_OW = "unique_id_ow";
  private final String USER_ID_BC = "user_id_bc";
  private final String USER_ID_IS = "user_id_is";
  private final String USER_ID_OW = "user_id_ow";
  private final String VERSION = "version";
  private Object block = new Object();
  private OnAppsInstall mAppsInstallListener;
  private Context mContext;
  boolean mExternalStorageAvailable = false;
  boolean mExternalStorageWriteable = false;
  private SharedPreferences mSharedPreferences;
  private Object mUserDataLock = new Object();
  private String rootDirectoryPath;
  private final String state = Environment.getExternalStorageState();
  
  public CacheManager(Context paramContext)
  {
    this.mContext = paramContext.getApplicationContext();
    createRootDirectory();
  }
  
  private JSONObject buildFilesMap(String paramString)
  {
    File localFile1 = new File(getRootDirectoryPath(), paramString);
    JSONObject localJSONObject = new JSONObject();
    File[] arrayOfFile = localFile1.listFiles();
    int i;
    if (arrayOfFile != null) {
      i = arrayOfFile.length;
    }
    for (int j = 0;; j++)
    {
      if (j >= i) {
        return localJSONObject;
      }
      File localFile2 = arrayOfFile[j];
      try
      {
        Object localObject = looping(localFile2);
        if ((localObject instanceof JSONArray)) {
          localJSONObject.put("files", looping(localFile2));
        } else if ((localObject instanceof JSONObject)) {
          localJSONObject.put(localFile2.getName(), looping(localFile2));
        }
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
      }
    }
  }
  
  private void deleteAllFiles(String paramString)
  {
    File[] arrayOfFile = new File(paramString).listFiles();
    if (arrayOfFile == null) {
      return;
    }
    int i = arrayOfFile.length;
    int j = 0;
    label23:
    File localFile;
    if (j < i)
    {
      localFile = arrayOfFile[j];
      if (!localFile.isDirectory()) {
        break label64;
      }
      deleteAllFiles(localFile.getAbsolutePath());
      localFile.delete();
    }
    for (;;)
    {
      j++;
      break label23;
      break;
      label64:
      localFile.delete();
    }
  }
  
  private boolean deleteFolderRecursive(File paramFile)
  {
    int i = 0;
    if (!paramFile.exists()) {
      return false;
    }
    if (paramFile.isDirectory())
    {
      File[] arrayOfFile = paramFile.listFiles();
      int j = arrayOfFile.length;
      for (;;)
      {
        if (i >= j) {
          return paramFile.delete();
        }
        deleteFolderRecursive(arrayOfFile[i]);
        i++;
      }
    }
    return paramFile.delete();
  }
  
  private File getDiskCacheDir(Context paramContext, String paramString)
  {
    if (isExternalStorageAvailable())
    {
      if (getExternalCacheDir(paramContext) != null) {
        return new File(getExternalCacheDir(paramContext).getPath() + File.separator + paramString);
      }
      return new File(getInternalCacheDirPath(paramContext) + File.separator + paramString);
    }
    return new File(getInternalCacheDirPath(paramContext) + File.separator + paramString);
  }
  
  private static File getExternalCacheDir(Context paramContext)
  {
    return paramContext.getExternalCacheDir();
  }
  
  private static String getInternalCacheDirPath(Context paramContext)
  {
    File localFile = paramContext.getCacheDir();
    String str = null;
    if (localFile != null) {
      str = localFile.getPath();
    }
    return str;
  }
  
  public static boolean isExternalStorageAvailable(Context paramContext)
  {
    String str = Environment.getExternalStorageState();
    if ("mounted".equals(str)) {
      return true;
    }
    return "mounted_ro".equals(str);
  }
  
  private boolean isFolder(File paramFile)
  {
    return paramFile.isDirectory();
  }
  
  private Object looping(File paramFile)
  {
    JSONObject localJSONObject = new JSONObject();
    JSONArray localJSONArray = new JSONArray();
    for (;;)
    {
      int j;
      try
      {
        if (paramFile.isFile())
        {
          localJSONArray.put(paramFile.getName());
          return localJSONArray;
        }
        arrayOfFile = paramFile.listFiles();
        int i = arrayOfFile.length;
        j = 0;
        if (j < i) {
          continue;
        }
        if (isFolder(paramFile))
        {
          String str2 = getCampaignLastUpdate(paramFile.getName());
          if (str2 != null) {
            localJSONObject.put("lastUpdateTime", str2);
          }
        }
        str1 = paramFile.getName();
        if (!str1.equalsIgnoreCase(SSAEnums.ProductType.BrandConnect.toString())) {
          continue;
        }
        localProductType = SSAEnums.ProductType.BrandConnect;
      }
      catch (JSONException localJSONException)
      {
        File[] arrayOfFile;
        String str1;
        File localFile;
        localJSONException.printStackTrace();
        break label278;
        if (!str1.equalsIgnoreCase(SSAEnums.ProductType.OfferWall.toString())) {
          continue;
        }
        SSAEnums.ProductType localProductType = SSAEnums.ProductType.OfferWall;
        continue;
        boolean bool = str1.equalsIgnoreCase(SSAEnums.ProductType.Interstitial.toString());
        localProductType = null;
        if (!bool) {
          continue;
        }
        localProductType = SSAEnums.ProductType.Interstitial;
        continue;
      }
      if (localProductType != null)
      {
        localJSONObject.put(SDKUtils.encodeString("applicationUserId"), SDKUtils.encodeString(getUniqueId(localProductType)));
        localJSONObject.put(SDKUtils.encodeString("applicationKey"), SDKUtils.encodeString(getApplicationKey(localProductType)));
        break label278;
        localFile = arrayOfFile[j];
        if (isFolder(localFile))
        {
          localJSONObject.put(localFile.getName(), looping(localFile));
          break label280;
        }
        localJSONArray.put(localFile.getName());
        localJSONObject.put("files", localJSONArray);
        break label280;
      }
      label278:
      return localJSONObject;
      label280:
      j++;
    }
  }
  
  public void addSession(SSASession paramSSASession)
  {
    JSONObject localJSONObject;
    if (getShouldRegisterSessions()) {
      localJSONObject = new JSONObject();
    }
    try
    {
      localJSONObject.put("sessionStartTime", paramSSASession.getSessionStartTime());
      localJSONObject.put("sessionEndTime", paramSSASession.getSessionEndTime());
      localJSONObject.put("sessionType", paramSSASession.getSessionType());
      localJSONObject.put("connectivity", paramSSASession.getConnectivity());
      label63:
      JSONArray localJSONArray = getSessions();
      if (localJSONArray == null) {
        localJSONArray = new JSONArray();
      }
      localJSONArray.put(localJSONObject);
      this.mSharedPreferences = this.mContext.getSharedPreferences("shared_pref_ssa_general", 0);
      SharedPreferences.Editor localEditor = this.mSharedPreferences.edit();
      localEditor.putString("sessions", localJSONArray.toString());
      localEditor.commit();
      return;
    }
    catch (JSONException localJSONException)
    {
      break label63;
    }
  }
  
  public void createRootDirectory()
  {
    if (isExternalStorageAvailable())
    {
      File localFile = getDiskCacheDir(this.mContext, "supersonicads");
      if (!localFile.exists()) {
        localFile.mkdir();
      }
      setRootDirectoryPath(localFile.getPath());
    }
  }
  
  public boolean deleteFile(String paramString1, String paramString2)
  {
    for (;;)
    {
      int j;
      synchronized (this.block)
      {
        File[] arrayOfFile = new File(getRootDirectoryPath(), paramString1).listFiles();
        if (arrayOfFile == null) {
          return false;
        }
        int i = arrayOfFile.length;
        j = 0;
        if (j >= i) {
          return false;
        }
        File localFile = arrayOfFile[j];
        if ((localFile.isFile()) && (localFile.getName().equalsIgnoreCase(paramString2)))
        {
          boolean bool = localFile.delete();
          return bool;
        }
      }
      j++;
    }
  }
  
  public boolean deleteFolder(String paramString)
  {
    synchronized (this.block)
    {
      boolean bool = deleteFolderRecursive(new File(getRootDirectoryPath(), paramString));
      return bool;
    }
  }
  
  public void deleteSessions()
  {
    this.mSharedPreferences = this.mContext.getSharedPreferences("shared_pref_ssa_general", 0);
    SharedPreferences.Editor localEditor = this.mSharedPreferences.edit();
    localEditor.putString("sessions", null);
    localEditor.commit();
  }
  
  public String getApplicationKey(SSAEnums.ProductType paramProductType)
  {
    this.mSharedPreferences = this.mContext.getSharedPreferences("shared_pref_ssa_init_parameters", 0);
    switch (paramProductType)
    {
    default: 
      return "EMPTY_APPLICATION_KEY";
    case BrandConnect: 
      return this.mSharedPreferences.getString("application_key_bc", null);
    case Interstitial: 
      return this.mSharedPreferences.getString("application_key_ow", null);
    }
    return this.mSharedPreferences.getString("application_key_is", null);
  }
  
  public SSAEnums.BackButtonState getBackButtonState()
  {
    this.mSharedPreferences = this.mContext.getSharedPreferences("shared_pref_ssa_general", 0);
    int i = Integer.parseInt(this.mSharedPreferences.getString("back_button_state", "2"));
    if (i == 0) {
      return SSAEnums.BackButtonState.None;
    }
    if (i == 1) {
      return SSAEnums.BackButtonState.Device;
    }
    if (i == 2) {
      return SSAEnums.BackButtonState.Controller;
    }
    return SSAEnums.BackButtonState.Controller;
  }
  
  public String getCachedFilesMap(String paramString)
  {
    JSONObject localJSONObject = buildFilesMap(paramString);
    try
    {
      localJSONObject.put("path", paramString);
      return localJSONObject.toString();
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        localJSONException.printStackTrace();
      }
    }
  }
  
  public String getCampaignLastUpdate(String paramString)
  {
    this.mSharedPreferences = this.mContext.getSharedPreferences("shared_pref_last_update_campaign", 0);
    return this.mSharedPreferences.getString(paramString, null);
  }
  
  public String getConnectionRetries()
  {
    this.mSharedPreferences = this.mContext.getSharedPreferences("shared_pref_ssa_bc_parameters", 0);
    return this.mSharedPreferences.getString("ssa_bc_parameter_connection_retries", "3");
  }
  
  public String getCurrentSDKVersion()
  {
    this.mSharedPreferences = this.mContext.getSharedPreferences("current_sdk_version", 0);
    return this.mSharedPreferences.getString("version", "UN_VERSIONED");
  }
  
  public String getRootDirectoryPath()
  {
    return this.rootDirectoryPath;
  }
  
  public String getSDKDownloadUrl()
  {
    this.mSharedPreferences = this.mContext.getSharedPreferences("shared_pref_ssa_general", 0);
    return this.mSharedPreferences.getString("ssa_sdk_download_url", null);
  }
  
  public List<String> getSearchKeys()
  {
    this.mSharedPreferences = this.mContext.getSharedPreferences("shared_pref_ssa_general", 0);
    String str = this.mSharedPreferences.getString("search_keys", null);
    ArrayList localArrayList = new ArrayList();
    JSONArray localJSONArray;
    if (str != null)
    {
      SSAObj localSSAObj = new SSAObj(str);
      if (localSSAObj.containsKey("searchKeys")) {
        localJSONArray = (JSONArray)localSSAObj.get("searchKeys");
      }
    }
    try
    {
      localArrayList.addAll(SSAObj.toList(localJSONArray));
      return localArrayList;
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
    return localArrayList;
  }
  
  public JSONArray getSessions()
  {
    this.mSharedPreferences = this.mContext.getSharedPreferences("shared_pref_ssa_general", 0);
    String str = this.mSharedPreferences.getString("sessions", null);
    if (str == null) {
      return new JSONArray();
    }
    try
    {
      JSONArray localJSONArray1 = new JSONArray(str);
      localJSONArray2 = localJSONArray1;
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        JSONArray localJSONArray2 = null;
      }
    }
    return localJSONArray2;
  }
  
  public boolean getShouldRegisterSessions()
  {
    this.mSharedPreferences = this.mContext.getSharedPreferences("shared_pref_ssa_general", 0);
    return this.mSharedPreferences.getBoolean("register_sessions", true);
  }
  
  public String getUniqueId(SSAEnums.ProductType paramProductType)
  {
    this.mSharedPreferences = this.mContext.getSharedPreferences("shared_pref_ssa_init_parameters", 0);
    switch (paramProductType)
    {
    default: 
      return "EMPTY_UNIQUE_ID";
    case BrandConnect: 
      return this.mSharedPreferences.getString("unique_id_bc", null);
    case Interstitial: 
      return this.mSharedPreferences.getString("unique_id_ow", null);
    }
    return this.mSharedPreferences.getString("unique_id_is", null);
  }
  
  public String getUniqueId(String paramString)
  {
    this.mSharedPreferences = this.mContext.getSharedPreferences("shared_pref_ssa_init_parameters", 0);
    String str = "EMPTY_UNIQUE_ID";
    if (paramString.equalsIgnoreCase(SSAEnums.ProductType.BrandConnect.toString())) {
      str = this.mSharedPreferences.getString("unique_id_bc", null);
    }
    do
    {
      return str;
      if (paramString.equalsIgnoreCase(SSAEnums.ProductType.OfferWall.toString())) {
        return this.mSharedPreferences.getString("unique_id_ow", null);
      }
    } while (!paramString.equalsIgnoreCase(SSAEnums.ProductType.Interstitial.toString()));
    return this.mSharedPreferences.getString("unique_id_is", null);
  }
  
  public String getUserData(String paramString)
  {
    synchronized (this.mUserDataLock)
    {
      this.mSharedPreferences = this.mContext.getSharedPreferences("controller_user_data", 0);
      String str = this.mSharedPreferences.getString(paramString, null);
      if (str != null) {
        return str;
      }
      return "{}";
    }
  }
  
  public String getUserID(SSAEnums.ProductType paramProductType)
  {
    this.mSharedPreferences = this.mContext.getSharedPreferences("shared_pref_ssa_init_parameters", 0);
    switch (paramProductType)
    {
    default: 
      return "EMPTY_USER_ID";
    case BrandConnect: 
      return this.mSharedPreferences.getString("user_id_bc", null);
    case Interstitial: 
      return this.mSharedPreferences.getString("user_id_ow", null);
    }
    return this.mSharedPreferences.getString("user_id_is", null);
  }
  
  public boolean isExternalStorageAvailable()
  {
    if ("mounted".equals(this.state))
    {
      this.mExternalStorageWriteable = true;
      this.mExternalStorageAvailable = true;
    }
    for (;;)
    {
      return this.mExternalStorageAvailable;
      if ("mounted_ro".equals(this.state))
      {
        this.mExternalStorageAvailable = true;
        this.mExternalStorageWriteable = false;
      }
      else
      {
        this.mExternalStorageWriteable = false;
        this.mExternalStorageAvailable = false;
      }
    }
  }
  
  public boolean isFileCached(SSAFile paramSSAFile)
  {
    int i;
    int j;
    synchronized (this.block)
    {
      File localFile1 = new File(getRootDirectoryPath(), paramSSAFile.getPath());
      File[] arrayOfFile;
      if ((localFile1 != null) && (localFile1.listFiles() != null))
      {
        arrayOfFile = localFile1.listFiles();
        i = arrayOfFile.length;
        j = 0;
      }
      else
      {
        return false;
        label55:
        File localFile2 = arrayOfFile[j];
        if ((!localFile2.isFile()) || (!localFile2.getName().equalsIgnoreCase(SDKUtils.getFileName(paramSSAFile.getFile())))) {
          break label109;
        }
        return true;
      }
    }
    for (;;)
    {
      if (j < i) {
        break label55;
      }
      break;
      label109:
      j++;
    }
  }
  
  public boolean isMobileConrollerExist()
  {
    File localFile1 = new File(getRootDirectoryPath(), "");
    File[] arrayOfFile;
    int i;
    if ((localFile1 != null) && (localFile1.listFiles() != null))
    {
      arrayOfFile = localFile1.listFiles();
      i = arrayOfFile.length;
    }
    for (int j = 0;; j++)
    {
      if (j >= i) {
        return false;
      }
      File localFile2 = arrayOfFile[j];
      if ((localFile2.isFile()) && (localFile2.getName().equalsIgnoreCase("mobileController.html"))) {
        return true;
      }
    }
  }
  
  public boolean isPathExist(String paramString)
  {
    return new File(getRootDirectoryPath(), paramString).exists();
  }
  
  public String makeDir(String paramString)
  {
    synchronized (this.block)
    {
      File localFile = new File(getRootDirectoryPath(), paramString);
      if ((!localFile.exists()) && (!localFile.mkdirs())) {
        return null;
      }
      String str = localFile.getPath();
      return str;
    }
  }
  
  public void refreshRootDirectory(Context paramContext)
  {
    if (!getCurrentSDKVersion().equalsIgnoreCase(DeviceProperties.getInstance(paramContext).getSupersonicSdkVersion()))
    {
      setCurrentSDKVersion(paramContext);
      File localFile = getExternalCacheDir(paramContext);
      if (localFile != null) {
        deleteAllFiles(localFile.getAbsolutePath() + File.separator + "supersonicads" + File.separator);
      }
      deleteAllFiles(getInternalCacheDirPath(paramContext) + File.separator + "supersonicads" + File.separator);
      createRootDirectory();
    }
  }
  
  public void setApplicationKey(String paramString, SSAEnums.ProductType paramProductType)
  {
    this.mSharedPreferences = this.mContext.getSharedPreferences("shared_pref_ssa_init_parameters", 0);
    SharedPreferences.Editor localEditor = this.mSharedPreferences.edit();
    switch (paramProductType)
    {
    }
    for (;;)
    {
      localEditor.commit();
      return;
      localEditor.putString("application_key_bc", paramString);
      continue;
      localEditor.putString("application_key_ow", paramString);
      continue;
      localEditor.putString("application_key_is", paramString);
    }
  }
  
  public void setAppsInstall()
  {
    new AppsInstallAsync(null).execute(new Void[0]);
  }
  
  public void setBackButtonState(String paramString)
  {
    this.mSharedPreferences = this.mContext.getSharedPreferences("shared_pref_ssa_general", 0);
    SharedPreferences.Editor localEditor = this.mSharedPreferences.edit();
    localEditor.putString("back_button_state", paramString);
    localEditor.commit();
  }
  
  public void setCampaignLastUpdate(String paramString1, String paramString2)
  {
    this.mSharedPreferences = this.mContext.getApplicationContext().getSharedPreferences("shared_pref_last_update_campaign", 0);
    SharedPreferences.Editor localEditor = this.mSharedPreferences.edit();
    localEditor.putString(paramString1, paramString2);
    localEditor.commit();
  }
  
  public void setCurrentSDKVersion(Context paramContext)
  {
    String str = DeviceProperties.getInstance(paramContext).getSupersonicSdkVersion();
    this.mSharedPreferences = this.mContext.getApplicationContext().getSharedPreferences("current_sdk_version", 0);
    SharedPreferences.Editor localEditor = this.mSharedPreferences.edit();
    localEditor.putString("version", str);
    localEditor.commit();
  }
  
  public boolean setLatestCompeltionsTime(String paramString1, String paramString2, String paramString3)
  {
    synchronized (this.mUserDataLock)
    {
      this.mSharedPreferences = this.mContext.getSharedPreferences("controller_user_data", 0);
      String str = this.mSharedPreferences.getString("ssaUserData", null);
      boolean bool1 = TextUtils.isEmpty(str);
      if (!bool1) {}
      try
      {
        JSONObject localJSONObject1 = new JSONObject(str);
        if (!localJSONObject1.isNull(paramString2))
        {
          JSONObject localJSONObject2 = localJSONObject1.getJSONObject(paramString2);
          if (!localJSONObject2.isNull(paramString3))
          {
            localJSONObject2.getJSONObject(paramString3).put("timestamp", paramString1);
            SharedPreferences.Editor localEditor = this.mSharedPreferences.edit();
            localEditor.putString("ssaUserData", localJSONObject1.toString());
            boolean bool2 = localEditor.commit();
            return bool2;
          }
        }
      }
      catch (JSONException localJSONException)
      {
        label143:
        break label143;
      }
      return false;
    }
  }
  
  public void setOnAppsInstallListener(OnAppsInstall paramOnAppsInstall)
  {
    this.mAppsInstallListener = paramOnAppsInstall;
  }
  
  public void setRootDirectoryPath(String paramString)
  {
    this.rootDirectoryPath = paramString;
  }
  
  public void setSSABCParameters(SSABCParameters paramSSABCParameters)
  {
    this.mSharedPreferences = this.mContext.getApplicationContext().getSharedPreferences("shared_pref_ssa_bc_parameters", 0);
    SharedPreferences.Editor localEditor = this.mSharedPreferences.edit();
    localEditor.putString("ssa_bc_parameter_connection_retries", paramSSABCParameters.getConnectionRetries());
    localEditor.commit();
  }
  
  public void setSearchKeys(String paramString)
  {
    this.mSharedPreferences = this.mContext.getSharedPreferences("shared_pref_ssa_general", 0);
    SharedPreferences.Editor localEditor = this.mSharedPreferences.edit();
    localEditor.putString("search_keys", paramString);
    localEditor.commit();
  }
  
  public void setShouldRegisterSessions(boolean paramBoolean)
  {
    this.mSharedPreferences = this.mContext.getSharedPreferences("shared_pref_ssa_general", 0);
    SharedPreferences.Editor localEditor = this.mSharedPreferences.edit();
    localEditor.putBoolean("register_sessions", paramBoolean);
    localEditor.commit();
  }
  
  public boolean setUniqueId(String paramString1, String paramString2)
  {
    this.mSharedPreferences = this.mContext.getSharedPreferences("shared_pref_ssa_init_parameters", 0);
    SharedPreferences.Editor localEditor = this.mSharedPreferences.edit();
    if (paramString2.equalsIgnoreCase(SSAEnums.ProductType.BrandConnect.toString())) {
      localEditor.putString("unique_id_bc", paramString1);
    }
    for (;;)
    {
      return localEditor.commit();
      if (paramString2.equalsIgnoreCase(SSAEnums.ProductType.OfferWall.toString())) {
        localEditor.putString("unique_id_ow", paramString1);
      } else if (paramString2.equalsIgnoreCase(SSAEnums.ProductType.Interstitial.toString())) {
        localEditor.putString("unique_id_is", paramString1);
      }
    }
  }
  
  public boolean setUserData(String paramString1, String paramString2)
  {
    synchronized (this.mUserDataLock)
    {
      this.mSharedPreferences = this.mContext.getSharedPreferences("controller_user_data", 0);
      SharedPreferences.Editor localEditor = this.mSharedPreferences.edit();
      localEditor.putString(paramString1, paramString2);
      boolean bool = localEditor.commit();
      return bool;
    }
  }
  
  public void setUserID(String paramString, SSAEnums.ProductType paramProductType)
  {
    this.mSharedPreferences = this.mContext.getSharedPreferences("shared_pref_ssa_init_parameters", 0);
    SharedPreferences.Editor localEditor = this.mSharedPreferences.edit();
    switch (paramProductType)
    {
    }
    for (;;)
    {
      localEditor.commit();
      return;
      localEditor.putString("user_id_bc", paramString);
      continue;
      localEditor.putString("user_id_ow", paramString);
      continue;
      localEditor.putString("user_id_is", paramString);
    }
  }
  
  private class AppsInstallAsync
    extends AsyncTask<Void, Void, Void>
  {
    private JSONArray jsArr;
    private PackageManager pckMgr;
    
    private AppsInstallAsync() {}
    
    protected Void doInBackground(Void... paramVarArgs)
    {
      List localList = this.pckMgr.getInstalledApplications(0);
      this.jsArr = new JSONArray();
      Iterator localIterator = localList.iterator();
      for (;;)
      {
        if (!localIterator.hasNext()) {
          return null;
        }
        ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
        String str = localApplicationInfo.packageName;
        try
        {
          JSONObject localJSONObject = new JSONObject();
          localJSONObject.put("packageName", str);
          this.jsArr.put(localJSONObject);
        }
        catch (JSONException localJSONException) {}
      }
    }
    
    protected void onPostExecute(Void paramVoid)
    {
      if (CacheManager.this.mAppsInstallListener != null) {
        CacheManager.this.mAppsInstallListener.onAppsInstallFinish(this.jsArr);
      }
    }
    
    protected void onPreExecute()
    {
      this.pckMgr = CacheManager.this.mContext.getPackageManager();
    }
  }
  
  public static abstract interface OnAppsInstall
  {
    public abstract void onAppsInstallFinish(JSONArray paramJSONArray);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.supersonicads.sdk.precache.CacheManager
 * JD-Core Version:    0.7.0.1
 */