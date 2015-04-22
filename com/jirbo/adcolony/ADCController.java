package com.jirbo.adcolony;

import android.app.Activity;
import android.content.Intent;
import java.util.ArrayList;

class ADCController
{
  ADCAdManager ad_manager = new ADCAdManager(this);
  ADCConfiguration configuration = new ADCConfiguration(this);
  boolean dispatching_events;
  ArrayList<ADCEvent> event_queue = new ArrayList();
  ADCMediaManager media_manager = new ADCMediaManager(this);
  volatile boolean monitor_thread_active;
  ArrayList<ADCEvent> pending_events = new ArrayList();
  ADCPlayHistory play_history = new ADCPlayHistory(this);
  ADCReportingManager reporting_manager = new ADCReportingManager(this);
  ADCSessionManager session_manager = new ADCSessionManager(this);
  ADCStorage storage = new ADCStorage(this);
  ADCUtil.Stopwatch time_waiting_for_open_udid = new ADCUtil.Stopwatch();
  ADCZoneStateManager zone_state_manager = new ADCZoneStateManager(this);
  
  void configure(String paramString1, String paramString2, String[] paramArrayOfString)
  {
    try
    {
      set_log_level(ADC.log_level);
      ADCLog.info.print("==== Configuring AdColony ").print(this.configuration.sdk_version).println(" ====");
      ADCLog.dev.print("package name: ").println(ADCUtil.package_name());
      this.configuration.app_id = paramString2;
      this.configuration.zone_ids = paramArrayOfString;
      this.configuration.parse_client_options(paramString1);
      this.time_waiting_for_open_udid.restart();
      return;
    }
    catch (RuntimeException localRuntimeException)
    {
      for (;;)
      {
        ADC.on_fatal_error(localRuntimeException);
      }
    }
    finally {}
  }
  
  void dispatch_events()
  {
    if (this.dispatching_events) {}
    while (!ADC.is_ready()) {
      return;
    }
    for (;;)
    {
      int i;
      try
      {
        if ((!this.dispatching_events) || ((!this.monitor_thread_active) && (this.event_queue.size() > 0)))
        {
          this.dispatching_events = true;
          this.pending_events.addAll(this.event_queue);
          this.event_queue.clear();
          i = 0;
          if (i < this.pending_events.size())
          {
            if (this.pending_events.get(i) == null) {
              break label144;
            }
            ((ADCEvent)this.pending_events.get(i)).dispatch();
            break label144;
          }
          this.pending_events.clear();
          continue;
        }
        this.dispatching_events = false;
      }
      catch (RuntimeException localRuntimeException)
      {
        this.dispatching_events = false;
        this.pending_events.clear();
        this.event_queue.clear();
        ADC.on_fatal_error(localRuntimeException);
        return;
      }
      return;
      label144:
      i++;
    }
  }
  
