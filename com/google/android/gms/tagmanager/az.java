package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d.a;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

class az
  extends aj
{
  private static final String ID = a.ae.toString();
  private static final String apA = b.di.toString();
  private static final String apB = b.dm.toString();
  private static final String apC = b.cH.toString();
  private static final String apf = b.bw.toString();
  
  public az()
  {
    super(str, arrayOfString);
  }
  
  private String a(String paramString, a parama, Set<Character> paramSet)
  {
    switch (1.apD[parama.ordinal()])
    {
    default: 
      return paramString;
    case 1: 
      try
      {
        String str4 = dm.de(paramString);
        return str4;
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        bh.b("Joiner: unsupported encoding", localUnsupportedEncodingException);
        return paramString;
      }
    }
    String str1 = paramString.replace("\\", "\\\\");
    Iterator localIterator = paramSet.iterator();
    String str3;
    for (String str2 = str1; localIterator.hasNext(); str2 = str2.replace(str3, "\\" + str3)) {
      str3 = ((Character)localIterator.next()).toString();
    }
    return str2;
  }
  
  private void a(StringBuilder paramStringBuilder, String paramString, a parama, Set<Character> paramSet)
  {
    paramStringBuilder.append(a(paramString, parama, paramSet));
  }
  
  private void a(Set<Character> paramSet, String paramString)
  {
    for (int i = 0; i < paramString.length(); i++) {
      paramSet.add(Character.valueOf(paramString.charAt(i)));
    }
  }
  
  public d.a C(Map<String, d.a> paramMap)
  {
    d.a locala1 = (d.a)paramMap.get(apf);
    if (locala1 == null) {
      return di.pK();
    }
    d.a locala2 = (d.a)paramMap.get(apA);
    String str1;
    String str2;
    label70:
    a locala4;
    String str5;
    a locala6;
    Object localObject;
    if (locala2 != null)
    {
      str1 = di.j(locala2);
      d.a locala3 = (d.a)paramMap.get(apB);
      if (locala3 == null) {
        break label186;
      }
      str2 = di.j(locala3);
      locala4 = a.apE;
      d.a locala5 = (d.a)paramMap.get(apC);
      if (locala5 == null) {
        break label432;
      }
      str5 = di.j(locala5);
      if (!"url".equals(str5)) {
        break label193;
      }
      locala6 = a.apF;
      localObject = null;
    }
    for (;;)
    {
      label119:
      StringBuilder localStringBuilder = new StringBuilder();
      switch (locala1.type)
      {
      default: 
        a(localStringBuilder, di.j(locala1), locala6, (Set)localObject);
      }
      for (;;)
      {
        return di.u(localStringBuilder.toString());
        str1 = "";
        break;
        label186:
        str2 = "=";
        break label70;
        label193:
        if ("backslash".equals(str5))
        {
          locala6 = a.apG;
          localObject = new HashSet();
          a((Set)localObject, str1);
          a((Set)localObject, str2);
          ((Set)localObject).remove(Character.valueOf('\\'));
          break label119;
        }
        bh.T("Joiner: unsupported escape type: " + str5);
        return di.pK();
        int j = 1;
        d.a[] arrayOfa = locala1.gw;
        int k = arrayOfa.length;
        int m = 0;
        while (m < k)
        {
          d.a locala7 = arrayOfa[m];
          if (j == 0) {
            localStringBuilder.append(str1);
          }
          a(localStringBuilder, di.j(locala7), locala6, (Set)localObject);
          m++;
          j = 0;
        }
        for (int i = 0; i < locala1.gx.length; i++)
        {
          if (i > 0) {
            localStringBuilder.append(str1);
          }
          String str3 = di.j(locala1.gx[i]);
          String str4 = di.j(locala1.gy[i]);
          a(localStringBuilder, str3, locala6, (Set)localObject);
          localStringBuilder.append(str2);
          a(localStringBuilder, str4, locala6, (Set)localObject);
        }
      }
      label432:
      locala6 = locala4;
      localObject = null;
    }
  }
  
  public boolean nN()
  {
    return true;
  }
  
  private static enum a
  {
    static
    {
      a[] arrayOfa = new a[3];
      arrayOfa[0] = apE;
      arrayOfa[1] = apF;
      arrayOfa[2] = apG;
      apH = arrayOfa;
    }
    
    private a() {}
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.az
 * JD-Core Version:    0.7.0.1
 */