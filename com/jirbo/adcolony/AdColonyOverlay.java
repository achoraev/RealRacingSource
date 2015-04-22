package com.jirbo.adcolony;

import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;

public class AdColonyOverlay
  extends ADCVideo
{
  Rect bounds = new Rect();
  int old_seek = 0;
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    Display localDisplay = getWindowManager().getDefaultDisplay();
    this.display_width = localDisplay.getWidth();
    this.display_height = localDisplay.getHeight();
    ADC.layout_changed = true;
    final View localView = new View(this);
    localView.setBackgroundColor(-16777216);
    if ((video_finished) && (this.hud.is_html5))
    {
      this.web_layout.setLayoutParams(new FrameLayout.LayoutParams(this.display_width, this.display_height - this.hud.offset, 17));
      this.layout.addView(localView, new FrameLayout.LayoutParams(this.display_width, this.display_height, 17));
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          AdColonyOverlay.this.layout.removeView(localView);
        }
      }, 1500L);
    }
    this.hud.adjust_size();
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.jirbo.adcolony.AdColonyOverlay
 * JD-Core Version:    0.7.0.1
 */