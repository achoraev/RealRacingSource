package com.google.android.gms.drive.realtime.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface h
  extends IInterface
{
  public abstract void c(boolean paramBoolean1, boolean paramBoolean2)
    throws RemoteException;
  
  public static abstract class a
    extends Binder
    implements h
  {
    public static h ad(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.drive.realtime.internal.IDocumentSaveStateEventCallback");
      if ((localIInterface != null) && ((localIInterface instanceof h))) {
        return (h)localIInterface;
      }
      return new a(paramIBinder);
    }
    
    public IBinder asBinder()
    {
      return this;
    }
    
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      switch (paramInt1)
      {
      default: 
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902: 
        paramParcel2.writeString("com.google.android.gms.drive.realtime.internal.IDocumentSaveStateEventCallback");
        return true;
      }
      paramParcel1.enforceInterface("com.google.android.gms.drive.realtime.internal.IDocumentSaveStateEventCallback");
      if (paramParcel1.readInt() != 0) {}
      for (boolean bool1 = true;; bool1 = false)
      {
        int i = paramParcel1.readInt();
        boolean bool2 = false;
        if (i != 0) {
          bool2 = true;
        }
        c(bool1, bool2);
        paramParcel2.writeNoException();
        return true;
      }
    }
    
    private static class a
      implements h
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
      public void c(boolean paramBoolean1, boolean paramBoolean2)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore_3
        //   2: invokestatic 27	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   5: astore 4
        //   7: invokestatic 27	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   10: astore 5
        //   12: aload 4
        //   14: ldc 29
        //   16: invokevirtual 33	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   19: iload_1
        //   20: ifeq +55 -> 75
        //   23: iload_3
        //   24: istore 7
        //   26: aload 4
        //   28: iload 7
        //   30: invokevirtual 37	android/os/Parcel:writeInt	(I)V
        //   33: iload_2
        //   34: ifeq +47 -> 81
        //   37: aload 4
        //   39: iload_3
        //   40: invokevirtual 37	android/os/Parcel:writeInt	(I)V
        //   43: aload_0
        //   44: getfield 15	com/google/android/gms/drive/realtime/internal/h$a$a:lb	Landroid/os/IBinder;
        //   47: iconst_1
        //   48: aload 4
        //   50: aload 5
        //   52: iconst_0
        //   53: invokeinterface 43 5 0
        //   58: pop
        //   59: aload 5
        //   61: invokevirtual 46	android/os/Parcel:readException	()V
        //   64: aload 5
        //   66: invokevirtual 49	android/os/Parcel:recycle	()V
        //   69: aload 4
        //   71: invokevirtual 49	android/os/Parcel:recycle	()V
        //   74: return
        //   75: iconst_0
        //   76: istore 7
        //   78: goto -52 -> 26
        //   81: iconst_0
        //   82: istore_3
        //   83: goto -46 -> 37
        //   86: astore 6
        //   88: aload 5
        //   90: invokevirtual 49	android/os/Parcel:recycle	()V
        //   93: aload 4
        //   95: invokevirtual 49	android/os/Parcel:recycle	()V
        //   98: aload 6
        //   100: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	101	0	this	a
        //   0	101	1	paramBoolean1	boolean
        //   0	101	2	paramBoolean2	boolean
        //   1	82	3	i	int
        //   5	89	4	localParcel1	Parcel
        //   10	79	5	localParcel2	Parcel
        //   86	13	6	localObject	Object
        //   24	53	7	j	int
        // Exception table:
        //   from	to	target	type
        //   12	19	86	finally
        //   26	33	86	finally
        //   37	64	86	finally
      }
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.realtime.internal.h
 * JD-Core Version:    0.7.0.1
 */