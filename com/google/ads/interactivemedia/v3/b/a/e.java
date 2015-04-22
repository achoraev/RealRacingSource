package com.google.ads.interactivemedia.v3.b.a;

import android.util.Log;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;

public class e
{
  public List<Float> adCuePoints;
  public a adData;
  public long adTimeUpdateMs;
  public String adUiStyle;
  public Map<String, c> companions;
  public int errorCode;
  public String errorMessage;
  public String innerError;
  public SortedSet<Float> internalCuePoints;
  public String ln;
  public a logData;
  public String m;
  public String n;
  public String translation;
  public String videoUrl;
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("JavaScriptMsgData[");
    Field[] arrayOfField = e.class.getFields();
    int i = arrayOfField.length;
    int j = 0;
    for (;;)
    {
      if (j < i)
      {
        Field localField = arrayOfField[j];
        try
        {
          Object localObject = localField.get(this);
          localStringBuilder.append(localField.getName()).append(":");
          localStringBuilder.append(localObject).append(",");
          j++;
        }
        catch (IllegalArgumentException localIllegalArgumentException)
        {
          for (;;)
          {
            Log.e("IMASDK", "IllegalArgumentException occurred", localIllegalArgumentException);
          }
        }
        catch (IllegalAccessException localIllegalAccessException)
        {
          for (;;)
          {
            Log.e("IMASDK", "IllegalAccessException occurred", localIllegalAccessException);
          }
        }
      }
    }
    return "]";
  }
  
  public class a
  {
    public int errorCode;
    public String errorMessage;
    public String innerError;
    public String type;
    
    public Map<String, String> constructMap()
    {
      HashMap localHashMap = new HashMap();
      localHashMap.put("type", this.type);
      localHashMap.put("errorCode", String.valueOf(this.errorCode));
      localHashMap.put("errorMessage", this.errorMessage);
      if (this.innerError != null) {
        localHashMap.put("innerError", this.innerError);
      }
      return localHashMap;
    }
    
    public String toString()
    {
      Object[] arrayOfObject = new Object[4];
      arrayOfObject[0] = this.type;
      arrayOfObject[1] = Integer.valueOf(this.errorCode);
      arrayOfObject[2] = this.errorMessage;
      arrayOfObject[3] = this.innerError;
      return String.format("Log[type=%s, errorCode=%s, errorMessage=%s, innerError=%s]", arrayOfObject);
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.b.a.e
 * JD-Core Version:    0.7.0.1
 */