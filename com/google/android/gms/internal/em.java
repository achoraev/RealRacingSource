package com.google.android.gms.internal;

import com.google.android.gms.ads.purchase.InAppPurchaseListener;

@ez
public final class em
  extends eh.a
{
  private final InAppPurchaseListener oC;
  
  public em(InAppPurchaseListener paramInAppPurchaseListener)
  {
    this.oC = paramInAppPurchaseListener;
  }
  
  public void a(eg parameg)
  {
    this.oC.onInAppPurchaseRequested(new ep(parameg));
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.em
 * JD-Core Version:    0.7.0.1
 */