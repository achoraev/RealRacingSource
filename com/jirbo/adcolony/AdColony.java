package com.jirbo.adcolony;

import android.app.Activity;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Handler;
import android.view.ViewGroup;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import java.util.ArrayList;
import java.util.HashMap;

public class AdColony
{
  static boolean advertising_id_ready;
  boolean block = false;
  
  public static Activity activity()
  {
    return ADC.activity();
  }
  
  public static void addAdAvailabilityListener(AdColonyAdAvailabilityListener paramAdColonyAdAvailabilityListener)
  {
    if (ADC.ad_availability_listener_list.contains(paramAdColonyAdAvailabilityListener)) {
      return;
    }
    ADC.ad_availability_listener_list.add(paramAdColonyAdAvailabilityListener);
  }
  
  public static void addV4VCListener(AdColonyV4VCListener paramAdColonyV4VCListener)
  {
    if (ADC.v4vc_listener_list.contains(paramAdColonyV4VCListener)) {
      return;
    }
    ADC.v4vc_listener_list.add(paramAdColonyV4VCListener);
  }
  
  public static void cancelVideo()
  {
    if (ADC.current_video != null)
    {
      ADC.current_video.finish();
      ADC.destroyed = true;
      ADC.end_card_finished_handler.notify_canceled(null);
    }
  }
  
  public static void configure(Activity paramActivity, String paramString1, String paramString2, String... paramVarArgs)
  {
    advertising_id_ready = false;
    Handler localHandler;
    Runnable local1;
    if (Build.VERSION.SDK_INT >= 11)
    {
      new AdvertisingIdTask(paramActivity).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
      ADC.native_ad_view_list.clear();
      localHandler = new Handler();
      local1 = new Runnable()
      {
        public void run()
        {
          ADC.block = false;
        }
      };
      if ((ADC.block) && (!ADC.disable_block)) {
        break label156;
      }
      if (!ADC.user_disabled) {
        break label93;
      }
    }
    for (;;)
    {
      return;
      new AdvertisingIdTask(paramActivity).execute(new Void[0]);
      break;
      label93:
      if (paramString2 == null)
      {
        ADC.on_fatal_error("Null App ID - disabling AdColony.");
        return;
      }
      if (paramVarArgs == null)
      {
        ADC.on_fatal_error("Null Zone IDs array - disabling AdColony.");
        return;
      }
      if (paramVarArgs.length == 0)
      {
        ADC.on_fatal_error("No Zone IDs provided - disabling AdColony.");
        return;
      }
      ADC.initialize(paramActivity);
      ADC.controller.configure(paramString1, paramString2, paramVarArgs);
      ADC.initialized = true;
      ADC.block = true;
      localHandler.postDelayed(local1, 120000L);
      label156:
      if (ADC.current_video == null) {
        ADC.show = true;
      }
      ADC.v4vc_listener_list.clear();
      ADC.ad_availability_listener_list.clear();
      ADC.ad_availability_map = new HashMap();
      for (int i = 0; i < paramVarArgs.length; i++) {
        ADC.ad_availability_map.put(paramVarArgs[i], Boolean.valueOf(false));
      }
    }
  }
  
  public static void disable()
  {
    ADC.user_disabled = true;
  }
  
  public static void disableDECOverride()
  {
    ADC.force_dec_url = null;
  }
  
  public static String getCustomID()
  {
    return ADC.controller.configuration.custom_id;
  }
  
  public static String getDeviceID()
  {
    return ADC.controller.configuration.device_id;
  }
  
  public static int getRemainingV4VCForZone(String paramString)
  {
    if ((ADC.controller == null) || (ADC.controller.play_history == null) || (ADC.controller.ad_manager == null) || (ADC.controller.ad_manager.app == null) || (ADC.controller.ad_manager.app.zones == null)) {
      return ADCLog.info.int_fail("getRemainingV4VCForZone called before AdColony has finished configuring.");
    }
    ADCManifest.Zone localZone = ADC.controller.ad_manager.app.zones.find(paramString);
    if (!localZone.v4vc.enabled) {
      return ADCLog.info.int_fail("getRemainingV4VCForZone called with non-V4VC zone.");
    }
    return localZone.v4vc.limits.daily_play_cap - ADC.controller.play_history.zone_daily_play_count(paramString);
  }
  
