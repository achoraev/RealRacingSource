package com.firemint.realracing;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.opengl.GLUtils;
import android.os.Build.VERSION;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils.TruncateAt;
import android.util.Log;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.opengles.GL10;

class GlyphVector
{
  public static final int fmParagraphAlignCentered = 2;
  public static final int fmParagraphAlignJustified = 3;
  public static final int fmParagraphAlignLeft = 0;
  public static final int fmParagraphAlignNatural = 4;
  public static final int fmParagraphAlignRight = 1;
  public static final int fmParagraphCharWrap = 1;
  public static final int fmParagraphClip = 2;
  public static final int fmParagraphTruncateHead = 3;
  public static final int fmParagraphTruncateMiddle = 5;
  public static final int fmParagraphTruncateTail = 4;
  public static final int fmParagraphWordWrap = 0;
  public static final int fmParagraphWordWrapTruncate = 6;
  public float boundsH = 0.0F;
  public float boundsW = 0.0F;
  private Rect m_bounds = null;
  private StaticLayout m_ellipsisLayout = null;
  private int m_ellipsisOffset = 0;
  private StaticLayout m_layout = null;
  private TextPaint m_paint = null;
  private String m_text = null;
  public int numLines = 0;
  public float offsetX = 0.0F;
  public float offsetY = 0.0F;
  public int texHeight = 0;
  public int texId = -1;
  public int texWidth = 0;
  
  private String sanitize(String paramString)
  {
    if (Build.VERSION.SDK_INT < 19) {
      paramString = paramString.replace("⁠", "");
    }
    return paramString;
  }
  
  public boolean createTexture()
  {
    if ((this.m_text == null) || (this.m_paint == null) || (this.m_bounds == null)) {
      return false;
    }
    for (this.texWidth = 1; this.texWidth < this.m_bounds.width(); this.texWidth = (2 * this.texWidth)) {}
    for (this.texHeight = 1; this.texHeight < this.m_bounds.height(); this.texHeight = (2 * this.texHeight)) {}
    Bitmap localBitmap = Bitmap.createBitmap(this.texWidth, this.texHeight, Bitmap.Config.ARGB_4444);
    Canvas localCanvas = new Canvas(localBitmap);
    localBitmap.eraseColor(Color.argb(0, 0, 0, 0));
    if (this.m_layout == null) {
      localCanvas.drawText(this.m_text, -this.m_bounds.left, -this.m_bounds.top, this.m_paint);
    }
    GL10 localGL10;
    for (;;)
    {
      int[] arrayOfInt = new int[1];
      localGL10 = (GL10)MainActivity.instance.getGLView().getGLContext().getGL();
      localGL10.glGenTextures(1, arrayOfInt, 0);
      this.texId = arrayOfInt[0];
      if (this.texId >= 0) {
        break;
      }
      localBitmap.recycle();
      return false;
      localCanvas.translate(-this.m_bounds.left, -this.m_bounds.top);
      this.m_layout.draw(localCanvas);
      if (this.m_ellipsisLayout != null)
      {
        localCanvas.translate(0.0F, this.m_ellipsisOffset);
        this.m_ellipsisLayout.draw(localCanvas);
      }
    }
    localGL10.glBindTexture(3553, this.texId);
    localGL10.glTexParameterf(3553, 10241, 9729.0F);
    localGL10.glTexParameterf(3553, 10240, 9729.0F);
    localGL10.glTexParameterf(3553, 10242, 33071.0F);
    localGL10.glTexParameterf(3553, 10243, 33071.0F);
    localGL10.glPixelStorei(3317, 1);
    GLUtils.texImage2D(3553, 0, localBitmap, 0);
    localBitmap.recycle();
    return true;
  }
  
