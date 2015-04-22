package com.millennialmedia.android;

import android.util.Log;

public abstract interface RequestListener
{
  public abstract void MMAdOverlayClosed(MMAd paramMMAd);
  
  public abstract void MMAdOverlayLaunched(MMAd paramMMAd);
  
  public abstract void MMAdRequestIsCaching(MMAd paramMMAd);
  
  public abstract void onSingleTap(MMAd paramMMAd);
  
  public abstract void requestCompleted(MMAd paramMMAd);
  
  public abstract void requestFailed(MMAd paramMMAd, MMException paramMMException);
  
  public static class RequestListenerImpl
    implements RequestListener
  {
    public void MMAdOverlayClosed(MMAd paramMMAd)
    {
      Log.i("MMSDK", "Millennial Media Ad View overlay closed");
    }
    
    public void MMAdOverlayLaunched(MMAd paramMMAd)
    {
      Log.i("MMSDK", "Millennial Media Ad View overlay launched");
    }
    
    public void MMAdRequestIsCaching(MMAd paramMMAd)
    {
      Log.i("MMSDK", "Millennial Media Ad View caching request");
    }
    
    public void onSingleTap(MMAd paramMMAd)
    {
      Log.i("MMSDK", "Ad tapped");
    }
    
    public void requestCompleted(MMAd paramMMAd)
    {
      Log.i("MMSDK", "Ad request succeeded");
    }
    
    public void requestFailed(MMAd paramMMAd, MMException paramMMException)
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = Integer.valueOf(paramMMException.getCode());
      arrayOfObject[1] = paramMMException.getMessage();
      Log.i("MMSDK", String.format("Ad request failed with error: %d %s.", arrayOfObject));
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.RequestListener
 * JD-Core Version:    0.7.0.1
 */