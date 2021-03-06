package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Iterator;
import java.util.Set;

public class ni
  implements SafeParcelable
{
  public static final nk CREATOR = new nk();
  public final long akH;
  public final byte[] akI;
  public final Bundle akJ;
  public final String tag;
  public final int versionCode;
  
  ni(int paramInt, long paramLong, String paramString, byte[] paramArrayOfByte, Bundle paramBundle)
  {
    this.versionCode = paramInt;
    this.akH = paramLong;
    this.tag = paramString;
    this.akI = paramArrayOfByte;
    this.akJ = paramBundle;
  }
  
  public ni(long paramLong, String paramString, byte[] paramArrayOfByte, String... paramVarArgs)
  {
    this.versionCode = 1;
    this.akH = paramLong;
    this.tag = paramString;
    this.akI = paramArrayOfByte;
    this.akJ = f(paramVarArgs);
  }
  
  private static Bundle f(String... paramVarArgs)
  {
    Bundle localBundle = null;
    if (paramVarArgs == null) {}
    for (;;)
    {
      return localBundle;
      if (paramVarArgs.length % 2 != 0) {
        throw new IllegalArgumentException("extras must have an even number of elements");
      }
      int i = paramVarArgs.length / 2;
      localBundle = null;
      if (i != 0)
      {
        localBundle = new Bundle(i);
        for (int j = 0; j < i; j++) {
          localBundle.putString(paramVarArgs[(j * 2)], paramVarArgs[(1 + j * 2)]);
        }
      }
    }
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("tag=").append(this.tag).append(",");
    localStringBuilder.append("eventTime=").append(this.akH).append(",");
    if ((this.akJ != null) && (!this.akJ.isEmpty()))
    {
      localStringBuilder.append("keyValues=");
      Iterator localIterator = this.akJ.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        localStringBuilder.append("(").append(str).append(",");
        localStringBuilder.append(this.akJ.getString(str)).append(")");
        localStringBuilder.append(" ");
      }
    }
    return localStringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    nk.a(this, paramParcel, paramInt);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.ni
 * JD-Core Version:    0.7.0.1
 */