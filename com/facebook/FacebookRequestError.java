package com.facebook;

import com.facebook.android.R.string;
import com.facebook.internal.Utility;
import java.net.HttpURLConnection;
import org.json.JSONException;
import org.json.JSONObject;

public final class FacebookRequestError
{
  private static final String BODY_KEY = "body";
  private static final String CODE_KEY = "code";
  private static final int EC_APP_NOT_INSTALLED = 458;
  private static final int EC_APP_TOO_MANY_CALLS = 4;
  private static final int EC_EXPIRED = 463;
  private static final int EC_INVALID_SESSION = 102;
  private static final int EC_INVALID_TOKEN = 190;
  private static final int EC_PASSWORD_CHANGED = 460;
  private static final int EC_PERMISSION_DENIED = 10;
  private static final Range EC_RANGE_PERMISSION = new Range(200, 299, null);
  private static final int EC_SERVICE_UNAVAILABLE = 2;
  private static final int EC_UNCONFIRMED_USER = 464;
  private static final int EC_UNKNOWN_ERROR = 1;
  private static final int EC_USER_CHECKPOINTED = 459;
  private static final int EC_USER_TOO_MANY_CALLS = 17;
  private static final String ERROR_CODE_FIELD_KEY = "code";
  private static final String ERROR_CODE_KEY = "error_code";
  private static final String ERROR_IS_TRANSIENT_KEY = "is_transient";
  private static final String ERROR_KEY = "error";
  private static final String ERROR_MESSAGE_FIELD_KEY = "message";
  private static final String ERROR_MSG_KEY = "error_msg";
  private static final String ERROR_REASON_KEY = "error_reason";
  private static final String ERROR_SUB_CODE_KEY = "error_subcode";
  private static final String ERROR_TYPE_FIELD_KEY = "type";
  private static final String ERROR_USER_MSG_KEY = "error_user_msg";
  private static final String ERROR_USER_TITLE_KEY = "error_user_title";
  private static final Range HTTP_RANGE_CLIENT_ERROR = new Range(400, 499, null);
  private static final Range HTTP_RANGE_SERVER_ERROR = new Range(500, 599, null);
  private static final Range HTTP_RANGE_SUCCESS = new Range(200, 299, null);
  public static final int INVALID_ERROR_CODE = -1;
  public static final int INVALID_HTTP_STATUS_CODE = -1;
  private static final int INVALID_MESSAGE_ID;
  private final Object batchRequestResult;
  private final Category category;
  private final HttpURLConnection connection;
  private final int errorCode;
  private final boolean errorIsTransient;
  private final String errorMessage;
  private final String errorType;
  private final String errorUserMessage;
  private final String errorUserTitle;
  private final FacebookException exception;
  private final JSONObject requestResult;
  private final JSONObject requestResultBody;
  private final int requestStatusCode;
  private final boolean shouldNotifyUser;
  private final int subErrorCode;
  private final int userActionMessageId;
  
  private FacebookRequestError(int paramInt1, int paramInt2, int paramInt3, String paramString1, String paramString2, String paramString3, String paramString4, boolean paramBoolean, JSONObject paramJSONObject1, JSONObject paramJSONObject2, Object paramObject, HttpURLConnection paramHttpURLConnection)
  {
    this(paramInt1, paramInt2, paramInt3, paramString1, paramString2, paramString3, paramString4, paramBoolean, paramJSONObject1, paramJSONObject2, paramObject, paramHttpURLConnection, null);
  }
  