  public Rect findLayoutBounds(StaticLayout paramStaticLayout)
  {
    int i = paramStaticLayout.getLineCount();
    Rect localRect1 = new Rect();
    int j = 0;
    if (j < i)
    {
      Rect localRect2 = new Rect();
      if (paramStaticLayout.getEllipsisCount(j) > 0) {
        localRect2.left = ((int)Math.floor(paramStaticLayout.getLineLeft(j)));
      }
      for (localRect2.right = (localRect2.left + paramStaticLayout.getEllipsizedWidth());; localRect2.right = ((int)Math.ceil(paramStaticLayout.getLineRight(j))))
      {
        localRect2.top = paramStaticLayout.getLineTop(j);
        localRect2.bottom = paramStaticLayout.getLineBottom(j);
        if ((j == 0) || (localRect2.left < localRect1.left)) {
          localRect1.left = localRect2.left;
        }
        if ((j == 0) || (localRect2.top < localRect1.top)) {
          localRect1.top = localRect2.top;
        }
        if ((j == 0) || (localRect2.right > localRect1.right)) {
          localRect1.right = localRect2.right;
        }
        if ((j == 0) || (localRect2.bottom > localRect1.bottom)) {
          localRect1.bottom = localRect2.bottom;
        }
        j++;
        break;
        localRect2.left = ((int)Math.floor(paramStaticLayout.getLineLeft(j)));
      }
    }
    return localRect1;
  }
  
  public void init(Font paramFont, String paramString)
  {
    String str = sanitize(paramString);
    this.m_text = str;
    this.m_paint = new TextPaint();
    this.m_paint.setColor(-1);
    this.m_paint.setTypeface(paramFont.getTypeface());
    this.m_paint.setTextSize(paramFont.getSize());
    this.m_paint.setTextScaleX(paramFont.getWidthScale());
    this.m_paint.setTextAlign(Paint.Align.LEFT);
    this.m_paint.setAntiAlias(true);
    this.m_bounds = new Rect();
    this.m_paint.getTextBounds(str, 0, str.length(), this.m_bounds);
    Rect localRect1 = this.m_bounds;
    localRect1.top = (-1 + localRect1.top);
    Rect localRect2 = this.m_bounds;
    localRect2.bottom = (1 + localRect2.bottom);
    this.offsetX = (-this.m_bounds.left);
    this.offsetY = (-this.m_bounds.top);
    this.boundsW = this.m_bounds.width();
    this.boundsH = this.m_bounds.height();
    this.numLines = 1;
  }
  
