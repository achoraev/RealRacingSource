package com.google.android.gms.internal;

import android.text.TextUtils;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class jz
{
  private static final Pattern MT = Pattern.compile("\\\\.");
  private static final Pattern MU = Pattern.compile("[\\\\\"/\b\f\n\r\t]");
  
  public static String bf(String paramString)
  {
    Matcher localMatcher;
    StringBuffer localStringBuffer;
    if (!TextUtils.isEmpty(paramString))
    {
      localMatcher = MU.matcher(paramString);
      localStringBuffer = null;
      while (localMatcher.find())
      {
        if (localStringBuffer == null) {
          localStringBuffer = new StringBuffer();
        }
        switch (localMatcher.group().charAt(0))
        {
        default: 
          break;
        case '\b': 
          localMatcher.appendReplacement(localStringBuffer, "\\\\b");
          break;
        case '"': 
          localMatcher.appendReplacement(localStringBuffer, "\\\\\\\"");
          break;
        case '\\': 
          localMatcher.appendReplacement(localStringBuffer, "\\\\\\\\");
          break;
        case '/': 
          localMatcher.appendReplacement(localStringBuffer, "\\\\/");
          break;
        case '\f': 
          localMatcher.appendReplacement(localStringBuffer, "\\\\f");
          break;
        case '\n': 
          localMatcher.appendReplacement(localStringBuffer, "\\\\n");
          break;
        case '\r': 
          localMatcher.appendReplacement(localStringBuffer, "\\\\r");
          break;
        case '\t': 
          localMatcher.appendReplacement(localStringBuffer, "\\\\t");
        }
      }
      if (localStringBuffer != null) {}
    }
    else
    {
      return paramString;
    }
    localMatcher.appendTail(localStringBuffer);
    return localStringBuffer.toString();
  }
  
  public static boolean d(Object paramObject1, Object paramObject2)
  {
    JSONObject localJSONObject1;
    JSONObject localJSONObject2;
    if (((paramObject1 instanceof JSONObject)) && ((paramObject2 instanceof JSONObject)))
    {
      localJSONObject1 = (JSONObject)paramObject1;
      localJSONObject2 = (JSONObject)paramObject2;
      if (localJSONObject1.length() == localJSONObject2.length()) {}
    }
    for (;;)
    {
      return false;
      Iterator localIterator = localJSONObject1.keys();
      for (;;)
      {
        String str;
        if (localIterator.hasNext())
        {
          str = (String)localIterator.next();
          if (!localJSONObject2.has(str)) {
            break;
          }
        }
        JSONArray localJSONArray1;
        JSONArray localJSONArray2;
        int i;
        boolean bool1;
        try
        {
          boolean bool2 = d(localJSONObject1.get(str), localJSONObject2.get(str));
          if (!bool2) {
            return false;
          }
        }
        catch (JSONException localJSONException2) {}
      }
      return true;
      if ((!(paramObject1 instanceof JSONArray)) || (!(paramObject2 instanceof JSONArray))) {
        break label185;
      }
      localJSONArray1 = (JSONArray)paramObject1;
      localJSONArray2 = (JSONArray)paramObject2;
      if (localJSONArray1.length() == localJSONArray2.length())
      {
        i = 0;
        if (i >= localJSONArray1.length()) {}
      }
      try
      {
        bool1 = d(localJSONArray1.get(i), localJSONArray2.get(i));
        if (bool1) {
          i++;
        }
      }
      catch (JSONException localJSONException1)
      {
        label185:
        return false;
      }
    }
    return true;
    return paramObject1.equals(paramObject2);
    return false;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.jz
 * JD-Core Version:    0.7.0.1
 */