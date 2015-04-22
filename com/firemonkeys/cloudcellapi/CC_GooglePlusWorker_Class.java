package com.firemonkeys.cloudcellapi;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.achievement.Achievement;
import com.google.android.gms.games.achievement.AchievementBuffer;
import com.google.android.gms.games.achievement.Achievements;
import com.google.android.gms.games.achievement.Achievements.LoadAchievementsResult;
import com.google.android.gms.games.achievement.Achievements.UpdateAchievementResult;
import com.google.android.gms.plus.Account;
import com.google.android.gms.plus.People;
import com.google.android.gms.plus.People.LoadPeopleResult;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import com.google.android.gms.plus.model.people.Person.Image;
import com.google.android.gms.plus.model.people.PersonBuffer;
import java.io.File;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class CC_GooglePlusWorker_Class
  implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, DialogInterface.OnCancelListener
{
  private static final int INVALID_REQUEST_CODE = -1;
  private static final int REQUEST_CODE_CONNECT_GAME_SERVICES = 468723;
  private static final int REQUEST_CODE_INTERACTIVE_POST = 468722;
  private static final int REQUEST_CODE_RESOLVE_GOOGLE_PLUS_ERROR = 468720;
  private static final int REQUEST_CODE_SHOW_ACHIEVEMENTS = 468724;
  private static final int REQUEST_CODE_SIGN_IN = 468721;
  private static final Object lock;
  private static File m_ShareTempFile = null;
  private static boolean m_bLoginChanged;
  private static boolean m_bLoginSilent;
  private static long m_nLoginCallback;
  private static long m_nLoginUserPointer;
  private static long m_nShareCallback;
  private static long m_nShareUserPointer;
  public static GoogleApiClient m_pGoogleApiClient = null;
  private static CC_GooglePlusWorker_Class m_pGooglePlusWorker = null;
  
  static
  {
    lock = new Object();
    m_bLoginSilent = true;
    m_bLoginChanged = false;
    m_nLoginCallback = 0L;
    m_nLoginUserPointer = 0L;
    m_nShareCallback = 0L;
    m_nShareUserPointer = 0L;
  }
  
  public CC_GooglePlusWorker_Class()
  {
    m_pGooglePlusWorker = this;
  }
  
  private native void LoadFriendVectorCallback(boolean paramBoolean, String[] paramArrayOfString1, String[] paramArrayOfString2, long paramLong1, long paramLong2);
  
  private native void LoadProfileCallback(boolean paramBoolean, String[] paramArrayOfString, long paramLong1, long paramLong2);
  
  public static void Log(String paramString)
  {
    Log.i("CC_PLUS", paramString);
  }
  
  private native void LoginCallback(String paramString1, String paramString2, long paramLong1, long paramLong2);
  
  private native void LogoutCallback(long paramLong1, long paramLong2);
  
  private static void ShareCallback(boolean paramBoolean)
  {
    for (;;)
    {
      synchronized (lock)
      {
        Log("ShareCallback. Success: " + paramBoolean);
        if (m_ShareTempFile != null)
        {
          boolean bool = m_ShareTempFile.delete();
          StringBuilder localStringBuilder = new StringBuilder().append("ShareCallback - Attempting to delete temp file: ");
          if (bool)
          {
            str = "Success";
            Log(str);
            m_ShareTempFile = null;
          }
        }
        else
        {
          if (m_nShareCallback != 0L)
          {
            m_pGooglePlusWorker.ShareCallback(paramBoolean, m_nShareCallback, m_nShareUserPointer);
            m_nShareCallback = 0L;
            m_nShareUserPointer = 0L;
          }
          return;
        }
      }
      String str = "Failure";
    }
  }
  
  private native void ShareCallback(boolean paramBoolean, long paramLong1, long paramLong2);
  
