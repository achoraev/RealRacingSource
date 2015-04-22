package com.jirbo.adcolony;

import android.os.Build.VERSION;

class ADCConfiguration
{
  static String ad_manifest_url = "https://androidads20.adcolony.com/configure";
  String advertising_id;
  String android_id;
  String android_id_sha1;
  String app_id;
  String app_version = "1.0";
  String available_stores;
  String carrier_name;
  boolean configured = false;
  ADCController controller;
  ADCManifest.Ad current_ad;
  double current_progress = 0.0D;
  ADCManifest.Zone current_zone;
  String custom_id = "";
  String device_api;
  String device_id;
  String device_manufacturer;
  String device_model;
  String device_type;
  boolean fatal_error = false;
  String imei;
  String imei_sha1;
  String info_string;
  String language;
  boolean limit_tracking;
  String memory_class;
  String memory_used;
  String open_udid;
  String origin_store = "google";
  String os_name = "android";
  String os_version;
  ADCData.Table properties = new ADCData.Table();
  String sdk_type = "android_native";
  String sdk_version = "2.1.1";
  int session_timeout_seconds = 300;
  boolean skippable = false;
  int sleep_count = 0;
  String[] zone_ids;
  
  ADCConfiguration(ADCController paramADCController)
  {
    this.controller = paramADCController;
  }
  
