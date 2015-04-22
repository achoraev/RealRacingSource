package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.google.android.gms.cast.LaunchOptions;

public abstract interface in
  extends IInterface
{
  public abstract void a(double paramDouble1, double paramDouble2, boolean paramBoolean)
    throws RemoteException;
  
  public abstract void a(String paramString, LaunchOptions paramLaunchOptions)
    throws RemoteException;
  
  public abstract void a(String paramString1, String paramString2, long paramLong)
    throws RemoteException;
  
  public abstract void a(String paramString, byte[] paramArrayOfByte, long paramLong)
    throws RemoteException;
  
  public abstract void a(boolean paramBoolean1, double paramDouble, boolean paramBoolean2)
    throws RemoteException;
  
  public abstract void aH(String paramString)
    throws RemoteException;
  
  public abstract void aI(String paramString)
    throws RemoteException;
  
  public abstract void aJ(String paramString)
    throws RemoteException;
  
  public abstract void disconnect()
    throws RemoteException;
  
  public abstract void f(String paramString, boolean paramBoolean)
    throws RemoteException;
  
  public abstract void fD()
    throws RemoteException;
  
  public abstract void fP()
    throws RemoteException;
  
  public abstract void l(String paramString1, String paramString2)
    throws RemoteException;
  
  public static abstract class a
    extends Binder
    implements in
  {
    public static in M(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.cast.internal.ICastDeviceController");
      if ((localIInterface != null) && ((localIInterface instanceof in))) {
        return (in)localIInterface;
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
        paramParcel2.writeString("com.google.android.gms.cast.internal.ICastDeviceController");
        return true;
      case 1: 
        paramParcel1.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceController");
        disconnect();
        return true;
      case 2: 
        paramParcel1.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceController");
        String str2 = paramParcel1.readString();
        int j = paramParcel1.readInt();
        boolean bool4 = false;
        if (j != 0) {
          bool4 = true;
        }
        f(str2, bool4);
        return true;
      case 3: 
        paramParcel1.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceController");
        l(paramParcel1.readString(), paramParcel1.readString());
        return true;
      case 4: 
        paramParcel1.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceController");
        fP();
        return true;
      case 5: 
        paramParcel1.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceController");
        aH(paramParcel1.readString());
        return true;
      case 6: 
        paramParcel1.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceController");
        fD();
        return true;
      case 7: 
        paramParcel1.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceController");
        double d2 = paramParcel1.readDouble();
        double d3 = paramParcel1.readDouble();
        if (paramParcel1.readInt() != 0) {}
        for (boolean bool3 = true;; bool3 = false)
        {
          a(d2, d3, bool3);
          return true;
        }
      case 8: 
        paramParcel1.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceController");
        if (paramParcel1.readInt() != 0) {}
        for (boolean bool1 = true;; bool1 = false)
        {
          double d1 = paramParcel1.readDouble();
          int i = paramParcel1.readInt();
          boolean bool2 = false;
          if (i != 0) {
            bool2 = true;
          }
          a(bool1, d1, bool2);
          return true;
        }
      case 9: 
        paramParcel1.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceController");
        a(paramParcel1.readString(), paramParcel1.readString(), paramParcel1.readLong());
        return true;
      case 10: 
        paramParcel1.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceController");
        a(paramParcel1.readString(), paramParcel1.createByteArray(), paramParcel1.readLong());
        return true;
      case 11: 
        paramParcel1.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceController");
        aI(paramParcel1.readString());
        return true;
      case 12: 
        paramParcel1.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceController");
        aJ(paramParcel1.readString());
        return true;
      }
      paramParcel1.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceController");
      String str1 = paramParcel1.readString();
      if (paramParcel1.readInt() != 0) {}
      for (LaunchOptions localLaunchOptions = (LaunchOptions)LaunchOptions.CREATOR.createFromParcel(paramParcel1);; localLaunchOptions = null)
      {
        a(str1, localLaunchOptions);
        return true;
      }
    }
    
    private static class a
      implements in
    {
      private IBinder lb;
      
      a(IBinder paramIBinder)
      {
        this.lb = paramIBinder;
      }
      
      /* Error */
      public void a(double paramDouble1, double paramDouble2, boolean paramBoolean)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore 6
        //   3: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 7
        //   8: aload 7
        //   10: ldc 27
        //   12: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   15: aload 7
        //   17: dload_1
        //   18: invokevirtual 35	android/os/Parcel:writeDouble	(D)V
        //   21: aload 7
        //   23: dload_3
        //   24: invokevirtual 35	android/os/Parcel:writeDouble	(D)V
        //   27: iload 5
        //   29: ifeq +32 -> 61
        //   32: aload 7
        //   34: iload 6
        //   36: invokevirtual 39	android/os/Parcel:writeInt	(I)V
        //   39: aload_0
        //   40: getfield 15	com/google/android/gms/internal/in$a$a:lb	Landroid/os/IBinder;
        //   43: bipush 7
        //   45: aload 7
        //   47: aconst_null
        //   48: iconst_1
        //   49: invokeinterface 45 5 0
        //   54: pop
        //   55: aload 7
        //   57: invokevirtual 48	android/os/Parcel:recycle	()V
        //   60: return
        //   61: iconst_0
        //   62: istore 6
        //   64: goto -32 -> 32
        //   67: astore 8
        //   69: aload 7
        //   71: invokevirtual 48	android/os/Parcel:recycle	()V
        //   74: aload 8
        //   76: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	77	0	this	a
        //   0	77	1	paramDouble1	double
        //   0	77	3	paramDouble2	double
        //   0	77	5	paramBoolean	boolean
        //   1	62	6	i	int
        //   6	64	7	localParcel	Parcel
        //   67	8	8	localObject	Object
        // Exception table:
        //   from	to	target	type
        //   8	27	67	finally
        //   32	55	67	finally
      }
      
      /* Error */
      public void a(String paramString, LaunchOptions paramLaunchOptions)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_3
        //   4: aload_3
        //   5: ldc 27
        //   7: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   10: aload_3
        //   11: aload_1
        //   12: invokevirtual 52	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   15: aload_2
        //   16: ifnull +34 -> 50
        //   19: aload_3
        //   20: iconst_1
        //   21: invokevirtual 39	android/os/Parcel:writeInt	(I)V
        //   24: aload_2
        //   25: aload_3
        //   26: iconst_0
        //   27: invokevirtual 58	com/google/android/gms/cast/LaunchOptions:writeToParcel	(Landroid/os/Parcel;I)V
        //   30: aload_0
        //   31: getfield 15	com/google/android/gms/internal/in$a$a:lb	Landroid/os/IBinder;
        //   34: bipush 13
        //   36: aload_3
        //   37: aconst_null
        //   38: iconst_1
        //   39: invokeinterface 45 5 0
        //   44: pop
        //   45: aload_3
        //   46: invokevirtual 48	android/os/Parcel:recycle	()V
        //   49: return
        //   50: aload_3
        //   51: iconst_0
        //   52: invokevirtual 39	android/os/Parcel:writeInt	(I)V
        //   55: goto -25 -> 30
        //   58: astore 4
        //   60: aload_3
        //   61: invokevirtual 48	android/os/Parcel:recycle	()V
        //   64: aload 4
        //   66: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	67	0	this	a
        //   0	67	1	paramString	String
        //   0	67	2	paramLaunchOptions	LaunchOptions
        //   3	58	3	localParcel	Parcel
        //   58	7	4	localObject	Object
        // Exception table:
        //   from	to	target	type
        //   4	15	58	finally
        //   19	30	58	finally
        //   30	45	58	finally
        //   50	55	58	finally
      }
      
      public void a(String paramString1, String paramString2, long paramLong)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.google.android.gms.cast.internal.ICastDeviceController");
          localParcel.writeString(paramString1);
          localParcel.writeString(paramString2);
          localParcel.writeLong(paramLong);
          this.lb.transact(9, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
      }
      
      public void a(String paramString, byte[] paramArrayOfByte, long paramLong)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.google.android.gms.cast.internal.ICastDeviceController");
          localParcel.writeString(paramString);
          localParcel.writeByteArray(paramArrayOfByte);
          localParcel.writeLong(paramLong);
          this.lb.transact(10, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
      }
      
      /* Error */
      public void a(boolean paramBoolean1, double paramDouble, boolean paramBoolean2)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore 5
        //   3: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 6
        //   8: aload 6
        //   10: ldc 27
        //   12: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   15: iload_1
        //   16: ifeq +54 -> 70
        //   19: iload 5
        //   21: istore 8
        //   23: aload 6
        //   25: iload 8
        //   27: invokevirtual 39	android/os/Parcel:writeInt	(I)V
        //   30: aload 6
        //   32: dload_2
        //   33: invokevirtual 35	android/os/Parcel:writeDouble	(D)V
        //   36: iload 4
        //   38: ifeq +38 -> 76
        //   41: aload 6
        //   43: iload 5
        //   45: invokevirtual 39	android/os/Parcel:writeInt	(I)V
        //   48: aload_0
        //   49: getfield 15	com/google/android/gms/internal/in$a$a:lb	Landroid/os/IBinder;
        //   52: bipush 8
        //   54: aload 6
        //   56: aconst_null
        //   57: iconst_1
        //   58: invokeinterface 45 5 0
        //   63: pop
        //   64: aload 6
        //   66: invokevirtual 48	android/os/Parcel:recycle	()V
        //   69: return
        //   70: iconst_0
        //   71: istore 8
        //   73: goto -50 -> 23
        //   76: iconst_0
        //   77: istore 5
        //   79: goto -38 -> 41
        //   82: astore 7
        //   84: aload 6
        //   86: invokevirtual 48	android/os/Parcel:recycle	()V
        //   89: aload 7
        //   91: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	92	0	this	a
        //   0	92	1	paramBoolean1	boolean
        //   0	92	2	paramDouble	double
        //   0	92	4	paramBoolean2	boolean
        //   1	77	5	i	int
        //   6	79	6	localParcel	Parcel
        //   82	8	7	localObject	Object
        //   21	51	8	j	int
        // Exception table:
        //   from	to	target	type
        //   8	15	82	finally
        //   23	36	82	finally
        //   41	64	82	finally
      }
      
      public void aH(String paramString)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.google.android.gms.cast.internal.ICastDeviceController");
          localParcel.writeString(paramString);
          this.lb.transact(5, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
      }
      
      public void aI(String paramString)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.google.android.gms.cast.internal.ICastDeviceController");
          localParcel.writeString(paramString);
          this.lb.transact(11, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
      }
      
      public void aJ(String paramString)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.google.android.gms.cast.internal.ICastDeviceController");
          localParcel.writeString(paramString);
          this.lb.transact(12, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
      }
      
      public IBinder asBinder()
      {
        return this.lb;
      }
      
      public void disconnect()
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.google.android.gms.cast.internal.ICastDeviceController");
          this.lb.transact(1, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
      }
      
      /* Error */
      public void f(String paramString, boolean paramBoolean)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore_3
        //   2: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   5: astore 4
        //   7: aload 4
        //   9: ldc 27
        //   11: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload 4
        //   16: aload_1
        //   17: invokevirtual 52	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   20: iload_2
        //   21: ifeq +30 -> 51
        //   24: aload 4
        //   26: iload_3
        //   27: invokevirtual 39	android/os/Parcel:writeInt	(I)V
        //   30: aload_0
        //   31: getfield 15	com/google/android/gms/internal/in$a$a:lb	Landroid/os/IBinder;
        //   34: iconst_2
        //   35: aload 4
        //   37: aconst_null
        //   38: iconst_1
        //   39: invokeinterface 45 5 0
        //   44: pop
        //   45: aload 4
        //   47: invokevirtual 48	android/os/Parcel:recycle	()V
        //   50: return
        //   51: iconst_0
        //   52: istore_3
        //   53: goto -29 -> 24
        //   56: astore 5
        //   58: aload 4
        //   60: invokevirtual 48	android/os/Parcel:recycle	()V
        //   63: aload 5
        //   65: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	66	0	this	a
        //   0	66	1	paramString	String
        //   0	66	2	paramBoolean	boolean
        //   1	52	3	i	int
        //   5	54	4	localParcel	Parcel
        //   56	8	5	localObject	Object
        // Exception table:
        //   from	to	target	type
        //   7	20	56	finally
        //   24	45	56	finally
      }
      
      public void fD()
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.google.android.gms.cast.internal.ICastDeviceController");
          this.lb.transact(6, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
      }
      
      public void fP()
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.google.android.gms.cast.internal.ICastDeviceController");
          this.lb.transact(4, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
      }
      
      public void l(String paramString1, String paramString2)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.google.android.gms.cast.internal.ICastDeviceController");
          localParcel.writeString(paramString1);
          localParcel.writeString(paramString2);
          this.lb.transact(3, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
      }
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.in
 * JD-Core Version:    0.7.0.1
 */