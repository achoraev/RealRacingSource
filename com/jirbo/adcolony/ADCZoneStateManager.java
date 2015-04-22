package com.jirbo.adcolony;

import java.util.ArrayList;

class ADCZoneStateManager
{
  ADCController controller;
  ArrayList<ADCZoneState> data = new ArrayList();
  boolean modified = false;
  
  ADCZoneStateManager(ADCController paramADCController)
  {
    this.controller = paramADCController;
  }
  
  int count()
  {
    return this.data.size();
  }
  
  ADCZoneState find(String paramString)
  {
    int i = this.data.size();
    for (int j = 0; j < i; j++)
    {
      ADCZoneState localADCZoneState2 = (ADCZoneState)this.data.get(j);
      if (localADCZoneState2.uuid.equals(paramString)) {
        return localADCZoneState2;
      }
    }
    this.modified = true;
    ADCZoneState localADCZoneState1 = new ADCZoneState(paramString);
    this.data.add(localADCZoneState1);
    return localADCZoneState1;
  }
  
  ADCZoneState get(int paramInt)
  {
    return (ADCZoneState)this.data.get(paramInt);
  }
  
  void load()
  {
    ADCData.List localList = ADCJSON.load_List(new ADCDataFile("zone_state.txt"));
    if (localList != null)
    {
      this.data.clear();
      for (int k = 0; k < localList.count(); k++)
      {
        ADCData.Table localTable = localList.get_Table(k);
        ADCZoneState localADCZoneState = new ADCZoneState();
        if (localADCZoneState.parse(localTable)) {
          this.data.add(localADCZoneState);
        }
      }
    }
    String[] arrayOfString = this.controller.configuration.zone_ids;
    int i = arrayOfString.length;
    for (int j = 0; j < i; j++) {
      find(arrayOfString[j]);
    }
  }
  
  void save()
  {
    int i = 0;
    ADCLog.dev.println("Saving zone state...");
    this.modified = false;
    ADCData.List localList = new ADCData.List();
    String[] arrayOfString = this.controller.configuration.zone_ids;
    int j = arrayOfString.length;
    while (i < j)
    {
      localList.add(find(arrayOfString[i]).to_Table());
      i++;
    }
    ADCJSON.save(new ADCDataFile("zone_state.txt"), localList);
    ADCLog.dev.println("Saved zone state");
  }
  
  void update()
  {
    if (this.modified) {
      save();
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.jirbo.adcolony.ADCZoneStateManager
 * JD-Core Version:    0.7.0.1
 */