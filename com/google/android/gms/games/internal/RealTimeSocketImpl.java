package com.google.android.gms.games.internal;

import android.net.LocalSocket;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.games.multiplayer.realtime.RealTimeSocket;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

final class RealTimeSocketImpl
  implements RealTimeSocket
{
  private ParcelFileDescriptor KE;
  private final String Xr;
  private final LocalSocket Ye;
  
  RealTimeSocketImpl(LocalSocket paramLocalSocket, String paramString)
  {
    this.Ye = paramLocalSocket;
    this.Xr = paramString;
  }
  
  public void close()
    throws IOException
  {
    this.Ye.close();
  }
  
  public InputStream getInputStream()
    throws IOException
  {
    return this.Ye.getInputStream();
  }
  
  public OutputStream getOutputStream()
    throws IOException
  {
    return this.Ye.getOutputStream();
  }
  
  public ParcelFileDescriptor getParcelFileDescriptor()
    throws IOException
  {
    if ((this.KE == null) && (!isClosed()))
    {
      Parcel localParcel = Parcel.obtain();
      localParcel.writeFileDescriptor(this.Ye.getFileDescriptor());
      localParcel.setDataPosition(0);
      this.KE = localParcel.readFileDescriptor();
    }
    return this.KE;
  }
  
  public boolean isClosed()
  {
    return (!this.Ye.isConnected()) && (!this.Ye.isBound());
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.internal.RealTimeSocketImpl
 * JD-Core Version:    0.7.0.1
 */