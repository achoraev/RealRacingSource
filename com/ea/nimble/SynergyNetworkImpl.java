package com.ea.nimble;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

public class SynergyNetworkImpl
  extends Component
  implements ISynergyNetwork
{
  private ArrayList<SynergyNetworkConnection> m_pendingRequests = null;
  private String m_sessionId;
  private BroadcastReceiver m_synergyEnvironmentNotifyReceiver = null;
  
  private String generateSessionId()
  {
    return UUID.randomUUID().toString().replace("-", "").toLowerCase(Locale.US);
  }
  
  public static ISynergyNetwork getComponent()
  {
    return (ISynergyNetwork)Base.getComponent("com.ea.nimble.synergynetwork");
  }
  
  private SynergyNetworkConnectionHandle sendSynergyRequest(SynergyRequest paramSynergyRequest, SynergyNetworkConnectionCallback paramSynergyNetworkConnectionCallback)
  {
    SynergyNetworkConnection localSynergyNetworkConnection = new SynergyNetworkConnection(paramSynergyRequest, paramSynergyNetworkConnectionCallback);
    localSynergyNetworkConnection.start();
    return localSynergyNetworkConnection;
  }
  
  public void cleanup()
  {
    if (this.m_synergyEnvironmentNotifyReceiver != null)
    {
      Utility.unregisterReceiver(this.m_synergyEnvironmentNotifyReceiver);
      this.m_synergyEnvironmentNotifyReceiver = null;
    }
    this.m_pendingRequests.clear();
  }
  
  public String getComponentId()
  {
    return "com.ea.nimble.synergynetwork";
  }
  
  String getSessionId()
  {
    return this.m_sessionId;
  }
  
  public void restore()
  {
    if (!SynergyEnvironment.getComponent().isDataAvailable())
    {
      this.m_synergyEnvironmentNotifyReceiver = new BroadcastReceiver()
      {
        public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
        {
          if (paramAnonymousIntent.getStringExtra("result").equals("0"))
          {
            Iterator localIterator2 = SynergyNetworkImpl.this.m_pendingRequests.iterator();
            while (localIterator2.hasNext()) {
              ((SynergyNetworkConnection)localIterator2.next()).errorPriorToSend(new Error(Error.Code.SYNERGY_ENVIRONMENT_UPDATE_FAILURE, "Failed to retrieve Environment data from Synergy"));
            }
          }
          Iterator localIterator1 = SynergyNetworkImpl.this.m_pendingRequests.iterator();
          while (localIterator1.hasNext()) {
            ((SynergyNetworkConnection)localIterator1.next()).start();
          }
          SynergyNetworkImpl.this.m_pendingRequests.clear();
        }
      };
      Utility.registerReceiver("nimble.environment.notification.startup_requests_finished", this.m_synergyEnvironmentNotifyReceiver);
    }
  }
  
  protected void resume()
  {
    this.m_sessionId = generateSessionId();
  }
  
  public SynergyNetworkConnectionHandle sendGetRequest(String paramString1, String paramString2, Map<String, String> paramMap, SynergyNetworkConnectionCallback paramSynergyNetworkConnectionCallback)
  {
    SynergyRequest localSynergyRequest = new SynergyRequest(paramString2, IHttpRequest.Method.GET, null);
    localSynergyRequest.baseUrl = paramString1;
    localSynergyRequest.urlParameters = paramMap;
    return sendSynergyRequest(localSynergyRequest, paramSynergyNetworkConnectionCallback);
  }
  
  public SynergyNetworkConnectionHandle sendPostRequest(String paramString1, String paramString2, Map<String, String> paramMap, Map<String, Object> paramMap1, SynergyNetworkConnectionCallback paramSynergyNetworkConnectionCallback)
  {
    SynergyRequest localSynergyRequest = new SynergyRequest(paramString2, IHttpRequest.Method.POST, null);
    localSynergyRequest.baseUrl = paramString1;
    localSynergyRequest.urlParameters = paramMap;
    localSynergyRequest.jsonData = paramMap1;
    return sendSynergyRequest(localSynergyRequest, paramSynergyNetworkConnectionCallback);
  }
  
  public SynergyNetworkConnectionHandle sendPostRequest(String paramString1, String paramString2, Map<String, String> paramMap1, Map<String, Object> paramMap, SynergyNetworkConnectionCallback paramSynergyNetworkConnectionCallback, Map<String, String> paramMap2)
  {
    SynergyRequest localSynergyRequest = new SynergyRequest(paramString2, IHttpRequest.Method.POST, null);
    localSynergyRequest.baseUrl = paramString1;
    localSynergyRequest.urlParameters = paramMap1;
    localSynergyRequest.jsonData = paramMap;
    if (paramMap2 != null) {
      localSynergyRequest.httpRequest.headers.putAll(paramMap2);
    }
    return sendSynergyRequest(localSynergyRequest, paramSynergyNetworkConnectionCallback);
  }
  
  public void sendRequest(SynergyRequest paramSynergyRequest, SynergyNetworkConnectionCallback paramSynergyNetworkConnectionCallback)
  {
    ISynergyEnvironment localISynergyEnvironment = SynergyEnvironment.getComponent();
    if (localISynergyEnvironment.isDataAvailable())
    {
      sendSynergyRequest(paramSynergyRequest, paramSynergyNetworkConnectionCallback);
      return;
    }
    SynergyNetworkConnection localSynergyNetworkConnection = new SynergyNetworkConnection(paramSynergyRequest, paramSynergyNetworkConnectionCallback);
    if (localISynergyEnvironment.isUpdateInProgress())
    {
      this.m_pendingRequests.add(localSynergyNetworkConnection);
      return;
    }
    Error localError = localISynergyEnvironment.checkAndInitiateSynergyEnvironmentUpdate();
    if (localError == null)
    {
      this.m_pendingRequests.add(localSynergyNetworkConnection);
      return;
    }
    localSynergyNetworkConnection.errorPriorToSend(localError);
  }
  
  public void setup()
  {
    this.m_pendingRequests = new ArrayList();
    this.m_sessionId = generateSessionId();
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ea.nimble.SynergyNetworkImpl
 * JD-Core Version:    0.7.0.1
 */