package com.google.android.gms.plus.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.dynamic.d.a;

public abstract interface c
  extends IInterface
{
  public abstract d a(d paramd, int paramInt1, int paramInt2, String paramString, int paramInt3)
    throws RemoteException;
  
  public abstract d a(d paramd, int paramInt1, int paramInt2, String paramString1, String paramString2)
    throws RemoteException;
  
  public static abstract class a
    extends Binder
    implements c
  {
    public static c bF(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.plus.internal.IPlusOneButtonCreator");
      if ((localIInterface != null) && ((localIInterface instanceof c))) {
        return (c)localIInterface;
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
        paramParcel2.writeString("com.google.android.gms.plus.internal.IPlusOneButtonCreator");
        return true;
      case 1: 
        paramParcel1.enforceInterface("com.google.android.gms.plus.internal.IPlusOneButtonCreator");
        d locald2 = a(d.a.am(paramParcel1.readStrongBinder()), paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readString(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        if (locald2 != null) {}
        for (IBinder localIBinder2 = locald2.asBinder();; localIBinder2 = null)
        {
          paramParcel2.writeStrongBinder(localIBinder2);
          return true;
        }
      }
      paramParcel1.enforceInterface("com.google.android.gms.plus.internal.IPlusOneButtonCreator");
      d locald1 = a(d.a.am(paramParcel1.readStrongBinder()), paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readString(), paramParcel1.readString());
      paramParcel2.writeNoException();
      IBinder localIBinder1 = null;
      if (locald1 != null) {
        localIBinder1 = locald1.asBinder();
      }
      paramParcel2.writeStrongBinder(localIBinder1);
      return true;
    }
    
    private static class a
      implements c
    {
      private IBinder lb;
      
      a(IBinder paramIBinder)
      {
        this.lb = paramIBinder;
      }
      
      /* Error */
      public d a(d paramd, int paramInt1, int paramInt2, String paramString, int paramInt3)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 6
        //   5: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 7
        //   10: aload 6
        //   12: ldc 27
        //   14: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +88 -> 106
        //   21: aload_1
        //   22: invokeinterface 37 1 0
        //   27: astore 9
        //   29: aload 6
        //   31: aload 9
        //   33: invokevirtual 40	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload 6
        //   38: iload_2
        //   39: invokevirtual 44	android/os/Parcel:writeInt	(I)V
        //   42: aload 6
        //   44: iload_3
        //   45: invokevirtual 44	android/os/Parcel:writeInt	(I)V
        //   48: aload 6
        //   50: aload 4
        //   52: invokevirtual 47	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   55: aload 6
        //   57: iload 5
        //   59: invokevirtual 44	android/os/Parcel:writeInt	(I)V
        //   62: aload_0
        //   63: getfield 15	com/google/android/gms/plus/internal/c$a$a:lb	Landroid/os/IBinder;
        //   66: iconst_1
        //   67: aload 6
        //   69: aload 7
        //   71: iconst_0
        //   72: invokeinterface 53 5 0
        //   77: pop
        //   78: aload 7
        //   80: invokevirtual 56	android/os/Parcel:readException	()V
        //   83: aload 7
        //   85: invokevirtual 59	android/os/Parcel:readStrongBinder	()Landroid/os/IBinder;
        //   88: invokestatic 65	com/google/android/gms/dynamic/d$a:am	(Landroid/os/IBinder;)Lcom/google/android/gms/dynamic/d;
        //   91: astore 11
        //   93: aload 7
        //   95: invokevirtual 68	android/os/Parcel:recycle	()V
        //   98: aload 6
        //   100: invokevirtual 68	android/os/Parcel:recycle	()V
        //   103: aload 11
        //   105: areturn
        //   106: aconst_null
        //   107: astore 9
        //   109: goto -80 -> 29
        //   112: astore 8
        //   114: aload 7
        //   116: invokevirtual 68	android/os/Parcel:recycle	()V
        //   119: aload 6
        //   121: invokevirtual 68	android/os/Parcel:recycle	()V
        //   124: aload 8
        //   126: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	127	0	this	a
        //   0	127	1	paramd	d
        //   0	127	2	paramInt1	int
        //   0	127	3	paramInt2	int
        //   0	127	4	paramString	String
        //   0	127	5	paramInt3	int
        //   3	117	6	localParcel1	Parcel
        //   8	107	7	localParcel2	Parcel
        //   112	13	8	localObject	Object
        //   27	81	9	localIBinder	IBinder
        //   91	13	11	locald	d
        // Exception table:
        //   from	to	target	type
        //   10	17	112	finally
        //   21	29	112	finally
        //   29	93	112	finally
      }
      
      /* Error */
      public d a(d paramd, int paramInt1, int paramInt2, String paramString1, String paramString2)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 6
        //   5: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 7
        //   10: aload 6
        //   12: ldc 27
        //   14: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +88 -> 106
        //   21: aload_1
        //   22: invokeinterface 37 1 0
        //   27: astore 9
        //   29: aload 6
        //   31: aload 9
        //   33: invokevirtual 40	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload 6
        //   38: iload_2
        //   39: invokevirtual 44	android/os/Parcel:writeInt	(I)V
        //   42: aload 6
        //   44: iload_3
        //   45: invokevirtual 44	android/os/Parcel:writeInt	(I)V
        //   48: aload 6
        //   50: aload 4
        //   52: invokevirtual 47	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   55: aload 6
        //   57: aload 5
        //   59: invokevirtual 47	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   62: aload_0
        //   63: getfield 15	com/google/android/gms/plus/internal/c$a$a:lb	Landroid/os/IBinder;
        //   66: iconst_2
        //   67: aload 6
        //   69: aload 7
        //   71: iconst_0
        //   72: invokeinterface 53 5 0
        //   77: pop
        //   78: aload 7
        //   80: invokevirtual 56	android/os/Parcel:readException	()V
        //   83: aload 7
        //   85: invokevirtual 59	android/os/Parcel:readStrongBinder	()Landroid/os/IBinder;
        //   88: invokestatic 65	com/google/android/gms/dynamic/d$a:am	(Landroid/os/IBinder;)Lcom/google/android/gms/dynamic/d;
        //   91: astore 11
        //   93: aload 7
        //   95: invokevirtual 68	android/os/Parcel:recycle	()V
        //   98: aload 6
        //   100: invokevirtual 68	android/os/Parcel:recycle	()V
        //   103: aload 11
        //   105: areturn
        //   106: aconst_null
        //   107: astore 9
        //   109: goto -80 -> 29
        //   112: astore 8
        //   114: aload 7
        //   116: invokevirtual 68	android/os/Parcel:recycle	()V
        //   119: aload 6
        //   121: invokevirtual 68	android/os/Parcel:recycle	()V
        //   124: aload 8
        //   126: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	127	0	this	a
        //   0	127	1	paramd	d
        //   0	127	2	paramInt1	int
        //   0	127	3	paramInt2	int
        //   0	127	4	paramString1	String
        //   0	127	5	paramString2	String
        //   3	117	6	localParcel1	Parcel
        //   8	107	7	localParcel2	Parcel
        //   112	13	8	localObject	Object
        //   27	81	9	localIBinder	IBinder
        //   91	13	11	locald	d
        // Exception table:
        //   from	to	target	type
        //   10	17	112	finally
        //   21	29	112	finally
        //   29	93	112	finally
      }
      
      public IBinder asBinder()
      {
        return this.lb;
      }
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.plus.internal.c
 * JD-Core Version:    0.7.0.1
 */