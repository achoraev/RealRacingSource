package com.jirbo.adcolony;

import android.app.Activity;
import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

class ADCUtil
{
  static byte[] byte_buffer = new byte[1024];
  static StringBuilder string_buffer = new StringBuilder();
  
  static boolean application_exists(String paramString)
  {
    try
    {
      AdColony.activity().getApplication().getPackageManager().getApplicationInfo(paramString, 0);
      return true;
    }
    catch (Exception localException) {}
    return false;
  }
  
  static String application_version()
  {
    try
    {
      String str = AdColony.activity().getPackageManager().getPackageInfo(AdColony.activity().getPackageName(), 0).versionName;
      return str;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      ADC.log_error("Failed to retrieve package info.");
    }
    return "1.0";
  }
  
  static String calculate_sha1(String paramString)
  {
    try
    {
      String str = AeSimpleSHA1.sha1(paramString);
      return str;
    }
    catch (Exception localException) {}
    return null;
  }
  
  static String create_uuid()
  {
    return UUID.randomUUID().toString();
  }
  
  static double current_time()
  {
    return System.currentTimeMillis() / 1000.0D;
  }
  
  static String format(double paramDouble, int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    format(paramDouble, paramInt, localStringBuilder);
    return localStringBuilder.toString();
  }
  
  static void format(double paramDouble, int paramInt, StringBuilder paramStringBuilder)
  {
    if ((Double.isNaN(paramDouble)) || (Double.isInfinite(paramDouble))) {
      paramStringBuilder.append(paramDouble);
    }
    long l1;
    long l3;
    for (;;)
    {
      return;
      if (paramDouble < 0.0D)
      {
        paramDouble = -paramDouble;
        paramStringBuilder.append('-');
      }
      if (paramInt == 0)
      {
        paramStringBuilder.append(Math.round(paramDouble));
        return;
      }
      l1 = Math.pow(10.0D, paramInt);
      long l2 = Math.round(paramDouble * l1);
      paramStringBuilder.append(l2 / l1);
      paramStringBuilder.append('.');
      l3 = l2 % l1;
      if (l3 != 0L) {
        break;
      }
      for (int i = 0; i < paramInt; i++) {
        paramStringBuilder.append('0');
      }
    }
    for (long l4 = l3 * 10L; l4 < l1; l4 *= 10L) {
      paramStringBuilder.append('0');
    }
    paramStringBuilder.append(l3);
  }
  
  static String load_file(String paramString)
  {
    return load_file(paramString, "");
  }
  
  static String load_file(String paramString1, String paramString2)
  {
    if (paramString1 != null) {}
    for (;;)
    {
      int i;
      int j;
      try
      {
        ADCLog.dev.print("Loading ").println(paramString1);
        FileInputStream localFileInputStream = new FileInputStream(paramString1);
        synchronized (byte_buffer)
        {
          string_buffer.setLength(0);
          string_buffer.append(paramString2);
          i = localFileInputStream.read(byte_buffer, 0, byte_buffer.length);
          break label160;
          if (j < i)
          {
            string_buffer.append((char)byte_buffer[j]);
            j++;
            continue;
          }
          i = localFileInputStream.read(byte_buffer, 0, byte_buffer.length);
          break label160;
          localFileInputStream.close();
          String str = string_buffer.toString();
          return str;
        }
        return "";
      }
      catch (IOException localIOException)
      {
        ADCLog.error.print("Unable to load ").println(paramString1);
        return "";
      }
      label160:
      if (i != -1) {
        j = 0;
      }
    }
  }
  
  static String package_name()
  {
    return ADC.activity().getPackageName();
  }
  
  static class Stopwatch
  {
    long start_ms = System.currentTimeMillis();
    
    double elapsed_seconds()
    {
      return (System.currentTimeMillis() - this.start_ms) / 1000.0D;
    }
    
    void restart()
    {
      this.start_ms = System.currentTimeMillis();
    }
    
    public String toString()
    {
      return ADCUtil.format(elapsed_seconds(), 2);
    }
  }
  
  static class Timer
  {
    double target_time = System.currentTimeMillis();
    
    Timer(double paramDouble)
    {
      restart(paramDouble);
    }
    
    boolean expired()
    {
      return remaining_seconds() == 0.0D;
    }
    
    double remaining_seconds()
    {
      double d1 = System.currentTimeMillis() / 1000.0D;
      double d2 = this.target_time - d1;
      if (d2 <= 0.0D) {
        d2 = 0.0D;
      }
      return d2;
    }
    
    void restart(double paramDouble)
    {
      this.target_time = (paramDouble + System.currentTimeMillis() / 1000.0D);
    }
    
    public String toString()
    {
      return ADCUtil.format(remaining_seconds(), 2);
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.jirbo.adcolony.ADCUtil
 * JD-Core Version:    0.7.0.1
 */