  void configure()
  {
    while ((!AdColony.advertising_id_ready) && (this.sleep_count < 60)) {
      try
      {
        this.sleep_count = (1 + this.sleep_count);
        Thread.sleep(50L);
      }
      catch (Exception localException) {}
    }
    this.sleep_count = 0;
    this.controller.zone_state_manager.load();
    this.advertising_id = either_or(ADCDevice.advertising_id, "");
    this.limit_tracking = ADCDevice.limit_ad_tracking;
    this.android_id = either_or(ADCDevice.android_id(), "");
    String str;
    if (!this.advertising_id.equals(""))
    {
      str = "";
      this.android_id_sha1 = str;
      this.carrier_name = either_or(ADCDevice.carrier_name(), "");
      if (this.device_id == null) {
        this.device_id = either_or(ADCDevice.device_id(), "");
      }
      this.device_manufacturer = either_or(ADCDevice.manufacturer(), "");
      this.device_model = either_or(ADCDevice.model(), "");
      this.language = either_or(ADCDevice.language(), "en");
      this.open_udid = either_or(ADCDevice.open_udid(), "");
      this.os_version = either_or(ADCDevice.os_version(), "");
      this.device_api = either_or("" + Build.VERSION.SDK_INT, "");
      this.imei = either_or(ADCDevice.imei(), "");
      this.imei_sha1 = "";
      this.memory_class = either_or("" + ADCDevice.memory_class(), "");
      this.memory_used = either_or("" + ADCDevice.memory_used(), "");
      ADC.os_version = this.os_version;
      ADC.sdk_version = this.sdk_version;
      if (!ADC.is_tablet) {
        break label1484;
      }
    }
    label1484:
    for (this.device_type = "tablet";; this.device_type = "phone")
    {
      this.available_stores = "";
      if ((ADCUtil.application_exists("com.android.vending")) || (ADCUtil.application_exists("com.android.market"))) {
        this.available_stores = "google";
      }
      if (ADCUtil.application_exists("com.amazon.venezia"))
      {
        if (this.available_stores.length() > 0) {
          this.available_stores += ",";
        }
        this.available_stores += "amazon";
      }
      if (ADCLog.debug.enabled)
      {
        ADCLog.debug.print("sdk_version:").println(this.sdk_version);
        ADCLog.debug.print("ad_manifest_url:").println(ad_manifest_url);
        ADCLog.debug.print("app_id:").println(this.app_id);
        ADCLog.debug.print("zone_ids:").println(this.zone_ids);
        ADCLog.debug.print("os_name:").println(this.os_name);
        ADCLog.debug.print("sdk_type:").println(this.sdk_type);
        ADCLog.debug.print("app_version:").println(this.app_version);
        ADCLog.debug.print("origin_store:").println(this.origin_store);
        ADCLog.debug.print("skippable:").println(this.skippable);
        ADCLog.debug.print("android_id:").println(this.android_id);
        ADCLog.debug.print("android_id_sha1:").println(this.android_id_sha1);
        ADCLog.debug.print("available_stores:").println(this.available_stores);
        ADCLog.debug.print("carrier_name:").println(this.carrier_name);
        ADCLog.debug.print("custom_id:").println(this.custom_id);
        ADCLog.debug.print("device_id:").println(this.device_id);
        ADCLog.debug.print("device_manufacturer:").println(this.device_manufacturer);
        ADCLog.debug.print("device_model:").println(this.device_model);
        ADCLog.debug.print("device_type:").println(this.device_type);
        ADCLog.debug.print("imei:").println(this.imei);
        ADCLog.debug.print("imei_sha1:").println(this.imei_sha1);
        ADCLog.debug.print("language:").println(this.language);
        ADCLog.debug.print("open_udid:").println(this.open_udid);
        ADCLog.debug.print("os_version:").println(this.os_version);
      }
      ADCStringBuilder localADCStringBuilder = new ADCStringBuilder();
      localADCStringBuilder.print("&os_name=");
      localADCStringBuilder.print(ADCNetwork.url_encoded(this.os_name));
      localADCStringBuilder.print("&os_version=");
      localADCStringBuilder.print(ADCNetwork.url_encoded(this.os_version));
      localADCStringBuilder.print("&device_api=");
      localADCStringBuilder.print(ADCNetwork.url_encoded(this.device_api));
      localADCStringBuilder.print("&app_version=");
      localADCStringBuilder.print(ADCNetwork.url_encoded(this.app_version));
      localADCStringBuilder.print("&android_id_sha1=");
      localADCStringBuilder.print(ADCNetwork.url_encoded(this.android_id_sha1));
      localADCStringBuilder.print("&device_id=");
      localADCStringBuilder.print(ADCNetwork.url_encoded(this.device_id));
      localADCStringBuilder.print("&open_udid=");
      localADCStringBuilder.print(ADCNetwork.url_encoded(this.open_udid));
      localADCStringBuilder.print("&device_type=");
      localADCStringBuilder.print(ADCNetwork.url_encoded(this.device_type));
      localADCStringBuilder.print("&ln=");
      localADCStringBuilder.print(ADCNetwork.url_encoded(this.language));
      localADCStringBuilder.print("&device_brand=");
      localADCStringBuilder.print(ADCNetwork.url_encoded(this.device_manufacturer));
      localADCStringBuilder.print("&device_model=");
      localADCStringBuilder.print(ADCNetwork.url_encoded(this.device_model));
      localADCStringBuilder.print("&screen_width=");
      localADCStringBuilder.print(ADCNetwork.url_encoded("" + ADCDevice.display_width()));
      localADCStringBuilder.print("&screen_height=");
      localADCStringBuilder.print(ADCNetwork.url_encoded("" + ADCDevice.display_height()));
      localADCStringBuilder.print("&sdk_type=");
      localADCStringBuilder.print(ADCNetwork.url_encoded(this.sdk_type));
      localADCStringBuilder.print("&sdk_version=");
      localADCStringBuilder.print(ADCNetwork.url_encoded(this.sdk_version));
      localADCStringBuilder.print("&origin_store=");
      localADCStringBuilder.print(ADCNetwork.url_encoded(this.origin_store));
      localADCStringBuilder.print("&available_stores=");
      localADCStringBuilder.print(ADCNetwork.url_encoded(this.available_stores));
      localADCStringBuilder.print("&imei_sha1=");
      localADCStringBuilder.print(ADCNetwork.url_encoded(this.imei_sha1));
      localADCStringBuilder.print("&memory_class=");
      localADCStringBuilder.print(ADCNetwork.url_encoded(this.memory_class));
      localADCStringBuilder.print("&memory_used_mb=");
      localADCStringBuilder.print(ADCNetwork.url_encoded(this.memory_used));
      localADCStringBuilder.print("&advertiser_id=");
      localADCStringBuilder.print(ADCNetwork.url_encoded(this.advertising_id));
      localADCStringBuilder.print("&limit_tracking=");
      localADCStringBuilder.print(this.limit_tracking);
      this.info_string = localADCStringBuilder.toString();
      this.controller.storage.configure();
      this.controller.media_manager.configure();
      this.controller.reporting_manager.configure();
      this.controller.ad_manager.configure();
      this.controller.session_manager.configure();
      this.controller.play_history.configure();
      this.configured = true;
      this.controller.ad_manager.refresh();
      if ((this.controller.ad_manager.app.view_network_pass_filter == null) || (this.controller.ad_manager.app.view_network_pass_filter.equals(""))) {
        this.controller.ad_manager.app.view_network_pass_filter = "all";
      }
      if ((this.controller.ad_manager.app.cache_network_pass_filter == null) || (this.controller.ad_manager.app.cache_network_pass_filter.equals(""))) {
        this.controller.ad_manager.app.cache_network_pass_filter = "all";
      }
      return;
      str = either_or(ADCUtil.calculate_sha1(this.android_id), "");
      break;
    }
  }
  
