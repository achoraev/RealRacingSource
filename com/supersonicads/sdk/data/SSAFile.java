package com.supersonicads.sdk.data;

public class SSAFile
  extends SSAObj
{
  private String FILE = "file";
  private String LAST_UPDATE_TIME = "lastUpdateTime";
  private String PATH = "path";
  private String mErrMsg;
  private String mFile;
  private String mLastUpdateTime;
  private String mPath;
  
  public SSAFile(String paramString)
  {
    super(paramString);
    if (containsKey(this.FILE)) {
      setFile(getString(this.FILE));
    }
    if (containsKey(this.PATH)) {
      setPath(getString(this.PATH));
    }
    if (containsKey(this.LAST_UPDATE_TIME)) {
      setLastUpdateTime(getString(this.LAST_UPDATE_TIME));
    }
  }
  
  public SSAFile(String paramString1, String paramString2)
  {
    setFile(paramString1);
    setPath(paramString2);
  }
  
  private void setFile(String paramString)
  {
    this.mFile = paramString;
  }
  
  private void setPath(String paramString)
  {
    this.mPath = paramString;
  }
  
  public String getErrMsg()
  {
    return this.mErrMsg;
  }
  
  public String getFile()
  {
    return this.mFile;
  }
  
  public String getLastUpdateTime()
  {
    return this.mLastUpdateTime;
  }
  
  public String getPath()
  {
    return this.mPath;
  }
  
  public void setErrMsg(String paramString)
  {
    this.mErrMsg = paramString;
  }
  
  public void setLastUpdateTime(String paramString)
  {
    this.mLastUpdateTime = paramString;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.supersonicads.sdk.data.SSAFile
 * JD-Core Version:    0.7.0.1
 */