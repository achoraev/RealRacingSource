package com.crashlytics.android.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import java.io.File;

public class D
  extends u
{
  private String a;
  private String b;
  private String c;
  private ao d;
  private aJ e;
  private long f;
  private av g;
  private O h;
  
  public static D a()
  {
    return (D)v.a().a(D.class);
  }
  
  private String b()
  {
    return ab.a(getContext(), "com.crashlytics.ApiEndpoint");
  }
  
  public final void a(af paramaf)
  {
    if (this.h != null) {
      this.h.a(paramaf.a());
    }
  }
  
  public final void a(ag paramag)
  {
    if (this.h != null) {
      this.h.b(paramag.a());
    }
  }
  
  protected final void c()
  {
    for (;;)
    {
      try
      {
        this.g = new av(v.a().b());
        this.e = new aJ(v.a().a(D.class));
        localContext = getContext();
        PackageManager localPackageManager = localContext.getPackageManager();
        this.d = new ao(localContext);
        this.a = localContext.getPackageName();
        localPackageInfo = localPackageManager.getPackageInfo(this.a, 0);
        this.b = Integer.toString(localPackageInfo.versionCode);
        if (localPackageInfo.versionName != null) {
          continue;
        }
        str = "0.0";
        this.c = str;
        if (Build.VERSION.SDK_INT < 9) {
          continue;
        }
        this.f = localPackageInfo.firstInstallTime;
      }
      catch (Exception localException)
      {
        Context localContext;
        PackageInfo localPackageInfo;
        String str;
        v.a().b().a("Crashlytics", "Error setting up app properties", localException);
        continue;
      }
      new Thread(new E(this), "Crashlytics Initializer").start();
      return;
      str = localPackageInfo.versionName;
      continue;
      this.f = new File(localContext.getPackageManager().getApplicationInfo(localContext.getPackageName(), 0).sourceDir).lastModified();
    }
  }
  
  public String getVersion()
  {
    return v.a().getVersion();
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.crashlytics.android.internal.D
 * JD-Core Version:    0.7.0.1
 */