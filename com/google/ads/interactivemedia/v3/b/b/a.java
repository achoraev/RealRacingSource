package com.google.ads.interactivemedia.v3.b.b;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;
import android.text.TextUtils.TruncateAt;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class a
  extends LinearLayout
{
  private d a;
  private TextView b;
  private TextView c;
  private List<a> d = new ArrayList();
  
  public a(Context paramContext, d paramd)
  {
    super(paramContext);
    this.a = paramd;
    this.b = new TextView(paramContext);
    this.b.setTextColor(paramd.h);
    this.b.setIncludeFontPadding(false);
    this.b.setGravity(16);
    this.b.setEllipsize(TextUtils.TruncateAt.END);
    this.b.setSingleLine();
    int i = c.a(paramd.k, getResources().getDisplayMetrics().density);
    this.b.setPadding(i, i, i, i);
    LinearLayout.LayoutParams localLayoutParams1 = new LinearLayout.LayoutParams(-2, -2, 1.0F);
    addView(this.b, localLayoutParams1);
    if (paramd.l)
    {
      this.c = new TextView(paramContext);
      this.c.setTextColor(paramd.o);
      this.c.setTextSize(paramd.p);
      this.c.setText(paramd.n);
      this.c.setIncludeFontPadding(false);
      this.c.setPadding(10, 10, 10, 10);
      this.c.setGravity(16);
      this.c.setEllipsize(TextUtils.TruncateAt.END);
      this.c.setSingleLine();
      ShapeDrawable localShapeDrawable = new ShapeDrawable(new Shape()
      {
        public void draw(Canvas paramAnonymousCanvas, Paint paramAnonymousPaint)
        {
          paramAnonymousCanvas.drawLine(0.0F, 0.0F, 0.0F, getHeight(), paramAnonymousPaint);
        }
      });
      localShapeDrawable.getPaint().setColor(paramd.e);
      localShapeDrawable.getPaint().setStrokeWidth(paramd.f);
      localShapeDrawable.getPaint().setStyle(Paint.Style.STROKE);
      this.c.setBackgroundDrawable(localShapeDrawable);
      this.c.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          a.this.a();
        }
      });
      LinearLayout.LayoutParams localLayoutParams2 = new LinearLayout.LayoutParams(-2, -2);
      this.c.setLayoutParams(localLayoutParams2);
      addView(this.c, localLayoutParams2);
    }
  }
  
  protected void a()
  {
    Iterator localIterator = this.d.iterator();
    while (localIterator.hasNext()) {
      ((a)localIterator.next()).c();
    }
  }
  
  public void a(a parama)
  {
    this.d.add(parama);
  }
  
  public void a(String paramString)
  {
    this.b.setText(paramString);
  }
  
  public void b(String paramString)
  {
    this.c.setText(paramString);
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    GradientDrawable localGradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, this.a.b);
    localGradientDrawable.setBounds(0, 0, paramInt1, paramInt2);
    ShapeDrawable localShapeDrawable = new ShapeDrawable(new Shape()
    {
      public void draw(Canvas paramAnonymousCanvas, Paint paramAnonymousPaint)
      {
        paramAnonymousCanvas.drawLine(0.0F, getHeight(), getWidth(), getHeight(), paramAnonymousPaint);
      }
    });
    localShapeDrawable.getPaint().setColor(this.a.c);
    localShapeDrawable.getPaint().setStrokeWidth(this.a.d);
    localShapeDrawable.getPaint().setStyle(Paint.Style.STROKE);
    localShapeDrawable.setBounds(0, 0, paramInt1, paramInt2);
    setBackgroundDrawable(new LayerDrawable(new Drawable[] { localGradientDrawable, localShapeDrawable }));
  }
  
  public static abstract interface a
  {
    public abstract void c();
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.b.b.a
 * JD-Core Version:    0.7.0.1
 */