package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.dynamic.d.a;

public abstract interface be
  extends IInterface
{
  public abstract IBinder a(d paramd, ay paramay, String paramString, ct paramct, int paramInt)
    throws RemoteException;
  
  public static abstract class a
    extends Binder
    implements be
  {
    public static be g(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManagerCreator");
      if ((localIInterface != null) && ((localIInterface instanceof be))) {
        return (be)localIInterface;
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
        paramParcel2.writeString("com.google.android.gms.ads.internal.client.IAdManagerCreator");
        return true;
      }
      paramParcel1.enforceInterface("com.google.android.gms.ads.internal.client.IAdManagerCreator");
      d locald = d.a.am(paramParcel1.readStrongBinder());
      if (paramParcel1.readInt() != 0) {}
      for (ay localay = ay.CREATOR.c(paramParcel1);; localay = null)
      {
        IBinder localIBinder = a(locald, localay, paramParcel1.readString(), ct.a.l(paramParcel1.readStrongBinder()), paramParcel1.readInt());
        paramParcel2.writeNoException();
        paramParcel2.writeStrongBinder(localIBinder);
        return true;
      }
    }
    
    private static class a
      implements be
    {
      private IBinder lb;
      
      a(IBinder paramIBinder)
      {
        this.lb = paramIBinder;
      }
      
      /* Error */
      public IBinder a(d paramd, ay paramay, String paramString, ct paramct, int paramInt)
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
        //   18: ifnull +113 -> 131
        //   21: aload_1
        //   22: invokeinterface 37 1 0
        //   27: astore 9
        //   29: aload 6
        //   31: aload 9
        //   33: invokevirtual 40	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload_2
        //   37: ifnull +100 -> 137
        //   40: aload 6
        //   42: iconst_1
        //   43: invokevirtual 44	android/os/Parcel:writeInt	(I)V
        //   46: aload_2
        //   47: aload 6
        //   49: iconst_0
        //   50: invokevirtual 50	com/google/android/gms/internal/ay:writeToParcel	(Landroid/os/Parcel;I)V
        //   53: aload 6
        //   55: aload_3
        //   56: invokevirtual 53	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   59: aconst_null
        //   60: astore 10
        //   62: aload 4
        //   64: ifnull +12 -> 76
        //   67: aload 4
        //   69: invokeinterface 56 1 0
        //   74: astore 10
        //   76: aload 6
        //   78: aload 10
        //   80: invokevirtual 40	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   83: aload 6
        //   85: iload 5
        //   87: invokevirtual 44	android/os/Parcel:writeInt	(I)V
        //   90: aload_0
        //   91: getfield 15	com/google/android/gms/internal/be$a$a:lb	Landroid/os/IBinder;
        //   94: iconst_1
        //   95: aload 6
        //   97: aload 7
        //   99: iconst_0
        //   100: invokeinterface 62 5 0
        //   105: pop
        //   106: aload 7
        //   108: invokevirtual 65	android/os/Parcel:readException	()V
        //   111: aload 7
        //   113: invokevirtual 68	android/os/Parcel:readStrongBinder	()Landroid/os/IBinder;
        //   116: astore 12
        //   118: aload 7
        //   120: invokevirtual 71	android/os/Parcel:recycle	()V
        //   123: aload 6
        //   125: invokevirtual 71	android/os/Parcel:recycle	()V
        //   128: aload 12
        //   130: areturn
        //   131: aconst_null
        //   132: astore 9
        //   134: goto -105 -> 29
        //   137: aload 6
        //   139: iconst_0
        //   140: invokevirtual 44	android/os/Parcel:writeInt	(I)V
        //   143: goto -90 -> 53
        //   146: astore 8
        //   148: aload 7
        //   150: invokevirtual 71	android/os/Parcel:recycle	()V
        //   153: aload 6
        //   155: invokevirtual 71	android/os/Parcel:recycle	()V
        //   158: aload 8
        //   160: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	161	0	this	a
        //   0	161	1	paramd	d
        //   0	161	2	paramay	ay
        //   0	161	3	paramString	String
        //   0	161	4	paramct	ct
        //   0	161	5	paramInt	int
        //   3	151	6	localParcel1	Parcel
        //   8	141	7	localParcel2	Parcel
        //   146	13	8	localObject	Object
        //   27	106	9	localIBinder1	IBinder
        //   60	19	10	localIBinder2	IBinder
        //   116	13	12	localIBinder3	IBinder
        // Exception table:
        //   from	to	target	type
        //   10	17	146	finally
        //   21	29	146	finally
        //   29	36	146	finally
        //   40	53	146	finally
        //   53	59	146	finally
        //   67	76	146	finally
        //   76	118	146	finally
        //   137	143	146	finally
      }
      
      public IBinder asBinder()
      {
        return this.lb;
      }
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.be
 * JD-Core Version:    0.7.0.1
 */