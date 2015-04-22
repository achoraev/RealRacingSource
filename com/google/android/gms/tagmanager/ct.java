package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.c.i;
import com.google.android.gms.internal.d.a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class ct
{
  private static final bz<d.a> aqS = new bz(di.pK(), true);
  private final DataLayer aod;
  private final cr.c aqT;
  private final ag aqU;
  private final Map<String, aj> aqV;
  private final Map<String, aj> aqW;
  private final Map<String, aj> aqX;
  private final k<cr.a, bz<d.a>> aqY;
  private final k<String, b> aqZ;
  private final Set<cr.e> ara;
  private final Map<String, c> arb;
  private volatile String arc;
  private int ard;
  
  public ct(Context paramContext, cr.c paramc, DataLayer paramDataLayer, s.a parama1, s.a parama2, ag paramag)
  {
    if (paramc == null) {
      throw new NullPointerException("resource cannot be null");
    }
    this.aqT = paramc;
    this.ara = new HashSet(paramc.oY());
    this.aod = paramDataLayer;
    this.aqU = paramag;
    l.a local1 = new l.a()
    {
      public int a(cr.a paramAnonymousa, bz<d.a> paramAnonymousbz)
      {
        return ((d.a)paramAnonymousbz.getObject()).qH();
      }
    };
    this.aqY = new l().a(1048576, local1);
    l.a local2 = new l.a()
    {
      public int a(String paramAnonymousString, ct.b paramAnonymousb)
      {
        return paramAnonymousString.length() + paramAnonymousb.getSize();
      }
    };
    this.aqZ = new l().a(1048576, local2);
    this.aqV = new HashMap();
    b(new i(paramContext));
    b(new s(parama2));
    b(new w(paramDataLayer));
    b(new dj(paramContext, paramDataLayer));
    this.aqW = new HashMap();
    c(new q());
    c(new ad());
    c(new ae());
    c(new al());
    c(new am());
    c(new bd());
    c(new be());
    c(new ci());
    c(new dc());
    this.aqX = new HashMap();
    a(new b(paramContext));
    a(new c(paramContext));
    a(new e(paramContext));
    a(new f(paramContext));
    a(new g(paramContext));
    a(new h(paramContext));
    a(new m());
    a(new p(this.aqT.getVersion()));
    a(new s(parama1));
    a(new u(paramDataLayer));
    a(new z(paramContext));
    a(new aa());
    a(new ac());
    a(new ah(this));
    a(new an());
    a(new ao());
    a(new ax(paramContext));
    a(new az());
    a(new bc());
    a(new bj());
    a(new bl(paramContext));
    a(new ca());
    a(new cc());
    a(new cf());
    a(new ch());
    a(new cj(paramContext));
    a(new cu());
    a(new cv());
    a(new de());
    a(new dk());
    this.arb = new HashMap();
    Iterator localIterator1 = this.ara.iterator();
    while (localIterator1.hasNext())
    {
      cr.e locale = (cr.e)localIterator1.next();
      if (paramag.oq())
      {
        a(locale.pg(), locale.ph(), "add macro");
        a(locale.pl(), locale.pi(), "remove macro");
        a(locale.pe(), locale.pj(), "add tag");
        a(locale.pf(), locale.pk(), "remove tag");
      }
      for (int i = 0; i < locale.pg().size(); i++)
      {
        cr.a locala3 = (cr.a)locale.pg().get(i);
        String str2 = "Unknown";
        if ((paramag.oq()) && (i < locale.ph().size())) {
          str2 = (String)locale.ph().get(i);
        }
        c localc2 = e(this.arb, h(locala3));
        localc2.b(locale);
        localc2.a(locale, locala3);
        localc2.a(locale, str2);
      }
      for (int j = 0; j < locale.pl().size(); j++)
      {
        cr.a locala2 = (cr.a)locale.pl().get(j);
        String str1 = "Unknown";
        if ((paramag.oq()) && (j < locale.pi().size())) {
          str1 = (String)locale.pi().get(j);
        }
        c localc1 = e(this.arb, h(locala2));
        localc1.b(locale);
        localc1.b(locale, locala2);
        localc1.b(locale, str1);
      }
    }
    Iterator localIterator2 = this.aqT.oZ().entrySet().iterator();
    while (localIterator2.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator2.next();
      Iterator localIterator3 = ((List)localEntry.getValue()).iterator();
      while (localIterator3.hasNext())
      {
        cr.a locala1 = (cr.a)localIterator3.next();
        if (!di.n((d.a)locala1.oU().get(com.google.android.gms.internal.b.dG.toString())).booleanValue()) {
          e(this.arb, (String)localEntry.getKey()).i(locala1);
        }
      }
    }
  }
  
  private bz<d.a> a(d.a parama, Set<String> paramSet, dl paramdl)
  {
    if (!parama.gF) {
      return new bz(parama, true);
    }
    switch (parama.type)
    {
    case 5: 
    case 6: 
    default: 
      bh.T("Unknown type: " + parama.type);
      return aqS;
    case 2: 
      d.a locala3 = cr.g(parama);
      locala3.gw = new d.a[parama.gw.length];
      for (int k = 0; k < parama.gw.length; k++)
      {
        bz localbz5 = a(parama.gw[k], paramSet, paramdl.fi(k));
        if (localbz5 == aqS) {
          return aqS;
        }
        locala3.gw[k] = ((d.a)localbz5.getObject());
      }
      return new bz(locala3, false);
    case 3: 
      d.a locala2 = cr.g(parama);
      if (parama.gx.length != parama.gy.length)
      {
        bh.T("Invalid serving value: " + parama.toString());
        return aqS;
      }
      locala2.gx = new d.a[parama.gx.length];
      locala2.gy = new d.a[parama.gx.length];
      for (int j = 0; j < parama.gx.length; j++)
      {
        bz localbz3 = a(parama.gx[j], paramSet, paramdl.fj(j));
        bz localbz4 = a(parama.gy[j], paramSet, paramdl.fk(j));
        if ((localbz3 == aqS) || (localbz4 == aqS)) {
          return aqS;
        }
        locala2.gx[j] = ((d.a)localbz3.getObject());
        locala2.gy[j] = ((d.a)localbz4.getObject());
      }
      return new bz(locala2, false);
    case 4: 
      if (paramSet.contains(parama.gz))
      {
        bh.T("Macro cycle detected.  Current macro reference: " + parama.gz + "." + "  Previous macro references: " + paramSet.toString() + ".");
        return aqS;
      }
      paramSet.add(parama.gz);
      bz localbz2 = dm.a(a(parama.gz, paramSet, paramdl.oF()), parama.gE);
      paramSet.remove(parama.gz);
      return localbz2;
    }
    d.a locala1 = cr.g(parama);
    locala1.gD = new d.a[parama.gD.length];
    for (int i = 0; i < parama.gD.length; i++)
    {
      bz localbz1 = a(parama.gD[i], paramSet, paramdl.fl(i));
      if (localbz1 == aqS) {
        return aqS;
      }
      locala1.gD[i] = ((d.a)localbz1.getObject());
    }
    return new bz(locala1, false);
  }
  
  private bz<d.a> a(String paramString, Set<String> paramSet, bk parambk)
  {
    this.ard = (1 + this.ard);
    b localb = (b)this.aqZ.get(paramString);
    if ((localb != null) && (!this.aqU.oq()))
    {
      a(localb.oV(), paramSet);
      this.ard = (-1 + this.ard);
      return localb.pp();
    }
    c localc = (c)this.arb.get(paramString);
    if (localc == null)
    {
      bh.T(po() + "Invalid macro: " + paramString);
      this.ard = (-1 + this.ard);
      return aqS;
    }
    bz localbz1 = a(paramString, localc.pq(), localc.pr(), localc.ps(), localc.pu(), localc.pt(), paramSet, parambk.oh());
    if (((Set)localbz1.getObject()).isEmpty()) {}
    for (cr.a locala = localc.pv(); locala == null; locala = (cr.a)((Set)localbz1.getObject()).iterator().next())
    {
      this.ard = (-1 + this.ard);
      return aqS;
      if (((Set)localbz1.getObject()).size() > 1) {
        bh.W(po() + "Multiple macros active for macroName " + paramString);
      }
    }
    bz localbz2 = a(this.aqX, locala, paramSet, parambk.ow());
    boolean bool;
    if ((localbz1.oG()) && (localbz2.oG()))
    {
      bool = true;
      if (localbz2 != aqS) {
        break label399;
      }
    }
    label399:
    for (bz localbz3 = aqS;; localbz3 = new bz(localbz2.getObject(), bool))
    {
      d.a locala1 = locala.oV();
      if (localbz3.oG()) {
        this.aqZ.e(paramString, new b(localbz3, locala1));
      }
      a(locala1, paramSet);
      this.ard = (-1 + this.ard);
      return localbz3;
      bool = false;
      break;
    }
  }
  
  private bz<d.a> a(Map<String, aj> paramMap, cr.a parama, Set<String> paramSet, ck paramck)
  {
    boolean bool1 = true;
    d.a locala = (d.a)parama.oU().get(com.google.android.gms.internal.b.cU.toString());
    bz localbz1;
    if (locala == null)
    {
      bh.T("No function id in properties");
      localbz1 = aqS;
    }
    String str;
    aj localaj;
    do
    {
      return localbz1;
      str = locala.gA;
      localaj = (aj)paramMap.get(str);
      if (localaj == null)
      {
        bh.T(str + " has no backing implementation.");
        return aqS;
      }
      localbz1 = (bz)this.aqY.get(parama);
    } while ((localbz1 != null) && (!this.aqU.oq()));
    HashMap localHashMap = new HashMap();
    Iterator localIterator = parama.oU().entrySet().iterator();
    boolean bool2 = bool1;
    if (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      cm localcm = paramck.cH((String)localEntry.getKey());
      bz localbz3 = a((d.a)localEntry.getValue(), paramSet, localcm.e((d.a)localEntry.getValue()));
      if (localbz3 == aqS) {
        return aqS;
      }
      if (localbz3.oG()) {
        parama.a((String)localEntry.getKey(), (d.a)localbz3.getObject());
      }
      for (boolean bool3 = bool2;; bool3 = false)
      {
        localHashMap.put(localEntry.getKey(), localbz3.getObject());
        bool2 = bool3;
        break;
      }
    }
    if (!localaj.a(localHashMap.keySet()))
    {
      bh.T("Incorrect keys for function " + str + " required " + localaj.os() + " had " + localHashMap.keySet());
      return aqS;
    }
    if ((bool2) && (localaj.nN())) {}
    for (;;)
    {
      bz localbz2 = new bz(localaj.C(localHashMap), bool1);
      if (bool1) {
        this.aqY.e(parama, localbz2);
      }
      paramck.d((d.a)localbz2.getObject());
      return localbz2;
      bool1 = false;
    }
  }
  
  private bz<Set<cr.a>> a(Set<cr.e> paramSet, Set<String> paramSet1, a parama, cs paramcs)
  {
    HashSet localHashSet1 = new HashSet();
    HashSet localHashSet2 = new HashSet();
    Iterator localIterator = paramSet.iterator();
    boolean bool1 = true;
    if (localIterator.hasNext())
    {
      cr.e locale = (cr.e)localIterator.next();
      cn localcn = paramcs.oE();
      bz localbz = a(locale, paramSet1, localcn);
      if (((Boolean)localbz.getObject()).booleanValue()) {
        parama.a(locale, localHashSet1, localHashSet2, localcn);
      }
      if ((bool1) && (localbz.oG())) {}
      for (boolean bool2 = true;; bool2 = false)
      {
        bool1 = bool2;
        break;
      }
    }
    localHashSet1.removeAll(localHashSet2);
    paramcs.b(localHashSet1);
    return new bz(localHashSet1, bool1);
  }
  
  private void a(d.a parama, Set<String> paramSet)
  {
    if (parama == null) {}
    for (;;)
    {
      return;
      bz localbz = a(parama, paramSet, new bx());
      if (localbz != aqS)
      {
        Object localObject1 = di.o((d.a)localbz.getObject());
        if ((localObject1 instanceof Map))
        {
          Map localMap2 = (Map)localObject1;
          this.aod.push(localMap2);
          return;
        }
        if (!(localObject1 instanceof List)) {
          break;
        }
        Iterator localIterator = ((List)localObject1).iterator();
        while (localIterator.hasNext())
        {
          Object localObject2 = localIterator.next();
          if ((localObject2 instanceof Map))
          {
            Map localMap1 = (Map)localObject2;
            this.aod.push(localMap1);
          }
          else
          {
            bh.W("pushAfterEvaluate: value not a Map");
          }
        }
      }
    }
    bh.W("pushAfterEvaluate: value not a Map or List");
  }
  
  private static void a(List<cr.a> paramList, List<String> paramList1, String paramString)
  {
    if (paramList.size() != paramList1.size()) {
      bh.U("Invalid resource: imbalance of rule names of functions for " + paramString + " operation. Using default rule name instead");
    }
  }
  
  private static void a(Map<String, aj> paramMap, aj paramaj)
  {
    if (paramMap.containsKey(paramaj.or())) {
      throw new IllegalArgumentException("Duplicate function type name: " + paramaj.or());
    }
    paramMap.put(paramaj.or(), paramaj);
  }
  
  private static c e(Map<String, c> paramMap, String paramString)
  {
    c localc = (c)paramMap.get(paramString);
    if (localc == null)
    {
      localc = new c();
      paramMap.put(paramString, localc);
    }
    return localc;
  }
  
  private static String h(cr.a parama)
  {
    return di.j((d.a)parama.oU().get(com.google.android.gms.internal.b.df.toString()));
  }
  
  private String po()
  {
    if (this.ard <= 1) {
      return "";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(Integer.toString(this.ard));
    for (int i = 2; i < this.ard; i++) {
      localStringBuilder.append(' ');
    }
    localStringBuilder.append(": ");
    return localStringBuilder.toString();
  }
  
  bz<Boolean> a(cr.a parama, Set<String> paramSet, ck paramck)
  {
    bz localbz = a(this.aqW, parama, paramSet, paramck);
    Boolean localBoolean = di.n((d.a)localbz.getObject());
    paramck.d(di.u(localBoolean));
    return new bz(localBoolean, localbz.oG());
  }
  
  bz<Boolean> a(cr.e parame, Set<String> paramSet, cn paramcn)
  {
    Iterator localIterator1 = parame.pd().iterator();
    boolean bool1 = true;
    if (localIterator1.hasNext())
    {
      bz localbz2 = a((cr.a)localIterator1.next(), paramSet, paramcn.oy());
      if (((Boolean)localbz2.getObject()).booleanValue())
      {
        paramcn.f(di.u(Boolean.valueOf(false)));
        return new bz(Boolean.valueOf(false), localbz2.oG());
      }
      if ((bool1) && (localbz2.oG())) {}
      for (boolean bool2 = true;; bool2 = false)
      {
        bool1 = bool2;
        break;
      }
    }
    Iterator localIterator2 = parame.pc().iterator();
    while (localIterator2.hasNext())
    {
      bz localbz1 = a((cr.a)localIterator2.next(), paramSet, paramcn.oz());
      if (!((Boolean)localbz1.getObject()).booleanValue())
      {
        paramcn.f(di.u(Boolean.valueOf(false)));
        return new bz(Boolean.valueOf(false), localbz1.oG());
      }
      if ((bool1) && (localbz1.oG())) {
        bool1 = true;
      } else {
        bool1 = false;
      }
    }
    paramcn.f(di.u(Boolean.valueOf(true)));
    return new bz(Boolean.valueOf(true), bool1);
  }
  
  bz<Set<cr.a>> a(String paramString, Set<cr.e> paramSet, final Map<cr.e, List<cr.a>> paramMap1, final Map<cr.e, List<String>> paramMap2, final Map<cr.e, List<cr.a>> paramMap3, final Map<cr.e, List<String>> paramMap4, Set<String> paramSet1, cs paramcs)
  {
    a(paramSet, paramSet1, new a()
    {
      public void a(cr.e paramAnonymouse, Set<cr.a> paramAnonymousSet1, Set<cr.a> paramAnonymousSet2, cn paramAnonymouscn)
      {
        List localList1 = (List)paramMap1.get(paramAnonymouse);
        List localList2 = (List)paramMap2.get(paramAnonymouse);
        if (localList1 != null)
        {
          paramAnonymousSet1.addAll(localList1);
          paramAnonymouscn.oA().c(localList1, localList2);
        }
        List localList3 = (List)paramMap3.get(paramAnonymouse);
        List localList4 = (List)paramMap4.get(paramAnonymouse);
        if (localList3 != null)
        {
          paramAnonymousSet2.addAll(localList3);
          paramAnonymouscn.oB().c(localList3, localList4);
        }
      }
    }, paramcs);
  }
  
  bz<Set<cr.a>> a(Set<cr.e> paramSet, cs paramcs)
  {
    a(paramSet, new HashSet(), new a()
    {
      public void a(cr.e paramAnonymouse, Set<cr.a> paramAnonymousSet1, Set<cr.a> paramAnonymousSet2, cn paramAnonymouscn)
      {
        paramAnonymousSet1.addAll(paramAnonymouse.pe());
        paramAnonymousSet2.addAll(paramAnonymouse.pf());
        paramAnonymouscn.oC().c(paramAnonymouse.pe(), paramAnonymouse.pj());
        paramAnonymouscn.oD().c(paramAnonymouse.pf(), paramAnonymouse.pk());
      }
    }, paramcs);
  }
  
  void a(aj paramaj)
  {
    a(this.aqX, paramaj);
  }
  
  void b(aj paramaj)
  {
    a(this.aqV, paramaj);
  }
  
  void c(aj paramaj)
  {
    a(this.aqW, paramaj);
  }
  
  public bz<d.a> cR(String paramString)
  {
    this.ard = 0;
    af localaf = this.aqU.cA(paramString);
    bz localbz = a(paramString, new HashSet(), localaf.on());
    localaf.op();
    return localbz;
  }
  
  void cS(String paramString)
  {
    try
    {
      this.arc = paramString;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public void cp(String paramString)
  {
    try
    {
      cS(paramString);
      af localaf = this.aqU.cB(paramString);
      t localt = localaf.oo();
      Iterator localIterator = ((Set)a(this.ara, localt.oh()).getObject()).iterator();
      while (localIterator.hasNext())
      {
        cr.a locala = (cr.a)localIterator.next();
        a(this.aqV, locala, new HashSet(), localt.og());
      }
      localaf.op();
    }
    finally {}
    cS(null);
  }
  
  public void k(List<c.i> paramList)
  {
    for (;;)
    {
      try
      {
        Iterator localIterator = paramList.iterator();
        if (!localIterator.hasNext()) {
          break;
        }
        c.i locali = (c.i)localIterator.next();
        if ((locali.name == null) || (!locali.name.startsWith("gaExperiment:"))) {
          bh.V("Ignored supplemental: " + locali);
        } else {
          ai.a(this.aod, locali);
        }
      }
      finally {}
    }
  }
  
  String pn()
  {
    try
    {
      String str = this.arc;
      return str;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  static abstract interface a
  {
    public abstract void a(cr.e parame, Set<cr.a> paramSet1, Set<cr.a> paramSet2, cn paramcn);
  }
  
  private static class b
  {
    private d.a aqE;
    private bz<d.a> arj;
    
    public b(bz<d.a> parambz, d.a parama)
    {
      this.arj = parambz;
      this.aqE = parama;
    }
    
    public int getSize()
    {
      int i = ((d.a)this.arj.getObject()).qH();
      if (this.aqE == null) {}
      for (int j = 0;; j = this.aqE.qH()) {
        return j + i;
      }
    }
    
    public d.a oV()
    {
      return this.aqE;
    }
    
    public bz<d.a> pp()
    {
      return this.arj;
    }
  }
  
  private static class c
  {
    private final Set<cr.e> ara = new HashSet();
    private final Map<cr.e, List<cr.a>> ark = new HashMap();
    private final Map<cr.e, List<cr.a>> arl = new HashMap();
    private final Map<cr.e, List<String>> arm = new HashMap();
    private final Map<cr.e, List<String>> arn = new HashMap();
    private cr.a aro;
    
    public void a(cr.e parame, cr.a parama)
    {
      Object localObject = (List)this.ark.get(parame);
      if (localObject == null)
      {
        localObject = new ArrayList();
        this.ark.put(parame, localObject);
      }
      ((List)localObject).add(parama);
    }
    
    public void a(cr.e parame, String paramString)
    {
      Object localObject = (List)this.arm.get(parame);
      if (localObject == null)
      {
        localObject = new ArrayList();
        this.arm.put(parame, localObject);
      }
      ((List)localObject).add(paramString);
    }
    
    public void b(cr.e parame)
    {
      this.ara.add(parame);
    }
    
    public void b(cr.e parame, cr.a parama)
    {
      Object localObject = (List)this.arl.get(parame);
      if (localObject == null)
      {
        localObject = new ArrayList();
        this.arl.put(parame, localObject);
      }
      ((List)localObject).add(parama);
    }
    
    public void b(cr.e parame, String paramString)
    {
      Object localObject = (List)this.arn.get(parame);
      if (localObject == null)
      {
        localObject = new ArrayList();
        this.arn.put(parame, localObject);
      }
      ((List)localObject).add(paramString);
    }
    
    public void i(cr.a parama)
    {
      this.aro = parama;
    }
    
    public Set<cr.e> pq()
    {
      return this.ara;
    }
    
    public Map<cr.e, List<cr.a>> pr()
    {
      return this.ark;
    }
    
    public Map<cr.e, List<String>> ps()
    {
      return this.arm;
    }
    
    public Map<cr.e, List<String>> pt()
    {
      return this.arn;
    }
    
    public Map<cr.e, List<cr.a>> pu()
    {
      return this.arl;
    }
    
    public cr.a pv()
    {
      return this.aro;
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.ct
 * JD-Core Version:    0.7.0.1
 */