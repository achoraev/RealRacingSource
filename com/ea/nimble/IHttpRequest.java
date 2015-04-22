package com.ea.nimble;

import java.net.URL;
import java.util.EnumSet;
import java.util.Map;

public abstract interface IHttpRequest
{
  public abstract byte[] getData();
  
  public abstract Map<String, String> getHeaders();
  
  public abstract Method getMethod();
  
  public abstract EnumSet<OverwritePolicy> getOverwritePolicy();
  
  public abstract String getTargetFilePath();
  
  public abstract double getTimeout();
  
  public abstract URL getUrl();
  
  public static enum Method
  {
    static
    {
      DELETE = new Method("DELETE", 4);
      UNRECOGNIZED = new Method("UNRECOGNIZED", 5);
      Method[] arrayOfMethod = new Method[6];
      arrayOfMethod[0] = GET;
      arrayOfMethod[1] = HEAD;
      arrayOfMethod[2] = POST;
      arrayOfMethod[3] = PUT;
      arrayOfMethod[4] = DELETE;
      arrayOfMethod[5] = UNRECOGNIZED;
      $VALUES = arrayOfMethod;
    }
    
    private Method() {}
    
    public String toString()
    {
      switch (IHttpRequest.1.$SwitchMap$com$ea$nimble$IHttpRequest$Method[ordinal()])
      {
      default: 
        return null;
      case 1: 
        return "GET";
      case 2: 
        return "HEAD";
      case 3: 
        return "POST";
      case 4: 
        return "PUT";
      }
      return "DELETE";
    }
  }
  
  public static enum OverwritePolicy
  {
    public static final EnumSet<OverwritePolicy> OVERWRITE = EnumSet.noneOf(OverwritePolicy.class);
    public static final EnumSet<OverwritePolicy> SMART = EnumSet.allOf(OverwritePolicy.class);
    
    static
    {
      DATE_CHECK = new OverwritePolicy("DATE_CHECK", 1);
      LENGTH_CHECK = new OverwritePolicy("LENGTH_CHECK", 2);
      OverwritePolicy[] arrayOfOverwritePolicy = new OverwritePolicy[3];
      arrayOfOverwritePolicy[0] = RESUME_DOWNLOAD;
      arrayOfOverwritePolicy[1] = DATE_CHECK;
      arrayOfOverwritePolicy[2] = LENGTH_CHECK;
      $VALUES = arrayOfOverwritePolicy;
    }
    
    private OverwritePolicy() {}
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.ea.nimble.IHttpRequest
 * JD-Core Version:    0.7.0.1
 */