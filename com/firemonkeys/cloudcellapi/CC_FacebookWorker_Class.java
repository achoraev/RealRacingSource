package com.firemonkeys.cloudcellapi;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import com.facebook.AccessToken;
import com.facebook.FacebookException;
import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.Request.Callback;
import com.facebook.Request.GraphUserCallback;
import com.facebook.Request.GraphUserListCallback;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.Session.Builder;
import com.facebook.Session.NewPermissionsRequest;
import com.facebook.Session.OpenRequest;
import com.facebook.Session.StatusCallback;
import com.facebook.SessionState;
import com.facebook.model.GraphObject;
import com.facebook.model.GraphObject.Factory;
import com.facebook.model.GraphUser;
import com.facebook.widget.WebDialog;
import com.facebook.widget.WebDialog.FeedDialogBuilder;
import com.facebook.widget.WebDialog.OnCompleteListener;
import com.facebook.widget.WebDialog.RequestsDialogBuilder;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CC_FacebookWorker_Class
{
  private static final List<String> PUBLISH_PERMISSIONS;
  private static long m_nLoginCallback;
  private static long m_nLoginUserPointer;
  public static Session m_pSession;
  private Session.StatusCallback m_pStatusCallback;
  
  static
  {
    if (!CC_FacebookWorker_Class.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      m_nLoginCallback = 0L;
      m_nLoginUserPointer = 0L;
      PUBLISH_PERMISSIONS = Arrays.asList(new String[] { "publish_actions", "manage_notifications", "manage_pages", "rsvp_event" });
      return;
    }
  }
  
  private void CheckExistingToken()
  {
    SharedPreferences localSharedPreferences = CC_Activity.GetActivity().getPreferences(0);
    String str = localSharedPreferences.getString("access_token", null);
    if (str != null)
    {
      SharedPreferences.Editor localEditor = localSharedPreferences.edit();
      localEditor.putString("access_token", null);
      localEditor.commit();
      AccessToken localAccessToken = AccessToken.createFromExistingAccessToken(str, null, null, null, null);
      m_pSession.open(localAccessToken, null);
    }
  }
  
  private native void FeedPostCallback(boolean paramBoolean, long paramLong1, long paramLong2);
  
  private native void FriendInviteCallback(boolean paramBoolean, long paramLong1, long paramLong2);
  
  private native void LoadFriendVectorCallback(boolean paramBoolean, String[] paramArrayOfString1, String[] paramArrayOfString2, long paramLong1, long paramLong2);
  
  private native void LoginCallback(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, long paramLong1, long paramLong2);
  
  private native void LogoutCallback(long paramLong1, long paramLong2);
  
  private native void PermissionCheckCallback(boolean paramBoolean, long paramLong1, long paramLong2);
  
  private native void PermissionGrantCallback(boolean paramBoolean, long paramLong1, long paramLong2);
  
  private native void PhotoPostCallback(boolean paramBoolean, long paramLong1, long paramLong2);
  
  public static void handleActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (Session.getActiveSession() != null) {
      Session.getActiveSession().onActivityResult(CC_Activity.GetActivity(), paramInt1, paramInt2, paramIntent);
    }
  }
  
  public void Constructor(String paramString)
  {
    Activity localActivity = CC_Activity.GetActivity();
    assert (localActivity != null);
    this.m_pStatusCallback = new SessionStatusCallback(null);
    m_pSession = new Session.Builder(localActivity).setApplicationId(paramString).build();
    CheckExistingToken();
    Session.setActiveSession(m_pSession);
    if (m_pSession.getState().equals(SessionState.CREATED_TOKEN_LOADED)) {
      m_pSession.openForRead(new Session.OpenRequest(CC_Activity.GetActivity()));
    }
  }
  
  public AvatarInfo DecodeAvatar(byte[] paramArrayOfByte, int paramInt)
  {
    Bitmap localBitmap = BitmapFactory.decodeByteArray(paramArrayOfByte, 0, paramInt);
    int i = localBitmap.getByteCount();
    AvatarInfo localAvatarInfo = new AvatarInfo();
    localAvatarInfo.nWidth = localBitmap.getWidth();
    localAvatarInfo.nHeight = localBitmap.getHeight();
    localAvatarInfo.nChannels = (i / (localBitmap.getWidth() * localBitmap.getHeight()));
    ByteBuffer localByteBuffer = ByteBuffer.allocate(i);
    localBitmap.copyPixelsToBuffer(localByteBuffer);
    localAvatarInfo.data = localByteBuffer.array();
    localBitmap.recycle();
    return localAvatarInfo;
  }
  
  public void FeedPost(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, boolean paramBoolean, final long paramLong1, long paramLong2)
  {
    if (paramBoolean)
    {
      final Bundle localBundle = new Bundle();
      localBundle.putString("name", paramString1);
      localBundle.putString("caption", paramString2);
      localBundle.putString("description", paramString3);
      localBundle.putString("link", paramString4);
      localBundle.putString("picture", paramString5);
      localBundle.putString("actions", paramString6);
      CC_Activity.GetActivity().runOnUiThread(new Runnable()
      {
        public void run()
        {
          ((WebDialog.FeedDialogBuilder)new WebDialog.FeedDialogBuilder(CC_Activity.GetActivity(), CC_FacebookWorker_Class.m_pSession, localBundle).setOnCompleteListener(new WebDialog.OnCompleteListener()
          {
            public void onComplete(Bundle paramAnonymous2Bundle, FacebookException paramAnonymous2FacebookException)
            {
              if (paramAnonymous2FacebookException == null)
              {
                if (paramAnonymous2Bundle.getString("post_id") != null)
                {
                  CC_FacebookWorker_Class.this.FeedPostCallback(true, CC_FacebookWorker_Class.5.this.val$pCallback, CC_FacebookWorker_Class.5.this.val$pUserPointer);
                  return;
                }
                CC_FacebookWorker_Class.this.FeedPostCallback(false, CC_FacebookWorker_Class.5.this.val$pCallback, CC_FacebookWorker_Class.5.this.val$pUserPointer);
                return;
              }
              CC_FacebookWorker_Class.this.FeedPostCallback(false, CC_FacebookWorker_Class.5.this.val$pCallback, CC_FacebookWorker_Class.5.this.val$pUserPointer);
            }
          })).build().show();
        }
      });
      return;
    }
    final GraphObject localGraphObject = GraphObject.Factory.create();
    localGraphObject.setProperty("name", paramString1);
    localGraphObject.setProperty("caption", paramString2);
    localGraphObject.setProperty("description", paramString3);
    localGraphObject.setProperty("link", paramString4);
    localGraphObject.setProperty("picture", paramString5);
    localGraphObject.setProperty("actions", paramString6);
    CC_Activity.GetActivity().runOnUiThread(new Runnable()
    {
      public void run()
      {
        try
        {
          Request.newPostRequest(CC_FacebookWorker_Class.m_pSession, "me/feed", localGraphObject, new Request.Callback()
          {
            public void onCompleted(Response paramAnonymous2Response)
            {
              if ((paramAnonymous2Response.getError() != null) || (paramAnonymous2Response.getGraphObject().getProperty("id") == null))
              {
                CC_FacebookWorker_Class.this.FeedPostCallback(false, CC_FacebookWorker_Class.6.this.val$pCallback, CC_FacebookWorker_Class.6.this.val$pUserPointer);
                return;
              }
              CC_FacebookWorker_Class.this.FeedPostCallback(true, CC_FacebookWorker_Class.6.this.val$pCallback, CC_FacebookWorker_Class.6.this.val$pUserPointer);
            }
          }).executeAsync();
          return;
        }
        catch (Exception localException)
        {
          Log.e("Cloudcell", "CC_FacebookWorker_Class::FeedPost() Exception:" + localException.getMessage());
          CC_FacebookWorker_Class.this.FeedPostCallback(false, paramLong1, this.val$pUserPointer);
        }
      }
    });
  }
  
  public void FriendInvite(String paramString1, String paramString2, final long paramLong1, long paramLong2)
  {
    Log.i("Cloudcell", "CC_FacebookWorker_Class::FriendInvite() sTitle: " + paramString1 + "sMessage: " + paramString2);
    final Bundle localBundle = new Bundle();
    localBundle.putString("title", paramString1);
    localBundle.putString("message", paramString2);
    CC_Activity.GetActivity().runOnUiThread(new Runnable()
    {
      public void run()
      {
        ((WebDialog.RequestsDialogBuilder)new WebDialog.RequestsDialogBuilder(CC_Activity.GetActivity(), CC_FacebookWorker_Class.m_pSession, localBundle).setOnCompleteListener(new WebDialog.OnCompleteListener()
        {
          public void onComplete(Bundle paramAnonymous2Bundle, FacebookException paramAnonymous2FacebookException)
          {
            if (paramAnonymous2FacebookException == null)
            {
              CC_FacebookWorker_Class.this.FriendInviteCallback(true, CC_FacebookWorker_Class.8.this.val$pCallback, CC_FacebookWorker_Class.8.this.val$pUserPointer);
              return;
            }
            CC_FacebookWorker_Class.this.FriendInviteCallback(false, CC_FacebookWorker_Class.8.this.val$pCallback, CC_FacebookWorker_Class.8.this.val$pUserPointer);
          }
        })).build().show();
      }
    });
  }
  
  public String GetAccessToken()
  {
    if (m_pSession.getAccessToken() != null) {
      return m_pSession.getAccessToken();
    }
    return "";
  }
  
  public boolean GetSessionValid()
  {
    return m_pSession.isOpened();
  }
  
  public void LoadFriendVector(final long paramLong1, long paramLong2)
  {
    if (!GetSessionValid())
    {
      LoadFriendVectorCallback(false, null, null, paramLong1, paramLong2);
      return;
    }
    CC_Activity.GetActivity().runOnUiThread(new Runnable()
    {
      public void run()
      {
        Request.newMyFriendsRequest(CC_FacebookWorker_Class.m_pSession, new Request.GraphUserListCallback()
        {
          public void onCompleted(List<GraphUser> paramAnonymous2List, Response paramAnonymous2Response)
          {
            if (paramAnonymous2List != null)
            {
              String[] arrayOfString1 = new String[paramAnonymous2List.size()];
              String[] arrayOfString2 = new String[paramAnonymous2List.size()];
              for (int i = 0; i < paramAnonymous2List.size(); i++)
              {
                arrayOfString1[i] = ((GraphUser)paramAnonymous2List.get(i)).getId();
                arrayOfString2[i] = ((GraphUser)paramAnonymous2List.get(i)).getName();
              }
              CC_FacebookWorker_Class.this.LoadFriendVectorCallback(true, arrayOfString1, arrayOfString2, CC_FacebookWorker_Class.4.this.val$pCallback, CC_FacebookWorker_Class.4.this.val$pUserPointer);
              return;
            }
            CC_FacebookWorker_Class.this.LoadFriendVectorCallback(false, null, null, CC_FacebookWorker_Class.4.this.val$pCallback, CC_FacebookWorker_Class.4.this.val$pUserPointer);
          }
        }).executeAsync();
      }
    });
  }
  
  public void Login(String[] paramArrayOfString, boolean paramBoolean, long paramLong1, long paramLong2)
  {
    m_nLoginCallback = paramLong1;
    m_nLoginUserPointer = paramLong2;
    CC_Activity.GetActivity().runOnUiThread(new Runnable()
    {
      public void run()
      {
        Session.openActiveSession(CC_Activity.GetActivity(), true, CC_FacebookWorker_Class.this.m_pStatusCallback);
      }
    });
  }
  
  public void Logout(long paramLong1, long paramLong2)
  {
    if (!m_pSession.isClosed()) {
      m_pSession.closeAndClearTokenInformation();
    }
    LogoutCallback(paramLong1, paramLong2);
  }
  
  public void PermissionCheck(String[] paramArrayOfString, long paramLong1, long paramLong2)
  {
    if (!GetSessionValid())
    {
      PermissionCheckCallback(false, paramLong1, paramLong2);
      return;
    }
    for (int i = 0; i < paramArrayOfString.length; i++) {
      if (!m_pSession.getPermissions().contains(paramArrayOfString[i]))
      {
        PermissionCheckCallback(false, paramLong1, paramLong2);
        return;
      }
    }
    PermissionCheckCallback(true, paramLong1, paramLong2);
  }
  
  public void PermissionGrant(final String[] paramArrayOfString, final long paramLong1, long paramLong2)
  {
    if (!GetSessionValid())
    {
      PermissionGrantCallback(false, paramLong1, paramLong2);
      return;
    }
    CC_Activity.GetActivity().runOnUiThread(new Runnable()
    {
      public void run()
      {
        ArrayList localArrayList1 = new ArrayList();
        ArrayList localArrayList2 = new ArrayList();
        int i = 0;
        if (i < paramArrayOfString.length)
        {
          if (!CC_FacebookWorker_Class.m_pSession.getPermissions().contains(paramArrayOfString[i]))
          {
            if (!CC_FacebookWorker_Class.PUBLISH_PERMISSIONS.contains(paramArrayOfString[i])) {
              break label83;
            }
            localArrayList2.add(paramArrayOfString[i]);
          }
          for (;;)
          {
            i++;
            break;
            label83:
            localArrayList1.add(paramArrayOfString[i]);
          }
        }
        try
        {
          if (localArrayList1.size() > 0) {
            CC_FacebookWorker_Class.m_pSession.requestNewReadPermissions(new Session.NewPermissionsRequest(CC_Activity.GetActivity(), localArrayList1));
          }
          if (localArrayList2.size() > 0) {
            CC_FacebookWorker_Class.m_pSession.requestNewPublishPermissions(new Session.NewPermissionsRequest(CC_Activity.GetActivity(), localArrayList2));
          }
          CC_FacebookWorker_Class.this.PermissionGrantCallback(true, paramLong1, this.val$pUserPointer);
          return;
        }
        catch (Exception localException)
        {
          Log.e("Cloudcell", "CC_FacebookWorker_Class::PermissionGrant() Exception:" + localException.getMessage());
          CC_FacebookWorker_Class.this.PermissionGrantCallback(false, paramLong1, this.val$pUserPointer);
        }
      }
    });
  }
  
  public void PhotoPost(String paramString, byte[] paramArrayOfByte, final long paramLong1, long paramLong2)
  {
    final Bundle localBundle = new Bundle(2);
    localBundle.putString("message", paramString);
    localBundle.putParcelable("source", BitmapFactory.decodeByteArray(paramArrayOfByte, 0, paramArrayOfByte.length));
    CC_Activity.GetActivity().runOnUiThread(new Runnable()
    {
      public void run()
      {
        try
        {
          new Request(CC_FacebookWorker_Class.m_pSession, "me/photos", localBundle, HttpMethod.POST, new Request.Callback()
          {
            public void onCompleted(Response paramAnonymous2Response)
            {
              if ((paramAnonymous2Response.getError() != null) || (paramAnonymous2Response.getGraphObject().getProperty("id") == null))
              {
                CC_FacebookWorker_Class.this.PhotoPostCallback(false, CC_FacebookWorker_Class.7.this.val$pCallback, CC_FacebookWorker_Class.7.this.val$pUserPointer);
                return;
              }
              CC_FacebookWorker_Class.this.PhotoPostCallback(true, CC_FacebookWorker_Class.7.this.val$pCallback, CC_FacebookWorker_Class.7.this.val$pUserPointer);
            }
          }).executeAsync();
          return;
        }
        catch (Exception localException)
        {
          Log.e("Cloudcell", "CC_FacebookWorker_Class::FeedPost() Exception:" + localException.getMessage());
          CC_FacebookWorker_Class.this.PhotoPostCallback(false, paramLong1, this.val$pUserPointer);
        }
      }
    });
  }
  
  public void SilentLogin(final String paramString, long paramLong1, long paramLong2)
  {
    if (!m_pSession.isClosed()) {
      m_pSession.closeAndClearTokenInformation();
    }
    m_nLoginCallback = paramLong1;
    m_nLoginUserPointer = paramLong2;
    CC_Activity.GetActivity().runOnUiThread(new Runnable()
    {
      public void run()
      {
        AccessToken localAccessToken = AccessToken.createFromExistingAccessToken(paramString, null, null, null, null);
        CC_FacebookWorker_Class.m_pSession.open(localAccessToken, null);
        if (CC_FacebookWorker_Class.m_pSession.getState().equals(SessionState.CREATED_TOKEN_LOADED)) {
          CC_FacebookWorker_Class.m_pSession.openForRead(new Session.OpenRequest(CC_Activity.GetActivity()));
        }
      }
    });
  }
  
  public class AvatarInfo
  {
    byte[] data;
    int nChannels;
    int nHeight;
    int nWidth;
    
    public AvatarInfo() {}
  }
  
  private class SessionStatusCallback
    implements Session.StatusCallback
  {
    private SessionStatusCallback() {}
    
    public void call(Session paramSession, SessionState paramSessionState, Exception paramException)
    {
      if (paramSessionState.isOpened())
      {
        CC_FacebookWorker_Class.m_pSession = Session.getActiveSession();
        if (CC_FacebookWorker_Class.m_nLoginCallback != 0L) {
          CC_Activity.GetActivity().runOnUiThread(new Runnable()
          {
            public void run()
            {
              Request.newMeRequest(CC_FacebookWorker_Class.m_pSession, new Request.GraphUserCallback()
              {
                public void onCompleted(GraphUser paramAnonymous2GraphUser, Response paramAnonymous2Response)
                {
                  String str1;
                  String str2;
                  label41:
                  String str3;
                  label58:
                  String str4;
                  if (CC_FacebookWorker_Class.m_nLoginCallback != 0L)
                  {
                    if (paramAnonymous2GraphUser.getId() == null) {
                      break label131;
                    }
                    str1 = paramAnonymous2GraphUser.getId();
                    if (paramAnonymous2GraphUser.getName() == null) {
                      break label137;
                    }
                    str2 = paramAnonymous2GraphUser.getName();
                    if (paramAnonymous2GraphUser.getFirstName() == null) {
                      break label144;
                    }
                    str3 = paramAnonymous2GraphUser.getFirstName();
                    if (paramAnonymous2GraphUser.getMiddleName() == null) {
                      break label151;
                    }
                    str4 = paramAnonymous2GraphUser.getMiddleName();
                    label75:
                    if (paramAnonymous2GraphUser.getLastName() == null) {
                      break label158;
                    }
                  }
                  label131:
                  label137:
                  label144:
                  label151:
                  label158:
                  for (String str5 = paramAnonymous2GraphUser.getLastName();; str5 = "")
                  {
                    CC_FacebookWorker_Class.this.LoginCallback(str1, str2, str3, str4, str5, CC_FacebookWorker_Class.m_nLoginCallback, CC_FacebookWorker_Class.m_nLoginUserPointer);
                    CC_FacebookWorker_Class.access$002(0L);
                    CC_FacebookWorker_Class.access$102(0L);
                    return;
                    str1 = "";
                    break;
                    str2 = "";
                    break label41;
                    str3 = "";
                    break label58;
                    str4 = "";
                    break label75;
                  }
                }
              }).executeAsync();
            }
          });
        }
      }
      while ((!paramSessionState.isClosed()) || (CC_FacebookWorker_Class.m_nLoginCallback == 0L)) {
        return;
      }
      CC_FacebookWorker_Class.this.LoginCallback("0", "", "", "", "", CC_FacebookWorker_Class.m_nLoginCallback, CC_FacebookWorker_Class.m_nLoginUserPointer);
      CC_FacebookWorker_Class.access$002(0L);
      CC_FacebookWorker_Class.access$102(0L);
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.firemonkeys.cloudcellapi.CC_FacebookWorker_Class
 * JD-Core Version:    0.7.0.1
 */