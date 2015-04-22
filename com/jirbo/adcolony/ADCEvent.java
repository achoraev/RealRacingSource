package com.jirbo.adcolony;

abstract class ADCEvent
{
  ADCController controller;
  
  ADCEvent(ADCController paramADCController)
  {
    this(paramADCController, true);
  }
  
  ADCEvent(ADCController paramADCController, boolean paramBoolean)
  {
    this.controller = paramADCController;
    if (paramBoolean) {
      paramADCController.queue_event(this);
    }
  }
  
  abstract void dispatch();
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.jirbo.adcolony.ADCEvent
 * JD-Core Version:    0.7.0.1
 */