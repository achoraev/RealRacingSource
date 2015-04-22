package com.google.ads.a;

import com.google.android.gms.internal.df;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

@Deprecated
public abstract class g
{
  protected void a() {}
  
  public void a(Map<String, String> paramMap)
    throws g.a
  {
    HashMap localHashMap = new HashMap();
    for (Field localField3 : getClass().getFields())
    {
      b localb = (b)localField3.getAnnotation(b.class);
      if (localb != null) {
        localHashMap.put(localb.a(), localField3);
      }
    }
    if (localHashMap.isEmpty()) {
      df.w("No server options fields detected. To suppress this message either add a field with the @Parameter annotation, or override the load() method.");
    }
    Iterator localIterator1 = paramMap.entrySet().iterator();
    while (localIterator1.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator1.next();
      Field localField2 = (Field)localHashMap.remove(localEntry.getKey());
      if (localField2 != null) {
        try
        {
          localField2.set(this, localEntry.getValue());
        }
        catch (IllegalAccessException localIllegalAccessException)
        {
          df.w("Server option \"" + (String)localEntry.getKey() + "\" could not be set: Illegal Access");
        }
        catch (IllegalArgumentException localIllegalArgumentException)
        {
          df.w("Server option \"" + (String)localEntry.getKey() + "\" could not be set: Bad Type");
        }
      } else {
        df.d("Unexpected server option: " + (String)localEntry.getKey() + " = \"" + (String)localEntry.getValue() + "\"");
      }
    }
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator2 = localHashMap.values().iterator();
    while (localIterator2.hasNext())
    {
      Field localField1 = (Field)localIterator2.next();
      if (((b)localField1.getAnnotation(b.class)).b())
      {
        df.w("Required server option missing: " + ((b)localField1.getAnnotation(b.class)).a());
        if (localStringBuilder.length() > 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append(((b)localField1.getAnnotation(b.class)).a());
      }
    }
    if (localStringBuilder.length() > 0) {
      throw new a("Required server option(s) missing: " + localStringBuilder.toString());
    }
    a();
  }
  
  public static final class a
    extends Exception
  {
    public a(String paramString)
    {
      super();
    }
  }
  
  @Retention(RetentionPolicy.RUNTIME)
  @Target({java.lang.annotation.ElementType.FIELD})
  protected static @interface b
  {
    String a();
    
    boolean b() default true;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.a.g
 * JD-Core Version:    0.7.0.1
 */