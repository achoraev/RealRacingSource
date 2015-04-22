package com.jirbo.adcolony;

import java.io.Serializable;

class ADCZoneState
  implements Serializable
{
  int play_order_index;
  int session_play_count;
  int skipped_plays;
  String uuid = "";
  
  ADCZoneState() {}
  
  ADCZoneState(String paramString)
  {
    this.uuid = paramString;
  }
  
  boolean parse(ADCData.Table paramTable)
  {
    if (paramTable == null) {
      return false;
    }
    this.uuid = paramTable.get_String("uuid", "error");
    this.skipped_plays = paramTable.get_Integer("skipped_plays");
    this.play_order_index = paramTable.get_Integer("play_order_index");
    return true;
  }
  
  ADCData.Table to_Table()
  {
    ADCData.Table localTable = new ADCData.Table();
    localTable.set("uuid", this.uuid);
    localTable.set("skipped_plays", this.skipped_plays);
    localTable.set("play_order_index", this.play_order_index);
    return localTable;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.jirbo.adcolony.ADCZoneState
 * JD-Core Version:    0.7.0.1
 */