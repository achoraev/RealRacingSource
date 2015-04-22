package com.google.ads.interactivemedia.v3.api;

import android.view.ViewGroup;

public abstract interface CompanionAdSlot
{
  public abstract ViewGroup getContainer();
  
  public abstract int getHeight();
  
  public abstract int getWidth();
  
  public abstract void setContainer(ViewGroup paramViewGroup);
  
  public abstract void setSize(int paramInt1, int paramInt2);
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.api.CompanionAdSlot
 * JD-Core Version:    0.7.0.1
 */