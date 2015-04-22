package com.google.android.gms.fitness.request;

import android.os.SystemClock;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.location.LocationRequest;
import java.util.concurrent.TimeUnit;

public class SensorRequest
{
  public static final int ACCURACY_MODE_DEFAULT = 2;
  public static final int ACCURACY_MODE_HIGH = 3;
  public static final int ACCURACY_MODE_LOW = 1;
  private final DataType Sp;
  private final DataSource Sq;
  private final long Tt;
  private final int Tu;
  private final long UD;
  private final long UE;
  private final LocationRequest UI;
  private final long UJ;
  
  private SensorRequest(DataSource paramDataSource, LocationRequest paramLocationRequest)
  {
    this.UI = paramLocationRequest;
    this.Tt = TimeUnit.MILLISECONDS.toMicros(paramLocationRequest.getInterval());
    this.UE = TimeUnit.MILLISECONDS.toMicros(paramLocationRequest.getFastestInterval());
    this.UD = this.Tt;
    this.Sp = paramDataSource.getDataType();
    this.Tu = a(paramLocationRequest);
    this.Sq = paramDataSource;
    long l = paramLocationRequest.getExpirationTime();
    if (l == 9223372036854775807L)
    {
      this.UJ = 9223372036854775807L;
      return;
    }
    this.UJ = TimeUnit.MILLISECONDS.toMicros(l - SystemClock.elapsedRealtime());
  }
  
  private SensorRequest(Builder paramBuilder)
  {
    this.Sq = Builder.a(paramBuilder);
    this.Sp = Builder.b(paramBuilder);
    this.Tt = Builder.c(paramBuilder);
    this.UE = Builder.d(paramBuilder);
    this.UD = Builder.e(paramBuilder);
    this.Tu = Builder.f(paramBuilder);
    this.UI = null;
    this.UJ = Builder.g(paramBuilder);
  }
  
  private static int a(LocationRequest paramLocationRequest)
  {
    switch (paramLocationRequest.getPriority())
    {
    default: 
      return 2;
    case 100: 
      return 3;
    }
    return 1;
  }
  
  private boolean a(SensorRequest paramSensorRequest)
  {
    return (n.equal(this.Sq, paramSensorRequest.Sq)) && (n.equal(this.Sp, paramSensorRequest.Sp)) && (this.Tt == paramSensorRequest.Tt) && (this.UE == paramSensorRequest.UE) && (this.UD == paramSensorRequest.UD) && (this.Tu == paramSensorRequest.Tu) && (n.equal(this.UI, paramSensorRequest.UI)) && (this.UJ == paramSensorRequest.UJ);
  }
  
  public static int da(int paramInt)
  {
    switch (paramInt)
    {
    case 2: 
    default: 
      paramInt = 2;
    }
    return paramInt;
  }
  
  public static SensorRequest fromLocationRequest(DataSource paramDataSource, LocationRequest paramLocationRequest)
  {
    return new SensorRequest(paramDataSource, paramLocationRequest);
  }
  
  public boolean equals(Object paramObject)
  {
    return (this == paramObject) || (((paramObject instanceof SensorRequest)) && (a((SensorRequest)paramObject)));
  }
  
  public int getAccuracyMode()
  {
    return this.Tu;
  }
  
  public DataSource getDataSource()
  {
    return this.Sq;
  }
  
  public DataType getDataType()
  {
    return this.Sp;
  }
  
  public long getFastestRate(TimeUnit paramTimeUnit)
  {
    return paramTimeUnit.convert(this.UE, TimeUnit.MICROSECONDS);
  }
  
  public long getMaxDeliveryLatency(TimeUnit paramTimeUnit)
  {
    return paramTimeUnit.convert(this.UD, TimeUnit.MICROSECONDS);
  }
  
  public long getSamplingRate(TimeUnit paramTimeUnit)
  {
    return paramTimeUnit.convert(this.Tt, TimeUnit.MICROSECONDS);
  }
  
