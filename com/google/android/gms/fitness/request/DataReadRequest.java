package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.Bucket;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class DataReadRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<DataReadRequest> CREATOR = new g();
  public static final int NO_LIMIT;
  private final int BR;
  private final long KS;
  private final List<DataType> SB;
  private final int SE;
  private final long Sr;
  private final List<DataSource> Uk;
  private final List<DataType> Uo;
  private final List<DataSource> Up;
  private final long Uq;
  private final DataSource Ur;
  private final int Us;
  private final boolean Ut;
  private final boolean Uu;
  private final boolean Uv;
  
  DataReadRequest(int paramInt1, List<DataType> paramList1, List<DataSource> paramList2, long paramLong1, long paramLong2, List<DataType> paramList3, List<DataSource> paramList4, int paramInt2, long paramLong3, DataSource paramDataSource, int paramInt3, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    this.BR = paramInt1;
    this.SB = Collections.unmodifiableList(paramList1);
    this.Uk = Collections.unmodifiableList(paramList2);
    this.KS = paramLong1;
    this.Sr = paramLong2;
    this.Uo = Collections.unmodifiableList(paramList3);
    this.Up = Collections.unmodifiableList(paramList4);
    this.SE = paramInt2;
    this.Uq = paramLong3;
    this.Ur = paramDataSource;
    this.Us = paramInt3;
    this.Ut = paramBoolean1;
    this.Uu = paramBoolean2;
    this.Uv = paramBoolean3;
  }
  
  private DataReadRequest(Builder paramBuilder)
  {
    this.BR = 2;
    this.SB = Collections.unmodifiableList(Builder.a(paramBuilder));
    this.Uk = Collections.unmodifiableList(Builder.b(paramBuilder));
    this.KS = Builder.c(paramBuilder);
    this.Sr = Builder.d(paramBuilder);
    this.Uo = Collections.unmodifiableList(Builder.e(paramBuilder));
    this.Up = Collections.unmodifiableList(Builder.f(paramBuilder));
    this.SE = Builder.g(paramBuilder);
    this.Uq = Builder.h(paramBuilder);
    this.Ur = Builder.i(paramBuilder);
    this.Us = Builder.j(paramBuilder);
    this.Ut = Builder.k(paramBuilder);
    this.Uu = Builder.l(paramBuilder);
    this.Uv = Builder.m(paramBuilder);
  }
  
  private boolean a(DataReadRequest paramDataReadRequest)
  {
    return (this.SB.equals(paramDataReadRequest.SB)) && (this.Uk.equals(paramDataReadRequest.Uk)) && (this.KS == paramDataReadRequest.KS) && (this.Sr == paramDataReadRequest.Sr) && (this.SE == paramDataReadRequest.SE) && (this.Up.equals(paramDataReadRequest.Up)) && (this.Uo.equals(paramDataReadRequest.Uo)) && (n.equal(this.Ur, paramDataReadRequest.Ur)) && (this.Uq == paramDataReadRequest.Uq) && (this.Uv == paramDataReadRequest.Uv);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (this == paramObject) || (((paramObject instanceof DataReadRequest)) && (a((DataReadRequest)paramObject)));
  }
  
  public DataSource getActivityDataSource()
  {
    return this.Ur;
  }
  
  public List<DataSource> getAggregatedDataSources()
  {
    return this.Up;
  }
  
  public List<DataType> getAggregatedDataTypes()
  {
    return this.Uo;
  }
  
  public long getBucketDuration(TimeUnit paramTimeUnit)
  {
    return paramTimeUnit.convert(this.Uq, TimeUnit.MILLISECONDS);
  }
  
  public int getBucketType()
  {
    return this.SE;
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
  
  public int getLimit()
  {
    return this.Us;
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
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = Integer.valueOf(this.SE);
    arrayOfObject[1] = Long.valueOf(this.KS);
    arrayOfObject[2] = Long.valueOf(this.Sr);
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
  
  public boolean jk()
  {
    return this.Ut;
  }
  
  public boolean jl()
  {
    return this.Uv;
  }
  
  public boolean jm()
  {
    return this.Uu;
  }
  
  public long jn()
  {
    return this.Uq;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("DataReadRequest{");
    if (!this.SB.isEmpty())
    {
      Iterator localIterator4 = this.SB.iterator();
      while (localIterator4.hasNext()) {
        localStringBuilder.append(((DataType)localIterator4.next()).iQ()).append(" ");
      }
    }
    if (!this.Uk.isEmpty())
    {
      Iterator localIterator3 = this.Uk.iterator();
      while (localIterator3.hasNext()) {
        localStringBuilder.append(((DataSource)localIterator3.next()).toDebugString()).append(" ");
      }
    }
    if (this.SE != 0)
    {
      localStringBuilder.append("bucket by ").append(Bucket.cy(this.SE));
      if (this.Uq > 0L) {
        localStringBuilder.append(" >").append(this.Uq).append("ms");
      }
      localStringBuilder.append(": ");
    }
    if (!this.Uo.isEmpty())
    {
      Iterator localIterator2 = this.Uo.iterator();
      while (localIterator2.hasNext()) {
        localStringBuilder.append(((DataType)localIterator2.next()).iQ()).append(" ");
      }
    }
    if (!this.Up.isEmpty())
    {
      Iterator localIterator1 = this.Up.iterator();
      while (localIterator1.hasNext()) {
        localStringBuilder.append(((DataSource)localIterator1.next()).toDebugString()).append(" ");
      }
    }
    Object[] arrayOfObject = new Object[4];
    arrayOfObject[0] = Long.valueOf(this.KS);
    arrayOfObject[1] = Long.valueOf(this.KS);
    arrayOfObject[2] = Long.valueOf(this.Sr);
    arrayOfObject[3] = Long.valueOf(this.Sr);
    localStringBuilder.append(String.format("(%tF %tT - %tF %tT)", arrayOfObject));
    if (this.Ur != null) {
      localStringBuilder.append("activities: ").append(this.Ur.toDebugString());
    }
    if (this.Uv) {
      localStringBuilder.append(" +server");
    }
    localStringBuilder.append("}");
    return localStringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    g.a(this, paramParcel, paramInt);
  }
  
  public static class Builder
  {
    private long KS;
    private List<DataType> SB = new ArrayList();
    private int SE = 0;
    private long Sr;
    private List<DataSource> Uk = new ArrayList();
    private List<DataType> Uo = new ArrayList();
    private List<DataSource> Up = new ArrayList();
    private long Uq = 0L;
    private DataSource Ur;
    private int Us = 0;
    private boolean Ut = false;
    private boolean Uu = false;
    private boolean Uv = false;
    
    public Builder aggregate(DataSource paramDataSource, DataType paramDataType)
    {
      o.b(paramDataSource, "Attempting to add a null data source");
      if (!this.Uk.contains(paramDataSource)) {}
      for (boolean bool = true;; bool = false)
      {
        o.a(bool, "Cannot add the same data source for aggregated and detailed");
        DataType localDataType = paramDataSource.getDataType();
        o.b(DataType.AGGREGATE_INPUT_TYPES.contains(localDataType), "Unsupported input data type specified for aggregation: %s", new Object[] { localDataType });
        o.b(DataType.getAggregatesForInput(localDataType).contains(paramDataType), "Invalid output aggregate data type specified: %s -> %s", new Object[] { localDataType, paramDataType });
        if (!this.Up.contains(paramDataSource)) {
          this.Up.add(paramDataSource);
        }
        return this;
      }
    }
    
    public Builder aggregate(DataType paramDataType1, DataType paramDataType2)
    {
      o.b(paramDataType1, "Attempting to use a null data type");
      if (!this.SB.contains(paramDataType1)) {}
      for (boolean bool = true;; bool = false)
      {
        o.a(bool, "Cannot add the same data type as aggregated and detailed");
        o.b(DataType.AGGREGATE_INPUT_TYPES.contains(paramDataType1), "Unsupported input data type specified for aggregation: %s", new Object[] { paramDataType1 });
        o.b(DataType.getAggregatesForInput(paramDataType1).contains(paramDataType2), "Invalid output aggregate data type specified: %s -> %s", new Object[] { paramDataType1, paramDataType2 });
        if (!this.Uo.contains(paramDataType1)) {
          this.Uo.add(paramDataType1);
        }
        return this;
      }
    }
    
    public Builder bucketByActivitySegment(int paramInt, TimeUnit paramTimeUnit)
    {
      boolean bool1;
      if (this.SE == 0)
      {
        bool1 = true;
        Object[] arrayOfObject1 = new Object[1];
        arrayOfObject1[0] = Integer.valueOf(this.SE);
        o.b(bool1, "Bucketing strategy already set to %s", arrayOfObject1);
        if (paramInt <= 0) {
          break label86;
        }
      }
      label86:
      for (boolean bool2 = true;; bool2 = false)
      {
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = Integer.valueOf(paramInt);
        o.b(bool2, "Must specify a valid minimum duration for an activity segment: %d", arrayOfObject2);
        this.SE = 4;
        this.Uq = paramTimeUnit.toMillis(paramInt);
        return this;
        bool1 = false;
        break;
      }
    }
    
    public Builder bucketByActivitySegment(int paramInt, TimeUnit paramTimeUnit, DataSource paramDataSource)
    {
      boolean bool1;
      boolean bool2;
      if (this.SE == 0)
      {
        bool1 = true;
        Object[] arrayOfObject1 = new Object[1];
        arrayOfObject1[0] = Integer.valueOf(this.SE);
        o.b(bool1, "Bucketing strategy already set to %s", arrayOfObject1);
        if (paramInt <= 0) {
          break label131;
        }
        bool2 = true;
        label43:
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = Integer.valueOf(paramInt);
        o.b(bool2, "Must specify a valid minimum duration for an activity segment: %d", arrayOfObject2);
        if (paramDataSource == null) {
          break label137;
        }
      }
      label131:
      label137:
      for (boolean bool3 = true;; bool3 = false)
      {
        o.b(bool3, "Invalid activity data source specified");
        o.b(paramDataSource.getDataType().equals(DataType.TYPE_ACTIVITY_SEGMENT), "Invalid activity data source specified: %s", new Object[] { paramDataSource });
        this.Ur = paramDataSource;
        this.SE = 4;
        this.Uq = paramTimeUnit.toMillis(paramInt);
        return this;
        bool1 = false;
        break;
        bool2 = false;
        break label43;
      }
    }
    
    public Builder bucketByActivityType(int paramInt, TimeUnit paramTimeUnit)
    {
      boolean bool1;
      if (this.SE == 0)
      {
        bool1 = true;
        Object[] arrayOfObject1 = new Object[1];
        arrayOfObject1[0] = Integer.valueOf(this.SE);
        o.b(bool1, "Bucketing strategy already set to %s", arrayOfObject1);
        if (paramInt <= 0) {
          break label86;
        }
      }
      label86:
      for (boolean bool2 = true;; bool2 = false)
      {
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = Integer.valueOf(paramInt);
        o.b(bool2, "Must specify a valid minimum duration for an activity segment: %d", arrayOfObject2);
        this.SE = 3;
        this.Uq = paramTimeUnit.toMillis(paramInt);
        return this;
        bool1 = false;
        break;
      }
    }
    
    public Builder bucketByActivityType(int paramInt, TimeUnit paramTimeUnit, DataSource paramDataSource)
    {
      boolean bool1;
      boolean bool2;
      if (this.SE == 0)
      {
        bool1 = true;
        Object[] arrayOfObject1 = new Object[1];
        arrayOfObject1[0] = Integer.valueOf(this.SE);
        o.b(bool1, "Bucketing strategy already set to %s", arrayOfObject1);
        if (paramInt <= 0) {
          break label131;
        }
        bool2 = true;
        label43:
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = Integer.valueOf(paramInt);
        o.b(bool2, "Must specify a valid minimum duration for an activity segment: %d", arrayOfObject2);
        if (paramDataSource == null) {
          break label137;
        }
      }
      label131:
      label137:
      for (boolean bool3 = true;; bool3 = false)
      {
        o.b(bool3, "Invalid activity data source specified");
        o.b(paramDataSource.getDataType().equals(DataType.TYPE_ACTIVITY_SEGMENT), "Invalid activity data source specified: %s", new Object[] { paramDataSource });
        this.Ur = paramDataSource;
        this.SE = 3;
        this.Uq = paramTimeUnit.toMillis(paramInt);
        return this;
        bool1 = false;
        break;
        bool2 = false;
        break label43;
      }
    }
    
    public Builder bucketBySession(int paramInt, TimeUnit paramTimeUnit)
    {
      boolean bool1;
      if (this.SE == 0)
      {
        bool1 = true;
        Object[] arrayOfObject1 = new Object[1];
        arrayOfObject1[0] = Integer.valueOf(this.SE);
        o.b(bool1, "Bucketing strategy already set to %s", arrayOfObject1);
        if (paramInt <= 0) {
          break label86;
        }
      }
      label86:
      for (boolean bool2 = true;; bool2 = false)
      {
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = Integer.valueOf(paramInt);
        o.b(bool2, "Must specify a valid minimum duration for an activity segment: %d", arrayOfObject2);
        this.SE = 2;
        this.Uq = paramTimeUnit.toMillis(paramInt);
        return this;
        bool1 = false;
        break;
      }
    }
    
    public Builder bucketByTime(int paramInt, TimeUnit paramTimeUnit)
    {
      boolean bool1;
      if (this.SE == 0)
      {
        bool1 = true;
        Object[] arrayOfObject1 = new Object[1];
        arrayOfObject1[0] = Integer.valueOf(this.SE);
        o.b(bool1, "Bucketing strategy already set to %s", arrayOfObject1);
        if (paramInt <= 0) {
          break label86;
        }
      }
      label86:
      for (boolean bool2 = true;; bool2 = false)
      {
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = Integer.valueOf(paramInt);
        o.b(bool2, "Must specify a valid minimum duration for an activity segment: %d", arrayOfObject2);
        this.SE = 1;
        this.Uq = paramTimeUnit.toMillis(paramInt);
        return this;
        bool1 = false;
        break;
      }
    }
    
    public DataReadRequest build()
    {
      boolean bool1 = true;
      boolean bool2;
      boolean bool3;
      label69:
      boolean bool4;
      label118:
      boolean bool5;
      if ((!this.Uk.isEmpty()) || (!this.SB.isEmpty()) || (!this.Up.isEmpty()) || (!this.Uo.isEmpty()))
      {
        bool2 = bool1;
        o.a(bool2, "Must add at least one data source (aggregated or detailed)");
        if (this.KS <= 0L) {
          break label216;
        }
        bool3 = bool1;
        Object[] arrayOfObject1 = new Object[bool1];
        arrayOfObject1[0] = Long.valueOf(this.KS);
        o.a(bool3, "Invalid start time: %s", arrayOfObject1);
        if ((this.Sr <= 0L) || (this.Sr <= this.KS)) {
          break label221;
        }
        bool4 = bool1;
        Object[] arrayOfObject2 = new Object[bool1];
        arrayOfObject2[0] = Long.valueOf(this.Sr);
        o.a(bool4, "Invalid end time: %s", arrayOfObject2);
        if ((!this.Up.isEmpty()) || (!this.Uo.isEmpty())) {
          break label227;
        }
        bool5 = bool1;
        label171:
        if (((!bool5) || (this.SE != 0)) && ((bool5) || (this.SE == 0))) {
          break label233;
        }
      }
      for (;;)
      {
        o.a(bool1, "Must specify a valid bucketing strategy while requesting aggregation");
        return new DataReadRequest(this, null);
        bool2 = false;
        break;
        label216:
        bool3 = false;
        break label69;
        label221:
        bool4 = false;
        break label118;
        label227:
        bool5 = false;
        break label171;
        label233:
        bool1 = false;
      }
    }
    
    public Builder enableServerQueries()
    {
      this.Uv = true;
      return this;
    }
    
    public Builder read(DataSource paramDataSource)
    {
      o.b(paramDataSource, "Attempting to add a null data source");
      if (!this.Up.contains(paramDataSource)) {}
      for (boolean bool = true;; bool = false)
      {
        o.b(bool, "Cannot add the same data source as aggregated and detailed");
        if (!this.Uk.contains(paramDataSource)) {
          this.Uk.add(paramDataSource);
        }
        return this;
      }
    }
    
    public Builder read(DataType paramDataType)
    {
      o.b(paramDataType, "Attempting to use a null data type");
      if (!this.Uo.contains(paramDataType)) {}
      for (boolean bool = true;; bool = false)
      {
        o.a(bool, "Cannot add the same data type as aggregated and detailed");
        if (!this.SB.contains(paramDataType)) {
          this.SB.add(paramDataType);
        }
        return this;
      }
    }
    
    public Builder setLimit(int paramInt)
    {
      if (paramInt > 0) {}
      for (boolean bool = true;; bool = false)
      {
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Integer.valueOf(paramInt);
        o.b(bool, "Invalid limit %d is specified", arrayOfObject);
        this.Us = paramInt;
        return this;
      }
    }
    
    public Builder setTimeRange(long paramLong1, long paramLong2, TimeUnit paramTimeUnit)
    {
      this.KS = paramTimeUnit.toMillis(paramLong1);
      this.Sr = paramTimeUnit.toMillis(paramLong2);
      return this;
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.fitness.request.DataReadRequest
 * JD-Core Version:    0.7.0.1
 */