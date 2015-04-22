package com.firemonkeys.cloudcellapi.util;

import org.json.JSONException;
import org.json.JSONObject;

public class SkuDetails
{
  public static final String ITEM_TYPE_INAPP = "inapp";
  String mDescription;
  String mItemType;
  String mJson;
  String mPrice;
  String mPriceAmountMicros;
  String mPriceCurrencyCode;
  String mSku;
  String mTitle;
  String mType;
  
  public SkuDetails(String paramString)
    throws JSONException
  {
    this("inapp", paramString);
  }
  
  public SkuDetails(String paramString1, String paramString2)
    throws JSONException
  {
    this.mItemType = paramString1;
    this.mJson = paramString2;
    JSONObject localJSONObject = new JSONObject(this.mJson);
    this.mSku = localJSONObject.optString("productId");
    this.mType = localJSONObject.optString("type");
    this.mPrice = localJSONObject.optString("price");
    this.mPriceAmountMicros = localJSONObject.optString("price_amount_micros");
    this.mPriceCurrencyCode = localJSONObject.optString("price_currency_code");
    this.mTitle = localJSONObject.optString("title");
    this.mDescription = localJSONObject.optString("description");
  }
  
  public String getDescription()
  {
    return this.mDescription;
  }
  
  public String getPrice()
  {
    return this.mPrice;
  }
  
  public String getPriceAmountMicros()
  {
    return this.mPriceAmountMicros;
  }
  
  public String getPriceCurrencyCode()
  {
    return this.mPriceCurrencyCode;
  }
  
  public String getSku()
  {
    return this.mSku;
  }
  
  public String getTitle()
  {
    return this.mTitle;
  }
  
  public String getType()
  {
    return this.mType;
  }
  
  public String toString()
  {
    return "SkuDetails:" + this.mJson;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.firemonkeys.cloudcellapi.util.SkuDetails
 * JD-Core Version:    0.7.0.1
 */