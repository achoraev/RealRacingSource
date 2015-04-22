package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.dynamic.d.a;

public abstract interface cu
  extends IInterface
{
  public abstract void a(d paramd, av paramav, String paramString, cv paramcv)
    throws RemoteException;
  
  public abstract void a(d paramd, av paramav, String paramString1, String paramString2, cv paramcv)
    throws RemoteException;
  
  public abstract void a(d paramd, ay paramay, av paramav, String paramString, cv paramcv)
    throws RemoteException;
  
  public abstract void a(d paramd, ay paramay, av paramav, String paramString1, String paramString2, cv paramcv)
    throws RemoteException;
  
  public abstract void destroy()
    throws RemoteException;
  
  public abstract d getView()
    throws RemoteException;
  
  public abstract void pause()
    throws RemoteException;
  
  public abstract void resume()
    throws RemoteException;
  
  public abstract void showInterstitial()
    throws RemoteException;
  
  public static abstract class a
    extends Binder
    implements cu
  {
    public a()
    {
      attachInterface(this, "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
    }
    
    public static cu m(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
      if ((localIInterface != null) && ((localIInterface instanceof cu))) {
        return (cu)localIInterface;
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
        paramParcel2.writeString("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
        return true;
      case 1: 
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
        d locald5 = d.a.am(paramParcel1.readStrongBinder());
        ay localay2;
        if (paramParcel1.readInt() != 0)
        {
          localay2 = ay.CREATOR.c(paramParcel1);
          if (paramParcel1.readInt() == 0) {
            break label190;
          }
        }
        for (av localav4 = av.CREATOR.b(paramParcel1);; localav4 = null)
        {
          a(locald5, localay2, localav4, paramParcel1.readString(), cv.a.n(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
          localay2 = null;
          break;
        }
      case 2: 
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
        d locald4 = getView();
        paramParcel2.writeNoException();
        IBinder localIBinder = null;
        if (locald4 != null) {
          localIBinder = locald4.asBinder();
        }
        paramParcel2.writeStrongBinder(localIBinder);
        return true;
      case 3: 
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
        d locald3 = d.a.am(paramParcel1.readStrongBinder());
        int i = paramParcel1.readInt();
        av localav3 = null;
        if (i != 0) {
          localav3 = av.CREATOR.b(paramParcel1);
        }
        a(locald3, localav3, paramParcel1.readString(), cv.a.n(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 4: 
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
        showInterstitial();
        paramParcel2.writeNoException();
        return true;
      case 5: 
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
        destroy();
        paramParcel2.writeNoException();
        return true;
      case 6: 
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
        d locald2 = d.a.am(paramParcel1.readStrongBinder());
        ay localay1;
        if (paramParcel1.readInt() != 0)
        {
          localay1 = ay.CREATOR.c(paramParcel1);
          if (paramParcel1.readInt() == 0) {
            break label416;
          }
        }
        for (av localav2 = av.CREATOR.b(paramParcel1);; localav2 = null)
        {
          a(locald2, localay1, localav2, paramParcel1.readString(), paramParcel1.readString(), cv.a.n(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
          localay1 = null;
          break;
        }
      case 7: 
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
        d locald1 = d.a.am(paramParcel1.readStrongBinder());
        if (paramParcel1.readInt() != 0) {}
        for (av localav1 = av.CREATOR.b(paramParcel1);; localav1 = null)
        {
          a(locald1, localav1, paramParcel1.readString(), paramParcel1.readString(), cv.a.n(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        }
      case 8: 
        label190:
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
        label416:
        pause();
        paramParcel2.writeNoException();
        return true;
      }
      paramParcel1.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
      resume();
      paramParcel2.writeNoException();
      return true;
    }
    
    private static class a
      implements cu
    {
      private IBinder lb;
      
      a(IBinder paramIBinder)
      {
        this.lb = paramIBinder;
      }
      
      /* Error */
      public void a(d paramd, av paramav, String paramString, cv paramcv)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 5
        //   5: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 6
        //   10: aload 5
        //   12: ldc 27
        //   14: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +97 -> 115
        //   21: aload_1
        //   22: invokeinterface 37 1 0
        //   27: astore 8
        //   29: aload 5
        //   31: aload 8
        //   33: invokevirtual 40	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload_2
        //   37: ifnull +84 -> 121
        //   40: aload 5
        //   42: iconst_1
        //   43: invokevirtual 44	android/os/Parcel:writeInt	(I)V
        //   46: aload_2
        //   47: aload 5
        //   49: iconst_0
        //   50: invokevirtual 50	com/google/android/gms/internal/av:writeToParcel	(Landroid/os/Parcel;I)V
        //   53: aload 5
        //   55: aload_3
        //   56: invokevirtual 53	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   59: aconst_null
        //   60: astore 9
        //   62: aload 4
        //   64: ifnull +12 -> 76
        //   67: aload 4
        //   69: invokeinterface 56 1 0
        //   74: astore 9
        //   76: aload 5
        //   78: aload 9
        //   80: invokevirtual 40	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   83: aload_0
        //   84: getfield 15	com/google/android/gms/internal/cu$a$a:lb	Landroid/os/IBinder;
        //   87: iconst_3
        //   88: aload 5
        //   90: aload 6
        //   92: iconst_0
        //   93: invokeinterface 62 5 0
        //   98: pop
        //   99: aload 6
        //   101: invokevirtual 65	android/os/Parcel:readException	()V
        //   104: aload 6
        //   106: invokevirtual 68	android/os/Parcel:recycle	()V
        //   109: aload 5
        //   111: invokevirtual 68	android/os/Parcel:recycle	()V
        //   114: return
        //   115: aconst_null
        //   116: astore 8
        //   118: goto -89 -> 29
        //   121: aload 5
        //   123: iconst_0
        //   124: invokevirtual 44	android/os/Parcel:writeInt	(I)V
        //   127: goto -74 -> 53
        //   130: astore 7
        //   132: aload 6
        //   134: invokevirtual 68	android/os/Parcel:recycle	()V
        //   137: aload 5
        //   139: invokevirtual 68	android/os/Parcel:recycle	()V
        //   142: aload 7
        //   144: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	145	0	this	a
        //   0	145	1	paramd	d
        //   0	145	2	paramav	av
        //   0	145	3	paramString	String
        //   0	145	4	paramcv	cv
        //   3	135	5	localParcel1	Parcel
        //   8	125	6	localParcel2	Parcel
        //   130	13	7	localObject	Object
        //   27	90	8	localIBinder1	IBinder
        //   60	19	9	localIBinder2	IBinder
        // Exception table:
        //   from	to	target	type
        //   10	17	130	finally
        //   21	29	130	finally
        //   29	36	130	finally
        //   40	53	130	finally
        //   53	59	130	finally
        //   67	76	130	finally
        //   76	104	130	finally
        //   121	127	130	finally
      }
      
      /* Error */
      public void a(d paramd, av paramav, String paramString1, String paramString2, cv paramcv)
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
        //   18: ifnull +105 -> 123
        //   21: aload_1
        //   22: invokeinterface 37 1 0
        //   27: astore 9
        //   29: aload 6
        //   31: aload 9
        //   33: invokevirtual 40	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload_2
        //   37: ifnull +92 -> 129
        //   40: aload 6
        //   42: iconst_1
        //   43: invokevirtual 44	android/os/Parcel:writeInt	(I)V
        //   46: aload_2
        //   47: aload 6
        //   49: iconst_0
        //   50: invokevirtual 50	com/google/android/gms/internal/av:writeToParcel	(Landroid/os/Parcel;I)V
        //   53: aload 6
        //   55: aload_3
        //   56: invokevirtual 53	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   59: aload 6
        //   61: aload 4
        //   63: invokevirtual 53	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   66: aconst_null
        //   67: astore 10
        //   69: aload 5
        //   71: ifnull +12 -> 83
        //   74: aload 5
        //   76: invokeinterface 56 1 0
        //   81: astore 10
        //   83: aload 6
        //   85: aload 10
        //   87: invokevirtual 40	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   90: aload_0
        //   91: getfield 15	com/google/android/gms/internal/cu$a$a:lb	Landroid/os/IBinder;
        //   94: bipush 7
        //   96: aload 6
        //   98: aload 7
        //   100: iconst_0
        //   101: invokeinterface 62 5 0
        //   106: pop
        //   107: aload 7
        //   109: invokevirtual 65	android/os/Parcel:readException	()V
        //   112: aload 7
        //   114: invokevirtual 68	android/os/Parcel:recycle	()V
        //   117: aload 6
        //   119: invokevirtual 68	android/os/Parcel:recycle	()V
        //   122: return
        //   123: aconst_null
        //   124: astore 9
        //   126: goto -97 -> 29
        //   129: aload 6
        //   131: iconst_0
        //   132: invokevirtual 44	android/os/Parcel:writeInt	(I)V
        //   135: goto -82 -> 53
        //   138: astore 8
        //   140: aload 7
        //   142: invokevirtual 68	android/os/Parcel:recycle	()V
        //   145: aload 6
        //   147: invokevirtual 68	android/os/Parcel:recycle	()V
        //   150: aload 8
        //   152: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	153	0	this	a
        //   0	153	1	paramd	d
        //   0	153	2	paramav	av
        //   0	153	3	paramString1	String
        //   0	153	4	paramString2	String
        //   0	153	5	paramcv	cv
        //   3	143	6	localParcel1	Parcel
        //   8	133	7	localParcel2	Parcel
        //   138	13	8	localObject	Object
        //   27	98	9	localIBinder1	IBinder
        //   67	19	10	localIBinder2	IBinder
        // Exception table:
        //   from	to	target	type
        //   10	17	138	finally
        //   21	29	138	finally
        //   29	36	138	finally
        //   40	53	138	finally
        //   53	66	138	finally
        //   74	83	138	finally
        //   83	112	138	finally
        //   129	135	138	finally
      }
      
      public void a(d paramd, ay paramay, av paramav, String paramString, cv paramcv)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        label163:
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
            IBinder localIBinder1;
            if (paramd != null)
            {
              localIBinder1 = paramd.asBinder();
              localParcel1.writeStrongBinder(localIBinder1);
              if (paramay != null)
              {
                localParcel1.writeInt(1);
                paramay.writeToParcel(localParcel1, 0);
                if (paramav == null) {
                  break label163;
                }
                localParcel1.writeInt(1);
                paramav.writeToParcel(localParcel1, 0);
                localParcel1.writeString(paramString);
                IBinder localIBinder2 = null;
                if (paramcv != null) {
                  localIBinder2 = paramcv.asBinder();
                }
                localParcel1.writeStrongBinder(localIBinder2);
                this.lb.transact(1, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localIBinder1 = null;
              continue;
            }
            localParcel1.writeInt(0);
            continue;
            localParcel1.writeInt(0);
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void a(d paramd, ay paramay, av paramav, String paramString1, String paramString2, cv paramcv)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        label171:
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
            IBinder localIBinder1;
            if (paramd != null)
            {
              localIBinder1 = paramd.asBinder();
              localParcel1.writeStrongBinder(localIBinder1);
              if (paramay != null)
              {
                localParcel1.writeInt(1);
                paramay.writeToParcel(localParcel1, 0);
                if (paramav == null) {
                  break label171;
                }
                localParcel1.writeInt(1);
                paramav.writeToParcel(localParcel1, 0);
                localParcel1.writeString(paramString1);
                localParcel1.writeString(paramString2);
                IBinder localIBinder2 = null;
                if (paramcv != null) {
                  localIBinder2 = paramcv.asBinder();
                }
                localParcel1.writeStrongBinder(localIBinder2);
                this.lb.transact(6, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localIBinder1 = null;
              continue;
            }
            localParcel1.writeInt(0);
            continue;
            localParcel1.writeInt(0);
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public IBinder asBinder()
      {
        return this.lb;
      }
      
      public void destroy()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
          this.lb.transact(5, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public d getView()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
          this.lb.transact(2, localParcel1, localParcel2, 0);
          localParcel2.readException();
          d locald = d.a.am(localParcel2.readStrongBinder());
          return locald;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void pause()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
          this.lb.transact(8, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void resume()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
          this.lb.transact(9, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void showInterstitial()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
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
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.cu
 * JD-Core Version:    0.7.0.1
 */