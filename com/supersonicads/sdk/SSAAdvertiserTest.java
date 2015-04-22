package com.supersonicads.sdk;

import android.content.Context;

public abstract interface SSAAdvertiserTest
  extends SSAAdvertiser
{
  public abstract void clearReportApp(Context paramContext);
  
  public abstract void setDomain(String paramString1, String paramString2, int paramInt);
  
  public abstract void setPackageName(String paramString);
  
  public abstract void setTimeAPI(String paramString);
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.supersonicads.sdk.SSAAdvertiserTest
 * JD-Core Version:    0.7.0.1
 */