package com.amazon.device.iap.internal.a;

import com.amazon.device.iap.internal.b;
import java.util.HashMap;
import java.util.Map;

public final class d
  implements b
{
  private static final Map<Class, Class> a = new HashMap();
  
  static
  {
    a.put(com.amazon.device.iap.internal.c.class, c.class);
    a.put(com.amazon.device.iap.internal.a.class, a.class);
  }
  
  public <T> Class<T> a(Class<T> paramClass)
  {
    return (Class)a.get(paramClass);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.amazon.device.iap.internal.a.d
 * JD-Core Version:    0.7.0.1
 */