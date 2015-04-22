package com.jirbo.adcolony;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

class ADC
{
  static final String LOGTAG = "AdColony";
  public static final int LOG_DEBUG = 1;
  public static final int LOG_DEV = 0;
  public static final int LOG_ERROR = 3;
  public static final int LOG_INFO = 2;
  static boolean active;
  private static Activity activity;
  static ArrayList<AdColonyAdAvailabilityListener> ad_availability_listener_list = new ArrayList();
  static HashMap ad_availability_map;
  static ArrayList<Bitmap> bitmaps;
  static boolean block;
  static boolean companion_ad_disabled;
  static boolean configured;
  static boolean connected;
  static ADCController controller;
  static AdColonyAd current_ad;
  static ADCDialog current_dialog;
  static ADCVideo current_video;
  static boolean destroyed;
  static boolean disable_block;
  public static final boolean disable_limits;
  static boolean dont_resume;
  public static final boolean enable_developer_logging;
  static EndCardFinished end_card_finished_handler;
  static boolean end_card_is_html5;
  static String end_card_mraid_filepath;
  static String end_card_url;
  static int error_code;
  static boolean fatal_error;
  static boolean finishing;
  public static String force_dec_url = null;
  public static final String force_static_url = null;
  static boolean graceful_fail;
  static double hud_scale;
  static boolean initialized;
  static boolean is_tablet;
  public static final boolean keep_current_ads;
  static long last_config_ms;
  static ADCVideo latest_video;
  static boolean layout_changed;
  static int load_timeout;
  static int log_level;
  static boolean main_activity_paused;
  static boolean mraid_block;
  static ArrayList<AdColonyNativeAdView> native_ad_view_list = new ArrayList();
  static int orientation;
  static String os_version;
  public static final boolean play_short_videos;
  static boolean resume_from_ad;
  static String sdk_version;
  static boolean show;
  static boolean show_post_popup;
  static boolean user_disabled;
  static ArrayList<AdColonyV4VCListener> v4vc_listener_list;
  static V4VCResultsHandler v4vc_results_handler;
  static boolean video_disabled;
  
  static
  {
    controller = new ADCController();
    log_level = 2;
    show_post_popup = false;
    show = true;
    orientation = 0;
    hud_scale = 1.0D;
    block = false;
    disable_block = false;
    mraid_block = false;
    layout_changed = false;
    connected = true;
    error_code = 0;
    bitmaps = new ArrayList();
    v4vc_listener_list = new ArrayList();
  }
  
  static Activity activity()
  {
    if (activity == null) {
      throw new AdColonyException("AdColony.configure() must be called before any other AdColony methods. If you have called AdColony.configure(), the Activity reference you passed in via AdColony.configure() OR AdColony.resume() is null.");
    }
    return activity;
  }
  
  static void add_native_ad_view(AdColonyNativeAdView paramAdColonyNativeAdView)
  {
    native_ad_view_list.add(paramAdColonyNativeAdView);
  }
  
  static boolean disabled()
  {
    return (fatal_error) || (user_disabled) || (!initialized);
  }
  
  static void ensure_configured()
  {
    activity();
  }
  
  static int get_Integer(String paramString)
  {
    return controller.get_Integer(paramString);
  }
  
  static boolean get_Logical(String paramString)
  {
    return controller.get_Logical(paramString);
  }
  
  static double get_Real(String paramString)
  {
    return controller.get_Real(paramString);
  }
  
  static String get_String(String paramString)
  {
    return controller.get_String(paramString);
  }
  
