package com.millennialmedia.android;

import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.Callable;

class BridgeMMInlineVideo
  extends MMJSObject
{
  private static final String ADJUST_VIDEO = "adjustVideo";
  private static final String INSERT_VIDEO = "insertVideo";
  private static final String PAUSE_VIDEO = "pauseVideo";
  private static final String PLAY_VIDEO = "playVideo";
  private static final String REMOVE_VIDEO = "removeVideo";
  private static final String RESUME_VIDEO = "resumeVideo";
  private static final String SET_STREAM_VIDEO_SOURCE = "setStreamVideoSource";
  private static final String STOP_VIDEO = "stopVideo";
  
  public MMJSResponse adjustVideo(final Map<String, String> paramMap)
  {
    runOnUiThreadFuture(new Callable()
    {
      public MMJSResponse call()
      {
        MMWebView localMMWebView = (MMWebView)BridgeMMInlineVideo.this.mmWebViewRef.get();
        if ((localMMWebView != null) && (localMMWebView != null) && (localMMWebView.getMMLayout().adjustVideo(new InlineVideoView.InlineParams(paramMap, localMMWebView.getContext())))) {
          return MMJSResponse.responseWithSuccess();
        }
        return MMJSResponse.responseWithError();
      }
    });
  }
  
  MMJSResponse executeCommand(String paramString, Map<String, String> paramMap)
  {
    MMJSResponse localMMJSResponse;
    if ("adjustVideo".equals(paramString)) {
      localMMJSResponse = adjustVideo(paramMap);
    }
    boolean bool;
    do
    {
      return localMMJSResponse;
      if ("insertVideo".equals(paramString)) {
        return insertVideo(paramMap);
      }
      if ("pauseVideo".equals(paramString)) {
        return pauseVideo(paramMap);
      }
      if ("playVideo".equals(paramString)) {
        return playVideo(paramMap);
      }
      if ("removeVideo".equals(paramString)) {
        return removeVideo(paramMap);
      }
      if ("resumeVideo".equals(paramString)) {
        return resumeVideo(paramMap);
      }
      if ("setStreamVideoSource".equals(paramString)) {
        return setStreamVideoSource(paramMap);
      }
      bool = "stopVideo".equals(paramString);
      localMMJSResponse = null;
    } while (!bool);
    return stopVideo(paramMap);
  }
  
  public MMJSResponse insertVideo(final Map<String, String> paramMap)
  {
    runOnUiThreadFuture(new Callable()
    {
      public MMJSResponse call()
      {
        MMWebView localMMWebView = (MMWebView)BridgeMMInlineVideo.this.mmWebViewRef.get();
        if (localMMWebView != null)
        {
          MMLayout localMMLayout = localMMWebView.getMMLayout();
          localMMLayout.initInlineVideo(new InlineVideoView.InlineParams(paramMap, localMMWebView.getContext()));
          return MMJSResponse.responseWithSuccess("usingStreaming=" + localMMLayout.isVideoPlayingStreaming());
        }
        return MMJSResponse.responseWithError();
      }
    });
  }
  
  public MMJSResponse pauseVideo(Map<String, String> paramMap)
  {
    runOnUiThreadFuture(new Callable()
    {
      public MMJSResponse call()
      {
        MMWebView localMMWebView = (MMWebView)BridgeMMInlineVideo.this.mmWebViewRef.get();
        if (localMMWebView != null)
        {
          MMLayout localMMLayout = localMMWebView.getMMLayout();
          if (localMMLayout != null)
          {
            localMMLayout.pauseVideo();
            return MMJSResponse.responseWithSuccess();
          }
        }
        return MMJSResponse.responseWithError();
      }
    });
  }
  
  public MMJSResponse playVideo(Map<String, String> paramMap)
  {
    runOnUiThreadFuture(new Callable()
    {
      public MMJSResponse call()
      {
        MMWebView localMMWebView = (MMWebView)BridgeMMInlineVideo.this.mmWebViewRef.get();
        if (localMMWebView != null)
        {
          MMLayout localMMLayout = localMMWebView.getMMLayout();
          if (localMMLayout != null)
          {
            localMMLayout.playVideo();
            return MMJSResponse.responseWithSuccess();
          }
        }
        return MMJSResponse.responseWithError();
      }
    });
  }
  
  public MMJSResponse removeVideo(Map<String, String> paramMap)
  {
    runOnUiThreadFuture(new Callable()
    {
      public MMJSResponse call()
      {
        MMWebView localMMWebView = (MMWebView)BridgeMMInlineVideo.this.mmWebViewRef.get();
        if (localMMWebView != null)
        {
          MMLayout localMMLayout = localMMWebView.getMMLayout();
          if (localMMLayout != null)
          {
            localMMLayout.removeVideo();
            return MMJSResponse.responseWithSuccess();
          }
        }
        return MMJSResponse.responseWithError();
      }
    });
  }
  
  public MMJSResponse resumeVideo(Map<String, String> paramMap)
  {
    runOnUiThreadFuture(new Callable()
    {
      public MMJSResponse call()
      {
        MMWebView localMMWebView = (MMWebView)BridgeMMInlineVideo.this.mmWebViewRef.get();
        if (localMMWebView != null)
        {
          MMLayout localMMLayout = localMMWebView.getMMLayout();
          if (localMMLayout != null)
          {
            localMMLayout.resumeVideo();
            return MMJSResponse.responseWithSuccess();
          }
        }
        return MMJSResponse.responseWithError();
      }
    });
  }
  
  public MMJSResponse setStreamVideoSource(final Map<String, String> paramMap)
  {
    runOnUiThreadFuture(new Callable()
    {
      public MMJSResponse call()
      {
        MMWebView localMMWebView = (MMWebView)BridgeMMInlineVideo.this.mmWebViewRef.get();
        if (localMMWebView != null)
        {
          MMLayout localMMLayout = localMMWebView.getMMLayout();
          String str = (String)paramMap.get("streamVideoURI");
          if ((localMMLayout != null) && (str != null))
          {
            localMMLayout.setVideoSource(str);
            return MMJSResponse.responseWithSuccess();
          }
        }
        return MMJSResponse.responseWithError();
      }
    });
  }
  
  public MMJSResponse stopVideo(Map<String, String> paramMap)
  {
    runOnUiThreadFuture(new Callable()
    {
      public MMJSResponse call()
      {
        MMWebView localMMWebView = (MMWebView)BridgeMMInlineVideo.this.mmWebViewRef.get();
        if (localMMWebView != null)
        {
          MMLayout localMMLayout = localMMWebView.getMMLayout();
          if (localMMLayout != null)
          {
            localMMLayout.stopVideo();
            return MMJSResponse.responseWithSuccess();
          }
        }
        return MMJSResponse.responseWithError();
      }
    });
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.BridgeMMInlineVideo
 * JD-Core Version:    0.7.0.1
 */