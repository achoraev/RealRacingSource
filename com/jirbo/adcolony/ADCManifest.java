package com.jirbo.adcolony;

import java.util.ArrayList;
import java.util.HashMap;

class ADCManifest
{
  static class Ad
  {
    int ad_campaign_id;
    int ad_group_id;
    int ad_id;
    ADCManifest.AdTracking ad_tracking;
    ADCManifest.CompanionAd companion_ad;
    boolean contracted;
    int cpcv_bid;
    boolean enable_in_app_store;
    int expires;
    boolean fullscreen;
    boolean house_ad;
    ADCManifest.InAppBrowser in_app_browser;
    boolean is_native_ad;
    ADCManifest.Limits limits;
    ADCManifest.NativeAd native_ad;
    int net_earnings;
    boolean test_ad;
    ADCManifest.ThirdPartyTracking third_party_tracking;
    String title;
    String uuid;
    ADCManifest.AdV4VC v4vc;
    ADCManifest.Video video;
    boolean video_events_on_replays;
    
    void cache_media()
    {
      this.v4vc.cache_media();
      this.in_app_browser.cache_media();
      this.native_ad.cache_media();
      this.companion_ad.cache_media();
      this.video.cache_media();
    }
    
    boolean is_ready()
    {
      if (!this.in_app_browser.is_ready()) {}
      while (((this.v4vc.enabled) && (!this.v4vc.is_ready())) || ((this.native_ad.enabled) && (!this.native_ad.is_ready())) || ((this.companion_ad.enabled) && (!this.companion_ad.is_ready())) || (!this.video.is_ready())) {
        return false;
      }
      return true;
    }
    
