package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public abstract interface nh
  extends IInterface
{
  public abstract void a(String paramString, nm paramnm, ni paramni)
    throws RemoteException;
  
  public abstract void a(String paramString, nm paramnm, List<ni> paramList)
    throws RemoteException;
  
  public abstract void a(String paramString, nm paramnm, byte[] paramArrayOfByte)
    throws RemoteException;
  
  public static abstract class a
    extends Binder
    implements nh
  {
    public static nh bC(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.playlog.internal.IPlayLogService");
      if ((localIInterface != null) && ((localIInterface instanceof nh))) {
        return (nh)localIInterface;
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
        paramParcel2.writeString("com.google.android.gms.playlog.internal.IPlayLogService");
        return true;
      case 2: 
        paramParcel1.enforceInterface("com.google.android.gms.playlog.internal.IPlayLogService");
        String str3 = paramParcel1.readString();
        if (paramParcel1.readInt() != 0) {}
        for (nm localnm3 = nm.CREATOR.cY(paramParcel1);; localnm3 = null)
        {
          int k = paramParcel1.readInt();
          ni localni = null;
          if (k != 0) {
            localni = ni.CREATOR.cX(paramParcel1);
          }
          a(str3, localnm3, localni);
          return true;
        }
      case 3: 
        paramParcel1.enforceInterface("com.google.android.gms.playlog.internal.IPlayLogService");
        String str2 = paramParcel1.readString();
        int j = paramParcel1.readInt();
        nm localnm2 = null;
        if (j != 0) {
          localnm2 = nm.CREATOR.cY(paramParcel1);
        }
        a(str2, localnm2, paramParcel1.createTypedArrayList(ni.CREATOR));
        return true;
      }
      paramParcel1.enforceInterface("com.google.android.gms.playlog.internal.IPlayLogService");
      String str1 = paramParcel1.readString();
      int i = paramParcel1.readInt();
      nm localnm1 = null;
      if (i != 0) {
        localnm1 = nm.CREATOR.cY(paramParcel1);
      }
      a(str1, localnm1, paramParcel1.createByteArray());
      return true;
    }
    
    private static class a
      implements nh
    {
      private IBinder lb;
      
      a(IBinder paramIBinder)
      {
        this.lb = paramIBinder;
      }
      
      public void a(String paramString, nm paramnm, ni paramni)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel.writeInterfaceToken("com.google.android.gms.playlog.internal.IPlayLogService");
            localParcel.writeString(paramString);
            if (paramnm != null)
            {
              localParcel.writeInt(1);
              paramnm.writeToParcel(localParcel, 0);
              if (paramni != null)
              {
                localParcel.writeInt(1);
                paramni.writeToParcel(localParcel, 0);
                this.lb.transact(2, localParcel, null, 1);
              }
            }
            else
            {
              localParcel.writeInt(0);
              continue;
            }
            localParcel.writeInt(0);
          }
          finally
          {
            localParcel.recycle();
          }
        }
      }
      
      /* Error */
      public void a(String paramString, nm paramnm, List<ni> paramList)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 4
        //   5: aload 4
        //   7: ldc 27
        //   9: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   12: aload 4
        //   14: aload_1
        //   15: invokevirtual 34	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   18: aload_2
        //   19: ifnull +43 -> 62
        //   22: aload 4
        //   24: iconst_1
        //   25: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   28: aload_2
        //   29: aload 4
        //   31: iconst_0
        //   32: invokevirtual 44	com/google/android/gms/internal/nm:writeToParcel	(Landroid/os/Parcel;I)V
        //   35: aload 4
        //   37: aload_3
        //   38: invokevirtual 61	android/os/Parcel:writeTypedList	(Ljava/util/List;)V
        //   41: aload_0
        //   42: getfield 15	com/google/android/gms/internal/nh$a$a:lb	Landroid/os/IBinder;
        //   45: iconst_3
        //   46: aload 4
        //   48: aconst_null
        //   49: iconst_1
        //   50: invokeinterface 53 5 0
        //   55: pop
        //   56: aload 4
        //   58: invokevirtual 56	android/os/Parcel:recycle	()V
        //   61: return
        //   62: aload 4
        //   64: iconst_0
        //   65: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   68: goto -33 -> 35
        //   71: astore 5
        //   73: aload 4
        //   75: invokevirtual 56	android/os/Parcel:recycle	()V
        //   78: aload 5
        //   80: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	81	0	this	a
        //   0	81	1	paramString	String
        //   0	81	2	paramnm	nm
        //   0	81	3	paramList	List<ni>
        //   3	71	4	localParcel	Parcel
        //   71	8	5	localObject	Object
        // Exception table:
        //   from	to	target	type
        //   5	18	71	finally
        //   22	35	71	finally
        //   35	56	71	finally
        //   62	68	71	finally
      }
      
      /* Error */
      public void a(String paramString, nm paramnm, byte[] paramArrayOfByte)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 4
        //   5: aload 4
        //   7: ldc 27
        //   9: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   12: aload 4
        //   14: aload_1
        //   15: invokevirtual 34	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   18: aload_2
        //   19: ifnull +43 -> 62
        //   22: aload 4
        //   24: iconst_1
        //   25: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   28: aload_2
        //   29: aload 4
        //   31: iconst_0
        //   32: invokevirtual 44	com/google/android/gms/internal/nm:writeToParcel	(Landroid/os/Parcel;I)V
        //   35: aload 4
        //   37: aload_3
        //   38: invokevirtual 66	android/os/Parcel:writeByteArray	([B)V
        //   41: aload_0
        //   42: getfield 15	com/google/android/gms/internal/nh$a$a:lb	Landroid/os/IBinder;
        //   45: iconst_4
        //   46: aload 4
        //   48: aconst_null
        //   49: iconst_1
        //   50: invokeinterface 53 5 0
        //   55: pop
        //   56: aload 4
        //   58: invokevirtual 56	android/os/Parcel:recycle	()V
        //   61: return
        //   62: aload 4
        //   64: iconst_0
        //   65: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   68: goto -33 -> 35
        //   71: astore 5
        //   73: aload 4
        //   75: invokevirtual 56	android/os/Parcel:recycle	()V
        //   78: aload 5
        //   80: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	81	0	this	a
        //   0	81	1	paramString	String
        //   0	81	2	paramnm	nm
        //   0	81	3	paramArrayOfByte	byte[]
        //   3	71	4	localParcel	Parcel
        //   71	8	5	localObject	Object
        // Exception table:
        //   from	to	target	type
        //   5	18	71	finally
        //   22	35	71	finally
        //   35	56	71	finally
        //   62	68	71	finally
      }
      
      public IBinder asBinder()
      {
        return this.lb;
      }
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.nh
 * JD-Core Version:    0.7.0.1
 */