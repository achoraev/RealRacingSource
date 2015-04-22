package com.google.android.gms.games.internal;

import android.os.ParcelFileDescriptor;
import android.os.ParcelFileDescriptor.AutoCloseInputStream;
import android.os.ParcelFileDescriptor.AutoCloseOutputStream;
import com.google.android.gms.games.multiplayer.realtime.RealTimeSocket;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class LibjingleNativeSocket
  implements RealTimeSocket
{
  private static final String TAG = LibjingleNativeSocket.class.getSimpleName();
  private final ParcelFileDescriptor KE;
  private final InputStream XX;
  private final OutputStream XY;
  
  LibjingleNativeSocket(ParcelFileDescriptor paramParcelFileDescriptor)
  {
    this.KE = paramParcelFileDescriptor;
    this.XX = new ParcelFileDescriptor.AutoCloseInputStream(paramParcelFileDescriptor);
    this.XY = new ParcelFileDescriptor.AutoCloseOutputStream(paramParcelFileDescriptor);
  }
  
  public void close()
    throws IOException
  {
    this.KE.close();
  }
  
  public InputStream getInputStream()
    throws IOException
  {
    return this.XX;
  }
  
  public OutputStream getOutputStream()
    throws IOException
  {
    return this.XY;
  }
  
  public ParcelFileDescriptor getParcelFileDescriptor()
    throws IOException
  {
    return this.KE;
  }
  
  public boolean isClosed()
  {
    try
    {
      this.XX.available();
      return false;
    }
    catch (IOException localIOException) {}
    return true;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.internal.LibjingleNativeSocket
 * JD-Core Version:    0.7.0.1
 */