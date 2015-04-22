package com.firemonkeys.cloudcellapi;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

public class AndroidAssetManagerService
  extends Service
{
  NetworkStatusMonitor m_pMonitor = null;
  
  public native void ProcessDownloadsJNI();
  
  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }
  
  public void onCreate()
  {
    super.onCreate();
    this.m_pMonitor = null;
  }
  
  public void onDestroy()
  {
    super.onDestroy();
    if (this.m_pMonitor != null)
    {
      unregisterReceiver(this.m_pMonitor);
      this.m_pMonitor = null;
    }
  }
  
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    Log.i("Cloudcell", "AndroidAssetManagerService::onStartCommand()");
    new Thread(new Runnable()
    {
      public void run()
      {
        if (AndroidAssetManagerService.this.m_pMonitor == null)
        {
          AndroidAssetManagerService.this.m_pMonitor = new NetworkStatusMonitor();
          IntentFilter localIntentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
          AndroidAssetManagerService.this.registerReceiver(AndroidAssetManagerService.this.m_pMonitor, localIntentFilter);
        }
        AndroidAssetManagerService.this.ProcessDownloadsJNI();
        CC_Activity.stopDownloadService();
      }
    }).start();
    return 2;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.firemonkeys.cloudcellapi.AndroidAssetManagerService
 * JD-Core Version:    0.7.0.1
 */