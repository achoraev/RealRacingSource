package com.jirbo.adcolony;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaScannerConnection.OnScanCompletedListener;
import android.net.Uri;
import android.os.Handler;
import android.webkit.WebView;
import android.widget.Toast;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

class ADCMRAIDHandler
{
  AdColonyAd ad;
  Activity ctx;
  WebView end_card_web_view;
  Handler h;
  String play_order_index_json;
  Runnable r;
  ADCVideo video;
  
  public ADCMRAIDHandler(ADCVideo paramADCVideo, WebView paramWebView, Activity paramActivity)
  {
    this.end_card_web_view = paramWebView;
    this.ctx = paramActivity;
    this.video = paramADCVideo;
    this.h = new Handler();
    this.r = new Runnable()
    {
      public void run()
      {
        ADC.mraid_block = false;
      }
    };
  }
  
  void MRAIDCommandAutoPlay(HashMap paramHashMap)
  {
    ADCLog.dev.print("ADC [info] MRAIDCommandCheckAutoPlay called with parameters: ").println(paramHashMap);
  }
  
  void MRAIDCommandCheckAppPresence(HashMap paramHashMap)
  {
    ADCLog.dev.print("ADC [info] MRAIDCommandCheckAppPresence called with parameters: ").println(paramHashMap);
    String str = decode((String)paramHashMap.get("handle"));
    boolean bool = ADCUtil.application_exists(str);
    execute_javascript("adc_bridge.fireAppPresenceEvent('" + str + "'," + bool + ")");
  }
  
  void MRAIDCommandClose()
  {
    ADCLog.dev.println("ADC [info] MRAIDCommandClose called");
    this.ctx.finish();
    ADC.end_card_finished_handler.notify_continuation(this.video.ad);
  }
  
  void MRAIDCommandCreateCalendarEvent(HashMap paramHashMap)
  {
    ADCLog.dev.print("ADC [info] MRAIDCommandCreateCalendarEvent called with parameters: ").println(paramHashMap);
    ADC.mraid_block = true;
    this.h.postDelayed(this.r, 1000L);
    ADC.track_ad_event("html5_interaction", this.play_order_index_json, this.video.ad);
    Date localDate1 = null;
    SimpleDateFormat localSimpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd'T'hh:mmZ");
    SimpleDateFormat localSimpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ");
    SimpleDateFormat localSimpleDateFormat3 = new SimpleDateFormat("yyyy-MM-dd");
    String str1 = decode((String)paramHashMap.get("description"));
    decode((String)paramHashMap.get("location"));
    String str2 = decode((String)paramHashMap.get("start"));
    String str3 = decode((String)paramHashMap.get("end"));
    String str4 = decode((String)paramHashMap.get("summary"));
    String str5 = decode((String)paramHashMap.get("recurrence"));
    String str6 = "";
    String str7 = "";
    long l1 = 0L;
    HashMap localHashMap = new HashMap();
    String str8 = str5.replace("\"", "").replace("{", "").replace("}", "");
    if (!str8.equals(""))
    {
      for (String str9 : str8.split(",")) {
        localHashMap.put(str9.split(":")[0], str9.split(":")[1]);
      }
      str6 = decode((String)localHashMap.get("expires"));
      str7 = decode((String)localHashMap.get("frequency")).toUpperCase();
      ADCLog.dev.print("Calendar Recurrence - ").println(str8);
      ADCLog.dev.print("Calendar Recurrence - frequency = ").println(str7);
      ADCLog.dev.print("Calendar Recurrence - expires =  ").println(str6);
    }
    if (str4.equals("")) {
      str4 = decode((String)paramHashMap.get("description"));
    }
    try
    {
      localDate1 = localSimpleDateFormat1.parse(str2);
      Date localDate5 = localSimpleDateFormat1.parse(str3);
      localObject = localDate5;
    }
    catch (Exception localException1)
    {
      for (;;)
      {
        Object localObject = null;
      }
    }
    if (localDate1 == null) {}
    for (;;)
    {
      try
      {
        localDate1 = localSimpleDateFormat2.parse(str2);
        Date localDate4 = localSimpleDateFormat2.parse(str3);
        localObject = localDate4;
      }
      catch (Exception localException4)
      {
        Date localDate3;
        Date localDate2;
        long l2;
        long l3;
        long l4;
        Intent localIntent;
        label773:
        continue;
      }
      try
      {
        localDate3 = localSimpleDateFormat3.parse(str6);
        localDate2 = localDate3;
      }
      catch (Exception localException2)
      {
        localDate2 = null;
      }
    }
    if (localDate1 == null)
    {
      Toast.makeText(this.ctx, "Unable to create Calendar Event.", 0).show();
      return;
    }
    l2 = localDate1.getTime();
    l3 = ((Date)localObject).getTime();
    l4 = 0L;
    if (localDate2 != null) {
      l4 = (localDate2.getTime() - localDate1.getTime()) / 1000L;
    }
    if (str7.equals("DAILY"))
    {
      l1 = 1L + l4 / 86400L;
      if (str8.equals("")) {
        break label773;
      }
      localIntent = new Intent("android.intent.action.EDIT").setType("vnd.android.cursor.item/event").putExtra("title", str4).putExtra("description", str1).putExtra("beginTime", l2).putExtra("endTime", l3).putExtra("rrule", "FREQ=" + str7 + ";" + "COUNT=" + l1);
      ADCLog.dev.print("Calendar Recurrence - count = ").println(l1);
    }
    for (;;)
    {
      try
      {
        this.ctx.startActivity(localIntent);
        return;
      }
      catch (Exception localException3)
      {
        Toast.makeText(this.ctx, "Unable to create Calendar Event.", 0).show();
        return;
      }
      if (str7.equals("WEEKLY"))
      {
        l1 = 1L + l4 / 604800L;
        break;
      }
      if (str7.equals("MONTHLY"))
      {
        l1 = 1L + l4 / 2629800L;
        break;
      }
      if (!str7.equals("YEARLY")) {
        break;
      }
      l1 = 1L + l4 / 31557600L;
      break;
      localIntent = new Intent("android.intent.action.EDIT").setType("vnd.android.cursor.item/event").putExtra("title", str4).putExtra("description", str1).putExtra("beginTime", l2).putExtra("endTime", l3);
    }
  }
  
