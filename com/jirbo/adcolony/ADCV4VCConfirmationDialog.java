package com.jirbo.adcolony;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout.LayoutParams;
import java.util.ArrayList;

class ADCV4VCConfirmationDialog
  extends ADCDialog
{
  boolean cancel_down;
  boolean confirm_down;
  
  public ADCV4VCConfirmationDialog(String paramString, AdColonyV4VCAd paramAdColonyV4VCAd)
  {
    this.amount = paramString;
    this.listener = paramAdColonyV4VCAd;
    if (!isReady()) {
      return;
    }
    AdColony.activity().addContentView(this, new FrameLayout.LayoutParams(-1, -1, 17));
  }
  
  public void onDraw(Canvas paramCanvas)
  {
    calculatePosition();
    int i = 255 * (int)(System.currentTimeMillis() - this.start_ms) / 1000;
    if (i > 128) {
      i = 128;
    }
    paramCanvas.drawARGB(i, 0, 0, 0);
    this.img_bg.draw(paramCanvas, this.left_x, this.top_y);
    int j = 3 * fontHeight() / 2;
    int k = this.listener.getRemainingViewsUntilReward();
    if (this.listener.getViewsPerReward() == 1)
    {
      splitLine(this.amount, "");
      if (!one_line)
      {
        drawEmbossedText("Watch a video to earn", this.center_x, (int)(this.center_y - 2.8D * j), paramCanvas);
        drawEmbossedText(amount_line_1, this.center_x, (int)(this.center_y - 2.05D * j), paramCanvas);
        drawEmbossedText(amount_line_2 + ".", this.center_x, (int)(this.center_y - 1.3D * j), paramCanvas);
        this.img_logo.draw(paramCanvas, this.center_x - this.img_logo.width / 2, this.center_y - this.img_logo.height / 2);
        if (this.confirm_down) {
          break label682;
        }
        this.img_confirm_normal.draw(paramCanvas, this.button_x1, this.button_y);
        label253:
        if (this.cancel_down) {
          break label701;
        }
        this.img_cancel_normal.draw(paramCanvas, this.button_x2, this.button_y);
      }
    }
    for (;;)
    {
      drawButtonText("Yes", this.button_x1, this.button_y, paramCanvas);
      drawButtonText("No", this.button_x2, this.button_y, paramCanvas);
      if (i != 128) {
        invalidate();
      }
      return;
      drawEmbossedText("Watch a video to earn", this.center_x, (int)(this.center_y - 2.5D * j), paramCanvas);
      drawEmbossedText(amount_line_1 + ".", this.center_x, (int)(this.center_y - 1.5D * j), paramCanvas);
      break;
      if (k == 1) {}
      for (String str = "video";; str = "videos")
      {
        splitLine(this.amount, "" + k + " more " + str + " to earn )?");
        if (one_line) {
          break label587;
        }
        drawEmbossedText("Watch a sponsored video now (Only", this.center_x, (int)(this.center_y - 2.8D * j), paramCanvas);
        drawEmbossedText("" + k + " more " + str + " to earn " + amount_line_1, this.center_x, (int)(this.center_y - 2.05D * j), paramCanvas);
        drawEmbossedText(amount_line_2 + ")?", this.center_x, (int)(this.center_y - 1.3D * j), paramCanvas);
        break;
      }
      label587:
      drawEmbossedText("Watch a sponsored video now (Only", this.center_x, (int)(this.center_y - 2.5D * j), paramCanvas);
      drawEmbossedText("" + k + " more " + str + " to earn " + amount_line_1 + ")?", this.center_x, (int)(this.center_y - 1.5D * j), paramCanvas);
      break;
      label682:
      this.img_confirm_down.draw(paramCanvas, this.button_x1, this.button_y);
      break label253;
      label701:
      this.img_cancel_down.draw(paramCanvas, this.button_x2, this.button_y);
    }
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    int i = (int)paramMotionEvent.getX();
    int j = (int)paramMotionEvent.getY();
    if (paramMotionEvent.getAction() == 1)
    {
      if ((buttonContains(i, j, this.button_x1, this.button_y)) && (this.confirm_down))
      {
        ADC.current_dialog = null;
        ((ViewGroup)getParent()).removeView(this);
        this.listener.on_dialog_finished(true);
        this.cancel_down = false;
        this.confirm_down = false;
        invalidate();
      }
    }
    else if (paramMotionEvent.getAction() == 0)
    {
      if (!buttonContains(i, j, this.button_x1, this.button_y)) {
        break label210;
      }
      this.confirm_down = true;
      invalidate();
    }
    label210:
    while (!buttonContains(i, j, this.button_x2, this.button_y))
    {
      return true;
      if ((!buttonContains(i, j, this.button_x2, this.button_y)) || (!this.cancel_down)) {
        break;
      }
      ADC.current_dialog = null;
      ((ViewGroup)getParent()).removeView(this);
      this.listener.on_dialog_finished(false);
      ADC.show = true;
      for (int k = 0; k < ADC.bitmaps.size(); k++) {
        ((Bitmap)ADC.bitmaps.get(k)).recycle();
      }
      ADC.bitmaps.clear();
      break;
    }
    this.cancel_down = true;
    invalidate();
    return true;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.jirbo.adcolony.ADCV4VCConfirmationDialog
 * JD-Core Version:    0.7.0.1
 */