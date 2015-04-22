package com.google.ads.interactivemedia.v3.a.c;

import com.google.ads.interactivemedia.v3.a.b.b;
import java.lang.reflect.Type;

public class a<T>
{
  final Class<? super T> a;
  final Type b;
  final int c;
  
  protected a()
  {
    this.b = a(getClass());
    this.a = b.e(this.b);
    this.c = this.b.hashCode();
  }
  
  a(Type paramType)
  {
    this.b = b.d((Type)com.google.ads.interactivemedia.v3.a.b.a.a(paramType));
    this.a = b.e(this.b);
    this.c = this.b.hashCode();
  }
  
  public static a<?> a(Type paramType)
  {
    return new a(paramType);
  }
  
  static Type a(Class<?> paramClass)
  {
    Type localType = paramClass.getGenericSuperclass();
    if ((localType instanceof Class)) {
      throw new RuntimeException("Missing type parameter.");
    }
    return b.d(((java.lang.reflect.ParameterizedType)localType).getActualTypeArguments()[0]);
  }
  
  public static <T> a<T> b(Class<T> paramClass)
  {
    return new a(paramClass);
  }
  
  public final Class<? super T> a()
  {
    return this.a;
  }
  
  public final Type b()
  {
    return this.b;
  }
  
  public final boolean equals(Object paramObject)
  {
    return ((paramObject instanceof a)) && (b.a(this.b, ((a)paramObject).b));
  }
  
  public final int hashCode()
  {
    return this.c;
  }
  
  public final String toString()
  {
    return b.f(this.b);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.a.c.a
 * JD-Core Version:    0.7.0.1
 */