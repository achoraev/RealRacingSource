package com.facebook.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.app.Fragment;
import com.facebook.AppEventsLogger;
import com.facebook.FacebookException;
import com.facebook.FacebookGraphObjectException;
import com.facebook.NativeAppCallAttachmentStore;
import com.facebook.NativeAppCallContentProvider;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import com.facebook.model.GraphObject;
import com.facebook.model.GraphObject.Factory;
import com.facebook.model.GraphObjectList;
import com.facebook.model.OpenGraphAction;
import com.facebook.model.OpenGraphObject;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FacebookDialog
{
  public static final String COMPLETION_GESTURE_CANCEL = "cancel";
  private static final String EXTRA_DIALOG_COMPLETE_KEY = "com.facebook.platform.extra.DID_COMPLETE";
  private static final String EXTRA_DIALOG_COMPLETION_GESTURE_KEY = "com.facebook.platform.extra.COMPLETION_GESTURE";
  private static final String EXTRA_DIALOG_COMPLETION_ID_KEY = "com.facebook.platform.extra.POST_ID";
  private static NativeAppCallAttachmentStore attachmentStore;
  private Activity activity;
  private PendingCall appCall;
  private Fragment fragment;
  private OnPresentCallback onPresentCallback;
  
  private FacebookDialog(Activity paramActivity, Fragment paramFragment, PendingCall paramPendingCall, OnPresentCallback paramOnPresentCallback)
  {
    this.activity = paramActivity;
    this.fragment = paramFragment;
    this.appCall = paramPendingCall;
    this.onPresentCallback = paramOnPresentCallback;
  }
  
  public static boolean canPresentMessageDialog(Context paramContext, MessageDialogFeature... paramVarArgs)
  {
    return handleCanPresent(paramContext, EnumSet.of(MessageDialogFeature.MESSAGE_DIALOG, paramVarArgs));
  }
  
  public static boolean canPresentOpenGraphActionDialog(Context paramContext, OpenGraphActionDialogFeature... paramVarArgs)
  {
    return handleCanPresent(paramContext, EnumSet.of(OpenGraphActionDialogFeature.OG_ACTION_DIALOG, paramVarArgs));
  }
  
  public static boolean canPresentOpenGraphMessageDialog(Context paramContext, OpenGraphMessageDialogFeature... paramVarArgs)
  {
    return handleCanPresent(paramContext, EnumSet.of(OpenGraphMessageDialogFeature.OG_MESSAGE_DIALOG, paramVarArgs));
  }
  
  public static boolean canPresentShareDialog(Context paramContext, ShareDialogFeature... paramVarArgs)
  {
    return handleCanPresent(paramContext, EnumSet.of(ShareDialogFeature.SHARE_DIALOG, paramVarArgs));
  }
  
  private static String getActionForFeatures(Iterable<? extends DialogFeature> paramIterable)
  {
    Iterator localIterator = paramIterable.iterator();
    boolean bool = localIterator.hasNext();
    String str = null;
    if (bool) {
      str = ((DialogFeature)localIterator.next()).getAction();
    }
    return str;
  }
  
  private static NativeAppCallAttachmentStore getAttachmentStore()
  {
    if (attachmentStore == null) {
      attachmentStore = new NativeAppCallAttachmentStore();
    }
    return attachmentStore;
  }
  
  private static String getEventName(Intent paramIntent)
  {
    return getEventName(paramIntent.getStringExtra("com.facebook.platform.protocol.PROTOCOL_ACTION"), paramIntent.hasExtra("com.facebook.platform.extra.PHOTOS"));
  }
  
  private static String getEventName(String paramString, boolean paramBoolean)
  {
    if (paramString.equals("com.facebook.platform.action.request.FEED_DIALOG"))
    {
      if (paramBoolean) {
        return "fb_dialogs_present_share_photo";
      }
      return "fb_dialogs_present_share";
    }
    if (paramString.equals("com.facebook.platform.action.request.MESSAGE_DIALOG"))
    {
      if (paramBoolean) {
        return "fb_dialogs_present_message_photo";
      }
      return "fb_dialogs_present_message";
    }
    if (paramString.equals("com.facebook.platform.action.request.OGACTIONPUBLISH_DIALOG")) {
      return "fb_dialogs_present_share_og";
    }
    if (paramString.equals("com.facebook.platform.action.request.OGMESSAGEPUBLISH_DIALOG")) {
      return "fb_dialogs_present_message_og";
    }
    throw new FacebookException("An unspecified action was presented");
  }
  
  private static int getMinVersionForFeatures(Iterable<? extends DialogFeature> paramIterable)
  {
    int i = -2147483648;
    Iterator localIterator = paramIterable.iterator();
    while (localIterator.hasNext()) {
      i = Math.max(i, ((DialogFeature)localIterator.next()).getMinVersion());
    }
    return i;
  }
  
  public static String getNativeDialogCompletionGesture(Bundle paramBundle)
  {
    return paramBundle.getString("com.facebook.platform.extra.COMPLETION_GESTURE");
  }
  
  public static boolean getNativeDialogDidComplete(Bundle paramBundle)
  {
    return paramBundle.getBoolean("com.facebook.platform.extra.DID_COMPLETE", false);
  }
  
  public static String getNativeDialogPostId(Bundle paramBundle)
  {
    return paramBundle.getString("com.facebook.platform.extra.POST_ID");
  }
  
  private static int getProtocolVersionForNativeDialog(Context paramContext, String paramString, int paramInt)
  {
    return NativeProtocol.getLatestAvailableProtocolVersionForAction(paramContext, paramString, paramInt);
  }
  
  public static boolean handleActivityResult(Context paramContext, PendingCall paramPendingCall, int paramInt, Intent paramIntent, Callback paramCallback)
  {
    if (paramInt != paramPendingCall.getRequestCode()) {
      return false;
    }
    if (attachmentStore != null) {
      attachmentStore.cleanupAttachmentsForCall(paramContext, paramPendingCall.getCallId());
    }
    if (paramCallback != null)
    {
      if (!NativeProtocol.isErrorResult(paramIntent)) {
        break label57;
      }
      paramCallback.onError(paramPendingCall, NativeProtocol.getErrorFromResult(paramIntent), paramIntent.getExtras());
    }
    for (;;)
    {
      return true;
      label57:
      paramCallback.onComplete(paramPendingCall, NativeProtocol.getSuccessResultsFromIntent(paramIntent));
    }
  }
  
  private static boolean handleCanPresent(Context paramContext, Iterable<? extends DialogFeature> paramIterable)
  {
    return getProtocolVersionForNativeDialog(paramContext, getActionForFeatures(paramIterable), getMinVersionForFeatures(paramIterable)) != -1;
  }
  
  private static void logDialogActivity(Activity paramActivity, Fragment paramFragment, String paramString1, String paramString2)
  {
    if (paramFragment != null) {
      paramActivity = paramFragment.getActivity();
    }
    AppEventsLogger localAppEventsLogger = AppEventsLogger.newLogger(paramActivity);
    Bundle localBundle = new Bundle();
    localBundle.putString("fb_dialog_outcome", paramString2);
    localAppEventsLogger.logSdkEvent(paramString1, null, localBundle);
  }
  
  public PendingCall present()
  {
    logDialogActivity(this.activity, this.fragment, getEventName(this.appCall.getRequestIntent()), "Completed");
    if (this.onPresentCallback != null) {}
    for (;;)
    {
      try
      {
        this.onPresentCallback.onPresent(this.activity);
        if (this.fragment != null)
        {
          this.fragment.startActivityForResult(this.appCall.getRequestIntent(), this.appCall.getRequestCode());
          return this.appCall;
        }
      }
      catch (Exception localException)
      {
        throw new FacebookException(localException);
      }
      this.activity.startActivityForResult(this.appCall.getRequestIntent(), this.appCall.getRequestCode());
    }
  }
  
  public static abstract class Builder<CONCRETE extends Builder<?>>
  {
    protected final Activity activity;
    protected final FacebookDialog.PendingCall appCall;
    protected final String applicationId;
    protected String applicationName;
    protected Fragment fragment;
    protected HashMap<String, File> imageAttachmentFiles = new HashMap();
    protected HashMap<String, Bitmap> imageAttachments = new HashMap();
    
    public Builder(Activity paramActivity)
    {
      Validate.notNull(paramActivity, "activity");
      this.activity = paramActivity;
      this.applicationId = Utility.getMetadataApplicationId(paramActivity);
      this.appCall = new FacebookDialog.PendingCall(64207);
    }
    
    protected CONCRETE addImageAttachment(String paramString, Bitmap paramBitmap)
    {
      this.imageAttachments.put(paramString, paramBitmap);
      return this;
    }
    
    protected CONCRETE addImageAttachment(String paramString, File paramFile)
    {
      this.imageAttachmentFiles.put(paramString, paramFile);
      return this;
    }
    
    protected List<String> addImageAttachmentFiles(Collection<File> paramCollection)
    {
      ArrayList localArrayList = new ArrayList();
      Iterator localIterator = paramCollection.iterator();
      while (localIterator.hasNext())
      {
        File localFile = (File)localIterator.next();
        String str = UUID.randomUUID().toString();
        addImageAttachment(str, localFile);
        localArrayList.add(NativeAppCallContentProvider.getAttachmentUrl(this.applicationId, this.appCall.getCallId(), str));
      }
      return localArrayList;
    }
    
    protected List<String> addImageAttachments(Collection<Bitmap> paramCollection)
    {
      ArrayList localArrayList = new ArrayList();
      Iterator localIterator = paramCollection.iterator();
      while (localIterator.hasNext())
      {
        Bitmap localBitmap = (Bitmap)localIterator.next();
        String str = UUID.randomUUID().toString();
        addImageAttachment(str, localBitmap);
        localArrayList.add(NativeAppCallContentProvider.getAttachmentUrl(this.applicationId, this.appCall.getCallId(), str));
      }
      return localArrayList;
    }
    
    public FacebookDialog build()
    {
      validate();
      String str = FacebookDialog.getActionForFeatures(getDialogFeatures());
      int i = FacebookDialog.getProtocolVersionForNativeDialog(this.activity, str, FacebookDialog.access$200(getDialogFeatures()));
      if (NativeProtocol.isVersionCompatibleWithBucketedIntent(i)) {}
      Intent localIntent;
      for (Bundle localBundle = getMethodArguments();; localBundle = setBundleExtras(new Bundle()))
      {
        localIntent = NativeProtocol.createPlatformActivityIntent(this.activity, this.appCall.getCallId().toString(), str, i, this.applicationName, localBundle);
        if (localIntent != null) {
          break;
        }
        FacebookDialog.logDialogActivity(this.activity, this.fragment, FacebookDialog.access$400(str, localBundle.containsKey("com.facebook.platform.extra.PHOTOS")), "Failed");
        throw new FacebookException("Unable to create Intent; this likely means the Facebook app is not installed.");
      }
      FacebookDialog.PendingCall.access$600(this.appCall, localIntent);
      return new FacebookDialog(this.activity, this.fragment, this.appCall, getOnPresentCallback(), null);
    }
    
    public boolean canPresent()
    {
      return FacebookDialog.handleCanPresent(this.activity, getDialogFeatures());
    }
    
    abstract EnumSet<? extends FacebookDialog.DialogFeature> getDialogFeatures();
    
    List<String> getImageAttachmentNames()
    {
      return new ArrayList(this.imageAttachments.keySet());
    }
    
    protected abstract Bundle getMethodArguments();
    
    FacebookDialog.OnPresentCallback getOnPresentCallback()
    {
      new FacebookDialog.OnPresentCallback()
      {
        public void onPresent(Context paramAnonymousContext)
          throws Exception
        {
          if ((FacebookDialog.Builder.this.imageAttachments != null) && (FacebookDialog.Builder.this.imageAttachments.size() > 0)) {
            FacebookDialog.access$900().addAttachmentsForCall(paramAnonymousContext, FacebookDialog.Builder.this.appCall.getCallId(), FacebookDialog.Builder.this.imageAttachments);
          }
          if ((FacebookDialog.Builder.this.imageAttachmentFiles != null) && (FacebookDialog.Builder.this.imageAttachmentFiles.size() > 0)) {
            FacebookDialog.access$900().addAttachmentFilesForCall(paramAnonymousContext, FacebookDialog.Builder.this.appCall.getCallId(), FacebookDialog.Builder.this.imageAttachmentFiles);
          }
        }
      };
    }
    
    protected void putExtra(Bundle paramBundle, String paramString1, String paramString2)
    {
      if (paramString2 != null) {
        paramBundle.putString(paramString1, paramString2);
      }
    }
    
    public CONCRETE setApplicationName(String paramString)
    {
      this.applicationName = paramString;
      return this;
    }
    
    protected abstract Bundle setBundleExtras(Bundle paramBundle);
    
    public CONCRETE setFragment(Fragment paramFragment)
    {
      this.fragment = paramFragment;
      return this;
    }
    
    public CONCRETE setRequestCode(int paramInt)
    {
      FacebookDialog.PendingCall.access$000(this.appCall, paramInt);
      return this;
    }
    
    void validate() {}
  }
  
  public static abstract interface Callback
  {
    public abstract void onComplete(FacebookDialog.PendingCall paramPendingCall, Bundle paramBundle);
    
    public abstract void onError(FacebookDialog.PendingCall paramPendingCall, Exception paramException, Bundle paramBundle);
  }
  
  private static abstract interface DialogFeature
  {
    public abstract String getAction();
    
    public abstract int getMinVersion();
  }
  
  public static class MessageDialogBuilder
    extends FacebookDialog.ShareDialogBuilderBase<MessageDialogBuilder>
  {
    public MessageDialogBuilder(Activity paramActivity)
    {
      super();
    }
    
    EnumSet<? extends FacebookDialog.DialogFeature> getDialogFeatures()
    {
      return EnumSet.of(FacebookDialog.MessageDialogFeature.MESSAGE_DIALOG);
    }
  }
  
  public static enum MessageDialogFeature
    implements FacebookDialog.DialogFeature
  {
    private int minVersion;
    
    static
    {
      MessageDialogFeature[] arrayOfMessageDialogFeature = new MessageDialogFeature[2];
      arrayOfMessageDialogFeature[0] = MESSAGE_DIALOG;
      arrayOfMessageDialogFeature[1] = PHOTOS;
      $VALUES = arrayOfMessageDialogFeature;
    }
    
    private MessageDialogFeature(int paramInt)
    {
      this.minVersion = paramInt;
    }
    
    public String getAction()
    {
      return "com.facebook.platform.action.request.MESSAGE_DIALOG";
    }
    
    public int getMinVersion()
    {
      return this.minVersion;
    }
  }
  
  static abstract interface OnPresentCallback
  {
    public abstract void onPresent(Context paramContext)
      throws Exception;
  }
  
  public static class OpenGraphActionDialogBuilder
    extends FacebookDialog.OpenGraphDialogBuilderBase<OpenGraphActionDialogBuilder>
  {
    public OpenGraphActionDialogBuilder(Activity paramActivity, OpenGraphAction paramOpenGraphAction, String paramString)
    {
      super(paramOpenGraphAction, paramString);
    }
    
    @Deprecated
    public OpenGraphActionDialogBuilder(Activity paramActivity, OpenGraphAction paramOpenGraphAction, String paramString1, String paramString2)
    {
      super(paramOpenGraphAction, paramString1, paramString2);
    }
    
    EnumSet<? extends FacebookDialog.DialogFeature> getDialogFeatures()
    {
      return EnumSet.of(FacebookDialog.OpenGraphActionDialogFeature.OG_ACTION_DIALOG);
    }
  }
  
  public static enum OpenGraphActionDialogFeature
    implements FacebookDialog.DialogFeature
  {
    private int minVersion;
    
    static
    {
      OpenGraphActionDialogFeature[] arrayOfOpenGraphActionDialogFeature = new OpenGraphActionDialogFeature[1];
      arrayOfOpenGraphActionDialogFeature[0] = OG_ACTION_DIALOG;
      $VALUES = arrayOfOpenGraphActionDialogFeature;
    }
    
    private OpenGraphActionDialogFeature(int paramInt)
    {
      this.minVersion = paramInt;
    }
    
    public String getAction()
    {
      return "com.facebook.platform.action.request.OGACTIONPUBLISH_DIALOG";
    }
    
    public int getMinVersion()
    {
      return this.minVersion;
    }
  }
  
  private static abstract class OpenGraphDialogBuilderBase<CONCRETE extends OpenGraphDialogBuilderBase<?>>
    extends FacebookDialog.Builder<CONCRETE>
  {
    private OpenGraphAction action;
    private String actionType;
    private boolean dataErrorsFatal;
    private String previewPropertyName;
    
    public OpenGraphDialogBuilderBase(Activity paramActivity, OpenGraphAction paramOpenGraphAction, String paramString)
    {
      super();
      Validate.notNull(paramOpenGraphAction, "action");
      Validate.notNullOrEmpty(paramOpenGraphAction.getType(), "action.getType()");
      Validate.notNullOrEmpty(paramString, "previewPropertyName");
      if (paramOpenGraphAction.getProperty(paramString) == null) {
        throw new IllegalArgumentException("A property named \"" + paramString + "\" was not found on the action.  The name of " + "the preview property must match the name of an action property.");
      }
      this.action = paramOpenGraphAction;
      this.actionType = paramOpenGraphAction.getType();
      this.previewPropertyName = paramString;
    }
    
    @Deprecated
    public OpenGraphDialogBuilderBase(Activity paramActivity, OpenGraphAction paramOpenGraphAction, String paramString1, String paramString2)
    {
      super();
      Validate.notNull(paramOpenGraphAction, "action");
      Validate.notNullOrEmpty(paramString1, "actionType");
      Validate.notNullOrEmpty(paramString2, "previewPropertyName");
      if (paramOpenGraphAction.getProperty(paramString2) == null) {
        throw new IllegalArgumentException("A property named \"" + paramString2 + "\" was not found on the action.  The name of " + "the preview property must match the name of an action property.");
      }
      String str = paramOpenGraphAction.getType();
      if ((!Utility.isNullOrEmpty(str)) && (!str.equals(paramString1))) {
        throw new IllegalArgumentException("'actionType' must match the type of 'action' if it is specified. Consider using OpenGraphDialogBuilderBase(Activity activity, OpenGraphAction action, String previewPropertyName) instead.");
      }
      this.action = paramOpenGraphAction;
      this.actionType = paramString1;
      this.previewPropertyName = paramString2;
    }
    
    private JSONObject flattenChildrenOfGraphObject(JSONObject paramJSONObject)
    {
      try
      {
        localJSONObject = new JSONObject(paramJSONObject.toString());
        try
        {
          Iterator localIterator = localJSONObject.keys();
          while (localIterator.hasNext())
          {
            String str = (String)localIterator.next();
            if (!str.equalsIgnoreCase("image"))
            {
              localJSONObject.put(str, flattenObject(localJSONObject.get(str)));
              continue;
              throw new FacebookException(localJSONException1);
            }
          }
        }
        catch (JSONException localJSONException1) {}
      }
      catch (JSONException localJSONException2)
      {
        JSONObject localJSONObject;
        label71:
        break label71;
      }
      return localJSONObject;
    }
    
    private Object flattenObject(Object paramObject)
      throws JSONException
    {
      if (paramObject == null) {
        paramObject = null;
      }
      do
      {
        JSONObject localJSONObject;
        do
        {
          do
          {
            return paramObject;
            if (!(paramObject instanceof JSONObject)) {
              break;
            }
            localJSONObject = (JSONObject)paramObject;
          } while (localJSONObject.optBoolean("fbsdk:create_object"));
          if (localJSONObject.has("id")) {
            return localJSONObject.getString("id");
          }
        } while (!localJSONObject.has("url"));
        return localJSONObject.getString("url");
      } while (!(paramObject instanceof JSONArray));
      JSONArray localJSONArray1 = (JSONArray)paramObject;
      JSONArray localJSONArray2 = new JSONArray();
      int i = localJSONArray1.length();
      for (int j = 0; j < i; j++) {
        localJSONArray2.put(flattenObject(localJSONArray1.get(j)));
      }
      return localJSONArray2;
    }
    
    private void updateActionAttachmentUrls(List<String> paramList, boolean paramBoolean)
    {
      Object localObject = this.action.getImage();
      if (localObject == null) {
        localObject = new ArrayList(paramList.size());
      }
      Iterator localIterator = paramList.iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        JSONObject localJSONObject = new JSONObject();
        try
        {
          localJSONObject.put("url", str);
          if (paramBoolean) {
            localJSONObject.put("user_generated", true);
          }
          ((List)localObject).add(localJSONObject);
        }
        catch (JSONException localJSONException)
        {
          throw new FacebookException("Unable to attach images", localJSONException);
        }
      }
      this.action.setImage((List)localObject);
    }
    
    protected Bundle getMethodArguments()
    {
      Bundle localBundle = new Bundle();
      putExtra(localBundle, "PREVIEW_PROPERTY_NAME", this.previewPropertyName);
      putExtra(localBundle, "ACTION_TYPE", this.actionType);
      localBundle.putBoolean("DATA_FAILURES_FATAL", this.dataErrorsFatal);
      putExtra(localBundle, "ACTION", flattenChildrenOfGraphObject(this.action.getInnerJSONObject()).toString());
      return localBundle;
    }
    
    protected Bundle setBundleExtras(Bundle paramBundle)
    {
      putExtra(paramBundle, "com.facebook.platform.extra.PREVIEW_PROPERTY_NAME", this.previewPropertyName);
      putExtra(paramBundle, "com.facebook.platform.extra.ACTION_TYPE", this.actionType);
      paramBundle.putBoolean("com.facebook.platform.extra.DATA_FAILURES_FATAL", this.dataErrorsFatal);
      putExtra(paramBundle, "com.facebook.platform.extra.ACTION", flattenChildrenOfGraphObject(this.action.getInnerJSONObject()).toString());
      return paramBundle;
    }
    
    public CONCRETE setDataErrorsFatal(boolean paramBoolean)
    {
      this.dataErrorsFatal = paramBoolean;
      return this;
    }
    
    public CONCRETE setImageAttachmentFilesForAction(List<File> paramList)
    {
      return setImageAttachmentFilesForAction(paramList, false);
    }
    
    public CONCRETE setImageAttachmentFilesForAction(List<File> paramList, boolean paramBoolean)
    {
      Validate.containsNoNulls(paramList, "bitmapFiles");
      if (this.action == null) {
        throw new FacebookException("Can not set attachments prior to setting action.");
      }
      updateActionAttachmentUrls(addImageAttachmentFiles(paramList), paramBoolean);
      return this;
    }
    
    public CONCRETE setImageAttachmentFilesForObject(String paramString, List<File> paramList)
    {
      return setImageAttachmentFilesForObject(paramString, paramList, false);
    }
    
    public CONCRETE setImageAttachmentFilesForObject(String paramString, List<File> paramList, boolean paramBoolean)
    {
      Validate.notNull(paramString, "objectProperty");
      Validate.containsNoNulls(paramList, "bitmapFiles");
      if (this.action == null) {
        throw new FacebookException("Can not set attachments prior to setting action.");
      }
      updateObjectAttachmentUrls(paramString, addImageAttachmentFiles(paramList), paramBoolean);
      return this;
    }
    
    public CONCRETE setImageAttachmentsForAction(List<Bitmap> paramList)
    {
      return setImageAttachmentsForAction(paramList, false);
    }
    
    public CONCRETE setImageAttachmentsForAction(List<Bitmap> paramList, boolean paramBoolean)
    {
      Validate.containsNoNulls(paramList, "bitmaps");
      if (this.action == null) {
        throw new FacebookException("Can not set attachments prior to setting action.");
      }
      updateActionAttachmentUrls(addImageAttachments(paramList), paramBoolean);
      return this;
    }
    
    public CONCRETE setImageAttachmentsForObject(String paramString, List<Bitmap> paramList)
    {
      return setImageAttachmentsForObject(paramString, paramList, false);
    }
    
    public CONCRETE setImageAttachmentsForObject(String paramString, List<Bitmap> paramList, boolean paramBoolean)
    {
      Validate.notNull(paramString, "objectProperty");
      Validate.containsNoNulls(paramList, "bitmaps");
      if (this.action == null) {
        throw new FacebookException("Can not set attachments prior to setting action.");
      }
      updateObjectAttachmentUrls(paramString, addImageAttachments(paramList), paramBoolean);
      return this;
    }
    
    void updateObjectAttachmentUrls(String paramString, List<String> paramList, boolean paramBoolean)
    {
      OpenGraphObject localOpenGraphObject;
      try
      {
        localOpenGraphObject = (OpenGraphObject)this.action.getPropertyAs(paramString, OpenGraphObject.class);
        if (localOpenGraphObject == null) {
          throw new IllegalArgumentException("Action does not contain a property '" + paramString + "'");
        }
      }
      catch (FacebookGraphObjectException localFacebookGraphObjectException)
      {
        throw new IllegalArgumentException("Property '" + paramString + "' is not a graph object");
      }
      if (!localOpenGraphObject.getCreateObject()) {
        throw new IllegalArgumentException("The Open Graph object in '" + paramString + "' is not marked for creation");
      }
      GraphObjectList localGraphObjectList = localOpenGraphObject.getImage();
      if (localGraphObjectList == null) {
        localGraphObjectList = GraphObject.Factory.createList(GraphObject.class);
      }
      Iterator localIterator = paramList.iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        GraphObject localGraphObject = GraphObject.Factory.create();
        localGraphObject.setProperty("url", str);
        if (paramBoolean) {
          localGraphObject.setProperty("user_generated", Boolean.valueOf(true));
        }
        localGraphObjectList.add(localGraphObject);
      }
      localOpenGraphObject.setImage(localGraphObjectList);
    }
  }
  
  public static class OpenGraphMessageDialogBuilder
    extends FacebookDialog.OpenGraphDialogBuilderBase<OpenGraphMessageDialogBuilder>
  {
    public OpenGraphMessageDialogBuilder(Activity paramActivity, OpenGraphAction paramOpenGraphAction, String paramString)
    {
      super(paramOpenGraphAction, paramString);
    }
    
    EnumSet<? extends FacebookDialog.DialogFeature> getDialogFeatures()
    {
      return EnumSet.of(FacebookDialog.OpenGraphMessageDialogFeature.OG_MESSAGE_DIALOG);
    }
  }
  
  public static enum OpenGraphMessageDialogFeature
    implements FacebookDialog.DialogFeature
  {
    private int minVersion;
    
    static
    {
      OpenGraphMessageDialogFeature[] arrayOfOpenGraphMessageDialogFeature = new OpenGraphMessageDialogFeature[1];
      arrayOfOpenGraphMessageDialogFeature[0] = OG_MESSAGE_DIALOG;
      $VALUES = arrayOfOpenGraphMessageDialogFeature;
    }
    
    private OpenGraphMessageDialogFeature(int paramInt)
    {
      this.minVersion = paramInt;
    }
    
    public String getAction()
    {
      return "com.facebook.platform.action.request.OGMESSAGEPUBLISH_DIALOG";
    }
    
    public int getMinVersion()
    {
      return this.minVersion;
    }
  }
  
  public static class PendingCall
    implements Parcelable
  {
    public static final Parcelable.Creator<PendingCall> CREATOR = new Parcelable.Creator()
    {
      public FacebookDialog.PendingCall createFromParcel(Parcel paramAnonymousParcel)
      {
        return new FacebookDialog.PendingCall(paramAnonymousParcel, null);
      }
      
      public FacebookDialog.PendingCall[] newArray(int paramAnonymousInt)
      {
        return new FacebookDialog.PendingCall[paramAnonymousInt];
      }
    };
    private UUID callId;
    private int requestCode;
    private Intent requestIntent;
    
    public PendingCall(int paramInt)
    {
      this.callId = UUID.randomUUID();
      this.requestCode = paramInt;
    }
    
    private PendingCall(Parcel paramParcel)
    {
      this.callId = UUID.fromString(paramParcel.readString());
      this.requestIntent = ((Intent)paramParcel.readParcelable(null));
      this.requestCode = paramParcel.readInt();
    }
    
    private void setRequestCode(int paramInt)
    {
      this.requestCode = paramInt;
    }
    
    private void setRequestIntent(Intent paramIntent)
    {
      this.requestIntent = paramIntent;
    }
    
    public int describeContents()
    {
      return 0;
    }
    
    public UUID getCallId()
    {
      return this.callId;
    }
    
    public int getRequestCode()
    {
      return this.requestCode;
    }
    
    public Intent getRequestIntent()
    {
      return this.requestIntent;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      paramParcel.writeString(this.callId.toString());
      paramParcel.writeParcelable(this.requestIntent, 0);
      paramParcel.writeInt(this.requestCode);
    }
  }
  
  private static abstract class PhotoDialogBuilderBase<CONCRETE extends PhotoDialogBuilderBase<?>>
    extends FacebookDialog.Builder<CONCRETE>
  {
    static int MAXIMUM_PHOTO_COUNT = 6;
    private ArrayList<String> friends;
    private ArrayList<String> imageAttachmentUrls = new ArrayList();
    private String place;
    
    public PhotoDialogBuilderBase(Activity paramActivity)
    {
      super();
    }
    
    public CONCRETE addPhotoFiles(Collection<File> paramCollection)
    {
      this.imageAttachmentUrls.addAll(addImageAttachmentFiles(paramCollection));
      return this;
    }
    
    public CONCRETE addPhotos(Collection<Bitmap> paramCollection)
    {
      this.imageAttachmentUrls.addAll(addImageAttachments(paramCollection));
      return this;
    }
    
    abstract int getMaximumNumberOfPhotos();
    
    protected Bundle getMethodArguments()
    {
      Bundle localBundle = new Bundle();
      putExtra(localBundle, "PLACE", this.place);
      localBundle.putStringArrayList("PHOTOS", this.imageAttachmentUrls);
      if (!Utility.isNullOrEmpty(this.friends)) {
        localBundle.putStringArrayList("FRIENDS", this.friends);
      }
      return localBundle;
    }
    
    protected Bundle setBundleExtras(Bundle paramBundle)
    {
      putExtra(paramBundle, "com.facebook.platform.extra.APPLICATION_ID", this.applicationId);
      putExtra(paramBundle, "com.facebook.platform.extra.APPLICATION_NAME", this.applicationName);
      putExtra(paramBundle, "com.facebook.platform.extra.PLACE", this.place);
      paramBundle.putStringArrayList("com.facebook.platform.extra.PHOTOS", this.imageAttachmentUrls);
      if (!Utility.isNullOrEmpty(this.friends)) {
        paramBundle.putStringArrayList("com.facebook.platform.extra.FRIENDS", this.friends);
      }
      return paramBundle;
    }
    
    public CONCRETE setFriends(List<String> paramList)
    {
      this.friends = new ArrayList(paramList);
      return this;
    }
    
    public CONCRETE setPlace(String paramString)
    {
      this.place = paramString;
      return this;
    }
    
    void validate()
    {
      super.validate();
      if (this.imageAttachmentUrls.isEmpty()) {
        throw new FacebookException("Must specify at least one photo.");
      }
      if (this.imageAttachmentUrls.size() > getMaximumNumberOfPhotos())
      {
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Integer.valueOf(getMaximumNumberOfPhotos());
        throw new FacebookException(String.format("Cannot add more than %d photos.", arrayOfObject));
      }
    }
  }
  
  public static class PhotoMessageDialogBuilder
    extends FacebookDialog.PhotoDialogBuilderBase<PhotoMessageDialogBuilder>
  {
    public PhotoMessageDialogBuilder(Activity paramActivity)
    {
      super();
    }
    
    EnumSet<? extends FacebookDialog.DialogFeature> getDialogFeatures()
    {
      return EnumSet.of(FacebookDialog.MessageDialogFeature.MESSAGE_DIALOG, FacebookDialog.MessageDialogFeature.PHOTOS);
    }
    
    int getMaximumNumberOfPhotos()
    {
      return MAXIMUM_PHOTO_COUNT;
    }
  }
  
  public static class PhotoShareDialogBuilder
    extends FacebookDialog.PhotoDialogBuilderBase<PhotoShareDialogBuilder>
  {
    public PhotoShareDialogBuilder(Activity paramActivity)
    {
      super();
    }
    
    EnumSet<? extends FacebookDialog.DialogFeature> getDialogFeatures()
    {
      return EnumSet.of(FacebookDialog.ShareDialogFeature.SHARE_DIALOG, FacebookDialog.ShareDialogFeature.PHOTOS);
    }
    
    int getMaximumNumberOfPhotos()
    {
      return MAXIMUM_PHOTO_COUNT;
    }
  }
  
  public static class ShareDialogBuilder
    extends FacebookDialog.ShareDialogBuilderBase<ShareDialogBuilder>
  {
    public ShareDialogBuilder(Activity paramActivity)
    {
      super();
    }
    
    EnumSet<? extends FacebookDialog.DialogFeature> getDialogFeatures()
    {
      return EnumSet.of(FacebookDialog.ShareDialogFeature.SHARE_DIALOG);
    }
  }
  
  private static abstract class ShareDialogBuilderBase<CONCRETE extends ShareDialogBuilderBase<?>>
    extends FacebookDialog.Builder<CONCRETE>
  {
    private String caption;
    private boolean dataErrorsFatal;
    private String description;
    private ArrayList<String> friends;
    protected String link;
    private String name;
    private String picture;
    private String place;
    private String ref;
    
    public ShareDialogBuilderBase(Activity paramActivity)
    {
      super();
    }
    
    protected Bundle getMethodArguments()
    {
      Bundle localBundle = new Bundle();
      putExtra(localBundle, "TITLE", this.name);
      putExtra(localBundle, "SUBTITLE", this.caption);
      putExtra(localBundle, "DESCRIPTION", this.description);
      putExtra(localBundle, "LINK", this.link);
      putExtra(localBundle, "IMAGE", this.picture);
      putExtra(localBundle, "PLACE", this.place);
      putExtra(localBundle, "TITLE", this.name);
      putExtra(localBundle, "REF", this.ref);
      localBundle.putBoolean("DATA_FAILURES_FATAL", this.dataErrorsFatal);
      if (!Utility.isNullOrEmpty(this.friends)) {
        localBundle.putStringArrayList("FRIENDS", this.friends);
      }
      return localBundle;
    }
    
    protected Bundle setBundleExtras(Bundle paramBundle)
    {
      putExtra(paramBundle, "com.facebook.platform.extra.APPLICATION_ID", this.applicationId);
      putExtra(paramBundle, "com.facebook.platform.extra.APPLICATION_NAME", this.applicationName);
      putExtra(paramBundle, "com.facebook.platform.extra.TITLE", this.name);
      putExtra(paramBundle, "com.facebook.platform.extra.SUBTITLE", this.caption);
      putExtra(paramBundle, "com.facebook.platform.extra.DESCRIPTION", this.description);
      putExtra(paramBundle, "com.facebook.platform.extra.LINK", this.link);
      putExtra(paramBundle, "com.facebook.platform.extra.IMAGE", this.picture);
      putExtra(paramBundle, "com.facebook.platform.extra.PLACE", this.place);
      putExtra(paramBundle, "com.facebook.platform.extra.TITLE", this.name);
      putExtra(paramBundle, "com.facebook.platform.extra.REF", this.ref);
      paramBundle.putBoolean("com.facebook.platform.extra.DATA_FAILURES_FATAL", this.dataErrorsFatal);
      if (!Utility.isNullOrEmpty(this.friends)) {
        paramBundle.putStringArrayList("com.facebook.platform.extra.FRIENDS", this.friends);
      }
      return paramBundle;
    }
    
    public CONCRETE setCaption(String paramString)
    {
      this.caption = paramString;
      return this;
    }
    
    public CONCRETE setDataErrorsFatal(boolean paramBoolean)
    {
      this.dataErrorsFatal = paramBoolean;
      return this;
    }
    
    public CONCRETE setDescription(String paramString)
    {
      this.description = paramString;
      return this;
    }
    
    public CONCRETE setFriends(List<String> paramList)
    {
      this.friends = new ArrayList(paramList);
      return this;
    }
    
    public CONCRETE setLink(String paramString)
    {
      this.link = paramString;
      return this;
    }
    
    public CONCRETE setName(String paramString)
    {
      this.name = paramString;
      return this;
    }
    
    public CONCRETE setPicture(String paramString)
    {
      this.picture = paramString;
      return this;
    }
    
    public CONCRETE setPlace(String paramString)
    {
      this.place = paramString;
      return this;
    }
    
    public CONCRETE setRef(String paramString)
    {
      this.ref = paramString;
      return this;
    }
  }
  
  public static enum ShareDialogFeature
    implements FacebookDialog.DialogFeature
  {
    private int minVersion;
    
    static
    {
      PHOTOS = new ShareDialogFeature("PHOTOS", 1, 20140204);
      ShareDialogFeature[] arrayOfShareDialogFeature = new ShareDialogFeature[2];
      arrayOfShareDialogFeature[0] = SHARE_DIALOG;
      arrayOfShareDialogFeature[1] = PHOTOS;
      $VALUES = arrayOfShareDialogFeature;
    }
    
    private ShareDialogFeature(int paramInt)
    {
      this.minVersion = paramInt;
    }
    
    public String getAction()
    {
      return "com.facebook.platform.action.request.FEED_DIALOG";
    }
    
    public int getMinVersion()
    {
      return this.minVersion;
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.facebook.widget.FacebookDialog
 * JD-Core Version:    0.7.0.1
 */