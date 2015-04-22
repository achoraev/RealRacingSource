package com.firemint.realracing;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootReceiver
  extends BroadcastReceiver
{
  static final String LOG_TAG = "rr3";
  
  static
  {
    System.loadLibrary("fmodex");
    System.loadLibrary("RealRacing3");
  }
  
  static native void resumeNotification();
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    AppProxy.SetContext(paramContext);
    Log.i("rr3", "BootReceiver onReceive");
    resumeNotification();
    Log.i("rr3", "BootReceiver onReceive done");
    AppProxy.SetContext(null);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.firemint.realracing.BootReceiver
 * JD-Core Version:    0.7.0.1
 */