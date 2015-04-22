package com.facebook.internal;

import com.facebook.Settings;
import java.util.Collection;

public final class ServerProtocol
{
  private static final String DIALOG_AUTHORITY_FORMAT = "m.%s";
  public static final String DIALOG_PARAM_ACCESS_TOKEN = "access_token";
  public static final String DIALOG_PARAM_APP_ID = "app_id";
  public static final String DIALOG_PARAM_AUTH_TYPE = "auth_type";
  public static final String DIALOG_PARAM_CLIENT_ID = "client_id";
  public static final String DIALOG_PARAM_DEFAULT_AUDIENCE = "default_audience";
  public static final String DIALOG_PARAM_DISPLAY = "display";
  public static final String DIALOG_PARAM_E2E = "e2e";
  public static final String DIALOG_PARAM_LEGACY_OVERRIDE = "legacy_override";
  public static final String DIALOG_PARAM_REDIRECT_URI = "redirect_uri";
  public static final String DIALOG_PARAM_RESPONSE_TYPE = "response_type";
  public static final String DIALOG_PARAM_RETURN_SCOPES = "return_scopes";
  public static final String DIALOG_PARAM_SCOPE = "scope";
  public static final String DIALOG_PATH = "dialog/";
  public static final String DIALOG_REREQUEST_AUTH_TYPE = "rerequest";
  public static final String DIALOG_RESPONSE_TYPE_TOKEN = "token";
  public static final String DIALOG_RETURN_SCOPES_TRUE = "true";
  public static final String GRAPH_API_VERSION = "v2.1";
  private static final String GRAPH_URL_FORMAT = "https://graph.%s";
  private static final String GRAPH_VIDEO_URL_FORMAT = "https://graph-video.%s";
  private static final String LEGACY_API_VERSION = "v1.0";
  public static final Collection<String> errorsProxyAuthDisabled = Utility.unmodifiableCollection(new String[] { "service_disabled", "AndroidAuthKillSwitchException" });
  public static final Collection<String> errorsUserCanceled = Utility.unmodifiableCollection(new String[] { "access_denied", "OAuthAccessDeniedException" });
  
  public static final String getAPIVersion()
  {
    if (Settings.getPlatformCompatibilityEnabled()) {
      return "v1.0";
    }
    return "v2.1";
  }
  
  public static final String getDialogAuthority()
  {
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Settings.getFacebookDomain();
    return String.format("m.%s", arrayOfObject);
  }
  
  public static final String getGraphUrlBase()
  {
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Settings.getFacebookDomain();
    return String.format("https://graph.%s", arrayOfObject);
  }
  
  public static final String getGraphVideoUrlBase()
  {
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Settings.getFacebookDomain();
    return String.format("https://graph-video.%s", arrayOfObject);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.facebook.internal.ServerProtocol
 * JD-Core Version:    0.7.0.1
 */