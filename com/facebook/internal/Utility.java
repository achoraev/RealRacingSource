package com.facebook.internal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.facebook.FacebookException;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Settings;
import com.facebook.model.GraphObject;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public final class Utility
{
  private static final String APPLICATION_FIELDS = "fields";
  private static final String[] APP_SETTING_FIELDS = { "supports_attribution", "supports_implicit_sdk_logging", "gdpv4_nux_content", "gdpv4_nux_enabled" };
  public static final int DEFAULT_STREAM_BUFFER_SIZE = 8192;
  private static final String EXTRA_APP_EVENTS_INFO_FORMAT_VERSION = "a1";
  private static final String HASH_ALGORITHM_MD5 = "MD5";
  private static final String HASH_ALGORITHM_SHA1 = "SHA-1";
  static final String LOG_TAG = "FacebookSDK";
  private static final String NUX_CONTENT = "gdpv4_nux_content";
  private static final String NUX_ENABLED = "gdpv4_nux_enabled";
  private static final String SUPPORTS_ATTRIBUTION = "supports_attribution";
  private static final String SUPPORTS_IMPLICIT_SDK_LOGGING = "supports_implicit_sdk_logging";
  private static final String URL_SCHEME = "https";
  private static Map<String, FetchedAppSettings> fetchedAppSettings = new ConcurrentHashMap();
  
  public static <T> boolean areObjectsEqual(T paramT1, T paramT2)
  {
    if (paramT1 == null) {
      return paramT2 == null;
    }
    return paramT1.equals(paramT2);
  }
  
  public static <T> ArrayList<T> arrayList(T... paramVarArgs)
  {
    ArrayList localArrayList = new ArrayList(paramVarArgs.length);
    int i = paramVarArgs.length;
    for (int j = 0; j < i; j++) {
      localArrayList.add(paramVarArgs[j]);
    }
    return localArrayList;
  }
  
  public static <T> List<T> asListNoNulls(T... paramVarArgs)
  {
    ArrayList localArrayList = new ArrayList();
    int i = paramVarArgs.length;
    for (int j = 0; j < i; j++)
    {
      T ? = paramVarArgs[j];
      if (? != null) {
        localArrayList.add(?);
      }
    }
    return localArrayList;
  }
  
  public static Uri buildUri(String paramString1, String paramString2, Bundle paramBundle)
  {
    Uri.Builder localBuilder = new Uri.Builder();
    localBuilder.scheme("https");
    localBuilder.authority(paramString1);
    localBuilder.path(paramString2);
    Iterator localIterator = paramBundle.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      Object localObject = paramBundle.get(str);
      if ((localObject instanceof String)) {
        localBuilder.appendQueryParameter(str, (String)localObject);
      }
    }
    return localBuilder.build();
  }
  
  public static void clearCaches(Context paramContext)
  {
    ImageDownloader.clearCache(paramContext);
  }
  
  private static void clearCookiesForDomain(Context paramContext, String paramString)
  {
    CookieSyncManager.createInstance(paramContext).sync();
    CookieManager localCookieManager = CookieManager.getInstance();
    String str = localCookieManager.getCookie(paramString);
    if (str == null) {
      return;
    }
    String[] arrayOfString1 = str.split(";");
    int i = arrayOfString1.length;
    for (int j = 0; j < i; j++)
    {
      String[] arrayOfString2 = arrayOfString1[j].split("=");
      if (arrayOfString2.length > 0) {
        localCookieManager.setCookie(paramString, arrayOfString2[0].trim() + "=;expires=Sat, 1 Jan 2000 00:00:01 UTC;");
      }
    }
    localCookieManager.removeExpiredCookie();
  }
  
  public static void clearFacebookCookies(Context paramContext)
  {
    clearCookiesForDomain(paramContext, "facebook.com");
    clearCookiesForDomain(paramContext, ".facebook.com");
    clearCookiesForDomain(paramContext, "https://facebook.com");
    clearCookiesForDomain(paramContext, "https://.facebook.com");
  }
  
  public static void closeQuietly(Closeable paramCloseable)
  {
    if (paramCloseable != null) {}
    try
    {
      paramCloseable.close();
      return;
    }
    catch (IOException localIOException) {}
  }
  
  static Map<String, Object> convertJSONObjectToHashMap(JSONObject paramJSONObject)
  {
    HashMap localHashMap = new HashMap();
    JSONArray localJSONArray = paramJSONObject.names();
    int i = 0;
    while (i < localJSONArray.length()) {
      try
      {
        String str = localJSONArray.getString(i);
        Object localObject = paramJSONObject.get(str);
        if ((localObject instanceof JSONObject)) {
          localObject = convertJSONObjectToHashMap((JSONObject)localObject);
        }
        localHashMap.put(str, localObject);
        label65:
        i++;
      }
      catch (JSONException localJSONException)
      {
        break label65;
      }
    }
    return localHashMap;
  }
  
  public static void deleteDirectory(File paramFile)
  {
    if (!paramFile.exists()) {
      return;
    }
    if (paramFile.isDirectory())
    {
      File[] arrayOfFile = paramFile.listFiles();
      int i = arrayOfFile.length;
      for (int j = 0; j < i; j++) {
        deleteDirectory(arrayOfFile[j]);
      }
    }
    paramFile.delete();
  }
  
  public static void disconnectQuietly(URLConnection paramURLConnection)
  {
    if ((paramURLConnection instanceof HttpURLConnection)) {
      ((HttpURLConnection)paramURLConnection).disconnect();
    }
  }
  
  public static String getActivityName(Context paramContext)
  {
    if (paramContext == null) {
      return "null";
    }
    if (paramContext == paramContext.getApplicationContext()) {
      return "unknown";
    }
    return paramContext.getClass().getSimpleName();
  }
  
  public static String getHashedDeviceAndAppID(Context paramContext, String paramString)
  {
    String str = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
    if (str == null) {
      return null;
    }
    return sha1hash(str + paramString);
  }
  
  public static String getMetadataApplicationId(Context paramContext)
  {
    Validate.notNull(paramContext, "context");
    Settings.loadDefaultsFromMetadata(paramContext);
    return Settings.getApplicationId();
  }
  
  public static Method getMethodQuietly(Class<?> paramClass, String paramString, Class<?>... paramVarArgs)
  {
    try
    {
      Method localMethod = paramClass.getMethod(paramString, paramVarArgs);
      return localMethod;
    }
    catch (NoSuchMethodException localNoSuchMethodException) {}
    return null;
  }
  
  public static Method getMethodQuietly(String paramString1, String paramString2, Class<?>... paramVarArgs)
  {
    try
    {
      Method localMethod = getMethodQuietly(Class.forName(paramString1), paramString2, paramVarArgs);
      return localMethod;
    }
    catch (ClassNotFoundException localClassNotFoundException) {}
    return null;
  }
  
  public static Object getStringPropertyAsJSON(JSONObject paramJSONObject, String paramString1, String paramString2)
    throws JSONException
  {
    Object localObject = paramJSONObject.opt(paramString1);
    if ((localObject != null) && ((localObject instanceof String))) {
      localObject = new JSONTokener((String)localObject).nextValue();
    }
    if ((localObject != null) && (!(localObject instanceof JSONObject)) && (!(localObject instanceof JSONArray)))
    {
      if (paramString2 != null)
      {
        JSONObject localJSONObject = new JSONObject();
        localJSONObject.putOpt(paramString2, localObject);
        return localJSONObject;
      }
      throw new FacebookException("Got an unexpected non-JSON object.");
    }
    return localObject;
  }
  
  private static String hashBytes(MessageDigest paramMessageDigest, byte[] paramArrayOfByte)
  {
    paramMessageDigest.update(paramArrayOfByte);
    byte[] arrayOfByte = paramMessageDigest.digest();
    StringBuilder localStringBuilder = new StringBuilder();
    int i = arrayOfByte.length;
    for (int j = 0; j < i; j++)
    {
      int k = arrayOfByte[j];
      localStringBuilder.append(Integer.toHexString(0xF & k >> 4));
      localStringBuilder.append(Integer.toHexString(0xF & k >> 0));
    }
    return localStringBuilder.toString();
  }
  
  private static String hashWithAlgorithm(String paramString1, String paramString2)
  {
    return hashWithAlgorithm(paramString1, paramString2.getBytes());
  }
  
  private static String hashWithAlgorithm(String paramString, byte[] paramArrayOfByte)
  {
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance(paramString);
      return hashBytes(localMessageDigest, paramArrayOfByte);
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException) {}
    return null;
  }
  
  public static Object invokeMethodQuietly(Object paramObject, Method paramMethod, Object... paramVarArgs)
  {
    try
    {
      Object localObject = paramMethod.invoke(paramObject, paramVarArgs);
      return localObject;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      return null;
    }
    catch (InvocationTargetException localInvocationTargetException) {}
    return null;
  }
  
  public static boolean isNullOrEmpty(String paramString)
  {
    return (paramString == null) || (paramString.length() == 0);
  }
  
  public static <T> boolean isNullOrEmpty(Collection<T> paramCollection)
  {
    return (paramCollection == null) || (paramCollection.size() == 0);
  }
  
  public static <T> boolean isSubset(Collection<T> paramCollection1, Collection<T> paramCollection2)
  {
    if ((paramCollection2 == null) || (paramCollection2.size() == 0))
    {
      boolean bool;
      if (paramCollection1 != null)
      {
        int i = paramCollection1.size();
        bool = false;
        if (i != 0) {}
      }
      else
      {
        bool = true;
      }
      return bool;
    }
    HashSet localHashSet = new HashSet(paramCollection2);
    Iterator localIterator = paramCollection1.iterator();
    while (localIterator.hasNext()) {
      if (!localHashSet.contains(localIterator.next())) {
        return false;
      }
    }
    return true;
  }
  
  public static void logd(String paramString, Exception paramException)
  {
    if ((Settings.isLoggingEnabled()) && (paramString != null) && (paramException != null)) {
      Log.d(paramString, paramException.getClass().getSimpleName() + ": " + paramException.getMessage());
    }
  }
  
  public static void logd(String paramString1, String paramString2)
  {
    if ((Settings.isLoggingEnabled()) && (paramString1 != null) && (paramString2 != null)) {
      Log.d(paramString1, paramString2);
    }
  }
  
  static String md5hash(String paramString)
  {
    return hashWithAlgorithm("MD5", paramString);
  }
  
  public static void putObjectInBundle(Bundle paramBundle, String paramString, Object paramObject)
  {
    if ((paramObject instanceof String))
    {
      paramBundle.putString(paramString, (String)paramObject);
      return;
    }
    if ((paramObject instanceof Parcelable))
    {
      paramBundle.putParcelable(paramString, (Parcelable)paramObject);
      return;
    }
    if ((paramObject instanceof byte[]))
    {
      paramBundle.putByteArray(paramString, (byte[])paramObject);
      return;
    }
    throw new FacebookException("attempted to add unsupported type to Bundle");
  }
  
  public static FetchedAppSettings queryAppSettings(String paramString, boolean paramBoolean)
  {
    if ((!paramBoolean) && (fetchedAppSettings.containsKey(paramString))) {
      return (FetchedAppSettings)fetchedAppSettings.get(paramString);
    }
    Bundle localBundle = new Bundle();
    localBundle.putString("fields", TextUtils.join(",", APP_SETTING_FIELDS));
    Request localRequest = Request.newGraphPathRequest(null, paramString, null);
    localRequest.setParameters(localBundle);
    GraphObject localGraphObject = localRequest.executeAndWait().getGraphObject();
    FetchedAppSettings localFetchedAppSettings = new FetchedAppSettings(safeGetBooleanFromResponse(localGraphObject, "supports_attribution"), safeGetBooleanFromResponse(localGraphObject, "supports_implicit_sdk_logging"), safeGetStringFromResponse(localGraphObject, "gdpv4_nux_content"), safeGetBooleanFromResponse(localGraphObject, "gdpv4_nux_enabled"), null);
    fetchedAppSettings.put(paramString, localFetchedAppSettings);
    return localFetchedAppSettings;
  }
  
  /* Error */
  public static String readStreamToString(java.io.InputStream paramInputStream)
    throws IOException
  {
    // Byte code:
    //   0: new 515	java/io/BufferedInputStream
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 518	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   8: astore_1
    //   9: new 520	java/io/InputStreamReader
    //   12: dup
    //   13: aload_1
    //   14: invokespecial 521	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   17: astore_2
    //   18: new 163	java/lang/StringBuilder
    //   21: dup
    //   22: invokespecial 164	java/lang/StringBuilder:<init>	()V
    //   25: astore_3
    //   26: sipush 2048
    //   29: newarray char
    //   31: astore 7
    //   33: aload_2
    //   34: aload 7
    //   36: invokevirtual 525	java/io/InputStreamReader:read	([C)I
    //   39: istore 8
    //   41: iload 8
    //   43: iconst_m1
    //   44: if_icmpeq +37 -> 81
    //   47: aload_3
    //   48: aload 7
    //   50: iconst_0
    //   51: iload 8
    //   53: invokevirtual 528	java/lang/StringBuilder:append	([CII)Ljava/lang/StringBuilder;
    //   56: pop
    //   57: goto -24 -> 33
    //   60: astore 4
    //   62: aload_2
    //   63: astore 5
    //   65: aload_1
    //   66: astore 6
    //   68: aload 6
    //   70: invokestatic 530	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   73: aload 5
    //   75: invokestatic 530	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   78: aload 4
    //   80: athrow
    //   81: aload_3
    //   82: invokevirtual 177	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   85: astore 10
    //   87: aload_1
    //   88: invokestatic 530	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   91: aload_2
    //   92: invokestatic 530	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   95: aload 10
    //   97: areturn
    //   98: astore 4
    //   100: aconst_null
    //   101: astore 6
    //   103: aconst_null
    //   104: astore 5
    //   106: goto -38 -> 68
    //   109: astore 4
    //   111: aload_1
    //   112: astore 6
    //   114: aconst_null
    //   115: astore 5
    //   117: goto -49 -> 68
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	120	0	paramInputStream	java.io.InputStream
    //   8	104	1	localBufferedInputStream1	java.io.BufferedInputStream
    //   17	75	2	localInputStreamReader1	java.io.InputStreamReader
    //   25	57	3	localStringBuilder	StringBuilder
    //   60	19	4	localObject1	Object
    //   98	1	4	localObject2	Object
    //   109	1	4	localObject3	Object
    //   63	53	5	localInputStreamReader2	java.io.InputStreamReader
    //   66	47	6	localBufferedInputStream2	java.io.BufferedInputStream
    //   31	18	7	arrayOfChar	char[]
    //   39	13	8	i	int
    //   85	11	10	str	String
    // Exception table:
    //   from	to	target	type
    //   18	33	60	finally
    //   33	41	60	finally
    //   47	57	60	finally
    //   81	87	60	finally
    //   0	9	98	finally
    //   9	18	109	finally
  }
  
  private static boolean safeGetBooleanFromResponse(GraphObject paramGraphObject, String paramString)
  {
    Object localObject1 = Boolean.valueOf(false);
    if (paramGraphObject != null) {
      localObject1 = paramGraphObject.getProperty(paramString);
    }
    if (!(localObject1 instanceof Boolean)) {}
    for (Object localObject2 = Boolean.valueOf(false);; localObject2 = localObject1) {
      return ((Boolean)localObject2).booleanValue();
    }
  }
  
  private static String safeGetStringFromResponse(GraphObject paramGraphObject, String paramString)
  {
    Object localObject1 = "";
    if (paramGraphObject != null) {
      localObject1 = paramGraphObject.getProperty(paramString);
    }
    if (!(localObject1 instanceof String)) {}
    for (Object localObject2 = "";; localObject2 = localObject1) {
      return (String)localObject2;
    }
  }
  
  public static void setAppEventAttributionParameters(GraphObject paramGraphObject, AttributionIdentifiers paramAttributionIdentifiers, String paramString, boolean paramBoolean)
  {
    boolean bool1 = true;
    if ((paramAttributionIdentifiers != null) && (paramAttributionIdentifiers.getAttributionId() != null)) {
      paramGraphObject.setProperty("attribution", paramAttributionIdentifiers.getAttributionId());
    }
    boolean bool2;
    if ((paramAttributionIdentifiers != null) && (paramAttributionIdentifiers.getAndroidAdvertiserId() != null))
    {
      paramGraphObject.setProperty("advertiser_id", paramAttributionIdentifiers.getAndroidAdvertiserId());
      if (!paramAttributionIdentifiers.isTrackingLimited())
      {
        bool2 = bool1;
        paramGraphObject.setProperty("advertiser_tracking_enabled", Boolean.valueOf(bool2));
        label76:
        if (paramBoolean) {
          break label118;
        }
      }
    }
    for (;;)
    {
      paramGraphObject.setProperty("application_tracking_enabled", Boolean.valueOf(bool1));
      return;
      bool2 = false;
      break;
      if (paramString == null) {
        break label76;
      }
      paramGraphObject.setProperty("advertiser_id", paramString);
      break label76;
      label118:
      bool1 = false;
    }
  }
  
  public static void setAppEventExtendedDeviceInfoParameters(GraphObject paramGraphObject, Context paramContext)
  {
    JSONArray localJSONArray = new JSONArray();
    localJSONArray.put("a1");
    String str1 = paramContext.getPackageName();
    int i = -1;
    String str2 = "";
    try
    {
      PackageInfo localPackageInfo = paramContext.getPackageManager().getPackageInfo(str1, 0);
      i = localPackageInfo.versionCode;
      str2 = localPackageInfo.versionName;
      label55:
      localJSONArray.put(str1);
      localJSONArray.put(i);
      localJSONArray.put(str2);
      paramGraphObject.setProperty("extinfo", localJSONArray.toString());
      return;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      break label55;
    }
  }
  
  static String sha1hash(String paramString)
  {
    return hashWithAlgorithm("SHA-1", paramString);
  }
  
  static String sha1hash(byte[] paramArrayOfByte)
  {
    return hashWithAlgorithm("SHA-1", paramArrayOfByte);
  }
  
  public static boolean stringsEqualOrEmpty(String paramString1, String paramString2)
  {
    boolean bool1 = TextUtils.isEmpty(paramString1);
    boolean bool2 = TextUtils.isEmpty(paramString2);
    if ((bool1) && (bool2)) {
      return true;
    }
    if ((!bool1) && (!bool2)) {
      return paramString1.equals(paramString2);
    }
    return false;
  }
  
  public static <T> Collection<T> unmodifiableCollection(T... paramVarArgs)
  {
    return Collections.unmodifiableCollection(Arrays.asList(paramVarArgs));
  }
  
  public static class FetchedAppSettings
  {
    private String nuxContent;
    private boolean nuxEnabled;
    private boolean supportsAttribution;
    private boolean supportsImplicitLogging;
    
    private FetchedAppSettings(boolean paramBoolean1, boolean paramBoolean2, String paramString, boolean paramBoolean3)
    {
      this.supportsAttribution = paramBoolean1;
      this.supportsImplicitLogging = paramBoolean2;
      this.nuxContent = paramString;
      this.nuxEnabled = paramBoolean3;
    }
    
    public String getNuxContent()
    {
      return this.nuxContent;
    }
    
    public boolean getNuxEnabled()
    {
      return this.nuxEnabled;
    }
    
    public boolean supportsAttribution()
    {
      return this.supportsAttribution;
    }
    
    public boolean supportsImplicitLogging()
    {
      return this.supportsImplicitLogging;
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.facebook.internal.Utility
 * JD-Core Version:    0.7.0.1
 */