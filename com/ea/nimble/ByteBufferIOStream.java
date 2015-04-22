package com.ea.nimble;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class ByteBufferIOStream
{
  protected static final int SEGMENT_SIZE = 4096;
  protected int m_availableSegment = 0;
  protected LinkedList<byte[]> m_buffer = new LinkedList();
  protected boolean m_closed = false;
  protected ByteBufferInputStream m_input = new ByteBufferInputStream();
  protected ByteBufferOutputStream m_output = new ByteBufferOutputStream();
  protected int m_readPosition = 0;
  protected int m_writePosition = 0;
  
  public ByteBufferIOStream()
  {
    this(1);
  }
  
  public ByteBufferIOStream(int paramInt)
  {
    if (paramInt <= 0) {
      paramInt = 1;
    }
    int i = 1 + (paramInt - 1) / 4096;
    for (int j = 0; j < i; j++) {
      this.m_buffer.add(new byte[4096]);
    }
  }
  
  public void appendSegmentToBuffer(byte[] paramArrayOfByte, int paramInt)
    throws IOException
  {
    if ((this.m_writePosition == 0) && (paramArrayOfByte.length == 4096))
    {
      ListIterator localListIterator = this.m_buffer.listIterator();
      for (int i = 0; i < this.m_availableSegment; i++) {
        localListIterator.next();
      }
      localListIterator.add(paramArrayOfByte);
      if (paramInt != 4096)
      {
        this.m_writePosition = paramInt;
        return;
      }
      this.m_availableSegment = (1 + this.m_availableSegment);
      return;
    }
    getOutputStream().write(paramArrayOfByte, 0, paramInt);
  }
  
  public int available()
    throws IOException
  {
    return this.m_input.available();
  }
  
  public void clear()
  {
    this.m_closed = false;
    this.m_availableSegment = 0;
    this.m_writePosition = 0;
    this.m_readPosition = 0;
  }
  
  protected void closeIOStream()
  {
    this.m_closed = true;
  }
  
  public InputStream getInputStream()
  {
    return this.m_input;
  }
  
  public OutputStream getOutputStream()
  {
    return this.m_output;
  }
  
  public byte[] growBufferBySegment()
    throws IOException
  {
    if (this.m_writePosition != 0) {
      throw new IOException("Bad location to grow buffer");
    }
    ListIterator localListIterator = this.m_buffer.listIterator();
    for (int i = 0; i < this.m_availableSegment; i++) {
      localListIterator.next();
    }
    byte[] arrayOfByte = new byte[4096];
    localListIterator.add(arrayOfByte);
    this.m_availableSegment = (1 + this.m_availableSegment);
    return arrayOfByte;
  }
  
  public byte[] prepareSegment()
  {
    if (1 + this.m_availableSegment >= this.m_buffer.size()) {
      return new byte[4096];
    }
    if (this.m_buffer.size() == 0) {
      return null;
    }
    return (byte[])this.m_buffer.removeLast();
  }
  
  protected class ByteBufferInputStream
    extends InputStream
  {
    protected ByteBufferInputStream() {}
    
    public int available()
      throws IOException
    {
      if (ByteBufferIOStream.this.m_closed) {
        throw new IOException("ByteBufferIOStream is closed");
      }
      return 4096 * ByteBufferIOStream.this.m_availableSegment + ByteBufferIOStream.this.m_writePosition - ByteBufferIOStream.this.m_readPosition;
    }
    
    public void close()
      throws IOException
    {
      ByteBufferIOStream.this.closeIOStream();
    }
    
    public boolean markSupported()
    {
      return false;
    }
    
    public int read()
      throws IOException
    {
      if (available() <= 0) {
        throw new IOException("Nothing to read in ByteBufferIOStream");
      }
      int i = ((byte[])ByteBufferIOStream.this.m_buffer.getFirst())[ByteBufferIOStream.this.m_readPosition];
      ByteBufferIOStream localByteBufferIOStream1 = ByteBufferIOStream.this;
      localByteBufferIOStream1.m_readPosition = (1 + localByteBufferIOStream1.m_readPosition);
      if (ByteBufferIOStream.this.m_readPosition >= 4096)
      {
        ByteBufferIOStream.this.m_buffer.add(ByteBufferIOStream.this.m_buffer.poll());
        ByteBufferIOStream.this.m_readPosition = 0;
        ByteBufferIOStream localByteBufferIOStream2 = ByteBufferIOStream.this;
        localByteBufferIOStream2.m_availableSegment = (-1 + localByteBufferIOStream2.m_availableSegment);
      }
      return i;
    }
    
    public int read(byte[] paramArrayOfByte)
      throws IOException
    {
      return read(paramArrayOfByte, 0, paramArrayOfByte.length);
    }
    
    public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws IOException
    {
      if ((paramInt1 < 0) || (paramInt2 < 0) || (paramInt1 + paramInt2 > paramArrayOfByte.length)) {
        throw new IndexOutOfBoundsException("The reading range of out of buffer boundary.");
      }
      int i = available();
      if (i <= 0) {
        return -1;
      }
      if (paramInt2 > i) {
        paramInt2 = i;
      }
      int j = paramInt2;
      int k = 4096 - ByteBufferIOStream.this.m_readPosition;
      if (j < k)
      {
        System.arraycopy(ByteBufferIOStream.this.m_buffer.getFirst(), ByteBufferIOStream.this.m_readPosition, paramArrayOfByte, paramInt1, j);
        ByteBufferIOStream localByteBufferIOStream2 = ByteBufferIOStream.this;
        localByteBufferIOStream2.m_readPosition = (j + localByteBufferIOStream2.m_readPosition);
      }
      for (;;)
      {
        return paramInt2;
        System.arraycopy(ByteBufferIOStream.this.m_buffer.getFirst(), ByteBufferIOStream.this.m_readPosition, paramArrayOfByte, paramInt1, k);
        ByteBufferIOStream.this.m_buffer.add(ByteBufferIOStream.this.m_buffer.poll());
        int m = j - k;
        int n = paramInt1 + k;
        int i1 = m / 4096;
        for (int i2 = 0; i2 < i1; i2++)
        {
          System.arraycopy(ByteBufferIOStream.this.m_buffer.getFirst(), 0, paramArrayOfByte, n, 4096);
          ByteBufferIOStream.this.m_buffer.add(ByteBufferIOStream.this.m_buffer.poll());
          m -= 4096;
          n += 4096;
        }
        System.arraycopy(ByteBufferIOStream.this.m_buffer.getFirst(), 0, paramArrayOfByte, n, m);
        ByteBufferIOStream.this.m_readPosition = m;
        ByteBufferIOStream localByteBufferIOStream1 = ByteBufferIOStream.this;
        localByteBufferIOStream1.m_availableSegment -= i1 + 1;
      }
    }
    
    public long skip(long paramLong)
      throws IOException
    {
      int i = available();
      if (i <= 0) {
        return 0L;
      }
      if (paramLong > i) {
        paramLong = i;
      }
      int j = (int)paramLong;
      int k = 4096 - ByteBufferIOStream.this.m_readPosition;
      if (j < k)
      {
        ByteBufferIOStream localByteBufferIOStream2 = ByteBufferIOStream.this;
        localByteBufferIOStream2.m_readPosition = (j + localByteBufferIOStream2.m_readPosition);
      }
      for (;;)
      {
        return paramLong;
        ByteBufferIOStream.this.m_buffer.add(ByteBufferIOStream.this.m_buffer.poll());
        int m = j - k;
        int n = m / 4096;
        for (int i1 = 0; i1 < n; i1++)
        {
          ByteBufferIOStream.this.m_buffer.add(ByteBufferIOStream.this.m_buffer.poll());
          m -= 4096;
        }
        ByteBufferIOStream.this.m_readPosition = m;
        ByteBufferIOStream localByteBufferIOStream1 = ByteBufferIOStream.this;
        localByteBufferIOStream1.m_availableSegment -= n + 1;
      }
    }
  }
  
  protected class ByteBufferOutputStream
    extends OutputStream
  {
    protected ByteBufferOutputStream() {}
    
    public void close()
      throws IOException
    {
      ByteBufferIOStream.this.closeIOStream();
    }
    
    public void write(int paramInt)
      throws IOException
    {
      if (ByteBufferIOStream.this.m_closed) {
        throw new IOException("ByteBufferIOStream is closed");
      }
      ((byte[])ByteBufferIOStream.this.m_buffer.getFirst())[ByteBufferIOStream.this.m_writePosition] = ((byte)paramInt);
      ByteBufferIOStream localByteBufferIOStream1 = ByteBufferIOStream.this;
      localByteBufferIOStream1.m_writePosition = (1 + localByteBufferIOStream1.m_writePosition);
      if (ByteBufferIOStream.this.m_writePosition == 4096)
      {
        ByteBufferIOStream.this.m_writePosition = 0;
        ByteBufferIOStream localByteBufferIOStream2 = ByteBufferIOStream.this;
        localByteBufferIOStream2.m_availableSegment = (1 + localByteBufferIOStream2.m_availableSegment);
      }
    }
    
    public void write(byte[] paramArrayOfByte)
      throws IOException
    {
      write(paramArrayOfByte, 0, paramArrayOfByte.length);
    }
    
    public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws IOException
    {
      if ((paramInt1 < 0) || (paramInt2 < 0) || (paramInt1 + paramInt2 > paramArrayOfByte.length)) {
        throw new IndexOutOfBoundsException("The writing range is out of buffer boundary.");
      }
      if (ByteBufferIOStream.this.m_closed) {
        throw new IOException("ByteBufferIOStream is closed");
      }
      int i = 4096 - ByteBufferIOStream.this.m_writePosition;
      Iterator localIterator = ByteBufferIOStream.this.m_buffer.iterator();
      for (int j = 0; j < ByteBufferIOStream.this.m_availableSegment; j++) {
        localIterator.next();
      }
      if (paramInt2 < i)
      {
        System.arraycopy(paramArrayOfByte, paramInt1, localIterator.next(), ByteBufferIOStream.this.m_writePosition, paramInt2);
        ByteBufferIOStream localByteBufferIOStream3 = ByteBufferIOStream.this;
        localByteBufferIOStream3.m_writePosition = (paramInt2 + localByteBufferIOStream3.m_writePosition);
      }
      for (;;)
      {
        return;
        System.arraycopy(paramArrayOfByte, paramInt1, localIterator.next(), ByteBufferIOStream.this.m_writePosition, i);
        int k = paramInt2 - i;
        int m = paramInt1 + i;
        ByteBufferIOStream localByteBufferIOStream1 = ByteBufferIOStream.this;
        localByteBufferIOStream1.m_availableSegment = (1 + localByteBufferIOStream1.m_availableSegment);
        ByteBufferIOStream.this.m_writePosition = 0;
        while (k > 0)
        {
          byte[] arrayOfByte;
          if (localIterator.hasNext()) {
            arrayOfByte = (byte[])localIterator.next();
          }
          for (;;)
          {
            if (k >= 4096) {
              break label288;
            }
            System.arraycopy(paramArrayOfByte, m, arrayOfByte, 0, k);
            ByteBufferIOStream.this.m_writePosition = k;
            k = 0;
            break;
            arrayOfByte = new byte[4096];
            ByteBufferIOStream.this.m_buffer.add(arrayOfByte);
          }
          label288:
          System.arraycopy(paramArrayOfByte, m, arrayOfByte, 0, 4096);
          k -= 4096;
          m += 4096;
          ByteBufferIOStream localByteBufferIOStream2 = ByteBufferIOStream.this;
          localByteBufferIOStream2.m_availableSegment = (1 + localByteBufferIOStream2.m_availableSegment);
        }
      }
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ea.nimble.ByteBufferIOStream
 * JD-Core Version:    0.7.0.1
 */