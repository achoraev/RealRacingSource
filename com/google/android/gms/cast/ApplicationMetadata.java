package com.google.android.gms.cast;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.ik;
import java.util.ArrayList;
import java.util.List;

public final class ApplicationMetadata
  implements SafeParcelable
{
  public static final Parcelable.Creator<ApplicationMetadata> CREATOR = new a();
  private final int BR;
  List<WebImage> EA;
  List<String> EB;
  String EC;
  Uri ED;
  String Ez;
  String mName;
  
  private ApplicationMetadata()
  {
    this.BR = 1;
    this.EA = new ArrayList();
    this.EB = new ArrayList();
  }
  
  ApplicationMetadata(int paramInt, String paramString1, String paramString2, List<WebImage> paramList, List<String> paramList1, String paramString3, Uri paramUri)
  {
    this.BR = paramInt;
    this.Ez = paramString1;
    this.mName = paramString2;
    this.EA = paramList;
    this.EB = paramList1;
    this.EC = paramString3;
    this.ED = paramUri;
  }
  
  public boolean areNamespacesSupported(List<String> paramList)
  {
    return (this.EB != null) && (this.EB.containsAll(paramList));
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {}
    ApplicationMetadata localApplicationMetadata;
    do
    {
      return true;
      if (!(paramObject instanceof ApplicationMetadata)) {
        return false;
      }
      localApplicationMetadata = (ApplicationMetadata)paramObject;
    } while ((ik.a(this.Ez, localApplicationMetadata.Ez)) && (ik.a(this.EA, localApplicationMetadata.EA)) && (ik.a(this.mName, localApplicationMetadata.mName)) && (ik.a(this.EB, localApplicationMetadata.EB)) && (ik.a(this.EC, localApplicationMetadata.EC)) && (ik.a(this.ED, localApplicationMetadata.ED)));
    return false;
  }
  
  public Uri fu()
  {
    return this.ED;
  }
  
  public String getApplicationId()
  {
    return this.Ez;
  }
  
  public List<WebImage> getImages()
  {
    return this.EA;
  }
  
  public String getName()
  {
    return this.mName;
  }
  
  public String getSenderAppIdentifier()
  {
    return this.EC;
  }
  
  int getVersionCode()
  {
    return this.BR;
  }
  
  public int hashCode()
  {
    Object[] arrayOfObject = new Object[7];
    arrayOfObject[0] = Integer.valueOf(this.BR);
    arrayOfObject[1] = this.Ez;
    arrayOfObject[2] = this.mName;
    arrayOfObject[3] = this.EA;
    arrayOfObject[4] = this.EB;
    arrayOfObject[5] = this.EC;
    arrayOfObject[6] = this.ED;
    return n.hashCode(arrayOfObject);
  }
  
  public boolean isNamespaceSupported(String paramString)
  {
    return (this.EB != null) && (this.EB.contains(paramString));
  }
  
  public String toString()
  {
    return this.mName;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    a.a(this, paramParcel, paramInt);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.cast.ApplicationMetadata
 * JD-Core Version:    0.7.0.1
 */