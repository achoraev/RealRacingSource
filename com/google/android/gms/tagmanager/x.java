package com.google.android.gms.tagmanager;

import android.util.Log;

class x
  implements bi
{
  private int xW = 5;
  
  public void S(String paramString)
  {
    if (this.xW <= 3) {
      Log.d("GoogleTagManager", paramString);
    }
  }
  
  public void T(String paramString)
  {
    if (this.xW <= 6) {
      Log.e("GoogleTagManager", paramString);
    }
  }
  
  public void U(String paramString)
  {
    if (this.xW <= 4) {
      Log.i("GoogleTagManager", paramString);
    }
  }
  
  public void V(String paramString)
  {
    if (this.xW <= 2) {
      Log.v("GoogleTagManager", paramString);
    }
  }
  
  public void W(String paramString)
  {
    if (this.xW <= 5) {
      Log.w("GoogleTagManager", paramString);
    }
  }
  
  public void b(String paramString, Throwable paramThrowable)
  {
    if (this.xW <= 6) {
      Log.e("GoogleTagManager", paramString, paramThrowable);
    }
  }
  
  public void d(String paramString, Throwable paramThrowable)
  {
    if (this.xW <= 5) {
      Log.w("GoogleTagManager", paramString, paramThrowable);
    }
  }
  
  public void setLogLevel(int paramInt)
  {
    this.xW = paramInt;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.x
 * JD-Core Version:    0.7.0.1
 */