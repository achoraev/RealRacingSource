package com.jirbo.adcolony;

import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;

class ADCSkipVideoDialog
  extends ADCDialog
{
  static ADCSkipVideoDialog current;
  static boolean skip_dialog;
  boolean cancel_down;
  boolean confirm_down;
  ADCVideo video;
  
  public ADCSkipVideoDialog(ADCVideo paramADCVideo, AdColonyV4VCAd paramAdColonyV4VCAd)
  {
    this.video = paramADCVideo;
    this.listener = paramAdColonyV4VCAd;
    paramADCVideo.video_view.pause();
    current = this;
    if (!isReady()) {}
  }
  
  void calculatePosition()
  {
    int i = this.video.display_width;
    int j = this.video.display_height;
    this.left_x = ((i - this.img_bg.width) / 2);
    this.top_y = ((j - this.img_bg.height) / 2);
    this.center_x = (this.left_x + this.img_bg.width / 2);
    this.center_y = (this.top_y + this.img_bg.height / 2);
    this.button_y = (this.top_y + (int)(this.img_bg.height - (this.img_confirm_normal.height + 16.0D * scale)));
    this.button_x1 = (this.left_x + (int)(16.0D * scale));
    this.button_x2 = (this.left_x + (int)(this.img_bg.width - (this.img_confirm_normal.width + 16.0D * scale)));
  }
  
  public void onDraw(Canvas paramCanvas)
  {
    if (this.video.video_view == null) {
      return;
    }
    skip_dialog = true;
    calculatePosition();
    int i = 255 * (int)(System.currentTimeMillis() - this.start_ms) / 1000;
    if (i > 128) {
      i = 128;
    }
    paramCanvas.drawARGB(i, 0, 0, 0);
    this.img_bg.draw(paramCanvas, this.left_x, this.top_y);
    int j = 3 * fontHeight() / 2;
    drawEmbossedText("Completion is required to receive", this.center_x, (int)(this.center_y - 2.75D * j), paramCanvas);
    drawEmbossedText("your reward.", this.center_x, this.center_y - j * 2, paramCanvas);
    drawEmbossedText("Are you sure you want to skip?", this.center_x, (int)(this.center_y - 1.25D * j), paramCanvas);
    this.img_logo.draw(paramCanvas, this.center_x - this.img_logo.width / 2, this.center_y - this.img_logo.height / 2);
    if (!this.confirm_down)
    {
      this.img_confirm_normal.draw(paramCanvas, this.button_x1, this.button_y);
      if (this.cancel_down) {
        break label280;
      }
      this.img_cancel_normal.draw(paramCanvas, this.button_x2, this.button_y);
    }
    for (;;)
    {
      drawButtonText("Yes", this.button_x1, this.button_y, paramCanvas);
      drawButtonText("No", this.button_x2, this.button_y, paramCanvas);
      return;
      this.img_confirm_down.draw(paramCanvas, this.button_x1, this.button_y);
      break;
      label280:
      this.img_cancel_down.draw(paramCanvas, this.button_x2, this.button_y);
    }
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (this.video.video_view == null) {}
    while (paramInt != 4) {
      return false;
    }
    return true;
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    int i = 1;
    if (ADCVideo.video_finished)
    {
      current = null;
      i = this.video.hud.onTouchEvent(paramMotionEvent);
    }
    int j;
    int k;
    label167:
    label214:
    do
    {
      return i;
      j = (int)paramMotionEvent.getX();
      k = (int)paramMotionEvent.getY();
      if (paramMotionEvent.getAction() == i)
      {
        if ((!buttonContains(j, k, this.button_x1, this.button_y)) || (!this.confirm_down)) {
          break label167;
        }
        current = null;
        skip_dialog = false;
        ADC.show_post_popup = false;
        ADC.destroyed = i;
        ADC.end_card_finished_handler.notify_canceled(this.listener);
        AdColonyBrowser.should_recycle = i;
        this.video.finish();
      }
      for (;;)
      {
        this.confirm_down = false;
        this.cancel_down = false;
        invalidate();
        if (paramMotionEvent.getAction() != 0) {
          break;
        }
        if (!buttonContains(j, k, this.button_x1, this.button_y)) {
          break label214;
        }
        this.confirm_down = i;
        invalidate();
        return i;
        if ((buttonContains(j, k, this.button_x2, this.button_y)) && (this.cancel_down))
        {
          current = null;
          skip_dialog = false;
          this.video.video_view.start();
        }
      }
    } while (!buttonContains(j, k, this.button_x2, this.button_y));
    this.cancel_down = i;
    invalidate();
    return i;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.jirbo.adcolony.ADCSkipVideoDialog
 * JD-Core Version:    0.7.0.1
 */