package com.ea.nimble.tracking;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import java.net.URLDecoder;
import java.util.Observable;

public class ReferrerReceiver
  extends BroadcastReceiver
{
  private static final ObservableChanged _observable = new ObservableChanged();
  
  public ReferrerReceiver()
  {
    Log.i("ReferrerReceiver", "OnReceive");
  }
  
  public static void clearReferrer(Context paramContext)
  {
    paramContext.getSharedPreferences("referrer", 0).edit().putString("referrer", "").commit();
  }
  
  public static ObservableChanged getObservable()
  {
    return _observable;
  }
  
  public static String getReferrer(Context paramContext)
  {
    return paramContext.getSharedPreferences("referrer", 0).getString("referrer", null);
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if (paramIntent != null) {}
    try
    {
      if (paramIntent.getAction().equals("com.android.vending.INSTALL_REFERRER"))
      {
        String str1 = paramIntent.getStringExtra("referrer");
        if (str1 != null)
        {
          String str2 = URLDecoder.decode(str1, "UTF-8");
          paramContext.getSharedPreferences("referrer", 0).edit().putString("referrer", str2).commit();
          _observable.notifyObservers(str2);
        }
      }
      return;
    }
    catch (Exception localException) {}
  }
  
  protected static class ObservableChanged
    extends Observable
  {
    public boolean hasChanged()
    {
      return true;
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ea.nimble.tracking.ReferrerReceiver
 * JD-Core Version:    0.7.0.1
 */