package com.amazon.device.iap.internal.a;

import android.util.Log;

public class a
  implements com.amazon.device.iap.internal.a
{
  private static String a(String paramString)
  {
    return "In App Purchasing SDK - Sandbox Mode: " + paramString;
  }
  
  public void a(String paramString1, String paramString2)
  {
    Log.d(paramString1, a(paramString2));
  }
  
  public boolean a()
  {
    return true;
  }
  
  public void b(String paramString1, String paramString2)
  {
    Log.e(paramString1, a(paramString2));
  }
  
  public boolean b()
  {
    return true;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.amazon.device.iap.internal.a.a
 * JD-Core Version:    0.7.0.1
 */