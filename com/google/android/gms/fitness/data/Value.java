package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class Value
  implements SafeParcelable
{
  public static final Parcelable.Creator<Value> CREATOR = new u();
  private final int BR;
  private final int Th;
  private boolean Tv;
  private float Tw;
  
  Value(int paramInt)
  {
    this(1, paramInt, false, 0.0F);
  }
  
  Value(int paramInt1, int paramInt2, boolean paramBoolean, float paramFloat)
  {
    this.BR = paramInt1;
    this.Th = paramInt2;
    this.Tv = paramBoolean;
    this.Tw = paramFloat;
  }
  
  private boolean a(Value paramValue)
  {
    if ((this.Th == paramValue.Th) && (this.Tv == paramValue.Tv))
    {
      switch (this.Th)
      {
      default: 
        if (this.Tw != paramValue.Tw) {
          break;
        }
      case 1: 
      case 2: 
        do
        {
          do
          {
            return true;
          } while (asInt() == paramValue.asInt());
          return false;
        } while (asFloat() == paramValue.asFloat());
        return false;
      }
      return false;
    }
    return false;
  }
  
  public float asFloat()
  {
    if (this.Th == 2) {}
    for (boolean bool = true;; bool = false)
    {
      o.a(bool, "Value is not in float format");
      return this.Tw;
    }
  }
  
  public int asInt()
  {
    int i = 1;
    if (this.Th == i) {}
    for (;;)
    {
      o.a(i, "Value is not in int format");
      return Float.floatToRawIntBits(this.Tw);
      int j = 0;
    }
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (this == paramObject) || (((paramObject instanceof Value)) && (a((Value)paramObject)));
  }
  
  public int getFormat()
  {
    return this.Th;
  }
  
  int getVersionCode()
  {
    return this.BR;
  }
  
  public int hashCode()
  {
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = Float.valueOf(this.Tw);
    arrayOfObject[1] = Integer.valueOf(this.Th);
    arrayOfObject[2] = Boolean.valueOf(this.Tv);
    return n.hashCode(arrayOfObject);
  }
  
  public boolean isSet()
  {
    return this.Tv;
  }
  
  float ja()
  {
    return this.Tw;
  }
  
  public void setFloat(float paramFloat)
  {
    if (this.Th == 2) {}
    for (boolean bool = true;; bool = false)
    {
      o.a(bool, "Attempting to set an float value to a field that is not in FLOAT format.  Please check the data type definition and use the right format.");
      this.Tv = true;
      this.Tw = paramFloat;
      return;
    }
  }
  
  public void setInt(int paramInt)
  {
    if (this.Th == 1) {}
    for (boolean bool = true;; bool = false)
    {
      o.a(bool, "Attempting to set an int value to a field that is not in INT32 format.  Please check the data type definition and use the right format.");
      this.Tv = true;
      this.Tw = Float.intBitsToFloat(paramInt);
      return;
    }
  }
  
  public String toString()
  {
    if (!this.Tv) {
      return "unset";
    }
    switch (this.Th)
    {
    default: 
      return "unknown";
    case 1: 
      return Integer.toString(asInt());
    }
    return Float.toString(asFloat());
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    u.a(this, paramParcel, paramInt);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.fitness.data.Value
 * JD-Core Version:    0.7.0.1
 */