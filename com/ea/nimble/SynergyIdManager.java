package com.ea.nimble;

public class SynergyIdManager
{
  public static final String COMPONENT_ID = "com.ea.nimble.synergyidmanager";
  public static final String LOG_TAG = "SynergyID";
  public static final String NOTIFICATION_ANONYMOUS_SYNERGY_ID_CHANGED = "nimble.synergyidmanager.notification.anonymous_synergy_id_changed";
  public static final String NOTIFICATION_SYNERGY_ID_CHANGED = "nimble.synergyidmanager.notification.synergy_id_changed";
  
  public static ISynergyIdManager getComponent()
  {
    return SynergyIdManagerImpl.getComponent();
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ea.nimble.SynergyIdManager
 * JD-Core Version:    0.7.0.1
 */