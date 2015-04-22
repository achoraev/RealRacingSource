package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.query.Filter;

public class FilterHolder
  implements SafeParcelable
{
  public static final Parcelable.Creator<FilterHolder> CREATOR = new d();
  final int BR;
  final ComparisonFilter<?> QO;
  final FieldOnlyFilter QP;
  final LogicalFilter QQ;
  final NotFilter QR;
  final InFilter<?> QS;
  final MatchAllFilter QT;
  final HasFilter QU;
  private final Filter QV;
  
  FilterHolder(int paramInt, ComparisonFilter<?> paramComparisonFilter, FieldOnlyFilter paramFieldOnlyFilter, LogicalFilter paramLogicalFilter, NotFilter paramNotFilter, InFilter<?> paramInFilter, MatchAllFilter paramMatchAllFilter, HasFilter<?> paramHasFilter)
  {
    this.BR = paramInt;
    this.QO = paramComparisonFilter;
    this.QP = paramFieldOnlyFilter;
    this.QQ = paramLogicalFilter;
    this.QR = paramNotFilter;
    this.QS = paramInFilter;
    this.QT = paramMatchAllFilter;
    this.QU = paramHasFilter;
    if (this.QO != null)
    {
      this.QV = this.QO;
      return;
    }
    if (this.QP != null)
    {
      this.QV = this.QP;
      return;
    }
    if (this.QQ != null)
    {
      this.QV = this.QQ;
      return;
    }
    if (this.QR != null)
    {
      this.QV = this.QR;
      return;
    }
    if (this.QS != null)
    {
      this.QV = this.QS;
      return;
    }
    if (this.QT != null)
    {
      this.QV = this.QT;
      return;
    }
    if (this.QU != null)
    {
      this.QV = this.QU;
      return;
    }
    throw new IllegalArgumentException("At least one filter must be set.");
  }
  
  public FilterHolder(Filter paramFilter)
  {
    this.BR = 2;
    ComparisonFilter localComparisonFilter;
    FieldOnlyFilter localFieldOnlyFilter;
    label38:
    LogicalFilter localLogicalFilter;
    label56:
    NotFilter localNotFilter;
    label75:
    InFilter localInFilter;
    label94:
    MatchAllFilter localMatchAllFilter;
    if ((paramFilter instanceof ComparisonFilter))
    {
      localComparisonFilter = (ComparisonFilter)paramFilter;
      this.QO = localComparisonFilter;
      if (!(paramFilter instanceof FieldOnlyFilter)) {
        break label202;
      }
      localFieldOnlyFilter = (FieldOnlyFilter)paramFilter;
      this.QP = localFieldOnlyFilter;
      if (!(paramFilter instanceof LogicalFilter)) {
        break label207;
      }
      localLogicalFilter = (LogicalFilter)paramFilter;
      this.QQ = localLogicalFilter;
      if (!(paramFilter instanceof NotFilter)) {
        break label213;
      }
      localNotFilter = (NotFilter)paramFilter;
      this.QR = localNotFilter;
      if (!(paramFilter instanceof InFilter)) {
        break label219;
      }
      localInFilter = (InFilter)paramFilter;
      this.QS = localInFilter;
      if (!(paramFilter instanceof MatchAllFilter)) {
        break label225;
      }
      localMatchAllFilter = (MatchAllFilter)paramFilter;
      label113:
      this.QT = localMatchAllFilter;
      if (!(paramFilter instanceof HasFilter)) {
        break label231;
      }
    }
    label202:
    label207:
    label213:
    label219:
    label225:
    label231:
    for (HasFilter localHasFilter = (HasFilter)paramFilter;; localHasFilter = null)
    {
      this.QU = localHasFilter;
      if ((this.QO != null) || (this.QP != null) || (this.QQ != null) || (this.QR != null) || (this.QS != null) || (this.QT != null) || (this.QU != null)) {
        break label237;
      }
      throw new IllegalArgumentException("Invalid filter type or null filter.");
      localComparisonFilter = null;
      break;
      localFieldOnlyFilter = null;
      break label38;
      localLogicalFilter = null;
      break label56;
      localNotFilter = null;
      break label75;
      localInFilter = null;
      break label94;
      localMatchAllFilter = null;
      break label113;
    }
    label237:
    this.QV = paramFilter;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public Filter getFilter()
  {
    return this.QV;
  }
  
  public String toString()
  {
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = this.QV;
    return String.format("FilterHolder[%s]", arrayOfObject);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    d.a(this, paramParcel, paramInt);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.query.internal.FilterHolder
 * JD-Core Version:    0.7.0.1
 */