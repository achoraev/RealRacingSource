package com.firemint.realracing;

import android.annotation.TargetApi;
import android.app.AlertDialog.Builder;
import android.app.Application;
import android.app.UiModeManager;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.Settings.System;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnSystemUiVisibilityChangeListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.crashlytics.android.Crashlytics;
import com.firemonkeys.cloudcellapi.CC_Activity;
import com.popcap.ea2.EASquared;

public class MainActivity
  extends CC_Activity
  implements MessageCallback
{
  public static final int ANDROID_ORIENTATION_LANDSCAPE = 2;
  public static final int ANDROID_ORIENTATION_PORTRAIT = 1;
  public static final int ANDROID_ORIENTATION_UNDEFINED = 0;
  public static final int ANDROID_ROTATION_0 = 0;
  public static final int ANDROID_ROTATION_180 = 2;
  public static final int ANDROID_ROTATION_270 = 3;
  public static final int ANDROID_ROTATION_90 = 1;
  public static final String APPLICATION_LIB_NAME = "RealRacing3";
  public static final int ButtonId_CENTRAL = 2;
  public static final int ButtonId_LEFT = 0;
  public static final int ButtonId_RIGHT = 1;
  public static final int GAMESTATE_CREATED = 1;
  public static final int GAMESTATE_DESTROYED = 6;
  public static final int GAMESTATE_NONE = 0;
  public static final int GAMESTATE_NOSTORAGE = 5;
  public static final int GAMESTATE_RUNNING = 3;
  public static final int GAMESTATE_STARTED = 2;
  public static final int GAMESTATE_STOPPED = 4;
  public static final String LOG_TAG = "RealRacing3";
  static final int WIFI_SETTINGS = 329418467;
  public static MainActivity instance;
  public static boolean m_WIFISettingsShown = false;
  private static boolean m_bIsAmazon;
  private static boolean m_bIsAndroidTv;
  private final Runnable HideSystemKeys = new Runnable()
  {
    @TargetApi(19)
    public void run()
    {
      if (MainActivity.IsAtLeastAPI(19)) {
        MainActivity.this.getWindow().getDecorView().setSystemUiVisibility(4614);
      }
      while ((!MainActivity.IsAtLeastAPI(14)) || ((0x1 & MainActivity.this.getWindow().getDecorView().getSystemUiVisibility()) == 1)) {
        return;
      }
      MainActivity.this.getWindow().getDecorView().setSystemUiVisibility(1);
    }
  };
  public Handler handler;
  private AudioStreamManager m_audioStreamManager = new AudioStreamManager();
  private long m_cheatPtr = 0L;
  private String m_cheatText = "";
  private CheatView m_cheatView = null;
  public ControllerManager m_controllerManager = null;
  private boolean m_delayedResumeJNI = false;
  private FrameLayout m_frameLayout;
  private int m_gameState = 0;
  private GLView m_glView = null;
  private boolean m_hasFocus = false;
  private boolean m_keyboardInputAlphaNumericOnly = false;
  private int m_keyboardInputMaxLength = 0;
  private boolean m_keyboardInputUppercaseOnly = false;
  private long m_keyboardPtr = 0L;
  private String m_keyboardText = "";
  private CheatView m_keyboardView = null;
  private View m_loadingView = null;
  private NimbleManager m_nimbleManager = new NimbleManager();
  private boolean m_paused = false;
  private String m_sLoadingString = "";
  private View m_splashView = null;
  int oldGameState = 0;
  
  static
  {
    System.loadLibrary("fmodex");
    System.loadLibrary("RealRacing3");
    instance = null;
    m_bIsAmazon = false;
    m_bIsAndroidTv = false;
  }
  
  public static boolean IsAtLeastAPI(int paramInt)
  {
    return Build.VERSION.SDK_INT >= paramInt;
  }
  
  private void OnSystemUiVisibilityChanged()
  {
    if (!getIsAmazon()) {
      getGLView().postDelayed(this.HideSystemKeys, 1000L);
    }
  }
  
  @TargetApi(14)
  private void SetupSystemUiVisibility()
  {
    getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener()
    {
      public void onSystemUiVisibilityChange(int paramAnonymousInt)
      {
        if (MainActivity.this.m_hasFocus) {
          MainActivity.this.OnSystemUiVisibilityChanged();
        }
      }
    });
  }
  
  static int getGameState()
  {
    if (instance == null) {
      return 0;
    }
    return instance.m_gameState;
  }
  
  static boolean getIsAmazon()
  {
    return m_bIsAmazon;
  }
  
  static boolean getIsAndroidTv()
  {
    return m_bIsAndroidTv;
  }
  
  public static void logi(String paramString)
  {
    if (("RealRacing3" != null) && ("RealRacing3".length() > 0)) {
      Log.i("RealRacing3", paramString);
    }
  }
  
  static boolean setGameState(int paramInt)
  {
    if (instance == null) {
      return false;
    }
    logi("setGameState(" + paramInt + ")");
    instance.m_gameState = paramInt;
    return true;
  }
  
  public boolean ExactlyAPI(int paramInt)
  {
    return Build.VERSION.SDK_INT == paramInt;
  }
  
  public native void HandleIntent(Intent paramIntent);
  
  void HandleLaunchURL(Intent paramIntent)
  {
    if (paramIntent != null)
    {
      String str = paramIntent.getStringExtra("launchURL");
      if ((str == null) || (str.length() == 0)) {
        str = paramIntent.getDataString();
      }
      if ((str != null) && (str.length() > 0)) {
        setLaunchURL(str);
      }
    }
  }
  
  public boolean IsBelowAPI(int paramInt)
  {
    return Build.VERSION.SDK_INT < paramInt;
  }
  
  public native void SetRunningOnAndroidTv(boolean paramBoolean);
  
  public native void alertMessageExecuteCallback(int paramInt);
  
  public native boolean checkMemoryJNI();
  
  boolean checkStorage(boolean paramBoolean)
  {
    if ((this.m_gameState != 5) || (paramBoolean))
    {
      logi("checkStorage(): storage state = " + Environment.getExternalStorageState());
      if ((Environment.getExternalStorageState().equals("mounted")) || (Environment.getExternalStorageState().equals("mounted_ro"))) {
        break label218;
      }
      if (this.m_gameState != 5) {
        this.oldGameState = this.m_gameState;
      }
      setGameState(5);
      logi("checkStorage(): game state = " + this.m_gameState + " old state=" + this.oldGameState);
      if ((this.oldGameState <= 1) && (checkMemoryJNI())) {
        new Message(getString(2131099718), getString(2131099719), getString(2131099721), getString(2131099720), null, 0, 0, 0, this);
      }
    }
    else
    {
      return false;
    }
    new Message(getString(2131099718), getString(2131099719), null, getString(2131099720), null, 0, 0, 0, null);
    return false;
    label218:
    return true;
  }
  
  protected View createLoadingView(String paramString)
  {
    Platform.getScreenWidth();
    int i = Platform.getScreenHeight();
    TextView localTextView = new TextView(this);
    localTextView.setText(paramString);
    localTextView.setTextColor(-1);
    localTextView.setTextSize(32.0F);
    localTextView.setVisibility(0);
    localTextView.setGravity(1);
    localTextView.setPadding(0, i - i / 7, 0, 0);
    return localTextView;
  }
  
  protected View createSplashView()
  {
    int i = Platform.getScreenWidth();
    int j = Platform.getScreenHeight();
    if (i < j)
    {
      int i1 = i;
      i = j;
      j = i1;
    }
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;
    try
    {
      Bitmap localBitmap1 = BitmapFactory.decodeStream(getAssets().open("splash.png"), null, localOptions);
      float f = i / j;
      int k = localBitmap1.getWidth();
      int m = localBitmap1.getHeight();
      int n = (int)((f * m - k) / 2.0F);
      Bitmap localBitmap2 = Bitmap.createBitmap(n + (n + k), m, Bitmap.Config.ARGB_8888);
      localBitmap2.eraseColor(Color.argb(0, 0, 0, 0));
      Paint localPaint = new Paint();
      localPaint.setFilterBitmap(true);
      Canvas localCanvas = new Canvas(localBitmap2);
      Rect localRect1 = new Rect(0, 0, 1, m);
      localCanvas.drawBitmap(localBitmap1, localRect1, new RectF(0.0F, 0.0F, n + (n + k), m), localPaint);
      Rect localRect2 = new Rect(0, 0, k, m);
      localCanvas.drawBitmap(localBitmap1, localRect2, new RectF(n, 0.0F, n + k, m), localPaint);
      ImageView localImageView = new ImageView(this);
      localImageView.setImageBitmap(localBitmap2);
      return localImageView;
    }
    catch (Exception localException)
    {
      Log.e("RealRacing3", "Failed loading splash bitmap!", localException);
      View localView = new View(this);
      return localView;
    }
  }
  
  public TextField createTextField(long paramLong, String paramString)
  {
    logi("createTextField(): " + paramString);
    final TextField localTextField = new TextField(instance, paramLong, paramString);
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        MainActivity.logi("Attached text field");
        MainActivity.this.m_frameLayout.addView(localTextField);
      }
    });
    return localTextField;
  }
  
  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    if (!isCheatInputShown())
    {
      if ((this.m_controllerManager != null) && (this.m_controllerManager.HandleKeyEvents(paramKeyEvent))) {
        return true;
      }
      if ((isMenuBackKey(paramKeyEvent.getKeyCode())) && (paramKeyEvent.getRepeatCount() == 0))
      {
        this.m_glView.queueEvent(new KeyEventRunnable(paramKeyEvent.getKeyCode(), paramKeyEvent.getAction()));
        return true;
      }
    }
    return super.dispatchKeyEvent(paramKeyEvent);
  }
  
  public void finishActivity()
  {
    finish();
  }
  
  public FrameLayout getFrameLayout()
  {
    return this.m_frameLayout;
  }
  
  public GLView getGLView()
  {
    return this.m_glView;
  }
  
  public boolean getPaused()
  {
    return this.m_paused;
  }
  
  public int getScreenOrientation()
  {
    switch (getResources().getConfiguration().orientation)
    {
    default: 
      return 0;
    case 1: 
      return 1;
    }
    return 2;
  }
  
  public int getScreenRotation()
  {
    switch (getWindowManager().getDefaultDisplay().getRotation())
    {
    case 3: 
    default: 
      return 3;
    case 0: 
      return 0;
    case 1: 
      return 1;
    }
    return 2;
  }
  
  public void hideCheatInput()
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        if (MainActivity.this.m_cheatView != null)
        {
          MainActivity.this.m_cheatView.end();
          if (MainActivity.this.m_cheatView.getParent() != null) {
            MainActivity.this.m_frameLayout.removeView(MainActivity.this.m_cheatView);
          }
        }
      }
    });
  }
  
  public void hideKeyboardInput()
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        if (MainActivity.this.m_keyboardView != null)
        {
          MainActivity.this.m_keyboardView.end();
          if (MainActivity.this.m_keyboardView.getParent() != null) {
            MainActivity.this.m_frameLayout.removeView(MainActivity.this.m_keyboardView);
          }
          MainActivity.this.OnSystemUiVisibilityChanged();
        }
      }
    });
  }
  
  public void hideLoadingSplash()
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        if (MainActivity.this.m_frameLayout.indexOfChild(MainActivity.this.m_splashView) >= 0)
        {
          MainActivity.this.m_frameLayout.removeView(MainActivity.this.m_splashView);
          MainActivity.this.m_frameLayout.removeView(MainActivity.this.m_loadingView);
        }
      }
    });
  }
  
  public void hideSplash()
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        if (MainActivity.this.m_frameLayout.indexOfChild(MainActivity.this.m_splashView) >= 0) {
          MainActivity.this.m_frameLayout.removeView(MainActivity.this.m_splashView);
        }
      }
    });
  }
  
  public boolean isCheatInputShown()
  {
    return (this.m_cheatView != null) && (this.m_cheatView.getParent() != null);
  }
  
  public boolean isKeyboardInputShown()
  {
    return (this.m_keyboardView != null) && (this.m_keyboardView.getParent() != null);
  }
  
  public boolean isMenuBackKey(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return false;
    }
    return true;
  }
  
  public boolean isSystemKey(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return false;
    }
    return true;
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt1 == 329418467) {
      m_WIFISettingsShown = false;
    }
    this.m_nimbleManager.onActivityResult(paramInt1, paramInt2, paramIntent);
  }
  
  public void onBackPressed()
  {
    if (this.m_nimbleManager.onBackPressed()) {
      super.onBackPressed();
    }
  }
  
  public native void onCheatInputDone(String paramString, long paramLong);
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
  }
  
  public native void onConfigurationChangedJNI();
  
  protected void onCreate(Bundle paramBundle)
  {
    AppProxy.SetActivity(this);
    logi("onCreate(): start");
    super.onCreate(paramBundle);
    this.handler = new Handler();
    HandleLaunchURL(getIntent());
    if (this.m_gameState == 6) {
      finish();
    }
    while (this.m_gameState >= 1) {
      return;
    }
    instance = this;
    setGameState(1);
    m_bIsAmazon = getApplication().getPackageName().contains("_azn");
    if (((UiModeManager)getSystemService("uimode")).getCurrentModeType() == 4) {}
    for (boolean bool = true;; bool = false)
    {
      m_bIsAndroidTv = bool;
      SetRunningOnAndroidTv(getIsAndroidTv());
      getContentResolver().registerContentObserver(Settings.System.CONTENT_URI, true, new ContentObserver(new Handler())
      {
        public void onChange(boolean paramAnonymousBoolean)
        {
          onChange(paramAnonymousBoolean, null);
        }
        
        public void onChange(boolean paramAnonymousBoolean, Uri paramAnonymousUri)
        {
          MainActivity.logi("contentchange");
          MainActivity.this.updateScreenOrientationConfiguration();
        }
      });
      updateScreenOrientationConfiguration();
      getWindow().requestFeature(1);
      getWindow().addFlags(1152);
      this.m_splashView = createSplashView();
      this.m_glView = new GLView(getApplication());
      SetupSystemUiVisibility();
      this.m_frameLayout = new FrameLayout(this);
      this.m_frameLayout.addView(this.m_glView);
      this.m_frameLayout.addView(this.m_splashView);
      setContentView(this.m_frameLayout);
      super.setInstances(this.m_glView, this.m_frameLayout);
      if (checkStorage(false)) {
        onCreateJNI();
      }
      MoviePlayer.startup(this, this.m_frameLayout, this.handler);
      this.m_nimbleManager.initialise(this);
      this.m_nimbleManager.onCreate(paramBundle);
      EASquared.applicationCreate(this);
      Crashlytics.start(this);
      this.m_controllerManager = new ControllerManager(this);
      logi("onCreate(): finish");
      return;
    }
  }
  
  public native void onCreateJNI();
  
  public void onDelayedResume()
  {
    if (this.m_delayedResumeJNI)
    {
      this.m_delayedResumeJNI = false;
      this.m_glView.queueEvent(new Runnable()
      {
        public void run()
        {
          MainActivity.this.onResumeJNI();
        }
      });
    }
  }
  
  protected void onDestroy()
  {
    logi("onDestroy(): start");
    super.onDestroy();
    this.m_nimbleManager.onDestroy();
    EASquared.applicationDestroy(this);
    if (this.m_controllerManager != null) {
      this.m_controllerManager.onDestroy();
    }
    if (this.m_gameState < 6)
    {
      setGameState(6);
      onDestroyJNI();
      super.clearInstances();
      this.m_glView = null;
      this.m_splashView = null;
      this.m_cheatView = null;
      this.m_frameLayout = null;
      instance = null;
      AppProxy.SetActivity(null);
      System.exit(0);
    }
    logi("onDestroy(): finish");
  }
  
  public native void onDestroyJNI();
  
  public boolean onGenericMotionEvent(MotionEvent paramMotionEvent)
  {
    if ((!isCheatInputShown()) && (this.m_controllerManager != null))
    {
      if (this.m_controllerManager.HandleMotionEvents(paramMotionEvent)) {
        return true;
      }
      this.m_controllerManager.Log("Not controller motion event");
    }
    return super.onGenericMotionEvent(paramMotionEvent);
  }
  
  public native void onKeyPressed(int paramInt);
  
  public native void onKeyReleased(int paramInt);
  
  public native void onKeyboardInputDone(String paramString, long paramLong);
  
  public native void onKeyboardInputUpdate(String paramString, long paramLong);
  
  public void onMessage(int paramInt)
  {
    if ((paramInt == 0) && (checkStorage(true)))
    {
      onCreateJNI();
      this.m_audioStreamManager.onStart();
      onStartJNI();
      if (this.m_hasFocus) {
        onDelayedResume();
      }
      this.m_paused = false;
      this.oldGameState = 3;
      setGameState(this.oldGameState);
    }
  }
  
  protected void onNewIntent(Intent paramIntent)
  {
    logi("onNewIntent");
    super.onNewIntent(paramIntent);
    HandleLaunchURL(paramIntent);
    HandleIntent(paramIntent);
  }
  
  protected void onPause()
  {
    logi("onPause(): start");
    this.m_delayedResumeJNI = false;
    super.onPause();
    showSplash();
    logi("onPause(): glPause");
    this.m_glView.onPause();
    this.m_nimbleManager.onPause();
    EASquared.applicationPause(this);
    MoviePlayer.pause();
    if (this.m_controllerManager != null) {
      this.m_controllerManager.onPause();
    }
    if (this.m_gameState == 3)
    {
      logi("onPause(): pauseJNI");
      setGameState(2);
      this.m_paused = true;
      onPauseJNI();
    }
    if (isKeyboardInputShown()) {
      this.m_keyboardView.onPause();
    }
    logi("onPause(): finish");
  }
  
  public native void onPauseJNI();
  
  public native void onRecievedMemoryWarningJNI();
  
  protected void onRestart()
  {
    logi("onRestart(): start");
    super.onRestart();
    this.m_nimbleManager.onRestart();
    EASquared.applicationRestart(this);
    logi("onRestart(): finish");
  }
  
  protected void onRestoreInstanceState(Bundle paramBundle)
  {
    this.m_nimbleManager.onRestoreInstanceState(paramBundle);
  }
  
  protected void onResume()
  {
    logi("onResume(): start");
    this.m_delayedResumeJNI = true;
    super.onResume();
    this.m_glView.onResume();
    this.m_nimbleManager.onResume();
    EASquared.applicationResume(this);
    if ((this.m_gameState != 5) && (this.m_gameState != 3))
    {
      setGameState(3);
      this.m_delayedResumeJNI = false;
      this.m_glView.queueEvent(new Runnable()
      {
        public void run()
        {
          MainActivity.this.onResumeJNI();
        }
      });
      this.m_paused = false;
    }
    if (isKeyboardInputShown()) {
      this.m_keyboardView.onResume();
    }
    MoviePlayer.resume();
    if (this.m_controllerManager != null) {
      this.m_controllerManager.onResume();
    }
    logi("onResume(): finish");
  }
  
  public native void onResumeJNI();
  
  protected void onSaveInstanceState(Bundle paramBundle)
  {
    this.m_nimbleManager.onSaveInstanceState(paramBundle);
  }
  
  protected void onStart()
  {
    logi("onStart(): start");
    super.onStart();
    if (!checkStorage(false)) {}
    while ((this.m_gameState >= 2) && (this.m_gameState < 4)) {
      return;
    }
    setGameState(2);
    this.m_audioStreamManager.onStart();
    onStartJNI();
    this.m_nimbleManager.onStart();
    logi("onStart(): finish");
  }
  
  public native void onStartJNI();
  
  protected void onStop()
  {
    logi("onStop(): start");
    super.onStop();
    if ((this.m_gameState != 5) && (this.m_gameState < 4))
    {
      setGameState(4);
      this.m_audioStreamManager.onStop();
      onStopJNI();
    }
    this.m_nimbleManager.onStop();
    EASquared.applicationStop(this);
    logi("onStop(): finish");
  }
  
  public native void onStopJNI();
  
  public native void onTouchBeginJNI(int paramInt, float paramFloat1, float paramFloat2);
  
  public native void onTouchCancelJNI();
  
  public native void onTouchEndJNI(int paramInt, float paramFloat1, float paramFloat2, boolean paramBoolean);
  
  public native void onTouchMoveJNI(int paramInt, float paramFloat1, float paramFloat2);
  
  public void onTrimMemory(int paramInt)
  {
    Log.d("RealRacing3", "OnTrimMemory(" + paramInt + ")");
    if (paramInt == 15) {
      Log.e("RealRacing3", "Received memory warning: TRIM_MEMORY_RUNNING_CRITICAL)");
    }
    this.m_glView.queueEvent(new Runnable()
    {
      public void run()
      {
        Log.d("RealRacing3", "onRecievedMemoryWarningJNI - Start");
        MainActivity.this.onRecievedMemoryWarningJNI();
        Log.d("RealRacing3", "onRecievedMemoryWarningJNI - End");
      }
    });
  }
  
  public native void onViewChangedJNI(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  public native void onViewCreatedJNI();
  
  public native void onViewRenderJNI(int paramInt1, int paramInt2);
  
  public void onWindowFocusChanged(boolean paramBoolean)
  {
    logi("onWindowFocusChanged(" + paramBoolean + "): start");
    this.m_hasFocus = paramBoolean;
    super.onWindowFocusChanged(paramBoolean);
    this.m_glView.queueEvent(new Runnable()
    {
      public void run()
      {
        MainActivity.this.onWindowFocusChangedJNI(MainActivity.this.m_hasFocus);
      }
    });
    if (this.m_hasFocus)
    {
      if (!this.m_paused) {
        onDelayedResume();
      }
      OnSystemUiVisibilityChanged();
    }
    this.m_nimbleManager.onWindowFocusChanged(paramBoolean);
    logi("onWindowFocusChanged(" + this.m_hasFocus + "): finish");
  }
  
  public native void onWindowFocusChangedJNI(boolean paramBoolean);
  
  public void removeTextField(final TextField paramTextField)
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        if ((paramTextField != null) && (paramTextField.getParent() != null))
        {
          MainActivity.logi("Destroying text field");
          MainActivity.this.m_frameLayout.removeView(paramTextField);
          return;
        }
        MainActivity.logi("Attempting to destroy null text field");
      }
    });
  }
  
  public native void setBackgroundLaunchURL(String paramString1, String paramString2);
  
  public native void setLaunchURL(String paramString);
  
  public native void setMusicEnabled(boolean paramBoolean);
  
  public void showCheatInput(long paramLong)
  {
    this.m_cheatPtr = paramLong;
    this.m_cheatText = "";
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        if (MainActivity.this.m_cheatView == null)
        {
          CheatView.CheatListener local1 = new CheatView.CheatListener()
          {
            public void onInputDone(boolean paramAnonymous2Boolean, CharSequence paramAnonymous2CharSequence)
            {
              MainActivity.access$702(MainActivity.this, paramAnonymous2CharSequence.toString());
              MainActivity.this.hideCheatInput();
              MainActivity.this.m_glView.queueEvent(new Runnable()
              {
                public void run()
                {
                  MainActivity.this.onCheatInputDone(MainActivity.this.m_cheatText, MainActivity.this.m_cheatPtr);
                }
              });
            }
            
            public CharSequence onInputUpdate(boolean paramAnonymous2Boolean, CharSequence paramAnonymous2CharSequence)
            {
              return paramAnonymous2CharSequence;
            }
          };
          MainActivity.access$602(MainActivity.this, new CheatView(MainActivity.instance, local1));
        }
        if (MainActivity.this.m_cheatView.getParent() == null) {
          MainActivity.this.m_frameLayout.addView(MainActivity.this.m_cheatView);
        }
        MainActivity.this.m_cheatView.begin();
      }
    });
  }
  
  public void showKeyboardInput(long paramLong, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    this.m_keyboardPtr = paramLong;
    this.m_keyboardText = "";
    this.m_keyboardInputMaxLength = paramInt;
    this.m_keyboardInputUppercaseOnly = paramBoolean1;
    this.m_keyboardInputAlphaNumericOnly = paramBoolean2;
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        if (MainActivity.this.m_keyboardView == null)
        {
          CheatView.CheatListener local1 = new CheatView.CheatListener()
          {
            public void onInputDone(boolean paramAnonymous2Boolean, CharSequence paramAnonymous2CharSequence)
            {
              MainActivity.access$1102(MainActivity.this, paramAnonymous2CharSequence.toString());
              MainActivity.this.hideKeyboardInput();
              MainActivity.this.m_glView.queueEvent(new Runnable()
              {
                public void run()
                {
                  MainActivity.this.onKeyboardInputDone(MainActivity.this.m_keyboardText, MainActivity.this.m_keyboardPtr);
                }
              });
            }
            
            public CharSequence onInputUpdate(boolean paramAnonymous2Boolean, CharSequence paramAnonymous2CharSequence)
            {
              MainActivity.access$1102(MainActivity.this, paramAnonymous2CharSequence.toString());
              if (MainActivity.this.m_keyboardInputAlphaNumericOnly) {
                MainActivity.access$1102(MainActivity.this, MainActivity.this.m_keyboardText.replaceAll("[^a-zA-Z0-9]", ""));
              }
              for (;;)
              {
                if (MainActivity.this.m_keyboardInputUppercaseOnly) {
                  MainActivity.access$1102(MainActivity.this, MainActivity.this.m_keyboardText.toUpperCase());
                }
                int i = MainActivity.this.m_keyboardInputMaxLength;
                if (MainActivity.this.m_keyboardText.length() < i) {
                  i = MainActivity.this.m_keyboardText.length();
                }
                if (i > 0) {
                  MainActivity.access$1102(MainActivity.this, MainActivity.this.m_keyboardText.substring(0, i));
                }
                MainActivity.this.m_glView.queueEvent(new Runnable()
                {
                  public void run()
                  {
                    MainActivity.this.onKeyboardInputUpdate(MainActivity.this.m_keyboardText, MainActivity.this.m_keyboardPtr);
                  }
                });
                return MainActivity.this.m_keyboardText;
                MainActivity.access$1102(MainActivity.this, MainActivity.this.m_keyboardText.replaceAll("[^a-zA-Z0-9\\s]", ""));
              }
            }
          };
          MainActivity.access$1002(MainActivity.this, new CheatView(MainActivity.instance, local1));
        }
        if (MainActivity.this.m_keyboardView.getParent() == null) {
          MainActivity.this.m_frameLayout.addView(MainActivity.this.m_keyboardView);
        }
        MainActivity.this.m_keyboardView.begin();
        MainActivity.this.m_keyboardView.touchOffToClose = false;
      }
    });
  }
  
  public void showLoadingSplash(String paramString)
  {
    this.m_sLoadingString = paramString;
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        if (MainActivity.this.m_frameLayout.indexOfChild(MainActivity.this.m_splashView) < 0)
        {
          MainActivity.this.m_frameLayout.addView(MainActivity.this.m_splashView);
          MainActivity.access$202(MainActivity.this, MainActivity.this.createLoadingView(MainActivity.this.m_sLoadingString));
          MainActivity.this.m_frameLayout.addView(MainActivity.this.m_loadingView);
        }
      }
    });
  }
  
  public void showMessage(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, int paramInt1, int paramInt2, int paramInt3)
  {
    new Message(paramString1, paramString2, paramString3, paramString4, paramString5, paramInt1, paramInt2, paramInt3, null);
  }
  
  public void showSplash()
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        if (MainActivity.this.m_frameLayout.indexOfChild(MainActivity.this.m_splashView) < 0) {
          MainActivity.this.m_frameLayout.addView(MainActivity.this.m_splashView);
        }
      }
    });
  }
  
  protected void updateScreenOrientationConfiguration()
  {
    if (Settings.System.getInt(getContentResolver(), "accelerometer_rotation", 1) == 0)
    {
      logi("setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)");
      setRequestedOrientation(0);
      return;
    }
    logi("setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE)");
    setRequestedOrientation(6);
  }
  
  class KeyEventRunnable
    implements Runnable
  {
    int m_keyCode;
    int m_nAction;
    
    KeyEventRunnable(int paramInt1, int paramInt2)
    {
      this.m_keyCode = paramInt1;
      this.m_nAction = paramInt2;
    }
    
    public void run()
    {
      try
      {
        if (this.m_nAction == 0)
        {
          MainActivity.instance.onKeyPressed(this.m_keyCode);
          return;
        }
        if (this.m_nAction == 1)
        {
          MainActivity.instance.onKeyReleased(this.m_keyCode);
          return;
        }
      }
      catch (UnsatisfiedLinkError localUnsatisfiedLinkError)
      {
        Log.e("RealRacing3", "UnsatisfiedLinkError when processing key event: " + localUnsatisfiedLinkError.getMessage());
      }
    }
  }
  
  class Message
  {
    private String TAG = "Message";
    private AlertDialog.Builder mAd;
    MessageCallback messageCallback;
    private int messageId_negative;
    private int messageId_neutral;
    private int messageId_positive;
    
    Message(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, int paramInt1, int paramInt2, int paramInt3, MessageCallback paramMessageCallback)
    {
      this.messageCallback = paramMessageCallback;
      Log.d(this.TAG, "Message(" + paramString1 + ", " + paramString2 + ", " + paramString3 + ", " + paramString4 + ", " + paramInt1 + ")");
      this.messageId_positive = paramInt1;
      this.messageId_negative = paramInt2;
      this.messageId_neutral = paramInt3;
      this.mAd = new AlertDialog.Builder(MainActivity.instance);
      if ((paramString1 != null) && (!paramString1.isEmpty())) {
        this.mAd.setTitle(paramString1);
      }
      if ((paramString2 != null) && (!paramString2.isEmpty())) {
        this.mAd.setMessage(paramString2);
      }
      this.mAd.setCancelable(false);
      if ((paramString3 != null) && (!paramString3.isEmpty())) {
        this.mAd.setPositiveButton(paramString3, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            if (MainActivity.Message.this.messageCallback == null)
            {
              MainActivity.instance.getGLView().queueEvent(new MainActivity.MessageExecuteCallback(MainActivity.this, MainActivity.Message.this.messageId_positive));
              return;
            }
            MainActivity.Message.this.messageCallback.onMessage(MainActivity.Message.this.messageId_positive);
          }
        });
      }
      if ((paramString4 != null) && (!paramString4.isEmpty()))
      {
        this.mAd.setNegativeButton(paramString4, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            MainActivity.instance.moveTaskToBack(true);
          }
        });
        this.mAd.setOnKeyListener(new DialogInterface.OnKeyListener()
        {
          public boolean onKey(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
          {
            if (paramAnonymousInt == 4)
            {
              MainActivity.instance.moveTaskToBack(true);
              return true;
            }
            return false;
          }
        });
      }
      if ((paramString5 != null) && (!paramString5.isEmpty())) {
        this.mAd.setNeutralButton(paramString5, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            if (MainActivity.Message.this.messageCallback == null)
            {
              MainActivity.instance.getGLView().queueEvent(new MainActivity.MessageExecuteCallback(MainActivity.this, MainActivity.Message.this.messageId_neutral));
              return;
            }
            MainActivity.Message.this.messageCallback.onMessage(MainActivity.Message.this.messageId_neutral);
          }
        });
      }
      Runnable local5 = new Runnable()
      {
        public void run()
        {
          MainActivity.Message.this.mAd.show();
        }
      };
      MainActivity.instance.handler.postDelayed(local5, 20L);
    }
  }
  
  class MessageExecuteCallback
    implements Runnable
  {
    int messageId;
    
    MessageExecuteCallback(int paramInt)
    {
      this.messageId = paramInt;
    }
    
    public void run()
    {
      MainActivity.instance.alertMessageExecuteCallback(this.messageId);
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.firemint.realracing.MainActivity
 * JD-Core Version:    0.7.0.1
 */