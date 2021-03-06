package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class q
  implements SafeParcelable
{
  public static final Parcelable.Creator<q> CREATOR = new r();
  final int BR;
  private final Session St;
  private final DataSet Ts;
  
  q(int paramInt, Session paramSession, DataSet paramDataSet)
  {
    this.BR = paramInt;
    this.St = paramSession;
    this.Ts = paramDataSet;
  }
  
  private boolean a(q paramq)
  {
    return (n.equal(this.St, paramq.St)) && (n.equal(this.Ts, paramq.Ts));
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (paramObject == this) || (((paramObject instanceof q)) && (a((q)paramObject)));
  }
  
  public Session getSession()
  {
    return this.St;
  }
  
  public int hashCode()
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = this.St;
    arrayOfObject[1] = this.Ts;
    return n.hashCode(arrayOfObject);
  }
  
  public DataSet iW()
  {
    return this.Ts;
  }
  
  public String toString()
  {
    return n.h(this).a("session", this.St).a("dataSet", this.Ts).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    r.a(this, paramParcel, paramInt);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.fitness.data.q
 * JD-Core Version:    0.7.0.1
 */