  static void has_ad_availability_changed()
  {
    if (controller == null) {}
    Iterator localIterator;
    label34:
    do
    {
      return;
      while ((ad_availability_listener_list.size() == 0) || (ad_availability_map == null)) {}
      localIterator = ad_availability_map.entrySet().iterator();
    } while (!localIterator.hasNext());
    Map.Entry localEntry = (Map.Entry)localIterator.next();
    boolean bool1 = ((Boolean)localEntry.getValue()).booleanValue();
    if (AdColony.isZoneV4VC((String)localEntry.getKey())) {}
    for (boolean bool2 = controller.is_v4vc_ad_available((String)localEntry.getKey(), true, false);; bool2 = controller.is_video_ad_available((String)localEntry.getKey(), true, false))
    {
      if (AdColony.isZoneNative((String)localEntry.getKey())) {
        bool2 = new AdColonyNativeAdView(activity(), (String)localEntry.getKey(), 300, true).isReady(true);
      }
      if (bool1 == bool2) {
        break;
      }
      ad_availability_map.put(localEntry.getKey(), Boolean.valueOf(bool2));
      for (int i = 0; i < ad_availability_listener_list.size(); i++) {
        ((AdColonyAdAvailabilityListener)ad_availability_listener_list.get(i)).onAdColonyAdAvailabilityChange(bool2, (String)localEntry.getKey());
      }
      break label34;
    }
  }
  
  static void initialize(Activity paramActivity)
  {
    configured = false;
    set_activity(paramActivity);
    current_dialog = null;
    is_tablet = ADCDevice.is_tablet();
    if (fatal_error)
    {
      fatal_error = false;
      initialized = false;
      controller = new ADCController();
    }
  }
  
  static boolean is_activity_null()
  {
    return activity == null;
  }
  
  static boolean is_ready()
  {
    return (initialized) && (!fatal_error) && (!user_disabled);
  }
  
  static void log(int paramInt, String paramString)
  {
    if (log_level <= paramInt) {}
    switch (paramInt)
    {
    default: 
      return;
    case 0: 
    case 1: 
      Log.d("AdColony", paramString);
      return;
    case 2: 
      Log.i("AdColony", paramString);
      return;
    }
    Log.e("AdColony", paramString);
  }
  
  static void log_debug(String paramString)
  {
    log(1, paramString);
  }
  
  static void log_dev(String paramString)
  {
    log(0, paramString);
  }
  
  static void log_error(String paramString)
  {
    log(3, paramString);
  }
  
  static void log_info(String paramString)
  {
    log(2, paramString);
  }
  
  static void on_fatal_error(RuntimeException paramRuntimeException)
  {
    fatal_error = true;
    log_error(paramRuntimeException.toString());
    paramRuntimeException.printStackTrace();
  }
  
  static void on_fatal_error(String paramString)
  {
    fatal_error = true;
    log_error(paramString);
  }
  
  static void queue_event(ADCEvent paramADCEvent)
  {
    controller.queue_event(paramADCEvent);
  }
  
  static void set_activity(Activity paramActivity)
  {
    if ((paramActivity == activity) || (paramActivity == null)) {
      return;
    }
    activity = paramActivity;
    end_card_finished_handler = new EndCardFinished();
    v4vc_results_handler = new V4VCResultsHandler();
    new ADCMonitor.Pinger();
  }
  
  static void set_log_level(int paramInt)
  {
    log_level = paramInt;
    ADCLog localADCLog1 = ADCLog.dev;
    boolean bool1;
    boolean bool2;
    label31:
    ADCLog localADCLog3;
    if (paramInt <= 0)
    {
      bool1 = true;
      localADCLog1.enabled = bool1;
      ADCLog localADCLog2 = ADCLog.debug;
      if (paramInt > 1) {
        break label107;
      }
      bool2 = true;
      localADCLog2.enabled = bool2;
      localADCLog3 = ADCLog.info;
      if (paramInt > 2) {
        break label113;
      }
    }
    label107:
    label113:
    for (boolean bool3 = true;; bool3 = false)
    {
      localADCLog3.enabled = bool3;
      ADCLog localADCLog4 = ADCLog.error;
      boolean bool4 = false;
      if (paramInt <= 3) {
        bool4 = true;
      }
      localADCLog4.enabled = bool4;
      if (paramInt <= 0) {
        log_dev("DEVELOPER LOGGING ENABLED");
      }
      if (paramInt <= 1) {
        log_debug("DEBUG LOGGING ENABLED");
      }
      return;
      bool1 = false;
      break;
      bool2 = false;
      break label31;
    }
  }
  
  static boolean should_log(int paramInt)
  {
    return log_level <= paramInt;
  }
  
  static boolean should_log_debug()
  {
    return log_level <= 1;
  }
  
