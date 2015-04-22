package com.google.ads.interactivemedia.v3.a;

import java.lang.reflect.Field;

public enum d
  implements e
{
  static
  {
    d[] arrayOfd = new d[5];
    arrayOfd[0] = a;
    arrayOfd[1] = b;
    arrayOfd[2] = c;
    arrayOfd[3] = d;
    arrayOfd[4] = e;
    f = arrayOfd;
  }
  
  private d() {}
  
  private static String a(char paramChar, String paramString, int paramInt)
  {
    if (paramInt < paramString.length()) {
      return paramChar + paramString.substring(paramInt);
    }
    return String.valueOf(paramChar);
  }
  
  private static String b(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    char c1 = paramString.charAt(0);
    if ((i >= -1 + paramString.length()) || (Character.isLetter(c1)))
    {
      if (i != paramString.length()) {
        break label66;
      }
      paramString = localStringBuilder.toString();
    }
    label66:
    while (Character.isUpperCase(c1))
    {
      return paramString;
      localStringBuilder.append(c1);
      i++;
      c1 = paramString.charAt(i);
      break;
    }
    return a(Character.toUpperCase(c1), paramString, i + 1);
  }
  
  private static String b(String paramString1, String paramString2)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    for (int i = 0; i < paramString1.length(); i++)
    {
      char c1 = paramString1.charAt(i);
      if ((Character.isUpperCase(c1)) && (localStringBuilder.length() != 0)) {
        localStringBuilder.append(paramString2);
      }
      localStringBuilder.append(c1);
    }
    return localStringBuilder.toString();
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.a.d
 * JD-Core Version:    0.7.0.1
 */