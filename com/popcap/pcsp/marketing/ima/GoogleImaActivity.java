package com.popcap.pcsp.marketing.ima;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;

public class GoogleImaActivity
  extends Activity
{
  private FrameLayout mVideoHolder;
  
  public void onBackPressed() {}
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    StandaloneVideoAdPlayer localStandaloneVideoAdPlayer = GoogleImaAgent.getInstance().getPlayer();
    if (localStandaloneVideoAdPlayer != null)
    {
      localStandaloneVideoAdPlayer.savePosition();
      this.mVideoHolder.removeView(localStandaloneVideoAdPlayer);
    }
    super.onConfigurationChanged(paramConfiguration);
    this.mVideoHolder.addView(localStandaloneVideoAdPlayer);
    localStandaloneVideoAdPlayer.restorePosition();
    localStandaloneVideoAdPlayer.play();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    GoogleImaAgent.getInstance().attachActivity(this);
    if (this.mVideoHolder == null)
    {
      this.mVideoHolder = new FrameLayout(this);
      this.mVideoHolder.addView(GoogleImaAgent.getInstance().getPlayer());
    }
    addContentView(this.mVideoHolder, new ViewGroup.LayoutParams(-1, -1));
    GoogleImaAgent.getInstance().start();
    GoogleImaAgent.getInstance().getPlayer().restorePosition();
  }
  
  protected void onDestroy()
  {
    StandaloneVideoAdPlayer localStandaloneVideoAdPlayer = GoogleImaAgent.getInstance().getPlayer();
    if (localStandaloneVideoAdPlayer != null)
    {
      localStandaloneVideoAdPlayer.savePosition();
      this.mVideoHolder.removeView(localStandaloneVideoAdPlayer);
    }
    super.onDestroy();
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((Build.VERSION.SDK_INT < 5) && (paramInt == 4))
    {
      onBackPressed();
      return true;
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  protected void onPause()
  {
    super.onPause();
    GoogleImaAgent.getInstance().pause();
  }
  
  protected void onResume()
  {
    super.onResume();
    GoogleImaAgent.getInstance().resume();
  }
  
  protected void onSaveInstanceState(Bundle paramBundle)
  {
    GoogleImaAgent.getInstance().getPlayer().savePosition();
    super.onSaveInstanceState(paramBundle);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.popcap.pcsp.marketing.ima.GoogleImaActivity
 * JD-Core Version:    0.7.0.1
 */