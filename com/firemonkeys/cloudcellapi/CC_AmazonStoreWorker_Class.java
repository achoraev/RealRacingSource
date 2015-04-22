package com.firemonkeys.cloudcellapi;

import android.app.Activity;
import com.amazon.device.iap.PurchasingService;

public class CC_AmazonStoreWorker_Class
{
  private CC_AmazonStoreObserver_Class mObserver;
  
  public void CompleteTransaction(String paramString, boolean paramBoolean)
  {
    this.mObserver.CompleteTransaction(paramString, Boolean.valueOf(paramBoolean));
  }
  
  void Constructor(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6)
  {
    this.mObserver = new CC_AmazonStoreObserver_Class(paramLong1, paramLong2, paramLong3, paramLong4, paramLong5, paramLong6);
    PurchasingService.registerListener(CC_Activity.GetActivity().getApplicationContext(), this.mObserver);
    PurchasingService.getUserData();
    Consts.Logger("Constructor");
  }
  
  public void Destructor()
  {
    PurchasingService.registerListener(CC_Activity.GetActivity().getApplicationContext(), null);
    this.mObserver = null;
  }
  
  void Purchase(String paramString)
  {
    this.mObserver.Purchase(paramString);
  }
  
  public void RefreshStorePurchases()
  {
    this.mObserver.RefreshStorePurchases();
  }
  
  public void RestorePurchase()
  {
    this.mObserver.RestorePurchase();
  }
  
  public void getProductDetails(String[] paramArrayOfString)
  {
    this.mObserver.getProductDetails(paramArrayOfString);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.firemonkeys.cloudcellapi.CC_AmazonStoreWorker_Class
 * JD-Core Version:    0.7.0.1
 */