package com.google.android.a;

import com.google.b.a.a;
import java.io.IOException;

class l
  implements j
{
  private a a;
  private byte[] b;
  private final int c;
  
  public l(int paramInt)
  {
    this.c = paramInt;
    a();
  }
  
  public void a()
  {
    this.b = new byte[this.c];
    this.a = a.a(this.b);
  }
  
  public void a(int paramInt, long paramLong)
    throws IOException
  {
    this.a.a(paramInt, paramLong);
  }
  
  public void a(int paramInt, String paramString)
    throws IOException
  {
    this.a.a(paramInt, paramString);
  }
  
  public byte[] b()
    throws IOException
  {
    int i = this.a.a();
    if (i < 0) {
      throw new IOException();
    }
    if (i == 0) {
      return this.b;
    }
    byte[] arrayOfByte = new byte[this.b.length - i];
    System.arraycopy(this.b, 0, arrayOfByte, 0, arrayOfByte.length);
    return arrayOfByte;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.a.l
 * JD-Core Version:    0.7.0.1
 */