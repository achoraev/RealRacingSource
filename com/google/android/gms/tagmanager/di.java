package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.d.a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class di
{
  private static final Object arU = null;
  private static Long arV = new Long(0L);
  private static Double arW = new Double(0.0D);
  private static dh arX = dh.z(0L);
  private static String arY = new String("");
  private static Boolean arZ = new Boolean(false);
  private static List<Object> asa = new ArrayList(0);
  private static Map<Object, Object> asb = new HashMap();
  private static d.a asc = u(arY);
  
  public static d.a cX(String paramString)
  {
    d.a locala = new d.a();
    locala.type = 5;
    locala.gA = paramString;
    return locala;
  }
  
  private static dh cY(String paramString)
  {
    try
    {
      dh localdh = dh.cW(paramString);
      return localdh;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      bh.T("Failed to convert '" + paramString + "' to a number.");
    }
    return arX;
  }
  
  private static Long cZ(String paramString)
  {
    dh localdh = cY(paramString);
    if (localdh == arX) {
      return arV;
    }
    return Long.valueOf(localdh.longValue());
  }
  
  private static Double da(String paramString)
  {
    dh localdh = cY(paramString);
    if (localdh == arX) {
      return arW;
    }
    return Double.valueOf(localdh.doubleValue());
  }
  
  private static Boolean db(String paramString)
  {
    if ("true".equalsIgnoreCase(paramString)) {
      return Boolean.TRUE;
    }
    if ("false".equalsIgnoreCase(paramString)) {
      return Boolean.FALSE;
    }
    return arZ;
  }
  
  private static double getDouble(Object paramObject)
  {
    if ((paramObject instanceof Number)) {
      return ((Number)paramObject).doubleValue();
    }
    bh.T("getDouble received non-Number");
    return 0.0D;
  }
  
  public static String j(d.a parama)
  {
    return p(o(parama));
  }
  
  public static dh k(d.a parama)
  {
    return q(o(parama));
  }
  
  public static Long l(d.a parama)
  {
    return r(o(parama));
  }
  
  public static Double m(d.a parama)
  {
    return s(o(parama));
  }
  
  public static Boolean n(d.a parama)
  {
    return t(o(parama));
  }
  
  public static Object o(d.a parama)
  {
    int i = 0;
    if (parama == null) {
      return arU;
    }
    switch (parama.type)
    {
    default: 
      bh.T("Failed to convert a value of type: " + parama.type);
      return arU;
    case 1: 
      return parama.gv;
    case 2: 
      ArrayList localArrayList = new ArrayList(parama.gw.length);
      d.a[] arrayOfa2 = parama.gw;
      int k = arrayOfa2.length;
      while (i < k)
      {
        Object localObject3 = o(arrayOfa2[i]);
        if (localObject3 == arU) {
          return arU;
        }
        localArrayList.add(localObject3);
        i++;
      }
      return localArrayList;
    case 3: 
      if (parama.gx.length != parama.gy.length)
      {
        bh.T("Converting an invalid value to object: " + parama.toString());
        return arU;
      }
      HashMap localHashMap = new HashMap(parama.gy.length);
      while (i < parama.gx.length)
      {
        Object localObject1 = o(parama.gx[i]);
        Object localObject2 = o(parama.gy[i]);
        if ((localObject1 == arU) || (localObject2 == arU)) {
          return arU;
        }
        localHashMap.put(localObject1, localObject2);
        i++;
      }
      return localHashMap;
    case 4: 
      bh.T("Trying to convert a macro reference to object");
      return arU;
    case 5: 
      bh.T("Trying to convert a function id to object");
      return arU;
    case 6: 
      return Long.valueOf(parama.gB);
    case 7: 
      StringBuffer localStringBuffer = new StringBuffer();
      d.a[] arrayOfa1 = parama.gD;
      int j = arrayOfa1.length;
      while (i < j)
      {
        String str = j(arrayOfa1[i]);
        if (str == arY) {
          return arU;
        }
        localStringBuffer.append(str);
        i++;
      }
      return localStringBuffer.toString();
    }
    return Boolean.valueOf(parama.gC);
  }
  
  public static String p(Object paramObject)
  {
    if (paramObject == null) {
      return arY;
    }
    return paramObject.toString();
  }
  
  public static Object pE()
  {
    return arU;
  }
  
  public static Long pF()
  {
    return arV;
  }
  
  public static Double pG()
  {
    return arW;
  }
  
  public static Boolean pH()
  {
    return arZ;
  }
  
  public static dh pI()
  {
    return arX;
  }
  
  public static String pJ()
  {
    return arY;
  }
  
  public static d.a pK()
  {
    return asc;
  }
  
  public static dh q(Object paramObject)
  {
    if ((paramObject instanceof dh)) {
      return (dh)paramObject;
    }
    if (w(paramObject)) {
      return dh.z(x(paramObject));
    }
    if (v(paramObject)) {
      return dh.a(Double.valueOf(getDouble(paramObject)));
    }
    return cY(p(paramObject));
  }
  
  public static Long r(Object paramObject)
  {
    if (w(paramObject)) {
      return Long.valueOf(x(paramObject));
    }
    return cZ(p(paramObject));
  }
  
  public static Double s(Object paramObject)
  {
    if (v(paramObject)) {
      return Double.valueOf(getDouble(paramObject));
    }
    return da(p(paramObject));
  }
  
  public static Boolean t(Object paramObject)
  {
    if ((paramObject instanceof Boolean)) {
      return (Boolean)paramObject;
    }
    return db(p(paramObject));
  }
  
  public static d.a u(Object paramObject)
  {
    boolean bool1 = false;
    d.a locala1 = new d.a();
    if ((paramObject instanceof d.a)) {
      return (d.a)paramObject;
    }
    if ((paramObject instanceof String))
    {
      locala1.type = 1;
      locala1.gv = ((String)paramObject);
    }
    for (;;)
    {
      locala1.gF = bool1;
      return locala1;
      if ((paramObject instanceof List))
      {
        locala1.type = 2;
        List localList = (List)paramObject;
        ArrayList localArrayList3 = new ArrayList(localList.size());
        Iterator localIterator2 = localList.iterator();
        boolean bool4 = false;
        if (localIterator2.hasNext())
        {
          d.a locala4 = u(localIterator2.next());
          if (locala4 == asc) {
            return asc;
          }
          if ((bool4) || (locala4.gF)) {}
          for (boolean bool5 = true;; bool5 = false)
          {
            localArrayList3.add(locala4);
            bool4 = bool5;
            break;
          }
        }
        locala1.gw = ((d.a[])localArrayList3.toArray(new d.a[0]));
        bool1 = bool4;
      }
      else if ((paramObject instanceof Map))
      {
        locala1.type = 3;
        Set localSet = ((Map)paramObject).entrySet();
        ArrayList localArrayList1 = new ArrayList(localSet.size());
        ArrayList localArrayList2 = new ArrayList(localSet.size());
        Iterator localIterator1 = localSet.iterator();
        boolean bool2 = false;
        if (localIterator1.hasNext())
        {
          Map.Entry localEntry = (Map.Entry)localIterator1.next();
          d.a locala2 = u(localEntry.getKey());
          d.a locala3 = u(localEntry.getValue());
          if ((locala2 == asc) || (locala3 == asc)) {
            return asc;
          }
          if ((bool2) || (locala2.gF) || (locala3.gF)) {}
          for (boolean bool3 = true;; bool3 = false)
          {
            localArrayList1.add(locala2);
            localArrayList2.add(locala3);
            bool2 = bool3;
            break;
          }
        }
        locala1.gx = ((d.a[])localArrayList1.toArray(new d.a[0]));
        locala1.gy = ((d.a[])localArrayList2.toArray(new d.a[0]));
        bool1 = bool2;
      }
      else if (v(paramObject))
      {
        locala1.type = 1;
        locala1.gv = paramObject.toString();
        bool1 = false;
      }
      else if (w(paramObject))
      {
        locala1.type = 6;
        locala1.gB = x(paramObject);
        bool1 = false;
      }
      else
      {
        if (!(paramObject instanceof Boolean)) {
          break;
        }
        locala1.type = 8;
        locala1.gC = ((Boolean)paramObject).booleanValue();
        bool1 = false;
      }
    }
    StringBuilder localStringBuilder = new StringBuilder().append("Converting to Value from unknown object type: ");
    if (paramObject == null) {}
    for (String str = "null";; str = paramObject.getClass().toString())
    {
      bh.T(str);
      return asc;
    }
  }
  
  private static boolean v(Object paramObject)
  {
    return ((paramObject instanceof Double)) || ((paramObject instanceof Float)) || (((paramObject instanceof dh)) && (((dh)paramObject).pz()));
  }
  
  private static boolean w(Object paramObject)
  {
    return ((paramObject instanceof Byte)) || ((paramObject instanceof Short)) || ((paramObject instanceof Integer)) || ((paramObject instanceof Long)) || (((paramObject instanceof dh)) && (((dh)paramObject).pA()));
  }
  
  private static long x(Object paramObject)
  {
    if ((paramObject instanceof Number)) {
      return ((Number)paramObject).longValue();
    }
    bh.T("getInt64 received non-Number");
    return 0L;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.di
 * JD-Core Version:    0.7.0.1
 */