package com.amazon.device.iap.internal.c;

import com.amazon.device.iap.internal.util.d;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

public class b
{
  private static final b b = new b();
  private final Set<String> a = new ConcurrentSkipListSet();
  
  public static b a()
  {
    return b;
  }
  
  public boolean a(String paramString)
  {
    if (!d.a(paramString)) {
      return this.a.remove(paramString);
    }
    return false;
  }
  
  public void b(String paramString)
  {
    if (!d.a(paramString)) {
      this.a.add(paramString);
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.amazon.device.iap.internal.c.b
 * JD-Core Version:    0.7.0.1
 */