package com.supersonicads.sdk.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SSAObj
{
  private JSONObject mJsonObject;
  
  public SSAObj() {}
  
  public SSAObj(String paramString)
  {
    setJsonObject(paramString);
  }
  
  private static Object fromJson(Object paramObject)
    throws JSONException
  {
    if (paramObject == JSONObject.NULL) {
      paramObject = null;
    }
    do
    {
      return paramObject;
      if ((paramObject instanceof JSONObject)) {
        return toMap((JSONObject)paramObject);
      }
    } while (!(paramObject instanceof JSONArray));
    return toList((JSONArray)paramObject);
  }
  
  private JSONObject getJsonObject()
  {
    return this.mJsonObject;
  }
  
  public static Map<String, Object> getMap(JSONObject paramJSONObject, String paramString)
    throws JSONException
  {
    return toMap(paramJSONObject.getJSONObject(paramString));
  }
  
  public static boolean isEmptyObject(JSONObject paramJSONObject)
  {
    return paramJSONObject.names() == null;
  }
  
  private void setJsonObject(String paramString)
  {
    try
    {
      this.mJsonObject = new JSONObject(paramString);
      return;
    }
    catch (JSONException localJSONException)
    {
      this.mJsonObject = new JSONObject();
    }
  }
  
  public static Object toJSON(Object paramObject)
    throws JSONException
  {
    Object localObject1;
    Map localMap;
    Iterator localIterator1;
    if ((paramObject instanceof Map))
    {
      localObject1 = new JSONObject();
      localMap = (Map)paramObject;
      localIterator1 = localMap.keySet().iterator();
      if (localIterator1.hasNext()) {}
    }
    for (;;)
    {
      return localObject1;
      Object localObject2 = localIterator1.next();
      ((JSONObject)localObject1).put(localObject2.toString(), toJSON(localMap.get(localObject2)));
      break;
      if (!(paramObject instanceof Iterable)) {
        return paramObject;
      }
      localObject1 = new JSONArray();
      Iterator localIterator2 = ((Iterable)paramObject).iterator();
      while (localIterator2.hasNext()) {
        ((JSONArray)localObject1).put(localIterator2.next());
      }
    }
    return paramObject;
  }
  
  public static List toList(JSONArray paramJSONArray)
    throws JSONException
  {
    ArrayList localArrayList = new ArrayList();
    for (int i = 0;; i++)
    {
      if (i >= paramJSONArray.length()) {
        return localArrayList;
      }
      localArrayList.add(fromJson(paramJSONArray.get(i)));
    }
  }
  
  public static Map<String, Object> toMap(JSONObject paramJSONObject)
    throws JSONException
  {
    HashMap localHashMap = new HashMap();
    Iterator localIterator = paramJSONObject.keys();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return localHashMap;
      }
      String str = (String)localIterator.next();
      localHashMap.put(str, fromJson(paramJSONObject.get(str)));
    }
  }
  
  public boolean containsKey(String paramString)
  {
    return getJsonObject().has(paramString);
  }
  
  public Object get(String paramString)
  {
    try
    {
      Object localObject = getJsonObject().get(paramString);
      return localObject;
    }
    catch (JSONException localJSONException) {}
    return null;
  }
  
  public boolean getBoolean(String paramString)
  {
    try
    {
      boolean bool = this.mJsonObject.getBoolean(paramString);
      return bool;
    }
    catch (JSONException localJSONException) {}
    return false;
  }
  
  public String getString(String paramString)
  {
    try
    {
      String str = this.mJsonObject.getString(paramString);
      return str;
    }
    catch (JSONException localJSONException) {}
    return null;
  }
  
  public boolean isNull(String paramString)
  {
    return getJsonObject().isNull(paramString);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.supersonicads.sdk.data.SSAObj
 * JD-Core Version:    0.7.0.1
 */