package com.amazon.device.iap.internal.b.c;

import android.os.RemoteException;
import com.amazon.android.framework.exception.KiwiException;
import com.amazon.device.iap.internal.b.h;
import com.amazon.device.iap.internal.model.ProductBuilder;
import com.amazon.device.iap.internal.model.ProductDataResponseBuilder;
import com.amazon.device.iap.internal.util.d;
import com.amazon.device.iap.model.Product;
import com.amazon.device.iap.model.ProductDataResponse;
import com.amazon.device.iap.model.ProductDataResponse.RequestStatus;
import com.amazon.device.iap.model.ProductType;
import com.amazon.venezia.command.SuccessResult;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public final class a
  extends c
{
  private static final String b = a.class.getSimpleName();
  
  public a(com.amazon.device.iap.internal.b.e parame, Set<String> paramSet)
  {
    super(parame, "2.0", paramSet);
  }
  
  private Product a(String paramString, Map paramMap)
    throws IllegalArgumentException
  {
    String str1 = (String)paramMap.get(paramString);
    try
    {
      JSONObject localJSONObject1 = new JSONObject(str1);
      ProductType localProductType = ProductType.valueOf(localJSONObject1.getString("itemType").toUpperCase());
      String str2 = localJSONObject1.getString("description");
      String str3 = localJSONObject1.optString("price", null);
      if (d.a(str3))
      {
        JSONObject localJSONObject2 = localJSONObject1.optJSONObject("priceJson");
        if (localJSONObject2 != null)
        {
          Currency localCurrency = Currency.getInstance(localJSONObject2.getString("currency"));
          BigDecimal localBigDecimal = new BigDecimal(localJSONObject2.getString("value"));
          str3 = localCurrency.getSymbol() + localBigDecimal;
        }
      }
      String str4 = localJSONObject1.getString("title");
      String str5 = localJSONObject1.getString("iconUrl");
      Product localProduct = new ProductBuilder().setSku(paramString).setProductType(localProductType).setDescription(str2).setPrice(str3).setSmallIconUrl(str5).setTitle(str4).build();
      return localProduct;
    }
    catch (JSONException localJSONException)
    {
      throw new IllegalArgumentException("error in parsing json string" + str1);
    }
  }
  
  protected boolean a(SuccessResult paramSuccessResult)
    throws RemoteException, KiwiException
  {
    Map localMap = paramSuccessResult.getData();
    com.amazon.device.iap.internal.util.e.a(b, "data: " + localMap);
    LinkedHashSet localLinkedHashSet = new LinkedHashSet();
    HashMap localHashMap = new HashMap();
    Iterator localIterator = this.a.iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if (!localMap.containsKey(str)) {
        localLinkedHashSet.add(str);
      } else {
        try
        {
          localHashMap.put(str, a(str, localMap));
        }
        catch (IllegalArgumentException localIllegalArgumentException)
        {
          localLinkedHashSet.add(str);
          ((String)localMap.get(str));
          com.amazon.device.iap.internal.util.e.b(b, "Error parsing JSON for SKU " + str + ": " + localIllegalArgumentException.getMessage());
        }
      }
    }
    com.amazon.device.iap.internal.b.e locale = b();
    ProductDataResponse localProductDataResponse = new ProductDataResponseBuilder().setRequestId(locale.c()).setRequestStatus(ProductDataResponse.RequestStatus.SUCCESSFUL).setUnavailableSkus(localLinkedHashSet).setProductData(localHashMap).build();
    locale.d().a(localProductDataResponse);
    return true;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.amazon.device.iap.internal.b.c.a
 * JD-Core Version:    0.7.0.1
 */