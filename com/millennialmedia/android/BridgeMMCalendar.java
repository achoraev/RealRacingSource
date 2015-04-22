package com.millennialmedia.android;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.provider.CalendarContract.Events;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.apache.http.impl.cookie.DateParseException;
import org.apache.http.impl.cookie.DateUtils;
import org.json.JSONException;
import org.json.JSONObject;

class BridgeMMCalendar
  extends MMJSObject
{
  private static final String ADD_EVENT = "addEvent";
  private static final String TAG = BridgeMMCalendar.class.getName();
  private static final String[] mraidCreateCalendarEventDateFormats = { "yyyy-MM-dd'T'HH:mmZZZ", "yyyy-MM-dd'T'HH:mm:ssZZZ" };
  private static final SimpleDateFormat rruleUntilDateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
  
  private String convertMraidDayToRRuleDay(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return null;
    case 1: 
      return "MO";
    case 2: 
      return "TU";
    case 3: 
      return "WE";
    case 4: 
      return "TH";
    case 5: 
      return "FR";
    case 6: 
      return "SA";
    }
    return "SU";
  }
  
  /* Error */
  private String convertRecurrence(JSONObject paramJSONObject)
  {
    // Byte code:
    //   0: new 67	java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial 68	java/lang/StringBuilder:<init>	()V
    //   7: astore_2
    //   8: aload_1
    //   9: ldc 70
    //   11: invokevirtual 76	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   14: astore 23
    //   16: aload_2
    //   17: ldc 78
    //   19: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   22: aload 23
    //   24: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   27: ldc 84
    //   29: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   32: pop
    //   33: getstatic 41	com/millennialmedia/android/BridgeMMCalendar:rruleUntilDateFormat	Ljava/text/SimpleDateFormat;
    //   36: aload_1
    //   37: ldc 86
    //   39: invokevirtual 76	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   42: getstatic 31	com/millennialmedia/android/BridgeMMCalendar:mraidCreateCalendarEventDateFormats	[Ljava/lang/String;
    //   45: invokestatic 92	org/apache/http/impl/cookie/DateUtils:parseDate	(Ljava/lang/String;[Ljava/lang/String;)Ljava/util/Date;
    //   48: invokevirtual 96	java/text/SimpleDateFormat:format	(Ljava/util/Date;)Ljava/lang/String;
    //   51: astore 21
    //   53: aload_2
    //   54: ldc 98
    //   56: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   59: aload 21
    //   61: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   64: ldc 84
    //   66: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   69: pop
    //   70: aload_1
    //   71: ldc 100
    //   73: invokevirtual 104	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   76: astore 15
    //   78: new 67	java/lang/StringBuilder
    //   81: dup
    //   82: invokespecial 68	java/lang/StringBuilder:<init>	()V
    //   85: astore 16
    //   87: iconst_0
    //   88: istore 17
    //   90: iload 17
    //   92: aload 15
    //   94: invokevirtual 110	org/json/JSONArray:length	()I
    //   97: if_icmpge +69 -> 166
    //   100: aload 16
    //   102: aload_0
    //   103: aload 15
    //   105: iload 17
    //   107: invokevirtual 114	org/json/JSONArray:getInt	(I)I
    //   110: invokespecial 116	com/millennialmedia/android/BridgeMMCalendar:convertMraidDayToRRuleDay	(I)Ljava/lang/String;
    //   113: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   116: ldc 118
    //   118: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   121: pop
    //   122: iinc 17 1
    //   125: goto -35 -> 90
    //   128: astore_3
    //   129: getstatic 23	com/millennialmedia/android/BridgeMMCalendar:TAG	Ljava/lang/String;
    //   132: ldc 120
    //   134: invokestatic 126	com/millennialmedia/android/MMLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   137: goto -104 -> 33
    //   140: astore 20
    //   142: getstatic 23	com/millennialmedia/android/BridgeMMCalendar:TAG	Ljava/lang/String;
    //   145: ldc 128
    //   147: invokestatic 131	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   150: goto -80 -> 70
    //   153: astore 4
    //   155: getstatic 23	com/millennialmedia/android/BridgeMMCalendar:TAG	Ljava/lang/String;
    //   158: ldc 133
    //   160: invokestatic 126	com/millennialmedia/android/MMLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   163: goto -93 -> 70
    //   166: aload_2
    //   167: ldc 135
    //   169: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   172: aload 16
    //   174: invokevirtual 138	java/lang/StringBuilder:append	(Ljava/lang/CharSequence;)Ljava/lang/StringBuilder;
    //   177: ldc 84
    //   179: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   182: pop
    //   183: aload_1
    //   184: ldc 140
    //   186: invokevirtual 76	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   189: ldc 142
    //   191: ldc 144
    //   193: invokevirtual 148	java/lang/String:replaceAll	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   196: ldc 150
    //   198: ldc 144
    //   200: invokevirtual 148	java/lang/String:replaceAll	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   203: astore 13
    //   205: aload_2
    //   206: ldc 152
    //   208: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   211: aload 13
    //   213: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   216: ldc 84
    //   218: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   221: pop
    //   222: aload_1
    //   223: ldc 154
    //   225: invokevirtual 76	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   228: ldc 142
    //   230: ldc 144
    //   232: invokevirtual 148	java/lang/String:replaceAll	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   235: ldc 150
    //   237: ldc 144
    //   239: invokevirtual 148	java/lang/String:replaceAll	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   242: astore 11
    //   244: aload_2
    //   245: ldc 156
    //   247: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   250: aload 11
    //   252: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   255: ldc 84
    //   257: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   260: pop
    //   261: aload_1
    //   262: ldc 158
    //   264: invokevirtual 76	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   267: astore 9
    //   269: aload_2
    //   270: ldc 160
    //   272: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   275: aload 9
    //   277: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   280: ldc 84
    //   282: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   285: pop
    //   286: aload_2
    //   287: invokevirtual 163	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   290: invokevirtual 166	java/lang/String:toUpperCase	()Ljava/lang/String;
    //   293: areturn
    //   294: astore 5
    //   296: getstatic 23	com/millennialmedia/android/BridgeMMCalendar:TAG	Ljava/lang/String;
    //   299: ldc 168
    //   301: invokestatic 126	com/millennialmedia/android/MMLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   304: goto -121 -> 183
    //   307: astore 6
    //   309: getstatic 23	com/millennialmedia/android/BridgeMMCalendar:TAG	Ljava/lang/String;
    //   312: ldc 170
    //   314: invokestatic 126	com/millennialmedia/android/MMLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   317: goto -95 -> 222
    //   320: astore 7
    //   322: getstatic 23	com/millennialmedia/android/BridgeMMCalendar:TAG	Ljava/lang/String;
    //   325: ldc 172
    //   327: invokestatic 126	com/millennialmedia/android/MMLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   330: goto -69 -> 261
    //   333: astore 8
    //   335: getstatic 23	com/millennialmedia/android/BridgeMMCalendar:TAG	Ljava/lang/String;
    //   338: ldc 174
    //   340: invokestatic 126	com/millennialmedia/android/MMLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   343: goto -57 -> 286
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	346	0	this	BridgeMMCalendar
    //   0	346	1	paramJSONObject	JSONObject
    //   7	280	2	localStringBuilder1	java.lang.StringBuilder
    //   128	1	3	localJSONException1	JSONException
    //   153	1	4	localJSONException2	JSONException
    //   294	1	5	localJSONException3	JSONException
    //   307	1	6	localJSONException4	JSONException
    //   320	1	7	localJSONException5	JSONException
    //   333	1	8	localJSONException6	JSONException
    //   267	9	9	str1	String
    //   242	9	11	str2	String
    //   203	9	13	str3	String
    //   76	28	15	localJSONArray	org.json.JSONArray
    //   85	88	16	localStringBuilder2	java.lang.StringBuilder
    //   88	35	17	i	int
    //   140	1	20	localDateParseException	DateParseException
    //   51	9	21	str4	String
    //   14	9	23	str5	String
    // Exception table:
    //   from	to	target	type
    //   8	33	128	org/json/JSONException
    //   33	70	140	org/apache/http/impl/cookie/DateParseException
    //   33	70	153	org/json/JSONException
    //   70	87	294	org/json/JSONException
    //   90	122	294	org/json/JSONException
    //   166	183	294	org/json/JSONException
    //   183	222	307	org/json/JSONException
    //   222	261	320	org/json/JSONException
    //   261	286	333	org/json/JSONException
  }
  
  @TargetApi(14)
  public MMJSResponse addEvent(Map<String, String> paramMap)
  {
    MMLog.d(TAG, "addEvent parameters: " + paramMap);
    label91:
    label103:
    label623:
    if (Build.VERSION.SDK_INT >= 14)
    {
      String str1;
      String str2;
      String str3;
      String str4;
      label115:
      String str5;
      label127:
      String str6;
      String str7;
      Date localDate1;
      Date localDate2;
      if ((paramMap != null) && (paramMap.get("parameters") != null))
      {
        try
        {
          JSONObject localJSONObject = new JSONObject((String)paramMap.get("parameters"));
          try
          {
            String str14 = localJSONObject.getString("description");
            str1 = str14;
          }
          catch (JSONException localJSONException1)
          {
            for (;;)
            {
              String str13;
              String str12;
              String str11;
              String str10;
              String str9;
              String str8;
              Date localDate4;
              Date localDate3;
              MMLog.e(TAG, "Unable to get calendar event description");
              str1 = null;
            }
          }
        }
        catch (JSONException localJSONException2)
        {
          MMLog.e(TAG, "Unable to parse calendar addEvent parameters");
          return MMJSResponse.responseWithError("Calendar Event Creation Failed.  Invalid parameters");
        }
        try
        {
          str13 = localJSONObject.getString("summary");
          str2 = str13;
        }
        catch (JSONException localJSONException3)
        {
          MMLog.d(TAG, "Unable to get calendar event description");
          str2 = null;
          break label91;
        }
        try
        {
          str12 = localJSONObject.getString("transparency");
          str3 = str12;
        }
        catch (JSONException localJSONException4)
        {
          MMLog.d(TAG, "Unable to get calendar event transparency");
          str3 = null;
          break label103;
        }
        try
        {
          str11 = localJSONObject.getString("reminder");
          str4 = str11;
        }
        catch (JSONException localJSONException5)
        {
          MMLog.d(TAG, "Unable to get calendar event reminder");
          str4 = null;
          break label115;
        }
        try
        {
          str10 = localJSONObject.getString("location");
          str5 = str10;
        }
        catch (JSONException localJSONException6)
        {
          MMLog.d(TAG, "Unable to get calendar event location");
          str5 = null;
          break label127;
        }
        try
        {
          str9 = localJSONObject.getString("status");
          str6 = str9;
        }
        catch (JSONException localJSONException7)
        {
          MMLog.d(TAG, "Unable to get calendar event status");
          str6 = null;
          break label139;
        }
        try
        {
          str8 = convertRecurrence(localJSONObject.getJSONObject("recurrence"));
          str7 = str8;
        }
        catch (JSONException localJSONException8)
        {
          MMLog.d(TAG, "Unable to get calendar event recurrence");
          str7 = null;
          break label155;
        }
        try
        {
          localDate4 = DateUtils.parseDate(localJSONObject.getString("start"), mraidCreateCalendarEventDateFormats);
          localDate1 = localDate4;
        }
        catch (DateParseException localDateParseException2)
        {
          MMLog.e(TAG, "Error parsing calendar event start date");
          localDate1 = null;
          break label173;
        }
        catch (JSONException localJSONException9)
        {
          MMLog.e(TAG, "Unable to get calendar event start date");
          localDate1 = null;
          break label173;
        }
        try
        {
          localDate3 = DateUtils.parseDate(localJSONObject.getString("end"), mraidCreateCalendarEventDateFormats);
          localDate2 = localDate3;
        }
        catch (DateParseException localDateParseException1)
        {
          MMLog.e(TAG, "Error parsing calendar event end date");
          localDate2 = null;
          break label191;
        }
        catch (JSONException localJSONException10)
        {
          MMLog.d(TAG, "Unable to get calendar event end date");
          localDate2 = null;
          break label191;
          Intent localIntent = new Intent("android.intent.action.INSERT").setData(CalendarContract.Events.CONTENT_URI);
          if (localDate1 == null) {
            break label499;
          }
          localIntent.putExtra("beginTime", localDate1.getTime());
          if (localDate2 == null) {
            break label518;
          }
          localIntent.putExtra("endTime", localDate2.getTime());
          if (str1 == null) {
            break label534;
          }
          localIntent.putExtra("title", str1);
          if (str2 == null) {
            break label549;
          }
          localIntent.putExtra("description", str2);
          if (str5 == null) {
            break label565;
          }
          localIntent.putExtra("eventLocation", str5);
          if (str7 == null) {
            break label581;
          }
          localIntent.putExtra("rrule", str7);
          if (str6 == null) {
            break label595;
          }
          MMLog.w(TAG, "Calendar addEvent does not support status");
          if (str3 == null) {
            break label609;
          }
          MMLog.w(TAG, "Calendar addEvent does not support transparency");
          if (str4 == null) {
            break label623;
          }
          MMLog.w(TAG, "Calendar addEvent does not support reminder");
          Context localContext = (Context)this.contextRef.get();
          if (localContext == null) {
            break label678;
          }
          Utils.IntentUtils.startActivity(localContext, localIntent);
          MMSDK.Event.intentStarted(localContext, "calendar", getAdImplId((String)paramMap.get("PROPERTY_EXPANDING")));
          return MMJSResponse.responseWithSuccess("Calendar Event Created");
        }
        MMLog.d(TAG, String.format("Creating calendar event: title: %s, location: %s, start: %s, end: %s, status: %s, summary: %s, rrule: %s", new Object[] { str1, str5, localDate1, localDate2, str6, str2, str7 }));
        if ((str1 == null) || (localDate1 == null))
        {
          MMLog.e(TAG, "Description and start must be provided to create calendar event.");
          return MMJSResponse.responseWithError("Calendar Event Creation Failed.  Minimum parameters not provided");
        }
      }
      label499:
      return null;
    }
    label139:
    label155:
    label173:
    label191:
    label581:
    label595:
    label609:
    return MMJSResponse.responseWithError("Not supported");
  }
  
  MMJSResponse executeCommand(String paramString, Map<String, String> paramMap)
  {
    boolean bool = "addEvent".equals(paramString);
    MMJSResponse localMMJSResponse = null;
    if (bool) {
      localMMJSResponse = addEvent(paramMap);
    }
    return localMMJSResponse;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.BridgeMMCalendar
 * JD-Core Version:    0.7.0.1
 */