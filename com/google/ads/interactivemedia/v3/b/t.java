package com.google.ads.interactivemedia.v3.b;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class t
{
  private final a a;
  private final WebView b;
  
  public t(Context paramContext, a parama)
  {
    this(new WebView(paramContext), parama);
  }
  
  public t(WebView paramWebView, a parama)
  {
    this.a = parama;
    this.b = paramWebView;
    this.b.setBackgroundColor(0);
    paramWebView.getSettings().setJavaScriptEnabled(true);
    paramWebView.setWebViewClient(new WebViewClient()
    {
      public void onPageFinished(WebView paramAnonymousWebView, String paramAnonymousString)
      {
        t.c("Finished " + paramAnonymousString);
      }
      
      public void onPageStarted(WebView paramAnonymousWebView, String paramAnonymousString, Bitmap paramAnonymousBitmap)
      {
        t.c("Started " + paramAnonymousString);
      }
      
      public void onReceivedError(WebView paramAnonymousWebView, int paramAnonymousInt, String paramAnonymousString1, String paramAnonymousString2)
      {
        t.c("Error: " + paramAnonymousInt + " " + paramAnonymousString1 + " " + paramAnonymousString2);
      }
      
      public boolean shouldOverrideUrlLoading(WebView paramAnonymousWebView, String paramAnonymousString)
      {
        if (!paramAnonymousString.startsWith("gmsg://")) {
          return false;
        }
        t.this.b(paramAnonymousString);
        return true;
      }
    });
  }
  
  static final void c(String paramString) {}
  
  public WebView a()
  {
    return this.b;
  }
  
  public void a(r paramr)
  {
    String str = paramr.e();
    c("Sending javascript msg: " + paramr + " as URL " + str);
    if (Build.VERSION.SDK_INT >= 19)
    {
      this.b.evaluateJavascript(str, null);
      return;
    }
    this.b.loadUrl(str);
  }
  
  public void a(String paramString)
  {
    this.b.loadUrl(paramString);
  }
  
  protected void b(String paramString)
  {
    try
    {
      r localr = r.a(paramString);
      c("Received msg: " + localr + " from URL " + paramString);
      this.a.a(localr);
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      Log.w("IMASDK", "Invalid internal message, ignoring. Please make sure the Google IMA SDK library is up to date. Message: " + paramString);
      return;
    }
    catch (Exception localException)
    {
      Log.e("IMASDK", "An internal error occured parsing message from javascript.  Message to be parsed: " + paramString, localException);
    }
  }
  
  public static abstract interface a
  {
    public abstract void a(r paramr);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.b.t
 * JD-Core Version:    0.7.0.1
 */