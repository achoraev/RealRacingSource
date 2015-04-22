package com.google.android.gms.drive.query;

import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.b;
import com.google.android.gms.drive.query.internal.Operator;
import com.google.android.gms.drive.query.internal.f;
import java.util.Iterator;
import java.util.List;

public class c
  implements f<String>
{
  public <T> String a(b<T> paramb, T paramT)
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = paramb.getName();
    arrayOfObject[1] = paramT;
    return String.format("contains(%s,%s)", arrayOfObject);
  }
  
  public <T> String a(Operator paramOperator, MetadataField<T> paramMetadataField, T paramT)
  {
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = paramOperator.getTag();
    arrayOfObject[1] = paramMetadataField.getName();
    arrayOfObject[2] = paramT;
    return String.format("cmp(%s,%s,%s)", arrayOfObject);
  }
  
  public String a(Operator paramOperator, List<String> paramList)
  {
    StringBuilder localStringBuilder = new StringBuilder(paramOperator.getTag() + "(");
    Iterator localIterator = paramList.iterator();
    for (String str1 = ""; localIterator.hasNext(); str1 = ",")
    {
      String str2 = (String)localIterator.next();
      localStringBuilder.append(str1);
      localStringBuilder.append(str2);
    }
    return ")";
  }
  
  public String bn(String paramString)
  {
    return String.format("not(%s)", new Object[] { paramString });
  }
  
  public String c(MetadataField<?> paramMetadataField)
  {
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = paramMetadataField.getName();
    return String.format("fieldOnly(%s)", arrayOfObject);
  }
  
  public <T> String c(MetadataField<T> paramMetadataField, T paramT)
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = paramMetadataField.getName();
    arrayOfObject[1] = paramT;
    return String.format("has(%s,%s)", arrayOfObject);
  }
  
  public String ir()
  {
    return "all()";
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.query.c
 * JD-Core Version:    0.7.0.1
 */