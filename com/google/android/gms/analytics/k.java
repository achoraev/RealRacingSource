package com.google.android.gms.analytics;

import android.util.Log;

class k
  implements Logger
{
  private int xW = 2;
  
  private String ae(String paramString)
  {
    return Thread.currentThread().toString() + ": " + paramString;
  }
  
  public void error(Exception paramException)
  {
    if (this.xW <= 3) {
      Log.e("GAV4", null, paramException);
    }
  }
  
  public void error(String paramString)
  {
    if (this.xW <= 3) {
      Log.e("GAV4", ae(paramString));
    }
  }
  
  public int getLogLevel()
  {
    return this.xW;
  }
  
  public void info(String paramString)
  {
    if (this.xW <= 1) {
      Log.i("GAV4", ae(paramString));
    }
  }
  
  public void setLogLevel(int paramInt)
  {
    this.xW = paramInt;
  }
  
  public void verbose(String paramString)
  {
    if (this.xW <= 0) {
      Log.v("GAV4", ae(paramString));
    }
  }
  
  public void warn(String paramString)
  {
    if (this.xW <= 2) {
      Log.w("GAV4", ae(paramString));
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.analytics.k
 * JD-Core Version:    0.7.0.1
 */