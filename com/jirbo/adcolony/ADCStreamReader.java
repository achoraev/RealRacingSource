package com.jirbo.adcolony;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

class ADCStreamReader
  extends InputStream
{
  byte[] buffer = new byte[1024];
  int buffer_count;
  int buffer_position;
  int dx;
  InputStream input;
  int position;
  int size;
  int x;
  
  ADCStreamReader(String paramString)
    throws IOException
  {
    if (ADC.log_level != 0)
    {
      this.dx = 23;
      this.x = 23;
    }
    this.size = ((int)new File(paramString).length());
    this.input = new FileInputStream(paramString);
  }
  
  public int available()
    throws IOException
  {
    return this.buffer_count - this.buffer_position + this.input.available();
  }
  
  public void close()
    throws IOException
  {
    this.input.close();
  }
  
  void fillBuffer()
    throws IOException
  {
    for (this.buffer_count = 0; this.buffer_count == 0; this.buffer_count = this.input.read(this.buffer, 0, 1024)) {}
    for (int i = 0; i < this.buffer_count; i++)
    {
      this.buffer[i] = ((byte)(this.buffer[i] ^ this.x));
      this.x += this.dx;
    }
    this.buffer_position = 0;
  }
  
  public void mark(int paramInt) {}
  
  public boolean markSupported()
  {
    return false;
  }
  
  public int read()
    throws IOException
  {
    if (this.position == this.size) {
      return -1;
    }
    if (this.buffer_position >= this.buffer_count) {
      fillBuffer();
    }
    this.position = (1 + this.position);
    byte[] arrayOfByte = this.buffer;
    int i = this.buffer_position;
    this.buffer_position = (i + 1);
    return arrayOfByte[i];
  }
  
  public int read(byte[] paramArrayOfByte)
    throws IOException
  {
    return read(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    int j;
    if (this.position == this.size) {
      j = -1;
    }
    for (;;)
    {
      return j;
      int i = this.size - this.position;
      if (paramInt2 > i) {
        paramInt2 = i;
      }
      j = 0;
      while (paramInt2 > 0)
      {
        if (this.buffer_position == this.buffer_count) {
          fillBuffer();
        }
        if (paramInt2 < this.buffer_count) {}
        int n;
        for (int k = paramInt2;; k = this.buffer_count)
        {
          int m = 0;
          int i1;
          for (n = paramInt1; m < k; n = i1)
          {
            i1 = n + 1;
            byte[] arrayOfByte = this.buffer;
            int i2 = this.buffer_position;
            this.buffer_position = (i2 + 1);
            paramArrayOfByte[n] = arrayOfByte[i2];
            m++;
          }
        }
        paramInt2 -= k;
        j += k;
        this.position = (k + this.position);
        paramInt1 = n;
      }
    }
  }
  
  public void reset()
    throws IOException
  {
    throw new IOException("ADCStreamReader does not support reset().");
  }
  
  public long skip(long paramLong)
    throws IOException
  {
    throw new IOException("ADCStreamReader does not support skip().");
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.jirbo.adcolony.ADCStreamReader
 * JD-Core Version:    0.7.0.1
 */