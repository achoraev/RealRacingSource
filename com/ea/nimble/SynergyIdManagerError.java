package com.ea.nimble;

public class SynergyIdManagerError
  extends Error
{
  public static final String ERROR_DOMAIN = "NimbleSynergyIdManager";
  private static final long serialVersionUID = 1L;
  
  public SynergyIdManagerError(int paramInt, String paramString)
  {
    super("NimbleSynergyIdManager", paramInt, paramString, null);
  }
  
  public SynergyIdManagerError(int paramInt, String paramString, Throwable paramThrowable)
  {
    super("NimbleSynergyIdManager", paramInt, paramString, paramThrowable);
  }
  
  public static enum Code
  {
    private int m_value;
    
    static
    {
      INVALID_ID = new Code("INVALID_ID", 2, 5002);
      MISSING_AUTHENTICATOR = new Code("MISSING_AUTHENTICATOR", 3, 5003);
      Code[] arrayOfCode = new Code[4];
      arrayOfCode[0] = AUTHENTICATOR_CONFLICT;
      arrayOfCode[1] = UNEXPECTED_LOGIN_STATE;
      arrayOfCode[2] = INVALID_ID;
      arrayOfCode[3] = MISSING_AUTHENTICATOR;
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
 * Qualified Name:     com.ea.nimble.SynergyIdManagerError
 * JD-Core Version:    0.7.0.1
 */