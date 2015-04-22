package com.jirbo.adcolony;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

class ADCMediaManager
  implements ADCDownload.Listener
{
  int active_downloads;
  ArrayList<Asset> assets = new ArrayList();
  boolean configured;
  ADCController controller;
  HashMap<String, Asset> lookup = new HashMap();
  boolean modified;
  int next_file_number = 1;
  ArrayList<String> pending_downloads = new ArrayList();
  ADCUtil.Timer save_timer = new ADCUtil.Timer(2.0D);
  double total_media_bytes;
  
  ADCMediaManager(ADCController paramADCController)
  {
    this.controller = paramADCController;
  }
  
  void cache(String paramString1, String paramString2)
  {
    if ((paramString1 == null) || (paramString1.equals(""))) {}
    Asset localAsset;
    do
    {
      return;
      if (paramString2 == null) {
        paramString2 = "";
      }
      localAsset = (Asset)this.lookup.get(paramString1);
      if (localAsset == null) {
        break;
      }
      localAsset.last_accessed = ADCUtil.current_time();
    } while ((localAsset.last_modified.equals(paramString2)) && ((localAsset.ready) || (localAsset.downloading)));
    for (;;)
    {
      if (localAsset.file_number == 0)
      {
        int i = get_next_file_number();
        String str1 = url_to_filename(paramString1, i);
        String str2 = this.controller.storage.media_path + str1;
        localAsset.file_number = i;
        localAsset.filepath = str2;
      }
      localAsset.last_modified = paramString2;
      localAsset.downloading = true;
      localAsset.ready = false;
      ADCLog.dev.print("Adding ").print(paramString1).println(" to pending downloads.");
      this.pending_downloads.add(paramString1);
      this.modified = true;
      this.save_timer.restart(2.0D);
      ADC.active = true;
      return;
      localAsset = new Asset();
      localAsset.url = paramString1;
      this.assets.add(localAsset);
      localAsset.last_accessed = ADCUtil.current_time();
      this.lookup.put(paramString1, localAsset);
    }
  }
  
  void configure()
  {
    load();
    this.configured = true;
  }
  
  int get_next_file_number()
  {
    this.modified = true;
    this.save_timer.restart(2.0D);
    int i = this.next_file_number;
    this.next_file_number = (i + 1);
    return i;
  }
  
  boolean is_cached(String paramString)
  {
    if ((paramString == null) || (paramString.equals(""))) {}
    Asset localAsset;
    do
    {
      do
      {
        return false;
        localAsset = (Asset)this.lookup.get(paramString);
        if (localAsset == null)
        {
          this.controller.ad_manager.app.cache_media();
          return false;
        }
        if (!localAsset.ready) {
          break;
        }
      } while (localAsset.downloading);
      localAsset.last_accessed = ADCUtil.current_time();
      return true;
    } while (localAsset.downloading);
    this.controller.ad_manager.app.cache_media();
    return false;
  }
  
  void load()
  {
    ADCLog.dev.println("Loading media info");
    ADCData.Table localTable1 = ADCJSON.load_Table(new ADCDataFile("media_info.txt"));
    if (localTable1 == null)
    {
      localTable1 = new ADCData.Table();
      ADCLog.dev.println("No saved media info exists.");
    }
    for (;;)
    {
      this.next_file_number = localTable1.get_Integer("next_file_number");
      if (this.next_file_number <= 0) {
        this.next_file_number = 1;
      }
      ADCData.List localList = localTable1.get_List("assets");
      if (localList == null) {
        break;
      }
      this.assets.clear();
      for (int i = 0; i < localList.count(); i++)
      {
        ADCData.Table localTable2 = localList.get_Table(i);
        Asset localAsset = new Asset();
        localAsset.url = localTable2.get_String("url");
        localAsset.filepath = localTable2.get_String("filepath");
        localAsset.last_modified = localTable2.get_String("last_modified");
        localAsset.file_number = localTable2.get_Integer("file_number");
        localAsset.size = localTable2.get_Integer("size");
        localAsset.ready = localTable2.get_Logical("ready");
        localAsset.last_accessed = localTable2.get_Real("last_accessed");
        if (localAsset.file_number > this.next_file_number) {
          this.next_file_number = (1 + localAsset.file_number);
        }
        this.assets.add(localAsset);
      }
      ADCLog.dev.println("Loaded media info");
    }
    refresh();
  }
  
  String local_filepath(String paramString)
  {
    Asset localAsset = (Asset)this.lookup.get(paramString);
    if ((localAsset == null) || (localAsset.filepath == null)) {
      return "(file not found)";
    }
    localAsset.last_accessed = ADCUtil.current_time();
    this.modified = true;
    this.save_timer.restart(2.0D);
    return localAsset.filepath;
  }
  
  public void on_download_finished(ADCDownload paramADCDownload)
  {
    Asset localAsset = (Asset)paramADCDownload.info;
    this.active_downloads = (-1 + this.active_downloads);
    this.modified = true;
    this.save_timer.restart(2.0D);
    localAsset.ready = paramADCDownload.success;
    localAsset.downloading = false;
    if (paramADCDownload.success)
    {
      localAsset.size = paramADCDownload.size;
      this.total_media_bytes += localAsset.size;
      ADCLog.dev.print("Downloaded ").println(localAsset.url);
    }
    ADC.has_ad_availability_changed();
    start_next_download();
  }
  
  void purge_old_assets()
  {
    double d = this.controller.ad_manager.app.media_pool_size;
    if (d == 0.0D) {
      return;
    }
    Object localObject;
    do
    {
      ADCLog.debug.print("Deleting ").println(localObject.filepath);
      localObject.ready = false;
      new File(localObject.filepath).delete();
      localObject.filepath = null;
      this.total_media_bytes -= localObject.size;
      ADCLog.debug.print("Media pool now at ").print(this.total_media_bytes / 1048576.0D).print("/").print(d / 1048576.0D).println(" MB");
      this.modified = true;
      this.save_timer.restart(2.0D);
      if (this.total_media_bytes <= this.controller.ad_manager.app.media_pool_size) {
        break;
      }
      localObject = null;
      for (int i = 0; i < this.assets.size(); i++)
      {
        Asset localAsset = (Asset)this.assets.get(i);
        if ((localAsset.ready) && ((localObject == null) || (localAsset.last_accessed < localObject.last_accessed))) {
          localObject = localAsset;
        }
      }
    } while (localObject != null);
  }
  
  void refresh()
  {
    HashMap localHashMap1 = new HashMap();
    String str1 = this.controller.storage.media_path;
    String[] arrayOfString = new File(str1).list();
    if (arrayOfString == null) {}
    for (String str5 : new String[0])
    {
      String str6 = str1 + str5;
      localHashMap1.put(str6, str6);
    }
    HashMap localHashMap2 = new HashMap();
    this.total_media_bytes = 0.0D;
    ArrayList localArrayList = new ArrayList();
    for (int k = 0; k < this.assets.size(); k++)
    {
      Asset localAsset2 = (Asset)this.assets.get(k);
      if ((!localAsset2.downloading) && (localAsset2.ready))
      {
        String str4 = localAsset2.filepath;
        if ((localHashMap1.containsKey(str4)) && (new File(str4).length() == localAsset2.size))
        {
          this.total_media_bytes += localAsset2.size;
          localArrayList.add(localAsset2);
          localHashMap2.put(str4, str4);
        }
      }
    }
    this.assets = localArrayList;
    int m = arrayOfString.length;
    for (int n = 0; n < m; n++)
    {
      String str2 = arrayOfString[n];
      String str3 = str1 + str2;
      if (!localHashMap2.containsKey(str3))
      {
        ADCLog.debug.print("Deleting unused media ").println(str3);
        new File(str3).delete();
      }
    }
    this.lookup.clear();
    for (int i1 = 0; i1 < this.assets.size(); i1++)
    {
      Asset localAsset1 = (Asset)this.assets.get(i1);
      this.lookup.put(localAsset1.url, localAsset1);
    }
    double d = this.controller.ad_manager.app.media_pool_size;
    if (d > 0.0D) {
      ADCLog.debug.print("Media pool at ").print(this.total_media_bytes / 1048576.0D).print("/").print(d / 1048576.0D).println(" MB");
    }
  }
  
  void save()
  {
    ADCLog.dev.println("Saving media info");
    ADCData.List localList = new ADCData.List();
    for (int i = 0; i < this.assets.size(); i++)
    {
      Asset localAsset = (Asset)this.assets.get(i);
      if ((localAsset.ready) && (!localAsset.downloading))
      {
        ADCData.Table localTable2 = new ADCData.Table();
        localTable2.set("url", localAsset.url);
        localTable2.set("filepath", localAsset.filepath);
        localTable2.set("last_modified", localAsset.last_modified);
        localTable2.set("file_number", localAsset.file_number);
        localTable2.set("size", localAsset.size);
        localTable2.set("ready", localAsset.ready);
        localTable2.set("last_accessed", localAsset.last_accessed);
        localList.add(localTable2);
      }
    }
    ADCData.Table localTable1 = new ADCData.Table();
    localTable1.set("next_file_number", this.next_file_number);
    localTable1.set("assets", localList);
    ADCJSON.save(new ADCDataFile("media_info.txt"), localTable1);
    this.modified = false;
  }
  
  void start_next_download()
  {
    if ((this.controller.ad_manager.app.cache_network_pass_filter.equals("wifi")) && (!ADCNetwork.using_wifi())) {
      ADCLog.dev.println("Skipping asset download due to CACHE_FILTER_WIFI");
    }
    for (;;)
    {
      return;
      if ((this.controller.ad_manager.app.cache_network_pass_filter.equals("cell")) && (!ADCNetwork.using_mobile()))
      {
        ADCLog.dev.println("Skipping asset download due to CACHE_FILTER_CELL.");
        return;
      }
      while ((this.active_downloads < 3) && (this.pending_downloads.size() > 0))
      {
        String str = (String)this.pending_downloads.remove(0);
        Asset localAsset = (Asset)this.lookup.get(str);
        if ((localAsset != null) && ((str == null) || (str.equals(""))))
        {
          ADCLog.error.println("[ADC ERROR] - NULL URL");
          new RuntimeException().printStackTrace();
        }
        if ((localAsset != null) && (str != null) && (!str.equals("")))
        {
          ADC.active = true;
          this.active_downloads = (1 + this.active_downloads);
          new ADCDownload(this.controller, str, this, localAsset.filepath).with_info(localAsset).start();
        }
      }
    }
  }
  
  void update()
  {
    start_next_download();
    if ((this.modified) && (this.save_timer.expired()))
    {
      purge_old_assets();
      save();
    }
  }
  
  String url_to_filename(String paramString, int paramInt)
  {
    int i = paramString.lastIndexOf('.');
    if (i == -1) {
      return paramInt + "";
    }
    return paramInt + paramString.substring(i);
  }
  
  static class Asset
  {
    boolean downloading;
    int file_number;
    String filepath;
    double last_accessed;
    String last_modified;
    boolean ready;
    int size;
    String url;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.jirbo.adcolony.ADCMediaManager
 * JD-Core Version:    0.7.0.1
 */