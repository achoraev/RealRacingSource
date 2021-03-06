package com.millennialmedia.android;

import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

abstract class MMWebViewClient
  extends WebViewClient
{
  private static final String TAG = "MMWebViewClient";
  private ExecutorService cachedExecutor = Executors.newCachedThreadPool();
  boolean isLastMMCommandResize;
  MMWebViewClientListener mmWebViewClientListener;
  HttpRedirection.RedirectionListenerImpl redirectListenerImpl;
  
  MMWebViewClient(MMWebViewClientListener paramMMWebViewClientListener, HttpRedirection.RedirectionListenerImpl paramRedirectionListenerImpl)
  {
    this.mmWebViewClientListener = paramMMWebViewClientListener;
    this.redirectListenerImpl = paramRedirectionListenerImpl;
  }
  
  public void onPageFinished(WebView paramWebView, String paramString)
  {
    MMWebView localMMWebView = (MMWebView)paramWebView;
    if (!localMMWebView.isOriginalUrl(paramString))
    {
      this.mmWebViewClientListener.onPageFinished(paramString);
      localMMWebView.setAdProperties();
      setMraidState(localMMWebView);
      MMLog.d("MMWebViewClient", "onPageFinished webview: " + localMMWebView.toString() + "url is " + paramString);
    }
    super.onPageFinished(paramWebView, paramString);
  }
  
  public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
  {
    MMLog.d("MMWebViewClient", String.format("onPageStarted: %s", new Object[] { paramString }));
    this.mmWebViewClientListener.onPageStarted(paramString);
    MMWebView localMMWebView = (MMWebView)paramWebView;
    localMMWebView.mraidState = "loading";
    localMMWebView.requiresPreAdSizeFix = false;
    super.onPageStarted(paramWebView, paramString, paramBitmap);
  }
  
  public void onReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2)
  {
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = Integer.valueOf(paramInt);
    arrayOfObject[1] = paramString1;
    arrayOfObject[2] = paramString2;
    MMLog.e("MMWebViewClient", String.format("Error: %s %s %s", arrayOfObject));
  }
  
  abstract void setMraidState(MMWebView paramMMWebView);
  
  public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
  {
    MMWebView localMMWebView = (MMWebView)paramWebView;
    if (!localMMWebView.isOriginalUrl(paramString))
    {
      MMLog.v("MMWebViewClient", "@@@@@@@@@@SHOULDOVERRIDELOADING@@@@@@@@@@@@@ Url is " + paramString + " for " + paramWebView);
      if (paramString.substring(0, 6).equalsIgnoreCase("mmsdk:"))
      {
        MMLog.v("MMWebViewClient", "Running JS bridge command: " + paramString);
        MMCommand localMMCommand = new MMCommand((MMWebView)paramWebView, paramString);
        this.isLastMMCommandResize = localMMCommand.isResizeCommand();
        this.cachedExecutor.execute(localMMCommand);
      }
    }
    else
    {
      return true;
    }
    if (this.redirectListenerImpl.isExpandingToUrl()) {
      return false;
    }
    this.redirectListenerImpl.url = paramString;
    this.redirectListenerImpl.weakContext = new WeakReference(paramWebView.getContext());
    this.redirectListenerImpl.creatorAdImplInternalId = localMMWebView.creatorAdImplId;
    HttpRedirection.startActivityFromUri(this.redirectListenerImpl);
    return true;
  }
  
  static abstract class MMWebViewClientListener
  {
    void onPageFinished(String paramString) {}
    
    void onPageStarted(String paramString) {}
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.MMWebViewClient
 * JD-Core Version:    0.7.0.1
 */