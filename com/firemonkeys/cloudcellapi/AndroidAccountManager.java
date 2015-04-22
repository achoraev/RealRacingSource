package com.firemonkeys.cloudcellapi;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.os.Bundle;

public class AndroidAccountManager
{
  private static AndroidAccountManager s_instance;
  private boolean m_addingAccount = false;
  private long m_loginCompleteCallback = 0L;
  private long m_userObject = 0L;
  
  static
  {
    if (!AndroidAccountManager.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      s_instance = null;
      return;
    }
  }
  
  private static native void LoginCompleteCallback(boolean paramBoolean, long paramLong1, long paramLong2);
  
  public static AndroidAccountManager getInstance()
  {
    return s_instance;
  }
  
  public void Constructor(long paramLong1, long paramLong2)
  {
    s_instance = this;
    this.m_loginCompleteCallback = paramLong1;
    this.m_userObject = paramLong2;
  }
  
  public void Destructor()
  {
    s_instance = null;
    this.m_loginCompleteCallback = 0L;
    this.m_userObject = 0L;
  }
  
  boolean IsLoggedIn()
  {
    assert (CC_Activity.GetActivity() != null);
    Account[] arrayOfAccount = AccountManager.get(CC_Activity.GetActivity()).getAccountsByType("com.google");
    return (arrayOfAccount != null) && (arrayOfAccount.length > 0);
  }
  
  void Login()
  {
    assert (CC_Activity.GetActivity() != null);
    boolean bool = false;
    final AccountManager localAccountManager = AccountManager.get(CC_Activity.GetActivity());
    Account[] arrayOfAccount = localAccountManager.getAccountsByType("com.google");
    try
    {
      if (arrayOfAccount.length == 0)
      {
        Consts.Logger("No google account found");
        this.m_addingAccount = true;
        localAccountManager.addAccount("com.google", "android", null, new Bundle(), CC_Activity.GetActivity(), new AccountManagerCallback()
        {
          public void run(AccountManagerFuture<Bundle> paramAnonymousAccountManagerFuture)
          {
            if (AndroidAccountManager.this.m_addingAccount)
            {
              AndroidAccountManager.access$002(AndroidAccountManager.this, false);
              int i = localAccountManager.getAccountsByType("com.google").length;
              boolean bool = false;
              if (i > 0) {
                bool = true;
              }
              AndroidAccountManager.LoginCompleteCallback(bool, AndroidAccountManager.this.m_loginCompleteCallback, AndroidAccountManager.this.m_userObject);
            }
          }
        }, null);
      }
      for (;;)
      {
        if (!this.m_addingAccount) {
          LoginCompleteCallback(bool, this.m_loginCompleteCallback, this.m_userObject);
        }
        return;
        bool = true;
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        this.m_addingAccount = false;
        localException.printStackTrace();
        bool = false;
      }
    }
  }
  
  public void onResume()
  {
    if (this.m_addingAccount)
    {
      this.m_addingAccount = false;
      if ((this.m_loginCompleteCallback != 0L) && (this.m_userObject != 0L))
      {
        int i = AccountManager.get(CC_Activity.GetActivity()).getAccountsByType("com.google").length;
        boolean bool = false;
        if (i > 0) {
          bool = true;
        }
        LoginCompleteCallback(bool, this.m_loginCompleteCallback, this.m_userObject);
      }
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.firemonkeys.cloudcellapi.AndroidAccountManager
 * JD-Core Version:    0.7.0.1
 */