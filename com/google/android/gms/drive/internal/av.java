package com.google.android.gms.drive.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.BaseImplementation.b;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveApi.DriveContentsResult;
import com.google.android.gms.drive.DriveFile.DownloadProgressListener;

class av
  extends c
{
  private final BaseImplementation.b<DriveApi.DriveContentsResult> De;
  private final DriveFile.DownloadProgressListener OU;
  
  av(BaseImplementation.b<DriveApi.DriveContentsResult> paramb, DriveFile.DownloadProgressListener paramDownloadProgressListener)
  {
    this.De = paramb;
    this.OU = paramDownloadProgressListener;
  }
  
  public void a(OnContentsResponse paramOnContentsResponse)
    throws RemoteException
  {
    if (paramOnContentsResponse.ie()) {}
    for (Status localStatus = new Status(-1);; localStatus = Status.Jv)
    {
      this.De.b(new o.c(localStatus, new r(paramOnContentsResponse.id())));
      return;
    }
  }
  
  public void a(OnDownloadProgressResponse paramOnDownloadProgressResponse)
    throws RemoteException
  {
    if (this.OU != null) {
      this.OU.onProgress(paramOnDownloadProgressResponse.jdMethod_if(), paramOnDownloadProgressResponse.ig());
    }
  }
  
  public void o(Status paramStatus)
    throws RemoteException
  {
    this.De.b(new o.c(paramStatus, null));
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.internal.av
 * JD-Core Version:    0.7.0.1
 */