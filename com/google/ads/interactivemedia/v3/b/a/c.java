package com.google.ads.interactivemedia.v3.b.a;

public class c
{
  public String clickThroughUrl;
  public String companionId;
  public String size;
  public String src;
  public a type;
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    c localc;
    do
    {
      do
      {
        return true;
        if (paramObject == null) {
          return false;
        }
        if (getClass() != paramObject.getClass()) {
          return false;
        }
        localc = (c)paramObject;
        if (this.clickThroughUrl == null)
        {
          if (localc.clickThroughUrl != null) {
            return false;
          }
        }
        else if (!this.clickThroughUrl.equals(localc.clickThroughUrl)) {
          return false;
        }
        if ((this.companionId != null) && (localc.companionId != null) && (!this.companionId.equals(localc.companionId))) {
          return false;
        }
        if (this.size == null)
        {
          if (localc.size != null) {
            return false;
          }
        }
        else if (!this.size.equals(localc.size)) {
          return false;
        }
        if (this.src != null) {
          break;
        }
      } while (localc.src == null);
      return false;
    } while (this.src.equals(localc.src));
    return false;
  }
  
  public String toString()
  {
    return "CompanionData [companionId=" + this.companionId + ", size=" + this.size + ", src=" + this.src + ", clickThroughUrl=" + this.clickThroughUrl + "]";
  }
  
  public static enum a
  {
    static
    {
      IFrame = new a("IFrame", 2);
      a[] arrayOfa = new a[3];
      arrayOfa[0] = Html;
      arrayOfa[1] = Static;
      arrayOfa[2] = IFrame;
      $VALUES = arrayOfa;
    }
    
    private a() {}
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.b.a.c
 * JD-Core Version:    0.7.0.1
 */