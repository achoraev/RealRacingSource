package com.fiksu.asotracking;

import android.content.Context;

class AndroidAdvertisingConfiguration
{
  static AndroidAdvertisingConfiguration create(Context paramContext)
  {
    try
    {
      Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
      GooglePlayAdvertisingConfiguration localGooglePlayAdvertisingConfiguration = new GooglePlayAdvertisingConfiguration(paramContext);
      return localGooglePlayAdvertisingConfiguration;
    }
    catch (ClassNotFoundException localClassNotFoundException) {}
    return new AndroidAdvertisingConfiguration();
  }
  
  String getAdvertisingIdentifier()
  {
    throw new IllegalStateException("Google Play library not present");
  }
  
  boolean isGooglePlayLibraryPresent()
  {
    return false;
  }
  
  boolean isGooglePlayServicesAvailable()
  {
    throw new IllegalStateException("Google Play library not present");
  }
  
  boolean limitAdTracking()
  {
    throw new IllegalStateException("Google Play library not present");
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.fiksu.asotracking.AndroidAdvertisingConfiguration
 * JD-Core Version:    0.7.0.1
 */