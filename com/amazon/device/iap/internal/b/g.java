package com.amazon.device.iap.internal.b;

import com.amazon.device.iap.internal.a;
import com.amazon.device.iap.internal.b;
import java.util.HashMap;
import java.util.Map;

public final class g
  implements b
{
  private static final Map<Class, Class> a = new HashMap();
  
  static
  {
    a.put(com.amazon.device.iap.internal.c.class, c.class);
    a.put(a.class, f.class);
  }
  
  public <T> Class<T> a(Class<T> paramClass)
  {
    return (Class)a.get(paramClass);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.amazon.device.iap.internal.b.g
 * JD-Core Version:    0.7.0.1
 */