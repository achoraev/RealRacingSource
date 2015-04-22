package com.google.android.gms.games.internal;

import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.games.achievement.AchievementEntity;
import com.google.android.gms.games.internal.multiplayer.ZInvitationCluster;
import com.google.android.gms.games.internal.request.GameRequestCluster;
import com.google.android.gms.games.multiplayer.ParticipantEntity;
import com.google.android.gms.games.multiplayer.ParticipantResult;
import com.google.android.gms.games.multiplayer.realtime.RoomEntity;
import com.google.android.gms.games.snapshot.SnapshotMetadataChange;

public abstract interface IGamesService
  extends IInterface
{
  public abstract void N(boolean paramBoolean)
    throws RemoteException;
  
  public abstract void O(boolean paramBoolean)
    throws RemoteException;
  
  public abstract void P(boolean paramBoolean)
    throws RemoteException;
  
  public abstract int a(IGamesCallbacks paramIGamesCallbacks, byte[] paramArrayOfByte, String paramString1, String paramString2)
    throws RemoteException;
  
  public abstract Intent a(int paramInt1, int paramInt2, boolean paramBoolean)
    throws RemoteException;
  
  public abstract Intent a(int paramInt1, byte[] paramArrayOfByte, int paramInt2, String paramString)
    throws RemoteException;
  
  public abstract Intent a(AchievementEntity paramAchievementEntity)
    throws RemoteException;
  
  public abstract Intent a(ZInvitationCluster paramZInvitationCluster, String paramString1, String paramString2)
    throws RemoteException;
  
  public abstract Intent a(GameRequestCluster paramGameRequestCluster, String paramString)
    throws RemoteException;
  
  public abstract Intent a(RoomEntity paramRoomEntity, int paramInt)
    throws RemoteException;
  
  public abstract Intent a(String paramString, boolean paramBoolean1, boolean paramBoolean2, int paramInt)
    throws RemoteException;
  
  public abstract Intent a(ParticipantEntity[] paramArrayOfParticipantEntity, String paramString1, String paramString2, Uri paramUri1, Uri paramUri2)
    throws RemoteException;
  
  public abstract void a(long paramLong, String paramString)
    throws RemoteException;
  
  public abstract void a(IBinder paramIBinder, Bundle paramBundle)
    throws RemoteException;
  
  public abstract void a(Contents paramContents)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, int paramInt)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, int paramInt1, int paramInt2, int paramInt3)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, int paramInt1, int paramInt2, String[] paramArrayOfString, Bundle paramBundle)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, int paramInt, int[] paramArrayOfInt)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, long paramLong)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, long paramLong, String paramString)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, Bundle paramBundle, int paramInt1, int paramInt2)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, IBinder paramIBinder, int paramInt, String[] paramArrayOfString, Bundle paramBundle, boolean paramBoolean, long paramLong)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, IBinder paramIBinder, String paramString, boolean paramBoolean, long paramLong)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, String paramString)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt, IBinder paramIBinder, Bundle paramBundle)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt, boolean paramBoolean)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt, int[] paramArrayOfInt)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, String paramString, long paramLong)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, String paramString1, long paramLong, String paramString2)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, String paramString, IBinder paramIBinder, Bundle paramBundle)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, String paramString, SnapshotMetadataChange paramSnapshotMetadataChange, Contents paramContents)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2, int paramInt1, int paramInt2)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2, SnapshotMetadataChange paramSnapshotMetadataChange, Contents paramContents)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2, boolean paramBoolean)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2, int[] paramArrayOfInt, int paramInt, boolean paramBoolean)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2, String[] paramArrayOfString)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2, String[] paramArrayOfString, boolean paramBoolean)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, String paramString, boolean paramBoolean)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, String paramString1, byte[] paramArrayOfByte, String paramString2, ParticipantResult[] paramArrayOfParticipantResult)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, String paramString, byte[] paramArrayOfByte, ParticipantResult[] paramArrayOfParticipantResult)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, String paramString, int[] paramArrayOfInt)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, String paramString, String[] paramArrayOfString, int paramInt1, byte[] paramArrayOfByte, int paramInt2)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, boolean paramBoolean)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, boolean paramBoolean, Bundle paramBundle)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, boolean paramBoolean, String[] paramArrayOfString)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, int[] paramArrayOfInt)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, int[] paramArrayOfInt, int paramInt, boolean paramBoolean)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, String[] paramArrayOfString)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, String[] paramArrayOfString, boolean paramBoolean)
    throws RemoteException;
  
  public abstract void a(String paramString, IBinder paramIBinder, Bundle paramBundle)
    throws RemoteException;
  
  public abstract int b(byte[] paramArrayOfByte, String paramString, String[] paramArrayOfString)
    throws RemoteException;
  
  public abstract Intent b(int paramInt1, int paramInt2, boolean paramBoolean)
    throws RemoteException;
  
  public abstract Intent b(int[] paramArrayOfInt)
    throws RemoteException;
  
  public abstract void b(long paramLong, String paramString)
    throws RemoteException;
  
  public abstract void b(IGamesCallbacks paramIGamesCallbacks)
    throws RemoteException;
  
  public abstract void b(IGamesCallbacks paramIGamesCallbacks, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
    throws RemoteException;
  
  public abstract void b(IGamesCallbacks paramIGamesCallbacks, long paramLong)
    throws RemoteException;
  
  public abstract void b(IGamesCallbacks paramIGamesCallbacks, long paramLong, String paramString)
    throws RemoteException;
  
  public abstract void b(IGamesCallbacks paramIGamesCallbacks, String paramString)
    throws RemoteException;
  
  public abstract void b(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt)
    throws RemoteException;
  
  public abstract void b(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
    throws RemoteException;
  
  public abstract void b(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt, IBinder paramIBinder, Bundle paramBundle)
    throws RemoteException;
  
  public abstract void b(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt, boolean paramBoolean)
    throws RemoteException;
  
  public abstract void b(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
    throws RemoteException;
  
  public abstract void b(IGamesCallbacks paramIGamesCallbacks, String paramString, IBinder paramIBinder, Bundle paramBundle)
    throws RemoteException;
  
  public abstract void b(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2)
    throws RemoteException;
  
  public abstract void b(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
    throws RemoteException;
  
  public abstract void b(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
    throws RemoteException;
  
  public abstract void b(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2, boolean paramBoolean)
    throws RemoteException;
  
  public abstract void b(IGamesCallbacks paramIGamesCallbacks, String paramString, boolean paramBoolean)
    throws RemoteException;
  
  public abstract void b(IGamesCallbacks paramIGamesCallbacks, boolean paramBoolean)
    throws RemoteException;
  
  public abstract void b(IGamesCallbacks paramIGamesCallbacks, String[] paramArrayOfString)
    throws RemoteException;
  
  public abstract void b(String paramString1, String paramString2, int paramInt)
    throws RemoteException;
  
  public abstract Intent bC(String paramString)
    throws RemoteException;
  
  public abstract String bE(String paramString)
    throws RemoteException;
  
  public abstract String bF(String paramString)
    throws RemoteException;
  
  public abstract void bG(String paramString)
    throws RemoteException;
  
  public abstract int bH(String paramString)
    throws RemoteException;
  
  public abstract Uri bI(String paramString)
    throws RemoteException;
  
  public abstract void bJ(String paramString)
    throws RemoteException;
  
  public abstract ParcelFileDescriptor bK(String paramString)
    throws RemoteException;
  
  public abstract Intent bx(String paramString)
    throws RemoteException;
  
  public abstract void c(long paramLong, String paramString)
    throws RemoteException;
  
  public abstract void c(IGamesCallbacks paramIGamesCallbacks)
    throws RemoteException;
  
  public abstract void c(IGamesCallbacks paramIGamesCallbacks, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
    throws RemoteException;
  
  public abstract void c(IGamesCallbacks paramIGamesCallbacks, long paramLong)
    throws RemoteException;
  
  public abstract void c(IGamesCallbacks paramIGamesCallbacks, long paramLong, String paramString)
    throws RemoteException;
  
  public abstract void c(IGamesCallbacks paramIGamesCallbacks, String paramString)
    throws RemoteException;
  
  public abstract void c(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt)
    throws RemoteException;
  
  public abstract void c(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
    throws RemoteException;
  
  public abstract void c(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2)
    throws RemoteException;
  
  public abstract void c(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2, boolean paramBoolean)
    throws RemoteException;
  
  public abstract void c(IGamesCallbacks paramIGamesCallbacks, String paramString, boolean paramBoolean)
    throws RemoteException;
  
  public abstract void c(IGamesCallbacks paramIGamesCallbacks, boolean paramBoolean)
    throws RemoteException;
  
  public abstract void c(IGamesCallbacks paramIGamesCallbacks, String[] paramArrayOfString)
    throws RemoteException;
  
  public abstract void c(String paramString1, String paramString2, int paramInt)
    throws RemoteException;
  
  public abstract void d(long paramLong, String paramString)
    throws RemoteException;
  
  public abstract void d(IGamesCallbacks paramIGamesCallbacks)
    throws RemoteException;
  
  public abstract void d(IGamesCallbacks paramIGamesCallbacks, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
    throws RemoteException;
  
  public abstract void d(IGamesCallbacks paramIGamesCallbacks, long paramLong)
    throws RemoteException;
  
  public abstract void d(IGamesCallbacks paramIGamesCallbacks, long paramLong, String paramString)
    throws RemoteException;
  
  public abstract void d(IGamesCallbacks paramIGamesCallbacks, String paramString)
    throws RemoteException;
  
  public abstract void d(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
    throws RemoteException;
  
  public abstract void d(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2)
    throws RemoteException;
  
  public abstract void d(IGamesCallbacks paramIGamesCallbacks, String paramString, boolean paramBoolean)
    throws RemoteException;
  
  public abstract void d(IGamesCallbacks paramIGamesCallbacks, boolean paramBoolean)
    throws RemoteException;
  
  public abstract void dC(int paramInt)
    throws RemoteException;
  
  public abstract void e(IGamesCallbacks paramIGamesCallbacks)
    throws RemoteException;
  
  public abstract void e(IGamesCallbacks paramIGamesCallbacks, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
    throws RemoteException;
  
  public abstract void e(IGamesCallbacks paramIGamesCallbacks, String paramString)
    throws RemoteException;
  
  public abstract void e(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
    throws RemoteException;
  
  public abstract void e(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2)
    throws RemoteException;
  
  public abstract void e(IGamesCallbacks paramIGamesCallbacks, String paramString, boolean paramBoolean)
    throws RemoteException;
  
  public abstract void e(IGamesCallbacks paramIGamesCallbacks, boolean paramBoolean)
    throws RemoteException;
  
  public abstract void f(IGamesCallbacks paramIGamesCallbacks)
    throws RemoteException;
  
  public abstract void f(IGamesCallbacks paramIGamesCallbacks, String paramString)
    throws RemoteException;
  
  public abstract void f(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
    throws RemoteException;
  
  public abstract void f(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2)
    throws RemoteException;
  
  public abstract void f(IGamesCallbacks paramIGamesCallbacks, boolean paramBoolean)
    throws RemoteException;
  
  public abstract Bundle fC()
    throws RemoteException;
  
  public abstract void g(IGamesCallbacks paramIGamesCallbacks)
    throws RemoteException;
  
  public abstract void g(IGamesCallbacks paramIGamesCallbacks, String paramString)
    throws RemoteException;
  
  public abstract void g(IGamesCallbacks paramIGamesCallbacks, boolean paramBoolean)
    throws RemoteException;
  
  public abstract ParcelFileDescriptor h(Uri paramUri)
    throws RemoteException;
  
  public abstract RoomEntity h(IGamesCallbacks paramIGamesCallbacks, String paramString)
    throws RemoteException;
  
  public abstract void h(IGamesCallbacks paramIGamesCallbacks)
    throws RemoteException;
  
  public abstract void h(IGamesCallbacks paramIGamesCallbacks, boolean paramBoolean)
    throws RemoteException;
  
  public abstract void i(IGamesCallbacks paramIGamesCallbacks)
    throws RemoteException;
  
  public abstract void i(IGamesCallbacks paramIGamesCallbacks, String paramString)
    throws RemoteException;
  
  public abstract void j(IGamesCallbacks paramIGamesCallbacks)
    throws RemoteException;
  
  public abstract void j(IGamesCallbacks paramIGamesCallbacks, String paramString)
    throws RemoteException;
  
  public abstract void k(IGamesCallbacks paramIGamesCallbacks, String paramString)
    throws RemoteException;
  
  public abstract boolean kA()
    throws RemoteException;
  
  public abstract DataHolder kB()
    throws RemoteException;
  
  public abstract void kC()
    throws RemoteException;
  
  public abstract Intent kD()
    throws RemoteException;
  
  public abstract void kE()
    throws RemoteException;
  
  public abstract boolean kF()
    throws RemoteException;
  
  public abstract String ka()
    throws RemoteException;
  
  public abstract String kb()
    throws RemoteException;
  
  public abstract Intent ke()
    throws RemoteException;
  
  public abstract Intent kf()
    throws RemoteException;
  
  public abstract Intent kg()
    throws RemoteException;
  
  public abstract Intent kh()
    throws RemoteException;
  
  public abstract Intent km()
    throws RemoteException;
  
  public abstract Intent kn()
    throws RemoteException;
  
  public abstract int ko()
    throws RemoteException;
  
  public abstract String kp()
    throws RemoteException;
  
  public abstract int kq()
    throws RemoteException;
  
  public abstract Intent kr()
    throws RemoteException;
  
  public abstract int ks()
    throws RemoteException;
  
  public abstract int kt()
    throws RemoteException;
  
  public abstract int ku()
    throws RemoteException;
  
  public abstract int kv()
    throws RemoteException;
  
  public abstract void kx()
    throws RemoteException;
  
  public abstract DataHolder kz()
    throws RemoteException;
  
  public abstract void l(IGamesCallbacks paramIGamesCallbacks, String paramString)
    throws RemoteException;
  
  public abstract void m(IGamesCallbacks paramIGamesCallbacks, String paramString)
    throws RemoteException;
  
  public abstract void n(IGamesCallbacks paramIGamesCallbacks, String paramString)
    throws RemoteException;
  
  public abstract void n(String paramString, int paramInt)
    throws RemoteException;
  
  public abstract void o(IGamesCallbacks paramIGamesCallbacks, String paramString)
    throws RemoteException;
  
  public abstract void o(String paramString, int paramInt)
    throws RemoteException;
  
  public abstract void p(IGamesCallbacks paramIGamesCallbacks, String paramString)
    throws RemoteException;
  
  public abstract void p(String paramString, int paramInt)
    throws RemoteException;
  
  public abstract void q(long paramLong)
    throws RemoteException;
  
  public abstract void q(IGamesCallbacks paramIGamesCallbacks, String paramString)
    throws RemoteException;
  
  public abstract void r(long paramLong)
    throws RemoteException;
  
  public abstract void r(IGamesCallbacks paramIGamesCallbacks, String paramString)
    throws RemoteException;
  
  public abstract void r(String paramString, int paramInt)
    throws RemoteException;
  
  public abstract void s(long paramLong)
    throws RemoteException;
  
  public abstract void s(IGamesCallbacks paramIGamesCallbacks, String paramString)
    throws RemoteException;
  
  public abstract void s(String paramString, int paramInt)
    throws RemoteException;
  
  public abstract void t(long paramLong)
    throws RemoteException;
  
  public abstract void t(IGamesCallbacks paramIGamesCallbacks, String paramString)
    throws RemoteException;
  
  public abstract void u(long paramLong)
    throws RemoteException;
  
  public abstract void u(IGamesCallbacks paramIGamesCallbacks, String paramString)
    throws RemoteException;
  
  public abstract void u(String paramString1, String paramString2)
    throws RemoteException;
  
  public abstract void v(String paramString1, String paramString2)
    throws RemoteException;
  
  public static abstract class Stub
    extends Binder
    implements IGamesService
  {
    public Stub()
    {
      attachInterface(this, "com.google.android.gms.games.internal.IGamesService");
    }
    
    public static IGamesService aB(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.games.internal.IGamesService");
      if ((localIInterface != null) && ((localIInterface instanceof IGamesService))) {
        return (IGamesService)localIInterface;
      }
      return new Proxy(paramIBinder);
    }
    
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      switch (paramInt1)
      {
      default: 
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902: 
        paramParcel2.writeString("com.google.android.gms.games.internal.IGamesService");
        return true;
      case 5001: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        q(paramParcel1.readLong());
        paramParcel2.writeNoException();
        return true;
      case 5002: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        a(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 5003: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        String str = kp();
      }
    }
    
    private static class Proxy
      implements IGamesService
    {
      private IBinder lb;
      
      Proxy(IBinder paramIBinder)
      {
        this.lb = paramIBinder;
      }
      
      public void N(boolean paramBoolean)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          int i = 0;
          if (paramBoolean) {
            i = 1;
          }
          localParcel1.writeInt(i);
          this.lb.transact(5068, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void O(boolean paramBoolean)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          int i = 0;
          if (paramBoolean) {
            i = 1;
          }
          localParcel1.writeInt(i);
          this.lb.transact(12026, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void P(boolean paramBoolean)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          int i = 0;
          if (paramBoolean) {
            i = 1;
          }
          localParcel1.writeInt(i);
          this.lb.transact(13001, localParcel1, localParcel2, 0);
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
      public int a(IGamesCallbacks paramIGamesCallbacks, byte[] paramArrayOfByte, String paramString1, String paramString2)
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
        //   18: ifnull +80 -> 98
        //   21: aload_1
        //   22: invokeinterface 57 1 0
        //   27: astore 8
        //   29: aload 5
        //   31: aload 8
        //   33: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload 5
        //   38: aload_2
        //   39: invokevirtual 64	android/os/Parcel:writeByteArray	([B)V
        //   42: aload 5
        //   44: aload_3
        //   45: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   48: aload 5
        //   50: aload 4
        //   52: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   55: aload_0
        //   56: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   59: sipush 5033
        //   62: aload 5
        //   64: aload 6
        //   66: iconst_0
        //   67: invokeinterface 41 5 0
        //   72: pop
        //   73: aload 6
        //   75: invokevirtual 44	android/os/Parcel:readException	()V
        //   78: aload 6
        //   80: invokevirtual 71	android/os/Parcel:readInt	()I
        //   83: istore 10
        //   85: aload 6
        //   87: invokevirtual 47	android/os/Parcel:recycle	()V
        //   90: aload 5
        //   92: invokevirtual 47	android/os/Parcel:recycle	()V
        //   95: iload 10
        //   97: ireturn
        //   98: aconst_null
        //   99: astore 8
        //   101: goto -72 -> 29
        //   104: astore 7
        //   106: aload 6
        //   108: invokevirtual 47	android/os/Parcel:recycle	()V
        //   111: aload 5
        //   113: invokevirtual 47	android/os/Parcel:recycle	()V
        //   116: aload 7
        //   118: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	119	0	this	Proxy
        //   0	119	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	119	2	paramArrayOfByte	byte[]
        //   0	119	3	paramString1	String
        //   0	119	4	paramString2	String
        //   3	109	5	localParcel1	Parcel
        //   8	99	6	localParcel2	Parcel
        //   104	13	7	localObject	Object
        //   27	73	8	localIBinder	IBinder
        //   83	13	10	i	int
        // Exception table:
        //   from	to	target	type
        //   10	17	104	finally
        //   21	29	104	finally
        //   29	85	104	finally
      }
      
      /* Error */
      public Intent a(int paramInt1, int paramInt2, boolean paramBoolean)
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
        //   17: aload 4
        //   19: iload_1
        //   20: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   23: aload 4
        //   25: iload_2
        //   26: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   29: iconst_0
        //   30: istore 7
        //   32: iload_3
        //   33: ifeq +6 -> 39
        //   36: iconst_1
        //   37: istore 7
        //   39: aload 4
        //   41: iload 7
        //   43: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   46: aload_0
        //   47: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   50: sipush 9008
        //   53: aload 4
        //   55: aload 5
        //   57: iconst_0
        //   58: invokeinterface 41 5 0
        //   63: pop
        //   64: aload 5
        //   66: invokevirtual 44	android/os/Parcel:readException	()V
        //   69: aload 5
        //   71: invokevirtual 71	android/os/Parcel:readInt	()I
        //   74: ifeq +31 -> 105
        //   77: getstatic 78	android/content/Intent:CREATOR	Landroid/os/Parcelable$Creator;
        //   80: aload 5
        //   82: invokeinterface 84 2 0
        //   87: checkcast 74	android/content/Intent
        //   90: astore 9
        //   92: aload 5
        //   94: invokevirtual 47	android/os/Parcel:recycle	()V
        //   97: aload 4
        //   99: invokevirtual 47	android/os/Parcel:recycle	()V
        //   102: aload 9
        //   104: areturn
        //   105: aconst_null
        //   106: astore 9
        //   108: goto -16 -> 92
        //   111: astore 6
        //   113: aload 5
        //   115: invokevirtual 47	android/os/Parcel:recycle	()V
        //   118: aload 4
        //   120: invokevirtual 47	android/os/Parcel:recycle	()V
        //   123: aload 6
        //   125: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	126	0	this	Proxy
        //   0	126	1	paramInt1	int
        //   0	126	2	paramInt2	int
        //   0	126	3	paramBoolean	boolean
        //   3	116	4	localParcel1	Parcel
        //   8	106	5	localParcel2	Parcel
        //   111	13	6	localObject	Object
        //   30	12	7	i	int
        //   90	17	9	localIntent	Intent
        // Exception table:
        //   from	to	target	type
        //   10	29	111	finally
        //   39	92	111	finally
      }
      
      /* Error */
      public Intent a(int paramInt1, byte[] paramArrayOfByte, int paramInt2, String paramString)
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
        //   17: aload 5
        //   19: iload_1
        //   20: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   23: aload 5
        //   25: aload_2
        //   26: invokevirtual 64	android/os/Parcel:writeByteArray	([B)V
        //   29: aload 5
        //   31: iload_3
        //   32: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   35: aload 5
        //   37: aload 4
        //   39: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   42: aload_0
        //   43: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   46: sipush 10012
        //   49: aload 5
        //   51: aload 6
        //   53: iconst_0
        //   54: invokeinterface 41 5 0
        //   59: pop
        //   60: aload 6
        //   62: invokevirtual 44	android/os/Parcel:readException	()V
        //   65: aload 6
        //   67: invokevirtual 71	android/os/Parcel:readInt	()I
        //   70: ifeq +31 -> 101
        //   73: getstatic 78	android/content/Intent:CREATOR	Landroid/os/Parcelable$Creator;
        //   76: aload 6
        //   78: invokeinterface 84 2 0
        //   83: checkcast 74	android/content/Intent
        //   86: astore 9
        //   88: aload 6
        //   90: invokevirtual 47	android/os/Parcel:recycle	()V
        //   93: aload 5
        //   95: invokevirtual 47	android/os/Parcel:recycle	()V
        //   98: aload 9
        //   100: areturn
        //   101: aconst_null
        //   102: astore 9
        //   104: goto -16 -> 88
        //   107: astore 7
        //   109: aload 6
        //   111: invokevirtual 47	android/os/Parcel:recycle	()V
        //   114: aload 5
        //   116: invokevirtual 47	android/os/Parcel:recycle	()V
        //   119: aload 7
        //   121: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	122	0	this	Proxy
        //   0	122	1	paramInt1	int
        //   0	122	2	paramArrayOfByte	byte[]
        //   0	122	3	paramInt2	int
        //   0	122	4	paramString	String
        //   3	112	5	localParcel1	Parcel
        //   8	102	6	localParcel2	Parcel
        //   107	13	7	localObject	Object
        //   86	17	9	localIntent	Intent
        // Exception table:
        //   from	to	target	type
        //   10	88	107	finally
      }
      
      public Intent a(AchievementEntity paramAchievementEntity)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (paramAchievementEntity != null)
            {
              localParcel1.writeInt(1);
              paramAchievementEntity.writeToParcel(localParcel1, 0);
              this.lb.transact(13005, localParcel1, localParcel2, 0);
              localParcel2.readException();
              if (localParcel2.readInt() != 0)
              {
                localIntent = (Intent)Intent.CREATOR.createFromParcel(localParcel2);
                return localIntent;
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            Intent localIntent = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public Intent a(ZInvitationCluster paramZInvitationCluster, String paramString1, String paramString2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (paramZInvitationCluster != null)
            {
              localParcel1.writeInt(1);
              paramZInvitationCluster.writeToParcel(localParcel1, 0);
              localParcel1.writeString(paramString1);
              localParcel1.writeString(paramString2);
              this.lb.transact(10021, localParcel1, localParcel2, 0);
              localParcel2.readException();
              if (localParcel2.readInt() != 0)
              {
                localIntent = (Intent)Intent.CREATOR.createFromParcel(localParcel2);
                return localIntent;
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            Intent localIntent = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public Intent a(GameRequestCluster paramGameRequestCluster, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (paramGameRequestCluster != null)
            {
              localParcel1.writeInt(1);
              paramGameRequestCluster.writeToParcel(localParcel1, 0);
              localParcel1.writeString(paramString);
              this.lb.transact(10022, localParcel1, localParcel2, 0);
              localParcel2.readException();
              if (localParcel2.readInt() != 0)
              {
                localIntent = (Intent)Intent.CREATOR.createFromParcel(localParcel2);
                return localIntent;
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            Intent localIntent = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public Intent a(RoomEntity paramRoomEntity, int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (paramRoomEntity != null)
            {
              localParcel1.writeInt(1);
              paramRoomEntity.writeToParcel(localParcel1, 0);
              localParcel1.writeInt(paramInt);
              this.lb.transact(9011, localParcel1, localParcel2, 0);
              localParcel2.readException();
              if (localParcel2.readInt() != 0)
              {
                localIntent = (Intent)Intent.CREATOR.createFromParcel(localParcel2);
                return localIntent;
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            Intent localIntent = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      /* Error */
      public Intent a(String paramString, boolean paramBoolean1, boolean paramBoolean2, int paramInt)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore 5
        //   3: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 6
        //   8: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 7
        //   13: aload 6
        //   15: ldc 27
        //   17: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload 6
        //   22: aload_1
        //   23: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   26: iload_2
        //   27: ifeq +91 -> 118
        //   30: iload 5
        //   32: istore 9
        //   34: aload 6
        //   36: iload 9
        //   38: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   41: iload_3
        //   42: ifeq +82 -> 124
        //   45: aload 6
        //   47: iload 5
        //   49: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   52: aload 6
        //   54: iload 4
        //   56: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   59: aload_0
        //   60: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   63: sipush 12001
        //   66: aload 6
        //   68: aload 7
        //   70: iconst_0
        //   71: invokeinterface 41 5 0
        //   76: pop
        //   77: aload 7
        //   79: invokevirtual 44	android/os/Parcel:readException	()V
        //   82: aload 7
        //   84: invokevirtual 71	android/os/Parcel:readInt	()I
        //   87: ifeq +43 -> 130
        //   90: getstatic 78	android/content/Intent:CREATOR	Landroid/os/Parcelable$Creator;
        //   93: aload 7
        //   95: invokeinterface 84 2 0
        //   100: checkcast 74	android/content/Intent
        //   103: astore 11
        //   105: aload 7
        //   107: invokevirtual 47	android/os/Parcel:recycle	()V
        //   110: aload 6
        //   112: invokevirtual 47	android/os/Parcel:recycle	()V
        //   115: aload 11
        //   117: areturn
        //   118: iconst_0
        //   119: istore 9
        //   121: goto -87 -> 34
        //   124: iconst_0
        //   125: istore 5
        //   127: goto -82 -> 45
        //   130: aconst_null
        //   131: astore 11
        //   133: goto -28 -> 105
        //   136: astore 8
        //   138: aload 7
        //   140: invokevirtual 47	android/os/Parcel:recycle	()V
        //   143: aload 6
        //   145: invokevirtual 47	android/os/Parcel:recycle	()V
        //   148: aload 8
        //   150: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	151	0	this	Proxy
        //   0	151	1	paramString	String
        //   0	151	2	paramBoolean1	boolean
        //   0	151	3	paramBoolean2	boolean
        //   0	151	4	paramInt	int
        //   1	125	5	i	int
        //   6	138	6	localParcel1	Parcel
        //   11	128	7	localParcel2	Parcel
        //   136	13	8	localObject	Object
        //   32	88	9	j	int
        //   103	29	11	localIntent	Intent
        // Exception table:
        //   from	to	target	type
        //   13	26	136	finally
        //   34	41	136	finally
        //   45	105	136	finally
      }
      
      public Intent a(ParticipantEntity[] paramArrayOfParticipantEntity, String paramString1, String paramString2, Uri paramUri1, Uri paramUri2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            localParcel1.writeTypedArray(paramArrayOfParticipantEntity, 0);
            localParcel1.writeString(paramString1);
            localParcel1.writeString(paramString2);
            if (paramUri1 != null)
            {
              localParcel1.writeInt(1);
              paramUri1.writeToParcel(localParcel1, 0);
              if (paramUri2 != null)
              {
                localParcel1.writeInt(1);
                paramUri2.writeToParcel(localParcel1, 0);
                this.lb.transact(9031, localParcel1, localParcel2, 0);
                localParcel2.readException();
                if (localParcel2.readInt() == 0) {
                  break label166;
                }
                localIntent = (Intent)Intent.CREATOR.createFromParcel(localParcel2);
                return localIntent;
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            localParcel1.writeInt(0);
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          continue;
          label166:
          Intent localIntent = null;
        }
      }
      
      public void a(long paramLong, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeLong(paramLong);
          localParcel1.writeString(paramString);
          this.lb.transact(8019, localParcel1, localParcel2, 0);
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
      public void a(IBinder paramIBinder, Bundle paramBundle)
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
        //   15: aload_3
        //   16: aload_1
        //   17: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   20: aload_2
        //   21: ifnull +46 -> 67
        //   24: aload_3
        //   25: iconst_1
        //   26: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   29: aload_2
        //   30: aload_3
        //   31: iconst_0
        //   32: invokevirtual 122	android/os/Bundle:writeToParcel	(Landroid/os/Parcel;I)V
        //   35: aload_0
        //   36: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   39: sipush 5005
        //   42: aload_3
        //   43: aload 4
        //   45: iconst_0
        //   46: invokeinterface 41 5 0
        //   51: pop
        //   52: aload 4
        //   54: invokevirtual 44	android/os/Parcel:readException	()V
        //   57: aload 4
        //   59: invokevirtual 47	android/os/Parcel:recycle	()V
        //   62: aload_3
        //   63: invokevirtual 47	android/os/Parcel:recycle	()V
        //   66: return
        //   67: aload_3
        //   68: iconst_0
        //   69: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   72: goto -37 -> 35
        //   75: astore 5
        //   77: aload 4
        //   79: invokevirtual 47	android/os/Parcel:recycle	()V
        //   82: aload_3
        //   83: invokevirtual 47	android/os/Parcel:recycle	()V
        //   86: aload 5
        //   88: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	89	0	this	Proxy
        //   0	89	1	paramIBinder	IBinder
        //   0	89	2	paramBundle	Bundle
        //   3	80	3	localParcel1	Parcel
        //   7	71	4	localParcel2	Parcel
        //   75	12	5	localObject	Object
        // Exception table:
        //   from	to	target	type
        //   9	20	75	finally
        //   24	35	75	finally
        //   35	57	75	finally
        //   67	72	75	finally
      }
      
      /* Error */
      public void a(Contents paramContents)
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
        //   15: ifnull +43 -> 58
        //   18: aload_2
        //   19: iconst_1
        //   20: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   23: aload_1
        //   24: aload_2
        //   25: iconst_0
        //   26: invokevirtual 126	com/google/android/gms/drive/Contents:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aload_0
        //   30: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   33: sipush 12019
        //   36: aload_2
        //   37: aload_3
        //   38: iconst_0
        //   39: invokeinterface 41 5 0
        //   44: pop
        //   45: aload_3
        //   46: invokevirtual 44	android/os/Parcel:readException	()V
        //   49: aload_3
        //   50: invokevirtual 47	android/os/Parcel:recycle	()V
        //   53: aload_2
        //   54: invokevirtual 47	android/os/Parcel:recycle	()V
        //   57: return
        //   58: aload_2
        //   59: iconst_0
        //   60: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   63: goto -34 -> 29
        //   66: astore 4
        //   68: aload_3
        //   69: invokevirtual 47	android/os/Parcel:recycle	()V
        //   72: aload_2
        //   73: invokevirtual 47	android/os/Parcel:recycle	()V
        //   76: aload 4
        //   78: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	79	0	this	Proxy
        //   0	79	1	paramContents	Contents
        //   3	70	2	localParcel1	Parcel
        //   7	62	3	localParcel2	Parcel
        //   66	11	4	localObject	Object
        // Exception table:
        //   from	to	target	type
        //   8	14	66	finally
        //   18	29	66	finally
        //   29	49	66	finally
        //   58	63	66	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks)
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
        //   15: ifnull +46 -> 61
        //   18: aload_1
        //   19: invokeinterface 57 1 0
        //   24: astore 5
        //   26: aload_2
        //   27: aload 5
        //   29: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   32: aload_0
        //   33: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   36: sipush 5002
        //   39: aload_2
        //   40: aload_3
        //   41: iconst_0
        //   42: invokeinterface 41 5 0
        //   47: pop
        //   48: aload_3
        //   49: invokevirtual 44	android/os/Parcel:readException	()V
        //   52: aload_3
        //   53: invokevirtual 47	android/os/Parcel:recycle	()V
        //   56: aload_2
        //   57: invokevirtual 47	android/os/Parcel:recycle	()V
        //   60: return
        //   61: aconst_null
        //   62: astore 5
        //   64: goto -38 -> 26
        //   67: astore 4
        //   69: aload_3
        //   70: invokevirtual 47	android/os/Parcel:recycle	()V
        //   73: aload_2
        //   74: invokevirtual 47	android/os/Parcel:recycle	()V
        //   77: aload 4
        //   79: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	80	0	this	Proxy
        //   0	80	1	paramIGamesCallbacks	IGamesCallbacks
        //   3	71	2	localParcel1	Parcel
        //   7	63	3	localParcel2	Parcel
        //   67	11	4	localObject	Object
        //   24	39	5	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   8	14	67	finally
        //   18	26	67	finally
        //   26	52	67	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, int paramInt)
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
        //   16: ifnull +54 -> 70
        //   19: aload_1
        //   20: invokeinterface 57 1 0
        //   25: astore 6
        //   27: aload_3
        //   28: aload 6
        //   30: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   33: aload_3
        //   34: iload_2
        //   35: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   38: aload_0
        //   39: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   42: sipush 10016
        //   45: aload_3
        //   46: aload 4
        //   48: iconst_0
        //   49: invokeinterface 41 5 0
        //   54: pop
        //   55: aload 4
        //   57: invokevirtual 44	android/os/Parcel:readException	()V
        //   60: aload 4
        //   62: invokevirtual 47	android/os/Parcel:recycle	()V
        //   65: aload_3
        //   66: invokevirtual 47	android/os/Parcel:recycle	()V
        //   69: return
        //   70: aconst_null
        //   71: astore 6
        //   73: goto -46 -> 27
        //   76: astore 5
        //   78: aload 4
        //   80: invokevirtual 47	android/os/Parcel:recycle	()V
        //   83: aload_3
        //   84: invokevirtual 47	android/os/Parcel:recycle	()V
        //   87: aload 5
        //   89: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	90	0	this	Proxy
        //   0	90	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	90	2	paramInt	int
        //   3	81	3	localParcel1	Parcel
        //   7	72	4	localParcel2	Parcel
        //   76	12	5	localObject	Object
        //   25	47	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   9	15	76	finally
        //   19	27	76	finally
        //   27	60	76	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, int paramInt1, int paramInt2, int paramInt3)
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
        //   18: ifnull +71 -> 89
        //   21: aload_1
        //   22: invokeinterface 57 1 0
        //   27: astore 8
        //   29: aload 5
        //   31: aload 8
        //   33: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload 5
        //   38: iload_2
        //   39: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   42: aload 5
        //   44: iload_3
        //   45: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   48: aload 5
        //   50: iload 4
        //   52: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   55: aload_0
        //   56: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   59: sipush 10009
        //   62: aload 5
        //   64: aload 6
        //   66: iconst_0
        //   67: invokeinterface 41 5 0
        //   72: pop
        //   73: aload 6
        //   75: invokevirtual 44	android/os/Parcel:readException	()V
        //   78: aload 6
        //   80: invokevirtual 47	android/os/Parcel:recycle	()V
        //   83: aload 5
        //   85: invokevirtual 47	android/os/Parcel:recycle	()V
        //   88: return
        //   89: aconst_null
        //   90: astore 8
        //   92: goto -63 -> 29
        //   95: astore 7
        //   97: aload 6
        //   99: invokevirtual 47	android/os/Parcel:recycle	()V
        //   102: aload 5
        //   104: invokevirtual 47	android/os/Parcel:recycle	()V
        //   107: aload 7
        //   109: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	110	0	this	Proxy
        //   0	110	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	110	2	paramInt1	int
        //   0	110	3	paramInt2	int
        //   0	110	4	paramInt3	int
        //   3	100	5	localParcel1	Parcel
        //   8	90	6	localParcel2	Parcel
        //   95	13	7	localObject	Object
        //   27	64	8	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   10	17	95	finally
        //   21	29	95	finally
        //   29	78	95	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore 6
        //   3: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 7
        //   8: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 8
        //   13: aload 7
        //   15: ldc 27
        //   17: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload_1
        //   21: ifnull +92 -> 113
        //   24: aload_1
        //   25: invokeinterface 57 1 0
        //   30: astore 10
        //   32: aload 7
        //   34: aload 10
        //   36: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   39: aload 7
        //   41: iload_2
        //   42: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   45: aload 7
        //   47: iload_3
        //   48: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   51: iload 4
        //   53: ifeq +66 -> 119
        //   56: iload 6
        //   58: istore 11
        //   60: aload 7
        //   62: iload 11
        //   64: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   67: iload 5
        //   69: ifeq +56 -> 125
        //   72: aload 7
        //   74: iload 6
        //   76: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   79: aload_0
        //   80: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   83: sipush 5044
        //   86: aload 7
        //   88: aload 8
        //   90: iconst_0
        //   91: invokeinterface 41 5 0
        //   96: pop
        //   97: aload 8
        //   99: invokevirtual 44	android/os/Parcel:readException	()V
        //   102: aload 8
        //   104: invokevirtual 47	android/os/Parcel:recycle	()V
        //   107: aload 7
        //   109: invokevirtual 47	android/os/Parcel:recycle	()V
        //   112: return
        //   113: aconst_null
        //   114: astore 10
        //   116: goto -84 -> 32
        //   119: iconst_0
        //   120: istore 11
        //   122: goto -62 -> 60
        //   125: iconst_0
        //   126: istore 6
        //   128: goto -56 -> 72
        //   131: astore 9
        //   133: aload 8
        //   135: invokevirtual 47	android/os/Parcel:recycle	()V
        //   138: aload 7
        //   140: invokevirtual 47	android/os/Parcel:recycle	()V
        //   143: aload 9
        //   145: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	146	0	this	Proxy
        //   0	146	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	146	2	paramInt1	int
        //   0	146	3	paramInt2	int
        //   0	146	4	paramBoolean1	boolean
        //   0	146	5	paramBoolean2	boolean
        //   1	126	6	i	int
        //   6	133	7	localParcel1	Parcel
        //   11	123	8	localParcel2	Parcel
        //   131	13	9	localObject	Object
        //   30	85	10	localIBinder	IBinder
        //   58	63	11	j	int
        // Exception table:
        //   from	to	target	type
        //   13	20	131	finally
        //   24	32	131	finally
        //   32	51	131	finally
        //   60	67	131	finally
        //   72	102	131	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, int paramInt1, int paramInt2, String[] paramArrayOfString, Bundle paramBundle)
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
        //   18: ifnull +90 -> 108
        //   21: aload_1
        //   22: invokeinterface 57 1 0
        //   27: astore 9
        //   29: aload 6
        //   31: aload 9
        //   33: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload 6
        //   38: iload_2
        //   39: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   42: aload 6
        //   44: iload_3
        //   45: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   48: aload 6
        //   50: aload 4
        //   52: invokevirtual 135	android/os/Parcel:writeStringArray	([Ljava/lang/String;)V
        //   55: aload 5
        //   57: ifnull +57 -> 114
        //   60: aload 6
        //   62: iconst_1
        //   63: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   66: aload 5
        //   68: aload 6
        //   70: iconst_0
        //   71: invokevirtual 122	android/os/Bundle:writeToParcel	(Landroid/os/Parcel;I)V
        //   74: aload_0
        //   75: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   78: sipush 8004
        //   81: aload 6
        //   83: aload 7
        //   85: iconst_0
        //   86: invokeinterface 41 5 0
        //   91: pop
        //   92: aload 7
        //   94: invokevirtual 44	android/os/Parcel:readException	()V
        //   97: aload 7
        //   99: invokevirtual 47	android/os/Parcel:recycle	()V
        //   102: aload 6
        //   104: invokevirtual 47	android/os/Parcel:recycle	()V
        //   107: return
        //   108: aconst_null
        //   109: astore 9
        //   111: goto -82 -> 29
        //   114: aload 6
        //   116: iconst_0
        //   117: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   120: goto -46 -> 74
        //   123: astore 8
        //   125: aload 7
        //   127: invokevirtual 47	android/os/Parcel:recycle	()V
        //   130: aload 6
        //   132: invokevirtual 47	android/os/Parcel:recycle	()V
        //   135: aload 8
        //   137: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	138	0	this	Proxy
        //   0	138	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	138	2	paramInt1	int
        //   0	138	3	paramInt2	int
        //   0	138	4	paramArrayOfString	String[]
        //   0	138	5	paramBundle	Bundle
        //   3	128	6	localParcel1	Parcel
        //   8	118	7	localParcel2	Parcel
        //   123	13	8	localObject	Object
        //   27	83	9	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   10	17	123	finally
        //   21	29	123	finally
        //   29	55	123	finally
        //   60	74	123	finally
        //   74	97	123	finally
        //   114	120	123	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore 5
        //   3: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 6
        //   8: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 7
        //   13: aload 6
        //   15: ldc 27
        //   17: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload_1
        //   21: ifnull +85 -> 106
        //   24: aload_1
        //   25: invokeinterface 57 1 0
        //   30: astore 9
        //   32: aload 6
        //   34: aload 9
        //   36: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   39: aload 6
        //   41: iload_2
        //   42: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   45: iload_3
        //   46: ifeq +66 -> 112
        //   49: iload 5
        //   51: istore 10
        //   53: aload 6
        //   55: iload 10
        //   57: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   60: iload 4
        //   62: ifeq +56 -> 118
        //   65: aload 6
        //   67: iload 5
        //   69: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   72: aload_0
        //   73: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   76: sipush 5015
        //   79: aload 6
        //   81: aload 7
        //   83: iconst_0
        //   84: invokeinterface 41 5 0
        //   89: pop
        //   90: aload 7
        //   92: invokevirtual 44	android/os/Parcel:readException	()V
        //   95: aload 7
        //   97: invokevirtual 47	android/os/Parcel:recycle	()V
        //   100: aload 6
        //   102: invokevirtual 47	android/os/Parcel:recycle	()V
        //   105: return
        //   106: aconst_null
        //   107: astore 9
        //   109: goto -77 -> 32
        //   112: iconst_0
        //   113: istore 10
        //   115: goto -62 -> 53
        //   118: iconst_0
        //   119: istore 5
        //   121: goto -56 -> 65
        //   124: astore 8
        //   126: aload 7
        //   128: invokevirtual 47	android/os/Parcel:recycle	()V
        //   131: aload 6
        //   133: invokevirtual 47	android/os/Parcel:recycle	()V
        //   136: aload 8
        //   138: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	139	0	this	Proxy
        //   0	139	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	139	2	paramInt	int
        //   0	139	3	paramBoolean1	boolean
        //   0	139	4	paramBoolean2	boolean
        //   1	119	5	i	int
        //   6	126	6	localParcel1	Parcel
        //   11	116	7	localParcel2	Parcel
        //   124	13	8	localObject	Object
        //   30	78	9	localIBinder	IBinder
        //   51	63	10	j	int
        // Exception table:
        //   from	to	target	type
        //   13	20	124	finally
        //   24	32	124	finally
        //   32	45	124	finally
        //   53	60	124	finally
        //   65	95	124	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, int paramInt, int[] paramArrayOfInt)
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
        //   18: ifnull +64 -> 82
        //   21: aload_1
        //   22: invokeinterface 57 1 0
        //   27: astore 7
        //   29: aload 4
        //   31: aload 7
        //   33: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload 4
        //   38: iload_2
        //   39: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   42: aload 4
        //   44: aload_3
        //   45: invokevirtual 141	android/os/Parcel:writeIntArray	([I)V
        //   48: aload_0
        //   49: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   52: sipush 10018
        //   55: aload 4
        //   57: aload 5
        //   59: iconst_0
        //   60: invokeinterface 41 5 0
        //   65: pop
        //   66: aload 5
        //   68: invokevirtual 44	android/os/Parcel:readException	()V
        //   71: aload 5
        //   73: invokevirtual 47	android/os/Parcel:recycle	()V
        //   76: aload 4
        //   78: invokevirtual 47	android/os/Parcel:recycle	()V
        //   81: return
        //   82: aconst_null
        //   83: astore 7
        //   85: goto -56 -> 29
        //   88: astore 6
        //   90: aload 5
        //   92: invokevirtual 47	android/os/Parcel:recycle	()V
        //   95: aload 4
        //   97: invokevirtual 47	android/os/Parcel:recycle	()V
        //   100: aload 6
        //   102: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	103	0	this	Proxy
        //   0	103	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	103	2	paramInt	int
        //   0	103	3	paramArrayOfInt	int[]
        //   3	93	4	localParcel1	Parcel
        //   8	83	5	localParcel2	Parcel
        //   88	13	6	localObject	Object
        //   27	57	7	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   10	17	88	finally
        //   21	29	88	finally
        //   29	71	88	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, long paramLong)
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
        //   18: ifnull +58 -> 76
        //   21: aload_1
        //   22: invokeinterface 57 1 0
        //   27: astore 7
        //   29: aload 4
        //   31: aload 7
        //   33: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload 4
        //   38: lload_2
        //   39: invokevirtual 118	android/os/Parcel:writeLong	(J)V
        //   42: aload_0
        //   43: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   46: sipush 5058
        //   49: aload 4
        //   51: aload 5
        //   53: iconst_0
        //   54: invokeinterface 41 5 0
        //   59: pop
        //   60: aload 5
        //   62: invokevirtual 44	android/os/Parcel:readException	()V
        //   65: aload 5
        //   67: invokevirtual 47	android/os/Parcel:recycle	()V
        //   70: aload 4
        //   72: invokevirtual 47	android/os/Parcel:recycle	()V
        //   75: return
        //   76: aconst_null
        //   77: astore 7
        //   79: goto -50 -> 29
        //   82: astore 6
        //   84: aload 5
        //   86: invokevirtual 47	android/os/Parcel:recycle	()V
        //   89: aload 4
        //   91: invokevirtual 47	android/os/Parcel:recycle	()V
        //   94: aload 6
        //   96: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	97	0	this	Proxy
        //   0	97	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	97	2	paramLong	long
        //   3	87	4	localParcel1	Parcel
        //   8	77	5	localParcel2	Parcel
        //   82	13	6	localObject	Object
        //   27	51	7	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   10	17	82	finally
        //   21	29	82	finally
        //   29	65	82	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, long paramLong, String paramString)
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
        //   18: ifnull +65 -> 83
        //   21: aload_1
        //   22: invokeinterface 57 1 0
        //   27: astore 8
        //   29: aload 5
        //   31: aload 8
        //   33: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload 5
        //   38: lload_2
        //   39: invokevirtual 118	android/os/Parcel:writeLong	(J)V
        //   42: aload 5
        //   44: aload 4
        //   46: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   49: aload_0
        //   50: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   53: sipush 8018
        //   56: aload 5
        //   58: aload 6
        //   60: iconst_0
        //   61: invokeinterface 41 5 0
        //   66: pop
        //   67: aload 6
        //   69: invokevirtual 44	android/os/Parcel:readException	()V
        //   72: aload 6
        //   74: invokevirtual 47	android/os/Parcel:recycle	()V
        //   77: aload 5
        //   79: invokevirtual 47	android/os/Parcel:recycle	()V
        //   82: return
        //   83: aconst_null
        //   84: astore 8
        //   86: goto -57 -> 29
        //   89: astore 7
        //   91: aload 6
        //   93: invokevirtual 47	android/os/Parcel:recycle	()V
        //   96: aload 5
        //   98: invokevirtual 47	android/os/Parcel:recycle	()V
        //   101: aload 7
        //   103: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	104	0	this	Proxy
        //   0	104	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	104	2	paramLong	long
        //   0	104	4	paramString	String
        //   3	94	5	localParcel1	Parcel
        //   8	84	6	localParcel2	Parcel
        //   89	13	7	localObject	Object
        //   27	58	8	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   10	17	89	finally
        //   21	29	89	finally
        //   29	72	89	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, Bundle paramBundle, int paramInt1, int paramInt2)
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
        //   18: ifnull +82 -> 100
        //   21: aload_1
        //   22: invokeinterface 57 1 0
        //   27: astore 8
        //   29: aload 5
        //   31: aload 8
        //   33: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload_2
        //   37: ifnull +69 -> 106
        //   40: aload 5
        //   42: iconst_1
        //   43: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   46: aload_2
        //   47: aload 5
        //   49: iconst_0
        //   50: invokevirtual 122	android/os/Bundle:writeToParcel	(Landroid/os/Parcel;I)V
        //   53: aload 5
        //   55: iload_3
        //   56: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   59: aload 5
        //   61: iload 4
        //   63: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   66: aload_0
        //   67: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   70: sipush 5021
        //   73: aload 5
        //   75: aload 6
        //   77: iconst_0
        //   78: invokeinterface 41 5 0
        //   83: pop
        //   84: aload 6
        //   86: invokevirtual 44	android/os/Parcel:readException	()V
        //   89: aload 6
        //   91: invokevirtual 47	android/os/Parcel:recycle	()V
        //   94: aload 5
        //   96: invokevirtual 47	android/os/Parcel:recycle	()V
        //   99: return
        //   100: aconst_null
        //   101: astore 8
        //   103: goto -74 -> 29
        //   106: aload 5
        //   108: iconst_0
        //   109: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   112: goto -59 -> 53
        //   115: astore 7
        //   117: aload 6
        //   119: invokevirtual 47	android/os/Parcel:recycle	()V
        //   122: aload 5
        //   124: invokevirtual 47	android/os/Parcel:recycle	()V
        //   127: aload 7
        //   129: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	130	0	this	Proxy
        //   0	130	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	130	2	paramBundle	Bundle
        //   0	130	3	paramInt1	int
        //   0	130	4	paramInt2	int
        //   3	120	5	localParcel1	Parcel
        //   8	110	6	localParcel2	Parcel
        //   115	13	7	localObject	Object
        //   27	75	8	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   10	17	115	finally
        //   21	29	115	finally
        //   29	36	115	finally
        //   40	53	115	finally
        //   53	89	115	finally
        //   106	112	115	finally
      }
      
      public void a(IGamesCallbacks paramIGamesCallbacks, IBinder paramIBinder, int paramInt, String[] paramArrayOfString, Bundle paramBundle, boolean paramBoolean, long paramLong)
        throws RemoteException
      {
        int i = 1;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder localIBinder;
            if (paramIGamesCallbacks != null)
            {
              localIBinder = paramIGamesCallbacks.asBinder();
              localParcel1.writeStrongBinder(localIBinder);
              localParcel1.writeStrongBinder(paramIBinder);
              localParcel1.writeInt(paramInt);
              localParcel1.writeStringArray(paramArrayOfString);
              if (paramBundle != null)
              {
                localParcel1.writeInt(1);
                paramBundle.writeToParcel(localParcel1, 0);
                break label164;
                localParcel1.writeInt(i);
                localParcel1.writeLong(paramLong);
                this.lb.transact(5030, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localIBinder = null;
              continue;
            }
            localParcel1.writeInt(0);
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          label164:
          while (!paramBoolean)
          {
            i = 0;
            break;
          }
        }
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, IBinder paramIBinder, String paramString, boolean paramBoolean, long paramLong)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 7
        //   5: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 8
        //   10: aload 7
        //   12: ldc 27
        //   14: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +89 -> 107
        //   21: aload_1
        //   22: invokeinterface 57 1 0
        //   27: astore 10
        //   29: aload 7
        //   31: aload 10
        //   33: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload 7
        //   38: aload_2
        //   39: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   42: aload 7
        //   44: aload_3
        //   45: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   48: iconst_0
        //   49: istore 11
        //   51: iload 4
        //   53: ifeq +6 -> 59
        //   56: iconst_1
        //   57: istore 11
        //   59: aload 7
        //   61: iload 11
        //   63: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   66: aload 7
        //   68: lload 5
        //   70: invokevirtual 118	android/os/Parcel:writeLong	(J)V
        //   73: aload_0
        //   74: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   77: sipush 5031
        //   80: aload 7
        //   82: aload 8
        //   84: iconst_0
        //   85: invokeinterface 41 5 0
        //   90: pop
        //   91: aload 8
        //   93: invokevirtual 44	android/os/Parcel:readException	()V
        //   96: aload 8
        //   98: invokevirtual 47	android/os/Parcel:recycle	()V
        //   101: aload 7
        //   103: invokevirtual 47	android/os/Parcel:recycle	()V
        //   106: return
        //   107: aconst_null
        //   108: astore 10
        //   110: goto -81 -> 29
        //   113: astore 9
        //   115: aload 8
        //   117: invokevirtual 47	android/os/Parcel:recycle	()V
        //   120: aload 7
        //   122: invokevirtual 47	android/os/Parcel:recycle	()V
        //   125: aload 9
        //   127: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	128	0	this	Proxy
        //   0	128	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	128	2	paramIBinder	IBinder
        //   0	128	3	paramString	String
        //   0	128	4	paramBoolean	boolean
        //   0	128	5	paramLong	long
        //   3	118	7	localParcel1	Parcel
        //   8	108	8	localParcel2	Parcel
        //   113	13	9	localObject	Object
        //   27	82	10	localIBinder	IBinder
        //   49	13	11	i	int
        // Exception table:
        //   from	to	target	type
        //   10	17	113	finally
        //   21	29	113	finally
        //   29	48	113	finally
        //   59	96	113	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, String paramString)
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
        //   16: ifnull +54 -> 70
        //   19: aload_1
        //   20: invokeinterface 57 1 0
        //   25: astore 6
        //   27: aload_3
        //   28: aload 6
        //   30: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   33: aload_3
        //   34: aload_2
        //   35: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   38: aload_0
        //   39: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   42: sipush 5014
        //   45: aload_3
        //   46: aload 4
        //   48: iconst_0
        //   49: invokeinterface 41 5 0
        //   54: pop
        //   55: aload 4
        //   57: invokevirtual 44	android/os/Parcel:readException	()V
        //   60: aload 4
        //   62: invokevirtual 47	android/os/Parcel:recycle	()V
        //   65: aload_3
        //   66: invokevirtual 47	android/os/Parcel:recycle	()V
        //   69: return
        //   70: aconst_null
        //   71: astore 6
        //   73: goto -46 -> 27
        //   76: astore 5
        //   78: aload 4
        //   80: invokevirtual 47	android/os/Parcel:recycle	()V
        //   83: aload_3
        //   84: invokevirtual 47	android/os/Parcel:recycle	()V
        //   87: aload 5
        //   89: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	90	0	this	Proxy
        //   0	90	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	90	2	paramString	String
        //   3	81	3	localParcel1	Parcel
        //   7	72	4	localParcel2	Parcel
        //   76	12	5	localObject	Object
        //   25	47	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   9	15	76	finally
        //   19	27	76	finally
        //   27	60	76	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt)
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
        //   18: ifnull +64 -> 82
        //   21: aload_1
        //   22: invokeinterface 57 1 0
        //   27: astore 7
        //   29: aload 4
        //   31: aload 7
        //   33: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload 4
        //   38: aload_2
        //   39: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   42: aload 4
        //   44: iload_3
        //   45: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   48: aload_0
        //   49: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   52: sipush 10011
        //   55: aload 4
        //   57: aload 5
        //   59: iconst_0
        //   60: invokeinterface 41 5 0
        //   65: pop
        //   66: aload 5
        //   68: invokevirtual 44	android/os/Parcel:readException	()V
        //   71: aload 5
        //   73: invokevirtual 47	android/os/Parcel:recycle	()V
        //   76: aload 4
        //   78: invokevirtual 47	android/os/Parcel:recycle	()V
        //   81: return
        //   82: aconst_null
        //   83: astore 7
        //   85: goto -56 -> 29
        //   88: astore 6
        //   90: aload 5
        //   92: invokevirtual 47	android/os/Parcel:recycle	()V
        //   95: aload 4
        //   97: invokevirtual 47	android/os/Parcel:recycle	()V
        //   100: aload 6
        //   102: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	103	0	this	Proxy
        //   0	103	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	103	2	paramString	String
        //   0	103	3	paramInt	int
        //   3	93	4	localParcel1	Parcel
        //   8	83	5	localParcel2	Parcel
        //   88	13	6	localObject	Object
        //   27	57	7	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   10	17	88	finally
        //   21	29	88	finally
        //   29	71	88	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 7
        //   5: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 8
        //   10: aload 7
        //   12: ldc 27
        //   14: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +96 -> 114
        //   21: aload_1
        //   22: invokeinterface 57 1 0
        //   27: astore 10
        //   29: aload 7
        //   31: aload 10
        //   33: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload 7
        //   38: aload_2
        //   39: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   42: aload 7
        //   44: iload_3
        //   45: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   48: aload 7
        //   50: iload 4
        //   52: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   55: aload 7
        //   57: iload 5
        //   59: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   62: iconst_0
        //   63: istore 11
        //   65: iload 6
        //   67: ifeq +6 -> 73
        //   70: iconst_1
        //   71: istore 11
        //   73: aload 7
        //   75: iload 11
        //   77: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   80: aload_0
        //   81: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   84: sipush 5019
        //   87: aload 7
        //   89: aload 8
        //   91: iconst_0
        //   92: invokeinterface 41 5 0
        //   97: pop
        //   98: aload 8
        //   100: invokevirtual 44	android/os/Parcel:readException	()V
        //   103: aload 8
        //   105: invokevirtual 47	android/os/Parcel:recycle	()V
        //   108: aload 7
        //   110: invokevirtual 47	android/os/Parcel:recycle	()V
        //   113: return
        //   114: aconst_null
        //   115: astore 10
        //   117: goto -88 -> 29
        //   120: astore 9
        //   122: aload 8
        //   124: invokevirtual 47	android/os/Parcel:recycle	()V
        //   127: aload 7
        //   129: invokevirtual 47	android/os/Parcel:recycle	()V
        //   132: aload 9
        //   134: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	135	0	this	Proxy
        //   0	135	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	135	2	paramString	String
        //   0	135	3	paramInt1	int
        //   0	135	4	paramInt2	int
        //   0	135	5	paramInt3	int
        //   0	135	6	paramBoolean	boolean
        //   3	125	7	localParcel1	Parcel
        //   8	115	8	localParcel2	Parcel
        //   120	13	9	localObject	Object
        //   27	89	10	localIBinder	IBinder
        //   63	13	11	i	int
        // Exception table:
        //   from	to	target	type
        //   10	17	120	finally
        //   21	29	120	finally
        //   29	62	120	finally
        //   73	103	120	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt, IBinder paramIBinder, Bundle paramBundle)
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
        //   18: ifnull +90 -> 108
        //   21: aload_1
        //   22: invokeinterface 57 1 0
        //   27: astore 9
        //   29: aload 6
        //   31: aload 9
        //   33: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload 6
        //   38: aload_2
        //   39: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   42: aload 6
        //   44: iload_3
        //   45: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   48: aload 6
        //   50: aload 4
        //   52: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   55: aload 5
        //   57: ifnull +57 -> 114
        //   60: aload 6
        //   62: iconst_1
        //   63: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   66: aload 5
        //   68: aload 6
        //   70: iconst_0
        //   71: invokevirtual 122	android/os/Bundle:writeToParcel	(Landroid/os/Parcel;I)V
        //   74: aload_0
        //   75: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   78: sipush 5025
        //   81: aload 6
        //   83: aload 7
        //   85: iconst_0
        //   86: invokeinterface 41 5 0
        //   91: pop
        //   92: aload 7
        //   94: invokevirtual 44	android/os/Parcel:readException	()V
        //   97: aload 7
        //   99: invokevirtual 47	android/os/Parcel:recycle	()V
        //   102: aload 6
        //   104: invokevirtual 47	android/os/Parcel:recycle	()V
        //   107: return
        //   108: aconst_null
        //   109: astore 9
        //   111: goto -82 -> 29
        //   114: aload 6
        //   116: iconst_0
        //   117: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   120: goto -46 -> 74
        //   123: astore 8
        //   125: aload 7
        //   127: invokevirtual 47	android/os/Parcel:recycle	()V
        //   130: aload 6
        //   132: invokevirtual 47	android/os/Parcel:recycle	()V
        //   135: aload 8
        //   137: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	138	0	this	Proxy
        //   0	138	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	138	2	paramString	String
        //   0	138	3	paramInt	int
        //   0	138	4	paramIBinder	IBinder
        //   0	138	5	paramBundle	Bundle
        //   3	128	6	localParcel1	Parcel
        //   8	118	7	localParcel2	Parcel
        //   123	13	8	localObject	Object
        //   27	83	9	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   10	17	123	finally
        //   21	29	123	finally
        //   29	55	123	finally
        //   60	74	123	finally
        //   74	97	123	finally
        //   114	120	123	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt, boolean paramBoolean)
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
        //   18: ifnull +82 -> 100
        //   21: aload_1
        //   22: invokeinterface 57 1 0
        //   27: astore 8
        //   29: aload 5
        //   31: aload 8
        //   33: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload 5
        //   38: aload_2
        //   39: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   42: aload 5
        //   44: iload_3
        //   45: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   48: iconst_0
        //   49: istore 9
        //   51: iload 4
        //   53: ifeq +6 -> 59
        //   56: iconst_1
        //   57: istore 9
        //   59: aload 5
        //   61: iload 9
        //   63: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   66: aload_0
        //   67: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   70: sipush 8023
        //   73: aload 5
        //   75: aload 6
        //   77: iconst_0
        //   78: invokeinterface 41 5 0
        //   83: pop
        //   84: aload 6
        //   86: invokevirtual 44	android/os/Parcel:readException	()V
        //   89: aload 6
        //   91: invokevirtual 47	android/os/Parcel:recycle	()V
        //   94: aload 5
        //   96: invokevirtual 47	android/os/Parcel:recycle	()V
        //   99: return
        //   100: aconst_null
        //   101: astore 8
        //   103: goto -74 -> 29
        //   106: astore 7
        //   108: aload 6
        //   110: invokevirtual 47	android/os/Parcel:recycle	()V
        //   113: aload 5
        //   115: invokevirtual 47	android/os/Parcel:recycle	()V
        //   118: aload 7
        //   120: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	121	0	this	Proxy
        //   0	121	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	121	2	paramString	String
        //   0	121	3	paramInt	int
        //   0	121	4	paramBoolean	boolean
        //   3	111	5	localParcel1	Parcel
        //   8	101	6	localParcel2	Parcel
        //   106	13	7	localObject	Object
        //   27	75	8	localIBinder	IBinder
        //   49	13	9	i	int
        // Exception table:
        //   from	to	target	type
        //   10	17	106	finally
        //   21	29	106	finally
        //   29	48	106	finally
        //   59	89	106	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore 6
        //   3: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 7
        //   8: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 8
        //   13: aload 7
        //   15: ldc 27
        //   17: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload_1
        //   21: ifnull +92 -> 113
        //   24: aload_1
        //   25: invokeinterface 57 1 0
        //   30: astore 10
        //   32: aload 7
        //   34: aload 10
        //   36: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   39: aload 7
        //   41: aload_2
        //   42: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   45: aload 7
        //   47: iload_3
        //   48: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   51: iload 4
        //   53: ifeq +66 -> 119
        //   56: iload 6
        //   58: istore 11
        //   60: aload 7
        //   62: iload 11
        //   64: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   67: iload 5
        //   69: ifeq +56 -> 125
        //   72: aload 7
        //   74: iload 6
        //   76: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   79: aload_0
        //   80: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   83: sipush 5045
        //   86: aload 7
        //   88: aload 8
        //   90: iconst_0
        //   91: invokeinterface 41 5 0
        //   96: pop
        //   97: aload 8
        //   99: invokevirtual 44	android/os/Parcel:readException	()V
        //   102: aload 8
        //   104: invokevirtual 47	android/os/Parcel:recycle	()V
        //   107: aload 7
        //   109: invokevirtual 47	android/os/Parcel:recycle	()V
        //   112: return
        //   113: aconst_null
        //   114: astore 10
        //   116: goto -84 -> 32
        //   119: iconst_0
        //   120: istore 11
        //   122: goto -62 -> 60
        //   125: iconst_0
        //   126: istore 6
        //   128: goto -56 -> 72
        //   131: astore 9
        //   133: aload 8
        //   135: invokevirtual 47	android/os/Parcel:recycle	()V
        //   138: aload 7
        //   140: invokevirtual 47	android/os/Parcel:recycle	()V
        //   143: aload 9
        //   145: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	146	0	this	Proxy
        //   0	146	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	146	2	paramString	String
        //   0	146	3	paramInt	int
        //   0	146	4	paramBoolean1	boolean
        //   0	146	5	paramBoolean2	boolean
        //   1	126	6	i	int
        //   6	133	7	localParcel1	Parcel
        //   11	123	8	localParcel2	Parcel
        //   131	13	9	localObject	Object
        //   30	85	10	localIBinder	IBinder
        //   58	63	11	j	int
        // Exception table:
        //   from	to	target	type
        //   13	20	131	finally
        //   24	32	131	finally
        //   32	51	131	finally
        //   60	67	131	finally
        //   72	102	131	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore 8
        //   3: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 9
        //   8: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 10
        //   13: aload 9
        //   15: ldc 27
        //   17: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload_1
        //   21: ifnull +124 -> 145
        //   24: aload_1
        //   25: invokeinterface 57 1 0
        //   30: astore 12
        //   32: aload 9
        //   34: aload 12
        //   36: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   39: aload 9
        //   41: aload_2
        //   42: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   45: aload 9
        //   47: iload_3
        //   48: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   51: iload 4
        //   53: ifeq +98 -> 151
        //   56: iload 8
        //   58: istore 13
        //   60: aload 9
        //   62: iload 13
        //   64: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   67: iload 5
        //   69: ifeq +88 -> 157
        //   72: iload 8
        //   74: istore 14
        //   76: aload 9
        //   78: iload 14
        //   80: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   83: iload 6
        //   85: ifeq +78 -> 163
        //   88: iload 8
        //   90: istore 15
        //   92: aload 9
        //   94: iload 15
        //   96: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   99: iload 7
        //   101: ifeq +68 -> 169
        //   104: aload 9
        //   106: iload 8
        //   108: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   111: aload_0
        //   112: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   115: sipush 6501
        //   118: aload 9
        //   120: aload 10
        //   122: iconst_0
        //   123: invokeinterface 41 5 0
        //   128: pop
        //   129: aload 10
        //   131: invokevirtual 44	android/os/Parcel:readException	()V
        //   134: aload 10
        //   136: invokevirtual 47	android/os/Parcel:recycle	()V
        //   139: aload 9
        //   141: invokevirtual 47	android/os/Parcel:recycle	()V
        //   144: return
        //   145: aconst_null
        //   146: astore 12
        //   148: goto -116 -> 32
        //   151: iconst_0
        //   152: istore 13
        //   154: goto -94 -> 60
        //   157: iconst_0
        //   158: istore 14
        //   160: goto -84 -> 76
        //   163: iconst_0
        //   164: istore 15
        //   166: goto -74 -> 92
        //   169: iconst_0
        //   170: istore 8
        //   172: goto -68 -> 104
        //   175: astore 11
        //   177: aload 10
        //   179: invokevirtual 47	android/os/Parcel:recycle	()V
        //   182: aload 9
        //   184: invokevirtual 47	android/os/Parcel:recycle	()V
        //   187: aload 11
        //   189: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	190	0	this	Proxy
        //   0	190	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	190	2	paramString	String
        //   0	190	3	paramInt	int
        //   0	190	4	paramBoolean1	boolean
        //   0	190	5	paramBoolean2	boolean
        //   0	190	6	paramBoolean3	boolean
        //   0	190	7	paramBoolean4	boolean
        //   1	170	8	i	int
        //   6	177	9	localParcel1	Parcel
        //   11	167	10	localParcel2	Parcel
        //   175	13	11	localObject	Object
        //   30	117	12	localIBinder	IBinder
        //   58	95	13	j	int
        //   74	85	14	k	int
        //   90	75	15	m	int
        // Exception table:
        //   from	to	target	type
        //   13	20	175	finally
        //   24	32	175	finally
        //   32	51	175	finally
        //   60	67	175	finally
        //   76	83	175	finally
        //   92	99	175	finally
        //   104	134	175	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt, int[] paramArrayOfInt)
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
        //   18: ifnull +71 -> 89
        //   21: aload_1
        //   22: invokeinterface 57 1 0
        //   27: astore 8
        //   29: aload 5
        //   31: aload 8
        //   33: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload 5
        //   38: aload_2
        //   39: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   42: aload 5
        //   44: iload_3
        //   45: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   48: aload 5
        //   50: aload 4
        //   52: invokevirtual 141	android/os/Parcel:writeIntArray	([I)V
        //   55: aload_0
        //   56: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   59: sipush 10019
        //   62: aload 5
        //   64: aload 6
        //   66: iconst_0
        //   67: invokeinterface 41 5 0
        //   72: pop
        //   73: aload 6
        //   75: invokevirtual 44	android/os/Parcel:readException	()V
        //   78: aload 6
        //   80: invokevirtual 47	android/os/Parcel:recycle	()V
        //   83: aload 5
        //   85: invokevirtual 47	android/os/Parcel:recycle	()V
        //   88: return
        //   89: aconst_null
        //   90: astore 8
        //   92: goto -63 -> 29
        //   95: astore 7
        //   97: aload 6
        //   99: invokevirtual 47	android/os/Parcel:recycle	()V
        //   102: aload 5
        //   104: invokevirtual 47	android/os/Parcel:recycle	()V
        //   107: aload 7
        //   109: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	110	0	this	Proxy
        //   0	110	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	110	2	paramString	String
        //   0	110	3	paramInt	int
        //   0	110	4	paramArrayOfInt	int[]
        //   3	100	5	localParcel1	Parcel
        //   8	90	6	localParcel2	Parcel
        //   95	13	7	localObject	Object
        //   27	64	8	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   10	17	95	finally
        //   21	29	95	finally
        //   29	78	95	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, String paramString, long paramLong)
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
        //   18: ifnull +64 -> 82
        //   21: aload_1
        //   22: invokeinterface 57 1 0
        //   27: astore 8
        //   29: aload 5
        //   31: aload 8
        //   33: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload 5
        //   38: aload_2
        //   39: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   42: aload 5
        //   44: lload_3
        //   45: invokevirtual 118	android/os/Parcel:writeLong	(J)V
        //   48: aload_0
        //   49: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   52: sipush 5016
        //   55: aload 5
        //   57: aload 6
        //   59: iconst_0
        //   60: invokeinterface 41 5 0
        //   65: pop
        //   66: aload 6
        //   68: invokevirtual 44	android/os/Parcel:readException	()V
        //   71: aload 6
        //   73: invokevirtual 47	android/os/Parcel:recycle	()V
        //   76: aload 5
        //   78: invokevirtual 47	android/os/Parcel:recycle	()V
        //   81: return
        //   82: aconst_null
        //   83: astore 8
        //   85: goto -56 -> 29
        //   88: astore 7
        //   90: aload 6
        //   92: invokevirtual 47	android/os/Parcel:recycle	()V
        //   95: aload 5
        //   97: invokevirtual 47	android/os/Parcel:recycle	()V
        //   100: aload 7
        //   102: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	103	0	this	Proxy
        //   0	103	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	103	2	paramString	String
        //   0	103	3	paramLong	long
        //   3	93	5	localParcel1	Parcel
        //   8	83	6	localParcel2	Parcel
        //   88	13	7	localObject	Object
        //   27	57	8	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   10	17	88	finally
        //   21	29	88	finally
        //   29	71	88	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, String paramString1, long paramLong, String paramString2)
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
        //   18: ifnull +71 -> 89
        //   21: aload_1
        //   22: invokeinterface 57 1 0
        //   27: astore 9
        //   29: aload 6
        //   31: aload 9
        //   33: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload 6
        //   38: aload_2
        //   39: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   42: aload 6
        //   44: lload_3
        //   45: invokevirtual 118	android/os/Parcel:writeLong	(J)V
        //   48: aload 6
        //   50: aload 5
        //   52: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   55: aload_0
        //   56: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   59: sipush 7002
        //   62: aload 6
        //   64: aload 7
        //   66: iconst_0
        //   67: invokeinterface 41 5 0
        //   72: pop
        //   73: aload 7
        //   75: invokevirtual 44	android/os/Parcel:readException	()V
        //   78: aload 7
        //   80: invokevirtual 47	android/os/Parcel:recycle	()V
        //   83: aload 6
        //   85: invokevirtual 47	android/os/Parcel:recycle	()V
        //   88: return
        //   89: aconst_null
        //   90: astore 9
        //   92: goto -63 -> 29
        //   95: astore 8
        //   97: aload 7
        //   99: invokevirtual 47	android/os/Parcel:recycle	()V
        //   102: aload 6
        //   104: invokevirtual 47	android/os/Parcel:recycle	()V
        //   107: aload 8
        //   109: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	110	0	this	Proxy
        //   0	110	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	110	2	paramString1	String
        //   0	110	3	paramLong	long
        //   0	110	5	paramString2	String
        //   3	100	6	localParcel1	Parcel
        //   8	90	7	localParcel2	Parcel
        //   95	13	8	localObject	Object
        //   27	64	9	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   10	17	95	finally
        //   21	29	95	finally
        //   29	78	95	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, String paramString, IBinder paramIBinder, Bundle paramBundle)
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
        //   18: ifnull +83 -> 101
        //   21: aload_1
        //   22: invokeinterface 57 1 0
        //   27: astore 8
        //   29: aload 5
        //   31: aload 8
        //   33: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload 5
        //   38: aload_2
        //   39: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   42: aload 5
        //   44: aload_3
        //   45: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   48: aload 4
        //   50: ifnull +57 -> 107
        //   53: aload 5
        //   55: iconst_1
        //   56: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   59: aload 4
        //   61: aload 5
        //   63: iconst_0
        //   64: invokevirtual 122	android/os/Bundle:writeToParcel	(Landroid/os/Parcel;I)V
        //   67: aload_0
        //   68: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   71: sipush 5023
        //   74: aload 5
        //   76: aload 6
        //   78: iconst_0
        //   79: invokeinterface 41 5 0
        //   84: pop
        //   85: aload 6
        //   87: invokevirtual 44	android/os/Parcel:readException	()V
        //   90: aload 6
        //   92: invokevirtual 47	android/os/Parcel:recycle	()V
        //   95: aload 5
        //   97: invokevirtual 47	android/os/Parcel:recycle	()V
        //   100: return
        //   101: aconst_null
        //   102: astore 8
        //   104: goto -75 -> 29
        //   107: aload 5
        //   109: iconst_0
        //   110: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   113: goto -46 -> 67
        //   116: astore 7
        //   118: aload 6
        //   120: invokevirtual 47	android/os/Parcel:recycle	()V
        //   123: aload 5
        //   125: invokevirtual 47	android/os/Parcel:recycle	()V
        //   128: aload 7
        //   130: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	131	0	this	Proxy
        //   0	131	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	131	2	paramString	String
        //   0	131	3	paramIBinder	IBinder
        //   0	131	4	paramBundle	Bundle
        //   3	121	5	localParcel1	Parcel
        //   8	111	6	localParcel2	Parcel
        //   116	13	7	localObject	Object
        //   27	76	8	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   10	17	116	finally
        //   21	29	116	finally
        //   29	48	116	finally
        //   53	67	116	finally
        //   67	90	116	finally
        //   107	113	116	finally
      }
      
      public void a(IGamesCallbacks paramIGamesCallbacks, String paramString, SnapshotMetadataChange paramSnapshotMetadataChange, Contents paramContents)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        label142:
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder localIBinder;
            if (paramIGamesCallbacks != null)
            {
              localIBinder = paramIGamesCallbacks.asBinder();
              localParcel1.writeStrongBinder(localIBinder);
              localParcel1.writeString(paramString);
              if (paramSnapshotMetadataChange != null)
              {
                localParcel1.writeInt(1);
                paramSnapshotMetadataChange.writeToParcel(localParcel1, 0);
                if (paramContents == null) {
                  break label142;
                }
                localParcel1.writeInt(1);
                paramContents.writeToParcel(localParcel1, 0);
                this.lb.transact(12007, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localIBinder = null;
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
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2)
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
        //   18: ifnull +64 -> 82
        //   21: aload_1
        //   22: invokeinterface 57 1 0
        //   27: astore 7
        //   29: aload 4
        //   31: aload 7
        //   33: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload 4
        //   38: aload_2
        //   39: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   42: aload 4
        //   44: aload_3
        //   45: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   48: aload_0
        //   49: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   52: sipush 5038
        //   55: aload 4
        //   57: aload 5
        //   59: iconst_0
        //   60: invokeinterface 41 5 0
        //   65: pop
        //   66: aload 5
        //   68: invokevirtual 44	android/os/Parcel:readException	()V
        //   71: aload 5
        //   73: invokevirtual 47	android/os/Parcel:recycle	()V
        //   76: aload 4
        //   78: invokevirtual 47	android/os/Parcel:recycle	()V
        //   81: return
        //   82: aconst_null
        //   83: astore 7
        //   85: goto -56 -> 29
        //   88: astore 6
        //   90: aload 5
        //   92: invokevirtual 47	android/os/Parcel:recycle	()V
        //   95: aload 4
        //   97: invokevirtual 47	android/os/Parcel:recycle	()V
        //   100: aload 6
        //   102: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	103	0	this	Proxy
        //   0	103	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	103	2	paramString1	String
        //   0	103	3	paramString2	String
        //   3	93	4	localParcel1	Parcel
        //   8	83	5	localParcel2	Parcel
        //   88	13	6	localObject	Object
        //   27	57	7	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   10	17	88	finally
        //   21	29	88	finally
        //   29	71	88	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2, int paramInt1, int paramInt2)
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
        //   18: ifnull +78 -> 96
        //   21: aload_1
        //   22: invokeinterface 57 1 0
        //   27: astore 9
        //   29: aload 6
        //   31: aload 9
        //   33: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload 6
        //   38: aload_2
        //   39: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   42: aload 6
        //   44: aload_3
        //   45: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   48: aload 6
        //   50: iload 4
        //   52: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   55: aload 6
        //   57: iload 5
        //   59: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   62: aload_0
        //   63: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   66: sipush 8001
        //   69: aload 6
        //   71: aload 7
        //   73: iconst_0
        //   74: invokeinterface 41 5 0
        //   79: pop
        //   80: aload 7
        //   82: invokevirtual 44	android/os/Parcel:readException	()V
        //   85: aload 7
        //   87: invokevirtual 47	android/os/Parcel:recycle	()V
        //   90: aload 6
        //   92: invokevirtual 47	android/os/Parcel:recycle	()V
        //   95: return
        //   96: aconst_null
        //   97: astore 9
        //   99: goto -70 -> 29
        //   102: astore 8
        //   104: aload 7
        //   106: invokevirtual 47	android/os/Parcel:recycle	()V
        //   109: aload 6
        //   111: invokevirtual 47	android/os/Parcel:recycle	()V
        //   114: aload 8
        //   116: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	117	0	this	Proxy
        //   0	117	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	117	2	paramString1	String
        //   0	117	3	paramString2	String
        //   0	117	4	paramInt1	int
        //   0	117	5	paramInt2	int
        //   3	107	6	localParcel1	Parcel
        //   8	97	7	localParcel2	Parcel
        //   102	13	8	localObject	Object
        //   27	71	9	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   10	17	102	finally
        //   21	29	102	finally
        //   29	85	102	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 7
        //   5: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 8
        //   10: aload 7
        //   12: ldc 27
        //   14: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +85 -> 103
        //   21: aload_1
        //   22: invokeinterface 57 1 0
        //   27: astore 10
        //   29: aload 7
        //   31: aload 10
        //   33: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload 7
        //   38: aload_2
        //   39: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   42: aload 7
        //   44: aload_3
        //   45: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   48: aload 7
        //   50: iload 4
        //   52: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   55: aload 7
        //   57: iload 5
        //   59: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   62: aload 7
        //   64: iload 6
        //   66: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   69: aload_0
        //   70: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   73: sipush 10010
        //   76: aload 7
        //   78: aload 8
        //   80: iconst_0
        //   81: invokeinterface 41 5 0
        //   86: pop
        //   87: aload 8
        //   89: invokevirtual 44	android/os/Parcel:readException	()V
        //   92: aload 8
        //   94: invokevirtual 47	android/os/Parcel:recycle	()V
        //   97: aload 7
        //   99: invokevirtual 47	android/os/Parcel:recycle	()V
        //   102: return
        //   103: aconst_null
        //   104: astore 10
        //   106: goto -77 -> 29
        //   109: astore 9
        //   111: aload 8
        //   113: invokevirtual 47	android/os/Parcel:recycle	()V
        //   116: aload 7
        //   118: invokevirtual 47	android/os/Parcel:recycle	()V
        //   121: aload 9
        //   123: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	124	0	this	Proxy
        //   0	124	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	124	2	paramString1	String
        //   0	124	3	paramString2	String
        //   0	124	4	paramInt1	int
        //   0	124	5	paramInt2	int
        //   0	124	6	paramInt3	int
        //   3	114	7	localParcel1	Parcel
        //   8	104	8	localParcel2	Parcel
        //   109	13	9	localObject	Object
        //   27	78	10	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   10	17	109	finally
        //   21	29	109	finally
        //   29	92	109	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 8
        //   5: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 9
        //   10: aload 8
        //   12: ldc 27
        //   14: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +103 -> 121
        //   21: aload_1
        //   22: invokeinterface 57 1 0
        //   27: astore 11
        //   29: aload 8
        //   31: aload 11
        //   33: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload 8
        //   38: aload_2
        //   39: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   42: aload 8
        //   44: aload_3
        //   45: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   48: aload 8
        //   50: iload 4
        //   52: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   55: aload 8
        //   57: iload 5
        //   59: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   62: aload 8
        //   64: iload 6
        //   66: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   69: iconst_0
        //   70: istore 12
        //   72: iload 7
        //   74: ifeq +6 -> 80
        //   77: iconst_1
        //   78: istore 12
        //   80: aload 8
        //   82: iload 12
        //   84: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   87: aload_0
        //   88: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   91: sipush 5039
        //   94: aload 8
        //   96: aload 9
        //   98: iconst_0
        //   99: invokeinterface 41 5 0
        //   104: pop
        //   105: aload 9
        //   107: invokevirtual 44	android/os/Parcel:readException	()V
        //   110: aload 9
        //   112: invokevirtual 47	android/os/Parcel:recycle	()V
        //   115: aload 8
        //   117: invokevirtual 47	android/os/Parcel:recycle	()V
        //   120: return
        //   121: aconst_null
        //   122: astore 11
        //   124: goto -95 -> 29
        //   127: astore 10
        //   129: aload 9
        //   131: invokevirtual 47	android/os/Parcel:recycle	()V
        //   134: aload 8
        //   136: invokevirtual 47	android/os/Parcel:recycle	()V
        //   139: aload 10
        //   141: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	142	0	this	Proxy
        //   0	142	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	142	2	paramString1	String
        //   0	142	3	paramString2	String
        //   0	142	4	paramInt1	int
        //   0	142	5	paramInt2	int
        //   0	142	6	paramInt3	int
        //   0	142	7	paramBoolean	boolean
        //   3	132	8	localParcel1	Parcel
        //   8	122	9	localParcel2	Parcel
        //   127	13	10	localObject	Object
        //   27	96	11	localIBinder	IBinder
        //   70	13	12	i	int
        // Exception table:
        //   from	to	target	type
        //   10	17	127	finally
        //   21	29	127	finally
        //   29	69	127	finally
        //   80	110	127	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore 7
        //   3: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 8
        //   8: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 9
        //   13: aload 8
        //   15: ldc 27
        //   17: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload_1
        //   21: ifnull +99 -> 120
        //   24: aload_1
        //   25: invokeinterface 57 1 0
        //   30: astore 11
        //   32: aload 8
        //   34: aload 11
        //   36: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   39: aload 8
        //   41: aload_2
        //   42: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   45: aload 8
        //   47: aload_3
        //   48: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   51: aload 8
        //   53: iload 4
        //   55: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   58: iload 5
        //   60: ifeq +66 -> 126
        //   63: iload 7
        //   65: istore 12
        //   67: aload 8
        //   69: iload 12
        //   71: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   74: iload 6
        //   76: ifeq +56 -> 132
        //   79: aload 8
        //   81: iload 7
        //   83: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   86: aload_0
        //   87: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   90: sipush 9028
        //   93: aload 8
        //   95: aload 9
        //   97: iconst_0
        //   98: invokeinterface 41 5 0
        //   103: pop
        //   104: aload 9
        //   106: invokevirtual 44	android/os/Parcel:readException	()V
        //   109: aload 9
        //   111: invokevirtual 47	android/os/Parcel:recycle	()V
        //   114: aload 8
        //   116: invokevirtual 47	android/os/Parcel:recycle	()V
        //   119: return
        //   120: aconst_null
        //   121: astore 11
        //   123: goto -91 -> 32
        //   126: iconst_0
        //   127: istore 12
        //   129: goto -62 -> 67
        //   132: iconst_0
        //   133: istore 7
        //   135: goto -56 -> 79
        //   138: astore 10
        //   140: aload 9
        //   142: invokevirtual 47	android/os/Parcel:recycle	()V
        //   145: aload 8
        //   147: invokevirtual 47	android/os/Parcel:recycle	()V
        //   150: aload 10
        //   152: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	153	0	this	Proxy
        //   0	153	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	153	2	paramString1	String
        //   0	153	3	paramString2	String
        //   0	153	4	paramInt	int
        //   0	153	5	paramBoolean1	boolean
        //   0	153	6	paramBoolean2	boolean
        //   1	133	7	i	int
        //   6	140	8	localParcel1	Parcel
        //   11	130	9	localParcel2	Parcel
        //   138	13	10	localObject	Object
        //   30	92	11	localIBinder	IBinder
        //   65	63	12	j	int
        // Exception table:
        //   from	to	target	type
        //   13	20	138	finally
        //   24	32	138	finally
        //   32	58	138	finally
        //   67	74	138	finally
        //   79	109	138	finally
      }
      
      public void a(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2, SnapshotMetadataChange paramSnapshotMetadataChange, Contents paramContents)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        label150:
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder localIBinder;
            if (paramIGamesCallbacks != null)
            {
              localIBinder = paramIGamesCallbacks.asBinder();
              localParcel1.writeStrongBinder(localIBinder);
              localParcel1.writeString(paramString1);
              localParcel1.writeString(paramString2);
              if (paramSnapshotMetadataChange != null)
              {
                localParcel1.writeInt(1);
                paramSnapshotMetadataChange.writeToParcel(localParcel1, 0);
                if (paramContents == null) {
                  break label150;
                }
                localParcel1.writeInt(1);
                paramContents.writeToParcel(localParcel1, 0);
                this.lb.transact(12033, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localIBinder = null;
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
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2, boolean paramBoolean)
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
        //   18: ifnull +82 -> 100
        //   21: aload_1
        //   22: invokeinterface 57 1 0
        //   27: astore 8
        //   29: aload 5
        //   31: aload 8
        //   33: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload 5
        //   38: aload_2
        //   39: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   42: aload 5
        //   44: aload_3
        //   45: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   48: iconst_0
        //   49: istore 9
        //   51: iload 4
        //   53: ifeq +6 -> 59
        //   56: iconst_1
        //   57: istore 9
        //   59: aload 5
        //   61: iload 9
        //   63: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   66: aload_0
        //   67: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   70: sipush 6002
        //   73: aload 5
        //   75: aload 6
        //   77: iconst_0
        //   78: invokeinterface 41 5 0
        //   83: pop
        //   84: aload 6
        //   86: invokevirtual 44	android/os/Parcel:readException	()V
        //   89: aload 6
        //   91: invokevirtual 47	android/os/Parcel:recycle	()V
        //   94: aload 5
        //   96: invokevirtual 47	android/os/Parcel:recycle	()V
        //   99: return
        //   100: aconst_null
        //   101: astore 8
        //   103: goto -74 -> 29
        //   106: astore 7
        //   108: aload 6
        //   110: invokevirtual 47	android/os/Parcel:recycle	()V
        //   113: aload 5
        //   115: invokevirtual 47	android/os/Parcel:recycle	()V
        //   118: aload 7
        //   120: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	121	0	this	Proxy
        //   0	121	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	121	2	paramString1	String
        //   0	121	3	paramString2	String
        //   0	121	4	paramBoolean	boolean
        //   3	111	5	localParcel1	Parcel
        //   8	101	6	localParcel2	Parcel
        //   106	13	7	localObject	Object
        //   27	75	8	localIBinder	IBinder
        //   49	13	9	i	int
        // Exception table:
        //   from	to	target	type
        //   10	17	106	finally
        //   21	29	106	finally
        //   29	48	106	finally
        //   59	89	106	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2, int[] paramArrayOfInt, int paramInt, boolean paramBoolean)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 7
        //   5: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 8
        //   10: aload 7
        //   12: ldc 27
        //   14: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +96 -> 114
        //   21: aload_1
        //   22: invokeinterface 57 1 0
        //   27: astore 10
        //   29: aload 7
        //   31: aload 10
        //   33: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload 7
        //   38: aload_2
        //   39: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   42: aload 7
        //   44: aload_3
        //   45: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   48: aload 7
        //   50: aload 4
        //   52: invokevirtual 141	android/os/Parcel:writeIntArray	([I)V
        //   55: aload 7
        //   57: iload 5
        //   59: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   62: iconst_0
        //   63: istore 11
        //   65: iload 6
        //   67: ifeq +6 -> 73
        //   70: iconst_1
        //   71: istore 11
        //   73: aload 7
        //   75: iload 11
        //   77: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   80: aload_0
        //   81: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   84: sipush 12015
        //   87: aload 7
        //   89: aload 8
        //   91: iconst_0
        //   92: invokeinterface 41 5 0
        //   97: pop
        //   98: aload 8
        //   100: invokevirtual 44	android/os/Parcel:readException	()V
        //   103: aload 8
        //   105: invokevirtual 47	android/os/Parcel:recycle	()V
        //   108: aload 7
        //   110: invokevirtual 47	android/os/Parcel:recycle	()V
        //   113: return
        //   114: aconst_null
        //   115: astore 10
        //   117: goto -88 -> 29
        //   120: astore 9
        //   122: aload 8
        //   124: invokevirtual 47	android/os/Parcel:recycle	()V
        //   127: aload 7
        //   129: invokevirtual 47	android/os/Parcel:recycle	()V
        //   132: aload 9
        //   134: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	135	0	this	Proxy
        //   0	135	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	135	2	paramString1	String
        //   0	135	3	paramString2	String
        //   0	135	4	paramArrayOfInt	int[]
        //   0	135	5	paramInt	int
        //   0	135	6	paramBoolean	boolean
        //   3	125	7	localParcel1	Parcel
        //   8	115	8	localParcel2	Parcel
        //   120	13	9	localObject	Object
        //   27	89	10	localIBinder	IBinder
        //   63	13	11	i	int
        // Exception table:
        //   from	to	target	type
        //   10	17	120	finally
        //   21	29	120	finally
        //   29	62	120	finally
        //   73	103	120	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2, String[] paramArrayOfString)
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
        //   18: ifnull +71 -> 89
        //   21: aload_1
        //   22: invokeinterface 57 1 0
        //   27: astore 8
        //   29: aload 5
        //   31: aload 8
        //   33: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload 5
        //   38: aload_2
        //   39: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   42: aload 5
        //   44: aload_3
        //   45: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   48: aload 5
        //   50: aload 4
        //   52: invokevirtual 135	android/os/Parcel:writeStringArray	([Ljava/lang/String;)V
        //   55: aload_0
        //   56: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   59: sipush 10008
        //   62: aload 5
        //   64: aload 6
        //   66: iconst_0
        //   67: invokeinterface 41 5 0
        //   72: pop
        //   73: aload 6
        //   75: invokevirtual 44	android/os/Parcel:readException	()V
        //   78: aload 6
        //   80: invokevirtual 47	android/os/Parcel:recycle	()V
        //   83: aload 5
        //   85: invokevirtual 47	android/os/Parcel:recycle	()V
        //   88: return
        //   89: aconst_null
        //   90: astore 8
        //   92: goto -63 -> 29
        //   95: astore 7
        //   97: aload 6
        //   99: invokevirtual 47	android/os/Parcel:recycle	()V
        //   102: aload 5
        //   104: invokevirtual 47	android/os/Parcel:recycle	()V
        //   107: aload 7
        //   109: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	110	0	this	Proxy
        //   0	110	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	110	2	paramString1	String
        //   0	110	3	paramString2	String
        //   0	110	4	paramArrayOfString	String[]
        //   3	100	5	localParcel1	Parcel
        //   8	90	6	localParcel2	Parcel
        //   95	13	7	localObject	Object
        //   27	64	8	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   10	17	95	finally
        //   21	29	95	finally
        //   29	78	95	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2, String[] paramArrayOfString, boolean paramBoolean)
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
        //   18: ifnull +89 -> 107
        //   21: aload_1
        //   22: invokeinterface 57 1 0
        //   27: astore 9
        //   29: aload 6
        //   31: aload 9
        //   33: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload 6
        //   38: aload_2
        //   39: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   42: aload 6
        //   44: aload_3
        //   45: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   48: aload 6
        //   50: aload 4
        //   52: invokevirtual 135	android/os/Parcel:writeStringArray	([Ljava/lang/String;)V
        //   55: iconst_0
        //   56: istore 10
        //   58: iload 5
        //   60: ifeq +6 -> 66
        //   63: iconst_1
        //   64: istore 10
        //   66: aload 6
        //   68: iload 10
        //   70: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   73: aload_0
        //   74: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   77: sipush 12028
        //   80: aload 6
        //   82: aload 7
        //   84: iconst_0
        //   85: invokeinterface 41 5 0
        //   90: pop
        //   91: aload 7
        //   93: invokevirtual 44	android/os/Parcel:readException	()V
        //   96: aload 7
        //   98: invokevirtual 47	android/os/Parcel:recycle	()V
        //   101: aload 6
        //   103: invokevirtual 47	android/os/Parcel:recycle	()V
        //   106: return
        //   107: aconst_null
        //   108: astore 9
        //   110: goto -81 -> 29
        //   113: astore 8
        //   115: aload 7
        //   117: invokevirtual 47	android/os/Parcel:recycle	()V
        //   120: aload 6
        //   122: invokevirtual 47	android/os/Parcel:recycle	()V
        //   125: aload 8
        //   127: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	128	0	this	Proxy
        //   0	128	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	128	2	paramString1	String
        //   0	128	3	paramString2	String
        //   0	128	4	paramArrayOfString	String[]
        //   0	128	5	paramBoolean	boolean
        //   3	118	6	localParcel1	Parcel
        //   8	108	7	localParcel2	Parcel
        //   113	13	8	localObject	Object
        //   27	82	9	localIBinder	IBinder
        //   56	13	10	i	int
        // Exception table:
        //   from	to	target	type
        //   10	17	113	finally
        //   21	29	113	finally
        //   29	55	113	finally
        //   66	96	113	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, String paramString, boolean paramBoolean)
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
        //   18: ifnull +75 -> 93
        //   21: aload_1
        //   22: invokeinterface 57 1 0
        //   27: astore 7
        //   29: aload 4
        //   31: aload 7
        //   33: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload 4
        //   38: aload_2
        //   39: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   42: iconst_0
        //   43: istore 8
        //   45: iload_3
        //   46: ifeq +6 -> 52
        //   49: iconst_1
        //   50: istore 8
        //   52: aload 4
        //   54: iload 8
        //   56: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   59: aload_0
        //   60: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   63: sipush 5054
        //   66: aload 4
        //   68: aload 5
        //   70: iconst_0
        //   71: invokeinterface 41 5 0
        //   76: pop
        //   77: aload 5
        //   79: invokevirtual 44	android/os/Parcel:readException	()V
        //   82: aload 5
        //   84: invokevirtual 47	android/os/Parcel:recycle	()V
        //   87: aload 4
        //   89: invokevirtual 47	android/os/Parcel:recycle	()V
        //   92: return
        //   93: aconst_null
        //   94: astore 7
        //   96: goto -67 -> 29
        //   99: astore 6
        //   101: aload 5
        //   103: invokevirtual 47	android/os/Parcel:recycle	()V
        //   106: aload 4
        //   108: invokevirtual 47	android/os/Parcel:recycle	()V
        //   111: aload 6
        //   113: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	114	0	this	Proxy
        //   0	114	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	114	2	paramString	String
        //   0	114	3	paramBoolean	boolean
        //   3	104	4	localParcel1	Parcel
        //   8	94	5	localParcel2	Parcel
        //   99	13	6	localObject	Object
        //   27	68	7	localIBinder	IBinder
        //   43	12	8	i	int
        // Exception table:
        //   from	to	target	type
        //   10	17	99	finally
        //   21	29	99	finally
        //   29	42	99	finally
        //   52	82	99	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, String paramString1, byte[] paramArrayOfByte, String paramString2, ParticipantResult[] paramArrayOfParticipantResult)
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
        //   18: ifnull +79 -> 97
        //   21: aload_1
        //   22: invokeinterface 57 1 0
        //   27: astore 9
        //   29: aload 6
        //   31: aload 9
        //   33: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload 6
        //   38: aload_2
        //   39: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   42: aload 6
        //   44: aload_3
        //   45: invokevirtual 64	android/os/Parcel:writeByteArray	([B)V
        //   48: aload 6
        //   50: aload 4
        //   52: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   55: aload 6
        //   57: aload 5
        //   59: iconst_0
        //   60: invokevirtual 110	android/os/Parcel:writeTypedArray	([Landroid/os/Parcelable;I)V
        //   63: aload_0
        //   64: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   67: sipush 8007
        //   70: aload 6
        //   72: aload 7
        //   74: iconst_0
        //   75: invokeinterface 41 5 0
        //   80: pop
        //   81: aload 7
        //   83: invokevirtual 44	android/os/Parcel:readException	()V
        //   86: aload 7
        //   88: invokevirtual 47	android/os/Parcel:recycle	()V
        //   91: aload 6
        //   93: invokevirtual 47	android/os/Parcel:recycle	()V
        //   96: return
        //   97: aconst_null
        //   98: astore 9
        //   100: goto -71 -> 29
        //   103: astore 8
        //   105: aload 7
        //   107: invokevirtual 47	android/os/Parcel:recycle	()V
        //   110: aload 6
        //   112: invokevirtual 47	android/os/Parcel:recycle	()V
        //   115: aload 8
        //   117: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	118	0	this	Proxy
        //   0	118	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	118	2	paramString1	String
        //   0	118	3	paramArrayOfByte	byte[]
        //   0	118	4	paramString2	String
        //   0	118	5	paramArrayOfParticipantResult	ParticipantResult[]
        //   3	108	6	localParcel1	Parcel
        //   8	98	7	localParcel2	Parcel
        //   103	13	8	localObject	Object
        //   27	72	9	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   10	17	103	finally
        //   21	29	103	finally
        //   29	86	103	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, String paramString, byte[] paramArrayOfByte, ParticipantResult[] paramArrayOfParticipantResult)
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
        //   18: ifnull +72 -> 90
        //   21: aload_1
        //   22: invokeinterface 57 1 0
        //   27: astore 8
        //   29: aload 5
        //   31: aload 8
        //   33: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload 5
        //   38: aload_2
        //   39: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   42: aload 5
        //   44: aload_3
        //   45: invokevirtual 64	android/os/Parcel:writeByteArray	([B)V
        //   48: aload 5
        //   50: aload 4
        //   52: iconst_0
        //   53: invokevirtual 110	android/os/Parcel:writeTypedArray	([Landroid/os/Parcelable;I)V
        //   56: aload_0
        //   57: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   60: sipush 8008
        //   63: aload 5
        //   65: aload 6
        //   67: iconst_0
        //   68: invokeinterface 41 5 0
        //   73: pop
        //   74: aload 6
        //   76: invokevirtual 44	android/os/Parcel:readException	()V
        //   79: aload 6
        //   81: invokevirtual 47	android/os/Parcel:recycle	()V
        //   84: aload 5
        //   86: invokevirtual 47	android/os/Parcel:recycle	()V
        //   89: return
        //   90: aconst_null
        //   91: astore 8
        //   93: goto -64 -> 29
        //   96: astore 7
        //   98: aload 6
        //   100: invokevirtual 47	android/os/Parcel:recycle	()V
        //   103: aload 5
        //   105: invokevirtual 47	android/os/Parcel:recycle	()V
        //   108: aload 7
        //   110: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	111	0	this	Proxy
        //   0	111	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	111	2	paramString	String
        //   0	111	3	paramArrayOfByte	byte[]
        //   0	111	4	paramArrayOfParticipantResult	ParticipantResult[]
        //   3	101	5	localParcel1	Parcel
        //   8	91	6	localParcel2	Parcel
        //   96	13	7	localObject	Object
        //   27	65	8	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   10	17	96	finally
        //   21	29	96	finally
        //   29	79	96	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, String paramString, int[] paramArrayOfInt)
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
        //   18: ifnull +64 -> 82
        //   21: aload_1
        //   22: invokeinterface 57 1 0
        //   27: astore 7
        //   29: aload 4
        //   31: aload 7
        //   33: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload 4
        //   38: aload_2
        //   39: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   42: aload 4
        //   44: aload_3
        //   45: invokevirtual 141	android/os/Parcel:writeIntArray	([I)V
        //   48: aload_0
        //   49: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   52: sipush 8017
        //   55: aload 4
        //   57: aload 5
        //   59: iconst_0
        //   60: invokeinterface 41 5 0
        //   65: pop
        //   66: aload 5
        //   68: invokevirtual 44	android/os/Parcel:readException	()V
        //   71: aload 5
        //   73: invokevirtual 47	android/os/Parcel:recycle	()V
        //   76: aload 4
        //   78: invokevirtual 47	android/os/Parcel:recycle	()V
        //   81: return
        //   82: aconst_null
        //   83: astore 7
        //   85: goto -56 -> 29
        //   88: astore 6
        //   90: aload 5
        //   92: invokevirtual 47	android/os/Parcel:recycle	()V
        //   95: aload 4
        //   97: invokevirtual 47	android/os/Parcel:recycle	()V
        //   100: aload 6
        //   102: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	103	0	this	Proxy
        //   0	103	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	103	2	paramString	String
        //   0	103	3	paramArrayOfInt	int[]
        //   3	93	4	localParcel1	Parcel
        //   8	83	5	localParcel2	Parcel
        //   88	13	6	localObject	Object
        //   27	57	7	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   10	17	88	finally
        //   21	29	88	finally
        //   29	71	88	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, String paramString, String[] paramArrayOfString, int paramInt1, byte[] paramArrayOfByte, int paramInt2)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 7
        //   5: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 8
        //   10: aload 7
        //   12: ldc 27
        //   14: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +85 -> 103
        //   21: aload_1
        //   22: invokeinterface 57 1 0
        //   27: astore 10
        //   29: aload 7
        //   31: aload 10
        //   33: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload 7
        //   38: aload_2
        //   39: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   42: aload 7
        //   44: aload_3
        //   45: invokevirtual 135	android/os/Parcel:writeStringArray	([Ljava/lang/String;)V
        //   48: aload 7
        //   50: iload 4
        //   52: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   55: aload 7
        //   57: aload 5
        //   59: invokevirtual 64	android/os/Parcel:writeByteArray	([B)V
        //   62: aload 7
        //   64: iload 6
        //   66: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   69: aload_0
        //   70: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   73: sipush 10005
        //   76: aload 7
        //   78: aload 8
        //   80: iconst_0
        //   81: invokeinterface 41 5 0
        //   86: pop
        //   87: aload 8
        //   89: invokevirtual 44	android/os/Parcel:readException	()V
        //   92: aload 8
        //   94: invokevirtual 47	android/os/Parcel:recycle	()V
        //   97: aload 7
        //   99: invokevirtual 47	android/os/Parcel:recycle	()V
        //   102: return
        //   103: aconst_null
        //   104: astore 10
        //   106: goto -77 -> 29
        //   109: astore 9
        //   111: aload 8
        //   113: invokevirtual 47	android/os/Parcel:recycle	()V
        //   116: aload 7
        //   118: invokevirtual 47	android/os/Parcel:recycle	()V
        //   121: aload 9
        //   123: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	124	0	this	Proxy
        //   0	124	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	124	2	paramString	String
        //   0	124	3	paramArrayOfString	String[]
        //   0	124	4	paramInt1	int
        //   0	124	5	paramArrayOfByte	byte[]
        //   0	124	6	paramInt2	int
        //   3	114	7	localParcel1	Parcel
        //   8	104	8	localParcel2	Parcel
        //   109	13	9	localObject	Object
        //   27	78	10	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   10	17	109	finally
        //   21	29	109	finally
        //   29	92	109	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, boolean paramBoolean)
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
        //   16: ifnull +65 -> 81
        //   19: aload_1
        //   20: invokeinterface 57 1 0
        //   25: astore 6
        //   27: aload_3
        //   28: aload 6
        //   30: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
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
        //   50: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   53: sipush 6001
        //   56: aload_3
        //   57: aload 4
        //   59: iconst_0
        //   60: invokeinterface 41 5 0
        //   65: pop
        //   66: aload 4
        //   68: invokevirtual 44	android/os/Parcel:readException	()V
        //   71: aload 4
        //   73: invokevirtual 47	android/os/Parcel:recycle	()V
        //   76: aload_3
        //   77: invokevirtual 47	android/os/Parcel:recycle	()V
        //   80: return
        //   81: aconst_null
        //   82: astore 6
        //   84: goto -57 -> 27
        //   87: astore 5
        //   89: aload 4
        //   91: invokevirtual 47	android/os/Parcel:recycle	()V
        //   94: aload_3
        //   95: invokevirtual 47	android/os/Parcel:recycle	()V
        //   98: aload 5
        //   100: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	101	0	this	Proxy
        //   0	101	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	101	2	paramBoolean	boolean
        //   3	92	3	localParcel1	Parcel
        //   7	83	4	localParcel2	Parcel
        //   87	12	5	localObject	Object
        //   25	58	6	localIBinder	IBinder
        //   34	11	7	i	int
        // Exception table:
        //   from	to	target	type
        //   9	15	87	finally
        //   19	27	87	finally
        //   27	33	87	finally
        //   43	71	87	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, boolean paramBoolean, Bundle paramBundle)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore 4
        //   3: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 5
        //   8: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 6
        //   13: aload 5
        //   15: ldc 27
        //   17: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload_1
        //   21: ifnull +80 -> 101
        //   24: aload_1
        //   25: invokeinterface 57 1 0
        //   30: astore 8
        //   32: aload 5
        //   34: aload 8
        //   36: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   39: iload_2
        //   40: ifeq +67 -> 107
        //   43: aload 5
        //   45: iload 4
        //   47: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   50: aload_3
        //   51: ifnull +62 -> 113
        //   54: aload 5
        //   56: iconst_1
        //   57: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   60: aload_3
        //   61: aload 5
        //   63: iconst_0
        //   64: invokevirtual 122	android/os/Bundle:writeToParcel	(Landroid/os/Parcel;I)V
        //   67: aload_0
        //   68: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   71: sipush 5063
        //   74: aload 5
        //   76: aload 6
        //   78: iconst_0
        //   79: invokeinterface 41 5 0
        //   84: pop
        //   85: aload 6
        //   87: invokevirtual 44	android/os/Parcel:readException	()V
        //   90: aload 6
        //   92: invokevirtual 47	android/os/Parcel:recycle	()V
        //   95: aload 5
        //   97: invokevirtual 47	android/os/Parcel:recycle	()V
        //   100: return
        //   101: aconst_null
        //   102: astore 8
        //   104: goto -72 -> 32
        //   107: iconst_0
        //   108: istore 4
        //   110: goto -67 -> 43
        //   113: aload 5
        //   115: iconst_0
        //   116: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   119: goto -52 -> 67
        //   122: astore 7
        //   124: aload 6
        //   126: invokevirtual 47	android/os/Parcel:recycle	()V
        //   129: aload 5
        //   131: invokevirtual 47	android/os/Parcel:recycle	()V
        //   134: aload 7
        //   136: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	137	0	this	Proxy
        //   0	137	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	137	2	paramBoolean	boolean
        //   0	137	3	paramBundle	Bundle
        //   1	108	4	i	int
        //   6	124	5	localParcel1	Parcel
        //   11	114	6	localParcel2	Parcel
        //   122	13	7	localObject	Object
        //   30	73	8	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   13	20	122	finally
        //   24	32	122	finally
        //   32	39	122	finally
        //   43	50	122	finally
        //   54	67	122	finally
        //   67	90	122	finally
        //   113	119	122	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, boolean paramBoolean, String[] paramArrayOfString)
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
        //   18: ifnull +75 -> 93
        //   21: aload_1
        //   22: invokeinterface 57 1 0
        //   27: astore 7
        //   29: aload 4
        //   31: aload 7
        //   33: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: iconst_0
        //   37: istore 8
        //   39: iload_2
        //   40: ifeq +6 -> 46
        //   43: iconst_1
        //   44: istore 8
        //   46: aload 4
        //   48: iload 8
        //   50: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   53: aload 4
        //   55: aload_3
        //   56: invokevirtual 135	android/os/Parcel:writeStringArray	([Ljava/lang/String;)V
        //   59: aload_0
        //   60: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   63: sipush 12031
        //   66: aload 4
        //   68: aload 5
        //   70: iconst_0
        //   71: invokeinterface 41 5 0
        //   76: pop
        //   77: aload 5
        //   79: invokevirtual 44	android/os/Parcel:readException	()V
        //   82: aload 5
        //   84: invokevirtual 47	android/os/Parcel:recycle	()V
        //   87: aload 4
        //   89: invokevirtual 47	android/os/Parcel:recycle	()V
        //   92: return
        //   93: aconst_null
        //   94: astore 7
        //   96: goto -67 -> 29
        //   99: astore 6
        //   101: aload 5
        //   103: invokevirtual 47	android/os/Parcel:recycle	()V
        //   106: aload 4
        //   108: invokevirtual 47	android/os/Parcel:recycle	()V
        //   111: aload 6
        //   113: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	114	0	this	Proxy
        //   0	114	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	114	2	paramBoolean	boolean
        //   0	114	3	paramArrayOfString	String[]
        //   3	104	4	localParcel1	Parcel
        //   8	94	5	localParcel2	Parcel
        //   99	13	6	localObject	Object
        //   27	68	7	localIBinder	IBinder
        //   37	12	8	i	int
        // Exception table:
        //   from	to	target	type
        //   10	17	99	finally
        //   21	29	99	finally
        //   29	36	99	finally
        //   46	82	99	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, int[] paramArrayOfInt)
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
        //   16: ifnull +54 -> 70
        //   19: aload_1
        //   20: invokeinterface 57 1 0
        //   25: astore 6
        //   27: aload_3
        //   28: aload 6
        //   30: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   33: aload_3
        //   34: aload_2
        //   35: invokevirtual 141	android/os/Parcel:writeIntArray	([I)V
        //   38: aload_0
        //   39: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   42: sipush 8003
        //   45: aload_3
        //   46: aload 4
        //   48: iconst_0
        //   49: invokeinterface 41 5 0
        //   54: pop
        //   55: aload 4
        //   57: invokevirtual 44	android/os/Parcel:readException	()V
        //   60: aload 4
        //   62: invokevirtual 47	android/os/Parcel:recycle	()V
        //   65: aload_3
        //   66: invokevirtual 47	android/os/Parcel:recycle	()V
        //   69: return
        //   70: aconst_null
        //   71: astore 6
        //   73: goto -46 -> 27
        //   76: astore 5
        //   78: aload 4
        //   80: invokevirtual 47	android/os/Parcel:recycle	()V
        //   83: aload_3
        //   84: invokevirtual 47	android/os/Parcel:recycle	()V
        //   87: aload 5
        //   89: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	90	0	this	Proxy
        //   0	90	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	90	2	paramArrayOfInt	int[]
        //   3	81	3	localParcel1	Parcel
        //   7	72	4	localParcel2	Parcel
        //   76	12	5	localObject	Object
        //   25	47	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   9	15	76	finally
        //   19	27	76	finally
        //   27	60	76	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, int[] paramArrayOfInt, int paramInt, boolean paramBoolean)
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
        //   18: ifnull +82 -> 100
        //   21: aload_1
        //   22: invokeinterface 57 1 0
        //   27: astore 8
        //   29: aload 5
        //   31: aload 8
        //   33: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload 5
        //   38: aload_2
        //   39: invokevirtual 141	android/os/Parcel:writeIntArray	([I)V
        //   42: aload 5
        //   44: iload_3
        //   45: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   48: iconst_0
        //   49: istore 9
        //   51: iload 4
        //   53: ifeq +6 -> 59
        //   56: iconst_1
        //   57: istore 9
        //   59: aload 5
        //   61: iload 9
        //   63: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   66: aload_0
        //   67: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   70: sipush 12010
        //   73: aload 5
        //   75: aload 6
        //   77: iconst_0
        //   78: invokeinterface 41 5 0
        //   83: pop
        //   84: aload 6
        //   86: invokevirtual 44	android/os/Parcel:readException	()V
        //   89: aload 6
        //   91: invokevirtual 47	android/os/Parcel:recycle	()V
        //   94: aload 5
        //   96: invokevirtual 47	android/os/Parcel:recycle	()V
        //   99: return
        //   100: aconst_null
        //   101: astore 8
        //   103: goto -74 -> 29
        //   106: astore 7
        //   108: aload 6
        //   110: invokevirtual 47	android/os/Parcel:recycle	()V
        //   113: aload 5
        //   115: invokevirtual 47	android/os/Parcel:recycle	()V
        //   118: aload 7
        //   120: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	121	0	this	Proxy
        //   0	121	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	121	2	paramArrayOfInt	int[]
        //   0	121	3	paramInt	int
        //   0	121	4	paramBoolean	boolean
        //   3	111	5	localParcel1	Parcel
        //   8	101	6	localParcel2	Parcel
        //   106	13	7	localObject	Object
        //   27	75	8	localIBinder	IBinder
        //   49	13	9	i	int
        // Exception table:
        //   from	to	target	type
        //   10	17	106	finally
        //   21	29	106	finally
        //   29	48	106	finally
        //   59	89	106	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, String[] paramArrayOfString)
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
        //   16: ifnull +54 -> 70
        //   19: aload_1
        //   20: invokeinterface 57 1 0
        //   25: astore 6
        //   27: aload_3
        //   28: aload 6
        //   30: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   33: aload_3
        //   34: aload_2
        //   35: invokevirtual 135	android/os/Parcel:writeStringArray	([Ljava/lang/String;)V
        //   38: aload_0
        //   39: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   42: sipush 10006
        //   45: aload_3
        //   46: aload 4
        //   48: iconst_0
        //   49: invokeinterface 41 5 0
        //   54: pop
        //   55: aload 4
        //   57: invokevirtual 44	android/os/Parcel:readException	()V
        //   60: aload 4
        //   62: invokevirtual 47	android/os/Parcel:recycle	()V
        //   65: aload_3
        //   66: invokevirtual 47	android/os/Parcel:recycle	()V
        //   69: return
        //   70: aconst_null
        //   71: astore 6
        //   73: goto -46 -> 27
        //   76: astore 5
        //   78: aload 4
        //   80: invokevirtual 47	android/os/Parcel:recycle	()V
        //   83: aload_3
        //   84: invokevirtual 47	android/os/Parcel:recycle	()V
        //   87: aload 5
        //   89: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	90	0	this	Proxy
        //   0	90	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	90	2	paramArrayOfString	String[]
        //   3	81	3	localParcel1	Parcel
        //   7	72	4	localParcel2	Parcel
        //   76	12	5	localObject	Object
        //   25	47	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   9	15	76	finally
        //   19	27	76	finally
        //   27	60	76	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, String[] paramArrayOfString, boolean paramBoolean)
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
        //   18: ifnull +75 -> 93
        //   21: aload_1
        //   22: invokeinterface 57 1 0
        //   27: astore 7
        //   29: aload 4
        //   31: aload 7
        //   33: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload 4
        //   38: aload_2
        //   39: invokevirtual 135	android/os/Parcel:writeStringArray	([Ljava/lang/String;)V
        //   42: iconst_0
        //   43: istore 8
        //   45: iload_3
        //   46: ifeq +6 -> 52
        //   49: iconst_1
        //   50: istore 8
        //   52: aload 4
        //   54: iload 8
        //   56: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   59: aload_0
        //   60: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   63: sipush 12029
        //   66: aload 4
        //   68: aload 5
        //   70: iconst_0
        //   71: invokeinterface 41 5 0
        //   76: pop
        //   77: aload 5
        //   79: invokevirtual 44	android/os/Parcel:readException	()V
        //   82: aload 5
        //   84: invokevirtual 47	android/os/Parcel:recycle	()V
        //   87: aload 4
        //   89: invokevirtual 47	android/os/Parcel:recycle	()V
        //   92: return
        //   93: aconst_null
        //   94: astore 7
        //   96: goto -67 -> 29
        //   99: astore 6
        //   101: aload 5
        //   103: invokevirtual 47	android/os/Parcel:recycle	()V
        //   106: aload 4
        //   108: invokevirtual 47	android/os/Parcel:recycle	()V
        //   111: aload 6
        //   113: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	114	0	this	Proxy
        //   0	114	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	114	2	paramArrayOfString	String[]
        //   0	114	3	paramBoolean	boolean
        //   3	104	4	localParcel1	Parcel
        //   8	94	5	localParcel2	Parcel
        //   99	13	6	localObject	Object
        //   27	68	7	localIBinder	IBinder
        //   43	12	8	i	int
        // Exception table:
        //   from	to	target	type
        //   10	17	99	finally
        //   21	29	99	finally
        //   29	42	99	finally
        //   52	82	99	finally
      }
      
      /* Error */
      public void a(String paramString, IBinder paramIBinder, Bundle paramBundle)
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
        //   17: aload 4
        //   19: aload_1
        //   20: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   23: aload 4
        //   25: aload_2
        //   26: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   29: aload_3
        //   30: ifnull +50 -> 80
        //   33: aload 4
        //   35: iconst_1
        //   36: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   39: aload_3
        //   40: aload 4
        //   42: iconst_0
        //   43: invokevirtual 122	android/os/Bundle:writeToParcel	(Landroid/os/Parcel;I)V
        //   46: aload_0
        //   47: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   50: sipush 13002
        //   53: aload 4
        //   55: aload 5
        //   57: iconst_0
        //   58: invokeinterface 41 5 0
        //   63: pop
        //   64: aload 5
        //   66: invokevirtual 44	android/os/Parcel:readException	()V
        //   69: aload 5
        //   71: invokevirtual 47	android/os/Parcel:recycle	()V
        //   74: aload 4
        //   76: invokevirtual 47	android/os/Parcel:recycle	()V
        //   79: return
        //   80: aload 4
        //   82: iconst_0
        //   83: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   86: goto -40 -> 46
        //   89: astore 6
        //   91: aload 5
        //   93: invokevirtual 47	android/os/Parcel:recycle	()V
        //   96: aload 4
        //   98: invokevirtual 47	android/os/Parcel:recycle	()V
        //   101: aload 6
        //   103: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	104	0	this	Proxy
        //   0	104	1	paramString	String
        //   0	104	2	paramIBinder	IBinder
        //   0	104	3	paramBundle	Bundle
        //   3	94	4	localParcel1	Parcel
        //   8	84	5	localParcel2	Parcel
        //   89	13	6	localObject	Object
        // Exception table:
        //   from	to	target	type
        //   10	29	89	finally
        //   33	46	89	finally
        //   46	69	89	finally
        //   80	86	89	finally
      }
      
      public IBinder asBinder()
      {
        return this.lb;
      }
      
      public int b(byte[] paramArrayOfByte, String paramString, String[] paramArrayOfString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeByteArray(paramArrayOfByte);
          localParcel1.writeString(paramString);
          localParcel1.writeStringArray(paramArrayOfString);
          this.lb.transact(5034, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      /* Error */
      public Intent b(int paramInt1, int paramInt2, boolean paramBoolean)
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
        //   17: aload 4
        //   19: iload_1
        //   20: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   23: aload 4
        //   25: iload_2
        //   26: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   29: iconst_0
        //   30: istore 7
        //   32: iload_3
        //   33: ifeq +6 -> 39
        //   36: iconst_1
        //   37: istore 7
        //   39: aload 4
        //   41: iload 7
        //   43: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   46: aload_0
        //   47: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   50: sipush 9009
        //   53: aload 4
        //   55: aload 5
        //   57: iconst_0
        //   58: invokeinterface 41 5 0
        //   63: pop
        //   64: aload 5
        //   66: invokevirtual 44	android/os/Parcel:readException	()V
        //   69: aload 5
        //   71: invokevirtual 71	android/os/Parcel:readInt	()I
        //   74: ifeq +31 -> 105
        //   77: getstatic 78	android/content/Intent:CREATOR	Landroid/os/Parcelable$Creator;
        //   80: aload 5
        //   82: invokeinterface 84 2 0
        //   87: checkcast 74	android/content/Intent
        //   90: astore 9
        //   92: aload 5
        //   94: invokevirtual 47	android/os/Parcel:recycle	()V
        //   97: aload 4
        //   99: invokevirtual 47	android/os/Parcel:recycle	()V
        //   102: aload 9
        //   104: areturn
        //   105: aconst_null
        //   106: astore 9
        //   108: goto -16 -> 92
        //   111: astore 6
        //   113: aload 5
        //   115: invokevirtual 47	android/os/Parcel:recycle	()V
        //   118: aload 4
        //   120: invokevirtual 47	android/os/Parcel:recycle	()V
        //   123: aload 6
        //   125: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	126	0	this	Proxy
        //   0	126	1	paramInt1	int
        //   0	126	2	paramInt2	int
        //   0	126	3	paramBoolean	boolean
        //   3	116	4	localParcel1	Parcel
        //   8	106	5	localParcel2	Parcel
        //   111	13	6	localObject	Object
        //   30	12	7	i	int
        //   90	17	9	localIntent	Intent
        // Exception table:
        //   from	to	target	type
        //   10	29	111	finally
        //   39	92	111	finally
      }
      
      /* Error */
      public Intent b(int[] paramArrayOfInt)
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
        //   14: aload_2
        //   15: aload_1
        //   16: invokevirtual 141	android/os/Parcel:writeIntArray	([I)V
        //   19: aload_0
        //   20: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   23: sipush 12030
        //   26: aload_2
        //   27: aload_3
        //   28: iconst_0
        //   29: invokeinterface 41 5 0
        //   34: pop
        //   35: aload_3
        //   36: invokevirtual 44	android/os/Parcel:readException	()V
        //   39: aload_3
        //   40: invokevirtual 71	android/os/Parcel:readInt	()I
        //   43: ifeq +28 -> 71
        //   46: getstatic 78	android/content/Intent:CREATOR	Landroid/os/Parcelable$Creator;
        //   49: aload_3
        //   50: invokeinterface 84 2 0
        //   55: checkcast 74	android/content/Intent
        //   58: astore 6
        //   60: aload_3
        //   61: invokevirtual 47	android/os/Parcel:recycle	()V
        //   64: aload_2
        //   65: invokevirtual 47	android/os/Parcel:recycle	()V
        //   68: aload 6
        //   70: areturn
        //   71: aconst_null
        //   72: astore 6
        //   74: goto -14 -> 60
        //   77: astore 4
        //   79: aload_3
        //   80: invokevirtual 47	android/os/Parcel:recycle	()V
        //   83: aload_2
        //   84: invokevirtual 47	android/os/Parcel:recycle	()V
        //   87: aload 4
        //   89: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	90	0	this	Proxy
        //   0	90	1	paramArrayOfInt	int[]
        //   3	81	2	localParcel1	Parcel
        //   7	73	3	localParcel2	Parcel
        //   77	11	4	localObject	Object
        //   58	15	6	localIntent	Intent
        // Exception table:
        //   from	to	target	type
        //   8	60	77	finally
      }
      
      public void b(long paramLong, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeLong(paramLong);
          localParcel1.writeString(paramString);
          this.lb.transact(8021, localParcel1, localParcel2, 0);
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
      public void b(IGamesCallbacks paramIGamesCallbacks)
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
        //   15: ifnull +46 -> 61
        //   18: aload_1
        //   19: invokeinterface 57 1 0
        //   24: astore 5
        //   26: aload_2
        //   27: aload 5
        //   29: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   32: aload_0
        //   33: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   36: sipush 5017
        //   39: aload_2
        //   40: aload_3
        //   41: iconst_0
        //   42: invokeinterface 41 5 0
        //   47: pop
        //   48: aload_3
        //   49: invokevirtual 44	android/os/Parcel:readException	()V
        //   52: aload_3
        //   53: invokevirtual 47	android/os/Parcel:recycle	()V
        //   56: aload_2
        //   57: invokevirtual 47	android/os/Parcel:recycle	()V
        //   60: return
        //   61: aconst_null
        //   62: astore 5
        //   64: goto -38 -> 26
        //   67: astore 4
        //   69: aload_3
        //   70: invokevirtual 47	android/os/Parcel:recycle	()V
        //   73: aload_2
        //   74: invokevirtual 47	android/os/Parcel:recycle	()V
        //   77: aload 4
        //   79: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	80	0	this	Proxy
        //   0	80	1	paramIGamesCallbacks	IGamesCallbacks
        //   3	71	2	localParcel1	Parcel
        //   7	63	3	localParcel2	Parcel
        //   67	11	4	localObject	Object
        //   24	39	5	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   8	14	67	finally
        //   18	26	67	finally
        //   26	52	67	finally
      }
      
      /* Error */
      public void b(IGamesCallbacks paramIGamesCallbacks, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore 5
        //   3: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 6
        //   8: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 7
        //   13: aload 6
        //   15: ldc 27
        //   17: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload_1
        //   21: ifnull +85 -> 106
        //   24: aload_1
        //   25: invokeinterface 57 1 0
        //   30: astore 9
        //   32: aload 6
        //   34: aload 9
        //   36: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   39: aload 6
        //   41: iload_2
        //   42: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   45: iload_3
        //   46: ifeq +66 -> 112
        //   49: iload 5
        //   51: istore 10
        //   53: aload 6
        //   55: iload 10
        //   57: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   60: iload 4
        //   62: ifeq +56 -> 118
        //   65: aload 6
        //   67: iload 5
        //   69: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   72: aload_0
        //   73: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   76: sipush 5046
        //   79: aload 6
        //   81: aload 7
        //   83: iconst_0
        //   84: invokeinterface 41 5 0
        //   89: pop
        //   90: aload 7
        //   92: invokevirtual 44	android/os/Parcel:readException	()V
        //   95: aload 7
        //   97: invokevirtual 47	android/os/Parcel:recycle	()V
        //   100: aload 6
        //   102: invokevirtual 47	android/os/Parcel:recycle	()V
        //   105: return
        //   106: aconst_null
        //   107: astore 9
        //   109: goto -77 -> 32
        //   112: iconst_0
        //   113: istore 10
        //   115: goto -62 -> 53
        //   118: iconst_0
        //   119: istore 5
        //   121: goto -56 -> 65
        //   124: astore 8
        //   126: aload 7
        //   128: invokevirtual 47	android/os/Parcel:recycle	()V
        //   131: aload 6
        //   133: invokevirtual 47	android/os/Parcel:recycle	()V
        //   136: aload 8
        //   138: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	139	0	this	Proxy
        //   0	139	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	139	2	paramInt	int
        //   0	139	3	paramBoolean1	boolean
        //   0	139	4	paramBoolean2	boolean
        //   1	119	5	i	int
        //   6	126	6	localParcel1	Parcel
        //   11	116	7	localParcel2	Parcel
        //   124	13	8	localObject	Object
        //   30	78	9	localIBinder	IBinder
        //   51	63	10	j	int
        // Exception table:
        //   from	to	target	type
        //   13	20	124	finally
        //   24	32	124	finally
        //   32	45	124	finally
        //   53	60	124	finally
        //   65	95	124	finally
      }
      
      /* Error */
      public void b(IGamesCallbacks paramIGamesCallbacks, long paramLong)
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
        //   18: ifnull +58 -> 76
        //   21: aload_1
        //   22: invokeinterface 57 1 0
        //   27: astore 7
        //   29: aload 4
        //   31: aload 7
        //   33: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload 4
        //   38: lload_2
        //   39: invokevirtual 118	android/os/Parcel:writeLong	(J)V
        //   42: aload_0
        //   43: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   46: sipush 8012
        //   49: aload 4
        //   51: aload 5
        //   53: iconst_0
        //   54: invokeinterface 41 5 0
        //   59: pop
        //   60: aload 5
        //   62: invokevirtual 44	android/os/Parcel:readException	()V
        //   65: aload 5
        //   67: invokevirtual 47	android/os/Parcel:recycle	()V
        //   70: aload 4
        //   72: invokevirtual 47	android/os/Parcel:recycle	()V
        //   75: return
        //   76: aconst_null
        //   77: astore 7
        //   79: goto -50 -> 29
        //   82: astore 6
        //   84: aload 5
        //   86: invokevirtual 47	android/os/Parcel:recycle	()V
        //   89: aload 4
        //   91: invokevirtual 47	android/os/Parcel:recycle	()V
        //   94: aload 6
        //   96: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	97	0	this	Proxy
        //   0	97	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	97	2	paramLong	long
        //   3	87	4	localParcel1	Parcel
        //   8	77	5	localParcel2	Parcel
        //   82	13	6	localObject	Object
        //   27	51	7	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   10	17	82	finally
        //   21	29	82	finally
        //   29	65	82	finally
      }
      
      /* Error */
      public void b(IGamesCallbacks paramIGamesCallbacks, long paramLong, String paramString)
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
        //   18: ifnull +65 -> 83
        //   21: aload_1
        //   22: invokeinterface 57 1 0
        //   27: astore 8
        //   29: aload 5
        //   31: aload 8
        //   33: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload 5
        //   38: lload_2
        //   39: invokevirtual 118	android/os/Parcel:writeLong	(J)V
        //   42: aload 5
        //   44: aload 4
        //   46: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   49: aload_0
        //   50: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   53: sipush 8020
        //   56: aload 5
        //   58: aload 6
        //   60: iconst_0
        //   61: invokeinterface 41 5 0
        //   66: pop
        //   67: aload 6
        //   69: invokevirtual 44	android/os/Parcel:readException	()V
        //   72: aload 6
        //   74: invokevirtual 47	android/os/Parcel:recycle	()V
        //   77: aload 5
        //   79: invokevirtual 47	android/os/Parcel:recycle	()V
        //   82: return
        //   83: aconst_null
        //   84: astore 8
        //   86: goto -57 -> 29
        //   89: astore 7
        //   91: aload 6
        //   93: invokevirtual 47	android/os/Parcel:recycle	()V
        //   96: aload 5
        //   98: invokevirtual 47	android/os/Parcel:recycle	()V
        //   101: aload 7
        //   103: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	104	0	this	Proxy
        //   0	104	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	104	2	paramLong	long
        //   0	104	4	paramString	String
        //   3	94	5	localParcel1	Parcel
        //   8	84	6	localParcel2	Parcel
        //   89	13	7	localObject	Object
        //   27	58	8	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   10	17	89	finally
        //   21	29	89	finally
        //   29	72	89	finally
      }
      
      /* Error */
      public void b(IGamesCallbacks paramIGamesCallbacks, String paramString)
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
        //   16: ifnull +54 -> 70
        //   19: aload_1
        //   20: invokeinterface 57 1 0
        //   25: astore 6
        //   27: aload_3
        //   28: aload 6
        //   30: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   33: aload_3
        //   34: aload_2
        //   35: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   38: aload_0
        //   39: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   42: sipush 5018
        //   45: aload_3
        //   46: aload 4
        //   48: iconst_0
        //   49: invokeinterface 41 5 0
        //   54: pop
        //   55: aload 4
        //   57: invokevirtual 44	android/os/Parcel:readException	()V
        //   60: aload 4
        //   62: invokevirtual 47	android/os/Parcel:recycle	()V
        //   65: aload_3
        //   66: invokevirtual 47	android/os/Parcel:recycle	()V
        //   69: return
        //   70: aconst_null
        //   71: astore 6
        //   73: goto -46 -> 27
        //   76: astore 5
        //   78: aload 4
        //   80: invokevirtual 47	android/os/Parcel:recycle	()V
        //   83: aload_3
        //   84: invokevirtual 47	android/os/Parcel:recycle	()V
        //   87: aload 5
        //   89: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	90	0	this	Proxy
        //   0	90	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	90	2	paramString	String
        //   3	81	3	localParcel1	Parcel
        //   7	72	4	localParcel2	Parcel
        //   76	12	5	localObject	Object
        //   25	47	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   9	15	76	finally
        //   19	27	76	finally
        //   27	60	76	finally
      }
      
      /* Error */
      public void b(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt)
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
        //   18: ifnull +64 -> 82
        //   21: aload_1
        //   22: invokeinterface 57 1 0
        //   27: astore 7
        //   29: aload 4
        //   31: aload 7
        //   33: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload 4
        //   38: aload_2
        //   39: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   42: aload 4
        //   44: iload_3
        //   45: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   48: aload_0
        //   49: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   52: sipush 12023
        //   55: aload 4
        //   57: aload 5
        //   59: iconst_0
        //   60: invokeinterface 41 5 0
        //   65: pop
        //   66: aload 5
        //   68: invokevirtual 44	android/os/Parcel:readException	()V
        //   71: aload 5
        //   73: invokevirtual 47	android/os/Parcel:recycle	()V
        //   76: aload 4
        //   78: invokevirtual 47	android/os/Parcel:recycle	()V
        //   81: return
        //   82: aconst_null
        //   83: astore 7
        //   85: goto -56 -> 29
        //   88: astore 6
        //   90: aload 5
        //   92: invokevirtual 47	android/os/Parcel:recycle	()V
        //   95: aload 4
        //   97: invokevirtual 47	android/os/Parcel:recycle	()V
        //   100: aload 6
        //   102: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	103	0	this	Proxy
        //   0	103	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	103	2	paramString	String
        //   0	103	3	paramInt	int
        //   3	93	4	localParcel1	Parcel
        //   8	83	5	localParcel2	Parcel
        //   88	13	6	localObject	Object
        //   27	57	7	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   10	17	88	finally
        //   21	29	88	finally
        //   29	71	88	finally
      }
      
      /* Error */
      public void b(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 7
        //   5: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 8
        //   10: aload 7
        //   12: ldc 27
        //   14: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +96 -> 114
        //   21: aload_1
        //   22: invokeinterface 57 1 0
        //   27: astore 10
        //   29: aload 7
        //   31: aload 10
        //   33: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload 7
        //   38: aload_2
        //   39: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   42: aload 7
        //   44: iload_3
        //   45: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   48: aload 7
        //   50: iload 4
        //   52: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   55: aload 7
        //   57: iload 5
        //   59: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   62: iconst_0
        //   63: istore 11
        //   65: iload 6
        //   67: ifeq +6 -> 73
        //   70: iconst_1
        //   71: istore 11
        //   73: aload 7
        //   75: iload 11
        //   77: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   80: aload_0
        //   81: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   84: sipush 5020
        //   87: aload 7
        //   89: aload 8
        //   91: iconst_0
        //   92: invokeinterface 41 5 0
        //   97: pop
        //   98: aload 8
        //   100: invokevirtual 44	android/os/Parcel:readException	()V
        //   103: aload 8
        //   105: invokevirtual 47	android/os/Parcel:recycle	()V
        //   108: aload 7
        //   110: invokevirtual 47	android/os/Parcel:recycle	()V
        //   113: return
        //   114: aconst_null
        //   115: astore 10
        //   117: goto -88 -> 29
        //   120: astore 9
        //   122: aload 8
        //   124: invokevirtual 47	android/os/Parcel:recycle	()V
        //   127: aload 7
        //   129: invokevirtual 47	android/os/Parcel:recycle	()V
        //   132: aload 9
        //   134: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	135	0	this	Proxy
        //   0	135	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	135	2	paramString	String
        //   0	135	3	paramInt1	int
        //   0	135	4	paramInt2	int
        //   0	135	5	paramInt3	int
        //   0	135	6	paramBoolean	boolean
        //   3	125	7	localParcel1	Parcel
        //   8	115	8	localParcel2	Parcel
        //   120	13	9	localObject	Object
        //   27	89	10	localIBinder	IBinder
        //   63	13	11	i	int
        // Exception table:
        //   from	to	target	type
        //   10	17	120	finally
        //   21	29	120	finally
        //   29	62	120	finally
        //   73	103	120	finally
      }
      
      /* Error */
      public void b(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt, IBinder paramIBinder, Bundle paramBundle)
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
        //   18: ifnull +90 -> 108
        //   21: aload_1
        //   22: invokeinterface 57 1 0
        //   27: astore 9
        //   29: aload 6
        //   31: aload 9
        //   33: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload 6
        //   38: aload_2
        //   39: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   42: aload 6
        //   44: iload_3
        //   45: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   48: aload 6
        //   50: aload 4
        //   52: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   55: aload 5
        //   57: ifnull +57 -> 114
        //   60: aload 6
        //   62: iconst_1
        //   63: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   66: aload 5
        //   68: aload 6
        //   70: iconst_0
        //   71: invokevirtual 122	android/os/Bundle:writeToParcel	(Landroid/os/Parcel;I)V
        //   74: aload_0
        //   75: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   78: sipush 7003
        //   81: aload 6
        //   83: aload 7
        //   85: iconst_0
        //   86: invokeinterface 41 5 0
        //   91: pop
        //   92: aload 7
        //   94: invokevirtual 44	android/os/Parcel:readException	()V
        //   97: aload 7
        //   99: invokevirtual 47	android/os/Parcel:recycle	()V
        //   102: aload 6
        //   104: invokevirtual 47	android/os/Parcel:recycle	()V
        //   107: return
        //   108: aconst_null
        //   109: astore 9
        //   111: goto -82 -> 29
        //   114: aload 6
        //   116: iconst_0
        //   117: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   120: goto -46 -> 74
        //   123: astore 8
        //   125: aload 7
        //   127: invokevirtual 47	android/os/Parcel:recycle	()V
        //   130: aload 6
        //   132: invokevirtual 47	android/os/Parcel:recycle	()V
        //   135: aload 8
        //   137: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	138	0	this	Proxy
        //   0	138	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	138	2	paramString	String
        //   0	138	3	paramInt	int
        //   0	138	4	paramIBinder	IBinder
        //   0	138	5	paramBundle	Bundle
        //   3	128	6	localParcel1	Parcel
        //   8	118	7	localParcel2	Parcel
        //   123	13	8	localObject	Object
        //   27	83	9	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   10	17	123	finally
        //   21	29	123	finally
        //   29	55	123	finally
        //   60	74	123	finally
        //   74	97	123	finally
        //   114	120	123	finally
      }
      
      /* Error */
      public void b(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt, boolean paramBoolean)
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
        //   18: ifnull +82 -> 100
        //   21: aload_1
        //   22: invokeinterface 57 1 0
        //   27: astore 8
        //   29: aload 5
        //   31: aload 8
        //   33: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload 5
        //   38: aload_2
        //   39: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   42: aload 5
        //   44: iload_3
        //   45: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   48: iconst_0
        //   49: istore 9
        //   51: iload 4
        //   53: ifeq +6 -> 59
        //   56: iconst_1
        //   57: istore 9
        //   59: aload 5
        //   61: iload 9
        //   63: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   66: aload_0
        //   67: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   70: sipush 10017
        //   73: aload 5
        //   75: aload 6
        //   77: iconst_0
        //   78: invokeinterface 41 5 0
        //   83: pop
        //   84: aload 6
        //   86: invokevirtual 44	android/os/Parcel:readException	()V
        //   89: aload 6
        //   91: invokevirtual 47	android/os/Parcel:recycle	()V
        //   94: aload 5
        //   96: invokevirtual 47	android/os/Parcel:recycle	()V
        //   99: return
        //   100: aconst_null
        //   101: astore 8
        //   103: goto -74 -> 29
        //   106: astore 7
        //   108: aload 6
        //   110: invokevirtual 47	android/os/Parcel:recycle	()V
        //   113: aload 5
        //   115: invokevirtual 47	android/os/Parcel:recycle	()V
        //   118: aload 7
        //   120: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	121	0	this	Proxy
        //   0	121	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	121	2	paramString	String
        //   0	121	3	paramInt	int
        //   0	121	4	paramBoolean	boolean
        //   3	111	5	localParcel1	Parcel
        //   8	101	6	localParcel2	Parcel
        //   106	13	7	localObject	Object
        //   27	75	8	localIBinder	IBinder
        //   49	13	9	i	int
        // Exception table:
        //   from	to	target	type
        //   10	17	106	finally
        //   21	29	106	finally
        //   29	48	106	finally
        //   59	89	106	finally
      }
      
      /* Error */
      public void b(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore 6
        //   3: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 7
        //   8: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 8
        //   13: aload 7
        //   15: ldc 27
        //   17: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload_1
        //   21: ifnull +92 -> 113
        //   24: aload_1
        //   25: invokeinterface 57 1 0
        //   30: astore 10
        //   32: aload 7
        //   34: aload 10
        //   36: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   39: aload 7
        //   41: aload_2
        //   42: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   45: aload 7
        //   47: iload_3
        //   48: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   51: iload 4
        //   53: ifeq +66 -> 119
        //   56: iload 6
        //   58: istore 11
        //   60: aload 7
        //   62: iload 11
        //   64: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   67: iload 5
        //   69: ifeq +56 -> 125
        //   72: aload 7
        //   74: iload 6
        //   76: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   79: aload_0
        //   80: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   83: sipush 5501
        //   86: aload 7
        //   88: aload 8
        //   90: iconst_0
        //   91: invokeinterface 41 5 0
        //   96: pop
        //   97: aload 8
        //   99: invokevirtual 44	android/os/Parcel:readException	()V
        //   102: aload 8
        //   104: invokevirtual 47	android/os/Parcel:recycle	()V
        //   107: aload 7
        //   109: invokevirtual 47	android/os/Parcel:recycle	()V
        //   112: return
        //   113: aconst_null
        //   114: astore 10
        //   116: goto -84 -> 32
        //   119: iconst_0
        //   120: istore 11
        //   122: goto -62 -> 60
        //   125: iconst_0
        //   126: istore 6
        //   128: goto -56 -> 72
        //   131: astore 9
        //   133: aload 8
        //   135: invokevirtual 47	android/os/Parcel:recycle	()V
        //   138: aload 7
        //   140: invokevirtual 47	android/os/Parcel:recycle	()V
        //   143: aload 9
        //   145: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	146	0	this	Proxy
        //   0	146	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	146	2	paramString	String
        //   0	146	3	paramInt	int
        //   0	146	4	paramBoolean1	boolean
        //   0	146	5	paramBoolean2	boolean
        //   1	126	6	i	int
        //   6	133	7	localParcel1	Parcel
        //   11	123	8	localParcel2	Parcel
        //   131	13	9	localObject	Object
        //   30	85	10	localIBinder	IBinder
        //   58	63	11	j	int
        // Exception table:
        //   from	to	target	type
        //   13	20	131	finally
        //   24	32	131	finally
        //   32	51	131	finally
        //   60	67	131	finally
        //   72	102	131	finally
      }
      
      /* Error */
      public void b(IGamesCallbacks paramIGamesCallbacks, String paramString, IBinder paramIBinder, Bundle paramBundle)
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
        //   18: ifnull +83 -> 101
        //   21: aload_1
        //   22: invokeinterface 57 1 0
        //   27: astore 8
        //   29: aload 5
        //   31: aload 8
        //   33: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload 5
        //   38: aload_2
        //   39: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   42: aload 5
        //   44: aload_3
        //   45: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   48: aload 4
        //   50: ifnull +57 -> 107
        //   53: aload 5
        //   55: iconst_1
        //   56: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   59: aload 4
        //   61: aload 5
        //   63: iconst_0
        //   64: invokevirtual 122	android/os/Bundle:writeToParcel	(Landroid/os/Parcel;I)V
        //   67: aload_0
        //   68: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   71: sipush 5024
        //   74: aload 5
        //   76: aload 6
        //   78: iconst_0
        //   79: invokeinterface 41 5 0
        //   84: pop
        //   85: aload 6
        //   87: invokevirtual 44	android/os/Parcel:readException	()V
        //   90: aload 6
        //   92: invokevirtual 47	android/os/Parcel:recycle	()V
        //   95: aload 5
        //   97: invokevirtual 47	android/os/Parcel:recycle	()V
        //   100: return
        //   101: aconst_null
        //   102: astore 8
        //   104: goto -75 -> 29
        //   107: aload 5
        //   109: iconst_0
        //   110: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   113: goto -46 -> 67
        //   116: astore 7
        //   118: aload 6
        //   120: invokevirtual 47	android/os/Parcel:recycle	()V
        //   123: aload 5
        //   125: invokevirtual 47	android/os/Parcel:recycle	()V
        //   128: aload 7
        //   130: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	131	0	this	Proxy
        //   0	131	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	131	2	paramString	String
        //   0	131	3	paramIBinder	IBinder
        //   0	131	4	paramBundle	Bundle
        //   3	121	5	localParcel1	Parcel
        //   8	111	6	localParcel2	Parcel
        //   116	13	7	localObject	Object
        //   27	76	8	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   10	17	116	finally
        //   21	29	116	finally
        //   29	48	116	finally
        //   53	67	116	finally
        //   67	90	116	finally
        //   107	113	116	finally
      }
      
      /* Error */
      public void b(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2)
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
        //   18: ifnull +64 -> 82
        //   21: aload_1
        //   22: invokeinterface 57 1 0
        //   27: astore 7
        //   29: aload 4
        //   31: aload 7
        //   33: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload 4
        //   38: aload_2
        //   39: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   42: aload 4
        //   44: aload_3
        //   45: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   48: aload_0
        //   49: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   52: sipush 5041
        //   55: aload 4
        //   57: aload 5
        //   59: iconst_0
        //   60: invokeinterface 41 5 0
        //   65: pop
        //   66: aload 5
        //   68: invokevirtual 44	android/os/Parcel:readException	()V
        //   71: aload 5
        //   73: invokevirtual 47	android/os/Parcel:recycle	()V
        //   76: aload 4
        //   78: invokevirtual 47	android/os/Parcel:recycle	()V
        //   81: return
        //   82: aconst_null
        //   83: astore 7
        //   85: goto -56 -> 29
        //   88: astore 6
        //   90: aload 5
        //   92: invokevirtual 47	android/os/Parcel:recycle	()V
        //   95: aload 4
        //   97: invokevirtual 47	android/os/Parcel:recycle	()V
        //   100: aload 6
        //   102: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	103	0	this	Proxy
        //   0	103	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	103	2	paramString1	String
        //   0	103	3	paramString2	String
        //   3	93	4	localParcel1	Parcel
        //   8	83	5	localParcel2	Parcel
        //   88	13	6	localObject	Object
        //   27	57	7	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   10	17	88	finally
        //   21	29	88	finally
        //   29	71	88	finally
      }
      
      /* Error */
      public void b(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 8
        //   5: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 9
        //   10: aload 8
        //   12: ldc 27
        //   14: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +103 -> 121
        //   21: aload_1
        //   22: invokeinterface 57 1 0
        //   27: astore 11
        //   29: aload 8
        //   31: aload 11
        //   33: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload 8
        //   38: aload_2
        //   39: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   42: aload 8
        //   44: aload_3
        //   45: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   48: aload 8
        //   50: iload 4
        //   52: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   55: aload 8
        //   57: iload 5
        //   59: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   62: aload 8
        //   64: iload 6
        //   66: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   69: iconst_0
        //   70: istore 12
        //   72: iload 7
        //   74: ifeq +6 -> 80
        //   77: iconst_1
        //   78: istore 12
        //   80: aload 8
        //   82: iload 12
        //   84: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   87: aload_0
        //   88: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   91: sipush 5040
        //   94: aload 8
        //   96: aload 9
        //   98: iconst_0
        //   99: invokeinterface 41 5 0
        //   104: pop
        //   105: aload 9
        //   107: invokevirtual 44	android/os/Parcel:readException	()V
        //   110: aload 9
        //   112: invokevirtual 47	android/os/Parcel:recycle	()V
        //   115: aload 8
        //   117: invokevirtual 47	android/os/Parcel:recycle	()V
        //   120: return
        //   121: aconst_null
        //   122: astore 11
        //   124: goto -95 -> 29
        //   127: astore 10
        //   129: aload 9
        //   131: invokevirtual 47	android/os/Parcel:recycle	()V
        //   134: aload 8
        //   136: invokevirtual 47	android/os/Parcel:recycle	()V
        //   139: aload 10
        //   141: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	142	0	this	Proxy
        //   0	142	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	142	2	paramString1	String
        //   0	142	3	paramString2	String
        //   0	142	4	paramInt1	int
        //   0	142	5	paramInt2	int
        //   0	142	6	paramInt3	int
        //   0	142	7	paramBoolean	boolean
        //   3	132	8	localParcel1	Parcel
        //   8	122	9	localParcel2	Parcel
        //   127	13	10	localObject	Object
        //   27	96	11	localIBinder	IBinder
        //   70	13	12	i	int
        // Exception table:
        //   from	to	target	type
        //   10	17	127	finally
        //   21	29	127	finally
        //   29	69	127	finally
        //   80	110	127	finally
      }
      
      /* Error */
      public void b(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore 7
        //   3: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 8
        //   8: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 9
        //   13: aload 8
        //   15: ldc 27
        //   17: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload_1
        //   21: ifnull +99 -> 120
        //   24: aload_1
        //   25: invokeinterface 57 1 0
        //   30: astore 11
        //   32: aload 8
        //   34: aload 11
        //   36: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   39: aload 8
        //   41: aload_2
        //   42: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   45: aload 8
        //   47: aload_3
        //   48: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   51: aload 8
        //   53: iload 4
        //   55: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   58: iload 5
        //   60: ifeq +66 -> 126
        //   63: iload 7
        //   65: istore 12
        //   67: aload 8
        //   69: iload 12
        //   71: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   74: iload 6
        //   76: ifeq +56 -> 132
        //   79: aload 8
        //   81: iload 7
        //   83: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   86: aload_0
        //   87: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   90: sipush 12018
        //   93: aload 8
        //   95: aload 9
        //   97: iconst_0
        //   98: invokeinterface 41 5 0
        //   103: pop
        //   104: aload 9
        //   106: invokevirtual 44	android/os/Parcel:readException	()V
        //   109: aload 9
        //   111: invokevirtual 47	android/os/Parcel:recycle	()V
        //   114: aload 8
        //   116: invokevirtual 47	android/os/Parcel:recycle	()V
        //   119: return
        //   120: aconst_null
        //   121: astore 11
        //   123: goto -91 -> 32
        //   126: iconst_0
        //   127: istore 12
        //   129: goto -62 -> 67
        //   132: iconst_0
        //   133: istore 7
        //   135: goto -56 -> 79
        //   138: astore 10
        //   140: aload 9
        //   142: invokevirtual 47	android/os/Parcel:recycle	()V
        //   145: aload 8
        //   147: invokevirtual 47	android/os/Parcel:recycle	()V
        //   150: aload 10
        //   152: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	153	0	this	Proxy
        //   0	153	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	153	2	paramString1	String
        //   0	153	3	paramString2	String
        //   0	153	4	paramInt	int
        //   0	153	5	paramBoolean1	boolean
        //   0	153	6	paramBoolean2	boolean
        //   1	133	7	i	int
        //   6	140	8	localParcel1	Parcel
        //   11	130	9	localParcel2	Parcel
        //   138	13	10	localObject	Object
        //   30	92	11	localIBinder	IBinder
        //   65	63	12	j	int
        // Exception table:
        //   from	to	target	type
        //   13	20	138	finally
        //   24	32	138	finally
        //   32	58	138	finally
        //   67	74	138	finally
        //   79	109	138	finally
      }
      
      /* Error */
      public void b(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2, boolean paramBoolean)
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
        //   18: ifnull +82 -> 100
        //   21: aload_1
        //   22: invokeinterface 57 1 0
        //   27: astore 8
        //   29: aload 5
        //   31: aload 8
        //   33: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload 5
        //   38: aload_2
        //   39: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   42: aload 5
        //   44: aload_3
        //   45: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   48: iconst_0
        //   49: istore 9
        //   51: iload 4
        //   53: ifeq +6 -> 59
        //   56: iconst_1
        //   57: istore 9
        //   59: aload 5
        //   61: iload 9
        //   63: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   66: aload_0
        //   67: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   70: sipush 6506
        //   73: aload 5
        //   75: aload 6
        //   77: iconst_0
        //   78: invokeinterface 41 5 0
        //   83: pop
        //   84: aload 6
        //   86: invokevirtual 44	android/os/Parcel:readException	()V
        //   89: aload 6
        //   91: invokevirtual 47	android/os/Parcel:recycle	()V
        //   94: aload 5
        //   96: invokevirtual 47	android/os/Parcel:recycle	()V
        //   99: return
        //   100: aconst_null
        //   101: astore 8
        //   103: goto -74 -> 29
        //   106: astore 7
        //   108: aload 6
        //   110: invokevirtual 47	android/os/Parcel:recycle	()V
        //   113: aload 5
        //   115: invokevirtual 47	android/os/Parcel:recycle	()V
        //   118: aload 7
        //   120: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	121	0	this	Proxy
        //   0	121	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	121	2	paramString1	String
        //   0	121	3	paramString2	String
        //   0	121	4	paramBoolean	boolean
        //   3	111	5	localParcel1	Parcel
        //   8	101	6	localParcel2	Parcel
        //   106	13	7	localObject	Object
        //   27	75	8	localIBinder	IBinder
        //   49	13	9	i	int
        // Exception table:
        //   from	to	target	type
        //   10	17	106	finally
        //   21	29	106	finally
        //   29	48	106	finally
        //   59	89	106	finally
      }
      
      /* Error */
      public void b(IGamesCallbacks paramIGamesCallbacks, String paramString, boolean paramBoolean)
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
        //   18: ifnull +75 -> 93
        //   21: aload_1
        //   22: invokeinterface 57 1 0
        //   27: astore 7
        //   29: aload 4
        //   31: aload 7
        //   33: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload 4
        //   38: aload_2
        //   39: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   42: iconst_0
        //   43: istore 8
        //   45: iload_3
        //   46: ifeq +6 -> 52
        //   49: iconst_1
        //   50: istore 8
        //   52: aload 4
        //   54: iload 8
        //   56: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   59: aload_0
        //   60: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   63: sipush 6502
        //   66: aload 4
        //   68: aload 5
        //   70: iconst_0
        //   71: invokeinterface 41 5 0
        //   76: pop
        //   77: aload 5
        //   79: invokevirtual 44	android/os/Parcel:readException	()V
        //   82: aload 5
        //   84: invokevirtual 47	android/os/Parcel:recycle	()V
        //   87: aload 4
        //   89: invokevirtual 47	android/os/Parcel:recycle	()V
        //   92: return
        //   93: aconst_null
        //   94: astore 7
        //   96: goto -67 -> 29
        //   99: astore 6
        //   101: aload 5
        //   103: invokevirtual 47	android/os/Parcel:recycle	()V
        //   106: aload 4
        //   108: invokevirtual 47	android/os/Parcel:recycle	()V
        //   111: aload 6
        //   113: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	114	0	this	Proxy
        //   0	114	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	114	2	paramString	String
        //   0	114	3	paramBoolean	boolean
        //   3	104	4	localParcel1	Parcel
        //   8	94	5	localParcel2	Parcel
        //   99	13	6	localObject	Object
        //   27	68	7	localIBinder	IBinder
        //   43	12	8	i	int
        // Exception table:
        //   from	to	target	type
        //   10	17	99	finally
        //   21	29	99	finally
        //   29	42	99	finally
        //   52	82	99	finally
      }
      
      /* Error */
      public void b(IGamesCallbacks paramIGamesCallbacks, boolean paramBoolean)
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
        //   16: ifnull +65 -> 81
        //   19: aload_1
        //   20: invokeinterface 57 1 0
        //   25: astore 6
        //   27: aload_3
        //   28: aload 6
        //   30: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
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
        //   50: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   53: sipush 6503
        //   56: aload_3
        //   57: aload 4
        //   59: iconst_0
        //   60: invokeinterface 41 5 0
        //   65: pop
        //   66: aload 4
        //   68: invokevirtual 44	android/os/Parcel:readException	()V
        //   71: aload 4
        //   73: invokevirtual 47	android/os/Parcel:recycle	()V
        //   76: aload_3
        //   77: invokevirtual 47	android/os/Parcel:recycle	()V
        //   80: return
        //   81: aconst_null
        //   82: astore 6
        //   84: goto -57 -> 27
        //   87: astore 5
        //   89: aload 4
        //   91: invokevirtual 47	android/os/Parcel:recycle	()V
        //   94: aload_3
        //   95: invokevirtual 47	android/os/Parcel:recycle	()V
        //   98: aload 5
        //   100: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	101	0	this	Proxy
        //   0	101	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	101	2	paramBoolean	boolean
        //   3	92	3	localParcel1	Parcel
        //   7	83	4	localParcel2	Parcel
        //   87	12	5	localObject	Object
        //   25	58	6	localIBinder	IBinder
        //   34	11	7	i	int
        // Exception table:
        //   from	to	target	type
        //   9	15	87	finally
        //   19	27	87	finally
        //   27	33	87	finally
        //   43	71	87	finally
      }
      
      /* Error */
      public void b(IGamesCallbacks paramIGamesCallbacks, String[] paramArrayOfString)
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
        //   16: ifnull +54 -> 70
        //   19: aload_1
        //   20: invokeinterface 57 1 0
        //   25: astore 6
        //   27: aload_3
        //   28: aload 6
        //   30: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   33: aload_3
        //   34: aload_2
        //   35: invokevirtual 135	android/os/Parcel:writeStringArray	([Ljava/lang/String;)V
        //   38: aload_0
        //   39: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   42: sipush 10007
        //   45: aload_3
        //   46: aload 4
        //   48: iconst_0
        //   49: invokeinterface 41 5 0
        //   54: pop
        //   55: aload 4
        //   57: invokevirtual 44	android/os/Parcel:readException	()V
        //   60: aload 4
        //   62: invokevirtual 47	android/os/Parcel:recycle	()V
        //   65: aload_3
        //   66: invokevirtual 47	android/os/Parcel:recycle	()V
        //   69: return
        //   70: aconst_null
        //   71: astore 6
        //   73: goto -46 -> 27
        //   76: astore 5
        //   78: aload 4
        //   80: invokevirtual 47	android/os/Parcel:recycle	()V
        //   83: aload_3
        //   84: invokevirtual 47	android/os/Parcel:recycle	()V
        //   87: aload 5
        //   89: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	90	0	this	Proxy
        //   0	90	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	90	2	paramArrayOfString	String[]
        //   3	81	3	localParcel1	Parcel
        //   7	72	4	localParcel2	Parcel
        //   76	12	5	localObject	Object
        //   25	47	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   9	15	76	finally
        //   19	27	76	finally
        //   27	60	76	finally
      }
      
      public void b(String paramString1, String paramString2, int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeString(paramString1);
          localParcel1.writeString(paramString2);
          localParcel1.writeInt(paramInt);
          this.lb.transact(5051, localParcel1, localParcel2, 0);
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
      public Intent bC(String paramString)
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
        //   14: aload_2
        //   15: aload_1
        //   16: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   19: aload_0
        //   20: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   23: sipush 12034
        //   26: aload_2
        //   27: aload_3
        //   28: iconst_0
        //   29: invokeinterface 41 5 0
        //   34: pop
        //   35: aload_3
        //   36: invokevirtual 44	android/os/Parcel:readException	()V
        //   39: aload_3
        //   40: invokevirtual 71	android/os/Parcel:readInt	()I
        //   43: ifeq +28 -> 71
        //   46: getstatic 78	android/content/Intent:CREATOR	Landroid/os/Parcelable$Creator;
        //   49: aload_3
        //   50: invokeinterface 84 2 0
        //   55: checkcast 74	android/content/Intent
        //   58: astore 6
        //   60: aload_3
        //   61: invokevirtual 47	android/os/Parcel:recycle	()V
        //   64: aload_2
        //   65: invokevirtual 47	android/os/Parcel:recycle	()V
        //   68: aload 6
        //   70: areturn
        //   71: aconst_null
        //   72: astore 6
        //   74: goto -14 -> 60
        //   77: astore 4
        //   79: aload_3
        //   80: invokevirtual 47	android/os/Parcel:recycle	()V
        //   83: aload_2
        //   84: invokevirtual 47	android/os/Parcel:recycle	()V
        //   87: aload 4
        //   89: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	90	0	this	Proxy
        //   0	90	1	paramString	String
        //   3	81	2	localParcel1	Parcel
        //   7	73	3	localParcel2	Parcel
        //   77	11	4	localObject	Object
        //   58	15	6	localIntent	Intent
        // Exception table:
        //   from	to	target	type
        //   8	60	77	finally
      }
      
      public String bE(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeString(paramString);
          this.lb.transact(5064, localParcel1, localParcel2, 0);
          localParcel2.readException();
          String str = localParcel2.readString();
          return str;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public String bF(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeString(paramString);
          this.lb.transact(5035, localParcel1, localParcel2, 0);
          localParcel2.readException();
          String str = localParcel2.readString();
          return str;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void bG(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeString(paramString);
          this.lb.transact(5050, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public int bH(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeString(paramString);
          this.lb.transact(5060, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      /* Error */
      public Uri bI(String paramString)
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
        //   14: aload_2
        //   15: aload_1
        //   16: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   19: aload_0
        //   20: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   23: sipush 5066
        //   26: aload_2
        //   27: aload_3
        //   28: iconst_0
        //   29: invokeinterface 41 5 0
        //   34: pop
        //   35: aload_3
        //   36: invokevirtual 44	android/os/Parcel:readException	()V
        //   39: aload_3
        //   40: invokevirtual 71	android/os/Parcel:readInt	()I
        //   43: ifeq +28 -> 71
        //   46: getstatic 203	android/net/Uri:CREATOR	Landroid/os/Parcelable$Creator;
        //   49: aload_3
        //   50: invokeinterface 84 2 0
        //   55: checkcast 112	android/net/Uri
        //   58: astore 6
        //   60: aload_3
        //   61: invokevirtual 47	android/os/Parcel:recycle	()V
        //   64: aload_2
        //   65: invokevirtual 47	android/os/Parcel:recycle	()V
        //   68: aload 6
        //   70: areturn
        //   71: aconst_null
        //   72: astore 6
        //   74: goto -14 -> 60
        //   77: astore 4
        //   79: aload_3
        //   80: invokevirtual 47	android/os/Parcel:recycle	()V
        //   83: aload_2
        //   84: invokevirtual 47	android/os/Parcel:recycle	()V
        //   87: aload 4
        //   89: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	90	0	this	Proxy
        //   0	90	1	paramString	String
        //   3	81	2	localParcel1	Parcel
        //   7	73	3	localParcel2	Parcel
        //   77	11	4	localObject	Object
        //   58	15	6	localUri	Uri
        // Exception table:
        //   from	to	target	type
        //   8	60	77	finally
      }
      
      public void bJ(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeString(paramString);
          this.lb.transact(8002, localParcel1, localParcel2, 0);
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
      public ParcelFileDescriptor bK(String paramString)
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
        //   14: aload_2
        //   15: aload_1
        //   16: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   19: aload_0
        //   20: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   23: sipush 9030
        //   26: aload_2
        //   27: aload_3
        //   28: iconst_0
        //   29: invokeinterface 41 5 0
        //   34: pop
        //   35: aload_3
        //   36: invokevirtual 44	android/os/Parcel:readException	()V
        //   39: aload_3
        //   40: invokevirtual 71	android/os/Parcel:readInt	()I
        //   43: ifeq +28 -> 71
        //   46: getstatic 209	android/os/ParcelFileDescriptor:CREATOR	Landroid/os/Parcelable$Creator;
        //   49: aload_3
        //   50: invokeinterface 84 2 0
        //   55: checkcast 208	android/os/ParcelFileDescriptor
        //   58: astore 6
        //   60: aload_3
        //   61: invokevirtual 47	android/os/Parcel:recycle	()V
        //   64: aload_2
        //   65: invokevirtual 47	android/os/Parcel:recycle	()V
        //   68: aload 6
        //   70: areturn
        //   71: aconst_null
        //   72: astore 6
        //   74: goto -14 -> 60
        //   77: astore 4
        //   79: aload_3
        //   80: invokevirtual 47	android/os/Parcel:recycle	()V
        //   83: aload_2
        //   84: invokevirtual 47	android/os/Parcel:recycle	()V
        //   87: aload 4
        //   89: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	90	0	this	Proxy
        //   0	90	1	paramString	String
        //   3	81	2	localParcel1	Parcel
        //   7	73	3	localParcel2	Parcel
        //   77	11	4	localObject	Object
        //   58	15	6	localParcelFileDescriptor	ParcelFileDescriptor
        // Exception table:
        //   from	to	target	type
        //   8	60	77	finally
      }
      
      /* Error */
      public Intent bx(String paramString)
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
        //   14: aload_2
        //   15: aload_1
        //   16: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   19: aload_0
        //   20: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   23: sipush 9004
        //   26: aload_2
        //   27: aload_3
        //   28: iconst_0
        //   29: invokeinterface 41 5 0
        //   34: pop
        //   35: aload_3
        //   36: invokevirtual 44	android/os/Parcel:readException	()V
        //   39: aload_3
        //   40: invokevirtual 71	android/os/Parcel:readInt	()I
        //   43: ifeq +28 -> 71
        //   46: getstatic 78	android/content/Intent:CREATOR	Landroid/os/Parcelable$Creator;
        //   49: aload_3
        //   50: invokeinterface 84 2 0
        //   55: checkcast 74	android/content/Intent
        //   58: astore 6
        //   60: aload_3
        //   61: invokevirtual 47	android/os/Parcel:recycle	()V
        //   64: aload_2
        //   65: invokevirtual 47	android/os/Parcel:recycle	()V
        //   68: aload 6
        //   70: areturn
        //   71: aconst_null
        //   72: astore 6
        //   74: goto -14 -> 60
        //   77: astore 4
        //   79: aload_3
        //   80: invokevirtual 47	android/os/Parcel:recycle	()V
        //   83: aload_2
        //   84: invokevirtual 47	android/os/Parcel:recycle	()V
        //   87: aload 4
        //   89: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	90	0	this	Proxy
        //   0	90	1	paramString	String
        //   3	81	2	localParcel1	Parcel
        //   7	73	3	localParcel2	Parcel
        //   77	11	4	localObject	Object
        //   58	15	6	localIntent	Intent
        // Exception table:
        //   from	to	target	type
        //   8	60	77	finally
      }
      
      public void c(long paramLong, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeLong(paramLong);
          localParcel1.writeString(paramString);
          this.lb.transact(10004, localParcel1, localParcel2, 0);
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
      public void c(IGamesCallbacks paramIGamesCallbacks)
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
        //   15: ifnull +46 -> 61
        //   18: aload_1
        //   19: invokeinterface 57 1 0
        //   24: astore 5
        //   26: aload_2
        //   27: aload 5
        //   29: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   32: aload_0
        //   33: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   36: sipush 5022
        //   39: aload_2
        //   40: aload_3
        //   41: iconst_0
        //   42: invokeinterface 41 5 0
        //   47: pop
        //   48: aload_3
        //   49: invokevirtual 44	android/os/Parcel:readException	()V
        //   52: aload_3
        //   53: invokevirtual 47	android/os/Parcel:recycle	()V
        //   56: aload_2
        //   57: invokevirtual 47	android/os/Parcel:recycle	()V
        //   60: return
        //   61: aconst_null
        //   62: astore 5
        //   64: goto -38 -> 26
        //   67: astore 4
        //   69: aload_3
        //   70: invokevirtual 47	android/os/Parcel:recycle	()V
        //   73: aload_2
        //   74: invokevirtual 47	android/os/Parcel:recycle	()V
        //   77: aload 4
        //   79: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	80	0	this	Proxy
        //   0	80	1	paramIGamesCallbacks	IGamesCallbacks
        //   3	71	2	localParcel1	Parcel
        //   7	63	3	localParcel2	Parcel
        //   67	11	4	localObject	Object
        //   24	39	5	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   8	14	67	finally
        //   18	26	67	finally
        //   26	52	67	finally
      }
      
      /* Error */
      public void c(IGamesCallbacks paramIGamesCallbacks, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore 5
        //   3: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 6
        //   8: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 7
        //   13: aload 6
        //   15: ldc 27
        //   17: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload_1
        //   21: ifnull +85 -> 106
        //   24: aload_1
        //   25: invokeinterface 57 1 0
        //   30: astore 9
        //   32: aload 6
        //   34: aload 9
        //   36: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   39: aload 6
        //   41: iload_2
        //   42: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   45: iload_3
        //   46: ifeq +66 -> 112
        //   49: iload 5
        //   51: istore 10
        //   53: aload 6
        //   55: iload 10
        //   57: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   60: iload 4
        //   62: ifeq +56 -> 118
        //   65: aload 6
        //   67: iload 5
        //   69: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   72: aload_0
        //   73: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   76: sipush 5048
        //   79: aload 6
        //   81: aload 7
        //   83: iconst_0
        //   84: invokeinterface 41 5 0
        //   89: pop
        //   90: aload 7
        //   92: invokevirtual 44	android/os/Parcel:readException	()V
        //   95: aload 7
        //   97: invokevirtual 47	android/os/Parcel:recycle	()V
        //   100: aload 6
        //   102: invokevirtual 47	android/os/Parcel:recycle	()V
        //   105: return
        //   106: aconst_null
        //   107: astore 9
        //   109: goto -77 -> 32
        //   112: iconst_0
        //   113: istore 10
        //   115: goto -62 -> 53
        //   118: iconst_0
        //   119: istore 5
        //   121: goto -56 -> 65
        //   124: astore 8
        //   126: aload 7
        //   128: invokevirtual 47	android/os/Parcel:recycle	()V
        //   131: aload 6
        //   133: invokevirtual 47	android/os/Parcel:recycle	()V
        //   136: aload 8
        //   138: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	139	0	this	Proxy
        //   0	139	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	139	2	paramInt	int
        //   0	139	3	paramBoolean1	boolean
        //   0	139	4	paramBoolean2	boolean
        //   1	119	5	i	int
        //   6	126	6	localParcel1	Parcel
        //   11	116	7	localParcel2	Parcel
        //   124	13	8	localObject	Object
        //   30	78	9	localIBinder	IBinder
        //   51	63	10	j	int
        // Exception table:
        //   from	to	target	type
        //   13	20	124	finally
        //   24	32	124	finally
        //   32	45	124	finally
        //   53	60	124	finally
        //   65	95	124	finally
      }
      
      /* Error */
      public void c(IGamesCallbacks paramIGamesCallbacks, long paramLong)
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
        //   18: ifnull +58 -> 76
        //   21: aload_1
        //   22: invokeinterface 57 1 0
        //   27: astore 7
        //   29: aload 4
        //   31: aload 7
        //   33: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload 4
        //   38: lload_2
        //   39: invokevirtual 118	android/os/Parcel:writeLong	(J)V
        //   42: aload_0
        //   43: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   46: sipush 10001
        //   49: aload 4
        //   51: aload 5
        //   53: iconst_0
        //   54: invokeinterface 41 5 0
        //   59: pop
        //   60: aload 5
        //   62: invokevirtual 44	android/os/Parcel:readException	()V
        //   65: aload 5
        //   67: invokevirtual 47	android/os/Parcel:recycle	()V
        //   70: aload 4
        //   72: invokevirtual 47	android/os/Parcel:recycle	()V
        //   75: return
        //   76: aconst_null
        //   77: astore 7
        //   79: goto -50 -> 29
        //   82: astore 6
        //   84: aload 5
        //   86: invokevirtual 47	android/os/Parcel:recycle	()V
        //   89: aload 4
        //   91: invokevirtual 47	android/os/Parcel:recycle	()V
        //   94: aload 6
        //   96: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	97	0	this	Proxy
        //   0	97	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	97	2	paramLong	long
        //   3	87	4	localParcel1	Parcel
        //   8	77	5	localParcel2	Parcel
        //   82	13	6	localObject	Object
        //   27	51	7	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   10	17	82	finally
        //   21	29	82	finally
        //   29	65	82	finally
      }
      
      /* Error */
      public void c(IGamesCallbacks paramIGamesCallbacks, long paramLong, String paramString)
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
        //   18: ifnull +65 -> 83
        //   21: aload_1
        //   22: invokeinterface 57 1 0
        //   27: astore 8
        //   29: aload 5
        //   31: aload 8
        //   33: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload 5
        //   38: lload_2
        //   39: invokevirtual 118	android/os/Parcel:writeLong	(J)V
        //   42: aload 5
        //   44: aload 4
        //   46: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   49: aload_0
        //   50: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   53: sipush 10003
        //   56: aload 5
        //   58: aload 6
        //   60: iconst_0
        //   61: invokeinterface 41 5 0
        //   66: pop
        //   67: aload 6
        //   69: invokevirtual 44	android/os/Parcel:readException	()V
        //   72: aload 6
        //   74: invokevirtual 47	android/os/Parcel:recycle	()V
        //   77: aload 5
        //   79: invokevirtual 47	android/os/Parcel:recycle	()V
        //   82: return
        //   83: aconst_null
        //   84: astore 8
        //   86: goto -57 -> 29
        //   89: astore 7
        //   91: aload 6
        //   93: invokevirtual 47	android/os/Parcel:recycle	()V
        //   96: aload 5
        //   98: invokevirtual 47	android/os/Parcel:recycle	()V
        //   101: aload 7
        //   103: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	104	0	this	Proxy
        //   0	104	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	104	2	paramLong	long
        //   0	104	4	paramString	String
        //   3	94	5	localParcel1	Parcel
        //   8	84	6	localParcel2	Parcel
        //   89	13	7	localObject	Object
        //   27	58	8	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   10	17	89	finally
        //   21	29	89	finally
        //   29	72	89	finally
      }
      
      /* Error */
      public void c(IGamesCallbacks paramIGamesCallbacks, String paramString)
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
        //   16: ifnull +54 -> 70
        //   19: aload_1
        //   20: invokeinterface 57 1 0
        //   25: astore 6
        //   27: aload_3
        //   28: aload 6
        //   30: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   33: aload_3
        //   34: aload_2
        //   35: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   38: aload_0
        //   39: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   42: sipush 5032
        //   45: aload_3
        //   46: aload 4
        //   48: iconst_0
        //   49: invokeinterface 41 5 0
        //   54: pop
        //   55: aload 4
        //   57: invokevirtual 44	android/os/Parcel:readException	()V
        //   60: aload 4
        //   62: invokevirtual 47	android/os/Parcel:recycle	()V
        //   65: aload_3
        //   66: invokevirtual 47	android/os/Parcel:recycle	()V
        //   69: return
        //   70: aconst_null
        //   71: astore 6
        //   73: goto -46 -> 27
        //   76: astore 5
        //   78: aload 4
        //   80: invokevirtual 47	android/os/Parcel:recycle	()V
        //   83: aload_3
        //   84: invokevirtual 47	android/os/Parcel:recycle	()V
        //   87: aload 5
        //   89: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	90	0	this	Proxy
        //   0	90	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	90	2	paramString	String
        //   3	81	3	localParcel1	Parcel
        //   7	72	4	localParcel2	Parcel
        //   76	12	5	localObject	Object
        //   25	47	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   9	15	76	finally
        //   19	27	76	finally
        //   27	60	76	finally
      }
      
      /* Error */
      public void c(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt)
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
        //   18: ifnull +64 -> 82
        //   21: aload_1
        //   22: invokeinterface 57 1 0
        //   27: astore 7
        //   29: aload 4
        //   31: aload 7
        //   33: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload 4
        //   38: aload_2
        //   39: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   42: aload 4
        //   44: iload_3
        //   45: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   48: aload_0
        //   49: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   52: sipush 12024
        //   55: aload 4
        //   57: aload 5
        //   59: iconst_0
        //   60: invokeinterface 41 5 0
        //   65: pop
        //   66: aload 5
        //   68: invokevirtual 44	android/os/Parcel:readException	()V
        //   71: aload 5
        //   73: invokevirtual 47	android/os/Parcel:recycle	()V
        //   76: aload 4
        //   78: invokevirtual 47	android/os/Parcel:recycle	()V
        //   81: return
        //   82: aconst_null
        //   83: astore 7
        //   85: goto -56 -> 29
        //   88: astore 6
        //   90: aload 5
        //   92: invokevirtual 47	android/os/Parcel:recycle	()V
        //   95: aload 4
        //   97: invokevirtual 47	android/os/Parcel:recycle	()V
        //   100: aload 6
        //   102: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	103	0	this	Proxy
        //   0	103	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	103	2	paramString	String
        //   0	103	3	paramInt	int
        //   3	93	4	localParcel1	Parcel
        //   8	83	5	localParcel2	Parcel
        //   88	13	6	localObject	Object
        //   27	57	7	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   10	17	88	finally
        //   21	29	88	finally
        //   29	71	88	finally
      }
      
      /* Error */
      public void c(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore 6
        //   3: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 7
        //   8: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 8
        //   13: aload 7
        //   15: ldc 27
        //   17: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload_1
        //   21: ifnull +92 -> 113
        //   24: aload_1
        //   25: invokeinterface 57 1 0
        //   30: astore 10
        //   32: aload 7
        //   34: aload 10
        //   36: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   39: aload 7
        //   41: aload_2
        //   42: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   45: aload 7
        //   47: iload_3
        //   48: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   51: iload 4
        //   53: ifeq +66 -> 119
        //   56: iload 6
        //   58: istore 11
        //   60: aload 7
        //   62: iload 11
        //   64: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   67: iload 5
        //   69: ifeq +56 -> 125
        //   72: aload 7
        //   74: iload 6
        //   76: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   79: aload_0
        //   80: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   83: sipush 9001
        //   86: aload 7
        //   88: aload 8
        //   90: iconst_0
        //   91: invokeinterface 41 5 0
        //   96: pop
        //   97: aload 8
        //   99: invokevirtual 44	android/os/Parcel:readException	()V
        //   102: aload 8
        //   104: invokevirtual 47	android/os/Parcel:recycle	()V
        //   107: aload 7
        //   109: invokevirtual 47	android/os/Parcel:recycle	()V
        //   112: return
        //   113: aconst_null
        //   114: astore 10
        //   116: goto -84 -> 32
        //   119: iconst_0
        //   120: istore 11
        //   122: goto -62 -> 60
        //   125: iconst_0
        //   126: istore 6
        //   128: goto -56 -> 72
        //   131: astore 9
        //   133: aload 8
        //   135: invokevirtual 47	android/os/Parcel:recycle	()V
        //   138: aload 7
        //   140: invokevirtual 47	android/os/Parcel:recycle	()V
        //   143: aload 9
        //   145: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	146	0	this	Proxy
        //   0	146	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	146	2	paramString	String
        //   0	146	3	paramInt	int
        //   0	146	4	paramBoolean1	boolean
        //   0	146	5	paramBoolean2	boolean
        //   1	126	6	i	int
        //   6	133	7	localParcel1	Parcel
        //   11	123	8	localParcel2	Parcel
        //   131	13	9	localObject	Object
        //   30	85	10	localIBinder	IBinder
        //   58	63	11	j	int
        // Exception table:
        //   from	to	target	type
        //   13	20	131	finally
        //   24	32	131	finally
        //   32	51	131	finally
        //   60	67	131	finally
        //   72	102	131	finally
      }
      
      /* Error */
      public void c(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2)
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
        //   18: ifnull +64 -> 82
        //   21: aload_1
        //   22: invokeinterface 57 1 0
        //   27: astore 7
        //   29: aload 4
        //   31: aload 7
        //   33: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload 4
        //   38: aload_2
        //   39: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   42: aload 4
        //   44: aload_3
        //   45: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   48: aload_0
        //   49: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   52: sipush 8011
        //   55: aload 4
        //   57: aload 5
        //   59: iconst_0
        //   60: invokeinterface 41 5 0
        //   65: pop
        //   66: aload 5
        //   68: invokevirtual 44	android/os/Parcel:readException	()V
        //   71: aload 5
        //   73: invokevirtual 47	android/os/Parcel:recycle	()V
        //   76: aload 4
        //   78: invokevirtual 47	android/os/Parcel:recycle	()V
        //   81: return
        //   82: aconst_null
        //   83: astore 7
        //   85: goto -56 -> 29
        //   88: astore 6
        //   90: aload 5
        //   92: invokevirtual 47	android/os/Parcel:recycle	()V
        //   95: aload 4
        //   97: invokevirtual 47	android/os/Parcel:recycle	()V
        //   100: aload 6
        //   102: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	103	0	this	Proxy
        //   0	103	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	103	2	paramString1	String
        //   0	103	3	paramString2	String
        //   3	93	4	localParcel1	Parcel
        //   8	83	5	localParcel2	Parcel
        //   88	13	6	localObject	Object
        //   27	57	7	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   10	17	88	finally
        //   21	29	88	finally
        //   29	71	88	finally
      }
      
      /* Error */
      public void c(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2, boolean paramBoolean)
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
        //   18: ifnull +82 -> 100
        //   21: aload_1
        //   22: invokeinterface 57 1 0
        //   27: astore 8
        //   29: aload 5
        //   31: aload 8
        //   33: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload 5
        //   38: aload_2
        //   39: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   42: aload 5
        //   44: aload_3
        //   45: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   48: iconst_0
        //   49: istore 9
        //   51: iload 4
        //   53: ifeq +6 -> 59
        //   56: iconst_1
        //   57: istore 9
        //   59: aload 5
        //   61: iload 9
        //   63: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   66: aload_0
        //   67: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   70: sipush 12003
        //   73: aload 5
        //   75: aload 6
        //   77: iconst_0
        //   78: invokeinterface 41 5 0
        //   83: pop
        //   84: aload 6
        //   86: invokevirtual 44	android/os/Parcel:readException	()V
        //   89: aload 6
        //   91: invokevirtual 47	android/os/Parcel:recycle	()V
        //   94: aload 5
        //   96: invokevirtual 47	android/os/Parcel:recycle	()V
        //   99: return
        //   100: aconst_null
        //   101: astore 8
        //   103: goto -74 -> 29
        //   106: astore 7
        //   108: aload 6
        //   110: invokevirtual 47	android/os/Parcel:recycle	()V
        //   113: aload 5
        //   115: invokevirtual 47	android/os/Parcel:recycle	()V
        //   118: aload 7
        //   120: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	121	0	this	Proxy
        //   0	121	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	121	2	paramString1	String
        //   0	121	3	paramString2	String
        //   0	121	4	paramBoolean	boolean
        //   3	111	5	localParcel1	Parcel
        //   8	101	6	localParcel2	Parcel
        //   106	13	7	localObject	Object
        //   27	75	8	localIBinder	IBinder
        //   49	13	9	i	int
        // Exception table:
        //   from	to	target	type
        //   10	17	106	finally
        //   21	29	106	finally
        //   29	48	106	finally
        //   59	89	106	finally
      }
      
      /* Error */
      public void c(IGamesCallbacks paramIGamesCallbacks, String paramString, boolean paramBoolean)
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
        //   18: ifnull +75 -> 93
        //   21: aload_1
        //   22: invokeinterface 57 1 0
        //   27: astore 7
        //   29: aload 4
        //   31: aload 7
        //   33: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload 4
        //   38: aload_2
        //   39: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   42: iconst_0
        //   43: istore 8
        //   45: iload_3
        //   46: ifeq +6 -> 52
        //   49: iconst_1
        //   50: istore 8
        //   52: aload 4
        //   54: iload 8
        //   56: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   59: aload_0
        //   60: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   63: sipush 6504
        //   66: aload 4
        //   68: aload 5
        //   70: iconst_0
        //   71: invokeinterface 41 5 0
        //   76: pop
        //   77: aload 5
        //   79: invokevirtual 44	android/os/Parcel:readException	()V
        //   82: aload 5
        //   84: invokevirtual 47	android/os/Parcel:recycle	()V
        //   87: aload 4
        //   89: invokevirtual 47	android/os/Parcel:recycle	()V
        //   92: return
        //   93: aconst_null
        //   94: astore 7
        //   96: goto -67 -> 29
        //   99: astore 6
        //   101: aload 5
        //   103: invokevirtual 47	android/os/Parcel:recycle	()V
        //   106: aload 4
        //   108: invokevirtual 47	android/os/Parcel:recycle	()V
        //   111: aload 6
        //   113: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	114	0	this	Proxy
        //   0	114	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	114	2	paramString	String
        //   0	114	3	paramBoolean	boolean
        //   3	104	4	localParcel1	Parcel
        //   8	94	5	localParcel2	Parcel
        //   99	13	6	localObject	Object
        //   27	68	7	localIBinder	IBinder
        //   43	12	8	i	int
        // Exception table:
        //   from	to	target	type
        //   10	17	99	finally
        //   21	29	99	finally
        //   29	42	99	finally
        //   52	82	99	finally
      }
      
      /* Error */
      public void c(IGamesCallbacks paramIGamesCallbacks, boolean paramBoolean)
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
        //   16: ifnull +65 -> 81
        //   19: aload_1
        //   20: invokeinterface 57 1 0
        //   25: astore 6
        //   27: aload_3
        //   28: aload 6
        //   30: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
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
        //   50: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   53: sipush 8027
        //   56: aload_3
        //   57: aload 4
        //   59: iconst_0
        //   60: invokeinterface 41 5 0
        //   65: pop
        //   66: aload 4
        //   68: invokevirtual 44	android/os/Parcel:readException	()V
        //   71: aload 4
        //   73: invokevirtual 47	android/os/Parcel:recycle	()V
        //   76: aload_3
        //   77: invokevirtual 47	android/os/Parcel:recycle	()V
        //   80: return
        //   81: aconst_null
        //   82: astore 6
        //   84: goto -57 -> 27
        //   87: astore 5
        //   89: aload 4
        //   91: invokevirtual 47	android/os/Parcel:recycle	()V
        //   94: aload_3
        //   95: invokevirtual 47	android/os/Parcel:recycle	()V
        //   98: aload 5
        //   100: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	101	0	this	Proxy
        //   0	101	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	101	2	paramBoolean	boolean
        //   3	92	3	localParcel1	Parcel
        //   7	83	4	localParcel2	Parcel
        //   87	12	5	localObject	Object
        //   25	58	6	localIBinder	IBinder
        //   34	11	7	i	int
        // Exception table:
        //   from	to	target	type
        //   9	15	87	finally
        //   19	27	87	finally
        //   27	33	87	finally
        //   43	71	87	finally
      }
      
      /* Error */
      public void c(IGamesCallbacks paramIGamesCallbacks, String[] paramArrayOfString)
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
        //   16: ifnull +54 -> 70
        //   19: aload_1
        //   20: invokeinterface 57 1 0
        //   25: astore 6
        //   27: aload_3
        //   28: aload 6
        //   30: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   33: aload_3
        //   34: aload_2
        //   35: invokevirtual 135	android/os/Parcel:writeStringArray	([Ljava/lang/String;)V
        //   38: aload_0
        //   39: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   42: sipush 10020
        //   45: aload_3
        //   46: aload 4
        //   48: iconst_0
        //   49: invokeinterface 41 5 0
        //   54: pop
        //   55: aload 4
        //   57: invokevirtual 44	android/os/Parcel:readException	()V
        //   60: aload 4
        //   62: invokevirtual 47	android/os/Parcel:recycle	()V
        //   65: aload_3
        //   66: invokevirtual 47	android/os/Parcel:recycle	()V
        //   69: return
        //   70: aconst_null
        //   71: astore 6
        //   73: goto -46 -> 27
        //   76: astore 5
        //   78: aload 4
        //   80: invokevirtual 47	android/os/Parcel:recycle	()V
        //   83: aload_3
        //   84: invokevirtual 47	android/os/Parcel:recycle	()V
        //   87: aload 5
        //   89: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	90	0	this	Proxy
        //   0	90	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	90	2	paramArrayOfString	String[]
        //   3	81	3	localParcel1	Parcel
        //   7	72	4	localParcel2	Parcel
        //   76	12	5	localObject	Object
        //   25	47	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   9	15	76	finally
        //   19	27	76	finally
        //   27	60	76	finally
      }
      
      public void c(String paramString1, String paramString2, int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeString(paramString1);
          localParcel1.writeString(paramString2);
          localParcel1.writeInt(paramInt);
          this.lb.transact(8026, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void d(long paramLong, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeLong(paramLong);
          localParcel1.writeString(paramString);
          this.lb.transact(12014, localParcel1, localParcel2, 0);
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
      public void d(IGamesCallbacks paramIGamesCallbacks)
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
        //   15: ifnull +46 -> 61
        //   18: aload_1
        //   19: invokeinterface 57 1 0
        //   24: astore 5
        //   26: aload_2
        //   27: aload 5
        //   29: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   32: aload_0
        //   33: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   36: sipush 5026
        //   39: aload_2
        //   40: aload_3
        //   41: iconst_0
        //   42: invokeinterface 41 5 0
        //   47: pop
        //   48: aload_3
        //   49: invokevirtual 44	android/os/Parcel:readException	()V
        //   52: aload_3
        //   53: invokevirtual 47	android/os/Parcel:recycle	()V
        //   56: aload_2
        //   57: invokevirtual 47	android/os/Parcel:recycle	()V
        //   60: return
        //   61: aconst_null
        //   62: astore 5
        //   64: goto -38 -> 26
        //   67: astore 4
        //   69: aload_3
        //   70: invokevirtual 47	android/os/Parcel:recycle	()V
        //   73: aload_2
        //   74: invokevirtual 47	android/os/Parcel:recycle	()V
        //   77: aload 4
        //   79: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	80	0	this	Proxy
        //   0	80	1	paramIGamesCallbacks	IGamesCallbacks
        //   3	71	2	localParcel1	Parcel
        //   7	63	3	localParcel2	Parcel
        //   67	11	4	localObject	Object
        //   24	39	5	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   8	14	67	finally
        //   18	26	67	finally
        //   26	52	67	finally
      }
      
      /* Error */
      public void d(IGamesCallbacks paramIGamesCallbacks, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore 5
        //   3: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 6
        //   8: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 7
        //   13: aload 6
        //   15: ldc 27
        //   17: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload_1
        //   21: ifnull +85 -> 106
        //   24: aload_1
        //   25: invokeinterface 57 1 0
        //   30: astore 9
        //   32: aload 6
        //   34: aload 9
        //   36: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   39: aload 6
        //   41: iload_2
        //   42: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   45: iload_3
        //   46: ifeq +66 -> 112
        //   49: iload 5
        //   51: istore 10
        //   53: aload 6
        //   55: iload 10
        //   57: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   60: iload 4
        //   62: ifeq +56 -> 118
        //   65: aload 6
        //   67: iload 5
        //   69: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   72: aload_0
        //   73: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   76: sipush 6003
        //   79: aload 6
        //   81: aload 7
        //   83: iconst_0
        //   84: invokeinterface 41 5 0
        //   89: pop
        //   90: aload 7
        //   92: invokevirtual 44	android/os/Parcel:readException	()V
        //   95: aload 7
        //   97: invokevirtual 47	android/os/Parcel:recycle	()V
        //   100: aload 6
        //   102: invokevirtual 47	android/os/Parcel:recycle	()V
        //   105: return
        //   106: aconst_null
        //   107: astore 9
        //   109: goto -77 -> 32
        //   112: iconst_0
        //   113: istore 10
        //   115: goto -62 -> 53
        //   118: iconst_0
        //   119: istore 5
        //   121: goto -56 -> 65
        //   124: astore 8
        //   126: aload 7
        //   128: invokevirtual 47	android/os/Parcel:recycle	()V
        //   131: aload 6
        //   133: invokevirtual 47	android/os/Parcel:recycle	()V
        //   136: aload 8
        //   138: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	139	0	this	Proxy
        //   0	139	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	139	2	paramInt	int
        //   0	139	3	paramBoolean1	boolean
        //   0	139	4	paramBoolean2	boolean
        //   1	119	5	i	int
        //   6	126	6	localParcel1	Parcel
        //   11	116	7	localParcel2	Parcel
        //   124	13	8	localObject	Object
        //   30	78	9	localIBinder	IBinder
        //   51	63	10	j	int
        // Exception table:
        //   from	to	target	type
        //   13	20	124	finally
        //   24	32	124	finally
        //   32	45	124	finally
        //   53	60	124	finally
        //   65	95	124	finally
      }
      
      /* Error */
      public void d(IGamesCallbacks paramIGamesCallbacks, long paramLong)
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
        //   18: ifnull +58 -> 76
        //   21: aload_1
        //   22: invokeinterface 57 1 0
        //   27: astore 7
        //   29: aload 4
        //   31: aload 7
        //   33: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload 4
        //   38: lload_2
        //   39: invokevirtual 118	android/os/Parcel:writeLong	(J)V
        //   42: aload_0
        //   43: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   46: sipush 12011
        //   49: aload 4
        //   51: aload 5
        //   53: iconst_0
        //   54: invokeinterface 41 5 0
        //   59: pop
        //   60: aload 5
        //   62: invokevirtual 44	android/os/Parcel:readException	()V
        //   65: aload 5
        //   67: invokevirtual 47	android/os/Parcel:recycle	()V
        //   70: aload 4
        //   72: invokevirtual 47	android/os/Parcel:recycle	()V
        //   75: return
        //   76: aconst_null
        //   77: astore 7
        //   79: goto -50 -> 29
        //   82: astore 6
        //   84: aload 5
        //   86: invokevirtual 47	android/os/Parcel:recycle	()V
        //   89: aload 4
        //   91: invokevirtual 47	android/os/Parcel:recycle	()V
        //   94: aload 6
        //   96: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	97	0	this	Proxy
        //   0	97	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	97	2	paramLong	long
        //   3	87	4	localParcel1	Parcel
        //   8	77	5	localParcel2	Parcel
        //   82	13	6	localObject	Object
        //   27	51	7	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   10	17	82	finally
        //   21	29	82	finally
        //   29	65	82	finally
      }
      
      /* Error */
      public void d(IGamesCallbacks paramIGamesCallbacks, long paramLong, String paramString)
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
        //   18: ifnull +65 -> 83
        //   21: aload_1
        //   22: invokeinterface 57 1 0
        //   27: astore 8
        //   29: aload 5
        //   31: aload 8
        //   33: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload 5
        //   38: lload_2
        //   39: invokevirtual 118	android/os/Parcel:writeLong	(J)V
        //   42: aload 5
        //   44: aload 4
        //   46: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   49: aload_0
        //   50: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   53: sipush 12013
        //   56: aload 5
        //   58: aload 6
        //   60: iconst_0
        //   61: invokeinterface 41 5 0
        //   66: pop
        //   67: aload 6
        //   69: invokevirtual 44	android/os/Parcel:readException	()V
        //   72: aload 6
        //   74: invokevirtual 47	android/os/Parcel:recycle	()V
        //   77: aload 5
        //   79: invokevirtual 47	android/os/Parcel:recycle	()V
        //   82: return
        //   83: aconst_null
        //   84: astore 8
        //   86: goto -57 -> 29
        //   89: astore 7
        //   91: aload 6
        //   93: invokevirtual 47	android/os/Parcel:recycle	()V
        //   96: aload 5
        //   98: invokevirtual 47	android/os/Parcel:recycle	()V
        //   101: aload 7
        //   103: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	104	0	this	Proxy
        //   0	104	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	104	2	paramLong	long
        //   0	104	4	paramString	String
        //   3	94	5	localParcel1	Parcel
        //   8	84	6	localParcel2	Parcel
        //   89	13	7	localObject	Object
        //   27	58	8	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   10	17	89	finally
        //   21	29	89	finally
        //   29	72	89	finally
      }
      
      /* Error */
      public void d(IGamesCallbacks paramIGamesCallbacks, String paramString)
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
        //   16: ifnull +54 -> 70
        //   19: aload_1
        //   20: invokeinterface 57 1 0
        //   25: astore 6
        //   27: aload_3
        //   28: aload 6
        //   30: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   33: aload_3
        //   34: aload_2
        //   35: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   38: aload_0
        //   39: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   42: sipush 5037
        //   45: aload_3
        //   46: aload 4
        //   48: iconst_0
        //   49: invokeinterface 41 5 0
        //   54: pop
        //   55: aload 4
        //   57: invokevirtual 44	android/os/Parcel:readException	()V
        //   60: aload 4
        //   62: invokevirtual 47	android/os/Parcel:recycle	()V
        //   65: aload_3
        //   66: invokevirtual 47	android/os/Parcel:recycle	()V
        //   69: return
        //   70: aconst_null
        //   71: astore 6
        //   73: goto -46 -> 27
        //   76: astore 5
        //   78: aload 4
        //   80: invokevirtual 47	android/os/Parcel:recycle	()V
        //   83: aload_3
        //   84: invokevirtual 47	android/os/Parcel:recycle	()V
        //   87: aload 5
        //   89: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	90	0	this	Proxy
        //   0	90	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	90	2	paramString	String
        //   3	81	3	localParcel1	Parcel
        //   7	72	4	localParcel2	Parcel
        //   76	12	5	localObject	Object
        //   25	47	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   9	15	76	finally
        //   19	27	76	finally
        //   27	60	76	finally
      }
      
      /* Error */
      public void d(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore 6
        //   3: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 7
        //   8: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 8
        //   13: aload 7
        //   15: ldc 27
        //   17: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload_1
        //   21: ifnull +92 -> 113
        //   24: aload_1
        //   25: invokeinterface 57 1 0
        //   30: astore 10
        //   32: aload 7
        //   34: aload 10
        //   36: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   39: aload 7
        //   41: aload_2
        //   42: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   45: aload 7
        //   47: iload_3
        //   48: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   51: iload 4
        //   53: ifeq +66 -> 119
        //   56: iload 6
        //   58: istore 11
        //   60: aload 7
        //   62: iload 11
        //   64: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   67: iload 5
        //   69: ifeq +56 -> 125
        //   72: aload 7
        //   74: iload 6
        //   76: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   79: aload_0
        //   80: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   83: sipush 9020
        //   86: aload 7
        //   88: aload 8
        //   90: iconst_0
        //   91: invokeinterface 41 5 0
        //   96: pop
        //   97: aload 8
        //   99: invokevirtual 44	android/os/Parcel:readException	()V
        //   102: aload 8
        //   104: invokevirtual 47	android/os/Parcel:recycle	()V
        //   107: aload 7
        //   109: invokevirtual 47	android/os/Parcel:recycle	()V
        //   112: return
        //   113: aconst_null
        //   114: astore 10
        //   116: goto -84 -> 32
        //   119: iconst_0
        //   120: istore 11
        //   122: goto -62 -> 60
        //   125: iconst_0
        //   126: istore 6
        //   128: goto -56 -> 72
        //   131: astore 9
        //   133: aload 8
        //   135: invokevirtual 47	android/os/Parcel:recycle	()V
        //   138: aload 7
        //   140: invokevirtual 47	android/os/Parcel:recycle	()V
        //   143: aload 9
        //   145: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	146	0	this	Proxy
        //   0	146	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	146	2	paramString	String
        //   0	146	3	paramInt	int
        //   0	146	4	paramBoolean1	boolean
        //   0	146	5	paramBoolean2	boolean
        //   1	126	6	i	int
        //   6	133	7	localParcel1	Parcel
        //   11	123	8	localParcel2	Parcel
        //   131	13	9	localObject	Object
        //   30	85	10	localIBinder	IBinder
        //   58	63	11	j	int
        // Exception table:
        //   from	to	target	type
        //   13	20	131	finally
        //   24	32	131	finally
        //   32	51	131	finally
        //   60	67	131	finally
        //   72	102	131	finally
      }
      
      /* Error */
      public void d(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2)
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
        //   18: ifnull +64 -> 82
        //   21: aload_1
        //   22: invokeinterface 57 1 0
        //   27: astore 7
        //   29: aload 4
        //   31: aload 7
        //   33: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload 4
        //   38: aload_2
        //   39: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   42: aload 4
        //   44: aload_3
        //   45: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   48: aload_0
        //   49: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   52: sipush 8015
        //   55: aload 4
        //   57: aload 5
        //   59: iconst_0
        //   60: invokeinterface 41 5 0
        //   65: pop
        //   66: aload 5
        //   68: invokevirtual 44	android/os/Parcel:readException	()V
        //   71: aload 5
        //   73: invokevirtual 47	android/os/Parcel:recycle	()V
        //   76: aload 4
        //   78: invokevirtual 47	android/os/Parcel:recycle	()V
        //   81: return
        //   82: aconst_null
        //   83: astore 7
        //   85: goto -56 -> 29
        //   88: astore 6
        //   90: aload 5
        //   92: invokevirtual 47	android/os/Parcel:recycle	()V
        //   95: aload 4
        //   97: invokevirtual 47	android/os/Parcel:recycle	()V
        //   100: aload 6
        //   102: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	103	0	this	Proxy
        //   0	103	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	103	2	paramString1	String
        //   0	103	3	paramString2	String
        //   3	93	4	localParcel1	Parcel
        //   8	83	5	localParcel2	Parcel
        //   88	13	6	localObject	Object
        //   27	57	7	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   10	17	88	finally
        //   21	29	88	finally
        //   29	71	88	finally
      }
      
      /* Error */
      public void d(IGamesCallbacks paramIGamesCallbacks, String paramString, boolean paramBoolean)
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
        //   18: ifnull +75 -> 93
        //   21: aload_1
        //   22: invokeinterface 57 1 0
        //   27: astore 7
        //   29: aload 4
        //   31: aload 7
        //   33: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload 4
        //   38: aload_2
        //   39: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   42: iconst_0
        //   43: istore 8
        //   45: iload_3
        //   46: ifeq +6 -> 52
        //   49: iconst_1
        //   50: istore 8
        //   52: aload 4
        //   54: iload 8
        //   56: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   59: aload_0
        //   60: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   63: sipush 6505
        //   66: aload 4
        //   68: aload 5
        //   70: iconst_0
        //   71: invokeinterface 41 5 0
        //   76: pop
        //   77: aload 5
        //   79: invokevirtual 44	android/os/Parcel:readException	()V
        //   82: aload 5
        //   84: invokevirtual 47	android/os/Parcel:recycle	()V
        //   87: aload 4
        //   89: invokevirtual 47	android/os/Parcel:recycle	()V
        //   92: return
        //   93: aconst_null
        //   94: astore 7
        //   96: goto -67 -> 29
        //   99: astore 6
        //   101: aload 5
        //   103: invokevirtual 47	android/os/Parcel:recycle	()V
        //   106: aload 4
        //   108: invokevirtual 47	android/os/Parcel:recycle	()V
        //   111: aload 6
        //   113: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	114	0	this	Proxy
        //   0	114	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	114	2	paramString	String
        //   0	114	3	paramBoolean	boolean
        //   3	104	4	localParcel1	Parcel
        //   8	94	5	localParcel2	Parcel
        //   99	13	6	localObject	Object
        //   27	68	7	localIBinder	IBinder
        //   43	12	8	i	int
        // Exception table:
        //   from	to	target	type
        //   10	17	99	finally
        //   21	29	99	finally
        //   29	42	99	finally
        //   52	82	99	finally
      }
      
      /* Error */
      public void d(IGamesCallbacks paramIGamesCallbacks, boolean paramBoolean)
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
        //   16: ifnull +65 -> 81
        //   19: aload_1
        //   20: invokeinterface 57 1 0
        //   25: astore 6
        //   27: aload_3
        //   28: aload 6
        //   30: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
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
        //   50: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   53: sipush 12002
        //   56: aload_3
        //   57: aload 4
        //   59: iconst_0
        //   60: invokeinterface 41 5 0
        //   65: pop
        //   66: aload 4
        //   68: invokevirtual 44	android/os/Parcel:readException	()V
        //   71: aload 4
        //   73: invokevirtual 47	android/os/Parcel:recycle	()V
        //   76: aload_3
        //   77: invokevirtual 47	android/os/Parcel:recycle	()V
        //   80: return
        //   81: aconst_null
        //   82: astore 6
        //   84: goto -57 -> 27
        //   87: astore 5
        //   89: aload 4
        //   91: invokevirtual 47	android/os/Parcel:recycle	()V
        //   94: aload_3
        //   95: invokevirtual 47	android/os/Parcel:recycle	()V
        //   98: aload 5
        //   100: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	101	0	this	Proxy
        //   0	101	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	101	2	paramBoolean	boolean
        //   3	92	3	localParcel1	Parcel
        //   7	83	4	localParcel2	Parcel
        //   87	12	5	localObject	Object
        //   25	58	6	localIBinder	IBinder
        //   34	11	7	i	int
        // Exception table:
        //   from	to	target	type
        //   9	15	87	finally
        //   19	27	87	finally
        //   27	33	87	finally
        //   43	71	87	finally
      }
      
      public void dC(int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeInt(paramInt);
          this.lb.transact(5036, localParcel1, localParcel2, 0);
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
      public void e(IGamesCallbacks paramIGamesCallbacks)
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
        //   15: ifnull +46 -> 61
        //   18: aload_1
        //   19: invokeinterface 57 1 0
        //   24: astore 5
        //   26: aload_2
        //   27: aload 5
        //   29: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   32: aload_0
        //   33: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   36: sipush 5027
        //   39: aload_2
        //   40: aload_3
        //   41: iconst_0
        //   42: invokeinterface 41 5 0
        //   47: pop
        //   48: aload_3
        //   49: invokevirtual 44	android/os/Parcel:readException	()V
        //   52: aload_3
        //   53: invokevirtual 47	android/os/Parcel:recycle	()V
        //   56: aload_2
        //   57: invokevirtual 47	android/os/Parcel:recycle	()V
        //   60: return
        //   61: aconst_null
        //   62: astore 5
        //   64: goto -38 -> 26
        //   67: astore 4
        //   69: aload_3
        //   70: invokevirtual 47	android/os/Parcel:recycle	()V
        //   73: aload_2
        //   74: invokevirtual 47	android/os/Parcel:recycle	()V
        //   77: aload 4
        //   79: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	80	0	this	Proxy
        //   0	80	1	paramIGamesCallbacks	IGamesCallbacks
        //   3	71	2	localParcel1	Parcel
        //   7	63	3	localParcel2	Parcel
        //   67	11	4	localObject	Object
        //   24	39	5	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   8	14	67	finally
        //   18	26	67	finally
        //   26	52	67	finally
      }
      
      /* Error */
      public void e(IGamesCallbacks paramIGamesCallbacks, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore 5
        //   3: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 6
        //   8: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 7
        //   13: aload 6
        //   15: ldc 27
        //   17: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload_1
        //   21: ifnull +85 -> 106
        //   24: aload_1
        //   25: invokeinterface 57 1 0
        //   30: astore 9
        //   32: aload 6
        //   34: aload 9
        //   36: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   39: aload 6
        //   41: iload_2
        //   42: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   45: iload_3
        //   46: ifeq +66 -> 112
        //   49: iload 5
        //   51: istore 10
        //   53: aload 6
        //   55: iload 10
        //   57: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   60: iload 4
        //   62: ifeq +56 -> 118
        //   65: aload 6
        //   67: iload 5
        //   69: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   72: aload_0
        //   73: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   76: sipush 6004
        //   79: aload 6
        //   81: aload 7
        //   83: iconst_0
        //   84: invokeinterface 41 5 0
        //   89: pop
        //   90: aload 7
        //   92: invokevirtual 44	android/os/Parcel:readException	()V
        //   95: aload 7
        //   97: invokevirtual 47	android/os/Parcel:recycle	()V
        //   100: aload 6
        //   102: invokevirtual 47	android/os/Parcel:recycle	()V
        //   105: return
        //   106: aconst_null
        //   107: astore 9
        //   109: goto -77 -> 32
        //   112: iconst_0
        //   113: istore 10
        //   115: goto -62 -> 53
        //   118: iconst_0
        //   119: istore 5
        //   121: goto -56 -> 65
        //   124: astore 8
        //   126: aload 7
        //   128: invokevirtual 47	android/os/Parcel:recycle	()V
        //   131: aload 6
        //   133: invokevirtual 47	android/os/Parcel:recycle	()V
        //   136: aload 8
        //   138: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	139	0	this	Proxy
        //   0	139	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	139	2	paramInt	int
        //   0	139	3	paramBoolean1	boolean
        //   0	139	4	paramBoolean2	boolean
        //   1	119	5	i	int
        //   6	126	6	localParcel1	Parcel
        //   11	116	7	localParcel2	Parcel
        //   124	13	8	localObject	Object
        //   30	78	9	localIBinder	IBinder
        //   51	63	10	j	int
        // Exception table:
        //   from	to	target	type
        //   13	20	124	finally
        //   24	32	124	finally
        //   32	45	124	finally
        //   53	60	124	finally
        //   65	95	124	finally
      }
      
      /* Error */
      public void e(IGamesCallbacks paramIGamesCallbacks, String paramString)
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
        //   16: ifnull +54 -> 70
        //   19: aload_1
        //   20: invokeinterface 57 1 0
        //   25: astore 6
        //   27: aload_3
        //   28: aload 6
        //   30: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   33: aload_3
        //   34: aload_2
        //   35: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   38: aload_0
        //   39: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   42: sipush 5042
        //   45: aload_3
        //   46: aload 4
        //   48: iconst_0
        //   49: invokeinterface 41 5 0
        //   54: pop
        //   55: aload 4
        //   57: invokevirtual 44	android/os/Parcel:readException	()V
        //   60: aload 4
        //   62: invokevirtual 47	android/os/Parcel:recycle	()V
        //   65: aload_3
        //   66: invokevirtual 47	android/os/Parcel:recycle	()V
        //   69: return
        //   70: aconst_null
        //   71: astore 6
        //   73: goto -46 -> 27
        //   76: astore 5
        //   78: aload 4
        //   80: invokevirtual 47	android/os/Parcel:recycle	()V
        //   83: aload_3
        //   84: invokevirtual 47	android/os/Parcel:recycle	()V
        //   87: aload 5
        //   89: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	90	0	this	Proxy
        //   0	90	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	90	2	paramString	String
        //   3	81	3	localParcel1	Parcel
        //   7	72	4	localParcel2	Parcel
        //   76	12	5	localObject	Object
        //   25	47	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   9	15	76	finally
        //   19	27	76	finally
        //   27	60	76	finally
      }
      
      /* Error */
      public void e(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore 6
        //   3: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 7
        //   8: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 8
        //   13: aload 7
        //   15: ldc 27
        //   17: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload_1
        //   21: ifnull +92 -> 113
        //   24: aload_1
        //   25: invokeinterface 57 1 0
        //   30: astore 10
        //   32: aload 7
        //   34: aload 10
        //   36: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   39: aload 7
        //   41: aload_2
        //   42: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   45: aload 7
        //   47: iload_3
        //   48: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   51: iload 4
        //   53: ifeq +66 -> 119
        //   56: iload 6
        //   58: istore 11
        //   60: aload 7
        //   62: iload 11
        //   64: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   67: iload 5
        //   69: ifeq +56 -> 125
        //   72: aload 7
        //   74: iload 6
        //   76: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   79: aload_0
        //   80: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   83: sipush 12021
        //   86: aload 7
        //   88: aload 8
        //   90: iconst_0
        //   91: invokeinterface 41 5 0
        //   96: pop
        //   97: aload 8
        //   99: invokevirtual 44	android/os/Parcel:readException	()V
        //   102: aload 8
        //   104: invokevirtual 47	android/os/Parcel:recycle	()V
        //   107: aload 7
        //   109: invokevirtual 47	android/os/Parcel:recycle	()V
        //   112: return
        //   113: aconst_null
        //   114: astore 10
        //   116: goto -84 -> 32
        //   119: iconst_0
        //   120: istore 11
        //   122: goto -62 -> 60
        //   125: iconst_0
        //   126: istore 6
        //   128: goto -56 -> 72
        //   131: astore 9
        //   133: aload 8
        //   135: invokevirtual 47	android/os/Parcel:recycle	()V
        //   138: aload 7
        //   140: invokevirtual 47	android/os/Parcel:recycle	()V
        //   143: aload 9
        //   145: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	146	0	this	Proxy
        //   0	146	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	146	2	paramString	String
        //   0	146	3	paramInt	int
        //   0	146	4	paramBoolean1	boolean
        //   0	146	5	paramBoolean2	boolean
        //   1	126	6	i	int
        //   6	133	7	localParcel1	Parcel
        //   11	123	8	localParcel2	Parcel
        //   131	13	9	localObject	Object
        //   30	85	10	localIBinder	IBinder
        //   58	63	11	j	int
        // Exception table:
        //   from	to	target	type
        //   13	20	131	finally
        //   24	32	131	finally
        //   32	51	131	finally
        //   60	67	131	finally
        //   72	102	131	finally
      }
      
      /* Error */
      public void e(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2)
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
        //   18: ifnull +64 -> 82
        //   21: aload_1
        //   22: invokeinterface 57 1 0
        //   27: astore 7
        //   29: aload 4
        //   31: aload 7
        //   33: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload 4
        //   38: aload_2
        //   39: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   42: aload 4
        //   44: aload_3
        //   45: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   48: aload_0
        //   49: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   52: sipush 8016
        //   55: aload 4
        //   57: aload 5
        //   59: iconst_0
        //   60: invokeinterface 41 5 0
        //   65: pop
        //   66: aload 5
        //   68: invokevirtual 44	android/os/Parcel:readException	()V
        //   71: aload 5
        //   73: invokevirtual 47	android/os/Parcel:recycle	()V
        //   76: aload 4
        //   78: invokevirtual 47	android/os/Parcel:recycle	()V
        //   81: return
        //   82: aconst_null
        //   83: astore 7
        //   85: goto -56 -> 29
        //   88: astore 6
        //   90: aload 5
        //   92: invokevirtual 47	android/os/Parcel:recycle	()V
        //   95: aload 4
        //   97: invokevirtual 47	android/os/Parcel:recycle	()V
        //   100: aload 6
        //   102: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	103	0	this	Proxy
        //   0	103	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	103	2	paramString1	String
        //   0	103	3	paramString2	String
        //   3	93	4	localParcel1	Parcel
        //   8	83	5	localParcel2	Parcel
        //   88	13	6	localObject	Object
        //   27	57	7	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   10	17	88	finally
        //   21	29	88	finally
        //   29	71	88	finally
      }
      
      /* Error */
      public void e(IGamesCallbacks paramIGamesCallbacks, String paramString, boolean paramBoolean)
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
        //   18: ifnull +75 -> 93
        //   21: aload_1
        //   22: invokeinterface 57 1 0
        //   27: astore 7
        //   29: aload 4
        //   31: aload 7
        //   33: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload 4
        //   38: aload_2
        //   39: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   42: iconst_0
        //   43: istore 8
        //   45: iload_3
        //   46: ifeq +6 -> 52
        //   49: iconst_1
        //   50: istore 8
        //   52: aload 4
        //   54: iload 8
        //   56: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   59: aload_0
        //   60: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   63: sipush 12006
        //   66: aload 4
        //   68: aload 5
        //   70: iconst_0
        //   71: invokeinterface 41 5 0
        //   76: pop
        //   77: aload 5
        //   79: invokevirtual 44	android/os/Parcel:readException	()V
        //   82: aload 5
        //   84: invokevirtual 47	android/os/Parcel:recycle	()V
        //   87: aload 4
        //   89: invokevirtual 47	android/os/Parcel:recycle	()V
        //   92: return
        //   93: aconst_null
        //   94: astore 7
        //   96: goto -67 -> 29
        //   99: astore 6
        //   101: aload 5
        //   103: invokevirtual 47	android/os/Parcel:recycle	()V
        //   106: aload 4
        //   108: invokevirtual 47	android/os/Parcel:recycle	()V
        //   111: aload 6
        //   113: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	114	0	this	Proxy
        //   0	114	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	114	2	paramString	String
        //   0	114	3	paramBoolean	boolean
        //   3	104	4	localParcel1	Parcel
        //   8	94	5	localParcel2	Parcel
        //   99	13	6	localObject	Object
        //   27	68	7	localIBinder	IBinder
        //   43	12	8	i	int
        // Exception table:
        //   from	to	target	type
        //   10	17	99	finally
        //   21	29	99	finally
        //   29	42	99	finally
        //   52	82	99	finally
      }
      
      /* Error */
      public void e(IGamesCallbacks paramIGamesCallbacks, boolean paramBoolean)
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
        //   16: ifnull +65 -> 81
        //   19: aload_1
        //   20: invokeinterface 57 1 0
        //   25: astore 6
        //   27: aload_3
        //   28: aload 6
        //   30: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
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
        //   50: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   53: sipush 12032
        //   56: aload_3
        //   57: aload 4
        //   59: iconst_0
        //   60: invokeinterface 41 5 0
        //   65: pop
        //   66: aload 4
        //   68: invokevirtual 44	android/os/Parcel:readException	()V
        //   71: aload 4
        //   73: invokevirtual 47	android/os/Parcel:recycle	()V
        //   76: aload_3
        //   77: invokevirtual 47	android/os/Parcel:recycle	()V
        //   80: return
        //   81: aconst_null
        //   82: astore 6
        //   84: goto -57 -> 27
        //   87: astore 5
        //   89: aload 4
        //   91: invokevirtual 47	android/os/Parcel:recycle	()V
        //   94: aload_3
        //   95: invokevirtual 47	android/os/Parcel:recycle	()V
        //   98: aload 5
        //   100: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	101	0	this	Proxy
        //   0	101	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	101	2	paramBoolean	boolean
        //   3	92	3	localParcel1	Parcel
        //   7	83	4	localParcel2	Parcel
        //   87	12	5	localObject	Object
        //   25	58	6	localIBinder	IBinder
        //   34	11	7	i	int
        // Exception table:
        //   from	to	target	type
        //   9	15	87	finally
        //   19	27	87	finally
        //   27	33	87	finally
        //   43	71	87	finally
      }
      
      /* Error */
      public void f(IGamesCallbacks paramIGamesCallbacks)
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
        //   15: ifnull +46 -> 61
        //   18: aload_1
        //   19: invokeinterface 57 1 0
        //   24: astore 5
        //   26: aload_2
        //   27: aload 5
        //   29: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   32: aload_0
        //   33: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   36: sipush 5047
        //   39: aload_2
        //   40: aload_3
        //   41: iconst_0
        //   42: invokeinterface 41 5 0
        //   47: pop
        //   48: aload_3
        //   49: invokevirtual 44	android/os/Parcel:readException	()V
        //   52: aload_3
        //   53: invokevirtual 47	android/os/Parcel:recycle	()V
        //   56: aload_2
        //   57: invokevirtual 47	android/os/Parcel:recycle	()V
        //   60: return
        //   61: aconst_null
        //   62: astore 5
        //   64: goto -38 -> 26
        //   67: astore 4
        //   69: aload_3
        //   70: invokevirtual 47	android/os/Parcel:recycle	()V
        //   73: aload_2
        //   74: invokevirtual 47	android/os/Parcel:recycle	()V
        //   77: aload 4
        //   79: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	80	0	this	Proxy
        //   0	80	1	paramIGamesCallbacks	IGamesCallbacks
        //   3	71	2	localParcel1	Parcel
        //   7	63	3	localParcel2	Parcel
        //   67	11	4	localObject	Object
        //   24	39	5	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   8	14	67	finally
        //   18	26	67	finally
        //   26	52	67	finally
      }
      
      /* Error */
      public void f(IGamesCallbacks paramIGamesCallbacks, String paramString)
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
        //   16: ifnull +54 -> 70
        //   19: aload_1
        //   20: invokeinterface 57 1 0
        //   25: astore 6
        //   27: aload_3
        //   28: aload 6
        //   30: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   33: aload_3
        //   34: aload_2
        //   35: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   38: aload_0
        //   39: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   42: sipush 5043
        //   45: aload_3
        //   46: aload 4
        //   48: iconst_0
        //   49: invokeinterface 41 5 0
        //   54: pop
        //   55: aload 4
        //   57: invokevirtual 44	android/os/Parcel:readException	()V
        //   60: aload 4
        //   62: invokevirtual 47	android/os/Parcel:recycle	()V
        //   65: aload_3
        //   66: invokevirtual 47	android/os/Parcel:recycle	()V
        //   69: return
        //   70: aconst_null
        //   71: astore 6
        //   73: goto -46 -> 27
        //   76: astore 5
        //   78: aload 4
        //   80: invokevirtual 47	android/os/Parcel:recycle	()V
        //   83: aload_3
        //   84: invokevirtual 47	android/os/Parcel:recycle	()V
        //   87: aload 5
        //   89: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	90	0	this	Proxy
        //   0	90	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	90	2	paramString	String
        //   3	81	3	localParcel1	Parcel
        //   7	72	4	localParcel2	Parcel
        //   76	12	5	localObject	Object
        //   25	47	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   9	15	76	finally
        //   19	27	76	finally
        //   27	60	76	finally
      }
      
      /* Error */
      public void f(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore 6
        //   3: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 7
        //   8: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 8
        //   13: aload 7
        //   15: ldc 27
        //   17: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload_1
        //   21: ifnull +92 -> 113
        //   24: aload_1
        //   25: invokeinterface 57 1 0
        //   30: astore 10
        //   32: aload 7
        //   34: aload 10
        //   36: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   39: aload 7
        //   41: aload_2
        //   42: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   45: aload 7
        //   47: iload_3
        //   48: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   51: iload 4
        //   53: ifeq +66 -> 119
        //   56: iload 6
        //   58: istore 11
        //   60: aload 7
        //   62: iload 11
        //   64: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   67: iload 5
        //   69: ifeq +56 -> 125
        //   72: aload 7
        //   74: iload 6
        //   76: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   79: aload_0
        //   80: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   83: sipush 12022
        //   86: aload 7
        //   88: aload 8
        //   90: iconst_0
        //   91: invokeinterface 41 5 0
        //   96: pop
        //   97: aload 8
        //   99: invokevirtual 44	android/os/Parcel:readException	()V
        //   102: aload 8
        //   104: invokevirtual 47	android/os/Parcel:recycle	()V
        //   107: aload 7
        //   109: invokevirtual 47	android/os/Parcel:recycle	()V
        //   112: return
        //   113: aconst_null
        //   114: astore 10
        //   116: goto -84 -> 32
        //   119: iconst_0
        //   120: istore 11
        //   122: goto -62 -> 60
        //   125: iconst_0
        //   126: istore 6
        //   128: goto -56 -> 72
        //   131: astore 9
        //   133: aload 8
        //   135: invokevirtual 47	android/os/Parcel:recycle	()V
        //   138: aload 7
        //   140: invokevirtual 47	android/os/Parcel:recycle	()V
        //   143: aload 9
        //   145: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	146	0	this	Proxy
        //   0	146	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	146	2	paramString	String
        //   0	146	3	paramInt	int
        //   0	146	4	paramBoolean1	boolean
        //   0	146	5	paramBoolean2	boolean
        //   1	126	6	i	int
        //   6	133	7	localParcel1	Parcel
        //   11	123	8	localParcel2	Parcel
        //   131	13	9	localObject	Object
        //   30	85	10	localIBinder	IBinder
        //   58	63	11	j	int
        // Exception table:
        //   from	to	target	type
        //   13	20	131	finally
        //   24	32	131	finally
        //   32	51	131	finally
        //   60	67	131	finally
        //   72	102	131	finally
      }
      
      /* Error */
      public void f(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2)
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
        //   18: ifnull +64 -> 82
        //   21: aload_1
        //   22: invokeinterface 57 1 0
        //   27: astore 7
        //   29: aload 4
        //   31: aload 7
        //   33: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload 4
        //   38: aload_2
        //   39: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   42: aload 4
        //   44: aload_3
        //   45: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   48: aload_0
        //   49: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   52: sipush 12009
        //   55: aload 4
        //   57: aload 5
        //   59: iconst_0
        //   60: invokeinterface 41 5 0
        //   65: pop
        //   66: aload 5
        //   68: invokevirtual 44	android/os/Parcel:readException	()V
        //   71: aload 5
        //   73: invokevirtual 47	android/os/Parcel:recycle	()V
        //   76: aload 4
        //   78: invokevirtual 47	android/os/Parcel:recycle	()V
        //   81: return
        //   82: aconst_null
        //   83: astore 7
        //   85: goto -56 -> 29
        //   88: astore 6
        //   90: aload 5
        //   92: invokevirtual 47	android/os/Parcel:recycle	()V
        //   95: aload 4
        //   97: invokevirtual 47	android/os/Parcel:recycle	()V
        //   100: aload 6
        //   102: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	103	0	this	Proxy
        //   0	103	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	103	2	paramString1	String
        //   0	103	3	paramString2	String
        //   3	93	4	localParcel1	Parcel
        //   8	83	5	localParcel2	Parcel
        //   88	13	6	localObject	Object
        //   27	57	7	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   10	17	88	finally
        //   21	29	88	finally
        //   29	71	88	finally
      }
      
      /* Error */
      public void f(IGamesCallbacks paramIGamesCallbacks, boolean paramBoolean)
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
        //   16: ifnull +65 -> 81
        //   19: aload_1
        //   20: invokeinterface 57 1 0
        //   25: astore 6
        //   27: aload_3
        //   28: aload 6
        //   30: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
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
        //   50: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   53: sipush 12016
        //   56: aload_3
        //   57: aload 4
        //   59: iconst_0
        //   60: invokeinterface 41 5 0
        //   65: pop
        //   66: aload 4
        //   68: invokevirtual 44	android/os/Parcel:readException	()V
        //   71: aload 4
        //   73: invokevirtual 47	android/os/Parcel:recycle	()V
        //   76: aload_3
        //   77: invokevirtual 47	android/os/Parcel:recycle	()V
        //   80: return
        //   81: aconst_null
        //   82: astore 6
        //   84: goto -57 -> 27
        //   87: astore 5
        //   89: aload 4
        //   91: invokevirtual 47	android/os/Parcel:recycle	()V
        //   94: aload_3
        //   95: invokevirtual 47	android/os/Parcel:recycle	()V
        //   98: aload 5
        //   100: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	101	0	this	Proxy
        //   0	101	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	101	2	paramBoolean	boolean
        //   3	92	3	localParcel1	Parcel
        //   7	83	4	localParcel2	Parcel
        //   87	12	5	localObject	Object
        //   25	58	6	localIBinder	IBinder
        //   34	11	7	i	int
        // Exception table:
        //   from	to	target	type
        //   9	15	87	finally
        //   19	27	87	finally
        //   27	33	87	finally
        //   43	71	87	finally
      }
      
      /* Error */
      public Bundle fC()
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
        //   15: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   18: sipush 5004
        //   21: aload_1
        //   22: aload_2
        //   23: iconst_0
        //   24: invokeinterface 41 5 0
        //   29: pop
        //   30: aload_2
        //   31: invokevirtual 44	android/os/Parcel:readException	()V
        //   34: aload_2
        //   35: invokevirtual 71	android/os/Parcel:readInt	()I
        //   38: ifeq +28 -> 66
        //   41: getstatic 218	android/os/Bundle:CREATOR	Landroid/os/Parcelable$Creator;
        //   44: aload_2
        //   45: invokeinterface 84 2 0
        //   50: checkcast 121	android/os/Bundle
        //   53: astore 5
        //   55: aload_2
        //   56: invokevirtual 47	android/os/Parcel:recycle	()V
        //   59: aload_1
        //   60: invokevirtual 47	android/os/Parcel:recycle	()V
        //   63: aload 5
        //   65: areturn
        //   66: aconst_null
        //   67: astore 5
        //   69: goto -14 -> 55
        //   72: astore_3
        //   73: aload_2
        //   74: invokevirtual 47	android/os/Parcel:recycle	()V
        //   77: aload_1
        //   78: invokevirtual 47	android/os/Parcel:recycle	()V
        //   81: aload_3
        //   82: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	83	0	this	Proxy
        //   3	75	1	localParcel1	Parcel
        //   7	67	2	localParcel2	Parcel
        //   72	10	3	localObject	Object
        //   53	15	5	localBundle	Bundle
        // Exception table:
        //   from	to	target	type
        //   8	55	72	finally
      }
      
      /* Error */
      public void g(IGamesCallbacks paramIGamesCallbacks)
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
        //   15: ifnull +46 -> 61
        //   18: aload_1
        //   19: invokeinterface 57 1 0
        //   24: astore 5
        //   26: aload_2
        //   27: aload 5
        //   29: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   32: aload_0
        //   33: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   36: sipush 5049
        //   39: aload_2
        //   40: aload_3
        //   41: iconst_0
        //   42: invokeinterface 41 5 0
        //   47: pop
        //   48: aload_3
        //   49: invokevirtual 44	android/os/Parcel:readException	()V
        //   52: aload_3
        //   53: invokevirtual 47	android/os/Parcel:recycle	()V
        //   56: aload_2
        //   57: invokevirtual 47	android/os/Parcel:recycle	()V
        //   60: return
        //   61: aconst_null
        //   62: astore 5
        //   64: goto -38 -> 26
        //   67: astore 4
        //   69: aload_3
        //   70: invokevirtual 47	android/os/Parcel:recycle	()V
        //   73: aload_2
        //   74: invokevirtual 47	android/os/Parcel:recycle	()V
        //   77: aload 4
        //   79: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	80	0	this	Proxy
        //   0	80	1	paramIGamesCallbacks	IGamesCallbacks
        //   3	71	2	localParcel1	Parcel
        //   7	63	3	localParcel2	Parcel
        //   67	11	4	localObject	Object
        //   24	39	5	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   8	14	67	finally
        //   18	26	67	finally
        //   26	52	67	finally
      }
      
      /* Error */
      public void g(IGamesCallbacks paramIGamesCallbacks, String paramString)
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
        //   16: ifnull +54 -> 70
        //   19: aload_1
        //   20: invokeinterface 57 1 0
        //   25: astore 6
        //   27: aload_3
        //   28: aload 6
        //   30: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   33: aload_3
        //   34: aload_2
        //   35: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   38: aload_0
        //   39: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   42: sipush 5052
        //   45: aload_3
        //   46: aload 4
        //   48: iconst_0
        //   49: invokeinterface 41 5 0
        //   54: pop
        //   55: aload 4
        //   57: invokevirtual 44	android/os/Parcel:readException	()V
        //   60: aload 4
        //   62: invokevirtual 47	android/os/Parcel:recycle	()V
        //   65: aload_3
        //   66: invokevirtual 47	android/os/Parcel:recycle	()V
        //   69: return
        //   70: aconst_null
        //   71: astore 6
        //   73: goto -46 -> 27
        //   76: astore 5
        //   78: aload 4
        //   80: invokevirtual 47	android/os/Parcel:recycle	()V
        //   83: aload_3
        //   84: invokevirtual 47	android/os/Parcel:recycle	()V
        //   87: aload 5
        //   89: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	90	0	this	Proxy
        //   0	90	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	90	2	paramString	String
        //   3	81	3	localParcel1	Parcel
        //   7	72	4	localParcel2	Parcel
        //   76	12	5	localObject	Object
        //   25	47	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   9	15	76	finally
        //   19	27	76	finally
        //   27	60	76	finally
      }
      
      /* Error */
      public void g(IGamesCallbacks paramIGamesCallbacks, boolean paramBoolean)
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
        //   16: ifnull +65 -> 81
        //   19: aload_1
        //   20: invokeinterface 57 1 0
        //   25: astore 6
        //   27: aload_3
        //   28: aload 6
        //   30: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
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
        //   50: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   53: sipush 13003
        //   56: aload_3
        //   57: aload 4
        //   59: iconst_0
        //   60: invokeinterface 41 5 0
        //   65: pop
        //   66: aload 4
        //   68: invokevirtual 44	android/os/Parcel:readException	()V
        //   71: aload 4
        //   73: invokevirtual 47	android/os/Parcel:recycle	()V
        //   76: aload_3
        //   77: invokevirtual 47	android/os/Parcel:recycle	()V
        //   80: return
        //   81: aconst_null
        //   82: astore 6
        //   84: goto -57 -> 27
        //   87: astore 5
        //   89: aload 4
        //   91: invokevirtual 47	android/os/Parcel:recycle	()V
        //   94: aload_3
        //   95: invokevirtual 47	android/os/Parcel:recycle	()V
        //   98: aload 5
        //   100: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	101	0	this	Proxy
        //   0	101	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	101	2	paramBoolean	boolean
        //   3	92	3	localParcel1	Parcel
        //   7	83	4	localParcel2	Parcel
        //   87	12	5	localObject	Object
        //   25	58	6	localIBinder	IBinder
        //   34	11	7	i	int
        // Exception table:
        //   from	to	target	type
        //   9	15	87	finally
        //   19	27	87	finally
        //   27	33	87	finally
        //   43	71	87	finally
      }
      
      public ParcelFileDescriptor h(Uri paramUri)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (paramUri != null)
            {
              localParcel1.writeInt(1);
              paramUri.writeToParcel(localParcel1, 0);
              this.lb.transact(6507, localParcel1, localParcel2, 0);
              localParcel2.readException();
              if (localParcel2.readInt() != 0)
              {
                localParcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(localParcel2);
                return localParcelFileDescriptor;
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            ParcelFileDescriptor localParcelFileDescriptor = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      /* Error */
      public RoomEntity h(IGamesCallbacks paramIGamesCallbacks, String paramString)
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
        //   16: ifnull +86 -> 102
        //   19: aload_1
        //   20: invokeinterface 57 1 0
        //   25: astore 6
        //   27: aload_3
        //   28: aload 6
        //   30: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   33: aload_3
        //   34: aload_2
        //   35: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   38: aload_0
        //   39: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   42: sipush 5053
        //   45: aload_3
        //   46: aload 4
        //   48: iconst_0
        //   49: invokeinterface 41 5 0
        //   54: pop
        //   55: aload 4
        //   57: invokevirtual 44	android/os/Parcel:readException	()V
        //   60: aload 4
        //   62: invokevirtual 71	android/os/Parcel:readInt	()I
        //   65: istore 8
        //   67: aconst_null
        //   68: astore 9
        //   70: iload 8
        //   72: ifeq +18 -> 90
        //   75: getstatic 223	com/google/android/gms/games/multiplayer/realtime/RoomEntity:CREATOR	Landroid/os/Parcelable$Creator;
        //   78: aload 4
        //   80: invokeinterface 84 2 0
        //   85: checkcast 103	com/google/android/gms/games/multiplayer/realtime/RoomEntity
        //   88: astore 9
        //   90: aload 4
        //   92: invokevirtual 47	android/os/Parcel:recycle	()V
        //   95: aload_3
        //   96: invokevirtual 47	android/os/Parcel:recycle	()V
        //   99: aload 9
        //   101: areturn
        //   102: aconst_null
        //   103: astore 6
        //   105: goto -78 -> 27
        //   108: astore 5
        //   110: aload 4
        //   112: invokevirtual 47	android/os/Parcel:recycle	()V
        //   115: aload_3
        //   116: invokevirtual 47	android/os/Parcel:recycle	()V
        //   119: aload 5
        //   121: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	122	0	this	Proxy
        //   0	122	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	122	2	paramString	String
        //   3	113	3	localParcel1	Parcel
        //   7	104	4	localParcel2	Parcel
        //   108	12	5	localObject	Object
        //   25	79	6	localIBinder	IBinder
        //   65	6	8	i	int
        //   68	32	9	localRoomEntity	RoomEntity
        // Exception table:
        //   from	to	target	type
        //   9	15	108	finally
        //   19	27	108	finally
        //   27	67	108	finally
        //   75	90	108	finally
      }
      
      /* Error */
      public void h(IGamesCallbacks paramIGamesCallbacks)
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
        //   15: ifnull +46 -> 61
        //   18: aload_1
        //   19: invokeinterface 57 1 0
        //   24: astore 5
        //   26: aload_2
        //   27: aload 5
        //   29: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   32: aload_0
        //   33: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   36: sipush 5056
        //   39: aload_2
        //   40: aload_3
        //   41: iconst_0
        //   42: invokeinterface 41 5 0
        //   47: pop
        //   48: aload_3
        //   49: invokevirtual 44	android/os/Parcel:readException	()V
        //   52: aload_3
        //   53: invokevirtual 47	android/os/Parcel:recycle	()V
        //   56: aload_2
        //   57: invokevirtual 47	android/os/Parcel:recycle	()V
        //   60: return
        //   61: aconst_null
        //   62: astore 5
        //   64: goto -38 -> 26
        //   67: astore 4
        //   69: aload_3
        //   70: invokevirtual 47	android/os/Parcel:recycle	()V
        //   73: aload_2
        //   74: invokevirtual 47	android/os/Parcel:recycle	()V
        //   77: aload 4
        //   79: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	80	0	this	Proxy
        //   0	80	1	paramIGamesCallbacks	IGamesCallbacks
        //   3	71	2	localParcel1	Parcel
        //   7	63	3	localParcel2	Parcel
        //   67	11	4	localObject	Object
        //   24	39	5	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   8	14	67	finally
        //   18	26	67	finally
        //   26	52	67	finally
      }
      
      /* Error */
      public void h(IGamesCallbacks paramIGamesCallbacks, boolean paramBoolean)
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
        //   16: ifnull +65 -> 81
        //   19: aload_1
        //   20: invokeinterface 57 1 0
        //   25: astore 6
        //   27: aload_3
        //   28: aload 6
        //   30: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
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
        //   50: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   53: sipush 13004
        //   56: aload_3
        //   57: aload 4
        //   59: iconst_0
        //   60: invokeinterface 41 5 0
        //   65: pop
        //   66: aload 4
        //   68: invokevirtual 44	android/os/Parcel:readException	()V
        //   71: aload 4
        //   73: invokevirtual 47	android/os/Parcel:recycle	()V
        //   76: aload_3
        //   77: invokevirtual 47	android/os/Parcel:recycle	()V
        //   80: return
        //   81: aconst_null
        //   82: astore 6
        //   84: goto -57 -> 27
        //   87: astore 5
        //   89: aload 4
        //   91: invokevirtual 47	android/os/Parcel:recycle	()V
        //   94: aload_3
        //   95: invokevirtual 47	android/os/Parcel:recycle	()V
        //   98: aload 5
        //   100: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	101	0	this	Proxy
        //   0	101	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	101	2	paramBoolean	boolean
        //   3	92	3	localParcel1	Parcel
        //   7	83	4	localParcel2	Parcel
        //   87	12	5	localObject	Object
        //   25	58	6	localIBinder	IBinder
        //   34	11	7	i	int
        // Exception table:
        //   from	to	target	type
        //   9	15	87	finally
        //   19	27	87	finally
        //   27	33	87	finally
        //   43	71	87	finally
      }
      
      /* Error */
      public void i(IGamesCallbacks paramIGamesCallbacks)
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
        //   15: ifnull +46 -> 61
        //   18: aload_1
        //   19: invokeinterface 57 1 0
        //   24: astore 5
        //   26: aload_2
        //   27: aload 5
        //   29: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   32: aload_0
        //   33: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   36: sipush 5062
        //   39: aload_2
        //   40: aload_3
        //   41: iconst_0
        //   42: invokeinterface 41 5 0
        //   47: pop
        //   48: aload_3
        //   49: invokevirtual 44	android/os/Parcel:readException	()V
        //   52: aload_3
        //   53: invokevirtual 47	android/os/Parcel:recycle	()V
        //   56: aload_2
        //   57: invokevirtual 47	android/os/Parcel:recycle	()V
        //   60: return
        //   61: aconst_null
        //   62: astore 5
        //   64: goto -38 -> 26
        //   67: astore 4
        //   69: aload_3
        //   70: invokevirtual 47	android/os/Parcel:recycle	()V
        //   73: aload_2
        //   74: invokevirtual 47	android/os/Parcel:recycle	()V
        //   77: aload 4
        //   79: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	80	0	this	Proxy
        //   0	80	1	paramIGamesCallbacks	IGamesCallbacks
        //   3	71	2	localParcel1	Parcel
        //   7	63	3	localParcel2	Parcel
        //   67	11	4	localObject	Object
        //   24	39	5	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   8	14	67	finally
        //   18	26	67	finally
        //   26	52	67	finally
      }
      
      /* Error */
      public void i(IGamesCallbacks paramIGamesCallbacks, String paramString)
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
        //   16: ifnull +54 -> 70
        //   19: aload_1
        //   20: invokeinterface 57 1 0
        //   25: astore 6
        //   27: aload_3
        //   28: aload 6
        //   30: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   33: aload_3
        //   34: aload_2
        //   35: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   38: aload_0
        //   39: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   42: sipush 5061
        //   45: aload_3
        //   46: aload 4
        //   48: iconst_0
        //   49: invokeinterface 41 5 0
        //   54: pop
        //   55: aload 4
        //   57: invokevirtual 44	android/os/Parcel:readException	()V
        //   60: aload 4
        //   62: invokevirtual 47	android/os/Parcel:recycle	()V
        //   65: aload_3
        //   66: invokevirtual 47	android/os/Parcel:recycle	()V
        //   69: return
        //   70: aconst_null
        //   71: astore 6
        //   73: goto -46 -> 27
        //   76: astore 5
        //   78: aload 4
        //   80: invokevirtual 47	android/os/Parcel:recycle	()V
        //   83: aload_3
        //   84: invokevirtual 47	android/os/Parcel:recycle	()V
        //   87: aload 5
        //   89: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	90	0	this	Proxy
        //   0	90	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	90	2	paramString	String
        //   3	81	3	localParcel1	Parcel
        //   7	72	4	localParcel2	Parcel
        //   76	12	5	localObject	Object
        //   25	47	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   9	15	76	finally
        //   19	27	76	finally
        //   27	60	76	finally
      }
      
      /* Error */
      public void j(IGamesCallbacks paramIGamesCallbacks)
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
        //   15: ifnull +46 -> 61
        //   18: aload_1
        //   19: invokeinterface 57 1 0
        //   24: astore 5
        //   26: aload_2
        //   27: aload 5
        //   29: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   32: aload_0
        //   33: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   36: sipush 11001
        //   39: aload_2
        //   40: aload_3
        //   41: iconst_0
        //   42: invokeinterface 41 5 0
        //   47: pop
        //   48: aload_3
        //   49: invokevirtual 44	android/os/Parcel:readException	()V
        //   52: aload_3
        //   53: invokevirtual 47	android/os/Parcel:recycle	()V
        //   56: aload_2
        //   57: invokevirtual 47	android/os/Parcel:recycle	()V
        //   60: return
        //   61: aconst_null
        //   62: astore 5
        //   64: goto -38 -> 26
        //   67: astore 4
        //   69: aload_3
        //   70: invokevirtual 47	android/os/Parcel:recycle	()V
        //   73: aload_2
        //   74: invokevirtual 47	android/os/Parcel:recycle	()V
        //   77: aload 4
        //   79: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	80	0	this	Proxy
        //   0	80	1	paramIGamesCallbacks	IGamesCallbacks
        //   3	71	2	localParcel1	Parcel
        //   7	63	3	localParcel2	Parcel
        //   67	11	4	localObject	Object
        //   24	39	5	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   8	14	67	finally
        //   18	26	67	finally
        //   26	52	67	finally
      }
      
      /* Error */
      public void j(IGamesCallbacks paramIGamesCallbacks, String paramString)
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
        //   16: ifnull +54 -> 70
        //   19: aload_1
        //   20: invokeinterface 57 1 0
        //   25: astore 6
        //   27: aload_3
        //   28: aload 6
        //   30: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   33: aload_3
        //   34: aload_2
        //   35: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   38: aload_0
        //   39: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   42: sipush 5057
        //   45: aload_3
        //   46: aload 4
        //   48: iconst_0
        //   49: invokeinterface 41 5 0
        //   54: pop
        //   55: aload 4
        //   57: invokevirtual 44	android/os/Parcel:readException	()V
        //   60: aload 4
        //   62: invokevirtual 47	android/os/Parcel:recycle	()V
        //   65: aload_3
        //   66: invokevirtual 47	android/os/Parcel:recycle	()V
        //   69: return
        //   70: aconst_null
        //   71: astore 6
        //   73: goto -46 -> 27
        //   76: astore 5
        //   78: aload 4
        //   80: invokevirtual 47	android/os/Parcel:recycle	()V
        //   83: aload_3
        //   84: invokevirtual 47	android/os/Parcel:recycle	()V
        //   87: aload 5
        //   89: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	90	0	this	Proxy
        //   0	90	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	90	2	paramString	String
        //   3	81	3	localParcel1	Parcel
        //   7	72	4	localParcel2	Parcel
        //   76	12	5	localObject	Object
        //   25	47	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   9	15	76	finally
        //   19	27	76	finally
        //   27	60	76	finally
      }
      
      /* Error */
      public void k(IGamesCallbacks paramIGamesCallbacks, String paramString)
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
        //   16: ifnull +54 -> 70
        //   19: aload_1
        //   20: invokeinterface 57 1 0
        //   25: astore 6
        //   27: aload_3
        //   28: aload 6
        //   30: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   33: aload_3
        //   34: aload_2
        //   35: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   38: aload_0
        //   39: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   42: sipush 7001
        //   45: aload_3
        //   46: aload 4
        //   48: iconst_0
        //   49: invokeinterface 41 5 0
        //   54: pop
        //   55: aload 4
        //   57: invokevirtual 44	android/os/Parcel:readException	()V
        //   60: aload 4
        //   62: invokevirtual 47	android/os/Parcel:recycle	()V
        //   65: aload_3
        //   66: invokevirtual 47	android/os/Parcel:recycle	()V
        //   69: return
        //   70: aconst_null
        //   71: astore 6
        //   73: goto -46 -> 27
        //   76: astore 5
        //   78: aload 4
        //   80: invokevirtual 47	android/os/Parcel:recycle	()V
        //   83: aload_3
        //   84: invokevirtual 47	android/os/Parcel:recycle	()V
        //   87: aload 5
        //   89: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	90	0	this	Proxy
        //   0	90	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	90	2	paramString	String
        //   3	81	3	localParcel1	Parcel
        //   7	72	4	localParcel2	Parcel
        //   76	12	5	localObject	Object
        //   25	47	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   9	15	76	finally
        //   19	27	76	finally
        //   27	60	76	finally
      }
      
      public boolean kA()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          this.lb.transact(5067, localParcel1, localParcel2, 0);
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
      public DataHolder kB()
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
        //   15: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   18: sipush 5502
        //   21: aload_1
        //   22: aload_2
        //   23: iconst_0
        //   24: invokeinterface 41 5 0
        //   29: pop
        //   30: aload_2
        //   31: invokevirtual 44	android/os/Parcel:readException	()V
        //   34: aload_2
        //   35: invokevirtual 71	android/os/Parcel:readInt	()I
        //   38: ifeq +27 -> 65
        //   41: getstatic 235	com/google/android/gms/common/data/DataHolder:CREATOR	Lcom/google/android/gms/common/data/f;
        //   44: aload_2
        //   45: invokevirtual 241	com/google/android/gms/common/data/f:z	(Landroid/os/Parcel;)Lcom/google/android/gms/common/data/DataHolder;
        //   48: astore 6
        //   50: aload 6
        //   52: astore 5
        //   54: aload_2
        //   55: invokevirtual 47	android/os/Parcel:recycle	()V
        //   58: aload_1
        //   59: invokevirtual 47	android/os/Parcel:recycle	()V
        //   62: aload 5
        //   64: areturn
        //   65: aconst_null
        //   66: astore 5
        //   68: goto -14 -> 54
        //   71: astore_3
        //   72: aload_2
        //   73: invokevirtual 47	android/os/Parcel:recycle	()V
        //   76: aload_1
        //   77: invokevirtual 47	android/os/Parcel:recycle	()V
        //   80: aload_3
        //   81: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	82	0	this	Proxy
        //   3	74	1	localParcel1	Parcel
        //   7	66	2	localParcel2	Parcel
        //   71	10	3	localObject	Object
        //   52	15	5	localDataHolder1	DataHolder
        //   48	3	6	localDataHolder2	DataHolder
        // Exception table:
        //   from	to	target	type
        //   8	50	71	finally
      }
      
      public void kC()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          this.lb.transact(8022, localParcel1, localParcel2, 0);
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
      public Intent kD()
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
        //   15: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   18: sipush 9013
        //   21: aload_1
        //   22: aload_2
        //   23: iconst_0
        //   24: invokeinterface 41 5 0
        //   29: pop
        //   30: aload_2
        //   31: invokevirtual 44	android/os/Parcel:readException	()V
        //   34: aload_2
        //   35: invokevirtual 71	android/os/Parcel:readInt	()I
        //   38: ifeq +28 -> 66
        //   41: getstatic 78	android/content/Intent:CREATOR	Landroid/os/Parcelable$Creator;
        //   44: aload_2
        //   45: invokeinterface 84 2 0
        //   50: checkcast 74	android/content/Intent
        //   53: astore 5
        //   55: aload_2
        //   56: invokevirtual 47	android/os/Parcel:recycle	()V
        //   59: aload_1
        //   60: invokevirtual 47	android/os/Parcel:recycle	()V
        //   63: aload 5
        //   65: areturn
        //   66: aconst_null
        //   67: astore 5
        //   69: goto -14 -> 55
        //   72: astore_3
        //   73: aload_2
        //   74: invokevirtual 47	android/os/Parcel:recycle	()V
        //   77: aload_1
        //   78: invokevirtual 47	android/os/Parcel:recycle	()V
        //   81: aload_3
        //   82: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	83	0	this	Proxy
        //   3	75	1	localParcel1	Parcel
        //   7	67	2	localParcel2	Parcel
        //   72	10	3	localObject	Object
        //   53	15	5	localIntent	Intent
        // Exception table:
        //   from	to	target	type
        //   8	55	72	finally
      }
      
      public void kE()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          this.lb.transact(11002, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public boolean kF()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          this.lb.transact(12025, localParcel1, localParcel2, 0);
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
      
      public String ka()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          this.lb.transact(5007, localParcel1, localParcel2, 0);
          localParcel2.readException();
          String str = localParcel2.readString();
          return str;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public String kb()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          this.lb.transact(5012, localParcel1, localParcel2, 0);
          localParcel2.readException();
          String str = localParcel2.readString();
          return str;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      /* Error */
      public Intent ke()
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
        //   15: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   18: sipush 9003
        //   21: aload_1
        //   22: aload_2
        //   23: iconst_0
        //   24: invokeinterface 41 5 0
        //   29: pop
        //   30: aload_2
        //   31: invokevirtual 44	android/os/Parcel:readException	()V
        //   34: aload_2
        //   35: invokevirtual 71	android/os/Parcel:readInt	()I
        //   38: ifeq +28 -> 66
        //   41: getstatic 78	android/content/Intent:CREATOR	Landroid/os/Parcelable$Creator;
        //   44: aload_2
        //   45: invokeinterface 84 2 0
        //   50: checkcast 74	android/content/Intent
        //   53: astore 5
        //   55: aload_2
        //   56: invokevirtual 47	android/os/Parcel:recycle	()V
        //   59: aload_1
        //   60: invokevirtual 47	android/os/Parcel:recycle	()V
        //   63: aload 5
        //   65: areturn
        //   66: aconst_null
        //   67: astore 5
        //   69: goto -14 -> 55
        //   72: astore_3
        //   73: aload_2
        //   74: invokevirtual 47	android/os/Parcel:recycle	()V
        //   77: aload_1
        //   78: invokevirtual 47	android/os/Parcel:recycle	()V
        //   81: aload_3
        //   82: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	83	0	this	Proxy
        //   3	75	1	localParcel1	Parcel
        //   7	67	2	localParcel2	Parcel
        //   72	10	3	localObject	Object
        //   53	15	5	localIntent	Intent
        // Exception table:
        //   from	to	target	type
        //   8	55	72	finally
      }
      
      /* Error */
      public Intent kf()
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
        //   15: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   18: sipush 9005
        //   21: aload_1
        //   22: aload_2
        //   23: iconst_0
        //   24: invokeinterface 41 5 0
        //   29: pop
        //   30: aload_2
        //   31: invokevirtual 44	android/os/Parcel:readException	()V
        //   34: aload_2
        //   35: invokevirtual 71	android/os/Parcel:readInt	()I
        //   38: ifeq +28 -> 66
        //   41: getstatic 78	android/content/Intent:CREATOR	Landroid/os/Parcelable$Creator;
        //   44: aload_2
        //   45: invokeinterface 84 2 0
        //   50: checkcast 74	android/content/Intent
        //   53: astore 5
        //   55: aload_2
        //   56: invokevirtual 47	android/os/Parcel:recycle	()V
        //   59: aload_1
        //   60: invokevirtual 47	android/os/Parcel:recycle	()V
        //   63: aload 5
        //   65: areturn
        //   66: aconst_null
        //   67: astore 5
        //   69: goto -14 -> 55
        //   72: astore_3
        //   73: aload_2
        //   74: invokevirtual 47	android/os/Parcel:recycle	()V
        //   77: aload_1
        //   78: invokevirtual 47	android/os/Parcel:recycle	()V
        //   81: aload_3
        //   82: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	83	0	this	Proxy
        //   3	75	1	localParcel1	Parcel
        //   7	67	2	localParcel2	Parcel
        //   72	10	3	localObject	Object
        //   53	15	5	localIntent	Intent
        // Exception table:
        //   from	to	target	type
        //   8	55	72	finally
      }
      
      /* Error */
      public Intent kg()
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
        //   15: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   18: sipush 9006
        //   21: aload_1
        //   22: aload_2
        //   23: iconst_0
        //   24: invokeinterface 41 5 0
        //   29: pop
        //   30: aload_2
        //   31: invokevirtual 44	android/os/Parcel:readException	()V
        //   34: aload_2
        //   35: invokevirtual 71	android/os/Parcel:readInt	()I
        //   38: ifeq +28 -> 66
        //   41: getstatic 78	android/content/Intent:CREATOR	Landroid/os/Parcelable$Creator;
        //   44: aload_2
        //   45: invokeinterface 84 2 0
        //   50: checkcast 74	android/content/Intent
        //   53: astore 5
        //   55: aload_2
        //   56: invokevirtual 47	android/os/Parcel:recycle	()V
        //   59: aload_1
        //   60: invokevirtual 47	android/os/Parcel:recycle	()V
        //   63: aload 5
        //   65: areturn
        //   66: aconst_null
        //   67: astore 5
        //   69: goto -14 -> 55
        //   72: astore_3
        //   73: aload_2
        //   74: invokevirtual 47	android/os/Parcel:recycle	()V
        //   77: aload_1
        //   78: invokevirtual 47	android/os/Parcel:recycle	()V
        //   81: aload_3
        //   82: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	83	0	this	Proxy
        //   3	75	1	localParcel1	Parcel
        //   7	67	2	localParcel2	Parcel
        //   72	10	3	localObject	Object
        //   53	15	5	localIntent	Intent
        // Exception table:
        //   from	to	target	type
        //   8	55	72	finally
      }
      
      /* Error */
      public Intent kh()
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
        //   15: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   18: sipush 9007
        //   21: aload_1
        //   22: aload_2
        //   23: iconst_0
        //   24: invokeinterface 41 5 0
        //   29: pop
        //   30: aload_2
        //   31: invokevirtual 44	android/os/Parcel:readException	()V
        //   34: aload_2
        //   35: invokevirtual 71	android/os/Parcel:readInt	()I
        //   38: ifeq +28 -> 66
        //   41: getstatic 78	android/content/Intent:CREATOR	Landroid/os/Parcelable$Creator;
        //   44: aload_2
        //   45: invokeinterface 84 2 0
        //   50: checkcast 74	android/content/Intent
        //   53: astore 5
        //   55: aload_2
        //   56: invokevirtual 47	android/os/Parcel:recycle	()V
        //   59: aload_1
        //   60: invokevirtual 47	android/os/Parcel:recycle	()V
        //   63: aload 5
        //   65: areturn
        //   66: aconst_null
        //   67: astore 5
        //   69: goto -14 -> 55
        //   72: astore_3
        //   73: aload_2
        //   74: invokevirtual 47	android/os/Parcel:recycle	()V
        //   77: aload_1
        //   78: invokevirtual 47	android/os/Parcel:recycle	()V
        //   81: aload_3
        //   82: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	83	0	this	Proxy
        //   3	75	1	localParcel1	Parcel
        //   7	67	2	localParcel2	Parcel
        //   72	10	3	localObject	Object
        //   53	15	5	localIntent	Intent
        // Exception table:
        //   from	to	target	type
        //   8	55	72	finally
      }
      
      /* Error */
      public Intent km()
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
        //   15: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   18: sipush 9010
        //   21: aload_1
        //   22: aload_2
        //   23: iconst_0
        //   24: invokeinterface 41 5 0
        //   29: pop
        //   30: aload_2
        //   31: invokevirtual 44	android/os/Parcel:readException	()V
        //   34: aload_2
        //   35: invokevirtual 71	android/os/Parcel:readInt	()I
        //   38: ifeq +28 -> 66
        //   41: getstatic 78	android/content/Intent:CREATOR	Landroid/os/Parcelable$Creator;
        //   44: aload_2
        //   45: invokeinterface 84 2 0
        //   50: checkcast 74	android/content/Intent
        //   53: astore 5
        //   55: aload_2
        //   56: invokevirtual 47	android/os/Parcel:recycle	()V
        //   59: aload_1
        //   60: invokevirtual 47	android/os/Parcel:recycle	()V
        //   63: aload 5
        //   65: areturn
        //   66: aconst_null
        //   67: astore 5
        //   69: goto -14 -> 55
        //   72: astore_3
        //   73: aload_2
        //   74: invokevirtual 47	android/os/Parcel:recycle	()V
        //   77: aload_1
        //   78: invokevirtual 47	android/os/Parcel:recycle	()V
        //   81: aload_3
        //   82: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	83	0	this	Proxy
        //   3	75	1	localParcel1	Parcel
        //   7	67	2	localParcel2	Parcel
        //   72	10	3	localObject	Object
        //   53	15	5	localIntent	Intent
        // Exception table:
        //   from	to	target	type
        //   8	55	72	finally
      }
      
      /* Error */
      public Intent kn()
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
        //   15: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   18: sipush 9012
        //   21: aload_1
        //   22: aload_2
        //   23: iconst_0
        //   24: invokeinterface 41 5 0
        //   29: pop
        //   30: aload_2
        //   31: invokevirtual 44	android/os/Parcel:readException	()V
        //   34: aload_2
        //   35: invokevirtual 71	android/os/Parcel:readInt	()I
        //   38: ifeq +28 -> 66
        //   41: getstatic 78	android/content/Intent:CREATOR	Landroid/os/Parcelable$Creator;
        //   44: aload_2
        //   45: invokeinterface 84 2 0
        //   50: checkcast 74	android/content/Intent
        //   53: astore 5
        //   55: aload_2
        //   56: invokevirtual 47	android/os/Parcel:recycle	()V
        //   59: aload_1
        //   60: invokevirtual 47	android/os/Parcel:recycle	()V
        //   63: aload 5
        //   65: areturn
        //   66: aconst_null
        //   67: astore 5
        //   69: goto -14 -> 55
        //   72: astore_3
        //   73: aload_2
        //   74: invokevirtual 47	android/os/Parcel:recycle	()V
        //   77: aload_1
        //   78: invokevirtual 47	android/os/Parcel:recycle	()V
        //   81: aload_3
        //   82: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	83	0	this	Proxy
        //   3	75	1	localParcel1	Parcel
        //   7	67	2	localParcel2	Parcel
        //   72	10	3	localObject	Object
        //   53	15	5	localIntent	Intent
        // Exception table:
        //   from	to	target	type
        //   8	55	72	finally
      }
      
      public int ko()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          this.lb.transact(9019, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public String kp()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          this.lb.transact(5003, localParcel1, localParcel2, 0);
          localParcel2.readException();
          String str = localParcel2.readString();
          return str;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public int kq()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          this.lb.transact(8024, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      /* Error */
      public Intent kr()
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
        //   15: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   18: sipush 10015
        //   21: aload_1
        //   22: aload_2
        //   23: iconst_0
        //   24: invokeinterface 41 5 0
        //   29: pop
        //   30: aload_2
        //   31: invokevirtual 44	android/os/Parcel:readException	()V
        //   34: aload_2
        //   35: invokevirtual 71	android/os/Parcel:readInt	()I
        //   38: ifeq +28 -> 66
        //   41: getstatic 78	android/content/Intent:CREATOR	Landroid/os/Parcelable$Creator;
        //   44: aload_2
        //   45: invokeinterface 84 2 0
        //   50: checkcast 74	android/content/Intent
        //   53: astore 5
        //   55: aload_2
        //   56: invokevirtual 47	android/os/Parcel:recycle	()V
        //   59: aload_1
        //   60: invokevirtual 47	android/os/Parcel:recycle	()V
        //   63: aload 5
        //   65: areturn
        //   66: aconst_null
        //   67: astore 5
        //   69: goto -14 -> 55
        //   72: astore_3
        //   73: aload_2
        //   74: invokevirtual 47	android/os/Parcel:recycle	()V
        //   77: aload_1
        //   78: invokevirtual 47	android/os/Parcel:recycle	()V
        //   81: aload_3
        //   82: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	83	0	this	Proxy
        //   3	75	1	localParcel1	Parcel
        //   7	67	2	localParcel2	Parcel
        //   72	10	3	localObject	Object
        //   53	15	5	localIntent	Intent
        // Exception table:
        //   from	to	target	type
        //   8	55	72	finally
      }
      
      public int ks()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          this.lb.transact(10013, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public int kt()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          this.lb.transact(10023, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public int ku()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          this.lb.transact(12035, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public int kv()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          this.lb.transact(12036, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void kx()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          this.lb.transact(5006, localParcel1, localParcel2, 0);
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
      public DataHolder kz()
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
        //   15: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   18: sipush 5013
        //   21: aload_1
        //   22: aload_2
        //   23: iconst_0
        //   24: invokeinterface 41 5 0
        //   29: pop
        //   30: aload_2
        //   31: invokevirtual 44	android/os/Parcel:readException	()V
        //   34: aload_2
        //   35: invokevirtual 71	android/os/Parcel:readInt	()I
        //   38: ifeq +27 -> 65
        //   41: getstatic 235	com/google/android/gms/common/data/DataHolder:CREATOR	Lcom/google/android/gms/common/data/f;
        //   44: aload_2
        //   45: invokevirtual 241	com/google/android/gms/common/data/f:z	(Landroid/os/Parcel;)Lcom/google/android/gms/common/data/DataHolder;
        //   48: astore 6
        //   50: aload 6
        //   52: astore 5
        //   54: aload_2
        //   55: invokevirtual 47	android/os/Parcel:recycle	()V
        //   58: aload_1
        //   59: invokevirtual 47	android/os/Parcel:recycle	()V
        //   62: aload 5
        //   64: areturn
        //   65: aconst_null
        //   66: astore 5
        //   68: goto -14 -> 54
        //   71: astore_3
        //   72: aload_2
        //   73: invokevirtual 47	android/os/Parcel:recycle	()V
        //   76: aload_1
        //   77: invokevirtual 47	android/os/Parcel:recycle	()V
        //   80: aload_3
        //   81: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	82	0	this	Proxy
        //   3	74	1	localParcel1	Parcel
        //   7	66	2	localParcel2	Parcel
        //   71	10	3	localObject	Object
        //   52	15	5	localDataHolder1	DataHolder
        //   48	3	6	localDataHolder2	DataHolder
        // Exception table:
        //   from	to	target	type
        //   8	50	71	finally
      }
      
      /* Error */
      public void l(IGamesCallbacks paramIGamesCallbacks, String paramString)
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
        //   16: ifnull +54 -> 70
        //   19: aload_1
        //   20: invokeinterface 57 1 0
        //   25: astore 6
        //   27: aload_3
        //   28: aload 6
        //   30: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   33: aload_3
        //   34: aload_2
        //   35: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   38: aload_0
        //   39: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   42: sipush 8005
        //   45: aload_3
        //   46: aload 4
        //   48: iconst_0
        //   49: invokeinterface 41 5 0
        //   54: pop
        //   55: aload 4
        //   57: invokevirtual 44	android/os/Parcel:readException	()V
        //   60: aload 4
        //   62: invokevirtual 47	android/os/Parcel:recycle	()V
        //   65: aload_3
        //   66: invokevirtual 47	android/os/Parcel:recycle	()V
        //   69: return
        //   70: aconst_null
        //   71: astore 6
        //   73: goto -46 -> 27
        //   76: astore 5
        //   78: aload 4
        //   80: invokevirtual 47	android/os/Parcel:recycle	()V
        //   83: aload_3
        //   84: invokevirtual 47	android/os/Parcel:recycle	()V
        //   87: aload 5
        //   89: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	90	0	this	Proxy
        //   0	90	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	90	2	paramString	String
        //   3	81	3	localParcel1	Parcel
        //   7	72	4	localParcel2	Parcel
        //   76	12	5	localObject	Object
        //   25	47	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   9	15	76	finally
        //   19	27	76	finally
        //   27	60	76	finally
      }
      
      /* Error */
      public void m(IGamesCallbacks paramIGamesCallbacks, String paramString)
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
        //   16: ifnull +54 -> 70
        //   19: aload_1
        //   20: invokeinterface 57 1 0
        //   25: astore 6
        //   27: aload_3
        //   28: aload 6
        //   30: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   33: aload_3
        //   34: aload_2
        //   35: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   38: aload_0
        //   39: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   42: sipush 8006
        //   45: aload_3
        //   46: aload 4
        //   48: iconst_0
        //   49: invokeinterface 41 5 0
        //   54: pop
        //   55: aload 4
        //   57: invokevirtual 44	android/os/Parcel:readException	()V
        //   60: aload 4
        //   62: invokevirtual 47	android/os/Parcel:recycle	()V
        //   65: aload_3
        //   66: invokevirtual 47	android/os/Parcel:recycle	()V
        //   69: return
        //   70: aconst_null
        //   71: astore 6
        //   73: goto -46 -> 27
        //   76: astore 5
        //   78: aload 4
        //   80: invokevirtual 47	android/os/Parcel:recycle	()V
        //   83: aload_3
        //   84: invokevirtual 47	android/os/Parcel:recycle	()V
        //   87: aload 5
        //   89: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	90	0	this	Proxy
        //   0	90	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	90	2	paramString	String
        //   3	81	3	localParcel1	Parcel
        //   7	72	4	localParcel2	Parcel
        //   76	12	5	localObject	Object
        //   25	47	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   9	15	76	finally
        //   19	27	76	finally
        //   27	60	76	finally
      }
      
      /* Error */
      public void n(IGamesCallbacks paramIGamesCallbacks, String paramString)
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
        //   16: ifnull +54 -> 70
        //   19: aload_1
        //   20: invokeinterface 57 1 0
        //   25: astore 6
        //   27: aload_3
        //   28: aload 6
        //   30: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   33: aload_3
        //   34: aload_2
        //   35: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   38: aload_0
        //   39: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   42: sipush 8009
        //   45: aload_3
        //   46: aload 4
        //   48: iconst_0
        //   49: invokeinterface 41 5 0
        //   54: pop
        //   55: aload 4
        //   57: invokevirtual 44	android/os/Parcel:readException	()V
        //   60: aload 4
        //   62: invokevirtual 47	android/os/Parcel:recycle	()V
        //   65: aload_3
        //   66: invokevirtual 47	android/os/Parcel:recycle	()V
        //   69: return
        //   70: aconst_null
        //   71: astore 6
        //   73: goto -46 -> 27
        //   76: astore 5
        //   78: aload 4
        //   80: invokevirtual 47	android/os/Parcel:recycle	()V
        //   83: aload_3
        //   84: invokevirtual 47	android/os/Parcel:recycle	()V
        //   87: aload 5
        //   89: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	90	0	this	Proxy
        //   0	90	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	90	2	paramString	String
        //   3	81	3	localParcel1	Parcel
        //   7	72	4	localParcel2	Parcel
        //   76	12	5	localObject	Object
        //   25	47	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   9	15	76	finally
        //   19	27	76	finally
        //   27	60	76	finally
      }
      
      public void n(String paramString, int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeString(paramString);
          localParcel1.writeInt(paramInt);
          this.lb.transact(12017, localParcel1, localParcel2, 0);
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
      public void o(IGamesCallbacks paramIGamesCallbacks, String paramString)
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
        //   16: ifnull +54 -> 70
        //   19: aload_1
        //   20: invokeinterface 57 1 0
        //   25: astore 6
        //   27: aload_3
        //   28: aload 6
        //   30: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   33: aload_3
        //   34: aload_2
        //   35: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   38: aload_0
        //   39: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   42: sipush 8010
        //   45: aload_3
        //   46: aload 4
        //   48: iconst_0
        //   49: invokeinterface 41 5 0
        //   54: pop
        //   55: aload 4
        //   57: invokevirtual 44	android/os/Parcel:readException	()V
        //   60: aload 4
        //   62: invokevirtual 47	android/os/Parcel:recycle	()V
        //   65: aload_3
        //   66: invokevirtual 47	android/os/Parcel:recycle	()V
        //   69: return
        //   70: aconst_null
        //   71: astore 6
        //   73: goto -46 -> 27
        //   76: astore 5
        //   78: aload 4
        //   80: invokevirtual 47	android/os/Parcel:recycle	()V
        //   83: aload_3
        //   84: invokevirtual 47	android/os/Parcel:recycle	()V
        //   87: aload 5
        //   89: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	90	0	this	Proxy
        //   0	90	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	90	2	paramString	String
        //   3	81	3	localParcel1	Parcel
        //   7	72	4	localParcel2	Parcel
        //   76	12	5	localObject	Object
        //   25	47	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   9	15	76	finally
        //   19	27	76	finally
        //   27	60	76	finally
      }
      
      public void o(String paramString, int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeString(paramString);
          localParcel1.writeInt(paramInt);
          this.lb.transact(5029, localParcel1, localParcel2, 0);
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
      public void p(IGamesCallbacks paramIGamesCallbacks, String paramString)
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
        //   16: ifnull +54 -> 70
        //   19: aload_1
        //   20: invokeinterface 57 1 0
        //   25: astore 6
        //   27: aload_3
        //   28: aload 6
        //   30: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   33: aload_3
        //   34: aload_2
        //   35: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   38: aload_0
        //   39: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   42: sipush 8014
        //   45: aload_3
        //   46: aload 4
        //   48: iconst_0
        //   49: invokeinterface 41 5 0
        //   54: pop
        //   55: aload 4
        //   57: invokevirtual 44	android/os/Parcel:readException	()V
        //   60: aload 4
        //   62: invokevirtual 47	android/os/Parcel:recycle	()V
        //   65: aload_3
        //   66: invokevirtual 47	android/os/Parcel:recycle	()V
        //   69: return
        //   70: aconst_null
        //   71: astore 6
        //   73: goto -46 -> 27
        //   76: astore 5
        //   78: aload 4
        //   80: invokevirtual 47	android/os/Parcel:recycle	()V
        //   83: aload_3
        //   84: invokevirtual 47	android/os/Parcel:recycle	()V
        //   87: aload 5
        //   89: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	90	0	this	Proxy
        //   0	90	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	90	2	paramString	String
        //   3	81	3	localParcel1	Parcel
        //   7	72	4	localParcel2	Parcel
        //   76	12	5	localObject	Object
        //   25	47	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   9	15	76	finally
        //   19	27	76	finally
        //   27	60	76	finally
      }
      
      public void p(String paramString, int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeString(paramString);
          localParcel1.writeInt(paramInt);
          this.lb.transact(5028, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void q(long paramLong)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeLong(paramLong);
          this.lb.transact(5001, localParcel1, localParcel2, 0);
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
      public void q(IGamesCallbacks paramIGamesCallbacks, String paramString)
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
        //   16: ifnull +54 -> 70
        //   19: aload_1
        //   20: invokeinterface 57 1 0
        //   25: astore 6
        //   27: aload_3
        //   28: aload 6
        //   30: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   33: aload_3
        //   34: aload_2
        //   35: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   38: aload_0
        //   39: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   42: sipush 9002
        //   45: aload_3
        //   46: aload 4
        //   48: iconst_0
        //   49: invokeinterface 41 5 0
        //   54: pop
        //   55: aload 4
        //   57: invokevirtual 44	android/os/Parcel:readException	()V
        //   60: aload 4
        //   62: invokevirtual 47	android/os/Parcel:recycle	()V
        //   65: aload_3
        //   66: invokevirtual 47	android/os/Parcel:recycle	()V
        //   69: return
        //   70: aconst_null
        //   71: astore 6
        //   73: goto -46 -> 27
        //   76: astore 5
        //   78: aload 4
        //   80: invokevirtual 47	android/os/Parcel:recycle	()V
        //   83: aload_3
        //   84: invokevirtual 47	android/os/Parcel:recycle	()V
        //   87: aload 5
        //   89: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	90	0	this	Proxy
        //   0	90	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	90	2	paramString	String
        //   3	81	3	localParcel1	Parcel
        //   7	72	4	localParcel2	Parcel
        //   76	12	5	localObject	Object
        //   25	47	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   9	15	76	finally
        //   19	27	76	finally
        //   27	60	76	finally
      }
      
      public void r(long paramLong)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeLong(paramLong);
          this.lb.transact(5059, localParcel1, localParcel2, 0);
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
      public void r(IGamesCallbacks paramIGamesCallbacks, String paramString)
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
        //   16: ifnull +54 -> 70
        //   19: aload_1
        //   20: invokeinterface 57 1 0
        //   25: astore 6
        //   27: aload_3
        //   28: aload 6
        //   30: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   33: aload_3
        //   34: aload_2
        //   35: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   38: aload_0
        //   39: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   42: sipush 12020
        //   45: aload_3
        //   46: aload 4
        //   48: iconst_0
        //   49: invokeinterface 41 5 0
        //   54: pop
        //   55: aload 4
        //   57: invokevirtual 44	android/os/Parcel:readException	()V
        //   60: aload 4
        //   62: invokevirtual 47	android/os/Parcel:recycle	()V
        //   65: aload_3
        //   66: invokevirtual 47	android/os/Parcel:recycle	()V
        //   69: return
        //   70: aconst_null
        //   71: astore 6
        //   73: goto -46 -> 27
        //   76: astore 5
        //   78: aload 4
        //   80: invokevirtual 47	android/os/Parcel:recycle	()V
        //   83: aload_3
        //   84: invokevirtual 47	android/os/Parcel:recycle	()V
        //   87: aload 5
        //   89: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	90	0	this	Proxy
        //   0	90	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	90	2	paramString	String
        //   3	81	3	localParcel1	Parcel
        //   7	72	4	localParcel2	Parcel
        //   76	12	5	localObject	Object
        //   25	47	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   9	15	76	finally
        //   19	27	76	finally
        //   27	60	76	finally
      }
      
      public void r(String paramString, int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeString(paramString);
          localParcel1.writeInt(paramInt);
          this.lb.transact(5055, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void s(long paramLong)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeLong(paramLong);
          this.lb.transact(8013, localParcel1, localParcel2, 0);
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
      public void s(IGamesCallbacks paramIGamesCallbacks, String paramString)
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
        //   16: ifnull +54 -> 70
        //   19: aload_1
        //   20: invokeinterface 57 1 0
        //   25: astore 6
        //   27: aload_3
        //   28: aload 6
        //   30: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   33: aload_3
        //   34: aload_2
        //   35: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   38: aload_0
        //   39: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   42: sipush 12005
        //   45: aload_3
        //   46: aload 4
        //   48: iconst_0
        //   49: invokeinterface 41 5 0
        //   54: pop
        //   55: aload 4
        //   57: invokevirtual 44	android/os/Parcel:readException	()V
        //   60: aload 4
        //   62: invokevirtual 47	android/os/Parcel:recycle	()V
        //   65: aload_3
        //   66: invokevirtual 47	android/os/Parcel:recycle	()V
        //   69: return
        //   70: aconst_null
        //   71: astore 6
        //   73: goto -46 -> 27
        //   76: astore 5
        //   78: aload 4
        //   80: invokevirtual 47	android/os/Parcel:recycle	()V
        //   83: aload_3
        //   84: invokevirtual 47	android/os/Parcel:recycle	()V
        //   87: aload 5
        //   89: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	90	0	this	Proxy
        //   0	90	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	90	2	paramString	String
        //   3	81	3	localParcel1	Parcel
        //   7	72	4	localParcel2	Parcel
        //   76	12	5	localObject	Object
        //   25	47	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   9	15	76	finally
        //   19	27	76	finally
        //   27	60	76	finally
      }
      
      public void s(String paramString, int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeString(paramString);
          localParcel1.writeInt(paramInt);
          this.lb.transact(10014, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void t(long paramLong)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeLong(paramLong);
          this.lb.transact(10002, localParcel1, localParcel2, 0);
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
      public void t(IGamesCallbacks paramIGamesCallbacks, String paramString)
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
        //   16: ifnull +54 -> 70
        //   19: aload_1
        //   20: invokeinterface 57 1 0
        //   25: astore 6
        //   27: aload_3
        //   28: aload 6
        //   30: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   33: aload_3
        //   34: aload_2
        //   35: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   38: aload_0
        //   39: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   42: sipush 12027
        //   45: aload_3
        //   46: aload 4
        //   48: iconst_0
        //   49: invokeinterface 41 5 0
        //   54: pop
        //   55: aload 4
        //   57: invokevirtual 44	android/os/Parcel:readException	()V
        //   60: aload 4
        //   62: invokevirtual 47	android/os/Parcel:recycle	()V
        //   65: aload_3
        //   66: invokevirtual 47	android/os/Parcel:recycle	()V
        //   69: return
        //   70: aconst_null
        //   71: astore 6
        //   73: goto -46 -> 27
        //   76: astore 5
        //   78: aload 4
        //   80: invokevirtual 47	android/os/Parcel:recycle	()V
        //   83: aload_3
        //   84: invokevirtual 47	android/os/Parcel:recycle	()V
        //   87: aload 5
        //   89: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	90	0	this	Proxy
        //   0	90	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	90	2	paramString	String
        //   3	81	3	localParcel1	Parcel
        //   7	72	4	localParcel2	Parcel
        //   76	12	5	localObject	Object
        //   25	47	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   9	15	76	finally
        //   19	27	76	finally
        //   27	60	76	finally
      }
      
      public void u(long paramLong)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeLong(paramLong);
          this.lb.transact(12012, localParcel1, localParcel2, 0);
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
      public void u(IGamesCallbacks paramIGamesCallbacks, String paramString)
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
        //   16: ifnull +54 -> 70
        //   19: aload_1
        //   20: invokeinterface 57 1 0
        //   25: astore 6
        //   27: aload_3
        //   28: aload 6
        //   30: invokevirtual 60	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   33: aload_3
        //   34: aload_2
        //   35: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   38: aload_0
        //   39: getfield 15	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   42: sipush 12008
        //   45: aload_3
        //   46: aload 4
        //   48: iconst_0
        //   49: invokeinterface 41 5 0
        //   54: pop
        //   55: aload 4
        //   57: invokevirtual 44	android/os/Parcel:readException	()V
        //   60: aload 4
        //   62: invokevirtual 47	android/os/Parcel:recycle	()V
        //   65: aload_3
        //   66: invokevirtual 47	android/os/Parcel:recycle	()V
        //   69: return
        //   70: aconst_null
        //   71: astore 6
        //   73: goto -46 -> 27
        //   76: astore 5
        //   78: aload 4
        //   80: invokevirtual 47	android/os/Parcel:recycle	()V
        //   83: aload_3
        //   84: invokevirtual 47	android/os/Parcel:recycle	()V
        //   87: aload 5
        //   89: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	90	0	this	Proxy
        //   0	90	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	90	2	paramString	String
        //   3	81	3	localParcel1	Parcel
        //   7	72	4	localParcel2	Parcel
        //   76	12	5	localObject	Object
        //   25	47	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   9	15	76	finally
        //   19	27	76	finally
        //   27	60	76	finally
      }
      
      public void u(String paramString1, String paramString2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeString(paramString1);
          localParcel1.writeString(paramString2);
          this.lb.transact(5065, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void v(String paramString1, String paramString2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeString(paramString1);
          localParcel1.writeString(paramString2);
          this.lb.transact(8025, localParcel1, localParcel2, 0);
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
 * Qualified Name:     com.google.android.gms.games.internal.IGamesService
 * JD-Core Version:    0.7.0.1
 */