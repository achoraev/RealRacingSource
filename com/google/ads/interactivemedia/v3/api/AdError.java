package com.google.ads.interactivemedia.v3.api;

public class AdError
  extends Exception
{
  private final AdErrorCode a;
  private final AdErrorType b;
  
  public AdError(AdErrorType paramAdErrorType, int paramInt, String paramString)
  {
    this(paramAdErrorType, AdErrorCode.a(paramInt), paramString);
  }
  
  public AdError(AdErrorType paramAdErrorType, AdErrorCode paramAdErrorCode, String paramString)
  {
    super(paramString);
    this.b = paramAdErrorType;
    this.a = paramAdErrorCode;
  }
  
  public AdErrorCode getErrorCode()
  {
    return this.a;
  }
  
  public AdErrorType getErrorType()
  {
    return this.b;
  }
  
  public String getMessage()
  {
    return super.getMessage();
  }
  
  public static enum AdErrorCode
  {
    private final int a;
    
    static
    {
      UNKNOWN_AD_RESPONSE = new AdErrorCode("UNKNOWN_AD_RESPONSE", 2, 200);
      VAST_LOAD_TIMEOUT = new AdErrorCode("VAST_LOAD_TIMEOUT", 3, 301);
      VAST_TOO_MANY_REDIRECTS = new AdErrorCode("VAST_TOO_MANY_REDIRECTS", 4, 302);
      VAST_INVALID_URL = new AdErrorCode("VAST_INVALID_URL", 5, 303);
      VIDEO_PLAY_ERROR = new AdErrorCode("VIDEO_PLAY_ERROR", 6, 400);
      VAST_MEDIA_LOAD_TIMEOUT = new AdErrorCode("VAST_MEDIA_LOAD_TIMEOUT", 7, 402);
      VAST_LINEAR_ASSET_MISMATCH = new AdErrorCode("VAST_LINEAR_ASSET_MISMATCH", 8, 403);
      OVERLAY_AD_PLAYING_FAILED = new AdErrorCode("OVERLAY_AD_PLAYING_FAILED", 9, 500);
      OVERLAY_AD_LOADING_FAILED = new AdErrorCode("OVERLAY_AD_LOADING_FAILED", 10, 502);
      VAST_NONLINEAR_ASSET_MISMATCH = new AdErrorCode("VAST_NONLINEAR_ASSET_MISMATCH", 11, 503);
      COMPANION_AD_LOADING_FAILED = new AdErrorCode("COMPANION_AD_LOADING_FAILED", 12, 603);
      UNKNOWN_ERROR = new AdErrorCode("UNKNOWN_ERROR", 13, 900);
      PLAYLIST_MALFORMED_RESPONSE = new AdErrorCode("PLAYLIST_MALFORMED_RESPONSE", 14, 1004);
      FAILED_TO_REQUEST_ADS = new AdErrorCode("FAILED_TO_REQUEST_ADS", 15, 1005);
      REQUIRED_LISTENERS_NOT_ADDED = new AdErrorCode("REQUIRED_LISTENERS_NOT_ADDED", 16, 1006);
      VAST_ASSET_NOT_FOUND = new AdErrorCode("VAST_ASSET_NOT_FOUND", 17, 1007);
      ADS_REQUEST_NETWORK_ERROR = new AdErrorCode("ADS_REQUEST_NETWORK_ERROR", 18, 1012);
      INVALID_ARGUMENTS = new AdErrorCode("INVALID_ARGUMENTS", 19, 1101);
      API_ERROR = new AdErrorCode("API_ERROR", 20, 1102);
      AdErrorCode[] arrayOfAdErrorCode = new AdErrorCode[21];
      arrayOfAdErrorCode[0] = INTERNAL_ERROR;
      arrayOfAdErrorCode[1] = VAST_MALFORMED_RESPONSE;
      arrayOfAdErrorCode[2] = UNKNOWN_AD_RESPONSE;
      arrayOfAdErrorCode[3] = VAST_LOAD_TIMEOUT;
      arrayOfAdErrorCode[4] = VAST_TOO_MANY_REDIRECTS;
      arrayOfAdErrorCode[5] = VAST_INVALID_URL;
      arrayOfAdErrorCode[6] = VIDEO_PLAY_ERROR;
      arrayOfAdErrorCode[7] = VAST_MEDIA_LOAD_TIMEOUT;
      arrayOfAdErrorCode[8] = VAST_LINEAR_ASSET_MISMATCH;
      arrayOfAdErrorCode[9] = OVERLAY_AD_PLAYING_FAILED;
      arrayOfAdErrorCode[10] = OVERLAY_AD_LOADING_FAILED;
      arrayOfAdErrorCode[11] = VAST_NONLINEAR_ASSET_MISMATCH;
      arrayOfAdErrorCode[12] = COMPANION_AD_LOADING_FAILED;
      arrayOfAdErrorCode[13] = UNKNOWN_ERROR;
      arrayOfAdErrorCode[14] = PLAYLIST_MALFORMED_RESPONSE;
      arrayOfAdErrorCode[15] = FAILED_TO_REQUEST_ADS;
      arrayOfAdErrorCode[16] = REQUIRED_LISTENERS_NOT_ADDED;
      arrayOfAdErrorCode[17] = VAST_ASSET_NOT_FOUND;
      arrayOfAdErrorCode[18] = ADS_REQUEST_NETWORK_ERROR;
      arrayOfAdErrorCode[19] = INVALID_ARGUMENTS;
      arrayOfAdErrorCode[20] = API_ERROR;
      b = arrayOfAdErrorCode;
    }
    
    private AdErrorCode(int paramInt)
    {
      this.a = paramInt;
    }
    
    static AdErrorCode a(int paramInt)
    {
      for (AdErrorCode localAdErrorCode : ) {
        if (localAdErrorCode.a() == paramInt) {
          return localAdErrorCode;
        }
      }
      if (1204 == paramInt) {
        return INTERNAL_ERROR;
      }
      return UNKNOWN_ERROR;
    }
    
    int a()
    {
      return this.a;
    }
    
    public boolean equals(int paramInt)
    {
      return this.a == paramInt;
    }
  }
  
  public static enum AdErrorType
  {
    static
    {
      AdErrorType[] arrayOfAdErrorType = new AdErrorType[2];
      arrayOfAdErrorType[0] = LOAD;
      arrayOfAdErrorType[1] = PLAY;
      a = arrayOfAdErrorType;
    }
    
    private AdErrorType() {}
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.api.AdError
 * JD-Core Version:    0.7.0.1
 */