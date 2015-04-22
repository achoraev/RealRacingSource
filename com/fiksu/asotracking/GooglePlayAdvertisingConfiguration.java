package com.fiksu.asotracking;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;

class GooglePlayAdvertisingConfiguration
  extends AndroidAdvertisingConfiguration
{
  private String advertisingIdentifier = null;
  private boolean googlePlayServicesAvailable = false;
  private boolean limitAdTracking = false;
  
  public GooglePlayAdvertisingConfiguration(Context paramContext)
  {
    try
    {
      AdvertisingIdClient.Info localInfo = AdvertisingIdClient.getAdvertisingIdInfo(paramContext);
      this.advertisingIdentifier = localInfo.getId();
      this.limitAdTracking = localInfo.isLimitAdTrackingEnabled();
      this.googlePlayServicesAvailable = true;
      return;
    }
    catch (IOException localIOException)
    {
      Log.d("FiksuTracking", "Error connecting to Google Services: " + localIOException.getMessage());
      return;
    }
    catch (IllegalStateException localIllegalStateException)
    {
      Log.d("FiksuTracking", "Illegal state connecting to Google Services: " + localIllegalStateException.getMessage());
      return;
    }
    catch (GooglePlayServicesRepairableException localGooglePlayServicesRepairableException)
    {
      Log.d("FiksuTracking", "Repairable problem connecting to Google Services: " + localGooglePlayServicesRepairableException.getMessage());
      return;
    }
    catch (GooglePlayServicesNotAvailableException localGooglePlayServicesNotAvailableException)
    {
      Log.d("FiksuTracking", "Google Services not available: " + localGooglePlayServicesNotAvailableException.getMessage());
    }
  }
  
  String getAdvertisingIdentifier()
  {
    if (!this.googlePlayServicesAvailable) {
      throw new IllegalStateException("Google Play Services not present");
    }
    return this.advertisingIdentifier;
  }
  
  boolean isGooglePlayLibraryPresent()
  {
    return true;
  }
  
  boolean isGooglePlayServicesAvailable()
  {
    return this.googlePlayServicesAvailable;
  }
  
  boolean limitAdTracking()
  {
    if (!this.googlePlayServicesAvailable) {
      throw new IllegalStateException("Google Play Services not present");
    }
    return this.limitAdTracking;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.fiksu.asotracking.GooglePlayAdvertisingConfiguration
 * JD-Core Version:    0.7.0.1
 */