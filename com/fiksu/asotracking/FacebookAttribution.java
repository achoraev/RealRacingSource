package com.fiksu.asotracking;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

final class FacebookAttribution
{
  static final String ATTRIBUTION_ID_COLUMN_NAME = "aid";
  static final Uri ATTRIBUTION_ID_CONTENT_URI = Uri.parse("content://com.facebook.katana.provider.AttributionIdProvider");
  static final String[] PROJECTION = { "aid" };
  
  static String getAttributionId(ContentResolver paramContentResolver)
  {
    Cursor localCursor = null;
    try
    {
      localCursor = paramContentResolver.query(ATTRIBUTION_ID_CONTENT_URI, PROJECTION, null, null, null);
      if (localCursor != null)
      {
        boolean bool = localCursor.moveToFirst();
        if (bool) {}
      }
      else
      {
        if (localCursor != null) {
          localCursor.close();
        }
        localObject2 = null;
        return localObject2;
      }
      String str = localCursor.getString(localCursor.getColumnIndex("aid"));
      Object localObject2 = str;
      return localObject2;
    }
    catch (NullPointerException localNullPointerException)
    {
      Log.e("FiksuTracking", "Caught NullPointerException in FacebookAttribution.getAttributionId(). Returning null");
      return null;
    }
    catch (IllegalStateException localIllegalStateException)
    {
      Log.e("FiksuTracking", "Caught IllegalStateException in FacebookAttribution.getAttributionId(). Returning null");
      return null;
    }
    finally
    {
      if (localCursor != null) {
        localCursor.close();
      }
    }
  }
  
  static String getAttributionId(Context paramContext)
  {
    return getAttributionId(paramContext.getContentResolver());
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.fiksu.asotracking.FacebookAttribution
 * JD-Core Version:    0.7.0.1
 */