  String either_or(String paramString1, String paramString2)
  {
    if (paramString1 != null) {
      return paramString1;
    }
    return paramString2;
  }
  
  void parse_client_options(String paramString)
  {
    if (paramString == null) {
      paramString = "";
    }
    String[] arrayOfString1 = paramString.split(",");
    int i = arrayOfString1.length;
    int j = 0;
    if (j < i)
    {
      String[] arrayOfString2 = arrayOfString1[j].split(":");
      String str1;
      String str2;
      if (arrayOfString2.length == 2)
      {
        str1 = arrayOfString2[0];
        str2 = arrayOfString2[1];
        if (str1.equals("version")) {
          this.app_version = str2;
        }
      }
      for (;;)
      {
        j++;
        break;
        if (str1.equals("store"))
        {
          if ((str2.toLowerCase().equals("google")) || (str2.toLowerCase().equals("amazon"))) {
            this.origin_store = str2;
          } else {
            throw new AdColonyException("Origin store in client options must be set to either 'google' or 'amazon'");
          }
        }
        else if (str1.equals("skippable"))
        {
          this.skippable = false;
          continue;
          if (arrayOfString2[0].equals("skippable")) {
            this.skippable = false;
          }
        }
      }
    }
  }
  
  void prepare_end_card()
  {
    ADCManifest.StaticCompanionAdInfo localStaticCompanionAdInfo = this.current_ad.companion_ad._static;
    ADCManifest.HTML5CompanionAdInfo localHTML5CompanionAdInfo = this.current_ad.companion_ad.html5;
    ADCMediaManager localADCMediaManager = this.controller.media_manager;
    if (!this.current_ad.companion_ad.enabled) {
      return;
    }
    if (localHTML5CompanionAdInfo.is_ready())
    {
      ADC.end_card_is_html5 = true;
      ADC.end_card_url = localHTML5CompanionAdInfo.html5_tag;
      ADC.end_card_mraid_filepath = localADCMediaManager.local_filepath(localHTML5CompanionAdInfo.mraid_js.url);
      this.properties.set("close_image_normal", localADCMediaManager.local_filepath(localHTML5CompanionAdInfo.close.image_normal));
      this.properties.set("close_image_down", localADCMediaManager.local_filepath(localHTML5CompanionAdInfo.close.image_down));
      this.properties.set("reload_image_normal", localADCMediaManager.local_filepath(localHTML5CompanionAdInfo.replay.image_normal));
      this.properties.set("reload_image_down", localADCMediaManager.local_filepath(localHTML5CompanionAdInfo.replay.image_down));
    }
    for (;;)
    {
      this.properties.set("end_card_enabled", this.current_ad.companion_ad.enabled);
      this.properties.set("load_timeout_enabled", this.current_ad.companion_ad.html5.load_timeout_enabled);
      this.properties.set("load_timeout", this.current_ad.companion_ad.html5.load_timeout);
      this.properties.set("hardware_acceleration_disabled", this.controller.ad_manager.app.hardware_acceleration_disabled);
      return;
      ADC.end_card_is_html5 = false;
      this.properties.set("end_card_filepath", localADCMediaManager.local_filepath(localStaticCompanionAdInfo.background_image));
      this.properties.set("info_image_normal", localADCMediaManager.local_filepath(localStaticCompanionAdInfo.info.image_normal));
      this.properties.set("info_image_down", localADCMediaManager.local_filepath(localStaticCompanionAdInfo.info.image_down));
      this.properties.set("info_url", localStaticCompanionAdInfo.info.click_action);
      this.properties.set("replay_image_normal", localADCMediaManager.local_filepath(localStaticCompanionAdInfo.replay.image_normal));
      this.properties.set("replay_image_down", localADCMediaManager.local_filepath(localStaticCompanionAdInfo.replay.image_down));
      this.properties.set("continue_image_normal", localADCMediaManager.local_filepath(localStaticCompanionAdInfo._continue.image_normal));
      this.properties.set("continue_image_down", localADCMediaManager.local_filepath(localStaticCompanionAdInfo._continue.image_down));
      this.properties.set("download_image_normal", localADCMediaManager.local_filepath(localStaticCompanionAdInfo.download.image_normal));
      this.properties.set("download_image_down", localADCMediaManager.local_filepath(localStaticCompanionAdInfo.download.image_down));
      this.properties.set("download_url", localStaticCompanionAdInfo.download.click_action);
    }
  }
  
