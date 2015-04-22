package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.drive.query.Filter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LogicalFilter
  extends AbstractFilter
{
  public static final Parcelable.Creator<LogicalFilter> CREATOR = new i();
  final int BR;
  private List<Filter> QF;
  final Operator QK;
  final List<FilterHolder> QX;
  
  LogicalFilter(int paramInt, Operator paramOperator, List<FilterHolder> paramList)
  {
    this.BR = paramInt;
    this.QK = paramOperator;
    this.QX = paramList;
  }
  
  public LogicalFilter(Operator paramOperator, Filter paramFilter, Filter... paramVarArgs)
  {
    this.BR = 1;
    this.QK = paramOperator;
    this.QX = new ArrayList(1 + paramVarArgs.length);
    this.QX.add(new FilterHolder(paramFilter));
    this.QF = new ArrayList(1 + paramVarArgs.length);
    this.QF.add(paramFilter);
    int i = paramVarArgs.length;
    for (int j = 0; j < i; j++)
    {
      Filter localFilter = paramVarArgs[j];
      this.QX.add(new FilterHolder(localFilter));
      this.QF.add(localFilter);
    }
  }
  
  public LogicalFilter(Operator paramOperator, Iterable<Filter> paramIterable)
  {
    this.BR = 1;
    this.QK = paramOperator;
    this.QF = new ArrayList();
    this.QX = new ArrayList();
    Iterator localIterator = paramIterable.iterator();
    while (localIterator.hasNext())
    {
      Filter localFilter = (Filter)localIterator.next();
      this.QF.add(localFilter);
      this.QX.add(new FilterHolder(localFilter));
    }
  }
  
  public <T> T a(f<T> paramf)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.QX.iterator();
    while (localIterator.hasNext()) {
      localArrayList.add(((FilterHolder)localIterator.next()).getFilter().a(paramf));
    }
    return paramf.b(this.QK, localArrayList);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    i.a(this, paramParcel, paramInt);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.query.internal.LogicalFilter
 * JD-Core Version:    0.7.0.1
 */