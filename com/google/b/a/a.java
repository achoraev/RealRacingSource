package com.google.b.a;

import java.io.IOException;

public final class a
{
  private final byte[] a;
  private final int b;
  private int c;
  
  private a(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    this.a = paramArrayOfByte;
    this.c = paramInt1;
    this.b = (paramInt1 + paramInt2);
  }
  
  public static a a(byte[] paramArrayOfByte)
  {
    return a(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public static a a(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    return new a(paramArrayOfByte, paramInt1, paramInt2);
  }
  
  public int a()
  {
    return this.b - this.c;
  }
  
  public void a(byte paramByte)
    throws IOException
  {
    if (this.c == this.b) {
      throw new a(this.c, this.b);
    }
    byte[] arrayOfByte = this.a;
    int i = this.c;
    this.c = (i + 1);
    arrayOfByte[i] = paramByte;
  }
  
  public void a(int paramInt)
    throws IOException
  {
    a((byte)paramInt);
  }
  
  public void a(int paramInt1, int paramInt2)
    throws IOException
  {
    b(b.a(paramInt1, paramInt2));
  }
  
  public void a(int paramInt, long paramLong)
    throws IOException
  {
    a(paramInt, 0);
    a(paramLong);
  }
  
  public void a(int paramInt, String paramString)
    throws IOException
  {
    a(paramInt, 2);
    a(paramString);
  }
  
  public void a(long paramLong)
    throws IOException
  {
    b(paramLong);
  }
  
  public void a(String paramString)
    throws IOException
  {
    byte[] arrayOfByte = paramString.getBytes("UTF-8");
    b(arrayOfByte.length);
    b(arrayOfByte);
  }
  
  public void b(int paramInt)
    throws IOException
  {
    for (;;)
    {
      if ((paramInt & 0xFFFFFF80) == 0)
      {
        a(paramInt);
        return;
      }
      a(0x80 | paramInt & 0x7F);
      paramInt >>>= 7;
    }
  }
  
  public void b(long paramLong)
    throws IOException
  {
    for (;;)
    {
      if ((0xFFFFFF80 & paramLong) == 0L)
      {
        a((int)paramLong);
        return;
      }
      a(0x80 | 0x7F & (int)paramLong);
      paramLong >>>= 7;
    }
  }
  
  public void b(byte[] paramArrayOfByte)
    throws IOException
  {
    b(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public void b(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (this.b - this.c >= paramInt2)
    {
      System.arraycopy(paramArrayOfByte, paramInt1, this.a, this.c, paramInt2);
      this.c = (paramInt2 + this.c);
      return;
    }
    throw new a(this.c, this.b);
  }
  
  public static class a
    extends IOException
  {
    a(int paramInt1, int paramInt2)
    {
      super();
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.b.a.a
 * JD-Core Version:    0.7.0.1
 */