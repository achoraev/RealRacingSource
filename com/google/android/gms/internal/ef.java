package com.google.android.gms.internal;

import android.text.TextUtils;
import android.util.Base64;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

@ez
public class ef
{
  public static PublicKey F(String paramString)
  {
    try
    {
      byte[] arrayOfByte = Base64.decode(paramString, 0);
      PublicKey localPublicKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(arrayOfByte));
      return localPublicKey;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      throw new RuntimeException(localNoSuchAlgorithmException);
    }
    catch (InvalidKeySpecException localInvalidKeySpecException)
    {
      gs.T("Invalid key specification.");
      throw new IllegalArgumentException(localInvalidKeySpecException);
    }
  }
  
  public static boolean a(PublicKey paramPublicKey, String paramString1, String paramString2)
  {
    try
    {
      Signature localSignature = Signature.getInstance("SHA1withRSA");
      localSignature.initVerify(paramPublicKey);
      localSignature.update(paramString1.getBytes());
      if (!localSignature.verify(Base64.decode(paramString2, 0)))
      {
        gs.T("Signature verification failed.");
        return false;
      }
      return true;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      gs.T("NoSuchAlgorithmException.");
      return false;
    }
    catch (InvalidKeyException localInvalidKeyException)
    {
      gs.T("Invalid key specification.");
      return false;
    }
    catch (SignatureException localSignatureException)
    {
      gs.T("Signature exception.");
    }
    return false;
  }
  
  public static boolean b(String paramString1, String paramString2, String paramString3)
  {
    if ((TextUtils.isEmpty(paramString2)) || (TextUtils.isEmpty(paramString1)) || (TextUtils.isEmpty(paramString3)))
    {
      gs.T("Purchase verification failed: missing data.");
      return false;
    }
    return a(F(paramString1), paramString2, paramString3);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.ef
 * JD-Core Version:    0.7.0.1
 */