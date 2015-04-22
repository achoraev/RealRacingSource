package com.firemonkeys.cloudcellapi;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import com.amazon.device.iap.PurchasingListener;
import com.amazon.device.iap.PurchasingService;
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
import com.amazon.device.iap.model.UserData;
import com.amazon.device.iap.model.UserDataResponse;
import com.amazon.device.iap.model.UserDataResponse.RequestStatus;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CC_AmazonStoreObserver_Class
  implements PurchasingListener
{
  private static long m_nConstructCallback = 0L;
  private static long m_nProductDetailsCallback = 0L;
  private static long m_nPurchaseErrorCallback = 0L;
  private static long m_nPurchaseSucceedCallback = 0L;
  private static long m_nRestoreCallback = 0L;
  private static long m_nUserPointer = 0L;
  private static final String s_sAppTesterAccount1 = "l3HL7XppEMhrOGDnur9-ulvqomrSg6qyODKmah76lJU=";
  private String currentUser;
  ArrayList<Receipt> mRestoreReceipts = new ArrayList();
  String sActivePurchaseProductId = null;
  
  CC_AmazonStoreObserver_Class(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6)
  {
    m_nConstructCallback = paramLong1;
    m_nProductDetailsCallback = paramLong2;
    m_nPurchaseSucceedCallback = paramLong3;
    m_nPurchaseErrorCallback = paramLong4;
    m_nRestoreCallback = paramLong5;
    m_nUserPointer = paramLong6;
  }
  
  private static native void ConstructCallback(boolean paramBoolean1, boolean paramBoolean2, long paramLong1, long paramLong2);
  
  private static native void ProductDetailsCallback(boolean paramBoolean, Product[] paramArrayOfProduct, long paramLong1, long paramLong2);
  
  private static native void PurchaseErrorCallback(String paramString, long paramLong1, long paramLong2, long paramLong3);
  
  private static native void PurchaseSucceedCallback(String paramString, Receipt paramReceipt, long paramLong1, long paramLong2);
  
  private static native void RefreshCallback(boolean paramBoolean, long paramLong);
  
  private static native void RestoreCallback(boolean paramBoolean, String paramString, Receipt[] paramArrayOfReceipt, long paramLong1, long paramLong2);
  
  private SharedPreferences.Editor getSharedPreferencesEditor()
  {
    return getSharedPreferencesForCurrentUser().edit();
  }
  
  private SharedPreferences getSharedPreferencesForCurrentUser()
  {
    return CC_Activity.GetActivity().getSharedPreferences(getCurrentUser(), 0);
  }
  
  private void printReceipt(Receipt paramReceipt)
  {
    if (paramReceipt == null)
    {
      Consts.Logger("Receipt: null");
      return;
    }
    Object[] arrayOfObject = new Object[4];
    arrayOfObject[0] = paramReceipt.getProductType();
    arrayOfObject[1] = paramReceipt.getSku();
    arrayOfObject[2] = paramReceipt.getPurchaseDate();
    arrayOfObject[3] = paramReceipt.getCancelDate();
    Consts.Logger(String.format("Receipt: ProductType: %s Sku: %s SubscriptionPeriod: %s - %s", arrayOfObject));
  }
  
  public void CompleteTransaction(String paramString, Boolean paramBoolean)
  {
    Consts.Logger("CompleteTransaction");
    Consts.Logger("fulfilled: " + paramBoolean);
    if (paramBoolean.booleanValue())
    {
      PurchasingService.notifyFulfillment(paramString, FulfillmentResult.FULFILLED);
      return;
    }
    PurchasingService.notifyFulfillment(paramString, FulfillmentResult.UNAVAILABLE);
  }
  
  void Purchase(String paramString)
  {
    Consts.Logger("Purchasing " + paramString);
    this.sActivePurchaseProductId = paramString;
    PurchasingService.purchase(paramString);
  }
  
  public void RefreshStorePurchases()
  {
    Consts.Logger("RefreshStorePurchases");
    PurchasingService.getPurchaseUpdates(false);
  }
  
  public void RestorePurchase()
  {
    Consts.Logger("RestorePurchase");
    this.mRestoreReceipts.clear();
    PurchasingService.getPurchaseUpdates(true);
  }
  
  String getCurrentUser()
  {
    return this.currentUser;
  }
  
  public void getProductDetails(String[] paramArrayOfString)
  {
    Consts.Logger("getProductDetails Begin");
    HashSet localHashSet = new HashSet();
    int i = paramArrayOfString.length;
    for (int j = 0; j < i; j++) {
      localHashSet.add(paramArrayOfString[j]);
    }
    PurchasingService.getProductData(localHashSet);
  }
  
  public void onProductDataResponse(ProductDataResponse paramProductDataResponse)
  {
    Consts.Logger("onProductDataResponse recieved");
    Consts.Logger("ProductDataRequestStatus" + paramProductDataResponse.getRequestStatus());
    Consts.Logger("ProductDataRequestId" + paramProductDataResponse.getRequestId());
    new ProductDataAsyncTask(null).execute(new ProductDataResponse[] { paramProductDataResponse });
  }
  
  public void onPurchaseResponse(PurchaseResponse paramPurchaseResponse)
  {
    Consts.Logger("onPurchaseResponse recieved");
    Consts.Logger("PurchaseRequestStatus: " + paramPurchaseResponse.getRequestStatus());
    Receipt localReceipt = paramPurchaseResponse.getReceipt();
    printReceipt(localReceipt);
    switch (1.$SwitchMap$com$amazon$device$iap$model$PurchaseResponse$RequestStatus[paramPurchaseResponse.getRequestStatus().ordinal()])
    {
    }
    for (;;)
    {
      this.sActivePurchaseProductId = null;
      return;
      PurchaseSucceedCallback(paramPurchaseResponse.getUserData().getUserId(), localReceipt, m_nPurchaseSucceedCallback, m_nUserPointer);
      continue;
      PurchaseErrorCallback(this.sActivePurchaseProductId, paramPurchaseResponse.getRequestStatus().ordinal(), m_nPurchaseErrorCallback, m_nUserPointer);
    }
  }
  
  public void onPurchaseUpdatesResponse(PurchaseUpdatesResponse paramPurchaseUpdatesResponse)
  {
    Consts.Logger("onPurchaseUpdatesRecived received: Response -" + paramPurchaseUpdatesResponse);
    Consts.Logger("RequestStatus:" + paramPurchaseUpdatesResponse.getRequestStatus());
    Consts.Logger("RequestID:" + paramPurchaseUpdatesResponse.getRequestId());
    new PurchaseUpdatesAsyncTask(null).execute(new PurchaseUpdatesResponse[] { paramPurchaseUpdatesResponse });
  }
  
  public void onUserDataResponse(UserDataResponse paramUserDataResponse)
  {
    Consts.Logger("onUserDataResponse recieved: Response " + paramUserDataResponse);
    Consts.Logger("RequestId:" + paramUserDataResponse.getRequestId());
    Consts.Logger("IdRequestStatus:" + paramUserDataResponse.getRequestStatus());
    new GetUserIdAsyncTask(null).execute(new UserDataResponse[] { paramUserDataResponse });
  }
  
  void setCurrentUser(String paramString)
  {
    this.currentUser = paramString;
  }
  
  private class GetUserIdAsyncTask
    extends AsyncTask<UserDataResponse, Void, Boolean>
  {
    private GetUserIdAsyncTask() {}
    
    protected Boolean doInBackground(UserDataResponse... paramVarArgs)
    {
      UserDataResponse localUserDataResponse = paramVarArgs[0];
      if (localUserDataResponse.getRequestStatus() == UserDataResponse.RequestStatus.SUCCESSFUL)
      {
        Consts.Logger("doInBackground SUCCESSFUL");
        String str = localUserDataResponse.getUserData().getUserId();
        CC_AmazonStoreObserver_Class.this.setCurrentUser(str);
        return Boolean.valueOf(true);
      }
      Consts.Logger("doInBackground: Unable to get user ID.");
      return Boolean.valueOf(false);
    }
    
    protected void onPostExecute(Boolean paramBoolean)
    {
      super.onPostExecute(paramBoolean);
      CC_AmazonStoreObserver_Class.ConstructCallback(paramBoolean.booleanValue(), paramBoolean.booleanValue(), CC_AmazonStoreObserver_Class.m_nConstructCallback, CC_AmazonStoreObserver_Class.m_nUserPointer);
    }
  }
  
  private class ProductDataAsyncTask
    extends AsyncTask<ProductDataResponse, Void, Void>
  {
    private ProductDataAsyncTask() {}
    
    protected Void doInBackground(ProductDataResponse... paramVarArgs)
    {
      ProductDataResponse localProductDataResponse = paramVarArgs[0];
      switch (CC_AmazonStoreObserver_Class.1.$SwitchMap$com$amazon$device$iap$model$ProductDataResponse$RequestStatus[localProductDataResponse.getRequestStatus().ordinal()])
      {
      }
      for (;;)
      {
        return null;
        Map localMap = localProductDataResponse.getProductData();
        Product[] arrayOfProduct = new Product[localMap.size()];
        int i = 0;
        Iterator localIterator = localMap.keySet().iterator();
        while (localIterator.hasNext())
        {
          Product localProduct = (Product)localMap.get((String)localIterator.next());
          Object[] arrayOfObject = new Object[5];
          arrayOfObject[0] = localProduct.getTitle();
          arrayOfObject[1] = localProduct.getProductType();
          arrayOfObject[2] = localProduct.getSku();
          arrayOfObject[3] = localProduct.getPrice();
          arrayOfObject[4] = localProduct.getDescription();
          Consts.Logger(String.format("Product: %s\n Type: %s\n SKU: %s\n Price: %s\n Description: %s\n", arrayOfObject));
          arrayOfProduct[i] = localProduct;
          i++;
        }
        CC_AmazonStoreObserver_Class.ProductDetailsCallback(true, arrayOfProduct, CC_AmazonStoreObserver_Class.m_nProductDetailsCallback, CC_AmazonStoreObserver_Class.m_nUserPointer);
        continue;
        CC_AmazonStoreObserver_Class.ProductDetailsCallback(false, null, CC_AmazonStoreObserver_Class.m_nProductDetailsCallback, CC_AmazonStoreObserver_Class.m_nUserPointer);
      }
    }
  }
  
  private class PurchaseUpdatesAsyncTask
    extends AsyncTask<PurchaseUpdatesResponse, Void, Boolean>
  {
    private PurchaseUpdatesAsyncTask() {}
    
    protected Boolean doInBackground(PurchaseUpdatesResponse... paramVarArgs)
    {
      PurchaseUpdatesResponse localPurchaseUpdatesResponse = paramVarArgs[0];
      String str = CC_AmazonStoreObserver_Class.this.getCurrentUser();
      if ((!localPurchaseUpdatesResponse.getUserData().getUserId().equals(str)) && ((!str.equals("l3HL7XppEMhrOGDnur9-ulvqomrSg6qyODKmah76lJU=")) || (!localPurchaseUpdatesResponse.getUserData().getUserId().equals(""))))
      {
        Consts.Logger("Reject PurchaseUpdatesAsyncTask for user " + localPurchaseUpdatesResponse.getUserData().getUserId() + " Excepted user " + str);
        CC_AmazonStoreObserver_Class.RestoreCallback(false, localPurchaseUpdatesResponse.getUserData().getUserId(), null, CC_AmazonStoreObserver_Class.m_nRestoreCallback, CC_AmazonStoreObserver_Class.m_nUserPointer);
        return Boolean.valueOf(false);
      }
      switch (CC_AmazonStoreObserver_Class.1.$SwitchMap$com$amazon$device$iap$model$PurchaseUpdatesResponse$RequestStatus[localPurchaseUpdatesResponse.getRequestStatus().ordinal()])
      {
      default: 
        return Boolean.valueOf(false);
      case 1: 
        Iterator localIterator1 = localPurchaseUpdatesResponse.getReceipts().iterator();
        if (localIterator1.hasNext())
        {
          Receipt localReceipt = (Receipt)localIterator1.next();
          switch (CC_AmazonStoreObserver_Class.1.$SwitchMap$com$amazon$device$iap$model$ProductType[localReceipt.getProductType().ordinal()])
          {
          }
          for (;;)
          {
            CC_AmazonStoreObserver_Class.this.mRestoreReceipts.add(localReceipt);
            CC_AmazonStoreObserver_Class.this.printReceipt(localReceipt);
            break;
            Consts.Logger("PurchaseUpdatesAsyncTask: consumable product for Restore");
          }
        }
        if (localPurchaseUpdatesResponse.hasMore())
        {
          Consts.Logger("Initiating Another Purchase Updates");
          PurchasingService.getPurchaseUpdates(false);
        }
        for (;;)
        {
          return Boolean.valueOf(true);
          Receipt[] arrayOfReceipt = new Receipt[CC_AmazonStoreObserver_Class.this.mRestoreReceipts.size()];
          int i = 0;
          Iterator localIterator2 = CC_AmazonStoreObserver_Class.this.mRestoreReceipts.iterator();
          while (localIterator2.hasNext())
          {
            arrayOfReceipt[i] = ((Receipt)localIterator2.next());
            i++;
          }
          CC_AmazonStoreObserver_Class.this.mRestoreReceipts.clear();
          CC_AmazonStoreObserver_Class.RestoreCallback(true, localPurchaseUpdatesResponse.getUserData().getUserId(), arrayOfReceipt, CC_AmazonStoreObserver_Class.m_nRestoreCallback, CC_AmazonStoreObserver_Class.m_nUserPointer);
        }
      }
      CC_AmazonStoreObserver_Class.RestoreCallback(false, localPurchaseUpdatesResponse.getUserData().getUserId(), null, CC_AmazonStoreObserver_Class.m_nRestoreCallback, CC_AmazonStoreObserver_Class.m_nUserPointer);
      return Boolean.valueOf(false);
    }
    
    protected void onPostExecute(Boolean paramBoolean)
    {
      super.onPostExecute(paramBoolean);
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.firemonkeys.cloudcellapi.CC_AmazonStoreObserver_Class
 * JD-Core Version:    0.7.0.1
 */