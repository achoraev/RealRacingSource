package com.google.ads.interactivemedia.v3.b.a;

import com.google.ads.interactivemedia.v3.api.AdPodInfo;

public class b
  implements AdPodInfo
{
  public int adPosition = 1;
  public boolean isBumper = false;
  public double maxDuration = -1.0D;
  public int podIndex;
  public double timeOffset;
  public int totalAds = 1;
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    b localb;
    do
    {
      return true;
      if (paramObject == null) {
        return false;
      }
      if (getClass() != paramObject.getClass()) {
        return false;
      }
      localb = (b)paramObject;
      if (this.adPosition != localb.adPosition) {
        return false;
      }
      if (this.isBumper != localb.isBumper) {
        return false;
      }
      if (this.totalAds != localb.totalAds) {
        return false;
      }
      if (this.maxDuration != localb.maxDuration) {
        return false;
      }
      if (this.podIndex != localb.podIndex) {
        return false;
      }
    } while (this.timeOffset == localb.timeOffset);
    return false;
  }
  
  public int getAdPosition()
  {
    return this.adPosition;
  }
  
  public double getMaxDuration()
  {
    return this.maxDuration;
  }
  
  public int getPodIndex()
  {
    return this.podIndex;
  }
  
  public double getTimeOffset()
  {
    return this.timeOffset;
  }
  
  public int getTotalAds()
  {
    return this.totalAds;
  }
  
  public int hashCode()
  {
    int i = 31 * (31 + this.adPosition);
    if (this.isBumper) {}
    for (int j = 1231;; j = 1237)
    {
      int k = 31 * (j + i) + this.totalAds;
      long l1 = Double.doubleToLongBits(this.maxDuration);
      int m = 31 * (k * 31 + (int)(l1 ^ l1 >>> 32)) + this.podIndex;
      long l2 = Double.doubleToLongBits(this.timeOffset);
      return m * 31 + (int)(l2 ^ l2 >>> 32);
    }
  }
  
  public boolean isBumper()
  {
    return this.isBumper;
  }
  
  public String toString()
  {
    return "AdPodInfo [totalAds=" + this.totalAds + ", adPosition=" + this.adPosition + ", isBumper=" + this.isBumper + ", maxDuration=" + this.maxDuration + ", podIndex=" + this.podIndex + ", timeOffset=" + this.timeOffset + "]";
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.b.a.b
 * JD-Core Version:    0.7.0.1
 */