  void MRAIDCommandExpand(String paramString)
  {
    ADCLog.dev.print("ADC [info] MRAIDCommandExpand called with url: ").println(paramString);
    execute_javascript("adc_bridge.fireChangeEvent({state:'expanded'});");
  }
  
  void MRAIDCommandLaunchApp(HashMap paramHashMap)
  {
    ADCLog.dev.print("ADC [info] MRAIDCommandLaunchApp called with parameters: ").println(paramHashMap);
    ADC.mraid_block = true;
    this.h.postDelayed(this.r, 1000L);
    String str = decode((String)paramHashMap.get("handle"));
    ADC.track_ad_event("html5_interaction", this.play_order_index_json, this.video.ad);
    try
    {
      Intent localIntent = this.ctx.getPackageManager().getLaunchIntentForPackage(str);
      this.ctx.startActivity(localIntent);
      return;
    }
    catch (Exception localException)
    {
      Toast.makeText(this.ctx, "Failed to launch external application.", 0).show();
    }
  }
  
  void MRAIDCommandMail(HashMap paramHashMap)
  {
    ADCLog.dev.print("ADC [info] MRAIDCommandMail called with parameters: ").println(paramHashMap);
    ADC.mraid_block = true;
    this.h.postDelayed(this.r, 1000L);
    String str1 = decode((String)paramHashMap.get("subject"));
    String str2 = decode((String)paramHashMap.get("body"));
    String str3 = decode((String)paramHashMap.get("to"));
    ADC.track_ad_event("html5_interaction", this.play_order_index_json, this.video.ad);
    try
    {
      Intent localIntent = new Intent("android.intent.action.SEND");
      localIntent.setType("plain/text");
      localIntent.putExtra("android.intent.extra.SUBJECT", str1).putExtra("android.intent.extra.TEXT", str2).putExtra("android.intent.extra.EMAIL", new String[] { str3 });
      this.ctx.startActivity(localIntent);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      Toast.makeText(this.ctx, "Unable to launch email client.", 0).show();
    }
  }
  
