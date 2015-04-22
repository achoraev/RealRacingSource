package com.crashlytics.android;

import java.io.File;
import java.io.FilenameFilter;

final class g
  implements FilenameFilter
{
  public final boolean accept(File paramFile, String paramString)
  {
    return paramString.endsWith(".cls_temp");
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.crashlytics.android.g
 * JD-Core Version:    0.7.0.1
 */