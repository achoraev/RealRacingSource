package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.dynamic.d.a;

public abstract interface ej
  extends IInterface
{
  public abstract IBinder b(d paramd)
    throws RemoteException;
  
  public static abstract class a
    extends Binder
    implements ej
  {
    public static ej v(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseManagerCreator");
      if ((localIInterface != null) && ((localIInterface instanceof ej))) {
        return (ej)localIInterface;
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
        paramParcel2.writeString("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseManagerCreator");
        return true;
      }
      paramParcel1.enforceInterface("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseManagerCreator");
      IBinder localIBinder = b(d.a.am(paramParcel1.readStrongBinder()));
      paramParcel2.writeNoException();
      paramParcel2.writeStrongBinder(localIBinder);
      return true;
    }
    
    private static class a
      implements ej
    {
      private IBinder lb;
      
      a(IBinder paramIBinder)
      {
        this.lb = paramIBinder;
      }
      
      public IBinder asBinder()
      {
        return this.lb;
      }
      
      /* Error */
      public IBinder b(d paramd)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 27	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: invokestatic 27	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_3
        //   8: aload_2
        //   9: ldc 29
        //   11: invokevirtual 33	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_1
        //   15: ifnull +52 -> 67
        //   18: aload_1
        //   19: invokeinterface 37 1 0
        //   24: astore 5
        //   26: aload_2
        //   27: aload 5
        //   29: invokevirtual 40	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   32: aload_0
        //   33: getfield 15	com/google/android/gms/internal/ej$a$a:lb	Landroid/os/IBinder;
        //   36: iconst_1
        //   37: aload_2
        //   38: aload_3
        //   39: iconst_0
        //   40: invokeinterface 46 5 0
        //   45: pop
        //   46: aload_3
        //   47: invokevirtual 49	android/os/Parcel:readException	()V
        //   50: aload_3
        //   51: invokevirtual 52	android/os/Parcel:readStrongBinder	()Landroid/os/IBinder;
        //   54: astore 7
        //   56: aload_3
        //   57: invokevirtual 55	android/os/Parcel:recycle	()V
        //   60: aload_2
        //   61: invokevirtual 55	android/os/Parcel:recycle	()V
        //   64: aload 7
        //   66: areturn
        //   67: aconst_null
        //   68: astore 5
        //   70: goto -44 -> 26
        //   73: astore 4
        //   75: aload_3
        //   76: invokevirtual 55	android/os/Parcel:recycle	()V
        //   79: aload_2
        //   80: invokevirtual 55	android/os/Parcel:recycle	()V
        //   83: aload 4
        //   85: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	86	0	this	a
        //   0	86	1	paramd	d
        //   3	77	2	localParcel1	Parcel
        //   7	69	3	localParcel2	Parcel
        //   73	11	4	localObject	Object
        //   24	45	5	localIBinder1	IBinder
        //   54	11	7	localIBinder2	IBinder
        // Exception table:
        //   from	to	target	type
        //   8	14	73	finally
        //   18	26	73	finally
        //   26	56	73	finally
      }
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.ej
 * JD-Core Version:    0.7.0.1
 */