  void MRAIDCommandOpen(String paramString)
  {
    ADCLog.dev.print("ADC [info] MRAIDCommandOpen called with url: ").println(paramString);
    ADC.mraid_block = true;
    this.h.postDelayed(this.r, 1000L);
    String str1 = decode(paramString);
    if (str1.startsWith("adcvideo"))
    {
      String str4 = str1.replace("adcvideo", "http");
      this.video.playVideo(str4);
      return;
    }
    if (paramString.contains("youtube")) {
      try
      {
        String str3 = str1.substring(2 + str1.indexOf('v'));
        Intent localIntent4 = new Intent("android.intent.action.VIEW", Uri.parse("vnd.youtube:" + str3));
        this.ctx.startActivity(localIntent4);
        return;
      }
      catch (Exception localException)
      {
        String str2 = decode(paramString);
        if (str2.contains("safari")) {
          str2 = str2.replace("safari", "http");
        }
        Intent localIntent3 = new Intent("android.intent.action.VIEW", Uri.parse(str2));
        this.ctx.startActivity(localIntent3);
        return;
      }
    }
    if (str1.startsWith("browser"))
    {
      ADC.track_ad_event("html5_interaction", this.video.ad);
      Intent localIntent2 = new Intent("android.intent.action.VIEW", Uri.parse(str1.replace("browser", "http")));
      this.ctx.startActivity(localIntent2);
      return;
    }
    ADC.track_ad_event("html5_interaction", this.play_order_index_json, this.video.ad);
    AdColonyBrowser.url = str1;
    Intent localIntent1 = new Intent(this.ctx, AdColonyBrowser.class);
    this.ctx.startActivity(localIntent1);
  }
  
