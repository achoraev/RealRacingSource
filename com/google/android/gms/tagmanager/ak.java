package com.google.android.gms.tagmanager;

import android.os.Build.VERSION;
import java.io.File;

class ak
{
  static boolean ag(String paramString)
  {
    if (version() < 9) {
      return false;
    }
    File localFile = new File(paramString);
    localFile.setReadable(false, false);
    localFile.setWritable(false, false);
    localFile.setReadable(true, true);
    localFile.setWritable(true, true);
    return true;
  }
  
  public static int version()
  {
    try
    {
      int i = Integer.parseInt(Build.VERSION.SDK);
      return i;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      bh.T("Invalid version number: " + Build.VERSION.SDK);
    }
    return 0;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.ak
 * JD-Core Version:    0.7.0.1
 */