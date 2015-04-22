package com.google.ads.interactivemedia.v3.b;

import com.google.ads.interactivemedia.v3.api.player.VideoAdPlayer.VideoAdPlayerCallback;
import com.google.ads.interactivemedia.v3.api.player.VideoProgressUpdate;

public class d
  implements VideoAdPlayer.VideoAdPlayerCallback, v.a
{
  private s a;
  private String b;
  private boolean c = false;
  private boolean d = false;
  private v e;
  
  public d(s params, String paramString, v paramv)
  {
    this.a = params;
    this.b = paramString;
    this.e = paramv;
  }
  
  public void a(VideoProgressUpdate paramVideoProgressUpdate)
  {
    if ((paramVideoProgressUpdate != null) && (paramVideoProgressUpdate.getDuration() > 0.0F))
    {
      if ((!this.d) && (paramVideoProgressUpdate.getCurrentTime() > 0.0F))
      {
        a(r.c.start);
        this.d = true;
      }
      a(r.c.timeupdate, paramVideoProgressUpdate);
    }
  }
  
  void a(r.c paramc)
  {
    a(paramc, null);
  }
  
  void a(r.c paramc, VideoProgressUpdate paramVideoProgressUpdate)
  {
    r localr = new r(r.b.videoDisplay, paramc, this.b, paramVideoProgressUpdate);
    this.a.b(localr);
  }
  
  public void onEnded()
  {
    this.e.c();
    a(r.c.end);
  }
  
  public void onError()
  {
    this.e.c();
    a(r.c.error);
  }
  
  public void onPause()
  {
    this.e.c();
    a(r.c.pause);
  }
  
  public void onPlay()
  {
    this.e.b();
    this.d = false;
  }
  
  public void onResume()
  {
    this.e.b();
    a(r.c.play);
  }
  
  public void onVolumeChanged(int paramInt)
  {
    if ((paramInt == 0) && (!this.c))
    {
      a(r.c.mute);
      this.c = true;
    }
    if ((paramInt != 0) && (this.c))
    {
      a(r.c.unmute);
      this.c = false;
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.b.d
 * JD-Core Version:    0.7.0.1
 */