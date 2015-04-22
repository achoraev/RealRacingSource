package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Session;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DataDeleteRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<DataDeleteRequest> CREATOR = new d();
  private final int BR;
  private final long KS;
  private final List<DataType> SB;
  private final long Sr;
  private final List<DataSource> Uk;
  private final List<Session> Ul;
  private final boolean Um;
  private final boolean Un;
  
  DataDeleteRequest(int paramInt, long paramLong1, long paramLong2, List<DataSource> paramList, List<DataType> paramList1, List<Session> paramList2, boolean paramBoolean1, boolean paramBoolean2)
  {
    this.BR = paramInt;
    this.KS = paramLong1;
    this.Sr = paramLong2;
    this.Uk = Collections.unmodifiableList(paramList);
    this.SB = Collections.unmodifiableList(paramList1);
    this.Ul = paramList2;
    this.Um = paramBoolean1;
    this.Un = paramBoolean2;
  }
  
  private DataDeleteRequest(Builder paramBuilder)
  {
    this.BR = 1;
    this.KS = Builder.a(paramBuilder);
    this.Sr = Builder.b(paramBuilder);
    this.Uk = Collections.unmodifiableList(Builder.c(paramBuilder));
    this.SB = Collections.unmodifiableList(Builder.d(paramBuilder));
    this.Ul = Collections.unmodifiableList(Builder.e(paramBuilder));
    this.Um = Builder.f(paramBuilder);
    this.Un = Builder.g(paramBuilder);
  }
  
  private boolean a(DataDeleteRequest paramDataDeleteRequest)
  {
    return (this.KS == paramDataDeleteRequest.KS) && (this.Sr == paramDataDeleteRequest.Sr) && (n.equal(this.Uk, paramDataDeleteRequest.Uk)) && (n.equal(this.SB, paramDataDeleteRequest.SB)) && (n.equal(this.Ul, paramDataDeleteRequest.Ul)) && (this.Um == paramDataDeleteRequest.Um) && (this.Un == paramDataDeleteRequest.Un);
  }
  
  public boolean deleteAllData()
  {
    return this.Um;
  }
  
  public boolean deleteAllSessions()
  {
    return this.Un;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (paramObject == this) || (((paramObject instanceof DataDeleteRequest)) && (a((DataDeleteRequest)paramObject)));
  }
  
  public List<DataSource> getDataSources()
  {
    return this.Uk;
  }
  
  public List<DataType> getDataTypes()
  {
    return this.SB;
  }
  
  public long getEndTime(TimeUnit paramTimeUnit)
  {
    return paramTimeUnit.convert(this.Sr, TimeUnit.MILLISECONDS);
  }
  
  public List<Session> getSessions()
  {
    return this.Ul;
  }
  
  public long getStartTime(TimeUnit paramTimeUnit)
  {
    return paramTimeUnit.convert(this.KS, TimeUnit.MILLISECONDS);
  }
  
  int getVersionCode()
  {
    return this.BR;
  }
  
  public int hashCode()
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = Long.valueOf(this.KS);
    arrayOfObject[1] = Long.valueOf(this.Sr);
    return n.hashCode(arrayOfObject);
  }
  
  public long iD()
  {
    return this.KS;
  }
  
  public long iE()
  {
    return this.Sr;
  }
  
  public boolean jg()
  {
    return this.Um;
  }
  
  public boolean jh()
  {
    return this.Un;
  }
  
  public String toString()
  {
    return n.h(this).a("startTimeMillis", Long.valueOf(this.KS)).a("endTimeMillis", Long.valueOf(this.Sr)).a("dataSources", this.Uk).a("dateTypes", this.SB).a("sessions", this.Ul).a("deleteAllData", Boolean.valueOf(this.Um)).a("deleteAllSessions", Boolean.valueOf(this.Un)).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    d.a(this, paramParcel, paramInt);
  }
  
  public static class Builder
  {
    private long KS;
    private List<DataType> SB = new ArrayList();
    private long Sr;
    private List<DataSource> Uk = new ArrayList();
    private List<Session> Ul = new ArrayList();
    private boolean Um = false;
    private boolean Un = false;
    
    private void ji()
    {
      if (this.Ul.isEmpty()) {
        return;
      }
      Iterator localIterator = this.Ul.iterator();
      label23:
      Session localSession;
      if (localIterator.hasNext())
      {
        localSession = (Session)localIterator.next();
        if ((localSession.getStartTime(TimeUnit.MILLISECONDS) < this.KS) || (localSession.getEndTime(TimeUnit.MILLISECONDS) > this.Sr)) {
          break label118;
        }
      }
      label118:
      for (boolean bool = true;; bool = false)
      {
        Object[] arrayOfObject = new Object[3];
        arrayOfObject[0] = localSession;
        arrayOfObject[1] = Long.valueOf(this.KS);
        arrayOfObject[2] = Long.valueOf(this.Sr);
        o.a(bool, "Session %s is outside the time interval [%d, %d]", arrayOfObject);
        break label23;
        break;
      }
    }
    
    public Builder addDataSource(DataSource paramDataSource)
    {
      boolean bool1 = true;
      boolean bool2;
      if (!this.Um)
      {
        bool2 = bool1;
        o.b(bool2, "All data is already marked for deletion");
        if (paramDataSource == null) {
          break label58;
        }
      }
      for (;;)
      {
        o.b(bool1, "Must specify a valid data source");
        if (!this.Uk.contains(paramDataSource)) {
          this.Uk.add(paramDataSource);
        }
        return this;
        bool2 = false;
        break;
        label58:
        bool1 = false;
      }
    }
    
    public Builder addDataType(DataType paramDataType)
    {
      boolean bool1 = true;
      boolean bool2;
      if (!this.Um)
      {
        bool2 = bool1;
        o.b(bool2, "All data is already marked for deletion");
        if (paramDataType == null) {
          break label58;
        }
      }
      for (;;)
      {
        o.b(bool1, "Must specify a valid data type");
        if (!this.SB.contains(paramDataType)) {
          this.SB.add(paramDataType);
        }
        return this;
        bool2 = false;
        break;
        label58:
        bool1 = false;
      }
    }
    
    public Builder addSession(Session paramSession)
    {
      boolean bool1 = true;
      boolean bool2;
      boolean bool3;
      if (!this.Un)
      {
        bool2 = bool1;
        o.b(bool2, "All sessions already marked for deletion");
        if (paramSession == null) {
          break label67;
        }
        bool3 = bool1;
        label24:
        o.b(bool3, "Must specify a valid session");
        if (paramSession.getEndTime(TimeUnit.MILLISECONDS) <= 0L) {
          break label73;
        }
      }
      for (;;)
      {
        o.b(bool1, "Must specify a session that has already ended");
        this.Ul.add(paramSession);
        return this;
        bool2 = false;
        break;
        label67:
        bool3 = false;
        break label24;
        label73:
        bool1 = false;
      }
    }
    
    public DataDeleteRequest build()
    {
      boolean bool1;
      int i;
      if ((this.KS > 0L) && (this.Sr > this.KS))
      {
        bool1 = true;
        o.a(bool1, "Must specify a valid time interval");
        if ((!this.Um) && (this.Uk.isEmpty()) && (this.SB.isEmpty())) {
          break label123;
        }
        i = 1;
        label62:
        if ((!this.Un) && (this.Ul.isEmpty())) {
          break label128;
        }
      }
      label128:
      for (int j = 1;; j = 0)
      {
        boolean bool2;
        if (i == 0)
        {
          bool2 = false;
          if (j == 0) {}
        }
        else
        {
          bool2 = true;
        }
        o.a(bool2, "No data or session marked for deletion");
        ji();
        return new DataDeleteRequest(this, null);
        bool1 = false;
        break;
        label123:
        i = 0;
        break label62;
      }
    }
    
    public Builder deleteAllData()
    {
      if ((this.SB.isEmpty()) && (this.Uk.isEmpty())) {}
      for (boolean bool = true;; bool = false)
      {
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = this.Uk;
        arrayOfObject[1] = this.SB;
        o.b(bool, "Specific data source/type already specified for deletion. DataSources: %s DataTypes: %s", arrayOfObject);
        this.Um = true;
        return this;
      }
    }
    
    public Builder deleteAllSessions()
    {
      boolean bool = this.Ul.isEmpty();
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = this.Ul;
      o.b(bool, "Specific sessions already added for deletion: %s", arrayOfObject);
      this.Un = true;
      return this;
    }
    
    public Builder setTimeInterval(long paramLong1, long paramLong2, TimeUnit paramTimeUnit)
    {
      boolean bool1;
      if (paramLong1 > 0L)
      {
        bool1 = true;
        Object[] arrayOfObject1 = new Object[1];
        arrayOfObject1[0] = Long.valueOf(paramLong1);
        o.b(bool1, "Invalid start time :%d", arrayOfObject1);
        if (paramLong2 <= paramLong1) {
          break label92;
        }
      }
      label92:
      for (boolean bool2 = true;; bool2 = false)
      {
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = Long.valueOf(paramLong2);
        o.b(bool2, "Invalid end time :%d", arrayOfObject2);
        this.KS = paramTimeUnit.toMillis(paramLong1);
        this.Sr = paramTimeUnit.toMillis(paramLong2);
        return this;
        bool1 = false;
        break;
      }
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.fitness.request.DataDeleteRequest
 * JD-Core Version:    0.7.0.1
 */