package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.google.android.gms.dynamic.c;
import com.google.android.gms.dynamic.c.a;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.dynamic.d.a;
import com.google.android.gms.wallet.fragment.WalletFragmentOptions;

public abstract interface ou
  extends IInterface
{
  public abstract or a(d paramd, c paramc, WalletFragmentOptions paramWalletFragmentOptions, os paramos)
    throws RemoteException;
  
  public static abstract class a
    extends Binder
    implements ou
  {
    public static ou bM(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.wallet.internal.IWalletDynamiteCreator");
      if ((localIInterface != null) && ((localIInterface instanceof ou))) {
        return (ou)localIInterface;
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
        paramParcel2.writeString("com.google.android.gms.wallet.internal.IWalletDynamiteCreator");
        return true;
      }
      paramParcel1.enforceInterface("com.google.android.gms.wallet.internal.IWalletDynamiteCreator");
      d locald = d.a.am(paramParcel1.readStrongBinder());
      c localc = c.a.al(paramParcel1.readStrongBinder());
      if (paramParcel1.readInt() != 0) {}
      for (WalletFragmentOptions localWalletFragmentOptions = (WalletFragmentOptions)WalletFragmentOptions.CREATOR.createFromParcel(paramParcel1);; localWalletFragmentOptions = null)
      {
        or localor = a(locald, localc, localWalletFragmentOptions, os.a.bK(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        IBinder localIBinder = null;
        if (localor != null) {
          localIBinder = localor.asBinder();
        }
        paramParcel2.writeStrongBinder(localIBinder);
        return true;
      }
    }
    
    private static class a
      implements ou
    {
      private IBinder lb;
      
      a(IBinder paramIBinder)
      {
        this.lb = paramIBinder;
      }
      
      /* Error */
      public or a(d paramd, c paramc, WalletFragmentOptions paramWalletFragmentOptions, os paramos)
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
        //   18: ifnull +122 -> 140
        //   21: aload_1
        //   22: invokeinterface 37 1 0
        //   27: astore 8
        //   29: aload 5
        //   31: aload 8
        //   33: invokevirtual 40	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload_2
        //   37: ifnull +109 -> 146
        //   40: aload_2
        //   41: invokeinterface 43 1 0
        //   46: astore 9
        //   48: aload 5
        //   50: aload 9
        //   52: invokevirtual 40	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   55: aload_3
        //   56: ifnull +96 -> 152
        //   59: aload 5
        //   61: iconst_1
        //   62: invokevirtual 47	android/os/Parcel:writeInt	(I)V
        //   65: aload_3
        //   66: aload 5
        //   68: iconst_0
        //   69: invokevirtual 53	com/google/android/gms/wallet/fragment/WalletFragmentOptions:writeToParcel	(Landroid/os/Parcel;I)V
        //   72: aconst_null
        //   73: astore 10
        //   75: aload 4
        //   77: ifnull +12 -> 89
        //   80: aload 4
        //   82: invokeinterface 56 1 0
        //   87: astore 10
        //   89: aload 5
        //   91: aload 10
        //   93: invokevirtual 40	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   96: aload_0
        //   97: getfield 15	com/google/android/gms/internal/ou$a$a:lb	Landroid/os/IBinder;
        //   100: iconst_1
        //   101: aload 5
        //   103: aload 6
        //   105: iconst_0
        //   106: invokeinterface 62 5 0
        //   111: pop
        //   112: aload 6
        //   114: invokevirtual 65	android/os/Parcel:readException	()V
        //   117: aload 6
        //   119: invokevirtual 68	android/os/Parcel:readStrongBinder	()Landroid/os/IBinder;
        //   122: invokestatic 74	com/google/android/gms/internal/or$a:bJ	(Landroid/os/IBinder;)Lcom/google/android/gms/internal/or;
        //   125: astore 12
        //   127: aload 6
        //   129: invokevirtual 77	android/os/Parcel:recycle	()V
        //   132: aload 5
        //   134: invokevirtual 77	android/os/Parcel:recycle	()V
        //   137: aload 12
        //   139: areturn
        //   140: aconst_null
        //   141: astore 8
        //   143: goto -114 -> 29
        //   146: aconst_null
        //   147: astore 9
        //   149: goto -101 -> 48
        //   152: aload 5
        //   154: iconst_0
        //   155: invokevirtual 47	android/os/Parcel:writeInt	(I)V
        //   158: goto -86 -> 72
        //   161: astore 7
        //   163: aload 6
        //   165: invokevirtual 77	android/os/Parcel:recycle	()V
        //   168: aload 5
        //   170: invokevirtual 77	android/os/Parcel:recycle	()V
        //   173: aload 7
        //   175: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	176	0	this	a
        //   0	176	1	paramd	d
        //   0	176	2	paramc	c
        //   0	176	3	paramWalletFragmentOptions	WalletFragmentOptions
        //   0	176	4	paramos	os
        //   3	166	5	localParcel1	Parcel
        //   8	156	6	localParcel2	Parcel
        //   161	13	7	localObject	Object
        //   27	115	8	localIBinder1	IBinder
        //   46	102	9	localIBinder2	IBinder
        //   73	19	10	localIBinder3	IBinder
        //   125	13	12	localor	or
        // Exception table:
        //   from	to	target	type
        //   10	17	161	finally
        //   21	29	161	finally
        //   29	36	161	finally
        //   40	48	161	finally
        //   48	55	161	finally
        //   59	72	161	finally
        //   80	89	161	finally
        //   89	127	161	finally
        //   152	158	161	finally
      }
      
      public IBinder asBinder()
      {
        return this.lb;
      }
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.ou
 * JD-Core Version:    0.7.0.1
 */