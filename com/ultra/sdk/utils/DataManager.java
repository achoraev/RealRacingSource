package com.ultra.sdk.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.ultra.sdk.data.CPVOrder;
import com.ultra.sdk.data.OfferWallOrder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

public class DataManager
{
  private static final String CPV_ORDERS_SHARED_SHARED_PREFERENCES = "cpv_orders_shared_shared_preferences";
  private static final String OFFER_WALL_ORDERS_SHARED_SHARED_PREFERENCES = "offer_wall_orders_shared_shared_preferences";
  private static final String ULTRA_SHARED_PREFERENCES = "ultra_shared_preferences";
  private static DataManager sInstance;
  private Context mContext;
  private SharedPreferences mSharedPreferences;
  
  private DataManager(Context paramContext)
  {
    this.mContext = paramContext;
    this.mSharedPreferences = this.mContext.getSharedPreferences("ultra_shared_preferences", 0);
  }
  
  private String getCPVOrders()
  {
    return this.mSharedPreferences.getString("cpv_orders_shared_shared_preferences", "EMPTY_CPV_ORDERS");
  }
  
  public static DataManager getInstance(Context paramContext)
  {
    if (sInstance == null) {}
    try
    {
      if (sInstance == null) {
        sInstance = new DataManager(paramContext);
      }
      return sInstance;
    }
    finally {}
  }
  
  private String getOfferWallOrders()
  {
    return this.mSharedPreferences.getString("offer_wall_orders_shared_shared_preferences", "EMPTY_OFFER_WALL_ORDERS");
  }
  
  public List<CPVOrder> getCPVOrdersList()
  {
    ArrayList localArrayList = new ArrayList();
    try
    {
      localArrayList.addAll(UltraParser.parseCPVOrder(new JSONArray(getCPVOrders())));
      return localArrayList;
    }
    catch (JSONException localJSONException) {}
    return localArrayList;
  }
  
  public List<OfferWallOrder> getOfferWallOrdersList()
  {
    ArrayList localArrayList = new ArrayList();
    try
    {
      localArrayList.addAll(UltraParser.parseOfferWallOrder(new JSONArray(getOfferWallOrders())));
      return localArrayList;
    }
    catch (JSONException localJSONException) {}
    return localArrayList;
  }
  
  public List<String> getOfferWallProvidersList()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = getOfferWallOrdersList().iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return localArrayList;
      }
      localArrayList.add(((OfferWallOrder)localIterator.next()).getProvider());
    }
  }
  
  public void setCPVOrders(String paramString)
  {
    SharedPreferences.Editor localEditor = this.mSharedPreferences.edit();
    localEditor.putString("cpv_orders_shared_shared_preferences", paramString);
    localEditor.commit();
  }
  
  public void setOfferWallOrders(String paramString)
  {
    SharedPreferences.Editor localEditor = this.mSharedPreferences.edit();
    localEditor.putString("offer_wall_orders_shared_shared_preferences", paramString);
    localEditor.commit();
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ultra.sdk.utils.DataManager
 * JD-Core Version:    0.7.0.1
 */