package com.google.android.gms.drive.query.internal;

import com.google.android.gms.drive.query.Filter;
import com.google.android.gms.drive.query.c;

public abstract class AbstractFilter
  implements Filter
{
  public String toString()
  {
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = a(new c());
    return String.format("Filter[%s]", arrayOfObject);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.query.internal.AbstractFilter
 * JD-Core Version:    0.7.0.1
 */