  static boolean should_log_dev()
  {
    return log_level <= 0;
  }
  
  static void trace(String paramString)
  {
    Toast.makeText(activity(), paramString, 0).show();
  }
  
  static void track_ad_event(String paramString, AdColonyAd paramAdColonyAd)
  {
    controller.track_ad_event(paramString, null, paramAdColonyAd);
  }
  
  static void track_ad_event(String paramString1, String paramString2, AdColonyAd paramAdColonyAd)
  {
    controller.track_ad_event(paramString1, paramString2, paramAdColonyAd);
  }
  
  static void track_app_event(String paramString)
  {
    controller.track_app_event(paramString, null);
  }
  
  static void track_app_event(String paramString1, String paramString2)
  {
    controller.track_app_event(paramString1, paramString2);
  }
  
  static class EndCardFinished
    extends Handler
  {
    AdColonyAd ad;
    
    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      }
      do
      {
        do
        {
          return;
          ADC.track_ad_event("skip", this.ad);
        } while (ADC.current_ad == null);
        ADC.current_ad.status = 1;
        ADC.current_ad.on_video_finished();
        return;
        ADCData.Table localTable = new ADCData.Table();
        if (ADC.latest_video.hud.is_html5) {
          localTable.set("html5_endcard_loading_started", ADC.latest_video.html5_endcard_loading_started);
        }
        if (ADC.latest_video.hud.is_html5) {
          localTable.set("html5_endcard_loading_finished", ADC.latest_video.html5_endcard_loading_finished);
        }
        if (ADC.latest_video.hud.is_html5) {
          localTable.set("html5_endcard_loading_time", ADC.latest_video.html5_endcard_loading_time);
        }
        if (ADC.latest_video.hud.is_html5) {
          localTable.set("html5_endcard_loading_timeout", ADC.latest_video.html5_endcard_loading_timeout);
        }
        if (ADC.latest_video.endcard_time_spent < 60000.0D) {
          localTable.set("endcard_time_spent", ADC.latest_video.endcard_time_spent);
        }
        localTable.set("endcard_dissolved", ADC.latest_video.endcard_dissolved);
        localTable.set("replay", ADCVideo.is_replay);
        localTable.set("reward", ADC.latest_video.rewarded);
        ADC.controller.reporting_manager.track_ad_event("continue", localTable, this.ad);
        ADC.controller.ad_manager.refresh();
      } while (ADC.current_ad == null);
      ADC.current_ad.on_video_finished();
    }
    
    public void notify_canceled(AdColonyAd paramAdColonyAd)
    {
      if (paramAdColonyAd == null) {}
      for (this.ad = ADC.current_ad;; this.ad = paramAdColonyAd)
      {
        sendMessage(obtainMessage(0));
        return;
      }
    }
    
    public void notify_continuation(AdColonyAd paramAdColonyAd)
    {
      if (paramAdColonyAd == null) {}
      for (this.ad = ADC.current_ad;; this.ad = paramAdColonyAd)
      {
        sendMessage(obtainMessage(1));
        return;
      }
    }
  }
  
  static class V4VCResultsHandler
    extends Handler
  {
    public void handleMessage(Message paramMessage)
    {
      String str = (String)paramMessage.obj;
      int i = paramMessage.what;
      if (str != null) {}
      for (boolean bool = true;; bool = false)
      {
        if (!bool) {
          str = "";
        }
        AdColonyV4VCReward localAdColonyV4VCReward = new AdColonyV4VCReward(bool, str, i);
        for (int j = 0; j < ADC.v4vc_listener_list.size(); j++) {
          ((AdColonyV4VCListener)ADC.v4vc_listener_list.get(j)).onAdColonyV4VCReward(localAdColonyV4VCReward);
        }
      }
    }
    
    public void notify_listeners(boolean paramBoolean, String paramString, int paramInt)
    {
      if (paramBoolean) {}
      for (;;)
      {
        sendMessage(obtainMessage(paramInt, paramString));
        return;
        paramString = null;
      }
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.jirbo.adcolony.ADC
 * JD-Core Version:    0.7.0.1
 */