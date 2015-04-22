package com.millennialmedia.android;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.URLUtil;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.Map;

public class BridgeMMSpeechkit
  extends MMJSObject
  implements MediaPlayer.OnCompletionListener, BridgeMMMedia.Audio.PeriodicListener
{
  private static final String TAG = "BridgeMMSpeechkit";
  private String ADD_CUSTOM_VOICE_WORDS = "addCustomVoiceWords";
  private String CACHE_AUDIO = "cacheAudio";
  private String DELETE_CUSTOM_VOICE_WORDS = "deleteCustomVoiceWords";
  private String END_RECORDING = "endRecording";
  private String GET_SESSION_ID = "getSessionId";
  private String PLAY_AUDIO = "playAudio";
  private String RELEASE_VOICE = "releaseVoice";
  private String SAMPLE_BACKGROUND_AUDIO_LEVEL = "sampleBackgroundAudioLevel";
  private String START_RECORDING = "startRecording";
  private String STOP_AUDIO = "stopAudio";
  private String TEXT_TO_SPEECH = "textToSpeech";
  
  private NVASpeechKit getCreateSpeechKit()
  {
    MMWebView localMMWebView = (MMWebView)this.mmWebViewRef.get();
    NVASpeechKit localNVASpeechKit = null;
    if (localMMWebView != null)
    {
      boolean bool = localMMWebView.allowSpeechCreationCommands();
      localNVASpeechKit = null;
      if (bool)
      {
        if (getSpeechKitInternal() != null) {
          return getSpeechKitInternal();
        }
        Context localContext = localMMWebView.getContext();
        localNVASpeechKit = null;
        if (localContext != null)
        {
          localNVASpeechKit = new NVASpeechKit(localMMWebView);
          setSpeechKit(localNVASpeechKit);
          HandShake.NuanceCredentials localNuanceCredentials = HandShake.sharedHandShake(localContext).nuanceCredentials;
          if (localNuanceCredentials != null) {
            localNVASpeechKit.initialize(localNuanceCredentials, localContext.getApplicationContext());
          }
        }
      }
    }
    return localNVASpeechKit;
  }
  
  static SpeechKitHolder getInstance()
  {
    return SingletonHolder.INSTANCE;
  }
  
  private NVASpeechKit getSpeechKit()
  {
    MMWebView localMMWebView = (MMWebView)this.mmWebViewRef.get();
    if ((localMMWebView != null) && (localMMWebView.allowRecordingCommands())) {
      return getSpeechKitInternal();
    }
    return null;
  }
  
  static NVASpeechKit getSpeechKitInternal()
  {
    return getInstance().getSpeechKit();
  }
  
  private NVASpeechKit getSpeechKitRelease()
  {
    return getSpeechKitInternal();
  }
  
  private MMJSResponse playAudioInternal(Map<String, String> paramMap)
  {
    Context localContext = (Context)this.contextRef.get();
    String str = (String)paramMap.get("path");
    BridgeMMMedia.Audio localAudio;
    if ((localContext != null) && (str != null))
    {
      localAudio = BridgeMMMedia.Audio.sharedAudio(localContext);
      if (localAudio != null) {
        break label44;
      }
    }
    label44:
    File localFile;
    do
    {
      return null;
      if (localAudio.isPlaying()) {
        return MMJSResponse.responseWithError("Audio already playing.");
      }
      if (str.startsWith("http")) {
        return localAudio.playAudio(Uri.parse(str), Boolean.parseBoolean((String)paramMap.get("repeat")));
      }
      localFile = AdCache.getDownloadFile(localContext, str);
    } while (!localFile.exists());
    return localAudio.playAudio(Uri.fromFile(localFile), Boolean.parseBoolean((String)paramMap.get("repeat")));
  }
  
  static boolean releaseSpeechKit()
  {
    return getInstance().release();
  }
  
  static void setSpeechKit(NVASpeechKit paramNVASpeechKit)
  {
    getInstance().release();
    getInstance().setSpeechKit(paramNVASpeechKit);
  }
  
  public MMJSResponse addCustomVoiceWords(Map<String, String> paramMap)
  {
    NVASpeechKit localNVASpeechKit = getCreateSpeechKit();
    if (localNVASpeechKit == null) {
      return MMJSResponse.responseWithError("Unable to create speech kit");
    }
    String str = (String)paramMap.get("words");
    if (!TextUtils.isEmpty(str))
    {
      String[] arrayOfString = str.split(",");
      localNVASpeechKit.updateCustomWords(NVASpeechKit.CustomWordsOp.Add, arrayOfString);
      injectJavascript("javascript:MMJS.sdk.customVoiceWordsAdded()");
      return MMJSResponse.responseWithSuccess("Added " + str);
    }
    return null;
  }
  
  public MMJSResponse cacheAudio(Map<String, String> paramMap)
  {
    String str = (String)paramMap.get("url");
    if (!URLUtil.isValidUrl(str)) {
      return MMJSResponse.responseWithError("Invalid url");
    }
    if (this.contextRef != null)
    {
      Context localContext = (Context)this.contextRef.get();
      if ((localContext != null) && (AdCache.downloadComponentExternalStorage(str, str.substring(1 + str.lastIndexOf("/")), localContext)))
      {
        injectJavascript("javascript:MMJS.sdk.audioCached()");
        return MMJSResponse.responseWithSuccess("Successfully cached audio at " + str);
      }
    }
    return MMJSResponse.responseWithError("Failed to cache audio at" + str);
  }
  
  public MMJSResponse deleteCustomVoiceWords(Map<String, String> paramMap)
  {
    NVASpeechKit localNVASpeechKit = getCreateSpeechKit();
    if (localNVASpeechKit == null) {
      return MMJSResponse.responseWithError("Unable to create speech kit");
    }
    String str = (String)paramMap.get("words");
    if (!TextUtils.isEmpty(str))
    {
      String[] arrayOfString = str.split(",");
      localNVASpeechKit.updateCustomWords(NVASpeechKit.CustomWordsOp.Remove, arrayOfString);
      injectJavascript("javascript:MMJS.sdk.customVoiceWordsDeleted()");
      return MMJSResponse.responseWithSuccess("Deleted " + str);
    }
    return null;
  }
  
  public MMJSResponse endRecording(Map<String, String> paramMap)
  {
    NVASpeechKit localNVASpeechKit = getSpeechKit();
    if (localNVASpeechKit == null) {
      return MMJSResponse.responseWithError("Unable to get speech kit");
    }
    try
    {
      if (localNVASpeechKit.endRecording())
      {
        MMJSResponse localMMJSResponse = MMJSResponse.responseWithSuccess();
        return localMMJSResponse;
      }
    }
    finally {}
    return MMJSResponse.responseWithError("Failed in speechKit");
  }
  
  MMJSResponse executeCommand(String paramString, Map<String, String> paramMap)
  {
    MMJSResponse localMMJSResponse;
    if (this.START_RECORDING.equals(paramString)) {
      localMMJSResponse = startRecording(paramMap);
    }
    boolean bool;
    do
    {
      return localMMJSResponse;
      if (this.END_RECORDING.equals(paramString)) {
        return endRecording(paramMap);
      }
      if (this.CACHE_AUDIO.equals(paramString)) {
        return cacheAudio(paramMap);
      }
      if (this.GET_SESSION_ID.equals(paramString)) {
        return getSessionId(paramMap);
      }
      if (this.PLAY_AUDIO.equals(paramString)) {
        return playAudio(paramMap);
      }
      if (this.TEXT_TO_SPEECH.equals(paramString)) {
        return textToSpeech(paramMap);
      }
      if (this.STOP_AUDIO.equals(paramString)) {
        return stopAudio(paramMap);
      }
      if (this.SAMPLE_BACKGROUND_AUDIO_LEVEL.equals(paramString)) {
        return sampleBackgroundAudioLevel(paramMap);
      }
      if (this.RELEASE_VOICE.equals(paramString)) {
        return releaseVoice(paramMap);
      }
      if (this.ADD_CUSTOM_VOICE_WORDS.equals(paramString)) {
        return addCustomVoiceWords(paramMap);
      }
      bool = this.DELETE_CUSTOM_VOICE_WORDS.equals(paramString);
      localMMJSResponse = null;
    } while (!bool);
    return deleteCustomVoiceWords(paramMap);
  }
  
  public MMJSResponse getSessionId(Map<String, String> paramMap)
  {
    NVASpeechKit localNVASpeechKit = getSpeechKit();
    if (localNVASpeechKit == null) {
      return MMJSResponse.responseWithError("No SpeechKit session open.");
    }
    String str = localNVASpeechKit.getSessionId();
    if (!TextUtils.isEmpty(str)) {
      return MMJSResponse.responseWithSuccess(str);
    }
    return MMJSResponse.responseWithError("No SpeechKit session open.");
  }
  
  void injectJavascript(String paramString)
  {
    MMWebView localMMWebView = (MMWebView)this.mmWebViewRef.get();
    if (localMMWebView != null) {
      localMMWebView.loadUrl(paramString);
    }
  }
  
  public void onCompletion(MediaPlayer paramMediaPlayer)
  {
    injectJavascript("javascript:MMJS.sdk.audioCompleted()");
    Context localContext = (Context)this.contextRef.get();
    if (localContext != null)
    {
      BridgeMMMedia.Audio localAudio = BridgeMMMedia.Audio.sharedAudio(localContext);
      if (localAudio != null)
      {
        localAudio.removeCompletionListener(this);
        localAudio.removePeriodicListener(this);
      }
    }
  }
  
  public void onUpdate(int paramInt)
  {
    injectJavascript("javascript:MMJS.sdk.audioPositionChange(" + paramInt + ")");
  }
  
  public MMJSResponse playAudio(Map<String, String> paramMap)
  {
    MMJSResponse localMMJSResponse;
    if (getCreateSpeechKit() == null) {
      localMMJSResponse = MMJSResponse.responseWithError("Unable to create speech kit");
    }
    do
    {
      return localMMJSResponse;
      if (!URLUtil.isValidUrl((String)paramMap.get("url"))) {
        return MMJSResponse.responseWithError("Invalid url");
      }
      String str = (String)paramMap.get("url");
      if (TextUtils.isEmpty(str)) {
        break;
      }
      Context localContext = (Context)this.contextRef.get();
      if (localContext == null) {
        break;
      }
      BridgeMMMedia.Audio localAudio = BridgeMMMedia.Audio.sharedAudio(localContext);
      if (localAudio != null)
      {
        localAudio.addCompletionListener(this);
        localAudio.addPeriodicListener(this);
      }
      paramMap.put("path", str);
      localMMJSResponse = playAudioInternal(paramMap);
    } while ((localMMJSResponse == null) || (localMMJSResponse.result != 1));
    injectJavascript("javascript:MMJS.sdk.audioStarted()");
    return localMMJSResponse;
    return null;
  }
  
  public MMJSResponse releaseVoice(Map<String, String> paramMap)
  {
    if (releaseSpeechKit()) {
      return MMJSResponse.responseWithError("Unable to get speech kit");
    }
    return MMJSResponse.responseWithSuccess("released speechkit");
  }
  
  public MMJSResponse sampleBackgroundAudioLevel(Map<String, String> paramMap)
  {
    NVASpeechKit localNVASpeechKit = getCreateSpeechKit();
    if (localNVASpeechKit == null) {
      return MMJSResponse.responseWithError("Unable to create speech kit");
    }
    localNVASpeechKit.startSampleRecording();
    return null;
  }
  
  public MMJSResponse startRecording(Map<String, String> paramMap)
  {
    NVASpeechKit localNVASpeechKit = getCreateSpeechKit();
    if (localNVASpeechKit == null) {
      return MMJSResponse.responseWithError("Unable to create speech kit");
    }
    String str = (String)paramMap.get("language");
    if (TextUtils.isEmpty(str)) {
      str = "en_GB";
    }
    if (localNVASpeechKit.startRecording(str)) {
      return MMJSResponse.responseWithSuccess();
    }
    return MMJSResponse.responseWithError("Failed in speechKit");
  }
  
  public MMJSResponse stopAudio(Map<String, String> paramMap)
  {
    NVASpeechKit localNVASpeechKit = getSpeechKit();
    if (localNVASpeechKit == null) {
      return MMJSResponse.responseWithError("Unable to get speech kit");
    }
    localNVASpeechKit.stopActions();
    if (this.contextRef != null)
    {
      BridgeMMMedia.Audio localAudio = BridgeMMMedia.Audio.sharedAudio((Context)this.contextRef.get());
      if (localAudio != null) {
        return localAudio.stop();
      }
    }
    return MMJSResponse.responseWithSuccess();
  }
  
  public MMJSResponse textToSpeech(Map<String, String> paramMap)
  {
    MMLog.d("BridgeMMSpeechkit", "@@-Calling textToSpeech");
    NVASpeechKit localNVASpeechKit = getCreateSpeechKit();
    if (localNVASpeechKit == null) {
      return MMJSResponse.responseWithError("Unable to create speech kit");
    }
    String str1 = (String)paramMap.get("language");
    String str2 = (String)paramMap.get("text");
    if (TextUtils.isEmpty(str1)) {
      str1 = "en_GB";
    }
    localNVASpeechKit.stopActions();
    if (localNVASpeechKit.textToSpeech(str2, str1)) {
      return MMJSResponse.responseWithSuccess();
    }
    return MMJSResponse.responseWithError("Failed in speechKit");
  }
  
  private static class SingletonHolder
  {
    public static final BridgeMMSpeechkit.SpeechKitHolder INSTANCE = new BridgeMMSpeechkit.SpeechKitHolder(null);
  }
  
  private static class SpeechKitHolder
  {
    private NVASpeechKit _speechKit;
    
    public NVASpeechKit getSpeechKit()
    {
      return this._speechKit;
    }
    
    public boolean release()
    {
      if (this._speechKit == null) {
        return false;
      }
      Utils.ThreadUtils.execute(new Runnable()
      {
        public void run()
        {
          synchronized (BridgeMMSpeechkit.SpeechKitHolder.this)
          {
            if (BridgeMMSpeechkit.SpeechKitHolder.this._speechKit != null)
            {
              BridgeMMSpeechkit.SpeechKitHolder.this._speechKit.cancelRecording();
              BridgeMMSpeechkit.SpeechKitHolder.this._speechKit.release();
              BridgeMMSpeechkit.SpeechKitHolder.access$002(BridgeMMSpeechkit.SpeechKitHolder.this, null);
            }
            return;
          }
        }
      });
      return true;
    }
    
    public void setSpeechKit(NVASpeechKit paramNVASpeechKit)
    {
      this._speechKit = paramNVASpeechKit;
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.BridgeMMSpeechkit
 * JD-Core Version:    0.7.0.1
 */