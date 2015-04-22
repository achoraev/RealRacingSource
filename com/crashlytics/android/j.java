package com.crashlytics.android;

import android.os.AsyncTask;

final class j
  extends AsyncTask<Void, Void, Void>
{
  j(CrashTest paramCrashTest, long paramLong) {}
  
  private Void a()
  {
    try
    {
      Thread.sleep(this.a);
      label7:
      this.b.throwRuntimeException("Background thread crash");
      return null;
    }
    catch (InterruptedException localInterruptedException)
    {
      break label7;
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.crashlytics.android.j
 * JD-Core Version:    0.7.0.1
 */