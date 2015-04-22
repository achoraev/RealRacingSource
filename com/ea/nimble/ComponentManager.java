package com.ea.nimble;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class ComponentManager
  implements LogSource
{
  private Map<String, Component> m_components = new LinkedHashMap();
  private Stage m_stage = Stage.CREATE;
  
  void cleanup()
  {
    ListIterator localListIterator = new ArrayList(this.m_components.values()).listIterator(this.m_components.size());
    while (localListIterator.hasPrevious()) {
      ((Component)localListIterator.previous()).cleanup();
    }
    this.m_stage = Stage.CREATE;
  }
  
  Component getComponent(String paramString)
  {
    return (Component)this.m_components.get(paramString);
  }
  
  Component[] getComponentList(String paramString)
  {
    ArrayList localArrayList = new ArrayList(this.m_components.size());
    Iterator localIterator = this.m_components.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      if (((String)localEntry.getKey()).startsWith(paramString)) {
        localArrayList.add(localEntry.getValue());
      }
    }
    return (Component[])localArrayList.toArray(new Component[localArrayList.size()]);
  }
  
  public String getLogSourceTitle()
  {
    return "Component";
  }
  
  void registerComponent(Component paramComponent, String paramString)
  {
    if (!Utility.validString(paramString))
    {
      Log.Helper.LOGF(this, "Cannot register component without valid componentId", new Object[0]);
      return;
    }
    if (paramComponent == null)
    {
      Log.Helper.LOGF(this, "Try to register invalid component with id: " + paramString, new Object[0]);
      return;
    }
    Component localComponent = (Component)this.m_components.get(paramString);
    if (localComponent == null) {
      Log.Helper.LOGI(this, "Register module: " + paramString, new Object[0]);
    }
    for (;;)
    {
      this.m_components.put(paramString, paramComponent);
      if (this.m_stage.compareTo(Stage.SETUP) < 0) {
        break;
      }
      if (localComponent != null)
      {
        if (this.m_stage.compareTo(Stage.SETUP) >= 0)
        {
          if (this.m_stage.compareTo(Stage.SUSPEND) >= 0) {
            localComponent.resume();
          }
          localComponent.cleanup();
        }
        localComponent.teardown();
      }
      paramComponent.setup();
      if (this.m_stage.compareTo(Stage.READY) < 0) {
        break;
      }
      paramComponent.restore();
      if (this.m_stage.compareTo(Stage.SUSPEND) < 0) {
        break;
      }
      paramComponent.suspend();
      return;
      Log.Helper.LOGI(this, "Register module(overwrite): " + paramString, new Object[0]);
    }
  }
  
  void restore()
  {
    Iterator localIterator = this.m_components.values().iterator();
    while (localIterator.hasNext()) {
      ((Component)localIterator.next()).restore();
    }
    this.m_stage = Stage.READY;
  }
  
  void resume()
  {
    Iterator localIterator = this.m_components.values().iterator();
    while (localIterator.hasNext()) {
      ((Component)localIterator.next()).resume();
    }
    this.m_stage = Stage.READY;
  }
  
  void setup()
  {
    Iterator localIterator = this.m_components.values().iterator();
    while (localIterator.hasNext()) {
      ((Component)localIterator.next()).setup();
    }
    this.m_stage = Stage.SETUP;
  }
  
  void suspend()
  {
    ListIterator localListIterator = new ArrayList(this.m_components.values()).listIterator(this.m_components.size());
    while (localListIterator.hasPrevious()) {
      ((Component)localListIterator.previous()).suspend();
    }
    this.m_stage = Stage.SUSPEND;
  }
  
  void teardown()
  {
    ListIterator localListIterator = new ArrayList(this.m_components.values()).listIterator(this.m_components.size());
    while (localListIterator.hasPrevious()) {
      ((Component)localListIterator.previous()).teardown();
    }
    this.m_stage = Stage.CREATE;
  }
  
  private static enum Stage
  {
    static
    {
      READY = new Stage("READY", 2);
      SUSPEND = new Stage("SUSPEND", 3);
      Stage[] arrayOfStage = new Stage[4];
      arrayOfStage[0] = CREATE;
      arrayOfStage[1] = SETUP;
      arrayOfStage[2] = READY;
      arrayOfStage[3] = SUSPEND;
      $VALUES = arrayOfStage;
    }
    
    private Stage() {}
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ea.nimble.ComponentManager
 * JD-Core Version:    0.7.0.1
 */