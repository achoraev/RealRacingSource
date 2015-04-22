package com.google.android.gms.tagmanager;

import android.text.TextUtils;

class ap
{
  private final long AF;
  private final long AG;
  private final long apm;
  private String apn;
  
  ap(long paramLong1, long paramLong2, long paramLong3)
  {
    this.AF = paramLong1;
    this.AG = paramLong2;
    this.apm = paramLong3;
  }
  
  void ak(String paramString)
  {
    if ((paramString == null) || (TextUtils.isEmpty(paramString.trim()))) {
      return;
    }
    this.apn = paramString;
  }
  
  long eG()
  {
    return this.AF;
  }
  
  long ot()
  {
    return this.apm;
  }
  
  String ou()
  {
    return this.apn;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.ap
 * JD-Core Version:    0.7.0.1
 */