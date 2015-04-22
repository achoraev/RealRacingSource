package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import java.util.List;

public class e
  implements SafeParcelable
{
  public static final Parcelable.Creator<e> CREATOR = new f();
  private final int BR;
  private final DataSet Ts;
  
  e(int paramInt, DataSet paramDataSet)
  {
    this.BR = paramInt;
    this.Ts = paramDataSet;
  }
  
  private e(a parama)
  {
    this.BR = 1;
    this.Ts = a.a(parama);
  }
  
  private boolean a(e parame)
  {
    return n.equal(this.Ts, parame.Ts);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (paramObject == this) || (((paramObject instanceof e)) && (a((e)paramObject)));
  }
  
  int getVersionCode()
  {
    return this.BR;
  }
  
  public int hashCode()
  {
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = this.Ts;
    return n.hashCode(arrayOfObject);
  }
  
  public DataSet iW()
  {
    return this.Ts;
  }
  
  public String toString()
  {
    return n.h(this).a("dataSet", this.Ts).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    f.a(this, paramParcel, paramInt);
  }
  
  public static class a
  {
    private DataSet Ts;
    
    public a b(DataSet paramDataSet)
    {
      this.Ts = paramDataSet;
      return this;
    }
    
    public e jj()
    {
      boolean bool1 = true;
      boolean bool2;
      boolean bool3;
      if (this.Ts != null)
      {
        bool2 = bool1;
        o.a(bool2, "Must set the data set");
        if (this.Ts.getDataPoints().isEmpty()) {
          break label74;
        }
        bool3 = bool1;
        label34:
        o.a(bool3, "Cannot use an empty data set");
        if (this.Ts.getDataSource().iM() == null) {
          break label79;
        }
      }
      for (;;)
      {
        o.a(bool1, "Must set the app package name for the data source");
        return new e(this, null);
        bool2 = false;
        break;
        label74:
        bool3 = false;
        break label34;
        label79:
        bool1 = false;
      }
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.fitness.request.e
 * JD-Core Version:    0.7.0.1
 */