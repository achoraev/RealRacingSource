package com.firemint.realracing;

import android.opengl.ETC1Util;
import android.opengl.ETC1Util.ETC1Texture;
import java.nio.ByteBuffer;

public class CarLiveryBaker
{
  static ByteBuffer compressTextureToETC1(ByteBuffer paramByteBuffer, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    return ETC1Util.compressTexture(paramByteBuffer, paramInt1, paramInt2, paramInt3, paramInt4).getData();
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.firemint.realracing.CarLiveryBaker
 * JD-Core Version:    0.7.0.1
 */