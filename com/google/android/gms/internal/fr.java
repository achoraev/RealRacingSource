package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@ez
public final class fr
  extends fm.a
{
  private static final Object uf = new Object();
  private static fr ug;
  private final Context mContext;
  private final fx uh;
  private final ci ui;
  private final bm uj;
  
  fr(Context paramContext, bm parambm, ci paramci, fx paramfx)
  {
    this.mContext = paramContext;
    this.uh = paramfx;
    this.ui = paramci;
    this.uj = parambm;
  }
  
  private static gw.a I(String paramString)
  {
    new gw.a()
    {
      public void a(gv paramAnonymousgv)
      {
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = "AFMA_buildAdURL";
        arrayOfObject[1] = this.uo;
        String str = String.format("javascript:%s(%s);", arrayOfObject);
        gs.V("About to execute: " + str);
        paramAnonymousgv.loadUrl(str);
      }
    };
  }
  
  private static fk a(Context paramContext, bm parambm, ci paramci, fx paramfx, final fi paramfi)
  {
    gs.S("Starting ad request from service.");
    paramci.init();
    fw localfw = new fw(paramContext);
    if (localfw.vd == -1)
    {
      gs.S("Device is offline.");
      return new fk(2);
    }
    final ft localft = new ft(paramfi.applicationInfo.packageName);
    if (paramfi.tx.extras != null)
    {
      String str4 = paramfi.tx.extras.getString("_ad");
      if (str4 != null) {
        return fs.a(paramContext, paramfi, str4);
      }
    }
    Location localLocation = paramci.a(250L);
    final String str1 = parambm.bp();
    String str2 = fs.a(paramfi, localfw, localLocation, parambm.bq(), parambm.br());
    if (str2 == null) {
      return new fk(0);
    }
    final gw.a locala = I(str2);
    gr.wC.post(new Runnable()
    {
      public void run()
      {
        gv localgv = gv.a(this.mV, new ay(), false, false, null, paramfi.lD);
        localgv.setWillNotDraw(true);
        localft.b(localgv);
        gw localgw = localgv.du();
        localgw.a("/invalidRequest", localft.us);
        localgw.a("/loadAdURL", localft.ut);
        localgw.a("/log", bx.pG);
        localgw.a(locala);
        gs.S("Loading the JS library.");
        localgv.loadUrl(str1);
      }
    });
    fv localfv;
    try
    {
      localfv = (fv)localft.cK().get(10L, TimeUnit.SECONDS);
      if (localfv == null) {
        return new fk(0);
      }
    }
    catch (Exception localException)
    {
      return new fk(0);
    }
    if (localfv.getErrorCode() != -2) {
      return new fk(localfv.getErrorCode());
    }
    boolean bool = localfv.cN();
    String str3 = null;
    if (bool) {
      str3 = paramfx.K(paramfi.ty.packageName);
    }
    return a(paramContext, paramfi.lD.wD, localfv.getUrl(), str3, localfv);
  }
  
  public static fk a(Context paramContext, String paramString1, String paramString2, String paramString3, fv paramfv)
  {
    try
    {
      localfu = new fu();
      gs.S("AdRequestServiceImpl: Sending request: " + paramString2);
      URL localURL1 = new URL(paramString2);
      l = SystemClock.elapsedRealtime();
      localURL2 = localURL1;
      i = 0;
    }
    catch (IOException localIOException)
    {
      try
      {
        for (;;)
        {
          fu localfu;
          long l;
          URL localURL2;
          int i;
          gj.a(paramContext, paramString1, false, localHttpURLConnection);
          if (!TextUtils.isEmpty(paramString3)) {
            localHttpURLConnection.addRequestProperty("x-afma-drt-cookie", paramString3);
          }
          if ((paramfv != null) && (!TextUtils.isEmpty(paramfv.cM())))
          {
            localHttpURLConnection.setDoOutput(true);
            byte[] arrayOfByte = paramfv.cM().getBytes();
            localHttpURLConnection.setFixedLengthStreamingMode(arrayOfByte.length);
            BufferedOutputStream localBufferedOutputStream = new BufferedOutputStream(localHttpURLConnection.getOutputStream());
            localBufferedOutputStream.write(arrayOfByte);
            localBufferedOutputStream.close();
          }
          int j = localHttpURLConnection.getResponseCode();
          Map localMap = localHttpURLConnection.getHeaderFields();
          if ((j >= 200) && (j < 300))
          {
            String str2 = localURL2.toString();
            String str3 = gj.a(new InputStreamReader(localHttpURLConnection.getInputStream()));
            a(str2, localMap, str3, j);
            localfu.a(str2, localMap, str3);
            fk localfk4 = localfu.i(l);
            return localfk4;
          }
          a(localURL2.toString(), localMap, null, j);
          if ((j >= 300) && (j < 400))
          {
            String str1 = localHttpURLConnection.getHeaderField("Location");
            if (TextUtils.isEmpty(str1))
            {
              gs.W("No location header to follow redirect.");
              fk localfk3 = new fk(0);
              return localfk3;
            }
            localURL2 = new URL(str1);
            i++;
            if (i > 5)
            {
              gs.W("Too many redirects.");
              fk localfk2 = new fk(0);
              return localfk2;
            }
          }
          else
          {
            gs.W("Received error HTTP response code: " + j);
            fk localfk1 = new fk(0);
            return localfk1;
          }
          localfu.e(localMap);
          localHttpURLConnection.disconnect();
        }
      }
      finally
      {
        HttpURLConnection localHttpURLConnection;
        localHttpURLConnection.disconnect();
      }
      localIOException = localIOException;
      gs.W("Error while connecting to ad server: " + localIOException.getMessage());
      return new fk(2);
    }
    localHttpURLConnection = (HttpURLConnection)localURL2.openConnection();
  }
  
  public static fr a(Context paramContext, bm parambm, ci paramci, fx paramfx)
  {
    synchronized (uf)
    {
      if (ug == null) {
        ug = new fr(paramContext.getApplicationContext(), parambm, paramci, paramfx);
      }
      fr localfr = ug;
      return localfr;
    }
  }
  
  private static void a(String paramString1, Map<String, List<String>> paramMap, String paramString2, int paramInt)
  {
    if (gs.u(2))
    {
      gs.V("Http Response: {\n  URL:\n    " + paramString1 + "\n  Headers:");
      if (paramMap != null)
      {
        Iterator localIterator1 = paramMap.keySet().iterator();
        while (localIterator1.hasNext())
        {
          String str1 = (String)localIterator1.next();
          gs.V("    " + str1 + ":");
          Iterator localIterator2 = ((List)paramMap.get(str1)).iterator();
          while (localIterator2.hasNext())
          {
            String str2 = (String)localIterator2.next();
            gs.V("      " + str2);
          }
        }
      }
      gs.V("  Body:");
      if (paramString2 != null) {
        for (int i = 0; i < Math.min(paramString2.length(), 100000); i += 1000) {
          gs.V(paramString2.substring(i, Math.min(paramString2.length(), i + 1000)));
        }
      }
      gs.V("    null");
      gs.V("  Response Code:\n    " + paramInt + "\n}");
    }
  }
  
  public fk b(fi paramfi)
  {
    return a(this.mContext, this.uj, this.ui, this.uh, paramfi);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.fr
 * JD-Core Version:    0.7.0.1
 */