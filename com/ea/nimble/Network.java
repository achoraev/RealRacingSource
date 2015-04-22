package com.ea.nimble;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Network
{
  public static final String COMPONENT_ID = "com.ea.nimble.network";
  
  public static String generateParameterString(Map<String, String> paramMap)
  {
    Object localObject = "";
    String str1 = "";
    Iterator localIterator = paramMap.entrySet().iterator();
    for (;;)
    {
      Map.Entry localEntry;
      if (localIterator.hasNext())
      {
        localEntry = (Map.Entry)localIterator.next();
        if (((String)localObject).length() > 0) {
          localObject = (String)localObject + "&";
        }
      }
      try
      {
        str1 = URLEncoder.encode((String)localEntry.getKey(), "UTF-8");
        String str2 = URLEncoder.encode((String)localEntry.getValue(), "UTF-8");
        localObject = (String)localObject + str1;
        localObject = (String)localObject + "=";
        String str3 = (String)localObject + str2;
        localObject = str3;
      }
      catch (NullPointerException localNullPointerException)
      {
        Log.Helper.LOGDS("Network", "NULL POINTER on generate param string for key: " + str1, new Object[0]);
        continue;
        return localObject;
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException) {}
    }
  }
  
  public static URL generateURL(String paramString, Map<String, String> paramMap)
  {
    if (paramMap != null) {}
    try
    {
      String str = String.format("%s?%s", new Object[] { paramString, generateParameterString(paramMap) });
      Log.Helper.LOGVS("Network", "Generated URL = %s", new Object[] { str });
      return new URL(str);
    }
    catch (MalformedURLException localMalformedURLException)
    {
      URL localURL;
      Log.Helper.LOGFS("Network", "Malformed URL from %s", new Object[] { paramString });
    }
    Log.Helper.LOGVS("Network", "Generated URL = %s", new Object[] { paramString });
    localURL = new URL(paramString);
    return localURL;
    return null;
  }
  
  public static INetwork getComponent()
  {
    return (INetwork)Base.getComponent("com.ea.nimble.network");
  }
  
  public static enum Status
  {
    static
    {
      NONE = new Status("NONE", 1);
      DEAD = new Status("DEAD", 2);
      OK = new Status("OK", 3);
      Status[] arrayOfStatus = new Status[4];
      arrayOfStatus[0] = UNKNOWN;
      arrayOfStatus[1] = NONE;
      arrayOfStatus[2] = DEAD;
      arrayOfStatus[3] = OK;
      $VALUES = arrayOfStatus;
    }
    
    private Status() {}
    
    public String toString()
    {
      switch (Network.1.$SwitchMap$com$ea$nimble$Network$Status[ordinal()])
      {
      default: 
        return "NET UNKONWN";
      case 1: 
        return "NET NONE";
      case 2: 
        return "NET DEAD";
      }
      return "NET OK";
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ea.nimble.Network
 * JD-Core Version:    0.7.0.1
 */