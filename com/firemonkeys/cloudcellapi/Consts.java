package com.firemonkeys.cloudcellapi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class Consts
{
  public static final String ACTION_CONFIRM_NOTIFICATION = "com.firemonkeys.cloudcellapi.CONFIRM_NOTIFICATION";
  public static final String ACTION_GET_PURCHASE_INFORMATION = "com.firemonkeys.cloudcellapi.GET_PURCHASE_INFORMATION";
  public static final String ACTION_NOTIFY = "com.android.vending.billing.IN_APP_NOTIFY";
  public static final String ACTION_PURCHASE_STATE_CHANGED = "com.android.vending.billing.PURCHASE_STATE_CHANGED";
  public static final String ACTION_RESPONSE_CODE = "com.android.vending.billing.RESPONSE_CODE";
  public static final String ACTION_RESTORE_TRANSACTIONS = "com.firemonkeys.cloudcellapi.RESTORE_TRANSACTIONS";
  public static final String BILLING_CHECK_BILLING_SUPPORTED = "CHECK_BILLING_SUPPORTED";
  public static final String BILLING_CONFIRM_NOTIFICATIONS = "CONFIRM_NOTIFICATIONS";
  public static final String BILLING_GET_PURCHASE_INFORMATION = "GET_PURCHASE_INFORMATION";
  public static final int BILLING_MAX_SKUS_PER_QUERY = 20;
  public static final String BILLING_REQUEST_API_VERSION = "API_VERSION";
  public static final String BILLING_REQUEST_DEVELOPER_PAYLOAD = "DEVELOPER_PAYLOAD";
  public static final String BILLING_REQUEST_ITEM_ID = "ITEM_ID";
  public static final String BILLING_REQUEST_ITEM_TYPE = "ITEM_TYPE";
  public static final String BILLING_REQUEST_METHOD = "BILLING_REQUEST";
  public static final String BILLING_REQUEST_NONCE = "NONCE";
  public static final String BILLING_REQUEST_NOTIFY_IDS = "NOTIFY_IDS";
  public static final String BILLING_REQUEST_PACKAGE_NAME = "PACKAGE_NAME";
  public static final String BILLING_REQUEST_PURCHASE = "REQUEST_PURCHASE";
  public static long BILLING_RESPONSE_INVALID_REQUEST_ID = -1L;
  public static final String BILLING_RESPONSE_PURCHASE_INTENT = "PURCHASE_INTENT";
  public static final String BILLING_RESPONSE_REQUEST_ID = "REQUEST_ID";
  public static final String BILLING_RESPONSE_RESPONSE_CODE = "RESPONSE_CODE";
  public static final String BILLING_RESTORE_TRANSACTIONS = "RESTORE_TRANSACTIONS";
  public static boolean DEBUG = false;
  public static final String GET_SKU_DETAILS_ITEM_LIST = "ITEM_ID_LIST";
  public static final String GET_SKU_DETAILS_ITEM_TYPE_LIST = "ITEM_TYPE_LIST";
  public static final String IAB_TAG = "IAB";
  public static final String INAPP_CONTINUATION_TOKEN = "INAPP_CONTINUATION_TOKEN";
  public static final String INAPP_REQUEST_ID = "request_id";
  public static final String INAPP_RESPONSE_CODE = "response_code";
  public static final String INAPP_SIGNATURE = "inapp_signature";
  public static final String INAPP_SIGNED_DATA = "inapp_signed_data";
  public static final String ITEM_TYPE_INAPP = "inapp";
  public static final String ITEM_TYPE_SUBSCRIPTION = "subs";
  public static final String MARKET_BILLING_SERVICE_ACTION = "com.android.vending.billing.MarketBillingService.BIND";
  public static final String NOTIFICATION_ID = "notification_id";
  public static final String RESPONSE_BUY_INTENT = "BUY_INTENT";
  public static final String RESPONSE_CODE = "RESPONSE_CODE";
  public static final String RESPONSE_GET_SKU_DETAILS_LIST = "DETAILS_LIST";
  public static final String RESPONSE_INAPP_ITEM_LIST = "INAPP_PURCHASE_ITEM_LIST";
  public static final String RESPONSE_INAPP_PURCHASE_DATA = "INAPP_PURCHASE_DATA";
  public static final String RESPONSE_INAPP_PURCHASE_DATA_LIST = "INAPP_PURCHASE_DATA_LIST";
  public static final String RESPONSE_INAPP_SIGNATURE = "INAPP_DATA_SIGNATURE";
  public static final String RESPONSE_INAPP_SIGNATURE_LIST = "INAPP_DATA_SIGNATURE_LIST";
  
  static void Logger(String paramString)
  {
    if (DEBUG) {
      Log.d("IAB", paramString);
    }
  }
  
  public static void enableLog()
  {
    DEBUG = true;
  }
  
  static ResponseCode getResponseCodeFromBundle(Bundle paramBundle)
  {
    Object localObject = paramBundle.get("RESPONSE_CODE");
    if (localObject == null)
    {
      Logger("Bundle with null response code, assuming OK (known issue)");
      return ResponseCode.RESULT_OK;
    }
    if ((localObject instanceof Integer)) {
      return ResponseCode.valueOf(((Integer)localObject).intValue());
    }
    if ((localObject instanceof Long)) {
      return ResponseCode.valueOf((int)((Long)localObject).longValue());
    }
    Logger("Unexpected type for bundle response code.");
    Logger(localObject.getClass().getName());
    throw new RuntimeException("Unexpected type for bundle response code: " + localObject.getClass().getName());
  }
  
  static ResponseCode getResponseCodeFromIntent(Intent paramIntent)
  {
    Object localObject = paramIntent.getExtras().get("RESPONSE_CODE");
    if (localObject == null)
    {
      Logger("Intent with no response code, assuming OK (known issue)");
      return ResponseCode.RESULT_OK;
    }
    if ((localObject instanceof Integer)) {
      return ResponseCode.valueOf(((Integer)localObject).intValue());
    }
    if ((localObject instanceof Long)) {
      return ResponseCode.valueOf((int)((Long)localObject).longValue());
    }
    Logger("Unexpected type for intent response code.");
    Logger(localObject.getClass().getName());
    throw new RuntimeException("Unexpected type for intent response code: " + localObject.getClass().getName());
  }
  
  public static enum PurchaseState
  {
    static
    {
      CANCELED = new PurchaseState("CANCELED", 1);
      REFUNDED = new PurchaseState("REFUNDED", 2);
      PurchaseState[] arrayOfPurchaseState = new PurchaseState[3];
      arrayOfPurchaseState[0] = PURCHASED;
      arrayOfPurchaseState[1] = CANCELED;
      arrayOfPurchaseState[2] = REFUNDED;
      $VALUES = arrayOfPurchaseState;
    }
    
    private PurchaseState() {}
    
    public static String toString(PurchaseState paramPurchaseState)
    {
      long l = 1L;
      switch (Consts.1.$SwitchMap$com$firemonkeys$cloudcellapi$Consts$PurchaseState[paramPurchaseState.ordinal()])
      {
      }
      for (;;)
      {
        return String.valueOf(l);
        l = 0L;
        continue;
        l = 1L;
        continue;
        l = 2L;
      }
    }
    
    public static PurchaseState valueOf(int paramInt)
    {
      PurchaseState[] arrayOfPurchaseState = values();
      if ((paramInt < 0) || (paramInt >= arrayOfPurchaseState.length)) {
        return CANCELED;
      }
      return arrayOfPurchaseState[paramInt];
    }
  }
  
  public static enum ResponseCode
  {
    private int m_value;
    
    static
    {
      RESULT_SERVICE_UNAVAILABLE = new ResponseCode("RESULT_SERVICE_UNAVAILABLE", 2, 2);
      RESULT_BILLING_UNAVAILABLE = new ResponseCode("RESULT_BILLING_UNAVAILABLE", 3, 3);
      RESULT_ITEM_UNAVAILABLE = new ResponseCode("RESULT_ITEM_UNAVAILABLE", 4, 4);
      RESULT_DEVELOPER_ERROR = new ResponseCode("RESULT_DEVELOPER_ERROR", 5, 5);
      RESULT_ERROR = new ResponseCode("RESULT_ERROR", 6, 6);
      RESULT_ITEM_ALREADY_OWNED = new ResponseCode("RESULT_ITEM_ALREADY_OWNED", 7, 7);
      RESULT_ITEM_NOT_OWNED = new ResponseCode("RESULT_ITEM_NOT_OWNED", 8, 8);
      ResponseCode[] arrayOfResponseCode = new ResponseCode[9];
      arrayOfResponseCode[0] = RESULT_OK;
      arrayOfResponseCode[1] = RESULT_USER_CANCELED;
      arrayOfResponseCode[2] = RESULT_SERVICE_UNAVAILABLE;
      arrayOfResponseCode[3] = RESULT_BILLING_UNAVAILABLE;
      arrayOfResponseCode[4] = RESULT_ITEM_UNAVAILABLE;
      arrayOfResponseCode[5] = RESULT_DEVELOPER_ERROR;
      arrayOfResponseCode[6] = RESULT_ERROR;
      arrayOfResponseCode[7] = RESULT_ITEM_ALREADY_OWNED;
      arrayOfResponseCode[8] = RESULT_ITEM_NOT_OWNED;
      $VALUES = arrayOfResponseCode;
    }
    
    private ResponseCode(int paramInt)
    {
      this.m_value = paramInt;
    }
    
    public static ResponseCode valueOf(int paramInt)
    {
      ResponseCode[] arrayOfResponseCode = values();
      if ((paramInt < 0) || (paramInt >= arrayOfResponseCode.length)) {
        return RESULT_ERROR;
      }
      return arrayOfResponseCode[paramInt];
    }
    
    public int getValue()
    {
      return this.m_value;
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.firemonkeys.cloudcellapi.Consts
 * JD-Core Version:    0.7.0.1
 */