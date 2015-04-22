package com.jirbo.adcolony;

import java.io.Serializable;

public abstract class AdColonyAd
  implements Serializable
{
  static final int status_canceled = 1;
  static final int status_no_fill = 2;
  static final int status_no_request = 0;
  static final int status_shown = 4;
  static final int status_skipped = 3;
  ADCManifest.Ad ad_info;
  String ad_unit = "";
  String asi = "";
  double current_progress = 0.0D;
  double force_current_progress = 0.0D;
  int global_seek_to_ms;
  boolean is_native;
  boolean is_native_expanded;
  boolean replay;
  int status = 0;
  String view_format = "";
  String zone_id;
  ADCManifest.Zone zone_info;
  
  public boolean canceled()
  {
    return this.status == 1;
  }
  
  public int getAvailableViews()
  {
    if (!isReady()) {}
    while (!set_up_info()) {
      return 0;
    }
    return this.zone_info.get_available_plays();
  }
  
  abstract boolean isReady();
  
  abstract boolean isReady(boolean paramBoolean);
  
  abstract boolean is_v4vc();
  
  public boolean noFill()
  {
    return this.status == 2;
  }
  
  public boolean notShown()
  {
    return this.status != 4;
  }
  
  abstract void on_video_finished();
  
  boolean set_up_info()
  {
    return set_up_info(false);
  }
  
  boolean set_up_info(boolean paramBoolean)
  {
    boolean bool1;
    if (this.status == 4) {
      bool1 = true;
    }
    do
    {
      do
      {
        return bool1;
        if (isReady()) {
          break;
        }
        bool1 = false;
      } while (!paramBoolean);
      if (isReady(true)) {
        break;
      }
      bool1 = false;
    } while (paramBoolean);
    this.zone_info = ADC.controller.get_zone_info(this.zone_id);
    ADCManifest.Ad localAd;
    if (paramBoolean)
    {
      localAd = this.zone_info.current_native_ad();
      this.ad_info = localAd;
      if (this.ad_info == null) {
        break label94;
      }
    }
    label94:
    for (boolean bool2 = true;; bool2 = false)
    {
      return bool2;
      localAd = this.zone_info.current_ad();
      break;
    }
  }
  
  public boolean shown()
  {
    return this.status == 4;
  }
  
  public boolean skipped()
  {
    return this.status == 3;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.jirbo.adcolony.AdColonyAd
 * JD-Core Version:    0.7.0.1
 */