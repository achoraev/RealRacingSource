package com.google.android.gms.games.internal.events;

import java.util.concurrent.atomic.AtomicReference;

public abstract class EventIncrementManager
{
  private final AtomicReference<EventIncrementCache> aaw = new AtomicReference();
  
  public void flush()
  {
    EventIncrementCache localEventIncrementCache = (EventIncrementCache)this.aaw.get();
    if (localEventIncrementCache != null) {
      localEventIncrementCache.flush();
    }
  }
  
  protected abstract EventIncrementCache ky();
  
  public void n(String paramString, int paramInt)
  {
    EventIncrementCache localEventIncrementCache = (EventIncrementCache)this.aaw.get();
    if (localEventIncrementCache == null)
    {
      localEventIncrementCache = ky();
      if (!this.aaw.compareAndSet(null, localEventIncrementCache)) {
        localEventIncrementCache = (EventIncrementCache)this.aaw.get();
      }
    }
    localEventIncrementCache.w(paramString, paramInt);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.internal.events.EventIncrementManager
 * JD-Core Version:    0.7.0.1
 */