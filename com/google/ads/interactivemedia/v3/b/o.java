package com.google.ads.interactivemedia.v3.b;

import com.google.ads.interactivemedia.v3.api.AdErrorEvent;
import com.google.ads.interactivemedia.v3.api.AdErrorEvent.AdErrorListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class o
{
  private final List<AdErrorEvent.AdErrorListener> a = new ArrayList(1);
  
  public void a(AdErrorEvent.AdErrorListener paramAdErrorListener)
  {
    this.a.add(paramAdErrorListener);
  }
  
  public void a(AdErrorEvent paramAdErrorEvent)
  {
    Iterator localIterator = this.a.iterator();
    while (localIterator.hasNext()) {
      ((AdErrorEvent.AdErrorListener)localIterator.next()).onAdError(paramAdErrorEvent);
    }
  }
  
  public void b(AdErrorEvent.AdErrorListener paramAdErrorListener)
  {
    this.a.remove(paramAdErrorListener);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.b.o
 * JD-Core Version:    0.7.0.1
 */