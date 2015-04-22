package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.ju;

class bf
  implements cg
{
  private final long AN;
  private final int AO;
  private double AP;
  private long AQ;
  private final Object AR = new Object();
  private final String AS;
  private final long apL;
  private final ju yD;
  
  public bf(int paramInt, long paramLong1, long paramLong2, String paramString, ju paramju)
  {
    this.AO = paramInt;
    this.AP = this.AO;
    this.AN = paramLong1;
    this.apL = paramLong2;
    this.AS = paramString;
    this.yD = paramju;
  }
  
  public boolean eJ()
  {
    synchronized (this.AR)
    {
      long l = this.yD.currentTimeMillis();
      if (l - this.AQ < this.apL)
      {
        bh.W("Excessive " + this.AS + " detected; call ignored.");
        return false;
      }
      if (this.AP < this.AO)
      {
        double d = (l - this.AQ) / this.AN;
        if (d > 0.0D) {
          this.AP = Math.min(this.AO, d + this.AP);
        }
      }
      this.AQ = l;
      if (this.AP >= 1.0D)
      {
        this.AP -= 1.0D;
        return true;
      }
    }
    bh.W("Excessive " + this.AS + " detected; call ignored.");
    return false;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.bf
 * JD-Core Version:    0.7.0.1
 */