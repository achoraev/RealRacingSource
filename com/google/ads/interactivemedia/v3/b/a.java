package com.google.ads.interactivemedia.v3.b;

import android.view.ViewGroup;
import com.google.ads.interactivemedia.v3.api.AdDisplayContainer;
import com.google.ads.interactivemedia.v3.api.CompanionAdSlot;
import com.google.ads.interactivemedia.v3.api.player.VideoAdPlayer;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class a
  implements AdDisplayContainer
{
  private static int e = 0;
  private VideoAdPlayer a;
  private ViewGroup b;
  private Collection<CompanionAdSlot> c = Collections.emptyList();
  private Map<String, CompanionAdSlot> d = null;
  
  public Map<String, CompanionAdSlot> a()
  {
    if (this.d == null)
    {
      this.d = new HashMap();
      Iterator localIterator = this.c.iterator();
      while (localIterator.hasNext())
      {
        CompanionAdSlot localCompanionAdSlot = (CompanionAdSlot)localIterator.next();
        Map localMap = this.d;
        StringBuilder localStringBuilder = new StringBuilder().append("compSlot_");
        int i = e;
        e = i + 1;
        localMap.put(i, localCompanionAdSlot);
      }
    }
    return this.d;
  }
  
  public ViewGroup getAdContainer()
  {
    return this.b;
  }
  
  public Collection<CompanionAdSlot> getCompanionSlots()
  {
    return this.c;
  }
  
  public VideoAdPlayer getPlayer()
  {
    return this.a;
  }
  
  public void setAdContainer(ViewGroup paramViewGroup)
  {
    this.b = paramViewGroup;
  }
  
  public void setCompanionSlots(Collection<CompanionAdSlot> paramCollection)
  {
    this.c = paramCollection;
  }
  
  public void setPlayer(VideoAdPlayer paramVideoAdPlayer)
  {
    this.a = paramVideoAdPlayer;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.b.a
 * JD-Core Version:    0.7.0.1
 */