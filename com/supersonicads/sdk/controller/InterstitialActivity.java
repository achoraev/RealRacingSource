package com.supersonicads.sdk.controller;

import android.os.Bundle;
import com.supersonicads.sdk.utils.Logger;

public class InterstitialActivity
  extends ControllerActivity
{
  private static final String TAG = ControllerActivity.class.getSimpleName();
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    Logger.i(TAG, "onCreate");
  }
  
  protected void onPause()
  {
    super.onPause();
    Logger.i(TAG, "onPause");
  }
  
  protected void onResume()
  {
    super.onResume();
    Logger.i(TAG, "onResume");
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.supersonicads.sdk.controller.InterstitialActivity
 * JD-Core Version:    0.7.0.1
 */