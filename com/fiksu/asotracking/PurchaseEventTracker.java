package com.fiksu.asotracking;

import android.content.Context;

final class PurchaseEventTracker
  extends EventTracker
{
  PurchaseEventTracker(Context paramContext, FiksuTrackingManager.PurchaseEvent paramPurchaseEvent, String paramString1, Double paramDouble, String paramString2)
  {
    super(paramContext, FiksuEventType.PURCHASE.getName() + paramPurchaseEvent.getNameSuffix());
    addParameter(FiksuEventParameter.USERNAME, paramString1);
    addParameter(FiksuEventParameter.FVALUE, paramDouble.toString());
    addParameter(FiksuEventParameter.TVALUE, paramString2);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.fiksu.asotracking.PurchaseEventTracker
 * JD-Core Version:    0.7.0.1
 */