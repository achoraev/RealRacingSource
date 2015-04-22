package com.supersonicads.sdk.data;

public class AdUnitsReady
  extends SSAObj
{
  private static String FIRST_CAMPAIGN_CREDITS;
  private static String NUM_OF_AD_UNITS;
  private static String PRODUCT_TYPE = "productType";
  private static String TOTAL_NUMBER_CREDITS;
  private static String TYPE = "type";
  private String mFirstCampaignCredits;
  private String mNumOfAdUnits;
  private boolean mNumOfAdUnitsExist;
  private String mProductType;
  private String mTotalNumberCredits;
  private String mType;
  
  static
  {
    NUM_OF_AD_UNITS = "numOfAdUnits";
    FIRST_CAMPAIGN_CREDITS = "firstCampaignCredits";
    TOTAL_NUMBER_CREDITS = "totalNumberCredits";
  }
  
  public AdUnitsReady(String paramString)
  {
    super(paramString);
    if (containsKey(TYPE)) {
      setType(getString(TYPE));
    }
    if (containsKey(NUM_OF_AD_UNITS))
    {
      setNumOfAdUnits(getString(NUM_OF_AD_UNITS));
      setNumOfAdUnitsExist(true);
    }
    for (;;)
    {
      if (containsKey(FIRST_CAMPAIGN_CREDITS)) {
        setFirstCampaignCredits(getString(FIRST_CAMPAIGN_CREDITS));
      }
      if (containsKey(TOTAL_NUMBER_CREDITS)) {
        setTotalNumberCredits(getString(TOTAL_NUMBER_CREDITS));
      }
      if (containsKey(PRODUCT_TYPE)) {
        setProductType(getString(PRODUCT_TYPE));
      }
      return;
      setNumOfAdUnitsExist(false);
    }
  }
  
  private void setNumOfAdUnitsExist(boolean paramBoolean)
  {
    this.mNumOfAdUnitsExist = paramBoolean;
  }
  
  public String getFirstCampaignCredits()
  {
    return this.mFirstCampaignCredits;
  }
  
  public String getNumOfAdUnits()
  {
    return this.mNumOfAdUnits;
  }
  
  public String getProductType()
  {
    return this.mProductType;
  }
  
  public String getTotalNumberCredits()
  {
    return this.mTotalNumberCredits;
  }
  
  public String getType()
  {
    return this.mType;
  }
  
  public boolean isNumOfAdUnitsExist()
  {
    return this.mNumOfAdUnitsExist;
  }
  
  public void setFirstCampaignCredits(String paramString)
  {
    this.mFirstCampaignCredits = paramString;
  }
  
  public void setNumOfAdUnits(String paramString)
  {
    this.mNumOfAdUnits = paramString;
  }
  
  public void setProductType(String paramString)
  {
    this.mProductType = paramString;
  }
  
  public void setTotalNumberCredits(String paramString)
  {
    this.mTotalNumberCredits = paramString;
  }
  
  public void setType(String paramString)
  {
    this.mType = paramString;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.supersonicads.sdk.data.AdUnitsReady
 * JD-Core Version:    0.7.0.1
 */