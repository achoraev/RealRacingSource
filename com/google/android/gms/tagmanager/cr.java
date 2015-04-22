package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.b;
import com.google.android.gms.internal.c.b;
import com.google.android.gms.internal.c.e;
import com.google.android.gms.internal.c.f;
import com.google.android.gms.internal.c.g;
import com.google.android.gms.internal.c.h;
import com.google.android.gms.internal.d.a;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class cr
{
  private static d.a a(int paramInt, c.f paramf, d.a[] paramArrayOfa, Set<Integer> paramSet)
    throws cr.g
  {
    int i = 0;
    if (paramSet.contains(Integer.valueOf(paramInt))) {
      cL("Value cycle detected.  Current value reference: " + paramInt + "." + "  Previous value references: " + paramSet + ".");
    }
    d.a locala1 = (d.a)a(paramf.fG, paramInt, "values");
    if (paramArrayOfa[paramInt] != null) {
      return paramArrayOfa[paramInt];
    }
    paramSet.add(Integer.valueOf(paramInt));
    int j = locala1.type;
    d.a locala2 = null;
    switch (j)
    {
    }
    for (;;)
    {
      if (locala2 == null) {
        cL("Invalid value: " + locala1);
      }
      paramArrayOfa[paramInt] = locala2;
      paramSet.remove(Integer.valueOf(paramInt));
      return locala2;
      c.h localh3 = h(locala1);
      locala2 = g(locala1);
      locala2.gw = new d.a[localh3.gh.length];
      int[] arrayOfInt4 = localh3.gh;
      int i11 = arrayOfInt4.length;
      int i14;
      for (int i12 = 0; i < i11; i12 = i14)
      {
        int i13 = arrayOfInt4[i];
        d.a[] arrayOfa4 = locala2.gw;
        i14 = i12 + 1;
        arrayOfa4[i12] = a(i13, paramf, paramArrayOfa, paramSet);
        i++;
      }
      locala2 = g(locala1);
      c.h localh2 = h(locala1);
      if (localh2.gi.length != localh2.gj.length) {
        cL("Uneven map keys (" + localh2.gi.length + ") and map values (" + localh2.gj.length + ")");
      }
      locala2.gx = new d.a[localh2.gi.length];
      locala2.gy = new d.a[localh2.gi.length];
      int[] arrayOfInt2 = localh2.gi;
      int i2 = arrayOfInt2.length;
      int i3 = 0;
      int i10;
      for (int i4 = 0; i3 < i2; i4 = i10)
      {
        int i9 = arrayOfInt2[i3];
        d.a[] arrayOfa3 = locala2.gx;
        i10 = i4 + 1;
        arrayOfa3[i4] = a(i9, paramf, paramArrayOfa, paramSet);
        i3++;
      }
      int[] arrayOfInt3 = localh2.gj;
      int i5 = arrayOfInt3.length;
      int i8;
      for (int i6 = 0; i < i5; i6 = i8)
      {
        int i7 = arrayOfInt3[i];
        d.a[] arrayOfa2 = locala2.gy;
        i8 = i6 + 1;
        arrayOfa2[i6] = a(i7, paramf, paramArrayOfa, paramSet);
        i++;
      }
      locala2 = g(locala1);
      locala2.gz = di.j(a(h(locala1).gm, paramf, paramArrayOfa, paramSet));
      continue;
      locala2 = g(locala1);
      c.h localh1 = h(locala1);
      locala2.gD = new d.a[localh1.gl.length];
      int[] arrayOfInt1 = localh1.gl;
      int k = arrayOfInt1.length;
      int i1;
      for (int m = 0; i < k; m = i1)
      {
        int n = arrayOfInt1[i];
        d.a[] arrayOfa1 = locala2.gD;
        i1 = m + 1;
        arrayOfa1[m] = a(n, paramf, paramArrayOfa, paramSet);
        i++;
      }
      locala2 = locala1;
    }
  }
  
  private static a a(c.b paramb, c.f paramf, d.a[] paramArrayOfa, int paramInt)
    throws cr.g
  {
    b localb = a.oT();
    int[] arrayOfInt = paramb.fq;
    int i = arrayOfInt.length;
    int j = 0;
    if (j < i)
    {
      Integer localInteger = Integer.valueOf(arrayOfInt[j]);
      c.e locale = (c.e)a(paramf.fH, localInteger.intValue(), "properties");
      String str = (String)a(paramf.fF, locale.key, "keys");
      d.a locala = (d.a)a(paramArrayOfa, locale.value, "values");
      if (b.ec.toString().equals(str)) {
        localb.i(locala);
      }
      for (;;)
      {
        j++;
        break;
        localb.b(str, locala);
      }
    }
    return localb.oW();
  }
  
  private static e a(c.g paramg, List<a> paramList1, List<a> paramList2, List<a> paramList3, c.f paramf)
  {
    f localf = e.pb();
    int[] arrayOfInt1 = paramg.fV;
    int i = arrayOfInt1.length;
    for (int j = 0; j < i; j++) {
      localf.b((a)paramList3.get(Integer.valueOf(arrayOfInt1[j]).intValue()));
    }
    int[] arrayOfInt2 = paramg.fW;
    int k = arrayOfInt2.length;
    for (int m = 0; m < k; m++) {
      localf.c((a)paramList3.get(Integer.valueOf(arrayOfInt2[m]).intValue()));
    }
    int[] arrayOfInt3 = paramg.fX;
    int n = arrayOfInt3.length;
    for (int i1 = 0; i1 < n; i1++) {
      localf.d((a)paramList1.get(Integer.valueOf(arrayOfInt3[i1]).intValue()));
    }
    int[] arrayOfInt4 = paramg.fZ;
    int i2 = arrayOfInt4.length;
    for (int i3 = 0; i3 < i2; i3++)
    {
      Integer localInteger4 = Integer.valueOf(arrayOfInt4[i3]);
      localf.cN(paramf.fG[localInteger4.intValue()].gv);
    }
    int[] arrayOfInt5 = paramg.fY;
    int i4 = arrayOfInt5.length;
    for (int i5 = 0; i5 < i4; i5++) {
      localf.e((a)paramList1.get(Integer.valueOf(arrayOfInt5[i5]).intValue()));
    }
    int[] arrayOfInt6 = paramg.ga;
    int i6 = arrayOfInt6.length;
    for (int i7 = 0; i7 < i6; i7++)
    {
      Integer localInteger3 = Integer.valueOf(arrayOfInt6[i7]);
      localf.cO(paramf.fG[localInteger3.intValue()].gv);
    }
    int[] arrayOfInt7 = paramg.gb;
    int i8 = arrayOfInt7.length;
    for (int i9 = 0; i9 < i8; i9++) {
      localf.f((a)paramList2.get(Integer.valueOf(arrayOfInt7[i9]).intValue()));
    }
    int[] arrayOfInt8 = paramg.gd;
    int i10 = arrayOfInt8.length;
    for (int i11 = 0; i11 < i10; i11++)
    {
      Integer localInteger2 = Integer.valueOf(arrayOfInt8[i11]);
      localf.cP(paramf.fG[localInteger2.intValue()].gv);
    }
    int[] arrayOfInt9 = paramg.gc;
    int i12 = arrayOfInt9.length;
    for (int i13 = 0; i13 < i12; i13++) {
      localf.g((a)paramList2.get(Integer.valueOf(arrayOfInt9[i13]).intValue()));
    }
    int[] arrayOfInt10 = paramg.ge;
    int i14 = arrayOfInt10.length;
    for (int i15 = 0; i15 < i14; i15++)
    {
      Integer localInteger1 = Integer.valueOf(arrayOfInt10[i15]);
      localf.cQ(paramf.fG[localInteger1.intValue()].gv);
    }
    return localf.pm();
  }
  
  private static <T> T a(T[] paramArrayOfT, int paramInt, String paramString)
    throws cr.g
  {
    if ((paramInt < 0) || (paramInt >= paramArrayOfT.length)) {
      cL("Index out of bounds detected: " + paramInt + " in " + paramString);
    }
    return paramArrayOfT[paramInt];
  }
  
  public static c b(c.f paramf)
    throws cr.g
  {
    int i = 0;
    d.a[] arrayOfa = new d.a[paramf.fG.length];
    for (int j = 0; j < paramf.fG.length; j++) {
      a(j, paramf, arrayOfa, new HashSet(0));
    }
    d locald = c.oX();
    ArrayList localArrayList1 = new ArrayList();
    for (int k = 0; k < paramf.fJ.length; k++) {
      localArrayList1.add(a(paramf.fJ[k], paramf, arrayOfa, k));
    }
    ArrayList localArrayList2 = new ArrayList();
    for (int m = 0; m < paramf.fK.length; m++) {
      localArrayList2.add(a(paramf.fK[m], paramf, arrayOfa, m));
    }
    ArrayList localArrayList3 = new ArrayList();
    for (int n = 0; n < paramf.fI.length; n++)
    {
      a locala = a(paramf.fI[n], paramf, arrayOfa, n);
      locald.a(locala);
      localArrayList3.add(locala);
    }
    c.g[] arrayOfg = paramf.fL;
    int i1 = arrayOfg.length;
    while (i < i1)
    {
      locald.a(a(arrayOfg[i], localArrayList1, localArrayList3, localArrayList2, paramf));
      i++;
    }
    locald.cM(paramf.version);
    locald.fm(paramf.fT);
    return locald.pa();
  }
  
  public static void b(InputStream paramInputStream, OutputStream paramOutputStream)
    throws IOException
  {
    byte[] arrayOfByte = new byte[1024];
    for (;;)
    {
      int i = paramInputStream.read(arrayOfByte);
      if (i == -1) {
        return;
      }
      paramOutputStream.write(arrayOfByte, 0, i);
    }
  }
  
  private static void cL(String paramString)
    throws cr.g
  {
    bh.T(paramString);
    throw new g(paramString);
  }
  
  public static d.a g(d.a parama)
  {
    d.a locala = new d.a();
    locala.type = parama.type;
    locala.gE = ((int[])parama.gE.clone());
    if (parama.gF) {
      locala.gF = parama.gF;
    }
    return locala;
  }
  
  private static c.h h(d.a parama)
    throws cr.g
  {
    if ((c.h)parama.a(c.h.gf) == null) {
      cL("Expected a ServingValue and didn't get one. Value is: " + parama);
    }
    return (c.h)parama.a(c.h.gf);
  }
  
  public static class a
  {
    private final Map<String, d.a> aqD;
    private final d.a aqE;
    
    private a(Map<String, d.a> paramMap, d.a parama)
    {
      this.aqD = paramMap;
      this.aqE = parama;
    }
    
    public static cr.b oT()
    {
      return new cr.b(null);
    }
    
    public void a(String paramString, d.a parama)
    {
      this.aqD.put(paramString, parama);
    }
    
    public Map<String, d.a> oU()
    {
      return Collections.unmodifiableMap(this.aqD);
    }
    
    public d.a oV()
    {
      return this.aqE;
    }
    
    public String toString()
    {
      return "Properties: " + oU() + " pushAfterEvaluate: " + this.aqE;
    }
  }
  
  public static class b
  {
    private final Map<String, d.a> aqD = new HashMap();
    private d.a aqE;
    
    public b b(String paramString, d.a parama)
    {
      this.aqD.put(paramString, parama);
      return this;
    }
    
    public b i(d.a parama)
    {
      this.aqE = parama;
      return this;
    }
    
    public cr.a oW()
    {
      return new cr.a(this.aqD, this.aqE, null);
    }
  }
  
  public static class c
  {
    private final String Sx;
    private final List<cr.e> aqF;
    private final Map<String, List<cr.a>> aqG;
    private final int aqH;
    
    private c(List<cr.e> paramList, Map<String, List<cr.a>> paramMap, String paramString, int paramInt)
    {
      this.aqF = Collections.unmodifiableList(paramList);
      this.aqG = Collections.unmodifiableMap(paramMap);
      this.Sx = paramString;
      this.aqH = paramInt;
    }
    
    public static cr.d oX()
    {
      return new cr.d(null);
    }
    
    public String getVersion()
    {
      return this.Sx;
    }
    
    public List<cr.e> oY()
    {
      return this.aqF;
    }
    
    public Map<String, List<cr.a>> oZ()
    {
      return this.aqG;
    }
    
    public String toString()
    {
      return "Rules: " + oY() + "  Macros: " + this.aqG;
    }
  }
  
  public static class d
  {
    private String Sx = "";
    private final List<cr.e> aqF = new ArrayList();
    private final Map<String, List<cr.a>> aqG = new HashMap();
    private int aqH = 0;
    
    public d a(cr.a parama)
    {
      String str = di.j((d.a)parama.oU().get(b.df.toString()));
      Object localObject = (List)this.aqG.get(str);
      if (localObject == null)
      {
        localObject = new ArrayList();
        this.aqG.put(str, localObject);
      }
      ((List)localObject).add(parama);
      return this;
    }
    
    public d a(cr.e parame)
    {
      this.aqF.add(parame);
      return this;
    }
    
    public d cM(String paramString)
    {
      this.Sx = paramString;
      return this;
    }
    
    public d fm(int paramInt)
    {
      this.aqH = paramInt;
      return this;
    }
    
    public cr.c pa()
    {
      return new cr.c(this.aqF, this.aqG, this.Sx, this.aqH, null);
    }
  }
  
  public static class e
  {
    private final List<cr.a> aqI;
    private final List<cr.a> aqJ;
    private final List<cr.a> aqK;
    private final List<cr.a> aqL;
    private final List<cr.a> aqM;
    private final List<cr.a> aqN;
    private final List<String> aqO;
    private final List<String> aqP;
    private final List<String> aqQ;
    private final List<String> aqR;
    
    private e(List<cr.a> paramList1, List<cr.a> paramList2, List<cr.a> paramList3, List<cr.a> paramList4, List<cr.a> paramList5, List<cr.a> paramList6, List<String> paramList7, List<String> paramList8, List<String> paramList9, List<String> paramList10)
    {
      this.aqI = Collections.unmodifiableList(paramList1);
      this.aqJ = Collections.unmodifiableList(paramList2);
      this.aqK = Collections.unmodifiableList(paramList3);
      this.aqL = Collections.unmodifiableList(paramList4);
      this.aqM = Collections.unmodifiableList(paramList5);
      this.aqN = Collections.unmodifiableList(paramList6);
      this.aqO = Collections.unmodifiableList(paramList7);
      this.aqP = Collections.unmodifiableList(paramList8);
      this.aqQ = Collections.unmodifiableList(paramList9);
      this.aqR = Collections.unmodifiableList(paramList10);
    }
    
    public static cr.f pb()
    {
      return new cr.f(null);
    }
    
    public List<cr.a> pc()
    {
      return this.aqI;
    }
    
    public List<cr.a> pd()
    {
      return this.aqJ;
    }
    
    public List<cr.a> pe()
    {
      return this.aqK;
    }
    
    public List<cr.a> pf()
    {
      return this.aqL;
    }
    
    public List<cr.a> pg()
    {
      return this.aqM;
    }
    
    public List<String> ph()
    {
      return this.aqO;
    }
    
    public List<String> pi()
    {
      return this.aqP;
    }
    
    public List<String> pj()
    {
      return this.aqQ;
    }
    
    public List<String> pk()
    {
      return this.aqR;
    }
    
    public List<cr.a> pl()
    {
      return this.aqN;
    }
    
    public String toString()
    {
      return "Positive predicates: " + pc() + "  Negative predicates: " + pd() + "  Add tags: " + pe() + "  Remove tags: " + pf() + "  Add macros: " + pg() + "  Remove macros: " + pl();
    }
  }
  
  public static class f
  {
    private final List<cr.a> aqI = new ArrayList();
    private final List<cr.a> aqJ = new ArrayList();
    private final List<cr.a> aqK = new ArrayList();
    private final List<cr.a> aqL = new ArrayList();
    private final List<cr.a> aqM = new ArrayList();
    private final List<cr.a> aqN = new ArrayList();
    private final List<String> aqO = new ArrayList();
    private final List<String> aqP = new ArrayList();
    private final List<String> aqQ = new ArrayList();
    private final List<String> aqR = new ArrayList();
    
    public f b(cr.a parama)
    {
      this.aqI.add(parama);
      return this;
    }
    
    public f c(cr.a parama)
    {
      this.aqJ.add(parama);
      return this;
    }
    
    public f cN(String paramString)
    {
      this.aqQ.add(paramString);
      return this;
    }
    
    public f cO(String paramString)
    {
      this.aqR.add(paramString);
      return this;
    }
    
    public f cP(String paramString)
    {
      this.aqO.add(paramString);
      return this;
    }
    
    public f cQ(String paramString)
    {
      this.aqP.add(paramString);
      return this;
    }
    
    public f d(cr.a parama)
    {
      this.aqK.add(parama);
      return this;
    }
    
    public f e(cr.a parama)
    {
      this.aqL.add(parama);
      return this;
    }
    
    public f f(cr.a parama)
    {
      this.aqM.add(parama);
      return this;
    }
    
    public f g(cr.a parama)
    {
      this.aqN.add(parama);
      return this;
    }
    
    public cr.e pm()
    {
      return new cr.e(this.aqI, this.aqJ, this.aqK, this.aqL, this.aqM, this.aqN, this.aqO, this.aqP, this.aqQ, this.aqR, null);
    }
  }
  
  public static class g
    extends Exception
  {
    public g(String paramString)
    {
      super();
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.cr
 * JD-Core Version:    0.7.0.1
 */