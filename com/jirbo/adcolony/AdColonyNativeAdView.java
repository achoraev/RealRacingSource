package com.jirbo.adcolony;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.FileInputStream;
import java.util.ArrayList;

public class AdColonyNativeAdView
  extends FrameLayout
  implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener
{
  boolean actual_user_mute;
  AdColonyInterstitialAd ad;
  ADCManifest.Ad ad_info;
  String advertiser_name;
  TextView advertiser_text;
  Activity context;
  int current_button_color = -3355444;
  int current_button_text_color = -16777216;
  Bitmap current_volume_bitmap;
  String description;
  float dpi;
  Button engagement_button;
  String engagement_command = "";
  boolean engagement_enabled = false;
  String engagement_text = "";
  String engagement_type = "";
  boolean finished;
  boolean first_ensure;
  boolean first_initialize = true;
  SurfaceTexture global_texture;
  int height;
  FileInputStream infile;
  boolean initialized;
  boolean is_private;
  boolean list_view;
  AdColonyNativeAdListener listener;
  boolean manually_paused;
  MediaPlayer mp;
  boolean mute_enabled;
  String mute_filepath;
  AdColonyNativeAdMutedListener mute_listener;
  int original_height;
  int original_width;
  ViewGroup parent_view;
  boolean paused;
  boolean played;
  boolean playing;
  String poster_image_filepath;
  float pub_defined_volume = 0.25F;
  boolean replay;
  Surface s;
  int screen_width;
  int seek_to_ms;
  float set_volume = 0.25F;
  boolean skip_interval;
  TextView sponsor_text;
  ADCImage static_image;
  StaticImageView static_image_view;
  boolean surface_ready;
  View texture_view;
  ADCImage thumb_image;
  String thumb_image_filepath;
  ImageView thumb_image_view;
  String title;
  TextView title_text;
  String unmute_filepath;
  boolean user_mute;
  String video_filepath;
  int video_player_height;
  int video_player_width;
  boolean volume;
  ImageView volume_button;
  boolean volume_button_added;
  ADCImage volume_button_down;
  boolean volume_button_enabled = true;
  ADCImage volume_button_normal;
  int width;
  String zone_id;
  ADCManifest.Zone zone_info;
  boolean zone_is_native;
  
  public AdColonyNativeAdView(Activity paramActivity, String paramString, int paramInt)
  {
    super(paramActivity);
    ADC.ensure_configured();
    ADC.native_ad_view_list.add(this);
    ADC.error_code = 0;
    this.context = paramActivity;
    this.zone_id = paramString;
    this.width = paramInt;
    this.volume = true;
    this.dpi = ADC.activity().getResources().getDisplayMetrics().density;
    Display localDisplay = ADC.activity().getWindowManager().getDefaultDisplay();
    int i;
    int j;
    if (Build.VERSION.SDK_INT >= 14)
    {
      Point localPoint = new Point();
      localDisplay.getSize(localPoint);
      i = localPoint.x;
      j = localPoint.y;
      if (i >= j) {
        break label249;
      }
    }
    for (;;)
    {
      this.screen_width = i;
      this.ad = new AdColonyInterstitialAd(paramString);
      this.ad.ad_unit = "native";
      this.ad.view_format = "native";
      ADC.controller.reporting_manager.track_ad_request(paramString, this.ad);
      setBackgroundColor(-16777216);
      initialize();
      return;
      i = localDisplay.getWidth();
      j = localDisplay.getHeight();
      break;
      label249:
      i = j;
    }
  }
  
  AdColonyNativeAdView(Activity paramActivity, String paramString, int paramInt, boolean paramBoolean)
  {
    super(paramActivity);
    ADC.ensure_configured();
    this.context = paramActivity;
    this.zone_id = paramString;
    this.width = paramInt;
    this.is_private = paramBoolean;
    this.volume = true;
    this.dpi = ADC.activity().getResources().getDisplayMetrics().density;
    Display localDisplay = ADC.activity().getWindowManager().getDefaultDisplay();
    int i;
    int j;
    if (Build.VERSION.SDK_INT >= 14)
    {
      Point localPoint = new Point();
      localDisplay.getSize(localPoint);
      i = localPoint.x;
      j = localPoint.y;
      if (i >= j) {
        break label229;
      }
    }
    for (;;)
    {
      this.screen_width = i;
      this.ad = new AdColonyInterstitialAd(paramString);
      this.ad.ad_unit = "native";
      this.ad.view_format = "native";
      setBackgroundColor(-16777216);
      initialize();
      return;
      i = localDisplay.getWidth();
      j = localDisplay.getHeight();
      break;
      label229:
      i = j;
    }
  }
  
  public void destroy()
  {
    ADCLog.info.println("[ADC] Native Ad Destroy called.");
    if (this.s != null) {
      this.s.release();
    }
    if (this.mp != null) {
      this.mp.release();
    }
    this.mp = null;
    ADC.native_ad_view_list.remove(this);
  }
  
