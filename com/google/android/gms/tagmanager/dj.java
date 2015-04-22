package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.analytics.HitBuilders.ScreenViewBuilder;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d.a;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class dj
  extends dg
{
  private static final String ID = a.aO.toString();
  private static final String asd = b.bj.toString();
  private static final String ase = b.bs.toString();
  private static final String asf = b.cE.toString();
  private static final String asg = b.cz.toString();
  private static final String ash = b.cy.toString();
  private static final String asi = b.br.toString();
  private static final String asj = b.eN.toString();
  private static final String ask = b.eQ.toString();
  private static final String asl = b.eS.toString();
  private static final List<String> asm = Arrays.asList(new String[] { "detail", "checkout", "checkout_option", "click", "add", "remove", "purchase", "refund" });
  private static Map<String, String> asn;
  private static Map<String, String> aso;
  private final DataLayer aod;
  private final Set<String> asp;
  private final df asq;
  
  public dj(Context paramContext, DataLayer paramDataLayer)
  {
    this(paramContext, paramDataLayer, new df(paramContext));
  }
  
  dj(Context paramContext, DataLayer paramDataLayer, df paramdf)
  {
    super(ID, new String[0]);
    this.aod = paramDataLayer;
    this.asq = paramdf;
    this.asp = new HashSet();
    this.asp.add("");
    this.asp.add("0");
    this.asp.add("false");
  }
  
  private Promotion M(Map<String, String> paramMap)
  {
    Promotion localPromotion = new Promotion();
    String str1 = (String)paramMap.get("id");
    if (str1 != null) {
      localPromotion.setId(String.valueOf(str1));
    }
    String str2 = (String)paramMap.get("name");
    if (str2 != null) {
      localPromotion.setName(String.valueOf(str2));
    }
    String str3 = (String)paramMap.get("creative");
    if (str3 != null) {
      localPromotion.setCreative(String.valueOf(str3));
    }
    String str4 = (String)paramMap.get("position");
    if (str4 != null) {
      localPromotion.setPosition(String.valueOf(str4));
    }
    return localPromotion;
  }
  
  private Product N(Map<String, Object> paramMap)
  {
    Product localProduct = new Product();
    Object localObject1 = paramMap.get("id");
    if (localObject1 != null) {
      localProduct.setId(String.valueOf(localObject1));
    }
    Object localObject2 = paramMap.get("name");
    if (localObject2 != null) {
      localProduct.setName(String.valueOf(localObject2));
    }
    Object localObject3 = paramMap.get("brand");
    if (localObject3 != null) {
      localProduct.setBrand(String.valueOf(localObject3));
    }
    Object localObject4 = paramMap.get("category");
    if (localObject4 != null) {
      localProduct.setCategory(String.valueOf(localObject4));
    }
    Object localObject5 = paramMap.get("variant");
    if (localObject5 != null) {
      localProduct.setVariant(String.valueOf(localObject5));
    }
    Object localObject6 = paramMap.get("coupon");
    if (localObject6 != null) {
      localProduct.setCouponCode(String.valueOf(localObject6));
    }
    Object localObject7 = paramMap.get("position");
    if (localObject7 != null) {
      localProduct.setPosition(z(localObject7).intValue());
    }
    Object localObject8 = paramMap.get("price");
    if (localObject8 != null) {
      localProduct.setPrice(y(localObject8).doubleValue());
    }
    Object localObject9 = paramMap.get("quantity");
    if (localObject9 != null) {
      localProduct.setQuantity(z(localObject9).intValue());
    }
    return localProduct;
  }
  
  private Map<String, String> O(Map<String, d.a> paramMap)
  {
    d.a locala = (d.a)paramMap.get(ask);
    if (locala != null) {
      return c(locala);
    }
    if (asn == null)
    {
      HashMap localHashMap = new HashMap();
      localHashMap.put("transactionId", "&ti");
      localHashMap.put("transactionAffiliation", "&ta");
      localHashMap.put("transactionTax", "&tt");
      localHashMap.put("transactionShipping", "&ts");
      localHashMap.put("transactionTotal", "&tr");
      localHashMap.put("transactionCurrency", "&cu");
      asn = localHashMap;
    }
    return asn;
  }
  
  private Map<String, String> P(Map<String, d.a> paramMap)
  {
    d.a locala = (d.a)paramMap.get(asl);
    if (locala != null) {
      return c(locala);
    }
    if (aso == null)
    {
      HashMap localHashMap = new HashMap();
      localHashMap.put("name", "&in");
      localHashMap.put("sku", "&ic");
      localHashMap.put("category", "&iv");
      localHashMap.put("price", "&ip");
      localHashMap.put("quantity", "&iq");
      localHashMap.put("currency", "&cu");
      aso = localHashMap;
    }
    return aso;
  }
  
  private void a(Tracker paramTracker, Map<String, d.a> paramMap)
  {
    String str = dc("transactionId");
    if (str == null) {
      bh.T("Cannot find transactionId in data layer.");
    }
    for (;;)
    {
      return;
      LinkedList localLinkedList = new LinkedList();
      try
      {
        Map localMap1 = p((d.a)paramMap.get(asi));
        localMap1.put("&t", "transaction");
        Iterator localIterator1 = O(paramMap).entrySet().iterator();
        while (localIterator1.hasNext())
        {
          Map.Entry localEntry2 = (Map.Entry)localIterator1.next();
          b(localMap1, (String)localEntry2.getValue(), dc((String)localEntry2.getKey()));
        }
        localLinkedList.add(localMap1);
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        bh.b("Unable to send transaction", localIllegalArgumentException);
        return;
      }
      List localList = dd("transactionProducts");
      if (localList != null)
      {
        Iterator localIterator2 = localList.iterator();
        while (localIterator2.hasNext())
        {
          Map localMap2 = (Map)localIterator2.next();
          if (localMap2.get("name") == null)
          {
            bh.T("Unable to send transaction item hit due to missing 'name' field.");
            return;
          }
          Map localMap3 = p((d.a)paramMap.get(asi));
          localMap3.put("&t", "item");
          localMap3.put("&ti", str);
          Iterator localIterator4 = P(paramMap).entrySet().iterator();
          while (localIterator4.hasNext())
          {
            Map.Entry localEntry1 = (Map.Entry)localIterator4.next();
            b(localMap3, (String)localEntry1.getValue(), (String)localMap2.get(localEntry1.getKey()));
          }
          localLinkedList.add(localMap3);
        }
      }
      Iterator localIterator3 = localLinkedList.iterator();
      while (localIterator3.hasNext()) {
        paramTracker.send((Map)localIterator3.next());
      }
    }
  }
  
  private void b(Tracker paramTracker, Map<String, d.a> paramMap)
  {
    HitBuilders.ScreenViewBuilder localScreenViewBuilder = new HitBuilders.ScreenViewBuilder();
    Map localMap1 = p((d.a)paramMap.get(asi));
    localScreenViewBuilder.setAll(localMap1);
    Object localObject4;
    if (f(paramMap, asg))
    {
      localObject4 = this.aod.get("ecommerce");
      if (!(localObject4 instanceof Map)) {
        break label747;
      }
    }
    label676:
    label735:
    label741:
    label747:
    for (Map localMap7 = (Map)localObject4;; localMap7 = null)
    {
      Map localMap2 = localMap7;
      for (;;)
      {
        List localList;
        if (localMap2 != null)
        {
          String str1 = (String)localMap1.get("&cu");
          if (str1 == null) {
            str1 = (String)localMap2.get("currencyCode");
          }
          if (str1 != null) {
            localScreenViewBuilder.set("&cu", str1);
          }
          Object localObject2 = localMap2.get("impressions");
          if ((localObject2 instanceof List))
          {
            Iterator localIterator4 = ((List)localObject2).iterator();
            for (;;)
            {
              if (localIterator4.hasNext())
              {
                Map localMap6 = (Map)localIterator4.next();
                try
                {
                  localScreenViewBuilder.addImpression(N(localMap6), (String)localMap6.get("list"));
                }
                catch (RuntimeException localRuntimeException4)
                {
                  bh.T("Failed to extract a product from DataLayer. " + localRuntimeException4.getMessage());
                }
                continue;
                Object localObject1 = di.o((d.a)paramMap.get(ash));
                if (!(localObject1 instanceof Map)) {
                  break label741;
                }
                localMap2 = (Map)localObject1;
                break;
              }
            }
          }
          if (localMap2.containsKey("promoClick")) {
            localList = (List)((Map)localMap2.get("promoClick")).get("promotions");
          }
        }
        for (;;)
        {
          if (localList != null)
          {
            Iterator localIterator3 = localList.iterator();
            for (;;)
            {
              if (localIterator3.hasNext())
              {
                Map localMap5 = (Map)localIterator3.next();
                try
                {
                  localScreenViewBuilder.addPromotion(M(localMap5));
                }
                catch (RuntimeException localRuntimeException3)
                {
                  bh.T("Failed to extract a promotion from DataLayer. " + localRuntimeException3.getMessage());
                }
                continue;
                if (!localMap2.containsKey("promoView")) {
                  break label735;
                }
                localList = (List)((Map)localMap2.get("promoView")).get("promotions");
                break;
              }
            }
            if (localMap2.containsKey("promoClick")) {
              localScreenViewBuilder.set("&promoa", "click");
            }
          }
          String str2;
          Map localMap3;
          for (int i = 0;; i = 1)
          {
            if (i == 0) {
              break label676;
            }
            Iterator localIterator1 = asm.iterator();
            do
            {
              if (!localIterator1.hasNext()) {
                break;
              }
              str2 = (String)localIterator1.next();
            } while (!localMap2.containsKey(str2));
            localMap3 = (Map)localMap2.get(str2);
            Iterator localIterator2 = ((List)localMap3.get("products")).iterator();
            while (localIterator2.hasNext())
            {
              Map localMap4 = (Map)localIterator2.next();
              try
              {
                localScreenViewBuilder.addProduct(N(localMap4));
              }
              catch (RuntimeException localRuntimeException2)
              {
                bh.T("Failed to extract a product from DataLayer. " + localRuntimeException2.getMessage());
              }
            }
            localScreenViewBuilder.set("&promoa", "view");
          }
          for (;;)
          {
            try
            {
              if (!localMap3.containsKey("actionField")) {
                continue;
              }
              localObject3 = c(str2, (Map)localMap3.get("actionField"));
              localScreenViewBuilder.setProductAction((ProductAction)localObject3);
            }
            catch (RuntimeException localRuntimeException1)
            {
              Object localObject3;
              ProductAction localProductAction;
              bh.T("Failed to extract a product action from DataLayer. " + localRuntimeException1.getMessage());
              continue;
            }
            paramTracker.send(localScreenViewBuilder.build());
            return;
            localProductAction = new ProductAction(str2);
            localObject3 = localProductAction;
          }
          localList = null;
        }
        localMap2 = null;
      }
    }
  }
  
  private void b(Map<String, String> paramMap, String paramString1, String paramString2)
  {
    if (paramString2 != null) {
      paramMap.put(paramString1, paramString2);
    }
  }
  
  private ProductAction c(String paramString, Map<String, Object> paramMap)
  {
    ProductAction localProductAction = new ProductAction(paramString);
    Object localObject1 = paramMap.get("id");
    if (localObject1 != null) {
      localProductAction.setTransactionId(String.valueOf(localObject1));
    }
    Object localObject2 = paramMap.get("affiliation");
    if (localObject2 != null) {
      localProductAction.setTransactionAffiliation(String.valueOf(localObject2));
    }
    Object localObject3 = paramMap.get("coupon");
    if (localObject3 != null) {
      localProductAction.setTransactionCouponCode(String.valueOf(localObject3));
    }
    Object localObject4 = paramMap.get("list");
    if (localObject4 != null) {
      localProductAction.setProductActionList(String.valueOf(localObject4));
    }
    Object localObject5 = paramMap.get("option");
    if (localObject5 != null) {
      localProductAction.setCheckoutOptions(String.valueOf(localObject5));
    }
    Object localObject6 = paramMap.get("revenue");
    if (localObject6 != null) {
      localProductAction.setTransactionRevenue(y(localObject6).doubleValue());
    }
    Object localObject7 = paramMap.get("tax");
    if (localObject7 != null) {
      localProductAction.setTransactionTax(y(localObject7).doubleValue());
    }
    Object localObject8 = paramMap.get("shipping");
    if (localObject8 != null) {
      localProductAction.setTransactionShipping(y(localObject8).doubleValue());
    }
    Object localObject9 = paramMap.get("step");
    if (localObject9 != null) {
      localProductAction.setCheckoutStep(z(localObject9).intValue());
    }
    return localProductAction;
  }
  
  private Map<String, String> c(d.a parama)
  {
    Object localObject = di.o(parama);
    if (!(localObject instanceof Map)) {
      return null;
    }
    Map localMap = (Map)localObject;
    LinkedHashMap localLinkedHashMap = new LinkedHashMap();
    Iterator localIterator = localMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      localLinkedHashMap.put(localEntry.getKey().toString(), localEntry.getValue().toString());
    }
    return localLinkedHashMap;
  }
  
  private String dc(String paramString)
  {
    Object localObject = this.aod.get(paramString);
    if (localObject == null) {
      return null;
    }
    return localObject.toString();
  }
  
  private List<Map<String, String>> dd(String paramString)
  {
    Object localObject = this.aod.get(paramString);
    if (localObject == null) {
      return null;
    }
    if (!(localObject instanceof List)) {
      throw new IllegalArgumentException("transactionProducts should be of type List.");
    }
    Iterator localIterator = ((List)localObject).iterator();
    while (localIterator.hasNext()) {
      if (!(localIterator.next() instanceof Map)) {
        throw new IllegalArgumentException("Each element of transactionProducts should be of type Map.");
      }
    }
    return (List)localObject;
  }
  
  private boolean f(Map<String, d.a> paramMap, String paramString)
  {
    d.a locala = (d.a)paramMap.get(paramString);
    if (locala == null) {
      return false;
    }
    return di.n(locala).booleanValue();
  }
  
  private Map<String, String> p(d.a parama)
  {
    if (parama == null) {
      return new HashMap();
    }
    Map localMap = c(parama);
    if (localMap == null) {
      return new HashMap();
    }
    String str = (String)localMap.get("&aip");
    if ((str != null) && (this.asp.contains(str.toLowerCase()))) {
      localMap.remove("&aip");
    }
    return localMap;
  }
  
  private Double y(Object paramObject)
  {
    if ((paramObject instanceof String)) {
      try
      {
        Double localDouble = Double.valueOf((String)paramObject);
        return localDouble;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw new RuntimeException("Cannot convert the object to Double: " + localNumberFormatException.getMessage());
      }
    }
    if ((paramObject instanceof Integer)) {
      return Double.valueOf(((Integer)paramObject).doubleValue());
    }
    if ((paramObject instanceof Double)) {
      return (Double)paramObject;
    }
    throw new RuntimeException("Cannot convert the object to Double: " + paramObject.toString());
  }
  
  private Integer z(Object paramObject)
  {
    if ((paramObject instanceof String)) {
      try
      {
        Integer localInteger = Integer.valueOf((String)paramObject);
        return localInteger;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw new RuntimeException("Cannot convert the object to Integer: " + localNumberFormatException.getMessage());
      }
    }
    if ((paramObject instanceof Double)) {
      return Integer.valueOf(((Double)paramObject).intValue());
    }
    if ((paramObject instanceof Integer)) {
      return (Integer)paramObject;
    }
    throw new RuntimeException("Cannot convert the object to Integer: " + paramObject.toString());
  }
  
  public void E(Map<String, d.a> paramMap)
  {
    Tracker localTracker = this.asq.cU("_GTM_DEFAULT_TRACKER_");
    if (f(paramMap, asf))
    {
      b(localTracker, paramMap);
      return;
    }
    if (f(paramMap, ase))
    {
      localTracker.send(p((d.a)paramMap.get(asi)));
      return;
    }
    if (f(paramMap, asj))
    {
      a(localTracker, paramMap);
      return;
    }
    bh.W("Ignoring unknown tag.");
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.dj
 * JD-Core Version:    0.7.0.1
 */