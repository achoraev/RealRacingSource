package com.ea.nimble;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;

public class Timer
{
  private static Handler s_handler = new Handler(Looper.getMainLooper());
  private long m_fireTime;
  private long m_pauseTime;
  private boolean m_paused;
  private boolean m_running;
  private Runnable m_task;
  private Runnable m_taskToRun;
  private long m_timeInterval;
  
  public Timer(Runnable paramRunnable)
  {
    this.m_task = paramRunnable;
    this.m_running = false;
    this.m_paused = false;
  }
  
  public void cancel()
  {
    if (this.m_running)
    {
      if (!this.m_paused) {
        s_handler.removeCallbacks(this.m_taskToRun);
      }
      this.m_running = false;
    }
  }
  
  public void fire()
  {
    cancel();
    this.m_task.run();
    if ((this.m_taskToRun instanceof RepeatingTask))
    {
      if (this.m_paused) {
        this.m_fireTime = (this.m_pauseTime + this.m_timeInterval);
      }
    }
    else {
      return;
    }
    this.m_fireTime = (SystemClock.uptimeMillis() + this.m_timeInterval);
    s_handler.postDelayed(this.m_taskToRun, this.m_timeInterval);
    this.m_running = true;
  }
  
  public boolean isPaused()
  {
    return this.m_paused;
  }
  
  public boolean isRunning()
  {
    return this.m_running;
  }
  
  public void pause()
  {
    if ((!this.m_paused) && (this.m_running))
    {
      this.m_pauseTime = SystemClock.uptimeMillis();
      s_handler.removeCallbacks(this.m_taskToRun);
      this.m_paused = true;
    }
  }
  
  public void resume()
  {
    if ((this.m_paused) && (this.m_running))
    {
      this.m_fireTime += SystemClock.uptimeMillis() - this.m_pauseTime;
      s_handler.postAtTime(this.m_taskToRun, this.m_fireTime);
      this.m_paused = false;
    }
  }
  
  public void schedule(double paramDouble, boolean paramBoolean)
  {
    cancel();
    if (paramDouble < 0.1D)
    {
      if (paramBoolean)
      {
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Double.valueOf(paramDouble);
        Log.Helper.LOGES(null, "Timer scheduled to repeat for %.2f seconds, running only once", arrayOfObject);
      }
      if (Looper.myLooper() == Looper.getMainLooper())
      {
        this.m_task.run();
        return;
      }
      s_handler.post(this.m_task);
      return;
    }
    this.m_timeInterval = ((1000.0D * paramDouble));
    this.m_fireTime = (SystemClock.uptimeMillis() + this.m_timeInterval);
    if (paramBoolean) {}
    for (this.m_taskToRun = new RepeatingTask(null);; this.m_taskToRun = new SingleRunTask(null))
    {
      s_handler.postDelayed(this.m_taskToRun, this.m_timeInterval);
      this.m_running = true;
      return;
    }
  }
  
  private class RepeatingTask
    implements Runnable
  {
    private RepeatingTask() {}
    
    public void run()
    {
      if ((!Timer.this.m_paused) && (Timer.this.m_running))
      {
        Timer.this.m_task.run();
        Timer.access$302(Timer.this, SystemClock.uptimeMillis() + Timer.this.m_fireTime);
        Timer.s_handler.postDelayed(this, Timer.this.m_timeInterval);
      }
    }
  }
  
  private class SingleRunTask
    implements Runnable
  {
    private SingleRunTask() {}
    
    public void run()
    {
      if ((!Timer.this.m_paused) && (Timer.this.m_running))
      {
        Timer.access$102(Timer.this, false);
        Timer.this.m_task.run();
      }
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ea.nimble.Timer
 * JD-Core Version:    0.7.0.1
 */