package com.millennialmedia.android;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import com.nuance.nmdp.speechkit.DataUploadCommand;
import com.nuance.nmdp.speechkit.DataUploadCommand.Listener;
import com.nuance.nmdp.speechkit.DataUploadResult;
import com.nuance.nmdp.speechkit.GenericCommand;
import com.nuance.nmdp.speechkit.GenericCommand.Listener;
import com.nuance.nmdp.speechkit.GenericResult;
import com.nuance.nmdp.speechkit.Recognition;
import com.nuance.nmdp.speechkit.Recognizer;
import com.nuance.nmdp.speechkit.Recognizer.Listener;
import com.nuance.nmdp.speechkit.SpeechError;
import com.nuance.nmdp.speechkit.SpeechKit;
import com.nuance.nmdp.speechkit.SpeechKit.CmdSetType;
import com.nuance.nmdp.speechkit.Vocalizer;
import com.nuance.nmdp.speechkit.Vocalizer.Listener;
import com.nuance.nmdp.speechkit.recognitionresult.DetailedResult;
import com.nuance.nmdp.speechkit.util.dataupload.Action;
import com.nuance.nmdp.speechkit.util.dataupload.Action.ActionType;
import com.nuance.nmdp.speechkit.util.dataupload.Data;
import com.nuance.nmdp.speechkit.util.dataupload.Data.DataType;
import com.nuance.nmdp.speechkit.util.dataupload.DataBlock;
import com.nuance.nmdp.speechkit.util.pdx.PdxValue.Dictionary;
import java.lang.ref.WeakReference;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NVASpeechKit
{
  static final float AUDIO_LEVEL_CHANGE_INTERVAL = 0.25F;
  static final float AUDIO_LEVEL_MAX = 90.0F;
  private static final int AUDIO_LEVEL_UPDATE_FREQUENCY = 50;
  private static final int AUDIO_SAMPLE_PERIOD = 2000;
  static final float SAMPLING_BG_INTERVAL = 0.1F;
  private static final String TAG = "NVASpeechKit";
  private static String nuanceIdCache = null;
  HandShake.NuanceCredentials _credentials;
  public Result[] _results = null;
  private Runnable audioLevelCallback = new Runnable()
  {
    public void run()
    {
      if (NVASpeechKit.this.skCurrentRecognizer != null)
      {
        double d = NVASpeechKit.AudioLevelTracker.access$100(NVASpeechKit.this.skCurrentRecognizer.getAudioLevel());
        MMLog.d("NVASpeechKit", "audiolevel changed: level=" + d);
        if ((NVASpeechKit.this.audioLevelTracker.update(d)) && (NVASpeechKit.this.speechKitListener != null)) {
          NVASpeechKit.this.speechKitListener.onAudioLevelUpdate(d);
        }
        if ((NVASpeechKit.this.state == NVASpeechKit.State.RECORDING) || (NVASpeechKit.this.audioLevelTracker.isTrackingAudioSample)) {
          NVASpeechKit.this.speeckKitHandler.postDelayed(NVASpeechKit.this.audioLevelCallback, 50L);
        }
      }
    }
  };
  private AudioLevelTracker audioLevelTracker = new AudioLevelTracker();
  private Runnable audioSampleCallback = new Runnable()
  {
    public void run()
    {
      NVASpeechKit.this.endRecording();
    }
  };
  private GenericCommand.Listener commandListener = new GenericCommand.Listener()
  {
    public void onComplete(GenericCommand paramAnonymousGenericCommand, GenericResult paramAnonymousGenericResult, SpeechError paramAnonymousSpeechError)
    {
      if (paramAnonymousSpeechError != null) {
        MMLog.e("NVASpeechKit", "GenericCommand listener. Error: " + paramAnonymousSpeechError.getErrorDetail());
      }
      for (;;)
      {
        NVASpeechKit.this.notifySpeechResults();
        return;
        MMLog.d("NVASpeechKit", "GenericCommand listener. Success: " + paramAnonymousGenericResult.getQueryResult());
      }
    }
  };
  private DataUploadCommand.Listener dataUploadListener = new DataUploadCommand.Listener()
  {
    private void notifyListener(DataUploadCommand paramAnonymousDataUploadCommand)
    {
      if ((NVASpeechKit.this.speechKitListener != null) && (NVASpeechKit.this.pendingDataUploadCommand == paramAnonymousDataUploadCommand))
      {
        if (NVASpeechKit.this.pendingDataUploadCommandType != NVASpeechKit.CustomWordsOp.Add) {
          break label56;
        }
        NVASpeechKit.this.speechKitListener.onCustomWordsAdded();
      }
      for (;;)
      {
        NVASpeechKit.access$802(NVASpeechKit.this, null);
        return;
        label56:
        NVASpeechKit.this.speechKitListener.onCustomWordsDeleted();
      }
    }
    
    public void onError(DataUploadCommand paramAnonymousDataUploadCommand, SpeechError paramAnonymousSpeechError)
    {
      MMLog.e("NVASpeechKit", "DataUploadCommand listener error. command:" + paramAnonymousDataUploadCommand.toString() + " Error:" + paramAnonymousSpeechError.getErrorDetail());
      notifyListener(paramAnonymousDataUploadCommand);
    }
    
    public void onResults(DataUploadCommand paramAnonymousDataUploadCommand, DataUploadResult paramAnonymousDataUploadResult)
    {
      MMLog.d("NVASpeechKit", "DataUploadCommand listener successful command:" + paramAnonymousDataUploadCommand.toString() + " isVocRegenerated:" + paramAnonymousDataUploadResult.isVocRegenerated() + " results:" + paramAnonymousDataUploadResult.toString());
      notifyListener(paramAnonymousDataUploadCommand);
    }
  };
  private String nuance_transaction_session_id;
  private String packageName;
  private DataUploadCommand pendingDataUploadCommand;
  private CustomWordsOp pendingDataUploadCommandType;
  private SpeechKit sk;
  private Recognizer skCurrentRecognizer;
  private Vocalizer skCurrentVocalizer;
  private Recognizer.Listener skRecogListener;
  private Vocalizer.Listener skVocalListener;
  private Listener speechKitListener = new Listener()
  {
    public void onAudioLevelUpdate(double paramAnonymousDouble)
    {
      NVASpeechKit.this.audioLevelChange(paramAnonymousDouble);
    }
    
    public void onAudioSampleUpdate(double paramAnonymousDouble)
    {
      NVASpeechKit.this.backgroundAudioLevel(paramAnonymousDouble);
    }
    
    public void onCustomWordsAdded() {}
    
    public void onCustomWordsDeleted() {}
    
    public void onError() {}
    
    public void onResults()
    {
      JSONArray localJSONArray = NVASpeechKit.this.resultsToJSON(NVASpeechKit.this.getResults());
      NVASpeechKit.this.recognitionResult(localJSONArray.toString());
    }
    
    public void onStateChange(NVASpeechKit.State paramAnonymousState)
    {
      switch (NVASpeechKit.8.$SwitchMap$com$millennialmedia$android$NVASpeechKit$State[paramAnonymousState.ordinal()])
      {
      default: 
        return;
      case 1: 
        NVASpeechKit.this.voiceStateChangeError();
        return;
      case 2: 
        NVASpeechKit.this.voiceStateChangeProcessing();
        return;
      case 3: 
        NVASpeechKit.this.voiceStateChangeReady();
        return;
      case 4: 
        NVASpeechKit.this.voiceStateChangeRecording();
        return;
      }
      NVASpeechKit.this.voiceStateChangeVocalizing();
    }
  };
  private Handler speeckKitHandler;
  private State state;
  private WeakReference<MMWebView> webViewRef;
  
  public NVASpeechKit(MMWebView paramMMWebView)
  {
    if (paramMMWebView != null)
    {
      this.webViewRef = new WeakReference(paramMMWebView);
      initInternalData(paramMMWebView.getContext().getApplicationContext());
    }
    this.state = State.READY;
  }
  
  private String byte2Str(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null) {
      return null;
    }
    StringBuffer localStringBuffer = new StringBuffer();
    for (int i = 0; i < paramArrayOfByte.length; i++)
    {
      int j = 0xFF & paramArrayOfByte[i];
      String str = Integer.toHexString(j);
      if (j < 16) {
        localStringBuffer.append('0');
      }
      localStringBuffer.append(str);
    }
    return localStringBuffer.toString();
  }
  
  private void cancelAudioLevelCallbacks()
  {
    if (this.speeckKitHandler != null)
    {
      this.speeckKitHandler.removeCallbacks(this.audioSampleCallback);
      this.speeckKitHandler.removeCallbacks(this.audioLevelCallback);
    }
  }
  
  private Recognizer.Listener createRecognizerListener()
  {
    new Recognizer.Listener()
    {
      public void onError(Recognizer paramAnonymousRecognizer, SpeechError paramAnonymousSpeechError)
      {
        MMLog.d("NVASpeechKit", "Speech Kit Error code:" + paramAnonymousSpeechError.getErrorCode() + " detail:" + paramAnonymousSpeechError.getErrorDetail() + " suggestions:" + paramAnonymousSpeechError.getSuggestion());
        NVASpeechKit.this.cancelAudioLevelCallbacks();
        NVASpeechKit.this.handleSpeechError(paramAnonymousSpeechError);
        NVASpeechKit.access$002(NVASpeechKit.this, null);
        if (NVASpeechKit.this.sk != null) {
          MMLog.d("NVASpeechKit", "Recognizer.Listener.onError: session id [" + NVASpeechKit.this.sk.getSessionId() + "]");
        }
      }
      
      public void onRecordingBegin(Recognizer paramAnonymousRecognizer)
      {
        MMLog.d("NVASpeechKit", "recording begins");
        NVASpeechKit.this._results = null;
        if (!NVASpeechKit.this.audioLevelTracker.isTrackingAudioSample()) {
          NVASpeechKit.this.setState(NVASpeechKit.State.RECORDING);
        }
        NVASpeechKit.this.startProgress(paramAnonymousRecognizer);
        if (NVASpeechKit.this.audioLevelTracker.isTrackingAudioSample())
        {
          NVASpeechKit.this.speeckKitHandler.removeCallbacks(NVASpeechKit.this.audioSampleCallback);
          NVASpeechKit.this.speeckKitHandler.postDelayed(NVASpeechKit.this.audioSampleCallback, 2000L);
        }
      }
      
      public void onRecordingDone(Recognizer paramAnonymousRecognizer)
      {
        MMLog.d("NVASpeechKit", "recording has ended");
        NVASpeechKit.this.cancelAudioLevelCallbacks();
        if (!NVASpeechKit.this.audioLevelTracker.isTrackingAudioSample()) {
          NVASpeechKit.this.setState(NVASpeechKit.State.PROCESSING);
        }
        if (NVASpeechKit.this.sk != null) {
          NVASpeechKit.access$1702(NVASpeechKit.this, NVASpeechKit.this.sk.getSessionId());
        }
      }
      
      public void onResults(Recognizer paramAnonymousRecognizer, Recognition paramAnonymousRecognition)
      {
        MMLog.d("NVASpeechKit", "recording results returned.");
        NVASpeechKit.this.cancelAudioLevelCallbacks();
        if (!NVASpeechKit.this.audioLevelTracker.isTrackingAudioSample)
        {
          NVASpeechKit.this.processResults(paramAnonymousRecognition.getDetailedResults());
          if (NVASpeechKit.this.nuance_transaction_session_id != null) {
            MMLog.d("NVASpeechKit", "Recognizer.Listener.onResults: session id [" + NVASpeechKit.this.nuance_transaction_session_id + "]");
          }
          NVASpeechKit.this.logEvent();
          return;
        }
        NVASpeechKit.this._results = new NVASpeechKit.Result[0];
        NVASpeechKit.this.notifySpeechResults();
      }
    };
  }
  
  private Vocalizer.Listener createVocalizerListener()
  {
    new Vocalizer.Listener()
    {
      public void onSpeakingBegin(Vocalizer paramAnonymousVocalizer, String paramAnonymousString, Object paramAnonymousObject)
      {
        MMLog.d("NVASpeechKit", "Vocalization begins. text=" + paramAnonymousString);
        NVASpeechKit.this.setState(NVASpeechKit.State.VOCALIZING);
      }
      
      public void onSpeakingDone(Vocalizer paramAnonymousVocalizer, String paramAnonymousString, SpeechError paramAnonymousSpeechError, Object paramAnonymousObject)
      {
        MMLog.d("NVASpeechKit", "Vocalization has ended.");
        if (paramAnonymousSpeechError != null)
        {
          MMLog.e("NVASpeechKit", "Vocalizer error: " + paramAnonymousSpeechError.getErrorDetail());
          NVASpeechKit.this.handleSpeechError(paramAnonymousSpeechError);
          return;
        }
        NVASpeechKit.this.setState(NVASpeechKit.State.READY);
      }
    };
  }
  
  private String getAdId()
  {
    if (this.webViewRef != null)
    {
      MMWebView localMMWebView = (MMWebView)this.webViewRef.get();
      if (localMMWebView != null) {
        return localMMWebView.getAdId();
      }
    }
    return "DEFAULT_AD_ID";
  }
  
  private MMWebView getMMWebView()
  {
    if (this.webViewRef != null) {
      return (MMWebView)this.webViewRef.get();
    }
    return null;
  }
  
  private String getSpeechError(SpeechError paramSpeechError)
  {
    if (paramSpeechError == null) {
      return "No Error given";
    }
    return "Speech Kit Error code:" + paramSpeechError.getErrorCode() + " detail:" + paramSpeechError.getErrorDetail() + " suggestions:" + paramSpeechError.getSuggestion();
  }
  
  private void handleSpeechError(SpeechError paramSpeechError)
  {
    switch (paramSpeechError.getErrorCode())
    {
    case 3: 
    case 4: 
    default: 
      if (this.speechKitListener != null)
      {
        this.speechKitListener.onError();
        setState(State.ERROR);
        voiceError(getSpeechError(paramSpeechError));
      }
      return;
    case 2: 
      if (!this.audioLevelTracker.isTrackingAudioSample) {
        setState(State.PROCESSING);
      }
      this._results = new Result[0];
      notifySpeechResults();
      return;
    }
    setState(State.READY);
    this.skCurrentRecognizer = null;
  }
  
  private void initInternalData(Context paramContext)
  {
    if (this.packageName == null) {
      this.packageName = paramContext.getApplicationContext().getPackageName();
    }
  }
  
  private void notifySpeechResults()
  {
    if ((this.speechKitListener != null) && (this._results != null))
    {
      if (!this.audioLevelTracker.isTrackingAudioSample) {
        break label60;
      }
      this.speechKitListener.onAudioSampleUpdate(this.audioLevelTracker.averageLevel);
      this.audioLevelTracker.reset();
    }
    for (;;)
    {
      setState(State.READY);
      this.skCurrentRecognizer = null;
      return;
      label60:
      this.speechKitListener.onResults();
    }
  }
  
  private void processResults(List<DetailedResult> paramList)
  {
    MMLog.d("NVASpeechKit", "processResults called.");
    this._results = new Result[paramList.size()];
    int i = 0;
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      DetailedResult localDetailedResult = (DetailedResult)localIterator.next();
      Result[] arrayOfResult = this._results;
      int j = i + 1;
      arrayOfResult[i] = new Result(localDetailedResult.toString(), localDetailedResult.getConfidenceScore());
      i = j;
    }
  }
  
  private void releaseWebView()
  {
    if (getMMWebView() != null) {
      this.webViewRef.clear();
    }
  }
  
  private JSONArray resultsToJSON(Result[] paramArrayOfResult)
  {
    JSONArray localJSONArray = new JSONArray();
    int i = 0;
    while (i < paramArrayOfResult.length)
    {
      JSONObject localJSONObject = new JSONObject();
      try
      {
        localJSONObject.put("score", "" + paramArrayOfResult[i].getResultScore());
        localJSONObject.put("result", paramArrayOfResult[i].getResultString());
        localJSONArray.put(localJSONObject);
        i++;
      }
      catch (JSONException localJSONException)
      {
        MMLog.e("NVASpeechKit", "JSON creation error.", localJSONException);
        localJSONArray = null;
      }
    }
    return localJSONArray;
  }
  
  private void setState(State paramState)
  {
    try
    {
      MMLog.d("NVASpeechKit", "recording results returned. state=" + paramState);
      State localState = this.state;
      this.state = paramState;
      if ((this.speechKitListener != null) && (this.state != localState)) {
        this.speechKitListener.onStateChange(paramState);
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  private void startProgress(Recognizer paramRecognizer)
  {
    this.speeckKitHandler.removeCallbacks(this.audioLevelCallback);
    this.speeckKitHandler.postDelayed(this.audioLevelCallback, 50L);
  }
  
  private byte[] string2Byte(String paramString)
  {
    byte[] arrayOfByte;
    if (paramString == null) {
      arrayOfByte = null;
    }
    for (;;)
    {
      return arrayOfByte;
      arrayOfByte = new byte[paramString.length() / 2];
      for (int i = 0; i < arrayOfByte.length; i++) {
        arrayOfByte[i] = ((byte)Integer.parseInt(paramString.substring(i * 2, 2 + i * 2), 16));
      }
    }
  }
  
  void audioLevelChange(double paramDouble)
  {
    MMWebView localMMWebView = getMMWebView();
    if (localMMWebView != null) {
      localMMWebView.loadUrl("javascript:MMJS.sdk.audioLevelChange(" + paramDouble + ")");
    }
  }
  
  void backgroundAudioLevel(double paramDouble)
  {
    MMWebView localMMWebView = getMMWebView();
    if (localMMWebView != null) {
      localMMWebView.loadUrl("javascript:MMJS.sdk.backgroundAudioLevel(" + paramDouble + ")");
    }
  }
  
  public void cancelRecording()
  {
    if (this.skCurrentRecognizer != null)
    {
      MMLog.d("NVASpeechKit", "cancel RECORDING");
      this.skCurrentRecognizer.cancel();
      this.skCurrentRecognizer = null;
      setState(State.READY);
    }
  }
  
  public boolean endRecording()
  {
    if (this.skCurrentRecognizer != null)
    {
      MMLog.d("NVASpeechKit", "end RECORDING");
      this.skCurrentRecognizer.stopRecording();
      this.skCurrentRecognizer = null;
      return true;
    }
    return false;
  }
  
  String getNuanceId()
  {
    for (;;)
    {
      try
      {
        if (nuanceIdCache != null)
        {
          localObject2 = nuanceIdCache;
          return localObject2;
        }
        WeakReference localWeakReference = this.webViewRef;
        Context localContext = null;
        if (localWeakReference != null)
        {
          MMWebView localMMWebView = (MMWebView)this.webViewRef.get();
          localContext = null;
          if (localMMWebView != null) {
            localContext = localMMWebView.getContext();
          }
        }
        localObject2 = null;
        if (localContext != null)
        {
          str1 = Settings.Secure.getString(localContext.getContentResolver(), "android_id");
          localObject2 = null;
          if (str1 == null) {}
        }
      }
      finally
      {
        try
        {
          String str1;
          String str2 = MMSDK.byteArrayToString(MessageDigest.getInstance("SHA1").digest(str1.getBytes()));
          localObject2 = str2;
          nuanceIdCache = (String)localObject2;
        }
        catch (Exception localException)
        {
          MMLog.e("NVASpeechKit", "Problem with nuanceid", localException);
          Object localObject2 = null;
        }
        localObject1 = finally;
      }
    }
  }
  
  public Result[] getResults()
  {
    return this._results;
  }
  
  String getSessionId()
  {
    if (this.sk != null) {
      return this.sk.getSessionId();
    }
    return "";
  }
  
  public State getState()
  {
    try
    {
      State localState = this.state;
      return localState;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public boolean initialize(HandShake.NuanceCredentials paramNuanceCredentials, Context paramContext)
  {
    MMLog.d("NVASpeechKit", "initialize called.");
    if ((paramNuanceCredentials == null) || (paramContext == null)) {
      return false;
    }
    this._credentials = paramNuanceCredentials;
    if (this.sk != null) {}
    try
    {
      this.sk.connect();
      if (this.sk == null)
      {
        byte[] arrayOfByte = string2Byte(paramNuanceCredentials.appKey);
        MMLog.d("NVASpeechKit", paramNuanceCredentials.toString());
        this.sk = SpeechKit.initialize(paramContext, "1.0", paramNuanceCredentials.appID, paramNuanceCredentials.server, paramNuanceCredentials.port, false, arrayOfByte, SpeechKit.CmdSetType.NVC);
        this.skVocalListener = createVocalizerListener();
        this.skRecogListener = createRecognizerListener();
        this.speeckKitHandler = new Handler(Looper.getMainLooper());
        this.sk.connect();
        setState(State.READY);
        return true;
      }
    }
    catch (IllegalStateException localIllegalStateException)
    {
      for (;;)
      {
        this.sk = null;
      }
      MMLog.d("NVASpeechKit", "Already initialized. Skipping.");
    }
    return false;
  }
  
  public void logEvent()
  {
    if (this.sk == null) {
      return;
    }
    PdxValue.Dictionary localDictionary = new PdxValue.Dictionary();
    localDictionary.put("nva_ad_network_id", "MillenialMedia");
    localDictionary.put("nva_device_id", getNuanceId());
    localDictionary.put("nva_ad_publisher_id", this.packageName);
    String str1 = "";
    if ((this._credentials != null) && (!TextUtils.isEmpty(this._credentials.sessionID)))
    {
      str1 = this._credentials.sessionID;
      localDictionary.put("nva_ad_session_id", this._credentials.sessionID);
    }
    String str2 = getAdId();
    if (!TextUtils.isEmpty(str2)) {
      localDictionary.put("nva_ad_id", str2);
    }
    if (this.nuance_transaction_session_id != null)
    {
      localDictionary.put("nva_nvc_session_id", this.nuance_transaction_session_id);
      this.nuance_transaction_session_id = null;
    }
    for (;;)
    {
      MMLog.d("NVASpeechKit", "Sending log revision command to server. sessionId[" + this.sk.getSessionId() + "] deviceId[" + getNuanceId() + "] adId[" + str2 + "] mmSessionId[" + str1 + "]");
      this.sk.createLogRevisionCmd("NVA_LOG_EVENT", localDictionary, this.sk.getSessionId(), this.commandListener, this.speeckKitHandler).start();
      return;
      this.sk.getSessionId();
    }
  }
  
  void recognitionResult(String paramString)
  {
    MMWebView localMMWebView = getMMWebView();
    if (localMMWebView != null) {
      localMMWebView.loadUrl("javascript:MMJS.sdk.recognitionResult(" + paramString + ")");
    }
  }
  
  public void release()
  {
    MMLog.d("NVASpeechKit", "release called.");
    stopActions();
    cancelAudioLevelCallbacks();
    if (this.sk != null)
    {
      this.sk.release();
      setState(State.READY);
      this.sk = null;
    }
    this.pendingDataUploadCommand = null;
    releaseWebView();
  }
  
  public void setSpeechKitListener(Listener paramListener)
  {
    this.speechKitListener = paramListener;
  }
  
  public boolean startRecording(String paramString)
  {
    MMLog.d("NVASpeechKit", "RECORDING INVOKED.");
    if ((this.state == State.READY) && (this.sk != null))
    {
      this.nuance_transaction_session_id = null;
      this.skCurrentRecognizer = this.sk.createRecognizer("dictation", 1, paramString, this.skRecogListener, this.speeckKitHandler);
      MMLog.d("NVASpeechKit", "START RECORDING");
      this.skCurrentRecognizer.start();
      return true;
    }
    return false;
  }
  
  public void startSampleRecording()
  {
    this.audioLevelTracker.startTrackingAudioSample();
    startRecording("en_US");
  }
  
  public void stopActions()
  {
    if (this.sk != null) {}
    try
    {
      this.sk.cancelCurrent();
      return;
    }
    catch (Exception localException)
    {
      MMLog.e("NVASpeechKit", "No speech kit to disconnect.", localException);
    }
  }
  
  public boolean textToSpeech(String paramString1, String paramString2)
  {
    MMLog.d("NVASpeechKit", "TTS INVOKED.");
    if ((this.state == State.READY) && (this.sk != null))
    {
      this.skCurrentVocalizer = this.sk.createVocalizerWithLanguage(paramString2, this.skVocalListener, this.speeckKitHandler);
      this.skCurrentVocalizer.speakString(paramString1, this);
      return true;
    }
    return false;
  }
  
  public void updateCustomWords(CustomWordsOp paramCustomWordsOp, String[] paramArrayOfString)
  {
    if (this.sk == null) {
      return;
    }
    DataBlock localDataBlock = new DataBlock();
    StringBuilder localStringBuilder = new StringBuilder().append("Creating dataupload command and ");
    String str1;
    Data localData;
    if (paramCustomWordsOp == CustomWordsOp.Add)
    {
      str1 = "adding";
      MMLog.d("NVASpeechKit", str1 + " words.");
      localData = new Data("nva_custom_word_uploads", Data.DataType.CUSTOMWORDS);
      if (paramCustomWordsOp != CustomWordsOp.Add) {
        break label175;
      }
    }
    Action localAction;
    label175:
    for (Action.ActionType localActionType = Action.ActionType.ADD;; localActionType = Action.ActionType.REMOVE)
    {
      localAction = new Action(localActionType);
      int i = paramArrayOfString.length;
      for (int j = 0; j < i; j++)
      {
        String str2 = paramArrayOfString[j];
        localAction.addWord(str2);
        MMLog.d("NVASpeechKit", "\tword: '" + str2 + "'");
      }
      str1 = "deleting";
      break;
    }
    localData.addAction(localAction);
    localDataBlock.addData(localData);
    int k = localDataBlock.getChecksum();
    this.pendingDataUploadCommandType = paramCustomWordsOp;
    this.pendingDataUploadCommand = this.sk.createDataUploadCmd(localDataBlock, k, k, this.dataUploadListener, this.speeckKitHandler);
    this.pendingDataUploadCommand.start();
  }
  
  void voiceError(String paramString)
  {
    MMWebView localMMWebView = getMMWebView();
    if (localMMWebView != null) {
      localMMWebView.loadUrl("javascript:MMJS.sdk.voiceError('" + paramString + "')");
    }
  }
  
  void voiceStateChangeError()
  {
    MMWebView localMMWebView = getMMWebView();
    if (localMMWebView != null) {
      localMMWebView.loadUrl("javascript:MMJS.sdk.voiceStateChange('error')");
    }
  }
  
  void voiceStateChangeProcessing()
  {
    MMWebView localMMWebView = getMMWebView();
    if (localMMWebView != null) {
      localMMWebView.loadUrl("javascript:MMJS.sdk.voiceStateChange('processing')");
    }
  }
  
  void voiceStateChangeReady()
  {
    MMWebView localMMWebView = getMMWebView();
    if (localMMWebView != null) {
      localMMWebView.loadUrl("javascript:MMJS.sdk.voiceStateChange('ready')");
    }
  }
  
  void voiceStateChangeRecording()
  {
    MMWebView localMMWebView = getMMWebView();
    if (localMMWebView != null) {
      localMMWebView.loadUrl("javascript:MMJS.sdk.voiceStateChange('recording')");
    }
  }
  
  void voiceStateChangeVocalizing()
  {
    MMWebView localMMWebView = getMMWebView();
    if (localMMWebView != null) {
      localMMWebView.loadUrl("javascript:MMJS.sdk.voiceStateChange('vocalizing')");
    }
  }
  
  static class AudioLevelTracker
  {
    private static final double MAX = 80.0D;
    private static final double MIN = 40.0D;
    private static final double NORMALIZE_FACTOR = 4.004004004004004D;
    private static final double SCALE = 9.99D;
    double audioLevel;
    int audioLevelCount;
    double averageLevel;
    boolean isTrackingAudioSample;
    
    public AudioLevelTracker()
    {
      reset();
    }
    
    private static double normalize(double paramDouble)
    {
      return Math.min(9.99D, Math.max(Math.floor(paramDouble - 40.0D) / 4.004004004004004D, 0.0D));
    }
    
    public boolean isTrackingAudioSample()
    {
      return this.isTrackingAudioSample;
    }
    
    public void reset()
    {
      this.averageLevel = 0.0D;
      this.audioLevelCount = 0;
      this.isTrackingAudioSample = false;
    }
    
    public void startTrackingAudioSample()
    {
      reset();
      this.isTrackingAudioSample = true;
    }
    
    public boolean update(double paramDouble)
    {
      double d1 = this.averageLevel;
      double d2 = this.audioLevel;
      this.audioLevel = paramDouble;
      this.audioLevelCount = (1 + this.audioLevelCount);
      this.averageLevel = ((paramDouble + d1 * (-1 + this.audioLevelCount)) / this.audioLevelCount);
      if (this.isTrackingAudioSample) {}
      while (this.audioLevel == d2) {
        return false;
      }
      return true;
    }
  }
  
  public static enum CustomWordsOp
  {
    static
    {
      CustomWordsOp[] arrayOfCustomWordsOp = new CustomWordsOp[2];
      arrayOfCustomWordsOp[0] = Add;
      arrayOfCustomWordsOp[1] = Remove;
      $VALUES = arrayOfCustomWordsOp;
    }
    
    private CustomWordsOp() {}
  }
  
  public static abstract interface Listener
  {
    public abstract void onAudioLevelUpdate(double paramDouble);
    
    public abstract void onAudioSampleUpdate(double paramDouble);
    
    public abstract void onCustomWordsAdded();
    
    public abstract void onCustomWordsDeleted();
    
    public abstract void onError();
    
    public abstract void onResults();
    
    public abstract void onStateChange(NVASpeechKit.State paramState);
  }
  
  public class Result
  {
    public final int resultScore;
    public final String resultString;
    
    public Result(String paramString, double paramDouble)
    {
      this.resultString = paramString;
      this.resultScore = ((int)paramDouble);
    }
    
    public int getResultScore()
    {
      return this.resultScore;
    }
    
    public String getResultString()
    {
      return this.resultString;
    }
  }
  
  public static enum State
  {
    private String name;
    
    static
    {
      RECORDING = new State("RECORDING", 2, "recording");
      READY = new State("READY", 3, "ready");
      PROCESSING = new State("PROCESSING", 4, "processing");
      State[] arrayOfState = new State[5];
      arrayOfState[0] = ERROR;
      arrayOfState[1] = VOCALIZING;
      arrayOfState[2] = RECORDING;
      arrayOfState[3] = READY;
      arrayOfState[4] = PROCESSING;
      $VALUES = arrayOfState;
    }
    
    private State(String paramString)
    {
      this.name = paramString;
    }
    
    public String toString()
    {
      return this.name;
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.NVASpeechKit
 * JD-Core Version:    0.7.0.1
 */