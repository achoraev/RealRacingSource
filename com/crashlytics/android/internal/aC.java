package com.crashlytics.android.internal;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

final class aC
  implements aB
{
  public final HttpURLConnection a(URL paramURL)
    throws IOException
  {
    return (HttpURLConnection)paramURL.openConnection();
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.crashlytics.android.internal.aC
 * JD-Core Version:    0.7.0.1
 */