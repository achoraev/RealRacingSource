package com.popcap.pcsp.marketing.ima;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.google.ads.interactivemedia.v3.api.player.VideoAdPlayer;
import com.google.ads.interactivemedia.v3.api.player.VideoAdPlayer.VideoAdPlayerCallback;
import com.google.ads.interactivemedia.v3.api.player.VideoProgressUpdate;

public class StandaloneVideoAdPlayer
  extends RelativeLayout
  implements VideoAdPlayer
{
  private FrameLayout adUiContainer;
  private int savedPosition = 0;
  private TrackingVideoView video;
  
  public StandaloneVideoAdPlayer(Context paramContext)
  {
    super(paramContext);
    init();
  }
  
  public StandaloneVideoAdPlayer(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init();
  }
  
  public StandaloneVideoAdPlayer(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init();
  }
  
  private void init()
  {
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-1, -1);
    localLayoutParams.addRule(14);
    localLayoutParams.addRule(13);
    this.video = new TrackingVideoView(getContext());
    this.video.setOnTouchListener(new View.OnTouchListener()
    {
      public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
      {
        StandaloneVideoAdPlayer.this.video.togglePlayback();
        return false;
      }
    });
    addView(this.video, localLayoutParams);
    this.adUiContainer = new FrameLayout(getContext());
    addView(this.adUiContainer, -1);
  }
  
  public void addCallback(VideoAdPlayer.VideoAdPlayerCallback paramVideoAdPlayerCallback)
  {
    this.video.addCallback(paramVideoAdPlayerCallback);
  }
  
  public VideoProgressUpdate getProgress()
  {
    int i = this.video.getDuration();
    if (i <= 0) {
      return VideoProgressUpdate.VIDEO_TIME_NOT_READY;
    }
    VideoProgressUpdate localVideoProgressUpdate = new VideoProgressUpdate(this.video.getCurrentPosition(), i);
    Log.i("PLAYER", localVideoProgressUpdate.toString());
    return localVideoProgressUpdate;
  }
  
  public ViewGroup getUiContainer()
  {
    return this.adUiContainer;
  }
  
  public void loadAd(String paramString)
  {
    this.video.setVideoPath(paramString);
  }
  
  public void pauseAd()
  {
    this.video.pause();
  }
  
  public void play()
  {
    this.video.start();
  }
  
  public void playAd()
  {
    this.video.start();
  }
  
  public void removeCallback(VideoAdPlayer.VideoAdPlayerCallback paramVideoAdPlayerCallback)
  {
    this.video.removeCallback(paramVideoAdPlayerCallback);
  }
  
  public void restorePosition()
  {
    this.video.seekTo(this.savedPosition);
  }
  
  public void resumeAd()
  {
    this.video.start();
  }
  
  public void savePosition()
  {
    this.savedPosition = this.video.getCurrentPosition();
  }
  
  public void setCompletionCallback(TrackingVideoView.CompleteCallback paramCompleteCallback)
  {
    this.video.setCompleteCallback(paramCompleteCallback);
  }
  
  public void stopAd()
  {
    this.video.stopPlayback();
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.popcap.pcsp.marketing.ima.StandaloneVideoAdPlayer
 * JD-Core Version:    0.7.0.1
 */