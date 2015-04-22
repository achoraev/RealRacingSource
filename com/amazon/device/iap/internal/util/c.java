package com.amazon.device.iap.internal.util;

public enum c
{
  private int d;
  
  static
  {
    c[] arrayOfc = new c[3];
    arrayOfc[0] = a;
    arrayOfc[1] = b;
    arrayOfc[2] = c;
    e = arrayOfc;
  }
  
  private c(int paramInt)
  {
    this.d = paramInt;
  }
  
  public static c[] a()
  {
    return (c[])e.clone();
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.amazon.device.iap.internal.util.c
 * JD-Core Version:    0.7.0.1
 */