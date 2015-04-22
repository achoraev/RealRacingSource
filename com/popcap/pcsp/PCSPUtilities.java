package com.popcap.pcsp;

import android.util.Log;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class PCSPUtilities
{
  private static final String TAG = "PCSPUtilities";
  
  public static String GetMD5HexString(String paramString)
  {
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.update(paramString.getBytes());
      byte[] arrayOfByte = localMessageDigest.digest();
      StringBuffer localStringBuffer = new StringBuffer();
      int i = arrayOfByte.length;
      for (int j = 0; j < i; j++)
      {
        byte b = arrayOfByte[j];
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Byte.valueOf(b);
        localStringBuffer.append(String.format("%02X", arrayOfObject));
      }
      String str = localStringBuffer.toString();
      return str;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      Log.e("PCSPUtilities", localNoSuchAlgorithmException.toString());
    }
    return "";
  }
  
  public static String GetUUIDString()
  {
    return UUID.randomUUID().toString();
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.popcap.pcsp.PCSPUtilities
 * JD-Core Version:    0.7.0.1
 */