  public static void get_images(String paramString)
  {
    ADC.controller.configuration.prepare_video_ad(paramString);
  }
  
  public static boolean isTablet()
  {
    return ADCDevice.is_tablet();
  }
  
  public static boolean isZoneNative(String paramString)
  {
    if (ADC.controller == null) {}
    for (;;)
    {
      return false;
      if ((ADC.controller.ad_manager != null) && (ADC.controller.ad_manager.app != null) && (ADC.controller.ad_manager.app.zones != null) && (ADC.controller.ad_manager.app.zones.find(paramString) != null) && (ADC.controller.ad_manager.app.zones.find(paramString).ads != null) && (ADC.controller.ad_manager.app.zones.find(paramString).ads.data != null)) {
        for (int i = 0; i < ADC.controller.ad_manager.app.zones.find(paramString).ads.data.size(); i++) {
          if (ADC.controller.ad_manager.app.zones.find(paramString).ads.get(i).native_ad.enabled) {
            return true;
          }
        }
      }
    }
  }
  
  public static boolean isZoneV4VC(String paramString)
  {
    if (ADC.controller == null) {}
    while ((ADC.controller.ad_manager == null) || (ADC.controller.ad_manager.app == null) || (ADC.controller.ad_manager.app.zones == null)) {
      return false;
    }
    return ADC.controller.ad_manager.is_zone_v4vc(paramString, false);
  }
  
  public static void onBackPressed()
  {
    if ((ADC.current_dialog != null) && ((ADC.current_dialog instanceof ADCV4VCConfirmationDialog)))
    {
      ((ViewGroup)ADC.current_dialog.getParent()).removeView(ADC.current_dialog);
      ADC.show = true;
      ADC.current_dialog.listener.on_dialog_finished(false);
      for (int i = 0; i < ADC.bitmaps.size(); i++) {
        ((Bitmap)ADC.bitmaps.get(i)).recycle();
      }
      ADC.bitmaps.clear();
      ADC.current_dialog = null;
    }
  }
  
  public static void pause()
  {
    ADCLog.info.println("[ADC] AdColony pause called.");
    ADC.dont_resume = true;
    for (int i = 0; i < ADC.native_ad_view_list.size(); i++) {
      if (ADC.native_ad_view_list.get(i) != null)
      {
        AdColonyNativeAdView localAdColonyNativeAdView = (AdColonyNativeAdView)ADC.native_ad_view_list.get(i);
        localAdColonyNativeAdView.paused = true;
        if ((localAdColonyNativeAdView.mp != null) && (!localAdColonyNativeAdView.finished) && (localAdColonyNativeAdView.mp.isPlaying()))
        {
          if (ADC.show) {
            localAdColonyNativeAdView.static_image_view.setVisibility(0);
          }
          localAdColonyNativeAdView.system_pause();
        }
      }
    }
  }
  
  public static void removeAdAvailabilityListener(AdColonyAdAvailabilityListener paramAdColonyAdAvailabilityListener)
  {
    ADC.ad_availability_listener_list.remove(paramAdColonyAdAvailabilityListener);
  }
  
  public static void removeV4VCListener(AdColonyV4VCListener paramAdColonyV4VCListener)
  {
    ADC.v4vc_listener_list.remove(paramAdColonyV4VCListener);
  }
  
