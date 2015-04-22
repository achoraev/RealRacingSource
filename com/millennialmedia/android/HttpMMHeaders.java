package com.millennialmedia.android;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Serializable;
import java.util.Map;
import org.apache.http.Header;

class HttpMMHeaders
  implements Parcelable, Serializable
{
  public static final Parcelable.Creator<HttpMMHeaders> CREATOR = new Parcelable.Creator()
  {
    public HttpMMHeaders createFromParcel(Parcel paramAnonymousParcel)
    {
      return new HttpMMHeaders(paramAnonymousParcel);
    }
    
    public HttpMMHeaders[] newArray(int paramAnonymousInt)
    {
      return new HttpMMHeaders[paramAnonymousInt];
    }
  };
  private static final String HEADER_MM_ACID = "X-MM-ACID";
  private static final String HEADER_MM_CUSTOM_CLOSE = "X-MM-USE-CUSTOM-CLOSE";
  private static final String HEADER_MM_ENABLE_HARDWARE_ACCEL = "X-MM-ENABLE-HARDWARE-ACCELERATION";
  private static final String HEADER_MM_TRANSITION = "X-MM-TRANSITION";
  private static final String HEADER_MM_TRANSITION_DURATION = "X-MM-TRANSITION-DURATION";
  private static final String HEADER_MM_TRANSPARENT = "X-MM-TRANSPARENT";
  private static final String TAG = HttpMMHeaders.class.getName();
  static final long serialVersionUID = 3168621112125974L;
  String acid;
  boolean enableHardwareAccel;
  boolean isTransparent;
  String transition;
  long transitionTimeInMillis;
  boolean useCustomClose;
  
  public HttpMMHeaders(Parcel paramParcel)
  {
    try
    {
      boolean[] arrayOfBoolean = new boolean[3];
      paramParcel.readBooleanArray(arrayOfBoolean);
      this.isTransparent = arrayOfBoolean[0];
      this.useCustomClose = arrayOfBoolean[1];
      this.enableHardwareAccel = arrayOfBoolean[2];
      this.transition = paramParcel.readString();
      this.acid = paramParcel.readString();
      this.transitionTimeInMillis = paramParcel.readLong();
      return;
    }
    catch (Exception localException)
    {
      MMLog.e(TAG, "Header serializing failed", localException);
    }
  }
  
  public HttpMMHeaders(Header[] paramArrayOfHeader)
  {
    int i = paramArrayOfHeader.length;
    for (int j = 0; j < i; j++)
    {
      Header localHeader = paramArrayOfHeader[j];
      checkTransparent(localHeader);
      checkTransition(localHeader);
      checkTransitionDuration(localHeader);
      checkUseCustomClose(localHeader);
      checkEnableHardwareAccel(localHeader);
      checkAcid(localHeader);
    }
  }
  
  private void checkAcid(Header paramHeader)
  {
    if ("X-MM-ACID".equalsIgnoreCase(paramHeader.getName())) {
      this.acid = paramHeader.getValue();
    }
  }
  
  private void checkEnableHardwareAccel(Header paramHeader)
  {
    if ("X-MM-ENABLE-HARDWARE-ACCELERATION".equalsIgnoreCase(paramHeader.getName())) {
      this.enableHardwareAccel = Boolean.parseBoolean(paramHeader.getValue());
    }
  }
  
  private void checkTransition(Header paramHeader)
  {
    if ("X-MM-TRANSITION".equalsIgnoreCase(paramHeader.getName())) {
      this.transition = paramHeader.getValue();
    }
  }
  
  private void checkTransitionDuration(Header paramHeader)
  {
    if ("X-MM-TRANSITION-DURATION".equalsIgnoreCase(paramHeader.getName()))
    {
      String str = paramHeader.getValue();
      if (str != null) {
        this.transitionTimeInMillis = ((1000.0F * Float.parseFloat(str)));
      }
    }
  }
  
  private void checkTransparent(Header paramHeader)
  {
    if ("X-MM-TRANSPARENT".equalsIgnoreCase(paramHeader.getName()))
    {
      String str = paramHeader.getValue();
      if (str != null) {
        this.isTransparent = Boolean.parseBoolean(str);
      }
    }
  }
  
  private void checkUseCustomClose(Header paramHeader)
  {
    if ("X-MM-USE-CUSTOM-CLOSE".equalsIgnoreCase(paramHeader.getName())) {
      this.useCustomClose = Boolean.parseBoolean(paramHeader.getValue());
    }
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  void updateArgumentsWithSettings(Map<String, String> paramMap)
  {
    paramMap.put("transparent", String.valueOf(this.isTransparent));
    paramMap.put("transition", String.valueOf(this.transition));
    paramMap.put("acid", String.valueOf(this.acid));
    paramMap.put("transitionDuration", String.valueOf(this.transitionTimeInMillis));
    paramMap.put("useCustomClose", String.valueOf(this.useCustomClose));
    paramMap.put("enableHardwareAccel", String.valueOf(this.enableHardwareAccel));
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    boolean[] arrayOfBoolean = new boolean[3];
    arrayOfBoolean[0] = this.isTransparent;
    arrayOfBoolean[1] = this.useCustomClose;
    arrayOfBoolean[2] = this.enableHardwareAccel;
    paramParcel.writeBooleanArray(arrayOfBoolean);
    paramParcel.writeString(this.transition);
    paramParcel.writeString(this.acid);
    paramParcel.writeLong(this.transitionTimeInMillis);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.HttpMMHeaders
 * JD-Core Version:    0.7.0.1
 */