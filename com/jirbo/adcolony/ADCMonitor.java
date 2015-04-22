package com.jirbo.adcolony;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import java.io.PrintStream;

class ADCMonitor
  implements Runnable
{
  public static final int SECONDS_BEFORE_IDLE = 5;
  public static final int SECONDS_BEFORE_SUSPEND = 10;
  static volatile long last_ping_ms;
  static String monitor_mutex = "MONITOR_MUTEX";
  static volatile ADCMonitor singleton;
  
  static void ping()
  {
    synchronized (monitor_mutex)
    {
      last_ping_ms = System.currentTimeMillis();
      if (singleton == null)
      {
        ADC.log_dev("Creating ADC Monitor singleton.");
        singleton = new ADCMonitor();
        new Thread(singleton).start();
      }
      return;
    }
  }
  
  public void run()
  {
    ADC.set_log_level(ADC.log_level);
    ADCLog.dev.println("ADC Monitor Started.");
    ADC.controller.on_resume();
    int i = 0;
    for (;;)
    {
      long l1;
      long l2;
      int k;
      if (!AdColony.activity().isFinishing())
      {
        l1 = System.currentTimeMillis();
        ADC.active = false;
        ADC.controller.update();
        if (!ADC.active) {
          break label164;
        }
        l2 = 50L;
        k = (int)((System.currentTimeMillis() - last_ping_ms) / 1000L);
        ADC.controller.update();
        if ((i != 0) && (k < 10)) {
          break label189;
        }
      }
      synchronized (monitor_mutex)
      {
        singleton = null;
        if (i == 0) {
          ADC.controller.on_suspend();
        }
        if (AdColony.activity().isFinishing())
        {
          ADC.finishing = true;
          sleep(5000L);
          if (ADC.finishing)
          {
            ADCLog.info.println("ADC.finishing, controller on_stop");
            ADC.controller.on_stop();
            ADCThreadPool.reset();
          }
        }
        System.out.println("Exiting monitor");
        return;
        label164:
        if (i != 0) {}
        for (int j = 2000;; j = 250)
        {
          l2 = j;
          break;
        }
        label189:
        if (k < 5)
        {
          i = 0;
          ADC.controller.on_resume();
          ADC.log_dev("AdColony is active.");
        }
        do
        {
          sleep(l2);
          long l3 = System.currentTimeMillis();
          if ((l3 - l1 > 3000L) || (l3 - l1 <= 0L)) {
            break;
          }
          ADCSessionManager localADCSessionManager = ADC.controller.session_manager;
          localADCSessionManager.session_time += (l3 - l1) / 1000.0D;
          break;
        } while (k < 5);
        ADC.log_dev("AdColony is idle.");
        i = 1;
        ADC.controller.on_suspend();
      }
    }
  }
  
  void sleep(long paramLong)
  {
    try
    {
      Thread.sleep(paramLong);
      return;
    }
    catch (InterruptedException localInterruptedException) {}
  }
  
  static class Pinger
    extends Handler
  {
    Pinger()
    {
      sendMessageDelayed(obtainMessage(), 1000L);
    }
    
    public void handleMessage(Message paramMessage)
    {
      if (ADC.activity().isFinishing())
      {
        ADC.log_dev("Monitor pinger exiting.");
        return;
      }
      if (ADC.activity().hasWindowFocus()) {
        ADCMonitor.ping();
      }
      sendMessageDelayed(obtainMessage(), 1000L);
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.jirbo.adcolony.ADCMonitor
 * JD-Core Version:    0.7.0.1
 */