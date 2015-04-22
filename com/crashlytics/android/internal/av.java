package com.crashlytics.android.internal;

import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import javax.security.auth.x500.X500Principal;

public class av
{
  private final q a;
  private aG b;
  private SSLSocketFactory c;
  private boolean d;
  
  public av()
  {
    this(new r());
  }
  
  public av(q paramq)
  {
    this.a = paramq;
  }
  
  private void a()
  {
    try
    {
      this.d = false;
      this.c = null;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  private static boolean a(X509Certificate paramX509Certificate1, X509Certificate paramX509Certificate2)
  {
    if (!paramX509Certificate1.getSubjectX500Principal().equals(paramX509Certificate2.getIssuerX500Principal())) {
      return false;
    }
    try
    {
      paramX509Certificate2.verify(paramX509Certificate1.getPublicKey());
      return true;
    }
    catch (GeneralSecurityException localGeneralSecurityException) {}
    return false;
  }
  
  public static X509Certificate[] a(X509Certificate[] paramArrayOfX509Certificate, aI paramaI)
    throws CertificateException
  {
    int i = 1;
    LinkedList localLinkedList = new LinkedList();
    if (paramaI.a(paramArrayOfX509Certificate[0])) {}
    for (int j = i;; j = 0)
    {
      localLinkedList.add(paramArrayOfX509Certificate[0]);
      int k = j;
      for (int m = i; m < paramArrayOfX509Certificate.length; m++)
      {
        if (paramaI.a(paramArrayOfX509Certificate[m])) {
          k = i;
        }
        if (!a(paramArrayOfX509Certificate[m], paramArrayOfX509Certificate[(m - 1)])) {
          break;
        }
        localLinkedList.add(paramArrayOfX509Certificate[m]);
      }
      X509Certificate localX509Certificate = paramaI.b(paramArrayOfX509Certificate[(m - 1)]);
      if (localX509Certificate != null) {
        localLinkedList.add(localX509Certificate);
      }
      for (;;)
      {
        if (i != 0) {
          return (X509Certificate[])localLinkedList.toArray(new X509Certificate[localLinkedList.size()]);
        }
        throw new CertificateException("Didn't find a trust anchor in chain cleanup!");
        i = k;
      }
    }
  }
  
  private SSLSocketFactory b()
  {
    try
    {
      if ((this.c == null) && (!this.d)) {
        this.c = c();
      }
      SSLSocketFactory localSSLSocketFactory = this.c;
      return localSSLSocketFactory;
    }
    finally {}
  }
  
  /* Error */
  private SSLSocketFactory c()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: iconst_1
    //   4: putfield 26	com/crashlytics/android/internal/av:d	Z
    //   7: aload_0
    //   8: getfield 95	com/crashlytics/android/internal/av:b	Lcom/crashlytics/android/internal/aG;
    //   11: astore 4
    //   13: ldc 97
    //   15: invokestatic 103	javax/net/ssl/SSLContext:getInstance	(Ljava/lang/String;)Ljavax/net/ssl/SSLContext;
    //   18: astore 5
    //   20: aload 5
    //   22: aconst_null
    //   23: iconst_1
    //   24: anewarray 105	javax/net/ssl/TrustManager
    //   27: dup
    //   28: iconst_0
    //   29: new 107	com/crashlytics/android/internal/aH
    //   32: dup
    //   33: new 62	com/crashlytics/android/internal/aI
    //   36: dup
    //   37: aload 4
    //   39: invokeinterface 112 1 0
    //   44: aload 4
    //   46: invokeinterface 115 1 0
    //   51: invokespecial 118	com/crashlytics/android/internal/aI:<init>	(Ljava/io/InputStream;Ljava/lang/String;)V
    //   54: aload 4
    //   56: invokespecial 121	com/crashlytics/android/internal/aH:<init>	(Lcom/crashlytics/android/internal/aI;Lcom/crashlytics/android/internal/aG;)V
    //   59: aastore
    //   60: aconst_null
    //   61: invokevirtual 125	javax/net/ssl/SSLContext:init	([Ljavax/net/ssl/KeyManager;[Ljavax/net/ssl/TrustManager;Ljava/security/SecureRandom;)V
    //   64: aload 5
    //   66: invokevirtual 128	javax/net/ssl/SSLContext:getSocketFactory	()Ljavax/net/ssl/SSLSocketFactory;
    //   69: astore_3
    //   70: aload_0
    //   71: getfield 24	com/crashlytics/android/internal/av:a	Lcom/crashlytics/android/internal/q;
    //   74: ldc 130
    //   76: ldc 132
    //   78: invokeinterface 137 3 0
    //   83: aload_0
    //   84: monitorexit
    //   85: aload_3
    //   86: areturn
    //   87: astore_2
    //   88: aload_0
    //   89: getfield 24	com/crashlytics/android/internal/av:a	Lcom/crashlytics/android/internal/q;
    //   92: ldc 130
    //   94: ldc 139
    //   96: aload_2
    //   97: invokeinterface 142 4 0
    //   102: aconst_null
    //   103: astore_3
    //   104: goto -21 -> 83
    //   107: astore_1
    //   108: aload_0
    //   109: monitorexit
    //   110: aload_1
    //   111: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	112	0	this	av
    //   107	4	1	localObject	Object
    //   87	10	2	localException	java.lang.Exception
    //   69	35	3	localSSLSocketFactory	SSLSocketFactory
    //   11	44	4	localaG	aG
    //   18	47	5	localSSLContext	javax.net.ssl.SSLContext
    // Exception table:
    //   from	to	target	type
    //   7	83	87	java/lang/Exception
    //   2	7	107	finally
    //   7	83	107	finally
    //   88	102	107	finally
  }
  
  public ay a(ax paramax, String paramString)
  {
    return a(paramax, paramString, Collections.emptyMap());
  }
  
  public ay a(ax paramax, String paramString, Map<String, String> paramMap)
  {
    ay localay;
    switch (aw.a[paramax.ordinal()])
    {
    default: 
      throw new IllegalArgumentException("Unsupported HTTP method!");
    case 1: 
      localay = ay.a(paramString, paramMap, true);
      if (paramString != null) {
        break;
      }
    }
    for (boolean bool = false;; bool = paramString.toLowerCase().startsWith("https"))
    {
      if ((bool) && (this.b != null))
      {
        SSLSocketFactory localSSLSocketFactory = b();
        if (localSSLSocketFactory != null) {
          ((HttpsURLConnection)localay.a()).setSSLSocketFactory(localSSLSocketFactory);
        }
      }
      return localay;
      localay = ay.b(paramString, paramMap, true);
      break;
      localay = ay.a(paramString);
      break;
      localay = ay.b(paramString);
      break;
    }
  }
  
  public void a(aG paramaG)
  {
    if (this.b != paramaG)
    {
      this.b = paramaG;
      a();
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.crashlytics.android.internal.av
 * JD-Core Version:    0.7.0.1
 */