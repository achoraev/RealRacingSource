package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.c.f;
import com.google.android.gms.internal.c.i;
import com.google.android.gms.internal.c.j;
import com.google.android.gms.internal.d.a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Container
{
  private final String aoc;
  private final DataLayer aod;
  private ct aoe;
  private Map<String, FunctionCallMacroCallback> aof = new HashMap();
  private Map<String, FunctionCallTagCallback> aog = new HashMap();
  private volatile long aoh;
  private volatile String aoi = "";
  private final Context mContext;
  
  Container(Context paramContext, DataLayer paramDataLayer, String paramString, long paramLong, c.j paramj)
  {
    this.mContext = paramContext;
    this.aod = paramDataLayer;
    this.aoc = paramString;
    this.aoh = paramLong;
    a(paramj.gs);
    if (paramj.gr != null) {
      a(paramj.gr);
    }
  }
  
  Container(Context paramContext, DataLayer paramDataLayer, String paramString, long paramLong, cr.c paramc)
  {
    this.mContext = paramContext;
    this.aod = paramDataLayer;
    this.aoc = paramString;
    this.aoh = paramLong;
    a(paramc);
  }
  
  private void a(c.f paramf)
  {
    if (paramf == null) {
      throw new NullPointerException();
    }
    try
    {
      cr.c localc = cr.b(paramf);
      a(localc);
      return;
    }
    catch (cr.g localg)
    {
      bh.T("Not loading resource: " + paramf + " because it is invalid: " + localg.toString());
    }
  }
  
  private void a(cr.c paramc)
  {
    this.aoi = paramc.getVersion();
    ag localag = cq(this.aoi);
    a(new ct(this.mContext, paramc, this.aod, new a(null), new b(null), localag));
  }
  
  private void a(ct paramct)
  {
    try
    {
      this.aoe = paramct;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  private void a(c.i[] paramArrayOfi)
  {
    ArrayList localArrayList = new ArrayList();
    int i = paramArrayOfi.length;
    for (int j = 0; j < i; j++) {
      localArrayList.add(paramArrayOfi[j]);
    }
    nT().k(localArrayList);
  }
  
  private ct nT()
  {
    try
    {
      ct localct = this.aoe;
      return localct;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  FunctionCallMacroCallback cn(String paramString)
  {
    synchronized (this.aof)
    {
      FunctionCallMacroCallback localFunctionCallMacroCallback = (FunctionCallMacroCallback)this.aof.get(paramString);
      return localFunctionCallMacroCallback;
    }
  }
  
  FunctionCallTagCallback co(String paramString)
  {
    synchronized (this.aog)
    {
      FunctionCallTagCallback localFunctionCallTagCallback = (FunctionCallTagCallback)this.aog.get(paramString);
      return localFunctionCallTagCallback;
    }
  }
  
  void cp(String paramString)
  {
    nT().cp(paramString);
  }
  
  ag cq(String paramString)
  {
    if (ce.oJ().oK().equals(ce.a.aqj)) {}
    return new br();
  }
  
  public boolean getBoolean(String paramString)
  {
    ct localct = nT();
    if (localct == null)
    {
      bh.T("getBoolean called for closed container.");
      return di.pH().booleanValue();
    }
    try
    {
      boolean bool = di.n((d.a)localct.cR(paramString).getObject()).booleanValue();
      return bool;
    }
    catch (Exception localException)
    {
      bh.T("Calling getBoolean() threw an exception: " + localException.getMessage() + " Returning default value.");
    }
    return di.pH().booleanValue();
  }
  
  public String getContainerId()
  {
    return this.aoc;
  }
  
  public double getDouble(String paramString)
  {
    ct localct = nT();
    if (localct == null)
    {
      bh.T("getDouble called for closed container.");
      return di.pG().doubleValue();
    }
    try
    {
      double d = di.m((d.a)localct.cR(paramString).getObject()).doubleValue();
      return d;
    }
    catch (Exception localException)
    {
      bh.T("Calling getDouble() threw an exception: " + localException.getMessage() + " Returning default value.");
    }
    return di.pG().doubleValue();
  }
  
  public long getLastRefreshTime()
  {
    return this.aoh;
  }
  
  public long getLong(String paramString)
  {
    ct localct = nT();
    if (localct == null)
    {
      bh.T("getLong called for closed container.");
      return di.pF().longValue();
    }
    try
    {
      long l = di.l((d.a)localct.cR(paramString).getObject()).longValue();
      return l;
    }
    catch (Exception localException)
    {
      bh.T("Calling getLong() threw an exception: " + localException.getMessage() + " Returning default value.");
    }
    return di.pF().longValue();
  }
  
  public String getString(String paramString)
  {
    ct localct = nT();
    if (localct == null)
    {
      bh.T("getString called for closed container.");
      return di.pJ();
    }
    try
    {
      String str = di.j((d.a)localct.cR(paramString).getObject());
      return str;
    }
    catch (Exception localException)
    {
      bh.T("Calling getString() threw an exception: " + localException.getMessage() + " Returning default value.");
    }
    return di.pJ();
  }
  
  public boolean isDefault()
  {
    return getLastRefreshTime() == 0L;
  }
  
  String nS()
  {
    return this.aoi;
  }
  
  public void registerFunctionCallMacroCallback(String paramString, FunctionCallMacroCallback paramFunctionCallMacroCallback)
  {
    if (paramFunctionCallMacroCallback == null) {
      throw new NullPointerException("Macro handler must be non-null");
    }
    synchronized (this.aof)
    {
      this.aof.put(paramString, paramFunctionCallMacroCallback);
      return;
    }
  }
  
  public void registerFunctionCallTagCallback(String paramString, FunctionCallTagCallback paramFunctionCallTagCallback)
  {
    if (paramFunctionCallTagCallback == null) {
      throw new NullPointerException("Tag callback must be non-null");
    }
    synchronized (this.aog)
    {
      this.aog.put(paramString, paramFunctionCallTagCallback);
      return;
    }
  }
  
  void release()
  {
    this.aoe = null;
  }
  
  public void unregisterFunctionCallMacroCallback(String paramString)
  {
    synchronized (this.aof)
    {
      this.aof.remove(paramString);
      return;
    }
  }
  
  public void unregisterFunctionCallTagCallback(String paramString)
  {
    synchronized (this.aog)
    {
      this.aog.remove(paramString);
      return;
    }
  }
  
  public static abstract interface FunctionCallMacroCallback
  {
    public abstract Object getValue(String paramString, Map<String, Object> paramMap);
  }
  
  public static abstract interface FunctionCallTagCallback
  {
    public abstract void execute(String paramString, Map<String, Object> paramMap);
  }
  
  private class a
    implements s.a
  {
    private a() {}
    
    public Object b(String paramString, Map<String, Object> paramMap)
    {
      Container.FunctionCallMacroCallback localFunctionCallMacroCallback = Container.this.cn(paramString);
      if (localFunctionCallMacroCallback == null) {
        return null;
      }
      return localFunctionCallMacroCallback.getValue(paramString, paramMap);
    }
  }
  
  private class b
    implements s.a
  {
    private b() {}
    
    public Object b(String paramString, Map<String, Object> paramMap)
    {
      Container.FunctionCallTagCallback localFunctionCallTagCallback = Container.this.co(paramString);
      if (localFunctionCallTagCallback != null) {
        localFunctionCallTagCallback.execute(paramString, paramMap);
      }
      return di.pJ();
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.Container
 * JD-Core Version:    0.7.0.1
 */