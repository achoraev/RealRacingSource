package com.google.android.gms.appindexing;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.internal.hd;
import com.google.android.gms.internal.hz;

public final class AppIndex
{
  public static final Api<Api.ApiOptions.NoOptions> APP_INDEX_API = hd.BP;
  public static final AppIndexApi AppIndexApi = new hz();
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.appindexing.AppIndex
 * JD-Core Version:    0.7.0.1
 */