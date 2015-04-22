package com.google.android.gms.tagmanager;

import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.common.api.PendingResult;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class TagManager
{
  private static TagManager arN;
  private final DataLayer aod;
  private final r aqu;
  private final a arK;
  private final cx arL;
  private final ConcurrentMap<n, Boolean> arM;
  private final Context mContext;
  
  TagManager(Context paramContext, a parama, DataLayer paramDataLayer, cx paramcx)
  {
    if (paramContext == null) {
      throw new NullPointerException("context cannot be null");
    }
    this.mContext = paramContext.getApplicationContext();
    this.arL = paramcx;
    this.arK = parama;
    this.arM = new ConcurrentHashMap();
    this.aod = paramDataLayer;
    this.aod.a(new DataLayer.b()
    {
      public void D(Map<String, Object> paramAnonymousMap)
      {
        Object localObject = paramAnonymousMap.get("event");
        if (localObject != null) {
          TagManager.a(TagManager.this, localObject.toString());
        }
      }
    });
    this.aod.a(new d(this.mContext));
    this.aqu = new r();
    py();
  }
  
  private void cT(String paramString)
  {
    Iterator localIterator = this.arM.keySet().iterator();
    while (localIterator.hasNext()) {
      ((n)localIterator.next()).cp(paramString);
    }
  }
  
  public static TagManager getInstance(Context paramContext)
  {
    try
    {
      if (arN != null) {
        break label68;
      }
      if (paramContext == null)
      {
        bh.T("TagManager.getInstance requires non-null context.");
        throw new NullPointerException();
      }
    }
    finally {}
    arN = new TagManager(paramContext, new a()new DataLayernew v
    {
      public o a(Context paramAnonymousContext, TagManager paramAnonymousTagManager, Looper paramAnonymousLooper, String paramAnonymousString, int paramAnonymousInt, r paramAnonymousr)
      {
        return new o(paramAnonymousContext, paramAnonymousTagManager, paramAnonymousLooper, paramAnonymousString, paramAnonymousInt, paramAnonymousr);
      }
    }, new DataLayer(new v(paramContext)), cy.pw());
    label68:
    TagManager localTagManager = arN;
    return localTagManager;
  }
  
  private void py()
  {
    if (Build.VERSION.SDK_INT >= 14) {
      this.mContext.registerComponentCallbacks(new ComponentCallbacks2()
      {
        public void onConfigurationChanged(Configuration paramAnonymousConfiguration) {}
        
        public void onLowMemory() {}
        
        public void onTrimMemory(int paramAnonymousInt)
        {
          if (paramAnonymousInt == 20) {
            TagManager.this.dispatch();
          }
        }
      });
    }
  }
  
  void a(n paramn)
  {
    this.arM.put(paramn, Boolean.valueOf(true));
  }
  
  boolean b(n paramn)
  {
    return this.arM.remove(paramn) != null;
  }
  
  public void dispatch()
  {
    this.arL.dispatch();
  }
  
  public DataLayer getDataLayer()
  {
    return this.aod;
  }
  
  boolean i(Uri paramUri)
  {
    for (;;)
    {
      ce localce;
      String str;
      boolean bool;
      try
      {
        localce = ce.oJ();
        if (!localce.i(paramUri)) {
          break label228;
        }
        str = localce.getContainerId();
        int i = 4.arP[localce.oK().ordinal()];
        switch (i)
        {
        default: 
          bool = true;
          return bool;
        }
      }
      finally {}
      Iterator localIterator2 = this.arM.keySet().iterator();
      if (localIterator2.hasNext())
      {
        n localn2 = (n)localIterator2.next();
        if (localn2.getContainerId().equals(str))
        {
          localn2.cr(null);
          localn2.refresh();
        }
      }
      else
      {
        continue;
        Iterator localIterator1 = this.arM.keySet().iterator();
        while (localIterator1.hasNext())
        {
          n localn1 = (n)localIterator1.next();
          if (localn1.getContainerId().equals(str))
          {
            localn1.cr(localce.oL());
            localn1.refresh();
          }
          else if (localn1.nU() != null)
          {
            localn1.cr(null);
            localn1.refresh();
          }
        }
        continue;
        label228:
        bool = false;
      }
    }
  }
  
  public PendingResult<ContainerHolder> loadContainerDefaultOnly(String paramString, int paramInt)
  {
    o localo = this.arK.a(this.mContext, this, null, paramString, paramInt, this.aqu);
    localo.nX();
    return localo;
  }
  
  public PendingResult<ContainerHolder> loadContainerDefaultOnly(String paramString, int paramInt, Handler paramHandler)
  {
    o localo = this.arK.a(this.mContext, this, paramHandler.getLooper(), paramString, paramInt, this.aqu);
    localo.nX();
    return localo;
  }
  
  public PendingResult<ContainerHolder> loadContainerPreferFresh(String paramString, int paramInt)
  {
    o localo = this.arK.a(this.mContext, this, null, paramString, paramInt, this.aqu);
    localo.nZ();
    return localo;
  }
  
  public PendingResult<ContainerHolder> loadContainerPreferFresh(String paramString, int paramInt, Handler paramHandler)
  {
    o localo = this.arK.a(this.mContext, this, paramHandler.getLooper(), paramString, paramInt, this.aqu);
    localo.nZ();
    return localo;
  }
  
  public PendingResult<ContainerHolder> loadContainerPreferNonDefault(String paramString, int paramInt)
  {
    o localo = this.arK.a(this.mContext, this, null, paramString, paramInt, this.aqu);
    localo.nY();
    return localo;
  }
  
  public PendingResult<ContainerHolder> loadContainerPreferNonDefault(String paramString, int paramInt, Handler paramHandler)
  {
    o localo = this.arK.a(this.mContext, this, paramHandler.getLooper(), paramString, paramInt, this.aqu);
    localo.nY();
    return localo;
  }
  
  public void setVerboseLoggingEnabled(boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (int i = 2;; i = 5)
    {
      bh.setLogLevel(i);
      return;
    }
  }
  
  static abstract interface a
  {
    public abstract o a(Context paramContext, TagManager paramTagManager, Looper paramLooper, String paramString, int paramInt, r paramr);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.TagManager
 * JD-Core Version:    0.7.0.1
 */