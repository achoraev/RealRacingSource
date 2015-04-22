package com.ultra.sdk.utils;

import java.util.ArrayList;
import java.util.List;

public class ProvidersList
{
  public static final String AARKI = "Aarki";
  public static final String AD_COLONY = "AdColony";
  public static final String FLURRY = "Flurry";
  public static final String SPONSOR_PAY = "SponsorPay";
  public static final String SUPERSONIC_ADS = "SupersonicAds";
  public static final String TAPJOY = "Tapjoy";
  public static final String TRIAL_PAY = "TrialPay";
  public static final String VUNGLE = "Vungle";
  private static List<String> providers;
  
  public static List<String> getProvidersStaticList()
  {
    providers = new ArrayList();
    providers.add("SupersonicAds");
    providers.add("Tapjoy");
    providers.add("Aarki");
    providers.add("TrialPay");
    providers.add("SponsorPay");
    providers.add("AdColony");
    providers.add("Vungle");
    providers.add("Flurry");
    return providers;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ultra.sdk.utils.ProvidersList
 * JD-Core Version:    0.7.0.1
 */