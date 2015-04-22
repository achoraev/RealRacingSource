package com.google.ads.interactivemedia.v3.b.a;

import com.google.ads.interactivemedia.v3.api.AdsRequest;
import com.google.ads.interactivemedia.v3.api.CompanionAdSlot;
import com.google.ads.interactivemedia.v3.api.ImaSdkSettings;
import com.google.ads.interactivemedia.v3.b.a;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class d
{
  private String adTagUrl;
  private String adsResponse;
  private Map<String, String> companionSlots;
  private String env;
  private Map<String, String> extraParameters;
  private String network;
  private ImaSdkSettings settings;
  
  public d(AdsRequest paramAdsRequest, String paramString1, String paramString2, ImaSdkSettings paramImaSdkSettings)
  {
    this.adTagUrl = paramAdsRequest.getAdTagUrl();
    this.adsResponse = paramAdsRequest.getAdsResponse();
    this.env = paramString1;
    this.network = paramString2;
    this.extraParameters = paramAdsRequest.getExtraParameters();
    this.settings = paramImaSdkSettings;
    Map localMap = ((a)paramAdsRequest.getAdDisplayContainer()).a();
    if ((localMap != null) && (!localMap.isEmpty()))
    {
      this.companionSlots = new HashMap();
      Iterator localIterator = localMap.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        CompanionAdSlot localCompanionAdSlot = (CompanionAdSlot)localMap.get(str);
        this.companionSlots.put(str, localCompanionAdSlot.getWidth() + "x" + localCompanionAdSlot.getHeight());
      }
    }
  }
  
  public String toString()
  {
    String str = "adTagUrl=" + this.adTagUrl + ", env=" + this.env + ", network=" + this.network + ", companionSlots=" + this.companionSlots + ", extraParameters=" + this.extraParameters + ", settings=" + this.settings;
    if (this.adsResponse != null) {
      str = str + ", adsResponse=" + this.adsResponse;
    }
    return "GsonAdsRequest [" + str + "]";
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.b.a.d
 * JD-Core Version:    0.7.0.1
 */