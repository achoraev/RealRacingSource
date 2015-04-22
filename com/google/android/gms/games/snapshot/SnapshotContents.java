package com.google.android.gms.games.snapshot;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.games.internal.GamesLog;
import com.google.android.gms.internal.jy;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

public final class SnapshotContents
  implements SafeParcelable
{
  public static final SnapshotContentsCreator CREATOR = new SnapshotContentsCreator();
  private static final Object adg = new Object();
  private final int BR;
  private Contents Ox;
  
  SnapshotContents(int paramInt, Contents paramContents)
  {
    this.BR = paramInt;
    this.Ox = paramContents;
  }
  
  public SnapshotContents(Contents paramContents)
  {
    this(1, paramContents);
  }
  
  private boolean a(int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    if (!isClosed()) {}
    for (boolean bool = true;; bool = false)
    {
      o.a(bool, "Must provide a previously opened SnapshotContents");
      synchronized (adg)
      {
        FileOutputStream localFileOutputStream = new FileOutputStream(this.Ox.getParcelFileDescriptor().getFileDescriptor());
        BufferedOutputStream localBufferedOutputStream = new BufferedOutputStream(localFileOutputStream);
        try
        {
          FileChannel localFileChannel = localFileOutputStream.getChannel();
          localFileChannel.position(paramInt1);
          localBufferedOutputStream.write(paramArrayOfByte, paramInt2, paramInt3);
          if (paramBoolean) {
            localFileChannel.truncate(paramArrayOfByte.length);
          }
          localBufferedOutputStream.flush();
          return true;
        }
        catch (IOException localIOException)
        {
          GamesLog.a("SnapshotContents", "Failed to write snapshot data", localIOException);
          return false;
        }
      }
    }
  }
  
  public void close()
  {
    this.Ox.hJ();
    this.Ox = null;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public Contents getContents()
  {
    return this.Ox;
  }
  
  public ParcelFileDescriptor getParcelFileDescriptor()
  {
    if (!isClosed()) {}
    for (boolean bool = true;; bool = false)
    {
      o.a(bool, "Cannot mutate closed contents!");
      return this.Ox.getParcelFileDescriptor();
    }
  }
  
  public int getVersionCode()
  {
    return this.BR;
  }
  
  public boolean isClosed()
  {
    return this.Ox == null;
  }
  
  public boolean modifyBytes(int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3)
  {
    return a(paramInt1, paramArrayOfByte, paramInt2, paramArrayOfByte.length, false);
  }
  
  public byte[] readFully()
    throws IOException
  {
    boolean bool1 = isClosed();
    boolean bool2 = false;
    if (!bool1) {
      bool2 = true;
    }
    o.a(bool2, "Must provide a previously opened Snapshot");
    synchronized (adg)
    {
      FileInputStream localFileInputStream = new FileInputStream(this.Ox.getParcelFileDescriptor().getFileDescriptor());
      BufferedInputStream localBufferedInputStream = new BufferedInputStream(localFileInputStream);
      try
      {
        localFileInputStream.getChannel().position(0L);
        byte[] arrayOfByte = jy.a(localBufferedInputStream, false);
        localFileInputStream.getChannel().position(0L);
        return arrayOfByte;
      }
      catch (IOException localIOException)
      {
        GamesLog.b("SnapshotContents", "Failed to read snapshot data", localIOException);
        throw localIOException;
      }
    }
  }
  
  public boolean writeBytes(byte[] paramArrayOfByte)
  {
    return a(0, paramArrayOfByte, 0, paramArrayOfByte.length, true);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    SnapshotContentsCreator.a(this, paramParcel, paramInt);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.snapshot.SnapshotContents
 * JD-Core Version:    0.7.0.1
 */