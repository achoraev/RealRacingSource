package com.facebook;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public final class AccessToken
  implements Serializable
{
  static final String ACCESS_TOKEN_KEY = "access_token";
  private static final Date ALREADY_EXPIRED_EXPIRATION_TIME = MIN_DATE;
  private static final AccessTokenSource DEFAULT_ACCESS_TOKEN_SOURCE;
  private static final Date DEFAULT_EXPIRATION_TIME;
  private static final Date DEFAULT_LAST_REFRESH_TIME;
  static final String EXPIRES_IN_KEY = "expires_in";
  private static final Date MAX_DATE;
  private static final Date MIN_DATE = new Date(-9223372036854775808L);
  private static final long serialVersionUID = 1L;
  private final List<String> declinedPermissions;
  private final Date expires;
  private final Date lastRefresh;
  private final List<String> permissions;
  private final AccessTokenSource source;
  private final String token;
  
  static
  {
    MAX_DATE = new Date(9223372036854775807L);
    DEFAULT_EXPIRATION_TIME = MAX_DATE;
    DEFAULT_LAST_REFRESH_TIME = new Date();
    DEFAULT_ACCESS_TOKEN_SOURCE = AccessTokenSource.FACEBOOK_APPLICATION_WEB;
  }
  
  AccessToken(String paramString, Date paramDate1, List<String> paramList1, List<String> paramList2, AccessTokenSource paramAccessTokenSource, Date paramDate2)
  {
    if (paramList1 == null) {
      paramList1 = Collections.emptyList();
    }
    if (paramList2 == null) {
      paramList2 = Collections.emptyList();
    }
    this.expires = paramDate1;
    this.permissions = Collections.unmodifiableList(paramList1);
    this.declinedPermissions = Collections.unmodifiableList(paramList2);
    this.token = paramString;
    this.source = paramAccessTokenSource;
    this.lastRefresh = paramDate2;
  }
  
  private void appendPermissions(StringBuilder paramStringBuilder)
  {
    paramStringBuilder.append(" permissions:");
    if (this.permissions == null)
    {
      paramStringBuilder.append("null");
      return;
    }
    paramStringBuilder.append("[");
    paramStringBuilder.append(TextUtils.join(", ", this.permissions));
    paramStringBuilder.append("]");
  }
  
  static AccessToken createEmptyToken()
  {
    return new AccessToken("", ALREADY_EXPIRED_EXPIRATION_TIME, null, null, AccessTokenSource.NONE, DEFAULT_LAST_REFRESH_TIME);
  }
  
  private static AccessToken createFromBundle(List<String> paramList, Bundle paramBundle, AccessTokenSource paramAccessTokenSource, Date paramDate)
  {
    String str = paramBundle.getString("access_token");
    Date localDate = getBundleLongAsDate(paramBundle, "expires_in", paramDate);
    if ((Utility.isNullOrEmpty(str)) || (localDate == null)) {
      return null;
    }
    return new AccessToken(str, localDate, paramList, null, paramAccessTokenSource, new Date());
  }
  
  static AccessToken createFromCache(Bundle paramBundle)
  {
    List localList1 = getPermissionsFromBundle(paramBundle, "com.facebook.TokenCachingStrategy.Permissions");
    List localList2 = getPermissionsFromBundle(paramBundle, "com.facebook.TokenCachingStrategy.DeclinedPermissions");
    return new AccessToken(paramBundle.getString("com.facebook.TokenCachingStrategy.Token"), TokenCachingStrategy.getDate(paramBundle, "com.facebook.TokenCachingStrategy.ExpirationDate"), localList1, localList2, TokenCachingStrategy.getSource(paramBundle), TokenCachingStrategy.getDate(paramBundle, "com.facebook.TokenCachingStrategy.LastRefreshDate"));
  }
  
  public static AccessToken createFromExistingAccessToken(String paramString, Date paramDate1, Date paramDate2, AccessTokenSource paramAccessTokenSource, List<String> paramList)
  {
    if (paramDate1 == null) {
      paramDate1 = DEFAULT_EXPIRATION_TIME;
    }
    if (paramDate2 == null) {
      paramDate2 = DEFAULT_LAST_REFRESH_TIME;
    }
    if (paramAccessTokenSource == null) {
      paramAccessTokenSource = DEFAULT_ACCESS_TOKEN_SOURCE;
    }
    return new AccessToken(paramString, paramDate1, paramList, null, paramAccessTokenSource, paramDate2);
  }
  
  public static AccessToken createFromNativeLinkingIntent(Intent paramIntent)
  {
    Validate.notNull(paramIntent, "intent");
    if (paramIntent.getExtras() == null) {
      return null;
    }
    return createFromBundle(null, paramIntent.getExtras(), AccessTokenSource.FACEBOOK_APPLICATION_WEB, new Date());
  }
  
  static AccessToken createFromNativeLogin(Bundle paramBundle, AccessTokenSource paramAccessTokenSource)
  {
    Date localDate = getBundleLongAsDate(paramBundle, "com.facebook.platform.extra.EXPIRES_SECONDS_SINCE_EPOCH", new Date(0L));
    return createNew(paramBundle.getStringArrayList("com.facebook.platform.extra.PERMISSIONS"), null, paramBundle.getString("com.facebook.platform.extra.ACCESS_TOKEN"), localDate, paramAccessTokenSource);
  }
  
  @SuppressLint({"FieldGetter"})
  static AccessToken createFromRefresh(AccessToken paramAccessToken, Bundle paramBundle)
  {
    if ((paramAccessToken.source != AccessTokenSource.FACEBOOK_APPLICATION_WEB) && (paramAccessToken.source != AccessTokenSource.FACEBOOK_APPLICATION_NATIVE) && (paramAccessToken.source != AccessTokenSource.FACEBOOK_APPLICATION_SERVICE)) {
      throw new FacebookException("Invalid token source: " + paramAccessToken.source);
    }
    Date localDate = getBundleLongAsDate(paramBundle, "expires_in", new Date(0L));
    String str = paramBundle.getString("access_token");
    return createNew(paramAccessToken.getPermissions(), paramAccessToken.getDeclinedPermissions(), str, localDate, paramAccessToken.source);
  }
  
  static AccessToken createFromString(String paramString, List<String> paramList, AccessTokenSource paramAccessTokenSource)
  {
    return new AccessToken(paramString, DEFAULT_EXPIRATION_TIME, paramList, null, paramAccessTokenSource, DEFAULT_LAST_REFRESH_TIME);
  }
  
  static AccessToken createFromTokenWithRefreshedPermissions(AccessToken paramAccessToken, List<String> paramList1, List<String> paramList2)
  {
    return new AccessToken(paramAccessToken.token, paramAccessToken.expires, paramList1, paramList2, paramAccessToken.source, paramAccessToken.lastRefresh);
  }
  
  static AccessToken createFromWebBundle(List<String> paramList, Bundle paramBundle, AccessTokenSource paramAccessTokenSource)
  {
    Date localDate = getBundleLongAsDate(paramBundle, "expires_in", new Date());
    String str1 = paramBundle.getString("access_token");
    String str2 = paramBundle.getString("granted_scopes");
    if (!Utility.isNullOrEmpty(str2)) {
      paramList = new ArrayList(Arrays.asList(str2.split(",")));
    }
    String str3 = paramBundle.getString("denied_scopes");
    boolean bool = Utility.isNullOrEmpty(str3);
    ArrayList localArrayList = null;
    if (!bool) {
      localArrayList = new ArrayList(Arrays.asList(str3.split(",")));
    }
    return createNew(paramList, localArrayList, str1, localDate, paramAccessTokenSource);
  }
  
  private static AccessToken createNew(List<String> paramList1, List<String> paramList2, String paramString, Date paramDate, AccessTokenSource paramAccessTokenSource)
  {
    if ((Utility.isNullOrEmpty(paramString)) || (paramDate == null)) {
      return createEmptyToken();
    }
    return new AccessToken(paramString, paramDate, paramList1, paramList2, paramAccessTokenSource, new Date());
  }
  
  private static Date getBundleLongAsDate(Bundle paramBundle, String paramString, Date paramDate)
  {
    if (paramBundle == null) {
      return null;
    }
    Object localObject = paramBundle.get(paramString);
    long l2;
    if ((localObject instanceof Long)) {
      l2 = ((Long)localObject).longValue();
    }
    for (;;)
    {
      if (l2 == 0L)
      {
        return new Date(9223372036854775807L);
        if (!(localObject instanceof String)) {
          break;
        }
        try
        {
          long l1 = Long.parseLong((String)localObject);
          l2 = l1;
        }
        catch (NumberFormatException localNumberFormatException)
        {
          return null;
        }
      }
    }
    return new Date(paramDate.getTime() + 1000L * l2);
  }
  
  static List<String> getPermissionsFromBundle(Bundle paramBundle, String paramString)
  {
    ArrayList localArrayList = paramBundle.getStringArrayList(paramString);
    if (localArrayList == null) {
      return Collections.emptyList();
    }
    return Collections.unmodifiableList(new ArrayList(localArrayList));
  }
  
  private void readObject(ObjectInputStream paramObjectInputStream)
    throws InvalidObjectException
  {
    throw new InvalidObjectException("Cannot readObject, serialization proxy required");
  }
  
  private String tokenToString()
  {
    if (this.token == null) {
      return "null";
    }
    if (Settings.isLoggingBehaviorEnabled(LoggingBehavior.INCLUDE_ACCESS_TOKENS)) {
      return this.token;
    }
    return "ACCESS_TOKEN_REMOVED";
  }
  
  private Object writeReplace()
  {
    return new SerializationProxyV2(this.token, this.expires, this.permissions, this.declinedPermissions, this.source, this.lastRefresh, null);
  }
  
  public List<String> getDeclinedPermissions()
  {
    return this.declinedPermissions;
  }
  
  public Date getExpires()
  {
    return this.expires;
  }
  
  public Date getLastRefresh()
  {
    return this.lastRefresh;
  }
  
  public List<String> getPermissions()
  {
    return this.permissions;
  }
  
  public AccessTokenSource getSource()
  {
    return this.source;
  }
  
  public String getToken()
  {
    return this.token;
  }
  
  boolean isInvalid()
  {
    return (Utility.isNullOrEmpty(this.token)) || (new Date().after(this.expires));
  }
  
  Bundle toCacheBundle()
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("com.facebook.TokenCachingStrategy.Token", this.token);
    TokenCachingStrategy.putDate(localBundle, "com.facebook.TokenCachingStrategy.ExpirationDate", this.expires);
    localBundle.putStringArrayList("com.facebook.TokenCachingStrategy.Permissions", new ArrayList(this.permissions));
    localBundle.putStringArrayList("com.facebook.TokenCachingStrategy.DeclinedPermissions", new ArrayList(this.declinedPermissions));
    localBundle.putSerializable("com.facebook.TokenCachingStrategy.AccessTokenSource", this.source);
    TokenCachingStrategy.putDate(localBundle, "com.facebook.TokenCachingStrategy.LastRefreshDate", this.lastRefresh);
    return localBundle;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("{AccessToken");
    localStringBuilder.append(" token:").append(tokenToString());
    appendPermissions(localStringBuilder);
    localStringBuilder.append("}");
    return localStringBuilder.toString();
  }
  
  private static class SerializationProxyV1
    implements Serializable
  {
    private static final long serialVersionUID = -2488473066578201069L;
    private final Date expires;
    private final Date lastRefresh;
    private final List<String> permissions;
    private final AccessTokenSource source;
    private final String token;
    
    private SerializationProxyV1(String paramString, Date paramDate1, List<String> paramList, AccessTokenSource paramAccessTokenSource, Date paramDate2)
    {
      this.expires = paramDate1;
      this.permissions = paramList;
      this.token = paramString;
      this.source = paramAccessTokenSource;
      this.lastRefresh = paramDate2;
    }
    
    private Object readResolve()
    {
      return new AccessToken(this.token, this.expires, this.permissions, null, this.source, this.lastRefresh);
    }
  }
  
  private static class SerializationProxyV2
    implements Serializable
  {
    private static final long serialVersionUID = -2488473066578201068L;
    private final List<String> declinedPermissions;
    private final Date expires;
    private final Date lastRefresh;
    private final List<String> permissions;
    private final AccessTokenSource source;
    private final String token;
    
    private SerializationProxyV2(String paramString, Date paramDate1, List<String> paramList1, List<String> paramList2, AccessTokenSource paramAccessTokenSource, Date paramDate2)
    {
      this.expires = paramDate1;
      this.permissions = paramList1;
      this.declinedPermissions = paramList2;
      this.token = paramString;
      this.source = paramAccessTokenSource;
      this.lastRefresh = paramDate2;
    }
    
    private Object readResolve()
    {
      return new AccessToken(this.token, this.expires, this.permissions, this.declinedPermissions, this.source, this.lastRefresh);
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.facebook.AccessToken
 * JD-Core Version:    0.7.0.1
 */