package com.bda.controller;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.SparseArray;

public final class MotionEvent
  extends BaseEvent
  implements Parcelable
{
  public static final int AXIS_LTRIGGER = 17;
  public static final int AXIS_RTRIGGER = 18;
  public static final int AXIS_RZ = 14;
  public static final int AXIS_X = 0;
  public static final int AXIS_Y = 1;
  public static final int AXIS_Z = 11;
  public static final Parcelable.Creator<MotionEvent> CREATOR = new ParcelableCreator();
  final SparseArray<Float> mAxis;
  final SparseArray<Float> mPrecision;
  
  public MotionEvent(long paramLong, int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6)
  {
    super(paramLong, paramInt);
    this.mAxis = new SparseArray(4);
    this.mAxis.put(0, Float.valueOf(paramFloat1));
    this.mAxis.put(1, Float.valueOf(paramFloat2));
    this.mAxis.put(11, Float.valueOf(paramFloat3));
    this.mAxis.put(14, Float.valueOf(paramFloat4));
    this.mPrecision = new SparseArray(2);
    this.mPrecision.put(0, Float.valueOf(paramFloat5));
    this.mPrecision.put(1, Float.valueOf(paramFloat6));
  }
  
  public MotionEvent(long paramLong, int paramInt, int[] paramArrayOfInt1, float[] paramArrayOfFloat1, int[] paramArrayOfInt2, float[] paramArrayOfFloat2)
  {
    super(paramLong, paramInt);
    int i = paramArrayOfInt1.length;
    this.mAxis = new SparseArray(i);
    int j = 0;
    int k;
    if (j >= i)
    {
      k = paramArrayOfInt2.length;
      this.mPrecision = new SparseArray(k);
    }
    for (int m = 0;; m++)
    {
      if (m >= k)
      {
        return;
        this.mAxis.put(paramArrayOfInt1[j], Float.valueOf(paramArrayOfFloat1[j]));
        j++;
        break;
      }
      this.mPrecision.put(paramArrayOfInt2[m], Float.valueOf(paramArrayOfFloat2[m]));
    }
  }
  
  MotionEvent(Parcel paramParcel)
  {
    super(paramParcel);
    int i = paramParcel.readInt();
    this.mAxis = new SparseArray(i);
    int j = 0;
    if (j >= i) {
      this.mPrecision = new SparseArray(paramParcel.readInt());
    }
    for (int m = 0;; m++)
    {
      if (m >= i)
      {
        return;
        int k = paramParcel.readInt();
        float f1 = paramParcel.readFloat();
        this.mAxis.put(k, Float.valueOf(f1));
        j++;
        break;
      }
      int n = paramParcel.readInt();
      float f2 = paramParcel.readFloat();
      this.mPrecision.put(n, Float.valueOf(f2));
    }
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public final int findPointerIndex(int paramInt)
  {
    return -1;
  }
  
  public final float getAxisValue(int paramInt)
  {
    return getAxisValue(paramInt, 0);
  }
  
  public final float getAxisValue(int paramInt1, int paramInt2)
  {
    float f = 0.0F;
    if (paramInt2 == 0) {
      f = ((Float)this.mAxis.get(paramInt1, Float.valueOf(0.0F))).floatValue();
    }
    return f;
  }
  
  public final int getPointerCount()
  {
    return 1;
  }
  
  public final int getPointerId(int paramInt)
  {
    return 0;
  }
  
  public final float getRawX()
  {
    return getX();
  }
  
  public final float getRawY()
  {
    return getY();
  }
  
  public final float getX()
  {
    return getAxisValue(0, 0);
  }
  
  public final float getX(int paramInt)
  {
    return getAxisValue(0, paramInt);
  }
  
  public final float getXPrecision()
  {
    return ((Float)this.mPrecision.get(0, Float.valueOf(0.0F))).floatValue();
  }
  
  public final float getY()
  {
    return getAxisValue(1, 0);
  }
  
  public final float getY(int paramInt)
  {
    return getAxisValue(1, paramInt);
  }
  
  public final float getYPrecision()
  {
    return ((Float)this.mPrecision.get(1, Float.valueOf(0.0F))).floatValue();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
    int i = this.mAxis.size();
    paramParcel.writeInt(i);
    int j = 0;
    int k;
    if (j >= i)
    {
      k = this.mPrecision.size();
      paramParcel.writeInt(k);
    }
    for (int m = 0;; m++)
    {
      if (m >= k)
      {
        return;
        paramParcel.writeInt(this.mAxis.keyAt(j));
        paramParcel.writeFloat(((Float)this.mAxis.valueAt(j)).floatValue());
        j++;
        break;
      }
      paramParcel.writeInt(this.mPrecision.keyAt(m));
      paramParcel.writeFloat(((Float)this.mPrecision.valueAt(m)).floatValue());
    }
  }
  
  static class ParcelableCreator
    implements Parcelable.Creator<MotionEvent>
  {
    public MotionEvent createFromParcel(Parcel paramParcel)
    {
      return new MotionEvent(paramParcel);
    }
    
    public MotionEvent[] newArray(int paramInt)
    {
      return new MotionEvent[paramInt];
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.bda.controller.MotionEvent
 * JD-Core Version:    0.7.0.1
 */