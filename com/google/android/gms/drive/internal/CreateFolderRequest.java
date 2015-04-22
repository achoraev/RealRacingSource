package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public class CreateFolderRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<CreateFolderRequest> CREATOR = new k();
  final int BR;
  final MetadataBundle Ol;
  final DriveId On;
  
  CreateFolderRequest(int paramInt, DriveId paramDriveId, MetadataBundle paramMetadataBundle)
  {
    this.BR = paramInt;
    this.On = ((DriveId)o.i(paramDriveId));
    this.Ol = ((MetadataBundle)o.i(paramMetadataBundle));
  }
  
  public CreateFolderRequest(DriveId paramDriveId, MetadataBundle paramMetadataBundle)
  {
    this(1, paramDriveId, paramMetadataBundle);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    k.a(this, paramParcel, paramInt);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.internal.CreateFolderRequest
 * JD-Core Version:    0.7.0.1
 */