package com.google.android.gms.tagmanager;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class da<K, V>
  implements k<K, V>
{
  private final Map<K, V> arD = new HashMap();
  private final int arE;
  private final l.a<K, V> arF;
  private int arG;
  
  da(int paramInt, l.a<K, V> parama)
  {
    this.arE = paramInt;
    this.arF = parama;
  }
  
  public void e(K paramK, V paramV)
  {
    if ((paramK == null) || (paramV == null)) {
      try
      {
        throw new NullPointerException("key == null || value == null");
      }
      finally {}
    }
    this.arG += this.arF.sizeOf(paramK, paramV);
    if (this.arG > this.arE)
    {
      Iterator localIterator = this.arD.entrySet().iterator();
      do
      {
        if (!localIterator.hasNext()) {
          break;
        }
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        this.arG -= this.arF.sizeOf(localEntry.getKey(), localEntry.getValue());
        localIterator.remove();
      } while (this.arG > this.arE);
    }
    this.arD.put(paramK, paramV);
  }
  
  public V get(K paramK)
  {
    try
    {
      Object localObject2 = this.arD.get(paramK);
      return localObject2;
    }
    finally
    {
      localObject1 = finally;
      throw localObject1;
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.da
 * JD-Core Version:    0.7.0.1
 */