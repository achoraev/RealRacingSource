package com.google.ads.interactivemedia.v3.b.b;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;
import android.util.DisplayMetrics;
import android.widget.FrameLayout;
import android.widget.TextView;

public class b
  extends FrameLayout
{
  private final float a = getResources().getDisplayMetrics().density;
  private final TextView b;
  
  public b(Context paramContext)
  {
    super(paramContext);
    setBackgroundDrawable(new a());
    int i = a(8, this.a);
    setPadding(i, i, i, i);
    this.b = new TextView(paramContext);
    this.b.setTextColor(-3355444);
    this.b.setIncludeFontPadding(false);
    this.b.setGravity(17);
    addView(this.b);
  }
  
  private int a(int paramInt, float paramFloat)
  {
    return (int)(0.5F + paramFloat * paramInt);
  }
  
  public void a(String paramString)
  {
    this.b.setText(paramString);
  }
  
  private static class a
    extends ShapeDrawable
  {
    private Paint a = new Paint();
    private Paint b;
    
    public a()
    {
      super()
      {
        private Path a;
        
        public void draw(Canvas paramAnonymousCanvas, Paint paramAnonymousPaint)
        {
          paramAnonymousCanvas.drawPath(this.a, paramAnonymousPaint);
        }
        
        public void onResize(float paramAnonymousFloat1, float paramAnonymousFloat2)
        {
          this.a = new Path();
          this.a.moveTo(getWidth(), getHeight());
          this.a.lineTo(6, getHeight());
          this.a.arcTo(new RectF(0.0F, getHeight() - 12, 12, getHeight()), 90.0F, 90.0F);
          this.a.lineTo(0.0F, 6);
          this.a.arcTo(new RectF(0.0F, 0.0F, 12, 12), 180.0F, 90.0F);
          this.a.lineTo(getWidth(), 0.0F);
        }
      };
      this.a.setAntiAlias(true);
      this.a.setStyle(Paint.Style.STROKE);
      this.a.setStrokeWidth(1.0F);
      this.a.setARGB(150, 255, 255, 255);
      this.b = new Paint();
      this.b.setStyle(Paint.Style.FILL);
      this.b.setColor(-16777216);
      this.b.setAlpha(140);
    }
    
    protected void onDraw(Shape paramShape, Canvas paramCanvas, Paint paramPaint)
    {
      paramShape.draw(paramCanvas, this.b);
      paramShape.draw(paramCanvas, this.a);
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.b.b.b
 * JD-Core Version:    0.7.0.1
 */