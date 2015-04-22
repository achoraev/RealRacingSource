package com.crashlytics.android.internal;

import android.os.Looper;

final class o
  implements m
{
  public final void a(b paramb)
  {
    if (Looper.myLooper() != Looper.getMainLooper()) {
      throw new IllegalStateException("Event bus " + paramb + " accessed from non-main thread " + Looper.myLooper());
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.crashlytics.android.internal.o
 * JD-Core Version:    0.7.0.1
 */