package com.firemonkeys.cloudcellapi;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.android.vending.billing.IInAppBillingService;
import com.android.vending.billing.IInAppBillingService.Stub;
import com.firemonkeys.cloudcellapi.util.IabException;
import com.firemonkeys.cloudcellapi.util.IabResult;
import com.firemonkeys.cloudcellapi.util.Inventory;
import com.firemonkeys.cloudcellapi.util.Purchase;
import com.firemonkeys.cloudcellapi.util.SkuDetails;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;

public class CC_GoogleStoreServiceV3_Class
{
  private static final String LOG_TAG = "GoogleStoreService";
  static final int RC_REQUEST = 10001;
  private static CC_GoogleStoreServiceV3_Class m_instance;
  private static long m_nInitializeCallback;
  private static long m_nProductDetailsErrorCallback;
  private static long m_nProductDetailsSucceedCallback;
  private static long m_nPurchaseErrorCallback;
  private static long m_nPurchaseSucceedCallback;
  private static long m_nRefreshStorePurchasesCallback;
  private static long m_nRestoreCallback;
  private static long m_nUserPointer;
  private static String m_sGoogleStorePublicKey;
  private static String m_sPackageName;
  static String sActivePurchaseProductId;
  private Thread consumeThread = null;
  ArrayList<String> mConsumableProductIds = null;
  Context mContext;
  IInAppBillingService mService;
  ServiceConnection mServiceConn;
  
  static
  {
    if (!CC_GoogleStoreServiceV3_Class.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      m_instance = null;
      sActivePurchaseProductId = null;
      return;
    }
  }
  
  private static native void InitializeCallback(boolean paramBoolean1, boolean paramBoolean2, long paramLong1, long paramLong2);
  
  private static native boolean IsPackAlreadyBought(String paramString);
  
  private static native void ProductDetailsErrorCallback(long paramLong1, String paramString, long paramLong2, long paramLong3);
  
  private static native void ProductDetailsSucceedCallback(SkuDetails[] paramArrayOfSkuDetails, long paramLong1, long paramLong2);
  
  private static native void PurchaseErrorCallback(String paramString1, long paramLong1, String paramString2, long paramLong2, long paramLong3);
  
  private void PurchaseSucceedCallback(Purchase paramPurchase)
  {
    PurchaseSucceedCallback(paramPurchase.getOrderId(), paramPurchase.getPackageName(), paramPurchase.getSku(), paramPurchase.getPurchaseTime(), paramPurchase.getPurchaseState(), paramPurchase.getDeveloperPayload(), paramPurchase.getOriginalJson(), paramPurchase.getSignature(), m_nPurchaseSucceedCallback, m_nUserPointer);
  }
  
  private static native void PurchaseSucceedCallback(String paramString1, String paramString2, String paramString3, long paramLong1, long paramLong2, String paramString4, String paramString5, String paramString6, long paramLong3, long paramLong4);
  
  private static native void RefreshStorePurchasesCallback(boolean paramBoolean, long paramLong1, long paramLong2);
  
  private static native void RestoreCallback(long paramLong1, String paramString, int paramInt1, int paramInt2, long paramLong2, long paramLong3);
  
  private void consume(Purchase paramPurchase)
    throws IabException
  {
    if (!paramPurchase.getItemType().equals("inapp")) {
      throw new IabException(Consts.ResponseCode.RESULT_ERROR, "Items of type '" + paramPurchase.getItemType() + "' can't be consumed.");
    }
    String str1;
    String str2;
    try
    {
      str1 = paramPurchase.getToken();
      str2 = paramPurchase.getSku();
      if ((str1 == null) || (str1.equals("")))
      {
        Consts.Logger("Can't consume " + str2 + ". No token.");
        throw new IabException(Consts.ResponseCode.RESULT_ERROR, "PurchaseInfo is missing token for sku: " + str2 + " " + paramPurchase);
      }
    }
    catch (RemoteException localRemoteException)
    {
      throw new IabException(Consts.ResponseCode.RESULT_ERROR, "Remote exception while consuming. PurchaseInfo: " + paramPurchase, localRemoteException);
    }
    Consts.Logger("Consuming sku: " + str2 + ", token: " + str1);
    Consts.ResponseCode localResponseCode = Consts.ResponseCode.valueOf(this.mService.consumePurchase(3, this.mContext.getPackageName(), str1));
    if (localResponseCode == Consts.ResponseCode.RESULT_OK)
    {
      Consts.Logger("Successfully consumed sku: " + str2);
      return;
    }
    Consts.Logger("Error consuming consuming sku " + str2 + ". " + localResponseCode.toString());
    throw new IabException(localResponseCode, "Error consuming sku " + str2);
  }
  
  private Intent getExplicitIapIntent()
  {
    List localList = this.mContext.getPackageManager().queryIntentServices(new Intent("com.android.vending.billing.InAppBillingService.BIND"), 0);
    if ((localList == null) || (localList.size() != 1)) {
      return null;
    }
    ResolveInfo localResolveInfo = (ResolveInfo)localList.get(0);
    ComponentName localComponentName = new ComponentName(localResolveInfo.serviceInfo.packageName, localResolveInfo.serviceInfo.name);
    Intent localIntent = new Intent();
    localIntent.setComponent(localComponentName);
    return localIntent;
  }
  
  static String getPublicKey()
  {
    return m_sGoogleStorePublicKey;
  }
  
