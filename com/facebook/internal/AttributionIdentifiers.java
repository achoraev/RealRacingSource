package com.facebook.internal;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Looper;
import android.util.Log;
import com.facebook.FacebookException;
import java.lang.reflect.Method;

public class AttributionIdentifiers
{
  private static final String ANDROID_ID_COLUMN_NAME = "androidid";
  private static final String ATTRIBUTION_ID_COLUMN_NAME = "aid";
  private static final Uri ATTRIBUTION_ID_CONTENT_URI = Uri.parse("content://com.facebook.katana.provider.AttributionIdProvider");
  private static final int CONNECTION_RESULT_SUCCESS = 0;
  private static final long IDENTIFIER_REFRESH_INTERVAL_MILLIS = 3600000L;
  private static final String LIMIT_TRACKING_COLUMN_NAME = "limit_tracking";
  private static final String TAG = AttributionIdentifiers.class.getCanonicalName();
  private static AttributionIdentifiers recentlyFetchedIdentifiers;
  private String androidAdvertiserId;
  private String attributionId;
  private long fetchTime;
  private boolean limitTracking;
  
  private static AttributionIdentifiers getAndroidId(Context paramContext)
  {
    AttributionIdentifiers localAttributionIdentifiers = new AttributionIdentifiers();
    Object localObject2;
    Method localMethod3;
    Method localMethod4;
    do
    {
      do
      {
        Method localMethod2;
        do
        {
          Object localObject1;
          do
          {
            Method localMethod1;
            do
            {
              try
              {
                if (Looper.myLooper() == Looper.getMainLooper()) {
                  throw new FacebookException("getAndroidId cannot be called on the main thread.");
                }
              }
              catch (Exception localException)
              {
                Utility.logd("android_id", localException);
                return localAttributionIdentifiers;
              }
              localMethod1 = Utility.getMethodQuietly("com.google.android.gms.common.GooglePlayServicesUtil", "isGooglePlayServicesAvailable", new Class[] { Context.class });
            } while (localMethod1 == null);
            localObject1 = Utility.invokeMethodQuietly(null, localMethod1, new Object[] { paramContext });
          } while ((!(localObject1 instanceof Integer)) || (((Integer)localObject1).intValue() != 0));
          localMethod2 = Utility.getMethodQuietly("com.google.android.gms.ads.identifier.AdvertisingIdClient", "getAdvertisingIdInfo", new Class[] { Context.class });
        } while (localMethod2 == null);
        localObject2 = Utility.invokeMethodQuietly(null, localMethod2, new Object[] { paramContext });
      } while (localObject2 == null);
      localMethod3 = Utility.getMethodQuietly(localObject2.getClass(), "getId", new Class[0]);
      localMethod4 = Utility.getMethodQuietly(localObject2.getClass(), "isLimitAdTrackingEnabled", new Class[0]);
    } while ((localMethod3 == null) || (localMethod4 == null));
    localAttributionIdentifiers.androidAdvertiserId = ((String)Utility.invokeMethodQuietly(localObject2, localMethod3, new Object[0]));
    localAttributionIdentifiers.limitTracking = ((Boolean)Utility.invokeMethodQuietly(localObject2, localMethod4, new Object[0])).booleanValue();
    return localAttributionIdentifiers;
  }
  
  public static AttributionIdentifiers getAttributionIdentifiers(Context paramContext)
  {
    if ((recentlyFetchedIdentifiers != null) && (System.currentTimeMillis() - recentlyFetchedIdentifiers.fetchTime < 3600000L)) {
      return recentlyFetchedIdentifiers;
    }
    AttributionIdentifiers localAttributionIdentifiers = getAndroidId(paramContext);
    try
    {
      String[] arrayOfString = { "aid", "androidid", "limit_tracking" };
      Cursor localCursor = paramContext.getContentResolver().query(ATTRIBUTION_ID_CONTENT_URI, arrayOfString, null, null, null);
      if ((localCursor != null) && (localCursor.moveToFirst()))
      {
        int i = localCursor.getColumnIndex("aid");
        int j = localCursor.getColumnIndex("androidid");
        int k = localCursor.getColumnIndex("limit_tracking");
        localAttributionIdentifiers.attributionId = localCursor.getString(i);
        if ((j > 0) && (k > 0) && (localAttributionIdentifiers.getAndroidAdvertiserId() == null))
        {
          localAttributionIdentifiers.androidAdvertiserId = localCursor.getString(j);
          localAttributionIdentifiers.limitTracking = Boolean.parseBoolean(localCursor.getString(k));
        }
        localCursor.close();
        localAttributionIdentifiers.fetchTime = System.currentTimeMillis();
        recentlyFetchedIdentifiers = localAttributionIdentifiers;
        return localAttributionIdentifiers;
      }
    }
    catch (Exception localException)
    {
      Log.d(TAG, "Caught unexpected exception in getAttributionId(): " + localException.toString());
      return null;
    }
    return null;
  }
  
  public String getAndroidAdvertiserId()
  {
    return this.androidAdvertiserId;
  }
  
  public String getAttributionId()
  {
    return this.attributionId;
  }
  
  public boolean isTrackingLimited()
  {
    return this.limitTracking;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.facebook.internal.AttributionIdentifiers
 * JD-Core Version:    0.7.0.1
 */