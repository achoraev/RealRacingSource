package com.supersonicads.sdk.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.provider.Settings.System;
import android.view.Display;
import android.view.KeyEvent;
import android.view.OrientationEventListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.supersonicads.sdk.agent.SupersonicAdsPublisherAgent;
import com.supersonicads.sdk.data.SSAEnums.BackButtonState;
import com.supersonicads.sdk.precache.CacheManager;
import com.supersonicads.sdk.utils.Logger;
import com.supersonicads.sdk.utils.SDKUtils;

public class ControllerActivity
  extends Activity
  implements WebViewController.OnWebViewControllerChangeListener
{
  private static final String TAG = ControllerActivity.class.getSimpleName();
  private static final int WEB_VIEW_VIEW_ID = 1;
  final RelativeLayout.LayoutParams MATCH_PARENT_LAYOUT_PARAMS = new RelativeLayout.LayoutParams(-1, -1);
  public int applicationRotation = -1;
  private boolean calledFromOnCreate = false;
  public int currentRequestedRotation = -1;
  private RelativeLayout mContainer;
  private int mOrientation;
  private OrientationManager mOrientationManager;
  public int mOrientationType = -1;
  private WebViewController mWebViewController;
  private FrameLayout mWebViewFrameContainer;
  
  private void handleOrientationState(String paramString, int paramInt)
  {
    if (paramString != null)
    {
      if (!"landscape".equalsIgnoreCase(paramString)) {
        break label23;
      }
      setInitiateLandscapeOrientation();
      setOrientationEventListener(1);
    }
    label23:
    do
    {
      return;
      if ("portrait".equalsIgnoreCase(paramString))
      {
        setInitiatePortraitOrientation();
        setOrientationEventListener(0);
        return;
      }
      if ("application".equalsIgnoreCase(paramString))
      {
        setOrientationEventListener(2);
        return;
      }
    } while (!"device".equalsIgnoreCase(paramString));
    if (isDeviceOrientationLocked())
    {
      setRequestedOrientation(1);
      return;
    }
    setOrientationEventListener(2);
  }
  
  private void hideActivityTitle()
  {
    requestWindowFeature(1);
  }
  
  private void hideActivtiyStatusBar()
  {
    getWindow().setFlags(1024, 1024);
  }
  
  private boolean isDeviceOrientationLocked()
  {
    return Settings.System.getInt(getContentResolver(), "accelerometer_rotation", 0) != 1;
  }
  
  private void keepScreenOn()
  {
    getWindow().addFlags(128);
  }
  
  private void removeWebViewContainerView()
  {
    if (this.mContainer != null)
    {
      ViewGroup localViewGroup = (ViewGroup)this.mWebViewFrameContainer.getParent();
      if (localViewGroup.findViewById(1) != null) {
        localViewGroup.removeView(this.mWebViewFrameContainer);
      }
    }
  }
  
  private void setInitiateLandscapeOrientation()
  {
    int i = ((WindowManager)getSystemService("window")).getDefaultDisplay().getRotation();
    Logger.i(TAG, "setInitiateLandscapeOrientation");
    if (i == 0)
    {
      Logger.i(TAG, "ROTATION_0");
      this.mOrientation = 0;
      setRequestedOrientation(0);
      return;
    }
    if (i == 2)
    {
      Logger.i(TAG, "ROTATION_180");
      this.mOrientation = 8;
      setRequestedOrientation(8);
      return;
    }
    if (i == 3)
    {
      Logger.i(TAG, "ROTATION_270 Right Landscape");
      this.mOrientation = 8;
      setRequestedOrientation(8);
      return;
    }
    if (i == 1)
    {
      Logger.i(TAG, "ROTATION_90 Left Landscape");
      this.mOrientation = 0;
      setRequestedOrientation(0);
      return;
    }
    Logger.i(TAG, "No Rotation");
  }
  
  private void setInitiatePortraitOrientation()
  {
    int i = SDKUtils.getRotation(this);
    Logger.i(TAG, "setInitiatePortraitOrientation");
    if (i == 0)
    {
      Logger.i(TAG, "ROTATION_0");
      this.mOrientation = 1;
      setRequestedOrientation(1);
      return;
    }
    if (i == 2)
    {
      Logger.i(TAG, "ROTATION_180");
      this.mOrientation = 9;
      setRequestedOrientation(9);
      return;
    }
    if (i == 1)
    {
      Logger.i(TAG, "ROTATION_270 Right Landscape");
      this.mOrientation = 1;
      setRequestedOrientation(1);
      return;
    }
    if (i == 3)
    {
      Logger.i(TAG, "ROTATION_90 Left Landscape");
      this.mOrientation = 1;
      setRequestedOrientation(1);
      return;
    }
    Logger.i(TAG, "No Rotation");
  }
  
  private void setOrientationEventListener(int paramInt)
  {
    this.mOrientationType = paramInt;
    Logger.i(TAG, "setOrientationEventListener(" + paramInt + ")");
  }
  
  private void setRotation(int paramInt)
  {
    if (paramInt == 0)
    {
      Logger.i(TAG, "ROTATION_0");
      this.mOrientation = 1;
      setRequestedOrientation(1);
      return;
    }
    if (paramInt == 2)
    {
      Logger.i(TAG, "ROTATION_180");
      this.mOrientation = 9;
      setRequestedOrientation(9);
      return;
    }
    if (paramInt == 3)
    {
      Logger.i(TAG, "ROTATION_270 Right Landscape");
      this.mOrientation = 8;
      setRequestedOrientation(8);
      return;
    }
    if (paramInt == 1)
    {
      Logger.i(TAG, "ROTATION_90 Left Landscape");
      this.mOrientation = 0;
      setRequestedOrientation(0);
      return;
    }
    Logger.i(TAG, "No Rotation");
  }
  
  public void onBackPressed()
  {
    Logger.i(TAG, "onBackPressed");
    SSAEnums.BackButtonState localBackButtonState = new CacheManager(this).getBackButtonState();
    switch (localBackButtonState)
    {
    }
    do
    {
      return;
      super.onBackPressed();
      return;
    } while (this.mWebViewController == null);
    this.mWebViewController.nativeNavigationPressed("back");
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    Logger.i(TAG, "onCreate");
    hideActivityTitle();
    hideActivtiyStatusBar();
    keepScreenOn();
    this.mWebViewController = SupersonicAdsPublisherAgent.getInstance(this).getWebViewController();
    this.mWebViewController.setActivity(this);
    this.mWebViewController.setId(1);
    this.mWebViewController.setOnWebViewControllerChangeListener(this);
    this.mContainer = new RelativeLayout(this);
    setContentView(this.mContainer, this.MATCH_PARENT_LAYOUT_PARAMS);
    this.mWebViewFrameContainer = this.mWebViewController.getLayout();
    if ((this.mContainer.findViewById(1) == null) && (this.mWebViewFrameContainer.getParent() != null))
    {
      this.calledFromOnCreate = true;
      finish();
    }
    Intent localIntent = getIntent();
    String str = localIntent.getStringExtra("orientation_set_flag");
    int i = localIntent.getIntExtra("rotation_set_flag", 0);
    this.mOrientationManager = new OrientationManager(this, 3);
    if (this.mOrientationManager.canDetectOrientation()) {
      this.mOrientationManager.enable();
    }
    handleOrientationState(str, i);
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    Logger.i(TAG, "onDestroy");
    if (this.calledFromOnCreate) {
      removeWebViewContainerView();
    }
    if (this.mOrientationManager != null) {
      this.mOrientationManager.disable();
    }
    if (this.mWebViewController != null) {
      this.mWebViewController.setState(WebViewController.State.Gone);
    }
  }
  
  public void onHide()
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        ControllerActivity.this.finish();
      }
    });
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramInt == 4) && (this.mWebViewController.inCustomView()))
    {
      this.mWebViewController.hideCustomView();
      return true;
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  protected void onPause()
  {
    super.onPause();
    Logger.i(TAG, "onPause");
    if (this.mWebViewController != null)
    {
      this.mWebViewController.unregisterConnectionReceiver(this);
      this.mWebViewController.pause();
      this.mWebViewController.viewableChange(false, "main");
    }
    removeWebViewContainerView();
  }
  
  protected void onResume()
  {
    super.onResume();
    Logger.i(TAG, "onResume");
    this.mContainer.addView(this.mWebViewFrameContainer, this.MATCH_PARENT_LAYOUT_PARAMS);
    if (this.mWebViewController != null)
    {
      this.mWebViewController.registerConnectionReceiver(this);
      this.mWebViewController.resume();
      this.mWebViewController.viewableChange(true, "main");
    }
  }
  
  public void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    this.mWebViewController.saveState(paramBundle);
  }
  
  public void onSetOrientationCalled(String paramString, int paramInt)
  {
    handleOrientationState(paramString, paramInt);
  }
  
  protected void onUserLeaveHint()
  {
    super.onUserLeaveHint();
    Logger.i(TAG, "onUserLeaveHint");
  }
  
  public void setRequestedOrientation(int paramInt)
  {
    if (this.currentRequestedRotation != paramInt)
    {
      Logger.i(TAG, "Rotation: Req = " + paramInt + " Curr = " + this.currentRequestedRotation);
      this.currentRequestedRotation = paramInt;
      super.setRequestedOrientation(paramInt);
    }
  }
  
  private class OrientationManager
    extends OrientationEventListener
  {
    public int currentOrientation = 1;
    public int defaultOrientation = 1;
    
    public OrientationManager(Context paramContext, int paramInt)
    {
      super(paramInt);
      this.defaultOrientation = SDKUtils.getDeviceDefaultOrientation(paramContext);
      this.currentOrientation = paramContext.getResources().getConfiguration().orientation;
    }
    
    public void onOrientationChanged(int paramInt)
    {
      if (paramInt == -1) {
        ControllerActivity.this.setRequestedOrientation(ControllerActivity.this.mOrientation);
      }
      do
      {
        return;
        if ((paramInt <= 45) || (paramInt > 315))
        {
          switch (ControllerActivity.this.mOrientationType)
          {
          }
          for (;;)
          {
            ControllerActivity.this.setRequestedOrientation(ControllerActivity.this.mOrientation);
            return;
            if (this.defaultOrientation == 2)
            {
              ControllerActivity.this.mOrientation = 9;
            }
            else
            {
              ControllerActivity.this.mOrientation = 1;
              continue;
              ControllerActivity.this.mOrientation = 0;
              continue;
              if (this.defaultOrientation == 2)
              {
                if (this.currentOrientation == 2) {
                  ControllerActivity.this.mOrientation = 0;
                } else {
                  ControllerActivity.this.mOrientation = 0;
                }
              }
              else {
                ControllerActivity.this.mOrientation = 1;
              }
            }
          }
        }
        if ((paramInt > 45) && (paramInt <= 135))
        {
          switch (ControllerActivity.this.mOrientationType)
          {
          }
          for (;;)
          {
            ControllerActivity.this.setRequestedOrientation(ControllerActivity.this.mOrientation);
            return;
            ControllerActivity.this.mOrientation = 1;
            continue;
            ControllerActivity.this.mOrientation = 8;
            continue;
            if (this.defaultOrientation == 2) {
              ControllerActivity.this.mOrientation = 1;
            } else {
              ControllerActivity.this.mOrientation = 8;
            }
          }
        }
        if ((paramInt > 135) && (paramInt <= 225))
        {
          switch (ControllerActivity.this.mOrientationType)
          {
          }
          for (;;)
          {
            ControllerActivity.this.setRequestedOrientation(ControllerActivity.this.mOrientation);
            return;
            if (this.defaultOrientation == 2)
            {
              ControllerActivity.this.mOrientation = 9;
            }
            else
            {
              ControllerActivity.this.mOrientation = 1;
              continue;
              ControllerActivity.this.mOrientation = 8;
              continue;
              if (this.defaultOrientation == 2)
              {
                if (this.currentOrientation == 2) {
                  ControllerActivity.this.mOrientation = 8;
                } else {
                  ControllerActivity.this.mOrientation = 8;
                }
              }
              else {
                ControllerActivity.this.mOrientation = 9;
              }
            }
          }
        }
      } while ((paramInt <= 225) || (paramInt > 315));
      switch (ControllerActivity.this.mOrientationType)
      {
      }
      for (;;)
      {
        ControllerActivity.this.setRequestedOrientation(ControllerActivity.this.mOrientation);
        return;
        ControllerActivity.this.mOrientation = 9;
        continue;
        ControllerActivity.this.mOrientation = 0;
        continue;
        if (this.defaultOrientation == 2)
        {
          if (this.currentOrientation == 2) {
            ControllerActivity.this.mOrientation = 9;
          } else {
            ControllerActivity.this.mOrientation = 9;
          }
        }
        else {
          ControllerActivity.this.mOrientation = 0;
        }
      }
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.supersonicads.sdk.controller.ControllerActivity
 * JD-Core Version:    0.7.0.1
 */