  public ImageView getAdvertiserImage()
  {
    if (this.thumb_image == null)
    {
      this.thumb_image = new ADCImage(this.thumb_image_filepath, true, false);
      this.thumb_image.resize(this.dpi / 2.0F, true);
    }
    if (this.thumb_image_view == null)
    {
      this.thumb_image_view = new ImageView(ADC.activity());
      this.thumb_image_view.setImageBitmap(this.thumb_image.bitmap);
    }
    return this.thumb_image_view;
  }
  
  public String getAdvertiserName()
  {
    return this.advertiser_name;
  }
  
  public String getDescription()
  {
    return this.description;
  }
  
  public int getNativeAdHeight()
  {
    if (this.engagement_enabled) {
      return this.height + this.height / 5;
    }
    return this.height;
  }
  
  public int getNativeAdWidth()
  {
    return this.width;
  }
  
  public String getTitle()
  {
    return this.title;
  }
  
  void initialize()
  {
    this.surface_ready = false;
    this.initialized = false;
    setWillNotDraw(false);
    this.ad.native_ad = this;
    label273:
    label350:
    label358:
    label375:
    float f;
    if (this.first_initialize)
    {
      if ((ADC.controller != null) && (ADC.controller.configuration != null) && (this.ad != null) && (this.ad.zone_id != null) && (ADC.controller.is_video_ad_available(this.ad.zone_id, true, false)))
      {
        ADC.controller.configuration.prepare_video_ad(this.zone_id);
        this.ad.set_up_info(true);
        this.zone_info = this.ad.zone_info;
        this.video_filepath = ADC.get_String("video_filepath");
        this.advertiser_name = ADC.get_String("advertiser_name");
        this.description = ADC.get_String("description");
        this.title = ADC.get_String("title");
        this.poster_image_filepath = ADC.get_String("poster_image");
        this.unmute_filepath = ADC.get_String("unmute");
        this.mute_filepath = ADC.get_String("mute");
        this.thumb_image_filepath = ADC.get_String("thumb_image");
        this.engagement_enabled = ADC.get_Logical("native_engagement_enabled");
        this.engagement_text = ADC.get_String("native_engagement_label");
        this.engagement_command = ADC.get_String("native_engagement_command");
        this.engagement_type = ADC.get_String("native_engagement_type");
        if ((this.ad.ad_info == null) || (this.ad.ad_info.native_ad == null)) {
          break label350;
        }
        this.mute_enabled = this.ad.ad_info.native_ad.mute_enabled;
        if (this.zone_info != null) {
          this.zone_info.advance_play_index();
        }
        if ((this.ad.ad_info != null) && (this.ad.ad_info.native_ad != null) && (this.ad.ad_info.native_ad.enabled) && (this.ad.zone_info != null)) {
          break label358;
        }
        ADC.error_code = 13;
      }
      do
      {
        return;
        this.finished = true;
        break;
        this.mute_enabled = true;
        break label273;
        this.zone_is_native = true;
      } while (this.is_private);
      this.first_initialize = false;
      this.original_width = this.ad.ad_info.video.width;
      this.original_height = this.ad.ad_info.video.height;
      this.height = ((int)(this.original_height * (this.width / this.original_width)));
      if (this.engagement_enabled)
      {
        this.engagement_button = new Button(ADC.activity());
        this.engagement_button.setText(this.engagement_text);
        this.engagement_button.setGravity(17);
        this.engagement_button.setTextSize((int)(18.0D * (this.width / this.screen_width)));
        this.engagement_button.setPadding(0, 0, 0, 0);
        this.engagement_button.setBackgroundColor(this.current_button_color);
        this.engagement_button.setTextColor(this.current_button_text_color);
        this.engagement_button.setOnTouchListener(new View.OnTouchListener()
        {
          public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
          {
            int i = paramAnonymousMotionEvent.getAction();
            if (i == 0)
            {
              float[] arrayOfFloat = new float[3];
              Color.colorToHSV(AdColonyNativeAdView.this.current_button_color, arrayOfFloat);
              arrayOfFloat[2] = (0.8F * arrayOfFloat[2]);
              AdColonyNativeAdView.this.engagement_button.setBackgroundColor(Color.HSVToColor(arrayOfFloat));
            }
            do
            {
              return true;
              if (i == 3)
              {
                AdColonyNativeAdView.this.engagement_button.setBackgroundColor(AdColonyNativeAdView.this.current_button_color);
                return true;
              }
            } while (i != 1);
            if ((AdColonyNativeAdView.this.engagement_type.equals("install")) || (AdColonyNativeAdView.this.engagement_type.equals("url"))) {
              ADC.controller.reporting_manager.track_ad_event("native_overlay_click", AdColonyNativeAdView.this.ad);
            }
            try
            {
              Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse(AdColonyNativeAdView.this.engagement_command));
              ADC.activity().startActivity(localIntent);
              AdColonyNativeAdView.this.engagement_button.setBackgroundColor(AdColonyNativeAdView.this.current_button_color);
              return true;
            }
            catch (Exception localException)
            {
              for (;;)
              {
                Toast.makeText(ADC.activity(), "Unable to open store.", 0).show();
              }
            }
          }
        });
      }
      this.static_image = new ADCImage(this.poster_image_filepath, true, false);
      if (1.0F / (this.static_image.width / this.width) <= 1.0F / (this.static_image.height / this.height)) {
        break label1098;
      }
      f = 1.0F / (this.static_image.height / this.height);
      label614:
      this.static_image.resize(f, true);
      this.volume_button_down = new ADCImage(this.unmute_filepath, true, false);
      this.volume_button_normal = new ADCImage(this.mute_filepath, true, false);
      this.thumb_image = new ADCImage(this.thumb_image_filepath, true, false);
      this.thumb_image.resize(1.0F / (float)(this.thumb_image.width / this.width / (this.width / 5.5D / this.width)), true);
      this.volume_button_normal.resize(this.dpi / 2.0F, true);
      this.volume_button_down.resize(this.dpi / 2.0F, true);
      if (Build.VERSION.SDK_INT >= 14) {
        this.texture_view = new NativeVideoView(ADC.activity(), this.finished);
      }
      this.static_image_view = new StaticImageView(ADC.activity());
      this.thumb_image_view = new ImageView(ADC.activity());
      this.volume_button = new ImageView(ADC.activity());
      this.thumb_image_view.setImageBitmap(this.thumb_image.bitmap);
      if (!this.volume) {
        break label1118;
      }
      this.volume_button.setImageBitmap(this.volume_button_normal.bitmap);
    }
    for (;;)
    {
      FrameLayout.LayoutParams localLayoutParams1 = new FrameLayout.LayoutParams(this.volume_button_normal.width, this.volume_button_normal.height, 48);
      localLayoutParams1.setMargins(this.width - this.volume_button_normal.width, 0, 0, 0);
      this.volume_button.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (AdColonyNativeAdView.this.volume)
          {
            if (AdColonyNativeAdView.this.mute_listener != null) {
              AdColonyNativeAdView.this.mute_listener.onAdColonyNativeAdMuted(AdColonyNativeAdView.this, true);
            }
            AdColonyNativeAdView.this.setMuted(true);
            AdColonyNativeAdView.this.actual_user_mute = true;
          }
          while (AdColonyNativeAdView.this.current_volume_bitmap != AdColonyNativeAdView.this.volume_button_down.bitmap) {
            return;
          }
          if (AdColonyNativeAdView.this.mute_listener != null) {
            AdColonyNativeAdView.this.mute_listener.onAdColonyNativeAdMuted(AdColonyNativeAdView.this, false);
          }
          AdColonyNativeAdView.this.actual_user_mute = false;
          AdColonyNativeAdView.this.setMuted(false);
        }
      });
      this.current_volume_bitmap = this.volume_button_normal.bitmap;
      if (this.finished) {
        this.volume_button.setVisibility(8);
      }
      if (this.manually_paused) {
        this.volume_button.setVisibility(4);
      }
      if (Build.VERSION.SDK_INT >= 14) {
        addView(this.texture_view, new FrameLayout.LayoutParams(this.width, this.height));
      }
      if (Build.VERSION.SDK_INT < 14) {
        this.finished = true;
      }
      addView(this.static_image_view, new FrameLayout.LayoutParams(this.width, this.height));
      if ((this.mute_enabled) && (Build.VERSION.SDK_INT >= 14) && (this.volume_button_enabled)) {
        addView(this.volume_button, localLayoutParams1);
      }
      if (!this.engagement_enabled) {
        break;
      }
      FrameLayout.LayoutParams localLayoutParams2 = new FrameLayout.LayoutParams(this.width, this.height / 5, 80);
      addView(this.engagement_button, localLayoutParams2);
      return;
      if (Build.VERSION.SDK_INT >= 14) {
        break label375;
      }
      return;
      label1098:
      f = 1.0F / (this.static_image.width / this.width);
      break label614;
      label1118:
      this.volume_button.setImageBitmap(this.volume_button_down.bitmap);
    }
  }
  
  public boolean isReady()
  {
    return (this.ad.isReady(true)) && (this.zone_is_native) && (!this.skip_interval);
  }
  
  boolean isReady(boolean paramBoolean)
  {
    return (this.ad.isReady(true)) && (AdColony.isZoneNative(this.zone_id));
  }
  
  void mute(boolean paramBoolean)
  {
    if ((this.mp != null) && (this.volume_button != null))
    {
      if (paramBoolean)
      {
        this.mp.setVolume(0.0F, 0.0F);
        this.volume_button.setImageBitmap(this.volume_button_down.bitmap);
        this.current_volume_bitmap = this.volume_button_down.bitmap;
      }
    }
    else {
      return;
    }
    this.mp.setVolume(this.pub_defined_volume, this.pub_defined_volume);
    this.volume_button.setImageBitmap(this.volume_button_normal.bitmap);
    this.current_volume_bitmap = this.volume_button_normal.bitmap;
  }
  
  public void notifyAddedToListView()
  {
    if (!this.first_ensure)
    {
      this.first_ensure = true;
      return;
    }
    ((NativeVideoView)this.texture_view).onSurfaceTextureAvailable(this.global_texture, this.video_player_width, this.video_player_height);
  }
  
  public void onCompletion(MediaPlayer paramMediaPlayer)
  {
    this.static_image_view.setVisibility(0);
    this.volume_button.setVisibility(8);
    this.ad.ad_unit = "native";
    this.ad.view_format = "native";
    this.ad.is_native = true;
    this.finished = true;
    this.mp.release();
    this.mp = null;
    this.ad.global_seek_to_ms = 0;
    ADCData.Table localTable = new ADCData.Table();
    localTable.set("ad_slot", this.ad.zone_info.state.session_play_count);
    localTable.set("replay", false);
    ADC.controller.reporting_manager.track_ad_event("native_complete", localTable, this.ad);
    if (this.listener != null) {
      this.listener.onAdColonyNativeAdFinished(false, this);
    }
    this.replay = true;
  }
  
  public void onDraw(Canvas paramCanvas)
  {
    if (this.parent_view == null) {}
    for (;;)
    {
      return;
      Rect localRect = new Rect();
      if (!this.parent_view.hasFocus()) {
        this.parent_view.requestFocus();
      }
      if ((!this.finished) && (this.mp != null)) {
        this.seek_to_ms = this.mp.getCurrentPosition();
      }
      if (this.seek_to_ms != 0) {
        this.ad.global_seek_to_ms = this.seek_to_ms;
      }
      boolean bool = getLocalVisibleRect(localRect);
      if (((!bool) && (!this.list_view)) || ((this.list_view) && ((!bool) || ((localRect.bottom - localRect.top < getNativeAdHeight()) && (localRect.top != 0))))) {
        if ((!this.finished) && (this.mp != null) && (this.mp.isPlaying()) && (!this.manually_paused))
        {
          ADCLog.info.println("[ADC] Scroll Pause");
          ADC.controller.reporting_manager.track_ad_event("video_paused", this.ad);
          this.mp.pause();
          this.static_image_view.setVisibility(0);
        }
      }
      while ((!this.paused) && (!this.finished))
      {
        invalidate();
        return;
        if ((!this.finished) && (this.mp != null) && (this.mp.isPlaying()))
        {
          if (!this.surface_ready)
          {
            paramCanvas.drawARGB(255, 0, 0, 0);
          }
          else
          {
            this.ad.ad_unit = "native";
            this.ad.view_format = "native";
            ADC.controller.on_video_progress(this.mp.getCurrentPosition() / this.mp.getDuration(), this.ad);
          }
        }
        else if (!this.static_image_view.visible) {
          paramCanvas.drawARGB(255, 0, 0, 0);
        }
      }
    }
  }
  
  public boolean onError(MediaPlayer paramMediaPlayer, int paramInt1, int paramInt2)
  {
    this.static_image_view.setVisibility(0);
    this.volume_button.setVisibility(8);
    this.finished = true;
    this.surface_ready = true;
    this.mp = null;
    this.ad.global_seek_to_ms = 0;
    return true;
  }
  
  public void onPrepared(MediaPlayer paramMediaPlayer)
  {
    ADCLog.info.println("[ADC] Native Ad onPrepared called.");
    this.surface_ready = true;
    if ((!this.volume) && (this.current_volume_bitmap.equals(this.volume_button_normal.bitmap)))
    {
      mute(true);
      return;
    }
    setVolume(this.pub_defined_volume);
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (Build.VERSION.SDK_INT >= 14) {
      return false;
    }
    if ((paramMotionEvent.getAction() == 1) && (ADC.show) && (ADCNetwork.connected()))
    {
      ADC.current_ad = this.ad;
      ADC.controller.configuration.prepare_video_ad(this.zone_id, this.ad.ad_info);
      ADCVideo.reset();
      this.ad.replay = this.replay;
      this.ad.is_native_expanded = true;
      this.ad.ad_unit = "native";
      this.ad.view_format = "fullscreen";
      ADC.show = false;
      ADC.controller.reporting_manager.track_ad_event("video_expanded", this.ad);
      if (this.listener != null) {
        this.listener.onAdColonyNativeAdStarted(true, this);
      }
      if (!ADC.is_tablet) {
        break label304;
      }
      ADCLog.dev.println("Launching AdColonyOverlay");
      ADC.activity().startActivity(new Intent(ADC.activity(), AdColonyOverlay.class));
    }
    for (;;)
    {
      if (this.finished)
      {
        ADCZoneState localADCZoneState = this.ad.zone_info.state;
        localADCZoneState.session_play_count = (1 + localADCZoneState.session_play_count);
        ADC.controller.track_ad_event("start", "{\"ad_slot\":" + this.ad.zone_info.state.session_play_count + ", \"replay\":" + this.ad.replay + "}", this.ad);
        ADC.controller.play_history.add_play_event(this.ad.zone_id, this.ad.ad_info.ad_id);
      }
      this.finished = true;
      this.replay = true;
      return true;
      label304:
      ADCLog.dev.println("Launching AdColonyFullscreen");
      ADC.activity().startActivity(new Intent(ADC.activity(), AdColonyFullscreen.class));
    }
  }
  
  public void pause()
  {
    ADCLog.info.println("[ADC] Native Ad Pause called.");
    if ((this.mp != null) && (!this.finished) && (this.mp.isPlaying()) && (Build.VERSION.SDK_INT >= 14))
    {
      ADC.controller.reporting_manager.track_ad_event("video_paused", this.ad);
      this.manually_paused = true;
      this.mp.pause();
      this.static_image_view.setVisibility(0);
      this.volume_button.setVisibility(4);
    }
  }
  
  /* Error */
  void play()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 422	com/jirbo/adcolony/AdColonyNativeAdView:finished	Z
    //   6: ifne +20 -> 26
    //   9: aload_0
    //   10: getfield 285	com/jirbo/adcolony/AdColonyNativeAdView:mp	Landroid/media/MediaPlayer;
    //   13: ifnull +13 -> 26
    //   16: aload_0
    //   17: getfield 285	com/jirbo/adcolony/AdColonyNativeAdView:mp	Landroid/media/MediaPlayer;
    //   20: invokevirtual 638	android/media/MediaPlayer:isPlaying	()Z
    //   23: ifne +12 -> 35
    //   26: aload_0
    //   27: getfield 285	com/jirbo/adcolony/AdColonyNativeAdView:mp	Landroid/media/MediaPlayer;
    //   30: astore_2
    //   31: aload_2
    //   32: ifnonnull +6 -> 38
    //   35: aload_0
    //   36: monitorexit
    //   37: return
    //   38: aload_0
    //   39: aload_0
    //   40: getfield 132	com/jirbo/adcolony/AdColonyNativeAdView:pub_defined_volume	F
    //   43: invokevirtual 681	com/jirbo/adcolony/AdColonyNativeAdView:setVolume	(F)V
    //   46: aload_0
    //   47: getfield 285	com/jirbo/adcolony/AdColonyNativeAdView:mp	Landroid/media/MediaPlayer;
    //   50: invokevirtual 786	android/media/MediaPlayer:start	()V
    //   53: getstatic 234	com/jirbo/adcolony/ADC:controller	Lcom/jirbo/adcolony/ADCController;
    //   56: aload_0
    //   57: getfield 222	com/jirbo/adcolony/AdColonyNativeAdView:ad	Lcom/jirbo/adcolony/AdColonyInterstitialAd;
    //   60: invokevirtual 790	com/jirbo/adcolony/ADCController:on_video_start	(Lcom/jirbo/adcolony/AdColonyAd;)V
    //   63: aload_0
    //   64: getfield 222	com/jirbo/adcolony/AdColonyNativeAdView:ad	Lcom/jirbo/adcolony/AdColonyInterstitialAd;
    //   67: iconst_1
    //   68: putfield 560	com/jirbo/adcolony/AdColonyInterstitialAd:is_native	Z
    //   71: aload_0
    //   72: getfield 593	com/jirbo/adcolony/AdColonyNativeAdView:listener	Lcom/jirbo/adcolony/AdColonyNativeAdListener;
    //   75: ifnull -40 -> 35
    //   78: aload_0
    //   79: getfield 593	com/jirbo/adcolony/AdColonyNativeAdView:listener	Lcom/jirbo/adcolony/AdColonyNativeAdListener;
    //   82: iconst_0
    //   83: aload_0
    //   84: invokeinterface 719 3 0
    //   89: goto -54 -> 35
    //   92: astore_1
    //   93: aload_0
    //   94: monitorexit
    //   95: aload_1
    //   96: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	97	0	this	AdColonyNativeAdView
    //   92	4	1	localObject	Object
    //   30	2	2	localMediaPlayer	MediaPlayer
    // Exception table:
    //   from	to	target	type
    //   2	26	92	finally
    //   26	31	92	finally
    //   38	89	92	finally
  }
  
  public void prepareForListView()
  {
    this.list_view = true;
  }
  
  public void resume()
  {
    ADCLog.info.println("[ADC] Native Ad Resume called.");
    if ((this.mp != null) && (this.manually_paused) && (!this.finished) && (Build.VERSION.SDK_INT >= 14))
    {
      ADC.controller.reporting_manager.track_ad_event("video_resumed", this.ad);
      this.manually_paused = false;
      this.mp.seekTo(this.ad.global_seek_to_ms);
      this.mp.start();
      this.static_image_view.setVisibility(4);
      this.volume_button.setVisibility(0);
    }
  }
  
  public void setMuted(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.volume_button.setImageBitmap(this.volume_button_down.bitmap);
      this.volume = false;
      setVolume(0.0F);
      this.current_volume_bitmap = this.volume_button_down.bitmap;
    }
    while ((this.actual_user_mute) || (this.current_volume_bitmap != this.volume_button_down.bitmap)) {
      return;
    }
    this.volume_button.setImageBitmap(this.volume_button_normal.bitmap);
    this.volume = true;
    if (this.mp != null)
    {
      if (this.pub_defined_volume == 0.0D) {
        break label117;
      }
      setVolume(this.pub_defined_volume);
    }
    for (;;)
    {
      this.current_volume_bitmap = this.volume_button_normal.bitmap;
      return;
      label117:
      setVolume(0.25F);
    }
  }
  
  public void setOverlayButtonColor(int paramInt)
  {
    if (this.engagement_enabled) {
      this.engagement_button.setBackgroundColor(paramInt);
    }
    this.current_button_color = paramInt;
  }
  
  public void setOverlayButtonTextColor(int paramInt)
  {
    if (this.engagement_enabled) {
      this.engagement_button.setTextColor(paramInt);
    }
    this.current_button_text_color = paramInt;
  }
  
  public void setOverlayButtonTypeface(Typeface paramTypeface, int paramInt)
  {
    if (this.engagement_enabled) {
      this.engagement_button.setTypeface(paramTypeface, paramInt);
    }
  }
  
  public void setVolume(float paramFloat)
  {
    if (Build.VERSION.SDK_INT < 14) {}
    do
    {
      do
      {
        do
        {
          return;
          this.pub_defined_volume = paramFloat;
          if ((this.mp == null) || (paramFloat < 0.0D) || (paramFloat > 1.0D)) {
            break;
          }
          if (!this.actual_user_mute) {
            this.mp.setVolume(paramFloat, paramFloat);
          }
        } while (!this.surface_ready);
        if ((this.current_volume_bitmap == this.volume_button_down.bitmap) && (paramFloat > 0.0D) && (!this.actual_user_mute))
        {
          this.volume_button.setImageBitmap(this.volume_button_normal.bitmap);
          this.current_volume_bitmap = this.volume_button_normal.bitmap;
          ADC.controller.reporting_manager.track_ad_event("sound_unmute", this.ad);
          this.volume = true;
          return;
        }
      } while ((this.current_volume_bitmap != this.volume_button_normal.bitmap) || (paramFloat != 0.0D));
      this.volume_button.setImageBitmap(this.volume_button_down.bitmap);
      this.current_volume_bitmap = this.volume_button_down.bitmap;
      ADC.controller.reporting_manager.track_ad_event("sound_mute", this.ad);
      this.volume = false;
      return;
    } while ((paramFloat < 0.0D) || (paramFloat > 1.0D));
    this.set_volume = paramFloat;
  }
  
  void system_pause()
  {
    if ((!this.finished) && (this.mp != null) && (this.mp.isPlaying()) && (!this.manually_paused))
    {
      ADC.controller.reporting_manager.track_ad_event("video_paused", this.ad);
      this.mp.pause();
    }
  }
  
  public AdColonyNativeAdView withListener(AdColonyNativeAdListener paramAdColonyNativeAdListener)
  {
    this.listener = paramAdColonyNativeAdListener;
    this.ad.native_listener = paramAdColonyNativeAdListener;
    return this;
  }
  
  public AdColonyNativeAdView withMutedListener(AdColonyNativeAdMutedListener paramAdColonyNativeAdMutedListener)
  {
    this.mute_listener = paramAdColonyNativeAdMutedListener;
    return this;
  }
  
  class NativeVideoView
    extends TextureView
    implements TextureView.SurfaceTextureListener
  {
    boolean fin = false;
    boolean running = false;
    
    NativeVideoView(Context paramContext)
    {
      this(paramContext, false);
    }
    
    NativeVideoView(Context paramContext, boolean paramBoolean)
    {
      super();
      setSurfaceTextureListener(this);
      setWillNotDraw(false);
      this.fin = paramBoolean;
    }
    
    public void onSurfaceTextureAvailable(SurfaceTexture paramSurfaceTexture, int paramInt1, int paramInt2)
    {
      AdColonyNativeAdView.this.static_image_view.setVisibility(0);
      AdColonyNativeAdView.this.global_texture = paramSurfaceTexture;
      if ((AdColonyNativeAdView.this.finished) || (this.fin)) {}
      for (;;)
      {
        return;
        AdColonyNativeAdView.this.s = new Surface(paramSurfaceTexture);
        if (AdColonyNativeAdView.this.mp != null) {
          AdColonyNativeAdView.this.mp.release();
        }
        AdColonyNativeAdView.this.video_player_width = paramInt1;
        AdColonyNativeAdView.this.video_player_height = paramInt2;
        AdColonyNativeAdView.this.mp = new MediaPlayer();
        try
        {
          AdColonyNativeAdView.this.infile = new FileInputStream(AdColonyNativeAdView.this.video_filepath);
          AdColonyNativeAdView.this.mp.setDataSource(AdColonyNativeAdView.this.infile.getFD());
          AdColonyNativeAdView.this.mp.setSurface(AdColonyNativeAdView.this.s);
          AdColonyNativeAdView.this.mp.setOnCompletionListener(AdColonyNativeAdView.this);
          AdColonyNativeAdView.this.mp.setOnPreparedListener(AdColonyNativeAdView.this);
          AdColonyNativeAdView.this.mp.setOnErrorListener(AdColonyNativeAdView.this);
          AdColonyNativeAdView.this.mp.prepareAsync();
          ADCLog.info.println("[ADC] Native Ad Prepare called.");
          this.running = true;
          Handler localHandler = new Handler();
          Runnable local1 = new Runnable()
          {
            public void run()
            {
              if ((!AdColonyNativeAdView.this.surface_ready) && (!AdColonyNativeAdView.this.paused))
              {
                AdColonyNativeAdView.NativeVideoView.this.running = false;
                AdColonyNativeAdView.this.finished = true;
                AdColonyNativeAdView.this.volume_button.setVisibility(8);
              }
            }
          };
          if (!this.running)
          {
            localHandler.postDelayed(local1, 1800L);
            return;
          }
        }
        catch (Exception localException)
        {
          AdColonyNativeAdView.this.finished = true;
          AdColonyNativeAdView.this.volume_button.setVisibility(8);
        }
      }
    }
    
    public boolean onSurfaceTextureDestroyed(SurfaceTexture paramSurfaceTexture)
    {
      ADCLog.info.println("[ADC] Native surface destroyed");
      AdColonyNativeAdView.this.surface_ready = false;
      AdColonyNativeAdView.this.volume_button.setVisibility(4);
      AdColonyNativeAdView.this.static_image_view.setVisibility(0);
      return true;
    }
    
    public void onSurfaceTextureSizeChanged(SurfaceTexture paramSurfaceTexture, int paramInt1, int paramInt2)
    {
      ADCLog.info.println("[ADC] onSurfaceTextureSizeChanged");
    }
    
    public void onSurfaceTextureUpdated(SurfaceTexture paramSurfaceTexture) {}
    
    public boolean onTouchEvent(MotionEvent paramMotionEvent)
    {
      int i = paramMotionEvent.getAction();
      float f1 = paramMotionEvent.getX();
      float f2 = paramMotionEvent.getY();
      if ((i != 1) || (!ADC.show) || ((f1 > 8 + (AdColonyNativeAdView.this.width - AdColonyNativeAdView.this.volume_button_normal.width)) && (f2 < 8 + AdColonyNativeAdView.this.volume_button_normal.height) && (!AdColonyNativeAdView.this.finished) && (AdColonyNativeAdView.this.mp != null) && (AdColonyNativeAdView.this.mp.isPlaying()))) {}
      do
      {
        return true;
        ADC.current_ad = AdColonyNativeAdView.this.ad;
        ADC.controller.configuration.prepare_video_ad(AdColonyNativeAdView.this.zone_id, AdColonyNativeAdView.this.ad.ad_info);
        ADCVideo.reset();
        AdColonyNativeAdView.this.ad.ad_unit = "native";
        AdColonyNativeAdView.this.ad.view_format = "fullscreen";
        AdColonyNativeAdView.this.ad.is_native_expanded = true;
        AdColonyNativeAdView.this.ad.replay = AdColonyNativeAdView.this.replay;
      } while (((!AdColonyNativeAdView.this.surface_ready) && (!AdColonyNativeAdView.this.finished)) || (!ADCNetwork.connected()));
      if (AdColonyNativeAdView.this.listener != null) {
        AdColonyNativeAdView.this.listener.onAdColonyNativeAdStarted(true, AdColonyNativeAdView.this);
      }
      if ((AdColonyNativeAdView.this.mp != null) && (AdColonyNativeAdView.this.mp.isPlaying()))
      {
        ADCVideo.video_start = AdColonyNativeAdView.this.mp.getCurrentPosition();
        AdColonyNativeAdView.this.ad.force_current_progress = AdColonyNativeAdView.this.ad.current_progress;
        AdColonyNativeAdView.this.mp.pause();
        AdColonyNativeAdView.this.finished = true;
        ADC.show = false;
        ADC.controller.reporting_manager.track_ad_event("video_expanded", AdColonyNativeAdView.this.ad);
        if (!ADC.is_tablet) {
          break label558;
        }
        ADCLog.dev.println("Launching AdColonyOverlay");
        ADC.activity().startActivity(new Intent(ADC.activity(), AdColonyOverlay.class));
      }
      for (;;)
      {
        if (AdColonyNativeAdView.this.finished)
        {
          ADCZoneState localADCZoneState = AdColonyNativeAdView.this.ad.zone_info.state;
          localADCZoneState.session_play_count = (1 + localADCZoneState.session_play_count);
          ADC.controller.track_ad_event("start", "{\"ad_slot\":" + AdColonyNativeAdView.this.ad.zone_info.state.session_play_count + ", \"replay\":" + AdColonyNativeAdView.this.ad.replay + "}", AdColonyNativeAdView.this.ad);
          ADC.controller.play_history.add_play_event(AdColonyNativeAdView.this.ad.zone_id, AdColonyNativeAdView.this.ad.ad_info.ad_id);
        }
        AdColonyNativeAdView.this.replay = true;
        return true;
        AdColonyNativeAdView.this.ad.force_current_progress = 0.0D;
        ADCVideo.video_start = 0;
        break;
        label558:
        ADCLog.dev.println("Launching AdColonyFullscreen");
        ADC.activity().startActivity(new Intent(ADC.activity(), AdColonyFullscreen.class));
      }
    }
  }
  
  class StaticImageView
    extends View
  {
    boolean visible;
    
    public StaticImageView(Context paramContext)
    {
      super();
    }
    
    public void onDraw(Canvas paramCanvas)
    {
      AdColonyNativeAdView.this.parent_view = ((ViewGroup)getParent().getParent());
      Rect localRect = new Rect();
      if ((AdColonyNativeAdView.this.mp != null) && (!AdColonyNativeAdView.this.mp.isPlaying()) && (AdColonyNativeAdView.this.list_view)) {
        this.visible = false;
      }
      if ((getLocalVisibleRect(localRect)) && (Build.VERSION.SDK_INT >= 14) && (AdColonyNativeAdView.this.surface_ready)) {
        if ((!AdColonyNativeAdView.this.list_view) || ((AdColonyNativeAdView.this.list_view) && ((localRect.top == 0) || (localRect.bottom - localRect.top > AdColonyNativeAdView.this.getNativeAdHeight()))))
        {
          if (((this.visible) || (AdColonyNativeAdView.this.finished) || (AdColonyNativeAdView.this.mp == null) || (AdColonyNativeAdView.this.mp.isPlaying()) || (AdColonyNativeAdView.this.paused) || (AdColonyNativeAdView.this.ad.isReady(true)) || (!AdColonyNativeAdView.this.played)) || (!AdColonyNativeAdView.this.played))
          {
            ADCLog.info.println("[ADC] Native Ad Starting");
            AdColonyNativeAdView.this.play();
            AdColonyNativeAdView.this.played = true;
            AdColonyNativeAdView.this.ad.ad_unit = "native";
            AdColonyNativeAdView.this.ad.view_format = "native";
          }
        }
        else
        {
          this.visible = true;
          label274:
          if ((!AdColonyNativeAdView.this.finished) && (!ADCNetwork.connected()) && (AdColonyNativeAdView.this.mp != null) && (!AdColonyNativeAdView.this.mp.isPlaying()))
          {
            setVisibility(0);
            AdColonyNativeAdView.this.volume_button.setVisibility(8);
            AdColonyNativeAdView.this.finished = true;
          }
          if ((AdColonyNativeAdView.this.finished) || (AdColonyNativeAdView.this.mp == null) || (!AdColonyNativeAdView.this.mp.isPlaying())) {
            break label640;
          }
          setVisibility(8);
          AdColonyNativeAdView.this.volume_button.setVisibility(0);
        }
      }
      for (;;)
      {
        if ((!AdColonyNativeAdView.this.paused) && (!AdColonyNativeAdView.this.finished)) {
          invalidate();
        }
        return;
        if ((!AdColonyNativeAdView.this.manually_paused) && (AdColonyNativeAdView.this.mp != null) && (ADCNetwork.connected()) && (!AdColonyNativeAdView.this.mp.isPlaying()) && (AdColonyNativeAdView.this.context.hasWindowFocus()) && (!ADC.dont_resume))
        {
          ADCLog.info.println("[ADC] Native Ad Resuming");
          ADC.controller.reporting_manager.track_ad_event("video_resumed", AdColonyNativeAdView.this.ad);
          if (!AdColonyNativeAdView.this.volume) {
            AdColonyNativeAdView.this.mute(true);
          }
          AdColonyNativeAdView.this.setVolume(AdColonyNativeAdView.this.pub_defined_volume);
          AdColonyNativeAdView.this.mp.seekTo(AdColonyNativeAdView.this.ad.global_seek_to_ms);
          AdColonyNativeAdView.this.mp.start();
          break;
        }
        if ((AdColonyNativeAdView.this.finished) || (AdColonyNativeAdView.this.played) || (ADC.controller.is_video_ad_available(AdColonyNativeAdView.this.ad.zone_id, true, false))) {
          break;
        }
        AdColonyNativeAdView.this.finished = true;
        setVisibility(0);
        AdColonyNativeAdView.this.volume_button.setVisibility(8);
        break;
        this.visible = false;
        break label274;
        label640:
        if ((AdColonyNativeAdView.this.finished) || (AdColonyNativeAdView.this.manually_paused))
        {
          paramCanvas.drawARGB(255, 0, 0, 0);
          AdColonyNativeAdView.this.volume_button.setVisibility(8);
          AdColonyNativeAdView.this.static_image.draw(paramCanvas, (AdColonyNativeAdView.this.width - AdColonyNativeAdView.this.static_image.width) / 2, (AdColonyNativeAdView.this.height - AdColonyNativeAdView.this.static_image.height) / 2);
        }
      }
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.jirbo.adcolony.AdColonyNativeAdView
 * JD-Core Version:    0.7.0.1
 */