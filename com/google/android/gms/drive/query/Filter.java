package com.google.android.gms.drive.query;

import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.query.internal.f;

public abstract interface Filter
  extends SafeParcelable
{
  public abstract <T> T a(f<T> paramf);
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.query.Filter
 * JD-Core Version:    0.7.0.1
 */