package com.millennialmedia.android;

import android.content.Context;

public final class MMInterstitial
  implements MMAd
{
  private static final String TAG = "MMInterstitial";
  MMAdImpl adImpl;
  int externalId;
  
  public MMInterstitial(Context paramContext)
  {
    this.adImpl = new MMInterstitialAdImpl(paramContext.getApplicationContext());
    this.adImpl.adType = "i";
  }
  
  private void fetchInternal()
  {
    if (isAdAvailable())
    {
      MMLog.d("MMInterstitial", "Ad already fetched and ready for display...");
      MMSDK.Event.requestFailed(this.adImpl, new MMException(17));
      return;
    }
    MMLog.d("MMInterstitial", "Fetching new ad...");
    this.adImpl.requestAd();
  }
  
  public boolean display()
  {
    return display(false);
  }
  
  public boolean display(boolean paramBoolean)
  {
    if (!MMSDK.isUiThread()) {
      MMLog.e("MMInterstitial", MMException.getErrorCodeMessage(3));
    }
    int i;
    do
    {
      do
      {
        return false;
        try
        {
          i = displayInternal();
          if ((i == 0) || (!paramBoolean)) {
            break;
          }
          throw new MMException(i);
        }
        catch (Exception localException) {}
      } while (!paramBoolean);
      throw new MMException(localException);
    } while (i != 0);
    return true;
  }
  
  int displayInternal()
  {
    try
    {
      MMAdImplController.assignAdViewController(this.adImpl);
      if (this.adImpl.controller != null)
      {
        int i = this.adImpl.controller.display(this.adImpl);
        return i;
      }
    }
    catch (Exception localException)
    {
      MMLog.e("MMInterstitial", "There was an exception displaying a cached ad. ", localException);
      localException.printStackTrace();
    }
    return 100;
  }
  
  public void fetch()
  {
    if ((this.adImpl != null) && (this.adImpl.requestListener != null))
    {
      fetch(this.adImpl.mmRequest, this.adImpl.requestListener);
      return;
    }
    fetchInternal();
  }
  
  public void fetch(MMRequest paramMMRequest)
  {
    if ((this.adImpl != null) && (this.adImpl.requestListener != null))
    {
      fetch(paramMMRequest, this.adImpl.requestListener);
      return;
    }
    fetchInternal();
  }
  
  public void fetch(MMRequest paramMMRequest, RequestListener paramRequestListener)
  {
    if (this.adImpl != null)
    {
      this.adImpl.mmRequest = paramMMRequest;
      this.adImpl.requestListener = paramRequestListener;
    }
    fetchInternal();
  }
  
  public String getApid()
  {
    return this.adImpl.getApid();
  }
  
  public boolean getIgnoresDensityScaling()
  {
    return this.adImpl.getIgnoresDensityScaling();
  }
  
  public RequestListener getListener()
  {
    return this.adImpl.getListener();
  }
  
  public MMRequest getMMRequest()
  {
    return this.adImpl.getMMRequest();
  }
  
  public boolean isAdAvailable()
  {
    if (!MMSDK.isUiThread()) {
      MMLog.e("MMInterstitial", MMException.getErrorCodeMessage(3));
    }
    for (;;)
    {
      return false;
      try
      {
        MMAdImplController.assignAdViewController(this.adImpl);
        if (this.adImpl.controller != null)
        {
          int i = this.adImpl.controller.isAdAvailable(this.adImpl);
          if (i == 0) {
            return true;
          }
        }
      }
      catch (Exception localException)
      {
        MMLog.e("MMInterstitial", "There was an exception checking for a cached ad. ", localException);
        localException.printStackTrace();
      }
    }
    return false;
  }
  
  public void setApid(String paramString)
  {
    this.adImpl.setApid(paramString);
  }
  
  public void setIgnoresDensityScaling(boolean paramBoolean)
  {
    this.adImpl.setIgnoresDensityScaling(paramBoolean);
  }
  
  public void setListener(RequestListener paramRequestListener)
  {
    this.adImpl.setListener(paramRequestListener);
  }
  
  public void setMMRequest(MMRequest paramMMRequest)
  {
    this.adImpl.setMMRequest(paramMMRequest);
  }
  
  class MMInterstitialAdImpl
    extends MMAdImpl
  {
    public MMInterstitialAdImpl(Context paramContext)
    {
      super();
    }
    
    MMInterstitial getCallingAd()
    {
      return MMInterstitial.this;
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.MMInterstitial
 * JD-Core Version:    0.7.0.1
 */