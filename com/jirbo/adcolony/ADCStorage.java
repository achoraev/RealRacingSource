package com.jirbo.adcolony;

import android.app.Activity;
import android.os.Environment;
import android.os.StatFs;
import java.io.File;

public class ADCStorage
{
  String adcolony_path;
  ADCController controller;
  File data_file;
  String data_path;
  File media_file;
  String media_path;
  
  ADCStorage(ADCController paramADCController)
  {
    this.controller = paramADCController;
  }
  
  double available_space(String paramString)
  {
    try
    {
      StatFs localStatFs = new StatFs(paramString);
      long l = localStatFs.getBlockSize();
      int i = localStatFs.getAvailableBlocks();
      return l * i;
    }
    catch (RuntimeException localRuntimeException) {}
    return 0.0D;
  }
  
  void configure()
  {
    ADCLog.dev.println("Configuring storage");
    int i = 1;
    if ((external_storage_path() != null) && (available_space(external_storage_path()) > 1048576.0D + available_space(internal_storage_path())) && (available_space(internal_storage_path()) < 31457280.0D)) {
      i = 0;
    }
    if (i != 0)
    {
      ADCLog.debug.println("Using internal storage:");
      this.adcolony_path = (internal_storage_path() + "/adc/");
    }
    for (;;)
    {
      this.media_path = (this.adcolony_path + "media/");
      ADCLog.dev.println(this.media_path);
      this.media_file = new File(this.media_path);
      if (!this.media_file.isDirectory())
      {
        this.media_file.delete();
        this.media_file.mkdirs();
      }
      if (this.media_file.isDirectory()) {
        break;
      }
      ADC.on_fatal_error("Cannot create media folder.");
      return;
      this.adcolony_path = (external_storage_path() + "/.adc2/" + ADCUtil.package_name() + "/");
      ADCLog.debug.println("Using external storage:");
    }
    if (available_space(this.media_path) < 20971520.0D)
    {
      ADC.on_fatal_error("Not enough space to store temporary files (" + available_space(this.media_path) + " bytes available).");
      return;
    }
    this.data_path = (internal_storage_path() + "/adc/data/");
    if (ADC.log_level == 0) {
      this.data_path = (this.adcolony_path + "data/");
    }
    ADCLog.dev.print("Internal data path: ").println(this.data_path);
    this.data_file = new File(this.data_path);
    if (!this.data_file.isDirectory()) {
      this.data_file.delete();
    }
    this.data_file.mkdirs();
  }
  
  String external_storage_path()
  {
    if ("mounted".equals(Environment.getExternalStorageState())) {
      return Environment.getExternalStorageDirectory().getAbsolutePath();
    }
    return null;
  }
  
  String internal_storage_path()
  {
    return AdColony.activity().getFilesDir().getAbsolutePath();
  }
  
  void validate_storage_paths()
  {
    if ((this.media_file == null) || (this.data_file == null)) {
      return;
    }
    if (!this.media_file.isDirectory()) {
      this.media_file.delete();
    }
    if (!this.data_file.isDirectory()) {
      this.data_file.delete();
    }
    this.media_file.mkdirs();
    this.data_file.mkdirs();
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.jirbo.adcolony.ADCStorage
 * JD-Core Version:    0.7.0.1
 */