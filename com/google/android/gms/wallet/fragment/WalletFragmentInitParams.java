package com.google.android.gms.wallet.fragment;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;

public final class WalletFragmentInitParams
  implements SafeParcelable
{
  public static final Parcelable.Creator<WalletFragmentInitParams> CREATOR = new a();
  final int BR;
  private String Dd;
  private MaskedWalletRequest atW;
  private MaskedWallet atX;
  private int auk;
  
  private WalletFragmentInitParams()
  {
    this.BR = 1;
    this.auk = -1;
  }
  
  WalletFragmentInitParams(int paramInt1, String paramString, MaskedWalletRequest paramMaskedWalletRequest, int paramInt2, MaskedWallet paramMaskedWallet)
  {
    this.BR = paramInt1;
    this.Dd = paramString;
    this.atW = paramMaskedWalletRequest;
    this.auk = paramInt2;
    this.atX = paramMaskedWallet;
  }
  
  public static Builder newBuilder()
  {
    WalletFragmentInitParams localWalletFragmentInitParams = new WalletFragmentInitParams();
    localWalletFragmentInitParams.getClass();
    return new Builder(null);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public String getAccountName()
  {
    return this.Dd;
  }
  
  public MaskedWallet getMaskedWallet()
  {
    return this.atX;
  }
  
  public MaskedWalletRequest getMaskedWalletRequest()
  {
    return this.atW;
  }
  
  public int getMaskedWalletRequestCode()
  {
    return this.auk;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    a.a(this, paramParcel, paramInt);
  }
  
  public final class Builder
  {
    private Builder() {}
    
    public WalletFragmentInitParams build()
    {
      boolean bool1 = true;
      boolean bool2;
      if (((WalletFragmentInitParams.a(WalletFragmentInitParams.this) != null) && (WalletFragmentInitParams.b(WalletFragmentInitParams.this) == null)) || ((WalletFragmentInitParams.a(WalletFragmentInitParams.this) == null) && (WalletFragmentInitParams.b(WalletFragmentInitParams.this) != null)))
      {
        bool2 = bool1;
        o.a(bool2, "Exactly one of MaskedWallet or MaskedWalletRequest is required");
        if (WalletFragmentInitParams.c(WalletFragmentInitParams.this) < 0) {
          break label76;
        }
      }
      for (;;)
      {
        o.a(bool1, "masked wallet request code is required and must be non-negative");
        return WalletFragmentInitParams.this;
        bool2 = false;
        break;
        label76:
        bool1 = false;
      }
    }
    
    public Builder setAccountName(String paramString)
    {
      WalletFragmentInitParams.a(WalletFragmentInitParams.this, paramString);
      return this;
    }
    
    public Builder setMaskedWallet(MaskedWallet paramMaskedWallet)
    {
      WalletFragmentInitParams.a(WalletFragmentInitParams.this, paramMaskedWallet);
      return this;
    }
    
    public Builder setMaskedWalletRequest(MaskedWalletRequest paramMaskedWalletRequest)
    {
      WalletFragmentInitParams.a(WalletFragmentInitParams.this, paramMaskedWalletRequest);
      return this;
    }
    
    public Builder setMaskedWalletRequestCode(int paramInt)
    {
      WalletFragmentInitParams.a(WalletFragmentInitParams.this, paramInt);
      return this;
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.android.gms.wallet.fragment.WalletFragmentInitParams
 * JD-Core Version:    0.7.0.1
 */