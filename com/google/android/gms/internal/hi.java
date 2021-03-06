package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class hi
  implements SafeParcelable
{
  public static final hj CREATOR = new hj();
  public static final int Cc = Integer.parseInt("-1");
  private static final hq Cd = new hq.a("SsbContext").E(true).at("blob").fm();
  final int BR;
  public final String Ce;
  final hq Cf;
  public final int Cg;
  public final byte[] Ch;
  
  hi(int paramInt1, String paramString, hq paramhq, int paramInt2, byte[] paramArrayOfByte)
  {
    if ((paramInt2 == Cc) || (hp.O(paramInt2) != null)) {}
    for (boolean bool = true;; bool = false)
    {
      o.b(bool, "Invalid section type " + paramInt2);
      this.BR = paramInt1;
      this.Ce = paramString;
      this.Cf = paramhq;
      this.Cg = paramInt2;
      this.Ch = paramArrayOfByte;
      String str = fk();
      if (str == null) {
        break;
      }
      throw new IllegalArgumentException(str);
    }
  }
  
  public hi(String paramString, hq paramhq)
  {
    this(1, paramString, paramhq, Cc, null);
  }
  
  public hi(String paramString1, hq paramhq, String paramString2)
  {
    this(1, paramString1, paramhq, hp.as(paramString2), null);
  }
  
  public hi(byte[] paramArrayOfByte, hq paramhq)
  {
    this(1, null, paramhq, Cc, paramArrayOfByte);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public String fk()
  {
    if ((this.Cg != Cc) && (hp.O(this.Cg) == null)) {
      return "Invalid section type " + this.Cg;
    }
    if ((this.Ce != null) && (this.Ch != null)) {
      return "Both content and blobContent set";
    }
    return null;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    hj.a(this, paramParcel, paramInt);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.hi
 * JD-Core Version:    0.7.0.1
 */