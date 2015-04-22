package com.google.ads.interactivemedia.v3.b;

public class u
{
  private final long a;
  private final r.a b;
  
  u(long paramLong, r.a parama)
  {
    this.a = paramLong;
    this.b = parama;
  }
  
  public long a()
  {
    return this.a;
  }
  
  public r.a b()
  {
    return this.b;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    u localu;
    do
    {
      return true;
      if (paramObject == null) {
        return false;
      }
      if (getClass() != paramObject.getClass()) {
        return false;
      }
      localu = (u)paramObject;
      if (this.a != localu.a) {
        return false;
      }
    } while (this.b == localu.b);
    return false;
  }
  
  public int hashCode()
  {
    return 31 * (int)this.a + this.b.hashCode();
  }
  
  public String toString()
  {
    return "NativeBridgeConfig [adTimeUpdateMs=" + this.a + ", adUiStyle=" + this.b + "]";
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     com.google.ads.interactivemedia.v3.b.u
 * JD-Core Version:    0.7.0.1
 */