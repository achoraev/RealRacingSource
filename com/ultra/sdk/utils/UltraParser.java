package com.ultra.sdk.utils;

import com.ultra.sdk.data.CPVOrder;
import com.ultra.sdk.data.OfferWallOrder;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UltraParser
{
  private static final String IMPRESSION_URL = "impressionUrl";
  private static final String PROVIDER = "provider";
  private static final String REQUEST_URL = "requestUrl";
  
  public static List<CPVOrder> parseCPVOrder(JSONArray paramJSONArray)
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    for (;;)
    {
      if (i >= paramJSONArray.length()) {
        return localArrayList;
      }
      try
      {
        JSONObject localJSONObject = paramJSONArray.getJSONObject(i);
        String str1 = localJSONObject.getString("provider");
        String str2 = localJSONObject.getString("requestUrl");
        CPVOrder localCPVOrder = new CPVOrder();
        localCPVOrder.setProvider(str1);
        localCPVOrder.setRequestUrl(str2);
        localArrayList.add(localCPVOrder);
        label77:
        i++;
      }
      catch (JSONException localJSONException)
      {
        break label77;
      }
    }
  }
  
  public static List<OfferWallOrder> parseOfferWallOrder(JSONArray paramJSONArray)
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    for (;;)
    {
      if (i >= paramJSONArray.length()) {
        return localArrayList;
      }
      try
      {
        JSONObject localJSONObject = paramJSONArray.getJSONObject(i);
        String str1 = localJSONObject.getString("provider");
        String str2 = localJSONObject.getString("impressionUrl");
        OfferWallOrder localOfferWallOrder = new OfferWallOrder();
        localOfferWallOrder.setProvider(str1);
        localOfferWallOrder.setImpressionUrl(str2);
        localArrayList.add(localOfferWallOrder);
        label77:
        i++;
      }
      catch (JSONException localJSONException)
      {
        break label77;
      }
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ultra.sdk.utils.UltraParser
 * JD-Core Version:    0.7.0.1
 */