  public void initWithParagraph(Font paramFont, String paramString, float paramFloat1, float paramFloat2, int paramInt1, int paramInt2)
  {
    String str1 = sanitize(paramString);
    this.m_text = str1;
    this.m_paint = new TextPaint();
    this.m_paint.setColor(-1);
    this.m_paint.setTypeface(paramFont.getTypeface());
    this.m_paint.setTextSize(paramFont.getSize());
    this.m_paint.setTextScaleX(paramFont.getWidthScale());
    this.m_paint.setAntiAlias(true);
    if (paramFloat1 < 0.0F) {
      paramFloat1 = 0.0F;
    }
    if (paramFloat2 < 0.0F) {
      paramFloat2 = 0.0F;
    }
    Layout.Alignment localAlignment = Layout.Alignment.ALIGN_NORMAL;
    TextUtils.TruncateAt localTruncateAt3;
    label228:
    int i22;
    label293:
    int i14;
    switch (paramInt2)
    {
    case 3: 
    default: 
      if (paramInt1 == 1)
      {
        Log.e("RealRacing3", "Using unsupported text break style: fmParagraphCharWrap! Falling back to WordWrap.");
        paramInt1 = 0;
      }
      switch (paramInt1)
      {
      case 3: 
      case 4: 
      case 5: 
      default: 
        localTruncateAt3 = TextUtils.TruncateAt.START;
        switch (paramInt1)
        {
        default: 
          str1.replace('\n', ' ');
          int i19 = str1.length();
          TextPaint localTextPaint7 = this.m_paint;
          int i20 = (int)(2.0F * paramFloat1);
          int i21 = (int)paramFloat1;
          this.m_layout = new StaticLayout(str1, 0, i19, localTextPaint7, i20, localAlignment, 1.0F, 0.0F, false, localTruncateAt3, i21);
          i22 = 0;
          int i23 = this.m_layout.getLineCount();
          if (i22 < i23)
          {
            if (this.m_layout.getEllipsisCount(i22) > 0)
            {
              int i24 = this.m_layout.getLineStart(i22) + this.m_layout.getEllipsisStart(i22);
              str1 = this.m_layout.getText().toString().substring(0, i24 + 1);
              int i25 = str1.length();
              TextPaint localTextPaint8 = this.m_paint;
              int i26 = (int)paramFloat1;
              this.m_layout = new StaticLayout(str1, 0, i25, localTextPaint8, i26, localAlignment, 1.0F, 0.0F, false);
            }
          }
          else
          {
            label404:
            this.m_bounds = findLayoutBounds(this.m_layout);
            this.numLines = this.m_layout.getLineCount();
            if ((paramInt1 == 2) && (paramFloat2 > 0.0F))
            {
              i14 = 0;
              label443:
              int i15 = this.numLines;
              if (i14 < i15)
              {
                if (this.m_layout.getLineBottom(i14) - this.m_bounds.top <= (int)paramFloat2) {
                  break label1220;
                }
                int i16 = i14 - 1;
                if (i16 < 0) {
                  i16 = 0;
                }
                int i17 = this.m_layout.getLineVisibleEnd(i16);
                TextPaint localTextPaint6 = this.m_paint;
                int i18 = (int)paramFloat1;
                this.m_layout = new StaticLayout(str1, 0, i17, localTextPaint6, i18, localAlignment, 1.0F, 0.0F, false);
                this.m_bounds = findLayoutBounds(this.m_layout);
                this.numLines = this.m_layout.getLineCount();
              }
            }
            if ((paramInt1 != 6) || (paramFloat2 <= 0.0F)) {}
          }
          break;
        }
        break;
      }
      break;
    }
    for (int k = 0;; k++)
    {
      int m = this.numLines;
      Rect localRect4;
      if (k < m)
      {
        if (this.m_layout.getLineBottom(k) - this.m_bounds.top <= (int)paramFloat2) {
          continue;
        }
        int n = k - 1;
        if (n < 0) {
          n = 0;
        }
        int i1 = this.m_layout.getLineStart(n);
        int i2 = str1.length();
        TextPaint localTextPaint3 = this.m_paint;
        int i3 = (int)paramFloat1;
        TextUtils.TruncateAt localTruncateAt1 = TextUtils.TruncateAt.END;
        int i4 = (int)paramFloat1;
        this.m_ellipsisLayout = new StaticLayout(str1, i1, i2, localTextPaint3, i3, localAlignment, 1.0F, 0.0F, false, localTruncateAt1, i4);
        localRect4 = findLayoutBounds(this.m_ellipsisLayout);
        if (this.m_ellipsisLayout.getLineCount() > 1)
        {
          int i9 = this.m_ellipsisLayout.getLineStart(0);
          int i10 = this.m_ellipsisLayout.getLineVisibleEnd(0);
          String str2 = str1.substring(i9, i10);
          if (!str2.endsWith("…")) {
            str2 = str2 + "…";
          }
          int i11 = str2.length();
          TextPaint localTextPaint5 = this.m_paint;
          int i12 = (int)paramFloat1;
          TextUtils.TruncateAt localTruncateAt2 = TextUtils.TruncateAt.END;
          int i13 = (int)paramFloat1;
          this.m_ellipsisLayout = new StaticLayout(str2, 0, i11, localTextPaint5, i12, localAlignment, 1.0F, 0.0F, false, localTruncateAt2, i13);
          localRect4 = findLayoutBounds(this.m_ellipsisLayout);
        }
        if (n <= 0) {
          break label1226;
        }
        int i5 = n - 1;
        int i6 = this.m_layout.getLineVisibleEnd(i5);
        TextPaint localTextPaint4 = this.m_paint;
        int i7 = (int)paramFloat1;
        this.m_layout = new StaticLayout(str1, 0, i6, localTextPaint4, i7, localAlignment, 1.0F, 0.0F, false);
        this.m_bounds = findLayoutBounds(this.m_layout);
        this.m_ellipsisOffset = this.m_bounds.height();
        int i8 = this.m_ellipsisOffset;
        localRect4.offset(0, i8);
        this.m_bounds.union(localRect4);
      }
      for (this.numLines = (this.m_layout.getLineCount() + this.m_ellipsisLayout.getLineCount());; this.numLines = this.m_layout.getLineCount())
      {
        Rect localRect1 = new Rect();
        TextPaint localTextPaint2 = this.m_paint;
        int j = this.m_layout.getLineEnd(0);
        localTextPaint2.getTextBounds(str1, 0, j, localRect1);
        this.m_bounds.top = Math.min(this.m_bounds.top, this.m_layout.getLineBaseline(0) + localRect1.top);
        Rect localRect2 = this.m_bounds;
        localRect2.top = (-1 + localRect2.top);
        Rect localRect3 = this.m_bounds;
        localRect3.bottom = (1 + localRect3.bottom);
        if (this.numLines > 0)
        {
          this.offsetX = 0.0F;
          this.offsetY = (this.m_layout.getLineBaseline(0) - this.m_bounds.top);
        }
        this.boundsW = this.m_bounds.width();
        this.boundsH = this.m_bounds.height();
        return;
        localAlignment = Layout.Alignment.ALIGN_NORMAL;
        break;
        localAlignment = Layout.Alignment.ALIGN_CENTER;
        break;
        localAlignment = Layout.Alignment.ALIGN_OPPOSITE;
        break;
        TextPaint localTextPaint1 = this.m_paint;
        int i = (int)paramFloat1;
        this.m_layout = new StaticLayout(str1, localTextPaint1, i, localAlignment, 1.0F, 0.0F, false);
        break label404;
        localTruncateAt3 = TextUtils.TruncateAt.START;
        break label228;
        localTruncateAt3 = TextUtils.TruncateAt.END;
        break label228;
        localTruncateAt3 = TextUtils.TruncateAt.MIDDLE;
        break label228;
        i22++;
        break label293;
        label1220:
        i14++;
        break label443;
        label1226:
        this.m_layout = this.m_ellipsisLayout;
        this.m_ellipsisLayout = null;
        this.m_bounds = localRect4;
      }
    }
  }
  
