package com.jirbo.adcolony;

import java.io.File;

class ADCDir
{
  public static boolean delete(File paramFile)
  {
    if (paramFile.exists())
    {
      File[] arrayOfFile = paramFile.listFiles();
      int i = 0;
      if (i < arrayOfFile.length)
      {
        if (arrayOfFile[i].isDirectory()) {
          delete(arrayOfFile[i]);
        }
        for (;;)
        {
          i++;
          break;
          arrayOfFile[i].delete();
        }
      }
    }
    return paramFile.delete();
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.jirbo.adcolony.ADCDir
 * JD-Core Version:    0.7.0.1
 */