  String find_v4vc_zone()
  {
    try
    {
      String str = this.ad_manager.find_v4vc_zone();
      return str;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  String find_video_zone()
  {
    try
    {
      String str = this.ad_manager.find_video_zone();
      return str;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  int get_Integer(String paramString)
  {
    try
    {
      int j = this.configuration.properties.get_Integer(paramString);
      i = j;
    }
    catch (RuntimeException localRuntimeException)
    {
      for (;;)
      {
        ADC.on_fatal_error(localRuntimeException);
        int i = 0;
      }
    }
    finally {}
    return i;
  }
  
  boolean get_Logical(String paramString)
  {
    try
    {
      boolean bool2 = this.configuration.properties.get_Logical(paramString);
      bool1 = bool2;
    }
    catch (RuntimeException localRuntimeException)
    {
      for (;;)
      {
        ADC.on_fatal_error(localRuntimeException);
        boolean bool1 = false;
      }
    }
    finally {}
    return bool1;
  }
  
  double get_Real(String paramString)
  {
    try
    {
      double d2 = this.configuration.properties.get_Real(paramString);
      d1 = d2;
    }
    catch (RuntimeException localRuntimeException)
    {
      for (;;)
      {
        ADC.on_fatal_error(localRuntimeException);
        double d1 = 0.0D;
      }
    }
    finally {}
    return d1;
  }
  
  String get_String(String paramString)
  {
    try
    {
      String str2 = this.configuration.properties.get_String(paramString);
      str1 = str2;
    }
    catch (RuntimeException localRuntimeException)
    {
      for (;;)
      {
        ADC.on_fatal_error(localRuntimeException);
        String str1 = null;
      }
    }
    finally {}
    return str1;
  }
  
  int get_reward_credit(String paramString)
  {
    try
    {
      int i = this.play_history.get_reward_credit(paramString);
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  ADCManifest.Zone get_zone_info(String paramString)
  {
    try
    {
      ADCManifest.Zone localZone = this.ad_manager.app.zones.find(paramString);
      return localZone;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  boolean is_v4vc_ad_available(String paramString)
  {
    try
    {
      boolean bool = is_v4vc_ad_available(paramString, false, true);
      return bool;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  boolean is_v4vc_ad_available(String paramString, boolean paramBoolean1, boolean paramBoolean2)
  {
    for (;;)
    {
      try
      {
        boolean bool2 = ADC.is_ready();
        bool1 = false;
        if (bool2) {
          continue;
        }
      }
      catch (RuntimeException localRuntimeException)
      {
        boolean bool3;
        boolean bool4;
        ADC.on_fatal_error(localRuntimeException);
        boolean bool1 = false;
        continue;
      }
      finally {}
      return bool1;
      bool3 = this.ad_manager.is_ad_available(paramString, paramBoolean1);
      bool1 = false;
      if (bool3)
      {
        bool4 = this.ad_manager.app.zones.find(paramString).is_v4vc_zone(paramBoolean2);
        bool1 = bool4;
      }
    }
  }
  
  boolean is_video_ad_available(String paramString)
  {
    try
    {
      boolean bool = is_video_ad_available(paramString, false, true);
      return bool;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  boolean is_video_ad_available(String paramString, boolean paramBoolean1, boolean paramBoolean2)
  {
    for (;;)
    {
      try
      {
        boolean bool2 = ADC.is_ready();
        bool1 = false;
        if (bool2) {
          continue;
        }
      }
      catch (RuntimeException localRuntimeException)
      {
        boolean bool3;
        boolean bool4;
        ADC.on_fatal_error(localRuntimeException);
        boolean bool1 = false;
        continue;
      }
      finally {}
      return bool1;
      bool3 = this.ad_manager.is_ad_available(paramString, paramBoolean1);
      bool1 = false;
      if (bool3)
      {
        bool4 = this.ad_manager.app.zones.find(paramString).is_video_zone(paramBoolean2);
        bool1 = bool4;
      }
    }
  }
  
  boolean launch_video(AdColonyAd paramAdColonyAd)
  {
    for (;;)
    {
      try
      {
        if (this.configuration.current_zone.check_for_skip_due_to_interval())
        {
          ADC.current_ad.status = 3;
          bool = false;
          return bool;
        }
        on_video_start(paramAdColonyAd);
        ADCVideo.reset();
        if (ADC.is_tablet)
        {
          ADCLog.dev.println("Launching AdColonyOverlay");
          Intent localIntent2 = new Intent(ADC.activity(), AdColonyOverlay.class);
          ADC.activity().startActivity(localIntent2);
        }
        else
        {
          ADCLog.dev.println("Launching AdColonyFullscreen");
          Intent localIntent1 = new Intent(ADC.activity(), AdColonyFullscreen.class);
          ADC.activity().startActivity(localIntent1);
        }
      }
      finally {}
      boolean bool = true;
    }
  }
  
  void on_resume()
  {
    this.monitor_thread_active = true;
    new ADCEvent(this)
    {
      void dispatch()
      {
        this.controller.session_manager.on_resume();
      }
    };
  }
  
  void on_stop()
  {
    new ADCEvent(this)
    {
      void dispatch()
      {
        this.controller.session_manager.on_stop();
      }
    };
  }
  
  void on_suspend()
  {
    this.monitor_thread_active = false;
    new ADCEvent(this)
    {
      void dispatch()
      {
        this.controller.session_manager.on_suspend();
      }
    };
  }
  
  void on_v4vc_result(boolean paramBoolean, String paramString, int paramInt)
  {
    try
    {
      ADC.v4vc_results_handler.notify_listeners(paramBoolean, paramString, paramInt);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  void on_video_finish(boolean paramBoolean, final AdColonyAd paramAdColonyAd)
  {
    if (paramAdColonyAd == null) {
      return;
    }
    for (;;)
    {
      try
      {
        on_video_progress(1.0D, paramAdColonyAd);
        if ((paramBoolean) || (!paramAdColonyAd.is_v4vc())) {
          break;
        }
        paramAdColonyAd.zone_info.advance_play_index();
        this.play_history.add_play_event(paramAdColonyAd.zone_id, paramAdColonyAd.ad_info.ad_id);
        AdColonyV4VCAd localAdColonyV4VCAd = (AdColonyV4VCAd)ADC.current_ad;
        final String str = localAdColonyV4VCAd.getRewardName();
        final int i = localAdColonyV4VCAd.getRewardAmount();
        j = 1;
        int k = localAdColonyV4VCAd.getViewsPerReward();
        if (k > 1)
        {
          int m = 1 + this.play_history.get_reward_credit(localAdColonyV4VCAd.getRewardName());
          if (m >= k)
          {
            m = 0;
            this.play_history.set_reward_credit(localAdColonyV4VCAd.getRewardName(), m);
          }
        }
        else
        {
          if (j == 0) {
            break;
          }
          if (localAdColonyV4VCAd.zone_info.v4vc.client_side) {
            on_v4vc_result(true, str, i);
          }
          new ADCEvent(this)
          {
            void dispatch()
            {
              ADCData.Table localTable = new ADCData.Table();
              localTable.set("v4vc_name", str);
              localTable.set("v4vc_amount", i);
              this.controller.reporting_manager.track_ad_event("reward_v4vc", localTable, paramAdColonyAd);
            }
          };
          break;
        }
      }
      finally {}
      int j = 0;
    }
  }
  
  void on_video_progress(final double paramDouble, AdColonyAd paramAdColonyAd)
  {
    new ADCEvent(this)
    {
      void dispatch()
      {
        this.controller.reporting_manager.track_video_progress(paramDouble, this.val$ad);
      }
    };
  }
  
  void on_video_start(final AdColonyAd paramAdColonyAd)
  {
    try
    {
      this.configuration.current_progress = 0.0D;
      ADCLog.dev.println("Tracking ad event - start");
      ADCZoneState localADCZoneState = paramAdColonyAd.zone_info.state;
      localADCZoneState.session_play_count = (1 + localADCZoneState.session_play_count);
      if (!paramAdColonyAd.is_v4vc())
      {
        paramAdColonyAd.zone_info.advance_play_index();
        this.play_history.add_play_event(paramAdColonyAd.zone_id, paramAdColonyAd.ad_info.ad_id);
      }
      new ADCEvent(this)
      {
        void dispatch()
        {
          if ((!AdColony.isZoneV4VC(paramAdColonyAd.zone_id)) && (paramAdColonyAd.view_format.equals("native")))
          {
            ADCController.this.track_ad_event("native_start", "{\"ad_slot\":" + paramAdColonyAd.zone_info.state.session_play_count + ", \"replay\":false}", paramAdColonyAd);
            return;
          }
          ADCController.this.track_ad_event("start", "{\"ad_slot\":" + paramAdColonyAd.zone_info.state.session_play_count + ", \"replay\":" + paramAdColonyAd.replay + "}", paramAdColonyAd);
        }
      };
      return;
    }
    finally {}
  }
  
  void prepare_v4vc_ad(AdColonyV4VCAd paramAdColonyV4VCAd)
  {
    try
    {
      this.configuration.prepare_v4vc_ad(paramAdColonyV4VCAd.zone_id);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  void prepare_video_ad(AdColonyInterstitialAd paramAdColonyInterstitialAd)
  {
    try
    {
      this.configuration.prepare_video_ad(paramAdColonyInterstitialAd.zone_id);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  void prepare_video_ad(AdColonyVideoAd paramAdColonyVideoAd)
  {
    try
    {
      this.configuration.prepare_video_ad(paramAdColonyVideoAd.zone_id);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  void queue_event(ADCEvent paramADCEvent)
  {
    synchronized (this.event_queue)
    {
      if (!ADC.is_ready()) {
        return;
      }
      this.event_queue.add(paramADCEvent);
      if (!this.monitor_thread_active) {
        update();
      }
      return;
    }
  }
  
  void set_log_level(int paramInt)
  {
    ADC.set_log_level(paramInt);
  }
  
  void set_reward_credit(String paramString, int paramInt)
  {
    try
    {
      this.play_history.set_reward_credit(paramString, paramInt);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  boolean show_v4vc_ad(AdColonyV4VCAd paramAdColonyV4VCAd)
  {
    for (;;)
    {
      try
      {
        ADC.current_ad = paramAdColonyV4VCAd;
        str = paramAdColonyV4VCAd.zone_id;
        boolean bool2 = is_v4vc_ad_available(str);
        bool1 = false;
        if (bool2) {
          continue;
        }
      }
      catch (RuntimeException localRuntimeException)
      {
        String str;
        boolean bool3;
        ADC.on_fatal_error(localRuntimeException);
        boolean bool1 = false;
        continue;
      }
      finally {}
      return bool1;
      ADCLog.dev.print("Showing v4vc for zone ").println(str);
      prepare_v4vc_ad(paramAdColonyV4VCAd);
      bool3 = launch_video(paramAdColonyV4VCAd);
      bool1 = bool3;
    }
  }
  
  boolean show_video_ad(AdColonyInterstitialAd paramAdColonyInterstitialAd)
  {
    for (;;)
    {
      try
      {
        ADC.current_ad = paramAdColonyInterstitialAd;
        str = paramAdColonyInterstitialAd.zone_id;
        boolean bool2 = is_video_ad_available(str);
        bool1 = false;
        if (bool2) {
          continue;
        }
      }
      catch (RuntimeException localRuntimeException)
      {
        String str;
        boolean bool3;
        ADC.on_fatal_error(localRuntimeException);
        boolean bool1 = false;
        continue;
      }
      finally {}
      return bool1;
      ADCLog.dev.print("Showing ad for zone ").println(str);
      prepare_video_ad(paramAdColonyInterstitialAd);
      bool3 = launch_video(paramAdColonyInterstitialAd);
      bool1 = bool3;
    }
  }
  
  boolean show_video_ad(AdColonyVideoAd paramAdColonyVideoAd)
  {
    for (;;)
    {
      try
      {
        ADC.current_ad = paramAdColonyVideoAd;
        str = paramAdColonyVideoAd.zone_id;
        boolean bool2 = is_video_ad_available(str);
        bool1 = false;
        if (bool2) {
          continue;
        }
      }
      catch (RuntimeException localRuntimeException)
      {
        String str;
        boolean bool3;
        ADC.on_fatal_error(localRuntimeException);
        boolean bool1 = false;
        continue;
      }
      finally {}
      return bool1;
      ADCLog.dev.print("Showing ad for zone ").println(str);
      prepare_video_ad(paramAdColonyVideoAd);
      bool3 = launch_video(paramAdColonyVideoAd);
      bool1 = bool3;
    }
  }
  
  void track_ad_event(final String paramString1, final String paramString2, final AdColonyAd paramAdColonyAd)
  {
    new ADCEvent(this)
    {
      void dispatch()
      {
        this.controller.reporting_manager.track_ad_event(paramString1, ADCJSON.parse_Table(paramString2), paramAdColonyAd);
      }
    };
  }
  
  void track_app_event(final String paramString1, final String paramString2)
  {
    new ADCEvent(this)
    {
      void dispatch()
      {
        this.controller.reporting_manager.track_app_event(paramString1, ADCJSON.parse_Table(paramString2));
      }
    };
  }
  
  /* Error */
  void update()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: invokestatic 478	com/jirbo/adcolony/ADC:disabled	()Z
    //   5: istore_2
    //   6: iload_2
    //   7: ifeq +6 -> 13
    //   10: aload_0
    //   11: monitorexit
    //   12: return
    //   13: aload_0
    //   14: invokevirtual 480	com/jirbo/adcolony/ADCController:dispatch_events	()V
    //   17: getstatic 483	com/jirbo/adcolony/ADC:configured	Z
    //   20: ifne +38 -> 58
    //   23: invokestatic 488	com/jirbo/adcolony/ADCDevice:open_udid	()Ljava/lang/String;
    //   26: ifnonnull +17 -> 43
    //   29: aload_0
    //   30: getfield 87	com/jirbo/adcolony/ADCController:time_waiting_for_open_udid	Lcom/jirbo/adcolony/ADCUtil$Stopwatch;
    //   33: invokevirtual 492	com/jirbo/adcolony/ADCUtil$Stopwatch:elapsed_seconds	()D
    //   36: ldc2_w 493
    //   39: dcmpl
    //   40: ifle +14 -> 54
    //   43: aload_0
    //   44: getfield 40	com/jirbo/adcolony/ADCController:configuration	Lcom/jirbo/adcolony/ADCConfiguration;
    //   47: invokevirtual 496	com/jirbo/adcolony/ADCConfiguration:configure	()V
    //   50: iconst_1
    //   51: putstatic 483	com/jirbo/adcolony/ADC:configured	Z
    //   54: iconst_1
    //   55: putstatic 499	com/jirbo/adcolony/ADC:active	Z
    //   58: aload_0
    //   59: getfield 45	com/jirbo/adcolony/ADCController:ad_manager	Lcom/jirbo/adcolony/ADCAdManager;
    //   62: invokevirtual 500	com/jirbo/adcolony/ADCAdManager:update	()V
    //   65: aload_0
    //   66: getfield 50	com/jirbo/adcolony/ADCController:media_manager	Lcom/jirbo/adcolony/ADCMediaManager;
    //   69: invokevirtual 501	com/jirbo/adcolony/ADCMediaManager:update	()V
    //   72: aload_0
    //   73: getfield 60	com/jirbo/adcolony/ADCController:session_manager	Lcom/jirbo/adcolony/ADCSessionManager;
    //   76: invokevirtual 502	com/jirbo/adcolony/ADCSessionManager:update	()V
    //   79: aload_0
    //   80: getfield 55	com/jirbo/adcolony/ADCController:reporting_manager	Lcom/jirbo/adcolony/ADCReportingManager;
    //   83: invokevirtual 503	com/jirbo/adcolony/ADCReportingManager:update	()V
    //   86: aload_0
    //   87: getfield 75	com/jirbo/adcolony/ADCController:play_history	Lcom/jirbo/adcolony/ADCPlayHistory;
    //   90: invokevirtual 504	com/jirbo/adcolony/ADCPlayHistory:update	()V
    //   93: aload_0
    //   94: getfield 70	com/jirbo/adcolony/ADCController:zone_state_manager	Lcom/jirbo/adcolony/ADCZoneStateManager;
    //   97: invokevirtual 505	com/jirbo/adcolony/ADCZoneStateManager:update	()V
    //   100: goto -90 -> 10
    //   103: astore_3
    //   104: aload_3
    //   105: invokestatic 152	com/jirbo/adcolony/ADC:on_fatal_error	(Ljava/lang/RuntimeException;)V
    //   108: goto -98 -> 10
    //   111: astore_1
    //   112: aload_0
    //   113: monitorexit
    //   114: aload_1
    //   115: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	116	0	this	ADCController
    //   111	4	1	localObject	Object
    //   5	2	2	bool	boolean
    //   103	2	3	localRuntimeException	RuntimeException
    // Exception table:
    //   from	to	target	type
    //   13	43	103	java/lang/RuntimeException
    //   43	54	103	java/lang/RuntimeException
    //   54	58	103	java/lang/RuntimeException
    //   58	100	103	java/lang/RuntimeException
    //   2	6	111	finally
    //   13	43	111	finally
    //   43	54	111	finally
    //   54	58	111	finally
    //   58	100	111	finally
    //   104	108	111	finally
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.jirbo.adcolony.ADCController
 * JD-Core Version:    0.7.0.1
 */