package com.jirbo.adcolony;

import java.util.ArrayList;
import java.util.HashMap;

class ADCReportingManager
  implements ADCDownload.Listener
{
  int active_submissions = 0;
  ADCController controller;
  ArrayList<Event> culling_list = new ArrayList();
  boolean modified = false;
  ArrayList<Event> pending_events = new ArrayList();
  
  ADCReportingManager(ADCController paramADCController)
  {
    this.controller = paramADCController;
  }
  
  void configure()
  {
    load();
    this.active_submissions = 0;
  }
  
  void dispatch_events()
  {
    if (this.pending_events.size() == 0) {}
    label216:
    for (;;)
    {
      return;
      while (this.pending_events.size() > 1000) {
        this.pending_events.remove(-1 + this.pending_events.size());
      }
      if (ADCNetwork.connected())
      {
        double d = ADCUtil.current_time();
        for (int i = 0;; i++)
        {
          if (i >= this.pending_events.size()) {
            break label216;
          }
          Event localEvent = (Event)this.pending_events.get(i);
          if ((localEvent.next_attempt_time < d) && (!localEvent.submitting))
          {
            if (this.active_submissions == 5) {
              break;
            }
            this.active_submissions = (1 + this.active_submissions);
            localEvent.submitting = true;
            ADCDownload localADCDownload = new ADCDownload(this.controller, localEvent.url, this).with_info(localEvent);
            if (localEvent.third_party) {
              localADCDownload.third_party_tracking = true;
            }
            if (localEvent.payload != null) {
              localADCDownload.with_post_data("application/json", localEvent.payload);
            }
            ADCLog.debug.print("Submitting '").print(localEvent.type).println("' event.");
            localADCDownload.start();
            ADC.active = true;
          }
        }
      }
    }
  }
  
  void load()
  {
    ADC.active = true;
    ADCData.List localList = ADCJSON.load_List(new ADCDataFile("tracking_info.txt"));
    if (localList != null)
    {
      this.pending_events.clear();
      for (int i = 0; i < localList.count(); i++)
      {
        ADCData.Table localTable = localList.get_Table(i);
        Event localEvent = new Event();
        localEvent.type = localTable.get_String("type");
        localEvent.url = localTable.get_String("url");
        localEvent.payload = localTable.get_String("payload", null);
        localEvent.attempts = localTable.get_Integer("attempts");
        localEvent.third_party = localTable.get_Logical("third_party");
        if ((localEvent.type.equals("v4vc_callback")) || (localEvent.type.equals("reward_v4vc")))
        {
          localEvent.v4vc_name = localTable.get_String("v4vc_name");
          localEvent.v4vc_amount = localTable.get_Integer("v4vc_amount");
        }
        this.pending_events.add(localEvent);
      }
    }
    ADCLog.dev.print("Loaded ").print(this.pending_events.size()).println(" events");
  }
  
  public void on_download_finished(ADCDownload paramADCDownload)
  {
    ADC.active = true;
    this.modified = true;
    this.active_submissions = (-1 + this.active_submissions);
    Event localEvent1 = (Event)paramADCDownload.info;
    ADCLog.dev.print("on_download_finished - event.type = ").println(localEvent1.type);
    localEvent1.submitting = false;
    boolean bool = paramADCDownload.success;
    if ((bool) && (localEvent1.payload != null))
    {
      ADCData.Table localTable = ADCJSON.parse_Table(paramADCDownload.data);
      if (localTable == null) {
        break label357;
      }
      bool = localTable.get_String("status").equals("success");
      if ((bool) && (localEvent1.type.equals("reward_v4vc")))
      {
        if (!localTable.get_Logical("v4vc_status")) {
          break label344;
        }
        String str = localTable.get_String("v4vc_callback");
        if (str.length() <= 0) {
          break label318;
        }
        Event localEvent2 = new Event();
        localEvent2.type = "v4vc_callback";
        localEvent2.url = str;
        localEvent2.v4vc_name = localEvent1.v4vc_name;
        localEvent2.v4vc_amount = localEvent1.v4vc_amount;
        this.pending_events.add(localEvent2);
      }
    }
    if ((bool) && (localEvent1.type.equals("v4vc_callback")))
    {
      ADCLog.dev.println("v4vc_callback response:").println(paramADCDownload.data);
      if (paramADCDownload.data.indexOf("vc_success") != -1)
      {
        if (ADC.current_video != null) {
          ADC.current_video.rewarded = true;
        }
        ADCLog.dev.println("v4vc_callback success");
        this.controller.on_v4vc_result(true, localEvent1.v4vc_name, localEvent1.v4vc_amount);
      }
    }
    else
    {
      label273:
      if (!bool) {
        break label456;
      }
      ADCLog.dev.print("Event submission SUCCESS for type: ").println(localEvent1.type);
      localEvent1.discard = true;
    }
    for (;;)
    {
      if (!this.controller.session_manager.active) {
        save();
      }
      return;
      label318:
      if (ADC.current_video != null) {
        ADC.current_video.rewarded = true;
      }
      ADCLog.dev.println("Client-side V4VC success");
      break;
      label344:
      ADCLog.dev.println("Client-side V4VC failure");
      break;
      label357:
      bool = false;
      break;
      if ((paramADCDownload.data.indexOf("vc_decline") != -1) || (paramADCDownload.data.indexOf("vc_noreward") != -1))
      {
        ADCLog.info.print("Server-side V4VC failure: ").println(paramADCDownload.url);
        ADCLog.dev.println("v4vc_callback declined");
        this.controller.on_v4vc_result(false, "", 0);
        break label273;
      }
      ADCLog.info.print("Server-side V4VC failure: ").println(paramADCDownload.url);
      bool = false;
      break label273;
      label456:
      ADCLog.dev.print("Event submission FAILED for type: ").print(localEvent1.type).print(" on try ").println(1 + localEvent1.attempts);
      localEvent1.attempts = (1 + localEvent1.attempts);
      if (localEvent1.attempts >= 24)
      {
        ADCLog.error.println("Discarding event after 24 attempts to report.");
        localEvent1.discard = true;
        if (localEvent1.type.equals("v4vc_callback")) {
          this.controller.on_v4vc_result(false, "", 0);
        }
      }
      else
      {
        int i = 20;
        if (localEvent1.next_attempt_delay > 0) {
          i = 3 * localEvent1.next_attempt_delay;
        }
        if (i > 10000) {
          i = 10000;
        }
        ADCLog.dev.print("Retrying in ").print(i).print(" seconds (attempt ").print(localEvent1.attempts).println(")");
        localEvent1.next_attempt_delay = i;
        localEvent1.next_attempt_time = (ADCUtil.current_time() + i);
      }
    }
  }
  
  void save()
  {
    this.culling_list.clear();
    this.culling_list.addAll(this.pending_events);
    this.pending_events.clear();
    ADCData.List localList = new ADCData.List();
    for (int i = 0; i < this.culling_list.size(); i++)
    {
      Event localEvent = (Event)this.culling_list.get(i);
      if (!localEvent.discard)
      {
        this.pending_events.add(localEvent);
        ADCData.Table localTable = new ADCData.Table();
        localTable.set("type", localEvent.type);
        localTable.set("url", localEvent.url);
        if (localEvent.payload != null) {
          localTable.set("payload", localEvent.payload);
        }
        localTable.set("attempts", localEvent.attempts);
        if (localEvent.v4vc_name != null)
        {
          localTable.set("v4vc_name", localEvent.v4vc_name);
          localTable.set("v4vc_amount", localEvent.v4vc_amount);
        }
        if (localEvent.third_party) {
          localTable.set("third_party", true);
        }
        localList.add(localTable);
      }
    }
    this.culling_list.clear();
    ADCLog.dev.print("Saving tracking_info (").print(this.pending_events.size()).println(" events)");
    ADCJSON.save(new ADCDataFile("tracking_info.txt"), localList);
  }
  
  void track_ad_event(String paramString, ADCData.Table paramTable, AdColonyAd paramAdColonyAd)
  {
    if (paramString == null)
    {
      ADCLog.error.println("No such event type:").println(paramString);
      return;
    }
    if (paramTable == null)
    {
      paramTable = new ADCData.Table();
      paramTable.set("replay", paramAdColonyAd.replay);
    }
    track_adcolony_event(paramString, paramAdColonyAd.ad_info.ad_tracking.lookup.get_String(paramString), paramTable, paramAdColonyAd);
    track_third_party_event(paramString, (ArrayList)paramAdColonyAd.ad_info.third_party_tracking.lookup.get(paramString));
  }
  
  void track_ad_event(String paramString, AdColonyAd paramAdColonyAd)
  {
    track_ad_event(paramString, null, paramAdColonyAd);
  }
  
  void track_ad_request(String paramString, AdColonyAd paramAdColonyAd)
  {
    if ((this.controller == null) || (this.controller.ad_manager == null) || (this.controller.ad_manager.app == null) || (this.controller.ad_manager.app.zones == null) || (this.controller.ad_manager.app.zones.find(paramString) == null)) {}
    ADCManifest.Zone localZone;
    do
    {
      return;
      ADCLog.dev.print("Ad request for zone ").println(paramString);
      localZone = this.controller.ad_manager.app.zones.find(paramString);
    } while ((localZone == null) || (localZone.zone_tracking == null) || (localZone.zone_tracking.request == null));
    ADCData.Table localTable = new ADCData.Table();
    if (ADC.error_code == 0) {
      localTable.set("request_denied", false);
    }
    for (;;)
    {
      localTable.set("request_denied_reason", ADC.error_code);
      track_adcolony_event("request", localZone.zone_tracking.request, localTable, paramAdColonyAd);
      ADCLog.dev.print("Tracking ad request - URL : ").println(localZone.zone_tracking.request);
      return;
      localTable.set("request_denied", true);
    }
  }
  
  void track_adcolony_event(String paramString1, String paramString2, ADCData.Table paramTable)
  {
    track_adcolony_event(paramString1, paramString2, paramTable, null);
  }
  
  void track_adcolony_event(String paramString1, String paramString2, ADCData.Table paramTable, AdColonyAd paramAdColonyAd)
  {
    if ((paramString2 == null) || (paramString2.equals(""))) {
      return;
    }
    if (paramTable == null) {
      paramTable = new ADCData.Table();
    }
    String str = ADCUtil.create_uuid();
    if (paramAdColonyAd != null) {
      paramTable.set("asi", paramAdColonyAd.asi);
    }
    paramTable.set("sid", this.controller.session_manager.current_session_id);
    paramTable.set("guid", str);
    paramTable.set("guid_key", ADCUtil.calculate_sha1(str + "DUBu6wJ27y6xs7VWmNDw67DD"));
    Event localEvent = new Event();
    localEvent.type = paramString1;
    localEvent.url = paramString2;
    ADCLog.dev.println("EVENT ----------------------------");
    ADCLog.dev.print("EVENT - TYPE = ").println(paramString1);
    ADCLog.dev.print("EVENT - URL  = ").println(paramString2);
    localEvent.payload = paramTable.to_json();
    if (paramString1.equals("reward_v4vc"))
    {
      localEvent.v4vc_name = paramTable.get_String("v4vc_name");
      localEvent.v4vc_amount = paramTable.get_Integer("v4vc_amount");
    }
    this.pending_events.add(localEvent);
    this.modified = true;
    ADC.active = true;
  }
  
  void track_app_event(String paramString, ADCData.Table paramTable)
  {
    ADCManifest.AppTracking localAppTracking = this.controller.ad_manager.app.app_tracking;
    if (localAppTracking != null) {
      track_adcolony_event(paramString, localAppTracking.lookup.get_String(paramString), paramTable);
    }
    ADCManifest.ThirdPartyAppTracking localThirdPartyAppTracking = this.controller.ad_manager.app.third_party_app_tracking;
    if (localThirdPartyAppTracking != null) {
      track_third_party_event(paramString, (ArrayList)localThirdPartyAppTracking.lookup.get(paramString));
    }
  }
  
  void track_third_party_event(String paramString, ArrayList<String> paramArrayList)
  {
    if ((paramArrayList == null) || (paramArrayList.size() == 0)) {
      return;
    }
    for (int i = 0; i < paramArrayList.size(); i++)
    {
      String str = (String)paramArrayList.get(i);
      Event localEvent = new Event();
      localEvent.type = paramString;
      localEvent.url = str;
      localEvent.third_party = true;
      this.pending_events.add(localEvent);
    }
    this.modified = true;
    ADC.active = true;
  }
  
  void track_video_progress(double paramDouble, AdColonyAd paramAdColonyAd)
  {
    double d = paramAdColonyAd.current_progress;
    if (paramDouble < d) {
      return;
    }
    label110:
    ADCData.Table localTable;
    if ((d < 0.25D) && (paramDouble >= 0.25D))
    {
      if ((!AdColony.isZoneV4VC(paramAdColonyAd.zone_id)) && (paramAdColonyAd.view_format.equals("native"))) {
        track_ad_event("native_first_quartile", paramAdColonyAd);
      }
    }
    else
    {
      if ((d < 0.5D) && (paramDouble >= 0.5D))
      {
        if ((AdColony.isZoneV4VC(paramAdColonyAd.zone_id)) || (!paramAdColonyAd.view_format.equals("native"))) {
          break label267;
        }
        track_ad_event("native_midpoint", paramAdColonyAd);
      }
      if ((d < 0.75D) && (paramDouble >= 0.75D))
      {
        if ((AdColony.isZoneV4VC(paramAdColonyAd.zone_id)) || (!paramAdColonyAd.view_format.equals("native"))) {
          break label278;
        }
        track_ad_event("native_third_quartile", paramAdColonyAd);
      }
      label158:
      if ((d < 1.0D) && (paramDouble >= 1.0D) && (!paramAdColonyAd.view_format.equals("native")))
      {
        ADCLog.dev.print("Tracking ad event - complete");
        localTable = new ADCData.Table();
        if (!paramAdColonyAd.is_native_expanded) {
          break label289;
        }
        localTable.set("ad_slot", paramAdColonyAd.zone_info.state.session_play_count);
      }
    }
    for (;;)
    {
      localTable.set("replay", paramAdColonyAd.replay);
      track_ad_event("complete", localTable, paramAdColonyAd);
      paramAdColonyAd.current_progress = paramDouble;
      return;
      track_ad_event("first_quartile", paramAdColonyAd);
      break;
      label267:
      track_ad_event("midpoint", paramAdColonyAd);
      break label110;
      label278:
      track_ad_event("third_quartile", paramAdColonyAd);
      break label158;
      label289:
      localTable.set("ad_slot", paramAdColonyAd.zone_info.state.session_play_count);
    }
  }
  
  void update()
  {
    if (this.modified)
    {
      this.modified = false;
      save();
    }
    dispatch_events();
  }
  
  static class Event
  {
    int attempts;
    boolean discard;
    int next_attempt_delay;
    double next_attempt_time;
    String payload;
    boolean submitting;
    boolean third_party;
    String type;
    String url;
    int v4vc_amount;
    String v4vc_name;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.jirbo.adcolony.ADCReportingManager
 * JD-Core Version:    0.7.0.1
 */