package com.firemonkeys.cloudcellapi.util;

import com.firemonkeys.cloudcellapi.Consts.ResponseCode;

public class IabException
  extends Exception
{
  IabResult mResult;
  
  public IabException(Consts.ResponseCode paramResponseCode, String paramString)
  {
    this(new IabResult(paramResponseCode, paramString));
  }
  
  public IabException(Consts.ResponseCode paramResponseCode, String paramString, Exception paramException)
  {
    this(new IabResult(paramResponseCode, paramString), paramException);
  }
  
  public IabException(IabResult paramIabResult)
  {
    this(paramIabResult, null);
  }
  
  public IabException(IabResult paramIabResult, Exception paramException)
  {
    super(paramIabResult.getMessage(), paramException);
    this.mResult = paramIabResult;
  }
  
  public IabResult getResult()
  {
    return this.mResult;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.firemonkeys.cloudcellapi.util.IabException
 * JD-Core Version:    0.7.0.1
 */