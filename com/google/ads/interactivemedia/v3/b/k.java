package com.google.ads.interactivemedia.v3.b;

import android.view.ViewGroup;
import com.google.ads.interactivemedia.v3.api.CompanionAdSlot;

public class k
  implements CompanionAdSlot
{
  private int a;
  private int b;
  private ViewGroup c;
  
  public ViewGroup getContainer()
  {
    return this.c;
  }
  
  public int getHeight()
  {
    return this.b;
  }
  
  public int getWidth()
  {
    return this.a;
  }
  
  public void setContainer(ViewGroup paramViewGroup)
  {
    this.c = paramViewGroup;
  }
  
  public void setSize(int paramInt1, int paramInt2)
  {
    this.a = paramInt1;
    this.b = paramInt2;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.b.k
 * JD-Core Version:    0.7.0.1
 */