  private FacebookRequestError(int paramInt1, int paramInt2, int paramInt3, String paramString1, String paramString2, String paramString3, String paramString4, boolean paramBoolean, JSONObject paramJSONObject1, JSONObject paramJSONObject2, Object paramObject, HttpURLConnection paramHttpURLConnection, FacebookException paramFacebookException)
  {
    this.requestStatusCode = paramInt1;
    this.errorCode = paramInt2;
    this.subErrorCode = paramInt3;
    this.errorType = paramString1;
    this.errorMessage = paramString2;
    this.requestResultBody = paramJSONObject1;
    this.requestResult = paramJSONObject2;
    this.batchRequestResult = paramObject;
    this.connection = paramHttpURLConnection;
    this.errorUserTitle = paramString3;
    this.errorUserMessage = paramString4;
    this.errorIsTransient = paramBoolean;
    int i;
    int j;
    Category localCategory;
    if (paramFacebookException != null)
    {
      this.exception = paramFacebookException;
      i = 1;
      j = 0;
      if (i == 0) {
        break label158;
      }
      localCategory = Category.CLIENT;
      j = 0;
      label103:
      if ((paramString4 == null) || (paramString4.length() <= 0)) {
        break label369;
      }
    }
    label158:
    label341:
    label343:
    label369:
    for (boolean bool = true;; bool = false)
    {
      this.category = localCategory;
      this.userActionMessageId = j;
      this.shouldNotifyUser = bool;
      return;
      this.exception = new FacebookServiceException(this, paramString2);
      i = 0;
      break;
      if ((paramInt2 == 1) || (paramInt2 == 2)) {
        localCategory = Category.SERVER;
      }
      for (;;)
      {
        if (localCategory != null) {
          break label341;
        }
        if (!HTTP_RANGE_CLIENT_ERROR.contains(paramInt1)) {
          break label343;
        }
        localCategory = Category.BAD_REQUEST;
        break;
        if ((paramInt2 == 4) || (paramInt2 == 17))
        {
          localCategory = Category.THROTTLING;
          j = 0;
        }
        else if ((paramInt2 == 10) || (EC_RANGE_PERMISSION.contains(paramInt2)))
        {
          localCategory = Category.PERMISSION;
          j = R.string.com_facebook_requesterror_permissions;
        }
        else if (paramInt2 != 102)
        {
          localCategory = null;
          j = 0;
          if (paramInt2 != 190) {}
        }
        else if ((paramInt3 == 459) || (paramInt3 == 464))
        {
          localCategory = Category.AUTHENTICATION_RETRY;
          j = R.string.com_facebook_requesterror_web_login;
        }
        else
        {
          localCategory = Category.AUTHENTICATION_REOPEN_SESSION;
          if ((paramInt3 == 458) || (paramInt3 == 463)) {
            j = R.string.com_facebook_requesterror_relogin;
          } else if (paramInt3 == 460) {
            j = R.string.com_facebook_requesterror_password_changed;
          } else {
            j = R.string.com_facebook_requesterror_reconnect;
          }
        }
      }
      break label103;
      if (HTTP_RANGE_SERVER_ERROR.contains(paramInt1))
      {
        localCategory = Category.SERVER;
        break label103;
      }
      localCategory = Category.OTHER;
      break label103;
    }
  }
  
  public FacebookRequestError(int paramInt, String paramString1, String paramString2)
  {
    this(-1, paramInt, -1, paramString1, paramString2, null, null, false, null, null, null, null, null);
  }
  
  FacebookRequestError(HttpURLConnection paramHttpURLConnection, Exception paramException) {}
  
  static FacebookRequestError checkResponseAndCreateError(JSONObject paramJSONObject, Object paramObject, HttpURLConnection paramHttpURLConnection)
  {
    try
    {
      if (paramJSONObject.has("code"))
      {
        int i = paramJSONObject.getInt("code");
        Object localObject = Utility.getStringPropertyAsJSON(paramJSONObject, "body", "FACEBOOK_NON_JSON_RESULT");
        if ((localObject != null) && ((localObject instanceof JSONObject)))
        {
          JSONObject localJSONObject2 = (JSONObject)localObject;
          int j = -1;
          int k = -1;
          String str1;
          String str2;
          String str4;
          String str3;
          boolean bool1;
          int m;
          if (localJSONObject2.has("error"))
          {
            JSONObject localJSONObject3 = (JSONObject)Utility.getStringPropertyAsJSON(localJSONObject2, "error", null);
            str1 = localJSONObject3.optString("type", null);
            str2 = localJSONObject3.optString("message", null);
            j = localJSONObject3.optInt("code", -1);
            k = localJSONObject3.optInt("error_subcode", -1);
            str4 = localJSONObject3.optString("error_user_msg", null);
            str3 = localJSONObject3.optString("error_user_title", null);
            bool1 = localJSONObject3.optBoolean("is_transient", false);
            m = 1;
          }
          while (m != 0)
          {
            return new FacebookRequestError(i, j, k, str1, str2, str3, str4, bool1, localJSONObject2, paramJSONObject, paramObject, paramHttpURLConnection);
            if ((!localJSONObject2.has("error_code")) && (!localJSONObject2.has("error_msg")))
            {
              boolean bool2 = localJSONObject2.has("error_reason");
              str1 = null;
              str2 = null;
              str3 = null;
              str4 = null;
              bool1 = false;
              m = 0;
              if (!bool2) {}
            }
            else
            {
              str1 = localJSONObject2.optString("error_reason", null);
              str2 = localJSONObject2.optString("error_msg", null);
              j = localJSONObject2.optInt("error_code", -1);
              k = localJSONObject2.optInt("error_subcode", -1);
              m = 1;
              str3 = null;
              str4 = null;
              bool1 = false;
            }
          }
        }
        if (!HTTP_RANGE_SUCCESS.contains(i))
        {
          if (paramJSONObject.has("body")) {}
          for (JSONObject localJSONObject1 = (JSONObject)Utility.getStringPropertyAsJSON(paramJSONObject, "body", "FACEBOOK_NON_JSON_RESULT");; localJSONObject1 = null)
          {
            FacebookRequestError localFacebookRequestError = new FacebookRequestError(i, -1, -1, null, null, null, null, false, localJSONObject1, paramJSONObject, paramObject, paramHttpURLConnection);
            return localFacebookRequestError;
          }
        }
      }
      return null;
    }
    catch (JSONException localJSONException) {}
  }
  
