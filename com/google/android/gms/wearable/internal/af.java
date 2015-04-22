package com.google.android.gms.wearable.internal;

import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.c;

public abstract interface af
  extends IInterface
{
  public abstract void a(ad paramad)
    throws RemoteException;
  
  public abstract void a(ad paramad, Uri paramUri)
    throws RemoteException;
  
  public abstract void a(ad paramad, Asset paramAsset)
    throws RemoteException;
  
  public abstract void a(ad paramad, PutDataRequest paramPutDataRequest)
    throws RemoteException;
  
  public abstract void a(ad paramad, c paramc)
    throws RemoteException;
  
  public abstract void a(ad paramad, aq paramaq)
    throws RemoteException;
  
  public abstract void a(ad paramad, b paramb)
    throws RemoteException;
  
  public abstract void a(ad paramad, String paramString)
    throws RemoteException;
  
  public abstract void a(ad paramad, String paramString1, String paramString2, byte[] paramArrayOfByte)
    throws RemoteException;
  
  public abstract void b(ad paramad)
    throws RemoteException;
  
  public abstract void b(ad paramad, Uri paramUri)
    throws RemoteException;
  
  public abstract void b(ad paramad, c paramc)
    throws RemoteException;
  
  public abstract void b(ad paramad, String paramString)
    throws RemoteException;
  
  public abstract void c(ad paramad)
    throws RemoteException;
  
  public abstract void c(ad paramad, Uri paramUri)
    throws RemoteException;
  
  public abstract void c(ad paramad, String paramString)
    throws RemoteException;
  
  public abstract void d(ad paramad)
    throws RemoteException;
  
  public abstract void e(ad paramad)
    throws RemoteException;
  
  public abstract void f(ad paramad)
    throws RemoteException;
  
  public abstract void g(ad paramad)
    throws RemoteException;
  
  public abstract void h(ad paramad)
    throws RemoteException;
  
  public abstract void i(ad paramad)
    throws RemoteException;
  
  public static abstract class a
    extends Binder
    implements af
  {
    public static af bT(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.wearable.internal.IWearableService");
      if ((localIInterface != null) && ((localIInterface instanceof af))) {
        return (af)localIInterface;
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
        paramParcel2.writeString("com.google.android.gms.wearable.internal.IWearableService");
        return true;
      case 20: 
        paramParcel1.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
        ad localad9 = ad.a.bR(paramParcel1.readStrongBinder());
        int i4 = paramParcel1.readInt();
        c localc2 = null;
        if (i4 != 0) {
          localc2 = (c)c.CREATOR.createFromParcel(paramParcel1);
        }
        a(localad9, localc2);
        paramParcel2.writeNoException();
        return true;
      case 21: 
        paramParcel1.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
        a(ad.a.bR(paramParcel1.readStrongBinder()), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 22: 
        paramParcel1.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
        a(ad.a.bR(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 23: 
        paramParcel1.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
        b(ad.a.bR(paramParcel1.readStrongBinder()), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 24: 
        paramParcel1.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
        c(ad.a.bR(paramParcel1.readStrongBinder()), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 6: 
        paramParcel1.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
        ad localad8 = ad.a.bR(paramParcel1.readStrongBinder());
        int i3 = paramParcel1.readInt();
        PutDataRequest localPutDataRequest = null;
        if (i3 != 0) {
          localPutDataRequest = (PutDataRequest)PutDataRequest.CREATOR.createFromParcel(paramParcel1);
        }
        a(localad8, localPutDataRequest);
        paramParcel2.writeNoException();
        return true;
      case 7: 
        paramParcel1.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
        ad localad7 = ad.a.bR(paramParcel1.readStrongBinder());
        int i2 = paramParcel1.readInt();
        Uri localUri3 = null;
        if (i2 != 0) {
          localUri3 = (Uri)Uri.CREATOR.createFromParcel(paramParcel1);
        }
        a(localad7, localUri3);
        paramParcel2.writeNoException();
        return true;
      case 8: 
        paramParcel1.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
        b(ad.a.bR(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 9: 
        paramParcel1.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
        ad localad6 = ad.a.bR(paramParcel1.readStrongBinder());
        int i1 = paramParcel1.readInt();
        Uri localUri2 = null;
        if (i1 != 0) {
          localUri2 = (Uri)Uri.CREATOR.createFromParcel(paramParcel1);
        }
        b(localad6, localUri2);
        paramParcel2.writeNoException();
        return true;
      case 11: 
        paramParcel1.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
        ad localad5 = ad.a.bR(paramParcel1.readStrongBinder());
        int n = paramParcel1.readInt();
        Uri localUri1 = null;
        if (n != 0) {
          localUri1 = (Uri)Uri.CREATOR.createFromParcel(paramParcel1);
        }
        c(localad5, localUri1);
        paramParcel2.writeNoException();
        return true;
      case 12: 
        paramParcel1.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
        a(ad.a.bR(paramParcel1.readStrongBinder()), paramParcel1.readString(), paramParcel1.readString(), paramParcel1.createByteArray());
        paramParcel2.writeNoException();
        return true;
      case 13: 
        paramParcel1.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
        ad localad4 = ad.a.bR(paramParcel1.readStrongBinder());
        int m = paramParcel1.readInt();
        Asset localAsset = null;
        if (m != 0) {
          localAsset = (Asset)Asset.CREATOR.createFromParcel(paramParcel1);
        }
        a(localad4, localAsset);
        paramParcel2.writeNoException();
        return true;
      case 14: 
        paramParcel1.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
        c(ad.a.bR(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 15: 
        paramParcel1.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
        d(ad.a.bR(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 16: 
        paramParcel1.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
        ad localad3 = ad.a.bR(paramParcel1.readStrongBinder());
        int k = paramParcel1.readInt();
        b localb = null;
        if (k != 0) {
          localb = (b)b.CREATOR.createFromParcel(paramParcel1);
        }
        a(localad3, localb);
        paramParcel2.writeNoException();
        return true;
      case 17: 
        paramParcel1.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
        ad localad2 = ad.a.bR(paramParcel1.readStrongBinder());
        int j = paramParcel1.readInt();
        aq localaq = null;
        if (j != 0) {
          localaq = (aq)aq.CREATOR.createFromParcel(paramParcel1);
        }
        a(localad2, localaq);
        paramParcel2.writeNoException();
        return true;
      case 18: 
        paramParcel1.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
        e(ad.a.bR(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 19: 
        paramParcel1.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
        f(ad.a.bR(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 2: 
        paramParcel1.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
        ad localad1 = ad.a.bR(paramParcel1.readStrongBinder());
        int i = paramParcel1.readInt();
        c localc1 = null;
        if (i != 0) {
          localc1 = (c)c.CREATOR.createFromParcel(paramParcel1);
        }
        b(localad1, localc1);
        paramParcel2.writeNoException();
        return true;
      case 3: 
        paramParcel1.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
        g(ad.a.bR(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 4: 
        paramParcel1.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
        h(ad.a.bR(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      }
      paramParcel1.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
      i(ad.a.bR(paramParcel1.readStrongBinder()));
      paramParcel2.writeNoException();
      return true;
    }
    
    private static class a
      implements af
    {
      private IBinder lb;
      
      a(IBinder paramIBinder)
      {
        this.lb = paramIBinder;
      }
      
      /* Error */
      public void a(ad paramad)
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
        //   15: ifnull +45 -> 60
        //   18: aload_1
        //   19: invokeinterface 37 1 0
        //   24: astore 5
        //   26: aload_2
        //   27: aload 5
        //   29: invokevirtual 40	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   32: aload_0
        //   33: getfield 15	com/google/android/gms/wearable/internal/af$a$a:lb	Landroid/os/IBinder;
        //   36: bipush 22
        //   38: aload_2
        //   39: aload_3
        //   40: iconst_0
        //   41: invokeinterface 46 5 0
        //   46: pop
        //   47: aload_3
        //   48: invokevirtual 49	android/os/Parcel:readException	()V
        //   51: aload_3
        //   52: invokevirtual 52	android/os/Parcel:recycle	()V
        //   55: aload_2
        //   56: invokevirtual 52	android/os/Parcel:recycle	()V
        //   59: return
        //   60: aconst_null
        //   61: astore 5
        //   63: goto -37 -> 26
        //   66: astore 4
        //   68: aload_3
        //   69: invokevirtual 52	android/os/Parcel:recycle	()V
        //   72: aload_2
        //   73: invokevirtual 52	android/os/Parcel:recycle	()V
        //   76: aload 4
        //   78: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	79	0	this	a
        //   0	79	1	paramad	ad
        //   3	70	2	localParcel1	Parcel
        //   7	62	3	localParcel2	Parcel
        //   66	11	4	localObject	Object
        //   24	38	5	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   8	14	66	finally
        //   18	26	66	finally
        //   26	51	66	finally
      }
      
      /* Error */
      public void a(ad paramad, Uri paramUri)
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
        //   20: invokeinterface 37 1 0
        //   25: astore 6
        //   27: aload_3
        //   28: aload 6
        //   30: invokevirtual 40	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   33: aload_2
        //   34: ifnull +51 -> 85
        //   37: aload_3
        //   38: iconst_1
        //   39: invokevirtual 57	android/os/Parcel:writeInt	(I)V
        //   42: aload_2
        //   43: aload_3
        //   44: iconst_0
        //   45: invokevirtual 63	android/net/Uri:writeToParcel	(Landroid/os/Parcel;I)V
        //   48: aload_0
        //   49: getfield 15	com/google/android/gms/wearable/internal/af$a$a:lb	Landroid/os/IBinder;
        //   52: bipush 7
        //   54: aload_3
        //   55: aload 4
        //   57: iconst_0
        //   58: invokeinterface 46 5 0
        //   63: pop
        //   64: aload 4
        //   66: invokevirtual 49	android/os/Parcel:readException	()V
        //   69: aload 4
        //   71: invokevirtual 52	android/os/Parcel:recycle	()V
        //   74: aload_3
        //   75: invokevirtual 52	android/os/Parcel:recycle	()V
        //   78: return
        //   79: aconst_null
        //   80: astore 6
        //   82: goto -55 -> 27
        //   85: aload_3
        //   86: iconst_0
        //   87: invokevirtual 57	android/os/Parcel:writeInt	(I)V
        //   90: goto -42 -> 48
        //   93: astore 5
        //   95: aload 4
        //   97: invokevirtual 52	android/os/Parcel:recycle	()V
        //   100: aload_3
        //   101: invokevirtual 52	android/os/Parcel:recycle	()V
        //   104: aload 5
        //   106: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	107	0	this	a
        //   0	107	1	paramad	ad
        //   0	107	2	paramUri	Uri
        //   3	98	3	localParcel1	Parcel
        //   7	89	4	localParcel2	Parcel
        //   93	12	5	localObject	Object
        //   25	56	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   9	15	93	finally
        //   19	27	93	finally
        //   27	33	93	finally
        //   37	48	93	finally
        //   48	69	93	finally
        //   85	90	93	finally
      }
      
      /* Error */
      public void a(ad paramad, Asset paramAsset)
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
        //   20: invokeinterface 37 1 0
        //   25: astore 6
        //   27: aload_3
        //   28: aload 6
        //   30: invokevirtual 40	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   33: aload_2
        //   34: ifnull +51 -> 85
        //   37: aload_3
        //   38: iconst_1
        //   39: invokevirtual 57	android/os/Parcel:writeInt	(I)V
        //   42: aload_2
        //   43: aload_3
        //   44: iconst_0
        //   45: invokevirtual 67	com/google/android/gms/wearable/Asset:writeToParcel	(Landroid/os/Parcel;I)V
        //   48: aload_0
        //   49: getfield 15	com/google/android/gms/wearable/internal/af$a$a:lb	Landroid/os/IBinder;
        //   52: bipush 13
        //   54: aload_3
        //   55: aload 4
        //   57: iconst_0
        //   58: invokeinterface 46 5 0
        //   63: pop
        //   64: aload 4
        //   66: invokevirtual 49	android/os/Parcel:readException	()V
        //   69: aload 4
        //   71: invokevirtual 52	android/os/Parcel:recycle	()V
        //   74: aload_3
        //   75: invokevirtual 52	android/os/Parcel:recycle	()V
        //   78: return
        //   79: aconst_null
        //   80: astore 6
        //   82: goto -55 -> 27
        //   85: aload_3
        //   86: iconst_0
        //   87: invokevirtual 57	android/os/Parcel:writeInt	(I)V
        //   90: goto -42 -> 48
        //   93: astore 5
        //   95: aload 4
        //   97: invokevirtual 52	android/os/Parcel:recycle	()V
        //   100: aload_3
        //   101: invokevirtual 52	android/os/Parcel:recycle	()V
        //   104: aload 5
        //   106: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	107	0	this	a
        //   0	107	1	paramad	ad
        //   0	107	2	paramAsset	Asset
        //   3	98	3	localParcel1	Parcel
        //   7	89	4	localParcel2	Parcel
        //   93	12	5	localObject	Object
        //   25	56	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   9	15	93	finally
        //   19	27	93	finally
        //   27	33	93	finally
        //   37	48	93	finally
        //   48	69	93	finally
        //   85	90	93	finally
      }
      
      /* Error */
      public void a(ad paramad, PutDataRequest paramPutDataRequest)
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
        //   20: invokeinterface 37 1 0
        //   25: astore 6
        //   27: aload_3
        //   28: aload 6
        //   30: invokevirtual 40	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   33: aload_2
        //   34: ifnull +51 -> 85
        //   37: aload_3
        //   38: iconst_1
        //   39: invokevirtual 57	android/os/Parcel:writeInt	(I)V
        //   42: aload_2
        //   43: aload_3
        //   44: iconst_0
        //   45: invokevirtual 71	com/google/android/gms/wearable/PutDataRequest:writeToParcel	(Landroid/os/Parcel;I)V
        //   48: aload_0
        //   49: getfield 15	com/google/android/gms/wearable/internal/af$a$a:lb	Landroid/os/IBinder;
        //   52: bipush 6
        //   54: aload_3
        //   55: aload 4
        //   57: iconst_0
        //   58: invokeinterface 46 5 0
        //   63: pop
        //   64: aload 4
        //   66: invokevirtual 49	android/os/Parcel:readException	()V
        //   69: aload 4
        //   71: invokevirtual 52	android/os/Parcel:recycle	()V
        //   74: aload_3
        //   75: invokevirtual 52	android/os/Parcel:recycle	()V
        //   78: return
        //   79: aconst_null
        //   80: astore 6
        //   82: goto -55 -> 27
        //   85: aload_3
        //   86: iconst_0
        //   87: invokevirtual 57	android/os/Parcel:writeInt	(I)V
        //   90: goto -42 -> 48
        //   93: astore 5
        //   95: aload 4
        //   97: invokevirtual 52	android/os/Parcel:recycle	()V
        //   100: aload_3
        //   101: invokevirtual 52	android/os/Parcel:recycle	()V
        //   104: aload 5
        //   106: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	107	0	this	a
        //   0	107	1	paramad	ad
        //   0	107	2	paramPutDataRequest	PutDataRequest
        //   3	98	3	localParcel1	Parcel
        //   7	89	4	localParcel2	Parcel
        //   93	12	5	localObject	Object
        //   25	56	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   9	15	93	finally
        //   19	27	93	finally
        //   27	33	93	finally
        //   37	48	93	finally
        //   48	69	93	finally
        //   85	90	93	finally
      }
      
      /* Error */
      public void a(ad paramad, c paramc)
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
        //   20: invokeinterface 37 1 0
        //   25: astore 6
        //   27: aload_3
        //   28: aload 6
        //   30: invokevirtual 40	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   33: aload_2
        //   34: ifnull +51 -> 85
        //   37: aload_3
        //   38: iconst_1
        //   39: invokevirtual 57	android/os/Parcel:writeInt	(I)V
        //   42: aload_2
        //   43: aload_3
        //   44: iconst_0
        //   45: invokevirtual 75	com/google/android/gms/wearable/c:writeToParcel	(Landroid/os/Parcel;I)V
        //   48: aload_0
        //   49: getfield 15	com/google/android/gms/wearable/internal/af$a$a:lb	Landroid/os/IBinder;
        //   52: bipush 20
        //   54: aload_3
        //   55: aload 4
        //   57: iconst_0
        //   58: invokeinterface 46 5 0
        //   63: pop
        //   64: aload 4
        //   66: invokevirtual 49	android/os/Parcel:readException	()V
        //   69: aload 4
        //   71: invokevirtual 52	android/os/Parcel:recycle	()V
        //   74: aload_3
        //   75: invokevirtual 52	android/os/Parcel:recycle	()V
        //   78: return
        //   79: aconst_null
        //   80: astore 6
        //   82: goto -55 -> 27
        //   85: aload_3
        //   86: iconst_0
        //   87: invokevirtual 57	android/os/Parcel:writeInt	(I)V
        //   90: goto -42 -> 48
        //   93: astore 5
        //   95: aload 4
        //   97: invokevirtual 52	android/os/Parcel:recycle	()V
        //   100: aload_3
        //   101: invokevirtual 52	android/os/Parcel:recycle	()V
        //   104: aload 5
        //   106: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	107	0	this	a
        //   0	107	1	paramad	ad
        //   0	107	2	paramc	c
        //   3	98	3	localParcel1	Parcel
        //   7	89	4	localParcel2	Parcel
        //   93	12	5	localObject	Object
        //   25	56	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   9	15	93	finally
        //   19	27	93	finally
        //   27	33	93	finally
        //   37	48	93	finally
        //   48	69	93	finally
        //   85	90	93	finally
      }
      
      /* Error */
      public void a(ad paramad, aq paramaq)
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
        //   20: invokeinterface 37 1 0
        //   25: astore 6
        //   27: aload_3
        //   28: aload 6
        //   30: invokevirtual 40	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   33: aload_2
        //   34: ifnull +51 -> 85
        //   37: aload_3
        //   38: iconst_1
        //   39: invokevirtual 57	android/os/Parcel:writeInt	(I)V
        //   42: aload_2
        //   43: aload_3
        //   44: iconst_0
        //   45: invokevirtual 79	com/google/android/gms/wearable/internal/aq:writeToParcel	(Landroid/os/Parcel;I)V
        //   48: aload_0
        //   49: getfield 15	com/google/android/gms/wearable/internal/af$a$a:lb	Landroid/os/IBinder;
        //   52: bipush 17
        //   54: aload_3
        //   55: aload 4
        //   57: iconst_0
        //   58: invokeinterface 46 5 0
        //   63: pop
        //   64: aload 4
        //   66: invokevirtual 49	android/os/Parcel:readException	()V
        //   69: aload 4
        //   71: invokevirtual 52	android/os/Parcel:recycle	()V
        //   74: aload_3
        //   75: invokevirtual 52	android/os/Parcel:recycle	()V
        //   78: return
        //   79: aconst_null
        //   80: astore 6
        //   82: goto -55 -> 27
        //   85: aload_3
        //   86: iconst_0
        //   87: invokevirtual 57	android/os/Parcel:writeInt	(I)V
        //   90: goto -42 -> 48
        //   93: astore 5
        //   95: aload 4
        //   97: invokevirtual 52	android/os/Parcel:recycle	()V
        //   100: aload_3
        //   101: invokevirtual 52	android/os/Parcel:recycle	()V
        //   104: aload 5
        //   106: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	107	0	this	a
        //   0	107	1	paramad	ad
        //   0	107	2	paramaq	aq
        //   3	98	3	localParcel1	Parcel
        //   7	89	4	localParcel2	Parcel
        //   93	12	5	localObject	Object
        //   25	56	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   9	15	93	finally
        //   19	27	93	finally
        //   27	33	93	finally
        //   37	48	93	finally
        //   48	69	93	finally
        //   85	90	93	finally
      }
      
      /* Error */
      public void a(ad paramad, b paramb)
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
        //   20: invokeinterface 37 1 0
        //   25: astore 6
        //   27: aload_3
        //   28: aload 6
        //   30: invokevirtual 40	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   33: aload_2
        //   34: ifnull +51 -> 85
        //   37: aload_3
        //   38: iconst_1
        //   39: invokevirtual 57	android/os/Parcel:writeInt	(I)V
        //   42: aload_2
        //   43: aload_3
        //   44: iconst_0
        //   45: invokevirtual 83	com/google/android/gms/wearable/internal/b:writeToParcel	(Landroid/os/Parcel;I)V
        //   48: aload_0
        //   49: getfield 15	com/google/android/gms/wearable/internal/af$a$a:lb	Landroid/os/IBinder;
        //   52: bipush 16
        //   54: aload_3
        //   55: aload 4
        //   57: iconst_0
        //   58: invokeinterface 46 5 0
        //   63: pop
        //   64: aload 4
        //   66: invokevirtual 49	android/os/Parcel:readException	()V
        //   69: aload 4
        //   71: invokevirtual 52	android/os/Parcel:recycle	()V
        //   74: aload_3
        //   75: invokevirtual 52	android/os/Parcel:recycle	()V
        //   78: return
        //   79: aconst_null
        //   80: astore 6
        //   82: goto -55 -> 27
        //   85: aload_3
        //   86: iconst_0
        //   87: invokevirtual 57	android/os/Parcel:writeInt	(I)V
        //   90: goto -42 -> 48
        //   93: astore 5
        //   95: aload 4
        //   97: invokevirtual 52	android/os/Parcel:recycle	()V
        //   100: aload_3
        //   101: invokevirtual 52	android/os/Parcel:recycle	()V
        //   104: aload 5
        //   106: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	107	0	this	a
        //   0	107	1	paramad	ad
        //   0	107	2	paramb	b
        //   3	98	3	localParcel1	Parcel
        //   7	89	4	localParcel2	Parcel
        //   93	12	5	localObject	Object
        //   25	56	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   9	15	93	finally
        //   19	27	93	finally
        //   27	33	93	finally
        //   37	48	93	finally
        //   48	69	93	finally
        //   85	90	93	finally
      }
      
      /* Error */
      public void a(ad paramad, String paramString)
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
        //   16: ifnull +53 -> 69
        //   19: aload_1
        //   20: invokeinterface 37 1 0
        //   25: astore 6
        //   27: aload_3
        //   28: aload 6
        //   30: invokevirtual 40	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   33: aload_3
        //   34: aload_2
        //   35: invokevirtual 87	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   38: aload_0
        //   39: getfield 15	com/google/android/gms/wearable/internal/af$a$a:lb	Landroid/os/IBinder;
        //   42: bipush 21
        //   44: aload_3
        //   45: aload 4
        //   47: iconst_0
        //   48: invokeinterface 46 5 0
        //   53: pop
        //   54: aload 4
        //   56: invokevirtual 49	android/os/Parcel:readException	()V
        //   59: aload 4
        //   61: invokevirtual 52	android/os/Parcel:recycle	()V
        //   64: aload_3
        //   65: invokevirtual 52	android/os/Parcel:recycle	()V
        //   68: return
        //   69: aconst_null
        //   70: astore 6
        //   72: goto -45 -> 27
        //   75: astore 5
        //   77: aload 4
        //   79: invokevirtual 52	android/os/Parcel:recycle	()V
        //   82: aload_3
        //   83: invokevirtual 52	android/os/Parcel:recycle	()V
        //   86: aload 5
        //   88: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	89	0	this	a
        //   0	89	1	paramad	ad
        //   0	89	2	paramString	String
        //   3	80	3	localParcel1	Parcel
        //   7	71	4	localParcel2	Parcel
        //   75	12	5	localObject	Object
        //   25	46	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   9	15	75	finally
        //   19	27	75	finally
        //   27	59	75	finally
      }
      
      /* Error */
      public void a(ad paramad, String paramString1, String paramString2, byte[] paramArrayOfByte)
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
        //   18: ifnull +70 -> 88
        //   21: aload_1
        //   22: invokeinterface 37 1 0
        //   27: astore 8
        //   29: aload 5
        //   31: aload 8
        //   33: invokevirtual 40	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload 5
        //   38: aload_2
        //   39: invokevirtual 87	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   42: aload 5
        //   44: aload_3
        //   45: invokevirtual 87	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   48: aload 5
        //   50: aload 4
        //   52: invokevirtual 92	android/os/Parcel:writeByteArray	([B)V
        //   55: aload_0
        //   56: getfield 15	com/google/android/gms/wearable/internal/af$a$a:lb	Landroid/os/IBinder;
        //   59: bipush 12
        //   61: aload 5
        //   63: aload 6
        //   65: iconst_0
        //   66: invokeinterface 46 5 0
        //   71: pop
        //   72: aload 6
        //   74: invokevirtual 49	android/os/Parcel:readException	()V
        //   77: aload 6
        //   79: invokevirtual 52	android/os/Parcel:recycle	()V
        //   82: aload 5
        //   84: invokevirtual 52	android/os/Parcel:recycle	()V
        //   87: return
        //   88: aconst_null
        //   89: astore 8
        //   91: goto -62 -> 29
        //   94: astore 7
        //   96: aload 6
        //   98: invokevirtual 52	android/os/Parcel:recycle	()V
        //   101: aload 5
        //   103: invokevirtual 52	android/os/Parcel:recycle	()V
        //   106: aload 7
        //   108: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	109	0	this	a
        //   0	109	1	paramad	ad
        //   0	109	2	paramString1	String
        //   0	109	3	paramString2	String
        //   0	109	4	paramArrayOfByte	byte[]
        //   3	99	5	localParcel1	Parcel
        //   8	89	6	localParcel2	Parcel
        //   94	13	7	localObject	Object
        //   27	63	8	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   10	17	94	finally
        //   21	29	94	finally
        //   29	77	94	finally
      }
      
      public IBinder asBinder()
      {
        return this.lb;
      }
      
      /* Error */
      public void b(ad paramad)
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
        //   15: ifnull +45 -> 60
        //   18: aload_1
        //   19: invokeinterface 37 1 0
        //   24: astore 5
        //   26: aload_2
        //   27: aload 5
        //   29: invokevirtual 40	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   32: aload_0
        //   33: getfield 15	com/google/android/gms/wearable/internal/af$a$a:lb	Landroid/os/IBinder;
        //   36: bipush 8
        //   38: aload_2
        //   39: aload_3
        //   40: iconst_0
        //   41: invokeinterface 46 5 0
        //   46: pop
        //   47: aload_3
        //   48: invokevirtual 49	android/os/Parcel:readException	()V
        //   51: aload_3
        //   52: invokevirtual 52	android/os/Parcel:recycle	()V
        //   55: aload_2
        //   56: invokevirtual 52	android/os/Parcel:recycle	()V
        //   59: return
        //   60: aconst_null
        //   61: astore 5
        //   63: goto -37 -> 26
        //   66: astore 4
        //   68: aload_3
        //   69: invokevirtual 52	android/os/Parcel:recycle	()V
        //   72: aload_2
        //   73: invokevirtual 52	android/os/Parcel:recycle	()V
        //   76: aload 4
        //   78: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	79	0	this	a
        //   0	79	1	paramad	ad
        //   3	70	2	localParcel1	Parcel
        //   7	62	3	localParcel2	Parcel
        //   66	11	4	localObject	Object
        //   24	38	5	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   8	14	66	finally
        //   18	26	66	finally
        //   26	51	66	finally
      }
      
      /* Error */
      public void b(ad paramad, Uri paramUri)
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
        //   20: invokeinterface 37 1 0
        //   25: astore 6
        //   27: aload_3
        //   28: aload 6
        //   30: invokevirtual 40	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   33: aload_2
        //   34: ifnull +51 -> 85
        //   37: aload_3
        //   38: iconst_1
        //   39: invokevirtual 57	android/os/Parcel:writeInt	(I)V
        //   42: aload_2
        //   43: aload_3
        //   44: iconst_0
        //   45: invokevirtual 63	android/net/Uri:writeToParcel	(Landroid/os/Parcel;I)V
        //   48: aload_0
        //   49: getfield 15	com/google/android/gms/wearable/internal/af$a$a:lb	Landroid/os/IBinder;
        //   52: bipush 9
        //   54: aload_3
        //   55: aload 4
        //   57: iconst_0
        //   58: invokeinterface 46 5 0
        //   63: pop
        //   64: aload 4
        //   66: invokevirtual 49	android/os/Parcel:readException	()V
        //   69: aload 4
        //   71: invokevirtual 52	android/os/Parcel:recycle	()V
        //   74: aload_3
        //   75: invokevirtual 52	android/os/Parcel:recycle	()V
        //   78: return
        //   79: aconst_null
        //   80: astore 6
        //   82: goto -55 -> 27
        //   85: aload_3
        //   86: iconst_0
        //   87: invokevirtual 57	android/os/Parcel:writeInt	(I)V
        //   90: goto -42 -> 48
        //   93: astore 5
        //   95: aload 4
        //   97: invokevirtual 52	android/os/Parcel:recycle	()V
        //   100: aload_3
        //   101: invokevirtual 52	android/os/Parcel:recycle	()V
        //   104: aload 5
        //   106: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	107	0	this	a
        //   0	107	1	paramad	ad
        //   0	107	2	paramUri	Uri
        //   3	98	3	localParcel1	Parcel
        //   7	89	4	localParcel2	Parcel
        //   93	12	5	localObject	Object
        //   25	56	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   9	15	93	finally
        //   19	27	93	finally
        //   27	33	93	finally
        //   37	48	93	finally
        //   48	69	93	finally
        //   85	90	93	finally
      }
      
      /* Error */
      public void b(ad paramad, c paramc)
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
        //   16: ifnull +62 -> 78
        //   19: aload_1
        //   20: invokeinterface 37 1 0
        //   25: astore 6
        //   27: aload_3
        //   28: aload 6
        //   30: invokevirtual 40	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   33: aload_2
        //   34: ifnull +50 -> 84
        //   37: aload_3
        //   38: iconst_1
        //   39: invokevirtual 57	android/os/Parcel:writeInt	(I)V
        //   42: aload_2
        //   43: aload_3
        //   44: iconst_0
        //   45: invokevirtual 75	com/google/android/gms/wearable/c:writeToParcel	(Landroid/os/Parcel;I)V
        //   48: aload_0
        //   49: getfield 15	com/google/android/gms/wearable/internal/af$a$a:lb	Landroid/os/IBinder;
        //   52: iconst_2
        //   53: aload_3
        //   54: aload 4
        //   56: iconst_0
        //   57: invokeinterface 46 5 0
        //   62: pop
        //   63: aload 4
        //   65: invokevirtual 49	android/os/Parcel:readException	()V
        //   68: aload 4
        //   70: invokevirtual 52	android/os/Parcel:recycle	()V
        //   73: aload_3
        //   74: invokevirtual 52	android/os/Parcel:recycle	()V
        //   77: return
        //   78: aconst_null
        //   79: astore 6
        //   81: goto -54 -> 27
        //   84: aload_3
        //   85: iconst_0
        //   86: invokevirtual 57	android/os/Parcel:writeInt	(I)V
        //   89: goto -41 -> 48
        //   92: astore 5
        //   94: aload 4
        //   96: invokevirtual 52	android/os/Parcel:recycle	()V
        //   99: aload_3
        //   100: invokevirtual 52	android/os/Parcel:recycle	()V
        //   103: aload 5
        //   105: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	106	0	this	a
        //   0	106	1	paramad	ad
        //   0	106	2	paramc	c
        //   3	97	3	localParcel1	Parcel
        //   7	88	4	localParcel2	Parcel
        //   92	12	5	localObject	Object
        //   25	55	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   9	15	92	finally
        //   19	27	92	finally
        //   27	33	92	finally
        //   37	48	92	finally
        //   48	68	92	finally
        //   84	89	92	finally
      }
      
      /* Error */
      public void b(ad paramad, String paramString)
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
        //   16: ifnull +53 -> 69
        //   19: aload_1
        //   20: invokeinterface 37 1 0
        //   25: astore 6
        //   27: aload_3
        //   28: aload 6
        //   30: invokevirtual 40	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   33: aload_3
        //   34: aload_2
        //   35: invokevirtual 87	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   38: aload_0
        //   39: getfield 15	com/google/android/gms/wearable/internal/af$a$a:lb	Landroid/os/IBinder;
        //   42: bipush 23
        //   44: aload_3
        //   45: aload 4
        //   47: iconst_0
        //   48: invokeinterface 46 5 0
        //   53: pop
        //   54: aload 4
        //   56: invokevirtual 49	android/os/Parcel:readException	()V
        //   59: aload 4
        //   61: invokevirtual 52	android/os/Parcel:recycle	()V
        //   64: aload_3
        //   65: invokevirtual 52	android/os/Parcel:recycle	()V
        //   68: return
        //   69: aconst_null
        //   70: astore 6
        //   72: goto -45 -> 27
        //   75: astore 5
        //   77: aload 4
        //   79: invokevirtual 52	android/os/Parcel:recycle	()V
        //   82: aload_3
        //   83: invokevirtual 52	android/os/Parcel:recycle	()V
        //   86: aload 5
        //   88: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	89	0	this	a
        //   0	89	1	paramad	ad
        //   0	89	2	paramString	String
        //   3	80	3	localParcel1	Parcel
        //   7	71	4	localParcel2	Parcel
        //   75	12	5	localObject	Object
        //   25	46	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   9	15	75	finally
        //   19	27	75	finally
        //   27	59	75	finally
      }
      
      /* Error */
      public void c(ad paramad)
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
        //   15: ifnull +45 -> 60
        //   18: aload_1
        //   19: invokeinterface 37 1 0
        //   24: astore 5
        //   26: aload_2
        //   27: aload 5
        //   29: invokevirtual 40	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   32: aload_0
        //   33: getfield 15	com/google/android/gms/wearable/internal/af$a$a:lb	Landroid/os/IBinder;
        //   36: bipush 14
        //   38: aload_2
        //   39: aload_3
        //   40: iconst_0
        //   41: invokeinterface 46 5 0
        //   46: pop
        //   47: aload_3
        //   48: invokevirtual 49	android/os/Parcel:readException	()V
        //   51: aload_3
        //   52: invokevirtual 52	android/os/Parcel:recycle	()V
        //   55: aload_2
        //   56: invokevirtual 52	android/os/Parcel:recycle	()V
        //   59: return
        //   60: aconst_null
        //   61: astore 5
        //   63: goto -37 -> 26
        //   66: astore 4
        //   68: aload_3
        //   69: invokevirtual 52	android/os/Parcel:recycle	()V
        //   72: aload_2
        //   73: invokevirtual 52	android/os/Parcel:recycle	()V
        //   76: aload 4
        //   78: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	79	0	this	a
        //   0	79	1	paramad	ad
        //   3	70	2	localParcel1	Parcel
        //   7	62	3	localParcel2	Parcel
        //   66	11	4	localObject	Object
        //   24	38	5	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   8	14	66	finally
        //   18	26	66	finally
        //   26	51	66	finally
      }
      
      /* Error */
      public void c(ad paramad, Uri paramUri)
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
        //   20: invokeinterface 37 1 0
        //   25: astore 6
        //   27: aload_3
        //   28: aload 6
        //   30: invokevirtual 40	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   33: aload_2
        //   34: ifnull +51 -> 85
        //   37: aload_3
        //   38: iconst_1
        //   39: invokevirtual 57	android/os/Parcel:writeInt	(I)V
        //   42: aload_2
        //   43: aload_3
        //   44: iconst_0
        //   45: invokevirtual 63	android/net/Uri:writeToParcel	(Landroid/os/Parcel;I)V
        //   48: aload_0
        //   49: getfield 15	com/google/android/gms/wearable/internal/af$a$a:lb	Landroid/os/IBinder;
        //   52: bipush 11
        //   54: aload_3
        //   55: aload 4
        //   57: iconst_0
        //   58: invokeinterface 46 5 0
        //   63: pop
        //   64: aload 4
        //   66: invokevirtual 49	android/os/Parcel:readException	()V
        //   69: aload 4
        //   71: invokevirtual 52	android/os/Parcel:recycle	()V
        //   74: aload_3
        //   75: invokevirtual 52	android/os/Parcel:recycle	()V
        //   78: return
        //   79: aconst_null
        //   80: astore 6
        //   82: goto -55 -> 27
        //   85: aload_3
        //   86: iconst_0
        //   87: invokevirtual 57	android/os/Parcel:writeInt	(I)V
        //   90: goto -42 -> 48
        //   93: astore 5
        //   95: aload 4
        //   97: invokevirtual 52	android/os/Parcel:recycle	()V
        //   100: aload_3
        //   101: invokevirtual 52	android/os/Parcel:recycle	()V
        //   104: aload 5
        //   106: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	107	0	this	a
        //   0	107	1	paramad	ad
        //   0	107	2	paramUri	Uri
        //   3	98	3	localParcel1	Parcel
        //   7	89	4	localParcel2	Parcel
        //   93	12	5	localObject	Object
        //   25	56	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   9	15	93	finally
        //   19	27	93	finally
        //   27	33	93	finally
        //   37	48	93	finally
        //   48	69	93	finally
        //   85	90	93	finally
      }
      
      /* Error */
      public void c(ad paramad, String paramString)
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
        //   16: ifnull +53 -> 69
        //   19: aload_1
        //   20: invokeinterface 37 1 0
        //   25: astore 6
        //   27: aload_3
        //   28: aload 6
        //   30: invokevirtual 40	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   33: aload_3
        //   34: aload_2
        //   35: invokevirtual 87	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   38: aload_0
        //   39: getfield 15	com/google/android/gms/wearable/internal/af$a$a:lb	Landroid/os/IBinder;
        //   42: bipush 24
        //   44: aload_3
        //   45: aload 4
        //   47: iconst_0
        //   48: invokeinterface 46 5 0
        //   53: pop
        //   54: aload 4
        //   56: invokevirtual 49	android/os/Parcel:readException	()V
        //   59: aload 4
        //   61: invokevirtual 52	android/os/Parcel:recycle	()V
        //   64: aload_3
        //   65: invokevirtual 52	android/os/Parcel:recycle	()V
        //   68: return
        //   69: aconst_null
        //   70: astore 6
        //   72: goto -45 -> 27
        //   75: astore 5
        //   77: aload 4
        //   79: invokevirtual 52	android/os/Parcel:recycle	()V
        //   82: aload_3
        //   83: invokevirtual 52	android/os/Parcel:recycle	()V
        //   86: aload 5
        //   88: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	89	0	this	a
        //   0	89	1	paramad	ad
        //   0	89	2	paramString	String
        //   3	80	3	localParcel1	Parcel
        //   7	71	4	localParcel2	Parcel
        //   75	12	5	localObject	Object
        //   25	46	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   9	15	75	finally
        //   19	27	75	finally
        //   27	59	75	finally
      }
      
      /* Error */
      public void d(ad paramad)
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
        //   15: ifnull +45 -> 60
        //   18: aload_1
        //   19: invokeinterface 37 1 0
        //   24: astore 5
        //   26: aload_2
        //   27: aload 5
        //   29: invokevirtual 40	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   32: aload_0
        //   33: getfield 15	com/google/android/gms/wearable/internal/af$a$a:lb	Landroid/os/IBinder;
        //   36: bipush 15
        //   38: aload_2
        //   39: aload_3
        //   40: iconst_0
        //   41: invokeinterface 46 5 0
        //   46: pop
        //   47: aload_3
        //   48: invokevirtual 49	android/os/Parcel:readException	()V
        //   51: aload_3
        //   52: invokevirtual 52	android/os/Parcel:recycle	()V
        //   55: aload_2
        //   56: invokevirtual 52	android/os/Parcel:recycle	()V
        //   59: return
        //   60: aconst_null
        //   61: astore 5
        //   63: goto -37 -> 26
        //   66: astore 4
        //   68: aload_3
        //   69: invokevirtual 52	android/os/Parcel:recycle	()V
        //   72: aload_2
        //   73: invokevirtual 52	android/os/Parcel:recycle	()V
        //   76: aload 4
        //   78: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	79	0	this	a
        //   0	79	1	paramad	ad
        //   3	70	2	localParcel1	Parcel
        //   7	62	3	localParcel2	Parcel
        //   66	11	4	localObject	Object
        //   24	38	5	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   8	14	66	finally
        //   18	26	66	finally
        //   26	51	66	finally
      }
      
      /* Error */
      public void e(ad paramad)
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
        //   15: ifnull +45 -> 60
        //   18: aload_1
        //   19: invokeinterface 37 1 0
        //   24: astore 5
        //   26: aload_2
        //   27: aload 5
        //   29: invokevirtual 40	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   32: aload_0
        //   33: getfield 15	com/google/android/gms/wearable/internal/af$a$a:lb	Landroid/os/IBinder;
        //   36: bipush 18
        //   38: aload_2
        //   39: aload_3
        //   40: iconst_0
        //   41: invokeinterface 46 5 0
        //   46: pop
        //   47: aload_3
        //   48: invokevirtual 49	android/os/Parcel:readException	()V
        //   51: aload_3
        //   52: invokevirtual 52	android/os/Parcel:recycle	()V
        //   55: aload_2
        //   56: invokevirtual 52	android/os/Parcel:recycle	()V
        //   59: return
        //   60: aconst_null
        //   61: astore 5
        //   63: goto -37 -> 26
        //   66: astore 4
        //   68: aload_3
        //   69: invokevirtual 52	android/os/Parcel:recycle	()V
        //   72: aload_2
        //   73: invokevirtual 52	android/os/Parcel:recycle	()V
        //   76: aload 4
        //   78: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	79	0	this	a
        //   0	79	1	paramad	ad
        //   3	70	2	localParcel1	Parcel
        //   7	62	3	localParcel2	Parcel
        //   66	11	4	localObject	Object
        //   24	38	5	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   8	14	66	finally
        //   18	26	66	finally
        //   26	51	66	finally
      }
      
      /* Error */
      public void f(ad paramad)
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
        //   15: ifnull +45 -> 60
        //   18: aload_1
        //   19: invokeinterface 37 1 0
        //   24: astore 5
        //   26: aload_2
        //   27: aload 5
        //   29: invokevirtual 40	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   32: aload_0
        //   33: getfield 15	com/google/android/gms/wearable/internal/af$a$a:lb	Landroid/os/IBinder;
        //   36: bipush 19
        //   38: aload_2
        //   39: aload_3
        //   40: iconst_0
        //   41: invokeinterface 46 5 0
        //   46: pop
        //   47: aload_3
        //   48: invokevirtual 49	android/os/Parcel:readException	()V
        //   51: aload_3
        //   52: invokevirtual 52	android/os/Parcel:recycle	()V
        //   55: aload_2
        //   56: invokevirtual 52	android/os/Parcel:recycle	()V
        //   59: return
        //   60: aconst_null
        //   61: astore 5
        //   63: goto -37 -> 26
        //   66: astore 4
        //   68: aload_3
        //   69: invokevirtual 52	android/os/Parcel:recycle	()V
        //   72: aload_2
        //   73: invokevirtual 52	android/os/Parcel:recycle	()V
        //   76: aload 4
        //   78: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	79	0	this	a
        //   0	79	1	paramad	ad
        //   3	70	2	localParcel1	Parcel
        //   7	62	3	localParcel2	Parcel
        //   66	11	4	localObject	Object
        //   24	38	5	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   8	14	66	finally
        //   18	26	66	finally
        //   26	51	66	finally
      }
      
      /* Error */
      public void g(ad paramad)
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
        //   19: invokeinterface 37 1 0
        //   24: astore 5
        //   26: aload_2
        //   27: aload 5
        //   29: invokevirtual 40	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   32: aload_0
        //   33: getfield 15	com/google/android/gms/wearable/internal/af$a$a:lb	Landroid/os/IBinder;
        //   36: iconst_3
        //   37: aload_2
        //   38: aload_3
        //   39: iconst_0
        //   40: invokeinterface 46 5 0
        //   45: pop
        //   46: aload_3
        //   47: invokevirtual 49	android/os/Parcel:readException	()V
        //   50: aload_3
        //   51: invokevirtual 52	android/os/Parcel:recycle	()V
        //   54: aload_2
        //   55: invokevirtual 52	android/os/Parcel:recycle	()V
        //   58: return
        //   59: aconst_null
        //   60: astore 5
        //   62: goto -36 -> 26
        //   65: astore 4
        //   67: aload_3
        //   68: invokevirtual 52	android/os/Parcel:recycle	()V
        //   71: aload_2
        //   72: invokevirtual 52	android/os/Parcel:recycle	()V
        //   75: aload 4
        //   77: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	78	0	this	a
        //   0	78	1	paramad	ad
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
      public void h(ad paramad)
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
        //   19: invokeinterface 37 1 0
        //   24: astore 5
        //   26: aload_2
        //   27: aload 5
        //   29: invokevirtual 40	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   32: aload_0
        //   33: getfield 15	com/google/android/gms/wearable/internal/af$a$a:lb	Landroid/os/IBinder;
        //   36: iconst_4
        //   37: aload_2
        //   38: aload_3
        //   39: iconst_0
        //   40: invokeinterface 46 5 0
        //   45: pop
        //   46: aload_3
        //   47: invokevirtual 49	android/os/Parcel:readException	()V
        //   50: aload_3
        //   51: invokevirtual 52	android/os/Parcel:recycle	()V
        //   54: aload_2
        //   55: invokevirtual 52	android/os/Parcel:recycle	()V
        //   58: return
        //   59: aconst_null
        //   60: astore 5
        //   62: goto -36 -> 26
        //   65: astore 4
        //   67: aload_3
        //   68: invokevirtual 52	android/os/Parcel:recycle	()V
        //   71: aload_2
        //   72: invokevirtual 52	android/os/Parcel:recycle	()V
        //   75: aload 4
        //   77: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	78	0	this	a
        //   0	78	1	paramad	ad
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
      public void i(ad paramad)
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
        //   19: invokeinterface 37 1 0
        //   24: astore 5
        //   26: aload_2
        //   27: aload 5
        //   29: invokevirtual 40	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   32: aload_0
        //   33: getfield 15	com/google/android/gms/wearable/internal/af$a$a:lb	Landroid/os/IBinder;
        //   36: iconst_5
        //   37: aload_2
        //   38: aload_3
        //   39: iconst_0
        //   40: invokeinterface 46 5 0
        //   45: pop
        //   46: aload_3
        //   47: invokevirtual 49	android/os/Parcel:readException	()V
        //   50: aload_3
        //   51: invokevirtual 52	android/os/Parcel:recycle	()V
        //   54: aload_2
        //   55: invokevirtual 52	android/os/Parcel:recycle	()V
        //   58: return
        //   59: aconst_null
        //   60: astore 5
        //   62: goto -36 -> 26
        //   65: astore 4
        //   67: aload_3
        //   68: invokevirtual 52	android/os/Parcel:recycle	()V
        //   71: aload_2
        //   72: invokevirtual 52	android/os/Parcel:recycle	()V
        //   75: aload 4
        //   77: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	78	0	this	a
        //   0	78	1	paramad	ad
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
 * Qualified Name:     com.google.android.gms.wearable.internal.af
 * JD-Core Version:    0.7.0.1
 */