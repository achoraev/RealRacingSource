package com.google.android.gms.games.internal;

import com.google.android.gms.common.internal.d;
import com.google.android.gms.internal.jx;

public abstract class GamesDowngradeableSafeParcel
  extends d
{
  protected static boolean c(Integer paramInteger)
  {
    if (paramInteger == null) {
      return false;
    }
    return jx.aQ(paramInteger.intValue());
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.internal.GamesDowngradeableSafeParcel
 * JD-Core Version:    0.7.0.1
 */