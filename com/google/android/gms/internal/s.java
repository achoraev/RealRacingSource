package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface s
  extends IInterface
{
  public abstract boolean a(boolean paramBoolean)
    throws RemoteException;
  
  public abstract void b(String paramString, boolean paramBoolean)
    throws RemoteException;
  
  public abstract String c(String paramString)
    throws RemoteException;
  
  public abstract String getId()
    throws RemoteException;
  
  public static abstract class a
    extends Binder
    implements s
  {
    public static s b(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
      if ((localIInterface != null) && ((localIInterface instanceof s))) {
        return (s)localIInterface;
      }
      return new a(paramIBinder);
    }
    
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      switch (paramInt1)
      {
      default: 
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902: 
        paramParcel2.writeString("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
        return true;
      case 1: 
        paramParcel1.enforceInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
        String str3 = getId();
        paramParcel2.writeNoException();
        paramParcel2.writeString(str3);
        return true;
      case 2: 
        paramParcel1.enforceInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
        if (paramParcel1.readInt() != 0) {}
        for (boolean bool2 = true;; bool2 = false)
        {
          boolean bool3 = a(bool2);
          paramParcel2.writeNoException();
          int j = 0;
          if (bool3) {
            j = 1;
          }
          paramParcel2.writeInt(j);
          return true;
        }
      case 3: 
        paramParcel1.enforceInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
        String str2 = c(paramParcel1.readString());
        paramParcel2.writeNoException();
        paramParcel2.writeString(str2);
        return true;
      }
      paramParcel1.enforceInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
      String str1 = paramParcel1.readString();
      int i = paramParcel1.readInt();
      boolean bool1 = false;
      if (i != 0) {
        bool1 = true;
      }
      b(str1, bool1);
      paramParcel2.writeNoException();
      return true;
    }
    
    private static class a
      implements s
    {
      private IBinder lb;
      
      a(IBinder paramIBinder)
      {
        this.lb = paramIBinder;
      }
      
      /* Error */
      public boolean a(boolean paramBoolean)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore_2
        //   2: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   5: astore_3
        //   6: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   9: astore 4
        //   11: aload_3
        //   12: ldc 27
        //   14: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: iload_1
        //   18: ifeq +55 -> 73
        //   21: iload_2
        //   22: istore 6
        //   24: aload_3
        //   25: iload 6
        //   27: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   30: aload_0
        //   31: getfield 15	com/google/android/gms/internal/s$a$a:lb	Landroid/os/IBinder;
        //   34: iconst_2
        //   35: aload_3
        //   36: aload 4
        //   38: iconst_0
        //   39: invokeinterface 41 5 0
        //   44: pop
        //   45: aload 4
        //   47: invokevirtual 44	android/os/Parcel:readException	()V
        //   50: aload 4
        //   52: invokevirtual 48	android/os/Parcel:readInt	()I
        //   55: istore 8
        //   57: iload 8
        //   59: ifeq +20 -> 79
        //   62: aload 4
        //   64: invokevirtual 51	android/os/Parcel:recycle	()V
        //   67: aload_3
        //   68: invokevirtual 51	android/os/Parcel:recycle	()V
        //   71: iload_2
        //   72: ireturn
        //   73: iconst_0
        //   74: istore 6
        //   76: goto -52 -> 24
        //   79: iconst_0
        //   80: istore_2
        //   81: goto -19 -> 62
        //   84: astore 5
        //   86: aload 4
        //   88: invokevirtual 51	android/os/Parcel:recycle	()V
        //   91: aload_3
        //   92: invokevirtual 51	android/os/Parcel:recycle	()V
        //   95: aload 5
        //   97: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	98	0	this	a
        //   0	98	1	paramBoolean	boolean
        //   1	80	2	i	int
        //   5	87	3	localParcel1	Parcel
        //   9	78	4	localParcel2	Parcel
        //   84	12	5	localObject	Object
        //   22	4	6	j	int
        //   74	1	6	k	int
        //   55	3	8	m	int
        // Exception table:
        //   from	to	target	type
        //   11	17	84	finally
        //   24	57	84	finally
      }
      
      public IBinder asBinder()
      {
        return this.lb;
      }
      
      public void b(String paramString, boolean paramBoolean)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
          localParcel1.writeString(paramString);
          int i = 0;
          if (paramBoolean) {
            i = 1;
          }
          localParcel1.writeInt(i);
          this.lb.transact(4, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public String c(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
          localParcel1.writeString(paramString);
          this.lb.transact(3, localParcel1, localParcel2, 0);
          localParcel2.readException();
          String str = localParcel2.readString();
          return str;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public String getId()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
          this.lb.transact(1, localParcel1, localParcel2, 0);
          localParcel2.readException();
          String str = localParcel2.readString();
          return str;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.s
 * JD-Core Version:    0.7.0.1
 */