  public boolean renderToTexture(int paramInt1, int paramInt2, int paramInt3, float paramFloat)
  {
    if ((this.m_text == null) || (this.m_paint == null) || (this.m_bounds == null)) {
      return false;
    }
    Bitmap localBitmap = Bitmap.createBitmap(paramInt2, paramInt3, Bitmap.Config.ALPHA_8);
    Canvas localCanvas = new Canvas(localBitmap);
    localBitmap.eraseColor(Color.argb(0, 0, 0, 0));
    localCanvas.translate(0.0F, paramInt3);
    localCanvas.scale(1.0F, -1.0F);
    localCanvas.translate(0.5F * (paramInt2 - paramFloat * this.m_bounds.width()) - paramFloat * this.m_bounds.left, 0.5F * (paramInt3 - paramFloat * this.m_bounds.height()) - paramFloat * this.m_bounds.top);
    localCanvas.scale(paramFloat, paramFloat);
    localCanvas.drawText(this.m_text, 0.0F, 0.0F, this.m_paint);
    GL10 localGL10 = (GL10)MainActivity.instance.getGLView().getGLContext().getGL();
    localGL10.glBindTexture(3553, paramInt1);
    localGL10.glPixelStorei(3317, 1);
    GLUtils.texSubImage2D(3553, 0, 0, 0, localBitmap, 6409, 5121);
    localBitmap.recycle();
    return true;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.firemint.realracing.GlyphVector
 * JD-Core Version:    0.7.0.1
 */