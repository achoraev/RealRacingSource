package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;

public class aj
  implements SafeParcelable
{
  public static final Parcelable.Creator<aj> CREATOR = new ak();
  private final int BR;
  private final DataType Sp;
  private final DataSource Sq;
  
  aj(int paramInt, DataType paramDataType, DataSource paramDataSource)
  {
    this.BR = paramInt;
    this.Sp = paramDataType;
    this.Sq = paramDataSource;
  }
  
  private aj(a parama)
  {
    this.BR = 1;
    this.Sp = a.a(parama);
    this.Sq = a.b(parama);
  }
  
  private boolean a(aj paramaj)
  {
    return (n.equal(this.Sq, paramaj.Sq)) && (n.equal(this.Sp, paramaj.Sp));
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (this == paramObject) || (((paramObject instanceof aj)) && (a((aj)paramObject)));
  }
  
  public DataSource getDataSource()
  {
    return this.Sq;
  }
  
  public DataType getDataType()
  {
    return this.Sp;
  }
  
  int getVersionCode()
  {
    return this.BR;
  }
  
  public int hashCode()
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = this.Sq;
    arrayOfObject[1] = this.Sp;
    return n.hashCode(arrayOfObject);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ak.a(this, paramParcel, paramInt);
  }
  
  public static class a
  {
    private DataType Sp;
    private DataSource Sq;
    
    public a d(DataSource paramDataSource)
    {
      this.Sq = paramDataSource;
      return this;
    }
    
    public a d(DataType paramDataType)
    {
      this.Sp = paramDataType;
      return this;
    }
    
    public aj jG()
    {
      if ((this.Sp != null) && (this.Sq != null)) {
        throw new IllegalArgumentException("Cannot specify both dataType and dataSource");
      }
      return new aj(this, null);
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.fitness.request.aj
 * JD-Core Version:    0.7.0.1
 */