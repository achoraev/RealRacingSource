package com.google.android.gms.auth.api;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface IGoogleAuthService
  extends IInterface
{
  public abstract void sendConnection(IGoogleAuthApiCallbacks paramIGoogleAuthApiCallbacks, GoogleAuthApiRequest paramGoogleAuthApiRequest)
    throws RemoteException;
  
  public static abstract class Stub
    extends Binder
    implements IGoogleAuthService
  {
    public Stub()
    {
      attachInterface(this, "com.google.android.gms.auth.api.IGoogleAuthService");
    }
    
    public static IGoogleAuthService asInterface(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.auth.api.IGoogleAuthService");
      if ((localIInterface != null) && ((localIInterface instanceof IGoogleAuthService))) {
        return (IGoogleAuthService)localIInterface;
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
        paramParcel2.writeString("com.google.android.gms.auth.api.IGoogleAuthService");
        return true;
      }
      paramParcel1.enforceInterface("com.google.android.gms.auth.api.IGoogleAuthService");
      IGoogleAuthApiCallbacks localIGoogleAuthApiCallbacks = IGoogleAuthApiCallbacks.Stub.asInterface(paramParcel1.readStrongBinder());
      if (paramParcel1.readInt() != 0) {}
      for (GoogleAuthApiRequest localGoogleAuthApiRequest = GoogleAuthApiRequest.CREATOR.createFromParcel(paramParcel1);; localGoogleAuthApiRequest = null)
      {
        sendConnection(localIGoogleAuthApiCallbacks, localGoogleAuthApiRequest);
        paramParcel2.writeNoException();
        return true;
      }
    }
    
    private static class a
      implements IGoogleAuthService
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
      public void sendConnection(IGoogleAuthApiCallbacks paramIGoogleAuthApiCallbacks, GoogleAuthApiRequest paramGoogleAuthApiRequest)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 27	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_3
        //   4: invokestatic 27	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore 4
        //   9: aload_3
        //   10: ldc 29
        //   12: invokevirtual 33	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
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
        //   39: invokevirtual 44	android/os/Parcel:writeInt	(I)V
        //   42: aload_2
        //   43: aload_3
        //   44: iconst_0
        //   45: invokevirtual 50	com/google/android/gms/auth/api/GoogleAuthApiRequest:writeToParcel	(Landroid/os/Parcel;I)V
        //   48: aload_0
        //   49: getfield 15	com/google/android/gms/auth/api/IGoogleAuthService$Stub$a:lb	Landroid/os/IBinder;
        //   52: iconst_1
        //   53: aload_3
        //   54: aload 4
        //   56: iconst_0
        //   57: invokeinterface 56 5 0
        //   62: pop
        //   63: aload 4
        //   65: invokevirtual 59	android/os/Parcel:readException	()V
        //   68: aload 4
        //   70: invokevirtual 62	android/os/Parcel:recycle	()V
        //   73: aload_3
        //   74: invokevirtual 62	android/os/Parcel:recycle	()V
        //   77: return
        //   78: aconst_null
        //   79: astore 6
        //   81: goto -54 -> 27
        //   84: aload_3
        //   85: iconst_0
        //   86: invokevirtual 44	android/os/Parcel:writeInt	(I)V
        //   89: goto -41 -> 48
        //   92: astore 5
        //   94: aload 4
        //   96: invokevirtual 62	android/os/Parcel:recycle	()V
        //   99: aload_3
        //   100: invokevirtual 62	android/os/Parcel:recycle	()V
        //   103: aload 5
        //   105: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	106	0	this	a
        //   0	106	1	paramIGoogleAuthApiCallbacks	IGoogleAuthApiCallbacks
        //   0	106	2	paramGoogleAuthApiRequest	GoogleAuthApiRequest
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
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.auth.api.IGoogleAuthService
 * JD-Core Version:    0.7.0.1
 */