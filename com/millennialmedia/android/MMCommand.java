package com.millennialmedia.android;

import android.net.Uri;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

class MMCommand
  implements Runnable
{
  private static final String MM_BANNER = "MMBanner";
  private static final String MM_CACHED_VIDEO = "MMCachedVideo";
  private static final String MM_CALENDAR = "MMCalendar";
  private static final String MM_DEVICE = "MMDevice";
  private static final String MM_INLINE_VIDEO = "MMInlineVideo";
  private static final String MM_INTERSTITIAL = "MMInterstitial";
  private static final String MM_MEDIA = "MMMedia";
  private static final String MM_NOTIFICATION = "MMNotification";
  private static final String MM_SPEECHKIT = "MMSpeechkit";
  private static final String TAG = "MMCommand";
  private Map<String, String> arguments;
  private String bridgeService;
  private String callback;
  private String serviceMethod;
  private WeakReference<MMWebView> webViewRef;
  
  static
  {
    ComponentRegistry.addBannerBridge(new BridgeMMBanner());
    ComponentRegistry.addCachedVideoBridge(new BridgeMMCachedVideo());
    ComponentRegistry.addCalendarBridge(new BridgeMMCalendar());
    ComponentRegistry.addDeviceBridge(new BridgeMMDevice());
    ComponentRegistry.addInlineVideoBridge(new BridgeMMInlineVideo());
    ComponentRegistry.addInterstitialBridge(new BridgeMMInterstitial());
    ComponentRegistry.addMediaBridge(new BridgeMMMedia());
    ComponentRegistry.addNotificationBridge(new BridgeMMNotification());
    ComponentRegistry.addSpeechkitBridge(new BridgeMMSpeechkit());
  }
  
  MMCommand(MMWebView paramMMWebView, String paramString)
  {
    this.webViewRef = new WeakReference(paramMMWebView);
    try
    {
      String[] arrayOfString1 = Uri.parse(paramString).getHost().split("\\.");
      if (arrayOfString1.length != 2) {
        return;
      }
      this.bridgeService = arrayOfString1[0];
      this.serviceMethod = arrayOfString1[1];
      this.arguments = new HashMap();
      String[] arrayOfString2 = paramString.substring(1 + paramString.indexOf('?')).split("&");
      int i = arrayOfString2.length;
      for (int j = 0; j < i; j++)
      {
        String[] arrayOfString3 = arrayOfString2[j].split("=");
        if (arrayOfString3.length >= 2)
        {
          this.arguments.put(Uri.decode(arrayOfString3[0]), Uri.decode(arrayOfString3[1]));
          if (arrayOfString3[0].equalsIgnoreCase("callback")) {
            this.callback = Uri.decode(arrayOfString3[1]);
          }
        }
      }
      return;
    }
    catch (Exception localException)
    {
      MMLog.e("MMCommand", String.format("Exception while executing javascript call %s ", new Object[] { paramString }), localException);
      localException.printStackTrace();
    }
  }
  
  private MMJSObject getBridgeService(String paramString)
  {
    BridgeMMBanner localBridgeMMBanner = null;
    if (paramString != null)
    {
      if (!"MMBanner".equals(paramString)) {
        break label21;
      }
      localBridgeMMBanner = ComponentRegistry.getBannerBridge();
    }
    label21:
    boolean bool;
    do
    {
      return localBridgeMMBanner;
      if ("MMCachedVideo".equals(paramString)) {
        return ComponentRegistry.getCachedVideoBridge();
      }
      if ("MMCalendar".equals(paramString)) {
        return ComponentRegistry.getCalendarBridge();
      }
      if ("MMDevice".equals(paramString)) {
        return ComponentRegistry.getDeviceBridge();
      }
      if ("MMInlineVideo".equals(paramString)) {
        return ComponentRegistry.getInlineVideoBridge();
      }
      if ("MMInterstitial".equals(paramString)) {
        return ComponentRegistry.getInterstitialBridge();
      }
      if ("MMMedia".equals(paramString)) {
        return ComponentRegistry.getMediaBridge();
      }
      if ("MMNotification".equals(paramString)) {
        return ComponentRegistry.getNotificationBridge();
      }
      bool = "MMSpeechkit".equals(paramString);
      localBridgeMMBanner = null;
    } while (!bool);
    return ComponentRegistry.getSpeechkitBridge();
  }
  
  private String getBridgeStrippedClassName()
  {
    return this.bridgeService.replaceFirst("Bridge", "");
  }
  
  boolean isResizeCommand()
  {
    if (this.serviceMethod != null) {
      return "resize".equals(this.serviceMethod);
    }
    return false;
  }
  
  /* Error */
  public void run()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 146	com/millennialmedia/android/MMCommand:bridgeService	Ljava/lang/String;
    //   4: ifnull +299 -> 303
    //   7: aload_0
    //   8: getfield 148	com/millennialmedia/android/MMCommand:serviceMethod	Ljava/lang/String;
    //   11: astore 7
    //   13: aload 7
    //   15: ifnull +288 -> 303
    //   18: aload_0
    //   19: getfield 126	com/millennialmedia/android/MMCommand:webViewRef	Ljava/lang/ref/WeakReference;
    //   22: invokevirtual 260	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   25: checkcast 262	com/millennialmedia/android/MMWebView
    //   28: astore 9
    //   30: aconst_null
    //   31: astore 5
    //   33: aload 9
    //   35: ifnull +63 -> 98
    //   38: aload_0
    //   39: aload_0
    //   40: getfield 146	com/millennialmedia/android/MMCommand:bridgeService	Ljava/lang/String;
    //   43: invokespecial 264	com/millennialmedia/android/MMCommand:getBridgeService	(Ljava/lang/String;)Lcom/millennialmedia/android/MMJSObject;
    //   46: astore 10
    //   48: aload 10
    //   50: ifnull +139 -> 189
    //   53: aload 10
    //   55: aload 9
    //   57: invokevirtual 268	com/millennialmedia/android/MMWebView:getContext	()Landroid/content/Context;
    //   60: invokevirtual 274	com/millennialmedia/android/MMJSObject:setContext	(Landroid/content/Context;)V
    //   63: aload 10
    //   65: aload 9
    //   67: invokevirtual 278	com/millennialmedia/android/MMJSObject:setMMWebView	(Lcom/millennialmedia/android/MMWebView;)V
    //   70: aload 9
    //   72: aload_0
    //   73: getfield 153	com/millennialmedia/android/MMCommand:arguments	Ljava/util/Map;
    //   76: invokevirtual 282	com/millennialmedia/android/MMWebView:updateArgumentsWithSettings	(Ljava/util/Map;)V
    //   79: aload 10
    //   81: aload_0
    //   82: getfield 148	com/millennialmedia/android/MMCommand:serviceMethod	Ljava/lang/String;
    //   85: aload_0
    //   86: getfield 153	com/millennialmedia/android/MMCommand:arguments	Ljava/util/Map;
    //   89: invokevirtual 286	com/millennialmedia/android/MMJSObject:executeCommand	(Ljava/lang/String;Ljava/util/Map;)Lcom/millennialmedia/android/MMJSResponse;
    //   92: astore 11
    //   94: aload 11
    //   96: astore 5
    //   98: aload_0
    //   99: getfield 182	com/millennialmedia/android/MMCommand:callback	Ljava/lang/String;
    //   102: invokestatic 292	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   105: ifne +83 -> 188
    //   108: aload_0
    //   109: getfield 126	com/millennialmedia/android/MMCommand:webViewRef	Ljava/lang/ref/WeakReference;
    //   112: invokevirtual 260	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   115: checkcast 262	com/millennialmedia/android/MMWebView
    //   118: astore 6
    //   120: aload 6
    //   122: ifnull +66 -> 188
    //   125: aload 5
    //   127: ifnonnull +12 -> 139
    //   130: aload_0
    //   131: getfield 148	com/millennialmedia/android/MMCommand:serviceMethod	Ljava/lang/String;
    //   134: invokestatic 298	com/millennialmedia/android/MMJSResponse:responseWithError	(Ljava/lang/String;)Lcom/millennialmedia/android/MMJSResponse;
    //   137: astore 5
    //   139: aload 5
    //   141: getfield 301	com/millennialmedia/android/MMJSResponse:methodName	Ljava/lang/String;
    //   144: ifnonnull +12 -> 156
    //   147: aload 5
    //   149: aload_0
    //   150: getfield 148	com/millennialmedia/android/MMCommand:serviceMethod	Ljava/lang/String;
    //   153: putfield 301	com/millennialmedia/android/MMJSResponse:methodName	Ljava/lang/String;
    //   156: aload 5
    //   158: getfield 304	com/millennialmedia/android/MMJSResponse:className	Ljava/lang/String;
    //   161: ifnonnull +12 -> 173
    //   164: aload 5
    //   166: aload_0
    //   167: invokespecial 306	com/millennialmedia/android/MMCommand:getBridgeStrippedClassName	()Ljava/lang/String;
    //   170: putfield 304	com/millennialmedia/android/MMJSResponse:className	Ljava/lang/String;
    //   173: new 308	com/millennialmedia/android/MMCommand$1
    //   176: dup
    //   177: aload_0
    //   178: aload 6
    //   180: aload 5
    //   182: invokespecial 311	com/millennialmedia/android/MMCommand$1:<init>	(Lcom/millennialmedia/android/MMCommand;Lcom/millennialmedia/android/MMWebView;Lcom/millennialmedia/android/MMJSResponse;)V
    //   185: invokestatic 317	com/millennialmedia/android/MMSDK:runOnUiThread	(Ljava/lang/Runnable;)V
    //   188: return
    //   189: new 319	java/lang/StringBuilder
    //   192: dup
    //   193: invokespecial 320	java/lang/StringBuilder:<init>	()V
    //   196: ldc_w 322
    //   199: invokevirtual 326	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   202: aload_0
    //   203: getfield 146	com/millennialmedia/android/MMCommand:bridgeService	Ljava/lang/String;
    //   206: invokevirtual 326	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   209: ldc_w 328
    //   212: invokevirtual 326	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   215: invokevirtual 331	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   218: invokestatic 298	com/millennialmedia/android/MMJSResponse:responseWithError	(Ljava/lang/String;)Lcom/millennialmedia/android/MMJSResponse;
    //   221: astore 12
    //   223: aload 12
    //   225: astore 5
    //   227: goto -129 -> 98
    //   230: astore 8
    //   232: ldc 37
    //   234: new 319	java/lang/StringBuilder
    //   237: dup
    //   238: invokespecial 320	java/lang/StringBuilder:<init>	()V
    //   241: ldc_w 333
    //   244: invokevirtual 326	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   247: aload_0
    //   248: getfield 148	com/millennialmedia/android/MMCommand:serviceMethod	Ljava/lang/String;
    //   251: invokevirtual 326	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   254: invokevirtual 331	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   257: aload 8
    //   259: invokestatic 194	com/millennialmedia/android/MMLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   262: new 319	java/lang/StringBuilder
    //   265: dup
    //   266: invokespecial 320	java/lang/StringBuilder:<init>	()V
    //   269: ldc_w 335
    //   272: invokevirtual 326	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   275: aload 8
    //   277: invokevirtual 339	java/lang/Object:getClass	()Ljava/lang/Class;
    //   280: invokevirtual 344	java/lang/Class:getName	()Ljava/lang/String;
    //   283: invokevirtual 326	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   286: ldc_w 346
    //   289: invokevirtual 326	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   292: invokevirtual 331	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   295: invokestatic 298	com/millennialmedia/android/MMJSResponse:responseWithError	(Ljava/lang/String;)Lcom/millennialmedia/android/MMJSResponse;
    //   298: astore 5
    //   300: goto -202 -> 98
    //   303: ldc_w 348
    //   306: invokestatic 298	com/millennialmedia/android/MMJSResponse:responseWithError	(Ljava/lang/String;)Lcom/millennialmedia/android/MMJSResponse;
    //   309: astore 4
    //   311: aload 4
    //   313: astore 5
    //   315: goto -217 -> 98
    //   318: astore_1
    //   319: aload_0
    //   320: getfield 182	com/millennialmedia/android/MMCommand:callback	Ljava/lang/String;
    //   323: invokestatic 292	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   326: ifne +75 -> 401
    //   329: aload_0
    //   330: getfield 126	com/millennialmedia/android/MMCommand:webViewRef	Ljava/lang/ref/WeakReference;
    //   333: invokevirtual 260	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   336: checkcast 262	com/millennialmedia/android/MMWebView
    //   339: astore_2
    //   340: aload_2
    //   341: ifnull +60 -> 401
    //   344: aconst_null
    //   345: astore_3
    //   346: iconst_0
    //   347: ifne +11 -> 358
    //   350: aload_0
    //   351: getfield 148	com/millennialmedia/android/MMCommand:serviceMethod	Ljava/lang/String;
    //   354: invokestatic 298	com/millennialmedia/android/MMJSResponse:responseWithError	(Ljava/lang/String;)Lcom/millennialmedia/android/MMJSResponse;
    //   357: astore_3
    //   358: aload_3
    //   359: getfield 301	com/millennialmedia/android/MMJSResponse:methodName	Ljava/lang/String;
    //   362: ifnonnull +11 -> 373
    //   365: aload_3
    //   366: aload_0
    //   367: getfield 148	com/millennialmedia/android/MMCommand:serviceMethod	Ljava/lang/String;
    //   370: putfield 301	com/millennialmedia/android/MMJSResponse:methodName	Ljava/lang/String;
    //   373: aload_3
    //   374: getfield 304	com/millennialmedia/android/MMJSResponse:className	Ljava/lang/String;
    //   377: ifnonnull +11 -> 388
    //   380: aload_3
    //   381: aload_0
    //   382: invokespecial 306	com/millennialmedia/android/MMCommand:getBridgeStrippedClassName	()Ljava/lang/String;
    //   385: putfield 304	com/millennialmedia/android/MMJSResponse:className	Ljava/lang/String;
    //   388: new 308	com/millennialmedia/android/MMCommand$1
    //   391: dup
    //   392: aload_0
    //   393: aload_2
    //   394: aload_3
    //   395: invokespecial 311	com/millennialmedia/android/MMCommand$1:<init>	(Lcom/millennialmedia/android/MMCommand;Lcom/millennialmedia/android/MMWebView;Lcom/millennialmedia/android/MMJSResponse;)V
    //   398: invokestatic 317	com/millennialmedia/android/MMSDK:runOnUiThread	(Ljava/lang/Runnable;)V
    //   401: aload_1
    //   402: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	403	0	this	MMCommand
    //   318	84	1	localObject1	Object
    //   339	55	2	localMMWebView1	MMWebView
    //   345	50	3	localMMJSResponse1	MMJSResponse
    //   309	3	4	localMMJSResponse2	MMJSResponse
    //   31	283	5	localObject2	Object
    //   118	61	6	localMMWebView2	MMWebView
    //   11	3	7	str	String
    //   230	46	8	localException	Exception
    //   28	43	9	localMMWebView3	MMWebView
    //   46	34	10	localMMJSObject	MMJSObject
    //   92	3	11	localMMJSResponse3	MMJSResponse
    //   221	3	12	localMMJSResponse4	MMJSResponse
    // Exception table:
    //   from	to	target	type
    //   18	30	230	java/lang/Exception
    //   38	48	230	java/lang/Exception
    //   53	94	230	java/lang/Exception
    //   189	223	230	java/lang/Exception
    //   0	13	318	finally
    //   18	30	318	finally
    //   38	48	318	finally
    //   53	94	318	finally
    //   189	223	318	finally
    //   232	300	318	finally
    //   303	311	318	finally
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.MMCommand
 * JD-Core Version:    0.7.0.1
 */