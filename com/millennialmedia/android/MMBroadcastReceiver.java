package com.millennialmedia.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

@Deprecated
public class MMBroadcastReceiver
  extends BroadcastReceiver
{
  public static final String ACTION_AD_SINGLE_TAP = "millennialmedia.action.ACTION_AD_SINGLE_TAP";
  public static final String ACTION_DISPLAY_STARTED = "millennialmedia.action.ACTION_DISPLAY_STARTED";
  public static final String ACTION_FETCH_FAILED = "millennialmedia.action.ACTION_FETCH_FAILED";
  public static final String ACTION_FETCH_STARTED_CACHING = "millennialmedia.action.ACTION_FETCH_STARTED_CACHING";
  public static final String ACTION_FETCH_SUCCEEDED = "millennialmedia.action.ACTION_FETCH_SUCCEEDED";
  public static final String ACTION_GETAD_FAILED = "millennialmedia.action.ACTION_GETAD_FAILED";
  public static final String ACTION_GETAD_SUCCEEDED = "millennialmedia.action.ACTION_GETAD_SUCCEEDED";
  public static final String ACTION_INTENT_STARTED = "millennialmedia.action.ACTION_INTENT_STARTED";
  public static final String ACTION_OVERLAY_CLOSED = "millennialmedia.action.ACTION_OVERLAY_CLOSED";
  public static final String ACTION_OVERLAY_OPENED = "millennialmedia.action.ACTION_OVERLAY_OPENED";
  @Deprecated
  public static final String ACTION_OVERLAY_TAP = "millennialmedia.action.ACTION_OVERLAY_TAP";
  public static final String CATEGORY_SDK = "millennialmedia.category.CATEGORY_SDK";
  private static final String TAG = "MMBroadcastReceiver";
  
  public static IntentFilter createIntentFilter()
  {
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addCategory("millennialmedia.category.CATEGORY_SDK");
    localIntentFilter.addAction("millennialmedia.action.ACTION_DISPLAY_STARTED");
    localIntentFilter.addAction("millennialmedia.action.ACTION_FETCH_FAILED");
    localIntentFilter.addAction("millennialmedia.action.ACTION_FETCH_SUCCEEDED");
    localIntentFilter.addAction("millennialmedia.action.ACTION_FETCH_STARTED_CACHING");
    localIntentFilter.addAction("millennialmedia.action.ACTION_GETAD_FAILED");
    localIntentFilter.addAction("millennialmedia.action.ACTION_GETAD_SUCCEEDED");
    localIntentFilter.addAction("millennialmedia.action.ACTION_INTENT_STARTED");
    localIntentFilter.addAction("millennialmedia.action.ACTION_OVERLAY_CLOSED");
    localIntentFilter.addAction("millennialmedia.action.ACTION_OVERLAY_OPENED");
    localIntentFilter.addAction("millennialmedia.action.ACTION_OVERLAY_TAP");
    localIntentFilter.addAction("millennialmedia.action.ACTION_AD_SINGLE_TAP");
    return localIntentFilter;
  }
  
  public void displayStarted(MMAd paramMMAd)
  {
    MMLog.i("MMBroadcastReceiver", "Millennial Media display started.");
  }
  
  public void fetchFailure(MMAd paramMMAd)
  {
    MMLog.i("MMBroadcastReceiver", "Millennial Media fetch failed.");
  }
  
  public void fetchFinishedCaching(MMAd paramMMAd)
  {
    MMLog.i("MMBroadcastReceiver", "Millennial Media fetch finished caching.");
  }
  
  public void fetchStartedCaching(MMAd paramMMAd)
  {
    MMLog.i("MMBroadcastReceiver", "Millennial Media fetch started caching.");
  }
  
  public void getAdFailure(MMAd paramMMAd)
  {
    MMLog.i("MMBroadcastReceiver", "Millennial Media ad Failure.");
  }
  
  public void getAdSuccess(MMAd paramMMAd)
  {
    MMLog.i("MMBroadcastReceiver", "Millennial Media ad Success.");
  }
  
  public void intentStarted(MMAd paramMMAd, String paramString)
  {
    if (paramString != null) {
      MMLog.i("MMBroadcastReceiver", "Millennial Media started intent: " + paramString);
    }
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    String str1 = paramIntent.getAction();
    String str2 = paramIntent.getStringExtra("packageName");
    long l = paramIntent.getLongExtra("internalId", -4L);
    if (!paramContext.getPackageName().equals(str2)) {}
    MMAd localMMAd;
    do
    {
      return;
      boolean bool = l < -4L;
      localMMAd = null;
      if (bool)
      {
        MMAdImpl localMMAdImpl = MMAdImplController.getAdImplWithId(l);
        localMMAd = null;
        if (localMMAdImpl != null) {
          localMMAd = localMMAdImpl.getCallingAd();
        }
      }
      StringBuilder localStringBuilder = new StringBuilder().append(" @@ Intent - Ad in receiver = ");
      if (localMMAd == null) {}
      for (String str3 = " null";; str3 = localMMAd.toString())
      {
        MMLog.v("MMBroadcastReceiver", str3);
        if (!str1.equals("millennialmedia.action.ACTION_OVERLAY_OPENED")) {
          break;
        }
        overlayOpened(localMMAd);
        return;
      }
      if (str1.equals("millennialmedia.action.ACTION_OVERLAY_CLOSED"))
      {
        overlayClosed(localMMAd);
        return;
      }
      if (str1.equals("millennialmedia.action.ACTION_OVERLAY_TAP"))
      {
        overlayTap(localMMAd);
        return;
      }
      if (str1.equals("millennialmedia.action.ACTION_AD_SINGLE_TAP"))
      {
        onSingleTap(localMMAd);
        return;
      }
      if (str1.equals("millennialmedia.action.ACTION_DISPLAY_STARTED"))
      {
        displayStarted(localMMAd);
        return;
      }
      if (str1.equals("millennialmedia.action.ACTION_FETCH_FAILED"))
      {
        fetchFailure(localMMAd);
        return;
      }
      if (str1.equals("millennialmedia.action.ACTION_FETCH_SUCCEEDED"))
      {
        fetchFinishedCaching(localMMAd);
        return;
      }
      if (str1.equals("millennialmedia.action.ACTION_FETCH_STARTED_CACHING"))
      {
        fetchStartedCaching(localMMAd);
        return;
      }
      if (str1.equals("millennialmedia.action.ACTION_GETAD_FAILED"))
      {
        getAdFailure(localMMAd);
        return;
      }
      if (str1.equals("millennialmedia.action.ACTION_GETAD_SUCCEEDED"))
      {
        getAdSuccess(localMMAd);
        return;
      }
    } while (!str1.equals("millennialmedia.action.ACTION_INTENT_STARTED"));
    intentStarted(localMMAd, paramIntent.getStringExtra("intentType"));
  }
  
  public void onSingleTap(MMAd paramMMAd)
  {
    MMLog.i("MMBroadcastReceiver", "Millennial Media ad Tap.");
  }
  
  public void overlayClosed(MMAd paramMMAd)
  {
    MMLog.i("MMBroadcastReceiver", "Millennial Media overlay closed.");
  }
  
  public void overlayOpened(MMAd paramMMAd)
  {
    MMLog.i("MMBroadcastReceiver", "Millennial Media overlay opened.");
  }
  
  @Deprecated
  public void overlayTap(MMAd paramMMAd)
  {
    MMLog.i("MMBroadcastReceiver", "Millennial Media overlay Tap.");
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.MMBroadcastReceiver
 * JD-Core Version:    0.7.0.1
 */