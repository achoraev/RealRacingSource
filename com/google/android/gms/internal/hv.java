package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface hv
  extends IInterface
{
  public abstract void a(hm.a parama, hw paramhw)
    throws RemoteException;
  
  public abstract void a(hw paramhw)
    throws RemoteException;
  
  public abstract void a(hw paramhw, String paramString, hs[] paramArrayOfhs)
    throws RemoteException;
  
  public abstract void a(hw paramhw, boolean paramBoolean)
    throws RemoteException;
  
  public abstract void b(hw paramhw)
    throws RemoteException;
  
  public static abstract class a
    extends Binder
    implements hv
  {
    public static hv F(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.appdatasearch.internal.ILightweightAppDataSearch");
      if ((localIInterface != null) && ((localIInterface instanceof hv))) {
        return (hv)localIInterface;
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
        paramParcel2.writeString("com.google.android.gms.appdatasearch.internal.ILightweightAppDataSearch");
        return true;
      case 1: 
        paramParcel1.enforceInterface("com.google.android.gms.appdatasearch.internal.ILightweightAppDataSearch");
        a(hw.a.G(paramParcel1.readStrongBinder()), paramParcel1.readString(), (hs[])paramParcel1.createTypedArray(hs.CREATOR));
        paramParcel2.writeNoException();
        return true;
      case 2: 
        paramParcel1.enforceInterface("com.google.android.gms.appdatasearch.internal.ILightweightAppDataSearch");
        a(hw.a.G(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 3: 
        paramParcel1.enforceInterface("com.google.android.gms.appdatasearch.internal.ILightweightAppDataSearch");
        b(hw.a.G(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 4: 
        paramParcel1.enforceInterface("com.google.android.gms.appdatasearch.internal.ILightweightAppDataSearch");
        hw localhw = hw.a.G(paramParcel1.readStrongBinder());
        if (paramParcel1.readInt() != 0) {}
        for (boolean bool = true;; bool = false)
        {
          a(localhw, bool);
          paramParcel2.writeNoException();
          return true;
        }
      }
      paramParcel1.enforceInterface("com.google.android.gms.appdatasearch.internal.ILightweightAppDataSearch");
      if (paramParcel1.readInt() != 0) {}
      for (hm.a locala = hm.a.CREATOR.p(paramParcel1);; locala = null)
      {
        a(locala, hw.a.G(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      }
    }
    
    private static class a
      implements hv
    {
      private IBinder lb;
      
      a(IBinder paramIBinder)
      {
        this.lb = paramIBinder;
      }
      
      public void a(hm.a parama, hw paramhw)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.appdatasearch.internal.ILightweightAppDataSearch");
            if (parama != null)
            {
              localParcel1.writeInt(1);
              parama.writeToParcel(localParcel1, 0);
              if (paramhw != null)
              {
                localIBinder = paramhw.asBinder();
                localParcel1.writeStrongBinder(localIBinder);
                this.lb.transact(5, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            IBinder localIBinder = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      /* Error */
      public void a(hw paramhw)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_3
        //   8: aload_2
        //   9: ldc 27
        //   11: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_1
        //   15: ifnull +44 -> 59
        //   18: aload_1
        //   19: invokeinterface 47 1 0
        //   24: astore 5
        //   26: aload_2
        //   27: aload 5
        //   29: invokevirtual 50	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   32: aload_0
        //   33: getfield 15	com/google/android/gms/internal/hv$a$a:lb	Landroid/os/IBinder;
        //   36: iconst_2
        //   37: aload_2
        //   38: aload_3
        //   39: iconst_0
        //   40: invokeinterface 56 5 0
        //   45: pop
        //   46: aload_3
        //   47: invokevirtual 59	android/os/Parcel:readException	()V
        //   50: aload_3
        //   51: invokevirtual 62	android/os/Parcel:recycle	()V
        //   54: aload_2
        //   55: invokevirtual 62	android/os/Parcel:recycle	()V
        //   58: return
        //   59: aconst_null
        //   60: astore 5
        //   62: goto -36 -> 26
        //   65: astore 4
        //   67: aload_3
        //   68: invokevirtual 62	android/os/Parcel:recycle	()V
        //   71: aload_2
        //   72: invokevirtual 62	android/os/Parcel:recycle	()V
        //   75: aload 4
        //   77: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	78	0	this	a
        //   0	78	1	paramhw	hw
        //   3	69	2	localParcel1	Parcel
        //   7	61	3	localParcel2	Parcel
        //   65	11	4	localObject	Object
        //   24	37	5	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   8	14	65	finally
        //   18	26	65	finally
        //   26	50	65	finally
      }
      
      /* Error */
      public void a(hw paramhw, String paramString, hs[] paramArrayOfhs)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 4
        //   5: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 5
        //   10: aload 4
        //   12: ldc 27
        //   14: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +63 -> 81
        //   21: aload_1
        //   22: invokeinterface 47 1 0
        //   27: astore 7
        //   29: aload 4
        //   31: aload 7
        //   33: invokevirtual 50	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload 4
        //   38: aload_2
        //   39: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   42: aload 4
        //   44: aload_3
        //   45: iconst_0
        //   46: invokevirtual 71	android/os/Parcel:writeTypedArray	([Landroid/os/Parcelable;I)V
        //   49: aload_0
        //   50: getfield 15	com/google/android/gms/internal/hv$a$a:lb	Landroid/os/IBinder;
        //   53: iconst_1
        //   54: aload 4
        //   56: aload 5
        //   58: iconst_0
        //   59: invokeinterface 56 5 0
        //   64: pop
        //   65: aload 5
        //   67: invokevirtual 59	android/os/Parcel:readException	()V
        //   70: aload 5
        //   72: invokevirtual 62	android/os/Parcel:recycle	()V
        //   75: aload 4
        //   77: invokevirtual 62	android/os/Parcel:recycle	()V
        //   80: return
        //   81: aconst_null
        //   82: astore 7
        //   84: goto -55 -> 29
        //   87: astore 6
        //   89: aload 5
        //   91: invokevirtual 62	android/os/Parcel:recycle	()V
        //   94: aload 4
        //   96: invokevirtual 62	android/os/Parcel:recycle	()V
        //   99: aload 6
        //   101: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	102	0	this	a
        //   0	102	1	paramhw	hw
        //   0	102	2	paramString	String
        //   0	102	3	paramArrayOfhs	hs[]
        //   3	92	4	localParcel1	Parcel
        //   8	82	5	localParcel2	Parcel
        //   87	13	6	localObject	Object
        //   27	56	7	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   10	17	87	finally
        //   21	29	87	finally
        //   29	70	87	finally
      }
      
      /* Error */
      public void a(hw paramhw, boolean paramBoolean)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_3
        //   4: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore 4
        //   9: aload_3
        //   10: ldc 27
        //   12: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   15: aload_1
        //   16: ifnull +63 -> 79
        //   19: aload_1
        //   20: invokeinterface 47 1 0
        //   25: astore 6
        //   27: aload_3
        //   28: aload 6
        //   30: invokevirtual 50	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   33: iconst_0
        //   34: istore 7
        //   36: iload_2
        //   37: ifeq +6 -> 43
        //   40: iconst_1
        //   41: istore 7
        //   43: aload_3
        //   44: iload 7
        //   46: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   49: aload_0
        //   50: getfield 15	com/google/android/gms/internal/hv$a$a:lb	Landroid/os/IBinder;
        //   53: iconst_4
        //   54: aload_3
        //   55: aload 4
        //   57: iconst_0
        //   58: invokeinterface 56 5 0
        //   63: pop
        //   64: aload 4
        //   66: invokevirtual 59	android/os/Parcel:readException	()V
        //   69: aload 4
        //   71: invokevirtual 62	android/os/Parcel:recycle	()V
        //   74: aload_3
        //   75: invokevirtual 62	android/os/Parcel:recycle	()V
        //   78: return
        //   79: aconst_null
        //   80: astore 6
        //   82: goto -55 -> 27
        //   85: astore 5
        //   87: aload 4
        //   89: invokevirtual 62	android/os/Parcel:recycle	()V
        //   92: aload_3
        //   93: invokevirtual 62	android/os/Parcel:recycle	()V
        //   96: aload 5
        //   98: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	99	0	this	a
        //   0	99	1	paramhw	hw
        //   0	99	2	paramBoolean	boolean
        //   3	90	3	localParcel1	Parcel
        //   7	81	4	localParcel2	Parcel
        //   85	12	5	localObject	Object
        //   25	56	6	localIBinder	IBinder
        //   34	11	7	i	int
        // Exception table:
        //   from	to	target	type
        //   9	15	85	finally
        //   19	27	85	finally
        //   27	33	85	finally
        //   43	69	85	finally
      }
      
      public IBinder asBinder()
      {
        return this.lb;
      }
      
      /* Error */
      public void b(hw paramhw)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_3
        //   8: aload_2
        //   9: ldc 27
        //   11: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_1
        //   15: ifnull +44 -> 59
        //   18: aload_1
        //   19: invokeinterface 47 1 0
        //   24: astore 5
        //   26: aload_2
        //   27: aload 5
        //   29: invokevirtual 50	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   32: aload_0
        //   33: getfield 15	com/google/android/gms/internal/hv$a$a:lb	Landroid/os/IBinder;
        //   36: iconst_3
        //   37: aload_2
        //   38: aload_3
        //   39: iconst_0
        //   40: invokeinterface 56 5 0
        //   45: pop
        //   46: aload_3
        //   47: invokevirtual 59	android/os/Parcel:readException	()V
        //   50: aload_3
        //   51: invokevirtual 62	android/os/Parcel:recycle	()V
        //   54: aload_2
        //   55: invokevirtual 62	android/os/Parcel:recycle	()V
        //   58: return
        //   59: aconst_null
        //   60: astore 5
        //   62: goto -36 -> 26
        //   65: astore 4
        //   67: aload_3
        //   68: invokevirtual 62	android/os/Parcel:recycle	()V
        //   71: aload_2
        //   72: invokevirtual 62	android/os/Parcel:recycle	()V
        //   75: aload 4
        //   77: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	78	0	this	a
        //   0	78	1	paramhw	hw
        //   3	69	2	localParcel1	Parcel
        //   7	61	3	localParcel2	Parcel
        //   65	11	4	localObject	Object
        //   24	37	5	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   8	14	65	finally
        //   18	26	65	finally
        //   26	50	65	finally
      }
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.hv
 * JD-Core Version:    0.7.0.1
 */