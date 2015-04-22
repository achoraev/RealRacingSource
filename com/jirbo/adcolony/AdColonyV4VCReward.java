package com.jirbo.adcolony;

public class AdColonyV4VCReward
{
  int amount;
  String name;
  boolean success;
  
  AdColonyV4VCReward(boolean paramBoolean, String paramString, int paramInt)
  {
    this.success = paramBoolean;
    this.name = paramString;
    this.amount = paramInt;
  }
  
  public int amount()
  {
    return this.amount;
  }
  
  public String name()
  {
    return this.name;
  }
  
  public boolean success()
  {
    return this.success;
  }
  
  public String toString()
  {
    if (this.success) {
      return this.name + ":" + this.amount;
    }
    return "no reward";
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.jirbo.adcolony.AdColonyV4VCReward
 * JD-Core Version:    0.7.0.1
 */