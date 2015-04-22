package com.ea.nimble;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.PrintWriter;
import java.io.StringWriter;

public class Error
  extends Exception
  implements Parcelable, Externalizable
{
  public static final Parcelable.Creator<Error> CREATOR = new Parcelable.Creator()
  {
    public Error createFromParcel(Parcel paramAnonymousParcel)
    {
      return new Error(paramAnonymousParcel);
    }
    
    public Error[] newArray(int paramAnonymousInt)
    {
      return new Error[paramAnonymousInt];
    }
  };
  public static final String ERROR_DOMAIN = "NimbleError";
  private static final long serialVersionUID = 1L;
  private int m_code;
  private String m_domain;
  
  public Error() {}
  
  public Error(Parcel paramParcel)
  {
    readFromParcel(paramParcel);
  }
  
  public Error(Code paramCode, String paramString)
  {
    this(paramCode, paramString, null);
  }
  
  public Error(Code paramCode, String paramString, Throwable paramThrowable)
  {
    this("NimbleError", paramCode.intValue(), paramString, paramThrowable);
  }
  
  public Error(String paramString1, int paramInt, String paramString2)
  {
    this(paramString1, paramInt, paramString2, null);
  }
  
  public Error(String paramString1, int paramInt, String paramString2, Throwable paramThrowable)
  {
    super(paramString2, paramThrowable);
    this.m_domain = paramString1;
    this.m_code = paramInt;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public int getCode()
  {
    return this.m_code;
  }
  
  public String getDomain()
  {
    return this.m_domain;
  }
  
  public boolean isError(Code paramCode)
  {
    return this.m_code == paramCode.intValue();
  }
  
  public void readExternal(ObjectInput paramObjectInput)
    throws IOException, ClassNotFoundException
  {
    this.m_domain = paramObjectInput.readUTF();
    this.m_code = paramObjectInput.readInt();
    initCause((Throwable)paramObjectInput.readObject());
  }
  
  public void readFromParcel(Parcel paramParcel)
  {
    this.m_domain = paramParcel.readString();
    this.m_code = paramParcel.readInt();
    initCause((Throwable)paramParcel.readSerializable());
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if ((this.m_domain != null) && (this.m_domain.length() > 0)) {
      localStringBuilder.append(this.m_domain).append("(");
    }
    for (;;)
    {
      localStringBuilder.append(this.m_code).append(")");
      String str = getLocalizedMessage();
      if ((str != null) && (str.length() > 0)) {
        localStringBuilder.append(": ").append(str);
      }
      Throwable localThrowable = getCause();
      if (localThrowable != null)
      {
        localStringBuilder.append("\nCaused by: ");
        StringWriter localStringWriter = new StringWriter();
        localThrowable.printStackTrace(new PrintWriter(localStringWriter));
        localStringBuilder.append(localStringWriter.toString());
      }
      return localStringBuilder.toString();
      localStringBuilder.append("Error").append("(");
    }
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput)
    throws IOException
  {
    if ((this.m_domain != null) && (this.m_domain.length() > 0)) {
      paramObjectOutput.writeUTF(this.m_domain);
    }
    for (;;)
    {
      paramObjectOutput.writeInt(this.m_code);
      Throwable localThrowable = getCause();
      if (localThrowable != null) {
        paramObjectOutput.writeObject(localThrowable);
      }
      return;
      paramObjectOutput.writeUTF("");
    }
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    if ((this.m_domain != null) && (this.m_domain.length() > 0)) {
      paramParcel.writeString(this.m_domain);
    }
    for (;;)
    {
      paramParcel.writeInt(this.m_code);
      Throwable localThrowable = getCause();
      if (localThrowable == null) {
        break;
      }
      paramParcel.writeSerializable(localThrowable);
      return;
      paramParcel.writeString("");
    }
    paramParcel.writeSerializable("");
  }
  
  public static enum Code
  {
    private int m_value;
    
    static
    {
      SYSTEM_UNEXPECTED = new Code("SYSTEM_UNEXPECTED", 1, 100);
      NOT_READY = new Code("NOT_READY", 2, 101);
      UNSUPPORTED = new Code("UNSUPPORTED", 3, 102);
      NOT_AVAILABLE = new Code("NOT_AVAILABLE", 4, 103);
      NOT_IMPLEMENTED = new Code("NOT_IMPLEMENTED", 5, 104);
      INVALID_ARGUMENT = new Code("INVALID_ARGUMENT", 6, 301);
      MISSING_CALLBACK = new Code("MISSING_CALLBACK", 7, 300);
      NETWORK_UNSUPPORTED_CONNECTION_TYPE = new Code("NETWORK_UNSUPPORTED_CONNECTION_TYPE", 8, 1001);
      NETWORK_NO_CONNECTION = new Code("NETWORK_NO_CONNECTION", 9, 1002);
      NETWORK_UNREACHABLE = new Code("NETWORK_UNREACHABLE", 10, 1003);
      NETWORK_OVERSIZE_DATA = new Code("NETWORK_OVERSIZE_DATA", 11, 1004);
      NETWORK_OPERATION_CANCELLED = new Code("NETWORK_OPERATION_CANCELLED", 12, 1005);
      NETWORK_INVALID_SERVER_RESPONSE = new Code("NETWORK_INVALID_SERVER_RESPONSE", 13, 1006);
      NETWORK_TIMEOUT = new Code("NETWORK_TIMEOUT", 14, 1007);
      NETWORK_CONNECTION_ERROR = new Code("NETWORK_CONNECTION_ERROR", 15, 1010);
      SYNERGY_SERVER_FULL = new Code("SYNERGY_SERVER_FULL", 16, 2001);
      SYNERGY_GET_DIRECTION_TIMEOUT = new Code("SYNERGY_GET_DIRECTION_TIMEOUT", 17, 2002);
      SYNERGY_GET_EA_DEVICE_ID_FAILURE = new Code("SYNERGY_GET_EA_DEVICE_ID_FAILURE", 18, 2003);
      SYNERGY_VALIDATE_EA_DEVICE_ID_FAILURE = new Code("SYNERGY_VALIDATE_EA_DEVICE_ID_FAILURE", 19, 2004);
      SYNERGY_GET_ANONYMOUS_ID_FAILURE = new Code("SYNERGY_GET_ANONYMOUS_ID_FAILURE", 20, 2005);
      SYNERGY_ENVIRONMENT_UPDATE_FAILURE = new Code("SYNERGY_ENVIRONMENT_UPDATE_FAILURE", 21, 2006);
      SYNERGY_PURCHASE_VERIFICATION_FAILURE = new Code("SYNERGY_PURCHASE_VERIFICATION_FAILURE", 22, 2007);
      SYNERGY_GET_NONCE_FAILURE = new Code("SYNERGY_GET_NONCE_FAILURE", 23, 2008);
      SYNERGY_GET_AGE_COMPLIANCE_FAILURE = new Code("SYNERGY_GET_AGE_COMPLIANCE_FAILURE", 24, 2009);
      Code[] arrayOfCode = new Code[25];
      arrayOfCode[0] = UNKNOWN;
      arrayOfCode[1] = SYSTEM_UNEXPECTED;
      arrayOfCode[2] = NOT_READY;
      arrayOfCode[3] = UNSUPPORTED;
      arrayOfCode[4] = NOT_AVAILABLE;
      arrayOfCode[5] = NOT_IMPLEMENTED;
      arrayOfCode[6] = INVALID_ARGUMENT;
      arrayOfCode[7] = MISSING_CALLBACK;
      arrayOfCode[8] = NETWORK_UNSUPPORTED_CONNECTION_TYPE;
      arrayOfCode[9] = NETWORK_NO_CONNECTION;
      arrayOfCode[10] = NETWORK_UNREACHABLE;
      arrayOfCode[11] = NETWORK_OVERSIZE_DATA;
      arrayOfCode[12] = NETWORK_OPERATION_CANCELLED;
      arrayOfCode[13] = NETWORK_INVALID_SERVER_RESPONSE;
      arrayOfCode[14] = NETWORK_TIMEOUT;
      arrayOfCode[15] = NETWORK_CONNECTION_ERROR;
      arrayOfCode[16] = SYNERGY_SERVER_FULL;
      arrayOfCode[17] = SYNERGY_GET_DIRECTION_TIMEOUT;
      arrayOfCode[18] = SYNERGY_GET_EA_DEVICE_ID_FAILURE;
      arrayOfCode[19] = SYNERGY_VALIDATE_EA_DEVICE_ID_FAILURE;
      arrayOfCode[20] = SYNERGY_GET_ANONYMOUS_ID_FAILURE;
      arrayOfCode[21] = SYNERGY_ENVIRONMENT_UPDATE_FAILURE;
      arrayOfCode[22] = SYNERGY_PURCHASE_VERIFICATION_FAILURE;
      arrayOfCode[23] = SYNERGY_GET_NONCE_FAILURE;
      arrayOfCode[24] = SYNERGY_GET_AGE_COMPLIANCE_FAILURE;
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
 * Qualified Name:     com.ea.nimble.Error
 * JD-Core Version:    0.7.0.1
 */