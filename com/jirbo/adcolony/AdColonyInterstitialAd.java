package com.jirbo.adcolony;

import android.graphics.Bitmap;
import java.util.ArrayList;

public class AdColonyInterstitialAd
  extends AdColonyAd
{
  AdColonyAdListener listener;
  AdColonyNativeAdView native_ad;
  AdColonyNativeAdListener native_listener;
  
  public AdColonyInterstitialAd()
  {
    ADC.show_post_popup = false;
    ADC.ensure_configured();
    this.ad_unit = "interstitial";
    this.view_format = "fullscreen";
    this.asi = ADCUtil.create_uuid();
  }
  
  public AdColonyInterstitialAd(String paramString)
  {
    this.ad_unit = "interstitial";
    this.view_format = "fullscreen";
    ADC.ensure_configured();
    this.zone_id = paramString;
    this.asi = ADCUtil.create_uuid();
  }
  
  public boolean isReady()
  {
    if (this.zone_id == null)
    {
      this.zone_id = ADC.controller.find_video_zone();
      if (this.zone_id == null) {
        return false;
      }
    }
    if (AdColony.isZoneNative(this.zone_id))
    {
      ADC.error_code = 12;
      return false;
    }
    return ADC.controller.is_video_ad_available(this.zone_id);
  }
  
  boolean isReady(boolean paramBoolean)
  {
    if (this.zone_id == null)
    {
      this.zone_id = ADC.controller.find_video_zone();
      if (this.zone_id == null) {
        return false;
      }
    }
    return ADC.controller.is_video_ad_available(this.zone_id);
  }
  
  boolean is_v4vc()
  {
    return false;
  }
  
  void on_video_finished()
  {
    this.ad_unit = "interstitial";
    this.view_format = "fullscreen";
    if (this.listener != null) {
      this.listener.onAdColonyAdAttemptFinished(this);
    }
    for (;;)
    {
      ADC.has_ad_availability_changed();
      System.gc();
      if ((ADC.show_post_popup) || (AdColonyBrowser.is_open)) {
        break label108;
      }
      for (int i = 0; i < ADC.bitmaps.size(); i++) {
        ((Bitmap)ADC.bitmaps.get(i)).recycle();
      }
      if (this.native_listener != null) {
        this.native_listener.onAdColonyNativeAdFinished(true, this.native_ad);
      }
    }
    ADC.bitmaps.clear();
    label108:
    ADC.current_video = null;
    ADC.show = true;
  }
  
  public void show()
  {
    ADC.error_code = 0;
    this.ad_unit = "interstitial";
    this.view_format = "fullscreen";
    if (!isReady())
    {
      new ADCEvent(ADC.controller)
      {
        void dispatch()
        {
          if (AdColonyInterstitialAd.this.zone_id != null) {
            this.controller.reporting_manager.track_ad_request(AdColonyInterstitialAd.this.zone_id, AdColonyInterstitialAd.this);
          }
        }
      };
      this.status = 2;
      if (this.listener != null) {
        this.listener.onAdColonyAdAttemptFinished(this);
      }
      return;
    }
    if (ADC.show)
    {
      new ADCEvent(ADC.controller)
      {
        void dispatch()
        {
          this.controller.reporting_manager.track_ad_request(AdColonyInterstitialAd.this.zone_id, AdColonyInterstitialAd.this);
        }
      };
      ADC.show = false;
      set_up_info();
      ADC.current_ad = this;
      if (!ADC.controller.show_video_ad(this))
      {
        if (this.listener != null) {
          this.listener.onAdColonyAdAttemptFinished(this);
        }
        ADC.show = true;
        return;
      }
      if (this.listener != null) {
        this.listener.onAdColonyAdStarted(this);
      }
    }
    this.status = 4;
  }
  
  public AdColonyInterstitialAd withListener(AdColonyAdListener paramAdColonyAdListener)
  {
    this.listener = paramAdColonyAdListener;
    return this;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.jirbo.adcolony.AdColonyInterstitialAd
 * JD-Core Version:    0.7.0.1
 */