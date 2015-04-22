package com.amazon.device.iap.internal.model;

import com.amazon.device.iap.model.UserData;

public class UserDataBuilder
{
  private String marketplace;
  private String userId;
  
  public UserData build()
  {
    return new UserData(this);
  }
  
  public String getMarketplace()
  {
    return this.marketplace;
  }
  
  public String getUserId()
  {
    return this.userId;
  }
  
  public UserDataBuilder setMarketplace(String paramString)
  {
    this.marketplace = paramString;
    return this;
  }
  
  public UserDataBuilder setUserId(String paramString)
  {
    this.userId = paramString;
    return this;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.amazon.device.iap.internal.model.UserDataBuilder
 * JD-Core Version:    0.7.0.1
 */