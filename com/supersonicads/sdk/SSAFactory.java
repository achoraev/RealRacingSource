package com.supersonicads.sdk;

import android.content.Context;
import com.supersonicads.sdk.agent.SupersonicAdsAdvertiserAgent;
import com.supersonicads.sdk.agent.SupersonicAdsPublisherAgent;
import com.supersonicads.sdk.data.SSAEnums.DebugMode;

public class SSAFactory
{
  public static SSAAdvertiser getAdvertiserInstance()
  {
    return SupersonicAdsAdvertiserAgent.getInstance();
  }
  
  public static SSAAdvertiserTest getAdvertiserTestInstance()
  {
    return SupersonicAdsAdvertiserAgent.getInstance();
  }
  
  public static SSAPublisher getPublisherInstance(Context paramContext)
  {
    return SupersonicAdsPublisherAgent.getInstance(paramContext);
  }
  
  public static SSAPublisher getPublisherTestInstance(Context paramContext)
  {
    return SupersonicAdsPublisherAgent.getInstance(paramContext, SSAEnums.DebugMode.MODE_2.getValue());
  }
  
  public static SSAPublisher getPublisherTestInstance(Context paramContext, String paramString, int paramInt)
  {
    return SupersonicAdsPublisherAgent.getInstance(paramContext, paramString, paramInt);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.supersonicads.sdk.SSAFactory
 * JD-Core Version:    0.7.0.1
 */