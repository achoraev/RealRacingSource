package com.ea.nimble;

public class SynergyServerError
  extends Error
{
  public static final String ERROR_DOMAIN = "SynergyServerError";
  private static final long serialVersionUID = 1L;
  
  public SynergyServerError(int paramInt, String paramString)
  {
    super("SynergyServerError", paramInt, paramString, null);
  }
  
  public SynergyServerError(int paramInt, String paramString, Throwable paramThrowable)
  {
    super("SynergyServerError", paramInt, paramString, paramThrowable);
  }
  
  public boolean isError(int paramInt)
  {
    return getCode() == paramInt;
  }
  
  public static enum Code
  {
    private int m_value;
    
    static
    {
      ERROR_NOT_SUPPORTED_RECEIPT_TYPE = new Code("ERROR_NOT_SUPPORTED_RECEIPT_TYPE", 2, -30015);
      AMAZON_SERVER_CONNECTION_ERROR = new Code("AMAZON_SERVER_CONNECTION_ERROR", 3, -30016);
      APPLE_SERVER_CONNECTION_ERROR = new Code("APPLE_SERVER_CONNECTION_ERROR", 4, 10001);
      Code[] arrayOfCode = new Code[5];
      arrayOfCode[0] = ERROR_NONCE_VERIFICATION;
      arrayOfCode[1] = ERROR_SIGNATURE_VERIFICATION;
      arrayOfCode[2] = ERROR_NOT_SUPPORTED_RECEIPT_TYPE;
      arrayOfCode[3] = AMAZON_SERVER_CONNECTION_ERROR;
      arrayOfCode[4] = APPLE_SERVER_CONNECTION_ERROR;
      $VALUES = arrayOfCode;
    }
    
    private Code(int paramInt)
    {
      this.m_value = paramInt;
    }
    
    public int intValue()
    {
      return this.m_value;
    }
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ea.nimble.SynergyServerError
 * JD-Core Version:    0.7.0.1
 */