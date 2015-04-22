package com.google.android.gms.games.snapshot;

import android.os.Parcel;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.Contents;
import java.io.IOException;

public final class SnapshotEntity
  implements SafeParcelable, Snapshot
{
  public static final SnapshotEntityCreator CREATOR = new SnapshotEntityCreator();
  private final int BR;
  private final SnapshotMetadataEntity adh;
  private final SnapshotContents adi;
  
  SnapshotEntity(int paramInt, SnapshotMetadata paramSnapshotMetadata, SnapshotContents paramSnapshotContents)
  {
    this.BR = paramInt;
    this.adh = new SnapshotMetadataEntity(paramSnapshotMetadata);
    this.adi = paramSnapshotContents;
  }
  
  public SnapshotEntity(SnapshotMetadata paramSnapshotMetadata, SnapshotContents paramSnapshotContents)
  {
    this(2, paramSnapshotMetadata, paramSnapshotContents);
  }
  
  static boolean a(Snapshot paramSnapshot, Object paramObject)
  {
    boolean bool = true;
    if (!(paramObject instanceof Snapshot)) {
      bool = false;
    }
    Snapshot localSnapshot;
    do
    {
      do
      {
        return bool;
      } while (paramSnapshot == paramObject);
      localSnapshot = (Snapshot)paramObject;
    } while ((n.equal(localSnapshot.getMetadata(), paramSnapshot.getMetadata())) && (n.equal(localSnapshot.getSnapshotContents(), paramSnapshot.getSnapshotContents())));
    return false;
  }
  
  static int b(Snapshot paramSnapshot)
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = paramSnapshot.getMetadata();
    arrayOfObject[1] = paramSnapshot.getSnapshotContents();
    return n.hashCode(arrayOfObject);
  }
  
  static String c(Snapshot paramSnapshot)
  {
    n.a locala = n.h(paramSnapshot).a("Metadata", paramSnapshot.getMetadata());
    if (paramSnapshot.getSnapshotContents() != null) {}
    for (boolean bool = true;; bool = false) {
      return locala.a("HasContents", Boolean.valueOf(bool)).toString();
    }
  }
  
  private boolean isClosed()
  {
    return this.adi.isClosed();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return a(this, paramObject);
  }
  
  public Snapshot freeze()
  {
    return this;
  }
  
  public Contents getContents()
  {
    if (isClosed()) {
      return null;
    }
    return this.adi.getContents();
  }
  
  public SnapshotMetadata getMetadata()
  {
    return this.adh;
  }
  
  public SnapshotContents getSnapshotContents()
  {
    if (isClosed()) {
      return null;
    }
    return this.adi;
  }
  
  public int getVersionCode()
  {
    return this.BR;
  }
  
  public int hashCode()
  {
    return b(this);
  }
  
  public boolean isDataValid()
  {
    return true;
  }
  
  public boolean modifyBytes(int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3)
  {
    return this.adi.modifyBytes(paramInt1, paramArrayOfByte, paramInt2, paramInt3);
  }
  
  public byte[] readFully()
  {
    try
    {
      byte[] arrayOfByte = this.adi.readFully();
      return arrayOfByte;
    }
    catch (IOException localIOException)
    {
      throw new RuntimeException(localIOException);
    }
  }
  
  public String toString()
  {
    return c(this);
  }
  
  public boolean writeBytes(byte[] paramArrayOfByte)
  {
    return this.adi.writeBytes(paramArrayOfByte);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    SnapshotEntityCreator.a(this, paramParcel, paramInt);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.snapshot.SnapshotEntity
 * JD-Core Version:    0.7.0.1
 */