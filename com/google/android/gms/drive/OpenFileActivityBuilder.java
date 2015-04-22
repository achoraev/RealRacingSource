package com.google.android.gms.drive;

import android.content.IntentSender;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.drive.internal.OpenFileIntentSenderRequest;
import com.google.android.gms.drive.internal.ab;
import com.google.android.gms.drive.internal.q;

public class OpenFileActivityBuilder
{
  public static final String EXTRA_RESPONSE_DRIVE_ID = "response_drive_id";
  private String Nw;
  private String[] Nx;
  private DriveId Ny;
  
  public IntentSender build(GoogleApiClient paramGoogleApiClient)
  {
    o.a(paramGoogleApiClient.isConnected(), "Client must be connected");
    if (this.Nx == null) {
      this.Nx = new String[0];
    }
    ab localab = ((q)paramGoogleApiClient.a(Drive.CU)).hY();
    try
    {
      IntentSender localIntentSender = localab.a(new OpenFileIntentSenderRequest(this.Nw, this.Nx, this.Ny));
      return localIntentSender;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeException("Unable to connect Drive Play Service", localRemoteException);
    }
  }
  
  public OpenFileActivityBuilder setActivityStartFolder(DriveId paramDriveId)
  {
    this.Ny = ((DriveId)o.i(paramDriveId));
    return this;
  }
  
  public OpenFileActivityBuilder setActivityTitle(String paramString)
  {
    this.Nw = ((String)o.i(paramString));
    return this;
  }
  
  public OpenFileActivityBuilder setMimeType(String[] paramArrayOfString)
  {
    if (paramArrayOfString != null) {}
    for (boolean bool = true;; bool = false)
    {
      o.b(bool, "mimeTypes may not be null");
      this.Nx = paramArrayOfString;
      return this;
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.OpenFileActivityBuilder
 * JD-Core Version:    0.7.0.1
 */