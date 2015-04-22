package com.firemint.realracing;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class MoviePlayer
  implements MediaPlayer.OnCompletionListener
{
  public static Activity mActivity;
  public static Handler mUIThreadHandler;
  public static ViewGroup mViewGroup;
  public static MPHelper msMPHelper;
  public RelativeLayout mLayout;
  private long mThat;
  
  public static void pause()
  {
    mActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        if (MoviePlayer.msMPHelper != null) {
          MoviePlayer.msMPHelper.pause();
        }
      }
    });
  }
  
  public static void resume()
  {
    mActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        Log.i("MoviePlayer", "MoviePlayer.Resume()");
        if (MoviePlayer.msMPHelper != null) {
          MoviePlayer.msMPHelper.resume();
        }
      }
    });
  }
  
  public static void startup(Activity paramActivity, ViewGroup paramViewGroup, Handler paramHandler)
  {
    Log.i("RealRacing3", "MoviePlayer.startup");
    mActivity = paramActivity;
    mViewGroup = paramViewGroup;
    mUIThreadHandler = paramHandler;
  }
  
  public native void OnCompletionNative(long paramLong);
  
  protected void clear()
  {
    if (msMPHelper != null)
    {
      msMPHelper.destroy();
      this.mLayout.removeView(msMPHelper);
      mViewGroup.removeView(this.mLayout);
      this.mLayout = null;
      msMPHelper = null;
    }
  }
  
  public void onCompletion(MediaPlayer paramMediaPlayer)
  {
    clear();
    MainActivity.instance.getGLView().queueEvent(new Runnable()
    {
      public void run()
      {
        MoviePlayer.this.OnCompletionNative(MoviePlayer.this.mThat);
      }
    });
  }
  
  public void play(final String paramString, long paramLong)
  {
    Log.i("RealRacing3", "MoviePlayer.play");
    if (msMPHelper != null)
    {
      Log.e("RealRacing3", "MoviePlayer Cannot play multiple movies simultaneously");
      OnCompletionNative(paramLong);
      return;
    }
    this.mThat = paramLong;
    Runnable local1 = new Runnable()
    {
      public void run()
      {
        MoviePlayer.this.mLayout = new RelativeLayout(MoviePlayer.mActivity);
        MoviePlayer.mViewGroup.addView(MoviePlayer.this.mLayout);
        MoviePlayer.msMPHelper = new MoviePlayer.MPHelper(MoviePlayer.this, MoviePlayer.mActivity);
        MoviePlayer.msMPHelper.setOnCompletionListener(jdField_this);
        MoviePlayer.msMPHelper.requestFocus();
        MoviePlayer.msMPHelper.setZOrderOnTop(true);
        RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(Platform.getScreenWidth(), Platform.getScreenHeight());
        localLayoutParams.leftMargin = 0;
        localLayoutParams.topMargin = 0;
        MoviePlayer.this.mLayout.addView(MoviePlayer.msMPHelper, localLayoutParams);
        MoviePlayer.msMPHelper.setVideoURL(paramString);
        MoviePlayer.msMPHelper.start();
      }
    };
    mActivity.runOnUiThread(local1);
  }
  
  public void stop()
  {
    mActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        MoviePlayer.this.clear();
      }
    });
  }
  
  public class MPHelper
    extends SurfaceView
    implements SurfaceHolder.Callback, MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnVideoSizeChangedListener
  {
    public MediaPlayer.OnCompletionListener mCompletionListener = null;
    public MediaPlayer mMediaPlayer = null;
    public boolean mStartAfterCreating = false;
    public SurfaceHolder mSurfaceHolder = null;
    public Uri mUri;
    
    public MPHelper(Context paramContext)
    {
      super();
      getHolder().addCallback(this);
      getHolder().setType(3);
      setFocusable(true);
      setFocusableInTouchMode(true);
      requestFocus();
    }
    
    private void openVideo()
    {
      Log.i("RealRacing3", "MPHelper openVideo");
      if ((this.mUri == null) || (this.mSurfaceHolder == null)) {
        return;
      }
      destroy();
      try
      {
        this.mMediaPlayer = new MediaPlayer();
        Log.i("RealRacing3", "opening video " + this.mUri);
        this.mMediaPlayer.setDataSource(getContext(), this.mUri);
        this.mMediaPlayer.setOnPreparedListener(this);
        this.mMediaPlayer.setOnVideoSizeChangedListener(this);
        this.mMediaPlayer.setOnCompletionListener(this.mCompletionListener);
        this.mMediaPlayer.setOnErrorListener(this);
        this.mMediaPlayer.setDisplay(this.mSurfaceHolder);
        this.mMediaPlayer.setAudioStreamType(3);
        this.mMediaPlayer.setScreenOnWhilePlaying(true);
        this.mMediaPlayer.prepare();
        return;
      }
      catch (Exception localException)
      {
        Log.w("RealRacing3", "error opening video " + this.mUri + " exception: " + localException);
      }
    }
    
    private void setup()
    {
      openVideo();
      requestLayout();
      invalidate();
    }
    
    public void destroy()
    {
      Log.i("MoviePlayer", "MPHelper destroyed");
      if (this.mMediaPlayer != null)
      {
        this.mMediaPlayer.reset();
        this.mMediaPlayer.release();
        this.mMediaPlayer = null;
      }
    }
    
    public boolean onError(MediaPlayer paramMediaPlayer, int paramInt1, int paramInt2)
    {
      Log.i("RealRacing3", "onError what: " + paramInt1 + "   extra: " + paramInt2);
      return true;
    }
    
    public void onPrepared(MediaPlayer paramMediaPlayer)
    {
      Log.i("RealRacing3", "MPHelper onPrepared");
      if (this.mStartAfterCreating)
      {
        start();
        this.mStartAfterCreating = false;
      }
    }
    
    public void onVideoSizeChanged(MediaPlayer paramMediaPlayer, int paramInt1, int paramInt2)
    {
      Log.i("RealRacing3", "onVideoSizeChanged");
    }
    
    public void pause()
    {
      if (this.mMediaPlayer != null)
      {
        Log.i("MoviePlayer", "MPHelper.pause()");
        this.mMediaPlayer.pause();
      }
    }
    
    public void resume()
    {
      Log.i("MoviePlayer", "MPHelper.resume()");
      if (this.mMediaPlayer == null)
      {
        Log.e("MoviePlayer", "MPHelper.resume() Media Player is null");
        return;
      }
      if (this.mSurfaceHolder == null)
      {
        Log.e("MoviePlayer", "MPHelper.resume() surface is null");
        return;
      }
      if (this.mMediaPlayer.isPlaying())
      {
        Log.e("MoviePlayer", "MPHelper.resume() Media player is already playing");
        return;
      }
      if (this.mMediaPlayer.getCurrentPosition() <= 0)
      {
        Log.e("MoviePlayer", "MPHelper.resume() Media player has not started");
        return;
      }
      Log.i("MoviePlayer", "MPHelper.resume() resuming video");
      this.mMediaPlayer.start();
    }
    
    public void setOnCompletionListener(MediaPlayer.OnCompletionListener paramOnCompletionListener)
    {
      this.mCompletionListener = paramOnCompletionListener;
    }
    
    public void setVideoURI(Uri paramUri)
    {
      this.mUri = paramUri;
      setup();
    }
    
    public void setVideoURL(String paramString)
    {
      setVideoURI(Uri.parse(paramString));
    }
    
    public void start()
    {
      if (this.mMediaPlayer != null)
      {
        this.mMediaPlayer.start();
        return;
      }
      this.mStartAfterCreating = true;
    }
    
    public void surfaceChanged(SurfaceHolder paramSurfaceHolder, int paramInt1, int paramInt2, int paramInt3)
    {
      Log.i("RealRacing3", "MPHelper surfaceChanged: " + paramInt2 + ", " + paramInt3);
      if (this.mMediaPlayer != null)
      {
        this.mMediaPlayer.setDisplay(paramSurfaceHolder);
        resume();
      }
    }
    
    public void surfaceCreated(SurfaceHolder paramSurfaceHolder)
    {
      Log.i("RealRacing3", "MPHelper surfaceCreated");
      this.mSurfaceHolder = paramSurfaceHolder;
      if (this.mMediaPlayer != null)
      {
        this.mMediaPlayer.setDisplay(paramSurfaceHolder);
        return;
      }
      openVideo();
    }
    
    public void surfaceDestroyed(SurfaceHolder paramSurfaceHolder)
    {
      Log.i("RealRacing3", "MPHelper surfaceDestroyed");
      this.mSurfaceHolder = null;
      pause();
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.firemint.realracing.MoviePlayer
 * JD-Core Version:    0.7.0.1
 */