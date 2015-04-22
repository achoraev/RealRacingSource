package com.google.ads.interactivemedia.v3.b.a;

import com.google.ads.interactivemedia.v3.api.Ad;
import com.google.ads.interactivemedia.v3.api.AdPodInfo;
import java.util.Arrays;

public class a
  implements Ad
{
  private String adId;
  private b adPodInfo = new b();
  private String adSystem;
  private String[] adWrapperIds;
  private String[] adWrapperSystems;
  private String clickThroughUrl;
  private double duration;
  private int height;
  private boolean linear;
  private boolean skippable;
  private String traffickingParameters;
  private int width;
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    a locala;
    do
    {
      return true;
      if (paramObject == null) {
        return false;
      }
      if (getClass() != paramObject.getClass()) {
        return false;
      }
      locala = (a)paramObject;
      if (this.adId == null)
      {
        if (locala.adId != null) {
          return false;
        }
      }
      else if (!this.adId.equals(locala.adId)) {
        return false;
      }
      if (this.adSystem == null)
      {
        if (locala.adSystem != null) {
          return false;
        }
      }
      else if (!this.adSystem.equals(locala.adSystem)) {
        return false;
      }
      if (this.adPodInfo == null)
      {
        if (locala.adPodInfo != null) {
          return false;
        }
      }
      else if (!this.adPodInfo.equals(locala.adPodInfo)) {
        return false;
      }
      if (this.clickThroughUrl == null)
      {
        if (locala.clickThroughUrl != null) {
          return false;
        }
      }
      else if (!this.clickThroughUrl.equals(locala.clickThroughUrl)) {
        return false;
      }
      if (Double.doubleToLongBits(this.duration) != Double.doubleToLongBits(locala.duration)) {
        return false;
      }
      if (this.height != locala.height) {
        return false;
      }
      if (this.linear != locala.linear) {
        return false;
      }
      if (this.traffickingParameters == null)
      {
        if (locala.traffickingParameters != null) {
          return false;
        }
      }
      else if (!this.traffickingParameters.equals(locala.traffickingParameters)) {
        return false;
      }
    } while (this.width == locala.width);
    return false;
  }
  
  public String getAdId()
  {
    return this.adId;
  }
  
  public AdPodInfo getAdPodInfo()
  {
    return this.adPodInfo;
  }
  
  public String getAdSystem()
  {
    return this.adSystem;
  }
  
  public String[] getAdWrapperIds()
  {
    return this.adWrapperIds;
  }
  
  public String[] getAdWrapperSystems()
  {
    return this.adWrapperSystems;
  }
  
  public String getClickThruUrl()
  {
    return this.clickThroughUrl;
  }
  
  public double getDuration()
  {
    return this.duration;
  }
  
  public int getHeight()
  {
    return this.height;
  }
  
  public String getTraffickingParameters()
  {
    return this.traffickingParameters;
  }
  
  public int getWidth()
  {
    return this.width;
  }
  
  public int hashCode()
  {
    int i;
    int k;
    label26:
    int n;
    label44:
    int i3;
    label100:
    int i4;
    int i5;
    if (this.adId == null)
    {
      i = 0;
      int j = 31 * (i + 31);
      if (this.adPodInfo != null) {
        break label149;
      }
      k = 0;
      int m = 31 * (k + j);
      if (this.clickThroughUrl != null) {
        break label160;
      }
      n = 0;
      int i1 = n + m;
      long l = Double.doubleToLongBits(this.duration);
      int i2 = 31 * (31 * (i1 * 31 + (int)(l ^ l >>> 32)) + this.height);
      if (!this.linear) {
        break label172;
      }
      i3 = 1231;
      i4 = 31 * (i3 + i2);
      String str = this.traffickingParameters;
      i5 = 0;
      if (str != null) {
        break label180;
      }
    }
    for (;;)
    {
      return 31 * (i4 + i5) + this.width;
      i = this.adId.hashCode();
      break;
      label149:
      k = this.adPodInfo.hashCode();
      break label26;
      label160:
      n = this.clickThroughUrl.hashCode();
      break label44;
      label172:
      i3 = 1237;
      break label100;
      label180:
      i5 = this.traffickingParameters.hashCode();
    }
  }
  
  public boolean isLinear()
  {
    return this.linear;
  }
  
  public boolean isSkippable()
  {
    return this.skippable;
  }
  
  public String toString()
  {
    return "Ad [adId=" + this.adId + ", adWrapperIds=" + Arrays.toString(this.adWrapperIds) + ", adWrapperSystems=" + Arrays.toString(this.adWrapperSystems) + ", adSystem=" + this.adSystem + ", linear=" + this.linear + ", skippable=" + this.skippable + ", width=" + this.width + ", height=" + this.height + ", traffickingParameters=" + this.traffickingParameters + ", clickThroughUrl=" + this.clickThroughUrl + ", duration=" + this.duration + ", adPodInfo=" + this.adPodInfo + "]";
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.b.a.a
 * JD-Core Version:    0.7.0.1
 */