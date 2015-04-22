package com.ea.nimble;

class SynergyNetworkConnection
  implements SynergyNetworkConnectionHandle
{
  private SynergyNetworkConnectionCallback m_completionCallback;
  private SynergyNetworkConnectionCallback m_headerCallback;
  private NetworkConnectionHandle m_networkHandle = null;
  private SynergyNetworkConnectionCallback m_progressCallback;
  private SynergyRequest m_request;
  private SynergyResponse m_response;
  
  public SynergyNetworkConnection(SynergyRequest paramSynergyRequest, SynergyNetworkConnectionCallback paramSynergyNetworkConnectionCallback)
  {
    this.m_request = paramSynergyRequest;
    this.m_response = new SynergyResponse();
    this.m_headerCallback = null;
    this.m_progressCallback = null;
    this.m_completionCallback = paramSynergyNetworkConnectionCallback;
  }
  
  private void updateNetworkHeaderHandler()
  {
    if (this.m_headerCallback == null)
    {
      this.m_networkHandle.setHeaderCallback(null);
      return;
    }
    this.m_networkHandle.setHeaderCallback(new NetworkConnectionCallback()
    {
      public void callback(NetworkConnectionHandle paramAnonymousNetworkConnectionHandle)
      {
        SynergyNetworkConnection.this.m_headerCallback.callback(SynergyNetworkConnection.this);
      }
    });
  }
  
  private void updateNetworkProgressHandler()
  {
    if (this.m_progressCallback == null)
    {
      this.m_networkHandle.setProgressCallback(null);
      return;
    }
    this.m_networkHandle.setProgressCallback(new NetworkConnectionCallback()
    {
      public void callback(NetworkConnectionHandle paramAnonymousNetworkConnectionHandle)
      {
        SynergyNetworkConnection.this.m_progressCallback.callback(SynergyNetworkConnection.this);
      }
    });
  }
  
  public void cancel()
  {
    this.m_networkHandle.cancel();
  }
  
  public void errorPriorToSend(Exception paramException)
  {
    HttpResponse localHttpResponse = new HttpResponse();
    localHttpResponse.error = paramException;
    localHttpResponse.isCompleted = true;
    this.m_response.httpResponse = localHttpResponse;
    if (this.m_completionCallback != null) {
      this.m_completionCallback.callback(this);
    }
  }
  
  public SynergyNetworkConnectionCallback getCompletionCallback()
  {
    return this.m_completionCallback;
  }
  
  public SynergyNetworkConnectionCallback getHeaderCallback()
  {
    return this.m_headerCallback;
  }
  
  public NetworkConnectionHandle getNetworkConnectionHandle()
  {
    return this.m_networkHandle;
  }
  
  public SynergyNetworkConnectionCallback getProgressCallback()
  {
    return this.m_progressCallback;
  }
  
  public ISynergyRequest getRequest()
  {
    return this.m_request;
  }
  
  public ISynergyResponse getResponse()
  {
    return this.m_response;
  }
  
  void send()
  {
    try
    {
      this.m_request.build();
      this.m_networkHandle = Network.getComponent().sendRequest(this.m_request.httpRequest, new NetworkConnectionCallback()
      {
        public void callback(NetworkConnectionHandle paramAnonymousNetworkConnectionHandle)
        {
          if (SynergyNetworkConnection.this.m_networkHandle == null)
          {
            SynergyNetworkConnection.access$002(SynergyNetworkConnection.this, paramAnonymousNetworkConnectionHandle);
            SynergyNetworkConnection.this.m_response.httpResponse = paramAnonymousNetworkConnectionHandle.getResponse();
          }
          SynergyNetworkConnection.this.m_response.parseData();
          paramAnonymousNetworkConnectionHandle.setHeaderCallback(null);
          paramAnonymousNetworkConnectionHandle.setProgressCallback(null);
          paramAnonymousNetworkConnectionHandle.setCompletionCallback(null);
          if (SynergyNetworkConnection.this.m_completionCallback != null) {
            SynergyNetworkConnection.this.m_completionCallback.callback(SynergyNetworkConnection.this);
          }
        }
      });
      this.m_response.httpResponse = this.m_networkHandle.getResponse();
      updateNetworkHeaderHandler();
      updateNetworkProgressHandler();
      return;
    }
    catch (Exception localException)
    {
      errorPriorToSend(localException);
    }
  }
  
  public void setCompletionCallback(SynergyNetworkConnectionCallback paramSynergyNetworkConnectionCallback)
  {
    this.m_completionCallback = paramSynergyNetworkConnectionCallback;
  }
  
  public void setHeaderCallback(SynergyNetworkConnectionCallback paramSynergyNetworkConnectionCallback)
  {
    this.m_headerCallback = paramSynergyNetworkConnectionCallback;
    if (this.m_networkHandle != null) {
      updateNetworkHeaderHandler();
    }
  }
  
  public void setNetworkConnectionHandle(NetworkConnectionHandle paramNetworkConnectionHandle)
  {
    this.m_networkHandle = paramNetworkConnectionHandle;
  }
  
  public void setProgressCallback(SynergyNetworkConnectionCallback paramSynergyNetworkConnectionCallback)
  {
    this.m_progressCallback = paramSynergyNetworkConnectionCallback;
    if (this.m_networkHandle != null) {
      updateNetworkProgressHandler();
    }
  }
  
  public void start()
  {
    this.m_request.prepare(this);
  }
  
  public void waitOn()
  {
    this.m_networkHandle.waitOn();
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ea.nimble.SynergyNetworkConnection
 * JD-Core Version:    0.7.0.1
 */