  public Object getBatchRequestResult()
  {
    return this.batchRequestResult;
  }
  
  public Category getCategory()
  {
    return this.category;
  }
  
  public HttpURLConnection getConnection()
  {
    return this.connection;
  }
  
  public int getErrorCode()
  {
    return this.errorCode;
  }
  
  public boolean getErrorIsTransient()
  {
    return this.errorIsTransient;
  }
  
  public String getErrorMessage()
  {
    if (this.errorMessage != null) {
      return this.errorMessage;
    }
    return this.exception.getLocalizedMessage();
  }
  
  public String getErrorType()
  {
    return this.errorType;
  }
  
  public String getErrorUserMessage()
  {
    return this.errorUserMessage;
  }
  
  public String getErrorUserTitle()
  {
    return this.errorUserTitle;
  }
  
  public FacebookException getException()
  {
    return this.exception;
  }
  
  public JSONObject getRequestResult()
  {
    return this.requestResult;
  }
  
  public JSONObject getRequestResultBody()
  {
    return this.requestResultBody;
  }
  
  public int getRequestStatusCode()
  {
    return this.requestStatusCode;
  }
  
  public int getSubErrorCode()
  {
    return this.subErrorCode;
  }
  
  public int getUserActionMessageId()
  {
    return this.userActionMessageId;
  }
  
  public boolean shouldNotifyUser()
  {
    return this.shouldNotifyUser;
  }
  
  public String toString()
  {
    return "{HttpStatus: " + this.requestStatusCode + ", errorCode: " + this.errorCode + ", errorType: " + this.errorType + ", errorMessage: " + getErrorMessage() + "}";
  }
  
  public static enum Category
  {
    static
    {
      AUTHENTICATION_REOPEN_SESSION = new Category("AUTHENTICATION_REOPEN_SESSION", 1);
      PERMISSION = new Category("PERMISSION", 2);
      SERVER = new Category("SERVER", 3);
      THROTTLING = new Category("THROTTLING", 4);
      OTHER = new Category("OTHER", 5);
      BAD_REQUEST = new Category("BAD_REQUEST", 6);
      CLIENT = new Category("CLIENT", 7);
      Category[] arrayOfCategory = new Category[8];
      arrayOfCategory[0] = AUTHENTICATION_RETRY;
      arrayOfCategory[1] = AUTHENTICATION_REOPEN_SESSION;
      arrayOfCategory[2] = PERMISSION;
      arrayOfCategory[3] = SERVER;
      arrayOfCategory[4] = THROTTLING;
      arrayOfCategory[5] = OTHER;
      arrayOfCategory[6] = BAD_REQUEST;
      arrayOfCategory[7] = CLIENT;
      $VALUES = arrayOfCategory;
    }
    
    private Category() {}
  }
  
  private static class Range
  {
    private final int end;
    private final int start;
    
    private Range(int paramInt1, int paramInt2)
    {
      this.start = paramInt1;
      this.end = paramInt2;
    }
    
    boolean contains(int paramInt)
    {
      return (this.start <= paramInt) && (paramInt <= this.end);
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.facebook.FacebookRequestError
 * JD-Core Version:    0.7.0.1
 */