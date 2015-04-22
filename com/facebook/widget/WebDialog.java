package com.facebook.widget;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.facebook.FacebookDialogException;
import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.FacebookRequestError;
import com.facebook.FacebookServiceException;
import com.facebook.Session;
import com.facebook.android.R.drawable;
import com.facebook.android.R.string;
import com.facebook.android.Util;
import com.facebook.internal.ServerProtocol;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;

public class WebDialog
  extends Dialog
{
  private static final int BACKGROUND_GRAY = -872415232;
  static final String CANCEL_URI = "fbconnect://cancel";
  public static final int DEFAULT_THEME = 16973840;
  static final boolean DISABLE_SSL_CHECK_FOR_TESTING = false;
  private static final String DISPLAY_TOUCH = "touch";
  private static final String LOG_TAG = "FacebookSDK.WebDialog";
  private static final int MAX_PADDING_SCREEN_HEIGHT = 1280;
  private static final int MAX_PADDING_SCREEN_WIDTH = 800;
  private static final double MIN_SCALE_FACTOR = 0.5D;
  private static final int NO_PADDING_SCREEN_HEIGHT = 800;
  private static final int NO_PADDING_SCREEN_WIDTH = 480;
  static final String REDIRECT_URI = "fbconnect://success";
  private FrameLayout contentFrameLayout;
  private ImageView crossImageView;
  private boolean isDetached = false;
  private boolean listenerCalled = false;
  private OnCompleteListener onCompleteListener;
  private ProgressDialog spinner;
  private String url;
  private WebView webView;
  
  public WebDialog(Context paramContext, String paramString)
  {
    this(paramContext, paramString, 16973840);
  }
  
  public WebDialog(Context paramContext, String paramString, int paramInt)
  {
    super(paramContext, paramInt);
    this.url = paramString;
  }
  
  public WebDialog(Context paramContext, String paramString, Bundle paramBundle, int paramInt, OnCompleteListener paramOnCompleteListener)
  {
    super(paramContext, paramInt);
    if (paramBundle == null) {
      paramBundle = new Bundle();
    }
    paramBundle.putString("redirect_uri", "fbconnect://success");
    paramBundle.putString("display", "touch");
    this.url = Utility.buildUri(ServerProtocol.getDialogAuthority(), ServerProtocol.getAPIVersion() + "/" + "dialog/" + paramString, paramBundle).toString();
    this.onCompleteListener = paramOnCompleteListener;
  }
  
  private void calculateSize()
  {
    Display localDisplay = ((WindowManager)getContext().getSystemService("window")).getDefaultDisplay();
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    localDisplay.getMetrics(localDisplayMetrics);
    int i;
    if (localDisplayMetrics.widthPixels < localDisplayMetrics.heightPixels)
    {
      i = localDisplayMetrics.widthPixels;
      if (localDisplayMetrics.widthPixels >= localDisplayMetrics.heightPixels) {
        break label133;
      }
    }
    label133:
    for (int j = localDisplayMetrics.heightPixels;; j = localDisplayMetrics.widthPixels)
    {
      int k = Math.min(getScaledSize(i, localDisplayMetrics.density, 480, 800), localDisplayMetrics.widthPixels);
      int m = Math.min(getScaledSize(j, localDisplayMetrics.density, 800, 1280), localDisplayMetrics.heightPixels);
      getWindow().setLayout(k, m);
      return;
      i = localDisplayMetrics.heightPixels;
      break;
    }
  }
  
  private void createCrossImage()
  {
    this.crossImageView = new ImageView(getContext());
    this.crossImageView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        WebDialog.this.sendCancelToListener();
        WebDialog.this.dismiss();
      }
    });
    Drawable localDrawable = getContext().getResources().getDrawable(R.drawable.com_facebook_close);
    this.crossImageView.setImageDrawable(localDrawable);
    this.crossImageView.setVisibility(4);
  }
  
  private int getScaledSize(int paramInt1, float paramFloat, int paramInt2, int paramInt3)
  {
    int i = (int)(paramInt1 / paramFloat);
    double d;
    if (i <= paramInt2) {
      d = 1.0D;
    }
    for (;;)
    {
      return (int)(d * paramInt1);
      if (i >= paramInt3) {
        d = 0.5D;
      } else {
        d = 0.5D + 0.5D * ((paramInt3 - i) / (paramInt3 - paramInt2));
      }
    }
  }
  
  private void sendCancelToListener()
  {
    sendErrorToListener(new FacebookOperationCanceledException());
  }
  
  private void sendErrorToListener(Throwable paramThrowable)
  {
    if ((this.onCompleteListener != null) && (!this.listenerCalled))
    {
      this.listenerCalled = true;
      if (!(paramThrowable instanceof FacebookException)) {
        break label43;
      }
    }
    label43:
    for (FacebookException localFacebookException = (FacebookException)paramThrowable;; localFacebookException = new FacebookException(paramThrowable))
    {
      this.onCompleteListener.onComplete(null, localFacebookException);
      return;
    }
  }
  
  private void sendSuccessToListener(Bundle paramBundle)
  {
    if ((this.onCompleteListener != null) && (!this.listenerCalled))
    {
      this.listenerCalled = true;
      this.onCompleteListener.onComplete(paramBundle, null);
    }
  }
  
  @SuppressLint({"SetJavaScriptEnabled"})
  private void setUpWebView(int paramInt)
  {
    LinearLayout localLinearLayout = new LinearLayout(getContext());
    this.webView = new WebView(getContext());
    this.webView.setVerticalScrollBarEnabled(false);
    this.webView.setHorizontalScrollBarEnabled(false);
    this.webView.setWebViewClient(new DialogWebViewClient(null));
    this.webView.getSettings().setJavaScriptEnabled(true);
    this.webView.loadUrl(this.url);
    this.webView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
    this.webView.setVisibility(4);
    this.webView.getSettings().setSavePassword(false);
    this.webView.getSettings().setSaveFormData(false);
    localLinearLayout.setPadding(paramInt, paramInt, paramInt, paramInt);
    localLinearLayout.addView(this.webView);
    localLinearLayout.setBackgroundColor(-872415232);
    this.contentFrameLayout.addView(localLinearLayout);
  }
  
  public void dismiss()
  {
    if (this.webView != null) {
      this.webView.stopLoading();
    }
    if (!this.isDetached)
    {
      if (this.spinner.isShowing()) {
        this.spinner.dismiss();
      }
      super.dismiss();
    }
  }
  
  public OnCompleteListener getOnCompleteListener()
  {
    return this.onCompleteListener;
  }
  
  public void onAttachedToWindow()
  {
    this.isDetached = false;
    super.onAttachedToWindow();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setOnCancelListener(new DialogInterface.OnCancelListener()
    {
      public void onCancel(DialogInterface paramAnonymousDialogInterface)
      {
        WebDialog.this.sendCancelToListener();
      }
    });
    this.spinner = new ProgressDialog(getContext());
    this.spinner.requestWindowFeature(1);
    this.spinner.setMessage(getContext().getString(R.string.com_facebook_loading));
    this.spinner.setOnCancelListener(new DialogInterface.OnCancelListener()
    {
      public void onCancel(DialogInterface paramAnonymousDialogInterface)
      {
        WebDialog.this.sendCancelToListener();
        WebDialog.this.dismiss();
      }
    });
    requestWindowFeature(1);
    this.contentFrameLayout = new FrameLayout(getContext());
    calculateSize();
    getWindow().setGravity(17);
    getWindow().setSoftInputMode(16);
    createCrossImage();
    setUpWebView(1 + this.crossImageView.getDrawable().getIntrinsicWidth() / 2);
    this.contentFrameLayout.addView(this.crossImageView, new ViewGroup.LayoutParams(-2, -2));
    setContentView(this.contentFrameLayout);
  }
  
  public void onDetachedFromWindow()
  {
    this.isDetached = true;
    super.onDetachedFromWindow();
  }
  
  public void setOnCompleteListener(OnCompleteListener paramOnCompleteListener)
  {
    this.onCompleteListener = paramOnCompleteListener;
  }
  
  public static class Builder
    extends WebDialog.BuilderBase<Builder>
  {
    public Builder(Context paramContext, Session paramSession, String paramString, Bundle paramBundle)
    {
      super(paramSession, paramString, paramBundle);
    }
    
    public Builder(Context paramContext, String paramString)
    {
      super(paramString);
    }
    
    public Builder(Context paramContext, String paramString1, String paramString2, Bundle paramBundle)
    {
      super(paramString1, paramString2, paramBundle);
    }
  }
  
  private static class BuilderBase<CONCRETE extends BuilderBase<?>>
  {
    private String action;
    private String applicationId;
    private Context context;
    private WebDialog.OnCompleteListener listener;
    private Bundle parameters;
    private Session session;
    private int theme = 16973840;
    
    protected BuilderBase(Context paramContext, Session paramSession, String paramString, Bundle paramBundle)
    {
      Validate.notNull(paramSession, "session");
      if (!paramSession.isOpened()) {
        throw new FacebookException("Attempted to use a Session that was not open.");
      }
      this.session = paramSession;
      finishInit(paramContext, paramString, paramBundle);
    }
    
    protected BuilderBase(Context paramContext, String paramString)
    {
      Session localSession = Session.getActiveSession();
      if ((localSession != null) && (localSession.isOpened())) {
        this.session = localSession;
      }
      for (;;)
      {
        finishInit(paramContext, paramString, null);
        return;
        String str = Utility.getMetadataApplicationId(paramContext);
        if (str == null) {
          break;
        }
        this.applicationId = str;
      }
      throw new FacebookException("Attempted to create a builder without an open Active Session or a valid default Application ID.");
    }
    
    protected BuilderBase(Context paramContext, String paramString1, String paramString2, Bundle paramBundle)
    {
      if (paramString1 == null) {
        paramString1 = Utility.getMetadataApplicationId(paramContext);
      }
      Validate.notNullOrEmpty(paramString1, "applicationId");
      this.applicationId = paramString1;
      finishInit(paramContext, paramString2, paramBundle);
    }
    
    private void finishInit(Context paramContext, String paramString, Bundle paramBundle)
    {
      this.context = paramContext;
      this.action = paramString;
      if (paramBundle != null)
      {
        this.parameters = paramBundle;
        return;
      }
      this.parameters = new Bundle();
    }
    
    public WebDialog build()
    {
      if ((this.session != null) && (this.session.isOpened()))
      {
        this.parameters.putString("app_id", this.session.getApplicationId());
        this.parameters.putString("access_token", this.session.getAccessToken());
      }
      for (;;)
      {
        return new WebDialog(this.context, this.action, this.parameters, this.theme, this.listener);
        this.parameters.putString("app_id", this.applicationId);
      }
    }
    
    protected String getApplicationId()
    {
      return this.applicationId;
    }
    
    protected Context getContext()
    {
      return this.context;
    }
    
    protected WebDialog.OnCompleteListener getListener()
    {
      return this.listener;
    }
    
    protected Bundle getParameters()
    {
      return this.parameters;
    }
    
    protected int getTheme()
    {
      return this.theme;
    }
    
    public CONCRETE setOnCompleteListener(WebDialog.OnCompleteListener paramOnCompleteListener)
    {
      this.listener = paramOnCompleteListener;
      return this;
    }
    
    public CONCRETE setTheme(int paramInt)
    {
      this.theme = paramInt;
      return this;
    }
  }
  
  private class DialogWebViewClient
    extends WebViewClient
  {
    private DialogWebViewClient() {}
    
    public void onPageFinished(WebView paramWebView, String paramString)
    {
      super.onPageFinished(paramWebView, paramString);
      if (!WebDialog.this.isDetached) {
        WebDialog.this.spinner.dismiss();
      }
      WebDialog.this.contentFrameLayout.setBackgroundColor(0);
      WebDialog.this.webView.setVisibility(0);
      WebDialog.this.crossImageView.setVisibility(0);
    }
    
    public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
    {
      Utility.logd("FacebookSDK.WebDialog", "Webview loading URL: " + paramString);
      super.onPageStarted(paramWebView, paramString, paramBitmap);
      if (!WebDialog.this.isDetached) {
        WebDialog.this.spinner.show();
      }
    }
    
    public void onReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2)
    {
      super.onReceivedError(paramWebView, paramInt, paramString1, paramString2);
      WebDialog.this.sendErrorToListener(new FacebookDialogException(paramString1, paramInt, paramString2));
      WebDialog.this.dismiss();
    }
    
    public void onReceivedSslError(WebView paramWebView, SslErrorHandler paramSslErrorHandler, SslError paramSslError)
    {
      super.onReceivedSslError(paramWebView, paramSslErrorHandler, paramSslError);
      WebDialog.this.sendErrorToListener(new FacebookDialogException(null, -11, null));
      paramSslErrorHandler.cancel();
      WebDialog.this.dismiss();
    }
    
    public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
    {
      Utility.logd("FacebookSDK.WebDialog", "Redirect URL: " + paramString);
      String str1;
      String str2;
      int i;
      if (paramString.startsWith("fbconnect://success"))
      {
        Bundle localBundle = Util.parseUrl(paramString);
        str1 = localBundle.getString("error");
        if (str1 == null) {
          str1 = localBundle.getString("error_type");
        }
        str2 = localBundle.getString("error_msg");
        if (str2 == null) {
          str2 = localBundle.getString("error_description");
        }
        String str3 = localBundle.getString("error_code");
        i = -1;
        if (!Utility.isNullOrEmpty(str3)) {}
        try
        {
          int j = Integer.parseInt(str3);
          i = j;
        }
        catch (NumberFormatException localNumberFormatException)
        {
          for (;;)
          {
            i = -1;
            continue;
            if ((str1 != null) && ((str1.equals("access_denied")) || (str1.equals("OAuthAccessDeniedException"))))
            {
              WebDialog.this.sendCancelToListener();
            }
            else
            {
              FacebookRequestError localFacebookRequestError = new FacebookRequestError(i, str1, str2);
              WebDialog.this.sendErrorToListener(new FacebookServiceException(localFacebookRequestError, str2));
            }
          }
        }
        if ((Utility.isNullOrEmpty(str1)) && (Utility.isNullOrEmpty(str2)) && (i == -1))
        {
          WebDialog.this.sendSuccessToListener(localBundle);
          WebDialog.this.dismiss();
          return true;
        }
      }
      if (paramString.startsWith("fbconnect://cancel"))
      {
        WebDialog.this.sendCancelToListener();
        WebDialog.this.dismiss();
        return true;
      }
      if (paramString.contains("touch")) {
        return false;
      }
      WebDialog.this.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramString)));
      return true;
    }
  }
  
  public static class FeedDialogBuilder
    extends WebDialog.BuilderBase<FeedDialogBuilder>
  {
    private static final String CAPTION_PARAM = "caption";
    private static final String DESCRIPTION_PARAM = "description";
    private static final String FEED_DIALOG = "feed";
    private static final String FROM_PARAM = "from";
    private static final String LINK_PARAM = "link";
    private static final String NAME_PARAM = "name";
    private static final String PICTURE_PARAM = "picture";
    private static final String SOURCE_PARAM = "source";
    private static final String TO_PARAM = "to";
    
    public FeedDialogBuilder(Context paramContext)
    {
      super("feed");
    }
    
    public FeedDialogBuilder(Context paramContext, Session paramSession)
    {
      super(paramSession, "feed", null);
    }
    
    public FeedDialogBuilder(Context paramContext, Session paramSession, Bundle paramBundle)
    {
      super(paramSession, "feed", paramBundle);
    }
    
    public FeedDialogBuilder(Context paramContext, String paramString, Bundle paramBundle)
    {
      super(paramString, "feed", paramBundle);
    }
    
    public FeedDialogBuilder setCaption(String paramString)
    {
      getParameters().putString("caption", paramString);
      return this;
    }
    
    public FeedDialogBuilder setDescription(String paramString)
    {
      getParameters().putString("description", paramString);
      return this;
    }
    
    public FeedDialogBuilder setFrom(String paramString)
    {
      getParameters().putString("from", paramString);
      return this;
    }
    
    public FeedDialogBuilder setLink(String paramString)
    {
      getParameters().putString("link", paramString);
      return this;
    }
    
    public FeedDialogBuilder setName(String paramString)
    {
      getParameters().putString("name", paramString);
      return this;
    }
    
    public FeedDialogBuilder setPicture(String paramString)
    {
      getParameters().putString("picture", paramString);
      return this;
    }
    
    public FeedDialogBuilder setSource(String paramString)
    {
      getParameters().putString("source", paramString);
      return this;
    }
    
    public FeedDialogBuilder setTo(String paramString)
    {
      getParameters().putString("to", paramString);
      return this;
    }
  }
  
  public static abstract interface OnCompleteListener
  {
    public abstract void onComplete(Bundle paramBundle, FacebookException paramFacebookException);
  }
  
  public static class RequestsDialogBuilder
    extends WebDialog.BuilderBase<RequestsDialogBuilder>
  {
    private static final String APPREQUESTS_DIALOG = "apprequests";
    private static final String DATA_PARAM = "data";
    private static final String MESSAGE_PARAM = "message";
    private static final String TITLE_PARAM = "title";
    private static final String TO_PARAM = "to";
    
    public RequestsDialogBuilder(Context paramContext)
    {
      super("apprequests");
    }
    
    public RequestsDialogBuilder(Context paramContext, Session paramSession)
    {
      super(paramSession, "apprequests", null);
    }
    
    public RequestsDialogBuilder(Context paramContext, Session paramSession, Bundle paramBundle)
    {
      super(paramSession, "apprequests", paramBundle);
    }
    
    public RequestsDialogBuilder(Context paramContext, String paramString, Bundle paramBundle)
    {
      super(paramString, "apprequests", paramBundle);
    }
    
    public RequestsDialogBuilder setData(String paramString)
    {
      getParameters().putString("data", paramString);
      return this;
    }
    
    public RequestsDialogBuilder setMessage(String paramString)
    {
      getParameters().putString("message", paramString);
      return this;
    }
    
    public RequestsDialogBuilder setTitle(String paramString)
    {
      getParameters().putString("title", paramString);
      return this;
    }
    
    public RequestsDialogBuilder setTo(String paramString)
    {
      getParameters().putString("to", paramString);
      return this;
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.facebook.widget.WebDialog
 * JD-Core Version:    0.7.0.1
 */