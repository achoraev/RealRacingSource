package com.popcap.pcsp.marketing.ima;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.widget.VideoView;
import com.google.ads.interactivemedia.v3.api.player.VideoAdPlayer.VideoAdPlayerCallback;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TrackingVideoView
  extends VideoView
  implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener
{
  private final List<VideoAdPlayer.VideoAdPlayerCallback> adCallbacks = new ArrayList(1);
  private CompleteCallback completeCallback;
  private PlaybackState state = PlaybackState.STOPPED;
  
  public TrackingVideoView(Context paramContext)
  {
    super(paramContext);
    super.setOnCompletionListener(this);
    super.setOnErrorListener(this);
  }
  
  private void onStop()
  {
    if (this.state == PlaybackState.STOPPED) {
      return;
    }
    this.state = PlaybackState.STOPPED;
  }
  
  public void addCallback(VideoAdPlayer.VideoAdPlayerCallback paramVideoAdPlayerCallback)
  {
    this.adCallbacks.add(paramVideoAdPlayerCallback);
  }
  
  public void onCompletion(MediaPlayer paramMediaPlayer)
  {
    paramMediaPlayer.setDisplay(null);
    paramMediaPlayer.reset();
    paramMediaPlayer.setDisplay(getHolder());
    onStop();
    Iterator localIterator = this.adCallbacks.iterator();
    while (localIterator.hasNext()) {
      ((VideoAdPlayer.VideoAdPlayerCallback)localIterator.next()).onEnded();
    }
    this.completeCallback.onComplete();
  }
  
  public boolean onError(MediaPlayer paramMediaPlayer, int paramInt1, int paramInt2)
  {
    Iterator localIterator = this.adCallbacks.iterator();
    while (localIterator.hasNext()) {
      ((VideoAdPlayer.VideoAdPlayerCallback)localIterator.next()).onError();
    }
    onStop();
    return true;
  }
  
  public void pause()
  {
    super.pause();
    this.state = PlaybackState.PAUSED;
    Iterator localIterator = this.adCallbacks.iterator();
    while (localIterator.hasNext()) {
      ((VideoAdPlayer.VideoAdPlayerCallback)localIterator.next()).onPause();
    }
  }
  
  public void removeCallback(VideoAdPlayer.VideoAdPlayerCallback paramVideoAdPlayerCallback)
  {
    this.adCallbacks.remove(paramVideoAdPlayerCallback);
  }
  
  public void setCompleteCallback(CompleteCallback paramCompleteCallback)
  {
    this.completeCallback = paramCompleteCallback;
  }
  
  public void setOnCompletionListener(MediaPlayer.OnCompletionListener paramOnCompletionListener)
  {
    throw new UnsupportedOperationException();
  }
  
  public void start()
  {
    super.start();
    PlaybackState localPlaybackState = this.state;
    this.state = PlaybackState.PLAYING;
    switch (1.$SwitchMap$com$popcap$pcsp$marketing$ima$TrackingVideoView$PlaybackState[localPlaybackState.ordinal()])
    {
    }
    for (;;)
    {
      return;
      Iterator localIterator2 = this.adCallbacks.iterator();
      while (localIterator2.hasNext()) {
        ((VideoAdPlayer.VideoAdPlayerCallback)localIterator2.next()).onPlay();
      }
      continue;
      Iterator localIterator1 = this.adCallbacks.iterator();
      while (localIterator1.hasNext()) {
        ((VideoAdPlayer.VideoAdPlayerCallback)localIterator1.next()).onResume();
      }
    }
  }
  
  public void stopPlayback()
  {
    super.stopPlayback();
    onStop();
  }
  
  public void togglePlayback()
  {
    switch (1.$SwitchMap$com$popcap$pcsp$marketing$ima$TrackingVideoView$PlaybackState[this.state.ordinal()])
    {
    default: 
      return;
    case 1: 
    case 2: 
      start();
      return;
    }
    pause();
  }
  
  public static abstract interface CompleteCallback
  {
    public abstract void onComplete();
  }
  
  private static enum PlaybackState
  {
    static
    {
      PAUSED = new PlaybackState("PAUSED", 1);
      PLAYING = new PlaybackState("PLAYING", 2);
      PlaybackState[] arrayOfPlaybackState = new PlaybackState[3];
      arrayOfPlaybackState[0] = STOPPED;
      arrayOfPlaybackState[1] = PAUSED;
      arrayOfPlaybackState[2] = PLAYING;
      $VALUES = arrayOfPlaybackState;
    }
    
    private PlaybackState() {}
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.popcap.pcsp.marketing.ima.TrackingVideoView
 * JD-Core Version:    0.7.0.1
 */