package com.amazon.device.iap.internal.model;

import com.amazon.device.iap.model.Product;
import com.amazon.device.iap.model.ProductDataResponse;
import com.amazon.device.iap.model.ProductDataResponse.RequestStatus;
import com.amazon.device.iap.model.RequestId;
import java.util.Map;
import java.util.Set;

public class ProductDataResponseBuilder
{
  private Map<String, Product> productData;
  private RequestId requestId;
  private ProductDataResponse.RequestStatus requestStatus;
  private Set<String> unavailableSkus;
  
  public ProductDataResponse build()
  {
    return new ProductDataResponse(this);
  }
  
  public Map<String, Product> getProductData()
  {
    return this.productData;
  }
  
  public RequestId getRequestId()
  {
    return this.requestId;
  }
  
  public ProductDataResponse.RequestStatus getRequestStatus()
  {
    return this.requestStatus;
  }
  
  public Set<String> getUnavailableSkus()
  {
    return this.unavailableSkus;
  }
  
  public ProductDataResponseBuilder setProductData(Map<String, Product> paramMap)
  {
    this.productData = paramMap;
    return this;
  }
  
  public ProductDataResponseBuilder setRequestId(RequestId paramRequestId)
  {
    this.requestId = paramRequestId;
    return this;
  }
  
  public ProductDataResponseBuilder setRequestStatus(ProductDataResponse.RequestStatus paramRequestStatus)
  {
    this.requestStatus = paramRequestStatus;
    return this;
  }
  
  public ProductDataResponseBuilder setUnavailableSkus(Set<String> paramSet)
  {
    this.unavailableSkus = paramSet;
    return this;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.amazon.device.iap.internal.model.ProductDataResponseBuilder
 * JD-Core Version:    0.7.0.1
 */