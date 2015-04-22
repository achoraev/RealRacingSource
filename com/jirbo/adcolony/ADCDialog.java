package com.jirbo.adcolony;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout.LayoutParams;

class ADCDialog
  extends View
{
  static String amount_line_1 = "";
  static String amount_line_2 = "";
  static boolean one_line = true;
  static double scale;
  static Paint text_paint = new Paint(1);
  static float[] widths = new float[80];
  AdColonyInterstitialAd ad;
  String amount;
  int button_x1;
  int button_x2;
  int button_y;
  int center_x;
  int center_y;
  int dialog_type;
  ADCImage img_bg;
  ADCImage img_cancel_down;
  ADCImage img_cancel_normal;
  ADCImage img_confirm_down;
  ADCImage img_confirm_normal;
  ADCImage img_done_down;
  ADCImage img_done_normal;
  ADCImage img_logo;
  int left_x;
  AdColonyV4VCAd listener;
  long start_ms = System.currentTimeMillis();
  int top_y;
  
  ADCDialog()
  {
    super(ADC.activity());
  }
  
  public ADCDialog(String paramString, int paramInt, AdColonyInterstitialAd paramAdColonyInterstitialAd)
  {
    super(AdColony.activity());
    this.amount = paramString;
    this.dialog_type = paramInt;
    this.ad = paramAdColonyInterstitialAd;
    if (!isReady()) {
      return;
    }
    AdColony.activity().addContentView(this, new FrameLayout.LayoutParams(-1, -1, 17));
  }
  
  boolean buttonContains(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (paramInt1 < paramInt3) {}
    while ((paramInt2 < paramInt4) || (paramInt1 >= paramInt3 + this.img_confirm_normal.width) || (paramInt2 >= paramInt4 + this.img_confirm_normal.height)) {
      return false;
    }
    return true;
  }
  
  void calculatePosition()
  {
    Display localDisplay = ADC.activity().getWindowManager().getDefaultDisplay();
    int i = localDisplay.getWidth();
    int j = localDisplay.getHeight();
    this.left_x = ((i - this.img_bg.width) / 2);
    this.top_y = (-80 + (j - this.img_bg.height) / 2);
    this.center_x = (this.left_x + this.img_bg.width / 2);
    this.center_y = (this.top_y + this.img_bg.height / 2);
    this.button_y = (this.top_y + (int)(this.img_bg.height - (this.img_confirm_normal.height + 16.0D * scale)));
    this.button_x1 = (this.left_x + (int)(16.0D * scale));
    this.button_x2 = (this.left_x + (int)(this.img_bg.width - (this.img_confirm_normal.width + 16.0D * scale)));
  }
  
  void drawButtonText(String paramString, int paramInt1, int paramInt2, Canvas paramCanvas)
  {
    drawShadowText(paramString, paramInt1 + this.img_confirm_normal.width / 2, paramInt2 + this.img_confirm_normal.height / 2 + 4 * fontHeight() / 10, paramCanvas);
  }
  
  void drawEmbossedText(String paramString, int paramInt1, int paramInt2, Canvas paramCanvas)
  {
    int i = paramInt1 - textWidthOf(paramString) / 2;
    text_paint.setColor(-986896);
    paramCanvas.drawText(paramString, i + 1, paramInt2 + 1, text_paint);
    text_paint.setColor(-8355712);
    paramCanvas.drawText(paramString, i, paramInt2, text_paint);
  }
  
  void drawShadowText(String paramString, int paramInt1, int paramInt2, Canvas paramCanvas)
  {
    int i = paramInt1 - textWidthOf(paramString) / 2;
    text_paint.setColor(-8355712);
    paramCanvas.drawText(paramString, i + 2, paramInt2 + 2, text_paint);
    text_paint.setColor(-1);
    paramCanvas.drawText(paramString, i, paramInt2, text_paint);
  }
  
  int fontHeight()
  {
    return (int)text_paint.getTextSize();
  }
  
  public boolean isReady()
  {
    if (this.img_bg != null) {
      return true;
    }
    this.img_bg = new ADCImage(ADC.get_String("pre_popup_bg"));
    this.img_logo = new ADCImage(ADC.get_String("v4vc_logo"));
    this.img_confirm_normal = new ADCImage(ADC.get_String("yes_button_normal"));
    this.img_confirm_down = new ADCImage(ADC.get_String("yes_button_down"));
    this.img_cancel_normal = new ADCImage(ADC.get_String("no_button_normal"));
    this.img_cancel_down = new ADCImage(ADC.get_String("no_button_down"));
    this.img_done_normal = new ADCImage(ADC.get_String("done_button_normal"));
    this.img_done_down = new ADCImage(ADC.get_String("done_button_down"));
    Display localDisplay = ADC.activity().getWindowManager().getDefaultDisplay();
    int i = localDisplay.getWidth();
    int j = localDisplay.getHeight();
    if (j > i) {}
    for (double d = (j - i) / 360.0D;; d = (i - j) / 360.0D)
    {
      if (d > 2.5D) {
        d = 2.5D;
      }
      if (d < 0.8D) {
        d = 0.8D;
      }
      scale = d;
      this.img_bg.resize(d / 1.8D);
      this.img_logo.resize(d / 1.8D);
      this.img_confirm_down.resize(d / 1.8D);
      this.img_cancel_down.resize(d / 1.8D);
      this.img_confirm_normal.resize(d / 1.8D);
      this.img_cancel_normal.resize(d / 1.8D);
      this.img_done_down.resize(d / 1.8D);
      this.img_done_normal.resize(d / 1.8D);
      text_paint.setTextSize((float)(18.0D * d));
      text_paint.setFakeBoldText(true);
      return true;
    }
  }
  
  void splitLine(String paramString1, String paramString2)
  {
    int i = textWidthOf(paramString1);
    amount_line_1 = "";
    amount_line_2 = "";
    String str1 = "";
    if (i > this.img_bg.width - textWidthOf("WW") - textWidthOf(paramString2))
    {
      int j = 0;
      int k = 0;
      one_line = false;
      for (int m = 0; m < this.img_bg.width - textWidthOf("WW") - textWidthOf(paramString2); m = textWidthOf(str1))
      {
        str1 = str1 + paramString1.charAt(k);
        k++;
      }
      int n = 0;
      while (n < k) {
        if ((str1.charAt(n) == ' ') && (n >= 5))
        {
          j = n;
          amount_line_1 = paramString1.substring(0, j);
          n++;
        }
        else
        {
          if (j < 5) {}
          for (String str3 = paramString1.substring(0, k);; str3 = amount_line_1)
          {
            amount_line_1 = str3;
            break;
          }
        }
      }
      if (j < 5) {}
      for (String str2 = paramString1.substring(k);; str2 = paramString1.substring(j))
      {
        amount_line_2 = str2;
        return;
      }
    }
    one_line = true;
    amount_line_1 = paramString1;
    amount_line_2 = "";
  }
  
  int textWidthOf(String paramString)
  {
    text_paint.getTextWidths(paramString, widths);
    float f = 0.0F;
    int i = paramString.length();
    for (int j = 0; j < i; j++) {
      f += widths[j];
    }
    return (int)f;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.jirbo.adcolony.ADCDialog
 * JD-Core Version:    0.7.0.1
 */