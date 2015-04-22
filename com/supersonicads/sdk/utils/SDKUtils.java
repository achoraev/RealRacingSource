package com.supersonicads.sdk.utils;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;
import com.supersonicads.sdk.precache.CacheManager;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;
import java.util.zip.GZIPInputStream;
import org.json.JSONException;
import org.json.JSONObject;

public class SDKUtils
{
  private static final String TAG = SDKUtils.class.getSimpleName();
  private static String mAdvertisingId;
  private static boolean mIsLimitedTrackingEnabled = true;
  
  public static int convertDpToPx(int paramInt)
  {
    return (int)TypedValue.applyDimension(0, paramInt, Resources.getSystem().getDisplayMetrics());
  }
  
  public static int convertPxToDp(int paramInt)
  {
    return (int)TypedValue.applyDimension(1, paramInt, Resources.getSystem().getDisplayMetrics());
  }
  
  public static String convertStreamToString(InputStream paramInputStream, boolean paramBoolean, String paramString1, String paramString2)
    throws IOException
  {
    Object localObject1 = paramInputStream;
    if (paramBoolean) {
      localObject1 = new GZIPInputStream(paramInputStream);
    }
    BufferedWriter localBufferedWriter = new BufferedWriter(new FileWriter(new File(paramString1, paramString2)));
    try
    {
      BufferedReader localBufferedReader1 = new BufferedReader(new InputStreamReader((InputStream)localObject1, "UTF-8"));
      try
      {
        StringBuilder localStringBuilder = new StringBuilder();
        for (;;)
        {
          String str1 = localBufferedReader1.readLine();
          if (str1 == null)
          {
            localBufferedWriter.write(localStringBuilder.toString());
            String str2 = localStringBuilder.toString();
            if (localBufferedReader1 != null) {
              localBufferedReader1.close();
            }
            ((InputStream)localObject1).close();
            if (paramBoolean) {
              paramInputStream.close();
            }
            localBufferedWriter.close();
            return str2;
          }
          localStringBuilder.append(str1);
          localStringBuilder.append("\n");
        }
        if (localBufferedReader2 == null) {
          break label166;
        }
      }
      finally
      {
        localBufferedReader2 = localBufferedReader1;
      }
    }
    finally
    {
      BufferedReader localBufferedReader2 = null;
    }
    localBufferedReader2.close();
    label166:
    ((InputStream)localObject1).close();
    if (paramBoolean) {
      paramInputStream.close();
    }
    localBufferedWriter.close();
    throw localObject2;
  }
  
  public static String createErrorMessage(String paramString1, String paramString2)
  {
    return String.format("%s Failure occurred during initiation at: %s", new Object[] { paramString1, paramString2 });
  }
  
  public static int dpToPx(long paramLong)
  {
    DisplayMetrics localDisplayMetrics = Resources.getSystem().getDisplayMetrics();
    return (int)(0.5F + (float)paramLong * localDisplayMetrics.density);
  }
  
  public static String encodeString(String paramString)
  {
    try
    {
      String str = URLEncoder.encode(paramString, "UTF-8").replace("+", "%20");
      return str;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException) {}
    return "";
  }
  
  public static byte[] encrypt(String paramString)
  {
    MessageDigest localMessageDigest = null;
    try
    {
      localMessageDigest = MessageDigest.getInstance("SHA-1");
      localMessageDigest.reset();
      localMessageDigest.update(paramString.getBytes("UTF-8"));
      if (localMessageDigest != null) {
        return localMessageDigest.digest();
      }
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      for (;;)
      {
        localNoSuchAlgorithmException.printStackTrace();
      }
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      for (;;)
      {
        localUnsupportedEncodingException.printStackTrace();
      }
    }
    return null;
  }
  
  public static String getAdvertiserId()
  {
    return mAdvertisingId;
  }
  
  private static String getAndroidID(Context paramContext)
  {
    return Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
  }
  
  public static int getApplicationRotation(Context paramContext)
  {
    return ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getRotation();
  }
  
  public static long getAvailableSpaceInMB(Context paramContext)
  {
    StatFs localStatFs = new StatFs(new CacheManager(paramContext).getRootDirectoryPath());
    return localStatFs.getAvailableBlocks() * localStatFs.getBlockSize() / 1048576L;
  }
  
