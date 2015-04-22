package com.ea.nimble;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class PersistenceServiceImpl
  extends Component
  implements IPersistenceService, LogSource
{
  private Encryptor m_encryptor;
  protected Map<String, Persistence> m_persistences;
  
  private Persistence loadPersistenceById(String paramString, Persistence.Storage paramStorage)
  {
    String str = paramString + "-" + paramStorage.toString();
    Persistence localPersistence1 = (Persistence)this.m_persistences.get(str);
    Persistence localPersistence2;
    if (localPersistence1 != null) {
      localPersistence2 = localPersistence1;
    }
    boolean bool;
    do
    {
      return localPersistence2;
      bool = new File(Persistence.getPersistencePath(paramString, paramStorage)).exists();
      localPersistence2 = null;
    } while (!bool);
    Persistence localPersistence3 = new Persistence(paramString, paramStorage, this.m_encryptor);
    localPersistence3.restore(false, null);
    this.m_persistences.put(str, localPersistence3);
    return localPersistence3;
  }
  
  private void removePersistenceById(String paramString, Persistence.Storage paramStorage)
  {
    String str = paramString + "-" + paramStorage.toString();
    Persistence localPersistence = (Persistence)this.m_persistences.get(str);
    if (localPersistence != null)
    {
      localPersistence.clean();
      this.m_persistences.remove(str);
    }
  }
  
  private void synchronize()
  {
    Iterator localIterator = this.m_persistences.values().iterator();
    while (localIterator.hasNext()) {
      ((Persistence)localIterator.next()).synchronize();
    }
  }
  
  public void cleanPersistenceReference(String paramString, Persistence.Storage paramStorage)
  {
    if (!Utility.validString(paramString))
    {
      Log.Helper.LOGF(this, "Invalid identifier " + paramString + " for persistence", new Object[0]);
      return;
    }
    this.m_persistences.remove(paramString + "-" + paramStorage.toString());
  }
  
  public String getComponentId()
  {
    return "com.ea.nimble.persistence";
  }
  
  public String getLogSourceTitle()
  {
    return "Persistence";
  }
  
  public Persistence getPersistence(String paramString, Persistence.Storage paramStorage)
  {
    Persistence localPersistence1;
    if (!Utility.validString(paramString))
    {
      Log.Helper.LOGF(this, "Invalid identifier " + paramString + " for persistence", new Object[0]);
      localPersistence1 = null;
    }
    do
    {
      return localPersistence1;
      localPersistence1 = loadPersistenceById(paramString, paramStorage);
    } while (localPersistence1 != null);
    Persistence localPersistence2 = new Persistence(paramString, paramStorage, this.m_encryptor);
    this.m_persistences.put(paramString + "-" + paramStorage.toString(), localPersistence2);
    return localPersistence2;
  }
  
  public void migratePersistence(String paramString1, Persistence.Storage paramStorage, String paramString2, PersistenceService.PersistenceMergePolicy paramPersistenceMergePolicy)
  {
    if ((!Utility.validString(paramString1)) || (!Utility.validString(paramString2))) {
      Log.Helper.LOGF(this, "Invalid identifiers " + paramString1 + " or " + paramString2 + " for component persistence", new Object[0]);
    }
    String str;
    Persistence localPersistence1;
    do
    {
      return;
      str = paramString2 + "-" + paramStorage.toString();
      localPersistence1 = loadPersistenceById(paramString1, paramStorage);
      if (localPersistence1 != null) {
        break;
      }
    } while (paramPersistenceMergePolicy != PersistenceService.PersistenceMergePolicy.OVERWRITE);
    this.m_persistences.remove(str);
    new File(Persistence.getPersistencePath(paramString2, paramStorage)).delete();
    return;
    Persistence localPersistence2 = loadPersistenceById(paramString2, paramStorage);
    if (localPersistence2 == null)
    {
      Persistence localPersistence3 = new Persistence(localPersistence1, paramString2);
      this.m_persistences.put(str, localPersistence3);
      localPersistence3.synchronize();
      return;
    }
    localPersistence2.merge(localPersistence1, paramPersistenceMergePolicy);
  }
  
  public void removePersistence(String paramString, Persistence.Storage paramStorage)
  {
    if (!Utility.validString(paramString))
    {
      Log.Helper.LOGF(this, "Invalid identifier " + paramString + " for persistence", new Object[0]);
      return;
    }
    removePersistenceById(paramString, paramStorage);
  }
  
  public void setup()
  {
    this.m_persistences = new HashMap();
    this.m_encryptor = new Encryptor();
  }
  
  public void suspend()
  {
    synchronize();
  }
  
  public void teardown()
  {
    synchronize();
    this.m_persistences = null;
    this.m_encryptor = null;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ea.nimble.PersistenceServiceImpl
 * JD-Core Version:    0.7.0.1
 */