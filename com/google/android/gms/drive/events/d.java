package com.google.android.gms.drive.events;

import com.google.android.gms.drive.DriveId;

public class d
{
  public static boolean a(int paramInt, DriveId paramDriveId)
  {
    return (paramDriveId != null) || (bd(paramInt));
  }
  
  public static boolean bd(int paramInt)
  {
    return (0x2 & 1 << paramInt) != 0L;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.events.d
 * JD-Core Version:    0.7.0.1
 */