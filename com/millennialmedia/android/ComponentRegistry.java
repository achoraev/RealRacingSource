package com.millennialmedia.android;

import java.util.Stack;

class ComponentRegistry
{
  static Stack<BridgeMMBanner> bannerBridges;
  static Stack<BridgeMMCachedVideo> cachedVideoBridges;
  static Stack<BridgeMMCalendar> calendarBridges;
  static Stack<BridgeMMDevice> deviceBridges;
  static Stack<ExampleComponent> examples = new Stack();
  static Stack<BridgeMMInlineVideo> inlineVideoBridges;
  static Stack<BridgeMMInterstitial> interstitialBridges;
  static Stack<MMLog.LoggingComponent> loggingComponents = new Stack();
  static Stack<BridgeMMMedia> mediaBridges;
  static Stack<BridgeMMNotification> notificationBridges;
  static Stack<BridgeMMSpeechkit> speechkitBridges;
  
  static
  {
    bannerBridges = new Stack();
    cachedVideoBridges = new Stack();
    calendarBridges = new Stack();
    deviceBridges = new Stack();
    inlineVideoBridges = new Stack();
    interstitialBridges = new Stack();
    mediaBridges = new Stack();
    notificationBridges = new Stack();
    speechkitBridges = new Stack();
  }
  
  static void addBannerBridge(BridgeMMBanner paramBridgeMMBanner)
  {
    bannerBridges.push(paramBridgeMMBanner);
  }
  
  static void addCachedVideoBridge(BridgeMMCachedVideo paramBridgeMMCachedVideo)
  {
    cachedVideoBridges.push(paramBridgeMMCachedVideo);
  }
  
  static void addCalendarBridge(BridgeMMCalendar paramBridgeMMCalendar)
  {
    calendarBridges.push(paramBridgeMMCalendar);
  }
  
  static void addDeviceBridge(BridgeMMDevice paramBridgeMMDevice)
  {
    deviceBridges.push(paramBridgeMMDevice);
  }
  
  static void addExample(ExampleComponent paramExampleComponent)
  {
    examples.push(paramExampleComponent);
  }
  
  static void addInlineVideoBridge(BridgeMMInlineVideo paramBridgeMMInlineVideo)
  {
    inlineVideoBridges.push(paramBridgeMMInlineVideo);
  }
  
  static void addInterstitialBridge(BridgeMMInterstitial paramBridgeMMInterstitial)
  {
    interstitialBridges.push(paramBridgeMMInterstitial);
  }
  
  static void addLoggingComponent(MMLog.LoggingComponent paramLoggingComponent)
  {
    loggingComponents.push(paramLoggingComponent);
  }
  
  static void addMediaBridge(BridgeMMMedia paramBridgeMMMedia)
  {
    mediaBridges.push(paramBridgeMMMedia);
  }
  
  static void addNotificationBridge(BridgeMMNotification paramBridgeMMNotification)
  {
    notificationBridges.push(paramBridgeMMNotification);
  }
  
  static void addSpeechkitBridge(BridgeMMSpeechkit paramBridgeMMSpeechkit)
  {
    speechkitBridges.push(paramBridgeMMSpeechkit);
  }
  
  static BridgeMMBanner getBannerBridge()
  {
    return (BridgeMMBanner)getComponent(bannerBridges);
  }
  
  static BridgeMMCachedVideo getCachedVideoBridge()
  {
    return (BridgeMMCachedVideo)getComponent(cachedVideoBridges);
  }
  
  static BridgeMMCalendar getCalendarBridge()
  {
    return (BridgeMMCalendar)getComponent(calendarBridges);
  }
  
  private static <T> T getComponent(Stack<T> paramStack)
  {
    if (paramStack.isEmpty()) {
      return null;
    }
    return paramStack.lastElement();
  }
  
  static BridgeMMDevice getDeviceBridge()
  {
    return (BridgeMMDevice)getComponent(deviceBridges);
  }
  
  static ExampleComponent getExample()
  {
    return (ExampleComponent)getComponent(examples);
  }
  
  static BridgeMMInlineVideo getInlineVideoBridge()
  {
    return (BridgeMMInlineVideo)getComponent(inlineVideoBridges);
  }
  
  static BridgeMMInterstitial getInterstitialBridge()
  {
    return (BridgeMMInterstitial)getComponent(interstitialBridges);
  }
  
  static MMLog.LoggingComponent getLoggingComponent()
  {
    return (MMLog.LoggingComponent)getComponent(loggingComponents);
  }
  
  static BridgeMMMedia getMediaBridge()
  {
    return (BridgeMMMedia)getComponent(mediaBridges);
  }
  
  static BridgeMMNotification getNotificationBridge()
  {
    return (BridgeMMNotification)getComponent(notificationBridges);
  }
  
  static BridgeMMSpeechkit getSpeechkitBridge()
  {
    return (BridgeMMSpeechkit)getComponent(speechkitBridges);
  }
  
  static void removeBannerBridge(boolean paramBoolean)
  {
    removeComponent(paramBoolean, bannerBridges);
  }
  
  static void removeCachedVideoBridge(boolean paramBoolean)
  {
    removeComponent(paramBoolean, cachedVideoBridges);
  }
  
  static void removeCalendarBridge(boolean paramBoolean)
  {
    removeComponent(paramBoolean, calendarBridges);
  }
  
  private static <T> void removeComponent(boolean paramBoolean, Stack<T> paramStack)
  {
    if (paramStack.isEmpty()) {}
    while ((paramStack.size() == 1) && (!paramBoolean)) {
      return;
    }
    paramStack.pop();
  }
  
  static void removeDeviceBridge(boolean paramBoolean)
  {
    removeComponent(paramBoolean, deviceBridges);
  }
  
  static void removeExample(boolean paramBoolean)
  {
    removeComponent(paramBoolean, examples);
  }
  
  static void removeInlineVideoBridge(boolean paramBoolean)
  {
    removeComponent(paramBoolean, inlineVideoBridges);
  }
  
  static void removeInterstitialBridge(boolean paramBoolean)
  {
    removeComponent(paramBoolean, interstitialBridges);
  }
  
  static void removeLoggingComponent(boolean paramBoolean)
  {
    removeComponent(paramBoolean, loggingComponents);
  }
  
  static void removeMediaBridge(boolean paramBoolean)
  {
    removeComponent(paramBoolean, mediaBridges);
  }
  
  static void removeNotificationBridge(boolean paramBoolean)
  {
    removeComponent(paramBoolean, notificationBridges);
  }
  
  static void removeSpeechkitBridge(boolean paramBoolean)
  {
    removeComponent(paramBoolean, speechkitBridges);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.ComponentRegistry
 * JD-Core Version:    0.7.0.1
 */