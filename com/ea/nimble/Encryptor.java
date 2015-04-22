package com.ea.nimble;

import android.annotation.SuppressLint;
import android.os.Build.VERSION;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.NetworkInterface;
import java.security.GeneralSecurityException;
import java.security.Provider;
import java.security.Provider.Service;
import java.security.Security;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

class Encryptor
{
  private static int ENCRYPTION_KEY_LENGHT = 128;
  private static int ENCRYPTION_KEY_ROUND = 997;
  private Cipher m_decryptor = null;
  private Cipher m_encryptor = null;
  
  @SuppressLint({"NewApi"})
  private void initialize()
    throws GeneralSecurityException
  {
    for (Provider localProvider : )
    {
      Log.Helper.LOGIS(null, "Cryptor Provider: " + localProvider.getName(), new Object[0]);
      Iterator localIterator2 = localProvider.getServices().iterator();
      while (localIterator2.hasNext())
      {
        Provider.Service localService = (Provider.Service)localIterator2.next();
        Log.Helper.LOGIS(null, "Cryptor Algorithm: " + localService.getAlgorithm(), new Object[0]);
      }
    }
    try
    {
      IApplicationEnvironment localIApplicationEnvironment = ApplicationEnvironment.getComponent();
      String str1 = localIApplicationEnvironment.getApplicationBundleId();
      Object localObject = localIApplicationEnvironment.getMACAddress();
      if (localObject == null)
      {
        int k = Build.VERSION.SDK_INT;
        if (k >= 9) {
          try
          {
            Iterator localIterator1 = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
            while (localIterator1.hasNext())
            {
              byte[] arrayOfByte3 = ((NetworkInterface)localIterator1.next()).getHardwareAddress();
              if (arrayOfByte3 != null)
              {
                StringBuilder localStringBuilder = new StringBuilder();
                for (int i1 = 0; i1 < arrayOfByte3.length; i1++)
                {
                  Object[] arrayOfObject = new Object[1];
                  arrayOfObject[0] = Byte.valueOf(arrayOfByte3[i1]);
                  localStringBuilder.append(String.format("%02X:", arrayOfObject));
                }
                if (localStringBuilder.length() > 0) {
                  localStringBuilder.deleteCharAt(-1 + localStringBuilder.length());
                }
                String str2 = localStringBuilder.toString();
                localObject = str2;
              }
            }
            arrayOfByte1 = ((String)localObject).getBytes();
          }
          catch (Exception localException) {}
        }
      }
      byte[] arrayOfByte1;
      byte[] arrayOfByte2 = new byte[8];
      int m = 0;
      for (int n = 0; m < arrayOfByte2.length; n++)
      {
        if ((n + 1) % 3 == 0) {
          n++;
        }
        arrayOfByte2[m] = arrayOfByte1[n];
        m++;
      }
      SecretKeyFactory localSecretKeyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
      PBEKeySpec localPBEKeySpec = new PBEKeySpec(str1.toCharArray(), arrayOfByte2, ENCRYPTION_KEY_ROUND, ENCRYPTION_KEY_LENGHT);
      SecretKey localSecretKey = localSecretKeyFactory.generateSecret(localPBEKeySpec);
      PBEParameterSpec localPBEParameterSpec = new PBEParameterSpec(arrayOfByte2, ENCRYPTION_KEY_ROUND);
      this.m_encryptor = Cipher.getInstance("PBEWithMD5AndDES");
      this.m_encryptor.init(1, localSecretKey, localPBEParameterSpec);
      this.m_decryptor = Cipher.getInstance("PBEWithMD5AndDES");
      this.m_decryptor.init(2, localSecretKey, localPBEParameterSpec);
      return;
    }
    catch (GeneralSecurityException localGeneralSecurityException)
    {
      Log.Helper.LOGFS(null, "Can't initialize Encryptor: " + localGeneralSecurityException.toString(), new Object[0]);
      throw localGeneralSecurityException;
    }
  }
  
  public ObjectInputStream encryptInputStream(InputStream paramInputStream)
    throws IOException, GeneralSecurityException
  {
    if ((this.m_encryptor == null) || (this.m_encryptor == null)) {
      initialize();
    }
    return new ObjectInputStream(new CipherInputStream(paramInputStream, this.m_decryptor));
  }
  
  public ObjectOutputStream encryptOutputStream(OutputStream paramOutputStream)
    throws IOException, GeneralSecurityException
  {
    if ((this.m_encryptor == null) || (this.m_encryptor == null)) {
      initialize();
    }
    return new ObjectOutputStream(new CipherOutputStream(paramOutputStream, this.m_encryptor));
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ea.nimble.Encryptor
 * JD-Core Version:    0.7.0.1
 */