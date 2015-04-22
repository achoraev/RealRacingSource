package com.firemonkeys.cloudcellapi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class CC_GoogleStoreBroadcastReceiver_Class
  extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    CC_GoogleStoreService_Class.BroadcastReceive(paramIntent);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.firemonkeys.cloudcellapi.CC_GoogleStoreBroadcastReceiver_Class
 * JD-Core Version:    0.7.0.1
 */