package com.google.android.gms.drive.query;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.query.internal.LogicalFilter;
import com.google.android.gms.drive.query.internal.MatchAllFilter;
import com.google.android.gms.drive.query.internal.Operator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Query
  implements SafeParcelable
{
  public static final Parcelable.Creator<Query> CREATOR = new a();
  final int BR;
  final LogicalFilter QB;
  final String QC;
  final SortOrder QD;
  final List<String> QE;
  
  Query(int paramInt, LogicalFilter paramLogicalFilter, String paramString, SortOrder paramSortOrder, List<String> paramList)
  {
    this.BR = paramInt;
    this.QB = paramLogicalFilter;
    this.QC = paramString;
    this.QD = paramSortOrder;
    this.QE = paramList;
  }
  
  Query(LogicalFilter paramLogicalFilter, String paramString, SortOrder paramSortOrder, List<String> paramList)
  {
    this(1, paramLogicalFilter, paramString, paramSortOrder, paramList);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public Filter getFilter()
  {
    return this.QB;
  }
  
  public String getPageToken()
  {
    return this.QC;
  }
  
  public SortOrder getSortOrder()
  {
    return this.QD;
  }
  
  public List<String> iq()
  {
    return this.QE;
  }
  
  public String toString()
  {
    Locale localLocale = Locale.US;
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = this.QB;
    arrayOfObject[1] = this.QD;
    arrayOfObject[2] = this.QC;
    return String.format(localLocale, "Query[%s,%s,PageToken=%s]", arrayOfObject);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    a.a(this, paramParcel, paramInt);
  }
  
  public static class Builder
  {
    private String QC;
    private SortOrder QD;
    private List<String> QE;
    private final List<Filter> QF = new ArrayList();
    
    public Builder() {}
    
    public Builder(Query paramQuery)
    {
      this.QF.add(paramQuery.getFilter());
      this.QC = paramQuery.getPageToken();
      this.QD = paramQuery.getSortOrder();
      this.QE = paramQuery.iq();
    }
    
    public Builder addFilter(Filter paramFilter)
    {
      if (!(paramFilter instanceof MatchAllFilter)) {
        this.QF.add(paramFilter);
      }
      return this;
    }
    
    public Query build()
    {
      return new Query(new LogicalFilter(Operator.Re, this.QF), this.QC, this.QD, this.QE);
    }
    
    public Builder setPageToken(String paramString)
    {
      this.QC = paramString;
      return this;
    }
    
    public Builder setSortOrder(SortOrder paramSortOrder)
    {
      this.QD = paramSortOrder;
      return this;
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.query.Query
 * JD-Core Version:    0.7.0.1
 */