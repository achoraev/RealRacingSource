package com.ultra.sdk;

import android.content.Context;
import android.text.TextUtils;
import com.ultra.sdk.data.CPVOrder;
import com.ultra.sdk.data.OfferWallOrder;
import com.ultra.sdk.utils.DataManager;
import com.ultra.sdk.utils.ProvidersList;
import com.ultra.sdk.utils.UltraLogger;
import com.ultra.sdk.volley.RequestQueue;
import com.ultra.sdk.volley.Response.ErrorListener;
import com.ultra.sdk.volley.Response.Listener;
import com.ultra.sdk.volley.VolleyError;
import com.ultra.sdk.volley.toolbox.JsonArrayRequest;
import com.ultra.sdk.volley.toolbox.JsonObjectRequest;
import com.ultra.sdk.volley.toolbox.Volley;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UltraManager
{
  public static final String TAG = UltraManager.class.getSimpleName();
  private static boolean isVideoAvaialble = false;
  public static Context mContext;
  public static UltraCPVListener mUltraCPVListener;
  public static UltraOfferWallListener mUltraOfferWallListener;
  
  private UltraManager(Context paramContext) {}
  
  public static void initUltra(Context paramContext, String paramString1, String paramString2)
  {
    RequestQueue localRequestQueue = Volley.newRequestQueue(paramContext);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("http://www.ultraadserver.com/api/rest/v1.1/").append("uniqueusers?").append("applicationKey").append("=").append("%1$s").append("&").append("applicationUserId").append("=").append("%2$s");
    String str = String.format(localStringBuilder.toString(), new Object[] { paramString1, paramString2 });
    UltraLogger.i(TAG, "Request: uniqueusers? : " + str);
    localRequestQueue.add(new JsonObjectRequest(0, str, null, new Response.Listener()new Response.ErrorListener
    {
      public void onResponse(JSONObject paramAnonymousJSONObject)
      {
        UltraLogger.i(UltraManager.TAG, "Response: uniqueusers? : " + paramAnonymousJSONObject);
        try
        {
          paramAnonymousJSONObject.get("status");
          return;
        }
        catch (JSONException localJSONException)
        {
          localJSONException.printStackTrace();
        }
      }
    }, new Response.ErrorListener()
    {
      public void onErrorResponse(VolleyError paramAnonymousVolleyError)
      {
        UltraLogger.i(UltraManager.TAG, "Response: uniqueusers? : " + paramAnonymousVolleyError.getMessage());
      }
    }));
  }
  
  public static void loadUltraCPVOrder(Context paramContext, String paramString1, String paramString2)
  {
    RequestQueue localRequestQueue = Volley.newRequestQueue(paramContext);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("http://www.ultraadserver.com/api/rest/v1.1/").append("ultra/cpv?").append("applicationKey").append("=").append("%1$s").append("&").append("applicationUserId").append("=").append("%2$s").append("&").append("platform").append("=").append("android");
    String str = String.format(localStringBuilder.toString(), new Object[] { paramString1, paramString2 });
    UltraLogger.i(TAG, "Request: ultra/cpv? : " + str);
    localRequestQueue.add(new JsonArrayRequest(str, new Response.Listener()new Response.ErrorListener
    {
      public void onResponse(JSONArray paramAnonymousJSONArray)
      {
        UltraLogger.i(UltraManager.TAG, "Response: ultra/cpv? : " + paramAnonymousJSONArray);
        DataManager.getInstance(UltraManager.this).setCPVOrders(paramAnonymousJSONArray.toString());
      }
    }, new Response.ErrorListener()
    {
      public void onErrorResponse(VolleyError paramAnonymousVolleyError)
      {
        UltraLogger.i(UltraManager.TAG, "Response: ultra/cpv? : " + paramAnonymousVolleyError.getMessage());
      }
    }));
  }
  
  public static void loadUltraOfferWallOrder(Context paramContext, String paramString1, String paramString2, UltraOfferWallListener paramUltraOfferWallListener)
  {
    setUltraOfferWallListener(paramUltraOfferWallListener);
    RequestQueue localRequestQueue = Volley.newRequestQueue(paramContext);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("http://www.ultraadserver.com/api/rest/v1.1/").append("ultra/wall?").append("applicationKey").append("=").append("%1$s").append("&").append("applicationUserId").append("=").append("%2$s").append("&").append("platform").append("=").append("android");
    String str = String.format(localStringBuilder.toString(), new Object[] { paramString1, paramString2 });
    UltraLogger.i(TAG, "Request: ultra/wall? : " + str);
    localRequestQueue.add(new JsonArrayRequest(str, new Response.Listener()new Response.ErrorListener
    {
      public void onResponse(JSONArray paramAnonymousJSONArray)
      {
        UltraLogger.i(UltraManager.TAG, "Response: ultra/wall? : " + paramAnonymousJSONArray);
        DataManager localDataManager = DataManager.getInstance(UltraManager.this);
        localDataManager.setOfferWallOrders(paramAnonymousJSONArray.toString());
        if (UltraManager.mUltraOfferWallListener != null)
        {
          List localList = localDataManager.getOfferWallProvidersList();
          UltraManager.mUltraOfferWallListener.getOfferWallProviders(localList);
        }
      }
    }, new Response.ErrorListener()
    {
      public void onErrorResponse(VolleyError paramAnonymousVolleyError)
      {
        UltraLogger.i(UltraManager.TAG, "Response: ultra/wall? : " + paramAnonymousVolleyError.getMessage());
        if (UltraManager.mUltraOfferWallListener != null)
        {
          DataManager localDataManager = DataManager.getInstance(UltraManager.this);
          UltraManager.mUltraOfferWallListener.getOfferWallProviders(localDataManager.getOfferWallProvidersList());
        }
      }
    }));
  }
  
  private static void reportImpression(Context paramContext, String paramString, Boolean... paramVarArgs)
  {
    if (TextUtils.isEmpty(paramString)) {
      return;
    }
    RequestQueue localRequestQueue = Volley.newRequestQueue(paramContext);
    StringBuilder localStringBuilder = new StringBuilder(paramString);
    if (paramVarArgs.length > 0) {
      localStringBuilder.append("&").append("impression").append("=").append(paramVarArgs[0]);
    }
    UltraLogger.i(TAG, "Request: " + localStringBuilder.toString());
    localRequestQueue.add(new JsonArrayRequest(localStringBuilder.toString(), new Response.Listener()new Response.ErrorListener
    {
      public void onResponse(JSONArray paramAnonymousJSONArray)
      {
        UltraLogger.i(UltraManager.TAG, "Response: " + paramAnonymousJSONArray);
      }
    }, new Response.ErrorListener()
    {
      public void onErrorResponse(VolleyError paramAnonymousVolleyError)
      {
        UltraLogger.i(UltraManager.TAG, "Response: " + paramAnonymousVolleyError.getMessage());
      }
    }));
  }
  
  public static void reportProviderDidStartUltraCPV(boolean paramBoolean)
  {
    isVideoAvaialble = paramBoolean;
  }
  
  public static void reportUltraOfferWallImpression(Context paramContext, String paramString)
  {
    Iterator localIterator;
    if (!TextUtils.isEmpty(paramString)) {
      localIterator = DataManager.getInstance(paramContext).getOfferWallOrdersList().iterator();
    }
    OfferWallOrder localOfferWallOrder;
    do
    {
      if (!localIterator.hasNext()) {
        return;
      }
      localOfferWallOrder = (OfferWallOrder)localIterator.next();
    } while (!localOfferWallOrder.getProvider().equalsIgnoreCase(paramString));
    reportImpression(paramContext, localOfferWallOrder.getImpressionUrl(), new Boolean[0]);
  }
  
  private static void setUltraCPVListener(UltraCPVListener paramUltraCPVListener)
  {
    mUltraCPVListener = paramUltraCPVListener;
  }
  
  private static void setUltraOfferWallListener(UltraOfferWallListener paramUltraOfferWallListener)
  {
    mUltraOfferWallListener = paramUltraOfferWallListener;
  }
  
  public static void startUltraCPV(Context paramContext, UltraCPVListener paramUltraCPVListener)
  {
    setUltraCPVListener(paramUltraCPVListener);
    ArrayList localArrayList = new ArrayList();
    localArrayList.addAll(DataManager.getInstance(paramContext).getCPVOrdersList());
    boolean bool = false;
    Iterator localIterator2;
    if (mUltraCPVListener != null)
    {
      if (localArrayList.isEmpty()) {
        break label143;
      }
      localIterator2 = localArrayList.iterator();
      if (localIterator2.hasNext()) {
        break label76;
      }
      label62:
      if (!bool) {
        mUltraCPVListener.ultraNoMoreCPVOffers();
      }
    }
    label143:
    label201:
    for (;;)
    {
      return;
      label76:
      CPVOrder localCPVOrder = (CPVOrder)localIterator2.next();
      String str1 = localCPVOrder.getProvider();
      String str2 = localCPVOrder.getRequestUrl();
      startUltraCPVByProvider(str1);
      bool = isVideoAvaialble;
      Boolean[] arrayOfBoolean = new Boolean[1];
      arrayOfBoolean[0] = Boolean.valueOf(bool);
      reportImpression(paramContext, str2, arrayOfBoolean);
      if (!bool) {
        break;
      }
      break label62;
      Iterator localIterator1 = ProvidersList.getProvidersStaticList().iterator();
      if (!localIterator1.hasNext()) {}
      for (;;)
      {
        if (bool) {
          break label201;
        }
        mUltraCPVListener.ultraNoMoreCPVOffers();
        return;
        startUltraCPVByProvider((String)localIterator1.next());
        bool = isVideoAvaialble;
        if (!bool) {
          break;
        }
      }
    }
  }
  
  private static void startUltraCPVByProvider(String paramString)
  {
    if (paramString != null)
    {
      if (!"SupersonicAds".equals(paramString)) {
        break label49;
      }
      mUltraCPVListener.startUltraCPVSSA();
    }
    for (;;)
    {
      if (paramString != null) {
        UltraLogger.i(TAG, "Start Ultra CPV " + paramString);
      }
      return;
      label49:
      if ("Aarki".equals(paramString)) {
        mUltraCPVListener.startUltraCPVAarki();
      } else if ("SponsorPay".equals(paramString)) {
        mUltraCPVListener.startUltraCPVSponsorPay();
      } else if ("Tapjoy".equals(paramString)) {
        mUltraCPVListener.startUltraCPVTapJoy();
      } else if ("TrialPay".equals(paramString)) {
        mUltraCPVListener.startUltraCPVTrialPay();
      } else if ("AdColony".equals(paramString)) {
        mUltraCPVListener.startUltraCPVAdColony();
      } else if ("Flurry".equals(paramString)) {
        mUltraCPVListener.startUltraCPVFlurry();
      } else if ("Vungle".equals(paramString)) {
        mUltraCPVListener.startUltraCPVVungle();
      }
    }
  }
  
  public static abstract interface UltraCPVListener
  {
    public abstract void startUltraCPVAarki();
    
    public abstract void startUltraCPVAdColony();
    
    public abstract void startUltraCPVFlurry();
    
    public abstract void startUltraCPVSSA();
    
    public abstract void startUltraCPVSponsorPay();
    
    public abstract void startUltraCPVTapJoy();
    
    public abstract void startUltraCPVTrialPay();
    
    public abstract void startUltraCPVVungle();
    
    public abstract void ultraNoMoreCPVOffers();
  }
  
  public static abstract interface UltraOfferWallListener
  {
    public abstract void getOfferWallProviders(List<String> paramList);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ultra.sdk.UltraManager
 * JD-Core Version:    0.7.0.1
 */