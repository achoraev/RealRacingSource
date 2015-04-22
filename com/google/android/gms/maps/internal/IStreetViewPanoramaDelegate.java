package com.google.android.gms.maps.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.dynamic.d.a;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;
import com.google.android.gms.maps.model.i;
import com.google.android.gms.maps.model.t;

public abstract interface IStreetViewPanoramaDelegate
  extends IInterface
{
  public abstract void animateTo(StreetViewPanoramaCamera paramStreetViewPanoramaCamera, long paramLong)
    throws RemoteException;
  
  public abstract void enablePanning(boolean paramBoolean)
    throws RemoteException;
  
  public abstract void enableStreetNames(boolean paramBoolean)
    throws RemoteException;
  
  public abstract void enableUserNavigation(boolean paramBoolean)
    throws RemoteException;
  
  public abstract void enableZoom(boolean paramBoolean)
    throws RemoteException;
  
  public abstract StreetViewPanoramaCamera getPanoramaCamera()
    throws RemoteException;
  
  public abstract StreetViewPanoramaLocation getStreetViewPanoramaLocation()
    throws RemoteException;
  
  public abstract boolean isPanningGesturesEnabled()
    throws RemoteException;
  
  public abstract boolean isStreetNamesEnabled()
    throws RemoteException;
  
  public abstract boolean isUserNavigationEnabled()
    throws RemoteException;
  
  public abstract boolean isZoomGesturesEnabled()
    throws RemoteException;
  
  public abstract d orientationToPoint(StreetViewPanoramaOrientation paramStreetViewPanoramaOrientation)
    throws RemoteException;
  
  public abstract StreetViewPanoramaOrientation pointToOrientation(d paramd)
    throws RemoteException;
  
  public abstract void setOnStreetViewPanoramaCameraChangeListener(p paramp)
    throws RemoteException;
  
  public abstract void setOnStreetViewPanoramaChangeListener(q paramq)
    throws RemoteException;
  
  public abstract void setOnStreetViewPanoramaClickListener(r paramr)
    throws RemoteException;
  
  public abstract void setPosition(LatLng paramLatLng)
    throws RemoteException;
  
  public abstract void setPositionWithID(String paramString)
    throws RemoteException;
  
  public abstract void setPositionWithRadius(LatLng paramLatLng, int paramInt)
    throws RemoteException;
  
  public static abstract class a
    extends Binder
    implements IStreetViewPanoramaDelegate
  {
    public static IStreetViewPanoramaDelegate bl(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
      if ((localIInterface != null) && ((localIInterface instanceof IStreetViewPanoramaDelegate))) {
        return (IStreetViewPanoramaDelegate)localIInterface;
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
        paramParcel2.writeString("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
        return true;
      case 1: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
        int i5 = paramParcel1.readInt();
        boolean bool8 = false;
        if (i5 != 0) {
          bool8 = true;
        }
        enableZoom(bool8);
        paramParcel2.writeNoException();
        return true;
      case 2: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
        int i4 = paramParcel1.readInt();
        boolean bool7 = false;
        if (i4 != 0) {
          bool7 = true;
        }
        enablePanning(bool7);
        paramParcel2.writeNoException();
        return true;
      case 3: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
        int i3 = paramParcel1.readInt();
        boolean bool6 = false;
        if (i3 != 0) {
          bool6 = true;
        }
        enableUserNavigation(bool6);
        paramParcel2.writeNoException();
        return true;
      case 4: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
        int i2 = paramParcel1.readInt();
        boolean bool5 = false;
        if (i2 != 0) {
          bool5 = true;
        }
        enableStreetNames(bool5);
        paramParcel2.writeNoException();
        return true;
      case 5: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
        boolean bool4 = isZoomGesturesEnabled();
        paramParcel2.writeNoException();
        int i1 = 0;
        if (bool4) {
          i1 = 1;
        }
        paramParcel2.writeInt(i1);
        return true;
      case 6: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
        boolean bool3 = isPanningGesturesEnabled();
        paramParcel2.writeNoException();
        int n = 0;
        if (bool3) {
          n = 1;
        }
        paramParcel2.writeInt(n);
        return true;
      case 7: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
        boolean bool2 = isUserNavigationEnabled();
        paramParcel2.writeNoException();
        int m = 0;
        if (bool2) {
          m = 1;
        }
        paramParcel2.writeInt(m);
        return true;
      case 8: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
        boolean bool1 = isStreetNamesEnabled();
        paramParcel2.writeNoException();
        int k = 0;
        if (bool1) {
          k = 1;
        }
        paramParcel2.writeInt(k);
        return true;
      case 9: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
        if (paramParcel1.readInt() != 0) {}
        for (StreetViewPanoramaCamera localStreetViewPanoramaCamera2 = StreetViewPanoramaCamera.CREATOR.cQ(paramParcel1);; localStreetViewPanoramaCamera2 = null)
        {
          animateTo(localStreetViewPanoramaCamera2, paramParcel1.readLong());
          paramParcel2.writeNoException();
          return true;
        }
      case 10: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
        StreetViewPanoramaCamera localStreetViewPanoramaCamera1 = getPanoramaCamera();
        paramParcel2.writeNoException();
        if (localStreetViewPanoramaCamera1 != null)
        {
          paramParcel2.writeInt(1);
          localStreetViewPanoramaCamera1.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 11: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
        setPositionWithID(paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 12: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
        int j = paramParcel1.readInt();
        LatLng localLatLng2 = null;
        if (j != 0) {
          localLatLng2 = LatLng.CREATOR.cM(paramParcel1);
        }
        setPosition(localLatLng2);
        paramParcel2.writeNoException();
        return true;
      case 13: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
        int i = paramParcel1.readInt();
        LatLng localLatLng1 = null;
        if (i != 0) {
          localLatLng1 = LatLng.CREATOR.cM(paramParcel1);
        }
        setPositionWithRadius(localLatLng1, paramParcel1.readInt());
        paramParcel2.writeNoException();
        return true;
      case 14: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
        StreetViewPanoramaLocation localStreetViewPanoramaLocation = getStreetViewPanoramaLocation();
        paramParcel2.writeNoException();
        if (localStreetViewPanoramaLocation != null)
        {
          paramParcel2.writeInt(1);
          localStreetViewPanoramaLocation.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 15: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
        setOnStreetViewPanoramaChangeListener(q.a.bh(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 16: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
        setOnStreetViewPanoramaCameraChangeListener(p.a.bg(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 17: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
        setOnStreetViewPanoramaClickListener(r.a.bi(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 18: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
        StreetViewPanoramaOrientation localStreetViewPanoramaOrientation2 = pointToOrientation(d.a.am(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        if (localStreetViewPanoramaOrientation2 != null)
        {
          paramParcel2.writeInt(1);
          localStreetViewPanoramaOrientation2.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      }
      paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
      if (paramParcel1.readInt() != 0) {}
      for (StreetViewPanoramaOrientation localStreetViewPanoramaOrientation1 = StreetViewPanoramaOrientation.CREATOR.cT(paramParcel1);; localStreetViewPanoramaOrientation1 = null)
      {
        d locald = orientationToPoint(localStreetViewPanoramaOrientation1);
        paramParcel2.writeNoException();
        IBinder localIBinder = null;
        if (locald != null) {
          localIBinder = locald.asBinder();
        }
        paramParcel2.writeStrongBinder(localIBinder);
        return true;
      }
    }
    
    private static class a
      implements IStreetViewPanoramaDelegate
    {
      private IBinder lb;
      
      a(IBinder paramIBinder)
      {
        this.lb = paramIBinder;
      }
      
      /* Error */
      public void animateTo(StreetViewPanoramaCamera paramStreetViewPanoramaCamera, long paramLong)
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
        //   18: ifnull +55 -> 73
        //   21: aload 4
        //   23: iconst_1
        //   24: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   27: aload_1
        //   28: aload 4
        //   30: iconst_0
        //   31: invokevirtual 41	com/google/android/gms/maps/model/StreetViewPanoramaCamera:writeToParcel	(Landroid/os/Parcel;I)V
        //   34: aload 4
        //   36: lload_2
        //   37: invokevirtual 45	android/os/Parcel:writeLong	(J)V
        //   40: aload_0
        //   41: getfield 15	com/google/android/gms/maps/internal/IStreetViewPanoramaDelegate$a$a:lb	Landroid/os/IBinder;
        //   44: bipush 9
        //   46: aload 4
        //   48: aload 5
        //   50: iconst_0
        //   51: invokeinterface 51 5 0
        //   56: pop
        //   57: aload 5
        //   59: invokevirtual 54	android/os/Parcel:readException	()V
        //   62: aload 5
        //   64: invokevirtual 57	android/os/Parcel:recycle	()V
        //   67: aload 4
        //   69: invokevirtual 57	android/os/Parcel:recycle	()V
        //   72: return
        //   73: aload 4
        //   75: iconst_0
        //   76: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   79: goto -45 -> 34
        //   82: astore 6
        //   84: aload 5
        //   86: invokevirtual 57	android/os/Parcel:recycle	()V
        //   89: aload 4
        //   91: invokevirtual 57	android/os/Parcel:recycle	()V
        //   94: aload 6
        //   96: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	97	0	this	a
        //   0	97	1	paramStreetViewPanoramaCamera	StreetViewPanoramaCamera
        //   0	97	2	paramLong	long
        //   3	87	4	localParcel1	Parcel
        //   8	77	5	localParcel2	Parcel
        //   82	13	6	localObject	Object
        // Exception table:
        //   from	to	target	type
        //   10	17	82	finally
        //   21	34	82	finally
        //   34	62	82	finally
        //   73	79	82	finally
      }
      
      public IBinder asBinder()
      {
        return this.lb;
      }
      
      public void enablePanning(boolean paramBoolean)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
          int i = 0;
          if (paramBoolean) {
            i = 1;
          }
          localParcel1.writeInt(i);
          this.lb.transact(2, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void enableStreetNames(boolean paramBoolean)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
          int i = 0;
          if (paramBoolean) {
            i = 1;
          }
          localParcel1.writeInt(i);
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
      
      public void enableUserNavigation(boolean paramBoolean)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
          int i = 0;
          if (paramBoolean) {
            i = 1;
          }
          localParcel1.writeInt(i);
          this.lb.transact(3, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      /* Error */
      public void enableZoom(boolean paramBoolean)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore_2
        //   2: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   5: astore_3
        //   6: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   9: astore 4
        //   11: aload_3
        //   12: ldc 27
        //   14: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: iload_1
        //   18: ifeq +38 -> 56
        //   21: aload_3
        //   22: iload_2
        //   23: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   26: aload_0
        //   27: getfield 15	com/google/android/gms/maps/internal/IStreetViewPanoramaDelegate$a$a:lb	Landroid/os/IBinder;
        //   30: iconst_1
        //   31: aload_3
        //   32: aload 4
        //   34: iconst_0
        //   35: invokeinterface 51 5 0
        //   40: pop
        //   41: aload 4
        //   43: invokevirtual 54	android/os/Parcel:readException	()V
        //   46: aload 4
        //   48: invokevirtual 57	android/os/Parcel:recycle	()V
        //   51: aload_3
        //   52: invokevirtual 57	android/os/Parcel:recycle	()V
        //   55: return
        //   56: iconst_0
        //   57: istore_2
        //   58: goto -37 -> 21
        //   61: astore 5
        //   63: aload 4
        //   65: invokevirtual 57	android/os/Parcel:recycle	()V
        //   68: aload_3
        //   69: invokevirtual 57	android/os/Parcel:recycle	()V
        //   72: aload 5
        //   74: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	75	0	this	a
        //   0	75	1	paramBoolean	boolean
        //   1	57	2	i	int
        //   5	64	3	localParcel1	Parcel
        //   9	55	4	localParcel2	Parcel
        //   61	12	5	localObject	Object
        // Exception table:
        //   from	to	target	type
        //   11	17	61	finally
        //   21	46	61	finally
      }
      
      /* Error */
      public StreetViewPanoramaCamera getPanoramaCamera()
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_1
        //   4: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_2
        //   8: aload_1
        //   9: ldc 27
        //   11: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_0
        //   15: getfield 15	com/google/android/gms/maps/internal/IStreetViewPanoramaDelegate$a$a:lb	Landroid/os/IBinder;
        //   18: bipush 10
        //   20: aload_1
        //   21: aload_2
        //   22: iconst_0
        //   23: invokeinterface 51 5 0
        //   28: pop
        //   29: aload_2
        //   30: invokevirtual 54	android/os/Parcel:readException	()V
        //   33: aload_2
        //   34: invokevirtual 70	android/os/Parcel:readInt	()I
        //   37: ifeq +27 -> 64
        //   40: getstatic 74	com/google/android/gms/maps/model/StreetViewPanoramaCamera:CREATOR	Lcom/google/android/gms/maps/model/q;
        //   43: aload_2
        //   44: invokevirtual 80	com/google/android/gms/maps/model/q:cQ	(Landroid/os/Parcel;)Lcom/google/android/gms/maps/model/StreetViewPanoramaCamera;
        //   47: astore 6
        //   49: aload 6
        //   51: astore 5
        //   53: aload_2
        //   54: invokevirtual 57	android/os/Parcel:recycle	()V
        //   57: aload_1
        //   58: invokevirtual 57	android/os/Parcel:recycle	()V
        //   61: aload 5
        //   63: areturn
        //   64: aconst_null
        //   65: astore 5
        //   67: goto -14 -> 53
        //   70: astore_3
        //   71: aload_2
        //   72: invokevirtual 57	android/os/Parcel:recycle	()V
        //   75: aload_1
        //   76: invokevirtual 57	android/os/Parcel:recycle	()V
        //   79: aload_3
        //   80: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	81	0	this	a
        //   3	73	1	localParcel1	Parcel
        //   7	65	2	localParcel2	Parcel
        //   70	10	3	localObject	Object
        //   51	15	5	localStreetViewPanoramaCamera1	StreetViewPanoramaCamera
        //   47	3	6	localStreetViewPanoramaCamera2	StreetViewPanoramaCamera
        // Exception table:
        //   from	to	target	type
        //   8	49	70	finally
      }
      
      /* Error */
      public StreetViewPanoramaLocation getStreetViewPanoramaLocation()
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_1
        //   4: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_2
        //   8: aload_1
        //   9: ldc 27
        //   11: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_0
        //   15: getfield 15	com/google/android/gms/maps/internal/IStreetViewPanoramaDelegate$a$a:lb	Landroid/os/IBinder;
        //   18: bipush 14
        //   20: aload_1
        //   21: aload_2
        //   22: iconst_0
        //   23: invokeinterface 51 5 0
        //   28: pop
        //   29: aload_2
        //   30: invokevirtual 54	android/os/Parcel:readException	()V
        //   33: aload_2
        //   34: invokevirtual 70	android/os/Parcel:readInt	()I
        //   37: ifeq +27 -> 64
        //   40: getstatic 87	com/google/android/gms/maps/model/StreetViewPanoramaLocation:CREATOR	Lcom/google/android/gms/maps/model/s;
        //   43: aload_2
        //   44: invokevirtual 93	com/google/android/gms/maps/model/s:cS	(Landroid/os/Parcel;)Lcom/google/android/gms/maps/model/StreetViewPanoramaLocation;
        //   47: astore 6
        //   49: aload 6
        //   51: astore 5
        //   53: aload_2
        //   54: invokevirtual 57	android/os/Parcel:recycle	()V
        //   57: aload_1
        //   58: invokevirtual 57	android/os/Parcel:recycle	()V
        //   61: aload 5
        //   63: areturn
        //   64: aconst_null
        //   65: astore 5
        //   67: goto -14 -> 53
        //   70: astore_3
        //   71: aload_2
        //   72: invokevirtual 57	android/os/Parcel:recycle	()V
        //   75: aload_1
        //   76: invokevirtual 57	android/os/Parcel:recycle	()V
        //   79: aload_3
        //   80: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	81	0	this	a
        //   3	73	1	localParcel1	Parcel
        //   7	65	2	localParcel2	Parcel
        //   70	10	3	localObject	Object
        //   51	15	5	localStreetViewPanoramaLocation1	StreetViewPanoramaLocation
        //   47	3	6	localStreetViewPanoramaLocation2	StreetViewPanoramaLocation
        // Exception table:
        //   from	to	target	type
        //   8	49	70	finally
      }
      
      public boolean isPanningGesturesEnabled()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
          this.lb.transact(6, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          boolean bool = false;
          if (i != 0) {
            bool = true;
          }
          return bool;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public boolean isStreetNamesEnabled()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
          this.lb.transact(8, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          boolean bool = false;
          if (i != 0) {
            bool = true;
          }
          return bool;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public boolean isUserNavigationEnabled()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
          this.lb.transact(7, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          boolean bool = false;
          if (i != 0) {
            bool = true;
          }
          return bool;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public boolean isZoomGesturesEnabled()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
          this.lb.transact(5, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          boolean bool = false;
          if (i != 0) {
            bool = true;
          }
          return bool;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      /* Error */
      public d orientationToPoint(StreetViewPanoramaOrientation paramStreetViewPanoramaOrientation)
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
        //   15: ifnull +53 -> 68
        //   18: aload_2
        //   19: iconst_1
        //   20: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   23: aload_1
        //   24: aload_2
        //   25: iconst_0
        //   26: invokevirtual 103	com/google/android/gms/maps/model/StreetViewPanoramaOrientation:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aload_0
        //   30: getfield 15	com/google/android/gms/maps/internal/IStreetViewPanoramaDelegate$a$a:lb	Landroid/os/IBinder;
        //   33: bipush 19
        //   35: aload_2
        //   36: aload_3
        //   37: iconst_0
        //   38: invokeinterface 51 5 0
        //   43: pop
        //   44: aload_3
        //   45: invokevirtual 54	android/os/Parcel:readException	()V
        //   48: aload_3
        //   49: invokevirtual 106	android/os/Parcel:readStrongBinder	()Landroid/os/IBinder;
        //   52: invokestatic 112	com/google/android/gms/dynamic/d$a:am	(Landroid/os/IBinder;)Lcom/google/android/gms/dynamic/d;
        //   55: astore 6
        //   57: aload_3
        //   58: invokevirtual 57	android/os/Parcel:recycle	()V
        //   61: aload_2
        //   62: invokevirtual 57	android/os/Parcel:recycle	()V
        //   65: aload 6
        //   67: areturn
        //   68: aload_2
        //   69: iconst_0
        //   70: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   73: goto -44 -> 29
        //   76: astore 4
        //   78: aload_3
        //   79: invokevirtual 57	android/os/Parcel:recycle	()V
        //   82: aload_2
        //   83: invokevirtual 57	android/os/Parcel:recycle	()V
        //   86: aload 4
        //   88: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	89	0	this	a
        //   0	89	1	paramStreetViewPanoramaOrientation	StreetViewPanoramaOrientation
        //   3	80	2	localParcel1	Parcel
        //   7	72	3	localParcel2	Parcel
        //   76	11	4	localObject	Object
        //   55	11	6	locald	d
        // Exception table:
        //   from	to	target	type
        //   8	14	76	finally
        //   18	29	76	finally
        //   29	57	76	finally
        //   68	73	76	finally
      }
      
      /* Error */
      public StreetViewPanoramaOrientation pointToOrientation(d paramd)
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
        //   15: ifnull +74 -> 89
        //   18: aload_1
        //   19: invokeinterface 118 1 0
        //   24: astore 5
        //   26: aload_2
        //   27: aload 5
        //   29: invokevirtual 121	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   32: aload_0
        //   33: getfield 15	com/google/android/gms/maps/internal/IStreetViewPanoramaDelegate$a$a:lb	Landroid/os/IBinder;
        //   36: bipush 18
        //   38: aload_2
        //   39: aload_3
        //   40: iconst_0
        //   41: invokeinterface 51 5 0
        //   46: pop
        //   47: aload_3
        //   48: invokevirtual 54	android/os/Parcel:readException	()V
        //   51: aload_3
        //   52: invokevirtual 70	android/os/Parcel:readInt	()I
        //   55: istore 7
        //   57: aconst_null
        //   58: astore 8
        //   60: iload 7
        //   62: ifeq +16 -> 78
        //   65: getstatic 124	com/google/android/gms/maps/model/StreetViewPanoramaOrientation:CREATOR	Lcom/google/android/gms/maps/model/t;
        //   68: aload_3
        //   69: invokevirtual 130	com/google/android/gms/maps/model/t:cT	(Landroid/os/Parcel;)Lcom/google/android/gms/maps/model/StreetViewPanoramaOrientation;
        //   72: astore 9
        //   74: aload 9
        //   76: astore 8
        //   78: aload_3
        //   79: invokevirtual 57	android/os/Parcel:recycle	()V
        //   82: aload_2
        //   83: invokevirtual 57	android/os/Parcel:recycle	()V
        //   86: aload 8
        //   88: areturn
        //   89: aconst_null
        //   90: astore 5
        //   92: goto -66 -> 26
        //   95: astore 4
        //   97: aload_3
        //   98: invokevirtual 57	android/os/Parcel:recycle	()V
        //   101: aload_2
        //   102: invokevirtual 57	android/os/Parcel:recycle	()V
        //   105: aload 4
        //   107: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	108	0	this	a
        //   0	108	1	paramd	d
        //   3	99	2	localParcel1	Parcel
        //   7	91	3	localParcel2	Parcel
        //   95	11	4	localObject1	Object
        //   24	67	5	localIBinder	IBinder
        //   55	6	7	i	int
        //   58	29	8	localObject2	Object
        //   72	3	9	localStreetViewPanoramaOrientation	StreetViewPanoramaOrientation
        // Exception table:
        //   from	to	target	type
        //   8	14	95	finally
        //   18	26	95	finally
        //   26	57	95	finally
        //   65	74	95	finally
      }
      
      /* Error */
      public void setOnStreetViewPanoramaCameraChangeListener(p paramp)
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
        //   19: invokeinterface 135 1 0
        //   24: astore 5
        //   26: aload_2
        //   27: aload 5
        //   29: invokevirtual 121	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   32: aload_0
        //   33: getfield 15	com/google/android/gms/maps/internal/IStreetViewPanoramaDelegate$a$a:lb	Landroid/os/IBinder;
        //   36: bipush 16
        //   38: aload_2
        //   39: aload_3
        //   40: iconst_0
        //   41: invokeinterface 51 5 0
        //   46: pop
        //   47: aload_3
        //   48: invokevirtual 54	android/os/Parcel:readException	()V
        //   51: aload_3
        //   52: invokevirtual 57	android/os/Parcel:recycle	()V
        //   55: aload_2
        //   56: invokevirtual 57	android/os/Parcel:recycle	()V
        //   59: return
        //   60: aconst_null
        //   61: astore 5
        //   63: goto -37 -> 26
        //   66: astore 4
        //   68: aload_3
        //   69: invokevirtual 57	android/os/Parcel:recycle	()V
        //   72: aload_2
        //   73: invokevirtual 57	android/os/Parcel:recycle	()V
        //   76: aload 4
        //   78: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	79	0	this	a
        //   0	79	1	paramp	p
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
      public void setOnStreetViewPanoramaChangeListener(q paramq)
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
        //   19: invokeinterface 140 1 0
        //   24: astore 5
        //   26: aload_2
        //   27: aload 5
        //   29: invokevirtual 121	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   32: aload_0
        //   33: getfield 15	com/google/android/gms/maps/internal/IStreetViewPanoramaDelegate$a$a:lb	Landroid/os/IBinder;
        //   36: bipush 15
        //   38: aload_2
        //   39: aload_3
        //   40: iconst_0
        //   41: invokeinterface 51 5 0
        //   46: pop
        //   47: aload_3
        //   48: invokevirtual 54	android/os/Parcel:readException	()V
        //   51: aload_3
        //   52: invokevirtual 57	android/os/Parcel:recycle	()V
        //   55: aload_2
        //   56: invokevirtual 57	android/os/Parcel:recycle	()V
        //   59: return
        //   60: aconst_null
        //   61: astore 5
        //   63: goto -37 -> 26
        //   66: astore 4
        //   68: aload_3
        //   69: invokevirtual 57	android/os/Parcel:recycle	()V
        //   72: aload_2
        //   73: invokevirtual 57	android/os/Parcel:recycle	()V
        //   76: aload 4
        //   78: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	79	0	this	a
        //   0	79	1	paramq	q
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
      public void setOnStreetViewPanoramaClickListener(r paramr)
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
        //   19: invokeinterface 145 1 0
        //   24: astore 5
        //   26: aload_2
        //   27: aload 5
        //   29: invokevirtual 121	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   32: aload_0
        //   33: getfield 15	com/google/android/gms/maps/internal/IStreetViewPanoramaDelegate$a$a:lb	Landroid/os/IBinder;
        //   36: bipush 17
        //   38: aload_2
        //   39: aload_3
        //   40: iconst_0
        //   41: invokeinterface 51 5 0
        //   46: pop
        //   47: aload_3
        //   48: invokevirtual 54	android/os/Parcel:readException	()V
        //   51: aload_3
        //   52: invokevirtual 57	android/os/Parcel:recycle	()V
        //   55: aload_2
        //   56: invokevirtual 57	android/os/Parcel:recycle	()V
        //   59: return
        //   60: aconst_null
        //   61: astore 5
        //   63: goto -37 -> 26
        //   66: astore 4
        //   68: aload_3
        //   69: invokevirtual 57	android/os/Parcel:recycle	()V
        //   72: aload_2
        //   73: invokevirtual 57	android/os/Parcel:recycle	()V
        //   76: aload 4
        //   78: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	79	0	this	a
        //   0	79	1	paramr	r
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
      public void setPosition(LatLng paramLatLng)
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
        //   15: ifnull +42 -> 57
        //   18: aload_2
        //   19: iconst_1
        //   20: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   23: aload_1
        //   24: aload_2
        //   25: iconst_0
        //   26: invokevirtual 150	com/google/android/gms/maps/model/LatLng:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aload_0
        //   30: getfield 15	com/google/android/gms/maps/internal/IStreetViewPanoramaDelegate$a$a:lb	Landroid/os/IBinder;
        //   33: bipush 12
        //   35: aload_2
        //   36: aload_3
        //   37: iconst_0
        //   38: invokeinterface 51 5 0
        //   43: pop
        //   44: aload_3
        //   45: invokevirtual 54	android/os/Parcel:readException	()V
        //   48: aload_3
        //   49: invokevirtual 57	android/os/Parcel:recycle	()V
        //   52: aload_2
        //   53: invokevirtual 57	android/os/Parcel:recycle	()V
        //   56: return
        //   57: aload_2
        //   58: iconst_0
        //   59: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   62: goto -33 -> 29
        //   65: astore 4
        //   67: aload_3
        //   68: invokevirtual 57	android/os/Parcel:recycle	()V
        //   71: aload_2
        //   72: invokevirtual 57	android/os/Parcel:recycle	()V
        //   75: aload 4
        //   77: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	78	0	this	a
        //   0	78	1	paramLatLng	LatLng
        //   3	69	2	localParcel1	Parcel
        //   7	61	3	localParcel2	Parcel
        //   65	11	4	localObject	Object
        // Exception table:
        //   from	to	target	type
        //   8	14	65	finally
        //   18	29	65	finally
        //   29	48	65	finally
        //   57	62	65	finally
      }
      
      public void setPositionWithID(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
          localParcel1.writeString(paramString);
          this.lb.transact(11, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      /* Error */
      public void setPositionWithRadius(LatLng paramLatLng, int paramInt)
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
        //   16: ifnull +50 -> 66
        //   19: aload_3
        //   20: iconst_1
        //   21: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   24: aload_1
        //   25: aload_3
        //   26: iconst_0
        //   27: invokevirtual 150	com/google/android/gms/maps/model/LatLng:writeToParcel	(Landroid/os/Parcel;I)V
        //   30: aload_3
        //   31: iload_2
        //   32: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   35: aload_0
        //   36: getfield 15	com/google/android/gms/maps/internal/IStreetViewPanoramaDelegate$a$a:lb	Landroid/os/IBinder;
        //   39: bipush 13
        //   41: aload_3
        //   42: aload 4
        //   44: iconst_0
        //   45: invokeinterface 51 5 0
        //   50: pop
        //   51: aload 4
        //   53: invokevirtual 54	android/os/Parcel:readException	()V
        //   56: aload 4
        //   58: invokevirtual 57	android/os/Parcel:recycle	()V
        //   61: aload_3
        //   62: invokevirtual 57	android/os/Parcel:recycle	()V
        //   65: return
        //   66: aload_3
        //   67: iconst_0
        //   68: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   71: goto -41 -> 30
        //   74: astore 5
        //   76: aload 4
        //   78: invokevirtual 57	android/os/Parcel:recycle	()V
        //   81: aload_3
        //   82: invokevirtual 57	android/os/Parcel:recycle	()V
        //   85: aload 5
        //   87: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	88	0	this	a
        //   0	88	1	paramLatLng	LatLng
        //   0	88	2	paramInt	int
        //   3	79	3	localParcel1	Parcel
        //   7	70	4	localParcel2	Parcel
        //   74	12	5	localObject	Object
        // Exception table:
        //   from	to	target	type
        //   9	15	74	finally
        //   19	30	74	finally
        //   30	56	74	finally
        //   66	71	74	finally
      }
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
 * JD-Core Version:    0.7.0.1
 */