  public static String getConnectionType(Context paramContext)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    NetworkInfo localNetworkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    String str;
    int i;
    if ((localNetworkInfo != null) && (localNetworkInfo.isConnected()))
    {
      str = localNetworkInfo.getTypeName();
      localNetworkInfo.getSubtypeName();
      i = localNetworkInfo.getType();
      if (i != 0) {
        break label66;
      }
      localStringBuilder.append("3g");
    }
    for (;;)
    {
      return localStringBuilder.toString();
      label66:
      if (i == 1) {
        localStringBuilder.append("wifi");
      } else {
        localStringBuilder.append(str);
      }
    }
  }
  
  public static Long getCurrentTimeMillis()
  {
    return Long.valueOf(System.currentTimeMillis());
  }
  
  public static int getDeviceDefaultOrientation(Context paramContext)
  {
    WindowManager localWindowManager = (WindowManager)paramContext.getSystemService("window");
    Configuration localConfiguration = paramContext.getResources().getConfiguration();
    int i = localWindowManager.getDefaultDisplay().getRotation();
    if (((i != 0) && (i != 2)) || ((localConfiguration.orientation == 2) || (((i == 1) || (i == 3)) && (localConfiguration.orientation == 1)))) {
      return 2;
    }
    return 1;
  }
  
  public static int getDeviceHeight()
  {
    return Resources.getSystem().getDisplayMetrics().heightPixels;
  }
  
  public static float getDeviceScale()
  {
    return Resources.getSystem().getDisplayMetrics().density;
  }
  
  public static int getDeviceWidth()
  {
    return Resources.getSystem().getDisplayMetrics().widthPixels;
  }
  
  public static String getFileName(String paramString)
  {
    String[] arrayOfString = paramString.split(File.separator);
    String str1 = arrayOfString[(-1 + arrayOfString.length)].split("\\?")[0];
    try
    {
      String str2 = URLEncoder.encode(str1, "UTF-8");
      return str2;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      localUnsupportedEncodingException.printStackTrace();
    }
    return null;
  }
  
  /* Error */
  public static Object getIInAppBillingServiceClass(android.os.IBinder paramIBinder)
  {
    // Byte code:
    //   0: ldc_w 326
    //   3: invokestatic 330	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   6: ldc_w 332
    //   9: iconst_1
    //   10: anewarray 13	java/lang/Class
    //   13: dup
    //   14: iconst_0
    //   15: ldc_w 334
    //   18: aastore
    //   19: invokevirtual 338	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   22: aconst_null
    //   23: iconst_1
    //   24: anewarray 4	java/lang/Object
    //   27: dup
    //   28: iconst_0
    //   29: aload_0
    //   30: aastore
    //   31: invokevirtual 344	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   34: astore 13
    //   36: aload 13
    //   38: astore_3
    //   39: iconst_0
    //   40: ifeq +95 -> 135
    //   43: aconst_null
    //   44: invokevirtual 349	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   47: ifnull +42 -> 89
    //   50: getstatic 19	com/supersonicads/sdk/utils/SDKUtils:TAG	Ljava/lang/String;
    //   53: new 81	java/lang/StringBuilder
    //   56: dup
    //   57: aconst_null
    //   58: invokevirtual 353	java/lang/Object:getClass	()Ljava/lang/Class;
    //   61: invokevirtual 17	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   64: invokestatic 356	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   67: invokespecial 357	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   70: ldc_w 359
    //   73: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   76: aconst_null
    //   77: invokevirtual 349	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   80: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   83: invokevirtual 88	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   86: invokestatic 364	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   89: aconst_null
    //   90: invokevirtual 368	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   93: ifnull +42 -> 135
    //   96: getstatic 19	com/supersonicads/sdk/utils/SDKUtils:TAG	Ljava/lang/String;
    //   99: new 81	java/lang/StringBuilder
    //   102: dup
    //   103: aconst_null
    //   104: invokevirtual 353	java/lang/Object:getClass	()Ljava/lang/Class;
    //   107: invokevirtual 17	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   110: invokestatic 356	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   113: invokespecial 357	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   116: ldc_w 359
    //   119: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   122: aconst_null
    //   123: invokevirtual 368	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   126: invokevirtual 371	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   129: invokevirtual 88	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   132: invokestatic 364	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   135: aload_3
    //   136: areturn
    //   137: astore 11
    //   139: aconst_null
    //   140: astore_3
    //   141: aload 11
    //   143: ifnull -8 -> 135
    //   146: aload 11
    //   148: invokevirtual 349	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   151: ifnull +44 -> 195
    //   154: getstatic 19	com/supersonicads/sdk/utils/SDKUtils:TAG	Ljava/lang/String;
    //   157: new 81	java/lang/StringBuilder
    //   160: dup
    //   161: aload 11
    //   163: invokevirtual 353	java/lang/Object:getClass	()Ljava/lang/Class;
    //   166: invokevirtual 17	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   169: invokestatic 356	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   172: invokespecial 357	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   175: ldc_w 359
    //   178: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   181: aload 11
    //   183: invokevirtual 349	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   186: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   189: invokevirtual 88	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   192: invokestatic 364	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   195: aload 11
    //   197: invokevirtual 368	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   200: astore 12
    //   202: aconst_null
    //   203: astore_3
    //   204: aload 12
    //   206: ifnull -71 -> 135
    //   209: getstatic 19	com/supersonicads/sdk/utils/SDKUtils:TAG	Ljava/lang/String;
    //   212: new 81	java/lang/StringBuilder
    //   215: dup
    //   216: aload 11
    //   218: invokevirtual 353	java/lang/Object:getClass	()Ljava/lang/Class;
    //   221: invokevirtual 17	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   224: invokestatic 356	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   227: invokespecial 357	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   230: ldc_w 359
    //   233: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   236: aload 11
    //   238: invokevirtual 368	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   241: invokevirtual 371	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   244: invokevirtual 88	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   247: invokestatic 364	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   250: aconst_null
    //   251: areturn
    //   252: astore 9
    //   254: aconst_null
    //   255: astore_3
    //   256: aload 9
    //   258: ifnull -123 -> 135
    //   261: aload 9
    //   263: invokevirtual 349	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   266: ifnull +44 -> 310
    //   269: getstatic 19	com/supersonicads/sdk/utils/SDKUtils:TAG	Ljava/lang/String;
    //   272: new 81	java/lang/StringBuilder
    //   275: dup
    //   276: aload 9
    //   278: invokevirtual 353	java/lang/Object:getClass	()Ljava/lang/Class;
    //   281: invokevirtual 17	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   284: invokestatic 356	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   287: invokespecial 357	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   290: ldc_w 359
    //   293: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   296: aload 9
    //   298: invokevirtual 349	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   301: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   304: invokevirtual 88	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   307: invokestatic 364	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   310: aload 9
    //   312: invokevirtual 368	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   315: astore 10
    //   317: aconst_null
    //   318: astore_3
    //   319: aload 10
    //   321: ifnull -186 -> 135
    //   324: getstatic 19	com/supersonicads/sdk/utils/SDKUtils:TAG	Ljava/lang/String;
    //   327: new 81	java/lang/StringBuilder
    //   330: dup
    //   331: aload 9
    //   333: invokevirtual 353	java/lang/Object:getClass	()Ljava/lang/Class;
    //   336: invokevirtual 17	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   339: invokestatic 356	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   342: invokespecial 357	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   345: ldc_w 359
    //   348: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   351: aload 9
    //   353: invokevirtual 368	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   356: invokevirtual 371	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   359: invokevirtual 88	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   362: invokestatic 364	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   365: aconst_null
    //   366: areturn
    //   367: astore 7
    //   369: aconst_null
    //   370: astore_3
    //   371: aload 7
    //   373: ifnull -238 -> 135
    //   376: aload 7
    //   378: invokevirtual 349	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   381: ifnull +44 -> 425
    //   384: getstatic 19	com/supersonicads/sdk/utils/SDKUtils:TAG	Ljava/lang/String;
    //   387: new 81	java/lang/StringBuilder
    //   390: dup
    //   391: aload 7
    //   393: invokevirtual 353	java/lang/Object:getClass	()Ljava/lang/Class;
    //   396: invokevirtual 17	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   399: invokestatic 356	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   402: invokespecial 357	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   405: ldc_w 359
    //   408: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   411: aload 7
    //   413: invokevirtual 349	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   416: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   419: invokevirtual 88	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   422: invokestatic 364	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   425: aload 7
    //   427: invokevirtual 368	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   430: astore 8
    //   432: aconst_null
    //   433: astore_3
    //   434: aload 8
    //   436: ifnull -301 -> 135
    //   439: getstatic 19	com/supersonicads/sdk/utils/SDKUtils:TAG	Ljava/lang/String;
    //   442: new 81	java/lang/StringBuilder
    //   445: dup
    //   446: aload 7
    //   448: invokevirtual 353	java/lang/Object:getClass	()Ljava/lang/Class;
    //   451: invokevirtual 17	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   454: invokestatic 356	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   457: invokespecial 357	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   460: ldc_w 359
    //   463: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   466: aload 7
    //   468: invokevirtual 368	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   471: invokevirtual 371	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   474: invokevirtual 88	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   477: invokestatic 364	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   480: aconst_null
    //   481: areturn
    //   482: astore 5
    //   484: aconst_null
    //   485: astore_3
    //   486: aload 5
    //   488: ifnull -353 -> 135
    //   491: aload 5
    //   493: invokevirtual 349	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   496: ifnull +44 -> 540
    //   499: getstatic 19	com/supersonicads/sdk/utils/SDKUtils:TAG	Ljava/lang/String;
    //   502: new 81	java/lang/StringBuilder
    //   505: dup
    //   506: aload 5
    //   508: invokevirtual 353	java/lang/Object:getClass	()Ljava/lang/Class;
    //   511: invokevirtual 17	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   514: invokestatic 356	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   517: invokespecial 357	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   520: ldc_w 359
    //   523: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   526: aload 5
    //   528: invokevirtual 349	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   531: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   534: invokevirtual 88	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   537: invokestatic 364	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   540: aload 5
    //   542: invokevirtual 368	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   545: astore 6
    //   547: aconst_null
    //   548: astore_3
    //   549: aload 6
    //   551: ifnull -416 -> 135
    //   554: getstatic 19	com/supersonicads/sdk/utils/SDKUtils:TAG	Ljava/lang/String;
    //   557: new 81	java/lang/StringBuilder
    //   560: dup
    //   561: aload 5
    //   563: invokevirtual 353	java/lang/Object:getClass	()Ljava/lang/Class;
    //   566: invokevirtual 17	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   569: invokestatic 356	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   572: invokespecial 357	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   575: ldc_w 359
    //   578: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   581: aload 5
    //   583: invokevirtual 368	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   586: invokevirtual 371	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   589: invokevirtual 88	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   592: invokestatic 364	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   595: aconst_null
    //   596: areturn
    //   597: astore_2
    //   598: aconst_null
    //   599: astore_3
    //   600: aload_2
    //   601: ifnull -466 -> 135
    //   604: aload_2
    //   605: invokevirtual 349	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   608: ifnull +42 -> 650
    //   611: getstatic 19	com/supersonicads/sdk/utils/SDKUtils:TAG	Ljava/lang/String;
    //   614: new 81	java/lang/StringBuilder
    //   617: dup
    //   618: aload_2
    //   619: invokevirtual 353	java/lang/Object:getClass	()Ljava/lang/Class;
    //   622: invokevirtual 17	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   625: invokestatic 356	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   628: invokespecial 357	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   631: ldc_w 359
    //   634: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   637: aload_2
    //   638: invokevirtual 349	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   641: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   644: invokevirtual 88	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   647: invokestatic 364	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   650: aload_2
    //   651: invokevirtual 368	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   654: astore 4
    //   656: aconst_null
    //   657: astore_3
    //   658: aload 4
    //   660: ifnull -525 -> 135
    //   663: getstatic 19	com/supersonicads/sdk/utils/SDKUtils:TAG	Ljava/lang/String;
    //   666: new 81	java/lang/StringBuilder
    //   669: dup
    //   670: aload_2
    //   671: invokevirtual 353	java/lang/Object:getClass	()Ljava/lang/Class;
    //   674: invokevirtual 17	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   677: invokestatic 356	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   680: invokespecial 357	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   683: ldc_w 359
    //   686: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   689: aload_2
    //   690: invokevirtual 368	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   693: invokevirtual 371	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   696: invokevirtual 88	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   699: invokestatic 364	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   702: aconst_null
    //   703: areturn
    //   704: astore_1
    //   705: iconst_0
    //   706: ifeq +95 -> 801
    //   709: aconst_null
    //   710: invokevirtual 349	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   713: ifnull +42 -> 755
    //   716: getstatic 19	com/supersonicads/sdk/utils/SDKUtils:TAG	Ljava/lang/String;
    //   719: new 81	java/lang/StringBuilder
    //   722: dup
    //   723: aconst_null
    //   724: invokevirtual 353	java/lang/Object:getClass	()Ljava/lang/Class;
    //   727: invokevirtual 17	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   730: invokestatic 356	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   733: invokespecial 357	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   736: ldc_w 359
    //   739: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   742: aconst_null
    //   743: invokevirtual 349	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   746: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   749: invokevirtual 88	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   752: invokestatic 364	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   755: aconst_null
    //   756: invokevirtual 368	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   759: ifnull +42 -> 801
    //   762: getstatic 19	com/supersonicads/sdk/utils/SDKUtils:TAG	Ljava/lang/String;
    //   765: new 81	java/lang/StringBuilder
    //   768: dup
    //   769: aconst_null
    //   770: invokevirtual 353	java/lang/Object:getClass	()Ljava/lang/Class;
    //   773: invokevirtual 17	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   776: invokestatic 356	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   779: invokespecial 357	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   782: ldc_w 359
    //   785: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   788: aconst_null
    //   789: invokevirtual 368	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   792: invokevirtual 371	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   795: invokevirtual 88	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   798: invokestatic 364	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   801: aload_1
    //   802: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	803	0	paramIBinder	android.os.IBinder
    //   704	98	1	localObject1	Object
    //   597	93	2	localInvocationTargetException	java.lang.reflect.InvocationTargetException
    //   38	620	3	localObject2	Object
    //   654	5	4	localThrowable1	java.lang.Throwable
    //   482	100	5	localIllegalArgumentException	java.lang.IllegalArgumentException
    //   545	5	6	localThrowable2	java.lang.Throwable
    //   367	100	7	localIllegalAccessException	java.lang.IllegalAccessException
    //   430	5	8	localThrowable3	java.lang.Throwable
    //   252	100	9	localNoSuchMethodException	java.lang.NoSuchMethodException
    //   315	5	10	localThrowable4	java.lang.Throwable
    //   137	100	11	localClassNotFoundException	java.lang.ClassNotFoundException
    //   200	5	12	localThrowable5	java.lang.Throwable
    //   34	3	13	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   0	36	137	java/lang/ClassNotFoundException
    //   0	36	252	java/lang/NoSuchMethodException
    //   0	36	367	java/lang/IllegalAccessException
    //   0	36	482	java/lang/IllegalArgumentException
    //   0	36	597	java/lang/reflect/InvocationTargetException
    //   0	36	704	finally
  }
  
  /* Error */
  public static String getMD5(String paramString)
  {
    // Byte code:
    //   0: new 374	java/math/BigInteger
    //   3: dup
    //   4: iconst_1
    //   5: ldc_w 376
    //   8: invokestatic 157	java/security/MessageDigest:getInstance	(Ljava/lang/String;)Ljava/security/MessageDigest;
    //   11: aload_0
    //   12: invokevirtual 378	java/lang/String:getBytes	()[B
    //   15: invokevirtual 381	java/security/MessageDigest:digest	([B)[B
    //   18: invokespecial 384	java/math/BigInteger:<init>	(I[B)V
    //   21: bipush 16
    //   23: invokevirtual 387	java/math/BigInteger:toString	(I)Ljava/lang/String;
    //   26: astore_2
    //   27: aload_2
    //   28: invokevirtual 390	java/lang/String:length	()I
    //   31: bipush 32
    //   33: if_icmplt +5 -> 38
    //   36: aload_2
    //   37: areturn
    //   38: new 81	java/lang/StringBuilder
    //   41: dup
    //   42: ldc_w 392
    //   45: invokespecial 357	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   48: aload_2
    //   49: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   52: invokevirtual 88	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   55: astore_3
    //   56: aload_3
    //   57: astore_2
    //   58: goto -31 -> 27
    //   61: astore_1
    //   62: new 394	java/lang/RuntimeException
    //   65: dup
    //   66: aload_1
    //   67: invokespecial 397	java/lang/RuntimeException:<init>	(Ljava/lang/Throwable;)V
    //   70: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	71	0	paramString	String
    //   61	6	1	localNoSuchAlgorithmException	NoSuchAlgorithmException
    //   26	32	2	localObject	Object
    //   55	2	3	str	String
    // Exception table:
    //   from	to	target	type
    //   0	27	61	java/security/NoSuchAlgorithmException
    //   27	36	61	java/security/NoSuchAlgorithmException
    //   38	56	61	java/security/NoSuchAlgorithmException
  }
  
  public static JSONObject getOrientation(Context paramContext)
  {
    int i = paramContext.getResources().getConfiguration().orientation;
    String str = "portrait";
    if (i == 2) {
      str = "landscape";
    }
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("orientation", str);
      return localJSONObject;
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
    return localJSONObject;
  }
  
  public static JSONObject getOrientationNew(Context paramContext)
  {
    int i = getDeviceDefaultOrientation(paramContext);
    int j = getApplicationRotation(paramContext);
    String str = "portrait";
    int k;
    if ((j != 1) && (j != 3))
    {
      k = 0;
      if (k == 0) {
        break label69;
      }
      if (i == 1) {
        str = "landscape";
      }
    }
    JSONObject localJSONObject;
    for (;;)
    {
      localJSONObject = new JSONObject();
      try
      {
        localJSONObject.put("orientation", str);
        return localJSONObject;
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
      }
      k = 1;
      break;
      label69:
      if (i == 2) {
        str = "landscape";
      }
    }
    return localJSONObject;
  }
  
  public static String getPackageName(Context paramContext)
  {
    return paramContext.getPackageName();
  }
  
  public static int getRotation(Context paramContext)
  {
    return ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getRotation();
  }
  
  public static boolean isApplicationVisible(Context paramContext)
  {
    String str = paramContext.getPackageName();
    Iterator localIterator = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses().iterator();
    ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo;
    do
    {
      if (!localIterator.hasNext()) {
        return false;
      }
      localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)localIterator.next();
    } while ((!localRunningAppProcessInfo.processName.equalsIgnoreCase(str)) || (localRunningAppProcessInfo.importance != 100));
    return true;
  }
  
  public static boolean isConnectingToInternet(Context paramContext)
  {
    ConnectivityManager localConnectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
    NetworkInfo[] arrayOfNetworkInfo;
    if (localConnectivityManager != null)
    {
      arrayOfNetworkInfo = localConnectivityManager.getAllNetworkInfo();
      if (arrayOfNetworkInfo == null) {}
    }
    for (int i = 0;; i++)
    {
      if (i >= arrayOfNetworkInfo.length) {
        return false;
      }
      if (arrayOfNetworkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
        return true;
      }
    }
  }
  
  public static boolean isLimitAdTrackingEnabled()
  {
    return mIsLimitedTrackingEnabled;
  }
  
  public static boolean isOnline(Context paramContext)
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    return (localNetworkInfo != null) && (localNetworkInfo.isConnected());
  }
  
  public static boolean isPermissionGranted(Context paramContext, String paramString)
  {
    return paramContext.checkCallingOrSelfPermission(paramString) == 0;
  }
  
  /* Error */
  public static void loadGoogleAdvertiserInfo(Context paramContext)
  {
    // Byte code:
    //   0: ldc_w 485
    //   3: invokestatic 330	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   6: ldc_w 487
    //   9: iconst_1
    //   10: anewarray 13	java/lang/Class
    //   13: dup
    //   14: iconst_0
    //   15: ldc 182
    //   17: aastore
    //   18: invokevirtual 338	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   21: aconst_null
    //   22: iconst_1
    //   23: anewarray 4	java/lang/Object
    //   26: dup
    //   27: iconst_0
    //   28: aload_0
    //   29: aastore
    //   30: invokevirtual 344	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   33: astore 7
    //   35: aload 7
    //   37: invokevirtual 353	java/lang/Object:getClass	()Ljava/lang/Class;
    //   40: ldc_w 489
    //   43: iconst_0
    //   44: anewarray 13	java/lang/Class
    //   47: invokevirtual 338	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   50: astore 8
    //   52: aload 7
    //   54: invokevirtual 353	java/lang/Object:getClass	()Ljava/lang/Class;
    //   57: ldc_w 490
    //   60: iconst_0
    //   61: anewarray 13	java/lang/Class
    //   64: invokevirtual 338	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   67: astore 9
    //   69: aload 8
    //   71: aload 7
    //   73: iconst_0
    //   74: anewarray 4	java/lang/Object
    //   77: invokevirtual 344	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   80: invokevirtual 491	java/lang/Object:toString	()Ljava/lang/String;
    //   83: putstatic 178	com/supersonicads/sdk/utils/SDKUtils:mAdvertisingId	Ljava/lang/String;
    //   86: aload 9
    //   88: aload 7
    //   90: iconst_0
    //   91: anewarray 4	java/lang/Object
    //   94: invokevirtual 344	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   97: checkcast 493	java/lang/Boolean
    //   100: invokevirtual 496	java/lang/Boolean:booleanValue	()Z
    //   103: putstatic 21	com/supersonicads/sdk/utils/SDKUtils:mIsLimitedTrackingEnabled	Z
    //   106: iconst_0
    //   107: ifeq +95 -> 202
    //   110: aconst_null
    //   111: invokevirtual 349	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   114: ifnull +42 -> 156
    //   117: getstatic 19	com/supersonicads/sdk/utils/SDKUtils:TAG	Ljava/lang/String;
    //   120: new 81	java/lang/StringBuilder
    //   123: dup
    //   124: aconst_null
    //   125: invokevirtual 353	java/lang/Object:getClass	()Ljava/lang/Class;
    //   128: invokevirtual 17	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   131: invokestatic 356	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   134: invokespecial 357	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   137: ldc_w 359
    //   140: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   143: aconst_null
    //   144: invokevirtual 349	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   147: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   150: invokevirtual 88	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   153: invokestatic 364	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   156: aconst_null
    //   157: invokevirtual 368	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   160: ifnull +42 -> 202
    //   163: getstatic 19	com/supersonicads/sdk/utils/SDKUtils:TAG	Ljava/lang/String;
    //   166: new 81	java/lang/StringBuilder
    //   169: dup
    //   170: aconst_null
    //   171: invokevirtual 353	java/lang/Object:getClass	()Ljava/lang/Class;
    //   174: invokevirtual 17	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   177: invokestatic 356	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   180: invokespecial 357	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   183: ldc_w 359
    //   186: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   189: aconst_null
    //   190: invokevirtual 368	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   193: invokevirtual 371	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   196: invokevirtual 88	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   199: invokestatic 364	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   202: return
    //   203: astore 6
    //   205: aload 6
    //   207: ifnull -5 -> 202
    //   210: aload 6
    //   212: invokevirtual 349	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   215: ifnull +44 -> 259
    //   218: getstatic 19	com/supersonicads/sdk/utils/SDKUtils:TAG	Ljava/lang/String;
    //   221: new 81	java/lang/StringBuilder
    //   224: dup
    //   225: aload 6
    //   227: invokevirtual 353	java/lang/Object:getClass	()Ljava/lang/Class;
    //   230: invokevirtual 17	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   233: invokestatic 356	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   236: invokespecial 357	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   239: ldc_w 359
    //   242: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   245: aload 6
    //   247: invokevirtual 349	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   250: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   253: invokevirtual 88	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   256: invokestatic 364	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   259: aload 6
    //   261: invokevirtual 368	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   264: ifnull -62 -> 202
    //   267: getstatic 19	com/supersonicads/sdk/utils/SDKUtils:TAG	Ljava/lang/String;
    //   270: new 81	java/lang/StringBuilder
    //   273: dup
    //   274: aload 6
    //   276: invokevirtual 353	java/lang/Object:getClass	()Ljava/lang/Class;
    //   279: invokevirtual 17	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   282: invokestatic 356	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   285: invokespecial 357	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   288: ldc_w 359
    //   291: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   294: aload 6
    //   296: invokevirtual 368	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   299: invokevirtual 371	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   302: invokevirtual 88	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   305: invokestatic 364	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   308: return
    //   309: astore 5
    //   311: aload 5
    //   313: ifnull -111 -> 202
    //   316: aload 5
    //   318: invokevirtual 349	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   321: ifnull +44 -> 365
    //   324: getstatic 19	com/supersonicads/sdk/utils/SDKUtils:TAG	Ljava/lang/String;
    //   327: new 81	java/lang/StringBuilder
    //   330: dup
    //   331: aload 5
    //   333: invokevirtual 353	java/lang/Object:getClass	()Ljava/lang/Class;
    //   336: invokevirtual 17	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   339: invokestatic 356	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   342: invokespecial 357	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   345: ldc_w 359
    //   348: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   351: aload 5
    //   353: invokevirtual 349	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   356: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   359: invokevirtual 88	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   362: invokestatic 364	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   365: aload 5
    //   367: invokevirtual 368	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   370: ifnull -168 -> 202
    //   373: getstatic 19	com/supersonicads/sdk/utils/SDKUtils:TAG	Ljava/lang/String;
    //   376: new 81	java/lang/StringBuilder
    //   379: dup
    //   380: aload 5
    //   382: invokevirtual 353	java/lang/Object:getClass	()Ljava/lang/Class;
    //   385: invokevirtual 17	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   388: invokestatic 356	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   391: invokespecial 357	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   394: ldc_w 359
    //   397: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   400: aload 5
    //   402: invokevirtual 368	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   405: invokevirtual 371	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   408: invokevirtual 88	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   411: invokestatic 364	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   414: return
    //   415: astore 4
    //   417: aload 4
    //   419: ifnull -217 -> 202
    //   422: aload 4
    //   424: invokevirtual 349	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   427: ifnull +44 -> 471
    //   430: getstatic 19	com/supersonicads/sdk/utils/SDKUtils:TAG	Ljava/lang/String;
    //   433: new 81	java/lang/StringBuilder
    //   436: dup
    //   437: aload 4
    //   439: invokevirtual 353	java/lang/Object:getClass	()Ljava/lang/Class;
    //   442: invokevirtual 17	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   445: invokestatic 356	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   448: invokespecial 357	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   451: ldc_w 359
    //   454: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   457: aload 4
    //   459: invokevirtual 349	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   462: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   465: invokevirtual 88	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   468: invokestatic 364	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   471: aload 4
    //   473: invokevirtual 368	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   476: ifnull -274 -> 202
    //   479: getstatic 19	com/supersonicads/sdk/utils/SDKUtils:TAG	Ljava/lang/String;
    //   482: new 81	java/lang/StringBuilder
    //   485: dup
    //   486: aload 4
    //   488: invokevirtual 353	java/lang/Object:getClass	()Ljava/lang/Class;
    //   491: invokevirtual 17	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   494: invokestatic 356	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   497: invokespecial 357	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   500: ldc_w 359
    //   503: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   506: aload 4
    //   508: invokevirtual 368	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   511: invokevirtual 371	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   514: invokevirtual 88	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   517: invokestatic 364	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   520: return
    //   521: astore_3
    //   522: aload_3
    //   523: ifnull -321 -> 202
    //   526: aload_3
    //   527: invokevirtual 349	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   530: ifnull +42 -> 572
    //   533: getstatic 19	com/supersonicads/sdk/utils/SDKUtils:TAG	Ljava/lang/String;
    //   536: new 81	java/lang/StringBuilder
    //   539: dup
    //   540: aload_3
    //   541: invokevirtual 353	java/lang/Object:getClass	()Ljava/lang/Class;
    //   544: invokevirtual 17	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   547: invokestatic 356	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   550: invokespecial 357	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   553: ldc_w 359
    //   556: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   559: aload_3
    //   560: invokevirtual 349	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   563: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   566: invokevirtual 88	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   569: invokestatic 364	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   572: aload_3
    //   573: invokevirtual 368	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   576: ifnull -374 -> 202
    //   579: getstatic 19	com/supersonicads/sdk/utils/SDKUtils:TAG	Ljava/lang/String;
    //   582: new 81	java/lang/StringBuilder
    //   585: dup
    //   586: aload_3
    //   587: invokevirtual 353	java/lang/Object:getClass	()Ljava/lang/Class;
    //   590: invokevirtual 17	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   593: invokestatic 356	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   596: invokespecial 357	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   599: ldc_w 359
    //   602: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   605: aload_3
    //   606: invokevirtual 368	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   609: invokevirtual 371	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   612: invokevirtual 88	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   615: invokestatic 364	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   618: return
    //   619: astore_2
    //   620: aload_2
    //   621: ifnull -419 -> 202
    //   624: aload_2
    //   625: invokevirtual 349	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   628: ifnull +42 -> 670
    //   631: getstatic 19	com/supersonicads/sdk/utils/SDKUtils:TAG	Ljava/lang/String;
    //   634: new 81	java/lang/StringBuilder
    //   637: dup
    //   638: aload_2
    //   639: invokevirtual 353	java/lang/Object:getClass	()Ljava/lang/Class;
    //   642: invokevirtual 17	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   645: invokestatic 356	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   648: invokespecial 357	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   651: ldc_w 359
    //   654: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   657: aload_2
    //   658: invokevirtual 349	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   661: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   664: invokevirtual 88	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   667: invokestatic 364	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   670: aload_2
    //   671: invokevirtual 368	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   674: ifnull -472 -> 202
    //   677: getstatic 19	com/supersonicads/sdk/utils/SDKUtils:TAG	Ljava/lang/String;
    //   680: new 81	java/lang/StringBuilder
    //   683: dup
    //   684: aload_2
    //   685: invokevirtual 353	java/lang/Object:getClass	()Ljava/lang/Class;
    //   688: invokevirtual 17	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   691: invokestatic 356	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   694: invokespecial 357	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   697: ldc_w 359
    //   700: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   703: aload_2
    //   704: invokevirtual 368	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   707: invokevirtual 371	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   710: invokevirtual 88	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   713: invokestatic 364	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   716: return
    //   717: astore_1
    //   718: iconst_0
    //   719: ifeq +95 -> 814
    //   722: aconst_null
    //   723: invokevirtual 349	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   726: ifnull +42 -> 768
    //   729: getstatic 19	com/supersonicads/sdk/utils/SDKUtils:TAG	Ljava/lang/String;
    //   732: new 81	java/lang/StringBuilder
    //   735: dup
    //   736: aconst_null
    //   737: invokevirtual 353	java/lang/Object:getClass	()Ljava/lang/Class;
    //   740: invokevirtual 17	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   743: invokestatic 356	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   746: invokespecial 357	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   749: ldc_w 359
    //   752: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   755: aconst_null
    //   756: invokevirtual 349	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   759: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   762: invokevirtual 88	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   765: invokestatic 364	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   768: aconst_null
    //   769: invokevirtual 368	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   772: ifnull +42 -> 814
    //   775: getstatic 19	com/supersonicads/sdk/utils/SDKUtils:TAG	Ljava/lang/String;
    //   778: new 81	java/lang/StringBuilder
    //   781: dup
    //   782: aconst_null
    //   783: invokevirtual 353	java/lang/Object:getClass	()Ljava/lang/Class;
    //   786: invokevirtual 17	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   789: invokestatic 356	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   792: invokespecial 357	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   795: ldc_w 359
    //   798: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   801: aconst_null
    //   802: invokevirtual 368	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   805: invokevirtual 371	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   808: invokevirtual 88	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   811: invokestatic 364	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   814: aload_1
    //   815: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	816	0	paramContext	Context
    //   717	98	1	localObject1	Object
    //   619	85	2	localInvocationTargetException	java.lang.reflect.InvocationTargetException
    //   521	85	3	localIllegalArgumentException	java.lang.IllegalArgumentException
    //   415	92	4	localIllegalAccessException	java.lang.IllegalAccessException
    //   309	92	5	localNoSuchMethodException	java.lang.NoSuchMethodException
    //   203	92	6	localClassNotFoundException	java.lang.ClassNotFoundException
    //   33	56	7	localObject2	Object
    //   50	20	8	localMethod1	java.lang.reflect.Method
    //   67	20	9	localMethod2	java.lang.reflect.Method
    // Exception table:
    //   from	to	target	type
    //   0	106	203	java/lang/ClassNotFoundException
    //   0	106	309	java/lang/NoSuchMethodException
    //   0	106	415	java/lang/IllegalAccessException
    //   0	106	521	java/lang/IllegalArgumentException
    //   0	106	619	java/lang/reflect/InvocationTargetException
    //   0	106	717	finally
  }
  
  public static int pxToDp(long paramLong)
  {
    DisplayMetrics localDisplayMetrics = Resources.getSystem().getDisplayMetrics();
    return (int)(0.5F + (float)paramLong / localDisplayMetrics.density);
  }
  
  /* Error */
  public static String queryingPurchasedItems(Object paramObject, String paramString)
  {
    // Byte code:
    //   0: new 501	org/json/JSONArray
    //   3: dup
    //   4: invokespecial 502	org/json/JSONArray:<init>	()V
    //   7: astore_2
    //   8: aload_0
    //   9: invokevirtual 353	java/lang/Object:getClass	()Ljava/lang/Class;
    //   12: astore 8
    //   14: iconst_4
    //   15: anewarray 13	java/lang/Class
    //   18: astore 9
    //   20: aload 9
    //   22: iconst_0
    //   23: getstatic 508	java/lang/Integer:TYPE	Ljava/lang/Class;
    //   26: aastore
    //   27: aload 9
    //   29: iconst_1
    //   30: ldc 113
    //   32: aastore
    //   33: aload 9
    //   35: iconst_2
    //   36: ldc 113
    //   38: aastore
    //   39: aload 9
    //   41: iconst_3
    //   42: ldc 113
    //   44: aastore
    //   45: aload 8
    //   47: ldc_w 510
    //   50: aload 9
    //   52: invokevirtual 338	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   55: astore 10
    //   57: iconst_4
    //   58: anewarray 4	java/lang/Object
    //   61: astore 11
    //   63: aload 11
    //   65: iconst_0
    //   66: iconst_3
    //   67: invokestatic 513	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   70: aastore
    //   71: aload 11
    //   73: iconst_1
    //   74: aload_1
    //   75: aastore
    //   76: aload 11
    //   78: iconst_2
    //   79: ldc_w 515
    //   82: aastore
    //   83: aload 10
    //   85: aload_0
    //   86: aload 11
    //   88: invokevirtual 344	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   91: astore 12
    //   93: aload 12
    //   95: invokevirtual 353	java/lang/Object:getClass	()Ljava/lang/Class;
    //   98: ldc_w 517
    //   101: iconst_1
    //   102: anewarray 13	java/lang/Class
    //   105: dup
    //   106: iconst_0
    //   107: ldc 113
    //   109: aastore
    //   110: invokevirtual 338	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   113: astore 13
    //   115: aload 12
    //   117: invokevirtual 353	java/lang/Object:getClass	()Ljava/lang/Class;
    //   120: ldc_w 519
    //   123: iconst_1
    //   124: anewarray 13	java/lang/Class
    //   127: dup
    //   128: iconst_0
    //   129: ldc 113
    //   131: aastore
    //   132: invokevirtual 338	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   135: astore 14
    //   137: aload 12
    //   139: invokevirtual 353	java/lang/Object:getClass	()Ljava/lang/Class;
    //   142: ldc_w 520
    //   145: iconst_1
    //   146: anewarray 13	java/lang/Class
    //   149: dup
    //   150: iconst_0
    //   151: ldc 113
    //   153: aastore
    //   154: invokevirtual 338	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   157: astore 15
    //   159: aload 13
    //   161: aload 12
    //   163: iconst_1
    //   164: anewarray 4	java/lang/Object
    //   167: dup
    //   168: iconst_0
    //   169: ldc_w 522
    //   172: aastore
    //   173: invokevirtual 344	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   176: checkcast 504	java/lang/Integer
    //   179: invokevirtual 525	java/lang/Integer:intValue	()I
    //   182: ifne +107 -> 289
    //   185: aload 14
    //   187: aload 12
    //   189: iconst_1
    //   190: anewarray 4	java/lang/Object
    //   193: dup
    //   194: iconst_0
    //   195: ldc_w 527
    //   198: aastore
    //   199: invokevirtual 344	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   202: checkcast 529	java/util/ArrayList
    //   205: astore 16
    //   207: aload 14
    //   209: aload 12
    //   211: iconst_1
    //   212: anewarray 4	java/lang/Object
    //   215: dup
    //   216: iconst_0
    //   217: ldc_w 531
    //   220: aastore
    //   221: invokevirtual 344	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   224: checkcast 529	java/util/ArrayList
    //   227: astore 17
    //   229: aload 14
    //   231: aload 12
    //   233: iconst_1
    //   234: anewarray 4	java/lang/Object
    //   237: dup
    //   238: iconst_0
    //   239: ldc_w 533
    //   242: aastore
    //   243: invokevirtual 344	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   246: checkcast 529	java/util/ArrayList
    //   249: astore 18
    //   251: aload 15
    //   253: aload 12
    //   255: iconst_1
    //   256: anewarray 4	java/lang/Object
    //   259: dup
    //   260: iconst_0
    //   261: ldc_w 535
    //   264: aastore
    //   265: invokevirtual 344	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   268: checkcast 113	java/lang/String
    //   271: pop
    //   272: iconst_0
    //   273: istore 20
    //   275: aload 17
    //   277: invokevirtual 538	java/util/ArrayList:size	()I
    //   280: istore 21
    //   282: iload 20
    //   284: iload 21
    //   286: if_icmplt +104 -> 390
    //   289: iconst_0
    //   290: ifeq +95 -> 385
    //   293: aconst_null
    //   294: invokevirtual 349	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   297: ifnull +42 -> 339
    //   300: getstatic 19	com/supersonicads/sdk/utils/SDKUtils:TAG	Ljava/lang/String;
    //   303: new 81	java/lang/StringBuilder
    //   306: dup
    //   307: aconst_null
    //   308: invokevirtual 353	java/lang/Object:getClass	()Ljava/lang/Class;
    //   311: invokevirtual 17	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   314: invokestatic 356	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   317: invokespecial 357	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   320: ldc_w 359
    //   323: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   326: aconst_null
    //   327: invokevirtual 349	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   330: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   333: invokevirtual 88	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   336: invokestatic 364	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   339: aconst_null
    //   340: invokevirtual 368	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   343: ifnull +42 -> 385
    //   346: getstatic 19	com/supersonicads/sdk/utils/SDKUtils:TAG	Ljava/lang/String;
    //   349: new 81	java/lang/StringBuilder
    //   352: dup
    //   353: aconst_null
    //   354: invokevirtual 353	java/lang/Object:getClass	()Ljava/lang/Class;
    //   357: invokevirtual 17	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   360: invokestatic 356	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   363: invokespecial 357	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   366: ldc_w 359
    //   369: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   372: aconst_null
    //   373: invokevirtual 368	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   376: invokevirtual 371	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   379: invokevirtual 88	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   382: invokestatic 364	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   385: aload_2
    //   386: invokevirtual 539	org/json/JSONArray:toString	()Ljava/lang/String;
    //   389: areturn
    //   390: aload 17
    //   392: iload 20
    //   394: invokevirtual 543	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   397: checkcast 113	java/lang/String
    //   400: astore 22
    //   402: aload 18
    //   404: iload 20
    //   406: invokevirtual 543	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   409: checkcast 113	java/lang/String
    //   412: astore 23
    //   414: aload 16
    //   416: iload 20
    //   418: invokevirtual 543	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   421: checkcast 113	java/lang/String
    //   424: astore 24
    //   426: getstatic 19	com/supersonicads/sdk/utils/SDKUtils:TAG	Ljava/lang/String;
    //   429: aload 22
    //   431: invokestatic 364	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   434: getstatic 19	com/supersonicads/sdk/utils/SDKUtils:TAG	Ljava/lang/String;
    //   437: aload 23
    //   439: invokestatic 364	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   442: getstatic 19	com/supersonicads/sdk/utils/SDKUtils:TAG	Ljava/lang/String;
    //   445: aload 24
    //   447: invokestatic 364	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   450: new 407	org/json/JSONObject
    //   453: dup
    //   454: invokespecial 408	org/json/JSONObject:<init>	()V
    //   457: astore 25
    //   459: aload 25
    //   461: ldc_w 545
    //   464: aload 22
    //   466: invokevirtual 413	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   469: pop
    //   470: aload 25
    //   472: ldc_w 547
    //   475: aload 22
    //   477: invokevirtual 413	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   480: pop
    //   481: aload 25
    //   483: ldc_w 549
    //   486: aload 22
    //   488: invokevirtual 413	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   491: pop
    //   492: aload_2
    //   493: aload 25
    //   495: invokevirtual 552	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   498: pop
    //   499: iinc 20 1
    //   502: goto -227 -> 275
    //   505: astore 7
    //   507: aload 7
    //   509: ifnull -124 -> 385
    //   512: aload 7
    //   514: invokevirtual 349	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   517: ifnull +44 -> 561
    //   520: getstatic 19	com/supersonicads/sdk/utils/SDKUtils:TAG	Ljava/lang/String;
    //   523: new 81	java/lang/StringBuilder
    //   526: dup
    //   527: aload 7
    //   529: invokevirtual 353	java/lang/Object:getClass	()Ljava/lang/Class;
    //   532: invokevirtual 17	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   535: invokestatic 356	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   538: invokespecial 357	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   541: ldc_w 359
    //   544: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   547: aload 7
    //   549: invokevirtual 349	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   552: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   555: invokevirtual 88	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   558: invokestatic 364	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   561: aload 7
    //   563: invokevirtual 368	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   566: ifnull -181 -> 385
    //   569: getstatic 19	com/supersonicads/sdk/utils/SDKUtils:TAG	Ljava/lang/String;
    //   572: new 81	java/lang/StringBuilder
    //   575: dup
    //   576: aload 7
    //   578: invokevirtual 353	java/lang/Object:getClass	()Ljava/lang/Class;
    //   581: invokevirtual 17	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   584: invokestatic 356	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   587: invokespecial 357	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   590: ldc_w 359
    //   593: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   596: aload 7
    //   598: invokevirtual 368	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   601: invokevirtual 371	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   604: invokevirtual 88	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   607: invokestatic 364	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   610: goto -225 -> 385
    //   613: astore 6
    //   615: aload 6
    //   617: ifnull -232 -> 385
    //   620: aload 6
    //   622: invokevirtual 349	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   625: ifnull +44 -> 669
    //   628: getstatic 19	com/supersonicads/sdk/utils/SDKUtils:TAG	Ljava/lang/String;
    //   631: new 81	java/lang/StringBuilder
    //   634: dup
    //   635: aload 6
    //   637: invokevirtual 353	java/lang/Object:getClass	()Ljava/lang/Class;
    //   640: invokevirtual 17	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   643: invokestatic 356	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   646: invokespecial 357	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   649: ldc_w 359
    //   652: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   655: aload 6
    //   657: invokevirtual 349	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   660: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   663: invokevirtual 88	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   666: invokestatic 364	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   669: aload 6
    //   671: invokevirtual 368	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   674: ifnull -289 -> 385
    //   677: getstatic 19	com/supersonicads/sdk/utils/SDKUtils:TAG	Ljava/lang/String;
    //   680: new 81	java/lang/StringBuilder
    //   683: dup
    //   684: aload 6
    //   686: invokevirtual 353	java/lang/Object:getClass	()Ljava/lang/Class;
    //   689: invokevirtual 17	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   692: invokestatic 356	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   695: invokespecial 357	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   698: ldc_w 359
    //   701: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   704: aload 6
    //   706: invokevirtual 368	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   709: invokevirtual 371	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   712: invokevirtual 88	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   715: invokestatic 364	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   718: goto -333 -> 385
    //   721: astore 5
    //   723: aload 5
    //   725: ifnull -340 -> 385
    //   728: aload 5
    //   730: invokevirtual 349	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   733: ifnull +44 -> 777
    //   736: getstatic 19	com/supersonicads/sdk/utils/SDKUtils:TAG	Ljava/lang/String;
    //   739: new 81	java/lang/StringBuilder
    //   742: dup
    //   743: aload 5
    //   745: invokevirtual 353	java/lang/Object:getClass	()Ljava/lang/Class;
    //   748: invokevirtual 17	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   751: invokestatic 356	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   754: invokespecial 357	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   757: ldc_w 359
    //   760: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   763: aload 5
    //   765: invokevirtual 349	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   768: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   771: invokevirtual 88	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   774: invokestatic 364	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   777: aload 5
    //   779: invokevirtual 368	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   782: ifnull -397 -> 385
    //   785: getstatic 19	com/supersonicads/sdk/utils/SDKUtils:TAG	Ljava/lang/String;
    //   788: new 81	java/lang/StringBuilder
    //   791: dup
    //   792: aload 5
    //   794: invokevirtual 353	java/lang/Object:getClass	()Ljava/lang/Class;
    //   797: invokevirtual 17	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   800: invokestatic 356	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   803: invokespecial 357	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   806: ldc_w 359
    //   809: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   812: aload 5
    //   814: invokevirtual 368	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   817: invokevirtual 371	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   820: invokevirtual 88	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   823: invokestatic 364	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   826: goto -441 -> 385
    //   829: astore 4
    //   831: aload 4
    //   833: ifnull -448 -> 385
    //   836: aload 4
    //   838: invokevirtual 349	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   841: ifnull +44 -> 885
    //   844: getstatic 19	com/supersonicads/sdk/utils/SDKUtils:TAG	Ljava/lang/String;
    //   847: new 81	java/lang/StringBuilder
    //   850: dup
    //   851: aload 4
    //   853: invokevirtual 353	java/lang/Object:getClass	()Ljava/lang/Class;
    //   856: invokevirtual 17	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   859: invokestatic 356	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   862: invokespecial 357	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   865: ldc_w 359
    //   868: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   871: aload 4
    //   873: invokevirtual 349	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   876: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   879: invokevirtual 88	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   882: invokestatic 364	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   885: aload 4
    //   887: invokevirtual 368	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   890: ifnull -505 -> 385
    //   893: getstatic 19	com/supersonicads/sdk/utils/SDKUtils:TAG	Ljava/lang/String;
    //   896: new 81	java/lang/StringBuilder
    //   899: dup
    //   900: aload 4
    //   902: invokevirtual 353	java/lang/Object:getClass	()Ljava/lang/Class;
    //   905: invokevirtual 17	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   908: invokestatic 356	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   911: invokespecial 357	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   914: ldc_w 359
    //   917: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   920: aload 4
    //   922: invokevirtual 368	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   925: invokevirtual 371	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   928: invokevirtual 88	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   931: invokestatic 364	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   934: goto -549 -> 385
    //   937: astore_3
    //   938: iconst_0
    //   939: ifeq +95 -> 1034
    //   942: aconst_null
    //   943: invokevirtual 349	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   946: ifnull +42 -> 988
    //   949: getstatic 19	com/supersonicads/sdk/utils/SDKUtils:TAG	Ljava/lang/String;
    //   952: new 81	java/lang/StringBuilder
    //   955: dup
    //   956: aconst_null
    //   957: invokevirtual 353	java/lang/Object:getClass	()Ljava/lang/Class;
    //   960: invokevirtual 17	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   963: invokestatic 356	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   966: invokespecial 357	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   969: ldc_w 359
    //   972: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   975: aconst_null
    //   976: invokevirtual 349	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   979: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   982: invokevirtual 88	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   985: invokestatic 364	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   988: aconst_null
    //   989: invokevirtual 368	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   992: ifnull +42 -> 1034
    //   995: getstatic 19	com/supersonicads/sdk/utils/SDKUtils:TAG	Ljava/lang/String;
    //   998: new 81	java/lang/StringBuilder
    //   1001: dup
    //   1002: aconst_null
    //   1003: invokevirtual 353	java/lang/Object:getClass	()Ljava/lang/Class;
    //   1006: invokevirtual 17	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   1009: invokestatic 356	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   1012: invokespecial 357	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1015: ldc_w 359
    //   1018: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1021: aconst_null
    //   1022: invokevirtual 368	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   1025: invokevirtual 371	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1028: invokevirtual 88	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1031: invokestatic 364	com/supersonicads/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1034: aload_3
    //   1035: athrow
    //   1036: astore 26
    //   1038: goto -539 -> 499
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1041	0	paramObject	Object
    //   0	1041	1	paramString	String
    //   7	486	2	localJSONArray	org.json.JSONArray
    //   937	98	3	localObject1	Object
    //   829	92	4	localInvocationTargetException	java.lang.reflect.InvocationTargetException
    //   721	92	5	localIllegalArgumentException	java.lang.IllegalArgumentException
    //   613	92	6	localIllegalAccessException	java.lang.IllegalAccessException
    //   505	92	7	localNoSuchMethodException	java.lang.NoSuchMethodException
    //   12	34	8	localClass	Class
    //   18	33	9	arrayOfClass	Class[]
    //   55	29	10	localMethod1	java.lang.reflect.Method
    //   61	26	11	arrayOfObject	Object[]
    //   91	163	12	localObject2	Object
    //   113	47	13	localMethod2	java.lang.reflect.Method
    //   135	95	14	localMethod3	java.lang.reflect.Method
    //   157	95	15	localMethod4	java.lang.reflect.Method
    //   205	210	16	localArrayList1	java.util.ArrayList
    //   227	164	17	localArrayList2	java.util.ArrayList
    //   249	154	18	localArrayList3	java.util.ArrayList
    //   273	227	20	i	int
    //   280	7	21	j	int
    //   400	87	22	str1	String
    //   412	26	23	str2	String
    //   424	22	24	str3	String
    //   457	37	25	localJSONObject	JSONObject
    //   1036	1	26	localJSONException	JSONException
    // Exception table:
    //   from	to	target	type
    //   8	272	505	java/lang/NoSuchMethodException
    //   275	282	505	java/lang/NoSuchMethodException
    //   390	459	505	java/lang/NoSuchMethodException
    //   459	499	505	java/lang/NoSuchMethodException
    //   8	272	613	java/lang/IllegalAccessException
    //   275	282	613	java/lang/IllegalAccessException
    //   390	459	613	java/lang/IllegalAccessException
    //   459	499	613	java/lang/IllegalAccessException
    //   8	272	721	java/lang/IllegalArgumentException
    //   275	282	721	java/lang/IllegalArgumentException
    //   390	459	721	java/lang/IllegalArgumentException
    //   459	499	721	java/lang/IllegalArgumentException
    //   8	272	829	java/lang/reflect/InvocationTargetException
    //   275	282	829	java/lang/reflect/InvocationTargetException
    //   390	459	829	java/lang/reflect/InvocationTargetException
    //   459	499	829	java/lang/reflect/InvocationTargetException
    //   8	272	937	finally
    //   275	282	937	finally
    //   390	459	937	finally
    //   459	499	937	finally
    //   459	499	1036	org/json/JSONException
  }
  
  public static void showNoInternetDialog(Context paramContext)
  {
    new AlertDialog.Builder(paramContext).setMessage("No Internet Connection").setPositiveButton("Ok", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
      }
    }).show();
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.supersonicads.sdk.utils.SDKUtils
 * JD-Core Version:    0.7.0.1
 */