  public static void handleActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    boolean bool = true;
    Log("Plus handleActivityResult requestCode:" + paramInt1 + " resultCode:" + paramInt2);
    if (m_pGoogleApiClient == null) {}
    do
    {
      return;
      if (paramInt1 == 468721)
      {
        m_bLoginSilent = bool;
        m_pGoogleApiClient.connect();
        return;
      }
      if (paramInt1 == 468720)
      {
        ShareCallback(false);
        return;
      }
    } while (paramInt1 != 468722);
    if (paramInt2 == -1) {}
    for (;;)
    {
      ShareCallback(bool);
      return;
      bool = false;
    }
  }
  
  public static void onStart()
  {
    if (m_pGoogleApiClient != null)
    {
      m_bLoginSilent = true;
      m_pGoogleApiClient.connect();
    }
  }
  
  public static void onStop()
  {
    if (m_pGoogleApiClient != null)
    {
      m_pGoogleApiClient.disconnect();
      synchronized (lock)
      {
        m_bLoginChanged = true;
        return;
      }
    }
  }
  
  public void Constructor(String paramString, boolean paramBoolean)
  {
    GoogleApiClient.Builder localBuilder = new GoogleApiClient.Builder(CC_Activity.GetActivity().getApplicationContext(), this, this);
    localBuilder.addApi(Plus.API).addScope(Plus.SCOPE_PLUS_LOGIN).addScope(Plus.SCOPE_PLUS_PROFILE);
    if (paramBoolean) {
      localBuilder.addApi(Games.API).addScope(Games.SCOPE_GAMES);
    }
    localBuilder.setGravityForPopups(49);
    localBuilder.setViewForPopups(CC_Activity.GetGLSurfaceView());
    m_pGoogleApiClient = localBuilder.build();
  }
  
  public void DoUnlockAchievement(final String paramString)
  {
    Log("DoUnlockAchievement: " + paramString);
    if ((m_pGoogleApiClient == null) || (!m_pGoogleApiClient.isConnected())) {
      return;
    }
    Log("Game Client unlockAchievement() called. AchievementId:" + paramString);
    Games.Achievements.unlockImmediate(m_pGoogleApiClient, paramString).setResultCallback(new ResultCallback()
    {
      public void onResult(Achievements.UpdateAchievementResult paramAnonymousUpdateAchievementResult)
      {
        CC_GooglePlusWorker_Class.Log("Game Client unlockAchievement() returned. AchievementId:" + paramString + " result:" + paramAnonymousUpdateAchievementResult.getStatus().getStatusCode());
      }
    });
  }
  
  protected Person GetCurrentPerson()
  {
    if (!m_pGoogleApiClient.isConnected()) {
      return null;
    }
    return Plus.PeopleApi.getCurrentPerson(m_pGoogleApiClient);
  }
  
  public String GetPersonId()
  {
    Person localPerson = GetCurrentPerson();
    if (localPerson != null) {
      return localPerson.getId();
    }
    return "";
  }
  
  public String GetPersonName()
  {
    Person localPerson = GetCurrentPerson();
    if (localPerson != null) {
      return localPerson.getDisplayName();
    }
    return "";
  }
  
  public boolean GetSessionChanged()
  {
    synchronized (lock)
    {
      boolean bool = m_bLoginChanged;
      m_bLoginChanged = false;
      return bool;
    }
  }
  
  public boolean GetSessionValid()
  {
    return m_pGoogleApiClient.isConnected();
  }
  
  public void LoadFriendVector(final long paramLong1, long paramLong2)
  {
    Log("LoadFriendVector");
    Plus.PeopleApi.loadVisible(m_pGoogleApiClient, 1, null).setResultCallback(new ResultCallback()
    {
      public void onResult(People.LoadPeopleResult paramAnonymousLoadPeopleResult)
      {
        CC_GooglePlusWorker_Class.this.OnFriendsLoaded(paramAnonymousLoadPeopleResult, paramLong1, this.val$pUserPointer);
      }
    });
  }
  
  public void LoadFriendVectorConnected(final long paramLong1, long paramLong2)
  {
    Log("LoadFriendVectorConnected");
    Plus.PeopleApi.loadConnected(m_pGoogleApiClient).setResultCallback(new ResultCallback()
    {
      public void onResult(People.LoadPeopleResult paramAnonymousLoadPeopleResult)
      {
        CC_GooglePlusWorker_Class.this.OnFriendsLoaded(paramAnonymousLoadPeopleResult, paramLong1, this.val$pUserPointer);
      }
    });
  }
  
  public void LoadProfile(String paramString, final long paramLong1, long paramLong2)
  {
    Log("PLUS LoadProfile");
    Plus.PeopleApi.load(m_pGoogleApiClient, new String[] { paramString }).setResultCallback(new ResultCallback()
    {
      public void onResult(People.LoadPeopleResult paramAnonymousLoadPeopleResult)
      {
        PersonBuffer localPersonBuffer = paramAnonymousLoadPeopleResult.getPersonBuffer();
        Person localPerson = null;
        if (localPersonBuffer != null)
        {
          int i = localPersonBuffer.getCount();
          localPerson = null;
          if (i > 0) {
            localPerson = localPersonBuffer.get(0);
          }
        }
        bool = false;
        arrayOfString = null;
        if (localPerson != null) {}
        try
        {
          arrayOfString = new String[6];
          arrayOfString[0] = localPerson.getId();
          arrayOfString[1] = localPerson.getDisplayName();
          Uri localUri = Uri.parse(localPerson.getImage().getUrl());
          arrayOfString[2] = localUri.getScheme();
          arrayOfString[3] = localUri.getHost();
          arrayOfString[4] = String.valueOf(localUri.getPort());
          String str2 = localUri.getPath();
          String str3 = localUri.getQuery();
          if ((str3 != null) && (str3.length() > 0)) {
            str2 = str2 + "?" + str3;
          }
          String str4 = localUri.getFragment();
          if ((str4 != null) && (str4.length() > 0)) {
            str2 = str2 + "#" + str4;
          }
          arrayOfString[5] = str2;
          bool = true;
        }
        catch (Exception localException)
        {
          synchronized (CC_GooglePlusWorker_Class.lock)
          {
            for (;;)
            {
              StringBuilder localStringBuilder;
              if (paramLong1 != 0L) {
                CC_GooglePlusWorker_Class.this.LoadProfileCallback(bool, arrayOfString, paramLong1, this.val$pUserPointer);
              }
              return;
              localException = localException;
              Consts.Logger("onPersonLoaded exception.");
              localException.printStackTrace();
              bool = false;
            }
            String str1 = "FAIL";
          }
        }
        localStringBuilder = new StringBuilder().append("PLUS onPersonLoaded ");
        if (bool)
        {
          str1 = "SUCCESS";
          CC_GooglePlusWorker_Class.Log(str1);
        }
      }
    });
  }
  
  public void Login(final long paramLong1, long paramLong2, final boolean paramBoolean)
  {
    CC_Activity.GetActivity().runOnUiThread(new Runnable()
    {
      public void run()
      {
        CC_GooglePlusWorker_Class.Log("LOGIN isConnecting:" + CC_GooglePlusWorker_Class.m_pGoogleApiClient.isConnecting() + " isConnected:" + CC_GooglePlusWorker_Class.m_pGoogleApiClient.isConnected());
        if ((!CC_GooglePlusWorker_Class.m_pGoogleApiClient.isConnecting()) && (!CC_GooglePlusWorker_Class.m_pGoogleApiClient.isConnected())) {
          synchronized (CC_GooglePlusWorker_Class.lock)
          {
            CC_GooglePlusWorker_Class.access$102(paramLong1);
            CC_GooglePlusWorker_Class.access$202(paramBoolean);
            CC_GooglePlusWorker_Class.access$302(this.val$bSilent);
            CC_GooglePlusWorker_Class.Log("LOGIN connect()");
            CC_GooglePlusWorker_Class.m_pGoogleApiClient.connect();
            return;
          }
        }
        String str1 = CC_GooglePlusWorker_Class.this.GetPersonId();
        String str2 = CC_GooglePlusWorker_Class.this.GetPersonName();
        CC_GooglePlusWorker_Class.this.LoginCallback(str1, str2, paramLong1, paramBoolean);
      }
    });
  }
  
  protected void LoginCallback(String paramString1, String paramString2)
  {
    synchronized (lock)
    {
      Log("PLUS LoginCallback person id:" + paramString1 + " name:" + paramString2);
      if (m_nLoginCallback != 0L)
      {
        LoginCallback(paramString1, paramString2, m_nLoginCallback, m_nLoginUserPointer);
        m_nLoginCallback = 0L;
        m_nLoginUserPointer = 0L;
        return;
      }
      m_bLoginChanged = true;
    }
  }
  
  public void Logout(long paramLong1, long paramLong2)
  {
    StringBuilder localStringBuilder1 = new StringBuilder().append("PLUS Logout isConnecting:");
    String str1;
    StringBuilder localStringBuilder2;
    if (m_pGoogleApiClient.isConnecting())
    {
      str1 = "true";
      localStringBuilder2 = localStringBuilder1.append(str1).append(" isConnected:");
      if (!m_pGoogleApiClient.isConnected()) {
        break label131;
      }
    }
    label131:
    for (String str2 = "true";; str2 = "false")
    {
      Log(str2);
      if ((m_pGoogleApiClient.isConnecting()) || (m_pGoogleApiClient.isConnected()))
      {
        Plus.AccountApi.clearDefaultAccount(m_pGoogleApiClient);
        m_pGoogleApiClient.disconnect();
      }
      LogoutCallback(paramLong1, paramLong2);
      return;
      str1 = "false";
      break;
    }
  }
  
  public void OnFriendsLoaded(People.LoadPeopleResult paramLoadPeopleResult, long paramLong1, long paramLong2)
  {
    bool = paramLoadPeopleResult.getStatus().isSuccess();
    StringBuilder localStringBuilder = new StringBuilder().append("onPeopleLoaded ");
    String str;
    if (bool) {
      str = "SUCCESS";
    }
    for (;;)
    {
      Log(str);
      String[] arrayOfString1 = new String[0];
      String[] arrayOfString2 = new String[0];
      PersonBuffer localPersonBuffer;
      if (bool) {
        localPersonBuffer = paramLoadPeopleResult.getPersonBuffer();
      }
      try
      {
        Log("onPeopleLoaded count:" + localPersonBuffer.getCount());
        int i = localPersonBuffer.getCount();
        arrayOfString1 = new String[i];
        arrayOfString2 = new String[i];
        int j = 0;
        for (;;)
        {
          if (j < i)
          {
            Person localPerson = localPersonBuffer.get(j);
            Log("PLUS onPeopleLoaded personId:" + localPerson.getId() + " name:" + localPerson.getDisplayName());
            arrayOfString1[j] = localPerson.getId();
            arrayOfString2[j] = localPerson.getDisplayName();
            j++;
            continue;
            str = "FAILED";
            break;
          }
        }
      }
      catch (Exception localException)
      {
        for (;;)
        {
          localException = localException;
          Log("PLUS onPeopleLoaded EXCEPTION");
          localException.printStackTrace();
          localPersonBuffer.close();
        }
      }
      finally
      {
        localPersonBuffer.close();
      }
    }
    localObject1 = lock;
    if (paramLong1 == 0L) {}
  }
  
  public void ResetAchievements()
  {
    if ((m_pGoogleApiClient == null) || (!m_pGoogleApiClient.isConnected())) {
      return;
    }
    new Thread(new Runnable()
    {
      public void run()
      {
        try
        {
          String str = GoogleAuthUtil.getToken(CC_Activity.GetActivity(), Plus.AccountApi.getAccountName(CC_GooglePlusWorker_Class.m_pGoogleApiClient), "oauth2:https://www.googleapis.com/auth/games");
          CC_GooglePlusWorker_Class.Log("ResetAchievements AccessToken:" + str);
          CC_GooglePlusWorker_Class.Log(EntityUtils.toString(new DefaultHttpClient().execute(new HttpPost("https://www.googleapis.com/games/v1management/achievements/reset?access_token=" + str)).getEntity()));
          return;
        }
        catch (Exception localException)
        {
          Consts.Logger("ResetAchievements exception.");
          localException.printStackTrace();
        }
      }
    }).start();
  }
  
  public void Share(String paramString1, final String paramString2, final String paramString3, final String paramString4, final String paramString5, final String paramString6, final byte[] paramArrayOfByte, final String paramString7, final String paramString8, final String paramString9, final long paramLong1, long paramLong2)
  {
    final Activity localActivity = CC_Activity.GetActivity();
    localActivity.runOnUiThread(new Runnable()
    {
      /* Error */
      public void run()
      {
        // Byte code:
        //   0: invokestatic 72	com/firemonkeys/cloudcellapi/CC_GooglePlusWorker_Class:access$000	()Ljava/lang/Object;
        //   3: astore_1
        //   4: aload_1
        //   5: monitorenter
        //   6: aload_0
        //   7: getfield 36	com/firemonkeys/cloudcellapi/CC_GooglePlusWorker_Class$5:val$pCallback	J
        //   10: invokestatic 76	com/firemonkeys/cloudcellapi/CC_GooglePlusWorker_Class:access$602	(J)J
        //   13: pop2
        //   14: aload_0
        //   15: getfield 38	com/firemonkeys/cloudcellapi/CC_GooglePlusWorker_Class$5:val$pUserPointer	J
        //   18: invokestatic 79	com/firemonkeys/cloudcellapi/CC_GooglePlusWorker_Class:access$702	(J)J
        //   21: pop2
        //   22: aload_1
        //   23: monitorexit
        //   24: aload_0
        //   25: getfield 40	com/firemonkeys/cloudcellapi/CC_GooglePlusWorker_Class$5:val$activity	Landroid/app/Activity;
        //   28: invokevirtual 85	android/app/Activity:getApplicationContext	()Landroid/content/Context;
        //   31: invokestatic 91	com/google/android/gms/common/GooglePlayServicesUtil:isGooglePlayServicesAvailable	(Landroid/content/Context;)I
        //   34: istore 8
        //   36: iload 8
        //   38: ifne +652 -> 690
        //   41: new 93	com/google/android/gms/plus/PlusShare$Builder
        //   44: dup
        //   45: aload_0
        //   46: getfield 40	com/firemonkeys/cloudcellapi/CC_GooglePlusWorker_Class$5:val$activity	Landroid/app/Activity;
        //   49: invokespecial 96	com/google/android/gms/plus/PlusShare$Builder:<init>	(Landroid/app/Activity;)V
        //   52: astore 9
        //   54: aload 9
        //   56: aload_0
        //   57: getfield 42	com/firemonkeys/cloudcellapi/CC_GooglePlusWorker_Class$5:val$sPrefillText	Ljava/lang/String;
        //   60: invokevirtual 100	com/google/android/gms/plus/PlusShare$Builder:setText	(Ljava/lang/CharSequence;)Lcom/google/android/gms/plus/PlusShare$Builder;
        //   63: pop
        //   64: aload 9
        //   66: ldc 102
        //   68: invokevirtual 106	com/google/android/gms/plus/PlusShare$Builder:setType	(Ljava/lang/String;)Lcom/google/android/gms/plus/PlusShare$Builder;
        //   71: pop
        //   72: aload_0
        //   73: getfield 44	com/firemonkeys/cloudcellapi/CC_GooglePlusWorker_Class$5:val$sContentURL	Ljava/lang/String;
        //   76: invokevirtual 112	java/lang/String:length	()I
        //   79: ifle +392 -> 471
        //   82: aload 9
        //   84: new 114	java/lang/StringBuilder
        //   87: dup
        //   88: invokespecial 115	java/lang/StringBuilder:<init>	()V
        //   91: aload_0
        //   92: getfield 42	com/firemonkeys/cloudcellapi/CC_GooglePlusWorker_Class$5:val$sPrefillText	Ljava/lang/String;
        //   95: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   98: ldc 121
        //   100: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   103: aload_0
        //   104: getfield 44	com/firemonkeys/cloudcellapi/CC_GooglePlusWorker_Class$5:val$sContentURL	Ljava/lang/String;
        //   107: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   110: invokevirtual 125	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   113: invokevirtual 100	com/google/android/gms/plus/PlusShare$Builder:setText	(Ljava/lang/CharSequence;)Lcom/google/android/gms/plus/PlusShare$Builder;
        //   116: pop
        //   117: new 114	java/lang/StringBuilder
        //   120: dup
        //   121: invokespecial 115	java/lang/StringBuilder:<init>	()V
        //   124: ldc 127
        //   126: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   129: aload_0
        //   130: getfield 42	com/firemonkeys/cloudcellapi/CC_GooglePlusWorker_Class$5:val$sPrefillText	Ljava/lang/String;
        //   133: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   136: ldc 129
        //   138: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   141: invokevirtual 125	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   144: invokestatic 133	com/firemonkeys/cloudcellapi/CC_GooglePlusWorker_Class:Log	(Ljava/lang/String;)V
        //   147: new 114	java/lang/StringBuilder
        //   150: dup
        //   151: invokespecial 115	java/lang/StringBuilder:<init>	()V
        //   154: ldc 135
        //   156: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   159: aload_0
        //   160: getfield 44	com/firemonkeys/cloudcellapi/CC_GooglePlusWorker_Class$5:val$sContentURL	Ljava/lang/String;
        //   163: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   166: ldc 129
        //   168: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   171: invokevirtual 125	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   174: invokestatic 133	com/firemonkeys/cloudcellapi/CC_GooglePlusWorker_Class:Log	(Ljava/lang/String;)V
        //   177: new 114	java/lang/StringBuilder
        //   180: dup
        //   181: invokespecial 115	java/lang/StringBuilder:<init>	()V
        //   184: ldc 137
        //   186: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   189: aload_0
        //   190: getfield 46	com/firemonkeys/cloudcellapi/CC_GooglePlusWorker_Class$5:val$sContentTitle	Ljava/lang/String;
        //   193: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   196: ldc 139
        //   198: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   201: aload_0
        //   202: getfield 48	com/firemonkeys/cloudcellapi/CC_GooglePlusWorker_Class$5:val$sContentDescription	Ljava/lang/String;
        //   205: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   208: ldc 141
        //   210: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   213: aload_0
        //   214: getfield 50	com/firemonkeys/cloudcellapi/CC_GooglePlusWorker_Class$5:val$sContentThumbURL	Ljava/lang/String;
        //   217: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   220: ldc 143
        //   222: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   225: aload_0
        //   226: getfield 52	com/firemonkeys/cloudcellapi/CC_GooglePlusWorker_Class$5:val$sContentDeepLinkID	Ljava/lang/String;
        //   229: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   232: ldc 129
        //   234: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   237: invokevirtual 125	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   240: invokestatic 133	com/firemonkeys/cloudcellapi/CC_GooglePlusWorker_Class:Log	(Ljava/lang/String;)V
        //   243: new 114	java/lang/StringBuilder
        //   246: dup
        //   247: invokespecial 115	java/lang/StringBuilder:<init>	()V
        //   250: ldc 145
        //   252: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   255: aload_0
        //   256: getfield 54	com/firemonkeys/cloudcellapi/CC_GooglePlusWorker_Class$5:val$sCallToActionLabel	Ljava/lang/String;
        //   259: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   262: ldc 147
        //   264: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   267: aload_0
        //   268: getfield 56	com/firemonkeys/cloudcellapi/CC_GooglePlusWorker_Class$5:val$sCallToActionURL	Ljava/lang/String;
        //   271: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   274: ldc 149
        //   276: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   279: aload_0
        //   280: getfield 58	com/firemonkeys/cloudcellapi/CC_GooglePlusWorker_Class$5:val$sCallToActionDeepLinkID	Ljava/lang/String;
        //   283: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   286: ldc 129
        //   288: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   291: invokevirtual 125	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   294: invokestatic 133	com/firemonkeys/cloudcellapi/CC_GooglePlusWorker_Class:Log	(Ljava/lang/String;)V
        //   297: aload_0
        //   298: getfield 60	com/firemonkeys/cloudcellapi/CC_GooglePlusWorker_Class$5:val$pContentImage	[B
        //   301: ifnull +261 -> 562
        //   304: aload_0
        //   305: getfield 60	com/firemonkeys/cloudcellapi/CC_GooglePlusWorker_Class$5:val$pContentImage	[B
        //   308: arraylength
        //   309: istore 17
        //   311: iload 17
        //   313: ifle +249 -> 562
        //   316: ldc 151
        //   318: ldc 153
        //   320: invokestatic 159	com/firemonkeys/cloudcellapi/CC_Activity:GetActivity	()Landroid/app/Activity;
        //   323: invokevirtual 85	android/app/Activity:getApplicationContext	()Landroid/content/Context;
        //   326: aconst_null
        //   327: invokevirtual 165	android/content/Context:getExternalFilesDir	(Ljava/lang/String;)Ljava/io/File;
        //   330: invokestatic 171	java/io/File:createTempFile	(Ljava/lang/String;Ljava/lang/String;Ljava/io/File;)Ljava/io/File;
        //   333: invokestatic 175	com/firemonkeys/cloudcellapi/CC_GooglePlusWorker_Class:access$802	(Ljava/io/File;)Ljava/io/File;
        //   336: pop
        //   337: new 114	java/lang/StringBuilder
        //   340: dup
        //   341: invokespecial 115	java/lang/StringBuilder:<init>	()V
        //   344: ldc 177
        //   346: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   349: invokestatic 181	com/firemonkeys/cloudcellapi/CC_GooglePlusWorker_Class:access$800	()Ljava/io/File;
        //   352: invokevirtual 184	java/io/File:getAbsolutePath	()Ljava/lang/String;
        //   355: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   358: invokevirtual 125	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   361: invokestatic 133	com/firemonkeys/cloudcellapi/CC_GooglePlusWorker_Class:Log	(Ljava/lang/String;)V
        //   364: new 186	java/io/FileOutputStream
        //   367: dup
        //   368: invokestatic 181	com/firemonkeys/cloudcellapi/CC_GooglePlusWorker_Class:access$800	()Ljava/io/File;
        //   371: invokespecial 189	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
        //   374: astore 20
        //   376: aload 20
        //   378: aload_0
        //   379: getfield 60	com/firemonkeys/cloudcellapi/CC_GooglePlusWorker_Class$5:val$pContentImage	[B
        //   382: invokevirtual 193	java/io/FileOutputStream:write	([B)V
        //   385: aload 20
        //   387: invokevirtual 196	java/io/FileOutputStream:flush	()V
        //   390: aload 20
        //   392: invokevirtual 199	java/io/FileOutputStream:close	()V
        //   395: invokestatic 181	com/firemonkeys/cloudcellapi/CC_GooglePlusWorker_Class:access$800	()Ljava/io/File;
        //   398: invokevirtual 203	java/io/File:canRead	()Z
        //   401: ifeq +100 -> 501
        //   404: new 114	java/lang/StringBuilder
        //   407: dup
        //   408: invokespecial 115	java/lang/StringBuilder:<init>	()V
        //   411: ldc 205
        //   413: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   416: invokestatic 181	com/firemonkeys/cloudcellapi/CC_GooglePlusWorker_Class:access$800	()Ljava/io/File;
        //   419: invokevirtual 184	java/io/File:getAbsolutePath	()Ljava/lang/String;
        //   422: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   425: invokevirtual 125	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   428: invokestatic 133	com/firemonkeys/cloudcellapi/CC_GooglePlusWorker_Class:Log	(Ljava/lang/String;)V
        //   431: aload 9
        //   433: invokestatic 181	com/firemonkeys/cloudcellapi/CC_GooglePlusWorker_Class:access$800	()Ljava/io/File;
        //   436: invokestatic 211	android/net/Uri:fromFile	(Ljava/io/File;)Landroid/net/Uri;
        //   439: invokevirtual 215	com/google/android/gms/plus/PlusShare$Builder:setStream	(Landroid/net/Uri;)Lcom/google/android/gms/plus/PlusShare$Builder;
        //   442: pop
        //   443: aload 9
        //   445: ldc 217
        //   447: invokevirtual 106	com/google/android/gms/plus/PlusShare$Builder:setType	(Ljava/lang/String;)Lcom/google/android/gms/plus/PlusShare$Builder;
        //   450: pop
        //   451: aload_0
        //   452: getfield 40	com/firemonkeys/cloudcellapi/CC_GooglePlusWorker_Class$5:val$activity	Landroid/app/Activity;
        //   455: aload 9
        //   457: invokevirtual 221	com/google/android/gms/plus/PlusShare$Builder:getIntent	()Landroid/content/Intent;
        //   460: ldc 222
        //   462: invokevirtual 226	android/app/Activity:startActivityForResult	(Landroid/content/Intent;I)V
        //   465: return
        //   466: astore_2
        //   467: aload_1
        //   468: monitorexit
        //   469: aload_2
        //   470: athrow
        //   471: aload 9
        //   473: aload_0
        //   474: getfield 42	com/firemonkeys/cloudcellapi/CC_GooglePlusWorker_Class$5:val$sPrefillText	Ljava/lang/String;
        //   477: invokevirtual 100	com/google/android/gms/plus/PlusShare$Builder:setText	(Ljava/lang/CharSequence;)Lcom/google/android/gms/plus/PlusShare$Builder;
        //   480: pop
        //   481: goto -364 -> 117
        //   484: astore 7
        //   486: ldc 228
        //   488: invokestatic 233	com/firemonkeys/cloudcellapi/Consts:Logger	(Ljava/lang/String;)V
        //   491: aload 7
        //   493: invokevirtual 236	java/lang/Exception:printStackTrace	()V
        //   496: iconst_0
        //   497: invokestatic 240	com/firemonkeys/cloudcellapi/CC_GooglePlusWorker_Class:access$1000	(Z)V
        //   500: return
        //   501: new 114	java/lang/StringBuilder
        //   504: dup
        //   505: invokespecial 115	java/lang/StringBuilder:<init>	()V
        //   508: ldc 242
        //   510: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   513: invokestatic 181	com/firemonkeys/cloudcellapi/CC_GooglePlusWorker_Class:access$800	()Ljava/io/File;
        //   516: invokevirtual 184	java/io/File:getAbsolutePath	()Ljava/lang/String;
        //   519: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   522: invokevirtual 125	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   525: invokestatic 133	com/firemonkeys/cloudcellapi/CC_GooglePlusWorker_Class:Log	(Ljava/lang/String;)V
        //   528: goto -77 -> 451
        //   531: astore 18
        //   533: new 114	java/lang/StringBuilder
        //   536: dup
        //   537: invokespecial 115	java/lang/StringBuilder:<init>	()V
        //   540: ldc 244
        //   542: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   545: aload 18
        //   547: invokevirtual 247	java/io/IOException:getMessage	()Ljava/lang/String;
        //   550: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   553: invokevirtual 125	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   556: invokestatic 133	com/firemonkeys/cloudcellapi/CC_GooglePlusWorker_Class:Log	(Ljava/lang/String;)V
        //   559: goto -108 -> 451
        //   562: aload_0
        //   563: getfield 44	com/firemonkeys/cloudcellapi/CC_GooglePlusWorker_Class$5:val$sContentURL	Ljava/lang/String;
        //   566: invokevirtual 112	java/lang/String:length	()I
        //   569: ifle +87 -> 656
        //   572: ldc 249
        //   574: invokestatic 133	com/firemonkeys/cloudcellapi/CC_GooglePlusWorker_Class:Log	(Ljava/lang/String;)V
        //   577: aload 9
        //   579: aload_0
        //   580: getfield 44	com/firemonkeys/cloudcellapi/CC_GooglePlusWorker_Class$5:val$sContentURL	Ljava/lang/String;
        //   583: invokestatic 253	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
        //   586: invokevirtual 256	com/google/android/gms/plus/PlusShare$Builder:setContentUrl	(Landroid/net/Uri;)Lcom/google/android/gms/plus/PlusShare$Builder;
        //   589: pop
        //   590: ldc_w 258
        //   593: invokestatic 133	com/firemonkeys/cloudcellapi/CC_GooglePlusWorker_Class:Log	(Ljava/lang/String;)V
        //   596: aload 9
        //   598: aload_0
        //   599: getfield 52	com/firemonkeys/cloudcellapi/CC_GooglePlusWorker_Class$5:val$sContentDeepLinkID	Ljava/lang/String;
        //   602: invokevirtual 261	com/google/android/gms/plus/PlusShare$Builder:setContentDeepLinkId	(Ljava/lang/String;)Lcom/google/android/gms/plus/PlusShare$Builder;
        //   605: pop
        //   606: aload_0
        //   607: getfield 54	com/firemonkeys/cloudcellapi/CC_GooglePlusWorker_Class$5:val$sCallToActionLabel	Ljava/lang/String;
        //   610: invokevirtual 112	java/lang/String:length	()I
        //   613: ifle -162 -> 451
        //   616: aload_0
        //   617: getfield 56	com/firemonkeys/cloudcellapi/CC_GooglePlusWorker_Class$5:val$sCallToActionURL	Ljava/lang/String;
        //   620: invokevirtual 112	java/lang/String:length	()I
        //   623: ifle -172 -> 451
        //   626: ldc_w 263
        //   629: invokestatic 133	com/firemonkeys/cloudcellapi/CC_GooglePlusWorker_Class:Log	(Ljava/lang/String;)V
        //   632: aload 9
        //   634: aload_0
        //   635: getfield 54	com/firemonkeys/cloudcellapi/CC_GooglePlusWorker_Class$5:val$sCallToActionLabel	Ljava/lang/String;
        //   638: aload_0
        //   639: getfield 56	com/firemonkeys/cloudcellapi/CC_GooglePlusWorker_Class$5:val$sCallToActionURL	Ljava/lang/String;
        //   642: invokestatic 253	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
        //   645: aload_0
        //   646: getfield 58	com/firemonkeys/cloudcellapi/CC_GooglePlusWorker_Class$5:val$sCallToActionDeepLinkID	Ljava/lang/String;
        //   649: invokevirtual 267	com/google/android/gms/plus/PlusShare$Builder:addCallToAction	(Ljava/lang/String;Landroid/net/Uri;Ljava/lang/String;)Lcom/google/android/gms/plus/PlusShare$Builder;
        //   652: pop
        //   653: goto -202 -> 451
        //   656: ldc_w 258
        //   659: invokestatic 133	com/firemonkeys/cloudcellapi/CC_GooglePlusWorker_Class:Log	(Ljava/lang/String;)V
        //   662: aload 9
        //   664: aload_0
        //   665: getfield 52	com/firemonkeys/cloudcellapi/CC_GooglePlusWorker_Class$5:val$sContentDeepLinkID	Ljava/lang/String;
        //   668: aload_0
        //   669: getfield 46	com/firemonkeys/cloudcellapi/CC_GooglePlusWorker_Class$5:val$sContentTitle	Ljava/lang/String;
        //   672: aload_0
        //   673: getfield 48	com/firemonkeys/cloudcellapi/CC_GooglePlusWorker_Class$5:val$sContentDescription	Ljava/lang/String;
        //   676: aload_0
        //   677: getfield 50	com/firemonkeys/cloudcellapi/CC_GooglePlusWorker_Class$5:val$sContentThumbURL	Ljava/lang/String;
        //   680: invokestatic 253	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
        //   683: invokevirtual 270	com/google/android/gms/plus/PlusShare$Builder:setContentDeepLinkId	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroid/net/Uri;)Lcom/google/android/gms/plus/PlusShare$Builder;
        //   686: pop
        //   687: goto -236 -> 451
        //   690: iload 8
        //   692: aload_0
        //   693: getfield 40	com/firemonkeys/cloudcellapi/CC_GooglePlusWorker_Class$5:val$activity	Landroid/app/Activity;
        //   696: ldc_w 271
        //   699: invokestatic 275	com/google/android/gms/common/GooglePlayServicesUtil:getErrorDialog	(ILandroid/app/Activity;I)Landroid/app/Dialog;
        //   702: astore 24
        //   704: aload 24
        //   706: invokestatic 279	com/firemonkeys/cloudcellapi/CC_GooglePlusWorker_Class:access$900	()Lcom/firemonkeys/cloudcellapi/CC_GooglePlusWorker_Class;
        //   709: invokevirtual 285	android/app/Dialog:setOnCancelListener	(Landroid/content/DialogInterface$OnCancelListener;)V
        //   712: aload 24
        //   714: invokevirtual 288	android/app/Dialog:show	()V
        //   717: return
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	718	0	this	5
        //   3	465	1	localObject1	Object
        //   466	4	2	localObject2	Object
        //   484	8	7	localException	Exception
        //   34	657	8	i	int
        //   52	611	9	localBuilder	com.google.android.gms.plus.PlusShare.Builder
        //   309	3	17	j	int
        //   531	15	18	localIOException	java.io.IOException
        //   374	17	20	localFileOutputStream	java.io.FileOutputStream
        //   702	11	24	localDialog	Dialog
        // Exception table:
        //   from	to	target	type
        //   6	24	466	finally
        //   467	469	466	finally
        //   24	36	484	java/lang/Exception
        //   41	117	484	java/lang/Exception
        //   117	311	484	java/lang/Exception
        //   316	451	484	java/lang/Exception
        //   451	465	484	java/lang/Exception
        //   471	481	484	java/lang/Exception
        //   501	528	484	java/lang/Exception
        //   533	559	484	java/lang/Exception
        //   562	653	484	java/lang/Exception
        //   656	687	484	java/lang/Exception
        //   690	717	484	java/lang/Exception
        //   316	451	531	java/io/IOException
        //   501	528	531	java/io/IOException
      }
    });
  }
  
  public void ShowAchievements()
  {
    if ((m_pGoogleApiClient == null) || (!m_pGoogleApiClient.isConnected())) {
      return;
    }
    final Activity localActivity = CC_Activity.GetActivity();
    localActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        try
        {
          localActivity.startActivityForResult(Games.Achievements.getAchievementsIntent(CC_GooglePlusWorker_Class.m_pGoogleApiClient), 468724);
          return;
        }
        catch (Exception localException)
        {
          Consts.Logger("ShowAchievements exception.");
          localException.printStackTrace();
        }
      }
    });
  }
  
  public void UnlockAchievement(final String paramString)
  {
    Log("Attempting to unlock Achievement:" + paramString);
    if ((m_pGoogleApiClient == null) || (!m_pGoogleApiClient.isConnected())) {
      return;
    }
    Log("Game Client loadAchievements() called");
    Games.Achievements.load(m_pGoogleApiClient, false).setResultCallback(new ResultCallback()
    {
      public void onResult(Achievements.LoadAchievementsResult paramAnonymousLoadAchievementsResult)
      {
        AchievementBuffer localAchievementBuffer = paramAnonymousLoadAchievementsResult.getAchievements();
        CC_GooglePlusWorker_Class.Log("Game Client onAchievementsLoaded returned. NumAchievements:" + localAchievementBuffer.getCount());
        for (int i = 0;; i++)
        {
          if (i < localAchievementBuffer.getCount())
          {
            Achievement localAchievement = localAchievementBuffer.get(i);
            if (!localAchievement.getAchievementId().equals(paramString)) {
              continue;
            }
            CC_GooglePlusWorker_Class.Log("Found Achievement:" + paramString + " State:" + localAchievement.getState());
            if (localAchievement.getState() != 0) {
              CC_GooglePlusWorker_Class.this.DoUnlockAchievement(paramString);
            }
          }
          localAchievementBuffer.close();
          return;
        }
      }
    });
  }
  
  public void onCancel(DialogInterface paramDialogInterface)
  {
    ShareCallback(false);
  }
  
  public void onConnected(Bundle paramBundle)
  {
    Log("PLUS onConnected start");
    String str1 = GetPersonId();
    String str2 = GetPersonName();
    Log("PLUS onConnected person id:" + str1 + " name:" + str2);
    LoginCallback(str1, str2);
    Log("PLUS onConnected end");
  }
  
  public void onConnectionFailed(ConnectionResult paramConnectionResult)
  {
    Log("onConnectionFailed result:" + paramConnectionResult.getErrorCode());
    if (!m_bLoginSilent)
    {
      Log("onConnectionFailed hasResolution:" + paramConnectionResult.hasResolution());
      if (paramConnectionResult.hasResolution()) {
        try
        {
          Log("onConnectionFailed startResolutionForResult()");
          paramConnectionResult.startResolutionForResult(CC_Activity.GetActivity(), 468721);
          return;
        }
        catch (IntentSender.SendIntentException localSendIntentException)
        {
          Log("onConnectionFailed Exception!");
          localSendIntentException.printStackTrace();
        }
      }
      Log("GooglePlayServicesUtil.getErrorDialog() errorCode:" + paramConnectionResult.getErrorCode());
      Dialog localDialog = GooglePlayServicesUtil.getErrorDialog(paramConnectionResult.getErrorCode(), CC_Activity.GetActivity(), 468721);
      if (localDialog != null)
      {
        Log("Dialog.show()");
        localDialog.show();
      }
    }
    LoginCallback("", "");
  }
  
  public void onConnectionSuspended(int paramInt)
  {
    Log("onConnectionSuspended(" + paramInt + ")");
    Object localObject1 = lock;
    if (paramInt == 2) {}
    try
    {
      m_bLoginChanged = true;
      return;
    }
    finally {}
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.firemonkeys.cloudcellapi.CC_GooglePlusWorker_Class
 * JD-Core Version:    0.7.0.1
 */