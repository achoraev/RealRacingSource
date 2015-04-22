package com.jirbo.adcolony;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.webkit.ConsoleMessage;
import android.webkit.GeolocationPermissions.Callback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import java.lang.reflect.Method;
import java.util.ArrayList;

class ADCVideoHUD
  extends View
  implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener
{
  static float[] widths = new float[80];
  ADCImage[] active_buttons = new ADCImage[4];
  ADCVideo activity;
  float angle_per_sec;
  ADCImage background;
  Rect bounds = new Rect();
  ADCImage browser_icon;
  String[] button_labels = new String[4];
  Paint button_text_paint = new Paint(1);
  boolean can_press = true;
  Canvas canvas;
  float center_x;
  float center_y;
  Paint circle_paint = new Paint(1);
  boolean close_pressed;
  int close_xpos;
  int close_ypos;
  float cur_angle;
  ADCUtil.Timer dissolve_timer;
  long end_card_loading_finish;
  long end_card_loading_start;
  WebView end_card_web_view;
  boolean engagement;
  boolean engagement_delay_met;
  int engagement_delay_ms;
  boolean engagement_pressed;
  String engagement_text;
  String engagement_url;
  boolean error;
  boolean first_resize = true;
  boolean first_update = true;
  boolean graceful_fail = true;
  float height;
  double hud_scale = 1.0D;
  double image_scale = 1.0D;
  ADCImage img_close_button;
  ADCImage img_close_button_down;
  ADCImage img_engagement_button_down;
  ADCImage img_engagement_button_normal;
  ADCImage img_reload_button;
  ADCImage img_reload_button_down;
  ADCImage img_skip_button;
  ADCImage img_skip_button_down;
  boolean is_html5;
  boolean is_static;
  int keyboard_offset = 0;
  int left_margin = 99;
  View loading_view;
  ADCMRAIDHandler mraid_handler;
  ADCImage[] normal_buttons = new ADCImage[4];
  int offset;
  Handler on_button_press_handler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      if (ADCVideoHUD.this.activity.isFinishing()) {}
      while (ADCVideoHUD.this.activity.video_view == null) {
        return;
      }
      ADCVideoHUD.this.onButton(paramAnonymousMessage.what);
    }
  };
  int original_bg_height;
  int original_bg_width;
  RectF oval = new RectF();
  Paint paint = new Paint();
  float radius;
  int recent_selected_button;
  boolean reload_pressed;
  int reload_xpos;
  int reload_ypos;
  boolean resize_images = true;
  int selected_button;
  boolean show_version;
  boolean skip_delay_met;
  int skip_delay_ms;
  boolean skippable;
  boolean stay_at_zero;
  Paint text_paint = new Paint(1);
  boolean up_state = true;
  UpdateHandler update_handler = new UpdateHandler();
  String version_number = ADC.controller.configuration.sdk_version;
  Paint version_paint = new Paint(1);
  int video_duration_ms;
  boolean web_layout_offset;
  float width;
  
  ADCVideoHUD(ADCVideo paramADCVideo)
  {
    super(paramADCVideo);
    this.activity = paramADCVideo;
    this.skippable = ADC.controller.configuration.skippable;
    if (ADC.current_ad != null)
    {
      this.skippable |= ADC.current_ad.ad_info.video.skip_video.enabled;
      ADC.current_ad.current_progress = ADC.current_ad.force_current_progress;
    }
    this.is_html5 = ADC.end_card_is_html5;
    if (ADC.force_dec_url != null) {
      ADC.end_card_url = ADC.force_dec_url;
    }
    boolean bool;
    if ((ADC.current_ad != null) && (ADC.current_ad.ad_info.companion_ad.enabled))
    {
      if (!this.is_html5)
      {
        bool = true;
        this.is_static = bool;
      }
    }
    else
    {
      if (!this.is_static) {
        break label1032;
      }
      this.background = new ADCImage(ADC.get_String("end_card_filepath"));
      this.original_bg_width = this.background.width;
      this.original_bg_height = this.background.height;
      if (this.original_bg_width == 0) {
        this.original_bg_width = 480;
      }
      if (this.original_bg_height == 0) {
        this.original_bg_height = 320;
      }
      this.normal_buttons[0] = new ADCImage(ADC.get_String("info_image_normal"));
      this.normal_buttons[1] = new ADCImage(ADC.get_String("download_image_normal"));
      this.normal_buttons[2] = new ADCImage(ADC.get_String("replay_image_normal"));
      this.normal_buttons[3] = new ADCImage(ADC.get_String("continue_image_normal"));
      this.active_buttons[0] = new ADCImage(ADC.get_String("info_image_down"), true);
      this.active_buttons[1] = new ADCImage(ADC.get_String("download_image_down"), true);
      this.active_buttons[2] = new ADCImage(ADC.get_String("replay_image_down"), true);
      this.active_buttons[3] = new ADCImage(ADC.get_String("continue_image_down"), true);
      this.button_labels[0] = "Info";
      this.button_labels[1] = "Download";
      this.button_labels[2] = "Replay";
      this.button_labels[3] = "Continue";
    }
    for (;;)
    {
      if (this.skippable)
      {
        this.img_skip_button = new ADCImage(ADC.get_String("skip_video_image_normal"));
        this.img_skip_button_down = new ADCImage(ADC.get_String("skip_video_image_down"));
        this.skip_delay_ms = (1000 * ADC.get_Integer("skip_delay"));
      }
      this.circle_paint.setStyle(Paint.Style.STROKE);
      float f = 2.0F * paramADCVideo.getResources().getDisplayMetrics().density;
      if (f > 6.0F) {
        f = 6.0F;
      }
      if (f < 4.0F) {}
      this.circle_paint.setStrokeWidth(2.0F * paramADCVideo.getResources().getDisplayMetrics().density);
      this.circle_paint.setColor(-3355444);
      this.stay_at_zero = false;
      this.engagement = false;
      if (ADC.current_ad != null) {
        this.engagement = ADC.current_ad.ad_info.video.in_video_engagement.enabled;
      }
      if (this.engagement)
      {
        this.img_engagement_button_normal = new ADCImage(ADC.get_String("engagement_image_normal"));
        this.img_engagement_button_down = new ADCImage(ADC.get_String("engagement_image_down"));
        this.engagement_url = ADC.current_ad.ad_info.video.in_video_engagement.click_action;
        this.engagement_text = ADC.current_ad.ad_info.video.in_video_engagement.label;
        this.engagement_delay_ms = (1000 * ADC.get_Integer("engagement_delay"));
        if (this.engagement_text.equals("")) {
          this.engagement_text = "Learn More";
        }
        if ((this.img_engagement_button_normal == null) || (this.img_engagement_button_down == null)) {
          this.engagement = false;
        }
      }
      if (ADCVideo.video_finished) {
        on_video_finish();
      }
      this.paint.setColor(-1);
      this.button_text_paint.setTextSize(24.0F);
      this.button_text_paint.setColor(-16777216);
      this.text_paint.setColor(-3355444);
      this.text_paint.setTextSize(20.0F);
      this.text_paint.setTextAlign(Paint.Align.CENTER);
      this.version_paint.setTextSize(20.0F);
      this.version_paint.setColor(-1);
      label1032:
      try
      {
        Class localClass = getClass();
        Class[] arrayOfClass = new Class[2];
        arrayOfClass[0] = Integer.TYPE;
        arrayOfClass[1] = Paint.class;
        Method localMethod = localClass.getMethod("setLayerType", arrayOfClass);
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = Integer.valueOf(1);
        arrayOfObject[1] = null;
        localMethod.invoke(this, arrayOfObject);
        return;
      }
      catch (Exception localException) {}
      bool = false;
      break;
      if (this.is_html5)
      {
        this.img_reload_button = new ADCImage(ADC.get_String("reload_image_normal"));
        this.img_close_button = new ADCImage(ADC.get_String("close_image_normal"));
        this.img_close_button_down = new ADCImage(ADC.get_String("close_image_down"));
        this.img_reload_button_down = new ADCImage(ADC.get_String("reload_image_down"));
        this.browser_icon = new ADCImage(ADC.get_String("browser_icon"));
        this.loading_view = new LoadingView(paramADCVideo);
        set_up_web_view();
      }
    }
  }
  
  public void adjust_size()
  {
    boolean bool = this.activity.calculate_layout();
    this.resize_images = (bool | this.resize_images);
    if (this.activity.video_view != null)
    {
      if (this.video_duration_ms <= 0) {
        this.video_duration_ms = this.activity.video_view.getDuration();
      }
      if (bool)
      {
        setLayoutParams(new FrameLayout.LayoutParams(this.activity.display_width, this.activity.display_height, 17));
        this.activity.video_view.setLayoutParams(new FrameLayout.LayoutParams(this.activity.view_width, this.activity.view_height, 17));
        this.resize_images = true;
      }
    }
    double d7;
    double d8;
    float f3;
    label269:
    float f4;
    label289:
    label456:
    double d5;
    label538:
    double d1;
    double d2;
    label600:
    double d3;
    if (this.resize_images)
    {
      this.resize_images = false;
      if (this.first_resize)
      {
        DisplayMetrics localDisplayMetrics = AdColony.activity().getResources().getDisplayMetrics();
        float f1 = localDisplayMetrics.widthPixels / localDisplayMetrics.xdpi;
        float f2 = localDisplayMetrics.heightPixels / localDisplayMetrics.ydpi;
        double d6 = Math.sqrt(f1 * f1 + f2 * f2);
        d7 = Math.sqrt(localDisplayMetrics.widthPixels * localDisplayMetrics.widthPixels + localDisplayMetrics.heightPixels * localDisplayMetrics.heightPixels) / d6;
        if (d7 / 280.0D >= 0.7D) {
          break label857;
        }
        d8 = 0.7D;
        this.hud_scale = d8;
        if (20.0D * this.hud_scale >= 18.0D) {
          break label868;
        }
        f3 = 18.0F;
        if (20.0D * this.hud_scale >= 18.0D) {
          break label882;
        }
        f4 = 18.0F;
        this.text_paint.setTextSize(f3);
        this.version_paint.setTextSize(f3);
        this.button_text_paint.setTextSize(f4);
        if ((this.engagement) && (this.img_engagement_button_normal != null) && (this.img_engagement_button_down != null))
        {
          this.img_engagement_button_normal.ninePatch(textWidthOf(this.engagement_text + 2 * this.img_engagement_button_normal.width), this.img_engagement_button_normal.height);
          this.img_engagement_button_down.ninePatch(textWidthOf(this.engagement_text + 2 * this.img_engagement_button_down.width), this.img_engagement_button_down.height);
        }
        if (this.activity.display_width <= this.activity.display_height) {
          break label896;
        }
        this.first_resize = false;
      }
      if (this.is_html5)
      {
        if ((bool) && (this.end_card_web_view != null)) {
          this.end_card_web_view.setLayoutParams(new FrameLayout.LayoutParams(this.activity.display_width, this.activity.display_height - this.offset, 17));
        }
        if (this.activity.view_height / 640.0D >= 0.9D) {
          break label907;
        }
        d5 = 0.9D;
        this.image_scale = d5;
      }
      if (this.is_static)
      {
        d1 = this.original_bg_width / this.original_bg_height;
        if (this.activity.display_width / d1 <= this.activity.display_height / 1.0D) {
          break label924;
        }
        d2 = this.activity.display_height / 1.0D;
        this.activity.view_width = ((int)(d1 * d2));
        this.activity.view_height = ((int)(1.0D * d2));
        if (this.activity.display_width <= this.activity.display_height) {
          break label940;
        }
        d3 = this.activity.view_height / 640.0D;
        label656:
        this.image_scale = d3;
        if (this.activity.display_width / this.original_bg_width <= this.activity.display_height / this.original_bg_height) {
          break label957;
        }
      }
    }
    label896:
    label907:
    label924:
    label940:
    label957:
    for (double d4 = this.activity.display_height / this.original_bg_height;; d4 = this.activity.display_width / this.original_bg_width)
    {
      this.background.resize(d4);
      this.background.center_within(this.activity.display_width, this.activity.display_height);
      if ((this.engagement) && (this.img_engagement_button_normal != null) && (this.img_engagement_button_down != null))
      {
        int i = (int)(this.img_engagement_button_normal.original_bitmap.getHeight() * this.hud_scale);
        int j = (int)(this.img_engagement_button_down.original_bitmap.getHeight() * this.hud_scale);
        this.img_engagement_button_normal.resize(this.img_engagement_button_normal.width, i);
        this.img_engagement_button_down.resize(this.img_engagement_button_down.width, j);
      }
      if (this.skippable)
      {
        this.img_skip_button.resize(this.hud_scale);
        this.img_skip_button_down.resize(this.hud_scale);
      }
      return;
      label857:
      d8 = d7 / 280.0D;
      break;
      label868:
      f3 = (float)(20.0D * this.hud_scale);
      break label269;
      label882:
      f4 = (float)(20.0D * this.hud_scale);
      break label289;
      break label456;
      d5 = this.activity.view_height / 640.0D;
      break label538;
      d2 = this.activity.display_width / d1;
      break label600;
      d3 = this.activity.view_height / 960.0D;
      break label656;
    }
  }
  
  public boolean clicked(ADCImage paramADCImage, int paramInt1, int paramInt2)
  {
    return (paramInt1 < 8 + (paramADCImage.x() + paramADCImage.width)) && (paramInt1 > -8 + paramADCImage.x()) && (paramInt2 < 8 + (paramADCImage.y() + paramADCImage.height)) && (paramInt2 > -8 + paramADCImage.y());
  }
  
  public void complete()
  {
    ADCController localADCController = ADC.controller;
    localADCController.on_video_finish(ADCVideo.is_replay, this.activity.ad);
    if ((this.is_html5) && (this.graceful_fail) && (ADC.graceful_fail))
    {
      this.activity.layout.addView(this.loading_view);
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          if ((ADCVideoHUD.this.graceful_fail) && (ADCVideoHUD.this.activity != null) && (ADCVideoHUD.this.is_html5) && (ADCVideoHUD.this.end_card_web_view != null))
          {
            ADCVideoHUD.this.activity.html5_endcard_loading_timeout = true;
            ADCVideoHUD.this.handle_continue();
          }
        }
      }, 1000 * ADC.load_timeout);
    }
    if (ADC.companion_ad_disabled) {
      handle_continue();
    }
    ADC.track_ad_event("card_shown", this.activity.ad);
    synchronized (this.update_handler)
    {
      this.dissolve_timer = null;
      if (ADC.current_ad.ad_info.companion_ad.dissolve) {
        this.dissolve_timer = new ADCUtil.Timer(ADC.current_ad.ad_info.companion_ad.dissolve_delay);
      }
      if (this.is_html5)
      {
        Handler localHandler = new Handler();
        final View localView = new View(this.activity);
        Runnable local6 = new Runnable()
        {
          public void run()
          {
            ADCVideoHUD.this.activity.layout.removeView(localView);
            ADCVideoHUD.this.dec_fire_viewable_change(true);
            ADCVideoHUD.this.activity.endcard_time_resume = System.currentTimeMillis();
          }
        };
        localView.setBackgroundColor(-16777216);
        this.activity.layout.addView(localView);
        localHandler.postDelayed(local6, 500L);
        this.activity.web_layout.setVisibility(0);
      }
      this.activity.endcard_time_resume = System.currentTimeMillis();
      on_video_finish();
      return;
    }
  }
  
  void dec_fire_keyboard_viewable_change(final boolean paramBoolean)
  {
    if (this.is_static) {}
    while (this.end_card_web_view == null) {
      return;
    }
    this.end_card_web_view.addJavascriptInterface(new Object()
    {
      public String toString()
      {
        if (paramBoolean) {
          return "keyboard_opened";
        }
        return "keyboard_closed";
      }
    }, "keyboard_listener");
  }
  
  void dec_fire_viewable_change(boolean paramBoolean)
  {
    if (this.is_static) {
      return;
    }
    if (paramBoolean)
    {
      execute_javascript("adc_bridge.fireChangeEvent({viewable:true});");
      return;
    }
    execute_javascript("adc_bridge.fireChangeEvent({viewable:false});");
  }
  
  void execute_javascript(String paramString)
  {
    if (this.is_static) {}
    while (this.end_card_web_view == null) {
      return;
    }
    if (Build.VERSION.SDK_INT >= 19)
    {
      this.end_card_web_view.evaluateJavascript(paramString, null);
      return;
    }
    this.end_card_web_view.loadUrl("javascript:" + paramString);
  }
  
  int get_button_index(int paramInt1, int paramInt2)
  {
    int i;
    if ((paramInt1 >= this.left_margin) && (paramInt1 < 62 + this.left_margin)) {
      i = 1;
    }
    int k;
    do
    {
      int j;
      do
      {
        boolean bool;
        do
        {
          ADCCustomVideoView localADCCustomVideoView;
          do
          {
            return i;
            if ((paramInt1 >= 78 + this.left_margin) && (paramInt1 < 62 + (78 + this.left_margin))) {
              return 2;
            }
            if ((paramInt1 >= 78 + (78 + this.left_margin)) && (paramInt1 < 62 + (78 + (78 + this.left_margin)))) {
              return 3;
            }
            if ((paramInt1 >= 78 + (78 + (78 + this.left_margin))) && (paramInt1 < 62 + (78 + (78 + (78 + this.left_margin))))) {
              return 4;
            }
            localADCCustomVideoView = this.activity.video_view;
            i = 0;
          } while (localADCCustomVideoView == null);
          bool = this.skippable;
          i = 0;
        } while (!bool);
        j = this.activity.video_view.getWidth() - this.img_skip_button.width;
        i = 0;
      } while (paramInt1 < j);
      k = this.img_skip_button.height;
      i = 0;
    } while (paramInt2 > k);
    return 10;
  }
  
  void handle_cancel()
  {
    ADC.resume_from_ad = true;
    if (ADC.current_ad.is_v4vc())
    {
      ADCVideo.video_seek_to_ms = this.activity.video_view.getCurrentPosition();
      ADCSkipVideoDialog.current = new ADCSkipVideoDialog(this.activity, (AdColonyV4VCAd)ADC.current_ad);
      return;
    }
    for (int i = 0; i < ADC.native_ad_view_list.size(); i++) {
      if (ADC.native_ad_view_list.get(i) != null) {
        ((AdColonyNativeAdView)ADC.native_ad_view_list.get(i)).initialize();
      }
    }
    this.activity.finish();
    ADC.end_card_finished_handler.notify_canceled(this.activity.ad);
    ADC.destroyed = true;
    AdColonyBrowser.should_recycle = true;
  }
  
  void handle_continue()
  {
    if ((this.activity == null) || ((this.is_html5) && ((this.end_card_web_view == null) || (this.activity.web_layout == null) || (this.activity.layout == null)))) {
      return;
    }
    ADC.resume_from_ad = true;
    this.activity.endcard_time_pause = System.currentTimeMillis();
    ADCVideo localADCVideo = this.activity;
    localADCVideo.endcard_time_spent += (this.activity.endcard_time_pause - this.activity.endcard_time_resume) / 1000.0D;
    ADC.destroyed = true;
    for (int i = 0; i < ADC.native_ad_view_list.size(); i++) {
      if (ADC.native_ad_view_list.get(i) != null) {
        ((AdColonyNativeAdView)ADC.native_ad_view_list.get(i)).initialize();
      }
    }
    this.activity.finish();
    this.dissolve_timer = null;
    if (this.is_html5)
    {
      this.activity.layout.removeView(this.activity.web_layout);
      this.end_card_web_view.destroy();
      this.end_card_web_view = null;
    }
    ADC.end_card_finished_handler.notify_continuation(this.activity.ad);
    AdColonyBrowser.should_recycle = true;
  }
  
  void handle_replay()
  {
    ADC.track_ad_event("replay", this.activity.ad);
    ADCVideo.is_replay = true;
    ADCVideo.video_finished = false;
    ADCVideo.video_seek_to_ms = 0;
    this.stay_at_zero = false;
    final View localView = new View(this.activity);
    localView.setBackgroundColor(-16777216);
    this.activity.layout.addView(localView, new FrameLayout.LayoutParams(this.activity.display_width, this.activity.display_height, 17));
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        if (ADCVideoHUD.this.is_html5) {
          ADCVideoHUD.this.activity.web_layout.setVisibility(4);
        }
        ADCVideoHUD.this.activity.layout.removeView(localView);
      }
    }, 900L);
    this.activity.video_view.start();
    ADC.controller.on_video_start(this.activity.ad);
    this.activity.video_view.requestFocus();
    this.activity.video_view.setBackgroundColor(0);
    this.activity.video_view.setVisibility(0);
    dec_fire_viewable_change(false);
  }
  
  void keyboardCheck()
  {
    getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
    {
      public void onGlobalLayout()
      {
        Rect localRect = new Rect();
        jdField_this.getWindowVisibleDisplayFrame(localRect);
        if (ADCVideoHUD.this.end_card_web_view != null) {
          ADCVideoHUD.this.set_offset(jdField_this.getRootView().getHeight() - (localRect.bottom - localRect.top) - (ADCVideoHUD.this.activity.display_height - ADCVideoHUD.this.end_card_web_view.getHeight()) / 2);
        }
        ADCVideoHUD.this.open_or_closed();
      }
    });
  }
  
  void load_dec_url()
  {
    this.end_card_web_view.loadUrl(ADC.end_card_url);
    ADCLog.dev.print("Loading - end card url = ").println(ADC.end_card_url);
  }
  
  void onButton(int paramInt)
  {
    for (;;)
    {
      try
      {
        if ((!this.can_press) && (paramInt != 10)) {
          break;
        }
        this.can_press = false;
        switch (paramInt)
        {
        case 5: 
        case 6: 
        case 7: 
        case 8: 
        case 9: 
        default: 
          this.selected_button = 0;
          new Handler().postDelayed(new Runnable()
          {
            public void run()
            {
              ADCVideoHUD.this.can_press = true;
            }
          }, 1500L);
          return;
        }
      }
      catch (RuntimeException localRuntimeException)
      {
        this.can_press = true;
        return;
      }
      this.selected_button = 0;
      ADC.track_ad_event("info", "{\"ad_slot\":" + ADC.current_ad.zone_info.state.play_order_index + "}", this.activity.ad);
      String str2 = ADC.get_String("info_url");
      ADCLog.debug.print("INFO ").println(str2);
      if ((str2.startsWith("market:")) || (str2.startsWith("amzn:")))
      {
        this.activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str2)));
      }
      else
      {
        AdColonyBrowser.url = str2;
        this.activity.startActivity(new Intent(this.activity, AdColonyBrowser.class));
        continue;
        this.selected_button = 0;
        ADC.track_ad_event("download", "{\"ad_slot\":" + ADC.current_ad.zone_info.state.play_order_index + "}", this.activity.ad);
        String str1 = ADC.get_String("download_url");
        ADCLog.debug.print("DOWNLOAD ").println(str1);
        if ((str1.startsWith("market:")) || (str1.startsWith("amzn:")))
        {
          this.activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str1)));
        }
        else
        {
          AdColonyBrowser.url = str1;
          this.activity.startActivity(new Intent(this.activity, AdColonyBrowser.class));
          continue;
          this.selected_button = 0;
          handle_replay();
          invalidate();
          continue;
          this.selected_button = 0;
          this.activity.video_view.stopPlayback();
          handle_continue();
          continue;
          this.selected_button = 0;
          handle_cancel();
        }
      }
    }
  }
  
  public void onCompletion(MediaPlayer paramMediaPlayer)
  {
    complete();
  }
  
  public void onDraw(Canvas paramCanvas)
  {
    if (this.error) {
      return;
    }
    adjust_size();
    this.canvas = paramCanvas;
    boolean bool2;
    label51:
    boolean bool1;
    label91:
    int n;
    int i1;
    if ((!this.skip_delay_met) && (this.skippable))
    {
      if (this.activity.video_view.getCurrentPosition() > this.skip_delay_ms)
      {
        bool2 = true;
        this.skip_delay_met = bool2;
      }
    }
    else
    {
      if ((!this.engagement_delay_met) && (this.engagement))
      {
        if (this.activity.video_view.getCurrentPosition() <= this.engagement_delay_ms) {
          break label399;
        }
        bool1 = true;
        this.engagement_delay_met = bool1;
      }
      if ((!ADCVideo.video_finished) || (!this.is_static)) {
        break label471;
      }
      paramCanvas.drawARGB(0xFF & this.activity.bg_color >> 24, 0, 0, 0);
      this.background.draw(paramCanvas, (this.activity.display_width - this.background.width) / 2, (this.activity.display_height - this.background.height) / 2);
      m = (int)(186.0D * this.image_scale) + this.background.x();
      n = (int)(470.0D * this.image_scale) + this.background.y();
      i1 = 0;
      label219:
      if (i1 < this.normal_buttons.length)
      {
        if ((this.selected_button != i1 + 1) && ((this.recent_selected_button != i1 + 1) || (this.up_state) || (this.recent_selected_button == 0))) {
          break label405;
        }
        this.active_buttons[i1].resize(this.image_scale);
        this.active_buttons[i1].draw(paramCanvas, m, n);
      }
    }
    for (int m = (int)(m + 157.0F * this.image_scale);; m = (int)(m + 157.0F * this.image_scale))
    {
      label399:
      label405:
      do
      {
        this.text_paint.setColor(-1);
        this.text_paint.clearShadowLayer();
        paramCanvas.drawText(this.button_labels[i1], this.normal_buttons[i1].x() + this.normal_buttons[i1].width / 2, this.normal_buttons[i1].y() + this.normal_buttons[i1].height, this.text_paint);
        i1++;
        break label219;
        break;
        bool2 = false;
        break label51;
        bool1 = false;
        break label91;
      } while ((!this.up_state) && (i1 + 1 == this.recent_selected_button));
      this.normal_buttons[i1].resize(this.image_scale);
      this.normal_buttons[i1].draw(paramCanvas, m, n);
    }
    label471:
    if ((ADCVideo.video_finished) && (this.is_html5))
    {
      this.img_close_button.resize(this.hud_scale);
      this.img_close_button_down.resize(this.hud_scale);
      this.img_reload_button.resize(this.hud_scale);
      this.img_reload_button_down.resize(this.hud_scale);
      int k;
      if ((!ADC.is_tablet) && (this.close_xpos != 0))
      {
        k = this.close_xpos;
        this.close_xpos = k;
        this.close_ypos = 0;
        this.reload_xpos = 0;
        this.reload_ypos = 0;
        if (this.close_pressed) {
          break label644;
        }
        this.img_close_button.draw(paramCanvas, this.close_xpos, this.close_ypos);
        if (this.reload_pressed) {
          break label663;
        }
        this.img_reload_button.draw(paramCanvas, this.reload_xpos, this.reload_ypos);
      }
      for (;;)
      {
        keyboardCheck();
        return;
        k = this.activity.display_width - this.img_close_button.width;
        break;
        this.img_close_button_down.draw(paramCanvas, this.close_xpos, this.close_ypos);
        break label596;
        this.img_reload_button_down.draw(paramCanvas, this.reload_xpos, this.reload_ypos);
      }
    }
    label596:
    if (this.activity.video_view != null)
    {
      ADC.controller.on_video_progress(this.activity.video_view.getCurrentPosition() / this.activity.video_view.getDuration(), this.activity.ad);
      int i = this.activity.video_view.getCurrentPosition();
      int j = (999 + (this.video_duration_ms - i)) / 1000;
      if ((this.stay_at_zero) && (j == 1)) {
        j = 0;
      }
      if (j == 0) {
        this.stay_at_zero = true;
      }
      if (i >= 500)
      {
        if (this.first_update)
        {
          this.angle_per_sec = ((float)(360.0D / (this.video_duration_ms / 1000.0D)));
          this.first_update = false;
          Rect localRect = new Rect();
          this.text_paint.getTextBounds("0123456789", 0, 9, localRect);
          this.radius = localRect.height();
        }
        this.width = getWidth();
        this.height = getHeight();
        this.center_x = this.radius;
        this.center_y = (this.activity.display_height - this.radius - this.offset);
        this.oval.set(this.center_x - this.radius / 2.0F, this.center_y - 2.0F * this.radius, this.center_x + 2.0F * this.radius, this.center_y + this.radius / 2.0F);
        this.circle_paint.setShadowLayer((int)(4.0D * this.image_scale), 0.0F, 0.0F, -16777216);
        this.cur_angle = ((float)(this.angle_per_sec * (this.video_duration_ms / 1000.0D - i / 1000.0D)));
        paramCanvas.drawArc(this.oval, 270.0F, this.cur_angle, false, this.circle_paint);
        if (!ADCVideo.video_finished)
        {
          this.text_paint.setColor(-3355444);
          this.text_paint.setShadowLayer((int)(2.0D * this.image_scale), 0.0F, 0.0F, -16777216);
          this.text_paint.setTextAlign(Paint.Align.CENTER);
          this.text_paint.setLinearText(true);
          paramCanvas.drawText("" + j, this.oval.centerX(), (float)(this.oval.centerY() + 1.35D * this.text_paint.getFontMetrics().bottom), this.text_paint);
        }
        if ((this.skippable) && (!ADCVideo.video_finished) && (this.skip_delay_met))
        {
          if (this.selected_button != 10) {
            break label1391;
          }
          this.img_skip_button_down.draw(paramCanvas, this.activity.display_width - this.img_skip_button_down.width, (int)(4.0D * this.image_scale));
        }
        if ((this.engagement) && (this.engagement_delay_met))
        {
          if (!this.engagement_pressed) {
            break label1426;
          }
          this.img_engagement_button_down.set_position((int)(this.activity.display_width - this.img_engagement_button_down.width - this.radius / 2.0F), this.activity.display_height - this.img_engagement_button_down.height - this.offset - (int)(this.radius / 2.0F));
          this.img_engagement_button_down.draw(paramCanvas);
        }
      }
    }
    for (;;)
    {
      label644:
      label663:
      label1213:
      this.button_text_paint.setTextAlign(Paint.Align.CENTER);
      paramCanvas.drawText(this.engagement_text, this.img_engagement_button_normal.dest_rect.centerX(), (float)(this.img_engagement_button_normal.dest_rect.centerY() + 1.35D * this.button_text_paint.getFontMetrics().bottom), this.button_text_paint);
      if (ADCSkipVideoDialog.current != null) {
        ADCSkipVideoDialog.current.onDraw(paramCanvas);
      }
      if (!ADCVideo.visible) {
        break;
      }
      invalidate();
      return;
      label1391:
      this.img_skip_button.draw(paramCanvas, this.activity.display_width - this.img_skip_button.width, (int)(4.0D * this.image_scale));
      break label1213;
      label1426:
      this.img_engagement_button_normal.set_position((int)(this.activity.display_width - this.img_engagement_button_normal.width - this.radius / 2.0F), this.activity.display_height - this.img_engagement_button_normal.height - this.offset - (int)(this.radius / 2.0F));
      this.img_engagement_button_normal.draw(paramCanvas);
    }
  }
  
  public boolean onError(MediaPlayer paramMediaPlayer, int paramInt1, int paramInt2)
  {
    handle_cancel();
    return true;
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.offset = (this.activity.display_height - paramInt2);
    if (Build.MODEL.equals("Kindle Fire")) {
      this.offset = 20;
    }
    if (Build.MODEL.equals("SCH-I800")) {
      this.offset = 25;
    }
    if ((Build.MODEL.equals("SHW-M380K")) || (Build.MODEL.equals("SHW-M380S")) || (Build.MODEL.equals("SHW-M380W"))) {
      this.offset = 40;
    }
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    int i = paramMotionEvent.getAction();
    if (ADCSkipVideoDialog.current != null) {
      ADCSkipVideoDialog.current.onTouchEvent(paramMotionEvent);
    }
    do
    {
      int j;
      int k;
      do
      {
        return true;
        j = (int)paramMotionEvent.getX();
        k = (int)paramMotionEvent.getY();
        if (i != 0) {
          break;
        }
        if ((ADCVideo.video_finished) && (this.is_html5))
        {
          if (clicked(this.img_close_button, j, k))
          {
            this.close_pressed = true;
            invalidate();
            return true;
          }
          if (clicked(this.img_reload_button, j, k))
          {
            this.reload_pressed = true;
            invalidate();
            return true;
          }
          return false;
        }
        if ((ADCVideo.video_finished) && (this.is_static))
        {
          j = (int)((paramMotionEvent.getX() - this.background.x()) / (2.0D * this.image_scale));
          k = (int)((paramMotionEvent.getY() - this.background.y()) / (2.0D * this.image_scale));
          if ((this.selected_button == 0) && (k >= 235) && (k < 305))
          {
            int n = get_button_index(j, k);
            this.selected_button = n;
            this.recent_selected_button = n;
            this.up_state = false;
            invalidate();
          }
        }
        if ((this.skippable) && (this.skip_delay_met) && (this.activity.video_view != null) && (clicked(this.img_skip_button, j, k)))
        {
          this.selected_button = 10;
          this.recent_selected_button = this.selected_button;
          this.up_state = false;
          invalidate();
          return true;
        }
      } while ((!this.engagement) || (!this.engagement_delay_met) || (!clicked(this.img_engagement_button_normal, j, k)));
      this.engagement_pressed = true;
      invalidate();
      return true;
      if (i == 1)
      {
        if ((ADCVideo.video_finished) && (this.is_html5))
        {
          if ((clicked(this.img_close_button, j, k)) && (this.close_pressed))
          {
            this.selected_button = 4;
            if (this.end_card_web_view != null) {
              this.end_card_web_view.clearCache(true);
            }
            this.on_button_press_handler.sendMessageDelayed(this.on_button_press_handler.obtainMessage(this.selected_button), 250L);
            return true;
          }
          if ((clicked(this.img_reload_button, j, k)) && (this.reload_pressed))
          {
            this.selected_button = 3;
            if (this.end_card_web_view != null) {
              this.end_card_web_view.clearCache(true);
            }
            this.on_button_press_handler.sendMessageDelayed(this.on_button_press_handler.obtainMessage(this.selected_button), 250L);
            return true;
          }
        }
        if ((ADCVideo.video_finished) && (this.is_static))
        {
          j = (int)((paramMotionEvent.getX() - this.background.x()) / (2.0D * this.image_scale));
          k = (int)((paramMotionEvent.getY() - this.background.y()) / (2.0D * this.image_scale));
          if ((!this.up_state) && (k >= 235) && (k < 305))
          {
            int m = get_button_index(j, k);
            if ((m > 0) && (m == this.recent_selected_button)) {
              this.on_button_press_handler.sendMessageDelayed(this.on_button_press_handler.obtainMessage(m), 250L);
            }
          }
        }
        if ((this.skippable) && (this.skip_delay_met) && (this.activity.video_view != null) && (clicked(this.img_skip_button, j, k)))
        {
          this.selected_button = 10;
          this.up_state = true;
          this.recent_selected_button = this.selected_button;
          this.on_button_press_handler.sendMessageDelayed(this.on_button_press_handler.obtainMessage(this.selected_button), 250L);
          return true;
        }
        if ((this.engagement) && (this.engagement_delay_met) && (clicked(this.img_engagement_button_normal, j, k)))
        {
          this.engagement_pressed = false;
          if ((this.engagement_url.startsWith("market:")) || (this.engagement_url.startsWith("amzn:"))) {
            this.activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.engagement_url)));
          }
          for (;;)
          {
            ADC.track_ad_event("in_video_engagement", "{\"ad_slot\":" + ADC.current_ad.zone_info.state.play_order_index + "}", this.activity.ad);
            return true;
            AdColonyBrowser.url = this.engagement_url;
            this.activity.startActivity(new Intent(this.activity, AdColonyBrowser.class));
          }
        }
        this.close_pressed = false;
        this.reload_pressed = false;
        this.engagement_pressed = false;
        this.up_state = true;
        this.selected_button = 0;
        invalidate();
        return true;
      }
    } while (i != 3);
    this.close_pressed = false;
    this.reload_pressed = false;
    this.engagement_pressed = false;
    this.up_state = true;
    this.selected_button = 0;
    invalidate();
    return true;
  }
  
  void on_video_finish()
  {
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        if (ADCVideoHUD.this.activity.video_view != null) {
          ADCVideoHUD.this.activity.video_view.setVisibility(8);
        }
      }
    }, 300L);
    ADCVideo.video_finished = true;
    if (this.activity.video_view != null) {
      this.activity.video_view.stopPlayback();
    }
    ADCSkipVideoDialog.current = null;
    invalidate();
    this.reload_pressed = false;
    invalidate();
  }
  
  void open_or_closed()
  {
    if ((this.keyboard_offset >= 70) && (!this.web_layout_offset))
    {
      this.web_layout_offset = true;
      dec_fire_keyboard_viewable_change(true);
    }
    while ((!this.web_layout_offset) || (this.keyboard_offset != 0)) {
      return;
    }
    this.web_layout_offset = false;
    dec_fire_keyboard_viewable_change(false);
  }
  
  void set_offset(int paramInt)
  {
    this.keyboard_offset = paramInt;
    if (paramInt < 0) {
      this.keyboard_offset = 0;
    }
  }
  
  void set_up_web_view()
  {
    this.end_card_web_view = new WebView(this.activity);
    this.end_card_web_view.setFocusable(true);
    this.end_card_web_view.setHorizontalScrollBarEnabled(false);
    this.end_card_web_view.setVerticalScrollBarEnabled(false);
    WebSettings localWebSettings = this.end_card_web_view.getSettings();
    localWebSettings.setJavaScriptEnabled(true);
    localWebSettings.setPluginState(WebSettings.PluginState.ON_DEMAND);
    localWebSettings.setBuiltInZoomControls(true);
    localWebSettings.setGeolocationEnabled(true);
    this.end_card_web_view.setWebChromeClient(new WebChromeClient()
    {
      public boolean onConsoleMessage(ConsoleMessage paramAnonymousConsoleMessage)
      {
        String str = paramAnonymousConsoleMessage.sourceId();
        if (str == null) {
          str = "Internal";
        }
        for (;;)
        {
          ADCLog.debug.print(paramAnonymousConsoleMessage.message()).print(" [").print(str).print(" line ").print(paramAnonymousConsoleMessage.lineNumber()).println("]");
          return true;
          int i = str.lastIndexOf('/');
          if (i != -1) {
            str = str.substring(i + 1);
          }
        }
      }
      
      public void onGeolocationPermissionsShowPrompt(String paramAnonymousString, GeolocationPermissions.Callback paramAnonymousCallback)
      {
        paramAnonymousCallback.invoke(paramAnonymousString, true, false);
      }
    });
    this.activity.web_layout = new FrameLayout(this.activity);
    if (ADC.get_Logical("hardware_acceleration_disabled")) {}
    try
    {
      Class localClass = this.activity.web_layout.getClass();
      Class[] arrayOfClass = new Class[2];
      arrayOfClass[0] = Integer.TYPE;
      arrayOfClass[1] = Paint.class;
      Method localMethod = localClass.getMethod("setLayerType", arrayOfClass);
      WebView localWebView = this.end_card_web_view;
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = Integer.valueOf(1);
      arrayOfObject[1] = null;
      localMethod.invoke(localWebView, arrayOfObject);
      label189:
      this.mraid_handler = new ADCMRAIDHandler(this.activity, this.end_card_web_view, this.activity);
      WebViewClient local4 = new WebViewClient()
      {
        String end_card_url = ADC.end_card_url;
        
        public void onLoadResource(WebView paramAnonymousWebView, String paramAnonymousString)
        {
          ADCLog.dev.print("DEC onLoad: ").println(paramAnonymousString);
          if (paramAnonymousString.equals(this.end_card_url))
          {
            ADCLog.dev.println("DEC disabling mouse events");
            ADCVideoHUD.this.execute_javascript("if (typeof(CN) != 'undefined' && CN.div) {\n  if (typeof(cn_dispatch_on_touch_begin) != 'undefined') CN.div.removeEventListener('mousedown',  cn_dispatch_on_touch_begin, true);\n  if (typeof(cn_dispatch_on_touch_end) != 'undefined')   CN.div.removeEventListener('mouseup',  cn_dispatch_on_touch_end, true);\n  if (typeof(cn_dispatch_on_touch_move) != 'undefined')  CN.div.removeEventListener('mousemove',  cn_dispatch_on_touch_move, true);\n}\n");
          }
        }
        
        public void onPageFinished(WebView paramAnonymousWebView, String paramAnonymousString)
        {
          if (paramAnonymousString.equals(this.end_card_url))
          {
            ADCVideoHUD.this.graceful_fail = false;
            ADCVideoHUD.this.activity.html5_endcard_loading_finished = true;
            ADCVideoHUD.this.end_card_loading_finish = System.currentTimeMillis();
            ADCVideoHUD.this.activity.html5_endcard_loading_time = ((ADCVideoHUD.this.end_card_loading_finish - ADCVideoHUD.this.end_card_loading_start) / 1000.0D);
          }
          ADCVideoHUD.this.activity.layout.removeView(ADCVideoHUD.this.loading_view);
        }
        
        public void onPageStarted(WebView paramAnonymousWebView, String paramAnonymousString, Bitmap paramAnonymousBitmap)
        {
          if (paramAnonymousString.equals(this.end_card_url))
          {
            ADCVideoHUD.this.activity.html5_endcard_loading_started = true;
            ADCVideoHUD.this.end_card_loading_start = System.currentTimeMillis();
          }
        }
        
        public boolean shouldOverrideUrlLoading(WebView paramAnonymousWebView, String paramAnonymousString)
        {
          ADCLog.dev.print("DEC request: ").println(paramAnonymousString);
          if (paramAnonymousString.contains("mraid:")) {
            ADCVideoHUD.this.mraid_handler.handleMRAIDCommand(paramAnonymousString);
          }
          do
          {
            return true;
            if (paramAnonymousString.contains("youtube"))
            {
              Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("vnd.youtube:" + paramAnonymousString));
              localIntent.putExtra("VIDEO_ID", paramAnonymousString);
              ADCVideoHUD.this.activity.startActivity(localIntent);
              return true;
            }
          } while (paramAnonymousString.contains("mraid.js"));
          return false;
        }
      };
      this.end_card_web_view.setWebViewClient(local4);
      if (Build.VERSION.SDK_INT >= 19) {
        this.end_card_web_view.loadUrl(ADC.end_card_url);
      }
      String str1 = ADCUtil.load_file(ADC.end_card_mraid_filepath, "");
      ADCLog.dev.println("Injecting mraid");
      execute_javascript(str1);
      String str2;
      if (ADC.is_tablet)
      {
        str2 = "true";
        execute_javascript("var is_tablet=" + str2 + ";");
        if (!ADC.is_tablet) {
          break label468;
        }
      }
      label468:
      for (String str3 = "tablet";; str3 = "phone")
      {
        execute_javascript("adc_bridge.adc_version='" + ADC.sdk_version + "'");
        execute_javascript("adc_bridge.os_version='" + ADC.os_version + "'");
        execute_javascript("adc_bridge.os_name='android'");
        execute_javascript("adc_bridge.device_type='" + str3 + "'");
        execute_javascript("adc_bridge.fireChangeEvent({state:'default'});");
        execute_javascript("adc_bridge.fireReadyEvent()");
        if (Build.VERSION.SDK_INT < 19) {
          this.end_card_web_view.loadUrl(ADC.end_card_url);
        }
        return;
        str2 = "false";
        break;
      }
    }
    catch (Exception localException)
    {
      break label189;
    }
  }
  
  int textWidthOf(String paramString)
  {
    this.button_text_paint.getTextWidths(paramString, widths);
    float f = 0.0F;
    int i = paramString.length();
    for (int j = 0; j < i; j++) {
      f += widths[j];
    }
    return (int)f;
  }
  
  class LoadingView
    extends View
  {
    Rect bounds = new Rect();
    
    public LoadingView(Activity paramActivity)
    {
      super();
    }
    
    public void onDraw(Canvas paramCanvas)
    {
      paramCanvas.drawARGB(255, 0, 0, 0);
      getDrawingRect(this.bounds);
      ADCVideoHUD.this.browser_icon.draw(paramCanvas, (this.bounds.width() - ADCVideoHUD.this.browser_icon.width) / 2, (this.bounds.height() - ADCVideoHUD.this.browser_icon.height) / 2);
      invalidate();
    }
  }
  
  class UpdateHandler
    extends Handler
  {
    UpdateHandler()
    {
      check_back_later();
    }
    
    void check_back_later()
    {
      sendMessageDelayed(obtainMessage(), 500L);
    }
    
    public void handleMessage(Message paramMessage)
    {
      check_back_later();
      if (ADCVideoHUD.this.activity.isFinishing()) {}
      while (ADCVideoHUD.this.activity.video_view == null) {
        return;
      }
      try
      {
        if ((ADCVideoHUD.this.dissolve_timer != null) && (ADCVideoHUD.this.dissolve_timer.expired()) && (!ADCVideoHUD.this.activity.video_view.isPlaying()))
        {
          ADCVideoHUD.this.dissolve_timer = null;
          ADCVideoHUD.this.selected_button = 0;
          if (ADCVideoHUD.this.activity.video_view != null) {
            ADCVideoHUD.this.activity.video_view.stopPlayback();
          }
          ADCVideoHUD.this.activity.endcard_dissolved = true;
          ADCVideoHUD.this.handle_continue();
        }
        return;
      }
      finally {}
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.jirbo.adcolony.ADCVideoHUD
 * JD-Core Version:    0.7.0.1
 */