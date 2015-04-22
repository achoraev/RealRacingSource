package com.google.ads.interactivemedia.v3.api;

import android.content.Context;
import android.net.Uri;
import com.google.ads.interactivemedia.v3.b.a;
import com.google.ads.interactivemedia.v3.b.e;
import com.google.ads.interactivemedia.v3.b.i;
import com.google.ads.interactivemedia.v3.b.j;
import com.google.ads.interactivemedia.v3.b.k;
import com.google.ads.interactivemedia.v3.b.p;

public class ImaSdkFactory
{
  private static ImaSdkFactory instance;
  
  private AdsLoader createAdsLoader(Context paramContext, Uri paramUri, ImaSdkSettings paramImaSdkSettings)
  {
    return new e(paramContext, paramUri, paramImaSdkSettings);
  }
  
  public static ImaSdkFactory getInstance()
  {
    if (instance == null) {
      instance = new ImaSdkFactory();
    }
    return instance;
  }
  
  public AdDisplayContainer createAdDisplayContainer()
  {
    return new a();
  }
  
  public AdsLoader createAdsLoader(Context paramContext)
  {
    return createAdsLoader(paramContext, createImaSdkSettings());
  }
  
  public AdsLoader createAdsLoader(Context paramContext, ImaSdkSettings paramImaSdkSettings)
  {
    return new e(paramContext, p.a, paramImaSdkSettings);
  }
  
  public AdsRenderingSettings createAdsRenderingSettings()
  {
    return new i();
  }
  
  public AdsRequest createAdsRequest()
  {
    return new j();
  }
  
  public CompanionAdSlot createCompanionAdSlot()
  {
    return new k();
  }
  
  public ImaSdkSettings createImaSdkSettings()
  {
    return new ImaSdkSettings();
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.api.ImaSdkFactory
 * JD-Core Version:    0.7.0.1
 */