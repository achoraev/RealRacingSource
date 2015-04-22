package com.firemonkeys.cloudcellapi;

import android.app.Activity;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.android.vending.billing.IMarketBillingService;
import com.android.vending.billing.IMarketBillingService.Stub;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;

class CC_GoogleStoreService_Class
  extends Service
  implements ServiceConnection
{
  private static final Class[] START_INTENT_SENDER_SIG;
  private static long m_nConstructCallback;
  private static long m_nInAppNotifyCallback;
  private static long m_nPurchaseStateChangedCallback;
  private static long m_nResponseCodeCallback;
  private static long m_nUserPointer;
  private static Activity m_pActivity;
  private static IMarketBillingService m_pService;
  private static String m_sPackageName;
  private Method m_mStartIntentSender;
  private Object[] m_pStartIntentSenderArgs = new Object[5];
  
  static
  {
    Class[] arrayOfClass = new Class[5];
    arrayOfClass[0] = IntentSender.class;
    arrayOfClass[1] = Intent.class;
    arrayOfClass[2] = Integer.TYPE;
    arrayOfClass[3] = Integer.TYPE;
    arrayOfClass[4] = Integer.TYPE;
    START_INTENT_SENDER_SIG = arrayOfClass;
  }
  
  public static void BroadcastReceive(Intent paramIntent)
  {
    String str1 = paramIntent.getAction();
    Consts.Logger("BroadcastReceive: " + str1);
    if (str1.equals("com.android.vending.billing.PURCHASE_STATE_CHANGED"))
    {
      long l3 = paramIntent.getLongExtra("request_id", -1L);
      String str3 = paramIntent.getStringExtra("inapp_signed_data");
      String str4 = paramIntent.getStringExtra("inapp_signature");
      Consts.Logger("onReceive: " + str1 + " " + l3 + " " + str3 + " " + str4);
      ArrayList localArrayList1 = CC_Security.getVerifiedPurchaseList(getPublicKey(), str3, str4);
      if (localArrayList1 == null)
      {
        Consts.Logger("onReceive: no purchases verified");
        ResponseCodeCallback(l3, 6, m_nResponseCodeCallback, m_nUserPointer);
      }
      ArrayList localArrayList2;
      do
      {
        return;
        localArrayList2 = new ArrayList();
        Iterator localIterator = localArrayList1.iterator();
        while (localIterator.hasNext())
        {
          CC_Security.VerifiedPurchase localVerifiedPurchase = (CC_Security.VerifiedPurchase)localIterator.next();
          if (localVerifiedPurchase.notificationId != null) {
            localArrayList2.add(localVerifiedPurchase.notificationId);
          }
          Consts.Logger("onReceive: verified sellID " + localVerifiedPurchase.productId);
          PurchaseStateChangedCallback(localVerifiedPurchase.notificationId, localVerifiedPurchase.orderId, localVerifiedPurchase.packageName, localVerifiedPurchase.productId, String.valueOf(localVerifiedPurchase.purchaseTime), Consts.PurchaseState.toString(localVerifiedPurchase.purchaseState), localVerifiedPurchase.developerPayload, str3, str4, m_nPurchaseStateChangedCallback, m_nUserPointer);
        }
      } while (localArrayList2.isEmpty());
      ConfirmTransaction((String[])localArrayList2.toArray(new String[localArrayList2.size()]));
      return;
    }
    if (str1.equals("com.android.vending.billing.IN_APP_NOTIFY"))
    {
      long l2 = paramIntent.getLongExtra("request_id", -1L);
      String str2 = paramIntent.getStringExtra("notification_id");
      Consts.Logger("onReceive: " + str1 + " " + l2 + " " + str2);
      try
      {
        InAppNotifyCallback(str2, m_nInAppNotifyCallback, m_nUserPointer);
        m_pActivity.runOnUiThread(new Runnable()
        {
          public void run()
          {
            try
            {
              String[] arrayOfString = new String[1];
              arrayOfString[0] = this.val$sNotificationId;
              Bundle localBundle1 = CC_GoogleStoreService_Class.MakeRequestBundle("GET_PURCHASE_INFORMATION");
              localBundle1.putLong("NONCE", CC_Security.generateNonce());
              localBundle1.putStringArray("NOTIFY_IDS", arrayOfString);
              Bundle localBundle2 = CC_GoogleStoreService_Class.m_pService.sendBillingRequest(localBundle1);
              Consts.Logger("in app notify sent " + localBundle2.getInt("RESPONSE_CODE") + " " + localBundle2.getLong("REQUEST_ID"));
              return;
            }
            catch (RemoteException localRemoteException) {}
          }
        });
        return;
      }
      catch (UnsatisfiedLinkError localUnsatisfiedLinkError)
      {
        Consts.Logger("onReceive: cannot invoke InAppNotifyCallback");
        return;
      }
    }
    if (str1.equals("com.android.vending.billing.RESPONSE_CODE"))
    {
      long l1 = paramIntent.getLongExtra("request_id", -1L);
      int i = paramIntent.getIntExtra("response_code", -1);
      Consts.Logger("onReceive: " + str1 + " " + l1 + " " + i);
      ResponseCodeCallback(l1, i, m_nResponseCodeCallback, m_nUserPointer);
      return;
    }
    Consts.Logger("onReceive: UNKNOWN ACTION: " + str1);
  }
  
  public static void ConfirmTransaction(String[] paramArrayOfString)
  {
    Consts.Logger("ConfirmTransaction Begin");
    m_pActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        try
        {
          Bundle localBundle1 = CC_GoogleStoreService_Class.MakeRequestBundle("CONFIRM_NOTIFICATIONS");
          localBundle1.putStringArray("NOTIFY_IDS", this.val$sTransactionId);
          Bundle localBundle2 = CC_GoogleStoreService_Class.m_pService.sendBillingRequest(localBundle1);
          if (localBundle2.getInt("RESPONSE_CODE") == 0)
          {
            Consts.Logger("ConfirmTransaction Success " + localBundle2.getLong("REQUEST_ID"));
            return;
          }
          Consts.Logger("ConfirmTransaction Failure " + this.val$sTransactionId);
          return;
        }
        catch (RemoteException localRemoteException) {}
      }
    });
  }
  
  private static native void ConstructCallback(boolean paramBoolean1, boolean paramBoolean2, long paramLong1, long paramLong2);
  
  private static native void InAppNotifyCallback(String paramString, long paramLong1, long paramLong2);
  
  private static Bundle MakeRequestBundle(String paramString)
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("BILLING_REQUEST", paramString);
    localBundle.putInt("API_VERSION", 1);
    localBundle.putString("PACKAGE_NAME", m_sPackageName);
    return localBundle;
  }
  
  private static native void PurchaseSetRequestId(long paramLong1, long paramLong2, long paramLong3);
  
  private static native void PurchaseStateChangedCallback(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, long paramLong1, long paramLong2);
  
  private static native void ResponseCodeCallback(long paramLong1, int paramInt, long paramLong2, long paramLong3);
  
  static String getPublicKey()
  {
    if (m_sPackageName.equals("com.ea.games.r3_row"))
    {
      Consts.Logger("getPublicKey ROW");
      return "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjDe1oWKcLp/Rtm1ezKa9G+fHdqKvWEx" + "NR1aYuudU5VGX9gTby6w8UPKLHXeWxuOq2mS2hWxjIXVsOanspXWJbnBaqydpXmNz+0qZ0K6VlEiL7YvVL9KHNpVAck1bz4toLrmrjM6E/RyphBl+3W5+XSGOJ4Z3dabuz/TQdgNlYAr9nd1NlLg8S2Pu1FsFygy79kpwKBDZFUiCTDwzmGgLSetFnOfr0NwC+NnF/bEB6gE3REcdtT0zyFj0SR8ZWIKRvRPu5BYXe9nKguLN0AUvshXm/MjhrxKmRWTjTTKZkax+f3zFut/yq8UDxMwGA15Ex60xkCcELgCcNPBrWdFDtQIDAQAB";
    }
    if (m_sPackageName.equals("com.ea.games.r3_na"))
    {
      Consts.Logger("getPublicKey NA");
      return "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsPSqZyfa7iZ9DOslXqDVPiyjsIjCCCDsp1x4pcuH9gFiivyVWhIAHrfMdLlg+6UmlbUoSyKwNRUKJA+H5N/L792/PewpOe2yXSvsqtdROJ12O34q8DPC2D7ezL8M/Io3bp9x18u/D5lf2PEQq4uBNEV1W1iK3PtsdWdpmNKMvnPDQXT3vciWdKa1R0g2xRHCXEK2Ft1Tlkx38ejAAmYvpvoP2e8IqnlwH2fz89G5nxnKdmGop1mz4KTZ8REmBCnmTb07" + "fRkSd4N7S64W2zu8lktMUUbGvCmRbt4zE48AzXEWmyHNW+mqx+IUKyK5wHObnDM9RSwKLWsDe++rrGgROQIDAQAB";
    }
    Consts.Logger("getPublicKey unknown package name");
    return null;
  }
  
  public void Constructor(Activity paramActivity, long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5)
  {
    m_pActivity = paramActivity;
    attachBaseContext(m_pActivity);
    m_nConstructCallback = paramLong1;
    m_nPurchaseStateChangedCallback = paramLong2;
    m_nInAppNotifyCallback = paramLong3;
    m_nResponseCodeCallback = paramLong4;
    m_nUserPointer = paramLong5;
    m_pService = null;
    m_sPackageName = getPackageName();
    try
    {
      if (!m_pActivity.bindService(new Intent("com.android.vending.billing.MarketBillingService.BIND"), this, 1)) {
        ConstructCallback(false, false, m_nConstructCallback, m_nUserPointer);
      }
      return;
    }
    catch (SecurityException localSecurityException)
    {
      ConstructCallback(false, false, m_nConstructCallback, m_nUserPointer);
    }
  }
  
  public void Purchase(final String paramString, final long paramLong1, long paramLong2)
  {
    Consts.Logger("Purchase");
    m_pActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        long l = 0L;
        Bundle localBundle2;
        PendingIntent localPendingIntent;
        Intent localIntent;
        try
        {
          Consts.Logger("Purchase0");
          Bundle localBundle1 = CC_GoogleStoreService_Class.MakeRequestBundle("REQUEST_PURCHASE");
          localBundle1.putString("ITEM_ID", paramString);
          localBundle1.putString("ITEM_TYPE", "inapp");
          localBundle1.putString("DEVELOPER_PAYLOAD", "pizza");
          localBundle2 = CC_GoogleStoreService_Class.m_pService.sendBillingRequest(localBundle1);
          Consts.Logger("Purchase1");
          if (localBundle2.getInt("RESPONSE_CODE") == 0)
          {
            Consts.Logger("Purchase2");
            l = localBundle2.getLong("REQUEST_ID");
            if (l <= 0L) {
              break label322;
            }
            Consts.Logger("Purchase3");
            Consts.Logger("purchase sent " + localBundle2.getInt("RESPONSE_CODE") + localBundle2.getLong("REQUEST_ID"));
            CC_GoogleStoreService_Class.PurchaseSetRequestId(l, paramLong1, this.val$nUserPointer);
            Consts.Logger("Purchase3-0");
          }
        }
        catch (RemoteException localRemoteException) {}
        try
        {
          localPendingIntent = (PendingIntent)localBundle2.getParcelable("PURCHASE_INTENT");
          Consts.Logger("Purchase3-1");
          if (localPendingIntent == null) {
            break label322;
          }
          CC_GoogleStoreService_Class.access$602(CC_GoogleStoreService_Class.this, CC_GoogleStoreService_Class.m_pActivity.getClass().getMethod("startIntentSender", CC_GoogleStoreService_Class.START_INTENT_SENDER_SIG));
          localIntent = new Intent();
          CC_GoogleStoreService_Class.this.m_pStartIntentSenderArgs[0] = localPendingIntent.getIntentSender();
          CC_GoogleStoreService_Class.this.m_pStartIntentSenderArgs[1] = localIntent;
          CC_GoogleStoreService_Class.this.m_pStartIntentSenderArgs[2] = Integer.valueOf(0);
          CC_GoogleStoreService_Class.this.m_pStartIntentSenderArgs[3] = Integer.valueOf(0);
          CC_GoogleStoreService_Class.this.m_pStartIntentSenderArgs[4] = Integer.valueOf(0);
          Consts.Logger("Purchase3-2");
          CC_GoogleStoreService_Class.this.m_mStartIntentSender.invoke(CC_GoogleStoreService_Class.m_pActivity, CC_GoogleStoreService_Class.this.m_pStartIntentSenderArgs);
          Consts.Logger("Purchase3-3");
          return;
        }
        catch (Exception localException)
        {
          break label322;
        }
        Consts.Logger("Purchase4");
        CC_GoogleStoreService_Class.ResponseCodeCallback(l, localBundle2.getInt("RESPONSE_CODE"), CC_GoogleStoreService_Class.m_nResponseCodeCallback, CC_GoogleStoreService_Class.m_nUserPointer);
        return;
        label322:
        Consts.Logger("DOOP!");
        CC_GoogleStoreService_Class.ResponseCodeCallback(l, 6, CC_GoogleStoreService_Class.m_nResponseCodeCallback, CC_GoogleStoreService_Class.m_nUserPointer);
        Consts.Logger("Purchase5");
      }
    });
  }
  
  public void RestorePurchase()
  {
    Consts.Logger("RestorePurchase Begin");
    m_pActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        try
        {
          Bundle localBundle1 = CC_GoogleStoreService_Class.MakeRequestBundle("RESTORE_TRANSACTIONS");
          localBundle1.putLong("NONCE", CC_Security.generateNonce());
          Bundle localBundle2 = CC_GoogleStoreService_Class.m_pService.sendBillingRequest(localBundle1);
          if (localBundle2.getInt("RESPONSE_CODE") == 0)
          {
            Consts.Logger("RestorePurchase Success " + localBundle2.getLong("REQUEST_ID"));
            return;
          }
          Consts.Logger("RestorePurchase Failure");
          return;
        }
        catch (RemoteException localRemoteException) {}
      }
    });
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    Consts.Logger("onBind");
    return null;
  }
  
  public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
  {
    Consts.Logger("onServiceConnected");
    m_pService = IMarketBillingService.Stub.asInterface(paramIBinder);
    m_pActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        try
        {
          Bundle localBundle = CC_GoogleStoreService_Class.MakeRequestBundle("CHECK_BILLING_SUPPORTED");
          if (CC_GoogleStoreService_Class.m_pService.sendBillingRequest(localBundle).getInt("RESPONSE_CODE") == 0)
          {
            Consts.Logger("onServiceConnected RES OK");
            CC_GoogleStoreService_Class.ConstructCallback(true, true, CC_GoogleStoreService_Class.m_nConstructCallback, CC_GoogleStoreService_Class.m_nUserPointer);
            return;
          }
          Consts.Logger("onServiceConnected RES FAIL");
          CC_GoogleStoreService_Class.ConstructCallback(false, false, CC_GoogleStoreService_Class.m_nConstructCallback, CC_GoogleStoreService_Class.m_nUserPointer);
          return;
        }
        catch (RemoteException localRemoteException)
        {
          CC_GoogleStoreService_Class.ConstructCallback(false, false, CC_GoogleStoreService_Class.m_nConstructCallback, CC_GoogleStoreService_Class.m_nUserPointer);
        }
      }
    });
  }
  
  public void onServiceDisconnected(ComponentName paramComponentName)
  {
    Consts.Logger("onServiceDisconnected");
    m_pService = null;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.firemonkeys.cloudcellapi.CC_GoogleStoreService_Class
 * JD-Core Version:    0.7.0.1
 */