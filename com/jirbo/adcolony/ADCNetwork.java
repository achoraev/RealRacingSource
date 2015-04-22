package com.jirbo.adcolony;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

class ADCNetwork
{
  public static String lowercase_hex_digits = "0123456789abcdef";
  public static final int timeout_seconds = 30;
  public static String uppercase_hex_digits;
  public static String url_encoding_map = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx  x          xxxxxxx                          xxxx x                          xxxxx";
  
  static
  {
    uppercase_hex_digits = "0123456789ABCDEF";
  }
  
  static boolean connected()
  {
    return (using_wifi()) || (using_mobile());
  }
  
  public static int hex_character_to_value(char paramChar)
  {
    int i = uppercase_hex_digits.indexOf(paramChar);
    if (i >= 0) {
      return i;
    }
    int j = lowercase_hex_digits.indexOf(paramChar);
    if (j >= 0) {
      return j;
    }
    return 0;
  }
  
  public static String status()
  {
    if (using_wifi()) {
      return "wifi";
    }
    if (using_mobile()) {
      return "cell";
    }
    return "offline";
  }
  
  public static String url_decoded(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = paramString.length();
    int j = 0;
    if (j < i)
    {
      char c1 = paramString.charAt(j);
      char c2;
      label50:
      char c3;
      if (c1 == '%') {
        if (j + 1 < i)
        {
          c2 = paramString.charAt(j + 1);
          if (j + 2 >= i) {
            break label102;
          }
          c3 = paramString.charAt(j + 2);
          label66:
          j += 2;
          localStringBuilder.append((char)(hex_character_to_value(c2) << 8 | hex_character_to_value(c3)));
        }
      }
      for (;;)
      {
        j++;
        break;
        c2 = '0';
        break label50;
        label102:
        c3 = '0';
        break label66;
        localStringBuilder.append(c1);
      }
    }
    return localStringBuilder.toString();
  }
  
  public static String url_encoded(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = paramString.length();
    int j = 0;
    if (j < i)
    {
      char c = paramString.charAt(j);
      if ((c < '') && (url_encoding_map.charAt(c) == ' ')) {
        localStringBuilder.append(c);
      }
      for (;;)
      {
        j++;
        break;
        localStringBuilder.append('%');
        int k = 0xF & c >> '\004';
        int m = c & 0xF;
        if (k < 10) {
          localStringBuilder.append((char)(k + 48));
        }
        for (;;)
        {
          if (m >= 10) {
            break label140;
          }
          localStringBuilder.append((char)(m + 48));
          break;
          localStringBuilder.append((char)(-10 + (k + 65)));
        }
        label140:
        localStringBuilder.append((char)(-10 + (m + 65)));
      }
    }
    return localStringBuilder.toString();
  }
  
  static boolean using_mobile()
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager)AdColony.activity().getSystemService("connectivity")).getActiveNetworkInfo();
    if (localNetworkInfo == null) {}
    int i;
    do
    {
      return false;
      i = localNetworkInfo.getType();
    } while ((i != 0) && (i < 2));
    return true;
  }
  
  static boolean using_wifi()
  {
    int i = 1;
    NetworkInfo localNetworkInfo = ((ConnectivityManager)AdColony.activity().getSystemService("connectivity")).getActiveNetworkInfo();
    if (localNetworkInfo == null) {
      return false;
    }
    if (localNetworkInfo.getType() == i) {}
    for (;;)
    {
      return i;
      int j = 0;
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.jirbo.adcolony.ADCNetwork
 * JD-Core Version:    0.7.0.1
 */