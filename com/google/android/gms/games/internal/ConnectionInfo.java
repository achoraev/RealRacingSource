package com.google.android.gms.games.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ConnectionInfo
  implements SafeParcelable
{
  public static final ConnectionInfoCreator CREATOR = new ConnectionInfoCreator();
  private final int BR;
  private final String Wq;
  private final int Wr;
  
  public ConnectionInfo(int paramInt1, String paramString, int paramInt2)
  {
    this.BR = paramInt1;
    this.Wq = paramString;
    this.Wr = paramInt2;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public int getVersionCode()
  {
    return this.BR;
  }
  
  public String jX()
  {
    return this.Wq;
  }
  
  public int jY()
  {
    return this.Wr;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ConnectionInfoCreator.a(this, paramParcel, paramInt);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.internal.ConnectionInfo
 * JD-Core Version:    0.7.0.1
 */