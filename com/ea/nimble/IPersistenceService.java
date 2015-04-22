package com.ea.nimble;

public abstract interface IPersistenceService
{
  public abstract void cleanPersistenceReference(String paramString, Persistence.Storage paramStorage);
  
  public abstract Persistence getPersistence(String paramString, Persistence.Storage paramStorage);
  
  public abstract void migratePersistence(String paramString1, Persistence.Storage paramStorage, String paramString2, PersistenceService.PersistenceMergePolicy paramPersistenceMergePolicy);
  
  public abstract void removePersistence(String paramString, Persistence.Storage paramStorage);
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ea.nimble.IPersistenceService
 * JD-Core Version:    0.7.0.1
 */