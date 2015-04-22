package com.google.ads.interactivemedia.v3.a.b;

import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public abstract class k
{
  public static k a()
  {
    try
    {
      Class localClass = Class.forName("sun.misc.Unsafe");
      Field localField = localClass.getDeclaredField("theUnsafe");
      localField.setAccessible(true);
      final Object localObject = localField.get(null);
      k local1 = new k()
      {
        public <T> T a(Class<T> paramAnonymousClass)
          throws Exception
        {
          return this.a.invoke(localObject, new Object[] { paramAnonymousClass });
        }
      };
      return local1;
    }
    catch (Exception localException1)
    {
      try
      {
        Method localMethod3 = ObjectInputStream.class.getDeclaredMethod("newInstance", new Class[] { Class.class, Class.class });
        localMethod3.setAccessible(true);
        k local2 = new k()
        {
          public <T> T a(Class<T> paramAnonymousClass)
            throws Exception
          {
            return this.a.invoke(null, new Object[] { paramAnonymousClass, Object.class });
          }
        };
        return local2;
      }
      catch (Exception localException2)
      {
        try
        {
          Method localMethod1 = ObjectStreamClass.class.getDeclaredMethod("getConstructorId", new Class[] { Class.class });
          localMethod1.setAccessible(true);
          final int i = ((Integer)localMethod1.invoke(null, new Object[] { Object.class })).intValue();
          Class[] arrayOfClass = new Class[2];
          arrayOfClass[0] = Class.class;
          arrayOfClass[1] = Integer.TYPE;
          Method localMethod2 = ObjectStreamClass.class.getDeclaredMethod("newInstance", arrayOfClass);
          localMethod2.setAccessible(true);
          k local3 = new k()
          {
            public <T> T a(Class<T> paramAnonymousClass)
              throws Exception
            {
              Method localMethod = this.a;
              Object[] arrayOfObject = new Object[2];
              arrayOfObject[0] = paramAnonymousClass;
              arrayOfObject[1] = Integer.valueOf(i);
              return localMethod.invoke(null, arrayOfObject);
            }
          };
          return local3;
        }
        catch (Exception localException3) {}
      }
    }
    new k()
    {
      public <T> T a(Class<T> paramAnonymousClass)
      {
        throw new UnsupportedOperationException("Cannot allocate " + paramAnonymousClass);
      }
    };
  }
  
  public abstract <T> T a(Class<T> paramClass)
    throws Exception;
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.a.b.k
 * JD-Core Version:    0.7.0.1
 */