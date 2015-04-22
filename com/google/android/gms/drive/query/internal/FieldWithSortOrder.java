package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Locale;

public class FieldWithSortOrder
  implements SafeParcelable
{
  public static final c CREATOR = new c();
  final int BR;
  final String PB;
  final boolean QN;
  
  FieldWithSortOrder(int paramInt, String paramString, boolean paramBoolean)
  {
    this.BR = paramInt;
    this.PB = paramString;
    this.QN = paramBoolean;
  }
  
  public FieldWithSortOrder(String paramString, boolean paramBoolean)
  {
    this(1, paramString, paramBoolean);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public String toString()
  {
    Locale localLocale = Locale.US;
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = this.PB;
    if (this.QN) {}
    for (String str = "ASC";; str = "DESC")
    {
      arrayOfObject[1] = str;
      return String.format(localLocale, "FieldWithSortOrder[%s %s]", arrayOfObject);
    }
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    c.a(this, paramParcel, paramInt);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.query.internal.FieldWithSortOrder
 * JD-Core Version:    0.7.0.1
 */