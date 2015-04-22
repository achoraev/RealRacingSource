package com.firemonkeys.cloudcellapi;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import java.io.File;
import org.apache.http.util.EncodingUtils;

public class UserInterfaceManager_Class
{
  static ScrollableWebView m_pStaticWebView;
  
  private native void ClickableCallback(int paramInt);
  
  private native void WebBrowserLoadFailCallback(long paramLong1, long paramLong2);
  
  private native void WebBrowserLoadFinishCallback(long paramLong1, long paramLong2);
  
  private native boolean WebBrowserLoadShouldStartCallback(String paramString, long paramLong1, long paramLong2);
  
  private native void WebBrowserLoadStartCallback(String paramString, long paramLong1, long paramLong2);
  
  void ClickableCreate(final ImageView paramImageView, final int paramInt)
  {
    CC_Activity.GetActivity().runOnUiThread(new Runnable()
    {
      public void run()
      {
        paramImageView.setClickable(true);
        paramImageView.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymous2View)
          {
            Log.e("Main", "iElementId:" + UserInterfaceManager_Class.8.this.val$iElementId);
            UserInterfaceManager_Class.this.ClickableCallback(UserInterfaceManager_Class.8.this.val$iElementId);
          }
        });
      }
    });
  }
  
  int GetScreenHeight()
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    int i = CC_Activity.GetActivity().getWindow().getDecorView().getSystemUiVisibility();
    if ((Build.VERSION.SDK_INT >= 19) && ((i & 0x1002) == 4098)) {
      CC_Activity.GetActivity().getWindowManager().getDefaultDisplay().getRealMetrics(localDisplayMetrics);
    }
    for (;;)
    {
      return (int)(localDisplayMetrics.heightPixels / GetScreenScale());
      CC_Activity.GetActivity().getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    }
  }
  
  float GetScreenScale()
  {
    Display localDisplay = CC_Activity.GetActivity().getWindowManager().getDefaultDisplay();
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    localDisplay.getMetrics(localDisplayMetrics);
    float f = 1.0F;
    if (localDisplayMetrics.density > 1.0F) {
      f = 2.0F;
    }
    return f;
  }
  
  @TargetApi(19)
  int GetScreenWidth()
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    int i = CC_Activity.GetActivity().getWindow().getDecorView().getSystemUiVisibility();
    if ((Build.VERSION.SDK_INT >= 19) && ((i & 0x1002) == 4098)) {
      CC_Activity.GetActivity().getWindowManager().getDefaultDisplay().getRealMetrics(localDisplayMetrics);
    }
    for (;;)
    {
      return (int)(localDisplayMetrics.widthPixels / GetScreenScale());
      CC_Activity.GetActivity().getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    }
  }
  
  void HandleScrollInput(float paramFloat1, float paramFloat2)
  {
    if (m_pStaticWebView != null)
    {
      final int i = (int)(paramFloat1 * 5.0F);
      final int j = (int)(paramFloat2 * 5.0F);
      m_pStaticWebView.post(new Runnable()
      {
        public void run()
        {
          UserInterfaceManager_Class.m_pStaticWebView.ScrollView(i, j);
        }
      });
    }
  }
  
  ImageView ImageCreate(final RelativeLayout paramRelativeLayout, final int paramInt1, final int paramInt2, final int paramInt3, final int paramInt4, final String paramString, int paramInt5, int paramInt6)
  {
    Activity localActivity = CC_Activity.GetActivity();
    final ImageView localImageView = new ImageView(localActivity);
    localActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        Bitmap localBitmap = BitmapFactory.decodeFile(paramString);
        localImageView.setImageBitmap(localBitmap);
        RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(paramInt3, paramInt4);
        localLayoutParams.leftMargin = paramInt1;
        localLayoutParams.topMargin = paramInt2;
        localImageView.setLayoutParams(localLayoutParams);
        paramRelativeLayout.addView(localImageView);
      }
    });
    return localImageView;
  }
  
  void ImageHide(final ImageView paramImageView)
  {
    CC_Activity.GetActivity().runOnUiThread(new Runnable()
    {
      public void run()
      {
        paramImageView.setVisibility(8);
      }
    });
  }
  
  ImageView ImagePatchCreate(final RelativeLayout paramRelativeLayout, final int paramInt1, final int paramInt2, final int paramInt3, final int paramInt4, final String paramString, final int paramInt5)
  {
    Activity localActivity = CC_Activity.GetActivity();
    final ImageView localImageView = new ImageView(localActivity);
    localActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        File localFile = new File(paramString);
        Bitmap localBitmap1 = Bitmap.createBitmap(paramInt3, paramInt4, Bitmap.Config.ARGB_8888);
        Canvas localCanvas = new Canvas(localBitmap1);
        Paint localPaint = new Paint(1);
        Bitmap localBitmap2 = BitmapFactory.decodeFile(localFile.getAbsolutePath());
        localCanvas.drawBitmap(localBitmap2, new Rect(0, 0, paramInt5, paramInt5), new Rect(0, 0, paramInt5, paramInt5), localPaint);
        localCanvas.drawBitmap(localBitmap2, new Rect(paramInt5, 0, localBitmap2.getWidth() - paramInt5, paramInt5), new Rect(paramInt5, 0, paramInt3 - paramInt5, paramInt5), localPaint);
        localCanvas.drawBitmap(localBitmap2, new Rect(localBitmap2.getWidth() - paramInt5, 0, localBitmap2.getWidth(), paramInt5), new Rect(paramInt3 - paramInt5, 0, paramInt3, paramInt5), localPaint);
        localCanvas.drawBitmap(localBitmap2, new Rect(0, paramInt5, paramInt5, localBitmap2.getHeight() - paramInt5), new Rect(0, paramInt5, paramInt5, paramInt4 - paramInt5), localPaint);
        localCanvas.drawBitmap(localBitmap2, new Rect(paramInt5, paramInt5, localBitmap2.getWidth() - paramInt5, localBitmap2.getHeight() - paramInt5), new Rect(paramInt5, paramInt5, paramInt3 - paramInt5, paramInt4 - paramInt5), localPaint);
        localCanvas.drawBitmap(localBitmap2, new Rect(localBitmap2.getWidth() - paramInt5, paramInt5, localBitmap2.getWidth(), localBitmap2.getHeight() - paramInt5), new Rect(paramInt3 - paramInt5, paramInt5, paramInt3, paramInt4 - paramInt5), localPaint);
        localCanvas.drawBitmap(localBitmap2, new Rect(0, localBitmap2.getHeight() - paramInt5, paramInt5, localBitmap2.getHeight()), new Rect(0, paramInt4 - paramInt5, paramInt5, paramInt4), localPaint);
        localCanvas.drawBitmap(localBitmap2, new Rect(paramInt5, localBitmap2.getHeight() - paramInt5, localBitmap2.getWidth() - paramInt5, localBitmap2.getHeight()), new Rect(paramInt5, paramInt4 - paramInt5, paramInt3 - paramInt5, paramInt4), localPaint);
        localCanvas.drawBitmap(localBitmap2, new Rect(localBitmap2.getWidth() - paramInt5, localBitmap2.getHeight() - paramInt5, localBitmap2.getWidth(), localBitmap2.getHeight()), new Rect(paramInt3 - paramInt5, paramInt4 - paramInt5, paramInt3, paramInt4), localPaint);
        localImageView.setImageBitmap(localBitmap1);
        RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(paramInt3, paramInt4);
        localLayoutParams.leftMargin = paramInt1;
        localLayoutParams.topMargin = paramInt2;
        localImageView.setLayoutParams(localLayoutParams);
        paramRelativeLayout.addView(localImageView);
      }
    });
    return localImageView;
  }
  
  void ImageShow(final ImageView paramImageView)
  {
    CC_Activity.GetActivity().runOnUiThread(new Runnable()
    {
      public void run()
      {
        paramImageView.setVisibility(0);
      }
    });
  }
  
  TextView LabelCreate(final TextView paramTextView, final RelativeLayout paramRelativeLayout, final String paramString1, final String paramString2, final int paramInt1, final float paramFloat1, final float paramFloat2, final float paramFloat3, final int paramInt2, final int paramInt3, final int paramInt4, final int paramInt5)
  {
    Activity localActivity = CC_Activity.GetActivity();
    if (paramTextView == null) {}
    for (final boolean bool = true;; bool = false)
    {
      if (bool) {
        paramTextView = new TextView(localActivity);
      }
      localActivity.runOnUiThread(new Runnable()
      {
        public void run()
        {
          paramTextView.setText(paramString1);
          paramTextView.setTextSize(0, paramInt1);
          paramTextView.setTypeface(Typeface.create(paramString2, 0));
          paramTextView.setTextColor(Color.rgb((int)(255.0F * paramFloat1), (int)(255.0F * paramFloat2), (int)(255.0F * paramFloat3)));
          paramTextView.setGravity(17);
          RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(paramInt4, paramInt5);
          localLayoutParams.leftMargin = paramInt2;
          localLayoutParams.topMargin = paramInt3;
          paramTextView.setLayoutParams(localLayoutParams);
          if (bool) {
            paramRelativeLayout.addView(paramTextView);
          }
        }
      });
      return paramTextView;
    }
  }
  
  void LabelDelete(final TextView paramTextView)
  {
    CC_Activity.GetActivity().runOnUiThread(new Runnable()
    {
      public void run()
      {
        paramTextView.setText("");
      }
    });
  }
  
  void ShowDialogBox(final String paramString1, final String paramString2, final String paramString3)
  {
    final Activity localActivity = CC_Activity.GetActivity();
    localActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(localActivity);
        localBuilder.setTitle(paramString1).setMessage(paramString2).setNegativeButton(paramString3, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int) {}
        });
        localBuilder.show();
      }
    });
  }
  
  WebView WebBrowserCreate(ScrollableWebView paramScrollableWebView, RelativeLayout paramRelativeLayout, int paramInt1, final int paramInt2, int paramInt3, final int paramInt4, final long paramLong1, final long paramLong2, long paramLong3, final long paramLong4, long paramLong5)
  {
    final Activity localActivity = CC_Activity.GetActivity();
    m_pStaticWebView = paramScrollableWebView;
    synchronized (new Runnable()
    {
      public void run()
      {
        int i = 1;
        for (;;)
        {
          try
          {
            if (UserInterfaceManager_Class.m_pStaticWebView == null)
            {
              if (i != 0) {
                UserInterfaceManager_Class.m_pStaticWebView = new UserInterfaceManager_Class.ScrollableWebView(UserInterfaceManager_Class.this, localActivity);
              }
              UserInterfaceManager_Class.m_pStaticWebView.getSettings().setJavaScriptEnabled(true);
              UserInterfaceManager_Class.m_pStaticWebView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
              UserInterfaceManager_Class.m_pStaticWebView.setWebViewClient(new WebViewClient()
              {
                public void onPageFinished(WebView paramAnonymous2WebView, String paramAnonymous2String)
                {
                  UserInterfaceManager_Class.this.WebBrowserLoadFinishCallback(UserInterfaceManager_Class.12.this.val$nLoadFinishCallback, UserInterfaceManager_Class.12.this.val$nUserPointer);
                }
                
                public void onPageStarted(WebView paramAnonymous2WebView, String paramAnonymous2String, Bitmap paramAnonymous2Bitmap)
                {
                  UserInterfaceManager_Class.this.WebBrowserLoadStartCallback(paramAnonymous2String, UserInterfaceManager_Class.12.this.val$nLoadStartCallback, UserInterfaceManager_Class.12.this.val$nUserPointer);
                }
                
                public void onReceivedError(WebView paramAnonymous2WebView, int paramAnonymous2Int, String paramAnonymous2String1, String paramAnonymous2String2)
                {
                  UserInterfaceManager_Class.this.WebBrowserLoadFailCallback(UserInterfaceManager_Class.12.this.val$nLoadFailCallback, UserInterfaceManager_Class.12.this.val$nUserPointer);
                }
                
                public boolean shouldOverrideUrlLoading(WebView paramAnonymous2WebView, String paramAnonymous2String)
                {
                  return UserInterfaceManager_Class.this.WebBrowserLoadShouldStartCallback(paramAnonymous2String, UserInterfaceManager_Class.12.this.val$nLoadShouldStartCallback, UserInterfaceManager_Class.12.this.val$nUserPointer);
                }
              });
              UserInterfaceManager_Class.m_pStaticWebView.setOnTouchListener(new View.OnTouchListener()
              {
                public boolean onTouch(View paramAnonymous2View, MotionEvent paramAnonymous2MotionEvent)
                {
                  switch (paramAnonymous2MotionEvent.getAction())
                  {
                  }
                  for (;;)
                  {
                    return false;
                    if (!paramAnonymous2View.hasFocus()) {
                      paramAnonymous2View.requestFocus();
                    }
                  }
                }
              });
              RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(this.val$iWidth, this.val$iHeight);
              localLayoutParams.leftMargin = this.val$iX;
              localLayoutParams.topMargin = this.val$iY;
              UserInterfaceManager_Class.m_pStaticWebView.setLayoutParams(localLayoutParams);
              if (i != 0) {
                this.val$pLayout.addView(UserInterfaceManager_Class.m_pStaticWebView);
              }
              notify();
              return;
            }
          }
          finally {}
          i = 0;
        }
      }
    })
    {
      localActivity.runOnUiThread(???);
    }
    try
    {
      ???.wait();
      label54:
      return m_pStaticWebView;
      localObject = finally;
      throw localObject;
    }
    catch (Exception localException)
    {
      break label54;
    }
  }
  
  void WebBrowserOpenUrl(final WebView paramWebView, final String paramString1, final String paramString2, boolean paramBoolean, final String paramString3)
  {
    CC_Activity.GetActivity().runOnUiThread(new Runnable()
    {
      public void run()
      {
        if ((paramString3 != null) && (paramString3.length() > 0))
        {
          paramWebView.postUrl(paramString1 + paramString2, EncodingUtils.getBytes(paramString3, "UTF-8"));
          return;
        }
        paramWebView.loadUrl(paramString1 + paramString2);
      }
    });
  }
  
  RelativeLayout WindowCreate(int paramInt1, int paramInt2, final int paramInt3, final int paramInt4)
  {
    final Activity localActivity = CC_Activity.GetActivity();
    final RelativeLayout localRelativeLayout = new RelativeLayout(localActivity);
    localActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        localActivity.addContentView(localRelativeLayout, new RelativeLayout.LayoutParams(paramInt3, paramInt4));
      }
    });
    return localRelativeLayout;
  }
  
  void WindowHide(final RelativeLayout paramRelativeLayout)
  {
    final Activity localActivity = CC_Activity.GetActivity();
    localActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        View localView = localActivity.getCurrentFocus();
        if (localView != null) {
          ((InputMethodManager)localActivity.getSystemService("input_method")).hideSoftInputFromWindow(localView.getApplicationWindowToken(), 0);
        }
        paramRelativeLayout.setVisibility(8);
      }
    });
  }
  
  void WindowShow(final RelativeLayout paramRelativeLayout)
  {
    Log.i("TEST User Act", "--- UserInterfaceManager_Class WindowShow");
    CC_Activity.GetActivity().runOnUiThread(new Runnable()
    {
      public void run()
      {
        Log.i("TEST User Act", "### UserInterfaceManager_Class WindowShow");
        paramRelativeLayout.setVisibility(0);
      }
    });
  }
  
  public class ScrollableWebView
    extends WebView
  {
    public ScrollableWebView(Context paramContext)
    {
      super();
    }
    
    public void ScrollView(int paramInt1, int paramInt2)
    {
      int i = computeHorizontalScrollRange() - getWidth();
      int j = clamp(paramInt1 + getScrollX(), 0, i);
      int k = computeVerticalScrollRange() - getHeight();
      scrollTo(j, clamp(paramInt2 + getScrollY(), 0, k));
    }
    
    int clamp(int paramInt1, int paramInt2, int paramInt3)
    {
      if (paramInt1 < paramInt2) {
        return paramInt2;
      }
      if (paramInt1 > paramInt3) {
        return paramInt3;
      }
      return paramInt1;
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.firemonkeys.cloudcellapi.UserInterfaceManager_Class
 * JD-Core Version:    0.7.0.1
 */