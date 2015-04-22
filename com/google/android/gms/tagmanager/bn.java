package com.google.android.gms.tagmanager;

import android.os.Build.VERSION;

class bn
{
  int nP()
  {
    return Build.VERSION.SDK_INT;
  }
  
  public bm ox()
  {
    if (nP() < 8) {
      return new av();
    }
    return new aw();
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.bn
 * JD-Core Version:    0.7.0.1
 */