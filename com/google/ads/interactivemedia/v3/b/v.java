package com.google.ads.interactivemedia.v3.b;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import com.google.ads.interactivemedia.v3.api.player.VideoAdPlayer;
import com.google.ads.interactivemedia.v3.api.player.VideoProgressUpdate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class v
  implements Handler.Callback
{
  protected final VideoAdPlayer a;
  protected final long b;
  protected boolean c = false;
  private Handler d;
  private List<a> e = new ArrayList(1);
  private List<a> f = new ArrayList(1);
  private List<a> g;
  
  v(Handler paramHandler, VideoAdPlayer paramVideoAdPlayer, long paramLong)
  {
    this.a = paramVideoAdPlayer;
    this.b = paramLong;
    this.d = paramHandler;
    if (paramHandler == null) {
      this.d = new Handler(this);
    }
    this.g = this.e;
  }
  
  public v(VideoAdPlayer paramVideoAdPlayer, long paramLong)
  {
    this(null, paramVideoAdPlayer, paramLong);
  }
  
  private void d()
  {
    if (this.c) {
      return;
    }
    this.c = true;
    this.d.sendEmptyMessage(1);
  }
  
  public void a()
  {
    this.g = this.f;
    d();
  }
  
  public void a(a parama)
  {
    this.e.add(parama);
  }
  
  public void b()
  {
    this.g = this.e;
    d();
  }
  
  public void b(a parama)
  {
    this.e.remove(parama);
  }
  
  public void c()
  {
    this.c = false;
    this.d.sendMessageAtFrontOfQueue(Message.obtain(this.d, 2));
  }
  
  public void c(a parama)
  {
    this.f.add(parama);
  }
  
  public boolean handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    default: 
      return true;
    case 2: 
      this.d.removeMessages(1);
      return true;
    }
    VideoProgressUpdate localVideoProgressUpdate = this.a.getProgress();
    Iterator localIterator = this.g.iterator();
    while (localIterator.hasNext()) {
      ((a)localIterator.next()).a(localVideoProgressUpdate);
    }
    this.d.sendMessageDelayed(Message.obtain(this.d, 1), this.b);
    return true;
  }
  
  public static abstract interface a
  {
    public abstract void a(VideoProgressUpdate paramVideoProgressUpdate);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.b.v
 * JD-Core Version:    0.7.0.1
 */