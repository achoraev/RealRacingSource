package com.supersonicads.sdk.controller;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.supersonicads.sdk.agent.SupersonicAdsPublisherAgent;
import com.supersonicads.sdk.precache.CacheManager;
import com.supersonicads.sdk.utils.Logger;
import java.util.Iterator;
import java.util.List;

public class OpenUrlActivity
  extends Activity
{
  private static final String TAG = "OpenUrlActivity";
  private static final int WEB_VIEW_VIEW_ID = 1;
  boolean isSecondaryWebview;
  private ProgressBar mProgressBar;
  private String mUrl;
  private WebViewController mWebViewController;
  private RelativeLayout mainLayout;
  private WebView webView = null;
  
  private void disableTouch()
  {
    getWindow().addFlags(16);
  }
  
  private void hideActivityTitle()
  {
    requestWindowFeature(1);
  }
  
  private void hideActivtiyStatusBar()
  {
    getWindow().setFlags(1024, 1024);
  }
  
  private void initializeWebView()
  {
    RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(-1, -1);
    this.webView = new WebView(this);
    this.webView.setId(1);
    this.webView.setWebViewClient(new WebViewClient());
    this.webView.getSettings().setJavaScriptEnabled(true);
    this.webView.setWebViewClient(new Client(null));
    this.mainLayout.addView(this.webView, localLayoutParams1);
    if (Build.VERSION.SDK_INT >= 11) {}
    for (this.mProgressBar = new ProgressBar(new ContextThemeWrapper(this, 16973939));; this.mProgressBar = new ProgressBar(this))
    {
      RelativeLayout.LayoutParams localLayoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
      localLayoutParams2.addRule(13);
      this.mProgressBar.setLayoutParams(localLayoutParams2);
      this.mProgressBar.setVisibility(4);
      this.mainLayout.addView(this.mProgressBar);
      loadUrl(this.mUrl);
      if (this.mWebViewController != null) {
        this.mWebViewController.viewableChange(true, "secondary");
      }
      return;
    }
  }
  
  public void finish()
  {
    if (this.isSecondaryWebview) {
      this.mWebViewController.engageEnd("secondaryClose");
    }
    super.finish();
  }
  
  public void loadUrl(String paramString)
  {
    this.webView.stopLoading();
    this.webView.clearHistory();
    try
    {
      this.webView.loadUrl(paramString);
      return;
    }
    catch (Throwable localThrowable)
    {
      Logger.e("OpenUrlActivity", "OpenUrlActivity:: loadUrl: " + localThrowable.toString());
    }
  }
  
  public void onBackPressed()
  {
    if (this.webView.canGoBack())
    {
      this.webView.goBack();
      return;
    }
    super.onBackPressed();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    Logger.i("OpenUrlActivity", "onCreate()");
    this.mWebViewController = SupersonicAdsPublisherAgent.getInstance(this).getWebViewController();
    hideActivityTitle();
    hideActivtiyStatusBar();
    Bundle localBundle = getIntent().getExtras();
    this.mUrl = localBundle.getString(WebViewController.EXTERNAL_URL);
    this.isSecondaryWebview = localBundle.getBoolean(WebViewController.SECONDARY_WEB_VIEW);
    this.mainLayout = new RelativeLayout(this);
    setContentView(this.mainLayout, new ViewGroup.LayoutParams(-1, -1));
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
  }
  
  protected void onPause()
  {
    super.onPause();
    if (this.mWebViewController != null)
    {
      this.mWebViewController.viewableChange(false, "secondary");
      if (this.mainLayout != null)
      {
        ViewGroup localViewGroup = (ViewGroup)this.webView.getParent();
        if (localViewGroup.findViewById(1) != null)
        {
          localViewGroup.removeView(this.webView);
          this.webView.destroy();
        }
      }
    }
  }
  
  protected void onResume()
  {
    super.onResume();
    initializeWebView();
  }
  
  private class Client
    extends WebViewClient
  {
    private Client() {}
    
    public void onPageFinished(WebView paramWebView, String paramString)
    {
      super.onPageFinished(paramWebView, paramString);
      OpenUrlActivity.this.mProgressBar.setVisibility(4);
    }
    
    public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
    {
      super.onPageStarted(paramWebView, paramString, paramBitmap);
      OpenUrlActivity.this.mProgressBar.setVisibility(0);
    }
    
    public void onReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2)
    {
      super.onReceivedError(paramWebView, paramInt, paramString1, paramString2);
    }
    
    public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
    {
      List localList = new CacheManager(OpenUrlActivity.this).getSearchKeys();
      Iterator localIterator;
      if ((localList != null) && (!localList.isEmpty())) {
        localIterator = localList.iterator();
      }
      do
      {
        if (!localIterator.hasNext()) {
          return super.shouldOverrideUrlLoading(paramWebView, paramString);
        }
      } while (!paramString.contains((String)localIterator.next()));
      Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
      OpenUrlActivity.this.startActivity(localIntent);
      OpenUrlActivity.this.mWebViewController.interceptedUrlToStore();
      OpenUrlActivity.this.finish();
      return true;
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.supersonicads.sdk.controller.OpenUrlActivity
 * JD-Core Version:    0.7.0.1
 */