  void MRAIDCommandOpenStore(String paramString)
  {
    ADCLog.dev.print("ADC [info] MRAIDCommandOpenStore called with item: ").println(paramString);
    ADC.mraid_block = true;
    this.h.postDelayed(this.r, 1000L);
    ADC.track_ad_event("html5_interaction", this.play_order_index_json, this.video.ad);
    String str = decode(paramString);
    try
    {
      Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse(str));
      this.ctx.startActivity(localIntent);
      return;
    }
    catch (Exception localException)
    {
      Toast.makeText(this.ctx, "Unable to open store.", 0).show();
    }
  }
  
  void MRAIDCommandSMS(HashMap paramHashMap)
  {
    ADCLog.dev.print("ADC [info] MRAIDCommandSMS called with parameters: ").println(paramHashMap);
    ADC.mraid_block = true;
    this.h.postDelayed(this.r, 1000L);
    String str1 = decode((String)paramHashMap.get("to"));
    String str2 = decode((String)paramHashMap.get("body"));
    ADC.track_ad_event("html5_interaction", this.play_order_index_json, this.video.ad);
    try
    {
      Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("sms:" + str1)).putExtra("sms_body", str2);
      this.ctx.startActivity(localIntent);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      Toast.makeText(this.ctx, "Failed to create sms.", 0).show();
    }
  }
  
  void MRAIDCommandSendADCEvent(String paramString)
  {
    ADCLog.dev.print("ADC [info] MRAIDCommandSendADCEvent called with type: ").println(paramString);
    ADC.track_ad_event(paramString, this.video.ad);
  }
  
  void MRAIDCommandSendCustomADCEvent(HashMap paramHashMap)
  {
    ADCLog.dev.print("ADC [info] MRAIDCommandSendCustomADCEvent called with parameters: ").println(paramHashMap);
    String str = decode((String)paramHashMap.get("event_type"));
    ADC.track_ad_event("custom_event", "{\"event_type\":\"" + str + "\",\"ad_slot\":" + this.ad.zone_info.state.session_play_count + "}", this.video.ad);
  }
  
  void MRAIDCommandSocialPost(HashMap paramHashMap)
  {
    ADC.mraid_block = true;
    this.h.postDelayed(this.r, 1000L);
    String str1 = decode((String)paramHashMap.get("text"));
    String str2 = decode((String)paramHashMap.get("url"));
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setType("text/plain");
    localIntent.putExtra("android.intent.extra.TEXT", str1 + " " + str2);
    this.ctx.startActivity(Intent.createChooser(localIntent, "Share this post using..."));
  }
  
  /* Error */
  void MRAIDCommandTakeScreenshot()
  {
    // Byte code:
    //   0: iconst_1
    //   1: putstatic 139	com/jirbo/adcolony/ADC:mraid_block	Z
    //   4: aload_0
    //   5: getfield 34	com/jirbo/adcolony/ADCMRAIDHandler:h	Landroid/os/Handler;
    //   8: aload_0
    //   9: getfield 41	com/jirbo/adcolony/ADCMRAIDHandler:r	Ljava/lang/Runnable;
    //   12: ldc2_w 140
    //   15: invokevirtual 145	android/os/Handler:postDelayed	(Ljava/lang/Runnable;J)Z
    //   18: pop
    //   19: ldc 147
    //   21: aload_0
    //   22: getfield 149	com/jirbo/adcolony/ADCMRAIDHandler:play_order_index_json	Ljava/lang/String;
    //   25: aload_0
    //   26: getfield 29	com/jirbo/adcolony/ADCMRAIDHandler:video	Lcom/jirbo/adcolony/ADCVideo;
    //   29: getfield 124	com/jirbo/adcolony/ADCVideo:ad	Lcom/jirbo/adcolony/AdColonyAd;
    //   32: invokestatic 153	com/jirbo/adcolony/ADC:track_ad_event	(Ljava/lang/String;Ljava/lang/String;Lcom/jirbo/adcolony/AdColonyAd;)V
    //   35: new 84	java/lang/StringBuilder
    //   38: dup
    //   39: invokespecial 85	java/lang/StringBuilder:<init>	()V
    //   42: invokestatic 475	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   45: invokevirtual 478	java/io/File:toString	()Ljava/lang/String;
    //   48: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   51: ldc_w 480
    //   54: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   57: ldc_w 482
    //   60: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   63: invokestatic 487	java/lang/System:currentTimeMillis	()J
    //   66: invokevirtual 279	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   69: ldc_w 489
    //   72: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   75: invokevirtual 102	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   78: astore_2
    //   79: aload_0
    //   80: getfield 25	com/jirbo/adcolony/ADCMRAIDHandler:end_card_web_view	Landroid/webkit/WebView;
    //   83: invokevirtual 495	android/webkit/WebView:getRootView	()Landroid/view/View;
    //   86: astore_3
    //   87: aload_3
    //   88: iconst_1
    //   89: invokevirtual 501	android/view/View:setDrawingCacheEnabled	(Z)V
    //   92: aload_3
    //   93: invokevirtual 505	android/view/View:getDrawingCache	()Landroid/graphics/Bitmap;
    //   96: invokestatic 511	android/graphics/Bitmap:createBitmap	(Landroid/graphics/Bitmap;)Landroid/graphics/Bitmap;
    //   99: astore 4
    //   101: aload_3
    //   102: iconst_0
    //   103: invokevirtual 501	android/view/View:setDrawingCacheEnabled	(Z)V
    //   106: new 477	java/io/File
    //   109: dup
    //   110: new 84	java/lang/StringBuilder
    //   113: dup
    //   114: invokespecial 85	java/lang/StringBuilder:<init>	()V
    //   117: invokestatic 475	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   120: invokevirtual 478	java/io/File:toString	()Ljava/lang/String;
    //   123: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   126: ldc_w 513
    //   129: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   132: invokevirtual 102	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   135: invokespecial 514	java/io/File:<init>	(Ljava/lang/String;)V
    //   138: astore 5
    //   140: new 477	java/io/File
    //   143: dup
    //   144: new 84	java/lang/StringBuilder
    //   147: dup
    //   148: invokespecial 85	java/lang/StringBuilder:<init>	()V
    //   151: invokestatic 475	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   154: invokevirtual 478	java/io/File:toString	()Ljava/lang/String;
    //   157: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   160: ldc_w 516
    //   163: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   166: invokevirtual 102	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   169: invokespecial 514	java/io/File:<init>	(Ljava/lang/String;)V
    //   172: astore 6
    //   174: aload 5
    //   176: invokevirtual 520	java/io/File:mkdir	()Z
    //   179: pop
    //   180: aload 6
    //   182: invokevirtual 520	java/io/File:mkdir	()Z
    //   185: pop
    //   186: new 477	java/io/File
    //   189: dup
    //   190: aload_2
    //   191: invokespecial 514	java/io/File:<init>	(Ljava/lang/String;)V
    //   194: astore 8
    //   196: new 522	java/io/FileOutputStream
    //   199: dup
    //   200: aload 8
    //   202: invokespecial 525	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   205: astore 9
    //   207: aload 4
    //   209: getstatic 531	android/graphics/Bitmap$CompressFormat:JPEG	Landroid/graphics/Bitmap$CompressFormat;
    //   212: bipush 90
    //   214: aload 9
    //   216: invokevirtual 535	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   219: pop
    //   220: aload 9
    //   222: invokevirtual 540	java/io/OutputStream:flush	()V
    //   225: aload 9
    //   227: invokevirtual 543	java/io/OutputStream:close	()V
    //   230: aload_0
    //   231: getfield 27	com/jirbo/adcolony/ADCMRAIDHandler:ctx	Landroid/app/Activity;
    //   234: iconst_1
    //   235: anewarray 72	java/lang/String
    //   238: dup
    //   239: iconst_0
    //   240: aload_2
    //   241: aastore
    //   242: aconst_null
    //   243: new 545	com/jirbo/adcolony/ADCMRAIDHandler$2
    //   246: dup
    //   247: aload_0
    //   248: invokespecial 546	com/jirbo/adcolony/ADCMRAIDHandler$2:<init>	(Lcom/jirbo/adcolony/ADCMRAIDHandler;)V
    //   251: invokestatic 552	android/media/MediaScannerConnection:scanFile	(Landroid/content/Context;[Ljava/lang/String;[Ljava/lang/String;Landroid/media/MediaScannerConnection$OnScanCompletedListener;)V
    //   254: return
    //   255: astore 16
    //   257: aload_0
    //   258: getfield 27	com/jirbo/adcolony/ADCMRAIDHandler:ctx	Landroid/app/Activity;
    //   261: ldc_w 554
    //   264: iconst_0
    //   265: invokestatic 231	android/widget/Toast:makeText	(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
    //   268: invokevirtual 234	android/widget/Toast:show	()V
    //   271: getstatic 49	com/jirbo/adcolony/ADCLog:dev	Lcom/jirbo/adcolony/ADCLog;
    //   274: ldc_w 556
    //   277: invokevirtual 55	com/jirbo/adcolony/ADCLog:print	(Ljava/lang/String;)Lcom/jirbo/adcolony/ADCLog;
    //   280: pop
    //   281: return
    //   282: astore 15
    //   284: aload_0
    //   285: getfield 27	com/jirbo/adcolony/ADCMRAIDHandler:ctx	Landroid/app/Activity;
    //   288: ldc_w 554
    //   291: iconst_0
    //   292: invokestatic 231	android/widget/Toast:makeText	(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
    //   295: invokevirtual 234	android/widget/Toast:show	()V
    //   298: getstatic 49	com/jirbo/adcolony/ADCLog:dev	Lcom/jirbo/adcolony/ADCLog;
    //   301: ldc_w 558
    //   304: invokevirtual 55	com/jirbo/adcolony/ADCLog:print	(Ljava/lang/String;)Lcom/jirbo/adcolony/ADCLog;
    //   307: pop
    //   308: return
    //   309: astore 12
    //   311: goto -27 -> 284
    //   314: astore 10
    //   316: goto -59 -> 257
    //   319: astore 7
    //   321: goto -135 -> 186
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	324	0	this	ADCMRAIDHandler
    //   78	163	2	str	String
    //   86	16	3	localView	android.view.View
    //   99	109	4	localBitmap	android.graphics.Bitmap
    //   138	37	5	localFile1	java.io.File
    //   172	9	6	localFile2	java.io.File
    //   319	1	7	localException	Exception
    //   194	7	8	localFile3	java.io.File
    //   205	21	9	localFileOutputStream	java.io.FileOutputStream
    //   314	1	10	localFileNotFoundException1	java.io.FileNotFoundException
    //   309	1	12	localIOException1	java.io.IOException
    //   282	1	15	localIOException2	java.io.IOException
    //   255	1	16	localFileNotFoundException2	java.io.FileNotFoundException
    // Exception table:
    //   from	to	target	type
    //   196	207	255	java/io/FileNotFoundException
    //   196	207	282	java/io/IOException
    //   207	254	309	java/io/IOException
    //   207	254	314	java/io/FileNotFoundException
    //   174	186	319	java/lang/Exception
  }
  
  void MRAIDCommandTel(HashMap paramHashMap)
  {
    ADCLog.dev.print("ADC [info] MRAIDCommandTel called with parameters: ").println(paramHashMap);
    ADC.mraid_block = true;
    this.h.postDelayed(this.r, 1000L);
    String str = decode((String)paramHashMap.get("number"));
    ADC.track_ad_event("html5_interaction", this.play_order_index_json, this.video.ad);
    try
    {
      Intent localIntent = new Intent("android.intent.action.DIAL").setData(Uri.parse("tel:" + str));
      this.ctx.startActivity(localIntent);
      return;
    }
    catch (Exception localException)
    {
      Toast.makeText(this.ctx, "Failed to dial number.", 0).show();
    }
  }
  
  String decode(String paramString)
  {
    if (paramString == null) {
      return "";
    }
    return URLDecoder.decode(paramString);
  }
  
  void execute_javascript(String paramString)
  {
    this.end_card_web_view.loadUrl("javascript:" + paramString);
  }
  
  void handleMRAIDCommand(String paramString)
  {
    String str1 = paramString.replace("mraid://", "");
    String[] arrayOfString1;
    String str2;
    if (str1.contains("?"))
    {
      arrayOfString1 = str1.split("\\?");
      str2 = arrayOfString1[0];
      if (arrayOfString1 == null) {
        break label122;
      }
    }
    HashMap localHashMap;
    label122:
    for (String[] arrayOfString2 = arrayOfString1[1].split("&");; arrayOfString2 = new String[0])
    {
      localHashMap = new HashMap();
      int i = arrayOfString2.length;
      for (int j = 0; j < i; j++)
      {
        String str3 = arrayOfString2[j];
        localHashMap.put(str3.split("=")[0], str3.split("=")[1]);
      }
      str2 = str1;
      arrayOfString1 = null;
      break;
    }
    this.ad = ADC.current_ad;
    this.play_order_index_json = ("{\"ad_slot\":" + this.ad.zone_info.state.session_play_count + "}");
    if (str2.equals("send_adc_event")) {
      MRAIDCommandSendADCEvent((String)localHashMap.get("type"));
    }
    for (;;)
    {
      execute_javascript("adc_bridge.nativeCallComplete()");
      return;
      if (str2.equals("close")) {
        MRAIDCommandClose();
      } else if ((str2.equals("open_store")) && (!ADC.mraid_block)) {
        MRAIDCommandOpenStore((String)localHashMap.get("item"));
      } else if ((str2.equals("open")) && (!ADC.mraid_block)) {
        MRAIDCommandOpen((String)localHashMap.get("url"));
      } else if (str2.equals("expand")) {
        MRAIDCommandExpand((String)localHashMap.get("url"));
      } else if ((str2.equals("create_calendar_event")) && (!ADC.mraid_block)) {
        MRAIDCommandCreateCalendarEvent(localHashMap);
      } else if ((str2.equals("mail")) && (!ADC.mraid_block)) {
        MRAIDCommandMail(localHashMap);
      } else if ((str2.equals("sms")) && (!ADC.mraid_block)) {
        MRAIDCommandSMS(localHashMap);
      } else if ((str2.equals("tel")) && (!ADC.mraid_block)) {
        MRAIDCommandTel(localHashMap);
      } else if (str2.equals("custom_event")) {
        MRAIDCommandSendCustomADCEvent(localHashMap);
      } else if ((str2.equals("launch_app")) && (!ADC.mraid_block)) {
        MRAIDCommandLaunchApp(localHashMap);
      } else if (str2.equals("check_app_presence")) {
        MRAIDCommandCheckAppPresence(localHashMap);
      } else if (str2.equals("auto_play")) {
        MRAIDCommandAutoPlay(localHashMap);
      } else if (str2.equals("save_screenshot")) {
        MRAIDCommandTakeScreenshot();
      } else if ((str2.equals("social_post")) && (!ADC.mraid_block)) {
        MRAIDCommandSocialPost(localHashMap);
      }
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.jirbo.adcolony.ADCMRAIDHandler
 * JD-Core Version:    0.7.0.1
 */