package com.ea.nimble;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import java.io.ByteArrayInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import javax.security.auth.x500.X500Principal;

class BaseCore
  implements IApplicationLifecycle.ApplicationLifecycleCallbacks
{
  public static final String NIMBLE_COMPONENT_LIST = "setting::components";
  public static final String NIMBLE_LOG_SETTING = "setting::log";
  public static final String NIMBLE_SERVER_CONFIG = "com.ea.nimble.configuration";
  protected static BaseCore s_core;
  protected static boolean s_coreDestroyed = false;
  protected ApplicationEnvironmentImpl m_applicationEnvironment;
  protected IApplicationLifecycle m_applicationLifecycle;
  protected ComponentManager m_componentManager;
  protected NimbleConfiguration m_configuration;
  protected LogImpl m_log;
  protected PersistenceServiceImpl m_persistenceService;
  protected State m_state;
  
  private void destroy()
  {
    Log.Helper.LOGD(this, "NIMBLE DESTROY for Android will keep Core and Static components alive", new Object[0]);
  }
  
  public static BaseCore getInstance()
  {
    if (s_core == null)
    {
      if (s_coreDestroyed) {
        throw new AssertionError("Cannot revive destroyed BaseCore, please utilizesetupNimble() and tearDownNimble() explicitly to extend longevity to match your expectation.");
      }
      android.util.Log.d("Nimble", String.format("NIMBLE %s SETUP (%s)", new Object[] { "1.13.1.1009", "1.13.1.1009" }));
      s_core = new BaseCore();
      s_core.initialize();
    }
    return s_core;
  }
  
  private void initialize()
  {
    this.m_state = State.INACTIVE;
    loadConfiguration();
    this.m_componentManager = new ComponentManager();
    this.m_applicationLifecycle = new ApplicationLifecycleImpl(this);
    this.m_applicationEnvironment = new ApplicationEnvironmentImpl(this);
    this.m_log = ((LogImpl)Log.getComponent());
    this.m_log.connectToCore(this);
    this.m_persistenceService = new PersistenceServiceImpl();
    NetworkImpl localNetworkImpl = new NetworkImpl();
    SynergyEnvironmentImpl localSynergyEnvironmentImpl = new SynergyEnvironmentImpl(this);
    SynergyNetworkImpl localSynergyNetworkImpl = new SynergyNetworkImpl();
    SynergyIdManagerImpl localSynergyIdManagerImpl = new SynergyIdManagerImpl();
    this.m_componentManager.registerComponent(this.m_applicationEnvironment, "com.ea.nimble.applicationEnvironment");
    this.m_componentManager.registerComponent(this.m_log, "com.ea.nimble.NimbleLog");
    this.m_componentManager.registerComponent(this.m_persistenceService, "com.ea.nimble.persistence");
    this.m_componentManager.registerComponent(localNetworkImpl, "com.ea.nimble.network");
    this.m_componentManager.registerComponent(localSynergyIdManagerImpl, "com.ea.nimble.synergyidmanager");
    this.m_componentManager.registerComponent(localSynergyEnvironmentImpl, "com.ea.nimble.synergyEnvironment");
    this.m_componentManager.registerComponent(localSynergyNetworkImpl, "com.ea.nimble.synergynetwork");
    Iterator localIterator = getSettings("setting::components").values().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      try
      {
        Method localMethod = Class.forName(str).getDeclaredMethod("initialize", new Class[0]);
        localMethod.setAccessible(true);
        localMethod.invoke(null, new Object[0]);
      }
      catch (NullPointerException localNullPointerException)
      {
        Log.Helper.LOGE(this, "Method " + str + ".initialize() should be static", new Object[0]);
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        Log.Helper.LOGE(this, "Method " + str + ".initialize() should take no arguments", new Object[0]);
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        Log.Helper.LOGE(this, "Method " + str + ".initialize() is not accessible", new Object[0]);
      }
      catch (InvocationTargetException localInvocationTargetException)
      {
        Log.Helper.LOGE(this, "Method " + str + ".initialize() threw an exception", new Object[0]);
        localInvocationTargetException.printStackTrace();
      }
      catch (NoSuchMethodException localNoSuchMethodException)
      {
        Log.Helper.LOGE(this, "No method " + str + ".initialize()", new Object[0]);
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        Log.Helper.LOGD(this, "Component " + str + " not found", new Object[0]);
      }
    }
    try
    {
      if (!isAppSigned(ApplicationEnvironment.getComponent().getApplicationContext()))
      {
        android.util.Log.e("Nimble", "This application is NOT signed with a valid certificate. MTX may not work correctly with this application");
        return;
      }
      android.util.Log.i("Nimble", "This application is signed with a valid certificate.");
      return;
    }
    catch (Exception localException)
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = localException.getMessage();
      android.util.Log.e("Nimble", String.format("Unable to verify application signature. Message: %s", arrayOfObject));
    }
  }
  
  protected static void injectMock(BaseCore paramBaseCore)
  {
    if (paramBaseCore == null)
    {
      s_core = null;
      s_coreDestroyed = false;
      return;
    }
    s_core = paramBaseCore;
    s_coreDestroyed = false;
  }
  
  private boolean isAppSigned(Context paramContext)
  {
    X500Principal localX500Principal = new X500Principal("CN=Android Debug,O=Android,C=US");
    int i = 0;
    for (;;)
    {
      try
      {
        Signature[] arrayOfSignature = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 64).signatures;
        int j = arrayOfSignature.length;
        k = 0;
        if (k < j)
        {
          Signature localSignature = arrayOfSignature[k];
          boolean bool = ((X509Certificate)CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(localSignature.toByteArray()))).getSubjectX500Principal().equals(localX500Principal);
          i = bool;
          if (i == 0) {
            continue;
          }
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        int k;
        localNameNotFoundException.printStackTrace();
        continue;
      }
      catch (CertificateException localCertificateException)
      {
        localCertificateException.printStackTrace();
        continue;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        continue;
      }
      if (i != 0) {
        break label135;
      }
      return true;
      k++;
    }
    label135:
    return false;
  }
  
  private void loadConfiguration()
  {
    try
    {
      String str = ApplicationEnvironment.getCurrentActivity().getPackageManager().getApplicationInfo(ApplicationEnvironment.getCurrentActivity().getPackageName(), 128).metaData.getString("com.ea.nimble.configuration");
      if (Utility.validString(str))
      {
        this.m_configuration = NimbleConfiguration.fromName(str);
        if (this.m_configuration != NimbleConfiguration.UNKNOWN)
        {
          NimbleConfiguration localNimbleConfiguration1 = this.m_configuration;
          NimbleConfiguration localNimbleConfiguration2 = NimbleConfiguration.CUSTOMIZED;
          if (localNimbleConfiguration1 != localNimbleConfiguration2) {
            return;
          }
        }
      }
    }
    catch (Exception localException)
    {
      android.util.Log.e("Nimble", "WARNING! Cannot find valid NimbleConfiguration from AndroidManifest.xml");
      this.m_configuration = NimbleConfiguration.LIVE;
    }
  }
  
  public BaseCore activeValidate()
  {
    switch (2.$SwitchMap$com$ea$nimble$BaseCore$State[this.m_state.ordinal()])
    {
    default: 
      return this;
    case 1: 
      Log.Helper.LOGF(this, "Access NimbleBaseCore before setup, call setupNimble() explicitly to activate it.", new Object[0]);
      return null;
    case 2: 
      Log.Helper.LOGF(this, "Access NimbleBaseCore after clean up, call setupNimble() explicitly again to activate it.", new Object[0]);
      return null;
    }
    Log.Helper.LOGF(this, "Accessing component after destroy, only static components are available right now.", new Object[0]);
    return null;
  }
  
  public IApplicationEnvironment getApplicationEnvironment()
  {
    return this.m_applicationEnvironment;
  }
  
  public IApplicationLifecycle getApplicationLifecycle()
  {
    return this.m_applicationLifecycle;
  }
  
  public ComponentManager getComponentManager()
  {
    return this.m_componentManager;
  }
  
  public NimbleConfiguration getConfiguration()
  {
    return this.m_configuration;
  }
  
  public ILog getLog()
  {
    return this.m_log;
  }
  
  public IPersistenceService getPersistenceService()
  {
    return this.m_persistenceService;
  }
  
  public Map<String, String> getSettings(String paramString)
  {
    int j;
    if (paramString == "setting::log")
    {
      String str2 = ApplicationEnvironment.getCurrentActivity().getPackageName();
      j = ApplicationEnvironment.getComponent().getApplicationContext().getResources().getIdentifier("nimble_log", "xml", str2);
      if (j != 0) {}
    }
    int i;
    do
    {
      do
      {
        return null;
        return Utility.parseXmlFile(j);
      } while (paramString != "setting::components");
      String str1 = ApplicationEnvironment.getCurrentActivity().getPackageName();
      i = ApplicationEnvironment.getComponent().getApplicationContext().getResources().getIdentifier("components", "xml", str1);
    } while (i == 0);
    return Utility.parseXmlFile(i);
  }
  
  public void onApplicationLaunch(Intent paramIntent)
  {
    if ((this.m_state == State.INACTIVE) || (this.m_state == State.DESTROY))
    {
      this.m_componentManager.setup();
      Utility.sendBroadcast("nimble.notification.componentIndependentSetupFinished", null);
      this.m_state = State.AUTO_SETUP;
    }
    try
    {
      this.m_componentManager.restore();
      return;
    }
    catch (AssertionError localAssertionError)
    {
      this.m_state = State.INACTIVE;
      throw localAssertionError;
    }
  }
  
  public void onApplicationQuit()
  {
    switch (2.$SwitchMap$com$ea$nimble$BaseCore$State[this.m_state.ordinal()])
    {
    case 2: 
    default: 
      return;
    case 4: 
      this.m_componentManager.cleanup();
      this.m_state = State.DESTROY;
      this.m_componentManager.teardown();
      destroy();
      return;
    case 5: 
      this.m_componentManager.cleanup();
      this.m_state = State.QUITTING;
      this.m_componentManager.teardown();
      return;
    case 1: 
      Log.Helper.LOGF(this, "No app start before app quit, something must be wrong.", new Object[0]);
      return;
    }
    Log.Helper.LOGF(this, "Double app quit, something must be wrong.", new Object[0]);
  }
  
  public void onApplicationResume()
  {
    if ((this.m_state == State.MANUAL_SETUP) || (this.m_state == State.AUTO_SETUP)) {
      this.m_componentManager.resume();
    }
  }
  
  public void onApplicationSuspend()
  {
    if ((this.m_state == State.MANUAL_SETUP) || (this.m_state == State.AUTO_SETUP)) {
      this.m_componentManager.suspend();
    }
  }
  
  public void restartWithConfiguration(final NimbleConfiguration paramNimbleConfiguration)
  {
    Log.Helper.LOGE(this, ">>>>>>>>>>>>>>>>>>>>>>", new Object[0]);
    Log.Helper.LOGE(this, "restartWithConfiguration should not be used in an integration. This function is for QA testing purposes.", new Object[0]);
    Log.Helper.LOGE(this, ">>>>>>>>>>>>>>>>>>>>>>", new Object[0]);
    if (paramNimbleConfiguration == NimbleConfiguration.UNKNOWN)
    {
      Log.Helper.LOGE(this, "Cannot restart nimble with unknown configuration", new Object[0]);
      return;
    }
    new Handler(Looper.getMainLooper()).post(new Runnable()
    {
      public void run()
      {
        switch (BaseCore.2.$SwitchMap$com$ea$nimble$BaseCore$State[BaseCore.this.m_state.ordinal()])
        {
        default: 
          return;
        case 4: 
        case 5: 
          BaseCore.this.m_componentManager.cleanup();
          BaseCore.this.m_componentManager.teardown();
          BaseCore.this.m_configuration = paramNimbleConfiguration;
          BaseCore.this.m_componentManager.setup();
          Utility.sendBroadcast("nimble.notification.componentIndependentSetupFinished", null);
          BaseCore.this.m_componentManager.restore();
          return;
        case 1: 
        case 2: 
        case 3: 
          Log.Helper.LOGF(this, "Should not happen, getInstance should ensure active instance", new Object[0]);
        }
        Log.Helper.LOGF(this, "Cannot restart Nimble when app is quiting", new Object[0]);
      }
    });
  }
  
  public void setup()
  {
    switch (2.$SwitchMap$com$ea$nimble$BaseCore$State[this.m_state.ordinal()])
    {
    default: 
      return;
    case 1: 
    case 2: 
      this.m_componentManager.setup();
      this.m_state = State.MANUAL_SETUP;
      Utility.sendBroadcast("nimble.notification.componentIndependentSetupFinished", null);
      this.m_componentManager.restore();
      return;
    case 4: 
      this.m_state = State.MANUAL_SETUP;
      return;
    }
    Log.Helper.LOGF(this, "Multiple setupNimble() calls without teardownNimble().", new Object[0]);
  }
  
  public void teardown()
  {
    switch (2.$SwitchMap$com$ea$nimble$BaseCore$State[this.m_state.ordinal()])
    {
    default: 
      return;
    case 5: 
      this.m_componentManager.cleanup();
      this.m_state = State.MANUAL_TEARDOWN;
      this.m_componentManager.teardown();
      return;
    case 6: 
      this.m_state = State.DESTROY;
      destroy();
      return;
    case 1: 
    case 4: 
      Log.Helper.LOGF(this, "Cannot teardownNimble() before setupNimble().", new Object[0]);
    }
    Log.Helper.LOGF(this, "Multiple teardownNimble() calls without setupNibmle().", new Object[0]);
  }
  
  private static enum State
  {
    static
    {
      AUTO_SETUP = new State("AUTO_SETUP", 1);
      MANUAL_SETUP = new State("MANUAL_SETUP", 2);
      MANUAL_TEARDOWN = new State("MANUAL_TEARDOWN", 3);
      QUITTING = new State("QUITTING", 4);
      DESTROY = new State("DESTROY", 5);
      State[] arrayOfState = new State[6];
      arrayOfState[0] = INACTIVE;
      arrayOfState[1] = AUTO_SETUP;
      arrayOfState[2] = MANUAL_SETUP;
      arrayOfState[3] = MANUAL_TEARDOWN;
      arrayOfState[4] = QUITTING;
      arrayOfState[5] = DESTROY;
      $VALUES = arrayOfState;
    }
    
    private State() {}
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ea.nimble.BaseCore
 * JD-Core Version:    0.7.0.1
 */