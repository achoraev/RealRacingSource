package com.google.ads.interactivemedia.v3.b;

import com.google.ads.interactivemedia.v3.api.player.VideoProgressUpdate;
import java.util.SortedSet;

public class n
  implements v.a
{
  private final SortedSet<Float> a;
  private s b;
  private String c;
  private float d = 0.0F;
  
  public n(s params, SortedSet<Float> paramSortedSet, String paramString)
  {
    this.b = params;
    this.c = paramString;
    this.a = paramSortedSet;
  }
  
  private SortedSet<Float> a(float paramFloat)
  {
    if (this.d < paramFloat) {
      return this.a.subSet(Float.valueOf(this.d), Float.valueOf(paramFloat));
    }
    return this.a.subSet(Float.valueOf(paramFloat), Float.valueOf(this.d));
  }
  
  public void a(VideoProgressUpdate paramVideoProgressUpdate)
  {
    if ((paramVideoProgressUpdate == null) || (paramVideoProgressUpdate.getDuration() < 0.0F)) {
      return;
    }
    if (!a(paramVideoProgressUpdate.getCurrentTime()).isEmpty()) {}
    for (int i = 1;; i = 0)
    {
      this.d = paramVideoProgressUpdate.getCurrentTime();
      if (i == 0) {
        break;
      }
      this.b.b(new r(r.b.contentTimeUpdate, r.c.contentTimeUpdate, this.c, paramVideoProgressUpdate));
      return;
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.b.n
 * JD-Core Version:    0.7.0.1
 */