package com.crashlytics.android;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final class s
  implements DialogInterface.OnClickListener
{
  s(p paramp) {}
  
  public final void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    Crashlytics.a(true);
    this.a.a.a(true);
    paramDialogInterface.dismiss();
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.crashlytics.android.s
 * JD-Core Version:    0.7.0.1
 */