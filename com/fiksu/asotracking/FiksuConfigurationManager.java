package com.fiksu.asotracking;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

final class FiksuConfigurationManager
{
  private static final FiksuConfigurationManager INSTANCE = new FiksuConfigurationManager();
  private final FiksuConfiguration mFiksuConfiguration = new FiksuConfiguration();
  private final AtomicBoolean mIsUpdatingConfiguration = new AtomicBoolean(false);
  private SharedPreferences mSharedPreferences = null;
  
  private static HttpClient getHttpClient(Context paramContext)
  {
    try
    {
      DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient();
      return localDefaultHttpClient;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      Log.e("FiksuTracking", "Caught IllegalArgumentException while creating http client: " + localIllegalArgumentException.toString());
    }
    return null;
  }
  
  static FiksuConfigurationManager getInstance()
  {
    return INSTANCE;
  }
  
  private static String getSynchUrl(Context paramContext)
  {
    return "https://sdk.fiksu.com/config/FiksuConfiguration_android_" + paramContext.getPackageName() + "_" + "50015" + ".json";
  }
  
  /* Error */
  private static String readContentsFromServer(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 96	com/fiksu/asotracking/FiksuConfigurationManager:getHttpClient	(Landroid/content/Context;)Lorg/apache/http/client/HttpClient;
    //   4: astore 4
    //   6: aload 4
    //   8: ifnonnull +5 -> 13
    //   11: aconst_null
    //   12: areturn
    //   13: aload 4
    //   15: new 98	org/apache/http/client/methods/HttpGet
    //   18: dup
    //   19: aload_1
    //   20: invokespecial 99	org/apache/http/client/methods/HttpGet:<init>	(Ljava/lang/String;)V
    //   23: invokeinterface 105 2 0
    //   28: astore 5
    //   30: aload 5
    //   32: invokeinterface 111 1 0
    //   37: invokeinterface 117 1 0
    //   42: istore 6
    //   44: ldc 51
    //   46: new 53	java/lang/StringBuilder
    //   49: dup
    //   50: ldc 119
    //   52: invokespecial 58	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   55: aload_1
    //   56: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   59: ldc 121
    //   61: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   64: iload 6
    //   66: invokevirtual 124	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   69: invokevirtual 67	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   72: invokestatic 127	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   75: pop
    //   76: iload 6
    //   78: sipush 404
    //   81: if_icmpne +6 -> 87
    //   84: ldc 129
    //   86: areturn
    //   87: aload 5
    //   89: invokeinterface 133 1 0
    //   94: invokeinterface 139 1 0
    //   99: astore 8
    //   101: new 141	java/io/BufferedReader
    //   104: dup
    //   105: new 143	java/io/InputStreamReader
    //   108: dup
    //   109: aload 8
    //   111: invokespecial 146	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   114: invokespecial 149	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   117: astore 9
    //   119: new 53	java/lang/StringBuilder
    //   122: dup
    //   123: invokespecial 150	java/lang/StringBuilder:<init>	()V
    //   126: astore 10
    //   128: aload 9
    //   130: invokevirtual 153	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   133: astore 16
    //   135: aload 16
    //   137: ifnonnull +65 -> 202
    //   140: aload 10
    //   142: invokevirtual 67	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   145: astore 17
    //   147: aload 8
    //   149: invokevirtual 158	java/io/InputStream:close	()V
    //   152: aload 9
    //   154: invokevirtual 159	java/io/BufferedReader:close	()V
    //   157: aload 17
    //   159: areturn
    //   160: astore 18
    //   162: aload 17
    //   164: areturn
    //   165: astore_2
    //   166: ldc 51
    //   168: new 53	java/lang/StringBuilder
    //   171: dup
    //   172: ldc 161
    //   174: invokespecial 58	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   177: aload_1
    //   178: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   181: ldc 163
    //   183: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   186: aload_2
    //   187: invokevirtual 164	java/io/IOException:toString	()Ljava/lang/String;
    //   190: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   193: invokevirtual 67	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   196: invokestatic 73	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   199: pop
    //   200: aconst_null
    //   201: areturn
    //   202: aload 10
    //   204: new 53	java/lang/StringBuilder
    //   207: dup
    //   208: aload 16
    //   210: invokestatic 170	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   213: invokespecial 58	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   216: ldc 172
    //   218: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   221: invokevirtual 67	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   224: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   227: pop
    //   228: goto -100 -> 128
    //   231: astore 13
    //   233: ldc 51
    //   235: new 53	java/lang/StringBuilder
    //   238: dup
    //   239: ldc 174
    //   241: invokespecial 58	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   244: aload_1
    //   245: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   248: ldc 163
    //   250: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   253: aload 13
    //   255: invokevirtual 164	java/io/IOException:toString	()Ljava/lang/String;
    //   258: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   261: invokevirtual 67	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   264: invokestatic 73	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   267: pop
    //   268: aload 8
    //   270: invokevirtual 158	java/io/InputStream:close	()V
    //   273: aload 9
    //   275: invokevirtual 159	java/io/BufferedReader:close	()V
    //   278: aconst_null
    //   279: areturn
    //   280: astore 15
    //   282: aconst_null
    //   283: areturn
    //   284: astore 11
    //   286: aload 8
    //   288: invokevirtual 158	java/io/InputStream:close	()V
    //   291: aload 9
    //   293: invokevirtual 159	java/io/BufferedReader:close	()V
    //   296: aload 11
    //   298: athrow
    //   299: astore 12
    //   301: goto -5 -> 296
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	304	0	paramContext	Context
    //   0	304	1	paramString	String
    //   165	22	2	localIOException1	java.io.IOException
    //   4	10	4	localHttpClient	HttpClient
    //   28	60	5	localHttpResponse	org.apache.http.HttpResponse
    //   42	40	6	i	int
    //   99	188	8	localInputStream	java.io.InputStream
    //   117	175	9	localBufferedReader	java.io.BufferedReader
    //   126	77	10	localStringBuilder	java.lang.StringBuilder
    //   284	13	11	localObject	Object
    //   299	1	12	localIOException2	java.io.IOException
    //   231	23	13	localIOException3	java.io.IOException
    //   280	1	15	localIOException4	java.io.IOException
    //   133	76	16	str1	String
    //   145	18	17	str2	String
    //   160	1	18	localIOException5	java.io.IOException
    // Exception table:
    //   from	to	target	type
    //   147	157	160	java/io/IOException
    //   0	6	165	java/io/IOException
    //   13	76	165	java/io/IOException
    //   87	101	165	java/io/IOException
    //   128	135	231	java/io/IOException
    //   140	147	231	java/io/IOException
    //   202	228	231	java/io/IOException
    //   268	278	280	java/io/IOException
    //   128	135	284	finally
    //   140	147	284	finally
    //   202	228	284	finally
    //   233	268	284	finally
    //   286	296	299	java/io/IOException
  }
  
