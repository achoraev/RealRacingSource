package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.wearable.c;

public class t
  implements SafeParcelable
{
  public static final Parcelable.Creator<t> CREATOR = new u();
  public final c[] avy;
  public final int statusCode;
  public final int versionCode;
  
  t(int paramInt1, int paramInt2, c[] paramArrayOfc)
  {
    this.versionCode = paramInt1;
    this.statusCode = paramInt2;
    this.avy = paramArrayOfc;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    u.a(this, paramParcel, paramInt);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.wearable.internal.t
 * JD-Core Version:    0.7.0.1
 */