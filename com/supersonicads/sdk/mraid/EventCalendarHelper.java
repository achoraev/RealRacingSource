package com.supersonicads.sdk.mraid;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.provider.CalendarContract.Calendars;
import android.provider.CalendarContract.Events;
import android.provider.CalendarContract.Reminders;
import com.supersonicads.sdk.data.SSAEventCalendar;
import java.util.Calendar;
import java.util.TimeZone;

public class EventCalendarHelper
{
  private void addEventReminders(Context paramContext, long paramLong)
  {
    ContentResolver localContentResolver = paramContext.getContentResolver();
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("event_id", Long.valueOf(paramLong));
    localContentValues.put("minutes", Integer.valueOf(3));
    localContentValues.put("method", Integer.valueOf(1));
    localContentResolver.insert(getCalendarRemaindersUri(), localContentValues);
  }
  
  private static void addReminder(String paramString) {}
  
  public static void createCalendarEvent(Context paramContext, String paramString)
  {
    createEvent(paramContext, new SSAEventCalendar(paramString));
  }
  
  private static void createEvent(Context paramContext, SSAEventCalendar paramSSAEventCalendar)
  {
    getNewCalendarEventId(paramContext);
    Uri localUri = getCalendarEventsUri();
    Calendar localCalendar = Calendar.getInstance();
    Calendar.getInstance().set(2014, 2, 19, 12, 30, 55);
    Calendar.getInstance().set(2014, 3, 19, 12, 15, 20);
    TimeZone localTimeZone = TimeZone.getDefault();
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("calendar_id", Integer.valueOf(1));
    localContentValues.put("eventStatus", Integer.valueOf(1));
    localContentValues.put("allDay", Integer.valueOf(0));
    localContentValues.put("hasAlarm", Integer.valueOf(0));
    localContentValues.put("description", paramSSAEventCalendar.getDescription());
    localContentValues.put("dtstart", Long.valueOf(localCalendar.getTimeInMillis()));
    localContentValues.put("dtend", Long.valueOf(600000L + localCalendar.getTimeInMillis()));
    localContentValues.put("eventTimezone", localTimeZone.getID());
    localContentValues.put("rrule", "FREQ=WEEKLY;INTERVAL=1;UNTIL=20140231");
    Integer.valueOf(paramContext.getContentResolver().insert(localUri, localContentValues).getLastPathSegment());
  }
  
  private int deleteEvent(Context paramContext, long paramLong)
  {
    ContentResolver localContentResolver = paramContext.getContentResolver();
    new ContentValues();
    return localContentResolver.delete(ContentUris.withAppendedId(CalendarContract.Events.CONTENT_URI, paramLong), null, null);
  }
  
  private static Uri getCalendarCalendarsUri()
  {
    if (Build.VERSION.SDK_INT <= 14) {
      return Uri.parse("content://com.android.calendar/calendars");
    }
    return CalendarContract.Calendars.CONTENT_URI;
  }
  
  private static Uri getCalendarEventsUri()
  {
    if (Build.VERSION.SDK_INT <= 14) {
      return Uri.parse("content://com.android.calendar/events");
    }
    return CalendarContract.Events.CONTENT_URI;
  }
  
  private static Uri getCalendarRemaindersUri()
  {
    if (Build.VERSION.SDK_INT <= 14) {
      return Uri.parse("content://com.android.calendar/reminders");
    }
    return CalendarContract.Reminders.CONTENT_URI;
  }
  
  private static int getNewCalendarEventId(Context paramContext)
  {
    ContentResolver localContentResolver = paramContext.getContentResolver();
    String[] arrayOfString1;
    Cursor localCursor;
    int[] arrayOfInt;
    String[] arrayOfString2;
    if (Build.VERSION.SDK_INT <= 14)
    {
      arrayOfString1 = new String[2];
      arrayOfString1[0] = "_id";
      arrayOfString1[1] = "displayName";
      localCursor = localContentResolver.query(getCalendarCalendarsUri(), arrayOfString1, null, null, null);
      boolean bool = localCursor.moveToFirst();
      arrayOfInt = null;
      if (bool)
      {
        arrayOfString2 = new String[localCursor.getCount()];
        arrayOfInt = new int[localCursor.getCount()];
      }
    }
    for (int i = 0;; i++)
    {
      if (i >= arrayOfString2.length)
      {
        return arrayOfInt[0];
        arrayOfString1 = new String[] { "_id", "calendar_displayName" };
        break;
      }
      arrayOfInt[i] = localCursor.getInt(0);
      localCursor.moveToNext();
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.supersonicads.sdk.mraid.EventCalendarHelper
 * JD-Core Version:    0.7.0.1
 */