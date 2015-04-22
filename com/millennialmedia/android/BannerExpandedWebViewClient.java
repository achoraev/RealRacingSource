package com.millennialmedia.android;

class BannerExpandedWebViewClient
  extends MMWebViewClient
{
  BannerExpandedWebViewClient(MMWebViewClient.MMWebViewClientListener paramMMWebViewClientListener, HttpRedirection.RedirectionListenerImpl paramRedirectionListenerImpl)
  {
    super(paramMMWebViewClientListener, paramRedirectionListenerImpl);
  }
  
  void setMraidState(MMWebView paramMMWebView)
  {
    paramMMWebView.setMraidPlacementTypeInline();
    paramMMWebView.setMraidExpanded();
    paramMMWebView.setMraidReady();
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.BannerExpandedWebViewClient
 * JD-Core Version:    0.7.0.1
 */