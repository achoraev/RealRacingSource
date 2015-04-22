package com.amazon.device.iap.model;

public enum ProductType
{
  static
  {
    ProductType[] arrayOfProductType = new ProductType[3];
    arrayOfProductType[0] = CONSUMABLE;
    arrayOfProductType[1] = ENTITLED;
    arrayOfProductType[2] = SUBSCRIPTION;
    $VALUES = arrayOfProductType;
  }
  
  private ProductType() {}
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.amazon.device.iap.model.ProductType
 * JD-Core Version:    0.7.0.1
 */