package com.jirbo.adcolony;

import android.graphics.Bitmap;
import java.util.ArrayList;

public final class AdColonyV4VCAd
  extends AdColonyAd
{
  AdColonyAdListener listener;
  boolean show_post_popup = false;
  boolean show_pre_popup = false;
  
  public AdColonyV4VCAd()
  {
    ADC.show_post_popup = false;
    ADC.ensure_configured();
    this.ad_unit = "v4vc";
    this.view_format = "fullscreen";
    this.asi = ADCUtil.create_uuid();
  }
  
  public AdColonyV4VCAd(String paramString)
  {
    ADC.ensure_configured();
    this.zone_id = paramString;
    this.ad_unit = "v4vc";
    this.view_format = "fullscreen";
    this.asi = ADCUtil.create_uuid();
  }
  
  public int getRemainingViewsUntilReward()
  {
    if (!set_up_info()) {
      return 0;
    }
    return this.zone_info.v4vc.videos_per_reward - ADC.controller.get_reward_credit(this.zone_info.v4vc.reward_name);
  }
  
  public int getRewardAmount()
  {
    if (!set_up_info()) {
      return 0;
    }
    return this.zone_info.v4vc.reward_amount;
  }
  
  public String getRewardName()
  {
    if (!set_up_info()) {
      return "";
    }
    return this.zone_info.v4vc.reward_name;
  }
  
  public int getViewsPerReward()
  {
    if (!set_up_info()) {
      return 0;
    }
    return this.zone_info.v4vc.videos_per_reward;
  }
  
  public boolean isReady()
  {
    if (this.zone_id == null)
    {
      this.zone_id = ADC.controller.find_v4vc_zone();
      if (this.zone_id == null) {
        return false;
      }
    }
    return ADC.controller.is_v4vc_ad_available(this.zone_id);
  }
  
  boolean isReady(boolean paramBoolean)
  {
    return false;
  }
  
  boolean is_v4vc()
  {
    return true;
  }
  
  void on_dialog_finished(boolean paramBoolean)
  {
    if (paramBoolean) {
      if (ADC.controller.show_v4vc_ad(this))
      {
        if (this.listener != null) {
          this.listener.onAdColonyAdStarted(this);
        }
        this.status = 4;
      }
    }
    for (;;)
    {
      if ((this.status != 4) && (this.listener != null)) {
        this.listener.onAdColonyAdAttemptFinished(this);
      }
      return;
      this.status = 3;
      continue;
      this.status = 1;
    }
  }
  
  void on_video_finished()
  {
    if ((this.status == 4) && (this.show_post_popup)) {
      setDialog("Result");
    }
    if (this.listener != null) {
      this.listener.onAdColonyAdAttemptFinished(this);
    }
    ADC.has_ad_availability_changed();
    if ((!ADC.show_post_popup) && (!AdColonyBrowser.is_open))
    {
      for (int i = 0; i < ADC.bitmaps.size(); i++) {
        ((Bitmap)ADC.bitmaps.get(i)).recycle();
      }
      ADC.bitmaps.clear();
    }
    ADC.current_video = null;
    if (!this.show_post_popup) {
      ADC.show = true;
    }
    System.gc();
  }
  
  void setDialog(String paramString)
  {
    String str1 = getRewardName();
    String str2 = "" + getRewardAmount();
    String str3 = str2 + " " + str1;
    if (paramString.equals("Confirmation"))
    {
      ADC.current_dialog = new ADCV4VCConfirmationDialog(str3, this);
      return;
    }
    ADC.current_dialog = new ADCV4VCResultsDialog(str3, this);
  }
  
  public void show()
  {
    ADC.error_code = 0;
    if (!isReady())
    {
      new ADCEvent(ADC.controller)
      {
        void dispatch()
        {
          if (AdColonyV4VCAd.this.zone_id != null) {
            this.controller.reporting_manager.track_ad_request(AdColonyV4VCAd.this.zone_id, AdColonyV4VCAd.this);
          }
        }
      };
      this.status = 2;
      if (this.listener != null) {
        this.listener.onAdColonyAdAttemptFinished(this);
      }
    }
    while (!ADC.show) {
      return;
    }
    new ADCEvent(ADC.controller)
    {
      void dispatch()
      {
        this.controller.reporting_manager.track_ad_request(AdColonyV4VCAd.this.zone_id, AdColonyV4VCAd.this);
      }
    };
    ADC.show = false;
    set_up_info();
    ADC.current_ad = this;
    ADC.controller.prepare_v4vc_ad(this);
    if (this.show_pre_popup)
    {
      setDialog("Confirmation");
      return;
    }
    on_dialog_finished(true);
  }
  
  public AdColonyV4VCAd withConfirmationDialog()
  {
    return withConfirmationDialog(true);
  }
  
  public AdColonyV4VCAd withConfirmationDialog(boolean paramBoolean)
  {
    this.show_pre_popup = paramBoolean;
    return this;
  }
  
  public AdColonyV4VCAd withListener(AdColonyAdListener paramAdColonyAdListener)
  {
    this.listener = paramAdColonyAdListener;
    return this;
  }
  
  public AdColonyV4VCAd withResultsDialog()
  {
    return withResultsDialog(true);
  }
  
  public AdColonyV4VCAd withResultsDialog(boolean paramBoolean)
  {
    this.show_post_popup = paramBoolean;
    ADC.show_post_popup = this.show_post_popup;
    return this;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.jirbo.adcolony.AdColonyV4VCAd
 * JD-Core Version:    0.7.0.1
 */