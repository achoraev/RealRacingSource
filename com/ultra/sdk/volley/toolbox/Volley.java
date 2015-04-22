package com.ultra.sdk.volley.toolbox;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.http.AndroidHttpClient;
import android.os.Build.VERSION;
import com.ultra.sdk.volley.RequestQueue;
import java.io.File;

public class Volley
{
  private static final String DEFAULT_CACHE_DIR = "volley";
  
  public static RequestQueue newRequestQueue(Context paramContext)
  {
    return newRequestQueue(paramContext, null);
  }
  
  public static RequestQueue newRequestQueue(Context paramContext, HttpStack paramHttpStack)
  {
    File localFile = new File(paramContext.getCacheDir(), "volley");
    Object localObject = "volley/0";
    try
    {
      String str1 = paramContext.getPackageName();
      PackageInfo localPackageInfo = paramContext.getPackageManager().getPackageInfo(str1, 0);
      String str2 = str1 + "/" + localPackageInfo.versionCode;
      localObject = str2;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      label68:
      label125:
      break label68;
    }
    if (paramHttpStack == null) {
      if (Build.VERSION.SDK_INT < 9) {
        break label125;
      }
    }
    for (paramHttpStack = new HurlStack();; paramHttpStack = new HttpClientStack(AndroidHttpClient.newInstance((String)localObject)))
    {
      BasicNetwork localBasicNetwork = new BasicNetwork(paramHttpStack);
      RequestQueue localRequestQueue = new RequestQueue(new DiskBasedCache(localFile), localBasicNetwork);
      localRequestQueue.start();
      return localRequestQueue;
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ultra.sdk.volley.toolbox.Volley
 * JD-Core Version:    0.7.0.1
 */