  public int hashCode()
  {
    Object[] arrayOfObject = new Object[8];
    arrayOfObject[0] = this.Sq;
    arrayOfObject[1] = this.Sp;
    arrayOfObject[2] = Long.valueOf(this.Tt);
    arrayOfObject[3] = Long.valueOf(this.UE);
    arrayOfObject[4] = Long.valueOf(this.UD);
    arrayOfObject[5] = Integer.valueOf(this.Tu);
    arrayOfObject[6] = this.UI;
    arrayOfObject[7] = Long.valueOf(this.UJ);
    return n.hashCode(arrayOfObject);
  }
  
  public long jx()
  {
    return this.UJ;
  }
  
  public String toString()
  {
    return n.h(this).a("dataSource", this.Sq).a("dataType", this.Sp).a("samplingRateMicros", Long.valueOf(this.Tt)).a("deliveryLatencyMicros", Long.valueOf(this.UD)).a("timeOutMicros", Long.valueOf(this.UJ)).toString();
  }
  
  public static class Builder
  {
    private DataType Sp;
    private DataSource Sq;
    private long Tt = -1L;
    private int Tu = 2;
    private long UD = 0L;
    private long UE = 0L;
    private long UJ = 9223372036854775807L;
    private boolean UK = false;
    
    public SensorRequest build()
    {
      if ((this.Sq != null) || (this.Sp != null)) {}
      for (boolean bool1 = true;; bool1 = false)
      {
        o.a(bool1, "Must call setDataSource() or setDataType()");
        boolean bool2;
        if ((this.Sp != null) && (this.Sq != null))
        {
          boolean bool3 = this.Sp.equals(this.Sq.getDataType());
          bool2 = false;
          if (!bool3) {}
        }
        else
        {
          bool2 = true;
        }
        o.a(bool2, "Specified data type is incompatible with specified data source");
        return new SensorRequest(this, null);
      }
    }
    
    public Builder setAccuracyMode(int paramInt)
    {
      this.Tu = SensorRequest.da(paramInt);
      return this;
    }
    
    public Builder setDataSource(DataSource paramDataSource)
    {
      this.Sq = paramDataSource;
      return this;
    }
    
    public Builder setDataType(DataType paramDataType)
    {
      this.Sp = paramDataType;
      return this;
    }
    
    public Builder setFastestRate(int paramInt, TimeUnit paramTimeUnit)
    {
      if (paramInt >= 0) {}
      for (boolean bool = true;; bool = false)
      {
        o.b(bool, "Cannot use a negative interval");
        this.UK = true;
        this.UE = paramTimeUnit.toMicros(paramInt);
        return this;
      }
    }
    
    public Builder setMaxDeliveryLatency(int paramInt, TimeUnit paramTimeUnit)
    {
      if (paramInt >= 0) {}
      for (boolean bool = true;; bool = false)
      {
        o.b(bool, "Cannot use a negative delivery interval");
        this.UD = paramTimeUnit.toMicros(paramInt);
        return this;
      }
    }
    
    public Builder setSamplingRate(long paramLong, TimeUnit paramTimeUnit)
    {
      if (paramLong >= 0L) {}
      for (boolean bool = true;; bool = false)
      {
        o.b(bool, "Cannot use a negative sampling interval");
        this.Tt = paramTimeUnit.toMicros(paramLong);
        if (!this.UK) {
          this.UE = (this.Tt / 2L);
        }
        return this;
      }
    }
    
    public Builder setTimeout(long paramLong, TimeUnit paramTimeUnit)
    {
      boolean bool1 = true;
      boolean bool2;
      if (paramLong > 0L)
      {
        bool2 = bool1;
        Object[] arrayOfObject = new Object[bool1];
        arrayOfObject[0] = Long.valueOf(paramLong);
        o.b(bool2, "Invalid time out value specified: %d", arrayOfObject);
        if (paramTimeUnit == null) {
          break label65;
        }
      }
      for (;;)
      {
        o.b(bool1, "Invalid time unit specified");
        this.UJ = paramTimeUnit.toMicros(paramLong);
        return this;
        bool2 = false;
        break;
        label65:
        bool1 = false;
      }
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.fitness.request.SensorRequest
 * JD-Core Version:    0.7.0.1
 */