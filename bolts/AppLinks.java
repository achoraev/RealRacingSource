package bolts;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public final class AppLinks
{
  static final String KEY_NAME_APPLINK_DATA = "al_applink_data";
  static final String KEY_NAME_EXTRAS = "extras";
  static final String KEY_NAME_TARGET = "target_url";
  
  public static Bundle getAppLinkData(Intent paramIntent)
  {
    return paramIntent.getBundleExtra("al_applink_data");
  }
  
  public static Bundle getAppLinkExtras(Intent paramIntent)
  {
    Bundle localBundle = getAppLinkData(paramIntent);
    if (localBundle == null) {
      return null;
    }
    return localBundle.getBundle("extras");
  }
  
  public static Uri getTargetUrl(Intent paramIntent)
  {
    Bundle localBundle = getAppLinkData(paramIntent);
    if (localBundle != null)
    {
      String str = localBundle.getString("target_url");
      if (str != null) {
        return Uri.parse(str);
      }
    }
    return paramIntent.getData();
  }
  
  public static Uri getTargetUrlFromInboundIntent(Context paramContext, Intent paramIntent)
  {
    Bundle localBundle = getAppLinkData(paramIntent);
    Uri localUri = null;
    if (localBundle != null)
    {
      String str = localBundle.getString("target_url");
      localUri = null;
      if (str != null)
      {
        MeasurementEvent.sendBroadcastEvent(paramContext, "al_nav_in", paramIntent, null);
        localUri = Uri.parse(str);
      }
    }
    return localUri;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     bolts.AppLinks
 * JD-Core Version:    0.7.0.1
 */