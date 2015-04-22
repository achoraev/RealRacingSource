package com.google.android.gms.tagmanager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataLayer
{
  public static final String EVENT_KEY = "event";
  public static final Object OBJECT_NOT_PRESENT = new Object();
  static final String[] aoG = "gtm.lifetime".toString().split("\\.");
  private static final Pattern aoH = Pattern.compile("(\\d+)\\s*([smhd]?)");
  private final ConcurrentHashMap<b, Integer> aoI;
  private final Map<String, Object> aoJ;
  private final ReentrantLock aoK;
  private final LinkedList<Map<String, Object>> aoL;
  private final c aoM;
  private final CountDownLatch aoN;
  
  DataLayer()
  {
    this(new c()
    {
      public void a(DataLayer.c.a paramAnonymousa)
      {
        paramAnonymousa.g(new ArrayList());
      }
      
      public void a(List<DataLayer.a> paramAnonymousList, long paramAnonymousLong) {}
      
      public void cx(String paramAnonymousString) {}
    });
  }
  
  DataLayer(c paramc)
  {
    this.aoM = paramc;
    this.aoI = new ConcurrentHashMap();
    this.aoJ = new HashMap();
    this.aoK = new ReentrantLock();
    this.aoL = new LinkedList();
    this.aoN = new CountDownLatch(1);
    oe();
  }
  
  private void F(Map<String, Object> paramMap)
  {
    this.aoK.lock();
    try
    {
      this.aoL.offer(paramMap);
      if (this.aoK.getHoldCount() == 1) {
        of();
      }
      G(paramMap);
      return;
    }
    finally
    {
      this.aoK.unlock();
    }
  }
  
  private void G(Map<String, Object> paramMap)
  {
    Long localLong = H(paramMap);
    if (localLong == null) {
      return;
    }
    List localList = J(paramMap);
    localList.remove("gtm.lifetime");
    this.aoM.a(localList, localLong.longValue());
  }
  
  private Long H(Map<String, Object> paramMap)
  {
    Object localObject = I(paramMap);
    if (localObject == null) {
      return null;
    }
    return cw(localObject.toString());
  }
  
  private Object I(Map<String, Object> paramMap)
  {
    String[] arrayOfString = aoG;
    int i = arrayOfString.length;
    int j = 0;
    Object localObject2;
    for (Object localObject1 = paramMap;; localObject1 = localObject2)
    {
      String str;
      if (j < i)
      {
        str = arrayOfString[j];
        if (!(localObject1 instanceof Map)) {
          localObject1 = null;
        }
      }
      else
      {
        return localObject1;
      }
      localObject2 = ((Map)localObject1).get(str);
      j++;
    }
  }
  
  private List<a> J(Map<String, Object> paramMap)
  {
    ArrayList localArrayList = new ArrayList();
    a(paramMap, "", localArrayList);
    return localArrayList;
  }
  
  private void K(Map<String, Object> paramMap)
  {
    synchronized (this.aoJ)
    {
      Iterator localIterator = paramMap.keySet().iterator();
      if (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        a(c(str, paramMap.get(str)), this.aoJ);
      }
    }
    L(paramMap);
  }
  
  private void L(Map<String, Object> paramMap)
  {
    Iterator localIterator = this.aoI.keySet().iterator();
    while (localIterator.hasNext()) {
      ((b)localIterator.next()).D(paramMap);
    }
  }
  
  private void a(Map<String, Object> paramMap, String paramString, Collection<a> paramCollection)
  {
    Iterator localIterator = paramMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      StringBuilder localStringBuilder = new StringBuilder().append(paramString);
      if (paramString.length() == 0) {}
      String str2;
      for (String str1 = "";; str1 = ".")
      {
        str2 = str1 + (String)localEntry.getKey();
        if (!(localEntry.getValue() instanceof Map)) {
          break label124;
        }
        a((Map)localEntry.getValue(), str2, paramCollection);
        break;
      }
      label124:
      if (!str2.equals("gtm.lifetime")) {
        paramCollection.add(new a(str2, localEntry.getValue()));
      }
    }
  }
  
  static Long cw(String paramString)
  {
    Matcher localMatcher = aoH.matcher(paramString);
    if (!localMatcher.matches())
    {
      bh.U("unknown _lifetime: " + paramString);
      return null;
    }
    long l1;
    try
    {
      long l2 = Long.parseLong(localMatcher.group(1));
      l1 = l2;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      for (;;)
      {
        bh.W("illegal number in _lifetime value: " + paramString);
        l1 = 0L;
      }
      String str = localMatcher.group(2);
      if (str.length() != 0) {
        break label133;
      }
      return Long.valueOf(l1);
      switch (str.charAt(0))
      {
      default: 
        bh.W("unknown units in _lifetime: " + paramString);
        return null;
      }
    }
    if (l1 <= 0L)
    {
      bh.U("non-positive _lifetime: " + paramString);
      return null;
    }
    label133:
    return Long.valueOf(l1 * 1000L);
    return Long.valueOf(60L * (l1 * 1000L));
    return Long.valueOf(60L * (60L * (l1 * 1000L)));
    return Long.valueOf(24L * (60L * (60L * (l1 * 1000L))));
  }
  
  public static List<Object> listOf(Object... paramVarArgs)
  {
    ArrayList localArrayList = new ArrayList();
    for (int i = 0; i < paramVarArgs.length; i++) {
      localArrayList.add(paramVarArgs[i]);
    }
    return localArrayList;
  }
  
  public static Map<String, Object> mapOf(Object... paramVarArgs)
  {
    if (paramVarArgs.length % 2 != 0) {
      throw new IllegalArgumentException("expected even number of key-value pairs");
    }
    HashMap localHashMap = new HashMap();
    for (int i = 0; i < paramVarArgs.length; i += 2)
    {
      if (!(paramVarArgs[i] instanceof String)) {
        throw new IllegalArgumentException("key is not a string: " + paramVarArgs[i]);
      }
      localHashMap.put((String)paramVarArgs[i], paramVarArgs[(i + 1)]);
    }
    return localHashMap;
  }
  
  private void oe()
  {
    this.aoM.a(new DataLayer.c.a()
    {
      public void g(List<DataLayer.a> paramAnonymousList)
      {
        Iterator localIterator = paramAnonymousList.iterator();
        while (localIterator.hasNext())
        {
          DataLayer.a locala = (DataLayer.a)localIterator.next();
          DataLayer.a(DataLayer.this, DataLayer.this.c(locala.JO, locala.wq));
        }
        DataLayer.a(DataLayer.this).countDown();
      }
    });
  }
  
  private void of()
  {
    int j;
    for (int i = 0;; i = j)
    {
      Map localMap = (Map)this.aoL.poll();
      if (localMap != null)
      {
        K(localMap);
        j = i + 1;
        if (j > 500)
        {
          this.aoL.clear();
          throw new RuntimeException("Seems like an infinite loop of pushing to the data layer");
        }
      }
      else
      {
        return;
      }
    }
  }
  
  void a(b paramb)
  {
    this.aoI.put(paramb, Integer.valueOf(0));
  }
  
  void a(Map<String, Object> paramMap1, Map<String, Object> paramMap2)
  {
    Iterator localIterator = paramMap1.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      Object localObject = paramMap1.get(str);
      if ((localObject instanceof List))
      {
        if (!(paramMap2.get(str) instanceof List)) {
          paramMap2.put(str, new ArrayList());
        }
        b((List)localObject, (List)paramMap2.get(str));
      }
      else if ((localObject instanceof Map))
      {
        if (!(paramMap2.get(str) instanceof Map)) {
          paramMap2.put(str, new HashMap());
        }
        a((Map)localObject, (Map)paramMap2.get(str));
      }
      else
      {
        paramMap2.put(str, localObject);
      }
    }
  }
  
  void b(List<Object> paramList1, List<Object> paramList2)
  {
    while (paramList2.size() < paramList1.size()) {
      paramList2.add(null);
    }
    int i = 0;
    if (i < paramList1.size())
    {
      Object localObject = paramList1.get(i);
      if ((localObject instanceof List))
      {
        if (!(paramList2.get(i) instanceof List)) {
          paramList2.set(i, new ArrayList());
        }
        b((List)localObject, (List)paramList2.get(i));
      }
      for (;;)
      {
        i++;
        break;
        if ((localObject instanceof Map))
        {
          if (!(paramList2.get(i) instanceof Map)) {
            paramList2.set(i, new HashMap());
          }
          a((Map)localObject, (Map)paramList2.get(i));
        }
        else if (localObject != OBJECT_NOT_PRESENT)
        {
          paramList2.set(i, localObject);
        }
      }
    }
  }
  
  Map<String, Object> c(String paramString, Object paramObject)
  {
    HashMap localHashMap1 = new HashMap();
    String[] arrayOfString = paramString.toString().split("\\.");
    int i = 0;
    HashMap localHashMap2;
    for (Object localObject = localHashMap1; i < -1 + arrayOfString.length; localObject = localHashMap2)
    {
      localHashMap2 = new HashMap();
      ((Map)localObject).put(arrayOfString[i], localHashMap2);
      i++;
    }
    ((Map)localObject).put(arrayOfString[(-1 + arrayOfString.length)], paramObject);
    return localHashMap1;
  }
  
  void cv(String paramString)
  {
    push(paramString, null);
    this.aoM.cx(paramString);
  }
  
  public Object get(String paramString)
  {
    for (;;)
    {
      int j;
      Object localObject3;
      synchronized (this.aoJ)
      {
        Map localMap2 = this.aoJ;
        String[] arrayOfString = paramString.split("\\.");
        int i = arrayOfString.length;
        localObject2 = localMap2;
        j = 0;
        if (j < i)
        {
          String str = arrayOfString[j];
          if (!(localObject2 instanceof Map)) {
            return null;
          }
          localObject3 = ((Map)localObject2).get(str);
          if (localObject3 == null) {
            return null;
          }
        }
        else
        {
          return localObject2;
        }
      }
      j++;
      Object localObject2 = localObject3;
    }
  }
  
  public void push(String paramString, Object paramObject)
  {
    push(c(paramString, paramObject));
  }
  
  public void push(Map<String, Object> paramMap)
  {
    try
    {
      this.aoN.await();
      F(paramMap);
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      for (;;)
      {
        bh.W("DataLayer.push: unexpected InterruptedException");
      }
    }
  }
  
  public void pushEvent(String paramString, Map<String, Object> paramMap)
  {
    HashMap localHashMap = new HashMap(paramMap);
    localHashMap.put("event", paramString);
    push(localHashMap);
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder;
    synchronized (this.aoJ)
    {
      localStringBuilder = new StringBuilder();
      Iterator localIterator = this.aoJ.entrySet().iterator();
      if (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = localEntry.getKey();
        arrayOfObject[1] = localEntry.getValue();
        localStringBuilder.append(String.format("{\n\tKey: %s\n\tValue: %s\n}\n", arrayOfObject));
      }
    }
    String str = localStringBuilder.toString();
    return str;
  }
  
  static final class a
  {
    public final String JO;
    public final Object wq;
    
    a(String paramString, Object paramObject)
    {
      this.JO = paramString;
      this.wq = paramObject;
    }
    
    public boolean equals(Object paramObject)
    {
      if (!(paramObject instanceof a)) {}
      a locala;
      do
      {
        return false;
        locala = (a)paramObject;
      } while ((!this.JO.equals(locala.JO)) || (!this.wq.equals(locala.wq)));
      return true;
    }
    
    public int hashCode()
    {
      Integer[] arrayOfInteger = new Integer[2];
      arrayOfInteger[0] = Integer.valueOf(this.JO.hashCode());
      arrayOfInteger[1] = Integer.valueOf(this.wq.hashCode());
      return Arrays.hashCode(arrayOfInteger);
    }
    
    public String toString()
    {
      return "Key: " + this.JO + " value: " + this.wq.toString();
    }
  }
  
  static abstract interface b
  {
    public abstract void D(Map<String, Object> paramMap);
  }
  
  static abstract interface c
  {
    public abstract void a(a parama);
    
    public abstract void a(List<DataLayer.a> paramList, long paramLong);
    
    public abstract void cx(String paramString);
    
    public static abstract interface a
    {
      public abstract void g(List<DataLayer.a> paramList);
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.DataLayer
 * JD-Core Version:    0.7.0.1
 */