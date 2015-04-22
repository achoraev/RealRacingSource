package com.google.android.gms.wearable.internal;

import android.content.Context;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.ParcelFileDescriptor.AutoCloseOutputStream;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.BaseImplementation.b;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.e;
import com.google.android.gms.common.internal.e.e;
import com.google.android.gms.common.internal.l;
import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.DataApi.DataItemResult;
import com.google.android.gms.wearable.DataApi.DataListener;
import com.google.android.gms.wearable.DataApi.DeleteDataItemsResult;
import com.google.android.gms.wearable.DataApi.GetFdForAssetResult;
import com.google.android.gms.wearable.DataItemAsset;
import com.google.android.gms.wearable.DataItemBuffer;
import com.google.android.gms.wearable.MessageApi.MessageListener;
import com.google.android.gms.wearable.MessageApi.SendMessageResult;
import com.google.android.gms.wearable.NodeApi.GetConnectedNodesResult;
import com.google.android.gms.wearable.NodeApi.GetLocalNodeResult;
import com.google.android.gms.wearable.NodeApi.NodeListener;
import com.google.android.gms.wearable.PutDataRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class aw
  extends e<af>
{
  private final ExecutorService aqA = Executors.newCachedThreadPool();
  private final HashMap<DataApi.DataListener, ax> avQ = new HashMap();
  private final HashMap<MessageApi.MessageListener, ax> avR = new HashMap();
  private final HashMap<NodeApi.NodeListener, ax> avS = new HashMap();
  
  public aw(Context paramContext, Looper paramLooper, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    super(paramContext, paramLooper, paramConnectionCallbacks, paramOnConnectionFailedListener, new String[0]);
  }
  
  private FutureTask<Boolean> a(final ParcelFileDescriptor paramParcelFileDescriptor, final byte[] paramArrayOfByte)
  {
    new FutureTask(new Callable()
    {
      public Boolean qa()
      {
        if (Log.isLoggable("WearableClient", 3)) {
          Log.d("WearableClient", "processAssets: writing data to FD : " + paramParcelFileDescriptor);
        }
        ParcelFileDescriptor.AutoCloseOutputStream localAutoCloseOutputStream = new ParcelFileDescriptor.AutoCloseOutputStream(paramParcelFileDescriptor);
        try
        {
          localAutoCloseOutputStream.write(paramArrayOfByte);
          localAutoCloseOutputStream.flush();
          if (Log.isLoggable("WearableClient", 3)) {
            Log.d("WearableClient", "processAssets: wrote data: " + paramParcelFileDescriptor);
          }
          Boolean localBoolean = Boolean.valueOf(true);
          label266:
          return localBoolean;
        }
        catch (IOException localIOException2)
        {
          localIOException2 = localIOException2;
          Log.w("WearableClient", "processAssets: writing data failed: " + paramParcelFileDescriptor);
          return Boolean.valueOf(false);
        }
        finally
        {
          try
          {
            if (Log.isLoggable("WearableClient", 3)) {
              Log.d("WearableClient", "processAssets: closing: " + paramParcelFileDescriptor);
            }
            localAutoCloseOutputStream.close();
          }
          catch (IOException localIOException1)
          {
            break label266;
          }
        }
      }
    });
  }
  
  protected void a(int paramInt, IBinder paramIBinder, Bundle paramBundle)
  {
    if (Log.isLoggable("WearableClient", 2)) {
      Log.d("WearableClient", "onPostInitHandler: statusCode " + paramInt);
    }
    a local1;
    af localaf;
    if (paramInt == 0) {
      try
      {
        local1 = new a()
        {
          public void a(Status paramAnonymousStatus) {}
        };
        if (Log.isLoggable("WearableClient", 2)) {
          Log.d("WearableClient", "onPostInitHandler: service " + paramIBinder);
        }
        localaf = af.a.bT(paramIBinder);
        Iterator localIterator1 = this.avQ.entrySet().iterator();
        while (localIterator1.hasNext())
        {
          Map.Entry localEntry3 = (Map.Entry)localIterator1.next();
          if (Log.isLoggable("WearableClient", 2)) {
            Log.d("WearableClient", "onPostInitHandler: adding Data listener " + localEntry3.getValue());
          }
          localaf.a(local1, new b((ax)localEntry3.getValue()));
          continue;
          Log.d("WearableClient", "WearableClientImpl.onPostInitHandler: done");
        }
      }
      catch (RemoteException localRemoteException)
      {
        Log.d("WearableClient", "WearableClientImpl.onPostInitHandler: error while adding listener", localRemoteException);
      }
    }
    for (;;)
    {
      super.a(paramInt, paramIBinder, paramBundle);
      return;
      Iterator localIterator2 = this.avR.entrySet().iterator();
      while (localIterator2.hasNext())
      {
        Map.Entry localEntry2 = (Map.Entry)localIterator2.next();
        if (Log.isLoggable("WearableClient", 2)) {
          Log.d("WearableClient", "onPostInitHandler: adding Message listener " + localEntry2.getValue());
        }
        localaf.a(local1, new b((ax)localEntry2.getValue()));
      }
      Iterator localIterator3 = this.avS.entrySet().iterator();
      while (localIterator3.hasNext())
      {
        Map.Entry localEntry1 = (Map.Entry)localIterator3.next();
        if (Log.isLoggable("WearableClient", 2)) {
          Log.d("WearableClient", "onPostInitHandler: adding Node listener " + localEntry1.getValue());
        }
        localaf.a(local1, new b((ax)localEntry1.getValue()));
      }
    }
  }
  
  public void a(final BaseImplementation.b<DataApi.DataItemResult> paramb, Uri paramUri)
    throws RemoteException
  {
    ((af)gS()).a(new a()
    {
      public void a(x paramAnonymousx)
      {
        paramb.b(new f.a(new Status(paramAnonymousx.statusCode), paramAnonymousx.avA));
      }
    }, paramUri);
  }
  
  public void a(final BaseImplementation.b<DataApi.GetFdForAssetResult> paramb, Asset paramAsset)
    throws RemoteException
  {
    ((af)gS()).a(new a()
    {
      public void a(z paramAnonymousz)
      {
        paramb.b(new f.c(new Status(paramAnonymousz.statusCode), paramAnonymousz.avB));
      }
    }, paramAsset);
  }
  
  public void a(BaseImplementation.b<Status> paramb, DataApi.DataListener paramDataListener)
    throws RemoteException
  {
    ae localae;
    synchronized (this.avQ)
    {
      localae = (ae)this.avQ.remove(paramDataListener);
      if (localae == null)
      {
        paramb.b(new Status(4002));
        return;
      }
    }
    a(paramb, localae);
  }
  
  public void a(final BaseImplementation.b<Status> paramb, final DataApi.DataListener paramDataListener, IntentFilter[] paramArrayOfIntentFilter)
    throws RemoteException
  {
    ax localax = ax.a(paramDataListener, paramArrayOfIntentFilter);
    synchronized (this.avQ)
    {
      if (this.avQ.get(paramDataListener) != null)
      {
        paramb.b(new Status(4001));
        return;
      }
      this.avQ.put(paramDataListener, localax);
      ((af)gS()).a(new a()new b
      {
        public void a(Status paramAnonymousStatus)
        {
          if (!paramAnonymousStatus.isSuccess()) {}
          synchronized (aw.b(aw.this))
          {
            aw.b(aw.this).remove(paramDataListener);
            paramb.b(paramAnonymousStatus);
            return;
          }
        }
      }, new b(localax));
      return;
    }
  }
  
  public void a(BaseImplementation.b<DataApi.GetFdForAssetResult> paramb, DataItemAsset paramDataItemAsset)
    throws RemoteException
  {
    a(paramb, Asset.createFromRef(paramDataItemAsset.getId()));
  }
  
  public void a(BaseImplementation.b<Status> paramb, MessageApi.MessageListener paramMessageListener)
    throws RemoteException
  {
    synchronized (this.avR)
    {
      ae localae = (ae)this.avR.remove(paramMessageListener);
      if (localae == null)
      {
        paramb.b(new Status(4002));
        return;
      }
      a(paramb, localae);
    }
  }
  
  public void a(final BaseImplementation.b<Status> paramb, final MessageApi.MessageListener paramMessageListener, IntentFilter[] paramArrayOfIntentFilter)
    throws RemoteException
  {
    ax localax = ax.a(paramMessageListener, paramArrayOfIntentFilter);
    synchronized (this.avR)
    {
      if (this.avR.get(paramMessageListener) != null)
      {
        paramb.b(new Status(4001));
        return;
      }
      this.avR.put(paramMessageListener, localax);
      ((af)gS()).a(new a()new b
      {
        public void a(Status paramAnonymousStatus)
        {
          if (!paramAnonymousStatus.isSuccess()) {}
          synchronized (aw.c(aw.this))
          {
            aw.c(aw.this).remove(paramMessageListener);
            paramb.b(paramAnonymousStatus);
            return;
          }
        }
      }, new b(localax));
      return;
    }
  }
  
  public void a(final BaseImplementation.b<Status> paramb, final NodeApi.NodeListener paramNodeListener)
    throws RemoteException, RemoteException
  {
    ax localax = ax.a(paramNodeListener);
    synchronized (this.avS)
    {
      if (this.avS.get(paramNodeListener) != null)
      {
        paramb.b(new Status(4001));
        return;
      }
      this.avS.put(paramNodeListener, localax);
      ((af)gS()).a(new a()new b
      {
        public void a(Status paramAnonymousStatus)
        {
          if (!paramAnonymousStatus.isSuccess()) {}
          synchronized (aw.d(aw.this))
          {
            aw.d(aw.this).remove(paramNodeListener);
            paramb.b(paramAnonymousStatus);
            return;
          }
        }
      }, new b(localax));
      return;
    }
  }
  
  public void a(BaseImplementation.b<DataApi.DataItemResult> paramb, PutDataRequest paramPutDataRequest)
    throws RemoteException
  {
    Iterator localIterator1 = paramPutDataRequest.getAssets().entrySet().iterator();
    while (localIterator1.hasNext())
    {
      Asset localAsset2 = (Asset)((Map.Entry)localIterator1.next()).getValue();
      if ((localAsset2.getData() == null) && (localAsset2.getDigest() == null) && (localAsset2.getFd() == null) && (localAsset2.getUri() == null)) {
        throw new IllegalArgumentException("Put for " + paramPutDataRequest.getUri() + " contains invalid asset: " + localAsset2);
      }
    }
    PutDataRequest localPutDataRequest = PutDataRequest.k(paramPutDataRequest.getUri());
    localPutDataRequest.setData(paramPutDataRequest.getData());
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator2 = paramPutDataRequest.getAssets().entrySet().iterator();
    while (localIterator2.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator2.next();
      Asset localAsset1 = (Asset)localEntry.getValue();
      if (localAsset1.getData() == null) {
        localPutDataRequest.putAsset((String)localEntry.getKey(), (Asset)localEntry.getValue());
      } else {
        try
        {
          ParcelFileDescriptor[] arrayOfParcelFileDescriptor = ParcelFileDescriptor.createPipe();
          if (Log.isLoggable("WearableClient", 3)) {
            Log.d("WearableClient", "processAssets: replacing data with FD in asset: " + localAsset1 + " read:" + arrayOfParcelFileDescriptor[0] + " write:" + arrayOfParcelFileDescriptor[1]);
          }
          localPutDataRequest.putAsset((String)localEntry.getKey(), Asset.createFromFd(arrayOfParcelFileDescriptor[0]));
          FutureTask localFutureTask = a(arrayOfParcelFileDescriptor[1], localAsset1.getData());
          localArrayList.add(localFutureTask);
          this.aqA.submit(localFutureTask);
        }
        catch (IOException localIOException)
        {
          throw new IllegalStateException("Unable to create ParcelFileDescriptor for asset in request: " + paramPutDataRequest, localIOException);
        }
      }
    }
    try
    {
      ((af)gS()).a(new a(paramb, localArrayList), localPutDataRequest);
      return;
    }
    catch (NullPointerException localNullPointerException)
    {
      throw new IllegalStateException("Unable to putDataItem: " + paramPutDataRequest, localNullPointerException);
    }
  }
  
  public void a(final BaseImplementation.b<Status> paramb, ae paramae)
    throws RemoteException
  {
    ((af)gS()).a(new a()new aq
    {
      public void a(Status paramAnonymousStatus)
      {
        paramb.b(paramAnonymousStatus);
      }
    }, new aq(paramae));
  }
  
  public void a(final BaseImplementation.b<MessageApi.SendMessageResult> paramb, String paramString1, String paramString2, byte[] paramArrayOfByte)
    throws RemoteException
  {
    ((af)gS()).a(new a()
    {
      public void a(as paramAnonymousas)
      {
        paramb.b(new ag.a(new Status(paramAnonymousas.statusCode), paramAnonymousas.avO));
      }
    }, paramString1, paramString2, paramArrayOfByte);
  }
  
  protected void a(l paraml, e.e parame)
    throws RemoteException
  {
    paraml.e(parame, 6171000, getContext().getPackageName());
  }
  
  public void b(final BaseImplementation.b<DataItemBuffer> paramb, Uri paramUri)
    throws RemoteException
  {
    ((af)gS()).b(new a()
    {
      public void aa(DataHolder paramAnonymousDataHolder)
      {
        paramb.b(new DataItemBuffer(paramAnonymousDataHolder));
      }
    }, paramUri);
  }
  
  public void b(BaseImplementation.b<Status> paramb, NodeApi.NodeListener paramNodeListener)
    throws RemoteException
  {
    synchronized (this.avS)
    {
      ae localae = (ae)this.avS.remove(paramNodeListener);
      if (localae == null)
      {
        paramb.b(new Status(4002));
        return;
      }
      a(paramb, localae);
    }
  }
  
  protected af bU(IBinder paramIBinder)
  {
    return af.a.bT(paramIBinder);
  }
  
  public void c(final BaseImplementation.b<DataApi.DeleteDataItemsResult> paramb, Uri paramUri)
    throws RemoteException
  {
    ((af)gS()).c(new a()
    {
      public void a(p paramAnonymousp)
      {
        paramb.b(new f.b(new Status(paramAnonymousp.statusCode), paramAnonymousp.avw));
      }
    }, paramUri);
  }
  
  public void disconnect()
  {
    super.disconnect();
    this.avQ.clear();
    this.avR.clear();
    this.avS.clear();
  }
  
  protected String getServiceDescriptor()
  {
    return "com.google.android.gms.wearable.internal.IWearableService";
  }
  
  protected String getStartServiceAction()
  {
    return "com.google.android.gms.wearable.BIND";
  }
  
  public void o(final BaseImplementation.b<DataItemBuffer> paramb)
    throws RemoteException
  {
    ((af)gS()).b(new a()
    {
      public void aa(DataHolder paramAnonymousDataHolder)
      {
        paramb.b(new DataItemBuffer(paramAnonymousDataHolder));
      }
    });
  }
  
  public void p(final BaseImplementation.b<NodeApi.GetLocalNodeResult> paramb)
    throws RemoteException
  {
    ((af)gS()).c(new a()
    {
      public void a(ab paramAnonymousab)
      {
        paramb.b(new aj.b(new Status(paramAnonymousab.statusCode), paramAnonymousab.avC));
      }
    });
  }
  
  public void q(final BaseImplementation.b<NodeApi.GetConnectedNodesResult> paramb)
    throws RemoteException
  {
    ((af)gS()).d(new a()
    {
      public void a(v paramAnonymousv)
      {
        ArrayList localArrayList = new ArrayList();
        localArrayList.addAll(paramAnonymousv.avz);
        paramb.b(new aj.a(new Status(paramAnonymousv.statusCode), localArrayList));
      }
    });
  }
  
  private static class a
    extends a
  {
    private final BaseImplementation.b<DataApi.DataItemResult> De;
    private final List<FutureTask<Boolean>> avW;
    
    a(BaseImplementation.b<DataApi.DataItemResult> paramb, List<FutureTask<Boolean>> paramList)
    {
      this.De = paramb;
      this.avW = paramList;
    }
    
    public void a(ao paramao)
    {
      this.De.b(new f.a(new Status(paramao.statusCode), paramao.avA));
      if (paramao.statusCode != 0)
      {
        Iterator localIterator = this.avW.iterator();
        while (localIterator.hasNext()) {
          ((FutureTask)localIterator.next()).cancel(true);
        }
      }
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.wearable.internal.aw
 * JD-Core Version:    0.7.0.1
 */