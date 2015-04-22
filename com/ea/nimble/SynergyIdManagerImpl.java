package com.ea.nimble;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.util.HashMap;
import java.util.Map;

class SynergyIdManagerImpl
  extends Component
  implements ISynergyIdManager, LogSource
{
  private static final String ANONYMOUS_ID_PERSISTENCE_DATA_ID = "anonymousId";
  private static final String AUTHENTICATOR_PERSISTENCE_DATA_ID = "authenticator";
  private static final String CURRENT_ID_PERSISTENCE_DATA_ID = "currentId";
  private static final String SYNERGY_ID_MANAGER_ANONYMOUS_ID_PERSISTENCE_ID = "com.ea.nimble.synergyidmanager.anonymousId";
  private static final String VERSION_PERSISTENCE_DATA_ID = "dataVersion";
  private String m_anonymousSynergyId;
  private String m_authenticatorIdentifier;
  private String m_currentSynergyId;
  private BroadcastReceiver m_receiver = new SynergyIdManagerReceiver(null);
  
  public static ISynergyIdManager getComponent()
  {
    return (ISynergyIdManager)Base.getComponent("com.ea.nimble.synergyidmanager");
  }
  
  private void onSynergyEnvironmentStartupRequestsFinished()
  {
    if (SynergyEnvironment.getComponent() != null)
    {
      Log.Helper.LOGD(this, "onSynergyEnvironmentStartupRequestsFinished - Process the notification, everything looks okay", new Object[0]);
      setAnonymousSynergyId(SynergyEnvironment.getComponent().getSynergyId());
      if (!Utility.validString(this.m_currentSynergyId)) {
        setCurrentSynergyId(this.m_anonymousSynergyId);
      }
      return;
    }
    Log.Helper.LOGI(this, "onSynergyEnvironmentStartupRequestsFinished - Aborted because we were unable to get SynergyEnvironment", new Object[0]);
  }
  
  private void restoreFromPersistent()
  {
    Persistence localPersistence1 = PersistenceService.getPersistenceForNimbleComponent("com.ea.nimble.synergyidmanager", Persistence.Storage.CACHE);
    String str1;
    if (localPersistence1 != null)
    {
      Log.Helper.LOGD("Loaded persistence data version,  %s.", localPersistence1.getStringValue("dataVersion"), new Object[0]);
      setCurrentSynergyId(localPersistence1.getStringValue("currentId"));
      this.m_authenticatorIdentifier = localPersistence1.getStringValue("authenticator");
      Object[] arrayOfObject3 = new Object[2];
      arrayOfObject3[0] = this.m_currentSynergyId;
      arrayOfObject3[1] = this.m_authenticatorIdentifier;
      Log.Helper.LOGD(this, "Loaded Synergy ID, %s, with authenticator, %s.", arrayOfObject3);
      Persistence localPersistence2 = PersistenceService.getPersistenceForNimbleComponent("com.ea.nimble.synergyidmanager.anonymousId", Persistence.Storage.DOCUMENT);
      if (localPersistence2 == null) {
        break label170;
      }
      String str2 = localPersistence2.getStringValue("dataVersion");
      Object[] arrayOfObject1 = new Object[1];
      arrayOfObject1[0] = Utility.safeString(str2);
      Log.Helper.LOGD(this, "Loaded persistence data version, %s.", arrayOfObject1);
      str1 = localPersistence2.getStringValue("anonymousId");
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = Utility.safeString(str1);
      Log.Helper.LOGD(this, "Loaded anonymous Synergy ID, %s.", arrayOfObject2);
    }
    for (;;)
    {
      setAnonymousSynergyId(str1);
      return;
      Log.Helper.LOGE(this, "Could not get persistence object to load from.", new Object[0]);
      break;
      label170:
      Log.Helper.LOGE(this, "Could not get anonymous Synergy ID persistence object to load from.", new Object[0]);
      str1 = null;
    }
  }
  
  private void saveDataToPersistent()
  {
    Persistence localPersistence1 = PersistenceService.getPersistenceForNimbleComponent("com.ea.nimble.synergyidmanager.anonymousId", Persistence.Storage.DOCUMENT);
    if (localPersistence1 != null)
    {
      Log.Helper.LOGD("Saving anonymous Synergy ID, %s, to persistent.", this.m_anonymousSynergyId, new Object[0]);
      localPersistence1.setValue("dataVersion", "1.0.0");
      localPersistence1.setValue("anonymousId", this.m_anonymousSynergyId);
      localPersistence1.setBackUp(true);
      localPersistence1.synchronize();
    }
    for (;;)
    {
      Persistence localPersistence2 = PersistenceService.getPersistenceForNimbleComponent("com.ea.nimble.synergyidmanager", Persistence.Storage.CACHE);
      if (localPersistence2 == null) {
        break;
      }
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = this.m_currentSynergyId;
      arrayOfObject[1] = this.m_authenticatorIdentifier;
      Log.Helper.LOGD(this, "Saving current Synergy ID, %s, and authenticator, %s, to persistent.", arrayOfObject);
      localPersistence2.setValue("dataVersion", "1.0.0");
      localPersistence2.setValue("currentId", this.m_currentSynergyId);
      localPersistence2.setValue("authenticator", this.m_authenticatorIdentifier);
      localPersistence2.synchronize();
      return;
      Log.Helper.LOGE(this, "Could not get anonymous Synergy ID persistence object to save to.", new Object[0]);
    }
    Log.Helper.LOGE(this, "Could not get persistence object to save to.", new Object[0]);
  }
  
  private void setAnonymousSynergyId(String paramString)
  {
    if ((Utility.validString(this.m_anonymousSynergyId)) && (!Utility.validString(paramString)))
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = this.m_anonymousSynergyId;
      Log.Helper.LOGE(this, "Attempt to set invalid anonymous Synergy ID over existing ID, %s. Ignoring attempt.", arrayOfObject);
    }
    for (;;)
    {
      return;
      String str = this.m_anonymousSynergyId;
      this.m_anonymousSynergyId = paramString;
      saveDataToPersistent();
      if (!Utility.validString(str)) {}
      while (this.m_authenticatorIdentifier == null)
      {
        setCurrentSynergyId(this.m_anonymousSynergyId);
        return;
        if (((Utility.validString(str)) && (!str.equals(this.m_anonymousSynergyId))) || ((Utility.validString(this.m_anonymousSynergyId)) && (!this.m_anonymousSynergyId.equals(str))))
        {
          HashMap localHashMap = new HashMap();
          localHashMap.put("previousSynergyId", Utility.safeString(str));
          localHashMap.put("currentSynergyId", Utility.safeString(this.m_anonymousSynergyId));
          Utility.sendBroadcast("nimble.synergyidmanager.notification.anonymous_synergy_id_changed", localHashMap);
        }
      }
    }
  }
  
  private void setCurrentSynergyId(String paramString)
  {
    if ((Utility.validString(this.m_currentSynergyId)) && (!Utility.validString(paramString)))
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = this.m_currentSynergyId;
      Log.Helper.LOGE(this, "Attempt to set invalid current Synergy ID over existing ID, %s. Ignoring attempt.", arrayOfObject);
    }
    String str;
    do
    {
      return;
      str = this.m_currentSynergyId;
      this.m_currentSynergyId = paramString;
      saveDataToPersistent();
    } while ((!Utility.validString(str)) || (((!Utility.validString(str)) || (str.equals(this.m_currentSynergyId))) && ((!Utility.validString(this.m_currentSynergyId)) || (this.m_currentSynergyId.equals(str)))));
    HashMap localHashMap = new HashMap();
    localHashMap.put("previousSynergyId", Utility.safeString(str));
    localHashMap.put("currentSynergyId", Utility.safeString(this.m_currentSynergyId));
    Utility.sendBroadcast("nimble.synergyidmanager.notification.synergy_id_changed", localHashMap);
  }
  
  private void sleep()
  {
    Utility.unregisterReceiver(this.m_receiver);
    saveDataToPersistent();
  }
  
  private void wakeup()
  {
    restoreFromPersistent();
    if (!Utility.validString(this.m_anonymousSynergyId)) {
      setAnonymousSynergyId(SynergyEnvironment.getComponent().getSynergyId());
    }
    if (!Utility.validString(this.m_currentSynergyId)) {
      setCurrentSynergyId(this.m_anonymousSynergyId);
    }
    Utility.registerReceiver("nimble.environment.notification.startup_requests_finished", this.m_receiver);
  }
  
  protected void cleanup()
  {
    sleep();
  }
  
  public String getAnonymousSynergyId()
  {
    if (Utility.validString(this.m_anonymousSynergyId)) {
      return this.m_anonymousSynergyId;
    }
    return SynergyEnvironment.getComponent().getSynergyId();
  }
  
  public String getComponentId()
  {
    return "com.ea.nimble.synergyidmanager";
  }
  
  public String getLogSourceTitle()
  {
    return "SynergyId";
  }
  
  public String getSynergyId()
  {
    if (Utility.validString(this.m_currentSynergyId)) {
      return this.m_currentSynergyId;
    }
    return getAnonymousSynergyId();
  }
  
  public SynergyIdManagerError login(String paramString1, String paramString2)
  {
    if (this.m_authenticatorIdentifier != null) {
      return new SynergyIdManagerError(SynergyIdManagerError.Code.UNEXPECTED_LOGIN_STATE.intValue(), "Already logged in with authenticator, " + this.m_authenticatorIdentifier);
    }
    if ((!Utility.validString(paramString1)) || (!Utility.isOnlyDecimalCharacters(paramString1))) {
      return new SynergyIdManagerError(SynergyIdManagerError.Code.INVALID_ID.intValue(), "Synergy ID must be numeric digits.");
    }
    if (!Utility.validString(paramString2)) {
      return new SynergyIdManagerError(SynergyIdManagerError.Code.MISSING_AUTHENTICATOR.intValue(), "Authenticator string required for login API.");
    }
    this.m_authenticatorIdentifier = paramString2;
    setCurrentSynergyId(paramString1);
    return null;
  }
  
  public SynergyIdManagerError logout(String paramString)
  {
    if (this.m_authenticatorIdentifier == null) {
      return new SynergyIdManagerError(SynergyIdManagerError.Code.UNEXPECTED_LOGIN_STATE.intValue(), "Already logged out.");
    }
    if (!Utility.validString(paramString)) {
      return new SynergyIdManagerError(SynergyIdManagerError.Code.MISSING_AUTHENTICATOR.intValue(), "Authenticator string required for logout API.");
    }
    if (!this.m_authenticatorIdentifier.equals(paramString)) {
      return new SynergyIdManagerError(SynergyIdManagerError.Code.AUTHENTICATOR_CONFLICT.intValue(), "Logout must be performed by the same authenticator that logged in, " + this.m_authenticatorIdentifier);
    }
    setCurrentSynergyId(this.m_anonymousSynergyId);
    this.m_authenticatorIdentifier = null;
    return null;
  }
  
  protected void restore()
  {
    wakeup();
  }
  
  protected void resume()
  {
    wakeup();
  }
  
  protected void suspend()
  {
    sleep();
  }
  
  private class SynergyIdManagerReceiver
    extends BroadcastReceiver
  {
    private SynergyIdManagerReceiver() {}
    
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if ((ApplicationEnvironment.isMainApplicationRunning()) && (ApplicationEnvironment.getCurrentActivity() != null)) {
        SynergyIdManagerImpl.this.onSynergyEnvironmentStartupRequestsFinished();
      }
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ea.nimble.SynergyIdManagerImpl
 * JD-Core Version:    0.7.0.1
 */