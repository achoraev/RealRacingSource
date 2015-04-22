package com.amazon.device.iap;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.amazon.device.iap.internal.d;
import com.amazon.device.iap.internal.util.e;

public final class ResponseReceiver
  extends BroadcastReceiver
{
  private static final String TAG = ResponseReceiver.class.getSimpleName();
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    try
    {
      d.d().a(paramContext, paramIntent);
      return;
    }
    catch (Exception localException)
    {
      e.b(TAG, "Error in onReceive: " + localException);
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.amazon.device.iap.ResponseReceiver
 * JD-Core Version:    0.7.0.1
 */