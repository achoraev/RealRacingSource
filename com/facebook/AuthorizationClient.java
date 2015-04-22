package com.facebook;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.CookieSyncManager;
import com.facebook.android.R.string;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.PlatformServiceClient.CompletedListener;
import com.facebook.internal.ServerProtocol;
import com.facebook.internal.Utility;
import com.facebook.model.GraphUser;
import com.facebook.widget.WebDialog;
import com.facebook.widget.WebDialog.Builder;
import com.facebook.widget.WebDialog.OnCompleteListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

class AuthorizationClient
  implements Serializable
{
  static final String EVENT_EXTRAS_DEFAULT_AUDIENCE = "default_audience";
  static final String EVENT_EXTRAS_IS_LEGACY = "is_legacy";
  static final String EVENT_EXTRAS_LOGIN_BEHAVIOR = "login_behavior";
  static final String EVENT_EXTRAS_MISSING_INTERNET_PERMISSION = "no_internet_permission";
  static final String EVENT_EXTRAS_NEW_PERMISSIONS = "new_permissions";
  static final String EVENT_EXTRAS_NOT_TRIED = "not_tried";
  static final String EVENT_EXTRAS_PERMISSIONS = "permissions";
  static final String EVENT_EXTRAS_REQUEST_CODE = "request_code";
  static final String EVENT_EXTRAS_TRY_LEGACY = "try_legacy";
  static final String EVENT_EXTRAS_TRY_LOGIN_ACTIVITY = "try_login_activity";
  static final String EVENT_NAME_LOGIN_COMPLETE = "fb_mobile_login_complete";
  private static final String EVENT_NAME_LOGIN_METHOD_COMPLETE = "fb_mobile_login_method_complete";
  private static final String EVENT_NAME_LOGIN_METHOD_START = "fb_mobile_login_method_start";
  static final String EVENT_NAME_LOGIN_START = "fb_mobile_login_start";
  static final String EVENT_PARAM_AUTH_LOGGER_ID = "0_auth_logger_id";
  static final String EVENT_PARAM_ERROR_CODE = "4_error_code";
  static final String EVENT_PARAM_ERROR_MESSAGE = "5_error_message";
  static final String EVENT_PARAM_EXTRAS = "6_extras";
  static final String EVENT_PARAM_LOGIN_RESULT = "2_result";
  static final String EVENT_PARAM_METHOD = "3_method";
  private static final String EVENT_PARAM_METHOD_RESULT_SKIPPED = "skipped";
  static final String EVENT_PARAM_TIMESTAMP = "1_timestamp_ms";
  private static final String TAG = "Facebook-AuthorizationClient";
  private static final String WEB_VIEW_AUTH_HANDLER_STORE = "com.facebook.AuthorizationClient.WebViewAuthHandler.TOKEN_STORE_KEY";
  private static final String WEB_VIEW_AUTH_HANDLER_TOKEN_KEY = "TOKEN";
  private static final long serialVersionUID = 1L;
  private transient AppEventsLogger appEventsLogger;
  transient BackgroundProcessingListener backgroundProcessingListener;
  transient boolean checkedInternetPermission;
  transient Context context;
  AuthHandler currentHandler;
  List<AuthHandler> handlersToTry;
  Map<String, String> loggingExtras;
  transient OnCompletedListener onCompletedListener;
  AuthorizationRequest pendingRequest;
  transient StartActivityDelegate startActivityDelegate;
  
  private void addLoggingExtra(String paramString1, String paramString2, boolean paramBoolean)
  {
    if (this.loggingExtras == null) {
      this.loggingExtras = new HashMap();
    }
    if ((this.loggingExtras.containsKey(paramString1)) && (paramBoolean)) {
      paramString2 = (String)this.loggingExtras.get(paramString1) + "," + paramString2;
    }
    this.loggingExtras.put(paramString1, paramString2);
  }
  
  private void completeWithFailure()
  {
    complete(Result.createErrorResult(this.pendingRequest, "Login attempt failed.", null));
  }
  
  private AppEventsLogger getAppEventsLogger()
  {
    if ((this.appEventsLogger == null) || (!this.appEventsLogger.getApplicationId().equals(this.pendingRequest.getApplicationId()))) {
      this.appEventsLogger = AppEventsLogger.newLogger(this.context, this.pendingRequest.getApplicationId());
    }
    return this.appEventsLogger;
  }
  
  private static String getE2E()
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("init", System.currentTimeMillis());
      label18:
      return localJSONObject.toString();
    }
    catch (JSONException localJSONException)
    {
      break label18;
    }
  }
  
  private List<AuthHandler> getHandlerTypes(AuthorizationRequest paramAuthorizationRequest)
  {
    ArrayList localArrayList = new ArrayList();
    SessionLoginBehavior localSessionLoginBehavior = paramAuthorizationRequest.getLoginBehavior();
    if (localSessionLoginBehavior.allowsKatanaAuth())
    {
      if (!paramAuthorizationRequest.isLegacy()) {
        localArrayList.add(new GetTokenAuthHandler());
      }
      localArrayList.add(new KatanaProxyAuthHandler());
    }
    if (localSessionLoginBehavior.allowsWebViewAuth()) {
      localArrayList.add(new WebViewAuthHandler());
    }
    return localArrayList;
  }
  
  private void logAuthorizationMethodComplete(String paramString, Result paramResult, Map<String, String> paramMap)
  {
    logAuthorizationMethodComplete(paramString, paramResult.code.getLoggingValue(), paramResult.errorMessage, paramResult.errorCode, paramMap);
  }
  
  private void logAuthorizationMethodComplete(String paramString1, String paramString2, String paramString3, String paramString4, Map<String, String> paramMap)
  {
    Bundle localBundle;
    if (this.pendingRequest == null)
    {
      localBundle = newAuthorizationLoggingBundle("");
      localBundle.putString("2_result", AuthorizationClient.Result.Code.ERROR.getLoggingValue());
      localBundle.putString("5_error_message", "Unexpected call to logAuthorizationMethodComplete with null pendingRequest.");
    }
    for (;;)
    {
      localBundle.putString("3_method", paramString1);
      localBundle.putLong("1_timestamp_ms", System.currentTimeMillis());
      getAppEventsLogger().logSdkEvent("fb_mobile_login_method_complete", null, localBundle);
      return;
      localBundle = newAuthorizationLoggingBundle(this.pendingRequest.getAuthId());
      if (paramString2 != null) {
        localBundle.putString("2_result", paramString2);
      }
      if (paramString3 != null) {
        localBundle.putString("5_error_message", paramString3);
      }
      if (paramString4 != null) {
        localBundle.putString("4_error_code", paramString4);
      }
      if ((paramMap != null) && (!paramMap.isEmpty())) {
        localBundle.putString("6_extras", new JSONObject(paramMap).toString());
      }
    }
  }
  
  private void logAuthorizationMethodStart(String paramString)
  {
    Bundle localBundle = newAuthorizationLoggingBundle(this.pendingRequest.getAuthId());
    localBundle.putLong("1_timestamp_ms", System.currentTimeMillis());
    localBundle.putString("3_method", paramString);
    getAppEventsLogger().logSdkEvent("fb_mobile_login_method_start", null, localBundle);
  }
  
  private void logWebLoginCompleted(String paramString1, String paramString2)
  {
    AppEventsLogger localAppEventsLogger = AppEventsLogger.newLogger(this.context, paramString1);
    Bundle localBundle = new Bundle();
    localBundle.putString("fb_web_login_e2e", paramString2);
    localBundle.putLong("fb_web_login_switchback_time", System.currentTimeMillis());
    localBundle.putString("app_id", paramString1);
    localAppEventsLogger.logSdkEvent("fb_dialogs_web_login_dialog_complete", null, localBundle);
  }
  
  static Bundle newAuthorizationLoggingBundle(String paramString)
  {
    Bundle localBundle = new Bundle();
    localBundle.putLong("1_timestamp_ms", System.currentTimeMillis());
    localBundle.putString("0_auth_logger_id", paramString);
    localBundle.putString("3_method", "");
    localBundle.putString("2_result", "");
    localBundle.putString("5_error_message", "");
    localBundle.putString("4_error_code", "");
    localBundle.putString("6_extras", "");
    return localBundle;
  }
  
  private void notifyBackgroundProcessingStart()
  {
    if (this.backgroundProcessingListener != null) {
      this.backgroundProcessingListener.onBackgroundProcessingStarted();
    }
  }
  
  private void notifyBackgroundProcessingStop()
  {
    if (this.backgroundProcessingListener != null) {
      this.backgroundProcessingListener.onBackgroundProcessingStopped();
    }
  }
  
  private void notifyOnCompleteListener(Result paramResult)
  {
    if (this.onCompletedListener != null) {
      this.onCompletedListener.onCompleted(paramResult);
    }
  }
  
  void authorize(AuthorizationRequest paramAuthorizationRequest)
  {
    if (paramAuthorizationRequest == null) {}
    do
    {
      return;
      if (this.pendingRequest != null) {
        throw new FacebookException("Attempted to authorize while a request is pending.");
      }
    } while ((paramAuthorizationRequest.needsNewTokenValidation()) && (!checkInternetPermission()));
    this.pendingRequest = paramAuthorizationRequest;
    this.handlersToTry = getHandlerTypes(paramAuthorizationRequest);
    tryNextHandler();
  }
  
  void cancelCurrentHandler()
  {
    if (this.currentHandler != null) {
      this.currentHandler.cancel();
    }
  }
  
  boolean checkInternetPermission()
  {
    if (this.checkedInternetPermission) {
      return true;
    }
    if (checkPermission("android.permission.INTERNET") != 0)
    {
      String str1 = this.context.getString(R.string.com_facebook_internet_permission_error_title);
      String str2 = this.context.getString(R.string.com_facebook_internet_permission_error_message);
      complete(Result.createErrorResult(this.pendingRequest, str1, str2));
      return false;
    }
    this.checkedInternetPermission = true;
    return true;
  }
  
  int checkPermission(String paramString)
  {
    return this.context.checkCallingOrSelfPermission(paramString);
  }
  
  void complete(Result paramResult)
  {
    if (this.currentHandler != null) {
      logAuthorizationMethodComplete(this.currentHandler.getNameForLogging(), paramResult, this.currentHandler.methodLoggingExtras);
    }
    if (this.loggingExtras != null) {
      paramResult.loggingExtras = this.loggingExtras;
    }
    this.handlersToTry = null;
    this.currentHandler = null;
    this.pendingRequest = null;
    this.loggingExtras = null;
    notifyOnCompleteListener(paramResult);
  }
  
  void completeAndValidate(Result paramResult)
  {
    if ((paramResult.token != null) && (this.pendingRequest.needsNewTokenValidation()))
    {
      validateSameFbidAndFinish(paramResult);
      return;
    }
    complete(paramResult);
  }
  
  void continueAuth()
  {
    if ((this.pendingRequest == null) || (this.currentHandler == null)) {
      throw new FacebookException("Attempted to continue authorization without a pending request.");
    }
    if (this.currentHandler.needsRestart())
    {
      this.currentHandler.cancel();
      tryCurrentHandler();
    }
  }
  
  Request createGetPermissionsRequest(String paramString)
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("access_token", paramString);
    return new Request(null, "me/permissions", localBundle, HttpMethod.GET, null);
  }
  
  Request createGetProfileIdRequest(String paramString)
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("fields", "id");
    localBundle.putString("access_token", paramString);
    return new Request(null, "me", localBundle, HttpMethod.GET, null);
  }
  
  RequestBatch createReauthValidationBatch(final Result paramResult)
  {
    final ArrayList localArrayList1 = new ArrayList();
    final ArrayList localArrayList2 = new ArrayList();
    final ArrayList localArrayList3 = new ArrayList();
    String str1 = paramResult.token.getToken();
    Request.Callback local3 = new Request.Callback()
    {
      public void onCompleted(Response paramAnonymousResponse)
      {
        try
        {
          GraphUser localGraphUser = (GraphUser)paramAnonymousResponse.getGraphObjectAs(GraphUser.class);
          if (localGraphUser != null) {
            localArrayList1.add(localGraphUser.getId());
          }
          return;
        }
        catch (Exception localException) {}
      }
    };
    String str2 = this.pendingRequest.getPreviousAccessToken();
    Request localRequest1 = createGetProfileIdRequest(str2);
    localRequest1.setCallback(local3);
    Request localRequest2 = createGetProfileIdRequest(str1);
    localRequest2.setCallback(local3);
    Request localRequest3 = createGetPermissionsRequest(str2);
    localRequest3.setCallback(new Request.Callback()
    {
      public void onCompleted(Response paramAnonymousResponse)
      {
        try
        {
          Session.PermissionsPair localPermissionsPair = Session.handlePermissionResponse(paramAnonymousResponse);
          if (localPermissionsPair != null)
          {
            localArrayList2.addAll(localPermissionsPair.getGrantedPermissions());
            localArrayList3.addAll(localPermissionsPair.getDeclinedPermissions());
          }
          return;
        }
        catch (Exception localException) {}
      }
    });
    RequestBatch localRequestBatch = new RequestBatch(new Request[] { localRequest1, localRequest2, localRequest3 });
    localRequestBatch.setBatchApplicationId(this.pendingRequest.getApplicationId());
    localRequestBatch.addCallback(new RequestBatch.Callback()
    {
      /* Error */
      public void onBatchCompleted(RequestBatch paramAnonymousRequestBatch)
      {
        // Byte code:
        //   0: aload_0
        //   1: getfield 25	com/facebook/AuthorizationClient$5:val$fbids	Ljava/util/ArrayList;
        //   4: invokevirtual 44	java/util/ArrayList:size	()I
        //   7: iconst_2
        //   8: if_icmpne +101 -> 109
        //   11: aload_0
        //   12: getfield 25	com/facebook/AuthorizationClient$5:val$fbids	Ljava/util/ArrayList;
        //   15: iconst_0
        //   16: invokevirtual 48	java/util/ArrayList:get	(I)Ljava/lang/Object;
        //   19: ifnull +90 -> 109
        //   22: aload_0
        //   23: getfield 25	com/facebook/AuthorizationClient$5:val$fbids	Ljava/util/ArrayList;
        //   26: iconst_1
        //   27: invokevirtual 48	java/util/ArrayList:get	(I)Ljava/lang/Object;
        //   30: ifnull +79 -> 109
        //   33: aload_0
        //   34: getfield 25	com/facebook/AuthorizationClient$5:val$fbids	Ljava/util/ArrayList;
        //   37: iconst_0
        //   38: invokevirtual 48	java/util/ArrayList:get	(I)Ljava/lang/Object;
        //   41: checkcast 50	java/lang/String
        //   44: aload_0
        //   45: getfield 25	com/facebook/AuthorizationClient$5:val$fbids	Ljava/util/ArrayList;
        //   48: iconst_1
        //   49: invokevirtual 48	java/util/ArrayList:get	(I)Ljava/lang/Object;
        //   52: invokevirtual 54	java/lang/String:equals	(Ljava/lang/Object;)Z
        //   55: ifeq +54 -> 109
        //   58: aload_0
        //   59: getfield 27	com/facebook/AuthorizationClient$5:val$pendingResult	Lcom/facebook/AuthorizationClient$Result;
        //   62: getfield 60	com/facebook/AuthorizationClient$Result:token	Lcom/facebook/AccessToken;
        //   65: aload_0
        //   66: getfield 29	com/facebook/AuthorizationClient$5:val$grantedPermissions	Ljava/util/ArrayList;
        //   69: aload_0
        //   70: getfield 31	com/facebook/AuthorizationClient$5:val$declinedPermissions	Ljava/util/ArrayList;
        //   73: invokestatic 66	com/facebook/AccessToken:createFromTokenWithRefreshedPermissions	(Lcom/facebook/AccessToken;Ljava/util/List;Ljava/util/List;)Lcom/facebook/AccessToken;
        //   76: astore 6
        //   78: aload_0
        //   79: getfield 23	com/facebook/AuthorizationClient$5:this$0	Lcom/facebook/AuthorizationClient;
        //   82: getfield 70	com/facebook/AuthorizationClient:pendingRequest	Lcom/facebook/AuthorizationClient$AuthorizationRequest;
        //   85: aload 6
        //   87: invokestatic 74	com/facebook/AuthorizationClient$Result:createTokenResult	(Lcom/facebook/AuthorizationClient$AuthorizationRequest;Lcom/facebook/AccessToken;)Lcom/facebook/AuthorizationClient$Result;
        //   90: astore 5
        //   92: aload_0
        //   93: getfield 23	com/facebook/AuthorizationClient$5:this$0	Lcom/facebook/AuthorizationClient;
        //   96: aload 5
        //   98: invokevirtual 78	com/facebook/AuthorizationClient:complete	(Lcom/facebook/AuthorizationClient$Result;)V
        //   101: aload_0
        //   102: getfield 23	com/facebook/AuthorizationClient$5:this$0	Lcom/facebook/AuthorizationClient;
        //   105: invokestatic 82	com/facebook/AuthorizationClient:access$000	(Lcom/facebook/AuthorizationClient;)V
        //   108: return
        //   109: aload_0
        //   110: getfield 23	com/facebook/AuthorizationClient$5:this$0	Lcom/facebook/AuthorizationClient;
        //   113: getfield 70	com/facebook/AuthorizationClient:pendingRequest	Lcom/facebook/AuthorizationClient$AuthorizationRequest;
        //   116: ldc 84
        //   118: aconst_null
        //   119: invokestatic 88	com/facebook/AuthorizationClient$Result:createErrorResult	(Lcom/facebook/AuthorizationClient$AuthorizationRequest;Ljava/lang/String;Ljava/lang/String;)Lcom/facebook/AuthorizationClient$Result;
        //   122: astore 4
        //   124: aload 4
        //   126: astore 5
        //   128: goto -36 -> 92
        //   131: astore_3
        //   132: aload_0
        //   133: getfield 23	com/facebook/AuthorizationClient$5:this$0	Lcom/facebook/AuthorizationClient;
        //   136: aload_0
        //   137: getfield 23	com/facebook/AuthorizationClient$5:this$0	Lcom/facebook/AuthorizationClient;
        //   140: getfield 70	com/facebook/AuthorizationClient:pendingRequest	Lcom/facebook/AuthorizationClient$AuthorizationRequest;
        //   143: ldc 90
        //   145: aload_3
        //   146: invokevirtual 94	java/lang/Exception:getMessage	()Ljava/lang/String;
        //   149: invokestatic 88	com/facebook/AuthorizationClient$Result:createErrorResult	(Lcom/facebook/AuthorizationClient$AuthorizationRequest;Ljava/lang/String;Ljava/lang/String;)Lcom/facebook/AuthorizationClient$Result;
        //   152: invokevirtual 78	com/facebook/AuthorizationClient:complete	(Lcom/facebook/AuthorizationClient$Result;)V
        //   155: aload_0
        //   156: getfield 23	com/facebook/AuthorizationClient$5:this$0	Lcom/facebook/AuthorizationClient;
        //   159: invokestatic 82	com/facebook/AuthorizationClient:access$000	(Lcom/facebook/AuthorizationClient;)V
        //   162: return
        //   163: astore_2
        //   164: aload_0
        //   165: getfield 23	com/facebook/AuthorizationClient$5:this$0	Lcom/facebook/AuthorizationClient;
        //   168: invokestatic 82	com/facebook/AuthorizationClient:access$000	(Lcom/facebook/AuthorizationClient;)V
        //   171: aload_2
        //   172: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	173	0	this	5
        //   0	173	1	paramAnonymousRequestBatch	RequestBatch
        //   163	9	2	localObject1	Object
        //   131	15	3	localException	Exception
        //   122	3	4	localResult	AuthorizationClient.Result
        //   90	37	5	localObject2	Object
        //   76	10	6	localAccessToken	AccessToken
        // Exception table:
        //   from	to	target	type
        //   0	92	131	java/lang/Exception
        //   92	101	131	java/lang/Exception
        //   109	124	131	java/lang/Exception
        //   0	92	163	finally
        //   92	101	163	finally
        //   109	124	163	finally
        //   132	155	163	finally
      }
    });
    return localRequestBatch;
  }
  
  BackgroundProcessingListener getBackgroundProcessingListener()
  {
    return this.backgroundProcessingListener;
  }
  
  boolean getInProgress()
  {
    return (this.pendingRequest != null) && (this.currentHandler != null);
  }
  
  OnCompletedListener getOnCompletedListener()
  {
    return this.onCompletedListener;
  }
  
  StartActivityDelegate getStartActivityDelegate()
  {
    if (this.startActivityDelegate != null) {
      return this.startActivityDelegate;
    }
    if (this.pendingRequest != null) {
      new StartActivityDelegate()
      {
        public Activity getActivityContext()
        {
          return AuthorizationClient.this.pendingRequest.getStartActivityDelegate().getActivityContext();
        }
        
        public void startActivityForResult(Intent paramAnonymousIntent, int paramAnonymousInt)
        {
          AuthorizationClient.this.pendingRequest.getStartActivityDelegate().startActivityForResult(paramAnonymousIntent, paramAnonymousInt);
        }
      };
    }
    return null;
  }
  
  boolean onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt1 == this.pendingRequest.getRequestCode()) {
      return this.currentHandler.onActivityResult(paramInt1, paramInt2, paramIntent);
    }
    return false;
  }
  
  void setBackgroundProcessingListener(BackgroundProcessingListener paramBackgroundProcessingListener)
  {
    this.backgroundProcessingListener = paramBackgroundProcessingListener;
  }
  
  void setContext(final Activity paramActivity)
  {
    this.context = paramActivity;
    this.startActivityDelegate = new StartActivityDelegate()
    {
      public Activity getActivityContext()
      {
        return paramActivity;
      }
      
      public void startActivityForResult(Intent paramAnonymousIntent, int paramAnonymousInt)
      {
        paramActivity.startActivityForResult(paramAnonymousIntent, paramAnonymousInt);
      }
    };
  }
  
  void setContext(Context paramContext)
  {
    this.context = paramContext;
    this.startActivityDelegate = null;
  }
  
  void setOnCompletedListener(OnCompletedListener paramOnCompletedListener)
  {
    this.onCompletedListener = paramOnCompletedListener;
  }
  
  void startOrContinueAuth(AuthorizationRequest paramAuthorizationRequest)
  {
    if (getInProgress())
    {
      continueAuth();
      return;
    }
    authorize(paramAuthorizationRequest);
  }
  
  boolean tryCurrentHandler()
  {
    if ((this.currentHandler.needsInternetPermission()) && (!checkInternetPermission()))
    {
      addLoggingExtra("no_internet_permission", "1", false);
      return false;
    }
    boolean bool = this.currentHandler.tryAuthorize(this.pendingRequest);
    if (bool)
    {
      logAuthorizationMethodStart(this.currentHandler.getNameForLogging());
      return bool;
    }
    addLoggingExtra("not_tried", this.currentHandler.getNameForLogging(), true);
    return bool;
  }
  
  void tryNextHandler()
  {
    if (this.currentHandler != null) {
      logAuthorizationMethodComplete(this.currentHandler.getNameForLogging(), "skipped", null, null, this.currentHandler.methodLoggingExtras);
    }
    do
    {
      if ((this.handlersToTry == null) || (this.handlersToTry.isEmpty())) {
        break;
      }
      this.currentHandler = ((AuthHandler)this.handlersToTry.remove(0));
    } while (!tryCurrentHandler());
    while (this.pendingRequest == null) {
      return;
    }
    completeWithFailure();
  }
  
  void validateSameFbidAndFinish(Result paramResult)
  {
    if (paramResult.token == null) {
      throw new FacebookException("Can't validate without a token");
    }
    RequestBatch localRequestBatch = createReauthValidationBatch(paramResult);
    notifyBackgroundProcessingStart();
    localRequestBatch.executeAsync();
  }
  
  static class AuthDialogBuilder
    extends WebDialog.Builder
  {
    private static final String OAUTH_DIALOG = "oauth";
    static final String REDIRECT_URI = "fbconnect://success";
    private String e2e;
    private boolean isRerequest;
    
    public AuthDialogBuilder(Context paramContext, String paramString, Bundle paramBundle)
    {
      super(paramString, "oauth", paramBundle);
    }
    
    public WebDialog build()
    {
      Bundle localBundle = getParameters();
      localBundle.putString("redirect_uri", "fbconnect://success");
      localBundle.putString("client_id", getApplicationId());
      localBundle.putString("e2e", this.e2e);
      localBundle.putString("response_type", "token");
      localBundle.putString("return_scopes", "true");
      if ((this.isRerequest) && (!Settings.getPlatformCompatibilityEnabled())) {
        localBundle.putString("auth_type", "rerequest");
      }
      return new WebDialog(getContext(), "oauth", localBundle, getTheme(), getListener());
    }
    
    public AuthDialogBuilder setE2E(String paramString)
    {
      this.e2e = paramString;
      return this;
    }
    
    public AuthDialogBuilder setIsRerequest(boolean paramBoolean)
    {
      this.isRerequest = paramBoolean;
      return this;
    }
  }
  
  abstract class AuthHandler
    implements Serializable
  {
    private static final long serialVersionUID = 1L;
    Map<String, String> methodLoggingExtras;
    
    AuthHandler() {}
    
    protected void addLoggingExtra(String paramString, Object paramObject)
    {
      if (this.methodLoggingExtras == null) {
        this.methodLoggingExtras = new HashMap();
      }
      Map localMap = this.methodLoggingExtras;
      if (paramObject == null) {}
      for (Object localObject = null;; localObject = paramObject.toString())
      {
        localMap.put(paramString, localObject);
        return;
      }
    }
    
    void cancel() {}
    
    abstract String getNameForLogging();
    
    boolean needsInternetPermission()
    {
      return false;
    }
    
    boolean needsRestart()
    {
      return false;
    }
    
    boolean onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
    {
      return false;
    }
    
    abstract boolean tryAuthorize(AuthorizationClient.AuthorizationRequest paramAuthorizationRequest);
  }
  
  static class AuthorizationRequest
    implements Serializable
  {
    private static final long serialVersionUID = 1L;
    private final String applicationId;
    private final String authId;
    private final SessionDefaultAudience defaultAudience;
    private boolean isLegacy = false;
    private boolean isRerequest = false;
    private final SessionLoginBehavior loginBehavior;
    private List<String> permissions;
    private final String previousAccessToken;
    private final int requestCode;
    private final transient AuthorizationClient.StartActivityDelegate startActivityDelegate;
    
    AuthorizationRequest(SessionLoginBehavior paramSessionLoginBehavior, int paramInt, boolean paramBoolean, List<String> paramList, SessionDefaultAudience paramSessionDefaultAudience, String paramString1, String paramString2, AuthorizationClient.StartActivityDelegate paramStartActivityDelegate, String paramString3)
    {
      this.loginBehavior = paramSessionLoginBehavior;
      this.requestCode = paramInt;
      this.isLegacy = paramBoolean;
      this.permissions = paramList;
      this.defaultAudience = paramSessionDefaultAudience;
      this.applicationId = paramString1;
      this.previousAccessToken = paramString2;
      this.startActivityDelegate = paramStartActivityDelegate;
      this.authId = paramString3;
    }
    
    String getApplicationId()
    {
      return this.applicationId;
    }
    
    String getAuthId()
    {
      return this.authId;
    }
    
    SessionDefaultAudience getDefaultAudience()
    {
      return this.defaultAudience;
    }
    
    SessionLoginBehavior getLoginBehavior()
    {
      return this.loginBehavior;
    }
    
    List<String> getPermissions()
    {
      return this.permissions;
    }
    
    String getPreviousAccessToken()
    {
      return this.previousAccessToken;
    }
    
    int getRequestCode()
    {
      return this.requestCode;
    }
    
    AuthorizationClient.StartActivityDelegate getStartActivityDelegate()
    {
      return this.startActivityDelegate;
    }
    
    boolean isLegacy()
    {
      return this.isLegacy;
    }
    
    boolean isRerequest()
    {
      return this.isRerequest;
    }
    
    boolean needsNewTokenValidation()
    {
      return (this.previousAccessToken != null) && (!this.isLegacy);
    }
    
    void setIsLegacy(boolean paramBoolean)
    {
      this.isLegacy = paramBoolean;
    }
    
    void setPermissions(List<String> paramList)
    {
      this.permissions = paramList;
    }
    
    void setRerequest(boolean paramBoolean)
    {
      this.isRerequest = paramBoolean;
    }
  }
  
  static abstract interface BackgroundProcessingListener
  {
    public abstract void onBackgroundProcessingStarted();
    
    public abstract void onBackgroundProcessingStopped();
  }
  
  class GetTokenAuthHandler
    extends AuthorizationClient.AuthHandler
  {
    private static final long serialVersionUID = 1L;
    private transient GetTokenClient getTokenClient;
    
    GetTokenAuthHandler()
    {
      super();
    }
    
    void cancel()
    {
      if (this.getTokenClient != null)
      {
        this.getTokenClient.cancel();
        this.getTokenClient = null;
      }
    }
    
    String getNameForLogging()
    {
      return "get_token";
    }
    
    void getTokenCompleted(AuthorizationClient.AuthorizationRequest paramAuthorizationRequest, Bundle paramBundle)
    {
      this.getTokenClient = null;
      AuthorizationClient.this.notifyBackgroundProcessingStop();
      if (paramBundle != null)
      {
        ArrayList localArrayList1 = paramBundle.getStringArrayList("com.facebook.platform.extra.PERMISSIONS");
        List localList = paramAuthorizationRequest.getPermissions();
        if ((localArrayList1 != null) && ((localList == null) || (localArrayList1.containsAll(localList))))
        {
          AccessToken localAccessToken = AccessToken.createFromNativeLogin(paramBundle, AccessTokenSource.FACEBOOK_APPLICATION_SERVICE);
          AuthorizationClient.Result localResult = AuthorizationClient.Result.createTokenResult(AuthorizationClient.this.pendingRequest, localAccessToken);
          AuthorizationClient.this.completeAndValidate(localResult);
          return;
        }
        ArrayList localArrayList2 = new ArrayList();
        Iterator localIterator = localList.iterator();
        while (localIterator.hasNext())
        {
          String str = (String)localIterator.next();
          if (!localArrayList1.contains(str)) {
            localArrayList2.add(str);
          }
        }
        if (!localArrayList2.isEmpty()) {
          addLoggingExtra("new_permissions", TextUtils.join(",", localArrayList2));
        }
        paramAuthorizationRequest.setPermissions(localArrayList2);
      }
      AuthorizationClient.this.tryNextHandler();
    }
    
    boolean needsRestart()
    {
      return this.getTokenClient == null;
    }
    
    boolean tryAuthorize(final AuthorizationClient.AuthorizationRequest paramAuthorizationRequest)
    {
      this.getTokenClient = new GetTokenClient(AuthorizationClient.this.context, paramAuthorizationRequest.getApplicationId());
      if (!this.getTokenClient.start()) {
        return false;
      }
      AuthorizationClient.this.notifyBackgroundProcessingStart();
      PlatformServiceClient.CompletedListener local1 = new PlatformServiceClient.CompletedListener()
      {
        public void completed(Bundle paramAnonymousBundle)
        {
          AuthorizationClient.GetTokenAuthHandler.this.getTokenCompleted(paramAuthorizationRequest, paramAnonymousBundle);
        }
      };
      this.getTokenClient.setCompletedListener(local1);
      return true;
    }
  }
  
  abstract class KatanaAuthHandler
    extends AuthorizationClient.AuthHandler
  {
    private static final long serialVersionUID = 1L;
    
    KatanaAuthHandler()
    {
      super();
    }
    
    protected boolean tryIntent(Intent paramIntent, int paramInt)
    {
      if (paramIntent == null) {
        return false;
      }
      try
      {
        AuthorizationClient.this.getStartActivityDelegate().startActivityForResult(paramIntent, paramInt);
        return true;
      }
      catch (ActivityNotFoundException localActivityNotFoundException) {}
      return false;
    }
  }
  
  class KatanaProxyAuthHandler
    extends AuthorizationClient.KatanaAuthHandler
  {
    private static final long serialVersionUID = 1L;
    private String applicationId;
    
    KatanaProxyAuthHandler()
    {
      super();
    }
    
    private AuthorizationClient.Result handleResultOk(Intent paramIntent)
    {
      Bundle localBundle = paramIntent.getExtras();
      String str1 = localBundle.getString("error");
      if (str1 == null) {
        str1 = localBundle.getString("error_type");
      }
      String str2 = localBundle.getString("error_code");
      String str3 = localBundle.getString("error_message");
      if (str3 == null) {
        str3 = localBundle.getString("error_description");
      }
      String str4 = localBundle.getString("e2e");
      if (!Utility.isNullOrEmpty(str4)) {
        AuthorizationClient.this.logWebLoginCompleted(this.applicationId, str4);
      }
      AuthorizationClient.Result localResult;
      if ((str1 == null) && (str2 == null) && (str3 == null))
      {
        AccessToken localAccessToken = AccessToken.createFromWebBundle(AuthorizationClient.this.pendingRequest.getPermissions(), localBundle, AccessTokenSource.FACEBOOK_APPLICATION_WEB);
        localResult = AuthorizationClient.Result.createTokenResult(AuthorizationClient.this.pendingRequest, localAccessToken);
      }
      boolean bool;
      do
      {
        return localResult;
        bool = ServerProtocol.errorsProxyAuthDisabled.contains(str1);
        localResult = null;
      } while (bool);
      if (ServerProtocol.errorsUserCanceled.contains(str1)) {
        return AuthorizationClient.Result.createCancelResult(AuthorizationClient.this.pendingRequest, null);
      }
      return AuthorizationClient.Result.createErrorResult(AuthorizationClient.this.pendingRequest, str1, str3, str2);
    }
    
    String getNameForLogging()
    {
      return "katana_proxy_auth";
    }
    
    boolean onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
    {
      AuthorizationClient.Result localResult;
      if (paramIntent == null)
      {
        localResult = AuthorizationClient.Result.createCancelResult(AuthorizationClient.this.pendingRequest, "Operation canceled");
        if (localResult == null) {
          break label92;
        }
        AuthorizationClient.this.completeAndValidate(localResult);
      }
      for (;;)
      {
        return true;
        if (paramInt2 == 0)
        {
          localResult = AuthorizationClient.Result.createCancelResult(AuthorizationClient.this.pendingRequest, paramIntent.getStringExtra("error"));
          break;
        }
        if (paramInt2 != -1)
        {
          localResult = AuthorizationClient.Result.createErrorResult(AuthorizationClient.this.pendingRequest, "Unexpected resultCode from authorization.", null);
          break;
        }
        localResult = handleResultOk(paramIntent);
        break;
        label92:
        AuthorizationClient.this.tryNextHandler();
      }
    }
    
    boolean tryAuthorize(AuthorizationClient.AuthorizationRequest paramAuthorizationRequest)
    {
      this.applicationId = paramAuthorizationRequest.getApplicationId();
      String str = AuthorizationClient.access$100();
      Intent localIntent = NativeProtocol.createProxyAuthIntent(AuthorizationClient.this.context, paramAuthorizationRequest.getApplicationId(), paramAuthorizationRequest.getPermissions(), str, paramAuthorizationRequest.isRerequest(), paramAuthorizationRequest.getDefaultAudience());
      addLoggingExtra("e2e", str);
      return tryIntent(localIntent, paramAuthorizationRequest.getRequestCode());
    }
  }
  
  static abstract interface OnCompletedListener
  {
    public abstract void onCompleted(AuthorizationClient.Result paramResult);
  }
  
  static class Result
    implements Serializable
  {
    private static final long serialVersionUID = 1L;
    final Code code;
    final String errorCode;
    final String errorMessage;
    Map<String, String> loggingExtras;
    final AuthorizationClient.AuthorizationRequest request;
    final AccessToken token;
    
    private Result(AuthorizationClient.AuthorizationRequest paramAuthorizationRequest, Code paramCode, AccessToken paramAccessToken, String paramString1, String paramString2)
    {
      this.request = paramAuthorizationRequest;
      this.token = paramAccessToken;
      this.errorMessage = paramString1;
      this.code = paramCode;
      this.errorCode = paramString2;
    }
    
    static Result createCancelResult(AuthorizationClient.AuthorizationRequest paramAuthorizationRequest, String paramString)
    {
      return new Result(paramAuthorizationRequest, Code.CANCEL, null, paramString, null);
    }
    
    static Result createErrorResult(AuthorizationClient.AuthorizationRequest paramAuthorizationRequest, String paramString1, String paramString2)
    {
      return createErrorResult(paramAuthorizationRequest, paramString1, paramString2, null);
    }
    
    static Result createErrorResult(AuthorizationClient.AuthorizationRequest paramAuthorizationRequest, String paramString1, String paramString2, String paramString3)
    {
      String str = TextUtils.join(": ", Utility.asListNoNulls(new String[] { paramString1, paramString2 }));
      return new Result(paramAuthorizationRequest, Code.ERROR, null, str, paramString3);
    }
    
    static Result createTokenResult(AuthorizationClient.AuthorizationRequest paramAuthorizationRequest, AccessToken paramAccessToken)
    {
      return new Result(paramAuthorizationRequest, Code.SUCCESS, paramAccessToken, null, null);
    }
    
    static enum Code
    {
      private final String loggingValue;
      
      static
      {
        CANCEL = new Code("CANCEL", 1, "cancel");
        ERROR = new Code("ERROR", 2, "error");
        Code[] arrayOfCode = new Code[3];
        arrayOfCode[0] = SUCCESS;
        arrayOfCode[1] = CANCEL;
        arrayOfCode[2] = ERROR;
        $VALUES = arrayOfCode;
      }
      
      private Code(String paramString)
      {
        this.loggingValue = paramString;
      }
      
      String getLoggingValue()
      {
        return this.loggingValue;
      }
    }
  }
  
  static abstract interface StartActivityDelegate
  {
    public abstract Activity getActivityContext();
    
    public abstract void startActivityForResult(Intent paramIntent, int paramInt);
  }
  
  class WebViewAuthHandler
    extends AuthorizationClient.AuthHandler
  {
    private static final long serialVersionUID = 1L;
    private String applicationId;
    private String e2e;
    private transient WebDialog loginDialog;
    
    WebViewAuthHandler()
    {
      super();
    }
    
    private String loadCookieToken()
    {
      return AuthorizationClient.this.getStartActivityDelegate().getActivityContext().getSharedPreferences("com.facebook.AuthorizationClient.WebViewAuthHandler.TOKEN_STORE_KEY", 0).getString("TOKEN", "");
    }
    
    private void saveCookieToken(String paramString)
    {
      SharedPreferences.Editor localEditor = AuthorizationClient.this.getStartActivityDelegate().getActivityContext().getSharedPreferences("com.facebook.AuthorizationClient.WebViewAuthHandler.TOKEN_STORE_KEY", 0).edit();
      localEditor.putString("TOKEN", paramString);
      if (!localEditor.commit()) {
        Utility.logd("Facebook-AuthorizationClient", "Could not update saved web view auth handler token.");
      }
    }
    
    void cancel()
    {
      if (this.loginDialog != null)
      {
        this.loginDialog.dismiss();
        this.loginDialog = null;
      }
    }
    
    String getNameForLogging()
    {
      return "web_view";
    }
    
    boolean needsInternetPermission()
    {
      return true;
    }
    
    boolean needsRestart()
    {
      return true;
    }
    
    void onWebDialogComplete(AuthorizationClient.AuthorizationRequest paramAuthorizationRequest, Bundle paramBundle, FacebookException paramFacebookException)
    {
      AuthorizationClient.Result localResult;
      if (paramBundle != null)
      {
        if (paramBundle.containsKey("e2e")) {
          this.e2e = paramBundle.getString("e2e");
        }
        AccessToken localAccessToken = AccessToken.createFromWebBundle(paramAuthorizationRequest.getPermissions(), paramBundle, AccessTokenSource.WEB_VIEW);
        localResult = AuthorizationClient.Result.createTokenResult(AuthorizationClient.this.pendingRequest, localAccessToken);
        CookieSyncManager.createInstance(AuthorizationClient.this.context).sync();
        saveCookieToken(localAccessToken.getToken());
      }
      for (;;)
      {
        if (!Utility.isNullOrEmpty(this.e2e)) {
          AuthorizationClient.this.logWebLoginCompleted(this.applicationId, this.e2e);
        }
        AuthorizationClient.this.completeAndValidate(localResult);
        return;
        if ((paramFacebookException instanceof FacebookOperationCanceledException))
        {
          localResult = AuthorizationClient.Result.createCancelResult(AuthorizationClient.this.pendingRequest, "User canceled log in.");
        }
        else
        {
          this.e2e = null;
          String str1 = paramFacebookException.getMessage();
          boolean bool = paramFacebookException instanceof FacebookServiceException;
          String str2 = null;
          if (bool)
          {
            FacebookRequestError localFacebookRequestError = ((FacebookServiceException)paramFacebookException).getRequestError();
            Object[] arrayOfObject = new Object[1];
            arrayOfObject[0] = Integer.valueOf(localFacebookRequestError.getErrorCode());
            str2 = String.format("%d", arrayOfObject);
            str1 = localFacebookRequestError.toString();
          }
          localResult = AuthorizationClient.Result.createErrorResult(AuthorizationClient.this.pendingRequest, null, str1, str2);
        }
      }
    }
    
    boolean tryAuthorize(final AuthorizationClient.AuthorizationRequest paramAuthorizationRequest)
    {
      this.applicationId = paramAuthorizationRequest.getApplicationId();
      Bundle localBundle = new Bundle();
      if (!Utility.isNullOrEmpty(paramAuthorizationRequest.getPermissions()))
      {
        String str2 = TextUtils.join(",", paramAuthorizationRequest.getPermissions());
        localBundle.putString("scope", str2);
        addLoggingExtra("scope", str2);
      }
      localBundle.putString("default_audience", paramAuthorizationRequest.getDefaultAudience().getNativeProtocolAudience());
      String str1 = paramAuthorizationRequest.getPreviousAccessToken();
      if ((!Utility.isNullOrEmpty(str1)) && (str1.equals(loadCookieToken())))
      {
        localBundle.putString("access_token", str1);
        addLoggingExtra("access_token", "1");
      }
      for (;;)
      {
        WebDialog.OnCompleteListener local1 = new WebDialog.OnCompleteListener()
        {
          public void onComplete(Bundle paramAnonymousBundle, FacebookException paramAnonymousFacebookException)
          {
            AuthorizationClient.WebViewAuthHandler.this.onWebDialogComplete(paramAuthorizationRequest, paramAnonymousBundle, paramAnonymousFacebookException);
          }
        };
        this.e2e = AuthorizationClient.access$100();
        addLoggingExtra("e2e", this.e2e);
        this.loginDialog = ((WebDialog.Builder)new AuthorizationClient.AuthDialogBuilder(AuthorizationClient.this.getStartActivityDelegate().getActivityContext(), this.applicationId, localBundle).setE2E(this.e2e).setIsRerequest(paramAuthorizationRequest.isRerequest()).setOnCompleteListener(local1)).build();
        this.loginDialog.show();
        return true;
        Utility.clearFacebookCookies(AuthorizationClient.this.context);
        addLoggingExtra("access_token", "0");
      }
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.facebook.AuthorizationClient
 * JD-Core Version:    0.7.0.1
 */