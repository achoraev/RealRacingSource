package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.google.android.gms.fitness.request.DataDeleteRequest;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.request.DataSourcesRequest;
import com.google.android.gms.fitness.request.DataTypeCreateRequest;
import com.google.android.gms.fitness.request.SessionInsertRequest;
import com.google.android.gms.fitness.request.SessionReadRequest;
import com.google.android.gms.fitness.request.StartBleScanRequest;
import com.google.android.gms.fitness.request.aa;
import com.google.android.gms.fitness.request.ad;
import com.google.android.gms.fitness.request.af;
import com.google.android.gms.fitness.request.ah;
import com.google.android.gms.fitness.request.aj;
import com.google.android.gms.fitness.request.b;
import com.google.android.gms.fitness.request.e;
import com.google.android.gms.fitness.request.j;
import com.google.android.gms.fitness.request.m;
import com.google.android.gms.fitness.request.o;
import com.google.android.gms.fitness.request.q;
import com.google.android.gms.fitness.request.u;
import com.google.android.gms.fitness.request.w;
import com.google.android.gms.fitness.request.y;

public abstract interface kp
  extends IInterface
{
  public abstract void a(DataDeleteRequest paramDataDeleteRequest, kt paramkt, String paramString)
    throws RemoteException;
  
  public abstract void a(DataReadRequest paramDataReadRequest, km paramkm, String paramString)
    throws RemoteException;
  
  public abstract void a(DataSourcesRequest paramDataSourcesRequest, kn paramkn, String paramString)
    throws RemoteException;
  
  public abstract void a(DataTypeCreateRequest paramDataTypeCreateRequest, ko paramko, String paramString)
    throws RemoteException;
  
  public abstract void a(SessionInsertRequest paramSessionInsertRequest, kt paramkt, String paramString)
    throws RemoteException;
  
  public abstract void a(SessionReadRequest paramSessionReadRequest, kr paramkr, String paramString)
    throws RemoteException;
  
  public abstract void a(StartBleScanRequest paramStartBleScanRequest, kt paramkt, String paramString)
    throws RemoteException;
  
  public abstract void a(aa paramaa, kt paramkt, String paramString)
    throws RemoteException;
  
  public abstract void a(ad paramad, kt paramkt, String paramString)
    throws RemoteException;
  
  public abstract void a(af paramaf, kt paramkt, String paramString)
    throws RemoteException;
  
  public abstract void a(ah paramah, kt paramkt, String paramString)
    throws RemoteException;
  
  public abstract void a(aj paramaj, kt paramkt, String paramString)
    throws RemoteException;
  
  public abstract void a(b paramb, kt paramkt, String paramString)
    throws RemoteException;
  
  public abstract void a(e parame, kt paramkt, String paramString)
    throws RemoteException;
  
  public abstract void a(j paramj, ko paramko, String paramString)
    throws RemoteException;
  
  public abstract void a(m paramm, kq paramkq, String paramString)
    throws RemoteException;
  
  public abstract void a(o paramo, kt paramkt, String paramString)
    throws RemoteException;
  
  public abstract void a(q paramq, kt paramkt, String paramString)
    throws RemoteException;
  
  public abstract void a(u paramu, kt paramkt, String paramString)
    throws RemoteException;
  
  public abstract void a(w paramw, kt paramkt, String paramString)
    throws RemoteException;
  
  public abstract void a(y paramy, ks paramks, String paramString)
    throws RemoteException;
  
  public abstract void a(kt paramkt, String paramString)
    throws RemoteException;
  
  public abstract void a(lf paramlf, String paramString)
    throws RemoteException;
  
  public abstract void b(kt paramkt, String paramString)
    throws RemoteException;
  
  public static abstract class a
    extends Binder
    implements kp
  {
    public static kp as(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
      if ((localIInterface != null) && ((localIInterface instanceof kp))) {
        return (kp)localIInterface;
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
        paramParcel2.writeString("com.google.android.gms.fitness.internal.IGoogleFitnessService");
        return true;
      case 1: 
        paramParcel1.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
        int i16 = paramParcel1.readInt();
        DataSourcesRequest localDataSourcesRequest = null;
        if (i16 != 0) {
          localDataSourcesRequest = (DataSourcesRequest)DataSourcesRequest.CREATOR.createFromParcel(paramParcel1);
        }
        a(localDataSourcesRequest, kn.a.aq(paramParcel1.readStrongBinder()), paramParcel1.readString());
        return true;
      case 2: 
        paramParcel1.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
        int i15 = paramParcel1.readInt();
        o localo = null;
        if (i15 != 0) {
          localo = (o)o.CREATOR.createFromParcel(paramParcel1);
        }
        a(localo, kt.a.aw(paramParcel1.readStrongBinder()), paramParcel1.readString());
        return true;
      case 3: 
        paramParcel1.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
        int i14 = paramParcel1.readInt();
        q localq = null;
        if (i14 != 0) {
          localq = (q)q.CREATOR.createFromParcel(paramParcel1);
        }
        a(localq, kt.a.aw(paramParcel1.readStrongBinder()), paramParcel1.readString());
        return true;
      case 4: 
        paramParcel1.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
        int i13 = paramParcel1.readInt();
        af localaf = null;
        if (i13 != 0) {
          localaf = (af)af.CREATOR.createFromParcel(paramParcel1);
        }
        a(localaf, kt.a.aw(paramParcel1.readStrongBinder()), paramParcel1.readString());
        return true;
      case 5: 
        paramParcel1.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
        int i12 = paramParcel1.readInt();
        aj localaj = null;
        if (i12 != 0) {
          localaj = (aj)aj.CREATOR.createFromParcel(paramParcel1);
        }
        a(localaj, kt.a.aw(paramParcel1.readStrongBinder()), paramParcel1.readString());
        return true;
      case 6: 
        paramParcel1.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
        int i11 = paramParcel1.readInt();
        m localm = null;
        if (i11 != 0) {
          localm = (m)m.CREATOR.createFromParcel(paramParcel1);
        }
        a(localm, kq.a.at(paramParcel1.readStrongBinder()), paramParcel1.readString());
        return true;
      case 7: 
        paramParcel1.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
        int i10 = paramParcel1.readInt();
        e locale = null;
        if (i10 != 0) {
          locale = (e)e.CREATOR.createFromParcel(paramParcel1);
        }
        a(locale, kt.a.aw(paramParcel1.readStrongBinder()), paramParcel1.readString());
        return true;
      case 19: 
        paramParcel1.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
        int i9 = paramParcel1.readInt();
        DataDeleteRequest localDataDeleteRequest = null;
        if (i9 != 0) {
          localDataDeleteRequest = (DataDeleteRequest)DataDeleteRequest.CREATOR.createFromParcel(paramParcel1);
        }
        a(localDataDeleteRequest, kt.a.aw(paramParcel1.readStrongBinder()), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 13: 
        paramParcel1.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
        int i8 = paramParcel1.readInt();
        DataTypeCreateRequest localDataTypeCreateRequest = null;
        if (i8 != 0) {
          localDataTypeCreateRequest = (DataTypeCreateRequest)DataTypeCreateRequest.CREATOR.createFromParcel(paramParcel1);
        }
        a(localDataTypeCreateRequest, ko.a.ar(paramParcel1.readStrongBinder()), paramParcel1.readString());
        return true;
      case 14: 
        paramParcel1.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
        int i7 = paramParcel1.readInt();
        j localj = null;
        if (i7 != 0) {
          localj = (j)j.CREATOR.createFromParcel(paramParcel1);
        }
        a(localj, ko.a.ar(paramParcel1.readStrongBinder()), paramParcel1.readString());
        return true;
      case 8: 
        paramParcel1.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
        int i6 = paramParcel1.readInt();
        DataReadRequest localDataReadRequest = null;
        if (i6 != 0) {
          localDataReadRequest = (DataReadRequest)DataReadRequest.CREATOR.createFromParcel(paramParcel1);
        }
        a(localDataReadRequest, km.a.ap(paramParcel1.readStrongBinder()), paramParcel1.readString());
        return true;
      case 9: 
        paramParcel1.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
        int i5 = paramParcel1.readInt();
        SessionInsertRequest localSessionInsertRequest = null;
        if (i5 != 0) {
          localSessionInsertRequest = (SessionInsertRequest)SessionInsertRequest.CREATOR.createFromParcel(paramParcel1);
        }
        a(localSessionInsertRequest, kt.a.aw(paramParcel1.readStrongBinder()), paramParcel1.readString());
        return true;
      case 10: 
        paramParcel1.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
        int i4 = paramParcel1.readInt();
        SessionReadRequest localSessionReadRequest = null;
        if (i4 != 0) {
          localSessionReadRequest = (SessionReadRequest)SessionReadRequest.CREATOR.createFromParcel(paramParcel1);
        }
        a(localSessionReadRequest, kr.a.au(paramParcel1.readStrongBinder()), paramParcel1.readString());
        return true;
      case 11: 
        paramParcel1.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
        int i3 = paramParcel1.readInt();
        w localw = null;
        if (i3 != 0) {
          localw = (w)w.CREATOR.createFromParcel(paramParcel1);
        }
        a(localw, kt.a.aw(paramParcel1.readStrongBinder()), paramParcel1.readString());
        return true;
      case 12: 
        paramParcel1.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
        int i2 = paramParcel1.readInt();
        y localy = null;
        if (i2 != 0) {
          localy = (y)y.CREATOR.createFromParcel(paramParcel1);
        }
        a(localy, ks.a.av(paramParcel1.readStrongBinder()), paramParcel1.readString());
        return true;
      case 15: 
        paramParcel1.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
        int i1 = paramParcel1.readInt();
        StartBleScanRequest localStartBleScanRequest = null;
        if (i1 != 0) {
          localStartBleScanRequest = (StartBleScanRequest)StartBleScanRequest.CREATOR.createFromParcel(paramParcel1);
        }
        a(localStartBleScanRequest, kt.a.aw(paramParcel1.readStrongBinder()), paramParcel1.readString());
        return true;
      case 16: 
        paramParcel1.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
        int n = paramParcel1.readInt();
        ad localad = null;
        if (n != 0) {
          localad = (ad)ad.CREATOR.createFromParcel(paramParcel1);
        }
        a(localad, kt.a.aw(paramParcel1.readStrongBinder()), paramParcel1.readString());
        return true;
      case 17: 
        paramParcel1.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
        int m = paramParcel1.readInt();
        b localb = null;
        if (m != 0) {
          localb = (b)b.CREATOR.createFromParcel(paramParcel1);
        }
        a(localb, kt.a.aw(paramParcel1.readStrongBinder()), paramParcel1.readString());
        return true;
      case 18: 
        paramParcel1.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
        int k = paramParcel1.readInt();
        ah localah = null;
        if (k != 0) {
          localah = (ah)ah.CREATOR.createFromParcel(paramParcel1);
        }
        a(localah, kt.a.aw(paramParcel1.readStrongBinder()), paramParcel1.readString());
        return true;
      case 20: 
        paramParcel1.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
        int j = paramParcel1.readInt();
        u localu = null;
        if (j != 0) {
          localu = (u)u.CREATOR.createFromParcel(paramParcel1);
        }
        a(localu, kt.a.aw(paramParcel1.readStrongBinder()), paramParcel1.readString());
        return true;
      case 21: 
        paramParcel1.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
        int i = paramParcel1.readInt();
        aa localaa = null;
        if (i != 0) {
          localaa = (aa)aa.CREATOR.createFromParcel(paramParcel1);
        }
        a(localaa, kt.a.aw(paramParcel1.readStrongBinder()), paramParcel1.readString());
        return true;
      case 22: 
        paramParcel1.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
        a(kt.a.aw(paramParcel1.readStrongBinder()), paramParcel1.readString());
        return true;
      case 23: 
        paramParcel1.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
        b(kt.a.aw(paramParcel1.readStrongBinder()), paramParcel1.readString());
        return true;
      }
      paramParcel1.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
      a(lf.a.ax(paramParcel1.readStrongBinder()), paramParcel1.readString());
      return true;
    }
    
    private static class a
      implements kp
    {
      private IBinder lb;
      
      a(IBinder paramIBinder)
      {
        this.lb = paramIBinder;
      }
      
      public void a(DataDeleteRequest paramDataDeleteRequest, kt paramkt, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.fitness.internal.IGoogleFitnessService");
            if (paramDataDeleteRequest != null)
            {
              localParcel1.writeInt(1);
              paramDataDeleteRequest.writeToParcel(localParcel1, 0);
              if (paramkt != null)
              {
                localIBinder = paramkt.asBinder();
                localParcel1.writeStrongBinder(localIBinder);
                localParcel1.writeString(paramString);
                this.lb.transact(19, localParcel1, localParcel2, 0);
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
      public void a(DataReadRequest paramDataReadRequest, km paramkm, String paramString)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 4
        //   5: aload 4
        //   7: ldc 27
        //   9: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   12: aload_1
        //   13: ifnull +66 -> 79
        //   16: aload 4
        //   18: iconst_1
        //   19: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   22: aload_1
        //   23: aload 4
        //   25: iconst_0
        //   26: invokevirtual 69	com/google/android/gms/fitness/request/DataReadRequest:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aconst_null
        //   30: astore 6
        //   32: aload_2
        //   33: ifnull +11 -> 44
        //   36: aload_2
        //   37: invokeinterface 72 1 0
        //   42: astore 6
        //   44: aload 4
        //   46: aload 6
        //   48: invokevirtual 50	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   51: aload 4
        //   53: aload_3
        //   54: invokevirtual 53	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   57: aload_0
        //   58: getfield 15	com/google/android/gms/internal/kp$a$a:lb	Landroid/os/IBinder;
        //   61: bipush 8
        //   63: aload 4
        //   65: aconst_null
        //   66: iconst_1
        //   67: invokeinterface 59 5 0
        //   72: pop
        //   73: aload 4
        //   75: invokevirtual 65	android/os/Parcel:recycle	()V
        //   78: return
        //   79: aload 4
        //   81: iconst_0
        //   82: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   85: goto -56 -> 29
        //   88: astore 5
        //   90: aload 4
        //   92: invokevirtual 65	android/os/Parcel:recycle	()V
        //   95: aload 5
        //   97: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	98	0	this	a
        //   0	98	1	paramDataReadRequest	DataReadRequest
        //   0	98	2	paramkm	km
        //   0	98	3	paramString	String
        //   3	88	4	localParcel	Parcel
        //   88	8	5	localObject	Object
        //   30	17	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   5	12	88	finally
        //   16	29	88	finally
        //   36	44	88	finally
        //   44	73	88	finally
        //   79	85	88	finally
      }
      
      /* Error */
      public void a(DataSourcesRequest paramDataSourcesRequest, kn paramkn, String paramString)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 4
        //   5: aload 4
        //   7: ldc 27
        //   9: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   12: aload_1
        //   13: ifnull +65 -> 78
        //   16: aload 4
        //   18: iconst_1
        //   19: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   22: aload_1
        //   23: aload 4
        //   25: iconst_0
        //   26: invokevirtual 76	com/google/android/gms/fitness/request/DataSourcesRequest:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aconst_null
        //   30: astore 6
        //   32: aload_2
        //   33: ifnull +11 -> 44
        //   36: aload_2
        //   37: invokeinterface 79 1 0
        //   42: astore 6
        //   44: aload 4
        //   46: aload 6
        //   48: invokevirtual 50	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   51: aload 4
        //   53: aload_3
        //   54: invokevirtual 53	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   57: aload_0
        //   58: getfield 15	com/google/android/gms/internal/kp$a$a:lb	Landroid/os/IBinder;
        //   61: iconst_1
        //   62: aload 4
        //   64: aconst_null
        //   65: iconst_1
        //   66: invokeinterface 59 5 0
        //   71: pop
        //   72: aload 4
        //   74: invokevirtual 65	android/os/Parcel:recycle	()V
        //   77: return
        //   78: aload 4
        //   80: iconst_0
        //   81: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   84: goto -55 -> 29
        //   87: astore 5
        //   89: aload 4
        //   91: invokevirtual 65	android/os/Parcel:recycle	()V
        //   94: aload 5
        //   96: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	97	0	this	a
        //   0	97	1	paramDataSourcesRequest	DataSourcesRequest
        //   0	97	2	paramkn	kn
        //   0	97	3	paramString	String
        //   3	87	4	localParcel	Parcel
        //   87	8	5	localObject	Object
        //   30	17	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   5	12	87	finally
        //   16	29	87	finally
        //   36	44	87	finally
        //   44	72	87	finally
        //   78	84	87	finally
      }
      
      /* Error */
      public void a(DataTypeCreateRequest paramDataTypeCreateRequest, ko paramko, String paramString)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 4
        //   5: aload 4
        //   7: ldc 27
        //   9: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   12: aload_1
        //   13: ifnull +66 -> 79
        //   16: aload 4
        //   18: iconst_1
        //   19: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   22: aload_1
        //   23: aload 4
        //   25: iconst_0
        //   26: invokevirtual 83	com/google/android/gms/fitness/request/DataTypeCreateRequest:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aconst_null
        //   30: astore 6
        //   32: aload_2
        //   33: ifnull +11 -> 44
        //   36: aload_2
        //   37: invokeinterface 86 1 0
        //   42: astore 6
        //   44: aload 4
        //   46: aload 6
        //   48: invokevirtual 50	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   51: aload 4
        //   53: aload_3
        //   54: invokevirtual 53	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   57: aload_0
        //   58: getfield 15	com/google/android/gms/internal/kp$a$a:lb	Landroid/os/IBinder;
        //   61: bipush 13
        //   63: aload 4
        //   65: aconst_null
        //   66: iconst_1
        //   67: invokeinterface 59 5 0
        //   72: pop
        //   73: aload 4
        //   75: invokevirtual 65	android/os/Parcel:recycle	()V
        //   78: return
        //   79: aload 4
        //   81: iconst_0
        //   82: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   85: goto -56 -> 29
        //   88: astore 5
        //   90: aload 4
        //   92: invokevirtual 65	android/os/Parcel:recycle	()V
        //   95: aload 5
        //   97: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	98	0	this	a
        //   0	98	1	paramDataTypeCreateRequest	DataTypeCreateRequest
        //   0	98	2	paramko	ko
        //   0	98	3	paramString	String
        //   3	88	4	localParcel	Parcel
        //   88	8	5	localObject	Object
        //   30	17	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   5	12	88	finally
        //   16	29	88	finally
        //   36	44	88	finally
        //   44	73	88	finally
        //   79	85	88	finally
      }
      
      /* Error */
      public void a(SessionInsertRequest paramSessionInsertRequest, kt paramkt, String paramString)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 4
        //   5: aload 4
        //   7: ldc 27
        //   9: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   12: aload_1
        //   13: ifnull +66 -> 79
        //   16: aload 4
        //   18: iconst_1
        //   19: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   22: aload_1
        //   23: aload 4
        //   25: iconst_0
        //   26: invokevirtual 90	com/google/android/gms/fitness/request/SessionInsertRequest:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aconst_null
        //   30: astore 6
        //   32: aload_2
        //   33: ifnull +11 -> 44
        //   36: aload_2
        //   37: invokeinterface 47 1 0
        //   42: astore 6
        //   44: aload 4
        //   46: aload 6
        //   48: invokevirtual 50	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   51: aload 4
        //   53: aload_3
        //   54: invokevirtual 53	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   57: aload_0
        //   58: getfield 15	com/google/android/gms/internal/kp$a$a:lb	Landroid/os/IBinder;
        //   61: bipush 9
        //   63: aload 4
        //   65: aconst_null
        //   66: iconst_1
        //   67: invokeinterface 59 5 0
        //   72: pop
        //   73: aload 4
        //   75: invokevirtual 65	android/os/Parcel:recycle	()V
        //   78: return
        //   79: aload 4
        //   81: iconst_0
        //   82: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   85: goto -56 -> 29
        //   88: astore 5
        //   90: aload 4
        //   92: invokevirtual 65	android/os/Parcel:recycle	()V
        //   95: aload 5
        //   97: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	98	0	this	a
        //   0	98	1	paramSessionInsertRequest	SessionInsertRequest
        //   0	98	2	paramkt	kt
        //   0	98	3	paramString	String
        //   3	88	4	localParcel	Parcel
        //   88	8	5	localObject	Object
        //   30	17	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   5	12	88	finally
        //   16	29	88	finally
        //   36	44	88	finally
        //   44	73	88	finally
        //   79	85	88	finally
      }
      
      /* Error */
      public void a(SessionReadRequest paramSessionReadRequest, kr paramkr, String paramString)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 4
        //   5: aload 4
        //   7: ldc 27
        //   9: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   12: aload_1
        //   13: ifnull +66 -> 79
        //   16: aload 4
        //   18: iconst_1
        //   19: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   22: aload_1
        //   23: aload 4
        //   25: iconst_0
        //   26: invokevirtual 94	com/google/android/gms/fitness/request/SessionReadRequest:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aconst_null
        //   30: astore 6
        //   32: aload_2
        //   33: ifnull +11 -> 44
        //   36: aload_2
        //   37: invokeinterface 97 1 0
        //   42: astore 6
        //   44: aload 4
        //   46: aload 6
        //   48: invokevirtual 50	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   51: aload 4
        //   53: aload_3
        //   54: invokevirtual 53	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   57: aload_0
        //   58: getfield 15	com/google/android/gms/internal/kp$a$a:lb	Landroid/os/IBinder;
        //   61: bipush 10
        //   63: aload 4
        //   65: aconst_null
        //   66: iconst_1
        //   67: invokeinterface 59 5 0
        //   72: pop
        //   73: aload 4
        //   75: invokevirtual 65	android/os/Parcel:recycle	()V
        //   78: return
        //   79: aload 4
        //   81: iconst_0
        //   82: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   85: goto -56 -> 29
        //   88: astore 5
        //   90: aload 4
        //   92: invokevirtual 65	android/os/Parcel:recycle	()V
        //   95: aload 5
        //   97: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	98	0	this	a
        //   0	98	1	paramSessionReadRequest	SessionReadRequest
        //   0	98	2	paramkr	kr
        //   0	98	3	paramString	String
        //   3	88	4	localParcel	Parcel
        //   88	8	5	localObject	Object
        //   30	17	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   5	12	88	finally
        //   16	29	88	finally
        //   36	44	88	finally
        //   44	73	88	finally
        //   79	85	88	finally
      }
      
      /* Error */
      public void a(StartBleScanRequest paramStartBleScanRequest, kt paramkt, String paramString)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 4
        //   5: aload 4
        //   7: ldc 27
        //   9: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   12: aload_1
        //   13: ifnull +66 -> 79
        //   16: aload 4
        //   18: iconst_1
        //   19: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   22: aload_1
        //   23: aload 4
        //   25: iconst_0
        //   26: invokevirtual 101	com/google/android/gms/fitness/request/StartBleScanRequest:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aconst_null
        //   30: astore 6
        //   32: aload_2
        //   33: ifnull +11 -> 44
        //   36: aload_2
        //   37: invokeinterface 47 1 0
        //   42: astore 6
        //   44: aload 4
        //   46: aload 6
        //   48: invokevirtual 50	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   51: aload 4
        //   53: aload_3
        //   54: invokevirtual 53	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   57: aload_0
        //   58: getfield 15	com/google/android/gms/internal/kp$a$a:lb	Landroid/os/IBinder;
        //   61: bipush 15
        //   63: aload 4
        //   65: aconst_null
        //   66: iconst_1
        //   67: invokeinterface 59 5 0
        //   72: pop
        //   73: aload 4
        //   75: invokevirtual 65	android/os/Parcel:recycle	()V
        //   78: return
        //   79: aload 4
        //   81: iconst_0
        //   82: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   85: goto -56 -> 29
        //   88: astore 5
        //   90: aload 4
        //   92: invokevirtual 65	android/os/Parcel:recycle	()V
        //   95: aload 5
        //   97: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	98	0	this	a
        //   0	98	1	paramStartBleScanRequest	StartBleScanRequest
        //   0	98	2	paramkt	kt
        //   0	98	3	paramString	String
        //   3	88	4	localParcel	Parcel
        //   88	8	5	localObject	Object
        //   30	17	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   5	12	88	finally
        //   16	29	88	finally
        //   36	44	88	finally
        //   44	73	88	finally
        //   79	85	88	finally
      }
      
      /* Error */
      public void a(aa paramaa, kt paramkt, String paramString)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 4
        //   5: aload 4
        //   7: ldc 27
        //   9: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   12: aload_1
        //   13: ifnull +66 -> 79
        //   16: aload 4
        //   18: iconst_1
        //   19: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   22: aload_1
        //   23: aload 4
        //   25: iconst_0
        //   26: invokevirtual 105	com/google/android/gms/fitness/request/aa:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aconst_null
        //   30: astore 6
        //   32: aload_2
        //   33: ifnull +11 -> 44
        //   36: aload_2
        //   37: invokeinterface 47 1 0
        //   42: astore 6
        //   44: aload 4
        //   46: aload 6
        //   48: invokevirtual 50	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   51: aload 4
        //   53: aload_3
        //   54: invokevirtual 53	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   57: aload_0
        //   58: getfield 15	com/google/android/gms/internal/kp$a$a:lb	Landroid/os/IBinder;
        //   61: bipush 21
        //   63: aload 4
        //   65: aconst_null
        //   66: iconst_1
        //   67: invokeinterface 59 5 0
        //   72: pop
        //   73: aload 4
        //   75: invokevirtual 65	android/os/Parcel:recycle	()V
        //   78: return
        //   79: aload 4
        //   81: iconst_0
        //   82: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   85: goto -56 -> 29
        //   88: astore 5
        //   90: aload 4
        //   92: invokevirtual 65	android/os/Parcel:recycle	()V
        //   95: aload 5
        //   97: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	98	0	this	a
        //   0	98	1	paramaa	aa
        //   0	98	2	paramkt	kt
        //   0	98	3	paramString	String
        //   3	88	4	localParcel	Parcel
        //   88	8	5	localObject	Object
        //   30	17	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   5	12	88	finally
        //   16	29	88	finally
        //   36	44	88	finally
        //   44	73	88	finally
        //   79	85	88	finally
      }
      
      /* Error */
      public void a(ad paramad, kt paramkt, String paramString)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 4
        //   5: aload 4
        //   7: ldc 27
        //   9: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   12: aload_1
        //   13: ifnull +66 -> 79
        //   16: aload 4
        //   18: iconst_1
        //   19: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   22: aload_1
        //   23: aload 4
        //   25: iconst_0
        //   26: invokevirtual 109	com/google/android/gms/fitness/request/ad:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aconst_null
        //   30: astore 6
        //   32: aload_2
        //   33: ifnull +11 -> 44
        //   36: aload_2
        //   37: invokeinterface 47 1 0
        //   42: astore 6
        //   44: aload 4
        //   46: aload 6
        //   48: invokevirtual 50	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   51: aload 4
        //   53: aload_3
        //   54: invokevirtual 53	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   57: aload_0
        //   58: getfield 15	com/google/android/gms/internal/kp$a$a:lb	Landroid/os/IBinder;
        //   61: bipush 16
        //   63: aload 4
        //   65: aconst_null
        //   66: iconst_1
        //   67: invokeinterface 59 5 0
        //   72: pop
        //   73: aload 4
        //   75: invokevirtual 65	android/os/Parcel:recycle	()V
        //   78: return
        //   79: aload 4
        //   81: iconst_0
        //   82: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   85: goto -56 -> 29
        //   88: astore 5
        //   90: aload 4
        //   92: invokevirtual 65	android/os/Parcel:recycle	()V
        //   95: aload 5
        //   97: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	98	0	this	a
        //   0	98	1	paramad	ad
        //   0	98	2	paramkt	kt
        //   0	98	3	paramString	String
        //   3	88	4	localParcel	Parcel
        //   88	8	5	localObject	Object
        //   30	17	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   5	12	88	finally
        //   16	29	88	finally
        //   36	44	88	finally
        //   44	73	88	finally
        //   79	85	88	finally
      }
      
      /* Error */
      public void a(af paramaf, kt paramkt, String paramString)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 4
        //   5: aload 4
        //   7: ldc 27
        //   9: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   12: aload_1
        //   13: ifnull +65 -> 78
        //   16: aload 4
        //   18: iconst_1
        //   19: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   22: aload_1
        //   23: aload 4
        //   25: iconst_0
        //   26: invokevirtual 113	com/google/android/gms/fitness/request/af:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aconst_null
        //   30: astore 6
        //   32: aload_2
        //   33: ifnull +11 -> 44
        //   36: aload_2
        //   37: invokeinterface 47 1 0
        //   42: astore 6
        //   44: aload 4
        //   46: aload 6
        //   48: invokevirtual 50	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   51: aload 4
        //   53: aload_3
        //   54: invokevirtual 53	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   57: aload_0
        //   58: getfield 15	com/google/android/gms/internal/kp$a$a:lb	Landroid/os/IBinder;
        //   61: iconst_4
        //   62: aload 4
        //   64: aconst_null
        //   65: iconst_1
        //   66: invokeinterface 59 5 0
        //   71: pop
        //   72: aload 4
        //   74: invokevirtual 65	android/os/Parcel:recycle	()V
        //   77: return
        //   78: aload 4
        //   80: iconst_0
        //   81: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   84: goto -55 -> 29
        //   87: astore 5
        //   89: aload 4
        //   91: invokevirtual 65	android/os/Parcel:recycle	()V
        //   94: aload 5
        //   96: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	97	0	this	a
        //   0	97	1	paramaf	af
        //   0	97	2	paramkt	kt
        //   0	97	3	paramString	String
        //   3	87	4	localParcel	Parcel
        //   87	8	5	localObject	Object
        //   30	17	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   5	12	87	finally
        //   16	29	87	finally
        //   36	44	87	finally
        //   44	72	87	finally
        //   78	84	87	finally
      }
      
      /* Error */
      public void a(ah paramah, kt paramkt, String paramString)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 4
        //   5: aload 4
        //   7: ldc 27
        //   9: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   12: aload_1
        //   13: ifnull +66 -> 79
        //   16: aload 4
        //   18: iconst_1
        //   19: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   22: aload_1
        //   23: aload 4
        //   25: iconst_0
        //   26: invokevirtual 117	com/google/android/gms/fitness/request/ah:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aconst_null
        //   30: astore 6
        //   32: aload_2
        //   33: ifnull +11 -> 44
        //   36: aload_2
        //   37: invokeinterface 47 1 0
        //   42: astore 6
        //   44: aload 4
        //   46: aload 6
        //   48: invokevirtual 50	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   51: aload 4
        //   53: aload_3
        //   54: invokevirtual 53	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   57: aload_0
        //   58: getfield 15	com/google/android/gms/internal/kp$a$a:lb	Landroid/os/IBinder;
        //   61: bipush 18
        //   63: aload 4
        //   65: aconst_null
        //   66: iconst_1
        //   67: invokeinterface 59 5 0
        //   72: pop
        //   73: aload 4
        //   75: invokevirtual 65	android/os/Parcel:recycle	()V
        //   78: return
        //   79: aload 4
        //   81: iconst_0
        //   82: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   85: goto -56 -> 29
        //   88: astore 5
        //   90: aload 4
        //   92: invokevirtual 65	android/os/Parcel:recycle	()V
        //   95: aload 5
        //   97: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	98	0	this	a
        //   0	98	1	paramah	ah
        //   0	98	2	paramkt	kt
        //   0	98	3	paramString	String
        //   3	88	4	localParcel	Parcel
        //   88	8	5	localObject	Object
        //   30	17	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   5	12	88	finally
        //   16	29	88	finally
        //   36	44	88	finally
        //   44	73	88	finally
        //   79	85	88	finally
      }
      
      /* Error */
      public void a(aj paramaj, kt paramkt, String paramString)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 4
        //   5: aload 4
        //   7: ldc 27
        //   9: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   12: aload_1
        //   13: ifnull +65 -> 78
        //   16: aload 4
        //   18: iconst_1
        //   19: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   22: aload_1
        //   23: aload 4
        //   25: iconst_0
        //   26: invokevirtual 121	com/google/android/gms/fitness/request/aj:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aconst_null
        //   30: astore 6
        //   32: aload_2
        //   33: ifnull +11 -> 44
        //   36: aload_2
        //   37: invokeinterface 47 1 0
        //   42: astore 6
        //   44: aload 4
        //   46: aload 6
        //   48: invokevirtual 50	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   51: aload 4
        //   53: aload_3
        //   54: invokevirtual 53	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   57: aload_0
        //   58: getfield 15	com/google/android/gms/internal/kp$a$a:lb	Landroid/os/IBinder;
        //   61: iconst_5
        //   62: aload 4
        //   64: aconst_null
        //   65: iconst_1
        //   66: invokeinterface 59 5 0
        //   71: pop
        //   72: aload 4
        //   74: invokevirtual 65	android/os/Parcel:recycle	()V
        //   77: return
        //   78: aload 4
        //   80: iconst_0
        //   81: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   84: goto -55 -> 29
        //   87: astore 5
        //   89: aload 4
        //   91: invokevirtual 65	android/os/Parcel:recycle	()V
        //   94: aload 5
        //   96: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	97	0	this	a
        //   0	97	1	paramaj	aj
        //   0	97	2	paramkt	kt
        //   0	97	3	paramString	String
        //   3	87	4	localParcel	Parcel
        //   87	8	5	localObject	Object
        //   30	17	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   5	12	87	finally
        //   16	29	87	finally
        //   36	44	87	finally
        //   44	72	87	finally
        //   78	84	87	finally
      }
      
      /* Error */
      public void a(b paramb, kt paramkt, String paramString)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 4
        //   5: aload 4
        //   7: ldc 27
        //   9: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   12: aload_1
        //   13: ifnull +66 -> 79
        //   16: aload 4
        //   18: iconst_1
        //   19: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   22: aload_1
        //   23: aload 4
        //   25: iconst_0
        //   26: invokevirtual 125	com/google/android/gms/fitness/request/b:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aconst_null
        //   30: astore 6
        //   32: aload_2
        //   33: ifnull +11 -> 44
        //   36: aload_2
        //   37: invokeinterface 47 1 0
        //   42: astore 6
        //   44: aload 4
        //   46: aload 6
        //   48: invokevirtual 50	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   51: aload 4
        //   53: aload_3
        //   54: invokevirtual 53	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   57: aload_0
        //   58: getfield 15	com/google/android/gms/internal/kp$a$a:lb	Landroid/os/IBinder;
        //   61: bipush 17
        //   63: aload 4
        //   65: aconst_null
        //   66: iconst_1
        //   67: invokeinterface 59 5 0
        //   72: pop
        //   73: aload 4
        //   75: invokevirtual 65	android/os/Parcel:recycle	()V
        //   78: return
        //   79: aload 4
        //   81: iconst_0
        //   82: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   85: goto -56 -> 29
        //   88: astore 5
        //   90: aload 4
        //   92: invokevirtual 65	android/os/Parcel:recycle	()V
        //   95: aload 5
        //   97: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	98	0	this	a
        //   0	98	1	paramb	b
        //   0	98	2	paramkt	kt
        //   0	98	3	paramString	String
        //   3	88	4	localParcel	Parcel
        //   88	8	5	localObject	Object
        //   30	17	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   5	12	88	finally
        //   16	29	88	finally
        //   36	44	88	finally
        //   44	73	88	finally
        //   79	85	88	finally
      }
      
      /* Error */
      public void a(e parame, kt paramkt, String paramString)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 4
        //   5: aload 4
        //   7: ldc 27
        //   9: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   12: aload_1
        //   13: ifnull +66 -> 79
        //   16: aload 4
        //   18: iconst_1
        //   19: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   22: aload_1
        //   23: aload 4
        //   25: iconst_0
        //   26: invokevirtual 129	com/google/android/gms/fitness/request/e:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aconst_null
        //   30: astore 6
        //   32: aload_2
        //   33: ifnull +11 -> 44
        //   36: aload_2
        //   37: invokeinterface 47 1 0
        //   42: astore 6
        //   44: aload 4
        //   46: aload 6
        //   48: invokevirtual 50	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   51: aload 4
        //   53: aload_3
        //   54: invokevirtual 53	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   57: aload_0
        //   58: getfield 15	com/google/android/gms/internal/kp$a$a:lb	Landroid/os/IBinder;
        //   61: bipush 7
        //   63: aload 4
        //   65: aconst_null
        //   66: iconst_1
        //   67: invokeinterface 59 5 0
        //   72: pop
        //   73: aload 4
        //   75: invokevirtual 65	android/os/Parcel:recycle	()V
        //   78: return
        //   79: aload 4
        //   81: iconst_0
        //   82: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   85: goto -56 -> 29
        //   88: astore 5
        //   90: aload 4
        //   92: invokevirtual 65	android/os/Parcel:recycle	()V
        //   95: aload 5
        //   97: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	98	0	this	a
        //   0	98	1	parame	e
        //   0	98	2	paramkt	kt
        //   0	98	3	paramString	String
        //   3	88	4	localParcel	Parcel
        //   88	8	5	localObject	Object
        //   30	17	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   5	12	88	finally
        //   16	29	88	finally
        //   36	44	88	finally
        //   44	73	88	finally
        //   79	85	88	finally
      }
      
      /* Error */
      public void a(j paramj, ko paramko, String paramString)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 4
        //   5: aload 4
        //   7: ldc 27
        //   9: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   12: aload_1
        //   13: ifnull +66 -> 79
        //   16: aload 4
        //   18: iconst_1
        //   19: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   22: aload_1
        //   23: aload 4
        //   25: iconst_0
        //   26: invokevirtual 133	com/google/android/gms/fitness/request/j:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aconst_null
        //   30: astore 6
        //   32: aload_2
        //   33: ifnull +11 -> 44
        //   36: aload_2
        //   37: invokeinterface 86 1 0
        //   42: astore 6
        //   44: aload 4
        //   46: aload 6
        //   48: invokevirtual 50	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   51: aload 4
        //   53: aload_3
        //   54: invokevirtual 53	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   57: aload_0
        //   58: getfield 15	com/google/android/gms/internal/kp$a$a:lb	Landroid/os/IBinder;
        //   61: bipush 14
        //   63: aload 4
        //   65: aconst_null
        //   66: iconst_1
        //   67: invokeinterface 59 5 0
        //   72: pop
        //   73: aload 4
        //   75: invokevirtual 65	android/os/Parcel:recycle	()V
        //   78: return
        //   79: aload 4
        //   81: iconst_0
        //   82: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   85: goto -56 -> 29
        //   88: astore 5
        //   90: aload 4
        //   92: invokevirtual 65	android/os/Parcel:recycle	()V
        //   95: aload 5
        //   97: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	98	0	this	a
        //   0	98	1	paramj	j
        //   0	98	2	paramko	ko
        //   0	98	3	paramString	String
        //   3	88	4	localParcel	Parcel
        //   88	8	5	localObject	Object
        //   30	17	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   5	12	88	finally
        //   16	29	88	finally
        //   36	44	88	finally
        //   44	73	88	finally
        //   79	85	88	finally
      }
      
      /* Error */
      public void a(m paramm, kq paramkq, String paramString)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 4
        //   5: aload 4
        //   7: ldc 27
        //   9: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   12: aload_1
        //   13: ifnull +66 -> 79
        //   16: aload 4
        //   18: iconst_1
        //   19: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   22: aload_1
        //   23: aload 4
        //   25: iconst_0
        //   26: invokevirtual 137	com/google/android/gms/fitness/request/m:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aconst_null
        //   30: astore 6
        //   32: aload_2
        //   33: ifnull +11 -> 44
        //   36: aload_2
        //   37: invokeinterface 140 1 0
        //   42: astore 6
        //   44: aload 4
        //   46: aload 6
        //   48: invokevirtual 50	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   51: aload 4
        //   53: aload_3
        //   54: invokevirtual 53	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   57: aload_0
        //   58: getfield 15	com/google/android/gms/internal/kp$a$a:lb	Landroid/os/IBinder;
        //   61: bipush 6
        //   63: aload 4
        //   65: aconst_null
        //   66: iconst_1
        //   67: invokeinterface 59 5 0
        //   72: pop
        //   73: aload 4
        //   75: invokevirtual 65	android/os/Parcel:recycle	()V
        //   78: return
        //   79: aload 4
        //   81: iconst_0
        //   82: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   85: goto -56 -> 29
        //   88: astore 5
        //   90: aload 4
        //   92: invokevirtual 65	android/os/Parcel:recycle	()V
        //   95: aload 5
        //   97: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	98	0	this	a
        //   0	98	1	paramm	m
        //   0	98	2	paramkq	kq
        //   0	98	3	paramString	String
        //   3	88	4	localParcel	Parcel
        //   88	8	5	localObject	Object
        //   30	17	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   5	12	88	finally
        //   16	29	88	finally
        //   36	44	88	finally
        //   44	73	88	finally
        //   79	85	88	finally
      }
      
      /* Error */
      public void a(o paramo, kt paramkt, String paramString)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 4
        //   5: aload 4
        //   7: ldc 27
        //   9: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   12: aload_1
        //   13: ifnull +65 -> 78
        //   16: aload 4
        //   18: iconst_1
        //   19: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   22: aload_1
        //   23: aload 4
        //   25: iconst_0
        //   26: invokevirtual 144	com/google/android/gms/fitness/request/o:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aconst_null
        //   30: astore 6
        //   32: aload_2
        //   33: ifnull +11 -> 44
        //   36: aload_2
        //   37: invokeinterface 47 1 0
        //   42: astore 6
        //   44: aload 4
        //   46: aload 6
        //   48: invokevirtual 50	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   51: aload 4
        //   53: aload_3
        //   54: invokevirtual 53	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   57: aload_0
        //   58: getfield 15	com/google/android/gms/internal/kp$a$a:lb	Landroid/os/IBinder;
        //   61: iconst_2
        //   62: aload 4
        //   64: aconst_null
        //   65: iconst_1
        //   66: invokeinterface 59 5 0
        //   71: pop
        //   72: aload 4
        //   74: invokevirtual 65	android/os/Parcel:recycle	()V
        //   77: return
        //   78: aload 4
        //   80: iconst_0
        //   81: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   84: goto -55 -> 29
        //   87: astore 5
        //   89: aload 4
        //   91: invokevirtual 65	android/os/Parcel:recycle	()V
        //   94: aload 5
        //   96: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	97	0	this	a
        //   0	97	1	paramo	o
        //   0	97	2	paramkt	kt
        //   0	97	3	paramString	String
        //   3	87	4	localParcel	Parcel
        //   87	8	5	localObject	Object
        //   30	17	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   5	12	87	finally
        //   16	29	87	finally
        //   36	44	87	finally
        //   44	72	87	finally
        //   78	84	87	finally
      }
      
      /* Error */
      public void a(q paramq, kt paramkt, String paramString)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 4
        //   5: aload 4
        //   7: ldc 27
        //   9: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   12: aload_1
        //   13: ifnull +65 -> 78
        //   16: aload 4
        //   18: iconst_1
        //   19: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   22: aload_1
        //   23: aload 4
        //   25: iconst_0
        //   26: invokevirtual 148	com/google/android/gms/fitness/request/q:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aconst_null
        //   30: astore 6
        //   32: aload_2
        //   33: ifnull +11 -> 44
        //   36: aload_2
        //   37: invokeinterface 47 1 0
        //   42: astore 6
        //   44: aload 4
        //   46: aload 6
        //   48: invokevirtual 50	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   51: aload 4
        //   53: aload_3
        //   54: invokevirtual 53	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   57: aload_0
        //   58: getfield 15	com/google/android/gms/internal/kp$a$a:lb	Landroid/os/IBinder;
        //   61: iconst_3
        //   62: aload 4
        //   64: aconst_null
        //   65: iconst_1
        //   66: invokeinterface 59 5 0
        //   71: pop
        //   72: aload 4
        //   74: invokevirtual 65	android/os/Parcel:recycle	()V
        //   77: return
        //   78: aload 4
        //   80: iconst_0
        //   81: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   84: goto -55 -> 29
        //   87: astore 5
        //   89: aload 4
        //   91: invokevirtual 65	android/os/Parcel:recycle	()V
        //   94: aload 5
        //   96: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	97	0	this	a
        //   0	97	1	paramq	q
        //   0	97	2	paramkt	kt
        //   0	97	3	paramString	String
        //   3	87	4	localParcel	Parcel
        //   87	8	5	localObject	Object
        //   30	17	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   5	12	87	finally
        //   16	29	87	finally
        //   36	44	87	finally
        //   44	72	87	finally
        //   78	84	87	finally
      }
      
      /* Error */
      public void a(u paramu, kt paramkt, String paramString)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 4
        //   5: aload 4
        //   7: ldc 27
        //   9: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   12: aload_1
        //   13: ifnull +66 -> 79
        //   16: aload 4
        //   18: iconst_1
        //   19: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   22: aload_1
        //   23: aload 4
        //   25: iconst_0
        //   26: invokevirtual 152	com/google/android/gms/fitness/request/u:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aconst_null
        //   30: astore 6
        //   32: aload_2
        //   33: ifnull +11 -> 44
        //   36: aload_2
        //   37: invokeinterface 47 1 0
        //   42: astore 6
        //   44: aload 4
        //   46: aload 6
        //   48: invokevirtual 50	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   51: aload 4
        //   53: aload_3
        //   54: invokevirtual 53	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   57: aload_0
        //   58: getfield 15	com/google/android/gms/internal/kp$a$a:lb	Landroid/os/IBinder;
        //   61: bipush 20
        //   63: aload 4
        //   65: aconst_null
        //   66: iconst_1
        //   67: invokeinterface 59 5 0
        //   72: pop
        //   73: aload 4
        //   75: invokevirtual 65	android/os/Parcel:recycle	()V
        //   78: return
        //   79: aload 4
        //   81: iconst_0
        //   82: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   85: goto -56 -> 29
        //   88: astore 5
        //   90: aload 4
        //   92: invokevirtual 65	android/os/Parcel:recycle	()V
        //   95: aload 5
        //   97: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	98	0	this	a
        //   0	98	1	paramu	u
        //   0	98	2	paramkt	kt
        //   0	98	3	paramString	String
        //   3	88	4	localParcel	Parcel
        //   88	8	5	localObject	Object
        //   30	17	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   5	12	88	finally
        //   16	29	88	finally
        //   36	44	88	finally
        //   44	73	88	finally
        //   79	85	88	finally
      }
      
      /* Error */
      public void a(w paramw, kt paramkt, String paramString)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 4
        //   5: aload 4
        //   7: ldc 27
        //   9: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   12: aload_1
        //   13: ifnull +66 -> 79
        //   16: aload 4
        //   18: iconst_1
        //   19: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   22: aload_1
        //   23: aload 4
        //   25: iconst_0
        //   26: invokevirtual 156	com/google/android/gms/fitness/request/w:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aconst_null
        //   30: astore 6
        //   32: aload_2
        //   33: ifnull +11 -> 44
        //   36: aload_2
        //   37: invokeinterface 47 1 0
        //   42: astore 6
        //   44: aload 4
        //   46: aload 6
        //   48: invokevirtual 50	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   51: aload 4
        //   53: aload_3
        //   54: invokevirtual 53	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   57: aload_0
        //   58: getfield 15	com/google/android/gms/internal/kp$a$a:lb	Landroid/os/IBinder;
        //   61: bipush 11
        //   63: aload 4
        //   65: aconst_null
        //   66: iconst_1
        //   67: invokeinterface 59 5 0
        //   72: pop
        //   73: aload 4
        //   75: invokevirtual 65	android/os/Parcel:recycle	()V
        //   78: return
        //   79: aload 4
        //   81: iconst_0
        //   82: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   85: goto -56 -> 29
        //   88: astore 5
        //   90: aload 4
        //   92: invokevirtual 65	android/os/Parcel:recycle	()V
        //   95: aload 5
        //   97: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	98	0	this	a
        //   0	98	1	paramw	w
        //   0	98	2	paramkt	kt
        //   0	98	3	paramString	String
        //   3	88	4	localParcel	Parcel
        //   88	8	5	localObject	Object
        //   30	17	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   5	12	88	finally
        //   16	29	88	finally
        //   36	44	88	finally
        //   44	73	88	finally
        //   79	85	88	finally
      }
      
      /* Error */
      public void a(y paramy, ks paramks, String paramString)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 4
        //   5: aload 4
        //   7: ldc 27
        //   9: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   12: aload_1
        //   13: ifnull +66 -> 79
        //   16: aload 4
        //   18: iconst_1
        //   19: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   22: aload_1
        //   23: aload 4
        //   25: iconst_0
        //   26: invokevirtual 160	com/google/android/gms/fitness/request/y:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aconst_null
        //   30: astore 6
        //   32: aload_2
        //   33: ifnull +11 -> 44
        //   36: aload_2
        //   37: invokeinterface 163 1 0
        //   42: astore 6
        //   44: aload 4
        //   46: aload 6
        //   48: invokevirtual 50	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   51: aload 4
        //   53: aload_3
        //   54: invokevirtual 53	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   57: aload_0
        //   58: getfield 15	com/google/android/gms/internal/kp$a$a:lb	Landroid/os/IBinder;
        //   61: bipush 12
        //   63: aload 4
        //   65: aconst_null
        //   66: iconst_1
        //   67: invokeinterface 59 5 0
        //   72: pop
        //   73: aload 4
        //   75: invokevirtual 65	android/os/Parcel:recycle	()V
        //   78: return
        //   79: aload 4
        //   81: iconst_0
        //   82: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   85: goto -56 -> 29
        //   88: astore 5
        //   90: aload 4
        //   92: invokevirtual 65	android/os/Parcel:recycle	()V
        //   95: aload 5
        //   97: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	98	0	this	a
        //   0	98	1	paramy	y
        //   0	98	2	paramks	ks
        //   0	98	3	paramString	String
        //   3	88	4	localParcel	Parcel
        //   88	8	5	localObject	Object
        //   30	17	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   5	12	88	finally
        //   16	29	88	finally
        //   36	44	88	finally
        //   44	73	88	finally
        //   79	85	88	finally
      }
      
      public void a(kt paramkt, String paramString)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.google.android.gms.fitness.internal.IGoogleFitnessService");
          IBinder localIBinder = null;
          if (paramkt != null) {
            localIBinder = paramkt.asBinder();
          }
          localParcel.writeStrongBinder(localIBinder);
          localParcel.writeString(paramString);
          this.lb.transact(22, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
      }
      
      public void a(lf paramlf, String paramString)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.google.android.gms.fitness.internal.IGoogleFitnessService");
          IBinder localIBinder = null;
          if (paramlf != null) {
            localIBinder = paramlf.asBinder();
          }
          localParcel.writeStrongBinder(localIBinder);
          localParcel.writeString(paramString);
          this.lb.transact(24, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
      }
      
      public IBinder asBinder()
      {
        return this.lb;
      }
      
      public void b(kt paramkt, String paramString)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.google.android.gms.fitness.internal.IGoogleFitnessService");
          IBinder localIBinder = null;
          if (paramkt != null) {
            localIBinder = paramkt.asBinder();
          }
          localParcel.writeStrongBinder(localIBinder);
          localParcel.writeString(paramString);
          this.lb.transact(23, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
      }
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.kp
 * JD-Core Version:    0.7.0.1
 */