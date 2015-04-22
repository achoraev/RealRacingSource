package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.dynamic.e;
import java.util.Iterator;
import org.json.JSONObject;

@ez
public final class cx
  extends cu.a
{
  private final MediationAdapter qE;
  
  public cx(MediationAdapter paramMediationAdapter)
  {
    this.qE = paramMediationAdapter;
  }
  
  private Bundle a(String paramString1, int paramInt, String paramString2)
    throws RemoteException
  {
    gs.W("Server parameters: " + paramString1);
    Object localObject;
    try
    {
      localObject = new Bundle();
      if (paramString1 != null)
      {
        JSONObject localJSONObject = new JSONObject(paramString1);
        Bundle localBundle = new Bundle();
        Iterator localIterator = localJSONObject.keys();
        while (localIterator.hasNext())
        {
          String str = (String)localIterator.next();
          localBundle.putString(str, localJSONObject.getString(str));
        }
        localObject = localBundle;
      }
    }
    catch (Throwable localThrowable)
    {
      gs.d("Could not get Server Parameters Bundle.", localThrowable);
      throw new RemoteException();
    }
    if ((this.qE instanceof AdMobAdapter))
    {
      ((Bundle)localObject).putString("adJson", paramString2);
      ((Bundle)localObject).putInt("tagForChildDirectedTreatment", paramInt);
    }
    return localObject;
  }
  
  public void a(d paramd, av paramav, String paramString, cv paramcv)
    throws RemoteException
  {
    a(paramd, paramav, paramString, null, paramcv);
  }
  
  /* Error */
  public void a(d paramd, av paramav, String paramString1, String paramString2, cv paramcv)
    throws RemoteException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 14	com/google/android/gms/internal/cx:qE	Lcom/google/android/gms/ads/mediation/MediationAdapter;
    //   4: instanceof 93
    //   7: ifne +42 -> 49
    //   10: new 22	java/lang/StringBuilder
    //   13: dup
    //   14: invokespecial 23	java/lang/StringBuilder:<init>	()V
    //   17: ldc 95
    //   19: invokevirtual 29	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   22: aload_0
    //   23: getfield 14	com/google/android/gms/internal/cx:qE	Lcom/google/android/gms/ads/mediation/MediationAdapter;
    //   26: invokevirtual 101	java/lang/Object:getClass	()Ljava/lang/Class;
    //   29: invokevirtual 106	java/lang/Class:getCanonicalName	()Ljava/lang/String;
    //   32: invokevirtual 29	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   35: invokevirtual 33	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   38: invokestatic 39	com/google/android/gms/internal/gs:W	(Ljava/lang/String;)V
    //   41: new 18	android/os/RemoteException
    //   44: dup
    //   45: invokespecial 77	android/os/RemoteException:<init>	()V
    //   48: athrow
    //   49: ldc 108
    //   51: invokestatic 111	com/google/android/gms/internal/gs:S	(Ljava/lang/String;)V
    //   54: aload_0
    //   55: getfield 14	com/google/android/gms/internal/cx:qE	Lcom/google/android/gms/ads/mediation/MediationAdapter;
    //   58: checkcast 93	com/google/android/gms/ads/mediation/MediationInterstitialAdapter
    //   61: astore 7
    //   63: aload_2
    //   64: getfield 117	com/google/android/gms/internal/av:nV	Ljava/util/List;
    //   67: ifnull +117 -> 184
    //   70: new 119	java/util/HashSet
    //   73: dup
    //   74: aload_2
    //   75: getfield 117	com/google/android/gms/internal/av:nV	Ljava/util/List;
    //   78: invokespecial 122	java/util/HashSet:<init>	(Ljava/util/Collection;)V
    //   81: astore 8
    //   83: new 124	com/google/android/gms/internal/cw
    //   86: dup
    //   87: new 126	java/util/Date
    //   90: dup
    //   91: aload_2
    //   92: getfield 130	com/google/android/gms/internal/av:nT	J
    //   95: invokespecial 133	java/util/Date:<init>	(J)V
    //   98: aload_2
    //   99: getfield 137	com/google/android/gms/internal/av:nU	I
    //   102: aload 8
    //   104: aload_2
    //   105: getfield 141	com/google/android/gms/internal/av:ob	Landroid/location/Location;
    //   108: aload_2
    //   109: getfield 145	com/google/android/gms/internal/av:nW	Z
    //   112: aload_2
    //   113: getfield 148	com/google/android/gms/internal/av:nX	I
    //   116: invokespecial 151	com/google/android/gms/internal/cw:<init>	(Ljava/util/Date;ILjava/util/Set;Landroid/location/Location;ZI)V
    //   119: astore 9
    //   121: aload_2
    //   122: getfield 155	com/google/android/gms/internal/av:od	Landroid/os/Bundle;
    //   125: ifnull +65 -> 190
    //   128: aload_2
    //   129: getfield 155	com/google/android/gms/internal/av:od	Landroid/os/Bundle;
    //   132: aload 7
    //   134: invokevirtual 101	java/lang/Object:getClass	()Ljava/lang/Class;
    //   137: invokevirtual 158	java/lang/Class:getName	()Ljava/lang/String;
    //   140: invokevirtual 162	android/os/Bundle:getBundle	(Ljava/lang/String;)Landroid/os/Bundle;
    //   143: astore 10
    //   145: aload 7
    //   147: aload_1
    //   148: invokestatic 168	com/google/android/gms/dynamic/e:f	(Lcom/google/android/gms/dynamic/d;)Ljava/lang/Object;
    //   151: checkcast 170	android/content/Context
    //   154: new 172	com/google/android/gms/internal/cy
    //   157: dup
    //   158: aload 5
    //   160: invokespecial 175	com/google/android/gms/internal/cy:<init>	(Lcom/google/android/gms/internal/cv;)V
    //   163: aload_0
    //   164: aload_3
    //   165: aload_2
    //   166: getfield 148	com/google/android/gms/internal/av:nX	I
    //   169: aload 4
    //   171: invokespecial 177	com/google/android/gms/internal/cx:a	(Ljava/lang/String;ILjava/lang/String;)Landroid/os/Bundle;
    //   174: aload 9
    //   176: aload 10
    //   178: invokeinterface 181 6 0
    //   183: return
    //   184: aconst_null
    //   185: astore 8
    //   187: goto -104 -> 83
    //   190: aconst_null
    //   191: astore 10
    //   193: goto -48 -> 145
    //   196: astore 6
    //   198: ldc 183
    //   200: aload 6
    //   202: invokestatic 76	com/google/android/gms/internal/gs:d	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   205: new 18	android/os/RemoteException
    //   208: dup
    //   209: invokespecial 77	android/os/RemoteException:<init>	()V
    //   212: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	213	0	this	cx
    //   0	213	1	paramd	d
    //   0	213	2	paramav	av
    //   0	213	3	paramString1	String
    //   0	213	4	paramString2	String
    //   0	213	5	paramcv	cv
    //   196	5	6	localThrowable	Throwable
    //   61	85	7	localMediationInterstitialAdapter	MediationInterstitialAdapter
    //   81	105	8	localHashSet	java.util.HashSet
    //   119	56	9	localcw	cw
    //   143	49	10	localBundle	Bundle
    // Exception table:
    //   from	to	target	type
    //   54	83	196	java/lang/Throwable
    //   83	145	196	java/lang/Throwable
    //   145	183	196	java/lang/Throwable
  }
  
  public void a(d paramd, ay paramay, av paramav, String paramString, cv paramcv)
    throws RemoteException
  {
    a(paramd, paramay, paramav, paramString, null, paramcv);
  }
  
  /* Error */
  public void a(d paramd, ay paramay, av paramav, String paramString1, String paramString2, cv paramcv)
    throws RemoteException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 14	com/google/android/gms/internal/cx:qE	Lcom/google/android/gms/ads/mediation/MediationAdapter;
    //   4: instanceof 189
    //   7: ifne +42 -> 49
    //   10: new 22	java/lang/StringBuilder
    //   13: dup
    //   14: invokespecial 23	java/lang/StringBuilder:<init>	()V
    //   17: ldc 191
    //   19: invokevirtual 29	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   22: aload_0
    //   23: getfield 14	com/google/android/gms/internal/cx:qE	Lcom/google/android/gms/ads/mediation/MediationAdapter;
    //   26: invokevirtual 101	java/lang/Object:getClass	()Ljava/lang/Class;
    //   29: invokevirtual 106	java/lang/Class:getCanonicalName	()Ljava/lang/String;
    //   32: invokevirtual 29	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   35: invokevirtual 33	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   38: invokestatic 39	com/google/android/gms/internal/gs:W	(Ljava/lang/String;)V
    //   41: new 18	android/os/RemoteException
    //   44: dup
    //   45: invokespecial 77	android/os/RemoteException:<init>	()V
    //   48: athrow
    //   49: ldc 193
    //   51: invokestatic 111	com/google/android/gms/internal/gs:S	(Ljava/lang/String;)V
    //   54: aload_0
    //   55: getfield 14	com/google/android/gms/internal/cx:qE	Lcom/google/android/gms/ads/mediation/MediationAdapter;
    //   58: checkcast 189	com/google/android/gms/ads/mediation/MediationBannerAdapter
    //   61: astore 8
    //   63: aload_3
    //   64: getfield 117	com/google/android/gms/internal/av:nV	Ljava/util/List;
    //   67: ifnull +140 -> 207
    //   70: new 119	java/util/HashSet
    //   73: dup
    //   74: aload_3
    //   75: getfield 117	com/google/android/gms/internal/av:nV	Ljava/util/List;
    //   78: invokespecial 122	java/util/HashSet:<init>	(Ljava/util/Collection;)V
    //   81: astore 9
    //   83: new 124	com/google/android/gms/internal/cw
    //   86: dup
    //   87: new 126	java/util/Date
    //   90: dup
    //   91: aload_3
    //   92: getfield 130	com/google/android/gms/internal/av:nT	J
    //   95: invokespecial 133	java/util/Date:<init>	(J)V
    //   98: aload_3
    //   99: getfield 137	com/google/android/gms/internal/av:nU	I
    //   102: aload 9
    //   104: aload_3
    //   105: getfield 141	com/google/android/gms/internal/av:ob	Landroid/location/Location;
    //   108: aload_3
    //   109: getfield 145	com/google/android/gms/internal/av:nW	Z
    //   112: aload_3
    //   113: getfield 148	com/google/android/gms/internal/av:nX	I
    //   116: invokespecial 151	com/google/android/gms/internal/cw:<init>	(Ljava/util/Date;ILjava/util/Set;Landroid/location/Location;ZI)V
    //   119: astore 10
    //   121: aload_3
    //   122: getfield 155	com/google/android/gms/internal/av:od	Landroid/os/Bundle;
    //   125: astore 11
    //   127: aconst_null
    //   128: astore 12
    //   130: aload 11
    //   132: ifnull +20 -> 152
    //   135: aload_3
    //   136: getfield 155	com/google/android/gms/internal/av:od	Landroid/os/Bundle;
    //   139: aload 8
    //   141: invokevirtual 101	java/lang/Object:getClass	()Ljava/lang/Class;
    //   144: invokevirtual 158	java/lang/Class:getName	()Ljava/lang/String;
    //   147: invokevirtual 162	android/os/Bundle:getBundle	(Ljava/lang/String;)Landroid/os/Bundle;
    //   150: astore 12
    //   152: aload 8
    //   154: aload_1
    //   155: invokestatic 168	com/google/android/gms/dynamic/e:f	(Lcom/google/android/gms/dynamic/d;)Ljava/lang/Object;
    //   158: checkcast 170	android/content/Context
    //   161: new 172	com/google/android/gms/internal/cy
    //   164: dup
    //   165: aload 6
    //   167: invokespecial 175	com/google/android/gms/internal/cy:<init>	(Lcom/google/android/gms/internal/cv;)V
    //   170: aload_0
    //   171: aload 4
    //   173: aload_3
    //   174: getfield 148	com/google/android/gms/internal/av:nX	I
    //   177: aload 5
    //   179: invokespecial 177	com/google/android/gms/internal/cx:a	(Ljava/lang/String;ILjava/lang/String;)Landroid/os/Bundle;
    //   182: aload_2
    //   183: getfield 198	com/google/android/gms/internal/ay:width	I
    //   186: aload_2
    //   187: getfield 201	com/google/android/gms/internal/ay:height	I
    //   190: aload_2
    //   191: getfield 205	com/google/android/gms/internal/ay:of	Ljava/lang/String;
    //   194: invokestatic 210	com/google/android/gms/ads/a:a	(IILjava/lang/String;)Lcom/google/android/gms/ads/AdSize;
    //   197: aload 10
    //   199: aload 12
    //   201: invokeinterface 214 7 0
    //   206: return
    //   207: aconst_null
    //   208: astore 9
    //   210: goto -127 -> 83
    //   213: astore 7
    //   215: ldc 216
    //   217: aload 7
    //   219: invokestatic 76	com/google/android/gms/internal/gs:d	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   222: new 18	android/os/RemoteException
    //   225: dup
    //   226: invokespecial 77	android/os/RemoteException:<init>	()V
    //   229: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	230	0	this	cx
    //   0	230	1	paramd	d
    //   0	230	2	paramay	ay
    //   0	230	3	paramav	av
    //   0	230	4	paramString1	String
    //   0	230	5	paramString2	String
    //   0	230	6	paramcv	cv
    //   213	5	7	localThrowable	Throwable
    //   61	92	8	localMediationBannerAdapter	MediationBannerAdapter
    //   81	128	9	localHashSet	java.util.HashSet
    //   119	79	10	localcw	cw
    //   125	6	11	localBundle1	Bundle
    //   128	72	12	localBundle2	Bundle
    // Exception table:
    //   from	to	target	type
    //   54	83	213	java/lang/Throwable
    //   83	127	213	java/lang/Throwable
    //   135	152	213	java/lang/Throwable
    //   152	206	213	java/lang/Throwable
  }
  
  public void destroy()
    throws RemoteException
  {
    try
    {
      this.qE.onDestroy();
      return;
    }
    catch (Throwable localThrowable)
    {
      gs.d("Could not destroy adapter.", localThrowable);
      throw new RemoteException();
    }
  }
  
  public d getView()
    throws RemoteException
  {
    if (!(this.qE instanceof MediationBannerAdapter))
    {
      gs.W("MediationAdapter is not a MediationBannerAdapter: " + this.qE.getClass().getCanonicalName());
      throw new RemoteException();
    }
    try
    {
      d locald = e.k(((MediationBannerAdapter)this.qE).getBannerView());
      return locald;
    }
    catch (Throwable localThrowable)
    {
      gs.d("Could not get banner view from adapter.", localThrowable);
      throw new RemoteException();
    }
  }
  
  public void pause()
    throws RemoteException
  {
    try
    {
      this.qE.onPause();
      return;
    }
    catch (Throwable localThrowable)
    {
      gs.d("Could not pause adapter.", localThrowable);
      throw new RemoteException();
    }
  }
  
  public void resume()
    throws RemoteException
  {
    try
    {
      this.qE.onResume();
      return;
    }
    catch (Throwable localThrowable)
    {
      gs.d("Could not resume adapter.", localThrowable);
      throw new RemoteException();
    }
  }
  
  public void showInterstitial()
    throws RemoteException
  {
    if (!(this.qE instanceof MediationInterstitialAdapter))
    {
      gs.W("MediationAdapter is not a MediationInterstitialAdapter: " + this.qE.getClass().getCanonicalName());
      throw new RemoteException();
    }
    gs.S("Showing interstitial from adapter.");
    try
    {
      ((MediationInterstitialAdapter)this.qE).showInterstitial();
      return;
    }
    catch (Throwable localThrowable)
    {
      gs.d("Could not show interstitial from adapter.", localThrowable);
      throw new RemoteException();
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.cx
 * JD-Core Version:    0.7.0.1
 */