  private void setSharedPreferences(Context paramContext)
  {
    if (this.mSharedPreferences != null) {
      return;
    }
    if (paramContext == null)
    {
      Log.e("FiksuTracking", "Context is null so we cannot load configuration from SharedPreferences");
      return;
    }
    this.mSharedPreferences = paramContext.getSharedPreferences("FiksuConfigurationSharedPreferences", 0);
  }
  
  private boolean updateConfigurationFromServerBlocking(Context paramContext, String paramString)
  {
    String str = readContentsFromServer(paramContext, paramString);
    if (str == null) {
      return false;
    }
    if (str.length() == 0)
    {
      this.mFiksuConfiguration.updateLastUpdateTimestamp();
      this.mFiksuConfiguration.writeToSharedPreferences(this.mSharedPreferences);
      return true;
    }
    try
    {
      this.mFiksuConfiguration.readFromJSONObject(new JSONObject(str));
      this.mFiksuConfiguration.writeToSharedPreferences(this.mSharedPreferences);
      return true;
    }
    catch (JSONException localJSONException)
    {
      Log.e("FiksuTracking", "Caught JSONException parsing data from [" + str + "]: " + localJSONException.toString());
    }
    return false;
  }
  
  FiksuConfiguration getFiksuConfiguration()
  {
    return this.mFiksuConfiguration;
  }
  
  void initialize(Context paramContext)
  {
    setSharedPreferences(paramContext);
    if (this.mSharedPreferences != null) {
      this.mFiksuConfiguration.readFromSharedPreferences(this.mSharedPreferences);
    }
    if (this.mFiksuConfiguration.hasEverSynchedWithServer())
    {
      updateConfiguration(paramContext);
      return;
    }
    updateConfiguration(paramContext);
  }
  
  void setDebugModeEnabled(boolean paramBoolean)
  {
    this.mFiksuConfiguration.setDebugModeEnabled(paramBoolean);
  }
  
  void updateConfiguration(Context paramContext)
  {
    if (this.mFiksuConfiguration.isUpToDate()) {
      return;
    }
    setSharedPreferences(paramContext);
    updateConfigurationFromServerInTheBackground(paramContext, getSynchUrl(paramContext));
  }
  
  void updateConfigurationFromServerInTheBackground(final Context paramContext, final String paramString)
  {
    if (this.mIsUpdatingConfiguration.compareAndSet(false, true)) {
      new Thread(new Runnable()
      {
        public void run()
        {
          try
          {
            FiksuConfigurationManager.this.updateConfigurationFromServerBlocking(paramContext, paramString);
            return;
          }
          finally
          {
            FiksuConfigurationManager.this.mIsUpdatingConfiguration.set(false);
          }
        }
      }).start();
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.fiksu.asotracking.FiksuConfigurationManager
 * JD-Core Version:    0.7.0.1
 */