package com.amazon.device.iap.internal.a;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import com.amazon.device.iap.PurchasingListener;
import com.amazon.device.iap.internal.model.ProductBuilder;
import com.amazon.device.iap.internal.model.ProductDataResponseBuilder;
import com.amazon.device.iap.internal.model.PurchaseResponseBuilder;
import com.amazon.device.iap.internal.model.PurchaseUpdatesResponseBuilder;
import com.amazon.device.iap.internal.model.ReceiptBuilder;
import com.amazon.device.iap.internal.model.UserDataBuilder;
import com.amazon.device.iap.internal.model.UserDataResponseBuilder;
import com.amazon.device.iap.internal.util.e;
import com.amazon.device.iap.model.FulfillmentResult;
import com.amazon.device.iap.model.Product;
import com.amazon.device.iap.model.ProductDataResponse;
import com.amazon.device.iap.model.ProductDataResponse.RequestStatus;
import com.amazon.device.iap.model.ProductType;
import com.amazon.device.iap.model.PurchaseResponse;
import com.amazon.device.iap.model.PurchaseResponse.RequestStatus;
import com.amazon.device.iap.model.PurchaseUpdatesResponse;
import com.amazon.device.iap.model.PurchaseUpdatesResponse.RequestStatus;
import com.amazon.device.iap.model.Receipt;
import com.amazon.device.iap.model.RequestId;
import com.amazon.device.iap.model.UserData;
import com.amazon.device.iap.model.UserDataResponse;
import com.amazon.device.iap.model.UserDataResponse.RequestStatus;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class c
  implements com.amazon.device.iap.internal.c
{
  private static final String a = c.class.getSimpleName();
  
  private Product a(String paramString, JSONObject paramJSONObject)
    throws JSONException
  {
    ProductType localProductType = ProductType.valueOf(paramJSONObject.optString("itemType"));
    JSONObject localJSONObject = paramJSONObject.getJSONObject("priceJson");
    Currency localCurrency = Currency.getInstance(localJSONObject.optString("currency"));
    BigDecimal localBigDecimal = new BigDecimal(localJSONObject.optString("value"));
    String str1 = localCurrency.getSymbol() + localBigDecimal;
    String str2 = paramJSONObject.optString("title");
    String str3 = paramJSONObject.optString("description");
    String str4 = paramJSONObject.optString("smallIconUrl");
    return new ProductBuilder().setSku(paramString).setProductType(localProductType).setDescription(str3).setPrice(str1).setSmallIconUrl(str4).setTitle(str2).build();
  }
  
  private Receipt a(JSONObject paramJSONObject)
    throws ParseException
  {
    String str1 = paramJSONObject.optString("receiptId");
    String str2 = paramJSONObject.optString("sku");
    ProductType localProductType = ProductType.valueOf(paramJSONObject.optString("itemType"));
    String str3 = paramJSONObject.optString("purchaseDate");
    Date localDate1 = b.a.parse(str3);
    String str4 = paramJSONObject.optString("cancelDate");
    if ((str4 == null) || (str4.length() == 0)) {}
    for (Date localDate2 = null;; localDate2 = b.a.parse(str4)) {
      return new ReceiptBuilder().setReceiptId(str1).setSku(str2).setProductType(localProductType).setPurchaseDate(localDate1).setCancelDate(localDate2).build();
    }
  }
  
  private void a(Intent paramIntent)
    throws JSONException
  {
    PurchaseUpdatesResponse localPurchaseUpdatesResponse = b(paramIntent);
    if (localPurchaseUpdatesResponse.getRequestStatus() == PurchaseUpdatesResponse.RequestStatus.SUCCESSFUL)
    {
      String str = new JSONObject(paramIntent.getStringExtra("purchaseUpdatesOutput")).optString("offset");
      Log.i(a, "Offset for PurchaseUpdatesResponse:" + str);
      com.amazon.device.iap.internal.util.b.a(localPurchaseUpdatesResponse.getUserData().getUserId(), str);
    }
    a(localPurchaseUpdatesResponse);
  }
  
  private void a(String paramString)
  {
    try
    {
      Context localContext = com.amazon.device.iap.internal.d.d().b();
      Bundle localBundle = new Bundle();
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("requestId", paramString);
      localJSONObject.put("packageName", localContext.getPackageName());
      localJSONObject.put("sdkVersion", "2.0.1");
      localBundle.putString("userInput", localJSONObject.toString());
      Intent localIntent = new Intent("com.amazon.testclient.iap.appUserId");
      localIntent.addFlags(268435456);
      localIntent.putExtras(localBundle);
      localContext.startService(localIntent);
      return;
    }
    catch (JSONException localJSONException)
    {
      e.b(a, "Error in sendGetUserDataRequest.");
    }
  }
  
  private void a(String paramString1, String paramString2)
  {
    try
    {
      Context localContext = com.amazon.device.iap.internal.d.d().b();
      boolean bool = "1".equals(paramString1.substring(1 + "GET_USER_ID_FOR_PURCHASE_UPDATES_PREFIX".length(), 2 + "GET_USER_ID_FOR_PURCHASE_UPDATES_PREFIX".length()));
      String str = com.amazon.device.iap.internal.util.b.a(paramString2);
      Log.i(a, "send PurchaseUpdates with user id:" + paramString2 + ";reset flag:" + bool + ", local cursor:" + str + ", parsed from old requestId:" + paramString1);
      RequestId localRequestId = new RequestId();
      Bundle localBundle = new Bundle();
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("requestId", localRequestId.toString());
      if (bool) {
        str = null;
      }
      localJSONObject.put("offset", str);
      localJSONObject.put("sdkVersion", "2.0.1");
      localJSONObject.put("packageName", localContext.getPackageName());
      localBundle.putString("purchaseUpdatesInput", localJSONObject.toString());
      Intent localIntent = new Intent("com.amazon.testclient.iap.purchaseUpdates");
      localIntent.addFlags(268435456);
      localIntent.putExtras(localBundle);
      localContext.startService(localIntent);
      return;
    }
    catch (JSONException localJSONException)
    {
      e.b(a, "Error in sendPurchaseUpdatesRequest.");
    }
  }
  
  private PurchaseUpdatesResponse b(Intent paramIntent)
  {
    localRequestStatus1 = PurchaseUpdatesResponse.RequestStatus.FAILED;
    for (;;)
    {
      try
      {
        localJSONObject1 = new JSONObject(paramIntent.getStringExtra("purchaseUpdatesOutput"));
        localRequestId2 = RequestId.fromString(localJSONObject1.optString("requestId"));
      }
      catch (Exception localException1)
      {
        JSONObject localJSONObject1;
        String str1;
        String str2;
        localObject = localException1;
        localRequestStatus2 = localRequestStatus1;
        localArrayList = null;
        bool1 = false;
        localUserData1 = null;
        localRequestId1 = null;
        continue;
      }
      try
      {
        localRequestStatus1 = PurchaseUpdatesResponse.RequestStatus.valueOf(localJSONObject1.optString("status"));
        bool2 = localJSONObject1.optBoolean("isMore");
      }
      catch (Exception localException2)
      {
        localRequestId1 = localRequestId2;
        localObject = localException2;
        localRequestStatus2 = localRequestStatus1;
        localArrayList = null;
        bool1 = false;
        localUserData1 = null;
        continue;
      }
      try
      {
        str1 = localJSONObject1.optString("userId");
        str2 = localJSONObject1.optString("marketplace");
        localUserData2 = new UserDataBuilder().setUserId(str1).setMarketplace(str2).build();
      }
      catch (Exception localException3)
      {
        localRequestId1 = localRequestId2;
        localObject = localException3;
        localRequestStatus2 = localRequestStatus1;
        bool1 = bool2;
        localArrayList = null;
        localUserData1 = null;
        continue;
      }
      try
      {
        if (localRequestStatus1 != PurchaseUpdatesResponse.RequestStatus.SUCCESSFUL) {
          continue;
        }
        localArrayList = new ArrayList();
        try
        {
          JSONArray localJSONArray = localJSONObject1.optJSONArray("receipts");
          int i = 0;
          if ((localJSONArray == null) || (i >= localJSONArray.length())) {
            continue;
          }
          JSONObject localJSONObject2 = localJSONArray.optJSONObject(i);
          try
          {
            localArrayList.add(a(localJSONObject2));
            i++;
          }
          catch (Exception localException6)
          {
            Log.e(a, "Failed to parse receipt from json:" + localJSONObject2);
            continue;
          }
          Log.e(a, "Error parsing purchase updates output", (Throwable)localObject);
        }
        catch (Exception localException5)
        {
          localRequestStatus2 = localRequestStatus1;
          bool1 = bool2;
          localUserData1 = localUserData2;
          localRequestId1 = localRequestId2;
          localObject = localException5;
        }
      }
      catch (Exception localException4)
      {
        localRequestStatus2 = localRequestStatus1;
        bool1 = bool2;
        localUserData1 = localUserData2;
        localRequestId1 = localRequestId2;
        localObject = localException4;
        localArrayList = null;
        continue;
      }
      return new PurchaseUpdatesResponseBuilder().setRequestId(localRequestId1).setRequestStatus(localRequestStatus2).setUserData(localUserData1).setReceipts(localArrayList).setHasMore(bool1).build();
      localArrayList = null;
      localRequestStatus2 = localRequestStatus1;
      bool1 = bool2;
      localUserData1 = localUserData2;
      localRequestId1 = localRequestId2;
    }
  }
  
  private void c(Intent paramIntent)
  {
    a(d(paramIntent));
  }
  
  private ProductDataResponse d(Intent paramIntent)
  {
    localRequestStatus1 = ProductDataResponse.RequestStatus.FAILED;
    for (;;)
    {
      try
      {
        localJSONObject1 = new JSONObject(paramIntent.getStringExtra("itemDataOutput"));
        localRequestId2 = RequestId.fromString(localJSONObject1.optString("requestId"));
      }
      catch (Exception localException1)
      {
        JSONObject localJSONObject1;
        HashMap localHashMap2;
        label200:
        label243:
        label251:
        localObject1 = localException1;
        localObject2 = localRequestStatus1;
        localHashMap1 = null;
        localObject3 = null;
        localRequestId1 = null;
        continue;
      }
      for (;;)
      {
        try
        {
          localRequestStatus1 = ProductDataResponse.RequestStatus.valueOf(localJSONObject1.optString("status"));
          if (localRequestStatus1 == ProductDataResponse.RequestStatus.FAILED) {
            continue;
          }
          localObject3 = new LinkedHashSet();
        }
        catch (Exception localException2)
        {
          ProductDataResponse.RequestStatus localRequestStatus2 = localRequestStatus1;
          localRequestId1 = localRequestId2;
          localObject1 = localException2;
          localObject2 = localRequestStatus2;
          localHashMap1 = null;
          localObject3 = null;
          break label200;
          localHashMap1 = null;
          localObject4 = null;
          break label251;
        }
        try
        {
          localHashMap2 = new HashMap();
          try
          {
            JSONArray localJSONArray = localJSONObject1.optJSONArray("unavailableSkus");
            if (localJSONArray != null) {
              for (int i = 0; i < localJSONArray.length(); i++) {
                ((Set)localObject3).add(localJSONArray.getString(i));
              }
            }
            JSONObject localJSONObject2 = localJSONObject1.optJSONObject("items");
            if (localJSONObject2 == null) {
              break label243;
            }
            Iterator localIterator = localJSONObject2.keys();
            while (localIterator.hasNext())
            {
              String str = (String)localIterator.next();
              localHashMap2.put(str, a(str, localJSONObject2.optJSONObject(str)));
            }
            Log.e(a, "Error parsing item data output", (Throwable)localObject1);
          }
          catch (Exception localException3)
          {
            localHashMap1 = localHashMap2;
            localObject2 = localRequestStatus1;
            localRequestId1 = localRequestId2;
            localObject1 = localException3;
          }
        }
        catch (Exception localException4)
        {
          localObject2 = localRequestStatus1;
          localRequestId1 = localRequestId2;
          localObject1 = localException4;
          localHashMap1 = null;
          break label200;
        }
      }
      return new ProductDataResponseBuilder().setRequestId(localRequestId1).setRequestStatus((ProductDataResponse.RequestStatus)localObject2).setProductData(localHashMap1).setUnavailableSkus((Set)localObject3).build();
      localHashMap1 = localHashMap2;
      localObject4 = localObject3;
      localObject3 = localObject4;
      localObject2 = localRequestStatus1;
      localRequestId1 = localRequestId2;
    }
  }
  
  private void e(Intent paramIntent)
  {
    UserDataResponse localUserDataResponse = f(paramIntent);
    if ((localUserDataResponse.getRequestId() != null) && (localUserDataResponse.getRequestId().toString().startsWith("GET_USER_ID_FOR_PURCHASE_UPDATES_PREFIX")))
    {
      if ((localUserDataResponse.getUserData() == null) || (com.amazon.device.iap.internal.util.d.a(localUserDataResponse.getUserData().getUserId())))
      {
        Log.e(a, "No Userid found in userDataResponse" + localUserDataResponse);
        a(new PurchaseUpdatesResponseBuilder().setRequestId(localUserDataResponse.getRequestId()).setRequestStatus(PurchaseUpdatesResponse.RequestStatus.FAILED).setUserData(localUserDataResponse.getUserData()).setReceipts(new ArrayList()).setHasMore(false).build());
        return;
      }
      Log.i(a, "sendGetPurchaseUpdates with user id" + localUserDataResponse.getUserData().getUserId());
      a(localUserDataResponse.getRequestId().toString(), localUserDataResponse.getUserData().getUserId());
      return;
    }
    a(localUserDataResponse);
  }
  
  private UserDataResponse f(Intent paramIntent)
  {
    localRequestStatus1 = UserDataResponse.RequestStatus.FAILED;
    for (;;)
    {
      try
      {
        JSONObject localJSONObject = new JSONObject(paramIntent.getStringExtra("userOutput"));
        RequestId localRequestId2 = RequestId.fromString(localJSONObject.optString("requestId"));
        RequestId localRequestId1 = localRequestId2;
        UserDataResponse.RequestStatus localRequestStatus4;
        UserDataResponse.RequestStatus localRequestStatus5;
        Object localObject3;
        String str1;
        String str2;
        UserData localUserData;
        UserDataResponse.RequestStatus localRequestStatus6;
        Log.e(a, "Error parsing userid output", (Throwable)localObject1);
      }
      catch (Exception localException1)
      {
        try
        {
          localRequestStatus4 = UserDataResponse.RequestStatus.valueOf(localJSONObject.optString("status"));
          localRequestStatus2 = localRequestStatus4;
        }
        catch (Exception localException3)
        {
          for (;;)
          {
            Object localObject2;
            UserDataResponse.RequestStatus localRequestStatus3;
            UserDataResponse.RequestStatus localRequestStatus2 = localRequestStatus1;
            Object localObject1 = localException3;
          }
        }
        try
        {
          localRequestStatus5 = UserDataResponse.RequestStatus.SUCCESSFUL;
          localObject3 = null;
          if (localRequestStatus2 == localRequestStatus5)
          {
            str1 = localJSONObject.optString("userId");
            str2 = localJSONObject.optString("marketplace");
            localUserData = new UserDataBuilder().setUserId(str1).setMarketplace(str2).build();
            localObject3 = localUserData;
          }
          localRequestStatus6 = localRequestStatus2;
          localObject2 = localObject3;
          localRequestStatus3 = localRequestStatus6;
          return new UserDataResponseBuilder().setRequestId(localRequestId1).setRequestStatus(localRequestStatus3).setUserData(localObject2).build();
        }
        catch (Exception localException2)
        {
          break label159;
        }
        localException1 = localException1;
        localRequestId1 = null;
        localObject1 = localException1;
        localRequestStatus2 = localRequestStatus1;
      }
      label159:
      localRequestStatus3 = localRequestStatus2;
      localObject2 = null;
    }
  }
  
  private void g(Intent paramIntent)
  {
    a(h(paramIntent));
  }
  
  private PurchaseResponse h(Intent paramIntent)
  {
    localRequestStatus1 = PurchaseResponse.RequestStatus.FAILED;
    for (;;)
    {
      try
      {
        JSONObject localJSONObject1 = new JSONObject(paramIntent.getStringExtra("purchaseOutput"));
        RequestId localRequestId2 = RequestId.fromString(localJSONObject1.optString("requestId"));
        RequestId localRequestId1 = localRequestId2;
        String str1;
        String str2;
        UserData localUserData2;
        PurchaseResponse.RequestStatus localRequestStatus3;
        JSONObject localJSONObject2;
        Receipt localReceipt;
        Log.e(a, "Error parsing purchase output", (Throwable)localObject1);
      }
      catch (Exception localException1)
      {
        try
        {
          str1 = localJSONObject1.optString("userId");
          str2 = localJSONObject1.optString("marketplace");
          localUserData2 = new UserDataBuilder().setUserId(str1).setMarketplace(str2).build();
          localUserData1 = localUserData2;
        }
        catch (Exception localException3)
        {
          for (;;)
          {
            Object localObject2;
            localObject1 = localException3;
            localRequestStatus2 = localRequestStatus1;
            UserData localUserData1 = null;
          }
        }
        try
        {
          localRequestStatus3 = PurchaseResponse.RequestStatus.safeValueOf(localJSONObject1.optString("purchaseStatus"));
          localRequestStatus2 = localRequestStatus3;
        }
        catch (Exception localException4)
        {
          localRequestStatus2 = localRequestStatus1;
          localObject1 = localException4;
          break label169;
        }
        try
        {
          localJSONObject2 = localJSONObject1.optJSONObject("receipt");
          localObject2 = null;
          if (localJSONObject2 != null)
          {
            localReceipt = a(localJSONObject2);
            localObject2 = localReceipt;
          }
          return new PurchaseResponseBuilder().setRequestId(localRequestId1).setRequestStatus(localRequestStatus2).setUserData(localUserData1).setReceipt(localObject2).build();
        }
        catch (Exception localException2)
        {
          break label169;
        }
        localException1 = localException1;
        localUserData1 = null;
        localRequestId1 = null;
        localRequestStatus2 = localRequestStatus1;
        localObject1 = localException1;
      }
      label169:
      localObject2 = null;
    }
  }
  
  public void a(Context paramContext, Intent paramIntent)
  {
    e.a(a, "handleResponse");
    String str;
    try
    {
      str = paramIntent.getExtras().getString("responseType");
      if (str.equalsIgnoreCase("com.amazon.testclient.iap.purchase"))
      {
        g(paramIntent);
        return;
      }
      if (str.equalsIgnoreCase("com.amazon.testclient.iap.appUserId"))
      {
        e(paramIntent);
        return;
      }
    }
    catch (Exception localException)
    {
      Log.e(a, "Error handling response.", localException);
      return;
    }
    if (str.equalsIgnoreCase("com.amazon.testclient.iap.itemData"))
    {
      c(paramIntent);
      return;
    }
    if (str.equalsIgnoreCase("com.amazon.testclient.iap.purchaseUpdates")) {
      a(paramIntent);
    }
  }
  
  public void a(RequestId paramRequestId)
  {
    e.a(a, "sendGetUserDataRequest");
    a(paramRequestId.toString());
  }
  
  public void a(RequestId paramRequestId, String paramString)
  {
    e.a(a, "sendPurchaseRequest");
    try
    {
      Context localContext = com.amazon.device.iap.internal.d.d().b();
      Bundle localBundle = new Bundle();
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("sku", paramString);
      localJSONObject.put("requestId", paramRequestId.toString());
      localJSONObject.put("packageName", localContext.getPackageName());
      localJSONObject.put("sdkVersion", "2.0.1");
      localBundle.putString("purchaseInput", localJSONObject.toString());
      Intent localIntent = new Intent("com.amazon.testclient.iap.purchase");
      localIntent.addFlags(268435456);
      localIntent.putExtras(localBundle);
      localContext.startService(localIntent);
      return;
    }
    catch (JSONException localJSONException)
    {
      e.b(a, "Error in sendPurchaseRequest.");
    }
  }
  
  public void a(RequestId paramRequestId, String paramString, FulfillmentResult paramFulfillmentResult)
  {
    e.a(a, "sendNotifyPurchaseFulfilled");
    try
    {
      Context localContext = com.amazon.device.iap.internal.d.d().b();
      Bundle localBundle = new Bundle();
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("requestId", paramRequestId.toString());
      localJSONObject.put("packageName", localContext.getPackageName());
      localJSONObject.put("receiptId", paramString);
      localJSONObject.put("fulfillmentResult", paramFulfillmentResult);
      localJSONObject.put("sdkVersion", "2.0.1");
      localBundle.putString("purchaseFulfilledInput", localJSONObject.toString());
      Intent localIntent = new Intent("com.amazon.testclient.iap.purchaseFulfilled");
      localIntent.addFlags(268435456);
      localIntent.putExtras(localBundle);
      localContext.startService(localIntent);
      return;
    }
    catch (JSONException localJSONException)
    {
      e.b(a, "Error in sendNotifyPurchaseFulfilled.");
    }
  }
  
  public void a(RequestId paramRequestId, Set<String> paramSet)
  {
    e.a(a, "sendItemDataRequest");
    try
    {
      Context localContext = com.amazon.device.iap.internal.d.d().b();
      Bundle localBundle = new Bundle();
      JSONObject localJSONObject = new JSONObject();
      JSONArray localJSONArray = new JSONArray(paramSet);
      localJSONObject.put("requestId", paramRequestId.toString());
      localJSONObject.put("packageName", localContext.getPackageName());
      localJSONObject.put("skus", localJSONArray);
      localJSONObject.put("sdkVersion", "2.0.1");
      localBundle.putString("itemDataInput", localJSONObject.toString());
      Intent localIntent = new Intent("com.amazon.testclient.iap.itemData");
      localIntent.addFlags(268435456);
      localIntent.putExtras(localBundle);
      localContext.startService(localIntent);
      return;
    }
    catch (JSONException localJSONException)
    {
      e.b(a, "Error in sendItemDataRequest.");
    }
  }
  
  public void a(RequestId paramRequestId, boolean paramBoolean)
  {
    StringBuilder localStringBuilder = new StringBuilder().append("GET_USER_ID_FOR_PURCHASE_UPDATES_PREFIX:");
    if (paramBoolean) {}
    for (int i = 1;; i = 0)
    {
      String str = i + ":" + new RequestId().toString();
      e.a(a, "sendPurchaseUpdatesRequest/sendGetUserData first:" + str);
      a(str);
      return;
    }
  }
  
  protected void a(final Object paramObject)
  {
    com.amazon.device.iap.internal.util.d.a(paramObject, "response");
    Context localContext = com.amazon.device.iap.internal.d.d().b();
    final PurchasingListener localPurchasingListener = com.amazon.device.iap.internal.d.d().a();
    if ((localContext == null) || (localPurchasingListener == null))
    {
      e.a(a, "PurchasingListener is not set. Dropping response: " + paramObject);
      return;
    }
    Runnable local1 = new Runnable()
    {
      public void run()
      {
        try
        {
          if ((paramObject instanceof ProductDataResponse))
          {
            localPurchasingListener.onProductDataResponse((ProductDataResponse)paramObject);
            return;
          }
          if ((paramObject instanceof UserDataResponse))
          {
            localPurchasingListener.onUserDataResponse((UserDataResponse)paramObject);
            return;
          }
        }
        catch (Exception localException)
        {
          e.b(c.a(), "Error in sendResponse: " + localException);
          return;
        }
        if ((paramObject instanceof PurchaseUpdatesResponse))
        {
          localPurchasingListener.onPurchaseUpdatesResponse((PurchaseUpdatesResponse)paramObject);
          return;
        }
        if ((paramObject instanceof PurchaseResponse))
        {
          localPurchasingListener.onPurchaseResponse((PurchaseResponse)paramObject);
          return;
        }
        e.b(c.a(), "Unknown response type:" + paramObject.getClass().getName());
      }
    };
    new Handler(localContext.getMainLooper()).post(local1);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.amazon.device.iap.internal.a.c
 * JD-Core Version:    0.7.0.1
 */