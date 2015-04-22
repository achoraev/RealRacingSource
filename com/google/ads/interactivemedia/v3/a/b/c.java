package com.google.ads.interactivemedia.v3.a.b;

import com.google.ads.interactivemedia.v3.a.c.a;
import com.google.ads.interactivemedia.v3.a.m;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public final class c
{
  private final Map<Type, com.google.ads.interactivemedia.v3.a.h<?>> a;
  
  public c(Map<Type, com.google.ads.interactivemedia.v3.a.h<?>> paramMap)
  {
    this.a = paramMap;
  }
  
  private <T> h<T> a(Class<? super T> paramClass)
  {
    try
    {
      final Constructor localConstructor = paramClass.getDeclaredConstructor(new Class[0]);
      if (!localConstructor.isAccessible()) {
        localConstructor.setAccessible(true);
      }
      h local6 = new h()
      {
        public T a()
        {
          try
          {
            Object localObject = localConstructor.newInstance(null);
            return localObject;
          }
          catch (InstantiationException localInstantiationException)
          {
            throw new RuntimeException("Failed to invoke " + localConstructor + " with no args", localInstantiationException);
          }
          catch (InvocationTargetException localInvocationTargetException)
          {
            throw new RuntimeException("Failed to invoke " + localConstructor + " with no args", localInvocationTargetException.getTargetException());
          }
          catch (IllegalAccessException localIllegalAccessException)
          {
            throw new AssertionError(localIllegalAccessException);
          }
        }
      };
      return local6;
    }
    catch (NoSuchMethodException localNoSuchMethodException) {}
    return null;
  }
  
  private <T> h<T> a(final Type paramType, Class<? super T> paramClass)
  {
    if (Collection.class.isAssignableFrom(paramClass))
    {
      if (SortedSet.class.isAssignableFrom(paramClass)) {
        new h()
        {
          public T a()
          {
            return new TreeSet();
          }
        };
      }
      if (EnumSet.class.isAssignableFrom(paramClass)) {
        new h()
        {
          public T a()
          {
            if ((paramType instanceof ParameterizedType))
            {
              Type localType = ((ParameterizedType)paramType).getActualTypeArguments()[0];
              if ((localType instanceof Class)) {
                return EnumSet.noneOf((Class)localType);
              }
              throw new m("Invalid EnumSet type: " + paramType.toString());
            }
            throw new m("Invalid EnumSet type: " + paramType.toString());
          }
        };
      }
      if (Set.class.isAssignableFrom(paramClass)) {
        new h()
        {
          public T a()
          {
            return new LinkedHashSet();
          }
        };
      }
      if (Queue.class.isAssignableFrom(paramClass)) {
        new h()
        {
          public T a()
          {
            return new LinkedList();
          }
        };
      }
      new h()
      {
        public T a()
        {
          return new ArrayList();
        }
      };
    }
    if (Map.class.isAssignableFrom(paramClass))
    {
      if (SortedMap.class.isAssignableFrom(paramClass)) {
        new h()
        {
          public T a()
          {
            return new TreeMap();
          }
        };
      }
      if (((paramType instanceof ParameterizedType)) && (!String.class.isAssignableFrom(a.a(((ParameterizedType)paramType).getActualTypeArguments()[0]).a()))) {
        new h()
        {
          public T a()
          {
            return new LinkedHashMap();
          }
        };
      }
      new h()
      {
        public T a()
        {
          return new g();
        }
      };
    }
    return null;
  }
  
  private <T> h<T> b(final Type paramType, final Class<? super T> paramClass)
  {
    new h()
    {
      private final k d = k.a();
      
      public T a()
      {
        try
        {
          Object localObject = this.d.a(paramClass);
          return localObject;
        }
        catch (Exception localException)
        {
          throw new RuntimeException("Unable to invoke no-args constructor for " + paramType + ". " + "Register an InstanceCreator with Gson for this type may fix this problem.", localException);
        }
      }
    };
  }
  
  public <T> h<T> a(a<T> parama)
  {
    final Type localType = parama.b();
    Class localClass = parama.a();
    final com.google.ads.interactivemedia.v3.a.h localh1 = (com.google.ads.interactivemedia.v3.a.h)this.a.get(localType);
    Object localObject;
    if (localh1 != null) {
      localObject = new h()
      {
        public T a()
        {
          return localh1.a(localType);
        }
      };
    }
    do
    {
      do
      {
        return localObject;
        final com.google.ads.interactivemedia.v3.a.h localh2 = (com.google.ads.interactivemedia.v3.a.h)this.a.get(localClass);
        if (localh2 != null) {
          new h()
          {
            public T a()
            {
              return localh2.a(localType);
            }
          };
        }
        localObject = a(localClass);
      } while (localObject != null);
      localObject = a(localType, localClass);
    } while (localObject != null);
    return b(localType, localClass);
  }
  
  public String toString()
  {
    return this.a.toString();
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.a.b.c
 * JD-Core Version:    0.7.0.1
 */