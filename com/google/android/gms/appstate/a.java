package com.google.android.gms.appstate;

import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;

public final class a
  implements AppState
{
  private final int CO;
  private final String CP;
  private final byte[] CQ;
  private final boolean CR;
  private final String CS;
  private final byte[] CT;
  
  public a(AppState paramAppState)
  {
    this.CO = paramAppState.getKey();
    this.CP = paramAppState.getLocalVersion();
    this.CQ = paramAppState.getLocalData();
    this.CR = paramAppState.hasConflict();
    this.CS = paramAppState.getConflictVersion();
    this.CT = paramAppState.getConflictData();
  }
  
  static int a(AppState paramAppState)
  {
    Object[] arrayOfObject = new Object[6];
    arrayOfObject[0] = Integer.valueOf(paramAppState.getKey());
    arrayOfObject[1] = paramAppState.getLocalVersion();
    arrayOfObject[2] = paramAppState.getLocalData();
    arrayOfObject[3] = Boolean.valueOf(paramAppState.hasConflict());
    arrayOfObject[4] = paramAppState.getConflictVersion();
    arrayOfObject[5] = paramAppState.getConflictData();
    return n.hashCode(arrayOfObject);
  }
  
  static boolean a(AppState paramAppState, Object paramObject)
  {
    boolean bool = true;
    if (!(paramObject instanceof AppState)) {
      bool = false;
    }
    AppState localAppState;
    do
    {
      do
      {
        return bool;
      } while (paramAppState == paramObject);
      localAppState = (AppState)paramObject;
    } while ((n.equal(Integer.valueOf(localAppState.getKey()), Integer.valueOf(paramAppState.getKey()))) && (n.equal(localAppState.getLocalVersion(), paramAppState.getLocalVersion())) && (n.equal(localAppState.getLocalData(), paramAppState.getLocalData())) && (n.equal(Boolean.valueOf(localAppState.hasConflict()), Boolean.valueOf(paramAppState.hasConflict()))) && (n.equal(localAppState.getConflictVersion(), paramAppState.getConflictVersion())) && (n.equal(localAppState.getConflictData(), paramAppState.getConflictData())));
    return false;
  }
  
  static String b(AppState paramAppState)
  {
    return n.h(paramAppState).a("Key", Integer.valueOf(paramAppState.getKey())).a("LocalVersion", paramAppState.getLocalVersion()).a("LocalData", paramAppState.getLocalData()).a("HasConflict", Boolean.valueOf(paramAppState.hasConflict())).a("ConflictVersion", paramAppState.getConflictVersion()).a("ConflictData", paramAppState.getConflictData()).toString();
  }
  
  public boolean equals(Object paramObject)
  {
    return a(this, paramObject);
  }
  
  public AppState fo()
  {
    return this;
  }
  
  public byte[] getConflictData()
  {
    return this.CT;
  }
  
  public String getConflictVersion()
  {
    return this.CS;
  }
  
  public int getKey()
  {
    return this.CO;
  }
  
  public byte[] getLocalData()
  {
    return this.CQ;
  }
  
  public String getLocalVersion()
  {
    return this.CP;
  }
  
  public boolean hasConflict()
  {
    return this.CR;
  }
  
  public int hashCode()
  {
    return a(this);
  }
  
  public boolean isDataValid()
  {
    return true;
  }
  
  public String toString()
  {
    return b(this);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.appstate.a
 * JD-Core Version:    0.7.0.1
 */