package com.google.android.gms.drive.widget;

import android.content.Context;
import android.database.CursorIndexOutOfBoundsException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.google.android.gms.common.data.DataBuffer;
import com.google.android.gms.drive.internal.v;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class DataBufferAdapter<T>
  extends BaseAdapter
{
  private final int RR;
  private int RS;
  private final int RT;
  private final List<DataBuffer<T>> RU;
  private final LayoutInflater RV;
  private boolean RW = true;
  private final Context mContext;
  
  public DataBufferAdapter(Context paramContext, int paramInt)
  {
    this(paramContext, paramInt, 0, new ArrayList());
  }
  
  public DataBufferAdapter(Context paramContext, int paramInt1, int paramInt2)
  {
    this(paramContext, paramInt1, paramInt2, new ArrayList());
  }
  
  public DataBufferAdapter(Context paramContext, int paramInt1, int paramInt2, List<DataBuffer<T>> paramList)
  {
    this.mContext = paramContext;
    this.RS = paramInt1;
    this.RR = paramInt1;
    this.RT = paramInt2;
    this.RU = paramList;
    this.RV = ((LayoutInflater)paramContext.getSystemService("layout_inflater"));
  }
  
  public DataBufferAdapter(Context paramContext, int paramInt1, int paramInt2, DataBuffer<T>... paramVarArgs)
  {
    this(paramContext, paramInt1, paramInt2, Arrays.asList(paramVarArgs));
  }
  
  public DataBufferAdapter(Context paramContext, int paramInt, List<DataBuffer<T>> paramList)
  {
    this(paramContext, paramInt, 0, paramList);
  }
  
  public DataBufferAdapter(Context paramContext, int paramInt, DataBuffer<T>... paramVarArgs)
  {
    this(paramContext, paramInt, 0, Arrays.asList(paramVarArgs));
  }
  
  private View a(int paramInt1, View paramView, ViewGroup paramViewGroup, int paramInt2)
  {
    View localView;
    if (paramView == null) {
      localView = this.RV.inflate(paramInt2, paramViewGroup, false);
    }
    try
    {
      TextView localTextView2;
      if (this.RT == 0) {
        localTextView2 = (TextView)localView;
      }
      Object localObject2;
      TextView localTextView1;
      for (Object localObject1 = localTextView2;; localObject1 = localTextView1)
      {
        localObject2 = getItem(paramInt1);
        if (!(localObject2 instanceof CharSequence)) {
          break label113;
        }
        ((TextView)localObject1).setText((CharSequence)localObject2);
        return localView;
        localView = paramView;
        break;
        localTextView1 = (TextView)localView.findViewById(this.RT);
      }
      ((TextView)localObject1).setText(localObject2.toString());
    }
    catch (ClassCastException localClassCastException)
    {
      v.a("DataBufferAdapter", localClassCastException, "You must supply a resource ID for a TextView");
      throw new IllegalStateException("DataBufferAdapter requires the resource ID to be a TextView", localClassCastException);
    }
    label113:
    return localView;
  }
  
  public void append(DataBuffer<T> paramDataBuffer)
  {
    this.RU.add(paramDataBuffer);
    if (this.RW) {
      notifyDataSetChanged();
    }
  }
  
  public void clear()
  {
    Iterator localIterator = this.RU.iterator();
    while (localIterator.hasNext()) {
      ((DataBuffer)localIterator.next()).release();
    }
    this.RU.clear();
    if (this.RW) {
      notifyDataSetChanged();
    }
  }
  
  public Context getContext()
  {
    return this.mContext;
  }
  
  public int getCount()
  {
    Iterator localIterator = this.RU.iterator();
    int i = 0;
    while (localIterator.hasNext()) {
      i += ((DataBuffer)localIterator.next()).getCount();
    }
    return i;
  }
  
  public View getDropDownView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    return a(paramInt, paramView, paramViewGroup, this.RS);
  }
  
  public T getItem(int paramInt)
    throws CursorIndexOutOfBoundsException
  {
    Iterator localIterator = this.RU.iterator();
    int i = paramInt;
    while (localIterator.hasNext())
    {
      DataBuffer localDataBuffer = (DataBuffer)localIterator.next();
      int j = localDataBuffer.getCount();
      if (j <= i) {
        i -= j;
      } else {
        try
        {
          Object localObject = localDataBuffer.get(i);
          return localObject;
        }
        catch (CursorIndexOutOfBoundsException localCursorIndexOutOfBoundsException)
        {
          throw new CursorIndexOutOfBoundsException(paramInt, getCount());
        }
      }
    }
    throw new CursorIndexOutOfBoundsException(paramInt, getCount());
  }
  
  public long getItemId(int paramInt)
  {
    return paramInt;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    return a(paramInt, paramView, paramViewGroup, this.RR);
  }
  
  public void notifyDataSetChanged()
  {
    super.notifyDataSetChanged();
    this.RW = true;
  }
  
  public void setDropDownViewResource(int paramInt)
  {
    this.RS = paramInt;
  }
  
  public void setNotifyOnChange(boolean paramBoolean)
  {
    this.RW = paramBoolean;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.widget.DataBufferAdapter
 * JD-Core Version:    0.7.0.1
 */