  /* Error */
  public static boolean handleActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    // Byte code:
    //   0: getstatic 51	com/firemonkeys/cloudcellapi/CC_GoogleStoreServiceV3_Class:m_instance	Lcom/firemonkeys/cloudcellapi/CC_GoogleStoreServiceV3_Class;
    //   3: astore_3
    //   4: iconst_0
    //   5: istore 4
    //   7: aload_3
    //   8: ifnull +81 -> 89
    //   11: getstatic 283	com/firemonkeys/cloudcellapi/CC_GoogleStoreServiceV3_Class:m_sPackageName	Ljava/lang/String;
    //   14: astore 5
    //   16: iconst_0
    //   17: istore 4
    //   19: aload 5
    //   21: ifnull +68 -> 89
    //   24: iconst_0
    //   25: istore 4
    //   27: iload_0
    //   28: sipush 10001
    //   31: if_icmpne +58 -> 89
    //   34: new 285	com/firemonkeys/cloudcellapi/util/IabResult
    //   37: dup
    //   38: getstatic 148	com/firemonkeys/cloudcellapi/Consts$ResponseCode:RESULT_ERROR	Lcom/firemonkeys/cloudcellapi/Consts$ResponseCode;
    //   41: ldc_w 287
    //   44: invokespecial 288	com/firemonkeys/cloudcellapi/util/IabResult:<init>	(Lcom/firemonkeys/cloudcellapi/Consts$ResponseCode;Ljava/lang/String;)V
    //   47: pop
    //   48: aconst_null
    //   49: astore 7
    //   51: aload_2
    //   52: ifnonnull +40 -> 92
    //   55: ldc_w 290
    //   58: invokestatic 180	com/firemonkeys/cloudcellapi/Consts:Logger	(Ljava/lang/String;)V
    //   61: new 285	com/firemonkeys/cloudcellapi/util/IabResult
    //   64: dup
    //   65: getstatic 148	com/firemonkeys/cloudcellapi/Consts$ResponseCode:RESULT_ERROR	Lcom/firemonkeys/cloudcellapi/Consts$ResponseCode;
    //   68: ldc_w 292
    //   71: invokespecial 288	com/firemonkeys/cloudcellapi/util/IabResult:<init>	(Lcom/firemonkeys/cloudcellapi/Consts$ResponseCode;Ljava/lang/String;)V
    //   74: astore 11
    //   76: iconst_1
    //   77: istore 4
    //   79: getstatic 51	com/firemonkeys/cloudcellapi/CC_GoogleStoreServiceV3_Class:m_instance	Lcom/firemonkeys/cloudcellapi/CC_GoogleStoreServiceV3_Class;
    //   82: aload 11
    //   84: aload 7
    //   86: invokevirtual 296	com/firemonkeys/cloudcellapi/CC_GoogleStoreServiceV3_Class:onPurchaseFinished	(Lcom/firemonkeys/cloudcellapi/util/IabResult;Lcom/firemonkeys/cloudcellapi/util/Purchase;)V
    //   89: iload 4
    //   91: ireturn
    //   92: aload_2
    //   93: invokestatic 300	com/firemonkeys/cloudcellapi/Consts:getResponseCodeFromIntent	(Landroid/content/Intent;)Lcom/firemonkeys/cloudcellapi/Consts$ResponseCode;
    //   96: astore 8
    //   98: aload_2
    //   99: ldc_w 302
    //   102: invokevirtual 306	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   105: astore 9
    //   107: aload_2
    //   108: ldc_w 308
    //   111: invokevirtual 306	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   114: astore 10
    //   116: iload_1
    //   117: iconst_m1
    //   118: if_icmpne +325 -> 443
    //   121: aload 8
    //   123: getstatic 216	com/firemonkeys/cloudcellapi/Consts$ResponseCode:RESULT_OK	Lcom/firemonkeys/cloudcellapi/Consts$ResponseCode;
    //   126: if_acmpne +317 -> 443
    //   129: ldc_w 310
    //   132: invokestatic 180	com/firemonkeys/cloudcellapi/Consts:Logger	(Ljava/lang/String;)V
    //   135: new 150	java/lang/StringBuilder
    //   138: dup
    //   139: invokespecial 151	java/lang/StringBuilder:<init>	()V
    //   142: ldc_w 312
    //   145: invokevirtual 157	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   148: aload 9
    //   150: invokevirtual 157	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   153: invokevirtual 162	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   156: invokestatic 180	com/firemonkeys/cloudcellapi/Consts:Logger	(Ljava/lang/String;)V
    //   159: new 150	java/lang/StringBuilder
    //   162: dup
    //   163: invokespecial 151	java/lang/StringBuilder:<init>	()V
    //   166: ldc_w 314
    //   169: invokevirtual 157	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   172: aload 10
    //   174: invokevirtual 157	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   177: invokevirtual 162	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   180: invokestatic 180	com/firemonkeys/cloudcellapi/Consts:Logger	(Ljava/lang/String;)V
    //   183: new 150	java/lang/StringBuilder
    //   186: dup
    //   187: invokespecial 151	java/lang/StringBuilder:<init>	()V
    //   190: ldc_w 316
    //   193: invokevirtual 157	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   196: aload_2
    //   197: invokevirtual 320	android/content/Intent:getExtras	()Landroid/os/Bundle;
    //   200: invokevirtual 187	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   203: invokevirtual 162	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   206: invokestatic 180	com/firemonkeys/cloudcellapi/Consts:Logger	(Ljava/lang/String;)V
    //   209: ldc_w 322
    //   212: invokestatic 180	com/firemonkeys/cloudcellapi/Consts:Logger	(Ljava/lang/String;)V
    //   215: aload 9
    //   217: ifnull +8 -> 225
    //   220: aload 10
    //   222: ifnonnull +62 -> 284
    //   225: ldc_w 324
    //   228: invokestatic 180	com/firemonkeys/cloudcellapi/Consts:Logger	(Ljava/lang/String;)V
    //   231: new 150	java/lang/StringBuilder
    //   234: dup
    //   235: invokespecial 151	java/lang/StringBuilder:<init>	()V
    //   238: ldc_w 316
    //   241: invokevirtual 157	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   244: aload_2
    //   245: invokevirtual 320	android/content/Intent:getExtras	()Landroid/os/Bundle;
    //   248: invokevirtual 327	android/os/Bundle:toString	()Ljava/lang/String;
    //   251: invokevirtual 157	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   254: invokevirtual 162	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   257: invokestatic 180	com/firemonkeys/cloudcellapi/Consts:Logger	(Ljava/lang/String;)V
    //   260: new 285	com/firemonkeys/cloudcellapi/util/IabResult
    //   263: dup
    //   264: getstatic 148	com/firemonkeys/cloudcellapi/Consts$ResponseCode:RESULT_ERROR	Lcom/firemonkeys/cloudcellapi/Consts$ResponseCode;
    //   267: ldc_w 329
    //   270: invokespecial 288	com/firemonkeys/cloudcellapi/util/IabResult:<init>	(Lcom/firemonkeys/cloudcellapi/Consts$ResponseCode;Ljava/lang/String;)V
    //   273: astore 11
    //   275: iconst_1
    //   276: istore 4
    //   278: aconst_null
    //   279: astore 7
    //   281: goto -202 -> 79
    //   284: new 74	com/firemonkeys/cloudcellapi/util/Purchase
    //   287: dup
    //   288: ldc 136
    //   290: aload 9
    //   292: aload 10
    //   294: invokespecial 332	com/firemonkeys/cloudcellapi/util/Purchase:<init>	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   297: astore 14
    //   299: aload 14
    //   301: invokevirtual 84	com/firemonkeys/cloudcellapi/util/Purchase:getSku	()Ljava/lang/String;
    //   304: astore 16
    //   306: invokestatic 334	com/firemonkeys/cloudcellapi/CC_GoogleStoreServiceV3_Class:getPublicKey	()Ljava/lang/String;
    //   309: aload 9
    //   311: aload 10
    //   313: invokestatic 340	com/firemonkeys/cloudcellapi/util/Security:verifyPurchase	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   316: ifne +66 -> 382
    //   319: new 150	java/lang/StringBuilder
    //   322: dup
    //   323: invokespecial 151	java/lang/StringBuilder:<init>	()V
    //   326: ldc_w 342
    //   329: invokevirtual 157	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   332: aload 16
    //   334: invokevirtual 157	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   337: invokevirtual 162	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   340: invokestatic 180	com/firemonkeys/cloudcellapi/Consts:Logger	(Ljava/lang/String;)V
    //   343: new 285	com/firemonkeys/cloudcellapi/util/IabResult
    //   346: dup
    //   347: getstatic 148	com/firemonkeys/cloudcellapi/Consts$ResponseCode:RESULT_ERROR	Lcom/firemonkeys/cloudcellapi/Consts$ResponseCode;
    //   350: new 150	java/lang/StringBuilder
    //   353: dup
    //   354: invokespecial 151	java/lang/StringBuilder:<init>	()V
    //   357: ldc_w 344
    //   360: invokevirtual 157	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   363: aload 16
    //   365: invokevirtual 157	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   368: invokevirtual 162	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   371: invokespecial 288	com/firemonkeys/cloudcellapi/util/IabResult:<init>	(Lcom/firemonkeys/cloudcellapi/Consts$ResponseCode;Ljava/lang/String;)V
    //   374: astore 11
    //   376: iconst_1
    //   377: istore 4
    //   379: goto +306 -> 685
    //   382: ldc_w 346
    //   385: invokestatic 180	com/firemonkeys/cloudcellapi/Consts:Logger	(Ljava/lang/String;)V
    //   388: new 285	com/firemonkeys/cloudcellapi/util/IabResult
    //   391: dup
    //   392: getstatic 216	com/firemonkeys/cloudcellapi/Consts$ResponseCode:RESULT_OK	Lcom/firemonkeys/cloudcellapi/Consts$ResponseCode;
    //   395: ldc_w 348
    //   398: invokespecial 288	com/firemonkeys/cloudcellapi/util/IabResult:<init>	(Lcom/firemonkeys/cloudcellapi/Consts$ResponseCode;Ljava/lang/String;)V
    //   401: astore 11
    //   403: iconst_1
    //   404: istore 4
    //   406: goto +279 -> 685
    //   409: astore 15
    //   411: ldc_w 350
    //   414: invokestatic 180	com/firemonkeys/cloudcellapi/Consts:Logger	(Ljava/lang/String;)V
    //   417: aload 15
    //   419: invokevirtual 353	org/json/JSONException:printStackTrace	()V
    //   422: new 285	com/firemonkeys/cloudcellapi/util/IabResult
    //   425: dup
    //   426: getstatic 148	com/firemonkeys/cloudcellapi/Consts$ResponseCode:RESULT_ERROR	Lcom/firemonkeys/cloudcellapi/Consts$ResponseCode;
    //   429: ldc_w 350
    //   432: invokespecial 288	com/firemonkeys/cloudcellapi/util/IabResult:<init>	(Lcom/firemonkeys/cloudcellapi/Consts$ResponseCode;Ljava/lang/String;)V
    //   435: astore 11
    //   437: iconst_1
    //   438: istore 4
    //   440: goto -361 -> 79
    //   443: iload_1
    //   444: iconst_m1
    //   445: if_icmpne +103 -> 548
    //   448: new 150	java/lang/StringBuilder
    //   451: dup
    //   452: invokespecial 151	java/lang/StringBuilder:<init>	()V
    //   455: ldc_w 355
    //   458: invokevirtual 157	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   461: aload 8
    //   463: invokevirtual 223	com/firemonkeys/cloudcellapi/Consts$ResponseCode:toString	()Ljava/lang/String;
    //   466: invokevirtual 157	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   469: invokevirtual 162	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   472: invokestatic 180	com/firemonkeys/cloudcellapi/Consts:Logger	(Ljava/lang/String;)V
    //   475: new 74	com/firemonkeys/cloudcellapi/util/Purchase
    //   478: dup
    //   479: ldc 136
    //   481: aload 9
    //   483: aload 10
    //   485: invokespecial 332	com/firemonkeys/cloudcellapi/util/Purchase:<init>	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   488: astore 12
    //   490: new 285	com/firemonkeys/cloudcellapi/util/IabResult
    //   493: dup
    //   494: aload 8
    //   496: ldc_w 357
    //   499: invokespecial 288	com/firemonkeys/cloudcellapi/util/IabResult:<init>	(Lcom/firemonkeys/cloudcellapi/Consts$ResponseCode;Ljava/lang/String;)V
    //   502: astore 11
    //   504: iconst_1
    //   505: istore 4
    //   507: aload 12
    //   509: astore 7
    //   511: goto -432 -> 79
    //   514: astore 13
    //   516: ldc_w 350
    //   519: invokestatic 180	com/firemonkeys/cloudcellapi/Consts:Logger	(Ljava/lang/String;)V
    //   522: aload 13
    //   524: invokevirtual 353	org/json/JSONException:printStackTrace	()V
    //   527: new 285	com/firemonkeys/cloudcellapi/util/IabResult
    //   530: dup
    //   531: getstatic 148	com/firemonkeys/cloudcellapi/Consts$ResponseCode:RESULT_ERROR	Lcom/firemonkeys/cloudcellapi/Consts$ResponseCode;
    //   534: ldc_w 350
    //   537: invokespecial 288	com/firemonkeys/cloudcellapi/util/IabResult:<init>	(Lcom/firemonkeys/cloudcellapi/Consts$ResponseCode;Ljava/lang/String;)V
    //   540: astore 11
    //   542: iconst_1
    //   543: istore 4
    //   545: goto -466 -> 79
    //   548: iload_1
    //   549: ifne +54 -> 603
    //   552: new 150	java/lang/StringBuilder
    //   555: dup
    //   556: invokespecial 151	java/lang/StringBuilder:<init>	()V
    //   559: ldc_w 359
    //   562: invokevirtual 157	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   565: aload 8
    //   567: invokevirtual 223	com/firemonkeys/cloudcellapi/Consts$ResponseCode:toString	()Ljava/lang/String;
    //   570: invokevirtual 157	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   573: invokevirtual 162	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   576: invokestatic 180	com/firemonkeys/cloudcellapi/Consts:Logger	(Ljava/lang/String;)V
    //   579: new 285	com/firemonkeys/cloudcellapi/util/IabResult
    //   582: dup
    //   583: getstatic 362	com/firemonkeys/cloudcellapi/Consts$ResponseCode:RESULT_USER_CANCELED	Lcom/firemonkeys/cloudcellapi/Consts$ResponseCode;
    //   586: ldc_w 364
    //   589: invokespecial 288	com/firemonkeys/cloudcellapi/util/IabResult:<init>	(Lcom/firemonkeys/cloudcellapi/Consts$ResponseCode;Ljava/lang/String;)V
    //   592: astore 11
    //   594: iconst_1
    //   595: istore 4
    //   597: aconst_null
    //   598: astore 7
    //   600: goto -521 -> 79
    //   603: new 150	java/lang/StringBuilder
    //   606: dup
    //   607: invokespecial 151	java/lang/StringBuilder:<init>	()V
    //   610: ldc_w 366
    //   613: invokevirtual 157	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   616: iload_1
    //   617: invokestatic 371	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   620: invokevirtual 157	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   623: ldc_w 373
    //   626: invokevirtual 157	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   629: aload 8
    //   631: invokevirtual 223	com/firemonkeys/cloudcellapi/Consts$ResponseCode:toString	()Ljava/lang/String;
    //   634: invokevirtual 157	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   637: invokevirtual 162	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   640: invokestatic 180	com/firemonkeys/cloudcellapi/Consts:Logger	(Ljava/lang/String;)V
    //   643: new 285	com/firemonkeys/cloudcellapi/util/IabResult
    //   646: dup
    //   647: getstatic 148	com/firemonkeys/cloudcellapi/Consts$ResponseCode:RESULT_ERROR	Lcom/firemonkeys/cloudcellapi/Consts$ResponseCode;
    //   650: ldc_w 375
    //   653: invokespecial 288	com/firemonkeys/cloudcellapi/util/IabResult:<init>	(Lcom/firemonkeys/cloudcellapi/Consts$ResponseCode;Ljava/lang/String;)V
    //   656: astore 11
    //   658: iconst_1
    //   659: istore 4
    //   661: aconst_null
    //   662: astore 7
    //   664: goto -585 -> 79
    //   667: astore 13
    //   669: aload 12
    //   671: astore 7
    //   673: goto -157 -> 516
    //   676: astore 15
    //   678: aload 14
    //   680: astore 7
    //   682: goto -271 -> 411
    //   685: aload 14
    //   687: astore 7
    //   689: goto -610 -> 79
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	692	0	paramInt1	int
    //   0	692	1	paramInt2	int
    //   0	692	2	paramIntent	Intent
    //   3	5	3	localCC_GoogleStoreServiceV3_Class	CC_GoogleStoreServiceV3_Class
    //   5	655	4	bool	boolean
    //   14	6	5	str1	String
    //   49	639	7	localObject	Object
    //   96	534	8	localResponseCode	Consts.ResponseCode
    //   105	377	9	str2	String
    //   114	370	10	str3	String
    //   74	583	11	localIabResult	IabResult
    //   488	182	12	localPurchase1	Purchase
    //   514	9	13	localJSONException1	JSONException
    //   667	1	13	localJSONException2	JSONException
    //   297	389	14	localPurchase2	Purchase
    //   409	9	15	localJSONException3	JSONException
    //   676	1	15	localJSONException4	JSONException
    //   304	60	16	str4	String
    // Exception table:
    //   from	to	target	type
    //   284	299	409	org/json/JSONException
    //   475	490	514	org/json/JSONException
    //   490	504	667	org/json/JSONException
    //   299	376	676	org/json/JSONException
    //   382	403	676	org/json/JSONException
  }
  
  private boolean isProductConsumable(String paramString)
  {
    if (this.mConsumableProductIds != null)
    {
      Iterator localIterator = this.mConsumableProductIds.iterator();
      do
      {
        if (!localIterator.hasNext()) {
          break;
        }
      } while (!paramString.equals((String)localIterator.next()));
      return true;
    }
    Consts.Logger("mConsumableProductIds is not inialized yet");
    return false;
  }
  
  private void runConsume(IabResult paramIabResult, final Purchase paramPurchase)
  {
    CC_Activity.GetActivity().runOnUiThread(new Runnable()
    {
      public void run()
      {
        Consts.Logger("Send consume request.");
        final Handler localHandler = new Handler();
        if ((CC_GoogleStoreServiceV3_Class.this.consumeThread == null) || (!CC_GoogleStoreServiceV3_Class.this.consumeThread.isAlive()) || (CC_GoogleStoreServiceV3_Class.this.consumeThread.isInterrupted()))
        {
          CC_GoogleStoreServiceV3_Class.access$102(CC_GoogleStoreServiceV3_Class.this, new Thread(new Runnable()
          {
            public void run()
            {
              final ArrayList localArrayList = new ArrayList();
              try
              {
                CC_GoogleStoreServiceV3_Class.this.consume(CC_GoogleStoreServiceV3_Class.5.this.val$purchase);
                localArrayList.add(new IabResult(Consts.ResponseCode.RESULT_OK, "Successful consume of sku " + CC_GoogleStoreServiceV3_Class.5.this.val$purchase.getSku()));
                localHandler.post(new Runnable()
                {
                  public void run()
                  {
                    CC_GoogleStoreServiceV3_Class.this.onConsumeFinished(CC_GoogleStoreServiceV3_Class.5.this.val$purchase, (IabResult)localArrayList.get(0));
                  }
                });
                return;
              }
              catch (IabException localIabException)
              {
                for (;;)
                {
                  localArrayList.add(localIabException.getResult());
                }
              }
            }
          }));
          CC_GoogleStoreServiceV3_Class.this.consumeThread.start();
          return;
        }
        Consts.Logger("Attempt to start consume when previous consume still running. OrderId: " + paramPurchase.getOrderId() + ", sku: " + paramPurchase.getSku() + ", token: " + paramPurchase.getToken());
      }
    });
  }
  
  public void Constructor(String paramString, long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6, long paramLong7, long paramLong8)
  {
    Activity localActivity = CC_Activity.GetActivity();
    this.mContext = localActivity.getApplicationContext();
    m_sGoogleStorePublicKey = paramString;
    m_nInitializeCallback = paramLong1;
    m_nProductDetailsSucceedCallback = paramLong2;
    m_nProductDetailsErrorCallback = paramLong3;
    m_nPurchaseSucceedCallback = paramLong4;
    m_nPurchaseErrorCallback = paramLong5;
    m_nRestoreCallback = paramLong6;
    m_nRefreshStorePurchasesCallback = paramLong7;
    m_nUserPointer = paramLong8;
    m_sPackageName = localActivity.getPackageName();
    m_instance = this;
  }
  
  public void Destructor()
  {
    m_instance = null;
  }
  
  public void Initialize()
  {
    Activity localActivity = CC_Activity.GetActivity();
    if (localActivity != null) {
      localActivity.runOnUiThread(new Runnable()
      {
        public void run()
        {
          CC_GoogleStoreServiceV3_Class.this.mServiceConn = new ServiceConnection()
          {
            public void onServiceConnected(ComponentName paramAnonymous2ComponentName, IBinder paramAnonymous2IBinder)
            {
              Log.d("GoogleStoreService", "Billing service connected.");
              CC_GoogleStoreServiceV3_Class.this.mService = IInAppBillingService.Stub.asInterface(paramAnonymous2IBinder);
              String str = CC_GoogleStoreServiceV3_Class.this.mContext.getPackageName();
              try
              {
                Log.d("GoogleStoreService", "Checking for in-app billing 3 support.");
                Consts.ResponseCode localResponseCode = Consts.ResponseCode.valueOf(CC_GoogleStoreServiceV3_Class.this.mService.isBillingSupported(3, str, "inapp"));
                if (localResponseCode != Consts.ResponseCode.RESULT_OK)
                {
                  CC_GoogleStoreServiceV3_Class.this.onIabSetupFinished(new IabResult(localResponseCode, "Error checking for billing v3 support."));
                  return;
                }
                Log.d("GoogleStoreService", "In-app billing version 3 supported for " + str);
                CC_GoogleStoreServiceV3_Class.this.onIabSetupFinished(new IabResult(Consts.ResponseCode.RESULT_OK, "Setup successful."));
                return;
              }
              catch (RemoteException localRemoteException)
              {
                CC_GoogleStoreServiceV3_Class.this.onIabSetupFinished(new IabResult(Consts.ResponseCode.RESULT_ERROR, "RemoteException while setting up in-app billing."));
                localRemoteException.printStackTrace();
              }
            }
            
            public void onServiceDisconnected(ComponentName paramAnonymous2ComponentName)
            {
              Log.d("GoogleStoreService", "Billing service disconnected.");
              CC_GoogleStoreServiceV3_Class.this.mService = null;
            }
          };
          Intent localIntent = CC_GoogleStoreServiceV3_Class.this.getExplicitIapIntent();
          if (!CC_GoogleStoreServiceV3_Class.this.mContext.getPackageManager().queryIntentServices(localIntent, 0).isEmpty())
          {
            CC_GoogleStoreServiceV3_Class.this.mContext.bindService(localIntent, CC_GoogleStoreServiceV3_Class.this.mServiceConn, 1);
            return;
          }
          CC_GoogleStoreServiceV3_Class.this.onIabSetupFinished(new IabResult(Consts.ResponseCode.RESULT_BILLING_UNAVAILABLE, "Billing service unavailable on device."));
        }
      });
    }
  }
  
  public void Purchase(final String paramString, long paramLong1, long paramLong2)
  {
    Consts.Logger("Purchase");
    final Activity localActivity = CC_Activity.GetActivity();
    localActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        IabResult localIabResult = new IabResult(Consts.ResponseCode.RESULT_OK, "Purchase successful.");
        CC_GoogleStoreServiceV3_Class.sActivePurchaseProductId = paramString;
        try
        {
          Consts.Logger("Constructing buy intent for " + paramString + ", item type: " + "inapp");
          Bundle localBundle = CC_GoogleStoreServiceV3_Class.this.mService.getBuyIntent(3, CC_GoogleStoreServiceV3_Class.this.mContext.getPackageName(), paramString, "inapp", "");
          Consts.ResponseCode localResponseCode = Consts.getResponseCodeFromBundle(localBundle);
          if (localResponseCode != Consts.ResponseCode.RESULT_OK)
          {
            Consts.Logger("Unable to buy item, Error response: " + localResponseCode.toString());
            localIabResult = new IabResult(localResponseCode, "Unable to buy item");
          }
          for (;;)
          {
            if (localIabResult.isFailure()) {
              CC_GoogleStoreServiceV3_Class.this.onPurchaseFinished(localIabResult, null);
            }
            return;
            PendingIntent localPendingIntent = (PendingIntent)localBundle.getParcelable("BUY_INTENT");
            Consts.Logger("Launching buy intent for " + paramString + ". Request code: " + 10001);
            localActivity.startIntentSenderForResult(localPendingIntent.getIntentSender(), 10001, new Intent(), Integer.valueOf(0).intValue(), Integer.valueOf(0).intValue(), Integer.valueOf(0).intValue());
          }
        }
        catch (IntentSender.SendIntentException localSendIntentException)
        {
          for (;;)
          {
            Consts.Logger("SendIntentException while launching purchase flow for sku " + paramString);
            localSendIntentException.printStackTrace();
            localIabResult = new IabResult(Consts.ResponseCode.RESULT_ERROR, "Failed to send intent.");
          }
        }
        catch (RemoteException localRemoteException)
        {
          for (;;)
          {
            Consts.Logger("RemoteException while launching purchase flow for sku " + paramString);
            localRemoteException.printStackTrace();
            localIabResult = new IabResult(Consts.ResponseCode.RESULT_ERROR, "Remote exception while starting purchase flow", paramString);
          }
        }
        catch (IllegalStateException localIllegalStateException)
        {
          for (;;)
          {
            localIllegalStateException.printStackTrace();
            localIabResult = new IabResult(Consts.ResponseCode.RESULT_ERROR, localIllegalStateException.getMessage());
          }
        }
        catch (Exception localException)
        {
          for (;;)
          {
            localException.printStackTrace();
            localIabResult = new IabResult(Consts.ResponseCode.RESULT_ERROR, localException.getMessage());
          }
        }
      }
    });
  }
  
  /* Error */
  public void RefreshStorePurchases()
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_1
    //   2: aload_0
    //   3: getfield 198	com/firemonkeys/cloudcellapi/CC_GoogleStoreServiceV3_Class:mService	Lcom/android/vending/billing/IInAppBillingService;
    //   6: iconst_3
    //   7: aload_0
    //   8: getfield 200	com/firemonkeys/cloudcellapi/CC_GoogleStoreServiceV3_Class:mContext	Landroid/content/Context;
    //   11: invokevirtual 203	android/content/Context:getPackageName	()Ljava/lang/String;
    //   14: ldc 136
    //   16: aconst_null
    //   17: invokeinterface 449 5 0
    //   22: astore_3
    //   23: aload_3
    //   24: ldc_w 451
    //   27: invokevirtual 455	android/os/Bundle:getInt	(Ljava/lang/String;)I
    //   30: invokestatic 213	com/firemonkeys/cloudcellapi/Consts$ResponseCode:valueOf	(I)Lcom/firemonkeys/cloudcellapi/Consts$ResponseCode;
    //   33: getstatic 216	com/firemonkeys/cloudcellapi/Consts$ResponseCode:RESULT_OK	Lcom/firemonkeys/cloudcellapi/Consts$ResponseCode;
    //   36: if_acmpne +268 -> 304
    //   39: iconst_1
    //   40: istore_1
    //   41: aload_3
    //   42: ldc_w 457
    //   45: invokevirtual 461	android/os/Bundle:getStringArrayList	(Ljava/lang/String;)Ljava/util/ArrayList;
    //   48: astore 4
    //   50: aload_3
    //   51: ldc_w 463
    //   54: invokevirtual 461	android/os/Bundle:getStringArrayList	(Ljava/lang/String;)Ljava/util/ArrayList;
    //   57: astore 5
    //   59: aload_3
    //   60: ldc_w 465
    //   63: invokevirtual 461	android/os/Bundle:getStringArrayList	(Ljava/lang/String;)Ljava/util/ArrayList;
    //   66: astore 6
    //   68: iconst_0
    //   69: istore 7
    //   71: aload 5
    //   73: invokevirtual 466	java/util/ArrayList:size	()I
    //   76: istore 8
    //   78: iload 7
    //   80: iload 8
    //   82: if_icmpge +222 -> 304
    //   85: new 285	com/firemonkeys/cloudcellapi/util/IabResult
    //   88: getstatic 148	com/firemonkeys/cloudcellapi/Consts$ResponseCode:RESULT_ERROR	Lcom/firemonkeys/cloudcellapi/Consts$ResponseCode;
    //   91: ldc_w 468
    //   94: invokespecial 288	com/firemonkeys/cloudcellapi/util/IabResult:<init>	(Lcom/firemonkeys/cloudcellapi/Consts$ResponseCode;Ljava/lang/String;)V
    //   97: aload 5
    //   99: iload 7
    //   101: invokevirtual 469	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   104: checkcast 138	java/lang/String
    //   107: astore 10
    //   109: aload 6
    //   111: iload 7
    //   113: invokevirtual 469	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   116: checkcast 138	java/lang/String
    //   119: astore 11
    //   121: aload 4
    //   123: iload 7
    //   125: invokevirtual 469	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   128: checkcast 138	java/lang/String
    //   131: astore 12
    //   133: new 74	com/firemonkeys/cloudcellapi/util/Purchase
    //   136: dup
    //   137: ldc 136
    //   139: aload 10
    //   141: aload 11
    //   143: invokespecial 332	com/firemonkeys/cloudcellapi/util/Purchase:<init>	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   146: astore 13
    //   148: aload_0
    //   149: aload 13
    //   151: invokevirtual 84	com/firemonkeys/cloudcellapi/util/Purchase:getSku	()Ljava/lang/String;
    //   154: invokespecial 471	com/firemonkeys/cloudcellapi/CC_GoogleStoreServiceV3_Class:isProductConsumable	(Ljava/lang/String;)Z
    //   157: ifeq +165 -> 322
    //   160: invokestatic 334	com/firemonkeys/cloudcellapi/CC_GoogleStoreServiceV3_Class:getPublicKey	()Ljava/lang/String;
    //   163: aload 10
    //   165: aload 11
    //   167: invokestatic 340	com/firemonkeys/cloudcellapi/util/Security:verifyPurchase	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   170: ifne +87 -> 257
    //   173: new 150	java/lang/StringBuilder
    //   176: dup
    //   177: invokespecial 151	java/lang/StringBuilder:<init>	()V
    //   180: ldc_w 342
    //   183: invokevirtual 157	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   186: aload 12
    //   188: invokevirtual 157	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   191: invokevirtual 162	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   194: invokestatic 180	com/firemonkeys/cloudcellapi/Consts:Logger	(Ljava/lang/String;)V
    //   197: getstatic 148	com/firemonkeys/cloudcellapi/Consts$ResponseCode:RESULT_ERROR	Lcom/firemonkeys/cloudcellapi/Consts$ResponseCode;
    //   200: astore 16
    //   202: new 150	java/lang/StringBuilder
    //   205: dup
    //   206: invokespecial 151	java/lang/StringBuilder:<init>	()V
    //   209: ldc_w 344
    //   212: invokevirtual 157	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   215: aload 12
    //   217: invokevirtual 157	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   220: invokevirtual 162	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   223: astore 17
    //   225: new 285	com/firemonkeys/cloudcellapi/util/IabResult
    //   228: dup
    //   229: aload 16
    //   231: aload 17
    //   233: invokespecial 288	com/firemonkeys/cloudcellapi/util/IabResult:<init>	(Lcom/firemonkeys/cloudcellapi/Consts$ResponseCode;Ljava/lang/String;)V
    //   236: astore 15
    //   238: aload 13
    //   240: invokevirtual 84	com/firemonkeys/cloudcellapi/util/Purchase:getSku	()Ljava/lang/String;
    //   243: putstatic 53	com/firemonkeys/cloudcellapi/CC_GoogleStoreServiceV3_Class:sActivePurchaseProductId	Ljava/lang/String;
    //   246: aload_0
    //   247: aload 15
    //   249: aload 13
    //   251: invokevirtual 296	com/firemonkeys/cloudcellapi/CC_GoogleStoreServiceV3_Class:onPurchaseFinished	(Lcom/firemonkeys/cloudcellapi/util/IabResult;Lcom/firemonkeys/cloudcellapi/util/Purchase;)V
    //   254: goto +68 -> 322
    //   257: ldc_w 346
    //   260: invokestatic 180	com/firemonkeys/cloudcellapi/Consts:Logger	(Ljava/lang/String;)V
    //   263: getstatic 216	com/firemonkeys/cloudcellapi/Consts$ResponseCode:RESULT_OK	Lcom/firemonkeys/cloudcellapi/Consts$ResponseCode;
    //   266: astore 14
    //   268: new 285	com/firemonkeys/cloudcellapi/util/IabResult
    //   271: dup
    //   272: aload 14
    //   274: ldc_w 348
    //   277: invokespecial 288	com/firemonkeys/cloudcellapi/util/IabResult:<init>	(Lcom/firemonkeys/cloudcellapi/Consts$ResponseCode;Ljava/lang/String;)V
    //   280: astore 15
    //   282: goto -44 -> 238
    //   285: astore 9
    //   287: ldc_w 350
    //   290: invokestatic 180	com/firemonkeys/cloudcellapi/Consts:Logger	(Ljava/lang/String;)V
    //   293: aload 9
    //   295: invokevirtual 353	org/json/JSONException:printStackTrace	()V
    //   298: goto +24 -> 322
    //   301: astore_2
    //   302: iconst_0
    //   303: istore_1
    //   304: aconst_null
    //   305: invokestatic 477	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   308: ifeq -306 -> 2
    //   311: iload_1
    //   312: getstatic 428	com/firemonkeys/cloudcellapi/CC_GoogleStoreServiceV3_Class:m_nRefreshStorePurchasesCallback	J
    //   315: getstatic 105	com/firemonkeys/cloudcellapi/CC_GoogleStoreServiceV3_Class:m_nUserPointer	J
    //   318: invokestatic 479	com/firemonkeys/cloudcellapi/CC_GoogleStoreServiceV3_Class:RefreshStorePurchasesCallback	(ZJJ)V
    //   321: return
    //   322: iinc 7 1
    //   325: goto -254 -> 71
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	328	0	this	CC_GoogleStoreServiceV3_Class
    //   1	311	1	bool	boolean
    //   301	1	2	localRemoteException	RemoteException
    //   22	38	3	localBundle	Bundle
    //   48	74	4	localArrayList1	ArrayList
    //   57	41	5	localArrayList2	ArrayList
    //   66	44	6	localArrayList3	ArrayList
    //   69	254	7	i	int
    //   76	7	8	j	int
    //   285	9	9	localJSONException	JSONException
    //   107	57	10	str1	String
    //   119	47	11	str2	String
    //   131	85	12	str3	String
    //   146	104	13	localPurchase	Purchase
    //   266	7	14	localResponseCode1	Consts.ResponseCode
    //   236	45	15	localIabResult	IabResult
    //   200	30	16	localResponseCode2	Consts.ResponseCode
    //   223	9	17	str4	String
    // Exception table:
    //   from	to	target	type
    //   85	238	285	org/json/JSONException
    //   238	254	285	org/json/JSONException
    //   257	282	285	org/json/JSONException
    //   2	39	301	android/os/RemoteException
    //   41	68	301	android/os/RemoteException
    //   71	78	301	android/os/RemoteException
    //   85	238	301	android/os/RemoteException
    //   238	254	301	android/os/RemoteException
    //   257	282	301	android/os/RemoteException
    //   287	298	301	android/os/RemoteException
  }
  
  public void RestorePurchase()
  {
    Consts.Logger("RestorePurchase Begin");
    CC_Activity.GetActivity().runOnUiThread(new Runnable()
    {
      public void run()
      {
        Consts.Logger("Querying inventory.");
        new Thread(new Runnable()
        {
          /* Error */
          public void run()
          {
            // Byte code:
            //   0: aconst_null
            //   1: astore_1
            //   2: getstatic 33	com/firemonkeys/cloudcellapi/Consts$ResponseCode:RESULT_OK	Lcom/firemonkeys/cloudcellapi/Consts$ResponseCode;
            //   5: pop
            //   6: new 35	com/firemonkeys/cloudcellapi/util/Inventory
            //   9: dup
            //   10: invokespecial 36	com/firemonkeys/cloudcellapi/util/Inventory:<init>	()V
            //   13: astore_3
            //   14: ldc 38
            //   16: invokestatic 44	com/firemonkeys/cloudcellapi/Consts:Logger	(Ljava/lang/String;)V
            //   19: new 46	java/lang/StringBuilder
            //   22: dup
            //   23: invokespecial 47	java/lang/StringBuilder:<init>	()V
            //   26: ldc 49
            //   28: invokevirtual 53	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   31: aload_0
            //   32: getfield 19	com/firemonkeys/cloudcellapi/CC_GoogleStoreServiceV3_Class$6$1:this$1	Lcom/firemonkeys/cloudcellapi/CC_GoogleStoreServiceV3_Class$6;
            //   35: getfield 57	com/firemonkeys/cloudcellapi/CC_GoogleStoreServiceV3_Class$6:this$0	Lcom/firemonkeys/cloudcellapi/CC_GoogleStoreServiceV3_Class;
            //   38: getfield 63	com/firemonkeys/cloudcellapi/CC_GoogleStoreServiceV3_Class:mContext	Landroid/content/Context;
            //   41: invokevirtual 69	android/content/Context:getPackageName	()Ljava/lang/String;
            //   44: invokevirtual 53	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   47: invokevirtual 72	java/lang/StringBuilder:toString	()Ljava/lang/String;
            //   50: invokestatic 44	com/firemonkeys/cloudcellapi/Consts:Logger	(Ljava/lang/String;)V
            //   53: iconst_0
            //   54: istore 12
            //   56: aconst_null
            //   57: astore 13
            //   59: new 46	java/lang/StringBuilder
            //   62: dup
            //   63: invokespecial 47	java/lang/StringBuilder:<init>	()V
            //   66: ldc 74
            //   68: invokevirtual 53	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   71: aload 13
            //   73: invokevirtual 53	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   76: invokevirtual 72	java/lang/StringBuilder:toString	()Ljava/lang/String;
            //   79: invokestatic 44	com/firemonkeys/cloudcellapi/Consts:Logger	(Ljava/lang/String;)V
            //   82: aload_0
            //   83: getfield 19	com/firemonkeys/cloudcellapi/CC_GoogleStoreServiceV3_Class$6$1:this$1	Lcom/firemonkeys/cloudcellapi/CC_GoogleStoreServiceV3_Class$6;
            //   86: getfield 57	com/firemonkeys/cloudcellapi/CC_GoogleStoreServiceV3_Class$6:this$0	Lcom/firemonkeys/cloudcellapi/CC_GoogleStoreServiceV3_Class;
            //   89: getfield 78	com/firemonkeys/cloudcellapi/CC_GoogleStoreServiceV3_Class:mService	Lcom/android/vending/billing/IInAppBillingService;
            //   92: iconst_3
            //   93: aload_0
            //   94: getfield 19	com/firemonkeys/cloudcellapi/CC_GoogleStoreServiceV3_Class$6$1:this$1	Lcom/firemonkeys/cloudcellapi/CC_GoogleStoreServiceV3_Class$6;
            //   97: getfield 57	com/firemonkeys/cloudcellapi/CC_GoogleStoreServiceV3_Class$6:this$0	Lcom/firemonkeys/cloudcellapi/CC_GoogleStoreServiceV3_Class;
            //   100: getfield 63	com/firemonkeys/cloudcellapi/CC_GoogleStoreServiceV3_Class:mContext	Landroid/content/Context;
            //   103: invokevirtual 69	android/content/Context:getPackageName	()Ljava/lang/String;
            //   106: ldc 80
            //   108: aload 13
            //   110: invokeinterface 86 5 0
            //   115: astore 14
            //   117: aload 14
            //   119: invokestatic 90	com/firemonkeys/cloudcellapi/Consts:getResponseCodeFromBundle	(Landroid/os/Bundle;)Lcom/firemonkeys/cloudcellapi/Consts$ResponseCode;
            //   122: astore 15
            //   124: new 46	java/lang/StringBuilder
            //   127: dup
            //   128: invokespecial 47	java/lang/StringBuilder:<init>	()V
            //   131: ldc 92
            //   133: invokevirtual 53	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   136: aload 15
            //   138: invokevirtual 93	com/firemonkeys/cloudcellapi/Consts$ResponseCode:toString	()Ljava/lang/String;
            //   141: invokevirtual 53	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   144: invokevirtual 72	java/lang/StringBuilder:toString	()Ljava/lang/String;
            //   147: invokestatic 44	com/firemonkeys/cloudcellapi/Consts:Logger	(Ljava/lang/String;)V
            //   150: aload 15
            //   152: getstatic 33	com/firemonkeys/cloudcellapi/Consts$ResponseCode:RESULT_OK	Lcom/firemonkeys/cloudcellapi/Consts$ResponseCode;
            //   155: if_acmpeq +105 -> 260
            //   158: new 46	java/lang/StringBuilder
            //   161: dup
            //   162: invokespecial 47	java/lang/StringBuilder:<init>	()V
            //   165: ldc 95
            //   167: invokevirtual 53	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   170: aload 15
            //   172: invokevirtual 93	com/firemonkeys/cloudcellapi/Consts$ResponseCode:toString	()Ljava/lang/String;
            //   175: invokevirtual 53	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   178: invokevirtual 72	java/lang/StringBuilder:toString	()Ljava/lang/String;
            //   181: invokestatic 44	com/firemonkeys/cloudcellapi/Consts:Logger	(Ljava/lang/String;)V
            //   184: aload 13
            //   186: invokestatic 101	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
            //   189: ifne +11 -> 200
            //   192: aload 15
            //   194: getstatic 104	com/firemonkeys/cloudcellapi/Consts$ResponseCode:RESULT_ERROR	Lcom/firemonkeys/cloudcellapi/Consts$ResponseCode;
            //   197: if_acmpne -138 -> 59
            //   200: iload 12
            //   202: ifeq +380 -> 582
            //   205: getstatic 104	com/firemonkeys/cloudcellapi/Consts$ResponseCode:RESULT_ERROR	Lcom/firemonkeys/cloudcellapi/Consts$ResponseCode;
            //   208: astore 5
            //   210: aload_3
            //   211: astore_1
            //   212: new 106	com/firemonkeys/cloudcellapi/util/IabResult
            //   215: dup
            //   216: aload 5
            //   218: aload 5
            //   220: invokevirtual 93	com/firemonkeys/cloudcellapi/Consts$ResponseCode:toString	()Ljava/lang/String;
            //   223: invokespecial 109	com/firemonkeys/cloudcellapi/util/IabResult:<init>	(Lcom/firemonkeys/cloudcellapi/Consts$ResponseCode;Ljava/lang/String;)V
            //   226: astore 6
            //   228: aload_1
            //   229: astore 7
            //   231: aload_0
            //   232: getfield 21	com/firemonkeys/cloudcellapi/CC_GoogleStoreServiceV3_Class$6$1:val$handler	Landroid/os/Handler;
            //   235: astore 8
            //   237: new 111	com/firemonkeys/cloudcellapi/CC_GoogleStoreServiceV3_Class$6$1$1
            //   240: dup
            //   241: aload_0
            //   242: aload 6
            //   244: aload 7
            //   246: invokespecial 114	com/firemonkeys/cloudcellapi/CC_GoogleStoreServiceV3_Class$6$1$1:<init>	(Lcom/firemonkeys/cloudcellapi/CC_GoogleStoreServiceV3_Class$6$1;Lcom/firemonkeys/cloudcellapi/util/IabResult;Lcom/firemonkeys/cloudcellapi/util/Inventory;)V
            //   249: astore 9
            //   251: aload 8
            //   253: aload 9
            //   255: invokevirtual 120	android/os/Handler:post	(Ljava/lang/Runnable;)Z
            //   258: pop
            //   259: return
            //   260: aload 14
            //   262: ldc 122
            //   264: invokevirtual 128	android/os/Bundle:containsKey	(Ljava/lang/String;)Z
            //   267: ifeq +23 -> 290
            //   270: aload 14
            //   272: ldc 130
            //   274: invokevirtual 128	android/os/Bundle:containsKey	(Ljava/lang/String;)Z
            //   277: ifeq +13 -> 290
            //   280: aload 14
            //   282: ldc 132
            //   284: invokevirtual 128	android/os/Bundle:containsKey	(Ljava/lang/String;)Z
            //   287: ifne +16 -> 303
            //   290: ldc 134
            //   292: invokestatic 44	com/firemonkeys/cloudcellapi/Consts:Logger	(Ljava/lang/String;)V
            //   295: getstatic 104	com/firemonkeys/cloudcellapi/Consts$ResponseCode:RESULT_ERROR	Lcom/firemonkeys/cloudcellapi/Consts$ResponseCode;
            //   298: astore 15
            //   300: goto -116 -> 184
            //   303: aload 14
            //   305: ldc 122
            //   307: invokevirtual 138	android/os/Bundle:getStringArrayList	(Ljava/lang/String;)Ljava/util/ArrayList;
            //   310: astore 16
            //   312: aload 14
            //   314: ldc 130
            //   316: invokevirtual 138	android/os/Bundle:getStringArrayList	(Ljava/lang/String;)Ljava/util/ArrayList;
            //   319: astore 17
            //   321: aload 14
            //   323: ldc 132
            //   325: invokevirtual 138	android/os/Bundle:getStringArrayList	(Ljava/lang/String;)Ljava/util/ArrayList;
            //   328: astore 18
            //   330: iconst_0
            //   331: istore 19
            //   333: iload 19
            //   335: aload 17
            //   337: invokevirtual 144	java/util/ArrayList:size	()I
            //   340: if_icmpge +195 -> 535
            //   343: aload 17
            //   345: iload 19
            //   347: invokevirtual 148	java/util/ArrayList:get	(I)Ljava/lang/Object;
            //   350: checkcast 150	java/lang/String
            //   353: astore 20
            //   355: aload 18
            //   357: iload 19
            //   359: invokevirtual 148	java/util/ArrayList:get	(I)Ljava/lang/Object;
            //   362: checkcast 150	java/lang/String
            //   365: astore 21
            //   367: aload 16
            //   369: iload 19
            //   371: invokevirtual 148	java/util/ArrayList:get	(I)Ljava/lang/Object;
            //   374: checkcast 150	java/lang/String
            //   377: astore 22
            //   379: invokestatic 153	com/firemonkeys/cloudcellapi/CC_GoogleStoreServiceV3_Class:getPublicKey	()Ljava/lang/String;
            //   382: aload 20
            //   384: aload 21
            //   386: invokestatic 159	com/firemonkeys/cloudcellapi/util/Security:verifyPurchase	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
            //   389: ifeq +89 -> 478
            //   392: new 46	java/lang/StringBuilder
            //   395: dup
            //   396: invokespecial 47	java/lang/StringBuilder:<init>	()V
            //   399: ldc 161
            //   401: invokevirtual 53	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   404: aload 22
            //   406: invokevirtual 53	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   409: invokevirtual 72	java/lang/StringBuilder:toString	()Ljava/lang/String;
            //   412: invokestatic 44	com/firemonkeys/cloudcellapi/Consts:Logger	(Ljava/lang/String;)V
            //   415: new 163	com/firemonkeys/cloudcellapi/util/Purchase
            //   418: dup
            //   419: ldc 80
            //   421: aload 20
            //   423: aload 21
            //   425: invokespecial 166	com/firemonkeys/cloudcellapi/util/Purchase:<init>	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
            //   428: astore 23
            //   430: aload 23
            //   432: invokevirtual 169	com/firemonkeys/cloudcellapi/util/Purchase:getToken	()Ljava/lang/String;
            //   435: invokestatic 101	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
            //   438: ifeq +31 -> 469
            //   441: ldc 171
            //   443: invokestatic 44	com/firemonkeys/cloudcellapi/Consts:Logger	(Ljava/lang/String;)V
            //   446: new 46	java/lang/StringBuilder
            //   449: dup
            //   450: invokespecial 47	java/lang/StringBuilder:<init>	()V
            //   453: ldc 173
            //   455: invokevirtual 53	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   458: aload 20
            //   460: invokevirtual 53	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   463: invokevirtual 72	java/lang/StringBuilder:toString	()Ljava/lang/String;
            //   466: invokestatic 44	com/firemonkeys/cloudcellapi/Consts:Logger	(Ljava/lang/String;)V
            //   469: aload_3
            //   470: aload 23
            //   472: invokevirtual 177	com/firemonkeys/cloudcellapi/util/Inventory:addPurchase	(Lcom/firemonkeys/cloudcellapi/util/Purchase;)V
            //   475: goto +139 -> 614
            //   478: ldc 179
            //   480: invokestatic 44	com/firemonkeys/cloudcellapi/Consts:Logger	(Ljava/lang/String;)V
            //   483: new 46	java/lang/StringBuilder
            //   486: dup
            //   487: invokespecial 47	java/lang/StringBuilder:<init>	()V
            //   490: ldc 181
            //   492: invokevirtual 53	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   495: aload 20
            //   497: invokevirtual 53	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   500: invokevirtual 72	java/lang/StringBuilder:toString	()Ljava/lang/String;
            //   503: invokestatic 44	com/firemonkeys/cloudcellapi/Consts:Logger	(Ljava/lang/String;)V
            //   506: new 46	java/lang/StringBuilder
            //   509: dup
            //   510: invokespecial 47	java/lang/StringBuilder:<init>	()V
            //   513: ldc 183
            //   515: invokevirtual 53	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   518: aload 21
            //   520: invokevirtual 53	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   523: invokevirtual 72	java/lang/StringBuilder:toString	()Ljava/lang/String;
            //   526: invokestatic 44	com/firemonkeys/cloudcellapi/Consts:Logger	(Ljava/lang/String;)V
            //   529: iconst_1
            //   530: istore 12
            //   532: goto +82 -> 614
            //   535: aload 14
            //   537: ldc 185
            //   539: invokevirtual 189	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
            //   542: astore 13
            //   544: new 46	java/lang/StringBuilder
            //   547: dup
            //   548: invokespecial 47	java/lang/StringBuilder:<init>	()V
            //   551: ldc 191
            //   553: invokevirtual 53	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   556: aload 13
            //   558: invokevirtual 53	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   561: invokevirtual 72	java/lang/StringBuilder:toString	()Ljava/lang/String;
            //   564: invokestatic 44	com/firemonkeys/cloudcellapi/Consts:Logger	(Ljava/lang/String;)V
            //   567: goto -383 -> 184
            //   570: astore 11
            //   572: aload_3
            //   573: astore_1
            //   574: getstatic 104	com/firemonkeys/cloudcellapi/Consts$ResponseCode:RESULT_ERROR	Lcom/firemonkeys/cloudcellapi/Consts$ResponseCode;
            //   577: astore 5
            //   579: goto -367 -> 212
            //   582: getstatic 33	com/firemonkeys/cloudcellapi/Consts$ResponseCode:RESULT_OK	Lcom/firemonkeys/cloudcellapi/Consts$ResponseCode;
            //   585: astore 5
            //   587: goto -377 -> 210
            //   590: astore 25
            //   592: getstatic 104	com/firemonkeys/cloudcellapi/Consts$ResponseCode:RESULT_ERROR	Lcom/firemonkeys/cloudcellapi/Consts$ResponseCode;
            //   595: astore 5
            //   597: goto -385 -> 212
            //   600: astore 4
            //   602: aload_3
            //   603: astore_1
            //   604: goto -12 -> 592
            //   607: astore 24
            //   609: aconst_null
            //   610: astore_1
            //   611: goto -37 -> 574
            //   614: iinc 19 1
            //   617: goto -284 -> 333
            // Local variable table:
            //   start	length	slot	name	signature
            //   0	620	0	this	1
            //   1	610	1	localObject1	Object
            //   13	590	3	localInventory	Inventory
            //   600	1	4	localJSONException1	JSONException
            //   208	388	5	localResponseCode1	Consts.ResponseCode
            //   226	17	6	localIabResult	IabResult
            //   229	16	7	localObject2	Object
            //   235	17	8	localHandler	Handler
            //   249	5	9	local1	1
            //   570	1	11	localRemoteException1	RemoteException
            //   54	477	12	i	int
            //   57	500	13	str1	String
            //   115	421	14	localBundle	Bundle
            //   122	177	15	localResponseCode2	Consts.ResponseCode
            //   310	58	16	localArrayList1	ArrayList
            //   319	25	17	localArrayList2	ArrayList
            //   328	28	18	localArrayList3	ArrayList
            //   331	284	19	j	int
            //   353	143	20	str2	String
            //   365	154	21	str3	String
            //   377	28	22	str4	String
            //   428	43	23	localPurchase	Purchase
            //   607	1	24	localRemoteException2	RemoteException
            //   590	1	25	localJSONException2	JSONException
            // Exception table:
            //   from	to	target	type
            //   14	53	570	android/os/RemoteException
            //   59	184	570	android/os/RemoteException
            //   184	200	570	android/os/RemoteException
            //   205	210	570	android/os/RemoteException
            //   260	290	570	android/os/RemoteException
            //   290	300	570	android/os/RemoteException
            //   303	330	570	android/os/RemoteException
            //   333	469	570	android/os/RemoteException
            //   469	475	570	android/os/RemoteException
            //   478	529	570	android/os/RemoteException
            //   535	567	570	android/os/RemoteException
            //   582	587	570	android/os/RemoteException
            //   6	14	590	org/json/JSONException
            //   14	53	600	org/json/JSONException
            //   59	184	600	org/json/JSONException
            //   184	200	600	org/json/JSONException
            //   205	210	600	org/json/JSONException
            //   260	290	600	org/json/JSONException
            //   290	300	600	org/json/JSONException
            //   303	330	600	org/json/JSONException
            //   333	469	600	org/json/JSONException
            //   469	475	600	org/json/JSONException
            //   478	529	600	org/json/JSONException
            //   535	567	600	org/json/JSONException
            //   582	587	600	org/json/JSONException
            //   6	14	607	android/os/RemoteException
          }
        }).start();
      }
    });
  }
  
  public void getProductDetails(final String[] paramArrayOfString)
  {
    Consts.Logger("getProductDetails Begin");
    new Thread(new Runnable()
    {
      public void run()
      {
        Consts.Logger("Querying sku details.");
        int i = 0;
        int j = paramArrayOfString.length;
        Inventory localInventory = new Inventory();
        Consts.ResponseCode localResponseCode1 = Consts.ResponseCode.RESULT_OK;
        IabResult localIabResult = new IabResult(localResponseCode1, "Inventory refresh successful.");
        for (;;)
        {
          int i1;
          if (i < j)
          {
            ArrayList localArrayList = new ArrayList();
            int k = j - i;
            int m;
            int n;
            label75:
            int i2;
            if (k > 20)
            {
              m = 20;
              n = 0;
              i1 = i;
              if (n >= m) {
                break label147;
              }
              String[] arrayOfString = paramArrayOfString;
              i2 = i1 + 1;
              String str2 = arrayOfString[i1];
              if ((str2 == null) || (str2.length() <= 0)) {
                break label139;
              }
              localArrayList.add(str2);
            }
            for (;;)
            {
              n++;
              i1 = i2;
              break label75;
              m = k;
              break;
              label139:
              Consts.Logger("A productId was null/length 0. Skipping it");
            }
            label147:
            Consts.ResponseCode localResponseCode2 = Consts.ResponseCode.RESULT_OK;
            try
            {
              Bundle localBundle1 = new Bundle();
              localBundle1.putStringArrayList("ITEM_ID_LIST", localArrayList);
              Bundle localBundle2 = CC_GoogleStoreServiceV3_Class.this.mService.getSkuDetails(3, CC_GoogleStoreServiceV3_Class.this.mContext.getPackageName(), "inapp", localBundle1);
              if (!localBundle2.containsKey("DETAILS_LIST"))
              {
                localResponseCode2 = Consts.getResponseCodeFromBundle(localBundle2);
                Consts.ResponseCode localResponseCode4 = Consts.ResponseCode.RESULT_OK;
                if (localResponseCode2 != localResponseCode4)
                {
                  Consts.Logger("getSkuDetails() failed: " + localResponseCode2.toString());
                  localIabResult = new IabResult(localResponseCode2, "getSkuDetails returned Bundle with error.");
                  break label487;
                }
                Consts.Logger("getSkuDetails() returned a bundle with neither an error nor a detail list.");
                localIabResult = new IabResult(localResponseCode2, "getSkuDetails() returned a bundle with neither an error nor a detail list.");
                break label487;
              }
              Iterator localIterator = localBundle2.getStringArrayList("DETAILS_LIST").iterator();
              while (localIterator.hasNext())
              {
                SkuDetails localSkuDetails = new SkuDetails("inapp", (String)localIterator.next());
                Consts.Logger("sku Currency Code: " + localSkuDetails.getPriceCurrencyCode());
                Consts.Logger("Got sku details POOP: " + localSkuDetails);
                localInventory.addSkuDetails(localSkuDetails);
              }
              Consts.ResponseCode localResponseCode3;
              String str1;
              CC_GoogleStoreServiceV3_Class.this.onQueryInventoryFinished(localIabResult, localInventory);
            }
            catch (RemoteException localRemoteException)
            {
              localIabResult = new IabResult(localResponseCode2, "getProductDetails suffered a remote exception.");
            }
            catch (JSONException localJSONException)
            {
              localIabResult = new IabResult(localResponseCode2, "getProductDetails suffered a JSON exception.");
            }
            catch (Exception localException)
            {
              localResponseCode3 = Consts.ResponseCode.RESULT_ERROR;
              str1 = "getProductDetails exception: " + localException.toString();
              localIabResult = new IabResult(localResponseCode3, str1);
            }
          }
          else
          {
            return;
          }
          label487:
          i = i1;
        }
      }
    }).start();
  }
  
  public void onConsumeFinished(Purchase paramPurchase, IabResult paramIabResult)
  {
    Consts.Logger("Consumption finished. Purchase: " + paramPurchase + ", result: " + paramIabResult);
    if (paramIabResult.isSuccess())
    {
      Consts.Logger("Consumption successful. Provisioning consumable product.");
      PurchaseSucceedCallback(paramPurchase);
    }
    for (;;)
    {
      Consts.Logger("End consumption flow.");
      return;
      Consts.Logger("Consumption failed. It should be provisioned later.");
      assert (paramIabResult.getSku() != null);
      String str1 = "";
      if (paramIabResult.getSku() != null) {
        str1 = paramIabResult.getSku();
      }
      String str2 = "";
      if (paramIabResult.getMessage() != null) {
        str2 = paramIabResult.getMessage();
      }
      PurchaseErrorCallback(str1, paramIabResult.getResponse().getValue(), str2, m_nPurchaseErrorCallback, m_nUserPointer);
    }
  }
  
  public void onConsumeMultiFinished(List<Purchase> paramList, List<IabResult> paramList1)
  {
    Consts.Logger("Unprovisioned consumption finished.");
    assert (paramList.size() == paramList1.size());
    int i = 0;
    Iterator localIterator = paramList1.iterator();
    if (localIterator.hasNext())
    {
      IabResult localIabResult = (IabResult)localIterator.next();
      Purchase localPurchase = (Purchase)paramList.get(i);
      if (localIabResult.isSuccess())
      {
        Consts.Logger("Consumption successful. Provisioning consumable product.");
        PurchaseSucceedCallback(localPurchase);
      }
      for (;;)
      {
        i++;
        break;
        Consts.Logger("Consumption failed. It should be provisioned later on next app launch. Reason: " + localIabResult.getMessage());
      }
    }
    Consts.Logger("End unprovisioned consumption flow.");
  }
  
  public void onIabSetupFinished(IabResult paramIabResult)
  {
    Consts.Logger("Setup finished.");
    if (!paramIabResult.isSuccess()) {
      Consts.Logger("Problem setting up in-app billing: " + paramIabResult);
    }
    InitializeCallback(paramIabResult.isSuccess(), paramIabResult.isSuccess(), m_nInitializeCallback, m_nUserPointer);
  }
  
  public void onPurchaseFinished(IabResult paramIabResult, Purchase paramPurchase)
  {
    Consts.Logger("Purchase finished: " + paramIabResult + ", purchase: " + paramPurchase);
    String str1;
    String str2;
    if (paramIabResult.isFailure()) {
      if (sActivePurchaseProductId == null)
      {
        str1 = "";
        if (paramIabResult.getMessage() != null) {
          break label92;
        }
        str2 = "";
        label60:
        PurchaseErrorCallback(str1, paramIabResult.getResponse().getValue(), str2, m_nPurchaseErrorCallback, m_nUserPointer);
      }
    }
    for (;;)
    {
      sActivePurchaseProductId = null;
      return;
      str1 = sActivePurchaseProductId;
      break;
      label92:
      str2 = paramIabResult.getMessage();
      break label60;
      Consts.Logger("Purchase successful.");
      if (isProductConsumable(paramPurchase.getSku()))
      {
        runConsume(paramIabResult, paramPurchase);
      }
      else
      {
        Consts.Logger("Provisioning non-consumable product.");
        PurchaseSucceedCallback(paramPurchase);
      }
    }
  }
  
  public void onQueryInventoryFinished(IabResult paramIabResult, Inventory paramInventory)
  {
    Consts.Logger("Query product details finished.");
    if (paramIabResult.isFailure()) {
      ProductDetailsErrorCallback(paramIabResult.getResponse().getValue(), paramIabResult.getMessage(), m_nProductDetailsErrorCallback, m_nUserPointer);
    }
    for (;;)
    {
      Consts.Logger("Query product details finished.");
      return;
      Consts.Logger("Query product details was successful.");
      List localList = paramInventory.getAllSkuDetails();
      SkuDetails[] arrayOfSkuDetails = new SkuDetails[localList.size()];
      int i = 0;
      Iterator localIterator = localList.iterator();
      while (localIterator.hasNext())
      {
        SkuDetails localSkuDetails = (SkuDetails)localIterator.next();
        Consts.Logger("SkuDetails: sku = " + localSkuDetails.getSku() + ", price = " + localSkuDetails.getPrice() + ", title = " + localSkuDetails.getTitle() + ", type = " + localSkuDetails.getType() + ", micros = " + localSkuDetails.getPriceAmountMicros() + ", currency = " + localSkuDetails.getPriceCurrencyCode());
        arrayOfSkuDetails[i] = localSkuDetails;
        i++;
      }
      ProductDetailsSucceedCallback(arrayOfSkuDetails, m_nProductDetailsSucceedCallback, m_nUserPointer);
    }
  }
  
  public void onQueryRestorePurchasesFinished(IabResult paramIabResult, Inventory paramInventory)
  {
    Consts.Logger("Restore: query inventory finished.");
    boolean bool = paramIabResult.isFailure();
    int i = 0;
    if (!bool)
    {
      Iterator localIterator = paramInventory.getAllPurchases().iterator();
      while (localIterator.hasNext())
      {
        Purchase localPurchase = (Purchase)localIterator.next();
        if (!isProductConsumable(localPurchase.getSku()))
        {
          PurchaseSucceedCallback(localPurchase);
          if (!IsPackAlreadyBought(localPurchase.getOrderId())) {
            i++;
          }
        }
        else if (!IsPackAlreadyBought(localPurchase.getOrderId()))
        {
          runConsume(paramIabResult, localPurchase);
          i++;
        }
      }
    }
    RestoreCallback(paramIabResult.getResponse().getValue(), paramIabResult.getMessage(), i, paramInventory.getAllPurchases().size(), m_nRestoreCallback, m_nUserPointer);
  }
  
  public void setConsumableProductList(final String[] paramArrayOfString)
  {
    Consts.Logger("setConsumableProductList");
    CC_Activity.GetActivity().runOnUiThread(new Runnable()
    {
      public void run()
      {
        Consts.Logger("Saving consumable products list.");
        ArrayList localArrayList = new ArrayList(paramArrayOfString.length);
        String[] arrayOfString = paramArrayOfString;
        int i = arrayOfString.length;
        for (int j = 0; j < i; j++) {
          localArrayList.add(arrayOfString[j]);
        }
        CC_GoogleStoreServiceV3_Class.this.mConsumableProductIds = localArrayList;
      }
    });
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.firemonkeys.cloudcellapi.CC_GoogleStoreServiceV3_Class
 * JD-Core Version:    0.7.0.1
 */