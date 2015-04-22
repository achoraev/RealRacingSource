package com.ea.nimble;

class HttpError
  extends Error
{
  public static final String ERROR_DOMAIN = "HttpError";
  private static final long serialVersionUID = 1L;
  
  public HttpError(int paramInt, String paramString)
  {
    super("HttpError", paramInt, paramString, null);
  }
  
  public HttpError(int paramInt, String paramString, Throwable paramThrowable)
  {
    super("HttpError", paramInt, paramString, paramThrowable);
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ea.nimble.HttpError
 * JD-Core Version:    0.7.0.1
 */