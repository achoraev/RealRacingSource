package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class hk
  implements SafeParcelable
{
  public static final hl CREATOR = new hl();
  final int BR;
  final Bundle Ci;
  public final int id;
  
  hk(int paramInt1, int paramInt2, Bundle paramBundle)
  {
    this.BR = paramInt1;
    this.id = paramInt2;
    this.Ci = paramBundle;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    hl.a(this, paramParcel, paramInt);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.hk
 * JD-Core Version:    0.7.0.1
 */