  void prepare_v4vc_ad(String paramString)
  {
    this.current_zone = this.controller.ad_manager.app.zones.find(paramString);
    this.current_ad = this.current_zone.current_ad();
    ADCMediaManager localADCMediaManager = this.controller.media_manager;
    ADCManifest.Video localVideo = this.current_ad.video;
    this.properties.set("video_enabled", localVideo.enabled);
    this.properties.set("video_filepath", localVideo.filepath());
    this.properties.set("video_width", localVideo.width);
    this.properties.set("video_height", localVideo.height);
    this.properties.set("video_duration", localVideo.duration);
    this.properties.set("engagement_delay", localVideo.in_video_engagement.delay);
    this.properties.set("skip_delay", localVideo.skip_video.delay);
    prepare_end_card();
    ADCManifest.AdV4VC localAdV4VC = this.current_ad.v4vc;
    this.properties.set("pre_popup_bg", localADCMediaManager.local_filepath(localAdV4VC.pre_popup.dialog.image));
    this.properties.set("v4vc_logo", localADCMediaManager.local_filepath(localAdV4VC.pre_popup.dialog.logo.image));
    this.properties.set("no_button_normal", localADCMediaManager.local_filepath(localAdV4VC.pre_popup.dialog.option_no.image_normal));
    this.properties.set("no_button_down", localADCMediaManager.local_filepath(localAdV4VC.pre_popup.dialog.option_no.image_down));
    this.properties.set("yes_button_normal", localADCMediaManager.local_filepath(localAdV4VC.pre_popup.dialog.option_yes.image_normal));
    this.properties.set("yes_button_down", localADCMediaManager.local_filepath(localAdV4VC.pre_popup.dialog.option_yes.image_down));
    this.properties.set("done_button_normal", localADCMediaManager.local_filepath(localAdV4VC.post_popup.dialog.option_done.image_normal));
    this.properties.set("done_button_down", localADCMediaManager.local_filepath(localAdV4VC.post_popup.dialog.option_done.image_down));
    this.properties.set("browser_close_image_normal", localADCMediaManager.local_filepath(this.current_ad.in_app_browser.close.image_normal));
    this.properties.set("browser_close_image_down", localADCMediaManager.local_filepath(this.current_ad.in_app_browser.close.image_down));
    this.properties.set("browser_reload_image_normal", localADCMediaManager.local_filepath(this.current_ad.in_app_browser.reload.image_normal));
    this.properties.set("browser_reload_image_down", localADCMediaManager.local_filepath(this.current_ad.in_app_browser.reload.image_down));
    this.properties.set("browser_back_image_normal", localADCMediaManager.local_filepath(this.current_ad.in_app_browser.back.image_normal));
    this.properties.set("browser_back_image_down", localADCMediaManager.local_filepath(this.current_ad.in_app_browser.back.image_down));
    this.properties.set("browser_forward_image_normal", localADCMediaManager.local_filepath(this.current_ad.in_app_browser.forward.image_normal));
    this.properties.set("browser_forward_image_down", localADCMediaManager.local_filepath(this.current_ad.in_app_browser.forward.image_down));
    this.properties.set("browser_stop_image_normal", localADCMediaManager.local_filepath(this.current_ad.in_app_browser.stop.image_normal));
    this.properties.set("browser_stop_image_down", localADCMediaManager.local_filepath(this.current_ad.in_app_browser.stop.image_down));
    this.properties.set("browser_glow_button", localADCMediaManager.local_filepath(this.current_ad.in_app_browser.tiny_glow_image));
    this.properties.set("browser_icon", localADCMediaManager.local_filepath(this.current_ad.in_app_browser.logo.image));
    this.properties.set("skip_video_image_normal", localADCMediaManager.local_filepath(localVideo.skip_video.image_normal));
    this.properties.set("skip_video_image_down", localADCMediaManager.local_filepath(localVideo.skip_video.image_down));
    this.properties.set("engagement_image_normal", localADCMediaManager.local_filepath(localVideo.in_video_engagement.image_normal));
    this.properties.set("engagement_image_down", localADCMediaManager.local_filepath(localVideo.in_video_engagement.image_down));
  }
  
