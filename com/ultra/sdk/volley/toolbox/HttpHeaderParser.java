package com.ultra.sdk.volley.toolbox;

import com.ultra.sdk.volley.Cache.Entry;
import com.ultra.sdk.volley.NetworkResponse;
import java.util.Date;
import java.util.Map;
import org.apache.http.impl.cookie.DateParseException;
import org.apache.http.impl.cookie.DateUtils;

public class HttpHeaderParser
{
  public static Cache.Entry parseCacheHeaders(NetworkResponse paramNetworkResponse)
  {
    long l1 = System.currentTimeMillis();
    Map localMap = paramNetworkResponse.headers;
    long l2 = 0L;
    long l3 = 0L;
    long l4 = 0L;
    long l5 = 0L;
    String str1 = (String)localMap.get("Date");
    if (str1 != null) {
      l2 = parseDateAsEpoch(str1);
    }
    String str2 = (String)localMap.get("Cache-Control");
    int i = 0;
    String[] arrayOfString;
    int j;
    String str4;
    if (str2 != null)
    {
      i = 1;
      arrayOfString = str2.split(",");
      j = 0;
      if (j < arrayOfString.length) {}
    }
    else
    {
      String str3 = (String)localMap.get("Expires");
      if (str3 != null) {
        l3 = parseDateAsEpoch(str3);
      }
      str4 = (String)localMap.get("ETag");
      if (i == 0) {
        break label291;
      }
    }
    for (l4 = l1 + 1000L * l5;; l4 = l1 + (l3 - l2)) {
      label291:
      do
      {
        Cache.Entry localEntry = new Cache.Entry();
        localEntry.data = paramNetworkResponse.data;
        localEntry.etag = str4;
        localEntry.softTtl = l4;
        localEntry.ttl = localEntry.softTtl;
        localEntry.serverDate = l2;
        localEntry.responseHeaders = localMap;
        return localEntry;
        String str5 = arrayOfString[j].trim();
        if ((str5.equals("no-cache")) || (str5.equals("no-store"))) {
          return null;
        }
        if (str5.startsWith("max-age=")) {}
        for (;;)
        {
          try
          {
            long l6 = Long.parseLong(str5.substring(8));
            l5 = l6;
            j++;
          }
          catch (Exception localException)
          {
            continue;
          }
          break;
          if ((str5.equals("must-revalidate")) || (str5.equals("proxy-revalidate"))) {
            l5 = 0L;
          }
        }
      } while ((l2 <= 0L) || (l3 < l2));
    }
  }
  
  public static String parseCharset(Map<String, String> paramMap)
  {
    String str = (String)paramMap.get("Content-Type");
    String[] arrayOfString1;
    if (str != null) {
      arrayOfString1 = str.split(";");
    }
    for (int i = 1;; i++)
    {
      if (i >= arrayOfString1.length) {
        return "ISO-8859-1";
      }
      String[] arrayOfString2 = arrayOfString1[i].trim().split("=");
      if ((arrayOfString2.length == 2) && (arrayOfString2[0].equals("charset"))) {
        return arrayOfString2[1];
      }
    }
  }
  
  public static long parseDateAsEpoch(String paramString)
  {
    try
    {
      long l = DateUtils.parseDate(paramString).getTime();
      return l;
    }
    catch (DateParseException localDateParseException) {}
    return 0L;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ultra.sdk.volley.toolbox.HttpHeaderParser
 * JD-Core Version:    0.7.0.1
 */