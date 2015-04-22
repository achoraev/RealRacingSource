package com.google.ads.interactivemedia.v3.api;

public class ImaSdkSettings
{
  private transient String language = "en";
  private String ppid;
  
  public String getLanguage()
  {
    return this.language;
  }
  
  public String getPpid()
  {
    return this.ppid;
  }
  
  public void setLanguage(String paramString)
  {
    this.language = paramString;
  }
  
  public void setPpid(String paramString)
  {
    this.ppid = paramString;
  }
  
  public String toString()
  {
    return "ImaSdkSettings [ppid=" + this.ppid + ", language=" + this.language + "]";
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.api.ImaSdkSettings
 * JD-Core Version:    0.7.0.1
 */