  public static void resume(Activity paramActivity)
  {
    ADCLog.info.println("[ADC] AdColony resume called.");
    ADC.dont_resume = false;
    ADC.set_activity(paramActivity);
    ADC.finishing = false;
    if (paramActivity == null)
    {
      ADCLog.error.println("Activity reference is null. Disabling AdColony.");
      disable();
      return;
    }
    new Thread(new Runnable()
    {
      public void run()
      {
        while (!this.val$a.hasWindowFocus()) {
          try
          {
            Thread.sleep(50L);
          }
          catch (Exception localException) {}
        }
        this.val$a.runOnUiThread(new Runnable()
        {
          public void run()
          {
            for (int i = 0; i < ADC.native_ad_view_list.size(); i++)
            {
              AdColonyNativeAdView localAdColonyNativeAdView = (AdColonyNativeAdView)ADC.native_ad_view_list.get(i);
              if ((localAdColonyNativeAdView != null) && (ADC.activity() == localAdColonyNativeAdView.context) && (!localAdColonyNativeAdView.finished))
              {
                localAdColonyNativeAdView.paused = false;
                localAdColonyNativeAdView.invalidate();
                if (localAdColonyNativeAdView.static_image_view != null)
                {
                  localAdColonyNativeAdView.static_image_view.visible = false;
                  localAdColonyNativeAdView.static_image_view.invalidate();
                }
              }
            }
          }
        });
      }
    }).start();
    ADC.resume_from_ad = false;
  }
  
  public static void setCustomID(String paramString)
  {
    if (!paramString.equals(ADC.controller.configuration.custom_id))
    {
      ADC.controller.configuration.custom_id = paramString;
      ADC.block = false;
      ADC.controller.ad_manager.attempted_load = true;
      ADC.controller.ad_manager.is_configured = false;
      ADC.controller.ad_manager.needs_refresh = true;
    }
  }
  
  public static void setDeviceID(String paramString)
  {
    if (!paramString.equals(ADC.controller.configuration.device_id))
    {
      ADC.controller.configuration.device_id = paramString;
      ADC.block = false;
      ADC.controller.ad_manager.attempted_load = true;
      ADC.controller.ad_manager.is_configured = false;
      ADC.controller.ad_manager.needs_refresh = true;
    }
  }
  
  public static String statusForZone(String paramString)
  {
    if ((ADC.controller == null) || (ADC.controller.ad_manager == null) || (ADC.controller.ad_manager.app == null) || (ADC.controller.ad_manager.app.zones == null)) {
      return "unknown";
    }
    ADCManifest.Zone localZone = ADC.controller.ad_manager.app.zones.find(paramString);
    if (localZone != null)
    {
      if (!localZone.enabled) {
        return "off";
      }
      if ((localZone.active) && (ADC.controller.ad_manager.is_ad_available_errorless(paramString, true))) {
        return "active";
      }
      return "loading";
    }
    if (!ADC.configured) {
      return "unknown";
    }
    return "invalid";
  }
  
  private static class AdvertisingIdTask
    extends AsyncTask<Void, Void, Void>
  {
    Activity activity;
    String advertising_id = "";
    boolean limit_ad_tracking;
    
    AdvertisingIdTask(Activity paramActivity)
    {
      this.activity = paramActivity;
    }
    
    protected Void doInBackground(Void... paramVarArgs)
    {
      try
      {
        AdvertisingIdClient.Info localInfo = AdvertisingIdClient.getAdvertisingIdInfo(this.activity);
        this.advertising_id = localInfo.getId();
        this.limit_ad_tracking = localInfo.isLimitAdTrackingEnabled();
        return null;
      }
      catch (NoClassDefFoundError localNoClassDefFoundError)
      {
        ADCLog.error.println("Google Play Services SDK not installed! Collecting Android Id instead of Advertising Id.");
        return null;
      }
      catch (Exception localException)
      {
        ADCLog.error.println("Advertising Id not available! Collecting Android Id instead of Advertising Id.");
        localException.printStackTrace();
      }
      return null;
    }
    
    protected void onPostExecute(Void paramVoid)
    {
      ADCDevice.advertising_id = this.advertising_id;
      ADCDevice.limit_ad_tracking = this.limit_ad_tracking;
      AdColony.advertising_id_ready = true;
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.jirbo.adcolony.AdColony
 * JD-Core Version:    0.7.0.1
 */