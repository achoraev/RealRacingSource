package com.millennialmedia.android;

class InterstitialWebViewClient
  extends MMWebViewClient
{
  InterstitialWebViewClient(MMWebViewClient.MMWebViewClientListener paramMMWebViewClientListener, HttpRedirection.RedirectionListenerImpl paramRedirectionListenerImpl)
  {
    super(paramMMWebViewClientListener, paramRedirectionListenerImpl);
  }
  
  void setMraidState(MMWebView paramMMWebView)
  {
    paramMMWebView.setMraidPlacementTypeInterstitial();
    paramMMWebView.setMraidDefault();
    paramMMWebView.setMraidReady();
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.InterstitialWebViewClient
 * JD-Core Version:    0.7.0.1
 */