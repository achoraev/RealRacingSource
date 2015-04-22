package com.jirbo.adcolony;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class AeSimpleSHA1
{
  private static String convertToHex(byte[] paramArrayOfByte)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = paramArrayOfByte.length;
    int j = 0;
    int k;
    int m;
    if (j < i)
    {
      k = paramArrayOfByte[j];
      m = 0xF & k >>> 4;
    }
    int i1;
    label106:
    for (int n = 0;; n = i1)
    {
      if ((m >= 0) && (m <= 9)) {}
      for (char c = (char)(m + 48);; c = (char)(97 + (m - 10)))
      {
        localStringBuilder.append(c);
        m = k & 0xF;
        i1 = n + 1;
        if (n < 1) {
          break label106;
        }
        j++;
        break;
      }
      return localStringBuilder.toString();
    }
  }
  
  public static String sha1(String paramString)
    throws NoSuchAlgorithmException, UnsupportedEncodingException
  {
    MessageDigest localMessageDigest = MessageDigest.getInstance("SHA-1");
    localMessageDigest.update(paramString.getBytes("iso-8859-1"), 0, paramString.length());
    return convertToHex(localMessageDigest.digest());
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.jirbo.adcolony.AeSimpleSHA1
 * JD-Core Version:    0.7.0.1
 */