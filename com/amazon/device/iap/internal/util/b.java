package com.amazon.device.iap.internal.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class b
{
  private static final String a = b.class.getName() + "_PREFS";
  
  public static String a(String paramString)
  {
    d.a(paramString, "userId");
    Context localContext = com.amazon.device.iap.internal.d.d().b();
    d.a(localContext, "context");
    return localContext.getSharedPreferences(a, 0).getString(paramString, null);
  }
  
  public static void a(String paramString1, String paramString2)
  {
    d.a(paramString1, "userId");
    Context localContext = com.amazon.device.iap.internal.d.d().b();
    d.a(localContext, "context");
    SharedPreferences.Editor localEditor = localContext.getSharedPreferences(a, 0).edit();
    localEditor.putString(paramString1, paramString2);
    localEditor.commit();
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.amazon.device.iap.internal.util.b
 * JD-Core Version:    0.7.0.1
 */