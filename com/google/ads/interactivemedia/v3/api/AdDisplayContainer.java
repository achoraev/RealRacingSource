package com.google.ads.interactivemedia.v3.api;

import android.view.ViewGroup;
import com.google.ads.interactivemedia.v3.api.player.VideoAdPlayer;
import java.util.Collection;

public abstract interface AdDisplayContainer
{
  public abstract ViewGroup getAdContainer();
  
  public abstract Collection<CompanionAdSlot> getCompanionSlots();
  
  public abstract VideoAdPlayer getPlayer();
  
  public abstract void setAdContainer(ViewGroup paramViewGroup);
  
  public abstract void setCompanionSlots(Collection<CompanionAdSlot> paramCollection);
  
  public abstract void setPlayer(VideoAdPlayer paramVideoAdPlayer);
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.api.AdDisplayContainer
 * JD-Core Version:    0.7.0.1
 */