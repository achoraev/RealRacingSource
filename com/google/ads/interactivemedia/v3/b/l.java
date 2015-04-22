package com.google.ads.interactivemedia.v3.b;

import android.content.Context;
import android.os.Message;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebView.WebViewTransport;
import android.webkit.WebViewClient;
import com.google.ads.interactivemedia.v3.b.a.c;
import com.google.ads.interactivemedia.v3.b.a.c.a;

public class l
  extends WebView
{
  public l(final Context paramContext, final s params, c paramc)
  {
    super(paramContext);
    getSettings().setSupportMultipleWindows(true);
    setWebChromeClient(new WebChromeClient()
    {
      public boolean onCreateWindow(WebView paramAnonymousWebView, boolean paramAnonymousBoolean1, boolean paramAnonymousBoolean2, Message paramAnonymousMessage)
      {
        WebView.WebViewTransport localWebViewTransport = (WebView.WebViewTransport)paramAnonymousMessage.obj;
        localWebViewTransport.setWebView(new WebView(paramContext));
        localWebViewTransport.getWebView().setWebViewClient(new WebViewClient()
        {
          public boolean shouldOverrideUrlLoading(WebView paramAnonymous2WebView, String paramAnonymous2String)
          {
            l.1.this.b.b(paramAnonymous2String);
            return true;
          }
        });
        paramAnonymousMessage.sendToTarget();
        return true;
      }
    });
    if (paramc.type == c.a.Html)
    {
      loadData(paramc.src, "text/html", null);
      return;
    }
    if (paramc.type == c.a.IFrame)
    {
      loadUrl(paramc.src);
      return;
    }
    throw new IllegalArgumentException("Companion type " + paramc.type + " is not valid for a CompanionWebView");
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.b.l
 * JD-Core Version:    0.7.0.1
 */