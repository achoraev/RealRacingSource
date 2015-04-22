package com.crashlytics.android;

import java.io.File;
import java.io.FilenameFilter;

final class ab
  implements FilenameFilter
{
  public final boolean accept(File paramFile, String paramString)
  {
    return (paramString.endsWith(".cls")) && (!paramString.contains("Session"));
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.crashlytics.android.ab
 * JD-Core Version:    0.7.0.1
 */