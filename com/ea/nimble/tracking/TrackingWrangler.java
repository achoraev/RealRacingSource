package com.ea.nimble.tracking;

import com.ea.nimble.Base;
import com.ea.nimble.Component;
import com.ea.nimble.Log.Helper;
import com.ea.nimble.LogSource;
import java.util.Map;

public class TrackingWrangler
  extends Component
  implements ITracking, LogSource
{
  private ITracking[] m_trackingComponents;
  
  public void addCustomSessionData(String paramString1, String paramString2)
  {
    ITracking[] arrayOfITracking = this.m_trackingComponents;
    int i = arrayOfITracking.length;
    for (int j = 0; j < i; j++) {
      arrayOfITracking[j].addCustomSessionData(paramString1, paramString2);
    }
  }
  
  public void clearCustomSessionData()
  {
    ITracking[] arrayOfITracking = this.m_trackingComponents;
    int i = arrayOfITracking.length;
    for (int j = 0; j < i; j++) {
      arrayOfITracking[j].clearCustomSessionData();
    }
  }
  
  public String getComponentId()
  {
    return "com.ea.nimble.tracking";
  }
  
  public boolean getEnable()
  {
    boolean bool = false;
    ITracking[] arrayOfITracking = this.m_trackingComponents;
    int i = arrayOfITracking.length;
    int j = 0;
    if (j < i)
    {
      ITracking localITracking = arrayOfITracking[j];
      if ((bool) || (localITracking.getEnable())) {}
      for (bool = true;; bool = false)
      {
        j++;
        break;
      }
    }
    return bool;
  }
  
  public String getLogSourceTitle()
  {
    return "Tracking";
  }
  
  public void logEvent(String paramString, Map<String, String> paramMap)
  {
    Log.Helper.LOGD(this, "Logging event, " + paramString, new Object[0]);
    ITracking[] arrayOfITracking = this.m_trackingComponents;
    int i = arrayOfITracking.length;
    for (int j = 0; j < i; j++) {
      arrayOfITracking[j].logEvent(paramString, paramMap);
    }
  }
  
  public void restore()
  {
    Component[] arrayOfComponent = Base.getComponentList("com.ea.nimble.trackingimpl");
    this.m_trackingComponents = new ITracking[arrayOfComponent.length];
    for (int i = 0; i < arrayOfComponent.length; i++) {
      this.m_trackingComponents[i] = ((ITracking)arrayOfComponent[i]);
    }
  }
  
  public void setEnable(boolean paramBoolean)
  {
    StringBuilder localStringBuilder1 = new StringBuilder();
    String str1;
    int j;
    label50:
    ITracking localITracking;
    StringBuilder localStringBuilder2;
    if (paramBoolean)
    {
      str1 = "ENABLE";
      Log.Helper.LOGD(this, str1 + " tracking", new Object[0]);
      ITracking[] arrayOfITracking = this.m_trackingComponents;
      int i = arrayOfITracking.length;
      j = 0;
      if (j >= i) {
        return;
      }
      localITracking = arrayOfITracking[j];
      localITracking.setEnable(paramBoolean);
      if (localITracking.getEnable() != paramBoolean)
      {
        localStringBuilder2 = new StringBuilder();
        if (!paramBoolean) {
          break label146;
        }
      }
    }
    label146:
    for (String str2 = "ENABLE";; str2 = "DISABLE")
    {
      Log.Helper.LOGE(this, str2 + " failed for tracking component, " + localITracking.getClass().getName(), new Object[0]);
      j++;
      break label50;
      str1 = "DISABLE";
      break;
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ea.nimble.tracking.TrackingWrangler
 * JD-Core Version:    0.7.0.1
 */