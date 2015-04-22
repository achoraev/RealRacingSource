package com.amazon.device.iap.model;

public enum FulfillmentResult
{
  static
  {
    FulfillmentResult[] arrayOfFulfillmentResult = new FulfillmentResult[2];
    arrayOfFulfillmentResult[0] = FULFILLED;
    arrayOfFulfillmentResult[1] = UNAVAILABLE;
    $VALUES = arrayOfFulfillmentResult;
  }
  
  private FulfillmentResult() {}
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.amazon.device.iap.model.FulfillmentResult
 * JD-Core Version:    0.7.0.1
 */