  void prepare_video_ad(String paramString)
  {
    prepare_video_ad(paramString, null);
  }
  
  void prepare_video_ad(String paramString, ADCManifest.Ad paramAd)
  {
    this.current_zone = this.controller.ad_manager.app.zones.find(paramString);
    if (this.current_zone == null) {}
    for (;;)
    {
      return;
      if (paramAd == null) {}
      for (this.current_ad = this.current_zone.current_ad(); this.current_ad != null; this.current_ad = paramAd)
      {
        ADCMediaManager localADCMediaManager = this.controller.media_manager;
        ADCManifest.Video localVideo = this.current_ad.video;
        this.properties.set("video_enabled", localVideo.enabled);
        this.properties.set("video_filepath", localVideo.filepath());
        this.properties.set("video_width", localVideo.width);
        this.properties.set("video_height", localVideo.height);
        this.properties.set("video_duration", localVideo.duration);
        this.properties.set("engagement_delay", localVideo.in_video_engagement.delay);
        this.properties.set("skip_delay", localVideo.skip_video.delay);
        this.properties.set("browser_close_image_normal", localADCMediaManager.local_filepath(this.current_ad.in_app_browser.close.image_normal));
        this.properties.set("browser_close_image_down", localADCMediaManager.local_filepath(this.current_ad.in_app_browser.close.image_down));
        this.properties.set("browser_reload_image_normal", localADCMediaManager.local_filepath(this.current_ad.in_app_browser.reload.image_normal));
        this.properties.set("browser_reload_image_down", localADCMediaManager.local_filepath(this.current_ad.in_app_browser.reload.image_down));
        this.properties.set("browser_back_image_normal", localADCMediaManager.local_filepath(this.current_ad.in_app_browser.back.image_normal));
        this.properties.set("browser_back_image_down", localADCMediaManager.local_filepath(this.current_ad.in_app_browser.back.image_down));
        this.properties.set("browser_forward_image_normal", localADCMediaManager.local_filepath(this.current_ad.in_app_browser.forward.image_normal));
        this.properties.set("browser_forward_image_down", localADCMediaManager.local_filepath(this.current_ad.in_app_browser.forward.image_down));
        this.properties.set("browser_stop_image_normal", localADCMediaManager.local_filepath(this.current_ad.in_app_browser.stop.image_normal));
        this.properties.set("browser_stop_image_down", localADCMediaManager.local_filepath(this.current_ad.in_app_browser.stop.image_down));
        this.properties.set("browser_glow_button", localADCMediaManager.local_filepath(this.current_ad.in_app_browser.tiny_glow_image));
        this.properties.set("browser_icon", localADCMediaManager.local_filepath(this.current_ad.in_app_browser.logo.image));
        this.properties.set("mute", localADCMediaManager.local_filepath(this.current_ad.native_ad.mute.image));
        this.properties.set("unmute", localADCMediaManager.local_filepath(this.current_ad.native_ad.unmute.image));
        this.properties.set("poster_image", localADCMediaManager.local_filepath(this.current_ad.native_ad.poster_image));
        this.properties.set("thumb_image", localADCMediaManager.local_filepath(this.current_ad.native_ad.thumb_image));
        this.properties.set("advertiser_name", this.current_ad.native_ad.advertiser_name);
        this.properties.set("description", this.current_ad.native_ad.description);
        this.properties.set("title", this.current_ad.native_ad.title);
        this.properties.set("native_engagement_enabled", this.current_ad.native_ad.native_overlay.enabled);
        this.properties.set("native_engagement_type", this.current_ad.native_ad.native_overlay.click_action_type);
        this.properties.set("native_engagement_command", this.current_ad.native_ad.native_overlay.click_action);
        this.properties.set("native_engagement_label", this.current_ad.native_ad.native_overlay.label);
        this.properties.set("skip_video_image_normal", localADCMediaManager.local_filepath(localVideo.skip_video.image_normal));
        this.properties.set("skip_video_image_down", localADCMediaManager.local_filepath(localVideo.skip_video.image_down));
        this.properties.set("engagement_image_normal", localADCMediaManager.local_filepath(localVideo.in_video_engagement.image_normal));
        this.properties.set("engagement_image_down", localADCMediaManager.local_filepath(localVideo.in_video_engagement.image_down));
        prepare_end_card();
        return;
      }
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.jirbo.adcolony.ADCConfiguration
 * JD-Core Version:    0.7.0.1
 */