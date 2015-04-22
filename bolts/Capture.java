package bolts;

public class Capture<T>
{
  private T value;
  
  public Capture() {}
  
  public Capture(T paramT)
  {
    this.value = paramT;
  }
  
  public T get()
  {
    return this.value;
  }
  
  public void set(T paramT)
  {
    this.value = paramT;
  }
}


/* Location:           E:\Dropbox\Dropbox\RealRacingHack\Decompile\install_dex2jar.jar
 * Qualified Name:     bolts.Capture
 * JD-Core Version:    0.7.0.1
 */