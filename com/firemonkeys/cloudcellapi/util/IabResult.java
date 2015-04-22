package com.firemonkeys.cloudcellapi.util;

import com.firemonkeys.cloudcellapi.Consts.ResponseCode;

public class IabResult
{
  String mMessage;
  Consts.ResponseCode mResponse;
  String mSku;
  
  public IabResult(Consts.ResponseCode paramResponseCode, String paramString)
  {
    this.mResponse = paramResponseCode;
    this.mMessage = paramString;
  }
  
  public IabResult(Consts.ResponseCode paramResponseCode, String paramString1, String paramString2)
  {
    this(paramResponseCode, paramString1);
    this.mSku = paramString2;
  }
  
  public String getMessage()
  {
    return this.mMessage;
  }
  
  public Consts.ResponseCode getResponse()
  {
    return this.mResponse;
  }
  
  public String getSku()
  {
    return this.mSku;
  }
  
  public boolean isFailure()
  {
    return !isSuccess();
  }
  
  public boolean isSuccess()
  {
    return this.mResponse == Consts.ResponseCode.RESULT_OK;
  }
  
  public String toString()
  {
    return "IabResult: " + getMessage();
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.firemonkeys.cloudcellapi.util.IabResult
 * JD-Core Version:    0.7.0.1
 */