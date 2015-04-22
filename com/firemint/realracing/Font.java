package com.firemint.realracing;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.graphics.Rect;
import android.graphics.Typeface;
import java.nio.ByteBuffer;

class Font
{
  public static final int OUTLINE_INSIDE = 2;
  public static final int OUTLINE_NONE = 0;
  public static final int OUTLINE_OUTSIDE = 3;
  public static final int OUTLINE_STROKE = 1;
  public float ascent = 0.0F;
  byte[] bmpData = null;
  public int bmpHeight = 0;
  public int bmpLeft = 0;
  public int bmpPitch = 0;
  public int bmpTop = 0;
  public int bmpWidth = 0;
  public float bottom = 0.0F;
  public Paint defaultPaint = null;
  public float descent = 0.0F;
  public float glyphAdvance = 0.0F;
  public float glyphHeight = 0.0F;
  public float glyphOffX = 0.0F;
  public float glyphOffY = 0.0F;
  public float glyphWidth = 0.0F;
  public float height = 0.0F;
  public float leading = 0.0F;
  private float m_size = 0.0F;
  private Typeface m_typeface;
  private float m_widthScale = 1.0F;
  public float top = 0.0F;
  
  public float getSize()
  {
    return this.m_size;
  }
  
  public Typeface getTypeface()
  {
    return this.m_typeface;
  }
  
  public float getWidthScale()
  {
    return this.m_widthScale;
  }
  
  public boolean init(String paramString, boolean paramBoolean1, boolean paramBoolean2, float paramFloat1, float paramFloat2)
  {
    Typeface localTypeface = Typeface.createFromFile(paramString);
    if (localTypeface == null) {
      return false;
    }
    int i;
    if ((paramBoolean1) && (paramBoolean2)) {
      i = 3;
    }
    for (;;)
    {
      this.m_typeface = Typeface.create(localTypeface, i);
      if (this.m_typeface == null) {
        break;
      }
      this.m_size = paramFloat1;
      if (paramFloat2 > 0.0F) {
        this.m_widthScale = paramFloat2;
      }
      this.defaultPaint = new Paint();
      this.defaultPaint.setColor(-1);
      this.defaultPaint.setTypeface(this.m_typeface);
      this.defaultPaint.setTextSize(this.m_size);
      this.defaultPaint.setTextScaleX(this.m_widthScale);
      this.defaultPaint.setAntiAlias(true);
      this.defaultPaint.setTextAlign(Paint.Align.LEFT);
      Paint.FontMetrics localFontMetrics = this.defaultPaint.getFontMetrics();
      this.ascent = (-localFontMetrics.ascent);
      this.descent = localFontMetrics.descent;
      this.height = (this.ascent + this.descent);
      this.top = (-localFontMetrics.top);
      this.bottom = localFontMetrics.bottom;
      this.leading = localFontMetrics.leading;
      return true;
      if (paramBoolean1)
      {
        i = 1;
      }
      else
      {
        i = 0;
        if (paramBoolean2) {
          i = 2;
        }
      }
    }
  }
  
  public boolean loadBitmap(int paramInt1, float paramFloat1, float paramFloat2, float paramFloat3, int paramInt2)
  {
    if (this.defaultPaint == null) {
      return false;
    }
    int[] arrayOfInt = { paramInt1 };
    String str;
    float f2;
    float f3;
    float f5;
    try
    {
      str = new String(arrayOfInt, 0, 1);
      Rect localRect = new Rect();
      this.defaultPaint.getTextBounds(str, 0, str.length(), localRect);
      float f1 = 1.0F;
      if ((paramInt2 == 1) || (paramInt2 == 3)) {
        f1 += paramFloat3;
      }
      f2 = -paramFloat2;
      f3 = (float)Math.floor(paramFloat1 + localRect.left - f1);
      float f4 = (float)Math.ceil(f1 + (paramFloat1 + localRect.right));
      f5 = (float)Math.floor(f2 + localRect.top - f1);
      float f6 = (float)Math.ceil(f1 + (f2 + localRect.bottom));
      this.bmpLeft = ((int)f3);
      this.bmpTop = (-(int)f5);
      this.bmpWidth = ((int)(f4 - f3));
      this.bmpHeight = ((int)(f6 - f5));
      if ((this.bmpWidth <= 0) || (this.bmpHeight <= 0)) {
        return false;
      }
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      return false;
    }
    Bitmap localBitmap = Bitmap.createBitmap(this.bmpWidth, this.bmpHeight, Bitmap.Config.ALPHA_8);
    Canvas localCanvas = new Canvas(localBitmap);
    localBitmap.eraseColor(Color.argb(0, 0, 0, 0));
    localCanvas.drawText(str, paramFloat1 - f3, f2 - f5, this.defaultPaint);
    this.bmpData = new byte[localBitmap.getWidth() * localBitmap.getHeight()];
    localBitmap.copyPixelsToBuffer(ByteBuffer.wrap(this.bmpData));
    this.bmpPitch = localBitmap.getRowBytes();
    localBitmap.recycle();
    return true;
  }
  
  public boolean loadGlyph(int paramInt)
  {
    if (this.defaultPaint == null) {
      return false;
    }
    int[] arrayOfInt = { paramInt };
    try
    {
      String str = new String(arrayOfInt, 0, 1);
      Rect localRect = new Rect();
      this.defaultPaint.getTextBounds(str, 0, str.length(), localRect);
      this.glyphOffX = localRect.left;
      this.glyphOffY = (-localRect.top);
      this.glyphWidth = localRect.width();
      this.glyphHeight = localRect.height();
      float[] arrayOfFloat = new float[str.length()];
      this.defaultPaint.getTextWidths(str, arrayOfFloat);
      this.glyphAdvance = arrayOfFloat[0];
      return true;
    }
    catch (IllegalArgumentException localIllegalArgumentException) {}
    return false;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.firemint.realracing.Font
 * JD-Core Version:    0.7.0.1
 */