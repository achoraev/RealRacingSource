package com.facebook.widget;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.facebook.android.R.drawable;
import com.facebook.android.R.id;
import com.facebook.android.R.layout;
import java.lang.ref.WeakReference;

public class ToolTipPopup
{
  public static final long DEFAULT_POPUP_DISPLAY_TIME = 6000L;
  private final WeakReference<View> mAnchorViewRef;
  private final Context mContext;
  private long mNuxDisplayTime = 6000L;
  private PopupContentView mPopupContent;
  private PopupWindow mPopupWindow;
  private final ViewTreeObserver.OnScrollChangedListener mScrollListener = new ViewTreeObserver.OnScrollChangedListener()
  {
    public void onScrollChanged()
    {
      if ((ToolTipPopup.this.mAnchorViewRef.get() != null) && (ToolTipPopup.this.mPopupWindow != null) && (ToolTipPopup.this.mPopupWindow.isShowing()))
      {
        if (ToolTipPopup.this.mPopupWindow.isAboveAnchor()) {
          ToolTipPopup.this.mPopupContent.showBottomArrow();
        }
      }
      else {
        return;
      }
      ToolTipPopup.this.mPopupContent.showTopArrow();
    }
  };
  private Style mStyle = Style.BLUE;
  private final String mText;
  
  public ToolTipPopup(String paramString, View paramView)
  {
    this.mText = paramString;
    this.mAnchorViewRef = new WeakReference(paramView);
    this.mContext = paramView.getContext();
  }
  
  private void registerObserver()
  {
    unregisterObserver();
    if (this.mAnchorViewRef.get() != null) {
      ((View)this.mAnchorViewRef.get()).getViewTreeObserver().addOnScrollChangedListener(this.mScrollListener);
    }
  }
  
  private void unregisterObserver()
  {
    if (this.mAnchorViewRef.get() != null) {
      ((View)this.mAnchorViewRef.get()).getViewTreeObserver().removeOnScrollChangedListener(this.mScrollListener);
    }
  }
  
  private void updateArrows()
  {
    if ((this.mPopupWindow != null) && (this.mPopupWindow.isShowing()))
    {
      if (this.mPopupWindow.isAboveAnchor()) {
        this.mPopupContent.showBottomArrow();
      }
    }
    else {
      return;
    }
    this.mPopupContent.showTopArrow();
  }
  
  public void dismiss()
  {
    unregisterObserver();
    if (this.mPopupWindow != null) {
      this.mPopupWindow.dismiss();
    }
  }
  
  public void setNuxDisplayTime(long paramLong)
  {
    this.mNuxDisplayTime = paramLong;
  }
  
  public void setStyle(Style paramStyle)
  {
    this.mStyle = paramStyle;
  }
  
  public void show()
  {
    if (this.mAnchorViewRef.get() != null)
    {
      this.mPopupContent = new PopupContentView(this.mContext);
      ((TextView)this.mPopupContent.findViewById(R.id.com_facebook_tooltip_bubble_view_text_body)).setText(this.mText);
      if (this.mStyle != Style.BLUE) {
        break label258;
      }
      this.mPopupContent.bodyFrame.setBackgroundResource(R.drawable.com_facebook_tooltip_blue_background);
      this.mPopupContent.bottomArrow.setImageResource(R.drawable.com_facebook_tooltip_blue_bottomnub);
      this.mPopupContent.topArrow.setImageResource(R.drawable.com_facebook_tooltip_blue_topnub);
      this.mPopupContent.xOut.setImageResource(R.drawable.com_facebook_tooltip_blue_xout);
    }
    for (;;)
    {
      View localView = ((Activity)this.mContext).getWindow().getDecorView();
      int i = localView.getWidth();
      int j = localView.getHeight();
      registerObserver();
      this.mPopupContent.onMeasure(View.MeasureSpec.makeMeasureSpec(i, -2147483648), View.MeasureSpec.makeMeasureSpec(j, -2147483648));
      this.mPopupWindow = new PopupWindow(this.mPopupContent, this.mPopupContent.getMeasuredWidth(), this.mPopupContent.getMeasuredHeight());
      this.mPopupWindow.showAsDropDown((View)this.mAnchorViewRef.get());
      updateArrows();
      if (this.mNuxDisplayTime > 0L) {
        this.mPopupContent.postDelayed(new Runnable()
        {
          public void run()
          {
            ToolTipPopup.this.dismiss();
          }
        }, this.mNuxDisplayTime);
      }
      this.mPopupWindow.setTouchable(true);
      this.mPopupContent.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          ToolTipPopup.this.dismiss();
        }
      });
      return;
      label258:
      this.mPopupContent.bodyFrame.setBackgroundResource(R.drawable.com_facebook_tooltip_black_background);
      this.mPopupContent.bottomArrow.setImageResource(R.drawable.com_facebook_tooltip_black_bottomnub);
      this.mPopupContent.topArrow.setImageResource(R.drawable.com_facebook_tooltip_black_topnub);
      this.mPopupContent.xOut.setImageResource(R.drawable.com_facebook_tooltip_black_xout);
    }
  }
  
  private class PopupContentView
    extends FrameLayout
  {
    private View bodyFrame;
    private ImageView bottomArrow;
    private ImageView topArrow;
    private ImageView xOut;
    
    public PopupContentView(Context paramContext)
    {
      super();
      init();
    }
    
    private void init()
    {
      LayoutInflater.from(getContext()).inflate(R.layout.com_facebook_tooltip_bubble, this);
      this.topArrow = ((ImageView)findViewById(R.id.com_facebook_tooltip_bubble_view_top_pointer));
      this.bottomArrow = ((ImageView)findViewById(R.id.com_facebook_tooltip_bubble_view_bottom_pointer));
      this.bodyFrame = findViewById(R.id.com_facebook_body_frame);
      this.xOut = ((ImageView)findViewById(R.id.com_facebook_button_xout));
    }
    
    public void onMeasure(int paramInt1, int paramInt2)
    {
      super.onMeasure(paramInt1, paramInt2);
    }
    
    public void showBottomArrow()
    {
      this.topArrow.setVisibility(4);
      this.bottomArrow.setVisibility(0);
    }
    
    public void showTopArrow()
    {
      this.topArrow.setVisibility(0);
      this.bottomArrow.setVisibility(4);
    }
  }
  
  public static enum Style
  {
    static
    {
      BLACK = new Style("BLACK", 1);
      Style[] arrayOfStyle = new Style[2];
      arrayOfStyle[0] = BLUE;
      arrayOfStyle[1] = BLACK;
      $VALUES = arrayOfStyle;
    }
    
    private Style() {}
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.facebook.widget.ToolTipPopup
 * JD-Core Version:    0.7.0.1
 */