    boolean parse(ADCData.Table paramTable)
    {
      if (paramTable == null) {}
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                do
                {
                  do
                  {
                    do
                    {
                      return false;
                      this.uuid = paramTable.get_String("uuid");
                      this.title = paramTable.get_String("title");
                      this.ad_campaign_id = paramTable.get_Integer("ad_campaign_id");
                      this.ad_id = paramTable.get_Integer("ad_id");
                      this.ad_group_id = paramTable.get_Integer("ad_group_id");
                      this.cpcv_bid = paramTable.get_Integer("cpcv_bid");
                      this.net_earnings = paramTable.get_Integer("net_earnings");
                      this.expires = paramTable.get_Integer("expires");
                      this.enable_in_app_store = paramTable.get_Logical("enable_in_app_store");
                      this.video_events_on_replays = paramTable.get_Logical("video_events_on_replays");
                      this.test_ad = paramTable.get_Logical("test_ad");
                      this.fullscreen = paramTable.get_Logical("fullscreen");
                      this.house_ad = paramTable.get_Logical("house_ad");
                      this.contracted = paramTable.get_Logical("contracted");
                      this.limits = new ADCManifest.Limits();
                    } while (!this.limits.parse(paramTable.get_Table("limits")));
                    this.third_party_tracking = new ADCManifest.ThirdPartyTracking();
                  } while (!this.third_party_tracking.parse(paramTable.get_Table("third_party_tracking")));
                  this.in_app_browser = new ADCManifest.InAppBrowser();
                } while (!this.in_app_browser.parse(paramTable.get_Table("in_app_browser")));
                this.native_ad = new ADCManifest.NativeAd();
              } while (!this.native_ad.parse(paramTable.get_Table("native")));
              this.v4vc = new ADCManifest.AdV4VC();
            } while (!this.v4vc.parse(paramTable.get_Table("v4vc")));
            this.ad_tracking = new ADCManifest.AdTracking();
          } while (!this.ad_tracking.parse(paramTable.get_Table("ad_tracking")));
          this.companion_ad = new ADCManifest.CompanionAd();
        } while (!this.companion_ad.parse(paramTable.get_Table("companion_ad")));
        this.video = new ADCManifest.Video();
      } while (!this.video.parse(paramTable.get_Table("video")));
      return true;
    }
  }
  
  static class AdTracking
  {
    String _continue;
    String cancel;
    String card_dissolved;
    String card_shown;
    String complete;
    String custom_event;
    String download;
    String first_quartile;
    String html5_interaction;
    String in_video_engagement;
    String info;
    ADCData.Table lookup = new ADCData.Table();
    String midpoint;
    String native_complete;
    String native_first_quartile;
    String native_midpoint;
    String native_overlay_click;
    String native_start;
    String native_third_quartile;
    String replay;
    String reward_v4vc;
    String skip;
    String sound_mute;
    String sound_unmute;
    String start;
    String third_quartile;
    String video_expanded;
    String video_paused;
    String video_resumed;
    
    boolean parse(ADCData.Table paramTable)
    {
      if (paramTable == null) {
        return true;
      }
      this.replay = paramTable.get_String("replay", null);
      this.card_shown = paramTable.get_String("card_shown", null);
      this.html5_interaction = paramTable.get_String("html5_interaction", null);
      this.cancel = paramTable.get_String("cancel", null);
      this.download = paramTable.get_String("download", null);
      this.skip = paramTable.get_String("skip", null);
      this.info = paramTable.get_String("info", null);
      this.custom_event = paramTable.get_String("custom_event", null);
      this.midpoint = paramTable.get_String("midpoint", null);
      this.card_dissolved = paramTable.get_String("card_dissolved", null);
      this.start = paramTable.get_String("start", null);
      this.third_quartile = paramTable.get_String("third_quartile", null);
      this.complete = paramTable.get_String("complete", null);
      this._continue = paramTable.get_String("continue", null);
      this.in_video_engagement = paramTable.get_String("in_video_engagement", null);
      this.reward_v4vc = paramTable.get_String("reward_v4vc", null);
      this.first_quartile = paramTable.get_String("first_quartile", null);
      this.video_expanded = paramTable.get_String("video_expanded", null);
      this.sound_mute = paramTable.get_String("sound_mute", null);
      this.sound_unmute = paramTable.get_String("sound_unmute", null);
      this.video_paused = paramTable.get_String("video_paused", null);
      this.video_resumed = paramTable.get_String("video_resumed", null);
      this.native_start = paramTable.get_String("native_start", null);
      this.native_first_quartile = paramTable.get_String("native_first_quartile", null);
      this.native_midpoint = paramTable.get_String("native_midpoint", null);
      this.native_third_quartile = paramTable.get_String("native_third_quartile", null);
      this.native_complete = paramTable.get_String("native_complete", null);
      this.native_overlay_click = paramTable.get_String("native_overlay_click", null);
      this.lookup.set("replay", this.replay);
      this.lookup.set("card_shown", this.card_shown);
      this.lookup.set("html5_interaction", this.html5_interaction);
      this.lookup.set("cancel", this.cancel);
      this.lookup.set("download", this.download);
      this.lookup.set("skip", this.skip);
      this.lookup.set("info", this.info);
      this.lookup.set("custom_event", this.custom_event);
      this.lookup.set("midpoint", this.midpoint);
      this.lookup.set("card_dissolved", this.card_dissolved);
      this.lookup.set("start", this.start);
      this.lookup.set("third_quartile", this.third_quartile);
      this.lookup.set("complete", this.complete);
      this.lookup.set("continue", this._continue);
      this.lookup.set("in_video_engagement", this.in_video_engagement);
      this.lookup.set("reward_v4vc", this.reward_v4vc);
      this.lookup.set("first_quartile", this.first_quartile);
      this.lookup.set("video_expanded", this.video_expanded);
      this.lookup.set("sound_mute", this.sound_mute);
      this.lookup.set("sound_unmute", this.sound_unmute);
      this.lookup.set("video_paused", this.video_paused);
      this.lookup.set("video_resumed", this.video_resumed);
      this.lookup.set("native_start", this.native_start);
      this.lookup.set("native_first_quartile", this.native_first_quartile);
      this.lookup.set("native_midpoint", this.native_midpoint);
      this.lookup.set("native_third_quartile", this.native_third_quartile);
      this.lookup.set("native_complete", this.native_complete);
      this.lookup.set("native_overlay_click", this.native_overlay_click);
      return true;
    }
  }
  
  static class AdV4VC
  {
    boolean enabled;
    ADCManifest.PostPopupInfo post_popup;
    ADCManifest.PrePopupInfo pre_popup;
    
    void cache_media()
    {
      if (!this.enabled) {
        return;
      }
      this.pre_popup.cache_media();
      this.post_popup.cache_media();
    }
    
    boolean is_ready()
    {
      if (!this.pre_popup.is_ready()) {}
      while (!this.post_popup.is_ready()) {
        return false;
      }
      return true;
    }
    
    boolean parse(ADCData.Table paramTable)
    {
      if (paramTable == null) {}
      do
      {
        do
        {
          return false;
          this.enabled = paramTable.get_Logical("enabled");
          if (!this.enabled) {
            return true;
          }
          this.pre_popup = new ADCManifest.PrePopupInfo();
        } while (!this.pre_popup.parse(paramTable.get_Table("pre_popup")));
        this.post_popup = new ADCManifest.PostPopupInfo();
      } while (!this.post_popup.parse(paramTable.get_Table("post_popup")));
      return true;
    }
  }
  
  static class Ads
  {
    ArrayList<ADCManifest.Ad> data = new ArrayList();
    
    void add(ADCManifest.Ad paramAd)
    {
      this.data.add(paramAd);
    }
    
    int count()
    {
      return this.data.size();
    }
    
    ADCManifest.Ad find(String paramString)
    {
      for (int i = 0; i < this.data.size(); i++)
      {
        ADCManifest.Ad localAd = (ADCManifest.Ad)this.data.get(i);
        if (localAd.uuid.equals(paramString)) {
          return localAd;
        }
      }
      return null;
    }
    
    ADCManifest.Ad find_native_ad(int paramInt)
    {
      ADCManifest.Ad localAd;
      for (int i = paramInt; i < this.data.size(); i++)
      {
        localAd = (ADCManifest.Ad)this.data.get(i);
        if (localAd.native_ad.enabled) {
          return localAd;
        }
      }
      for (int j = 0;; j++)
      {
        if (j >= this.data.size()) {
          break label89;
        }
        localAd = (ADCManifest.Ad)this.data.get(j);
        if (localAd.native_ad.enabled) {
          break;
        }
      }
      label89:
      return null;
    }
    
    ADCManifest.Ad first()
    {
      return (ADCManifest.Ad)this.data.get(0);
    }
    
    ADCManifest.Ad get(int paramInt)
    {
      return (ADCManifest.Ad)this.data.get(paramInt);
    }
    
    boolean parse(ADCData.List paramList)
    {
      if (paramList == null) {
        return false;
      }
      for (int i = 0;; i++)
      {
        if (i >= paramList.count()) {
          break label51;
        }
        ADCManifest.Ad localAd = new ADCManifest.Ad();
        if (!localAd.parse(paramList.get_Table(i))) {
          break;
        }
        this.data.add(localAd);
      }
      label51:
      return true;
    }
  }
  
  static class App
  {
    ADCManifest.AppTracking app_tracking;
    String cache_network_pass_filter;
    boolean collect_iap_enabled;
    ArrayList<String> console_messages;
    boolean enabled;
    boolean hardware_acceleration_disabled = false;
    String last_country;
    String last_ip;
    String log_level;
    boolean log_screen_overlay;
    double media_pool_size;
    ADCManifest.ThirdPartyAppTracking third_party_app_tracking;
    String view_network_pass_filter;
    ADCManifest.Zones zones;
    
    void cache_media()
    {
      ADCLog.dev.println("Caching media");
      if (!this.enabled) {}
      for (;;)
      {
        return;
        for (int i = 0; i < this.zones.count(); i++) {
          this.zones.get(i).cache_media();
        }
      }
    }
    
    boolean is_ad_available(String paramString)
    {
      return is_ad_available(paramString, false, true);
    }
    
    boolean is_ad_available(String paramString, boolean paramBoolean1, boolean paramBoolean2)
    {
      if (!this.enabled) {}
      ADCManifest.Zone localZone;
      do
      {
        return false;
        localZone = this.zones.find(paramString);
      } while (localZone == null);
      return localZone.is_ad_available(paramBoolean1, paramBoolean2);
    }
    
    boolean parse(ADCData.Table paramTable)
    {
      if (paramTable == null) {}
      do
      {
        do
        {
          do
          {
            return false;
            this.enabled = paramTable.get_Logical("enabled");
            this.log_screen_overlay = paramTable.get_Logical("log_screen_overlay");
            this.last_country = paramTable.get_String("last_country");
            this.last_ip = paramTable.get_String("last_ip");
            this.collect_iap_enabled = paramTable.get_Logical("collect_iap_enabled");
            this.media_pool_size = paramTable.get_Real("media_pool_size");
            this.log_level = paramTable.get_String("log_level");
            this.view_network_pass_filter = paramTable.get_String("view_network_pass_filter");
            this.cache_network_pass_filter = paramTable.get_String("cache_network_pass_filter");
            this.hardware_acceleration_disabled = paramTable.get_Logical("hardware_acceleration_disabled");
            if ((this.view_network_pass_filter == null) || (this.view_network_pass_filter.equals(""))) {
              this.view_network_pass_filter = "all";
            }
            if ((this.cache_network_pass_filter == null) || (this.cache_network_pass_filter.equals(""))) {
              this.cache_network_pass_filter = "all";
            }
            this.app_tracking = new ADCManifest.AppTracking();
          } while (!this.app_tracking.parse(paramTable.get_Table("tracking")));
          this.third_party_app_tracking = new ADCManifest.ThirdPartyAppTracking();
        } while (!this.third_party_app_tracking.parse(paramTable.get_Table("third_party_tracking")));
        this.console_messages = paramTable.get_StringList("console_messages");
        ADCLog.dev.println("Parsing zones");
        this.zones = new ADCManifest.Zones();
      } while (!this.zones.parse(paramTable.get_List("zones")));
      ADCLog.dev.println("Finished parsing app info");
      return true;
    }
  }
  
  static class AppTracking
  {
    String dynamic_interests;
    String in_app_purchase;
    String install;
    ADCData.Table lookup;
    String session_end;
    String session_start;
    String update;
    String user_meta_data;
    
    boolean parse(ADCData.Table paramTable)
    {
      if (paramTable == null) {
        return true;
      }
      this.update = paramTable.get_String("update", null);
      this.install = paramTable.get_String("install", null);
      this.dynamic_interests = paramTable.get_String("dynamic_interests", null);
      this.user_meta_data = paramTable.get_String("user_meta_data", null);
      this.in_app_purchase = paramTable.get_String("in_app_purchase", null);
      this.session_end = paramTable.get_String("session_end", null);
      this.session_start = paramTable.get_String("session_start", null);
      this.lookup = new ADCData.Table();
      this.lookup.set("update", this.update);
      this.lookup.set("install", this.install);
      this.lookup.set("dynamic_interests", this.dynamic_interests);
      this.lookup.set("user_meta_data", this.user_meta_data);
      this.lookup.set("in_app_purchase", this.in_app_purchase);
      this.lookup.set("session_end", this.session_end);
      this.lookup.set("session_start", this.session_start);
      return true;
    }
  }
  
  static class ButtonInfo
  {
    String click_action;
    String click_action_type;
    int delay;
    boolean enabled;
    String event;
    int height;
    String image_down;
    String image_down_last_modified;
    String image_normal;
    String image_normal_last_modified;
    String label;
    String label_html;
    String label_rgba;
    String label_shadow_rgba;
    int scale;
    int width;
    
    void cache_media()
    {
      ADC.controller.media_manager.cache(this.image_normal, this.image_normal_last_modified);
      ADC.controller.media_manager.cache(this.image_down, this.image_down_last_modified);
    }
    
    boolean is_ready()
    {
      if (!this.enabled) {}
      do
      {
        return true;
        if (!ADC.controller.media_manager.is_cached(this.image_normal)) {
          return false;
        }
      } while (ADC.controller.media_manager.is_cached(this.image_down));
      return false;
    }
    
    boolean parse(ADCData.Table paramTable)
    {
      if (paramTable == null) {
        return false;
      }
      this.enabled = paramTable.get_Logical("enabled", true);
      this.delay = paramTable.get_Integer("delay");
      this.width = paramTable.get_Integer("width");
      this.height = paramTable.get_Integer("height");
      this.scale = paramTable.get_Integer("scale");
      this.image_normal = paramTable.get_String("image_normal");
      this.image_normal_last_modified = paramTable.get_String("image_normal_last_modified");
      this.image_down = paramTable.get_String("image_down");
      this.image_down_last_modified = paramTable.get_String("image_down_last_modified");
      this.click_action = paramTable.get_String("click_action");
      this.click_action_type = paramTable.get_String("click_action_type");
      this.label = paramTable.get_String("label");
      this.label_rgba = paramTable.get_String("label_rgba");
      this.label_shadow_rgba = paramTable.get_String("label_shadow_rgba");
      this.label_html = paramTable.get_String("label_html");
      this.event = paramTable.get_String("event");
      return true;
    }
  }
  
  static class CompanionAd
  {
    ADCManifest.StaticCompanionAdInfo _static;
    int ad_campaign_id;
    int ad_id;
    boolean dissolve;
    double dissolve_delay;
    boolean enable_in_app_store;
    boolean enabled;
    ADCManifest.HTML5CompanionAdInfo html5;
    String uuid;
    
    void cache_media()
    {
      if (!this.enabled) {
        return;
      }
      this._static.cache_media();
      this.html5.cache_media();
    }
    
    boolean is_ready()
    {
      if ((this.html5.enabled) && (!this.html5.is_ready())) {}
      do
      {
        return false;
        if (!this.enabled) {
          return true;
        }
      } while ((!this._static.is_ready()) && (!this.html5.is_ready()));
      return true;
    }
    
    boolean parse(ADCData.Table paramTable)
    {
      if (paramTable == null) {}
      do
      {
        do
        {
          return false;
          this.enabled = paramTable.get_Logical("enabled");
          if (!this.enabled) {
            return true;
          }
          this.uuid = paramTable.get_String("uuid");
          this.ad_id = paramTable.get_Integer("ad_id");
          this.ad_campaign_id = paramTable.get_Integer("ad_campaign_id");
          this.dissolve = paramTable.get_Logical("dissolve");
          this.enable_in_app_store = paramTable.get_Logical("enable_in_app_store");
          this.dissolve_delay = paramTable.get_Real("dissolve_delay");
          this._static = new ADCManifest.StaticCompanionAdInfo();
        } while (!this._static.parse(paramTable.get_Table("static")));
        this.html5 = new ADCManifest.HTML5CompanionAdInfo();
      } while (!this.html5.parse(paramTable.get_Table("html5")));
      return true;
    }
  }
  
  static class HTML5CompanionAdInfo
  {
    String background_color;
    ADCManifest.ImageInfo background_logo;
    ADCManifest.ButtonInfo close;
    boolean enabled;
    String html5_tag;
    boolean load_spinner_enabled;
    double load_timeout;
    boolean load_timeout_enabled;
    ADCManifest.MRAIDJS mraid_js;
    ADCManifest.ButtonInfo replay;
    
    void cache_media()
    {
      if (!this.enabled) {}
      do
      {
        return;
        if (this.mraid_js != null) {
          this.mraid_js.cache_media();
        }
        if (this.background_logo != null) {
          this.background_logo.cache_media();
        }
        if (this.replay != null) {
          this.replay.cache_media();
        }
      } while (this.close == null);
      this.close.cache_media();
    }
    
    boolean is_ready()
    {
      boolean bool2;
      if (!ADCNetwork.connected())
      {
        ADC.error_code = 8;
        bool2 = ADCLog.info.fail("Ad not ready due to no network connection.");
      }
      boolean bool6;
      do
      {
        boolean bool5;
        do
        {
          boolean bool4;
          do
          {
            boolean bool3;
            do
            {
              boolean bool1;
              do
              {
                return bool2;
                bool1 = this.enabled;
                bool2 = false;
              } while (!bool1);
              bool3 = this.mraid_js.is_ready();
              bool2 = false;
            } while (!bool3);
            bool4 = this.background_logo.is_ready();
            bool2 = false;
          } while (!bool4);
          bool5 = this.replay.is_ready();
          bool2 = false;
        } while (!bool5);
        bool6 = this.close.is_ready();
        bool2 = false;
      } while (!bool6);
      return true;
    }
    
    boolean parse(ADCData.Table paramTable)
    {
      if (paramTable == null) {}
      do
      {
        do
        {
          do
          {
            do
            {
              return false;
              this.enabled = paramTable.get_Logical("enabled");
              this.load_timeout = paramTable.get_Real("load_timeout");
              this.load_timeout_enabled = paramTable.get_Logical("load_timeout_enabled");
              this.load_spinner_enabled = paramTable.get_Logical("load_spinner_enabled");
              this.background_color = paramTable.get_String("background_color");
              this.html5_tag = paramTable.get_String("html5_tag");
              this.mraid_js = new ADCManifest.MRAIDJS();
            } while (!this.mraid_js.parse(paramTable.get_Table("mraid_js")));
            this.background_logo = new ADCManifest.ImageInfo();
          } while (!this.background_logo.parse(paramTable.get_Table("background_logo")));
          this.replay = new ADCManifest.ButtonInfo();
        } while (!this.replay.parse(paramTable.get_Table("replay")));
        this.close = new ADCManifest.ButtonInfo();
      } while (!this.close.parse(paramTable.get_Table("close")));
      return true;
    }
  }
  
  static class ImageInfo
  {
    boolean enabled;
    int height;
    String image;
    String image_last_modified;
    int scale;
    int width;
    
    void cache_media()
    {
      ADC.controller.media_manager.cache(this.image, this.image_last_modified);
    }
    
    boolean is_ready()
    {
      if (!this.enabled) {
        return true;
      }
      return ADC.controller.media_manager.is_cached(this.image);
    }
    
    boolean parse(ADCData.Table paramTable)
    {
      boolean bool = true;
      if (paramTable == null) {
        bool = false;
      }
      do
      {
        return bool;
        this.enabled = paramTable.get_Logical("enabled", bool);
        this.width = paramTable.get_Integer("width");
        this.height = paramTable.get_Integer("height");
        this.scale = paramTable.get_Integer("scale");
        this.image = paramTable.get_String("image");
        this.image_last_modified = paramTable.get_String("image_last_modified");
      } while (!this.image_last_modified.equals(""));
      this.image_last_modified = paramTable.get_String("last_modified");
      return bool;
    }
  }
  
  static class InAppBrowser
  {
    ADCManifest.ButtonInfo back;
    String background_bar_image;
    String background_bar_image_last_modified;
    String background_color;
    String background_tile_image;
    String background_tile_image_last_modified;
    ADCManifest.ButtonInfo close;
    ADCManifest.ButtonInfo forward;
    ADCManifest.ImageInfo logo;
    ADCManifest.ButtonInfo reload;
    ADCManifest.ButtonInfo stop;
    String tiny_glow_image;
    String tiny_glow_image_last_modified;
    
    void cache_media()
    {
      ADC.controller.media_manager.cache(this.tiny_glow_image, this.tiny_glow_image_last_modified);
      ADC.controller.media_manager.cache(this.background_bar_image, this.background_bar_image_last_modified);
      ADC.controller.media_manager.cache(this.background_tile_image, this.background_tile_image_last_modified);
      this.logo.cache_media();
      this.stop.cache_media();
      this.back.cache_media();
      this.close.cache_media();
      this.forward.cache_media();
      this.reload.cache_media();
    }
    
    boolean is_ready()
    {
      if (!ADC.controller.media_manager.is_cached(this.tiny_glow_image)) {}
      while ((!ADC.controller.media_manager.is_cached(this.background_bar_image)) || (!ADC.controller.media_manager.is_cached(this.background_tile_image)) || (!this.logo.is_ready()) || (!this.stop.is_ready()) || (!this.back.is_ready()) || (!this.close.is_ready()) || (!this.forward.is_ready()) || (!this.reload.is_ready())) {
        return false;
      }
      return true;
    }
    
    boolean parse(ADCData.Table paramTable)
    {
      if (paramTable == null) {}
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                do
                {
                  do
                  {
                    return false;
                    this.tiny_glow_image = paramTable.get_String("tiny_glow_image");
                    this.tiny_glow_image_last_modified = paramTable.get_String("tiny_glow_image_last_modified;");
                    this.background_bar_image = paramTable.get_String("background_bar_image");
                    this.background_bar_image_last_modified = paramTable.get_String("background_bar_image_last_modified");
                    this.background_tile_image = paramTable.get_String("background_tile_image");
                    this.background_tile_image_last_modified = paramTable.get_String("background_tile_image_last_modified");
                    this.background_color = paramTable.get_String("background_color");
                    this.logo = new ADCManifest.ImageInfo();
                  } while (!this.logo.parse(paramTable.get_Table("logo")));
                  this.logo = new ADCManifest.ImageInfo();
                } while (!this.logo.parse(paramTable.get_Table("logo")));
                this.stop = new ADCManifest.ButtonInfo();
              } while (!this.stop.parse(paramTable.get_Table("stop")));
              this.back = new ADCManifest.ButtonInfo();
            } while (!this.back.parse(paramTable.get_Table("back")));
            this.close = new ADCManifest.ButtonInfo();
          } while (!this.close.parse(paramTable.get_Table("close")));
          this.forward = new ADCManifest.ButtonInfo();
        } while (!this.forward.parse(paramTable.get_Table("forward")));
        this.reload = new ADCManifest.ButtonInfo();
      } while (!this.reload.parse(paramTable.get_Table("reload")));
      return true;
    }
  }
  
  static class Limits
  {
    int custom_play_cap;
    int custom_play_cap_period;
    int daily_play_cap;
    int monthly_play_cap;
    int total_play_cap;
    int volatile_expiration;
    int volatile_play_cap;
    int weekly_play_cap;
    
    boolean parse(ADCData.Table paramTable)
    {
      if (paramTable == null) {
        return false;
      }
      this.daily_play_cap = paramTable.get_Integer("daily_play_cap");
      this.custom_play_cap = paramTable.get_Integer("custom_play_cap");
      this.custom_play_cap_period = paramTable.get_Integer("custom_play_cap_period");
      this.total_play_cap = paramTable.get_Integer("total_play_cap");
      this.monthly_play_cap = paramTable.get_Integer("monthly_play_cap");
      this.weekly_play_cap = paramTable.get_Integer("weekly_play_cap");
      this.volatile_expiration = paramTable.get_Integer("volatile_expiration");
      this.volatile_play_cap = paramTable.get_Integer("volatile_play_cap");
      return true;
    }
  }
  
  static class MRAIDJS
  {
    boolean enabled;
    String last_modified;
    String url;
    
    void cache_media()
    {
      ADC.controller.media_manager.cache(this.url, this.last_modified);
    }
    
    boolean is_ready()
    {
      if (!this.enabled) {}
      while (ADC.controller.media_manager.is_cached(this.url)) {
        return true;
      }
      return false;
    }
    
    boolean parse(ADCData.Table paramTable)
    {
      boolean bool = true;
      if (paramTable == null) {
        bool = false;
      }
      do
      {
        return bool;
        this.enabled = paramTable.get_Logical("enabled");
      } while (!this.enabled);
      this.url = paramTable.get_String("url");
      this.last_modified = paramTable.get_String("last_modified");
      return bool;
    }
  }
  
  static class NativeAd
  {
    String advertiser_name;
    String description;
    boolean enabled;
    ADCManifest.ImageInfo mute;
    boolean mute_enabled;
    ADCManifest.NativeOverlay native_overlay;
    String poster_image;
    String poster_image_last_modified;
    String thumb_image;
    String thumb_image_last_modified;
    String title;
    ADCManifest.ImageInfo unmute;
    
    void cache_media()
    {
      ADC.controller.media_manager.cache(this.poster_image, this.poster_image_last_modified);
      ADC.controller.media_manager.cache(this.thumb_image, this.thumb_image_last_modified);
      this.mute.cache_media();
      this.unmute.cache_media();
    }
    
    boolean is_ready()
    {
      if (!this.enabled) {}
      while ((!ADC.controller.media_manager.is_cached(this.poster_image)) || (!ADC.controller.media_manager.is_cached(this.thumb_image)) || (!this.mute.is_ready()) || (!this.unmute.is_ready())) {
        return false;
      }
      return true;
    }
    
    boolean parse(ADCData.Table paramTable)
    {
      if (paramTable == null) {}
      do
      {
        do
        {
          do
          {
            return false;
            this.enabled = paramTable.get_Logical("enabled");
            this.poster_image = paramTable.get_String("poster_image");
            this.advertiser_name = paramTable.get_String("advertiser_name");
            this.description = paramTable.get_String("description");
            this.title = paramTable.get_String("title");
            this.thumb_image = paramTable.get_String("thumb_image");
            this.poster_image_last_modified = paramTable.get_String("poster_image_last_modified");
            this.thumb_image_last_modified = paramTable.get_String("thumb_image_last_modified");
            this.mute = new ADCManifest.ImageInfo();
          } while (!this.mute.parse(paramTable.get_Table("mute")));
          this.mute_enabled = this.mute.enabled;
          this.unmute = new ADCManifest.ImageInfo();
        } while (!this.unmute.parse(paramTable.get_Table("unmute")));
        this.native_overlay = new ADCManifest.NativeOverlay();
      } while (!this.native_overlay.parse(paramTable.get_Table("overlay")));
      return true;
    }
  }
  
  static class NativeOverlay
  {
    String click_action;
    String click_action_type;
    boolean enabled;
    boolean in_app;
    String label;
    
    boolean parse(ADCData.Table paramTable)
    {
      if (paramTable == null) {
        return false;
      }
      this.enabled = paramTable.get_Logical("enabled");
      this.in_app = paramTable.get_Logical("in_app");
      this.click_action_type = paramTable.get_String("click_action_type");
      this.click_action = paramTable.get_String("click_action");
      this.label = paramTable.get_String("label");
      return true;
    }
  }
  
  static class PostPopupDialogInfo
  {
    int height;
    String image;
    String image_last_modified;
    String label;
    String label_fraction;
    String label_html;
    String label_reward;
    String label_rgba;
    String label_shadow_rgba;
    ADCManifest.ImageInfo logo;
    ADCManifest.ButtonInfo option_done;
    int scale;
    int width;
    
    void cache_media()
    {
      ADC.controller.media_manager.cache(this.image, this.image_last_modified);
      this.logo.cache_media();
      this.option_done.cache_media();
    }
    
    boolean is_ready()
    {
      if (!ADC.controller.media_manager.is_cached(this.image)) {}
      while ((!this.logo.is_ready()) || (!this.option_done.is_ready())) {
        return false;
      }
      return true;
    }
    
    boolean parse(ADCData.Table paramTable)
    {
      this.scale = paramTable.get_Integer("scale");
      this.label_reward = paramTable.get_String("label_reward");
      this.width = paramTable.get_Integer("width");
      this.height = paramTable.get_Integer("height");
      this.image = paramTable.get_String("image");
      this.image_last_modified = paramTable.get_String("image_last_modified");
      this.label = paramTable.get_String("label");
      this.label_rgba = paramTable.get_String("label_rgba");
      this.label_shadow_rgba = paramTable.get_String("label_shadow_rgba");
      this.label_fraction = paramTable.get_String("label_fraction");
      this.label_html = paramTable.get_String("label_html");
      this.logo = new ADCManifest.ImageInfo();
      if (!this.logo.parse(paramTable.get_Table("logo"))) {}
      do
      {
        return false;
        this.option_done = new ADCManifest.ButtonInfo();
      } while (!this.option_done.parse(paramTable.get_Table("option_done")));
      return true;
    }
  }
  
  static class PostPopupInfo
  {
    String background_image;
    String background_image_last_modified;
    ADCManifest.ImageInfo background_logo;
    ADCManifest.PostPopupDialogInfo dialog;
    
    void cache_media()
    {
      ADC.controller.media_manager.cache(this.background_image, this.background_image_last_modified);
      this.dialog.cache_media();
    }
    
    boolean is_ready()
    {
      if (!ADC.controller.media_manager.is_cached(this.background_image)) {}
      while ((!this.background_logo.is_ready()) || (!this.dialog.is_ready())) {
        return false;
      }
      return true;
    }
    
    boolean parse(ADCData.Table paramTable)
    {
      this.background_image = paramTable.get_String("background_image");
      this.background_image_last_modified = paramTable.get_String("background_image_last_modified");
      this.background_logo = new ADCManifest.ImageInfo();
      if (!this.background_logo.parse(paramTable.get_Table("background_logo"))) {}
      do
      {
        return false;
        this.dialog = new ADCManifest.PostPopupDialogInfo();
      } while (!this.dialog.parse(paramTable.get_Table("dialog")));
      return true;
    }
  }
  
  static class PrePopupDialogInfo
  {
    int height;
    String image;
    String image_last_modified;
    String label;
    String label_fraction;
    String label_html;
    String label_reward;
    String label_rgba;
    String label_shadow_rgba;
    ADCManifest.ImageInfo logo;
    ADCManifest.ButtonInfo option_no;
    ADCManifest.ButtonInfo option_yes;
    int scale;
    int width;
    
    void cache_media()
    {
      ADC.controller.media_manager.cache(this.image, this.image_last_modified);
      this.logo.cache_media();
      this.option_yes.cache_media();
      this.option_no.cache_media();
    }
    
    boolean is_ready()
    {
      if (!ADC.controller.media_manager.is_cached(this.image)) {}
      while ((!this.logo.is_ready()) || (!this.option_yes.is_ready())) {
        return false;
      }
      return true;
    }
    
    boolean parse(ADCData.Table paramTable)
    {
      this.scale = paramTable.get_Integer("scale");
      this.label_reward = paramTable.get_String("label_reward");
      this.width = paramTable.get_Integer("width");
      this.height = paramTable.get_Integer("height");
      this.image = paramTable.get_String("image");
      this.image_last_modified = paramTable.get_String("image_last_modified");
      this.label = paramTable.get_String("label");
      this.label_rgba = paramTable.get_String("label_rgba");
      this.label_shadow_rgba = paramTable.get_String("label_shadow_rgba");
      this.label_fraction = paramTable.get_String("label_fraction");
      this.label_html = paramTable.get_String("label_html");
      this.logo = new ADCManifest.ImageInfo();
      if (!this.logo.parse(paramTable.get_Table("logo"))) {}
      do
      {
        do
        {
          return false;
          this.option_yes = new ADCManifest.ButtonInfo();
        } while (!this.option_yes.parse(paramTable.get_Table("option_yes")));
        this.option_no = new ADCManifest.ButtonInfo();
      } while (!this.option_no.parse(paramTable.get_Table("option_no")));
      return true;
    }
  }
  
  static class PrePopupInfo
  {
    String background_image;
    String background_image_last_modified;
    ADCManifest.ImageInfo background_logo;
    ADCManifest.PrePopupDialogInfo dialog;
    
    void cache_media()
    {
      ADC.controller.media_manager.cache(this.background_image, this.background_image_last_modified);
      this.background_logo.cache_media();
      this.dialog.cache_media();
    }
    
    boolean is_ready()
    {
      if (!ADC.controller.media_manager.is_cached(this.background_image)) {}
      while ((!this.background_logo.is_ready()) || (!this.dialog.is_ready())) {
        return false;
      }
      return true;
    }
    
    boolean parse(ADCData.Table paramTable)
    {
      this.background_image = paramTable.get_String("background_image");
      this.background_image_last_modified = paramTable.get_String("background_image_last_modified");
      this.background_logo = new ADCManifest.ImageInfo();
      if (!this.background_logo.parse(paramTable.get_Table("background_logo"))) {}
      do
      {
        return false;
        this.dialog = new ADCManifest.PrePopupDialogInfo();
      } while (!this.dialog.parse(paramTable.get_Table("dialog")));
      return true;
    }
  }
  
  static class StaticCompanionAdInfo
  {
    ADCManifest.ButtonInfo _continue;
    String background_image;
    String background_image_last_modified;
    ADCManifest.ButtonInfo download;
    boolean enabled;
    int height;
    ADCManifest.ButtonInfo info;
    ADCManifest.ButtonInfo replay;
    int width;
    
    void cache_media()
    {
      if (!this.enabled) {
        return;
      }
      ADC.controller.media_manager.cache(this.background_image, this.background_image_last_modified);
      this.replay.cache_media();
      this._continue.cache_media();
      this.download.cache_media();
      this.info.cache_media();
    }
    
    boolean is_ready()
    {
      if (!this.enabled) {}
      do
      {
        return true;
        if (!ADC.controller.media_manager.is_cached(this.background_image)) {
          return false;
        }
        if (!this.replay.is_ready()) {
          return false;
        }
        if (!this._continue.is_ready()) {
          return false;
        }
        if (!this.download.is_ready()) {
          return false;
        }
      } while (this.info.is_ready());
      return false;
    }
    
    boolean parse(ADCData.Table paramTable)
    {
      if (paramTable == null) {}
      do
      {
        do
        {
          do
          {
            do
            {
              return false;
              this.enabled = paramTable.get_Logical("enabled");
              if (!this.enabled) {
                return true;
              }
              this.width = paramTable.get_Integer("width");
              this.height = paramTable.get_Integer("height");
              this.background_image = paramTable.get_String("background_image");
              this.background_image_last_modified = paramTable.get_String("background_image_last_modified");
              if (ADC.force_static_url != null) {
                this.background_image = ADC.force_static_url;
              }
              this.replay = new ADCManifest.ButtonInfo();
            } while (!this.replay.parse(paramTable.get_Table("replay")));
            this._continue = new ADCManifest.ButtonInfo();
          } while (!this._continue.parse(paramTable.get_Table("continue")));
          this.download = new ADCManifest.ButtonInfo();
        } while (!this.download.parse(paramTable.get_Table("download")));
        this.info = new ADCManifest.ButtonInfo();
      } while (!this.info.parse(paramTable.get_Table("info")));
      return true;
    }
  }
  
  static class ThirdPartyAppTracking
  {
    ArrayList<String> install = new ArrayList();
    HashMap<String, ArrayList<String>> lookup = new HashMap();
    ArrayList<String> session_start = new ArrayList();
    ArrayList<String> update = new ArrayList();
    
    boolean parse(ADCData.Table paramTable)
    {
      if (paramTable == null) {}
      ArrayList localArrayList3;
      do
      {
        ArrayList localArrayList2;
        do
        {
          ArrayList localArrayList1;
          do
          {
            return false;
            localArrayList1 = paramTable.get_StringList("update");
            this.update = localArrayList1;
          } while (localArrayList1 == null);
          localArrayList2 = paramTable.get_StringList("install");
          this.install = localArrayList2;
        } while (localArrayList2 == null);
        localArrayList3 = paramTable.get_StringList("session_start");
        this.session_start = localArrayList3;
      } while (localArrayList3 == null);
      this.lookup.put("update", this.update);
      this.lookup.put("install", this.install);
      this.lookup.put("session_start", this.session_start);
      return true;
    }
  }
  
  static class ThirdPartyTracking
  {
    ArrayList<String> _continue = new ArrayList();
    ArrayList<String> cancel = new ArrayList();
    ArrayList<String> card_dissolved = new ArrayList();
    ArrayList<String> card_shown = new ArrayList();
    ArrayList<String> complete = new ArrayList();
    ArrayList<String> download = new ArrayList();
    ArrayList<String> first_quartile = new ArrayList();
    ArrayList<String> html5_interaction = new ArrayList();
    ArrayList<String> in_video_engagement = new ArrayList();
    ArrayList<String> info = new ArrayList();
    HashMap<String, ArrayList<String>> lookup = new HashMap();
    ArrayList<String> midpoint = new ArrayList();
    ArrayList<String> native_complete = new ArrayList();
    ArrayList<String> native_first_quartile = new ArrayList();
    ArrayList<String> native_midpoint = new ArrayList();
    ArrayList<String> native_overlay_click = new ArrayList();
    ArrayList<String> native_start = new ArrayList();
    ArrayList<String> native_third_quartile = new ArrayList();
    ArrayList<String> replay = new ArrayList();
    ArrayList<String> reward_v4vc = new ArrayList();
    ArrayList<String> skip = new ArrayList();
    ArrayList<String> sound_mute = new ArrayList();
    ArrayList<String> sound_unmute = new ArrayList();
    ArrayList<String> start = new ArrayList();
    ArrayList<String> third_quartile = new ArrayList();
    ArrayList<String> video_expanded = new ArrayList();
    ArrayList<String> video_paused = new ArrayList();
    ArrayList<String> video_resumed = new ArrayList();
    
    boolean parse(ADCData.Table paramTable)
    {
      if (paramTable == null) {
        return false;
      }
      this.replay = paramTable.get_StringList("replay");
      this.card_shown = paramTable.get_StringList("card_shown");
      this.html5_interaction = paramTable.get_StringList("html5_interaction");
      this.cancel = paramTable.get_StringList("cancel");
      this.download = paramTable.get_StringList("download");
      this.skip = paramTable.get_StringList("skip");
      this.info = paramTable.get_StringList("info");
      this.midpoint = paramTable.get_StringList("midpoint");
      this.card_dissolved = paramTable.get_StringList("card_dissolved");
      this.start = paramTable.get_StringList("start");
      this.third_quartile = paramTable.get_StringList("third_quartile");
      this.complete = paramTable.get_StringList("complete");
      this._continue = paramTable.get_StringList("continue");
      this.in_video_engagement = paramTable.get_StringList("in_video_engagement");
      this.reward_v4vc = paramTable.get_StringList("reward_v4vc");
      this.first_quartile = paramTable.get_StringList("first_quartile");
      this.video_expanded = paramTable.get_StringList("video_expanded");
      this.sound_mute = paramTable.get_StringList("sound_mute");
      this.sound_unmute = paramTable.get_StringList("sound_unmute");
      this.video_paused = paramTable.get_StringList("video_paused");
      this.video_resumed = paramTable.get_StringList("video_resumed");
      this.native_start = paramTable.get_StringList("native_start");
      this.native_first_quartile = paramTable.get_StringList("native_first_quartile");
      this.native_midpoint = paramTable.get_StringList("native_midpoint");
      this.native_third_quartile = paramTable.get_StringList("native_third_quartile");
      this.native_complete = paramTable.get_StringList("native_complete");
      this.native_overlay_click = paramTable.get_StringList("native_overlay_click");
      this.lookup.put("replay", this.replay);
      this.lookup.put("card_shown", this.card_shown);
      this.lookup.put("html5_interaction", this.html5_interaction);
      this.lookup.put("cancel", this.cancel);
      this.lookup.put("download", this.download);
      this.lookup.put("skip", this.skip);
      this.lookup.put("info", this.info);
      this.lookup.put("midpoint", this.midpoint);
      this.lookup.put("card_dissolved", this.card_dissolved);
      this.lookup.put("start", this.start);
      this.lookup.put("third_quartile", this.third_quartile);
      this.lookup.put("complete", this.complete);
      this.lookup.put("continue", this._continue);
      this.lookup.put("in_video_engagement", this.in_video_engagement);
      this.lookup.put("reward_v4vc", this.reward_v4vc);
      this.lookup.put("first_quartile", this.first_quartile);
      this.lookup.put("video_expanded", this.video_expanded);
      this.lookup.put("sound_mute", this.sound_mute);
      this.lookup.put("sound_unmute", this.sound_unmute);
      this.lookup.put("video_paused", this.video_paused);
      this.lookup.put("video_resumed", this.video_resumed);
      this.lookup.put("native_start", this.native_start);
      this.lookup.put("native_first_quartile", this.native_first_quartile);
      this.lookup.put("native_midpoint", this.native_midpoint);
      this.lookup.put("native_third_quartile", this.native_third_quartile);
      this.lookup.put("native_complete", this.native_complete);
      this.lookup.put("native_overlay_click", this.native_overlay_click);
      return true;
    }
  }
  
  static class V4VCLimits
  {
    int custom_play_cap;
    int custom_play_cap_period;
    int daily_play_cap;
    
    boolean parse(ADCData.Table paramTable)
    {
      if (paramTable == null) {
        return false;
      }
      this.daily_play_cap = paramTable.get_Integer("daily_play_cap");
      this.custom_play_cap = paramTable.get_Integer("custom_play_cap");
      this.custom_play_cap_period = paramTable.get_Integer("custom_play_cap_period");
      return true;
    }
  }
  
  static class Video
  {
    String audio_channels;
    String audio_codec;
    String audio_sample_rate;
    double duration;
    boolean enabled;
    int height;
    ADCManifest.ButtonInfo in_video_engagement;
    String last_modified;
    ADCManifest.ButtonInfo skip_video;
    String url;
    String video_codec;
    String video_frame_rate;
    int width;
    
    void cache_media()
    {
      ADC.controller.media_manager.cache(this.url, this.last_modified);
      this.in_video_engagement.cache_media();
      this.skip_video.cache_media();
    }
    
    String filepath()
    {
      return ADC.controller.media_manager.local_filepath(this.url);
    }
    
    boolean is_ready()
    {
      if (!this.enabled) {}
      do
      {
        return true;
        if (!ADC.controller.media_manager.is_cached(this.url)) {
          return false;
        }
        if (!this.skip_video.is_ready()) {
          return false;
        }
        if (!this.in_video_engagement.is_ready()) {
          return false;
        }
        if ((ADC.controller.ad_manager.app.view_network_pass_filter.equals("online")) && (!ADCNetwork.connected()))
        {
          ADC.error_code = 9;
          return ADCLog.info.fail("Video not ready due to VIEW_FILTER_ONLINE");
        }
        if ((ADC.controller.ad_manager.app.view_network_pass_filter.equals("wifi")) && (!ADCNetwork.using_wifi()))
        {
          ADC.error_code = 9;
          return ADCLog.info.fail("Video not ready due to VIEW_FILTER_WIFI");
        }
        if ((ADC.controller.ad_manager.app.view_network_pass_filter.equals("cell")) && (!ADCNetwork.using_mobile()))
        {
          ADC.error_code = 9;
          return ADCLog.info.fail("Video not ready due to VIEW_FILTER_CELL");
        }
      } while ((!ADC.controller.ad_manager.app.view_network_pass_filter.equals("offline")) || (!ADCNetwork.connected()));
      ADC.error_code = 9;
      return ADCLog.info.fail("Video not ready due to VIEW_FILTER_OFFLINE");
    }
    
    boolean parse(ADCData.Table paramTable)
    {
      if (paramTable == null) {}
      do
      {
        do
        {
          return false;
          this.enabled = paramTable.get_Logical("enabled");
          if (!this.enabled) {
            return true;
          }
          this.width = paramTable.get_Integer("width");
          this.height = paramTable.get_Integer("height");
          this.url = paramTable.get_String("url");
          this.last_modified = paramTable.get_String("last_modified");
          this.video_frame_rate = paramTable.get_String("video_frame_rate");
          this.audio_channels = paramTable.get_String("audio_channels");
          this.audio_codec = paramTable.get_String("audio_codec");
          this.audio_sample_rate = paramTable.get_String("audio_sample_rate");
          this.video_codec = paramTable.get_String("video_codec");
          this.duration = paramTable.get_Real("duration");
          this.skip_video = new ADCManifest.ButtonInfo();
        } while (!this.skip_video.parse(paramTable.get_Table("skip_video")));
        this.in_video_engagement = new ADCManifest.ButtonInfo();
      } while (!this.in_video_engagement.parse(paramTable.get_Table("in_video_engagement")));
      return true;
    }
  }
  
  static class Zone
  {
    boolean active;
    ADCManifest.Ads ads;
    int daily_play_cap;
    boolean enabled;
    int play_interval;
    ArrayList<String> play_order;
    int session_play_cap;
    ADCZoneState state;
    String uuid;
    ADCManifest.ZoneV4VC v4vc;
    ADCManifest.ZoneTracking zone_tracking;
    
    void advance_play_index()
    {
      if (this.play_order.size() > 0) {
        this.state.play_order_index = ((1 + this.state.play_order_index) % this.play_order.size());
      }
    }
    
    void cache_media()
    {
      if ((!this.enabled) || (!this.active)) {}
      for (;;)
      {
        return;
        for (int i = 0; i < this.ads.count(); i++) {
          this.ads.get(i).cache_media();
        }
      }
    }
    
    boolean check_for_skip_due_to_interval()
    {
      if (this.play_interval <= 1) {}
      int i;
      do
      {
        return false;
        ADC.controller.zone_state_manager.modified = true;
        ADCZoneState localADCZoneState = this.state;
        i = localADCZoneState.skipped_plays;
        localADCZoneState.skipped_plays = (i + 1);
      } while (i == 0);
      if (this.state.skipped_plays >= this.play_interval) {
        this.state.skipped_plays = 0;
      }
      return true;
    }
    
    int combine_results(int paramInt1, int paramInt2)
    {
      if (paramInt2 <= 0) {
        paramInt2 = 0;
      }
      while (paramInt1 == -1) {
        return paramInt2;
      }
      if (paramInt1 < paramInt2) {}
      for (;;)
      {
        return paramInt1;
        paramInt1 = paramInt2;
      }
    }
    
    ADCManifest.Ad current_ad()
    {
      if (this.play_order.size() > 0) {
        return this.ads.find((String)this.play_order.get(this.state.play_order_index % this.play_order.size()));
      }
      return null;
    }
    
    ADCManifest.Ad current_native_ad()
    {
      if (this.play_order.size() > 0) {
        return this.ads.find_native_ad(this.state.play_order_index % this.play_order.size());
      }
      return null;
    }
    
    int get_available_plays()
    {
      try
      {
        int i = get_available_plays(current_ad());
        return i;
      }
      finally
      {
        localObject = finally;
        throw localObject;
      }
    }
    
    /* Error */
    int get_available_plays(ADCManifest.Ad paramAd)
    {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: getstatic 74	com/jirbo/adcolony/ADC:controller	Lcom/jirbo/adcolony/ADCController;
      //   5: getfield 115	com/jirbo/adcolony/ADCController:play_history	Lcom/jirbo/adcolony/ADCPlayHistory;
      //   8: aload_0
      //   9: getfield 117	com/jirbo/adcolony/ADCManifest$Zone:uuid	Ljava/lang/String;
      //   12: invokevirtual 123	com/jirbo/adcolony/ADCPlayHistory:zone_daily_play_count	(Ljava/lang/String;)I
      //   15: istore_3
      //   16: iconst_m1
      //   17: istore 4
      //   19: aload_1
      //   20: getfield 127	com/jirbo/adcolony/ADCManifest$Ad:limits	Lcom/jirbo/adcolony/ADCManifest$Limits;
      //   23: getfield 132	com/jirbo/adcolony/ADCManifest$Limits:volatile_expiration	I
      //   26: istore 5
      //   28: iload 5
      //   30: ifeq +57 -> 87
      //   33: getstatic 136	com/jirbo/adcolony/ADC:last_config_ms	J
      //   36: lconst_0
      //   37: lcmp
      //   38: ifeq +49 -> 87
      //   41: invokestatic 142	java/lang/System:currentTimeMillis	()J
      //   44: getstatic 136	com/jirbo/adcolony/ADC:last_config_ms	J
      //   47: lsub
      //   48: ldc2_w 143
      //   51: ldiv
      //   52: iload 5
      //   54: i2l
      //   55: lcmp
      //   56: iflt +31 -> 87
      //   59: aload_0
      //   60: invokevirtual 147	com/jirbo/adcolony/ADCManifest$Zone:try_refresh	()V
      //   63: bipush 7
      //   65: putstatic 150	com/jirbo/adcolony/ADC:error_code	I
      //   68: getstatic 156	com/jirbo/adcolony/ADCLog:info	Lcom/jirbo/adcolony/ADCLog;
      //   71: ldc 158
      //   73: invokevirtual 161	com/jirbo/adcolony/ADCLog:int_fail	(Ljava/lang/String;)I
      //   76: istore 21
      //   78: iload 21
      //   80: istore 8
      //   82: aload_0
      //   83: monitorexit
      //   84: iload 8
      //   86: ireturn
      //   87: aload_1
      //   88: getfield 127	com/jirbo/adcolony/ADCManifest$Ad:limits	Lcom/jirbo/adcolony/ADCManifest$Limits;
      //   91: getfield 164	com/jirbo/adcolony/ADCManifest$Limits:volatile_play_cap	I
      //   94: istore 6
      //   96: iload 6
      //   98: ifle +39 -> 137
      //   101: aload_0
      //   102: iload 4
      //   104: iload 6
      //   106: getstatic 74	com/jirbo/adcolony/ADC:controller	Lcom/jirbo/adcolony/ADCController;
      //   109: getfield 115	com/jirbo/adcolony/ADCController:play_history	Lcom/jirbo/adcolony/ADCPlayHistory;
      //   112: aload_1
      //   113: getfield 167	com/jirbo/adcolony/ADCManifest$Ad:ad_id	I
      //   116: invokestatic 142	java/lang/System:currentTimeMillis	()J
      //   119: getstatic 136	com/jirbo/adcolony/ADC:last_config_ms	J
      //   122: lsub
      //   123: ldc2_w 143
      //   126: ldiv
      //   127: l2d
      //   128: invokevirtual 171	com/jirbo/adcolony/ADCPlayHistory:ad_interval_play_count	(ID)I
      //   131: isub
      //   132: invokevirtual 173	com/jirbo/adcolony/ADCManifest$Zone:combine_results	(II)I
      //   135: istore 4
      //   137: iload 4
      //   139: ifne +30 -> 169
      //   142: iload 6
      //   144: ifeq +25 -> 169
      //   147: aload_0
      //   148: invokevirtual 147	com/jirbo/adcolony/ADCManifest$Zone:try_refresh	()V
      //   151: bipush 7
      //   153: putstatic 150	com/jirbo/adcolony/ADC:error_code	I
      //   156: getstatic 156	com/jirbo/adcolony/ADCLog:info	Lcom/jirbo/adcolony/ADCLog;
      //   159: ldc 175
      //   161: invokevirtual 161	com/jirbo/adcolony/ADCLog:int_fail	(Ljava/lang/String;)I
      //   164: istore 8
      //   166: goto -84 -> 82
      //   169: aload_0
      //   170: getfield 177	com/jirbo/adcolony/ADCManifest$Zone:daily_play_cap	I
      //   173: istore 7
      //   175: iload 7
      //   177: ifle +15 -> 192
      //   180: aload_0
      //   181: iload 4
      //   183: iload 7
      //   185: iload_3
      //   186: isub
      //   187: invokevirtual 173	com/jirbo/adcolony/ADCManifest$Zone:combine_results	(II)I
      //   190: istore 4
      //   192: iload 4
      //   194: ifne +40 -> 234
      //   197: iconst_5
      //   198: putstatic 150	com/jirbo/adcolony/ADC:error_code	I
      //   201: getstatic 156	com/jirbo/adcolony/ADCLog:info	Lcom/jirbo/adcolony/ADCLog;
      //   204: new 179	java/lang/StringBuilder
      //   207: dup
      //   208: invokespecial 180	java/lang/StringBuilder:<init>	()V
      //   211: ldc 182
      //   213: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   216: aload_0
      //   217: getfield 117	com/jirbo/adcolony/ADCManifest$Zone:uuid	Ljava/lang/String;
      //   220: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   223: invokevirtual 190	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   226: invokevirtual 161	com/jirbo/adcolony/ADCLog:int_fail	(Ljava/lang/String;)I
      //   229: istore 8
      //   231: goto -149 -> 82
      //   234: aload_0
      //   235: getfield 192	com/jirbo/adcolony/ADCManifest$Zone:session_play_cap	I
      //   238: istore 9
      //   240: iload 9
      //   242: ifle +27 -> 269
      //   245: aload_0
      //   246: iload 4
      //   248: iload 9
      //   250: getstatic 74	com/jirbo/adcolony/ADC:controller	Lcom/jirbo/adcolony/ADCController;
      //   253: getfield 115	com/jirbo/adcolony/ADCController:play_history	Lcom/jirbo/adcolony/ADCPlayHistory;
      //   256: aload_0
      //   257: getfield 117	com/jirbo/adcolony/ADCManifest$Zone:uuid	Ljava/lang/String;
      //   260: invokevirtual 195	com/jirbo/adcolony/ADCPlayHistory:zone_session_play_count	(Ljava/lang/String;)I
      //   263: isub
      //   264: invokevirtual 173	com/jirbo/adcolony/ADCManifest$Zone:combine_results	(II)I
      //   267: istore 4
      //   269: iload 4
      //   271: ifne +40 -> 311
      //   274: iconst_3
      //   275: putstatic 150	com/jirbo/adcolony/ADC:error_code	I
      //   278: getstatic 156	com/jirbo/adcolony/ADCLog:info	Lcom/jirbo/adcolony/ADCLog;
      //   281: new 179	java/lang/StringBuilder
      //   284: dup
      //   285: invokespecial 180	java/lang/StringBuilder:<init>	()V
      //   288: ldc 197
      //   290: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   293: aload_0
      //   294: getfield 117	com/jirbo/adcolony/ADCManifest$Zone:uuid	Ljava/lang/String;
      //   297: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   300: invokevirtual 190	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   303: invokevirtual 161	com/jirbo/adcolony/ADCLog:int_fail	(Ljava/lang/String;)I
      //   306: istore 8
      //   308: goto -226 -> 82
      //   311: aload_0
      //   312: getfield 199	com/jirbo/adcolony/ADCManifest$Zone:v4vc	Lcom/jirbo/adcolony/ADCManifest$ZoneV4VC;
      //   315: getfield 202	com/jirbo/adcolony/ADCManifest$ZoneV4VC:enabled	Z
      //   318: ifeq +132 -> 450
      //   321: aload_0
      //   322: getfield 199	com/jirbo/adcolony/ADCManifest$Zone:v4vc	Lcom/jirbo/adcolony/ADCManifest$ZoneV4VC;
      //   325: getfield 205	com/jirbo/adcolony/ADCManifest$ZoneV4VC:limits	Lcom/jirbo/adcolony/ADCManifest$V4VCLimits;
      //   328: getfield 208	com/jirbo/adcolony/ADCManifest$V4VCLimits:daily_play_cap	I
      //   331: istore 18
      //   333: iload 18
      //   335: ifle +15 -> 350
      //   338: aload_0
      //   339: iload 4
      //   341: iload 18
      //   343: iload_3
      //   344: isub
      //   345: invokevirtual 173	com/jirbo/adcolony/ADCManifest$Zone:combine_results	(II)I
      //   348: istore 4
      //   350: iload 4
      //   352: ifne +20 -> 372
      //   355: iconst_4
      //   356: putstatic 150	com/jirbo/adcolony/ADC:error_code	I
      //   359: getstatic 156	com/jirbo/adcolony/ADCLog:info	Lcom/jirbo/adcolony/ADCLog;
      //   362: ldc 210
      //   364: invokevirtual 161	com/jirbo/adcolony/ADCLog:int_fail	(Ljava/lang/String;)I
      //   367: istore 8
      //   369: goto -287 -> 82
      //   372: aload_0
      //   373: getfield 199	com/jirbo/adcolony/ADCManifest$Zone:v4vc	Lcom/jirbo/adcolony/ADCManifest$ZoneV4VC;
      //   376: getfield 205	com/jirbo/adcolony/ADCManifest$ZoneV4VC:limits	Lcom/jirbo/adcolony/ADCManifest$V4VCLimits;
      //   379: getfield 213	com/jirbo/adcolony/ADCManifest$V4VCLimits:custom_play_cap_period	I
      //   382: istore 19
      //   384: aload_0
      //   385: getfield 199	com/jirbo/adcolony/ADCManifest$Zone:v4vc	Lcom/jirbo/adcolony/ADCManifest$ZoneV4VC;
      //   388: getfield 205	com/jirbo/adcolony/ADCManifest$ZoneV4VC:limits	Lcom/jirbo/adcolony/ADCManifest$V4VCLimits;
      //   391: getfield 216	com/jirbo/adcolony/ADCManifest$V4VCLimits:custom_play_cap	I
      //   394: istore 20
      //   396: iload 20
      //   398: ifle +30 -> 428
      //   401: aload_0
      //   402: iload 4
      //   404: iload 20
      //   406: getstatic 74	com/jirbo/adcolony/ADC:controller	Lcom/jirbo/adcolony/ADCController;
      //   409: getfield 115	com/jirbo/adcolony/ADCController:play_history	Lcom/jirbo/adcolony/ADCPlayHistory;
      //   412: aload_0
      //   413: getfield 117	com/jirbo/adcolony/ADCManifest$Zone:uuid	Ljava/lang/String;
      //   416: iload 19
      //   418: i2d
      //   419: invokevirtual 220	com/jirbo/adcolony/ADCPlayHistory:zone_interval_play_count	(Ljava/lang/String;D)I
      //   422: isub
      //   423: invokevirtual 173	com/jirbo/adcolony/ADCManifest$Zone:combine_results	(II)I
      //   426: istore 4
      //   428: iload 4
      //   430: ifne +20 -> 450
      //   433: iconst_4
      //   434: putstatic 150	com/jirbo/adcolony/ADC:error_code	I
      //   437: getstatic 156	com/jirbo/adcolony/ADCLog:info	Lcom/jirbo/adcolony/ADCLog;
      //   440: ldc 222
      //   442: invokevirtual 161	com/jirbo/adcolony/ADCLog:int_fail	(Ljava/lang/String;)I
      //   445: istore 8
      //   447: goto -365 -> 82
      //   450: aload_1
      //   451: getfield 167	com/jirbo/adcolony/ADCManifest$Ad:ad_id	I
      //   454: istore 10
      //   456: aload_1
      //   457: getfield 127	com/jirbo/adcolony/ADCManifest$Ad:limits	Lcom/jirbo/adcolony/ADCManifest$Limits;
      //   460: getfield 223	com/jirbo/adcolony/ADCManifest$Limits:daily_play_cap	I
      //   463: istore 11
      //   465: iload 11
      //   467: ifle +25 -> 492
      //   470: aload_0
      //   471: iload 4
      //   473: iload 11
      //   475: getstatic 74	com/jirbo/adcolony/ADC:controller	Lcom/jirbo/adcolony/ADCController;
      //   478: getfield 115	com/jirbo/adcolony/ADCController:play_history	Lcom/jirbo/adcolony/ADCPlayHistory;
      //   481: iload 10
      //   483: invokevirtual 227	com/jirbo/adcolony/ADCPlayHistory:ad_daily_play_count	(I)I
      //   486: isub
      //   487: invokevirtual 173	com/jirbo/adcolony/ADCManifest$Zone:combine_results	(II)I
      //   490: istore 4
      //   492: iload 4
      //   494: ifne +25 -> 519
      //   497: bipush 7
      //   499: putstatic 150	com/jirbo/adcolony/ADC:error_code	I
      //   502: aload_0
      //   503: invokevirtual 147	com/jirbo/adcolony/ADCManifest$Zone:try_refresh	()V
      //   506: getstatic 156	com/jirbo/adcolony/ADCLog:info	Lcom/jirbo/adcolony/ADCLog;
      //   509: ldc 229
      //   511: invokevirtual 161	com/jirbo/adcolony/ADCLog:int_fail	(Ljava/lang/String;)I
      //   514: istore 8
      //   516: goto -434 -> 82
      //   519: aload_1
      //   520: getfield 127	com/jirbo/adcolony/ADCManifest$Ad:limits	Lcom/jirbo/adcolony/ADCManifest$Limits;
      //   523: getfield 232	com/jirbo/adcolony/ADCManifest$Limits:weekly_play_cap	I
      //   526: istore 12
      //   528: iload 12
      //   530: ifle +25 -> 555
      //   533: aload_0
      //   534: iload 4
      //   536: iload 12
      //   538: getstatic 74	com/jirbo/adcolony/ADC:controller	Lcom/jirbo/adcolony/ADCController;
      //   541: getfield 115	com/jirbo/adcolony/ADCController:play_history	Lcom/jirbo/adcolony/ADCPlayHistory;
      //   544: iload 10
      //   546: invokevirtual 235	com/jirbo/adcolony/ADCPlayHistory:ad_weekly_play_count	(I)I
      //   549: isub
      //   550: invokevirtual 173	com/jirbo/adcolony/ADCManifest$Zone:combine_results	(II)I
      //   553: istore 4
      //   555: iload 4
      //   557: ifne +25 -> 582
      //   560: bipush 7
      //   562: putstatic 150	com/jirbo/adcolony/ADC:error_code	I
      //   565: aload_0
      //   566: invokevirtual 147	com/jirbo/adcolony/ADCManifest$Zone:try_refresh	()V
      //   569: getstatic 156	com/jirbo/adcolony/ADCLog:info	Lcom/jirbo/adcolony/ADCLog;
      //   572: ldc 237
      //   574: invokevirtual 161	com/jirbo/adcolony/ADCLog:int_fail	(Ljava/lang/String;)I
      //   577: istore 8
      //   579: goto -497 -> 82
      //   582: aload_1
      //   583: getfield 127	com/jirbo/adcolony/ADCManifest$Ad:limits	Lcom/jirbo/adcolony/ADCManifest$Limits;
      //   586: getfield 240	com/jirbo/adcolony/ADCManifest$Limits:monthly_play_cap	I
      //   589: istore 13
      //   591: iload 13
      //   593: ifle +25 -> 618
      //   596: aload_0
      //   597: iload 4
      //   599: iload 13
      //   601: getstatic 74	com/jirbo/adcolony/ADC:controller	Lcom/jirbo/adcolony/ADCController;
      //   604: getfield 115	com/jirbo/adcolony/ADCController:play_history	Lcom/jirbo/adcolony/ADCPlayHistory;
      //   607: iload 10
      //   609: invokevirtual 243	com/jirbo/adcolony/ADCPlayHistory:ad_monthly_play_count	(I)I
      //   612: isub
      //   613: invokevirtual 173	com/jirbo/adcolony/ADCManifest$Zone:combine_results	(II)I
      //   616: istore 4
      //   618: iload 4
      //   620: ifne +25 -> 645
      //   623: bipush 7
      //   625: putstatic 150	com/jirbo/adcolony/ADC:error_code	I
      //   628: aload_0
      //   629: invokevirtual 147	com/jirbo/adcolony/ADCManifest$Zone:try_refresh	()V
      //   632: getstatic 156	com/jirbo/adcolony/ADCLog:info	Lcom/jirbo/adcolony/ADCLog;
      //   635: ldc 245
      //   637: invokevirtual 161	com/jirbo/adcolony/ADCLog:int_fail	(Ljava/lang/String;)I
      //   640: istore 8
      //   642: goto -560 -> 82
      //   645: aload_1
      //   646: getfield 127	com/jirbo/adcolony/ADCManifest$Ad:limits	Lcom/jirbo/adcolony/ADCManifest$Limits;
      //   649: getfield 248	com/jirbo/adcolony/ADCManifest$Limits:total_play_cap	I
      //   652: istore 14
      //   654: iload 14
      //   656: ifle +25 -> 681
      //   659: aload_0
      //   660: iload 4
      //   662: iload 14
      //   664: getstatic 74	com/jirbo/adcolony/ADC:controller	Lcom/jirbo/adcolony/ADCController;
      //   667: getfield 115	com/jirbo/adcolony/ADCController:play_history	Lcom/jirbo/adcolony/ADCPlayHistory;
      //   670: iload 10
      //   672: invokevirtual 251	com/jirbo/adcolony/ADCPlayHistory:ad_half_year_play_count	(I)I
      //   675: isub
      //   676: invokevirtual 173	com/jirbo/adcolony/ADCManifest$Zone:combine_results	(II)I
      //   679: istore 4
      //   681: iload 4
      //   683: ifne +25 -> 708
      //   686: bipush 7
      //   688: putstatic 150	com/jirbo/adcolony/ADC:error_code	I
      //   691: aload_0
      //   692: invokevirtual 147	com/jirbo/adcolony/ADCManifest$Zone:try_refresh	()V
      //   695: getstatic 156	com/jirbo/adcolony/ADCLog:info	Lcom/jirbo/adcolony/ADCLog;
      //   698: ldc 253
      //   700: invokevirtual 161	com/jirbo/adcolony/ADCLog:int_fail	(Ljava/lang/String;)I
      //   703: istore 8
      //   705: goto -623 -> 82
      //   708: aload_1
      //   709: getfield 127	com/jirbo/adcolony/ADCManifest$Ad:limits	Lcom/jirbo/adcolony/ADCManifest$Limits;
      //   712: getfield 254	com/jirbo/adcolony/ADCManifest$Limits:custom_play_cap_period	I
      //   715: istore 15
      //   717: aload_1
      //   718: getfield 127	com/jirbo/adcolony/ADCManifest$Ad:limits	Lcom/jirbo/adcolony/ADCManifest$Limits;
      //   721: getfield 255	com/jirbo/adcolony/ADCManifest$Limits:custom_play_cap	I
      //   724: istore 16
      //   726: iload 16
      //   728: ifle +32 -> 760
      //   731: aload_0
      //   732: iload 4
      //   734: iload 16
      //   736: getstatic 74	com/jirbo/adcolony/ADC:controller	Lcom/jirbo/adcolony/ADCController;
      //   739: getfield 115	com/jirbo/adcolony/ADCController:play_history	Lcom/jirbo/adcolony/ADCPlayHistory;
      //   742: iload 10
      //   744: iload 15
      //   746: i2d
      //   747: invokevirtual 171	com/jirbo/adcolony/ADCPlayHistory:ad_interval_play_count	(ID)I
      //   750: isub
      //   751: invokevirtual 173	com/jirbo/adcolony/ADCManifest$Zone:combine_results	(II)I
      //   754: istore 17
      //   756: iload 17
      //   758: istore 4
      //   760: iload 4
      //   762: istore 8
      //   764: goto -682 -> 82
      //   767: astore_2
      //   768: aload_0
      //   769: monitorexit
      //   770: aload_2
      //   771: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	772	0	this	Zone
      //   0	772	1	paramAd	ADCManifest.Ad
      //   767	4	2	localObject	Object
      //   15	330	3	i	int
      //   17	744	4	j	int
      //   26	27	5	k	int
      //   94	49	6	m	int
      //   173	14	7	n	int
      //   80	683	8	i1	int
      //   238	26	9	i2	int
      //   454	289	10	i3	int
      //   463	24	11	i4	int
      //   526	24	12	i5	int
      //   589	24	13	i6	int
      //   652	24	14	i7	int
      //   715	30	15	i8	int
      //   724	27	16	i9	int
      //   754	3	17	i10	int
      //   331	14	18	i11	int
      //   382	35	19	i12	int
      //   394	29	20	i13	int
      //   76	3	21	i14	int
      // Exception table:
      //   from	to	target	type
      //   2	16	767	finally
      //   19	28	767	finally
      //   33	78	767	finally
      //   87	96	767	finally
      //   101	137	767	finally
      //   147	166	767	finally
      //   169	175	767	finally
      //   180	192	767	finally
      //   197	231	767	finally
      //   234	240	767	finally
      //   245	269	767	finally
      //   274	308	767	finally
      //   311	333	767	finally
      //   338	350	767	finally
      //   355	369	767	finally
      //   372	396	767	finally
      //   401	428	767	finally
      //   433	447	767	finally
      //   450	465	767	finally
      //   470	492	767	finally
      //   497	516	767	finally
      //   519	528	767	finally
      //   533	555	767	finally
      //   560	579	767	finally
      //   582	591	767	finally
      //   596	618	767	finally
      //   623	642	767	finally
      //   645	654	767	finally
      //   659	681	767	finally
      //   686	705	767	finally
      //   708	726	767	finally
      //   731	756	767	finally
    }
    
    boolean is_ad_available()
    {
      return is_ad_available(false, true);
    }
    
    boolean is_ad_available(boolean paramBoolean1, boolean paramBoolean2)
    {
      int i = 1;
      if (!paramBoolean2) {
        i = is_ad_available_errorless(paramBoolean1);
      }
      Object localObject;
      do
      {
        return i;
        if ((!this.enabled) || (!this.active))
        {
          ADC.error_code = i;
          return ADCLog.info.fail("Ad is not ready to be played, as zone " + this.uuid + " is disabled or inactive.");
        }
        if ((this.ads.count() == 0) || (this.play_order.size() == 0))
        {
          ADC.error_code = 5;
          return ADCLog.info.fail("Ad is not ready to be played, as AdColony currently has no videos available to be played in zone " + this.uuid + ".");
        }
        int j = this.play_order.size();
        for (int k = 0;; k++)
        {
          localObject = null;
          if (k < j)
          {
            ADCManifest.Ad localAd = current_ad();
            if (localAd == null) {
              return ADCLog.info.fail("Ad is not ready to be played due to an unknown error.");
            }
            if (localAd.is_ready()) {
              localObject = localAd;
            }
          }
          else
          {
            if (localObject != null) {
              break;
            }
            ADC.error_code = 6;
            return ADCLog.info.fail("Ad is not ready to be played as required assets are still downloading or otherwise missing.");
          }
          advance_play_index();
        }
      } while (get_available_plays(localObject) != 0);
      return false;
    }
    
    boolean is_ad_available_errorless(boolean paramBoolean)
    {
      if (!paramBoolean) {
        ADC.has_ad_availability_changed();
      }
      if ((!this.enabled) || (!this.active)) {}
      while ((this.ads.count() == 0) || (this.play_order.size() == 0)) {
        return false;
      }
      int i = this.play_order.size();
      for (int j = 0;; j++)
      {
        Object localObject = null;
        if (j < i)
        {
          ADCManifest.Ad localAd = current_ad();
          if (localAd == null) {
            break;
          }
          if (!localAd.is_ready()) {
            break label100;
          }
          localObject = localAd;
        }
        if ((localObject == null) || (get_available_plays(localObject) == 0)) {
          break;
        }
        return true;
        label100:
        advance_play_index();
      }
    }
    
    boolean is_v4vc_zone()
    {
      return is_v4vc_zone(true);
    }
    
    boolean is_v4vc_zone(boolean paramBoolean)
    {
      int i = 1;
      if (!paramBoolean) {
        i = is_v4vc_zone_errorless();
      }
      do
      {
        return i;
        if ((!this.enabled) || (!this.active))
        {
          ADC.error_code = i;
          return ADCLog.info.fail("Ad is not ready, as zone " + this.uuid + " is disabled or inactive.");
        }
        if (this.ads.count() == 0)
        {
          ADC.error_code = 5;
          return ADCLog.info.fail("Ad is not ready, as there are currently no ads to play in zone " + this.uuid + ".");
        }
      } while (this.ads.first().v4vc.enabled);
      ADC.error_code = 15;
      return ADCLog.info.fail("Ad is not ready, as zone " + this.uuid + " is not V4VC enabled and must be played using an AdColonyVideoAd object.");
    }
    
    boolean is_v4vc_zone_errorless()
    {
      if ((!this.enabled) || (!this.active)) {}
      while ((this.ads.count() == 0) || (!this.ads.first().v4vc.enabled)) {
        return false;
      }
      return true;
    }
    
    boolean is_video_zone()
    {
      return is_video_zone(true);
    }
    
    boolean is_video_zone(boolean paramBoolean)
    {
      int i = 1;
      if (!paramBoolean) {
        i = is_video_zone_errorless();
      }
      do
      {
        return i;
        if ((!this.enabled) || (!this.active))
        {
          ADC.error_code = i;
          return ADCLog.info.fail("Ad is not ready, as zone " + this.uuid + " is disabled or inactive.");
        }
        if (this.ads.count() == 0)
        {
          ADC.error_code = 5;
          return ADCLog.info.fail("Ad is not ready, as there are currently no ads to play in zone " + this.uuid + ".");
        }
      } while (!this.ads.first().v4vc.enabled);
      ADC.error_code = 14;
      return ADCLog.info.fail("Ad is not ready, as zone " + this.uuid + " is V4VC enabled and must be played using an AdColonyV4VCAd object.");
    }
    
    boolean is_video_zone_errorless()
    {
      if ((!this.enabled) || (!this.active)) {}
      while ((this.ads.count() == 0) || (this.ads.first().v4vc.enabled)) {
        return false;
      }
      return true;
    }
    
    boolean parse(ADCData.Table paramTable)
    {
      if (paramTable == null) {}
      do
      {
        do
        {
          do
          {
            ArrayList localArrayList;
            do
            {
              return false;
              this.uuid = paramTable.get_String("uuid");
              this.enabled = paramTable.get_Logical("enabled");
              this.active = paramTable.get_Logical("active");
              if ((!this.enabled) || (!this.active)) {
                return true;
              }
              this.play_interval = paramTable.get_Integer("play_interval");
              this.daily_play_cap = paramTable.get_Integer("daily_play_cap");
              this.session_play_cap = paramTable.get_Integer("session_play_cap");
              this.play_order = new ArrayList();
              localArrayList = paramTable.get_StringList("play_order");
              this.play_order = localArrayList;
            } while (localArrayList == null);
            this.zone_tracking = new ADCManifest.ZoneTracking();
          } while (!this.zone_tracking.parse(paramTable.get_Table("tracking")));
          this.ads = new ADCManifest.Ads();
        } while (!this.ads.parse(paramTable.get_List("ads")));
        this.v4vc = new ADCManifest.ZoneV4VC();
      } while (!this.v4vc.parse(paramTable.get_Table("v4vc")));
      this.state = ADC.controller.zone_state_manager.find(this.uuid);
      return true;
    }
    
    void try_refresh()
    {
      ADC.controller.ad_manager.refresh();
    }
  }
  
  static class ZoneTracking
  {
    String request;
    
    boolean parse(ADCData.Table paramTable)
    {
      if (paramTable == null) {
        return true;
      }
      this.request = paramTable.get_String("request", null);
      return true;
    }
  }
  
  static class ZoneV4VC
  {
    boolean client_side;
    boolean enabled;
    ADCManifest.V4VCLimits limits;
    int reward_amount;
    String reward_name;
    int videos_per_reward;
    
    boolean parse(ADCData.Table paramTable)
    {
      if (paramTable == null) {}
      do
      {
        return false;
        this.enabled = paramTable.get_Logical("enabled");
        if (!this.enabled) {
          return true;
        }
        this.limits = new ADCManifest.V4VCLimits();
      } while (!this.limits.parse(paramTable.get_Table("limits")));
      this.reward_amount = paramTable.get_Integer("reward_amount");
      this.reward_name = paramTable.get_String("reward_name");
      this.client_side = paramTable.get_Logical("client_side");
      this.videos_per_reward = paramTable.get_Integer("videos_per_reward");
      return true;
    }
  }
  
  static class Zones
  {
    ArrayList<ADCManifest.Zone> data;
    
    int count()
    {
      return this.data.size();
    }
    
    ADCManifest.Zone find(String paramString)
    {
      for (int i = 0; i < this.data.size(); i++)
      {
        ADCManifest.Zone localZone = (ADCManifest.Zone)this.data.get(i);
        if (localZone.uuid.equals(paramString)) {
          return localZone;
        }
      }
      ADCLog.dev.print("No such zone: ").println(paramString);
      return null;
    }
    
    ADCManifest.Zone first()
    {
      return (ADCManifest.Zone)this.data.get(0);
    }
    
    ADCManifest.Zone get(int paramInt)
    {
      return (ADCManifest.Zone)this.data.get(paramInt);
    }
    
    boolean parse(ADCData.List paramList)
    {
      this.data = new ArrayList();
      if (paramList == null) {
        return false;
      }
      for (int i = 0;; i++)
      {
        if (i >= paramList.count()) {
          break label62;
        }
        ADCManifest.Zone localZone = new ADCManifest.Zone();
        if (!localZone.parse(paramList.get_Table(i))) {
          break;
        }
        this.data.add(localZone);
      }
      label62:
      return true;
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.jirbo.adcolony.ADCManifest
 * JD-Core Version:    0.7.0.1
 */