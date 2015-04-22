package com.firemint.realracing3;

import java.io.Serializable;
import java.nio.ByteBuffer;

public class fmNfcData
  implements Serializable
{
  byte[] m_data;
  int m_nType;
  int m_nVersion;
  
  fmNfcData(int paramInt1, int paramInt2, byte[] paramArrayOfByte)
  {
    this.m_nType = paramInt1;
    this.m_nVersion = paramInt2;
    this.m_data = paramArrayOfByte;
  }
  
  fmNfcData(byte[] paramArrayOfByte)
  {
    ByteBuffer localByteBuffer = ByteBuffer.wrap(Uncompress(paramArrayOfByte));
    this.m_nType = localByteBuffer.getInt();
    this.m_nVersion = localByteBuffer.getInt();
    this.m_data = new byte[localByteBuffer.remaining()];
    localByteBuffer.get(this.m_data, 0, localByteBuffer.remaining());
  }
  
  byte[] Compress(byte[] paramArrayOfByte)
  {
    return paramArrayOfByte;
  }
  
  byte[] GetByteArray()
  {
    ByteBuffer localByteBuffer = ByteBuffer.allocate(8 + this.m_data.length);
    localByteBuffer.putInt(this.m_nType);
    localByteBuffer.putInt(this.m_nVersion);
    localByteBuffer.put(this.m_data);
    return Compress(localByteBuffer.array());
  }
  
  byte[] Uncompress(byte[] paramArrayOfByte)
  {
    return paramArrayOfByte;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.firemint.realracing3.fmNfcData
 * JD-Core Version:    0.7.0.1
 */