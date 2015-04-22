package com.google.ads.mediation.millennial;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.google.ads.AdRequest.ErrorCode;
import com.google.ads.AdSize;
import com.google.ads.mediation.MediationAdRequest;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationBannerListener;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationInterstitialListener;
import com.millennialmedia.android.MMAd;
import com.millennialmedia.android.MMAdView;
import com.millennialmedia.android.MMException;
import com.millennialmedia.android.MMInterstitial;
import com.millennialmedia.android.MMRequest;
import com.millennialmedia.android.RequestListener;
import java.util.Set;

public final class MillennialAdapter
  implements MediationBannerAdapter<MillennialAdapterExtras, MillennialAdapterServerParameters>, MediationInterstitialAdapter<MillennialAdapterExtras, MillennialAdapterServerParameters>
{
  public static final int ID_BANNER = 835823882;
  private MMAdView adView;
  private MediationBannerListener bannerListener;
  private MMInterstitial interstitial;
  private MediationInterstitialListener interstitialListener;
  private FrameLayout wrappedAdView;
  
  private MMRequest createMMRequest(MediationAdRequest paramMediationAdRequest, MillennialAdapterExtras paramMillennialAdapterExtras)
  {
    MMRequest localMMRequest = new MMRequest();
    if (paramMillennialAdapterExtras == null) {
      paramMillennialAdapterExtras = new MillennialAdapterExtras();
    }
    String str1 = null;
    if (paramMediationAdRequest != null)
    {
      Set localSet = paramMediationAdRequest.getKeywords();
      str1 = null;
      if (localSet != null)
      {
        str1 = TextUtils.join(",", paramMediationAdRequest.getKeywords());
        localMMRequest.setKeywords(str1);
      }
    }
    String str2 = paramMillennialAdapterExtras.getKeywords();
    String str3;
    if ((str2 != null) && (!TextUtils.isEmpty(str2)))
    {
      if (TextUtils.isEmpty(str1))
      {
        str3 = str2;
        localMMRequest.setKeywords(str3);
      }
    }
    else
    {
      if (paramMillennialAdapterExtras.getChildren() != null) {
        localMMRequest.setChildren(paramMillennialAdapterExtras.getChildren().getDescription());
      }
      if ((paramMediationAdRequest != null) && (paramMediationAdRequest.getAgeInYears() != null)) {
        localMMRequest.setAge(paramMediationAdRequest.getAgeInYears().toString());
      }
      if (paramMillennialAdapterExtras.getAge() != -1) {
        localMMRequest.setAge(Integer.toString(paramMillennialAdapterExtras.getAge()));
      }
      if ((paramMediationAdRequest != null) && (paramMediationAdRequest.getGender() != null)) {
        switch (1.$SwitchMap$com$google$ads$AdRequest$Gender[paramMediationAdRequest.getGender().ordinal()])
        {
        }
      }
    }
    for (;;)
    {
      if (paramMillennialAdapterExtras.getGender() != null) {
        localMMRequest.setGender(paramMillennialAdapterExtras.getGender().getDescription());
      }
      if (paramMillennialAdapterExtras.getIncomeInUsDollars() != null) {
        localMMRequest.setIncome(paramMillennialAdapterExtras.getIncomeInUsDollars().toString());
      }
      if ((paramMediationAdRequest != null) && (paramMediationAdRequest.getLocation() != null)) {
        MMRequest.setUserLocation(paramMediationAdRequest.getLocation());
      }
      if (paramMillennialAdapterExtras.getLocation() != null) {
        MMRequest.setUserLocation(paramMillennialAdapterExtras.getLocation());
      }
      if (paramMillennialAdapterExtras.getPostalCode() != null) {
        localMMRequest.setZip(paramMillennialAdapterExtras.getPostalCode());
      }
      if (paramMillennialAdapterExtras.getMaritalStatus() != null) {
        localMMRequest.setMarital(paramMillennialAdapterExtras.getMaritalStatus().getDescription());
      }
      if (paramMillennialAdapterExtras.getEthnicity() != null) {
        localMMRequest.setEthnicity(paramMillennialAdapterExtras.getEthnicity().getDescription());
      }
      if (paramMillennialAdapterExtras.getPolitics() != null) {
        localMMRequest.setPolitics(paramMillennialAdapterExtras.getPolitics().getDescription());
      }
      if (paramMillennialAdapterExtras.getEducation() != null) {
        localMMRequest.setEducation(paramMillennialAdapterExtras.getEducation().getDescription());
      }
      return localMMRequest;
      str3 = str1 + "," + str2;
      break;
      localMMRequest.setGender("male");
      continue;
      localMMRequest.setGender("female");
      continue;
      localMMRequest.setGender("unknown");
    }
  }
  
  private static int dip(int paramInt, Context paramContext)
  {
    return (int)TypedValue.applyDimension(1, paramInt, paramContext.getResources().getDisplayMetrics());
  }
  
  public void destroy() {}
  
  public Class<MillennialAdapterExtras> getAdditionalParametersType()
  {
    return MillennialAdapterExtras.class;
  }
  
  public View getBannerView()
  {
    return this.wrappedAdView;
  }
  
  public Class<MillennialAdapterServerParameters> getServerParametersType()
  {
    return MillennialAdapterServerParameters.class;
  }
  
  public void requestBannerAd(MediationBannerListener paramMediationBannerListener, Activity paramActivity, MillennialAdapterServerParameters paramMillennialAdapterServerParameters, AdSize paramAdSize, MediationAdRequest paramMediationAdRequest, MillennialAdapterExtras paramMillennialAdapterExtras)
  {
    this.bannerListener = paramMediationBannerListener;
    this.adView = new MMAdView(paramActivity);
    if (paramAdSize.isSizeAppropriate(320, 53))
    {
      this.adView.setWidth(320);
      this.adView.setHeight(53);
    }
    for (FrameLayout.LayoutParams localLayoutParams = new FrameLayout.LayoutParams(dip(320, paramActivity), dip(53, paramActivity));; localLayoutParams = new FrameLayout.LayoutParams(dip(paramAdSize.getWidth(), paramActivity), dip(paramAdSize.getHeight(), paramActivity)))
    {
      this.adView.setApid(paramMillennialAdapterServerParameters.apid);
      this.adView.setMMRequest(createMMRequest(paramMediationAdRequest, paramMillennialAdapterExtras));
      this.adView.setListener(new BannerListener(null));
      this.wrappedAdView = new FrameLayout(paramActivity);
      this.wrappedAdView.setLayoutParams(localLayoutParams);
      this.wrappedAdView.addView(this.adView);
      this.adView.setId(835823882);
      this.adView.getAd();
      return;
      this.adView.setWidth(paramAdSize.getWidth());
      this.adView.setHeight(paramAdSize.getHeight());
    }
  }
  
  public void requestInterstitialAd(MediationInterstitialListener paramMediationInterstitialListener, Activity paramActivity, MillennialAdapterServerParameters paramMillennialAdapterServerParameters, MediationAdRequest paramMediationAdRequest, MillennialAdapterExtras paramMillennialAdapterExtras)
  {
    this.interstitialListener = paramMediationInterstitialListener;
    this.interstitial = new MMInterstitial(paramActivity);
    this.interstitial.setApid(paramMillennialAdapterServerParameters.apid);
    this.interstitial.setMMRequest(createMMRequest(paramMediationAdRequest, paramMillennialAdapterExtras));
    this.interstitial.setListener(new InterstitialListener(null));
    this.interstitial.fetch();
  }
  
  public void showInterstitial()
  {
    this.interstitial.display();
  }
  
  private class BannerListener
    implements RequestListener
  {
    private BannerListener() {}
    
    public void MMAdOverlayClosed(MMAd paramMMAd)
    {
      MillennialAdapter.this.bannerListener.onDismissScreen(MillennialAdapter.this);
    }
    
    public void MMAdOverlayLaunched(MMAd paramMMAd)
    {
      MillennialAdapter.this.bannerListener.onPresentScreen(MillennialAdapter.this);
    }
    
    public void MMAdRequestIsCaching(MMAd paramMMAd) {}
    
    public void onSingleTap(MMAd paramMMAd)
    {
      MillennialAdapter.this.bannerListener.onClick(MillennialAdapter.this);
    }
    
    public void requestCompleted(MMAd paramMMAd)
    {
      MillennialAdapter.this.bannerListener.onReceivedAd(MillennialAdapter.this);
    }
    
    public void requestFailed(MMAd paramMMAd, MMException paramMMException)
    {
      MillennialAdapter.this.bannerListener.onFailedToReceiveAd(MillennialAdapter.this, AdRequest.ErrorCode.NO_FILL);
    }
  }
  
  private class InterstitialListener
    implements RequestListener
  {
    private InterstitialListener() {}
    
    public void MMAdOverlayClosed(MMAd paramMMAd)
    {
      MillennialAdapter.this.interstitialListener.onDismissScreen(MillennialAdapter.this);
    }
    
    public void MMAdOverlayLaunched(MMAd paramMMAd)
    {
      MillennialAdapter.this.interstitialListener.onPresentScreen(MillennialAdapter.this);
    }
    
    public void MMAdRequestIsCaching(MMAd paramMMAd) {}
    
    public void onSingleTap(MMAd paramMMAd) {}
    
    public void requestCompleted(MMAd paramMMAd)
    {
      MillennialAdapter.this.interstitialListener.onReceivedAd(MillennialAdapter.this);
    }
    
    public void requestFailed(MMAd paramMMAd, MMException paramMMException)
    {
      if (paramMMException.getCode() == 17)
      {
        MillennialAdapter.this.interstitialListener.onReceivedAd(MillennialAdapter.this);
        return;
      }
      MillennialAdapter.this.interstitialListener.onFailedToReceiveAd(MillennialAdapter.this, AdRequest.ErrorCode.NO_FILL);
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.mediation.millennial.MillennialAdapter
 * JD-Core Version:    0.7.0.1
 */