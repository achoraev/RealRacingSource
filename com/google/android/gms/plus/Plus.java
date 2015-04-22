package com.google.android.gms.plus;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.Optional;
import com.google.android.gms.common.api.Api.b;
import com.google.android.gms.common.api.Api.c;
import com.google.android.gms.common.api.BaseImplementation.a;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.internal.np;
import com.google.android.gms.internal.nq;
import com.google.android.gms.internal.nr;
import com.google.android.gms.internal.ns;
import com.google.android.gms.internal.nt;
import com.google.android.gms.plus.internal.PlusCommonExtras;
import com.google.android.gms.plus.internal.e;
import com.google.android.gms.plus.internal.h;
import java.util.HashSet;
import java.util.Set;

public final class Plus
{
  public static final Api<PlusOptions> API;
  public static final Account AccountApi = new np();
  public static final Api.c<e> CU = new Api.c();
  static final Api.b<e, PlusOptions> CV = new Api.b()
  {
    public e a(Context paramAnonymousContext, Looper paramAnonymousLooper, ClientSettings paramAnonymousClientSettings, Plus.PlusOptions paramAnonymousPlusOptions, GoogleApiClient.ConnectionCallbacks paramAnonymousConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramAnonymousOnConnectionFailedListener)
    {
      if (paramAnonymousPlusOptions == null) {
        paramAnonymousPlusOptions = new Plus.PlusOptions(null);
      }
      e locale = new e(paramAnonymousContext, paramAnonymousLooper, paramAnonymousConnectionCallbacks, paramAnonymousOnConnectionFailedListener, new h(paramAnonymousClientSettings.getAccountNameOrDefault(), paramAnonymousClientSettings.getScopesArray(), (String[])paramAnonymousPlusOptions.alc.toArray(new String[0]), new String[0], paramAnonymousContext.getPackageName(), paramAnonymousContext.getPackageName(), null, new PlusCommonExtras()));
      return locale;
    }
    
    public int getPriority()
    {
      return 2;
    }
  };
  public static final Moments MomentsApi;
  public static final People PeopleApi;
  public static final Scope SCOPE_PLUS_LOGIN;
  public static final Scope SCOPE_PLUS_PROFILE;
  public static final b akZ = new nr();
  public static final a ala = new nq();
  
  static
  {
    API = new Api(CV, CU, new Scope[0]);
    SCOPE_PLUS_LOGIN = new Scope("https://www.googleapis.com/auth/plus.login");
    SCOPE_PLUS_PROFILE = new Scope("https://www.googleapis.com/auth/plus.me");
    MomentsApi = new ns();
    PeopleApi = new nt();
  }
  
  public static e a(GoogleApiClient paramGoogleApiClient, Api.c<e> paramc)
  {
    boolean bool1 = true;
    boolean bool2;
    e locale;
    if (paramGoogleApiClient != null)
    {
      bool2 = bool1;
      o.b(bool2, "GoogleApiClient parameter is required.");
      o.a(paramGoogleApiClient.isConnected(), "GoogleApiClient must be connected.");
      locale = (e)paramGoogleApiClient.a(paramc);
      if (locale == null) {
        break label56;
      }
    }
    for (;;)
    {
      o.a(bool1, "GoogleApiClient is not configured to use the Plus.API Api. Pass this into GoogleApiClient.Builder#addApi() to use this feature.");
      return locale;
      bool2 = false;
      break;
      label56:
      bool1 = false;
    }
  }
  
  public static final class PlusOptions
    implements Api.ApiOptions.Optional
  {
    final String alb;
    final Set<String> alc;
    
    private PlusOptions()
    {
      this.alb = null;
      this.alc = new HashSet();
    }
    
    private PlusOptions(Builder paramBuilder)
    {
      this.alb = paramBuilder.alb;
      this.alc = paramBuilder.alc;
    }
    
    public static Builder builder()
    {
      return new Builder();
    }
    
    public static final class Builder
    {
      String alb;
      final Set<String> alc = new HashSet();
      
      public Builder addActivityTypes(String... paramVarArgs)
      {
        o.b(paramVarArgs, "activityTypes may not be null.");
        for (int i = 0; i < paramVarArgs.length; i++) {
          this.alc.add(paramVarArgs[i]);
        }
        return this;
      }
      
      public Plus.PlusOptions build()
      {
        return new Plus.PlusOptions(this, null);
      }
      
      public Builder setServerClientId(String paramString)
      {
        this.alb = paramString;
        return this;
      }
    }
  }
  
  public static abstract class a<R extends Result>
    extends BaseImplementation.a<R, e>
  {
    public a()
    {
      super();
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.plus.Plus
 * JD-Core Version:    0.7.0.1
 */