package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.internal.kj;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SessionInsertRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<SessionInsertRequest> CREATOR = new s();
  private final int BR;
  private final List<DataSet> SD;
  private final Session St;
  private final List<DataPoint> UL;
  
  SessionInsertRequest(int paramInt, Session paramSession, List<DataSet> paramList, List<DataPoint> paramList1)
  {
    this.BR = paramInt;
    this.St = paramSession;
    this.SD = Collections.unmodifiableList(paramList);
    this.UL = Collections.unmodifiableList(paramList1);
  }
  
  private SessionInsertRequest(Builder paramBuilder)
  {
    this.BR = 1;
    this.St = Builder.a(paramBuilder);
    this.SD = Collections.unmodifiableList(Builder.b(paramBuilder));
    this.UL = Collections.unmodifiableList(Builder.c(paramBuilder));
  }
  
  private boolean a(SessionInsertRequest paramSessionInsertRequest)
  {
    return (n.equal(this.St, paramSessionInsertRequest.St)) && (n.equal(this.SD, paramSessionInsertRequest.SD)) && (n.equal(this.UL, paramSessionInsertRequest.UL));
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (paramObject == this) || (((paramObject instanceof SessionInsertRequest)) && (a((SessionInsertRequest)paramObject)));
  }
  
  public List<DataPoint> getAggregateDataPoints()
  {
    return this.UL;
  }
  
  public List<DataSet> getDataSets()
  {
    return this.SD;
  }
  
  public Session getSession()
  {
    return this.St;
  }
  
  int getVersionCode()
  {
    return this.BR;
  }
  
  public int hashCode()
  {
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = this.St;
    arrayOfObject[1] = this.SD;
    arrayOfObject[2] = this.UL;
    return n.hashCode(arrayOfObject);
  }
  
  public String toString()
  {
    return n.h(this).a("session", this.St).a("dataSets", this.SD).a("aggregateDataPoints", this.UL).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    s.a(this, paramParcel, paramInt);
  }
  
  public static class Builder
  {
    private List<DataSet> SD = new ArrayList();
    private Session St;
    private List<DataPoint> UL = new ArrayList();
    private List<DataSource> UM = new ArrayList();
    
    private void d(DataPoint paramDataPoint)
    {
      f(paramDataPoint);
      e(paramDataPoint);
    }
    
    private void e(DataPoint paramDataPoint)
    {
      long l1 = this.St.getStartTime(TimeUnit.NANOSECONDS);
      long l2 = this.St.getEndTime(TimeUnit.NANOSECONDS);
      long l3 = paramDataPoint.getStartTime(TimeUnit.NANOSECONDS);
      long l4 = paramDataPoint.getEndTime(TimeUnit.NANOSECONDS);
      TimeUnit localTimeUnit;
      if ((l3 != 0L) && (l4 != 0L))
      {
        localTimeUnit = TimeUnit.MILLISECONDS;
        if (l4 > l2) {
          l4 = kj.a(l4, TimeUnit.NANOSECONDS, localTimeUnit);
        }
        if ((l3 < l1) || (l4 > l2)) {
          break label209;
        }
      }
      label209:
      for (boolean bool = true;; bool = false)
      {
        Object[] arrayOfObject1 = new Object[3];
        arrayOfObject1[0] = paramDataPoint;
        arrayOfObject1[1] = Long.valueOf(l1);
        arrayOfObject1[2] = Long.valueOf(l2);
        o.a(bool, "Data point %s has start and end times outside session interval [%d, %d]", arrayOfObject1);
        if (l4 != paramDataPoint.getEndTime(TimeUnit.NANOSECONDS))
        {
          Object[] arrayOfObject2 = new Object[3];
          arrayOfObject2[0] = Long.valueOf(paramDataPoint.getEndTime(TimeUnit.NANOSECONDS));
          arrayOfObject2[1] = Long.valueOf(l4);
          arrayOfObject2[2] = localTimeUnit;
          Log.w("Fitness", String.format("Data point end time [%d] is truncated to [%d] to match the precision [%s] of the session start and end time", arrayOfObject2));
          paramDataPoint.setTimeInterval(l3, l4, TimeUnit.NANOSECONDS);
        }
        return;
      }
    }
    
    private void f(DataPoint paramDataPoint)
    {
      long l1 = this.St.getStartTime(TimeUnit.NANOSECONDS);
      long l2 = this.St.getEndTime(TimeUnit.NANOSECONDS);
      long l3 = paramDataPoint.getTimestamp(TimeUnit.NANOSECONDS);
      TimeUnit localTimeUnit;
      if (l3 != 0L)
      {
        localTimeUnit = TimeUnit.MILLISECONDS;
        if ((l3 < l1) || (l3 > l2)) {
          l3 = kj.a(l3, TimeUnit.NANOSECONDS, localTimeUnit);
        }
        if ((l3 < l1) || (l3 > l2)) {
          break label198;
        }
      }
      label198:
      for (boolean bool = true;; bool = false)
      {
        Object[] arrayOfObject1 = new Object[3];
        arrayOfObject1[0] = paramDataPoint;
        arrayOfObject1[1] = Long.valueOf(l1);
        arrayOfObject1[2] = Long.valueOf(l2);
        o.a(bool, "Data point %s has time stamp outside session interval [%d, %d]", arrayOfObject1);
        if (paramDataPoint.getTimestamp(TimeUnit.NANOSECONDS) != l3)
        {
          Object[] arrayOfObject2 = new Object[3];
          arrayOfObject2[0] = Long.valueOf(paramDataPoint.getTimestamp(TimeUnit.NANOSECONDS));
          arrayOfObject2[1] = Long.valueOf(l3);
          arrayOfObject2[2] = localTimeUnit;
          Log.w("Fitness", String.format("Data point timestamp [%d] is truncated to [%d] to match the precision [%s] of the session start and end time", arrayOfObject2));
          paramDataPoint.setTimestamp(l3, TimeUnit.NANOSECONDS);
        }
        return;
      }
    }
    
    private void jy()
    {
      Iterator localIterator1 = this.SD.iterator();
      while (localIterator1.hasNext())
      {
        Iterator localIterator3 = ((DataSet)localIterator1.next()).getDataPoints().iterator();
        while (localIterator3.hasNext()) {
          d((DataPoint)localIterator3.next());
        }
      }
      Iterator localIterator2 = this.UL.iterator();
      while (localIterator2.hasNext()) {
        d((DataPoint)localIterator2.next());
      }
    }
    
    public Builder addAggregateDataPoint(DataPoint paramDataPoint)
    {
      boolean bool1;
      boolean bool2;
      label45:
      DataSource localDataSource;
      if (paramDataPoint != null)
      {
        bool1 = true;
        o.b(bool1, "Must specify a valid aggregate data point.");
        long l1 = paramDataPoint.getStartTime(TimeUnit.NANOSECONDS);
        long l2 = paramDataPoint.getEndTime(TimeUnit.NANOSECONDS);
        if ((l1 <= 0L) || (l2 <= l1)) {
          break label129;
        }
        bool2 = true;
        o.b(bool2, "Aggregate data point should have valid start and end times: %s", new Object[] { paramDataPoint });
        localDataSource = paramDataPoint.getDataSource();
        if (this.UM.contains(localDataSource)) {
          break label135;
        }
      }
      label129:
      label135:
      for (boolean bool3 = true;; bool3 = false)
      {
        o.a(bool3, "Data set/Aggregate data point for this data source %s is already added.", new Object[] { localDataSource });
        this.UM.add(localDataSource);
        this.UL.add(paramDataPoint);
        return this;
        bool1 = false;
        break;
        bool2 = false;
        break label45;
      }
    }
    
    public Builder addDataSet(DataSet paramDataSet)
    {
      boolean bool1 = true;
      boolean bool2;
      DataSource localDataSource;
      boolean bool3;
      if (paramDataSet != null)
      {
        bool2 = bool1;
        o.b(bool2, "Must specify a valid data set.");
        localDataSource = paramDataSet.getDataSource();
        if (this.UM.contains(localDataSource)) {
          break label106;
        }
        bool3 = bool1;
        label37:
        Object[] arrayOfObject = new Object[bool1];
        arrayOfObject[0] = localDataSource;
        o.a(bool3, "Data set for this data source %s is already added.", arrayOfObject);
        if (paramDataSet.getDataPoints().isEmpty()) {
          break label112;
        }
      }
      for (;;)
      {
        o.b(bool1, "No data points specified in the input data set.");
        this.UM.add(localDataSource);
        this.SD.add(paramDataSet);
        return this;
        bool2 = false;
        break;
        label106:
        bool3 = false;
        break label37;
        label112:
        bool1 = false;
      }
    }
    
    public SessionInsertRequest build()
    {
      boolean bool1 = true;
      boolean bool2;
      if (this.St != null)
      {
        bool2 = bool1;
        o.a(bool2, "Must specify a valid session.");
        if (this.St.getEndTime(TimeUnit.MILLISECONDS) == 0L) {
          break label57;
        }
      }
      for (;;)
      {
        o.a(bool1, "Must specify a valid end time, cannot insert a continuing session.");
        jy();
        return new SessionInsertRequest(this, null);
        bool2 = false;
        break;
        label57:
        bool1 = false;
      }
    }
    
    public Builder setSession(Session paramSession)
    {
      this.St = paramSession;
      return this;
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.fitness.request.SessionInsertRequest
 * JD-Core Version:    0.7.0.1
 */