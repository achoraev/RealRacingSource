package com.ea.nimble;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class Utility
{
  public static String SHA256HashString(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("SHA-256");
      localMessageDigest.reset();
      byte[] arrayOfByte = localMessageDigest.digest(paramString.getBytes());
      StringBuffer localStringBuffer = new StringBuffer();
      for (int i = 0; i < arrayOfByte.length; i++) {
        localStringBuffer.append(Integer.toString(256 + (0xFF & arrayOfByte[i]), 16).substring(1));
      }
      return localStringBuffer.toString();
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      Log.Helper.LOGES(null, "Can't find SHA-256 algorithm", new Object[0]);
      return null;
    }
  }
  
  public static String bytesToHexString(byte[] paramArrayOfByte)
  {
    char[] arrayOfChar1 = "0123456789ABCDEF".toCharArray();
    char[] arrayOfChar2 = new char[2 * paramArrayOfByte.length];
    for (int i = 0; i < paramArrayOfByte.length; i++)
    {
      int j = 0xFF & paramArrayOfByte[i];
      arrayOfChar2[(i * 2)] = arrayOfChar1[(j >> 4)];
      arrayOfChar2[(1 + i * 2)] = arrayOfChar1[(j & 0xF)];
    }
    return new String(arrayOfChar2);
  }
  
  public static List<Object> convertJSONArrayToList(JSONArray paramJSONArray)
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    if (i < paramJSONArray.length())
    {
      if (paramJSONArray.isNull(i)) {
        localArrayList.add(null);
      }
      for (;;)
      {
        i++;
        break;
        JSONObject localJSONObject = paramJSONArray.optJSONObject(i);
        if (localJSONObject != null)
        {
          localArrayList.add(convertJSONObjectToMap(localJSONObject));
        }
        else
        {
          JSONArray localJSONArray = paramJSONArray.optJSONArray(i);
          if (localJSONArray != null) {
            localArrayList.add(convertJSONArrayToList(localJSONArray));
          } else {
            try
            {
              localArrayList.add(paramJSONArray.get(i));
            }
            catch (JSONException localJSONException)
            {
              localJSONException.printStackTrace();
              localArrayList.add(null);
            }
          }
        }
      }
    }
    return localArrayList;
  }
  
  public static Map<String, Object> convertJSONObjectToMap(JSONObject paramJSONObject)
  {
    HashMap localHashMap = new HashMap();
    Iterator localIterator = paramJSONObject.keys();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if (paramJSONObject.isNull(str))
      {
        localHashMap.put(str, null);
      }
      else
      {
        JSONObject localJSONObject = paramJSONObject.optJSONObject(str);
        if (localJSONObject != null)
        {
          localHashMap.put(str, convertJSONObjectToMap(localJSONObject));
        }
        else
        {
          JSONArray localJSONArray = paramJSONObject.optJSONArray(str);
          if (localJSONArray != null) {
            localHashMap.put(str, convertJSONArrayToList(localJSONArray));
          } else {
            try
            {
              localHashMap.put(str, paramJSONObject.get(str));
            }
            catch (JSONException localJSONException)
            {
              localJSONException.printStackTrace();
              localHashMap.put(str, null);
            }
          }
        }
      }
    }
    return localHashMap;
  }
  
  public static String convertObjectToJSONString(Object paramObject)
  {
    return new GsonBuilder().disableHtmlEscaping().create().toJson(paramObject);
  }
  
  public static boolean getTestResult()
  {
    return 5 + 5 == 10;
  }
  
  public static String getUTCDateStringFormat(Date paramDate)
  {
    if (paramDate == null) {
      return "";
    }
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US);
    localSimpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    return localSimpleDateFormat.format(paramDate);
  }
  
  public static boolean isOnlyDecimalCharacters(String paramString)
  {
    if (paramString == null) {
      return false;
    }
    for (int i = 0;; i++)
    {
      if (i >= paramString.length()) {
        break label33;
      }
      if (!Character.isDigit(paramString.charAt(i))) {
        break;
      }
    }
    label33:
    return true;
  }
  
  /* Error */
  public static Map<String, String> parseXmlFile(int paramInt)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: invokestatic 226	com/ea/nimble/ApplicationEnvironment:getComponent	()Lcom/ea/nimble/IApplicationEnvironment;
    //   5: invokeinterface 232 1 0
    //   10: invokevirtual 238	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   13: iload_0
    //   14: invokevirtual 244	android/content/res/Resources:getXml	(I)Landroid/content/res/XmlResourceParser;
    //   17: astore_1
    //   18: new 246	java/util/LinkedHashMap
    //   21: dup
    //   22: invokespecial 247	java/util/LinkedHashMap:<init>	()V
    //   25: astore 4
    //   27: aconst_null
    //   28: astore 5
    //   30: aload_1
    //   31: invokeinterface 252 1 0
    //   36: istore 6
    //   38: iload 6
    //   40: iconst_1
    //   41: if_icmpeq +101 -> 142
    //   44: iload 6
    //   46: iconst_2
    //   47: if_icmpne +22 -> 69
    //   50: aload_1
    //   51: invokeinterface 255 1 0
    //   56: astore 5
    //   58: aload_1
    //   59: invokeinterface 257 1 0
    //   64: istore 6
    //   66: goto -28 -> 38
    //   69: iload 6
    //   71: iconst_4
    //   72: if_icmpne -14 -> 58
    //   75: aload 4
    //   77: aload 5
    //   79: aload_1
    //   80: invokeinterface 260 1 0
    //   85: invokeinterface 144 3 0
    //   90: pop
    //   91: goto -33 -> 58
    //   94: astore_3
    //   95: aconst_null
    //   96: new 262	java/lang/StringBuilder
    //   99: dup
    //   100: invokespecial 263	java/lang/StringBuilder:<init>	()V
    //   103: ldc_w 265
    //   106: invokevirtual 268	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   109: aload_3
    //   110: invokevirtual 269	java/lang/Exception:toString	()Ljava/lang/String;
    //   113: invokevirtual 268	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   116: invokevirtual 270	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   119: iconst_0
    //   120: anewarray 4	java/lang/Object
    //   123: invokestatic 58	com/ea/nimble/Log$Helper:LOGES	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
    //   126: aload_1
    //   127: ifnull +9 -> 136
    //   130: aload_1
    //   131: invokeinterface 273 1 0
    //   136: aconst_null
    //   137: astore 4
    //   139: aload 4
    //   141: areturn
    //   142: aload_1
    //   143: invokeinterface 273 1 0
    //   148: aload_1
    //   149: ifnull -10 -> 139
    //   152: aload_1
    //   153: invokeinterface 273 1 0
    //   158: aload 4
    //   160: areturn
    //   161: astore_2
    //   162: aload_1
    //   163: ifnull +9 -> 172
    //   166: aload_1
    //   167: invokeinterface 273 1 0
    //   172: aload_2
    //   173: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	174	0	paramInt	int
    //   1	166	1	localXmlResourceParser	android.content.res.XmlResourceParser
    //   161	12	2	localObject	Object
    //   94	16	3	localException	java.lang.Exception
    //   25	134	4	localLinkedHashMap	java.util.LinkedHashMap
    //   28	50	5	str	String
    //   36	37	6	i	int
    // Exception table:
    //   from	to	target	type
    //   2	27	94	java/lang/Exception
    //   30	38	94	java/lang/Exception
    //   50	58	94	java/lang/Exception
    //   58	66	94	java/lang/Exception
    //   75	91	94	java/lang/Exception
    //   142	148	94	java/lang/Exception
    //   2	27	161	finally
    //   30	38	161	finally
    //   50	58	161	finally
    //   58	66	161	finally
    //   75	91	161	finally
    //   95	126	161	finally
    //   142	148	161	finally
  }
  
  public static String readStringFromStream(InputStream paramInputStream)
    throws IOException
  {
    InputStreamReader localInputStreamReader = new InputStreamReader(paramInputStream, Charset.defaultCharset());
    char[] arrayOfChar = new char[4096];
    StringWriter localStringWriter = new StringWriter();
    for (;;)
    {
      int i = localInputStreamReader.read(arrayOfChar, 0, 4096);
      if (i <= 0) {
        break;
      }
      localStringWriter.write(arrayOfChar, 0, i);
    }
    return localStringWriter.toString();
  }
  
  public static void registerReceiver(String paramString, BroadcastReceiver paramBroadcastReceiver)
  {
    IntentFilter localIntentFilter = new IntentFilter(paramString);
    LocalBroadcastManager.getInstance(ApplicationEnvironment.getComponent().getApplicationContext()).registerReceiver(paramBroadcastReceiver, localIntentFilter);
  }
  
  public static String safeString(String paramString)
  {
    if (paramString == null) {
      paramString = "";
    }
    return paramString;
  }
  
  public static void sendBroadcast(String paramString, Map<String, String> paramMap)
  {
    LocalBroadcastManager localLocalBroadcastManager = LocalBroadcastManager.getInstance(ApplicationEnvironment.getComponent().getApplicationContext());
    Intent localIntent = new Intent(paramString);
    if (paramMap != null)
    {
      Iterator localIterator = paramMap.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        localIntent.putExtra(str, (String)paramMap.get(str));
      }
    }
    localLocalBroadcastManager.sendBroadcast(localIntent);
  }
  
  public static void sendBroadcastSerializable(String paramString, Map<String, Serializable> paramMap)
  {
    LocalBroadcastManager localLocalBroadcastManager = LocalBroadcastManager.getInstance(ApplicationEnvironment.getComponent().getApplicationContext());
    Intent localIntent = new Intent(paramString);
    if (paramMap != null)
    {
      Iterator localIterator = paramMap.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        localIntent.putExtra(str, (Serializable)paramMap.get(str));
      }
    }
    localLocalBroadcastManager.sendBroadcast(localIntent);
  }
  
  public static boolean stringsAreEquivalent(String paramString1, String paramString2)
  {
    if ((paramString1 != null) && (paramString2 != null)) {
      if (paramString1.compareTo(paramString2) != 0) {}
    }
    while (paramString1 == paramString2)
    {
      return true;
      return false;
    }
    return false;
  }
  
  public static void unregisterReceiver(BroadcastReceiver paramBroadcastReceiver)
  {
    LocalBroadcastManager.getInstance(ApplicationEnvironment.getComponent().getApplicationContext()).unregisterReceiver(paramBroadcastReceiver);
  }
  
  public static boolean validString(String paramString)
  {
    return (paramString != null) && (paramString.length() > 0);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ea.nimble.Utility
 * JD-Core Version:    0.7.0.1
 */