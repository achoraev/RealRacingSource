package com.jirbo.adcolony;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public abstract class ADCVideo
  extends Activity
{
  static Activity ctx;
  static boolean html5_video_playing;
  static int html5_video_seek_to_ms;
  static boolean is_replay;
  static boolean mraid_ready;
  static boolean unfocused;
  static boolean video_finished;
  static int video_seek_to_ms;
  static int video_start;
  static boolean visible;
  int actual_video_height;
  int actual_video_width;
  AdColonyAd ad;
  int bg_color;
  Rect bounds = new Rect();
  ADCImage browser_icon;
  int display_height;
  int display_width;
  boolean endcard_dissolved;
  long endcard_time_pause;
  long endcard_time_resume;
  double endcard_time_spent;
  boolean first_play = true;
  boolean first_resume = true;
  boolean html5_endcard_loading_finished;
  boolean html5_endcard_loading_started;
  double html5_endcard_loading_time;
  boolean html5_endcard_loading_timeout;
  FrameLayout html5_video_layout;
  VideoView html5_video_view;
  ADCVideoHUD hud;
  FileInputStream infile;
  FrameLayout layout;
  LoadingView loading_view;
  boolean rewarded;
  String video_url = "";
  ADCCustomVideoView video_view;
  int view_height;
  int view_width;
  FrameLayout web_layout;
  
  static void reset()
  {
    video_seek_to_ms = 0;
    video_finished = false;
    is_replay = false;
    html5_video_playing = false;
  }
  
  boolean calculate_layout()
  {
    Display localDisplay = getWindowManager().getDefaultDisplay();
    this.display_width = localDisplay.getWidth();
    this.display_height = localDisplay.getHeight();
    this.bg_color = -16777216;
    getWindow().setBackgroundDrawable(new ColorDrawable(this.bg_color));
    int i = this.display_width;
    int j = this.display_height;
    this.view_width = i;
    this.view_height = j;
    boolean bool1 = ADC.layout_changed;
    boolean bool2 = false;
    if (bool1)
    {
      ADC.layout_changed = false;
      bool2 = true;
    }
    return bool2;
  }
  
  public void onCreate(Bundle paramBundle)
  {
    ADC.destroyed = false;
    super.onCreate(paramBundle);
    ctx = this;
    boolean bool1;
    if (!ADC.get_Logical("video_enabled"))
    {
      bool1 = true;
      ADC.video_disabled = bool1;
      if (ADC.get_Logical("end_card_enabled")) {
        break label140;
      }
    }
    label140:
    for (boolean bool2 = true;; bool2 = false)
    {
      ADC.companion_ad_disabled = bool2;
      ADC.graceful_fail = ADC.get_Logical("load_timeout_enabled");
      ADC.load_timeout = ADC.get_Integer("load_timeout");
      for (int i = 0; i < ADC.native_ad_view_list.size(); i++) {
        if (ADC.native_ad_view_list.get(i) != null)
        {
          AdColonyNativeAdView localAdColonyNativeAdView = (AdColonyNativeAdView)ADC.native_ad_view_list.get(i);
          if (localAdColonyNativeAdView.mp != null) {
            localAdColonyNativeAdView.texture_view.setVisibility(4);
          }
          if (localAdColonyNativeAdView.volume_button != null) {
            localAdColonyNativeAdView.volume_button.setVisibility(4);
          }
        }
      }
      bool1 = false;
      break;
    }
    this.ad = ADC.current_ad;
    is_replay = this.ad.replay;
    requestWindowFeature(1);
    getWindow().setFlags(1024, 1024);
    if (!ADC.is_tablet)
    {
      if (Build.VERSION.SDK_INT >= 10) {
        setRequestedOrientation(6);
      }
      for (;;)
      {
        setVolumeControlStream(3);
        this.video_view = new ADCCustomVideoView(this);
        this.layout = new FrameLayout(this);
        this.hud = new ADCVideoHUD(this);
        this.html5_video_layout = new FrameLayout(this);
        this.loading_view = new LoadingView(this);
        this.browser_icon = new ADCImage(ADC.get_String("browser_icon"));
        AdColonyBrowser.should_recycle = false;
        ADC.current_video = this;
        ADC.latest_video = this;
        return;
        setRequestedOrientation(0);
      }
    }
    int j = getResources().getConfiguration().orientation;
    if ((j == 0) || (j == 6) || (j == 2)) {}
    for (int k = 6;; k = 7)
    {
      ADC.orientation = k;
      if ((Build.VERSION.SDK_INT < 10) || (Build.MODEL.equals("Kindle Fire"))) {
        break label381;
      }
      setRequestedOrientation(ADC.orientation);
      break;
    }
    label381:
    if (Build.MODEL.equals("Kindle Fire"))
    {
      getRequestedOrientation();
      switch (((WindowManager)getSystemService("window")).getDefaultDisplay().getRotation())
      {
      default: 
        j = 8;
      }
    }
    for (;;)
    {
      ADC.orientation = j;
      setRequestedOrientation(j);
      break;
      j = 1;
      continue;
      j = 0;
      continue;
      j = 9;
    }
  }
  
  public void onDestroy()
  {
    super.onDestroy();
    if (!ADC.destroyed) {}
    ADC.show = true;
    ADC.current_video = null;
    ADC.destroyed = true;
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4) {
      return true;
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((ADCSkipVideoDialog.current != null) && (ADCSkipVideoDialog.current.onKeyDown(paramInt, paramKeyEvent))) {}
    label134:
    do
    {
      do
      {
        do
        {
          return true;
          if (paramInt != 4) {
            break label134;
          }
          if (!video_finished) {
            break;
          }
          if (html5_video_playing)
          {
            this.html5_video_view.stopPlayback();
            html5_video_playing = false;
            this.html5_video_layout.removeAllViews();
            setContentView(this.layout);
            return true;
          }
        } while ((this.hud == null) || (this.hud.selected_button != 0));
        ADC.destroyed = true;
        this.hud.handle_continue();
        return true;
      } while ((this.hud == null) || (!this.hud.skippable) || (!this.hud.skip_delay_met));
      ADC.destroyed = true;
      this.hud.handle_cancel();
      return true;
    } while (paramInt == 82);
    return super.onKeyUp(paramInt, paramKeyEvent);
  }
  
  public void onPause()
  {
    visible = false;
    if (html5_video_playing)
    {
      if (this.html5_video_view != null)
      {
        html5_video_seek_to_ms = this.html5_video_view.getCurrentPosition();
        this.html5_video_view.stopPlayback();
      }
      if (video_finished)
      {
        View localView = new View(this);
        localView.setBackgroundColor(-16777216);
        setContentView(localView);
        this.endcard_time_pause = System.currentTimeMillis();
        if (!isFinishing()) {
          this.endcard_time_spent += (this.endcard_time_pause - this.endcard_time_resume) / 1000.0D;
        }
      }
      if (this.video_view == null) {
        break label207;
      }
      if (this.video_view.getCurrentPosition() != 0) {
        video_seek_to_ms = this.video_view.getCurrentPosition();
      }
      this.video_view.stopPlayback();
      this.video_view.setBackgroundColor(-16777216);
    }
    for (;;)
    {
      this.hud.up_state = true;
      this.hud.reload_pressed = false;
      this.hud.close_pressed = false;
      this.hud.engagement_pressed = false;
      this.hud.recent_selected_button = 0;
      this.hud.selected_button = 0;
      this.hud.invalidate();
      super.onPause();
      return;
      html5_video_seek_to_ms = 0;
      break;
      label207:
      video_seek_to_ms = 0;
    }
  }
  
  public void onResume()
  {
    visible = true;
    super.onResume();
    if (ADC.is_activity_null()) {
      finish();
    }
    calculate_layout();
    if (this.first_resume)
    {
      this.first_resume = false;
      if (!video_finished)
      {
        if (this.hud.is_html5) {
          this.web_layout.addView(this.hud.end_card_web_view);
        }
        if (this.hud.is_html5) {
          this.web_layout.setVisibility(4);
        }
        if (Build.MODEL.equals("Kindle Fire")) {
          this.hud.offset = 20;
        }
        if (Build.MODEL.equals("SCH-I800")) {
          this.hud.offset = 25;
        }
        this.layout.addView(this.video_view, new FrameLayout.LayoutParams(this.view_width, this.view_height, 17));
        if (this.hud.is_html5) {
          this.layout.addView(this.web_layout, new FrameLayout.LayoutParams(this.display_width, this.display_height - this.hud.offset, 17));
        }
        this.layout.addView(this.hud, new FrameLayout.LayoutParams(this.display_width, this.display_height, 17));
      }
    }
    if (html5_video_playing)
    {
      this.html5_video_layout.removeView(this.loading_view);
      this.html5_video_layout.addView(this.loading_view);
      setContentView(this.html5_video_layout);
    }
    for (;;)
    {
      this.video_view.setOnCompletionListener(this.hud);
      this.video_view.setOnErrorListener(this.hud);
      try
      {
        this.infile = new FileInputStream(ADC.get_String("video_filepath"));
        this.video_view.setVideoPath(this.infile.getFD());
        if (!unfocused) {
          onWindowFocusChanged(true);
        }
        if (ADC.video_disabled)
        {
          this.hud.adjust_size();
          this.hud.complete();
        }
        return;
      }
      catch (IOException localIOException)
      {
        ADC.log_error("Unable to play video: " + ADC.get_String("video_filepath"));
        finish();
      }
      setContentView(this.layout);
      if (video_finished) {
        this.endcard_time_resume = System.currentTimeMillis();
      }
    }
  }
  
  public void onWindowFocusChanged(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      unfocused = false;
      if ((!video_finished) && (visible)) {
        if (this.video_view != null)
        {
          if (video_start != 0) {
            video_seek_to_ms = video_start;
          }
          video_start = 0;
          this.video_view.seekTo(video_seek_to_ms);
          if (!ADC.is_tablet) {
            break label131;
          }
          Handler localHandler = new Handler();
          Runnable local1 = new Runnable()
          {
            public void run()
            {
              ADCVideo.this.video_view.setBackgroundColor(0);
            }
          };
          this.video_view.setBackgroundColor(-16777216);
          localHandler.postDelayed(local1, 900L);
          if (!ADCSkipVideoDialog.skip_dialog)
          {
            this.hud.stay_at_zero = false;
            this.video_view.start();
          }
          this.hud.requestFocus();
          this.hud.invalidate();
        }
      }
      label131:
      do
      {
        return;
        this.video_view.setBackgroundColor(0);
        break;
        if (html5_video_playing)
        {
          if (this.html5_video_view != null)
          {
            this.html5_video_view.seekTo(html5_video_seek_to_ms);
            this.html5_video_view.start();
            return;
          }
          if (this.html5_video_layout != null) {
            this.html5_video_layout.removeAllViews();
          }
          setContentView(this.layout);
          return;
        }
      } while (!video_finished);
      this.hud.invalidate();
      return;
    }
    if (visible)
    {
      video_seek_to_ms = this.video_view.getCurrentPosition();
      this.video_view.pause();
    }
    unfocused = true;
  }
  
  void playVideo(String paramString)
  {
    this.video_url = paramString;
    html5_video_playing = true;
    this.html5_video_view = new VideoView(this);
    Uri localUri = Uri.parse(paramString);
    this.html5_video_view.setVideoURI(localUri);
    new MediaController(this).setMediaPlayer(this.html5_video_view);
    this.html5_video_view.setLayoutParams(new FrameLayout.LayoutParams(this.display_width, this.display_height, 17));
    this.html5_video_layout.addView(this.html5_video_view);
    this.html5_video_layout.addView(this.loading_view);
    setContentView(this.html5_video_layout);
    this.html5_video_view.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
    {
      public void onCompletion(MediaPlayer paramAnonymousMediaPlayer)
      {
        ADCVideo.this.setContentView(ADCVideo.this.layout);
        ADCVideo.this.html5_video_layout.removeAllViews();
        ADCVideo.html5_video_playing = false;
      }
    });
    this.html5_video_view.setOnPreparedListener(new MediaPlayer.OnPreparedListener()
    {
      public void onPrepared(MediaPlayer paramAnonymousMediaPlayer)
      {
        ADCVideo.this.html5_video_layout.removeViewAt(1);
      }
    });
    this.html5_video_view.start();
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
      ADCVideo.this.browser_icon.draw(paramCanvas, (this.bounds.width() - ADCVideo.this.browser_icon.width) / 2, (this.bounds.height() - ADCVideo.this.browser_icon.height) / 2);
      invalidate();
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.jirbo.adcolony.ADCVideo
 * JD-Core Version:    0.7.0.1
 */